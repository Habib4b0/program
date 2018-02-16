/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.stpl.app.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link RsContract}.
 * </p>
 *
 * @author
 * @see RsContract
 * @generated
 */
@ProviderType
public class RsContractWrapper implements RsContract, ModelWrapper<RsContract> {
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
		String cfpContractSid = (String)attributes.get("cfpContractSid");

		if (cfpContractSid != null) {
			setCfpContractSid(cfpContractSid);
		}

		Date createdDate = (Date)attributes.get("createdDate");

		if (createdDate != null) {
			setCreatedDate(createdDate);
		}

		String psContractSid = (String)attributes.get("psContractSid");

		if (psContractSid != null) {
			setPsContractSid(psContractSid);
		}

		String rsName = (String)attributes.get("rsName");

		if (rsName != null) {
			setRsName(rsName);
		}

		Integer rsStatus = (Integer)attributes.get("rsStatus");

		if (rsStatus != null) {
			setRsStatus(rsStatus);
		}

		Date rsStartDate = (Date)attributes.get("rsStartDate");

		if (rsStartDate != null) {
			setRsStartDate(rsStartDate);
		}

		String rsTransRefId = (String)attributes.get("rsTransRefId");

		if (rsTransRefId != null) {
			setRsTransRefId(rsTransRefId);
		}

		String makePayableTo = (String)attributes.get("makePayableTo");

		if (makePayableTo != null) {
			setMakePayableTo(makePayableTo);
		}

		Integer createdBy = (Integer)attributes.get("createdBy");

		if (createdBy != null) {
			setCreatedBy(createdBy);
		}

		Integer rsCategory = (Integer)attributes.get("rsCategory");

		if (rsCategory != null) {
			setRsCategory(rsCategory);
		}

		Integer rsTradeClass = (Integer)attributes.get("rsTradeClass");

		if (rsTradeClass != null) {
			setRsTradeClass(rsTradeClass);
		}

		Integer contractMasterSid = (Integer)attributes.get("contractMasterSid");

		if (contractMasterSid != null) {
			setContractMasterSid(contractMasterSid);
		}

		Integer rebateRuleType = (Integer)attributes.get("rebateRuleType");

		if (rebateRuleType != null) {
			setRebateRuleType(rebateRuleType);
		}

		Integer paymentMethod = (Integer)attributes.get("paymentMethod");

		if (paymentMethod != null) {
			setPaymentMethod(paymentMethod);
		}

		Date rsContractAttachedDate = (Date)attributes.get(
				"rsContractAttachedDate");

		if (rsContractAttachedDate != null) {
			setRsContractAttachedDate(rsContractAttachedDate);
		}

		String rsAlias = (String)attributes.get("rsAlias");

		if (rsAlias != null) {
			setRsAlias(rsAlias);
		}

		String formulaMethodId = (String)attributes.get("formulaMethodId");

		if (formulaMethodId != null) {
			setFormulaMethodId(formulaMethodId);
		}

		Integer rebateProcessingType = (Integer)attributes.get(
				"rebateProcessingType");

		if (rebateProcessingType != null) {
			setRebateProcessingType(rebateProcessingType);
		}

		Integer rsContractAttachedStatus = (Integer)attributes.get(
				"rsContractAttachedStatus");

		if (rsContractAttachedStatus != null) {
			setRsContractAttachedStatus(rsContractAttachedStatus);
		}

		Integer interestBearingBasis = (Integer)attributes.get(
				"interestBearingBasis");

		if (interestBearingBasis != null) {
			setInterestBearingBasis(interestBearingBasis);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String rsTransRefName = (String)attributes.get("rsTransRefName");

		if (rsTransRefName != null) {
			setRsTransRefName(rsTransRefName);
		}

		Integer rebateProgramType = (Integer)attributes.get("rebateProgramType");

		if (rebateProgramType != null) {
			setRebateProgramType(rebateProgramType);
		}

		String rebatePlanLevel = (String)attributes.get("rebatePlanLevel");

		if (rebatePlanLevel != null) {
			setRebatePlanLevel(rebatePlanLevel);
		}

		String source = (String)attributes.get("source");

		if (source != null) {
			setSource(source);
		}

		String rsCalendar = (String)attributes.get("rsCalendar");

		if (rsCalendar != null) {
			setRsCalendar(rsCalendar);
		}

		Integer rsType = (Integer)attributes.get("rsType");

		if (rsType != null) {
			setRsType(rsType);
		}

		String address1 = (String)attributes.get("address1");

		if (address1 != null) {
			setAddress1(address1);
		}

		String address2 = (String)attributes.get("address2");

		if (address2 != null) {
			setAddress2(address2);
		}

		Date rsEndDate = (Date)attributes.get("rsEndDate");

		if (rsEndDate != null) {
			setRsEndDate(rsEndDate);
		}

		Integer modifiedBy = (Integer)attributes.get("modifiedBy");

		if (modifiedBy != null) {
			setModifiedBy(modifiedBy);
		}

		String rsTransRefNo = (String)attributes.get("rsTransRefNo");

		if (rsTransRefNo != null) {
			setRsTransRefNo(rsTransRefNo);
		}

		String zipCode = (String)attributes.get("zipCode");

		if (zipCode != null) {
			setZipCode(zipCode);
		}

		String rebateRuleAssociation = (String)attributes.get(
				"rebateRuleAssociation");

		if (rebateRuleAssociation != null) {
			setRebateRuleAssociation(rebateRuleAssociation);
		}

		Integer state = (Integer)attributes.get("state");

		if (state != null) {
			setState(state);
		}

		Integer rebateFrequency = (Integer)attributes.get("rebateFrequency");

		if (rebateFrequency != null) {
			setRebateFrequency(rebateFrequency);
		}

		String rsDesignation = (String)attributes.get("rsDesignation");

		if (rsDesignation != null) {
			setRsDesignation(rsDesignation);
		}

		String batchId = (String)attributes.get("batchId");

		if (batchId != null) {
			setBatchId(batchId);
		}

		String ifpContractSid = (String)attributes.get("ifpContractSid");

		if (ifpContractSid != null) {
			setIfpContractSid(ifpContractSid);
		}

		Integer rsContractSid = (Integer)attributes.get("rsContractSid");

		if (rsContractSid != null) {
			setRsContractSid(rsContractSid);
		}

		Integer paymentTerms = (Integer)attributes.get("paymentTerms");

		if (paymentTerms != null) {
			setPaymentTerms(paymentTerms);
		}

		String rsNo = (String)attributes.get("rsNo");

		if (rsNo != null) {
			setRsNo(rsNo);
		}

		Integer rsModelSid = (Integer)attributes.get("rsModelSid");

		if (rsModelSid != null) {
			setRsModelSid(rsModelSid);
		}

		Integer rsValidationProfile = (Integer)attributes.get(
				"rsValidationProfile");

		if (rsValidationProfile != null) {
			setRsValidationProfile(rsValidationProfile);
		}

		String paymentGracePeriod = (String)attributes.get("paymentGracePeriod");

		if (paymentGracePeriod != null) {
			setPaymentGracePeriod(paymentGracePeriod);
		}

		Integer paymentFrequency = (Integer)attributes.get("paymentFrequency");

		if (paymentFrequency != null) {
			setPaymentFrequency(paymentFrequency);
		}

		Boolean recordLockStatus = (Boolean)attributes.get("recordLockStatus");

		if (recordLockStatus != null) {
			setRecordLockStatus(recordLockStatus);
		}

		String rsId = (String)attributes.get("rsId");

		if (rsId != null) {
			setRsId(rsId);
		}

		String city = (String)attributes.get("city");

		if (city != null) {
			setCity(city);
		}

		String parentRsName = (String)attributes.get("parentRsName");

		if (parentRsName != null) {
			setParentRsName(parentRsName);
		}

		Integer interestBearingIndicator = (Integer)attributes.get(
				"interestBearingIndicator");

		if (interestBearingIndicator != null) {
			setInterestBearingIndicator(interestBearingIndicator);
		}

		String parentRsId = (String)attributes.get("parentRsId");

		if (parentRsId != null) {
			setParentRsId(parentRsId);
		}

		String inboundStatus = (String)attributes.get("inboundStatus");

		if (inboundStatus != null) {
			setInboundStatus(inboundStatus);
		}

		Integer calculationType = (Integer)attributes.get("calculationType");

		if (calculationType != null) {
			setCalculationType(calculationType);
		}

		Integer calculationLevel = (Integer)attributes.get("calculationLevel");

		if (calculationLevel != null) {
			setCalculationLevel(calculationLevel);
		}

		String calculationRule = (String)attributes.get("calculationRule");

		if (calculationRule != null) {
			setCalculationRule(calculationRule);
		}

		String calculationRuleLevel = (String)attributes.get(
				"calculationRuleLevel");

		if (calculationRuleLevel != null) {
			setCalculationRuleLevel(calculationRuleLevel);
		}

		String evaluationRuleType = (String)attributes.get("evaluationRuleType");

		if (evaluationRuleType != null) {
			setEvaluationRuleType(evaluationRuleType);
		}

		String evaluationRuleLevel = (String)attributes.get(
				"evaluationRuleLevel");

		if (evaluationRuleLevel != null) {
			setEvaluationRuleLevel(evaluationRuleLevel);
		}

		String evaluationRuleOrAssociation = (String)attributes.get(
				"evaluationRuleOrAssociation");

		if (evaluationRuleOrAssociation != null) {
			setEvaluationRuleOrAssociation(evaluationRuleOrAssociation);
		}

		String deductionInclusion = (String)attributes.get("deductionInclusion");

		if (deductionInclusion != null) {
			setDeductionInclusion(deductionInclusion);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new RsContractWrapper((RsContract)_rsContract.clone());
	}

	@Override
	public int compareTo(RsContract rsContract) {
		return _rsContract.compareTo(rsContract);
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
	* Returns the address2 of this rs contract.
	*
	* @return the address2 of this rs contract
	*/
	@Override
	public java.lang.String getAddress2() {
		return _rsContract.getAddress2();
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
	* Returns the calculation level of this rs contract.
	*
	* @return the calculation level of this rs contract
	*/
	@Override
	public int getCalculationLevel() {
		return _rsContract.getCalculationLevel();
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
	* Returns the calculation rule level of this rs contract.
	*
	* @return the calculation rule level of this rs contract
	*/
	@Override
	public java.lang.String getCalculationRuleLevel() {
		return _rsContract.getCalculationRuleLevel();
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
	* Returns the cfp contract sid of this rs contract.
	*
	* @return the cfp contract sid of this rs contract
	*/
	@Override
	public java.lang.String getCfpContractSid() {
		return _rsContract.getCfpContractSid();
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
	* Returns the contract master sid of this rs contract.
	*
	* @return the contract master sid of this rs contract
	*/
	@Override
	public int getContractMasterSid() {
		return _rsContract.getContractMasterSid();
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
	* Returns the created date of this rs contract.
	*
	* @return the created date of this rs contract
	*/
	@Override
	public Date getCreatedDate() {
		return _rsContract.getCreatedDate();
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
	* Returns the evaluation rule level of this rs contract.
	*
	* @return the evaluation rule level of this rs contract
	*/
	@Override
	public java.lang.String getEvaluationRuleLevel() {
		return _rsContract.getEvaluationRuleLevel();
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
	* Returns the evaluation rule type of this rs contract.
	*
	* @return the evaluation rule type of this rs contract
	*/
	@Override
	public java.lang.String getEvaluationRuleType() {
		return _rsContract.getEvaluationRuleType();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _rsContract.getExpandoBridge();
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
	* Returns the ifp contract sid of this rs contract.
	*
	* @return the ifp contract sid of this rs contract
	*/
	@Override
	public java.lang.String getIfpContractSid() {
		return _rsContract.getIfpContractSid();
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
	* Returns the interest bearing basis of this rs contract.
	*
	* @return the interest bearing basis of this rs contract
	*/
	@Override
	public int getInterestBearingBasis() {
		return _rsContract.getInterestBearingBasis();
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
	* Returns the make payable to of this rs contract.
	*
	* @return the make payable to of this rs contract
	*/
	@Override
	public java.lang.String getMakePayableTo() {
		return _rsContract.getMakePayableTo();
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
	* Returns the modified date of this rs contract.
	*
	* @return the modified date of this rs contract
	*/
	@Override
	public Date getModifiedDate() {
		return _rsContract.getModifiedDate();
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
	* Returns the parent rs name of this rs contract.
	*
	* @return the parent rs name of this rs contract
	*/
	@Override
	public java.lang.String getParentRsName() {
		return _rsContract.getParentRsName();
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
	* Returns the payment grace period of this rs contract.
	*
	* @return the payment grace period of this rs contract
	*/
	@Override
	public java.lang.String getPaymentGracePeriod() {
		return _rsContract.getPaymentGracePeriod();
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
	* Returns the payment terms of this rs contract.
	*
	* @return the payment terms of this rs contract
	*/
	@Override
	public int getPaymentTerms() {
		return _rsContract.getPaymentTerms();
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

	@Override
	public Serializable getPrimaryKeyObj() {
		return _rsContract.getPrimaryKeyObj();
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
	* Returns the rebate frequency of this rs contract.
	*
	* @return the rebate frequency of this rs contract
	*/
	@Override
	public int getRebateFrequency() {
		return _rsContract.getRebateFrequency();
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
	* Returns the rebate processing type of this rs contract.
	*
	* @return the rebate processing type of this rs contract
	*/
	@Override
	public int getRebateProcessingType() {
		return _rsContract.getRebateProcessingType();
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
	* Returns the rebate rule association of this rs contract.
	*
	* @return the rebate rule association of this rs contract
	*/
	@Override
	public java.lang.String getRebateRuleAssociation() {
		return _rsContract.getRebateRuleAssociation();
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
	* Returns the record lock status of this rs contract.
	*
	* @return the record lock status of this rs contract
	*/
	@Override
	public boolean getRecordLockStatus() {
		return _rsContract.getRecordLockStatus();
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
	* Returns the rs calendar of this rs contract.
	*
	* @return the rs calendar of this rs contract
	*/
	@Override
	public java.lang.String getRsCalendar() {
		return _rsContract.getRsCalendar();
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
	* Returns the rs contract attached date of this rs contract.
	*
	* @return the rs contract attached date of this rs contract
	*/
	@Override
	public Date getRsContractAttachedDate() {
		return _rsContract.getRsContractAttachedDate();
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
	* Returns the rs contract sid of this rs contract.
	*
	* @return the rs contract sid of this rs contract
	*/
	@Override
	public int getRsContractSid() {
		return _rsContract.getRsContractSid();
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
	* Returns the rs end date of this rs contract.
	*
	* @return the rs end date of this rs contract
	*/
	@Override
	public Date getRsEndDate() {
		return _rsContract.getRsEndDate();
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
	* Returns the rs model sid of this rs contract.
	*
	* @return the rs model sid of this rs contract
	*/
	@Override
	public int getRsModelSid() {
		return _rsContract.getRsModelSid();
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
	* Returns the rs no of this rs contract.
	*
	* @return the rs no of this rs contract
	*/
	@Override
	public java.lang.String getRsNo() {
		return _rsContract.getRsNo();
	}

	/**
	* Returns the rs start date of this rs contract.
	*
	* @return the rs start date of this rs contract
	*/
	@Override
	public Date getRsStartDate() {
		return _rsContract.getRsStartDate();
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
	* Returns the rs trade class of this rs contract.
	*
	* @return the rs trade class of this rs contract
	*/
	@Override
	public int getRsTradeClass() {
		return _rsContract.getRsTradeClass();
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
	* Returns the rs trans ref name of this rs contract.
	*
	* @return the rs trans ref name of this rs contract
	*/
	@Override
	public java.lang.String getRsTransRefName() {
		return _rsContract.getRsTransRefName();
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
	* Returns the rs type of this rs contract.
	*
	* @return the rs type of this rs contract
	*/
	@Override
	public int getRsType() {
		return _rsContract.getRsType();
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
	* Returns the source of this rs contract.
	*
	* @return the source of this rs contract
	*/
	@Override
	public java.lang.String getSource() {
		return _rsContract.getSource();
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
	* Returns the zip code of this rs contract.
	*
	* @return the zip code of this rs contract
	*/
	@Override
	public java.lang.String getZipCode() {
		return _rsContract.getZipCode();
	}

	@Override
	public int hashCode() {
		return _rsContract.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _rsContract.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _rsContract.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _rsContract.isNew();
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

	@Override
	public void persist() {
		_rsContract.persist();
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
	* Sets the address2 of this rs contract.
	*
	* @param address2 the address2 of this rs contract
	*/
	@Override
	public void setAddress2(java.lang.String address2) {
		_rsContract.setAddress2(address2);
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

	@Override
	public void setCachedModel(boolean cachedModel) {
		_rsContract.setCachedModel(cachedModel);
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
	* Sets the calculation rule of this rs contract.
	*
	* @param calculationRule the calculation rule of this rs contract
	*/
	@Override
	public void setCalculationRule(java.lang.String calculationRule) {
		_rsContract.setCalculationRule(calculationRule);
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
	* Sets the calculation type of this rs contract.
	*
	* @param calculationType the calculation type of this rs contract
	*/
	@Override
	public void setCalculationType(int calculationType) {
		_rsContract.setCalculationType(calculationType);
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
	* Sets the city of this rs contract.
	*
	* @param city the city of this rs contract
	*/
	@Override
	public void setCity(java.lang.String city) {
		_rsContract.setCity(city);
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
	* Sets the created by of this rs contract.
	*
	* @param createdBy the created by of this rs contract
	*/
	@Override
	public void setCreatedBy(int createdBy) {
		_rsContract.setCreatedBy(createdBy);
	}

	/**
	* Sets the created date of this rs contract.
	*
	* @param createdDate the created date of this rs contract
	*/
	@Override
	public void setCreatedDate(Date createdDate) {
		_rsContract.setCreatedDate(createdDate);
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
	* Sets the evaluation rule type of this rs contract.
	*
	* @param evaluationRuleType the evaluation rule type of this rs contract
	*/
	@Override
	public void setEvaluationRuleType(java.lang.String evaluationRuleType) {
		_rsContract.setEvaluationRuleType(evaluationRuleType);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_rsContract.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_rsContract.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_rsContract.setExpandoBridgeAttributes(serviceContext);
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
	* Sets the ifp contract sid of this rs contract.
	*
	* @param ifpContractSid the ifp contract sid of this rs contract
	*/
	@Override
	public void setIfpContractSid(java.lang.String ifpContractSid) {
		_rsContract.setIfpContractSid(ifpContractSid);
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
	* Sets the interest bearing basis of this rs contract.
	*
	* @param interestBearingBasis the interest bearing basis of this rs contract
	*/
	@Override
	public void setInterestBearingBasis(int interestBearingBasis) {
		_rsContract.setInterestBearingBasis(interestBearingBasis);
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
	* Sets the make payable to of this rs contract.
	*
	* @param makePayableTo the make payable to of this rs contract
	*/
	@Override
	public void setMakePayableTo(java.lang.String makePayableTo) {
		_rsContract.setMakePayableTo(makePayableTo);
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
	* Sets the modified date of this rs contract.
	*
	* @param modifiedDate the modified date of this rs contract
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_rsContract.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_rsContract.setNew(n);
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
	* Sets the parent rs name of this rs contract.
	*
	* @param parentRsName the parent rs name of this rs contract
	*/
	@Override
	public void setParentRsName(java.lang.String parentRsName) {
		_rsContract.setParentRsName(parentRsName);
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
	* Sets the payment grace period of this rs contract.
	*
	* @param paymentGracePeriod the payment grace period of this rs contract
	*/
	@Override
	public void setPaymentGracePeriod(java.lang.String paymentGracePeriod) {
		_rsContract.setPaymentGracePeriod(paymentGracePeriod);
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
	* Sets the payment terms of this rs contract.
	*
	* @param paymentTerms the payment terms of this rs contract
	*/
	@Override
	public void setPaymentTerms(int paymentTerms) {
		_rsContract.setPaymentTerms(paymentTerms);
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

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_rsContract.setPrimaryKeyObj(primaryKeyObj);
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
	* Sets the rebate frequency of this rs contract.
	*
	* @param rebateFrequency the rebate frequency of this rs contract
	*/
	@Override
	public void setRebateFrequency(int rebateFrequency) {
		_rsContract.setRebateFrequency(rebateFrequency);
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
	* Sets the rebate processing type of this rs contract.
	*
	* @param rebateProcessingType the rebate processing type of this rs contract
	*/
	@Override
	public void setRebateProcessingType(int rebateProcessingType) {
		_rsContract.setRebateProcessingType(rebateProcessingType);
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
	* Sets the rebate rule association of this rs contract.
	*
	* @param rebateRuleAssociation the rebate rule association of this rs contract
	*/
	@Override
	public void setRebateRuleAssociation(java.lang.String rebateRuleAssociation) {
		_rsContract.setRebateRuleAssociation(rebateRuleAssociation);
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
	* Sets whether this rs contract is record lock status.
	*
	* @param recordLockStatus the record lock status of this rs contract
	*/
	@Override
	public void setRecordLockStatus(boolean recordLockStatus) {
		_rsContract.setRecordLockStatus(recordLockStatus);
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
	* Sets the rs calendar of this rs contract.
	*
	* @param rsCalendar the rs calendar of this rs contract
	*/
	@Override
	public void setRsCalendar(java.lang.String rsCalendar) {
		_rsContract.setRsCalendar(rsCalendar);
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
	* Sets the rs contract attached date of this rs contract.
	*
	* @param rsContractAttachedDate the rs contract attached date of this rs contract
	*/
	@Override
	public void setRsContractAttachedDate(Date rsContractAttachedDate) {
		_rsContract.setRsContractAttachedDate(rsContractAttachedDate);
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
	* Sets the rs contract sid of this rs contract.
	*
	* @param rsContractSid the rs contract sid of this rs contract
	*/
	@Override
	public void setRsContractSid(int rsContractSid) {
		_rsContract.setRsContractSid(rsContractSid);
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
	* Sets the rs end date of this rs contract.
	*
	* @param rsEndDate the rs end date of this rs contract
	*/
	@Override
	public void setRsEndDate(Date rsEndDate) {
		_rsContract.setRsEndDate(rsEndDate);
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
	* Sets the rs model sid of this rs contract.
	*
	* @param rsModelSid the rs model sid of this rs contract
	*/
	@Override
	public void setRsModelSid(int rsModelSid) {
		_rsContract.setRsModelSid(rsModelSid);
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
	* Sets the rs no of this rs contract.
	*
	* @param rsNo the rs no of this rs contract
	*/
	@Override
	public void setRsNo(java.lang.String rsNo) {
		_rsContract.setRsNo(rsNo);
	}

	/**
	* Sets the rs start date of this rs contract.
	*
	* @param rsStartDate the rs start date of this rs contract
	*/
	@Override
	public void setRsStartDate(Date rsStartDate) {
		_rsContract.setRsStartDate(rsStartDate);
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
	* Sets the rs trade class of this rs contract.
	*
	* @param rsTradeClass the rs trade class of this rs contract
	*/
	@Override
	public void setRsTradeClass(int rsTradeClass) {
		_rsContract.setRsTradeClass(rsTradeClass);
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
	* Sets the rs trans ref name of this rs contract.
	*
	* @param rsTransRefName the rs trans ref name of this rs contract
	*/
	@Override
	public void setRsTransRefName(java.lang.String rsTransRefName) {
		_rsContract.setRsTransRefName(rsTransRefName);
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
	* Sets the rs type of this rs contract.
	*
	* @param rsType the rs type of this rs contract
	*/
	@Override
	public void setRsType(int rsType) {
		_rsContract.setRsType(rsType);
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
	* Sets the source of this rs contract.
	*
	* @param source the source of this rs contract
	*/
	@Override
	public void setSource(java.lang.String source) {
		_rsContract.setSource(source);
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
	* Sets the zip code of this rs contract.
	*
	* @param zipCode the zip code of this rs contract
	*/
	@Override
	public void setZipCode(java.lang.String zipCode) {
		_rsContract.setZipCode(zipCode);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<RsContract> toCacheModel() {
		return _rsContract.toCacheModel();
	}

	@Override
	public RsContract toEscapedModel() {
		return new RsContractWrapper(_rsContract.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _rsContract.toString();
	}

	@Override
	public RsContract toUnescapedModel() {
		return new RsContractWrapper(_rsContract.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _rsContract.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof RsContractWrapper)) {
			return false;
		}

		RsContractWrapper rsContractWrapper = (RsContractWrapper)obj;

		if (Objects.equals(_rsContract, rsContractWrapper._rsContract)) {
			return true;
		}

		return false;
	}

	@Override
	public RsContract getWrappedModel() {
		return _rsContract;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _rsContract.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _rsContract.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_rsContract.resetOriginalValues();
	}

	private final RsContract _rsContract;
}