package com.syscxp.biz.core.base;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @Author: sunxuelong.
 * @Cretion Date: 2018-04-26.
 * @Description: .
 */

@MappedSuperclass
public class BaseEntity implements Serializable{
    @Id
    @GenericGenerator(name="id",strategy="uuid")
    @GeneratedValue(generator="id")
    @Column(length = 32, nullable = false)
    private String id;

    @Version
    private Integer version;

    @Column(updatable = false)
    @CreationTimestamp
    private Timestamp createdAt;

    @Column
    private String createdBy;

    @Column
    @UpdateTimestamp
    private Timestamp updatedAt;

    @Column
    private String updatedBy;

    @Override
    public int hashCode() {
        return id == null ? System.identityHashCode(this) : id.hashCode();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }
}