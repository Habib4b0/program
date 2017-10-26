package com.stpl.app.service.persistence;

import com.stpl.app.model.ItemHierarchyMaster;
import com.stpl.app.service.ItemHierarchyMasterLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class ItemHierarchyMasterActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public ItemHierarchyMasterActionableDynamicQuery()
        throws SystemException {
        setBaseLocalService(ItemHierarchyMasterLocalServiceUtil.getService());
        setClass(ItemHierarchyMaster.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("itemHierarchyMasterSid");
    }
}
