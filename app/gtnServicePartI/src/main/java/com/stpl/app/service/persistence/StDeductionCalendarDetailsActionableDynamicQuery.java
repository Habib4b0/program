package com.stpl.app.service.persistence;

import com.stpl.app.model.StDeductionCalendarDetails;
import com.stpl.app.service.StDeductionCalendarDetailsLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class StDeductionCalendarDetailsActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public StDeductionCalendarDetailsActionableDynamicQuery()
        throws SystemException {
        setBaseLocalService(StDeductionCalendarDetailsLocalServiceUtil.getService());
        setClass(StDeductionCalendarDetails.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("primaryKey.periodSid");
    }
}
