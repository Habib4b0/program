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

import com.stpl.app.model.IfpContract;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing IfpContract in entity cache.
 *
 * @author
 * @see IfpContract
 * @generated
 */
@ProviderType
public class IfpContractCacheModel implements CacheModel<IfpContract>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof IfpContractCacheModel)) {
			return false;
		}

		IfpContractCacheModel ifpContractCacheModel = (IfpContractCacheModel)obj;

		if (ifpContractSid == ifpContractCacheModel.ifpContractSid) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, ifpContractSid);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(49);

		sb.append("{cfpContractSid=");
		sb.append(cfpContractSid);
		sb.append(", parentIfpName=");
		sb.append(parentIfpName);
		sb.append(", ifpContractAttachedDate=");
		sb.append(ifpContractAttachedDate);
		sb.append(", ifpStatus=");
		sb.append(ifpStatus);
		sb.append(", ifpStartDate=");
		sb.append(ifpStartDate);
		sb.append(", ifpContractAttachedStatus=");
		sb.append(ifpContractAttachedStatus);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", ifpCategory=");
		sb.append(ifpCategory);
		sb.append(", recordLockStatus=");
		sb.append(recordLockStatus);
		sb.append(", ifpEndDate=");
		sb.append(ifpEndDate);
		sb.append(", createdDate=");
		sb.append(createdDate);
		sb.append(", createdBy=");
		sb.append(createdBy);
		sb.append(", source=");
		sb.append(source);
		sb.append(", ifpDesignation=");
		sb.append(ifpDesignation);
		sb.append(", parentIfpId=");
		sb.append(parentIfpId);
		sb.append(", batchId=");
		sb.append(batchId);
		sb.append(", contractMasterSid=");
		sb.append(contractMasterSid);
		sb.append(", ifpType=");
		sb.append(ifpType);
		sb.append(", ifpName=");
		sb.append(ifpName);
		sb.append(", ifpNo=");
		sb.append(ifpNo);
		sb.append(", modifiedBy=");
		sb.append(modifiedBy);
		sb.append(", inboundStatus=");
		sb.append(inboundStatus);
		sb.append(", ifpContractSid=");
		sb.append(ifpContractSid);
		sb.append(", ifpModelSid=");
		sb.append(ifpModelSid);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public IfpContract toEntityModel() {
		IfpContractImpl ifpContractImpl = new IfpContractImpl();

		if (cfpContractSid == null) {
			ifpContractImpl.setCfpContractSid(StringPool.BLANK);
		}
		else {
			ifpContractImpl.setCfpContractSid(cfpContractSid);
		}

		if (parentIfpName == null) {
			ifpContractImpl.setParentIfpName(StringPool.BLANK);
		}
		else {
			ifpContractImpl.setParentIfpName(parentIfpName);
		}

		if (ifpContractAttachedDate == Long.MIN_VALUE) {
			ifpContractImpl.setIfpContractAttachedDate(null);
		}
		else {
			ifpContractImpl.setIfpContractAttachedDate(new Date(
					ifpContractAttachedDate));
		}

		ifpContractImpl.setIfpStatus(ifpStatus);

		if (ifpStartDate == Long.MIN_VALUE) {
			ifpContractImpl.setIfpStartDate(null);
		}
		else {
			ifpContractImpl.setIfpStartDate(new Date(ifpStartDate));
		}

		ifpContractImpl.setIfpContractAttachedStatus(ifpContractAttachedStatus);

		if (modifiedDate == Long.MIN_VALUE) {
			ifpContractImpl.setModifiedDate(null);
		}
		else {
			ifpContractImpl.setModifiedDate(new Date(modifiedDate));
		}

		ifpContractImpl.setIfpCategory(ifpCategory);
		ifpContractImpl.setRecordLockStatus(recordLockStatus);

		if (ifpEndDate == Long.MIN_VALUE) {
			ifpContractImpl.setIfpEndDate(null);
		}
		else {
			ifpContractImpl.setIfpEndDate(new Date(ifpEndDate));
		}

		if (createdDate == Long.MIN_VALUE) {
			ifpContractImpl.setCreatedDate(null);
		}
		else {
			ifpContractImpl.setCreatedDate(new Date(createdDate));
		}

		ifpContractImpl.setCreatedBy(createdBy);

		if (source == null) {
			ifpContractImpl.setSource(StringPool.BLANK);
		}
		else {
			ifpContractImpl.setSource(source);
		}

		if (ifpDesignation == null) {
			ifpContractImpl.setIfpDesignation(StringPool.BLANK);
		}
		else {
			ifpContractImpl.setIfpDesignation(ifpDesignation);
		}

		if (parentIfpId == null) {
			ifpContractImpl.setParentIfpId(StringPool.BLANK);
		}
		else {
			ifpContractImpl.setParentIfpId(parentIfpId);
		}

		if (batchId == null) {
			ifpContractImpl.setBatchId(StringPool.BLANK);
		}
		else {
			ifpContractImpl.setBatchId(batchId);
		}

		ifpContractImpl.setContractMasterSid(contractMasterSid);
		ifpContractImpl.setIfpType(ifpType);

		if (ifpName == null) {
			ifpContractImpl.setIfpName(StringPool.BLANK);
		}
		else {
			ifpContractImpl.setIfpName(ifpName);
		}

		if (ifpNo == null) {
			ifpContractImpl.setIfpNo(StringPool.BLANK);
		}
		else {
			ifpContractImpl.setIfpNo(ifpNo);
		}

		ifpContractImpl.setModifiedBy(modifiedBy);

		if (inboundStatus == null) {
			ifpContractImpl.setInboundStatus(StringPool.BLANK);
		}
		else {
			ifpContractImpl.setInboundStatus(inboundStatus);
		}

		ifpContractImpl.setIfpContractSid(ifpContractSid);
		ifpContractImpl.setIfpModelSid(ifpModelSid);

		ifpContractImpl.resetOriginalValues();

		return ifpContractImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		cfpContractSid = objectInput.readUTF();
		parentIfpName = objectInput.readUTF();
		ifpContractAttachedDate = objectInput.readLong();

		ifpStatus = objectInput.readInt();
		ifpStartDate = objectInput.readLong();

		ifpContractAttachedStatus = objectInput.readInt();
		modifiedDate = objectInput.readLong();

		ifpCategory = objectInput.readInt();

		recordLockStatus = objectInput.readBoolean();
		ifpEndDate = objectInput.readLong();
		createdDate = objectInput.readLong();

		createdBy = objectInput.readInt();
		source = objectInput.readUTF();
		ifpDesignation = objectInput.readUTF();
		parentIfpId = objectInput.readUTF();
		batchId = objectInput.readUTF();

		contractMasterSid = objectInput.readInt();

		ifpType = objectInput.readInt();
		ifpName = objectInput.readUTF();
		ifpNo = objectInput.readUTF();

		modifiedBy = objectInput.readInt();
		inboundStatus = objectInput.readUTF();

		ifpContractSid = objectInput.readInt();

		ifpModelSid = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		if (cfpContractSid == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(cfpContractSid);
		}

		if (parentIfpName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(parentIfpName);
		}

		objectOutput.writeLong(ifpContractAttachedDate);

		objectOutput.writeInt(ifpStatus);
		objectOutput.writeLong(ifpStartDate);

		objectOutput.writeInt(ifpContractAttachedStatus);
		objectOutput.writeLong(modifiedDate);

		objectOutput.writeInt(ifpCategory);

		objectOutput.writeBoolean(recordLockStatus);
		objectOutput.writeLong(ifpEndDate);
		objectOutput.writeLong(createdDate);

		objectOutput.writeInt(createdBy);

		if (source == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(source);
		}

		if (ifpDesignation == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(ifpDesignation);
		}

		if (parentIfpId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(parentIfpId);
		}

		if (batchId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(batchId);
		}

		objectOutput.writeInt(contractMasterSid);

		objectOutput.writeInt(ifpType);

		if (ifpName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(ifpName);
		}

		if (ifpNo == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(ifpNo);
		}

		objectOutput.writeInt(modifiedBy);

		if (inboundStatus == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(inboundStatus);
		}

		objectOutput.writeInt(ifpContractSid);

		objectOutput.writeInt(ifpModelSid);
	}

	public String cfpContractSid;
	public String parentIfpName;
	public long ifpContractAttachedDate;
	public int ifpStatus;
	public long ifpStartDate;
	public int ifpContractAttachedStatus;
	public long modifiedDate;
	public int ifpCategory;
	public boolean recordLockStatus;
	public long ifpEndDate;
	public long createdDate;
	public int createdBy;
	public String source;
	public String ifpDesignation;
	public String parentIfpId;
	public String batchId;
	public int contractMasterSid;
	public int ifpType;
	public String ifpName;
	public String ifpNo;
	public int modifiedBy;
	public String inboundStatus;
	public int ifpContractSid;
	public int ifpModelSid;
}