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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author
 * @generated
 */
@ProviderType
public class RsContractSoap implements Serializable {
	public static RsContractSoap toSoapModel(RsContract model) {
		RsContractSoap soapModel = new RsContractSoap();

		soapModel.setCfpContractSid(model.getCfpContractSid());
		soapModel.setCreatedDate(model.getCreatedDate());
		soapModel.setPsContractSid(model.getPsContractSid());
		soapModel.setRsName(model.getRsName());
		soapModel.setRsStatus(model.getRsStatus());
		soapModel.setRsStartDate(model.getRsStartDate());
		soapModel.setRsTransRefId(model.getRsTransRefId());
		soapModel.setMakePayableTo(model.getMakePayableTo());
		soapModel.setCreatedBy(model.getCreatedBy());
		soapModel.setRsCategory(model.getRsCategory());
		soapModel.setRsTradeClass(model.getRsTradeClass());
		soapModel.setContractMasterSid(model.getContractMasterSid());
		soapModel.setRebateRuleType(model.getRebateRuleType());
		soapModel.setPaymentMethod(model.getPaymentMethod());
		soapModel.setRsContractAttachedDate(model.getRsContractAttachedDate());
		soapModel.setRsAlias(model.getRsAlias());
		soapModel.setFormulaMethodId(model.getFormulaMethodId());
		soapModel.setRebateProcessingType(model.getRebateProcessingType());
		soapModel.setRsContractAttachedStatus(model.getRsContractAttachedStatus());
		soapModel.setInterestBearingBasis(model.getInterestBearingBasis());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setRsTransRefName(model.getRsTransRefName());
		soapModel.setRebateProgramType(model.getRebateProgramType());
		soapModel.setRebatePlanLevel(model.getRebatePlanLevel());
		soapModel.setSource(model.getSource());
		soapModel.setRsCalendar(model.getRsCalendar());
		soapModel.setRsType(model.getRsType());
		soapModel.setAddress1(model.getAddress1());
		soapModel.setAddress2(model.getAddress2());
		soapModel.setRsEndDate(model.getRsEndDate());
		soapModel.setModifiedBy(model.getModifiedBy());
		soapModel.setRsTransRefNo(model.getRsTransRefNo());
		soapModel.setZipCode(model.getZipCode());
		soapModel.setRebateRuleAssociation(model.getRebateRuleAssociation());
		soapModel.setState(model.getState());
		soapModel.setRebateFrequency(model.getRebateFrequency());
		soapModel.setRsDesignation(model.getRsDesignation());
		soapModel.setBatchId(model.getBatchId());
		soapModel.setIfpContractSid(model.getIfpContractSid());
		soapModel.setRsContractSid(model.getRsContractSid());
		soapModel.setPaymentTerms(model.getPaymentTerms());
		soapModel.setRsNo(model.getRsNo());
		soapModel.setRsModelSid(model.getRsModelSid());
		soapModel.setRsValidationProfile(model.getRsValidationProfile());
		soapModel.setPaymentGracePeriod(model.getPaymentGracePeriod());
		soapModel.setPaymentFrequency(model.getPaymentFrequency());
		soapModel.setRecordLockStatus(model.getRecordLockStatus());
		soapModel.setRsId(model.getRsId());
		soapModel.setCity(model.getCity());
		soapModel.setParentRsName(model.getParentRsName());
		soapModel.setInterestBearingIndicator(model.getInterestBearingIndicator());
		soapModel.setParentRsId(model.getParentRsId());
		soapModel.setInboundStatus(model.getInboundStatus());
		soapModel.setCalculationType(model.getCalculationType());
		soapModel.setCalculationLevel(model.getCalculationLevel());
		soapModel.setCalculationRule(model.getCalculationRule());
		soapModel.setCalculationRuleLevel(model.getCalculationRuleLevel());
		soapModel.setEvaluationRuleType(model.getEvaluationRuleType());
		soapModel.setEvaluationRuleLevel(model.getEvaluationRuleLevel());
		soapModel.setEvaluationRuleOrAssociation(model.getEvaluationRuleOrAssociation());
		soapModel.setDeductionInclusion(model.getDeductionInclusion());

		return soapModel;
	}

	public static RsContractSoap[] toSoapModels(RsContract[] models) {
		RsContractSoap[] soapModels = new RsContractSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static RsContractSoap[][] toSoapModels(RsContract[][] models) {
		RsContractSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new RsContractSoap[models.length][models[0].length];
		}
		else {
			soapModels = new RsContractSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static RsContractSoap[] toSoapModels(List<RsContract> models) {
		List<RsContractSoap> soapModels = new ArrayList<RsContractSoap>(models.size());

		for (RsContract model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new RsContractSoap[soapModels.size()]);
	}

	public RsContractSoap() {
	}

	public int getPrimaryKey() {
		return _rsContractSid;
	}

	public void setPrimaryKey(int pk) {
		setRsContractSid(pk);
	}

	public String getCfpContractSid() {
		return _cfpContractSid;
	}

	public void setCfpContractSid(String cfpContractSid) {
		_cfpContractSid = cfpContractSid;
	}

	public Date getCreatedDate() {
		return _createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		_createdDate = createdDate;
	}

	public String getPsContractSid() {
		return _psContractSid;
	}

	public void setPsContractSid(String psContractSid) {
		_psContractSid = psContractSid;
	}

	public String getRsName() {
		return _rsName;
	}

	public void setRsName(String rsName) {
		_rsName = rsName;
	}

	public int getRsStatus() {
		return _rsStatus;
	}

	public void setRsStatus(int rsStatus) {
		_rsStatus = rsStatus;
	}

	public Date getRsStartDate() {
		return _rsStartDate;
	}

	public void setRsStartDate(Date rsStartDate) {
		_rsStartDate = rsStartDate;
	}

	public String getRsTransRefId() {
		return _rsTransRefId;
	}

	public void setRsTransRefId(String rsTransRefId) {
		_rsTransRefId = rsTransRefId;
	}

	public String getMakePayableTo() {
		return _makePayableTo;
	}

	public void setMakePayableTo(String makePayableTo) {
		_makePayableTo = makePayableTo;
	}

	public int getCreatedBy() {
		return _createdBy;
	}

	public void setCreatedBy(int createdBy) {
		_createdBy = createdBy;
	}

	public int getRsCategory() {
		return _rsCategory;
	}

	public void setRsCategory(int rsCategory) {
		_rsCategory = rsCategory;
	}

	public int getRsTradeClass() {
		return _rsTradeClass;
	}

	public void setRsTradeClass(int rsTradeClass) {
		_rsTradeClass = rsTradeClass;
	}

	public int getContractMasterSid() {
		return _contractMasterSid;
	}

	public void setContractMasterSid(int contractMasterSid) {
		_contractMasterSid = contractMasterSid;
	}

	public int getRebateRuleType() {
		return _rebateRuleType;
	}

	public void setRebateRuleType(int rebateRuleType) {
		_rebateRuleType = rebateRuleType;
	}

	public int getPaymentMethod() {
		return _paymentMethod;
	}

	public void setPaymentMethod(int paymentMethod) {
		_paymentMethod = paymentMethod;
	}

	public Date getRsContractAttachedDate() {
		return _rsContractAttachedDate;
	}

	public void setRsContractAttachedDate(Date rsContractAttachedDate) {
		_rsContractAttachedDate = rsContractAttachedDate;
	}

	public String getRsAlias() {
		return _rsAlias;
	}

	public void setRsAlias(String rsAlias) {
		_rsAlias = rsAlias;
	}

	public String getFormulaMethodId() {
		return _formulaMethodId;
	}

	public void setFormulaMethodId(String formulaMethodId) {
		_formulaMethodId = formulaMethodId;
	}

	public int getRebateProcessingType() {
		return _rebateProcessingType;
	}

	public void setRebateProcessingType(int rebateProcessingType) {
		_rebateProcessingType = rebateProcessingType;
	}

	public int getRsContractAttachedStatus() {
		return _rsContractAttachedStatus;
	}

	public void setRsContractAttachedStatus(int rsContractAttachedStatus) {
		_rsContractAttachedStatus = rsContractAttachedStatus;
	}

	public int getInterestBearingBasis() {
		return _interestBearingBasis;
	}

	public void setInterestBearingBasis(int interestBearingBasis) {
		_interestBearingBasis = interestBearingBasis;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public String getRsTransRefName() {
		return _rsTransRefName;
	}

	public void setRsTransRefName(String rsTransRefName) {
		_rsTransRefName = rsTransRefName;
	}

	public int getRebateProgramType() {
		return _rebateProgramType;
	}

	public void setRebateProgramType(int rebateProgramType) {
		_rebateProgramType = rebateProgramType;
	}

	public String getRebatePlanLevel() {
		return _rebatePlanLevel;
	}

	public void setRebatePlanLevel(String rebatePlanLevel) {
		_rebatePlanLevel = rebatePlanLevel;
	}

	public String getSource() {
		return _source;
	}

	public void setSource(String source) {
		_source = source;
	}

	public String getRsCalendar() {
		return _rsCalendar;
	}

	public void setRsCalendar(String rsCalendar) {
		_rsCalendar = rsCalendar;
	}

	public int getRsType() {
		return _rsType;
	}

	public void setRsType(int rsType) {
		_rsType = rsType;
	}

	public String getAddress1() {
		return _address1;
	}

	public void setAddress1(String address1) {
		_address1 = address1;
	}

	public String getAddress2() {
		return _address2;
	}

	public void setAddress2(String address2) {
		_address2 = address2;
	}

	public Date getRsEndDate() {
		return _rsEndDate;
	}

	public void setRsEndDate(Date rsEndDate) {
		_rsEndDate = rsEndDate;
	}

	public int getModifiedBy() {
		return _modifiedBy;
	}

	public void setModifiedBy(int modifiedBy) {
		_modifiedBy = modifiedBy;
	}

	public String getRsTransRefNo() {
		return _rsTransRefNo;
	}

	public void setRsTransRefNo(String rsTransRefNo) {
		_rsTransRefNo = rsTransRefNo;
	}

	public String getZipCode() {
		return _zipCode;
	}

	public void setZipCode(String zipCode) {
		_zipCode = zipCode;
	}

	public String getRebateRuleAssociation() {
		return _rebateRuleAssociation;
	}

	public void setRebateRuleAssociation(String rebateRuleAssociation) {
		_rebateRuleAssociation = rebateRuleAssociation;
	}

	public int getState() {
		return _state;
	}

	public void setState(int state) {
		_state = state;
	}

	public int getRebateFrequency() {
		return _rebateFrequency;
	}

	public void setRebateFrequency(int rebateFrequency) {
		_rebateFrequency = rebateFrequency;
	}

	public String getRsDesignation() {
		return _rsDesignation;
	}

	public void setRsDesignation(String rsDesignation) {
		_rsDesignation = rsDesignation;
	}

	public String getBatchId() {
		return _batchId;
	}

	public void setBatchId(String batchId) {
		_batchId = batchId;
	}

	public String getIfpContractSid() {
		return _ifpContractSid;
	}

	public void setIfpContractSid(String ifpContractSid) {
		_ifpContractSid = ifpContractSid;
	}

	public int getRsContractSid() {
		return _rsContractSid;
	}

	public void setRsContractSid(int rsContractSid) {
		_rsContractSid = rsContractSid;
	}

	public int getPaymentTerms() {
		return _paymentTerms;
	}

	public void setPaymentTerms(int paymentTerms) {
		_paymentTerms = paymentTerms;
	}

	public String getRsNo() {
		return _rsNo;
	}

	public void setRsNo(String rsNo) {
		_rsNo = rsNo;
	}

	public int getRsModelSid() {
		return _rsModelSid;
	}

	public void setRsModelSid(int rsModelSid) {
		_rsModelSid = rsModelSid;
	}

	public int getRsValidationProfile() {
		return _rsValidationProfile;
	}

	public void setRsValidationProfile(int rsValidationProfile) {
		_rsValidationProfile = rsValidationProfile;
	}

	public String getPaymentGracePeriod() {
		return _paymentGracePeriod;
	}

	public void setPaymentGracePeriod(String paymentGracePeriod) {
		_paymentGracePeriod = paymentGracePeriod;
	}

	public int getPaymentFrequency() {
		return _paymentFrequency;
	}

	public void setPaymentFrequency(int paymentFrequency) {
		_paymentFrequency = paymentFrequency;
	}

	public boolean getRecordLockStatus() {
		return _recordLockStatus;
	}

	public boolean isRecordLockStatus() {
		return _recordLockStatus;
	}

	public void setRecordLockStatus(boolean recordLockStatus) {
		_recordLockStatus = recordLockStatus;
	}

	public String getRsId() {
		return _rsId;
	}

	public void setRsId(String rsId) {
		_rsId = rsId;
	}

	public String getCity() {
		return _city;
	}

	public void setCity(String city) {
		_city = city;
	}

	public String getParentRsName() {
		return _parentRsName;
	}

	public void setParentRsName(String parentRsName) {
		_parentRsName = parentRsName;
	}

	public int getInterestBearingIndicator() {
		return _interestBearingIndicator;
	}

	public void setInterestBearingIndicator(int interestBearingIndicator) {
		_interestBearingIndicator = interestBearingIndicator;
	}

	public String getParentRsId() {
		return _parentRsId;
	}

	public void setParentRsId(String parentRsId) {
		_parentRsId = parentRsId;
	}

	public String getInboundStatus() {
		return _inboundStatus;
	}

	public void setInboundStatus(String inboundStatus) {
		_inboundStatus = inboundStatus;
	}

	public int getCalculationType() {
		return _calculationType;
	}

	public void setCalculationType(int calculationType) {
		_calculationType = calculationType;
	}

	public int getCalculationLevel() {
		return _calculationLevel;
	}

	public void setCalculationLevel(int calculationLevel) {
		_calculationLevel = calculationLevel;
	}

	public String getCalculationRule() {
		return _calculationRule;
	}

	public void setCalculationRule(String calculationRule) {
		_calculationRule = calculationRule;
	}

	public String getCalculationRuleLevel() {
		return _calculationRuleLevel;
	}

	public void setCalculationRuleLevel(String calculationRuleLevel) {
		_calculationRuleLevel = calculationRuleLevel;
	}

	public String getEvaluationRuleType() {
		return _evaluationRuleType;
	}

	public void setEvaluationRuleType(String evaluationRuleType) {
		_evaluationRuleType = evaluationRuleType;
	}

	public String getEvaluationRuleLevel() {
		return _evaluationRuleLevel;
	}

	public void setEvaluationRuleLevel(String evaluationRuleLevel) {
		_evaluationRuleLevel = evaluationRuleLevel;
	}

	public String getEvaluationRuleOrAssociation() {
		return _evaluationRuleOrAssociation;
	}

	public void setEvaluationRuleOrAssociation(
		String evaluationRuleOrAssociation) {
		_evaluationRuleOrAssociation = evaluationRuleOrAssociation;
	}

	public String getDeductionInclusion() {
		return _deductionInclusion;
	}

	public void setDeductionInclusion(String deductionInclusion) {
		_deductionInclusion = deductionInclusion;
	}

	private String _cfpContractSid;
	private Date _createdDate;
	private String _psContractSid;
	private String _rsName;
	private int _rsStatus;
	private Date _rsStartDate;
	private String _rsTransRefId;
	private String _makePayableTo;
	private int _createdBy;
	private int _rsCategory;
	private int _rsTradeClass;
	private int _contractMasterSid;
	private int _rebateRuleType;
	private int _paymentMethod;
	private Date _rsContractAttachedDate;
	private String _rsAlias;
	private String _formulaMethodId;
	private int _rebateProcessingType;
	private int _rsContractAttachedStatus;
	private int _interestBearingBasis;
	private Date _modifiedDate;
	private String _rsTransRefName;
	private int _rebateProgramType;
	private String _rebatePlanLevel;
	private String _source;
	private String _rsCalendar;
	private int _rsType;
	private String _address1;
	private String _address2;
	private Date _rsEndDate;
	private int _modifiedBy;
	private String _rsTransRefNo;
	private String _zipCode;
	private String _rebateRuleAssociation;
	private int _state;
	private int _rebateFrequency;
	private String _rsDesignation;
	private String _batchId;
	private String _ifpContractSid;
	private int _rsContractSid;
	private int _paymentTerms;
	private String _rsNo;
	private int _rsModelSid;
	private int _rsValidationProfile;
	private String _paymentGracePeriod;
	private int _paymentFrequency;
	private boolean _recordLockStatus;
	private String _rsId;
	private String _city;
	private String _parentRsName;
	private int _interestBearingIndicator;
	private String _parentRsId;
	private String _inboundStatus;
	private int _calculationType;
	private int _calculationLevel;
	private String _calculationRule;
	private String _calculationRuleLevel;
	private String _evaluationRuleType;
	private String _evaluationRuleLevel;
	private String _evaluationRuleOrAssociation;
	private String _deductionInclusion;
}