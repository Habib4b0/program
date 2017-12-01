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

import com.stpl.app.model.VwIvldDemandForecastActual;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing VwIvldDemandForecastActual in entity cache.
 *
 * @author
 * @see VwIvldDemandForecastActual
 * @generated
 */
@ProviderType
public class VwIvldDemandForecastActualCacheModel implements CacheModel<VwIvldDemandForecastActual>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof VwIvldDemandForecastActualCacheModel)) {
			return false;
		}

		VwIvldDemandForecastActualCacheModel vwIvldDemandForecastActualCacheModel =
			(VwIvldDemandForecastActualCacheModel)obj;

		if (ivldDemandActualForecastSid == vwIvldDemandForecastActualCacheModel.ivldDemandActualForecastSid) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, ivldDemandActualForecastSid);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(79);

		sb.append("{demandIntSid=");
		sb.append(demandIntSid);
		sb.append(", forecastYear=");
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
		sb.append(", ivldDemandActualForecastSid=");
		sb.append(ivldDemandActualForecastSid);
		sb.append(", businessUnitName=");
		sb.append(businessUnitName);
		sb.append(", addChgDelIndicator=");
		sb.append(addChgDelIndicator);
		sb.append(", errorCode=");
		sb.append(errorCode);
		sb.append(", marketShareUnits=");
		sb.append(marketShareUnits);
		sb.append(", inventoryUnitChange=");
		sb.append(inventoryUnitChange);
		sb.append(", reprocessedFlag=");
		sb.append(reprocessedFlag);
		sb.append(", uncapturedUnitsRatio=");
		sb.append(uncapturedUnitsRatio);
		sb.append(", reasonForFailure=");
		sb.append(reasonForFailure);
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
		sb.append(", errorField=");
		sb.append(errorField);
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
		sb.append(", checkRecord=");
		sb.append(checkRecord);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public VwIvldDemandForecastActual toEntityModel() {
		VwIvldDemandForecastActualImpl vwIvldDemandForecastActualImpl = new VwIvldDemandForecastActualImpl();

		if (demandIntSid == null) {
			vwIvldDemandForecastActualImpl.setDemandIntSid(StringPool.BLANK);
		}
		else {
			vwIvldDemandForecastActualImpl.setDemandIntSid(demandIntSid);
		}

		if (forecastYear == null) {
			vwIvldDemandForecastActualImpl.setForecastYear(StringPool.BLANK);
		}
		else {
			vwIvldDemandForecastActualImpl.setForecastYear(forecastYear);
		}

		if (grossUnits == null) {
			vwIvldDemandForecastActualImpl.setGrossUnits(StringPool.BLANK);
		}
		else {
			vwIvldDemandForecastActualImpl.setGrossUnits(grossUnits);
		}

		if (businessUnitNo == null) {
			vwIvldDemandForecastActualImpl.setBusinessUnitNo(StringPool.BLANK);
		}
		else {
			vwIvldDemandForecastActualImpl.setBusinessUnitNo(businessUnitNo);
		}

		if (totalDemandUnits == null) {
			vwIvldDemandForecastActualImpl.setTotalDemandUnits(StringPool.BLANK);
		}
		else {
			vwIvldDemandForecastActualImpl.setTotalDemandUnits(totalDemandUnits);
		}

		if (brandName == null) {
			vwIvldDemandForecastActualImpl.setBrandName(StringPool.BLANK);
		}
		else {
			vwIvldDemandForecastActualImpl.setBrandName(brandName);
		}

		if (itemId == null) {
			vwIvldDemandForecastActualImpl.setItemId(StringPool.BLANK);
		}
		else {
			vwIvldDemandForecastActualImpl.setItemId(itemId);
		}

		if (organizationKey == null) {
			vwIvldDemandForecastActualImpl.setOrganizationKey(StringPool.BLANK);
		}
		else {
			vwIvldDemandForecastActualImpl.setOrganizationKey(organizationKey);
		}

		if (source == null) {
			vwIvldDemandForecastActualImpl.setSource(StringPool.BLANK);
		}
		else {
			vwIvldDemandForecastActualImpl.setSource(source);
		}

		if (marketShareRatio == null) {
			vwIvldDemandForecastActualImpl.setMarketShareRatio(StringPool.BLANK);
		}
		else {
			vwIvldDemandForecastActualImpl.setMarketShareRatio(marketShareRatio);
		}

		vwIvldDemandForecastActualImpl.setIvldDemandActualForecastSid(ivldDemandActualForecastSid);

		if (businessUnitName == null) {
			vwIvldDemandForecastActualImpl.setBusinessUnitName(StringPool.BLANK);
		}
		else {
			vwIvldDemandForecastActualImpl.setBusinessUnitName(businessUnitName);
		}

		if (addChgDelIndicator == null) {
			vwIvldDemandForecastActualImpl.setAddChgDelIndicator(StringPool.BLANK);
		}
		else {
			vwIvldDemandForecastActualImpl.setAddChgDelIndicator(addChgDelIndicator);
		}

		if (errorCode == null) {
			vwIvldDemandForecastActualImpl.setErrorCode(StringPool.BLANK);
		}
		else {
			vwIvldDemandForecastActualImpl.setErrorCode(errorCode);
		}

		if (marketShareUnits == null) {
			vwIvldDemandForecastActualImpl.setMarketShareUnits(StringPool.BLANK);
		}
		else {
			vwIvldDemandForecastActualImpl.setMarketShareUnits(marketShareUnits);
		}

		if (inventoryUnitChange == null) {
			vwIvldDemandForecastActualImpl.setInventoryUnitChange(StringPool.BLANK);
		}
		else {
			vwIvldDemandForecastActualImpl.setInventoryUnitChange(inventoryUnitChange);
		}

		if (reprocessedFlag == null) {
			vwIvldDemandForecastActualImpl.setReprocessedFlag(StringPool.BLANK);
		}
		else {
			vwIvldDemandForecastActualImpl.setReprocessedFlag(reprocessedFlag);
		}

		if (uncapturedUnitsRatio == null) {
			vwIvldDemandForecastActualImpl.setUncapturedUnitsRatio(StringPool.BLANK);
		}
		else {
			vwIvldDemandForecastActualImpl.setUncapturedUnitsRatio(uncapturedUnitsRatio);
		}

		if (reasonForFailure == null) {
			vwIvldDemandForecastActualImpl.setReasonForFailure(StringPool.BLANK);
		}
		else {
			vwIvldDemandForecastActualImpl.setReasonForFailure(reasonForFailure);
		}

		if (country == null) {
			vwIvldDemandForecastActualImpl.setCountry(StringPool.BLANK);
		}
		else {
			vwIvldDemandForecastActualImpl.setCountry(country);
		}

		if (forecastType == null) {
			vwIvldDemandForecastActualImpl.setForecastType(StringPool.BLANK);
		}
		else {
			vwIvldDemandForecastActualImpl.setForecastType(forecastType);
		}

		if (brandId == null) {
			vwIvldDemandForecastActualImpl.setBrandId(StringPool.BLANK);
		}
		else {
			vwIvldDemandForecastActualImpl.setBrandId(brandId);
		}

		if (isForecast == null) {
			vwIvldDemandForecastActualImpl.setIsForecast(StringPool.BLANK);
		}
		else {
			vwIvldDemandForecastActualImpl.setIsForecast(isForecast);
		}

		if (uncapturedUnits == null) {
			vwIvldDemandForecastActualImpl.setUncapturedUnits(StringPool.BLANK);
		}
		else {
			vwIvldDemandForecastActualImpl.setUncapturedUnits(uncapturedUnits);
		}

		if (grossPrice == null) {
			vwIvldDemandForecastActualImpl.setGrossPrice(StringPool.BLANK);
		}
		else {
			vwIvldDemandForecastActualImpl.setGrossPrice(grossPrice);
		}

		if (isActive == null) {
			vwIvldDemandForecastActualImpl.setIsActive(StringPool.BLANK);
		}
		else {
			vwIvldDemandForecastActualImpl.setIsActive(isActive);
		}

		if (grossAmount == null) {
			vwIvldDemandForecastActualImpl.setGrossAmount(StringPool.BLANK);
		}
		else {
			vwIvldDemandForecastActualImpl.setGrossAmount(grossAmount);
		}

		if (batchId == null) {
			vwIvldDemandForecastActualImpl.setBatchId(StringPool.BLANK);
		}
		else {
			vwIvldDemandForecastActualImpl.setBatchId(batchId);
		}

		if (forecastVer == null) {
			vwIvldDemandForecastActualImpl.setForecastVer(StringPool.BLANK);
		}
		else {
			vwIvldDemandForecastActualImpl.setForecastVer(forecastVer);
		}

		if (itemName == null) {
			vwIvldDemandForecastActualImpl.setItemName(StringPool.BLANK);
		}
		else {
			vwIvldDemandForecastActualImpl.setItemName(itemName);
		}

		if (forecastMonth == null) {
			vwIvldDemandForecastActualImpl.setForecastMonth(StringPool.BLANK);
		}
		else {
			vwIvldDemandForecastActualImpl.setForecastMonth(forecastMonth);
		}

		if (errorField == null) {
			vwIvldDemandForecastActualImpl.setErrorField(StringPool.BLANK);
		}
		else {
			vwIvldDemandForecastActualImpl.setErrorField(errorField);
		}

		if (netSalesPrice == null) {
			vwIvldDemandForecastActualImpl.setNetSalesPrice(StringPool.BLANK);
		}
		else {
			vwIvldDemandForecastActualImpl.setNetSalesPrice(netSalesPrice);
		}

		if (netSalesAmount == null) {
			vwIvldDemandForecastActualImpl.setNetSalesAmount(StringPool.BLANK);
		}
		else {
			vwIvldDemandForecastActualImpl.setNetSalesAmount(netSalesAmount);
		}

		if (segment == null) {
			vwIvldDemandForecastActualImpl.setSegment(StringPool.BLANK);
		}
		else {
			vwIvldDemandForecastActualImpl.setSegment(segment);
		}

		if (totalDemandAmount == null) {
			vwIvldDemandForecastActualImpl.setTotalDemandAmount(StringPool.BLANK);
		}
		else {
			vwIvldDemandForecastActualImpl.setTotalDemandAmount(totalDemandAmount);
		}

		if (forecastName == null) {
			vwIvldDemandForecastActualImpl.setForecastName(StringPool.BLANK);
		}
		else {
			vwIvldDemandForecastActualImpl.setForecastName(forecastName);
		}

		if (marketSizeUnits == null) {
			vwIvldDemandForecastActualImpl.setMarketSizeUnits(StringPool.BLANK);
		}
		else {
			vwIvldDemandForecastActualImpl.setMarketSizeUnits(marketSizeUnits);
		}

		vwIvldDemandForecastActualImpl.setCheckRecord(checkRecord);

		vwIvldDemandForecastActualImpl.resetOriginalValues();

		return vwIvldDemandForecastActualImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		demandIntSid = objectInput.readUTF();
		forecastYear = objectInput.readUTF();
		grossUnits = objectInput.readUTF();
		businessUnitNo = objectInput.readUTF();
		totalDemandUnits = objectInput.readUTF();
		brandName = objectInput.readUTF();
		itemId = objectInput.readUTF();
		organizationKey = objectInput.readUTF();
		source = objectInput.readUTF();
		marketShareRatio = objectInput.readUTF();

		ivldDemandActualForecastSid = objectInput.readInt();
		businessUnitName = objectInput.readUTF();
		addChgDelIndicator = objectInput.readUTF();
		errorCode = objectInput.readUTF();
		marketShareUnits = objectInput.readUTF();
		inventoryUnitChange = objectInput.readUTF();
		reprocessedFlag = objectInput.readUTF();
		uncapturedUnitsRatio = objectInput.readUTF();
		reasonForFailure = objectInput.readUTF();
		country = objectInput.readUTF();
		forecastType = objectInput.readUTF();
		brandId = objectInput.readUTF();
		isForecast = objectInput.readUTF();
		uncapturedUnits = objectInput.readUTF();
		grossPrice = objectInput.readUTF();
		isActive = objectInput.readUTF();
		grossAmount = objectInput.readUTF();
		batchId = objectInput.readUTF();
		forecastVer = objectInput.readUTF();
		itemName = objectInput.readUTF();
		forecastMonth = objectInput.readUTF();
		errorField = objectInput.readUTF();
		netSalesPrice = objectInput.readUTF();
		netSalesAmount = objectInput.readUTF();
		segment = objectInput.readUTF();
		totalDemandAmount = objectInput.readUTF();
		forecastName = objectInput.readUTF();
		marketSizeUnits = objectInput.readUTF();

		checkRecord = objectInput.readBoolean();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		if (demandIntSid == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(demandIntSid);
		}

		if (forecastYear == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(forecastYear);
		}

		if (grossUnits == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(grossUnits);
		}

		if (businessUnitNo == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(businessUnitNo);
		}

		if (totalDemandUnits == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(totalDemandUnits);
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

		if (marketShareRatio == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(marketShareRatio);
		}

		objectOutput.writeInt(ivldDemandActualForecastSid);

		if (businessUnitName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(businessUnitName);
		}

		if (addChgDelIndicator == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(addChgDelIndicator);
		}

		if (errorCode == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(errorCode);
		}

		if (marketShareUnits == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(marketShareUnits);
		}

		if (inventoryUnitChange == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(inventoryUnitChange);
		}

		if (reprocessedFlag == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(reprocessedFlag);
		}

		if (uncapturedUnitsRatio == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(uncapturedUnitsRatio);
		}

		if (reasonForFailure == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(reasonForFailure);
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

		if (uncapturedUnits == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(uncapturedUnits);
		}

		if (grossPrice == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(grossPrice);
		}

		if (isActive == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(isActive);
		}

		if (grossAmount == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(grossAmount);
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

		if (errorField == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(errorField);
		}

		if (netSalesPrice == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(netSalesPrice);
		}

		if (netSalesAmount == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(netSalesAmount);
		}

		if (segment == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(segment);
		}

		if (totalDemandAmount == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(totalDemandAmount);
		}

		if (forecastName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(forecastName);
		}

		if (marketSizeUnits == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(marketSizeUnits);
		}

		objectOutput.writeBoolean(checkRecord);
	}

	public String demandIntSid;
	public String forecastYear;
	public String grossUnits;
	public String businessUnitNo;
	public String totalDemandUnits;
	public String brandName;
	public String itemId;
	public String organizationKey;
	public String source;
	public String marketShareRatio;
	public int ivldDemandActualForecastSid;
	public String businessUnitName;
	public String addChgDelIndicator;
	public String errorCode;
	public String marketShareUnits;
	public String inventoryUnitChange;
	public String reprocessedFlag;
	public String uncapturedUnitsRatio;
	public String reasonForFailure;
	public String country;
	public String forecastType;
	public String brandId;
	public String isForecast;
	public String uncapturedUnits;
	public String grossPrice;
	public String isActive;
	public String grossAmount;
	public String batchId;
	public String forecastVer;
	public String itemName;
	public String forecastMonth;
	public String errorField;
	public String netSalesPrice;
	public String netSalesAmount;
	public String segment;
	public String totalDemandAmount;
	public String forecastName;
	public String marketSizeUnits;
	public boolean checkRecord;
}