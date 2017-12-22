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

import com.stpl.app.parttwo.model.AhTempDetails;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing AhTempDetails in entity cache.
 *
 * @author
 * @see AhTempDetails
 * @generated
 */
@ProviderType
public class AhTempDetailsCacheModel implements CacheModel<AhTempDetails>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AhTempDetailsCacheModel)) {
			return false;
		}

		AhTempDetailsCacheModel ahTempDetailsCacheModel = (AhTempDetailsCacheModel)obj;

		if (componentMasterSid == ahTempDetailsCacheModel.componentMasterSid) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, componentMasterSid);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(49);

		sb.append("{checkRecord=");
		sb.append(checkRecord);
		sb.append(", contractHolder=");
		sb.append(contractHolder);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", itemMasterSid=");
		sb.append(itemMasterSid);
		sb.append(", businessUnitNo=");
		sb.append(businessUnitNo);
		sb.append(", companyName=");
		sb.append(companyName);
		sb.append(", itemId=");
		sb.append(itemId);
		sb.append(", brandName=");
		sb.append(brandName);
		sb.append(", componentName=");
		sb.append(componentName);
		sb.append(", createdDate=");
		sb.append(createdDate);
		sb.append(", createdBy=");
		sb.append(createdBy);
		sb.append(", screenName=");
		sb.append(screenName);
		sb.append(", businessUnitName=");
		sb.append(businessUnitName);
		sb.append(", companyNo=");
		sb.append(companyNo);
		sb.append(", itemIdentifierType=");
		sb.append(itemIdentifierType);
		sb.append(", componentNo=");
		sb.append(componentNo);
		sb.append(", sessionId=");
		sb.append(sessionId);
		sb.append(", itemName=");
		sb.append(itemName);
		sb.append(", itemIdentifier=");
		sb.append(itemIdentifier);
		sb.append(", companySid=");
		sb.append(companySid);
		sb.append(", itemNo=");
		sb.append(itemNo);
		sb.append(", componentType=");
		sb.append(componentType);
		sb.append(", theraputicClass=");
		sb.append(theraputicClass);
		sb.append(", componentMasterSid=");
		sb.append(componentMasterSid);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public AhTempDetails toEntityModel() {
		AhTempDetailsImpl ahTempDetailsImpl = new AhTempDetailsImpl();

		ahTempDetailsImpl.setCheckRecord(checkRecord);

		if (contractHolder == null) {
			ahTempDetailsImpl.setContractHolder(StringPool.BLANK);
		}
		else {
			ahTempDetailsImpl.setContractHolder(contractHolder);
		}

		if (userId == null) {
			ahTempDetailsImpl.setUserId(StringPool.BLANK);
		}
		else {
			ahTempDetailsImpl.setUserId(userId);
		}

		ahTempDetailsImpl.setItemMasterSid(itemMasterSid);

		if (businessUnitNo == null) {
			ahTempDetailsImpl.setBusinessUnitNo(StringPool.BLANK);
		}
		else {
			ahTempDetailsImpl.setBusinessUnitNo(businessUnitNo);
		}

		if (companyName == null) {
			ahTempDetailsImpl.setCompanyName(StringPool.BLANK);
		}
		else {
			ahTempDetailsImpl.setCompanyName(companyName);
		}

		if (itemId == null) {
			ahTempDetailsImpl.setItemId(StringPool.BLANK);
		}
		else {
			ahTempDetailsImpl.setItemId(itemId);
		}

		if (brandName == null) {
			ahTempDetailsImpl.setBrandName(StringPool.BLANK);
		}
		else {
			ahTempDetailsImpl.setBrandName(brandName);
		}

		if (componentName == null) {
			ahTempDetailsImpl.setComponentName(StringPool.BLANK);
		}
		else {
			ahTempDetailsImpl.setComponentName(componentName);
		}

		if (createdDate == Long.MIN_VALUE) {
			ahTempDetailsImpl.setCreatedDate(null);
		}
		else {
			ahTempDetailsImpl.setCreatedDate(new Date(createdDate));
		}

		if (createdBy == null) {
			ahTempDetailsImpl.setCreatedBy(StringPool.BLANK);
		}
		else {
			ahTempDetailsImpl.setCreatedBy(createdBy);
		}

		if (screenName == null) {
			ahTempDetailsImpl.setScreenName(StringPool.BLANK);
		}
		else {
			ahTempDetailsImpl.setScreenName(screenName);
		}

		if (businessUnitName == null) {
			ahTempDetailsImpl.setBusinessUnitName(StringPool.BLANK);
		}
		else {
			ahTempDetailsImpl.setBusinessUnitName(businessUnitName);
		}

		if (companyNo == null) {
			ahTempDetailsImpl.setCompanyNo(StringPool.BLANK);
		}
		else {
			ahTempDetailsImpl.setCompanyNo(companyNo);
		}

		if (itemIdentifierType == null) {
			ahTempDetailsImpl.setItemIdentifierType(StringPool.BLANK);
		}
		else {
			ahTempDetailsImpl.setItemIdentifierType(itemIdentifierType);
		}

		if (componentNo == null) {
			ahTempDetailsImpl.setComponentNo(StringPool.BLANK);
		}
		else {
			ahTempDetailsImpl.setComponentNo(componentNo);
		}

		if (sessionId == null) {
			ahTempDetailsImpl.setSessionId(StringPool.BLANK);
		}
		else {
			ahTempDetailsImpl.setSessionId(sessionId);
		}

		if (itemName == null) {
			ahTempDetailsImpl.setItemName(StringPool.BLANK);
		}
		else {
			ahTempDetailsImpl.setItemName(itemName);
		}

		if (itemIdentifier == null) {
			ahTempDetailsImpl.setItemIdentifier(StringPool.BLANK);
		}
		else {
			ahTempDetailsImpl.setItemIdentifier(itemIdentifier);
		}

		ahTempDetailsImpl.setCompanySid(companySid);

		if (itemNo == null) {
			ahTempDetailsImpl.setItemNo(StringPool.BLANK);
		}
		else {
			ahTempDetailsImpl.setItemNo(itemNo);
		}

		if (componentType == null) {
			ahTempDetailsImpl.setComponentType(StringPool.BLANK);
		}
		else {
			ahTempDetailsImpl.setComponentType(componentType);
		}

		if (theraputicClass == null) {
			ahTempDetailsImpl.setTheraputicClass(StringPool.BLANK);
		}
		else {
			ahTempDetailsImpl.setTheraputicClass(theraputicClass);
		}

		ahTempDetailsImpl.setComponentMasterSid(componentMasterSid);

		ahTempDetailsImpl.resetOriginalValues();

		return ahTempDetailsImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		checkRecord = objectInput.readBoolean();
		contractHolder = objectInput.readUTF();
		userId = objectInput.readUTF();

		itemMasterSid = objectInput.readInt();
		businessUnitNo = objectInput.readUTF();
		companyName = objectInput.readUTF();
		itemId = objectInput.readUTF();
		brandName = objectInput.readUTF();
		componentName = objectInput.readUTF();
		createdDate = objectInput.readLong();
		createdBy = objectInput.readUTF();
		screenName = objectInput.readUTF();
		businessUnitName = objectInput.readUTF();
		companyNo = objectInput.readUTF();
		itemIdentifierType = objectInput.readUTF();
		componentNo = objectInput.readUTF();
		sessionId = objectInput.readUTF();
		itemName = objectInput.readUTF();
		itemIdentifier = objectInput.readUTF();

		companySid = objectInput.readInt();
		itemNo = objectInput.readUTF();
		componentType = objectInput.readUTF();
		theraputicClass = objectInput.readUTF();

		componentMasterSid = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeBoolean(checkRecord);

		if (contractHolder == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(contractHolder);
		}

		if (userId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(userId);
		}

		objectOutput.writeInt(itemMasterSid);

		if (businessUnitNo == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(businessUnitNo);
		}

		if (companyName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(companyName);
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

		if (componentName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(componentName);
		}

		objectOutput.writeLong(createdDate);

		if (createdBy == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(createdBy);
		}

		if (screenName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(screenName);
		}

		if (businessUnitName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(businessUnitName);
		}

		if (companyNo == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(companyNo);
		}

		if (itemIdentifierType == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(itemIdentifierType);
		}

		if (componentNo == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(componentNo);
		}

		if (sessionId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(sessionId);
		}

		if (itemName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(itemName);
		}

		if (itemIdentifier == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(itemIdentifier);
		}

		objectOutput.writeInt(companySid);

		if (itemNo == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(itemNo);
		}

		if (componentType == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(componentType);
		}

		if (theraputicClass == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(theraputicClass);
		}

		objectOutput.writeInt(componentMasterSid);
	}

	public boolean checkRecord;
	public String contractHolder;
	public String userId;
	public int itemMasterSid;
	public String businessUnitNo;
	public String companyName;
	public String itemId;
	public String brandName;
	public String componentName;
	public long createdDate;
	public String createdBy;
	public String screenName;
	public String businessUnitName;
	public String companyNo;
	public String itemIdentifierType;
	public String componentNo;
	public String sessionId;
	public String itemName;
	public String itemIdentifier;
	public int companySid;
	public String itemNo;
	public String componentType;
	public String theraputicClass;
	public int componentMasterSid;
}