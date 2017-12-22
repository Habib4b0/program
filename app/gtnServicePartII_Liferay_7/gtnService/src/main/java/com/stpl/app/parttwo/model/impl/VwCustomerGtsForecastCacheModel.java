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

import com.stpl.app.parttwo.model.VwCustomerGtsForecast;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing VwCustomerGtsForecast in entity cache.
 *
 * @author
 * @see VwCustomerGtsForecast
 * @generated
 */
@ProviderType
public class VwCustomerGtsForecastCacheModel implements CacheModel<VwCustomerGtsForecast>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof VwCustomerGtsForecastCacheModel)) {
			return false;
		}

		VwCustomerGtsForecastCacheModel vwCustomerGtsForecastCacheModel = (VwCustomerGtsForecastCacheModel)obj;

		if (customerGtsForecastSid == vwCustomerGtsForecastCacheModel.customerGtsForecastSid) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, customerGtsForecastSid);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(79);

		sb.append("{price=");
		sb.append(price);
		sb.append(", forecastYear=");
		sb.append(forecastYear);
		sb.append(", deductionAmount=");
		sb.append(deductionAmount);
		sb.append(", deductionId=");
		sb.append(deductionId);
		sb.append(", forecastDate=");
		sb.append(forecastDate);
		sb.append(", itemId=");
		sb.append(itemId);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", source=");
		sb.append(source);
		sb.append(", createdBy=");
		sb.append(createdBy);
		sb.append(", createdDate=");
		sb.append(createdDate);
		sb.append(", addChgDelIndicator=");
		sb.append(addChgDelIndicator);
		sb.append(", modifiedBy=");
		sb.append(modifiedBy);
		sb.append(", salesAmount=");
		sb.append(salesAmount);
		sb.append(", udc6=");
		sb.append(udc6);
		sb.append(", udc5=");
		sb.append(udc5);
		sb.append(", deductionType=");
		sb.append(deductionType);
		sb.append(", udc4=");
		sb.append(udc4);
		sb.append(", units=");
		sb.append(units);
		sb.append(", deductionRate=");
		sb.append(deductionRate);
		sb.append(", udc1=");
		sb.append(udc1);
		sb.append(", customerGtsForecastSid=");
		sb.append(customerGtsForecastSid);
		sb.append(", udc2=");
		sb.append(udc2);
		sb.append(", udc3=");
		sb.append(udc3);
		sb.append(", country=");
		sb.append(country);
		sb.append(", companyIdString=");
		sb.append(companyIdString);
		sb.append(", forecastValueType=");
		sb.append(forecastValueType);
		sb.append(", deductionCategory=");
		sb.append(deductionCategory);
		sb.append(", adjustmentCode=");
		sb.append(adjustmentCode);
		sb.append(", deductionProgramType=");
		sb.append(deductionProgramType);
		sb.append(", customerGtsForecastIntfId=");
		sb.append(customerGtsForecastIntfId);
		sb.append(", salesInclusion=");
		sb.append(salesInclusion);
		sb.append(", forecastVer=");
		sb.append(forecastVer);
		sb.append(", batchId=");
		sb.append(batchId);
		sb.append(", priceType=");
		sb.append(priceType);
		sb.append(", forecastMonth=");
		sb.append(forecastMonth);
		sb.append(", deductionInclusion=");
		sb.append(deductionInclusion);
		sb.append(", segment=");
		sb.append(segment);
		sb.append(", brand=");
		sb.append(brand);
		sb.append(", forecastName=");
		sb.append(forecastName);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public VwCustomerGtsForecast toEntityModel() {
		VwCustomerGtsForecastImpl vwCustomerGtsForecastImpl = new VwCustomerGtsForecastImpl();

		vwCustomerGtsForecastImpl.setPrice(price);

		if (forecastYear == null) {
			vwCustomerGtsForecastImpl.setForecastYear(StringPool.BLANK);
		}
		else {
			vwCustomerGtsForecastImpl.setForecastYear(forecastYear);
		}

		vwCustomerGtsForecastImpl.setDeductionAmount(deductionAmount);

		if (deductionId == null) {
			vwCustomerGtsForecastImpl.setDeductionId(StringPool.BLANK);
		}
		else {
			vwCustomerGtsForecastImpl.setDeductionId(deductionId);
		}

		if (forecastDate == Long.MIN_VALUE) {
			vwCustomerGtsForecastImpl.setForecastDate(null);
		}
		else {
			vwCustomerGtsForecastImpl.setForecastDate(new Date(forecastDate));
		}

		if (itemId == null) {
			vwCustomerGtsForecastImpl.setItemId(StringPool.BLANK);
		}
		else {
			vwCustomerGtsForecastImpl.setItemId(itemId);
		}

		if (modifiedDate == Long.MIN_VALUE) {
			vwCustomerGtsForecastImpl.setModifiedDate(null);
		}
		else {
			vwCustomerGtsForecastImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (source == null) {
			vwCustomerGtsForecastImpl.setSource(StringPool.BLANK);
		}
		else {
			vwCustomerGtsForecastImpl.setSource(source);
		}

		if (createdBy == null) {
			vwCustomerGtsForecastImpl.setCreatedBy(StringPool.BLANK);
		}
		else {
			vwCustomerGtsForecastImpl.setCreatedBy(createdBy);
		}

		if (createdDate == Long.MIN_VALUE) {
			vwCustomerGtsForecastImpl.setCreatedDate(null);
		}
		else {
			vwCustomerGtsForecastImpl.setCreatedDate(new Date(createdDate));
		}

		if (addChgDelIndicator == null) {
			vwCustomerGtsForecastImpl.setAddChgDelIndicator(StringPool.BLANK);
		}
		else {
			vwCustomerGtsForecastImpl.setAddChgDelIndicator(addChgDelIndicator);
		}

		if (modifiedBy == null) {
			vwCustomerGtsForecastImpl.setModifiedBy(StringPool.BLANK);
		}
		else {
			vwCustomerGtsForecastImpl.setModifiedBy(modifiedBy);
		}

		vwCustomerGtsForecastImpl.setSalesAmount(salesAmount);

		if (udc6 == null) {
			vwCustomerGtsForecastImpl.setUdc6(StringPool.BLANK);
		}
		else {
			vwCustomerGtsForecastImpl.setUdc6(udc6);
		}

		if (udc5 == null) {
			vwCustomerGtsForecastImpl.setUdc5(StringPool.BLANK);
		}
		else {
			vwCustomerGtsForecastImpl.setUdc5(udc5);
		}

		if (deductionType == null) {
			vwCustomerGtsForecastImpl.setDeductionType(StringPool.BLANK);
		}
		else {
			vwCustomerGtsForecastImpl.setDeductionType(deductionType);
		}

		if (udc4 == null) {
			vwCustomerGtsForecastImpl.setUdc4(StringPool.BLANK);
		}
		else {
			vwCustomerGtsForecastImpl.setUdc4(udc4);
		}

		vwCustomerGtsForecastImpl.setUnits(units);
		vwCustomerGtsForecastImpl.setDeductionRate(deductionRate);

		if (udc1 == null) {
			vwCustomerGtsForecastImpl.setUdc1(StringPool.BLANK);
		}
		else {
			vwCustomerGtsForecastImpl.setUdc1(udc1);
		}

		vwCustomerGtsForecastImpl.setCustomerGtsForecastSid(customerGtsForecastSid);

		if (udc2 == null) {
			vwCustomerGtsForecastImpl.setUdc2(StringPool.BLANK);
		}
		else {
			vwCustomerGtsForecastImpl.setUdc2(udc2);
		}

		if (udc3 == null) {
			vwCustomerGtsForecastImpl.setUdc3(StringPool.BLANK);
		}
		else {
			vwCustomerGtsForecastImpl.setUdc3(udc3);
		}

		if (country == null) {
			vwCustomerGtsForecastImpl.setCountry(StringPool.BLANK);
		}
		else {
			vwCustomerGtsForecastImpl.setCountry(country);
		}

		if (companyIdString == null) {
			vwCustomerGtsForecastImpl.setCompanyIdString(StringPool.BLANK);
		}
		else {
			vwCustomerGtsForecastImpl.setCompanyIdString(companyIdString);
		}

		if (forecastValueType == null) {
			vwCustomerGtsForecastImpl.setForecastValueType(StringPool.BLANK);
		}
		else {
			vwCustomerGtsForecastImpl.setForecastValueType(forecastValueType);
		}

		if (deductionCategory == null) {
			vwCustomerGtsForecastImpl.setDeductionCategory(StringPool.BLANK);
		}
		else {
			vwCustomerGtsForecastImpl.setDeductionCategory(deductionCategory);
		}

		if (adjustmentCode == null) {
			vwCustomerGtsForecastImpl.setAdjustmentCode(StringPool.BLANK);
		}
		else {
			vwCustomerGtsForecastImpl.setAdjustmentCode(adjustmentCode);
		}

		if (deductionProgramType == null) {
			vwCustomerGtsForecastImpl.setDeductionProgramType(StringPool.BLANK);
		}
		else {
			vwCustomerGtsForecastImpl.setDeductionProgramType(deductionProgramType);
		}

		vwCustomerGtsForecastImpl.setCustomerGtsForecastIntfId(customerGtsForecastIntfId);

		if (salesInclusion == null) {
			vwCustomerGtsForecastImpl.setSalesInclusion(StringPool.BLANK);
		}
		else {
			vwCustomerGtsForecastImpl.setSalesInclusion(salesInclusion);
		}

		if (forecastVer == null) {
			vwCustomerGtsForecastImpl.setForecastVer(StringPool.BLANK);
		}
		else {
			vwCustomerGtsForecastImpl.setForecastVer(forecastVer);
		}

		if (batchId == null) {
			vwCustomerGtsForecastImpl.setBatchId(StringPool.BLANK);
		}
		else {
			vwCustomerGtsForecastImpl.setBatchId(batchId);
		}

		if (priceType == null) {
			vwCustomerGtsForecastImpl.setPriceType(StringPool.BLANK);
		}
		else {
			vwCustomerGtsForecastImpl.setPriceType(priceType);
		}

		if (forecastMonth == null) {
			vwCustomerGtsForecastImpl.setForecastMonth(StringPool.BLANK);
		}
		else {
			vwCustomerGtsForecastImpl.setForecastMonth(forecastMonth);
		}

		if (deductionInclusion == null) {
			vwCustomerGtsForecastImpl.setDeductionInclusion(StringPool.BLANK);
		}
		else {
			vwCustomerGtsForecastImpl.setDeductionInclusion(deductionInclusion);
		}

		if (segment == null) {
			vwCustomerGtsForecastImpl.setSegment(StringPool.BLANK);
		}
		else {
			vwCustomerGtsForecastImpl.setSegment(segment);
		}

		if (brand == null) {
			vwCustomerGtsForecastImpl.setBrand(StringPool.BLANK);
		}
		else {
			vwCustomerGtsForecastImpl.setBrand(brand);
		}

		if (forecastName == null) {
			vwCustomerGtsForecastImpl.setForecastName(StringPool.BLANK);
		}
		else {
			vwCustomerGtsForecastImpl.setForecastName(forecastName);
		}

		vwCustomerGtsForecastImpl.resetOriginalValues();

		return vwCustomerGtsForecastImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		price = objectInput.readDouble();
		forecastYear = objectInput.readUTF();

		deductionAmount = objectInput.readDouble();
		deductionId = objectInput.readUTF();
		forecastDate = objectInput.readLong();
		itemId = objectInput.readUTF();
		modifiedDate = objectInput.readLong();
		source = objectInput.readUTF();
		createdBy = objectInput.readUTF();
		createdDate = objectInput.readLong();
		addChgDelIndicator = objectInput.readUTF();
		modifiedBy = objectInput.readUTF();

		salesAmount = objectInput.readDouble();
		udc6 = objectInput.readUTF();
		udc5 = objectInput.readUTF();
		deductionType = objectInput.readUTF();
		udc4 = objectInput.readUTF();

		units = objectInput.readDouble();

		deductionRate = objectInput.readDouble();
		udc1 = objectInput.readUTF();

		customerGtsForecastSid = objectInput.readInt();
		udc2 = objectInput.readUTF();
		udc3 = objectInput.readUTF();
		country = objectInput.readUTF();
		companyIdString = objectInput.readUTF();
		forecastValueType = objectInput.readUTF();
		deductionCategory = objectInput.readUTF();
		adjustmentCode = objectInput.readUTF();
		deductionProgramType = objectInput.readUTF();

		customerGtsForecastIntfId = objectInput.readInt();
		salesInclusion = objectInput.readUTF();
		forecastVer = objectInput.readUTF();
		batchId = objectInput.readUTF();
		priceType = objectInput.readUTF();
		forecastMonth = objectInput.readUTF();
		deductionInclusion = objectInput.readUTF();
		segment = objectInput.readUTF();
		brand = objectInput.readUTF();
		forecastName = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeDouble(price);

		if (forecastYear == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(forecastYear);
		}

		objectOutput.writeDouble(deductionAmount);

		if (deductionId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(deductionId);
		}

		objectOutput.writeLong(forecastDate);

		if (itemId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(itemId);
		}

		objectOutput.writeLong(modifiedDate);

		if (source == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(source);
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

		if (modifiedBy == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(modifiedBy);
		}

		objectOutput.writeDouble(salesAmount);

		if (udc6 == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(udc6);
		}

		if (udc5 == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(udc5);
		}

		if (deductionType == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(deductionType);
		}

		if (udc4 == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(udc4);
		}

		objectOutput.writeDouble(units);

		objectOutput.writeDouble(deductionRate);

		if (udc1 == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(udc1);
		}

		objectOutput.writeInt(customerGtsForecastSid);

		if (udc2 == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(udc2);
		}

		if (udc3 == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(udc3);
		}

		if (country == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(country);
		}

		if (companyIdString == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(companyIdString);
		}

		if (forecastValueType == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(forecastValueType);
		}

		if (deductionCategory == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(deductionCategory);
		}

		if (adjustmentCode == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(adjustmentCode);
		}

		if (deductionProgramType == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(deductionProgramType);
		}

		objectOutput.writeInt(customerGtsForecastIntfId);

		if (salesInclusion == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(salesInclusion);
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

		if (priceType == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(priceType);
		}

		if (forecastMonth == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(forecastMonth);
		}

		if (deductionInclusion == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(deductionInclusion);
		}

		if (segment == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(segment);
		}

		if (brand == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(brand);
		}

		if (forecastName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(forecastName);
		}
	}

	public double price;
	public String forecastYear;
	public double deductionAmount;
	public String deductionId;
	public long forecastDate;
	public String itemId;
	public long modifiedDate;
	public String source;
	public String createdBy;
	public long createdDate;
	public String addChgDelIndicator;
	public String modifiedBy;
	public double salesAmount;
	public String udc6;
	public String udc5;
	public String deductionType;
	public String udc4;
	public double units;
	public double deductionRate;
	public String udc1;
	public int customerGtsForecastSid;
	public String udc2;
	public String udc3;
	public String country;
	public String companyIdString;
	public String forecastValueType;
	public String deductionCategory;
	public String adjustmentCode;
	public String deductionProgramType;
	public int customerGtsForecastIntfId;
	public String salesInclusion;
	public String forecastVer;
	public String batchId;
	public String priceType;
	public String forecastMonth;
	public String deductionInclusion;
	public String segment;
	public String brand;
	public String forecastName;
}