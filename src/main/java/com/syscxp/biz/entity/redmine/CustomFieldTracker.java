package com.syscxp.biz.entity.redmine;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @Author: sunxuelong.
 * @Cretion Date: 2018-09-17.
 * @Description: .
 */
@Entity(name = "custom_fields_trackers")
public class CustomFieldTracker {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "custom_field_id")
    private Long customFieldId;

    @Column(name = "tracker_id")
    private Long trackerId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomFieldId() {
        return customFieldId;
    }

    public void setCustomFieldId(Long customFieldId) {
        this.customFieldId = customFieldId;
    }

    public Long getTrackerId() {
        return trackerId;
    }

    public void setTrackerId(Long trackerId) {
        this.trackerId = trackerId;
    }
}


