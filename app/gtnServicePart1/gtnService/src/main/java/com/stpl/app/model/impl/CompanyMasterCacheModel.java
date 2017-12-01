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

import com.stpl.app.model.CompanyMaster;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing CompanyMaster in entity cache.
 *
 * @author
 * @see CompanyMaster
 * @generated
 */
@ProviderType
public class CompanyMasterCacheModel implements CacheModel<CompanyMaster>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CompanyMasterCacheModel)) {
			return false;
		}

		CompanyMasterCacheModel companyMasterCacheModel = (CompanyMasterCacheModel)obj;

		if (companyMasterSid == companyMasterCacheModel.companyMasterSid) {
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
		StringBundler sb = new StringBundler(61);

		sb.append("{state=");
		sb.append(state);
		sb.append(", financialSystem=");
		sb.append(financialSystem);
		sb.append(", companyGroup=");
		sb.append(companyGroup);
		sb.append(", companyName=");
		sb.append(companyName);
		sb.append(", companyCategory=");
		sb.append(companyCategory);
		sb.append(", lastUpdatedDate=");
		sb.append(lastUpdatedDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", lives=");
		sb.append(lives);
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
		sb.append(", modifiedBy=");
		sb.append(modifiedBy);
		sb.append(", inboundStatus=");
		sb.append(inboundStatus);
		sb.append(", companyMasterSid=");
		sb.append(companyMasterSid);
		sb.append(", zipCode=");
		sb.append(zipCode);
		sb.append(", companyStringId=");
		sb.append(companyStringId);
		sb.append(", country=");
		sb.append(country);
		sb.append(", internalNotes=");
		sb.append(internalNotes);
		sb.append(", orgKey=");
		sb.append(orgKey);
		sb.append(", companyType=");
		sb.append(companyType);
		sb.append(", recordLockStatus=");
		sb.append(recordLockStatus);
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
	public CompanyMaster toEntityModel() {
		CompanyMasterImpl companyMasterImpl = new CompanyMasterImpl();

		companyMasterImpl.setState(state);

		if (financialSystem == null) {
			companyMasterImpl.setFinancialSystem(StringPool.BLANK);
		}
		else {
			companyMasterImpl.setFinancialSystem(financialSystem);
		}

		if (companyGroup == null) {
			companyMasterImpl.setCompanyGroup(StringPool.BLANK);
		}
		else {
			companyMasterImpl.setCompanyGroup(companyGroup);
		}

		if (companyName == null) {
			companyMasterImpl.setCompanyName(StringPool.BLANK);
		}
		else {
			companyMasterImpl.setCompanyName(companyName);
		}

		companyMasterImpl.setCompanyCategory(companyCategory);

		if (lastUpdatedDate == Long.MIN_VALUE) {
			companyMasterImpl.setLastUpdatedDate(null);
		}
		else {
			companyMasterImpl.setLastUpdatedDate(new Date(lastUpdatedDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			companyMasterImpl.setModifiedDate(null);
		}
		else {
			companyMasterImpl.setModifiedDate(new Date(modifiedDate));
		}

		companyMasterImpl.setLives(lives);

		if (address2 == null) {
			companyMasterImpl.setAddress2(StringPool.BLANK);
		}
		else {
			companyMasterImpl.setAddress2(address2);
		}

		if (createdDate == Long.MIN_VALUE) {
			companyMasterImpl.setCreatedDate(null);
		}
		else {
			companyMasterImpl.setCreatedDate(new Date(createdDate));
		}

		companyMasterImpl.setCreatedBy(createdBy);

		if (source == null) {
			companyMasterImpl.setSource(StringPool.BLANK);
		}
		else {
			companyMasterImpl.setSource(source);
		}

		if (address1 == null) {
			companyMasterImpl.setAddress1(StringPool.BLANK);
		}
		else {
			companyMasterImpl.setAddress1(address1);
		}

		companyMasterImpl.setModifiedBy(modifiedBy);

		if (inboundStatus == null) {
			companyMasterImpl.setInboundStatus(StringPool.BLANK);
		}
		else {
			companyMasterImpl.setInboundStatus(inboundStatus);
		}

		companyMasterImpl.setCompanyMasterSid(companyMasterSid);

		if (zipCode == null) {
			companyMasterImpl.setZipCode(StringPool.BLANK);
		}
		else {
			companyMasterImpl.setZipCode(zipCode);
		}

		if (companyStringId == null) {
			companyMasterImpl.setCompanyStringId(StringPool.BLANK);
		}
		else {
			companyMasterImpl.setCompanyStringId(companyStringId);
		}

		companyMasterImpl.setCountry(country);

		if (internalNotes == null) {
			companyMasterImpl.setInternalNotes(StringPool.BLANK);
		}
		else {
			companyMasterImpl.setInternalNotes(internalNotes);
		}

		if (orgKey == null) {
			companyMasterImpl.setOrgKey(StringPool.BLANK);
		}
		else {
			companyMasterImpl.setOrgKey(orgKey);
		}

		companyMasterImpl.setCompanyType(companyType);
		companyMasterImpl.setRecordLockStatus(recordLockStatus);

		if (companyStartDate == Long.MIN_VALUE) {
			companyMasterImpl.setCompanyStartDate(null);
		}
		else {
			companyMasterImpl.setCompanyStartDate(new Date(companyStartDate));
		}

		if (companyNo == null) {
			companyMasterImpl.setCompanyNo(StringPool.BLANK);
		}
		else {
			companyMasterImpl.setCompanyNo(companyNo);
		}

		if (batchId == null) {
			companyMasterImpl.setBatchId(StringPool.BLANK);
		}
		else {
			companyMasterImpl.setBatchId(batchId);
		}

		companyMasterImpl.setCompanyStatus(companyStatus);

		if (companyEndDate == Long.MIN_VALUE) {
			companyMasterImpl.setCompanyEndDate(null);
		}
		else {
			companyMasterImpl.setCompanyEndDate(new Date(companyEndDate));
		}

		if (city == null) {
			companyMasterImpl.setCity(StringPool.BLANK);
		}
		else {
			companyMasterImpl.setCity(city);
		}

		if (regionCode == null) {
			companyMasterImpl.setRegionCode(StringPool.BLANK);
		}
		else {
			companyMasterImpl.setRegionCode(regionCode);
		}

		companyMasterImpl.resetOriginalValues();

		return companyMasterImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		state = objectInput.readInt();
		financialSystem = objectInput.readUTF();
		companyGroup = objectInput.readUTF();
		companyName = objectInput.readUTF();

		companyCategory = objectInput.readInt();
		lastUpdatedDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		lives = objectInput.readInt();
		address2 = objectInput.readUTF();
		createdDate = objectInput.readLong();

		createdBy = objectInput.readInt();
		source = objectInput.readUTF();
		address1 = objectInput.readUTF();

		modifiedBy = objectInput.readInt();
		inboundStatus = objectInput.readUTF();

		companyMasterSid = objectInput.readInt();
		zipCode = objectInput.readUTF();
		companyStringId = objectInput.readUTF();

		country = objectInput.readInt();
		internalNotes = objectInput.readUTF();
		orgKey = objectInput.readUTF();

		companyType = objectInput.readInt();

		recordLockStatus = objectInput.readBoolean();
		companyStartDate = objectInput.readLong();
		companyNo = objectInput.readUTF();
		batchId = objectInput.readUTF();

		companyStatus = objectInput.readInt();
		companyEndDate = objectInput.readLong();
		city = objectInput.readUTF();
		regionCode = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeInt(state);

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

		objectOutput.writeInt(companyCategory);
		objectOutput.writeLong(lastUpdatedDate);
		objectOutput.writeLong(modifiedDate);

		objectOutput.writeInt(lives);

		if (address2 == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(address2);
		}

		objectOutput.writeLong(createdDate);

		objectOutput.writeInt(createdBy);

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

		objectOutput.writeInt(modifiedBy);

		if (inboundStatus == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(inboundStatus);
		}

		objectOutput.writeInt(companyMasterSid);

		if (zipCode == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(zipCode);
		}

		if (companyStringId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(companyStringId);
		}

		objectOutput.writeInt(country);

		if (internalNotes == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(internalNotes);
		}

		if (orgKey == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(orgKey);
		}

		objectOutput.writeInt(companyType);

		objectOutput.writeBoolean(recordLockStatus);
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

		objectOutput.writeInt(companyStatus);
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

	public int state;
	public String financialSystem;
	public String companyGroup;
	public String companyName;
	public int companyCategory;
	public long lastUpdatedDate;
	public long modifiedDate;
	public int lives;
	public String address2;
	public long createdDate;
	public int createdBy;
	public String source;
	public String address1;
	public int modifiedBy;
	public String inboundStatus;
	public int companyMasterSid;
	public String zipCode;
	public String companyStringId;
	public int country;
	public String internalNotes;
	public String orgKey;
	public int companyType;
	public boolean recordLockStatus;
	public long companyStartDate;
	public String companyNo;
	public String batchId;
	public int companyStatus;
	public long companyEndDate;
	public String city;
	public String regionCode;
}