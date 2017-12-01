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

import com.stpl.app.model.HistCompanyGroupDetails;
import com.stpl.app.service.persistence.HistCompanyGroupDetailsPK;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing HistCompanyGroupDetails in entity cache.
 *
 * @author
 * @see HistCompanyGroupDetails
 * @generated
 */
@ProviderType
public class HistCompanyGroupDetailsCacheModel implements CacheModel<HistCompanyGroupDetails>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof HistCompanyGroupDetailsCacheModel)) {
			return false;
		}

		HistCompanyGroupDetailsCacheModel histCompanyGroupDetailsCacheModel = (HistCompanyGroupDetailsCacheModel)obj;

		if (histCompanyGroupDetailsPK.equals(
					histCompanyGroupDetailsCacheModel.histCompanyGroupDetailsPK)) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, histCompanyGroupDetailsPK);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(25);

		sb.append("{createdDate=");
		sb.append(createdDate);
		sb.append(", createdBy=");
		sb.append(createdBy);
		sb.append(", actionDate=");
		sb.append(actionDate);
		sb.append(", companyParentDetailsSid=");
		sb.append(companyParentDetailsSid);
		sb.append(", companyTradeclassSid=");
		sb.append(companyTradeclassSid);
		sb.append(", actionFlag=");
		sb.append(actionFlag);
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
	public HistCompanyGroupDetails toEntityModel() {
		HistCompanyGroupDetailsImpl histCompanyGroupDetailsImpl = new HistCompanyGroupDetailsImpl();

		if (createdDate == Long.MIN_VALUE) {
			histCompanyGroupDetailsImpl.setCreatedDate(null);
		}
		else {
			histCompanyGroupDetailsImpl.setCreatedDate(new Date(createdDate));
		}

		histCompanyGroupDetailsImpl.setCreatedBy(createdBy);

		if (actionDate == Long.MIN_VALUE) {
			histCompanyGroupDetailsImpl.setActionDate(null);
		}
		else {
			histCompanyGroupDetailsImpl.setActionDate(new Date(actionDate));
		}

		if (companyParentDetailsSid == null) {
			histCompanyGroupDetailsImpl.setCompanyParentDetailsSid(StringPool.BLANK);
		}
		else {
			histCompanyGroupDetailsImpl.setCompanyParentDetailsSid(companyParentDetailsSid);
		}

		histCompanyGroupDetailsImpl.setCompanyTradeclassSid(companyTradeclassSid);

		if (actionFlag == null) {
			histCompanyGroupDetailsImpl.setActionFlag(StringPool.BLANK);
		}
		else {
			histCompanyGroupDetailsImpl.setActionFlag(actionFlag);
		}

		histCompanyGroupDetailsImpl.setCompanyGroupSid(companyGroupSid);
		histCompanyGroupDetailsImpl.setVersionNo(versionNo);
		histCompanyGroupDetailsImpl.setCompanyGroupDetailsSid(companyGroupDetailsSid);
		histCompanyGroupDetailsImpl.setModifiedBy(modifiedBy);

		if (modifiedDate == Long.MIN_VALUE) {
			histCompanyGroupDetailsImpl.setModifiedDate(null);
		}
		else {
			histCompanyGroupDetailsImpl.setModifiedDate(new Date(modifiedDate));
		}

		histCompanyGroupDetailsImpl.setCompanyMasterSid(companyMasterSid);

		histCompanyGroupDetailsImpl.resetOriginalValues();

		return histCompanyGroupDetailsImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		createdDate = objectInput.readLong();

		createdBy = objectInput.readInt();
		actionDate = objectInput.readLong();
		companyParentDetailsSid = objectInput.readUTF();

		companyTradeclassSid = objectInput.readInt();
		actionFlag = objectInput.readUTF();

		companyGroupSid = objectInput.readInt();

		versionNo = objectInput.readInt();

		companyGroupDetailsSid = objectInput.readInt();

		modifiedBy = objectInput.readInt();
		modifiedDate = objectInput.readLong();

		companyMasterSid = objectInput.readInt();

		histCompanyGroupDetailsPK = new HistCompanyGroupDetailsPK(actionFlag,
				versionNo, companyGroupDetailsSid);
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(createdDate);

		objectOutput.writeInt(createdBy);
		objectOutput.writeLong(actionDate);

		if (companyParentDetailsSid == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(companyParentDetailsSid);
		}

		objectOutput.writeInt(companyTradeclassSid);

		if (actionFlag == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(actionFlag);
		}

		objectOutput.writeInt(companyGroupSid);

		objectOutput.writeInt(versionNo);

		objectOutput.writeInt(companyGroupDetailsSid);

		objectOutput.writeInt(modifiedBy);
		objectOutput.writeLong(modifiedDate);

		objectOutput.writeInt(companyMasterSid);
	}

	public long createdDate;
	public int createdBy;
	public long actionDate;
	public String companyParentDetailsSid;
	public int companyTradeclassSid;
	public String actionFlag;
	public int companyGroupSid;
	public int versionNo;
	public int companyGroupDetailsSid;
	public int modifiedBy;
	public long modifiedDate;
	public int companyMasterSid;
	public transient HistCompanyGroupDetailsPK histCompanyGroupDetailsPK;
}