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

import com.stpl.app.model.PhsProj;
import com.stpl.app.service.persistence.PhsProjPK;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing PhsProj in entity cache.
 *
 * @author
 * @see PhsProj
 * @generated
 */
@ProviderType
public class PhsProjCacheModel implements CacheModel<PhsProj>, Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof PhsProjCacheModel)) {
			return false;
		}

		PhsProjCacheModel phsProjCacheModel = (PhsProjCacheModel)obj;

		if (phsProjPK.equals(phsProjCacheModel.phsProjPK)) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, phsProjPK);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(13);

		sb.append("{adjustment=");
		sb.append(adjustment);
		sb.append(", periodSid=");
		sb.append(periodSid);
		sb.append(", priceType=");
		sb.append(priceType);
		sb.append(", projectionPrice=");
		sb.append(projectionPrice);
		sb.append(", notes=");
		sb.append(notes);
		sb.append(", naProjDetailsSid=");
		sb.append(naProjDetailsSid);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public PhsProj toEntityModel() {
		PhsProjImpl phsProjImpl = new PhsProjImpl();

		phsProjImpl.setAdjustment(adjustment);
		phsProjImpl.setPeriodSid(periodSid);

		if (priceType == null) {
			phsProjImpl.setPriceType(StringPool.BLANK);
		}
		else {
			phsProjImpl.setPriceType(priceType);
		}

		phsProjImpl.setProjectionPrice(projectionPrice);

		if (notes == null) {
			phsProjImpl.setNotes(StringPool.BLANK);
		}
		else {
			phsProjImpl.setNotes(notes);
		}

		phsProjImpl.setNaProjDetailsSid(naProjDetailsSid);

		phsProjImpl.resetOriginalValues();

		return phsProjImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		adjustment = objectInput.readDouble();

		periodSid = objectInput.readInt();
		priceType = objectInput.readUTF();

		projectionPrice = objectInput.readDouble();
		notes = objectInput.readUTF();

		naProjDetailsSid = objectInput.readInt();

		phsProjPK = new PhsProjPK(periodSid, priceType, naProjDetailsSid);
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeDouble(adjustment);

		objectOutput.writeInt(periodSid);

		if (priceType == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(priceType);
		}

		objectOutput.writeDouble(projectionPrice);

		if (notes == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(notes);
		}

		objectOutput.writeInt(naProjDetailsSid);
	}

	public double adjustment;
	public int periodSid;
	public String priceType;
	public double projectionPrice;
	public String notes;
	public int naProjDetailsSid;
	public transient PhsProjPK phsProjPK;
}