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

import com.stpl.app.model.MedicaidUraActuals;
import com.stpl.app.service.persistence.MedicaidUraActualsPK;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing MedicaidUraActuals in entity cache.
 *
 * @author
 * @see MedicaidUraActuals
 * @generated
 */
@ProviderType
public class MedicaidUraActualsCacheModel implements CacheModel<MedicaidUraActuals>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof MedicaidUraActualsCacheModel)) {
			return false;
		}

		MedicaidUraActualsCacheModel medicaidUraActualsCacheModel = (MedicaidUraActualsCacheModel)obj;

		if (medicaidUraActualsPK.equals(
					medicaidUraActualsCacheModel.medicaidUraActualsPK)) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, medicaidUraActualsPK);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(13);

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
		sb.append(", baseYear=");
		sb.append(baseYear);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public MedicaidUraActuals toEntityModel() {
		MedicaidUraActualsImpl medicaidUraActualsImpl = new MedicaidUraActualsImpl();

		medicaidUraActualsImpl.setPeriodSid(periodSid);

		if (priceType == null) {
			medicaidUraActualsImpl.setPriceType(StringPool.BLANK);
		}
		else {
			medicaidUraActualsImpl.setPriceType(priceType);
		}

		medicaidUraActualsImpl.setActualPrice(actualPrice);

		if (notes == null) {
			medicaidUraActualsImpl.setNotes(StringPool.BLANK);
		}
		else {
			medicaidUraActualsImpl.setNotes(notes);
		}

		medicaidUraActualsImpl.setNaProjDetailsSid(naProjDetailsSid);
		medicaidUraActualsImpl.setBaseYear(baseYear);

		medicaidUraActualsImpl.resetOriginalValues();

		return medicaidUraActualsImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		periodSid = objectInput.readInt();
		priceType = objectInput.readUTF();

		actualPrice = objectInput.readDouble();
		notes = objectInput.readUTF();

		naProjDetailsSid = objectInput.readInt();

		baseYear = objectInput.readDouble();

		medicaidUraActualsPK = new MedicaidUraActualsPK(periodSid, priceType,
				naProjDetailsSid);
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

		objectOutput.writeDouble(baseYear);
	}

	public int periodSid;
	public String priceType;
	public double actualPrice;
	public String notes;
	public int naProjDetailsSid;
	public double baseYear;
	public transient MedicaidUraActualsPK medicaidUraActualsPK;
}