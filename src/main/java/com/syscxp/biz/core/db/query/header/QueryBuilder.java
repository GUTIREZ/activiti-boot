package com.syscxp.biz.core.db.query.header;

import java.util.List;
import java.util.Map;

public interface QueryBuilder {
    <T> List<T> query(QueryMessage msg, Class<T> inventoryClass);

    long count(QueryMessage msg, Class inventoryClass);

    Map<String, List<String>> populateQueryableFields();
}
