package com.syscxp.biz.core.db.query.header;

import java.util.List;

/**
 */
public interface AddExpandedQueryExtensionPoint {
    List<ExpandedQueryStruct> getExpandedQueryStructs();

    List<ExpandedQueryAliasStruct> getExpandedQueryAliasesStructs();
}
