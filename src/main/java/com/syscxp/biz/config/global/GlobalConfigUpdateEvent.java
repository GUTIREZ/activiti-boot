package com.syscxp.biz.config.global;


public class GlobalConfigUpdateEvent {
    private GlobalConfig oldConfig;
    private GlobalConfig newConfig;

    public String getSubCategory() {
        return "GlobalConfig";
    }

    public GlobalConfig getOldConfig() {
        return oldConfig;
    }

    public void setOldConfig(GlobalConfig oldConfig) {
        this.oldConfig = oldConfig;
    }

    public GlobalConfig getNewConfig() {
        return newConfig;
    }

    public void setNewConfig(GlobalConfig newConfig) {
        this.newConfig = newConfig;
    }
}
