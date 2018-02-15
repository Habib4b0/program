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

import com.stpl.app.model.StNmDiscountProjection;
import com.stpl.app.service.persistence.StNmDiscountProjectionPK;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing StNmDiscountProjection in entity cache.
 *
 * @author
 * @see StNmDiscountProjection
 * @generated
 */
@ProviderType
public class StNmDiscountProjectionCacheModel implements CacheModel<StNmDiscountProjection>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof StNmDiscountProjectionCacheModel)) {
			return false;
		}

		StNmDiscountProjectionCacheModel stNmDiscountProjectionCacheModel = (StNmDiscountProjectionCacheModel)obj;

		if (stNmDiscountProjectionPK.equals(
					stNmDiscountProjectionCacheModel.stNmDiscountProjectionPK)) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, stNmDiscountProjectionPK);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(27);

		sb.append("{projectionRate=");
		sb.append(projectionRate);
		sb.append(", adjustmentValue=");
		sb.append(adjustmentValue);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", lastModifiedDate=");
		sb.append(lastModifiedDate);
		sb.append(", discountRate=");
		sb.append(discountRate);
		sb.append(", projectionSales=");
		sb.append(projectionSales);
		sb.append(", adjustmentType=");
		sb.append(adjustmentType);
		sb.append(", adjustmentBasis=");
		sb.append(adjustmentBasis);
		sb.append(", periodSid=");
		sb.append(periodSid);
		sb.append(", adjustmentMethodology=");
		sb.append(adjustmentMethodology);
		sb.append(", projectionDetailsSid=");
		sb.append(projectionDetailsSid);
		sb.append(", rsModelSid=");
		sb.append(rsModelSid);
		sb.append(", sessionId=");
		sb.append(sessionId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public StNmDiscountProjection toEntityModel() {
		StNmDiscountProjectionImpl stNmDiscountProjectionImpl = new StNmDiscountProjectionImpl();

		stNmDiscountProjectionImpl.setProjectionRate(projectionRate);
		stNmDiscountProjectionImpl.setAdjustmentValue(adjustmentValue);
		stNmDiscountProjectionImpl.setUserId(userId);

		if (lastModifiedDate == Long.MIN_VALUE) {
			stNmDiscountProjectionImpl.setLastModifiedDate(null);
		}
		else {
			stNmDiscountProjectionImpl.setLastModifiedDate(new Date(
					lastModifiedDate));
		}

		stNmDiscountProjectionImpl.setDiscountRate(discountRate);
		stNmDiscountProjectionImpl.setProjectionSales(projectionSales);

		if (adjustmentType == null) {
			stNmDiscountProjectionImpl.setAdjustmentType(StringPool.BLANK);
		}
		else {
			stNmDiscountProjectionImpl.setAdjustmentType(adjustmentType);
		}

		if (adjustmentBasis == null) {
			stNmDiscountProjectionImpl.setAdjustmentBasis(StringPool.BLANK);
		}
		else {
			stNmDiscountProjectionImpl.setAdjustmentBasis(adjustmentBasis);
		}

		stNmDiscountProjectionImpl.setPeriodSid(periodSid);

		if (adjustmentMethodology == null) {
			stNmDiscountProjectionImpl.setAdjustmentMethodology(StringPool.BLANK);
		}
		else {
			stNmDiscountProjectionImpl.setAdjustmentMethodology(adjustmentMethodology);
		}

		stNmDiscountProjectionImpl.setProjectionDetailsSid(projectionDetailsSid);
		stNmDiscountProjectionImpl.setRsModelSid(rsModelSid);
		stNmDiscountProjectionImpl.setSessionId(sessionId);

		stNmDiscountProjectionImpl.resetOriginalValues();

		return stNmDiscountProjectionImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		projectionRate = objectInput.readDouble();

		adjustmentValue = objectInput.readDouble();

		userId = objectInput.readInt();
		lastModifiedDate = objectInput.readLong();

		discountRate = objectInput.readDouble();

		projectionSales = objectInput.readDouble();
		adjustmentType = objectInput.readUTF();
		adjustmentBasis = objectInput.readUTF();

		periodSid = objectInput.readInt();
		adjustmentMethodology = objectInput.readUTF();

		projectionDetailsSid = objectInput.readInt();

		rsModelSid = objectInput.readInt();

		sessionId = objectInput.readInt();

		stNmDiscountProjectionPK = new StNmDiscountProjectionPK(userId,
				periodSid, projectionDetailsSid, rsModelSid, sessionId);
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeDouble(projectionRate);

		objectOutput.writeDouble(adjustmentValue);

		objectOutput.writeInt(userId);
		objectOutput.writeLong(lastModifiedDate);

		objectOutput.writeDouble(discountRate);

		objectOutput.writeDouble(projectionSales);

		if (adjustmentType == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(adjustmentType);
		}

		if (adjustmentBasis == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(adjustmentBasis);
		}

		objectOutput.writeInt(periodSid);

		if (adjustmentMethodology == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(adjustmentMethodology);
		}

		objectOutput.writeInt(projectionDetailsSid);

		objectOutput.writeInt(rsModelSid);

		objectOutput.writeInt(sessionId);
	}

	public double projectionRate;
	public double adjustmentValue;
	public int userId;
	public long lastModifiedDate;
	public double discountRate;
	public double projectionSales;
	public String adjustmentType;
	public String adjustmentBasis;
	public int periodSid;
	public String adjustmentMethodology;
	public int projectionDetailsSid;
	public int rsModelSid;
	public int sessionId;
	public transient StNmDiscountProjectionPK stNmDiscountProjectionPK;
}