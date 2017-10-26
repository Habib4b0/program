package com.stpl.app.service.persistence;

import com.stpl.app.model.MailNotificationMaster;
import com.stpl.app.service.MailNotificationMasterLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class MailNotificationMasterActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public MailNotificationMasterActionableDynamicQuery()
        throws SystemException {
        setBaseLocalService(MailNotificationMasterLocalServiceUtil.getService());
        setClass(MailNotificationMaster.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("mailNotificationSid");
    }
}
