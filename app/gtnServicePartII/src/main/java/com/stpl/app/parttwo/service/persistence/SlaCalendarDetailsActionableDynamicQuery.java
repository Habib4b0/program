package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.model.SlaCalendarDetails;
import com.stpl.app.parttwo.service.SlaCalendarDetailsLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class SlaCalendarDetailsActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public SlaCalendarDetailsActionableDynamicQuery() throws SystemException {
        setBaseLocalService(SlaCalendarDetailsLocalServiceUtil.getService());
        setClass(SlaCalendarDetails.class);

        setClassLoader(com.stpl.app.parttwo.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("slaCalendarDetailsSid");
    }
}
