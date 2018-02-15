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

package com.stpl.app.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link ItemMaster}.
 * </p>
 *
 * @author
 * @see ItemMaster
 * @generated
 */
@ProviderType
public class ItemMasterWrapper implements ItemMaster, ModelWrapper<ItemMaster> {
	public ItemMasterWrapper(ItemMaster itemMaster) {
		_itemMaster = itemMaster;
	}

	@Override
	public Class<?> getModelClass() {
		return ItemMaster.class;
	}

	@Override
	public String getModelClassName() {
		return ItemMaster.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("itemStatus", getItemStatus());
		attributes.put("itemDesc", getItemDesc());
		attributes.put("authorizedGenericStartDate",
			getAuthorizedGenericStartDate());
		attributes.put("acquiredAmp", getAcquiredAmp());
		attributes.put("newFormulationStartDate", getNewFormulationStartDate());
		attributes.put("marketTerminationDate", getMarketTerminationDate());
		attributes.put("obraBamp", getObraBamp());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("therapeuticClass", getTherapeuticClass());
		attributes.put("organizationKey", getOrganizationKey());
		attributes.put("acquiredBamp", getAcquiredBamp());
		attributes.put("pediatricExclusiveEndDate",
			getPediatricExclusiveEndDate());
		attributes.put("source", getSource());
		attributes.put("newFormulation", getNewFormulation());
		attributes.put("divestitureDate", getDivestitureDate());
		attributes.put("primaryUom", getPrimaryUom());
		attributes.put("newFormulationEndDate", getNewFormulationEndDate());
		attributes.put("modifiedBy", getModifiedBy());
		attributes.put("inboundStatus", getInboundStatus());
		attributes.put("packageSizeCode", getPackageSizeCode());
		attributes.put("secondaryUom", getSecondaryUom());
		attributes.put("discontinuationDate", getDiscontinuationDate());
		attributes.put("packageSizeIntroDate", getPackageSizeIntroDate());
		attributes.put("manufacturerId", getManufacturerId());
		attributes.put("itemEndDate", getItemEndDate());
		attributes.put("itemFamilyId", getItemFamilyId());
		attributes.put("strength", getStrength());
		attributes.put("itemCategory", getItemCategory());
		attributes.put("upps", getUpps());
		attributes.put("shelfLifeType", getShelfLifeType());
		attributes.put("pediatricExclusiveIndicator",
			getPediatricExclusiveIndicator());
		attributes.put("recordLockStatus", getRecordLockStatus());
		attributes.put("itemTypeIndication", getItemTypeIndication());
		attributes.put("acquisitionDate", getAcquisitionDate());
		attributes.put("clottingFactorIndicator", getClottingFactorIndicator());
		attributes.put("form", getForm());
		attributes.put("itemName", getItemName());
		attributes.put("shelfLife", getShelfLife());
		attributes.put("pediatricExclusiveStartDate",
			getPediatricExclusiveStartDate());
		attributes.put("firstSaleDate", getFirstSaleDate());
		attributes.put("itemMasterSid", getItemMasterSid());
		attributes.put("itemType", getItemType());
		attributes.put("itemId", getItemId());
		attributes.put("brandMasterSid", getBrandMasterSid());
		attributes.put("baselineAmp", getBaselineAmp());
		attributes.put("dualPricingIndicator", getDualPricingIndicator());
		attributes.put("dosesPerUnit", getDosesPerUnit());
		attributes.put("createdDate", getCreatedDate());
		attributes.put("createdBy", getCreatedBy());
		attributes.put("authorizedGeneric", getAuthorizedGeneric());
		attributes.put("itemStartDate", getItemStartDate());
		attributes.put("ndc9", getNdc9());
		attributes.put("authorizedGenericEndDate", getAuthorizedGenericEndDate());
		attributes.put("itemNo", getItemNo());
		attributes.put("packageSize", getPackageSize());
		attributes.put("ndc8", getNdc8());
		attributes.put("baseCpi", getBaseCpi());
		attributes.put("labelerCode", getLabelerCode());
		attributes.put("itemClass", getItemClass());
		attributes.put("clottingFactorEndDate", getClottingFactorEndDate());
		attributes.put("dra", getDra());
		attributes.put("baseCpiPeriod", getBaseCpiPeriod());
		attributes.put("newFormulationIndicator", getNewFormulationIndicator());
		attributes.put("lastLotExpirationDate", getLastLotExpirationDate());
		attributes.put("batchId", getBatchId());
		attributes.put("itemCode", getItemCode());
		attributes.put("clottingFactorStartDate", getClottingFactorStartDate());
		attributes.put("nonFederalExpirationDate", getNonFederalExpirationDate());
		attributes.put("internalNotes", getInternalNotes());
		attributes.put("baseCpiPrecision", getBaseCpiPrecision());
		attributes.put("baselineAmpPrecision", getBaselineAmpPrecision());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Integer itemStatus = (Integer)attributes.get("itemStatus");

		if (itemStatus != null) {
			setItemStatus(itemStatus);
		}

		String itemDesc = (String)attributes.get("itemDesc");

		if (itemDesc != null) {
			setItemDesc(itemDesc);
		}

		Date authorizedGenericStartDate = (Date)attributes.get(
				"authorizedGenericStartDate");

		if (authorizedGenericStartDate != null) {
			setAuthorizedGenericStartDate(authorizedGenericStartDate);
		}

		Double acquiredAmp = (Double)attributes.get("acquiredAmp");

		if (acquiredAmp != null) {
			setAcquiredAmp(acquiredAmp);
		}

		Date newFormulationStartDate = (Date)attributes.get(
				"newFormulationStartDate");

		if (newFormulationStartDate != null) {
			setNewFormulationStartDate(newFormulationStartDate);
		}

		Date marketTerminationDate = (Date)attributes.get(
				"marketTerminationDate");

		if (marketTerminationDate != null) {
			setMarketTerminationDate(marketTerminationDate);
		}

		Double obraBamp = (Double)attributes.get("obraBamp");

		if (obraBamp != null) {
			setObraBamp(obraBamp);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Integer therapeuticClass = (Integer)attributes.get("therapeuticClass");

		if (therapeuticClass != null) {
			setTherapeuticClass(therapeuticClass);
		}

		Integer organizationKey = (Integer)attributes.get("organizationKey");

		if (organizationKey != null) {
			setOrganizationKey(organizationKey);
		}

		Double acquiredBamp = (Double)attributes.get("acquiredBamp");

		if (acquiredBamp != null) {
			setAcquiredBamp(acquiredBamp);
		}

		Date pediatricExclusiveEndDate = (Date)attributes.get(
				"pediatricExclusiveEndDate");

		if (pediatricExclusiveEndDate != null) {
			setPediatricExclusiveEndDate(pediatricExclusiveEndDate);
		}

		String source = (String)attributes.get("source");

		if (source != null) {
			setSource(source);
		}

		String newFormulation = (String)attributes.get("newFormulation");

		if (newFormulation != null) {
			setNewFormulation(newFormulation);
		}

		Date divestitureDate = (Date)attributes.get("divestitureDate");

		if (divestitureDate != null) {
			setDivestitureDate(divestitureDate);
		}

		Integer primaryUom = (Integer)attributes.get("primaryUom");

		if (primaryUom != null) {
			setPrimaryUom(primaryUom);
		}

		Date newFormulationEndDate = (Date)attributes.get(
				"newFormulationEndDate");

		if (newFormulationEndDate != null) {
			setNewFormulationEndDate(newFormulationEndDate);
		}

		Integer modifiedBy = (Integer)attributes.get("modifiedBy");

		if (modifiedBy != null) {
			setModifiedBy(modifiedBy);
		}

		String inboundStatus = (String)attributes.get("inboundStatus");

		if (inboundStatus != null) {
			setInboundStatus(inboundStatus);
		}

		String packageSizeCode = (String)attributes.get("packageSizeCode");

		if (packageSizeCode != null) {
			setPackageSizeCode(packageSizeCode);
		}

		Integer secondaryUom = (Integer)attributes.get("secondaryUom");

		if (secondaryUom != null) {
			setSecondaryUom(secondaryUom);
		}

		Date discontinuationDate = (Date)attributes.get("discontinuationDate");

		if (discontinuationDate != null) {
			setDiscontinuationDate(discontinuationDate);
		}

		Date packageSizeIntroDate = (Date)attributes.get("packageSizeIntroDate");

		if (packageSizeIntroDate != null) {
			setPackageSizeIntroDate(packageSizeIntroDate);
		}

		String manufacturerId = (String)attributes.get("manufacturerId");

		if (manufacturerId != null) {
			setManufacturerId(manufacturerId);
		}

		Date itemEndDate = (Date)attributes.get("itemEndDate");

		if (itemEndDate != null) {
			setItemEndDate(itemEndDate);
		}

		String itemFamilyId = (String)attributes.get("itemFamilyId");

		if (itemFamilyId != null) {
			setItemFamilyId(itemFamilyId);
		}

		Integer strength = (Integer)attributes.get("strength");

		if (strength != null) {
			setStrength(strength);
		}

		Integer itemCategory = (Integer)attributes.get("itemCategory");

		if (itemCategory != null) {
			setItemCategory(itemCategory);
		}

		Double upps = (Double)attributes.get("upps");

		if (upps != null) {
			setUpps(upps);
		}

		Integer shelfLifeType = (Integer)attributes.get("shelfLifeType");

		if (shelfLifeType != null) {
			setShelfLifeType(shelfLifeType);
		}

		String pediatricExclusiveIndicator = (String)attributes.get(
				"pediatricExclusiveIndicator");

		if (pediatricExclusiveIndicator != null) {
			setPediatricExclusiveIndicator(pediatricExclusiveIndicator);
		}

		Boolean recordLockStatus = (Boolean)attributes.get("recordLockStatus");

		if (recordLockStatus != null) {
			setRecordLockStatus(recordLockStatus);
		}

		String itemTypeIndication = (String)attributes.get("itemTypeIndication");

		if (itemTypeIndication != null) {
			setItemTypeIndication(itemTypeIndication);
		}

		Date acquisitionDate = (Date)attributes.get("acquisitionDate");

		if (acquisitionDate != null) {
			setAcquisitionDate(acquisitionDate);
		}

		String clottingFactorIndicator = (String)attributes.get(
				"clottingFactorIndicator");

		if (clottingFactorIndicator != null) {
			setClottingFactorIndicator(clottingFactorIndicator);
		}

		Integer form = (Integer)attributes.get("form");

		if (form != null) {
			setForm(form);
		}

		String itemName = (String)attributes.get("itemName");

		if (itemName != null) {
			setItemName(itemName);
		}

		String shelfLife = (String)attributes.get("shelfLife");

		if (shelfLife != null) {
			setShelfLife(shelfLife);
		}

		Date pediatricExclusiveStartDate = (Date)attributes.get(
				"pediatricExclusiveStartDate");

		if (pediatricExclusiveStartDate != null) {
			setPediatricExclusiveStartDate(pediatricExclusiveStartDate);
		}

		Date firstSaleDate = (Date)attributes.get("firstSaleDate");

		if (firstSaleDate != null) {
			setFirstSaleDate(firstSaleDate);
		}

		Integer itemMasterSid = (Integer)attributes.get("itemMasterSid");

		if (itemMasterSid != null) {
			setItemMasterSid(itemMasterSid);
		}

		Integer itemType = (Integer)attributes.get("itemType");

		if (itemType != null) {
			setItemType(itemType);
		}

		String itemId = (String)attributes.get("itemId");

		if (itemId != null) {
			setItemId(itemId);
		}

		Integer brandMasterSid = (Integer)attributes.get("brandMasterSid");

		if (brandMasterSid != null) {
			setBrandMasterSid(brandMasterSid);
		}

		Double baselineAmp = (Double)attributes.get("baselineAmp");

		if (baselineAmp != null) {
			setBaselineAmp(baselineAmp);
		}

		String dualPricingIndicator = (String)attributes.get(
				"dualPricingIndicator");

		if (dualPricingIndicator != null) {
			setDualPricingIndicator(dualPricingIndicator);
		}

		String dosesPerUnit = (String)attributes.get("dosesPerUnit");

		if (dosesPerUnit != null) {
			setDosesPerUnit(dosesPerUnit);
		}

		Date createdDate = (Date)attributes.get("createdDate");

		if (createdDate != null) {
			setCreatedDate(createdDate);
		}

		Integer createdBy = (Integer)attributes.get("createdBy");

		if (createdBy != null) {
			setCreatedBy(createdBy);
		}

		String authorizedGeneric = (String)attributes.get("authorizedGeneric");

		if (authorizedGeneric != null) {
			setAuthorizedGeneric(authorizedGeneric);
		}

		Date itemStartDate = (Date)attributes.get("itemStartDate");

		if (itemStartDate != null) {
			setItemStartDate(itemStartDate);
		}

		String ndc9 = (String)attributes.get("ndc9");

		if (ndc9 != null) {
			setNdc9(ndc9);
		}

		Date authorizedGenericEndDate = (Date)attributes.get(
				"authorizedGenericEndDate");

		if (authorizedGenericEndDate != null) {
			setAuthorizedGenericEndDate(authorizedGenericEndDate);
		}

		String itemNo = (String)attributes.get("itemNo");

		if (itemNo != null) {
			setItemNo(itemNo);
		}

		String packageSize = (String)attributes.get("packageSize");

		if (packageSize != null) {
			setPackageSize(packageSize);
		}

		String ndc8 = (String)attributes.get("ndc8");

		if (ndc8 != null) {
			setNdc8(ndc8);
		}

		Double baseCpi = (Double)attributes.get("baseCpi");

		if (baseCpi != null) {
			setBaseCpi(baseCpi);
		}

		String labelerCode = (String)attributes.get("labelerCode");

		if (labelerCode != null) {
			setLabelerCode(labelerCode);
		}

		Integer itemClass = (Integer)attributes.get("itemClass");

		if (itemClass != null) {
			setItemClass(itemClass);
		}

		Date clottingFactorEndDate = (Date)attributes.get(
				"clottingFactorEndDate");

		if (clottingFactorEndDate != null) {
			setClottingFactorEndDate(clottingFactorEndDate);
		}

		Double dra = (Double)attributes.get("dra");

		if (dra != null) {
			setDra(dra);
		}

		Date baseCpiPeriod = (Date)attributes.get("baseCpiPeriod");

		if (baseCpiPeriod != null) {
			setBaseCpiPeriod(baseCpiPeriod);
		}

		String newFormulationIndicator = (String)attributes.get(
				"newFormulationIndicator");

		if (newFormulationIndicator != null) {
			setNewFormulationIndicator(newFormulationIndicator);
		}

		Date lastLotExpirationDate = (Date)attributes.get(
				"lastLotExpirationDate");

		if (lastLotExpirationDate != null) {
			setLastLotExpirationDate(lastLotExpirationDate);
		}

		String batchId = (String)attributes.get("batchId");

		if (batchId != null) {
			setBatchId(batchId);
		}

		String itemCode = (String)attributes.get("itemCode");

		if (itemCode != null) {
			setItemCode(itemCode);
		}

		Date clottingFactorStartDate = (Date)attributes.get(
				"clottingFactorStartDate");

		if (clottingFactorStartDate != null) {
			setClottingFactorStartDate(clottingFactorStartDate);
		}

		Date nonFederalExpirationDate = (Date)attributes.get(
				"nonFederalExpirationDate");

		if (nonFederalExpirationDate != null) {
			setNonFederalExpirationDate(nonFederalExpirationDate);
		}

		String internalNotes = (String)attributes.get("internalNotes");

		if (internalNotes != null) {
			setInternalNotes(internalNotes);
		}

		Integer baseCpiPrecision = (Integer)attributes.get("baseCpiPrecision");

		if (baseCpiPrecision != null) {
			setBaseCpiPrecision(baseCpiPrecision);
		}

		Integer baselineAmpPrecision = (Integer)attributes.get(
				"baselineAmpPrecision");

		if (baselineAmpPrecision != null) {
			setBaselineAmpPrecision(baselineAmpPrecision);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new ItemMasterWrapper((ItemMaster)_itemMaster.clone());
	}

	@Override
	public int compareTo(ItemMaster itemMaster) {
		return _itemMaster.compareTo(itemMaster);
	}

	/**
	* Returns the acquired amp of this item master.
	*
	* @return the acquired amp of this item master
	*/
	@Override
	public double getAcquiredAmp() {
		return _itemMaster.getAcquiredAmp();
	}

	/**
	* Returns the acquired bamp of this item master.
	*
	* @return the acquired bamp of this item master
	*/
	@Override
	public double getAcquiredBamp() {
		return _itemMaster.getAcquiredBamp();
	}

	/**
	* Returns the acquisition date of this item master.
	*
	* @return the acquisition date of this item master
	*/
	@Override
	public Date getAcquisitionDate() {
		return _itemMaster.getAcquisitionDate();
	}

	/**
	* Returns the authorized generic of this item master.
	*
	* @return the authorized generic of this item master
	*/
	@Override
	public java.lang.String getAuthorizedGeneric() {
		return _itemMaster.getAuthorizedGeneric();
	}

	/**
	* Returns the authorized generic end date of this item master.
	*
	* @return the authorized generic end date of this item master
	*/
	@Override
	public Date getAuthorizedGenericEndDate() {
		return _itemMaster.getAuthorizedGenericEndDate();
	}

	/**
	* Returns the authorized generic start date of this item master.
	*
	* @return the authorized generic start date of this item master
	*/
	@Override
	public Date getAuthorizedGenericStartDate() {
		return _itemMaster.getAuthorizedGenericStartDate();
	}

	/**
	* Returns the base cpi of this item master.
	*
	* @return the base cpi of this item master
	*/
	@Override
	public double getBaseCpi() {
		return _itemMaster.getBaseCpi();
	}

	/**
	* Returns the base cpi period of this item master.
	*
	* @return the base cpi period of this item master
	*/
	@Override
	public Date getBaseCpiPeriod() {
		return _itemMaster.getBaseCpiPeriod();
	}

	/**
	* Returns the base cpi precision of this item master.
	*
	* @return the base cpi precision of this item master
	*/
	@Override
	public int getBaseCpiPrecision() {
		return _itemMaster.getBaseCpiPrecision();
	}

	/**
	* Returns the baseline amp of this item master.
	*
	* @return the baseline amp of this item master
	*/
	@Override
	public double getBaselineAmp() {
		return _itemMaster.getBaselineAmp();
	}

	/**
	* Returns the baseline amp precision of this item master.
	*
	* @return the baseline amp precision of this item master
	*/
	@Override
	public int getBaselineAmpPrecision() {
		return _itemMaster.getBaselineAmpPrecision();
	}

	/**
	* Returns the batch ID of this item master.
	*
	* @return the batch ID of this item master
	*/
	@Override
	public java.lang.String getBatchId() {
		return _itemMaster.getBatchId();
	}

	/**
	* Returns the brand master sid of this item master.
	*
	* @return the brand master sid of this item master
	*/
	@Override
	public int getBrandMasterSid() {
		return _itemMaster.getBrandMasterSid();
	}

	/**
	* Returns the clotting factor end date of this item master.
	*
	* @return the clotting factor end date of this item master
	*/
	@Override
	public Date getClottingFactorEndDate() {
		return _itemMaster.getClottingFactorEndDate();
	}

	/**
	* Returns the clotting factor indicator of this item master.
	*
	* @return the clotting factor indicator of this item master
	*/
	@Override
	public java.lang.String getClottingFactorIndicator() {
		return _itemMaster.getClottingFactorIndicator();
	}

	/**
	* Returns the clotting factor start date of this item master.
	*
	* @return the clotting factor start date of this item master
	*/
	@Override
	public Date getClottingFactorStartDate() {
		return _itemMaster.getClottingFactorStartDate();
	}

	/**
	* Returns the created by of this item master.
	*
	* @return the created by of this item master
	*/
	@Override
	public int getCreatedBy() {
		return _itemMaster.getCreatedBy();
	}

	/**
	* Returns the created date of this item master.
	*
	* @return the created date of this item master
	*/
	@Override
	public Date getCreatedDate() {
		return _itemMaster.getCreatedDate();
	}

	/**
	* Returns the discontinuation date of this item master.
	*
	* @return the discontinuation date of this item master
	*/
	@Override
	public Date getDiscontinuationDate() {
		return _itemMaster.getDiscontinuationDate();
	}

	/**
	* Returns the divestiture date of this item master.
	*
	* @return the divestiture date of this item master
	*/
	@Override
	public Date getDivestitureDate() {
		return _itemMaster.getDivestitureDate();
	}

	/**
	* Returns the doses per unit of this item master.
	*
	* @return the doses per unit of this item master
	*/
	@Override
	public java.lang.String getDosesPerUnit() {
		return _itemMaster.getDosesPerUnit();
	}

	/**
	* Returns the dra of this item master.
	*
	* @return the dra of this item master
	*/
	@Override
	public double getDra() {
		return _itemMaster.getDra();
	}

	/**
	* Returns the dual pricing indicator of this item master.
	*
	* @return the dual pricing indicator of this item master
	*/
	@Override
	public java.lang.String getDualPricingIndicator() {
		return _itemMaster.getDualPricingIndicator();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _itemMaster.getExpandoBridge();
	}

	/**
	* Returns the first sale date of this item master.
	*
	* @return the first sale date of this item master
	*/
	@Override
	public Date getFirstSaleDate() {
		return _itemMaster.getFirstSaleDate();
	}

	/**
	* Returns the form of this item master.
	*
	* @return the form of this item master
	*/
	@Override
	public int getForm() {
		return _itemMaster.getForm();
	}

	/**
	* Returns the inbound status of this item master.
	*
	* @return the inbound status of this item master
	*/
	@Override
	public java.lang.String getInboundStatus() {
		return _itemMaster.getInboundStatus();
	}

	/**
	* Returns the internal notes of this item master.
	*
	* @return the internal notes of this item master
	*/
	@Override
	public java.lang.String getInternalNotes() {
		return _itemMaster.getInternalNotes();
	}

	/**
	* Returns the item category of this item master.
	*
	* @return the item category of this item master
	*/
	@Override
	public int getItemCategory() {
		return _itemMaster.getItemCategory();
	}

	/**
	* Returns the item class of this item master.
	*
	* @return the item class of this item master
	*/
	@Override
	public int getItemClass() {
		return _itemMaster.getItemClass();
	}

	/**
	* Returns the item code of this item master.
	*
	* @return the item code of this item master
	*/
	@Override
	public java.lang.String getItemCode() {
		return _itemMaster.getItemCode();
	}

	/**
	* Returns the item desc of this item master.
	*
	* @return the item desc of this item master
	*/
	@Override
	public java.lang.String getItemDesc() {
		return _itemMaster.getItemDesc();
	}

	/**
	* Returns the item end date of this item master.
	*
	* @return the item end date of this item master
	*/
	@Override
	public Date getItemEndDate() {
		return _itemMaster.getItemEndDate();
	}

	/**
	* Returns the item family ID of this item master.
	*
	* @return the item family ID of this item master
	*/
	@Override
	public java.lang.String getItemFamilyId() {
		return _itemMaster.getItemFamilyId();
	}

	/**
	* Returns the item ID of this item master.
	*
	* @return the item ID of this item master
	*/
	@Override
	public java.lang.String getItemId() {
		return _itemMaster.getItemId();
	}

	/**
	* Returns the item master sid of this item master.
	*
	* @return the item master sid of this item master
	*/
	@Override
	public int getItemMasterSid() {
		return _itemMaster.getItemMasterSid();
	}

	/**
	* Returns the item name of this item master.
	*
	* @return the item name of this item master
	*/
	@Override
	public java.lang.String getItemName() {
		return _itemMaster.getItemName();
	}

	/**
	* Returns the item no of this item master.
	*
	* @return the item no of this item master
	*/
	@Override
	public java.lang.String getItemNo() {
		return _itemMaster.getItemNo();
	}

	/**
	* Returns the item start date of this item master.
	*
	* @return the item start date of this item master
	*/
	@Override
	public Date getItemStartDate() {
		return _itemMaster.getItemStartDate();
	}

	/**
	* Returns the item status of this item master.
	*
	* @return the item status of this item master
	*/
	@Override
	public int getItemStatus() {
		return _itemMaster.getItemStatus();
	}

	/**
	* Returns the item type of this item master.
	*
	* @return the item type of this item master
	*/
	@Override
	public int getItemType() {
		return _itemMaster.getItemType();
	}

	/**
	* Returns the item type indication of this item master.
	*
	* @return the item type indication of this item master
	*/
	@Override
	public java.lang.String getItemTypeIndication() {
		return _itemMaster.getItemTypeIndication();
	}

	/**
	* Returns the labeler code of this item master.
	*
	* @return the labeler code of this item master
	*/
	@Override
	public java.lang.String getLabelerCode() {
		return _itemMaster.getLabelerCode();
	}

	/**
	* Returns the last lot expiration date of this item master.
	*
	* @return the last lot expiration date of this item master
	*/
	@Override
	public Date getLastLotExpirationDate() {
		return _itemMaster.getLastLotExpirationDate();
	}

	/**
	* Returns the manufacturer ID of this item master.
	*
	* @return the manufacturer ID of this item master
	*/
	@Override
	public java.lang.String getManufacturerId() {
		return _itemMaster.getManufacturerId();
	}

	/**
	* Returns the market termination date of this item master.
	*
	* @return the market termination date of this item master
	*/
	@Override
	public Date getMarketTerminationDate() {
		return _itemMaster.getMarketTerminationDate();
	}

	/**
	* Returns the modified by of this item master.
	*
	* @return the modified by of this item master
	*/
	@Override
	public int getModifiedBy() {
		return _itemMaster.getModifiedBy();
	}

	/**
	* Returns the modified date of this item master.
	*
	* @return the modified date of this item master
	*/
	@Override
	public Date getModifiedDate() {
		return _itemMaster.getModifiedDate();
	}

	/**
	* Returns the ndc8 of this item master.
	*
	* @return the ndc8 of this item master
	*/
	@Override
	public java.lang.String getNdc8() {
		return _itemMaster.getNdc8();
	}

	/**
	* Returns the ndc9 of this item master.
	*
	* @return the ndc9 of this item master
	*/
	@Override
	public java.lang.String getNdc9() {
		return _itemMaster.getNdc9();
	}

	/**
	* Returns the new formulation of this item master.
	*
	* @return the new formulation of this item master
	*/
	@Override
	public java.lang.String getNewFormulation() {
		return _itemMaster.getNewFormulation();
	}

	/**
	* Returns the new formulation end date of this item master.
	*
	* @return the new formulation end date of this item master
	*/
	@Override
	public Date getNewFormulationEndDate() {
		return _itemMaster.getNewFormulationEndDate();
	}

	/**
	* Returns the new formulation indicator of this item master.
	*
	* @return the new formulation indicator of this item master
	*/
	@Override
	public java.lang.String getNewFormulationIndicator() {
		return _itemMaster.getNewFormulationIndicator();
	}

	/**
	* Returns the new formulation start date of this item master.
	*
	* @return the new formulation start date of this item master
	*/
	@Override
	public Date getNewFormulationStartDate() {
		return _itemMaster.getNewFormulationStartDate();
	}

	/**
	* Returns the non federal expiration date of this item master.
	*
	* @return the non federal expiration date of this item master
	*/
	@Override
	public Date getNonFederalExpirationDate() {
		return _itemMaster.getNonFederalExpirationDate();
	}

	/**
	* Returns the obra bamp of this item master.
	*
	* @return the obra bamp of this item master
	*/
	@Override
	public double getObraBamp() {
		return _itemMaster.getObraBamp();
	}

	/**
	* Returns the organization key of this item master.
	*
	* @return the organization key of this item master
	*/
	@Override
	public int getOrganizationKey() {
		return _itemMaster.getOrganizationKey();
	}

	/**
	* Returns the package size of this item master.
	*
	* @return the package size of this item master
	*/
	@Override
	public java.lang.String getPackageSize() {
		return _itemMaster.getPackageSize();
	}

	/**
	* Returns the package size code of this item master.
	*
	* @return the package size code of this item master
	*/
	@Override
	public java.lang.String getPackageSizeCode() {
		return _itemMaster.getPackageSizeCode();
	}

	/**
	* Returns the package size intro date of this item master.
	*
	* @return the package size intro date of this item master
	*/
	@Override
	public Date getPackageSizeIntroDate() {
		return _itemMaster.getPackageSizeIntroDate();
	}

	/**
	* Returns the pediatric exclusive end date of this item master.
	*
	* @return the pediatric exclusive end date of this item master
	*/
	@Override
	public Date getPediatricExclusiveEndDate() {
		return _itemMaster.getPediatricExclusiveEndDate();
	}

	/**
	* Returns the pediatric exclusive indicator of this item master.
	*
	* @return the pediatric exclusive indicator of this item master
	*/
	@Override
	public java.lang.String getPediatricExclusiveIndicator() {
		return _itemMaster.getPediatricExclusiveIndicator();
	}

	/**
	* Returns the pediatric exclusive start date of this item master.
	*
	* @return the pediatric exclusive start date of this item master
	*/
	@Override
	public Date getPediatricExclusiveStartDate() {
		return _itemMaster.getPediatricExclusiveStartDate();
	}

	/**
	* Returns the primary key of this item master.
	*
	* @return the primary key of this item master
	*/
	@Override
	public int getPrimaryKey() {
		return _itemMaster.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _itemMaster.getPrimaryKeyObj();
	}

	/**
	* Returns the primary uom of this item master.
	*
	* @return the primary uom of this item master
	*/
	@Override
	public int getPrimaryUom() {
		return _itemMaster.getPrimaryUom();
	}

	/**
	* Returns the record lock status of this item master.
	*
	* @return the record lock status of this item master
	*/
	@Override
	public boolean getRecordLockStatus() {
		return _itemMaster.getRecordLockStatus();
	}

	/**
	* Returns the secondary uom of this item master.
	*
	* @return the secondary uom of this item master
	*/
	@Override
	public int getSecondaryUom() {
		return _itemMaster.getSecondaryUom();
	}

	/**
	* Returns the shelf life of this item master.
	*
	* @return the shelf life of this item master
	*/
	@Override
	public java.lang.String getShelfLife() {
		return _itemMaster.getShelfLife();
	}

	/**
	* Returns the shelf life type of this item master.
	*
	* @return the shelf life type of this item master
	*/
	@Override
	public int getShelfLifeType() {
		return _itemMaster.getShelfLifeType();
	}

	/**
	* Returns the source of this item master.
	*
	* @return the source of this item master
	*/
	@Override
	public java.lang.String getSource() {
		return _itemMaster.getSource();
	}

	/**
	* Returns the strength of this item master.
	*
	* @return the strength of this item master
	*/
	@Override
	public int getStrength() {
		return _itemMaster.getStrength();
	}

	/**
	* Returns the therapeutic class of this item master.
	*
	* @return the therapeutic class of this item master
	*/
	@Override
	public int getTherapeuticClass() {
		return _itemMaster.getTherapeuticClass();
	}

	/**
	* Returns the upps of this item master.
	*
	* @return the upps of this item master
	*/
	@Override
	public double getUpps() {
		return _itemMaster.getUpps();
	}

	@Override
	public int hashCode() {
		return _itemMaster.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _itemMaster.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _itemMaster.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _itemMaster.isNew();
	}

	/**
	* Returns <code>true</code> if this item master is record lock status.
	*
	* @return <code>true</code> if this item master is record lock status; <code>false</code> otherwise
	*/
	@Override
	public boolean isRecordLockStatus() {
		return _itemMaster.isRecordLockStatus();
	}

	@Override
	public void persist() {
		_itemMaster.persist();
	}

	/**
	* Sets the acquired amp of this item master.
	*
	* @param acquiredAmp the acquired amp of this item master
	*/
	@Override
	public void setAcquiredAmp(double acquiredAmp) {
		_itemMaster.setAcquiredAmp(acquiredAmp);
	}

	/**
	* Sets the acquired bamp of this item master.
	*
	* @param acquiredBamp the acquired bamp of this item master
	*/
	@Override
	public void setAcquiredBamp(double acquiredBamp) {
		_itemMaster.setAcquiredBamp(acquiredBamp);
	}

	/**
	* Sets the acquisition date of this item master.
	*
	* @param acquisitionDate the acquisition date of this item master
	*/
	@Override
	public void setAcquisitionDate(Date acquisitionDate) {
		_itemMaster.setAcquisitionDate(acquisitionDate);
	}

	/**
	* Sets the authorized generic of this item master.
	*
	* @param authorizedGeneric the authorized generic of this item master
	*/
	@Override
	public void setAuthorizedGeneric(java.lang.String authorizedGeneric) {
		_itemMaster.setAuthorizedGeneric(authorizedGeneric);
	}

	/**
	* Sets the authorized generic end date of this item master.
	*
	* @param authorizedGenericEndDate the authorized generic end date of this item master
	*/
	@Override
	public void setAuthorizedGenericEndDate(Date authorizedGenericEndDate) {
		_itemMaster.setAuthorizedGenericEndDate(authorizedGenericEndDate);
	}

	/**
	* Sets the authorized generic start date of this item master.
	*
	* @param authorizedGenericStartDate the authorized generic start date of this item master
	*/
	@Override
	public void setAuthorizedGenericStartDate(Date authorizedGenericStartDate) {
		_itemMaster.setAuthorizedGenericStartDate(authorizedGenericStartDate);
	}

	/**
	* Sets the base cpi of this item master.
	*
	* @param baseCpi the base cpi of this item master
	*/
	@Override
	public void setBaseCpi(double baseCpi) {
		_itemMaster.setBaseCpi(baseCpi);
	}

	/**
	* Sets the base cpi period of this item master.
	*
	* @param baseCpiPeriod the base cpi period of this item master
	*/
	@Override
	public void setBaseCpiPeriod(Date baseCpiPeriod) {
		_itemMaster.setBaseCpiPeriod(baseCpiPeriod);
	}

	/**
	* Sets the base cpi precision of this item master.
	*
	* @param baseCpiPrecision the base cpi precision of this item master
	*/
	@Override
	public void setBaseCpiPrecision(int baseCpiPrecision) {
		_itemMaster.setBaseCpiPrecision(baseCpiPrecision);
	}

	/**
	* Sets the baseline amp of this item master.
	*
	* @param baselineAmp the baseline amp of this item master
	*/
	@Override
	public void setBaselineAmp(double baselineAmp) {
		_itemMaster.setBaselineAmp(baselineAmp);
	}

	/**
	* Sets the baseline amp precision of this item master.
	*
	* @param baselineAmpPrecision the baseline amp precision of this item master
	*/
	@Override
	public void setBaselineAmpPrecision(int baselineAmpPrecision) {
		_itemMaster.setBaselineAmpPrecision(baselineAmpPrecision);
	}

	/**
	* Sets the batch ID of this item master.
	*
	* @param batchId the batch ID of this item master
	*/
	@Override
	public void setBatchId(java.lang.String batchId) {
		_itemMaster.setBatchId(batchId);
	}

	/**
	* Sets the brand master sid of this item master.
	*
	* @param brandMasterSid the brand master sid of this item master
	*/
	@Override
	public void setBrandMasterSid(int brandMasterSid) {
		_itemMaster.setBrandMasterSid(brandMasterSid);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_itemMaster.setCachedModel(cachedModel);
	}

	/**
	* Sets the clotting factor end date of this item master.
	*
	* @param clottingFactorEndDate the clotting factor end date of this item master
	*/
	@Override
	public void setClottingFactorEndDate(Date clottingFactorEndDate) {
		_itemMaster.setClottingFactorEndDate(clottingFactorEndDate);
	}

	/**
	* Sets the clotting factor indicator of this item master.
	*
	* @param clottingFactorIndicator the clotting factor indicator of this item master
	*/
	@Override
	public void setClottingFactorIndicator(
		java.lang.String clottingFactorIndicator) {
		_itemMaster.setClottingFactorIndicator(clottingFactorIndicator);
	}

	/**
	* Sets the clotting factor start date of this item master.
	*
	* @param clottingFactorStartDate the clotting factor start date of this item master
	*/
	@Override
	public void setClottingFactorStartDate(Date clottingFactorStartDate) {
		_itemMaster.setClottingFactorStartDate(clottingFactorStartDate);
	}

	/**
	* Sets the created by of this item master.
	*
	* @param createdBy the created by of this item master
	*/
	@Override
	public void setCreatedBy(int createdBy) {
		_itemMaster.setCreatedBy(createdBy);
	}

	/**
	* Sets the created date of this item master.
	*
	* @param createdDate the created date of this item master
	*/
	@Override
	public void setCreatedDate(Date createdDate) {
		_itemMaster.setCreatedDate(createdDate);
	}

	/**
	* Sets the discontinuation date of this item master.
	*
	* @param discontinuationDate the discontinuation date of this item master
	*/
	@Override
	public void setDiscontinuationDate(Date discontinuationDate) {
		_itemMaster.setDiscontinuationDate(discontinuationDate);
	}

	/**
	* Sets the divestiture date of this item master.
	*
	* @param divestitureDate the divestiture date of this item master
	*/
	@Override
	public void setDivestitureDate(Date divestitureDate) {
		_itemMaster.setDivestitureDate(divestitureDate);
	}

	/**
	* Sets the doses per unit of this item master.
	*
	* @param dosesPerUnit the doses per unit of this item master
	*/
	@Override
	public void setDosesPerUnit(java.lang.String dosesPerUnit) {
		_itemMaster.setDosesPerUnit(dosesPerUnit);
	}

	/**
	* Sets the dra of this item master.
	*
	* @param dra the dra of this item master
	*/
	@Override
	public void setDra(double dra) {
		_itemMaster.setDra(dra);
	}

	/**
	* Sets the dual pricing indicator of this item master.
	*
	* @param dualPricingIndicator the dual pricing indicator of this item master
	*/
	@Override
	public void setDualPricingIndicator(java.lang.String dualPricingIndicator) {
		_itemMaster.setDualPricingIndicator(dualPricingIndicator);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_itemMaster.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_itemMaster.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_itemMaster.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the first sale date of this item master.
	*
	* @param firstSaleDate the first sale date of this item master
	*/
	@Override
	public void setFirstSaleDate(Date firstSaleDate) {
		_itemMaster.setFirstSaleDate(firstSaleDate);
	}

	/**
	* Sets the form of this item master.
	*
	* @param form the form of this item master
	*/
	@Override
	public void setForm(int form) {
		_itemMaster.setForm(form);
	}

	/**
	* Sets the inbound status of this item master.
	*
	* @param inboundStatus the inbound status of this item master
	*/
	@Override
	public void setInboundStatus(java.lang.String inboundStatus) {
		_itemMaster.setInboundStatus(inboundStatus);
	}

	/**
	* Sets the internal notes of this item master.
	*
	* @param internalNotes the internal notes of this item master
	*/
	@Override
	public void setInternalNotes(java.lang.String internalNotes) {
		_itemMaster.setInternalNotes(internalNotes);
	}

	/**
	* Sets the item category of this item master.
	*
	* @param itemCategory the item category of this item master
	*/
	@Override
	public void setItemCategory(int itemCategory) {
		_itemMaster.setItemCategory(itemCategory);
	}

	/**
	* Sets the item class of this item master.
	*
	* @param itemClass the item class of this item master
	*/
	@Override
	public void setItemClass(int itemClass) {
		_itemMaster.setItemClass(itemClass);
	}

	/**
	* Sets the item code of this item master.
	*
	* @param itemCode the item code of this item master
	*/
	@Override
	public void setItemCode(java.lang.String itemCode) {
		_itemMaster.setItemCode(itemCode);
	}

	/**
	* Sets the item desc of this item master.
	*
	* @param itemDesc the item desc of this item master
	*/
	@Override
	public void setItemDesc(java.lang.String itemDesc) {
		_itemMaster.setItemDesc(itemDesc);
	}

	/**
	* Sets the item end date of this item master.
	*
	* @param itemEndDate the item end date of this item master
	*/
	@Override
	public void setItemEndDate(Date itemEndDate) {
		_itemMaster.setItemEndDate(itemEndDate);
	}

	/**
	* Sets the item family ID of this item master.
	*
	* @param itemFamilyId the item family ID of this item master
	*/
	@Override
	public void setItemFamilyId(java.lang.String itemFamilyId) {
		_itemMaster.setItemFamilyId(itemFamilyId);
	}

	/**
	* Sets the item ID of this item master.
	*
	* @param itemId the item ID of this item master
	*/
	@Override
	public void setItemId(java.lang.String itemId) {
		_itemMaster.setItemId(itemId);
	}

	/**
	* Sets the item master sid of this item master.
	*
	* @param itemMasterSid the item master sid of this item master
	*/
	@Override
	public void setItemMasterSid(int itemMasterSid) {
		_itemMaster.setItemMasterSid(itemMasterSid);
	}

	/**
	* Sets the item name of this item master.
	*
	* @param itemName the item name of this item master
	*/
	@Override
	public void setItemName(java.lang.String itemName) {
		_itemMaster.setItemName(itemName);
	}

	/**
	* Sets the item no of this item master.
	*
	* @param itemNo the item no of this item master
	*/
	@Override
	public void setItemNo(java.lang.String itemNo) {
		_itemMaster.setItemNo(itemNo);
	}

	/**
	* Sets the item start date of this item master.
	*
	* @param itemStartDate the item start date of this item master
	*/
	@Override
	public void setItemStartDate(Date itemStartDate) {
		_itemMaster.setItemStartDate(itemStartDate);
	}

	/**
	* Sets the item status of this item master.
	*
	* @param itemStatus the item status of this item master
	*/
	@Override
	public void setItemStatus(int itemStatus) {
		_itemMaster.setItemStatus(itemStatus);
	}

	/**
	* Sets the item type of this item master.
	*
	* @param itemType the item type of this item master
	*/
	@Override
	public void setItemType(int itemType) {
		_itemMaster.setItemType(itemType);
	}

	/**
	* Sets the item type indication of this item master.
	*
	* @param itemTypeIndication the item type indication of this item master
	*/
	@Override
	public void setItemTypeIndication(java.lang.String itemTypeIndication) {
		_itemMaster.setItemTypeIndication(itemTypeIndication);
	}

	/**
	* Sets the labeler code of this item master.
	*
	* @param labelerCode the labeler code of this item master
	*/
	@Override
	public void setLabelerCode(java.lang.String labelerCode) {
		_itemMaster.setLabelerCode(labelerCode);
	}

	/**
	* Sets the last lot expiration date of this item master.
	*
	* @param lastLotExpirationDate the last lot expiration date of this item master
	*/
	@Override
	public void setLastLotExpirationDate(Date lastLotExpirationDate) {
		_itemMaster.setLastLotExpirationDate(lastLotExpirationDate);
	}

	/**
	* Sets the manufacturer ID of this item master.
	*
	* @param manufacturerId the manufacturer ID of this item master
	*/
	@Override
	public void setManufacturerId(java.lang.String manufacturerId) {
		_itemMaster.setManufacturerId(manufacturerId);
	}

	/**
	* Sets the market termination date of this item master.
	*
	* @param marketTerminationDate the market termination date of this item master
	*/
	@Override
	public void setMarketTerminationDate(Date marketTerminationDate) {
		_itemMaster.setMarketTerminationDate(marketTerminationDate);
	}

	/**
	* Sets the modified by of this item master.
	*
	* @param modifiedBy the modified by of this item master
	*/
	@Override
	public void setModifiedBy(int modifiedBy) {
		_itemMaster.setModifiedBy(modifiedBy);
	}

	/**
	* Sets the modified date of this item master.
	*
	* @param modifiedDate the modified date of this item master
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_itemMaster.setModifiedDate(modifiedDate);
	}

	/**
	* Sets the ndc8 of this item master.
	*
	* @param ndc8 the ndc8 of this item master
	*/
	@Override
	public void setNdc8(java.lang.String ndc8) {
		_itemMaster.setNdc8(ndc8);
	}

	/**
	* Sets the ndc9 of this item master.
	*
	* @param ndc9 the ndc9 of this item master
	*/
	@Override
	public void setNdc9(java.lang.String ndc9) {
		_itemMaster.setNdc9(ndc9);
	}

	@Override
	public void setNew(boolean n) {
		_itemMaster.setNew(n);
	}

	/**
	* Sets the new formulation of this item master.
	*
	* @param newFormulation the new formulation of this item master
	*/
	@Override
	public void setNewFormulation(java.lang.String newFormulation) {
		_itemMaster.setNewFormulation(newFormulation);
	}

	/**
	* Sets the new formulation end date of this item master.
	*
	* @param newFormulationEndDate the new formulation end date of this item master
	*/
	@Override
	public void setNewFormulationEndDate(Date newFormulationEndDate) {
		_itemMaster.setNewFormulationEndDate(newFormulationEndDate);
	}

	/**
	* Sets the new formulation indicator of this item master.
	*
	* @param newFormulationIndicator the new formulation indicator of this item master
	*/
	@Override
	public void setNewFormulationIndicator(
		java.lang.String newFormulationIndicator) {
		_itemMaster.setNewFormulationIndicator(newFormulationIndicator);
	}

	/**
	* Sets the new formulation start date of this item master.
	*
	* @param newFormulationStartDate the new formulation start date of this item master
	*/
	@Override
	public void setNewFormulationStartDate(Date newFormulationStartDate) {
		_itemMaster.setNewFormulationStartDate(newFormulationStartDate);
	}

	/**
	* Sets the non federal expiration date of this item master.
	*
	* @param nonFederalExpirationDate the non federal expiration date of this item master
	*/
	@Override
	public void setNonFederalExpirationDate(Date nonFederalExpirationDate) {
		_itemMaster.setNonFederalExpirationDate(nonFederalExpirationDate);
	}

	/**
	* Sets the obra bamp of this item master.
	*
	* @param obraBamp the obra bamp of this item master
	*/
	@Override
	public void setObraBamp(double obraBamp) {
		_itemMaster.setObraBamp(obraBamp);
	}

	/**
	* Sets the organization key of this item master.
	*
	* @param organizationKey the organization key of this item master
	*/
	@Override
	public void setOrganizationKey(int organizationKey) {
		_itemMaster.setOrganizationKey(organizationKey);
	}

	/**
	* Sets the package size of this item master.
	*
	* @param packageSize the package size of this item master
	*/
	@Override
	public void setPackageSize(java.lang.String packageSize) {
		_itemMaster.setPackageSize(packageSize);
	}

	/**
	* Sets the package size code of this item master.
	*
	* @param packageSizeCode the package size code of this item master
	*/
	@Override
	public void setPackageSizeCode(java.lang.String packageSizeCode) {
		_itemMaster.setPackageSizeCode(packageSizeCode);
	}

	/**
	* Sets the package size intro date of this item master.
	*
	* @param packageSizeIntroDate the package size intro date of this item master
	*/
	@Override
	public void setPackageSizeIntroDate(Date packageSizeIntroDate) {
		_itemMaster.setPackageSizeIntroDate(packageSizeIntroDate);
	}

	/**
	* Sets the pediatric exclusive end date of this item master.
	*
	* @param pediatricExclusiveEndDate the pediatric exclusive end date of this item master
	*/
	@Override
	public void setPediatricExclusiveEndDate(Date pediatricExclusiveEndDate) {
		_itemMaster.setPediatricExclusiveEndDate(pediatricExclusiveEndDate);
	}

	/**
	* Sets the pediatric exclusive indicator of this item master.
	*
	* @param pediatricExclusiveIndicator the pediatric exclusive indicator of this item master
	*/
	@Override
	public void setPediatricExclusiveIndicator(
		java.lang.String pediatricExclusiveIndicator) {
		_itemMaster.setPediatricExclusiveIndicator(pediatricExclusiveIndicator);
	}

	/**
	* Sets the pediatric exclusive start date of this item master.
	*
	* @param pediatricExclusiveStartDate the pediatric exclusive start date of this item master
	*/
	@Override
	public void setPediatricExclusiveStartDate(Date pediatricExclusiveStartDate) {
		_itemMaster.setPediatricExclusiveStartDate(pediatricExclusiveStartDate);
	}

	/**
	* Sets the primary key of this item master.
	*
	* @param primaryKey the primary key of this item master
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_itemMaster.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_itemMaster.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the primary uom of this item master.
	*
	* @param primaryUom the primary uom of this item master
	*/
	@Override
	public void setPrimaryUom(int primaryUom) {
		_itemMaster.setPrimaryUom(primaryUom);
	}

	/**
	* Sets whether this item master is record lock status.
	*
	* @param recordLockStatus the record lock status of this item master
	*/
	@Override
	public void setRecordLockStatus(boolean recordLockStatus) {
		_itemMaster.setRecordLockStatus(recordLockStatus);
	}

	/**
	* Sets the secondary uom of this item master.
	*
	* @param secondaryUom the secondary uom of this item master
	*/
	@Override
	public void setSecondaryUom(int secondaryUom) {
		_itemMaster.setSecondaryUom(secondaryUom);
	}

	/**
	* Sets the shelf life of this item master.
	*
	* @param shelfLife the shelf life of this item master
	*/
	@Override
	public void setShelfLife(java.lang.String shelfLife) {
		_itemMaster.setShelfLife(shelfLife);
	}

	/**
	* Sets the shelf life type of this item master.
	*
	* @param shelfLifeType the shelf life type of this item master
	*/
	@Override
	public void setShelfLifeType(int shelfLifeType) {
		_itemMaster.setShelfLifeType(shelfLifeType);
	}

	/**
	* Sets the source of this item master.
	*
	* @param source the source of this item master
	*/
	@Override
	public void setSource(java.lang.String source) {
		_itemMaster.setSource(source);
	}

	/**
	* Sets the strength of this item master.
	*
	* @param strength the strength of this item master
	*/
	@Override
	public void setStrength(int strength) {
		_itemMaster.setStrength(strength);
	}

	/**
	* Sets the therapeutic class of this item master.
	*
	* @param therapeuticClass the therapeutic class of this item master
	*/
	@Override
	public void setTherapeuticClass(int therapeuticClass) {
		_itemMaster.setTherapeuticClass(therapeuticClass);
	}

	/**
	* Sets the upps of this item master.
	*
	* @param upps the upps of this item master
	*/
	@Override
	public void setUpps(double upps) {
		_itemMaster.setUpps(upps);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<ItemMaster> toCacheModel() {
		return _itemMaster.toCacheModel();
	}

	@Override
	public ItemMaster toEscapedModel() {
		return new ItemMasterWrapper(_itemMaster.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _itemMaster.toString();
	}

	@Override
	public ItemMaster toUnescapedModel() {
		return new ItemMasterWrapper(_itemMaster.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _itemMaster.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ItemMasterWrapper)) {
			return false;
		}

		ItemMasterWrapper itemMasterWrapper = (ItemMasterWrapper)obj;

		if (Objects.equals(_itemMaster, itemMasterWrapper._itemMaster)) {
			return true;
		}

		return false;
	}

	@Override
	public ItemMaster getWrappedModel() {
		return _itemMaster;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _itemMaster.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _itemMaster.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_itemMaster.resetOriginalValues();
	}

	private final ItemMaster _itemMaster;
}