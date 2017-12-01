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

import com.stpl.app.model.ChActualDiscount;
import com.stpl.app.service.persistence.ChActualDiscountPK;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing ChActualDiscount in entity cache.
 *
 * @author
 * @see ChActualDiscount
 * @generated
 */
@ProviderType
public class ChActualDiscountCacheModel implements CacheModel<ChActualDiscount>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ChActualDiscountCacheModel)) {
			return false;
		}

		ChActualDiscountCacheModel chActualDiscountCacheModel = (ChActualDiscountCacheModel)obj;

		if (chActualDiscountPK.equals(
					chActualDiscountCacheModel.chActualDiscountPK)) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, chActualDiscountPK);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(11);

		sb.append("{actualRate=");
		sb.append(actualRate);
		sb.append(", periodSid=");
		sb.append(periodSid);
		sb.append(", projectionDetailsSid=");
		sb.append(projectionDetailsSid);
		sb.append(", rsModelSid=");
		sb.append(rsModelSid);
		sb.append(", actualSales=");
		sb.append(actualSales);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ChActualDiscount toEntityModel() {
		ChActualDiscountImpl chActualDiscountImpl = new ChActualDiscountImpl();

		chActualDiscountImpl.setActualRate(actualRate);
		chActualDiscountImpl.setPeriodSid(periodSid);
		chActualDiscountImpl.setProjectionDetailsSid(projectionDetailsSid);
		chActualDiscountImpl.setRsModelSid(rsModelSid);
		chActualDiscountImpl.setActualSales(actualSales);

		chActualDiscountImpl.resetOriginalValues();

		return chActualDiscountImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		actualRate = objectInput.readDouble();

		periodSid = objectInput.readInt();

		projectionDetailsSid = objectInput.readInt();

		rsModelSid = objectInput.readInt();

		actualSales = objectInput.readDouble();

		chActualDiscountPK = new ChActualDiscountPK(periodSid,
				projectionDetailsSid, rsModelSid);
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeDouble(actualRate);

		objectOutput.writeInt(periodSid);

		objectOutput.writeInt(projectionDetailsSid);

		objectOutput.writeInt(rsModelSid);

		objectOutput.writeDouble(actualSales);
	}

	public double actualRate;
	public int periodSid;
	public int projectionDetailsSid;
	public int rsModelSid;
	public double actualSales;
	public transient ChActualDiscountPK chActualDiscountPK;
}