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

import com.stpl.app.parttwo.model.IvldCompanyIdentifier;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing IvldCompanyIdentifier in entity cache.
 *
 * @author
 * @see IvldCompanyIdentifier
 * @generated
 */
@ProviderType
public class IvldCompanyIdentifierCacheModel implements CacheModel<IvldCompanyIdentifier>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof IvldCompanyIdentifierCacheModel)) {
			return false;
		}

		IvldCompanyIdentifierCacheModel ivldCompanyIdentifierCacheModel = (IvldCompanyIdentifierCacheModel)obj;

		if (ivldCompanyIdentifierSid == ivldCompanyIdentifierCacheModel.ivldCompanyIdentifierSid) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, ivldCompanyIdentifierSid);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(51);

		sb.append("{reasonForFailure=");
		sb.append(reasonForFailure);
		sb.append(", companyIdString=");
		sb.append(companyIdString);
		sb.append(", companyName=");
		sb.append(companyName);
		sb.append(", endDate=");
		sb.append(endDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", identifierStatus=");
		sb.append(identifierStatus);
		sb.append(", companyIdentifier=");
		sb.append(companyIdentifier);
		sb.append(", entityCode=");
		sb.append(entityCode);
		sb.append(", companyIdentifierIntfid=");
		sb.append(companyIdentifierIntfid);
		sb.append(", startDate=");
		sb.append(startDate);
		sb.append(", source=");
		sb.append(source);
		sb.append(", createdDate=");
		sb.append(createdDate);
		sb.append(", createdBy=");
		sb.append(createdBy);
		sb.append(", companyNo=");
		sb.append(companyNo);
		sb.append(", addChgDelIndicator=");
		sb.append(addChgDelIndicator);
		sb.append(", batchId=");
		sb.append(batchId);
		sb.append(", errorField=");
		sb.append(errorField);
		sb.append(", errorCode=");
		sb.append(errorCode);
		sb.append(", identifierCodeQualifierName=");
		sb.append(identifierCodeQualifierName);
		sb.append(", intfInsertedDate=");
		sb.append(intfInsertedDate);
		sb.append(", modifiedBy=");
		sb.append(modifiedBy);
		sb.append(", ivldCompanyIdentifierSid=");
		sb.append(ivldCompanyIdentifierSid);
		sb.append(", reprocessedFlag=");
		sb.append(reprocessedFlag);
		sb.append(", identifierCodeQualifier=");
		sb.append(identifierCodeQualifier);
		sb.append(", checkRecord=");
		sb.append(checkRecord);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public IvldCompanyIdentifier toEntityModel() {
		IvldCompanyIdentifierImpl ivldCompanyIdentifierImpl = new IvldCompanyIdentifierImpl();

		if (reasonForFailure == null) {
			ivldCompanyIdentifierImpl.setReasonForFailure(StringPool.BLANK);
		}
		else {
			ivldCompanyIdentifierImpl.setReasonForFailure(reasonForFailure);
		}

		if (companyIdString == null) {
			ivldCompanyIdentifierImpl.setCompanyIdString(StringPool.BLANK);
		}
		else {
			ivldCompanyIdentifierImpl.setCompanyIdString(companyIdString);
		}

		if (companyName == null) {
			ivldCompanyIdentifierImpl.setCompanyName(StringPool.BLANK);
		}
		else {
			ivldCompanyIdentifierImpl.setCompanyName(companyName);
		}

		if (endDate == null) {
			ivldCompanyIdentifierImpl.setEndDate(StringPool.BLANK);
		}
		else {
			ivldCompanyIdentifierImpl.setEndDate(endDate);
		}

		if (modifiedDate == Long.MIN_VALUE) {
			ivldCompanyIdentifierImpl.setModifiedDate(null);
		}
		else {
			ivldCompanyIdentifierImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (identifierStatus == null) {
			ivldCompanyIdentifierImpl.setIdentifierStatus(StringPool.BLANK);
		}
		else {
			ivldCompanyIdentifierImpl.setIdentifierStatus(identifierStatus);
		}

		if (companyIdentifier == null) {
			ivldCompanyIdentifierImpl.setCompanyIdentifier(StringPool.BLANK);
		}
		else {
			ivldCompanyIdentifierImpl.setCompanyIdentifier(companyIdentifier);
		}

		if (entityCode == null) {
			ivldCompanyIdentifierImpl.setEntityCode(StringPool.BLANK);
		}
		else {
			ivldCompanyIdentifierImpl.setEntityCode(entityCode);
		}

		if (companyIdentifierIntfid == null) {
			ivldCompanyIdentifierImpl.setCompanyIdentifierIntfid(StringPool.BLANK);
		}
		else {
			ivldCompanyIdentifierImpl.setCompanyIdentifierIntfid(companyIdentifierIntfid);
		}

		if (startDate == null) {
			ivldCompanyIdentifierImpl.setStartDate(StringPool.BLANK);
		}
		else {
			ivldCompanyIdentifierImpl.setStartDate(startDate);
		}

		if (source == null) {
			ivldCompanyIdentifierImpl.setSource(StringPool.BLANK);
		}
		else {
			ivldCompanyIdentifierImpl.setSource(source);
		}

		if (createdDate == Long.MIN_VALUE) {
			ivldCompanyIdentifierImpl.setCreatedDate(null);
		}
		else {
			ivldCompanyIdentifierImpl.setCreatedDate(new Date(createdDate));
		}

		if (createdBy == null) {
			ivldCompanyIdentifierImpl.setCreatedBy(StringPool.BLANK);
		}
		else {
			ivldCompanyIdentifierImpl.setCreatedBy(createdBy);
		}

		if (companyNo == null) {
			ivldCompanyIdentifierImpl.setCompanyNo(StringPool.BLANK);
		}
		else {
			ivldCompanyIdentifierImpl.setCompanyNo(companyNo);
		}

		if (addChgDelIndicator == null) {
			ivldCompanyIdentifierImpl.setAddChgDelIndicator(StringPool.BLANK);
		}
		else {
			ivldCompanyIdentifierImpl.setAddChgDelIndicator(addChgDelIndicator);
		}

		if (batchId == null) {
			ivldCompanyIdentifierImpl.setBatchId(StringPool.BLANK);
		}
		else {
			ivldCompanyIdentifierImpl.setBatchId(batchId);
		}

		if (errorField == null) {
			ivldCompanyIdentifierImpl.setErrorField(StringPool.BLANK);
		}
		else {
			ivldCompanyIdentifierImpl.setErrorField(errorField);
		}

		if (errorCode == null) {
			ivldCompanyIdentifierImpl.setErrorCode(StringPool.BLANK);
		}
		else {
			ivldCompanyIdentifierImpl.setErrorCode(errorCode);
		}

		if (identifierCodeQualifierName == null) {
			ivldCompanyIdentifierImpl.setIdentifierCodeQualifierName(StringPool.BLANK);
		}
		else {
			ivldCompanyIdentifierImpl.setIdentifierCodeQualifierName(identifierCodeQualifierName);
		}

		if (intfInsertedDate == Long.MIN_VALUE) {
			ivldCompanyIdentifierImpl.setIntfInsertedDate(null);
		}
		else {
			ivldCompanyIdentifierImpl.setIntfInsertedDate(new Date(
					intfInsertedDate));
		}

		if (modifiedBy == null) {
			ivldCompanyIdentifierImpl.setModifiedBy(StringPool.BLANK);
		}
		else {
			ivldCompanyIdentifierImpl.setModifiedBy(modifiedBy);
		}

		ivldCompanyIdentifierImpl.setIvldCompanyIdentifierSid(ivldCompanyIdentifierSid);

		if (reprocessedFlag == null) {
			ivldCompanyIdentifierImpl.setReprocessedFlag(StringPool.BLANK);
		}
		else {
			ivldCompanyIdentifierImpl.setReprocessedFlag(reprocessedFlag);
		}

		if (identifierCodeQualifier == null) {
			ivldCompanyIdentifierImpl.setIdentifierCodeQualifier(StringPool.BLANK);
		}
		else {
			ivldCompanyIdentifierImpl.setIdentifierCodeQualifier(identifierCodeQualifier);
		}

		ivldCompanyIdentifierImpl.setCheckRecord(checkRecord);

		ivldCompanyIdentifierImpl.resetOriginalValues();

		return ivldCompanyIdentifierImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		reasonForFailure = objectInput.readUTF();
		companyIdString = objectInput.readUTF();
		companyName = objectInput.readUTF();
		endDate = objectInput.readUTF();
		modifiedDate = objectInput.readLong();
		identifierStatus = objectInput.readUTF();
		companyIdentifier = objectInput.readUTF();
		entityCode = objectInput.readUTF();
		companyIdentifierIntfid = objectInput.readUTF();
		startDate = objectInput.readUTF();
		source = objectInput.readUTF();
		createdDate = objectInput.readLong();
		createdBy = objectInput.readUTF();
		companyNo = objectInput.readUTF();
		addChgDelIndicator = objectInput.readUTF();
		batchId = objectInput.readUTF();
		errorField = objectInput.readUTF();
		errorCode = objectInput.readUTF();
		identifierCodeQualifierName = objectInput.readUTF();
		intfInsertedDate = objectInput.readLong();
		modifiedBy = objectInput.readUTF();

		ivldCompanyIdentifierSid = objectInput.readInt();
		reprocessedFlag = objectInput.readUTF();
		identifierCodeQualifier = objectInput.readUTF();

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

		if (companyIdString == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(companyIdString);
		}

		if (companyName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(companyName);
		}

		if (endDate == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(endDate);
		}

		objectOutput.writeLong(modifiedDate);

		if (identifierStatus == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(identifierStatus);
		}

		if (companyIdentifier == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(companyIdentifier);
		}

		if (entityCode == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(entityCode);
		}

		if (companyIdentifierIntfid == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(companyIdentifierIntfid);
		}

		if (startDate == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(startDate);
		}

		if (source == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(source);
		}

		objectOutput.writeLong(createdDate);

		if (createdBy == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(createdBy);
		}

		if (companyNo == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(companyNo);
		}

		if (addChgDelIndicator == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(addChgDelIndicator);
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

		if (errorCode == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(errorCode);
		}

		if (identifierCodeQualifierName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(identifierCodeQualifierName);
		}

		objectOutput.writeLong(intfInsertedDate);

		if (modifiedBy == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(modifiedBy);
		}

		objectOutput.writeInt(ivldCompanyIdentifierSid);

		if (reprocessedFlag == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(reprocessedFlag);
		}

		if (identifierCodeQualifier == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(identifierCodeQualifier);
		}

		objectOutput.writeBoolean(checkRecord);
	}

	public String reasonForFailure;
	public String companyIdString;
	public String companyName;
	public String endDate;
	public long modifiedDate;
	public String identifierStatus;
	public String companyIdentifier;
	public String entityCode;
	public String companyIdentifierIntfid;
	public String startDate;
	public String source;
	public long createdDate;
	public String createdBy;
	public String companyNo;
	public String addChgDelIndicator;
	public String batchId;
	public String errorField;
	public String errorCode;
	public String identifierCodeQualifierName;
	public long intfInsertedDate;
	public String modifiedBy;
	public int ivldCompanyIdentifierSid;
	public String reprocessedFlag;
	public String identifierCodeQualifier;
	public boolean checkRecord;
}