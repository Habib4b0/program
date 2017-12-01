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

import com.stpl.app.model.MSupplementalDiscProj;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing MSupplementalDiscProj in entity cache.
 *
 * @author
 * @see MSupplementalDiscProj
 * @generated
 */
@ProviderType
public class MSupplementalDiscProjCacheModel implements CacheModel<MSupplementalDiscProj>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof MSupplementalDiscProjCacheModel)) {
			return false;
		}

		MSupplementalDiscProjCacheModel mSupplementalDiscProjCacheModel = (MSupplementalDiscProjCacheModel)obj;

		if (projectionDetailsSid == mSupplementalDiscProjCacheModel.projectionDetailsSid) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, projectionDetailsSid);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(25);

		sb.append("{methodology=");
		sb.append(methodology);
		sb.append(", projectionRate=");
		sb.append(projectionRate);
		sb.append(", parity=");
		sb.append(parity);
		sb.append(", periodSid=");
		sb.append(periodSid);
		sb.append(", discountRate1=");
		sb.append(discountRate1);
		sb.append(", parityReference=");
		sb.append(parityReference);
		sb.append(", projectionDetailsSid=");
		sb.append(projectionDetailsSid);
		sb.append(", discountRate2=");
		sb.append(discountRate2);
		sb.append(", parityDiscount=");
		sb.append(parityDiscount);
		sb.append(", projectionSales=");
		sb.append(projectionSales);
		sb.append(", contractPrice=");
		sb.append(contractPrice);
		sb.append(", access=");
		sb.append(access);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public MSupplementalDiscProj toEntityModel() {
		MSupplementalDiscProjImpl mSupplementalDiscProjImpl = new MSupplementalDiscProjImpl();

		if (methodology == null) {
			mSupplementalDiscProjImpl.setMethodology(StringPool.BLANK);
		}
		else {
			mSupplementalDiscProjImpl.setMethodology(methodology);
		}

		mSupplementalDiscProjImpl.setProjectionRate(projectionRate);
		mSupplementalDiscProjImpl.setParity(parity);
		mSupplementalDiscProjImpl.setPeriodSid(periodSid);
		mSupplementalDiscProjImpl.setDiscountRate1(discountRate1);

		if (parityReference == null) {
			mSupplementalDiscProjImpl.setParityReference(StringPool.BLANK);
		}
		else {
			mSupplementalDiscProjImpl.setParityReference(parityReference);
		}

		mSupplementalDiscProjImpl.setProjectionDetailsSid(projectionDetailsSid);
		mSupplementalDiscProjImpl.setDiscountRate2(discountRate2);
		mSupplementalDiscProjImpl.setParityDiscount(parityDiscount);
		mSupplementalDiscProjImpl.setProjectionSales(projectionSales);
		mSupplementalDiscProjImpl.setContractPrice(contractPrice);

		if (access == null) {
			mSupplementalDiscProjImpl.setAccess(StringPool.BLANK);
		}
		else {
			mSupplementalDiscProjImpl.setAccess(access);
		}

		mSupplementalDiscProjImpl.resetOriginalValues();

		return mSupplementalDiscProjImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		methodology = objectInput.readUTF();

		projectionRate = objectInput.readDouble();

		parity = objectInput.readBoolean();

		periodSid = objectInput.readInt();

		discountRate1 = objectInput.readDouble();
		parityReference = objectInput.readUTF();

		projectionDetailsSid = objectInput.readInt();

		discountRate2 = objectInput.readDouble();

		parityDiscount = objectInput.readDouble();

		projectionSales = objectInput.readDouble();

		contractPrice = objectInput.readDouble();
		access = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		if (methodology == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(methodology);
		}

		objectOutput.writeDouble(projectionRate);

		objectOutput.writeBoolean(parity);

		objectOutput.writeInt(periodSid);

		objectOutput.writeDouble(discountRate1);

		if (parityReference == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(parityReference);
		}

		objectOutput.writeInt(projectionDetailsSid);

		objectOutput.writeDouble(discountRate2);

		objectOutput.writeDouble(parityDiscount);

		objectOutput.writeDouble(projectionSales);

		objectOutput.writeDouble(contractPrice);

		if (access == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(access);
		}
	}

	public String methodology;
	public double projectionRate;
	public boolean parity;
	public int periodSid;
	public double discountRate1;
	public String parityReference;
	public int projectionDetailsSid;
	public double discountRate2;
	public double parityDiscount;
	public double projectionSales;
	public double contractPrice;
	public String access;
}