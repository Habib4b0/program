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
public class GcmGlobalDetailsSoap implements Serializable {
	public static GcmGlobalDetailsSoap toSoapModel(GcmGlobalDetails model) {
		GcmGlobalDetailsSoap soapModel = new GcmGlobalDetailsSoap();

		soapModel.setItemStatus(model.getItemStatus());
		soapModel.setFormulaMethodId(model.getFormulaMethodId());
		soapModel.setModuleName(model.getModuleName());
		soapModel.setPaymentFrequency(model.getPaymentFrequency());
		soapModel.setEndDate(model.getEndDate());
		soapModel.setCfpStartDate(model.getCfpStartDate());
		soapModel.setPriceProtectionStartDate(model.getPriceProtectionStartDate());
		soapModel.setTempItemMasterSid(model.getTempItemMasterSid());
		soapModel.setBrandName(model.getBrandName());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setContractMasterSid(model.getContractMasterSid());
		soapModel.setModifiedBy(model.getModifiedBy());
		soapModel.setSubModuleName(model.getSubModuleName());
		soapModel.setTheraputicClass(model.getTheraputicClass());
		soapModel.setGcmGlobalDetailsSid(model.getGcmGlobalDetailsSid());
		soapModel.setCheckRecord(model.getCheckRecord());
		soapModel.setPaymentMethod(model.getPaymentMethod());
		soapModel.setContractPriceEndDate(model.getContractPriceEndDate());
		soapModel.setPsContractSid(model.getPsContractSid());
		soapModel.setPriceProtectionEndDate(model.getPriceProtectionEndDate());
		soapModel.setStartDate(model.getStartDate());
		soapModel.setScreenName(model.getScreenName());
		soapModel.setRsContractSid(model.getRsContractSid());
		soapModel.setItemName(model.getItemName());
		soapModel.setSessionId(model.getSessionId());
		soapModel.setCfpStatus(model.getCfpStatus());
		soapModel.setRsModelSid(model.getRsModelSid());
		soapModel.setCfpContractSid(model.getCfpContractSid());
		soapModel.setPrice(model.getPrice());
		soapModel.setTempEndDate(model.getTempEndDate());
		soapModel.setItemMasterSid(model.getItemMasterSid());
		soapModel.setItemType(model.getItemType());
		soapModel.setForecastingType(model.getForecastingType());
		soapModel.setItemId(model.getItemId());
		soapModel.setBasePrice(model.getBasePrice());
		soapModel.setStatus(model.getStatus());
		soapModel.setFormulaName(model.getFormulaName());
		soapModel.setWorkflowMasterSid(model.getWorkflowMasterSid());
		soapModel.setPriceTolerance(model.getPriceTolerance());
		soapModel.setCreatedBy(model.getCreatedBy());
		soapModel.setCreatedDate(model.getCreatedDate());
		soapModel.setTempStartDate(model.getTempStartDate());
		soapModel.setCfpEndDate(model.getCfpEndDate());
		soapModel.setPsModelSid(model.getPsModelSid());
		soapModel.setFormulaId(model.getFormulaId());
		soapModel.setItemNo(model.getItemNo());
		soapModel.setContractPrice(model.getContractPrice());
		soapModel.setIfpModelSid(model.getIfpModelSid());
		soapModel.setPriceToleranceType(model.getPriceToleranceType());
		soapModel.setRebateAmount(model.getRebateAmount());
		soapModel.setUserId(model.getUserId());
		soapModel.setProjectionMasterSid(model.getProjectionMasterSid());
		soapModel.setContractPriceStartDate(model.getContractPriceStartDate());
		soapModel.setPriceToleranceFrequency(model.getPriceToleranceFrequency());
		soapModel.setIfpContractAttachedStatus(model.getIfpContractAttachedStatus());
		soapModel.setRebatePlanSystemId(model.getRebatePlanSystemId());
		soapModel.setRebatePlanName(model.getRebatePlanName());
		soapModel.setCalendar(model.getCalendar());
		soapModel.setPricingQualifierSid(model.getPricingQualifierSid());
		soapModel.setTempStatus(model.getTempStatus());
		soapModel.setItemRebateEndDate(model.getItemRebateEndDate());
		soapModel.setPriceToleranceInterval(model.getPriceToleranceInterval());
		soapModel.setItemRebateStartDate(model.getItemRebateStartDate());
		soapModel.setOperation(model.getOperation());
		soapModel.setCfpModelSid(model.getCfpModelSid());
		soapModel.setItemStatusSid(model.getItemStatusSid());
		soapModel.setIfpContractSid(model.getIfpContractSid());

		return soapModel;
	}

	public static GcmGlobalDetailsSoap[] toSoapModels(GcmGlobalDetails[] models) {
		GcmGlobalDetailsSoap[] soapModels = new GcmGlobalDetailsSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static GcmGlobalDetailsSoap[][] toSoapModels(
		GcmGlobalDetails[][] models) {
		GcmGlobalDetailsSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new GcmGlobalDetailsSoap[models.length][models[0].length];
		}
		else {
			soapModels = new GcmGlobalDetailsSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static GcmGlobalDetailsSoap[] toSoapModels(
		List<GcmGlobalDetails> models) {
		List<GcmGlobalDetailsSoap> soapModels = new ArrayList<GcmGlobalDetailsSoap>(models.size());

		for (GcmGlobalDetails model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new GcmGlobalDetailsSoap[soapModels.size()]);
	}

	public GcmGlobalDetailsSoap() {
	}

	public int getPrimaryKey() {
		return _gcmGlobalDetailsSid;
	}

	public void setPrimaryKey(int pk) {
		setGcmGlobalDetailsSid(pk);
	}

	public String getItemStatus() {
		return _itemStatus;
	}

	public void setItemStatus(String itemStatus) {
		_itemStatus = itemStatus;
	}

	public String getFormulaMethodId() {
		return _formulaMethodId;
	}

	public void setFormulaMethodId(String formulaMethodId) {
		_formulaMethodId = formulaMethodId;
	}

	public String getModuleName() {
		return _moduleName;
	}

	public void setModuleName(String moduleName) {
		_moduleName = moduleName;
	}

	public String getPaymentFrequency() {
		return _paymentFrequency;
	}

	public void setPaymentFrequency(String paymentFrequency) {
		_paymentFrequency = paymentFrequency;
	}

	public Date getEndDate() {
		return _endDate;
	}

	public void setEndDate(Date endDate) {
		_endDate = endDate;
	}

	public Date getCfpStartDate() {
		return _cfpStartDate;
	}

	public void setCfpStartDate(Date cfpStartDate) {
		_cfpStartDate = cfpStartDate;
	}

	public Date getPriceProtectionStartDate() {
		return _priceProtectionStartDate;
	}

	public void setPriceProtectionStartDate(Date priceProtectionStartDate) {
		_priceProtectionStartDate = priceProtectionStartDate;
	}

	public String getTempItemMasterSid() {
		return _tempItemMasterSid;
	}

	public void setTempItemMasterSid(String tempItemMasterSid) {
		_tempItemMasterSid = tempItemMasterSid;
	}

	public String getBrandName() {
		return _brandName;
	}

	public void setBrandName(String brandName) {
		_brandName = brandName;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public int getContractMasterSid() {
		return _contractMasterSid;
	}

	public void setContractMasterSid(int contractMasterSid) {
		_contractMasterSid = contractMasterSid;
	}

	public int getModifiedBy() {
		return _modifiedBy;
	}

	public void setModifiedBy(int modifiedBy) {
		_modifiedBy = modifiedBy;
	}

	public String getSubModuleName() {
		return _subModuleName;
	}

	public void setSubModuleName(String subModuleName) {
		_subModuleName = subModuleName;
	}

	public String getTheraputicClass() {
		return _theraputicClass;
	}

	public void setTheraputicClass(String theraputicClass) {
		_theraputicClass = theraputicClass;
	}

	public int getGcmGlobalDetailsSid() {
		return _gcmGlobalDetailsSid;
	}

	public void setGcmGlobalDetailsSid(int gcmGlobalDetailsSid) {
		_gcmGlobalDetailsSid = gcmGlobalDetailsSid;
	}

	public boolean getCheckRecord() {
		return _checkRecord;
	}

	public boolean isCheckRecord() {
		return _checkRecord;
	}

	public void setCheckRecord(boolean checkRecord) {
		_checkRecord = checkRecord;
	}

	public String getPaymentMethod() {
		return _paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		_paymentMethod = paymentMethod;
	}

	public Date getContractPriceEndDate() {
		return _contractPriceEndDate;
	}

	public void setContractPriceEndDate(Date contractPriceEndDate) {
		_contractPriceEndDate = contractPriceEndDate;
	}

	public int getPsContractSid() {
		return _psContractSid;
	}

	public void setPsContractSid(int psContractSid) {
		_psContractSid = psContractSid;
	}

	public Date getPriceProtectionEndDate() {
		return _priceProtectionEndDate;
	}

	public void setPriceProtectionEndDate(Date priceProtectionEndDate) {
		_priceProtectionEndDate = priceProtectionEndDate;
	}

	public Date getStartDate() {
		return _startDate;
	}

	public void setStartDate(Date startDate) {
		_startDate = startDate;
	}

	public String getScreenName() {
		return _screenName;
	}

	public void setScreenName(String screenName) {
		_screenName = screenName;
	}

	public int getRsContractSid() {
		return _rsContractSid;
	}

	public void setRsContractSid(int rsContractSid) {
		_rsContractSid = rsContractSid;
	}

	public String getItemName() {
		return _itemName;
	}

	public void setItemName(String itemName) {
		_itemName = itemName;
	}

	public String getSessionId() {
		return _sessionId;
	}

	public void setSessionId(String sessionId) {
		_sessionId = sessionId;
	}

	public String getCfpStatus() {
		return _cfpStatus;
	}

	public void setCfpStatus(String cfpStatus) {
		_cfpStatus = cfpStatus;
	}

	public int getRsModelSid() {
		return _rsModelSid;
	}

	public void setRsModelSid(int rsModelSid) {
		_rsModelSid = rsModelSid;
	}

	public int getCfpContractSid() {
		return _cfpContractSid;
	}

	public void setCfpContractSid(int cfpContractSid) {
		_cfpContractSid = cfpContractSid;
	}

	public double getPrice() {
		return _price;
	}

	public void setPrice(double price) {
		_price = price;
	}

	public Date getTempEndDate() {
		return _tempEndDate;
	}

	public void setTempEndDate(Date tempEndDate) {
		_tempEndDate = tempEndDate;
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

	public String getForecastingType() {
		return _forecastingType;
	}

	public void setForecastingType(String forecastingType) {
		_forecastingType = forecastingType;
	}

	public String getItemId() {
		return _itemId;
	}

	public void setItemId(String itemId) {
		_itemId = itemId;
	}

	public double getBasePrice() {
		return _basePrice;
	}

	public void setBasePrice(double basePrice) {
		_basePrice = basePrice;
	}

	public String getStatus() {
		return _status;
	}

	public void setStatus(String status) {
		_status = status;
	}

	public String getFormulaName() {
		return _formulaName;
	}

	public void setFormulaName(String formulaName) {
		_formulaName = formulaName;
	}

	public int getWorkflowMasterSid() {
		return _workflowMasterSid;
	}

	public void setWorkflowMasterSid(int workflowMasterSid) {
		_workflowMasterSid = workflowMasterSid;
	}

	public double getPriceTolerance() {
		return _priceTolerance;
	}

	public void setPriceTolerance(double priceTolerance) {
		_priceTolerance = priceTolerance;
	}

	public int getCreatedBy() {
		return _createdBy;
	}

	public void setCreatedBy(int createdBy) {
		_createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return _createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		_createdDate = createdDate;
	}

	public Date getTempStartDate() {
		return _tempStartDate;
	}

	public void setTempStartDate(Date tempStartDate) {
		_tempStartDate = tempStartDate;
	}

	public Date getCfpEndDate() {
		return _cfpEndDate;
	}

	public void setCfpEndDate(Date cfpEndDate) {
		_cfpEndDate = cfpEndDate;
	}

	public int getPsModelSid() {
		return _psModelSid;
	}

	public void setPsModelSid(int psModelSid) {
		_psModelSid = psModelSid;
	}

	public String getFormulaId() {
		return _formulaId;
	}

	public void setFormulaId(String formulaId) {
		_formulaId = formulaId;
	}

	public String getItemNo() {
		return _itemNo;
	}

	public void setItemNo(String itemNo) {
		_itemNo = itemNo;
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

	public int getRebateAmount() {
		return _rebateAmount;
	}

	public void setRebateAmount(int rebateAmount) {
		_rebateAmount = rebateAmount;
	}

	public int getUserId() {
		return _userId;
	}

	public void setUserId(int userId) {
		_userId = userId;
	}

	public int getProjectionMasterSid() {
		return _projectionMasterSid;
	}

	public void setProjectionMasterSid(int projectionMasterSid) {
		_projectionMasterSid = projectionMasterSid;
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

	public String getIfpContractAttachedStatus() {
		return _ifpContractAttachedStatus;
	}

	public void setIfpContractAttachedStatus(String ifpContractAttachedStatus) {
		_ifpContractAttachedStatus = ifpContractAttachedStatus;
	}

	public int getRebatePlanSystemId() {
		return _rebatePlanSystemId;
	}

	public void setRebatePlanSystemId(int rebatePlanSystemId) {
		_rebatePlanSystemId = rebatePlanSystemId;
	}

	public String getRebatePlanName() {
		return _rebatePlanName;
	}

	public void setRebatePlanName(String rebatePlanName) {
		_rebatePlanName = rebatePlanName;
	}

	public String getCalendar() {
		return _calendar;
	}

	public void setCalendar(String calendar) {
		_calendar = calendar;
	}

	public String getPricingQualifierSid() {
		return _pricingQualifierSid;
	}

	public void setPricingQualifierSid(String pricingQualifierSid) {
		_pricingQualifierSid = pricingQualifierSid;
	}

	public String getTempStatus() {
		return _tempStatus;
	}

	public void setTempStatus(String tempStatus) {
		_tempStatus = tempStatus;
	}

	public Date getItemRebateEndDate() {
		return _itemRebateEndDate;
	}

	public void setItemRebateEndDate(Date itemRebateEndDate) {
		_itemRebateEndDate = itemRebateEndDate;
	}

	public int getPriceToleranceInterval() {
		return _priceToleranceInterval;
	}

	public void setPriceToleranceInterval(int priceToleranceInterval) {
		_priceToleranceInterval = priceToleranceInterval;
	}

	public Date getItemRebateStartDate() {
		return _itemRebateStartDate;
	}

	public void setItemRebateStartDate(Date itemRebateStartDate) {
		_itemRebateStartDate = itemRebateStartDate;
	}

	public String getOperation() {
		return _operation;
	}

	public void setOperation(String operation) {
		_operation = operation;
	}

	public int getCfpModelSid() {
		return _cfpModelSid;
	}

	public void setCfpModelSid(int cfpModelSid) {
		_cfpModelSid = cfpModelSid;
	}

	public int getItemStatusSid() {
		return _itemStatusSid;
	}

	public void setItemStatusSid(int itemStatusSid) {
		_itemStatusSid = itemStatusSid;
	}

	public int getIfpContractSid() {
		return _ifpContractSid;
	}

	public void setIfpContractSid(int ifpContractSid) {
		_ifpContractSid = ifpContractSid;
	}

	private String _itemStatus;
	private String _formulaMethodId;
	private String _moduleName;
	private String _paymentFrequency;
	private Date _endDate;
	private Date _cfpStartDate;
	private Date _priceProtectionStartDate;
	private String _tempItemMasterSid;
	private String _brandName;
	private Date _modifiedDate;
	private int _contractMasterSid;
	private int _modifiedBy;
	private String _subModuleName;
	private String _theraputicClass;
	private int _gcmGlobalDetailsSid;
	private boolean _checkRecord;
	private String _paymentMethod;
	private Date _contractPriceEndDate;
	private int _psContractSid;
	private Date _priceProtectionEndDate;
	private Date _startDate;
	private String _screenName;
	private int _rsContractSid;
	private String _itemName;
	private String _sessionId;
	private String _cfpStatus;
	private int _rsModelSid;
	private int _cfpContractSid;
	private double _price;
	private Date _tempEndDate;
	private int _itemMasterSid;
	private String _itemType;
	private String _forecastingType;
	private String _itemId;
	private double _basePrice;
	private String _status;
	private String _formulaName;
	private int _workflowMasterSid;
	private double _priceTolerance;
	private int _createdBy;
	private Date _createdDate;
	private Date _tempStartDate;
	private Date _cfpEndDate;
	private int _psModelSid;
	private String _formulaId;
	private String _itemNo;
	private double _contractPrice;
	private int _ifpModelSid;
	private int _priceToleranceType;
	private int _rebateAmount;
	private int _userId;
	private int _projectionMasterSid;
	private Date _contractPriceStartDate;
	private int _priceToleranceFrequency;
	private String _ifpContractAttachedStatus;
	private int _rebatePlanSystemId;
	private String _rebatePlanName;
	private String _calendar;
	private String _pricingQualifierSid;
	private String _tempStatus;
	private Date _itemRebateEndDate;
	private int _priceToleranceInterval;
	private Date _itemRebateStartDate;
	private String _operation;
	private int _cfpModelSid;
	private int _itemStatusSid;
	private int _ifpContractSid;
}