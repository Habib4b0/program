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

import com.stpl.app.model.HierarchyLevelValues;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing HierarchyLevelValues in entity cache.
 *
 * @author
 * @see HierarchyLevelValues
 * @generated
 */
@ProviderType
public class HierarchyLevelValuesCacheModel implements CacheModel<HierarchyLevelValues>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof HierarchyLevelValuesCacheModel)) {
			return false;
		}

		HierarchyLevelValuesCacheModel hierarchyLevelValuesCacheModel = (HierarchyLevelValuesCacheModel)obj;

		if (hierarchyLevelValuesSid == hierarchyLevelValuesCacheModel.hierarchyLevelValuesSid) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, hierarchyLevelValuesSid);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(17);

		sb.append("{levelValues=");
		sb.append(levelValues);
		sb.append(", hierarchyLevelValuesSid=");
		sb.append(hierarchyLevelValuesSid);
		sb.append(", createdDate=");
		sb.append(createdDate);
		sb.append(", createdBy=");
		sb.append(createdBy);
		sb.append(", hierarchyLevelDefinitionSid=");
		sb.append(hierarchyLevelDefinitionSid);
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
	public HierarchyLevelValues toEntityModel() {
		HierarchyLevelValuesImpl hierarchyLevelValuesImpl = new HierarchyLevelValuesImpl();

		if (levelValues == null) {
			hierarchyLevelValuesImpl.setLevelValues(StringPool.BLANK);
		}
		else {
			hierarchyLevelValuesImpl.setLevelValues(levelValues);
		}

		hierarchyLevelValuesImpl.setHierarchyLevelValuesSid(hierarchyLevelValuesSid);

		if (createdDate == Long.MIN_VALUE) {
			hierarchyLevelValuesImpl.setCreatedDate(null);
		}
		else {
			hierarchyLevelValuesImpl.setCreatedDate(new Date(createdDate));
		}

		hierarchyLevelValuesImpl.setCreatedBy(createdBy);
		hierarchyLevelValuesImpl.setHierarchyLevelDefinitionSid(hierarchyLevelDefinitionSid);
		hierarchyLevelValuesImpl.setVersionNo(versionNo);
		hierarchyLevelValuesImpl.setModifiedBy(modifiedBy);

		if (modifiedDate == Long.MIN_VALUE) {
			hierarchyLevelValuesImpl.setModifiedDate(null);
		}
		else {
			hierarchyLevelValuesImpl.setModifiedDate(new Date(modifiedDate));
		}

		hierarchyLevelValuesImpl.resetOriginalValues();

		return hierarchyLevelValuesImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		levelValues = objectInput.readUTF();

		hierarchyLevelValuesSid = objectInput.readInt();
		createdDate = objectInput.readLong();

		createdBy = objectInput.readInt();

		hierarchyLevelDefinitionSid = objectInput.readInt();

		versionNo = objectInput.readInt();

		modifiedBy = objectInput.readInt();
		modifiedDate = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		if (levelValues == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(levelValues);
		}

		objectOutput.writeInt(hierarchyLevelValuesSid);
		objectOutput.writeLong(createdDate);

		objectOutput.writeInt(createdBy);

		objectOutput.writeInt(hierarchyLevelDefinitionSid);

		objectOutput.writeInt(versionNo);

		objectOutput.writeInt(modifiedBy);
		objectOutput.writeLong(modifiedDate);
	}

	public String levelValues;
	public int hierarchyLevelValuesSid;
	public long createdDate;
	public int createdBy;
	public int hierarchyLevelDefinitionSid;
	public int versionNo;
	public int modifiedBy;
	public long modifiedDate;
}