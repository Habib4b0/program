package com.stpl.app.service.persistence;

import com.stpl.app.model.ItemMaster;
import com.stpl.app.service.ItemMasterLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class ItemMasterActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public ItemMasterActionableDynamicQuery() throws SystemException {
        setBaseLocalService(ItemMasterLocalServiceUtil.getService());
        setClass(ItemMaster.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("itemMasterSid");
    }
}
