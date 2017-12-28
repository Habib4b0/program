/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.security.notificationMgmt.logic;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import com.stpl.app.model.HelperTable;
import com.stpl.app.security.dao.NotificationMgmtLogicDAO;
import com.stpl.app.security.dao.impl.NotificationMgmtLogicDAOImpl;
import com.stpl.app.security.notificationMgmt.dto.NotificationMgmtIndexDTO;
import com.stpl.app.util.CommonUtils;
import com.stpl.ifs.util.HelperDTO;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.stpl.app.model.MailNotificationMaster;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.service.MailNotificationMasterLocalServiceUtil;
import org.jboss.logging.Logger;

/**
 *
 * @author Santanukumar
 */
public class NotificationMgmtLogic {
    private static final Logger LOGGER = Logger
            .getLogger(NotificationMgmtLogic.class);
   Date date = new Date();
    NotificationMgmtLogicDAO dao=new NotificationMgmtLogicDAOImpl();
    public  List<String> loadBusinessProcess() {
        DynamicQuery businessProcessDynamicQuery = HelperTableLocalServiceUtil.dynamicQuery();
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
        DynamicQuery categoryDynamicQuery = HelperTableLocalServiceUtil.dynamicQuery();
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
            dto.setId(obj.getHelperTableSid());
            dto.setDescription(obj.getDescription());
            results.add(dto);
        }
        return results;
    }
   public String  saveNotification(NotificationMgmtIndexDTO notificationMgmtIndexDTO){
       try {
            MailNotificationMaster mailNotificationMaster ;
            mailNotificationMaster = MailNotificationMasterLocalServiceUtil.createMailNotificationMaster(0);

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
        
            dao.saveMailNotificationMaster(mailNotificationMaster);
           
        } catch (SystemException e) {
            LOGGER.error(e);
            return "fail";
        }
        return "success";
    }
   public String  updateNotification(NotificationMgmtIndexDTO notificationMgmtIndexDTO, int mailNotificationSystemId){
        try {
            MailNotificationMaster mailNotificationMaster;
            mailNotificationMaster = dao.getMailNotificationMaster(mailNotificationSystemId);
        
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

       
        
            dao.updateMailNotificationMaster(mailNotificationMaster);
            
           
           
        } catch (SystemException | PortalException e ) {
            LOGGER.error(e);
            return "fail";
        }
        return "success";
    }
   public boolean deleteNotification(int mailNotificationSystemId) {
        try {
            dao.deleteNotification(mailNotificationSystemId);
            
            
        } catch (PortalException | SystemException ex) {
            LOGGER.error(ex);
            return false;
        }
        return true;
    }
   public List<NotificationMgmtIndexDTO> loadTable(){
       List<NotificationMgmtIndexDTO> resultBean=new ArrayList<NotificationMgmtIndexDTO>();
        DynamicQuery notificationDynamicQuery = MailNotificationMasterLocalServiceUtil.dynamicQuery();
        List<MailNotificationMaster> resultList=null;
        try{
             resultList=dao.getAllMailNotification(notificationDynamicQuery);
        }catch(SystemException e){
                     LOGGER.error(e);
                }
        if(resultList!=null){
            resultBean=getNotificationMgmtIndexDTO(resultList);
        }
       return resultBean;
   }
   public List<NotificationMgmtIndexDTO> getNotificationMgmtIndexDTO(List<MailNotificationMaster> resultList){
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
           LOGGER.debug("categoryId========================>>"+categoryId);
           
           notificationMgmtIndexDTO.setCategory(hmForCategory.get(categoryId));
           LOGGER.debug("notificationMgmtIndexDTO.getCategory()--------------"+notificationMgmtIndexDTO.getCategory());
          HelperDTO helperDTO=new HelperDTO();
            if(notificationMgmtIndexDTO.getCategory().equals("") || notificationMgmtIndexDTO.getCategory().equals("null")){
                helperDTO=null;
            }
            else{                
                helperDTO.setDescription(notificationMgmtIndexDTO.getCategory());
                helperDTO.setId(categoryId);
            }
           notificationMgmtIndexDTO.setCategoryddlb(helperDTO);
           resultBean.add(notificationMgmtIndexDTO);
           
       }
       return resultBean;
   }
}
