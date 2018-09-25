package com.syscxp.biz.entity.redmine;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @Author: sunxuelong.
 * @Cretion Date: 2018-09-17.
 * @Description: .
 */
@Entity(name = "trackers")
public class Tracker {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "is_in_chlog")
    private Boolean isInChlog;

    @Column(name = "position")
    private Long position;

    @Column(name = "is_in_roadmap")
    private Boolean isInRoadmap;

    @Column(name = "fields_bits")
    private Long fieldsbits;

    @Column(name = "default_status_id")
    private Long defaultStatusId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getInChlog() {
        return isInChlog;
    }

    public void setInChlog(Boolean inChlog) {
        isInChlog = inChlog;
    }

    public Long getPosition() {
        return position;
    }

    public void setPosition(Long position) {
        this.position = position;
    }

    public Boolean getInRoadmap() {
        return isInRoadmap;
    }

    public void setInRoadmap(Boolean inRoadmap) {
        isInRoadmap = inRoadmap;
    }

    public Long getFieldsbits() {
        return fieldsbits;
    }

    public void setFieldsbits(Long fieldsbits) {
        this.fieldsbits = fieldsbits;
    }

    public Long getDefaultStatusId() {
        return defaultStatusId;
    }

    public void setDefaultStatusId(Long defaultStatusId) {
        this.defaultStatusId = defaultStatusId;
    }
}


