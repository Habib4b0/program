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

import com.stpl.app.model.StChAssumptions;
import com.stpl.app.service.persistence.StChAssumptionsPK;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing StChAssumptions in entity cache.
 *
 * @author
 * @see StChAssumptions
 * @generated
 */
@ProviderType
public class StChAssumptionsCacheModel implements CacheModel<StChAssumptions>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof StChAssumptionsCacheModel)) {
			return false;
		}

		StChAssumptionsCacheModel stChAssumptionsCacheModel = (StChAssumptionsCacheModel)obj;

		if (stChAssumptionsPK.equals(
					stChAssumptionsCacheModel.stChAssumptionsPK)) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, stChAssumptionsPK);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(41);

		sb.append("{lastModifiedDate=");
		sb.append(lastModifiedDate);
		sb.append(", parent=");
		sb.append(parent);
		sb.append(", commentary=");
		sb.append(commentary);
		sb.append(", projectionDetailsSid=");
		sb.append(projectionDetailsSid);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", quarter=");
		sb.append(quarter);
		sb.append(", totalDiscountPercentChange=");
		sb.append(totalDiscountPercentChange);
		sb.append(", reasonCodes=");
		sb.append(reasonCodes);
		sb.append(", year=");
		sb.append(year);
		sb.append(", totalDiscountPercentProjected=");
		sb.append(totalDiscountPercentProjected);
		sb.append(", totalDiscountPercentPrior=");
		sb.append(totalDiscountPercentPrior);
		sb.append(", stChAssumptionsSid=");
		sb.append(stChAssumptionsSid);
		sb.append(", chAssumptionsSid=");
		sb.append(chAssumptionsSid);
		sb.append(", totalDiscountChange=");
		sb.append(totalDiscountChange);
		sb.append(", sessionId=");
		sb.append(sessionId);
		sb.append(", totalDiscountProjected=");
		sb.append(totalDiscountProjected);
		sb.append(", isChecked=");
		sb.append(isChecked);
		sb.append(", camId=");
		sb.append(camId);
		sb.append(", grossTradeSales=");
		sb.append(grossTradeSales);
		sb.append(", totalDiscountPrior=");
		sb.append(totalDiscountPrior);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public StChAssumptions toEntityModel() {
		StChAssumptionsImpl stChAssumptionsImpl = new StChAssumptionsImpl();

		if (lastModifiedDate == Long.MIN_VALUE) {
			stChAssumptionsImpl.setLastModifiedDate(null);
		}
		else {
			stChAssumptionsImpl.setLastModifiedDate(new Date(lastModifiedDate));
		}

		stChAssumptionsImpl.setParent(parent);

		if (commentary == null) {
			stChAssumptionsImpl.setCommentary(StringPool.BLANK);
		}
		else {
			stChAssumptionsImpl.setCommentary(commentary);
		}

		stChAssumptionsImpl.setProjectionDetailsSid(projectionDetailsSid);
		stChAssumptionsImpl.setUserId(userId);
		stChAssumptionsImpl.setQuarter(quarter);
		stChAssumptionsImpl.setTotalDiscountPercentChange(totalDiscountPercentChange);

		if (reasonCodes == null) {
			stChAssumptionsImpl.setReasonCodes(StringPool.BLANK);
		}
		else {
			stChAssumptionsImpl.setReasonCodes(reasonCodes);
		}

		stChAssumptionsImpl.setYear(year);
		stChAssumptionsImpl.setTotalDiscountPercentProjected(totalDiscountPercentProjected);
		stChAssumptionsImpl.setTotalDiscountPercentPrior(totalDiscountPercentPrior);

		if (stChAssumptionsSid == null) {
			stChAssumptionsImpl.setStChAssumptionsSid(StringPool.BLANK);
		}
		else {
			stChAssumptionsImpl.setStChAssumptionsSid(stChAssumptionsSid);
		}

		stChAssumptionsImpl.setChAssumptionsSid(chAssumptionsSid);
		stChAssumptionsImpl.setTotalDiscountChange(totalDiscountChange);
		stChAssumptionsImpl.setSessionId(sessionId);
		stChAssumptionsImpl.setTotalDiscountProjected(totalDiscountProjected);
		stChAssumptionsImpl.setIsChecked(isChecked);
		stChAssumptionsImpl.setCamId(camId);
		stChAssumptionsImpl.setGrossTradeSales(grossTradeSales);
		stChAssumptionsImpl.setTotalDiscountPrior(totalDiscountPrior);

		stChAssumptionsImpl.resetOriginalValues();

		return stChAssumptionsImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		lastModifiedDate = objectInput.readLong();

		parent = objectInput.readBoolean();
		commentary = objectInput.readUTF();

		projectionDetailsSid = objectInput.readInt();

		userId = objectInput.readInt();

		quarter = objectInput.readInt();

		totalDiscountPercentChange = objectInput.readDouble();
		reasonCodes = objectInput.readUTF();

		year = objectInput.readInt();

		totalDiscountPercentProjected = objectInput.readDouble();

		totalDiscountPercentPrior = objectInput.readDouble();
		stChAssumptionsSid = objectInput.readUTF();

		chAssumptionsSid = objectInput.readInt();

		totalDiscountChange = objectInput.readDouble();

		sessionId = objectInput.readInt();

		totalDiscountProjected = objectInput.readDouble();

		isChecked = objectInput.readBoolean();

		camId = objectInput.readInt();

		grossTradeSales = objectInput.readDouble();

		totalDiscountPrior = objectInput.readDouble();

		stChAssumptionsPK = new StChAssumptionsPK(projectionDetailsSid, userId,
				quarter, year, stChAssumptionsSid, chAssumptionsSid, sessionId);
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(lastModifiedDate);

		objectOutput.writeBoolean(parent);

		if (commentary == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(commentary);
		}

		objectOutput.writeInt(projectionDetailsSid);

		objectOutput.writeInt(userId);

		objectOutput.writeInt(quarter);

		objectOutput.writeDouble(totalDiscountPercentChange);

		if (reasonCodes == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(reasonCodes);
		}

		objectOutput.writeInt(year);

		objectOutput.writeDouble(totalDiscountPercentProjected);

		objectOutput.writeDouble(totalDiscountPercentPrior);

		if (stChAssumptionsSid == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(stChAssumptionsSid);
		}

		objectOutput.writeInt(chAssumptionsSid);

		objectOutput.writeDouble(totalDiscountChange);

		objectOutput.writeInt(sessionId);

		objectOutput.writeDouble(totalDiscountProjected);

		objectOutput.writeBoolean(isChecked);

		objectOutput.writeInt(camId);

		objectOutput.writeDouble(grossTradeSales);

		objectOutput.writeDouble(totalDiscountPrior);
	}

	public long lastModifiedDate;
	public boolean parent;
	public String commentary;
	public int projectionDetailsSid;
	public int userId;
	public int quarter;
	public double totalDiscountPercentChange;
	public String reasonCodes;
	public int year;
	public double totalDiscountPercentProjected;
	public double totalDiscountPercentPrior;
	public String stChAssumptionsSid;
	public int chAssumptionsSid;
	public double totalDiscountChange;
	public int sessionId;
	public double totalDiscountProjected;
	public boolean isChecked;
	public int camId;
	public double grossTradeSales;
	public double totalDiscountPrior;
	public transient StChAssumptionsPK stChAssumptionsPK;
}