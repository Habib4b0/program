package com.stpl.app.service.persistence;

import com.stpl.app.model.NationalAssumptionsActuals;
import com.stpl.app.service.NationalAssumptionsActualsLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class NationalAssumptionsActualsActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public NationalAssumptionsActualsActionableDynamicQuery()
        throws SystemException {
        setBaseLocalService(NationalAssumptionsActualsLocalServiceUtil.getService());
        setClass(NationalAssumptionsActuals.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("primaryKey.periodSid");
    }
}
