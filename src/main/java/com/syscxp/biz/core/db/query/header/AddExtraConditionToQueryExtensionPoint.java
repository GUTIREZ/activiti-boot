package com.syscxp.biz.core.db.query.header;

import java.util.List;

/**
 */
public interface AddExtraConditionToQueryExtensionPoint {
    List<Class> getMessageClassesForAddExtraConditionToQueryExtensionPoint();

    List<QueryCondition> getExtraQueryConditionForMessage(QueryMessage msg);
}
