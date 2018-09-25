package com.syscxp.biz.entity.redmine;

import org.springframework.stereotype.Component;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

/**
 * @Author: sunxuelong.
 * @Cretion Date: 2018-09-17.
 * @Description: .
 */

@StaticMetamodel(ProjectTracker.class)
@Component
public class ProjectTracker_ {
    public static volatile SingularAttribute<Issue, Long> projectId;
    public static volatile SingularAttribute<Issue, Long> trackerId;
}