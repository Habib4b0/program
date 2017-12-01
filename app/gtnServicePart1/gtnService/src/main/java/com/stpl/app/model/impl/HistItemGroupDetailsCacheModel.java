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

import com.stpl.app.model.HistItemGroupDetails;
import com.stpl.app.service.persistence.HistItemGroupDetailsPK;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing HistItemGroupDetails in entity cache.
 *
 * @author
 * @see HistItemGroupDetails
 * @generated
 */
@ProviderType
public class HistItemGroupDetailsCacheModel implements CacheModel<HistItemGroupDetails>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof HistItemGroupDetailsCacheModel)) {
			return false;
		}

		HistItemGroupDetailsCacheModel histItemGroupDetailsCacheModel = (HistItemGroupDetailsCacheModel)obj;

		if (histItemGroupDetailsPK.equals(
					histItemGroupDetailsCacheModel.histItemGroupDetailsPK)) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, histItemGroupDetailsPK);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(21);

		sb.append("{itemGroupDetailsSid=");
		sb.append(itemGroupDetailsSid);
		sb.append(", createdDate=");
		sb.append(createdDate);
		sb.append(", createdBy=");
		sb.append(createdBy);
		sb.append(", actionDate=");
		sb.append(actionDate);
		sb.append(", itemMasterSid=");
		sb.append(itemMasterSid);
		sb.append(", actionFlag=");
		sb.append(actionFlag);
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
	public HistItemGroupDetails toEntityModel() {
		HistItemGroupDetailsImpl histItemGroupDetailsImpl = new HistItemGroupDetailsImpl();

		histItemGroupDetailsImpl.setItemGroupDetailsSid(itemGroupDetailsSid);

		if (createdDate == Long.MIN_VALUE) {
			histItemGroupDetailsImpl.setCreatedDate(null);
		}
		else {
			histItemGroupDetailsImpl.setCreatedDate(new Date(createdDate));
		}

		histItemGroupDetailsImpl.setCreatedBy(createdBy);

		if (actionDate == Long.MIN_VALUE) {
			histItemGroupDetailsImpl.setActionDate(null);
		}
		else {
			histItemGroupDetailsImpl.setActionDate(new Date(actionDate));
		}

		histItemGroupDetailsImpl.setItemMasterSid(itemMasterSid);

		if (actionFlag == null) {
			histItemGroupDetailsImpl.setActionFlag(StringPool.BLANK);
		}
		else {
			histItemGroupDetailsImpl.setActionFlag(actionFlag);
		}

		histItemGroupDetailsImpl.setVersionNo(versionNo);
		histItemGroupDetailsImpl.setModifiedBy(modifiedBy);

		if (modifiedDate == Long.MIN_VALUE) {
			histItemGroupDetailsImpl.setModifiedDate(null);
		}
		else {
			histItemGroupDetailsImpl.setModifiedDate(new Date(modifiedDate));
		}

		histItemGroupDetailsImpl.setItemGroupSid(itemGroupSid);

		histItemGroupDetailsImpl.resetOriginalValues();

		return histItemGroupDetailsImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		itemGroupDetailsSid = objectInput.readInt();
		createdDate = objectInput.readLong();

		createdBy = objectInput.readInt();
		actionDate = objectInput.readLong();

		itemMasterSid = objectInput.readInt();
		actionFlag = objectInput.readUTF();

		versionNo = objectInput.readInt();

		modifiedBy = objectInput.readInt();
		modifiedDate = objectInput.readLong();

		itemGroupSid = objectInput.readInt();

		histItemGroupDetailsPK = new HistItemGroupDetailsPK(itemGroupDetailsSid,
				actionFlag, versionNo);
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeInt(itemGroupDetailsSid);
		objectOutput.writeLong(createdDate);

		objectOutput.writeInt(createdBy);
		objectOutput.writeLong(actionDate);

		objectOutput.writeInt(itemMasterSid);

		if (actionFlag == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(actionFlag);
		}

		objectOutput.writeInt(versionNo);

		objectOutput.writeInt(modifiedBy);
		objectOutput.writeLong(modifiedDate);

		objectOutput.writeInt(itemGroupSid);
	}

	public int itemGroupDetailsSid;
	public long createdDate;
	public int createdBy;
	public long actionDate;
	public int itemMasterSid;
	public String actionFlag;
	public int versionNo;
	public int modifiedBy;
	public long modifiedDate;
	public int itemGroupSid;
	public transient HistItemGroupDetailsPK histItemGroupDetailsPK;
}