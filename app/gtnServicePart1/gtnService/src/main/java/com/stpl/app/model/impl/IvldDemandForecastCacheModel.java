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

import com.stpl.app.model.IvldDemandForecast;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing IvldDemandForecast in entity cache.
 *
 * @author
 * @see IvldDemandForecast
 * @generated
 */
@ProviderType
public class IvldDemandForecastCacheModel implements CacheModel<IvldDemandForecast>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof IvldDemandForecastCacheModel)) {
			return false;
		}

		IvldDemandForecastCacheModel ivldDemandForecastCacheModel = (IvldDemandForecastCacheModel)obj;

		if (ivldDemandForecastSid == ivldDemandForecastCacheModel.ivldDemandForecastSid) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, ivldDemandForecastSid);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(81);

		sb.append("{forecastYear=");
		sb.append(forecastYear);
		sb.append(", grossUnits=");
		sb.append(grossUnits);
		sb.append(", totalDemandUnits=");
		sb.append(totalDemandUnits);
		sb.append(", itemId=");
		sb.append(itemId);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", organizationKey=");
		sb.append(organizationKey);
		sb.append(", ivldDemandForecastSid=");
		sb.append(ivldDemandForecastSid);
		sb.append(", source=");
		sb.append(source);
		sb.append(", marketShareRatio=");
		sb.append(marketShareRatio);
		sb.append(", createdBy=");
		sb.append(createdBy);
		sb.append(", createdDate=");
		sb.append(createdDate);
		sb.append(", addChgDelIndicator=");
		sb.append(addChgDelIndicator);
		sb.append(", itemIdentifier=");
		sb.append(itemIdentifier);
		sb.append(", errorCode=");
		sb.append(errorCode);
		sb.append(", intfInsertedDate=");
		sb.append(intfInsertedDate);
		sb.append(", modifiedBy=");
		sb.append(modifiedBy);
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
		sb.append(", demandForecastInterfaceId=");
		sb.append(demandForecastInterfaceId);
		sb.append(", uncapturedUnits=");
		sb.append(uncapturedUnits);
		sb.append(", grossPrice=");
		sb.append(grossPrice);
		sb.append(", grossAmount=");
		sb.append(grossAmount);
		sb.append(", itemIdentifierCodeQualifier=");
		sb.append(itemIdentifierCodeQualifier);
		sb.append(", forecastVer=");
		sb.append(forecastVer);
		sb.append(", batchId=");
		sb.append(batchId);
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
	public IvldDemandForecast toEntityModel() {
		IvldDemandForecastImpl ivldDemandForecastImpl = new IvldDemandForecastImpl();

		if (forecastYear == null) {
			ivldDemandForecastImpl.setForecastYear(StringPool.BLANK);
		}
		else {
			ivldDemandForecastImpl.setForecastYear(forecastYear);
		}

		if (grossUnits == null) {
			ivldDemandForecastImpl.setGrossUnits(StringPool.BLANK);
		}
		else {
			ivldDemandForecastImpl.setGrossUnits(grossUnits);
		}

		if (totalDemandUnits == null) {
			ivldDemandForecastImpl.setTotalDemandUnits(StringPool.BLANK);
		}
		else {
			ivldDemandForecastImpl.setTotalDemandUnits(totalDemandUnits);
		}

		if (itemId == null) {
			ivldDemandForecastImpl.setItemId(StringPool.BLANK);
		}
		else {
			ivldDemandForecastImpl.setItemId(itemId);
		}

		if (modifiedDate == Long.MIN_VALUE) {
			ivldDemandForecastImpl.setModifiedDate(null);
		}
		else {
			ivldDemandForecastImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (organizationKey == null) {
			ivldDemandForecastImpl.setOrganizationKey(StringPool.BLANK);
		}
		else {
			ivldDemandForecastImpl.setOrganizationKey(organizationKey);
		}

		ivldDemandForecastImpl.setIvldDemandForecastSid(ivldDemandForecastSid);

		if (source == null) {
			ivldDemandForecastImpl.setSource(StringPool.BLANK);
		}
		else {
			ivldDemandForecastImpl.setSource(source);
		}

		if (marketShareRatio == null) {
			ivldDemandForecastImpl.setMarketShareRatio(StringPool.BLANK);
		}
		else {
			ivldDemandForecastImpl.setMarketShareRatio(marketShareRatio);
		}

		if (createdBy == null) {
			ivldDemandForecastImpl.setCreatedBy(StringPool.BLANK);
		}
		else {
			ivldDemandForecastImpl.setCreatedBy(createdBy);
		}

		if (createdDate == Long.MIN_VALUE) {
			ivldDemandForecastImpl.setCreatedDate(null);
		}
		else {
			ivldDemandForecastImpl.setCreatedDate(new Date(createdDate));
		}

		if (addChgDelIndicator == null) {
			ivldDemandForecastImpl.setAddChgDelIndicator(StringPool.BLANK);
		}
		else {
			ivldDemandForecastImpl.setAddChgDelIndicator(addChgDelIndicator);
		}

		if (itemIdentifier == null) {
			ivldDemandForecastImpl.setItemIdentifier(StringPool.BLANK);
		}
		else {
			ivldDemandForecastImpl.setItemIdentifier(itemIdentifier);
		}

		if (errorCode == null) {
			ivldDemandForecastImpl.setErrorCode(StringPool.BLANK);
		}
		else {
			ivldDemandForecastImpl.setErrorCode(errorCode);
		}

		if (intfInsertedDate == null) {
			ivldDemandForecastImpl.setIntfInsertedDate(StringPool.BLANK);
		}
		else {
			ivldDemandForecastImpl.setIntfInsertedDate(intfInsertedDate);
		}

		if (modifiedBy == null) {
			ivldDemandForecastImpl.setModifiedBy(StringPool.BLANK);
		}
		else {
			ivldDemandForecastImpl.setModifiedBy(modifiedBy);
		}

		if (marketShareUnits == null) {
			ivldDemandForecastImpl.setMarketShareUnits(StringPool.BLANK);
		}
		else {
			ivldDemandForecastImpl.setMarketShareUnits(marketShareUnits);
		}

		if (inventoryUnitChange == null) {
			ivldDemandForecastImpl.setInventoryUnitChange(StringPool.BLANK);
		}
		else {
			ivldDemandForecastImpl.setInventoryUnitChange(inventoryUnitChange);
		}

		if (reprocessedFlag == null) {
			ivldDemandForecastImpl.setReprocessedFlag(StringPool.BLANK);
		}
		else {
			ivldDemandForecastImpl.setReprocessedFlag(reprocessedFlag);
		}

		if (uncapturedUnitsRatio == null) {
			ivldDemandForecastImpl.setUncapturedUnitsRatio(StringPool.BLANK);
		}
		else {
			ivldDemandForecastImpl.setUncapturedUnitsRatio(uncapturedUnitsRatio);
		}

		if (reasonForFailure == null) {
			ivldDemandForecastImpl.setReasonForFailure(StringPool.BLANK);
		}
		else {
			ivldDemandForecastImpl.setReasonForFailure(reasonForFailure);
		}

		if (country == null) {
			ivldDemandForecastImpl.setCountry(StringPool.BLANK);
		}
		else {
			ivldDemandForecastImpl.setCountry(country);
		}

		if (forecastType == null) {
			ivldDemandForecastImpl.setForecastType(StringPool.BLANK);
		}
		else {
			ivldDemandForecastImpl.setForecastType(forecastType);
		}

		if (brandId == null) {
			ivldDemandForecastImpl.setBrandId(StringPool.BLANK);
		}
		else {
			ivldDemandForecastImpl.setBrandId(brandId);
		}

		if (demandForecastInterfaceId == null) {
			ivldDemandForecastImpl.setDemandForecastInterfaceId(StringPool.BLANK);
		}
		else {
			ivldDemandForecastImpl.setDemandForecastInterfaceId(demandForecastInterfaceId);
		}

		if (uncapturedUnits == null) {
			ivldDemandForecastImpl.setUncapturedUnits(StringPool.BLANK);
		}
		else {
			ivldDemandForecastImpl.setUncapturedUnits(uncapturedUnits);
		}

		if (grossPrice == null) {
			ivldDemandForecastImpl.setGrossPrice(StringPool.BLANK);
		}
		else {
			ivldDemandForecastImpl.setGrossPrice(grossPrice);
		}

		if (grossAmount == null) {
			ivldDemandForecastImpl.setGrossAmount(StringPool.BLANK);
		}
		else {
			ivldDemandForecastImpl.setGrossAmount(grossAmount);
		}

		if (itemIdentifierCodeQualifier == null) {
			ivldDemandForecastImpl.setItemIdentifierCodeQualifier(StringPool.BLANK);
		}
		else {
			ivldDemandForecastImpl.setItemIdentifierCodeQualifier(itemIdentifierCodeQualifier);
		}

		if (forecastVer == null) {
			ivldDemandForecastImpl.setForecastVer(StringPool.BLANK);
		}
		else {
			ivldDemandForecastImpl.setForecastVer(forecastVer);
		}

		if (batchId == null) {
			ivldDemandForecastImpl.setBatchId(StringPool.BLANK);
		}
		else {
			ivldDemandForecastImpl.setBatchId(batchId);
		}

		if (forecastMonth == null) {
			ivldDemandForecastImpl.setForecastMonth(StringPool.BLANK);
		}
		else {
			ivldDemandForecastImpl.setForecastMonth(forecastMonth);
		}

		if (errorField == null) {
			ivldDemandForecastImpl.setErrorField(StringPool.BLANK);
		}
		else {
			ivldDemandForecastImpl.setErrorField(errorField);
		}

		if (netSalesPrice == null) {
			ivldDemandForecastImpl.setNetSalesPrice(StringPool.BLANK);
		}
		else {
			ivldDemandForecastImpl.setNetSalesPrice(netSalesPrice);
		}

		if (netSalesAmount == null) {
			ivldDemandForecastImpl.setNetSalesAmount(StringPool.BLANK);
		}
		else {
			ivldDemandForecastImpl.setNetSalesAmount(netSalesAmount);
		}

		if (segment == null) {
			ivldDemandForecastImpl.setSegment(StringPool.BLANK);
		}
		else {
			ivldDemandForecastImpl.setSegment(segment);
		}

		if (totalDemandAmount == null) {
			ivldDemandForecastImpl.setTotalDemandAmount(StringPool.BLANK);
		}
		else {
			ivldDemandForecastImpl.setTotalDemandAmount(totalDemandAmount);
		}

		if (forecastName == null) {
			ivldDemandForecastImpl.setForecastName(StringPool.BLANK);
		}
		else {
			ivldDemandForecastImpl.setForecastName(forecastName);
		}

		if (marketSizeUnits == null) {
			ivldDemandForecastImpl.setMarketSizeUnits(StringPool.BLANK);
		}
		else {
			ivldDemandForecastImpl.setMarketSizeUnits(marketSizeUnits);
		}

		ivldDemandForecastImpl.setCheckRecord(checkRecord);

		ivldDemandForecastImpl.resetOriginalValues();

		return ivldDemandForecastImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		forecastYear = objectInput.readUTF();
		grossUnits = objectInput.readUTF();
		totalDemandUnits = objectInput.readUTF();
		itemId = objectInput.readUTF();
		modifiedDate = objectInput.readLong();
		organizationKey = objectInput.readUTF();

		ivldDemandForecastSid = objectInput.readInt();
		source = objectInput.readUTF();
		marketShareRatio = objectInput.readUTF();
		createdBy = objectInput.readUTF();
		createdDate = objectInput.readLong();
		addChgDelIndicator = objectInput.readUTF();
		itemIdentifier = objectInput.readUTF();
		errorCode = objectInput.readUTF();
		intfInsertedDate = objectInput.readUTF();
		modifiedBy = objectInput.readUTF();
		marketShareUnits = objectInput.readUTF();
		inventoryUnitChange = objectInput.readUTF();
		reprocessedFlag = objectInput.readUTF();
		uncapturedUnitsRatio = objectInput.readUTF();
		reasonForFailure = objectInput.readUTF();
		country = objectInput.readUTF();
		forecastType = objectInput.readUTF();
		brandId = objectInput.readUTF();
		demandForecastInterfaceId = objectInput.readUTF();
		uncapturedUnits = objectInput.readUTF();
		grossPrice = objectInput.readUTF();
		grossAmount = objectInput.readUTF();
		itemIdentifierCodeQualifier = objectInput.readUTF();
		forecastVer = objectInput.readUTF();
		batchId = objectInput.readUTF();
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

		if (totalDemandUnits == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(totalDemandUnits);
		}

		if (itemId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(itemId);
		}

		objectOutput.writeLong(modifiedDate);

		if (organizationKey == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(organizationKey);
		}

		objectOutput.writeInt(ivldDemandForecastSid);

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

		if (createdBy == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(createdBy);
		}

		objectOutput.writeLong(createdDate);

		if (addChgDelIndicator == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(addChgDelIndicator);
		}

		if (itemIdentifier == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(itemIdentifier);
		}

		if (errorCode == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(errorCode);
		}

		if (intfInsertedDate == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(intfInsertedDate);
		}

		if (modifiedBy == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(modifiedBy);
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

		if (demandForecastInterfaceId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(demandForecastInterfaceId);
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

		if (grossAmount == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(grossAmount);
		}

		if (itemIdentifierCodeQualifier == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(itemIdentifierCodeQualifier);
		}

		if (forecastVer == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(forecastVer);
		}

		if (batchId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(batchId);
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

	public String forecastYear;
	public String grossUnits;
	public String totalDemandUnits;
	public String itemId;
	public long modifiedDate;
	public String organizationKey;
	public int ivldDemandForecastSid;
	public String source;
	public String marketShareRatio;
	public String createdBy;
	public long createdDate;
	public String addChgDelIndicator;
	public String itemIdentifier;
	public String errorCode;
	public String intfInsertedDate;
	public String modifiedBy;
	public String marketShareUnits;
	public String inventoryUnitChange;
	public String reprocessedFlag;
	public String uncapturedUnitsRatio;
	public String reasonForFailure;
	public String country;
	public String forecastType;
	public String brandId;
	public String demandForecastInterfaceId;
	public String uncapturedUnits;
	public String grossPrice;
	public String grossAmount;
	public String itemIdentifierCodeQualifier;
	public String forecastVer;
	public String batchId;
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