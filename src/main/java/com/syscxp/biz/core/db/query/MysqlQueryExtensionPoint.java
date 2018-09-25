package com.syscxp.biz.core.db.query;


import com.syscxp.biz.core.db.query.header.QueryMessage;

import java.util.List;

public interface MysqlQueryExtensionPoint {
    <T> List<T> query(QueryMessage msg, Class<T> inventoryClass);

    long count(QueryMessage msg, Class inventoryClass);
    
    List<Class<?>> getSupportQueryMessageClasses();
}
