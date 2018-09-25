package com.syscxp.biz.core.db.query;


import com.syscxp.biz.core.db.search.header.Inventory;
import com.syscxp.biz.core.utils.zstack.db.FieldUtils;

import javax.persistence.Id;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 */
public class QueryUtils {
    private static Map<Class, String> primaryKeys = new HashMap<Class, String>();

    public static Class getEntityClassFromInventoryClass(Class invClz) {
        Inventory at = (Inventory) invClz.getAnnotation(Inventory.class);
        return at.mappingVOClass();
    }

    public static String getPrimaryKeyNameFromEntityClass(Class entityClass) {
        String priKey = primaryKeys.get(entityClass);
        if (priKey == null) {
            Field f = FieldUtils.getAnnotatedField(Id.class, entityClass);
            priKey = f.getName();
            primaryKeys.put(entityClass, priKey);
        }
        return priKey;
    }

    public static String getPrimaryKeyNameFromInventoryClass(Class invClz) {
        Class entityClass = getEntityClassFromInventoryClass(invClz);
        return getPrimaryKeyNameFromEntityClass(entityClass);
    }
}
