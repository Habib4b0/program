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
public class ReturnsMasterSoap implements Serializable {
	public static ReturnsMasterSoap toSoapModel(ReturnsMaster model) {
		ReturnsMasterSoap soapModel = new ReturnsMasterSoap();

		soapModel.setAdjValueAtOrigAsp(model.getAdjValueAtOrigAsp());
		soapModel.setItemMasterSid(model.getItemMasterSid());
		soapModel.setFirstReturn(model.getFirstReturn());
		soapModel.setAsp(model.getAsp());
		soapModel.setMaxExpiredMonthPlusCutoff(model.getMaxExpiredMonthPlusCutoff());
		soapModel.setPosEstimatedReturnUnits(model.getPosEstimatedReturnUnits());
		soapModel.setOrigSaleMonthCutOff(model.getOrigSaleMonthCutOff());
		soapModel.setCalcUsed(model.getCalcUsed());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setBrandMasterSid(model.getBrandMasterSid());
		soapModel.setLastReturn(model.getLastReturn());
		soapModel.setExpectedReturnUnits(model.getExpectedReturnUnits());
		soapModel.setCreatedDate(model.getCreatedDate());
		soapModel.setCreatedBy(model.getCreatedBy());
		soapModel.setSource(model.getSource());
		soapModel.setVersion(model.getVersion());
		soapModel.setAddChgDelIndicator(model.getAddChgDelIndicator());
		soapModel.setReturnsMasterSid(model.getReturnsMasterSid());
		soapModel.setWeightedAvgMonths(model.getWeightedAvgMonths());
		soapModel.setModifiedBy(model.getModifiedBy());
		soapModel.setInboundStatus(model.getInboundStatus());
		soapModel.setPct25th(model.getPct25th());
		soapModel.setLoadDate(model.getLoadDate());
		soapModel.setMaxExpiredMonth(model.getMaxExpiredMonth());
		soapModel.setActualReturnRate(model.getActualReturnRate());
		soapModel.setRreserveId(model.getRreserveId());
		soapModel.setReturnComplete(model.getReturnComplete());
		soapModel.setExpectedReturnRate(model.getExpectedReturnRate());
		soapModel.setPct50th(model.getPct50th());
		soapModel.setWithin50qrtile(model.getWithin50qrtile());
		soapModel.setCumReturnUnits(model.getCumReturnUnits());
		soapModel.setOrigSaleMonth(model.getOrigSaleMonth());
		soapModel.setDescription(model.getDescription());
		soapModel.setSku(model.getSku());
		soapModel.setUpperLimit(model.getUpperLimit());
		soapModel.setLowerLimit(model.getLowerLimit());
		soapModel.setValueAtOrigAsp(model.getValueAtOrigAsp());
		soapModel.setAdjEstimatedReturnUnits(model.getAdjEstimatedReturnUnits());
		soapModel.setPct75th(model.getPct75th());
		soapModel.setRecordLockStatus(model.getRecordLockStatus());
		soapModel.setPastExpiration(model.getPastExpiration());
		soapModel.setBatchId(model.getBatchId());
		soapModel.setMaximum(model.getMaximum());
		soapModel.setOrigSaleUnits(model.getOrigSaleUnits());
		soapModel.setBrand(model.getBrand());
		soapModel.setOrigSaleDollars(model.getOrigSaleDollars());

		return soapModel;
	}

	public static ReturnsMasterSoap[] toSoapModels(ReturnsMaster[] models) {
		ReturnsMasterSoap[] soapModels = new ReturnsMasterSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ReturnsMasterSoap[][] toSoapModels(ReturnsMaster[][] models) {
		ReturnsMasterSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ReturnsMasterSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ReturnsMasterSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ReturnsMasterSoap[] toSoapModels(List<ReturnsMaster> models) {
		List<ReturnsMasterSoap> soapModels = new ArrayList<ReturnsMasterSoap>(models.size());

		for (ReturnsMaster model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ReturnsMasterSoap[soapModels.size()]);
	}

	public ReturnsMasterSoap() {
	}

	public int getPrimaryKey() {
		return _returnsMasterSid;
	}

	public void setPrimaryKey(int pk) {
		setReturnsMasterSid(pk);
	}

	public String getAdjValueAtOrigAsp() {
		return _adjValueAtOrigAsp;
	}

	public void setAdjValueAtOrigAsp(String adjValueAtOrigAsp) {
		_adjValueAtOrigAsp = adjValueAtOrigAsp;
	}

	public int getItemMasterSid() {
		return _itemMasterSid;
	}

	public void setItemMasterSid(int itemMasterSid) {
		_itemMasterSid = itemMasterSid;
	}

	public Date getFirstReturn() {
		return _firstReturn;
	}

	public void setFirstReturn(Date firstReturn) {
		_firstReturn = firstReturn;
	}

	public String getAsp() {
		return _asp;
	}

	public void setAsp(String asp) {
		_asp = asp;
	}

	public Date getMaxExpiredMonthPlusCutoff() {
		return _maxExpiredMonthPlusCutoff;
	}

	public void setMaxExpiredMonthPlusCutoff(Date maxExpiredMonthPlusCutoff) {
		_maxExpiredMonthPlusCutoff = maxExpiredMonthPlusCutoff;
	}

	public String getPosEstimatedReturnUnits() {
		return _posEstimatedReturnUnits;
	}

	public void setPosEstimatedReturnUnits(String posEstimatedReturnUnits) {
		_posEstimatedReturnUnits = posEstimatedReturnUnits;
	}

	public Date getOrigSaleMonthCutOff() {
		return _origSaleMonthCutOff;
	}

	public void setOrigSaleMonthCutOff(Date origSaleMonthCutOff) {
		_origSaleMonthCutOff = origSaleMonthCutOff;
	}

	public String getCalcUsed() {
		return _calcUsed;
	}

	public void setCalcUsed(String calcUsed) {
		_calcUsed = calcUsed;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public int getBrandMasterSid() {
		return _brandMasterSid;
	}

	public void setBrandMasterSid(int brandMasterSid) {
		_brandMasterSid = brandMasterSid;
	}

	public Date getLastReturn() {
		return _lastReturn;
	}

	public void setLastReturn(Date lastReturn) {
		_lastReturn = lastReturn;
	}

	public String getExpectedReturnUnits() {
		return _expectedReturnUnits;
	}

	public void setExpectedReturnUnits(String expectedReturnUnits) {
		_expectedReturnUnits = expectedReturnUnits;
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

	public String getSource() {
		return _source;
	}

	public void setSource(String source) {
		_source = source;
	}

	public String getVersion() {
		return _version;
	}

	public void setVersion(String version) {
		_version = version;
	}

	public String getAddChgDelIndicator() {
		return _addChgDelIndicator;
	}

	public void setAddChgDelIndicator(String addChgDelIndicator) {
		_addChgDelIndicator = addChgDelIndicator;
	}

	public int getReturnsMasterSid() {
		return _returnsMasterSid;
	}

	public void setReturnsMasterSid(int returnsMasterSid) {
		_returnsMasterSid = returnsMasterSid;
	}

	public String getWeightedAvgMonths() {
		return _weightedAvgMonths;
	}

	public void setWeightedAvgMonths(String weightedAvgMonths) {
		_weightedAvgMonths = weightedAvgMonths;
	}

	public String getModifiedBy() {
		return _modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		_modifiedBy = modifiedBy;
	}

	public String getInboundStatus() {
		return _inboundStatus;
	}

	public void setInboundStatus(String inboundStatus) {
		_inboundStatus = inboundStatus;
	}

	public String getPct25th() {
		return _pct25th;
	}

	public void setPct25th(String pct25th) {
		_pct25th = pct25th;
	}

	public Date getLoadDate() {
		return _loadDate;
	}

	public void setLoadDate(Date loadDate) {
		_loadDate = loadDate;
	}

	public Date getMaxExpiredMonth() {
		return _maxExpiredMonth;
	}

	public void setMaxExpiredMonth(Date maxExpiredMonth) {
		_maxExpiredMonth = maxExpiredMonth;
	}

	public String getActualReturnRate() {
		return _actualReturnRate;
	}

	public void setActualReturnRate(String actualReturnRate) {
		_actualReturnRate = actualReturnRate;
	}

	public String getRreserveId() {
		return _rreserveId;
	}

	public void setRreserveId(String rreserveId) {
		_rreserveId = rreserveId;
	}

	public String getReturnComplete() {
		return _returnComplete;
	}

	public void setReturnComplete(String returnComplete) {
		_returnComplete = returnComplete;
	}

	public String getExpectedReturnRate() {
		return _expectedReturnRate;
	}

	public void setExpectedReturnRate(String expectedReturnRate) {
		_expectedReturnRate = expectedReturnRate;
	}

	public String getPct50th() {
		return _pct50th;
	}

	public void setPct50th(String pct50th) {
		_pct50th = pct50th;
	}

	public String getWithin50qrtile() {
		return _within50qrtile;
	}

	public void setWithin50qrtile(String within50qrtile) {
		_within50qrtile = within50qrtile;
	}

	public String getCumReturnUnits() {
		return _cumReturnUnits;
	}

	public void setCumReturnUnits(String cumReturnUnits) {
		_cumReturnUnits = cumReturnUnits;
	}

	public Date getOrigSaleMonth() {
		return _origSaleMonth;
	}

	public void setOrigSaleMonth(Date origSaleMonth) {
		_origSaleMonth = origSaleMonth;
	}

	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		_description = description;
	}

	public String getSku() {
		return _sku;
	}

	public void setSku(String sku) {
		_sku = sku;
	}

	public String getUpperLimit() {
		return _upperLimit;
	}

	public void setUpperLimit(String upperLimit) {
		_upperLimit = upperLimit;
	}

	public String getLowerLimit() {
		return _lowerLimit;
	}

	public void setLowerLimit(String lowerLimit) {
		_lowerLimit = lowerLimit;
	}

	public String getValueAtOrigAsp() {
		return _valueAtOrigAsp;
	}

	public void setValueAtOrigAsp(String valueAtOrigAsp) {
		_valueAtOrigAsp = valueAtOrigAsp;
	}

	public String getAdjEstimatedReturnUnits() {
		return _adjEstimatedReturnUnits;
	}

	public void setAdjEstimatedReturnUnits(String adjEstimatedReturnUnits) {
		_adjEstimatedReturnUnits = adjEstimatedReturnUnits;
	}

	public String getPct75th() {
		return _pct75th;
	}

	public void setPct75th(String pct75th) {
		_pct75th = pct75th;
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

	public String getPastExpiration() {
		return _pastExpiration;
	}

	public void setPastExpiration(String pastExpiration) {
		_pastExpiration = pastExpiration;
	}

	public String getBatchId() {
		return _batchId;
	}

	public void setBatchId(String batchId) {
		_batchId = batchId;
	}

	public String getMaximum() {
		return _maximum;
	}

	public void setMaximum(String maximum) {
		_maximum = maximum;
	}

	public String getOrigSaleUnits() {
		return _origSaleUnits;
	}

	public void setOrigSaleUnits(String origSaleUnits) {
		_origSaleUnits = origSaleUnits;
	}

	public String getBrand() {
		return _brand;
	}

	public void setBrand(String brand) {
		_brand = brand;
	}

	public String getOrigSaleDollars() {
		return _origSaleDollars;
	}

	public void setOrigSaleDollars(String origSaleDollars) {
		_origSaleDollars = origSaleDollars;
	}

	private String _adjValueAtOrigAsp;
	private int _itemMasterSid;
	private Date _firstReturn;
	private String _asp;
	private Date _maxExpiredMonthPlusCutoff;
	private String _posEstimatedReturnUnits;
	private Date _origSaleMonthCutOff;
	private String _calcUsed;
	private Date _modifiedDate;
	private int _brandMasterSid;
	private Date _lastReturn;
	private String _expectedReturnUnits;
	private Date _createdDate;
	private String _createdBy;
	private String _source;
	private String _version;
	private String _addChgDelIndicator;
	private int _returnsMasterSid;
	private String _weightedAvgMonths;
	private String _modifiedBy;
	private String _inboundStatus;
	private String _pct25th;
	private Date _loadDate;
	private Date _maxExpiredMonth;
	private String _actualReturnRate;
	private String _rreserveId;
	private String _returnComplete;
	private String _expectedReturnRate;
	private String _pct50th;
	private String _within50qrtile;
	private String _cumReturnUnits;
	private Date _origSaleMonth;
	private String _description;
	private String _sku;
	private String _upperLimit;
	private String _lowerLimit;
	private String _valueAtOrigAsp;
	private String _adjEstimatedReturnUnits;
	private String _pct75th;
	private boolean _recordLockStatus;
	private String _pastExpiration;
	private String _batchId;
	private String _maximum;
	private String _origSaleUnits;
	private String _brand;
	private String _origSaleDollars;
}