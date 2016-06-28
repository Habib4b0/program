package com.stpl.app.service.persistence;

import com.stpl.app.model.RelationshipBuilder;
import com.stpl.app.service.RelationshipBuilderLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class RelationshipBuilderActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public RelationshipBuilderActionableDynamicQuery()
        throws SystemException {
        setBaseLocalService(RelationshipBuilderLocalServiceUtil.getService());
        setClass(RelationshipBuilder.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("relationshipBuilderSid");
    }
}
