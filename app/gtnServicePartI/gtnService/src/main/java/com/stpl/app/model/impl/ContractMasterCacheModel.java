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

import com.stpl.app.model.ContractMaster;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing ContractMaster in entity cache.
 *
 * @author
 * @see ContractMaster
 * @generated
 */
@ProviderType
public class ContractMasterCacheModel implements CacheModel<ContractMaster>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ContractMasterCacheModel)) {
			return false;
		}

		ContractMasterCacheModel contractMasterCacheModel = (ContractMasterCacheModel)obj;

		if (contractMasterSid == contractMasterCacheModel.contractMasterSid) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, contractMasterSid);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(121);

		sb.append("{proposalEndDate=");
		sb.append(proposalEndDate);
		sb.append(", createdDate=");
		sb.append(createdDate);
		sb.append(", renegotiationEndDate=");
		sb.append(renegotiationEndDate);
		sb.append(", outsideAdditionalName=");
		sb.append(outsideAdditionalName);
		sb.append(", endDate=");
		sb.append(endDate);
		sb.append(", manfCompanyMasterSid=");
		sb.append(manfCompanyMasterSid);
		sb.append(", renegotiationStartDate=");
		sb.append(renegotiationStartDate);
		sb.append(", insideAuthor=");
		sb.append(insideAuthor);
		sb.append(", advanceNoticeDays=");
		sb.append(advanceNoticeDays);
		sb.append(", outsideOwner=");
		sb.append(outsideOwner);
		sb.append(", mostFavoredNation=");
		sb.append(mostFavoredNation);
		sb.append(", insideAdditionalPhone=");
		sb.append(insideAdditionalPhone);
		sb.append(", originalStartDate=");
		sb.append(originalStartDate);
		sb.append(", createdBy=");
		sb.append(createdBy);
		sb.append(", proposalStartDate=");
		sb.append(proposalStartDate);
		sb.append(", contractTradeClass=");
		sb.append(contractTradeClass);
		sb.append(", outsideAdditional=");
		sb.append(outsideAdditional);
		sb.append(", processStatus=");
		sb.append(processStatus);
		sb.append(", insideAdditionalName=");
		sb.append(insideAdditionalName);
		sb.append(", contractMasterSid=");
		sb.append(contractMasterSid);
		sb.append(", contractStatus=");
		sb.append(contractStatus);
		sb.append(", contractId=");
		sb.append(contractId);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", contractType=");
		sb.append(contractType);
		sb.append(", awardStatus=");
		sb.append(awardStatus);
		sb.append(", insideOwner=");
		sb.append(insideOwner);
		sb.append(", source=");
		sb.append(source);
		sb.append(", shippingTerms=");
		sb.append(shippingTerms);
		sb.append(", priceEscalationClause=");
		sb.append(priceEscalationClause);
		sb.append(", modifiedBy=");
		sb.append(modifiedBy);
		sb.append(", outsideAdditionalPhone=");
		sb.append(outsideAdditionalPhone);
		sb.append(", term=");
		sb.append(term);
		sb.append(", contractNo=");
		sb.append(contractNo);
		sb.append(", batchId=");
		sb.append(batchId);
		sb.append(", documentClass=");
		sb.append(documentClass);
		sb.append(", originalEndDate=");
		sb.append(originalEndDate);
		sb.append(", paymentTerms=");
		sb.append(paymentTerms);
		sb.append(", insideAdditional=");
		sb.append(insideAdditional);
		sb.append(", affiliatedContractInfo=");
		sb.append(affiliatedContractInfo);
		sb.append(", category=");
		sb.append(category);
		sb.append(", outsidePhone=");
		sb.append(outsidePhone);
		sb.append(", priceprotectionStartDate=");
		sb.append(priceprotectionStartDate);
		sb.append(", priceprotectionEndDate=");
		sb.append(priceprotectionEndDate);
		sb.append(", documentType=");
		sb.append(documentType);
		sb.append(", exemptFromLowPrice=");
		sb.append(exemptFromLowPrice);
		sb.append(", organizationKey=");
		sb.append(organizationKey);
		sb.append(", currency=");
		sb.append(currency);
		sb.append(", insidePhone=");
		sb.append(insidePhone);
		sb.append(", bunitCompanyMasterSid=");
		sb.append(bunitCompanyMasterSid);
		sb.append(", outsideAuthor=");
		sb.append(outsideAuthor);
		sb.append(", contHoldCompanyMasterSid=");
		sb.append(contHoldCompanyMasterSid);
		sb.append(", startDate=");
		sb.append(startDate);
		sb.append(", contractName=");
		sb.append(contractName);
		sb.append(", lastUpdatedDate=");
		sb.append(lastUpdatedDate);
		sb.append(", recordLockStatus=");
		sb.append(recordLockStatus);
		sb.append(", priceResetIndicator=");
		sb.append(priceResetIndicator);
		sb.append(", minimumOrder=");
		sb.append(minimumOrder);
		sb.append(", cancellationClause=");
		sb.append(cancellationClause);
		sb.append(", inboundStatus=");
		sb.append(inboundStatus);
		sb.append(", internalNotes=");
		sb.append(internalNotes);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ContractMaster toEntityModel() {
		ContractMasterImpl contractMasterImpl = new ContractMasterImpl();

		if (proposalEndDate == Long.MIN_VALUE) {
			contractMasterImpl.setProposalEndDate(null);
		}
		else {
			contractMasterImpl.setProposalEndDate(new Date(proposalEndDate));
		}

		if (createdDate == Long.MIN_VALUE) {
			contractMasterImpl.setCreatedDate(null);
		}
		else {
			contractMasterImpl.setCreatedDate(new Date(createdDate));
		}

		if (renegotiationEndDate == Long.MIN_VALUE) {
			contractMasterImpl.setRenegotiationEndDate(null);
		}
		else {
			contractMasterImpl.setRenegotiationEndDate(new Date(
					renegotiationEndDate));
		}

		if (outsideAdditionalName == null) {
			contractMasterImpl.setOutsideAdditionalName(StringPool.BLANK);
		}
		else {
			contractMasterImpl.setOutsideAdditionalName(outsideAdditionalName);
		}

		if (endDate == Long.MIN_VALUE) {
			contractMasterImpl.setEndDate(null);
		}
		else {
			contractMasterImpl.setEndDate(new Date(endDate));
		}

		if (manfCompanyMasterSid == null) {
			contractMasterImpl.setManfCompanyMasterSid(StringPool.BLANK);
		}
		else {
			contractMasterImpl.setManfCompanyMasterSid(manfCompanyMasterSid);
		}

		if (renegotiationStartDate == Long.MIN_VALUE) {
			contractMasterImpl.setRenegotiationStartDate(null);
		}
		else {
			contractMasterImpl.setRenegotiationStartDate(new Date(
					renegotiationStartDate));
		}

		if (insideAuthor == null) {
			contractMasterImpl.setInsideAuthor(StringPool.BLANK);
		}
		else {
			contractMasterImpl.setInsideAuthor(insideAuthor);
		}

		contractMasterImpl.setAdvanceNoticeDays(advanceNoticeDays);

		if (outsideOwner == null) {
			contractMasterImpl.setOutsideOwner(StringPool.BLANK);
		}
		else {
			contractMasterImpl.setOutsideOwner(outsideOwner);
		}

		if (mostFavoredNation == null) {
			contractMasterImpl.setMostFavoredNation(StringPool.BLANK);
		}
		else {
			contractMasterImpl.setMostFavoredNation(mostFavoredNation);
		}

		if (insideAdditionalPhone == null) {
			contractMasterImpl.setInsideAdditionalPhone(StringPool.BLANK);
		}
		else {
			contractMasterImpl.setInsideAdditionalPhone(insideAdditionalPhone);
		}

		if (originalStartDate == Long.MIN_VALUE) {
			contractMasterImpl.setOriginalStartDate(null);
		}
		else {
			contractMasterImpl.setOriginalStartDate(new Date(originalStartDate));
		}

		contractMasterImpl.setCreatedBy(createdBy);

		if (proposalStartDate == Long.MIN_VALUE) {
			contractMasterImpl.setProposalStartDate(null);
		}
		else {
			contractMasterImpl.setProposalStartDate(new Date(proposalStartDate));
		}

		contractMasterImpl.setContractTradeClass(contractTradeClass);

		if (outsideAdditional == null) {
			contractMasterImpl.setOutsideAdditional(StringPool.BLANK);
		}
		else {
			contractMasterImpl.setOutsideAdditional(outsideAdditional);
		}

		contractMasterImpl.setProcessStatus(processStatus);

		if (insideAdditionalName == null) {
			contractMasterImpl.setInsideAdditionalName(StringPool.BLANK);
		}
		else {
			contractMasterImpl.setInsideAdditionalName(insideAdditionalName);
		}

		contractMasterImpl.setContractMasterSid(contractMasterSid);
		contractMasterImpl.setContractStatus(contractStatus);

		if (contractId == null) {
			contractMasterImpl.setContractId(StringPool.BLANK);
		}
		else {
			contractMasterImpl.setContractId(contractId);
		}

		if (modifiedDate == Long.MIN_VALUE) {
			contractMasterImpl.setModifiedDate(null);
		}
		else {
			contractMasterImpl.setModifiedDate(new Date(modifiedDate));
		}

		contractMasterImpl.setContractType(contractType);
		contractMasterImpl.setAwardStatus(awardStatus);

		if (insideOwner == null) {
			contractMasterImpl.setInsideOwner(StringPool.BLANK);
		}
		else {
			contractMasterImpl.setInsideOwner(insideOwner);
		}

		if (source == null) {
			contractMasterImpl.setSource(StringPool.BLANK);
		}
		else {
			contractMasterImpl.setSource(source);
		}

		if (shippingTerms == null) {
			contractMasterImpl.setShippingTerms(StringPool.BLANK);
		}
		else {
			contractMasterImpl.setShippingTerms(shippingTerms);
		}

		if (priceEscalationClause == null) {
			contractMasterImpl.setPriceEscalationClause(StringPool.BLANK);
		}
		else {
			contractMasterImpl.setPriceEscalationClause(priceEscalationClause);
		}

		contractMasterImpl.setModifiedBy(modifiedBy);

		if (outsideAdditionalPhone == null) {
			contractMasterImpl.setOutsideAdditionalPhone(StringPool.BLANK);
		}
		else {
			contractMasterImpl.setOutsideAdditionalPhone(outsideAdditionalPhone);
		}

		contractMasterImpl.setTerm(term);

		if (contractNo == null) {
			contractMasterImpl.setContractNo(StringPool.BLANK);
		}
		else {
			contractMasterImpl.setContractNo(contractNo);
		}

		if (batchId == null) {
			contractMasterImpl.setBatchId(StringPool.BLANK);
		}
		else {
			contractMasterImpl.setBatchId(batchId);
		}

		contractMasterImpl.setDocumentClass(documentClass);

		if (originalEndDate == Long.MIN_VALUE) {
			contractMasterImpl.setOriginalEndDate(null);
		}
		else {
			contractMasterImpl.setOriginalEndDate(new Date(originalEndDate));
		}

		contractMasterImpl.setPaymentTerms(paymentTerms);

		if (insideAdditional == null) {
			contractMasterImpl.setInsideAdditional(StringPool.BLANK);
		}
		else {
			contractMasterImpl.setInsideAdditional(insideAdditional);
		}

		if (affiliatedContractInfo == null) {
			contractMasterImpl.setAffiliatedContractInfo(StringPool.BLANK);
		}
		else {
			contractMasterImpl.setAffiliatedContractInfo(affiliatedContractInfo);
		}

		if (category == null) {
			contractMasterImpl.setCategory(StringPool.BLANK);
		}
		else {
			contractMasterImpl.setCategory(category);
		}

		if (outsidePhone == null) {
			contractMasterImpl.setOutsidePhone(StringPool.BLANK);
		}
		else {
			contractMasterImpl.setOutsidePhone(outsidePhone);
		}

		if (priceprotectionStartDate == Long.MIN_VALUE) {
			contractMasterImpl.setPriceprotectionStartDate(null);
		}
		else {
			contractMasterImpl.setPriceprotectionStartDate(new Date(
					priceprotectionStartDate));
		}

		if (priceprotectionEndDate == Long.MIN_VALUE) {
			contractMasterImpl.setPriceprotectionEndDate(null);
		}
		else {
			contractMasterImpl.setPriceprotectionEndDate(new Date(
					priceprotectionEndDate));
		}

		contractMasterImpl.setDocumentType(documentType);

		if (exemptFromLowPrice == null) {
			contractMasterImpl.setExemptFromLowPrice(StringPool.BLANK);
		}
		else {
			contractMasterImpl.setExemptFromLowPrice(exemptFromLowPrice);
		}

		if (organizationKey == null) {
			contractMasterImpl.setOrganizationKey(StringPool.BLANK);
		}
		else {
			contractMasterImpl.setOrganizationKey(organizationKey);
		}

		if (currency == null) {
			contractMasterImpl.setCurrency(StringPool.BLANK);
		}
		else {
			contractMasterImpl.setCurrency(currency);
		}

		if (insidePhone == null) {
			contractMasterImpl.setInsidePhone(StringPool.BLANK);
		}
		else {
			contractMasterImpl.setInsidePhone(insidePhone);
		}

		if (bunitCompanyMasterSid == null) {
			contractMasterImpl.setBunitCompanyMasterSid(StringPool.BLANK);
		}
		else {
			contractMasterImpl.setBunitCompanyMasterSid(bunitCompanyMasterSid);
		}

		if (outsideAuthor == null) {
			contractMasterImpl.setOutsideAuthor(StringPool.BLANK);
		}
		else {
			contractMasterImpl.setOutsideAuthor(outsideAuthor);
		}

		if (contHoldCompanyMasterSid == null) {
			contractMasterImpl.setContHoldCompanyMasterSid(StringPool.BLANK);
		}
		else {
			contractMasterImpl.setContHoldCompanyMasterSid(contHoldCompanyMasterSid);
		}

		if (startDate == Long.MIN_VALUE) {
			contractMasterImpl.setStartDate(null);
		}
		else {
			contractMasterImpl.setStartDate(new Date(startDate));
		}

		if (contractName == null) {
			contractMasterImpl.setContractName(StringPool.BLANK);
		}
		else {
			contractMasterImpl.setContractName(contractName);
		}

		if (lastUpdatedDate == Long.MIN_VALUE) {
			contractMasterImpl.setLastUpdatedDate(null);
		}
		else {
			contractMasterImpl.setLastUpdatedDate(new Date(lastUpdatedDate));
		}

		contractMasterImpl.setRecordLockStatus(recordLockStatus);

		if (priceResetIndicator == null) {
			contractMasterImpl.setPriceResetIndicator(StringPool.BLANK);
		}
		else {
			contractMasterImpl.setPriceResetIndicator(priceResetIndicator);
		}

		if (minimumOrder == null) {
			contractMasterImpl.setMinimumOrder(StringPool.BLANK);
		}
		else {
			contractMasterImpl.setMinimumOrder(minimumOrder);
		}

		if (cancellationClause == null) {
			contractMasterImpl.setCancellationClause(StringPool.BLANK);
		}
		else {
			contractMasterImpl.setCancellationClause(cancellationClause);
		}

		if (inboundStatus == null) {
			contractMasterImpl.setInboundStatus(StringPool.BLANK);
		}
		else {
			contractMasterImpl.setInboundStatus(inboundStatus);
		}

		if (internalNotes == null) {
			contractMasterImpl.setInternalNotes(StringPool.BLANK);
		}
		else {
			contractMasterImpl.setInternalNotes(internalNotes);
		}

		contractMasterImpl.resetOriginalValues();

		return contractMasterImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		proposalEndDate = objectInput.readLong();
		createdDate = objectInput.readLong();
		renegotiationEndDate = objectInput.readLong();
		outsideAdditionalName = objectInput.readUTF();
		endDate = objectInput.readLong();
		manfCompanyMasterSid = objectInput.readUTF();
		renegotiationStartDate = objectInput.readLong();
		insideAuthor = objectInput.readUTF();

		advanceNoticeDays = objectInput.readDouble();
		outsideOwner = objectInput.readUTF();
		mostFavoredNation = objectInput.readUTF();
		insideAdditionalPhone = objectInput.readUTF();
		originalStartDate = objectInput.readLong();

		createdBy = objectInput.readInt();
		proposalStartDate = objectInput.readLong();

		contractTradeClass = objectInput.readInt();
		outsideAdditional = objectInput.readUTF();

		processStatus = objectInput.readBoolean();
		insideAdditionalName = objectInput.readUTF();

		contractMasterSid = objectInput.readInt();

		contractStatus = objectInput.readInt();
		contractId = objectInput.readUTF();
		modifiedDate = objectInput.readLong();

		contractType = objectInput.readInt();

		awardStatus = objectInput.readInt();
		insideOwner = objectInput.readUTF();
		source = objectInput.readUTF();
		shippingTerms = objectInput.readUTF();
		priceEscalationClause = objectInput.readUTF();

		modifiedBy = objectInput.readInt();
		outsideAdditionalPhone = objectInput.readUTF();

		term = objectInput.readInt();
		contractNo = objectInput.readUTF();
		batchId = objectInput.readUTF();

		documentClass = objectInput.readInt();
		originalEndDate = objectInput.readLong();

		paymentTerms = objectInput.readInt();
		insideAdditional = objectInput.readUTF();
		affiliatedContractInfo = objectInput.readUTF();
		category = objectInput.readUTF();
		outsidePhone = objectInput.readUTF();
		priceprotectionStartDate = objectInput.readLong();
		priceprotectionEndDate = objectInput.readLong();

		documentType = objectInput.readInt();
		exemptFromLowPrice = objectInput.readUTF();
		organizationKey = objectInput.readUTF();
		currency = objectInput.readUTF();
		insidePhone = objectInput.readUTF();
		bunitCompanyMasterSid = objectInput.readUTF();
		outsideAuthor = objectInput.readUTF();
		contHoldCompanyMasterSid = objectInput.readUTF();
		startDate = objectInput.readLong();
		contractName = objectInput.readUTF();
		lastUpdatedDate = objectInput.readLong();

		recordLockStatus = objectInput.readBoolean();
		priceResetIndicator = objectInput.readUTF();
		minimumOrder = objectInput.readUTF();
		cancellationClause = objectInput.readUTF();
		inboundStatus = objectInput.readUTF();
		internalNotes = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(proposalEndDate);
		objectOutput.writeLong(createdDate);
		objectOutput.writeLong(renegotiationEndDate);

		if (outsideAdditionalName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(outsideAdditionalName);
		}

		objectOutput.writeLong(endDate);

		if (manfCompanyMasterSid == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(manfCompanyMasterSid);
		}

		objectOutput.writeLong(renegotiationStartDate);

		if (insideAuthor == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(insideAuthor);
		}

		objectOutput.writeDouble(advanceNoticeDays);

		if (outsideOwner == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(outsideOwner);
		}

		if (mostFavoredNation == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(mostFavoredNation);
		}

		if (insideAdditionalPhone == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(insideAdditionalPhone);
		}

		objectOutput.writeLong(originalStartDate);

		objectOutput.writeInt(createdBy);
		objectOutput.writeLong(proposalStartDate);

		objectOutput.writeInt(contractTradeClass);

		if (outsideAdditional == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(outsideAdditional);
		}

		objectOutput.writeBoolean(processStatus);

		if (insideAdditionalName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(insideAdditionalName);
		}

		objectOutput.writeInt(contractMasterSid);

		objectOutput.writeInt(contractStatus);

		if (contractId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(contractId);
		}

		objectOutput.writeLong(modifiedDate);

		objectOutput.writeInt(contractType);

		objectOutput.writeInt(awardStatus);

		if (insideOwner == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(insideOwner);
		}

		if (source == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(source);
		}

		if (shippingTerms == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(shippingTerms);
		}

		if (priceEscalationClause == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(priceEscalationClause);
		}

		objectOutput.writeInt(modifiedBy);

		if (outsideAdditionalPhone == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(outsideAdditionalPhone);
		}

		objectOutput.writeInt(term);

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

		objectOutput.writeInt(documentClass);
		objectOutput.writeLong(originalEndDate);

		objectOutput.writeInt(paymentTerms);

		if (insideAdditional == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(insideAdditional);
		}

		if (affiliatedContractInfo == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(affiliatedContractInfo);
		}

		if (category == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(category);
		}

		if (outsidePhone == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(outsidePhone);
		}

		objectOutput.writeLong(priceprotectionStartDate);
		objectOutput.writeLong(priceprotectionEndDate);

		objectOutput.writeInt(documentType);

		if (exemptFromLowPrice == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(exemptFromLowPrice);
		}

		if (organizationKey == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(organizationKey);
		}

		if (currency == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(currency);
		}

		if (insidePhone == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(insidePhone);
		}

		if (bunitCompanyMasterSid == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(bunitCompanyMasterSid);
		}

		if (outsideAuthor == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(outsideAuthor);
		}

		if (contHoldCompanyMasterSid == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(contHoldCompanyMasterSid);
		}

		objectOutput.writeLong(startDate);

		if (contractName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(contractName);
		}

		objectOutput.writeLong(lastUpdatedDate);

		objectOutput.writeBoolean(recordLockStatus);

		if (priceResetIndicator == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(priceResetIndicator);
		}

		if (minimumOrder == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(minimumOrder);
		}

		if (cancellationClause == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(cancellationClause);
		}

		if (inboundStatus == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(inboundStatus);
		}

		if (internalNotes == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(internalNotes);
		}
	}

	public long proposalEndDate;
	public long createdDate;
	public long renegotiationEndDate;
	public String outsideAdditionalName;
	public long endDate;
	public String manfCompanyMasterSid;
	public long renegotiationStartDate;
	public String insideAuthor;
	public double advanceNoticeDays;
	public String outsideOwner;
	public String mostFavoredNation;
	public String insideAdditionalPhone;
	public long originalStartDate;
	public int createdBy;
	public long proposalStartDate;
	public int contractTradeClass;
	public String outsideAdditional;
	public boolean processStatus;
	public String insideAdditionalName;
	public int contractMasterSid;
	public int contractStatus;
	public String contractId;
	public long modifiedDate;
	public int contractType;
	public int awardStatus;
	public String insideOwner;
	public String source;
	public String shippingTerms;
	public String priceEscalationClause;
	public int modifiedBy;
	public String outsideAdditionalPhone;
	public int term;
	public String contractNo;
	public String batchId;
	public int documentClass;
	public long originalEndDate;
	public int paymentTerms;
	public String insideAdditional;
	public String affiliatedContractInfo;
	public String category;
	public String outsidePhone;
	public long priceprotectionStartDate;
	public long priceprotectionEndDate;
	public int documentType;
	public String exemptFromLowPrice;
	public String organizationKey;
	public String currency;
	public String insidePhone;
	public String bunitCompanyMasterSid;
	public String outsideAuthor;
	public String contHoldCompanyMasterSid;
	public long startDate;
	public String contractName;
	public long lastUpdatedDate;
	public boolean recordLockStatus;
	public String priceResetIndicator;
	public String minimumOrder;
	public String cancellationClause;
	public String inboundStatus;
	public String internalNotes;
}