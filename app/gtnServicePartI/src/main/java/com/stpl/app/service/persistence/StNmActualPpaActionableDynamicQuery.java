package com.stpl.app.service.persistence;

import com.stpl.app.model.StNmActualPpa;
import com.stpl.app.service.StNmActualPpaLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class StNmActualPpaActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public StNmActualPpaActionableDynamicQuery() throws SystemException {
        setBaseLocalService(StNmActualPpaLocalServiceUtil.getService());
        setClass(StNmActualPpa.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("primaryKey.periodSid");
    }
}
