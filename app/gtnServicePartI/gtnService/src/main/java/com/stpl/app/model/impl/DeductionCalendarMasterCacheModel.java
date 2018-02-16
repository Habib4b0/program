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

import com.stpl.app.model.DeductionCalendarMaster;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing DeductionCalendarMaster in entity cache.
 *
 * @author
 * @see DeductionCalendarMaster
 * @generated
 */
@ProviderType
public class DeductionCalendarMasterCacheModel implements CacheModel<DeductionCalendarMaster>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof DeductionCalendarMasterCacheModel)) {
			return false;
		}

		DeductionCalendarMasterCacheModel deductionCalendarMasterCacheModel = (DeductionCalendarMasterCacheModel)obj;

		if (deductionCalendarMasterSid == deductionCalendarMasterCacheModel.deductionCalendarMasterSid) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, deductionCalendarMasterSid);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(25);

		sb.append("{createdBy=");
		sb.append(createdBy);
		sb.append(", deductionCalendarMasterSid=");
		sb.append(deductionCalendarMasterSid);
		sb.append(", deductionCalendarNo=");
		sb.append(deductionCalendarNo);
		sb.append(", modifiedBy=");
		sb.append(modifiedBy);
		sb.append(", createdDate=");
		sb.append(createdDate);
		sb.append(", category=");
		sb.append(category);
		sb.append(", additionalNotes=");
		sb.append(additionalNotes);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", deductionCalendarName=");
		sb.append(deductionCalendarName);
		sb.append(", deductionCalendarDesc=");
		sb.append(deductionCalendarDesc);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", inboundStatus=");
		sb.append(inboundStatus);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public DeductionCalendarMaster toEntityModel() {
		DeductionCalendarMasterImpl deductionCalendarMasterImpl = new DeductionCalendarMasterImpl();

		deductionCalendarMasterImpl.setCreatedBy(createdBy);
		deductionCalendarMasterImpl.setDeductionCalendarMasterSid(deductionCalendarMasterSid);

		if (deductionCalendarNo == null) {
			deductionCalendarMasterImpl.setDeductionCalendarNo(StringPool.BLANK);
		}
		else {
			deductionCalendarMasterImpl.setDeductionCalendarNo(deductionCalendarNo);
		}

		deductionCalendarMasterImpl.setModifiedBy(modifiedBy);

		if (createdDate == Long.MIN_VALUE) {
			deductionCalendarMasterImpl.setCreatedDate(null);
		}
		else {
			deductionCalendarMasterImpl.setCreatedDate(new Date(createdDate));
		}

		deductionCalendarMasterImpl.setCategory(category);

		if (additionalNotes == null) {
			deductionCalendarMasterImpl.setAdditionalNotes(StringPool.BLANK);
		}
		else {
			deductionCalendarMasterImpl.setAdditionalNotes(additionalNotes);
		}

		deductionCalendarMasterImpl.setUserId(userId);

		if (deductionCalendarName == null) {
			deductionCalendarMasterImpl.setDeductionCalendarName(StringPool.BLANK);
		}
		else {
			deductionCalendarMasterImpl.setDeductionCalendarName(deductionCalendarName);
		}

		if (deductionCalendarDesc == null) {
			deductionCalendarMasterImpl.setDeductionCalendarDesc(StringPool.BLANK);
		}
		else {
			deductionCalendarMasterImpl.setDeductionCalendarDesc(deductionCalendarDesc);
		}

		if (modifiedDate == Long.MIN_VALUE) {
			deductionCalendarMasterImpl.setModifiedDate(null);
		}
		else {
			deductionCalendarMasterImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (inboundStatus == null) {
			deductionCalendarMasterImpl.setInboundStatus(StringPool.BLANK);
		}
		else {
			deductionCalendarMasterImpl.setInboundStatus(inboundStatus);
		}

		deductionCalendarMasterImpl.resetOriginalValues();

		return deductionCalendarMasterImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		createdBy = objectInput.readInt();

		deductionCalendarMasterSid = objectInput.readInt();
		deductionCalendarNo = objectInput.readUTF();

		modifiedBy = objectInput.readInt();
		createdDate = objectInput.readLong();

		category = objectInput.readInt();
		additionalNotes = objectInput.readUTF();

		userId = objectInput.readInt();
		deductionCalendarName = objectInput.readUTF();
		deductionCalendarDesc = objectInput.readUTF();
		modifiedDate = objectInput.readLong();
		inboundStatus = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeInt(createdBy);

		objectOutput.writeInt(deductionCalendarMasterSid);

		if (deductionCalendarNo == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(deductionCalendarNo);
		}

		objectOutput.writeInt(modifiedBy);
		objectOutput.writeLong(createdDate);

		objectOutput.writeInt(category);

		if (additionalNotes == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(additionalNotes);
		}

		objectOutput.writeInt(userId);

		if (deductionCalendarName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(deductionCalendarName);
		}

		if (deductionCalendarDesc == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(deductionCalendarDesc);
		}

		objectOutput.writeLong(modifiedDate);

		if (inboundStatus == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(inboundStatus);
		}
	}

	public int createdBy;
	public int deductionCalendarMasterSid;
	public String deductionCalendarNo;
	public int modifiedBy;
	public long createdDate;
	public int category;
	public String additionalNotes;
	public int userId;
	public String deductionCalendarName;
	public String deductionCalendarDesc;
	public long modifiedDate;
	public String inboundStatus;
}