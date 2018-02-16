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

import com.stpl.app.model.HistCompanyGroup;
import com.stpl.app.service.persistence.HistCompanyGroupPK;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing HistCompanyGroup in entity cache.
 *
 * @author
 * @see HistCompanyGroup
 * @generated
 */
@ProviderType
public class HistCompanyGroupCacheModel implements CacheModel<HistCompanyGroup>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof HistCompanyGroupCacheModel)) {
			return false;
		}

		HistCompanyGroupCacheModel histCompanyGroupCacheModel = (HistCompanyGroupCacheModel)obj;

		if (histCompanyGroupPK.equals(
					histCompanyGroupCacheModel.histCompanyGroupPK)) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, histCompanyGroupPK);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(25);

		sb.append("{companyGroupNo=");
		sb.append(companyGroupNo);
		sb.append(", createdDate=");
		sb.append(createdDate);
		sb.append(", createdBy=");
		sb.append(createdBy);
		sb.append(", actionDate=");
		sb.append(actionDate);
		sb.append(", actionFlag=");
		sb.append(actionFlag);
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
		sb.append(", companyFilter=");
		sb.append(companyFilter);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public HistCompanyGroup toEntityModel() {
		HistCompanyGroupImpl histCompanyGroupImpl = new HistCompanyGroupImpl();

		if (companyGroupNo == null) {
			histCompanyGroupImpl.setCompanyGroupNo(StringPool.BLANK);
		}
		else {
			histCompanyGroupImpl.setCompanyGroupNo(companyGroupNo);
		}

		if (createdDate == Long.MIN_VALUE) {
			histCompanyGroupImpl.setCreatedDate(null);
		}
		else {
			histCompanyGroupImpl.setCreatedDate(new Date(createdDate));
		}

		histCompanyGroupImpl.setCreatedBy(createdBy);

		if (actionDate == Long.MIN_VALUE) {
			histCompanyGroupImpl.setActionDate(null);
		}
		else {
			histCompanyGroupImpl.setActionDate(new Date(actionDate));
		}

		if (actionFlag == null) {
			histCompanyGroupImpl.setActionFlag(StringPool.BLANK);
		}
		else {
			histCompanyGroupImpl.setActionFlag(actionFlag);
		}

		histCompanyGroupImpl.setCompanyGroupSid(companyGroupSid);

		if (companyGroupDescription == null) {
			histCompanyGroupImpl.setCompanyGroupDescription(StringPool.BLANK);
		}
		else {
			histCompanyGroupImpl.setCompanyGroupDescription(companyGroupDescription);
		}

		histCompanyGroupImpl.setVersionNo(versionNo);
		histCompanyGroupImpl.setModifiedBy(modifiedBy);

		if (modifiedDate == Long.MIN_VALUE) {
			histCompanyGroupImpl.setModifiedDate(null);
		}
		else {
			histCompanyGroupImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (companyGroupName == null) {
			histCompanyGroupImpl.setCompanyGroupName(StringPool.BLANK);
		}
		else {
			histCompanyGroupImpl.setCompanyGroupName(companyGroupName);
		}

		if (companyFilter == null) {
			histCompanyGroupImpl.setCompanyFilter(StringPool.BLANK);
		}
		else {
			histCompanyGroupImpl.setCompanyFilter(companyFilter);
		}

		histCompanyGroupImpl.resetOriginalValues();

		return histCompanyGroupImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		companyGroupNo = objectInput.readUTF();
		createdDate = objectInput.readLong();

		createdBy = objectInput.readInt();
		actionDate = objectInput.readLong();
		actionFlag = objectInput.readUTF();

		companyGroupSid = objectInput.readInt();
		companyGroupDescription = objectInput.readUTF();

		versionNo = objectInput.readInt();

		modifiedBy = objectInput.readInt();
		modifiedDate = objectInput.readLong();
		companyGroupName = objectInput.readUTF();
		companyFilter = objectInput.readUTF();

		histCompanyGroupPK = new HistCompanyGroupPK(actionFlag,
				companyGroupSid, versionNo);
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
		objectOutput.writeLong(actionDate);

		if (actionFlag == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(actionFlag);
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

		if (companyFilter == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(companyFilter);
		}
	}

	public String companyGroupNo;
	public long createdDate;
	public int createdBy;
	public long actionDate;
	public String actionFlag;
	public int companyGroupSid;
	public String companyGroupDescription;
	public int versionNo;
	public int modifiedBy;
	public long modifiedDate;
	public String companyGroupName;
	public String companyFilter;
	public transient HistCompanyGroupPK histCompanyGroupPK;
}