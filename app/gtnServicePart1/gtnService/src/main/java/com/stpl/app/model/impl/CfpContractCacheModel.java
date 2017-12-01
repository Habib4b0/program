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

import com.stpl.app.model.CfpContract;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing CfpContract in entity cache.
 *
 * @author
 * @see CfpContract
 * @generated
 */
@ProviderType
public class CfpContractCacheModel implements CacheModel<CfpContract>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CfpContractCacheModel)) {
			return false;
		}

		CfpContractCacheModel cfpContractCacheModel = (CfpContractCacheModel)obj;

		if (cfpContractSid == cfpContractCacheModel.cfpContractSid) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, cfpContractSid);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(51);

		sb.append("{createdBy=");
		sb.append(createdBy);
		sb.append(", cfpContractSid=");
		sb.append(cfpContractSid);
		sb.append(", cfpType=");
		sb.append(cfpType);
		sb.append(", cfpTradeClass=");
		sb.append(cfpTradeClass);
		sb.append(", modifiedBy=");
		sb.append(modifiedBy);
		sb.append(", createdDate=");
		sb.append(createdDate);
		sb.append(", contractMasterSid=");
		sb.append(contractMasterSid);
		sb.append(", cfpContractAttachedDate=");
		sb.append(cfpContractAttachedDate);
		sb.append(", cfpModelSid=");
		sb.append(cfpModelSid);
		sb.append(", batchId=");
		sb.append(batchId);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", recordLockStatus=");
		sb.append(recordLockStatus);
		sb.append(", cfpDesignation=");
		sb.append(cfpDesignation);
		sb.append(", cfpName=");
		sb.append(cfpName);
		sb.append(", cfpNo=");
		sb.append(cfpNo);
		sb.append(", cfpCategory=");
		sb.append(cfpCategory);
		sb.append(", source=");
		sb.append(source);
		sb.append(", cfpStatus=");
		sb.append(cfpStatus);
		sb.append(", parentCfpId=");
		sb.append(parentCfpId);
		sb.append(", cfpContractAttachedStatus=");
		sb.append(cfpContractAttachedStatus);
		sb.append(", cfpStartDate=");
		sb.append(cfpStartDate);
		sb.append(", cfpEndDate=");
		sb.append(cfpEndDate);
		sb.append(", parentCfpName=");
		sb.append(parentCfpName);
		sb.append(", inboundStatus=");
		sb.append(inboundStatus);
		sb.append(", salesInclusion=");
		sb.append(salesInclusion);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public CfpContract toEntityModel() {
		CfpContractImpl cfpContractImpl = new CfpContractImpl();

		cfpContractImpl.setCreatedBy(createdBy);
		cfpContractImpl.setCfpContractSid(cfpContractSid);
		cfpContractImpl.setCfpType(cfpType);
		cfpContractImpl.setCfpTradeClass(cfpTradeClass);
		cfpContractImpl.setModifiedBy(modifiedBy);

		if (createdDate == Long.MIN_VALUE) {
			cfpContractImpl.setCreatedDate(null);
		}
		else {
			cfpContractImpl.setCreatedDate(new Date(createdDate));
		}

		cfpContractImpl.setContractMasterSid(contractMasterSid);

		if (cfpContractAttachedDate == Long.MIN_VALUE) {
			cfpContractImpl.setCfpContractAttachedDate(null);
		}
		else {
			cfpContractImpl.setCfpContractAttachedDate(new Date(
					cfpContractAttachedDate));
		}

		cfpContractImpl.setCfpModelSid(cfpModelSid);

		if (batchId == null) {
			cfpContractImpl.setBatchId(StringPool.BLANK);
		}
		else {
			cfpContractImpl.setBatchId(batchId);
		}

		if (modifiedDate == Long.MIN_VALUE) {
			cfpContractImpl.setModifiedDate(null);
		}
		else {
			cfpContractImpl.setModifiedDate(new Date(modifiedDate));
		}

		cfpContractImpl.setRecordLockStatus(recordLockStatus);

		if (cfpDesignation == null) {
			cfpContractImpl.setCfpDesignation(StringPool.BLANK);
		}
		else {
			cfpContractImpl.setCfpDesignation(cfpDesignation);
		}

		if (cfpName == null) {
			cfpContractImpl.setCfpName(StringPool.BLANK);
		}
		else {
			cfpContractImpl.setCfpName(cfpName);
		}

		if (cfpNo == null) {
			cfpContractImpl.setCfpNo(StringPool.BLANK);
		}
		else {
			cfpContractImpl.setCfpNo(cfpNo);
		}

		cfpContractImpl.setCfpCategory(cfpCategory);

		if (source == null) {
			cfpContractImpl.setSource(StringPool.BLANK);
		}
		else {
			cfpContractImpl.setSource(source);
		}

		cfpContractImpl.setCfpStatus(cfpStatus);
		cfpContractImpl.setParentCfpId(parentCfpId);
		cfpContractImpl.setCfpContractAttachedStatus(cfpContractAttachedStatus);

		if (cfpStartDate == Long.MIN_VALUE) {
			cfpContractImpl.setCfpStartDate(null);
		}
		else {
			cfpContractImpl.setCfpStartDate(new Date(cfpStartDate));
		}

		if (cfpEndDate == Long.MIN_VALUE) {
			cfpContractImpl.setCfpEndDate(null);
		}
		else {
			cfpContractImpl.setCfpEndDate(new Date(cfpEndDate));
		}

		if (parentCfpName == null) {
			cfpContractImpl.setParentCfpName(StringPool.BLANK);
		}
		else {
			cfpContractImpl.setParentCfpName(parentCfpName);
		}

		if (inboundStatus == null) {
			cfpContractImpl.setInboundStatus(StringPool.BLANK);
		}
		else {
			cfpContractImpl.setInboundStatus(inboundStatus);
		}

		cfpContractImpl.setSalesInclusion(salesInclusion);

		cfpContractImpl.resetOriginalValues();

		return cfpContractImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		createdBy = objectInput.readInt();

		cfpContractSid = objectInput.readInt();

		cfpType = objectInput.readInt();

		cfpTradeClass = objectInput.readInt();

		modifiedBy = objectInput.readInt();
		createdDate = objectInput.readLong();

		contractMasterSid = objectInput.readInt();
		cfpContractAttachedDate = objectInput.readLong();

		cfpModelSid = objectInput.readInt();
		batchId = objectInput.readUTF();
		modifiedDate = objectInput.readLong();

		recordLockStatus = objectInput.readBoolean();
		cfpDesignation = objectInput.readUTF();
		cfpName = objectInput.readUTF();
		cfpNo = objectInput.readUTF();

		cfpCategory = objectInput.readInt();
		source = objectInput.readUTF();

		cfpStatus = objectInput.readInt();

		parentCfpId = objectInput.readInt();

		cfpContractAttachedStatus = objectInput.readInt();
		cfpStartDate = objectInput.readLong();
		cfpEndDate = objectInput.readLong();
		parentCfpName = objectInput.readUTF();
		inboundStatus = objectInput.readUTF();

		salesInclusion = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeInt(createdBy);

		objectOutput.writeInt(cfpContractSid);

		objectOutput.writeInt(cfpType);

		objectOutput.writeInt(cfpTradeClass);

		objectOutput.writeInt(modifiedBy);
		objectOutput.writeLong(createdDate);

		objectOutput.writeInt(contractMasterSid);
		objectOutput.writeLong(cfpContractAttachedDate);

		objectOutput.writeInt(cfpModelSid);

		if (batchId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(batchId);
		}

		objectOutput.writeLong(modifiedDate);

		objectOutput.writeBoolean(recordLockStatus);

		if (cfpDesignation == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(cfpDesignation);
		}

		if (cfpName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(cfpName);
		}

		if (cfpNo == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(cfpNo);
		}

		objectOutput.writeInt(cfpCategory);

		if (source == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(source);
		}

		objectOutput.writeInt(cfpStatus);

		objectOutput.writeInt(parentCfpId);

		objectOutput.writeInt(cfpContractAttachedStatus);
		objectOutput.writeLong(cfpStartDate);
		objectOutput.writeLong(cfpEndDate);

		if (parentCfpName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(parentCfpName);
		}

		if (inboundStatus == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(inboundStatus);
		}

		objectOutput.writeInt(salesInclusion);
	}

	public int createdBy;
	public int cfpContractSid;
	public int cfpType;
	public int cfpTradeClass;
	public int modifiedBy;
	public long createdDate;
	public int contractMasterSid;
	public long cfpContractAttachedDate;
	public int cfpModelSid;
	public String batchId;
	public long modifiedDate;
	public boolean recordLockStatus;
	public String cfpDesignation;
	public String cfpName;
	public String cfpNo;
	public int cfpCategory;
	public String source;
	public int cfpStatus;
	public int parentCfpId;
	public int cfpContractAttachedStatus;
	public long cfpStartDate;
	public long cfpEndDate;
	public String parentCfpName;
	public String inboundStatus;
	public int salesInclusion;
}