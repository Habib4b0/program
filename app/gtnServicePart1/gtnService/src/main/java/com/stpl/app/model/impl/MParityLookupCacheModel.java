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

import com.stpl.app.model.MParityLookup;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing MParityLookup in entity cache.
 *
 * @author
 * @see MParityLookup
 * @generated
 */
@ProviderType
public class MParityLookupCacheModel implements CacheModel<MParityLookup>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof MParityLookupCacheModel)) {
			return false;
		}

		MParityLookupCacheModel mParityLookupCacheModel = (MParityLookupCacheModel)obj;

		if (mParityLookupSid == mParityLookupCacheModel.mParityLookupSid) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, mParityLookupSid);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(11);

		sb.append("{contractMasterSid=");
		sb.append(contractMasterSid);
		sb.append(", marketType=");
		sb.append(marketType);
		sb.append(", itemMasterSid=");
		sb.append(itemMasterSid);
		sb.append(", mParityLookupSid=");
		sb.append(mParityLookupSid);
		sb.append(", projectionDetailsSid=");
		sb.append(projectionDetailsSid);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public MParityLookup toEntityModel() {
		MParityLookupImpl mParityLookupImpl = new MParityLookupImpl();

		mParityLookupImpl.setContractMasterSid(contractMasterSid);

		if (marketType == null) {
			mParityLookupImpl.setMarketType(StringPool.BLANK);
		}
		else {
			mParityLookupImpl.setMarketType(marketType);
		}

		mParityLookupImpl.setItemMasterSid(itemMasterSid);
		mParityLookupImpl.setMParityLookupSid(mParityLookupSid);
		mParityLookupImpl.setProjectionDetailsSid(projectionDetailsSid);

		mParityLookupImpl.resetOriginalValues();

		return mParityLookupImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		contractMasterSid = objectInput.readInt();
		marketType = objectInput.readUTF();

		itemMasterSid = objectInput.readInt();

		mParityLookupSid = objectInput.readInt();

		projectionDetailsSid = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeInt(contractMasterSid);

		if (marketType == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(marketType);
		}

		objectOutput.writeInt(itemMasterSid);

		objectOutput.writeInt(mParityLookupSid);

		objectOutput.writeInt(projectionDetailsSid);
	}

	public int contractMasterSid;
	public String marketType;
	public int itemMasterSid;
	public int mParityLookupSid;
	public int projectionDetailsSid;
}