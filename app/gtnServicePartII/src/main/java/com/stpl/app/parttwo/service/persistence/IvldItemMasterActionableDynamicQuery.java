package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.model.IvldItemMaster;
import com.stpl.app.parttwo.service.IvldItemMasterLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class IvldItemMasterActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public IvldItemMasterActionableDynamicQuery() throws SystemException {
        setBaseLocalService(IvldItemMasterLocalServiceUtil.getService());
        setClass(IvldItemMaster.class);

        setClassLoader(com.stpl.app.parttwo.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("ivldItemMasterSid");
    }
}
