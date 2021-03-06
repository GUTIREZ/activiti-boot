package com.syscxp.biz.core.db;

import com.alibaba.druid.pool.DruidDataSource;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import com.syscxp.biz.core.db.vo.EO;
import com.syscxp.biz.core.exception.ServiceException;
import com.syscxp.biz.core.utils.zstack.BeanUtils;
import com.syscxp.biz.core.utils.zstack.DebugUtils;
import com.syscxp.biz.core.utils.zstack.ObjectUtils;
import com.syscxp.biz.core.utils.zstack.db.FieldUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.*;

import static com.syscxp.biz.core.utils.zstack.CollectionDSL.list;


@Component
public class DatabaseFacadeImpl implements DatabaseFacade {
    private static final Logger logger = LoggerFactory.getLogger(DatabaseFacadeImpl.class);

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private DruidDataSource dataSource;
    private DataSource extraDataSource = null;
    private List<TransactionalCallback> transactionAsyncCallbacks = null;
    private List<TransactionalSyncCallback> transactionSyncCallbacks = null;
    private Map<Class, EntityInfo> entityInfoMap = new HashMap<>();
    private String dbVersion;

    class EntityInfo {
        Field voPrimaryKeyField;
        Field eoPrimaryKeyField;
        Field eoSoftDeleteColumn;
        Class eoClass;
        Class voClass;

        EntityInfo(Class voClazz) {
            voClass = voClazz;
            voPrimaryKeyField = FieldUtils.getAnnotatedField(Id.class, voClass);
            DebugUtils.Assert(voPrimaryKeyField != null, String.format("%s has no primary key", voClass));
            voPrimaryKeyField.setAccessible(true);

            EO at = (EO) voClazz.getAnnotation(EO.class);
            if (at != null) {
                eoClass = at.EOClazz();
                DebugUtils.Assert(eoClass != null, String.format("cannot find EO entity specified by VO entity[%s]", voClazz.getName()));
                eoPrimaryKeyField = FieldUtils.getAnnotatedField(Id.class, eoClass);
                DebugUtils.Assert(eoPrimaryKeyField != null, String.format("cannot find primary key field(@Id annotated) in EO entity[%s]", eoClass.getName()));
                eoPrimaryKeyField.setAccessible(true);
                eoSoftDeleteColumn = FieldUtils.getField(at.softDeletedColumn(), eoClass);
                DebugUtils.Assert(eoSoftDeleteColumn != null, String.format("cannot find soft delete column[%s] in EO entity[%s]", at.softDeletedColumn(), eoClass.getName()));
                eoSoftDeleteColumn.setAccessible(true);
            }
        }

        boolean hasEO() {
            return eoClass != null;
        }

        private Object getEOPrimaryKeyValue(Object entity) {
            try {
                return eoPrimaryKeyField.get(entity);
            } catch (IllegalAccessException e) {
                throw new ServiceException(e.getMessage());
            }
        }

        private Object getVOPrimaryKeyValue(Object entity) {
            try {
                return voPrimaryKeyField.get(entity);
            } catch (IllegalAccessException e) {
                throw new ServiceException(e.getMessage());
            }
        }

        private void updateEO(Object entity, DataIntegrityViolationException de) {
            if (!MySQLIntegrityConstraintViolationException.class.isAssignableFrom(de.getRootCause().getClass())) {
                throw de;
            }

            MySQLIntegrityConstraintViolationException me = (MySQLIntegrityConstraintViolationException) de.getRootCause();
            if (!(me.getErrorCode() == 1062 && "23000".equals(me.getSQLState()) && me.getMessage().contains("PRIMARY"))) {
                throw de;
            }

            if (!hasEO()) {
                throw de;
            }

            // at this point, the error is caused by a update tried on VO entity which has been soft deleted. This is mostly
            // caused by a deletion cascade(e.g deleting host will cause vm running on it to be deleted, and deleting vm is trying to return capacity
            // to host which has been soft deleted, because vm deletion is executed in async manner). In this case, we make the update to EO table

            Object idval = getEOPrimaryKeyValue(entity);
            Object eo = getEntityManager().find(eoClass, idval);
            final Object deo = ObjectUtils.copy(eo, entity);
            new Runnable() {
                @Override
                @Transactional(propagation = Propagation.REQUIRES_NEW)
                public void run() {
                    getEntityManager().merge(deo);
                }
            }.run();
            logger.debug(String.format("A EO[%s] update has been made", eoClass.getName()));
        }

        @Transactional(propagation = Propagation.REQUIRES_NEW)
        private Object update(Object e, boolean refresh) {
            try {
                e = getEntityManager().merge(e);
                if (refresh) {
                    getEntityManager().flush();
                    getEntityManager().refresh(e);
                }
                return e;
            } catch (DataIntegrityViolationException de) {
                updateEO(e, de);
            }

            return e;
        }

        @DeadlockAutoRestart
        void update(Object e) {
            update(e, false);
        }

        @DeadlockAutoRestart
        Object updateAndRefresh(Object e) {
            return update(e, true);
        }

        private void hardDelete(Object entity) {
            entity = getEntityManager().merge(entity);
            getEntityManager().remove(entity);
            Object idval = getVOPrimaryKeyValue(entity);
        }

        private void softDelete(Object entity) {
            try {
                Object idval = getEOPrimaryKeyValue(entity);
                if (idval == null) {
                    // the entity is physically deleted
                    return;
                }

                Object eo = getEntityManager().find(eoClass, idval);
                eoSoftDeleteColumn.set(eo, new Timestamp(System.currentTimeMillis()).toString());
                getEntityManager().merge(eo);
            } catch (ServiceException ce) {
                throw ce;
            } catch (Exception e) {
                throw new ServiceException(e.getMessage());
            }
        }

        private void softDelete(Collection ids) {
            String sql = String.format("update %s eo set eo.%s = (:date) where eo.%s in (:ids)",
                    eoClass.getSimpleName(), eoSoftDeleteColumn.getName(), eoPrimaryKeyField.getName());
            Query q = getEntityManager().createQuery(sql);
            q.setParameter("ids", ids);
            q.setParameter("date", new Timestamp(System.currentTimeMillis()).toString());
            q.executeUpdate();
        }

        private void hardDelete(Collection ids) {
            String tblName = hasEO() ? eoClass.getSimpleName() : voClass.getSimpleName();
            String sql = String.format("delete from %s eo where eo.%s in (:ids)", tblName, voPrimaryKeyField.getName());
            Query q = getEntityManager().createQuery(sql);
            q.setParameter("ids", ids);
            q.executeUpdate();
            logger.debug(String.format("hard delete %s records from %s", ids.size(), tblName));
        }

        @Transactional
        private void nativeSqlDelete(Collection ids) {
            // native sql can avoid JPA cascades a deletion to parent entity when deleting a child entity
            String sql = String.format("delete from %s where %s in (:ids)", voClass.getSimpleName(), voPrimaryKeyField.getName());
            Query q = getEntityManager().createNativeQuery(sql);
            q.setParameter("ids", ids);
            q.executeUpdate();
        }

        @Transactional(propagation = Propagation.REQUIRES_NEW)
        void remove(Object entity) {
            if (!hasEO()) {
                hardDelete(entity);
            } else {
                softDelete(entity);
            }
        }

        @Transactional(propagation = Propagation.REQUIRES_NEW)
        void removeByPrimaryKey(Object id) {
            if (hasEO()) {
                softDelete(list(id));
            } else {
                hardDelete(list(id));
            }
        }

        @Transactional(propagation = Propagation.REQUIRES_NEW)
        void removeByPrimaryKeys(Collection ids) {
            if (hasEO()) {
                softDelete(ids);
            } else {
                hardDelete(ids);
            }
        }

        @Transactional(propagation = Propagation.REQUIRES_NEW)
        void removeCollection(Collection entities) {
            for (Object entity : entities) {
                if (!entity.getClass().isAnnotationPresent(EO.class)) {
                    hardDelete(entity);
                } else {
                    softDelete(entity);
                }
            }
        }

        @Transactional(propagation = Propagation.REQUIRES_NEW)
        Object reload(Object entity) {
            return getEntityManager().find(entity.getClass(), getVOPrimaryKeyValue(entity));
        }

        @Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
        List listByPrimaryKeys(Collection ids, int offset, int length) {
            String sql = null;
            Query query = null;
            if (ids == null || ids.isEmpty()) {
                sql = String.format("select e from %s e", voClass.getSimpleName());
                query = getEntityManager().createQuery(sql, voClass);
            } else {
                sql = String.format("select e from %s e where e.%s in (:ids)", voClass.getSimpleName(), voPrimaryKeyField.getName());
                query = getEntityManager().createQuery(sql, voClass);
                query.setParameter("ids", ids);
            }
            query.setFirstResult(offset);
            query.setMaxResults(length);
            return query.getResultList();
        }

        @Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
        boolean isExist(Object id) {
            String sql = String.format("select count(*) from %s ref where ref.%s = :id", voClass.getSimpleName(), voPrimaryKeyField.getName());
            TypedQuery<Long> q = getEntityManager().createQuery(sql, Long.class);
            q.setParameter("id", id);
            q.setMaxResults(1);
            Long count = q.getSingleResult();
            return count > 0;
        }
    }

    @Transactional(readOnly = true)
    private void getDbVersionOnInit() {
//        String sql = "select version from schema_version order by version desc limit 1";
//        Query q = getEntityManager().createNativeQuery(sql);
//        dbVersion = (String) q.getSingleResult();
        dbVersion = "1.0";
    }

    void init() {
        buildEntityInfo();
        getDbVersionOnInit();
    }

    @Override
    public <T> T persist(T entity) {
        return persist(entity, false);
    }

    EntityInfo getEntityInfo(Class clz) {
        EntityInfo info = entityInfoMap.get(clz);
        DebugUtils.Assert(info != null, String.format("cannot find entity info for %s", clz.getName()));
        return info;
    }

    @Override
    public <T> void update(T entity) {
        getEntityInfo(entity.getClass()).update(entity);
    }

    @Override
    public CriteriaBuilder getCriteriaBuilder() {
        return entityManagerFactory.getCriteriaBuilder();
    }

    @Override
    public <T> SimpleQuery<T> createQuery(Class<T> entityClass) {
        assert entityClass.isAnnotationPresent(Entity.class) : entityClass.getName() + " is not annotated by JPA @Entity";
        return new SimpleQueryImpl<T>(entityClass);
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
    public <T> T findById(long id, Class<T> entityClass) {
        return getEntityManager().find(entityClass, id);
    }

    @Override
    @DeadlockAutoRestart
    public void remove(Object entity) {
        getEntityInfo(entity.getClass()).remove(entity);
    }

    @Override
    @DeadlockAutoRestart
    public void removeCollection(Collection entities, Class entityClass) {
        if (entities.isEmpty()) {
            return;
        }

        getEntityInfo(entityClass).removeCollection(entities);
    }

    @Override
    @DeadlockAutoRestart
    public void removeByPrimaryKeys(Collection priKeys, Class entityClazz) {
        if (priKeys.isEmpty()) {
            return;
        }
        getEntityInfo(entityClazz).removeByPrimaryKeys(priKeys);
    }


    @Override
    public <T> T updateAndRefresh(T entity) {
        return (T) getEntityInfo(entity.getClass()).updateAndRefresh(entity);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
    public <T> T findByUuid(String uuid, Class<T> entityClass) {
        return this.getEntityManager().find(entityClass, uuid);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
    public <T> T find(Query q) {
        List<T> ret = q.getResultList();
        if (ret.size() > 1) {
            throw new ServiceException("more than one result found");
        }
        return ret.isEmpty() ? null : ret.get(0);
    }

    @Override
    @DeadlockAutoRestart
    public void removeByPrimaryKey(Object primaryKey, Class<?> entityClass) {
        getEntityInfo(entityClass).removeByPrimaryKey(primaryKey);
    }

    @Override
    @Transactional
    public void hardDeleteCollectionSelectedBySQL(String sql, Class entityClass) {
        EntityInfo info = getEntityInfo(entityClass);
        Query q = getEntityManager().createQuery(sql);
        List ids = q.getResultList();
        if (ids.isEmpty()) {
            return;
        }

        info.hardDelete(ids);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    private <T> T doPersist(T entity, boolean isRefresh) {
        getEntityManager().persist(entity);

        if (isRefresh) {
            getEntityManager().flush();
            getEntityManager().refresh(entity);
        }
        return entity;
    }

    @DeadlockAutoRestart
    private <T> T persist(T entity, boolean isRefresh) {
        return doPersist(entity, isRefresh);
    }

    @Override
    public <T> T persistAndRefresh(T entity) {
        return persist(entity, true);
    }

    @Override
    public long count(Class<?> entityClass) {
        SimpleQuery<?> query = this.createQuery(entityClass);
        return query.count();
    }

    public void setDataSource(DruidDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public <T> T reload(T entity) {
        return (T) getEntityInfo(entity.getClass()).reload(entity);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    private void doUpdateCollection(Collection entities) {
        for (Object e : entities) {
            getEntityManager().merge(e);
        }
    }

    @Override
    @DeadlockAutoRestart
    public void updateCollection(Collection entities) {
        doUpdateCollection(entities);
    }

    @Override
    public long generateSequenceNumber(Class<?> seqTable) {
        try {
            Field id = seqTable.getDeclaredField("id");
            if (id == null) {
                throw new ServiceException(String.format("sequence VO[%s] must have 'id' field", seqTable.getName()));
            }
            Object vo = seqTable.newInstance();
            vo = persistAndRefresh(vo);
            id.setAccessible(true);
            return (Long) id.get(vo);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }


    @Override
    public <T> List<T> listAll(Class<T> clazz) {
        return listAll(0, Integer.MAX_VALUE, clazz);
    }

    @Override
    public <T> List<T> listAll(int offset, int length, Class<T> clazz) {
        return getEntityInfo(clazz).listByPrimaryKeys(null, offset, length);
    }

    @Override
    public <T> List<T> listByPrimaryKeys(Collection ids, Class<T> clazz) {
        return listByPrimaryKeys(ids, 0, Integer.MAX_VALUE, clazz);
    }

    @Override
    public <T> List<T> listByPrimaryKeys(Collection ids, int offset, int length, Class<T> clazz) {
        if (ids == null || ids.isEmpty()) {
            return Collections.EMPTY_LIST;
        } else {
            return getEntityInfo(clazz).listByPrimaryKeys(ids, offset, length);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void persistCollection(Collection entities) {
        for (Object e : entities) {
            this.getEntityManager().persist(e);
        }
    }

    @Override
    public boolean isExist(Object id, Class<?> clazz) {
        return getEntityInfo(clazz).isExist(id);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    private void _eoCleanup(Class VOClazz) {
        EntityInfo info = getEntityInfo(VOClazz);
        if (!info.hasEO()) {
            logger.warn(String.format("Class[%s] doesn't has EO.", VOClazz));
            return;
        }

        String deleted = info.eoSoftDeleteColumn.getName();
        String sql = String.format("select eo.%s from %s eo where eo.%s is not null", info.voPrimaryKeyField.getName(),
                info.eoClass.getSimpleName(), deleted);
        Query q = getEntityManager().createQuery(sql);
        List ids = q.getResultList();
        if (ids.isEmpty()) {
            return;
        }

        info.hardDelete(ids);

    }

    @Override
    @DeadlockAutoRestart
    public void eoCleanup(Class VOClazz) {
        _eoCleanup(VOClazz);
    }

    @Override
    public DataSource getDataSource() {
        return dataSource;
    }

    public void setExtraDataSource(DataSource extraDataSource) {
        this.extraDataSource = extraDataSource;
    }

    @Override
    public DataSource getExtraDataSource() {
        return extraDataSource;
    }

    private void buildEntityInfo() {
        String[] pkgs = StringUtils.split(DbGlobalProperty.ENTITY_PACKAGES, ",");
        List<Class> clzs = BeanUtils.scanClass(Arrays.asList(pkgs), Entity.class);
        for (Class clz : clzs) {
            logger.debug(String.format("build entity info for %s", clz.getName()));
            entityInfoMap.put(clz, new EntityInfo(clz));
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Timestamp getCurrentSqlTime() {
        Query query = getEntityManager().createNativeQuery("select current_timestamp()");
        return (Timestamp) query.getSingleResult();
    }

    @Override
    public String getDbVersion() {
        return dbVersion;
    }

}
