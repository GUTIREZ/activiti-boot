package com.syscxp.biz.core.db;


import com.syscxp.biz.config.GlobalProperty;
import com.syscxp.biz.config.GlobalPropertyDefinition;


@GlobalPropertyDefinition
public class DbGlobalProperty {
    @GlobalProperty(name="entityPackages", defaultValue = "com.syscxp")
    public static String ENTITY_PACKAGES;
}
