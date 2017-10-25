package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.model.SlaCalendarMaster;
import com.stpl.app.parttwo.service.SlaCalendarMasterLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class SlaCalendarMasterActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public SlaCalendarMasterActionableDynamicQuery() throws SystemException {
        setBaseLocalService(SlaCalendarMasterLocalServiceUtil.getService());
        setClass(SlaCalendarMaster.class);

        setClassLoader(com.stpl.app.parttwo.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("slaCalendarMasterSid");
    }
}
