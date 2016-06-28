package com.stpl.app.service.persistence;

import com.stpl.app.model.FcpActuals;
import com.stpl.app.service.FcpActualsLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class FcpActualsActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public FcpActualsActionableDynamicQuery() throws SystemException {
        setBaseLocalService(FcpActualsLocalServiceUtil.getService());
        setClass(FcpActuals.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("primaryKey.periodSid");
    }
}
