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

import com.stpl.app.model.RelationshipBuilder;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing RelationshipBuilder in entity cache.
 *
 * @author
 * @see RelationshipBuilder
 * @generated
 */
@ProviderType
public class RelationshipBuilderCacheModel implements CacheModel<RelationshipBuilder>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof RelationshipBuilderCacheModel)) {
			return false;
		}

		RelationshipBuilderCacheModel relationshipBuilderCacheModel = (RelationshipBuilderCacheModel)obj;

		if (relationshipBuilderSid == relationshipBuilderCacheModel.relationshipBuilderSid) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, relationshipBuilderSid);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(29);

		sb.append("{startDate=");
		sb.append(startDate);
		sb.append(", createdDate=");
		sb.append(createdDate);
		sb.append(", createdBy=");
		sb.append(createdBy);
		sb.append(", relationshipDescription=");
		sb.append(relationshipDescription);
		sb.append(", hierarchyDefinitionSid=");
		sb.append(hierarchyDefinitionSid);
		sb.append(", versionNo=");
		sb.append(versionNo);
		sb.append(", relationshipName=");
		sb.append(relationshipName);
		sb.append(", relationshipBuilderSid=");
		sb.append(relationshipBuilderSid);
		sb.append(", hierarchyVersion=");
		sb.append(hierarchyVersion);
		sb.append(", modifiedBy=");
		sb.append(modifiedBy);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", deductionRelation=");
		sb.append(deductionRelation);
		sb.append(", relationshipType=");
		sb.append(relationshipType);
		sb.append(", buildType=");
		sb.append(buildType);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public RelationshipBuilder toEntityModel() {
		RelationshipBuilderImpl relationshipBuilderImpl = new RelationshipBuilderImpl();

		if (startDate == Long.MIN_VALUE) {
			relationshipBuilderImpl.setStartDate(null);
		}
		else {
			relationshipBuilderImpl.setStartDate(new Date(startDate));
		}

		if (createdDate == Long.MIN_VALUE) {
			relationshipBuilderImpl.setCreatedDate(null);
		}
		else {
			relationshipBuilderImpl.setCreatedDate(new Date(createdDate));
		}

		relationshipBuilderImpl.setCreatedBy(createdBy);

		if (relationshipDescription == null) {
			relationshipBuilderImpl.setRelationshipDescription(StringPool.BLANK);
		}
		else {
			relationshipBuilderImpl.setRelationshipDescription(relationshipDescription);
		}

		relationshipBuilderImpl.setHierarchyDefinitionSid(hierarchyDefinitionSid);
		relationshipBuilderImpl.setVersionNo(versionNo);

		if (relationshipName == null) {
			relationshipBuilderImpl.setRelationshipName(StringPool.BLANK);
		}
		else {
			relationshipBuilderImpl.setRelationshipName(relationshipName);
		}

		relationshipBuilderImpl.setRelationshipBuilderSid(relationshipBuilderSid);
		relationshipBuilderImpl.setHierarchyVersion(hierarchyVersion);
		relationshipBuilderImpl.setModifiedBy(modifiedBy);

		if (modifiedDate == Long.MIN_VALUE) {
			relationshipBuilderImpl.setModifiedDate(null);
		}
		else {
			relationshipBuilderImpl.setModifiedDate(new Date(modifiedDate));
		}

		relationshipBuilderImpl.setDeductionRelation(deductionRelation);
		relationshipBuilderImpl.setRelationshipType(relationshipType);

		if (buildType == null) {
			relationshipBuilderImpl.setBuildType(StringPool.BLANK);
		}
		else {
			relationshipBuilderImpl.setBuildType(buildType);
		}

		relationshipBuilderImpl.resetOriginalValues();

		return relationshipBuilderImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		startDate = objectInput.readLong();
		createdDate = objectInput.readLong();

		createdBy = objectInput.readInt();
		relationshipDescription = objectInput.readUTF();

		hierarchyDefinitionSid = objectInput.readInt();

		versionNo = objectInput.readInt();
		relationshipName = objectInput.readUTF();

		relationshipBuilderSid = objectInput.readInt();

		hierarchyVersion = objectInput.readInt();

		modifiedBy = objectInput.readInt();
		modifiedDate = objectInput.readLong();

		deductionRelation = objectInput.readInt();

		relationshipType = objectInput.readInt();
		buildType = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(startDate);
		objectOutput.writeLong(createdDate);

		objectOutput.writeInt(createdBy);

		if (relationshipDescription == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(relationshipDescription);
		}

		objectOutput.writeInt(hierarchyDefinitionSid);

		objectOutput.writeInt(versionNo);

		if (relationshipName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(relationshipName);
		}

		objectOutput.writeInt(relationshipBuilderSid);

		objectOutput.writeInt(hierarchyVersion);

		objectOutput.writeInt(modifiedBy);
		objectOutput.writeLong(modifiedDate);

		objectOutput.writeInt(deductionRelation);

		objectOutput.writeInt(relationshipType);

		if (buildType == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(buildType);
		}
	}

	public long startDate;
	public long createdDate;
	public int createdBy;
	public String relationshipDescription;
	public int hierarchyDefinitionSid;
	public int versionNo;
	public String relationshipName;
	public int relationshipBuilderSid;
	public int hierarchyVersion;
	public int modifiedBy;
	public long modifiedDate;
	public int deductionRelation;
	public int relationshipType;
	public String buildType;
}