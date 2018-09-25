package com.syscxp.biz.entity.redmine;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;

/**
 * @Author: sunxuelong.
 * @Cretion Date: 2018-09-17.
 * @Description: .
 */
@Entity
@Table(name = "journals")
public class Journal implements Serializable{

    private static final long serialVersionUID = -8311383753028191567L;

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "journalized_id")
    private Long journalizedId;

    @Column(name = "journalized_type")
    private String journalizedType;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "notes")
    private String notes;

    @Column(name = "created_on")
    private Timestamp createdOn;

    @Column(name = "private_notes")
    private Boolean privateNotes;

    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinColumn(name = "journal_id")
    private Collection<JournalDetail> journalDetails = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getJournalizedId() {
        return journalizedId;
    }

    public void setJournalizedId(Long journalizedId) {
        this.journalizedId = journalizedId;
    }

    public String getJournalizedType() {
        return journalizedType;
    }

    public void setJournalizedType(String journalizedType) {
        this.journalizedType = journalizedType;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Timestamp getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Timestamp createdOn) {
        this.createdOn = createdOn;
    }

    public Boolean getPrivateNotes() {
        return privateNotes;
    }

    public void setPrivateNotes(Boolean privateNotes) {
        this.privateNotes = privateNotes;
    }

    public Collection<JournalDetail> getJournalDetails() {
        return journalDetails;
    }

    public void setJournalDetails(Collection<JournalDetail> journalDetails) {
        this.journalDetails = journalDetails;
    }
}


