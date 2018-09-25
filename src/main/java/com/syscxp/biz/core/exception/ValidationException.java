package com.syscxp.biz.core.exception;

import com.syscxp.biz.core.base.BaseError;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;

import java.util.List;

/***
 * @author sunxuelong
 * @Cretion Date: 2018-06-25.
 * @Description: 数据验证异常.
 */
@Component
public class ValidationException extends BaseException {
    private List<FieldError> fieldErrors;

    public ValidationException() {
    }

    public ValidationException(BaseError baseError) {
        super(baseError);
    }

    public ValidationException(BaseError baseError,List<FieldError> fieldErrors) {
        super(baseError);
        this.fieldErrors = fieldErrors;
    }

    public ValidationException(List<FieldError> fieldErrors) {
        //super();
        this.fieldErrors = fieldErrors;
    }

    public ValidationException(BaseError baseError, Throwable e) {
        super(baseError, e);
    }

    public ValidationException(String message) {
        super(message);
    }

    public ValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public List<FieldError> getFieldErrors() {
        return fieldErrors;
    }

    public void setFieldErrors(List<FieldError> fieldErrors) {
        this.fieldErrors = fieldErrors;
    }
}
