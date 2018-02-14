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

import com.stpl.app.model.StNmAssumptions;
import com.stpl.app.service.persistence.StNmAssumptionsPK;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing StNmAssumptions in entity cache.
 *
 * @author
 * @see StNmAssumptions
 * @generated
 */
@ProviderType
public class StNmAssumptionsCacheModel implements CacheModel<StNmAssumptions>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof StNmAssumptionsCacheModel)) {
			return false;
		}

		StNmAssumptionsCacheModel stNmAssumptionsCacheModel = (StNmAssumptionsCacheModel)obj;

		if (stNmAssumptionsPK.equals(
					stNmAssumptionsCacheModel.stNmAssumptionsPK)) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, stNmAssumptionsPK);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(45);

		sb.append("{lastModifiedDate=");
		sb.append(lastModifiedDate);
		sb.append(", parent=");
		sb.append(parent);
		sb.append(", projectionPeriod=");
		sb.append(projectionPeriod);
		sb.append(", commentary=");
		sb.append(commentary);
		sb.append(", nmAssumptionsSid=");
		sb.append(nmAssumptionsSid);
		sb.append(", projectionDetailsSid=");
		sb.append(projectionDetailsSid);
		sb.append(", netSalesPrior=");
		sb.append(netSalesPrior);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", grossSalesPercentChange=");
		sb.append(grossSalesPercentChange);
		sb.append(", totalDiscountPercentChange=");
		sb.append(totalDiscountPercentChange);
		sb.append(", reasonCodes=");
		sb.append(reasonCodes);
		sb.append(", totalDiscountPercentProjected=");
		sb.append(totalDiscountPercentProjected);
		sb.append(", totalDiscountPercentPrior=");
		sb.append(totalDiscountPercentPrior);
		sb.append(", netSalesProjected=");
		sb.append(netSalesProjected);
		sb.append(", stNmAssumptionsSid=");
		sb.append(stNmAssumptionsSid);
		sb.append(", grossSalesProjected=");
		sb.append(grossSalesProjected);
		sb.append(", sessionId=");
		sb.append(sessionId);
		sb.append(", grossSalesPrior=");
		sb.append(grossSalesPrior);
		sb.append(", isChecked=");
		sb.append(isChecked);
		sb.append(", camId=");
		sb.append(camId);
		sb.append(", netSalesPercentChange=");
		sb.append(netSalesPercentChange);
		sb.append(", segment=");
		sb.append(segment);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public StNmAssumptions toEntityModel() {
		StNmAssumptionsImpl stNmAssumptionsImpl = new StNmAssumptionsImpl();

		if (lastModifiedDate == Long.MIN_VALUE) {
			stNmAssumptionsImpl.setLastModifiedDate(null);
		}
		else {
			stNmAssumptionsImpl.setLastModifiedDate(new Date(lastModifiedDate));
		}

		stNmAssumptionsImpl.setParent(parent);
		stNmAssumptionsImpl.setProjectionPeriod(projectionPeriod);

		if (commentary == null) {
			stNmAssumptionsImpl.setCommentary(StringPool.BLANK);
		}
		else {
			stNmAssumptionsImpl.setCommentary(commentary);
		}

		stNmAssumptionsImpl.setNmAssumptionsSid(nmAssumptionsSid);
		stNmAssumptionsImpl.setProjectionDetailsSid(projectionDetailsSid);
		stNmAssumptionsImpl.setNetSalesPrior(netSalesPrior);
		stNmAssumptionsImpl.setUserId(userId);
		stNmAssumptionsImpl.setGrossSalesPercentChange(grossSalesPercentChange);
		stNmAssumptionsImpl.setTotalDiscountPercentChange(totalDiscountPercentChange);

		if (reasonCodes == null) {
			stNmAssumptionsImpl.setReasonCodes(StringPool.BLANK);
		}
		else {
			stNmAssumptionsImpl.setReasonCodes(reasonCodes);
		}

		stNmAssumptionsImpl.setTotalDiscountPercentProjected(totalDiscountPercentProjected);
		stNmAssumptionsImpl.setTotalDiscountPercentPrior(totalDiscountPercentPrior);
		stNmAssumptionsImpl.setNetSalesProjected(netSalesProjected);

		if (stNmAssumptionsSid == null) {
			stNmAssumptionsImpl.setStNmAssumptionsSid(StringPool.BLANK);
		}
		else {
			stNmAssumptionsImpl.setStNmAssumptionsSid(stNmAssumptionsSid);
		}

		stNmAssumptionsImpl.setGrossSalesProjected(grossSalesProjected);
		stNmAssumptionsImpl.setSessionId(sessionId);
		stNmAssumptionsImpl.setGrossSalesPrior(grossSalesPrior);
		stNmAssumptionsImpl.setIsChecked(isChecked);
		stNmAssumptionsImpl.setCamId(camId);
		stNmAssumptionsImpl.setNetSalesPercentChange(netSalesPercentChange);

		if (segment == null) {
			stNmAssumptionsImpl.setSegment(StringPool.BLANK);
		}
		else {
			stNmAssumptionsImpl.setSegment(segment);
		}

		stNmAssumptionsImpl.resetOriginalValues();

		return stNmAssumptionsImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		lastModifiedDate = objectInput.readLong();

		parent = objectInput.readBoolean();

		projectionPeriod = objectInput.readInt();
		commentary = objectInput.readUTF();

		nmAssumptionsSid = objectInput.readInt();

		projectionDetailsSid = objectInput.readInt();

		netSalesPrior = objectInput.readDouble();

		userId = objectInput.readInt();

		grossSalesPercentChange = objectInput.readDouble();

		totalDiscountPercentChange = objectInput.readDouble();
		reasonCodes = objectInput.readUTF();

		totalDiscountPercentProjected = objectInput.readDouble();

		totalDiscountPercentPrior = objectInput.readDouble();

		netSalesProjected = objectInput.readDouble();
		stNmAssumptionsSid = objectInput.readUTF();

		grossSalesProjected = objectInput.readDouble();

		sessionId = objectInput.readInt();

		grossSalesPrior = objectInput.readDouble();

		isChecked = objectInput.readBoolean();

		camId = objectInput.readInt();

		netSalesPercentChange = objectInput.readDouble();
		segment = objectInput.readUTF();

		stNmAssumptionsPK = new StNmAssumptionsPK(projectionPeriod,
				nmAssumptionsSid, projectionDetailsSid, userId,
				stNmAssumptionsSid, sessionId);
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(lastModifiedDate);

		objectOutput.writeBoolean(parent);

		objectOutput.writeInt(projectionPeriod);

		if (commentary == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(commentary);
		}

		objectOutput.writeInt(nmAssumptionsSid);

		objectOutput.writeInt(projectionDetailsSid);

		objectOutput.writeDouble(netSalesPrior);

		objectOutput.writeInt(userId);

		objectOutput.writeDouble(grossSalesPercentChange);

		objectOutput.writeDouble(totalDiscountPercentChange);

		if (reasonCodes == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(reasonCodes);
		}

		objectOutput.writeDouble(totalDiscountPercentProjected);

		objectOutput.writeDouble(totalDiscountPercentPrior);

		objectOutput.writeDouble(netSalesProjected);

		if (stNmAssumptionsSid == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(stNmAssumptionsSid);
		}

		objectOutput.writeDouble(grossSalesProjected);

		objectOutput.writeInt(sessionId);

		objectOutput.writeDouble(grossSalesPrior);

		objectOutput.writeBoolean(isChecked);

		objectOutput.writeInt(camId);

		objectOutput.writeDouble(netSalesPercentChange);

		if (segment == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(segment);
		}
	}

	public long lastModifiedDate;
	public boolean parent;
	public int projectionPeriod;
	public String commentary;
	public int nmAssumptionsSid;
	public int projectionDetailsSid;
	public double netSalesPrior;
	public int userId;
	public double grossSalesPercentChange;
	public double totalDiscountPercentChange;
	public String reasonCodes;
	public double totalDiscountPercentProjected;
	public double totalDiscountPercentPrior;
	public double netSalesProjected;
	public String stNmAssumptionsSid;
	public double grossSalesProjected;
	public int sessionId;
	public double grossSalesPrior;
	public boolean isChecked;
	public int camId;
	public double netSalesPercentChange;
	public String segment;
	public transient StNmAssumptionsPK stNmAssumptionsPK;
}