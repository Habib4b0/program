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

import com.stpl.app.model.AverageShelfLifeMaster;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing AverageShelfLifeMaster in entity cache.
 *
 * @author
 * @see AverageShelfLifeMaster
 * @generated
 */
@ProviderType
public class AverageShelfLifeMasterCacheModel implements CacheModel<AverageShelfLifeMaster>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AverageShelfLifeMasterCacheModel)) {
			return false;
		}

		AverageShelfLifeMasterCacheModel averageShelfLifeMasterCacheModel = (AverageShelfLifeMasterCacheModel)obj;

		if (averageShelfLifeMasterSid == averageShelfLifeMasterCacheModel.averageShelfLifeMasterSid) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, averageShelfLifeMasterSid);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(25);

		sb.append("{createdBy=");
		sb.append(createdBy);
		sb.append(", averageShelfLifeMasterSid=");
		sb.append(averageShelfLifeMasterSid);
		sb.append(", recordLockStatus=");
		sb.append(recordLockStatus);
		sb.append(", itemIdType=");
		sb.append(itemIdType);
		sb.append(", modifiedBy=");
		sb.append(modifiedBy);
		sb.append(", createdDate=");
		sb.append(createdDate);
		sb.append(", source=");
		sb.append(source);
		sb.append(", itemId=");
		sb.append(itemId);
		sb.append(", avgShelfLife=");
		sb.append(avgShelfLife);
		sb.append(", batchId=");
		sb.append(batchId);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", inboundStatus=");
		sb.append(inboundStatus);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public AverageShelfLifeMaster toEntityModel() {
		AverageShelfLifeMasterImpl averageShelfLifeMasterImpl = new AverageShelfLifeMasterImpl();

		averageShelfLifeMasterImpl.setCreatedBy(createdBy);
		averageShelfLifeMasterImpl.setAverageShelfLifeMasterSid(averageShelfLifeMasterSid);
		averageShelfLifeMasterImpl.setRecordLockStatus(recordLockStatus);

		if (itemIdType == null) {
			averageShelfLifeMasterImpl.setItemIdType(StringPool.BLANK);
		}
		else {
			averageShelfLifeMasterImpl.setItemIdType(itemIdType);
		}

		averageShelfLifeMasterImpl.setModifiedBy(modifiedBy);

		if (createdDate == Long.MIN_VALUE) {
			averageShelfLifeMasterImpl.setCreatedDate(null);
		}
		else {
			averageShelfLifeMasterImpl.setCreatedDate(new Date(createdDate));
		}

		if (source == null) {
			averageShelfLifeMasterImpl.setSource(StringPool.BLANK);
		}
		else {
			averageShelfLifeMasterImpl.setSource(source);
		}

		if (itemId == null) {
			averageShelfLifeMasterImpl.setItemId(StringPool.BLANK);
		}
		else {
			averageShelfLifeMasterImpl.setItemId(itemId);
		}

		if (avgShelfLife == null) {
			averageShelfLifeMasterImpl.setAvgShelfLife(StringPool.BLANK);
		}
		else {
			averageShelfLifeMasterImpl.setAvgShelfLife(avgShelfLife);
		}

		if (batchId == null) {
			averageShelfLifeMasterImpl.setBatchId(StringPool.BLANK);
		}
		else {
			averageShelfLifeMasterImpl.setBatchId(batchId);
		}

		if (modifiedDate == Long.MIN_VALUE) {
			averageShelfLifeMasterImpl.setModifiedDate(null);
		}
		else {
			averageShelfLifeMasterImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (inboundStatus == null) {
			averageShelfLifeMasterImpl.setInboundStatus(StringPool.BLANK);
		}
		else {
			averageShelfLifeMasterImpl.setInboundStatus(inboundStatus);
		}

		averageShelfLifeMasterImpl.resetOriginalValues();

		return averageShelfLifeMasterImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		createdBy = objectInput.readInt();

		averageShelfLifeMasterSid = objectInput.readInt();

		recordLockStatus = objectInput.readBoolean();
		itemIdType = objectInput.readUTF();

		modifiedBy = objectInput.readInt();
		createdDate = objectInput.readLong();
		source = objectInput.readUTF();
		itemId = objectInput.readUTF();
		avgShelfLife = objectInput.readUTF();
		batchId = objectInput.readUTF();
		modifiedDate = objectInput.readLong();
		inboundStatus = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeInt(createdBy);

		objectOutput.writeInt(averageShelfLifeMasterSid);

		objectOutput.writeBoolean(recordLockStatus);

		if (itemIdType == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(itemIdType);
		}

		objectOutput.writeInt(modifiedBy);
		objectOutput.writeLong(createdDate);

		if (source == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(source);
		}

		if (itemId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(itemId);
		}

		if (avgShelfLife == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(avgShelfLife);
		}

		if (batchId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(batchId);
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
	public int averageShelfLifeMasterSid;
	public boolean recordLockStatus;
	public String itemIdType;
	public int modifiedBy;
	public long createdDate;
	public String source;
	public String itemId;
	public String avgShelfLife;
	public String batchId;
	public long modifiedDate;
	public String inboundStatus;
}