package com.syscxp.biz.config.global;

public interface GlobalConfigValidatorExtensionPoint {
    void validateGlobalConfig(String category, String name, String oldValue, String newValue) throws GlobalConfigException;
}
