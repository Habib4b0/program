package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.model.VwItemMaster;
import com.stpl.app.parttwo.service.VwItemMasterLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class VwItemMasterActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public VwItemMasterActionableDynamicQuery() throws SystemException {
        setBaseLocalService(VwItemMasterLocalServiceUtil.getService());
        setClass(VwItemMaster.class);

        setClassLoader(com.stpl.app.parttwo.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("itemMasterSid");
    }
}
