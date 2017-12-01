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

import com.stpl.app.model.RelationshipLevelDefinition;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing RelationshipLevelDefinition in entity cache.
 *
 * @author
 * @see RelationshipLevelDefinition
 * @generated
 */
@ProviderType
public class RelationshipLevelDefinitionCacheModel implements CacheModel<RelationshipLevelDefinition>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof RelationshipLevelDefinitionCacheModel)) {
			return false;
		}

		RelationshipLevelDefinitionCacheModel relationshipLevelDefinitionCacheModel =
			(RelationshipLevelDefinitionCacheModel)obj;

		if (relationshipLevelSid == relationshipLevelDefinitionCacheModel.relationshipLevelSid) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, relationshipLevelSid);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(31);

		sb.append("{relationshipLevelValues=");
		sb.append(relationshipLevelValues);
		sb.append(", hierarchyLevelDefinitionSid=");
		sb.append(hierarchyLevelDefinitionSid);
		sb.append(", parentNode=");
		sb.append(parentNode);
		sb.append(", versionNo=");
		sb.append(versionNo);
		sb.append(", relationshipBuilderSid=");
		sb.append(relationshipBuilderSid);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", createdBy=");
		sb.append(createdBy);
		sb.append(", createdDate=");
		sb.append(createdDate);
		sb.append(", levelNo=");
		sb.append(levelNo);
		sb.append(", modifiedBy=");
		sb.append(modifiedBy);
		sb.append(", hierarchyNo=");
		sb.append(hierarchyNo);
		sb.append(", relationshipLevelSid=");
		sb.append(relationshipLevelSid);
		sb.append(", flag=");
		sb.append(flag);
		sb.append(", levelName=");
		sb.append(levelName);
		sb.append(", parentHierarchyNo=");
		sb.append(parentHierarchyNo);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public RelationshipLevelDefinition toEntityModel() {
		RelationshipLevelDefinitionImpl relationshipLevelDefinitionImpl = new RelationshipLevelDefinitionImpl();

		if (relationshipLevelValues == null) {
			relationshipLevelDefinitionImpl.setRelationshipLevelValues(StringPool.BLANK);
		}
		else {
			relationshipLevelDefinitionImpl.setRelationshipLevelValues(relationshipLevelValues);
		}

		relationshipLevelDefinitionImpl.setHierarchyLevelDefinitionSid(hierarchyLevelDefinitionSid);

		if (parentNode == null) {
			relationshipLevelDefinitionImpl.setParentNode(StringPool.BLANK);
		}
		else {
			relationshipLevelDefinitionImpl.setParentNode(parentNode);
		}

		relationshipLevelDefinitionImpl.setVersionNo(versionNo);
		relationshipLevelDefinitionImpl.setRelationshipBuilderSid(relationshipBuilderSid);

		if (modifiedDate == Long.MIN_VALUE) {
			relationshipLevelDefinitionImpl.setModifiedDate(null);
		}
		else {
			relationshipLevelDefinitionImpl.setModifiedDate(new Date(
					modifiedDate));
		}

		relationshipLevelDefinitionImpl.setCreatedBy(createdBy);

		if (createdDate == Long.MIN_VALUE) {
			relationshipLevelDefinitionImpl.setCreatedDate(null);
		}
		else {
			relationshipLevelDefinitionImpl.setCreatedDate(new Date(createdDate));
		}

		if (levelNo == null) {
			relationshipLevelDefinitionImpl.setLevelNo(StringPool.BLANK);
		}
		else {
			relationshipLevelDefinitionImpl.setLevelNo(levelNo);
		}

		relationshipLevelDefinitionImpl.setModifiedBy(modifiedBy);

		if (hierarchyNo == null) {
			relationshipLevelDefinitionImpl.setHierarchyNo(StringPool.BLANK);
		}
		else {
			relationshipLevelDefinitionImpl.setHierarchyNo(hierarchyNo);
		}

		relationshipLevelDefinitionImpl.setRelationshipLevelSid(relationshipLevelSid);

		if (flag == null) {
			relationshipLevelDefinitionImpl.setFlag(StringPool.BLANK);
		}
		else {
			relationshipLevelDefinitionImpl.setFlag(flag);
		}

		if (levelName == null) {
			relationshipLevelDefinitionImpl.setLevelName(StringPool.BLANK);
		}
		else {
			relationshipLevelDefinitionImpl.setLevelName(levelName);
		}

		if (parentHierarchyNo == null) {
			relationshipLevelDefinitionImpl.setParentHierarchyNo(StringPool.BLANK);
		}
		else {
			relationshipLevelDefinitionImpl.setParentHierarchyNo(parentHierarchyNo);
		}

		relationshipLevelDefinitionImpl.resetOriginalValues();

		return relationshipLevelDefinitionImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		relationshipLevelValues = objectInput.readUTF();

		hierarchyLevelDefinitionSid = objectInput.readInt();
		parentNode = objectInput.readUTF();

		versionNo = objectInput.readInt();

		relationshipBuilderSid = objectInput.readInt();
		modifiedDate = objectInput.readLong();

		createdBy = objectInput.readInt();
		createdDate = objectInput.readLong();
		levelNo = objectInput.readUTF();

		modifiedBy = objectInput.readInt();
		hierarchyNo = objectInput.readUTF();

		relationshipLevelSid = objectInput.readInt();
		flag = objectInput.readUTF();
		levelName = objectInput.readUTF();
		parentHierarchyNo = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		if (relationshipLevelValues == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(relationshipLevelValues);
		}

		objectOutput.writeInt(hierarchyLevelDefinitionSid);

		if (parentNode == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(parentNode);
		}

		objectOutput.writeInt(versionNo);

		objectOutput.writeInt(relationshipBuilderSid);
		objectOutput.writeLong(modifiedDate);

		objectOutput.writeInt(createdBy);
		objectOutput.writeLong(createdDate);

		if (levelNo == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(levelNo);
		}

		objectOutput.writeInt(modifiedBy);

		if (hierarchyNo == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(hierarchyNo);
		}

		objectOutput.writeInt(relationshipLevelSid);

		if (flag == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(flag);
		}

		if (levelName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(levelName);
		}

		if (parentHierarchyNo == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(parentHierarchyNo);
		}
	}

	public String relationshipLevelValues;
	public int hierarchyLevelDefinitionSid;
	public String parentNode;
	public int versionNo;
	public int relationshipBuilderSid;
	public long modifiedDate;
	public int createdBy;
	public long createdDate;
	public String levelNo;
	public int modifiedBy;
	public String hierarchyNo;
	public int relationshipLevelSid;
	public String flag;
	public String levelName;
	public String parentHierarchyNo;
}