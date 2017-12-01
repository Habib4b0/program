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

import com.stpl.app.model.InventoryWdActualMas;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing InventoryWdActualMas in entity cache.
 *
 * @author
 * @see InventoryWdActualMas
 * @generated
 */
@ProviderType
public class InventoryWdActualMasCacheModel implements CacheModel<InventoryWdActualMas>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof InventoryWdActualMasCacheModel)) {
			return false;
		}

		InventoryWdActualMasCacheModel inventoryWdActualMasCacheModel = (InventoryWdActualMasCacheModel)obj;

		if (inventoryWdActualMasSid == inventoryWdActualMasCacheModel.inventoryWdActualMasSid) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, inventoryWdActualMasSid);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(55);

		sb.append("{quantityOnOrder=");
		sb.append(quantityOnOrder);
		sb.append(", week=");
		sb.append(week);
		sb.append(", amountOnHand=");
		sb.append(amountOnHand);
		sb.append(", itemMasterSid=");
		sb.append(itemMasterSid);
		sb.append(", inventoryWdActualMasSid=");
		sb.append(inventoryWdActualMasSid);
		sb.append(", year=");
		sb.append(year);
		sb.append(", itemId=");
		sb.append(itemId);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", organizationKey=");
		sb.append(organizationKey);
		sb.append(", source=");
		sb.append(source);
		sb.append(", createdBy=");
		sb.append(createdBy);
		sb.append(", createdDate=");
		sb.append(createdDate);
		sb.append(", day=");
		sb.append(day);
		sb.append(", unitsOnHand=");
		sb.append(unitsOnHand);
		sb.append(", amountReceived=");
		sb.append(amountReceived);
		sb.append(", itemIdentifier=");
		sb.append(itemIdentifier);
		sb.append(", modifiedBy=");
		sb.append(modifiedBy);
		sb.append(", inboundStatus=");
		sb.append(inboundStatus);
		sb.append(", month=");
		sb.append(month);
		sb.append(", amountWithdrawn=");
		sb.append(amountWithdrawn);
		sb.append(", quantityReceived=");
		sb.append(quantityReceived);
		sb.append(", unitsWithdrawn=");
		sb.append(unitsWithdrawn);
		sb.append(", country=");
		sb.append(country);
		sb.append(", recordLockStatus=");
		sb.append(recordLockStatus);
		sb.append(", itemIdentifierCodeQualifier=");
		sb.append(itemIdentifierCodeQualifier);
		sb.append(", batchId=");
		sb.append(batchId);
		sb.append(", amountOnOrder=");
		sb.append(amountOnOrder);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public InventoryWdActualMas toEntityModel() {
		InventoryWdActualMasImpl inventoryWdActualMasImpl = new InventoryWdActualMasImpl();

		if (quantityOnOrder == null) {
			inventoryWdActualMasImpl.setQuantityOnOrder(StringPool.BLANK);
		}
		else {
			inventoryWdActualMasImpl.setQuantityOnOrder(quantityOnOrder);
		}

		if (week == null) {
			inventoryWdActualMasImpl.setWeek(StringPool.BLANK);
		}
		else {
			inventoryWdActualMasImpl.setWeek(week);
		}

		if (amountOnHand == null) {
			inventoryWdActualMasImpl.setAmountOnHand(StringPool.BLANK);
		}
		else {
			inventoryWdActualMasImpl.setAmountOnHand(amountOnHand);
		}

		inventoryWdActualMasImpl.setItemMasterSid(itemMasterSid);
		inventoryWdActualMasImpl.setInventoryWdActualMasSid(inventoryWdActualMasSid);

		if (year == null) {
			inventoryWdActualMasImpl.setYear(StringPool.BLANK);
		}
		else {
			inventoryWdActualMasImpl.setYear(year);
		}

		if (itemId == null) {
			inventoryWdActualMasImpl.setItemId(StringPool.BLANK);
		}
		else {
			inventoryWdActualMasImpl.setItemId(itemId);
		}

		if (modifiedDate == Long.MIN_VALUE) {
			inventoryWdActualMasImpl.setModifiedDate(null);
		}
		else {
			inventoryWdActualMasImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (organizationKey == null) {
			inventoryWdActualMasImpl.setOrganizationKey(StringPool.BLANK);
		}
		else {
			inventoryWdActualMasImpl.setOrganizationKey(organizationKey);
		}

		if (source == null) {
			inventoryWdActualMasImpl.setSource(StringPool.BLANK);
		}
		else {
			inventoryWdActualMasImpl.setSource(source);
		}

		if (createdBy == null) {
			inventoryWdActualMasImpl.setCreatedBy(StringPool.BLANK);
		}
		else {
			inventoryWdActualMasImpl.setCreatedBy(createdBy);
		}

		if (createdDate == Long.MIN_VALUE) {
			inventoryWdActualMasImpl.setCreatedDate(null);
		}
		else {
			inventoryWdActualMasImpl.setCreatedDate(new Date(createdDate));
		}

		if (day == null) {
			inventoryWdActualMasImpl.setDay(StringPool.BLANK);
		}
		else {
			inventoryWdActualMasImpl.setDay(day);
		}

		if (unitsOnHand == null) {
			inventoryWdActualMasImpl.setUnitsOnHand(StringPool.BLANK);
		}
		else {
			inventoryWdActualMasImpl.setUnitsOnHand(unitsOnHand);
		}

		if (amountReceived == null) {
			inventoryWdActualMasImpl.setAmountReceived(StringPool.BLANK);
		}
		else {
			inventoryWdActualMasImpl.setAmountReceived(amountReceived);
		}

		if (itemIdentifier == null) {
			inventoryWdActualMasImpl.setItemIdentifier(StringPool.BLANK);
		}
		else {
			inventoryWdActualMasImpl.setItemIdentifier(itemIdentifier);
		}

		if (modifiedBy == null) {
			inventoryWdActualMasImpl.setModifiedBy(StringPool.BLANK);
		}
		else {
			inventoryWdActualMasImpl.setModifiedBy(modifiedBy);
		}

		if (inboundStatus == null) {
			inventoryWdActualMasImpl.setInboundStatus(StringPool.BLANK);
		}
		else {
			inventoryWdActualMasImpl.setInboundStatus(inboundStatus);
		}

		if (month == null) {
			inventoryWdActualMasImpl.setMonth(StringPool.BLANK);
		}
		else {
			inventoryWdActualMasImpl.setMonth(month);
		}

		if (amountWithdrawn == null) {
			inventoryWdActualMasImpl.setAmountWithdrawn(StringPool.BLANK);
		}
		else {
			inventoryWdActualMasImpl.setAmountWithdrawn(amountWithdrawn);
		}

		if (quantityReceived == null) {
			inventoryWdActualMasImpl.setQuantityReceived(StringPool.BLANK);
		}
		else {
			inventoryWdActualMasImpl.setQuantityReceived(quantityReceived);
		}

		if (unitsWithdrawn == null) {
			inventoryWdActualMasImpl.setUnitsWithdrawn(StringPool.BLANK);
		}
		else {
			inventoryWdActualMasImpl.setUnitsWithdrawn(unitsWithdrawn);
		}

		if (country == null) {
			inventoryWdActualMasImpl.setCountry(StringPool.BLANK);
		}
		else {
			inventoryWdActualMasImpl.setCountry(country);
		}

		inventoryWdActualMasImpl.setRecordLockStatus(recordLockStatus);

		if (itemIdentifierCodeQualifier == null) {
			inventoryWdActualMasImpl.setItemIdentifierCodeQualifier(StringPool.BLANK);
		}
		else {
			inventoryWdActualMasImpl.setItemIdentifierCodeQualifier(itemIdentifierCodeQualifier);
		}

		if (batchId == null) {
			inventoryWdActualMasImpl.setBatchId(StringPool.BLANK);
		}
		else {
			inventoryWdActualMasImpl.setBatchId(batchId);
		}

		if (amountOnOrder == null) {
			inventoryWdActualMasImpl.setAmountOnOrder(StringPool.BLANK);
		}
		else {
			inventoryWdActualMasImpl.setAmountOnOrder(amountOnOrder);
		}

		inventoryWdActualMasImpl.resetOriginalValues();

		return inventoryWdActualMasImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		quantityOnOrder = objectInput.readUTF();
		week = objectInput.readUTF();
		amountOnHand = objectInput.readUTF();

		itemMasterSid = objectInput.readInt();

		inventoryWdActualMasSid = objectInput.readInt();
		year = objectInput.readUTF();
		itemId = objectInput.readUTF();
		modifiedDate = objectInput.readLong();
		organizationKey = objectInput.readUTF();
		source = objectInput.readUTF();
		createdBy = objectInput.readUTF();
		createdDate = objectInput.readLong();
		day = objectInput.readUTF();
		unitsOnHand = objectInput.readUTF();
		amountReceived = objectInput.readUTF();
		itemIdentifier = objectInput.readUTF();
		modifiedBy = objectInput.readUTF();
		inboundStatus = objectInput.readUTF();
		month = objectInput.readUTF();
		amountWithdrawn = objectInput.readUTF();
		quantityReceived = objectInput.readUTF();
		unitsWithdrawn = objectInput.readUTF();
		country = objectInput.readUTF();

		recordLockStatus = objectInput.readBoolean();
		itemIdentifierCodeQualifier = objectInput.readUTF();
		batchId = objectInput.readUTF();
		amountOnOrder = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		if (quantityOnOrder == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(quantityOnOrder);
		}

		if (week == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(week);
		}

		if (amountOnHand == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(amountOnHand);
		}

		objectOutput.writeInt(itemMasterSid);

		objectOutput.writeInt(inventoryWdActualMasSid);

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

		if (day == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(day);
		}

		if (unitsOnHand == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(unitsOnHand);
		}

		if (amountReceived == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(amountReceived);
		}

		if (itemIdentifier == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(itemIdentifier);
		}

		if (modifiedBy == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(modifiedBy);
		}

		if (inboundStatus == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(inboundStatus);
		}

		if (month == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(month);
		}

		if (amountWithdrawn == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(amountWithdrawn);
		}

		if (quantityReceived == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(quantityReceived);
		}

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

		objectOutput.writeBoolean(recordLockStatus);

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

		if (amountOnOrder == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(amountOnOrder);
		}
	}

	public String quantityOnOrder;
	public String week;
	public String amountOnHand;
	public int itemMasterSid;
	public int inventoryWdActualMasSid;
	public String year;
	public String itemId;
	public long modifiedDate;
	public String organizationKey;
	public String source;
	public String createdBy;
	public long createdDate;
	public String day;
	public String unitsOnHand;
	public String amountReceived;
	public String itemIdentifier;
	public String modifiedBy;
	public String inboundStatus;
	public String month;
	public String amountWithdrawn;
	public String quantityReceived;
	public String unitsWithdrawn;
	public String country;
	public boolean recordLockStatus;
	public String itemIdentifierCodeQualifier;
	public String batchId;
	public String amountOnOrder;
}