package com.syscxp.biz.core.base;


import com.syscxp.biz.core.db.query.QueryFacade;
import com.syscxp.biz.core.db.query.header.QueryMessage;
import com.syscxp.biz.core.db.query.header.QueryReply;
import org.springframework.stereotype.Service;

/**
 * @Author: sunxuelong.
 * @Cretion Date: 2018-06-28.
 * @Description: .
 */
@Service
public class BaseService {
    private final QueryFacade query;

    public BaseService(QueryFacade query) {
        this.query = query;
    }

    QueryReply search(QueryMessage msg, Class inventoryClass) {
        return query.handle(msg, inventoryClass);
    }
}
