package com.stpl.app.service.persistence;

import com.stpl.app.model.VwAccrualMaster;
import com.stpl.app.service.VwAccrualMasterLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class VwAccrualMasterActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public VwAccrualMasterActionableDynamicQuery() throws SystemException {
        setBaseLocalService(VwAccrualMasterLocalServiceUtil.getService());
        setClass(VwAccrualMaster.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("accrualMasterSid");
    }
}
