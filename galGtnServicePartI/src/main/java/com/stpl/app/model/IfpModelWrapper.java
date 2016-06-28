package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link IfpModel}.
 * </p>
 *
 * @author
 * @see IfpModel
 * @generated
 */
public class IfpModelWrapper implements IfpModel, ModelWrapper<IfpModel> {
    private IfpModel _ifpModel;

    public IfpModelWrapper(IfpModel ifpModel) {
        _ifpModel = ifpModel;
    }

    @Override
    public Class<?> getModelClass() {
        return IfpModel.class;
    }

    @Override
    public String getModelClassName() {
        return IfpModel.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("totalDollarCommitment", getTotalDollarCommitment());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("ifpStatus", getIfpStatus());
        attributes.put("totalVolumeCommitment", getTotalVolumeCommitment());
        attributes.put("batchId", getBatchId());
        attributes.put("internalNotes", getInternalNotes());
        attributes.put("ifpId", getIfpId());
        attributes.put("totalMarketshareCommitment",
            getTotalMarketshareCommitment());
        attributes.put("ifpCategory", getIfpCategory());
        attributes.put("parentIfpName", getParentIfpName());
        attributes.put("ifpEndDate", getIfpEndDate());
        attributes.put("ifpDesignation", getIfpDesignation());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("ifpStartDate", getIfpStartDate());
        attributes.put("parentIfpId", getParentIfpId());
        attributes.put("commitmentPeriod", getCommitmentPeriod());
        attributes.put("ifpType", getIfpType());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("ifpModelSid", getIfpModelSid());
        attributes.put("recordLockStatus", getRecordLockStatus());
        attributes.put("source", getSource());
        attributes.put("ifpName", getIfpName());
        attributes.put("ifpNo", getIfpNo());
        attributes.put("inboundStatus", getInboundStatus());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        String totalDollarCommitment = (String) attributes.get(
                "totalDollarCommitment");

        if (totalDollarCommitment != null) {
            setTotalDollarCommitment(totalDollarCommitment);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        Integer ifpStatus = (Integer) attributes.get("ifpStatus");

        if (ifpStatus != null) {
            setIfpStatus(ifpStatus);
        }

        String totalVolumeCommitment = (String) attributes.get(
                "totalVolumeCommitment");

        if (totalVolumeCommitment != null) {
            setTotalVolumeCommitment(totalVolumeCommitment);
        }

        String batchId = (String) attributes.get("batchId");

        if (batchId != null) {
            setBatchId(batchId);
        }

        String internalNotes = (String) attributes.get("internalNotes");

        if (internalNotes != null) {
            setInternalNotes(internalNotes);
        }

        String ifpId = (String) attributes.get("ifpId");

        if (ifpId != null) {
            setIfpId(ifpId);
        }

        String totalMarketshareCommitment = (String) attributes.get(
                "totalMarketshareCommitment");

        if (totalMarketshareCommitment != null) {
            setTotalMarketshareCommitment(totalMarketshareCommitment);
        }

        Integer ifpCategory = (Integer) attributes.get("ifpCategory");

        if (ifpCategory != null) {
            setIfpCategory(ifpCategory);
        }

        String parentIfpName = (String) attributes.get("parentIfpName");

        if (parentIfpName != null) {
            setParentIfpName(parentIfpName);
        }

        Date ifpEndDate = (Date) attributes.get("ifpEndDate");

        if (ifpEndDate != null) {
            setIfpEndDate(ifpEndDate);
        }

        String ifpDesignation = (String) attributes.get("ifpDesignation");

        if (ifpDesignation != null) {
            setIfpDesignation(ifpDesignation);
        }

        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        Date ifpStartDate = (Date) attributes.get("ifpStartDate");

        if (ifpStartDate != null) {
            setIfpStartDate(ifpStartDate);
        }

        String parentIfpId = (String) attributes.get("parentIfpId");

        if (parentIfpId != null) {
            setParentIfpId(parentIfpId);
        }

        String commitmentPeriod = (String) attributes.get("commitmentPeriod");

        if (commitmentPeriod != null) {
            setCommitmentPeriod(commitmentPeriod);
        }

        Integer ifpType = (Integer) attributes.get("ifpType");

        if (ifpType != null) {
            setIfpType(ifpType);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        Integer ifpModelSid = (Integer) attributes.get("ifpModelSid");

        if (ifpModelSid != null) {
            setIfpModelSid(ifpModelSid);
        }

        Boolean recordLockStatus = (Boolean) attributes.get("recordLockStatus");

        if (recordLockStatus != null) {
            setRecordLockStatus(recordLockStatus);
        }

        String source = (String) attributes.get("source");

        if (source != null) {
            setSource(source);
        }

        String ifpName = (String) attributes.get("ifpName");

        if (ifpName != null) {
            setIfpName(ifpName);
        }

        String ifpNo = (String) attributes.get("ifpNo");

        if (ifpNo != null) {
            setIfpNo(ifpNo);
        }

        String inboundStatus = (String) attributes.get("inboundStatus");

        if (inboundStatus != null) {
            setInboundStatus(inboundStatus);
        }
    }

    /**
    * Returns the primary key of this ifp model.
    *
    * @return the primary key of this ifp model
    */
    @Override
    public int getPrimaryKey() {
        return _ifpModel.getPrimaryKey();
    }

    /**
    * Sets the primary key of this ifp model.
    *
    * @param primaryKey the primary key of this ifp model
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _ifpModel.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the modified by of this ifp model.
    *
    * @return the modified by of this ifp model
    */
    @Override
    public int getModifiedBy() {
        return _ifpModel.getModifiedBy();
    }

    /**
    * Sets the modified by of this ifp model.
    *
    * @param modifiedBy the modified by of this ifp model
    */
    @Override
    public void setModifiedBy(int modifiedBy) {
        _ifpModel.setModifiedBy(modifiedBy);
    }

    /**
    * Returns the total dollar commitment of this ifp model.
    *
    * @return the total dollar commitment of this ifp model
    */
    @Override
    public java.lang.String getTotalDollarCommitment() {
        return _ifpModel.getTotalDollarCommitment();
    }

    /**
    * Sets the total dollar commitment of this ifp model.
    *
    * @param totalDollarCommitment the total dollar commitment of this ifp model
    */
    @Override
    public void setTotalDollarCommitment(java.lang.String totalDollarCommitment) {
        _ifpModel.setTotalDollarCommitment(totalDollarCommitment);
    }

    /**
    * Returns the created date of this ifp model.
    *
    * @return the created date of this ifp model
    */
    @Override
    public java.util.Date getCreatedDate() {
        return _ifpModel.getCreatedDate();
    }

    /**
    * Sets the created date of this ifp model.
    *
    * @param createdDate the created date of this ifp model
    */
    @Override
    public void setCreatedDate(java.util.Date createdDate) {
        _ifpModel.setCreatedDate(createdDate);
    }

    /**
    * Returns the ifp status of this ifp model.
    *
    * @return the ifp status of this ifp model
    */
    @Override
    public int getIfpStatus() {
        return _ifpModel.getIfpStatus();
    }

    /**
    * Sets the ifp status of this ifp model.
    *
    * @param ifpStatus the ifp status of this ifp model
    */
    @Override
    public void setIfpStatus(int ifpStatus) {
        _ifpModel.setIfpStatus(ifpStatus);
    }

    /**
    * Returns the total volume commitment of this ifp model.
    *
    * @return the total volume commitment of this ifp model
    */
    @Override
    public java.lang.String getTotalVolumeCommitment() {
        return _ifpModel.getTotalVolumeCommitment();
    }

    /**
    * Sets the total volume commitment of this ifp model.
    *
    * @param totalVolumeCommitment the total volume commitment of this ifp model
    */
    @Override
    public void setTotalVolumeCommitment(java.lang.String totalVolumeCommitment) {
        _ifpModel.setTotalVolumeCommitment(totalVolumeCommitment);
    }

    /**
    * Returns the batch ID of this ifp model.
    *
    * @return the batch ID of this ifp model
    */
    @Override
    public java.lang.String getBatchId() {
        return _ifpModel.getBatchId();
    }

    /**
    * Sets the batch ID of this ifp model.
    *
    * @param batchId the batch ID of this ifp model
    */
    @Override
    public void setBatchId(java.lang.String batchId) {
        _ifpModel.setBatchId(batchId);
    }

    /**
    * Returns the internal notes of this ifp model.
    *
    * @return the internal notes of this ifp model
    */
    @Override
    public java.lang.String getInternalNotes() {
        return _ifpModel.getInternalNotes();
    }

    /**
    * Sets the internal notes of this ifp model.
    *
    * @param internalNotes the internal notes of this ifp model
    */
    @Override
    public void setInternalNotes(java.lang.String internalNotes) {
        _ifpModel.setInternalNotes(internalNotes);
    }

    /**
    * Returns the ifp ID of this ifp model.
    *
    * @return the ifp ID of this ifp model
    */
    @Override
    public java.lang.String getIfpId() {
        return _ifpModel.getIfpId();
    }

    /**
    * Sets the ifp ID of this ifp model.
    *
    * @param ifpId the ifp ID of this ifp model
    */
    @Override
    public void setIfpId(java.lang.String ifpId) {
        _ifpModel.setIfpId(ifpId);
    }

    /**
    * Returns the total marketshare commitment of this ifp model.
    *
    * @return the total marketshare commitment of this ifp model
    */
    @Override
    public java.lang.String getTotalMarketshareCommitment() {
        return _ifpModel.getTotalMarketshareCommitment();
    }

    /**
    * Sets the total marketshare commitment of this ifp model.
    *
    * @param totalMarketshareCommitment the total marketshare commitment of this ifp model
    */
    @Override
    public void setTotalMarketshareCommitment(
        java.lang.String totalMarketshareCommitment) {
        _ifpModel.setTotalMarketshareCommitment(totalMarketshareCommitment);
    }

    /**
    * Returns the ifp category of this ifp model.
    *
    * @return the ifp category of this ifp model
    */
    @Override
    public int getIfpCategory() {
        return _ifpModel.getIfpCategory();
    }

    /**
    * Sets the ifp category of this ifp model.
    *
    * @param ifpCategory the ifp category of this ifp model
    */
    @Override
    public void setIfpCategory(int ifpCategory) {
        _ifpModel.setIfpCategory(ifpCategory);
    }

    /**
    * Returns the parent ifp name of this ifp model.
    *
    * @return the parent ifp name of this ifp model
    */
    @Override
    public java.lang.String getParentIfpName() {
        return _ifpModel.getParentIfpName();
    }

    /**
    * Sets the parent ifp name of this ifp model.
    *
    * @param parentIfpName the parent ifp name of this ifp model
    */
    @Override
    public void setParentIfpName(java.lang.String parentIfpName) {
        _ifpModel.setParentIfpName(parentIfpName);
    }

    /**
    * Returns the ifp end date of this ifp model.
    *
    * @return the ifp end date of this ifp model
    */
    @Override
    public java.util.Date getIfpEndDate() {
        return _ifpModel.getIfpEndDate();
    }

    /**
    * Sets the ifp end date of this ifp model.
    *
    * @param ifpEndDate the ifp end date of this ifp model
    */
    @Override
    public void setIfpEndDate(java.util.Date ifpEndDate) {
        _ifpModel.setIfpEndDate(ifpEndDate);
    }

    /**
    * Returns the ifp designation of this ifp model.
    *
    * @return the ifp designation of this ifp model
    */
    @Override
    public java.lang.String getIfpDesignation() {
        return _ifpModel.getIfpDesignation();
    }

    /**
    * Sets the ifp designation of this ifp model.
    *
    * @param ifpDesignation the ifp designation of this ifp model
    */
    @Override
    public void setIfpDesignation(java.lang.String ifpDesignation) {
        _ifpModel.setIfpDesignation(ifpDesignation);
    }

    /**
    * Returns the created by of this ifp model.
    *
    * @return the created by of this ifp model
    */
    @Override
    public int getCreatedBy() {
        return _ifpModel.getCreatedBy();
    }

    /**
    * Sets the created by of this ifp model.
    *
    * @param createdBy the created by of this ifp model
    */
    @Override
    public void setCreatedBy(int createdBy) {
        _ifpModel.setCreatedBy(createdBy);
    }

    /**
    * Returns the ifp start date of this ifp model.
    *
    * @return the ifp start date of this ifp model
    */
    @Override
    public java.util.Date getIfpStartDate() {
        return _ifpModel.getIfpStartDate();
    }

    /**
    * Sets the ifp start date of this ifp model.
    *
    * @param ifpStartDate the ifp start date of this ifp model
    */
    @Override
    public void setIfpStartDate(java.util.Date ifpStartDate) {
        _ifpModel.setIfpStartDate(ifpStartDate);
    }

    /**
    * Returns the parent ifp ID of this ifp model.
    *
    * @return the parent ifp ID of this ifp model
    */
    @Override
    public java.lang.String getParentIfpId() {
        return _ifpModel.getParentIfpId();
    }

    /**
    * Sets the parent ifp ID of this ifp model.
    *
    * @param parentIfpId the parent ifp ID of this ifp model
    */
    @Override
    public void setParentIfpId(java.lang.String parentIfpId) {
        _ifpModel.setParentIfpId(parentIfpId);
    }

    /**
    * Returns the commitment period of this ifp model.
    *
    * @return the commitment period of this ifp model
    */
    @Override
    public java.lang.String getCommitmentPeriod() {
        return _ifpModel.getCommitmentPeriod();
    }

    /**
    * Sets the commitment period of this ifp model.
    *
    * @param commitmentPeriod the commitment period of this ifp model
    */
    @Override
    public void setCommitmentPeriod(java.lang.String commitmentPeriod) {
        _ifpModel.setCommitmentPeriod(commitmentPeriod);
    }

    /**
    * Returns the ifp type of this ifp model.
    *
    * @return the ifp type of this ifp model
    */
    @Override
    public int getIfpType() {
        return _ifpModel.getIfpType();
    }

    /**
    * Sets the ifp type of this ifp model.
    *
    * @param ifpType the ifp type of this ifp model
    */
    @Override
    public void setIfpType(int ifpType) {
        _ifpModel.setIfpType(ifpType);
    }

    /**
    * Returns the modified date of this ifp model.
    *
    * @return the modified date of this ifp model
    */
    @Override
    public java.util.Date getModifiedDate() {
        return _ifpModel.getModifiedDate();
    }

    /**
    * Sets the modified date of this ifp model.
    *
    * @param modifiedDate the modified date of this ifp model
    */
    @Override
    public void setModifiedDate(java.util.Date modifiedDate) {
        _ifpModel.setModifiedDate(modifiedDate);
    }

    /**
    * Returns the ifp model sid of this ifp model.
    *
    * @return the ifp model sid of this ifp model
    */
    @Override
    public int getIfpModelSid() {
        return _ifpModel.getIfpModelSid();
    }

    /**
    * Sets the ifp model sid of this ifp model.
    *
    * @param ifpModelSid the ifp model sid of this ifp model
    */
    @Override
    public void setIfpModelSid(int ifpModelSid) {
        _ifpModel.setIfpModelSid(ifpModelSid);
    }

    /**
    * Returns the record lock status of this ifp model.
    *
    * @return the record lock status of this ifp model
    */
    @Override
    public boolean getRecordLockStatus() {
        return _ifpModel.getRecordLockStatus();
    }

    /**
    * Returns <code>true</code> if this ifp model is record lock status.
    *
    * @return <code>true</code> if this ifp model is record lock status; <code>false</code> otherwise
    */
    @Override
    public boolean isRecordLockStatus() {
        return _ifpModel.isRecordLockStatus();
    }

    /**
    * Sets whether this ifp model is record lock status.
    *
    * @param recordLockStatus the record lock status of this ifp model
    */
    @Override
    public void setRecordLockStatus(boolean recordLockStatus) {
        _ifpModel.setRecordLockStatus(recordLockStatus);
    }

    /**
    * Returns the source of this ifp model.
    *
    * @return the source of this ifp model
    */
    @Override
    public java.lang.String getSource() {
        return _ifpModel.getSource();
    }

    /**
    * Sets the source of this ifp model.
    *
    * @param source the source of this ifp model
    */
    @Override
    public void setSource(java.lang.String source) {
        _ifpModel.setSource(source);
    }

    /**
    * Returns the ifp name of this ifp model.
    *
    * @return the ifp name of this ifp model
    */
    @Override
    public java.lang.String getIfpName() {
        return _ifpModel.getIfpName();
    }

    /**
    * Sets the ifp name of this ifp model.
    *
    * @param ifpName the ifp name of this ifp model
    */
    @Override
    public void setIfpName(java.lang.String ifpName) {
        _ifpModel.setIfpName(ifpName);
    }

    /**
    * Returns the ifp no of this ifp model.
    *
    * @return the ifp no of this ifp model
    */
    @Override
    public java.lang.String getIfpNo() {
        return _ifpModel.getIfpNo();
    }

    /**
    * Sets the ifp no of this ifp model.
    *
    * @param ifpNo the ifp no of this ifp model
    */
    @Override
    public void setIfpNo(java.lang.String ifpNo) {
        _ifpModel.setIfpNo(ifpNo);
    }

    /**
    * Returns the inbound status of this ifp model.
    *
    * @return the inbound status of this ifp model
    */
    @Override
    public java.lang.String getInboundStatus() {
        return _ifpModel.getInboundStatus();
    }

    /**
    * Sets the inbound status of this ifp model.
    *
    * @param inboundStatus the inbound status of this ifp model
    */
    @Override
    public void setInboundStatus(java.lang.String inboundStatus) {
        _ifpModel.setInboundStatus(inboundStatus);
    }

    @Override
    public boolean isNew() {
        return _ifpModel.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _ifpModel.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _ifpModel.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _ifpModel.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _ifpModel.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _ifpModel.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _ifpModel.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _ifpModel.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _ifpModel.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _ifpModel.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _ifpModel.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new IfpModelWrapper((IfpModel) _ifpModel.clone());
    }

    @Override
    public int compareTo(IfpModel ifpModel) {
        return _ifpModel.compareTo(ifpModel);
    }

    @Override
    public int hashCode() {
        return _ifpModel.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<IfpModel> toCacheModel() {
        return _ifpModel.toCacheModel();
    }

    @Override
    public IfpModel toEscapedModel() {
        return new IfpModelWrapper(_ifpModel.toEscapedModel());
    }

    @Override
    public IfpModel toUnescapedModel() {
        return new IfpModelWrapper(_ifpModel.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _ifpModel.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _ifpModel.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _ifpModel.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof IfpModelWrapper)) {
            return false;
        }

        IfpModelWrapper ifpModelWrapper = (IfpModelWrapper) obj;

        if (Validator.equals(_ifpModel, ifpModelWrapper._ifpModel)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public IfpModel getWrappedIfpModel() {
        return _ifpModel;
    }

    @Override
    public IfpModel getWrappedModel() {
        return _ifpModel;
    }

    @Override
    public void resetOriginalValues() {
        _ifpModel.resetOriginalValues();
    }
}
