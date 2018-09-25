package com.syscxp.biz.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 */
@Target(value = {ElementType.FIELD})
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
public @interface GlobalProperty {
    final static String DEFAULT_NULL_STRING = "";

    String name();
    String defaultValue() default DEFAULT_NULL_STRING;
    boolean required() default false;
}