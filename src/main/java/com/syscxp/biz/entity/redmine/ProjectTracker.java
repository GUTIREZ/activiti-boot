package com.syscxp.biz.entity.redmine;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @Author: sunxuelong.
 * @Cretion Date: 2018-09-17.
 * @Description: .
 */
@Entity(name = "projects")
public class ProjectTracker {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "project_id")
    private Long projectId;

    @Column(name = "tracker_id")
    private Long trackerId;

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getTrackerId() {
        return trackerId;
    }

    public void setTrackerId(Long trackerId) {
        this.trackerId = trackerId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}


