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

import com.stpl.app.model.ModuleSubmoduleMaster;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing ModuleSubmoduleMaster in entity cache.
 *
 * @author
 * @see ModuleSubmoduleMaster
 * @generated
 */
@ProviderType
public class ModuleSubmoduleMasterCacheModel implements CacheModel<ModuleSubmoduleMaster>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ModuleSubmoduleMasterCacheModel)) {
			return false;
		}

		ModuleSubmoduleMasterCacheModel moduleSubmoduleMasterCacheModel = (ModuleSubmoduleMasterCacheModel)obj;

		if (moduleSubmoduleSid == moduleSubmoduleMasterCacheModel.moduleSubmoduleSid) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, moduleSubmoduleSid);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(19);

		sb.append("{createdDate=");
		sb.append(createdDate);
		sb.append(", createdBy=");
		sb.append(createdBy);
		sb.append(", category=");
		sb.append(category);
		sb.append(", moduleSubmoduleSid=");
		sb.append(moduleSubmoduleSid);
		sb.append(", submoduleName=");
		sb.append(submoduleName);
		sb.append(", moduleName=");
		sb.append(moduleName);
		sb.append(", versionNo=");
		sb.append(versionNo);
		sb.append(", modifiedBy=");
		sb.append(modifiedBy);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ModuleSubmoduleMaster toEntityModel() {
		ModuleSubmoduleMasterImpl moduleSubmoduleMasterImpl = new ModuleSubmoduleMasterImpl();

		if (createdDate == Long.MIN_VALUE) {
			moduleSubmoduleMasterImpl.setCreatedDate(null);
		}
		else {
			moduleSubmoduleMasterImpl.setCreatedDate(new Date(createdDate));
		}

		moduleSubmoduleMasterImpl.setCreatedBy(createdBy);

		if (category == null) {
			moduleSubmoduleMasterImpl.setCategory(StringPool.BLANK);
		}
		else {
			moduleSubmoduleMasterImpl.setCategory(category);
		}

		moduleSubmoduleMasterImpl.setModuleSubmoduleSid(moduleSubmoduleSid);

		if (submoduleName == null) {
			moduleSubmoduleMasterImpl.setSubmoduleName(StringPool.BLANK);
		}
		else {
			moduleSubmoduleMasterImpl.setSubmoduleName(submoduleName);
		}

		if (moduleName == null) {
			moduleSubmoduleMasterImpl.setModuleName(StringPool.BLANK);
		}
		else {
			moduleSubmoduleMasterImpl.setModuleName(moduleName);
		}

		moduleSubmoduleMasterImpl.setVersionNo(versionNo);
		moduleSubmoduleMasterImpl.setModifiedBy(modifiedBy);

		if (modifiedDate == Long.MIN_VALUE) {
			moduleSubmoduleMasterImpl.setModifiedDate(null);
		}
		else {
			moduleSubmoduleMasterImpl.setModifiedDate(new Date(modifiedDate));
		}

		moduleSubmoduleMasterImpl.resetOriginalValues();

		return moduleSubmoduleMasterImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		createdDate = objectInput.readLong();

		createdBy = objectInput.readInt();
		category = objectInput.readUTF();

		moduleSubmoduleSid = objectInput.readInt();
		submoduleName = objectInput.readUTF();
		moduleName = objectInput.readUTF();

		versionNo = objectInput.readInt();

		modifiedBy = objectInput.readInt();
		modifiedDate = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(createdDate);

		objectOutput.writeInt(createdBy);

		if (category == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(category);
		}

		objectOutput.writeInt(moduleSubmoduleSid);

		if (submoduleName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(submoduleName);
		}

		if (moduleName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(moduleName);
		}

		objectOutput.writeInt(versionNo);

		objectOutput.writeInt(modifiedBy);
		objectOutput.writeLong(modifiedDate);
	}

	public long createdDate;
	public int createdBy;
	public String category;
	public int moduleSubmoduleSid;
	public String submoduleName;
	public String moduleName;
	public int versionNo;
	public int modifiedBy;
	public long modifiedDate;
}