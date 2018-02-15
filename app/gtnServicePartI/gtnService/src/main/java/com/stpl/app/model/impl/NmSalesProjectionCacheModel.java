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

import com.stpl.app.model.NmSalesProjection;
import com.stpl.app.service.persistence.NmSalesProjectionPK;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing NmSalesProjection in entity cache.
 *
 * @author
 * @see NmSalesProjection
 * @generated
 */
@ProviderType
public class NmSalesProjectionCacheModel implements CacheModel<NmSalesProjection>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof NmSalesProjectionCacheModel)) {
			return false;
		}

		NmSalesProjectionCacheModel nmSalesProjectionCacheModel = (NmSalesProjectionCacheModel)obj;

		if (nmSalesProjectionPK.equals(
					nmSalesProjectionCacheModel.nmSalesProjectionPK)) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, nmSalesProjectionPK);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(13);

		sb.append("{periodSid=");
		sb.append(periodSid);
		sb.append(", productGrowth=");
		sb.append(productGrowth);
		sb.append(", projectionDetailsSid=");
		sb.append(projectionDetailsSid);
		sb.append(", accountGrowth=");
		sb.append(accountGrowth);
		sb.append(", projectionUnits=");
		sb.append(projectionUnits);
		sb.append(", projectionSales=");
		sb.append(projectionSales);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public NmSalesProjection toEntityModel() {
		NmSalesProjectionImpl nmSalesProjectionImpl = new NmSalesProjectionImpl();

		nmSalesProjectionImpl.setPeriodSid(periodSid);
		nmSalesProjectionImpl.setProductGrowth(productGrowth);
		nmSalesProjectionImpl.setProjectionDetailsSid(projectionDetailsSid);
		nmSalesProjectionImpl.setAccountGrowth(accountGrowth);
		nmSalesProjectionImpl.setProjectionUnits(projectionUnits);
		nmSalesProjectionImpl.setProjectionSales(projectionSales);

		nmSalesProjectionImpl.resetOriginalValues();

		return nmSalesProjectionImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		periodSid = objectInput.readInt();

		productGrowth = objectInput.readDouble();

		projectionDetailsSid = objectInput.readInt();

		accountGrowth = objectInput.readDouble();

		projectionUnits = objectInput.readDouble();

		projectionSales = objectInput.readDouble();

		nmSalesProjectionPK = new NmSalesProjectionPK(periodSid,
				projectionDetailsSid);
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeInt(periodSid);

		objectOutput.writeDouble(productGrowth);

		objectOutput.writeInt(projectionDetailsSid);

		objectOutput.writeDouble(accountGrowth);

		objectOutput.writeDouble(projectionUnits);

		objectOutput.writeDouble(projectionSales);
	}

	public int periodSid;
	public double productGrowth;
	public int projectionDetailsSid;
	public double accountGrowth;
	public double projectionUnits;
	public double projectionSales;
	public transient NmSalesProjectionPK nmSalesProjectionPK;
}