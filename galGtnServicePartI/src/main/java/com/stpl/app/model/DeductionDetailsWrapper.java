package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link DeductionDetails}.
 * </p>
 *
 * @author
 * @see DeductionDetails
 * @generated
 */
public class DeductionDetailsWrapper implements DeductionDetails,
    ModelWrapper<DeductionDetails> {
    private DeductionDetails _deductionDetails;

    public DeductionDetailsWrapper(DeductionDetails deductionDetails) {
        _deductionDetails = deductionDetails;
    }

    @Override
    public Class<?> getModelClass() {
        return DeductionDetails.class;
    }

    @Override
    public String getModelClassName() {
        return DeductionDetails.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("createdBy", getCreatedBy());
        attributes.put("netSalesFormulaMasterSid", getNetSalesFormulaMasterSid());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("rsContractSid", getRsContractSid());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("contractMasterSid", getContractMasterSid());
        attributes.put("deductionDetailsSid", getDeductionDetailsSid());
        attributes.put("indicator", getIndicator());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("recordLockStatus", getRecordLockStatus());
        attributes.put("source", getSource());
        attributes.put("cdrModelSid", getCdrModelSid());
        attributes.put("inboundStatus", getInboundStatus());
        attributes.put("deductionSubType", getDeductionSubType());
        attributes.put("deductionType", getDeductionType());
        attributes.put("deductionCategory", getDeductionCategory());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        Integer netSalesFormulaMasterSid = (Integer) attributes.get(
                "netSalesFormulaMasterSid");

        if (netSalesFormulaMasterSid != null) {
            setNetSalesFormulaMasterSid(netSalesFormulaMasterSid);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        Integer rsContractSid = (Integer) attributes.get("rsContractSid");

        if (rsContractSid != null) {
            setRsContractSid(rsContractSid);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        Integer contractMasterSid = (Integer) attributes.get(
                "contractMasterSid");

        if (contractMasterSid != null) {
            setContractMasterSid(contractMasterSid);
        }

        Integer deductionDetailsSid = (Integer) attributes.get(
                "deductionDetailsSid");

        if (deductionDetailsSid != null) {
            setDeductionDetailsSid(deductionDetailsSid);
        }

        String indicator = (String) attributes.get("indicator");

        if (indicator != null) {
            setIndicator(indicator);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        Boolean recordLockStatus = (Boolean) attributes.get("recordLockStatus");

        if (recordLockStatus != null) {
            setRecordLockStatus(recordLockStatus);
        }

        String source = (String) attributes.get("source");

        if (source != null) {
            setSource(source);
        }

        Integer cdrModelSid = (Integer) attributes.get("cdrModelSid");

        if (cdrModelSid != null) {
            setCdrModelSid(cdrModelSid);
        }

        String inboundStatus = (String) attributes.get("inboundStatus");

        if (inboundStatus != null) {
            setInboundStatus(inboundStatus);
        }

        String deductionSubType = (String) attributes.get("deductionSubType");

        if (deductionSubType != null) {
            setDeductionSubType(deductionSubType);
        }

        String deductionType = (String) attributes.get("deductionType");

        if (deductionType != null) {
            setDeductionType(deductionType);
        }

        String deductionCategory = (String) attributes.get("deductionCategory");

        if (deductionCategory != null) {
            setDeductionCategory(deductionCategory);
        }
    }

    /**
    * Returns the primary key of this deduction details.
    *
    * @return the primary key of this deduction details
    */
    @Override
    public int getPrimaryKey() {
        return _deductionDetails.getPrimaryKey();
    }

    /**
    * Sets the primary key of this deduction details.
    *
    * @param primaryKey the primary key of this deduction details
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _deductionDetails.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the created by of this deduction details.
    *
    * @return the created by of this deduction details
    */
    @Override
    public int getCreatedBy() {
        return _deductionDetails.getCreatedBy();
    }

    /**
    * Sets the created by of this deduction details.
    *
    * @param createdBy the created by of this deduction details
    */
    @Override
    public void setCreatedBy(int createdBy) {
        _deductionDetails.setCreatedBy(createdBy);
    }

    /**
    * Returns the net sales formula master sid of this deduction details.
    *
    * @return the net sales formula master sid of this deduction details
    */
    @Override
    public int getNetSalesFormulaMasterSid() {
        return _deductionDetails.getNetSalesFormulaMasterSid();
    }

    /**
    * Sets the net sales formula master sid of this deduction details.
    *
    * @param netSalesFormulaMasterSid the net sales formula master sid of this deduction details
    */
    @Override
    public void setNetSalesFormulaMasterSid(int netSalesFormulaMasterSid) {
        _deductionDetails.setNetSalesFormulaMasterSid(netSalesFormulaMasterSid);
    }

    /**
    * Returns the modified by of this deduction details.
    *
    * @return the modified by of this deduction details
    */
    @Override
    public int getModifiedBy() {
        return _deductionDetails.getModifiedBy();
    }

    /**
    * Sets the modified by of this deduction details.
    *
    * @param modifiedBy the modified by of this deduction details
    */
    @Override
    public void setModifiedBy(int modifiedBy) {
        _deductionDetails.setModifiedBy(modifiedBy);
    }

    /**
    * Returns the rs contract sid of this deduction details.
    *
    * @return the rs contract sid of this deduction details
    */
    @Override
    public int getRsContractSid() {
        return _deductionDetails.getRsContractSid();
    }

    /**
    * Sets the rs contract sid of this deduction details.
    *
    * @param rsContractSid the rs contract sid of this deduction details
    */
    @Override
    public void setRsContractSid(int rsContractSid) {
        _deductionDetails.setRsContractSid(rsContractSid);
    }

    /**
    * Returns the created date of this deduction details.
    *
    * @return the created date of this deduction details
    */
    @Override
    public java.util.Date getCreatedDate() {
        return _deductionDetails.getCreatedDate();
    }

    /**
    * Sets the created date of this deduction details.
    *
    * @param createdDate the created date of this deduction details
    */
    @Override
    public void setCreatedDate(java.util.Date createdDate) {
        _deductionDetails.setCreatedDate(createdDate);
    }

    /**
    * Returns the contract master sid of this deduction details.
    *
    * @return the contract master sid of this deduction details
    */
    @Override
    public int getContractMasterSid() {
        return _deductionDetails.getContractMasterSid();
    }

    /**
    * Sets the contract master sid of this deduction details.
    *
    * @param contractMasterSid the contract master sid of this deduction details
    */
    @Override
    public void setContractMasterSid(int contractMasterSid) {
        _deductionDetails.setContractMasterSid(contractMasterSid);
    }

    /**
    * Returns the deduction details sid of this deduction details.
    *
    * @return the deduction details sid of this deduction details
    */
    @Override
    public int getDeductionDetailsSid() {
        return _deductionDetails.getDeductionDetailsSid();
    }

    /**
    * Sets the deduction details sid of this deduction details.
    *
    * @param deductionDetailsSid the deduction details sid of this deduction details
    */
    @Override
    public void setDeductionDetailsSid(int deductionDetailsSid) {
        _deductionDetails.setDeductionDetailsSid(deductionDetailsSid);
    }

    /**
    * Returns the indicator of this deduction details.
    *
    * @return the indicator of this deduction details
    */
    @Override
    public java.lang.String getIndicator() {
        return _deductionDetails.getIndicator();
    }

    /**
    * Sets the indicator of this deduction details.
    *
    * @param indicator the indicator of this deduction details
    */
    @Override
    public void setIndicator(java.lang.String indicator) {
        _deductionDetails.setIndicator(indicator);
    }

    /**
    * Returns the modified date of this deduction details.
    *
    * @return the modified date of this deduction details
    */
    @Override
    public java.util.Date getModifiedDate() {
        return _deductionDetails.getModifiedDate();
    }

    /**
    * Sets the modified date of this deduction details.
    *
    * @param modifiedDate the modified date of this deduction details
    */
    @Override
    public void setModifiedDate(java.util.Date modifiedDate) {
        _deductionDetails.setModifiedDate(modifiedDate);
    }

    /**
    * Returns the record lock status of this deduction details.
    *
    * @return the record lock status of this deduction details
    */
    @Override
    public boolean getRecordLockStatus() {
        return _deductionDetails.getRecordLockStatus();
    }

    /**
    * Returns <code>true</code> if this deduction details is record lock status.
    *
    * @return <code>true</code> if this deduction details is record lock status; <code>false</code> otherwise
    */
    @Override
    public boolean isRecordLockStatus() {
        return _deductionDetails.isRecordLockStatus();
    }

    /**
    * Sets whether this deduction details is record lock status.
    *
    * @param recordLockStatus the record lock status of this deduction details
    */
    @Override
    public void setRecordLockStatus(boolean recordLockStatus) {
        _deductionDetails.setRecordLockStatus(recordLockStatus);
    }

    /**
    * Returns the source of this deduction details.
    *
    * @return the source of this deduction details
    */
    @Override
    public java.lang.String getSource() {
        return _deductionDetails.getSource();
    }

    /**
    * Sets the source of this deduction details.
    *
    * @param source the source of this deduction details
    */
    @Override
    public void setSource(java.lang.String source) {
        _deductionDetails.setSource(source);
    }

    /**
    * Returns the cdr model sid of this deduction details.
    *
    * @return the cdr model sid of this deduction details
    */
    @Override
    public int getCdrModelSid() {
        return _deductionDetails.getCdrModelSid();
    }

    /**
    * Sets the cdr model sid of this deduction details.
    *
    * @param cdrModelSid the cdr model sid of this deduction details
    */
    @Override
    public void setCdrModelSid(int cdrModelSid) {
        _deductionDetails.setCdrModelSid(cdrModelSid);
    }

    /**
    * Returns the inbound status of this deduction details.
    *
    * @return the inbound status of this deduction details
    */
    @Override
    public java.lang.String getInboundStatus() {
        return _deductionDetails.getInboundStatus();
    }

    /**
    * Sets the inbound status of this deduction details.
    *
    * @param inboundStatus the inbound status of this deduction details
    */
    @Override
    public void setInboundStatus(java.lang.String inboundStatus) {
        _deductionDetails.setInboundStatus(inboundStatus);
    }

    /**
    * Returns the deduction sub type of this deduction details.
    *
    * @return the deduction sub type of this deduction details
    */
    @Override
    public java.lang.String getDeductionSubType() {
        return _deductionDetails.getDeductionSubType();
    }

    /**
    * Sets the deduction sub type of this deduction details.
    *
    * @param deductionSubType the deduction sub type of this deduction details
    */
    @Override
    public void setDeductionSubType(java.lang.String deductionSubType) {
        _deductionDetails.setDeductionSubType(deductionSubType);
    }

    /**
    * Returns the deduction type of this deduction details.
    *
    * @return the deduction type of this deduction details
    */
    @Override
    public java.lang.String getDeductionType() {
        return _deductionDetails.getDeductionType();
    }

    /**
    * Sets the deduction type of this deduction details.
    *
    * @param deductionType the deduction type of this deduction details
    */
    @Override
    public void setDeductionType(java.lang.String deductionType) {
        _deductionDetails.setDeductionType(deductionType);
    }

    /**
    * Returns the deduction category of this deduction details.
    *
    * @return the deduction category of this deduction details
    */
    @Override
    public java.lang.String getDeductionCategory() {
        return _deductionDetails.getDeductionCategory();
    }

    /**
    * Sets the deduction category of this deduction details.
    *
    * @param deductionCategory the deduction category of this deduction details
    */
    @Override
    public void setDeductionCategory(java.lang.String deductionCategory) {
        _deductionDetails.setDeductionCategory(deductionCategory);
    }

    @Override
    public boolean isNew() {
        return _deductionDetails.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _deductionDetails.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _deductionDetails.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _deductionDetails.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _deductionDetails.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _deductionDetails.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _deductionDetails.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _deductionDetails.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _deductionDetails.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _deductionDetails.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _deductionDetails.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new DeductionDetailsWrapper((DeductionDetails) _deductionDetails.clone());
    }

    @Override
    public int compareTo(DeductionDetails deductionDetails) {
        return _deductionDetails.compareTo(deductionDetails);
    }

    @Override
    public int hashCode() {
        return _deductionDetails.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<DeductionDetails> toCacheModel() {
        return _deductionDetails.toCacheModel();
    }

    @Override
    public DeductionDetails toEscapedModel() {
        return new DeductionDetailsWrapper(_deductionDetails.toEscapedModel());
    }

    @Override
    public DeductionDetails toUnescapedModel() {
        return new DeductionDetailsWrapper(_deductionDetails.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _deductionDetails.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _deductionDetails.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _deductionDetails.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof DeductionDetailsWrapper)) {
            return false;
        }

        DeductionDetailsWrapper deductionDetailsWrapper = (DeductionDetailsWrapper) obj;

        if (Validator.equals(_deductionDetails,
                    deductionDetailsWrapper._deductionDetails)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public DeductionDetails getWrappedDeductionDetails() {
        return _deductionDetails;
    }

    @Override
    public DeductionDetails getWrappedModel() {
        return _deductionDetails;
    }

    @Override
    public void resetOriginalValues() {
        _deductionDetails.resetOriginalValues();
    }
}
