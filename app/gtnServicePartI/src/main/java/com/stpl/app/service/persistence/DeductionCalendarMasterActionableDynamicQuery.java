package com.stpl.app.service.persistence;

import com.stpl.app.model.DeductionCalendarMaster;
import com.stpl.app.service.DeductionCalendarMasterLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class DeductionCalendarMasterActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public DeductionCalendarMasterActionableDynamicQuery()
        throws SystemException {
        setBaseLocalService(DeductionCalendarMasterLocalServiceUtil.getService());
        setClass(DeductionCalendarMaster.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("deductionCalendarMasterSid");
    }
}
