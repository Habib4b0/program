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

import com.stpl.app.model.CpiIndexMaster;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing CpiIndexMaster in entity cache.
 *
 * @author
 * @see CpiIndexMaster
 * @generated
 */
@ProviderType
public class CpiIndexMasterCacheModel implements CacheModel<CpiIndexMaster>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CpiIndexMasterCacheModel)) {
			return false;
		}

		CpiIndexMasterCacheModel cpiIndexMasterCacheModel = (CpiIndexMasterCacheModel)obj;

		if (cpiIndexMasterSid == cpiIndexMasterCacheModel.cpiIndexMasterSid) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, cpiIndexMasterSid);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(29);

		sb.append("{effectiveDate=");
		sb.append(effectiveDate);
		sb.append(", createdBy=");
		sb.append(createdBy);
		sb.append(", modifiedBy=");
		sb.append(modifiedBy);
		sb.append(", createdDate=");
		sb.append(createdDate);
		sb.append(", cpiIndexMasterSid=");
		sb.append(cpiIndexMasterSid);
		sb.append(", batchId=");
		sb.append(batchId);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", status=");
		sb.append(status);
		sb.append(", indexType=");
		sb.append(indexType);
		sb.append(", indexId=");
		sb.append(indexId);
		sb.append(", recordLockStatus=");
		sb.append(recordLockStatus);
		sb.append(", indexValue=");
		sb.append(indexValue);
		sb.append(", source=");
		sb.append(source);
		sb.append(", inboundStatus=");
		sb.append(inboundStatus);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public CpiIndexMaster toEntityModel() {
		CpiIndexMasterImpl cpiIndexMasterImpl = new CpiIndexMasterImpl();

		if (effectiveDate == Long.MIN_VALUE) {
			cpiIndexMasterImpl.setEffectiveDate(null);
		}
		else {
			cpiIndexMasterImpl.setEffectiveDate(new Date(effectiveDate));
		}

		cpiIndexMasterImpl.setCreatedBy(createdBy);
		cpiIndexMasterImpl.setModifiedBy(modifiedBy);

		if (createdDate == Long.MIN_VALUE) {
			cpiIndexMasterImpl.setCreatedDate(null);
		}
		else {
			cpiIndexMasterImpl.setCreatedDate(new Date(createdDate));
		}

		cpiIndexMasterImpl.setCpiIndexMasterSid(cpiIndexMasterSid);

		if (batchId == null) {
			cpiIndexMasterImpl.setBatchId(StringPool.BLANK);
		}
		else {
			cpiIndexMasterImpl.setBatchId(batchId);
		}

		if (modifiedDate == Long.MIN_VALUE) {
			cpiIndexMasterImpl.setModifiedDate(null);
		}
		else {
			cpiIndexMasterImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (status == null) {
			cpiIndexMasterImpl.setStatus(StringPool.BLANK);
		}
		else {
			cpiIndexMasterImpl.setStatus(status);
		}

		if (indexType == null) {
			cpiIndexMasterImpl.setIndexType(StringPool.BLANK);
		}
		else {
			cpiIndexMasterImpl.setIndexType(indexType);
		}

		if (indexId == null) {
			cpiIndexMasterImpl.setIndexId(StringPool.BLANK);
		}
		else {
			cpiIndexMasterImpl.setIndexId(indexId);
		}

		cpiIndexMasterImpl.setRecordLockStatus(recordLockStatus);

		if (indexValue == null) {
			cpiIndexMasterImpl.setIndexValue(StringPool.BLANK);
		}
		else {
			cpiIndexMasterImpl.setIndexValue(indexValue);
		}

		if (source == null) {
			cpiIndexMasterImpl.setSource(StringPool.BLANK);
		}
		else {
			cpiIndexMasterImpl.setSource(source);
		}

		if (inboundStatus == null) {
			cpiIndexMasterImpl.setInboundStatus(StringPool.BLANK);
		}
		else {
			cpiIndexMasterImpl.setInboundStatus(inboundStatus);
		}

		cpiIndexMasterImpl.resetOriginalValues();

		return cpiIndexMasterImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		effectiveDate = objectInput.readLong();

		createdBy = objectInput.readInt();

		modifiedBy = objectInput.readInt();
		createdDate = objectInput.readLong();

		cpiIndexMasterSid = objectInput.readInt();
		batchId = objectInput.readUTF();
		modifiedDate = objectInput.readLong();
		status = objectInput.readUTF();
		indexType = objectInput.readUTF();
		indexId = objectInput.readUTF();

		recordLockStatus = objectInput.readBoolean();
		indexValue = objectInput.readUTF();
		source = objectInput.readUTF();
		inboundStatus = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(effectiveDate);

		objectOutput.writeInt(createdBy);

		objectOutput.writeInt(modifiedBy);
		objectOutput.writeLong(createdDate);

		objectOutput.writeInt(cpiIndexMasterSid);

		if (batchId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(batchId);
		}

		objectOutput.writeLong(modifiedDate);

		if (status == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(status);
		}

		if (indexType == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(indexType);
		}

		if (indexId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(indexId);
		}

		objectOutput.writeBoolean(recordLockStatus);

		if (indexValue == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(indexValue);
		}

		if (source == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(source);
		}

		if (inboundStatus == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(inboundStatus);
		}
	}

	public long effectiveDate;
	public int createdBy;
	public int modifiedBy;
	public long createdDate;
	public int cpiIndexMasterSid;
	public String batchId;
	public long modifiedDate;
	public String status;
	public String indexType;
	public String indexId;
	public boolean recordLockStatus;
	public String indexValue;
	public String source;
	public String inboundStatus;
}