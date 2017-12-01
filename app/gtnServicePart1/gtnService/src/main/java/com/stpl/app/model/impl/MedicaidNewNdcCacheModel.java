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

import com.stpl.app.model.MedicaidNewNdc;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing MedicaidNewNdc in entity cache.
 *
 * @author
 * @see MedicaidNewNdc
 * @generated
 */
@ProviderType
public class MedicaidNewNdcCacheModel implements CacheModel<MedicaidNewNdc>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof MedicaidNewNdcCacheModel)) {
			return false;
		}

		MedicaidNewNdcCacheModel medicaidNewNdcCacheModel = (MedicaidNewNdcCacheModel)obj;

		if (ndc9.equals(medicaidNewNdcCacheModel.ndc9)) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, ndc9);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(13);

		sb.append("{forecastAmp=");
		sb.append(forecastAmp);
		sb.append(", forecastBestprice=");
		sb.append(forecastBestprice);
		sb.append(", baseYearCpi=");
		sb.append(baseYearCpi);
		sb.append(", ndc9=");
		sb.append(ndc9);
		sb.append(", wacPrice=");
		sb.append(wacPrice);
		sb.append(", baseYearAmp=");
		sb.append(baseYearAmp);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public MedicaidNewNdc toEntityModel() {
		MedicaidNewNdcImpl medicaidNewNdcImpl = new MedicaidNewNdcImpl();

		medicaidNewNdcImpl.setForecastAmp(forecastAmp);
		medicaidNewNdcImpl.setForecastBestprice(forecastBestprice);
		medicaidNewNdcImpl.setBaseYearCpi(baseYearCpi);

		if (ndc9 == null) {
			medicaidNewNdcImpl.setNdc9(StringPool.BLANK);
		}
		else {
			medicaidNewNdcImpl.setNdc9(ndc9);
		}

		medicaidNewNdcImpl.setWacPrice(wacPrice);
		medicaidNewNdcImpl.setBaseYearAmp(baseYearAmp);

		medicaidNewNdcImpl.resetOriginalValues();

		return medicaidNewNdcImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		forecastAmp = objectInput.readDouble();

		forecastBestprice = objectInput.readDouble();

		baseYearCpi = objectInput.readDouble();
		ndc9 = objectInput.readUTF();

		wacPrice = objectInput.readDouble();

		baseYearAmp = objectInput.readDouble();
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

		objectOutput.writeDouble(wacPrice);

		objectOutput.writeDouble(baseYearAmp);
	}

	public double forecastAmp;
	public double forecastBestprice;
	public double baseYearCpi;
	public String ndc9;
	public double wacPrice;
	public double baseYearAmp;
}