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

import com.stpl.app.model.ProjectionProdHierarchy;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing ProjectionProdHierarchy in entity cache.
 *
 * @author
 * @see ProjectionProdHierarchy
 * @generated
 */
@ProviderType
public class ProjectionProdHierarchyCacheModel implements CacheModel<ProjectionProdHierarchy>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ProjectionProdHierarchyCacheModel)) {
			return false;
		}

		ProjectionProdHierarchyCacheModel projectionProdHierarchyCacheModel = (ProjectionProdHierarchyCacheModel)obj;

		if (projectionProdHierarchySid == projectionProdHierarchyCacheModel.projectionProdHierarchySid) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, projectionProdHierarchySid);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(7);

		sb.append("{projectionMasterSid=");
		sb.append(projectionMasterSid);
		sb.append(", projectionProdHierarchySid=");
		sb.append(projectionProdHierarchySid);
		sb.append(", relationshipLevelSid=");
		sb.append(relationshipLevelSid);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ProjectionProdHierarchy toEntityModel() {
		ProjectionProdHierarchyImpl projectionProdHierarchyImpl = new ProjectionProdHierarchyImpl();

		projectionProdHierarchyImpl.setProjectionMasterSid(projectionMasterSid);
		projectionProdHierarchyImpl.setProjectionProdHierarchySid(projectionProdHierarchySid);
		projectionProdHierarchyImpl.setRelationshipLevelSid(relationshipLevelSid);

		projectionProdHierarchyImpl.resetOriginalValues();

		return projectionProdHierarchyImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		projectionMasterSid = objectInput.readInt();

		projectionProdHierarchySid = objectInput.readInt();

		relationshipLevelSid = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeInt(projectionMasterSid);

		objectOutput.writeInt(projectionProdHierarchySid);

		objectOutput.writeInt(relationshipLevelSid);
	}

	public int projectionMasterSid;
	public int projectionProdHierarchySid;
	public int relationshipLevelSid;
}