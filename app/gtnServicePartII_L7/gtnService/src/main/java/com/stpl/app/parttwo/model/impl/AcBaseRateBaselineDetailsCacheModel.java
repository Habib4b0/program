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

package com.stpl.app.parttwo.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import com.stpl.app.parttwo.model.AcBaseRateBaselineDetails;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing AcBaseRateBaselineDetails in entity cache.
 *
 * @author
 * @see AcBaseRateBaselineDetails
 * @generated
 */
@ProviderType
public class AcBaseRateBaselineDetailsCacheModel implements CacheModel<AcBaseRateBaselineDetails>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AcBaseRateBaselineDetailsCacheModel)) {
			return false;
		}

		AcBaseRateBaselineDetailsCacheModel acBaseRateBaselineDetailsCacheModel = (AcBaseRateBaselineDetailsCacheModel)obj;

		if (acBrMethodologyDetailsSid == acBaseRateBaselineDetailsCacheModel.acBrMethodologyDetailsSid) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, acBrMethodologyDetailsSid);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(11);

		sb.append("{periodValue=");
		sb.append(periodValue);
		sb.append(", periodSid=");
		sb.append(periodSid);
		sb.append(", paymentsPeriod=");
		sb.append(paymentsPeriod);
		sb.append(", acBrMethodologyDetailsSid=");
		sb.append(acBrMethodologyDetailsSid);
		sb.append(", salesPeriod=");
		sb.append(salesPeriod);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public AcBaseRateBaselineDetails toEntityModel() {
		AcBaseRateBaselineDetailsImpl acBaseRateBaselineDetailsImpl = new AcBaseRateBaselineDetailsImpl();

		acBaseRateBaselineDetailsImpl.setPeriodValue(periodValue);
		acBaseRateBaselineDetailsImpl.setPeriodSid(periodSid);
		acBaseRateBaselineDetailsImpl.setPaymentsPeriod(paymentsPeriod);
		acBaseRateBaselineDetailsImpl.setAcBrMethodologyDetailsSid(acBrMethodologyDetailsSid);
		acBaseRateBaselineDetailsImpl.setSalesPeriod(salesPeriod);

		acBaseRateBaselineDetailsImpl.resetOriginalValues();

		return acBaseRateBaselineDetailsImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		periodValue = objectInput.readDouble();

		periodSid = objectInput.readInt();

		paymentsPeriod = objectInput.readBoolean();

		acBrMethodologyDetailsSid = objectInput.readInt();

		salesPeriod = objectInput.readBoolean();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeDouble(periodValue);

		objectOutput.writeInt(periodSid);

		objectOutput.writeBoolean(paymentsPeriod);

		objectOutput.writeInt(acBrMethodologyDetailsSid);

		objectOutput.writeBoolean(salesPeriod);
	}

	public double periodValue;
	public int periodSid;
	public boolean paymentsPeriod;
	public int acBrMethodologyDetailsSid;
	public boolean salesPeriod;
}