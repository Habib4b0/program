/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.security.notificationMgmt.dto;

import com.stpl.ifs.util.HelperDTO;

/**
 *
 * @author Santanukumar
 */
public class NotificationMgmtIndexDTO {
    private String businessProcess="";
    private String fromMailId="";
    private String toMailId="";
    private String ccMailId="";
    private String subject="";
     private String body="";
      private String creationDate="";
      private String modifiedDate="";
      private int createdById;
      private int modifiedById;
       private int categoryId;
       private int mailNotificationSystemId;
       private String category="";
       private HelperDTO categoryddlb;

    public HelperDTO getCategoryddlb() {
        return categoryddlb;
    }

    public void setCategoryddlb(HelperDTO categoryddlb) {
        this.categoryddlb = categoryddlb;
    }
       

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
       

    public int getMailNotificationSystemId() {
        return mailNotificationSystemId;
    }

    public void setMailNotificationSystemId(int mailNotificationSystemId) {
        this.mailNotificationSystemId = mailNotificationSystemId;
    }
       

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
    public String getBusinessProcess() {
        return businessProcess;
    }

    public void setBusinessProcess(String businessProcess) {
        this.businessProcess = businessProcess;
    }

    public String getFromMailId() {
        return fromMailId;
    }

    public void setFromMailId(String fromMailId) {
        this.fromMailId = fromMailId;
    }

    public String getToMailId() {
        return toMailId;
    }

    public void setToMailId(String toMailId) {
        this.toMailId = toMailId;
    }

    public String getCcMailId() {
        return ccMailId;
    }

    public void setCcMailId(String ccMailId) {
        this.ccMailId = ccMailId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public int getCreatedById() {
        return createdById;
    }

    public void setCreatedById(int createdById) {
        this.createdById = createdById;
    }

    public int getModifiedById() {
        return modifiedById;
    }

    public void setModifiedById(int modifiedById) {
        this.modifiedById = modifiedById;
    }
      
    
    
}
