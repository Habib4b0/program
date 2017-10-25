package com.stpl.app.service.persistence;

import com.stpl.app.model.IvldItemHierarchyDefinition;
import com.stpl.app.service.IvldItemHierarchyDefinitionLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class IvldItemHierarchyDefinitionActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public IvldItemHierarchyDefinitionActionableDynamicQuery()
        throws SystemException {
        setBaseLocalService(IvldItemHierarchyDefinitionLocalServiceUtil.getService());
        setClass(IvldItemHierarchyDefinition.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("ivldItemHierarchyDefinitionSid");
    }
}
