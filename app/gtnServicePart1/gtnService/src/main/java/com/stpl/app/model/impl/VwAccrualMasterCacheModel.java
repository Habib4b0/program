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

import com.stpl.app.model.VwAccrualMaster;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing VwAccrualMaster in entity cache.
 *
 * @author
 * @see VwAccrualMaster
 * @generated
 */
@ProviderType
public class VwAccrualMasterCacheModel implements CacheModel<VwAccrualMaster>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof VwAccrualMasterCacheModel)) {
			return false;
		}

		VwAccrualMasterCacheModel vwAccrualMasterCacheModel = (VwAccrualMasterCacheModel)obj;

		if (accrualMasterSid == vwAccrualMasterCacheModel.accrualMasterSid) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, accrualMasterSid);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(75);

		sb.append("{itemQualifier=");
		sb.append(itemQualifier);
		sb.append(", businessUnitQualifier=");
		sb.append(businessUnitQualifier);
		sb.append(", itemNo=");
		sb.append(itemNo);
		sb.append(", postingIndicator=");
		sb.append(postingIndicator);
		sb.append(", createdDate=");
		sb.append(createdDate);
		sb.append(", costCenter=");
		sb.append(costCenter);
		sb.append(", subLedger=");
		sb.append(subLedger);
		sb.append(", accrualPeriodEd=");
		sb.append(accrualPeriodEd);
		sb.append(", accrualId=");
		sb.append(accrualId);
		sb.append(", companyQualifier=");
		sb.append(companyQualifier);
		sb.append(", contractNo=");
		sb.append(contractNo);
		sb.append(", batchId=");
		sb.append(batchId);
		sb.append(", itemName=");
		sb.append(itemName);
		sb.append(", brandId=");
		sb.append(brandId);
		sb.append(", postingDate=");
		sb.append(postingDate);
		sb.append(", businessUnitName=");
		sb.append(businessUnitName);
		sb.append(", salesId=");
		sb.append(salesId);
		sb.append(", amount=");
		sb.append(amount);
		sb.append(", businessUnitNo=");
		sb.append(businessUnitNo);
		sb.append(", subLedgerType=");
		sb.append(subLedgerType);
		sb.append(", documentType=");
		sb.append(documentType);
		sb.append(", accuralType=");
		sb.append(accuralType);
		sb.append(", createdBy=");
		sb.append(createdBy);
		sb.append(", programNo=");
		sb.append(programNo);
		sb.append(", customerId=");
		sb.append(customerId);
		sb.append(", itemId=");
		sb.append(itemId);
		sb.append(", accrualMasterSid=");
		sb.append(accrualMasterSid);
		sb.append(", contractId=");
		sb.append(contractId);
		sb.append(", contractName=");
		sb.append(contractName);
		sb.append(", glAccount=");
		sb.append(glAccount);
		sb.append(", glDate=");
		sb.append(glDate);
		sb.append(", businessUnitId=");
		sb.append(businessUnitId);
		sb.append(", programType=");
		sb.append(programType);
		sb.append(", customerName=");
		sb.append(customerName);
		sb.append(", customerNo=");
		sb.append(customerNo);
		sb.append(", source=");
		sb.append(source);
		sb.append(", accrualPeriodSd=");
		sb.append(accrualPeriodSd);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public VwAccrualMaster toEntityModel() {
		VwAccrualMasterImpl vwAccrualMasterImpl = new VwAccrualMasterImpl();

		if (itemQualifier == null) {
			vwAccrualMasterImpl.setItemQualifier(StringPool.BLANK);
		}
		else {
			vwAccrualMasterImpl.setItemQualifier(itemQualifier);
		}

		if (businessUnitQualifier == null) {
			vwAccrualMasterImpl.setBusinessUnitQualifier(StringPool.BLANK);
		}
		else {
			vwAccrualMasterImpl.setBusinessUnitQualifier(businessUnitQualifier);
		}

		if (itemNo == null) {
			vwAccrualMasterImpl.setItemNo(StringPool.BLANK);
		}
		else {
			vwAccrualMasterImpl.setItemNo(itemNo);
		}

		if (postingIndicator == null) {
			vwAccrualMasterImpl.setPostingIndicator(StringPool.BLANK);
		}
		else {
			vwAccrualMasterImpl.setPostingIndicator(postingIndicator);
		}

		if (createdDate == Long.MIN_VALUE) {
			vwAccrualMasterImpl.setCreatedDate(null);
		}
		else {
			vwAccrualMasterImpl.setCreatedDate(new Date(createdDate));
		}

		if (costCenter == null) {
			vwAccrualMasterImpl.setCostCenter(StringPool.BLANK);
		}
		else {
			vwAccrualMasterImpl.setCostCenter(costCenter);
		}

		if (subLedger == null) {
			vwAccrualMasterImpl.setSubLedger(StringPool.BLANK);
		}
		else {
			vwAccrualMasterImpl.setSubLedger(subLedger);
		}

		if (accrualPeriodEd == Long.MIN_VALUE) {
			vwAccrualMasterImpl.setAccrualPeriodEd(null);
		}
		else {
			vwAccrualMasterImpl.setAccrualPeriodEd(new Date(accrualPeriodEd));
		}

		if (accrualId == null) {
			vwAccrualMasterImpl.setAccrualId(StringPool.BLANK);
		}
		else {
			vwAccrualMasterImpl.setAccrualId(accrualId);
		}

		if (companyQualifier == null) {
			vwAccrualMasterImpl.setCompanyQualifier(StringPool.BLANK);
		}
		else {
			vwAccrualMasterImpl.setCompanyQualifier(companyQualifier);
		}

		if (contractNo == null) {
			vwAccrualMasterImpl.setContractNo(StringPool.BLANK);
		}
		else {
			vwAccrualMasterImpl.setContractNo(contractNo);
		}

		if (batchId == null) {
			vwAccrualMasterImpl.setBatchId(StringPool.BLANK);
		}
		else {
			vwAccrualMasterImpl.setBatchId(batchId);
		}

		if (itemName == null) {
			vwAccrualMasterImpl.setItemName(StringPool.BLANK);
		}
		else {
			vwAccrualMasterImpl.setItemName(itemName);
		}

		if (brandId == null) {
			vwAccrualMasterImpl.setBrandId(StringPool.BLANK);
		}
		else {
			vwAccrualMasterImpl.setBrandId(brandId);
		}

		if (postingDate == Long.MIN_VALUE) {
			vwAccrualMasterImpl.setPostingDate(null);
		}
		else {
			vwAccrualMasterImpl.setPostingDate(new Date(postingDate));
		}

		if (businessUnitName == null) {
			vwAccrualMasterImpl.setBusinessUnitName(StringPool.BLANK);
		}
		else {
			vwAccrualMasterImpl.setBusinessUnitName(businessUnitName);
		}

		if (salesId == null) {
			vwAccrualMasterImpl.setSalesId(StringPool.BLANK);
		}
		else {
			vwAccrualMasterImpl.setSalesId(salesId);
		}

		vwAccrualMasterImpl.setAmount(amount);

		if (businessUnitNo == null) {
			vwAccrualMasterImpl.setBusinessUnitNo(StringPool.BLANK);
		}
		else {
			vwAccrualMasterImpl.setBusinessUnitNo(businessUnitNo);
		}

		if (subLedgerType == null) {
			vwAccrualMasterImpl.setSubLedgerType(StringPool.BLANK);
		}
		else {
			vwAccrualMasterImpl.setSubLedgerType(subLedgerType);
		}

		if (documentType == null) {
			vwAccrualMasterImpl.setDocumentType(StringPool.BLANK);
		}
		else {
			vwAccrualMasterImpl.setDocumentType(documentType);
		}

		if (accuralType == null) {
			vwAccrualMasterImpl.setAccuralType(StringPool.BLANK);
		}
		else {
			vwAccrualMasterImpl.setAccuralType(accuralType);
		}

		vwAccrualMasterImpl.setCreatedBy(createdBy);

		if (programNo == null) {
			vwAccrualMasterImpl.setProgramNo(StringPool.BLANK);
		}
		else {
			vwAccrualMasterImpl.setProgramNo(programNo);
		}

		if (customerId == null) {
			vwAccrualMasterImpl.setCustomerId(StringPool.BLANK);
		}
		else {
			vwAccrualMasterImpl.setCustomerId(customerId);
		}

		if (itemId == null) {
			vwAccrualMasterImpl.setItemId(StringPool.BLANK);
		}
		else {
			vwAccrualMasterImpl.setItemId(itemId);
		}

		vwAccrualMasterImpl.setAccrualMasterSid(accrualMasterSid);

		if (contractId == null) {
			vwAccrualMasterImpl.setContractId(StringPool.BLANK);
		}
		else {
			vwAccrualMasterImpl.setContractId(contractId);
		}

		if (contractName == null) {
			vwAccrualMasterImpl.setContractName(StringPool.BLANK);
		}
		else {
			vwAccrualMasterImpl.setContractName(contractName);
		}

		if (glAccount == null) {
			vwAccrualMasterImpl.setGlAccount(StringPool.BLANK);
		}
		else {
			vwAccrualMasterImpl.setGlAccount(glAccount);
		}

		if (glDate == Long.MIN_VALUE) {
			vwAccrualMasterImpl.setGlDate(null);
		}
		else {
			vwAccrualMasterImpl.setGlDate(new Date(glDate));
		}

		if (businessUnitId == null) {
			vwAccrualMasterImpl.setBusinessUnitId(StringPool.BLANK);
		}
		else {
			vwAccrualMasterImpl.setBusinessUnitId(businessUnitId);
		}

		if (programType == null) {
			vwAccrualMasterImpl.setProgramType(StringPool.BLANK);
		}
		else {
			vwAccrualMasterImpl.setProgramType(programType);
		}

		if (customerName == null) {
			vwAccrualMasterImpl.setCustomerName(StringPool.BLANK);
		}
		else {
			vwAccrualMasterImpl.setCustomerName(customerName);
		}

		if (customerNo == null) {
			vwAccrualMasterImpl.setCustomerNo(StringPool.BLANK);
		}
		else {
			vwAccrualMasterImpl.setCustomerNo(customerNo);
		}

		if (source == null) {
			vwAccrualMasterImpl.setSource(StringPool.BLANK);
		}
		else {
			vwAccrualMasterImpl.setSource(source);
		}

		if (accrualPeriodSd == Long.MIN_VALUE) {
			vwAccrualMasterImpl.setAccrualPeriodSd(null);
		}
		else {
			vwAccrualMasterImpl.setAccrualPeriodSd(new Date(accrualPeriodSd));
		}

		vwAccrualMasterImpl.resetOriginalValues();

		return vwAccrualMasterImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		itemQualifier = objectInput.readUTF();
		businessUnitQualifier = objectInput.readUTF();
		itemNo = objectInput.readUTF();
		postingIndicator = objectInput.readUTF();
		createdDate = objectInput.readLong();
		costCenter = objectInput.readUTF();
		subLedger = objectInput.readUTF();
		accrualPeriodEd = objectInput.readLong();
		accrualId = objectInput.readUTF();
		companyQualifier = objectInput.readUTF();
		contractNo = objectInput.readUTF();
		batchId = objectInput.readUTF();
		itemName = objectInput.readUTF();
		brandId = objectInput.readUTF();
		postingDate = objectInput.readLong();
		businessUnitName = objectInput.readUTF();
		salesId = objectInput.readUTF();

		amount = objectInput.readDouble();
		businessUnitNo = objectInput.readUTF();
		subLedgerType = objectInput.readUTF();
		documentType = objectInput.readUTF();
		accuralType = objectInput.readUTF();

		createdBy = objectInput.readInt();
		programNo = objectInput.readUTF();
		customerId = objectInput.readUTF();
		itemId = objectInput.readUTF();

		accrualMasterSid = objectInput.readInt();
		contractId = objectInput.readUTF();
		contractName = objectInput.readUTF();
		glAccount = objectInput.readUTF();
		glDate = objectInput.readLong();
		businessUnitId = objectInput.readUTF();
		programType = objectInput.readUTF();
		customerName = objectInput.readUTF();
		customerNo = objectInput.readUTF();
		source = objectInput.readUTF();
		accrualPeriodSd = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		if (itemQualifier == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(itemQualifier);
		}

		if (businessUnitQualifier == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(businessUnitQualifier);
		}

		if (itemNo == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(itemNo);
		}

		if (postingIndicator == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(postingIndicator);
		}

		objectOutput.writeLong(createdDate);

		if (costCenter == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(costCenter);
		}

		if (subLedger == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(subLedger);
		}

		objectOutput.writeLong(accrualPeriodEd);

		if (accrualId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(accrualId);
		}

		if (companyQualifier == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(companyQualifier);
		}

		if (contractNo == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(contractNo);
		}

		if (batchId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(batchId);
		}

		if (itemName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(itemName);
		}

		if (brandId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(brandId);
		}

		objectOutput.writeLong(postingDate);

		if (businessUnitName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(businessUnitName);
		}

		if (salesId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(salesId);
		}

		objectOutput.writeDouble(amount);

		if (businessUnitNo == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(businessUnitNo);
		}

		if (subLedgerType == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(subLedgerType);
		}

		if (documentType == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(documentType);
		}

		if (accuralType == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(accuralType);
		}

		objectOutput.writeInt(createdBy);

		if (programNo == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(programNo);
		}

		if (customerId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(customerId);
		}

		if (itemId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(itemId);
		}

		objectOutput.writeInt(accrualMasterSid);

		if (contractId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(contractId);
		}

		if (contractName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(contractName);
		}

		if (glAccount == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(glAccount);
		}

		objectOutput.writeLong(glDate);

		if (businessUnitId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(businessUnitId);
		}

		if (programType == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(programType);
		}

		if (customerName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(customerName);
		}

		if (customerNo == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(customerNo);
		}

		if (source == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(source);
		}

		objectOutput.writeLong(accrualPeriodSd);
	}

	public String itemQualifier;
	public String businessUnitQualifier;
	public String itemNo;
	public String postingIndicator;
	public long createdDate;
	public String costCenter;
	public String subLedger;
	public long accrualPeriodEd;
	public String accrualId;
	public String companyQualifier;
	public String contractNo;
	public String batchId;
	public String itemName;
	public String brandId;
	public long postingDate;
	public String businessUnitName;
	public String salesId;
	public double amount;
	public String businessUnitNo;
	public String subLedgerType;
	public String documentType;
	public String accuralType;
	public int createdBy;
	public String programNo;
	public String customerId;
	public String itemId;
	public int accrualMasterSid;
	public String contractId;
	public String contractName;
	public String glAccount;
	public long glDate;
	public String businessUnitId;
	public String programType;
	public String customerName;
	public String customerNo;
	public String source;
	public long accrualPeriodSd;
}