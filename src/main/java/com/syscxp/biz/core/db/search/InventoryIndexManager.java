package com.syscxp.biz.core.db.search;

import org.apache.http.client.HttpClient;

public interface InventoryIndexManager {
    HttpClient getHttpClient();
    
    String getElasticSearchBaseUrl();
}
