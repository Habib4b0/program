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

import com.stpl.app.model.StNationalAssumptions;
import com.stpl.app.service.persistence.StNationalAssumptionsPK;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing StNationalAssumptions in entity cache.
 *
 * @author
 * @see StNationalAssumptions
 * @generated
 */
@ProviderType
public class StNationalAssumptionsCacheModel implements CacheModel<StNationalAssumptions>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof StNationalAssumptionsCacheModel)) {
			return false;
		}

		StNationalAssumptionsCacheModel stNationalAssumptionsCacheModel = (StNationalAssumptionsCacheModel)obj;

		if (stNationalAssumptionsPK.equals(
					stNationalAssumptionsCacheModel.stNationalAssumptionsPK)) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, stNationalAssumptionsPK);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(29);

		sb.append("{lastModifiedDate=");
		sb.append(lastModifiedDate);
		sb.append(", baselinePeriod=");
		sb.append(baselinePeriod);
		sb.append(", startPeriod=");
		sb.append(startPeriod);
		sb.append(", frequency=");
		sb.append(frequency);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", endPeriod=");
		sb.append(endPeriod);
		sb.append(", naProjMasterSid=");
		sb.append(naProjMasterSid);
		sb.append(", rollingPeriod=");
		sb.append(rollingPeriod);
		sb.append(", forecastMethodology=");
		sb.append(forecastMethodology);
		sb.append(", priceType=");
		sb.append(priceType);
		sb.append(", priceBasis=");
		sb.append(priceBasis);
		sb.append(", sessionId=");
		sb.append(sessionId);
		sb.append(", baselineMethodology=");
		sb.append(baselineMethodology);
		sb.append(", growthRate=");
		sb.append(growthRate);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public StNationalAssumptions toEntityModel() {
		StNationalAssumptionsImpl stNationalAssumptionsImpl = new StNationalAssumptionsImpl();

		if (lastModifiedDate == Long.MIN_VALUE) {
			stNationalAssumptionsImpl.setLastModifiedDate(null);
		}
		else {
			stNationalAssumptionsImpl.setLastModifiedDate(new Date(
					lastModifiedDate));
		}

		if (baselinePeriod == null) {
			stNationalAssumptionsImpl.setBaselinePeriod(StringPool.BLANK);
		}
		else {
			stNationalAssumptionsImpl.setBaselinePeriod(baselinePeriod);
		}

		if (startPeriod == null) {
			stNationalAssumptionsImpl.setStartPeriod(StringPool.BLANK);
		}
		else {
			stNationalAssumptionsImpl.setStartPeriod(startPeriod);
		}

		if (frequency == null) {
			stNationalAssumptionsImpl.setFrequency(StringPool.BLANK);
		}
		else {
			stNationalAssumptionsImpl.setFrequency(frequency);
		}

		stNationalAssumptionsImpl.setUserId(userId);

		if (endPeriod == null) {
			stNationalAssumptionsImpl.setEndPeriod(StringPool.BLANK);
		}
		else {
			stNationalAssumptionsImpl.setEndPeriod(endPeriod);
		}

		stNationalAssumptionsImpl.setNaProjMasterSid(naProjMasterSid);

		if (rollingPeriod == null) {
			stNationalAssumptionsImpl.setRollingPeriod(StringPool.BLANK);
		}
		else {
			stNationalAssumptionsImpl.setRollingPeriod(rollingPeriod);
		}

		if (forecastMethodology == null) {
			stNationalAssumptionsImpl.setForecastMethodology(StringPool.BLANK);
		}
		else {
			stNationalAssumptionsImpl.setForecastMethodology(forecastMethodology);
		}

		if (priceType == null) {
			stNationalAssumptionsImpl.setPriceType(StringPool.BLANK);
		}
		else {
			stNationalAssumptionsImpl.setPriceType(priceType);
		}

		if (priceBasis == null) {
			stNationalAssumptionsImpl.setPriceBasis(StringPool.BLANK);
		}
		else {
			stNationalAssumptionsImpl.setPriceBasis(priceBasis);
		}

		stNationalAssumptionsImpl.setSessionId(sessionId);

		if (baselineMethodology == null) {
			stNationalAssumptionsImpl.setBaselineMethodology(StringPool.BLANK);
		}
		else {
			stNationalAssumptionsImpl.setBaselineMethodology(baselineMethodology);
		}

		stNationalAssumptionsImpl.setGrowthRate(growthRate);

		stNationalAssumptionsImpl.resetOriginalValues();

		return stNationalAssumptionsImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		lastModifiedDate = objectInput.readLong();
		baselinePeriod = objectInput.readUTF();
		startPeriod = objectInput.readUTF();
		frequency = objectInput.readUTF();

		userId = objectInput.readInt();
		endPeriod = objectInput.readUTF();

		naProjMasterSid = objectInput.readInt();
		rollingPeriod = objectInput.readUTF();
		forecastMethodology = objectInput.readUTF();
		priceType = objectInput.readUTF();
		priceBasis = objectInput.readUTF();

		sessionId = objectInput.readInt();
		baselineMethodology = objectInput.readUTF();

		growthRate = objectInput.readDouble();

		stNationalAssumptionsPK = new StNationalAssumptionsPK(startPeriod,
				userId, endPeriod, naProjMasterSid, priceType, sessionId);
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(lastModifiedDate);

		if (baselinePeriod == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(baselinePeriod);
		}

		if (startPeriod == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(startPeriod);
		}

		if (frequency == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(frequency);
		}

		objectOutput.writeInt(userId);

		if (endPeriod == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(endPeriod);
		}

		objectOutput.writeInt(naProjMasterSid);

		if (rollingPeriod == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(rollingPeriod);
		}

		if (forecastMethodology == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(forecastMethodology);
		}

		if (priceType == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(priceType);
		}

		if (priceBasis == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(priceBasis);
		}

		objectOutput.writeInt(sessionId);

		if (baselineMethodology == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(baselineMethodology);
		}

		objectOutput.writeDouble(growthRate);
	}

	public long lastModifiedDate;
	public String baselinePeriod;
	public String startPeriod;
	public String frequency;
	public int userId;
	public String endPeriod;
	public int naProjMasterSid;
	public String rollingPeriod;
	public String forecastMethodology;
	public String priceType;
	public String priceBasis;
	public int sessionId;
	public String baselineMethodology;
	public double growthRate;
	public transient StNationalAssumptionsPK stNationalAssumptionsPK;
}