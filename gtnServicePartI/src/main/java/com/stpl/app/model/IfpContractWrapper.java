package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link IfpContract}.
 * </p>
 *
 * @author
 * @see IfpContract
 * @generated
 */
public class IfpContractWrapper implements IfpContract,
    ModelWrapper<IfpContract> {
    private IfpContract _ifpContract;

    public IfpContractWrapper(IfpContract ifpContract) {
        _ifpContract = ifpContract;
    }

    @Override
    public Class<?> getModelClass() {
        return IfpContract.class;
    }

    @Override
    public String getModelClassName() {
        return IfpContract.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("cfpContractSid", getCfpContractSid());
        attributes.put("parentIfpName", getParentIfpName());
        attributes.put("ifpContractAttachedDate", getIfpContractAttachedDate());
        attributes.put("ifpStatus", getIfpStatus());
        attributes.put("ifpStartDate", getIfpStartDate());
        attributes.put("ifpContractAttachedStatus",
            getIfpContractAttachedStatus());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("ifpCategory", getIfpCategory());
        attributes.put("recordLockStatus", getRecordLockStatus());
        attributes.put("ifpEndDate", getIfpEndDate());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("source", getSource());
        attributes.put("ifpDesignation", getIfpDesignation());
        attributes.put("parentIfpId", getParentIfpId());
        attributes.put("batchId", getBatchId());
        attributes.put("contractMasterSid", getContractMasterSid());
        attributes.put("ifpType", getIfpType());
        attributes.put("ifpName", getIfpName());
        attributes.put("ifpNo", getIfpNo());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("inboundStatus", getInboundStatus());
        attributes.put("ifpContractSid", getIfpContractSid());
        attributes.put("ifpModelSid", getIfpModelSid());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String cfpContractSid = (String) attributes.get("cfpContractSid");

        if (cfpContractSid != null) {
            setCfpContractSid(cfpContractSid);
        }

        String parentIfpName = (String) attributes.get("parentIfpName");

        if (parentIfpName != null) {
            setParentIfpName(parentIfpName);
        }

        Date ifpContractAttachedDate = (Date) attributes.get(
                "ifpContractAttachedDate");

        if (ifpContractAttachedDate != null) {
            setIfpContractAttachedDate(ifpContractAttachedDate);
        }

        Integer ifpStatus = (Integer) attributes.get("ifpStatus");

        if (ifpStatus != null) {
            setIfpStatus(ifpStatus);
        }

        Date ifpStartDate = (Date) attributes.get("ifpStartDate");

        if (ifpStartDate != null) {
            setIfpStartDate(ifpStartDate);
        }

        Integer ifpContractAttachedStatus = (Integer) attributes.get(
                "ifpContractAttachedStatus");

        if (ifpContractAttachedStatus != null) {
            setIfpContractAttachedStatus(ifpContractAttachedStatus);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        Integer ifpCategory = (Integer) attributes.get("ifpCategory");

        if (ifpCategory != null) {
            setIfpCategory(ifpCategory);
        }

        Boolean recordLockStatus = (Boolean) attributes.get("recordLockStatus");

        if (recordLockStatus != null) {
            setRecordLockStatus(recordLockStatus);
        }

        Date ifpEndDate = (Date) attributes.get("ifpEndDate");

        if (ifpEndDate != null) {
            setIfpEndDate(ifpEndDate);
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

        String ifpDesignation = (String) attributes.get("ifpDesignation");

        if (ifpDesignation != null) {
            setIfpDesignation(ifpDesignation);
        }

        String parentIfpId = (String) attributes.get("parentIfpId");

        if (parentIfpId != null) {
            setParentIfpId(parentIfpId);
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

        Integer ifpType = (Integer) attributes.get("ifpType");

        if (ifpType != null) {
            setIfpType(ifpType);
        }

        String ifpName = (String) attributes.get("ifpName");

        if (ifpName != null) {
            setIfpName(ifpName);
        }

        String ifpNo = (String) attributes.get("ifpNo");

        if (ifpNo != null) {
            setIfpNo(ifpNo);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        String inboundStatus = (String) attributes.get("inboundStatus");

        if (inboundStatus != null) {
            setInboundStatus(inboundStatus);
        }

        Integer ifpContractSid = (Integer) attributes.get("ifpContractSid");

        if (ifpContractSid != null) {
            setIfpContractSid(ifpContractSid);
        }

        Integer ifpModelSid = (Integer) attributes.get("ifpModelSid");

        if (ifpModelSid != null) {
            setIfpModelSid(ifpModelSid);
        }
    }

    /**
    * Returns the primary key of this ifp contract.
    *
    * @return the primary key of this ifp contract
    */
    @Override
    public int getPrimaryKey() {
        return _ifpContract.getPrimaryKey();
    }

    /**
    * Sets the primary key of this ifp contract.
    *
    * @param primaryKey the primary key of this ifp contract
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _ifpContract.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the cfp contract sid of this ifp contract.
    *
    * @return the cfp contract sid of this ifp contract
    */
    @Override
    public java.lang.String getCfpContractSid() {
        return _ifpContract.getCfpContractSid();
    }

    /**
    * Sets the cfp contract sid of this ifp contract.
    *
    * @param cfpContractSid the cfp contract sid of this ifp contract
    */
    @Override
    public void setCfpContractSid(java.lang.String cfpContractSid) {
        _ifpContract.setCfpContractSid(cfpContractSid);
    }

    /**
    * Returns the parent ifp name of this ifp contract.
    *
    * @return the parent ifp name of this ifp contract
    */
    @Override
    public java.lang.String getParentIfpName() {
        return _ifpContract.getParentIfpName();
    }

    /**
    * Sets the parent ifp name of this ifp contract.
    *
    * @param parentIfpName the parent ifp name of this ifp contract
    */
    @Override
    public void setParentIfpName(java.lang.String parentIfpName) {
        _ifpContract.setParentIfpName(parentIfpName);
    }

    /**
    * Returns the ifp contract attached date of this ifp contract.
    *
    * @return the ifp contract attached date of this ifp contract
    */
    @Override
    public java.util.Date getIfpContractAttachedDate() {
        return _ifpContract.getIfpContractAttachedDate();
    }

    /**
    * Sets the ifp contract attached date of this ifp contract.
    *
    * @param ifpContractAttachedDate the ifp contract attached date of this ifp contract
    */
    @Override
    public void setIfpContractAttachedDate(
        java.util.Date ifpContractAttachedDate) {
        _ifpContract.setIfpContractAttachedDate(ifpContractAttachedDate);
    }

    /**
    * Returns the ifp status of this ifp contract.
    *
    * @return the ifp status of this ifp contract
    */
    @Override
    public int getIfpStatus() {
        return _ifpContract.getIfpStatus();
    }

    /**
    * Sets the ifp status of this ifp contract.
    *
    * @param ifpStatus the ifp status of this ifp contract
    */
    @Override
    public void setIfpStatus(int ifpStatus) {
        _ifpContract.setIfpStatus(ifpStatus);
    }

    /**
    * Returns the ifp start date of this ifp contract.
    *
    * @return the ifp start date of this ifp contract
    */
    @Override
    public java.util.Date getIfpStartDate() {
        return _ifpContract.getIfpStartDate();
    }

    /**
    * Sets the ifp start date of this ifp contract.
    *
    * @param ifpStartDate the ifp start date of this ifp contract
    */
    @Override
    public void setIfpStartDate(java.util.Date ifpStartDate) {
        _ifpContract.setIfpStartDate(ifpStartDate);
    }

    /**
    * Returns the ifp contract attached status of this ifp contract.
    *
    * @return the ifp contract attached status of this ifp contract
    */
    @Override
    public int getIfpContractAttachedStatus() {
        return _ifpContract.getIfpContractAttachedStatus();
    }

    /**
    * Sets the ifp contract attached status of this ifp contract.
    *
    * @param ifpContractAttachedStatus the ifp contract attached status of this ifp contract
    */
    @Override
    public void setIfpContractAttachedStatus(int ifpContractAttachedStatus) {
        _ifpContract.setIfpContractAttachedStatus(ifpContractAttachedStatus);
    }

    /**
    * Returns the modified date of this ifp contract.
    *
    * @return the modified date of this ifp contract
    */
    @Override
    public java.util.Date getModifiedDate() {
        return _ifpContract.getModifiedDate();
    }

    /**
    * Sets the modified date of this ifp contract.
    *
    * @param modifiedDate the modified date of this ifp contract
    */
    @Override
    public void setModifiedDate(java.util.Date modifiedDate) {
        _ifpContract.setModifiedDate(modifiedDate);
    }

    /**
    * Returns the ifp category of this ifp contract.
    *
    * @return the ifp category of this ifp contract
    */
    @Override
    public int getIfpCategory() {
        return _ifpContract.getIfpCategory();
    }

    /**
    * Sets the ifp category of this ifp contract.
    *
    * @param ifpCategory the ifp category of this ifp contract
    */
    @Override
    public void setIfpCategory(int ifpCategory) {
        _ifpContract.setIfpCategory(ifpCategory);
    }

    /**
    * Returns the record lock status of this ifp contract.
    *
    * @return the record lock status of this ifp contract
    */
    @Override
    public boolean getRecordLockStatus() {
        return _ifpContract.getRecordLockStatus();
    }

    /**
    * Returns <code>true</code> if this ifp contract is record lock status.
    *
    * @return <code>true</code> if this ifp contract is record lock status; <code>false</code> otherwise
    */
    @Override
    public boolean isRecordLockStatus() {
        return _ifpContract.isRecordLockStatus();
    }

    /**
    * Sets whether this ifp contract is record lock status.
    *
    * @param recordLockStatus the record lock status of this ifp contract
    */
    @Override
    public void setRecordLockStatus(boolean recordLockStatus) {
        _ifpContract.setRecordLockStatus(recordLockStatus);
    }

    /**
    * Returns the ifp end date of this ifp contract.
    *
    * @return the ifp end date of this ifp contract
    */
    @Override
    public java.util.Date getIfpEndDate() {
        return _ifpContract.getIfpEndDate();
    }

    /**
    * Sets the ifp end date of this ifp contract.
    *
    * @param ifpEndDate the ifp end date of this ifp contract
    */
    @Override
    public void setIfpEndDate(java.util.Date ifpEndDate) {
        _ifpContract.setIfpEndDate(ifpEndDate);
    }

    /**
    * Returns the created date of this ifp contract.
    *
    * @return the created date of this ifp contract
    */
    @Override
    public java.util.Date getCreatedDate() {
        return _ifpContract.getCreatedDate();
    }

    /**
    * Sets the created date of this ifp contract.
    *
    * @param createdDate the created date of this ifp contract
    */
    @Override
    public void setCreatedDate(java.util.Date createdDate) {
        _ifpContract.setCreatedDate(createdDate);
    }

    /**
    * Returns the created by of this ifp contract.
    *
    * @return the created by of this ifp contract
    */
    @Override
    public int getCreatedBy() {
        return _ifpContract.getCreatedBy();
    }

    /**
    * Sets the created by of this ifp contract.
    *
    * @param createdBy the created by of this ifp contract
    */
    @Override
    public void setCreatedBy(int createdBy) {
        _ifpContract.setCreatedBy(createdBy);
    }

    /**
    * Returns the source of this ifp contract.
    *
    * @return the source of this ifp contract
    */
    @Override
    public java.lang.String getSource() {
        return _ifpContract.getSource();
    }

    /**
    * Sets the source of this ifp contract.
    *
    * @param source the source of this ifp contract
    */
    @Override
    public void setSource(java.lang.String source) {
        _ifpContract.setSource(source);
    }

    /**
    * Returns the ifp designation of this ifp contract.
    *
    * @return the ifp designation of this ifp contract
    */
    @Override
    public java.lang.String getIfpDesignation() {
        return _ifpContract.getIfpDesignation();
    }

    /**
    * Sets the ifp designation of this ifp contract.
    *
    * @param ifpDesignation the ifp designation of this ifp contract
    */
    @Override
    public void setIfpDesignation(java.lang.String ifpDesignation) {
        _ifpContract.setIfpDesignation(ifpDesignation);
    }

    /**
    * Returns the parent ifp ID of this ifp contract.
    *
    * @return the parent ifp ID of this ifp contract
    */
    @Override
    public java.lang.String getParentIfpId() {
        return _ifpContract.getParentIfpId();
    }

    /**
    * Sets the parent ifp ID of this ifp contract.
    *
    * @param parentIfpId the parent ifp ID of this ifp contract
    */
    @Override
    public void setParentIfpId(java.lang.String parentIfpId) {
        _ifpContract.setParentIfpId(parentIfpId);
    }

    /**
    * Returns the batch ID of this ifp contract.
    *
    * @return the batch ID of this ifp contract
    */
    @Override
    public java.lang.String getBatchId() {
        return _ifpContract.getBatchId();
    }

    /**
    * Sets the batch ID of this ifp contract.
    *
    * @param batchId the batch ID of this ifp contract
    */
    @Override
    public void setBatchId(java.lang.String batchId) {
        _ifpContract.setBatchId(batchId);
    }

    /**
    * Returns the contract master sid of this ifp contract.
    *
    * @return the contract master sid of this ifp contract
    */
    @Override
    public int getContractMasterSid() {
        return _ifpContract.getContractMasterSid();
    }

    /**
    * Sets the contract master sid of this ifp contract.
    *
    * @param contractMasterSid the contract master sid of this ifp contract
    */
    @Override
    public void setContractMasterSid(int contractMasterSid) {
        _ifpContract.setContractMasterSid(contractMasterSid);
    }

    /**
    * Returns the ifp type of this ifp contract.
    *
    * @return the ifp type of this ifp contract
    */
    @Override
    public int getIfpType() {
        return _ifpContract.getIfpType();
    }

    /**
    * Sets the ifp type of this ifp contract.
    *
    * @param ifpType the ifp type of this ifp contract
    */
    @Override
    public void setIfpType(int ifpType) {
        _ifpContract.setIfpType(ifpType);
    }

    /**
    * Returns the ifp name of this ifp contract.
    *
    * @return the ifp name of this ifp contract
    */
    @Override
    public java.lang.String getIfpName() {
        return _ifpContract.getIfpName();
    }

    /**
    * Sets the ifp name of this ifp contract.
    *
    * @param ifpName the ifp name of this ifp contract
    */
    @Override
    public void setIfpName(java.lang.String ifpName) {
        _ifpContract.setIfpName(ifpName);
    }

    /**
    * Returns the ifp no of this ifp contract.
    *
    * @return the ifp no of this ifp contract
    */
    @Override
    public java.lang.String getIfpNo() {
        return _ifpContract.getIfpNo();
    }

    /**
    * Sets the ifp no of this ifp contract.
    *
    * @param ifpNo the ifp no of this ifp contract
    */
    @Override
    public void setIfpNo(java.lang.String ifpNo) {
        _ifpContract.setIfpNo(ifpNo);
    }

    /**
    * Returns the modified by of this ifp contract.
    *
    * @return the modified by of this ifp contract
    */
    @Override
    public int getModifiedBy() {
        return _ifpContract.getModifiedBy();
    }

    /**
    * Sets the modified by of this ifp contract.
    *
    * @param modifiedBy the modified by of this ifp contract
    */
    @Override
    public void setModifiedBy(int modifiedBy) {
        _ifpContract.setModifiedBy(modifiedBy);
    }

    /**
    * Returns the inbound status of this ifp contract.
    *
    * @return the inbound status of this ifp contract
    */
    @Override
    public java.lang.String getInboundStatus() {
        return _ifpContract.getInboundStatus();
    }

    /**
    * Sets the inbound status of this ifp contract.
    *
    * @param inboundStatus the inbound status of this ifp contract
    */
    @Override
    public void setInboundStatus(java.lang.String inboundStatus) {
        _ifpContract.setInboundStatus(inboundStatus);
    }

    /**
    * Returns the ifp contract sid of this ifp contract.
    *
    * @return the ifp contract sid of this ifp contract
    */
    @Override
    public int getIfpContractSid() {
        return _ifpContract.getIfpContractSid();
    }

    /**
    * Sets the ifp contract sid of this ifp contract.
    *
    * @param ifpContractSid the ifp contract sid of this ifp contract
    */
    @Override
    public void setIfpContractSid(int ifpContractSid) {
        _ifpContract.setIfpContractSid(ifpContractSid);
    }

    /**
    * Returns the ifp model sid of this ifp contract.
    *
    * @return the ifp model sid of this ifp contract
    */
    @Override
    public int getIfpModelSid() {
        return _ifpContract.getIfpModelSid();
    }

    /**
    * Sets the ifp model sid of this ifp contract.
    *
    * @param ifpModelSid the ifp model sid of this ifp contract
    */
    @Override
    public void setIfpModelSid(int ifpModelSid) {
        _ifpContract.setIfpModelSid(ifpModelSid);
    }

    @Override
    public boolean isNew() {
        return _ifpContract.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _ifpContract.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _ifpContract.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _ifpContract.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _ifpContract.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _ifpContract.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _ifpContract.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _ifpContract.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _ifpContract.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _ifpContract.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _ifpContract.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new IfpContractWrapper((IfpContract) _ifpContract.clone());
    }

    @Override
    public int compareTo(IfpContract ifpContract) {
        return _ifpContract.compareTo(ifpContract);
    }

    @Override
    public int hashCode() {
        return _ifpContract.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<IfpContract> toCacheModel() {
        return _ifpContract.toCacheModel();
    }

    @Override
    public IfpContract toEscapedModel() {
        return new IfpContractWrapper(_ifpContract.toEscapedModel());
    }

    @Override
    public IfpContract toUnescapedModel() {
        return new IfpContractWrapper(_ifpContract.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _ifpContract.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _ifpContract.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _ifpContract.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof IfpContractWrapper)) {
            return false;
        }

        IfpContractWrapper ifpContractWrapper = (IfpContractWrapper) obj;

        if (Validator.equals(_ifpContract, ifpContractWrapper._ifpContract)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public IfpContract getWrappedIfpContract() {
        return _ifpContract;
    }

    @Override
    public IfpContract getWrappedModel() {
        return _ifpContract;
    }

    @Override
    public void resetOriginalValues() {
        _ifpContract.resetOriginalValues();
    }
}
