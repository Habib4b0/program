package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link ContractAliasMaster}.
 * </p>
 *
 * @author
 * @see ContractAliasMaster
 * @generated
 */
public class ContractAliasMasterWrapper implements ContractAliasMaster,
    ModelWrapper<ContractAliasMaster> {
    private ContractAliasMaster _contractAliasMaster;

    public ContractAliasMasterWrapper(ContractAliasMaster contractAliasMaster) {
        _contractAliasMaster = contractAliasMaster;
    }

    @Override
    public Class<?> getModelClass() {
        return ContractAliasMaster.class;
    }

    @Override
    public String getModelClassName() {
        return ContractAliasMaster.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("contractAliasType", getContractAliasType());
        attributes.put("tpCompanyMasterSid", getTpCompanyMasterSid());
        attributes.put("endDate", getEndDate());
        attributes.put("contractAliasMasterSid", getContractAliasMasterSid());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("contractAliasNo", getContractAliasNo());
        attributes.put("recordLockStatus", getRecordLockStatus());
        attributes.put("startDate", getStartDate());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("source", getSource());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("contractMasterSid", getContractMasterSid());
        attributes.put("batchId", getBatchId());
        attributes.put("contractAliasName", getContractAliasName());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("inboundStatus", getInboundStatus());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Integer contractAliasType = (Integer) attributes.get(
                "contractAliasType");

        if (contractAliasType != null) {
            setContractAliasType(contractAliasType);
        }

        Integer tpCompanyMasterSid = (Integer) attributes.get(
                "tpCompanyMasterSid");

        if (tpCompanyMasterSid != null) {
            setTpCompanyMasterSid(tpCompanyMasterSid);
        }

        Date endDate = (Date) attributes.get("endDate");

        if (endDate != null) {
            setEndDate(endDate);
        }

        Integer contractAliasMasterSid = (Integer) attributes.get(
                "contractAliasMasterSid");

        if (contractAliasMasterSid != null) {
            setContractAliasMasterSid(contractAliasMasterSid);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        String contractAliasNo = (String) attributes.get("contractAliasNo");

        if (contractAliasNo != null) {
            setContractAliasNo(contractAliasNo);
        }

        Boolean recordLockStatus = (Boolean) attributes.get("recordLockStatus");

        if (recordLockStatus != null) {
            setRecordLockStatus(recordLockStatus);
        }

        Date startDate = (Date) attributes.get("startDate");

        if (startDate != null) {
            setStartDate(startDate);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        String source = (String) attributes.get("source");

        if (source != null) {
            setSource(source);
        }

        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        Integer contractMasterSid = (Integer) attributes.get(
                "contractMasterSid");

        if (contractMasterSid != null) {
            setContractMasterSid(contractMasterSid);
        }

        String batchId = (String) attributes.get("batchId");

        if (batchId != null) {
            setBatchId(batchId);
        }

        String contractAliasName = (String) attributes.get("contractAliasName");

        if (contractAliasName != null) {
            setContractAliasName(contractAliasName);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        String inboundStatus = (String) attributes.get("inboundStatus");

        if (inboundStatus != null) {
            setInboundStatus(inboundStatus);
        }
    }

    /**
    * Returns the primary key of this contract alias master.
    *
    * @return the primary key of this contract alias master
    */
    @Override
    public int getPrimaryKey() {
        return _contractAliasMaster.getPrimaryKey();
    }

    /**
    * Sets the primary key of this contract alias master.
    *
    * @param primaryKey the primary key of this contract alias master
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _contractAliasMaster.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the contract alias type of this contract alias master.
    *
    * @return the contract alias type of this contract alias master
    */
    @Override
    public int getContractAliasType() {
        return _contractAliasMaster.getContractAliasType();
    }

    /**
    * Sets the contract alias type of this contract alias master.
    *
    * @param contractAliasType the contract alias type of this contract alias master
    */
    @Override
    public void setContractAliasType(int contractAliasType) {
        _contractAliasMaster.setContractAliasType(contractAliasType);
    }

    /**
    * Returns the tp company master sid of this contract alias master.
    *
    * @return the tp company master sid of this contract alias master
    */
    @Override
    public int getTpCompanyMasterSid() {
        return _contractAliasMaster.getTpCompanyMasterSid();
    }

    /**
    * Sets the tp company master sid of this contract alias master.
    *
    * @param tpCompanyMasterSid the tp company master sid of this contract alias master
    */
    @Override
    public void setTpCompanyMasterSid(int tpCompanyMasterSid) {
        _contractAliasMaster.setTpCompanyMasterSid(tpCompanyMasterSid);
    }

    /**
    * Returns the end date of this contract alias master.
    *
    * @return the end date of this contract alias master
    */
    @Override
    public java.util.Date getEndDate() {
        return _contractAliasMaster.getEndDate();
    }

    /**
    * Sets the end date of this contract alias master.
    *
    * @param endDate the end date of this contract alias master
    */
    @Override
    public void setEndDate(java.util.Date endDate) {
        _contractAliasMaster.setEndDate(endDate);
    }

    /**
    * Returns the contract alias master sid of this contract alias master.
    *
    * @return the contract alias master sid of this contract alias master
    */
    @Override
    public int getContractAliasMasterSid() {
        return _contractAliasMaster.getContractAliasMasterSid();
    }

    /**
    * Sets the contract alias master sid of this contract alias master.
    *
    * @param contractAliasMasterSid the contract alias master sid of this contract alias master
    */
    @Override
    public void setContractAliasMasterSid(int contractAliasMasterSid) {
        _contractAliasMaster.setContractAliasMasterSid(contractAliasMasterSid);
    }

    /**
    * Returns the modified date of this contract alias master.
    *
    * @return the modified date of this contract alias master
    */
    @Override
    public java.util.Date getModifiedDate() {
        return _contractAliasMaster.getModifiedDate();
    }

    /**
    * Sets the modified date of this contract alias master.
    *
    * @param modifiedDate the modified date of this contract alias master
    */
    @Override
    public void setModifiedDate(java.util.Date modifiedDate) {
        _contractAliasMaster.setModifiedDate(modifiedDate);
    }

    /**
    * Returns the contract alias no of this contract alias master.
    *
    * @return the contract alias no of this contract alias master
    */
    @Override
    public java.lang.String getContractAliasNo() {
        return _contractAliasMaster.getContractAliasNo();
    }

    /**
    * Sets the contract alias no of this contract alias master.
    *
    * @param contractAliasNo the contract alias no of this contract alias master
    */
    @Override
    public void setContractAliasNo(java.lang.String contractAliasNo) {
        _contractAliasMaster.setContractAliasNo(contractAliasNo);
    }

    /**
    * Returns the record lock status of this contract alias master.
    *
    * @return the record lock status of this contract alias master
    */
    @Override
    public boolean getRecordLockStatus() {
        return _contractAliasMaster.getRecordLockStatus();
    }

    /**
    * Returns <code>true</code> if this contract alias master is record lock status.
    *
    * @return <code>true</code> if this contract alias master is record lock status; <code>false</code> otherwise
    */
    @Override
    public boolean isRecordLockStatus() {
        return _contractAliasMaster.isRecordLockStatus();
    }

    /**
    * Sets whether this contract alias master is record lock status.
    *
    * @param recordLockStatus the record lock status of this contract alias master
    */
    @Override
    public void setRecordLockStatus(boolean recordLockStatus) {
        _contractAliasMaster.setRecordLockStatus(recordLockStatus);
    }

    /**
    * Returns the start date of this contract alias master.
    *
    * @return the start date of this contract alias master
    */
    @Override
    public java.util.Date getStartDate() {
        return _contractAliasMaster.getStartDate();
    }

    /**
    * Sets the start date of this contract alias master.
    *
    * @param startDate the start date of this contract alias master
    */
    @Override
    public void setStartDate(java.util.Date startDate) {
        _contractAliasMaster.setStartDate(startDate);
    }

    /**
    * Returns the created date of this contract alias master.
    *
    * @return the created date of this contract alias master
    */
    @Override
    public java.util.Date getCreatedDate() {
        return _contractAliasMaster.getCreatedDate();
    }

    /**
    * Sets the created date of this contract alias master.
    *
    * @param createdDate the created date of this contract alias master
    */
    @Override
    public void setCreatedDate(java.util.Date createdDate) {
        _contractAliasMaster.setCreatedDate(createdDate);
    }

    /**
    * Returns the source of this contract alias master.
    *
    * @return the source of this contract alias master
    */
    @Override
    public java.lang.String getSource() {
        return _contractAliasMaster.getSource();
    }

    /**
    * Sets the source of this contract alias master.
    *
    * @param source the source of this contract alias master
    */
    @Override
    public void setSource(java.lang.String source) {
        _contractAliasMaster.setSource(source);
    }

    /**
    * Returns the created by of this contract alias master.
    *
    * @return the created by of this contract alias master
    */
    @Override
    public int getCreatedBy() {
        return _contractAliasMaster.getCreatedBy();
    }

    /**
    * Sets the created by of this contract alias master.
    *
    * @param createdBy the created by of this contract alias master
    */
    @Override
    public void setCreatedBy(int createdBy) {
        _contractAliasMaster.setCreatedBy(createdBy);
    }

    /**
    * Returns the contract master sid of this contract alias master.
    *
    * @return the contract master sid of this contract alias master
    */
    @Override
    public int getContractMasterSid() {
        return _contractAliasMaster.getContractMasterSid();
    }

    /**
    * Sets the contract master sid of this contract alias master.
    *
    * @param contractMasterSid the contract master sid of this contract alias master
    */
    @Override
    public void setContractMasterSid(int contractMasterSid) {
        _contractAliasMaster.setContractMasterSid(contractMasterSid);
    }

    /**
    * Returns the batch ID of this contract alias master.
    *
    * @return the batch ID of this contract alias master
    */
    @Override
    public java.lang.String getBatchId() {
        return _contractAliasMaster.getBatchId();
    }

    /**
    * Sets the batch ID of this contract alias master.
    *
    * @param batchId the batch ID of this contract alias master
    */
    @Override
    public void setBatchId(java.lang.String batchId) {
        _contractAliasMaster.setBatchId(batchId);
    }

    /**
    * Returns the contract alias name of this contract alias master.
    *
    * @return the contract alias name of this contract alias master
    */
    @Override
    public java.lang.String getContractAliasName() {
        return _contractAliasMaster.getContractAliasName();
    }

    /**
    * Sets the contract alias name of this contract alias master.
    *
    * @param contractAliasName the contract alias name of this contract alias master
    */
    @Override
    public void setContractAliasName(java.lang.String contractAliasName) {
        _contractAliasMaster.setContractAliasName(contractAliasName);
    }

    /**
    * Returns the modified by of this contract alias master.
    *
    * @return the modified by of this contract alias master
    */
    @Override
    public int getModifiedBy() {
        return _contractAliasMaster.getModifiedBy();
    }

    /**
    * Sets the modified by of this contract alias master.
    *
    * @param modifiedBy the modified by of this contract alias master
    */
    @Override
    public void setModifiedBy(int modifiedBy) {
        _contractAliasMaster.setModifiedBy(modifiedBy);
    }

    /**
    * Returns the inbound status of this contract alias master.
    *
    * @return the inbound status of this contract alias master
    */
    @Override
    public java.lang.String getInboundStatus() {
        return _contractAliasMaster.getInboundStatus();
    }

    /**
    * Sets the inbound status of this contract alias master.
    *
    * @param inboundStatus the inbound status of this contract alias master
    */
    @Override
    public void setInboundStatus(java.lang.String inboundStatus) {
        _contractAliasMaster.setInboundStatus(inboundStatus);
    }

    @Override
    public boolean isNew() {
        return _contractAliasMaster.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _contractAliasMaster.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _contractAliasMaster.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _contractAliasMaster.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _contractAliasMaster.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _contractAliasMaster.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _contractAliasMaster.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _contractAliasMaster.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _contractAliasMaster.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _contractAliasMaster.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _contractAliasMaster.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new ContractAliasMasterWrapper((ContractAliasMaster) _contractAliasMaster.clone());
    }

    @Override
    public int compareTo(ContractAliasMaster contractAliasMaster) {
        return _contractAliasMaster.compareTo(contractAliasMaster);
    }

    @Override
    public int hashCode() {
        return _contractAliasMaster.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<ContractAliasMaster> toCacheModel() {
        return _contractAliasMaster.toCacheModel();
    }

    @Override
    public ContractAliasMaster toEscapedModel() {
        return new ContractAliasMasterWrapper(_contractAliasMaster.toEscapedModel());
    }

    @Override
    public ContractAliasMaster toUnescapedModel() {
        return new ContractAliasMasterWrapper(_contractAliasMaster.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _contractAliasMaster.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _contractAliasMaster.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _contractAliasMaster.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof ContractAliasMasterWrapper)) {
            return false;
        }

        ContractAliasMasterWrapper contractAliasMasterWrapper = (ContractAliasMasterWrapper) obj;

        if (Validator.equals(_contractAliasMaster,
                    contractAliasMasterWrapper._contractAliasMaster)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public ContractAliasMaster getWrappedContractAliasMaster() {
        return _contractAliasMaster;
    }

    @Override
    public ContractAliasMaster getWrappedModel() {
        return _contractAliasMaster;
    }

    @Override
    public void resetOriginalValues() {
        _contractAliasMaster.resetOriginalValues();
    }
}
