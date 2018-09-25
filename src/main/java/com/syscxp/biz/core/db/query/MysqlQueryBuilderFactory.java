package com.syscxp.biz.core.db.query;

import com.syscxp.biz.core.db.query.header.QueryBuilder;
import com.syscxp.biz.core.db.query.header.QueryBuilderFactory;
import com.syscxp.biz.core.db.query.header.QueryBuilderType;
import org.springframework.beans.factory.annotation.Autowired;

public class MysqlQueryBuilderFactory implements QueryBuilderFactory {
    public static QueryBuilderType type = new QueryBuilderType("Mysql");
    
    @Autowired
    private MysqlQueryBuilderImpl builder;
    
    @Override
    public QueryBuilderType getQueryBuilderType() {
        return type;
    }

    @Override
    public QueryBuilder createQueryBuilder() {
        return builder;
    }

}
