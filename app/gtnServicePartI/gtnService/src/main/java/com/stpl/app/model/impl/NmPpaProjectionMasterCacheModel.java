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

import com.stpl.app.model.NmPpaProjectionMaster;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing NmPpaProjectionMaster in entity cache.
 *
 * @author
 * @see NmPpaProjectionMaster
 * @generated
 */
@ProviderType
public class NmPpaProjectionMasterCacheModel implements CacheModel<NmPpaProjectionMaster>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof NmPpaProjectionMasterCacheModel)) {
			return false;
		}

		NmPpaProjectionMasterCacheModel nmPpaProjectionMasterCacheModel = (NmPpaProjectionMasterCacheModel)obj;

		if (projectionDetailsSid == nmPpaProjectionMasterCacheModel.projectionDetailsSid) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, projectionDetailsSid);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(15);

		sb.append("{checkRecord=");
		sb.append(checkRecord);
		sb.append(", userGroup=");
		sb.append(userGroup);
		sb.append(", projectionDetailsSid=");
		sb.append(projectionDetailsSid);
		sb.append(", priceBasis=");
		sb.append(priceBasis);
		sb.append(", priceProtectionEndDate=");
		sb.append(priceProtectionEndDate);
		sb.append(", priceProtectionStartDate=");
		sb.append(priceProtectionStartDate);
		sb.append(", actualPriceCap=");
		sb.append(actualPriceCap);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public NmPpaProjectionMaster toEntityModel() {
		NmPpaProjectionMasterImpl nmPpaProjectionMasterImpl = new NmPpaProjectionMasterImpl();

		nmPpaProjectionMasterImpl.setCheckRecord(checkRecord);

		if (userGroup == null) {
			nmPpaProjectionMasterImpl.setUserGroup(StringPool.BLANK);
		}
		else {
			nmPpaProjectionMasterImpl.setUserGroup(userGroup);
		}

		nmPpaProjectionMasterImpl.setProjectionDetailsSid(projectionDetailsSid);

		if (priceBasis == null) {
			nmPpaProjectionMasterImpl.setPriceBasis(StringPool.BLANK);
		}
		else {
			nmPpaProjectionMasterImpl.setPriceBasis(priceBasis);
		}

		if (priceProtectionEndDate == Long.MIN_VALUE) {
			nmPpaProjectionMasterImpl.setPriceProtectionEndDate(null);
		}
		else {
			nmPpaProjectionMasterImpl.setPriceProtectionEndDate(new Date(
					priceProtectionEndDate));
		}

		if (priceProtectionStartDate == Long.MIN_VALUE) {
			nmPpaProjectionMasterImpl.setPriceProtectionStartDate(null);
		}
		else {
			nmPpaProjectionMasterImpl.setPriceProtectionStartDate(new Date(
					priceProtectionStartDate));
		}

		nmPpaProjectionMasterImpl.setActualPriceCap(actualPriceCap);

		nmPpaProjectionMasterImpl.resetOriginalValues();

		return nmPpaProjectionMasterImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		checkRecord = objectInput.readBoolean();
		userGroup = objectInput.readUTF();

		projectionDetailsSid = objectInput.readInt();
		priceBasis = objectInput.readUTF();
		priceProtectionEndDate = objectInput.readLong();
		priceProtectionStartDate = objectInput.readLong();

		actualPriceCap = objectInput.readDouble();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeBoolean(checkRecord);

		if (userGroup == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(userGroup);
		}

		objectOutput.writeInt(projectionDetailsSid);

		if (priceBasis == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(priceBasis);
		}

		objectOutput.writeLong(priceProtectionEndDate);
		objectOutput.writeLong(priceProtectionStartDate);

		objectOutput.writeDouble(actualPriceCap);
	}

	public boolean checkRecord;
	public String userGroup;
	public int projectionDetailsSid;
	public String priceBasis;
	public long priceProtectionEndDate;
	public long priceProtectionStartDate;
	public double actualPriceCap;
}