package com.syscxp.biz.entity.redmine;

import org.springframework.stereotype.Component;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

/**
 * @Author: sunxuelong.
 * @Cretion Date: 2018-09-17.
 * @Description: .
 */

@StaticMetamodel(Workflow.class)
@Component
public class Workflow_ {
    public static volatile SingularAttribute<Issue, Long> id;
    public static volatile SingularAttribute<Issue, Long> trackerId;
    public static volatile SingularAttribute<Issue, Long> roleId;
    public static volatile SingularAttribute<Issue, String> type;
    public static volatile SingularAttribute<Issue, String> fieldName;
    public static volatile SingularAttribute<Issue, String> rule;
}