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

import com.stpl.app.model.NaProjectionSelection;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing NaProjectionSelection in entity cache.
 *
 * @author
 * @see NaProjectionSelection
 * @generated
 */
@ProviderType
public class NaProjectionSelectionCacheModel implements CacheModel<NaProjectionSelection>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof NaProjectionSelectionCacheModel)) {
			return false;
		}

		NaProjectionSelectionCacheModel naProjectionSelectionCacheModel = (NaProjectionSelectionCacheModel)obj;

		if (naProjectionSelectionSid == naProjectionSelectionCacheModel.naProjectionSelectionSid) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, naProjectionSelectionSid);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(11);

		sb.append("{screenName=");
		sb.append(screenName);
		sb.append(", fieldName=");
		sb.append(fieldName);
		sb.append(", fieldValues=");
		sb.append(fieldValues);
		sb.append(", naProjectionSelectionSid=");
		sb.append(naProjectionSelectionSid);
		sb.append(", naProjMasterSid=");
		sb.append(naProjMasterSid);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public NaProjectionSelection toEntityModel() {
		NaProjectionSelectionImpl naProjectionSelectionImpl = new NaProjectionSelectionImpl();

		if (screenName == null) {
			naProjectionSelectionImpl.setScreenName(StringPool.BLANK);
		}
		else {
			naProjectionSelectionImpl.setScreenName(screenName);
		}

		if (fieldName == null) {
			naProjectionSelectionImpl.setFieldName(StringPool.BLANK);
		}
		else {
			naProjectionSelectionImpl.setFieldName(fieldName);
		}

		if (fieldValues == null) {
			naProjectionSelectionImpl.setFieldValues(StringPool.BLANK);
		}
		else {
			naProjectionSelectionImpl.setFieldValues(fieldValues);
		}

		naProjectionSelectionImpl.setNaProjectionSelectionSid(naProjectionSelectionSid);
		naProjectionSelectionImpl.setNaProjMasterSid(naProjMasterSid);

		naProjectionSelectionImpl.resetOriginalValues();

		return naProjectionSelectionImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		screenName = objectInput.readUTF();
		fieldName = objectInput.readUTF();
		fieldValues = objectInput.readUTF();

		naProjectionSelectionSid = objectInput.readInt();

		naProjMasterSid = objectInput.readInt();
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

		objectOutput.writeInt(naProjectionSelectionSid);

		objectOutput.writeInt(naProjMasterSid);
	}

	public String screenName;
	public String fieldName;
	public String fieldValues;
	public int naProjectionSelectionSid;
	public int naProjMasterSid;
}