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

import com.stpl.app.parttwo.model.IvldCompanyMaster;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing IvldCompanyMaster in entity cache.
 *
 * @author
 * @see IvldCompanyMaster
 * @generated
 */
@ProviderType
public class IvldCompanyMasterCacheModel implements CacheModel<IvldCompanyMaster>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof IvldCompanyMasterCacheModel)) {
			return false;
		}

		IvldCompanyMasterCacheModel ivldCompanyMasterCacheModel = (IvldCompanyMasterCacheModel)obj;

		if (ivldCompanyMasterSid == ivldCompanyMasterCacheModel.ivldCompanyMasterSid) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, ivldCompanyMasterSid);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(85);

		sb.append("{state=");
		sb.append(state);
		sb.append(", financialSystem=");
		sb.append(financialSystem);
		sb.append(", companyName=");
		sb.append(companyName);
		sb.append(", companyGroup=");
		sb.append(companyGroup);
		sb.append(", companyCategory=");
		sb.append(companyCategory);
		sb.append(", lastUpdatedDate=");
		sb.append(lastUpdatedDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", status=");
		sb.append(status);
		sb.append(", ivldCompanyMasterSid=");
		sb.append(ivldCompanyMasterSid);
		sb.append(", lives=");
		sb.append(lives);
		sb.append(", organizationKey=");
		sb.append(organizationKey);
		sb.append(", source=");
		sb.append(source);
		sb.append(", createdDate=");
		sb.append(createdDate);
		sb.append(", createdBy=");
		sb.append(createdBy);
		sb.append(", addChgDelIndicator=");
		sb.append(addChgDelIndicator);
		sb.append(", errorCode=");
		sb.append(errorCode);
		sb.append(", intfInsertedDate=");
		sb.append(intfInsertedDate);
		sb.append(", modifiedBy=");
		sb.append(modifiedBy);
		sb.append(", companyMasterIntfid=");
		sb.append(companyMasterIntfid);
		sb.append(", reprocessedFlag=");
		sb.append(reprocessedFlag);
		sb.append(", udc6=");
		sb.append(udc6);
		sb.append(", udc5=");
		sb.append(udc5);
		sb.append(", udc4=");
		sb.append(udc4);
		sb.append(", udc1=");
		sb.append(udc1);
		sb.append(", udc2=");
		sb.append(udc2);
		sb.append(", zipCode=");
		sb.append(zipCode);
		sb.append(", udc3=");
		sb.append(udc3);
		sb.append(", reasonForFailure=");
		sb.append(reasonForFailure);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", address1=");
		sb.append(address1);
		sb.append(", country=");
		sb.append(country);
		sb.append(", address2=");
		sb.append(address2);
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
		sb.append(", errorField=");
		sb.append(errorField);
		sb.append(", city=");
		sb.append(city);
		sb.append(", regionCode=");
		sb.append(regionCode);
		sb.append(", checkRecord=");
		sb.append(checkRecord);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public IvldCompanyMaster toEntityModel() {
		IvldCompanyMasterImpl ivldCompanyMasterImpl = new IvldCompanyMasterImpl();

		if (state == null) {
			ivldCompanyMasterImpl.setState(StringPool.BLANK);
		}
		else {
			ivldCompanyMasterImpl.setState(state);
		}

		if (financialSystem == null) {
			ivldCompanyMasterImpl.setFinancialSystem(StringPool.BLANK);
		}
		else {
			ivldCompanyMasterImpl.setFinancialSystem(financialSystem);
		}

		if (companyName == null) {
			ivldCompanyMasterImpl.setCompanyName(StringPool.BLANK);
		}
		else {
			ivldCompanyMasterImpl.setCompanyName(companyName);
		}

		if (companyGroup == null) {
			ivldCompanyMasterImpl.setCompanyGroup(StringPool.BLANK);
		}
		else {
			ivldCompanyMasterImpl.setCompanyGroup(companyGroup);
		}

		if (companyCategory == null) {
			ivldCompanyMasterImpl.setCompanyCategory(StringPool.BLANK);
		}
		else {
			ivldCompanyMasterImpl.setCompanyCategory(companyCategory);
		}

		if (lastUpdatedDate == null) {
			ivldCompanyMasterImpl.setLastUpdatedDate(StringPool.BLANK);
		}
		else {
			ivldCompanyMasterImpl.setLastUpdatedDate(lastUpdatedDate);
		}

		if (modifiedDate == Long.MIN_VALUE) {
			ivldCompanyMasterImpl.setModifiedDate(null);
		}
		else {
			ivldCompanyMasterImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (status == null) {
			ivldCompanyMasterImpl.setStatus(StringPool.BLANK);
		}
		else {
			ivldCompanyMasterImpl.setStatus(status);
		}

		ivldCompanyMasterImpl.setIvldCompanyMasterSid(ivldCompanyMasterSid);

		if (lives == null) {
			ivldCompanyMasterImpl.setLives(StringPool.BLANK);
		}
		else {
			ivldCompanyMasterImpl.setLives(lives);
		}

		if (organizationKey == null) {
			ivldCompanyMasterImpl.setOrganizationKey(StringPool.BLANK);
		}
		else {
			ivldCompanyMasterImpl.setOrganizationKey(organizationKey);
		}

		if (source == null) {
			ivldCompanyMasterImpl.setSource(StringPool.BLANK);
		}
		else {
			ivldCompanyMasterImpl.setSource(source);
		}

		if (createdDate == Long.MIN_VALUE) {
			ivldCompanyMasterImpl.setCreatedDate(null);
		}
		else {
			ivldCompanyMasterImpl.setCreatedDate(new Date(createdDate));
		}

		if (createdBy == null) {
			ivldCompanyMasterImpl.setCreatedBy(StringPool.BLANK);
		}
		else {
			ivldCompanyMasterImpl.setCreatedBy(createdBy);
		}

		if (addChgDelIndicator == null) {
			ivldCompanyMasterImpl.setAddChgDelIndicator(StringPool.BLANK);
		}
		else {
			ivldCompanyMasterImpl.setAddChgDelIndicator(addChgDelIndicator);
		}

		if (errorCode == null) {
			ivldCompanyMasterImpl.setErrorCode(StringPool.BLANK);
		}
		else {
			ivldCompanyMasterImpl.setErrorCode(errorCode);
		}

		if (intfInsertedDate == Long.MIN_VALUE) {
			ivldCompanyMasterImpl.setIntfInsertedDate(null);
		}
		else {
			ivldCompanyMasterImpl.setIntfInsertedDate(new Date(intfInsertedDate));
		}

		if (modifiedBy == null) {
			ivldCompanyMasterImpl.setModifiedBy(StringPool.BLANK);
		}
		else {
			ivldCompanyMasterImpl.setModifiedBy(modifiedBy);
		}

		if (companyMasterIntfid == null) {
			ivldCompanyMasterImpl.setCompanyMasterIntfid(StringPool.BLANK);
		}
		else {
			ivldCompanyMasterImpl.setCompanyMasterIntfid(companyMasterIntfid);
		}

		if (reprocessedFlag == null) {
			ivldCompanyMasterImpl.setReprocessedFlag(StringPool.BLANK);
		}
		else {
			ivldCompanyMasterImpl.setReprocessedFlag(reprocessedFlag);
		}

		if (udc6 == null) {
			ivldCompanyMasterImpl.setUdc6(StringPool.BLANK);
		}
		else {
			ivldCompanyMasterImpl.setUdc6(udc6);
		}

		if (udc5 == null) {
			ivldCompanyMasterImpl.setUdc5(StringPool.BLANK);
		}
		else {
			ivldCompanyMasterImpl.setUdc5(udc5);
		}

		if (udc4 == null) {
			ivldCompanyMasterImpl.setUdc4(StringPool.BLANK);
		}
		else {
			ivldCompanyMasterImpl.setUdc4(udc4);
		}

		if (udc1 == null) {
			ivldCompanyMasterImpl.setUdc1(StringPool.BLANK);
		}
		else {
			ivldCompanyMasterImpl.setUdc1(udc1);
		}

		if (udc2 == null) {
			ivldCompanyMasterImpl.setUdc2(StringPool.BLANK);
		}
		else {
			ivldCompanyMasterImpl.setUdc2(udc2);
		}

		if (zipCode == null) {
			ivldCompanyMasterImpl.setZipCode(StringPool.BLANK);
		}
		else {
			ivldCompanyMasterImpl.setZipCode(zipCode);
		}

		if (udc3 == null) {
			ivldCompanyMasterImpl.setUdc3(StringPool.BLANK);
		}
		else {
			ivldCompanyMasterImpl.setUdc3(udc3);
		}

		if (reasonForFailure == null) {
			ivldCompanyMasterImpl.setReasonForFailure(StringPool.BLANK);
		}
		else {
			ivldCompanyMasterImpl.setReasonForFailure(reasonForFailure);
		}

		if (companyId == null) {
			ivldCompanyMasterImpl.setCompanyId(StringPool.BLANK);
		}
		else {
			ivldCompanyMasterImpl.setCompanyId(companyId);
		}

		if (address1 == null) {
			ivldCompanyMasterImpl.setAddress1(StringPool.BLANK);
		}
		else {
			ivldCompanyMasterImpl.setAddress1(address1);
		}

		if (country == null) {
			ivldCompanyMasterImpl.setCountry(StringPool.BLANK);
		}
		else {
			ivldCompanyMasterImpl.setCountry(country);
		}

		if (address2 == null) {
			ivldCompanyMasterImpl.setAddress2(StringPool.BLANK);
		}
		else {
			ivldCompanyMasterImpl.setAddress2(address2);
		}

		if (companyType == null) {
			ivldCompanyMasterImpl.setCompanyType(StringPool.BLANK);
		}
		else {
			ivldCompanyMasterImpl.setCompanyType(companyType);
		}

		if (companyStartDate == null) {
			ivldCompanyMasterImpl.setCompanyStartDate(StringPool.BLANK);
		}
		else {
			ivldCompanyMasterImpl.setCompanyStartDate(companyStartDate);
		}

		if (companyNo == null) {
			ivldCompanyMasterImpl.setCompanyNo(StringPool.BLANK);
		}
		else {
			ivldCompanyMasterImpl.setCompanyNo(companyNo);
		}

		if (batchId == null) {
			ivldCompanyMasterImpl.setBatchId(StringPool.BLANK);
		}
		else {
			ivldCompanyMasterImpl.setBatchId(batchId);
		}

		if (companyStatus == null) {
			ivldCompanyMasterImpl.setCompanyStatus(StringPool.BLANK);
		}
		else {
			ivldCompanyMasterImpl.setCompanyStatus(companyStatus);
		}

		if (companyEndDate == null) {
			ivldCompanyMasterImpl.setCompanyEndDate(StringPool.BLANK);
		}
		else {
			ivldCompanyMasterImpl.setCompanyEndDate(companyEndDate);
		}

		if (errorField == null) {
			ivldCompanyMasterImpl.setErrorField(StringPool.BLANK);
		}
		else {
			ivldCompanyMasterImpl.setErrorField(errorField);
		}

		if (city == null) {
			ivldCompanyMasterImpl.setCity(StringPool.BLANK);
		}
		else {
			ivldCompanyMasterImpl.setCity(city);
		}

		if (regionCode == null) {
			ivldCompanyMasterImpl.setRegionCode(StringPool.BLANK);
		}
		else {
			ivldCompanyMasterImpl.setRegionCode(regionCode);
		}

		ivldCompanyMasterImpl.setCheckRecord(checkRecord);

		ivldCompanyMasterImpl.resetOriginalValues();

		return ivldCompanyMasterImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		state = objectInput.readUTF();
		financialSystem = objectInput.readUTF();
		companyName = objectInput.readUTF();
		companyGroup = objectInput.readUTF();
		companyCategory = objectInput.readUTF();
		lastUpdatedDate = objectInput.readUTF();
		modifiedDate = objectInput.readLong();
		status = objectInput.readUTF();

		ivldCompanyMasterSid = objectInput.readInt();
		lives = objectInput.readUTF();
		organizationKey = objectInput.readUTF();
		source = objectInput.readUTF();
		createdDate = objectInput.readLong();
		createdBy = objectInput.readUTF();
		addChgDelIndicator = objectInput.readUTF();
		errorCode = objectInput.readUTF();
		intfInsertedDate = objectInput.readLong();
		modifiedBy = objectInput.readUTF();
		companyMasterIntfid = objectInput.readUTF();
		reprocessedFlag = objectInput.readUTF();
		udc6 = objectInput.readUTF();
		udc5 = objectInput.readUTF();
		udc4 = objectInput.readUTF();
		udc1 = objectInput.readUTF();
		udc2 = objectInput.readUTF();
		zipCode = objectInput.readUTF();
		udc3 = objectInput.readUTF();
		reasonForFailure = objectInput.readUTF();
		companyId = objectInput.readUTF();
		address1 = objectInput.readUTF();
		country = objectInput.readUTF();
		address2 = objectInput.readUTF();
		companyType = objectInput.readUTF();
		companyStartDate = objectInput.readUTF();
		companyNo = objectInput.readUTF();
		batchId = objectInput.readUTF();
		companyStatus = objectInput.readUTF();
		companyEndDate = objectInput.readUTF();
		errorField = objectInput.readUTF();
		city = objectInput.readUTF();
		regionCode = objectInput.readUTF();

		checkRecord = objectInput.readBoolean();
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

		if (companyName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(companyName);
		}

		if (companyGroup == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(companyGroup);
		}

		if (companyCategory == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(companyCategory);
		}

		if (lastUpdatedDate == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(lastUpdatedDate);
		}

		objectOutput.writeLong(modifiedDate);

		if (status == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(status);
		}

		objectOutput.writeInt(ivldCompanyMasterSid);

		if (lives == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(lives);
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

		objectOutput.writeLong(createdDate);

		if (createdBy == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(createdBy);
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

		objectOutput.writeLong(intfInsertedDate);

		if (modifiedBy == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(modifiedBy);
		}

		if (companyMasterIntfid == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(companyMasterIntfid);
		}

		if (reprocessedFlag == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(reprocessedFlag);
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

		if (udc2 == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(udc2);
		}

		if (zipCode == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(zipCode);
		}

		if (udc3 == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(udc3);
		}

		if (reasonForFailure == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(reasonForFailure);
		}

		if (companyId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(companyId);
		}

		if (address1 == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(address1);
		}

		if (country == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(country);
		}

		if (address2 == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(address2);
		}

		if (companyType == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(companyType);
		}

		if (companyStartDate == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(companyStartDate);
		}

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

		if (companyEndDate == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(companyEndDate);
		}

		if (errorField == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(errorField);
		}

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

		objectOutput.writeBoolean(checkRecord);
	}

	public String state;
	public String financialSystem;
	public String companyName;
	public String companyGroup;
	public String companyCategory;
	public String lastUpdatedDate;
	public long modifiedDate;
	public String status;
	public int ivldCompanyMasterSid;
	public String lives;
	public String organizationKey;
	public String source;
	public long createdDate;
	public String createdBy;
	public String addChgDelIndicator;
	public String errorCode;
	public long intfInsertedDate;
	public String modifiedBy;
	public String companyMasterIntfid;
	public String reprocessedFlag;
	public String udc6;
	public String udc5;
	public String udc4;
	public String udc1;
	public String udc2;
	public String zipCode;
	public String udc3;
	public String reasonForFailure;
	public String companyId;
	public String address1;
	public String country;
	public String address2;
	public String companyType;
	public String companyStartDate;
	public String companyNo;
	public String batchId;
	public String companyStatus;
	public String companyEndDate;
	public String errorField;
	public String city;
	public String regionCode;
	public boolean checkRecord;
}