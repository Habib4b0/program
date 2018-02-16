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

import com.stpl.app.model.PsDetails;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing PsDetails in entity cache.
 *
 * @author
 * @see PsDetails
 * @generated
 */
@ProviderType
public class PsDetailsCacheModel implements CacheModel<PsDetails>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof PsDetailsCacheModel)) {
			return false;
		}

		PsDetailsCacheModel psDetailsCacheModel = (PsDetailsCacheModel)obj;

		if (psDetailsSid == psDetailsCacheModel.psDetailsSid) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, psDetailsSid);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(111);

		sb.append("{nepFormula=");
		sb.append(nepFormula);
		sb.append(", price=");
		sb.append(price);
		sb.append(", itemMasterSid=");
		sb.append(itemMasterSid);
		sb.append(", resetType=");
		sb.append(resetType);
		sb.append(", priceProtectionStartDate=");
		sb.append(priceProtectionStartDate);
		sb.append(", resetDate=");
		sb.append(resetDate);
		sb.append(", basePrice=");
		sb.append(basePrice);
		sb.append(", itemPsAttachedDate=");
		sb.append(itemPsAttachedDate);
		sb.append(", brandMasterSid=");
		sb.append(brandMasterSid);
		sb.append(", status=");
		sb.append(status);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", itemPsAttachedStatus=");
		sb.append(itemPsAttachedStatus);
		sb.append(", revisionDate=");
		sb.append(revisionDate);
		sb.append(", priceTolerance=");
		sb.append(priceTolerance);
		sb.append(", createdDate=");
		sb.append(createdDate);
		sb.append(", createdBy=");
		sb.append(createdBy);
		sb.append(", source=");
		sb.append(source);
		sb.append(", psDetailsSid=");
		sb.append(psDetailsSid);
		sb.append(", psModelSid=");
		sb.append(psModelSid);
		sb.append(", suggestedPrice=");
		sb.append(suggestedPrice);
		sb.append(", netPriceTypeFormula=");
		sb.append(netPriceTypeFormula);
		sb.append(", priceProtectionPriceType=");
		sb.append(priceProtectionPriceType);
		sb.append(", modifiedBy=");
		sb.append(modifiedBy);
		sb.append(", inboundStatus=");
		sb.append(inboundStatus);
		sb.append(", contractPrice=");
		sb.append(contractPrice);
		sb.append(", ifpModelSid=");
		sb.append(ifpModelSid);
		sb.append(", priceToleranceType=");
		sb.append(priceToleranceType);
		sb.append(", maxIncrementalChange=");
		sb.append(maxIncrementalChange);
		sb.append(", itemPricingQualifierSid=");
		sb.append(itemPricingQualifierSid);
		sb.append(", contractPriceEndDate=");
		sb.append(contractPriceEndDate);
		sb.append(", nep=");
		sb.append(nep);
		sb.append(", contractPriceStartDate=");
		sb.append(contractPriceStartDate);
		sb.append(", priceToleranceFrequency=");
		sb.append(priceToleranceFrequency);
		sb.append(", priceProtectionEndDate=");
		sb.append(priceProtectionEndDate);
		sb.append(", priceProtectionStatus=");
		sb.append(priceProtectionStatus);
		sb.append(", recordLockStatus=");
		sb.append(recordLockStatus);
		sb.append(", resetEligible=");
		sb.append(resetEligible);
		sb.append(", batchId=");
		sb.append(batchId);
		sb.append(", priceToleranceInterval=");
		sb.append(priceToleranceInterval);
		sb.append(", netPriceType=");
		sb.append(netPriceType);
		sb.append(", priceRevision=");
		sb.append(priceRevision);
		sb.append(", resetFrequency=");
		sb.append(resetFrequency);
		sb.append(", resetInterval=");
		sb.append(resetInterval);
		sb.append(", basePriceType=");
		sb.append(basePriceType);
		sb.append(", basePriceEntry=");
		sb.append(basePriceEntry);
		sb.append(", basePriceDate=");
		sb.append(basePriceDate);
		sb.append(", netBasePrice=");
		sb.append(netBasePrice);
		sb.append(", basePriceDdlb=");
		sb.append(basePriceDdlb);
		sb.append(", subsequentPeriodPriceType=");
		sb.append(subsequentPeriodPriceType);
		sb.append(", netSubsequentPeriodPrice=");
		sb.append(netSubsequentPeriodPrice);
		sb.append(", netSubsequentPriceFormulaId=");
		sb.append(netSubsequentPriceFormulaId);
		sb.append(", resetPriceType=");
		sb.append(resetPriceType);
		sb.append(", netResetPriceType=");
		sb.append(netResetPriceType);
		sb.append(", netResetPriceFormulaId=");
		sb.append(netResetPriceFormulaId);
		sb.append(", netBasePriceFormulaId=");
		sb.append(netBasePriceFormulaId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public PsDetails toEntityModel() {
		PsDetailsImpl psDetailsImpl = new PsDetailsImpl();

		psDetailsImpl.setNepFormula(nepFormula);
		psDetailsImpl.setPrice(price);
		psDetailsImpl.setItemMasterSid(itemMasterSid);
		psDetailsImpl.setResetType(resetType);

		if (priceProtectionStartDate == Long.MIN_VALUE) {
			psDetailsImpl.setPriceProtectionStartDate(null);
		}
		else {
			psDetailsImpl.setPriceProtectionStartDate(new Date(
					priceProtectionStartDate));
		}

		if (resetDate == Long.MIN_VALUE) {
			psDetailsImpl.setResetDate(null);
		}
		else {
			psDetailsImpl.setResetDate(new Date(resetDate));
		}

		psDetailsImpl.setBasePrice(basePrice);

		if (itemPsAttachedDate == Long.MIN_VALUE) {
			psDetailsImpl.setItemPsAttachedDate(null);
		}
		else {
			psDetailsImpl.setItemPsAttachedDate(new Date(itemPsAttachedDate));
		}

		if (brandMasterSid == null) {
			psDetailsImpl.setBrandMasterSid(StringPool.BLANK);
		}
		else {
			psDetailsImpl.setBrandMasterSid(brandMasterSid);
		}

		psDetailsImpl.setStatus(status);

		if (modifiedDate == Long.MIN_VALUE) {
			psDetailsImpl.setModifiedDate(null);
		}
		else {
			psDetailsImpl.setModifiedDate(new Date(modifiedDate));
		}

		psDetailsImpl.setItemPsAttachedStatus(itemPsAttachedStatus);

		if (revisionDate == Long.MIN_VALUE) {
			psDetailsImpl.setRevisionDate(null);
		}
		else {
			psDetailsImpl.setRevisionDate(new Date(revisionDate));
		}

		psDetailsImpl.setPriceTolerance(priceTolerance);

		if (createdDate == Long.MIN_VALUE) {
			psDetailsImpl.setCreatedDate(null);
		}
		else {
			psDetailsImpl.setCreatedDate(new Date(createdDate));
		}

		psDetailsImpl.setCreatedBy(createdBy);

		if (source == null) {
			psDetailsImpl.setSource(StringPool.BLANK);
		}
		else {
			psDetailsImpl.setSource(source);
		}

		psDetailsImpl.setPsDetailsSid(psDetailsSid);
		psDetailsImpl.setPsModelSid(psModelSid);
		psDetailsImpl.setSuggestedPrice(suggestedPrice);

		if (netPriceTypeFormula == null) {
			psDetailsImpl.setNetPriceTypeFormula(StringPool.BLANK);
		}
		else {
			psDetailsImpl.setNetPriceTypeFormula(netPriceTypeFormula);
		}

		psDetailsImpl.setPriceProtectionPriceType(priceProtectionPriceType);
		psDetailsImpl.setModifiedBy(modifiedBy);

		if (inboundStatus == null) {
			psDetailsImpl.setInboundStatus(StringPool.BLANK);
		}
		else {
			psDetailsImpl.setInboundStatus(inboundStatus);
		}

		psDetailsImpl.setContractPrice(contractPrice);
		psDetailsImpl.setIfpModelSid(ifpModelSid);
		psDetailsImpl.setPriceToleranceType(priceToleranceType);
		psDetailsImpl.setMaxIncrementalChange(maxIncrementalChange);
		psDetailsImpl.setItemPricingQualifierSid(itemPricingQualifierSid);

		if (contractPriceEndDate == Long.MIN_VALUE) {
			psDetailsImpl.setContractPriceEndDate(null);
		}
		else {
			psDetailsImpl.setContractPriceEndDate(new Date(contractPriceEndDate));
		}

		psDetailsImpl.setNep(nep);

		if (contractPriceStartDate == Long.MIN_VALUE) {
			psDetailsImpl.setContractPriceStartDate(null);
		}
		else {
			psDetailsImpl.setContractPriceStartDate(new Date(
					contractPriceStartDate));
		}

		psDetailsImpl.setPriceToleranceFrequency(priceToleranceFrequency);

		if (priceProtectionEndDate == Long.MIN_VALUE) {
			psDetailsImpl.setPriceProtectionEndDate(null);
		}
		else {
			psDetailsImpl.setPriceProtectionEndDate(new Date(
					priceProtectionEndDate));
		}

		psDetailsImpl.setPriceProtectionStatus(priceProtectionStatus);
		psDetailsImpl.setRecordLockStatus(recordLockStatus);
		psDetailsImpl.setResetEligible(resetEligible);

		if (batchId == null) {
			psDetailsImpl.setBatchId(StringPool.BLANK);
		}
		else {
			psDetailsImpl.setBatchId(batchId);
		}

		psDetailsImpl.setPriceToleranceInterval(priceToleranceInterval);
		psDetailsImpl.setNetPriceType(netPriceType);
		psDetailsImpl.setPriceRevision(priceRevision);
		psDetailsImpl.setResetFrequency(resetFrequency);
		psDetailsImpl.setResetInterval(resetInterval);
		psDetailsImpl.setBasePriceType(basePriceType);
		psDetailsImpl.setBasePriceEntry(basePriceEntry);

		if (basePriceDate == Long.MIN_VALUE) {
			psDetailsImpl.setBasePriceDate(null);
		}
		else {
			psDetailsImpl.setBasePriceDate(new Date(basePriceDate));
		}

		psDetailsImpl.setNetBasePrice(netBasePrice);
		psDetailsImpl.setBasePriceDdlb(basePriceDdlb);
		psDetailsImpl.setSubsequentPeriodPriceType(subsequentPeriodPriceType);
		psDetailsImpl.setNetSubsequentPeriodPrice(netSubsequentPeriodPrice);
		psDetailsImpl.setNetSubsequentPriceFormulaId(netSubsequentPriceFormulaId);
		psDetailsImpl.setResetPriceType(resetPriceType);
		psDetailsImpl.setNetResetPriceType(netResetPriceType);
		psDetailsImpl.setNetResetPriceFormulaId(netResetPriceFormulaId);
		psDetailsImpl.setNetBasePriceFormulaId(netBasePriceFormulaId);

		psDetailsImpl.resetOriginalValues();

		return psDetailsImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		nepFormula = objectInput.readInt();

		price = objectInput.readDouble();

		itemMasterSid = objectInput.readInt();

		resetType = objectInput.readInt();
		priceProtectionStartDate = objectInput.readLong();
		resetDate = objectInput.readLong();

		basePrice = objectInput.readDouble();
		itemPsAttachedDate = objectInput.readLong();
		brandMasterSid = objectInput.readUTF();

		status = objectInput.readInt();
		modifiedDate = objectInput.readLong();

		itemPsAttachedStatus = objectInput.readInt();
		revisionDate = objectInput.readLong();

		priceTolerance = objectInput.readDouble();
		createdDate = objectInput.readLong();

		createdBy = objectInput.readInt();
		source = objectInput.readUTF();

		psDetailsSid = objectInput.readInt();

		psModelSid = objectInput.readInt();

		suggestedPrice = objectInput.readDouble();
		netPriceTypeFormula = objectInput.readUTF();

		priceProtectionPriceType = objectInput.readInt();

		modifiedBy = objectInput.readInt();
		inboundStatus = objectInput.readUTF();

		contractPrice = objectInput.readDouble();

		ifpModelSid = objectInput.readInt();

		priceToleranceType = objectInput.readInt();

		maxIncrementalChange = objectInput.readDouble();

		itemPricingQualifierSid = objectInput.readInt();
		contractPriceEndDate = objectInput.readLong();

		nep = objectInput.readDouble();
		contractPriceStartDate = objectInput.readLong();

		priceToleranceFrequency = objectInput.readInt();
		priceProtectionEndDate = objectInput.readLong();

		priceProtectionStatus = objectInput.readInt();

		recordLockStatus = objectInput.readBoolean();

		resetEligible = objectInput.readInt();
		batchId = objectInput.readUTF();

		priceToleranceInterval = objectInput.readInt();

		netPriceType = objectInput.readInt();

		priceRevision = objectInput.readDouble();

		resetFrequency = objectInput.readInt();

		resetInterval = objectInput.readInt();

		basePriceType = objectInput.readInt();

		basePriceEntry = objectInput.readDouble();
		basePriceDate = objectInput.readLong();

		netBasePrice = objectInput.readInt();

		basePriceDdlb = objectInput.readInt();

		subsequentPeriodPriceType = objectInput.readInt();

		netSubsequentPeriodPrice = objectInput.readInt();

		netSubsequentPriceFormulaId = objectInput.readInt();

		resetPriceType = objectInput.readInt();

		netResetPriceType = objectInput.readInt();

		netResetPriceFormulaId = objectInput.readInt();

		netBasePriceFormulaId = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeInt(nepFormula);

		objectOutput.writeDouble(price);

		objectOutput.writeInt(itemMasterSid);

		objectOutput.writeInt(resetType);
		objectOutput.writeLong(priceProtectionStartDate);
		objectOutput.writeLong(resetDate);

		objectOutput.writeDouble(basePrice);
		objectOutput.writeLong(itemPsAttachedDate);

		if (brandMasterSid == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(brandMasterSid);
		}

		objectOutput.writeInt(status);
		objectOutput.writeLong(modifiedDate);

		objectOutput.writeInt(itemPsAttachedStatus);
		objectOutput.writeLong(revisionDate);

		objectOutput.writeDouble(priceTolerance);
		objectOutput.writeLong(createdDate);

		objectOutput.writeInt(createdBy);

		if (source == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(source);
		}

		objectOutput.writeInt(psDetailsSid);

		objectOutput.writeInt(psModelSid);

		objectOutput.writeDouble(suggestedPrice);

		if (netPriceTypeFormula == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(netPriceTypeFormula);
		}

		objectOutput.writeInt(priceProtectionPriceType);

		objectOutput.writeInt(modifiedBy);

		if (inboundStatus == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(inboundStatus);
		}

		objectOutput.writeDouble(contractPrice);

		objectOutput.writeInt(ifpModelSid);

		objectOutput.writeInt(priceToleranceType);

		objectOutput.writeDouble(maxIncrementalChange);

		objectOutput.writeInt(itemPricingQualifierSid);
		objectOutput.writeLong(contractPriceEndDate);

		objectOutput.writeDouble(nep);
		objectOutput.writeLong(contractPriceStartDate);

		objectOutput.writeInt(priceToleranceFrequency);
		objectOutput.writeLong(priceProtectionEndDate);

		objectOutput.writeInt(priceProtectionStatus);

		objectOutput.writeBoolean(recordLockStatus);

		objectOutput.writeInt(resetEligible);

		if (batchId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(batchId);
		}

		objectOutput.writeInt(priceToleranceInterval);

		objectOutput.writeInt(netPriceType);

		objectOutput.writeDouble(priceRevision);

		objectOutput.writeInt(resetFrequency);

		objectOutput.writeInt(resetInterval);

		objectOutput.writeInt(basePriceType);

		objectOutput.writeDouble(basePriceEntry);
		objectOutput.writeLong(basePriceDate);

		objectOutput.writeInt(netBasePrice);

		objectOutput.writeInt(basePriceDdlb);

		objectOutput.writeInt(subsequentPeriodPriceType);

		objectOutput.writeInt(netSubsequentPeriodPrice);

		objectOutput.writeInt(netSubsequentPriceFormulaId);

		objectOutput.writeInt(resetPriceType);

		objectOutput.writeInt(netResetPriceType);

		objectOutput.writeInt(netResetPriceFormulaId);

		objectOutput.writeInt(netBasePriceFormulaId);
	}

	public int nepFormula;
	public double price;
	public int itemMasterSid;
	public int resetType;
	public long priceProtectionStartDate;
	public long resetDate;
	public double basePrice;
	public long itemPsAttachedDate;
	public String brandMasterSid;
	public int status;
	public long modifiedDate;
	public int itemPsAttachedStatus;
	public long revisionDate;
	public double priceTolerance;
	public long createdDate;
	public int createdBy;
	public String source;
	public int psDetailsSid;
	public int psModelSid;
	public double suggestedPrice;
	public String netPriceTypeFormula;
	public int priceProtectionPriceType;
	public int modifiedBy;
	public String inboundStatus;
	public double contractPrice;
	public int ifpModelSid;
	public int priceToleranceType;
	public double maxIncrementalChange;
	public int itemPricingQualifierSid;
	public long contractPriceEndDate;
	public double nep;
	public long contractPriceStartDate;
	public int priceToleranceFrequency;
	public long priceProtectionEndDate;
	public int priceProtectionStatus;
	public boolean recordLockStatus;
	public int resetEligible;
	public String batchId;
	public int priceToleranceInterval;
	public int netPriceType;
	public double priceRevision;
	public int resetFrequency;
	public int resetInterval;
	public int basePriceType;
	public double basePriceEntry;
	public long basePriceDate;
	public int netBasePrice;
	public int basePriceDdlb;
	public int subsequentPeriodPriceType;
	public int netSubsequentPeriodPrice;
	public int netSubsequentPriceFormulaId;
	public int resetPriceType;
	public int netResetPriceType;
	public int netResetPriceFormulaId;
	public int netBasePriceFormulaId;
}