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

import com.stpl.app.parttwo.model.IvldcompanyIdentifier;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing IvldcompanyIdentifier in entity cache.
 *
 * @author
 * @see IvldcompanyIdentifier
 * @generated
 */
@ProviderType
public class IvldcompanyIdentifierCacheModel implements CacheModel<IvldcompanyIdentifier>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof IvldcompanyIdentifierCacheModel)) {
			return false;
		}

		IvldcompanyIdentifierCacheModel ivldcompanyIdentifierCacheModel = (IvldcompanyIdentifierCacheModel)obj;

		if (ivldcompanyIdentifierSid == ivldcompanyIdentifierCacheModel.ivldcompanyIdentifierSid) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, ivldcompanyIdentifierSid);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(51);

		sb.append("{reasonForFailure=");
		sb.append(reasonForFailure);
		sb.append(", companyId=");
		sb.append(companyId);
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
		sb.append(", ivldcompanyIdentifierSid=");
		sb.append(ivldcompanyIdentifierSid);
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
	public IvldcompanyIdentifier toEntityModel() {
		IvldcompanyIdentifierImpl ivldcompanyIdentifierImpl = new IvldcompanyIdentifierImpl();

		if (reasonForFailure == null) {
			ivldcompanyIdentifierImpl.setReasonForFailure(StringPool.BLANK);
		}
		else {
			ivldcompanyIdentifierImpl.setReasonForFailure(reasonForFailure);
		}

		if (companyId == null) {
			ivldcompanyIdentifierImpl.setCompanyId(StringPool.BLANK);
		}
		else {
			ivldcompanyIdentifierImpl.setCompanyId(companyId);
		}

		if (companyName == null) {
			ivldcompanyIdentifierImpl.setCompanyName(StringPool.BLANK);
		}
		else {
			ivldcompanyIdentifierImpl.setCompanyName(companyName);
		}

		if (endDate == null) {
			ivldcompanyIdentifierImpl.setEndDate(StringPool.BLANK);
		}
		else {
			ivldcompanyIdentifierImpl.setEndDate(endDate);
		}

		if (modifiedDate == Long.MIN_VALUE) {
			ivldcompanyIdentifierImpl.setModifiedDate(null);
		}
		else {
			ivldcompanyIdentifierImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (identifierStatus == null) {
			ivldcompanyIdentifierImpl.setIdentifierStatus(StringPool.BLANK);
		}
		else {
			ivldcompanyIdentifierImpl.setIdentifierStatus(identifierStatus);
		}

		if (companyIdentifier == null) {
			ivldcompanyIdentifierImpl.setCompanyIdentifier(StringPool.BLANK);
		}
		else {
			ivldcompanyIdentifierImpl.setCompanyIdentifier(companyIdentifier);
		}

		if (entityCode == null) {
			ivldcompanyIdentifierImpl.setEntityCode(StringPool.BLANK);
		}
		else {
			ivldcompanyIdentifierImpl.setEntityCode(entityCode);
		}

		if (companyIdentifierIntfid == null) {
			ivldcompanyIdentifierImpl.setCompanyIdentifierIntfid(StringPool.BLANK);
		}
		else {
			ivldcompanyIdentifierImpl.setCompanyIdentifierIntfid(companyIdentifierIntfid);
		}

		if (startDate == null) {
			ivldcompanyIdentifierImpl.setStartDate(StringPool.BLANK);
		}
		else {
			ivldcompanyIdentifierImpl.setStartDate(startDate);
		}

		if (source == null) {
			ivldcompanyIdentifierImpl.setSource(StringPool.BLANK);
		}
		else {
			ivldcompanyIdentifierImpl.setSource(source);
		}

		if (createdDate == Long.MIN_VALUE) {
			ivldcompanyIdentifierImpl.setCreatedDate(null);
		}
		else {
			ivldcompanyIdentifierImpl.setCreatedDate(new Date(createdDate));
		}

		if (createdBy == null) {
			ivldcompanyIdentifierImpl.setCreatedBy(StringPool.BLANK);
		}
		else {
			ivldcompanyIdentifierImpl.setCreatedBy(createdBy);
		}

		if (companyNo == null) {
			ivldcompanyIdentifierImpl.setCompanyNo(StringPool.BLANK);
		}
		else {
			ivldcompanyIdentifierImpl.setCompanyNo(companyNo);
		}

		if (addChgDelIndicator == null) {
			ivldcompanyIdentifierImpl.setAddChgDelIndicator(StringPool.BLANK);
		}
		else {
			ivldcompanyIdentifierImpl.setAddChgDelIndicator(addChgDelIndicator);
		}

		if (batchId == null) {
			ivldcompanyIdentifierImpl.setBatchId(StringPool.BLANK);
		}
		else {
			ivldcompanyIdentifierImpl.setBatchId(batchId);
		}

		if (errorField == null) {
			ivldcompanyIdentifierImpl.setErrorField(StringPool.BLANK);
		}
		else {
			ivldcompanyIdentifierImpl.setErrorField(errorField);
		}

		if (errorCode == null) {
			ivldcompanyIdentifierImpl.setErrorCode(StringPool.BLANK);
		}
		else {
			ivldcompanyIdentifierImpl.setErrorCode(errorCode);
		}

		if (identifierCodeQualifierName == null) {
			ivldcompanyIdentifierImpl.setIdentifierCodeQualifierName(StringPool.BLANK);
		}
		else {
			ivldcompanyIdentifierImpl.setIdentifierCodeQualifierName(identifierCodeQualifierName);
		}

		if (intfInsertedDate == Long.MIN_VALUE) {
			ivldcompanyIdentifierImpl.setIntfInsertedDate(null);
		}
		else {
			ivldcompanyIdentifierImpl.setIntfInsertedDate(new Date(
					intfInsertedDate));
		}

		if (modifiedBy == null) {
			ivldcompanyIdentifierImpl.setModifiedBy(StringPool.BLANK);
		}
		else {
			ivldcompanyIdentifierImpl.setModifiedBy(modifiedBy);
		}

		ivldcompanyIdentifierImpl.setIvldcompanyIdentifierSid(ivldcompanyIdentifierSid);

		if (reprocessedFlag == null) {
			ivldcompanyIdentifierImpl.setReprocessedFlag(StringPool.BLANK);
		}
		else {
			ivldcompanyIdentifierImpl.setReprocessedFlag(reprocessedFlag);
		}

		if (identifierCodeQualifier == null) {
			ivldcompanyIdentifierImpl.setIdentifierCodeQualifier(StringPool.BLANK);
		}
		else {
			ivldcompanyIdentifierImpl.setIdentifierCodeQualifier(identifierCodeQualifier);
		}

		ivldcompanyIdentifierImpl.setCheckRecord(checkRecord);

		ivldcompanyIdentifierImpl.resetOriginalValues();

		return ivldcompanyIdentifierImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		reasonForFailure = objectInput.readUTF();
		companyId = objectInput.readUTF();
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

		ivldcompanyIdentifierSid = objectInput.readInt();
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

		if (companyId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(companyId);
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

		objectOutput.writeInt(ivldcompanyIdentifierSid);

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
	public String companyId;
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
	public int ivldcompanyIdentifierSid;
	public String reprocessedFlag;
	public String identifierCodeQualifier;
	public boolean checkRecord;
}