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
public class ImtdPsDetailsSoap implements Serializable {
	public static ImtdPsDetailsSoap toSoapModel(ImtdPsDetails model) {
		ImtdPsDetailsSoap soapModel = new ImtdPsDetailsSoap();

		soapModel.setPsDetailsModifiedDate(model.getPsDetailsModifiedDate());
		soapModel.setPsDetailsSuggestedPrice(model.getPsDetailsSuggestedPrice());
		soapModel.setPsDetailsContractPrice(model.getPsDetailsContractPrice());
		soapModel.setResetDate(model.getResetDate());
		soapModel.setPsDetailsAttachedStatus(model.getPsDetailsAttachedStatus());
		soapModel.setImtdPsDetailsSid(model.getImtdPsDetailsSid());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setPsDetailsCreatedBy(model.getPsDetailsCreatedBy());
		soapModel.setContractMasterSid(model.getContractMasterSid());
		soapModel.setPsDtlsContPriceEnddate(model.getPsDtlsContPriceEnddate());
		soapModel.setPsDetailsPricPrtcnStdate(model.getPsDetailsPricPrtcnStdate());
		soapModel.setImtdCreatedDate(model.getImtdCreatedDate());
		soapModel.setNetPriceTypeFormula(model.getNetPriceTypeFormula());
		soapModel.setModifiedBy(model.getModifiedBy());
		soapModel.setMaxIncrementalChange(model.getMaxIncrementalChange());
		soapModel.setPsDetailsPricePlanId(model.getPsDetailsPricePlanId());
		soapModel.setCheckRecord(model.getCheckRecord());
		soapModel.setPsDtlsPriceToleranceFreq(model.getPsDtlsPriceToleranceFreq());
		soapModel.setItemName(model.getItemName());
		soapModel.setSessionId(model.getSessionId());
		soapModel.setResetFrequency(model.getResetFrequency());
		soapModel.setPsDtlsPriceToleranceType(model.getPsDtlsPriceToleranceType());
		soapModel.setPsDetailsPricetype(model.getPsDetailsPricetype());
		soapModel.setPsDetailsPriceRevision(model.getPsDetailsPriceRevision());
		soapModel.setResetInterval(model.getResetInterval());
		soapModel.setIfpNo(model.getIfpNo());
		soapModel.setPsDetailsAttachedDate(model.getPsDetailsAttachedDate());
		soapModel.setNepFormula(model.getNepFormula());
		soapModel.setPsDetailsModifiedBy(model.getPsDetailsModifiedBy());
		soapModel.setPsDtlsPriceToleranceIntrvl(model.getPsDtlsPriceToleranceIntrvl());
		soapModel.setItemMasterSid(model.getItemMasterSid());
		soapModel.setResetType(model.getResetType());
		soapModel.setItemId(model.getItemId());
		soapModel.setStatus(model.getStatus());
		soapModel.setBrandMasterSid(model.getBrandMasterSid());
		soapModel.setPsDetailsPrice(model.getPsDetailsPrice());
		soapModel.setPsDetailsCreatedDate(model.getPsDetailsCreatedDate());
		soapModel.setUsersSid(model.getUsersSid());
		soapModel.setCreatedBy(model.getCreatedBy());
		soapModel.setCreatedDate(model.getCreatedDate());
		soapModel.setPsDetailsSid(model.getPsDetailsSid());
		soapModel.setPsModelSid(model.getPsModelSid());
		soapModel.setPriceProtectionPriceType(model.getPriceProtectionPriceType());
		soapModel.setPsDetailsBasePrice(model.getPsDetailsBasePrice());
		soapModel.setItemNo(model.getItemNo());
		soapModel.setIfpModelSid(model.getIfpModelSid());
		soapModel.setPsDetailsRevisionDate(model.getPsDetailsRevisionDate());
		soapModel.setNep(model.getNep());
		soapModel.setPsDetailsPriceTolerance(model.getPsDetailsPriceTolerance());
		soapModel.setPriceProtectionStatus(model.getPriceProtectionStatus());
		soapModel.setPsDtlsContPriceStartdate(model.getPsDtlsContPriceStartdate());
		soapModel.setResetEligible(model.getResetEligible());
		soapModel.setNetPriceType(model.getNetPriceType());
		soapModel.setOperation(model.getOperation());
		soapModel.setCfpModelSid(model.getCfpModelSid());
		soapModel.setPsDetailsPricPrtcnEddate(model.getPsDetailsPricPrtcnEddate());
		soapModel.setBasePriceType(model.getBasePriceType());
		soapModel.setBasePriceEntry(model.getBasePriceEntry());
		soapModel.setBasePriceDate(model.getBasePriceDate());
		soapModel.setBasePriceDdlb(model.getBasePriceDdlb());
		soapModel.setNetBasePrice(model.getNetBasePrice());
		soapModel.setNetBasePriceFormulaId(model.getNetBasePriceFormulaId());
		soapModel.setNetBasePriceFormulaNo(model.getNetBasePriceFormulaNo());
		soapModel.setNetBasePriceFormulaName(model.getNetBasePriceFormulaName());
		soapModel.setSubsequentPeriodPriceType(model.getSubsequentPeriodPriceType());
		soapModel.setNetSubsequentPeriodPrice(model.getNetSubsequentPeriodPrice());
		soapModel.setNetSubsequentPriceFormulaId(model.getNetSubsequentPriceFormulaId());
		soapModel.setNetSubsequentPriceFormulaNo(model.getNetSubsequentPriceFormulaNo());
		soapModel.setNetSubsequentPriceFormulaName(model.getNetSubsequentPriceFormulaName());
		soapModel.setResetPriceType(model.getResetPriceType());
		soapModel.setNetResetPriceType(model.getNetResetPriceType());
		soapModel.setNetResetPriceFormulaId(model.getNetResetPriceFormulaId());
		soapModel.setNetResetPriceFormulaNo(model.getNetResetPriceFormulaNo());
		soapModel.setNetResetPriceFormulaName(model.getNetResetPriceFormulaName());

		return soapModel;
	}

	public static ImtdPsDetailsSoap[] toSoapModels(ImtdPsDetails[] models) {
		ImtdPsDetailsSoap[] soapModels = new ImtdPsDetailsSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ImtdPsDetailsSoap[][] toSoapModels(ImtdPsDetails[][] models) {
		ImtdPsDetailsSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ImtdPsDetailsSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ImtdPsDetailsSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ImtdPsDetailsSoap[] toSoapModels(List<ImtdPsDetails> models) {
		List<ImtdPsDetailsSoap> soapModels = new ArrayList<ImtdPsDetailsSoap>(models.size());

		for (ImtdPsDetails model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ImtdPsDetailsSoap[soapModels.size()]);
	}

	public ImtdPsDetailsSoap() {
	}

	public int getPrimaryKey() {
		return _imtdPsDetailsSid;
	}

	public void setPrimaryKey(int pk) {
		setImtdPsDetailsSid(pk);
	}

	public Date getPsDetailsModifiedDate() {
		return _psDetailsModifiedDate;
	}

	public void setPsDetailsModifiedDate(Date psDetailsModifiedDate) {
		_psDetailsModifiedDate = psDetailsModifiedDate;
	}

	public double getPsDetailsSuggestedPrice() {
		return _psDetailsSuggestedPrice;
	}

	public void setPsDetailsSuggestedPrice(double psDetailsSuggestedPrice) {
		_psDetailsSuggestedPrice = psDetailsSuggestedPrice;
	}

	public double getPsDetailsContractPrice() {
		return _psDetailsContractPrice;
	}

	public void setPsDetailsContractPrice(double psDetailsContractPrice) {
		_psDetailsContractPrice = psDetailsContractPrice;
	}

	public Date getResetDate() {
		return _resetDate;
	}

	public void setResetDate(Date resetDate) {
		_resetDate = resetDate;
	}

	public int getPsDetailsAttachedStatus() {
		return _psDetailsAttachedStatus;
	}

	public void setPsDetailsAttachedStatus(int psDetailsAttachedStatus) {
		_psDetailsAttachedStatus = psDetailsAttachedStatus;
	}

	public int getImtdPsDetailsSid() {
		return _imtdPsDetailsSid;
	}

	public void setImtdPsDetailsSid(int imtdPsDetailsSid) {
		_imtdPsDetailsSid = imtdPsDetailsSid;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public int getPsDetailsCreatedBy() {
		return _psDetailsCreatedBy;
	}

	public void setPsDetailsCreatedBy(int psDetailsCreatedBy) {
		_psDetailsCreatedBy = psDetailsCreatedBy;
	}

	public int getContractMasterSid() {
		return _contractMasterSid;
	}

	public void setContractMasterSid(int contractMasterSid) {
		_contractMasterSid = contractMasterSid;
	}

	public Date getPsDtlsContPriceEnddate() {
		return _psDtlsContPriceEnddate;
	}

	public void setPsDtlsContPriceEnddate(Date psDtlsContPriceEnddate) {
		_psDtlsContPriceEnddate = psDtlsContPriceEnddate;
	}

	public Date getPsDetailsPricPrtcnStdate() {
		return _psDetailsPricPrtcnStdate;
	}

	public void setPsDetailsPricPrtcnStdate(Date psDetailsPricPrtcnStdate) {
		_psDetailsPricPrtcnStdate = psDetailsPricPrtcnStdate;
	}

	public Date getImtdCreatedDate() {
		return _imtdCreatedDate;
	}

	public void setImtdCreatedDate(Date imtdCreatedDate) {
		_imtdCreatedDate = imtdCreatedDate;
	}

	public String getNetPriceTypeFormula() {
		return _netPriceTypeFormula;
	}

	public void setNetPriceTypeFormula(String netPriceTypeFormula) {
		_netPriceTypeFormula = netPriceTypeFormula;
	}

	public int getModifiedBy() {
		return _modifiedBy;
	}

	public void setModifiedBy(int modifiedBy) {
		_modifiedBy = modifiedBy;
	}

	public double getMaxIncrementalChange() {
		return _maxIncrementalChange;
	}

	public void setMaxIncrementalChange(double maxIncrementalChange) {
		_maxIncrementalChange = maxIncrementalChange;
	}

	public String getPsDetailsPricePlanId() {
		return _psDetailsPricePlanId;
	}

	public void setPsDetailsPricePlanId(String psDetailsPricePlanId) {
		_psDetailsPricePlanId = psDetailsPricePlanId;
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

	public int getPsDtlsPriceToleranceFreq() {
		return _psDtlsPriceToleranceFreq;
	}

	public void setPsDtlsPriceToleranceFreq(int psDtlsPriceToleranceFreq) {
		_psDtlsPriceToleranceFreq = psDtlsPriceToleranceFreq;
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

	public int getResetFrequency() {
		return _resetFrequency;
	}

	public void setResetFrequency(int resetFrequency) {
		_resetFrequency = resetFrequency;
	}

	public int getPsDtlsPriceToleranceType() {
		return _psDtlsPriceToleranceType;
	}

	public void setPsDtlsPriceToleranceType(int psDtlsPriceToleranceType) {
		_psDtlsPriceToleranceType = psDtlsPriceToleranceType;
	}

	public int getPsDetailsPricetype() {
		return _psDetailsPricetype;
	}

	public void setPsDetailsPricetype(int psDetailsPricetype) {
		_psDetailsPricetype = psDetailsPricetype;
	}

	public double getPsDetailsPriceRevision() {
		return _psDetailsPriceRevision;
	}

	public void setPsDetailsPriceRevision(double psDetailsPriceRevision) {
		_psDetailsPriceRevision = psDetailsPriceRevision;
	}

	public int getResetInterval() {
		return _resetInterval;
	}

	public void setResetInterval(int resetInterval) {
		_resetInterval = resetInterval;
	}

	public String getIfpNo() {
		return _ifpNo;
	}

	public void setIfpNo(String ifpNo) {
		_ifpNo = ifpNo;
	}

	public Date getPsDetailsAttachedDate() {
		return _psDetailsAttachedDate;
	}

	public void setPsDetailsAttachedDate(Date psDetailsAttachedDate) {
		_psDetailsAttachedDate = psDetailsAttachedDate;
	}

	public int getNepFormula() {
		return _nepFormula;
	}

	public void setNepFormula(int nepFormula) {
		_nepFormula = nepFormula;
	}

	public int getPsDetailsModifiedBy() {
		return _psDetailsModifiedBy;
	}

	public void setPsDetailsModifiedBy(int psDetailsModifiedBy) {
		_psDetailsModifiedBy = psDetailsModifiedBy;
	}

	public int getPsDtlsPriceToleranceIntrvl() {
		return _psDtlsPriceToleranceIntrvl;
	}

	public void setPsDtlsPriceToleranceIntrvl(int psDtlsPriceToleranceIntrvl) {
		_psDtlsPriceToleranceIntrvl = psDtlsPriceToleranceIntrvl;
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

	public String getItemId() {
		return _itemId;
	}

	public void setItemId(String itemId) {
		_itemId = itemId;
	}

	public int getStatus() {
		return _status;
	}

	public void setStatus(int status) {
		_status = status;
	}

	public int getBrandMasterSid() {
		return _brandMasterSid;
	}

	public void setBrandMasterSid(int brandMasterSid) {
		_brandMasterSid = brandMasterSid;
	}

	public double getPsDetailsPrice() {
		return _psDetailsPrice;
	}

	public void setPsDetailsPrice(double psDetailsPrice) {
		_psDetailsPrice = psDetailsPrice;
	}

	public Date getPsDetailsCreatedDate() {
		return _psDetailsCreatedDate;
	}

	public void setPsDetailsCreatedDate(Date psDetailsCreatedDate) {
		_psDetailsCreatedDate = psDetailsCreatedDate;
	}

	public int getUsersSid() {
		return _usersSid;
	}

	public void setUsersSid(int usersSid) {
		_usersSid = usersSid;
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

	public int getPriceProtectionPriceType() {
		return _priceProtectionPriceType;
	}

	public void setPriceProtectionPriceType(int priceProtectionPriceType) {
		_priceProtectionPriceType = priceProtectionPriceType;
	}

	public double getPsDetailsBasePrice() {
		return _psDetailsBasePrice;
	}

	public void setPsDetailsBasePrice(double psDetailsBasePrice) {
		_psDetailsBasePrice = psDetailsBasePrice;
	}

	public String getItemNo() {
		return _itemNo;
	}

	public void setItemNo(String itemNo) {
		_itemNo = itemNo;
	}

	public int getIfpModelSid() {
		return _ifpModelSid;
	}

	public void setIfpModelSid(int ifpModelSid) {
		_ifpModelSid = ifpModelSid;
	}

	public Date getPsDetailsRevisionDate() {
		return _psDetailsRevisionDate;
	}

	public void setPsDetailsRevisionDate(Date psDetailsRevisionDate) {
		_psDetailsRevisionDate = psDetailsRevisionDate;
	}

	public double getNep() {
		return _nep;
	}

	public void setNep(double nep) {
		_nep = nep;
	}

	public double getPsDetailsPriceTolerance() {
		return _psDetailsPriceTolerance;
	}

	public void setPsDetailsPriceTolerance(double psDetailsPriceTolerance) {
		_psDetailsPriceTolerance = psDetailsPriceTolerance;
	}

	public int getPriceProtectionStatus() {
		return _priceProtectionStatus;
	}

	public void setPriceProtectionStatus(int priceProtectionStatus) {
		_priceProtectionStatus = priceProtectionStatus;
	}

	public Date getPsDtlsContPriceStartdate() {
		return _psDtlsContPriceStartdate;
	}

	public void setPsDtlsContPriceStartdate(Date psDtlsContPriceStartdate) {
		_psDtlsContPriceStartdate = psDtlsContPriceStartdate;
	}

	public int getResetEligible() {
		return _resetEligible;
	}

	public void setResetEligible(int resetEligible) {
		_resetEligible = resetEligible;
	}

	public int getNetPriceType() {
		return _netPriceType;
	}

	public void setNetPriceType(int netPriceType) {
		_netPriceType = netPriceType;
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

	public Date getPsDetailsPricPrtcnEddate() {
		return _psDetailsPricPrtcnEddate;
	}

	public void setPsDetailsPricPrtcnEddate(Date psDetailsPricPrtcnEddate) {
		_psDetailsPricPrtcnEddate = psDetailsPricPrtcnEddate;
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

	public int getBasePriceDdlb() {
		return _basePriceDdlb;
	}

	public void setBasePriceDdlb(int basePriceDdlb) {
		_basePriceDdlb = basePriceDdlb;
	}

	public int getNetBasePrice() {
		return _netBasePrice;
	}

	public void setNetBasePrice(int netBasePrice) {
		_netBasePrice = netBasePrice;
	}

	public int getNetBasePriceFormulaId() {
		return _netBasePriceFormulaId;
	}

	public void setNetBasePriceFormulaId(int netBasePriceFormulaId) {
		_netBasePriceFormulaId = netBasePriceFormulaId;
	}

	public String getNetBasePriceFormulaNo() {
		return _netBasePriceFormulaNo;
	}

	public void setNetBasePriceFormulaNo(String netBasePriceFormulaNo) {
		_netBasePriceFormulaNo = netBasePriceFormulaNo;
	}

	public String getNetBasePriceFormulaName() {
		return _netBasePriceFormulaName;
	}

	public void setNetBasePriceFormulaName(String netBasePriceFormulaName) {
		_netBasePriceFormulaName = netBasePriceFormulaName;
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

	public String getNetSubsequentPriceFormulaNo() {
		return _netSubsequentPriceFormulaNo;
	}

	public void setNetSubsequentPriceFormulaNo(
		String netSubsequentPriceFormulaNo) {
		_netSubsequentPriceFormulaNo = netSubsequentPriceFormulaNo;
	}

	public String getNetSubsequentPriceFormulaName() {
		return _netSubsequentPriceFormulaName;
	}

	public void setNetSubsequentPriceFormulaName(
		String netSubsequentPriceFormulaName) {
		_netSubsequentPriceFormulaName = netSubsequentPriceFormulaName;
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

	public String getNetResetPriceFormulaNo() {
		return _netResetPriceFormulaNo;
	}

	public void setNetResetPriceFormulaNo(String netResetPriceFormulaNo) {
		_netResetPriceFormulaNo = netResetPriceFormulaNo;
	}

	public String getNetResetPriceFormulaName() {
		return _netResetPriceFormulaName;
	}

	public void setNetResetPriceFormulaName(String netResetPriceFormulaName) {
		_netResetPriceFormulaName = netResetPriceFormulaName;
	}

	private Date _psDetailsModifiedDate;
	private double _psDetailsSuggestedPrice;
	private double _psDetailsContractPrice;
	private Date _resetDate;
	private int _psDetailsAttachedStatus;
	private int _imtdPsDetailsSid;
	private Date _modifiedDate;
	private int _psDetailsCreatedBy;
	private int _contractMasterSid;
	private Date _psDtlsContPriceEnddate;
	private Date _psDetailsPricPrtcnStdate;
	private Date _imtdCreatedDate;
	private String _netPriceTypeFormula;
	private int _modifiedBy;
	private double _maxIncrementalChange;
	private String _psDetailsPricePlanId;
	private boolean _checkRecord;
	private int _psDtlsPriceToleranceFreq;
	private String _itemName;
	private String _sessionId;
	private int _resetFrequency;
	private int _psDtlsPriceToleranceType;
	private int _psDetailsPricetype;
	private double _psDetailsPriceRevision;
	private int _resetInterval;
	private String _ifpNo;
	private Date _psDetailsAttachedDate;
	private int _nepFormula;
	private int _psDetailsModifiedBy;
	private int _psDtlsPriceToleranceIntrvl;
	private int _itemMasterSid;
	private int _resetType;
	private String _itemId;
	private int _status;
	private int _brandMasterSid;
	private double _psDetailsPrice;
	private Date _psDetailsCreatedDate;
	private int _usersSid;
	private int _createdBy;
	private Date _createdDate;
	private int _psDetailsSid;
	private int _psModelSid;
	private int _priceProtectionPriceType;
	private double _psDetailsBasePrice;
	private String _itemNo;
	private int _ifpModelSid;
	private Date _psDetailsRevisionDate;
	private double _nep;
	private double _psDetailsPriceTolerance;
	private int _priceProtectionStatus;
	private Date _psDtlsContPriceStartdate;
	private int _resetEligible;
	private int _netPriceType;
	private String _operation;
	private int _cfpModelSid;
	private Date _psDetailsPricPrtcnEddate;
	private int _basePriceType;
	private double _basePriceEntry;
	private Date _basePriceDate;
	private int _basePriceDdlb;
	private int _netBasePrice;
	private int _netBasePriceFormulaId;
	private String _netBasePriceFormulaNo;
	private String _netBasePriceFormulaName;
	private int _subsequentPeriodPriceType;
	private int _netSubsequentPeriodPrice;
	private int _netSubsequentPriceFormulaId;
	private String _netSubsequentPriceFormulaNo;
	private String _netSubsequentPriceFormulaName;
	private int _resetPriceType;
	private int _netResetPriceType;
	private int _netResetPriceFormulaId;
	private String _netResetPriceFormulaNo;
	private String _netResetPriceFormulaName;
}