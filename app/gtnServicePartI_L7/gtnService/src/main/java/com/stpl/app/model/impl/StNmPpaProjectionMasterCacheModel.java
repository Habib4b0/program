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

import com.stpl.app.model.StNmPpaProjectionMaster;
import com.stpl.app.service.persistence.StNmPpaProjectionMasterPK;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing StNmPpaProjectionMaster in entity cache.
 *
 * @author
 * @see StNmPpaProjectionMaster
 * @generated
 */
@ProviderType
public class StNmPpaProjectionMasterCacheModel implements CacheModel<StNmPpaProjectionMaster>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof StNmPpaProjectionMasterCacheModel)) {
			return false;
		}

		StNmPpaProjectionMasterCacheModel stNmPpaProjectionMasterCacheModel = (StNmPpaProjectionMasterCacheModel)obj;

		if (stNmPpaProjectionMasterPK.equals(
					stNmPpaProjectionMasterCacheModel.stNmPpaProjectionMasterPK)) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, stNmPpaProjectionMasterPK);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(21);

		sb.append("{lastModifiedDate=");
		sb.append(lastModifiedDate);
		sb.append(", checkRecord=");
		sb.append(checkRecord);
		sb.append(", userGroup=");
		sb.append(userGroup);
		sb.append(", projectionDetailsSid=");
		sb.append(projectionDetailsSid);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", sessionId=");
		sb.append(sessionId);
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
	public StNmPpaProjectionMaster toEntityModel() {
		StNmPpaProjectionMasterImpl stNmPpaProjectionMasterImpl = new StNmPpaProjectionMasterImpl();

		if (lastModifiedDate == Long.MIN_VALUE) {
			stNmPpaProjectionMasterImpl.setLastModifiedDate(null);
		}
		else {
			stNmPpaProjectionMasterImpl.setLastModifiedDate(new Date(
					lastModifiedDate));
		}

		stNmPpaProjectionMasterImpl.setCheckRecord(checkRecord);

		if (userGroup == null) {
			stNmPpaProjectionMasterImpl.setUserGroup(StringPool.BLANK);
		}
		else {
			stNmPpaProjectionMasterImpl.setUserGroup(userGroup);
		}

		stNmPpaProjectionMasterImpl.setProjectionDetailsSid(projectionDetailsSid);
		stNmPpaProjectionMasterImpl.setUserId(userId);
		stNmPpaProjectionMasterImpl.setSessionId(sessionId);

		if (priceBasis == null) {
			stNmPpaProjectionMasterImpl.setPriceBasis(StringPool.BLANK);
		}
		else {
			stNmPpaProjectionMasterImpl.setPriceBasis(priceBasis);
		}

		if (priceProtectionEndDate == Long.MIN_VALUE) {
			stNmPpaProjectionMasterImpl.setPriceProtectionEndDate(null);
		}
		else {
			stNmPpaProjectionMasterImpl.setPriceProtectionEndDate(new Date(
					priceProtectionEndDate));
		}

		if (priceProtectionStartDate == Long.MIN_VALUE) {
			stNmPpaProjectionMasterImpl.setPriceProtectionStartDate(null);
		}
		else {
			stNmPpaProjectionMasterImpl.setPriceProtectionStartDate(new Date(
					priceProtectionStartDate));
		}

		stNmPpaProjectionMasterImpl.setActualPriceCap(actualPriceCap);

		stNmPpaProjectionMasterImpl.resetOriginalValues();

		return stNmPpaProjectionMasterImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		lastModifiedDate = objectInput.readLong();

		checkRecord = objectInput.readBoolean();
		userGroup = objectInput.readUTF();

		projectionDetailsSid = objectInput.readInt();

		userId = objectInput.readInt();

		sessionId = objectInput.readInt();
		priceBasis = objectInput.readUTF();
		priceProtectionEndDate = objectInput.readLong();
		priceProtectionStartDate = objectInput.readLong();

		actualPriceCap = objectInput.readDouble();

		stNmPpaProjectionMasterPK = new StNmPpaProjectionMasterPK(projectionDetailsSid,
				userId, sessionId);
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(lastModifiedDate);

		objectOutput.writeBoolean(checkRecord);

		if (userGroup == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(userGroup);
		}

		objectOutput.writeInt(projectionDetailsSid);

		objectOutput.writeInt(userId);

		objectOutput.writeInt(sessionId);

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

	public long lastModifiedDate;
	public boolean checkRecord;
	public String userGroup;
	public int projectionDetailsSid;
	public int userId;
	public int sessionId;
	public String priceBasis;
	public long priceProtectionEndDate;
	public long priceProtectionStartDate;
	public double actualPriceCap;
	public transient StNmPpaProjectionMasterPK stNmPpaProjectionMasterPK;
}