package com.stpl.app.service.persistence;

import com.stpl.app.model.HistHierarchyLevelValues;
import com.stpl.app.service.HistHierarchyLevelValuesLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class HistHierarchyLevelValuesActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public HistHierarchyLevelValuesActionableDynamicQuery()
        throws SystemException {
        setBaseLocalService(HistHierarchyLevelValuesLocalServiceUtil.getService());
        setClass(HistHierarchyLevelValues.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("primaryKey.hierarchyLevelValuesSid");
    }
}
