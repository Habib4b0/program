package com.stpl.app.service.persistence;

import com.stpl.app.model.PhsActuals;
import com.stpl.app.service.PhsActualsLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class PhsActualsActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public PhsActualsActionableDynamicQuery() throws SystemException {
        setBaseLocalService(PhsActualsLocalServiceUtil.getService());
        setClass(PhsActuals.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("primaryKey.periodSid");
    }
}
