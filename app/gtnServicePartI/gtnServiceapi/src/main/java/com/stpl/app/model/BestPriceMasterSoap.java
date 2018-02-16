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
public class BestPriceMasterSoap implements Serializable {
	public static BestPriceMasterSoap toSoapModel(BestPriceMaster model) {
		BestPriceMasterSoap soapModel = new BestPriceMasterSoap();

		soapModel.setInitialBestPrice(model.getInitialBestPrice());
		soapModel.setCreatedBy(model.getCreatedBy());
		soapModel.setItemNo(model.getItemNo());
		soapModel.setModifiedBy(model.getModifiedBy());
		soapModel.setAccountId(model.getAccountId());
		soapModel.setCreatedDate(model.getCreatedDate());
		soapModel.setItemId(model.getItemId());
		soapModel.setItemDesc(model.getItemDesc());
		soapModel.setSellingPrice(model.getSellingPrice());
		soapModel.setContractId(model.getContractId());
		soapModel.setContractNo(model.getContractNo());
		soapModel.setBatchId(model.getBatchId());
		soapModel.setBestPriceMasterSid(model.getBestPriceMasterSid());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setRecordLockStatus(model.getRecordLockStatus());
		soapModel.setBeginningWacPackage(model.getBeginningWacPackage());
		soapModel.setInitialDiscount(model.getInitialDiscount());
		soapModel.setPeriod(model.getPeriod());
		soapModel.setSource(model.getSource());
		soapModel.setContractStartDate(model.getContractStartDate());
		soapModel.setContractEndDate(model.getContractEndDate());
		soapModel.setInboundStatus(model.getInboundStatus());

		return soapModel;
	}

	public static BestPriceMasterSoap[] toSoapModels(BestPriceMaster[] models) {
		BestPriceMasterSoap[] soapModels = new BestPriceMasterSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static BestPriceMasterSoap[][] toSoapModels(
		BestPriceMaster[][] models) {
		BestPriceMasterSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new BestPriceMasterSoap[models.length][models[0].length];
		}
		else {
			soapModels = new BestPriceMasterSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static BestPriceMasterSoap[] toSoapModels(
		List<BestPriceMaster> models) {
		List<BestPriceMasterSoap> soapModels = new ArrayList<BestPriceMasterSoap>(models.size());

		for (BestPriceMaster model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new BestPriceMasterSoap[soapModels.size()]);
	}

	public BestPriceMasterSoap() {
	}

	public int getPrimaryKey() {
		return _bestPriceMasterSid;
	}

	public void setPrimaryKey(int pk) {
		setBestPriceMasterSid(pk);
	}

	public double getInitialBestPrice() {
		return _initialBestPrice;
	}

	public void setInitialBestPrice(double initialBestPrice) {
		_initialBestPrice = initialBestPrice;
	}

	public int getCreatedBy() {
		return _createdBy;
	}

	public void setCreatedBy(int createdBy) {
		_createdBy = createdBy;
	}

	public String getItemNo() {
		return _itemNo;
	}

	public void setItemNo(String itemNo) {
		_itemNo = itemNo;
	}

	public int getModifiedBy() {
		return _modifiedBy;
	}

	public void setModifiedBy(int modifiedBy) {
		_modifiedBy = modifiedBy;
	}

	public String getAccountId() {
		return _accountId;
	}

	public void setAccountId(String accountId) {
		_accountId = accountId;
	}

	public Date getCreatedDate() {
		return _createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		_createdDate = createdDate;
	}

	public String getItemId() {
		return _itemId;
	}

	public void setItemId(String itemId) {
		_itemId = itemId;
	}

	public String getItemDesc() {
		return _itemDesc;
	}

	public void setItemDesc(String itemDesc) {
		_itemDesc = itemDesc;
	}

	public double getSellingPrice() {
		return _sellingPrice;
	}

	public void setSellingPrice(double sellingPrice) {
		_sellingPrice = sellingPrice;
	}

	public String getContractId() {
		return _contractId;
	}

	public void setContractId(String contractId) {
		_contractId = contractId;
	}

	public String getContractNo() {
		return _contractNo;
	}

	public void setContractNo(String contractNo) {
		_contractNo = contractNo;
	}

	public String getBatchId() {
		return _batchId;
	}

	public void setBatchId(String batchId) {
		_batchId = batchId;
	}

	public int getBestPriceMasterSid() {
		return _bestPriceMasterSid;
	}

	public void setBestPriceMasterSid(int bestPriceMasterSid) {
		_bestPriceMasterSid = bestPriceMasterSid;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
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

	public double getBeginningWacPackage() {
		return _beginningWacPackage;
	}

	public void setBeginningWacPackage(double beginningWacPackage) {
		_beginningWacPackage = beginningWacPackage;
	}

	public double getInitialDiscount() {
		return _initialDiscount;
	}

	public void setInitialDiscount(double initialDiscount) {
		_initialDiscount = initialDiscount;
	}

	public String getPeriod() {
		return _period;
	}

	public void setPeriod(String period) {
		_period = period;
	}

	public String getSource() {
		return _source;
	}

	public void setSource(String source) {
		_source = source;
	}

	public Date getContractStartDate() {
		return _contractStartDate;
	}

	public void setContractStartDate(Date contractStartDate) {
		_contractStartDate = contractStartDate;
	}

	public Date getContractEndDate() {
		return _contractEndDate;
	}

	public void setContractEndDate(Date contractEndDate) {
		_contractEndDate = contractEndDate;
	}

	public String getInboundStatus() {
		return _inboundStatus;
	}

	public void setInboundStatus(String inboundStatus) {
		_inboundStatus = inboundStatus;
	}

	private double _initialBestPrice;
	private int _createdBy;
	private String _itemNo;
	private int _modifiedBy;
	private String _accountId;
	private Date _createdDate;
	private String _itemId;
	private String _itemDesc;
	private double _sellingPrice;
	private String _contractId;
	private String _contractNo;
	private String _batchId;
	private int _bestPriceMasterSid;
	private Date _modifiedDate;
	private boolean _recordLockStatus;
	private double _beginningWacPackage;
	private double _initialDiscount;
	private String _period;
	private String _source;
	private Date _contractStartDate;
	private Date _contractEndDate;
	private String _inboundStatus;
}