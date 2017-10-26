package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link RsContract}.
 * </p>
 *
 * @author
 * @see RsContract
 * @generated
 */
public class RsContractWrapper implements RsContract, ModelWrapper<RsContract> {
    private RsContract _rsContract;

    public RsContractWrapper(RsContract rsContract) {
        _rsContract = rsContract;
    }

    @Override
    public Class<?> getModelClass() {
        return RsContract.class;
    }

    @Override
    public String getModelClassName() {
        return RsContract.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("cfpContractSid", getCfpContractSid());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("psContractSid", getPsContractSid());
        attributes.put("rsName", getRsName());
        attributes.put("rsStatus", getRsStatus());
        attributes.put("rsStartDate", getRsStartDate());
        attributes.put("rsTransRefId", getRsTransRefId());
        attributes.put("makePayableTo", getMakePayableTo());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("rsCategory", getRsCategory());
        attributes.put("rsTradeClass", getRsTradeClass());
        attributes.put("contractMasterSid", getContractMasterSid());
        attributes.put("rebateRuleType", getRebateRuleType());
        attributes.put("paymentMethod", getPaymentMethod());
        attributes.put("rsContractAttachedDate", getRsContractAttachedDate());
        attributes.put("rsAlias", getRsAlias());
        attributes.put("formulaMethodId", getFormulaMethodId());
        attributes.put("rebateProcessingType", getRebateProcessingType());
        attributes.put("rsContractAttachedStatus", getRsContractAttachedStatus());
        attributes.put("interestBearingBasis", getInterestBearingBasis());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("rsTransRefName", getRsTransRefName());
        attributes.put("rebateProgramType", getRebateProgramType());
        attributes.put("rebatePlanLevel", getRebatePlanLevel());
        attributes.put("source", getSource());
        attributes.put("rsCalendar", getRsCalendar());
        attributes.put("rsType", getRsType());
        attributes.put("address1", getAddress1());
        attributes.put("address2", getAddress2());
        attributes.put("rsEndDate", getRsEndDate());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("rsTransRefNo", getRsTransRefNo());
        attributes.put("zipCode", getZipCode());
        attributes.put("rebateRuleAssociation", getRebateRuleAssociation());
        attributes.put("state", getState());
        attributes.put("rebateFrequency", getRebateFrequency());
        attributes.put("rsDesignation", getRsDesignation());
        attributes.put("batchId", getBatchId());
        attributes.put("ifpContractSid", getIfpContractSid());
        attributes.put("rsContractSid", getRsContractSid());
        attributes.put("paymentTerms", getPaymentTerms());
        attributes.put("rsNo", getRsNo());
        attributes.put("rsModelSid", getRsModelSid());
        attributes.put("rsValidationProfile", getRsValidationProfile());
        attributes.put("paymentGracePeriod", getPaymentGracePeriod());
        attributes.put("paymentFrequency", getPaymentFrequency());
        attributes.put("recordLockStatus", getRecordLockStatus());
        attributes.put("rsId", getRsId());
        attributes.put("city", getCity());
        attributes.put("parentRsName", getParentRsName());
        attributes.put("interestBearingIndicator", getInterestBearingIndicator());
        attributes.put("parentRsId", getParentRsId());
        attributes.put("inboundStatus", getInboundStatus());
        attributes.put("calculationType", getCalculationType());
        attributes.put("calculationLevel", getCalculationLevel());
        attributes.put("calculationRule", getCalculationRule());
        attributes.put("calculationRuleLevel", getCalculationRuleLevel());
        attributes.put("evaluationRuleType", getEvaluationRuleType());
        attributes.put("evaluationRuleLevel", getEvaluationRuleLevel());
        attributes.put("evaluationRuleOrAssociation",
            getEvaluationRuleOrAssociation());
        attributes.put("deductionInclusion", getDeductionInclusion());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String cfpContractSid = (String) attributes.get("cfpContractSid");

        if (cfpContractSid != null) {
            setCfpContractSid(cfpContractSid);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        String psContractSid = (String) attributes.get("psContractSid");

        if (psContractSid != null) {
            setPsContractSid(psContractSid);
        }

        String rsName = (String) attributes.get("rsName");

        if (rsName != null) {
            setRsName(rsName);
        }

        Integer rsStatus = (Integer) attributes.get("rsStatus");

        if (rsStatus != null) {
            setRsStatus(rsStatus);
        }

        Date rsStartDate = (Date) attributes.get("rsStartDate");

        if (rsStartDate != null) {
            setRsStartDate(rsStartDate);
        }

        String rsTransRefId = (String) attributes.get("rsTransRefId");

        if (rsTransRefId != null) {
            setRsTransRefId(rsTransRefId);
        }

        String makePayableTo = (String) attributes.get("makePayableTo");

        if (makePayableTo != null) {
            setMakePayableTo(makePayableTo);
        }

        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        Integer rsCategory = (Integer) attributes.get("rsCategory");

        if (rsCategory != null) {
            setRsCategory(rsCategory);
        }

        Integer rsTradeClass = (Integer) attributes.get("rsTradeClass");

        if (rsTradeClass != null) {
            setRsTradeClass(rsTradeClass);
        }

        Integer contractMasterSid = (Integer) attributes.get(
                "contractMasterSid");

        if (contractMasterSid != null) {
            setContractMasterSid(contractMasterSid);
        }

        Integer rebateRuleType = (Integer) attributes.get("rebateRuleType");

        if (rebateRuleType != null) {
            setRebateRuleType(rebateRuleType);
        }

        Integer paymentMethod = (Integer) attributes.get("paymentMethod");

        if (paymentMethod != null) {
            setPaymentMethod(paymentMethod);
        }

        Date rsContractAttachedDate = (Date) attributes.get(
                "rsContractAttachedDate");

        if (rsContractAttachedDate != null) {
            setRsContractAttachedDate(rsContractAttachedDate);
        }

        String rsAlias = (String) attributes.get("rsAlias");

        if (rsAlias != null) {
            setRsAlias(rsAlias);
        }

        String formulaMethodId = (String) attributes.get("formulaMethodId");

        if (formulaMethodId != null) {
            setFormulaMethodId(formulaMethodId);
        }

        Integer rebateProcessingType = (Integer) attributes.get(
                "rebateProcessingType");

        if (rebateProcessingType != null) {
            setRebateProcessingType(rebateProcessingType);
        }

        Integer rsContractAttachedStatus = (Integer) attributes.get(
                "rsContractAttachedStatus");

        if (rsContractAttachedStatus != null) {
            setRsContractAttachedStatus(rsContractAttachedStatus);
        }

        Integer interestBearingBasis = (Integer) attributes.get(
                "interestBearingBasis");

        if (interestBearingBasis != null) {
            setInterestBearingBasis(interestBearingBasis);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        String rsTransRefName = (String) attributes.get("rsTransRefName");

        if (rsTransRefName != null) {
            setRsTransRefName(rsTransRefName);
        }

        Integer rebateProgramType = (Integer) attributes.get(
                "rebateProgramType");

        if (rebateProgramType != null) {
            setRebateProgramType(rebateProgramType);
        }

        String rebatePlanLevel = (String) attributes.get("rebatePlanLevel");

        if (rebatePlanLevel != null) {
            setRebatePlanLevel(rebatePlanLevel);
        }

        String source = (String) attributes.get("source");

        if (source != null) {
            setSource(source);
        }

        String rsCalendar = (String) attributes.get("rsCalendar");

        if (rsCalendar != null) {
            setRsCalendar(rsCalendar);
        }

        Integer rsType = (Integer) attributes.get("rsType");

        if (rsType != null) {
            setRsType(rsType);
        }

        String address1 = (String) attributes.get("address1");

        if (address1 != null) {
            setAddress1(address1);
        }

        String address2 = (String) attributes.get("address2");

        if (address2 != null) {
            setAddress2(address2);
        }

        Date rsEndDate = (Date) attributes.get("rsEndDate");

        if (rsEndDate != null) {
            setRsEndDate(rsEndDate);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        String rsTransRefNo = (String) attributes.get("rsTransRefNo");

        if (rsTransRefNo != null) {
            setRsTransRefNo(rsTransRefNo);
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

        Integer state = (Integer) attributes.get("state");

        if (state != null) {
            setState(state);
        }

        Integer rebateFrequency = (Integer) attributes.get("rebateFrequency");

        if (rebateFrequency != null) {
            setRebateFrequency(rebateFrequency);
        }

        String rsDesignation = (String) attributes.get("rsDesignation");

        if (rsDesignation != null) {
            setRsDesignation(rsDesignation);
        }

        String batchId = (String) attributes.get("batchId");

        if (batchId != null) {
            setBatchId(batchId);
        }

        String ifpContractSid = (String) attributes.get("ifpContractSid");

        if (ifpContractSid != null) {
            setIfpContractSid(ifpContractSid);
        }

        Integer rsContractSid = (Integer) attributes.get("rsContractSid");

        if (rsContractSid != null) {
            setRsContractSid(rsContractSid);
        }

        Integer paymentTerms = (Integer) attributes.get("paymentTerms");

        if (paymentTerms != null) {
            setPaymentTerms(paymentTerms);
        }

        String rsNo = (String) attributes.get("rsNo");

        if (rsNo != null) {
            setRsNo(rsNo);
        }

        Integer rsModelSid = (Integer) attributes.get("rsModelSid");

        if (rsModelSid != null) {
            setRsModelSid(rsModelSid);
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

        Integer paymentFrequency = (Integer) attributes.get("paymentFrequency");

        if (paymentFrequency != null) {
            setPaymentFrequency(paymentFrequency);
        }

        Boolean recordLockStatus = (Boolean) attributes.get("recordLockStatus");

        if (recordLockStatus != null) {
            setRecordLockStatus(recordLockStatus);
        }

        String rsId = (String) attributes.get("rsId");

        if (rsId != null) {
            setRsId(rsId);
        }

        String city = (String) attributes.get("city");

        if (city != null) {
            setCity(city);
        }

        String parentRsName = (String) attributes.get("parentRsName");

        if (parentRsName != null) {
            setParentRsName(parentRsName);
        }

        Integer interestBearingIndicator = (Integer) attributes.get(
                "interestBearingIndicator");

        if (interestBearingIndicator != null) {
            setInterestBearingIndicator(interestBearingIndicator);
        }

        String parentRsId = (String) attributes.get("parentRsId");

        if (parentRsId != null) {
            setParentRsId(parentRsId);
        }

        String inboundStatus = (String) attributes.get("inboundStatus");

        if (inboundStatus != null) {
            setInboundStatus(inboundStatus);
        }

        Integer calculationType = (Integer) attributes.get("calculationType");

        if (calculationType != null) {
            setCalculationType(calculationType);
        }

        Integer calculationLevel = (Integer) attributes.get("calculationLevel");

        if (calculationLevel != null) {
            setCalculationLevel(calculationLevel);
        }

        String calculationRule = (String) attributes.get("calculationRule");

        if (calculationRule != null) {
            setCalculationRule(calculationRule);
        }

        String calculationRuleLevel = (String) attributes.get(
                "calculationRuleLevel");

        if (calculationRuleLevel != null) {
            setCalculationRuleLevel(calculationRuleLevel);
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

        String deductionInclusion = (String) attributes.get(
                "deductionInclusion");

        if (deductionInclusion != null) {
            setDeductionInclusion(deductionInclusion);
        }
    }

    /**
    * Returns the primary key of this rs contract.
    *
    * @return the primary key of this rs contract
    */
    @Override
    public int getPrimaryKey() {
        return _rsContract.getPrimaryKey();
    }

    /**
    * Sets the primary key of this rs contract.
    *
    * @param primaryKey the primary key of this rs contract
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _rsContract.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the cfp contract sid of this rs contract.
    *
    * @return the cfp contract sid of this rs contract
    */
    @Override
    public java.lang.String getCfpContractSid() {
        return _rsContract.getCfpContractSid();
    }

    /**
    * Sets the cfp contract sid of this rs contract.
    *
    * @param cfpContractSid the cfp contract sid of this rs contract
    */
    @Override
    public void setCfpContractSid(java.lang.String cfpContractSid) {
        _rsContract.setCfpContractSid(cfpContractSid);
    }

    /**
    * Returns the created date of this rs contract.
    *
    * @return the created date of this rs contract
    */
    @Override
    public java.util.Date getCreatedDate() {
        return _rsContract.getCreatedDate();
    }

    /**
    * Sets the created date of this rs contract.
    *
    * @param createdDate the created date of this rs contract
    */
    @Override
    public void setCreatedDate(java.util.Date createdDate) {
        _rsContract.setCreatedDate(createdDate);
    }

    /**
    * Returns the ps contract sid of this rs contract.
    *
    * @return the ps contract sid of this rs contract
    */
    @Override
    public java.lang.String getPsContractSid() {
        return _rsContract.getPsContractSid();
    }

    /**
    * Sets the ps contract sid of this rs contract.
    *
    * @param psContractSid the ps contract sid of this rs contract
    */
    @Override
    public void setPsContractSid(java.lang.String psContractSid) {
        _rsContract.setPsContractSid(psContractSid);
    }

    /**
    * Returns the rs name of this rs contract.
    *
    * @return the rs name of this rs contract
    */
    @Override
    public java.lang.String getRsName() {
        return _rsContract.getRsName();
    }

    /**
    * Sets the rs name of this rs contract.
    *
    * @param rsName the rs name of this rs contract
    */
    @Override
    public void setRsName(java.lang.String rsName) {
        _rsContract.setRsName(rsName);
    }

    /**
    * Returns the rs status of this rs contract.
    *
    * @return the rs status of this rs contract
    */
    @Override
    public int getRsStatus() {
        return _rsContract.getRsStatus();
    }

    /**
    * Sets the rs status of this rs contract.
    *
    * @param rsStatus the rs status of this rs contract
    */
    @Override
    public void setRsStatus(int rsStatus) {
        _rsContract.setRsStatus(rsStatus);
    }

    /**
    * Returns the rs start date of this rs contract.
    *
    * @return the rs start date of this rs contract
    */
    @Override
    public java.util.Date getRsStartDate() {
        return _rsContract.getRsStartDate();
    }

    /**
    * Sets the rs start date of this rs contract.
    *
    * @param rsStartDate the rs start date of this rs contract
    */
    @Override
    public void setRsStartDate(java.util.Date rsStartDate) {
        _rsContract.setRsStartDate(rsStartDate);
    }

    /**
    * Returns the rs trans ref ID of this rs contract.
    *
    * @return the rs trans ref ID of this rs contract
    */
    @Override
    public java.lang.String getRsTransRefId() {
        return _rsContract.getRsTransRefId();
    }

    /**
    * Sets the rs trans ref ID of this rs contract.
    *
    * @param rsTransRefId the rs trans ref ID of this rs contract
    */
    @Override
    public void setRsTransRefId(java.lang.String rsTransRefId) {
        _rsContract.setRsTransRefId(rsTransRefId);
    }

    /**
    * Returns the make payable to of this rs contract.
    *
    * @return the make payable to of this rs contract
    */
    @Override
    public java.lang.String getMakePayableTo() {
        return _rsContract.getMakePayableTo();
    }

    /**
    * Sets the make payable to of this rs contract.
    *
    * @param makePayableTo the make payable to of this rs contract
    */
    @Override
    public void setMakePayableTo(java.lang.String makePayableTo) {
        _rsContract.setMakePayableTo(makePayableTo);
    }

    /**
    * Returns the created by of this rs contract.
    *
    * @return the created by of this rs contract
    */
    @Override
    public int getCreatedBy() {
        return _rsContract.getCreatedBy();
    }

    /**
    * Sets the created by of this rs contract.
    *
    * @param createdBy the created by of this rs contract
    */
    @Override
    public void setCreatedBy(int createdBy) {
        _rsContract.setCreatedBy(createdBy);
    }

    /**
    * Returns the rs category of this rs contract.
    *
    * @return the rs category of this rs contract
    */
    @Override
    public int getRsCategory() {
        return _rsContract.getRsCategory();
    }

    /**
    * Sets the rs category of this rs contract.
    *
    * @param rsCategory the rs category of this rs contract
    */
    @Override
    public void setRsCategory(int rsCategory) {
        _rsContract.setRsCategory(rsCategory);
    }

    /**
    * Returns the rs trade class of this rs contract.
    *
    * @return the rs trade class of this rs contract
    */
    @Override
    public int getRsTradeClass() {
        return _rsContract.getRsTradeClass();
    }

    /**
    * Sets the rs trade class of this rs contract.
    *
    * @param rsTradeClass the rs trade class of this rs contract
    */
    @Override
    public void setRsTradeClass(int rsTradeClass) {
        _rsContract.setRsTradeClass(rsTradeClass);
    }

    /**
    * Returns the contract master sid of this rs contract.
    *
    * @return the contract master sid of this rs contract
    */
    @Override
    public int getContractMasterSid() {
        return _rsContract.getContractMasterSid();
    }

    /**
    * Sets the contract master sid of this rs contract.
    *
    * @param contractMasterSid the contract master sid of this rs contract
    */
    @Override
    public void setContractMasterSid(int contractMasterSid) {
        _rsContract.setContractMasterSid(contractMasterSid);
    }

    /**
    * Returns the rebate rule type of this rs contract.
    *
    * @return the rebate rule type of this rs contract
    */
    @Override
    public int getRebateRuleType() {
        return _rsContract.getRebateRuleType();
    }

    /**
    * Sets the rebate rule type of this rs contract.
    *
    * @param rebateRuleType the rebate rule type of this rs contract
    */
    @Override
    public void setRebateRuleType(int rebateRuleType) {
        _rsContract.setRebateRuleType(rebateRuleType);
    }

    /**
    * Returns the payment method of this rs contract.
    *
    * @return the payment method of this rs contract
    */
    @Override
    public int getPaymentMethod() {
        return _rsContract.getPaymentMethod();
    }

    /**
    * Sets the payment method of this rs contract.
    *
    * @param paymentMethod the payment method of this rs contract
    */
    @Override
    public void setPaymentMethod(int paymentMethod) {
        _rsContract.setPaymentMethod(paymentMethod);
    }

    /**
    * Returns the rs contract attached date of this rs contract.
    *
    * @return the rs contract attached date of this rs contract
    */
    @Override
    public java.util.Date getRsContractAttachedDate() {
        return _rsContract.getRsContractAttachedDate();
    }

    /**
    * Sets the rs contract attached date of this rs contract.
    *
    * @param rsContractAttachedDate the rs contract attached date of this rs contract
    */
    @Override
    public void setRsContractAttachedDate(java.util.Date rsContractAttachedDate) {
        _rsContract.setRsContractAttachedDate(rsContractAttachedDate);
    }

    /**
    * Returns the rs alias of this rs contract.
    *
    * @return the rs alias of this rs contract
    */
    @Override
    public java.lang.String getRsAlias() {
        return _rsContract.getRsAlias();
    }

    /**
    * Sets the rs alias of this rs contract.
    *
    * @param rsAlias the rs alias of this rs contract
    */
    @Override
    public void setRsAlias(java.lang.String rsAlias) {
        _rsContract.setRsAlias(rsAlias);
    }

    /**
    * Returns the formula method ID of this rs contract.
    *
    * @return the formula method ID of this rs contract
    */
    @Override
    public java.lang.String getFormulaMethodId() {
        return _rsContract.getFormulaMethodId();
    }

    /**
    * Sets the formula method ID of this rs contract.
    *
    * @param formulaMethodId the formula method ID of this rs contract
    */
    @Override
    public void setFormulaMethodId(java.lang.String formulaMethodId) {
        _rsContract.setFormulaMethodId(formulaMethodId);
    }

    /**
    * Returns the rebate processing type of this rs contract.
    *
    * @return the rebate processing type of this rs contract
    */
    @Override
    public int getRebateProcessingType() {
        return _rsContract.getRebateProcessingType();
    }

    /**
    * Sets the rebate processing type of this rs contract.
    *
    * @param rebateProcessingType the rebate processing type of this rs contract
    */
    @Override
    public void setRebateProcessingType(int rebateProcessingType) {
        _rsContract.setRebateProcessingType(rebateProcessingType);
    }

    /**
    * Returns the rs contract attached status of this rs contract.
    *
    * @return the rs contract attached status of this rs contract
    */
    @Override
    public int getRsContractAttachedStatus() {
        return _rsContract.getRsContractAttachedStatus();
    }

    /**
    * Sets the rs contract attached status of this rs contract.
    *
    * @param rsContractAttachedStatus the rs contract attached status of this rs contract
    */
    @Override
    public void setRsContractAttachedStatus(int rsContractAttachedStatus) {
        _rsContract.setRsContractAttachedStatus(rsContractAttachedStatus);
    }

    /**
    * Returns the interest bearing basis of this rs contract.
    *
    * @return the interest bearing basis of this rs contract
    */
    @Override
    public int getInterestBearingBasis() {
        return _rsContract.getInterestBearingBasis();
    }

    /**
    * Sets the interest bearing basis of this rs contract.
    *
    * @param interestBearingBasis the interest bearing basis of this rs contract
    */
    @Override
    public void setInterestBearingBasis(int interestBearingBasis) {
        _rsContract.setInterestBearingBasis(interestBearingBasis);
    }

    /**
    * Returns the modified date of this rs contract.
    *
    * @return the modified date of this rs contract
    */
    @Override
    public java.util.Date getModifiedDate() {
        return _rsContract.getModifiedDate();
    }

    /**
    * Sets the modified date of this rs contract.
    *
    * @param modifiedDate the modified date of this rs contract
    */
    @Override
    public void setModifiedDate(java.util.Date modifiedDate) {
        _rsContract.setModifiedDate(modifiedDate);
    }

    /**
    * Returns the rs trans ref name of this rs contract.
    *
    * @return the rs trans ref name of this rs contract
    */
    @Override
    public java.lang.String getRsTransRefName() {
        return _rsContract.getRsTransRefName();
    }

    /**
    * Sets the rs trans ref name of this rs contract.
    *
    * @param rsTransRefName the rs trans ref name of this rs contract
    */
    @Override
    public void setRsTransRefName(java.lang.String rsTransRefName) {
        _rsContract.setRsTransRefName(rsTransRefName);
    }

    /**
    * Returns the rebate program type of this rs contract.
    *
    * @return the rebate program type of this rs contract
    */
    @Override
    public int getRebateProgramType() {
        return _rsContract.getRebateProgramType();
    }

    /**
    * Sets the rebate program type of this rs contract.
    *
    * @param rebateProgramType the rebate program type of this rs contract
    */
    @Override
    public void setRebateProgramType(int rebateProgramType) {
        _rsContract.setRebateProgramType(rebateProgramType);
    }

    /**
    * Returns the rebate plan level of this rs contract.
    *
    * @return the rebate plan level of this rs contract
    */
    @Override
    public java.lang.String getRebatePlanLevel() {
        return _rsContract.getRebatePlanLevel();
    }

    /**
    * Sets the rebate plan level of this rs contract.
    *
    * @param rebatePlanLevel the rebate plan level of this rs contract
    */
    @Override
    public void setRebatePlanLevel(java.lang.String rebatePlanLevel) {
        _rsContract.setRebatePlanLevel(rebatePlanLevel);
    }

    /**
    * Returns the source of this rs contract.
    *
    * @return the source of this rs contract
    */
    @Override
    public java.lang.String getSource() {
        return _rsContract.getSource();
    }

    /**
    * Sets the source of this rs contract.
    *
    * @param source the source of this rs contract
    */
    @Override
    public void setSource(java.lang.String source) {
        _rsContract.setSource(source);
    }

    /**
    * Returns the rs calendar of this rs contract.
    *
    * @return the rs calendar of this rs contract
    */
    @Override
    public java.lang.String getRsCalendar() {
        return _rsContract.getRsCalendar();
    }

    /**
    * Sets the rs calendar of this rs contract.
    *
    * @param rsCalendar the rs calendar of this rs contract
    */
    @Override
    public void setRsCalendar(java.lang.String rsCalendar) {
        _rsContract.setRsCalendar(rsCalendar);
    }

    /**
    * Returns the rs type of this rs contract.
    *
    * @return the rs type of this rs contract
    */
    @Override
    public int getRsType() {
        return _rsContract.getRsType();
    }

    /**
    * Sets the rs type of this rs contract.
    *
    * @param rsType the rs type of this rs contract
    */
    @Override
    public void setRsType(int rsType) {
        _rsContract.setRsType(rsType);
    }

    /**
    * Returns the address1 of this rs contract.
    *
    * @return the address1 of this rs contract
    */
    @Override
    public java.lang.String getAddress1() {
        return _rsContract.getAddress1();
    }

    /**
    * Sets the address1 of this rs contract.
    *
    * @param address1 the address1 of this rs contract
    */
    @Override
    public void setAddress1(java.lang.String address1) {
        _rsContract.setAddress1(address1);
    }

    /**
    * Returns the address2 of this rs contract.
    *
    * @return the address2 of this rs contract
    */
    @Override
    public java.lang.String getAddress2() {
        return _rsContract.getAddress2();
    }

    /**
    * Sets the address2 of this rs contract.
    *
    * @param address2 the address2 of this rs contract
    */
    @Override
    public void setAddress2(java.lang.String address2) {
        _rsContract.setAddress2(address2);
    }

    /**
    * Returns the rs end date of this rs contract.
    *
    * @return the rs end date of this rs contract
    */
    @Override
    public java.util.Date getRsEndDate() {
        return _rsContract.getRsEndDate();
    }

    /**
    * Sets the rs end date of this rs contract.
    *
    * @param rsEndDate the rs end date of this rs contract
    */
    @Override
    public void setRsEndDate(java.util.Date rsEndDate) {
        _rsContract.setRsEndDate(rsEndDate);
    }

    /**
    * Returns the modified by of this rs contract.
    *
    * @return the modified by of this rs contract
    */
    @Override
    public int getModifiedBy() {
        return _rsContract.getModifiedBy();
    }

    /**
    * Sets the modified by of this rs contract.
    *
    * @param modifiedBy the modified by of this rs contract
    */
    @Override
    public void setModifiedBy(int modifiedBy) {
        _rsContract.setModifiedBy(modifiedBy);
    }

    /**
    * Returns the rs trans ref no of this rs contract.
    *
    * @return the rs trans ref no of this rs contract
    */
    @Override
    public java.lang.String getRsTransRefNo() {
        return _rsContract.getRsTransRefNo();
    }

    /**
    * Sets the rs trans ref no of this rs contract.
    *
    * @param rsTransRefNo the rs trans ref no of this rs contract
    */
    @Override
    public void setRsTransRefNo(java.lang.String rsTransRefNo) {
        _rsContract.setRsTransRefNo(rsTransRefNo);
    }

    /**
    * Returns the zip code of this rs contract.
    *
    * @return the zip code of this rs contract
    */
    @Override
    public java.lang.String getZipCode() {
        return _rsContract.getZipCode();
    }

    /**
    * Sets the zip code of this rs contract.
    *
    * @param zipCode the zip code of this rs contract
    */
    @Override
    public void setZipCode(java.lang.String zipCode) {
        _rsContract.setZipCode(zipCode);
    }

    /**
    * Returns the rebate rule association of this rs contract.
    *
    * @return the rebate rule association of this rs contract
    */
    @Override
    public java.lang.String getRebateRuleAssociation() {
        return _rsContract.getRebateRuleAssociation();
    }

    /**
    * Sets the rebate rule association of this rs contract.
    *
    * @param rebateRuleAssociation the rebate rule association of this rs contract
    */
    @Override
    public void setRebateRuleAssociation(java.lang.String rebateRuleAssociation) {
        _rsContract.setRebateRuleAssociation(rebateRuleAssociation);
    }

    /**
    * Returns the state of this rs contract.
    *
    * @return the state of this rs contract
    */
    @Override
    public int getState() {
        return _rsContract.getState();
    }

    /**
    * Sets the state of this rs contract.
    *
    * @param state the state of this rs contract
    */
    @Override
    public void setState(int state) {
        _rsContract.setState(state);
    }

    /**
    * Returns the rebate frequency of this rs contract.
    *
    * @return the rebate frequency of this rs contract
    */
    @Override
    public int getRebateFrequency() {
        return _rsContract.getRebateFrequency();
    }

    /**
    * Sets the rebate frequency of this rs contract.
    *
    * @param rebateFrequency the rebate frequency of this rs contract
    */
    @Override
    public void setRebateFrequency(int rebateFrequency) {
        _rsContract.setRebateFrequency(rebateFrequency);
    }

    /**
    * Returns the rs designation of this rs contract.
    *
    * @return the rs designation of this rs contract
    */
    @Override
    public java.lang.String getRsDesignation() {
        return _rsContract.getRsDesignation();
    }

    /**
    * Sets the rs designation of this rs contract.
    *
    * @param rsDesignation the rs designation of this rs contract
    */
    @Override
    public void setRsDesignation(java.lang.String rsDesignation) {
        _rsContract.setRsDesignation(rsDesignation);
    }

    /**
    * Returns the batch ID of this rs contract.
    *
    * @return the batch ID of this rs contract
    */
    @Override
    public java.lang.String getBatchId() {
        return _rsContract.getBatchId();
    }

    /**
    * Sets the batch ID of this rs contract.
    *
    * @param batchId the batch ID of this rs contract
    */
    @Override
    public void setBatchId(java.lang.String batchId) {
        _rsContract.setBatchId(batchId);
    }

    /**
    * Returns the ifp contract sid of this rs contract.
    *
    * @return the ifp contract sid of this rs contract
    */
    @Override
    public java.lang.String getIfpContractSid() {
        return _rsContract.getIfpContractSid();
    }

    /**
    * Sets the ifp contract sid of this rs contract.
    *
    * @param ifpContractSid the ifp contract sid of this rs contract
    */
    @Override
    public void setIfpContractSid(java.lang.String ifpContractSid) {
        _rsContract.setIfpContractSid(ifpContractSid);
    }

    /**
    * Returns the rs contract sid of this rs contract.
    *
    * @return the rs contract sid of this rs contract
    */
    @Override
    public int getRsContractSid() {
        return _rsContract.getRsContractSid();
    }

    /**
    * Sets the rs contract sid of this rs contract.
    *
    * @param rsContractSid the rs contract sid of this rs contract
    */
    @Override
    public void setRsContractSid(int rsContractSid) {
        _rsContract.setRsContractSid(rsContractSid);
    }

    /**
    * Returns the payment terms of this rs contract.
    *
    * @return the payment terms of this rs contract
    */
    @Override
    public int getPaymentTerms() {
        return _rsContract.getPaymentTerms();
    }

    /**
    * Sets the payment terms of this rs contract.
    *
    * @param paymentTerms the payment terms of this rs contract
    */
    @Override
    public void setPaymentTerms(int paymentTerms) {
        _rsContract.setPaymentTerms(paymentTerms);
    }

    /**
    * Returns the rs no of this rs contract.
    *
    * @return the rs no of this rs contract
    */
    @Override
    public java.lang.String getRsNo() {
        return _rsContract.getRsNo();
    }

    /**
    * Sets the rs no of this rs contract.
    *
    * @param rsNo the rs no of this rs contract
    */
    @Override
    public void setRsNo(java.lang.String rsNo) {
        _rsContract.setRsNo(rsNo);
    }

    /**
    * Returns the rs model sid of this rs contract.
    *
    * @return the rs model sid of this rs contract
    */
    @Override
    public int getRsModelSid() {
        return _rsContract.getRsModelSid();
    }

    /**
    * Sets the rs model sid of this rs contract.
    *
    * @param rsModelSid the rs model sid of this rs contract
    */
    @Override
    public void setRsModelSid(int rsModelSid) {
        _rsContract.setRsModelSid(rsModelSid);
    }

    /**
    * Returns the rs validation profile of this rs contract.
    *
    * @return the rs validation profile of this rs contract
    */
    @Override
    public int getRsValidationProfile() {
        return _rsContract.getRsValidationProfile();
    }

    /**
    * Sets the rs validation profile of this rs contract.
    *
    * @param rsValidationProfile the rs validation profile of this rs contract
    */
    @Override
    public void setRsValidationProfile(int rsValidationProfile) {
        _rsContract.setRsValidationProfile(rsValidationProfile);
    }

    /**
    * Returns the payment grace period of this rs contract.
    *
    * @return the payment grace period of this rs contract
    */
    @Override
    public java.lang.String getPaymentGracePeriod() {
        return _rsContract.getPaymentGracePeriod();
    }

    /**
    * Sets the payment grace period of this rs contract.
    *
    * @param paymentGracePeriod the payment grace period of this rs contract
    */
    @Override
    public void setPaymentGracePeriod(java.lang.String paymentGracePeriod) {
        _rsContract.setPaymentGracePeriod(paymentGracePeriod);
    }

    /**
    * Returns the payment frequency of this rs contract.
    *
    * @return the payment frequency of this rs contract
    */
    @Override
    public int getPaymentFrequency() {
        return _rsContract.getPaymentFrequency();
    }

    /**
    * Sets the payment frequency of this rs contract.
    *
    * @param paymentFrequency the payment frequency of this rs contract
    */
    @Override
    public void setPaymentFrequency(int paymentFrequency) {
        _rsContract.setPaymentFrequency(paymentFrequency);
    }

    /**
    * Returns the record lock status of this rs contract.
    *
    * @return the record lock status of this rs contract
    */
    @Override
    public boolean getRecordLockStatus() {
        return _rsContract.getRecordLockStatus();
    }

    /**
    * Returns <code>true</code> if this rs contract is record lock status.
    *
    * @return <code>true</code> if this rs contract is record lock status; <code>false</code> otherwise
    */
    @Override
    public boolean isRecordLockStatus() {
        return _rsContract.isRecordLockStatus();
    }

    /**
    * Sets whether this rs contract is record lock status.
    *
    * @param recordLockStatus the record lock status of this rs contract
    */
    @Override
    public void setRecordLockStatus(boolean recordLockStatus) {
        _rsContract.setRecordLockStatus(recordLockStatus);
    }

    /**
    * Returns the rs ID of this rs contract.
    *
    * @return the rs ID of this rs contract
    */
    @Override
    public java.lang.String getRsId() {
        return _rsContract.getRsId();
    }

    /**
    * Sets the rs ID of this rs contract.
    *
    * @param rsId the rs ID of this rs contract
    */
    @Override
    public void setRsId(java.lang.String rsId) {
        _rsContract.setRsId(rsId);
    }

    /**
    * Returns the city of this rs contract.
    *
    * @return the city of this rs contract
    */
    @Override
    public java.lang.String getCity() {
        return _rsContract.getCity();
    }

    /**
    * Sets the city of this rs contract.
    *
    * @param city the city of this rs contract
    */
    @Override
    public void setCity(java.lang.String city) {
        _rsContract.setCity(city);
    }

    /**
    * Returns the parent rs name of this rs contract.
    *
    * @return the parent rs name of this rs contract
    */
    @Override
    public java.lang.String getParentRsName() {
        return _rsContract.getParentRsName();
    }

    /**
    * Sets the parent rs name of this rs contract.
    *
    * @param parentRsName the parent rs name of this rs contract
    */
    @Override
    public void setParentRsName(java.lang.String parentRsName) {
        _rsContract.setParentRsName(parentRsName);
    }

    /**
    * Returns the interest bearing indicator of this rs contract.
    *
    * @return the interest bearing indicator of this rs contract
    */
    @Override
    public int getInterestBearingIndicator() {
        return _rsContract.getInterestBearingIndicator();
    }

    /**
    * Sets the interest bearing indicator of this rs contract.
    *
    * @param interestBearingIndicator the interest bearing indicator of this rs contract
    */
    @Override
    public void setInterestBearingIndicator(int interestBearingIndicator) {
        _rsContract.setInterestBearingIndicator(interestBearingIndicator);
    }

    /**
    * Returns the parent rs ID of this rs contract.
    *
    * @return the parent rs ID of this rs contract
    */
    @Override
    public java.lang.String getParentRsId() {
        return _rsContract.getParentRsId();
    }

    /**
    * Sets the parent rs ID of this rs contract.
    *
    * @param parentRsId the parent rs ID of this rs contract
    */
    @Override
    public void setParentRsId(java.lang.String parentRsId) {
        _rsContract.setParentRsId(parentRsId);
    }

    /**
    * Returns the inbound status of this rs contract.
    *
    * @return the inbound status of this rs contract
    */
    @Override
    public java.lang.String getInboundStatus() {
        return _rsContract.getInboundStatus();
    }

    /**
    * Sets the inbound status of this rs contract.
    *
    * @param inboundStatus the inbound status of this rs contract
    */
    @Override
    public void setInboundStatus(java.lang.String inboundStatus) {
        _rsContract.setInboundStatus(inboundStatus);
    }

    /**
    * Returns the calculation type of this rs contract.
    *
    * @return the calculation type of this rs contract
    */
    @Override
    public int getCalculationType() {
        return _rsContract.getCalculationType();
    }

    /**
    * Sets the calculation type of this rs contract.
    *
    * @param calculationType the calculation type of this rs contract
    */
    @Override
    public void setCalculationType(int calculationType) {
        _rsContract.setCalculationType(calculationType);
    }

    /**
    * Returns the calculation level of this rs contract.
    *
    * @return the calculation level of this rs contract
    */
    @Override
    public int getCalculationLevel() {
        return _rsContract.getCalculationLevel();
    }

    /**
    * Sets the calculation level of this rs contract.
    *
    * @param calculationLevel the calculation level of this rs contract
    */
    @Override
    public void setCalculationLevel(int calculationLevel) {
        _rsContract.setCalculationLevel(calculationLevel);
    }

    /**
    * Returns the calculation rule of this rs contract.
    *
    * @return the calculation rule of this rs contract
    */
    @Override
    public java.lang.String getCalculationRule() {
        return _rsContract.getCalculationRule();
    }

    /**
    * Sets the calculation rule of this rs contract.
    *
    * @param calculationRule the calculation rule of this rs contract
    */
    @Override
    public void setCalculationRule(java.lang.String calculationRule) {
        _rsContract.setCalculationRule(calculationRule);
    }

    /**
    * Returns the calculation rule level of this rs contract.
    *
    * @return the calculation rule level of this rs contract
    */
    @Override
    public java.lang.String getCalculationRuleLevel() {
        return _rsContract.getCalculationRuleLevel();
    }

    /**
    * Sets the calculation rule level of this rs contract.
    *
    * @param calculationRuleLevel the calculation rule level of this rs contract
    */
    @Override
    public void setCalculationRuleLevel(java.lang.String calculationRuleLevel) {
        _rsContract.setCalculationRuleLevel(calculationRuleLevel);
    }

    /**
    * Returns the evaluation rule type of this rs contract.
    *
    * @return the evaluation rule type of this rs contract
    */
    @Override
    public java.lang.String getEvaluationRuleType() {
        return _rsContract.getEvaluationRuleType();
    }

    /**
    * Sets the evaluation rule type of this rs contract.
    *
    * @param evaluationRuleType the evaluation rule type of this rs contract
    */
    @Override
    public void setEvaluationRuleType(java.lang.String evaluationRuleType) {
        _rsContract.setEvaluationRuleType(evaluationRuleType);
    }

    /**
    * Returns the evaluation rule level of this rs contract.
    *
    * @return the evaluation rule level of this rs contract
    */
    @Override
    public java.lang.String getEvaluationRuleLevel() {
        return _rsContract.getEvaluationRuleLevel();
    }

    /**
    * Sets the evaluation rule level of this rs contract.
    *
    * @param evaluationRuleLevel the evaluation rule level of this rs contract
    */
    @Override
    public void setEvaluationRuleLevel(java.lang.String evaluationRuleLevel) {
        _rsContract.setEvaluationRuleLevel(evaluationRuleLevel);
    }

    /**
    * Returns the evaluation rule or association of this rs contract.
    *
    * @return the evaluation rule or association of this rs contract
    */
    @Override
    public java.lang.String getEvaluationRuleOrAssociation() {
        return _rsContract.getEvaluationRuleOrAssociation();
    }

    /**
    * Sets the evaluation rule or association of this rs contract.
    *
    * @param evaluationRuleOrAssociation the evaluation rule or association of this rs contract
    */
    @Override
    public void setEvaluationRuleOrAssociation(
        java.lang.String evaluationRuleOrAssociation) {
        _rsContract.setEvaluationRuleOrAssociation(evaluationRuleOrAssociation);
    }

    /**
    * Returns the deduction inclusion of this rs contract.
    *
    * @return the deduction inclusion of this rs contract
    */
    @Override
    public java.lang.String getDeductionInclusion() {
        return _rsContract.getDeductionInclusion();
    }

    /**
    * Sets the deduction inclusion of this rs contract.
    *
    * @param deductionInclusion the deduction inclusion of this rs contract
    */
    @Override
    public void setDeductionInclusion(java.lang.String deductionInclusion) {
        _rsContract.setDeductionInclusion(deductionInclusion);
    }

    @Override
    public boolean isNew() {
        return _rsContract.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _rsContract.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _rsContract.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _rsContract.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _rsContract.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _rsContract.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _rsContract.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _rsContract.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _rsContract.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _rsContract.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _rsContract.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new RsContractWrapper((RsContract) _rsContract.clone());
    }

    @Override
    public int compareTo(RsContract rsContract) {
        return _rsContract.compareTo(rsContract);
    }

    @Override
    public int hashCode() {
        return _rsContract.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<RsContract> toCacheModel() {
        return _rsContract.toCacheModel();
    }

    @Override
    public RsContract toEscapedModel() {
        return new RsContractWrapper(_rsContract.toEscapedModel());
    }

    @Override
    public RsContract toUnescapedModel() {
        return new RsContractWrapper(_rsContract.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _rsContract.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _rsContract.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _rsContract.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof RsContractWrapper)) {
            return false;
        }

        RsContractWrapper rsContractWrapper = (RsContractWrapper) obj;

        if (Validator.equals(_rsContract, rsContractWrapper._rsContract)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public RsContract getWrappedRsContract() {
        return _rsContract;
    }

    @Override
    public RsContract getWrappedModel() {
        return _rsContract;
    }

    @Override
    public void resetOriginalValues() {
        _rsContract.resetOriginalValues();
    }
}
