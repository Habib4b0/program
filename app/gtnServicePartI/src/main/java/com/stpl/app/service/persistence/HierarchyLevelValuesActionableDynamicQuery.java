package com.stpl.app.service.persistence;

import com.stpl.app.model.HierarchyLevelValues;
import com.stpl.app.service.HierarchyLevelValuesLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class HierarchyLevelValuesActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public HierarchyLevelValuesActionableDynamicQuery()
        throws SystemException {
        setBaseLocalService(HierarchyLevelValuesLocalServiceUtil.getService());
        setClass(HierarchyLevelValues.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("hierarchyLevelValuesSid");
    }
}
