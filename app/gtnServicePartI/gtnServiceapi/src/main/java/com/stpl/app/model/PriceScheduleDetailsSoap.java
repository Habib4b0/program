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
public class PriceScheduleDetailsSoap implements Serializable {
	public static PriceScheduleDetailsSoap toSoapModel(
		PriceScheduleDetails model) {
		PriceScheduleDetailsSoap soapModel = new PriceScheduleDetailsSoap();

		soapModel.setPrice(model.getPrice());
		soapModel.setPsDetailsSystemId(model.getPsDetailsSystemId());
		soapModel.setCompanyFamilyplanSystemId(model.getCompanyFamilyplanSystemId());
		soapModel.setItemSystemId(model.getItemSystemId());
		soapModel.setPriceProtectionStartDate(model.getPriceProtectionStartDate());
		soapModel.setBasePrice(model.getBasePrice());
		soapModel.setRevisionDate(model.getRevisionDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setPriceScheduleSystemId(model.getPriceScheduleSystemId());
		soapModel.setItemFamilyplanSystemId(model.getItemFamilyplanSystemId());
		soapModel.setPriceTolerance(model.getPriceTolerance());
		soapModel.setCreatedBy(model.getCreatedBy());
		soapModel.setCreatedDate(model.getCreatedDate());
		soapModel.setSuggestedPrice(model.getSuggestedPrice());
		soapModel.setInboundStatus(model.getInboundStatus());
		soapModel.setModifiedBy(model.getModifiedBy());
		soapModel.setContractPrice(model.getContractPrice());
		soapModel.setPriceToleranceType(model.getPriceToleranceType());
		soapModel.setMemberFamilyplanSystemId(model.getMemberFamilyplanSystemId());
		soapModel.setContractPriceEndDate(model.getContractPriceEndDate());
		soapModel.setContractPriceStartDate(model.getContractPriceStartDate());
		soapModel.setPriceToleranceFrequency(model.getPriceToleranceFrequency());
		soapModel.setAttachedDate(model.getAttachedDate());
		soapModel.setPriceProtectionEndDate(model.getPriceProtectionEndDate());
		soapModel.setRecordLockStatus(model.getRecordLockStatus());
		soapModel.setPricePlanId(model.getPricePlanId());
		soapModel.setPriceType(model.getPriceType());
		soapModel.setBatchId(model.getBatchId());
		soapModel.setPriceToleranceInterval(model.getPriceToleranceInterval());
		soapModel.setPriceRevision(model.getPriceRevision());

		return soapModel;
	}

	public static PriceScheduleDetailsSoap[] toSoapModels(
		PriceScheduleDetails[] models) {
		PriceScheduleDetailsSoap[] soapModels = new PriceScheduleDetailsSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static PriceScheduleDetailsSoap[][] toSoapModels(
		PriceScheduleDetails[][] models) {
		PriceScheduleDetailsSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new PriceScheduleDetailsSoap[models.length][models[0].length];
		}
		else {
			soapModels = new PriceScheduleDetailsSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static PriceScheduleDetailsSoap[] toSoapModels(
		List<PriceScheduleDetails> models) {
		List<PriceScheduleDetailsSoap> soapModels = new ArrayList<PriceScheduleDetailsSoap>(models.size());

		for (PriceScheduleDetails model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new PriceScheduleDetailsSoap[soapModels.size()]);
	}

	public PriceScheduleDetailsSoap() {
	}

	public int getPrimaryKey() {
		return _psDetailsSystemId;
	}

	public void setPrimaryKey(int pk) {
		setPsDetailsSystemId(pk);
	}

	public double getPrice() {
		return _price;
	}

	public void setPrice(double price) {
		_price = price;
	}

	public int getPsDetailsSystemId() {
		return _psDetailsSystemId;
	}

	public void setPsDetailsSystemId(int psDetailsSystemId) {
		_psDetailsSystemId = psDetailsSystemId;
	}

	public int getCompanyFamilyplanSystemId() {
		return _companyFamilyplanSystemId;
	}

	public void setCompanyFamilyplanSystemId(int companyFamilyplanSystemId) {
		_companyFamilyplanSystemId = companyFamilyplanSystemId;
	}

	public int getItemSystemId() {
		return _itemSystemId;
	}

	public void setItemSystemId(int itemSystemId) {
		_itemSystemId = itemSystemId;
	}

	public Date getPriceProtectionStartDate() {
		return _priceProtectionStartDate;
	}

	public void setPriceProtectionStartDate(Date priceProtectionStartDate) {
		_priceProtectionStartDate = priceProtectionStartDate;
	}

	public double getBasePrice() {
		return _basePrice;
	}

	public void setBasePrice(double basePrice) {
		_basePrice = basePrice;
	}

	public Date getRevisionDate() {
		return _revisionDate;
	}

	public void setRevisionDate(Date revisionDate) {
		_revisionDate = revisionDate;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public int getPriceScheduleSystemId() {
		return _priceScheduleSystemId;
	}

	public void setPriceScheduleSystemId(int priceScheduleSystemId) {
		_priceScheduleSystemId = priceScheduleSystemId;
	}

	public int getItemFamilyplanSystemId() {
		return _itemFamilyplanSystemId;
	}

	public void setItemFamilyplanSystemId(int itemFamilyplanSystemId) {
		_itemFamilyplanSystemId = itemFamilyplanSystemId;
	}

	public double getPriceTolerance() {
		return _priceTolerance;
	}

	public void setPriceTolerance(double priceTolerance) {
		_priceTolerance = priceTolerance;
	}

	public String getCreatedBy() {
		return _createdBy;
	}

	public void setCreatedBy(String createdBy) {
		_createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return _createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		_createdDate = createdDate;
	}

	public double getSuggestedPrice() {
		return _suggestedPrice;
	}

	public void setSuggestedPrice(double suggestedPrice) {
		_suggestedPrice = suggestedPrice;
	}

	public String getInboundStatus() {
		return _inboundStatus;
	}

	public void setInboundStatus(String inboundStatus) {
		_inboundStatus = inboundStatus;
	}

	public String getModifiedBy() {
		return _modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		_modifiedBy = modifiedBy;
	}

	public double getContractPrice() {
		return _contractPrice;
	}

	public void setContractPrice(double contractPrice) {
		_contractPrice = contractPrice;
	}

	public String getPriceToleranceType() {
		return _priceToleranceType;
	}

	public void setPriceToleranceType(String priceToleranceType) {
		_priceToleranceType = priceToleranceType;
	}

	public int getMemberFamilyplanSystemId() {
		return _memberFamilyplanSystemId;
	}

	public void setMemberFamilyplanSystemId(int memberFamilyplanSystemId) {
		_memberFamilyplanSystemId = memberFamilyplanSystemId;
	}

	public Date getContractPriceEndDate() {
		return _contractPriceEndDate;
	}

	public void setContractPriceEndDate(Date contractPriceEndDate) {
		_contractPriceEndDate = contractPriceEndDate;
	}

	public Date getContractPriceStartDate() {
		return _contractPriceStartDate;
	}

	public void setContractPriceStartDate(Date contractPriceStartDate) {
		_contractPriceStartDate = contractPriceStartDate;
	}

	public String getPriceToleranceFrequency() {
		return _priceToleranceFrequency;
	}

	public void setPriceToleranceFrequency(String priceToleranceFrequency) {
		_priceToleranceFrequency = priceToleranceFrequency;
	}

	public Date getAttachedDate() {
		return _attachedDate;
	}

	public void setAttachedDate(Date attachedDate) {
		_attachedDate = attachedDate;
	}

	public Date getPriceProtectionEndDate() {
		return _priceProtectionEndDate;
	}

	public void setPriceProtectionEndDate(Date priceProtectionEndDate) {
		_priceProtectionEndDate = priceProtectionEndDate;
	}

	public String getRecordLockStatus() {
		return _recordLockStatus;
	}

	public void setRecordLockStatus(String recordLockStatus) {
		_recordLockStatus = recordLockStatus;
	}

	public String getPricePlanId() {
		return _pricePlanId;
	}

	public void setPricePlanId(String pricePlanId) {
		_pricePlanId = pricePlanId;
	}

	public String getPriceType() {
		return _priceType;
	}

	public void setPriceType(String priceType) {
		_priceType = priceType;
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

	public double getPriceRevision() {
		return _priceRevision;
	}

	public void setPriceRevision(double priceRevision) {
		_priceRevision = priceRevision;
	}

	private double _price;
	private int _psDetailsSystemId;
	private int _companyFamilyplanSystemId;
	private int _itemSystemId;
	private Date _priceProtectionStartDate;
	private double _basePrice;
	private Date _revisionDate;
	private Date _modifiedDate;
	private int _priceScheduleSystemId;
	private int _itemFamilyplanSystemId;
	private double _priceTolerance;
	private String _createdBy;
	private Date _createdDate;
	private double _suggestedPrice;
	private String _inboundStatus;
	private String _modifiedBy;
	private double _contractPrice;
	private String _priceToleranceType;
	private int _memberFamilyplanSystemId;
	private Date _contractPriceEndDate;
	private Date _contractPriceStartDate;
	private String _priceToleranceFrequency;
	private Date _attachedDate;
	private Date _priceProtectionEndDate;
	private String _recordLockStatus;
	private String _pricePlanId;
	private String _priceType;
	private String _batchId;
	private int _priceToleranceInterval;
	private double _priceRevision;
}