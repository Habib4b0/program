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

import com.stpl.app.model.StNmPpaProjection;
import com.stpl.app.service.persistence.StNmPpaProjectionPK;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing StNmPpaProjection in entity cache.
 *
 * @author
 * @see StNmPpaProjection
 * @generated
 */
@ProviderType
public class StNmPpaProjectionCacheModel implements CacheModel<StNmPpaProjection>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof StNmPpaProjectionCacheModel)) {
			return false;
		}

		StNmPpaProjectionCacheModel stNmPpaProjectionCacheModel = (StNmPpaProjectionCacheModel)obj;

		if (stNmPpaProjectionPK.equals(
					stNmPpaProjectionCacheModel.stNmPpaProjectionPK)) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, stNmPpaProjectionPK);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(27);

		sb.append("{lastModifiedDate=");
		sb.append(lastModifiedDate);
		sb.append(", periodSid=");
		sb.append(periodSid);
		sb.append(", projectionRate=");
		sb.append(projectionRate);
		sb.append(", projectionDetailsSid=");
		sb.append(projectionDetailsSid);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", priceCap=");
		sb.append(priceCap);
		sb.append(", projectionDiscountUnits=");
		sb.append(projectionDiscountUnits);
		sb.append(", sessionId=");
		sb.append(sessionId);
		sb.append(", projectionDiscountDollar=");
		sb.append(projectionDiscountDollar);
		sb.append(", reset=");
		sb.append(reset);
		sb.append(", projectionSales=");
		sb.append(projectionSales);
		sb.append(", projectionMap=");
		sb.append(projectionMap);
		sb.append(", resetPriceCap=");
		sb.append(resetPriceCap);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public StNmPpaProjection toEntityModel() {
		StNmPpaProjectionImpl stNmPpaProjectionImpl = new StNmPpaProjectionImpl();

		if (lastModifiedDate == Long.MIN_VALUE) {
			stNmPpaProjectionImpl.setLastModifiedDate(null);
		}
		else {
			stNmPpaProjectionImpl.setLastModifiedDate(new Date(lastModifiedDate));
		}

		stNmPpaProjectionImpl.setPeriodSid(periodSid);
		stNmPpaProjectionImpl.setProjectionRate(projectionRate);
		stNmPpaProjectionImpl.setProjectionDetailsSid(projectionDetailsSid);
		stNmPpaProjectionImpl.setUserId(userId);
		stNmPpaProjectionImpl.setPriceCap(priceCap);
		stNmPpaProjectionImpl.setProjectionDiscountUnits(projectionDiscountUnits);
		stNmPpaProjectionImpl.setSessionId(sessionId);
		stNmPpaProjectionImpl.setProjectionDiscountDollar(projectionDiscountDollar);
		stNmPpaProjectionImpl.setReset(reset);
		stNmPpaProjectionImpl.setProjectionSales(projectionSales);
		stNmPpaProjectionImpl.setProjectionMap(projectionMap);
		stNmPpaProjectionImpl.setResetPriceCap(resetPriceCap);

		stNmPpaProjectionImpl.resetOriginalValues();

		return stNmPpaProjectionImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		lastModifiedDate = objectInput.readLong();

		periodSid = objectInput.readInt();

		projectionRate = objectInput.readDouble();

		projectionDetailsSid = objectInput.readInt();

		userId = objectInput.readInt();

		priceCap = objectInput.readDouble();

		projectionDiscountUnits = objectInput.readDouble();

		sessionId = objectInput.readInt();

		projectionDiscountDollar = objectInput.readDouble();

		reset = objectInput.readBoolean();

		projectionSales = objectInput.readDouble();

		projectionMap = objectInput.readDouble();

		resetPriceCap = objectInput.readBoolean();

		stNmPpaProjectionPK = new StNmPpaProjectionPK(periodSid,
				projectionDetailsSid, userId, sessionId);
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(lastModifiedDate);

		objectOutput.writeInt(periodSid);

		objectOutput.writeDouble(projectionRate);

		objectOutput.writeInt(projectionDetailsSid);

		objectOutput.writeInt(userId);

		objectOutput.writeDouble(priceCap);

		objectOutput.writeDouble(projectionDiscountUnits);

		objectOutput.writeInt(sessionId);

		objectOutput.writeDouble(projectionDiscountDollar);

		objectOutput.writeBoolean(reset);

		objectOutput.writeDouble(projectionSales);

		objectOutput.writeDouble(projectionMap);

		objectOutput.writeBoolean(resetPriceCap);
	}

	public long lastModifiedDate;
	public int periodSid;
	public double projectionRate;
	public int projectionDetailsSid;
	public int userId;
	public double priceCap;
	public double projectionDiscountUnits;
	public int sessionId;
	public double projectionDiscountDollar;
	public boolean reset;
	public double projectionSales;
	public double projectionMap;
	public boolean resetPriceCap;
	public transient StNmPpaProjectionPK stNmPpaProjectionPK;
}