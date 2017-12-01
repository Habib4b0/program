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

import com.stpl.app.model.ForecastingMaster;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing ForecastingMaster in entity cache.
 *
 * @author
 * @see ForecastingMaster
 * @generated
 */
@ProviderType
public class ForecastingMasterCacheModel implements CacheModel<ForecastingMaster>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ForecastingMasterCacheModel)) {
			return false;
		}

		ForecastingMasterCacheModel forecastingMasterCacheModel = (ForecastingMasterCacheModel)obj;

		if (forecastMasterSid == forecastingMasterCacheModel.forecastMasterSid) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, forecastMasterSid);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(65);

		sb.append("{forecastValueType=");
		sb.append(forecastValueType);
		sb.append(", modifiedBy=");
		sb.append(modifiedBy);
		sb.append(", createdDate=");
		sb.append(createdDate);
		sb.append(", percentageEstimate=");
		sb.append(percentageEstimate);
		sb.append(", actualSalesPercentageMonth=");
		sb.append(actualSalesPercentageMonth);
		sb.append(", ndc=");
		sb.append(ndc);
		sb.append(", batchId=");
		sb.append(batchId);
		sb.append(", forecastVer=");
		sb.append(forecastVer);
		sb.append(", country=");
		sb.append(country);
		sb.append(", product=");
		sb.append(product);
		sb.append(", forecastStartDate=");
		sb.append(forecastStartDate);
		sb.append(", forecastedSalesPercentage=");
		sb.append(forecastedSalesPercentage);
		sb.append(", units=");
		sb.append(units);
		sb.append(", dollars=");
		sb.append(dollars);
		sb.append(", forecastMonth=");
		sb.append(forecastMonth);
		sb.append(", createdBy=");
		sb.append(createdBy);
		sb.append(", segment=");
		sb.append(segment);
		sb.append(", price=");
		sb.append(price);
		sb.append(", actualSalesPercentage=");
		sb.append(actualSalesPercentage);
		sb.append(", forecastYear=");
		sb.append(forecastYear);
		sb.append(", forecastName=");
		sb.append(forecastName);
		sb.append(", forecastDate=");
		sb.append(forecastDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", brand=");
		sb.append(brand);
		sb.append(", forecastValue=");
		sb.append(forecastValue);
		sb.append(", percentageEstimateYear=");
		sb.append(percentageEstimateYear);
		sb.append(", recordLockStatus=");
		sb.append(recordLockStatus);
		sb.append(", source=");
		sb.append(source);
		sb.append(", forecastMasterSid=");
		sb.append(forecastMasterSid);
		sb.append(", forecastedSalesPercentMonth=");
		sb.append(forecastedSalesPercentMonth);
		sb.append(", inboundStatus=");
		sb.append(inboundStatus);
		sb.append(", businessUnit=");
		sb.append(businessUnit);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ForecastingMaster toEntityModel() {
		ForecastingMasterImpl forecastingMasterImpl = new ForecastingMasterImpl();

		if (forecastValueType == null) {
			forecastingMasterImpl.setForecastValueType(StringPool.BLANK);
		}
		else {
			forecastingMasterImpl.setForecastValueType(forecastValueType);
		}

		forecastingMasterImpl.setModifiedBy(modifiedBy);

		if (createdDate == Long.MIN_VALUE) {
			forecastingMasterImpl.setCreatedDate(null);
		}
		else {
			forecastingMasterImpl.setCreatedDate(new Date(createdDate));
		}

		if (percentageEstimate == null) {
			forecastingMasterImpl.setPercentageEstimate(StringPool.BLANK);
		}
		else {
			forecastingMasterImpl.setPercentageEstimate(percentageEstimate);
		}

		forecastingMasterImpl.setActualSalesPercentageMonth(actualSalesPercentageMonth);

		if (ndc == null) {
			forecastingMasterImpl.setNdc(StringPool.BLANK);
		}
		else {
			forecastingMasterImpl.setNdc(ndc);
		}

		if (batchId == null) {
			forecastingMasterImpl.setBatchId(StringPool.BLANK);
		}
		else {
			forecastingMasterImpl.setBatchId(batchId);
		}

		if (forecastVer == null) {
			forecastingMasterImpl.setForecastVer(StringPool.BLANK);
		}
		else {
			forecastingMasterImpl.setForecastVer(forecastVer);
		}

		if (country == null) {
			forecastingMasterImpl.setCountry(StringPool.BLANK);
		}
		else {
			forecastingMasterImpl.setCountry(country);
		}

		if (product == null) {
			forecastingMasterImpl.setProduct(StringPool.BLANK);
		}
		else {
			forecastingMasterImpl.setProduct(product);
		}

		if (forecastStartDate == Long.MIN_VALUE) {
			forecastingMasterImpl.setForecastStartDate(null);
		}
		else {
			forecastingMasterImpl.setForecastStartDate(new Date(
					forecastStartDate));
		}

		if (forecastedSalesPercentage == null) {
			forecastingMasterImpl.setForecastedSalesPercentage(StringPool.BLANK);
		}
		else {
			forecastingMasterImpl.setForecastedSalesPercentage(forecastedSalesPercentage);
		}

		forecastingMasterImpl.setUnits(units);
		forecastingMasterImpl.setDollars(dollars);

		if (forecastMonth == null) {
			forecastingMasterImpl.setForecastMonth(StringPool.BLANK);
		}
		else {
			forecastingMasterImpl.setForecastMonth(forecastMonth);
		}

		forecastingMasterImpl.setCreatedBy(createdBy);

		if (segment == null) {
			forecastingMasterImpl.setSegment(StringPool.BLANK);
		}
		else {
			forecastingMasterImpl.setSegment(segment);
		}

		forecastingMasterImpl.setPrice(price);

		if (actualSalesPercentage == null) {
			forecastingMasterImpl.setActualSalesPercentage(StringPool.BLANK);
		}
		else {
			forecastingMasterImpl.setActualSalesPercentage(actualSalesPercentage);
		}

		if (forecastYear == null) {
			forecastingMasterImpl.setForecastYear(StringPool.BLANK);
		}
		else {
			forecastingMasterImpl.setForecastYear(forecastYear);
		}

		if (forecastName == null) {
			forecastingMasterImpl.setForecastName(StringPool.BLANK);
		}
		else {
			forecastingMasterImpl.setForecastName(forecastName);
		}

		if (forecastDate == Long.MIN_VALUE) {
			forecastingMasterImpl.setForecastDate(null);
		}
		else {
			forecastingMasterImpl.setForecastDate(new Date(forecastDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			forecastingMasterImpl.setModifiedDate(null);
		}
		else {
			forecastingMasterImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (brand == null) {
			forecastingMasterImpl.setBrand(StringPool.BLANK);
		}
		else {
			forecastingMasterImpl.setBrand(brand);
		}

		if (forecastValue == null) {
			forecastingMasterImpl.setForecastValue(StringPool.BLANK);
		}
		else {
			forecastingMasterImpl.setForecastValue(forecastValue);
		}

		forecastingMasterImpl.setPercentageEstimateYear(percentageEstimateYear);
		forecastingMasterImpl.setRecordLockStatus(recordLockStatus);

		if (source == null) {
			forecastingMasterImpl.setSource(StringPool.BLANK);
		}
		else {
			forecastingMasterImpl.setSource(source);
		}

		forecastingMasterImpl.setForecastMasterSid(forecastMasterSid);
		forecastingMasterImpl.setForecastedSalesPercentMonth(forecastedSalesPercentMonth);

		if (inboundStatus == null) {
			forecastingMasterImpl.setInboundStatus(StringPool.BLANK);
		}
		else {
			forecastingMasterImpl.setInboundStatus(inboundStatus);
		}

		forecastingMasterImpl.setBusinessUnit(businessUnit);

		forecastingMasterImpl.resetOriginalValues();

		return forecastingMasterImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		forecastValueType = objectInput.readUTF();

		modifiedBy = objectInput.readInt();
		createdDate = objectInput.readLong();
		percentageEstimate = objectInput.readUTF();

		actualSalesPercentageMonth = objectInput.readInt();
		ndc = objectInput.readUTF();
		batchId = objectInput.readUTF();
		forecastVer = objectInput.readUTF();
		country = objectInput.readUTF();
		product = objectInput.readUTF();
		forecastStartDate = objectInput.readLong();
		forecastedSalesPercentage = objectInput.readUTF();

		units = objectInput.readDouble();

		dollars = objectInput.readDouble();
		forecastMonth = objectInput.readUTF();

		createdBy = objectInput.readInt();
		segment = objectInput.readUTF();

		price = objectInput.readDouble();
		actualSalesPercentage = objectInput.readUTF();
		forecastYear = objectInput.readUTF();
		forecastName = objectInput.readUTF();
		forecastDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		brand = objectInput.readUTF();
		forecastValue = objectInput.readUTF();

		percentageEstimateYear = objectInput.readInt();

		recordLockStatus = objectInput.readBoolean();
		source = objectInput.readUTF();

		forecastMasterSid = objectInput.readInt();

		forecastedSalesPercentMonth = objectInput.readInt();
		inboundStatus = objectInput.readUTF();

		businessUnit = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		if (forecastValueType == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(forecastValueType);
		}

		objectOutput.writeInt(modifiedBy);
		objectOutput.writeLong(createdDate);

		if (percentageEstimate == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(percentageEstimate);
		}

		objectOutput.writeInt(actualSalesPercentageMonth);

		if (ndc == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(ndc);
		}

		if (batchId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(batchId);
		}

		if (forecastVer == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(forecastVer);
		}

		if (country == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(country);
		}

		if (product == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(product);
		}

		objectOutput.writeLong(forecastStartDate);

		if (forecastedSalesPercentage == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(forecastedSalesPercentage);
		}

		objectOutput.writeDouble(units);

		objectOutput.writeDouble(dollars);

		if (forecastMonth == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(forecastMonth);
		}

		objectOutput.writeInt(createdBy);

		if (segment == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(segment);
		}

		objectOutput.writeDouble(price);

		if (actualSalesPercentage == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(actualSalesPercentage);
		}

		if (forecastYear == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(forecastYear);
		}

		if (forecastName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(forecastName);
		}

		objectOutput.writeLong(forecastDate);
		objectOutput.writeLong(modifiedDate);

		if (brand == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(brand);
		}

		if (forecastValue == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(forecastValue);
		}

		objectOutput.writeInt(percentageEstimateYear);

		objectOutput.writeBoolean(recordLockStatus);

		if (source == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(source);
		}

		objectOutput.writeInt(forecastMasterSid);

		objectOutput.writeInt(forecastedSalesPercentMonth);

		if (inboundStatus == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(inboundStatus);
		}

		objectOutput.writeInt(businessUnit);
	}

	public String forecastValueType;
	public int modifiedBy;
	public long createdDate;
	public String percentageEstimate;
	public int actualSalesPercentageMonth;
	public String ndc;
	public String batchId;
	public String forecastVer;
	public String country;
	public String product;
	public long forecastStartDate;
	public String forecastedSalesPercentage;
	public double units;
	public double dollars;
	public String forecastMonth;
	public int createdBy;
	public String segment;
	public double price;
	public String actualSalesPercentage;
	public String forecastYear;
	public String forecastName;
	public long forecastDate;
	public long modifiedDate;
	public String brand;
	public String forecastValue;
	public int percentageEstimateYear;
	public boolean recordLockStatus;
	public String source;
	public int forecastMasterSid;
	public int forecastedSalesPercentMonth;
	public String inboundStatus;
	public int businessUnit;
}