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

import com.stpl.app.parttwo.model.IvldAccrualInbound;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing IvldAccrualInbound in entity cache.
 *
 * @author
 * @see IvldAccrualInbound
 * @generated
 */
@ProviderType
public class IvldAccrualInboundCacheModel implements CacheModel<IvldAccrualInbound>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof IvldAccrualInboundCacheModel)) {
			return false;
		}

		IvldAccrualInboundCacheModel ivldAccrualInboundCacheModel = (IvldAccrualInboundCacheModel)obj;

		if (ivldAccrualInboundSid == ivldAccrualInboundCacheModel.ivldAccrualInboundSid) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, ivldAccrualInboundSid);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(105);

		sb.append("{recordCreatedDate=");
		sb.append(recordCreatedDate);
		sb.append(", accountId=");
		sb.append(accountId);
		sb.append(", postingIndicator=");
		sb.append(postingIndicator);
		sb.append(", itemId=");
		sb.append(itemId);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", accrualPeriodEndDate=");
		sb.append(accrualPeriodEndDate);
		sb.append(", itemIdentCodeQualifier=");
		sb.append(itemIdentCodeQualifier);
		sb.append(", glCompanyMasterSid=");
		sb.append(glCompanyMasterSid);
		sb.append(", salesMasterId=");
		sb.append(salesMasterId);
		sb.append(", createdDate=");
		sb.append(createdDate);
		sb.append(", createdBy=");
		sb.append(createdBy);
		sb.append(", source=");
		sb.append(source);
		sb.append(", accrualPeriodStartDate=");
		sb.append(accrualPeriodStartDate);
		sb.append(", addChgDelIndicator=");
		sb.append(addChgDelIndicator);
		sb.append(", subLedgerType=");
		sb.append(subLedgerType);
		sb.append(", programNo=");
		sb.append(programNo);
		sb.append(", documentType=");
		sb.append(documentType);
		sb.append(", accrualIntfid=");
		sb.append(accrualIntfid);
		sb.append(", glCompanyName=");
		sb.append(glCompanyName);
		sb.append(", errorCode=");
		sb.append(errorCode);
		sb.append(", intfInsertedDate=");
		sb.append(intfInsertedDate);
		sb.append(", modifiedBy=");
		sb.append(modifiedBy);
		sb.append(", categoryId=");
		sb.append(categoryId);
		sb.append(", itemNo=");
		sb.append(itemNo);
		sb.append(", reprocessedFlag=");
		sb.append(reprocessedFlag);
		sb.append(", compIdentCodeQualifier=");
		sb.append(compIdentCodeQualifier);
		sb.append(", acctIdentCodeQualifier=");
		sb.append(acctIdentCodeQualifier);
		sb.append(", contractId=");
		sb.append(contractId);
		sb.append(", accountNo=");
		sb.append(accountNo);
		sb.append(", accrualId=");
		sb.append(accrualId);
		sb.append(", reasonForFailure=");
		sb.append(reasonForFailure);
		sb.append(", companyIdString=");
		sb.append(companyIdString);
		sb.append(", accountName=");
		sb.append(accountName);
		sb.append(", accrualType=");
		sb.append(accrualType);
		sb.append(", postingDate=");
		sb.append(postingDate);
		sb.append(", brandId=");
		sb.append(brandId);
		sb.append(", provisionId=");
		sb.append(provisionId);
		sb.append(", amount=");
		sb.append(amount);
		sb.append(", glDate=");
		sb.append(glDate);
		sb.append(", subLedger=");
		sb.append(subLedger);
		sb.append(", companyCostCenter=");
		sb.append(companyCostCenter);
		sb.append(", glAccount=");
		sb.append(glAccount);
		sb.append(", companyNo=");
		sb.append(companyNo);
		sb.append(", batchId=");
		sb.append(batchId);
		sb.append(", programType=");
		sb.append(programType);
		sb.append(", itemName=");
		sb.append(itemName);
		sb.append(", errorField=");
		sb.append(errorField);
		sb.append(", ivldAccrualInboundSid=");
		sb.append(ivldAccrualInboundSid);
		sb.append(", contractNo=");
		sb.append(contractNo);
		sb.append(", brandName=");
		sb.append(brandName);
		sb.append(", contractName=");
		sb.append(contractName);
		sb.append(", checkRecord=");
		sb.append(checkRecord);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public IvldAccrualInbound toEntityModel() {
		IvldAccrualInboundImpl ivldAccrualInboundImpl = new IvldAccrualInboundImpl();

		if (recordCreatedDate == null) {
			ivldAccrualInboundImpl.setRecordCreatedDate(StringPool.BLANK);
		}
		else {
			ivldAccrualInboundImpl.setRecordCreatedDate(recordCreatedDate);
		}

		if (accountId == null) {
			ivldAccrualInboundImpl.setAccountId(StringPool.BLANK);
		}
		else {
			ivldAccrualInboundImpl.setAccountId(accountId);
		}

		if (postingIndicator == null) {
			ivldAccrualInboundImpl.setPostingIndicator(StringPool.BLANK);
		}
		else {
			ivldAccrualInboundImpl.setPostingIndicator(postingIndicator);
		}

		if (itemId == null) {
			ivldAccrualInboundImpl.setItemId(StringPool.BLANK);
		}
		else {
			ivldAccrualInboundImpl.setItemId(itemId);
		}

		if (modifiedDate == Long.MIN_VALUE) {
			ivldAccrualInboundImpl.setModifiedDate(null);
		}
		else {
			ivldAccrualInboundImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (accrualPeriodEndDate == null) {
			ivldAccrualInboundImpl.setAccrualPeriodEndDate(StringPool.BLANK);
		}
		else {
			ivldAccrualInboundImpl.setAccrualPeriodEndDate(accrualPeriodEndDate);
		}

		if (itemIdentCodeQualifier == null) {
			ivldAccrualInboundImpl.setItemIdentCodeQualifier(StringPool.BLANK);
		}
		else {
			ivldAccrualInboundImpl.setItemIdentCodeQualifier(itemIdentCodeQualifier);
		}

		if (glCompanyMasterSid == null) {
			ivldAccrualInboundImpl.setGlCompanyMasterSid(StringPool.BLANK);
		}
		else {
			ivldAccrualInboundImpl.setGlCompanyMasterSid(glCompanyMasterSid);
		}

		if (salesMasterId == null) {
			ivldAccrualInboundImpl.setSalesMasterId(StringPool.BLANK);
		}
		else {
			ivldAccrualInboundImpl.setSalesMasterId(salesMasterId);
		}

		if (createdDate == Long.MIN_VALUE) {
			ivldAccrualInboundImpl.setCreatedDate(null);
		}
		else {
			ivldAccrualInboundImpl.setCreatedDate(new Date(createdDate));
		}

		if (createdBy == null) {
			ivldAccrualInboundImpl.setCreatedBy(StringPool.BLANK);
		}
		else {
			ivldAccrualInboundImpl.setCreatedBy(createdBy);
		}

		if (source == null) {
			ivldAccrualInboundImpl.setSource(StringPool.BLANK);
		}
		else {
			ivldAccrualInboundImpl.setSource(source);
		}

		if (accrualPeriodStartDate == null) {
			ivldAccrualInboundImpl.setAccrualPeriodStartDate(StringPool.BLANK);
		}
		else {
			ivldAccrualInboundImpl.setAccrualPeriodStartDate(accrualPeriodStartDate);
		}

		if (addChgDelIndicator == null) {
			ivldAccrualInboundImpl.setAddChgDelIndicator(StringPool.BLANK);
		}
		else {
			ivldAccrualInboundImpl.setAddChgDelIndicator(addChgDelIndicator);
		}

		if (subLedgerType == null) {
			ivldAccrualInboundImpl.setSubLedgerType(StringPool.BLANK);
		}
		else {
			ivldAccrualInboundImpl.setSubLedgerType(subLedgerType);
		}

		if (programNo == null) {
			ivldAccrualInboundImpl.setProgramNo(StringPool.BLANK);
		}
		else {
			ivldAccrualInboundImpl.setProgramNo(programNo);
		}

		if (documentType == null) {
			ivldAccrualInboundImpl.setDocumentType(StringPool.BLANK);
		}
		else {
			ivldAccrualInboundImpl.setDocumentType(documentType);
		}

		if (accrualIntfid == null) {
			ivldAccrualInboundImpl.setAccrualIntfid(StringPool.BLANK);
		}
		else {
			ivldAccrualInboundImpl.setAccrualIntfid(accrualIntfid);
		}

		if (glCompanyName == null) {
			ivldAccrualInboundImpl.setGlCompanyName(StringPool.BLANK);
		}
		else {
			ivldAccrualInboundImpl.setGlCompanyName(glCompanyName);
		}

		if (errorCode == null) {
			ivldAccrualInboundImpl.setErrorCode(StringPool.BLANK);
		}
		else {
			ivldAccrualInboundImpl.setErrorCode(errorCode);
		}

		if (intfInsertedDate == Long.MIN_VALUE) {
			ivldAccrualInboundImpl.setIntfInsertedDate(null);
		}
		else {
			ivldAccrualInboundImpl.setIntfInsertedDate(new Date(
					intfInsertedDate));
		}

		if (modifiedBy == null) {
			ivldAccrualInboundImpl.setModifiedBy(StringPool.BLANK);
		}
		else {
			ivldAccrualInboundImpl.setModifiedBy(modifiedBy);
		}

		if (categoryId == null) {
			ivldAccrualInboundImpl.setCategoryId(StringPool.BLANK);
		}
		else {
			ivldAccrualInboundImpl.setCategoryId(categoryId);
		}

		if (itemNo == null) {
			ivldAccrualInboundImpl.setItemNo(StringPool.BLANK);
		}
		else {
			ivldAccrualInboundImpl.setItemNo(itemNo);
		}

		if (reprocessedFlag == null) {
			ivldAccrualInboundImpl.setReprocessedFlag(StringPool.BLANK);
		}
		else {
			ivldAccrualInboundImpl.setReprocessedFlag(reprocessedFlag);
		}

		if (compIdentCodeQualifier == null) {
			ivldAccrualInboundImpl.setCompIdentCodeQualifier(StringPool.BLANK);
		}
		else {
			ivldAccrualInboundImpl.setCompIdentCodeQualifier(compIdentCodeQualifier);
		}

		if (acctIdentCodeQualifier == null) {
			ivldAccrualInboundImpl.setAcctIdentCodeQualifier(StringPool.BLANK);
		}
		else {
			ivldAccrualInboundImpl.setAcctIdentCodeQualifier(acctIdentCodeQualifier);
		}

		if (contractId == null) {
			ivldAccrualInboundImpl.setContractId(StringPool.BLANK);
		}
		else {
			ivldAccrualInboundImpl.setContractId(contractId);
		}

		if (accountNo == null) {
			ivldAccrualInboundImpl.setAccountNo(StringPool.BLANK);
		}
		else {
			ivldAccrualInboundImpl.setAccountNo(accountNo);
		}

		if (accrualId == null) {
			ivldAccrualInboundImpl.setAccrualId(StringPool.BLANK);
		}
		else {
			ivldAccrualInboundImpl.setAccrualId(accrualId);
		}

		if (reasonForFailure == null) {
			ivldAccrualInboundImpl.setReasonForFailure(StringPool.BLANK);
		}
		else {
			ivldAccrualInboundImpl.setReasonForFailure(reasonForFailure);
		}

		if (companyIdString == null) {
			ivldAccrualInboundImpl.setCompanyIdString(StringPool.BLANK);
		}
		else {
			ivldAccrualInboundImpl.setCompanyIdString(companyIdString);
		}

		if (accountName == null) {
			ivldAccrualInboundImpl.setAccountName(StringPool.BLANK);
		}
		else {
			ivldAccrualInboundImpl.setAccountName(accountName);
		}

		if (accrualType == null) {
			ivldAccrualInboundImpl.setAccrualType(StringPool.BLANK);
		}
		else {
			ivldAccrualInboundImpl.setAccrualType(accrualType);
		}

		if (postingDate == null) {
			ivldAccrualInboundImpl.setPostingDate(StringPool.BLANK);
		}
		else {
			ivldAccrualInboundImpl.setPostingDate(postingDate);
		}

		if (brandId == null) {
			ivldAccrualInboundImpl.setBrandId(StringPool.BLANK);
		}
		else {
			ivldAccrualInboundImpl.setBrandId(brandId);
		}

		if (provisionId == null) {
			ivldAccrualInboundImpl.setProvisionId(StringPool.BLANK);
		}
		else {
			ivldAccrualInboundImpl.setProvisionId(provisionId);
		}

		if (amount == null) {
			ivldAccrualInboundImpl.setAmount(StringPool.BLANK);
		}
		else {
			ivldAccrualInboundImpl.setAmount(amount);
		}

		if (glDate == null) {
			ivldAccrualInboundImpl.setGlDate(StringPool.BLANK);
		}
		else {
			ivldAccrualInboundImpl.setGlDate(glDate);
		}

		if (subLedger == null) {
			ivldAccrualInboundImpl.setSubLedger(StringPool.BLANK);
		}
		else {
			ivldAccrualInboundImpl.setSubLedger(subLedger);
		}

		if (companyCostCenter == null) {
			ivldAccrualInboundImpl.setCompanyCostCenter(StringPool.BLANK);
		}
		else {
			ivldAccrualInboundImpl.setCompanyCostCenter(companyCostCenter);
		}

		if (glAccount == null) {
			ivldAccrualInboundImpl.setGlAccount(StringPool.BLANK);
		}
		else {
			ivldAccrualInboundImpl.setGlAccount(glAccount);
		}

		if (companyNo == null) {
			ivldAccrualInboundImpl.setCompanyNo(StringPool.BLANK);
		}
		else {
			ivldAccrualInboundImpl.setCompanyNo(companyNo);
		}

		if (batchId == null) {
			ivldAccrualInboundImpl.setBatchId(StringPool.BLANK);
		}
		else {
			ivldAccrualInboundImpl.setBatchId(batchId);
		}

		if (programType == null) {
			ivldAccrualInboundImpl.setProgramType(StringPool.BLANK);
		}
		else {
			ivldAccrualInboundImpl.setProgramType(programType);
		}

		if (itemName == null) {
			ivldAccrualInboundImpl.setItemName(StringPool.BLANK);
		}
		else {
			ivldAccrualInboundImpl.setItemName(itemName);
		}

		if (errorField == null) {
			ivldAccrualInboundImpl.setErrorField(StringPool.BLANK);
		}
		else {
			ivldAccrualInboundImpl.setErrorField(errorField);
		}

		ivldAccrualInboundImpl.setIvldAccrualInboundSid(ivldAccrualInboundSid);

		if (contractNo == null) {
			ivldAccrualInboundImpl.setContractNo(StringPool.BLANK);
		}
		else {
			ivldAccrualInboundImpl.setContractNo(contractNo);
		}

		if (brandName == null) {
			ivldAccrualInboundImpl.setBrandName(StringPool.BLANK);
		}
		else {
			ivldAccrualInboundImpl.setBrandName(brandName);
		}

		if (contractName == null) {
			ivldAccrualInboundImpl.setContractName(StringPool.BLANK);
		}
		else {
			ivldAccrualInboundImpl.setContractName(contractName);
		}

		ivldAccrualInboundImpl.setCheckRecord(checkRecord);

		ivldAccrualInboundImpl.resetOriginalValues();

		return ivldAccrualInboundImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		recordCreatedDate = objectInput.readUTF();
		accountId = objectInput.readUTF();
		postingIndicator = objectInput.readUTF();
		itemId = objectInput.readUTF();
		modifiedDate = objectInput.readLong();
		accrualPeriodEndDate = objectInput.readUTF();
		itemIdentCodeQualifier = objectInput.readUTF();
		glCompanyMasterSid = objectInput.readUTF();
		salesMasterId = objectInput.readUTF();
		createdDate = objectInput.readLong();
		createdBy = objectInput.readUTF();
		source = objectInput.readUTF();
		accrualPeriodStartDate = objectInput.readUTF();
		addChgDelIndicator = objectInput.readUTF();
		subLedgerType = objectInput.readUTF();
		programNo = objectInput.readUTF();
		documentType = objectInput.readUTF();
		accrualIntfid = objectInput.readUTF();
		glCompanyName = objectInput.readUTF();
		errorCode = objectInput.readUTF();
		intfInsertedDate = objectInput.readLong();
		modifiedBy = objectInput.readUTF();
		categoryId = objectInput.readUTF();
		itemNo = objectInput.readUTF();
		reprocessedFlag = objectInput.readUTF();
		compIdentCodeQualifier = objectInput.readUTF();
		acctIdentCodeQualifier = objectInput.readUTF();
		contractId = objectInput.readUTF();
		accountNo = objectInput.readUTF();
		accrualId = objectInput.readUTF();
		reasonForFailure = objectInput.readUTF();
		companyIdString = objectInput.readUTF();
		accountName = objectInput.readUTF();
		accrualType = objectInput.readUTF();
		postingDate = objectInput.readUTF();
		brandId = objectInput.readUTF();
		provisionId = objectInput.readUTF();
		amount = objectInput.readUTF();
		glDate = objectInput.readUTF();
		subLedger = objectInput.readUTF();
		companyCostCenter = objectInput.readUTF();
		glAccount = objectInput.readUTF();
		companyNo = objectInput.readUTF();
		batchId = objectInput.readUTF();
		programType = objectInput.readUTF();
		itemName = objectInput.readUTF();
		errorField = objectInput.readUTF();

		ivldAccrualInboundSid = objectInput.readInt();
		contractNo = objectInput.readUTF();
		brandName = objectInput.readUTF();
		contractName = objectInput.readUTF();

		checkRecord = objectInput.readBoolean();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		if (recordCreatedDate == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(recordCreatedDate);
		}

		if (accountId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(accountId);
		}

		if (postingIndicator == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(postingIndicator);
		}

		if (itemId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(itemId);
		}

		objectOutput.writeLong(modifiedDate);

		if (accrualPeriodEndDate == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(accrualPeriodEndDate);
		}

		if (itemIdentCodeQualifier == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(itemIdentCodeQualifier);
		}

		if (glCompanyMasterSid == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(glCompanyMasterSid);
		}

		if (salesMasterId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(salesMasterId);
		}

		objectOutput.writeLong(createdDate);

		if (createdBy == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(createdBy);
		}

		if (source == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(source);
		}

		if (accrualPeriodStartDate == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(accrualPeriodStartDate);
		}

		if (addChgDelIndicator == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(addChgDelIndicator);
		}

		if (subLedgerType == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(subLedgerType);
		}

		if (programNo == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(programNo);
		}

		if (documentType == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(documentType);
		}

		if (accrualIntfid == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(accrualIntfid);
		}

		if (glCompanyName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(glCompanyName);
		}

		if (errorCode == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(errorCode);
		}

		objectOutput.writeLong(intfInsertedDate);

		if (modifiedBy == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(modifiedBy);
		}

		if (categoryId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(categoryId);
		}

		if (itemNo == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(itemNo);
		}

		if (reprocessedFlag == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(reprocessedFlag);
		}

		if (compIdentCodeQualifier == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(compIdentCodeQualifier);
		}

		if (acctIdentCodeQualifier == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(acctIdentCodeQualifier);
		}

		if (contractId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(contractId);
		}

		if (accountNo == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(accountNo);
		}

		if (accrualId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(accrualId);
		}

		if (reasonForFailure == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(reasonForFailure);
		}

		if (companyIdString == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(companyIdString);
		}

		if (accountName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(accountName);
		}

		if (accrualType == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(accrualType);
		}

		if (postingDate == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(postingDate);
		}

		if (brandId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(brandId);
		}

		if (provisionId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(provisionId);
		}

		if (amount == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(amount);
		}

		if (glDate == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(glDate);
		}

		if (subLedger == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(subLedger);
		}

		if (companyCostCenter == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(companyCostCenter);
		}

		if (glAccount == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(glAccount);
		}

		if (companyNo == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(companyNo);
		}

		if (batchId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(batchId);
		}

		if (programType == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(programType);
		}

		if (itemName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(itemName);
		}

		if (errorField == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(errorField);
		}

		objectOutput.writeInt(ivldAccrualInboundSid);

		if (contractNo == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(contractNo);
		}

		if (brandName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(brandName);
		}

		if (contractName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(contractName);
		}

		objectOutput.writeBoolean(checkRecord);
	}

	public String recordCreatedDate;
	public String accountId;
	public String postingIndicator;
	public String itemId;
	public long modifiedDate;
	public String accrualPeriodEndDate;
	public String itemIdentCodeQualifier;
	public String glCompanyMasterSid;
	public String salesMasterId;
	public long createdDate;
	public String createdBy;
	public String source;
	public String accrualPeriodStartDate;
	public String addChgDelIndicator;
	public String subLedgerType;
	public String programNo;
	public String documentType;
	public String accrualIntfid;
	public String glCompanyName;
	public String errorCode;
	public long intfInsertedDate;
	public String modifiedBy;
	public String categoryId;
	public String itemNo;
	public String reprocessedFlag;
	public String compIdentCodeQualifier;
	public String acctIdentCodeQualifier;
	public String contractId;
	public String accountNo;
	public String accrualId;
	public String reasonForFailure;
	public String companyIdString;
	public String accountName;
	public String accrualType;
	public String postingDate;
	public String brandId;
	public String provisionId;
	public String amount;
	public String glDate;
	public String subLedger;
	public String companyCostCenter;
	public String glAccount;
	public String companyNo;
	public String batchId;
	public String programType;
	public String itemName;
	public String errorField;
	public int ivldAccrualInboundSid;
	public String contractNo;
	public String brandName;
	public String contractName;
	public boolean checkRecord;
}