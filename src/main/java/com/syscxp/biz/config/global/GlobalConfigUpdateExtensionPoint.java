package com.syscxp.biz.config.global;

public interface GlobalConfigUpdateExtensionPoint {
    void updateGlobalConfig(GlobalConfig oldConfig, GlobalConfig newConfig);
}
