package com.stpl.app.service.persistence;

import com.stpl.app.model.MedicaidUraActuals;
import com.stpl.app.service.MedicaidUraActualsLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class MedicaidUraActualsActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public MedicaidUraActualsActionableDynamicQuery() throws SystemException {
        setBaseLocalService(MedicaidUraActualsLocalServiceUtil.getService());
        setClass(MedicaidUraActuals.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("primaryKey.periodSid");
    }
}
