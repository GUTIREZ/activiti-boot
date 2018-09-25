package com.syscxp.biz.core.utils.zstack.function;

import java.util.List;

/**
*/
public interface ListFunction<K, V> {
    List<K> call(V arg);
}
