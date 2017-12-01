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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author
 * @generated
 */
@ProviderType
public class ItemMasterSoap implements Serializable {
	public static ItemMasterSoap toSoapModel(ItemMaster model) {
		ItemMasterSoap soapModel = new ItemMasterSoap();

		soapModel.setItemStatus(model.getItemStatus());
		soapModel.setItemDesc(model.getItemDesc());
		soapModel.setAuthorizedGenericStartDate(model.getAuthorizedGenericStartDate());
		soapModel.setAcquiredAmp(model.getAcquiredAmp());
		soapModel.setNewFormulationStartDate(model.getNewFormulationStartDate());
		soapModel.setMarketTerminationDate(model.getMarketTerminationDate());
		soapModel.setObraBamp(model.getObraBamp());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setTherapeuticClass(model.getTherapeuticClass());
		soapModel.setOrganizationKey(model.getOrganizationKey());
		soapModel.setAcquiredBamp(model.getAcquiredBamp());
		soapModel.setPediatricExclusiveEndDate(model.getPediatricExclusiveEndDate());
		soapModel.setSource(model.getSource());
		soapModel.setNewFormulation(model.getNewFormulation());
		soapModel.setDivestitureDate(model.getDivestitureDate());
		soapModel.setPrimaryUom(model.getPrimaryUom());
		soapModel.setNewFormulationEndDate(model.getNewFormulationEndDate());
		soapModel.setModifiedBy(model.getModifiedBy());
		soapModel.setInboundStatus(model.getInboundStatus());
		soapModel.setPackageSizeCode(model.getPackageSizeCode());
		soapModel.setSecondaryUom(model.getSecondaryUom());
		soapModel.setDiscontinuationDate(model.getDiscontinuationDate());
		soapModel.setPackageSizeIntroDate(model.getPackageSizeIntroDate());
		soapModel.setManufacturerId(model.getManufacturerId());
		soapModel.setItemEndDate(model.getItemEndDate());
		soapModel.setItemFamilyId(model.getItemFamilyId());
		soapModel.setStrength(model.getStrength());
		soapModel.setItemCategory(model.getItemCategory());
		soapModel.setUpps(model.getUpps());
		soapModel.setShelfLifeType(model.getShelfLifeType());
		soapModel.setPediatricExclusiveIndicator(model.getPediatricExclusiveIndicator());
		soapModel.setRecordLockStatus(model.getRecordLockStatus());
		soapModel.setItemTypeIndication(model.getItemTypeIndication());
		soapModel.setAcquisitionDate(model.getAcquisitionDate());
		soapModel.setClottingFactorIndicator(model.getClottingFactorIndicator());
		soapModel.setForm(model.getForm());
		soapModel.setItemName(model.getItemName());
		soapModel.setShelfLife(model.getShelfLife());
		soapModel.setPediatricExclusiveStartDate(model.getPediatricExclusiveStartDate());
		soapModel.setFirstSaleDate(model.getFirstSaleDate());
		soapModel.setItemMasterSid(model.getItemMasterSid());
		soapModel.setItemType(model.getItemType());
		soapModel.setItemId(model.getItemId());
		soapModel.setBrandMasterSid(model.getBrandMasterSid());
		soapModel.setBaselineAmp(model.getBaselineAmp());
		soapModel.setDualPricingIndicator(model.getDualPricingIndicator());
		soapModel.setDosesPerUnit(model.getDosesPerUnit());
		soapModel.setCreatedDate(model.getCreatedDate());
		soapModel.setCreatedBy(model.getCreatedBy());
		soapModel.setAuthorizedGeneric(model.getAuthorizedGeneric());
		soapModel.setItemStartDate(model.getItemStartDate());
		soapModel.setNdc9(model.getNdc9());
		soapModel.setAuthorizedGenericEndDate(model.getAuthorizedGenericEndDate());
		soapModel.setItemNo(model.getItemNo());
		soapModel.setPackageSize(model.getPackageSize());
		soapModel.setNdc8(model.getNdc8());
		soapModel.setBaseCpi(model.getBaseCpi());
		soapModel.setLabelerCode(model.getLabelerCode());
		soapModel.setItemClass(model.getItemClass());
		soapModel.setClottingFactorEndDate(model.getClottingFactorEndDate());
		soapModel.setDra(model.getDra());
		soapModel.setBaseCpiPeriod(model.getBaseCpiPeriod());
		soapModel.setNewFormulationIndicator(model.getNewFormulationIndicator());
		soapModel.setLastLotExpirationDate(model.getLastLotExpirationDate());
		soapModel.setBatchId(model.getBatchId());
		soapModel.setItemCode(model.getItemCode());
		soapModel.setClottingFactorStartDate(model.getClottingFactorStartDate());
		soapModel.setNonFederalExpirationDate(model.getNonFederalExpirationDate());
		soapModel.setInternalNotes(model.getInternalNotes());
		soapModel.setBaseCpiPrecision(model.getBaseCpiPrecision());
		soapModel.setBaselineAmpPrecision(model.getBaselineAmpPrecision());

		return soapModel;
	}

	public static ItemMasterSoap[] toSoapModels(ItemMaster[] models) {
		ItemMasterSoap[] soapModels = new ItemMasterSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ItemMasterSoap[][] toSoapModels(ItemMaster[][] models) {
		ItemMasterSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ItemMasterSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ItemMasterSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ItemMasterSoap[] toSoapModels(List<ItemMaster> models) {
		List<ItemMasterSoap> soapModels = new ArrayList<ItemMasterSoap>(models.size());

		for (ItemMaster model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ItemMasterSoap[soapModels.size()]);
	}

	public ItemMasterSoap() {
	}

	public int getPrimaryKey() {
		return _itemMasterSid;
	}

	public void setPrimaryKey(int pk) {
		setItemMasterSid(pk);
	}

	public int getItemStatus() {
		return _itemStatus;
	}

	public void setItemStatus(int itemStatus) {
		_itemStatus = itemStatus;
	}

	public String getItemDesc() {
		return _itemDesc;
	}

	public void setItemDesc(String itemDesc) {
		_itemDesc = itemDesc;
	}

	public Date getAuthorizedGenericStartDate() {
		return _authorizedGenericStartDate;
	}

	public void setAuthorizedGenericStartDate(Date authorizedGenericStartDate) {
		_authorizedGenericStartDate = authorizedGenericStartDate;
	}

	public double getAcquiredAmp() {
		return _acquiredAmp;
	}

	public void setAcquiredAmp(double acquiredAmp) {
		_acquiredAmp = acquiredAmp;
	}

	public Date getNewFormulationStartDate() {
		return _newFormulationStartDate;
	}

	public void setNewFormulationStartDate(Date newFormulationStartDate) {
		_newFormulationStartDate = newFormulationStartDate;
	}

	public Date getMarketTerminationDate() {
		return _marketTerminationDate;
	}

	public void setMarketTerminationDate(Date marketTerminationDate) {
		_marketTerminationDate = marketTerminationDate;
	}

	public double getObraBamp() {
		return _obraBamp;
	}

	public void setObraBamp(double obraBamp) {
		_obraBamp = obraBamp;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public int getTherapeuticClass() {
		return _therapeuticClass;
	}

	public void setTherapeuticClass(int therapeuticClass) {
		_therapeuticClass = therapeuticClass;
	}

	public int getOrganizationKey() {
		return _organizationKey;
	}

	public void setOrganizationKey(int organizationKey) {
		_organizationKey = organizationKey;
	}

	public double getAcquiredBamp() {
		return _acquiredBamp;
	}

	public void setAcquiredBamp(double acquiredBamp) {
		_acquiredBamp = acquiredBamp;
	}

	public Date getPediatricExclusiveEndDate() {
		return _pediatricExclusiveEndDate;
	}

	public void setPediatricExclusiveEndDate(Date pediatricExclusiveEndDate) {
		_pediatricExclusiveEndDate = pediatricExclusiveEndDate;
	}

	public String getSource() {
		return _source;
	}

	public void setSource(String source) {
		_source = source;
	}

	public String getNewFormulation() {
		return _newFormulation;
	}

	public void setNewFormulation(String newFormulation) {
		_newFormulation = newFormulation;
	}

	public Date getDivestitureDate() {
		return _divestitureDate;
	}

	public void setDivestitureDate(Date divestitureDate) {
		_divestitureDate = divestitureDate;
	}

	public int getPrimaryUom() {
		return _primaryUom;
	}

	public void setPrimaryUom(int primaryUom) {
		_primaryUom = primaryUom;
	}

	public Date getNewFormulationEndDate() {
		return _newFormulationEndDate;
	}

	public void setNewFormulationEndDate(Date newFormulationEndDate) {
		_newFormulationEndDate = newFormulationEndDate;
	}

	public int getModifiedBy() {
		return _modifiedBy;
	}

	public void setModifiedBy(int modifiedBy) {
		_modifiedBy = modifiedBy;
	}

	public String getInboundStatus() {
		return _inboundStatus;
	}

	public void setInboundStatus(String inboundStatus) {
		_inboundStatus = inboundStatus;
	}

	public String getPackageSizeCode() {
		return _packageSizeCode;
	}

	public void setPackageSizeCode(String packageSizeCode) {
		_packageSizeCode = packageSizeCode;
	}

	public int getSecondaryUom() {
		return _secondaryUom;
	}

	public void setSecondaryUom(int secondaryUom) {
		_secondaryUom = secondaryUom;
	}

	public Date getDiscontinuationDate() {
		return _discontinuationDate;
	}

	public void setDiscontinuationDate(Date discontinuationDate) {
		_discontinuationDate = discontinuationDate;
	}

	public Date getPackageSizeIntroDate() {
		return _packageSizeIntroDate;
	}

	public void setPackageSizeIntroDate(Date packageSizeIntroDate) {
		_packageSizeIntroDate = packageSizeIntroDate;
	}

	public String getManufacturerId() {
		return _manufacturerId;
	}

	public void setManufacturerId(String manufacturerId) {
		_manufacturerId = manufacturerId;
	}

	public Date getItemEndDate() {
		return _itemEndDate;
	}

	public void setItemEndDate(Date itemEndDate) {
		_itemEndDate = itemEndDate;
	}

	public String getItemFamilyId() {
		return _itemFamilyId;
	}

	public void setItemFamilyId(String itemFamilyId) {
		_itemFamilyId = itemFamilyId;
	}

	public int getStrength() {
		return _strength;
	}

	public void setStrength(int strength) {
		_strength = strength;
	}

	public int getItemCategory() {
		return _itemCategory;
	}

	public void setItemCategory(int itemCategory) {
		_itemCategory = itemCategory;
	}

	public double getUpps() {
		return _upps;
	}

	public void setUpps(double upps) {
		_upps = upps;
	}

	public int getShelfLifeType() {
		return _shelfLifeType;
	}

	public void setShelfLifeType(int shelfLifeType) {
		_shelfLifeType = shelfLifeType;
	}

	public String getPediatricExclusiveIndicator() {
		return _pediatricExclusiveIndicator;
	}

	public void setPediatricExclusiveIndicator(
		String pediatricExclusiveIndicator) {
		_pediatricExclusiveIndicator = pediatricExclusiveIndicator;
	}

	public boolean getRecordLockStatus() {
		return _recordLockStatus;
	}

	public boolean isRecordLockStatus() {
		return _recordLockStatus;
	}

	public void setRecordLockStatus(boolean recordLockStatus) {
		_recordLockStatus = recordLockStatus;
	}

	public String getItemTypeIndication() {
		return _itemTypeIndication;
	}

	public void setItemTypeIndication(String itemTypeIndication) {
		_itemTypeIndication = itemTypeIndication;
	}

	public Date getAcquisitionDate() {
		return _acquisitionDate;
	}

	public void setAcquisitionDate(Date acquisitionDate) {
		_acquisitionDate = acquisitionDate;
	}

	public String getClottingFactorIndicator() {
		return _clottingFactorIndicator;
	}

	public void setClottingFactorIndicator(String clottingFactorIndicator) {
		_clottingFactorIndicator = clottingFactorIndicator;
	}

	public int getForm() {
		return _form;
	}

	public void setForm(int form) {
		_form = form;
	}

	public String getItemName() {
		return _itemName;
	}

	public void setItemName(String itemName) {
		_itemName = itemName;
	}

	public String getShelfLife() {
		return _shelfLife;
	}

	public void setShelfLife(String shelfLife) {
		_shelfLife = shelfLife;
	}

	public Date getPediatricExclusiveStartDate() {
		return _pediatricExclusiveStartDate;
	}

	public void setPediatricExclusiveStartDate(Date pediatricExclusiveStartDate) {
		_pediatricExclusiveStartDate = pediatricExclusiveStartDate;
	}

	public Date getFirstSaleDate() {
		return _firstSaleDate;
	}

	public void setFirstSaleDate(Date firstSaleDate) {
		_firstSaleDate = firstSaleDate;
	}

	public int getItemMasterSid() {
		return _itemMasterSid;
	}

	public void setItemMasterSid(int itemMasterSid) {
		_itemMasterSid = itemMasterSid;
	}

	public int getItemType() {
		return _itemType;
	}

	public void setItemType(int itemType) {
		_itemType = itemType;
	}

	public String getItemId() {
		return _itemId;
	}

	public void setItemId(String itemId) {
		_itemId = itemId;
	}

	public int getBrandMasterSid() {
		return _brandMasterSid;
	}

	public void setBrandMasterSid(int brandMasterSid) {
		_brandMasterSid = brandMasterSid;
	}

	public double getBaselineAmp() {
		return _baselineAmp;
	}

	public void setBaselineAmp(double baselineAmp) {
		_baselineAmp = baselineAmp;
	}

	public String getDualPricingIndicator() {
		return _dualPricingIndicator;
	}

	public void setDualPricingIndicator(String dualPricingIndicator) {
		_dualPricingIndicator = dualPricingIndicator;
	}

	public String getDosesPerUnit() {
		return _dosesPerUnit;
	}

	public void setDosesPerUnit(String dosesPerUnit) {
		_dosesPerUnit = dosesPerUnit;
	}

	public Date getCreatedDate() {
		return _createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		_createdDate = createdDate;
	}

	public int getCreatedBy() {
		return _createdBy;
	}

	public void setCreatedBy(int createdBy) {
		_createdBy = createdBy;
	}

	public String getAuthorizedGeneric() {
		return _authorizedGeneric;
	}

	public void setAuthorizedGeneric(String authorizedGeneric) {
		_authorizedGeneric = authorizedGeneric;
	}

	public Date getItemStartDate() {
		return _itemStartDate;
	}

	public void setItemStartDate(Date itemStartDate) {
		_itemStartDate = itemStartDate;
	}

	public String getNdc9() {
		return _ndc9;
	}

	public void setNdc9(String ndc9) {
		_ndc9 = ndc9;
	}

	public Date getAuthorizedGenericEndDate() {
		return _authorizedGenericEndDate;
	}

	public void setAuthorizedGenericEndDate(Date authorizedGenericEndDate) {
		_authorizedGenericEndDate = authorizedGenericEndDate;
	}

	public String getItemNo() {
		return _itemNo;
	}

	public void setItemNo(String itemNo) {
		_itemNo = itemNo;
	}

	public String getPackageSize() {
		return _packageSize;
	}

	public void setPackageSize(String packageSize) {
		_packageSize = packageSize;
	}

	public String getNdc8() {
		return _ndc8;
	}

	public void setNdc8(String ndc8) {
		_ndc8 = ndc8;
	}

	public double getBaseCpi() {
		return _baseCpi;
	}

	public void setBaseCpi(double baseCpi) {
		_baseCpi = baseCpi;
	}

	public String getLabelerCode() {
		return _labelerCode;
	}

	public void setLabelerCode(String labelerCode) {
		_labelerCode = labelerCode;
	}

	public int getItemClass() {
		return _itemClass;
	}

	public void setItemClass(int itemClass) {
		_itemClass = itemClass;
	}

	public Date getClottingFactorEndDate() {
		return _clottingFactorEndDate;
	}

	public void setClottingFactorEndDate(Date clottingFactorEndDate) {
		_clottingFactorEndDate = clottingFactorEndDate;
	}

	public double getDra() {
		return _dra;
	}

	public void setDra(double dra) {
		_dra = dra;
	}

	public Date getBaseCpiPeriod() {
		return _baseCpiPeriod;
	}

	public void setBaseCpiPeriod(Date baseCpiPeriod) {
		_baseCpiPeriod = baseCpiPeriod;
	}

	public String getNewFormulationIndicator() {
		return _newFormulationIndicator;
	}

	public void setNewFormulationIndicator(String newFormulationIndicator) {
		_newFormulationIndicator = newFormulationIndicator;
	}

	public Date getLastLotExpirationDate() {
		return _lastLotExpirationDate;
	}

	public void setLastLotExpirationDate(Date lastLotExpirationDate) {
		_lastLotExpirationDate = lastLotExpirationDate;
	}

	public String getBatchId() {
		return _batchId;
	}

	public void setBatchId(String batchId) {
		_batchId = batchId;
	}

	public String getItemCode() {
		return _itemCode;
	}

	public void setItemCode(String itemCode) {
		_itemCode = itemCode;
	}

	public Date getClottingFactorStartDate() {
		return _clottingFactorStartDate;
	}

	public void setClottingFactorStartDate(Date clottingFactorStartDate) {
		_clottingFactorStartDate = clottingFactorStartDate;
	}

	public Date getNonFederalExpirationDate() {
		return _nonFederalExpirationDate;
	}

	public void setNonFederalExpirationDate(Date nonFederalExpirationDate) {
		_nonFederalExpirationDate = nonFederalExpirationDate;
	}

	public String getInternalNotes() {
		return _internalNotes;
	}

	public void setInternalNotes(String internalNotes) {
		_internalNotes = internalNotes;
	}

	public int getBaseCpiPrecision() {
		return _baseCpiPrecision;
	}

	public void setBaseCpiPrecision(int baseCpiPrecision) {
		_baseCpiPrecision = baseCpiPrecision;
	}

	public int getBaselineAmpPrecision() {
		return _baselineAmpPrecision;
	}

	public void setBaselineAmpPrecision(int baselineAmpPrecision) {
		_baselineAmpPrecision = baselineAmpPrecision;
	}

	private int _itemStatus;
	private String _itemDesc;
	private Date _authorizedGenericStartDate;
	private double _acquiredAmp;
	private Date _newFormulationStartDate;
	private Date _marketTerminationDate;
	private double _obraBamp;
	private Date _modifiedDate;
	private int _therapeuticClass;
	private int _organizationKey;
	private double _acquiredBamp;
	private Date _pediatricExclusiveEndDate;
	private String _source;
	private String _newFormulation;
	private Date _divestitureDate;
	private int _primaryUom;
	private Date _newFormulationEndDate;
	private int _modifiedBy;
	private String _inboundStatus;
	private String _packageSizeCode;
	private int _secondaryUom;
	private Date _discontinuationDate;
	private Date _packageSizeIntroDate;
	private String _manufacturerId;
	private Date _itemEndDate;
	private String _itemFamilyId;
	private int _strength;
	private int _itemCategory;
	private double _upps;
	private int _shelfLifeType;
	private String _pediatricExclusiveIndicator;
	private boolean _recordLockStatus;
	private String _itemTypeIndication;
	private Date _acquisitionDate;
	private String _clottingFactorIndicator;
	private int _form;
	private String _itemName;
	private String _shelfLife;
	private Date _pediatricExclusiveStartDate;
	private Date _firstSaleDate;
	private int _itemMasterSid;
	private int _itemType;
	private String _itemId;
	private int _brandMasterSid;
	private double _baselineAmp;
	private String _dualPricingIndicator;
	private String _dosesPerUnit;
	private Date _createdDate;
	private int _createdBy;
	private String _authorizedGeneric;
	private Date _itemStartDate;
	private String _ndc9;
	private Date _authorizedGenericEndDate;
	private String _itemNo;
	private String _packageSize;
	private String _ndc8;
	private double _baseCpi;
	private String _labelerCode;
	private int _itemClass;
	private Date _clottingFactorEndDate;
	private double _dra;
	private Date _baseCpiPeriod;
	private String _newFormulationIndicator;
	private Date _lastLotExpirationDate;
	private String _batchId;
	private String _itemCode;
	private Date _clottingFactorStartDate;
	private Date _nonFederalExpirationDate;
	private String _internalNotes;
	private int _baseCpiPrecision;
	private int _baselineAmpPrecision;
}