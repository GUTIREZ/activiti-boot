package com.syscxp.biz.core.db.search.header;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Inventory {
    Class<?> mappingVOClass();

    String collectionValueOfMethod() default "";

    Parent[] parent() default {};
}
