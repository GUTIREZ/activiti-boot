package com.syscxp.biz.core.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @Author: sunxuelong.
 * @Cretion Date: 2018-05-10.
 * @Description: 用户名已存在.
 */
public class UsernameIsExitedException extends AuthenticationException {

    public UsernameIsExitedException(String msg) {
        super(msg);
    }

    public UsernameIsExitedException(String msg, Throwable t) {
        super(msg, t);
    }
}