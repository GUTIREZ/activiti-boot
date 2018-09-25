package com.syscxp.biz.entity.redmine;

import org.springframework.stereotype.Component;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

/**
 * @Author: sunxuelong.
 * @Cretion Date: 2018-09-17.
 * @Description: .
 */

@StaticMetamodel(JournalDetail.class)
@Component
public class JournalDetail_ {
    public static volatile SingularAttribute<Issue, Long> id;
    public static volatile SingularAttribute<Issue, Long> journalId;
    public static volatile SingularAttribute<Issue, String> property;
    public static volatile SingularAttribute<Issue, String> propKey;
}