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

import com.stpl.app.model.DeductionGroupDetails;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing DeductionGroupDetails in entity cache.
 *
 * @author
 * @see DeductionGroupDetails
 * @generated
 */
@ProviderType
public class DeductionGroupDetailsCacheModel implements CacheModel<DeductionGroupDetails>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof DeductionGroupDetailsCacheModel)) {
			return false;
		}

		DeductionGroupDetailsCacheModel deductionGroupDetailsCacheModel = (DeductionGroupDetailsCacheModel)obj;

		if (deductionGroupDetailsSid == deductionGroupDetailsCacheModel.deductionGroupDetailsSid) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, deductionGroupDetailsSid);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(17);

		sb.append("{deductionGroupDetailsSid=");
		sb.append(deductionGroupDetailsSid);
		sb.append(", createdDate=");
		sb.append(createdDate);
		sb.append(", createdBy=");
		sb.append(createdBy);
		sb.append(", deductionGroupSid=");
		sb.append(deductionGroupSid);
		sb.append(", versionNo=");
		sb.append(versionNo);
		sb.append(", modifiedBy=");
		sb.append(modifiedBy);
		sb.append(", rsModelSid=");
		sb.append(rsModelSid);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public DeductionGroupDetails toEntityModel() {
		DeductionGroupDetailsImpl deductionGroupDetailsImpl = new DeductionGroupDetailsImpl();

		deductionGroupDetailsImpl.setDeductionGroupDetailsSid(deductionGroupDetailsSid);

		if (createdDate == Long.MIN_VALUE) {
			deductionGroupDetailsImpl.setCreatedDate(null);
		}
		else {
			deductionGroupDetailsImpl.setCreatedDate(new Date(createdDate));
		}

		deductionGroupDetailsImpl.setCreatedBy(createdBy);
		deductionGroupDetailsImpl.setDeductionGroupSid(deductionGroupSid);
		deductionGroupDetailsImpl.setVersionNo(versionNo);
		deductionGroupDetailsImpl.setModifiedBy(modifiedBy);
		deductionGroupDetailsImpl.setRsModelSid(rsModelSid);

		if (modifiedDate == Long.MIN_VALUE) {
			deductionGroupDetailsImpl.setModifiedDate(null);
		}
		else {
			deductionGroupDetailsImpl.setModifiedDate(new Date(modifiedDate));
		}

		deductionGroupDetailsImpl.resetOriginalValues();

		return deductionGroupDetailsImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		deductionGroupDetailsSid = objectInput.readInt();
		createdDate = objectInput.readLong();

		createdBy = objectInput.readInt();

		deductionGroupSid = objectInput.readInt();

		versionNo = objectInput.readInt();

		modifiedBy = objectInput.readInt();

		rsModelSid = objectInput.readInt();
		modifiedDate = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeInt(deductionGroupDetailsSid);
		objectOutput.writeLong(createdDate);

		objectOutput.writeInt(createdBy);

		objectOutput.writeInt(deductionGroupSid);

		objectOutput.writeInt(versionNo);

		objectOutput.writeInt(modifiedBy);

		objectOutput.writeInt(rsModelSid);
		objectOutput.writeLong(modifiedDate);
	}

	public int deductionGroupDetailsSid;
	public long createdDate;
	public int createdBy;
	public int deductionGroupSid;
	public int versionNo;
	public int modifiedBy;
	public int rsModelSid;
	public long modifiedDate;
}