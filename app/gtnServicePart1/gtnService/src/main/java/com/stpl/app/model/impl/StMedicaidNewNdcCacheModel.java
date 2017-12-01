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

import com.stpl.app.model.StMedicaidNewNdc;
import com.stpl.app.service.persistence.StMedicaidNewNdcPK;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing StMedicaidNewNdc in entity cache.
 *
 * @author
 * @see StMedicaidNewNdc
 * @generated
 */
@ProviderType
public class StMedicaidNewNdcCacheModel implements CacheModel<StMedicaidNewNdc>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof StMedicaidNewNdcCacheModel)) {
			return false;
		}

		StMedicaidNewNdcCacheModel stMedicaidNewNdcCacheModel = (StMedicaidNewNdcCacheModel)obj;

		if (stMedicaidNewNdcPK.equals(
					stMedicaidNewNdcCacheModel.stMedicaidNewNdcPK)) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, stMedicaidNewNdcPK);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(19);

		sb.append("{forecastAmp=");
		sb.append(forecastAmp);
		sb.append(", forecastBestprice=");
		sb.append(forecastBestprice);
		sb.append(", baseYearCpi=");
		sb.append(baseYearCpi);
		sb.append(", ndc9=");
		sb.append(ndc9);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", lastModifiedDate=");
		sb.append(lastModifiedDate);
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
	public StMedicaidNewNdc toEntityModel() {
		StMedicaidNewNdcImpl stMedicaidNewNdcImpl = new StMedicaidNewNdcImpl();

		stMedicaidNewNdcImpl.setForecastAmp(forecastAmp);
		stMedicaidNewNdcImpl.setForecastBestprice(forecastBestprice);
		stMedicaidNewNdcImpl.setBaseYearCpi(baseYearCpi);

		if (ndc9 == null) {
			stMedicaidNewNdcImpl.setNdc9(StringPool.BLANK);
		}
		else {
			stMedicaidNewNdcImpl.setNdc9(ndc9);
		}

		stMedicaidNewNdcImpl.setUserId(userId);

		if (lastModifiedDate == Long.MIN_VALUE) {
			stMedicaidNewNdcImpl.setLastModifiedDate(null);
		}
		else {
			stMedicaidNewNdcImpl.setLastModifiedDate(new Date(lastModifiedDate));
		}

		stMedicaidNewNdcImpl.setWacPrice(wacPrice);
		stMedicaidNewNdcImpl.setBaseYearAmp(baseYearAmp);
		stMedicaidNewNdcImpl.setSessionId(sessionId);

		stMedicaidNewNdcImpl.resetOriginalValues();

		return stMedicaidNewNdcImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		forecastAmp = objectInput.readDouble();

		forecastBestprice = objectInput.readDouble();

		baseYearCpi = objectInput.readDouble();
		ndc9 = objectInput.readUTF();

		userId = objectInput.readInt();
		lastModifiedDate = objectInput.readLong();

		wacPrice = objectInput.readDouble();

		baseYearAmp = objectInput.readDouble();

		sessionId = objectInput.readInt();

		stMedicaidNewNdcPK = new StMedicaidNewNdcPK(ndc9, userId, sessionId);
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeDouble(forecastAmp);

		objectOutput.writeDouble(forecastBestprice);

		objectOutput.writeDouble(baseYearCpi);

		if (ndc9 == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(ndc9);
		}

		objectOutput.writeInt(userId);
		objectOutput.writeLong(lastModifiedDate);

		objectOutput.writeDouble(wacPrice);

		objectOutput.writeDouble(baseYearAmp);

		objectOutput.writeInt(sessionId);
	}

	public double forecastAmp;
	public double forecastBestprice;
	public double baseYearCpi;
	public String ndc9;
	public int userId;
	public long lastModifiedDate;
	public double wacPrice;
	public double baseYearAmp;
	public int sessionId;
	public transient StMedicaidNewNdcPK stMedicaidNewNdcPK;
}