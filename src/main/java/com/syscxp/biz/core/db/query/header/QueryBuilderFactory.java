package com.syscxp.biz.core.db.query.header;

public interface QueryBuilderFactory {
    QueryBuilderType getQueryBuilderType();

    QueryBuilder createQueryBuilder();
}
