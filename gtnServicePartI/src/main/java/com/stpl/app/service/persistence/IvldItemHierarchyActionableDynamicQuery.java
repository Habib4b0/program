package com.stpl.app.service.persistence;

import com.stpl.app.model.IvldItemHierarchy;
import com.stpl.app.service.IvldItemHierarchyLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class IvldItemHierarchyActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public IvldItemHierarchyActionableDynamicQuery() throws SystemException {
        setBaseLocalService(IvldItemHierarchyLocalServiceUtil.getService());
        setClass(IvldItemHierarchy.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("ivldItemHierarchySid");
    }
}
