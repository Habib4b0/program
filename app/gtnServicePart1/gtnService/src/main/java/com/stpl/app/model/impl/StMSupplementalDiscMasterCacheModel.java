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

import com.stpl.app.model.StMSupplementalDiscMaster;
import com.stpl.app.service.persistence.StMSupplementalDiscMasterPK;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing StMSupplementalDiscMaster in entity cache.
 *
 * @author
 * @see StMSupplementalDiscMaster
 * @generated
 */
@ProviderType
public class StMSupplementalDiscMasterCacheModel implements CacheModel<StMSupplementalDiscMaster>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof StMSupplementalDiscMasterCacheModel)) {
			return false;
		}

		StMSupplementalDiscMasterCacheModel stMSupplementalDiscMasterCacheModel = (StMSupplementalDiscMasterCacheModel)obj;

		if (stMSupplementalDiscMasterPK.equals(
					stMSupplementalDiscMasterCacheModel.stMSupplementalDiscMasterPK)) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, stMSupplementalDiscMasterPK);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(25);

		sb.append("{actualDiscountRate2=");
		sb.append(actualDiscountRate2);
		sb.append(", actualDiscountRate1=");
		sb.append(actualDiscountRate1);
		sb.append(", marketType=");
		sb.append(marketType);
		sb.append(", actualMethodology=");
		sb.append(actualMethodology);
		sb.append(", actualContractPrice=");
		sb.append(actualContractPrice);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", lastModifiedDate=");
		sb.append(lastModifiedDate);
		sb.append(", projectionDetailsSid=");
		sb.append(projectionDetailsSid);
		sb.append(", actualDiscount=");
		sb.append(actualDiscount);
		sb.append(", sessionId=");
		sb.append(sessionId);
		sb.append(", checkRecord=");
		sb.append(checkRecord);
		sb.append(", contractEndDate=");
		sb.append(contractEndDate);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public StMSupplementalDiscMaster toEntityModel() {
		StMSupplementalDiscMasterImpl stMSupplementalDiscMasterImpl = new StMSupplementalDiscMasterImpl();

		stMSupplementalDiscMasterImpl.setActualDiscountRate2(actualDiscountRate2);
		stMSupplementalDiscMasterImpl.setActualDiscountRate1(actualDiscountRate1);

		if (marketType == null) {
			stMSupplementalDiscMasterImpl.setMarketType(StringPool.BLANK);
		}
		else {
			stMSupplementalDiscMasterImpl.setMarketType(marketType);
		}

		if (actualMethodology == null) {
			stMSupplementalDiscMasterImpl.setActualMethodology(StringPool.BLANK);
		}
		else {
			stMSupplementalDiscMasterImpl.setActualMethodology(actualMethodology);
		}

		stMSupplementalDiscMasterImpl.setActualContractPrice(actualContractPrice);
		stMSupplementalDiscMasterImpl.setUserId(userId);

		if (lastModifiedDate == Long.MIN_VALUE) {
			stMSupplementalDiscMasterImpl.setLastModifiedDate(null);
		}
		else {
			stMSupplementalDiscMasterImpl.setLastModifiedDate(new Date(
					lastModifiedDate));
		}

		stMSupplementalDiscMasterImpl.setProjectionDetailsSid(projectionDetailsSid);
		stMSupplementalDiscMasterImpl.setActualDiscount(actualDiscount);
		stMSupplementalDiscMasterImpl.setSessionId(sessionId);
		stMSupplementalDiscMasterImpl.setCheckRecord(checkRecord);

		if (contractEndDate == Long.MIN_VALUE) {
			stMSupplementalDiscMasterImpl.setContractEndDate(null);
		}
		else {
			stMSupplementalDiscMasterImpl.setContractEndDate(new Date(
					contractEndDate));
		}

		stMSupplementalDiscMasterImpl.resetOriginalValues();

		return stMSupplementalDiscMasterImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		actualDiscountRate2 = objectInput.readDouble();

		actualDiscountRate1 = objectInput.readDouble();
		marketType = objectInput.readUTF();
		actualMethodology = objectInput.readUTF();

		actualContractPrice = objectInput.readDouble();

		userId = objectInput.readInt();
		lastModifiedDate = objectInput.readLong();

		projectionDetailsSid = objectInput.readInt();

		actualDiscount = objectInput.readDouble();

		sessionId = objectInput.readInt();

		checkRecord = objectInput.readInt();
		contractEndDate = objectInput.readLong();

		stMSupplementalDiscMasterPK = new StMSupplementalDiscMasterPK(userId,
				projectionDetailsSid, sessionId);
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeDouble(actualDiscountRate2);

		objectOutput.writeDouble(actualDiscountRate1);

		if (marketType == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(marketType);
		}

		if (actualMethodology == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(actualMethodology);
		}

		objectOutput.writeDouble(actualContractPrice);

		objectOutput.writeInt(userId);
		objectOutput.writeLong(lastModifiedDate);

		objectOutput.writeInt(projectionDetailsSid);

		objectOutput.writeDouble(actualDiscount);

		objectOutput.writeInt(sessionId);

		objectOutput.writeInt(checkRecord);
		objectOutput.writeLong(contractEndDate);
	}

	public double actualDiscountRate2;
	public double actualDiscountRate1;
	public String marketType;
	public String actualMethodology;
	public double actualContractPrice;
	public int userId;
	public long lastModifiedDate;
	public int projectionDetailsSid;
	public double actualDiscount;
	public int sessionId;
	public int checkRecord;
	public long contractEndDate;
	public transient StMSupplementalDiscMasterPK stMSupplementalDiscMasterPK;
}