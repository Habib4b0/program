package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link FormulaDetailsMaster}.
 * </p>
 *
 * @author
 * @see FormulaDetailsMaster
 * @generated
 */
public class FormulaDetailsMasterWrapper implements FormulaDetailsMaster,
    ModelWrapper<FormulaDetailsMaster> {
    private FormulaDetailsMaster _formulaDetailsMaster;

    public FormulaDetailsMasterWrapper(
        FormulaDetailsMaster formulaDetailsMaster) {
        _formulaDetailsMaster = formulaDetailsMaster;
    }

    @Override
    public Class<?> getModelClass() {
        return FormulaDetailsMaster.class;
    }

    @Override
    public String getModelClassName() {
        return FormulaDetailsMaster.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("companyId", getCompanyId());
        attributes.put("contractPrice1", getContractPrice1());
        attributes.put("contractPrice2", getContractPrice2());
        attributes.put("endDate", getEndDate());
        attributes.put("formulaNo", getFormulaNo());
        attributes.put("formulaDetailsMasterSid", getFormulaDetailsMasterSid());
        attributes.put("itemId", getItemId());
        attributes.put("rebatePercent1", getRebatePercent1());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("formulaDesc", getFormulaDesc());
        attributes.put("rebatePercent2", getRebatePercent2());
        attributes.put("rebatePercent3", getRebatePercent3());
        attributes.put("recordLockStatus", getRecordLockStatus());
        attributes.put("startDate", getStartDate());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("source", getSource());
        attributes.put("batchId", getBatchId());
        attributes.put("contractPrice3", getContractPrice3());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("inboundStatus", getInboundStatus());
        attributes.put("formulaId", getFormulaId());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String companyId = (String) attributes.get("companyId");

        if (companyId != null) {
            setCompanyId(companyId);
        }

        Double contractPrice1 = (Double) attributes.get("contractPrice1");

        if (contractPrice1 != null) {
            setContractPrice1(contractPrice1);
        }

        Double contractPrice2 = (Double) attributes.get("contractPrice2");

        if (contractPrice2 != null) {
            setContractPrice2(contractPrice2);
        }

        Date endDate = (Date) attributes.get("endDate");

        if (endDate != null) {
            setEndDate(endDate);
        }

        String formulaNo = (String) attributes.get("formulaNo");

        if (formulaNo != null) {
            setFormulaNo(formulaNo);
        }

        Integer formulaDetailsMasterSid = (Integer) attributes.get(
                "formulaDetailsMasterSid");

        if (formulaDetailsMasterSid != null) {
            setFormulaDetailsMasterSid(formulaDetailsMasterSid);
        }

        String itemId = (String) attributes.get("itemId");

        if (itemId != null) {
            setItemId(itemId);
        }

        Double rebatePercent1 = (Double) attributes.get("rebatePercent1");

        if (rebatePercent1 != null) {
            setRebatePercent1(rebatePercent1);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        String formulaDesc = (String) attributes.get("formulaDesc");

        if (formulaDesc != null) {
            setFormulaDesc(formulaDesc);
        }

        Double rebatePercent2 = (Double) attributes.get("rebatePercent2");

        if (rebatePercent2 != null) {
            setRebatePercent2(rebatePercent2);
        }

        Double rebatePercent3 = (Double) attributes.get("rebatePercent3");

        if (rebatePercent3 != null) {
            setRebatePercent3(rebatePercent3);
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

        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        String source = (String) attributes.get("source");

        if (source != null) {
            setSource(source);
        }

        String batchId = (String) attributes.get("batchId");

        if (batchId != null) {
            setBatchId(batchId);
        }

        Double contractPrice3 = (Double) attributes.get("contractPrice3");

        if (contractPrice3 != null) {
            setContractPrice3(contractPrice3);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        String inboundStatus = (String) attributes.get("inboundStatus");

        if (inboundStatus != null) {
            setInboundStatus(inboundStatus);
        }

        String formulaId = (String) attributes.get("formulaId");

        if (formulaId != null) {
            setFormulaId(formulaId);
        }
    }

    /**
    * Returns the primary key of this formula details master.
    *
    * @return the primary key of this formula details master
    */
    @Override
    public int getPrimaryKey() {
        return _formulaDetailsMaster.getPrimaryKey();
    }

    /**
    * Sets the primary key of this formula details master.
    *
    * @param primaryKey the primary key of this formula details master
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _formulaDetailsMaster.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the company ID of this formula details master.
    *
    * @return the company ID of this formula details master
    */
    @Override
    public java.lang.String getCompanyId() {
        return _formulaDetailsMaster.getCompanyId();
    }

    /**
    * Sets the company ID of this formula details master.
    *
    * @param companyId the company ID of this formula details master
    */
    @Override
    public void setCompanyId(java.lang.String companyId) {
        _formulaDetailsMaster.setCompanyId(companyId);
    }

    /**
    * Returns the contract price1 of this formula details master.
    *
    * @return the contract price1 of this formula details master
    */
    @Override
    public double getContractPrice1() {
        return _formulaDetailsMaster.getContractPrice1();
    }

    /**
    * Sets the contract price1 of this formula details master.
    *
    * @param contractPrice1 the contract price1 of this formula details master
    */
    @Override
    public void setContractPrice1(double contractPrice1) {
        _formulaDetailsMaster.setContractPrice1(contractPrice1);
    }

    /**
    * Returns the contract price2 of this formula details master.
    *
    * @return the contract price2 of this formula details master
    */
    @Override
    public double getContractPrice2() {
        return _formulaDetailsMaster.getContractPrice2();
    }

    /**
    * Sets the contract price2 of this formula details master.
    *
    * @param contractPrice2 the contract price2 of this formula details master
    */
    @Override
    public void setContractPrice2(double contractPrice2) {
        _formulaDetailsMaster.setContractPrice2(contractPrice2);
    }

    /**
    * Returns the end date of this formula details master.
    *
    * @return the end date of this formula details master
    */
    @Override
    public java.util.Date getEndDate() {
        return _formulaDetailsMaster.getEndDate();
    }

    /**
    * Sets the end date of this formula details master.
    *
    * @param endDate the end date of this formula details master
    */
    @Override
    public void setEndDate(java.util.Date endDate) {
        _formulaDetailsMaster.setEndDate(endDate);
    }

    /**
    * Returns the formula no of this formula details master.
    *
    * @return the formula no of this formula details master
    */
    @Override
    public java.lang.String getFormulaNo() {
        return _formulaDetailsMaster.getFormulaNo();
    }

    /**
    * Sets the formula no of this formula details master.
    *
    * @param formulaNo the formula no of this formula details master
    */
    @Override
    public void setFormulaNo(java.lang.String formulaNo) {
        _formulaDetailsMaster.setFormulaNo(formulaNo);
    }

    /**
    * Returns the formula details master sid of this formula details master.
    *
    * @return the formula details master sid of this formula details master
    */
    @Override
    public int getFormulaDetailsMasterSid() {
        return _formulaDetailsMaster.getFormulaDetailsMasterSid();
    }

    /**
    * Sets the formula details master sid of this formula details master.
    *
    * @param formulaDetailsMasterSid the formula details master sid of this formula details master
    */
    @Override
    public void setFormulaDetailsMasterSid(int formulaDetailsMasterSid) {
        _formulaDetailsMaster.setFormulaDetailsMasterSid(formulaDetailsMasterSid);
    }

    /**
    * Returns the item ID of this formula details master.
    *
    * @return the item ID of this formula details master
    */
    @Override
    public java.lang.String getItemId() {
        return _formulaDetailsMaster.getItemId();
    }

    /**
    * Sets the item ID of this formula details master.
    *
    * @param itemId the item ID of this formula details master
    */
    @Override
    public void setItemId(java.lang.String itemId) {
        _formulaDetailsMaster.setItemId(itemId);
    }

    /**
    * Returns the rebate percent1 of this formula details master.
    *
    * @return the rebate percent1 of this formula details master
    */
    @Override
    public double getRebatePercent1() {
        return _formulaDetailsMaster.getRebatePercent1();
    }

    /**
    * Sets the rebate percent1 of this formula details master.
    *
    * @param rebatePercent1 the rebate percent1 of this formula details master
    */
    @Override
    public void setRebatePercent1(double rebatePercent1) {
        _formulaDetailsMaster.setRebatePercent1(rebatePercent1);
    }

    /**
    * Returns the modified date of this formula details master.
    *
    * @return the modified date of this formula details master
    */
    @Override
    public java.util.Date getModifiedDate() {
        return _formulaDetailsMaster.getModifiedDate();
    }

    /**
    * Sets the modified date of this formula details master.
    *
    * @param modifiedDate the modified date of this formula details master
    */
    @Override
    public void setModifiedDate(java.util.Date modifiedDate) {
        _formulaDetailsMaster.setModifiedDate(modifiedDate);
    }

    /**
    * Returns the formula desc of this formula details master.
    *
    * @return the formula desc of this formula details master
    */
    @Override
    public java.lang.String getFormulaDesc() {
        return _formulaDetailsMaster.getFormulaDesc();
    }

    /**
    * Sets the formula desc of this formula details master.
    *
    * @param formulaDesc the formula desc of this formula details master
    */
    @Override
    public void setFormulaDesc(java.lang.String formulaDesc) {
        _formulaDetailsMaster.setFormulaDesc(formulaDesc);
    }

    /**
    * Returns the rebate percent2 of this formula details master.
    *
    * @return the rebate percent2 of this formula details master
    */
    @Override
    public double getRebatePercent2() {
        return _formulaDetailsMaster.getRebatePercent2();
    }

    /**
    * Sets the rebate percent2 of this formula details master.
    *
    * @param rebatePercent2 the rebate percent2 of this formula details master
    */
    @Override
    public void setRebatePercent2(double rebatePercent2) {
        _formulaDetailsMaster.setRebatePercent2(rebatePercent2);
    }

    /**
    * Returns the rebate percent3 of this formula details master.
    *
    * @return the rebate percent3 of this formula details master
    */
    @Override
    public double getRebatePercent3() {
        return _formulaDetailsMaster.getRebatePercent3();
    }

    /**
    * Sets the rebate percent3 of this formula details master.
    *
    * @param rebatePercent3 the rebate percent3 of this formula details master
    */
    @Override
    public void setRebatePercent3(double rebatePercent3) {
        _formulaDetailsMaster.setRebatePercent3(rebatePercent3);
    }

    /**
    * Returns the record lock status of this formula details master.
    *
    * @return the record lock status of this formula details master
    */
    @Override
    public boolean getRecordLockStatus() {
        return _formulaDetailsMaster.getRecordLockStatus();
    }

    /**
    * Returns <code>true</code> if this formula details master is record lock status.
    *
    * @return <code>true</code> if this formula details master is record lock status; <code>false</code> otherwise
    */
    @Override
    public boolean isRecordLockStatus() {
        return _formulaDetailsMaster.isRecordLockStatus();
    }

    /**
    * Sets whether this formula details master is record lock status.
    *
    * @param recordLockStatus the record lock status of this formula details master
    */
    @Override
    public void setRecordLockStatus(boolean recordLockStatus) {
        _formulaDetailsMaster.setRecordLockStatus(recordLockStatus);
    }

    /**
    * Returns the start date of this formula details master.
    *
    * @return the start date of this formula details master
    */
    @Override
    public java.util.Date getStartDate() {
        return _formulaDetailsMaster.getStartDate();
    }

    /**
    * Sets the start date of this formula details master.
    *
    * @param startDate the start date of this formula details master
    */
    @Override
    public void setStartDate(java.util.Date startDate) {
        _formulaDetailsMaster.setStartDate(startDate);
    }

    /**
    * Returns the created date of this formula details master.
    *
    * @return the created date of this formula details master
    */
    @Override
    public java.util.Date getCreatedDate() {
        return _formulaDetailsMaster.getCreatedDate();
    }

    /**
    * Sets the created date of this formula details master.
    *
    * @param createdDate the created date of this formula details master
    */
    @Override
    public void setCreatedDate(java.util.Date createdDate) {
        _formulaDetailsMaster.setCreatedDate(createdDate);
    }

    /**
    * Returns the created by of this formula details master.
    *
    * @return the created by of this formula details master
    */
    @Override
    public int getCreatedBy() {
        return _formulaDetailsMaster.getCreatedBy();
    }

    /**
    * Sets the created by of this formula details master.
    *
    * @param createdBy the created by of this formula details master
    */
    @Override
    public void setCreatedBy(int createdBy) {
        _formulaDetailsMaster.setCreatedBy(createdBy);
    }

    /**
    * Returns the source of this formula details master.
    *
    * @return the source of this formula details master
    */
    @Override
    public java.lang.String getSource() {
        return _formulaDetailsMaster.getSource();
    }

    /**
    * Sets the source of this formula details master.
    *
    * @param source the source of this formula details master
    */
    @Override
    public void setSource(java.lang.String source) {
        _formulaDetailsMaster.setSource(source);
    }

    /**
    * Returns the batch ID of this formula details master.
    *
    * @return the batch ID of this formula details master
    */
    @Override
    public java.lang.String getBatchId() {
        return _formulaDetailsMaster.getBatchId();
    }

    /**
    * Sets the batch ID of this formula details master.
    *
    * @param batchId the batch ID of this formula details master
    */
    @Override
    public void setBatchId(java.lang.String batchId) {
        _formulaDetailsMaster.setBatchId(batchId);
    }

    /**
    * Returns the contract price3 of this formula details master.
    *
    * @return the contract price3 of this formula details master
    */
    @Override
    public double getContractPrice3() {
        return _formulaDetailsMaster.getContractPrice3();
    }

    /**
    * Sets the contract price3 of this formula details master.
    *
    * @param contractPrice3 the contract price3 of this formula details master
    */
    @Override
    public void setContractPrice3(double contractPrice3) {
        _formulaDetailsMaster.setContractPrice3(contractPrice3);
    }

    /**
    * Returns the modified by of this formula details master.
    *
    * @return the modified by of this formula details master
    */
    @Override
    public int getModifiedBy() {
        return _formulaDetailsMaster.getModifiedBy();
    }

    /**
    * Sets the modified by of this formula details master.
    *
    * @param modifiedBy the modified by of this formula details master
    */
    @Override
    public void setModifiedBy(int modifiedBy) {
        _formulaDetailsMaster.setModifiedBy(modifiedBy);
    }

    /**
    * Returns the inbound status of this formula details master.
    *
    * @return the inbound status of this formula details master
    */
    @Override
    public java.lang.String getInboundStatus() {
        return _formulaDetailsMaster.getInboundStatus();
    }

    /**
    * Sets the inbound status of this formula details master.
    *
    * @param inboundStatus the inbound status of this formula details master
    */
    @Override
    public void setInboundStatus(java.lang.String inboundStatus) {
        _formulaDetailsMaster.setInboundStatus(inboundStatus);
    }

    /**
    * Returns the formula ID of this formula details master.
    *
    * @return the formula ID of this formula details master
    */
    @Override
    public java.lang.String getFormulaId() {
        return _formulaDetailsMaster.getFormulaId();
    }

    /**
    * Sets the formula ID of this formula details master.
    *
    * @param formulaId the formula ID of this formula details master
    */
    @Override
    public void setFormulaId(java.lang.String formulaId) {
        _formulaDetailsMaster.setFormulaId(formulaId);
    }

    @Override
    public boolean isNew() {
        return _formulaDetailsMaster.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _formulaDetailsMaster.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _formulaDetailsMaster.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _formulaDetailsMaster.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _formulaDetailsMaster.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _formulaDetailsMaster.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _formulaDetailsMaster.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _formulaDetailsMaster.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _formulaDetailsMaster.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _formulaDetailsMaster.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _formulaDetailsMaster.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new FormulaDetailsMasterWrapper((FormulaDetailsMaster) _formulaDetailsMaster.clone());
    }

    @Override
    public int compareTo(FormulaDetailsMaster formulaDetailsMaster) {
        return _formulaDetailsMaster.compareTo(formulaDetailsMaster);
    }

    @Override
    public int hashCode() {
        return _formulaDetailsMaster.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<FormulaDetailsMaster> toCacheModel() {
        return _formulaDetailsMaster.toCacheModel();
    }

    @Override
    public FormulaDetailsMaster toEscapedModel() {
        return new FormulaDetailsMasterWrapper(_formulaDetailsMaster.toEscapedModel());
    }

    @Override
    public FormulaDetailsMaster toUnescapedModel() {
        return new FormulaDetailsMasterWrapper(_formulaDetailsMaster.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _formulaDetailsMaster.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _formulaDetailsMaster.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _formulaDetailsMaster.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof FormulaDetailsMasterWrapper)) {
            return false;
        }

        FormulaDetailsMasterWrapper formulaDetailsMasterWrapper = (FormulaDetailsMasterWrapper) obj;

        if (Validator.equals(_formulaDetailsMaster,
                    formulaDetailsMasterWrapper._formulaDetailsMaster)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public FormulaDetailsMaster getWrappedFormulaDetailsMaster() {
        return _formulaDetailsMaster;
    }

    @Override
    public FormulaDetailsMaster getWrappedModel() {
        return _formulaDetailsMaster;
    }

    @Override
    public void resetOriginalValues() {
        _formulaDetailsMaster.resetOriginalValues();
    }
}
