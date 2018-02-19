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

import com.stpl.app.model.StMSupplementalDiscProj;
import com.stpl.app.service.persistence.StMSupplementalDiscProjPK;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing StMSupplementalDiscProj in entity cache.
 *
 * @author
 * @see StMSupplementalDiscProj
 * @generated
 */
@ProviderType
public class StMSupplementalDiscProjCacheModel implements CacheModel<StMSupplementalDiscProj>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof StMSupplementalDiscProjCacheModel)) {
			return false;
		}

		StMSupplementalDiscProjCacheModel stMSupplementalDiscProjCacheModel = (StMSupplementalDiscProjCacheModel)obj;

		if (stMSupplementalDiscProjPK.equals(
					stMSupplementalDiscProjCacheModel.stMSupplementalDiscProjPK)) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, stMSupplementalDiscProjPK);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(31);

		sb.append("{projectionRate=");
		sb.append(projectionRate);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", lastModifiedDate=");
		sb.append(lastModifiedDate);
		sb.append(", parityReference=");
		sb.append(parityReference);
		sb.append(", projectionSales=");
		sb.append(projectionSales);
		sb.append(", contractPrice=");
		sb.append(contractPrice);
		sb.append(", methodology=");
		sb.append(methodology);
		sb.append(", parity=");
		sb.append(parity);
		sb.append(", periodSid=");
		sb.append(periodSid);
		sb.append(", discountRate1=");
		sb.append(discountRate1);
		sb.append(", projectionDetailsSid=");
		sb.append(projectionDetailsSid);
		sb.append(", discountRate2=");
		sb.append(discountRate2);
		sb.append(", parityDiscount=");
		sb.append(parityDiscount);
		sb.append(", sessionId=");
		sb.append(sessionId);
		sb.append(", access=");
		sb.append(access);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public StMSupplementalDiscProj toEntityModel() {
		StMSupplementalDiscProjImpl stMSupplementalDiscProjImpl = new StMSupplementalDiscProjImpl();

		stMSupplementalDiscProjImpl.setProjectionRate(projectionRate);
		stMSupplementalDiscProjImpl.setUserId(userId);

		if (lastModifiedDate == Long.MIN_VALUE) {
			stMSupplementalDiscProjImpl.setLastModifiedDate(null);
		}
		else {
			stMSupplementalDiscProjImpl.setLastModifiedDate(new Date(
					lastModifiedDate));
		}

		if (parityReference == null) {
			stMSupplementalDiscProjImpl.setParityReference(StringPool.BLANK);
		}
		else {
			stMSupplementalDiscProjImpl.setParityReference(parityReference);
		}

		stMSupplementalDiscProjImpl.setProjectionSales(projectionSales);
		stMSupplementalDiscProjImpl.setContractPrice(contractPrice);

		if (methodology == null) {
			stMSupplementalDiscProjImpl.setMethodology(StringPool.BLANK);
		}
		else {
			stMSupplementalDiscProjImpl.setMethodology(methodology);
		}

		stMSupplementalDiscProjImpl.setParity(parity);
		stMSupplementalDiscProjImpl.setPeriodSid(periodSid);
		stMSupplementalDiscProjImpl.setDiscountRate1(discountRate1);
		stMSupplementalDiscProjImpl.setProjectionDetailsSid(projectionDetailsSid);
		stMSupplementalDiscProjImpl.setDiscountRate2(discountRate2);
		stMSupplementalDiscProjImpl.setParityDiscount(parityDiscount);
		stMSupplementalDiscProjImpl.setSessionId(sessionId);

		if (access == null) {
			stMSupplementalDiscProjImpl.setAccess(StringPool.BLANK);
		}
		else {
			stMSupplementalDiscProjImpl.setAccess(access);
		}

		stMSupplementalDiscProjImpl.resetOriginalValues();

		return stMSupplementalDiscProjImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		projectionRate = objectInput.readDouble();

		userId = objectInput.readInt();
		lastModifiedDate = objectInput.readLong();
		parityReference = objectInput.readUTF();

		projectionSales = objectInput.readDouble();

		contractPrice = objectInput.readDouble();
		methodology = objectInput.readUTF();

		parity = objectInput.readBoolean();

		periodSid = objectInput.readInt();

		discountRate1 = objectInput.readDouble();

		projectionDetailsSid = objectInput.readInt();

		discountRate2 = objectInput.readDouble();

		parityDiscount = objectInput.readDouble();

		sessionId = objectInput.readInt();
		access = objectInput.readUTF();

		stMSupplementalDiscProjPK = new StMSupplementalDiscProjPK(userId,
				projectionDetailsSid, sessionId);
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeDouble(projectionRate);

		objectOutput.writeInt(userId);
		objectOutput.writeLong(lastModifiedDate);

		if (parityReference == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(parityReference);
		}

		objectOutput.writeDouble(projectionSales);

		objectOutput.writeDouble(contractPrice);

		if (methodology == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(methodology);
		}

		objectOutput.writeBoolean(parity);

		objectOutput.writeInt(periodSid);

		objectOutput.writeDouble(discountRate1);

		objectOutput.writeInt(projectionDetailsSid);

		objectOutput.writeDouble(discountRate2);

		objectOutput.writeDouble(parityDiscount);

		objectOutput.writeInt(sessionId);

		if (access == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(access);
		}
	}

	public double projectionRate;
	public int userId;
	public long lastModifiedDate;
	public String parityReference;
	public double projectionSales;
	public double contractPrice;
	public String methodology;
	public boolean parity;
	public int periodSid;
	public double discountRate1;
	public int projectionDetailsSid;
	public double discountRate2;
	public double parityDiscount;
	public int sessionId;
	public String access;
	public transient StMSupplementalDiscProjPK stMSupplementalDiscProjPK;
}