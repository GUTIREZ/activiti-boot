package com.syscxp.biz.core.db.search.header;

import java.util.List;


public interface SearchIndexRecreateExtensionPoint {
    List<String> returnUuidToReindex(String inventoryName);
}
