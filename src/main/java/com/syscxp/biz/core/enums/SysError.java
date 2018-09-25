package com.syscxp.biz.core.enums;


import com.syscxp.biz.core.base.BaseError;

/**
 * @Author: sunxuelong.
 * @Cretion Date: 2018-05-11.
 * @Description: 系统默认错误.
 */
public enum  SysError implements BaseError {
    FAIL{
        @Override
        public String code() {
            return "SYS.10001";
        }

        @Override
        public String error() {
            return "UNHANDLED EXCEPTION";
        }
    },
    INVALID_PARAMETER{
        @Override
        public String code() {
            return "SYS.10002";
        }

        @Override
        public String error() {
            return "INVALID_PARAMETER";
        }
    },
}
