package com.stpl.app.service.persistence;

import com.stpl.app.model.HierarchyDefinition;
import com.stpl.app.service.HierarchyDefinitionLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class HierarchyDefinitionActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public HierarchyDefinitionActionableDynamicQuery()
        throws SystemException {
        setBaseLocalService(HierarchyDefinitionLocalServiceUtil.getService());
        setClass(HierarchyDefinition.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("hierarchyDefinitionSid");
    }
}
