package com.syscxp.biz.entity.redmine;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @Author: sunxuelong.
 * @Cretion Date: 2018-09-17.
 * @Description: .
 */
@Entity(name = "custom_fields_projects")
public class CustomFieldProject {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "custom_field_id")
    private Long customFieldId;

    @Column(name = "project_id")
    private Long projectId;

    public Long getCustomFieldId() {
        return customFieldId;
    }

    public void setCustomFieldId(Long customFieldId) {
        this.customFieldId = customFieldId;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}


