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

import com.stpl.app.model.PriceScheduleMaster;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing PriceScheduleMaster in entity cache.
 *
 * @author
 * @see PriceScheduleMaster
 * @generated
 */
@ProviderType
public class PriceScheduleMasterCacheModel implements CacheModel<PriceScheduleMaster>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof PriceScheduleMasterCacheModel)) {
			return false;
		}

		PriceScheduleMasterCacheModel priceScheduleMasterCacheModel = (PriceScheduleMasterCacheModel)obj;

		if (priceScheduleSystemId == priceScheduleMasterCacheModel.priceScheduleSystemId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, priceScheduleSystemId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(41);

		sb.append("{parentPriceScheduleName=");
		sb.append(parentPriceScheduleName);
		sb.append(", parentPriceScheduleId=");
		sb.append(parentPriceScheduleId);
		sb.append(", priceScheduleStartDate=");
		sb.append(priceScheduleStartDate);
		sb.append(", priceScheduleNo=");
		sb.append(priceScheduleNo);
		sb.append(", priceScheduleName=");
		sb.append(priceScheduleName);
		sb.append(", priceScheduleId=");
		sb.append(priceScheduleId);
		sb.append(", priceScheduleType=");
		sb.append(priceScheduleType);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", priceScheduleSystemId=");
		sb.append(priceScheduleSystemId);
		sb.append(", recordLockStatus=");
		sb.append(recordLockStatus);
		sb.append(", createdDate=");
		sb.append(createdDate);
		sb.append(", createdBy=");
		sb.append(createdBy);
		sb.append(", priceScheduleDesignation=");
		sb.append(priceScheduleDesignation);
		sb.append(", priceScheduleEndDate=");
		sb.append(priceScheduleEndDate);
		sb.append(", priceScheduleStatus=");
		sb.append(priceScheduleStatus);
		sb.append(", batchId=");
		sb.append(batchId);
		sb.append(", priceScheduleCategory=");
		sb.append(priceScheduleCategory);
		sb.append(", tradeClass=");
		sb.append(tradeClass);
		sb.append(", inboundStatus=");
		sb.append(inboundStatus);
		sb.append(", modifiedBy=");
		sb.append(modifiedBy);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public PriceScheduleMaster toEntityModel() {
		PriceScheduleMasterImpl priceScheduleMasterImpl = new PriceScheduleMasterImpl();

		if (parentPriceScheduleName == null) {
			priceScheduleMasterImpl.setParentPriceScheduleName(StringPool.BLANK);
		}
		else {
			priceScheduleMasterImpl.setParentPriceScheduleName(parentPriceScheduleName);
		}

		if (parentPriceScheduleId == null) {
			priceScheduleMasterImpl.setParentPriceScheduleId(StringPool.BLANK);
		}
		else {
			priceScheduleMasterImpl.setParentPriceScheduleId(parentPriceScheduleId);
		}

		if (priceScheduleStartDate == Long.MIN_VALUE) {
			priceScheduleMasterImpl.setPriceScheduleStartDate(null);
		}
		else {
			priceScheduleMasterImpl.setPriceScheduleStartDate(new Date(
					priceScheduleStartDate));
		}

		if (priceScheduleNo == null) {
			priceScheduleMasterImpl.setPriceScheduleNo(StringPool.BLANK);
		}
		else {
			priceScheduleMasterImpl.setPriceScheduleNo(priceScheduleNo);
		}

		if (priceScheduleName == null) {
			priceScheduleMasterImpl.setPriceScheduleName(StringPool.BLANK);
		}
		else {
			priceScheduleMasterImpl.setPriceScheduleName(priceScheduleName);
		}

		if (priceScheduleId == null) {
			priceScheduleMasterImpl.setPriceScheduleId(StringPool.BLANK);
		}
		else {
			priceScheduleMasterImpl.setPriceScheduleId(priceScheduleId);
		}

		if (priceScheduleType == null) {
			priceScheduleMasterImpl.setPriceScheduleType(StringPool.BLANK);
		}
		else {
			priceScheduleMasterImpl.setPriceScheduleType(priceScheduleType);
		}

		if (modifiedDate == Long.MIN_VALUE) {
			priceScheduleMasterImpl.setModifiedDate(null);
		}
		else {
			priceScheduleMasterImpl.setModifiedDate(new Date(modifiedDate));
		}

		priceScheduleMasterImpl.setPriceScheduleSystemId(priceScheduleSystemId);

		if (recordLockStatus == null) {
			priceScheduleMasterImpl.setRecordLockStatus(StringPool.BLANK);
		}
		else {
			priceScheduleMasterImpl.setRecordLockStatus(recordLockStatus);
		}

		if (createdDate == Long.MIN_VALUE) {
			priceScheduleMasterImpl.setCreatedDate(null);
		}
		else {
			priceScheduleMasterImpl.setCreatedDate(new Date(createdDate));
		}

		if (createdBy == null) {
			priceScheduleMasterImpl.setCreatedBy(StringPool.BLANK);
		}
		else {
			priceScheduleMasterImpl.setCreatedBy(createdBy);
		}

		if (priceScheduleDesignation == null) {
			priceScheduleMasterImpl.setPriceScheduleDesignation(StringPool.BLANK);
		}
		else {
			priceScheduleMasterImpl.setPriceScheduleDesignation(priceScheduleDesignation);
		}

		if (priceScheduleEndDate == Long.MIN_VALUE) {
			priceScheduleMasterImpl.setPriceScheduleEndDate(null);
		}
		else {
			priceScheduleMasterImpl.setPriceScheduleEndDate(new Date(
					priceScheduleEndDate));
		}

		if (priceScheduleStatus == null) {
			priceScheduleMasterImpl.setPriceScheduleStatus(StringPool.BLANK);
		}
		else {
			priceScheduleMasterImpl.setPriceScheduleStatus(priceScheduleStatus);
		}

		if (batchId == null) {
			priceScheduleMasterImpl.setBatchId(StringPool.BLANK);
		}
		else {
			priceScheduleMasterImpl.setBatchId(batchId);
		}

		if (priceScheduleCategory == null) {
			priceScheduleMasterImpl.setPriceScheduleCategory(StringPool.BLANK);
		}
		else {
			priceScheduleMasterImpl.setPriceScheduleCategory(priceScheduleCategory);
		}

		if (tradeClass == null) {
			priceScheduleMasterImpl.setTradeClass(StringPool.BLANK);
		}
		else {
			priceScheduleMasterImpl.setTradeClass(tradeClass);
		}

		if (inboundStatus == null) {
			priceScheduleMasterImpl.setInboundStatus(StringPool.BLANK);
		}
		else {
			priceScheduleMasterImpl.setInboundStatus(inboundStatus);
		}

		if (modifiedBy == null) {
			priceScheduleMasterImpl.setModifiedBy(StringPool.BLANK);
		}
		else {
			priceScheduleMasterImpl.setModifiedBy(modifiedBy);
		}

		priceScheduleMasterImpl.resetOriginalValues();

		return priceScheduleMasterImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		parentPriceScheduleName = objectInput.readUTF();
		parentPriceScheduleId = objectInput.readUTF();
		priceScheduleStartDate = objectInput.readLong();
		priceScheduleNo = objectInput.readUTF();
		priceScheduleName = objectInput.readUTF();
		priceScheduleId = objectInput.readUTF();
		priceScheduleType = objectInput.readUTF();
		modifiedDate = objectInput.readLong();

		priceScheduleSystemId = objectInput.readInt();
		recordLockStatus = objectInput.readUTF();
		createdDate = objectInput.readLong();
		createdBy = objectInput.readUTF();
		priceScheduleDesignation = objectInput.readUTF();
		priceScheduleEndDate = objectInput.readLong();
		priceScheduleStatus = objectInput.readUTF();
		batchId = objectInput.readUTF();
		priceScheduleCategory = objectInput.readUTF();
		tradeClass = objectInput.readUTF();
		inboundStatus = objectInput.readUTF();
		modifiedBy = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		if (parentPriceScheduleName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(parentPriceScheduleName);
		}

		if (parentPriceScheduleId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(parentPriceScheduleId);
		}

		objectOutput.writeLong(priceScheduleStartDate);

		if (priceScheduleNo == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(priceScheduleNo);
		}

		if (priceScheduleName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(priceScheduleName);
		}

		if (priceScheduleId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(priceScheduleId);
		}

		if (priceScheduleType == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(priceScheduleType);
		}

		objectOutput.writeLong(modifiedDate);

		objectOutput.writeInt(priceScheduleSystemId);

		if (recordLockStatus == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(recordLockStatus);
		}

		objectOutput.writeLong(createdDate);

		if (createdBy == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(createdBy);
		}

		if (priceScheduleDesignation == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(priceScheduleDesignation);
		}

		objectOutput.writeLong(priceScheduleEndDate);

		if (priceScheduleStatus == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(priceScheduleStatus);
		}

		if (batchId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(batchId);
		}

		if (priceScheduleCategory == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(priceScheduleCategory);
		}

		if (tradeClass == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(tradeClass);
		}

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
	}

	public String parentPriceScheduleName;
	public String parentPriceScheduleId;
	public long priceScheduleStartDate;
	public String priceScheduleNo;
	public String priceScheduleName;
	public String priceScheduleId;
	public String priceScheduleType;
	public long modifiedDate;
	public int priceScheduleSystemId;
	public String recordLockStatus;
	public long createdDate;
	public String createdBy;
	public String priceScheduleDesignation;
	public long priceScheduleEndDate;
	public String priceScheduleStatus;
	public String batchId;
	public String priceScheduleCategory;
	public String tradeClass;
	public String inboundStatus;
	public String modifiedBy;
}