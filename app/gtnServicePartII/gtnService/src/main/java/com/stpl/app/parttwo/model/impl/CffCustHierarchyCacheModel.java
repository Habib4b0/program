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

import com.stpl.app.parttwo.model.CffCustHierarchy;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing CffCustHierarchy in entity cache.
 *
 * @author
 * @see CffCustHierarchy
 * @generated
 */
@ProviderType
public class CffCustHierarchyCacheModel implements CacheModel<CffCustHierarchy>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CffCustHierarchyCacheModel)) {
			return false;
		}

		CffCustHierarchyCacheModel cffCustHierarchyCacheModel = (CffCustHierarchyCacheModel)obj;

		if (cffCustHierarchySid == cffCustHierarchyCacheModel.cffCustHierarchySid) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, cffCustHierarchySid);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(7);

		sb.append("{cffCustHierarchySid=");
		sb.append(cffCustHierarchySid);
		sb.append(", cffMasterSid=");
		sb.append(cffMasterSid);
		sb.append(", relationshipLevelSid=");
		sb.append(relationshipLevelSid);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public CffCustHierarchy toEntityModel() {
		CffCustHierarchyImpl cffCustHierarchyImpl = new CffCustHierarchyImpl();

		cffCustHierarchyImpl.setCffCustHierarchySid(cffCustHierarchySid);
		cffCustHierarchyImpl.setCffMasterSid(cffMasterSid);
		cffCustHierarchyImpl.setRelationshipLevelSid(relationshipLevelSid);

		cffCustHierarchyImpl.resetOriginalValues();

		return cffCustHierarchyImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		cffCustHierarchySid = objectInput.readInt();

		cffMasterSid = objectInput.readInt();

		relationshipLevelSid = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeInt(cffCustHierarchySid);

		objectOutput.writeInt(cffMasterSid);

		objectOutput.writeInt(relationshipLevelSid);
	}

	public int cffCustHierarchySid;
	public int cffMasterSid;
	public int relationshipLevelSid;
}