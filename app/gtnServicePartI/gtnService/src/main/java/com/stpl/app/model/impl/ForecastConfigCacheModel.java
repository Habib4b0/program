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

import com.stpl.app.model.ForecastConfig;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing ForecastConfig in entity cache.
 *
 * @author
 * @see ForecastConfig
 * @generated
 */
@ProviderType
public class ForecastConfigCacheModel implements CacheModel<ForecastConfig>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ForecastConfigCacheModel)) {
			return false;
		}

		ForecastConfigCacheModel forecastConfigCacheModel = (ForecastConfigCacheModel)obj;

		if (forecastConfigSid == forecastConfigCacheModel.forecastConfigSid) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, forecastConfigSid);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(47);

		sb.append("{processType=");
		sb.append(processType);
		sb.append(", toDate=");
		sb.append(toDate);
		sb.append(", versionNo=");
		sb.append(versionNo);
		sb.append(", forecastConfigSid=");
		sb.append(forecastConfigSid);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", fromDate=");
		sb.append(fromDate);
		sb.append(", projValue=");
		sb.append(projValue);
		sb.append(", createdBy=");
		sb.append(createdBy);
		sb.append(", createdDate=");
		sb.append(createdDate);
		sb.append(", projFreq=");
		sb.append(projFreq);
		sb.append(", histValue=");
		sb.append(histValue);
		sb.append(", businessProcessType=");
		sb.append(businessProcessType);
		sb.append(", modifiedBy=");
		sb.append(modifiedBy);
		sb.append(", histFreq=");
		sb.append(histFreq);
		sb.append(", activeStartDate=");
		sb.append(activeStartDate);
		sb.append(", activeEndDate=");
		sb.append(activeEndDate);
		sb.append(", processMode=");
		sb.append(processMode);
		sb.append(", historicalDataIntervalFrom=");
		sb.append(historicalDataIntervalFrom);
		sb.append(", historicalTimePeriodFrom=");
		sb.append(historicalTimePeriodFrom);
		sb.append(", projHistFreq=");
		sb.append(projHistFreq);
		sb.append(", futureTimePeriodFrom=");
		sb.append(futureTimePeriodFrom);
		sb.append(", historicalDataIntervalTo=");
		sb.append(historicalDataIntervalTo);
		sb.append(", projHistValue=");
		sb.append(projHistValue);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ForecastConfig toEntityModel() {
		ForecastConfigImpl forecastConfigImpl = new ForecastConfigImpl();

		forecastConfigImpl.setProcessType(processType);

		if (toDate == Long.MIN_VALUE) {
			forecastConfigImpl.setToDate(null);
		}
		else {
			forecastConfigImpl.setToDate(new Date(toDate));
		}

		forecastConfigImpl.setVersionNo(versionNo);
		forecastConfigImpl.setForecastConfigSid(forecastConfigSid);

		if (modifiedDate == Long.MIN_VALUE) {
			forecastConfigImpl.setModifiedDate(null);
		}
		else {
			forecastConfigImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (fromDate == Long.MIN_VALUE) {
			forecastConfigImpl.setFromDate(null);
		}
		else {
			forecastConfigImpl.setFromDate(new Date(fromDate));
		}

		forecastConfigImpl.setProjValue(projValue);
		forecastConfigImpl.setCreatedBy(createdBy);

		if (createdDate == Long.MIN_VALUE) {
			forecastConfigImpl.setCreatedDate(null);
		}
		else {
			forecastConfigImpl.setCreatedDate(new Date(createdDate));
		}

		forecastConfigImpl.setProjFreq(projFreq);
		forecastConfigImpl.setHistValue(histValue);
		forecastConfigImpl.setBusinessProcessType(businessProcessType);
		forecastConfigImpl.setModifiedBy(modifiedBy);
		forecastConfigImpl.setHistFreq(histFreq);

		if (activeStartDate == Long.MIN_VALUE) {
			forecastConfigImpl.setActiveStartDate(null);
		}
		else {
			forecastConfigImpl.setActiveStartDate(new Date(activeStartDate));
		}

		if (activeEndDate == Long.MIN_VALUE) {
			forecastConfigImpl.setActiveEndDate(null);
		}
		else {
			forecastConfigImpl.setActiveEndDate(new Date(activeEndDate));
		}

		forecastConfigImpl.setProcessMode(processMode);

		if (historicalDataIntervalFrom == Long.MIN_VALUE) {
			forecastConfigImpl.setHistoricalDataIntervalFrom(null);
		}
		else {
			forecastConfigImpl.setHistoricalDataIntervalFrom(new Date(
					historicalDataIntervalFrom));
		}

		if (historicalTimePeriodFrom == Long.MIN_VALUE) {
			forecastConfigImpl.setHistoricalTimePeriodFrom(null);
		}
		else {
			forecastConfigImpl.setHistoricalTimePeriodFrom(new Date(
					historicalTimePeriodFrom));
		}

		forecastConfigImpl.setProjHistFreq(projHistFreq);

		if (futureTimePeriodFrom == Long.MIN_VALUE) {
			forecastConfigImpl.setFutureTimePeriodFrom(null);
		}
		else {
			forecastConfigImpl.setFutureTimePeriodFrom(new Date(
					futureTimePeriodFrom));
		}

		if (historicalDataIntervalTo == Long.MIN_VALUE) {
			forecastConfigImpl.setHistoricalDataIntervalTo(null);
		}
		else {
			forecastConfigImpl.setHistoricalDataIntervalTo(new Date(
					historicalDataIntervalTo));
		}

		forecastConfigImpl.setProjHistValue(projHistValue);

		forecastConfigImpl.resetOriginalValues();

		return forecastConfigImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		processType = objectInput.readBoolean();
		toDate = objectInput.readLong();

		versionNo = objectInput.readInt();

		forecastConfigSid = objectInput.readInt();
		modifiedDate = objectInput.readLong();
		fromDate = objectInput.readLong();

		projValue = objectInput.readInt();

		createdBy = objectInput.readInt();
		createdDate = objectInput.readLong();

		projFreq = objectInput.readInt();

		histValue = objectInput.readInt();

		businessProcessType = objectInput.readInt();

		modifiedBy = objectInput.readInt();

		histFreq = objectInput.readInt();
		activeStartDate = objectInput.readLong();
		activeEndDate = objectInput.readLong();

		processMode = objectInput.readBoolean();
		historicalDataIntervalFrom = objectInput.readLong();
		historicalTimePeriodFrom = objectInput.readLong();

		projHistFreq = objectInput.readInt();
		futureTimePeriodFrom = objectInput.readLong();
		historicalDataIntervalTo = objectInput.readLong();

		projHistValue = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeBoolean(processType);
		objectOutput.writeLong(toDate);

		objectOutput.writeInt(versionNo);

		objectOutput.writeInt(forecastConfigSid);
		objectOutput.writeLong(modifiedDate);
		objectOutput.writeLong(fromDate);

		objectOutput.writeInt(projValue);

		objectOutput.writeInt(createdBy);
		objectOutput.writeLong(createdDate);

		objectOutput.writeInt(projFreq);

		objectOutput.writeInt(histValue);

		objectOutput.writeInt(businessProcessType);

		objectOutput.writeInt(modifiedBy);

		objectOutput.writeInt(histFreq);
		objectOutput.writeLong(activeStartDate);
		objectOutput.writeLong(activeEndDate);

		objectOutput.writeBoolean(processMode);
		objectOutput.writeLong(historicalDataIntervalFrom);
		objectOutput.writeLong(historicalTimePeriodFrom);

		objectOutput.writeInt(projHistFreq);
		objectOutput.writeLong(futureTimePeriodFrom);
		objectOutput.writeLong(historicalDataIntervalTo);

		objectOutput.writeInt(projHistValue);
	}

	public boolean processType;
	public long toDate;
	public int versionNo;
	public int forecastConfigSid;
	public long modifiedDate;
	public long fromDate;
	public int projValue;
	public int createdBy;
	public long createdDate;
	public int projFreq;
	public int histValue;
	public int businessProcessType;
	public int modifiedBy;
	public int histFreq;
	public long activeStartDate;
	public long activeEndDate;
	public boolean processMode;
	public long historicalDataIntervalFrom;
	public long historicalTimePeriodFrom;
	public int projHistFreq;
	public long futureTimePeriodFrom;
	public long historicalDataIntervalTo;
	public int projHistValue;
}