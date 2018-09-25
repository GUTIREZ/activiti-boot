package com.syscxp.biz.core.enums.Authehtication;


import com.syscxp.biz.core.base.BaseError;

/**
 * @Author: sunxuelong.
 * @Cretion Date: 2018-05-11.
 * @Description: 认证错误.
 */
public enum AuthError implements BaseError {
    INVALID_USER {
        @Override
        public String code() {
            return "AUTH.10001";
        }

        @Override
        public String error() {
            return "INVALID_USER";
        }
    },
    BAD_CREDENTIALS {
        @Override
        public String code() {
            return "AUTH.10002";
        }

        @Override
        public String error() {
            return "BAD_CREDENTIALS";
        }
    },
    TOKEN_EXPIRED {
        @Override
        public String code() {
            return "AUTH.10003";
        }

        @Override
        public String error() {
            return "TOKEN_EXPIRED";
        }
    },
    REFRESH_TOKEN_EXPIRED {
        @Override
        public String code() {
            return "AUTH.10004";
        }

        @Override
        public String error() {
            return "REFRESH_TOKEN_EXPIRED";
        }
    },
    NO_USER {
        @Override
        public String code() {
            return "AUTH.10005";
        }

        @Override
        public String error() {
            return "NO_USER";
        }
    },
    ILLEGAL_TOKEN {
        @Override
        public String code() {
            return "AUTH.10005";
        }

        @Override
        public String error() {
            return "ILLEGAL_TOKEN";
        }
    },
}
