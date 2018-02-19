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
public class PsDetailsSoap implements Serializable {
	public static PsDetailsSoap toSoapModel(PsDetails model) {
		PsDetailsSoap soapModel = new PsDetailsSoap();

		soapModel.setNepFormula(model.getNepFormula());
		soapModel.setPrice(model.getPrice());
		soapModel.setItemMasterSid(model.getItemMasterSid());
		soapModel.setResetType(model.getResetType());
		soapModel.setPriceProtectionStartDate(model.getPriceProtectionStartDate());
		soapModel.setResetDate(model.getResetDate());
		soapModel.setBasePrice(model.getBasePrice());
		soapModel.setItemPsAttachedDate(model.getItemPsAttachedDate());
		soapModel.setBrandMasterSid(model.getBrandMasterSid());
		soapModel.setStatus(model.getStatus());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setItemPsAttachedStatus(model.getItemPsAttachedStatus());
		soapModel.setRevisionDate(model.getRevisionDate());
		soapModel.setPriceTolerance(model.getPriceTolerance());
		soapModel.setCreatedDate(model.getCreatedDate());
		soapModel.setCreatedBy(model.getCreatedBy());
		soapModel.setSource(model.getSource());
		soapModel.setPsDetailsSid(model.getPsDetailsSid());
		soapModel.setPsModelSid(model.getPsModelSid());
		soapModel.setSuggestedPrice(model.getSuggestedPrice());
		soapModel.setNetPriceTypeFormula(model.getNetPriceTypeFormula());
		soapModel.setPriceProtectionPriceType(model.getPriceProtectionPriceType());
		soapModel.setModifiedBy(model.getModifiedBy());
		soapModel.setInboundStatus(model.getInboundStatus());
		soapModel.setContractPrice(model.getContractPrice());
		soapModel.setIfpModelSid(model.getIfpModelSid());
		soapModel.setPriceToleranceType(model.getPriceToleranceType());
		soapModel.setMaxIncrementalChange(model.getMaxIncrementalChange());
		soapModel.setItemPricingQualifierSid(model.getItemPricingQualifierSid());
		soapModel.setContractPriceEndDate(model.getContractPriceEndDate());
		soapModel.setNep(model.getNep());
		soapModel.setContractPriceStartDate(model.getContractPriceStartDate());
		soapModel.setPriceToleranceFrequency(model.getPriceToleranceFrequency());
		soapModel.setPriceProtectionEndDate(model.getPriceProtectionEndDate());
		soapModel.setPriceProtectionStatus(model.getPriceProtectionStatus());
		soapModel.setRecordLockStatus(model.getRecordLockStatus());
		soapModel.setResetEligible(model.getResetEligible());
		soapModel.setBatchId(model.getBatchId());
		soapModel.setPriceToleranceInterval(model.getPriceToleranceInterval());
		soapModel.setNetPriceType(model.getNetPriceType());
		soapModel.setPriceRevision(model.getPriceRevision());
		soapModel.setResetFrequency(model.getResetFrequency());
		soapModel.setResetInterval(model.getResetInterval());
		soapModel.setBasePriceType(model.getBasePriceType());
		soapModel.setBasePriceEntry(model.getBasePriceEntry());
		soapModel.setBasePriceDate(model.getBasePriceDate());
		soapModel.setNetBasePrice(model.getNetBasePrice());
		soapModel.setBasePriceDdlb(model.getBasePriceDdlb());
		soapModel.setSubsequentPeriodPriceType(model.getSubsequentPeriodPriceType());
		soapModel.setNetSubsequentPeriodPrice(model.getNetSubsequentPeriodPrice());
		soapModel.setNetSubsequentPriceFormulaId(model.getNetSubsequentPriceFormulaId());
		soapModel.setResetPriceType(model.getResetPriceType());
		soapModel.setNetResetPriceType(model.getNetResetPriceType());
		soapModel.setNetResetPriceFormulaId(model.getNetResetPriceFormulaId());
		soapModel.setNetBasePriceFormulaId(model.getNetBasePriceFormulaId());

		return soapModel;
	}

	public static PsDetailsSoap[] toSoapModels(PsDetails[] models) {
		PsDetailsSoap[] soapModels = new PsDetailsSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static PsDetailsSoap[][] toSoapModels(PsDetails[][] models) {
		PsDetailsSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new PsDetailsSoap[models.length][models[0].length];
		}
		else {
			soapModels = new PsDetailsSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static PsDetailsSoap[] toSoapModels(List<PsDetails> models) {
		List<PsDetailsSoap> soapModels = new ArrayList<PsDetailsSoap>(models.size());

		for (PsDetails model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new PsDetailsSoap[soapModels.size()]);
	}

	public PsDetailsSoap() {
	}

	public int getPrimaryKey() {
		return _psDetailsSid;
	}

	public void setPrimaryKey(int pk) {
		setPsDetailsSid(pk);
	}

	public int getNepFormula() {
		return _nepFormula;
	}

	public void setNepFormula(int nepFormula) {
		_nepFormula = nepFormula;
	}

	public double getPrice() {
		return _price;
	}

	public void setPrice(double price) {
		_price = price;
	}

	public int getItemMasterSid() {
		return _itemMasterSid;
	}

	public void setItemMasterSid(int itemMasterSid) {
		_itemMasterSid = itemMasterSid;
	}

	public int getResetType() {
		return _resetType;
	}

	public void setResetType(int resetType) {
		_resetType = resetType;
	}

	public Date getPriceProtectionStartDate() {
		return _priceProtectionStartDate;
	}

	public void setPriceProtectionStartDate(Date priceProtectionStartDate) {
		_priceProtectionStartDate = priceProtectionStartDate;
	}

	public Date getResetDate() {
		return _resetDate;
	}

	public void setResetDate(Date resetDate) {
		_resetDate = resetDate;
	}

	public double getBasePrice() {
		return _basePrice;
	}

	public void setBasePrice(double basePrice) {
		_basePrice = basePrice;
	}

	public Date getItemPsAttachedDate() {
		return _itemPsAttachedDate;
	}

	public void setItemPsAttachedDate(Date itemPsAttachedDate) {
		_itemPsAttachedDate = itemPsAttachedDate;
	}

	public String getBrandMasterSid() {
		return _brandMasterSid;
	}

	public void setBrandMasterSid(String brandMasterSid) {
		_brandMasterSid = brandMasterSid;
	}

	public int getStatus() {
		return _status;
	}

	public void setStatus(int status) {
		_status = status;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public int getItemPsAttachedStatus() {
		return _itemPsAttachedStatus;
	}

	public void setItemPsAttachedStatus(int itemPsAttachedStatus) {
		_itemPsAttachedStatus = itemPsAttachedStatus;
	}

	public Date getRevisionDate() {
		return _revisionDate;
	}

	public void setRevisionDate(Date revisionDate) {
		_revisionDate = revisionDate;
	}

	public double getPriceTolerance() {
		return _priceTolerance;
	}

	public void setPriceTolerance(double priceTolerance) {
		_priceTolerance = priceTolerance;
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

	public String getSource() {
		return _source;
	}

	public void setSource(String source) {
		_source = source;
	}

	public int getPsDetailsSid() {
		return _psDetailsSid;
	}

	public void setPsDetailsSid(int psDetailsSid) {
		_psDetailsSid = psDetailsSid;
	}

	public int getPsModelSid() {
		return _psModelSid;
	}

	public void setPsModelSid(int psModelSid) {
		_psModelSid = psModelSid;
	}

	public double getSuggestedPrice() {
		return _suggestedPrice;
	}

	public void setSuggestedPrice(double suggestedPrice) {
		_suggestedPrice = suggestedPrice;
	}

	public String getNetPriceTypeFormula() {
		return _netPriceTypeFormula;
	}

	public void setNetPriceTypeFormula(String netPriceTypeFormula) {
		_netPriceTypeFormula = netPriceTypeFormula;
	}

	public int getPriceProtectionPriceType() {
		return _priceProtectionPriceType;
	}

	public void setPriceProtectionPriceType(int priceProtectionPriceType) {
		_priceProtectionPriceType = priceProtectionPriceType;
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

	public double getContractPrice() {
		return _contractPrice;
	}

	public void setContractPrice(double contractPrice) {
		_contractPrice = contractPrice;
	}

	public int getIfpModelSid() {
		return _ifpModelSid;
	}

	public void setIfpModelSid(int ifpModelSid) {
		_ifpModelSid = ifpModelSid;
	}

	public int getPriceToleranceType() {
		return _priceToleranceType;
	}

	public void setPriceToleranceType(int priceToleranceType) {
		_priceToleranceType = priceToleranceType;
	}

	public double getMaxIncrementalChange() {
		return _maxIncrementalChange;
	}

	public void setMaxIncrementalChange(double maxIncrementalChange) {
		_maxIncrementalChange = maxIncrementalChange;
	}

	public int getItemPricingQualifierSid() {
		return _itemPricingQualifierSid;
	}

	public void setItemPricingQualifierSid(int itemPricingQualifierSid) {
		_itemPricingQualifierSid = itemPricingQualifierSid;
	}

	public Date getContractPriceEndDate() {
		return _contractPriceEndDate;
	}

	public void setContractPriceEndDate(Date contractPriceEndDate) {
		_contractPriceEndDate = contractPriceEndDate;
	}

	public double getNep() {
		return _nep;
	}

	public void setNep(double nep) {
		_nep = nep;
	}

	public Date getContractPriceStartDate() {
		return _contractPriceStartDate;
	}

	public void setContractPriceStartDate(Date contractPriceStartDate) {
		_contractPriceStartDate = contractPriceStartDate;
	}

	public int getPriceToleranceFrequency() {
		return _priceToleranceFrequency;
	}

	public void setPriceToleranceFrequency(int priceToleranceFrequency) {
		_priceToleranceFrequency = priceToleranceFrequency;
	}

	public Date getPriceProtectionEndDate() {
		return _priceProtectionEndDate;
	}

	public void setPriceProtectionEndDate(Date priceProtectionEndDate) {
		_priceProtectionEndDate = priceProtectionEndDate;
	}

	public int getPriceProtectionStatus() {
		return _priceProtectionStatus;
	}

	public void setPriceProtectionStatus(int priceProtectionStatus) {
		_priceProtectionStatus = priceProtectionStatus;
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

	public int getResetEligible() {
		return _resetEligible;
	}

	public void setResetEligible(int resetEligible) {
		_resetEligible = resetEligible;
	}

	public String getBatchId() {
		return _batchId;
	}

	public void setBatchId(String batchId) {
		_batchId = batchId;
	}

	public int getPriceToleranceInterval() {
		return _priceToleranceInterval;
	}

	public void setPriceToleranceInterval(int priceToleranceInterval) {
		_priceToleranceInterval = priceToleranceInterval;
	}

	public int getNetPriceType() {
		return _netPriceType;
	}

	public void setNetPriceType(int netPriceType) {
		_netPriceType = netPriceType;
	}

	public double getPriceRevision() {
		return _priceRevision;
	}

	public void setPriceRevision(double priceRevision) {
		_priceRevision = priceRevision;
	}

	public int getResetFrequency() {
		return _resetFrequency;
	}

	public void setResetFrequency(int resetFrequency) {
		_resetFrequency = resetFrequency;
	}

	public int getResetInterval() {
		return _resetInterval;
	}

	public void setResetInterval(int resetInterval) {
		_resetInterval = resetInterval;
	}

	public int getBasePriceType() {
		return _basePriceType;
	}

	public void setBasePriceType(int basePriceType) {
		_basePriceType = basePriceType;
	}

	public double getBasePriceEntry() {
		return _basePriceEntry;
	}

	public void setBasePriceEntry(double basePriceEntry) {
		_basePriceEntry = basePriceEntry;
	}

	public Date getBasePriceDate() {
		return _basePriceDate;
	}

	public void setBasePriceDate(Date basePriceDate) {
		_basePriceDate = basePriceDate;
	}

	public int getNetBasePrice() {
		return _netBasePrice;
	}

	public void setNetBasePrice(int netBasePrice) {
		_netBasePrice = netBasePrice;
	}

	public int getBasePriceDdlb() {
		return _basePriceDdlb;
	}

	public void setBasePriceDdlb(int basePriceDdlb) {
		_basePriceDdlb = basePriceDdlb;
	}

	public int getSubsequentPeriodPriceType() {
		return _subsequentPeriodPriceType;
	}

	public void setSubsequentPeriodPriceType(int subsequentPeriodPriceType) {
		_subsequentPeriodPriceType = subsequentPeriodPriceType;
	}

	public int getNetSubsequentPeriodPrice() {
		return _netSubsequentPeriodPrice;
	}

	public void setNetSubsequentPeriodPrice(int netSubsequentPeriodPrice) {
		_netSubsequentPeriodPrice = netSubsequentPeriodPrice;
	}

	public int getNetSubsequentPriceFormulaId() {
		return _netSubsequentPriceFormulaId;
	}

	public void setNetSubsequentPriceFormulaId(int netSubsequentPriceFormulaId) {
		_netSubsequentPriceFormulaId = netSubsequentPriceFormulaId;
	}

	public int getResetPriceType() {
		return _resetPriceType;
	}

	public void setResetPriceType(int resetPriceType) {
		_resetPriceType = resetPriceType;
	}

	public int getNetResetPriceType() {
		return _netResetPriceType;
	}

	public void setNetResetPriceType(int netResetPriceType) {
		_netResetPriceType = netResetPriceType;
	}

	public int getNetResetPriceFormulaId() {
		return _netResetPriceFormulaId;
	}

	public void setNetResetPriceFormulaId(int netResetPriceFormulaId) {
		_netResetPriceFormulaId = netResetPriceFormulaId;
	}

	public int getNetBasePriceFormulaId() {
		return _netBasePriceFormulaId;
	}

	public void setNetBasePriceFormulaId(int netBasePriceFormulaId) {
		_netBasePriceFormulaId = netBasePriceFormulaId;
	}

	private int _nepFormula;
	private double _price;
	private int _itemMasterSid;
	private int _resetType;
	private Date _priceProtectionStartDate;
	private Date _resetDate;
	private double _basePrice;
	private Date _itemPsAttachedDate;
	private String _brandMasterSid;
	private int _status;
	private Date _modifiedDate;
	private int _itemPsAttachedStatus;
	private Date _revisionDate;
	private double _priceTolerance;
	private Date _createdDate;
	private int _createdBy;
	private String _source;
	private int _psDetailsSid;
	private int _psModelSid;
	private double _suggestedPrice;
	private String _netPriceTypeFormula;
	private int _priceProtectionPriceType;
	private int _modifiedBy;
	private String _inboundStatus;
	private double _contractPrice;
	private int _ifpModelSid;
	private int _priceToleranceType;
	private double _maxIncrementalChange;
	private int _itemPricingQualifierSid;
	private Date _contractPriceEndDate;
	private double _nep;
	private Date _contractPriceStartDate;
	private int _priceToleranceFrequency;
	private Date _priceProtectionEndDate;
	private int _priceProtectionStatus;
	private boolean _recordLockStatus;
	private int _resetEligible;
	private String _batchId;
	private int _priceToleranceInterval;
	private int _netPriceType;
	private double _priceRevision;
	private int _resetFrequency;
	private int _resetInterval;
	private int _basePriceType;
	private double _basePriceEntry;
	private Date _basePriceDate;
	private int _netBasePrice;
	private int _basePriceDdlb;
	private int _subsequentPeriodPriceType;
	private int _netSubsequentPeriodPrice;
	private int _netSubsequentPriceFormulaId;
	private int _resetPriceType;
	private int _netResetPriceType;
	private int _netResetPriceFormulaId;
	private int _netBasePriceFormulaId;
}