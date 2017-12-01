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

import com.stpl.app.model.NmActualDiscount;
import com.stpl.app.service.persistence.NmActualDiscountPK;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing NmActualDiscount in entity cache.
 *
 * @author
 * @see NmActualDiscount
 * @generated
 */
@ProviderType
public class NmActualDiscountCacheModel implements CacheModel<NmActualDiscount>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof NmActualDiscountCacheModel)) {
			return false;
		}

		NmActualDiscountCacheModel nmActualDiscountCacheModel = (NmActualDiscountCacheModel)obj;

		if (nmActualDiscountPK.equals(
					nmActualDiscountCacheModel.nmActualDiscountPK)) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, nmActualDiscountPK);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(9);

		sb.append("{actualRate=");
		sb.append(actualRate);
		sb.append(", periodSid=");
		sb.append(periodSid);
		sb.append(", projectionDetailsSid=");
		sb.append(projectionDetailsSid);
		sb.append(", actualSales=");
		sb.append(actualSales);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public NmActualDiscount toEntityModel() {
		NmActualDiscountImpl nmActualDiscountImpl = new NmActualDiscountImpl();

		nmActualDiscountImpl.setActualRate(actualRate);
		nmActualDiscountImpl.setPeriodSid(periodSid);
		nmActualDiscountImpl.setProjectionDetailsSid(projectionDetailsSid);
		nmActualDiscountImpl.setActualSales(actualSales);

		nmActualDiscountImpl.resetOriginalValues();

		return nmActualDiscountImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		actualRate = objectInput.readDouble();

		periodSid = objectInput.readInt();

		projectionDetailsSid = objectInput.readInt();

		actualSales = objectInput.readDouble();

		nmActualDiscountPK = new NmActualDiscountPK(periodSid,
				projectionDetailsSid);
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeDouble(actualRate);

		objectOutput.writeInt(periodSid);

		objectOutput.writeInt(projectionDetailsSid);

		objectOutput.writeDouble(actualSales);
	}

	public double actualRate;
	public int periodSid;
	public int projectionDetailsSid;
	public double actualSales;
	public transient NmActualDiscountPK nmActualDiscountPK;
}