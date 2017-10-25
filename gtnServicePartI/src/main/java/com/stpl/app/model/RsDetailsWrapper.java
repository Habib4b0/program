package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link RsDetails}.
 * </p>
 *
 * @author
 * @see RsDetails
 * @generated
 */
public class RsDetailsWrapper implements RsDetails, ModelWrapper<RsDetails> {
    private RsDetails _rsDetails;

    public RsDetailsWrapper(RsDetails rsDetails) {
        _rsDetails = rsDetails;
    }

    @Override
    public Class<?> getModelClass() {
        return RsDetails.class;
    }

    @Override
    public String getModelClassName() {
        return RsDetails.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("rebateAmount", getRebateAmount());
        attributes.put("itemRsAttachedStatus", getItemRsAttachedStatus());
        attributes.put("formulaMethodId", getFormulaMethodId());
        attributes.put("itemMasterSid", getItemMasterSid());
        attributes.put("rebatePlanMasterSid", getRebatePlanMasterSid());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("bundleNo", getBundleNo());
        attributes.put("recordLockStatus", getRecordLockStatus());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("source", getSource());
        attributes.put("itemRebateEndDate", getItemRebateEndDate());
        attributes.put("batchId", getBatchId());
        attributes.put("itemRebateStartDate", getItemRebateStartDate());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("inboundStatus", getInboundStatus());
        attributes.put("rsDetailsSid", getRsDetailsSid());
        attributes.put("rsModelSid", getRsModelSid());
        attributes.put("formulaId", getFormulaId());
        attributes.put("itemRsAttachedDate", getItemRsAttachedDate());
        attributes.put("ifpModelSid", getIfpModelSid());
        attributes.put("deductionCalendarMasterSid",
            getDeductionCalendarMasterSid());
        attributes.put("netSalesFormulaMasterSid", getNetSalesFormulaMasterSid());
        attributes.put("evaluationRule", getEvaluationRule());
        attributes.put("netSalesRule", getNetSalesRule());
        attributes.put("formulaType", getFormulaType());
        attributes.put("calculationRule", getCalculationRule());
        attributes.put("calculationRuleBundle", getCalculationRuleBundle());
        attributes.put("evaluationRuleBundle", getEvaluationRuleBundle());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Double rebateAmount = (Double) attributes.get("rebateAmount");

        if (rebateAmount != null) {
            setRebateAmount(rebateAmount);
        }

        Integer itemRsAttachedStatus = (Integer) attributes.get(
                "itemRsAttachedStatus");

        if (itemRsAttachedStatus != null) {
            setItemRsAttachedStatus(itemRsAttachedStatus);
        }

        String formulaMethodId = (String) attributes.get("formulaMethodId");

        if (formulaMethodId != null) {
            setFormulaMethodId(formulaMethodId);
        }

        Integer itemMasterSid = (Integer) attributes.get("itemMasterSid");

        if (itemMasterSid != null) {
            setItemMasterSid(itemMasterSid);
        }

        Integer rebatePlanMasterSid = (Integer) attributes.get(
                "rebatePlanMasterSid");

        if (rebatePlanMasterSid != null) {
            setRebatePlanMasterSid(rebatePlanMasterSid);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
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

        String inboundStatus = (String) attributes.get("inboundStatus");

        if (inboundStatus != null) {
            setInboundStatus(inboundStatus);
        }

        Integer rsDetailsSid = (Integer) attributes.get("rsDetailsSid");

        if (rsDetailsSid != null) {
            setRsDetailsSid(rsDetailsSid);
        }

        Integer rsModelSid = (Integer) attributes.get("rsModelSid");

        if (rsModelSid != null) {
            setRsModelSid(rsModelSid);
        }

        Integer formulaId = (Integer) attributes.get("formulaId");

        if (formulaId != null) {
            setFormulaId(formulaId);
        }

        Date itemRsAttachedDate = (Date) attributes.get("itemRsAttachedDate");

        if (itemRsAttachedDate != null) {
            setItemRsAttachedDate(itemRsAttachedDate);
        }

        Integer ifpModelSid = (Integer) attributes.get("ifpModelSid");

        if (ifpModelSid != null) {
            setIfpModelSid(ifpModelSid);
        }

        Integer deductionCalendarMasterSid = (Integer) attributes.get(
                "deductionCalendarMasterSid");

        if (deductionCalendarMasterSid != null) {
            setDeductionCalendarMasterSid(deductionCalendarMasterSid);
        }

        Integer netSalesFormulaMasterSid = (Integer) attributes.get(
                "netSalesFormulaMasterSid");

        if (netSalesFormulaMasterSid != null) {
            setNetSalesFormulaMasterSid(netSalesFormulaMasterSid);
        }

        String evaluationRule = (String) attributes.get("evaluationRule");

        if (evaluationRule != null) {
            setEvaluationRule(evaluationRule);
        }

        String netSalesRule = (String) attributes.get("netSalesRule");

        if (netSalesRule != null) {
            setNetSalesRule(netSalesRule);
        }

        String formulaType = (String) attributes.get("formulaType");

        if (formulaType != null) {
            setFormulaType(formulaType);
        }

        String calculationRule = (String) attributes.get("calculationRule");

        if (calculationRule != null) {
            setCalculationRule(calculationRule);
        }

        String calculationRuleBundle = (String) attributes.get(
                "calculationRuleBundle");

        if (calculationRuleBundle != null) {
            setCalculationRuleBundle(calculationRuleBundle);
        }

        String evaluationRuleBundle = (String) attributes.get(
                "evaluationRuleBundle");

        if (evaluationRuleBundle != null) {
            setEvaluationRuleBundle(evaluationRuleBundle);
        }
    }

    /**
    * Returns the primary key of this rs details.
    *
    * @return the primary key of this rs details
    */
    @Override
    public int getPrimaryKey() {
        return _rsDetails.getPrimaryKey();
    }

    /**
    * Sets the primary key of this rs details.
    *
    * @param primaryKey the primary key of this rs details
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _rsDetails.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the rebate amount of this rs details.
    *
    * @return the rebate amount of this rs details
    */
    @Override
    public double getRebateAmount() {
        return _rsDetails.getRebateAmount();
    }

    /**
    * Sets the rebate amount of this rs details.
    *
    * @param rebateAmount the rebate amount of this rs details
    */
    @Override
    public void setRebateAmount(double rebateAmount) {
        _rsDetails.setRebateAmount(rebateAmount);
    }

    /**
    * Returns the item rs attached status of this rs details.
    *
    * @return the item rs attached status of this rs details
    */
    @Override
    public int getItemRsAttachedStatus() {
        return _rsDetails.getItemRsAttachedStatus();
    }

    /**
    * Sets the item rs attached status of this rs details.
    *
    * @param itemRsAttachedStatus the item rs attached status of this rs details
    */
    @Override
    public void setItemRsAttachedStatus(int itemRsAttachedStatus) {
        _rsDetails.setItemRsAttachedStatus(itemRsAttachedStatus);
    }

    /**
    * Returns the formula method ID of this rs details.
    *
    * @return the formula method ID of this rs details
    */
    @Override
    public java.lang.String getFormulaMethodId() {
        return _rsDetails.getFormulaMethodId();
    }

    /**
    * Sets the formula method ID of this rs details.
    *
    * @param formulaMethodId the formula method ID of this rs details
    */
    @Override
    public void setFormulaMethodId(java.lang.String formulaMethodId) {
        _rsDetails.setFormulaMethodId(formulaMethodId);
    }

    /**
    * Returns the item master sid of this rs details.
    *
    * @return the item master sid of this rs details
    */
    @Override
    public int getItemMasterSid() {
        return _rsDetails.getItemMasterSid();
    }

    /**
    * Sets the item master sid of this rs details.
    *
    * @param itemMasterSid the item master sid of this rs details
    */
    @Override
    public void setItemMasterSid(int itemMasterSid) {
        _rsDetails.setItemMasterSid(itemMasterSid);
    }

    /**
    * Returns the rebate plan master sid of this rs details.
    *
    * @return the rebate plan master sid of this rs details
    */
    @Override
    public int getRebatePlanMasterSid() {
        return _rsDetails.getRebatePlanMasterSid();
    }

    /**
    * Sets the rebate plan master sid of this rs details.
    *
    * @param rebatePlanMasterSid the rebate plan master sid of this rs details
    */
    @Override
    public void setRebatePlanMasterSid(int rebatePlanMasterSid) {
        _rsDetails.setRebatePlanMasterSid(rebatePlanMasterSid);
    }

    /**
    * Returns the modified date of this rs details.
    *
    * @return the modified date of this rs details
    */
    @Override
    public java.util.Date getModifiedDate() {
        return _rsDetails.getModifiedDate();
    }

    /**
    * Sets the modified date of this rs details.
    *
    * @param modifiedDate the modified date of this rs details
    */
    @Override
    public void setModifiedDate(java.util.Date modifiedDate) {
        _rsDetails.setModifiedDate(modifiedDate);
    }

    /**
    * Returns the bundle no of this rs details.
    *
    * @return the bundle no of this rs details
    */
    @Override
    public java.lang.String getBundleNo() {
        return _rsDetails.getBundleNo();
    }

    /**
    * Sets the bundle no of this rs details.
    *
    * @param bundleNo the bundle no of this rs details
    */
    @Override
    public void setBundleNo(java.lang.String bundleNo) {
        _rsDetails.setBundleNo(bundleNo);
    }

    /**
    * Returns the record lock status of this rs details.
    *
    * @return the record lock status of this rs details
    */
    @Override
    public boolean getRecordLockStatus() {
        return _rsDetails.getRecordLockStatus();
    }

    /**
    * Returns <code>true</code> if this rs details is record lock status.
    *
    * @return <code>true</code> if this rs details is record lock status; <code>false</code> otherwise
    */
    @Override
    public boolean isRecordLockStatus() {
        return _rsDetails.isRecordLockStatus();
    }

    /**
    * Sets whether this rs details is record lock status.
    *
    * @param recordLockStatus the record lock status of this rs details
    */
    @Override
    public void setRecordLockStatus(boolean recordLockStatus) {
        _rsDetails.setRecordLockStatus(recordLockStatus);
    }

    /**
    * Returns the created date of this rs details.
    *
    * @return the created date of this rs details
    */
    @Override
    public java.util.Date getCreatedDate() {
        return _rsDetails.getCreatedDate();
    }

    /**
    * Sets the created date of this rs details.
    *
    * @param createdDate the created date of this rs details
    */
    @Override
    public void setCreatedDate(java.util.Date createdDate) {
        _rsDetails.setCreatedDate(createdDate);
    }

    /**
    * Returns the created by of this rs details.
    *
    * @return the created by of this rs details
    */
    @Override
    public int getCreatedBy() {
        return _rsDetails.getCreatedBy();
    }

    /**
    * Sets the created by of this rs details.
    *
    * @param createdBy the created by of this rs details
    */
    @Override
    public void setCreatedBy(int createdBy) {
        _rsDetails.setCreatedBy(createdBy);
    }

    /**
    * Returns the source of this rs details.
    *
    * @return the source of this rs details
    */
    @Override
    public java.lang.String getSource() {
        return _rsDetails.getSource();
    }

    /**
    * Sets the source of this rs details.
    *
    * @param source the source of this rs details
    */
    @Override
    public void setSource(java.lang.String source) {
        _rsDetails.setSource(source);
    }

    /**
    * Returns the item rebate end date of this rs details.
    *
    * @return the item rebate end date of this rs details
    */
    @Override
    public java.util.Date getItemRebateEndDate() {
        return _rsDetails.getItemRebateEndDate();
    }

    /**
    * Sets the item rebate end date of this rs details.
    *
    * @param itemRebateEndDate the item rebate end date of this rs details
    */
    @Override
    public void setItemRebateEndDate(java.util.Date itemRebateEndDate) {
        _rsDetails.setItemRebateEndDate(itemRebateEndDate);
    }

    /**
    * Returns the batch ID of this rs details.
    *
    * @return the batch ID of this rs details
    */
    @Override
    public java.lang.String getBatchId() {
        return _rsDetails.getBatchId();
    }

    /**
    * Sets the batch ID of this rs details.
    *
    * @param batchId the batch ID of this rs details
    */
    @Override
    public void setBatchId(java.lang.String batchId) {
        _rsDetails.setBatchId(batchId);
    }

    /**
    * Returns the item rebate start date of this rs details.
    *
    * @return the item rebate start date of this rs details
    */
    @Override
    public java.util.Date getItemRebateStartDate() {
        return _rsDetails.getItemRebateStartDate();
    }

    /**
    * Sets the item rebate start date of this rs details.
    *
    * @param itemRebateStartDate the item rebate start date of this rs details
    */
    @Override
    public void setItemRebateStartDate(java.util.Date itemRebateStartDate) {
        _rsDetails.setItemRebateStartDate(itemRebateStartDate);
    }

    /**
    * Returns the modified by of this rs details.
    *
    * @return the modified by of this rs details
    */
    @Override
    public int getModifiedBy() {
        return _rsDetails.getModifiedBy();
    }

    /**
    * Sets the modified by of this rs details.
    *
    * @param modifiedBy the modified by of this rs details
    */
    @Override
    public void setModifiedBy(int modifiedBy) {
        _rsDetails.setModifiedBy(modifiedBy);
    }

    /**
    * Returns the inbound status of this rs details.
    *
    * @return the inbound status of this rs details
    */
    @Override
    public java.lang.String getInboundStatus() {
        return _rsDetails.getInboundStatus();
    }

    /**
    * Sets the inbound status of this rs details.
    *
    * @param inboundStatus the inbound status of this rs details
    */
    @Override
    public void setInboundStatus(java.lang.String inboundStatus) {
        _rsDetails.setInboundStatus(inboundStatus);
    }

    /**
    * Returns the rs details sid of this rs details.
    *
    * @return the rs details sid of this rs details
    */
    @Override
    public int getRsDetailsSid() {
        return _rsDetails.getRsDetailsSid();
    }

    /**
    * Sets the rs details sid of this rs details.
    *
    * @param rsDetailsSid the rs details sid of this rs details
    */
    @Override
    public void setRsDetailsSid(int rsDetailsSid) {
        _rsDetails.setRsDetailsSid(rsDetailsSid);
    }

    /**
    * Returns the rs model sid of this rs details.
    *
    * @return the rs model sid of this rs details
    */
    @Override
    public int getRsModelSid() {
        return _rsDetails.getRsModelSid();
    }

    /**
    * Sets the rs model sid of this rs details.
    *
    * @param rsModelSid the rs model sid of this rs details
    */
    @Override
    public void setRsModelSid(int rsModelSid) {
        _rsDetails.setRsModelSid(rsModelSid);
    }

    /**
    * Returns the formula ID of this rs details.
    *
    * @return the formula ID of this rs details
    */
    @Override
    public int getFormulaId() {
        return _rsDetails.getFormulaId();
    }

    /**
    * Sets the formula ID of this rs details.
    *
    * @param formulaId the formula ID of this rs details
    */
    @Override
    public void setFormulaId(int formulaId) {
        _rsDetails.setFormulaId(formulaId);
    }

    /**
    * Returns the item rs attached date of this rs details.
    *
    * @return the item rs attached date of this rs details
    */
    @Override
    public java.util.Date getItemRsAttachedDate() {
        return _rsDetails.getItemRsAttachedDate();
    }

    /**
    * Sets the item rs attached date of this rs details.
    *
    * @param itemRsAttachedDate the item rs attached date of this rs details
    */
    @Override
    public void setItemRsAttachedDate(java.util.Date itemRsAttachedDate) {
        _rsDetails.setItemRsAttachedDate(itemRsAttachedDate);
    }

    /**
    * Returns the ifp model sid of this rs details.
    *
    * @return the ifp model sid of this rs details
    */
    @Override
    public int getIfpModelSid() {
        return _rsDetails.getIfpModelSid();
    }

    /**
    * Sets the ifp model sid of this rs details.
    *
    * @param ifpModelSid the ifp model sid of this rs details
    */
    @Override
    public void setIfpModelSid(int ifpModelSid) {
        _rsDetails.setIfpModelSid(ifpModelSid);
    }

    /**
    * Returns the deduction calendar master sid of this rs details.
    *
    * @return the deduction calendar master sid of this rs details
    */
    @Override
    public int getDeductionCalendarMasterSid() {
        return _rsDetails.getDeductionCalendarMasterSid();
    }

    /**
    * Sets the deduction calendar master sid of this rs details.
    *
    * @param deductionCalendarMasterSid the deduction calendar master sid of this rs details
    */
    @Override
    public void setDeductionCalendarMasterSid(int deductionCalendarMasterSid) {
        _rsDetails.setDeductionCalendarMasterSid(deductionCalendarMasterSid);
    }

    /**
    * Returns the net sales formula master sid of this rs details.
    *
    * @return the net sales formula master sid of this rs details
    */
    @Override
    public int getNetSalesFormulaMasterSid() {
        return _rsDetails.getNetSalesFormulaMasterSid();
    }

    /**
    * Sets the net sales formula master sid of this rs details.
    *
    * @param netSalesFormulaMasterSid the net sales formula master sid of this rs details
    */
    @Override
    public void setNetSalesFormulaMasterSid(int netSalesFormulaMasterSid) {
        _rsDetails.setNetSalesFormulaMasterSid(netSalesFormulaMasterSid);
    }

    /**
    * Returns the evaluation rule of this rs details.
    *
    * @return the evaluation rule of this rs details
    */
    @Override
    public java.lang.String getEvaluationRule() {
        return _rsDetails.getEvaluationRule();
    }

    /**
    * Sets the evaluation rule of this rs details.
    *
    * @param evaluationRule the evaluation rule of this rs details
    */
    @Override
    public void setEvaluationRule(java.lang.String evaluationRule) {
        _rsDetails.setEvaluationRule(evaluationRule);
    }

    /**
    * Returns the net sales rule of this rs details.
    *
    * @return the net sales rule of this rs details
    */
    @Override
    public java.lang.String getNetSalesRule() {
        return _rsDetails.getNetSalesRule();
    }

    /**
    * Sets the net sales rule of this rs details.
    *
    * @param netSalesRule the net sales rule of this rs details
    */
    @Override
    public void setNetSalesRule(java.lang.String netSalesRule) {
        _rsDetails.setNetSalesRule(netSalesRule);
    }

    /**
    * Returns the formula type of this rs details.
    *
    * @return the formula type of this rs details
    */
    @Override
    public java.lang.String getFormulaType() {
        return _rsDetails.getFormulaType();
    }

    /**
    * Sets the formula type of this rs details.
    *
    * @param formulaType the formula type of this rs details
    */
    @Override
    public void setFormulaType(java.lang.String formulaType) {
        _rsDetails.setFormulaType(formulaType);
    }

    /**
    * Returns the calculation rule of this rs details.
    *
    * @return the calculation rule of this rs details
    */
    @Override
    public java.lang.String getCalculationRule() {
        return _rsDetails.getCalculationRule();
    }

    /**
    * Sets the calculation rule of this rs details.
    *
    * @param calculationRule the calculation rule of this rs details
    */
    @Override
    public void setCalculationRule(java.lang.String calculationRule) {
        _rsDetails.setCalculationRule(calculationRule);
    }

    /**
    * Returns the calculation rule bundle of this rs details.
    *
    * @return the calculation rule bundle of this rs details
    */
    @Override
    public java.lang.String getCalculationRuleBundle() {
        return _rsDetails.getCalculationRuleBundle();
    }

    /**
    * Sets the calculation rule bundle of this rs details.
    *
    * @param calculationRuleBundle the calculation rule bundle of this rs details
    */
    @Override
    public void setCalculationRuleBundle(java.lang.String calculationRuleBundle) {
        _rsDetails.setCalculationRuleBundle(calculationRuleBundle);
    }

    /**
    * Returns the evaluation rule bundle of this rs details.
    *
    * @return the evaluation rule bundle of this rs details
    */
    @Override
    public java.lang.String getEvaluationRuleBundle() {
        return _rsDetails.getEvaluationRuleBundle();
    }

    /**
    * Sets the evaluation rule bundle of this rs details.
    *
    * @param evaluationRuleBundle the evaluation rule bundle of this rs details
    */
    @Override
    public void setEvaluationRuleBundle(java.lang.String evaluationRuleBundle) {
        _rsDetails.setEvaluationRuleBundle(evaluationRuleBundle);
    }

    @Override
    public boolean isNew() {
        return _rsDetails.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _rsDetails.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _rsDetails.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _rsDetails.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _rsDetails.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _rsDetails.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _rsDetails.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _rsDetails.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _rsDetails.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _rsDetails.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _rsDetails.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new RsDetailsWrapper((RsDetails) _rsDetails.clone());
    }

    @Override
    public int compareTo(RsDetails rsDetails) {
        return _rsDetails.compareTo(rsDetails);
    }

    @Override
    public int hashCode() {
        return _rsDetails.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<RsDetails> toCacheModel() {
        return _rsDetails.toCacheModel();
    }

    @Override
    public RsDetails toEscapedModel() {
        return new RsDetailsWrapper(_rsDetails.toEscapedModel());
    }

    @Override
    public RsDetails toUnescapedModel() {
        return new RsDetailsWrapper(_rsDetails.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _rsDetails.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _rsDetails.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _rsDetails.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof RsDetailsWrapper)) {
            return false;
        }

        RsDetailsWrapper rsDetailsWrapper = (RsDetailsWrapper) obj;

        if (Validator.equals(_rsDetails, rsDetailsWrapper._rsDetails)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public RsDetails getWrappedRsDetails() {
        return _rsDetails;
    }

    @Override
    public RsDetails getWrappedModel() {
        return _rsDetails;
    }

    @Override
    public void resetOriginalValues() {
        _rsDetails.resetOriginalValues();
    }
}
