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

import com.stpl.app.model.StNmActualPpa;
import com.stpl.app.service.persistence.StNmActualPpaPK;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing StNmActualPpa in entity cache.
 *
 * @author
 * @see StNmActualPpa
 * @generated
 */
@ProviderType
public class StNmActualPpaCacheModel implements CacheModel<StNmActualPpa>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof StNmActualPpaCacheModel)) {
			return false;
		}

		StNmActualPpaCacheModel stNmActualPpaCacheModel = (StNmActualPpaCacheModel)obj;

		if (stNmActualPpaPK.equals(stNmActualPpaCacheModel.stNmActualPpaPK)) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, stNmActualPpaPK);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(27);

		sb.append("{lastModifiedDate=");
		sb.append(lastModifiedDate);
		sb.append(", actualRate=");
		sb.append(actualRate);
		sb.append(", periodSid=");
		sb.append(periodSid);
		sb.append(", actualProjDiscountDollar=");
		sb.append(actualProjDiscountDollar);
		sb.append(", actualProjectionSales=");
		sb.append(actualProjectionSales);
		sb.append(", projectionDetailsSid=");
		sb.append(projectionDetailsSid);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", actualProjectionRate=");
		sb.append(actualProjectionRate);
		sb.append(", sessionId=");
		sb.append(sessionId);
		sb.append(", actualProjDiscountUnits=");
		sb.append(actualProjDiscountUnits);
		sb.append(", actualDiscountDollar=");
		sb.append(actualDiscountDollar);
		sb.append(", actualDiscountUnits=");
		sb.append(actualDiscountUnits);
		sb.append(", actualSales=");
		sb.append(actualSales);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public StNmActualPpa toEntityModel() {
		StNmActualPpaImpl stNmActualPpaImpl = new StNmActualPpaImpl();

		if (lastModifiedDate == Long.MIN_VALUE) {
			stNmActualPpaImpl.setLastModifiedDate(null);
		}
		else {
			stNmActualPpaImpl.setLastModifiedDate(new Date(lastModifiedDate));
		}

		stNmActualPpaImpl.setActualRate(actualRate);
		stNmActualPpaImpl.setPeriodSid(periodSid);
		stNmActualPpaImpl.setActualProjDiscountDollar(actualProjDiscountDollar);
		stNmActualPpaImpl.setActualProjectionSales(actualProjectionSales);
		stNmActualPpaImpl.setProjectionDetailsSid(projectionDetailsSid);
		stNmActualPpaImpl.setUserId(userId);
		stNmActualPpaImpl.setActualProjectionRate(actualProjectionRate);
		stNmActualPpaImpl.setSessionId(sessionId);
		stNmActualPpaImpl.setActualProjDiscountUnits(actualProjDiscountUnits);
		stNmActualPpaImpl.setActualDiscountDollar(actualDiscountDollar);
		stNmActualPpaImpl.setActualDiscountUnits(actualDiscountUnits);
		stNmActualPpaImpl.setActualSales(actualSales);

		stNmActualPpaImpl.resetOriginalValues();

		return stNmActualPpaImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		lastModifiedDate = objectInput.readLong();

		actualRate = objectInput.readDouble();

		periodSid = objectInput.readInt();

		actualProjDiscountDollar = objectInput.readDouble();

		actualProjectionSales = objectInput.readDouble();

		projectionDetailsSid = objectInput.readInt();

		userId = objectInput.readInt();

		actualProjectionRate = objectInput.readDouble();

		sessionId = objectInput.readInt();

		actualProjDiscountUnits = objectInput.readDouble();

		actualDiscountDollar = objectInput.readDouble();

		actualDiscountUnits = objectInput.readDouble();

		actualSales = objectInput.readDouble();

		stNmActualPpaPK = new StNmActualPpaPK(periodSid, projectionDetailsSid,
				userId, sessionId);
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(lastModifiedDate);

		objectOutput.writeDouble(actualRate);

		objectOutput.writeInt(periodSid);

		objectOutput.writeDouble(actualProjDiscountDollar);

		objectOutput.writeDouble(actualProjectionSales);

		objectOutput.writeInt(projectionDetailsSid);

		objectOutput.writeInt(userId);

		objectOutput.writeDouble(actualProjectionRate);

		objectOutput.writeInt(sessionId);

		objectOutput.writeDouble(actualProjDiscountUnits);

		objectOutput.writeDouble(actualDiscountDollar);

		objectOutput.writeDouble(actualDiscountUnits);

		objectOutput.writeDouble(actualSales);
	}

	public long lastModifiedDate;
	public double actualRate;
	public int periodSid;
	public double actualProjDiscountDollar;
	public double actualProjectionSales;
	public int projectionDetailsSid;
	public int userId;
	public double actualProjectionRate;
	public int sessionId;
	public double actualProjDiscountUnits;
	public double actualDiscountDollar;
	public double actualDiscountUnits;
	public double actualSales;
	public transient StNmActualPpaPK stNmActualPpaPK;
}