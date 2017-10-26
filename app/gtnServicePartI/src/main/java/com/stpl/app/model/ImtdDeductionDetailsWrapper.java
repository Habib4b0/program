package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link ImtdDeductionDetails}.
 * </p>
 *
 * @author
 * @see ImtdDeductionDetails
 * @generated
 */
public class ImtdDeductionDetailsWrapper implements ImtdDeductionDetails,
    ModelWrapper<ImtdDeductionDetails> {
    private ImtdDeductionDetails _imtdDeductionDetails;

    public ImtdDeductionDetailsWrapper(
        ImtdDeductionDetails imtdDeductionDetails) {
        _imtdDeductionDetails = imtdDeductionDetails;
    }

    @Override
    public Class<?> getModelClass() {
        return ImtdDeductionDetails.class;
    }

    @Override
    public String getModelClassName() {
        return ImtdDeductionDetails.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("imtdDeductionDetailsSid", getImtdDeductionDetailsSid());
        attributes.put("deductionName", getDeductionName());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("imtdCreatedDate", getImtdCreatedDate());
        attributes.put("deductionDetailsSid", getDeductionDetailsSid());
        attributes.put("indicator", getIndicator());
        attributes.put("contractNo", getContractNo());
        attributes.put("checkRecord", getCheckRecord());
        attributes.put("deductionSubType", getDeductionSubType());
        attributes.put("cdrModelSid", getCdrModelSid());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("deductionNo", getDeductionNo());
        attributes.put("netSalesFormulaMasterSid", getNetSalesFormulaMasterSid());
        attributes.put("usersSid", getUsersSid());
        attributes.put("contractMasterSid", getContractMasterSid());
        attributes.put("contractName", getContractName());
        attributes.put("deductionCategory", getDeductionCategory());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("deductionType", getDeductionType());
        attributes.put("recordLockStatus", getRecordLockStatus());
        attributes.put("operation", getOperation());
        attributes.put("sessionId", getSessionId());
        attributes.put("rsContractSid", getRsContractSid());
        attributes.put("inboundStatus", getInboundStatus());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Integer imtdDeductionDetailsSid = (Integer) attributes.get(
                "imtdDeductionDetailsSid");

        if (imtdDeductionDetailsSid != null) {
            setImtdDeductionDetailsSid(imtdDeductionDetailsSid);
        }

        String deductionName = (String) attributes.get("deductionName");

        if (deductionName != null) {
            setDeductionName(deductionName);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        String imtdCreatedDate = (String) attributes.get("imtdCreatedDate");

        if (imtdCreatedDate != null) {
            setImtdCreatedDate(imtdCreatedDate);
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

        String contractNo = (String) attributes.get("contractNo");

        if (contractNo != null) {
            setContractNo(contractNo);
        }

        Boolean checkRecord = (Boolean) attributes.get("checkRecord");

        if (checkRecord != null) {
            setCheckRecord(checkRecord);
        }

        String deductionSubType = (String) attributes.get("deductionSubType");

        if (deductionSubType != null) {
            setDeductionSubType(deductionSubType);
        }

        Integer cdrModelSid = (Integer) attributes.get("cdrModelSid");

        if (cdrModelSid != null) {
            setCdrModelSid(cdrModelSid);
        }

        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        String deductionNo = (String) attributes.get("deductionNo");

        if (deductionNo != null) {
            setDeductionNo(deductionNo);
        }

        Integer netSalesFormulaMasterSid = (Integer) attributes.get(
                "netSalesFormulaMasterSid");

        if (netSalesFormulaMasterSid != null) {
            setNetSalesFormulaMasterSid(netSalesFormulaMasterSid);
        }

        Integer usersSid = (Integer) attributes.get("usersSid");

        if (usersSid != null) {
            setUsersSid(usersSid);
        }

        Integer contractMasterSid = (Integer) attributes.get(
                "contractMasterSid");

        if (contractMasterSid != null) {
            setContractMasterSid(contractMasterSid);
        }

        String contractName = (String) attributes.get("contractName");

        if (contractName != null) {
            setContractName(contractName);
        }

        String deductionCategory = (String) attributes.get("deductionCategory");

        if (deductionCategory != null) {
            setDeductionCategory(deductionCategory);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        String deductionType = (String) attributes.get("deductionType");

        if (deductionType != null) {
            setDeductionType(deductionType);
        }

        Boolean recordLockStatus = (Boolean) attributes.get("recordLockStatus");

        if (recordLockStatus != null) {
            setRecordLockStatus(recordLockStatus);
        }

        String operation = (String) attributes.get("operation");

        if (operation != null) {
            setOperation(operation);
        }

        String sessionId = (String) attributes.get("sessionId");

        if (sessionId != null) {
            setSessionId(sessionId);
        }

        Integer rsContractSid = (Integer) attributes.get("rsContractSid");

        if (rsContractSid != null) {
            setRsContractSid(rsContractSid);
        }

        String inboundStatus = (String) attributes.get("inboundStatus");

        if (inboundStatus != null) {
            setInboundStatus(inboundStatus);
        }
    }

    /**
    * Returns the primary key of this imtd deduction details.
    *
    * @return the primary key of this imtd deduction details
    */
    @Override
    public int getPrimaryKey() {
        return _imtdDeductionDetails.getPrimaryKey();
    }

    /**
    * Sets the primary key of this imtd deduction details.
    *
    * @param primaryKey the primary key of this imtd deduction details
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _imtdDeductionDetails.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the imtd deduction details sid of this imtd deduction details.
    *
    * @return the imtd deduction details sid of this imtd deduction details
    */
    @Override
    public int getImtdDeductionDetailsSid() {
        return _imtdDeductionDetails.getImtdDeductionDetailsSid();
    }

    /**
    * Sets the imtd deduction details sid of this imtd deduction details.
    *
    * @param imtdDeductionDetailsSid the imtd deduction details sid of this imtd deduction details
    */
    @Override
    public void setImtdDeductionDetailsSid(int imtdDeductionDetailsSid) {
        _imtdDeductionDetails.setImtdDeductionDetailsSid(imtdDeductionDetailsSid);
    }

    /**
    * Returns the deduction name of this imtd deduction details.
    *
    * @return the deduction name of this imtd deduction details
    */
    @Override
    public java.lang.String getDeductionName() {
        return _imtdDeductionDetails.getDeductionName();
    }

    /**
    * Sets the deduction name of this imtd deduction details.
    *
    * @param deductionName the deduction name of this imtd deduction details
    */
    @Override
    public void setDeductionName(java.lang.String deductionName) {
        _imtdDeductionDetails.setDeductionName(deductionName);
    }

    /**
    * Returns the modified by of this imtd deduction details.
    *
    * @return the modified by of this imtd deduction details
    */
    @Override
    public int getModifiedBy() {
        return _imtdDeductionDetails.getModifiedBy();
    }

    /**
    * Sets the modified by of this imtd deduction details.
    *
    * @param modifiedBy the modified by of this imtd deduction details
    */
    @Override
    public void setModifiedBy(int modifiedBy) {
        _imtdDeductionDetails.setModifiedBy(modifiedBy);
    }

    /**
    * Returns the created date of this imtd deduction details.
    *
    * @return the created date of this imtd deduction details
    */
    @Override
    public java.util.Date getCreatedDate() {
        return _imtdDeductionDetails.getCreatedDate();
    }

    /**
    * Sets the created date of this imtd deduction details.
    *
    * @param createdDate the created date of this imtd deduction details
    */
    @Override
    public void setCreatedDate(java.util.Date createdDate) {
        _imtdDeductionDetails.setCreatedDate(createdDate);
    }

    /**
    * Returns the imtd created date of this imtd deduction details.
    *
    * @return the imtd created date of this imtd deduction details
    */
    @Override
    public java.lang.String getImtdCreatedDate() {
        return _imtdDeductionDetails.getImtdCreatedDate();
    }

    /**
    * Sets the imtd created date of this imtd deduction details.
    *
    * @param imtdCreatedDate the imtd created date of this imtd deduction details
    */
    @Override
    public void setImtdCreatedDate(java.lang.String imtdCreatedDate) {
        _imtdDeductionDetails.setImtdCreatedDate(imtdCreatedDate);
    }

    /**
    * Returns the deduction details sid of this imtd deduction details.
    *
    * @return the deduction details sid of this imtd deduction details
    */
    @Override
    public int getDeductionDetailsSid() {
        return _imtdDeductionDetails.getDeductionDetailsSid();
    }

    /**
    * Sets the deduction details sid of this imtd deduction details.
    *
    * @param deductionDetailsSid the deduction details sid of this imtd deduction details
    */
    @Override
    public void setDeductionDetailsSid(int deductionDetailsSid) {
        _imtdDeductionDetails.setDeductionDetailsSid(deductionDetailsSid);
    }

    /**
    * Returns the indicator of this imtd deduction details.
    *
    * @return the indicator of this imtd deduction details
    */
    @Override
    public java.lang.String getIndicator() {
        return _imtdDeductionDetails.getIndicator();
    }

    /**
    * Sets the indicator of this imtd deduction details.
    *
    * @param indicator the indicator of this imtd deduction details
    */
    @Override
    public void setIndicator(java.lang.String indicator) {
        _imtdDeductionDetails.setIndicator(indicator);
    }

    /**
    * Returns the contract no of this imtd deduction details.
    *
    * @return the contract no of this imtd deduction details
    */
    @Override
    public java.lang.String getContractNo() {
        return _imtdDeductionDetails.getContractNo();
    }

    /**
    * Sets the contract no of this imtd deduction details.
    *
    * @param contractNo the contract no of this imtd deduction details
    */
    @Override
    public void setContractNo(java.lang.String contractNo) {
        _imtdDeductionDetails.setContractNo(contractNo);
    }

    /**
    * Returns the check record of this imtd deduction details.
    *
    * @return the check record of this imtd deduction details
    */
    @Override
    public boolean getCheckRecord() {
        return _imtdDeductionDetails.getCheckRecord();
    }

    /**
    * Returns <code>true</code> if this imtd deduction details is check record.
    *
    * @return <code>true</code> if this imtd deduction details is check record; <code>false</code> otherwise
    */
    @Override
    public boolean isCheckRecord() {
        return _imtdDeductionDetails.isCheckRecord();
    }

    /**
    * Sets whether this imtd deduction details is check record.
    *
    * @param checkRecord the check record of this imtd deduction details
    */
    @Override
    public void setCheckRecord(boolean checkRecord) {
        _imtdDeductionDetails.setCheckRecord(checkRecord);
    }

    /**
    * Returns the deduction sub type of this imtd deduction details.
    *
    * @return the deduction sub type of this imtd deduction details
    */
    @Override
    public java.lang.String getDeductionSubType() {
        return _imtdDeductionDetails.getDeductionSubType();
    }

    /**
    * Sets the deduction sub type of this imtd deduction details.
    *
    * @param deductionSubType the deduction sub type of this imtd deduction details
    */
    @Override
    public void setDeductionSubType(java.lang.String deductionSubType) {
        _imtdDeductionDetails.setDeductionSubType(deductionSubType);
    }

    /**
    * Returns the cdr model sid of this imtd deduction details.
    *
    * @return the cdr model sid of this imtd deduction details
    */
    @Override
    public int getCdrModelSid() {
        return _imtdDeductionDetails.getCdrModelSid();
    }

    /**
    * Sets the cdr model sid of this imtd deduction details.
    *
    * @param cdrModelSid the cdr model sid of this imtd deduction details
    */
    @Override
    public void setCdrModelSid(int cdrModelSid) {
        _imtdDeductionDetails.setCdrModelSid(cdrModelSid);
    }

    /**
    * Returns the created by of this imtd deduction details.
    *
    * @return the created by of this imtd deduction details
    */
    @Override
    public int getCreatedBy() {
        return _imtdDeductionDetails.getCreatedBy();
    }

    /**
    * Sets the created by of this imtd deduction details.
    *
    * @param createdBy the created by of this imtd deduction details
    */
    @Override
    public void setCreatedBy(int createdBy) {
        _imtdDeductionDetails.setCreatedBy(createdBy);
    }

    /**
    * Returns the deduction no of this imtd deduction details.
    *
    * @return the deduction no of this imtd deduction details
    */
    @Override
    public java.lang.String getDeductionNo() {
        return _imtdDeductionDetails.getDeductionNo();
    }

    /**
    * Sets the deduction no of this imtd deduction details.
    *
    * @param deductionNo the deduction no of this imtd deduction details
    */
    @Override
    public void setDeductionNo(java.lang.String deductionNo) {
        _imtdDeductionDetails.setDeductionNo(deductionNo);
    }

    /**
    * Returns the net sales formula master sid of this imtd deduction details.
    *
    * @return the net sales formula master sid of this imtd deduction details
    */
    @Override
    public int getNetSalesFormulaMasterSid() {
        return _imtdDeductionDetails.getNetSalesFormulaMasterSid();
    }

    /**
    * Sets the net sales formula master sid of this imtd deduction details.
    *
    * @param netSalesFormulaMasterSid the net sales formula master sid of this imtd deduction details
    */
    @Override
    public void setNetSalesFormulaMasterSid(int netSalesFormulaMasterSid) {
        _imtdDeductionDetails.setNetSalesFormulaMasterSid(netSalesFormulaMasterSid);
    }

    /**
    * Returns the users sid of this imtd deduction details.
    *
    * @return the users sid of this imtd deduction details
    */
    @Override
    public int getUsersSid() {
        return _imtdDeductionDetails.getUsersSid();
    }

    /**
    * Sets the users sid of this imtd deduction details.
    *
    * @param usersSid the users sid of this imtd deduction details
    */
    @Override
    public void setUsersSid(int usersSid) {
        _imtdDeductionDetails.setUsersSid(usersSid);
    }

    /**
    * Returns the contract master sid of this imtd deduction details.
    *
    * @return the contract master sid of this imtd deduction details
    */
    @Override
    public int getContractMasterSid() {
        return _imtdDeductionDetails.getContractMasterSid();
    }

    /**
    * Sets the contract master sid of this imtd deduction details.
    *
    * @param contractMasterSid the contract master sid of this imtd deduction details
    */
    @Override
    public void setContractMasterSid(int contractMasterSid) {
        _imtdDeductionDetails.setContractMasterSid(contractMasterSid);
    }

    /**
    * Returns the contract name of this imtd deduction details.
    *
    * @return the contract name of this imtd deduction details
    */
    @Override
    public java.lang.String getContractName() {
        return _imtdDeductionDetails.getContractName();
    }

    /**
    * Sets the contract name of this imtd deduction details.
    *
    * @param contractName the contract name of this imtd deduction details
    */
    @Override
    public void setContractName(java.lang.String contractName) {
        _imtdDeductionDetails.setContractName(contractName);
    }

    /**
    * Returns the deduction category of this imtd deduction details.
    *
    * @return the deduction category of this imtd deduction details
    */
    @Override
    public java.lang.String getDeductionCategory() {
        return _imtdDeductionDetails.getDeductionCategory();
    }

    /**
    * Sets the deduction category of this imtd deduction details.
    *
    * @param deductionCategory the deduction category of this imtd deduction details
    */
    @Override
    public void setDeductionCategory(java.lang.String deductionCategory) {
        _imtdDeductionDetails.setDeductionCategory(deductionCategory);
    }

    /**
    * Returns the modified date of this imtd deduction details.
    *
    * @return the modified date of this imtd deduction details
    */
    @Override
    public java.util.Date getModifiedDate() {
        return _imtdDeductionDetails.getModifiedDate();
    }

    /**
    * Sets the modified date of this imtd deduction details.
    *
    * @param modifiedDate the modified date of this imtd deduction details
    */
    @Override
    public void setModifiedDate(java.util.Date modifiedDate) {
        _imtdDeductionDetails.setModifiedDate(modifiedDate);
    }

    /**
    * Returns the deduction type of this imtd deduction details.
    *
    * @return the deduction type of this imtd deduction details
    */
    @Override
    public java.lang.String getDeductionType() {
        return _imtdDeductionDetails.getDeductionType();
    }

    /**
    * Sets the deduction type of this imtd deduction details.
    *
    * @param deductionType the deduction type of this imtd deduction details
    */
    @Override
    public void setDeductionType(java.lang.String deductionType) {
        _imtdDeductionDetails.setDeductionType(deductionType);
    }

    /**
    * Returns the record lock status of this imtd deduction details.
    *
    * @return the record lock status of this imtd deduction details
    */
    @Override
    public boolean getRecordLockStatus() {
        return _imtdDeductionDetails.getRecordLockStatus();
    }

    /**
    * Returns <code>true</code> if this imtd deduction details is record lock status.
    *
    * @return <code>true</code> if this imtd deduction details is record lock status; <code>false</code> otherwise
    */
    @Override
    public boolean isRecordLockStatus() {
        return _imtdDeductionDetails.isRecordLockStatus();
    }

    /**
    * Sets whether this imtd deduction details is record lock status.
    *
    * @param recordLockStatus the record lock status of this imtd deduction details
    */
    @Override
    public void setRecordLockStatus(boolean recordLockStatus) {
        _imtdDeductionDetails.setRecordLockStatus(recordLockStatus);
    }

    /**
    * Returns the operation of this imtd deduction details.
    *
    * @return the operation of this imtd deduction details
    */
    @Override
    public java.lang.String getOperation() {
        return _imtdDeductionDetails.getOperation();
    }

    /**
    * Sets the operation of this imtd deduction details.
    *
    * @param operation the operation of this imtd deduction details
    */
    @Override
    public void setOperation(java.lang.String operation) {
        _imtdDeductionDetails.setOperation(operation);
    }

    /**
    * Returns the session ID of this imtd deduction details.
    *
    * @return the session ID of this imtd deduction details
    */
    @Override
    public java.lang.String getSessionId() {
        return _imtdDeductionDetails.getSessionId();
    }

    /**
    * Sets the session ID of this imtd deduction details.
    *
    * @param sessionId the session ID of this imtd deduction details
    */
    @Override
    public void setSessionId(java.lang.String sessionId) {
        _imtdDeductionDetails.setSessionId(sessionId);
    }

    /**
    * Returns the rs contract sid of this imtd deduction details.
    *
    * @return the rs contract sid of this imtd deduction details
    */
    @Override
    public int getRsContractSid() {
        return _imtdDeductionDetails.getRsContractSid();
    }

    /**
    * Sets the rs contract sid of this imtd deduction details.
    *
    * @param rsContractSid the rs contract sid of this imtd deduction details
    */
    @Override
    public void setRsContractSid(int rsContractSid) {
        _imtdDeductionDetails.setRsContractSid(rsContractSid);
    }

    /**
    * Returns the inbound status of this imtd deduction details.
    *
    * @return the inbound status of this imtd deduction details
    */
    @Override
    public java.lang.String getInboundStatus() {
        return _imtdDeductionDetails.getInboundStatus();
    }

    /**
    * Sets the inbound status of this imtd deduction details.
    *
    * @param inboundStatus the inbound status of this imtd deduction details
    */
    @Override
    public void setInboundStatus(java.lang.String inboundStatus) {
        _imtdDeductionDetails.setInboundStatus(inboundStatus);
    }

    @Override
    public boolean isNew() {
        return _imtdDeductionDetails.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _imtdDeductionDetails.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _imtdDeductionDetails.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _imtdDeductionDetails.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _imtdDeductionDetails.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _imtdDeductionDetails.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _imtdDeductionDetails.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _imtdDeductionDetails.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _imtdDeductionDetails.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _imtdDeductionDetails.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _imtdDeductionDetails.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new ImtdDeductionDetailsWrapper((ImtdDeductionDetails) _imtdDeductionDetails.clone());
    }

    @Override
    public int compareTo(ImtdDeductionDetails imtdDeductionDetails) {
        return _imtdDeductionDetails.compareTo(imtdDeductionDetails);
    }

    @Override
    public int hashCode() {
        return _imtdDeductionDetails.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<ImtdDeductionDetails> toCacheModel() {
        return _imtdDeductionDetails.toCacheModel();
    }

    @Override
    public ImtdDeductionDetails toEscapedModel() {
        return new ImtdDeductionDetailsWrapper(_imtdDeductionDetails.toEscapedModel());
    }

    @Override
    public ImtdDeductionDetails toUnescapedModel() {
        return new ImtdDeductionDetailsWrapper(_imtdDeductionDetails.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _imtdDeductionDetails.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _imtdDeductionDetails.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _imtdDeductionDetails.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof ImtdDeductionDetailsWrapper)) {
            return false;
        }

        ImtdDeductionDetailsWrapper imtdDeductionDetailsWrapper = (ImtdDeductionDetailsWrapper) obj;

        if (Validator.equals(_imtdDeductionDetails,
                    imtdDeductionDetailsWrapper._imtdDeductionDetails)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public ImtdDeductionDetails getWrappedImtdDeductionDetails() {
        return _imtdDeductionDetails;
    }

    @Override
    public ImtdDeductionDetails getWrappedModel() {
        return _imtdDeductionDetails;
    }

    @Override
    public void resetOriginalValues() {
        _imtdDeductionDetails.resetOriginalValues();
    }
}
