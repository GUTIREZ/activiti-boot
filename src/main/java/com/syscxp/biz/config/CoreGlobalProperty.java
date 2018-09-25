package com.syscxp.biz.config;

/**
 */
@GlobalPropertyDefinition
public class CoreGlobalProperty {
    @GlobalProperty(name = "openTSDBServerUrl", defaultValue = "http://192.168.211.6:4242")
    public static String OPENTSDB_SERVER_URL;
}