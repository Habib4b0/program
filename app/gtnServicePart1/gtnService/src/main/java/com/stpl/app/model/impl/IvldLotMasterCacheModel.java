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

import com.stpl.app.model.IvldLotMaster;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing IvldLotMaster in entity cache.
 *
 * @author
 * @see IvldLotMaster
 * @generated
 */
@ProviderType
public class IvldLotMasterCacheModel implements CacheModel<IvldLotMaster>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof IvldLotMasterCacheModel)) {
			return false;
		}

		IvldLotMasterCacheModel ivldLotMasterCacheModel = (IvldLotMasterCacheModel)obj;

		if (ivldLotMasterSid == ivldLotMasterCacheModel.ivldLotMasterSid) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, ivldLotMasterSid);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(39);

		sb.append("{reasonForFailure=");
		sb.append(reasonForFailure);
		sb.append(", itemId=");
		sb.append(itemId);
		sb.append(", ivldLotMasterSid=");
		sb.append(ivldLotMasterSid);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", createdBy=");
		sb.append(createdBy);
		sb.append(", createdDate=");
		sb.append(createdDate);
		sb.append(", source=");
		sb.append(source);
		sb.append(", lastLotSoldDate=");
		sb.append(lastLotSoldDate);
		sb.append(", lotExpirationDate=");
		sb.append(lotExpirationDate);
		sb.append(", batchId=");
		sb.append(batchId);
		sb.append(", addChgDelIndicator=");
		sb.append(addChgDelIndicator);
		sb.append(", errorField=");
		sb.append(errorField);
		sb.append(", errorCode=");
		sb.append(errorCode);
		sb.append(", intfInsertedDate=");
		sb.append(intfInsertedDate);
		sb.append(", modifiedBy=");
		sb.append(modifiedBy);
		sb.append(", lotNo=");
		sb.append(lotNo);
		sb.append(", reprocessedFlag=");
		sb.append(reprocessedFlag);
		sb.append(", lotMasterIntfid=");
		sb.append(lotMasterIntfid);
		sb.append(", checkRecord=");
		sb.append(checkRecord);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public IvldLotMaster toEntityModel() {
		IvldLotMasterImpl ivldLotMasterImpl = new IvldLotMasterImpl();

		if (reasonForFailure == null) {
			ivldLotMasterImpl.setReasonForFailure(StringPool.BLANK);
		}
		else {
			ivldLotMasterImpl.setReasonForFailure(reasonForFailure);
		}

		if (itemId == null) {
			ivldLotMasterImpl.setItemId(StringPool.BLANK);
		}
		else {
			ivldLotMasterImpl.setItemId(itemId);
		}

		ivldLotMasterImpl.setIvldLotMasterSid(ivldLotMasterSid);

		if (modifiedDate == Long.MIN_VALUE) {
			ivldLotMasterImpl.setModifiedDate(null);
		}
		else {
			ivldLotMasterImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (createdBy == null) {
			ivldLotMasterImpl.setCreatedBy(StringPool.BLANK);
		}
		else {
			ivldLotMasterImpl.setCreatedBy(createdBy);
		}

		if (createdDate == Long.MIN_VALUE) {
			ivldLotMasterImpl.setCreatedDate(null);
		}
		else {
			ivldLotMasterImpl.setCreatedDate(new Date(createdDate));
		}

		if (source == null) {
			ivldLotMasterImpl.setSource(StringPool.BLANK);
		}
		else {
			ivldLotMasterImpl.setSource(source);
		}

		if (lastLotSoldDate == null) {
			ivldLotMasterImpl.setLastLotSoldDate(StringPool.BLANK);
		}
		else {
			ivldLotMasterImpl.setLastLotSoldDate(lastLotSoldDate);
		}

		if (lotExpirationDate == null) {
			ivldLotMasterImpl.setLotExpirationDate(StringPool.BLANK);
		}
		else {
			ivldLotMasterImpl.setLotExpirationDate(lotExpirationDate);
		}

		if (batchId == null) {
			ivldLotMasterImpl.setBatchId(StringPool.BLANK);
		}
		else {
			ivldLotMasterImpl.setBatchId(batchId);
		}

		if (addChgDelIndicator == null) {
			ivldLotMasterImpl.setAddChgDelIndicator(StringPool.BLANK);
		}
		else {
			ivldLotMasterImpl.setAddChgDelIndicator(addChgDelIndicator);
		}

		if (errorField == null) {
			ivldLotMasterImpl.setErrorField(StringPool.BLANK);
		}
		else {
			ivldLotMasterImpl.setErrorField(errorField);
		}

		if (errorCode == null) {
			ivldLotMasterImpl.setErrorCode(StringPool.BLANK);
		}
		else {
			ivldLotMasterImpl.setErrorCode(errorCode);
		}

		if (intfInsertedDate == Long.MIN_VALUE) {
			ivldLotMasterImpl.setIntfInsertedDate(null);
		}
		else {
			ivldLotMasterImpl.setIntfInsertedDate(new Date(intfInsertedDate));
		}

		if (modifiedBy == null) {
			ivldLotMasterImpl.setModifiedBy(StringPool.BLANK);
		}
		else {
			ivldLotMasterImpl.setModifiedBy(modifiedBy);
		}

		if (lotNo == null) {
			ivldLotMasterImpl.setLotNo(StringPool.BLANK);
		}
		else {
			ivldLotMasterImpl.setLotNo(lotNo);
		}

		if (reprocessedFlag == null) {
			ivldLotMasterImpl.setReprocessedFlag(StringPool.BLANK);
		}
		else {
			ivldLotMasterImpl.setReprocessedFlag(reprocessedFlag);
		}

		if (lotMasterIntfid == null) {
			ivldLotMasterImpl.setLotMasterIntfid(StringPool.BLANK);
		}
		else {
			ivldLotMasterImpl.setLotMasterIntfid(lotMasterIntfid);
		}

		ivldLotMasterImpl.setCheckRecord(checkRecord);

		ivldLotMasterImpl.resetOriginalValues();

		return ivldLotMasterImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		reasonForFailure = objectInput.readUTF();
		itemId = objectInput.readUTF();

		ivldLotMasterSid = objectInput.readInt();
		modifiedDate = objectInput.readLong();
		createdBy = objectInput.readUTF();
		createdDate = objectInput.readLong();
		source = objectInput.readUTF();
		lastLotSoldDate = objectInput.readUTF();
		lotExpirationDate = objectInput.readUTF();
		batchId = objectInput.readUTF();
		addChgDelIndicator = objectInput.readUTF();
		errorField = objectInput.readUTF();
		errorCode = objectInput.readUTF();
		intfInsertedDate = objectInput.readLong();
		modifiedBy = objectInput.readUTF();
		lotNo = objectInput.readUTF();
		reprocessedFlag = objectInput.readUTF();
		lotMasterIntfid = objectInput.readUTF();

		checkRecord = objectInput.readBoolean();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		if (reasonForFailure == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(reasonForFailure);
		}

		if (itemId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(itemId);
		}

		objectOutput.writeInt(ivldLotMasterSid);
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

		if (lastLotSoldDate == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(lastLotSoldDate);
		}

		if (lotExpirationDate == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(lotExpirationDate);
		}

		if (batchId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(batchId);
		}

		if (addChgDelIndicator == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(addChgDelIndicator);
		}

		if (errorField == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(errorField);
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

		if (lotNo == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(lotNo);
		}

		if (reprocessedFlag == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(reprocessedFlag);
		}

		if (lotMasterIntfid == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(lotMasterIntfid);
		}

		objectOutput.writeBoolean(checkRecord);
	}

	public String reasonForFailure;
	public String itemId;
	public int ivldLotMasterSid;
	public long modifiedDate;
	public String createdBy;
	public long createdDate;
	public String source;
	public String lastLotSoldDate;
	public String lotExpirationDate;
	public String batchId;
	public String addChgDelIndicator;
	public String errorField;
	public String errorCode;
	public long intfInsertedDate;
	public String modifiedBy;
	public String lotNo;
	public String reprocessedFlag;
	public String lotMasterIntfid;
	public boolean checkRecord;
}