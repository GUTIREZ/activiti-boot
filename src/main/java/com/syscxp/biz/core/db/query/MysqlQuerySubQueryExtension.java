package com.syscxp.biz.core.db.query;


import com.syscxp.biz.core.db.query.header.QueryMessage;

import java.util.List;

/**
 */
public interface MysqlQuerySubQueryExtension {
    String makeSubquery(QueryMessage msg, Class inventoryClass);

    List<String> getEscapeConditionNames();
}
