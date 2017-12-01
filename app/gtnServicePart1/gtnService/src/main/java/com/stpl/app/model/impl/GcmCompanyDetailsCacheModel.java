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

import com.stpl.app.model.GcmCompanyDetails;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing GcmCompanyDetails in entity cache.
 *
 * @author
 * @see GcmCompanyDetails
 * @generated
 */
@ProviderType
public class GcmCompanyDetailsCacheModel implements CacheModel<GcmCompanyDetails>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof GcmCompanyDetailsCacheModel)) {
			return false;
		}

		GcmCompanyDetailsCacheModel gcmCompanyDetailsCacheModel = (GcmCompanyDetailsCacheModel)obj;

		if (gcmCompanyDetailsSid == gcmCompanyDetailsCacheModel.gcmCompanyDetailsSid) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, gcmCompanyDetailsSid);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(49);

		sb.append("{checkRecord=");
		sb.append(checkRecord);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", moduleName=");
		sb.append(moduleName);
		sb.append(", companyStringId=");
		sb.append(companyStringId);
		sb.append(", cfpDetailsTradeClass=");
		sb.append(cfpDetailsTradeClass);
		sb.append(", companyName=");
		sb.append(companyName);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", gcmCompanyDetailsSid=");
		sb.append(gcmCompanyDetailsSid);
		sb.append(", itemCfpDetailsSid=");
		sb.append(itemCfpDetailsSid);
		sb.append(", createdDate=");
		sb.append(createdDate);
		sb.append(", createdBy=");
		sb.append(createdBy);
		sb.append(", companyStartDate=");
		sb.append(companyStartDate);
		sb.append(", companyNo=");
		sb.append(companyNo);
		sb.append(", companyStatus=");
		sb.append(companyStatus);
		sb.append(", sessionId=");
		sb.append(sessionId);
		sb.append(", companyEndDate=");
		sb.append(companyEndDate);
		sb.append(", cfpDetailsStartDate=");
		sb.append(cfpDetailsStartDate);
		sb.append(", operation=");
		sb.append(operation);
		sb.append(", cfpModelSid=");
		sb.append(cfpModelSid);
		sb.append(", modifiedBy=");
		sb.append(modifiedBy);
		sb.append(", subModuleName=");
		sb.append(subModuleName);
		sb.append(", cfpDetailsEndDate=");
		sb.append(cfpDetailsEndDate);
		sb.append(", companyStatusSid=");
		sb.append(companyStatusSid);
		sb.append(", companyMasterSid=");
		sb.append(companyMasterSid);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public GcmCompanyDetails toEntityModel() {
		GcmCompanyDetailsImpl gcmCompanyDetailsImpl = new GcmCompanyDetailsImpl();

		gcmCompanyDetailsImpl.setCheckRecord(checkRecord);
		gcmCompanyDetailsImpl.setUserId(userId);

		if (moduleName == null) {
			gcmCompanyDetailsImpl.setModuleName(StringPool.BLANK);
		}
		else {
			gcmCompanyDetailsImpl.setModuleName(moduleName);
		}

		if (companyStringId == null) {
			gcmCompanyDetailsImpl.setCompanyStringId(StringPool.BLANK);
		}
		else {
			gcmCompanyDetailsImpl.setCompanyStringId(companyStringId);
		}

		if (cfpDetailsTradeClass == null) {
			gcmCompanyDetailsImpl.setCfpDetailsTradeClass(StringPool.BLANK);
		}
		else {
			gcmCompanyDetailsImpl.setCfpDetailsTradeClass(cfpDetailsTradeClass);
		}

		if (companyName == null) {
			gcmCompanyDetailsImpl.setCompanyName(StringPool.BLANK);
		}
		else {
			gcmCompanyDetailsImpl.setCompanyName(companyName);
		}

		if (modifiedDate == Long.MIN_VALUE) {
			gcmCompanyDetailsImpl.setModifiedDate(null);
		}
		else {
			gcmCompanyDetailsImpl.setModifiedDate(new Date(modifiedDate));
		}

		gcmCompanyDetailsImpl.setGcmCompanyDetailsSid(gcmCompanyDetailsSid);
		gcmCompanyDetailsImpl.setItemCfpDetailsSid(itemCfpDetailsSid);

		if (createdDate == Long.MIN_VALUE) {
			gcmCompanyDetailsImpl.setCreatedDate(null);
		}
		else {
			gcmCompanyDetailsImpl.setCreatedDate(new Date(createdDate));
		}

		gcmCompanyDetailsImpl.setCreatedBy(createdBy);

		if (companyStartDate == Long.MIN_VALUE) {
			gcmCompanyDetailsImpl.setCompanyStartDate(null);
		}
		else {
			gcmCompanyDetailsImpl.setCompanyStartDate(new Date(companyStartDate));
		}

		if (companyNo == null) {
			gcmCompanyDetailsImpl.setCompanyNo(StringPool.BLANK);
		}
		else {
			gcmCompanyDetailsImpl.setCompanyNo(companyNo);
		}

		if (companyStatus == null) {
			gcmCompanyDetailsImpl.setCompanyStatus(StringPool.BLANK);
		}
		else {
			gcmCompanyDetailsImpl.setCompanyStatus(companyStatus);
		}

		if (sessionId == null) {
			gcmCompanyDetailsImpl.setSessionId(StringPool.BLANK);
		}
		else {
			gcmCompanyDetailsImpl.setSessionId(sessionId);
		}

		if (companyEndDate == Long.MIN_VALUE) {
			gcmCompanyDetailsImpl.setCompanyEndDate(null);
		}
		else {
			gcmCompanyDetailsImpl.setCompanyEndDate(new Date(companyEndDate));
		}

		if (cfpDetailsStartDate == Long.MIN_VALUE) {
			gcmCompanyDetailsImpl.setCfpDetailsStartDate(null);
		}
		else {
			gcmCompanyDetailsImpl.setCfpDetailsStartDate(new Date(
					cfpDetailsStartDate));
		}

		if (operation == null) {
			gcmCompanyDetailsImpl.setOperation(StringPool.BLANK);
		}
		else {
			gcmCompanyDetailsImpl.setOperation(operation);
		}

		gcmCompanyDetailsImpl.setCfpModelSid(cfpModelSid);
		gcmCompanyDetailsImpl.setModifiedBy(modifiedBy);

		if (subModuleName == null) {
			gcmCompanyDetailsImpl.setSubModuleName(StringPool.BLANK);
		}
		else {
			gcmCompanyDetailsImpl.setSubModuleName(subModuleName);
		}

		if (cfpDetailsEndDate == Long.MIN_VALUE) {
			gcmCompanyDetailsImpl.setCfpDetailsEndDate(null);
		}
		else {
			gcmCompanyDetailsImpl.setCfpDetailsEndDate(new Date(
					cfpDetailsEndDate));
		}

		gcmCompanyDetailsImpl.setCompanyStatusSid(companyStatusSid);
		gcmCompanyDetailsImpl.setCompanyMasterSid(companyMasterSid);

		gcmCompanyDetailsImpl.resetOriginalValues();

		return gcmCompanyDetailsImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		checkRecord = objectInput.readBoolean();

		userId = objectInput.readInt();
		moduleName = objectInput.readUTF();
		companyStringId = objectInput.readUTF();
		cfpDetailsTradeClass = objectInput.readUTF();
		companyName = objectInput.readUTF();
		modifiedDate = objectInput.readLong();

		gcmCompanyDetailsSid = objectInput.readInt();

		itemCfpDetailsSid = objectInput.readInt();
		createdDate = objectInput.readLong();

		createdBy = objectInput.readInt();
		companyStartDate = objectInput.readLong();
		companyNo = objectInput.readUTF();
		companyStatus = objectInput.readUTF();
		sessionId = objectInput.readUTF();
		companyEndDate = objectInput.readLong();
		cfpDetailsStartDate = objectInput.readLong();
		operation = objectInput.readUTF();

		cfpModelSid = objectInput.readInt();

		modifiedBy = objectInput.readInt();
		subModuleName = objectInput.readUTF();
		cfpDetailsEndDate = objectInput.readLong();

		companyStatusSid = objectInput.readInt();

		companyMasterSid = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeBoolean(checkRecord);

		objectOutput.writeInt(userId);

		if (moduleName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(moduleName);
		}

		if (companyStringId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(companyStringId);
		}

		if (cfpDetailsTradeClass == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(cfpDetailsTradeClass);
		}

		if (companyName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(companyName);
		}

		objectOutput.writeLong(modifiedDate);

		objectOutput.writeInt(gcmCompanyDetailsSid);

		objectOutput.writeInt(itemCfpDetailsSid);
		objectOutput.writeLong(createdDate);

		objectOutput.writeInt(createdBy);
		objectOutput.writeLong(companyStartDate);

		if (companyNo == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(companyNo);
		}

		if (companyStatus == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(companyStatus);
		}

		if (sessionId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(sessionId);
		}

		objectOutput.writeLong(companyEndDate);
		objectOutput.writeLong(cfpDetailsStartDate);

		if (operation == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(operation);
		}

		objectOutput.writeInt(cfpModelSid);

		objectOutput.writeInt(modifiedBy);

		if (subModuleName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(subModuleName);
		}

		objectOutput.writeLong(cfpDetailsEndDate);

		objectOutput.writeInt(companyStatusSid);

		objectOutput.writeInt(companyMasterSid);
	}

	public boolean checkRecord;
	public int userId;
	public String moduleName;
	public String companyStringId;
	public String cfpDetailsTradeClass;
	public String companyName;
	public long modifiedDate;
	public int gcmCompanyDetailsSid;
	public int itemCfpDetailsSid;
	public long createdDate;
	public int createdBy;
	public long companyStartDate;
	public String companyNo;
	public String companyStatus;
	public String sessionId;
	public long companyEndDate;
	public long cfpDetailsStartDate;
	public String operation;
	public int cfpModelSid;
	public int modifiedBy;
	public String subModuleName;
	public long cfpDetailsEndDate;
	public int companyStatusSid;
	public int companyMasterSid;
}