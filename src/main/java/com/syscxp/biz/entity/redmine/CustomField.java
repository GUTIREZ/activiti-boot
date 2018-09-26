package com.syscxp.biz.entity.redmine;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author: sunxuelong.
 * @Cretion Date: 2018-09-17.
 * @Description: .
 */
@Entity(name = "custom_fields")
public class CustomField implements Serializable {
    private static final long serialVersionUID = -2956036835993083555L;

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "type")
    private String type;

    @Column(name = "name")
    private String name;

    @Column(name = "field_format")
    private String fieldFormat;

    @Column(name = "possible_values")
    private String possibleValues;

    @Column(name = "regexpp")
    private Date regexpp;

    @Column(name = "min_length")
    private Long minLength;

    @Column(name = "max_length")
    private Long maxLength;

    @Column(name = "is_required")
    private Boolean isRequired;

    @Column(name = "is_for_all")
    private Boolean isForAll;

    @Column(name = "is_filter")
    private Boolean isFilter;

    @Column(name = "position")
    private Long position;

    @Column(name = "searchable")
    private Boolean searchable;

    @Column(name = "default_value")
    private String defaultValue;

    @Column(name = "editable")
    private Boolean editable;

    @Column(name = "visible")
    private Boolean visible;

    @Column(name = "multiple")
    private Boolean multiple;

//    @Column(name = "format_store")
//    private String formatStore;

    @Column(name = "description")
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFieldFormat() {
        return fieldFormat;
    }

    public void setFieldFormat(String fieldFormat) {
        this.fieldFormat = fieldFormat;
    }

    public String getPossibleValues() {
        return possibleValues;
    }

    public void setPossibleValues(String possibleValues) {
        this.possibleValues = possibleValues;
    }

    public Date getRegexpp() {
        return regexpp;
    }

    public void setRegexpp(Date regexpp) {
        this.regexpp = regexpp;
    }

    public Long getMinLength() {
        return minLength;
    }

    public void setMinLength(Long minLength) {
        this.minLength = minLength;
    }

    public Long getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(Long maxLength) {
        this.maxLength = maxLength;
    }

    public Boolean getRequired() {
        return isRequired;
    }

    public void setRequired(Boolean required) {
        isRequired = required;
    }

    public Boolean getForAll() {
        return isForAll;
    }

    public void setForAll(Boolean forAll) {
        isForAll = forAll;
    }

    public Boolean getFilter() {
        return isFilter;
    }

    public void setFilter(Boolean filter) {
        isFilter = filter;
    }

    public Long getPosition() {
        return position;
    }

    public void setPosition(Long position) {
        this.position = position;
    }

    public Boolean getSearchable() {
        return searchable;
    }

    public void setSearchable(Boolean searchable) {
        this.searchable = searchable;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public Boolean getEditable() {
        return editable;
    }

    public void setEditable(Boolean editable) {
        this.editable = editable;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public Boolean getMultiple() {
        return multiple;
    }

    public void setMultiple(Boolean multiple) {
        this.multiple = multiple;
    }

//    public String getFormatStore() {
//        return formatStore;
//    }
//
//    public void setFormatStore(String formatStore) {
//        this.formatStore = formatStore;
//    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}


