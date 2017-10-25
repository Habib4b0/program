package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link RsContractDetails}.
 * </p>
 *
 * @author
 * @see RsContractDetails
 * @generated
 */
public class RsContractDetailsWrapper implements RsContractDetails,
    ModelWrapper<RsContractDetails> {
    private RsContractDetails _rsContractDetails;

    public RsContractDetailsWrapper(RsContractDetails rsContractDetails) {
        _rsContractDetails = rsContractDetails;
    }

    @Override
    public Class<?> getModelClass() {
        return RsContractDetails.class;
    }

    @Override
    public String getModelClassName() {
        return RsContractDetails.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("rebateAmount", getRebateAmount());
        attributes.put("formulaMethodId", getFormulaMethodId());
        attributes.put("itemMasterSid", getItemMasterSid());
        attributes.put("rebatePlanMasterSid", getRebatePlanMasterSid());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("rsContractDetailsSid", getRsContractDetailsSid());
        attributes.put("bundleNo", getBundleNo());
        attributes.put("recordLockStatus", getRecordLockStatus());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("source", getSource());
        attributes.put("rsContractSid", getRsContractSid());
        attributes.put("itemRebateEndDate", getItemRebateEndDate());
        attributes.put("batchId", getBatchId());
        attributes.put("itemRebateStartDate", getItemRebateStartDate());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("formulaId", getFormulaId());
        attributes.put("inboundStatus", getInboundStatus());
        attributes.put("deductionCalendarMasterSid",
            getDeductionCalendarMasterSid());
        attributes.put("netSalesFormulaMasterSid", getNetSalesFormulaMasterSid());
        attributes.put("formulaType", getFormulaType());
        attributes.put("netSalesRule", getNetSalesRule());
        attributes.put("evaluationRule", getEvaluationRule());
        attributes.put("evaluationRuleBundle", getEvaluationRuleBundle());
        attributes.put("calculationRule", getCalculationRule());
        attributes.put("calculationRuleBundle", getCalculationRuleBundle());
        attributes.put("rsContractAttachedDate", getRsContractAttachedDate());
        attributes.put("rsContractAttachedStatus", getRsContractAttachedStatus());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Double rebateAmount = (Double) attributes.get("rebateAmount");

        if (rebateAmount != null) {
            setRebateAmount(rebateAmount);
        }

        String formulaMethodId = (String) attributes.get("formulaMethodId");

        if (formulaMethodId != null) {
            setFormulaMethodId(formulaMethodId);
        }

        Integer itemMasterSid = (Integer) attributes.get("itemMasterSid");

        if (itemMasterSid != null) {
            setItemMasterSid(itemMasterSid);
        }

        String rebatePlanMasterSid = (String) attributes.get(
                "rebatePlanMasterSid");

        if (rebatePlanMasterSid != null) {
            setRebatePlanMasterSid(rebatePlanMasterSid);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        Integer rsContractDetailsSid = (Integer) attributes.get(
                "rsContractDetailsSid");

        if (rsContractDetailsSid != null) {
            setRsContractDetailsSid(rsContractDetailsSid);
        }

        String bundleNo = (String) attributes.get("bundleNo");

        if (bundleNo != null) {
            setBundleNo(bundleNo);
        }

        Boolean recordLockStatus = (Boolean) attributes.get("recordLockStatus");

        if (recordLockStatus != null) {
            setRecordLockStatus(recordLockStatus);
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

        Integer rsContractSid = (Integer) attributes.get("rsContractSid");

        if (rsContractSid != null) {
            setRsContractSid(rsContractSid);
        }

        Date itemRebateEndDate = (Date) attributes.get("itemRebateEndDate");

        if (itemRebateEndDate != null) {
            setItemRebateEndDate(itemRebateEndDate);
        }

        String batchId = (String) attributes.get("batchId");

        if (batchId != null) {
            setBatchId(batchId);
        }

        Date itemRebateStartDate = (Date) attributes.get("itemRebateStartDate");

        if (itemRebateStartDate != null) {
            setItemRebateStartDate(itemRebateStartDate);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        Integer formulaId = (Integer) attributes.get("formulaId");

        if (formulaId != null) {
            setFormulaId(formulaId);
        }

        String inboundStatus = (String) attributes.get("inboundStatus");

        if (inboundStatus != null) {
            setInboundStatus(inboundStatus);
        }

        String deductionCalendarMasterSid = (String) attributes.get(
                "deductionCalendarMasterSid");

        if (deductionCalendarMasterSid != null) {
            setDeductionCalendarMasterSid(deductionCalendarMasterSid);
        }

        String netSalesFormulaMasterSid = (String) attributes.get(
                "netSalesFormulaMasterSid");

        if (netSalesFormulaMasterSid != null) {
            setNetSalesFormulaMasterSid(netSalesFormulaMasterSid);
        }

        Double formulaType = (Double) attributes.get("formulaType");

        if (formulaType != null) {
            setFormulaType(formulaType);
        }

        Integer netSalesRule = (Integer) attributes.get("netSalesRule");

        if (netSalesRule != null) {
            setNetSalesRule(netSalesRule);
        }

        Integer evaluationRule = (Integer) attributes.get("evaluationRule");

        if (evaluationRule != null) {
            setEvaluationRule(evaluationRule);
        }

        String evaluationRuleBundle = (String) attributes.get(
                "evaluationRuleBundle");

        if (evaluationRuleBundle != null) {
            setEvaluationRuleBundle(evaluationRuleBundle);
        }

        Integer calculationRule = (Integer) attributes.get("calculationRule");

        if (calculationRule != null) {
            setCalculationRule(calculationRule);
        }

        String calculationRuleBundle = (String) attributes.get(
                "calculationRuleBundle");

        if (calculationRuleBundle != null) {
            setCalculationRuleBundle(calculationRuleBundle);
        }

        Date rsContractAttachedDate = (Date) attributes.get(
                "rsContractAttachedDate");

        if (rsContractAttachedDate != null) {
            setRsContractAttachedDate(rsContractAttachedDate);
        }

        Integer rsContractAttachedStatus = (Integer) attributes.get(
                "rsContractAttachedStatus");

        if (rsContractAttachedStatus != null) {
            setRsContractAttachedStatus(rsContractAttachedStatus);
        }
    }

    /**
    * Returns the primary key of this rs contract details.
    *
    * @return the primary key of this rs contract details
    */
    @Override
    public int getPrimaryKey() {
        return _rsContractDetails.getPrimaryKey();
    }

    /**
    * Sets the primary key of this rs contract details.
    *
    * @param primaryKey the primary key of this rs contract details
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _rsContractDetails.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the rebate amount of this rs contract details.
    *
    * @return the rebate amount of this rs contract details
    */
    @Override
    public double getRebateAmount() {
        return _rsContractDetails.getRebateAmount();
    }

    /**
    * Sets the rebate amount of this rs contract details.
    *
    * @param rebateAmount the rebate amount of this rs contract details
    */
    @Override
    public void setRebateAmount(double rebateAmount) {
        _rsContractDetails.setRebateAmount(rebateAmount);
    }

    /**
    * Returns the formula method ID of this rs contract details.
    *
    * @return the formula method ID of this rs contract details
    */
    @Override
    public java.lang.String getFormulaMethodId() {
        return _rsContractDetails.getFormulaMethodId();
    }

    /**
    * Sets the formula method ID of this rs contract details.
    *
    * @param formulaMethodId the formula method ID of this rs contract details
    */
    @Override
    public void setFormulaMethodId(java.lang.String formulaMethodId) {
        _rsContractDetails.setFormulaMethodId(formulaMethodId);
    }

    /**
    * Returns the item master sid of this rs contract details.
    *
    * @return the item master sid of this rs contract details
    */
    @Override
    public int getItemMasterSid() {
        return _rsContractDetails.getItemMasterSid();
    }

    /**
    * Sets the item master sid of this rs contract details.
    *
    * @param itemMasterSid the item master sid of this rs contract details
    */
    @Override
    public void setItemMasterSid(int itemMasterSid) {
        _rsContractDetails.setItemMasterSid(itemMasterSid);
    }

    /**
    * Returns the rebate plan master sid of this rs contract details.
    *
    * @return the rebate plan master sid of this rs contract details
    */
    @Override
    public java.lang.String getRebatePlanMasterSid() {
        return _rsContractDetails.getRebatePlanMasterSid();
    }

    /**
    * Sets the rebate plan master sid of this rs contract details.
    *
    * @param rebatePlanMasterSid the rebate plan master sid of this rs contract details
    */
    @Override
    public void setRebatePlanMasterSid(java.lang.String rebatePlanMasterSid) {
        _rsContractDetails.setRebatePlanMasterSid(rebatePlanMasterSid);
    }

    /**
    * Returns the modified date of this rs contract details.
    *
    * @return the modified date of this rs contract details
    */
    @Override
    public java.util.Date getModifiedDate() {
        return _rsContractDetails.getModifiedDate();
    }

    /**
    * Sets the modified date of this rs contract details.
    *
    * @param modifiedDate the modified date of this rs contract details
    */
    @Override
    public void setModifiedDate(java.util.Date modifiedDate) {
        _rsContractDetails.setModifiedDate(modifiedDate);
    }

    /**
    * Returns the rs contract details sid of this rs contract details.
    *
    * @return the rs contract details sid of this rs contract details
    */
    @Override
    public int getRsContractDetailsSid() {
        return _rsContractDetails.getRsContractDetailsSid();
    }

    /**
    * Sets the rs contract details sid of this rs contract details.
    *
    * @param rsContractDetailsSid the rs contract details sid of this rs contract details
    */
    @Override
    public void setRsContractDetailsSid(int rsContractDetailsSid) {
        _rsContractDetails.setRsContractDetailsSid(rsContractDetailsSid);
    }

    /**
    * Returns the bundle no of this rs contract details.
    *
    * @return the bundle no of this rs contract details
    */
    @Override
    public java.lang.String getBundleNo() {
        return _rsContractDetails.getBundleNo();
    }

    /**
    * Sets the bundle no of this rs contract details.
    *
    * @param bundleNo the bundle no of this rs contract details
    */
    @Override
    public void setBundleNo(java.lang.String bundleNo) {
        _rsContractDetails.setBundleNo(bundleNo);
    }

    /**
    * Returns the record lock status of this rs contract details.
    *
    * @return the record lock status of this rs contract details
    */
    @Override
    public boolean getRecordLockStatus() {
        return _rsContractDetails.getRecordLockStatus();
    }

    /**
    * Returns <code>true</code> if this rs contract details is record lock status.
    *
    * @return <code>true</code> if this rs contract details is record lock status; <code>false</code> otherwise
    */
    @Override
    public boolean isRecordLockStatus() {
        return _rsContractDetails.isRecordLockStatus();
    }

    /**
    * Sets whether this rs contract details is record lock status.
    *
    * @param recordLockStatus the record lock status of this rs contract details
    */
    @Override
    public void setRecordLockStatus(boolean recordLockStatus) {
        _rsContractDetails.setRecordLockStatus(recordLockStatus);
    }

    /**
    * Returns the created date of this rs contract details.
    *
    * @return the created date of this rs contract details
    */
    @Override
    public java.util.Date getCreatedDate() {
        return _rsContractDetails.getCreatedDate();
    }

    /**
    * Sets the created date of this rs contract details.
    *
    * @param createdDate the created date of this rs contract details
    */
    @Override
    public void setCreatedDate(java.util.Date createdDate) {
        _rsContractDetails.setCreatedDate(createdDate);
    }

    /**
    * Returns the created by of this rs contract details.
    *
    * @return the created by of this rs contract details
    */
    @Override
    public int getCreatedBy() {
        return _rsContractDetails.getCreatedBy();
    }

    /**
    * Sets the created by of this rs contract details.
    *
    * @param createdBy the created by of this rs contract details
    */
    @Override
    public void setCreatedBy(int createdBy) {
        _rsContractDetails.setCreatedBy(createdBy);
    }

    /**
    * Returns the source of this rs contract details.
    *
    * @return the source of this rs contract details
    */
    @Override
    public java.lang.String getSource() {
        return _rsContractDetails.getSource();
    }

    /**
    * Sets the source of this rs contract details.
    *
    * @param source the source of this rs contract details
    */
    @Override
    public void setSource(java.lang.String source) {
        _rsContractDetails.setSource(source);
    }

    /**
    * Returns the rs contract sid of this rs contract details.
    *
    * @return the rs contract sid of this rs contract details
    */
    @Override
    public int getRsContractSid() {
        return _rsContractDetails.getRsContractSid();
    }

    /**
    * Sets the rs contract sid of this rs contract details.
    *
    * @param rsContractSid the rs contract sid of this rs contract details
    */
    @Override
    public void setRsContractSid(int rsContractSid) {
        _rsContractDetails.setRsContractSid(rsContractSid);
    }

    /**
    * Returns the item rebate end date of this rs contract details.
    *
    * @return the item rebate end date of this rs contract details
    */
    @Override
    public java.util.Date getItemRebateEndDate() {
        return _rsContractDetails.getItemRebateEndDate();
    }

    /**
    * Sets the item rebate end date of this rs contract details.
    *
    * @param itemRebateEndDate the item rebate end date of this rs contract details
    */
    @Override
    public void setItemRebateEndDate(java.util.Date itemRebateEndDate) {
        _rsContractDetails.setItemRebateEndDate(itemRebateEndDate);
    }

    /**
    * Returns the batch ID of this rs contract details.
    *
    * @return the batch ID of this rs contract details
    */
    @Override
    public java.lang.String getBatchId() {
        return _rsContractDetails.getBatchId();
    }

    /**
    * Sets the batch ID of this rs contract details.
    *
    * @param batchId the batch ID of this rs contract details
    */
    @Override
    public void setBatchId(java.lang.String batchId) {
        _rsContractDetails.setBatchId(batchId);
    }

    /**
    * Returns the item rebate start date of this rs contract details.
    *
    * @return the item rebate start date of this rs contract details
    */
    @Override
    public java.util.Date getItemRebateStartDate() {
        return _rsContractDetails.getItemRebateStartDate();
    }

    /**
    * Sets the item rebate start date of this rs contract details.
    *
    * @param itemRebateStartDate the item rebate start date of this rs contract details
    */
    @Override
    public void setItemRebateStartDate(java.util.Date itemRebateStartDate) {
        _rsContractDetails.setItemRebateStartDate(itemRebateStartDate);
    }

    /**
    * Returns the modified by of this rs contract details.
    *
    * @return the modified by of this rs contract details
    */
    @Override
    public int getModifiedBy() {
        return _rsContractDetails.getModifiedBy();
    }

    /**
    * Sets the modified by of this rs contract details.
    *
    * @param modifiedBy the modified by of this rs contract details
    */
    @Override
    public void setModifiedBy(int modifiedBy) {
        _rsContractDetails.setModifiedBy(modifiedBy);
    }

    /**
    * Returns the formula ID of this rs contract details.
    *
    * @return the formula ID of this rs contract details
    */
    @Override
    public int getFormulaId() {
        return _rsContractDetails.getFormulaId();
    }

    /**
    * Sets the formula ID of this rs contract details.
    *
    * @param formulaId the formula ID of this rs contract details
    */
    @Override
    public void setFormulaId(int formulaId) {
        _rsContractDetails.setFormulaId(formulaId);
    }

    /**
    * Returns the inbound status of this rs contract details.
    *
    * @return the inbound status of this rs contract details
    */
    @Override
    public java.lang.String getInboundStatus() {
        return _rsContractDetails.getInboundStatus();
    }

    /**
    * Sets the inbound status of this rs contract details.
    *
    * @param inboundStatus the inbound status of this rs contract details
    */
    @Override
    public void setInboundStatus(java.lang.String inboundStatus) {
        _rsContractDetails.setInboundStatus(inboundStatus);
    }

    /**
    * Returns the deduction calendar master sid of this rs contract details.
    *
    * @return the deduction calendar master sid of this rs contract details
    */
    @Override
    public java.lang.String getDeductionCalendarMasterSid() {
        return _rsContractDetails.getDeductionCalendarMasterSid();
    }

    /**
    * Sets the deduction calendar master sid of this rs contract details.
    *
    * @param deductionCalendarMasterSid the deduction calendar master sid of this rs contract details
    */
    @Override
    public void setDeductionCalendarMasterSid(
        java.lang.String deductionCalendarMasterSid) {
        _rsContractDetails.setDeductionCalendarMasterSid(deductionCalendarMasterSid);
    }

    /**
    * Returns the net sales formula master sid of this rs contract details.
    *
    * @return the net sales formula master sid of this rs contract details
    */
    @Override
    public java.lang.String getNetSalesFormulaMasterSid() {
        return _rsContractDetails.getNetSalesFormulaMasterSid();
    }

    /**
    * Sets the net sales formula master sid of this rs contract details.
    *
    * @param netSalesFormulaMasterSid the net sales formula master sid of this rs contract details
    */
    @Override
    public void setNetSalesFormulaMasterSid(
        java.lang.String netSalesFormulaMasterSid) {
        _rsContractDetails.setNetSalesFormulaMasterSid(netSalesFormulaMasterSid);
    }

    /**
    * Returns the formula type of this rs contract details.
    *
    * @return the formula type of this rs contract details
    */
    @Override
    public double getFormulaType() {
        return _rsContractDetails.getFormulaType();
    }

    /**
    * Sets the formula type of this rs contract details.
    *
    * @param formulaType the formula type of this rs contract details
    */
    @Override
    public void setFormulaType(double formulaType) {
        _rsContractDetails.setFormulaType(formulaType);
    }

    /**
    * Returns the net sales rule of this rs contract details.
    *
    * @return the net sales rule of this rs contract details
    */
    @Override
    public int getNetSalesRule() {
        return _rsContractDetails.getNetSalesRule();
    }

    /**
    * Sets the net sales rule of this rs contract details.
    *
    * @param netSalesRule the net sales rule of this rs contract details
    */
    @Override
    public void setNetSalesRule(int netSalesRule) {
        _rsContractDetails.setNetSalesRule(netSalesRule);
    }

    /**
    * Returns the evaluation rule of this rs contract details.
    *
    * @return the evaluation rule of this rs contract details
    */
    @Override
    public int getEvaluationRule() {
        return _rsContractDetails.getEvaluationRule();
    }

    /**
    * Sets the evaluation rule of this rs contract details.
    *
    * @param evaluationRule the evaluation rule of this rs contract details
    */
    @Override
    public void setEvaluationRule(int evaluationRule) {
        _rsContractDetails.setEvaluationRule(evaluationRule);
    }

    /**
    * Returns the evaluation rule bundle of this rs contract details.
    *
    * @return the evaluation rule bundle of this rs contract details
    */
    @Override
    public java.lang.String getEvaluationRuleBundle() {
        return _rsContractDetails.getEvaluationRuleBundle();
    }

    /**
    * Sets the evaluation rule bundle of this rs contract details.
    *
    * @param evaluationRuleBundle the evaluation rule bundle of this rs contract details
    */
    @Override
    public void setEvaluationRuleBundle(java.lang.String evaluationRuleBundle) {
        _rsContractDetails.setEvaluationRuleBundle(evaluationRuleBundle);
    }

    /**
    * Returns the calculation rule of this rs contract details.
    *
    * @return the calculation rule of this rs contract details
    */
    @Override
    public int getCalculationRule() {
        return _rsContractDetails.getCalculationRule();
    }

    /**
    * Sets the calculation rule of this rs contract details.
    *
    * @param calculationRule the calculation rule of this rs contract details
    */
    @Override
    public void setCalculationRule(int calculationRule) {
        _rsContractDetails.setCalculationRule(calculationRule);
    }

    /**
    * Returns the calculation rule bundle of this rs contract details.
    *
    * @return the calculation rule bundle of this rs contract details
    */
    @Override
    public java.lang.String getCalculationRuleBundle() {
        return _rsContractDetails.getCalculationRuleBundle();
    }

    /**
    * Sets the calculation rule bundle of this rs contract details.
    *
    * @param calculationRuleBundle the calculation rule bundle of this rs contract details
    */
    @Override
    public void setCalculationRuleBundle(java.lang.String calculationRuleBundle) {
        _rsContractDetails.setCalculationRuleBundle(calculationRuleBundle);
    }

    /**
    * Returns the rs contract attached date of this rs contract details.
    *
    * @return the rs contract attached date of this rs contract details
    */
    @Override
    public java.util.Date getRsContractAttachedDate() {
        return _rsContractDetails.getRsContractAttachedDate();
    }

    /**
    * Sets the rs contract attached date of this rs contract details.
    *
    * @param rsContractAttachedDate the rs contract attached date of this rs contract details
    */
    @Override
    public void setRsContractAttachedDate(java.util.Date rsContractAttachedDate) {
        _rsContractDetails.setRsContractAttachedDate(rsContractAttachedDate);
    }

    /**
    * Returns the rs contract attached status of this rs contract details.
    *
    * @return the rs contract attached status of this rs contract details
    */
    @Override
    public int getRsContractAttachedStatus() {
        return _rsContractDetails.getRsContractAttachedStatus();
    }

    /**
    * Sets the rs contract attached status of this rs contract details.
    *
    * @param rsContractAttachedStatus the rs contract attached status of this rs contract details
    */
    @Override
    public void setRsContractAttachedStatus(int rsContractAttachedStatus) {
        _rsContractDetails.setRsContractAttachedStatus(rsContractAttachedStatus);
    }

    @Override
    public boolean isNew() {
        return _rsContractDetails.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _rsContractDetails.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _rsContractDetails.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _rsContractDetails.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _rsContractDetails.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _rsContractDetails.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _rsContractDetails.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _rsContractDetails.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _rsContractDetails.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _rsContractDetails.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _rsContractDetails.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new RsContractDetailsWrapper((RsContractDetails) _rsContractDetails.clone());
    }

    @Override
    public int compareTo(RsContractDetails rsContractDetails) {
        return _rsContractDetails.compareTo(rsContractDetails);
    }

    @Override
    public int hashCode() {
        return _rsContractDetails.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<RsContractDetails> toCacheModel() {
        return _rsContractDetails.toCacheModel();
    }

    @Override
    public RsContractDetails toEscapedModel() {
        return new RsContractDetailsWrapper(_rsContractDetails.toEscapedModel());
    }

    @Override
    public RsContractDetails toUnescapedModel() {
        return new RsContractDetailsWrapper(_rsContractDetails.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _rsContractDetails.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _rsContractDetails.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _rsContractDetails.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof RsContractDetailsWrapper)) {
            return false;
        }

        RsContractDetailsWrapper rsContractDetailsWrapper = (RsContractDetailsWrapper) obj;

        if (Validator.equals(_rsContractDetails,
                    rsContractDetailsWrapper._rsContractDetails)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public RsContractDetails getWrappedRsContractDetails() {
        return _rsContractDetails;
    }

    @Override
    public RsContractDetails getWrappedModel() {
        return _rsContractDetails;
    }

    @Override
    public void resetOriginalValues() {
        _rsContractDetails.resetOriginalValues();
    }
}
