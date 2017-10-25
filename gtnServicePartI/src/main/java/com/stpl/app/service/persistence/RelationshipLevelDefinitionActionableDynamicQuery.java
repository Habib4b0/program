package com.stpl.app.service.persistence;

import com.stpl.app.model.RelationshipLevelDefinition;
import com.stpl.app.service.RelationshipLevelDefinitionLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class RelationshipLevelDefinitionActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public RelationshipLevelDefinitionActionableDynamicQuery()
        throws SystemException {
        setBaseLocalService(RelationshipLevelDefinitionLocalServiceUtil.getService());
        setClass(RelationshipLevelDefinition.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("relationshipLevelSid");
    }
}
