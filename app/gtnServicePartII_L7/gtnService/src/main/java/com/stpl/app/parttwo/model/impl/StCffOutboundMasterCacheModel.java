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

import com.stpl.app.parttwo.model.StCffOutboundMaster;
import com.stpl.app.parttwo.service.persistence.StCffOutboundMasterPK;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing StCffOutboundMaster in entity cache.
 *
 * @author
 * @see StCffOutboundMaster
 * @generated
 */
@ProviderType
public class StCffOutboundMasterCacheModel implements CacheModel<StCffOutboundMaster>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof StCffOutboundMasterCacheModel)) {
			return false;
		}

		StCffOutboundMasterCacheModel stCffOutboundMasterCacheModel = (StCffOutboundMasterCacheModel)obj;

		if (stCffOutboundMasterPK.equals(
					stCffOutboundMasterCacheModel.stCffOutboundMasterPK)) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, stCffOutboundMasterPK);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(143);

		sb.append("{etlCheckRecord=");
		sb.append(etlCheckRecord);
		sb.append(", customerName=");
		sb.append(customerName);
		sb.append(", contractHolderId=");
		sb.append(contractHolderId);
		sb.append(", businessUnitNo=");
		sb.append(businessUnitNo);
		sb.append(", year=");
		sb.append(year);
		sb.append(", financialForecastApprovalDate=");
		sb.append(financialForecastApprovalDate);
		sb.append(", deductionId=");
		sb.append(deductionId);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", deductionPerUnit=");
		sb.append(deductionPerUnit);
		sb.append(", cogsPerUnit=");
		sb.append(cogsPerUnit);
		sb.append(", contractType=");
		sb.append(contractType);
		sb.append(", source=");
		sb.append(source);
		sb.append(", businessUnitName=");
		sb.append(businessUnitName);
		sb.append(", contractMasterSid=");
		sb.append(contractMasterSid);
		sb.append(", financialForecastId=");
		sb.append(financialForecastId);
		sb.append(", projectId=");
		sb.append(projectId);
		sb.append(", customerNo=");
		sb.append(customerNo);
		sb.append(", modifiedBy=");
		sb.append(modifiedBy);
		sb.append(", salesDollars=");
		sb.append(salesDollars);
		sb.append(", month=");
		sb.append(month);
		sb.append(", cffDetailsSid=");
		sb.append(cffDetailsSid);
		sb.append(", type=");
		sb.append(type);
		sb.append(", deductionType=");
		sb.append(deductionType);
		sb.append(", companyMasterSid=");
		sb.append(companyMasterSid);
		sb.append(", checkRecord=");
		sb.append(checkRecord);
		sb.append(", contractName=");
		sb.append(contractName);
		sb.append(", deductionRate=");
		sb.append(deductionRate);
		sb.append(", deductionCategory=");
		sb.append(deductionCategory);
		sb.append(", cogsAmount=");
		sb.append(cogsAmount);
		sb.append(", deductionNo=");
		sb.append(deductionNo);
		sb.append(", financialForecastCreationDate=");
		sb.append(financialForecastCreationDate);
		sb.append(", companyNo=");
		sb.append(companyNo);
		sb.append(", salesUnits=");
		sb.append(salesUnits);
		sb.append(", sessionId=");
		sb.append(sessionId);
		sb.append(", itemName=");
		sb.append(itemName);
		sb.append(", deductionInclusion=");
		sb.append(deductionInclusion);
		sb.append(", rsModelSid=");
		sb.append(rsModelSid);
		sb.append(", contractHolderName=");
		sb.append(contractHolderName);
		sb.append(", itemMasterSid=");
		sb.append(itemMasterSid);
		sb.append(", companyName=");
		sb.append(companyName);
		sb.append(", customerId=");
		sb.append(customerId);
		sb.append(", itemId=");
		sb.append(itemId);
		sb.append(", netProfitDollars=");
		sb.append(netProfitDollars);
		sb.append(", glCompanyMasterSid=");
		sb.append(glCompanyMasterSid);
		sb.append(", createdDate=");
		sb.append(createdDate);
		sb.append(", createdBy=");
		sb.append(createdBy);
		sb.append(", deductionCategory1=");
		sb.append(deductionCategory1);
		sb.append(", deductionCategory2=");
		sb.append(deductionCategory2);
		sb.append(", contractHolderNo=");
		sb.append(contractHolderNo);
		sb.append(", deductionCategory3=");
		sb.append(deductionCategory3);
		sb.append(", itemNo=");
		sb.append(itemNo);
		sb.append(", deductionCategory4=");
		sb.append(deductionCategory4);
		sb.append(", deductionCategory5=");
		sb.append(deductionCategory5);
		sb.append(", deductionCategory6=");
		sb.append(deductionCategory6);
		sb.append(", contractId=");
		sb.append(contractId);
		sb.append(", deductionProgram=");
		sb.append(deductionProgram);
		sb.append(", businessUnitId=");
		sb.append(businessUnitId);
		sb.append(", projectionName=");
		sb.append(projectionName);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", outboundStatus=");
		sb.append(outboundStatus);
		sb.append(", originalBatchId=");
		sb.append(originalBatchId);
		sb.append(", deductionName=");
		sb.append(deductionName);
		sb.append(", netProfitPerUnit=");
		sb.append(netProfitPerUnit);
		sb.append(", periodSid=");
		sb.append(periodSid);
		sb.append(", salesInclusion=");
		sb.append(salesInclusion);
		sb.append(", batchId=");
		sb.append(batchId);
		sb.append(", financialForecastName=");
		sb.append(financialForecastName);
		sb.append(", netSalesDollar=");
		sb.append(netSalesDollar);
		sb.append(", deductionDollars=");
		sb.append(deductionDollars);
		sb.append(", contractNo=");
		sb.append(contractNo);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public StCffOutboundMaster toEntityModel() {
		StCffOutboundMasterImpl stCffOutboundMasterImpl = new StCffOutboundMasterImpl();

		stCffOutboundMasterImpl.setEtlCheckRecord(etlCheckRecord);

		if (customerName == null) {
			stCffOutboundMasterImpl.setCustomerName(StringPool.BLANK);
		}
		else {
			stCffOutboundMasterImpl.setCustomerName(customerName);
		}

		if (contractHolderId == null) {
			stCffOutboundMasterImpl.setContractHolderId(StringPool.BLANK);
		}
		else {
			stCffOutboundMasterImpl.setContractHolderId(contractHolderId);
		}

		if (businessUnitNo == null) {
			stCffOutboundMasterImpl.setBusinessUnitNo(StringPool.BLANK);
		}
		else {
			stCffOutboundMasterImpl.setBusinessUnitNo(businessUnitNo);
		}

		if (year == null) {
			stCffOutboundMasterImpl.setYear(StringPool.BLANK);
		}
		else {
			stCffOutboundMasterImpl.setYear(year);
		}

		if (financialForecastApprovalDate == Long.MIN_VALUE) {
			stCffOutboundMasterImpl.setFinancialForecastApprovalDate(null);
		}
		else {
			stCffOutboundMasterImpl.setFinancialForecastApprovalDate(new Date(
					financialForecastApprovalDate));
		}

		if (deductionId == null) {
			stCffOutboundMasterImpl.setDeductionId(StringPool.BLANK);
		}
		else {
			stCffOutboundMasterImpl.setDeductionId(deductionId);
		}

		if (modifiedDate == Long.MIN_VALUE) {
			stCffOutboundMasterImpl.setModifiedDate(null);
		}
		else {
			stCffOutboundMasterImpl.setModifiedDate(new Date(modifiedDate));
		}

		stCffOutboundMasterImpl.setDeductionPerUnit(deductionPerUnit);
		stCffOutboundMasterImpl.setCogsPerUnit(cogsPerUnit);

		if (contractType == null) {
			stCffOutboundMasterImpl.setContractType(StringPool.BLANK);
		}
		else {
			stCffOutboundMasterImpl.setContractType(contractType);
		}

		if (source == null) {
			stCffOutboundMasterImpl.setSource(StringPool.BLANK);
		}
		else {
			stCffOutboundMasterImpl.setSource(source);
		}

		if (businessUnitName == null) {
			stCffOutboundMasterImpl.setBusinessUnitName(StringPool.BLANK);
		}
		else {
			stCffOutboundMasterImpl.setBusinessUnitName(businessUnitName);
		}

		stCffOutboundMasterImpl.setContractMasterSid(contractMasterSid);

		if (financialForecastId == null) {
			stCffOutboundMasterImpl.setFinancialForecastId(StringPool.BLANK);
		}
		else {
			stCffOutboundMasterImpl.setFinancialForecastId(financialForecastId);
		}

		if (projectId == null) {
			stCffOutboundMasterImpl.setProjectId(StringPool.BLANK);
		}
		else {
			stCffOutboundMasterImpl.setProjectId(projectId);
		}

		if (customerNo == null) {
			stCffOutboundMasterImpl.setCustomerNo(StringPool.BLANK);
		}
		else {
			stCffOutboundMasterImpl.setCustomerNo(customerNo);
		}

		if (modifiedBy == null) {
			stCffOutboundMasterImpl.setModifiedBy(StringPool.BLANK);
		}
		else {
			stCffOutboundMasterImpl.setModifiedBy(modifiedBy);
		}

		stCffOutboundMasterImpl.setSalesDollars(salesDollars);
		stCffOutboundMasterImpl.setMonth(month);
		stCffOutboundMasterImpl.setCffDetailsSid(cffDetailsSid);

		if (type == null) {
			stCffOutboundMasterImpl.setType(StringPool.BLANK);
		}
		else {
			stCffOutboundMasterImpl.setType(type);
		}

		if (deductionType == null) {
			stCffOutboundMasterImpl.setDeductionType(StringPool.BLANK);
		}
		else {
			stCffOutboundMasterImpl.setDeductionType(deductionType);
		}

		stCffOutboundMasterImpl.setCompanyMasterSid(companyMasterSid);
		stCffOutboundMasterImpl.setCheckRecord(checkRecord);

		if (contractName == null) {
			stCffOutboundMasterImpl.setContractName(StringPool.BLANK);
		}
		else {
			stCffOutboundMasterImpl.setContractName(contractName);
		}

		stCffOutboundMasterImpl.setDeductionRate(deductionRate);

		if (deductionCategory == null) {
			stCffOutboundMasterImpl.setDeductionCategory(StringPool.BLANK);
		}
		else {
			stCffOutboundMasterImpl.setDeductionCategory(deductionCategory);
		}

		stCffOutboundMasterImpl.setCogsAmount(cogsAmount);

		if (deductionNo == null) {
			stCffOutboundMasterImpl.setDeductionNo(StringPool.BLANK);
		}
		else {
			stCffOutboundMasterImpl.setDeductionNo(deductionNo);
		}

		if (financialForecastCreationDate == Long.MIN_VALUE) {
			stCffOutboundMasterImpl.setFinancialForecastCreationDate(null);
		}
		else {
			stCffOutboundMasterImpl.setFinancialForecastCreationDate(new Date(
					financialForecastCreationDate));
		}

		if (companyNo == null) {
			stCffOutboundMasterImpl.setCompanyNo(StringPool.BLANK);
		}
		else {
			stCffOutboundMasterImpl.setCompanyNo(companyNo);
		}

		stCffOutboundMasterImpl.setSalesUnits(salesUnits);

		if (sessionId == null) {
			stCffOutboundMasterImpl.setSessionId(StringPool.BLANK);
		}
		else {
			stCffOutboundMasterImpl.setSessionId(sessionId);
		}

		if (itemName == null) {
			stCffOutboundMasterImpl.setItemName(StringPool.BLANK);
		}
		else {
			stCffOutboundMasterImpl.setItemName(itemName);
		}

		if (deductionInclusion == null) {
			stCffOutboundMasterImpl.setDeductionInclusion(StringPool.BLANK);
		}
		else {
			stCffOutboundMasterImpl.setDeductionInclusion(deductionInclusion);
		}

		stCffOutboundMasterImpl.setRsModelSid(rsModelSid);

		if (contractHolderName == null) {
			stCffOutboundMasterImpl.setContractHolderName(StringPool.BLANK);
		}
		else {
			stCffOutboundMasterImpl.setContractHolderName(contractHolderName);
		}

		stCffOutboundMasterImpl.setItemMasterSid(itemMasterSid);

		if (companyName == null) {
			stCffOutboundMasterImpl.setCompanyName(StringPool.BLANK);
		}
		else {
			stCffOutboundMasterImpl.setCompanyName(companyName);
		}

		if (customerId == null) {
			stCffOutboundMasterImpl.setCustomerId(StringPool.BLANK);
		}
		else {
			stCffOutboundMasterImpl.setCustomerId(customerId);
		}

		if (itemId == null) {
			stCffOutboundMasterImpl.setItemId(StringPool.BLANK);
		}
		else {
			stCffOutboundMasterImpl.setItemId(itemId);
		}

		stCffOutboundMasterImpl.setNetProfitDollars(netProfitDollars);
		stCffOutboundMasterImpl.setGlCompanyMasterSid(glCompanyMasterSid);

		if (createdDate == Long.MIN_VALUE) {
			stCffOutboundMasterImpl.setCreatedDate(null);
		}
		else {
			stCffOutboundMasterImpl.setCreatedDate(new Date(createdDate));
		}

		if (createdBy == null) {
			stCffOutboundMasterImpl.setCreatedBy(StringPool.BLANK);
		}
		else {
			stCffOutboundMasterImpl.setCreatedBy(createdBy);
		}

		if (deductionCategory1 == null) {
			stCffOutboundMasterImpl.setDeductionCategory1(StringPool.BLANK);
		}
		else {
			stCffOutboundMasterImpl.setDeductionCategory1(deductionCategory1);
		}

		if (deductionCategory2 == null) {
			stCffOutboundMasterImpl.setDeductionCategory2(StringPool.BLANK);
		}
		else {
			stCffOutboundMasterImpl.setDeductionCategory2(deductionCategory2);
		}

		if (contractHolderNo == null) {
			stCffOutboundMasterImpl.setContractHolderNo(StringPool.BLANK);
		}
		else {
			stCffOutboundMasterImpl.setContractHolderNo(contractHolderNo);
		}

		if (deductionCategory3 == null) {
			stCffOutboundMasterImpl.setDeductionCategory3(StringPool.BLANK);
		}
		else {
			stCffOutboundMasterImpl.setDeductionCategory3(deductionCategory3);
		}

		if (itemNo == null) {
			stCffOutboundMasterImpl.setItemNo(StringPool.BLANK);
		}
		else {
			stCffOutboundMasterImpl.setItemNo(itemNo);
		}

		if (deductionCategory4 == null) {
			stCffOutboundMasterImpl.setDeductionCategory4(StringPool.BLANK);
		}
		else {
			stCffOutboundMasterImpl.setDeductionCategory4(deductionCategory4);
		}

		if (deductionCategory5 == null) {
			stCffOutboundMasterImpl.setDeductionCategory5(StringPool.BLANK);
		}
		else {
			stCffOutboundMasterImpl.setDeductionCategory5(deductionCategory5);
		}

		if (deductionCategory6 == null) {
			stCffOutboundMasterImpl.setDeductionCategory6(StringPool.BLANK);
		}
		else {
			stCffOutboundMasterImpl.setDeductionCategory6(deductionCategory6);
		}

		if (contractId == null) {
			stCffOutboundMasterImpl.setContractId(StringPool.BLANK);
		}
		else {
			stCffOutboundMasterImpl.setContractId(contractId);
		}

		if (deductionProgram == null) {
			stCffOutboundMasterImpl.setDeductionProgram(StringPool.BLANK);
		}
		else {
			stCffOutboundMasterImpl.setDeductionProgram(deductionProgram);
		}

		if (businessUnitId == null) {
			stCffOutboundMasterImpl.setBusinessUnitId(StringPool.BLANK);
		}
		else {
			stCffOutboundMasterImpl.setBusinessUnitId(businessUnitId);
		}

		if (projectionName == null) {
			stCffOutboundMasterImpl.setProjectionName(StringPool.BLANK);
		}
		else {
			stCffOutboundMasterImpl.setProjectionName(projectionName);
		}

		if (userId == null) {
			stCffOutboundMasterImpl.setUserId(StringPool.BLANK);
		}
		else {
			stCffOutboundMasterImpl.setUserId(userId);
		}

		if (companyId == null) {
			stCffOutboundMasterImpl.setCompanyId(StringPool.BLANK);
		}
		else {
			stCffOutboundMasterImpl.setCompanyId(companyId);
		}

		if (outboundStatus == null) {
			stCffOutboundMasterImpl.setOutboundStatus(StringPool.BLANK);
		}
		else {
			stCffOutboundMasterImpl.setOutboundStatus(outboundStatus);
		}

		if (originalBatchId == null) {
			stCffOutboundMasterImpl.setOriginalBatchId(StringPool.BLANK);
		}
		else {
			stCffOutboundMasterImpl.setOriginalBatchId(originalBatchId);
		}

		if (deductionName == null) {
			stCffOutboundMasterImpl.setDeductionName(StringPool.BLANK);
		}
		else {
			stCffOutboundMasterImpl.setDeductionName(deductionName);
		}

		stCffOutboundMasterImpl.setNetProfitPerUnit(netProfitPerUnit);
		stCffOutboundMasterImpl.setPeriodSid(periodSid);

		if (salesInclusion == null) {
			stCffOutboundMasterImpl.setSalesInclusion(StringPool.BLANK);
		}
		else {
			stCffOutboundMasterImpl.setSalesInclusion(salesInclusion);
		}

		if (batchId == null) {
			stCffOutboundMasterImpl.setBatchId(StringPool.BLANK);
		}
		else {
			stCffOutboundMasterImpl.setBatchId(batchId);
		}

		if (financialForecastName == null) {
			stCffOutboundMasterImpl.setFinancialForecastName(StringPool.BLANK);
		}
		else {
			stCffOutboundMasterImpl.setFinancialForecastName(financialForecastName);
		}

		stCffOutboundMasterImpl.setNetSalesDollar(netSalesDollar);
		stCffOutboundMasterImpl.setDeductionDollars(deductionDollars);

		if (contractNo == null) {
			stCffOutboundMasterImpl.setContractNo(StringPool.BLANK);
		}
		else {
			stCffOutboundMasterImpl.setContractNo(contractNo);
		}

		stCffOutboundMasterImpl.resetOriginalValues();

		return stCffOutboundMasterImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		etlCheckRecord = objectInput.readBoolean();
		customerName = objectInput.readUTF();
		contractHolderId = objectInput.readUTF();
		businessUnitNo = objectInput.readUTF();
		year = objectInput.readUTF();
		financialForecastApprovalDate = objectInput.readLong();
		deductionId = objectInput.readUTF();
		modifiedDate = objectInput.readLong();

		deductionPerUnit = objectInput.readDouble();

		cogsPerUnit = objectInput.readDouble();
		contractType = objectInput.readUTF();
		source = objectInput.readUTF();
		businessUnitName = objectInput.readUTF();

		contractMasterSid = objectInput.readInt();
		financialForecastId = objectInput.readUTF();
		projectId = objectInput.readUTF();
		customerNo = objectInput.readUTF();
		modifiedBy = objectInput.readUTF();

		salesDollars = objectInput.readDouble();

		month = objectInput.readInt();

		cffDetailsSid = objectInput.readInt();
		type = objectInput.readUTF();
		deductionType = objectInput.readUTF();

		companyMasterSid = objectInput.readInt();

		checkRecord = objectInput.readBoolean();
		contractName = objectInput.readUTF();

		deductionRate = objectInput.readDouble();
		deductionCategory = objectInput.readUTF();

		cogsAmount = objectInput.readDouble();
		deductionNo = objectInput.readUTF();
		financialForecastCreationDate = objectInput.readLong();
		companyNo = objectInput.readUTF();

		salesUnits = objectInput.readDouble();
		sessionId = objectInput.readUTF();
		itemName = objectInput.readUTF();
		deductionInclusion = objectInput.readUTF();

		rsModelSid = objectInput.readInt();
		contractHolderName = objectInput.readUTF();

		itemMasterSid = objectInput.readInt();
		companyName = objectInput.readUTF();
		customerId = objectInput.readUTF();
		itemId = objectInput.readUTF();

		netProfitDollars = objectInput.readDouble();

		glCompanyMasterSid = objectInput.readInt();
		createdDate = objectInput.readLong();
		createdBy = objectInput.readUTF();
		deductionCategory1 = objectInput.readUTF();
		deductionCategory2 = objectInput.readUTF();
		contractHolderNo = objectInput.readUTF();
		deductionCategory3 = objectInput.readUTF();
		itemNo = objectInput.readUTF();
		deductionCategory4 = objectInput.readUTF();
		deductionCategory5 = objectInput.readUTF();
		deductionCategory6 = objectInput.readUTF();
		contractId = objectInput.readUTF();
		deductionProgram = objectInput.readUTF();
		businessUnitId = objectInput.readUTF();
		projectionName = objectInput.readUTF();
		userId = objectInput.readUTF();
		companyId = objectInput.readUTF();
		outboundStatus = objectInput.readUTF();
		originalBatchId = objectInput.readUTF();
		deductionName = objectInput.readUTF();

		netProfitPerUnit = objectInput.readDouble();

		periodSid = objectInput.readInt();
		salesInclusion = objectInput.readUTF();
		batchId = objectInput.readUTF();
		financialForecastName = objectInput.readUTF();

		netSalesDollar = objectInput.readDouble();

		deductionDollars = objectInput.readDouble();
		contractNo = objectInput.readUTF();

		stCffOutboundMasterPK = new StCffOutboundMasterPK(cffDetailsSid,
				sessionId, rsModelSid, userId, periodSid);
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeBoolean(etlCheckRecord);

		if (customerName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(customerName);
		}

		if (contractHolderId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(contractHolderId);
		}

		if (businessUnitNo == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(businessUnitNo);
		}

		if (year == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(year);
		}

		objectOutput.writeLong(financialForecastApprovalDate);

		if (deductionId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(deductionId);
		}

		objectOutput.writeLong(modifiedDate);

		objectOutput.writeDouble(deductionPerUnit);

		objectOutput.writeDouble(cogsPerUnit);

		if (contractType == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(contractType);
		}

		if (source == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(source);
		}

		if (businessUnitName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(businessUnitName);
		}

		objectOutput.writeInt(contractMasterSid);

		if (financialForecastId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(financialForecastId);
		}

		if (projectId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(projectId);
		}

		if (customerNo == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(customerNo);
		}

		if (modifiedBy == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(modifiedBy);
		}

		objectOutput.writeDouble(salesDollars);

		objectOutput.writeInt(month);

		objectOutput.writeInt(cffDetailsSid);

		if (type == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(type);
		}

		if (deductionType == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(deductionType);
		}

		objectOutput.writeInt(companyMasterSid);

		objectOutput.writeBoolean(checkRecord);

		if (contractName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(contractName);
		}

		objectOutput.writeDouble(deductionRate);

		if (deductionCategory == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(deductionCategory);
		}

		objectOutput.writeDouble(cogsAmount);

		if (deductionNo == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(deductionNo);
		}

		objectOutput.writeLong(financialForecastCreationDate);

		if (companyNo == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(companyNo);
		}

		objectOutput.writeDouble(salesUnits);

		if (sessionId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(sessionId);
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

		objectOutput.writeInt(rsModelSid);

		if (contractHolderName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(contractHolderName);
		}

		objectOutput.writeInt(itemMasterSid);

		if (companyName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(companyName);
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

		objectOutput.writeDouble(netProfitDollars);

		objectOutput.writeInt(glCompanyMasterSid);
		objectOutput.writeLong(createdDate);

		if (createdBy == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(createdBy);
		}

		if (deductionCategory1 == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(deductionCategory1);
		}

		if (deductionCategory2 == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(deductionCategory2);
		}

		if (contractHolderNo == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(contractHolderNo);
		}

		if (deductionCategory3 == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(deductionCategory3);
		}

		if (itemNo == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(itemNo);
		}

		if (deductionCategory4 == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(deductionCategory4);
		}

		if (deductionCategory5 == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(deductionCategory5);
		}

		if (deductionCategory6 == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(deductionCategory6);
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

		if (projectionName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(projectionName);
		}

		if (userId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(userId);
		}

		if (companyId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(companyId);
		}

		if (outboundStatus == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(outboundStatus);
		}

		if (originalBatchId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(originalBatchId);
		}

		if (deductionName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(deductionName);
		}

		objectOutput.writeDouble(netProfitPerUnit);

		objectOutput.writeInt(periodSid);

		if (salesInclusion == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(salesInclusion);
		}

		if (batchId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(batchId);
		}

		if (financialForecastName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(financialForecastName);
		}

		objectOutput.writeDouble(netSalesDollar);

		objectOutput.writeDouble(deductionDollars);

		if (contractNo == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(contractNo);
		}
	}

	public boolean etlCheckRecord;
	public String customerName;
	public String contractHolderId;
	public String businessUnitNo;
	public String year;
	public long financialForecastApprovalDate;
	public String deductionId;
	public long modifiedDate;
	public double deductionPerUnit;
	public double cogsPerUnit;
	public String contractType;
	public String source;
	public String businessUnitName;
	public int contractMasterSid;
	public String financialForecastId;
	public String projectId;
	public String customerNo;
	public String modifiedBy;
	public double salesDollars;
	public int month;
	public int cffDetailsSid;
	public String type;
	public String deductionType;
	public int companyMasterSid;
	public boolean checkRecord;
	public String contractName;
	public double deductionRate;
	public String deductionCategory;
	public double cogsAmount;
	public String deductionNo;
	public long financialForecastCreationDate;
	public String companyNo;
	public double salesUnits;
	public String sessionId;
	public String itemName;
	public String deductionInclusion;
	public int rsModelSid;
	public String contractHolderName;
	public int itemMasterSid;
	public String companyName;
	public String customerId;
	public String itemId;
	public double netProfitDollars;
	public int glCompanyMasterSid;
	public long createdDate;
	public String createdBy;
	public String deductionCategory1;
	public String deductionCategory2;
	public String contractHolderNo;
	public String deductionCategory3;
	public String itemNo;
	public String deductionCategory4;
	public String deductionCategory5;
	public String deductionCategory6;
	public String contractId;
	public String deductionProgram;
	public String businessUnitId;
	public String projectionName;
	public String userId;
	public String companyId;
	public String outboundStatus;
	public String originalBatchId;
	public String deductionName;
	public double netProfitPerUnit;
	public int periodSid;
	public String salesInclusion;
	public String batchId;
	public String financialForecastName;
	public double netSalesDollar;
	public double deductionDollars;
	public String contractNo;
	public transient StCffOutboundMasterPK stCffOutboundMasterPK;
}