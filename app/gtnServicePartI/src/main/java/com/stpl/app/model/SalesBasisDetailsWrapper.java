package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link SalesBasisDetails}.
 * </p>
 *
 * @author
 * @see SalesBasisDetails
 * @generated
 */
public class SalesBasisDetailsWrapper implements SalesBasisDetails,
    ModelWrapper<SalesBasisDetails> {
    private SalesBasisDetails _salesBasisDetails;

    public SalesBasisDetailsWrapper(SalesBasisDetails salesBasisDetails) {
        _salesBasisDetails = salesBasisDetails;
    }

    @Override
    public Class<?> getModelClass() {
        return SalesBasisDetails.class;
    }

    @Override
    public String getModelClassName() {
        return SalesBasisDetails.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("createdBy", getCreatedBy());
        attributes.put("netSalesFormulaMasterSid", getNetSalesFormulaMasterSid());
        attributes.put("recordLockStatus", getRecordLockStatus());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("contractMasterSid", getContractMasterSid());
        attributes.put("source", getSource());
        attributes.put("cdrModelSid", getCdrModelSid());
        attributes.put("salesBasisDetailsSid", getSalesBasisDetailsSid());
        attributes.put("cfpContractDetailsSid", getCfpContractDetailsSid());
        attributes.put("modifiedDate", getModifiedDate());
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

        Boolean recordLockStatus = (Boolean) attributes.get("recordLockStatus");

        if (recordLockStatus != null) {
            setRecordLockStatus(recordLockStatus);
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

        String source = (String) attributes.get("source");

        if (source != null) {
            setSource(source);
        }

        Integer cdrModelSid = (Integer) attributes.get("cdrModelSid");

        if (cdrModelSid != null) {
            setCdrModelSid(cdrModelSid);
        }

        Integer salesBasisDetailsSid = (Integer) attributes.get(
                "salesBasisDetailsSid");

        if (salesBasisDetailsSid != null) {
            setSalesBasisDetailsSid(salesBasisDetailsSid);
        }

        Integer cfpContractDetailsSid = (Integer) attributes.get(
                "cfpContractDetailsSid");

        if (cfpContractDetailsSid != null) {
            setCfpContractDetailsSid(cfpContractDetailsSid);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        String inboundStatus = (String) attributes.get("inboundStatus");

        if (inboundStatus != null) {
            setInboundStatus(inboundStatus);
        }
    }

    /**
    * Returns the primary key of this sales basis details.
    *
    * @return the primary key of this sales basis details
    */
    @Override
    public int getPrimaryKey() {
        return _salesBasisDetails.getPrimaryKey();
    }

    /**
    * Sets the primary key of this sales basis details.
    *
    * @param primaryKey the primary key of this sales basis details
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _salesBasisDetails.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the created by of this sales basis details.
    *
    * @return the created by of this sales basis details
    */
    @Override
    public int getCreatedBy() {
        return _salesBasisDetails.getCreatedBy();
    }

    /**
    * Sets the created by of this sales basis details.
    *
    * @param createdBy the created by of this sales basis details
    */
    @Override
    public void setCreatedBy(int createdBy) {
        _salesBasisDetails.setCreatedBy(createdBy);
    }

    /**
    * Returns the net sales formula master sid of this sales basis details.
    *
    * @return the net sales formula master sid of this sales basis details
    */
    @Override
    public int getNetSalesFormulaMasterSid() {
        return _salesBasisDetails.getNetSalesFormulaMasterSid();
    }

    /**
    * Sets the net sales formula master sid of this sales basis details.
    *
    * @param netSalesFormulaMasterSid the net sales formula master sid of this sales basis details
    */
    @Override
    public void setNetSalesFormulaMasterSid(int netSalesFormulaMasterSid) {
        _salesBasisDetails.setNetSalesFormulaMasterSid(netSalesFormulaMasterSid);
    }

    /**
    * Returns the record lock status of this sales basis details.
    *
    * @return the record lock status of this sales basis details
    */
    @Override
    public boolean getRecordLockStatus() {
        return _salesBasisDetails.getRecordLockStatus();
    }

    /**
    * Returns <code>true</code> if this sales basis details is record lock status.
    *
    * @return <code>true</code> if this sales basis details is record lock status; <code>false</code> otherwise
    */
    @Override
    public boolean isRecordLockStatus() {
        return _salesBasisDetails.isRecordLockStatus();
    }

    /**
    * Sets whether this sales basis details is record lock status.
    *
    * @param recordLockStatus the record lock status of this sales basis details
    */
    @Override
    public void setRecordLockStatus(boolean recordLockStatus) {
        _salesBasisDetails.setRecordLockStatus(recordLockStatus);
    }

    /**
    * Returns the modified by of this sales basis details.
    *
    * @return the modified by of this sales basis details
    */
    @Override
    public int getModifiedBy() {
        return _salesBasisDetails.getModifiedBy();
    }

    /**
    * Sets the modified by of this sales basis details.
    *
    * @param modifiedBy the modified by of this sales basis details
    */
    @Override
    public void setModifiedBy(int modifiedBy) {
        _salesBasisDetails.setModifiedBy(modifiedBy);
    }

    /**
    * Returns the created date of this sales basis details.
    *
    * @return the created date of this sales basis details
    */
    @Override
    public java.util.Date getCreatedDate() {
        return _salesBasisDetails.getCreatedDate();
    }

    /**
    * Sets the created date of this sales basis details.
    *
    * @param createdDate the created date of this sales basis details
    */
    @Override
    public void setCreatedDate(java.util.Date createdDate) {
        _salesBasisDetails.setCreatedDate(createdDate);
    }

    /**
    * Returns the contract master sid of this sales basis details.
    *
    * @return the contract master sid of this sales basis details
    */
    @Override
    public int getContractMasterSid() {
        return _salesBasisDetails.getContractMasterSid();
    }

    /**
    * Sets the contract master sid of this sales basis details.
    *
    * @param contractMasterSid the contract master sid of this sales basis details
    */
    @Override
    public void setContractMasterSid(int contractMasterSid) {
        _salesBasisDetails.setContractMasterSid(contractMasterSid);
    }

    /**
    * Returns the source of this sales basis details.
    *
    * @return the source of this sales basis details
    */
    @Override
    public java.lang.String getSource() {
        return _salesBasisDetails.getSource();
    }

    /**
    * Sets the source of this sales basis details.
    *
    * @param source the source of this sales basis details
    */
    @Override
    public void setSource(java.lang.String source) {
        _salesBasisDetails.setSource(source);
    }

    /**
    * Returns the cdr model sid of this sales basis details.
    *
    * @return the cdr model sid of this sales basis details
    */
    @Override
    public int getCdrModelSid() {
        return _salesBasisDetails.getCdrModelSid();
    }

    /**
    * Sets the cdr model sid of this sales basis details.
    *
    * @param cdrModelSid the cdr model sid of this sales basis details
    */
    @Override
    public void setCdrModelSid(int cdrModelSid) {
        _salesBasisDetails.setCdrModelSid(cdrModelSid);
    }

    /**
    * Returns the sales basis details sid of this sales basis details.
    *
    * @return the sales basis details sid of this sales basis details
    */
    @Override
    public int getSalesBasisDetailsSid() {
        return _salesBasisDetails.getSalesBasisDetailsSid();
    }

    /**
    * Sets the sales basis details sid of this sales basis details.
    *
    * @param salesBasisDetailsSid the sales basis details sid of this sales basis details
    */
    @Override
    public void setSalesBasisDetailsSid(int salesBasisDetailsSid) {
        _salesBasisDetails.setSalesBasisDetailsSid(salesBasisDetailsSid);
    }

    /**
    * Returns the cfp contract details sid of this sales basis details.
    *
    * @return the cfp contract details sid of this sales basis details
    */
    @Override
    public int getCfpContractDetailsSid() {
        return _salesBasisDetails.getCfpContractDetailsSid();
    }

    /**
    * Sets the cfp contract details sid of this sales basis details.
    *
    * @param cfpContractDetailsSid the cfp contract details sid of this sales basis details
    */
    @Override
    public void setCfpContractDetailsSid(int cfpContractDetailsSid) {
        _salesBasisDetails.setCfpContractDetailsSid(cfpContractDetailsSid);
    }

    /**
    * Returns the modified date of this sales basis details.
    *
    * @return the modified date of this sales basis details
    */
    @Override
    public java.util.Date getModifiedDate() {
        return _salesBasisDetails.getModifiedDate();
    }

    /**
    * Sets the modified date of this sales basis details.
    *
    * @param modifiedDate the modified date of this sales basis details
    */
    @Override
    public void setModifiedDate(java.util.Date modifiedDate) {
        _salesBasisDetails.setModifiedDate(modifiedDate);
    }

    /**
    * Returns the inbound status of this sales basis details.
    *
    * @return the inbound status of this sales basis details
    */
    @Override
    public java.lang.String getInboundStatus() {
        return _salesBasisDetails.getInboundStatus();
    }

    /**
    * Sets the inbound status of this sales basis details.
    *
    * @param inboundStatus the inbound status of this sales basis details
    */
    @Override
    public void setInboundStatus(java.lang.String inboundStatus) {
        _salesBasisDetails.setInboundStatus(inboundStatus);
    }

    @Override
    public boolean isNew() {
        return _salesBasisDetails.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _salesBasisDetails.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _salesBasisDetails.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _salesBasisDetails.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _salesBasisDetails.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _salesBasisDetails.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _salesBasisDetails.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _salesBasisDetails.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _salesBasisDetails.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _salesBasisDetails.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _salesBasisDetails.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new SalesBasisDetailsWrapper((SalesBasisDetails) _salesBasisDetails.clone());
    }

    @Override
    public int compareTo(SalesBasisDetails salesBasisDetails) {
        return _salesBasisDetails.compareTo(salesBasisDetails);
    }

    @Override
    public int hashCode() {
        return _salesBasisDetails.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<SalesBasisDetails> toCacheModel() {
        return _salesBasisDetails.toCacheModel();
    }

    @Override
    public SalesBasisDetails toEscapedModel() {
        return new SalesBasisDetailsWrapper(_salesBasisDetails.toEscapedModel());
    }

    @Override
    public SalesBasisDetails toUnescapedModel() {
        return new SalesBasisDetailsWrapper(_salesBasisDetails.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _salesBasisDetails.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _salesBasisDetails.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _salesBasisDetails.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof SalesBasisDetailsWrapper)) {
            return false;
        }

        SalesBasisDetailsWrapper salesBasisDetailsWrapper = (SalesBasisDetailsWrapper) obj;

        if (Validator.equals(_salesBasisDetails,
                    salesBasisDetailsWrapper._salesBasisDetails)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public SalesBasisDetails getWrappedSalesBasisDetails() {
        return _salesBasisDetails;
    }

    @Override
    public SalesBasisDetails getWrappedModel() {
        return _salesBasisDetails;
    }

    @Override
    public void resetOriginalValues() {
        _salesBasisDetails.resetOriginalValues();
    }
}
