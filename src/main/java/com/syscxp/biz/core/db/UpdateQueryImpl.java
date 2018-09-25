package com.syscxp.biz.core.db;

import com.syscxp.biz.config.Spring.SpringContext;
import com.syscxp.biz.core.exception.ServiceException;
import com.syscxp.biz.core.utils.zstack.DebugUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import javax.persistence.metamodel.SingularAttribute;
import java.util.*;

public class UpdateQueryImpl implements UpdateQuery {
    private static final Logger logger = LoggerFactory.getLogger(UpdateQueryImpl.class);

    private DatabaseFacadeImpl dbf;

    private Class entityClass;
    private Map<SingularAttribute, Object> setValues = new HashMap<>();
    private Map<SingularAttribute, List<Cond>> andConditions = new HashMap<>();

    private class Cond {
        SingularAttribute attr;
        SimpleQuery.Op op;
        Object val;
    }

    UpdateQuery entity(Class clazz) {
        dbf = SpringContext.getBean(DatabaseFacadeImpl.class);
        entityClass = clazz;
        return this;
    }

    @Override
    public UpdateQuery set(SingularAttribute attr, Object val) {
        if (setValues.containsKey(attr)) {
            throw new ServiceException(String.format("unable to set a column[%s] twice", attr.getName()));
        }

        setValues.put(attr, val);
        return this;
    }

    @Override
    public UpdateQuery condAnd(SingularAttribute attr, SimpleQuery.Op op, Object val) {
        if ((op == SimpleQuery.Op.IN || op == SimpleQuery.Op.NOT_IN) && !(val instanceof Collection)) {
            throw new ServiceException(String.format("for operation IN or NOT IN, a Collection value is expected, but %s got", val.getClass()));
        }

        Cond cond = new Cond();
        cond.attr = attr;
        cond.op = op;
        cond.val = val;

        List<Cond> conds = andConditions.get(attr);
        if (conds == null) {
            conds = new ArrayList<>();
            andConditions.put(attr, conds);
        }
        conds.add(cond);
        return this;
    }

    @Override
    public UpdateQuery eq(SingularAttribute attr, Object val) {
        condAnd(attr, SimpleQuery.Op.EQ, val);
        return this;
    }

    @Override
    public UpdateQuery notEq(SingularAttribute attr, Object val) {
        condAnd(attr, SimpleQuery.Op.NOT_EQ, val);
        return this;
    }

    @Override
    public UpdateQuery in(SingularAttribute attr, Collection val) {
        condAnd(attr, SimpleQuery.Op.IN, val);
        return this;
    }

    @Override
    public UpdateQuery notIn(SingularAttribute attr, Collection val) {
        condAnd(attr, SimpleQuery.Op.NOT_IN, val);
        return this;
    }

    @Override
    public UpdateQuery isNull(SingularAttribute attr) {
        condAnd(attr, SimpleQuery.Op.NULL, null);
        return this;
    }

    @Override
    public UpdateQuery notNull(SingularAttribute attr) {
        condAnd(attr, SimpleQuery.Op.NOT_NULL, null);
        return this;
    }

    @Override
    public UpdateQuery gt(SingularAttribute attr, Object val) {
        condAnd(attr, SimpleQuery.Op.GT, val);
        return this;
    }

    @Override
    public UpdateQuery gte(SingularAttribute attr, Object val) {
        condAnd(attr, SimpleQuery.Op.GTE, val);
        return this;
    }

    @Override
    public UpdateQuery lt(SingularAttribute attr, Object val) {
        condAnd(attr, SimpleQuery.Op.LT, val);
        return this;
    }

    @Override
    public UpdateQuery lte(SingularAttribute attr, Object val) {
        condAnd(attr, SimpleQuery.Op.LTE, val);
        return this;
    }

    @Override
    public UpdateQuery like(SingularAttribute attr, Object val) {
        condAnd(attr, SimpleQuery.Op.LIKE, val);
        return this;
    }

    @Override
    public UpdateQuery notLike(SingularAttribute attr, Object val) {
        condAnd(attr, SimpleQuery.Op.NOT_LIKE, val);
        return this;
    }

    private String where() {
        if (andConditions.isEmpty()) {
            return null;
        }

        List<String> condstrs = new ArrayList<>();
        for (List<Cond> conds : andConditions.values()) {
            for (int i = 0; i < conds.size(); i++) {
                Cond cond = conds.get(i);
                String condName = String.format("cond_%s_%s", cond.attr.getName(), i);
                if (SimpleQuery.Op.IN == cond.op || SimpleQuery.Op.NOT_IN == cond.op) {
                    condstrs.add(String.format("vo.%s %s (:%s)", cond.attr.getName(), cond.op.toString(), condName));
                } else if (SimpleQuery.Op.NULL == cond.op || SimpleQuery.Op.NOT_NULL == cond.op) {
                    condstrs.add(String.format("vo.%s %s", cond.attr.getName(), cond.op));
                } else {
                    condstrs.add(String.format("vo.%s %s :%s", cond.attr.getName(), cond.op.toString(), condName));
                }
            }
        }

        return StringUtils.join(condstrs, " AND ");
    }

    private void fillConditions(Query q) {
        for (List<Cond> conds : andConditions.values()) {
            for (int i = 0; i < conds.size(); i++) {
                Cond cond = conds.get(i);
                if (SimpleQuery.Op.NULL == cond.op || SimpleQuery.Op.NOT_NULL == cond.op) {
                    continue;
                }

                q.setParameter("cond_" + cond.attr.getName() + String.format("_%s", i), cond.val);
            }

        }
    }

    @Override
    @Transactional
    public int hardDelete() {
        DebugUtils.Assert(entityClass != null, "entity class cannot be null");

        StringBuilder sb = new StringBuilder(String.format("DELETE FROM %s vo", entityClass.getSimpleName()));

        String where = where();
        if (where != null) {
            sb.append(String.format(" WHERE %s", where));
        }

        String sql = sb.toString();
        if (logger.isTraceEnabled()) {
            logger.trace(sql);
        }

        Query q = dbf.getEntityManager().createQuery(sql);

        if (where != null) {
            fillConditions(q);
        }

        int ret = q.executeUpdate();
        dbf.getEntityManager().flush();
        return ret;
    }

    @Override
    @Transactional
    public void delete() {
        DebugUtils.Assert(entityClass != null, "entity class cannot be null");

        DatabaseFacadeImpl.EntityInfo info = dbf.getEntityInfo(entityClass);

        DebugUtils.Assert(entityClass != null, "entity class cannot be null");

        StringBuilder sb = new StringBuilder(String.format("SELECT vo.%s FROM %s vo", info.voPrimaryKeyField.getName(),
                entityClass.getSimpleName()));
        String where = where();
        if (where != null) {
            sb.append(String.format(" WHERE %s", where));
        }

        String sql = sb.toString();
        if (logger.isTraceEnabled()) {
            logger.trace(sql);
        }

        Query q = dbf.getEntityManager().createQuery(sql);

        if (where != null) {
            fillConditions(q);
        }

        List ids = q.getResultList();
        if (ids.isEmpty()) {
            return;
        }

        info.removeByPrimaryKeys(ids);
    }

    @Override
    @Transactional
    public void update() {
        DebugUtils.Assert(entityClass != null, "entity class cannot be null");

        StringBuilder sb = new StringBuilder(String.format("UPDATE %s vo", entityClass.getSimpleName()));
        List<String> setters = new ArrayList<>();
        for (Map.Entry<SingularAttribute, Object> e : setValues.entrySet()) {
            setters.add(String.format("vo.%s=:%s", e.getKey().getName(), e.getKey().getName()));
        }

        sb.append(String.format(" SET %s ", StringUtils.join(setters, ",")));

        String where = where();
        if (where != null) {
            sb.append(String.format(" WHERE %s", where));
        }

        String sql = sb.toString();
        if (logger.isTraceEnabled()) {
            logger.trace(sql);
        }

        Query q = dbf.getEntityManager().createQuery(sql);
        for (Map.Entry<SingularAttribute, Object> e : setValues.entrySet()) {
            q.setParameter(e.getKey().getName(), e.getValue());
        }

        if (where != null) {
            fillConditions(q);
        }

        q.executeUpdate();
        dbf.getEntityManager().flush();
    }
}