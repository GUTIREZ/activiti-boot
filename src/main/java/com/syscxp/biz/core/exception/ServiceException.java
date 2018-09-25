package com.syscxp.biz.core.exception;

import com.syscxp.biz.core.base.BaseError;
import org.springframework.stereotype.Component;

/***
 * @author sunxuelong
 * @Cretion Date: 2018-05-12.
 * @Description: 服务类异常.
 */
@Component
public class ServiceException extends BaseException {
    public ServiceException() {
    }

    public ServiceException(BaseError baseError) {
        super(baseError);
    }

    public ServiceException(BaseError baseError, Throwable e) {
        super(baseError, e);
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
