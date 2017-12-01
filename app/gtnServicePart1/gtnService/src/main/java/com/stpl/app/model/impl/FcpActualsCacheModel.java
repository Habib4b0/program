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

import com.stpl.app.model.FcpActuals;
import com.stpl.app.service.persistence.FcpActualsPK;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing FcpActuals in entity cache.
 *
 * @author
 * @see FcpActuals
 * @generated
 */
@ProviderType
public class FcpActualsCacheModel implements CacheModel<FcpActuals>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof FcpActualsCacheModel)) {
			return false;
		}

		FcpActualsCacheModel fcpActualsCacheModel = (FcpActualsCacheModel)obj;

		if (fcpActualsPK.equals(fcpActualsCacheModel.fcpActualsPK)) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, fcpActualsPK);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(11);

		sb.append("{periodSid=");
		sb.append(periodSid);
		sb.append(", priceType=");
		sb.append(priceType);
		sb.append(", actualPrice=");
		sb.append(actualPrice);
		sb.append(", notes=");
		sb.append(notes);
		sb.append(", naProjDetailsSid=");
		sb.append(naProjDetailsSid);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public FcpActuals toEntityModel() {
		FcpActualsImpl fcpActualsImpl = new FcpActualsImpl();

		fcpActualsImpl.setPeriodSid(periodSid);

		if (priceType == null) {
			fcpActualsImpl.setPriceType(StringPool.BLANK);
		}
		else {
			fcpActualsImpl.setPriceType(priceType);
		}

		fcpActualsImpl.setActualPrice(actualPrice);

		if (notes == null) {
			fcpActualsImpl.setNotes(StringPool.BLANK);
		}
		else {
			fcpActualsImpl.setNotes(notes);
		}

		fcpActualsImpl.setNaProjDetailsSid(naProjDetailsSid);

		fcpActualsImpl.resetOriginalValues();

		return fcpActualsImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		periodSid = objectInput.readInt();
		priceType = objectInput.readUTF();

		actualPrice = objectInput.readDouble();
		notes = objectInput.readUTF();

		naProjDetailsSid = objectInput.readInt();

		fcpActualsPK = new FcpActualsPK(periodSid, priceType, naProjDetailsSid);
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeInt(periodSid);

		if (priceType == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(priceType);
		}

		objectOutput.writeDouble(actualPrice);

		if (notes == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(notes);
		}

		objectOutput.writeInt(naProjDetailsSid);
	}

	public int periodSid;
	public String priceType;
	public double actualPrice;
	public String notes;
	public int naProjDetailsSid;
	public transient FcpActualsPK fcpActualsPK;
}