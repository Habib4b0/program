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

package com.stpl.app.parttwo.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import com.stpl.app.parttwo.model.VwAdjustDemandForecastAct;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing VwAdjustDemandForecastAct in entity cache.
 *
 * @author
 * @see VwAdjustDemandForecastAct
 * @generated
 */
@ProviderType
public class VwAdjustDemandForecastActCacheModel implements CacheModel<VwAdjustDemandForecastAct>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof VwAdjustDemandForecastActCacheModel)) {
			return false;
		}

		VwAdjustDemandForecastActCacheModel vwAdjustDemandForecastActCacheModel = (VwAdjustDemandForecastActCacheModel)obj;

		if (adjustedDemandForecastId == vwAdjustDemandForecastActCacheModel.adjustedDemandForecastId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, adjustedDemandForecastId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(63);

		sb.append("{forecastVer=");
		sb.append(forecastVer);
		sb.append(", grossUnits=");
		sb.append(grossUnits);
		sb.append(", businessUnitNo=");
		sb.append(businessUnitNo);
		sb.append(", forecastYear=");
		sb.append(forecastYear);
		sb.append(", brandName=");
		sb.append(brandName);
		sb.append(", itemId=");
		sb.append(itemId);
		sb.append(", organizationKey=");
		sb.append(organizationKey);
		sb.append(", source=");
		sb.append(source);
		sb.append(", marketShareRatio=");
		sb.append(marketShareRatio);
		sb.append(", businessUnitName=");
		sb.append(businessUnitName);
		sb.append(", marketShareUnits=");
		sb.append(marketShareUnits);
		sb.append(", forecastMonth=");
		sb.append(forecastMonth);
		sb.append(", inventoryUnitChange=");
		sb.append(inventoryUnitChange);
		sb.append(", uncapturedUnitsRatio=");
		sb.append(uncapturedUnitsRatio);
		sb.append(", country=");
		sb.append(country);
		sb.append(", forecastType=");
		sb.append(forecastType);
		sb.append(", totalDemandUnits=");
		sb.append(totalDemandUnits);
		sb.append(", brandId=");
		sb.append(brandId);
		sb.append(", isForecast=");
		sb.append(isForecast);
		sb.append(", totalDemandAmount=");
		sb.append(totalDemandAmount);
		sb.append(", uncapturedUnits=");
		sb.append(uncapturedUnits);
		sb.append(", grossPrice=");
		sb.append(grossPrice);
		sb.append(", grossAmount=");
		sb.append(grossAmount);
		sb.append(", batchId=");
		sb.append(batchId);
		sb.append(", adjustedDemandForecastId=");
		sb.append(adjustedDemandForecastId);
		sb.append(", itemName=");
		sb.append(itemName);
		sb.append(", netSalesPrice=");
		sb.append(netSalesPrice);
		sb.append(", netSalesAmount=");
		sb.append(netSalesAmount);
		sb.append(", segment=");
		sb.append(segment);
		sb.append(", forecastName=");
		sb.append(forecastName);
		sb.append(", marketSizeUnits=");
		sb.append(marketSizeUnits);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public VwAdjustDemandForecastAct toEntityModel() {
		VwAdjustDemandForecastActImpl vwAdjustDemandForecastActImpl = new VwAdjustDemandForecastActImpl();

		if (forecastVer == null) {
			vwAdjustDemandForecastActImpl.setForecastVer(StringPool.BLANK);
		}
		else {
			vwAdjustDemandForecastActImpl.setForecastVer(forecastVer);
		}

		vwAdjustDemandForecastActImpl.setGrossUnits(grossUnits);

		if (businessUnitNo == null) {
			vwAdjustDemandForecastActImpl.setBusinessUnitNo(StringPool.BLANK);
		}
		else {
			vwAdjustDemandForecastActImpl.setBusinessUnitNo(businessUnitNo);
		}

		if (forecastYear == null) {
			vwAdjustDemandForecastActImpl.setForecastYear(StringPool.BLANK);
		}
		else {
			vwAdjustDemandForecastActImpl.setForecastYear(forecastYear);
		}

		if (brandName == null) {
			vwAdjustDemandForecastActImpl.setBrandName(StringPool.BLANK);
		}
		else {
			vwAdjustDemandForecastActImpl.setBrandName(brandName);
		}

		if (itemId == null) {
			vwAdjustDemandForecastActImpl.setItemId(StringPool.BLANK);
		}
		else {
			vwAdjustDemandForecastActImpl.setItemId(itemId);
		}

		if (organizationKey == null) {
			vwAdjustDemandForecastActImpl.setOrganizationKey(StringPool.BLANK);
		}
		else {
			vwAdjustDemandForecastActImpl.setOrganizationKey(organizationKey);
		}

		if (source == null) {
			vwAdjustDemandForecastActImpl.setSource(StringPool.BLANK);
		}
		else {
			vwAdjustDemandForecastActImpl.setSource(source);
		}

		vwAdjustDemandForecastActImpl.setMarketShareRatio(marketShareRatio);

		if (businessUnitName == null) {
			vwAdjustDemandForecastActImpl.setBusinessUnitName(StringPool.BLANK);
		}
		else {
			vwAdjustDemandForecastActImpl.setBusinessUnitName(businessUnitName);
		}

		vwAdjustDemandForecastActImpl.setMarketShareUnits(marketShareUnits);

		if (forecastMonth == null) {
			vwAdjustDemandForecastActImpl.setForecastMonth(StringPool.BLANK);
		}
		else {
			vwAdjustDemandForecastActImpl.setForecastMonth(forecastMonth);
		}

		vwAdjustDemandForecastActImpl.setInventoryUnitChange(inventoryUnitChange);

		if (uncapturedUnitsRatio == null) {
			vwAdjustDemandForecastActImpl.setUncapturedUnitsRatio(StringPool.BLANK);
		}
		else {
			vwAdjustDemandForecastActImpl.setUncapturedUnitsRatio(uncapturedUnitsRatio);
		}

		if (country == null) {
			vwAdjustDemandForecastActImpl.setCountry(StringPool.BLANK);
		}
		else {
			vwAdjustDemandForecastActImpl.setCountry(country);
		}

		if (forecastType == null) {
			vwAdjustDemandForecastActImpl.setForecastType(StringPool.BLANK);
		}
		else {
			vwAdjustDemandForecastActImpl.setForecastType(forecastType);
		}

		vwAdjustDemandForecastActImpl.setTotalDemandUnits(totalDemandUnits);

		if (brandId == null) {
			vwAdjustDemandForecastActImpl.setBrandId(StringPool.BLANK);
		}
		else {
			vwAdjustDemandForecastActImpl.setBrandId(brandId);
		}

		if (isForecast == null) {
			vwAdjustDemandForecastActImpl.setIsForecast(StringPool.BLANK);
		}
		else {
			vwAdjustDemandForecastActImpl.setIsForecast(isForecast);
		}

		vwAdjustDemandForecastActImpl.setTotalDemandAmount(totalDemandAmount);
		vwAdjustDemandForecastActImpl.setUncapturedUnits(uncapturedUnits);
		vwAdjustDemandForecastActImpl.setGrossPrice(grossPrice);
		vwAdjustDemandForecastActImpl.setGrossAmount(grossAmount);

		if (batchId == null) {
			vwAdjustDemandForecastActImpl.setBatchId(StringPool.BLANK);
		}
		else {
			vwAdjustDemandForecastActImpl.setBatchId(batchId);
		}

		vwAdjustDemandForecastActImpl.setAdjustedDemandForecastId(adjustedDemandForecastId);

		if (itemName == null) {
			vwAdjustDemandForecastActImpl.setItemName(StringPool.BLANK);
		}
		else {
			vwAdjustDemandForecastActImpl.setItemName(itemName);
		}

		vwAdjustDemandForecastActImpl.setNetSalesPrice(netSalesPrice);
		vwAdjustDemandForecastActImpl.setNetSalesAmount(netSalesAmount);

		if (segment == null) {
			vwAdjustDemandForecastActImpl.setSegment(StringPool.BLANK);
		}
		else {
			vwAdjustDemandForecastActImpl.setSegment(segment);
		}

		if (forecastName == null) {
			vwAdjustDemandForecastActImpl.setForecastName(StringPool.BLANK);
		}
		else {
			vwAdjustDemandForecastActImpl.setForecastName(forecastName);
		}

		vwAdjustDemandForecastActImpl.setMarketSizeUnits(marketSizeUnits);

		vwAdjustDemandForecastActImpl.resetOriginalValues();

		return vwAdjustDemandForecastActImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		forecastVer = objectInput.readUTF();

		grossUnits = objectInput.readDouble();
		businessUnitNo = objectInput.readUTF();
		forecastYear = objectInput.readUTF();
		brandName = objectInput.readUTF();
		itemId = objectInput.readUTF();
		organizationKey = objectInput.readUTF();
		source = objectInput.readUTF();

		marketShareRatio = objectInput.readInt();
		businessUnitName = objectInput.readUTF();

		marketShareUnits = objectInput.readDouble();
		forecastMonth = objectInput.readUTF();

		inventoryUnitChange = objectInput.readDouble();
		uncapturedUnitsRatio = objectInput.readUTF();
		country = objectInput.readUTF();
		forecastType = objectInput.readUTF();

		totalDemandUnits = objectInput.readDouble();
		brandId = objectInput.readUTF();
		isForecast = objectInput.readUTF();

		totalDemandAmount = objectInput.readDouble();

		uncapturedUnits = objectInput.readDouble();

		grossPrice = objectInput.readDouble();

		grossAmount = objectInput.readDouble();
		batchId = objectInput.readUTF();

		adjustedDemandForecastId = objectInput.readInt();
		itemName = objectInput.readUTF();

		netSalesPrice = objectInput.readDouble();

		netSalesAmount = objectInput.readDouble();
		segment = objectInput.readUTF();
		forecastName = objectInput.readUTF();

		marketSizeUnits = objectInput.readDouble();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		if (forecastVer == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(forecastVer);
		}

		objectOutput.writeDouble(grossUnits);

		if (businessUnitNo == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(businessUnitNo);
		}

		if (forecastYear == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(forecastYear);
		}

		if (brandName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(brandName);
		}

		if (itemId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(itemId);
		}

		if (organizationKey == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(organizationKey);
		}

		if (source == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(source);
		}

		objectOutput.writeInt(marketShareRatio);

		if (businessUnitName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(businessUnitName);
		}

		objectOutput.writeDouble(marketShareUnits);

		if (forecastMonth == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(forecastMonth);
		}

		objectOutput.writeDouble(inventoryUnitChange);

		if (uncapturedUnitsRatio == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(uncapturedUnitsRatio);
		}

		if (country == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(country);
		}

		if (forecastType == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(forecastType);
		}

		objectOutput.writeDouble(totalDemandUnits);

		if (brandId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(brandId);
		}

		if (isForecast == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(isForecast);
		}

		objectOutput.writeDouble(totalDemandAmount);

		objectOutput.writeDouble(uncapturedUnits);

		objectOutput.writeDouble(grossPrice);

		objectOutput.writeDouble(grossAmount);

		if (batchId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(batchId);
		}

		objectOutput.writeInt(adjustedDemandForecastId);

		if (itemName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(itemName);
		}

		objectOutput.writeDouble(netSalesPrice);

		objectOutput.writeDouble(netSalesAmount);

		if (segment == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(segment);
		}

		if (forecastName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(forecastName);
		}

		objectOutput.writeDouble(marketSizeUnits);
	}

	public String forecastVer;
	public double grossUnits;
	public String businessUnitNo;
	public String forecastYear;
	public String brandName;
	public String itemId;
	public String organizationKey;
	public String source;
	public int marketShareRatio;
	public String businessUnitName;
	public double marketShareUnits;
	public String forecastMonth;
	public double inventoryUnitChange;
	public String uncapturedUnitsRatio;
	public String country;
	public String forecastType;
	public double totalDemandUnits;
	public String brandId;
	public String isForecast;
	public double totalDemandAmount;
	public double uncapturedUnits;
	public double grossPrice;
	public double grossAmount;
	public String batchId;
	public int adjustedDemandForecastId;
	public String itemName;
	public double netSalesPrice;
	public double netSalesAmount;
	public String segment;
	public String forecastName;
	public double marketSizeUnits;
}