package com.stpl.app.service.persistence;

import com.stpl.app.model.ItemHierarchyDefMaster;
import com.stpl.app.service.ItemHierarchyDefMasterLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class ItemHierarchyDefMasterActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public ItemHierarchyDefMasterActionableDynamicQuery()
        throws SystemException {
        setBaseLocalService(ItemHierarchyDefMasterLocalServiceUtil.getService());
        setClass(ItemHierarchyDefMaster.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("itemHierarchyDefMasterSid");
    }
}
