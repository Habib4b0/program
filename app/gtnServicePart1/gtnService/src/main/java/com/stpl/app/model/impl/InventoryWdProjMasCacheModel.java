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

import com.stpl.app.model.InventoryWdProjMas;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing InventoryWdProjMas in entity cache.
 *
 * @author
 * @see InventoryWdProjMas
 * @generated
 */
@ProviderType
public class InventoryWdProjMasCacheModel implements CacheModel<InventoryWdProjMas>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof InventoryWdProjMasCacheModel)) {
			return false;
		}

		InventoryWdProjMasCacheModel inventoryWdProjMasCacheModel = (InventoryWdProjMasCacheModel)obj;

		if (inventoryWdProjMasSid == inventoryWdProjMasCacheModel.inventoryWdProjMasSid) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, inventoryWdProjMasSid);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(47);

		sb.append("{week=");
		sb.append(week);
		sb.append(", itemMasterSid=");
		sb.append(itemMasterSid);
		sb.append(", unitsWithdrawn=");
		sb.append(unitsWithdrawn);
		sb.append(", country=");
		sb.append(country);
		sb.append(", year=");
		sb.append(year);
		sb.append(", itemId=");
		sb.append(itemId);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", organizationKey=");
		sb.append(organizationKey);
		sb.append(", recordLockStatus=");
		sb.append(recordLockStatus);
		sb.append(", itemIdentifierCodeQualifier=");
		sb.append(itemIdentifierCodeQualifier);
		sb.append(", source=");
		sb.append(source);
		sb.append(", createdDate=");
		sb.append(createdDate);
		sb.append(", createdBy=");
		sb.append(createdBy);
		sb.append(", inventoryWdProjMasSid=");
		sb.append(inventoryWdProjMasSid);
		sb.append(", day=");
		sb.append(day);
		sb.append(", forecastVer=");
		sb.append(forecastVer);
		sb.append(", batchId=");
		sb.append(batchId);
		sb.append(", itemIdentifier=");
		sb.append(itemIdentifier);
		sb.append(", inboundStatus=");
		sb.append(inboundStatus);
		sb.append(", modifiedBy=");
		sb.append(modifiedBy);
		sb.append(", month=");
		sb.append(month);
		sb.append(", forecastName=");
		sb.append(forecastName);
		sb.append(", amountWithdrawn=");
		sb.append(amountWithdrawn);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public InventoryWdProjMas toEntityModel() {
		InventoryWdProjMasImpl inventoryWdProjMasImpl = new InventoryWdProjMasImpl();

		if (week == null) {
			inventoryWdProjMasImpl.setWeek(StringPool.BLANK);
		}
		else {
			inventoryWdProjMasImpl.setWeek(week);
		}

		inventoryWdProjMasImpl.setItemMasterSid(itemMasterSid);

		if (unitsWithdrawn == null) {
			inventoryWdProjMasImpl.setUnitsWithdrawn(StringPool.BLANK);
		}
		else {
			inventoryWdProjMasImpl.setUnitsWithdrawn(unitsWithdrawn);
		}

		if (country == null) {
			inventoryWdProjMasImpl.setCountry(StringPool.BLANK);
		}
		else {
			inventoryWdProjMasImpl.setCountry(country);
		}

		if (year == null) {
			inventoryWdProjMasImpl.setYear(StringPool.BLANK);
		}
		else {
			inventoryWdProjMasImpl.setYear(year);
		}

		if (itemId == null) {
			inventoryWdProjMasImpl.setItemId(StringPool.BLANK);
		}
		else {
			inventoryWdProjMasImpl.setItemId(itemId);
		}

		if (modifiedDate == Long.MIN_VALUE) {
			inventoryWdProjMasImpl.setModifiedDate(null);
		}
		else {
			inventoryWdProjMasImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (organizationKey == null) {
			inventoryWdProjMasImpl.setOrganizationKey(StringPool.BLANK);
		}
		else {
			inventoryWdProjMasImpl.setOrganizationKey(organizationKey);
		}

		inventoryWdProjMasImpl.setRecordLockStatus(recordLockStatus);

		if (itemIdentifierCodeQualifier == null) {
			inventoryWdProjMasImpl.setItemIdentifierCodeQualifier(StringPool.BLANK);
		}
		else {
			inventoryWdProjMasImpl.setItemIdentifierCodeQualifier(itemIdentifierCodeQualifier);
		}

		if (source == null) {
			inventoryWdProjMasImpl.setSource(StringPool.BLANK);
		}
		else {
			inventoryWdProjMasImpl.setSource(source);
		}

		if (createdDate == Long.MIN_VALUE) {
			inventoryWdProjMasImpl.setCreatedDate(null);
		}
		else {
			inventoryWdProjMasImpl.setCreatedDate(new Date(createdDate));
		}

		if (createdBy == null) {
			inventoryWdProjMasImpl.setCreatedBy(StringPool.BLANK);
		}
		else {
			inventoryWdProjMasImpl.setCreatedBy(createdBy);
		}

		inventoryWdProjMasImpl.setInventoryWdProjMasSid(inventoryWdProjMasSid);

		if (day == null) {
			inventoryWdProjMasImpl.setDay(StringPool.BLANK);
		}
		else {
			inventoryWdProjMasImpl.setDay(day);
		}

		if (forecastVer == null) {
			inventoryWdProjMasImpl.setForecastVer(StringPool.BLANK);
		}
		else {
			inventoryWdProjMasImpl.setForecastVer(forecastVer);
		}

		if (batchId == null) {
			inventoryWdProjMasImpl.setBatchId(StringPool.BLANK);
		}
		else {
			inventoryWdProjMasImpl.setBatchId(batchId);
		}

		if (itemIdentifier == null) {
			inventoryWdProjMasImpl.setItemIdentifier(StringPool.BLANK);
		}
		else {
			inventoryWdProjMasImpl.setItemIdentifier(itemIdentifier);
		}

		if (inboundStatus == null) {
			inventoryWdProjMasImpl.setInboundStatus(StringPool.BLANK);
		}
		else {
			inventoryWdProjMasImpl.setInboundStatus(inboundStatus);
		}

		if (modifiedBy == null) {
			inventoryWdProjMasImpl.setModifiedBy(StringPool.BLANK);
		}
		else {
			inventoryWdProjMasImpl.setModifiedBy(modifiedBy);
		}

		if (month == null) {
			inventoryWdProjMasImpl.setMonth(StringPool.BLANK);
		}
		else {
			inventoryWdProjMasImpl.setMonth(month);
		}

		if (forecastName == null) {
			inventoryWdProjMasImpl.setForecastName(StringPool.BLANK);
		}
		else {
			inventoryWdProjMasImpl.setForecastName(forecastName);
		}

		if (amountWithdrawn == null) {
			inventoryWdProjMasImpl.setAmountWithdrawn(StringPool.BLANK);
		}
		else {
			inventoryWdProjMasImpl.setAmountWithdrawn(amountWithdrawn);
		}

		inventoryWdProjMasImpl.resetOriginalValues();

		return inventoryWdProjMasImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		week = objectInput.readUTF();

		itemMasterSid = objectInput.readInt();
		unitsWithdrawn = objectInput.readUTF();
		country = objectInput.readUTF();
		year = objectInput.readUTF();
		itemId = objectInput.readUTF();
		modifiedDate = objectInput.readLong();
		organizationKey = objectInput.readUTF();

		recordLockStatus = objectInput.readBoolean();
		itemIdentifierCodeQualifier = objectInput.readUTF();
		source = objectInput.readUTF();
		createdDate = objectInput.readLong();
		createdBy = objectInput.readUTF();

		inventoryWdProjMasSid = objectInput.readInt();
		day = objectInput.readUTF();
		forecastVer = objectInput.readUTF();
		batchId = objectInput.readUTF();
		itemIdentifier = objectInput.readUTF();
		inboundStatus = objectInput.readUTF();
		modifiedBy = objectInput.readUTF();
		month = objectInput.readUTF();
		forecastName = objectInput.readUTF();
		amountWithdrawn = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		if (week == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(week);
		}

		objectOutput.writeInt(itemMasterSid);

		if (unitsWithdrawn == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(unitsWithdrawn);
		}

		if (country == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(country);
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

		objectOutput.writeLong(modifiedDate);

		if (organizationKey == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(organizationKey);
		}

		objectOutput.writeBoolean(recordLockStatus);

		if (itemIdentifierCodeQualifier == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(itemIdentifierCodeQualifier);
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

		objectOutput.writeInt(inventoryWdProjMasSid);

		if (day == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(day);
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

		if (itemIdentifier == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(itemIdentifier);
		}

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

		if (month == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(month);
		}

		if (forecastName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(forecastName);
		}

		if (amountWithdrawn == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(amountWithdrawn);
		}
	}

	public String week;
	public int itemMasterSid;
	public String unitsWithdrawn;
	public String country;
	public String year;
	public String itemId;
	public long modifiedDate;
	public String organizationKey;
	public boolean recordLockStatus;
	public String itemIdentifierCodeQualifier;
	public String source;
	public long createdDate;
	public String createdBy;
	public int inventoryWdProjMasSid;
	public String day;
	public String forecastVer;
	public String batchId;
	public String itemIdentifier;
	public String inboundStatus;
	public String modifiedBy;
	public String month;
	public String forecastName;
	public String amountWithdrawn;
}