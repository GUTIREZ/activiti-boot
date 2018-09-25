package com.syscxp.biz.core.utils.zstack.function;

/**
*/
public interface Function<K, V> {
    K call(V arg);
}
