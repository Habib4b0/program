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

import com.stpl.app.model.StChActualDiscount;
import com.stpl.app.service.persistence.StChActualDiscountPK;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing StChActualDiscount in entity cache.
 *
 * @author
 * @see StChActualDiscount
 * @generated
 */
@ProviderType
public class StChActualDiscountCacheModel implements CacheModel<StChActualDiscount>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof StChActualDiscountCacheModel)) {
			return false;
		}

		StChActualDiscountCacheModel stChActualDiscountCacheModel = (StChActualDiscountCacheModel)obj;

		if (stChActualDiscountPK.equals(
					stChActualDiscountCacheModel.stChActualDiscountPK)) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, stChActualDiscountPK);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(17);

		sb.append("{lastModifiedDate=");
		sb.append(lastModifiedDate);
		sb.append(", actualRate=");
		sb.append(actualRate);
		sb.append(", periodSid=");
		sb.append(periodSid);
		sb.append(", projectionDetailsSid=");
		sb.append(projectionDetailsSid);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", sessionId=");
		sb.append(sessionId);
		sb.append(", rsModelSid=");
		sb.append(rsModelSid);
		sb.append(", actualSales=");
		sb.append(actualSales);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public StChActualDiscount toEntityModel() {
		StChActualDiscountImpl stChActualDiscountImpl = new StChActualDiscountImpl();

		if (lastModifiedDate == Long.MIN_VALUE) {
			stChActualDiscountImpl.setLastModifiedDate(null);
		}
		else {
			stChActualDiscountImpl.setLastModifiedDate(new Date(
					lastModifiedDate));
		}

		stChActualDiscountImpl.setActualRate(actualRate);
		stChActualDiscountImpl.setPeriodSid(periodSid);
		stChActualDiscountImpl.setProjectionDetailsSid(projectionDetailsSid);
		stChActualDiscountImpl.setUserId(userId);
		stChActualDiscountImpl.setSessionId(sessionId);
		stChActualDiscountImpl.setRsModelSid(rsModelSid);
		stChActualDiscountImpl.setActualSales(actualSales);

		stChActualDiscountImpl.resetOriginalValues();

		return stChActualDiscountImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		lastModifiedDate = objectInput.readLong();

		actualRate = objectInput.readDouble();

		periodSid = objectInput.readInt();

		projectionDetailsSid = objectInput.readInt();

		userId = objectInput.readInt();

		sessionId = objectInput.readInt();

		rsModelSid = objectInput.readInt();

		actualSales = objectInput.readDouble();

		stChActualDiscountPK = new StChActualDiscountPK(periodSid,
				projectionDetailsSid, userId, sessionId, rsModelSid);
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(lastModifiedDate);

		objectOutput.writeDouble(actualRate);

		objectOutput.writeInt(periodSid);

		objectOutput.writeInt(projectionDetailsSid);

		objectOutput.writeInt(userId);

		objectOutput.writeInt(sessionId);

		objectOutput.writeInt(rsModelSid);

		objectOutput.writeDouble(actualSales);
	}

	public long lastModifiedDate;
	public double actualRate;
	public int periodSid;
	public int projectionDetailsSid;
	public int userId;
	public int sessionId;
	public int rsModelSid;
	public double actualSales;
	public transient StChActualDiscountPK stChActualDiscountPK;
}