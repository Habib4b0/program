package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link ImtdSalesBasisDetails}.
 * </p>
 *
 * @author
 * @see ImtdSalesBasisDetails
 * @generated
 */
public class ImtdSalesBasisDetailsWrapper implements ImtdSalesBasisDetails,
    ModelWrapper<ImtdSalesBasisDetails> {
    private ImtdSalesBasisDetails _imtdSalesBasisDetails;

    public ImtdSalesBasisDetailsWrapper(
        ImtdSalesBasisDetails imtdSalesBasisDetails) {
        _imtdSalesBasisDetails = imtdSalesBasisDetails;
    }

    @Override
    public Class<?> getModelClass() {
        return ImtdSalesBasisDetails.class;
    }

    @Override
    public String getModelClassName() {
        return ImtdSalesBasisDetails.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("createdBy", getCreatedBy());
        attributes.put("netSalesFormulaMasterSid", getNetSalesFormulaMasterSid());
        attributes.put("usersSid", getUsersSid());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("contractMasterSid", getContractMasterSid());
        attributes.put("cfpNo", getCfpNo());
        attributes.put("imtdCreatedDate", getImtdCreatedDate());
        attributes.put("contractNo", getContractNo());
        attributes.put("contractName", getContractName());
        attributes.put("salesBasisDetailsSid", getSalesBasisDetailsSid());
        attributes.put("checkRecord", getCheckRecord());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("customerName", getCustomerName());
        attributes.put("operation", getOperation());
        attributes.put("customerNo", getCustomerNo());
        attributes.put("imtdSalesBasisDetailsSid", getImtdSalesBasisDetailsSid());
        attributes.put("cfpName", getCfpName());
        attributes.put("cdrModelSid", getCdrModelSid());
        attributes.put("sessionId", getSessionId());
        attributes.put("cfpContractDetailsSid", getCfpContractDetailsSid());
        attributes.put("inboundStatus", getInboundStatus());

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

        Integer usersSid = (Integer) attributes.get("usersSid");

        if (usersSid != null) {
            setUsersSid(usersSid);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
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

        String cfpNo = (String) attributes.get("cfpNo");

        if (cfpNo != null) {
            setCfpNo(cfpNo);
        }

        String imtdCreatedDate = (String) attributes.get("imtdCreatedDate");

        if (imtdCreatedDate != null) {
            setImtdCreatedDate(imtdCreatedDate);
        }

        String contractNo = (String) attributes.get("contractNo");

        if (contractNo != null) {
            setContractNo(contractNo);
        }

        String contractName = (String) attributes.get("contractName");

        if (contractName != null) {
            setContractName(contractName);
        }

        Integer salesBasisDetailsSid = (Integer) attributes.get(
                "salesBasisDetailsSid");

        if (salesBasisDetailsSid != null) {
            setSalesBasisDetailsSid(salesBasisDetailsSid);
        }

        Boolean checkRecord = (Boolean) attributes.get("checkRecord");

        if (checkRecord != null) {
            setCheckRecord(checkRecord);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        String customerName = (String) attributes.get("customerName");

        if (customerName != null) {
            setCustomerName(customerName);
        }

        String operation = (String) attributes.get("operation");

        if (operation != null) {
            setOperation(operation);
        }

        String customerNo = (String) attributes.get("customerNo");

        if (customerNo != null) {
            setCustomerNo(customerNo);
        }

        Integer imtdSalesBasisDetailsSid = (Integer) attributes.get(
                "imtdSalesBasisDetailsSid");

        if (imtdSalesBasisDetailsSid != null) {
            setImtdSalesBasisDetailsSid(imtdSalesBasisDetailsSid);
        }

        String cfpName = (String) attributes.get("cfpName");

        if (cfpName != null) {
            setCfpName(cfpName);
        }

        Integer cdrModelSid = (Integer) attributes.get("cdrModelSid");

        if (cdrModelSid != null) {
            setCdrModelSid(cdrModelSid);
        }

        String sessionId = (String) attributes.get("sessionId");

        if (sessionId != null) {
            setSessionId(sessionId);
        }

        Integer cfpContractDetailsSid = (Integer) attributes.get(
                "cfpContractDetailsSid");

        if (cfpContractDetailsSid != null) {
            setCfpContractDetailsSid(cfpContractDetailsSid);
        }

        String inboundStatus = (String) attributes.get("inboundStatus");

        if (inboundStatus != null) {
            setInboundStatus(inboundStatus);
        }
    }

    /**
    * Returns the primary key of this imtd sales basis details.
    *
    * @return the primary key of this imtd sales basis details
    */
    @Override
    public int getPrimaryKey() {
        return _imtdSalesBasisDetails.getPrimaryKey();
    }

    /**
    * Sets the primary key of this imtd sales basis details.
    *
    * @param primaryKey the primary key of this imtd sales basis details
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _imtdSalesBasisDetails.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the created by of this imtd sales basis details.
    *
    * @return the created by of this imtd sales basis details
    */
    @Override
    public int getCreatedBy() {
        return _imtdSalesBasisDetails.getCreatedBy();
    }

    /**
    * Sets the created by of this imtd sales basis details.
    *
    * @param createdBy the created by of this imtd sales basis details
    */
    @Override
    public void setCreatedBy(int createdBy) {
        _imtdSalesBasisDetails.setCreatedBy(createdBy);
    }

    /**
    * Returns the net sales formula master sid of this imtd sales basis details.
    *
    * @return the net sales formula master sid of this imtd sales basis details
    */
    @Override
    public int getNetSalesFormulaMasterSid() {
        return _imtdSalesBasisDetails.getNetSalesFormulaMasterSid();
    }

    /**
    * Sets the net sales formula master sid of this imtd sales basis details.
    *
    * @param netSalesFormulaMasterSid the net sales formula master sid of this imtd sales basis details
    */
    @Override
    public void setNetSalesFormulaMasterSid(int netSalesFormulaMasterSid) {
        _imtdSalesBasisDetails.setNetSalesFormulaMasterSid(netSalesFormulaMasterSid);
    }

    /**
    * Returns the users sid of this imtd sales basis details.
    *
    * @return the users sid of this imtd sales basis details
    */
    @Override
    public int getUsersSid() {
        return _imtdSalesBasisDetails.getUsersSid();
    }

    /**
    * Sets the users sid of this imtd sales basis details.
    *
    * @param usersSid the users sid of this imtd sales basis details
    */
    @Override
    public void setUsersSid(int usersSid) {
        _imtdSalesBasisDetails.setUsersSid(usersSid);
    }

    /**
    * Returns the modified by of this imtd sales basis details.
    *
    * @return the modified by of this imtd sales basis details
    */
    @Override
    public int getModifiedBy() {
        return _imtdSalesBasisDetails.getModifiedBy();
    }

    /**
    * Sets the modified by of this imtd sales basis details.
    *
    * @param modifiedBy the modified by of this imtd sales basis details
    */
    @Override
    public void setModifiedBy(int modifiedBy) {
        _imtdSalesBasisDetails.setModifiedBy(modifiedBy);
    }

    /**
    * Returns the created date of this imtd sales basis details.
    *
    * @return the created date of this imtd sales basis details
    */
    @Override
    public java.util.Date getCreatedDate() {
        return _imtdSalesBasisDetails.getCreatedDate();
    }

    /**
    * Sets the created date of this imtd sales basis details.
    *
    * @param createdDate the created date of this imtd sales basis details
    */
    @Override
    public void setCreatedDate(java.util.Date createdDate) {
        _imtdSalesBasisDetails.setCreatedDate(createdDate);
    }

    /**
    * Returns the contract master sid of this imtd sales basis details.
    *
    * @return the contract master sid of this imtd sales basis details
    */
    @Override
    public int getContractMasterSid() {
        return _imtdSalesBasisDetails.getContractMasterSid();
    }

    /**
    * Sets the contract master sid of this imtd sales basis details.
    *
    * @param contractMasterSid the contract master sid of this imtd sales basis details
    */
    @Override
    public void setContractMasterSid(int contractMasterSid) {
        _imtdSalesBasisDetails.setContractMasterSid(contractMasterSid);
    }

    /**
    * Returns the cfp no of this imtd sales basis details.
    *
    * @return the cfp no of this imtd sales basis details
    */
    @Override
    public java.lang.String getCfpNo() {
        return _imtdSalesBasisDetails.getCfpNo();
    }

    /**
    * Sets the cfp no of this imtd sales basis details.
    *
    * @param cfpNo the cfp no of this imtd sales basis details
    */
    @Override
    public void setCfpNo(java.lang.String cfpNo) {
        _imtdSalesBasisDetails.setCfpNo(cfpNo);
    }

    /**
    * Returns the imtd created date of this imtd sales basis details.
    *
    * @return the imtd created date of this imtd sales basis details
    */
    @Override
    public java.lang.String getImtdCreatedDate() {
        return _imtdSalesBasisDetails.getImtdCreatedDate();
    }

    /**
    * Sets the imtd created date of this imtd sales basis details.
    *
    * @param imtdCreatedDate the imtd created date of this imtd sales basis details
    */
    @Override
    public void setImtdCreatedDate(java.lang.String imtdCreatedDate) {
        _imtdSalesBasisDetails.setImtdCreatedDate(imtdCreatedDate);
    }

    /**
    * Returns the contract no of this imtd sales basis details.
    *
    * @return the contract no of this imtd sales basis details
    */
    @Override
    public java.lang.String getContractNo() {
        return _imtdSalesBasisDetails.getContractNo();
    }

    /**
    * Sets the contract no of this imtd sales basis details.
    *
    * @param contractNo the contract no of this imtd sales basis details
    */
    @Override
    public void setContractNo(java.lang.String contractNo) {
        _imtdSalesBasisDetails.setContractNo(contractNo);
    }

    /**
    * Returns the contract name of this imtd sales basis details.
    *
    * @return the contract name of this imtd sales basis details
    */
    @Override
    public java.lang.String getContractName() {
        return _imtdSalesBasisDetails.getContractName();
    }

    /**
    * Sets the contract name of this imtd sales basis details.
    *
    * @param contractName the contract name of this imtd sales basis details
    */
    @Override
    public void setContractName(java.lang.String contractName) {
        _imtdSalesBasisDetails.setContractName(contractName);
    }

    /**
    * Returns the sales basis details sid of this imtd sales basis details.
    *
    * @return the sales basis details sid of this imtd sales basis details
    */
    @Override
    public int getSalesBasisDetailsSid() {
        return _imtdSalesBasisDetails.getSalesBasisDetailsSid();
    }

    /**
    * Sets the sales basis details sid of this imtd sales basis details.
    *
    * @param salesBasisDetailsSid the sales basis details sid of this imtd sales basis details
    */
    @Override
    public void setSalesBasisDetailsSid(int salesBasisDetailsSid) {
        _imtdSalesBasisDetails.setSalesBasisDetailsSid(salesBasisDetailsSid);
    }

    /**
    * Returns the check record of this imtd sales basis details.
    *
    * @return the check record of this imtd sales basis details
    */
    @Override
    public boolean getCheckRecord() {
        return _imtdSalesBasisDetails.getCheckRecord();
    }

    /**
    * Returns <code>true</code> if this imtd sales basis details is check record.
    *
    * @return <code>true</code> if this imtd sales basis details is check record; <code>false</code> otherwise
    */
    @Override
    public boolean isCheckRecord() {
        return _imtdSalesBasisDetails.isCheckRecord();
    }

    /**
    * Sets whether this imtd sales basis details is check record.
    *
    * @param checkRecord the check record of this imtd sales basis details
    */
    @Override
    public void setCheckRecord(boolean checkRecord) {
        _imtdSalesBasisDetails.setCheckRecord(checkRecord);
    }

    /**
    * Returns the modified date of this imtd sales basis details.
    *
    * @return the modified date of this imtd sales basis details
    */
    @Override
    public java.util.Date getModifiedDate() {
        return _imtdSalesBasisDetails.getModifiedDate();
    }

    /**
    * Sets the modified date of this imtd sales basis details.
    *
    * @param modifiedDate the modified date of this imtd sales basis details
    */
    @Override
    public void setModifiedDate(java.util.Date modifiedDate) {
        _imtdSalesBasisDetails.setModifiedDate(modifiedDate);
    }

    /**
    * Returns the customer name of this imtd sales basis details.
    *
    * @return the customer name of this imtd sales basis details
    */
    @Override
    public java.lang.String getCustomerName() {
        return _imtdSalesBasisDetails.getCustomerName();
    }

    /**
    * Sets the customer name of this imtd sales basis details.
    *
    * @param customerName the customer name of this imtd sales basis details
    */
    @Override
    public void setCustomerName(java.lang.String customerName) {
        _imtdSalesBasisDetails.setCustomerName(customerName);
    }

    /**
    * Returns the operation of this imtd sales basis details.
    *
    * @return the operation of this imtd sales basis details
    */
    @Override
    public java.lang.String getOperation() {
        return _imtdSalesBasisDetails.getOperation();
    }

    /**
    * Sets the operation of this imtd sales basis details.
    *
    * @param operation the operation of this imtd sales basis details
    */
    @Override
    public void setOperation(java.lang.String operation) {
        _imtdSalesBasisDetails.setOperation(operation);
    }

    /**
    * Returns the customer no of this imtd sales basis details.
    *
    * @return the customer no of this imtd sales basis details
    */
    @Override
    public java.lang.String getCustomerNo() {
        return _imtdSalesBasisDetails.getCustomerNo();
    }

    /**
    * Sets the customer no of this imtd sales basis details.
    *
    * @param customerNo the customer no of this imtd sales basis details
    */
    @Override
    public void setCustomerNo(java.lang.String customerNo) {
        _imtdSalesBasisDetails.setCustomerNo(customerNo);
    }

    /**
    * Returns the imtd sales basis details sid of this imtd sales basis details.
    *
    * @return the imtd sales basis details sid of this imtd sales basis details
    */
    @Override
    public int getImtdSalesBasisDetailsSid() {
        return _imtdSalesBasisDetails.getImtdSalesBasisDetailsSid();
    }

    /**
    * Sets the imtd sales basis details sid of this imtd sales basis details.
    *
    * @param imtdSalesBasisDetailsSid the imtd sales basis details sid of this imtd sales basis details
    */
    @Override
    public void setImtdSalesBasisDetailsSid(int imtdSalesBasisDetailsSid) {
        _imtdSalesBasisDetails.setImtdSalesBasisDetailsSid(imtdSalesBasisDetailsSid);
    }

    /**
    * Returns the cfp name of this imtd sales basis details.
    *
    * @return the cfp name of this imtd sales basis details
    */
    @Override
    public java.lang.String getCfpName() {
        return _imtdSalesBasisDetails.getCfpName();
    }

    /**
    * Sets the cfp name of this imtd sales basis details.
    *
    * @param cfpName the cfp name of this imtd sales basis details
    */
    @Override
    public void setCfpName(java.lang.String cfpName) {
        _imtdSalesBasisDetails.setCfpName(cfpName);
    }

    /**
    * Returns the cdr model sid of this imtd sales basis details.
    *
    * @return the cdr model sid of this imtd sales basis details
    */
    @Override
    public int getCdrModelSid() {
        return _imtdSalesBasisDetails.getCdrModelSid();
    }

    /**
    * Sets the cdr model sid of this imtd sales basis details.
    *
    * @param cdrModelSid the cdr model sid of this imtd sales basis details
    */
    @Override
    public void setCdrModelSid(int cdrModelSid) {
        _imtdSalesBasisDetails.setCdrModelSid(cdrModelSid);
    }

    /**
    * Returns the session ID of this imtd sales basis details.
    *
    * @return the session ID of this imtd sales basis details
    */
    @Override
    public java.lang.String getSessionId() {
        return _imtdSalesBasisDetails.getSessionId();
    }

    /**
    * Sets the session ID of this imtd sales basis details.
    *
    * @param sessionId the session ID of this imtd sales basis details
    */
    @Override
    public void setSessionId(java.lang.String sessionId) {
        _imtdSalesBasisDetails.setSessionId(sessionId);
    }

    /**
    * Returns the cfp contract details sid of this imtd sales basis details.
    *
    * @return the cfp contract details sid of this imtd sales basis details
    */
    @Override
    public int getCfpContractDetailsSid() {
        return _imtdSalesBasisDetails.getCfpContractDetailsSid();
    }

    /**
    * Sets the cfp contract details sid of this imtd sales basis details.
    *
    * @param cfpContractDetailsSid the cfp contract details sid of this imtd sales basis details
    */
    @Override
    public void setCfpContractDetailsSid(int cfpContractDetailsSid) {
        _imtdSalesBasisDetails.setCfpContractDetailsSid(cfpContractDetailsSid);
    }

    /**
    * Returns the inbound status of this imtd sales basis details.
    *
    * @return the inbound status of this imtd sales basis details
    */
    @Override
    public java.lang.String getInboundStatus() {
        return _imtdSalesBasisDetails.getInboundStatus();
    }

    /**
    * Sets the inbound status of this imtd sales basis details.
    *
    * @param inboundStatus the inbound status of this imtd sales basis details
    */
    @Override
    public void setInboundStatus(java.lang.String inboundStatus) {
        _imtdSalesBasisDetails.setInboundStatus(inboundStatus);
    }

    @Override
    public boolean isNew() {
        return _imtdSalesBasisDetails.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _imtdSalesBasisDetails.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _imtdSalesBasisDetails.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _imtdSalesBasisDetails.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _imtdSalesBasisDetails.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _imtdSalesBasisDetails.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _imtdSalesBasisDetails.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _imtdSalesBasisDetails.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _imtdSalesBasisDetails.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _imtdSalesBasisDetails.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _imtdSalesBasisDetails.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new ImtdSalesBasisDetailsWrapper((ImtdSalesBasisDetails) _imtdSalesBasisDetails.clone());
    }

    @Override
    public int compareTo(ImtdSalesBasisDetails imtdSalesBasisDetails) {
        return _imtdSalesBasisDetails.compareTo(imtdSalesBasisDetails);
    }

    @Override
    public int hashCode() {
        return _imtdSalesBasisDetails.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<ImtdSalesBasisDetails> toCacheModel() {
        return _imtdSalesBasisDetails.toCacheModel();
    }

    @Override
    public ImtdSalesBasisDetails toEscapedModel() {
        return new ImtdSalesBasisDetailsWrapper(_imtdSalesBasisDetails.toEscapedModel());
    }

    @Override
    public ImtdSalesBasisDetails toUnescapedModel() {
        return new ImtdSalesBasisDetailsWrapper(_imtdSalesBasisDetails.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _imtdSalesBasisDetails.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _imtdSalesBasisDetails.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _imtdSalesBasisDetails.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof ImtdSalesBasisDetailsWrapper)) {
            return false;
        }

        ImtdSalesBasisDetailsWrapper imtdSalesBasisDetailsWrapper = (ImtdSalesBasisDetailsWrapper) obj;

        if (Validator.equals(_imtdSalesBasisDetails,
                    imtdSalesBasisDetailsWrapper._imtdSalesBasisDetails)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public ImtdSalesBasisDetails getWrappedImtdSalesBasisDetails() {
        return _imtdSalesBasisDetails;
    }

    @Override
    public ImtdSalesBasisDetails getWrappedModel() {
        return _imtdSalesBasisDetails;
    }

    @Override
    public void resetOriginalValues() {
        _imtdSalesBasisDetails.resetOriginalValues();
    }
}
