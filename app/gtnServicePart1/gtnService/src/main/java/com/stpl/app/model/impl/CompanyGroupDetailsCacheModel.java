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

import com.stpl.app.model.CompanyGroupDetails;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing CompanyGroupDetails in entity cache.
 *
 * @author
 * @see CompanyGroupDetails
 * @generated
 */
@ProviderType
public class CompanyGroupDetailsCacheModel implements CacheModel<CompanyGroupDetails>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CompanyGroupDetailsCacheModel)) {
			return false;
		}

		CompanyGroupDetailsCacheModel companyGroupDetailsCacheModel = (CompanyGroupDetailsCacheModel)obj;

		if (companyGroupDetailsSid == companyGroupDetailsCacheModel.companyGroupDetailsSid) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, companyGroupDetailsSid);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(21);

		sb.append("{createdDate=");
		sb.append(createdDate);
		sb.append(", createdBy=");
		sb.append(createdBy);
		sb.append(", companyParentDetailsSid=");
		sb.append(companyParentDetailsSid);
		sb.append(", companyTradeclassSid=");
		sb.append(companyTradeclassSid);
		sb.append(", companyGroupSid=");
		sb.append(companyGroupSid);
		sb.append(", versionNo=");
		sb.append(versionNo);
		sb.append(", companyGroupDetailsSid=");
		sb.append(companyGroupDetailsSid);
		sb.append(", modifiedBy=");
		sb.append(modifiedBy);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", companyMasterSid=");
		sb.append(companyMasterSid);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public CompanyGroupDetails toEntityModel() {
		CompanyGroupDetailsImpl companyGroupDetailsImpl = new CompanyGroupDetailsImpl();

		if (createdDate == Long.MIN_VALUE) {
			companyGroupDetailsImpl.setCreatedDate(null);
		}
		else {
			companyGroupDetailsImpl.setCreatedDate(new Date(createdDate));
		}

		companyGroupDetailsImpl.setCreatedBy(createdBy);

		if (companyParentDetailsSid == null) {
			companyGroupDetailsImpl.setCompanyParentDetailsSid(StringPool.BLANK);
		}
		else {
			companyGroupDetailsImpl.setCompanyParentDetailsSid(companyParentDetailsSid);
		}

		companyGroupDetailsImpl.setCompanyTradeclassSid(companyTradeclassSid);
		companyGroupDetailsImpl.setCompanyGroupSid(companyGroupSid);
		companyGroupDetailsImpl.setVersionNo(versionNo);
		companyGroupDetailsImpl.setCompanyGroupDetailsSid(companyGroupDetailsSid);
		companyGroupDetailsImpl.setModifiedBy(modifiedBy);

		if (modifiedDate == Long.MIN_VALUE) {
			companyGroupDetailsImpl.setModifiedDate(null);
		}
		else {
			companyGroupDetailsImpl.setModifiedDate(new Date(modifiedDate));
		}

		companyGroupDetailsImpl.setCompanyMasterSid(companyMasterSid);

		companyGroupDetailsImpl.resetOriginalValues();

		return companyGroupDetailsImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		createdDate = objectInput.readLong();

		createdBy = objectInput.readInt();
		companyParentDetailsSid = objectInput.readUTF();

		companyTradeclassSid = objectInput.readInt();

		companyGroupSid = objectInput.readInt();

		versionNo = objectInput.readInt();

		companyGroupDetailsSid = objectInput.readInt();

		modifiedBy = objectInput.readInt();
		modifiedDate = objectInput.readLong();

		companyMasterSid = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(createdDate);

		objectOutput.writeInt(createdBy);

		if (companyParentDetailsSid == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(companyParentDetailsSid);
		}

		objectOutput.writeInt(companyTradeclassSid);

		objectOutput.writeInt(companyGroupSid);

		objectOutput.writeInt(versionNo);

		objectOutput.writeInt(companyGroupDetailsSid);

		objectOutput.writeInt(modifiedBy);
		objectOutput.writeLong(modifiedDate);

		objectOutput.writeInt(companyMasterSid);
	}

	public long createdDate;
	public int createdBy;
	public String companyParentDetailsSid;
	public int companyTradeclassSid;
	public int companyGroupSid;
	public int versionNo;
	public int companyGroupDetailsSid;
	public int modifiedBy;
	public long modifiedDate;
	public int companyMasterSid;
}