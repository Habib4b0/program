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

import com.stpl.app.model.CustomViewDetails;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing CustomViewDetails in entity cache.
 *
 * @author
 * @see CustomViewDetails
 * @generated
 */
@ProviderType
public class CustomViewDetailsCacheModel implements CacheModel<CustomViewDetails>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CustomViewDetailsCacheModel)) {
			return false;
		}

		CustomViewDetailsCacheModel customViewDetailsCacheModel = (CustomViewDetailsCacheModel)obj;

		if (customViewDetailsSid == customViewDetailsCacheModel.customViewDetailsSid) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, customViewDetailsSid);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(11);

		sb.append("{hierarchyId=");
		sb.append(hierarchyId);
		sb.append(", hierarchyIndicator=");
		sb.append(hierarchyIndicator);
		sb.append(", customViewMasterSid=");
		sb.append(customViewMasterSid);
		sb.append(", customViewDetailsSid=");
		sb.append(customViewDetailsSid);
		sb.append(", levelNo=");
		sb.append(levelNo);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public CustomViewDetails toEntityModel() {
		CustomViewDetailsImpl customViewDetailsImpl = new CustomViewDetailsImpl();

		customViewDetailsImpl.setHierarchyId(hierarchyId);

		if (hierarchyIndicator == null) {
			customViewDetailsImpl.setHierarchyIndicator(StringPool.BLANK);
		}
		else {
			customViewDetailsImpl.setHierarchyIndicator(hierarchyIndicator);
		}

		customViewDetailsImpl.setCustomViewMasterSid(customViewMasterSid);
		customViewDetailsImpl.setCustomViewDetailsSid(customViewDetailsSid);
		customViewDetailsImpl.setLevelNo(levelNo);

		customViewDetailsImpl.resetOriginalValues();

		return customViewDetailsImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		hierarchyId = objectInput.readInt();
		hierarchyIndicator = objectInput.readUTF();

		customViewMasterSid = objectInput.readInt();

		customViewDetailsSid = objectInput.readInt();

		levelNo = objectInput.readInt();
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

		objectOutput.writeInt(customViewMasterSid);

		objectOutput.writeInt(customViewDetailsSid);

		objectOutput.writeInt(levelNo);
	}

	public int hierarchyId;
	public String hierarchyIndicator;
	public int customViewMasterSid;
	public int customViewDetailsSid;
	public int levelNo;
}