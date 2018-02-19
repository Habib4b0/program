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
public class VwItemPricingSoap implements Serializable {
	public static VwItemPricingSoap toSoapModel(VwItemPricing model) {
		VwItemPricingSoap soapModel = new VwItemPricingSoap();

		soapModel.setPricingCodeQualifier(model.getPricingCodeQualifier());
		soapModel.setItemPrice(model.getItemPrice());
		soapModel.setEndDate(model.getEndDate());
		soapModel.setItemId(model.getItemId());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setEntityCode(model.getEntityCode());
		soapModel.setStartDate(model.getStartDate());
		soapModel.setCreatedDate(model.getCreatedDate());
		soapModel.setCreatedBy(model.getCreatedBy());
		soapModel.setSource(model.getSource());
		soapModel.setBatchId(model.getBatchId());
		soapModel.setAddChgDelIndicator(model.getAddChgDelIndicator());
		soapModel.setItemName(model.getItemName());
		soapModel.setItemUom(model.getItemUom());
		soapModel.setModifiedBy(model.getModifiedBy());
		soapModel.setItemNo(model.getItemNo());
		soapModel.setItemPricingSid(model.getItemPricingSid());
		soapModel.setPricingCodeStatus(model.getPricingCodeStatus());
		soapModel.setPricingCodeQualifierName(model.getPricingCodeQualifierName());
		soapModel.setItemPriceprecision(model.getItemPriceprecision());

		return soapModel;
	}

	public static VwItemPricingSoap[] toSoapModels(VwItemPricing[] models) {
		VwItemPricingSoap[] soapModels = new VwItemPricingSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static VwItemPricingSoap[][] toSoapModels(VwItemPricing[][] models) {
		VwItemPricingSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new VwItemPricingSoap[models.length][models[0].length];
		}
		else {
			soapModels = new VwItemPricingSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static VwItemPricingSoap[] toSoapModels(List<VwItemPricing> models) {
		List<VwItemPricingSoap> soapModels = new ArrayList<VwItemPricingSoap>(models.size());

		for (VwItemPricing model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new VwItemPricingSoap[soapModels.size()]);
	}

	public VwItemPricingSoap() {
	}

	public int getPrimaryKey() {
		return _itemPricingSid;
	}

	public void setPrimaryKey(int pk) {
		setItemPricingSid(pk);
	}

	public String getPricingCodeQualifier() {
		return _pricingCodeQualifier;
	}

	public void setPricingCodeQualifier(String pricingCodeQualifier) {
		_pricingCodeQualifier = pricingCodeQualifier;
	}

	public String getItemPrice() {
		return _itemPrice;
	}

	public void setItemPrice(String itemPrice) {
		_itemPrice = itemPrice;
	}

	public Date getEndDate() {
		return _endDate;
	}

	public void setEndDate(Date endDate) {
		_endDate = endDate;
	}

	public String getItemId() {
		return _itemId;
	}

	public void setItemId(String itemId) {
		_itemId = itemId;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public String getEntityCode() {
		return _entityCode;
	}

	public void setEntityCode(String entityCode) {
		_entityCode = entityCode;
	}

	public Date getStartDate() {
		return _startDate;
	}

	public void setStartDate(Date startDate) {
		_startDate = startDate;
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

	public String getBatchId() {
		return _batchId;
	}

	public void setBatchId(String batchId) {
		_batchId = batchId;
	}

	public String getAddChgDelIndicator() {
		return _addChgDelIndicator;
	}

	public void setAddChgDelIndicator(String addChgDelIndicator) {
		_addChgDelIndicator = addChgDelIndicator;
	}

	public String getItemName() {
		return _itemName;
	}

	public void setItemName(String itemName) {
		_itemName = itemName;
	}

	public String getItemUom() {
		return _itemUom;
	}

	public void setItemUom(String itemUom) {
		_itemUom = itemUom;
	}

	public String getModifiedBy() {
		return _modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		_modifiedBy = modifiedBy;
	}

	public String getItemNo() {
		return _itemNo;
	}

	public void setItemNo(String itemNo) {
		_itemNo = itemNo;
	}

	public int getItemPricingSid() {
		return _itemPricingSid;
	}

	public void setItemPricingSid(int itemPricingSid) {
		_itemPricingSid = itemPricingSid;
	}

	public String getPricingCodeStatus() {
		return _pricingCodeStatus;
	}

	public void setPricingCodeStatus(String pricingCodeStatus) {
		_pricingCodeStatus = pricingCodeStatus;
	}

	public String getPricingCodeQualifierName() {
		return _pricingCodeQualifierName;
	}

	public void setPricingCodeQualifierName(String pricingCodeQualifierName) {
		_pricingCodeQualifierName = pricingCodeQualifierName;
	}

	public int getItemPriceprecision() {
		return _itemPriceprecision;
	}

	public void setItemPriceprecision(int itemPriceprecision) {
		_itemPriceprecision = itemPriceprecision;
	}

	private String _pricingCodeQualifier;
	private String _itemPrice;
	private Date _endDate;
	private String _itemId;
	private Date _modifiedDate;
	private String _entityCode;
	private Date _startDate;
	private Date _createdDate;
	private String _createdBy;
	private String _source;
	private String _batchId;
	private String _addChgDelIndicator;
	private String _itemName;
	private String _itemUom;
	private String _modifiedBy;
	private String _itemNo;
	private int _itemPricingSid;
	private String _pricingCodeStatus;
	private String _pricingCodeQualifierName;
	private int _itemPriceprecision;
}