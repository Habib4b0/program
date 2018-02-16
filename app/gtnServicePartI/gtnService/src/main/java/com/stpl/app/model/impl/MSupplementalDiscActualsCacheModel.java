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

import com.stpl.app.model.MSupplementalDiscActuals;
import com.stpl.app.service.persistence.MSupplementalDiscActualsPK;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing MSupplementalDiscActuals in entity cache.
 *
 * @author
 * @see MSupplementalDiscActuals
 * @generated
 */
@ProviderType
public class MSupplementalDiscActualsCacheModel implements CacheModel<MSupplementalDiscActuals>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof MSupplementalDiscActualsCacheModel)) {
			return false;
		}

		MSupplementalDiscActualsCacheModel mSupplementalDiscActualsCacheModel = (MSupplementalDiscActualsCacheModel)obj;

		if (mSupplementalDiscActualsPK.equals(
					mSupplementalDiscActualsCacheModel.mSupplementalDiscActualsPK)) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, mSupplementalDiscActualsPK);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(13);

		sb.append("{actualSales=");
		sb.append(actualSales);
		sb.append(", periodSid=");
		sb.append(periodSid);
		sb.append(", actualRate=");
		sb.append(actualRate);
		sb.append(", actualProjectionSales=");
		sb.append(actualProjectionSales);
		sb.append(", actualProjectionRate=");
		sb.append(actualProjectionRate);
		sb.append(", projectionDetailsSid=");
		sb.append(projectionDetailsSid);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public MSupplementalDiscActuals toEntityModel() {
		MSupplementalDiscActualsImpl mSupplementalDiscActualsImpl = new MSupplementalDiscActualsImpl();

		mSupplementalDiscActualsImpl.setActualSales(actualSales);
		mSupplementalDiscActualsImpl.setPeriodSid(periodSid);
		mSupplementalDiscActualsImpl.setActualRate(actualRate);
		mSupplementalDiscActualsImpl.setActualProjectionSales(actualProjectionSales);
		mSupplementalDiscActualsImpl.setActualProjectionRate(actualProjectionRate);
		mSupplementalDiscActualsImpl.setProjectionDetailsSid(projectionDetailsSid);

		mSupplementalDiscActualsImpl.resetOriginalValues();

		return mSupplementalDiscActualsImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		actualSales = objectInput.readDouble();

		periodSid = objectInput.readInt();

		actualRate = objectInput.readDouble();

		actualProjectionSales = objectInput.readDouble();

		actualProjectionRate = objectInput.readDouble();

		projectionDetailsSid = objectInput.readInt();

		mSupplementalDiscActualsPK = new MSupplementalDiscActualsPK(periodSid,
				projectionDetailsSid);
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeDouble(actualSales);

		objectOutput.writeInt(periodSid);

		objectOutput.writeDouble(actualRate);

		objectOutput.writeDouble(actualProjectionSales);

		objectOutput.writeDouble(actualProjectionRate);

		objectOutput.writeInt(projectionDetailsSid);
	}

	public double actualSales;
	public int periodSid;
	public double actualRate;
	public double actualProjectionSales;
	public double actualProjectionRate;
	public int projectionDetailsSid;
	public transient MSupplementalDiscActualsPK mSupplementalDiscActualsPK;
}