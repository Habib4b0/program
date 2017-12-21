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

import com.stpl.app.parttwo.model.VwCompanyMaster;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing VwCompanyMaster in entity cache.
 *
 * @author
 * @see VwCompanyMaster
 * @generated
 */
@ProviderType
public class VwCompanyMasterCacheModel implements CacheModel<VwCompanyMaster>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof VwCompanyMasterCacheModel)) {
			return false;
		}

		VwCompanyMasterCacheModel vwCompanyMasterCacheModel = (VwCompanyMasterCacheModel)obj;

		if (companyMasterSid == vwCompanyMasterCacheModel.companyMasterSid) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, companyMasterSid);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(69);

		sb.append("{state=");
		sb.append(state);
		sb.append(", financialSystem=");
		sb.append(financialSystem);
		sb.append(", companyGroup=");
		sb.append(companyGroup);
		sb.append(", companyName=");
		sb.append(companyName);
		sb.append(", lastUpdatedDate=");
		sb.append(lastUpdatedDate);
		sb.append(", companyCategory=");
		sb.append(companyCategory);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", lives=");
		sb.append(lives);
		sb.append(", organizationKey=");
		sb.append(organizationKey);
		sb.append(", address2=");
		sb.append(address2);
		sb.append(", createdDate=");
		sb.append(createdDate);
		sb.append(", createdBy=");
		sb.append(createdBy);
		sb.append(", source=");
		sb.append(source);
		sb.append(", address1=");
		sb.append(address1);
		sb.append(", addChgDelIndicator=");
		sb.append(addChgDelIndicator);
		sb.append(", modifiedBy=");
		sb.append(modifiedBy);
		sb.append(", udc6=");
		sb.append(udc6);
		sb.append(", udc5=");
		sb.append(udc5);
		sb.append(", companyMasterSid=");
		sb.append(companyMasterSid);
		sb.append(", udc4=");
		sb.append(udc4);
		sb.append(", udc1=");
		sb.append(udc1);
		sb.append(", zipCode=");
		sb.append(zipCode);
		sb.append(", udc2=");
		sb.append(udc2);
		sb.append(", udc3=");
		sb.append(udc3);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", country=");
		sb.append(country);
		sb.append(", companyType=");
		sb.append(companyType);
		sb.append(", companyStartDate=");
		sb.append(companyStartDate);
		sb.append(", companyNo=");
		sb.append(companyNo);
		sb.append(", batchId=");
		sb.append(batchId);
		sb.append(", companyStatus=");
		sb.append(companyStatus);
		sb.append(", companyEndDate=");
		sb.append(companyEndDate);
		sb.append(", city=");
		sb.append(city);
		sb.append(", regionCode=");
		sb.append(regionCode);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public VwCompanyMaster toEntityModel() {
		VwCompanyMasterImpl vwCompanyMasterImpl = new VwCompanyMasterImpl();

		if (state == null) {
			vwCompanyMasterImpl.setState(StringPool.BLANK);
		}
		else {
			vwCompanyMasterImpl.setState(state);
		}

		if (financialSystem == null) {
			vwCompanyMasterImpl.setFinancialSystem(StringPool.BLANK);
		}
		else {
			vwCompanyMasterImpl.setFinancialSystem(financialSystem);
		}

		if (companyGroup == null) {
			vwCompanyMasterImpl.setCompanyGroup(StringPool.BLANK);
		}
		else {
			vwCompanyMasterImpl.setCompanyGroup(companyGroup);
		}

		if (companyName == null) {
			vwCompanyMasterImpl.setCompanyName(StringPool.BLANK);
		}
		else {
			vwCompanyMasterImpl.setCompanyName(companyName);
		}

		if (lastUpdatedDate == Long.MIN_VALUE) {
			vwCompanyMasterImpl.setLastUpdatedDate(null);
		}
		else {
			vwCompanyMasterImpl.setLastUpdatedDate(new Date(lastUpdatedDate));
		}

		if (companyCategory == null) {
			vwCompanyMasterImpl.setCompanyCategory(StringPool.BLANK);
		}
		else {
			vwCompanyMasterImpl.setCompanyCategory(companyCategory);
		}

		if (modifiedDate == Long.MIN_VALUE) {
			vwCompanyMasterImpl.setModifiedDate(null);
		}
		else {
			vwCompanyMasterImpl.setModifiedDate(new Date(modifiedDate));
		}

		vwCompanyMasterImpl.setLives(lives);

		if (organizationKey == null) {
			vwCompanyMasterImpl.setOrganizationKey(StringPool.BLANK);
		}
		else {
			vwCompanyMasterImpl.setOrganizationKey(organizationKey);
		}

		if (address2 == null) {
			vwCompanyMasterImpl.setAddress2(StringPool.BLANK);
		}
		else {
			vwCompanyMasterImpl.setAddress2(address2);
		}

		if (createdDate == Long.MIN_VALUE) {
			vwCompanyMasterImpl.setCreatedDate(null);
		}
		else {
			vwCompanyMasterImpl.setCreatedDate(new Date(createdDate));
		}

		if (createdBy == null) {
			vwCompanyMasterImpl.setCreatedBy(StringPool.BLANK);
		}
		else {
			vwCompanyMasterImpl.setCreatedBy(createdBy);
		}

		if (source == null) {
			vwCompanyMasterImpl.setSource(StringPool.BLANK);
		}
		else {
			vwCompanyMasterImpl.setSource(source);
		}

		if (address1 == null) {
			vwCompanyMasterImpl.setAddress1(StringPool.BLANK);
		}
		else {
			vwCompanyMasterImpl.setAddress1(address1);
		}

		if (addChgDelIndicator == null) {
			vwCompanyMasterImpl.setAddChgDelIndicator(StringPool.BLANK);
		}
		else {
			vwCompanyMasterImpl.setAddChgDelIndicator(addChgDelIndicator);
		}

		if (modifiedBy == null) {
			vwCompanyMasterImpl.setModifiedBy(StringPool.BLANK);
		}
		else {
			vwCompanyMasterImpl.setModifiedBy(modifiedBy);
		}

		if (udc6 == null) {
			vwCompanyMasterImpl.setUdc6(StringPool.BLANK);
		}
		else {
			vwCompanyMasterImpl.setUdc6(udc6);
		}

		if (udc5 == null) {
			vwCompanyMasterImpl.setUdc5(StringPool.BLANK);
		}
		else {
			vwCompanyMasterImpl.setUdc5(udc5);
		}

		vwCompanyMasterImpl.setCompanyMasterSid(companyMasterSid);

		if (udc4 == null) {
			vwCompanyMasterImpl.setUdc4(StringPool.BLANK);
		}
		else {
			vwCompanyMasterImpl.setUdc4(udc4);
		}

		if (udc1 == null) {
			vwCompanyMasterImpl.setUdc1(StringPool.BLANK);
		}
		else {
			vwCompanyMasterImpl.setUdc1(udc1);
		}

		if (zipCode == null) {
			vwCompanyMasterImpl.setZipCode(StringPool.BLANK);
		}
		else {
			vwCompanyMasterImpl.setZipCode(zipCode);
		}

		if (udc2 == null) {
			vwCompanyMasterImpl.setUdc2(StringPool.BLANK);
		}
		else {
			vwCompanyMasterImpl.setUdc2(udc2);
		}

		if (udc3 == null) {
			vwCompanyMasterImpl.setUdc3(StringPool.BLANK);
		}
		else {
			vwCompanyMasterImpl.setUdc3(udc3);
		}

		if (companyId == null) {
			vwCompanyMasterImpl.setCompanyId(StringPool.BLANK);
		}
		else {
			vwCompanyMasterImpl.setCompanyId(companyId);
		}

		if (country == null) {
			vwCompanyMasterImpl.setCountry(StringPool.BLANK);
		}
		else {
			vwCompanyMasterImpl.setCountry(country);
		}

		if (companyType == null) {
			vwCompanyMasterImpl.setCompanyType(StringPool.BLANK);
		}
		else {
			vwCompanyMasterImpl.setCompanyType(companyType);
		}

		if (companyStartDate == Long.MIN_VALUE) {
			vwCompanyMasterImpl.setCompanyStartDate(null);
		}
		else {
			vwCompanyMasterImpl.setCompanyStartDate(new Date(companyStartDate));
		}

		if (companyNo == null) {
			vwCompanyMasterImpl.setCompanyNo(StringPool.BLANK);
		}
		else {
			vwCompanyMasterImpl.setCompanyNo(companyNo);
		}

		if (batchId == null) {
			vwCompanyMasterImpl.setBatchId(StringPool.BLANK);
		}
		else {
			vwCompanyMasterImpl.setBatchId(batchId);
		}

		if (companyStatus == null) {
			vwCompanyMasterImpl.setCompanyStatus(StringPool.BLANK);
		}
		else {
			vwCompanyMasterImpl.setCompanyStatus(companyStatus);
		}

		if (companyEndDate == Long.MIN_VALUE) {
			vwCompanyMasterImpl.setCompanyEndDate(null);
		}
		else {
			vwCompanyMasterImpl.setCompanyEndDate(new Date(companyEndDate));
		}

		if (city == null) {
			vwCompanyMasterImpl.setCity(StringPool.BLANK);
		}
		else {
			vwCompanyMasterImpl.setCity(city);
		}

		if (regionCode == null) {
			vwCompanyMasterImpl.setRegionCode(StringPool.BLANK);
		}
		else {
			vwCompanyMasterImpl.setRegionCode(regionCode);
		}

		vwCompanyMasterImpl.resetOriginalValues();

		return vwCompanyMasterImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		state = objectInput.readUTF();
		financialSystem = objectInput.readUTF();
		companyGroup = objectInput.readUTF();
		companyName = objectInput.readUTF();
		lastUpdatedDate = objectInput.readLong();
		companyCategory = objectInput.readUTF();
		modifiedDate = objectInput.readLong();

		lives = objectInput.readInt();
		organizationKey = objectInput.readUTF();
		address2 = objectInput.readUTF();
		createdDate = objectInput.readLong();
		createdBy = objectInput.readUTF();
		source = objectInput.readUTF();
		address1 = objectInput.readUTF();
		addChgDelIndicator = objectInput.readUTF();
		modifiedBy = objectInput.readUTF();
		udc6 = objectInput.readUTF();
		udc5 = objectInput.readUTF();

		companyMasterSid = objectInput.readInt();
		udc4 = objectInput.readUTF();
		udc1 = objectInput.readUTF();
		zipCode = objectInput.readUTF();
		udc2 = objectInput.readUTF();
		udc3 = objectInput.readUTF();
		companyId = objectInput.readUTF();
		country = objectInput.readUTF();
		companyType = objectInput.readUTF();
		companyStartDate = objectInput.readLong();
		companyNo = objectInput.readUTF();
		batchId = objectInput.readUTF();
		companyStatus = objectInput.readUTF();
		companyEndDate = objectInput.readLong();
		city = objectInput.readUTF();
		regionCode = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		if (state == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(state);
		}

		if (financialSystem == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(financialSystem);
		}

		if (companyGroup == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(companyGroup);
		}

		if (companyName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(companyName);
		}

		objectOutput.writeLong(lastUpdatedDate);

		if (companyCategory == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(companyCategory);
		}

		objectOutput.writeLong(modifiedDate);

		objectOutput.writeInt(lives);

		if (organizationKey == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(organizationKey);
		}

		if (address2 == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(address2);
		}

		objectOutput.writeLong(createdDate);

		if (createdBy == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(createdBy);
		}

		if (source == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(source);
		}

		if (address1 == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(address1);
		}

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

		objectOutput.writeInt(companyMasterSid);

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

		if (zipCode == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(zipCode);
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

		if (companyId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(companyId);
		}

		if (country == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(country);
		}

		if (companyType == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(companyType);
		}

		objectOutput.writeLong(companyStartDate);

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

		if (companyStatus == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(companyStatus);
		}

		objectOutput.writeLong(companyEndDate);

		if (city == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(city);
		}

		if (regionCode == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(regionCode);
		}
	}

	public String state;
	public String financialSystem;
	public String companyGroup;
	public String companyName;
	public long lastUpdatedDate;
	public String companyCategory;
	public long modifiedDate;
	public int lives;
	public String organizationKey;
	public String address2;
	public long createdDate;
	public String createdBy;
	public String source;
	public String address1;
	public String addChgDelIndicator;
	public String modifiedBy;
	public String udc6;
	public String udc5;
	public int companyMasterSid;
	public String udc4;
	public String udc1;
	public String zipCode;
	public String udc2;
	public String udc3;
	public String companyId;
	public String country;
	public String companyType;
	public long companyStartDate;
	public String companyNo;
	public String batchId;
	public String companyStatus;
	public long companyEndDate;
	public String city;
	public String regionCode;
}