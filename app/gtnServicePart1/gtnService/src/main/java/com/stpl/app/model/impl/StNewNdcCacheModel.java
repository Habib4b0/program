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

import com.stpl.app.model.StNewNdc;
import com.stpl.app.service.persistence.StNewNdcPK;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing StNewNdc in entity cache.
 *
 * @author
 * @see StNewNdc
 * @generated
 */
@ProviderType
public class StNewNdcCacheModel implements CacheModel<StNewNdc>, Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof StNewNdcCacheModel)) {
			return false;
		}

		StNewNdcCacheModel stNewNdcCacheModel = (StNewNdcCacheModel)obj;

		if (stNewNdcPK.equals(stNewNdcCacheModel.stNewNdcPK)) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, stNewNdcPK);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(21);

		sb.append("{forecastAmp=");
		sb.append(forecastAmp);
		sb.append(", forecastBestprice=");
		sb.append(forecastBestprice);
		sb.append(", naProjDetailsSid=");
		sb.append(naProjDetailsSid);
		sb.append(", baseYearCpi=");
		sb.append(baseYearCpi);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", lastModifiedDate=");
		sb.append(lastModifiedDate);
		sb.append(", itemMasterSid=");
		sb.append(itemMasterSid);
		sb.append(", wacPrice=");
		sb.append(wacPrice);
		sb.append(", baseYearAmp=");
		sb.append(baseYearAmp);
		sb.append(", sessionId=");
		sb.append(sessionId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public StNewNdc toEntityModel() {
		StNewNdcImpl stNewNdcImpl = new StNewNdcImpl();

		stNewNdcImpl.setForecastAmp(forecastAmp);
		stNewNdcImpl.setForecastBestprice(forecastBestprice);
		stNewNdcImpl.setNaProjDetailsSid(naProjDetailsSid);
		stNewNdcImpl.setBaseYearCpi(baseYearCpi);
		stNewNdcImpl.setUserId(userId);

		if (lastModifiedDate == Long.MIN_VALUE) {
			stNewNdcImpl.setLastModifiedDate(null);
		}
		else {
			stNewNdcImpl.setLastModifiedDate(new Date(lastModifiedDate));
		}

		stNewNdcImpl.setItemMasterSid(itemMasterSid);
		stNewNdcImpl.setWacPrice(wacPrice);
		stNewNdcImpl.setBaseYearAmp(baseYearAmp);
		stNewNdcImpl.setSessionId(sessionId);

		stNewNdcImpl.resetOriginalValues();

		return stNewNdcImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		forecastAmp = objectInput.readDouble();

		forecastBestprice = objectInput.readDouble();

		naProjDetailsSid = objectInput.readInt();

		baseYearCpi = objectInput.readDouble();

		userId = objectInput.readInt();
		lastModifiedDate = objectInput.readLong();

		itemMasterSid = objectInput.readInt();

		wacPrice = objectInput.readDouble();

		baseYearAmp = objectInput.readDouble();

		sessionId = objectInput.readInt();

		stNewNdcPK = new StNewNdcPK(naProjDetailsSid, userId, itemMasterSid,
				sessionId);
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeDouble(forecastAmp);

		objectOutput.writeDouble(forecastBestprice);

		objectOutput.writeInt(naProjDetailsSid);

		objectOutput.writeDouble(baseYearCpi);

		objectOutput.writeInt(userId);
		objectOutput.writeLong(lastModifiedDate);

		objectOutput.writeInt(itemMasterSid);

		objectOutput.writeDouble(wacPrice);

		objectOutput.writeDouble(baseYearAmp);

		objectOutput.writeInt(sessionId);
	}

	public double forecastAmp;
	public double forecastBestprice;
	public int naProjDetailsSid;
	public double baseYearCpi;
	public int userId;
	public long lastModifiedDate;
	public int itemMasterSid;
	public double wacPrice;
	public double baseYearAmp;
	public int sessionId;
	public transient StNewNdcPK stNewNdcPK;
}