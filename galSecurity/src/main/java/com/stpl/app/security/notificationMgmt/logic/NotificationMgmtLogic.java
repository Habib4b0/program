/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.security.notificationMgmt.logic;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.stpl.app.model.HelperTable;
import com.stpl.app.model.MailNotificationMaster;
import com.stpl.app.model.impl.MailNotificationMasterImpl;
import com.stpl.app.security.dao.NotificationMgmtLogicDAO;
import com.stpl.app.security.dao.impl.NotificationMgmtLogicDAOImpl;
import com.stpl.app.security.notificationMgmt.dto.NotificationMgmtIndexDTO;
import com.stpl.app.util.CommonUtils;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.stpl.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.server.VaadinSession;

/**
 *
 * @author Santanukumar
 */
public class NotificationMgmtLogic {
    private static final Logger LOGGER = LogManager
            .getLogger(NotificationMgmtLogic.class);
   Date date = new Date();
    NotificationMgmtLogicDAO dao=new NotificationMgmtLogicDAOImpl();
    public  List<String> loadBusinessProcess() {
        DynamicQuery businessProcessDynamicQuery = DynamicQueryFactoryUtil.forClass(HelperTable.class);
        businessProcessDynamicQuery.add(RestrictionsFactoryUtil.ilike("listName", "WorkflowProcesses"));
        List<HelperTable> resultList = new ArrayList<HelperTable>();
        List<String> helperList = new ArrayList<String>();
        try {
            resultList = dao.getBusinessProcess(businessProcessDynamicQuery);
            if (resultList != null) {
                for (int i = 0; i < resultList.size(); i++) {
                    HelperTable helperTable = (HelperTable) resultList.get(i);
                    helperList.add(helperTable.getDescription());
                }
            }
        } catch (SystemException e) {
            LOGGER.error(e);
        }
        return helperList;
    }
    public  List<HelperDTO> loadCategory() {
        DynamicQuery categoryDynamicQuery = DynamicQueryFactoryUtil.forClass(HelperTable.class);      
        categoryDynamicQuery.add(RestrictionsFactoryUtil.ilike("listName", "MailNotificationCategory"));
      
        List<HelperTable> resultList = new ArrayList<HelperTable>();
        List<HelperDTO> results = new ArrayList<HelperDTO>();
        try {
            resultList = dao.getCategory(categoryDynamicQuery);

        } catch (SystemException e) {
            LOGGER.error(e);
        }
        for (HelperTable obj : resultList) {
            HelperDTO dto = new HelperDTO();
            dto.setId((obj.getHelperTableSid()));
            dto.setDescription((obj.getDescription()));
            results.add(dto);
        }
        return results;
    }
   public String  saveNotification(NotificationMgmtIndexDTO notificationMgmtIndexDTO){
       String userId = String.valueOf(VaadinSession.getCurrent().getAttribute("userId"));
     //  HashMap<String, Long> hm = CommonUtils.getUserInfo();
     //  int createdById=Integer.valueOf(String.valueOf(hm.get(userId)));
       
     MailNotificationMaster mailNotificationMaster = new MailNotificationMasterImpl();
        if (notificationMgmtIndexDTO.getCategoryId()!= 0) {
            mailNotificationMaster.setNotificationCategoryId(notificationMgmtIndexDTO.getCategoryId());
        }
        
if (notificationMgmtIndexDTO.getBusinessProcess()!= null) {
            mailNotificationMaster.setNotificationModule(notificationMgmtIndexDTO.getBusinessProcess());
        }
        if (notificationMgmtIndexDTO.getFromMailId()!= null) {
            mailNotificationMaster.setFromMailId(notificationMgmtIndexDTO.getFromMailId());
        }

         if (notificationMgmtIndexDTO.getToMailId()!= null) {
            mailNotificationMaster.setToMailIds(notificationMgmtIndexDTO.getToMailId());
        }

          if (notificationMgmtIndexDTO.getCcMailId()!= null) {
            mailNotificationMaster.setCcMailIds(notificationMgmtIndexDTO.getCcMailId());
        }
         if (notificationMgmtIndexDTO.getSubject()!= null) {
            mailNotificationMaster.setSubject(notificationMgmtIndexDTO.getSubject());
        }
          if (notificationMgmtIndexDTO.getBody()!= null) {
            mailNotificationMaster.setBody(notificationMgmtIndexDTO.getBody());
        }
        if (notificationMgmtIndexDTO.getCreatedById()!=0) {
            
            mailNotificationMaster.setCreatedBy(notificationMgmtIndexDTO.getCreatedById());

        }
            mailNotificationMaster.setCreatedDate(date);
            mailNotificationMaster.setModifiedBy(0);
            mailNotificationMaster.setModifiedDate(date);
            mailNotificationMaster.setCreatedBy(0);

       
        try {
            mailNotificationMaster = dao.saveMailNotificationMaster(mailNotificationMaster);
            int SystemId = mailNotificationMaster.getMailNotificationSid();

           
           
        } catch (SystemException e) {
            LOGGER.error(e);
            return "fail";
        }
        return "success";
    }
   public String  updateNotification(NotificationMgmtIndexDTO notificationMgmtIndexDTO, int mailNotificationSystemId){
       String userId = String.valueOf(VaadinSession.getCurrent().getAttribute("userId"));
     
       
     MailNotificationMaster mailNotificationMaster = new MailNotificationMasterImpl();
    // int systemId=notificationMgmtIndexDTO.getMailNotificationSystemId();
        try {
            mailNotificationMaster = dao.getMailNotificationMaster(mailNotificationSystemId);
        }  catch (Exception ex) {
            
           LOGGER.error(ex);
        }
       if (notificationMgmtIndexDTO.getCategoryId()!= 0) {
            mailNotificationMaster.setNotificationCategoryId(notificationMgmtIndexDTO.getCategoryId());
       }
        
if (notificationMgmtIndexDTO.getBusinessProcess()!= null) {
            mailNotificationMaster.setNotificationModule(notificationMgmtIndexDTO.getBusinessProcess());
        }
        if (notificationMgmtIndexDTO.getFromMailId()!= null) {
            mailNotificationMaster.setFromMailId(notificationMgmtIndexDTO.getFromMailId());
        }

         if (notificationMgmtIndexDTO.getToMailId()!= null) {
            mailNotificationMaster.setToMailIds(notificationMgmtIndexDTO.getToMailId());
        }

          if (notificationMgmtIndexDTO.getCcMailId()!= null) {
            mailNotificationMaster.setCcMailIds(notificationMgmtIndexDTO.getCcMailId());
        }
         if (notificationMgmtIndexDTO.getSubject()!= null) {
            mailNotificationMaster.setSubject(notificationMgmtIndexDTO.getSubject());
        }
          if (notificationMgmtIndexDTO.getBody()!= null) {
            mailNotificationMaster.setBody(notificationMgmtIndexDTO.getBody());
        }
        if (notificationMgmtIndexDTO.getModifiedById()!=0) {
            
            mailNotificationMaster.setModifiedBy(notificationMgmtIndexDTO.getModifiedById());
        }
            mailNotificationMaster.setModifiedDate(date);

       
        try {
            mailNotificationMaster = dao.updateMailNotificationMaster(mailNotificationMaster);
            int SystemId = mailNotificationMaster.getMailNotificationSid();
            
           
           
        } catch (SystemException e) {
            LOGGER.error(e);
            return "fail";
        }
        return "success";
    }
   public boolean deleteNotification(int mailNotificationSystemId) {
        try {
            MailNotificationMaster mNm = dao.deleteNotification(mailNotificationSystemId);
           int systemId=mNm.getMailNotificationSid();
            
            
        } catch (PortalException ex) {
            LOGGER.error(ex);
            return false;
        } catch (SystemException ex) {
            LOGGER.error(ex);
            return false;
        }
        return true;
    }
   public List<NotificationMgmtIndexDTO> loadTable()
   {
       List<NotificationMgmtIndexDTO> resultBean=new ArrayList<NotificationMgmtIndexDTO>();
        DynamicQuery notificationDynamicQuery = DynamicQueryFactoryUtil.forClass(MailNotificationMaster.class);
        List<MailNotificationMaster> resultList=null;
        try
        {
             resultList=dao.getAllMailNotification(notificationDynamicQuery);
        }catch(SystemException e)
                {
                     LOGGER.error(e);
                }
        if(resultList!=null)
        {
            resultBean=getNotificationMgmtIndexDTO(resultList);
        }
       return resultBean;
   }
   public List<NotificationMgmtIndexDTO> getNotificationMgmtIndexDTO(List<MailNotificationMaster> resultList)
   {
       List<NotificationMgmtIndexDTO> resultBean=new ArrayList<NotificationMgmtIndexDTO>();
        HashMap<Integer, String> hmForCategory=CommonUtils.getCategoryNameFromId();
       for (MailNotificationMaster result : resultList) {
           NotificationMgmtIndexDTO notificationMgmtIndexDTO=new NotificationMgmtIndexDTO();
           notificationMgmtIndexDTO.setBusinessProcess(result.getNotificationModule());
           notificationMgmtIndexDTO.setFromMailId(result.getFromMailId());
           notificationMgmtIndexDTO.setToMailId(result.getToMailIds());
           notificationMgmtIndexDTO.setCcMailId(result.getCcMailIds());
           notificationMgmtIndexDTO.setSubject(result.getSubject());
           notificationMgmtIndexDTO.setBody(result.getBody());
           notificationMgmtIndexDTO.setCreationDate(String.valueOf(result.getCreatedDate()));
           notificationMgmtIndexDTO.setModifiedDate(String.valueOf(result.getModifiedDate())!=null && !"null".equals(String.valueOf(result.getModifiedDate()))?String.valueOf(result.getModifiedDate()):"");
           notificationMgmtIndexDTO.setCreatedById(result.getCreatedBy());
           notificationMgmtIndexDTO.setModifiedById(result.getModifiedBy());
           notificationMgmtIndexDTO.setMailNotificationSystemId(result.getMailNotificationSid());
           
           int categoryId=result.getNotificationCategoryId();
           LOGGER.info("categoryId========================>>"+categoryId);
           
           notificationMgmtIndexDTO.setCategory(hmForCategory.get(categoryId));
           LOGGER.info("notificationMgmtIndexDTO.getCategory()--------------"+notificationMgmtIndexDTO.getCategory());
          HelperDTO helperDTO=new HelperDTO();
            if(notificationMgmtIndexDTO.getCategory().equals("") || notificationMgmtIndexDTO.getCategory().equals("null") )
            {
                helperDTO=null;
            }
            else
            {                
                helperDTO.setDescription(notificationMgmtIndexDTO.getCategory());
                helperDTO.setId(categoryId);
            }
           notificationMgmtIndexDTO.setCategoryddlb(helperDTO);
           resultBean.add(notificationMgmtIndexDTO);
           
       }
       return resultBean;
   }
}
