package com.syscxp.biz.core.db.search.header;

/**
 */
@java.lang.annotation.Target({java.lang.annotation.ElementType.TYPE})
@java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
public @interface Parent {
    Class<?> inventoryClass();

    String type();
}
