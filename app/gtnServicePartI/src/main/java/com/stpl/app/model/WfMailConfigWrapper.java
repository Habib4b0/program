package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link WfMailConfig}.
 * </p>
 *
 * @author
 * @see WfMailConfig
 * @generated
 */
public class WfMailConfigWrapper implements WfMailConfig,
    ModelWrapper<WfMailConfig> {
    private WfMailConfig _wfMailConfig;

    public WfMailConfigWrapper(WfMailConfig wfMailConfig) {
        _wfMailConfig = wfMailConfig;
    }

    @Override
    public Class<?> getModelClass() {
        return WfMailConfig.class;
    }

    @Override
    public String getModelClassName() {
        return WfMailConfig.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("smtpFlag", getSmtpFlag());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("emailAddress", getEmailAddress());
        attributes.put("password", getPassword());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("wfMailConfigSid", getWfMailConfigSid());
        attributes.put("hostName", getHostName());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("portNumber", getPortNumber());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("inboundStatus", getInboundStatus());
        attributes.put("testMailAddress", getTestMailAddress());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String smtpFlag = (String) attributes.get("smtpFlag");

        if (smtpFlag != null) {
            setSmtpFlag(smtpFlag);
        }

        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        String emailAddress = (String) attributes.get("emailAddress");

        if (emailAddress != null) {
            setEmailAddress(emailAddress);
        }

        String password = (String) attributes.get("password");

        if (password != null) {
            setPassword(password);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        Integer wfMailConfigSid = (Integer) attributes.get("wfMailConfigSid");

        if (wfMailConfigSid != null) {
            setWfMailConfigSid(wfMailConfigSid);
        }

        String hostName = (String) attributes.get("hostName");

        if (hostName != null) {
            setHostName(hostName);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        String portNumber = (String) attributes.get("portNumber");

        if (portNumber != null) {
            setPortNumber(portNumber);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        String inboundStatus = (String) attributes.get("inboundStatus");

        if (inboundStatus != null) {
            setInboundStatus(inboundStatus);
        }

        String testMailAddress = (String) attributes.get("testMailAddress");

        if (testMailAddress != null) {
            setTestMailAddress(testMailAddress);
        }
    }

    /**
    * Returns the primary key of this wf mail config.
    *
    * @return the primary key of this wf mail config
    */
    @Override
    public int getPrimaryKey() {
        return _wfMailConfig.getPrimaryKey();
    }

    /**
    * Sets the primary key of this wf mail config.
    *
    * @param primaryKey the primary key of this wf mail config
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _wfMailConfig.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the smtp flag of this wf mail config.
    *
    * @return the smtp flag of this wf mail config
    */
    @Override
    public java.lang.String getSmtpFlag() {
        return _wfMailConfig.getSmtpFlag();
    }

    /**
    * Sets the smtp flag of this wf mail config.
    *
    * @param smtpFlag the smtp flag of this wf mail config
    */
    @Override
    public void setSmtpFlag(java.lang.String smtpFlag) {
        _wfMailConfig.setSmtpFlag(smtpFlag);
    }

    /**
    * Returns the created by of this wf mail config.
    *
    * @return the created by of this wf mail config
    */
    @Override
    public int getCreatedBy() {
        return _wfMailConfig.getCreatedBy();
    }

    /**
    * Sets the created by of this wf mail config.
    *
    * @param createdBy the created by of this wf mail config
    */
    @Override
    public void setCreatedBy(int createdBy) {
        _wfMailConfig.setCreatedBy(createdBy);
    }

    /**
    * Returns the email address of this wf mail config.
    *
    * @return the email address of this wf mail config
    */
    @Override
    public java.lang.String getEmailAddress() {
        return _wfMailConfig.getEmailAddress();
    }

    /**
    * Sets the email address of this wf mail config.
    *
    * @param emailAddress the email address of this wf mail config
    */
    @Override
    public void setEmailAddress(java.lang.String emailAddress) {
        _wfMailConfig.setEmailAddress(emailAddress);
    }

    /**
    * Returns the password of this wf mail config.
    *
    * @return the password of this wf mail config
    */
    @Override
    public java.lang.String getPassword() {
        return _wfMailConfig.getPassword();
    }

    /**
    * Sets the password of this wf mail config.
    *
    * @param password the password of this wf mail config
    */
    @Override
    public void setPassword(java.lang.String password) {
        _wfMailConfig.setPassword(password);
    }

    /**
    * Returns the modified by of this wf mail config.
    *
    * @return the modified by of this wf mail config
    */
    @Override
    public int getModifiedBy() {
        return _wfMailConfig.getModifiedBy();
    }

    /**
    * Sets the modified by of this wf mail config.
    *
    * @param modifiedBy the modified by of this wf mail config
    */
    @Override
    public void setModifiedBy(int modifiedBy) {
        _wfMailConfig.setModifiedBy(modifiedBy);
    }

    /**
    * Returns the wf mail config sid of this wf mail config.
    *
    * @return the wf mail config sid of this wf mail config
    */
    @Override
    public int getWfMailConfigSid() {
        return _wfMailConfig.getWfMailConfigSid();
    }

    /**
    * Sets the wf mail config sid of this wf mail config.
    *
    * @param wfMailConfigSid the wf mail config sid of this wf mail config
    */
    @Override
    public void setWfMailConfigSid(int wfMailConfigSid) {
        _wfMailConfig.setWfMailConfigSid(wfMailConfigSid);
    }

    /**
    * Returns the host name of this wf mail config.
    *
    * @return the host name of this wf mail config
    */
    @Override
    public java.lang.String getHostName() {
        return _wfMailConfig.getHostName();
    }

    /**
    * Sets the host name of this wf mail config.
    *
    * @param hostName the host name of this wf mail config
    */
    @Override
    public void setHostName(java.lang.String hostName) {
        _wfMailConfig.setHostName(hostName);
    }

    /**
    * Returns the created date of this wf mail config.
    *
    * @return the created date of this wf mail config
    */
    @Override
    public java.util.Date getCreatedDate() {
        return _wfMailConfig.getCreatedDate();
    }

    /**
    * Sets the created date of this wf mail config.
    *
    * @param createdDate the created date of this wf mail config
    */
    @Override
    public void setCreatedDate(java.util.Date createdDate) {
        _wfMailConfig.setCreatedDate(createdDate);
    }

    /**
    * Returns the port number of this wf mail config.
    *
    * @return the port number of this wf mail config
    */
    @Override
    public java.lang.String getPortNumber() {
        return _wfMailConfig.getPortNumber();
    }

    /**
    * Sets the port number of this wf mail config.
    *
    * @param portNumber the port number of this wf mail config
    */
    @Override
    public void setPortNumber(java.lang.String portNumber) {
        _wfMailConfig.setPortNumber(portNumber);
    }

    /**
    * Returns the modified date of this wf mail config.
    *
    * @return the modified date of this wf mail config
    */
    @Override
    public java.util.Date getModifiedDate() {
        return _wfMailConfig.getModifiedDate();
    }

    /**
    * Sets the modified date of this wf mail config.
    *
    * @param modifiedDate the modified date of this wf mail config
    */
    @Override
    public void setModifiedDate(java.util.Date modifiedDate) {
        _wfMailConfig.setModifiedDate(modifiedDate);
    }

    /**
    * Returns the inbound status of this wf mail config.
    *
    * @return the inbound status of this wf mail config
    */
    @Override
    public java.lang.String getInboundStatus() {
        return _wfMailConfig.getInboundStatus();
    }

    /**
    * Sets the inbound status of this wf mail config.
    *
    * @param inboundStatus the inbound status of this wf mail config
    */
    @Override
    public void setInboundStatus(java.lang.String inboundStatus) {
        _wfMailConfig.setInboundStatus(inboundStatus);
    }

    /**
    * Returns the test mail address of this wf mail config.
    *
    * @return the test mail address of this wf mail config
    */
    @Override
    public java.lang.String getTestMailAddress() {
        return _wfMailConfig.getTestMailAddress();
    }

    /**
    * Sets the test mail address of this wf mail config.
    *
    * @param testMailAddress the test mail address of this wf mail config
    */
    @Override
    public void setTestMailAddress(java.lang.String testMailAddress) {
        _wfMailConfig.setTestMailAddress(testMailAddress);
    }

    @Override
    public boolean isNew() {
        return _wfMailConfig.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _wfMailConfig.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _wfMailConfig.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _wfMailConfig.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _wfMailConfig.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _wfMailConfig.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _wfMailConfig.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _wfMailConfig.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _wfMailConfig.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _wfMailConfig.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _wfMailConfig.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new WfMailConfigWrapper((WfMailConfig) _wfMailConfig.clone());
    }

    @Override
    public int compareTo(WfMailConfig wfMailConfig) {
        return _wfMailConfig.compareTo(wfMailConfig);
    }

    @Override
    public int hashCode() {
        return _wfMailConfig.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<WfMailConfig> toCacheModel() {
        return _wfMailConfig.toCacheModel();
    }

    @Override
    public WfMailConfig toEscapedModel() {
        return new WfMailConfigWrapper(_wfMailConfig.toEscapedModel());
    }

    @Override
    public WfMailConfig toUnescapedModel() {
        return new WfMailConfigWrapper(_wfMailConfig.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _wfMailConfig.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _wfMailConfig.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _wfMailConfig.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof WfMailConfigWrapper)) {
            return false;
        }

        WfMailConfigWrapper wfMailConfigWrapper = (WfMailConfigWrapper) obj;

        if (Validator.equals(_wfMailConfig, wfMailConfigWrapper._wfMailConfig)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public WfMailConfig getWrappedWfMailConfig() {
        return _wfMailConfig;
    }

    @Override
    public WfMailConfig getWrappedModel() {
        return _wfMailConfig;
    }

    @Override
    public void resetOriginalValues() {
        _wfMailConfig.resetOriginalValues();
    }
}
