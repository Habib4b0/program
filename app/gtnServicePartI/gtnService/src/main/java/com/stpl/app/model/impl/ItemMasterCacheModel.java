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

import com.stpl.app.model.ItemMaster;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing ItemMaster in entity cache.
 *
 * @author
 * @see ItemMaster
 * @generated
 */
@ProviderType
public class ItemMasterCacheModel implements CacheModel<ItemMaster>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ItemMasterCacheModel)) {
			return false;
		}

		ItemMasterCacheModel itemMasterCacheModel = (ItemMasterCacheModel)obj;

		if (itemMasterSid == itemMasterCacheModel.itemMasterSid) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, itemMasterSid);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(143);

		sb.append("{itemStatus=");
		sb.append(itemStatus);
		sb.append(", itemDesc=");
		sb.append(itemDesc);
		sb.append(", authorizedGenericStartDate=");
		sb.append(authorizedGenericStartDate);
		sb.append(", acquiredAmp=");
		sb.append(acquiredAmp);
		sb.append(", newFormulationStartDate=");
		sb.append(newFormulationStartDate);
		sb.append(", marketTerminationDate=");
		sb.append(marketTerminationDate);
		sb.append(", obraBamp=");
		sb.append(obraBamp);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", therapeuticClass=");
		sb.append(therapeuticClass);
		sb.append(", organizationKey=");
		sb.append(organizationKey);
		sb.append(", acquiredBamp=");
		sb.append(acquiredBamp);
		sb.append(", pediatricExclusiveEndDate=");
		sb.append(pediatricExclusiveEndDate);
		sb.append(", source=");
		sb.append(source);
		sb.append(", newFormulation=");
		sb.append(newFormulation);
		sb.append(", divestitureDate=");
		sb.append(divestitureDate);
		sb.append(", primaryUom=");
		sb.append(primaryUom);
		sb.append(", newFormulationEndDate=");
		sb.append(newFormulationEndDate);
		sb.append(", modifiedBy=");
		sb.append(modifiedBy);
		sb.append(", inboundStatus=");
		sb.append(inboundStatus);
		sb.append(", packageSizeCode=");
		sb.append(packageSizeCode);
		sb.append(", secondaryUom=");
		sb.append(secondaryUom);
		sb.append(", discontinuationDate=");
		sb.append(discontinuationDate);
		sb.append(", packageSizeIntroDate=");
		sb.append(packageSizeIntroDate);
		sb.append(", manufacturerId=");
		sb.append(manufacturerId);
		sb.append(", itemEndDate=");
		sb.append(itemEndDate);
		sb.append(", itemFamilyId=");
		sb.append(itemFamilyId);
		sb.append(", strength=");
		sb.append(strength);
		sb.append(", itemCategory=");
		sb.append(itemCategory);
		sb.append(", upps=");
		sb.append(upps);
		sb.append(", shelfLifeType=");
		sb.append(shelfLifeType);
		sb.append(", pediatricExclusiveIndicator=");
		sb.append(pediatricExclusiveIndicator);
		sb.append(", recordLockStatus=");
		sb.append(recordLockStatus);
		sb.append(", itemTypeIndication=");
		sb.append(itemTypeIndication);
		sb.append(", acquisitionDate=");
		sb.append(acquisitionDate);
		sb.append(", clottingFactorIndicator=");
		sb.append(clottingFactorIndicator);
		sb.append(", form=");
		sb.append(form);
		sb.append(", itemName=");
		sb.append(itemName);
		sb.append(", shelfLife=");
		sb.append(shelfLife);
		sb.append(", pediatricExclusiveStartDate=");
		sb.append(pediatricExclusiveStartDate);
		sb.append(", firstSaleDate=");
		sb.append(firstSaleDate);
		sb.append(", itemMasterSid=");
		sb.append(itemMasterSid);
		sb.append(", itemType=");
		sb.append(itemType);
		sb.append(", itemId=");
		sb.append(itemId);
		sb.append(", brandMasterSid=");
		sb.append(brandMasterSid);
		sb.append(", baselineAmp=");
		sb.append(baselineAmp);
		sb.append(", dualPricingIndicator=");
		sb.append(dualPricingIndicator);
		sb.append(", dosesPerUnit=");
		sb.append(dosesPerUnit);
		sb.append(", createdDate=");
		sb.append(createdDate);
		sb.append(", createdBy=");
		sb.append(createdBy);
		sb.append(", authorizedGeneric=");
		sb.append(authorizedGeneric);
		sb.append(", itemStartDate=");
		sb.append(itemStartDate);
		sb.append(", ndc9=");
		sb.append(ndc9);
		sb.append(", authorizedGenericEndDate=");
		sb.append(authorizedGenericEndDate);
		sb.append(", itemNo=");
		sb.append(itemNo);
		sb.append(", packageSize=");
		sb.append(packageSize);
		sb.append(", ndc8=");
		sb.append(ndc8);
		sb.append(", baseCpi=");
		sb.append(baseCpi);
		sb.append(", labelerCode=");
		sb.append(labelerCode);
		sb.append(", itemClass=");
		sb.append(itemClass);
		sb.append(", clottingFactorEndDate=");
		sb.append(clottingFactorEndDate);
		sb.append(", dra=");
		sb.append(dra);
		sb.append(", baseCpiPeriod=");
		sb.append(baseCpiPeriod);
		sb.append(", newFormulationIndicator=");
		sb.append(newFormulationIndicator);
		sb.append(", lastLotExpirationDate=");
		sb.append(lastLotExpirationDate);
		sb.append(", batchId=");
		sb.append(batchId);
		sb.append(", itemCode=");
		sb.append(itemCode);
		sb.append(", clottingFactorStartDate=");
		sb.append(clottingFactorStartDate);
		sb.append(", nonFederalExpirationDate=");
		sb.append(nonFederalExpirationDate);
		sb.append(", internalNotes=");
		sb.append(internalNotes);
		sb.append(", baseCpiPrecision=");
		sb.append(baseCpiPrecision);
		sb.append(", baselineAmpPrecision=");
		sb.append(baselineAmpPrecision);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ItemMaster toEntityModel() {
		ItemMasterImpl itemMasterImpl = new ItemMasterImpl();

		itemMasterImpl.setItemStatus(itemStatus);

		if (itemDesc == null) {
			itemMasterImpl.setItemDesc(StringPool.BLANK);
		}
		else {
			itemMasterImpl.setItemDesc(itemDesc);
		}

		if (authorizedGenericStartDate == Long.MIN_VALUE) {
			itemMasterImpl.setAuthorizedGenericStartDate(null);
		}
		else {
			itemMasterImpl.setAuthorizedGenericStartDate(new Date(
					authorizedGenericStartDate));
		}

		itemMasterImpl.setAcquiredAmp(acquiredAmp);

		if (newFormulationStartDate == Long.MIN_VALUE) {
			itemMasterImpl.setNewFormulationStartDate(null);
		}
		else {
			itemMasterImpl.setNewFormulationStartDate(new Date(
					newFormulationStartDate));
		}

		if (marketTerminationDate == Long.MIN_VALUE) {
			itemMasterImpl.setMarketTerminationDate(null);
		}
		else {
			itemMasterImpl.setMarketTerminationDate(new Date(
					marketTerminationDate));
		}

		itemMasterImpl.setObraBamp(obraBamp);

		if (modifiedDate == Long.MIN_VALUE) {
			itemMasterImpl.setModifiedDate(null);
		}
		else {
			itemMasterImpl.setModifiedDate(new Date(modifiedDate));
		}

		itemMasterImpl.setTherapeuticClass(therapeuticClass);
		itemMasterImpl.setOrganizationKey(organizationKey);
		itemMasterImpl.setAcquiredBamp(acquiredBamp);

		if (pediatricExclusiveEndDate == Long.MIN_VALUE) {
			itemMasterImpl.setPediatricExclusiveEndDate(null);
		}
		else {
			itemMasterImpl.setPediatricExclusiveEndDate(new Date(
					pediatricExclusiveEndDate));
		}

		if (source == null) {
			itemMasterImpl.setSource(StringPool.BLANK);
		}
		else {
			itemMasterImpl.setSource(source);
		}

		if (newFormulation == null) {
			itemMasterImpl.setNewFormulation(StringPool.BLANK);
		}
		else {
			itemMasterImpl.setNewFormulation(newFormulation);
		}

		if (divestitureDate == Long.MIN_VALUE) {
			itemMasterImpl.setDivestitureDate(null);
		}
		else {
			itemMasterImpl.setDivestitureDate(new Date(divestitureDate));
		}

		itemMasterImpl.setPrimaryUom(primaryUom);

		if (newFormulationEndDate == Long.MIN_VALUE) {
			itemMasterImpl.setNewFormulationEndDate(null);
		}
		else {
			itemMasterImpl.setNewFormulationEndDate(new Date(
					newFormulationEndDate));
		}

		itemMasterImpl.setModifiedBy(modifiedBy);

		if (inboundStatus == null) {
			itemMasterImpl.setInboundStatus(StringPool.BLANK);
		}
		else {
			itemMasterImpl.setInboundStatus(inboundStatus);
		}

		if (packageSizeCode == null) {
			itemMasterImpl.setPackageSizeCode(StringPool.BLANK);
		}
		else {
			itemMasterImpl.setPackageSizeCode(packageSizeCode);
		}

		itemMasterImpl.setSecondaryUom(secondaryUom);

		if (discontinuationDate == Long.MIN_VALUE) {
			itemMasterImpl.setDiscontinuationDate(null);
		}
		else {
			itemMasterImpl.setDiscontinuationDate(new Date(discontinuationDate));
		}

		if (packageSizeIntroDate == Long.MIN_VALUE) {
			itemMasterImpl.setPackageSizeIntroDate(null);
		}
		else {
			itemMasterImpl.setPackageSizeIntroDate(new Date(
					packageSizeIntroDate));
		}

		if (manufacturerId == null) {
			itemMasterImpl.setManufacturerId(StringPool.BLANK);
		}
		else {
			itemMasterImpl.setManufacturerId(manufacturerId);
		}

		if (itemEndDate == Long.MIN_VALUE) {
			itemMasterImpl.setItemEndDate(null);
		}
		else {
			itemMasterImpl.setItemEndDate(new Date(itemEndDate));
		}

		if (itemFamilyId == null) {
			itemMasterImpl.setItemFamilyId(StringPool.BLANK);
		}
		else {
			itemMasterImpl.setItemFamilyId(itemFamilyId);
		}

		itemMasterImpl.setStrength(strength);
		itemMasterImpl.setItemCategory(itemCategory);
		itemMasterImpl.setUpps(upps);
		itemMasterImpl.setShelfLifeType(shelfLifeType);

		if (pediatricExclusiveIndicator == null) {
			itemMasterImpl.setPediatricExclusiveIndicator(StringPool.BLANK);
		}
		else {
			itemMasterImpl.setPediatricExclusiveIndicator(pediatricExclusiveIndicator);
		}

		itemMasterImpl.setRecordLockStatus(recordLockStatus);

		if (itemTypeIndication == null) {
			itemMasterImpl.setItemTypeIndication(StringPool.BLANK);
		}
		else {
			itemMasterImpl.setItemTypeIndication(itemTypeIndication);
		}

		if (acquisitionDate == Long.MIN_VALUE) {
			itemMasterImpl.setAcquisitionDate(null);
		}
		else {
			itemMasterImpl.setAcquisitionDate(new Date(acquisitionDate));
		}

		if (clottingFactorIndicator == null) {
			itemMasterImpl.setClottingFactorIndicator(StringPool.BLANK);
		}
		else {
			itemMasterImpl.setClottingFactorIndicator(clottingFactorIndicator);
		}

		itemMasterImpl.setForm(form);

		if (itemName == null) {
			itemMasterImpl.setItemName(StringPool.BLANK);
		}
		else {
			itemMasterImpl.setItemName(itemName);
		}

		if (shelfLife == null) {
			itemMasterImpl.setShelfLife(StringPool.BLANK);
		}
		else {
			itemMasterImpl.setShelfLife(shelfLife);
		}

		if (pediatricExclusiveStartDate == Long.MIN_VALUE) {
			itemMasterImpl.setPediatricExclusiveStartDate(null);
		}
		else {
			itemMasterImpl.setPediatricExclusiveStartDate(new Date(
					pediatricExclusiveStartDate));
		}

		if (firstSaleDate == Long.MIN_VALUE) {
			itemMasterImpl.setFirstSaleDate(null);
		}
		else {
			itemMasterImpl.setFirstSaleDate(new Date(firstSaleDate));
		}

		itemMasterImpl.setItemMasterSid(itemMasterSid);
		itemMasterImpl.setItemType(itemType);

		if (itemId == null) {
			itemMasterImpl.setItemId(StringPool.BLANK);
		}
		else {
			itemMasterImpl.setItemId(itemId);
		}

		itemMasterImpl.setBrandMasterSid(brandMasterSid);
		itemMasterImpl.setBaselineAmp(baselineAmp);

		if (dualPricingIndicator == null) {
			itemMasterImpl.setDualPricingIndicator(StringPool.BLANK);
		}
		else {
			itemMasterImpl.setDualPricingIndicator(dualPricingIndicator);
		}

		if (dosesPerUnit == null) {
			itemMasterImpl.setDosesPerUnit(StringPool.BLANK);
		}
		else {
			itemMasterImpl.setDosesPerUnit(dosesPerUnit);
		}

		if (createdDate == Long.MIN_VALUE) {
			itemMasterImpl.setCreatedDate(null);
		}
		else {
			itemMasterImpl.setCreatedDate(new Date(createdDate));
		}

		itemMasterImpl.setCreatedBy(createdBy);

		if (authorizedGeneric == null) {
			itemMasterImpl.setAuthorizedGeneric(StringPool.BLANK);
		}
		else {
			itemMasterImpl.setAuthorizedGeneric(authorizedGeneric);
		}

		if (itemStartDate == Long.MIN_VALUE) {
			itemMasterImpl.setItemStartDate(null);
		}
		else {
			itemMasterImpl.setItemStartDate(new Date(itemStartDate));
		}

		if (ndc9 == null) {
			itemMasterImpl.setNdc9(StringPool.BLANK);
		}
		else {
			itemMasterImpl.setNdc9(ndc9);
		}

		if (authorizedGenericEndDate == Long.MIN_VALUE) {
			itemMasterImpl.setAuthorizedGenericEndDate(null);
		}
		else {
			itemMasterImpl.setAuthorizedGenericEndDate(new Date(
					authorizedGenericEndDate));
		}

		if (itemNo == null) {
			itemMasterImpl.setItemNo(StringPool.BLANK);
		}
		else {
			itemMasterImpl.setItemNo(itemNo);
		}

		if (packageSize == null) {
			itemMasterImpl.setPackageSize(StringPool.BLANK);
		}
		else {
			itemMasterImpl.setPackageSize(packageSize);
		}

		if (ndc8 == null) {
			itemMasterImpl.setNdc8(StringPool.BLANK);
		}
		else {
			itemMasterImpl.setNdc8(ndc8);
		}

		itemMasterImpl.setBaseCpi(baseCpi);

		if (labelerCode == null) {
			itemMasterImpl.setLabelerCode(StringPool.BLANK);
		}
		else {
			itemMasterImpl.setLabelerCode(labelerCode);
		}

		itemMasterImpl.setItemClass(itemClass);

		if (clottingFactorEndDate == Long.MIN_VALUE) {
			itemMasterImpl.setClottingFactorEndDate(null);
		}
		else {
			itemMasterImpl.setClottingFactorEndDate(new Date(
					clottingFactorEndDate));
		}

		itemMasterImpl.setDra(dra);

		if (baseCpiPeriod == Long.MIN_VALUE) {
			itemMasterImpl.setBaseCpiPeriod(null);
		}
		else {
			itemMasterImpl.setBaseCpiPeriod(new Date(baseCpiPeriod));
		}

		if (newFormulationIndicator == null) {
			itemMasterImpl.setNewFormulationIndicator(StringPool.BLANK);
		}
		else {
			itemMasterImpl.setNewFormulationIndicator(newFormulationIndicator);
		}

		if (lastLotExpirationDate == Long.MIN_VALUE) {
			itemMasterImpl.setLastLotExpirationDate(null);
		}
		else {
			itemMasterImpl.setLastLotExpirationDate(new Date(
					lastLotExpirationDate));
		}

		if (batchId == null) {
			itemMasterImpl.setBatchId(StringPool.BLANK);
		}
		else {
			itemMasterImpl.setBatchId(batchId);
		}

		if (itemCode == null) {
			itemMasterImpl.setItemCode(StringPool.BLANK);
		}
		else {
			itemMasterImpl.setItemCode(itemCode);
		}

		if (clottingFactorStartDate == Long.MIN_VALUE) {
			itemMasterImpl.setClottingFactorStartDate(null);
		}
		else {
			itemMasterImpl.setClottingFactorStartDate(new Date(
					clottingFactorStartDate));
		}

		if (nonFederalExpirationDate == Long.MIN_VALUE) {
			itemMasterImpl.setNonFederalExpirationDate(null);
		}
		else {
			itemMasterImpl.setNonFederalExpirationDate(new Date(
					nonFederalExpirationDate));
		}

		if (internalNotes == null) {
			itemMasterImpl.setInternalNotes(StringPool.BLANK);
		}
		else {
			itemMasterImpl.setInternalNotes(internalNotes);
		}

		itemMasterImpl.setBaseCpiPrecision(baseCpiPrecision);
		itemMasterImpl.setBaselineAmpPrecision(baselineAmpPrecision);

		itemMasterImpl.resetOriginalValues();

		return itemMasterImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		itemStatus = objectInput.readInt();
		itemDesc = objectInput.readUTF();
		authorizedGenericStartDate = objectInput.readLong();

		acquiredAmp = objectInput.readDouble();
		newFormulationStartDate = objectInput.readLong();
		marketTerminationDate = objectInput.readLong();

		obraBamp = objectInput.readDouble();
		modifiedDate = objectInput.readLong();

		therapeuticClass = objectInput.readInt();

		organizationKey = objectInput.readInt();

		acquiredBamp = objectInput.readDouble();
		pediatricExclusiveEndDate = objectInput.readLong();
		source = objectInput.readUTF();
		newFormulation = objectInput.readUTF();
		divestitureDate = objectInput.readLong();

		primaryUom = objectInput.readInt();
		newFormulationEndDate = objectInput.readLong();

		modifiedBy = objectInput.readInt();
		inboundStatus = objectInput.readUTF();
		packageSizeCode = objectInput.readUTF();

		secondaryUom = objectInput.readInt();
		discontinuationDate = objectInput.readLong();
		packageSizeIntroDate = objectInput.readLong();
		manufacturerId = objectInput.readUTF();
		itemEndDate = objectInput.readLong();
		itemFamilyId = objectInput.readUTF();

		strength = objectInput.readInt();

		itemCategory = objectInput.readInt();

		upps = objectInput.readDouble();

		shelfLifeType = objectInput.readInt();
		pediatricExclusiveIndicator = objectInput.readUTF();

		recordLockStatus = objectInput.readBoolean();
		itemTypeIndication = objectInput.readUTF();
		acquisitionDate = objectInput.readLong();
		clottingFactorIndicator = objectInput.readUTF();

		form = objectInput.readInt();
		itemName = objectInput.readUTF();
		shelfLife = objectInput.readUTF();
		pediatricExclusiveStartDate = objectInput.readLong();
		firstSaleDate = objectInput.readLong();

		itemMasterSid = objectInput.readInt();

		itemType = objectInput.readInt();
		itemId = objectInput.readUTF();

		brandMasterSid = objectInput.readInt();

		baselineAmp = objectInput.readDouble();
		dualPricingIndicator = objectInput.readUTF();
		dosesPerUnit = objectInput.readUTF();
		createdDate = objectInput.readLong();

		createdBy = objectInput.readInt();
		authorizedGeneric = objectInput.readUTF();
		itemStartDate = objectInput.readLong();
		ndc9 = objectInput.readUTF();
		authorizedGenericEndDate = objectInput.readLong();
		itemNo = objectInput.readUTF();
		packageSize = objectInput.readUTF();
		ndc8 = objectInput.readUTF();

		baseCpi = objectInput.readDouble();
		labelerCode = objectInput.readUTF();

		itemClass = objectInput.readInt();
		clottingFactorEndDate = objectInput.readLong();

		dra = objectInput.readDouble();
		baseCpiPeriod = objectInput.readLong();
		newFormulationIndicator = objectInput.readUTF();
		lastLotExpirationDate = objectInput.readLong();
		batchId = objectInput.readUTF();
		itemCode = objectInput.readUTF();
		clottingFactorStartDate = objectInput.readLong();
		nonFederalExpirationDate = objectInput.readLong();
		internalNotes = objectInput.readUTF();

		baseCpiPrecision = objectInput.readInt();

		baselineAmpPrecision = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeInt(itemStatus);

		if (itemDesc == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(itemDesc);
		}

		objectOutput.writeLong(authorizedGenericStartDate);

		objectOutput.writeDouble(acquiredAmp);
		objectOutput.writeLong(newFormulationStartDate);
		objectOutput.writeLong(marketTerminationDate);

		objectOutput.writeDouble(obraBamp);
		objectOutput.writeLong(modifiedDate);

		objectOutput.writeInt(therapeuticClass);

		objectOutput.writeInt(organizationKey);

		objectOutput.writeDouble(acquiredBamp);
		objectOutput.writeLong(pediatricExclusiveEndDate);

		if (source == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(source);
		}

		if (newFormulation == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(newFormulation);
		}

		objectOutput.writeLong(divestitureDate);

		objectOutput.writeInt(primaryUom);
		objectOutput.writeLong(newFormulationEndDate);

		objectOutput.writeInt(modifiedBy);

		if (inboundStatus == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(inboundStatus);
		}

		if (packageSizeCode == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(packageSizeCode);
		}

		objectOutput.writeInt(secondaryUom);
		objectOutput.writeLong(discontinuationDate);
		objectOutput.writeLong(packageSizeIntroDate);

		if (manufacturerId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(manufacturerId);
		}

		objectOutput.writeLong(itemEndDate);

		if (itemFamilyId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(itemFamilyId);
		}

		objectOutput.writeInt(strength);

		objectOutput.writeInt(itemCategory);

		objectOutput.writeDouble(upps);

		objectOutput.writeInt(shelfLifeType);

		if (pediatricExclusiveIndicator == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(pediatricExclusiveIndicator);
		}

		objectOutput.writeBoolean(recordLockStatus);

		if (itemTypeIndication == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(itemTypeIndication);
		}

		objectOutput.writeLong(acquisitionDate);

		if (clottingFactorIndicator == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(clottingFactorIndicator);
		}

		objectOutput.writeInt(form);

		if (itemName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(itemName);
		}

		if (shelfLife == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(shelfLife);
		}

		objectOutput.writeLong(pediatricExclusiveStartDate);
		objectOutput.writeLong(firstSaleDate);

		objectOutput.writeInt(itemMasterSid);

		objectOutput.writeInt(itemType);

		if (itemId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(itemId);
		}

		objectOutput.writeInt(brandMasterSid);

		objectOutput.writeDouble(baselineAmp);

		if (dualPricingIndicator == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(dualPricingIndicator);
		}

		if (dosesPerUnit == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(dosesPerUnit);
		}

		objectOutput.writeLong(createdDate);

		objectOutput.writeInt(createdBy);

		if (authorizedGeneric == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(authorizedGeneric);
		}

		objectOutput.writeLong(itemStartDate);

		if (ndc9 == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(ndc9);
		}

		objectOutput.writeLong(authorizedGenericEndDate);

		if (itemNo == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(itemNo);
		}

		if (packageSize == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(packageSize);
		}

		if (ndc8 == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(ndc8);
		}

		objectOutput.writeDouble(baseCpi);

		if (labelerCode == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(labelerCode);
		}

		objectOutput.writeInt(itemClass);
		objectOutput.writeLong(clottingFactorEndDate);

		objectOutput.writeDouble(dra);
		objectOutput.writeLong(baseCpiPeriod);

		if (newFormulationIndicator == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(newFormulationIndicator);
		}

		objectOutput.writeLong(lastLotExpirationDate);

		if (batchId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(batchId);
		}

		if (itemCode == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(itemCode);
		}

		objectOutput.writeLong(clottingFactorStartDate);
		objectOutput.writeLong(nonFederalExpirationDate);

		if (internalNotes == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(internalNotes);
		}

		objectOutput.writeInt(baseCpiPrecision);

		objectOutput.writeInt(baselineAmpPrecision);
	}

	public int itemStatus;
	public String itemDesc;
	public long authorizedGenericStartDate;
	public double acquiredAmp;
	public long newFormulationStartDate;
	public long marketTerminationDate;
	public double obraBamp;
	public long modifiedDate;
	public int therapeuticClass;
	public int organizationKey;
	public double acquiredBamp;
	public long pediatricExclusiveEndDate;
	public String source;
	public String newFormulation;
	public long divestitureDate;
	public int primaryUom;
	public long newFormulationEndDate;
	public int modifiedBy;
	public String inboundStatus;
	public String packageSizeCode;
	public int secondaryUom;
	public long discontinuationDate;
	public long packageSizeIntroDate;
	public String manufacturerId;
	public long itemEndDate;
	public String itemFamilyId;
	public int strength;
	public int itemCategory;
	public double upps;
	public int shelfLifeType;
	public String pediatricExclusiveIndicator;
	public boolean recordLockStatus;
	public String itemTypeIndication;
	public long acquisitionDate;
	public String clottingFactorIndicator;
	public int form;
	public String itemName;
	public String shelfLife;
	public long pediatricExclusiveStartDate;
	public long firstSaleDate;
	public int itemMasterSid;
	public int itemType;
	public String itemId;
	public int brandMasterSid;
	public double baselineAmp;
	public String dualPricingIndicator;
	public String dosesPerUnit;
	public long createdDate;
	public int createdBy;
	public String authorizedGeneric;
	public long itemStartDate;
	public String ndc9;
	public long authorizedGenericEndDate;
	public String itemNo;
	public String packageSize;
	public String ndc8;
	public double baseCpi;
	public String labelerCode;
	public int itemClass;
	public long clottingFactorEndDate;
	public double dra;
	public long baseCpiPeriod;
	public String newFormulationIndicator;
	public long lastLotExpirationDate;
	public String batchId;
	public String itemCode;
	public long clottingFactorStartDate;
	public long nonFederalExpirationDate;
	public String internalNotes;
	public int baseCpiPrecision;
	public int baselineAmpPrecision;
}