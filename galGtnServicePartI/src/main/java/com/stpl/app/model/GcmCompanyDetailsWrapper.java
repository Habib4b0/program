package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link GcmCompanyDetails}.
 * </p>
 *
 * @author
 * @see GcmCompanyDetails
 * @generated
 */
public class GcmCompanyDetailsWrapper implements GcmCompanyDetails,
    ModelWrapper<GcmCompanyDetails> {
    private GcmCompanyDetails _gcmCompanyDetails;

    public GcmCompanyDetailsWrapper(GcmCompanyDetails gcmCompanyDetails) {
        _gcmCompanyDetails = gcmCompanyDetails;
    }

    @Override
    public Class<?> getModelClass() {
        return GcmCompanyDetails.class;
    }

    @Override
    public String getModelClassName() {
        return GcmCompanyDetails.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("checkRecord", getCheckRecord());
        attributes.put("userId", getUserId());
        attributes.put("moduleName", getModuleName());
        attributes.put("companyId", getCompanyId());
        attributes.put("cfpDetailsTradeClass", getCfpDetailsTradeClass());
        attributes.put("companyName", getCompanyName());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("gcmCompanyDetailsSid", getGcmCompanyDetailsSid());
        attributes.put("itemCfpDetailsSid", getItemCfpDetailsSid());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("companyStartDate", getCompanyStartDate());
        attributes.put("companyNo", getCompanyNo());
        attributes.put("companyStatus", getCompanyStatus());
        attributes.put("sessionId", getSessionId());
        attributes.put("companyEndDate", getCompanyEndDate());
        attributes.put("cfpDetailsStartDate", getCfpDetailsStartDate());
        attributes.put("operation", getOperation());
        attributes.put("cfpModelSid", getCfpModelSid());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("subModuleName", getSubModuleName());
        attributes.put("cfpDetailsEndDate", getCfpDetailsEndDate());
        attributes.put("companyStatusSid", getCompanyStatusSid());
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

        String moduleName = (String) attributes.get("moduleName");

        if (moduleName != null) {
            setModuleName(moduleName);
        }

        String companyId = (String) attributes.get("companyId");

        if (companyId != null) {
            setCompanyId(companyId);
        }

        String cfpDetailsTradeClass = (String) attributes.get(
                "cfpDetailsTradeClass");

        if (cfpDetailsTradeClass != null) {
            setCfpDetailsTradeClass(cfpDetailsTradeClass);
        }

        String companyName = (String) attributes.get("companyName");

        if (companyName != null) {
            setCompanyName(companyName);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        Integer gcmCompanyDetailsSid = (Integer) attributes.get(
                "gcmCompanyDetailsSid");

        if (gcmCompanyDetailsSid != null) {
            setGcmCompanyDetailsSid(gcmCompanyDetailsSid);
        }

        Integer itemCfpDetailsSid = (Integer) attributes.get(
                "itemCfpDetailsSid");

        if (itemCfpDetailsSid != null) {
            setItemCfpDetailsSid(itemCfpDetailsSid);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        Date companyStartDate = (Date) attributes.get("companyStartDate");

        if (companyStartDate != null) {
            setCompanyStartDate(companyStartDate);
        }

        String companyNo = (String) attributes.get("companyNo");

        if (companyNo != null) {
            setCompanyNo(companyNo);
        }

        String companyStatus = (String) attributes.get("companyStatus");

        if (companyStatus != null) {
            setCompanyStatus(companyStatus);
        }

        String sessionId = (String) attributes.get("sessionId");

        if (sessionId != null) {
            setSessionId(sessionId);
        }

        Date companyEndDate = (Date) attributes.get("companyEndDate");

        if (companyEndDate != null) {
            setCompanyEndDate(companyEndDate);
        }

        Date cfpDetailsStartDate = (Date) attributes.get("cfpDetailsStartDate");

        if (cfpDetailsStartDate != null) {
            setCfpDetailsStartDate(cfpDetailsStartDate);
        }

        String operation = (String) attributes.get("operation");

        if (operation != null) {
            setOperation(operation);
        }

        Integer cfpModelSid = (Integer) attributes.get("cfpModelSid");

        if (cfpModelSid != null) {
            setCfpModelSid(cfpModelSid);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        String subModuleName = (String) attributes.get("subModuleName");

        if (subModuleName != null) {
            setSubModuleName(subModuleName);
        }

        Date cfpDetailsEndDate = (Date) attributes.get("cfpDetailsEndDate");

        if (cfpDetailsEndDate != null) {
            setCfpDetailsEndDate(cfpDetailsEndDate);
        }

        Integer companyStatusSid = (Integer) attributes.get("companyStatusSid");

        if (companyStatusSid != null) {
            setCompanyStatusSid(companyStatusSid);
        }

        Integer companyMasterSid = (Integer) attributes.get("companyMasterSid");

        if (companyMasterSid != null) {
            setCompanyMasterSid(companyMasterSid);
        }
    }

    /**
    * Returns the primary key of this gcm company details.
    *
    * @return the primary key of this gcm company details
    */
    @Override
    public int getPrimaryKey() {
        return _gcmCompanyDetails.getPrimaryKey();
    }

    /**
    * Sets the primary key of this gcm company details.
    *
    * @param primaryKey the primary key of this gcm company details
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _gcmCompanyDetails.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the check record of this gcm company details.
    *
    * @return the check record of this gcm company details
    */
    @Override
    public boolean getCheckRecord() {
        return _gcmCompanyDetails.getCheckRecord();
    }

    /**
    * Returns <code>true</code> if this gcm company details is check record.
    *
    * @return <code>true</code> if this gcm company details is check record; <code>false</code> otherwise
    */
    @Override
    public boolean isCheckRecord() {
        return _gcmCompanyDetails.isCheckRecord();
    }

    /**
    * Sets whether this gcm company details is check record.
    *
    * @param checkRecord the check record of this gcm company details
    */
    @Override
    public void setCheckRecord(boolean checkRecord) {
        _gcmCompanyDetails.setCheckRecord(checkRecord);
    }

    /**
    * Returns the user ID of this gcm company details.
    *
    * @return the user ID of this gcm company details
    */
    @Override
    public int getUserId() {
        return _gcmCompanyDetails.getUserId();
    }

    /**
    * Sets the user ID of this gcm company details.
    *
    * @param userId the user ID of this gcm company details
    */
    @Override
    public void setUserId(int userId) {
        _gcmCompanyDetails.setUserId(userId);
    }

    /**
    * Returns the module name of this gcm company details.
    *
    * @return the module name of this gcm company details
    */
    @Override
    public java.lang.String getModuleName() {
        return _gcmCompanyDetails.getModuleName();
    }

    /**
    * Sets the module name of this gcm company details.
    *
    * @param moduleName the module name of this gcm company details
    */
    @Override
    public void setModuleName(java.lang.String moduleName) {
        _gcmCompanyDetails.setModuleName(moduleName);
    }

    /**
    * Returns the company ID of this gcm company details.
    *
    * @return the company ID of this gcm company details
    */
    @Override
    public java.lang.String getCompanyId() {
        return _gcmCompanyDetails.getCompanyId();
    }

    /**
    * Sets the company ID of this gcm company details.
    *
    * @param companyId the company ID of this gcm company details
    */
    @Override
    public void setCompanyId(java.lang.String companyId) {
        _gcmCompanyDetails.setCompanyId(companyId);
    }

    /**
    * Returns the cfp details trade class of this gcm company details.
    *
    * @return the cfp details trade class of this gcm company details
    */
    @Override
    public java.lang.String getCfpDetailsTradeClass() {
        return _gcmCompanyDetails.getCfpDetailsTradeClass();
    }

    /**
    * Sets the cfp details trade class of this gcm company details.
    *
    * @param cfpDetailsTradeClass the cfp details trade class of this gcm company details
    */
    @Override
    public void setCfpDetailsTradeClass(java.lang.String cfpDetailsTradeClass) {
        _gcmCompanyDetails.setCfpDetailsTradeClass(cfpDetailsTradeClass);
    }

    /**
    * Returns the company name of this gcm company details.
    *
    * @return the company name of this gcm company details
    */
    @Override
    public java.lang.String getCompanyName() {
        return _gcmCompanyDetails.getCompanyName();
    }

    /**
    * Sets the company name of this gcm company details.
    *
    * @param companyName the company name of this gcm company details
    */
    @Override
    public void setCompanyName(java.lang.String companyName) {
        _gcmCompanyDetails.setCompanyName(companyName);
    }

    /**
    * Returns the modified date of this gcm company details.
    *
    * @return the modified date of this gcm company details
    */
    @Override
    public java.util.Date getModifiedDate() {
        return _gcmCompanyDetails.getModifiedDate();
    }

    /**
    * Sets the modified date of this gcm company details.
    *
    * @param modifiedDate the modified date of this gcm company details
    */
    @Override
    public void setModifiedDate(java.util.Date modifiedDate) {
        _gcmCompanyDetails.setModifiedDate(modifiedDate);
    }

    /**
    * Returns the gcm company details sid of this gcm company details.
    *
    * @return the gcm company details sid of this gcm company details
    */
    @Override
    public int getGcmCompanyDetailsSid() {
        return _gcmCompanyDetails.getGcmCompanyDetailsSid();
    }

    /**
    * Sets the gcm company details sid of this gcm company details.
    *
    * @param gcmCompanyDetailsSid the gcm company details sid of this gcm company details
    */
    @Override
    public void setGcmCompanyDetailsSid(int gcmCompanyDetailsSid) {
        _gcmCompanyDetails.setGcmCompanyDetailsSid(gcmCompanyDetailsSid);
    }

    /**
    * Returns the item cfp details sid of this gcm company details.
    *
    * @return the item cfp details sid of this gcm company details
    */
    @Override
    public int getItemCfpDetailsSid() {
        return _gcmCompanyDetails.getItemCfpDetailsSid();
    }

    /**
    * Sets the item cfp details sid of this gcm company details.
    *
    * @param itemCfpDetailsSid the item cfp details sid of this gcm company details
    */
    @Override
    public void setItemCfpDetailsSid(int itemCfpDetailsSid) {
        _gcmCompanyDetails.setItemCfpDetailsSid(itemCfpDetailsSid);
    }

    /**
    * Returns the created date of this gcm company details.
    *
    * @return the created date of this gcm company details
    */
    @Override
    public java.util.Date getCreatedDate() {
        return _gcmCompanyDetails.getCreatedDate();
    }

    /**
    * Sets the created date of this gcm company details.
    *
    * @param createdDate the created date of this gcm company details
    */
    @Override
    public void setCreatedDate(java.util.Date createdDate) {
        _gcmCompanyDetails.setCreatedDate(createdDate);
    }

    /**
    * Returns the created by of this gcm company details.
    *
    * @return the created by of this gcm company details
    */
    @Override
    public int getCreatedBy() {
        return _gcmCompanyDetails.getCreatedBy();
    }

    /**
    * Sets the created by of this gcm company details.
    *
    * @param createdBy the created by of this gcm company details
    */
    @Override
    public void setCreatedBy(int createdBy) {
        _gcmCompanyDetails.setCreatedBy(createdBy);
    }

    /**
    * Returns the company start date of this gcm company details.
    *
    * @return the company start date of this gcm company details
    */
    @Override
    public java.util.Date getCompanyStartDate() {
        return _gcmCompanyDetails.getCompanyStartDate();
    }

    /**
    * Sets the company start date of this gcm company details.
    *
    * @param companyStartDate the company start date of this gcm company details
    */
    @Override
    public void setCompanyStartDate(java.util.Date companyStartDate) {
        _gcmCompanyDetails.setCompanyStartDate(companyStartDate);
    }

    /**
    * Returns the company no of this gcm company details.
    *
    * @return the company no of this gcm company details
    */
    @Override
    public java.lang.String getCompanyNo() {
        return _gcmCompanyDetails.getCompanyNo();
    }

    /**
    * Sets the company no of this gcm company details.
    *
    * @param companyNo the company no of this gcm company details
    */
    @Override
    public void setCompanyNo(java.lang.String companyNo) {
        _gcmCompanyDetails.setCompanyNo(companyNo);
    }

    /**
    * Returns the company status of this gcm company details.
    *
    * @return the company status of this gcm company details
    */
    @Override
    public java.lang.String getCompanyStatus() {
        return _gcmCompanyDetails.getCompanyStatus();
    }

    /**
    * Sets the company status of this gcm company details.
    *
    * @param companyStatus the company status of this gcm company details
    */
    @Override
    public void setCompanyStatus(java.lang.String companyStatus) {
        _gcmCompanyDetails.setCompanyStatus(companyStatus);
    }

    /**
    * Returns the session ID of this gcm company details.
    *
    * @return the session ID of this gcm company details
    */
    @Override
    public java.lang.String getSessionId() {
        return _gcmCompanyDetails.getSessionId();
    }

    /**
    * Sets the session ID of this gcm company details.
    *
    * @param sessionId the session ID of this gcm company details
    */
    @Override
    public void setSessionId(java.lang.String sessionId) {
        _gcmCompanyDetails.setSessionId(sessionId);
    }

    /**
    * Returns the company end date of this gcm company details.
    *
    * @return the company end date of this gcm company details
    */
    @Override
    public java.util.Date getCompanyEndDate() {
        return _gcmCompanyDetails.getCompanyEndDate();
    }

    /**
    * Sets the company end date of this gcm company details.
    *
    * @param companyEndDate the company end date of this gcm company details
    */
    @Override
    public void setCompanyEndDate(java.util.Date companyEndDate) {
        _gcmCompanyDetails.setCompanyEndDate(companyEndDate);
    }

    /**
    * Returns the cfp details start date of this gcm company details.
    *
    * @return the cfp details start date of this gcm company details
    */
    @Override
    public java.util.Date getCfpDetailsStartDate() {
        return _gcmCompanyDetails.getCfpDetailsStartDate();
    }

    /**
    * Sets the cfp details start date of this gcm company details.
    *
    * @param cfpDetailsStartDate the cfp details start date of this gcm company details
    */
    @Override
    public void setCfpDetailsStartDate(java.util.Date cfpDetailsStartDate) {
        _gcmCompanyDetails.setCfpDetailsStartDate(cfpDetailsStartDate);
    }

    /**
    * Returns the operation of this gcm company details.
    *
    * @return the operation of this gcm company details
    */
    @Override
    public java.lang.String getOperation() {
        return _gcmCompanyDetails.getOperation();
    }

    /**
    * Sets the operation of this gcm company details.
    *
    * @param operation the operation of this gcm company details
    */
    @Override
    public void setOperation(java.lang.String operation) {
        _gcmCompanyDetails.setOperation(operation);
    }

    /**
    * Returns the cfp model sid of this gcm company details.
    *
    * @return the cfp model sid of this gcm company details
    */
    @Override
    public int getCfpModelSid() {
        return _gcmCompanyDetails.getCfpModelSid();
    }

    /**
    * Sets the cfp model sid of this gcm company details.
    *
    * @param cfpModelSid the cfp model sid of this gcm company details
    */
    @Override
    public void setCfpModelSid(int cfpModelSid) {
        _gcmCompanyDetails.setCfpModelSid(cfpModelSid);
    }

    /**
    * Returns the modified by of this gcm company details.
    *
    * @return the modified by of this gcm company details
    */
    @Override
    public int getModifiedBy() {
        return _gcmCompanyDetails.getModifiedBy();
    }

    /**
    * Sets the modified by of this gcm company details.
    *
    * @param modifiedBy the modified by of this gcm company details
    */
    @Override
    public void setModifiedBy(int modifiedBy) {
        _gcmCompanyDetails.setModifiedBy(modifiedBy);
    }

    /**
    * Returns the sub module name of this gcm company details.
    *
    * @return the sub module name of this gcm company details
    */
    @Override
    public java.lang.String getSubModuleName() {
        return _gcmCompanyDetails.getSubModuleName();
    }

    /**
    * Sets the sub module name of this gcm company details.
    *
    * @param subModuleName the sub module name of this gcm company details
    */
    @Override
    public void setSubModuleName(java.lang.String subModuleName) {
        _gcmCompanyDetails.setSubModuleName(subModuleName);
    }

    /**
    * Returns the cfp details end date of this gcm company details.
    *
    * @return the cfp details end date of this gcm company details
    */
    @Override
    public java.util.Date getCfpDetailsEndDate() {
        return _gcmCompanyDetails.getCfpDetailsEndDate();
    }

    /**
    * Sets the cfp details end date of this gcm company details.
    *
    * @param cfpDetailsEndDate the cfp details end date of this gcm company details
    */
    @Override
    public void setCfpDetailsEndDate(java.util.Date cfpDetailsEndDate) {
        _gcmCompanyDetails.setCfpDetailsEndDate(cfpDetailsEndDate);
    }

    /**
    * Returns the company status sid of this gcm company details.
    *
    * @return the company status sid of this gcm company details
    */
    @Override
    public int getCompanyStatusSid() {
        return _gcmCompanyDetails.getCompanyStatusSid();
    }

    /**
    * Sets the company status sid of this gcm company details.
    *
    * @param companyStatusSid the company status sid of this gcm company details
    */
    @Override
    public void setCompanyStatusSid(int companyStatusSid) {
        _gcmCompanyDetails.setCompanyStatusSid(companyStatusSid);
    }

    /**
    * Returns the company master sid of this gcm company details.
    *
    * @return the company master sid of this gcm company details
    */
    @Override
    public int getCompanyMasterSid() {
        return _gcmCompanyDetails.getCompanyMasterSid();
    }

    /**
    * Sets the company master sid of this gcm company details.
    *
    * @param companyMasterSid the company master sid of this gcm company details
    */
    @Override
    public void setCompanyMasterSid(int companyMasterSid) {
        _gcmCompanyDetails.setCompanyMasterSid(companyMasterSid);
    }

    @Override
    public boolean isNew() {
        return _gcmCompanyDetails.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _gcmCompanyDetails.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _gcmCompanyDetails.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _gcmCompanyDetails.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _gcmCompanyDetails.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _gcmCompanyDetails.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _gcmCompanyDetails.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _gcmCompanyDetails.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _gcmCompanyDetails.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _gcmCompanyDetails.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _gcmCompanyDetails.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new GcmCompanyDetailsWrapper((GcmCompanyDetails) _gcmCompanyDetails.clone());
    }

    @Override
    public int compareTo(GcmCompanyDetails gcmCompanyDetails) {
        return _gcmCompanyDetails.compareTo(gcmCompanyDetails);
    }

    @Override
    public int hashCode() {
        return _gcmCompanyDetails.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<GcmCompanyDetails> toCacheModel() {
        return _gcmCompanyDetails.toCacheModel();
    }

    @Override
    public GcmCompanyDetails toEscapedModel() {
        return new GcmCompanyDetailsWrapper(_gcmCompanyDetails.toEscapedModel());
    }

    @Override
    public GcmCompanyDetails toUnescapedModel() {
        return new GcmCompanyDetailsWrapper(_gcmCompanyDetails.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _gcmCompanyDetails.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _gcmCompanyDetails.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _gcmCompanyDetails.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof GcmCompanyDetailsWrapper)) {
            return false;
        }

        GcmCompanyDetailsWrapper gcmCompanyDetailsWrapper = (GcmCompanyDetailsWrapper) obj;

        if (Validator.equals(_gcmCompanyDetails,
                    gcmCompanyDetailsWrapper._gcmCompanyDetails)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public GcmCompanyDetails getWrappedGcmCompanyDetails() {
        return _gcmCompanyDetails;
    }

    @Override
    public GcmCompanyDetails getWrappedModel() {
        return _gcmCompanyDetails;
    }

    @Override
    public void resetOriginalValues() {
        _gcmCompanyDetails.resetOriginalValues();
    }
}
