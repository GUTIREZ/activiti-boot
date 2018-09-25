package com.syscxp.biz.entity.redmine;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * @Author: sunxuelong.
 * @Cretion Date: 2018-09-17.
 * @Description: .
 */
@Entity(name = "projects")
public class Project {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "homepage")
    private String homePage;

    @Column(name = "is_public")
    private Boolean isPublic;

    @Column(name = "parent_id")
    private Long parentId;

    @Column(name = "created_on")
    private Timestamp createdOn;

    @Column(name = "updated_on")
    private Timestamp updatedOn;

    @Column(name = "identifier")
    private String identifier;

    @Column(name = "inherit_members")
    private Boolean inheritMembers;

    @Column(name = "default_version_id")
    private Long defaultVersionId;

    @Column(name = "default_assigned_to_id")
    private Long defaultAssignedToId;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHomePage() {
        return homePage;
    }

    public void setHomePage(String homePage) {
        this.homePage = homePage;
    }

    public Boolean getPublic() {
        return isPublic;
    }

    public void setPublic(Boolean aPublic) {
        isPublic = aPublic;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
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

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public Boolean getInheritMembers() {
        return inheritMembers;
    }

    public void setInheritMembers(Boolean inheritMembers) {
        this.inheritMembers = inheritMembers;
    }

    public Long getDefaultVersionId() {
        return defaultVersionId;
    }

    public void setDefaultVersionId(Long defaultVersionId) {
        this.defaultVersionId = defaultVersionId;
    }

    public Long getDefaultAssignedToId() {
        return defaultAssignedToId;
    }

    public void setDefaultAssignedToId(Long defaultAssignedToId) {
        this.defaultAssignedToId = defaultAssignedToId;
    }
}


