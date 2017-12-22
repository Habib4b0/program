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
import com.liferay.portal.kernel.util.StringPool;

import com.stpl.app.parttwo.model.CffCustomViewDetails;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing CffCustomViewDetails in entity cache.
 *
 * @author
 * @see CffCustomViewDetails
 * @generated
 */
@ProviderType
public class CffCustomViewDetailsCacheModel implements CacheModel<CffCustomViewDetails>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CffCustomViewDetailsCacheModel)) {
			return false;
		}

		CffCustomViewDetailsCacheModel cffCustomViewDetailsCacheModel = (CffCustomViewDetailsCacheModel)obj;

		if (cffCustomViewDetailsSid == cffCustomViewDetailsCacheModel.cffCustomViewDetailsSid) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, cffCustomViewDetailsSid);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(11);

		sb.append("{hierarchyId=");
		sb.append(hierarchyId);
		sb.append(", hierarchyIndicator=");
		sb.append(hierarchyIndicator);
		sb.append(", cffCustomViewDetailsSid=");
		sb.append(cffCustomViewDetailsSid);
		sb.append(", levelNo=");
		sb.append(levelNo);
		sb.append(", cffCustomViewMasterSid=");
		sb.append(cffCustomViewMasterSid);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public CffCustomViewDetails toEntityModel() {
		CffCustomViewDetailsImpl cffCustomViewDetailsImpl = new CffCustomViewDetailsImpl();

		cffCustomViewDetailsImpl.setHierarchyId(hierarchyId);

		if (hierarchyIndicator == null) {
			cffCustomViewDetailsImpl.setHierarchyIndicator(StringPool.BLANK);
		}
		else {
			cffCustomViewDetailsImpl.setHierarchyIndicator(hierarchyIndicator);
		}

		cffCustomViewDetailsImpl.setCffCustomViewDetailsSid(cffCustomViewDetailsSid);
		cffCustomViewDetailsImpl.setLevelNo(levelNo);
		cffCustomViewDetailsImpl.setCffCustomViewMasterSid(cffCustomViewMasterSid);

		cffCustomViewDetailsImpl.resetOriginalValues();

		return cffCustomViewDetailsImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		hierarchyId = objectInput.readInt();
		hierarchyIndicator = objectInput.readUTF();

		cffCustomViewDetailsSid = objectInput.readInt();

		levelNo = objectInput.readInt();

		cffCustomViewMasterSid = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeInt(hierarchyId);

		if (hierarchyIndicator == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(hierarchyIndicator);
		}

		objectOutput.writeInt(cffCustomViewDetailsSid);

		objectOutput.writeInt(levelNo);

		objectOutput.writeInt(cffCustomViewMasterSid);
	}

	public int hierarchyId;
	public String hierarchyIndicator;
	public int cffCustomViewDetailsSid;
	public int levelNo;
	public int cffCustomViewMasterSid;
}