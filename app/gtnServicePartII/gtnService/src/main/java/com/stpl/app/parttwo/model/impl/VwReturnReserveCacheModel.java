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

import com.stpl.app.parttwo.model.VwReturnReserve;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing VwReturnReserve in entity cache.
 *
 * @author
 * @see VwReturnReserve
 * @generated
 */
@ProviderType
public class VwReturnReserveCacheModel implements CacheModel<VwReturnReserve>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof VwReturnReserveCacheModel)) {
			return false;
		}

		VwReturnReserveCacheModel vwReturnReserveCacheModel = (VwReturnReserveCacheModel)obj;

		if (returnReserveSid == vwReturnReserveCacheModel.returnReserveSid) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, returnReserveSid);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(85);

		sb.append("{itemMasterSid=");
		sb.append(itemMasterSid);
		sb.append(", companyName=");
		sb.append(companyName);
		sb.append(", project=");
		sb.append(project);
		sb.append(", year=");
		sb.append(year);
		sb.append(", itemId=");
		sb.append(itemId);
		sb.append(", brandName=");
		sb.append(brandName);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", brandMasterSid=");
		sb.append(brandMasterSid);
		sb.append(", account=");
		sb.append(account);
		sb.append(", returnReserveIntfId=");
		sb.append(returnReserveIntfId);
		sb.append(", glCompanyMasterSid=");
		sb.append(glCompanyMasterSid);
		sb.append(", source=");
		sb.append(source);
		sb.append(", createdDate=");
		sb.append(createdDate);
		sb.append(", createdBy=");
		sb.append(createdBy);
		sb.append(", businessUnit=");
		sb.append(businessUnit);
		sb.append(", businessUnitName=");
		sb.append(businessUnitName);
		sb.append(", buCompanyMasterSid=");
		sb.append(buCompanyMasterSid);
		sb.append(", inboundStatus=");
		sb.append(inboundStatus);
		sb.append(", modifiedBy=");
		sb.append(modifiedBy);
		sb.append(", itemNo=");
		sb.append(itemNo);
		sb.append(", month=");
		sb.append(month);
		sb.append(", udc6=");
		sb.append(udc6);
		sb.append(", udc5=");
		sb.append(udc5);
		sb.append(", udc4=");
		sb.append(udc4);
		sb.append(", udc1=");
		sb.append(udc1);
		sb.append(", units=");
		sb.append(units);
		sb.append(", udc2=");
		sb.append(udc2);
		sb.append(", udc3=");
		sb.append(udc3);
		sb.append(", country=");
		sb.append(country);
		sb.append(", companyIdString=");
		sb.append(companyIdString);
		sb.append(", costCenter=");
		sb.append(costCenter);
		sb.append(", glCompany=");
		sb.append(glCompany);
		sb.append(", brandId=");
		sb.append(brandId);
		sb.append(", future1=");
		sb.append(future1);
		sb.append(", future2=");
		sb.append(future2);
		sb.append(", amount=");
		sb.append(amount);
		sb.append(", recordLockStatus=");
		sb.append(recordLockStatus);
		sb.append(", division=");
		sb.append(division);
		sb.append(", returnReserveSid=");
		sb.append(returnReserveSid);
		sb.append(", companyNo=");
		sb.append(companyNo);
		sb.append(", batchId=");
		sb.append(batchId);
		sb.append(", itemName=");
		sb.append(itemName);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public VwReturnReserve toEntityModel() {
		VwReturnReserveImpl vwReturnReserveImpl = new VwReturnReserveImpl();

		vwReturnReserveImpl.setItemMasterSid(itemMasterSid);

		if (companyName == null) {
			vwReturnReserveImpl.setCompanyName(StringPool.BLANK);
		}
		else {
			vwReturnReserveImpl.setCompanyName(companyName);
		}

		if (project == null) {
			vwReturnReserveImpl.setProject(StringPool.BLANK);
		}
		else {
			vwReturnReserveImpl.setProject(project);
		}

		if (year == null) {
			vwReturnReserveImpl.setYear(StringPool.BLANK);
		}
		else {
			vwReturnReserveImpl.setYear(year);
		}

		if (itemId == null) {
			vwReturnReserveImpl.setItemId(StringPool.BLANK);
		}
		else {
			vwReturnReserveImpl.setItemId(itemId);
		}

		if (brandName == null) {
			vwReturnReserveImpl.setBrandName(StringPool.BLANK);
		}
		else {
			vwReturnReserveImpl.setBrandName(brandName);
		}

		if (modifiedDate == Long.MIN_VALUE) {
			vwReturnReserveImpl.setModifiedDate(null);
		}
		else {
			vwReturnReserveImpl.setModifiedDate(new Date(modifiedDate));
		}

		vwReturnReserveImpl.setBrandMasterSid(brandMasterSid);

		if (account == null) {
			vwReturnReserveImpl.setAccount(StringPool.BLANK);
		}
		else {
			vwReturnReserveImpl.setAccount(account);
		}

		vwReturnReserveImpl.setReturnReserveIntfId(returnReserveIntfId);
		vwReturnReserveImpl.setGlCompanyMasterSid(glCompanyMasterSid);

		if (source == null) {
			vwReturnReserveImpl.setSource(StringPool.BLANK);
		}
		else {
			vwReturnReserveImpl.setSource(source);
		}

		if (createdDate == Long.MIN_VALUE) {
			vwReturnReserveImpl.setCreatedDate(null);
		}
		else {
			vwReturnReserveImpl.setCreatedDate(new Date(createdDate));
		}

		if (createdBy == null) {
			vwReturnReserveImpl.setCreatedBy(StringPool.BLANK);
		}
		else {
			vwReturnReserveImpl.setCreatedBy(createdBy);
		}

		if (businessUnit == null) {
			vwReturnReserveImpl.setBusinessUnit(StringPool.BLANK);
		}
		else {
			vwReturnReserveImpl.setBusinessUnit(businessUnit);
		}

		if (businessUnitName == null) {
			vwReturnReserveImpl.setBusinessUnitName(StringPool.BLANK);
		}
		else {
			vwReturnReserveImpl.setBusinessUnitName(businessUnitName);
		}

		vwReturnReserveImpl.setBuCompanyMasterSid(buCompanyMasterSid);

		if (inboundStatus == null) {
			vwReturnReserveImpl.setInboundStatus(StringPool.BLANK);
		}
		else {
			vwReturnReserveImpl.setInboundStatus(inboundStatus);
		}

		if (modifiedBy == null) {
			vwReturnReserveImpl.setModifiedBy(StringPool.BLANK);
		}
		else {
			vwReturnReserveImpl.setModifiedBy(modifiedBy);
		}

		if (itemNo == null) {
			vwReturnReserveImpl.setItemNo(StringPool.BLANK);
		}
		else {
			vwReturnReserveImpl.setItemNo(itemNo);
		}

		if (month == null) {
			vwReturnReserveImpl.setMonth(StringPool.BLANK);
		}
		else {
			vwReturnReserveImpl.setMonth(month);
		}

		if (udc6 == null) {
			vwReturnReserveImpl.setUdc6(StringPool.BLANK);
		}
		else {
			vwReturnReserveImpl.setUdc6(udc6);
		}

		if (udc5 == null) {
			vwReturnReserveImpl.setUdc5(StringPool.BLANK);
		}
		else {
			vwReturnReserveImpl.setUdc5(udc5);
		}

		if (udc4 == null) {
			vwReturnReserveImpl.setUdc4(StringPool.BLANK);
		}
		else {
			vwReturnReserveImpl.setUdc4(udc4);
		}

		if (udc1 == null) {
			vwReturnReserveImpl.setUdc1(StringPool.BLANK);
		}
		else {
			vwReturnReserveImpl.setUdc1(udc1);
		}

		if (units == null) {
			vwReturnReserveImpl.setUnits(StringPool.BLANK);
		}
		else {
			vwReturnReserveImpl.setUnits(units);
		}

		if (udc2 == null) {
			vwReturnReserveImpl.setUdc2(StringPool.BLANK);
		}
		else {
			vwReturnReserveImpl.setUdc2(udc2);
		}

		if (udc3 == null) {
			vwReturnReserveImpl.setUdc3(StringPool.BLANK);
		}
		else {
			vwReturnReserveImpl.setUdc3(udc3);
		}

		if (country == null) {
			vwReturnReserveImpl.setCountry(StringPool.BLANK);
		}
		else {
			vwReturnReserveImpl.setCountry(country);
		}

		if (companyIdString == null) {
			vwReturnReserveImpl.setCompanyIdString(StringPool.BLANK);
		}
		else {
			vwReturnReserveImpl.setCompanyIdString(companyIdString);
		}

		if (costCenter == null) {
			vwReturnReserveImpl.setCostCenter(StringPool.BLANK);
		}
		else {
			vwReturnReserveImpl.setCostCenter(costCenter);
		}

		if (glCompany == null) {
			vwReturnReserveImpl.setGlCompany(StringPool.BLANK);
		}
		else {
			vwReturnReserveImpl.setGlCompany(glCompany);
		}

		if (brandId == null) {
			vwReturnReserveImpl.setBrandId(StringPool.BLANK);
		}
		else {
			vwReturnReserveImpl.setBrandId(brandId);
		}

		if (future1 == null) {
			vwReturnReserveImpl.setFuture1(StringPool.BLANK);
		}
		else {
			vwReturnReserveImpl.setFuture1(future1);
		}

		if (future2 == null) {
			vwReturnReserveImpl.setFuture2(StringPool.BLANK);
		}
		else {
			vwReturnReserveImpl.setFuture2(future2);
		}

		if (amount == null) {
			vwReturnReserveImpl.setAmount(StringPool.BLANK);
		}
		else {
			vwReturnReserveImpl.setAmount(amount);
		}

		vwReturnReserveImpl.setRecordLockStatus(recordLockStatus);

		if (division == null) {
			vwReturnReserveImpl.setDivision(StringPool.BLANK);
		}
		else {
			vwReturnReserveImpl.setDivision(division);
		}

		vwReturnReserveImpl.setReturnReserveSid(returnReserveSid);

		if (companyNo == null) {
			vwReturnReserveImpl.setCompanyNo(StringPool.BLANK);
		}
		else {
			vwReturnReserveImpl.setCompanyNo(companyNo);
		}

		if (batchId == null) {
			vwReturnReserveImpl.setBatchId(StringPool.BLANK);
		}
		else {
			vwReturnReserveImpl.setBatchId(batchId);
		}

		if (itemName == null) {
			vwReturnReserveImpl.setItemName(StringPool.BLANK);
		}
		else {
			vwReturnReserveImpl.setItemName(itemName);
		}

		vwReturnReserveImpl.resetOriginalValues();

		return vwReturnReserveImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		itemMasterSid = objectInput.readInt();
		companyName = objectInput.readUTF();
		project = objectInput.readUTF();
		year = objectInput.readUTF();
		itemId = objectInput.readUTF();
		brandName = objectInput.readUTF();
		modifiedDate = objectInput.readLong();

		brandMasterSid = objectInput.readInt();
		account = objectInput.readUTF();

		returnReserveIntfId = objectInput.readInt();

		glCompanyMasterSid = objectInput.readInt();
		source = objectInput.readUTF();
		createdDate = objectInput.readLong();
		createdBy = objectInput.readUTF();
		businessUnit = objectInput.readUTF();
		businessUnitName = objectInput.readUTF();

		buCompanyMasterSid = objectInput.readInt();
		inboundStatus = objectInput.readUTF();
		modifiedBy = objectInput.readUTF();
		itemNo = objectInput.readUTF();
		month = objectInput.readUTF();
		udc6 = objectInput.readUTF();
		udc5 = objectInput.readUTF();
		udc4 = objectInput.readUTF();
		udc1 = objectInput.readUTF();
		units = objectInput.readUTF();
		udc2 = objectInput.readUTF();
		udc3 = objectInput.readUTF();
		country = objectInput.readUTF();
		companyIdString = objectInput.readUTF();
		costCenter = objectInput.readUTF();
		glCompany = objectInput.readUTF();
		brandId = objectInput.readUTF();
		future1 = objectInput.readUTF();
		future2 = objectInput.readUTF();
		amount = objectInput.readUTF();

		recordLockStatus = objectInput.readBoolean();
		division = objectInput.readUTF();

		returnReserveSid = objectInput.readInt();
		companyNo = objectInput.readUTF();
		batchId = objectInput.readUTF();
		itemName = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeInt(itemMasterSid);

		if (companyName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(companyName);
		}

		if (project == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(project);
		}

		if (year == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(year);
		}

		if (itemId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(itemId);
		}

		if (brandName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(brandName);
		}

		objectOutput.writeLong(modifiedDate);

		objectOutput.writeInt(brandMasterSid);

		if (account == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(account);
		}

		objectOutput.writeInt(returnReserveIntfId);

		objectOutput.writeInt(glCompanyMasterSid);

		if (source == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(source);
		}

		objectOutput.writeLong(createdDate);

		if (createdBy == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(createdBy);
		}

		if (businessUnit == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(businessUnit);
		}

		if (businessUnitName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(businessUnitName);
		}

		objectOutput.writeInt(buCompanyMasterSid);

		if (inboundStatus == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(inboundStatus);
		}

		if (modifiedBy == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(modifiedBy);
		}

		if (itemNo == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(itemNo);
		}

		if (month == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(month);
		}

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

		if (udc4 == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(udc4);
		}

		if (udc1 == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(udc1);
		}

		if (units == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(units);
		}

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

		if (costCenter == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(costCenter);
		}

		if (glCompany == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(glCompany);
		}

		if (brandId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(brandId);
		}

		if (future1 == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(future1);
		}

		if (future2 == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(future2);
		}

		if (amount == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(amount);
		}

		objectOutput.writeBoolean(recordLockStatus);

		if (division == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(division);
		}

		objectOutput.writeInt(returnReserveSid);

		if (companyNo == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(companyNo);
		}

		if (batchId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(batchId);
		}

		if (itemName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(itemName);
		}
	}

	public int itemMasterSid;
	public String companyName;
	public String project;
	public String year;
	public String itemId;
	public String brandName;
	public long modifiedDate;
	public int brandMasterSid;
	public String account;
	public int returnReserveIntfId;
	public int glCompanyMasterSid;
	public String source;
	public long createdDate;
	public String createdBy;
	public String businessUnit;
	public String businessUnitName;
	public int buCompanyMasterSid;
	public String inboundStatus;
	public String modifiedBy;
	public String itemNo;
	public String month;
	public String udc6;
	public String udc5;
	public String udc4;
	public String udc1;
	public String units;
	public String udc2;
	public String udc3;
	public String country;
	public String companyIdString;
	public String costCenter;
	public String glCompany;
	public String brandId;
	public String future1;
	public String future2;
	public String amount;
	public boolean recordLockStatus;
	public String division;
	public int returnReserveSid;
	public String companyNo;
	public String batchId;
	public String itemName;
}