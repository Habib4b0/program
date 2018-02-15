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

import com.stpl.app.model.StMAssumptions;
import com.stpl.app.service.persistence.StMAssumptionsPK;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing StMAssumptions in entity cache.
 *
 * @author
 * @see StMAssumptions
 * @generated
 */
@ProviderType
public class StMAssumptionsCacheModel implements CacheModel<StMAssumptions>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof StMAssumptionsCacheModel)) {
			return false;
		}

		StMAssumptionsCacheModel stMAssumptionsCacheModel = (StMAssumptionsCacheModel)obj;

		if (stMAssumptionsPK.equals(stMAssumptionsCacheModel.stMAssumptionsPK)) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, stMAssumptionsPK);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(45);

		sb.append("{grossSalesPercentChange=");
		sb.append(grossSalesPercentChange);
		sb.append(", grossSalesPrior=");
		sb.append(grossSalesPrior);
		sb.append(", projYear=");
		sb.append(projYear);
		sb.append(", totalDiscountPercentProjected=");
		sb.append(totalDiscountPercentProjected);
		sb.append(", camId=");
		sb.append(camId);
		sb.append(", commentary=");
		sb.append(commentary);
		sb.append(", isChecked=");
		sb.append(isChecked);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", lastModifiedDate=");
		sb.append(lastModifiedDate);
		sb.append(", grossSalesProjected=");
		sb.append(grossSalesProjected);
		sb.append(", totalDiscountPercentChange=");
		sb.append(totalDiscountPercentChange);
		sb.append(", totalDiscountPercentPrior=");
		sb.append(totalDiscountPercentPrior);
		sb.append(", netSalesPercentChange=");
		sb.append(netSalesPercentChange);
		sb.append(", parent=");
		sb.append(parent);
		sb.append(", stMAssumptionsSid=");
		sb.append(stMAssumptionsSid);
		sb.append(", projectionPeriod=");
		sb.append(projectionPeriod);
		sb.append(", projectionDetailsSid=");
		sb.append(projectionDetailsSid);
		sb.append(", netSalesPrior=");
		sb.append(netSalesPrior);
		sb.append(", sessionId=");
		sb.append(sessionId);
		sb.append(", netSalesProjected=");
		sb.append(netSalesProjected);
		sb.append(", reasonCodes=");
		sb.append(reasonCodes);
		sb.append(", mAssumptionsSid=");
		sb.append(mAssumptionsSid);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public StMAssumptions toEntityModel() {
		StMAssumptionsImpl stMAssumptionsImpl = new StMAssumptionsImpl();

		stMAssumptionsImpl.setGrossSalesPercentChange(grossSalesPercentChange);
		stMAssumptionsImpl.setGrossSalesPrior(grossSalesPrior);
		stMAssumptionsImpl.setProjYear(projYear);
		stMAssumptionsImpl.setTotalDiscountPercentProjected(totalDiscountPercentProjected);
		stMAssumptionsImpl.setCamId(camId);

		if (commentary == null) {
			stMAssumptionsImpl.setCommentary(StringPool.BLANK);
		}
		else {
			stMAssumptionsImpl.setCommentary(commentary);
		}

		stMAssumptionsImpl.setIsChecked(isChecked);
		stMAssumptionsImpl.setUserId(userId);

		if (lastModifiedDate == Long.MIN_VALUE) {
			stMAssumptionsImpl.setLastModifiedDate(null);
		}
		else {
			stMAssumptionsImpl.setLastModifiedDate(new Date(lastModifiedDate));
		}

		stMAssumptionsImpl.setGrossSalesProjected(grossSalesProjected);
		stMAssumptionsImpl.setTotalDiscountPercentChange(totalDiscountPercentChange);
		stMAssumptionsImpl.setTotalDiscountPercentPrior(totalDiscountPercentPrior);
		stMAssumptionsImpl.setNetSalesPercentChange(netSalesPercentChange);
		stMAssumptionsImpl.setParent(parent);

		if (stMAssumptionsSid == null) {
			stMAssumptionsImpl.setStMAssumptionsSid(StringPool.BLANK);
		}
		else {
			stMAssumptionsImpl.setStMAssumptionsSid(stMAssumptionsSid);
		}

		stMAssumptionsImpl.setProjectionPeriod(projectionPeriod);
		stMAssumptionsImpl.setProjectionDetailsSid(projectionDetailsSid);
		stMAssumptionsImpl.setNetSalesPrior(netSalesPrior);
		stMAssumptionsImpl.setSessionId(sessionId);
		stMAssumptionsImpl.setNetSalesProjected(netSalesProjected);

		if (reasonCodes == null) {
			stMAssumptionsImpl.setReasonCodes(StringPool.BLANK);
		}
		else {
			stMAssumptionsImpl.setReasonCodes(reasonCodes);
		}

		stMAssumptionsImpl.setMAssumptionsSid(mAssumptionsSid);

		stMAssumptionsImpl.resetOriginalValues();

		return stMAssumptionsImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		grossSalesPercentChange = objectInput.readDouble();

		grossSalesPrior = objectInput.readDouble();

		projYear = objectInput.readInt();

		totalDiscountPercentProjected = objectInput.readDouble();

		camId = objectInput.readInt();
		commentary = objectInput.readUTF();

		isChecked = objectInput.readBoolean();

		userId = objectInput.readInt();
		lastModifiedDate = objectInput.readLong();

		grossSalesProjected = objectInput.readDouble();

		totalDiscountPercentChange = objectInput.readDouble();

		totalDiscountPercentPrior = objectInput.readDouble();

		netSalesPercentChange = objectInput.readDouble();

		parent = objectInput.readBoolean();
		stMAssumptionsSid = objectInput.readUTF();

		projectionPeriod = objectInput.readInt();

		projectionDetailsSid = objectInput.readInt();

		netSalesPrior = objectInput.readDouble();

		sessionId = objectInput.readInt();

		netSalesProjected = objectInput.readDouble();
		reasonCodes = objectInput.readUTF();

		mAssumptionsSid = objectInput.readInt();

		stMAssumptionsPK = new StMAssumptionsPK(projYear, userId,
				stMAssumptionsSid, projectionPeriod, projectionDetailsSid,
				sessionId, mAssumptionsSid);
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeDouble(grossSalesPercentChange);

		objectOutput.writeDouble(grossSalesPrior);

		objectOutput.writeInt(projYear);

		objectOutput.writeDouble(totalDiscountPercentProjected);

		objectOutput.writeInt(camId);

		if (commentary == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(commentary);
		}

		objectOutput.writeBoolean(isChecked);

		objectOutput.writeInt(userId);
		objectOutput.writeLong(lastModifiedDate);

		objectOutput.writeDouble(grossSalesProjected);

		objectOutput.writeDouble(totalDiscountPercentChange);

		objectOutput.writeDouble(totalDiscountPercentPrior);

		objectOutput.writeDouble(netSalesPercentChange);

		objectOutput.writeBoolean(parent);

		if (stMAssumptionsSid == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(stMAssumptionsSid);
		}

		objectOutput.writeInt(projectionPeriod);

		objectOutput.writeInt(projectionDetailsSid);

		objectOutput.writeDouble(netSalesPrior);

		objectOutput.writeInt(sessionId);

		objectOutput.writeDouble(netSalesProjected);

		if (reasonCodes == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(reasonCodes);
		}

		objectOutput.writeInt(mAssumptionsSid);
	}

	public double grossSalesPercentChange;
	public double grossSalesPrior;
	public int projYear;
	public double totalDiscountPercentProjected;
	public int camId;
	public String commentary;
	public boolean isChecked;
	public int userId;
	public long lastModifiedDate;
	public double grossSalesProjected;
	public double totalDiscountPercentChange;
	public double totalDiscountPercentPrior;
	public double netSalesPercentChange;
	public boolean parent;
	public String stMAssumptionsSid;
	public int projectionPeriod;
	public int projectionDetailsSid;
	public double netSalesPrior;
	public int sessionId;
	public double netSalesProjected;
	public String reasonCodes;
	public int mAssumptionsSid;
	public transient StMAssumptionsPK stMAssumptionsPK;
}