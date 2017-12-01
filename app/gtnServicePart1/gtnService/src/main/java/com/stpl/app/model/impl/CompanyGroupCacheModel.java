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

import com.stpl.app.model.CompanyGroup;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing CompanyGroup in entity cache.
 *
 * @author
 * @see CompanyGroup
 * @generated
 */
@ProviderType
public class CompanyGroupCacheModel implements CacheModel<CompanyGroup>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CompanyGroupCacheModel)) {
			return false;
		}

		CompanyGroupCacheModel companyGroupCacheModel = (CompanyGroupCacheModel)obj;

		if (companyGroupSid == companyGroupCacheModel.companyGroupSid) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, companyGroupSid);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(21);

		sb.append("{companyGroupNo=");
		sb.append(companyGroupNo);
		sb.append(", createdDate=");
		sb.append(createdDate);
		sb.append(", createdBy=");
		sb.append(createdBy);
		sb.append(", companyFilter=");
		sb.append(companyFilter);
		sb.append(", companyGroupSid=");
		sb.append(companyGroupSid);
		sb.append(", companyGroupDescription=");
		sb.append(companyGroupDescription);
		sb.append(", versionNo=");
		sb.append(versionNo);
		sb.append(", modifiedBy=");
		sb.append(modifiedBy);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", companyGroupName=");
		sb.append(companyGroupName);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public CompanyGroup toEntityModel() {
		CompanyGroupImpl companyGroupImpl = new CompanyGroupImpl();

		if (companyGroupNo == null) {
			companyGroupImpl.setCompanyGroupNo(StringPool.BLANK);
		}
		else {
			companyGroupImpl.setCompanyGroupNo(companyGroupNo);
		}

		if (createdDate == Long.MIN_VALUE) {
			companyGroupImpl.setCreatedDate(null);
		}
		else {
			companyGroupImpl.setCreatedDate(new Date(createdDate));
		}

		companyGroupImpl.setCreatedBy(createdBy);

		if (companyFilter == null) {
			companyGroupImpl.setCompanyFilter(StringPool.BLANK);
		}
		else {
			companyGroupImpl.setCompanyFilter(companyFilter);
		}

		companyGroupImpl.setCompanyGroupSid(companyGroupSid);

		if (companyGroupDescription == null) {
			companyGroupImpl.setCompanyGroupDescription(StringPool.BLANK);
		}
		else {
			companyGroupImpl.setCompanyGroupDescription(companyGroupDescription);
		}

		companyGroupImpl.setVersionNo(versionNo);
		companyGroupImpl.setModifiedBy(modifiedBy);

		if (modifiedDate == Long.MIN_VALUE) {
			companyGroupImpl.setModifiedDate(null);
		}
		else {
			companyGroupImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (companyGroupName == null) {
			companyGroupImpl.setCompanyGroupName(StringPool.BLANK);
		}
		else {
			companyGroupImpl.setCompanyGroupName(companyGroupName);
		}

		companyGroupImpl.resetOriginalValues();

		return companyGroupImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		companyGroupNo = objectInput.readUTF();
		createdDate = objectInput.readLong();

		createdBy = objectInput.readInt();
		companyFilter = objectInput.readUTF();

		companyGroupSid = objectInput.readInt();
		companyGroupDescription = objectInput.readUTF();

		versionNo = objectInput.readInt();

		modifiedBy = objectInput.readInt();
		modifiedDate = objectInput.readLong();
		companyGroupName = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		if (companyGroupNo == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(companyGroupNo);
		}

		objectOutput.writeLong(createdDate);

		objectOutput.writeInt(createdBy);

		if (companyFilter == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(companyFilter);
		}

		objectOutput.writeInt(companyGroupSid);

		if (companyGroupDescription == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(companyGroupDescription);
		}

		objectOutput.writeInt(versionNo);

		objectOutput.writeInt(modifiedBy);
		objectOutput.writeLong(modifiedDate);

		if (companyGroupName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(companyGroupName);
		}
	}

	public String companyGroupNo;
	public long createdDate;
	public int createdBy;
	public String companyFilter;
	public int companyGroupSid;
	public String companyGroupDescription;
	public int versionNo;
	public int modifiedBy;
	public long modifiedDate;
	public String companyGroupName;
}