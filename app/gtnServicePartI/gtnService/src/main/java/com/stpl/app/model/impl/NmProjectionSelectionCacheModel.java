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

import com.stpl.app.model.NmProjectionSelection;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing NmProjectionSelection in entity cache.
 *
 * @author
 * @see NmProjectionSelection
 * @generated
 */
@ProviderType
public class NmProjectionSelectionCacheModel implements CacheModel<NmProjectionSelection>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof NmProjectionSelectionCacheModel)) {
			return false;
		}

		NmProjectionSelectionCacheModel nmProjectionSelectionCacheModel = (NmProjectionSelectionCacheModel)obj;

		if (nmProjectionSelectionSid == nmProjectionSelectionCacheModel.nmProjectionSelectionSid) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, nmProjectionSelectionSid);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(11);

		sb.append("{screenName=");
		sb.append(screenName);
		sb.append(", nmProjectionSelectionSid=");
		sb.append(nmProjectionSelectionSid);
		sb.append(", fieldName=");
		sb.append(fieldName);
		sb.append(", fieldValues=");
		sb.append(fieldValues);
		sb.append(", projectionMasterSid=");
		sb.append(projectionMasterSid);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public NmProjectionSelection toEntityModel() {
		NmProjectionSelectionImpl nmProjectionSelectionImpl = new NmProjectionSelectionImpl();

		if (screenName == null) {
			nmProjectionSelectionImpl.setScreenName(StringPool.BLANK);
		}
		else {
			nmProjectionSelectionImpl.setScreenName(screenName);
		}

		nmProjectionSelectionImpl.setNmProjectionSelectionSid(nmProjectionSelectionSid);

		if (fieldName == null) {
			nmProjectionSelectionImpl.setFieldName(StringPool.BLANK);
		}
		else {
			nmProjectionSelectionImpl.setFieldName(fieldName);
		}

		if (fieldValues == null) {
			nmProjectionSelectionImpl.setFieldValues(StringPool.BLANK);
		}
		else {
			nmProjectionSelectionImpl.setFieldValues(fieldValues);
		}

		nmProjectionSelectionImpl.setProjectionMasterSid(projectionMasterSid);

		nmProjectionSelectionImpl.resetOriginalValues();

		return nmProjectionSelectionImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		screenName = objectInput.readUTF();

		nmProjectionSelectionSid = objectInput.readInt();
		fieldName = objectInput.readUTF();
		fieldValues = objectInput.readUTF();

		projectionMasterSid = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		if (screenName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(screenName);
		}

		objectOutput.writeInt(nmProjectionSelectionSid);

		if (fieldName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(fieldName);
		}

		if (fieldValues == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(fieldValues);
		}

		objectOutput.writeInt(projectionMasterSid);
	}

	public String screenName;
	public int nmProjectionSelectionSid;
	public String fieldName;
	public String fieldValues;
	public int projectionMasterSid;
}