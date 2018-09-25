package com.syscxp.biz.core.exception;


import com.syscxp.biz.core.base.BaseError;

/**
 * @Author: sunxuelong.
 * @Cretion Date: 2018-05-10.
 * @Description: .
 */
public class AuthenticationException extends BaseException {
    public AuthenticationException(BaseError baseError) {
        super(baseError);
    }

    public AuthenticationException(BaseError baseError, Throwable e) {
        super(baseError, e);
    }

    public AuthenticationException(String message) {
        super(message);
    }

    public AuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }
}
