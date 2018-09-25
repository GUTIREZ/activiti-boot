package com.syscxp.biz.core.base;

import com.syscxp.biz.core.enums.SysError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 * @author : sunxuelong
 * Date: 2018/05/10 22:08
 * Description: json返回生成
 */
@Component
public class RespEntity {
    private static final String SUCCESS_CODE = "0";
    private static final String SUCCESS_MESSAGE = "SUCCESS";

    public static ResponseEntity success() {
        return ResponseEntity.status(HttpStatus.OK).body(new Result()
                .setCode(SUCCESS_CODE)
                .setMessage(SUCCESS_MESSAGE)
                .setError(null));
    }

    public static ResponseEntity<Result> success(String message) {
        return ResponseEntity.status(HttpStatus.OK).body(new Result()
                .setCode(SUCCESS_CODE)
                .setMessage(message)
                .setError(null));
    }

    public static ResponseEntity<Result> success(Object data) {
        return ResponseEntity.status(HttpStatus.OK).body(new Result()
                .setCode(SUCCESS_CODE)
                .setMessage(SUCCESS_MESSAGE)
                .setData(data));
    }

    public static ResponseEntity<Result> success(String message, Object data) {
        return ResponseEntity.status(HttpStatus.OK).body(new Result()
                .setCode(SUCCESS_CODE)
                .setMessage(message)
                .setData(data)
                .setError(null));
    }

    public static ResponseEntity<String> httpFail(HttpStatus httpStatus) {
        return ResponseEntity.status(httpStatus).body(httpStatus.getReasonPhrase());
    }

    public static ResponseEntity<Result> fail(String errorMessage) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Result()
                .setCode(SysError.FAIL.toString())
                .setMessage(SysError.FAIL.toString())
                .setError(errorMessage)
                .setData(null));
    }

    public static ResponseEntity<Result> fail(BaseError error, String errorMessage) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new Result()
                        .setCode(error)
                        .setMessage(error.error())
                        .setError(errorMessage)
                        .setData(null));
    }

    public static ResponseEntity<Result> fail(BaseError error) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Result()
                .setCode(error)
                .setError(error.error()));
    }
}