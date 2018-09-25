package com.syscxp.biz.core.db.query.header;


import java.util.List;

public class APIGenerateInventoryQueryDetailsMsg {
    private String outputDir;
    private List<String> basePackageNames;

    public String getOutputDir() {
        return outputDir;
    }

    public void setOutputDir(String outputDir) {
        this.outputDir = outputDir;
    }

    public List<String> getBasePackageNames() {
        return basePackageNames;
    }

    public void setBasePackageNames(List<String> basePackageNames) {
        this.basePackageNames = basePackageNames;
    }

    public static APIGenerateInventoryQueryDetailsMsg __example__() {
        APIGenerateInventoryQueryDetailsMsg msg = new APIGenerateInventoryQueryDetailsMsg();


        return msg;
    }

}
