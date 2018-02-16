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

import com.stpl.app.model.HistHierarchyLevelValues;
import com.stpl.app.service.persistence.HistHierarchyLevelValuesPK;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing HistHierarchyLevelValues in entity cache.
 *
 * @author
 * @see HistHierarchyLevelValues
 * @generated
 */
@ProviderType
public class HistHierarchyLevelValuesCacheModel implements CacheModel<HistHierarchyLevelValues>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof HistHierarchyLevelValuesCacheModel)) {
			return false;
		}

		HistHierarchyLevelValuesCacheModel histHierarchyLevelValuesCacheModel = (HistHierarchyLevelValuesCacheModel)obj;

		if (histHierarchyLevelValuesPK.equals(
					histHierarchyLevelValuesCacheModel.histHierarchyLevelValuesPK)) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, histHierarchyLevelValuesPK);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(21);

		sb.append("{levelValues=");
		sb.append(levelValues);
		sb.append(", hierarchyLevelValuesSid=");
		sb.append(hierarchyLevelValuesSid);
		sb.append(", createdDate=");
		sb.append(createdDate);
		sb.append(", createdBy=");
		sb.append(createdBy);
		sb.append(", actionDate=");
		sb.append(actionDate);
		sb.append(", actionFlag=");
		sb.append(actionFlag);
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
	public HistHierarchyLevelValues toEntityModel() {
		HistHierarchyLevelValuesImpl histHierarchyLevelValuesImpl = new HistHierarchyLevelValuesImpl();

		if (levelValues == null) {
			histHierarchyLevelValuesImpl.setLevelValues(StringPool.BLANK);
		}
		else {
			histHierarchyLevelValuesImpl.setLevelValues(levelValues);
		}

		histHierarchyLevelValuesImpl.setHierarchyLevelValuesSid(hierarchyLevelValuesSid);

		if (createdDate == Long.MIN_VALUE) {
			histHierarchyLevelValuesImpl.setCreatedDate(null);
		}
		else {
			histHierarchyLevelValuesImpl.setCreatedDate(new Date(createdDate));
		}

		histHierarchyLevelValuesImpl.setCreatedBy(createdBy);

		if (actionDate == Long.MIN_VALUE) {
			histHierarchyLevelValuesImpl.setActionDate(null);
		}
		else {
			histHierarchyLevelValuesImpl.setActionDate(new Date(actionDate));
		}

		if (actionFlag == null) {
			histHierarchyLevelValuesImpl.setActionFlag(StringPool.BLANK);
		}
		else {
			histHierarchyLevelValuesImpl.setActionFlag(actionFlag);
		}

		histHierarchyLevelValuesImpl.setHierarchyLevelDefinitionSid(hierarchyLevelDefinitionSid);
		histHierarchyLevelValuesImpl.setVersionNo(versionNo);
		histHierarchyLevelValuesImpl.setModifiedBy(modifiedBy);

		if (modifiedDate == Long.MIN_VALUE) {
			histHierarchyLevelValuesImpl.setModifiedDate(null);
		}
		else {
			histHierarchyLevelValuesImpl.setModifiedDate(new Date(modifiedDate));
		}

		histHierarchyLevelValuesImpl.resetOriginalValues();

		return histHierarchyLevelValuesImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		levelValues = objectInput.readUTF();

		hierarchyLevelValuesSid = objectInput.readInt();
		createdDate = objectInput.readLong();

		createdBy = objectInput.readInt();
		actionDate = objectInput.readLong();
		actionFlag = objectInput.readUTF();

		hierarchyLevelDefinitionSid = objectInput.readInt();

		versionNo = objectInput.readInt();

		modifiedBy = objectInput.readInt();
		modifiedDate = objectInput.readLong();

		histHierarchyLevelValuesPK = new HistHierarchyLevelValuesPK(hierarchyLevelValuesSid,
				actionFlag, versionNo);
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
		objectOutput.writeLong(actionDate);

		if (actionFlag == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(actionFlag);
		}

		objectOutput.writeInt(hierarchyLevelDefinitionSid);

		objectOutput.writeInt(versionNo);

		objectOutput.writeInt(modifiedBy);
		objectOutput.writeLong(modifiedDate);
	}

	public String levelValues;
	public int hierarchyLevelValuesSid;
	public long createdDate;
	public int createdBy;
	public long actionDate;
	public String actionFlag;
	public int hierarchyLevelDefinitionSid;
	public int versionNo;
	public int modifiedBy;
	public long modifiedDate;
	public transient HistHierarchyLevelValuesPK histHierarchyLevelValuesPK;
}