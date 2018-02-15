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

import com.stpl.app.model.CcpMap;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing CcpMap in entity cache.
 *
 * @author
 * @see CcpMap
 * @generated
 */
@ProviderType
public class CcpMapCacheModel implements CacheModel<CcpMap>, Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CcpMapCacheModel)) {
			return false;
		}

		CcpMapCacheModel ccpMapCacheModel = (CcpMapCacheModel)obj;

		if (ccpMapSid == ccpMapCacheModel.ccpMapSid) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, ccpMapSid);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(7);

		sb.append("{ccpDetailsSid=");
		sb.append(ccpDetailsSid);
		sb.append(", relationshipLevelSid=");
		sb.append(relationshipLevelSid);
		sb.append(", ccpMapSid=");
		sb.append(ccpMapSid);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public CcpMap toEntityModel() {
		CcpMapImpl ccpMapImpl = new CcpMapImpl();

		ccpMapImpl.setCcpDetailsSid(ccpDetailsSid);
		ccpMapImpl.setRelationshipLevelSid(relationshipLevelSid);
		ccpMapImpl.setCcpMapSid(ccpMapSid);

		ccpMapImpl.resetOriginalValues();

		return ccpMapImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		ccpDetailsSid = objectInput.readInt();

		relationshipLevelSid = objectInput.readInt();

		ccpMapSid = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeInt(ccpDetailsSid);

		objectOutput.writeInt(relationshipLevelSid);

		objectOutput.writeInt(ccpMapSid);
	}

	public int ccpDetailsSid;
	public int relationshipLevelSid;
	public int ccpMapSid;
}