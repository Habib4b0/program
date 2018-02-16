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

import com.stpl.app.model.ItemQualifier;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing ItemQualifier in entity cache.
 *
 * @author
 * @see ItemQualifier
 * @generated
 */
@ProviderType
public class ItemQualifierCacheModel implements CacheModel<ItemQualifier>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ItemQualifierCacheModel)) {
			return false;
		}

		ItemQualifierCacheModel itemQualifierCacheModel = (ItemQualifierCacheModel)obj;

		if (itemQualifierSid == itemQualifierCacheModel.itemQualifierSid) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, itemQualifierSid);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(29);

		sb.append("{createdBy=");
		sb.append(createdBy);
		sb.append(", itemQualifierSid=");
		sb.append(itemQualifierSid);
		sb.append(", specificEntityCode=");
		sb.append(specificEntityCode);
		sb.append(", itemQualifierName=");
		sb.append(itemQualifierName);
		sb.append(", modifiedBy=");
		sb.append(modifiedBy);
		sb.append(", createdDate=");
		sb.append(createdDate);
		sb.append(", batchId=");
		sb.append(batchId);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", effectiveDates=");
		sb.append(effectiveDates);
		sb.append(", notes=");
		sb.append(notes);
		sb.append(", itemQualifierValue=");
		sb.append(itemQualifierValue);
		sb.append(", recordLockStatus=");
		sb.append(recordLockStatus);
		sb.append(", source=");
		sb.append(source);
		sb.append(", inboundStatus=");
		sb.append(inboundStatus);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ItemQualifier toEntityModel() {
		ItemQualifierImpl itemQualifierImpl = new ItemQualifierImpl();

		itemQualifierImpl.setCreatedBy(createdBy);
		itemQualifierImpl.setItemQualifierSid(itemQualifierSid);

		if (specificEntityCode == null) {
			itemQualifierImpl.setSpecificEntityCode(StringPool.BLANK);
		}
		else {
			itemQualifierImpl.setSpecificEntityCode(specificEntityCode);
		}

		if (itemQualifierName == null) {
			itemQualifierImpl.setItemQualifierName(StringPool.BLANK);
		}
		else {
			itemQualifierImpl.setItemQualifierName(itemQualifierName);
		}

		itemQualifierImpl.setModifiedBy(modifiedBy);

		if (createdDate == Long.MIN_VALUE) {
			itemQualifierImpl.setCreatedDate(null);
		}
		else {
			itemQualifierImpl.setCreatedDate(new Date(createdDate));
		}

		if (batchId == null) {
			itemQualifierImpl.setBatchId(StringPool.BLANK);
		}
		else {
			itemQualifierImpl.setBatchId(batchId);
		}

		if (modifiedDate == Long.MIN_VALUE) {
			itemQualifierImpl.setModifiedDate(null);
		}
		else {
			itemQualifierImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (effectiveDates == null) {
			itemQualifierImpl.setEffectiveDates(StringPool.BLANK);
		}
		else {
			itemQualifierImpl.setEffectiveDates(effectiveDates);
		}

		if (notes == null) {
			itemQualifierImpl.setNotes(StringPool.BLANK);
		}
		else {
			itemQualifierImpl.setNotes(notes);
		}

		if (itemQualifierValue == null) {
			itemQualifierImpl.setItemQualifierValue(StringPool.BLANK);
		}
		else {
			itemQualifierImpl.setItemQualifierValue(itemQualifierValue);
		}

		itemQualifierImpl.setRecordLockStatus(recordLockStatus);

		if (source == null) {
			itemQualifierImpl.setSource(StringPool.BLANK);
		}
		else {
			itemQualifierImpl.setSource(source);
		}

		if (inboundStatus == null) {
			itemQualifierImpl.setInboundStatus(StringPool.BLANK);
		}
		else {
			itemQualifierImpl.setInboundStatus(inboundStatus);
		}

		itemQualifierImpl.resetOriginalValues();

		return itemQualifierImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		createdBy = objectInput.readInt();

		itemQualifierSid = objectInput.readInt();
		specificEntityCode = objectInput.readUTF();
		itemQualifierName = objectInput.readUTF();

		modifiedBy = objectInput.readInt();
		createdDate = objectInput.readLong();
		batchId = objectInput.readUTF();
		modifiedDate = objectInput.readLong();
		effectiveDates = objectInput.readUTF();
		notes = objectInput.readUTF();
		itemQualifierValue = objectInput.readUTF();

		recordLockStatus = objectInput.readBoolean();
		source = objectInput.readUTF();
		inboundStatus = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeInt(createdBy);

		objectOutput.writeInt(itemQualifierSid);

		if (specificEntityCode == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(specificEntityCode);
		}

		if (itemQualifierName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(itemQualifierName);
		}

		objectOutput.writeInt(modifiedBy);
		objectOutput.writeLong(createdDate);

		if (batchId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(batchId);
		}

		objectOutput.writeLong(modifiedDate);

		if (effectiveDates == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(effectiveDates);
		}

		if (notes == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(notes);
		}

		if (itemQualifierValue == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(itemQualifierValue);
		}

		objectOutput.writeBoolean(recordLockStatus);

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

	public int createdBy;
	public int itemQualifierSid;
	public String specificEntityCode;
	public String itemQualifierName;
	public int modifiedBy;
	public long createdDate;
	public String batchId;
	public long modifiedDate;
	public String effectiveDates;
	public String notes;
	public String itemQualifierValue;
	public boolean recordLockStatus;
	public String source;
	public String inboundStatus;
}