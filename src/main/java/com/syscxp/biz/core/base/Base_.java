package com.syscxp.biz.core.base;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.sql.Timestamp;

/**
 * @Author: sunxuelong.
 * @Cretion Date: 2018-06-14.
 * @Description: .
 */
@StaticMetamodel(BaseEntity.class)
public class Base_ {
    public static volatile SingularAttribute<BaseEntity, String> id;
    public static volatile SingularAttribute<BaseEntity, String> createdBy;
    public static volatile SingularAttribute<BaseEntity, Timestamp> createdAt;
    public static volatile SingularAttribute<BaseEntity, String> updatedBy;
    public static volatile SingularAttribute<BaseEntity, Timestamp> updatedAt;
}
