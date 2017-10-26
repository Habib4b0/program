package com.stpl.app.service.persistence;

import com.stpl.app.model.IvldLotMaster;
import com.stpl.app.service.IvldLotMasterLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class IvldLotMasterActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public IvldLotMasterActionableDynamicQuery() throws SystemException {
        setBaseLocalService(IvldLotMasterLocalServiceUtil.getService());
        setClass(IvldLotMaster.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("ivldLotMasterSid");
    }
}
