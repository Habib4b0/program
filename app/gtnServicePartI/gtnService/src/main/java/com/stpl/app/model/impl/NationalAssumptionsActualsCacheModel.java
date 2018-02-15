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

import com.stpl.app.model.NationalAssumptionsActuals;
import com.stpl.app.service.persistence.NationalAssumptionsActualsPK;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing NationalAssumptionsActuals in entity cache.
 *
 * @author
 * @see NationalAssumptionsActuals
 * @generated
 */
@ProviderType
public class NationalAssumptionsActualsCacheModel implements CacheModel<NationalAssumptionsActuals>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof NationalAssumptionsActualsCacheModel)) {
			return false;
		}

		NationalAssumptionsActualsCacheModel nationalAssumptionsActualsCacheModel =
			(NationalAssumptionsActualsCacheModel)obj;

		if (nationalAssumptionsActualsPK.equals(
					nationalAssumptionsActualsCacheModel.nationalAssumptionsActualsPK)) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, nationalAssumptionsActualsPK);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(9);

		sb.append("{periodSid=");
		sb.append(periodSid);
		sb.append(", itemMasterSid=");
		sb.append(itemMasterSid);
		sb.append(", priceType=");
		sb.append(priceType);
		sb.append(", actualPrice=");
		sb.append(actualPrice);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public NationalAssumptionsActuals toEntityModel() {
		NationalAssumptionsActualsImpl nationalAssumptionsActualsImpl = new NationalAssumptionsActualsImpl();

		nationalAssumptionsActualsImpl.setPeriodSid(periodSid);
		nationalAssumptionsActualsImpl.setItemMasterSid(itemMasterSid);

		if (priceType == null) {
			nationalAssumptionsActualsImpl.setPriceType(StringPool.BLANK);
		}
		else {
			nationalAssumptionsActualsImpl.setPriceType(priceType);
		}

		nationalAssumptionsActualsImpl.setActualPrice(actualPrice);

		nationalAssumptionsActualsImpl.resetOriginalValues();

		return nationalAssumptionsActualsImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		periodSid = objectInput.readInt();

		itemMasterSid = objectInput.readInt();
		priceType = objectInput.readUTF();

		actualPrice = objectInput.readDouble();

		nationalAssumptionsActualsPK = new NationalAssumptionsActualsPK(periodSid,
				itemMasterSid, priceType);
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeInt(periodSid);

		objectOutput.writeInt(itemMasterSid);

		if (priceType == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(priceType);
		}

		objectOutput.writeDouble(actualPrice);
	}

	public int periodSid;
	public int itemMasterSid;
	public String priceType;
	public double actualPrice;
	public transient NationalAssumptionsActualsPK nationalAssumptionsActualsPK;
}