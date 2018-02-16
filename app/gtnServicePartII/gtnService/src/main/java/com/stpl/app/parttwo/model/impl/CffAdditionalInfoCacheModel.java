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

import com.stpl.app.parttwo.model.CffAdditionalInfo;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing CffAdditionalInfo in entity cache.
 *
 * @author
 * @see CffAdditionalInfo
 * @generated
 */
@ProviderType
public class CffAdditionalInfoCacheModel implements CacheModel<CffAdditionalInfo>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CffAdditionalInfoCacheModel)) {
			return false;
		}

		CffAdditionalInfoCacheModel cffAdditionalInfoCacheModel = (CffAdditionalInfoCacheModel)obj;

		if (cffAdditionalInfoSid == cffAdditionalInfoCacheModel.cffAdditionalInfoSid) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, cffAdditionalInfoSid);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(11);

		sb.append("{createdDate=");
		sb.append(createdDate);
		sb.append(", createdBy=");
		sb.append(createdBy);
		sb.append(", cffMasterSid=");
		sb.append(cffMasterSid);
		sb.append(", cffAdditionalInfoSid=");
		sb.append(cffAdditionalInfoSid);
		sb.append(", notes=");
		sb.append(notes);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public CffAdditionalInfo toEntityModel() {
		CffAdditionalInfoImpl cffAdditionalInfoImpl = new CffAdditionalInfoImpl();

		if (createdDate == Long.MIN_VALUE) {
			cffAdditionalInfoImpl.setCreatedDate(null);
		}
		else {
			cffAdditionalInfoImpl.setCreatedDate(new Date(createdDate));
		}

		cffAdditionalInfoImpl.setCreatedBy(createdBy);
		cffAdditionalInfoImpl.setCffMasterSid(cffMasterSid);
		cffAdditionalInfoImpl.setCffAdditionalInfoSid(cffAdditionalInfoSid);

		if (notes == null) {
			cffAdditionalInfoImpl.setNotes(StringPool.BLANK);
		}
		else {
			cffAdditionalInfoImpl.setNotes(notes);
		}

		cffAdditionalInfoImpl.resetOriginalValues();

		return cffAdditionalInfoImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		createdDate = objectInput.readLong();

		createdBy = objectInput.readInt();

		cffMasterSid = objectInput.readInt();

		cffAdditionalInfoSid = objectInput.readInt();
		notes = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(createdDate);

		objectOutput.writeInt(createdBy);

		objectOutput.writeInt(cffMasterSid);

		objectOutput.writeInt(cffAdditionalInfoSid);

		if (notes == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(notes);
		}
	}

	public long createdDate;
	public int createdBy;
	public int cffMasterSid;
	public int cffAdditionalInfoSid;
	public String notes;
}