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

import com.stpl.app.model.HistRelationshipLevelDefn;
import com.stpl.app.service.persistence.HistRelationshipLevelDefnPK;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing HistRelationshipLevelDefn in entity cache.
 *
 * @author
 * @see HistRelationshipLevelDefn
 * @generated
 */
@ProviderType
public class HistRelationshipLevelDefnCacheModel implements CacheModel<HistRelationshipLevelDefn>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof HistRelationshipLevelDefnCacheModel)) {
			return false;
		}

		HistRelationshipLevelDefnCacheModel histRelationshipLevelDefnCacheModel = (HistRelationshipLevelDefnCacheModel)obj;

		if (histRelationshipLevelDefnPK.equals(
					histRelationshipLevelDefnCacheModel.histRelationshipLevelDefnPK)) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, histRelationshipLevelDefnPK);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(33);

		sb.append("{relationshipLevelValues=");
		sb.append(relationshipLevelValues);
		sb.append(", actionDate=");
		sb.append(actionDate);
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
		sb.append(", actionFlag=");
		sb.append(actionFlag);
		sb.append(", hierarchyNo=");
		sb.append(hierarchyNo);
		sb.append(", modifiedBy=");
		sb.append(modifiedBy);
		sb.append(", relationshipLevelSid=");
		sb.append(relationshipLevelSid);
		sb.append(", flag=");
		sb.append(flag);
		sb.append(", levelName=");
		sb.append(levelName);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public HistRelationshipLevelDefn toEntityModel() {
		HistRelationshipLevelDefnImpl histRelationshipLevelDefnImpl = new HistRelationshipLevelDefnImpl();

		if (relationshipLevelValues == null) {
			histRelationshipLevelDefnImpl.setRelationshipLevelValues(StringPool.BLANK);
		}
		else {
			histRelationshipLevelDefnImpl.setRelationshipLevelValues(relationshipLevelValues);
		}

		if (actionDate == Long.MIN_VALUE) {
			histRelationshipLevelDefnImpl.setActionDate(null);
		}
		else {
			histRelationshipLevelDefnImpl.setActionDate(new Date(actionDate));
		}

		histRelationshipLevelDefnImpl.setHierarchyLevelDefinitionSid(hierarchyLevelDefinitionSid);

		if (parentNode == null) {
			histRelationshipLevelDefnImpl.setParentNode(StringPool.BLANK);
		}
		else {
			histRelationshipLevelDefnImpl.setParentNode(parentNode);
		}

		histRelationshipLevelDefnImpl.setVersionNo(versionNo);
		histRelationshipLevelDefnImpl.setRelationshipBuilderSid(relationshipBuilderSid);

		if (modifiedDate == Long.MIN_VALUE) {
			histRelationshipLevelDefnImpl.setModifiedDate(null);
		}
		else {
			histRelationshipLevelDefnImpl.setModifiedDate(new Date(modifiedDate));
		}

		histRelationshipLevelDefnImpl.setCreatedBy(createdBy);

		if (createdDate == Long.MIN_VALUE) {
			histRelationshipLevelDefnImpl.setCreatedDate(null);
		}
		else {
			histRelationshipLevelDefnImpl.setCreatedDate(new Date(createdDate));
		}

		if (levelNo == null) {
			histRelationshipLevelDefnImpl.setLevelNo(StringPool.BLANK);
		}
		else {
			histRelationshipLevelDefnImpl.setLevelNo(levelNo);
		}

		if (actionFlag == null) {
			histRelationshipLevelDefnImpl.setActionFlag(StringPool.BLANK);
		}
		else {
			histRelationshipLevelDefnImpl.setActionFlag(actionFlag);
		}

		if (hierarchyNo == null) {
			histRelationshipLevelDefnImpl.setHierarchyNo(StringPool.BLANK);
		}
		else {
			histRelationshipLevelDefnImpl.setHierarchyNo(hierarchyNo);
		}

		histRelationshipLevelDefnImpl.setModifiedBy(modifiedBy);
		histRelationshipLevelDefnImpl.setRelationshipLevelSid(relationshipLevelSid);

		if (flag == null) {
			histRelationshipLevelDefnImpl.setFlag(StringPool.BLANK);
		}
		else {
			histRelationshipLevelDefnImpl.setFlag(flag);
		}

		if (levelName == null) {
			histRelationshipLevelDefnImpl.setLevelName(StringPool.BLANK);
		}
		else {
			histRelationshipLevelDefnImpl.setLevelName(levelName);
		}

		histRelationshipLevelDefnImpl.resetOriginalValues();

		return histRelationshipLevelDefnImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		relationshipLevelValues = objectInput.readUTF();
		actionDate = objectInput.readLong();

		hierarchyLevelDefinitionSid = objectInput.readInt();
		parentNode = objectInput.readUTF();

		versionNo = objectInput.readInt();

		relationshipBuilderSid = objectInput.readInt();
		modifiedDate = objectInput.readLong();

		createdBy = objectInput.readInt();
		createdDate = objectInput.readLong();
		levelNo = objectInput.readUTF();
		actionFlag = objectInput.readUTF();
		hierarchyNo = objectInput.readUTF();

		modifiedBy = objectInput.readInt();

		relationshipLevelSid = objectInput.readInt();
		flag = objectInput.readUTF();
		levelName = objectInput.readUTF();

		histRelationshipLevelDefnPK = new HistRelationshipLevelDefnPK(versionNo,
				actionFlag, relationshipLevelSid);
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

		objectOutput.writeLong(actionDate);

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

		if (actionFlag == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(actionFlag);
		}

		if (hierarchyNo == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(hierarchyNo);
		}

		objectOutput.writeInt(modifiedBy);

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
	}

	public String relationshipLevelValues;
	public long actionDate;
	public int hierarchyLevelDefinitionSid;
	public String parentNode;
	public int versionNo;
	public int relationshipBuilderSid;
	public long modifiedDate;
	public int createdBy;
	public long createdDate;
	public String levelNo;
	public String actionFlag;
	public String hierarchyNo;
	public int modifiedBy;
	public int relationshipLevelSid;
	public String flag;
	public String levelName;
	public transient HistRelationshipLevelDefnPK histRelationshipLevelDefnPK;
}