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

package com.stpl.app.parttwo.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import com.stpl.app.parttwo.model.CffProdHierarchy;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing CffProdHierarchy in entity cache.
 *
 * @author
 * @see CffProdHierarchy
 * @generated
 */
@ProviderType
public class CffProdHierarchyCacheModel implements CacheModel<CffProdHierarchy>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CffProdHierarchyCacheModel)) {
			return false;
		}

		CffProdHierarchyCacheModel cffProdHierarchyCacheModel = (CffProdHierarchyCacheModel)obj;

		if (cffProdHierarchySid == cffProdHierarchyCacheModel.cffProdHierarchySid) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, cffProdHierarchySid);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(7);

		sb.append("{cffMasterSid=");
		sb.append(cffMasterSid);
		sb.append(", relationshipLevelSid=");
		sb.append(relationshipLevelSid);
		sb.append(", cffProdHierarchySid=");
		sb.append(cffProdHierarchySid);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public CffProdHierarchy toEntityModel() {
		CffProdHierarchyImpl cffProdHierarchyImpl = new CffProdHierarchyImpl();

		cffProdHierarchyImpl.setCffMasterSid(cffMasterSid);
		cffProdHierarchyImpl.setRelationshipLevelSid(relationshipLevelSid);
		cffProdHierarchyImpl.setCffProdHierarchySid(cffProdHierarchySid);

		cffProdHierarchyImpl.resetOriginalValues();

		return cffProdHierarchyImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		cffMasterSid = objectInput.readInt();

		relationshipLevelSid = objectInput.readInt();

		cffProdHierarchySid = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeInt(cffMasterSid);

		objectOutput.writeInt(relationshipLevelSid);

		objectOutput.writeInt(cffProdHierarchySid);
	}

	public int cffMasterSid;
	public int relationshipLevelSid;
	public int cffProdHierarchySid;
}