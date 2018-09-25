package com.syscxp.biz.core.db.query.header;


/**
 */
public class APIGenerateQueryableFieldsEvent {
    private String outputFolder;


    public APIGenerateQueryableFieldsEvent() {
    }

    public String getOutputFolder() {
        return outputFolder;
    }

    public void setOutputFolder(String outputFolder) {
        this.outputFolder = outputFolder;
    }

    public static APIGenerateQueryableFieldsEvent __example__() {
        APIGenerateQueryableFieldsEvent event = new APIGenerateQueryableFieldsEvent();


        return event;
    }

}
