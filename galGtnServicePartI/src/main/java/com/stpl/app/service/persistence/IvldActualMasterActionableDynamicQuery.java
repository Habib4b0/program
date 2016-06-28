package com.stpl.app.service.persistence;

import com.stpl.app.model.IvldActualMaster;
import com.stpl.app.service.IvldActualMasterLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class IvldActualMasterActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public IvldActualMasterActionableDynamicQuery() throws SystemException {
        setBaseLocalService(IvldActualMasterLocalServiceUtil.getService());
        setClass(IvldActualMaster.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("ivldActualMasterSid");
    }
}
