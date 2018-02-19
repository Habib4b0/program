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

import com.stpl.app.model.ItemGroupDetails;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing ItemGroupDetails in entity cache.
 *
 * @author
 * @see ItemGroupDetails
 * @generated
 */
@ProviderType
public class ItemGroupDetailsCacheModel implements CacheModel<ItemGroupDetails>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ItemGroupDetailsCacheModel)) {
			return false;
		}

		ItemGroupDetailsCacheModel itemGroupDetailsCacheModel = (ItemGroupDetailsCacheModel)obj;

		if (itemGroupDetailsSid == itemGroupDetailsCacheModel.itemGroupDetailsSid) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, itemGroupDetailsSid);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(17);

		sb.append("{itemGroupDetailsSid=");
		sb.append(itemGroupDetailsSid);
		sb.append(", createdDate=");
		sb.append(createdDate);
		sb.append(", createdBy=");
		sb.append(createdBy);
		sb.append(", itemMasterSid=");
		sb.append(itemMasterSid);
		sb.append(", versionNo=");
		sb.append(versionNo);
		sb.append(", modifiedBy=");
		sb.append(modifiedBy);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", itemGroupSid=");
		sb.append(itemGroupSid);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ItemGroupDetails toEntityModel() {
		ItemGroupDetailsImpl itemGroupDetailsImpl = new ItemGroupDetailsImpl();

		itemGroupDetailsImpl.setItemGroupDetailsSid(itemGroupDetailsSid);

		if (createdDate == Long.MIN_VALUE) {
			itemGroupDetailsImpl.setCreatedDate(null);
		}
		else {
			itemGroupDetailsImpl.setCreatedDate(new Date(createdDate));
		}

		itemGroupDetailsImpl.setCreatedBy(createdBy);
		itemGroupDetailsImpl.setItemMasterSid(itemMasterSid);
		itemGroupDetailsImpl.setVersionNo(versionNo);
		itemGroupDetailsImpl.setModifiedBy(modifiedBy);

		if (modifiedDate == Long.MIN_VALUE) {
			itemGroupDetailsImpl.setModifiedDate(null);
		}
		else {
			itemGroupDetailsImpl.setModifiedDate(new Date(modifiedDate));
		}

		itemGroupDetailsImpl.setItemGroupSid(itemGroupSid);

		itemGroupDetailsImpl.resetOriginalValues();

		return itemGroupDetailsImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		itemGroupDetailsSid = objectInput.readInt();
		createdDate = objectInput.readLong();

		createdBy = objectInput.readInt();

		itemMasterSid = objectInput.readInt();

		versionNo = objectInput.readInt();

		modifiedBy = objectInput.readInt();
		modifiedDate = objectInput.readLong();

		itemGroupSid = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeInt(itemGroupDetailsSid);
		objectOutput.writeLong(createdDate);

		objectOutput.writeInt(createdBy);

		objectOutput.writeInt(itemMasterSid);

		objectOutput.writeInt(versionNo);

		objectOutput.writeInt(modifiedBy);
		objectOutput.writeLong(modifiedDate);

		objectOutput.writeInt(itemGroupSid);
	}

	public int itemGroupDetailsSid;
	public long createdDate;
	public int createdBy;
	public int itemMasterSid;
	public int versionNo;
	public int modifiedBy;
	public long modifiedDate;
	public int itemGroupSid;
}