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

import com.stpl.app.model.ChProjectionDiscount;
import com.stpl.app.service.persistence.ChProjectionDiscountPK;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing ChProjectionDiscount in entity cache.
 *
 * @author
 * @see ChProjectionDiscount
 * @generated
 */
@ProviderType
public class ChProjectionDiscountCacheModel implements CacheModel<ChProjectionDiscount>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ChProjectionDiscountCacheModel)) {
			return false;
		}

		ChProjectionDiscountCacheModel chProjectionDiscountCacheModel = (ChProjectionDiscountCacheModel)obj;

		if (chProjectionDiscountPK.equals(
					chProjectionDiscountCacheModel.chProjectionDiscountPK)) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, chProjectionDiscountPK);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(27);

		sb.append("{adjustmentMethodology=");
		sb.append(adjustmentMethodology);
		sb.append(", productGrowth=");
		sb.append(productGrowth);
		sb.append(", projectionRate=");
		sb.append(projectionRate);
		sb.append(", projectionDetailsSid=");
		sb.append(projectionDetailsSid);
		sb.append(", accountGrowth=");
		sb.append(accountGrowth);
		sb.append(", discountAmount=");
		sb.append(discountAmount);
		sb.append(", discountRate=");
		sb.append(discountRate);
		sb.append(", periodSid=");
		sb.append(periodSid);
		sb.append(", adjustmentBasis=");
		sb.append(adjustmentBasis);
		sb.append(", adjustmentValue=");
		sb.append(adjustmentValue);
		sb.append(", adjustmentType=");
		sb.append(adjustmentType);
		sb.append(", rsModelSid=");
		sb.append(rsModelSid);
		sb.append(", projectionSales=");
		sb.append(projectionSales);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ChProjectionDiscount toEntityModel() {
		ChProjectionDiscountImpl chProjectionDiscountImpl = new ChProjectionDiscountImpl();

		if (adjustmentMethodology == null) {
			chProjectionDiscountImpl.setAdjustmentMethodology(StringPool.BLANK);
		}
		else {
			chProjectionDiscountImpl.setAdjustmentMethodology(adjustmentMethodology);
		}

		chProjectionDiscountImpl.setProductGrowth(productGrowth);
		chProjectionDiscountImpl.setProjectionRate(projectionRate);
		chProjectionDiscountImpl.setProjectionDetailsSid(projectionDetailsSid);
		chProjectionDiscountImpl.setAccountGrowth(accountGrowth);
		chProjectionDiscountImpl.setDiscountAmount(discountAmount);
		chProjectionDiscountImpl.setDiscountRate(discountRate);
		chProjectionDiscountImpl.setPeriodSid(periodSid);

		if (adjustmentBasis == null) {
			chProjectionDiscountImpl.setAdjustmentBasis(StringPool.BLANK);
		}
		else {
			chProjectionDiscountImpl.setAdjustmentBasis(adjustmentBasis);
		}

		chProjectionDiscountImpl.setAdjustmentValue(adjustmentValue);

		if (adjustmentType == null) {
			chProjectionDiscountImpl.setAdjustmentType(StringPool.BLANK);
		}
		else {
			chProjectionDiscountImpl.setAdjustmentType(adjustmentType);
		}

		chProjectionDiscountImpl.setRsModelSid(rsModelSid);
		chProjectionDiscountImpl.setProjectionSales(projectionSales);

		chProjectionDiscountImpl.resetOriginalValues();

		return chProjectionDiscountImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		adjustmentMethodology = objectInput.readUTF();

		productGrowth = objectInput.readDouble();

		projectionRate = objectInput.readDouble();

		projectionDetailsSid = objectInput.readInt();

		accountGrowth = objectInput.readDouble();

		discountAmount = objectInput.readDouble();

		discountRate = objectInput.readDouble();

		periodSid = objectInput.readInt();
		adjustmentBasis = objectInput.readUTF();

		adjustmentValue = objectInput.readDouble();
		adjustmentType = objectInput.readUTF();

		rsModelSid = objectInput.readInt();

		projectionSales = objectInput.readDouble();

		chProjectionDiscountPK = new ChProjectionDiscountPK(projectionDetailsSid,
				periodSid, rsModelSid);
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		if (adjustmentMethodology == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(adjustmentMethodology);
		}

		objectOutput.writeDouble(productGrowth);

		objectOutput.writeDouble(projectionRate);

		objectOutput.writeInt(projectionDetailsSid);

		objectOutput.writeDouble(accountGrowth);

		objectOutput.writeDouble(discountAmount);

		objectOutput.writeDouble(discountRate);

		objectOutput.writeInt(periodSid);

		if (adjustmentBasis == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(adjustmentBasis);
		}

		objectOutput.writeDouble(adjustmentValue);

		if (adjustmentType == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(adjustmentType);
		}

		objectOutput.writeInt(rsModelSid);

		objectOutput.writeDouble(projectionSales);
	}

	public String adjustmentMethodology;
	public double productGrowth;
	public double projectionRate;
	public int projectionDetailsSid;
	public double accountGrowth;
	public double discountAmount;
	public double discountRate;
	public int periodSid;
	public String adjustmentBasis;
	public double adjustmentValue;
	public String adjustmentType;
	public int rsModelSid;
	public double projectionSales;
	public transient ChProjectionDiscountPK chProjectionDiscountPK;
}