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

import com.stpl.app.model.NationalAssumptionsProj;
import com.stpl.app.service.persistence.NationalAssumptionsProjPK;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing NationalAssumptionsProj in entity cache.
 *
 * @author
 * @see NationalAssumptionsProj
 * @generated
 */
@ProviderType
public class NationalAssumptionsProjCacheModel implements CacheModel<NationalAssumptionsProj>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof NationalAssumptionsProjCacheModel)) {
			return false;
		}

		NationalAssumptionsProjCacheModel nationalAssumptionsProjCacheModel = (NationalAssumptionsProjCacheModel)obj;

		if (nationalAssumptionsProjPK.equals(
					nationalAssumptionsProjCacheModel.nationalAssumptionsProjPK)) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, nationalAssumptionsProjPK);
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
		sb.append(", projectionPrice=");
		sb.append(projectionPrice);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public NationalAssumptionsProj toEntityModel() {
		NationalAssumptionsProjImpl nationalAssumptionsProjImpl = new NationalAssumptionsProjImpl();

		nationalAssumptionsProjImpl.setPeriodSid(periodSid);
		nationalAssumptionsProjImpl.setItemMasterSid(itemMasterSid);

		if (priceType == null) {
			nationalAssumptionsProjImpl.setPriceType(StringPool.BLANK);
		}
		else {
			nationalAssumptionsProjImpl.setPriceType(priceType);
		}

		nationalAssumptionsProjImpl.setProjectionPrice(projectionPrice);

		nationalAssumptionsProjImpl.resetOriginalValues();

		return nationalAssumptionsProjImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		periodSid = objectInput.readInt();

		itemMasterSid = objectInput.readInt();
		priceType = objectInput.readUTF();

		projectionPrice = objectInput.readDouble();

		nationalAssumptionsProjPK = new NationalAssumptionsProjPK(periodSid,
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

		objectOutput.writeDouble(projectionPrice);
	}

	public int periodSid;
	public int itemMasterSid;
	public String priceType;
	public double projectionPrice;
	public transient NationalAssumptionsProjPK nationalAssumptionsProjPK;
}