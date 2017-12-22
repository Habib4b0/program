/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.security.dao.impl;

import java.util.List;

import com.stpl.app.model.HelperTable;
import com.stpl.app.model.MailNotificationMaster;
import com.stpl.app.security.dao.NotificationMgmtLogicDAO;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.service.MailNotificationMasterLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

/**
 *
 * @author santanukumar
 */
public class NotificationMgmtLogicDAOImpl implements NotificationMgmtLogicDAO {

    public List<HelperTable> getBusinessProcess(DynamicQuery dynamicQuery) throws SystemException {
        return HelperTableLocalServiceUtil.dynamicQuery(dynamicQuery);
    }

    public List<HelperTable> getCategory(DynamicQuery dynamicQuery) throws SystemException {
        return HelperTableLocalServiceUtil.dynamicQuery(dynamicQuery);
    }

    public MailNotificationMaster saveMailNotificationMaster(MailNotificationMaster mailNotificationMaster) throws SystemException {
        return MailNotificationMasterLocalServiceUtil.addMailNotificationMaster(mailNotificationMaster);
    }

    public MailNotificationMaster getMailNotificationMaster(int systemId) throws SystemException, PortalException {
        return MailNotificationMasterLocalServiceUtil.getMailNotificationMaster(systemId);
    }

    public MailNotificationMaster updateMailNotificationMaster(MailNotificationMaster mailNotificationMaster) throws SystemException {
        return MailNotificationMasterLocalServiceUtil.updateMailNotificationMaster(mailNotificationMaster);
    }

    public MailNotificationMaster deleteNotification(int mailNotificationSystemId) throws SystemException, PortalException {
        return MailNotificationMasterLocalServiceUtil.deleteMailNotificationMaster(mailNotificationSystemId);
    }

    public List<MailNotificationMaster> getAllMailNotification(DynamicQuery query) throws SystemException {
       return MailNotificationMasterLocalServiceUtil.dynamicQuery(query);
    }
}
