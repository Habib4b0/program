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

import com.stpl.app.model.ChProjectionSelection;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing ChProjectionSelection in entity cache.
 *
 * @author
 * @see ChProjectionSelection
 * @generated
 */
@ProviderType
public class ChProjectionSelectionCacheModel implements CacheModel<ChProjectionSelection>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ChProjectionSelectionCacheModel)) {
			return false;
		}

		ChProjectionSelectionCacheModel chProjectionSelectionCacheModel = (ChProjectionSelectionCacheModel)obj;

		if (chProjectionSelectionSid == chProjectionSelectionCacheModel.chProjectionSelectionSid) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, chProjectionSelectionSid);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(11);

		sb.append("{screenName=");
		sb.append(screenName);
		sb.append(", fieldName=");
		sb.append(fieldName);
		sb.append(", chProjectionSelectionSid=");
		sb.append(chProjectionSelectionSid);
		sb.append(", fieldValues=");
		sb.append(fieldValues);
		sb.append(", projectionMasterSid=");
		sb.append(projectionMasterSid);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ChProjectionSelection toEntityModel() {
		ChProjectionSelectionImpl chProjectionSelectionImpl = new ChProjectionSelectionImpl();

		if (screenName == null) {
			chProjectionSelectionImpl.setScreenName(StringPool.BLANK);
		}
		else {
			chProjectionSelectionImpl.setScreenName(screenName);
		}

		if (fieldName == null) {
			chProjectionSelectionImpl.setFieldName(StringPool.BLANK);
		}
		else {
			chProjectionSelectionImpl.setFieldName(fieldName);
		}

		chProjectionSelectionImpl.setChProjectionSelectionSid(chProjectionSelectionSid);

		if (fieldValues == null) {
			chProjectionSelectionImpl.setFieldValues(StringPool.BLANK);
		}
		else {
			chProjectionSelectionImpl.setFieldValues(fieldValues);
		}

		chProjectionSelectionImpl.setProjectionMasterSid(projectionMasterSid);

		chProjectionSelectionImpl.resetOriginalValues();

		return chProjectionSelectionImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		screenName = objectInput.readUTF();
		fieldName = objectInput.readUTF();

		chProjectionSelectionSid = objectInput.readInt();
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

		if (fieldName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(fieldName);
		}

		objectOutput.writeInt(chProjectionSelectionSid);

		if (fieldValues == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(fieldValues);
		}

		objectOutput.writeInt(projectionMasterSid);
	}

	public String screenName;
	public String fieldName;
	public int chProjectionSelectionSid;
	public String fieldValues;
	public int projectionMasterSid;
}