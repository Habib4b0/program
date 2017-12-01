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

import com.stpl.app.model.HelperTable;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing HelperTable in entity cache.
 *
 * @author
 * @see HelperTable
 * @generated
 */
@ProviderType
public class HelperTableCacheModel implements CacheModel<HelperTable>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof HelperTableCacheModel)) {
			return false;
		}

		HelperTableCacheModel helperTableCacheModel = (HelperTableCacheModel)obj;

		if (helperTableSid == helperTableCacheModel.helperTableSid) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, helperTableSid);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(19);

		sb.append("{createdDate=");
		sb.append(createdDate);
		sb.append(", createdBy=");
		sb.append(createdBy);
		sb.append(", description=");
		sb.append(description);
		sb.append(", refCount=");
		sb.append(refCount);
		sb.append(", listName=");
		sb.append(listName);
		sb.append(", helperTableSid=");
		sb.append(helperTableSid);
		sb.append(", modifiedBy=");
		sb.append(modifiedBy);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", aliasName=");
		sb.append(aliasName);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public HelperTable toEntityModel() {
		HelperTableImpl helperTableImpl = new HelperTableImpl();

		if (createdDate == Long.MIN_VALUE) {
			helperTableImpl.setCreatedDate(null);
		}
		else {
			helperTableImpl.setCreatedDate(new Date(createdDate));
		}

		helperTableImpl.setCreatedBy(createdBy);

		if (description == null) {
			helperTableImpl.setDescription(StringPool.BLANK);
		}
		else {
			helperTableImpl.setDescription(description);
		}

		helperTableImpl.setRefCount(refCount);

		if (listName == null) {
			helperTableImpl.setListName(StringPool.BLANK);
		}
		else {
			helperTableImpl.setListName(listName);
		}

		helperTableImpl.setHelperTableSid(helperTableSid);
		helperTableImpl.setModifiedBy(modifiedBy);

		if (modifiedDate == Long.MIN_VALUE) {
			helperTableImpl.setModifiedDate(null);
		}
		else {
			helperTableImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (aliasName == null) {
			helperTableImpl.setAliasName(StringPool.BLANK);
		}
		else {
			helperTableImpl.setAliasName(aliasName);
		}

		helperTableImpl.resetOriginalValues();

		return helperTableImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		createdDate = objectInput.readLong();

		createdBy = objectInput.readInt();
		description = objectInput.readUTF();

		refCount = objectInput.readInt();
		listName = objectInput.readUTF();

		helperTableSid = objectInput.readInt();

		modifiedBy = objectInput.readInt();
		modifiedDate = objectInput.readLong();
		aliasName = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(createdDate);

		objectOutput.writeInt(createdBy);

		if (description == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(description);
		}

		objectOutput.writeInt(refCount);

		if (listName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(listName);
		}

		objectOutput.writeInt(helperTableSid);

		objectOutput.writeInt(modifiedBy);
		objectOutput.writeLong(modifiedDate);

		if (aliasName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(aliasName);
		}
	}

	public long createdDate;
	public int createdBy;
	public String description;
	public int refCount;
	public String listName;
	public int helperTableSid;
	public int modifiedBy;
	public long modifiedDate;
	public String aliasName;
}