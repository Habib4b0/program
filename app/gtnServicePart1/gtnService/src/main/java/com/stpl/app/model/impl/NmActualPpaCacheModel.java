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

import com.stpl.app.model.NmActualPpa;
import com.stpl.app.service.persistence.NmActualPpaPK;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing NmActualPpa in entity cache.
 *
 * @author
 * @see NmActualPpa
 * @generated
 */
@ProviderType
public class NmActualPpaCacheModel implements CacheModel<NmActualPpa>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof NmActualPpaCacheModel)) {
			return false;
		}

		NmActualPpaCacheModel nmActualPpaCacheModel = (NmActualPpaCacheModel)obj;

		if (nmActualPpaPK.equals(nmActualPpaCacheModel.nmActualPpaPK)) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, nmActualPpaPK);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(13);

		sb.append("{actualRate=");
		sb.append(actualRate);
		sb.append(", periodSid=");
		sb.append(periodSid);
		sb.append(", projectionDetailsSid=");
		sb.append(projectionDetailsSid);
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
	public NmActualPpa toEntityModel() {
		NmActualPpaImpl nmActualPpaImpl = new NmActualPpaImpl();

		nmActualPpaImpl.setActualRate(actualRate);
		nmActualPpaImpl.setPeriodSid(periodSid);
		nmActualPpaImpl.setProjectionDetailsSid(projectionDetailsSid);
		nmActualPpaImpl.setActualDiscountDollar(actualDiscountDollar);
		nmActualPpaImpl.setActualDiscountUnits(actualDiscountUnits);
		nmActualPpaImpl.setActualSales(actualSales);

		nmActualPpaImpl.resetOriginalValues();

		return nmActualPpaImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		actualRate = objectInput.readDouble();

		periodSid = objectInput.readInt();

		projectionDetailsSid = objectInput.readInt();

		actualDiscountDollar = objectInput.readDouble();

		actualDiscountUnits = objectInput.readDouble();

		actualSales = objectInput.readDouble();

		nmActualPpaPK = new NmActualPpaPK(periodSid, projectionDetailsSid);
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeDouble(actualRate);

		objectOutput.writeInt(periodSid);

		objectOutput.writeInt(projectionDetailsSid);

		objectOutput.writeDouble(actualDiscountDollar);

		objectOutput.writeDouble(actualDiscountUnits);

		objectOutput.writeDouble(actualSales);
	}

	public double actualRate;
	public int periodSid;
	public int projectionDetailsSid;
	public double actualDiscountDollar;
	public double actualDiscountUnits;
	public double actualSales;
	public transient NmActualPpaPK nmActualPpaPK;
}