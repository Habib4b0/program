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

import com.stpl.app.model.PriceScheduleDetails;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing PriceScheduleDetails in entity cache.
 *
 * @author
 * @see PriceScheduleDetails
 * @generated
 */
@ProviderType
public class PriceScheduleDetailsCacheModel implements CacheModel<PriceScheduleDetails>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof PriceScheduleDetailsCacheModel)) {
			return false;
		}

		PriceScheduleDetailsCacheModel priceScheduleDetailsCacheModel = (PriceScheduleDetailsCacheModel)obj;

		if (psDetailsSystemId == priceScheduleDetailsCacheModel.psDetailsSystemId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, psDetailsSystemId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(61);

		sb.append("{price=");
		sb.append(price);
		sb.append(", psDetailsSystemId=");
		sb.append(psDetailsSystemId);
		sb.append(", companyFamilyplanSystemId=");
		sb.append(companyFamilyplanSystemId);
		sb.append(", itemSystemId=");
		sb.append(itemSystemId);
		sb.append(", priceProtectionStartDate=");
		sb.append(priceProtectionStartDate);
		sb.append(", basePrice=");
		sb.append(basePrice);
		sb.append(", revisionDate=");
		sb.append(revisionDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", priceScheduleSystemId=");
		sb.append(priceScheduleSystemId);
		sb.append(", itemFamilyplanSystemId=");
		sb.append(itemFamilyplanSystemId);
		sb.append(", priceTolerance=");
		sb.append(priceTolerance);
		sb.append(", createdBy=");
		sb.append(createdBy);
		sb.append(", createdDate=");
		sb.append(createdDate);
		sb.append(", suggestedPrice=");
		sb.append(suggestedPrice);
		sb.append(", inboundStatus=");
		sb.append(inboundStatus);
		sb.append(", modifiedBy=");
		sb.append(modifiedBy);
		sb.append(", contractPrice=");
		sb.append(contractPrice);
		sb.append(", priceToleranceType=");
		sb.append(priceToleranceType);
		sb.append(", memberFamilyplanSystemId=");
		sb.append(memberFamilyplanSystemId);
		sb.append(", contractPriceEndDate=");
		sb.append(contractPriceEndDate);
		sb.append(", contractPriceStartDate=");
		sb.append(contractPriceStartDate);
		sb.append(", priceToleranceFrequency=");
		sb.append(priceToleranceFrequency);
		sb.append(", attachedDate=");
		sb.append(attachedDate);
		sb.append(", priceProtectionEndDate=");
		sb.append(priceProtectionEndDate);
		sb.append(", recordLockStatus=");
		sb.append(recordLockStatus);
		sb.append(", pricePlanId=");
		sb.append(pricePlanId);
		sb.append(", priceType=");
		sb.append(priceType);
		sb.append(", batchId=");
		sb.append(batchId);
		sb.append(", priceToleranceInterval=");
		sb.append(priceToleranceInterval);
		sb.append(", priceRevision=");
		sb.append(priceRevision);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public PriceScheduleDetails toEntityModel() {
		PriceScheduleDetailsImpl priceScheduleDetailsImpl = new PriceScheduleDetailsImpl();

		priceScheduleDetailsImpl.setPrice(price);
		priceScheduleDetailsImpl.setPsDetailsSystemId(psDetailsSystemId);
		priceScheduleDetailsImpl.setCompanyFamilyplanSystemId(companyFamilyplanSystemId);
		priceScheduleDetailsImpl.setItemSystemId(itemSystemId);

		if (priceProtectionStartDate == Long.MIN_VALUE) {
			priceScheduleDetailsImpl.setPriceProtectionStartDate(null);
		}
		else {
			priceScheduleDetailsImpl.setPriceProtectionStartDate(new Date(
					priceProtectionStartDate));
		}

		priceScheduleDetailsImpl.setBasePrice(basePrice);

		if (revisionDate == Long.MIN_VALUE) {
			priceScheduleDetailsImpl.setRevisionDate(null);
		}
		else {
			priceScheduleDetailsImpl.setRevisionDate(new Date(revisionDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			priceScheduleDetailsImpl.setModifiedDate(null);
		}
		else {
			priceScheduleDetailsImpl.setModifiedDate(new Date(modifiedDate));
		}

		priceScheduleDetailsImpl.setPriceScheduleSystemId(priceScheduleSystemId);
		priceScheduleDetailsImpl.setItemFamilyplanSystemId(itemFamilyplanSystemId);
		priceScheduleDetailsImpl.setPriceTolerance(priceTolerance);

		if (createdBy == null) {
			priceScheduleDetailsImpl.setCreatedBy(StringPool.BLANK);
		}
		else {
			priceScheduleDetailsImpl.setCreatedBy(createdBy);
		}

		if (createdDate == Long.MIN_VALUE) {
			priceScheduleDetailsImpl.setCreatedDate(null);
		}
		else {
			priceScheduleDetailsImpl.setCreatedDate(new Date(createdDate));
		}

		priceScheduleDetailsImpl.setSuggestedPrice(suggestedPrice);

		if (inboundStatus == null) {
			priceScheduleDetailsImpl.setInboundStatus(StringPool.BLANK);
		}
		else {
			priceScheduleDetailsImpl.setInboundStatus(inboundStatus);
		}

		if (modifiedBy == null) {
			priceScheduleDetailsImpl.setModifiedBy(StringPool.BLANK);
		}
		else {
			priceScheduleDetailsImpl.setModifiedBy(modifiedBy);
		}

		priceScheduleDetailsImpl.setContractPrice(contractPrice);

		if (priceToleranceType == null) {
			priceScheduleDetailsImpl.setPriceToleranceType(StringPool.BLANK);
		}
		else {
			priceScheduleDetailsImpl.setPriceToleranceType(priceToleranceType);
		}

		priceScheduleDetailsImpl.setMemberFamilyplanSystemId(memberFamilyplanSystemId);

		if (contractPriceEndDate == Long.MIN_VALUE) {
			priceScheduleDetailsImpl.setContractPriceEndDate(null);
		}
		else {
			priceScheduleDetailsImpl.setContractPriceEndDate(new Date(
					contractPriceEndDate));
		}

		if (contractPriceStartDate == Long.MIN_VALUE) {
			priceScheduleDetailsImpl.setContractPriceStartDate(null);
		}
		else {
			priceScheduleDetailsImpl.setContractPriceStartDate(new Date(
					contractPriceStartDate));
		}

		if (priceToleranceFrequency == null) {
			priceScheduleDetailsImpl.setPriceToleranceFrequency(StringPool.BLANK);
		}
		else {
			priceScheduleDetailsImpl.setPriceToleranceFrequency(priceToleranceFrequency);
		}

		if (attachedDate == Long.MIN_VALUE) {
			priceScheduleDetailsImpl.setAttachedDate(null);
		}
		else {
			priceScheduleDetailsImpl.setAttachedDate(new Date(attachedDate));
		}

		if (priceProtectionEndDate == Long.MIN_VALUE) {
			priceScheduleDetailsImpl.setPriceProtectionEndDate(null);
		}
		else {
			priceScheduleDetailsImpl.setPriceProtectionEndDate(new Date(
					priceProtectionEndDate));
		}

		if (recordLockStatus == null) {
			priceScheduleDetailsImpl.setRecordLockStatus(StringPool.BLANK);
		}
		else {
			priceScheduleDetailsImpl.setRecordLockStatus(recordLockStatus);
		}

		if (pricePlanId == null) {
			priceScheduleDetailsImpl.setPricePlanId(StringPool.BLANK);
		}
		else {
			priceScheduleDetailsImpl.setPricePlanId(pricePlanId);
		}

		if (priceType == null) {
			priceScheduleDetailsImpl.setPriceType(StringPool.BLANK);
		}
		else {
			priceScheduleDetailsImpl.setPriceType(priceType);
		}

		if (batchId == null) {
			priceScheduleDetailsImpl.setBatchId(StringPool.BLANK);
		}
		else {
			priceScheduleDetailsImpl.setBatchId(batchId);
		}

		priceScheduleDetailsImpl.setPriceToleranceInterval(priceToleranceInterval);
		priceScheduleDetailsImpl.setPriceRevision(priceRevision);

		priceScheduleDetailsImpl.resetOriginalValues();

		return priceScheduleDetailsImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		price = objectInput.readDouble();

		psDetailsSystemId = objectInput.readInt();

		companyFamilyplanSystemId = objectInput.readInt();

		itemSystemId = objectInput.readInt();
		priceProtectionStartDate = objectInput.readLong();

		basePrice = objectInput.readDouble();
		revisionDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		priceScheduleSystemId = objectInput.readInt();

		itemFamilyplanSystemId = objectInput.readInt();

		priceTolerance = objectInput.readDouble();
		createdBy = objectInput.readUTF();
		createdDate = objectInput.readLong();

		suggestedPrice = objectInput.readDouble();
		inboundStatus = objectInput.readUTF();
		modifiedBy = objectInput.readUTF();

		contractPrice = objectInput.readDouble();
		priceToleranceType = objectInput.readUTF();

		memberFamilyplanSystemId = objectInput.readInt();
		contractPriceEndDate = objectInput.readLong();
		contractPriceStartDate = objectInput.readLong();
		priceToleranceFrequency = objectInput.readUTF();
		attachedDate = objectInput.readLong();
		priceProtectionEndDate = objectInput.readLong();
		recordLockStatus = objectInput.readUTF();
		pricePlanId = objectInput.readUTF();
		priceType = objectInput.readUTF();
		batchId = objectInput.readUTF();

		priceToleranceInterval = objectInput.readInt();

		priceRevision = objectInput.readDouble();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeDouble(price);

		objectOutput.writeInt(psDetailsSystemId);

		objectOutput.writeInt(companyFamilyplanSystemId);

		objectOutput.writeInt(itemSystemId);
		objectOutput.writeLong(priceProtectionStartDate);

		objectOutput.writeDouble(basePrice);
		objectOutput.writeLong(revisionDate);
		objectOutput.writeLong(modifiedDate);

		objectOutput.writeInt(priceScheduleSystemId);

		objectOutput.writeInt(itemFamilyplanSystemId);

		objectOutput.writeDouble(priceTolerance);

		if (createdBy == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(createdBy);
		}

		objectOutput.writeLong(createdDate);

		objectOutput.writeDouble(suggestedPrice);

		if (inboundStatus == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(inboundStatus);
		}

		if (modifiedBy == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(modifiedBy);
		}

		objectOutput.writeDouble(contractPrice);

		if (priceToleranceType == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(priceToleranceType);
		}

		objectOutput.writeInt(memberFamilyplanSystemId);
		objectOutput.writeLong(contractPriceEndDate);
		objectOutput.writeLong(contractPriceStartDate);

		if (priceToleranceFrequency == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(priceToleranceFrequency);
		}

		objectOutput.writeLong(attachedDate);
		objectOutput.writeLong(priceProtectionEndDate);

		if (recordLockStatus == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(recordLockStatus);
		}

		if (pricePlanId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(pricePlanId);
		}

		if (priceType == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(priceType);
		}

		if (batchId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(batchId);
		}

		objectOutput.writeInt(priceToleranceInterval);

		objectOutput.writeDouble(priceRevision);
	}

	public double price;
	public int psDetailsSystemId;
	public int companyFamilyplanSystemId;
	public int itemSystemId;
	public long priceProtectionStartDate;
	public double basePrice;
	public long revisionDate;
	public long modifiedDate;
	public int priceScheduleSystemId;
	public int itemFamilyplanSystemId;
	public double priceTolerance;
	public String createdBy;
	public long createdDate;
	public double suggestedPrice;
	public String inboundStatus;
	public String modifiedBy;
	public double contractPrice;
	public String priceToleranceType;
	public int memberFamilyplanSystemId;
	public long contractPriceEndDate;
	public long contractPriceStartDate;
	public String priceToleranceFrequency;
	public long attachedDate;
	public long priceProtectionEndDate;
	public String recordLockStatus;
	public String pricePlanId;
	public String priceType;
	public String batchId;
	public int priceToleranceInterval;
	public double priceRevision;
}