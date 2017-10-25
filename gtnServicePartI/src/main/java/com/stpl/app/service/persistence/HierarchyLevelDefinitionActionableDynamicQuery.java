package com.stpl.app.service.persistence;

import com.stpl.app.model.HierarchyLevelDefinition;
import com.stpl.app.service.HierarchyLevelDefinitionLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class HierarchyLevelDefinitionActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public HierarchyLevelDefinitionActionableDynamicQuery()
        throws SystemException {
        setBaseLocalService(HierarchyLevelDefinitionLocalServiceUtil.getService());
        setClass(HierarchyLevelDefinition.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("hierarchyLevelDefinitionSid");
    }
}
