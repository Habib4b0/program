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

import com.stpl.app.parttwo.model.IvldCompanyParent;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing IvldCompanyParent in entity cache.
 *
 * @author
 * @see IvldCompanyParent
 * @generated
 */
@ProviderType
public class IvldCompanyParentCacheModel implements CacheModel<IvldCompanyParent>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof IvldCompanyParentCacheModel)) {
			return false;
		}

		IvldCompanyParentCacheModel ivldCompanyParentCacheModel = (IvldCompanyParentCacheModel)obj;

		if (ivldCompanyParentSid == ivldCompanyParentCacheModel.ivldCompanyParentSid) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, ivldCompanyParentSid);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(45);

		sb.append("{parentcompanyId=");
		sb.append(parentcompanyId);
		sb.append(", priorParentcompanyId=");
		sb.append(priorParentcompanyId);
		sb.append(", reasonForFailure=");
		sb.append(reasonForFailure);
		sb.append(", companyIdString=");
		sb.append(companyIdString);
		sb.append(", lastUpdatedDate=");
		sb.append(lastUpdatedDate);
		sb.append(", parentEndDate=");
		sb.append(parentEndDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", parentDetailsIntfid=");
		sb.append(parentDetailsIntfid);
		sb.append(", priorParentStartDate=");
		sb.append(priorParentStartDate);
		sb.append(", source=");
		sb.append(source);
		sb.append(", createdBy=");
		sb.append(createdBy);
		sb.append(", createdDate=");
		sb.append(createdDate);
		sb.append(", addChgDelIndicator=");
		sb.append(addChgDelIndicator);
		sb.append(", batchId=");
		sb.append(batchId);
		sb.append(", ivldCompanyParentSid=");
		sb.append(ivldCompanyParentSid);
		sb.append(", errorField=");
		sb.append(errorField);
		sb.append(", errorCode=");
		sb.append(errorCode);
		sb.append(", intfInsertedDate=");
		sb.append(intfInsertedDate);
		sb.append(", modifiedBy=");
		sb.append(modifiedBy);
		sb.append(", reprocessedFlag=");
		sb.append(reprocessedFlag);
		sb.append(", parentStartDate=");
		sb.append(parentStartDate);
		sb.append(", checkRecord=");
		sb.append(checkRecord);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public IvldCompanyParent toEntityModel() {
		IvldCompanyParentImpl ivldCompanyParentImpl = new IvldCompanyParentImpl();

		if (parentcompanyId == null) {
			ivldCompanyParentImpl.setParentcompanyId(StringPool.BLANK);
		}
		else {
			ivldCompanyParentImpl.setParentcompanyId(parentcompanyId);
		}

		if (priorParentcompanyId == null) {
			ivldCompanyParentImpl.setPriorParentcompanyId(StringPool.BLANK);
		}
		else {
			ivldCompanyParentImpl.setPriorParentcompanyId(priorParentcompanyId);
		}

		if (reasonForFailure == null) {
			ivldCompanyParentImpl.setReasonForFailure(StringPool.BLANK);
		}
		else {
			ivldCompanyParentImpl.setReasonForFailure(reasonForFailure);
		}

		if (companyIdString == null) {
			ivldCompanyParentImpl.setCompanyIdString(StringPool.BLANK);
		}
		else {
			ivldCompanyParentImpl.setCompanyIdString(companyIdString);
		}

		if (lastUpdatedDate == null) {
			ivldCompanyParentImpl.setLastUpdatedDate(StringPool.BLANK);
		}
		else {
			ivldCompanyParentImpl.setLastUpdatedDate(lastUpdatedDate);
		}

		if (parentEndDate == null) {
			ivldCompanyParentImpl.setParentEndDate(StringPool.BLANK);
		}
		else {
			ivldCompanyParentImpl.setParentEndDate(parentEndDate);
		}

		if (modifiedDate == Long.MIN_VALUE) {
			ivldCompanyParentImpl.setModifiedDate(null);
		}
		else {
			ivldCompanyParentImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (parentDetailsIntfid == null) {
			ivldCompanyParentImpl.setParentDetailsIntfid(StringPool.BLANK);
		}
		else {
			ivldCompanyParentImpl.setParentDetailsIntfid(parentDetailsIntfid);
		}

		if (priorParentStartDate == null) {
			ivldCompanyParentImpl.setPriorParentStartDate(StringPool.BLANK);
		}
		else {
			ivldCompanyParentImpl.setPriorParentStartDate(priorParentStartDate);
		}

		if (source == null) {
			ivldCompanyParentImpl.setSource(StringPool.BLANK);
		}
		else {
			ivldCompanyParentImpl.setSource(source);
		}

		if (createdBy == null) {
			ivldCompanyParentImpl.setCreatedBy(StringPool.BLANK);
		}
		else {
			ivldCompanyParentImpl.setCreatedBy(createdBy);
		}

		if (createdDate == Long.MIN_VALUE) {
			ivldCompanyParentImpl.setCreatedDate(null);
		}
		else {
			ivldCompanyParentImpl.setCreatedDate(new Date(createdDate));
		}

		if (addChgDelIndicator == null) {
			ivldCompanyParentImpl.setAddChgDelIndicator(StringPool.BLANK);
		}
		else {
			ivldCompanyParentImpl.setAddChgDelIndicator(addChgDelIndicator);
		}

		if (batchId == null) {
			ivldCompanyParentImpl.setBatchId(StringPool.BLANK);
		}
		else {
			ivldCompanyParentImpl.setBatchId(batchId);
		}

		ivldCompanyParentImpl.setIvldCompanyParentSid(ivldCompanyParentSid);

		if (errorField == null) {
			ivldCompanyParentImpl.setErrorField(StringPool.BLANK);
		}
		else {
			ivldCompanyParentImpl.setErrorField(errorField);
		}

		if (errorCode == null) {
			ivldCompanyParentImpl.setErrorCode(StringPool.BLANK);
		}
		else {
			ivldCompanyParentImpl.setErrorCode(errorCode);
		}

		if (intfInsertedDate == Long.MIN_VALUE) {
			ivldCompanyParentImpl.setIntfInsertedDate(null);
		}
		else {
			ivldCompanyParentImpl.setIntfInsertedDate(new Date(intfInsertedDate));
		}

		if (modifiedBy == null) {
			ivldCompanyParentImpl.setModifiedBy(StringPool.BLANK);
		}
		else {
			ivldCompanyParentImpl.setModifiedBy(modifiedBy);
		}

		if (reprocessedFlag == null) {
			ivldCompanyParentImpl.setReprocessedFlag(StringPool.BLANK);
		}
		else {
			ivldCompanyParentImpl.setReprocessedFlag(reprocessedFlag);
		}

		if (parentStartDate == null) {
			ivldCompanyParentImpl.setParentStartDate(StringPool.BLANK);
		}
		else {
			ivldCompanyParentImpl.setParentStartDate(parentStartDate);
		}

		ivldCompanyParentImpl.setCheckRecord(checkRecord);

		ivldCompanyParentImpl.resetOriginalValues();

		return ivldCompanyParentImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		parentcompanyId = objectInput.readUTF();
		priorParentcompanyId = objectInput.readUTF();
		reasonForFailure = objectInput.readUTF();
		companyIdString = objectInput.readUTF();
		lastUpdatedDate = objectInput.readUTF();
		parentEndDate = objectInput.readUTF();
		modifiedDate = objectInput.readLong();
		parentDetailsIntfid = objectInput.readUTF();
		priorParentStartDate = objectInput.readUTF();
		source = objectInput.readUTF();
		createdBy = objectInput.readUTF();
		createdDate = objectInput.readLong();
		addChgDelIndicator = objectInput.readUTF();
		batchId = objectInput.readUTF();

		ivldCompanyParentSid = objectInput.readInt();
		errorField = objectInput.readUTF();
		errorCode = objectInput.readUTF();
		intfInsertedDate = objectInput.readLong();
		modifiedBy = objectInput.readUTF();
		reprocessedFlag = objectInput.readUTF();
		parentStartDate = objectInput.readUTF();

		checkRecord = objectInput.readBoolean();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		if (parentcompanyId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(parentcompanyId);
		}

		if (priorParentcompanyId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(priorParentcompanyId);
		}

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

		if (lastUpdatedDate == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(lastUpdatedDate);
		}

		if (parentEndDate == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(parentEndDate);
		}

		objectOutput.writeLong(modifiedDate);

		if (parentDetailsIntfid == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(parentDetailsIntfid);
		}

		if (priorParentStartDate == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(priorParentStartDate);
		}

		if (source == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(source);
		}

		if (createdBy == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(createdBy);
		}

		objectOutput.writeLong(createdDate);

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

		objectOutput.writeInt(ivldCompanyParentSid);

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

		if (reprocessedFlag == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(reprocessedFlag);
		}

		if (parentStartDate == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(parentStartDate);
		}

		objectOutput.writeBoolean(checkRecord);
	}

	public String parentcompanyId;
	public String priorParentcompanyId;
	public String reasonForFailure;
	public String companyIdString;
	public String lastUpdatedDate;
	public String parentEndDate;
	public long modifiedDate;
	public String parentDetailsIntfid;
	public String priorParentStartDate;
	public String source;
	public String createdBy;
	public long createdDate;
	public String addChgDelIndicator;
	public String batchId;
	public int ivldCompanyParentSid;
	public String errorField;
	public String errorCode;
	public long intfInsertedDate;
	public String modifiedBy;
	public String reprocessedFlag;
	public String parentStartDate;
	public boolean checkRecord;
}