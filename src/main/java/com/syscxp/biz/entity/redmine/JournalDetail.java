package com.syscxp.biz.entity.redmine;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Author: sunxuelong.
 * @Cretion Date: 2018-09-17.
 * @Description: .
 */
@Entity
@Table(name = "journal_details")
public class JournalDetail implements Serializable {

    private static final long serialVersionUID = -1779058354302360502L;
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "journal_id")
    private Long journalId;

    @Column(name = "property")
    private String property;

    @Column(name = "prop_key")
    private String propKey;

    @Column(name = "old_value")
    private String oldValue;

    @Column(name = "value")
    private String value;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getJournalId() {
        return journalId;
    }

    public void setJournalId(Long journalId) {
        this.journalId = journalId;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getPropKey() {
        return propKey;
    }

    public void setPropKey(String propKey) {
        this.propKey = propKey;
    }

    public String getOldValue() {
        return oldValue;
    }

    public void setOldValue(String oldValue) {
        this.oldValue = oldValue;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}


