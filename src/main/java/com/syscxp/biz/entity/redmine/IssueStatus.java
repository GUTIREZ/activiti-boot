package com.syscxp.biz.entity.redmine;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @Author: sunxuelong.
 * @Cretion Date: 2018-09-17.
 * @Description: .
 */
@Entity(name = "issue_statuses")
public class IssueStatus {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "position")
    private Long position;

    @Column(name = "default_done_ratio")
    private Long defaultDoneRatio;

    @Column(name = "next_role")
    private String nextRole;

    @Column(name = "tracker_id")
    private String trackerId;

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

    public Long getPosition() {
        return position;
    }

    public void setPosition(Long position) {
        this.position = position;
    }

    public Long getDefaultDoneRatio() {
        return defaultDoneRatio;
    }

    public void setDefaultDoneRatio(Long defaultDoneRatio) {
        this.defaultDoneRatio = defaultDoneRatio;
    }

    public String getNextRole() {
        return nextRole;
    }

    public void setNextRole(String nextRole) {
        this.nextRole = nextRole;
    }

    public String getTrackerId() {
        return trackerId;
    }

    public void setTrackerId(String trackerId) {
        this.trackerId = trackerId;
    }
}


