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

import com.stpl.app.model.UsergroupDomainMaster;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing UsergroupDomainMaster in entity cache.
 *
 * @author
 * @see UsergroupDomainMaster
 * @generated
 */
@ProviderType
public class UsergroupDomainMasterCacheModel implements CacheModel<UsergroupDomainMaster>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof UsergroupDomainMasterCacheModel)) {
			return false;
		}

		UsergroupDomainMasterCacheModel usergroupDomainMasterCacheModel = (UsergroupDomainMasterCacheModel)obj;

		if (usergroupDomainSid == usergroupDomainMasterCacheModel.usergroupDomainSid) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, usergroupDomainSid);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(23);

		sb.append("{createdBy=");
		sb.append(createdBy);
		sb.append(", usergroupDomainSid=");
		sb.append(usergroupDomainSid);
		sb.append(", usersSid=");
		sb.append(usersSid);
		sb.append(", modifiedBy=");
		sb.append(modifiedBy);
		sb.append(", createdDate=");
		sb.append(createdDate);
		sb.append(", domainId=");
		sb.append(domainId);
		sb.append(", processed=");
		sb.append(processed);
		sb.append(", usergroupId=");
		sb.append(usergroupId);
		sb.append(", versionNo=");
		sb.append(versionNo);
		sb.append(", isActive=");
		sb.append(isActive);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public UsergroupDomainMaster toEntityModel() {
		UsergroupDomainMasterImpl usergroupDomainMasterImpl = new UsergroupDomainMasterImpl();

		usergroupDomainMasterImpl.setCreatedBy(createdBy);
		usergroupDomainMasterImpl.setUsergroupDomainSid(usergroupDomainSid);
		usergroupDomainMasterImpl.setUsersSid(usersSid);
		usergroupDomainMasterImpl.setModifiedBy(modifiedBy);

		if (createdDate == Long.MIN_VALUE) {
			usergroupDomainMasterImpl.setCreatedDate(null);
		}
		else {
			usergroupDomainMasterImpl.setCreatedDate(new Date(createdDate));
		}

		usergroupDomainMasterImpl.setDomainId(domainId);

		if (processed == null) {
			usergroupDomainMasterImpl.setProcessed(StringPool.BLANK);
		}
		else {
			usergroupDomainMasterImpl.setProcessed(processed);
		}

		usergroupDomainMasterImpl.setUsergroupId(usergroupId);
		usergroupDomainMasterImpl.setVersionNo(versionNo);

		if (isActive == null) {
			usergroupDomainMasterImpl.setIsActive(StringPool.BLANK);
		}
		else {
			usergroupDomainMasterImpl.setIsActive(isActive);
		}

		if (modifiedDate == Long.MIN_VALUE) {
			usergroupDomainMasterImpl.setModifiedDate(null);
		}
		else {
			usergroupDomainMasterImpl.setModifiedDate(new Date(modifiedDate));
		}

		usergroupDomainMasterImpl.resetOriginalValues();

		return usergroupDomainMasterImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		createdBy = objectInput.readInt();

		usergroupDomainSid = objectInput.readInt();

		usersSid = objectInput.readInt();

		modifiedBy = objectInput.readInt();
		createdDate = objectInput.readLong();

		domainId = objectInput.readInt();
		processed = objectInput.readUTF();

		usergroupId = objectInput.readInt();

		versionNo = objectInput.readInt();
		isActive = objectInput.readUTF();
		modifiedDate = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeInt(createdBy);

		objectOutput.writeInt(usergroupDomainSid);

		objectOutput.writeInt(usersSid);

		objectOutput.writeInt(modifiedBy);
		objectOutput.writeLong(createdDate);

		objectOutput.writeInt(domainId);

		if (processed == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(processed);
		}

		objectOutput.writeInt(usergroupId);

		objectOutput.writeInt(versionNo);

		if (isActive == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(isActive);
		}

		objectOutput.writeLong(modifiedDate);
	}

	public int createdBy;
	public int usergroupDomainSid;
	public int usersSid;
	public int modifiedBy;
	public long createdDate;
	public int domainId;
	public String processed;
	public int usergroupId;
	public int versionNo;
	public String isActive;
	public long modifiedDate;
}