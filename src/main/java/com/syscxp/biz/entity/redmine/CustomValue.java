package com.syscxp.biz.entity.redmine;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Author: sunxuelong.
 * @Cretion Date: 2018-09-17.
 * @Description: .
 */
@Entity(name = "custom_values")
public class CustomValue implements Serializable {
    private static final long serialVersionUID = 1183354053276272025L;

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "customized_type")
    private String customizedType;

    @Column(name = "customized_id")
    private Long customizedId;

    @Column(name = "custom_field_id")
    private Long customFieldId;

    @Column(name = "value")
    private String value;

    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinColumn(name = "custom_field_id", insertable = false, updatable = false)
    private CustomField customField;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomizedType() {
        return customizedType;
    }

    public void setCustomizedType(String customizedType) {
        this.customizedType = customizedType;
    }

    public Long getCustomizedId() {
        return customizedId;
    }

    public void setCustomizedId(Long customizedId) {
        this.customizedId = customizedId;
    }

    public Long getCustomFieldId() {
        return customFieldId;
    }

    public void setCustomFieldId(Long customFieldId) {
        this.customFieldId = customFieldId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public CustomField getCustomField() {
        return customField;
    }

    public void setCustomField(CustomField customField) {
        this.customField = customField;
    }
}


