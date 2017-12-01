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

import com.stpl.app.model.ItemPricingQualifier;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing ItemPricingQualifier in entity cache.
 *
 * @author
 * @see ItemPricingQualifier
 * @generated
 */
@ProviderType
public class ItemPricingQualifierCacheModel implements CacheModel<ItemPricingQualifier>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ItemPricingQualifierCacheModel)) {
			return false;
		}

		ItemPricingQualifierCacheModel itemPricingQualifierCacheModel = (ItemPricingQualifierCacheModel)obj;

		if (itemPricingQualifierSid == itemPricingQualifierCacheModel.itemPricingQualifierSid) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, itemPricingQualifierSid);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(29);

		sb.append("{createdBy=");
		sb.append(createdBy);
		sb.append(", itemPricingQualifierSid=");
		sb.append(itemPricingQualifierSid);
		sb.append(", specificEntityCode=");
		sb.append(specificEntityCode);
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
		sb.append(", recordLockStatus=");
		sb.append(recordLockStatus);
		sb.append(", source=");
		sb.append(source);
		sb.append(", pricingQualifier=");
		sb.append(pricingQualifier);
		sb.append(", itemPricingQualifierName=");
		sb.append(itemPricingQualifierName);
		sb.append(", inboundStatus=");
		sb.append(inboundStatus);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ItemPricingQualifier toEntityModel() {
		ItemPricingQualifierImpl itemPricingQualifierImpl = new ItemPricingQualifierImpl();

		itemPricingQualifierImpl.setCreatedBy(createdBy);
		itemPricingQualifierImpl.setItemPricingQualifierSid(itemPricingQualifierSid);

		if (specificEntityCode == null) {
			itemPricingQualifierImpl.setSpecificEntityCode(StringPool.BLANK);
		}
		else {
			itemPricingQualifierImpl.setSpecificEntityCode(specificEntityCode);
		}

		itemPricingQualifierImpl.setModifiedBy(modifiedBy);

		if (createdDate == Long.MIN_VALUE) {
			itemPricingQualifierImpl.setCreatedDate(null);
		}
		else {
			itemPricingQualifierImpl.setCreatedDate(new Date(createdDate));
		}

		if (batchId == null) {
			itemPricingQualifierImpl.setBatchId(StringPool.BLANK);
		}
		else {
			itemPricingQualifierImpl.setBatchId(batchId);
		}

		if (modifiedDate == Long.MIN_VALUE) {
			itemPricingQualifierImpl.setModifiedDate(null);
		}
		else {
			itemPricingQualifierImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (effectiveDates == null) {
			itemPricingQualifierImpl.setEffectiveDates(StringPool.BLANK);
		}
		else {
			itemPricingQualifierImpl.setEffectiveDates(effectiveDates);
		}

		if (notes == null) {
			itemPricingQualifierImpl.setNotes(StringPool.BLANK);
		}
		else {
			itemPricingQualifierImpl.setNotes(notes);
		}

		itemPricingQualifierImpl.setRecordLockStatus(recordLockStatus);

		if (source == null) {
			itemPricingQualifierImpl.setSource(StringPool.BLANK);
		}
		else {
			itemPricingQualifierImpl.setSource(source);
		}

		if (pricingQualifier == null) {
			itemPricingQualifierImpl.setPricingQualifier(StringPool.BLANK);
		}
		else {
			itemPricingQualifierImpl.setPricingQualifier(pricingQualifier);
		}

		if (itemPricingQualifierName == null) {
			itemPricingQualifierImpl.setItemPricingQualifierName(StringPool.BLANK);
		}
		else {
			itemPricingQualifierImpl.setItemPricingQualifierName(itemPricingQualifierName);
		}

		if (inboundStatus == null) {
			itemPricingQualifierImpl.setInboundStatus(StringPool.BLANK);
		}
		else {
			itemPricingQualifierImpl.setInboundStatus(inboundStatus);
		}

		itemPricingQualifierImpl.resetOriginalValues();

		return itemPricingQualifierImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		createdBy = objectInput.readInt();

		itemPricingQualifierSid = objectInput.readInt();
		specificEntityCode = objectInput.readUTF();

		modifiedBy = objectInput.readInt();
		createdDate = objectInput.readLong();
		batchId = objectInput.readUTF();
		modifiedDate = objectInput.readLong();
		effectiveDates = objectInput.readUTF();
		notes = objectInput.readUTF();

		recordLockStatus = objectInput.readBoolean();
		source = objectInput.readUTF();
		pricingQualifier = objectInput.readUTF();
		itemPricingQualifierName = objectInput.readUTF();
		inboundStatus = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeInt(createdBy);

		objectOutput.writeInt(itemPricingQualifierSid);

		if (specificEntityCode == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(specificEntityCode);
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

		objectOutput.writeBoolean(recordLockStatus);

		if (source == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(source);
		}

		if (pricingQualifier == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(pricingQualifier);
		}

		if (itemPricingQualifierName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(itemPricingQualifierName);
		}

		if (inboundStatus == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(inboundStatus);
		}
	}

	public int createdBy;
	public int itemPricingQualifierSid;
	public String specificEntityCode;
	public int modifiedBy;
	public long createdDate;
	public String batchId;
	public long modifiedDate;
	public String effectiveDates;
	public String notes;
	public boolean recordLockStatus;
	public String source;
	public String pricingQualifier;
	public String itemPricingQualifierName;
	public String inboundStatus;
}