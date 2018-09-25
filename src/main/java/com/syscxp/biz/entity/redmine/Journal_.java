package com.syscxp.biz.entity.redmine;

import org.springframework.stereotype.Component;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.sql.Timestamp;

/**
 * @Author: sunxuelong.
 * @Cretion Date: 2018-09-17.
 * @Description: .
 */

@StaticMetamodel(Journal.class)
@Component
public class Journal_ {
    public static volatile SingularAttribute<Journal, Long> id;
    public static volatile SingularAttribute<Journal, Long> journalizedId;
    public static volatile SingularAttribute<Journal, String> journalizedType;
    public static volatile SingularAttribute<Journal, Long> userId;
    public static volatile SingularAttribute<Journal, Timestamp> createdOn;
}