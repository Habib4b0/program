package com.stpl.app.service.persistence;

import com.stpl.app.model.NmActualPpa;
import com.stpl.app.service.NmActualPpaLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class NmActualPpaActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public NmActualPpaActionableDynamicQuery() throws SystemException {
        setBaseLocalService(NmActualPpaLocalServiceUtil.getService());
        setClass(NmActualPpa.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("primaryKey.periodSid");
    }
}
