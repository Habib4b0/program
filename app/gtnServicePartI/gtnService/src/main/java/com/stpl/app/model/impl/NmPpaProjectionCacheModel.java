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

import com.stpl.app.model.NmPpaProjection;
import com.stpl.app.service.persistence.NmPpaProjectionPK;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing NmPpaProjection in entity cache.
 *
 * @author
 * @see NmPpaProjection
 * @generated
 */
@ProviderType
public class NmPpaProjectionCacheModel implements CacheModel<NmPpaProjection>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof NmPpaProjectionCacheModel)) {
			return false;
		}

		NmPpaProjectionCacheModel nmPpaProjectionCacheModel = (NmPpaProjectionCacheModel)obj;

		if (nmPpaProjectionPK.equals(
					nmPpaProjectionCacheModel.nmPpaProjectionPK)) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, nmPpaProjectionPK);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(21);

		sb.append("{periodSid=");
		sb.append(periodSid);
		sb.append(", projectionRate=");
		sb.append(projectionRate);
		sb.append(", projectionDetailsSid=");
		sb.append(projectionDetailsSid);
		sb.append(", priceCap=");
		sb.append(priceCap);
		sb.append(", projectionDiscountUnits=");
		sb.append(projectionDiscountUnits);
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
	public NmPpaProjection toEntityModel() {
		NmPpaProjectionImpl nmPpaProjectionImpl = new NmPpaProjectionImpl();

		nmPpaProjectionImpl.setPeriodSid(periodSid);
		nmPpaProjectionImpl.setProjectionRate(projectionRate);
		nmPpaProjectionImpl.setProjectionDetailsSid(projectionDetailsSid);
		nmPpaProjectionImpl.setPriceCap(priceCap);
		nmPpaProjectionImpl.setProjectionDiscountUnits(projectionDiscountUnits);
		nmPpaProjectionImpl.setProjectionDiscountDollar(projectionDiscountDollar);
		nmPpaProjectionImpl.setReset(reset);
		nmPpaProjectionImpl.setProjectionSales(projectionSales);
		nmPpaProjectionImpl.setProjectionMap(projectionMap);
		nmPpaProjectionImpl.setResetPriceCap(resetPriceCap);

		nmPpaProjectionImpl.resetOriginalValues();

		return nmPpaProjectionImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		periodSid = objectInput.readInt();

		projectionRate = objectInput.readDouble();

		projectionDetailsSid = objectInput.readInt();

		priceCap = objectInput.readDouble();

		projectionDiscountUnits = objectInput.readDouble();

		projectionDiscountDollar = objectInput.readDouble();

		reset = objectInput.readBoolean();

		projectionSales = objectInput.readDouble();

		projectionMap = objectInput.readDouble();

		resetPriceCap = objectInput.readBoolean();

		nmPpaProjectionPK = new NmPpaProjectionPK(periodSid,
				projectionDetailsSid);
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeInt(periodSid);

		objectOutput.writeDouble(projectionRate);

		objectOutput.writeInt(projectionDetailsSid);

		objectOutput.writeDouble(priceCap);

		objectOutput.writeDouble(projectionDiscountUnits);

		objectOutput.writeDouble(projectionDiscountDollar);

		objectOutput.writeBoolean(reset);

		objectOutput.writeDouble(projectionSales);

		objectOutput.writeDouble(projectionMap);

		objectOutput.writeBoolean(resetPriceCap);
	}

	public int periodSid;
	public double projectionRate;
	public int projectionDetailsSid;
	public double priceCap;
	public double projectionDiscountUnits;
	public double projectionDiscountDollar;
	public boolean reset;
	public double projectionSales;
	public double projectionMap;
	public boolean resetPriceCap;
	public transient NmPpaProjectionPK nmPpaProjectionPK;
}