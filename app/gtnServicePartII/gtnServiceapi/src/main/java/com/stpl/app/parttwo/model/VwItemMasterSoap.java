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

package com.stpl.app.parttwo.model;

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
public class VwItemMasterSoap implements Serializable {
	public static VwItemMasterSoap toSoapModel(VwItemMaster model) {
		VwItemMasterSoap soapModel = new VwItemMasterSoap();

		soapModel.setItemStatus(model.getItemStatus());
		soapModel.setItemDesc(model.getItemDesc());
		soapModel.setAcquiredAmp(model.getAcquiredAmp());
		soapModel.setAuthorizedGenericStartDate(model.getAuthorizedGenericStartDate());
		soapModel.setNewFormulationStartDate(model.getNewFormulationStartDate());
		soapModel.setMarketTerminationDate(model.getMarketTerminationDate());
		soapModel.setObraBamp(model.getObraBamp());
		soapModel.setBrand(model.getBrand());
		soapModel.setManufacturerNo(model.getManufacturerNo());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setTherapeuticClass(model.getTherapeuticClass());
		soapModel.setOrganizationKey(model.getOrganizationKey());
		soapModel.setAcquiredBamp(model.getAcquiredBamp());
		soapModel.setPediatricExclusiveEndDate(model.getPediatricExclusiveEndDate());
		soapModel.setSource(model.getSource());
		soapModel.setNewFormulation(model.getNewFormulation());
		soapModel.setAddChgDelIndicator(model.getAddChgDelIndicator());
		soapModel.setDivestitureDate(model.getDivestitureDate());
		soapModel.setShelfLife(model.getShelfLife());
		soapModel.setPrimaryUom(model.getPrimaryUom());
		soapModel.setNewFormulationEndDate(model.getNewFormulationEndDate());
		soapModel.setModifiedBy(model.getModifiedBy());
		soapModel.setPackageSizeCode(model.getPackageSizeCode());
		soapModel.setSecondaryUom(model.getSecondaryUom());
		soapModel.setUdc6(model.getUdc6());
		soapModel.setUdc5(model.getUdc5());
		soapModel.setDiscontinuationDate(model.getDiscontinuationDate());
		soapModel.setUdc4(model.getUdc4());
		soapModel.setUdc1(model.getUdc1());
		soapModel.setUdc2(model.getUdc2());
		soapModel.setPackageSizeIntroDate(model.getPackageSizeIntroDate());
		soapModel.setUdc3(model.getUdc3());
		soapModel.setItemEndDate(model.getItemEndDate());
		soapModel.setManufacturerId(model.getManufacturerId());
		soapModel.setItemFamilyId(model.getItemFamilyId());
		soapModel.setStrength(model.getStrength());
		soapModel.setItemCategory(model.getItemCategory());
		soapModel.setUpps(model.getUpps());
		soapModel.setShelfLifeType(model.getShelfLifeType());
		soapModel.setPediatricExclusiveIndicator(model.getPediatricExclusiveIndicator());
		soapModel.setItemTypeIndication(model.getItemTypeIndication());
		soapModel.setAcquisitionDate(model.getAcquisitionDate());
		soapModel.setClottingFactorIndicator(model.getClottingFactorIndicator());
		soapModel.setForm(model.getForm());
		soapModel.setItemName(model.getItemName());
		soapModel.setManufacturerName(model.getManufacturerName());
		soapModel.setPediatricExclusiveStartDate(model.getPediatricExclusiveStartDate());
		soapModel.setFirstSaleDate(model.getFirstSaleDate());
		soapModel.setItemMasterSid(model.getItemMasterSid());
		soapModel.setItemType(model.getItemType());
		soapModel.setItemId(model.getItemId());
		soapModel.setBaselineAmp(model.getBaselineAmp());
		soapModel.setDosesPerUnit(model.getDosesPerUnit());
		soapModel.setDualPricingIndicator(model.getDualPricingIndicator());
		soapModel.setBaseCpi(model.getBaseCpi());
		soapModel.setCreatedDate(model.getCreatedDate());
		soapModel.setCreatedBy(model.getCreatedBy());
		soapModel.setItemStartDate(model.getItemStartDate());
		soapModel.setAuthorizedGeneric(model.getAuthorizedGeneric());
		soapModel.setNdc9(model.getNdc9());
		soapModel.setAuthorizedGenericEndDate(model.getAuthorizedGenericEndDate());
		soapModel.setItemNo(model.getItemNo());
		soapModel.setPackageSize(model.getPackageSize());
		soapModel.setNdc8(model.getNdc8());
		soapModel.setItemClass(model.getItemClass());
		soapModel.setLabelerCode(model.getLabelerCode());
		soapModel.setDisplayBrand(model.getDisplayBrand());
		soapModel.setClottingFactorEndDate(model.getClottingFactorEndDate());
		soapModel.setDra(model.getDra());
		soapModel.setBrandId(model.getBrandId());
		soapModel.setBaseCpiPeriod(model.getBaseCpiPeriod());
		soapModel.setNewFormulationIndicator(model.getNewFormulationIndicator());
		soapModel.setLastLotExpirationDate(model.getLastLotExpirationDate());
		soapModel.setBatchId(model.getBatchId());
		soapModel.setItemCode(model.getItemCode());
		soapModel.setClottingFactorStartDate(model.getClottingFactorStartDate());
		soapModel.setNonFederalExpirationDate(model.getNonFederalExpirationDate());
		soapModel.setBaseCpiPrecision(model.getBaseCpiPrecision());
		soapModel.setBaselineAmpPrecision(model.getBaselineAmpPrecision());

		return soapModel;
	}

	public static VwItemMasterSoap[] toSoapModels(VwItemMaster[] models) {
		VwItemMasterSoap[] soapModels = new VwItemMasterSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static VwItemMasterSoap[][] toSoapModels(VwItemMaster[][] models) {
		VwItemMasterSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new VwItemMasterSoap[models.length][models[0].length];
		}
		else {
			soapModels = new VwItemMasterSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static VwItemMasterSoap[] toSoapModels(List<VwItemMaster> models) {
		List<VwItemMasterSoap> soapModels = new ArrayList<VwItemMasterSoap>(models.size());

		for (VwItemMaster model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new VwItemMasterSoap[soapModels.size()]);
	}

	public VwItemMasterSoap() {
	}

	public int getPrimaryKey() {
		return _itemMasterSid;
	}

	public void setPrimaryKey(int pk) {
		setItemMasterSid(pk);
	}

	public String getItemStatus() {
		return _itemStatus;
	}

	public void setItemStatus(String itemStatus) {
		_itemStatus = itemStatus;
	}

	public String getItemDesc() {
		return _itemDesc;
	}

	public void setItemDesc(String itemDesc) {
		_itemDesc = itemDesc;
	}

	public String getAcquiredAmp() {
		return _acquiredAmp;
	}

	public void setAcquiredAmp(String acquiredAmp) {
		_acquiredAmp = acquiredAmp;
	}

	public Date getAuthorizedGenericStartDate() {
		return _authorizedGenericStartDate;
	}

	public void setAuthorizedGenericStartDate(Date authorizedGenericStartDate) {
		_authorizedGenericStartDate = authorizedGenericStartDate;
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

	public String getObraBamp() {
		return _obraBamp;
	}

	public void setObraBamp(String obraBamp) {
		_obraBamp = obraBamp;
	}

	public String getBrand() {
		return _brand;
	}

	public void setBrand(String brand) {
		_brand = brand;
	}

	public String getManufacturerNo() {
		return _manufacturerNo;
	}

	public void setManufacturerNo(String manufacturerNo) {
		_manufacturerNo = manufacturerNo;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public String getTherapeuticClass() {
		return _therapeuticClass;
	}

	public void setTherapeuticClass(String therapeuticClass) {
		_therapeuticClass = therapeuticClass;
	}

	public String getOrganizationKey() {
		return _organizationKey;
	}

	public void setOrganizationKey(String organizationKey) {
		_organizationKey = organizationKey;
	}

	public String getAcquiredBamp() {
		return _acquiredBamp;
	}

	public void setAcquiredBamp(String acquiredBamp) {
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

	public String getAddChgDelIndicator() {
		return _addChgDelIndicator;
	}

	public void setAddChgDelIndicator(String addChgDelIndicator) {
		_addChgDelIndicator = addChgDelIndicator;
	}

	public Date getDivestitureDate() {
		return _divestitureDate;
	}

	public void setDivestitureDate(Date divestitureDate) {
		_divestitureDate = divestitureDate;
	}

	public String getShelfLife() {
		return _shelfLife;
	}

	public void setShelfLife(String shelfLife) {
		_shelfLife = shelfLife;
	}

	public String getPrimaryUom() {
		return _primaryUom;
	}

	public void setPrimaryUom(String primaryUom) {
		_primaryUom = primaryUom;
	}

	public Date getNewFormulationEndDate() {
		return _newFormulationEndDate;
	}

	public void setNewFormulationEndDate(Date newFormulationEndDate) {
		_newFormulationEndDate = newFormulationEndDate;
	}

	public String getModifiedBy() {
		return _modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		_modifiedBy = modifiedBy;
	}

	public String getPackageSizeCode() {
		return _packageSizeCode;
	}

	public void setPackageSizeCode(String packageSizeCode) {
		_packageSizeCode = packageSizeCode;
	}

	public String getSecondaryUom() {
		return _secondaryUom;
	}

	public void setSecondaryUom(String secondaryUom) {
		_secondaryUom = secondaryUom;
	}

	public String getUdc6() {
		return _udc6;
	}

	public void setUdc6(String udc6) {
		_udc6 = udc6;
	}

	public String getUdc5() {
		return _udc5;
	}

	public void setUdc5(String udc5) {
		_udc5 = udc5;
	}

	public Date getDiscontinuationDate() {
		return _discontinuationDate;
	}

	public void setDiscontinuationDate(Date discontinuationDate) {
		_discontinuationDate = discontinuationDate;
	}

	public String getUdc4() {
		return _udc4;
	}

	public void setUdc4(String udc4) {
		_udc4 = udc4;
	}

	public String getUdc1() {
		return _udc1;
	}

	public void setUdc1(String udc1) {
		_udc1 = udc1;
	}

	public String getUdc2() {
		return _udc2;
	}

	public void setUdc2(String udc2) {
		_udc2 = udc2;
	}

	public Date getPackageSizeIntroDate() {
		return _packageSizeIntroDate;
	}

	public void setPackageSizeIntroDate(Date packageSizeIntroDate) {
		_packageSizeIntroDate = packageSizeIntroDate;
	}

	public String getUdc3() {
		return _udc3;
	}

	public void setUdc3(String udc3) {
		_udc3 = udc3;
	}

	public Date getItemEndDate() {
		return _itemEndDate;
	}

	public void setItemEndDate(Date itemEndDate) {
		_itemEndDate = itemEndDate;
	}

	public String getManufacturerId() {
		return _manufacturerId;
	}

	public void setManufacturerId(String manufacturerId) {
		_manufacturerId = manufacturerId;
	}

	public String getItemFamilyId() {
		return _itemFamilyId;
	}

	public void setItemFamilyId(String itemFamilyId) {
		_itemFamilyId = itemFamilyId;
	}

	public String getStrength() {
		return _strength;
	}

	public void setStrength(String strength) {
		_strength = strength;
	}

	public String getItemCategory() {
		return _itemCategory;
	}

	public void setItemCategory(String itemCategory) {
		_itemCategory = itemCategory;
	}

	public double getUpps() {
		return _upps;
	}

	public void setUpps(double upps) {
		_upps = upps;
	}

	public String getShelfLifeType() {
		return _shelfLifeType;
	}

	public void setShelfLifeType(String shelfLifeType) {
		_shelfLifeType = shelfLifeType;
	}

	public String getPediatricExclusiveIndicator() {
		return _pediatricExclusiveIndicator;
	}

	public void setPediatricExclusiveIndicator(
		String pediatricExclusiveIndicator) {
		_pediatricExclusiveIndicator = pediatricExclusiveIndicator;
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

	public String getForm() {
		return _form;
	}

	public void setForm(String form) {
		_form = form;
	}

	public String getItemName() {
		return _itemName;
	}

	public void setItemName(String itemName) {
		_itemName = itemName;
	}

	public String getManufacturerName() {
		return _manufacturerName;
	}

	public void setManufacturerName(String manufacturerName) {
		_manufacturerName = manufacturerName;
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

	public String getItemType() {
		return _itemType;
	}

	public void setItemType(String itemType) {
		_itemType = itemType;
	}

	public String getItemId() {
		return _itemId;
	}

	public void setItemId(String itemId) {
		_itemId = itemId;
	}

	public String getBaselineAmp() {
		return _baselineAmp;
	}

	public void setBaselineAmp(String baselineAmp) {
		_baselineAmp = baselineAmp;
	}

	public String getDosesPerUnit() {
		return _dosesPerUnit;
	}

	public void setDosesPerUnit(String dosesPerUnit) {
		_dosesPerUnit = dosesPerUnit;
	}

	public String getDualPricingIndicator() {
		return _dualPricingIndicator;
	}

	public void setDualPricingIndicator(String dualPricingIndicator) {
		_dualPricingIndicator = dualPricingIndicator;
	}

	public String getBaseCpi() {
		return _baseCpi;
	}

	public void setBaseCpi(String baseCpi) {
		_baseCpi = baseCpi;
	}

	public Date getCreatedDate() {
		return _createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		_createdDate = createdDate;
	}

	public String getCreatedBy() {
		return _createdBy;
	}

	public void setCreatedBy(String createdBy) {
		_createdBy = createdBy;
	}

	public Date getItemStartDate() {
		return _itemStartDate;
	}

	public void setItemStartDate(Date itemStartDate) {
		_itemStartDate = itemStartDate;
	}

	public String getAuthorizedGeneric() {
		return _authorizedGeneric;
	}

	public void setAuthorizedGeneric(String authorizedGeneric) {
		_authorizedGeneric = authorizedGeneric;
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

	public String getItemClass() {
		return _itemClass;
	}

	public void setItemClass(String itemClass) {
		_itemClass = itemClass;
	}

	public String getLabelerCode() {
		return _labelerCode;
	}

	public void setLabelerCode(String labelerCode) {
		_labelerCode = labelerCode;
	}

	public String getDisplayBrand() {
		return _displayBrand;
	}

	public void setDisplayBrand(String displayBrand) {
		_displayBrand = displayBrand;
	}

	public Date getClottingFactorEndDate() {
		return _clottingFactorEndDate;
	}

	public void setClottingFactorEndDate(Date clottingFactorEndDate) {
		_clottingFactorEndDate = clottingFactorEndDate;
	}

	public String getDra() {
		return _dra;
	}

	public void setDra(String dra) {
		_dra = dra;
	}

	public String getBrandId() {
		return _brandId;
	}

	public void setBrandId(String brandId) {
		_brandId = brandId;
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

	private String _itemStatus;
	private String _itemDesc;
	private String _acquiredAmp;
	private Date _authorizedGenericStartDate;
	private Date _newFormulationStartDate;
	private Date _marketTerminationDate;
	private String _obraBamp;
	private String _brand;
	private String _manufacturerNo;
	private Date _modifiedDate;
	private String _therapeuticClass;
	private String _organizationKey;
	private String _acquiredBamp;
	private Date _pediatricExclusiveEndDate;
	private String _source;
	private String _newFormulation;
	private String _addChgDelIndicator;
	private Date _divestitureDate;
	private String _shelfLife;
	private String _primaryUom;
	private Date _newFormulationEndDate;
	private String _modifiedBy;
	private String _packageSizeCode;
	private String _secondaryUom;
	private String _udc6;
	private String _udc5;
	private Date _discontinuationDate;
	private String _udc4;
	private String _udc1;
	private String _udc2;
	private Date _packageSizeIntroDate;
	private String _udc3;
	private Date _itemEndDate;
	private String _manufacturerId;
	private String _itemFamilyId;
	private String _strength;
	private String _itemCategory;
	private double _upps;
	private String _shelfLifeType;
	private String _pediatricExclusiveIndicator;
	private String _itemTypeIndication;
	private Date _acquisitionDate;
	private String _clottingFactorIndicator;
	private String _form;
	private String _itemName;
	private String _manufacturerName;
	private Date _pediatricExclusiveStartDate;
	private Date _firstSaleDate;
	private int _itemMasterSid;
	private String _itemType;
	private String _itemId;
	private String _baselineAmp;
	private String _dosesPerUnit;
	private String _dualPricingIndicator;
	private String _baseCpi;
	private Date _createdDate;
	private String _createdBy;
	private Date _itemStartDate;
	private String _authorizedGeneric;
	private String _ndc9;
	private Date _authorizedGenericEndDate;
	private String _itemNo;
	private String _packageSize;
	private String _ndc8;
	private String _itemClass;
	private String _labelerCode;
	private String _displayBrand;
	private Date _clottingFactorEndDate;
	private String _dra;
	private String _brandId;
	private Date _baseCpiPeriod;
	private String _newFormulationIndicator;
	private Date _lastLotExpirationDate;
	private String _batchId;
	private String _itemCode;
	private Date _clottingFactorStartDate;
	private Date _nonFederalExpirationDate;
	private int _baseCpiPrecision;
	private int _baselineAmpPrecision;
}