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

import com.stpl.app.model.VwDemandForecastActual;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing VwDemandForecastActual in entity cache.
 *
 * @author
 * @see VwDemandForecastActual
 * @generated
 */
@ProviderType
public class VwDemandForecastActualCacheModel implements CacheModel<VwDemandForecastActual>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof VwDemandForecastActualCacheModel)) {
			return false;
		}

		VwDemandForecastActualCacheModel vwDemandForecastActualCacheModel = (VwDemandForecastActualCacheModel)obj;

		if (demandForecastActualSid == vwDemandForecastActualCacheModel.demandForecastActualSid) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, demandForecastActualSid);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(67);

		sb.append("{forecastYear=");
		sb.append(forecastYear);
		sb.append(", grossUnits=");
		sb.append(grossUnits);
		sb.append(", businessUnitNo=");
		sb.append(businessUnitNo);
		sb.append(", totalDemandUnits=");
		sb.append(totalDemandUnits);
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
		sb.append(", demandForecastActualSid=");
		sb.append(demandForecastActualSid);
		sb.append(", marketShareUnits=");
		sb.append(marketShareUnits);
		sb.append(", inventoryUnitChange=");
		sb.append(inventoryUnitChange);
		sb.append(", uncapturedUnitsRatio=");
		sb.append(uncapturedUnitsRatio);
		sb.append(", country=");
		sb.append(country);
		sb.append(", forecastType=");
		sb.append(forecastType);
		sb.append(", brandId=");
		sb.append(brandId);
		sb.append(", isForecast=");
		sb.append(isForecast);
		sb.append(", uncapturedUnits=");
		sb.append(uncapturedUnits);
		sb.append(", grossPrice=");
		sb.append(grossPrice);
		sb.append(", isActive=");
		sb.append(isActive);
		sb.append(", grossAmount=");
		sb.append(grossAmount);
		sb.append(", batchId=");
		sb.append(batchId);
		sb.append(", forecastVer=");
		sb.append(forecastVer);
		sb.append(", itemName=");
		sb.append(itemName);
		sb.append(", forecastMonth=");
		sb.append(forecastMonth);
		sb.append(", netSalesPrice=");
		sb.append(netSalesPrice);
		sb.append(", netSalesAmount=");
		sb.append(netSalesAmount);
		sb.append(", segment=");
		sb.append(segment);
		sb.append(", totalDemandAmount=");
		sb.append(totalDemandAmount);
		sb.append(", forecastName=");
		sb.append(forecastName);
		sb.append(", marketSizeUnits=");
		sb.append(marketSizeUnits);
		sb.append(", demandId=");
		sb.append(demandId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public VwDemandForecastActual toEntityModel() {
		VwDemandForecastActualImpl vwDemandForecastActualImpl = new VwDemandForecastActualImpl();

		if (forecastYear == null) {
			vwDemandForecastActualImpl.setForecastYear(StringPool.BLANK);
		}
		else {
			vwDemandForecastActualImpl.setForecastYear(forecastYear);
		}

		vwDemandForecastActualImpl.setGrossUnits(grossUnits);

		if (businessUnitNo == null) {
			vwDemandForecastActualImpl.setBusinessUnitNo(StringPool.BLANK);
		}
		else {
			vwDemandForecastActualImpl.setBusinessUnitNo(businessUnitNo);
		}

		vwDemandForecastActualImpl.setTotalDemandUnits(totalDemandUnits);

		if (brandName == null) {
			vwDemandForecastActualImpl.setBrandName(StringPool.BLANK);
		}
		else {
			vwDemandForecastActualImpl.setBrandName(brandName);
		}

		if (itemId == null) {
			vwDemandForecastActualImpl.setItemId(StringPool.BLANK);
		}
		else {
			vwDemandForecastActualImpl.setItemId(itemId);
		}

		if (organizationKey == null) {
			vwDemandForecastActualImpl.setOrganizationKey(StringPool.BLANK);
		}
		else {
			vwDemandForecastActualImpl.setOrganizationKey(organizationKey);
		}

		if (source == null) {
			vwDemandForecastActualImpl.setSource(StringPool.BLANK);
		}
		else {
			vwDemandForecastActualImpl.setSource(source);
		}

		if (marketShareRatio == null) {
			vwDemandForecastActualImpl.setMarketShareRatio(StringPool.BLANK);
		}
		else {
			vwDemandForecastActualImpl.setMarketShareRatio(marketShareRatio);
		}

		if (businessUnitName == null) {
			vwDemandForecastActualImpl.setBusinessUnitName(StringPool.BLANK);
		}
		else {
			vwDemandForecastActualImpl.setBusinessUnitName(businessUnitName);
		}

		vwDemandForecastActualImpl.setDemandForecastActualSid(demandForecastActualSid);
		vwDemandForecastActualImpl.setMarketShareUnits(marketShareUnits);
		vwDemandForecastActualImpl.setInventoryUnitChange(inventoryUnitChange);

		if (uncapturedUnitsRatio == null) {
			vwDemandForecastActualImpl.setUncapturedUnitsRatio(StringPool.BLANK);
		}
		else {
			vwDemandForecastActualImpl.setUncapturedUnitsRatio(uncapturedUnitsRatio);
		}

		if (country == null) {
			vwDemandForecastActualImpl.setCountry(StringPool.BLANK);
		}
		else {
			vwDemandForecastActualImpl.setCountry(country);
		}

		if (forecastType == null) {
			vwDemandForecastActualImpl.setForecastType(StringPool.BLANK);
		}
		else {
			vwDemandForecastActualImpl.setForecastType(forecastType);
		}

		if (brandId == null) {
			vwDemandForecastActualImpl.setBrandId(StringPool.BLANK);
		}
		else {
			vwDemandForecastActualImpl.setBrandId(brandId);
		}

		if (isForecast == null) {
			vwDemandForecastActualImpl.setIsForecast(StringPool.BLANK);
		}
		else {
			vwDemandForecastActualImpl.setIsForecast(isForecast);
		}

		vwDemandForecastActualImpl.setUncapturedUnits(uncapturedUnits);
		vwDemandForecastActualImpl.setGrossPrice(grossPrice);

		if (isActive == null) {
			vwDemandForecastActualImpl.setIsActive(StringPool.BLANK);
		}
		else {
			vwDemandForecastActualImpl.setIsActive(isActive);
		}

		vwDemandForecastActualImpl.setGrossAmount(grossAmount);

		if (batchId == null) {
			vwDemandForecastActualImpl.setBatchId(StringPool.BLANK);
		}
		else {
			vwDemandForecastActualImpl.setBatchId(batchId);
		}

		if (forecastVer == null) {
			vwDemandForecastActualImpl.setForecastVer(StringPool.BLANK);
		}
		else {
			vwDemandForecastActualImpl.setForecastVer(forecastVer);
		}

		if (itemName == null) {
			vwDemandForecastActualImpl.setItemName(StringPool.BLANK);
		}
		else {
			vwDemandForecastActualImpl.setItemName(itemName);
		}

		if (forecastMonth == null) {
			vwDemandForecastActualImpl.setForecastMonth(StringPool.BLANK);
		}
		else {
			vwDemandForecastActualImpl.setForecastMonth(forecastMonth);
		}

		vwDemandForecastActualImpl.setNetSalesPrice(netSalesPrice);
		vwDemandForecastActualImpl.setNetSalesAmount(netSalesAmount);

		if (segment == null) {
			vwDemandForecastActualImpl.setSegment(StringPool.BLANK);
		}
		else {
			vwDemandForecastActualImpl.setSegment(segment);
		}

		vwDemandForecastActualImpl.setTotalDemandAmount(totalDemandAmount);

		if (forecastName == null) {
			vwDemandForecastActualImpl.setForecastName(StringPool.BLANK);
		}
		else {
			vwDemandForecastActualImpl.setForecastName(forecastName);
		}

		vwDemandForecastActualImpl.setMarketSizeUnits(marketSizeUnits);
		vwDemandForecastActualImpl.setDemandId(demandId);

		vwDemandForecastActualImpl.resetOriginalValues();

		return vwDemandForecastActualImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		forecastYear = objectInput.readUTF();

		grossUnits = objectInput.readDouble();
		businessUnitNo = objectInput.readUTF();

		totalDemandUnits = objectInput.readDouble();
		brandName = objectInput.readUTF();
		itemId = objectInput.readUTF();
		organizationKey = objectInput.readUTF();
		source = objectInput.readUTF();
		marketShareRatio = objectInput.readUTF();
		businessUnitName = objectInput.readUTF();

		demandForecastActualSid = objectInput.readInt();

		marketShareUnits = objectInput.readDouble();

		inventoryUnitChange = objectInput.readDouble();
		uncapturedUnitsRatio = objectInput.readUTF();
		country = objectInput.readUTF();
		forecastType = objectInput.readUTF();
		brandId = objectInput.readUTF();
		isForecast = objectInput.readUTF();

		uncapturedUnits = objectInput.readDouble();

		grossPrice = objectInput.readDouble();
		isActive = objectInput.readUTF();

		grossAmount = objectInput.readDouble();
		batchId = objectInput.readUTF();
		forecastVer = objectInput.readUTF();
		itemName = objectInput.readUTF();
		forecastMonth = objectInput.readUTF();

		netSalesPrice = objectInput.readDouble();

		netSalesAmount = objectInput.readDouble();
		segment = objectInput.readUTF();

		totalDemandAmount = objectInput.readDouble();
		forecastName = objectInput.readUTF();

		marketSizeUnits = objectInput.readDouble();

		demandId = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		if (forecastYear == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(forecastYear);
		}

		objectOutput.writeDouble(grossUnits);

		if (businessUnitNo == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(businessUnitNo);
		}

		objectOutput.writeDouble(totalDemandUnits);

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

		if (marketShareRatio == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(marketShareRatio);
		}

		if (businessUnitName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(businessUnitName);
		}

		objectOutput.writeInt(demandForecastActualSid);

		objectOutput.writeDouble(marketShareUnits);

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

		objectOutput.writeDouble(uncapturedUnits);

		objectOutput.writeDouble(grossPrice);

		if (isActive == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(isActive);
		}

		objectOutput.writeDouble(grossAmount);

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

		if (itemName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(itemName);
		}

		if (forecastMonth == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(forecastMonth);
		}

		objectOutput.writeDouble(netSalesPrice);

		objectOutput.writeDouble(netSalesAmount);

		if (segment == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(segment);
		}

		objectOutput.writeDouble(totalDemandAmount);

		if (forecastName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(forecastName);
		}

		objectOutput.writeDouble(marketSizeUnits);

		objectOutput.writeInt(demandId);
	}

	public String forecastYear;
	public double grossUnits;
	public String businessUnitNo;
	public double totalDemandUnits;
	public String brandName;
	public String itemId;
	public String organizationKey;
	public String source;
	public String marketShareRatio;
	public String businessUnitName;
	public int demandForecastActualSid;
	public double marketShareUnits;
	public double inventoryUnitChange;
	public String uncapturedUnitsRatio;
	public String country;
	public String forecastType;
	public String brandId;
	public String isForecast;
	public double uncapturedUnits;
	public double grossPrice;
	public String isActive;
	public double grossAmount;
	public String batchId;
	public String forecastVer;
	public String itemName;
	public String forecastMonth;
	public double netSalesPrice;
	public double netSalesAmount;
	public String segment;
	public double totalDemandAmount;
	public String forecastName;
	public double marketSizeUnits;
	public int demandId;
}