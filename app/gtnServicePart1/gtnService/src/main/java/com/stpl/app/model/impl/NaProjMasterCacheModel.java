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

import com.stpl.app.model.NaProjMaster;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing NaProjMaster in entity cache.
 *
 * @author
 * @see NaProjMaster
 * @generated
 */
@ProviderType
public class NaProjMasterCacheModel implements CacheModel<NaProjMaster>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof NaProjMasterCacheModel)) {
			return false;
		}

		NaProjMasterCacheModel naProjMasterCacheModel = (NaProjMasterCacheModel)obj;

		if (naProjMasterSid == naProjMasterCacheModel.naProjMasterSid) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, naProjMasterSid);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(23);

		sb.append("{naProjName=");
		sb.append(naProjName);
		sb.append(", createdDate=");
		sb.append(createdDate);
		sb.append(", createdBy=");
		sb.append(createdBy);
		sb.append(", saveFlag=");
		sb.append(saveFlag);
		sb.append(", modifiedBy=");
		sb.append(modifiedBy);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", naProjMasterSid=");
		sb.append(naProjMasterSid);
		sb.append(", itemGroupSid=");
		sb.append(itemGroupSid);
		sb.append(", therapeuticClass=");
		sb.append(therapeuticClass);
		sb.append(", companyMasterSid=");
		sb.append(companyMasterSid);
		sb.append(", businessUnit=");
		sb.append(businessUnit);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public NaProjMaster toEntityModel() {
		NaProjMasterImpl naProjMasterImpl = new NaProjMasterImpl();

		if (naProjName == null) {
			naProjMasterImpl.setNaProjName(StringPool.BLANK);
		}
		else {
			naProjMasterImpl.setNaProjName(naProjName);
		}

		if (createdDate == Long.MIN_VALUE) {
			naProjMasterImpl.setCreatedDate(null);
		}
		else {
			naProjMasterImpl.setCreatedDate(new Date(createdDate));
		}

		naProjMasterImpl.setCreatedBy(createdBy);
		naProjMasterImpl.setSaveFlag(saveFlag);
		naProjMasterImpl.setModifiedBy(modifiedBy);

		if (modifiedDate == Long.MIN_VALUE) {
			naProjMasterImpl.setModifiedDate(null);
		}
		else {
			naProjMasterImpl.setModifiedDate(new Date(modifiedDate));
		}

		naProjMasterImpl.setNaProjMasterSid(naProjMasterSid);
		naProjMasterImpl.setItemGroupSid(itemGroupSid);
		naProjMasterImpl.setTherapeuticClass(therapeuticClass);
		naProjMasterImpl.setCompanyMasterSid(companyMasterSid);
		naProjMasterImpl.setBusinessUnit(businessUnit);

		naProjMasterImpl.resetOriginalValues();

		return naProjMasterImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		naProjName = objectInput.readUTF();
		createdDate = objectInput.readLong();

		createdBy = objectInput.readInt();

		saveFlag = objectInput.readBoolean();

		modifiedBy = objectInput.readInt();
		modifiedDate = objectInput.readLong();

		naProjMasterSid = objectInput.readInt();

		itemGroupSid = objectInput.readInt();

		therapeuticClass = objectInput.readInt();

		companyMasterSid = objectInput.readInt();

		businessUnit = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		if (naProjName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(naProjName);
		}

		objectOutput.writeLong(createdDate);

		objectOutput.writeInt(createdBy);

		objectOutput.writeBoolean(saveFlag);

		objectOutput.writeInt(modifiedBy);
		objectOutput.writeLong(modifiedDate);

		objectOutput.writeInt(naProjMasterSid);

		objectOutput.writeInt(itemGroupSid);

		objectOutput.writeInt(therapeuticClass);

		objectOutput.writeInt(companyMasterSid);

		objectOutput.writeInt(businessUnit);
	}

	public String naProjName;
	public long createdDate;
	public int createdBy;
	public boolean saveFlag;
	public int modifiedBy;
	public long modifiedDate;
	public int naProjMasterSid;
	public int itemGroupSid;
	public int therapeuticClass;
	public int companyMasterSid;
	public int businessUnit;
}