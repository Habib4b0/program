package com.stpl.app.service.persistence;

import com.stpl.app.model.Period;
import com.stpl.app.service.PeriodLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class PeriodActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public PeriodActionableDynamicQuery() throws SystemException {
        setBaseLocalService(PeriodLocalServiceUtil.getService());
        setClass(Period.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("periodSid");
    }
}
