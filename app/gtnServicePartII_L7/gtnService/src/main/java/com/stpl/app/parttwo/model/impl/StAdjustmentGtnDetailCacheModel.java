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

package com.stpl.app.parttwo.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import com.stpl.app.parttwo.model.StAdjustmentGtnDetail;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing StAdjustmentGtnDetail in entity cache.
 *
 * @author
 * @see StAdjustmentGtnDetail
 * @generated
 */
@ProviderType
public class StAdjustmentGtnDetailCacheModel implements CacheModel<StAdjustmentGtnDetail>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof StAdjustmentGtnDetailCacheModel)) {
			return false;
		}

		StAdjustmentGtnDetailCacheModel stAdjustmentGtnDetailCacheModel = (StAdjustmentGtnDetailCacheModel)obj;

		if (workflowId.equals(stAdjustmentGtnDetailCacheModel.workflowId)) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, workflowId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(143);

		sb.append("{adjustmentCreatedDate=");
		sb.append(adjustmentCreatedDate);
		sb.append(", etlCheckRecord=");
		sb.append(etlCheckRecord);
		sb.append(", businessUnitNo=");
		sb.append(businessUnitNo);
		sb.append(", redemptionPeriod=");
		sb.append(redemptionPeriod);
		sb.append(", deductionId=");
		sb.append(deductionId);
		sb.append(", glYear=");
		sb.append(glYear);
		sb.append(", brandName=");
		sb.append(brandName);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", account=");
		sb.append(account);
		sb.append(", source=");
		sb.append(source);
		sb.append(", workflowApprovedDate=");
		sb.append(workflowApprovedDate);
		sb.append(", udc6=");
		sb.append(udc6);
		sb.append(", businessUnitName=");
		sb.append(businessUnitName);
		sb.append(", udc5=");
		sb.append(udc5);
		sb.append(", workflowCreatedDate=");
		sb.append(workflowCreatedDate);
		sb.append(", udc4=");
		sb.append(udc4);
		sb.append(", udc3=");
		sb.append(udc3);
		sb.append(", udc2=");
		sb.append(udc2);
		sb.append(", udc1=");
		sb.append(udc1);
		sb.append(", adjustmentType=");
		sb.append(adjustmentType);
		sb.append(", modifiedBy=");
		sb.append(modifiedBy);
		sb.append(", deductionType=");
		sb.append(deductionType);
		sb.append(", checkRecord=");
		sb.append(checkRecord);
		sb.append(", contractName=");
		sb.append(contractName);
		sb.append(", deductionRate=");
		sb.append(deductionRate);
		sb.append(", deductionCategory=");
		sb.append(deductionCategory);
		sb.append(", deductionNo=");
		sb.append(deductionNo);
		sb.append(", companyNo=");
		sb.append(companyNo);
		sb.append(", sessionId=");
		sb.append(sessionId);
		sb.append(", glcompanyId=");
		sb.append(glcompanyId);
		sb.append(", itemName=");
		sb.append(itemName);
		sb.append(", deductionInclusion=");
		sb.append(deductionInclusion);
		sb.append(", deductionAmount=");
		sb.append(deductionAmount);
		sb.append(", companyName=");
		sb.append(companyName);
		sb.append(", project=");
		sb.append(project);
		sb.append(", deductionUdc3=");
		sb.append(deductionUdc3);
		sb.append(", deductionUdc4=");
		sb.append(deductionUdc4);
		sb.append(", deductionUdc1=");
		sb.append(deductionUdc1);
		sb.append(", itemId=");
		sb.append(itemId);
		sb.append(", deductionUdc2=");
		sb.append(deductionUdc2);
		sb.append(", accountType=");
		sb.append(accountType);
		sb.append(", glString=");
		sb.append(glString);
		sb.append(", createdDate=");
		sb.append(createdDate);
		sb.append(", createdBy=");
		sb.append(createdBy);
		sb.append(", deductionUdc6=");
		sb.append(deductionUdc6);
		sb.append(", deductionUdc5=");
		sb.append(deductionUdc5);
		sb.append(", glCompanyName=");
		sb.append(glCompanyName);
		sb.append(", workflowId=");
		sb.append(workflowId);
		sb.append(", itemNo=");
		sb.append(itemNo);
		sb.append(", contractId=");
		sb.append(contractId);
		sb.append(", deductionProgram=");
		sb.append(deductionProgram);
		sb.append(", businessUnitId=");
		sb.append(businessUnitId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", costCenter=");
		sb.append(costCenter);
		sb.append(", companyIdString=");
		sb.append(companyIdString);
		sb.append(", outboundStatus=");
		sb.append(outboundStatus);
		sb.append(", future1=");
		sb.append(future1);
		sb.append(", brandId=");
		sb.append(brandId);
		sb.append(", deductionName=");
		sb.append(deductionName);
		sb.append(", future2=");
		sb.append(future2);
		sb.append(", workflowName=");
		sb.append(workflowName);
		sb.append(", glDate=");
		sb.append(glDate);
		sb.append(", workflowCreatedBy=");
		sb.append(workflowCreatedBy);
		sb.append(", glMonth=");
		sb.append(glMonth);
		sb.append(", batchId=");
		sb.append(batchId);
		sb.append(", accountCategory=");
		sb.append(accountCategory);
		sb.append(", glCompanyNo=");
		sb.append(glCompanyNo);
		sb.append(", workflowApprovedBy=");
		sb.append(workflowApprovedBy);
		sb.append(", contractNo=");
		sb.append(contractNo);
		sb.append(", originalBatchId=");
		sb.append(originalBatchId);
		sb.append(", adjustmentLevel=");
		sb.append(adjustmentLevel);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public StAdjustmentGtnDetail toEntityModel() {
		StAdjustmentGtnDetailImpl stAdjustmentGtnDetailImpl = new StAdjustmentGtnDetailImpl();

		if (adjustmentCreatedDate == Long.MIN_VALUE) {
			stAdjustmentGtnDetailImpl.setAdjustmentCreatedDate(null);
		}
		else {
			stAdjustmentGtnDetailImpl.setAdjustmentCreatedDate(new Date(
					adjustmentCreatedDate));
		}

		stAdjustmentGtnDetailImpl.setEtlCheckRecord(etlCheckRecord);

		if (businessUnitNo == null) {
			stAdjustmentGtnDetailImpl.setBusinessUnitNo(StringPool.BLANK);
		}
		else {
			stAdjustmentGtnDetailImpl.setBusinessUnitNo(businessUnitNo);
		}

		if (redemptionPeriod == Long.MIN_VALUE) {
			stAdjustmentGtnDetailImpl.setRedemptionPeriod(null);
		}
		else {
			stAdjustmentGtnDetailImpl.setRedemptionPeriod(new Date(
					redemptionPeriod));
		}

		if (deductionId == null) {
			stAdjustmentGtnDetailImpl.setDeductionId(StringPool.BLANK);
		}
		else {
			stAdjustmentGtnDetailImpl.setDeductionId(deductionId);
		}

		stAdjustmentGtnDetailImpl.setGlYear(glYear);

		if (brandName == null) {
			stAdjustmentGtnDetailImpl.setBrandName(StringPool.BLANK);
		}
		else {
			stAdjustmentGtnDetailImpl.setBrandName(brandName);
		}

		if (modifiedDate == Long.MIN_VALUE) {
			stAdjustmentGtnDetailImpl.setModifiedDate(null);
		}
		else {
			stAdjustmentGtnDetailImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (account == null) {
			stAdjustmentGtnDetailImpl.setAccount(StringPool.BLANK);
		}
		else {
			stAdjustmentGtnDetailImpl.setAccount(account);
		}

		if (source == null) {
			stAdjustmentGtnDetailImpl.setSource(StringPool.BLANK);
		}
		else {
			stAdjustmentGtnDetailImpl.setSource(source);
		}

		if (workflowApprovedDate == Long.MIN_VALUE) {
			stAdjustmentGtnDetailImpl.setWorkflowApprovedDate(null);
		}
		else {
			stAdjustmentGtnDetailImpl.setWorkflowApprovedDate(new Date(
					workflowApprovedDate));
		}

		if (udc6 == null) {
			stAdjustmentGtnDetailImpl.setUdc6(StringPool.BLANK);
		}
		else {
			stAdjustmentGtnDetailImpl.setUdc6(udc6);
		}

		if (businessUnitName == null) {
			stAdjustmentGtnDetailImpl.setBusinessUnitName(StringPool.BLANK);
		}
		else {
			stAdjustmentGtnDetailImpl.setBusinessUnitName(businessUnitName);
		}

		if (udc5 == null) {
			stAdjustmentGtnDetailImpl.setUdc5(StringPool.BLANK);
		}
		else {
			stAdjustmentGtnDetailImpl.setUdc5(udc5);
		}

		if (workflowCreatedDate == Long.MIN_VALUE) {
			stAdjustmentGtnDetailImpl.setWorkflowCreatedDate(null);
		}
		else {
			stAdjustmentGtnDetailImpl.setWorkflowCreatedDate(new Date(
					workflowCreatedDate));
		}

		if (udc4 == null) {
			stAdjustmentGtnDetailImpl.setUdc4(StringPool.BLANK);
		}
		else {
			stAdjustmentGtnDetailImpl.setUdc4(udc4);
		}

		if (udc3 == null) {
			stAdjustmentGtnDetailImpl.setUdc3(StringPool.BLANK);
		}
		else {
			stAdjustmentGtnDetailImpl.setUdc3(udc3);
		}

		if (udc2 == null) {
			stAdjustmentGtnDetailImpl.setUdc2(StringPool.BLANK);
		}
		else {
			stAdjustmentGtnDetailImpl.setUdc2(udc2);
		}

		if (udc1 == null) {
			stAdjustmentGtnDetailImpl.setUdc1(StringPool.BLANK);
		}
		else {
			stAdjustmentGtnDetailImpl.setUdc1(udc1);
		}

		if (adjustmentType == null) {
			stAdjustmentGtnDetailImpl.setAdjustmentType(StringPool.BLANK);
		}
		else {
			stAdjustmentGtnDetailImpl.setAdjustmentType(adjustmentType);
		}

		if (modifiedBy == null) {
			stAdjustmentGtnDetailImpl.setModifiedBy(StringPool.BLANK);
		}
		else {
			stAdjustmentGtnDetailImpl.setModifiedBy(modifiedBy);
		}

		if (deductionType == null) {
			stAdjustmentGtnDetailImpl.setDeductionType(StringPool.BLANK);
		}
		else {
			stAdjustmentGtnDetailImpl.setDeductionType(deductionType);
		}

		stAdjustmentGtnDetailImpl.setCheckRecord(checkRecord);

		if (contractName == null) {
			stAdjustmentGtnDetailImpl.setContractName(StringPool.BLANK);
		}
		else {
			stAdjustmentGtnDetailImpl.setContractName(contractName);
		}

		if (deductionRate == null) {
			stAdjustmentGtnDetailImpl.setDeductionRate(StringPool.BLANK);
		}
		else {
			stAdjustmentGtnDetailImpl.setDeductionRate(deductionRate);
		}

		if (deductionCategory == null) {
			stAdjustmentGtnDetailImpl.setDeductionCategory(StringPool.BLANK);
		}
		else {
			stAdjustmentGtnDetailImpl.setDeductionCategory(deductionCategory);
		}

		if (deductionNo == null) {
			stAdjustmentGtnDetailImpl.setDeductionNo(StringPool.BLANK);
		}
		else {
			stAdjustmentGtnDetailImpl.setDeductionNo(deductionNo);
		}

		if (companyNo == null) {
			stAdjustmentGtnDetailImpl.setCompanyNo(StringPool.BLANK);
		}
		else {
			stAdjustmentGtnDetailImpl.setCompanyNo(companyNo);
		}

		if (sessionId == null) {
			stAdjustmentGtnDetailImpl.setSessionId(StringPool.BLANK);
		}
		else {
			stAdjustmentGtnDetailImpl.setSessionId(sessionId);
		}

		if (glcompanyId == null) {
			stAdjustmentGtnDetailImpl.setGlcompanyId(StringPool.BLANK);
		}
		else {
			stAdjustmentGtnDetailImpl.setGlcompanyId(glcompanyId);
		}

		if (itemName == null) {
			stAdjustmentGtnDetailImpl.setItemName(StringPool.BLANK);
		}
		else {
			stAdjustmentGtnDetailImpl.setItemName(itemName);
		}

		if (deductionInclusion == null) {
			stAdjustmentGtnDetailImpl.setDeductionInclusion(StringPool.BLANK);
		}
		else {
			stAdjustmentGtnDetailImpl.setDeductionInclusion(deductionInclusion);
		}

		if (deductionAmount == null) {
			stAdjustmentGtnDetailImpl.setDeductionAmount(StringPool.BLANK);
		}
		else {
			stAdjustmentGtnDetailImpl.setDeductionAmount(deductionAmount);
		}

		if (companyName == null) {
			stAdjustmentGtnDetailImpl.setCompanyName(StringPool.BLANK);
		}
		else {
			stAdjustmentGtnDetailImpl.setCompanyName(companyName);
		}

		if (project == null) {
			stAdjustmentGtnDetailImpl.setProject(StringPool.BLANK);
		}
		else {
			stAdjustmentGtnDetailImpl.setProject(project);
		}

		if (deductionUdc3 == null) {
			stAdjustmentGtnDetailImpl.setDeductionUdc3(StringPool.BLANK);
		}
		else {
			stAdjustmentGtnDetailImpl.setDeductionUdc3(deductionUdc3);
		}

		if (deductionUdc4 == null) {
			stAdjustmentGtnDetailImpl.setDeductionUdc4(StringPool.BLANK);
		}
		else {
			stAdjustmentGtnDetailImpl.setDeductionUdc4(deductionUdc4);
		}

		if (deductionUdc1 == null) {
			stAdjustmentGtnDetailImpl.setDeductionUdc1(StringPool.BLANK);
		}
		else {
			stAdjustmentGtnDetailImpl.setDeductionUdc1(deductionUdc1);
		}

		if (itemId == null) {
			stAdjustmentGtnDetailImpl.setItemId(StringPool.BLANK);
		}
		else {
			stAdjustmentGtnDetailImpl.setItemId(itemId);
		}

		if (deductionUdc2 == null) {
			stAdjustmentGtnDetailImpl.setDeductionUdc2(StringPool.BLANK);
		}
		else {
			stAdjustmentGtnDetailImpl.setDeductionUdc2(deductionUdc2);
		}

		if (accountType == null) {
			stAdjustmentGtnDetailImpl.setAccountType(StringPool.BLANK);
		}
		else {
			stAdjustmentGtnDetailImpl.setAccountType(accountType);
		}

		if (glString == null) {
			stAdjustmentGtnDetailImpl.setGlString(StringPool.BLANK);
		}
		else {
			stAdjustmentGtnDetailImpl.setGlString(glString);
		}

		if (createdDate == Long.MIN_VALUE) {
			stAdjustmentGtnDetailImpl.setCreatedDate(null);
		}
		else {
			stAdjustmentGtnDetailImpl.setCreatedDate(new Date(createdDate));
		}

		if (createdBy == null) {
			stAdjustmentGtnDetailImpl.setCreatedBy(StringPool.BLANK);
		}
		else {
			stAdjustmentGtnDetailImpl.setCreatedBy(createdBy);
		}

		if (deductionUdc6 == null) {
			stAdjustmentGtnDetailImpl.setDeductionUdc6(StringPool.BLANK);
		}
		else {
			stAdjustmentGtnDetailImpl.setDeductionUdc6(deductionUdc6);
		}

		if (deductionUdc5 == null) {
			stAdjustmentGtnDetailImpl.setDeductionUdc5(StringPool.BLANK);
		}
		else {
			stAdjustmentGtnDetailImpl.setDeductionUdc5(deductionUdc5);
		}

		if (glCompanyName == null) {
			stAdjustmentGtnDetailImpl.setGlCompanyName(StringPool.BLANK);
		}
		else {
			stAdjustmentGtnDetailImpl.setGlCompanyName(glCompanyName);
		}

		if (workflowId == null) {
			stAdjustmentGtnDetailImpl.setWorkflowId(StringPool.BLANK);
		}
		else {
			stAdjustmentGtnDetailImpl.setWorkflowId(workflowId);
		}

		if (itemNo == null) {
			stAdjustmentGtnDetailImpl.setItemNo(StringPool.BLANK);
		}
		else {
			stAdjustmentGtnDetailImpl.setItemNo(itemNo);
		}

		if (contractId == null) {
			stAdjustmentGtnDetailImpl.setContractId(StringPool.BLANK);
		}
		else {
			stAdjustmentGtnDetailImpl.setContractId(contractId);
		}

		if (deductionProgram == null) {
			stAdjustmentGtnDetailImpl.setDeductionProgram(StringPool.BLANK);
		}
		else {
			stAdjustmentGtnDetailImpl.setDeductionProgram(deductionProgram);
		}

		if (businessUnitId == null) {
			stAdjustmentGtnDetailImpl.setBusinessUnitId(StringPool.BLANK);
		}
		else {
			stAdjustmentGtnDetailImpl.setBusinessUnitId(businessUnitId);
		}

		if (userId == null) {
			stAdjustmentGtnDetailImpl.setUserId(StringPool.BLANK);
		}
		else {
			stAdjustmentGtnDetailImpl.setUserId(userId);
		}

		if (costCenter == null) {
			stAdjustmentGtnDetailImpl.setCostCenter(StringPool.BLANK);
		}
		else {
			stAdjustmentGtnDetailImpl.setCostCenter(costCenter);
		}

		if (companyIdString == null) {
			stAdjustmentGtnDetailImpl.setCompanyIdString(StringPool.BLANK);
		}
		else {
			stAdjustmentGtnDetailImpl.setCompanyIdString(companyIdString);
		}

		if (outboundStatus == null) {
			stAdjustmentGtnDetailImpl.setOutboundStatus(StringPool.BLANK);
		}
		else {
			stAdjustmentGtnDetailImpl.setOutboundStatus(outboundStatus);
		}

		if (future1 == null) {
			stAdjustmentGtnDetailImpl.setFuture1(StringPool.BLANK);
		}
		else {
			stAdjustmentGtnDetailImpl.setFuture1(future1);
		}

		if (brandId == null) {
			stAdjustmentGtnDetailImpl.setBrandId(StringPool.BLANK);
		}
		else {
			stAdjustmentGtnDetailImpl.setBrandId(brandId);
		}

		if (deductionName == null) {
			stAdjustmentGtnDetailImpl.setDeductionName(StringPool.BLANK);
		}
		else {
			stAdjustmentGtnDetailImpl.setDeductionName(deductionName);
		}

		if (future2 == null) {
			stAdjustmentGtnDetailImpl.setFuture2(StringPool.BLANK);
		}
		else {
			stAdjustmentGtnDetailImpl.setFuture2(future2);
		}

		if (workflowName == null) {
			stAdjustmentGtnDetailImpl.setWorkflowName(StringPool.BLANK);
		}
		else {
			stAdjustmentGtnDetailImpl.setWorkflowName(workflowName);
		}

		if (glDate == Long.MIN_VALUE) {
			stAdjustmentGtnDetailImpl.setGlDate(null);
		}
		else {
			stAdjustmentGtnDetailImpl.setGlDate(new Date(glDate));
		}

		if (workflowCreatedBy == null) {
			stAdjustmentGtnDetailImpl.setWorkflowCreatedBy(StringPool.BLANK);
		}
		else {
			stAdjustmentGtnDetailImpl.setWorkflowCreatedBy(workflowCreatedBy);
		}

		stAdjustmentGtnDetailImpl.setGlMonth(glMonth);

		if (batchId == null) {
			stAdjustmentGtnDetailImpl.setBatchId(StringPool.BLANK);
		}
		else {
			stAdjustmentGtnDetailImpl.setBatchId(batchId);
		}

		if (accountCategory == null) {
			stAdjustmentGtnDetailImpl.setAccountCategory(StringPool.BLANK);
		}
		else {
			stAdjustmentGtnDetailImpl.setAccountCategory(accountCategory);
		}

		if (glCompanyNo == null) {
			stAdjustmentGtnDetailImpl.setGlCompanyNo(StringPool.BLANK);
		}
		else {
			stAdjustmentGtnDetailImpl.setGlCompanyNo(glCompanyNo);
		}

		if (workflowApprovedBy == null) {
			stAdjustmentGtnDetailImpl.setWorkflowApprovedBy(StringPool.BLANK);
		}
		else {
			stAdjustmentGtnDetailImpl.setWorkflowApprovedBy(workflowApprovedBy);
		}

		if (contractNo == null) {
			stAdjustmentGtnDetailImpl.setContractNo(StringPool.BLANK);
		}
		else {
			stAdjustmentGtnDetailImpl.setContractNo(contractNo);
		}

		if (originalBatchId == null) {
			stAdjustmentGtnDetailImpl.setOriginalBatchId(StringPool.BLANK);
		}
		else {
			stAdjustmentGtnDetailImpl.setOriginalBatchId(originalBatchId);
		}

		if (adjustmentLevel == null) {
			stAdjustmentGtnDetailImpl.setAdjustmentLevel(StringPool.BLANK);
		}
		else {
			stAdjustmentGtnDetailImpl.setAdjustmentLevel(adjustmentLevel);
		}

		stAdjustmentGtnDetailImpl.resetOriginalValues();

		return stAdjustmentGtnDetailImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		adjustmentCreatedDate = objectInput.readLong();

		etlCheckRecord = objectInput.readBoolean();
		businessUnitNo = objectInput.readUTF();
		redemptionPeriod = objectInput.readLong();
		deductionId = objectInput.readUTF();

		glYear = objectInput.readInt();
		brandName = objectInput.readUTF();
		modifiedDate = objectInput.readLong();
		account = objectInput.readUTF();
		source = objectInput.readUTF();
		workflowApprovedDate = objectInput.readLong();
		udc6 = objectInput.readUTF();
		businessUnitName = objectInput.readUTF();
		udc5 = objectInput.readUTF();
		workflowCreatedDate = objectInput.readLong();
		udc4 = objectInput.readUTF();
		udc3 = objectInput.readUTF();
		udc2 = objectInput.readUTF();
		udc1 = objectInput.readUTF();
		adjustmentType = objectInput.readUTF();
		modifiedBy = objectInput.readUTF();
		deductionType = objectInput.readUTF();

		checkRecord = objectInput.readBoolean();
		contractName = objectInput.readUTF();
		deductionRate = objectInput.readUTF();
		deductionCategory = objectInput.readUTF();
		deductionNo = objectInput.readUTF();
		companyNo = objectInput.readUTF();
		sessionId = objectInput.readUTF();
		glcompanyId = objectInput.readUTF();
		itemName = objectInput.readUTF();
		deductionInclusion = objectInput.readUTF();
		deductionAmount = objectInput.readUTF();
		companyName = objectInput.readUTF();
		project = objectInput.readUTF();
		deductionUdc3 = objectInput.readUTF();
		deductionUdc4 = objectInput.readUTF();
		deductionUdc1 = objectInput.readUTF();
		itemId = objectInput.readUTF();
		deductionUdc2 = objectInput.readUTF();
		accountType = objectInput.readUTF();
		glString = objectInput.readUTF();
		createdDate = objectInput.readLong();
		createdBy = objectInput.readUTF();
		deductionUdc6 = objectInput.readUTF();
		deductionUdc5 = objectInput.readUTF();
		glCompanyName = objectInput.readUTF();
		workflowId = objectInput.readUTF();
		itemNo = objectInput.readUTF();
		contractId = objectInput.readUTF();
		deductionProgram = objectInput.readUTF();
		businessUnitId = objectInput.readUTF();
		userId = objectInput.readUTF();
		costCenter = objectInput.readUTF();
		companyIdString = objectInput.readUTF();
		outboundStatus = objectInput.readUTF();
		future1 = objectInput.readUTF();
		brandId = objectInput.readUTF();
		deductionName = objectInput.readUTF();
		future2 = objectInput.readUTF();
		workflowName = objectInput.readUTF();
		glDate = objectInput.readLong();
		workflowCreatedBy = objectInput.readUTF();

		glMonth = objectInput.readInt();
		batchId = objectInput.readUTF();
		accountCategory = objectInput.readUTF();
		glCompanyNo = objectInput.readUTF();
		workflowApprovedBy = objectInput.readUTF();
		contractNo = objectInput.readUTF();
		originalBatchId = objectInput.readUTF();
		adjustmentLevel = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(adjustmentCreatedDate);

		objectOutput.writeBoolean(etlCheckRecord);

		if (businessUnitNo == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(businessUnitNo);
		}

		objectOutput.writeLong(redemptionPeriod);

		if (deductionId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(deductionId);
		}

		objectOutput.writeInt(glYear);

		if (brandName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(brandName);
		}

		objectOutput.writeLong(modifiedDate);

		if (account == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(account);
		}

		if (source == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(source);
		}

		objectOutput.writeLong(workflowApprovedDate);

		if (udc6 == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(udc6);
		}

		if (businessUnitName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(businessUnitName);
		}

		if (udc5 == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(udc5);
		}

		objectOutput.writeLong(workflowCreatedDate);

		if (udc4 == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(udc4);
		}

		if (udc3 == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(udc3);
		}

		if (udc2 == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(udc2);
		}

		if (udc1 == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(udc1);
		}

		if (adjustmentType == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(adjustmentType);
		}

		if (modifiedBy == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(modifiedBy);
		}

		if (deductionType == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(deductionType);
		}

		objectOutput.writeBoolean(checkRecord);

		if (contractName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(contractName);
		}

		if (deductionRate == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(deductionRate);
		}

		if (deductionCategory == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(deductionCategory);
		}

		if (deductionNo == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(deductionNo);
		}

		if (companyNo == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(companyNo);
		}

		if (sessionId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(sessionId);
		}

		if (glcompanyId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(glcompanyId);
		}

		if (itemName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(itemName);
		}

		if (deductionInclusion == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(deductionInclusion);
		}

		if (deductionAmount == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(deductionAmount);
		}

		if (companyName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(companyName);
		}

		if (project == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(project);
		}

		if (deductionUdc3 == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(deductionUdc3);
		}

		if (deductionUdc4 == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(deductionUdc4);
		}

		if (deductionUdc1 == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(deductionUdc1);
		}

		if (itemId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(itemId);
		}

		if (deductionUdc2 == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(deductionUdc2);
		}

		if (accountType == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(accountType);
		}

		if (glString == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(glString);
		}

		objectOutput.writeLong(createdDate);

		if (createdBy == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(createdBy);
		}

		if (deductionUdc6 == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(deductionUdc6);
		}

		if (deductionUdc5 == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(deductionUdc5);
		}

		if (glCompanyName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(glCompanyName);
		}

		if (workflowId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(workflowId);
		}

		if (itemNo == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(itemNo);
		}

		if (contractId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(contractId);
		}

		if (deductionProgram == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(deductionProgram);
		}

		if (businessUnitId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(businessUnitId);
		}

		if (userId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(userId);
		}

		if (costCenter == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(costCenter);
		}

		if (companyIdString == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(companyIdString);
		}

		if (outboundStatus == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(outboundStatus);
		}

		if (future1 == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(future1);
		}

		if (brandId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(brandId);
		}

		if (deductionName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(deductionName);
		}

		if (future2 == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(future2);
		}

		if (workflowName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(workflowName);
		}

		objectOutput.writeLong(glDate);

		if (workflowCreatedBy == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(workflowCreatedBy);
		}

		objectOutput.writeInt(glMonth);

		if (batchId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(batchId);
		}

		if (accountCategory == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(accountCategory);
		}

		if (glCompanyNo == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(glCompanyNo);
		}

		if (workflowApprovedBy == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(workflowApprovedBy);
		}

		if (contractNo == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(contractNo);
		}

		if (originalBatchId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(originalBatchId);
		}

		if (adjustmentLevel == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(adjustmentLevel);
		}
	}

	public long adjustmentCreatedDate;
	public boolean etlCheckRecord;
	public String businessUnitNo;
	public long redemptionPeriod;
	public String deductionId;
	public int glYear;
	public String brandName;
	public long modifiedDate;
	public String account;
	public String source;
	public long workflowApprovedDate;
	public String udc6;
	public String businessUnitName;
	public String udc5;
	public long workflowCreatedDate;
	public String udc4;
	public String udc3;
	public String udc2;
	public String udc1;
	public String adjustmentType;
	public String modifiedBy;
	public String deductionType;
	public boolean checkRecord;
	public String contractName;
	public String deductionRate;
	public String deductionCategory;
	public String deductionNo;
	public String companyNo;
	public String sessionId;
	public String glcompanyId;
	public String itemName;
	public String deductionInclusion;
	public String deductionAmount;
	public String companyName;
	public String project;
	public String deductionUdc3;
	public String deductionUdc4;
	public String deductionUdc1;
	public String itemId;
	public String deductionUdc2;
	public String accountType;
	public String glString;
	public long createdDate;
	public String createdBy;
	public String deductionUdc6;
	public String deductionUdc5;
	public String glCompanyName;
	public String workflowId;
	public String itemNo;
	public String contractId;
	public String deductionProgram;
	public String businessUnitId;
	public String userId;
	public String costCenter;
	public String companyIdString;
	public String outboundStatus;
	public String future1;
	public String brandId;
	public String deductionName;
	public String future2;
	public String workflowName;
	public long glDate;
	public String workflowCreatedBy;
	public int glMonth;
	public String batchId;
	public String accountCategory;
	public String glCompanyNo;
	public String workflowApprovedBy;
	public String contractNo;
	public String originalBatchId;
	public String adjustmentLevel;
}