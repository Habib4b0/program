package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link GcmCompanyLink}.
 * </p>
 *
 * @author
 * @see GcmCompanyLink
 * @generated
 */
public class GcmCompanyLinkWrapper implements GcmCompanyLink,
    ModelWrapper<GcmCompanyLink> {
    private GcmCompanyLink _gcmCompanyLink;

    public GcmCompanyLinkWrapper(GcmCompanyLink gcmCompanyLink) {
        _gcmCompanyLink = gcmCompanyLink;
    }

    @Override
    public Class<?> getModelClass() {
        return GcmCompanyLink.class;
    }

    @Override
    public String getModelClassName() {
        return GcmCompanyLink.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("checkRecord", getCheckRecord());
        attributes.put("userId", getUserId());
        attributes.put("companyNo", getCompanyNo());
        attributes.put("companyId", getCompanyId());
        attributes.put("gcmCompanyLinkSid", getGcmCompanyLinkSid());
        attributes.put("sessionId", getSessionId());
        attributes.put("companyName", getCompanyName());
        attributes.put("linkId", getLinkId());
        attributes.put("companyMasterSid", getCompanyMasterSid());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Boolean checkRecord = (Boolean) attributes.get("checkRecord");

        if (checkRecord != null) {
            setCheckRecord(checkRecord);
        }

        Integer userId = (Integer) attributes.get("userId");

        if (userId != null) {
            setUserId(userId);
        }

        String companyNo = (String) attributes.get("companyNo");

        if (companyNo != null) {
            setCompanyNo(companyNo);
        }

        String companyId = (String) attributes.get("companyId");

        if (companyId != null) {
            setCompanyId(companyId);
        }

        Integer gcmCompanyLinkSid = (Integer) attributes.get(
                "gcmCompanyLinkSid");

        if (gcmCompanyLinkSid != null) {
            setGcmCompanyLinkSid(gcmCompanyLinkSid);
        }

        String sessionId = (String) attributes.get("sessionId");

        if (sessionId != null) {
            setSessionId(sessionId);
        }

        String companyName = (String) attributes.get("companyName");

        if (companyName != null) {
            setCompanyName(companyName);
        }

        String linkId = (String) attributes.get("linkId");

        if (linkId != null) {
            setLinkId(linkId);
        }

        Integer companyMasterSid = (Integer) attributes.get("companyMasterSid");

        if (companyMasterSid != null) {
            setCompanyMasterSid(companyMasterSid);
        }
    }

    /**
    * Returns the primary key of this gcm company link.
    *
    * @return the primary key of this gcm company link
    */
    @Override
    public int getPrimaryKey() {
        return _gcmCompanyLink.getPrimaryKey();
    }

    /**
    * Sets the primary key of this gcm company link.
    *
    * @param primaryKey the primary key of this gcm company link
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _gcmCompanyLink.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the check record of this gcm company link.
    *
    * @return the check record of this gcm company link
    */
    @Override
    public boolean getCheckRecord() {
        return _gcmCompanyLink.getCheckRecord();
    }

    /**
    * Returns <code>true</code> if this gcm company link is check record.
    *
    * @return <code>true</code> if this gcm company link is check record; <code>false</code> otherwise
    */
    @Override
    public boolean isCheckRecord() {
        return _gcmCompanyLink.isCheckRecord();
    }

    /**
    * Sets whether this gcm company link is check record.
    *
    * @param checkRecord the check record of this gcm company link
    */
    @Override
    public void setCheckRecord(boolean checkRecord) {
        _gcmCompanyLink.setCheckRecord(checkRecord);
    }

    /**
    * Returns the user ID of this gcm company link.
    *
    * @return the user ID of this gcm company link
    */
    @Override
    public int getUserId() {
        return _gcmCompanyLink.getUserId();
    }

    /**
    * Sets the user ID of this gcm company link.
    *
    * @param userId the user ID of this gcm company link
    */
    @Override
    public void setUserId(int userId) {
        _gcmCompanyLink.setUserId(userId);
    }

    /**
    * Returns the company no of this gcm company link.
    *
    * @return the company no of this gcm company link
    */
    @Override
    public java.lang.String getCompanyNo() {
        return _gcmCompanyLink.getCompanyNo();
    }

    /**
    * Sets the company no of this gcm company link.
    *
    * @param companyNo the company no of this gcm company link
    */
    @Override
    public void setCompanyNo(java.lang.String companyNo) {
        _gcmCompanyLink.setCompanyNo(companyNo);
    }

    /**
    * Returns the company ID of this gcm company link.
    *
    * @return the company ID of this gcm company link
    */
    @Override
    public java.lang.String getCompanyId() {
        return _gcmCompanyLink.getCompanyId();
    }

    /**
    * Sets the company ID of this gcm company link.
    *
    * @param companyId the company ID of this gcm company link
    */
    @Override
    public void setCompanyId(java.lang.String companyId) {
        _gcmCompanyLink.setCompanyId(companyId);
    }

    /**
    * Returns the gcm company link sid of this gcm company link.
    *
    * @return the gcm company link sid of this gcm company link
    */
    @Override
    public int getGcmCompanyLinkSid() {
        return _gcmCompanyLink.getGcmCompanyLinkSid();
    }

    /**
    * Sets the gcm company link sid of this gcm company link.
    *
    * @param gcmCompanyLinkSid the gcm company link sid of this gcm company link
    */
    @Override
    public void setGcmCompanyLinkSid(int gcmCompanyLinkSid) {
        _gcmCompanyLink.setGcmCompanyLinkSid(gcmCompanyLinkSid);
    }

    /**
    * Returns the session ID of this gcm company link.
    *
    * @return the session ID of this gcm company link
    */
    @Override
    public java.lang.String getSessionId() {
        return _gcmCompanyLink.getSessionId();
    }

    /**
    * Sets the session ID of this gcm company link.
    *
    * @param sessionId the session ID of this gcm company link
    */
    @Override
    public void setSessionId(java.lang.String sessionId) {
        _gcmCompanyLink.setSessionId(sessionId);
    }

    /**
    * Returns the company name of this gcm company link.
    *
    * @return the company name of this gcm company link
    */
    @Override
    public java.lang.String getCompanyName() {
        return _gcmCompanyLink.getCompanyName();
    }

    /**
    * Sets the company name of this gcm company link.
    *
    * @param companyName the company name of this gcm company link
    */
    @Override
    public void setCompanyName(java.lang.String companyName) {
        _gcmCompanyLink.setCompanyName(companyName);
    }

    /**
    * Returns the link ID of this gcm company link.
    *
    * @return the link ID of this gcm company link
    */
    @Override
    public java.lang.String getLinkId() {
        return _gcmCompanyLink.getLinkId();
    }

    /**
    * Sets the link ID of this gcm company link.
    *
    * @param linkId the link ID of this gcm company link
    */
    @Override
    public void setLinkId(java.lang.String linkId) {
        _gcmCompanyLink.setLinkId(linkId);
    }

    /**
    * Returns the company master sid of this gcm company link.
    *
    * @return the company master sid of this gcm company link
    */
    @Override
    public int getCompanyMasterSid() {
        return _gcmCompanyLink.getCompanyMasterSid();
    }

    /**
    * Sets the company master sid of this gcm company link.
    *
    * @param companyMasterSid the company master sid of this gcm company link
    */
    @Override
    public void setCompanyMasterSid(int companyMasterSid) {
        _gcmCompanyLink.setCompanyMasterSid(companyMasterSid);
    }

    @Override
    public boolean isNew() {
        return _gcmCompanyLink.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _gcmCompanyLink.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _gcmCompanyLink.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _gcmCompanyLink.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _gcmCompanyLink.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _gcmCompanyLink.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _gcmCompanyLink.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _gcmCompanyLink.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _gcmCompanyLink.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _gcmCompanyLink.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _gcmCompanyLink.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new GcmCompanyLinkWrapper((GcmCompanyLink) _gcmCompanyLink.clone());
    }

    @Override
    public int compareTo(GcmCompanyLink gcmCompanyLink) {
        return _gcmCompanyLink.compareTo(gcmCompanyLink);
    }

    @Override
    public int hashCode() {
        return _gcmCompanyLink.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<GcmCompanyLink> toCacheModel() {
        return _gcmCompanyLink.toCacheModel();
    }

    @Override
    public GcmCompanyLink toEscapedModel() {
        return new GcmCompanyLinkWrapper(_gcmCompanyLink.toEscapedModel());
    }

    @Override
    public GcmCompanyLink toUnescapedModel() {
        return new GcmCompanyLinkWrapper(_gcmCompanyLink.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _gcmCompanyLink.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _gcmCompanyLink.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _gcmCompanyLink.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof GcmCompanyLinkWrapper)) {
            return false;
        }

        GcmCompanyLinkWrapper gcmCompanyLinkWrapper = (GcmCompanyLinkWrapper) obj;

        if (Validator.equals(_gcmCompanyLink,
                    gcmCompanyLinkWrapper._gcmCompanyLink)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public GcmCompanyLink getWrappedGcmCompanyLink() {
        return _gcmCompanyLink;
    }

    @Override
    public GcmCompanyLink getWrappedModel() {
        return _gcmCompanyLink;
    }

    @Override
    public void resetOriginalValues() {
        _gcmCompanyLink.resetOriginalValues();
    }
}
