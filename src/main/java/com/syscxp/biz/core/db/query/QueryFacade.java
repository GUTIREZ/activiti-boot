package com.syscxp.biz.core.db.query;

import com.syscxp.biz.core.db.query.header.QueryMessage;
import com.syscxp.biz.core.db.query.header.QueryReply;

import java.util.List;

public interface QueryFacade {
    <T> List<T> query(QueryMessage msg, Class<T> inventoryClass);

    long count(QueryMessage msg, Class inventoryClass);

    QueryReply handle(QueryMessage msg, Class inventoryClass);
}
