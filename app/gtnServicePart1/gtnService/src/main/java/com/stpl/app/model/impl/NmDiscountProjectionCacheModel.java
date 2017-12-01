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

import com.stpl.app.model.NmDiscountProjection;
import com.stpl.app.service.persistence.NmDiscountProjectionPK;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing NmDiscountProjection in entity cache.
 *
 * @author
 * @see NmDiscountProjection
 * @generated
 */
@ProviderType
public class NmDiscountProjectionCacheModel implements CacheModel<NmDiscountProjection>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof NmDiscountProjectionCacheModel)) {
			return false;
		}

		NmDiscountProjectionCacheModel nmDiscountProjectionCacheModel = (NmDiscountProjectionCacheModel)obj;

		if (nmDiscountProjectionPK.equals(
					nmDiscountProjectionCacheModel.nmDiscountProjectionPK)) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, nmDiscountProjectionPK);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(9);

		sb.append("{periodSid=");
		sb.append(periodSid);
		sb.append(", projectionRate=");
		sb.append(projectionRate);
		sb.append(", projectionDetailsSid=");
		sb.append(projectionDetailsSid);
		sb.append(", projectionSales=");
		sb.append(projectionSales);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public NmDiscountProjection toEntityModel() {
		NmDiscountProjectionImpl nmDiscountProjectionImpl = new NmDiscountProjectionImpl();

		nmDiscountProjectionImpl.setPeriodSid(periodSid);
		nmDiscountProjectionImpl.setProjectionRate(projectionRate);
		nmDiscountProjectionImpl.setProjectionDetailsSid(projectionDetailsSid);
		nmDiscountProjectionImpl.setProjectionSales(projectionSales);

		nmDiscountProjectionImpl.resetOriginalValues();

		return nmDiscountProjectionImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		periodSid = objectInput.readInt();

		projectionRate = objectInput.readDouble();

		projectionDetailsSid = objectInput.readInt();

		projectionSales = objectInput.readDouble();

		nmDiscountProjectionPK = new NmDiscountProjectionPK(periodSid,
				projectionDetailsSid);
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeInt(periodSid);

		objectOutput.writeDouble(projectionRate);

		objectOutput.writeInt(projectionDetailsSid);

		objectOutput.writeDouble(projectionSales);
	}

	public int periodSid;
	public double projectionRate;
	public int projectionDetailsSid;
	public double projectionSales;
	public transient NmDiscountProjectionPK nmDiscountProjectionPK;
}