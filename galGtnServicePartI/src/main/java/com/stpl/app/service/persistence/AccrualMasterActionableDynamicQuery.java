package com.stpl.app.service.persistence;

import com.stpl.app.model.AccrualMaster;
import com.stpl.app.service.AccrualMasterLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class AccrualMasterActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public AccrualMasterActionableDynamicQuery() throws SystemException {
        setBaseLocalService(AccrualMasterLocalServiceUtil.getService());
        setClass(AccrualMaster.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("accrualMasterSid");
    }
}
