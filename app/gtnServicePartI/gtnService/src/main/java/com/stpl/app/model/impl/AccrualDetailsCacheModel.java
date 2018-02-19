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

import com.stpl.app.model.AccrualDetails;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing AccrualDetails in entity cache.
 *
 * @author
 * @see AccrualDetails
 * @generated
 */
@ProviderType
public class AccrualDetailsCacheModel implements CacheModel<AccrualDetails>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AccrualDetailsCacheModel)) {
			return false;
		}

		AccrualDetailsCacheModel accrualDetailsCacheModel = (AccrualDetailsCacheModel)obj;

		if (accrualDetailsSid == accrualDetailsCacheModel.accrualDetailsSid) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, accrualDetailsSid);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(109);

		sb.append("{accountId=");
		sb.append(accountId);
		sb.append(", recordCreatedDate=");
		sb.append(recordCreatedDate);
		sb.append(", postingIndicator=");
		sb.append(postingIndicator);
		sb.append(", brandName=");
		sb.append(brandName);
		sb.append(", accrualPeriodEndDate=");
		sb.append(accrualPeriodEndDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", salesMasterId=");
		sb.append(salesMasterId);
		sb.append(", source=");
		sb.append(source);
		sb.append(", contractMasterSid=");
		sb.append(contractMasterSid);
		sb.append(", accrualDetailsSid=");
		sb.append(accrualDetailsSid);
		sb.append(", documentType=");
		sb.append(documentType);
		sb.append(", inboundStatus=");
		sb.append(inboundStatus);
		sb.append(", modifiedBy=");
		sb.append(modifiedBy);
		sb.append(", deductionType=");
		sb.append(deductionType);
		sb.append(", companyMasterSid=");
		sb.append(companyMasterSid);
		sb.append(", contractName=");
		sb.append(contractName);
		sb.append(", accountNo=");
		sb.append(accountNo);
		sb.append(", accountName=");
		sb.append(accountName);
		sb.append(", versionNo=");
		sb.append(versionNo);
		sb.append(", provisionId=");
		sb.append(provisionId);
		sb.append(", companyStringIdentifierCodeQualifier=");
		sb.append(companyStringIdentifierCodeQualifier);
		sb.append(", amount=");
		sb.append(amount);
		sb.append(", subLedger=");
		sb.append(subLedger);
		sb.append(", recordLockStatus=");
		sb.append(recordLockStatus);
		sb.append(", itemIdentifierCodeQualifier=");
		sb.append(itemIdentifierCodeQualifier);
		sb.append(", companyNo=");
		sb.append(companyNo);
		sb.append(", postingStatus=");
		sb.append(postingStatus);
		sb.append(", itemName=");
		sb.append(itemName);
		sb.append(", rsModelSid=");
		sb.append(rsModelSid);
		sb.append(", itemMasterSid=");
		sb.append(itemMasterSid);
		sb.append(", itemId=");
		sb.append(itemId);
		sb.append(", brandMasterSid=");
		sb.append(brandMasterSid);
		sb.append(", glCompanyMasterSid=");
		sb.append(glCompanyMasterSid);
		sb.append(", createdBy=");
		sb.append(createdBy);
		sb.append(", createdDate=");
		sb.append(createdDate);
		sb.append(", accrualPeriodStartDate=");
		sb.append(accrualPeriodStartDate);
		sb.append(", subLedgerType=");
		sb.append(subLedgerType);
		sb.append(", programNo=");
		sb.append(programNo);
		sb.append(", categoryId=");
		sb.append(categoryId);
		sb.append(", itemNo=");
		sb.append(itemNo);
		sb.append(", deductionSubType=");
		sb.append(deductionSubType);
		sb.append(", acctIdentifierCodeQualifier=");
		sb.append(acctIdentifierCodeQualifier);
		sb.append(", contractId=");
		sb.append(contractId);
		sb.append(", accrualId=");
		sb.append(accrualId);
		sb.append(", companyStringId=");
		sb.append(companyStringId);
		sb.append(", accrualType=");
		sb.append(accrualType);
		sb.append(", brandId=");
		sb.append(brandId);
		sb.append(", postingDate=");
		sb.append(postingDate);
		sb.append(", glDate=");
		sb.append(glDate);
		sb.append(", companyCostCenter=");
		sb.append(companyCostCenter);
		sb.append(", glAccount=");
		sb.append(glAccount);
		sb.append(", batchId=");
		sb.append(batchId);
		sb.append(", programType=");
		sb.append(programType);
		sb.append(", contractNo=");
		sb.append(contractNo);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public AccrualDetails toEntityModel() {
		AccrualDetailsImpl accrualDetailsImpl = new AccrualDetailsImpl();

		if (accountId == null) {
			accrualDetailsImpl.setAccountId(StringPool.BLANK);
		}
		else {
			accrualDetailsImpl.setAccountId(accountId);
		}

		if (recordCreatedDate == Long.MIN_VALUE) {
			accrualDetailsImpl.setRecordCreatedDate(null);
		}
		else {
			accrualDetailsImpl.setRecordCreatedDate(new Date(recordCreatedDate));
		}

		if (postingIndicator == null) {
			accrualDetailsImpl.setPostingIndicator(StringPool.BLANK);
		}
		else {
			accrualDetailsImpl.setPostingIndicator(postingIndicator);
		}

		if (brandName == null) {
			accrualDetailsImpl.setBrandName(StringPool.BLANK);
		}
		else {
			accrualDetailsImpl.setBrandName(brandName);
		}

		if (accrualPeriodEndDate == Long.MIN_VALUE) {
			accrualDetailsImpl.setAccrualPeriodEndDate(null);
		}
		else {
			accrualDetailsImpl.setAccrualPeriodEndDate(new Date(
					accrualPeriodEndDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			accrualDetailsImpl.setModifiedDate(null);
		}
		else {
			accrualDetailsImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (salesMasterId == null) {
			accrualDetailsImpl.setSalesMasterId(StringPool.BLANK);
		}
		else {
			accrualDetailsImpl.setSalesMasterId(salesMasterId);
		}

		if (source == null) {
			accrualDetailsImpl.setSource(StringPool.BLANK);
		}
		else {
			accrualDetailsImpl.setSource(source);
		}

		accrualDetailsImpl.setContractMasterSid(contractMasterSid);
		accrualDetailsImpl.setAccrualDetailsSid(accrualDetailsSid);

		if (documentType == null) {
			accrualDetailsImpl.setDocumentType(StringPool.BLANK);
		}
		else {
			accrualDetailsImpl.setDocumentType(documentType);
		}

		if (inboundStatus == null) {
			accrualDetailsImpl.setInboundStatus(StringPool.BLANK);
		}
		else {
			accrualDetailsImpl.setInboundStatus(inboundStatus);
		}

		accrualDetailsImpl.setModifiedBy(modifiedBy);

		if (deductionType == null) {
			accrualDetailsImpl.setDeductionType(StringPool.BLANK);
		}
		else {
			accrualDetailsImpl.setDeductionType(deductionType);
		}

		accrualDetailsImpl.setCompanyMasterSid(companyMasterSid);

		if (contractName == null) {
			accrualDetailsImpl.setContractName(StringPool.BLANK);
		}
		else {
			accrualDetailsImpl.setContractName(contractName);
		}

		if (accountNo == null) {
			accrualDetailsImpl.setAccountNo(StringPool.BLANK);
		}
		else {
			accrualDetailsImpl.setAccountNo(accountNo);
		}

		if (accountName == null) {
			accrualDetailsImpl.setAccountName(StringPool.BLANK);
		}
		else {
			accrualDetailsImpl.setAccountName(accountName);
		}

		accrualDetailsImpl.setVersionNo(versionNo);

		if (provisionId == null) {
			accrualDetailsImpl.setProvisionId(StringPool.BLANK);
		}
		else {
			accrualDetailsImpl.setProvisionId(provisionId);
		}

		if (companyStringIdentifierCodeQualifier == null) {
			accrualDetailsImpl.setCompanyStringIdentifierCodeQualifier(StringPool.BLANK);
		}
		else {
			accrualDetailsImpl.setCompanyStringIdentifierCodeQualifier(companyStringIdentifierCodeQualifier);
		}

		accrualDetailsImpl.setAmount(amount);

		if (subLedger == null) {
			accrualDetailsImpl.setSubLedger(StringPool.BLANK);
		}
		else {
			accrualDetailsImpl.setSubLedger(subLedger);
		}

		accrualDetailsImpl.setRecordLockStatus(recordLockStatus);

		if (itemIdentifierCodeQualifier == null) {
			accrualDetailsImpl.setItemIdentifierCodeQualifier(StringPool.BLANK);
		}
		else {
			accrualDetailsImpl.setItemIdentifierCodeQualifier(itemIdentifierCodeQualifier);
		}

		if (companyNo == null) {
			accrualDetailsImpl.setCompanyNo(StringPool.BLANK);
		}
		else {
			accrualDetailsImpl.setCompanyNo(companyNo);
		}

		if (postingStatus == null) {
			accrualDetailsImpl.setPostingStatus(StringPool.BLANK);
		}
		else {
			accrualDetailsImpl.setPostingStatus(postingStatus);
		}

		if (itemName == null) {
			accrualDetailsImpl.setItemName(StringPool.BLANK);
		}
		else {
			accrualDetailsImpl.setItemName(itemName);
		}

		accrualDetailsImpl.setRsModelSid(rsModelSid);
		accrualDetailsImpl.setItemMasterSid(itemMasterSid);

		if (itemId == null) {
			accrualDetailsImpl.setItemId(StringPool.BLANK);
		}
		else {
			accrualDetailsImpl.setItemId(itemId);
		}

		accrualDetailsImpl.setBrandMasterSid(brandMasterSid);

		if (glCompanyMasterSid == null) {
			accrualDetailsImpl.setGlCompanyMasterSid(StringPool.BLANK);
		}
		else {
			accrualDetailsImpl.setGlCompanyMasterSid(glCompanyMasterSid);
		}

		accrualDetailsImpl.setCreatedBy(createdBy);

		if (createdDate == Long.MIN_VALUE) {
			accrualDetailsImpl.setCreatedDate(null);
		}
		else {
			accrualDetailsImpl.setCreatedDate(new Date(createdDate));
		}

		if (accrualPeriodStartDate == Long.MIN_VALUE) {
			accrualDetailsImpl.setAccrualPeriodStartDate(null);
		}
		else {
			accrualDetailsImpl.setAccrualPeriodStartDate(new Date(
					accrualPeriodStartDate));
		}

		if (subLedgerType == null) {
			accrualDetailsImpl.setSubLedgerType(StringPool.BLANK);
		}
		else {
			accrualDetailsImpl.setSubLedgerType(subLedgerType);
		}

		if (programNo == null) {
			accrualDetailsImpl.setProgramNo(StringPool.BLANK);
		}
		else {
			accrualDetailsImpl.setProgramNo(programNo);
		}

		if (categoryId == null) {
			accrualDetailsImpl.setCategoryId(StringPool.BLANK);
		}
		else {
			accrualDetailsImpl.setCategoryId(categoryId);
		}

		if (itemNo == null) {
			accrualDetailsImpl.setItemNo(StringPool.BLANK);
		}
		else {
			accrualDetailsImpl.setItemNo(itemNo);
		}

		if (deductionSubType == null) {
			accrualDetailsImpl.setDeductionSubType(StringPool.BLANK);
		}
		else {
			accrualDetailsImpl.setDeductionSubType(deductionSubType);
		}

		if (acctIdentifierCodeQualifier == null) {
			accrualDetailsImpl.setAcctIdentifierCodeQualifier(StringPool.BLANK);
		}
		else {
			accrualDetailsImpl.setAcctIdentifierCodeQualifier(acctIdentifierCodeQualifier);
		}

		if (contractId == null) {
			accrualDetailsImpl.setContractId(StringPool.BLANK);
		}
		else {
			accrualDetailsImpl.setContractId(contractId);
		}

		if (accrualId == null) {
			accrualDetailsImpl.setAccrualId(StringPool.BLANK);
		}
		else {
			accrualDetailsImpl.setAccrualId(accrualId);
		}

		if (companyStringId == null) {
			accrualDetailsImpl.setCompanyStringId(StringPool.BLANK);
		}
		else {
			accrualDetailsImpl.setCompanyStringId(companyStringId);
		}

		if (accrualType == null) {
			accrualDetailsImpl.setAccrualType(StringPool.BLANK);
		}
		else {
			accrualDetailsImpl.setAccrualType(accrualType);
		}

		if (brandId == null) {
			accrualDetailsImpl.setBrandId(StringPool.BLANK);
		}
		else {
			accrualDetailsImpl.setBrandId(brandId);
		}

		if (postingDate == Long.MIN_VALUE) {
			accrualDetailsImpl.setPostingDate(null);
		}
		else {
			accrualDetailsImpl.setPostingDate(new Date(postingDate));
		}

		if (glDate == Long.MIN_VALUE) {
			accrualDetailsImpl.setGlDate(null);
		}
		else {
			accrualDetailsImpl.setGlDate(new Date(glDate));
		}

		if (companyCostCenter == null) {
			accrualDetailsImpl.setCompanyCostCenter(StringPool.BLANK);
		}
		else {
			accrualDetailsImpl.setCompanyCostCenter(companyCostCenter);
		}

		if (glAccount == null) {
			accrualDetailsImpl.setGlAccount(StringPool.BLANK);
		}
		else {
			accrualDetailsImpl.setGlAccount(glAccount);
		}

		if (batchId == null) {
			accrualDetailsImpl.setBatchId(StringPool.BLANK);
		}
		else {
			accrualDetailsImpl.setBatchId(batchId);
		}

		accrualDetailsImpl.setProgramType(programType);

		if (contractNo == null) {
			accrualDetailsImpl.setContractNo(StringPool.BLANK);
		}
		else {
			accrualDetailsImpl.setContractNo(contractNo);
		}

		accrualDetailsImpl.resetOriginalValues();

		return accrualDetailsImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		accountId = objectInput.readUTF();
		recordCreatedDate = objectInput.readLong();
		postingIndicator = objectInput.readUTF();
		brandName = objectInput.readUTF();
		accrualPeriodEndDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		salesMasterId = objectInput.readUTF();
		source = objectInput.readUTF();

		contractMasterSid = objectInput.readInt();

		accrualDetailsSid = objectInput.readInt();
		documentType = objectInput.readUTF();
		inboundStatus = objectInput.readUTF();

		modifiedBy = objectInput.readInt();
		deductionType = objectInput.readUTF();

		companyMasterSid = objectInput.readInt();
		contractName = objectInput.readUTF();
		accountNo = objectInput.readUTF();
		accountName = objectInput.readUTF();

		versionNo = objectInput.readInt();
		provisionId = objectInput.readUTF();
		companyStringIdentifierCodeQualifier = objectInput.readUTF();

		amount = objectInput.readDouble();
		subLedger = objectInput.readUTF();

		recordLockStatus = objectInput.readBoolean();
		itemIdentifierCodeQualifier = objectInput.readUTF();
		companyNo = objectInput.readUTF();
		postingStatus = objectInput.readUTF();
		itemName = objectInput.readUTF();

		rsModelSid = objectInput.readInt();

		itemMasterSid = objectInput.readInt();
		itemId = objectInput.readUTF();

		brandMasterSid = objectInput.readInt();
		glCompanyMasterSid = objectInput.readUTF();

		createdBy = objectInput.readInt();
		createdDate = objectInput.readLong();
		accrualPeriodStartDate = objectInput.readLong();
		subLedgerType = objectInput.readUTF();
		programNo = objectInput.readUTF();
		categoryId = objectInput.readUTF();
		itemNo = objectInput.readUTF();
		deductionSubType = objectInput.readUTF();
		acctIdentifierCodeQualifier = objectInput.readUTF();
		contractId = objectInput.readUTF();
		accrualId = objectInput.readUTF();
		companyStringId = objectInput.readUTF();
		accrualType = objectInput.readUTF();
		brandId = objectInput.readUTF();
		postingDate = objectInput.readLong();
		glDate = objectInput.readLong();
		companyCostCenter = objectInput.readUTF();
		glAccount = objectInput.readUTF();
		batchId = objectInput.readUTF();

		programType = objectInput.readInt();
		contractNo = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		if (accountId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(accountId);
		}

		objectOutput.writeLong(recordCreatedDate);

		if (postingIndicator == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(postingIndicator);
		}

		if (brandName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(brandName);
		}

		objectOutput.writeLong(accrualPeriodEndDate);
		objectOutput.writeLong(modifiedDate);

		if (salesMasterId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(salesMasterId);
		}

		if (source == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(source);
		}

		objectOutput.writeInt(contractMasterSid);

		objectOutput.writeInt(accrualDetailsSid);

		if (documentType == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(documentType);
		}

		if (inboundStatus == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(inboundStatus);
		}

		objectOutput.writeInt(modifiedBy);

		if (deductionType == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(deductionType);
		}

		objectOutput.writeInt(companyMasterSid);

		if (contractName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(contractName);
		}

		if (accountNo == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(accountNo);
		}

		if (accountName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(accountName);
		}

		objectOutput.writeInt(versionNo);

		if (provisionId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(provisionId);
		}

		if (companyStringIdentifierCodeQualifier == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(companyStringIdentifierCodeQualifier);
		}

		objectOutput.writeDouble(amount);

		if (subLedger == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(subLedger);
		}

		objectOutput.writeBoolean(recordLockStatus);

		if (itemIdentifierCodeQualifier == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(itemIdentifierCodeQualifier);
		}

		if (companyNo == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(companyNo);
		}

		if (postingStatus == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(postingStatus);
		}

		if (itemName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(itemName);
		}

		objectOutput.writeInt(rsModelSid);

		objectOutput.writeInt(itemMasterSid);

		if (itemId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(itemId);
		}

		objectOutput.writeInt(brandMasterSid);

		if (glCompanyMasterSid == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(glCompanyMasterSid);
		}

		objectOutput.writeInt(createdBy);
		objectOutput.writeLong(createdDate);
		objectOutput.writeLong(accrualPeriodStartDate);

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

		if (deductionSubType == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(deductionSubType);
		}

		if (acctIdentifierCodeQualifier == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(acctIdentifierCodeQualifier);
		}

		if (contractId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(contractId);
		}

		if (accrualId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(accrualId);
		}

		if (companyStringId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(companyStringId);
		}

		if (accrualType == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(accrualType);
		}

		if (brandId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(brandId);
		}

		objectOutput.writeLong(postingDate);
		objectOutput.writeLong(glDate);

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

		if (batchId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(batchId);
		}

		objectOutput.writeInt(programType);

		if (contractNo == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(contractNo);
		}
	}

	public String accountId;
	public long recordCreatedDate;
	public String postingIndicator;
	public String brandName;
	public long accrualPeriodEndDate;
	public long modifiedDate;
	public String salesMasterId;
	public String source;
	public int contractMasterSid;
	public int accrualDetailsSid;
	public String documentType;
	public String inboundStatus;
	public int modifiedBy;
	public String deductionType;
	public int companyMasterSid;
	public String contractName;
	public String accountNo;
	public String accountName;
	public int versionNo;
	public String provisionId;
	public String companyStringIdentifierCodeQualifier;
	public double amount;
	public String subLedger;
	public boolean recordLockStatus;
	public String itemIdentifierCodeQualifier;
	public String companyNo;
	public String postingStatus;
	public String itemName;
	public int rsModelSid;
	public int itemMasterSid;
	public String itemId;
	public int brandMasterSid;
	public String glCompanyMasterSid;
	public int createdBy;
	public long createdDate;
	public long accrualPeriodStartDate;
	public String subLedgerType;
	public String programNo;
	public String categoryId;
	public String itemNo;
	public String deductionSubType;
	public String acctIdentifierCodeQualifier;
	public String contractId;
	public String accrualId;
	public String companyStringId;
	public String accrualType;
	public String brandId;
	public long postingDate;
	public long glDate;
	public String companyCostCenter;
	public String glAccount;
	public String batchId;
	public int programType;
	public String contractNo;
}