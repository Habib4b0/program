package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link IvldGlCostCenter}.
 * </p>
 *
 * @author
 * @see IvldGlCostCenter
 * @generated
 */
public class IvldGlCostCenterWrapper implements IvldGlCostCenter,
    ModelWrapper<IvldGlCostCenter> {
    private IvldGlCostCenter _ivldGlCostCenter;

    public IvldGlCostCenterWrapper(IvldGlCostCenter ivldGlCostCenter) {
        _ivldGlCostCenter = ivldGlCostCenter;
    }

    @Override
    public Class<?> getModelClass() {
        return IvldGlCostCenter.class;
    }

    @Override
    public String getModelClassName() {
        return IvldGlCostCenter.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("reasonForFailure", getReasonForFailure());
        attributes.put("glCostCenterIntfid", getGlCostCenterIntfid());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("companyCostCenter", getCompanyCostCenter());
        attributes.put("uploadDate", getUploadDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("source", getSource());
        attributes.put("batchId", getBatchId());
        attributes.put("addChgDelIndicator", getAddChgDelIndicator());
        attributes.put("ivldGlCostCenterSid", getIvldGlCostCenterSid());
        attributes.put("errorField", getErrorField());
        attributes.put("errorCode", getErrorCode());
        attributes.put("intfInsertedDate", getIntfInsertedDate());
        attributes.put("companyCode", getCompanyCode());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("reprocessedFlag", getReprocessedFlag());
        attributes.put("ndc8", getNdc8());
        attributes.put("checkRecord", getCheckRecord());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String reasonForFailure = (String) attributes.get("reasonForFailure");

        if (reasonForFailure != null) {
            setReasonForFailure(reasonForFailure);
        }

        String glCostCenterIntfid = (String) attributes.get(
                "glCostCenterIntfid");

        if (glCostCenterIntfid != null) {
            setGlCostCenterIntfid(glCostCenterIntfid);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        String companyCostCenter = (String) attributes.get("companyCostCenter");

        if (companyCostCenter != null) {
            setCompanyCostCenter(companyCostCenter);
        }

        String uploadDate = (String) attributes.get("uploadDate");

        if (uploadDate != null) {
            setUploadDate(uploadDate);
        }

        String createdBy = (String) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        String source = (String) attributes.get("source");

        if (source != null) {
            setSource(source);
        }

        String batchId = (String) attributes.get("batchId");

        if (batchId != null) {
            setBatchId(batchId);
        }

        String addChgDelIndicator = (String) attributes.get(
                "addChgDelIndicator");

        if (addChgDelIndicator != null) {
            setAddChgDelIndicator(addChgDelIndicator);
        }

        Integer ivldGlCostCenterSid = (Integer) attributes.get(
                "ivldGlCostCenterSid");

        if (ivldGlCostCenterSid != null) {
            setIvldGlCostCenterSid(ivldGlCostCenterSid);
        }

        String errorField = (String) attributes.get("errorField");

        if (errorField != null) {
            setErrorField(errorField);
        }

        String errorCode = (String) attributes.get("errorCode");

        if (errorCode != null) {
            setErrorCode(errorCode);
        }

        Date intfInsertedDate = (Date) attributes.get("intfInsertedDate");

        if (intfInsertedDate != null) {
            setIntfInsertedDate(intfInsertedDate);
        }

        String companyCode = (String) attributes.get("companyCode");

        if (companyCode != null) {
            setCompanyCode(companyCode);
        }

        String modifiedBy = (String) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        String reprocessedFlag = (String) attributes.get("reprocessedFlag");

        if (reprocessedFlag != null) {
            setReprocessedFlag(reprocessedFlag);
        }

        String ndc8 = (String) attributes.get("ndc8");

        if (ndc8 != null) {
            setNdc8(ndc8);
        }

        Boolean checkRecord = (Boolean) attributes.get("checkRecord");

        if (checkRecord != null) {
            setCheckRecord(checkRecord);
        }
    }

    /**
    * Returns the primary key of this ivld gl cost center.
    *
    * @return the primary key of this ivld gl cost center
    */
    @Override
    public int getPrimaryKey() {
        return _ivldGlCostCenter.getPrimaryKey();
    }

    /**
    * Sets the primary key of this ivld gl cost center.
    *
    * @param primaryKey the primary key of this ivld gl cost center
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _ivldGlCostCenter.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the reason for failure of this ivld gl cost center.
    *
    * @return the reason for failure of this ivld gl cost center
    */
    @Override
    public java.lang.String getReasonForFailure() {
        return _ivldGlCostCenter.getReasonForFailure();
    }

    /**
    * Sets the reason for failure of this ivld gl cost center.
    *
    * @param reasonForFailure the reason for failure of this ivld gl cost center
    */
    @Override
    public void setReasonForFailure(java.lang.String reasonForFailure) {
        _ivldGlCostCenter.setReasonForFailure(reasonForFailure);
    }

    /**
    * Returns the gl cost center intfid of this ivld gl cost center.
    *
    * @return the gl cost center intfid of this ivld gl cost center
    */
    @Override
    public java.lang.String getGlCostCenterIntfid() {
        return _ivldGlCostCenter.getGlCostCenterIntfid();
    }

    /**
    * Sets the gl cost center intfid of this ivld gl cost center.
    *
    * @param glCostCenterIntfid the gl cost center intfid of this ivld gl cost center
    */
    @Override
    public void setGlCostCenterIntfid(java.lang.String glCostCenterIntfid) {
        _ivldGlCostCenter.setGlCostCenterIntfid(glCostCenterIntfid);
    }

    /**
    * Returns the modified date of this ivld gl cost center.
    *
    * @return the modified date of this ivld gl cost center
    */
    @Override
    public java.util.Date getModifiedDate() {
        return _ivldGlCostCenter.getModifiedDate();
    }

    /**
    * Sets the modified date of this ivld gl cost center.
    *
    * @param modifiedDate the modified date of this ivld gl cost center
    */
    @Override
    public void setModifiedDate(java.util.Date modifiedDate) {
        _ivldGlCostCenter.setModifiedDate(modifiedDate);
    }

    /**
    * Returns the company cost center of this ivld gl cost center.
    *
    * @return the company cost center of this ivld gl cost center
    */
    @Override
    public java.lang.String getCompanyCostCenter() {
        return _ivldGlCostCenter.getCompanyCostCenter();
    }

    /**
    * Sets the company cost center of this ivld gl cost center.
    *
    * @param companyCostCenter the company cost center of this ivld gl cost center
    */
    @Override
    public void setCompanyCostCenter(java.lang.String companyCostCenter) {
        _ivldGlCostCenter.setCompanyCostCenter(companyCostCenter);
    }

    /**
    * Returns the upload date of this ivld gl cost center.
    *
    * @return the upload date of this ivld gl cost center
    */
    @Override
    public java.lang.String getUploadDate() {
        return _ivldGlCostCenter.getUploadDate();
    }

    /**
    * Sets the upload date of this ivld gl cost center.
    *
    * @param uploadDate the upload date of this ivld gl cost center
    */
    @Override
    public void setUploadDate(java.lang.String uploadDate) {
        _ivldGlCostCenter.setUploadDate(uploadDate);
    }

    /**
    * Returns the created by of this ivld gl cost center.
    *
    * @return the created by of this ivld gl cost center
    */
    @Override
    public java.lang.String getCreatedBy() {
        return _ivldGlCostCenter.getCreatedBy();
    }

    /**
    * Sets the created by of this ivld gl cost center.
    *
    * @param createdBy the created by of this ivld gl cost center
    */
    @Override
    public void setCreatedBy(java.lang.String createdBy) {
        _ivldGlCostCenter.setCreatedBy(createdBy);
    }

    /**
    * Returns the created date of this ivld gl cost center.
    *
    * @return the created date of this ivld gl cost center
    */
    @Override
    public java.util.Date getCreatedDate() {
        return _ivldGlCostCenter.getCreatedDate();
    }

    /**
    * Sets the created date of this ivld gl cost center.
    *
    * @param createdDate the created date of this ivld gl cost center
    */
    @Override
    public void setCreatedDate(java.util.Date createdDate) {
        _ivldGlCostCenter.setCreatedDate(createdDate);
    }

    /**
    * Returns the source of this ivld gl cost center.
    *
    * @return the source of this ivld gl cost center
    */
    @Override
    public java.lang.String getSource() {
        return _ivldGlCostCenter.getSource();
    }

    /**
    * Sets the source of this ivld gl cost center.
    *
    * @param source the source of this ivld gl cost center
    */
    @Override
    public void setSource(java.lang.String source) {
        _ivldGlCostCenter.setSource(source);
    }

    /**
    * Returns the batch ID of this ivld gl cost center.
    *
    * @return the batch ID of this ivld gl cost center
    */
    @Override
    public java.lang.String getBatchId() {
        return _ivldGlCostCenter.getBatchId();
    }

    /**
    * Sets the batch ID of this ivld gl cost center.
    *
    * @param batchId the batch ID of this ivld gl cost center
    */
    @Override
    public void setBatchId(java.lang.String batchId) {
        _ivldGlCostCenter.setBatchId(batchId);
    }

    /**
    * Returns the add chg del indicator of this ivld gl cost center.
    *
    * @return the add chg del indicator of this ivld gl cost center
    */
    @Override
    public java.lang.String getAddChgDelIndicator() {
        return _ivldGlCostCenter.getAddChgDelIndicator();
    }

    /**
    * Sets the add chg del indicator of this ivld gl cost center.
    *
    * @param addChgDelIndicator the add chg del indicator of this ivld gl cost center
    */
    @Override
    public void setAddChgDelIndicator(java.lang.String addChgDelIndicator) {
        _ivldGlCostCenter.setAddChgDelIndicator(addChgDelIndicator);
    }

    /**
    * Returns the ivld gl cost center sid of this ivld gl cost center.
    *
    * @return the ivld gl cost center sid of this ivld gl cost center
    */
    @Override
    public int getIvldGlCostCenterSid() {
        return _ivldGlCostCenter.getIvldGlCostCenterSid();
    }

    /**
    * Sets the ivld gl cost center sid of this ivld gl cost center.
    *
    * @param ivldGlCostCenterSid the ivld gl cost center sid of this ivld gl cost center
    */
    @Override
    public void setIvldGlCostCenterSid(int ivldGlCostCenterSid) {
        _ivldGlCostCenter.setIvldGlCostCenterSid(ivldGlCostCenterSid);
    }

    /**
    * Returns the error field of this ivld gl cost center.
    *
    * @return the error field of this ivld gl cost center
    */
    @Override
    public java.lang.String getErrorField() {
        return _ivldGlCostCenter.getErrorField();
    }

    /**
    * Sets the error field of this ivld gl cost center.
    *
    * @param errorField the error field of this ivld gl cost center
    */
    @Override
    public void setErrorField(java.lang.String errorField) {
        _ivldGlCostCenter.setErrorField(errorField);
    }

    /**
    * Returns the error code of this ivld gl cost center.
    *
    * @return the error code of this ivld gl cost center
    */
    @Override
    public java.lang.String getErrorCode() {
        return _ivldGlCostCenter.getErrorCode();
    }

    /**
    * Sets the error code of this ivld gl cost center.
    *
    * @param errorCode the error code of this ivld gl cost center
    */
    @Override
    public void setErrorCode(java.lang.String errorCode) {
        _ivldGlCostCenter.setErrorCode(errorCode);
    }

    /**
    * Returns the intf inserted date of this ivld gl cost center.
    *
    * @return the intf inserted date of this ivld gl cost center
    */
    @Override
    public java.util.Date getIntfInsertedDate() {
        return _ivldGlCostCenter.getIntfInsertedDate();
    }

    /**
    * Sets the intf inserted date of this ivld gl cost center.
    *
    * @param intfInsertedDate the intf inserted date of this ivld gl cost center
    */
    @Override
    public void setIntfInsertedDate(java.util.Date intfInsertedDate) {
        _ivldGlCostCenter.setIntfInsertedDate(intfInsertedDate);
    }

    /**
    * Returns the company code of this ivld gl cost center.
    *
    * @return the company code of this ivld gl cost center
    */
    @Override
    public java.lang.String getCompanyCode() {
        return _ivldGlCostCenter.getCompanyCode();
    }

    /**
    * Sets the company code of this ivld gl cost center.
    *
    * @param companyCode the company code of this ivld gl cost center
    */
    @Override
    public void setCompanyCode(java.lang.String companyCode) {
        _ivldGlCostCenter.setCompanyCode(companyCode);
    }

    /**
    * Returns the modified by of this ivld gl cost center.
    *
    * @return the modified by of this ivld gl cost center
    */
    @Override
    public java.lang.String getModifiedBy() {
        return _ivldGlCostCenter.getModifiedBy();
    }

    /**
    * Sets the modified by of this ivld gl cost center.
    *
    * @param modifiedBy the modified by of this ivld gl cost center
    */
    @Override
    public void setModifiedBy(java.lang.String modifiedBy) {
        _ivldGlCostCenter.setModifiedBy(modifiedBy);
    }

    /**
    * Returns the reprocessed flag of this ivld gl cost center.
    *
    * @return the reprocessed flag of this ivld gl cost center
    */
    @Override
    public java.lang.String getReprocessedFlag() {
        return _ivldGlCostCenter.getReprocessedFlag();
    }

    /**
    * Sets the reprocessed flag of this ivld gl cost center.
    *
    * @param reprocessedFlag the reprocessed flag of this ivld gl cost center
    */
    @Override
    public void setReprocessedFlag(java.lang.String reprocessedFlag) {
        _ivldGlCostCenter.setReprocessedFlag(reprocessedFlag);
    }

    /**
    * Returns the ndc8 of this ivld gl cost center.
    *
    * @return the ndc8 of this ivld gl cost center
    */
    @Override
    public java.lang.String getNdc8() {
        return _ivldGlCostCenter.getNdc8();
    }

    /**
    * Sets the ndc8 of this ivld gl cost center.
    *
    * @param ndc8 the ndc8 of this ivld gl cost center
    */
    @Override
    public void setNdc8(java.lang.String ndc8) {
        _ivldGlCostCenter.setNdc8(ndc8);
    }

    /**
    * Returns the check record of this ivld gl cost center.
    *
    * @return the check record of this ivld gl cost center
    */
    @Override
    public boolean getCheckRecord() {
        return _ivldGlCostCenter.getCheckRecord();
    }

    /**
    * Returns <code>true</code> if this ivld gl cost center is check record.
    *
    * @return <code>true</code> if this ivld gl cost center is check record; <code>false</code> otherwise
    */
    @Override
    public boolean isCheckRecord() {
        return _ivldGlCostCenter.isCheckRecord();
    }

    /**
    * Sets whether this ivld gl cost center is check record.
    *
    * @param checkRecord the check record of this ivld gl cost center
    */
    @Override
    public void setCheckRecord(boolean checkRecord) {
        _ivldGlCostCenter.setCheckRecord(checkRecord);
    }

    @Override
    public boolean isNew() {
        return _ivldGlCostCenter.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _ivldGlCostCenter.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _ivldGlCostCenter.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _ivldGlCostCenter.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _ivldGlCostCenter.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _ivldGlCostCenter.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _ivldGlCostCenter.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _ivldGlCostCenter.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _ivldGlCostCenter.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _ivldGlCostCenter.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _ivldGlCostCenter.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new IvldGlCostCenterWrapper((IvldGlCostCenter) _ivldGlCostCenter.clone());
    }

    @Override
    public int compareTo(IvldGlCostCenter ivldGlCostCenter) {
        return _ivldGlCostCenter.compareTo(ivldGlCostCenter);
    }

    @Override
    public int hashCode() {
        return _ivldGlCostCenter.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<IvldGlCostCenter> toCacheModel() {
        return _ivldGlCostCenter.toCacheModel();
    }

    @Override
    public IvldGlCostCenter toEscapedModel() {
        return new IvldGlCostCenterWrapper(_ivldGlCostCenter.toEscapedModel());
    }

    @Override
    public IvldGlCostCenter toUnescapedModel() {
        return new IvldGlCostCenterWrapper(_ivldGlCostCenter.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _ivldGlCostCenter.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _ivldGlCostCenter.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _ivldGlCostCenter.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof IvldGlCostCenterWrapper)) {
            return false;
        }

        IvldGlCostCenterWrapper ivldGlCostCenterWrapper = (IvldGlCostCenterWrapper) obj;

        if (Validator.equals(_ivldGlCostCenter,
                    ivldGlCostCenterWrapper._ivldGlCostCenter)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public IvldGlCostCenter getWrappedIvldGlCostCenter() {
        return _ivldGlCostCenter;
    }

    @Override
    public IvldGlCostCenter getWrappedModel() {
        return _ivldGlCostCenter;
    }

    @Override
    public void resetOriginalValues() {
        _ivldGlCostCenter.resetOriginalValues();
    }
}
