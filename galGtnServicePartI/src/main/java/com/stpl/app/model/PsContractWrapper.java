package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link PsContract}.
 * </p>
 *
 * @author
 * @see PsContract
 * @generated
 */
public class PsContractWrapper implements PsContract, ModelWrapper<PsContract> {
    private PsContract _psContract;

    public PsContractWrapper(PsContract psContract) {
        _psContract = psContract;
    }

    @Override
    public Class<?> getModelClass() {
        return PsContract.class;
    }

    @Override
    public String getModelClassName() {
        return PsContract.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("psName", getPsName());
        attributes.put("cfpContractSid", getCfpContractSid());
        attributes.put("psContractSid", getPsContractSid());
        attributes.put("psType", getPsType());
        attributes.put("psContractAttachedStatus", getPsContractAttachedStatus());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("psCategory", getPsCategory());
        attributes.put("recordLockStatus", getRecordLockStatus());
        attributes.put("psStatus", getPsStatus());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("source", getSource());
        attributes.put("parentPsId", getParentPsId());
        attributes.put("psDesignation", getPsDesignation());
        attributes.put("batchId", getBatchId());
        attributes.put("contractMasterSid", getContractMasterSid());
        attributes.put("psModelSid", getPsModelSid());
        attributes.put("psContractAttachedDate", getPsContractAttachedDate());
        attributes.put("psEndDate", getPsEndDate());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("inboundStatus", getInboundStatus());
        attributes.put("parentPsName", getParentPsName());
        attributes.put("psStartDate", getPsStartDate());
        attributes.put("ifpContractSid", getIfpContractSid());
        attributes.put("psTradeClass", getPsTradeClass());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String psName = (String) attributes.get("psName");

        if (psName != null) {
            setPsName(psName);
        }

        String cfpContractSid = (String) attributes.get("cfpContractSid");

        if (cfpContractSid != null) {
            setCfpContractSid(cfpContractSid);
        }

        Integer psContractSid = (Integer) attributes.get("psContractSid");

        if (psContractSid != null) {
            setPsContractSid(psContractSid);
        }

        Integer psType = (Integer) attributes.get("psType");

        if (psType != null) {
            setPsType(psType);
        }

        Integer psContractAttachedStatus = (Integer) attributes.get(
                "psContractAttachedStatus");

        if (psContractAttachedStatus != null) {
            setPsContractAttachedStatus(psContractAttachedStatus);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        Integer psCategory = (Integer) attributes.get("psCategory");

        if (psCategory != null) {
            setPsCategory(psCategory);
        }

        Boolean recordLockStatus = (Boolean) attributes.get("recordLockStatus");

        if (recordLockStatus != null) {
            setRecordLockStatus(recordLockStatus);
        }

        Integer psStatus = (Integer) attributes.get("psStatus");

        if (psStatus != null) {
            setPsStatus(psStatus);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        String source = (String) attributes.get("source");

        if (source != null) {
            setSource(source);
        }

        String parentPsId = (String) attributes.get("parentPsId");

        if (parentPsId != null) {
            setParentPsId(parentPsId);
        }

        String psDesignation = (String) attributes.get("psDesignation");

        if (psDesignation != null) {
            setPsDesignation(psDesignation);
        }

        String batchId = (String) attributes.get("batchId");

        if (batchId != null) {
            setBatchId(batchId);
        }

        Integer contractMasterSid = (Integer) attributes.get(
                "contractMasterSid");

        if (contractMasterSid != null) {
            setContractMasterSid(contractMasterSid);
        }

        Integer psModelSid = (Integer) attributes.get("psModelSid");

        if (psModelSid != null) {
            setPsModelSid(psModelSid);
        }

        Date psContractAttachedDate = (Date) attributes.get(
                "psContractAttachedDate");

        if (psContractAttachedDate != null) {
            setPsContractAttachedDate(psContractAttachedDate);
        }

        Date psEndDate = (Date) attributes.get("psEndDate");

        if (psEndDate != null) {
            setPsEndDate(psEndDate);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        String inboundStatus = (String) attributes.get("inboundStatus");

        if (inboundStatus != null) {
            setInboundStatus(inboundStatus);
        }

        String parentPsName = (String) attributes.get("parentPsName");

        if (parentPsName != null) {
            setParentPsName(parentPsName);
        }

        Date psStartDate = (Date) attributes.get("psStartDate");

        if (psStartDate != null) {
            setPsStartDate(psStartDate);
        }

        String ifpContractSid = (String) attributes.get("ifpContractSid");

        if (ifpContractSid != null) {
            setIfpContractSid(ifpContractSid);
        }

        Integer psTradeClass = (Integer) attributes.get("psTradeClass");

        if (psTradeClass != null) {
            setPsTradeClass(psTradeClass);
        }
    }

    /**
    * Returns the primary key of this ps contract.
    *
    * @return the primary key of this ps contract
    */
    @Override
    public int getPrimaryKey() {
        return _psContract.getPrimaryKey();
    }

    /**
    * Sets the primary key of this ps contract.
    *
    * @param primaryKey the primary key of this ps contract
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _psContract.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the ps name of this ps contract.
    *
    * @return the ps name of this ps contract
    */
    @Override
    public java.lang.String getPsName() {
        return _psContract.getPsName();
    }

    /**
    * Sets the ps name of this ps contract.
    *
    * @param psName the ps name of this ps contract
    */
    @Override
    public void setPsName(java.lang.String psName) {
        _psContract.setPsName(psName);
    }

    /**
    * Returns the cfp contract sid of this ps contract.
    *
    * @return the cfp contract sid of this ps contract
    */
    @Override
    public java.lang.String getCfpContractSid() {
        return _psContract.getCfpContractSid();
    }

    /**
    * Sets the cfp contract sid of this ps contract.
    *
    * @param cfpContractSid the cfp contract sid of this ps contract
    */
    @Override
    public void setCfpContractSid(java.lang.String cfpContractSid) {
        _psContract.setCfpContractSid(cfpContractSid);
    }

    /**
    * Returns the ps contract sid of this ps contract.
    *
    * @return the ps contract sid of this ps contract
    */
    @Override
    public int getPsContractSid() {
        return _psContract.getPsContractSid();
    }

    /**
    * Sets the ps contract sid of this ps contract.
    *
    * @param psContractSid the ps contract sid of this ps contract
    */
    @Override
    public void setPsContractSid(int psContractSid) {
        _psContract.setPsContractSid(psContractSid);
    }

    /**
    * Returns the ps type of this ps contract.
    *
    * @return the ps type of this ps contract
    */
    @Override
    public int getPsType() {
        return _psContract.getPsType();
    }

    /**
    * Sets the ps type of this ps contract.
    *
    * @param psType the ps type of this ps contract
    */
    @Override
    public void setPsType(int psType) {
        _psContract.setPsType(psType);
    }

    /**
    * Returns the ps contract attached status of this ps contract.
    *
    * @return the ps contract attached status of this ps contract
    */
    @Override
    public int getPsContractAttachedStatus() {
        return _psContract.getPsContractAttachedStatus();
    }

    /**
    * Sets the ps contract attached status of this ps contract.
    *
    * @param psContractAttachedStatus the ps contract attached status of this ps contract
    */
    @Override
    public void setPsContractAttachedStatus(int psContractAttachedStatus) {
        _psContract.setPsContractAttachedStatus(psContractAttachedStatus);
    }

    /**
    * Returns the modified date of this ps contract.
    *
    * @return the modified date of this ps contract
    */
    @Override
    public java.util.Date getModifiedDate() {
        return _psContract.getModifiedDate();
    }

    /**
    * Sets the modified date of this ps contract.
    *
    * @param modifiedDate the modified date of this ps contract
    */
    @Override
    public void setModifiedDate(java.util.Date modifiedDate) {
        _psContract.setModifiedDate(modifiedDate);
    }

    /**
    * Returns the ps category of this ps contract.
    *
    * @return the ps category of this ps contract
    */
    @Override
    public int getPsCategory() {
        return _psContract.getPsCategory();
    }

    /**
    * Sets the ps category of this ps contract.
    *
    * @param psCategory the ps category of this ps contract
    */
    @Override
    public void setPsCategory(int psCategory) {
        _psContract.setPsCategory(psCategory);
    }

    /**
    * Returns the record lock status of this ps contract.
    *
    * @return the record lock status of this ps contract
    */
    @Override
    public boolean getRecordLockStatus() {
        return _psContract.getRecordLockStatus();
    }

    /**
    * Returns <code>true</code> if this ps contract is record lock status.
    *
    * @return <code>true</code> if this ps contract is record lock status; <code>false</code> otherwise
    */
    @Override
    public boolean isRecordLockStatus() {
        return _psContract.isRecordLockStatus();
    }

    /**
    * Sets whether this ps contract is record lock status.
    *
    * @param recordLockStatus the record lock status of this ps contract
    */
    @Override
    public void setRecordLockStatus(boolean recordLockStatus) {
        _psContract.setRecordLockStatus(recordLockStatus);
    }

    /**
    * Returns the ps status of this ps contract.
    *
    * @return the ps status of this ps contract
    */
    @Override
    public int getPsStatus() {
        return _psContract.getPsStatus();
    }

    /**
    * Sets the ps status of this ps contract.
    *
    * @param psStatus the ps status of this ps contract
    */
    @Override
    public void setPsStatus(int psStatus) {
        _psContract.setPsStatus(psStatus);
    }

    /**
    * Returns the created date of this ps contract.
    *
    * @return the created date of this ps contract
    */
    @Override
    public java.util.Date getCreatedDate() {
        return _psContract.getCreatedDate();
    }

    /**
    * Sets the created date of this ps contract.
    *
    * @param createdDate the created date of this ps contract
    */
    @Override
    public void setCreatedDate(java.util.Date createdDate) {
        _psContract.setCreatedDate(createdDate);
    }

    /**
    * Returns the created by of this ps contract.
    *
    * @return the created by of this ps contract
    */
    @Override
    public int getCreatedBy() {
        return _psContract.getCreatedBy();
    }

    /**
    * Sets the created by of this ps contract.
    *
    * @param createdBy the created by of this ps contract
    */
    @Override
    public void setCreatedBy(int createdBy) {
        _psContract.setCreatedBy(createdBy);
    }

    /**
    * Returns the source of this ps contract.
    *
    * @return the source of this ps contract
    */
    @Override
    public java.lang.String getSource() {
        return _psContract.getSource();
    }

    /**
    * Sets the source of this ps contract.
    *
    * @param source the source of this ps contract
    */
    @Override
    public void setSource(java.lang.String source) {
        _psContract.setSource(source);
    }

    /**
    * Returns the parent ps ID of this ps contract.
    *
    * @return the parent ps ID of this ps contract
    */
    @Override
    public java.lang.String getParentPsId() {
        return _psContract.getParentPsId();
    }

    /**
    * Sets the parent ps ID of this ps contract.
    *
    * @param parentPsId the parent ps ID of this ps contract
    */
    @Override
    public void setParentPsId(java.lang.String parentPsId) {
        _psContract.setParentPsId(parentPsId);
    }

    /**
    * Returns the ps designation of this ps contract.
    *
    * @return the ps designation of this ps contract
    */
    @Override
    public java.lang.String getPsDesignation() {
        return _psContract.getPsDesignation();
    }

    /**
    * Sets the ps designation of this ps contract.
    *
    * @param psDesignation the ps designation of this ps contract
    */
    @Override
    public void setPsDesignation(java.lang.String psDesignation) {
        _psContract.setPsDesignation(psDesignation);
    }

    /**
    * Returns the batch ID of this ps contract.
    *
    * @return the batch ID of this ps contract
    */
    @Override
    public java.lang.String getBatchId() {
        return _psContract.getBatchId();
    }

    /**
    * Sets the batch ID of this ps contract.
    *
    * @param batchId the batch ID of this ps contract
    */
    @Override
    public void setBatchId(java.lang.String batchId) {
        _psContract.setBatchId(batchId);
    }

    /**
    * Returns the contract master sid of this ps contract.
    *
    * @return the contract master sid of this ps contract
    */
    @Override
    public int getContractMasterSid() {
        return _psContract.getContractMasterSid();
    }

    /**
    * Sets the contract master sid of this ps contract.
    *
    * @param contractMasterSid the contract master sid of this ps contract
    */
    @Override
    public void setContractMasterSid(int contractMasterSid) {
        _psContract.setContractMasterSid(contractMasterSid);
    }

    /**
    * Returns the ps model sid of this ps contract.
    *
    * @return the ps model sid of this ps contract
    */
    @Override
    public int getPsModelSid() {
        return _psContract.getPsModelSid();
    }

    /**
    * Sets the ps model sid of this ps contract.
    *
    * @param psModelSid the ps model sid of this ps contract
    */
    @Override
    public void setPsModelSid(int psModelSid) {
        _psContract.setPsModelSid(psModelSid);
    }

    /**
    * Returns the ps contract attached date of this ps contract.
    *
    * @return the ps contract attached date of this ps contract
    */
    @Override
    public java.util.Date getPsContractAttachedDate() {
        return _psContract.getPsContractAttachedDate();
    }

    /**
    * Sets the ps contract attached date of this ps contract.
    *
    * @param psContractAttachedDate the ps contract attached date of this ps contract
    */
    @Override
    public void setPsContractAttachedDate(java.util.Date psContractAttachedDate) {
        _psContract.setPsContractAttachedDate(psContractAttachedDate);
    }

    /**
    * Returns the ps end date of this ps contract.
    *
    * @return the ps end date of this ps contract
    */
    @Override
    public java.util.Date getPsEndDate() {
        return _psContract.getPsEndDate();
    }

    /**
    * Sets the ps end date of this ps contract.
    *
    * @param psEndDate the ps end date of this ps contract
    */
    @Override
    public void setPsEndDate(java.util.Date psEndDate) {
        _psContract.setPsEndDate(psEndDate);
    }

    /**
    * Returns the modified by of this ps contract.
    *
    * @return the modified by of this ps contract
    */
    @Override
    public int getModifiedBy() {
        return _psContract.getModifiedBy();
    }

    /**
    * Sets the modified by of this ps contract.
    *
    * @param modifiedBy the modified by of this ps contract
    */
    @Override
    public void setModifiedBy(int modifiedBy) {
        _psContract.setModifiedBy(modifiedBy);
    }

    /**
    * Returns the inbound status of this ps contract.
    *
    * @return the inbound status of this ps contract
    */
    @Override
    public java.lang.String getInboundStatus() {
        return _psContract.getInboundStatus();
    }

    /**
    * Sets the inbound status of this ps contract.
    *
    * @param inboundStatus the inbound status of this ps contract
    */
    @Override
    public void setInboundStatus(java.lang.String inboundStatus) {
        _psContract.setInboundStatus(inboundStatus);
    }

    /**
    * Returns the parent ps name of this ps contract.
    *
    * @return the parent ps name of this ps contract
    */
    @Override
    public java.lang.String getParentPsName() {
        return _psContract.getParentPsName();
    }

    /**
    * Sets the parent ps name of this ps contract.
    *
    * @param parentPsName the parent ps name of this ps contract
    */
    @Override
    public void setParentPsName(java.lang.String parentPsName) {
        _psContract.setParentPsName(parentPsName);
    }

    /**
    * Returns the ps start date of this ps contract.
    *
    * @return the ps start date of this ps contract
    */
    @Override
    public java.util.Date getPsStartDate() {
        return _psContract.getPsStartDate();
    }

    /**
    * Sets the ps start date of this ps contract.
    *
    * @param psStartDate the ps start date of this ps contract
    */
    @Override
    public void setPsStartDate(java.util.Date psStartDate) {
        _psContract.setPsStartDate(psStartDate);
    }

    /**
    * Returns the ifp contract sid of this ps contract.
    *
    * @return the ifp contract sid of this ps contract
    */
    @Override
    public java.lang.String getIfpContractSid() {
        return _psContract.getIfpContractSid();
    }

    /**
    * Sets the ifp contract sid of this ps contract.
    *
    * @param ifpContractSid the ifp contract sid of this ps contract
    */
    @Override
    public void setIfpContractSid(java.lang.String ifpContractSid) {
        _psContract.setIfpContractSid(ifpContractSid);
    }

    /**
    * Returns the ps trade class of this ps contract.
    *
    * @return the ps trade class of this ps contract
    */
    @Override
    public int getPsTradeClass() {
        return _psContract.getPsTradeClass();
    }

    /**
    * Sets the ps trade class of this ps contract.
    *
    * @param psTradeClass the ps trade class of this ps contract
    */
    @Override
    public void setPsTradeClass(int psTradeClass) {
        _psContract.setPsTradeClass(psTradeClass);
    }

    @Override
    public boolean isNew() {
        return _psContract.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _psContract.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _psContract.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _psContract.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _psContract.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _psContract.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _psContract.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _psContract.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _psContract.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _psContract.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _psContract.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new PsContractWrapper((PsContract) _psContract.clone());
    }

    @Override
    public int compareTo(PsContract psContract) {
        return _psContract.compareTo(psContract);
    }

    @Override
    public int hashCode() {
        return _psContract.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<PsContract> toCacheModel() {
        return _psContract.toCacheModel();
    }

    @Override
    public PsContract toEscapedModel() {
        return new PsContractWrapper(_psContract.toEscapedModel());
    }

    @Override
    public PsContract toUnescapedModel() {
        return new PsContractWrapper(_psContract.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _psContract.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _psContract.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _psContract.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof PsContractWrapper)) {
            return false;
        }

        PsContractWrapper psContractWrapper = (PsContractWrapper) obj;

        if (Validator.equals(_psContract, psContractWrapper._psContract)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public PsContract getWrappedPsContract() {
        return _psContract;
    }

    @Override
    public PsContract getWrappedModel() {
        return _psContract;
    }

    @Override
    public void resetOriginalValues() {
        _psContract.resetOriginalValues();
    }
}
