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

import com.stpl.app.model.MProjectionSelection;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing MProjectionSelection in entity cache.
 *
 * @author
 * @see MProjectionSelection
 * @generated
 */
@ProviderType
public class MProjectionSelectionCacheModel implements CacheModel<MProjectionSelection>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof MProjectionSelectionCacheModel)) {
			return false;
		}

		MProjectionSelectionCacheModel mProjectionSelectionCacheModel = (MProjectionSelectionCacheModel)obj;

		if (mProjectionSelectionSid == mProjectionSelectionCacheModel.mProjectionSelectionSid) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, mProjectionSelectionSid);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(11);

		sb.append("{mProjectionSelectionSid=");
		sb.append(mProjectionSelectionSid);
		sb.append(", projectionMasterSid=");
		sb.append(projectionMasterSid);
		sb.append(", fieldValues=");
		sb.append(fieldValues);
		sb.append(", fieldName=");
		sb.append(fieldName);
		sb.append(", screenName=");
		sb.append(screenName);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public MProjectionSelection toEntityModel() {
		MProjectionSelectionImpl mProjectionSelectionImpl = new MProjectionSelectionImpl();

		mProjectionSelectionImpl.setMProjectionSelectionSid(mProjectionSelectionSid);
		mProjectionSelectionImpl.setProjectionMasterSid(projectionMasterSid);

		if (fieldValues == null) {
			mProjectionSelectionImpl.setFieldValues(StringPool.BLANK);
		}
		else {
			mProjectionSelectionImpl.setFieldValues(fieldValues);
		}

		if (fieldName == null) {
			mProjectionSelectionImpl.setFieldName(StringPool.BLANK);
		}
		else {
			mProjectionSelectionImpl.setFieldName(fieldName);
		}

		if (screenName == null) {
			mProjectionSelectionImpl.setScreenName(StringPool.BLANK);
		}
		else {
			mProjectionSelectionImpl.setScreenName(screenName);
		}

		mProjectionSelectionImpl.resetOriginalValues();

		return mProjectionSelectionImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		mProjectionSelectionSid = objectInput.readInt();

		projectionMasterSid = objectInput.readInt();
		fieldValues = objectInput.readUTF();
		fieldName = objectInput.readUTF();
		screenName = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeInt(mProjectionSelectionSid);

		objectOutput.writeInt(projectionMasterSid);

		if (fieldValues == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(fieldValues);
		}

		if (fieldName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(fieldName);
		}

		if (screenName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(screenName);
		}
	}

	public int mProjectionSelectionSid;
	public int projectionMasterSid;
	public String fieldValues;
	public String fieldName;
	public String screenName;
}