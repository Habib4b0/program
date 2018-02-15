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

import com.stpl.app.model.HierarchyLevelDefinition;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing HierarchyLevelDefinition in entity cache.
 *
 * @author
 * @see HierarchyLevelDefinition
 * @generated
 */
@ProviderType
public class HierarchyLevelDefinitionCacheModel implements CacheModel<HierarchyLevelDefinition>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof HierarchyLevelDefinitionCacheModel)) {
			return false;
		}

		HierarchyLevelDefinitionCacheModel hierarchyLevelDefinitionCacheModel = (HierarchyLevelDefinitionCacheModel)obj;

		if (hierarchyLevelDefinitionSid == hierarchyLevelDefinitionCacheModel.hierarchyLevelDefinitionSid) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, hierarchyLevelDefinitionSid);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(25);

		sb.append("{tableName=");
		sb.append(tableName);
		sb.append(", createdDate=");
		sb.append(createdDate);
		sb.append(", createdBy=");
		sb.append(createdBy);
		sb.append(", levelValueReference=");
		sb.append(levelValueReference);
		sb.append(", fieldName=");
		sb.append(fieldName);
		sb.append(", levelNo=");
		sb.append(levelNo);
		sb.append(", hierarchyLevelDefinitionSid=");
		sb.append(hierarchyLevelDefinitionSid);
		sb.append(", hierarchyDefinitionSid=");
		sb.append(hierarchyDefinitionSid);
		sb.append(", versionNo=");
		sb.append(versionNo);
		sb.append(", modifiedBy=");
		sb.append(modifiedBy);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", levelName=");
		sb.append(levelName);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public HierarchyLevelDefinition toEntityModel() {
		HierarchyLevelDefinitionImpl hierarchyLevelDefinitionImpl = new HierarchyLevelDefinitionImpl();

		if (tableName == null) {
			hierarchyLevelDefinitionImpl.setTableName(StringPool.BLANK);
		}
		else {
			hierarchyLevelDefinitionImpl.setTableName(tableName);
		}

		if (createdDate == Long.MIN_VALUE) {
			hierarchyLevelDefinitionImpl.setCreatedDate(null);
		}
		else {
			hierarchyLevelDefinitionImpl.setCreatedDate(new Date(createdDate));
		}

		hierarchyLevelDefinitionImpl.setCreatedBy(createdBy);

		if (levelValueReference == null) {
			hierarchyLevelDefinitionImpl.setLevelValueReference(StringPool.BLANK);
		}
		else {
			hierarchyLevelDefinitionImpl.setLevelValueReference(levelValueReference);
		}

		if (fieldName == null) {
			hierarchyLevelDefinitionImpl.setFieldName(StringPool.BLANK);
		}
		else {
			hierarchyLevelDefinitionImpl.setFieldName(fieldName);
		}

		hierarchyLevelDefinitionImpl.setLevelNo(levelNo);
		hierarchyLevelDefinitionImpl.setHierarchyLevelDefinitionSid(hierarchyLevelDefinitionSid);
		hierarchyLevelDefinitionImpl.setHierarchyDefinitionSid(hierarchyDefinitionSid);
		hierarchyLevelDefinitionImpl.setVersionNo(versionNo);
		hierarchyLevelDefinitionImpl.setModifiedBy(modifiedBy);

		if (modifiedDate == Long.MIN_VALUE) {
			hierarchyLevelDefinitionImpl.setModifiedDate(null);
		}
		else {
			hierarchyLevelDefinitionImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (levelName == null) {
			hierarchyLevelDefinitionImpl.setLevelName(StringPool.BLANK);
		}
		else {
			hierarchyLevelDefinitionImpl.setLevelName(levelName);
		}

		hierarchyLevelDefinitionImpl.resetOriginalValues();

		return hierarchyLevelDefinitionImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		tableName = objectInput.readUTF();
		createdDate = objectInput.readLong();

		createdBy = objectInput.readInt();
		levelValueReference = objectInput.readUTF();
		fieldName = objectInput.readUTF();

		levelNo = objectInput.readInt();

		hierarchyLevelDefinitionSid = objectInput.readInt();

		hierarchyDefinitionSid = objectInput.readInt();

		versionNo = objectInput.readInt();

		modifiedBy = objectInput.readInt();
		modifiedDate = objectInput.readLong();
		levelName = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		if (tableName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(tableName);
		}

		objectOutput.writeLong(createdDate);

		objectOutput.writeInt(createdBy);

		if (levelValueReference == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(levelValueReference);
		}

		if (fieldName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(fieldName);
		}

		objectOutput.writeInt(levelNo);

		objectOutput.writeInt(hierarchyLevelDefinitionSid);

		objectOutput.writeInt(hierarchyDefinitionSid);

		objectOutput.writeInt(versionNo);

		objectOutput.writeInt(modifiedBy);
		objectOutput.writeLong(modifiedDate);

		if (levelName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(levelName);
		}
	}

	public String tableName;
	public long createdDate;
	public int createdBy;
	public String levelValueReference;
	public String fieldName;
	public int levelNo;
	public int hierarchyLevelDefinitionSid;
	public int hierarchyDefinitionSid;
	public int versionNo;
	public int modifiedBy;
	public long modifiedDate;
	public String levelName;
}