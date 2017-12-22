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

package com.stpl.app.parttwo.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import com.stpl.app.parttwo.model.CffViewMaster;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing CffViewMaster in entity cache.
 *
 * @author
 * @see CffViewMaster
 * @generated
 */
@ProviderType
public class CffViewMasterCacheModel implements CacheModel<CffViewMaster>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CffViewMasterCacheModel)) {
			return false;
		}

		CffViewMasterCacheModel cffViewMasterCacheModel = (CffViewMasterCacheModel)obj;

		if (cffViewMasterSid == cffViewMasterCacheModel.cffViewMasterSid) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, cffViewMasterSid);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(17);

		sb.append("{createdDate=");
		sb.append(createdDate);
		sb.append(", createdBy=");
		sb.append(createdBy);
		sb.append(", viewType=");
		sb.append(viewType);
		sb.append(", cffViewMasterSid=");
		sb.append(cffViewMasterSid);
		sb.append(", cffMasterSid=");
		sb.append(cffMasterSid);
		sb.append(", modifiedBy=");
		sb.append(modifiedBy);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", viewName=");
		sb.append(viewName);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public CffViewMaster toEntityModel() {
		CffViewMasterImpl cffViewMasterImpl = new CffViewMasterImpl();

		if (createdDate == Long.MIN_VALUE) {
			cffViewMasterImpl.setCreatedDate(null);
		}
		else {
			cffViewMasterImpl.setCreatedDate(new Date(createdDate));
		}

		if (createdBy == null) {
			cffViewMasterImpl.setCreatedBy(StringPool.BLANK);
		}
		else {
			cffViewMasterImpl.setCreatedBy(createdBy);
		}

		if (viewType == null) {
			cffViewMasterImpl.setViewType(StringPool.BLANK);
		}
		else {
			cffViewMasterImpl.setViewType(viewType);
		}

		cffViewMasterImpl.setCffViewMasterSid(cffViewMasterSid);
		cffViewMasterImpl.setCffMasterSid(cffMasterSid);

		if (modifiedBy == null) {
			cffViewMasterImpl.setModifiedBy(StringPool.BLANK);
		}
		else {
			cffViewMasterImpl.setModifiedBy(modifiedBy);
		}

		if (modifiedDate == Long.MIN_VALUE) {
			cffViewMasterImpl.setModifiedDate(null);
		}
		else {
			cffViewMasterImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (viewName == null) {
			cffViewMasterImpl.setViewName(StringPool.BLANK);
		}
		else {
			cffViewMasterImpl.setViewName(viewName);
		}

		cffViewMasterImpl.resetOriginalValues();

		return cffViewMasterImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		createdDate = objectInput.readLong();
		createdBy = objectInput.readUTF();
		viewType = objectInput.readUTF();

		cffViewMasterSid = objectInput.readInt();

		cffMasterSid = objectInput.readInt();
		modifiedBy = objectInput.readUTF();
		modifiedDate = objectInput.readLong();
		viewName = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(createdDate);

		if (createdBy == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(createdBy);
		}

		if (viewType == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(viewType);
		}

		objectOutput.writeInt(cffViewMasterSid);

		objectOutput.writeInt(cffMasterSid);

		if (modifiedBy == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(modifiedBy);
		}

		objectOutput.writeLong(modifiedDate);

		if (viewName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(viewName);
		}
	}

	public long createdDate;
	public String createdBy;
	public String viewType;
	public int cffViewMasterSid;
	public int cffMasterSid;
	public String modifiedBy;
	public long modifiedDate;
	public String viewName;
}