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

package com.stpl.app.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import com.stpl.app.model.RsContract;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing RsContract in entity cache.
 *
 * @author
 * @see RsContract
 * @generated
 */
@ProviderType
public class RsContractCacheModel implements CacheModel<RsContract>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof RsContractCacheModel)) {
			return false;
		}

		RsContractCacheModel rsContractCacheModel = (RsContractCacheModel)obj;

		if (rsContractSid == rsContractCacheModel.rsContractSid) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, rsContractSid);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(123);

		sb.append("{cfpContractSid=");
		sb.append(cfpContractSid);
		sb.append(", createdDate=");
		sb.append(createdDate);
		sb.append(", psContractSid=");
		sb.append(psContractSid);
		sb.append(", rsName=");
		sb.append(rsName);
		sb.append(", rsStatus=");
		sb.append(rsStatus);
		sb.append(", rsStartDate=");
		sb.append(rsStartDate);
		sb.append(", rsTransRefId=");
		sb.append(rsTransRefId);
		sb.append(", makePayableTo=");
		sb.append(makePayableTo);
		sb.append(", createdBy=");
		sb.append(createdBy);
		sb.append(", rsCategory=");
		sb.append(rsCategory);
		sb.append(", rsTradeClass=");
		sb.append(rsTradeClass);
		sb.append(", contractMasterSid=");
		sb.append(contractMasterSid);
		sb.append(", rebateRuleType=");
		sb.append(rebateRuleType);
		sb.append(", paymentMethod=");
		sb.append(paymentMethod);
		sb.append(", rsContractAttachedDate=");
		sb.append(rsContractAttachedDate);
		sb.append(", rsAlias=");
		sb.append(rsAlias);
		sb.append(", formulaMethodId=");
		sb.append(formulaMethodId);
		sb.append(", rebateProcessingType=");
		sb.append(rebateProcessingType);
		sb.append(", rsContractAttachedStatus=");
		sb.append(rsContractAttachedStatus);
		sb.append(", interestBearingBasis=");
		sb.append(interestBearingBasis);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", rsTransRefName=");
		sb.append(rsTransRefName);
		sb.append(", rebateProgramType=");
		sb.append(rebateProgramType);
		sb.append(", rebatePlanLevel=");
		sb.append(rebatePlanLevel);
		sb.append(", source=");
		sb.append(source);
		sb.append(", rsCalendar=");
		sb.append(rsCalendar);
		sb.append(", rsType=");
		sb.append(rsType);
		sb.append(", address1=");
		sb.append(address1);
		sb.append(", address2=");
		sb.append(address2);
		sb.append(", rsEndDate=");
		sb.append(rsEndDate);
		sb.append(", modifiedBy=");
		sb.append(modifiedBy);
		sb.append(", rsTransRefNo=");
		sb.append(rsTransRefNo);
		sb.append(", zipCode=");
		sb.append(zipCode);
		sb.append(", rebateRuleAssociation=");
		sb.append(rebateRuleAssociation);
		sb.append(", state=");
		sb.append(state);
		sb.append(", rebateFrequency=");
		sb.append(rebateFrequency);
		sb.append(", rsDesignation=");
		sb.append(rsDesignation);
		sb.append(", batchId=");
		sb.append(batchId);
		sb.append(", ifpContractSid=");
		sb.append(ifpContractSid);
		sb.append(", rsContractSid=");
		sb.append(rsContractSid);
		sb.append(", paymentTerms=");
		sb.append(paymentTerms);
		sb.append(", rsNo=");
		sb.append(rsNo);
		sb.append(", rsModelSid=");
		sb.append(rsModelSid);
		sb.append(", rsValidationProfile=");
		sb.append(rsValidationProfile);
		sb.append(", paymentGracePeriod=");
		sb.append(paymentGracePeriod);
		sb.append(", paymentFrequency=");
		sb.append(paymentFrequency);
		sb.append(", recordLockStatus=");
		sb.append(recordLockStatus);
		sb.append(", rsId=");
		sb.append(rsId);
		sb.append(", city=");
		sb.append(city);
		sb.append(", parentRsName=");
		sb.append(parentRsName);
		sb.append(", interestBearingIndicator=");
		sb.append(interestBearingIndicator);
		sb.append(", parentRsId=");
		sb.append(parentRsId);
		sb.append(", inboundStatus=");
		sb.append(inboundStatus);
		sb.append(", calculationType=");
		sb.append(calculationType);
		sb.append(", calculationLevel=");
		sb.append(calculationLevel);
		sb.append(", calculationRule=");
		sb.append(calculationRule);
		sb.append(", calculationRuleLevel=");
		sb.append(calculationRuleLevel);
		sb.append(", evaluationRuleType=");
		sb.append(evaluationRuleType);
		sb.append(", evaluationRuleLevel=");
		sb.append(evaluationRuleLevel);
		sb.append(", evaluationRuleOrAssociation=");
		sb.append(evaluationRuleOrAssociation);
		sb.append(", deductionInclusion=");
		sb.append(deductionInclusion);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public RsContract toEntityModel() {
		RsContractImpl rsContractImpl = new RsContractImpl();

		if (cfpContractSid == null) {
			rsContractImpl.setCfpContractSid(StringPool.BLANK);
		}
		else {
			rsContractImpl.setCfpContractSid(cfpContractSid);
		}

		if (createdDate == Long.MIN_VALUE) {
			rsContractImpl.setCreatedDate(null);
		}
		else {
			rsContractImpl.setCreatedDate(new Date(createdDate));
		}

		if (psContractSid == null) {
			rsContractImpl.setPsContractSid(StringPool.BLANK);
		}
		else {
			rsContractImpl.setPsContractSid(psContractSid);
		}

		if (rsName == null) {
			rsContractImpl.setRsName(StringPool.BLANK);
		}
		else {
			rsContractImpl.setRsName(rsName);
		}

		rsContractImpl.setRsStatus(rsStatus);

		if (rsStartDate == Long.MIN_VALUE) {
			rsContractImpl.setRsStartDate(null);
		}
		else {
			rsContractImpl.setRsStartDate(new Date(rsStartDate));
		}

		if (rsTransRefId == null) {
			rsContractImpl.setRsTransRefId(StringPool.BLANK);
		}
		else {
			rsContractImpl.setRsTransRefId(rsTransRefId);
		}

		if (makePayableTo == null) {
			rsContractImpl.setMakePayableTo(StringPool.BLANK);
		}
		else {
			rsContractImpl.setMakePayableTo(makePayableTo);
		}

		rsContractImpl.setCreatedBy(createdBy);
		rsContractImpl.setRsCategory(rsCategory);
		rsContractImpl.setRsTradeClass(rsTradeClass);
		rsContractImpl.setContractMasterSid(contractMasterSid);
		rsContractImpl.setRebateRuleType(rebateRuleType);
		rsContractImpl.setPaymentMethod(paymentMethod);

		if (rsContractAttachedDate == Long.MIN_VALUE) {
			rsContractImpl.setRsContractAttachedDate(null);
		}
		else {
			rsContractImpl.setRsContractAttachedDate(new Date(
					rsContractAttachedDate));
		}

		if (rsAlias == null) {
			rsContractImpl.setRsAlias(StringPool.BLANK);
		}
		else {
			rsContractImpl.setRsAlias(rsAlias);
		}

		if (formulaMethodId == null) {
			rsContractImpl.setFormulaMethodId(StringPool.BLANK);
		}
		else {
			rsContractImpl.setFormulaMethodId(formulaMethodId);
		}

		rsContractImpl.setRebateProcessingType(rebateProcessingType);
		rsContractImpl.setRsContractAttachedStatus(rsContractAttachedStatus);
		rsContractImpl.setInterestBearingBasis(interestBearingBasis);

		if (modifiedDate == Long.MIN_VALUE) {
			rsContractImpl.setModifiedDate(null);
		}
		else {
			rsContractImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (rsTransRefName == null) {
			rsContractImpl.setRsTransRefName(StringPool.BLANK);
		}
		else {
			rsContractImpl.setRsTransRefName(rsTransRefName);
		}

		rsContractImpl.setRebateProgramType(rebateProgramType);

		if (rebatePlanLevel == null) {
			rsContractImpl.setRebatePlanLevel(StringPool.BLANK);
		}
		else {
			rsContractImpl.setRebatePlanLevel(rebatePlanLevel);
		}

		if (source == null) {
			rsContractImpl.setSource(StringPool.BLANK);
		}
		else {
			rsContractImpl.setSource(source);
		}

		if (rsCalendar == null) {
			rsContractImpl.setRsCalendar(StringPool.BLANK);
		}
		else {
			rsContractImpl.setRsCalendar(rsCalendar);
		}

		rsContractImpl.setRsType(rsType);

		if (address1 == null) {
			rsContractImpl.setAddress1(StringPool.BLANK);
		}
		else {
			rsContractImpl.setAddress1(address1);
		}

		if (address2 == null) {
			rsContractImpl.setAddress2(StringPool.BLANK);
		}
		else {
			rsContractImpl.setAddress2(address2);
		}

		if (rsEndDate == Long.MIN_VALUE) {
			rsContractImpl.setRsEndDate(null);
		}
		else {
			rsContractImpl.setRsEndDate(new Date(rsEndDate));
		}

		rsContractImpl.setModifiedBy(modifiedBy);

		if (rsTransRefNo == null) {
			rsContractImpl.setRsTransRefNo(StringPool.BLANK);
		}
		else {
			rsContractImpl.setRsTransRefNo(rsTransRefNo);
		}

		if (zipCode == null) {
			rsContractImpl.setZipCode(StringPool.BLANK);
		}
		else {
			rsContractImpl.setZipCode(zipCode);
		}

		if (rebateRuleAssociation == null) {
			rsContractImpl.setRebateRuleAssociation(StringPool.BLANK);
		}
		else {
			rsContractImpl.setRebateRuleAssociation(rebateRuleAssociation);
		}

		rsContractImpl.setState(state);
		rsContractImpl.setRebateFrequency(rebateFrequency);

		if (rsDesignation == null) {
			rsContractImpl.setRsDesignation(StringPool.BLANK);
		}
		else {
			rsContractImpl.setRsDesignation(rsDesignation);
		}

		if (batchId == null) {
			rsContractImpl.setBatchId(StringPool.BLANK);
		}
		else {
			rsContractImpl.setBatchId(batchId);
		}

		if (ifpContractSid == null) {
			rsContractImpl.setIfpContractSid(StringPool.BLANK);
		}
		else {
			rsContractImpl.setIfpContractSid(ifpContractSid);
		}

		rsContractImpl.setRsContractSid(rsContractSid);
		rsContractImpl.setPaymentTerms(paymentTerms);

		if (rsNo == null) {
			rsContractImpl.setRsNo(StringPool.BLANK);
		}
		else {
			rsContractImpl.setRsNo(rsNo);
		}

		rsContractImpl.setRsModelSid(rsModelSid);
		rsContractImpl.setRsValidationProfile(rsValidationProfile);

		if (paymentGracePeriod == null) {
			rsContractImpl.setPaymentGracePeriod(StringPool.BLANK);
		}
		else {
			rsContractImpl.setPaymentGracePeriod(paymentGracePeriod);
		}

		rsContractImpl.setPaymentFrequency(paymentFrequency);
		rsContractImpl.setRecordLockStatus(recordLockStatus);

		if (rsId == null) {
			rsContractImpl.setRsId(StringPool.BLANK);
		}
		else {
			rsContractImpl.setRsId(rsId);
		}

		if (city == null) {
			rsContractImpl.setCity(StringPool.BLANK);
		}
		else {
			rsContractImpl.setCity(city);
		}

		if (parentRsName == null) {
			rsContractImpl.setParentRsName(StringPool.BLANK);
		}
		else {
			rsContractImpl.setParentRsName(parentRsName);
		}

		rsContractImpl.setInterestBearingIndicator(interestBearingIndicator);

		if (parentRsId == null) {
			rsContractImpl.setParentRsId(StringPool.BLANK);
		}
		else {
			rsContractImpl.setParentRsId(parentRsId);
		}

		if (inboundStatus == null) {
			rsContractImpl.setInboundStatus(StringPool.BLANK);
		}
		else {
			rsContractImpl.setInboundStatus(inboundStatus);
		}

		rsContractImpl.setCalculationType(calculationType);
		rsContractImpl.setCalculationLevel(calculationLevel);

		if (calculationRule == null) {
			rsContractImpl.setCalculationRule(StringPool.BLANK);
		}
		else {
			rsContractImpl.setCalculationRule(calculationRule);
		}

		if (calculationRuleLevel == null) {
			rsContractImpl.setCalculationRuleLevel(StringPool.BLANK);
		}
		else {
			rsContractImpl.setCalculationRuleLevel(calculationRuleLevel);
		}

		if (evaluationRuleType == null) {
			rsContractImpl.setEvaluationRuleType(StringPool.BLANK);
		}
		else {
			rsContractImpl.setEvaluationRuleType(evaluationRuleType);
		}

		if (evaluationRuleLevel == null) {
			rsContractImpl.setEvaluationRuleLevel(StringPool.BLANK);
		}
		else {
			rsContractImpl.setEvaluationRuleLevel(evaluationRuleLevel);
		}

		if (evaluationRuleOrAssociation == null) {
			rsContractImpl.setEvaluationRuleOrAssociation(StringPool.BLANK);
		}
		else {
			rsContractImpl.setEvaluationRuleOrAssociation(evaluationRuleOrAssociation);
		}

		if (deductionInclusion == null) {
			rsContractImpl.setDeductionInclusion(StringPool.BLANK);
		}
		else {
			rsContractImpl.setDeductionInclusion(deductionInclusion);
		}

		rsContractImpl.resetOriginalValues();

		return rsContractImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		cfpContractSid = objectInput.readUTF();
		createdDate = objectInput.readLong();
		psContractSid = objectInput.readUTF();
		rsName = objectInput.readUTF();

		rsStatus = objectInput.readInt();
		rsStartDate = objectInput.readLong();
		rsTransRefId = objectInput.readUTF();
		makePayableTo = objectInput.readUTF();

		createdBy = objectInput.readInt();

		rsCategory = objectInput.readInt();

		rsTradeClass = objectInput.readInt();

		contractMasterSid = objectInput.readInt();

		rebateRuleType = objectInput.readInt();

		paymentMethod = objectInput.readInt();
		rsContractAttachedDate = objectInput.readLong();
		rsAlias = objectInput.readUTF();
		formulaMethodId = objectInput.readUTF();

		rebateProcessingType = objectInput.readInt();

		rsContractAttachedStatus = objectInput.readInt();

		interestBearingBasis = objectInput.readInt();
		modifiedDate = objectInput.readLong();
		rsTransRefName = objectInput.readUTF();

		rebateProgramType = objectInput.readInt();
		rebatePlanLevel = objectInput.readUTF();
		source = objectInput.readUTF();
		rsCalendar = objectInput.readUTF();

		rsType = objectInput.readInt();
		address1 = objectInput.readUTF();
		address2 = objectInput.readUTF();
		rsEndDate = objectInput.readLong();

		modifiedBy = objectInput.readInt();
		rsTransRefNo = objectInput.readUTF();
		zipCode = objectInput.readUTF();
		rebateRuleAssociation = objectInput.readUTF();

		state = objectInput.readInt();

		rebateFrequency = objectInput.readInt();
		rsDesignation = objectInput.readUTF();
		batchId = objectInput.readUTF();
		ifpContractSid = objectInput.readUTF();

		rsContractSid = objectInput.readInt();

		paymentTerms = objectInput.readInt();
		rsNo = objectInput.readUTF();

		rsModelSid = objectInput.readInt();

		rsValidationProfile = objectInput.readInt();
		paymentGracePeriod = objectInput.readUTF();

		paymentFrequency = objectInput.readInt();

		recordLockStatus = objectInput.readBoolean();
		rsId = objectInput.readUTF();
		city = objectInput.readUTF();
		parentRsName = objectInput.readUTF();

		interestBearingIndicator = objectInput.readInt();
		parentRsId = objectInput.readUTF();
		inboundStatus = objectInput.readUTF();

		calculationType = objectInput.readInt();

		calculationLevel = objectInput.readInt();
		calculationRule = objectInput.readUTF();
		calculationRuleLevel = objectInput.readUTF();
		evaluationRuleType = objectInput.readUTF();
		evaluationRuleLevel = objectInput.readUTF();
		evaluationRuleOrAssociation = objectInput.readUTF();
		deductionInclusion = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		if (cfpContractSid == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(cfpContractSid);
		}

		objectOutput.writeLong(createdDate);

		if (psContractSid == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(psContractSid);
		}

		if (rsName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(rsName);
		}

		objectOutput.writeInt(rsStatus);
		objectOutput.writeLong(rsStartDate);

		if (rsTransRefId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(rsTransRefId);
		}

		if (makePayableTo == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(makePayableTo);
		}

		objectOutput.writeInt(createdBy);

		objectOutput.writeInt(rsCategory);

		objectOutput.writeInt(rsTradeClass);

		objectOutput.writeInt(contractMasterSid);

		objectOutput.writeInt(rebateRuleType);

		objectOutput.writeInt(paymentMethod);
		objectOutput.writeLong(rsContractAttachedDate);

		if (rsAlias == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(rsAlias);
		}

		if (formulaMethodId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(formulaMethodId);
		}

		objectOutput.writeInt(rebateProcessingType);

		objectOutput.writeInt(rsContractAttachedStatus);

		objectOutput.writeInt(interestBearingBasis);
		objectOutput.writeLong(modifiedDate);

		if (rsTransRefName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(rsTransRefName);
		}

		objectOutput.writeInt(rebateProgramType);

		if (rebatePlanLevel == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(rebatePlanLevel);
		}

		if (source == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(source);
		}

		if (rsCalendar == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(rsCalendar);
		}

		objectOutput.writeInt(rsType);

		if (address1 == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(address1);
		}

		if (address2 == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(address2);
		}

		objectOutput.writeLong(rsEndDate);

		objectOutput.writeInt(modifiedBy);

		if (rsTransRefNo == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(rsTransRefNo);
		}

		if (zipCode == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(zipCode);
		}

		if (rebateRuleAssociation == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(rebateRuleAssociation);
		}

		objectOutput.writeInt(state);

		objectOutput.writeInt(rebateFrequency);

		if (rsDesignation == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(rsDesignation);
		}

		if (batchId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(batchId);
		}

		if (ifpContractSid == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(ifpContractSid);
		}

		objectOutput.writeInt(rsContractSid);

		objectOutput.writeInt(paymentTerms);

		if (rsNo == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(rsNo);
		}

		objectOutput.writeInt(rsModelSid);

		objectOutput.writeInt(rsValidationProfile);

		if (paymentGracePeriod == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(paymentGracePeriod);
		}

		objectOutput.writeInt(paymentFrequency);

		objectOutput.writeBoolean(recordLockStatus);

		if (rsId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(rsId);
		}

		if (city == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(city);
		}

		if (parentRsName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(parentRsName);
		}

		objectOutput.writeInt(interestBearingIndicator);

		if (parentRsId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(parentRsId);
		}

		if (inboundStatus == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(inboundStatus);
		}

		objectOutput.writeInt(calculationType);

		objectOutput.writeInt(calculationLevel);

		if (calculationRule == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(calculationRule);
		}

		if (calculationRuleLevel == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(calculationRuleLevel);
		}

		if (evaluationRuleType == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(evaluationRuleType);
		}

		if (evaluationRuleLevel == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(evaluationRuleLevel);
		}

		if (evaluationRuleOrAssociation == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(evaluationRuleOrAssociation);
		}

		if (deductionInclusion == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(deductionInclusion);
		}
	}

	public String cfpContractSid;
	public long createdDate;
	public String psContractSid;
	public String rsName;
	public int rsStatus;
	public long rsStartDate;
	public String rsTransRefId;
	public String makePayableTo;
	public int createdBy;
	public int rsCategory;
	public int rsTradeClass;
	public int contractMasterSid;
	public int rebateRuleType;
	public int paymentMethod;
	public long rsContractAttachedDate;
	public String rsAlias;
	public String formulaMethodId;
	public int rebateProcessingType;
	public int rsContractAttachedStatus;
	public int interestBearingBasis;
	public long modifiedDate;
	public String rsTransRefName;
	public int rebateProgramType;
	public String rebatePlanLevel;
	public String source;
	public String rsCalendar;
	public int rsType;
	public String address1;
	public String address2;
	public long rsEndDate;
	public int modifiedBy;
	public String rsTransRefNo;
	public String zipCode;
	public String rebateRuleAssociation;
	public int state;
	public int rebateFrequency;
	public String rsDesignation;
	public String batchId;
	public String ifpContractSid;
	public int rsContractSid;
	public int paymentTerms;
	public String rsNo;
	public int rsModelSid;
	public int rsValidationProfile;
	public String paymentGracePeriod;
	public int paymentFrequency;
	public boolean recordLockStatus;
	public String rsId;
	public String city;
	public String parentRsName;
	public int interestBearingIndicator;
	public String parentRsId;
	public String inboundStatus;
	public int calculationType;
	public int calculationLevel;
	public String calculationRule;
	public String calculationRuleLevel;
	public String evaluationRuleType;
	public String evaluationRuleLevel;
	public String evaluationRuleOrAssociation;
	public String deductionInclusion;
}