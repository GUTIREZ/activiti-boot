package com.syscxp.biz.core.base;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Component;

/**
 * @author : sunxuelong
 * Date: 2018/05/10 22:08
 * Description: 统一API响应结果
 */
@Component
public class Result {
    private String code;
    private String message;
    private String error;
    private Object data;

    public Result() {
    }

    public Result(BaseError error, String message) {
        this.code = error.code();
        this.error = error.error();
        this.message = message;
    }

    public Result(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public Result setCode(BaseError error) {
        this.code = error.code();
        return this;
    }

    public Result setCode(String code) {
        this.code = code;
        return this;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Result setMessage(String message) {
        this.message = message;
        return this;
    }

    public Object getData() {
        return data;
    }

    public Result setData(Object data) {
        this.data = data;
        return this;
    }

    public String getError() {
        return error;
    }

    public Result setError(String error) {
        this.error = error;
        return this;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
