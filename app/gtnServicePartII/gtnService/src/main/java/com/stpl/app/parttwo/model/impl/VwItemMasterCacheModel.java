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

import com.stpl.app.parttwo.model.VwItemMaster;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing VwItemMaster in entity cache.
 *
 * @author
 * @see VwItemMaster
 * @generated
 */
@ProviderType
public class VwItemMasterCacheModel implements CacheModel<VwItemMaster>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof VwItemMasterCacheModel)) {
			return false;
		}

		VwItemMasterCacheModel vwItemMasterCacheModel = (VwItemMasterCacheModel)obj;

		if (itemMasterSid == vwItemMasterCacheModel.itemMasterSid) {
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
		StringBundler sb = new StringBundler(159);

		sb.append("{itemStatus=");
		sb.append(itemStatus);
		sb.append(", itemDesc=");
		sb.append(itemDesc);
		sb.append(", acquiredAmp=");
		sb.append(acquiredAmp);
		sb.append(", authorizedGenericStartDate=");
		sb.append(authorizedGenericStartDate);
		sb.append(", newFormulationStartDate=");
		sb.append(newFormulationStartDate);
		sb.append(", marketTerminationDate=");
		sb.append(marketTerminationDate);
		sb.append(", obraBamp=");
		sb.append(obraBamp);
		sb.append(", brand=");
		sb.append(brand);
		sb.append(", manufacturerNo=");
		sb.append(manufacturerNo);
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
		sb.append(", addChgDelIndicator=");
		sb.append(addChgDelIndicator);
		sb.append(", divestitureDate=");
		sb.append(divestitureDate);
		sb.append(", shelfLife=");
		sb.append(shelfLife);
		sb.append(", primaryUom=");
		sb.append(primaryUom);
		sb.append(", newFormulationEndDate=");
		sb.append(newFormulationEndDate);
		sb.append(", modifiedBy=");
		sb.append(modifiedBy);
		sb.append(", packageSizeCode=");
		sb.append(packageSizeCode);
		sb.append(", secondaryUom=");
		sb.append(secondaryUom);
		sb.append(", udc6=");
		sb.append(udc6);
		sb.append(", udc5=");
		sb.append(udc5);
		sb.append(", discontinuationDate=");
		sb.append(discontinuationDate);
		sb.append(", udc4=");
		sb.append(udc4);
		sb.append(", udc1=");
		sb.append(udc1);
		sb.append(", udc2=");
		sb.append(udc2);
		sb.append(", packageSizeIntroDate=");
		sb.append(packageSizeIntroDate);
		sb.append(", udc3=");
		sb.append(udc3);
		sb.append(", itemEndDate=");
		sb.append(itemEndDate);
		sb.append(", manufacturerId=");
		sb.append(manufacturerId);
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
		sb.append(", manufacturerName=");
		sb.append(manufacturerName);
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
		sb.append(", baselineAmp=");
		sb.append(baselineAmp);
		sb.append(", dosesPerUnit=");
		sb.append(dosesPerUnit);
		sb.append(", dualPricingIndicator=");
		sb.append(dualPricingIndicator);
		sb.append(", baseCpi=");
		sb.append(baseCpi);
		sb.append(", createdDate=");
		sb.append(createdDate);
		sb.append(", createdBy=");
		sb.append(createdBy);
		sb.append(", itemStartDate=");
		sb.append(itemStartDate);
		sb.append(", authorizedGeneric=");
		sb.append(authorizedGeneric);
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
		sb.append(", itemClass=");
		sb.append(itemClass);
		sb.append(", labelerCode=");
		sb.append(labelerCode);
		sb.append(", displayBrand=");
		sb.append(displayBrand);
		sb.append(", clottingFactorEndDate=");
		sb.append(clottingFactorEndDate);
		sb.append(", dra=");
		sb.append(dra);
		sb.append(", brandId=");
		sb.append(brandId);
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
		sb.append(", baseCpiPrecision=");
		sb.append(baseCpiPrecision);
		sb.append(", baselineAmpPrecision=");
		sb.append(baselineAmpPrecision);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public VwItemMaster toEntityModel() {
		VwItemMasterImpl vwItemMasterImpl = new VwItemMasterImpl();

		if (itemStatus == null) {
			vwItemMasterImpl.setItemStatus(StringPool.BLANK);
		}
		else {
			vwItemMasterImpl.setItemStatus(itemStatus);
		}

		if (itemDesc == null) {
			vwItemMasterImpl.setItemDesc(StringPool.BLANK);
		}
		else {
			vwItemMasterImpl.setItemDesc(itemDesc);
		}

		if (acquiredAmp == null) {
			vwItemMasterImpl.setAcquiredAmp(StringPool.BLANK);
		}
		else {
			vwItemMasterImpl.setAcquiredAmp(acquiredAmp);
		}

		if (authorizedGenericStartDate == Long.MIN_VALUE) {
			vwItemMasterImpl.setAuthorizedGenericStartDate(null);
		}
		else {
			vwItemMasterImpl.setAuthorizedGenericStartDate(new Date(
					authorizedGenericStartDate));
		}

		if (newFormulationStartDate == Long.MIN_VALUE) {
			vwItemMasterImpl.setNewFormulationStartDate(null);
		}
		else {
			vwItemMasterImpl.setNewFormulationStartDate(new Date(
					newFormulationStartDate));
		}

		if (marketTerminationDate == Long.MIN_VALUE) {
			vwItemMasterImpl.setMarketTerminationDate(null);
		}
		else {
			vwItemMasterImpl.setMarketTerminationDate(new Date(
					marketTerminationDate));
		}

		if (obraBamp == null) {
			vwItemMasterImpl.setObraBamp(StringPool.BLANK);
		}
		else {
			vwItemMasterImpl.setObraBamp(obraBamp);
		}

		if (brand == null) {
			vwItemMasterImpl.setBrand(StringPool.BLANK);
		}
		else {
			vwItemMasterImpl.setBrand(brand);
		}

		if (manufacturerNo == null) {
			vwItemMasterImpl.setManufacturerNo(StringPool.BLANK);
		}
		else {
			vwItemMasterImpl.setManufacturerNo(manufacturerNo);
		}

		if (modifiedDate == Long.MIN_VALUE) {
			vwItemMasterImpl.setModifiedDate(null);
		}
		else {
			vwItemMasterImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (therapeuticClass == null) {
			vwItemMasterImpl.setTherapeuticClass(StringPool.BLANK);
		}
		else {
			vwItemMasterImpl.setTherapeuticClass(therapeuticClass);
		}

		if (organizationKey == null) {
			vwItemMasterImpl.setOrganizationKey(StringPool.BLANK);
		}
		else {
			vwItemMasterImpl.setOrganizationKey(organizationKey);
		}

		if (acquiredBamp == null) {
			vwItemMasterImpl.setAcquiredBamp(StringPool.BLANK);
		}
		else {
			vwItemMasterImpl.setAcquiredBamp(acquiredBamp);
		}

		if (pediatricExclusiveEndDate == Long.MIN_VALUE) {
			vwItemMasterImpl.setPediatricExclusiveEndDate(null);
		}
		else {
			vwItemMasterImpl.setPediatricExclusiveEndDate(new Date(
					pediatricExclusiveEndDate));
		}

		if (source == null) {
			vwItemMasterImpl.setSource(StringPool.BLANK);
		}
		else {
			vwItemMasterImpl.setSource(source);
		}

		if (newFormulation == null) {
			vwItemMasterImpl.setNewFormulation(StringPool.BLANK);
		}
		else {
			vwItemMasterImpl.setNewFormulation(newFormulation);
		}

		if (addChgDelIndicator == null) {
			vwItemMasterImpl.setAddChgDelIndicator(StringPool.BLANK);
		}
		else {
			vwItemMasterImpl.setAddChgDelIndicator(addChgDelIndicator);
		}

		if (divestitureDate == Long.MIN_VALUE) {
			vwItemMasterImpl.setDivestitureDate(null);
		}
		else {
			vwItemMasterImpl.setDivestitureDate(new Date(divestitureDate));
		}

		if (shelfLife == null) {
			vwItemMasterImpl.setShelfLife(StringPool.BLANK);
		}
		else {
			vwItemMasterImpl.setShelfLife(shelfLife);
		}

		if (primaryUom == null) {
			vwItemMasterImpl.setPrimaryUom(StringPool.BLANK);
		}
		else {
			vwItemMasterImpl.setPrimaryUom(primaryUom);
		}

		if (newFormulationEndDate == Long.MIN_VALUE) {
			vwItemMasterImpl.setNewFormulationEndDate(null);
		}
		else {
			vwItemMasterImpl.setNewFormulationEndDate(new Date(
					newFormulationEndDate));
		}

		if (modifiedBy == null) {
			vwItemMasterImpl.setModifiedBy(StringPool.BLANK);
		}
		else {
			vwItemMasterImpl.setModifiedBy(modifiedBy);
		}

		if (packageSizeCode == null) {
			vwItemMasterImpl.setPackageSizeCode(StringPool.BLANK);
		}
		else {
			vwItemMasterImpl.setPackageSizeCode(packageSizeCode);
		}

		if (secondaryUom == null) {
			vwItemMasterImpl.setSecondaryUom(StringPool.BLANK);
		}
		else {
			vwItemMasterImpl.setSecondaryUom(secondaryUom);
		}

		if (udc6 == null) {
			vwItemMasterImpl.setUdc6(StringPool.BLANK);
		}
		else {
			vwItemMasterImpl.setUdc6(udc6);
		}

		if (udc5 == null) {
			vwItemMasterImpl.setUdc5(StringPool.BLANK);
		}
		else {
			vwItemMasterImpl.setUdc5(udc5);
		}

		if (discontinuationDate == Long.MIN_VALUE) {
			vwItemMasterImpl.setDiscontinuationDate(null);
		}
		else {
			vwItemMasterImpl.setDiscontinuationDate(new Date(
					discontinuationDate));
		}

		if (udc4 == null) {
			vwItemMasterImpl.setUdc4(StringPool.BLANK);
		}
		else {
			vwItemMasterImpl.setUdc4(udc4);
		}

		if (udc1 == null) {
			vwItemMasterImpl.setUdc1(StringPool.BLANK);
		}
		else {
			vwItemMasterImpl.setUdc1(udc1);
		}

		if (udc2 == null) {
			vwItemMasterImpl.setUdc2(StringPool.BLANK);
		}
		else {
			vwItemMasterImpl.setUdc2(udc2);
		}

		if (packageSizeIntroDate == Long.MIN_VALUE) {
			vwItemMasterImpl.setPackageSizeIntroDate(null);
		}
		else {
			vwItemMasterImpl.setPackageSizeIntroDate(new Date(
					packageSizeIntroDate));
		}

		if (udc3 == null) {
			vwItemMasterImpl.setUdc3(StringPool.BLANK);
		}
		else {
			vwItemMasterImpl.setUdc3(udc3);
		}

		if (itemEndDate == Long.MIN_VALUE) {
			vwItemMasterImpl.setItemEndDate(null);
		}
		else {
			vwItemMasterImpl.setItemEndDate(new Date(itemEndDate));
		}

		if (manufacturerId == null) {
			vwItemMasterImpl.setManufacturerId(StringPool.BLANK);
		}
		else {
			vwItemMasterImpl.setManufacturerId(manufacturerId);
		}

		if (itemFamilyId == null) {
			vwItemMasterImpl.setItemFamilyId(StringPool.BLANK);
		}
		else {
			vwItemMasterImpl.setItemFamilyId(itemFamilyId);
		}

		if (strength == null) {
			vwItemMasterImpl.setStrength(StringPool.BLANK);
		}
		else {
			vwItemMasterImpl.setStrength(strength);
		}

		if (itemCategory == null) {
			vwItemMasterImpl.setItemCategory(StringPool.BLANK);
		}
		else {
			vwItemMasterImpl.setItemCategory(itemCategory);
		}

		vwItemMasterImpl.setUpps(upps);

		if (shelfLifeType == null) {
			vwItemMasterImpl.setShelfLifeType(StringPool.BLANK);
		}
		else {
			vwItemMasterImpl.setShelfLifeType(shelfLifeType);
		}

		if (pediatricExclusiveIndicator == null) {
			vwItemMasterImpl.setPediatricExclusiveIndicator(StringPool.BLANK);
		}
		else {
			vwItemMasterImpl.setPediatricExclusiveIndicator(pediatricExclusiveIndicator);
		}

		if (itemTypeIndication == null) {
			vwItemMasterImpl.setItemTypeIndication(StringPool.BLANK);
		}
		else {
			vwItemMasterImpl.setItemTypeIndication(itemTypeIndication);
		}

		if (acquisitionDate == Long.MIN_VALUE) {
			vwItemMasterImpl.setAcquisitionDate(null);
		}
		else {
			vwItemMasterImpl.setAcquisitionDate(new Date(acquisitionDate));
		}

		if (clottingFactorIndicator == null) {
			vwItemMasterImpl.setClottingFactorIndicator(StringPool.BLANK);
		}
		else {
			vwItemMasterImpl.setClottingFactorIndicator(clottingFactorIndicator);
		}

		if (form == null) {
			vwItemMasterImpl.setForm(StringPool.BLANK);
		}
		else {
			vwItemMasterImpl.setForm(form);
		}

		if (itemName == null) {
			vwItemMasterImpl.setItemName(StringPool.BLANK);
		}
		else {
			vwItemMasterImpl.setItemName(itemName);
		}

		if (manufacturerName == null) {
			vwItemMasterImpl.setManufacturerName(StringPool.BLANK);
		}
		else {
			vwItemMasterImpl.setManufacturerName(manufacturerName);
		}

		if (pediatricExclusiveStartDate == Long.MIN_VALUE) {
			vwItemMasterImpl.setPediatricExclusiveStartDate(null);
		}
		else {
			vwItemMasterImpl.setPediatricExclusiveStartDate(new Date(
					pediatricExclusiveStartDate));
		}

		if (firstSaleDate == Long.MIN_VALUE) {
			vwItemMasterImpl.setFirstSaleDate(null);
		}
		else {
			vwItemMasterImpl.setFirstSaleDate(new Date(firstSaleDate));
		}

		vwItemMasterImpl.setItemMasterSid(itemMasterSid);

		if (itemType == null) {
			vwItemMasterImpl.setItemType(StringPool.BLANK);
		}
		else {
			vwItemMasterImpl.setItemType(itemType);
		}

		if (itemId == null) {
			vwItemMasterImpl.setItemId(StringPool.BLANK);
		}
		else {
			vwItemMasterImpl.setItemId(itemId);
		}

		if (baselineAmp == null) {
			vwItemMasterImpl.setBaselineAmp(StringPool.BLANK);
		}
		else {
			vwItemMasterImpl.setBaselineAmp(baselineAmp);
		}

		if (dosesPerUnit == null) {
			vwItemMasterImpl.setDosesPerUnit(StringPool.BLANK);
		}
		else {
			vwItemMasterImpl.setDosesPerUnit(dosesPerUnit);
		}

		if (dualPricingIndicator == null) {
			vwItemMasterImpl.setDualPricingIndicator(StringPool.BLANK);
		}
		else {
			vwItemMasterImpl.setDualPricingIndicator(dualPricingIndicator);
		}

		if (baseCpi == null) {
			vwItemMasterImpl.setBaseCpi(StringPool.BLANK);
		}
		else {
			vwItemMasterImpl.setBaseCpi(baseCpi);
		}

		if (createdDate == Long.MIN_VALUE) {
			vwItemMasterImpl.setCreatedDate(null);
		}
		else {
			vwItemMasterImpl.setCreatedDate(new Date(createdDate));
		}

		if (createdBy == null) {
			vwItemMasterImpl.setCreatedBy(StringPool.BLANK);
		}
		else {
			vwItemMasterImpl.setCreatedBy(createdBy);
		}

		if (itemStartDate == Long.MIN_VALUE) {
			vwItemMasterImpl.setItemStartDate(null);
		}
		else {
			vwItemMasterImpl.setItemStartDate(new Date(itemStartDate));
		}

		if (authorizedGeneric == null) {
			vwItemMasterImpl.setAuthorizedGeneric(StringPool.BLANK);
		}
		else {
			vwItemMasterImpl.setAuthorizedGeneric(authorizedGeneric);
		}

		if (ndc9 == null) {
			vwItemMasterImpl.setNdc9(StringPool.BLANK);
		}
		else {
			vwItemMasterImpl.setNdc9(ndc9);
		}

		if (authorizedGenericEndDate == Long.MIN_VALUE) {
			vwItemMasterImpl.setAuthorizedGenericEndDate(null);
		}
		else {
			vwItemMasterImpl.setAuthorizedGenericEndDate(new Date(
					authorizedGenericEndDate));
		}

		if (itemNo == null) {
			vwItemMasterImpl.setItemNo(StringPool.BLANK);
		}
		else {
			vwItemMasterImpl.setItemNo(itemNo);
		}

		if (packageSize == null) {
			vwItemMasterImpl.setPackageSize(StringPool.BLANK);
		}
		else {
			vwItemMasterImpl.setPackageSize(packageSize);
		}

		if (ndc8 == null) {
			vwItemMasterImpl.setNdc8(StringPool.BLANK);
		}
		else {
			vwItemMasterImpl.setNdc8(ndc8);
		}

		if (itemClass == null) {
			vwItemMasterImpl.setItemClass(StringPool.BLANK);
		}
		else {
			vwItemMasterImpl.setItemClass(itemClass);
		}

		if (labelerCode == null) {
			vwItemMasterImpl.setLabelerCode(StringPool.BLANK);
		}
		else {
			vwItemMasterImpl.setLabelerCode(labelerCode);
		}

		if (displayBrand == null) {
			vwItemMasterImpl.setDisplayBrand(StringPool.BLANK);
		}
		else {
			vwItemMasterImpl.setDisplayBrand(displayBrand);
		}

		if (clottingFactorEndDate == Long.MIN_VALUE) {
			vwItemMasterImpl.setClottingFactorEndDate(null);
		}
		else {
			vwItemMasterImpl.setClottingFactorEndDate(new Date(
					clottingFactorEndDate));
		}

		if (dra == null) {
			vwItemMasterImpl.setDra(StringPool.BLANK);
		}
		else {
			vwItemMasterImpl.setDra(dra);
		}

		if (brandId == null) {
			vwItemMasterImpl.setBrandId(StringPool.BLANK);
		}
		else {
			vwItemMasterImpl.setBrandId(brandId);
		}

		if (baseCpiPeriod == Long.MIN_VALUE) {
			vwItemMasterImpl.setBaseCpiPeriod(null);
		}
		else {
			vwItemMasterImpl.setBaseCpiPeriod(new Date(baseCpiPeriod));
		}

		if (newFormulationIndicator == null) {
			vwItemMasterImpl.setNewFormulationIndicator(StringPool.BLANK);
		}
		else {
			vwItemMasterImpl.setNewFormulationIndicator(newFormulationIndicator);
		}

		if (lastLotExpirationDate == Long.MIN_VALUE) {
			vwItemMasterImpl.setLastLotExpirationDate(null);
		}
		else {
			vwItemMasterImpl.setLastLotExpirationDate(new Date(
					lastLotExpirationDate));
		}

		if (batchId == null) {
			vwItemMasterImpl.setBatchId(StringPool.BLANK);
		}
		else {
			vwItemMasterImpl.setBatchId(batchId);
		}

		if (itemCode == null) {
			vwItemMasterImpl.setItemCode(StringPool.BLANK);
		}
		else {
			vwItemMasterImpl.setItemCode(itemCode);
		}

		if (clottingFactorStartDate == Long.MIN_VALUE) {
			vwItemMasterImpl.setClottingFactorStartDate(null);
		}
		else {
			vwItemMasterImpl.setClottingFactorStartDate(new Date(
					clottingFactorStartDate));
		}

		if (nonFederalExpirationDate == Long.MIN_VALUE) {
			vwItemMasterImpl.setNonFederalExpirationDate(null);
		}
		else {
			vwItemMasterImpl.setNonFederalExpirationDate(new Date(
					nonFederalExpirationDate));
		}

		vwItemMasterImpl.setBaseCpiPrecision(baseCpiPrecision);
		vwItemMasterImpl.setBaselineAmpPrecision(baselineAmpPrecision);

		vwItemMasterImpl.resetOriginalValues();

		return vwItemMasterImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		itemStatus = objectInput.readUTF();
		itemDesc = objectInput.readUTF();
		acquiredAmp = objectInput.readUTF();
		authorizedGenericStartDate = objectInput.readLong();
		newFormulationStartDate = objectInput.readLong();
		marketTerminationDate = objectInput.readLong();
		obraBamp = objectInput.readUTF();
		brand = objectInput.readUTF();
		manufacturerNo = objectInput.readUTF();
		modifiedDate = objectInput.readLong();
		therapeuticClass = objectInput.readUTF();
		organizationKey = objectInput.readUTF();
		acquiredBamp = objectInput.readUTF();
		pediatricExclusiveEndDate = objectInput.readLong();
		source = objectInput.readUTF();
		newFormulation = objectInput.readUTF();
		addChgDelIndicator = objectInput.readUTF();
		divestitureDate = objectInput.readLong();
		shelfLife = objectInput.readUTF();
		primaryUom = objectInput.readUTF();
		newFormulationEndDate = objectInput.readLong();
		modifiedBy = objectInput.readUTF();
		packageSizeCode = objectInput.readUTF();
		secondaryUom = objectInput.readUTF();
		udc6 = objectInput.readUTF();
		udc5 = objectInput.readUTF();
		discontinuationDate = objectInput.readLong();
		udc4 = objectInput.readUTF();
		udc1 = objectInput.readUTF();
		udc2 = objectInput.readUTF();
		packageSizeIntroDate = objectInput.readLong();
		udc3 = objectInput.readUTF();
		itemEndDate = objectInput.readLong();
		manufacturerId = objectInput.readUTF();
		itemFamilyId = objectInput.readUTF();
		strength = objectInput.readUTF();
		itemCategory = objectInput.readUTF();

		upps = objectInput.readDouble();
		shelfLifeType = objectInput.readUTF();
		pediatricExclusiveIndicator = objectInput.readUTF();
		itemTypeIndication = objectInput.readUTF();
		acquisitionDate = objectInput.readLong();
		clottingFactorIndicator = objectInput.readUTF();
		form = objectInput.readUTF();
		itemName = objectInput.readUTF();
		manufacturerName = objectInput.readUTF();
		pediatricExclusiveStartDate = objectInput.readLong();
		firstSaleDate = objectInput.readLong();

		itemMasterSid = objectInput.readInt();
		itemType = objectInput.readUTF();
		itemId = objectInput.readUTF();
		baselineAmp = objectInput.readUTF();
		dosesPerUnit = objectInput.readUTF();
		dualPricingIndicator = objectInput.readUTF();
		baseCpi = objectInput.readUTF();
		createdDate = objectInput.readLong();
		createdBy = objectInput.readUTF();
		itemStartDate = objectInput.readLong();
		authorizedGeneric = objectInput.readUTF();
		ndc9 = objectInput.readUTF();
		authorizedGenericEndDate = objectInput.readLong();
		itemNo = objectInput.readUTF();
		packageSize = objectInput.readUTF();
		ndc8 = objectInput.readUTF();
		itemClass = objectInput.readUTF();
		labelerCode = objectInput.readUTF();
		displayBrand = objectInput.readUTF();
		clottingFactorEndDate = objectInput.readLong();
		dra = objectInput.readUTF();
		brandId = objectInput.readUTF();
		baseCpiPeriod = objectInput.readLong();
		newFormulationIndicator = objectInput.readUTF();
		lastLotExpirationDate = objectInput.readLong();
		batchId = objectInput.readUTF();
		itemCode = objectInput.readUTF();
		clottingFactorStartDate = objectInput.readLong();
		nonFederalExpirationDate = objectInput.readLong();

		baseCpiPrecision = objectInput.readInt();

		baselineAmpPrecision = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		if (itemStatus == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(itemStatus);
		}

		if (itemDesc == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(itemDesc);
		}

		if (acquiredAmp == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(acquiredAmp);
		}

		objectOutput.writeLong(authorizedGenericStartDate);
		objectOutput.writeLong(newFormulationStartDate);
		objectOutput.writeLong(marketTerminationDate);

		if (obraBamp == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(obraBamp);
		}

		if (brand == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(brand);
		}

		if (manufacturerNo == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(manufacturerNo);
		}

		objectOutput.writeLong(modifiedDate);

		if (therapeuticClass == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(therapeuticClass);
		}

		if (organizationKey == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(organizationKey);
		}

		if (acquiredBamp == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(acquiredBamp);
		}

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

		if (addChgDelIndicator == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(addChgDelIndicator);
		}

		objectOutput.writeLong(divestitureDate);

		if (shelfLife == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(shelfLife);
		}

		if (primaryUom == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(primaryUom);
		}

		objectOutput.writeLong(newFormulationEndDate);

		if (modifiedBy == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(modifiedBy);
		}

		if (packageSizeCode == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(packageSizeCode);
		}

		if (secondaryUom == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(secondaryUom);
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

		objectOutput.writeLong(discontinuationDate);

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

		objectOutput.writeLong(packageSizeIntroDate);

		if (udc3 == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(udc3);
		}

		objectOutput.writeLong(itemEndDate);

		if (manufacturerId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(manufacturerId);
		}

		if (itemFamilyId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(itemFamilyId);
		}

		if (strength == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(strength);
		}

		if (itemCategory == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(itemCategory);
		}

		objectOutput.writeDouble(upps);

		if (shelfLifeType == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(shelfLifeType);
		}

		if (pediatricExclusiveIndicator == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(pediatricExclusiveIndicator);
		}

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

		if (form == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(form);
		}

		if (itemName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(itemName);
		}

		if (manufacturerName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(manufacturerName);
		}

		objectOutput.writeLong(pediatricExclusiveStartDate);
		objectOutput.writeLong(firstSaleDate);

		objectOutput.writeInt(itemMasterSid);

		if (itemType == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(itemType);
		}

		if (itemId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(itemId);
		}

		if (baselineAmp == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(baselineAmp);
		}

		if (dosesPerUnit == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(dosesPerUnit);
		}

		if (dualPricingIndicator == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(dualPricingIndicator);
		}

		if (baseCpi == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(baseCpi);
		}

		objectOutput.writeLong(createdDate);

		if (createdBy == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(createdBy);
		}

		objectOutput.writeLong(itemStartDate);

		if (authorizedGeneric == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(authorizedGeneric);
		}

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

		if (itemClass == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(itemClass);
		}

		if (labelerCode == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(labelerCode);
		}

		if (displayBrand == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(displayBrand);
		}

		objectOutput.writeLong(clottingFactorEndDate);

		if (dra == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(dra);
		}

		if (brandId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(brandId);
		}

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

		objectOutput.writeInt(baseCpiPrecision);

		objectOutput.writeInt(baselineAmpPrecision);
	}

	public String itemStatus;
	public String itemDesc;
	public String acquiredAmp;
	public long authorizedGenericStartDate;
	public long newFormulationStartDate;
	public long marketTerminationDate;
	public String obraBamp;
	public String brand;
	public String manufacturerNo;
	public long modifiedDate;
	public String therapeuticClass;
	public String organizationKey;
	public String acquiredBamp;
	public long pediatricExclusiveEndDate;
	public String source;
	public String newFormulation;
	public String addChgDelIndicator;
	public long divestitureDate;
	public String shelfLife;
	public String primaryUom;
	public long newFormulationEndDate;
	public String modifiedBy;
	public String packageSizeCode;
	public String secondaryUom;
	public String udc6;
	public String udc5;
	public long discontinuationDate;
	public String udc4;
	public String udc1;
	public String udc2;
	public long packageSizeIntroDate;
	public String udc3;
	public long itemEndDate;
	public String manufacturerId;
	public String itemFamilyId;
	public String strength;
	public String itemCategory;
	public double upps;
	public String shelfLifeType;
	public String pediatricExclusiveIndicator;
	public String itemTypeIndication;
	public long acquisitionDate;
	public String clottingFactorIndicator;
	public String form;
	public String itemName;
	public String manufacturerName;
	public long pediatricExclusiveStartDate;
	public long firstSaleDate;
	public int itemMasterSid;
	public String itemType;
	public String itemId;
	public String baselineAmp;
	public String dosesPerUnit;
	public String dualPricingIndicator;
	public String baseCpi;
	public long createdDate;
	public String createdBy;
	public long itemStartDate;
	public String authorizedGeneric;
	public String ndc9;
	public long authorizedGenericEndDate;
	public String itemNo;
	public String packageSize;
	public String ndc8;
	public String itemClass;
	public String labelerCode;
	public String displayBrand;
	public long clottingFactorEndDate;
	public String dra;
	public String brandId;
	public long baseCpiPeriod;
	public String newFormulationIndicator;
	public long lastLotExpirationDate;
	public String batchId;
	public String itemCode;
	public long clottingFactorStartDate;
	public long nonFederalExpirationDate;
	public int baseCpiPrecision;
	public int baselineAmpPrecision;
}