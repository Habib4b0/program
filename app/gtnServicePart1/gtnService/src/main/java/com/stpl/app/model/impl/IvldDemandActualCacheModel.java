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

import com.stpl.app.model.IvldDemandActual;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing IvldDemandActual in entity cache.
 *
 * @author
 * @see IvldDemandActual
 * @generated
 */
@ProviderType
public class IvldDemandActualCacheModel implements CacheModel<IvldDemandActual>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof IvldDemandActualCacheModel)) {
			return false;
		}

		IvldDemandActualCacheModel ivldDemandActualCacheModel = (IvldDemandActualCacheModel)obj;

		if (ivldDemandActualSid == ivldDemandActualCacheModel.ivldDemandActualSid) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, ivldDemandActualSid);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(71);

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
		sb.append(", source=");
		sb.append(source);
		sb.append(", marketShareRatio=");
		sb.append(marketShareRatio);
		sb.append(", createdBy=");
		sb.append(createdBy);
		sb.append(", createdDate=");
		sb.append(createdDate);
		sb.append(", demandActualInterfaceId=");
		sb.append(demandActualInterfaceId);
		sb.append(", addChgDelIndicator=");
		sb.append(addChgDelIndicator);
		sb.append(", itemIdentifier=");
		sb.append(itemIdentifier);
		sb.append(", errorCode=");
		sb.append(errorCode);
		sb.append(", intfInsertedDate=");
		sb.append(intfInsertedDate);
		sb.append(", marketShareUnits=");
		sb.append(marketShareUnits);
		sb.append(", modifiedBy=");
		sb.append(modifiedBy);
		sb.append(", reprocessedFlag=");
		sb.append(reprocessedFlag);
		sb.append(", reasonForFailure=");
		sb.append(reasonForFailure);
		sb.append(", country=");
		sb.append(country);
		sb.append(", forecastType=");
		sb.append(forecastType);
		sb.append(", brandId=");
		sb.append(brandId);
		sb.append(", grossPrice=");
		sb.append(grossPrice);
		sb.append(", ivldDemandActualSid=");
		sb.append(ivldDemandActualSid);
		sb.append(", grossAmount=");
		sb.append(grossAmount);
		sb.append(", itemIdentifierCodeQualifier=");
		sb.append(itemIdentifierCodeQualifier);
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
		sb.append(", marketSizeUnits=");
		sb.append(marketSizeUnits);
		sb.append(", checkRecord=");
		sb.append(checkRecord);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public IvldDemandActual toEntityModel() {
		IvldDemandActualImpl ivldDemandActualImpl = new IvldDemandActualImpl();

		if (forecastYear == null) {
			ivldDemandActualImpl.setForecastYear(StringPool.BLANK);
		}
		else {
			ivldDemandActualImpl.setForecastYear(forecastYear);
		}

		if (grossUnits == null) {
			ivldDemandActualImpl.setGrossUnits(StringPool.BLANK);
		}
		else {
			ivldDemandActualImpl.setGrossUnits(grossUnits);
		}

		if (totalDemandUnits == null) {
			ivldDemandActualImpl.setTotalDemandUnits(StringPool.BLANK);
		}
		else {
			ivldDemandActualImpl.setTotalDemandUnits(totalDemandUnits);
		}

		if (itemId == null) {
			ivldDemandActualImpl.setItemId(StringPool.BLANK);
		}
		else {
			ivldDemandActualImpl.setItemId(itemId);
		}

		if (modifiedDate == Long.MIN_VALUE) {
			ivldDemandActualImpl.setModifiedDate(null);
		}
		else {
			ivldDemandActualImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (organizationKey == null) {
			ivldDemandActualImpl.setOrganizationKey(StringPool.BLANK);
		}
		else {
			ivldDemandActualImpl.setOrganizationKey(organizationKey);
		}

		if (source == null) {
			ivldDemandActualImpl.setSource(StringPool.BLANK);
		}
		else {
			ivldDemandActualImpl.setSource(source);
		}

		if (marketShareRatio == null) {
			ivldDemandActualImpl.setMarketShareRatio(StringPool.BLANK);
		}
		else {
			ivldDemandActualImpl.setMarketShareRatio(marketShareRatio);
		}

		if (createdBy == null) {
			ivldDemandActualImpl.setCreatedBy(StringPool.BLANK);
		}
		else {
			ivldDemandActualImpl.setCreatedBy(createdBy);
		}

		if (createdDate == Long.MIN_VALUE) {
			ivldDemandActualImpl.setCreatedDate(null);
		}
		else {
			ivldDemandActualImpl.setCreatedDate(new Date(createdDate));
		}

		if (demandActualInterfaceId == null) {
			ivldDemandActualImpl.setDemandActualInterfaceId(StringPool.BLANK);
		}
		else {
			ivldDemandActualImpl.setDemandActualInterfaceId(demandActualInterfaceId);
		}

		if (addChgDelIndicator == null) {
			ivldDemandActualImpl.setAddChgDelIndicator(StringPool.BLANK);
		}
		else {
			ivldDemandActualImpl.setAddChgDelIndicator(addChgDelIndicator);
		}

		if (itemIdentifier == null) {
			ivldDemandActualImpl.setItemIdentifier(StringPool.BLANK);
		}
		else {
			ivldDemandActualImpl.setItemIdentifier(itemIdentifier);
		}

		if (errorCode == null) {
			ivldDemandActualImpl.setErrorCode(StringPool.BLANK);
		}
		else {
			ivldDemandActualImpl.setErrorCode(errorCode);
		}

		if (intfInsertedDate == Long.MIN_VALUE) {
			ivldDemandActualImpl.setIntfInsertedDate(null);
		}
		else {
			ivldDemandActualImpl.setIntfInsertedDate(new Date(intfInsertedDate));
		}

		if (marketShareUnits == null) {
			ivldDemandActualImpl.setMarketShareUnits(StringPool.BLANK);
		}
		else {
			ivldDemandActualImpl.setMarketShareUnits(marketShareUnits);
		}

		if (modifiedBy == null) {
			ivldDemandActualImpl.setModifiedBy(StringPool.BLANK);
		}
		else {
			ivldDemandActualImpl.setModifiedBy(modifiedBy);
		}

		if (reprocessedFlag == null) {
			ivldDemandActualImpl.setReprocessedFlag(StringPool.BLANK);
		}
		else {
			ivldDemandActualImpl.setReprocessedFlag(reprocessedFlag);
		}

		if (reasonForFailure == null) {
			ivldDemandActualImpl.setReasonForFailure(StringPool.BLANK);
		}
		else {
			ivldDemandActualImpl.setReasonForFailure(reasonForFailure);
		}

		if (country == null) {
			ivldDemandActualImpl.setCountry(StringPool.BLANK);
		}
		else {
			ivldDemandActualImpl.setCountry(country);
		}

		if (forecastType == null) {
			ivldDemandActualImpl.setForecastType(StringPool.BLANK);
		}
		else {
			ivldDemandActualImpl.setForecastType(forecastType);
		}

		if (brandId == null) {
			ivldDemandActualImpl.setBrandId(StringPool.BLANK);
		}
		else {
			ivldDemandActualImpl.setBrandId(brandId);
		}

		if (grossPrice == null) {
			ivldDemandActualImpl.setGrossPrice(StringPool.BLANK);
		}
		else {
			ivldDemandActualImpl.setGrossPrice(grossPrice);
		}

		ivldDemandActualImpl.setIvldDemandActualSid(ivldDemandActualSid);

		if (grossAmount == null) {
			ivldDemandActualImpl.setGrossAmount(StringPool.BLANK);
		}
		else {
			ivldDemandActualImpl.setGrossAmount(grossAmount);
		}

		if (itemIdentifierCodeQualifier == null) {
			ivldDemandActualImpl.setItemIdentifierCodeQualifier(StringPool.BLANK);
		}
		else {
			ivldDemandActualImpl.setItemIdentifierCodeQualifier(itemIdentifierCodeQualifier);
		}

		if (batchId == null) {
			ivldDemandActualImpl.setBatchId(StringPool.BLANK);
		}
		else {
			ivldDemandActualImpl.setBatchId(batchId);
		}

		if (forecastMonth == null) {
			ivldDemandActualImpl.setForecastMonth(StringPool.BLANK);
		}
		else {
			ivldDemandActualImpl.setForecastMonth(forecastMonth);
		}

		if (errorField == null) {
			ivldDemandActualImpl.setErrorField(StringPool.BLANK);
		}
		else {
			ivldDemandActualImpl.setErrorField(errorField);
		}

		if (netSalesPrice == null) {
			ivldDemandActualImpl.setNetSalesPrice(StringPool.BLANK);
		}
		else {
			ivldDemandActualImpl.setNetSalesPrice(netSalesPrice);
		}

		if (netSalesAmount == null) {
			ivldDemandActualImpl.setNetSalesAmount(StringPool.BLANK);
		}
		else {
			ivldDemandActualImpl.setNetSalesAmount(netSalesAmount);
		}

		if (segment == null) {
			ivldDemandActualImpl.setSegment(StringPool.BLANK);
		}
		else {
			ivldDemandActualImpl.setSegment(segment);
		}

		if (totalDemandAmount == null) {
			ivldDemandActualImpl.setTotalDemandAmount(StringPool.BLANK);
		}
		else {
			ivldDemandActualImpl.setTotalDemandAmount(totalDemandAmount);
		}

		if (marketSizeUnits == null) {
			ivldDemandActualImpl.setMarketSizeUnits(StringPool.BLANK);
		}
		else {
			ivldDemandActualImpl.setMarketSizeUnits(marketSizeUnits);
		}

		ivldDemandActualImpl.setCheckRecord(checkRecord);

		ivldDemandActualImpl.resetOriginalValues();

		return ivldDemandActualImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		forecastYear = objectInput.readUTF();
		grossUnits = objectInput.readUTF();
		totalDemandUnits = objectInput.readUTF();
		itemId = objectInput.readUTF();
		modifiedDate = objectInput.readLong();
		organizationKey = objectInput.readUTF();
		source = objectInput.readUTF();
		marketShareRatio = objectInput.readUTF();
		createdBy = objectInput.readUTF();
		createdDate = objectInput.readLong();
		demandActualInterfaceId = objectInput.readUTF();
		addChgDelIndicator = objectInput.readUTF();
		itemIdentifier = objectInput.readUTF();
		errorCode = objectInput.readUTF();
		intfInsertedDate = objectInput.readLong();
		marketShareUnits = objectInput.readUTF();
		modifiedBy = objectInput.readUTF();
		reprocessedFlag = objectInput.readUTF();
		reasonForFailure = objectInput.readUTF();
		country = objectInput.readUTF();
		forecastType = objectInput.readUTF();
		brandId = objectInput.readUTF();
		grossPrice = objectInput.readUTF();

		ivldDemandActualSid = objectInput.readInt();
		grossAmount = objectInput.readUTF();
		itemIdentifierCodeQualifier = objectInput.readUTF();
		batchId = objectInput.readUTF();
		forecastMonth = objectInput.readUTF();
		errorField = objectInput.readUTF();
		netSalesPrice = objectInput.readUTF();
		netSalesAmount = objectInput.readUTF();
		segment = objectInput.readUTF();
		totalDemandAmount = objectInput.readUTF();
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

		if (demandActualInterfaceId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(demandActualInterfaceId);
		}

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

		objectOutput.writeLong(intfInsertedDate);

		if (marketShareUnits == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(marketShareUnits);
		}

		if (modifiedBy == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(modifiedBy);
		}

		if (reprocessedFlag == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(reprocessedFlag);
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

		if (grossPrice == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(grossPrice);
		}

		objectOutput.writeInt(ivldDemandActualSid);

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
	public String source;
	public String marketShareRatio;
	public String createdBy;
	public long createdDate;
	public String demandActualInterfaceId;
	public String addChgDelIndicator;
	public String itemIdentifier;
	public String errorCode;
	public long intfInsertedDate;
	public String marketShareUnits;
	public String modifiedBy;
	public String reprocessedFlag;
	public String reasonForFailure;
	public String country;
	public String forecastType;
	public String brandId;
	public String grossPrice;
	public int ivldDemandActualSid;
	public String grossAmount;
	public String itemIdentifierCodeQualifier;
	public String batchId;
	public String forecastMonth;
	public String errorField;
	public String netSalesPrice;
	public String netSalesAmount;
	public String segment;
	public String totalDemandAmount;
	public String marketSizeUnits;
	public boolean checkRecord;
}