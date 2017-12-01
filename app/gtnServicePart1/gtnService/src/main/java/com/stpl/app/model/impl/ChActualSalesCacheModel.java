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

import com.stpl.app.model.ChActualSales;
import com.stpl.app.service.persistence.ChActualSalesPK;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing ChActualSales in entity cache.
 *
 * @author
 * @see ChActualSales
 * @generated
 */
@ProviderType
public class ChActualSalesCacheModel implements CacheModel<ChActualSales>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ChActualSalesCacheModel)) {
			return false;
		}

		ChActualSalesCacheModel chActualSalesCacheModel = (ChActualSalesCacheModel)obj;

		if (chActualSalesPK.equals(chActualSalesCacheModel.chActualSalesPK)) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, chActualSalesPK);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(13);

		sb.append("{contractUnits=");
		sb.append(contractUnits);
		sb.append(", perOfBusiness=");
		sb.append(perOfBusiness);
		sb.append(", periodSid=");
		sb.append(periodSid);
		sb.append(", contractSales=");
		sb.append(contractSales);
		sb.append(", projectionDetailsSid=");
		sb.append(projectionDetailsSid);
		sb.append(", gtsSales=");
		sb.append(gtsSales);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ChActualSales toEntityModel() {
		ChActualSalesImpl chActualSalesImpl = new ChActualSalesImpl();

		chActualSalesImpl.setContractUnits(contractUnits);
		chActualSalesImpl.setPerOfBusiness(perOfBusiness);
		chActualSalesImpl.setPeriodSid(periodSid);
		chActualSalesImpl.setContractSales(contractSales);
		chActualSalesImpl.setProjectionDetailsSid(projectionDetailsSid);
		chActualSalesImpl.setGtsSales(gtsSales);

		chActualSalesImpl.resetOriginalValues();

		return chActualSalesImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		contractUnits = objectInput.readDouble();

		perOfBusiness = objectInput.readDouble();

		periodSid = objectInput.readInt();

		contractSales = objectInput.readDouble();

		projectionDetailsSid = objectInput.readInt();

		gtsSales = objectInput.readDouble();

		chActualSalesPK = new ChActualSalesPK(periodSid, projectionDetailsSid);
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeDouble(contractUnits);

		objectOutput.writeDouble(perOfBusiness);

		objectOutput.writeInt(periodSid);

		objectOutput.writeDouble(contractSales);

		objectOutput.writeInt(projectionDetailsSid);

		objectOutput.writeDouble(gtsSales);
	}

	public double contractUnits;
	public double perOfBusiness;
	public int periodSid;
	public double contractSales;
	public int projectionDetailsSid;
	public double gtsSales;
	public transient ChActualSalesPK chActualSalesPK;
}