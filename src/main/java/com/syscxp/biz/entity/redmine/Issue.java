package com.syscxp.biz.entity.redmine;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @Author: sunxuelong.
 * @Cretion Date: 2018-09-17.
 * @Description: .
 */
@Entity(name = "issues")
public class Issue {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "tracker_id")
    private String trackerId;

    @Column(name = "project_id")
    private Integer projectId;

    @Column(name = "subject")
    private String subject;

    @Column(name = "description")
    private String description;

    @Column(name = "due_date")
    private Date dueDate;

    @Column(name = "category_id")
    private Integer categoryId;

    @Column(name = "status_id")
    private Integer statusId;

    @Column(name = "assigned_to_id")
    private Integer assignedToId;

    @Column(name = "priority_id")
    private Integer priorityId;

    @Column(name = "fixed_version_id")
    private Integer fixedVersionId;

    @Column(name = "author_id")
    private Integer authorId;

    @Column(name = "lock_version")
    private Integer lockVersion;

    @Column(name = "created_on")
    private Timestamp createdOn;

    @Column(name = "updated_on")
    private Timestamp updatedOn;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "done_ratio")
    private Integer doneRatio;

    @Column(name = "estimated_hours")
    private Float estimatedHours;

    @Column(name = "parent_id")
    private Integer parentId;

    @Column(name = "root_id")
    private Integer rootId;

    @Column(name = "lft")
    private Integer lft;

    @Column(name = "rgt")
    private Integer rgt;

    @Column(name = "is_private")
    private Boolean isPrivate;

    @Column(name = "closed_on")
    private Timestamp closedOn;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTrackerId() {
        return trackerId;
    }

    public void setTrackerId(String trackerId) {
        this.trackerId = trackerId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public Integer getAssignedToId() {
        return assignedToId;
    }

    public void setAssignedToId(Integer assignedToId) {
        this.assignedToId = assignedToId;
    }

    public Integer getPriorityId() {
        return priorityId;
    }

    public void setPriorityId(Integer priorityId) {
        this.priorityId = priorityId;
    }

    public Integer getFixedVersionId() {
        return fixedVersionId;
    }

    public void setFixedVersionId(Integer fixedVersionId) {
        this.fixedVersionId = fixedVersionId;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public Integer getLockVersion() {
        return lockVersion;
    }

    public void setLockVersion(Integer lockVersion) {
        this.lockVersion = lockVersion;
    }

    public Timestamp getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Timestamp createdOn) {
        this.createdOn = createdOn;
    }

    public Timestamp getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Timestamp updatedOn) {
        this.updatedOn = updatedOn;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Integer getDoneRatio() {
        return doneRatio;
    }

    public void setDoneRatio(Integer doneRatio) {
        this.doneRatio = doneRatio;
    }

    public Float getEstimatedHours() {
        return estimatedHours;
    }

    public void setEstimatedHours(Float estimatedHours) {
        this.estimatedHours = estimatedHours;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getRootId() {
        return rootId;
    }

    public void setRootId(Integer rootId) {
        this.rootId = rootId;
    }

    public Integer getLft() {
        return lft;
    }

    public void setLft(Integer lft) {
        this.lft = lft;
    }

    public Integer getRgt() {
        return rgt;
    }

    public void setRgt(Integer rgt) {
        this.rgt = rgt;
    }

    public Boolean getPrivate() {
        return isPrivate;
    }

    public void setPrivate(Boolean aPrivate) {
        isPrivate = aPrivate;
    }

    public Timestamp getClosedOn() {
        return closedOn;
    }

    public void setClosedOn(Timestamp closedOn) {
        this.closedOn = closedOn;
    }
}


