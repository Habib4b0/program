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

import com.stpl.app.model.HistItemGroup;
import com.stpl.app.service.persistence.HistItemGroupPK;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing HistItemGroup in entity cache.
 *
 * @author
 * @see HistItemGroup
 * @generated
 */
@ProviderType
public class HistItemGroupCacheModel implements CacheModel<HistItemGroup>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof HistItemGroupCacheModel)) {
			return false;
		}

		HistItemGroupCacheModel histItemGroupCacheModel = (HistItemGroupCacheModel)obj;

		if (histItemGroupPK.equals(histItemGroupCacheModel.histItemGroupPK)) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, histItemGroupPK);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(25);

		sb.append("{lastModifiedDate=");
		sb.append(lastModifiedDate);
		sb.append(", createdDate=");
		sb.append(createdDate);
		sb.append(", createdBy=");
		sb.append(createdBy);
		sb.append(", actionFlag=");
		sb.append(actionFlag);
		sb.append(", itemGroupNo=");
		sb.append(itemGroupNo);
		sb.append(", versionNo=");
		sb.append(versionNo);
		sb.append(", modifiedBy=");
		sb.append(modifiedBy);
		sb.append(", itemGroupDescription=");
		sb.append(itemGroupDescription);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", itemGroupName=");
		sb.append(itemGroupName);
		sb.append(", itemGroupSid=");
		sb.append(itemGroupSid);
		sb.append(", companyMasterSid=");
		sb.append(companyMasterSid);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public HistItemGroup toEntityModel() {
		HistItemGroupImpl histItemGroupImpl = new HistItemGroupImpl();

		if (lastModifiedDate == Long.MIN_VALUE) {
			histItemGroupImpl.setLastModifiedDate(null);
		}
		else {
			histItemGroupImpl.setLastModifiedDate(new Date(lastModifiedDate));
		}

		if (createdDate == Long.MIN_VALUE) {
			histItemGroupImpl.setCreatedDate(null);
		}
		else {
			histItemGroupImpl.setCreatedDate(new Date(createdDate));
		}

		histItemGroupImpl.setCreatedBy(createdBy);

		if (actionFlag == null) {
			histItemGroupImpl.setActionFlag(StringPool.BLANK);
		}
		else {
			histItemGroupImpl.setActionFlag(actionFlag);
		}

		if (itemGroupNo == null) {
			histItemGroupImpl.setItemGroupNo(StringPool.BLANK);
		}
		else {
			histItemGroupImpl.setItemGroupNo(itemGroupNo);
		}

		histItemGroupImpl.setVersionNo(versionNo);
		histItemGroupImpl.setModifiedBy(modifiedBy);

		if (itemGroupDescription == null) {
			histItemGroupImpl.setItemGroupDescription(StringPool.BLANK);
		}
		else {
			histItemGroupImpl.setItemGroupDescription(itemGroupDescription);
		}

		if (modifiedDate == Long.MIN_VALUE) {
			histItemGroupImpl.setModifiedDate(null);
		}
		else {
			histItemGroupImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (itemGroupName == null) {
			histItemGroupImpl.setItemGroupName(StringPool.BLANK);
		}
		else {
			histItemGroupImpl.setItemGroupName(itemGroupName);
		}

		histItemGroupImpl.setItemGroupSid(itemGroupSid);
		histItemGroupImpl.setCompanyMasterSid(companyMasterSid);

		histItemGroupImpl.resetOriginalValues();

		return histItemGroupImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		lastModifiedDate = objectInput.readLong();
		createdDate = objectInput.readLong();

		createdBy = objectInput.readInt();
		actionFlag = objectInput.readUTF();
		itemGroupNo = objectInput.readUTF();

		versionNo = objectInput.readInt();

		modifiedBy = objectInput.readInt();
		itemGroupDescription = objectInput.readUTF();
		modifiedDate = objectInput.readLong();
		itemGroupName = objectInput.readUTF();

		itemGroupSid = objectInput.readInt();

		companyMasterSid = objectInput.readInt();

		histItemGroupPK = new HistItemGroupPK(actionFlag, versionNo,
				itemGroupSid);
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(lastModifiedDate);
		objectOutput.writeLong(createdDate);

		objectOutput.writeInt(createdBy);

		if (actionFlag == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(actionFlag);
		}

		if (itemGroupNo == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(itemGroupNo);
		}

		objectOutput.writeInt(versionNo);

		objectOutput.writeInt(modifiedBy);

		if (itemGroupDescription == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(itemGroupDescription);
		}

		objectOutput.writeLong(modifiedDate);

		if (itemGroupName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(itemGroupName);
		}

		objectOutput.writeInt(itemGroupSid);

		objectOutput.writeInt(companyMasterSid);
	}

	public long lastModifiedDate;
	public long createdDate;
	public int createdBy;
	public String actionFlag;
	public String itemGroupNo;
	public int versionNo;
	public int modifiedBy;
	public String itemGroupDescription;
	public long modifiedDate;
	public String itemGroupName;
	public int itemGroupSid;
	public int companyMasterSid;
	public transient HistItemGroupPK histItemGroupPK;
}