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

import com.stpl.app.model.ModuleProperties;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing ModuleProperties in entity cache.
 *
 * @author
 * @see ModuleProperties
 * @generated
 */
@ProviderType
public class ModulePropertiesCacheModel implements CacheModel<ModuleProperties>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ModulePropertiesCacheModel)) {
			return false;
		}

		ModulePropertiesCacheModel modulePropertiesCacheModel = (ModulePropertiesCacheModel)obj;

		if (modulePropertySid == modulePropertiesCacheModel.modulePropertySid) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, modulePropertySid);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(25);

		sb.append("{modulePropertySid=");
		sb.append(modulePropertySid);
		sb.append(", createdBy=");
		sb.append(createdBy);
		sb.append(", moduleName=");
		sb.append(moduleName);
		sb.append(", modifiedBy=");
		sb.append(modifiedBy);
		sb.append(", createdDate=");
		sb.append(createdDate);
		sb.append(", nullFlag=");
		sb.append(nullFlag);
		sb.append(", versionNo=");
		sb.append(versionNo);
		sb.append(", moduleSubmoduleSid=");
		sb.append(moduleSubmoduleSid);
		sb.append(", categoryName=");
		sb.append(categoryName);
		sb.append(", propertyName=");
		sb.append(propertyName);
		sb.append(", displayName=");
		sb.append(displayName);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ModuleProperties toEntityModel() {
		ModulePropertiesImpl modulePropertiesImpl = new ModulePropertiesImpl();

		modulePropertiesImpl.setModulePropertySid(modulePropertySid);
		modulePropertiesImpl.setCreatedBy(createdBy);

		if (moduleName == null) {
			modulePropertiesImpl.setModuleName(StringPool.BLANK);
		}
		else {
			modulePropertiesImpl.setModuleName(moduleName);
		}

		modulePropertiesImpl.setModifiedBy(modifiedBy);

		if (createdDate == Long.MIN_VALUE) {
			modulePropertiesImpl.setCreatedDate(null);
		}
		else {
			modulePropertiesImpl.setCreatedDate(new Date(createdDate));
		}

		if (nullFlag == null) {
			modulePropertiesImpl.setNullFlag(StringPool.BLANK);
		}
		else {
			modulePropertiesImpl.setNullFlag(nullFlag);
		}

		modulePropertiesImpl.setVersionNo(versionNo);
		modulePropertiesImpl.setModuleSubmoduleSid(moduleSubmoduleSid);

		if (categoryName == null) {
			modulePropertiesImpl.setCategoryName(StringPool.BLANK);
		}
		else {
			modulePropertiesImpl.setCategoryName(categoryName);
		}

		if (propertyName == null) {
			modulePropertiesImpl.setPropertyName(StringPool.BLANK);
		}
		else {
			modulePropertiesImpl.setPropertyName(propertyName);
		}

		if (displayName == null) {
			modulePropertiesImpl.setDisplayName(StringPool.BLANK);
		}
		else {
			modulePropertiesImpl.setDisplayName(displayName);
		}

		if (modifiedDate == Long.MIN_VALUE) {
			modulePropertiesImpl.setModifiedDate(null);
		}
		else {
			modulePropertiesImpl.setModifiedDate(new Date(modifiedDate));
		}

		modulePropertiesImpl.resetOriginalValues();

		return modulePropertiesImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		modulePropertySid = objectInput.readInt();

		createdBy = objectInput.readInt();
		moduleName = objectInput.readUTF();

		modifiedBy = objectInput.readInt();
		createdDate = objectInput.readLong();
		nullFlag = objectInput.readUTF();

		versionNo = objectInput.readInt();

		moduleSubmoduleSid = objectInput.readInt();
		categoryName = objectInput.readUTF();
		propertyName = objectInput.readUTF();
		displayName = objectInput.readUTF();
		modifiedDate = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeInt(modulePropertySid);

		objectOutput.writeInt(createdBy);

		if (moduleName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(moduleName);
		}

		objectOutput.writeInt(modifiedBy);
		objectOutput.writeLong(createdDate);

		if (nullFlag == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(nullFlag);
		}

		objectOutput.writeInt(versionNo);

		objectOutput.writeInt(moduleSubmoduleSid);

		if (categoryName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(categoryName);
		}

		if (propertyName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(propertyName);
		}

		if (displayName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(displayName);
		}

		objectOutput.writeLong(modifiedDate);
	}

	public int modulePropertySid;
	public int createdBy;
	public String moduleName;
	public int modifiedBy;
	public long createdDate;
	public String nullFlag;
	public int versionNo;
	public int moduleSubmoduleSid;
	public String categoryName;
	public String propertyName;
	public String displayName;
	public long modifiedDate;
}