package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link MailNotificationMaster}.
 * </p>
 *
 * @author
 * @see MailNotificationMaster
 * @generated
 */
public class MailNotificationMasterWrapper implements MailNotificationMaster,
    ModelWrapper<MailNotificationMaster> {
    private MailNotificationMaster _mailNotificationMaster;

    public MailNotificationMasterWrapper(
        MailNotificationMaster mailNotificationMaster) {
        _mailNotificationMaster = mailNotificationMaster;
    }

    @Override
    public Class<?> getModelClass() {
        return MailNotificationMaster.class;
    }

    @Override
    public String getModelClassName() {
        return MailNotificationMaster.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("subject", getSubject());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("toMailIds", getToMailIds());
        attributes.put("notificationCategoryId", getNotificationCategoryId());
        attributes.put("notificationModule", getNotificationModule());
        attributes.put("body", getBody());
        attributes.put("fromMailId", getFromMailId());
        attributes.put("ccMailIds", getCcMailIds());
        attributes.put("versionNo", getVersionNo());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("mailNotificationSid", getMailNotificationSid());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String subject = (String) attributes.get("subject");

        if (subject != null) {
            setSubject(subject);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        String toMailIds = (String) attributes.get("toMailIds");

        if (toMailIds != null) {
            setToMailIds(toMailIds);
        }

        Integer notificationCategoryId = (Integer) attributes.get(
                "notificationCategoryId");

        if (notificationCategoryId != null) {
            setNotificationCategoryId(notificationCategoryId);
        }

        String notificationModule = (String) attributes.get(
                "notificationModule");

        if (notificationModule != null) {
            setNotificationModule(notificationModule);
        }

        String body = (String) attributes.get("body");

        if (body != null) {
            setBody(body);
        }

        String fromMailId = (String) attributes.get("fromMailId");

        if (fromMailId != null) {
            setFromMailId(fromMailId);
        }

        String ccMailIds = (String) attributes.get("ccMailIds");

        if (ccMailIds != null) {
            setCcMailIds(ccMailIds);
        }

        Integer versionNo = (Integer) attributes.get("versionNo");

        if (versionNo != null) {
            setVersionNo(versionNo);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        Integer mailNotificationSid = (Integer) attributes.get(
                "mailNotificationSid");

        if (mailNotificationSid != null) {
            setMailNotificationSid(mailNotificationSid);
        }
    }

    /**
    * Returns the primary key of this mail notification master.
    *
    * @return the primary key of this mail notification master
    */
    @Override
    public int getPrimaryKey() {
        return _mailNotificationMaster.getPrimaryKey();
    }

    /**
    * Sets the primary key of this mail notification master.
    *
    * @param primaryKey the primary key of this mail notification master
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _mailNotificationMaster.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the subject of this mail notification master.
    *
    * @return the subject of this mail notification master
    */
    @Override
    public java.lang.String getSubject() {
        return _mailNotificationMaster.getSubject();
    }

    /**
    * Sets the subject of this mail notification master.
    *
    * @param subject the subject of this mail notification master
    */
    @Override
    public void setSubject(java.lang.String subject) {
        _mailNotificationMaster.setSubject(subject);
    }

    /**
    * Returns the created date of this mail notification master.
    *
    * @return the created date of this mail notification master
    */
    @Override
    public java.util.Date getCreatedDate() {
        return _mailNotificationMaster.getCreatedDate();
    }

    /**
    * Sets the created date of this mail notification master.
    *
    * @param createdDate the created date of this mail notification master
    */
    @Override
    public void setCreatedDate(java.util.Date createdDate) {
        _mailNotificationMaster.setCreatedDate(createdDate);
    }

    /**
    * Returns the created by of this mail notification master.
    *
    * @return the created by of this mail notification master
    */
    @Override
    public int getCreatedBy() {
        return _mailNotificationMaster.getCreatedBy();
    }

    /**
    * Sets the created by of this mail notification master.
    *
    * @param createdBy the created by of this mail notification master
    */
    @Override
    public void setCreatedBy(int createdBy) {
        _mailNotificationMaster.setCreatedBy(createdBy);
    }

    /**
    * Returns the to mail IDs of this mail notification master.
    *
    * @return the to mail IDs of this mail notification master
    */
    @Override
    public java.lang.String getToMailIds() {
        return _mailNotificationMaster.getToMailIds();
    }

    /**
    * Sets the to mail IDs of this mail notification master.
    *
    * @param toMailIds the to mail IDs of this mail notification master
    */
    @Override
    public void setToMailIds(java.lang.String toMailIds) {
        _mailNotificationMaster.setToMailIds(toMailIds);
    }

    /**
    * Returns the notification category ID of this mail notification master.
    *
    * @return the notification category ID of this mail notification master
    */
    @Override
    public int getNotificationCategoryId() {
        return _mailNotificationMaster.getNotificationCategoryId();
    }

    /**
    * Sets the notification category ID of this mail notification master.
    *
    * @param notificationCategoryId the notification category ID of this mail notification master
    */
    @Override
    public void setNotificationCategoryId(int notificationCategoryId) {
        _mailNotificationMaster.setNotificationCategoryId(notificationCategoryId);
    }

    /**
    * Returns the notification module of this mail notification master.
    *
    * @return the notification module of this mail notification master
    */
    @Override
    public java.lang.String getNotificationModule() {
        return _mailNotificationMaster.getNotificationModule();
    }

    /**
    * Sets the notification module of this mail notification master.
    *
    * @param notificationModule the notification module of this mail notification master
    */
    @Override
    public void setNotificationModule(java.lang.String notificationModule) {
        _mailNotificationMaster.setNotificationModule(notificationModule);
    }

    /**
    * Returns the body of this mail notification master.
    *
    * @return the body of this mail notification master
    */
    @Override
    public java.lang.String getBody() {
        return _mailNotificationMaster.getBody();
    }

    /**
    * Sets the body of this mail notification master.
    *
    * @param body the body of this mail notification master
    */
    @Override
    public void setBody(java.lang.String body) {
        _mailNotificationMaster.setBody(body);
    }

    /**
    * Returns the from mail ID of this mail notification master.
    *
    * @return the from mail ID of this mail notification master
    */
    @Override
    public java.lang.String getFromMailId() {
        return _mailNotificationMaster.getFromMailId();
    }

    /**
    * Sets the from mail ID of this mail notification master.
    *
    * @param fromMailId the from mail ID of this mail notification master
    */
    @Override
    public void setFromMailId(java.lang.String fromMailId) {
        _mailNotificationMaster.setFromMailId(fromMailId);
    }

    /**
    * Returns the cc mail IDs of this mail notification master.
    *
    * @return the cc mail IDs of this mail notification master
    */
    @Override
    public java.lang.String getCcMailIds() {
        return _mailNotificationMaster.getCcMailIds();
    }

    /**
    * Sets the cc mail IDs of this mail notification master.
    *
    * @param ccMailIds the cc mail IDs of this mail notification master
    */
    @Override
    public void setCcMailIds(java.lang.String ccMailIds) {
        _mailNotificationMaster.setCcMailIds(ccMailIds);
    }

    /**
    * Returns the version no of this mail notification master.
    *
    * @return the version no of this mail notification master
    */
    @Override
    public int getVersionNo() {
        return _mailNotificationMaster.getVersionNo();
    }

    /**
    * Sets the version no of this mail notification master.
    *
    * @param versionNo the version no of this mail notification master
    */
    @Override
    public void setVersionNo(int versionNo) {
        _mailNotificationMaster.setVersionNo(versionNo);
    }

    /**
    * Returns the modified by of this mail notification master.
    *
    * @return the modified by of this mail notification master
    */
    @Override
    public int getModifiedBy() {
        return _mailNotificationMaster.getModifiedBy();
    }

    /**
    * Sets the modified by of this mail notification master.
    *
    * @param modifiedBy the modified by of this mail notification master
    */
    @Override
    public void setModifiedBy(int modifiedBy) {
        _mailNotificationMaster.setModifiedBy(modifiedBy);
    }

    /**
    * Returns the modified date of this mail notification master.
    *
    * @return the modified date of this mail notification master
    */
    @Override
    public java.util.Date getModifiedDate() {
        return _mailNotificationMaster.getModifiedDate();
    }

    /**
    * Sets the modified date of this mail notification master.
    *
    * @param modifiedDate the modified date of this mail notification master
    */
    @Override
    public void setModifiedDate(java.util.Date modifiedDate) {
        _mailNotificationMaster.setModifiedDate(modifiedDate);
    }

    /**
    * Returns the mail notification sid of this mail notification master.
    *
    * @return the mail notification sid of this mail notification master
    */
    @Override
    public int getMailNotificationSid() {
        return _mailNotificationMaster.getMailNotificationSid();
    }

    /**
    * Sets the mail notification sid of this mail notification master.
    *
    * @param mailNotificationSid the mail notification sid of this mail notification master
    */
    @Override
    public void setMailNotificationSid(int mailNotificationSid) {
        _mailNotificationMaster.setMailNotificationSid(mailNotificationSid);
    }

    @Override
    public boolean isNew() {
        return _mailNotificationMaster.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _mailNotificationMaster.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _mailNotificationMaster.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _mailNotificationMaster.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _mailNotificationMaster.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _mailNotificationMaster.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _mailNotificationMaster.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _mailNotificationMaster.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _mailNotificationMaster.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _mailNotificationMaster.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _mailNotificationMaster.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new MailNotificationMasterWrapper((MailNotificationMaster) _mailNotificationMaster.clone());
    }

    @Override
    public int compareTo(MailNotificationMaster mailNotificationMaster) {
        return _mailNotificationMaster.compareTo(mailNotificationMaster);
    }

    @Override
    public int hashCode() {
        return _mailNotificationMaster.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<MailNotificationMaster> toCacheModel() {
        return _mailNotificationMaster.toCacheModel();
    }

    @Override
    public MailNotificationMaster toEscapedModel() {
        return new MailNotificationMasterWrapper(_mailNotificationMaster.toEscapedModel());
    }

    @Override
    public MailNotificationMaster toUnescapedModel() {
        return new MailNotificationMasterWrapper(_mailNotificationMaster.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _mailNotificationMaster.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _mailNotificationMaster.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _mailNotificationMaster.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof MailNotificationMasterWrapper)) {
            return false;
        }

        MailNotificationMasterWrapper mailNotificationMasterWrapper = (MailNotificationMasterWrapper) obj;

        if (Validator.equals(_mailNotificationMaster,
                    mailNotificationMasterWrapper._mailNotificationMaster)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public MailNotificationMaster getWrappedMailNotificationMaster() {
        return _mailNotificationMaster;
    }

    @Override
    public MailNotificationMaster getWrappedModel() {
        return _mailNotificationMaster;
    }

    @Override
    public void resetOriginalValues() {
        _mailNotificationMaster.resetOriginalValues();
    }
}
