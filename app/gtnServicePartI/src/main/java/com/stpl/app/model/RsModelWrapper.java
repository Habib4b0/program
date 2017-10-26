package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link RsModel}.
 * </p>
 *
 * @author
 * @see RsModel
 * @generated
 */
public class RsModelWrapper implements RsModel, ModelWrapper<RsModel> {
    private RsModel _rsModel;

    public RsModelWrapper(RsModel rsModel) {
        _rsModel = rsModel;
    }

    @Override
    public Class<?> getModelClass() {
        return RsModel.class;
    }

    @Override
    public String getModelClassName() {
        return RsModel.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("formulaMethodId", getFormulaMethodId());
        attributes.put("calculationType", getCalculationType());
        attributes.put("rsStatus", getRsStatus());
        attributes.put("rsEndDate", getRsEndDate());
        attributes.put("rsTransRefNo", getRsTransRefNo());
        attributes.put("paymentFrequency", getPaymentFrequency());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("calculationLevel", getCalculationLevel());
        attributes.put("rsName", getRsName());
        attributes.put("source", getSource());
        attributes.put("rebatePlanLevel", getRebatePlanLevel());
        attributes.put("rebateRuleType", getRebateRuleType());
        attributes.put("inboundStatus", getInboundStatus());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("rsAlias", getRsAlias());
        attributes.put("rsId", getRsId());
        attributes.put("paymentMethod", getPaymentMethod());
        attributes.put("zipCode", getZipCode());
        attributes.put("rebateRuleAssociation", getRebateRuleAssociation());
        attributes.put("parentRsName", getParentRsName());
        attributes.put("internalNotes", getInternalNotes());
        attributes.put("paymentLevel", getPaymentLevel());
        attributes.put("recordLockStatus", getRecordLockStatus());
        attributes.put("rsCalendar", getRsCalendar());
        attributes.put("rebateProgramType", getRebateProgramType());
        attributes.put("interestBearingBasis", getInterestBearingBasis());
        attributes.put("rsModelSid", getRsModelSid());
        attributes.put("city", getCity());
        attributes.put("rebateProcessingType", getRebateProcessingType());
        attributes.put("rsNo", getRsNo());
        attributes.put("state", getState());
        attributes.put("rebateFrequency", getRebateFrequency());
        attributes.put("parentRsId", getParentRsId());
        attributes.put("rsType", getRsType());
        attributes.put("rsStartDate", getRsStartDate());
        attributes.put("makePayableTo", getMakePayableTo());
        attributes.put("rsDesignation", getRsDesignation());
        attributes.put("rsTransRefName", getRsTransRefName());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("rsTransRefId", getRsTransRefId());
        attributes.put("rsCategory", getRsCategory());
        attributes.put("rsTradeClass", getRsTradeClass());
        attributes.put("interestBearingIndicator", getInterestBearingIndicator());
        attributes.put("manfCompanyMasterSid", getManfCompanyMasterSid());
        attributes.put("paymentTerms", getPaymentTerms());
        attributes.put("address1", getAddress1());
        attributes.put("address2", getAddress2());
        attributes.put("rsValidationProfile", getRsValidationProfile());
        attributes.put("paymentGracePeriod", getPaymentGracePeriod());
        attributes.put("batchId", getBatchId());
        attributes.put("evaluationRuleType", getEvaluationRuleType());
        attributes.put("evaluationRuleLevel", getEvaluationRuleLevel());
        attributes.put("evaluationRuleOrAssociation",
            getEvaluationRuleOrAssociation());
        attributes.put("calculationRuleLevel", getCalculationRuleLevel());
        attributes.put("calculationRule", getCalculationRule());
        attributes.put("deductionInclusion", getDeductionInclusion());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String formulaMethodId = (String) attributes.get("formulaMethodId");

        if (formulaMethodId != null) {
            setFormulaMethodId(formulaMethodId);
        }

        Integer calculationType = (Integer) attributes.get("calculationType");

        if (calculationType != null) {
            setCalculationType(calculationType);
        }

        Integer rsStatus = (Integer) attributes.get("rsStatus");

        if (rsStatus != null) {
            setRsStatus(rsStatus);
        }

        Date rsEndDate = (Date) attributes.get("rsEndDate");

        if (rsEndDate != null) {
            setRsEndDate(rsEndDate);
        }

        String rsTransRefNo = (String) attributes.get("rsTransRefNo");

        if (rsTransRefNo != null) {
            setRsTransRefNo(rsTransRefNo);
        }

        Integer paymentFrequency = (Integer) attributes.get("paymentFrequency");

        if (paymentFrequency != null) {
            setPaymentFrequency(paymentFrequency);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        Integer calculationLevel = (Integer) attributes.get("calculationLevel");

        if (calculationLevel != null) {
            setCalculationLevel(calculationLevel);
        }

        String rsName = (String) attributes.get("rsName");

        if (rsName != null) {
            setRsName(rsName);
        }

        String source = (String) attributes.get("source");

        if (source != null) {
            setSource(source);
        }

        String rebatePlanLevel = (String) attributes.get("rebatePlanLevel");

        if (rebatePlanLevel != null) {
            setRebatePlanLevel(rebatePlanLevel);
        }

        Integer rebateRuleType = (Integer) attributes.get("rebateRuleType");

        if (rebateRuleType != null) {
            setRebateRuleType(rebateRuleType);
        }

        String inboundStatus = (String) attributes.get("inboundStatus");

        if (inboundStatus != null) {
            setInboundStatus(inboundStatus);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        String rsAlias = (String) attributes.get("rsAlias");

        if (rsAlias != null) {
            setRsAlias(rsAlias);
        }

        String rsId = (String) attributes.get("rsId");

        if (rsId != null) {
            setRsId(rsId);
        }

        Integer paymentMethod = (Integer) attributes.get("paymentMethod");

        if (paymentMethod != null) {
            setPaymentMethod(paymentMethod);
        }

        String zipCode = (String) attributes.get("zipCode");

        if (zipCode != null) {
            setZipCode(zipCode);
        }

        String rebateRuleAssociation = (String) attributes.get(
                "rebateRuleAssociation");

        if (rebateRuleAssociation != null) {
            setRebateRuleAssociation(rebateRuleAssociation);
        }

        String parentRsName = (String) attributes.get("parentRsName");

        if (parentRsName != null) {
            setParentRsName(parentRsName);
        }

        String internalNotes = (String) attributes.get("internalNotes");

        if (internalNotes != null) {
            setInternalNotes(internalNotes);
        }

        Integer paymentLevel = (Integer) attributes.get("paymentLevel");

        if (paymentLevel != null) {
            setPaymentLevel(paymentLevel);
        }

        Boolean recordLockStatus = (Boolean) attributes.get("recordLockStatus");

        if (recordLockStatus != null) {
            setRecordLockStatus(recordLockStatus);
        }

        Integer rsCalendar = (Integer) attributes.get("rsCalendar");

        if (rsCalendar != null) {
            setRsCalendar(rsCalendar);
        }

        Integer rebateProgramType = (Integer) attributes.get(
                "rebateProgramType");

        if (rebateProgramType != null) {
            setRebateProgramType(rebateProgramType);
        }

        Integer interestBearingBasis = (Integer) attributes.get(
                "interestBearingBasis");

        if (interestBearingBasis != null) {
            setInterestBearingBasis(interestBearingBasis);
        }

        Integer rsModelSid = (Integer) attributes.get("rsModelSid");

        if (rsModelSid != null) {
            setRsModelSid(rsModelSid);
        }

        String city = (String) attributes.get("city");

        if (city != null) {
            setCity(city);
        }

        Integer rebateProcessingType = (Integer) attributes.get(
                "rebateProcessingType");

        if (rebateProcessingType != null) {
            setRebateProcessingType(rebateProcessingType);
        }

        String rsNo = (String) attributes.get("rsNo");

        if (rsNo != null) {
            setRsNo(rsNo);
        }

        Integer state = (Integer) attributes.get("state");

        if (state != null) {
            setState(state);
        }

        Integer rebateFrequency = (Integer) attributes.get("rebateFrequency");

        if (rebateFrequency != null) {
            setRebateFrequency(rebateFrequency);
        }

        String parentRsId = (String) attributes.get("parentRsId");

        if (parentRsId != null) {
            setParentRsId(parentRsId);
        }

        Integer rsType = (Integer) attributes.get("rsType");

        if (rsType != null) {
            setRsType(rsType);
        }

        Date rsStartDate = (Date) attributes.get("rsStartDate");

        if (rsStartDate != null) {
            setRsStartDate(rsStartDate);
        }

        String makePayableTo = (String) attributes.get("makePayableTo");

        if (makePayableTo != null) {
            setMakePayableTo(makePayableTo);
        }

        Integer rsDesignation = (Integer) attributes.get("rsDesignation");

        if (rsDesignation != null) {
            setRsDesignation(rsDesignation);
        }

        String rsTransRefName = (String) attributes.get("rsTransRefName");

        if (rsTransRefName != null) {
            setRsTransRefName(rsTransRefName);
        }

        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        String rsTransRefId = (String) attributes.get("rsTransRefId");

        if (rsTransRefId != null) {
            setRsTransRefId(rsTransRefId);
        }

        Integer rsCategory = (Integer) attributes.get("rsCategory");

        if (rsCategory != null) {
            setRsCategory(rsCategory);
        }

        Integer rsTradeClass = (Integer) attributes.get("rsTradeClass");

        if (rsTradeClass != null) {
            setRsTradeClass(rsTradeClass);
        }

        Integer interestBearingIndicator = (Integer) attributes.get(
                "interestBearingIndicator");

        if (interestBearingIndicator != null) {
            setInterestBearingIndicator(interestBearingIndicator);
        }

        Integer manfCompanyMasterSid = (Integer) attributes.get(
                "manfCompanyMasterSid");

        if (manfCompanyMasterSid != null) {
            setManfCompanyMasterSid(manfCompanyMasterSid);
        }

        Integer paymentTerms = (Integer) attributes.get("paymentTerms");

        if (paymentTerms != null) {
            setPaymentTerms(paymentTerms);
        }

        String address1 = (String) attributes.get("address1");

        if (address1 != null) {
            setAddress1(address1);
        }

        String address2 = (String) attributes.get("address2");

        if (address2 != null) {
            setAddress2(address2);
        }

        Integer rsValidationProfile = (Integer) attributes.get(
                "rsValidationProfile");

        if (rsValidationProfile != null) {
            setRsValidationProfile(rsValidationProfile);
        }

        String paymentGracePeriod = (String) attributes.get(
                "paymentGracePeriod");

        if (paymentGracePeriod != null) {
            setPaymentGracePeriod(paymentGracePeriod);
        }

        String batchId = (String) attributes.get("batchId");

        if (batchId != null) {
            setBatchId(batchId);
        }

        String evaluationRuleType = (String) attributes.get(
                "evaluationRuleType");

        if (evaluationRuleType != null) {
            setEvaluationRuleType(evaluationRuleType);
        }

        String evaluationRuleLevel = (String) attributes.get(
                "evaluationRuleLevel");

        if (evaluationRuleLevel != null) {
            setEvaluationRuleLevel(evaluationRuleLevel);
        }

        String evaluationRuleOrAssociation = (String) attributes.get(
                "evaluationRuleOrAssociation");

        if (evaluationRuleOrAssociation != null) {
            setEvaluationRuleOrAssociation(evaluationRuleOrAssociation);
        }

        String calculationRuleLevel = (String) attributes.get(
                "calculationRuleLevel");

        if (calculationRuleLevel != null) {
            setCalculationRuleLevel(calculationRuleLevel);
        }

        String calculationRule = (String) attributes.get("calculationRule");

        if (calculationRule != null) {
            setCalculationRule(calculationRule);
        }

        String deductionInclusion = (String) attributes.get(
                "deductionInclusion");

        if (deductionInclusion != null) {
            setDeductionInclusion(deductionInclusion);
        }
    }

    /**
    * Returns the primary key of this rs model.
    *
    * @return the primary key of this rs model
    */
    @Override
    public int getPrimaryKey() {
        return _rsModel.getPrimaryKey();
    }

    /**
    * Sets the primary key of this rs model.
    *
    * @param primaryKey the primary key of this rs model
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _rsModel.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the formula method ID of this rs model.
    *
    * @return the formula method ID of this rs model
    */
    @Override
    public java.lang.String getFormulaMethodId() {
        return _rsModel.getFormulaMethodId();
    }

    /**
    * Sets the formula method ID of this rs model.
    *
    * @param formulaMethodId the formula method ID of this rs model
    */
    @Override
    public void setFormulaMethodId(java.lang.String formulaMethodId) {
        _rsModel.setFormulaMethodId(formulaMethodId);
    }

    /**
    * Returns the calculation type of this rs model.
    *
    * @return the calculation type of this rs model
    */
    @Override
    public int getCalculationType() {
        return _rsModel.getCalculationType();
    }

    /**
    * Sets the calculation type of this rs model.
    *
    * @param calculationType the calculation type of this rs model
    */
    @Override
    public void setCalculationType(int calculationType) {
        _rsModel.setCalculationType(calculationType);
    }

    /**
    * Returns the rs status of this rs model.
    *
    * @return the rs status of this rs model
    */
    @Override
    public int getRsStatus() {
        return _rsModel.getRsStatus();
    }

    /**
    * Sets the rs status of this rs model.
    *
    * @param rsStatus the rs status of this rs model
    */
    @Override
    public void setRsStatus(int rsStatus) {
        _rsModel.setRsStatus(rsStatus);
    }

    /**
    * Returns the rs end date of this rs model.
    *
    * @return the rs end date of this rs model
    */
    @Override
    public java.util.Date getRsEndDate() {
        return _rsModel.getRsEndDate();
    }

    /**
    * Sets the rs end date of this rs model.
    *
    * @param rsEndDate the rs end date of this rs model
    */
    @Override
    public void setRsEndDate(java.util.Date rsEndDate) {
        _rsModel.setRsEndDate(rsEndDate);
    }

    /**
    * Returns the rs trans ref no of this rs model.
    *
    * @return the rs trans ref no of this rs model
    */
    @Override
    public java.lang.String getRsTransRefNo() {
        return _rsModel.getRsTransRefNo();
    }

    /**
    * Sets the rs trans ref no of this rs model.
    *
    * @param rsTransRefNo the rs trans ref no of this rs model
    */
    @Override
    public void setRsTransRefNo(java.lang.String rsTransRefNo) {
        _rsModel.setRsTransRefNo(rsTransRefNo);
    }

    /**
    * Returns the payment frequency of this rs model.
    *
    * @return the payment frequency of this rs model
    */
    @Override
    public int getPaymentFrequency() {
        return _rsModel.getPaymentFrequency();
    }

    /**
    * Sets the payment frequency of this rs model.
    *
    * @param paymentFrequency the payment frequency of this rs model
    */
    @Override
    public void setPaymentFrequency(int paymentFrequency) {
        _rsModel.setPaymentFrequency(paymentFrequency);
    }

    /**
    * Returns the modified date of this rs model.
    *
    * @return the modified date of this rs model
    */
    @Override
    public java.util.Date getModifiedDate() {
        return _rsModel.getModifiedDate();
    }

    /**
    * Sets the modified date of this rs model.
    *
    * @param modifiedDate the modified date of this rs model
    */
    @Override
    public void setModifiedDate(java.util.Date modifiedDate) {
        _rsModel.setModifiedDate(modifiedDate);
    }

    /**
    * Returns the calculation level of this rs model.
    *
    * @return the calculation level of this rs model
    */
    @Override
    public int getCalculationLevel() {
        return _rsModel.getCalculationLevel();
    }

    /**
    * Sets the calculation level of this rs model.
    *
    * @param calculationLevel the calculation level of this rs model
    */
    @Override
    public void setCalculationLevel(int calculationLevel) {
        _rsModel.setCalculationLevel(calculationLevel);
    }

    /**
    * Returns the rs name of this rs model.
    *
    * @return the rs name of this rs model
    */
    @Override
    public java.lang.String getRsName() {
        return _rsModel.getRsName();
    }

    /**
    * Sets the rs name of this rs model.
    *
    * @param rsName the rs name of this rs model
    */
    @Override
    public void setRsName(java.lang.String rsName) {
        _rsModel.setRsName(rsName);
    }

    /**
    * Returns the source of this rs model.
    *
    * @return the source of this rs model
    */
    @Override
    public java.lang.String getSource() {
        return _rsModel.getSource();
    }

    /**
    * Sets the source of this rs model.
    *
    * @param source the source of this rs model
    */
    @Override
    public void setSource(java.lang.String source) {
        _rsModel.setSource(source);
    }

    /**
    * Returns the rebate plan level of this rs model.
    *
    * @return the rebate plan level of this rs model
    */
    @Override
    public java.lang.String getRebatePlanLevel() {
        return _rsModel.getRebatePlanLevel();
    }

    /**
    * Sets the rebate plan level of this rs model.
    *
    * @param rebatePlanLevel the rebate plan level of this rs model
    */
    @Override
    public void setRebatePlanLevel(java.lang.String rebatePlanLevel) {
        _rsModel.setRebatePlanLevel(rebatePlanLevel);
    }

    /**
    * Returns the rebate rule type of this rs model.
    *
    * @return the rebate rule type of this rs model
    */
    @Override
    public int getRebateRuleType() {
        return _rsModel.getRebateRuleType();
    }

    /**
    * Sets the rebate rule type of this rs model.
    *
    * @param rebateRuleType the rebate rule type of this rs model
    */
    @Override
    public void setRebateRuleType(int rebateRuleType) {
        _rsModel.setRebateRuleType(rebateRuleType);
    }

    /**
    * Returns the inbound status of this rs model.
    *
    * @return the inbound status of this rs model
    */
    @Override
    public java.lang.String getInboundStatus() {
        return _rsModel.getInboundStatus();
    }

    /**
    * Sets the inbound status of this rs model.
    *
    * @param inboundStatus the inbound status of this rs model
    */
    @Override
    public void setInboundStatus(java.lang.String inboundStatus) {
        _rsModel.setInboundStatus(inboundStatus);
    }

    /**
    * Returns the modified by of this rs model.
    *
    * @return the modified by of this rs model
    */
    @Override
    public int getModifiedBy() {
        return _rsModel.getModifiedBy();
    }

    /**
    * Sets the modified by of this rs model.
    *
    * @param modifiedBy the modified by of this rs model
    */
    @Override
    public void setModifiedBy(int modifiedBy) {
        _rsModel.setModifiedBy(modifiedBy);
    }

    /**
    * Returns the rs alias of this rs model.
    *
    * @return the rs alias of this rs model
    */
    @Override
    public java.lang.String getRsAlias() {
        return _rsModel.getRsAlias();
    }

    /**
    * Sets the rs alias of this rs model.
    *
    * @param rsAlias the rs alias of this rs model
    */
    @Override
    public void setRsAlias(java.lang.String rsAlias) {
        _rsModel.setRsAlias(rsAlias);
    }

    /**
    * Returns the rs ID of this rs model.
    *
    * @return the rs ID of this rs model
    */
    @Override
    public java.lang.String getRsId() {
        return _rsModel.getRsId();
    }

    /**
    * Sets the rs ID of this rs model.
    *
    * @param rsId the rs ID of this rs model
    */
    @Override
    public void setRsId(java.lang.String rsId) {
        _rsModel.setRsId(rsId);
    }

    /**
    * Returns the payment method of this rs model.
    *
    * @return the payment method of this rs model
    */
    @Override
    public int getPaymentMethod() {
        return _rsModel.getPaymentMethod();
    }

    /**
    * Sets the payment method of this rs model.
    *
    * @param paymentMethod the payment method of this rs model
    */
    @Override
    public void setPaymentMethod(int paymentMethod) {
        _rsModel.setPaymentMethod(paymentMethod);
    }

    /**
    * Returns the zip code of this rs model.
    *
    * @return the zip code of this rs model
    */
    @Override
    public java.lang.String getZipCode() {
        return _rsModel.getZipCode();
    }

    /**
    * Sets the zip code of this rs model.
    *
    * @param zipCode the zip code of this rs model
    */
    @Override
    public void setZipCode(java.lang.String zipCode) {
        _rsModel.setZipCode(zipCode);
    }

    /**
    * Returns the rebate rule association of this rs model.
    *
    * @return the rebate rule association of this rs model
    */
    @Override
    public java.lang.String getRebateRuleAssociation() {
        return _rsModel.getRebateRuleAssociation();
    }

    /**
    * Sets the rebate rule association of this rs model.
    *
    * @param rebateRuleAssociation the rebate rule association of this rs model
    */
    @Override
    public void setRebateRuleAssociation(java.lang.String rebateRuleAssociation) {
        _rsModel.setRebateRuleAssociation(rebateRuleAssociation);
    }

    /**
    * Returns the parent rs name of this rs model.
    *
    * @return the parent rs name of this rs model
    */
    @Override
    public java.lang.String getParentRsName() {
        return _rsModel.getParentRsName();
    }

    /**
    * Sets the parent rs name of this rs model.
    *
    * @param parentRsName the parent rs name of this rs model
    */
    @Override
    public void setParentRsName(java.lang.String parentRsName) {
        _rsModel.setParentRsName(parentRsName);
    }

    /**
    * Returns the internal notes of this rs model.
    *
    * @return the internal notes of this rs model
    */
    @Override
    public java.lang.String getInternalNotes() {
        return _rsModel.getInternalNotes();
    }

    /**
    * Sets the internal notes of this rs model.
    *
    * @param internalNotes the internal notes of this rs model
    */
    @Override
    public void setInternalNotes(java.lang.String internalNotes) {
        _rsModel.setInternalNotes(internalNotes);
    }

    /**
    * Returns the payment level of this rs model.
    *
    * @return the payment level of this rs model
    */
    @Override
    public int getPaymentLevel() {
        return _rsModel.getPaymentLevel();
    }

    /**
    * Sets the payment level of this rs model.
    *
    * @param paymentLevel the payment level of this rs model
    */
    @Override
    public void setPaymentLevel(int paymentLevel) {
        _rsModel.setPaymentLevel(paymentLevel);
    }

    /**
    * Returns the record lock status of this rs model.
    *
    * @return the record lock status of this rs model
    */
    @Override
    public boolean getRecordLockStatus() {
        return _rsModel.getRecordLockStatus();
    }

    /**
    * Returns <code>true</code> if this rs model is record lock status.
    *
    * @return <code>true</code> if this rs model is record lock status; <code>false</code> otherwise
    */
    @Override
    public boolean isRecordLockStatus() {
        return _rsModel.isRecordLockStatus();
    }

    /**
    * Sets whether this rs model is record lock status.
    *
    * @param recordLockStatus the record lock status of this rs model
    */
    @Override
    public void setRecordLockStatus(boolean recordLockStatus) {
        _rsModel.setRecordLockStatus(recordLockStatus);
    }

    /**
    * Returns the rs calendar of this rs model.
    *
    * @return the rs calendar of this rs model
    */
    @Override
    public int getRsCalendar() {
        return _rsModel.getRsCalendar();
    }

    /**
    * Sets the rs calendar of this rs model.
    *
    * @param rsCalendar the rs calendar of this rs model
    */
    @Override
    public void setRsCalendar(int rsCalendar) {
        _rsModel.setRsCalendar(rsCalendar);
    }

    /**
    * Returns the rebate program type of this rs model.
    *
    * @return the rebate program type of this rs model
    */
    @Override
    public int getRebateProgramType() {
        return _rsModel.getRebateProgramType();
    }

    /**
    * Sets the rebate program type of this rs model.
    *
    * @param rebateProgramType the rebate program type of this rs model
    */
    @Override
    public void setRebateProgramType(int rebateProgramType) {
        _rsModel.setRebateProgramType(rebateProgramType);
    }

    /**
    * Returns the interest bearing basis of this rs model.
    *
    * @return the interest bearing basis of this rs model
    */
    @Override
    public int getInterestBearingBasis() {
        return _rsModel.getInterestBearingBasis();
    }

    /**
    * Sets the interest bearing basis of this rs model.
    *
    * @param interestBearingBasis the interest bearing basis of this rs model
    */
    @Override
    public void setInterestBearingBasis(int interestBearingBasis) {
        _rsModel.setInterestBearingBasis(interestBearingBasis);
    }

    /**
    * Returns the rs model sid of this rs model.
    *
    * @return the rs model sid of this rs model
    */
    @Override
    public int getRsModelSid() {
        return _rsModel.getRsModelSid();
    }

    /**
    * Sets the rs model sid of this rs model.
    *
    * @param rsModelSid the rs model sid of this rs model
    */
    @Override
    public void setRsModelSid(int rsModelSid) {
        _rsModel.setRsModelSid(rsModelSid);
    }

    /**
    * Returns the city of this rs model.
    *
    * @return the city of this rs model
    */
    @Override
    public java.lang.String getCity() {
        return _rsModel.getCity();
    }

    /**
    * Sets the city of this rs model.
    *
    * @param city the city of this rs model
    */
    @Override
    public void setCity(java.lang.String city) {
        _rsModel.setCity(city);
    }

    /**
    * Returns the rebate processing type of this rs model.
    *
    * @return the rebate processing type of this rs model
    */
    @Override
    public int getRebateProcessingType() {
        return _rsModel.getRebateProcessingType();
    }

    /**
    * Sets the rebate processing type of this rs model.
    *
    * @param rebateProcessingType the rebate processing type of this rs model
    */
    @Override
    public void setRebateProcessingType(int rebateProcessingType) {
        _rsModel.setRebateProcessingType(rebateProcessingType);
    }

    /**
    * Returns the rs no of this rs model.
    *
    * @return the rs no of this rs model
    */
    @Override
    public java.lang.String getRsNo() {
        return _rsModel.getRsNo();
    }

    /**
    * Sets the rs no of this rs model.
    *
    * @param rsNo the rs no of this rs model
    */
    @Override
    public void setRsNo(java.lang.String rsNo) {
        _rsModel.setRsNo(rsNo);
    }

    /**
    * Returns the state of this rs model.
    *
    * @return the state of this rs model
    */
    @Override
    public int getState() {
        return _rsModel.getState();
    }

    /**
    * Sets the state of this rs model.
    *
    * @param state the state of this rs model
    */
    @Override
    public void setState(int state) {
        _rsModel.setState(state);
    }

    /**
    * Returns the rebate frequency of this rs model.
    *
    * @return the rebate frequency of this rs model
    */
    @Override
    public int getRebateFrequency() {
        return _rsModel.getRebateFrequency();
    }

    /**
    * Sets the rebate frequency of this rs model.
    *
    * @param rebateFrequency the rebate frequency of this rs model
    */
    @Override
    public void setRebateFrequency(int rebateFrequency) {
        _rsModel.setRebateFrequency(rebateFrequency);
    }

    /**
    * Returns the parent rs ID of this rs model.
    *
    * @return the parent rs ID of this rs model
    */
    @Override
    public java.lang.String getParentRsId() {
        return _rsModel.getParentRsId();
    }

    /**
    * Sets the parent rs ID of this rs model.
    *
    * @param parentRsId the parent rs ID of this rs model
    */
    @Override
    public void setParentRsId(java.lang.String parentRsId) {
        _rsModel.setParentRsId(parentRsId);
    }

    /**
    * Returns the rs type of this rs model.
    *
    * @return the rs type of this rs model
    */
    @Override
    public int getRsType() {
        return _rsModel.getRsType();
    }

    /**
    * Sets the rs type of this rs model.
    *
    * @param rsType the rs type of this rs model
    */
    @Override
    public void setRsType(int rsType) {
        _rsModel.setRsType(rsType);
    }

    /**
    * Returns the rs start date of this rs model.
    *
    * @return the rs start date of this rs model
    */
    @Override
    public java.util.Date getRsStartDate() {
        return _rsModel.getRsStartDate();
    }

    /**
    * Sets the rs start date of this rs model.
    *
    * @param rsStartDate the rs start date of this rs model
    */
    @Override
    public void setRsStartDate(java.util.Date rsStartDate) {
        _rsModel.setRsStartDate(rsStartDate);
    }

    /**
    * Returns the make payable to of this rs model.
    *
    * @return the make payable to of this rs model
    */
    @Override
    public java.lang.String getMakePayableTo() {
        return _rsModel.getMakePayableTo();
    }

    /**
    * Sets the make payable to of this rs model.
    *
    * @param makePayableTo the make payable to of this rs model
    */
    @Override
    public void setMakePayableTo(java.lang.String makePayableTo) {
        _rsModel.setMakePayableTo(makePayableTo);
    }

    /**
    * Returns the rs designation of this rs model.
    *
    * @return the rs designation of this rs model
    */
    @Override
    public int getRsDesignation() {
        return _rsModel.getRsDesignation();
    }

    /**
    * Sets the rs designation of this rs model.
    *
    * @param rsDesignation the rs designation of this rs model
    */
    @Override
    public void setRsDesignation(int rsDesignation) {
        _rsModel.setRsDesignation(rsDesignation);
    }

    /**
    * Returns the rs trans ref name of this rs model.
    *
    * @return the rs trans ref name of this rs model
    */
    @Override
    public java.lang.String getRsTransRefName() {
        return _rsModel.getRsTransRefName();
    }

    /**
    * Sets the rs trans ref name of this rs model.
    *
    * @param rsTransRefName the rs trans ref name of this rs model
    */
    @Override
    public void setRsTransRefName(java.lang.String rsTransRefName) {
        _rsModel.setRsTransRefName(rsTransRefName);
    }

    /**
    * Returns the created by of this rs model.
    *
    * @return the created by of this rs model
    */
    @Override
    public int getCreatedBy() {
        return _rsModel.getCreatedBy();
    }

    /**
    * Sets the created by of this rs model.
    *
    * @param createdBy the created by of this rs model
    */
    @Override
    public void setCreatedBy(int createdBy) {
        _rsModel.setCreatedBy(createdBy);
    }

    /**
    * Returns the created date of this rs model.
    *
    * @return the created date of this rs model
    */
    @Override
    public java.util.Date getCreatedDate() {
        return _rsModel.getCreatedDate();
    }

    /**
    * Sets the created date of this rs model.
    *
    * @param createdDate the created date of this rs model
    */
    @Override
    public void setCreatedDate(java.util.Date createdDate) {
        _rsModel.setCreatedDate(createdDate);
    }

    /**
    * Returns the rs trans ref ID of this rs model.
    *
    * @return the rs trans ref ID of this rs model
    */
    @Override
    public java.lang.String getRsTransRefId() {
        return _rsModel.getRsTransRefId();
    }

    /**
    * Sets the rs trans ref ID of this rs model.
    *
    * @param rsTransRefId the rs trans ref ID of this rs model
    */
    @Override
    public void setRsTransRefId(java.lang.String rsTransRefId) {
        _rsModel.setRsTransRefId(rsTransRefId);
    }

    /**
    * Returns the rs category of this rs model.
    *
    * @return the rs category of this rs model
    */
    @Override
    public int getRsCategory() {
        return _rsModel.getRsCategory();
    }

    /**
    * Sets the rs category of this rs model.
    *
    * @param rsCategory the rs category of this rs model
    */
    @Override
    public void setRsCategory(int rsCategory) {
        _rsModel.setRsCategory(rsCategory);
    }

    /**
    * Returns the rs trade class of this rs model.
    *
    * @return the rs trade class of this rs model
    */
    @Override
    public int getRsTradeClass() {
        return _rsModel.getRsTradeClass();
    }

    /**
    * Sets the rs trade class of this rs model.
    *
    * @param rsTradeClass the rs trade class of this rs model
    */
    @Override
    public void setRsTradeClass(int rsTradeClass) {
        _rsModel.setRsTradeClass(rsTradeClass);
    }

    /**
    * Returns the interest bearing indicator of this rs model.
    *
    * @return the interest bearing indicator of this rs model
    */
    @Override
    public int getInterestBearingIndicator() {
        return _rsModel.getInterestBearingIndicator();
    }

    /**
    * Sets the interest bearing indicator of this rs model.
    *
    * @param interestBearingIndicator the interest bearing indicator of this rs model
    */
    @Override
    public void setInterestBearingIndicator(int interestBearingIndicator) {
        _rsModel.setInterestBearingIndicator(interestBearingIndicator);
    }

    /**
    * Returns the manf company master sid of this rs model.
    *
    * @return the manf company master sid of this rs model
    */
    @Override
    public int getManfCompanyMasterSid() {
        return _rsModel.getManfCompanyMasterSid();
    }

    /**
    * Sets the manf company master sid of this rs model.
    *
    * @param manfCompanyMasterSid the manf company master sid of this rs model
    */
    @Override
    public void setManfCompanyMasterSid(int manfCompanyMasterSid) {
        _rsModel.setManfCompanyMasterSid(manfCompanyMasterSid);
    }

    /**
    * Returns the payment terms of this rs model.
    *
    * @return the payment terms of this rs model
    */
    @Override
    public int getPaymentTerms() {
        return _rsModel.getPaymentTerms();
    }

    /**
    * Sets the payment terms of this rs model.
    *
    * @param paymentTerms the payment terms of this rs model
    */
    @Override
    public void setPaymentTerms(int paymentTerms) {
        _rsModel.setPaymentTerms(paymentTerms);
    }

    /**
    * Returns the address1 of this rs model.
    *
    * @return the address1 of this rs model
    */
    @Override
    public java.lang.String getAddress1() {
        return _rsModel.getAddress1();
    }

    /**
    * Sets the address1 of this rs model.
    *
    * @param address1 the address1 of this rs model
    */
    @Override
    public void setAddress1(java.lang.String address1) {
        _rsModel.setAddress1(address1);
    }

    /**
    * Returns the address2 of this rs model.
    *
    * @return the address2 of this rs model
    */
    @Override
    public java.lang.String getAddress2() {
        return _rsModel.getAddress2();
    }

    /**
    * Sets the address2 of this rs model.
    *
    * @param address2 the address2 of this rs model
    */
    @Override
    public void setAddress2(java.lang.String address2) {
        _rsModel.setAddress2(address2);
    }

    /**
    * Returns the rs validation profile of this rs model.
    *
    * @return the rs validation profile of this rs model
    */
    @Override
    public int getRsValidationProfile() {
        return _rsModel.getRsValidationProfile();
    }

    /**
    * Sets the rs validation profile of this rs model.
    *
    * @param rsValidationProfile the rs validation profile of this rs model
    */
    @Override
    public void setRsValidationProfile(int rsValidationProfile) {
        _rsModel.setRsValidationProfile(rsValidationProfile);
    }

    /**
    * Returns the payment grace period of this rs model.
    *
    * @return the payment grace period of this rs model
    */
    @Override
    public java.lang.String getPaymentGracePeriod() {
        return _rsModel.getPaymentGracePeriod();
    }

    /**
    * Sets the payment grace period of this rs model.
    *
    * @param paymentGracePeriod the payment grace period of this rs model
    */
    @Override
    public void setPaymentGracePeriod(java.lang.String paymentGracePeriod) {
        _rsModel.setPaymentGracePeriod(paymentGracePeriod);
    }

    /**
    * Returns the batch ID of this rs model.
    *
    * @return the batch ID of this rs model
    */
    @Override
    public java.lang.String getBatchId() {
        return _rsModel.getBatchId();
    }

    /**
    * Sets the batch ID of this rs model.
    *
    * @param batchId the batch ID of this rs model
    */
    @Override
    public void setBatchId(java.lang.String batchId) {
        _rsModel.setBatchId(batchId);
    }

    /**
    * Returns the evaluation rule type of this rs model.
    *
    * @return the evaluation rule type of this rs model
    */
    @Override
    public java.lang.String getEvaluationRuleType() {
        return _rsModel.getEvaluationRuleType();
    }

    /**
    * Sets the evaluation rule type of this rs model.
    *
    * @param evaluationRuleType the evaluation rule type of this rs model
    */
    @Override
    public void setEvaluationRuleType(java.lang.String evaluationRuleType) {
        _rsModel.setEvaluationRuleType(evaluationRuleType);
    }

    /**
    * Returns the evaluation rule level of this rs model.
    *
    * @return the evaluation rule level of this rs model
    */
    @Override
    public java.lang.String getEvaluationRuleLevel() {
        return _rsModel.getEvaluationRuleLevel();
    }

    /**
    * Sets the evaluation rule level of this rs model.
    *
    * @param evaluationRuleLevel the evaluation rule level of this rs model
    */
    @Override
    public void setEvaluationRuleLevel(java.lang.String evaluationRuleLevel) {
        _rsModel.setEvaluationRuleLevel(evaluationRuleLevel);
    }

    /**
    * Returns the evaluation rule or association of this rs model.
    *
    * @return the evaluation rule or association of this rs model
    */
    @Override
    public java.lang.String getEvaluationRuleOrAssociation() {
        return _rsModel.getEvaluationRuleOrAssociation();
    }

    /**
    * Sets the evaluation rule or association of this rs model.
    *
    * @param evaluationRuleOrAssociation the evaluation rule or association of this rs model
    */
    @Override
    public void setEvaluationRuleOrAssociation(
        java.lang.String evaluationRuleOrAssociation) {
        _rsModel.setEvaluationRuleOrAssociation(evaluationRuleOrAssociation);
    }

    /**
    * Returns the calculation rule level of this rs model.
    *
    * @return the calculation rule level of this rs model
    */
    @Override
    public java.lang.String getCalculationRuleLevel() {
        return _rsModel.getCalculationRuleLevel();
    }

    /**
    * Sets the calculation rule level of this rs model.
    *
    * @param calculationRuleLevel the calculation rule level of this rs model
    */
    @Override
    public void setCalculationRuleLevel(java.lang.String calculationRuleLevel) {
        _rsModel.setCalculationRuleLevel(calculationRuleLevel);
    }

    /**
    * Returns the calculation rule of this rs model.
    *
    * @return the calculation rule of this rs model
    */
    @Override
    public java.lang.String getCalculationRule() {
        return _rsModel.getCalculationRule();
    }

    /**
    * Sets the calculation rule of this rs model.
    *
    * @param calculationRule the calculation rule of this rs model
    */
    @Override
    public void setCalculationRule(java.lang.String calculationRule) {
        _rsModel.setCalculationRule(calculationRule);
    }

    /**
    * Returns the deduction inclusion of this rs model.
    *
    * @return the deduction inclusion of this rs model
    */
    @Override
    public java.lang.String getDeductionInclusion() {
        return _rsModel.getDeductionInclusion();
    }

    /**
    * Sets the deduction inclusion of this rs model.
    *
    * @param deductionInclusion the deduction inclusion of this rs model
    */
    @Override
    public void setDeductionInclusion(java.lang.String deductionInclusion) {
        _rsModel.setDeductionInclusion(deductionInclusion);
    }

    @Override
    public boolean isNew() {
        return _rsModel.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _rsModel.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _rsModel.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _rsModel.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _rsModel.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _rsModel.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _rsModel.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _rsModel.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _rsModel.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _rsModel.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _rsModel.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new RsModelWrapper((RsModel) _rsModel.clone());
    }

    @Override
    public int compareTo(RsModel rsModel) {
        return _rsModel.compareTo(rsModel);
    }

    @Override
    public int hashCode() {
        return _rsModel.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<RsModel> toCacheModel() {
        return _rsModel.toCacheModel();
    }

    @Override
    public RsModel toEscapedModel() {
        return new RsModelWrapper(_rsModel.toEscapedModel());
    }

    @Override
    public RsModel toUnescapedModel() {
        return new RsModelWrapper(_rsModel.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _rsModel.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _rsModel.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _rsModel.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof RsModelWrapper)) {
            return false;
        }

        RsModelWrapper rsModelWrapper = (RsModelWrapper) obj;

        if (Validator.equals(_rsModel, rsModelWrapper._rsModel)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public RsModel getWrappedRsModel() {
        return _rsModel;
    }

    @Override
    public RsModel getWrappedModel() {
        return _rsModel;
    }

    @Override
    public void resetOriginalValues() {
        _rsModel.resetOriginalValues();
    }
}
