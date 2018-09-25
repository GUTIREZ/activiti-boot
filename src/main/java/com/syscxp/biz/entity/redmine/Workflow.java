package com.syscxp.biz.entity.redmine;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @Author: sunxuelong.
 * @Cretion Date: 2018-09-17.
 * @Description: .
 */
@Entity(name = "workflows")
public class Workflow {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "tracker_id")
    private Long trackerId;

    @Column(name = "old_status_id")
    private Long oldStatusId;

    @Column(name = "new_status_id")
    private Long newStatusId;

    @Column(name = "role_id")
    private Long roleId;

    @Column(name = "assignee")
    private Boolean assignee;

    @Column(name = "author")
    private Boolean author;

    @Column(name = "type")
    private String type;

    @Column(name = "field_name")
    private String fieldName;

    @Column(name = "rule")
    private String rule;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTrackerId() {
        return trackerId;
    }

    public void setTrackerId(Long trackerId) {
        this.trackerId = trackerId;
    }

    public Long getOldStatusId() {
        return oldStatusId;
    }

    public void setOldStatusId(Long oldStatusId) {
        this.oldStatusId = oldStatusId;
    }

    public Long getNewStatusId() {
        return newStatusId;
    }

    public void setNewStatusId(Long newStatusId) {
        this.newStatusId = newStatusId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Boolean getAssignee() {
        return assignee;
    }

    public void setAssignee(Boolean assignee) {
        this.assignee = assignee;
    }

    public Boolean getAuthor() {
        return author;
    }

    public void setAuthor(Boolean author) {
        this.author = author;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }
}


