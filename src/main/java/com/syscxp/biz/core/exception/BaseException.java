package com.syscxp.biz.core.exception;


import com.syscxp.biz.core.base.BaseError;

/**
 * @Author: sunxuelong.
 * @Cretion Date: 2018-05-10.
 * @Description: .
 */
public class BaseException extends RuntimeException {

    private BaseError baseError;
    private String message;

    public BaseException() {
        super();
    }

    public BaseException(BaseError baseError) {
        super(baseError.error());
        this.baseError = baseError;
        this.message = baseError.error();
    }

    public BaseException(BaseError baseError,Throwable e) {
        super(baseError.error(),e);
        this.baseError = baseError;
        this.message = baseError.error();
    }

    public BaseException(String message) {
        super(message);
        this.message = message;
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseError getBaseError() {
        return baseError;
    }

    public void setBaseError(BaseError baseError) {
        this.baseError = baseError;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

