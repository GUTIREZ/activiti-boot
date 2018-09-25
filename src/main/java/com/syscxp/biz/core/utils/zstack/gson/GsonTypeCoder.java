package com.syscxp.biz.core.utils.zstack.gson;

import com.google.gson.JsonDeserializer;
import com.google.gson.JsonSerializer;

public interface GsonTypeCoder<T> extends JsonDeserializer<T>, JsonSerializer<T> {

}
