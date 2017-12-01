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

import com.stpl.app.model.ItemHierarchyDefMaster;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing ItemHierarchyDefMaster in entity cache.
 *
 * @author
 * @see ItemHierarchyDefMaster
 * @generated
 */
@ProviderType
public class ItemHierarchyDefMasterCacheModel implements CacheModel<ItemHierarchyDefMaster>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ItemHierarchyDefMasterCacheModel)) {
			return false;
		}

		ItemHierarchyDefMasterCacheModel itemHierarchyDefMasterCacheModel = (ItemHierarchyDefMasterCacheModel)obj;

		if (itemHierarchyDefMasterSid == itemHierarchyDefMasterCacheModel.itemHierarchyDefMasterSid) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, itemHierarchyDefMasterSid);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(25);

		sb.append("{itemHierarchyDefMasterSid=");
		sb.append(itemHierarchyDefMasterSid);
		sb.append(", createdBy=");
		sb.append(createdBy);
		sb.append(", recordLockStatus=");
		sb.append(recordLockStatus);
		sb.append(", modifiedBy=");
		sb.append(modifiedBy);
		sb.append(", createdDate=");
		sb.append(createdDate);
		sb.append(", alias=");
		sb.append(alias);
		sb.append(", source=");
		sb.append(source);
		sb.append(", batchId=");
		sb.append(batchId);
		sb.append(", bpiLvl=");
		sb.append(bpiLvl);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", member=");
		sb.append(member);
		sb.append(", inboundStatus=");
		sb.append(inboundStatus);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ItemHierarchyDefMaster toEntityModel() {
		ItemHierarchyDefMasterImpl itemHierarchyDefMasterImpl = new ItemHierarchyDefMasterImpl();

		itemHierarchyDefMasterImpl.setItemHierarchyDefMasterSid(itemHierarchyDefMasterSid);
		itemHierarchyDefMasterImpl.setCreatedBy(createdBy);
		itemHierarchyDefMasterImpl.setRecordLockStatus(recordLockStatus);
		itemHierarchyDefMasterImpl.setModifiedBy(modifiedBy);

		if (createdDate == Long.MIN_VALUE) {
			itemHierarchyDefMasterImpl.setCreatedDate(null);
		}
		else {
			itemHierarchyDefMasterImpl.setCreatedDate(new Date(createdDate));
		}

		if (alias == null) {
			itemHierarchyDefMasterImpl.setAlias(StringPool.BLANK);
		}
		else {
			itemHierarchyDefMasterImpl.setAlias(alias);
		}

		if (source == null) {
			itemHierarchyDefMasterImpl.setSource(StringPool.BLANK);
		}
		else {
			itemHierarchyDefMasterImpl.setSource(source);
		}

		if (batchId == null) {
			itemHierarchyDefMasterImpl.setBatchId(StringPool.BLANK);
		}
		else {
			itemHierarchyDefMasterImpl.setBatchId(batchId);
		}

		if (bpiLvl == null) {
			itemHierarchyDefMasterImpl.setBpiLvl(StringPool.BLANK);
		}
		else {
			itemHierarchyDefMasterImpl.setBpiLvl(bpiLvl);
		}

		if (modifiedDate == Long.MIN_VALUE) {
			itemHierarchyDefMasterImpl.setModifiedDate(null);
		}
		else {
			itemHierarchyDefMasterImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (member == null) {
			itemHierarchyDefMasterImpl.setMember(StringPool.BLANK);
		}
		else {
			itemHierarchyDefMasterImpl.setMember(member);
		}

		if (inboundStatus == null) {
			itemHierarchyDefMasterImpl.setInboundStatus(StringPool.BLANK);
		}
		else {
			itemHierarchyDefMasterImpl.setInboundStatus(inboundStatus);
		}

		itemHierarchyDefMasterImpl.resetOriginalValues();

		return itemHierarchyDefMasterImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		itemHierarchyDefMasterSid = objectInput.readInt();

		createdBy = objectInput.readInt();

		recordLockStatus = objectInput.readBoolean();

		modifiedBy = objectInput.readInt();
		createdDate = objectInput.readLong();
		alias = objectInput.readUTF();
		source = objectInput.readUTF();
		batchId = objectInput.readUTF();
		bpiLvl = objectInput.readUTF();
		modifiedDate = objectInput.readLong();
		member = objectInput.readUTF();
		inboundStatus = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeInt(itemHierarchyDefMasterSid);

		objectOutput.writeInt(createdBy);

		objectOutput.writeBoolean(recordLockStatus);

		objectOutput.writeInt(modifiedBy);
		objectOutput.writeLong(createdDate);

		if (alias == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(alias);
		}

		if (source == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(source);
		}

		if (batchId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(batchId);
		}

		if (bpiLvl == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(bpiLvl);
		}

		objectOutput.writeLong(modifiedDate);

		if (member == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(member);
		}

		if (inboundStatus == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(inboundStatus);
		}
	}

	public int itemHierarchyDefMasterSid;
	public int createdBy;
	public boolean recordLockStatus;
	public int modifiedBy;
	public long createdDate;
	public String alias;
	public String source;
	public String batchId;
	public String bpiLvl;
	public long modifiedDate;
	public String member;
	public String inboundStatus;
}