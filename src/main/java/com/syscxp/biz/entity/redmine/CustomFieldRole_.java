package com.syscxp.biz.entity.redmine;

import org.springframework.stereotype.Component;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

/**
 * @Author: sunxuelong.
 * @Cretion Date: 2018-09-17.
 * @Description: .
 */

@StaticMetamodel(CustomFieldRole.class)
@Component
public class CustomFieldRole_ {
    public static volatile SingularAttribute<Issue, Long> customFieldId;
    public static volatile SingularAttribute<Issue, Long> roleId;
}