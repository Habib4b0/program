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

import com.stpl.app.model.IvldBestPrice;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing IvldBestPrice in entity cache.
 *
 * @author
 * @see IvldBestPrice
 * @generated
 */
@ProviderType
public class IvldBestPriceCacheModel implements CacheModel<IvldBestPrice>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof IvldBestPriceCacheModel)) {
			return false;
		}

		IvldBestPriceCacheModel ivldBestPriceCacheModel = (IvldBestPriceCacheModel)obj;

		if (ivldBestPriceSid == ivldBestPriceCacheModel.ivldBestPriceSid) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, ivldBestPriceSid);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(57);

		sb.append("{itemDesc=");
		sb.append(itemDesc);
		sb.append(", bestPriceIntfid=");
		sb.append(bestPriceIntfid);
		sb.append(", accountId=");
		sb.append(accountId);
		sb.append(", sellingPrice=");
		sb.append(sellingPrice);
		sb.append(", period=");
		sb.append(period);
		sb.append(", itemId=");
		sb.append(itemId);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", createdBy=");
		sb.append(createdBy);
		sb.append(", createdDate=");
		sb.append(createdDate);
		sb.append(", source=");
		sb.append(source);
		sb.append(", addChgDelIndicator=");
		sb.append(addChgDelIndicator);
		sb.append(", initialDiscount=");
		sb.append(initialDiscount);
		sb.append(", errorCode=");
		sb.append(errorCode);
		sb.append(", modifiedBy=");
		sb.append(modifiedBy);
		sb.append(", intfInsertedDate=");
		sb.append(intfInsertedDate);
		sb.append(", itemNo=");
		sb.append(itemNo);
		sb.append(", reprocessedFlag=");
		sb.append(reprocessedFlag);
		sb.append(", contractEndDate=");
		sb.append(contractEndDate);
		sb.append(", ivldBestPriceSid=");
		sb.append(ivldBestPriceSid);
		sb.append(", contractId=");
		sb.append(contractId);
		sb.append(", beginningWacPackage=");
		sb.append(beginningWacPackage);
		sb.append(", reasonForFailure=");
		sb.append(reasonForFailure);
		sb.append(", contractStartDate=");
		sb.append(contractStartDate);
		sb.append(", batchId=");
		sb.append(batchId);
		sb.append(", errorField=");
		sb.append(errorField);
		sb.append(", initialBestPrice=");
		sb.append(initialBestPrice);
		sb.append(", contractNo=");
		sb.append(contractNo);
		sb.append(", checkRecord=");
		sb.append(checkRecord);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public IvldBestPrice toEntityModel() {
		IvldBestPriceImpl ivldBestPriceImpl = new IvldBestPriceImpl();

		if (itemDesc == null) {
			ivldBestPriceImpl.setItemDesc(StringPool.BLANK);
		}
		else {
			ivldBestPriceImpl.setItemDesc(itemDesc);
		}

		if (bestPriceIntfid == null) {
			ivldBestPriceImpl.setBestPriceIntfid(StringPool.BLANK);
		}
		else {
			ivldBestPriceImpl.setBestPriceIntfid(bestPriceIntfid);
		}

		if (accountId == null) {
			ivldBestPriceImpl.setAccountId(StringPool.BLANK);
		}
		else {
			ivldBestPriceImpl.setAccountId(accountId);
		}

		if (sellingPrice == null) {
			ivldBestPriceImpl.setSellingPrice(StringPool.BLANK);
		}
		else {
			ivldBestPriceImpl.setSellingPrice(sellingPrice);
		}

		if (period == null) {
			ivldBestPriceImpl.setPeriod(StringPool.BLANK);
		}
		else {
			ivldBestPriceImpl.setPeriod(period);
		}

		if (itemId == null) {
			ivldBestPriceImpl.setItemId(StringPool.BLANK);
		}
		else {
			ivldBestPriceImpl.setItemId(itemId);
		}

		if (modifiedDate == Long.MIN_VALUE) {
			ivldBestPriceImpl.setModifiedDate(null);
		}
		else {
			ivldBestPriceImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (createdBy == null) {
			ivldBestPriceImpl.setCreatedBy(StringPool.BLANK);
		}
		else {
			ivldBestPriceImpl.setCreatedBy(createdBy);
		}

		if (createdDate == Long.MIN_VALUE) {
			ivldBestPriceImpl.setCreatedDate(null);
		}
		else {
			ivldBestPriceImpl.setCreatedDate(new Date(createdDate));
		}

		if (source == null) {
			ivldBestPriceImpl.setSource(StringPool.BLANK);
		}
		else {
			ivldBestPriceImpl.setSource(source);
		}

		if (addChgDelIndicator == null) {
			ivldBestPriceImpl.setAddChgDelIndicator(StringPool.BLANK);
		}
		else {
			ivldBestPriceImpl.setAddChgDelIndicator(addChgDelIndicator);
		}

		if (initialDiscount == null) {
			ivldBestPriceImpl.setInitialDiscount(StringPool.BLANK);
		}
		else {
			ivldBestPriceImpl.setInitialDiscount(initialDiscount);
		}

		if (errorCode == null) {
			ivldBestPriceImpl.setErrorCode(StringPool.BLANK);
		}
		else {
			ivldBestPriceImpl.setErrorCode(errorCode);
		}

		if (modifiedBy == null) {
			ivldBestPriceImpl.setModifiedBy(StringPool.BLANK);
		}
		else {
			ivldBestPriceImpl.setModifiedBy(modifiedBy);
		}

		if (intfInsertedDate == Long.MIN_VALUE) {
			ivldBestPriceImpl.setIntfInsertedDate(null);
		}
		else {
			ivldBestPriceImpl.setIntfInsertedDate(new Date(intfInsertedDate));
		}

		if (itemNo == null) {
			ivldBestPriceImpl.setItemNo(StringPool.BLANK);
		}
		else {
			ivldBestPriceImpl.setItemNo(itemNo);
		}

		if (reprocessedFlag == null) {
			ivldBestPriceImpl.setReprocessedFlag(StringPool.BLANK);
		}
		else {
			ivldBestPriceImpl.setReprocessedFlag(reprocessedFlag);
		}

		if (contractEndDate == null) {
			ivldBestPriceImpl.setContractEndDate(StringPool.BLANK);
		}
		else {
			ivldBestPriceImpl.setContractEndDate(contractEndDate);
		}

		ivldBestPriceImpl.setIvldBestPriceSid(ivldBestPriceSid);

		if (contractId == null) {
			ivldBestPriceImpl.setContractId(StringPool.BLANK);
		}
		else {
			ivldBestPriceImpl.setContractId(contractId);
		}

		if (beginningWacPackage == null) {
			ivldBestPriceImpl.setBeginningWacPackage(StringPool.BLANK);
		}
		else {
			ivldBestPriceImpl.setBeginningWacPackage(beginningWacPackage);
		}

		if (reasonForFailure == null) {
			ivldBestPriceImpl.setReasonForFailure(StringPool.BLANK);
		}
		else {
			ivldBestPriceImpl.setReasonForFailure(reasonForFailure);
		}

		if (contractStartDate == null) {
			ivldBestPriceImpl.setContractStartDate(StringPool.BLANK);
		}
		else {
			ivldBestPriceImpl.setContractStartDate(contractStartDate);
		}

		if (batchId == null) {
			ivldBestPriceImpl.setBatchId(StringPool.BLANK);
		}
		else {
			ivldBestPriceImpl.setBatchId(batchId);
		}

		if (errorField == null) {
			ivldBestPriceImpl.setErrorField(StringPool.BLANK);
		}
		else {
			ivldBestPriceImpl.setErrorField(errorField);
		}

		if (initialBestPrice == null) {
			ivldBestPriceImpl.setInitialBestPrice(StringPool.BLANK);
		}
		else {
			ivldBestPriceImpl.setInitialBestPrice(initialBestPrice);
		}

		if (contractNo == null) {
			ivldBestPriceImpl.setContractNo(StringPool.BLANK);
		}
		else {
			ivldBestPriceImpl.setContractNo(contractNo);
		}

		ivldBestPriceImpl.setCheckRecord(checkRecord);

		ivldBestPriceImpl.resetOriginalValues();

		return ivldBestPriceImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		itemDesc = objectInput.readUTF();
		bestPriceIntfid = objectInput.readUTF();
		accountId = objectInput.readUTF();
		sellingPrice = objectInput.readUTF();
		period = objectInput.readUTF();
		itemId = objectInput.readUTF();
		modifiedDate = objectInput.readLong();
		createdBy = objectInput.readUTF();
		createdDate = objectInput.readLong();
		source = objectInput.readUTF();
		addChgDelIndicator = objectInput.readUTF();
		initialDiscount = objectInput.readUTF();
		errorCode = objectInput.readUTF();
		modifiedBy = objectInput.readUTF();
		intfInsertedDate = objectInput.readLong();
		itemNo = objectInput.readUTF();
		reprocessedFlag = objectInput.readUTF();
		contractEndDate = objectInput.readUTF();

		ivldBestPriceSid = objectInput.readInt();
		contractId = objectInput.readUTF();
		beginningWacPackage = objectInput.readUTF();
		reasonForFailure = objectInput.readUTF();
		contractStartDate = objectInput.readUTF();
		batchId = objectInput.readUTF();
		errorField = objectInput.readUTF();
		initialBestPrice = objectInput.readUTF();
		contractNo = objectInput.readUTF();

		checkRecord = objectInput.readBoolean();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		if (itemDesc == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(itemDesc);
		}

		if (bestPriceIntfid == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(bestPriceIntfid);
		}

		if (accountId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(accountId);
		}

		if (sellingPrice == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(sellingPrice);
		}

		if (period == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(period);
		}

		if (itemId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(itemId);
		}

		objectOutput.writeLong(modifiedDate);

		if (createdBy == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(createdBy);
		}

		objectOutput.writeLong(createdDate);

		if (source == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(source);
		}

		if (addChgDelIndicator == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(addChgDelIndicator);
		}

		if (initialDiscount == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(initialDiscount);
		}

		if (errorCode == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(errorCode);
		}

		if (modifiedBy == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(modifiedBy);
		}

		objectOutput.writeLong(intfInsertedDate);

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

		if (contractEndDate == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(contractEndDate);
		}

		objectOutput.writeInt(ivldBestPriceSid);

		if (contractId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(contractId);
		}

		if (beginningWacPackage == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(beginningWacPackage);
		}

		if (reasonForFailure == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(reasonForFailure);
		}

		if (contractStartDate == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(contractStartDate);
		}

		if (batchId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(batchId);
		}

		if (errorField == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(errorField);
		}

		if (initialBestPrice == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(initialBestPrice);
		}

		if (contractNo == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(contractNo);
		}

		objectOutput.writeBoolean(checkRecord);
	}

	public String itemDesc;
	public String bestPriceIntfid;
	public String accountId;
	public String sellingPrice;
	public String period;
	public String itemId;
	public long modifiedDate;
	public String createdBy;
	public long createdDate;
	public String source;
	public String addChgDelIndicator;
	public String initialDiscount;
	public String errorCode;
	public String modifiedBy;
	public long intfInsertedDate;
	public String itemNo;
	public String reprocessedFlag;
	public String contractEndDate;
	public int ivldBestPriceSid;
	public String contractId;
	public String beginningWacPackage;
	public String reasonForFailure;
	public String contractStartDate;
	public String batchId;
	public String errorField;
	public String initialBestPrice;
	public String contractNo;
	public boolean checkRecord;
}