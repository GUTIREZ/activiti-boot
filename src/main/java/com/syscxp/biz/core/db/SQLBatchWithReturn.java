package com.syscxp.biz.core.db;

import com.syscxp.biz.config.Spring.SpringContext;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.transaction.annotation.Transactional;

@Configurable(preConstruction = true, autowire = Autowire.BY_TYPE)
public abstract class SQLBatchWithReturn<T> {
    protected DatabaseFacade dbf;

    public SQLBatchWithReturn() {
        this.dbf = SpringContext.getBean(DatabaseFacadeImpl.class);
    }

    protected abstract T scripts();

    protected SQL sql(String text) {
        return SQL.New(text);
    }

    protected UpdateQuery sql(Class clz) {
        return SQL.New(clz);
    }

    protected SQL sql(String text, Class clz) {
        return SQL.New(text, clz);
    }

    protected Q q(Class clz) {
        return Q.New(clz);
    }

    protected <K> K persist(K k) {
        dbf.getEntityManager().persist(k);
        return k;
    }

    protected <K> K merge(K k) {
        dbf.getEntityManager().merge(k);
        return k;
    }

    protected void remove(Object k) {
        dbf.getEntityManager().remove(k);
    }

    protected void flush() {
        dbf.getEntityManager().flush();
    }

    protected <K> K findByUuid(String uuid, Class<K> clz) {
        return dbf.getEntityManager().find(clz, uuid);
    }

    protected <K> K reload(K k) {
        flush();
        dbf.getEntityManager().refresh(k);
        return k;
    }

    @Transactional
    private T _execute() {
        return scripts();
    }

    @DeadlockAutoRestart
    public T execute() {
        return _execute();
    }
}
