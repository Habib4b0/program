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

import com.stpl.app.model.DeductionGroup;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing DeductionGroup in entity cache.
 *
 * @author
 * @see DeductionGroup
 * @generated
 */
@ProviderType
public class DeductionGroupCacheModel implements CacheModel<DeductionGroup>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof DeductionGroupCacheModel)) {
			return false;
		}

		DeductionGroupCacheModel deductionGroupCacheModel = (DeductionGroupCacheModel)obj;

		if (deductionGroupSid == deductionGroupCacheModel.deductionGroupSid) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, deductionGroupSid);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(21);

		sb.append("{deductionFilter=");
		sb.append(deductionFilter);
		sb.append(", createdDate=");
		sb.append(createdDate);
		sb.append(", createdBy=");
		sb.append(createdBy);
		sb.append(", deductionGroupSid=");
		sb.append(deductionGroupSid);
		sb.append(", deductionGroupName=");
		sb.append(deductionGroupName);
		sb.append(", versionNo=");
		sb.append(versionNo);
		sb.append(", deductionGroupDescription=");
		sb.append(deductionGroupDescription);
		sb.append(", modifiedBy=");
		sb.append(modifiedBy);
		sb.append(", deductionGroupNo=");
		sb.append(deductionGroupNo);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public DeductionGroup toEntityModel() {
		DeductionGroupImpl deductionGroupImpl = new DeductionGroupImpl();

		if (deductionFilter == null) {
			deductionGroupImpl.setDeductionFilter(StringPool.BLANK);
		}
		else {
			deductionGroupImpl.setDeductionFilter(deductionFilter);
		}

		if (createdDate == Long.MIN_VALUE) {
			deductionGroupImpl.setCreatedDate(null);
		}
		else {
			deductionGroupImpl.setCreatedDate(new Date(createdDate));
		}

		deductionGroupImpl.setCreatedBy(createdBy);
		deductionGroupImpl.setDeductionGroupSid(deductionGroupSid);

		if (deductionGroupName == null) {
			deductionGroupImpl.setDeductionGroupName(StringPool.BLANK);
		}
		else {
			deductionGroupImpl.setDeductionGroupName(deductionGroupName);
		}

		deductionGroupImpl.setVersionNo(versionNo);

		if (deductionGroupDescription == null) {
			deductionGroupImpl.setDeductionGroupDescription(StringPool.BLANK);
		}
		else {
			deductionGroupImpl.setDeductionGroupDescription(deductionGroupDescription);
		}

		deductionGroupImpl.setModifiedBy(modifiedBy);

		if (deductionGroupNo == null) {
			deductionGroupImpl.setDeductionGroupNo(StringPool.BLANK);
		}
		else {
			deductionGroupImpl.setDeductionGroupNo(deductionGroupNo);
		}

		if (modifiedDate == Long.MIN_VALUE) {
			deductionGroupImpl.setModifiedDate(null);
		}
		else {
			deductionGroupImpl.setModifiedDate(new Date(modifiedDate));
		}

		deductionGroupImpl.resetOriginalValues();

		return deductionGroupImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		deductionFilter = objectInput.readUTF();
		createdDate = objectInput.readLong();

		createdBy = objectInput.readInt();

		deductionGroupSid = objectInput.readInt();
		deductionGroupName = objectInput.readUTF();

		versionNo = objectInput.readInt();
		deductionGroupDescription = objectInput.readUTF();

		modifiedBy = objectInput.readInt();
		deductionGroupNo = objectInput.readUTF();
		modifiedDate = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		if (deductionFilter == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(deductionFilter);
		}

		objectOutput.writeLong(createdDate);

		objectOutput.writeInt(createdBy);

		objectOutput.writeInt(deductionGroupSid);

		if (deductionGroupName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(deductionGroupName);
		}

		objectOutput.writeInt(versionNo);

		if (deductionGroupDescription == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(deductionGroupDescription);
		}

		objectOutput.writeInt(modifiedBy);

		if (deductionGroupNo == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(deductionGroupNo);
		}

		objectOutput.writeLong(modifiedDate);
	}

	public String deductionFilter;
	public long createdDate;
	public int createdBy;
	public int deductionGroupSid;
	public String deductionGroupName;
	public int versionNo;
	public String deductionGroupDescription;
	public int modifiedBy;
	public String deductionGroupNo;
	public long modifiedDate;
}