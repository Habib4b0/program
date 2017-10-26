package com.stpl.app.service.persistence;

import com.stpl.app.model.HistHierarchyLevelDefn;
import com.stpl.app.service.HistHierarchyLevelDefnLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class HistHierarchyLevelDefnActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public HistHierarchyLevelDefnActionableDynamicQuery()
        throws SystemException {
        setBaseLocalService(HistHierarchyLevelDefnLocalServiceUtil.getService());
        setClass(HistHierarchyLevelDefn.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("primaryKey.hierarchyLevelDefinitionSid");
    }
}
