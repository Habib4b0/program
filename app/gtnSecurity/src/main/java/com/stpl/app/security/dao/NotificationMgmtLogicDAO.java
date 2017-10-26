/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.security.dao;

import java.util.List;

import com.stpl.app.model.HelperTable;
import com.stpl.app.model.MailNotificationMaster;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;

/**
 *
 * @author santanukumar
 */
public interface NotificationMgmtLogicDAO {
    public List<HelperTable> getBusinessProcess(DynamicQuery dynamicQuery)throws SystemException;
    public List<HelperTable> getCategory(DynamicQuery dynamicQuery)throws SystemException;
    public MailNotificationMaster saveMailNotificationMaster(MailNotificationMaster mailNotificationMaster)throws SystemException;
    public MailNotificationMaster getMailNotificationMaster(int systemId)throws SystemException,PortalException;
     public MailNotificationMaster updateMailNotificationMaster(MailNotificationMaster mailNotificationMaster)throws SystemException;
     public MailNotificationMaster deleteNotification(int mailNotificationSystemId)throws SystemException,PortalException;
     public List<MailNotificationMaster> getAllMailNotification(DynamicQuery query)throws SystemException;
}
