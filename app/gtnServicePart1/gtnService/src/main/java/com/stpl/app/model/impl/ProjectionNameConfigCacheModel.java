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

import com.stpl.app.model.ProjectionNameConfig;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing ProjectionNameConfig in entity cache.
 *
 * @author
 * @see ProjectionNameConfig
 * @generated
 */
@ProviderType
public class ProjectionNameConfigCacheModel implements CacheModel<ProjectionNameConfig>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ProjectionNameConfigCacheModel)) {
			return false;
		}

		ProjectionNameConfigCacheModel projectionNameConfigCacheModel = (ProjectionNameConfigCacheModel)obj;

		if (projectionNameConfigSid == projectionNameConfigCacheModel.projectionNameConfigSid) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, projectionNameConfigSid);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(17);

		sb.append("{createdDate=");
		sb.append(createdDate);
		sb.append(", createdBy=");
		sb.append(createdBy);
		sb.append(", businessProcessType=");
		sb.append(businessProcessType);
		sb.append(", versionNo=");
		sb.append(versionNo);
		sb.append(", modifiedBy=");
		sb.append(modifiedBy);
		sb.append(", projectionNameConfigSid=");
		sb.append(projectionNameConfigSid);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", selectedAttributes=");
		sb.append(selectedAttributes);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ProjectionNameConfig toEntityModel() {
		ProjectionNameConfigImpl projectionNameConfigImpl = new ProjectionNameConfigImpl();

		if (createdDate == Long.MIN_VALUE) {
			projectionNameConfigImpl.setCreatedDate(null);
		}
		else {
			projectionNameConfigImpl.setCreatedDate(new Date(createdDate));
		}

		projectionNameConfigImpl.setCreatedBy(createdBy);

		if (businessProcessType == null) {
			projectionNameConfigImpl.setBusinessProcessType(StringPool.BLANK);
		}
		else {
			projectionNameConfigImpl.setBusinessProcessType(businessProcessType);
		}

		projectionNameConfigImpl.setVersionNo(versionNo);
		projectionNameConfigImpl.setModifiedBy(modifiedBy);
		projectionNameConfigImpl.setProjectionNameConfigSid(projectionNameConfigSid);

		if (modifiedDate == Long.MIN_VALUE) {
			projectionNameConfigImpl.setModifiedDate(null);
		}
		else {
			projectionNameConfigImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (selectedAttributes == null) {
			projectionNameConfigImpl.setSelectedAttributes(StringPool.BLANK);
		}
		else {
			projectionNameConfigImpl.setSelectedAttributes(selectedAttributes);
		}

		projectionNameConfigImpl.resetOriginalValues();

		return projectionNameConfigImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		createdDate = objectInput.readLong();

		createdBy = objectInput.readInt();
		businessProcessType = objectInput.readUTF();

		versionNo = objectInput.readInt();

		modifiedBy = objectInput.readInt();

		projectionNameConfigSid = objectInput.readInt();
		modifiedDate = objectInput.readLong();
		selectedAttributes = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(createdDate);

		objectOutput.writeInt(createdBy);

		if (businessProcessType == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(businessProcessType);
		}

		objectOutput.writeInt(versionNo);

		objectOutput.writeInt(modifiedBy);

		objectOutput.writeInt(projectionNameConfigSid);
		objectOutput.writeLong(modifiedDate);

		if (selectedAttributes == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(selectedAttributes);
		}
	}

	public long createdDate;
	public int createdBy;
	public String businessProcessType;
	public int versionNo;
	public int modifiedBy;
	public int projectionNameConfigSid;
	public long modifiedDate;
	public String selectedAttributes;
}