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

import com.stpl.app.parttwo.model.IvldItemIdentifier;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing IvldItemIdentifier in entity cache.
 *
 * @author
 * @see IvldItemIdentifier
 * @generated
 */
@ProviderType
public class IvldItemIdentifierCacheModel implements CacheModel<IvldItemIdentifier>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof IvldItemIdentifierCacheModel)) {
			return false;
		}

		IvldItemIdentifierCacheModel ivldItemIdentifierCacheModel = (IvldItemIdentifierCacheModel)obj;

		if (ivldItemIdentifierSid == ivldItemIdentifierCacheModel.ivldItemIdentifierSid) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, ivldItemIdentifierSid);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(51);

		sb.append("{createdBy=");
		sb.append(createdBy);
		sb.append(", identifierCodeQualifierName=");
		sb.append(identifierCodeQualifierName);
		sb.append(", ivldItemIdentifierSid=");
		sb.append(ivldItemIdentifierSid);
		sb.append(", itemNo=");
		sb.append(itemNo);
		sb.append(", modifiedBy=");
		sb.append(modifiedBy);
		sb.append(", createdDate=");
		sb.append(createdDate);
		sb.append(", identifierCodeQualifier=");
		sb.append(identifierCodeQualifier);
		sb.append(", itemId=");
		sb.append(itemId);
		sb.append(", endDate=");
		sb.append(endDate);
		sb.append(", errorField=");
		sb.append(errorField);
		sb.append(", startDate=");
		sb.append(startDate);
		sb.append(", batchId=");
		sb.append(batchId);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", itemName=");
		sb.append(itemName);
		sb.append(", errorCode=");
		sb.append(errorCode);
		sb.append(", reprocessedFlag=");
		sb.append(reprocessedFlag);
		sb.append(", itemIdentifier=");
		sb.append(itemIdentifier);
		sb.append(", itemStatus=");
		sb.append(itemStatus);
		sb.append(", reasonForFailure=");
		sb.append(reasonForFailure);
		sb.append(", source=");
		sb.append(source);
		sb.append(", addChgDelIndicator=");
		sb.append(addChgDelIndicator);
		sb.append(", entityCode=");
		sb.append(entityCode);
		sb.append(", itemIdentifierIntfid=");
		sb.append(itemIdentifierIntfid);
		sb.append(", intfInsertedDate=");
		sb.append(intfInsertedDate);
		sb.append(", checkRecord=");
		sb.append(checkRecord);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public IvldItemIdentifier toEntityModel() {
		IvldItemIdentifierImpl ivldItemIdentifierImpl = new IvldItemIdentifierImpl();

		if (createdBy == null) {
			ivldItemIdentifierImpl.setCreatedBy(StringPool.BLANK);
		}
		else {
			ivldItemIdentifierImpl.setCreatedBy(createdBy);
		}

		if (identifierCodeQualifierName == null) {
			ivldItemIdentifierImpl.setIdentifierCodeQualifierName(StringPool.BLANK);
		}
		else {
			ivldItemIdentifierImpl.setIdentifierCodeQualifierName(identifierCodeQualifierName);
		}

		ivldItemIdentifierImpl.setIvldItemIdentifierSid(ivldItemIdentifierSid);

		if (itemNo == null) {
			ivldItemIdentifierImpl.setItemNo(StringPool.BLANK);
		}
		else {
			ivldItemIdentifierImpl.setItemNo(itemNo);
		}

		if (modifiedBy == null) {
			ivldItemIdentifierImpl.setModifiedBy(StringPool.BLANK);
		}
		else {
			ivldItemIdentifierImpl.setModifiedBy(modifiedBy);
		}

		if (createdDate == Long.MIN_VALUE) {
			ivldItemIdentifierImpl.setCreatedDate(null);
		}
		else {
			ivldItemIdentifierImpl.setCreatedDate(new Date(createdDate));
		}

		if (identifierCodeQualifier == null) {
			ivldItemIdentifierImpl.setIdentifierCodeQualifier(StringPool.BLANK);
		}
		else {
			ivldItemIdentifierImpl.setIdentifierCodeQualifier(identifierCodeQualifier);
		}

		if (itemId == null) {
			ivldItemIdentifierImpl.setItemId(StringPool.BLANK);
		}
		else {
			ivldItemIdentifierImpl.setItemId(itemId);
		}

		if (endDate == null) {
			ivldItemIdentifierImpl.setEndDate(StringPool.BLANK);
		}
		else {
			ivldItemIdentifierImpl.setEndDate(endDate);
		}

		if (errorField == null) {
			ivldItemIdentifierImpl.setErrorField(StringPool.BLANK);
		}
		else {
			ivldItemIdentifierImpl.setErrorField(errorField);
		}

		if (startDate == null) {
			ivldItemIdentifierImpl.setStartDate(StringPool.BLANK);
		}
		else {
			ivldItemIdentifierImpl.setStartDate(startDate);
		}

		if (batchId == null) {
			ivldItemIdentifierImpl.setBatchId(StringPool.BLANK);
		}
		else {
			ivldItemIdentifierImpl.setBatchId(batchId);
		}

		if (modifiedDate == Long.MIN_VALUE) {
			ivldItemIdentifierImpl.setModifiedDate(null);
		}
		else {
			ivldItemIdentifierImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (itemName == null) {
			ivldItemIdentifierImpl.setItemName(StringPool.BLANK);
		}
		else {
			ivldItemIdentifierImpl.setItemName(itemName);
		}

		if (errorCode == null) {
			ivldItemIdentifierImpl.setErrorCode(StringPool.BLANK);
		}
		else {
			ivldItemIdentifierImpl.setErrorCode(errorCode);
		}

		if (reprocessedFlag == null) {
			ivldItemIdentifierImpl.setReprocessedFlag(StringPool.BLANK);
		}
		else {
			ivldItemIdentifierImpl.setReprocessedFlag(reprocessedFlag);
		}

		if (itemIdentifier == null) {
			ivldItemIdentifierImpl.setItemIdentifier(StringPool.BLANK);
		}
		else {
			ivldItemIdentifierImpl.setItemIdentifier(itemIdentifier);
		}

		if (itemStatus == null) {
			ivldItemIdentifierImpl.setItemStatus(StringPool.BLANK);
		}
		else {
			ivldItemIdentifierImpl.setItemStatus(itemStatus);
		}

		if (reasonForFailure == null) {
			ivldItemIdentifierImpl.setReasonForFailure(StringPool.BLANK);
		}
		else {
			ivldItemIdentifierImpl.setReasonForFailure(reasonForFailure);
		}

		if (source == null) {
			ivldItemIdentifierImpl.setSource(StringPool.BLANK);
		}
		else {
			ivldItemIdentifierImpl.setSource(source);
		}

		if (addChgDelIndicator == null) {
			ivldItemIdentifierImpl.setAddChgDelIndicator(StringPool.BLANK);
		}
		else {
			ivldItemIdentifierImpl.setAddChgDelIndicator(addChgDelIndicator);
		}

		if (entityCode == null) {
			ivldItemIdentifierImpl.setEntityCode(StringPool.BLANK);
		}
		else {
			ivldItemIdentifierImpl.setEntityCode(entityCode);
		}

		if (itemIdentifierIntfid == null) {
			ivldItemIdentifierImpl.setItemIdentifierIntfid(StringPool.BLANK);
		}
		else {
			ivldItemIdentifierImpl.setItemIdentifierIntfid(itemIdentifierIntfid);
		}

		if (intfInsertedDate == Long.MIN_VALUE) {
			ivldItemIdentifierImpl.setIntfInsertedDate(null);
		}
		else {
			ivldItemIdentifierImpl.setIntfInsertedDate(new Date(
					intfInsertedDate));
		}

		ivldItemIdentifierImpl.setCheckRecord(checkRecord);

		ivldItemIdentifierImpl.resetOriginalValues();

		return ivldItemIdentifierImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		createdBy = objectInput.readUTF();
		identifierCodeQualifierName = objectInput.readUTF();

		ivldItemIdentifierSid = objectInput.readInt();
		itemNo = objectInput.readUTF();
		modifiedBy = objectInput.readUTF();
		createdDate = objectInput.readLong();
		identifierCodeQualifier = objectInput.readUTF();
		itemId = objectInput.readUTF();
		endDate = objectInput.readUTF();
		errorField = objectInput.readUTF();
		startDate = objectInput.readUTF();
		batchId = objectInput.readUTF();
		modifiedDate = objectInput.readLong();
		itemName = objectInput.readUTF();
		errorCode = objectInput.readUTF();
		reprocessedFlag = objectInput.readUTF();
		itemIdentifier = objectInput.readUTF();
		itemStatus = objectInput.readUTF();
		reasonForFailure = objectInput.readUTF();
		source = objectInput.readUTF();
		addChgDelIndicator = objectInput.readUTF();
		entityCode = objectInput.readUTF();
		itemIdentifierIntfid = objectInput.readUTF();
		intfInsertedDate = objectInput.readLong();

		checkRecord = objectInput.readBoolean();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		if (createdBy == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(createdBy);
		}

		if (identifierCodeQualifierName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(identifierCodeQualifierName);
		}

		objectOutput.writeInt(ivldItemIdentifierSid);

		if (itemNo == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(itemNo);
		}

		if (modifiedBy == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(modifiedBy);
		}

		objectOutput.writeLong(createdDate);

		if (identifierCodeQualifier == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(identifierCodeQualifier);
		}

		if (itemId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(itemId);
		}

		if (endDate == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(endDate);
		}

		if (errorField == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(errorField);
		}

		if (startDate == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(startDate);
		}

		if (batchId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(batchId);
		}

		objectOutput.writeLong(modifiedDate);

		if (itemName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(itemName);
		}

		if (errorCode == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(errorCode);
		}

		if (reprocessedFlag == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(reprocessedFlag);
		}

		if (itemIdentifier == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(itemIdentifier);
		}

		if (itemStatus == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(itemStatus);
		}

		if (reasonForFailure == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(reasonForFailure);
		}

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

		if (entityCode == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(entityCode);
		}

		if (itemIdentifierIntfid == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(itemIdentifierIntfid);
		}

		objectOutput.writeLong(intfInsertedDate);

		objectOutput.writeBoolean(checkRecord);
	}

	public String createdBy;
	public String identifierCodeQualifierName;
	public int ivldItemIdentifierSid;
	public String itemNo;
	public String modifiedBy;
	public long createdDate;
	public String identifierCodeQualifier;
	public String itemId;
	public String endDate;
	public String errorField;
	public String startDate;
	public String batchId;
	public long modifiedDate;
	public String itemName;
	public String errorCode;
	public String reprocessedFlag;
	public String itemIdentifier;
	public String itemStatus;
	public String reasonForFailure;
	public String source;
	public String addChgDelIndicator;
	public String entityCode;
	public String itemIdentifierIntfid;
	public long intfInsertedDate;
	public boolean checkRecord;
}