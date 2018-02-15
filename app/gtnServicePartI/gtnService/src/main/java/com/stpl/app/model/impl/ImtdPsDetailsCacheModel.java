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

import com.stpl.app.model.ImtdPsDetails;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing ImtdPsDetails in entity cache.
 *
 * @author
 * @see ImtdPsDetails
 * @generated
 */
@ProviderType
public class ImtdPsDetailsCacheModel implements CacheModel<ImtdPsDetails>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ImtdPsDetailsCacheModel)) {
			return false;
		}

		ImtdPsDetailsCacheModel imtdPsDetailsCacheModel = (ImtdPsDetailsCacheModel)obj;

		if (imtdPsDetailsSid == imtdPsDetailsCacheModel.imtdPsDetailsSid) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, imtdPsDetailsSid);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(149);

		sb.append("{psDetailsModifiedDate=");
		sb.append(psDetailsModifiedDate);
		sb.append(", psDetailsSuggestedPrice=");
		sb.append(psDetailsSuggestedPrice);
		sb.append(", psDetailsContractPrice=");
		sb.append(psDetailsContractPrice);
		sb.append(", resetDate=");
		sb.append(resetDate);
		sb.append(", psDetailsAttachedStatus=");
		sb.append(psDetailsAttachedStatus);
		sb.append(", imtdPsDetailsSid=");
		sb.append(imtdPsDetailsSid);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", psDetailsCreatedBy=");
		sb.append(psDetailsCreatedBy);
		sb.append(", contractMasterSid=");
		sb.append(contractMasterSid);
		sb.append(", psDtlsContPriceEnddate=");
		sb.append(psDtlsContPriceEnddate);
		sb.append(", psDetailsPricPrtcnStdate=");
		sb.append(psDetailsPricPrtcnStdate);
		sb.append(", imtdCreatedDate=");
		sb.append(imtdCreatedDate);
		sb.append(", netPriceTypeFormula=");
		sb.append(netPriceTypeFormula);
		sb.append(", modifiedBy=");
		sb.append(modifiedBy);
		sb.append(", maxIncrementalChange=");
		sb.append(maxIncrementalChange);
		sb.append(", psDetailsPricePlanId=");
		sb.append(psDetailsPricePlanId);
		sb.append(", checkRecord=");
		sb.append(checkRecord);
		sb.append(", psDtlsPriceToleranceFreq=");
		sb.append(psDtlsPriceToleranceFreq);
		sb.append(", itemName=");
		sb.append(itemName);
		sb.append(", sessionId=");
		sb.append(sessionId);
		sb.append(", resetFrequency=");
		sb.append(resetFrequency);
		sb.append(", psDtlsPriceToleranceType=");
		sb.append(psDtlsPriceToleranceType);
		sb.append(", psDetailsPricetype=");
		sb.append(psDetailsPricetype);
		sb.append(", psDetailsPriceRevision=");
		sb.append(psDetailsPriceRevision);
		sb.append(", resetInterval=");
		sb.append(resetInterval);
		sb.append(", ifpNo=");
		sb.append(ifpNo);
		sb.append(", psDetailsAttachedDate=");
		sb.append(psDetailsAttachedDate);
		sb.append(", nepFormula=");
		sb.append(nepFormula);
		sb.append(", psDetailsModifiedBy=");
		sb.append(psDetailsModifiedBy);
		sb.append(", psDtlsPriceToleranceIntrvl=");
		sb.append(psDtlsPriceToleranceIntrvl);
		sb.append(", itemMasterSid=");
		sb.append(itemMasterSid);
		sb.append(", resetType=");
		sb.append(resetType);
		sb.append(", itemId=");
		sb.append(itemId);
		sb.append(", status=");
		sb.append(status);
		sb.append(", brandMasterSid=");
		sb.append(brandMasterSid);
		sb.append(", psDetailsPrice=");
		sb.append(psDetailsPrice);
		sb.append(", psDetailsCreatedDate=");
		sb.append(psDetailsCreatedDate);
		sb.append(", usersSid=");
		sb.append(usersSid);
		sb.append(", createdBy=");
		sb.append(createdBy);
		sb.append(", createdDate=");
		sb.append(createdDate);
		sb.append(", psDetailsSid=");
		sb.append(psDetailsSid);
		sb.append(", psModelSid=");
		sb.append(psModelSid);
		sb.append(", priceProtectionPriceType=");
		sb.append(priceProtectionPriceType);
		sb.append(", psDetailsBasePrice=");
		sb.append(psDetailsBasePrice);
		sb.append(", itemNo=");
		sb.append(itemNo);
		sb.append(", ifpModelSid=");
		sb.append(ifpModelSid);
		sb.append(", psDetailsRevisionDate=");
		sb.append(psDetailsRevisionDate);
		sb.append(", nep=");
		sb.append(nep);
		sb.append(", psDetailsPriceTolerance=");
		sb.append(psDetailsPriceTolerance);
		sb.append(", priceProtectionStatus=");
		sb.append(priceProtectionStatus);
		sb.append(", psDtlsContPriceStartdate=");
		sb.append(psDtlsContPriceStartdate);
		sb.append(", resetEligible=");
		sb.append(resetEligible);
		sb.append(", netPriceType=");
		sb.append(netPriceType);
		sb.append(", operation=");
		sb.append(operation);
		sb.append(", cfpModelSid=");
		sb.append(cfpModelSid);
		sb.append(", psDetailsPricPrtcnEddate=");
		sb.append(psDetailsPricPrtcnEddate);
		sb.append(", basePriceType=");
		sb.append(basePriceType);
		sb.append(", basePriceEntry=");
		sb.append(basePriceEntry);
		sb.append(", basePriceDate=");
		sb.append(basePriceDate);
		sb.append(", basePriceDdlb=");
		sb.append(basePriceDdlb);
		sb.append(", netBasePrice=");
		sb.append(netBasePrice);
		sb.append(", netBasePriceFormulaId=");
		sb.append(netBasePriceFormulaId);
		sb.append(", netBasePriceFormulaNo=");
		sb.append(netBasePriceFormulaNo);
		sb.append(", netBasePriceFormulaName=");
		sb.append(netBasePriceFormulaName);
		sb.append(", subsequentPeriodPriceType=");
		sb.append(subsequentPeriodPriceType);
		sb.append(", netSubsequentPeriodPrice=");
		sb.append(netSubsequentPeriodPrice);
		sb.append(", netSubsequentPriceFormulaId=");
		sb.append(netSubsequentPriceFormulaId);
		sb.append(", netSubsequentPriceFormulaNo=");
		sb.append(netSubsequentPriceFormulaNo);
		sb.append(", netSubsequentPriceFormulaName=");
		sb.append(netSubsequentPriceFormulaName);
		sb.append(", resetPriceType=");
		sb.append(resetPriceType);
		sb.append(", netResetPriceType=");
		sb.append(netResetPriceType);
		sb.append(", netResetPriceFormulaId=");
		sb.append(netResetPriceFormulaId);
		sb.append(", netResetPriceFormulaNo=");
		sb.append(netResetPriceFormulaNo);
		sb.append(", netResetPriceFormulaName=");
		sb.append(netResetPriceFormulaName);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ImtdPsDetails toEntityModel() {
		ImtdPsDetailsImpl imtdPsDetailsImpl = new ImtdPsDetailsImpl();

		if (psDetailsModifiedDate == Long.MIN_VALUE) {
			imtdPsDetailsImpl.setPsDetailsModifiedDate(null);
		}
		else {
			imtdPsDetailsImpl.setPsDetailsModifiedDate(new Date(
					psDetailsModifiedDate));
		}

		imtdPsDetailsImpl.setPsDetailsSuggestedPrice(psDetailsSuggestedPrice);
		imtdPsDetailsImpl.setPsDetailsContractPrice(psDetailsContractPrice);

		if (resetDate == Long.MIN_VALUE) {
			imtdPsDetailsImpl.setResetDate(null);
		}
		else {
			imtdPsDetailsImpl.setResetDate(new Date(resetDate));
		}

		imtdPsDetailsImpl.setPsDetailsAttachedStatus(psDetailsAttachedStatus);
		imtdPsDetailsImpl.setImtdPsDetailsSid(imtdPsDetailsSid);

		if (modifiedDate == Long.MIN_VALUE) {
			imtdPsDetailsImpl.setModifiedDate(null);
		}
		else {
			imtdPsDetailsImpl.setModifiedDate(new Date(modifiedDate));
		}

		imtdPsDetailsImpl.setPsDetailsCreatedBy(psDetailsCreatedBy);
		imtdPsDetailsImpl.setContractMasterSid(contractMasterSid);

		if (psDtlsContPriceEnddate == Long.MIN_VALUE) {
			imtdPsDetailsImpl.setPsDtlsContPriceEnddate(null);
		}
		else {
			imtdPsDetailsImpl.setPsDtlsContPriceEnddate(new Date(
					psDtlsContPriceEnddate));
		}

		if (psDetailsPricPrtcnStdate == Long.MIN_VALUE) {
			imtdPsDetailsImpl.setPsDetailsPricPrtcnStdate(null);
		}
		else {
			imtdPsDetailsImpl.setPsDetailsPricPrtcnStdate(new Date(
					psDetailsPricPrtcnStdate));
		}

		if (imtdCreatedDate == Long.MIN_VALUE) {
			imtdPsDetailsImpl.setImtdCreatedDate(null);
		}
		else {
			imtdPsDetailsImpl.setImtdCreatedDate(new Date(imtdCreatedDate));
		}

		if (netPriceTypeFormula == null) {
			imtdPsDetailsImpl.setNetPriceTypeFormula(StringPool.BLANK);
		}
		else {
			imtdPsDetailsImpl.setNetPriceTypeFormula(netPriceTypeFormula);
		}

		imtdPsDetailsImpl.setModifiedBy(modifiedBy);
		imtdPsDetailsImpl.setMaxIncrementalChange(maxIncrementalChange);

		if (psDetailsPricePlanId == null) {
			imtdPsDetailsImpl.setPsDetailsPricePlanId(StringPool.BLANK);
		}
		else {
			imtdPsDetailsImpl.setPsDetailsPricePlanId(psDetailsPricePlanId);
		}

		imtdPsDetailsImpl.setCheckRecord(checkRecord);
		imtdPsDetailsImpl.setPsDtlsPriceToleranceFreq(psDtlsPriceToleranceFreq);

		if (itemName == null) {
			imtdPsDetailsImpl.setItemName(StringPool.BLANK);
		}
		else {
			imtdPsDetailsImpl.setItemName(itemName);
		}

		if (sessionId == null) {
			imtdPsDetailsImpl.setSessionId(StringPool.BLANK);
		}
		else {
			imtdPsDetailsImpl.setSessionId(sessionId);
		}

		imtdPsDetailsImpl.setResetFrequency(resetFrequency);
		imtdPsDetailsImpl.setPsDtlsPriceToleranceType(psDtlsPriceToleranceType);
		imtdPsDetailsImpl.setPsDetailsPricetype(psDetailsPricetype);
		imtdPsDetailsImpl.setPsDetailsPriceRevision(psDetailsPriceRevision);
		imtdPsDetailsImpl.setResetInterval(resetInterval);

		if (ifpNo == null) {
			imtdPsDetailsImpl.setIfpNo(StringPool.BLANK);
		}
		else {
			imtdPsDetailsImpl.setIfpNo(ifpNo);
		}

		if (psDetailsAttachedDate == Long.MIN_VALUE) {
			imtdPsDetailsImpl.setPsDetailsAttachedDate(null);
		}
		else {
			imtdPsDetailsImpl.setPsDetailsAttachedDate(new Date(
					psDetailsAttachedDate));
		}

		imtdPsDetailsImpl.setNepFormula(nepFormula);
		imtdPsDetailsImpl.setPsDetailsModifiedBy(psDetailsModifiedBy);
		imtdPsDetailsImpl.setPsDtlsPriceToleranceIntrvl(psDtlsPriceToleranceIntrvl);
		imtdPsDetailsImpl.setItemMasterSid(itemMasterSid);
		imtdPsDetailsImpl.setResetType(resetType);

		if (itemId == null) {
			imtdPsDetailsImpl.setItemId(StringPool.BLANK);
		}
		else {
			imtdPsDetailsImpl.setItemId(itemId);
		}

		imtdPsDetailsImpl.setStatus(status);
		imtdPsDetailsImpl.setBrandMasterSid(brandMasterSid);
		imtdPsDetailsImpl.setPsDetailsPrice(psDetailsPrice);

		if (psDetailsCreatedDate == Long.MIN_VALUE) {
			imtdPsDetailsImpl.setPsDetailsCreatedDate(null);
		}
		else {
			imtdPsDetailsImpl.setPsDetailsCreatedDate(new Date(
					psDetailsCreatedDate));
		}

		imtdPsDetailsImpl.setUsersSid(usersSid);
		imtdPsDetailsImpl.setCreatedBy(createdBy);

		if (createdDate == Long.MIN_VALUE) {
			imtdPsDetailsImpl.setCreatedDate(null);
		}
		else {
			imtdPsDetailsImpl.setCreatedDate(new Date(createdDate));
		}

		imtdPsDetailsImpl.setPsDetailsSid(psDetailsSid);
		imtdPsDetailsImpl.setPsModelSid(psModelSid);
		imtdPsDetailsImpl.setPriceProtectionPriceType(priceProtectionPriceType);
		imtdPsDetailsImpl.setPsDetailsBasePrice(psDetailsBasePrice);

		if (itemNo == null) {
			imtdPsDetailsImpl.setItemNo(StringPool.BLANK);
		}
		else {
			imtdPsDetailsImpl.setItemNo(itemNo);
		}

		imtdPsDetailsImpl.setIfpModelSid(ifpModelSid);

		if (psDetailsRevisionDate == Long.MIN_VALUE) {
			imtdPsDetailsImpl.setPsDetailsRevisionDate(null);
		}
		else {
			imtdPsDetailsImpl.setPsDetailsRevisionDate(new Date(
					psDetailsRevisionDate));
		}

		imtdPsDetailsImpl.setNep(nep);
		imtdPsDetailsImpl.setPsDetailsPriceTolerance(psDetailsPriceTolerance);
		imtdPsDetailsImpl.setPriceProtectionStatus(priceProtectionStatus);

		if (psDtlsContPriceStartdate == Long.MIN_VALUE) {
			imtdPsDetailsImpl.setPsDtlsContPriceStartdate(null);
		}
		else {
			imtdPsDetailsImpl.setPsDtlsContPriceStartdate(new Date(
					psDtlsContPriceStartdate));
		}

		imtdPsDetailsImpl.setResetEligible(resetEligible);
		imtdPsDetailsImpl.setNetPriceType(netPriceType);

		if (operation == null) {
			imtdPsDetailsImpl.setOperation(StringPool.BLANK);
		}
		else {
			imtdPsDetailsImpl.setOperation(operation);
		}

		imtdPsDetailsImpl.setCfpModelSid(cfpModelSid);

		if (psDetailsPricPrtcnEddate == Long.MIN_VALUE) {
			imtdPsDetailsImpl.setPsDetailsPricPrtcnEddate(null);
		}
		else {
			imtdPsDetailsImpl.setPsDetailsPricPrtcnEddate(new Date(
					psDetailsPricPrtcnEddate));
		}

		imtdPsDetailsImpl.setBasePriceType(basePriceType);
		imtdPsDetailsImpl.setBasePriceEntry(basePriceEntry);

		if (basePriceDate == Long.MIN_VALUE) {
			imtdPsDetailsImpl.setBasePriceDate(null);
		}
		else {
			imtdPsDetailsImpl.setBasePriceDate(new Date(basePriceDate));
		}

		imtdPsDetailsImpl.setBasePriceDdlb(basePriceDdlb);
		imtdPsDetailsImpl.setNetBasePrice(netBasePrice);
		imtdPsDetailsImpl.setNetBasePriceFormulaId(netBasePriceFormulaId);

		if (netBasePriceFormulaNo == null) {
			imtdPsDetailsImpl.setNetBasePriceFormulaNo(StringPool.BLANK);
		}
		else {
			imtdPsDetailsImpl.setNetBasePriceFormulaNo(netBasePriceFormulaNo);
		}

		if (netBasePriceFormulaName == null) {
			imtdPsDetailsImpl.setNetBasePriceFormulaName(StringPool.BLANK);
		}
		else {
			imtdPsDetailsImpl.setNetBasePriceFormulaName(netBasePriceFormulaName);
		}

		imtdPsDetailsImpl.setSubsequentPeriodPriceType(subsequentPeriodPriceType);
		imtdPsDetailsImpl.setNetSubsequentPeriodPrice(netSubsequentPeriodPrice);
		imtdPsDetailsImpl.setNetSubsequentPriceFormulaId(netSubsequentPriceFormulaId);

		if (netSubsequentPriceFormulaNo == null) {
			imtdPsDetailsImpl.setNetSubsequentPriceFormulaNo(StringPool.BLANK);
		}
		else {
			imtdPsDetailsImpl.setNetSubsequentPriceFormulaNo(netSubsequentPriceFormulaNo);
		}

		if (netSubsequentPriceFormulaName == null) {
			imtdPsDetailsImpl.setNetSubsequentPriceFormulaName(StringPool.BLANK);
		}
		else {
			imtdPsDetailsImpl.setNetSubsequentPriceFormulaName(netSubsequentPriceFormulaName);
		}

		imtdPsDetailsImpl.setResetPriceType(resetPriceType);
		imtdPsDetailsImpl.setNetResetPriceType(netResetPriceType);
		imtdPsDetailsImpl.setNetResetPriceFormulaId(netResetPriceFormulaId);

		if (netResetPriceFormulaNo == null) {
			imtdPsDetailsImpl.setNetResetPriceFormulaNo(StringPool.BLANK);
		}
		else {
			imtdPsDetailsImpl.setNetResetPriceFormulaNo(netResetPriceFormulaNo);
		}

		if (netResetPriceFormulaName == null) {
			imtdPsDetailsImpl.setNetResetPriceFormulaName(StringPool.BLANK);
		}
		else {
			imtdPsDetailsImpl.setNetResetPriceFormulaName(netResetPriceFormulaName);
		}

		imtdPsDetailsImpl.resetOriginalValues();

		return imtdPsDetailsImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		psDetailsModifiedDate = objectInput.readLong();

		psDetailsSuggestedPrice = objectInput.readDouble();

		psDetailsContractPrice = objectInput.readDouble();
		resetDate = objectInput.readLong();

		psDetailsAttachedStatus = objectInput.readInt();

		imtdPsDetailsSid = objectInput.readInt();
		modifiedDate = objectInput.readLong();

		psDetailsCreatedBy = objectInput.readInt();

		contractMasterSid = objectInput.readInt();
		psDtlsContPriceEnddate = objectInput.readLong();
		psDetailsPricPrtcnStdate = objectInput.readLong();
		imtdCreatedDate = objectInput.readLong();
		netPriceTypeFormula = objectInput.readUTF();

		modifiedBy = objectInput.readInt();

		maxIncrementalChange = objectInput.readDouble();
		psDetailsPricePlanId = objectInput.readUTF();

		checkRecord = objectInput.readBoolean();

		psDtlsPriceToleranceFreq = objectInput.readInt();
		itemName = objectInput.readUTF();
		sessionId = objectInput.readUTF();

		resetFrequency = objectInput.readInt();

		psDtlsPriceToleranceType = objectInput.readInt();

		psDetailsPricetype = objectInput.readInt();

		psDetailsPriceRevision = objectInput.readDouble();

		resetInterval = objectInput.readInt();
		ifpNo = objectInput.readUTF();
		psDetailsAttachedDate = objectInput.readLong();

		nepFormula = objectInput.readInt();

		psDetailsModifiedBy = objectInput.readInt();

		psDtlsPriceToleranceIntrvl = objectInput.readInt();

		itemMasterSid = objectInput.readInt();

		resetType = objectInput.readInt();
		itemId = objectInput.readUTF();

		status = objectInput.readInt();

		brandMasterSid = objectInput.readInt();

		psDetailsPrice = objectInput.readDouble();
		psDetailsCreatedDate = objectInput.readLong();

		usersSid = objectInput.readInt();

		createdBy = objectInput.readInt();
		createdDate = objectInput.readLong();

		psDetailsSid = objectInput.readInt();

		psModelSid = objectInput.readInt();

		priceProtectionPriceType = objectInput.readInt();

		psDetailsBasePrice = objectInput.readDouble();
		itemNo = objectInput.readUTF();

		ifpModelSid = objectInput.readInt();
		psDetailsRevisionDate = objectInput.readLong();

		nep = objectInput.readDouble();

		psDetailsPriceTolerance = objectInput.readDouble();

		priceProtectionStatus = objectInput.readInt();
		psDtlsContPriceStartdate = objectInput.readLong();

		resetEligible = objectInput.readInt();

		netPriceType = objectInput.readInt();
		operation = objectInput.readUTF();

		cfpModelSid = objectInput.readInt();
		psDetailsPricPrtcnEddate = objectInput.readLong();

		basePriceType = objectInput.readInt();

		basePriceEntry = objectInput.readDouble();
		basePriceDate = objectInput.readLong();

		basePriceDdlb = objectInput.readInt();

		netBasePrice = objectInput.readInt();

		netBasePriceFormulaId = objectInput.readInt();
		netBasePriceFormulaNo = objectInput.readUTF();
		netBasePriceFormulaName = objectInput.readUTF();

		subsequentPeriodPriceType = objectInput.readInt();

		netSubsequentPeriodPrice = objectInput.readInt();

		netSubsequentPriceFormulaId = objectInput.readInt();
		netSubsequentPriceFormulaNo = objectInput.readUTF();
		netSubsequentPriceFormulaName = objectInput.readUTF();

		resetPriceType = objectInput.readInt();

		netResetPriceType = objectInput.readInt();

		netResetPriceFormulaId = objectInput.readInt();
		netResetPriceFormulaNo = objectInput.readUTF();
		netResetPriceFormulaName = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(psDetailsModifiedDate);

		objectOutput.writeDouble(psDetailsSuggestedPrice);

		objectOutput.writeDouble(psDetailsContractPrice);
		objectOutput.writeLong(resetDate);

		objectOutput.writeInt(psDetailsAttachedStatus);

		objectOutput.writeInt(imtdPsDetailsSid);
		objectOutput.writeLong(modifiedDate);

		objectOutput.writeInt(psDetailsCreatedBy);

		objectOutput.writeInt(contractMasterSid);
		objectOutput.writeLong(psDtlsContPriceEnddate);
		objectOutput.writeLong(psDetailsPricPrtcnStdate);
		objectOutput.writeLong(imtdCreatedDate);

		if (netPriceTypeFormula == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(netPriceTypeFormula);
		}

		objectOutput.writeInt(modifiedBy);

		objectOutput.writeDouble(maxIncrementalChange);

		if (psDetailsPricePlanId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(psDetailsPricePlanId);
		}

		objectOutput.writeBoolean(checkRecord);

		objectOutput.writeInt(psDtlsPriceToleranceFreq);

		if (itemName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(itemName);
		}

		if (sessionId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(sessionId);
		}

		objectOutput.writeInt(resetFrequency);

		objectOutput.writeInt(psDtlsPriceToleranceType);

		objectOutput.writeInt(psDetailsPricetype);

		objectOutput.writeDouble(psDetailsPriceRevision);

		objectOutput.writeInt(resetInterval);

		if (ifpNo == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(ifpNo);
		}

		objectOutput.writeLong(psDetailsAttachedDate);

		objectOutput.writeInt(nepFormula);

		objectOutput.writeInt(psDetailsModifiedBy);

		objectOutput.writeInt(psDtlsPriceToleranceIntrvl);

		objectOutput.writeInt(itemMasterSid);

		objectOutput.writeInt(resetType);

		if (itemId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(itemId);
		}

		objectOutput.writeInt(status);

		objectOutput.writeInt(brandMasterSid);

		objectOutput.writeDouble(psDetailsPrice);
		objectOutput.writeLong(psDetailsCreatedDate);

		objectOutput.writeInt(usersSid);

		objectOutput.writeInt(createdBy);
		objectOutput.writeLong(createdDate);

		objectOutput.writeInt(psDetailsSid);

		objectOutput.writeInt(psModelSid);

		objectOutput.writeInt(priceProtectionPriceType);

		objectOutput.writeDouble(psDetailsBasePrice);

		if (itemNo == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(itemNo);
		}

		objectOutput.writeInt(ifpModelSid);
		objectOutput.writeLong(psDetailsRevisionDate);

		objectOutput.writeDouble(nep);

		objectOutput.writeDouble(psDetailsPriceTolerance);

		objectOutput.writeInt(priceProtectionStatus);
		objectOutput.writeLong(psDtlsContPriceStartdate);

		objectOutput.writeInt(resetEligible);

		objectOutput.writeInt(netPriceType);

		if (operation == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(operation);
		}

		objectOutput.writeInt(cfpModelSid);
		objectOutput.writeLong(psDetailsPricPrtcnEddate);

		objectOutput.writeInt(basePriceType);

		objectOutput.writeDouble(basePriceEntry);
		objectOutput.writeLong(basePriceDate);

		objectOutput.writeInt(basePriceDdlb);

		objectOutput.writeInt(netBasePrice);

		objectOutput.writeInt(netBasePriceFormulaId);

		if (netBasePriceFormulaNo == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(netBasePriceFormulaNo);
		}

		if (netBasePriceFormulaName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(netBasePriceFormulaName);
		}

		objectOutput.writeInt(subsequentPeriodPriceType);

		objectOutput.writeInt(netSubsequentPeriodPrice);

		objectOutput.writeInt(netSubsequentPriceFormulaId);

		if (netSubsequentPriceFormulaNo == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(netSubsequentPriceFormulaNo);
		}

		if (netSubsequentPriceFormulaName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(netSubsequentPriceFormulaName);
		}

		objectOutput.writeInt(resetPriceType);

		objectOutput.writeInt(netResetPriceType);

		objectOutput.writeInt(netResetPriceFormulaId);

		if (netResetPriceFormulaNo == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(netResetPriceFormulaNo);
		}

		if (netResetPriceFormulaName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(netResetPriceFormulaName);
		}
	}

	public long psDetailsModifiedDate;
	public double psDetailsSuggestedPrice;
	public double psDetailsContractPrice;
	public long resetDate;
	public int psDetailsAttachedStatus;
	public int imtdPsDetailsSid;
	public long modifiedDate;
	public int psDetailsCreatedBy;
	public int contractMasterSid;
	public long psDtlsContPriceEnddate;
	public long psDetailsPricPrtcnStdate;
	public long imtdCreatedDate;
	public String netPriceTypeFormula;
	public int modifiedBy;
	public double maxIncrementalChange;
	public String psDetailsPricePlanId;
	public boolean checkRecord;
	public int psDtlsPriceToleranceFreq;
	public String itemName;
	public String sessionId;
	public int resetFrequency;
	public int psDtlsPriceToleranceType;
	public int psDetailsPricetype;
	public double psDetailsPriceRevision;
	public int resetInterval;
	public String ifpNo;
	public long psDetailsAttachedDate;
	public int nepFormula;
	public int psDetailsModifiedBy;
	public int psDtlsPriceToleranceIntrvl;
	public int itemMasterSid;
	public int resetType;
	public String itemId;
	public int status;
	public int brandMasterSid;
	public double psDetailsPrice;
	public long psDetailsCreatedDate;
	public int usersSid;
	public int createdBy;
	public long createdDate;
	public int psDetailsSid;
	public int psModelSid;
	public int priceProtectionPriceType;
	public double psDetailsBasePrice;
	public String itemNo;
	public int ifpModelSid;
	public long psDetailsRevisionDate;
	public double nep;
	public double psDetailsPriceTolerance;
	public int priceProtectionStatus;
	public long psDtlsContPriceStartdate;
	public int resetEligible;
	public int netPriceType;
	public String operation;
	public int cfpModelSid;
	public long psDetailsPricPrtcnEddate;
	public int basePriceType;
	public double basePriceEntry;
	public long basePriceDate;
	public int basePriceDdlb;
	public int netBasePrice;
	public int netBasePriceFormulaId;
	public String netBasePriceFormulaNo;
	public String netBasePriceFormulaName;
	public int subsequentPeriodPriceType;
	public int netSubsequentPeriodPrice;
	public int netSubsequentPriceFormulaId;
	public String netSubsequentPriceFormulaNo;
	public String netSubsequentPriceFormulaName;
	public int resetPriceType;
	public int netResetPriceType;
	public int netResetPriceFormulaId;
	public String netResetPriceFormulaNo;
	public String netResetPriceFormulaName;
}