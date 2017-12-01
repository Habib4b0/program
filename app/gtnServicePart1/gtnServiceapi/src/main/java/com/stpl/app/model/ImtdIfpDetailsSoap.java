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
public class ImtdIfpDetailsSoap implements Serializable {
	public static ImtdIfpDetailsSoap toSoapModel(ImtdIfpDetails model) {
		ImtdIfpDetailsSoap soapModel = new ImtdIfpDetailsSoap();

		soapModel.setItemStatus(model.getItemStatus());
		soapModel.setIfpDetailsEndDate(model.getIfpDetailsEndDate());
		soapModel.setItemMasterSid(model.getItemMasterSid());
		soapModel.setImtdCreateddate(model.getImtdCreateddate());
		soapModel.setItemPackageSize(model.getItemPackageSize());
		soapModel.setIfpDetailsCreatedDate(model.getIfpDetailsCreatedDate());
		soapModel.setTotalDollarCommitment(model.getTotalDollarCommitment());
		soapModel.setIfpDetailsCreatedBy(model.getIfpDetailsCreatedBy());
		soapModel.setItemId(model.getItemId());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setIfpDetailsModifiedBy(model.getIfpDetailsModifiedBy());
		soapModel.setIfpDetailsModifiedDate(model.getIfpDetailsModifiedDate());
		soapModel.setCreatedDate(model.getCreatedDate());
		soapModel.setCreatedBy(model.getCreatedBy());
		soapModel.setUsersSid(model.getUsersSid());
		soapModel.setItemDesc(model.getItemDesc());
		soapModel.setItemStartDate(model.getItemStartDate());
		soapModel.setItemStrength(model.getItemStrength());
		soapModel.setContractMasterSid(model.getContractMasterSid());
		soapModel.setModifiedBy(model.getModifiedBy());
		soapModel.setCommitmentPeriod(model.getCommitmentPeriod());
		soapModel.setItemNo(model.getItemNo());
		soapModel.setIfpDetailsSid(model.getIfpDetailsSid());
		soapModel.setIfpModelSid(model.getIfpModelSid());
		soapModel.setItemTherapeuticClass(model.getItemTherapeuticClass());
		soapModel.setIfpDetailsStartDate(model.getIfpDetailsStartDate());
		soapModel.setItemForm(model.getItemForm());
		soapModel.setTotalVolumeCommitment(model.getTotalVolumeCommitment());
		soapModel.setItemEndDate(model.getItemEndDate());
		soapModel.setCheckBox(model.getCheckBox());
		soapModel.setIfpDetailsAttachedStatus(model.getIfpDetailsAttachedStatus());
		soapModel.setTotalMarketshareCommitment(model.getTotalMarketshareCommitment());
		soapModel.setIfpDetailsAttachedDate(model.getIfpDetailsAttachedDate());
		soapModel.setImtdIfpDetailsSid(model.getImtdIfpDetailsSid());
		soapModel.setSessionId(model.getSessionId());
		soapModel.setItemName(model.getItemName());
		soapModel.setItemPrimaryUom(model.getItemPrimaryUom());
		soapModel.setOperation(model.getOperation());
		soapModel.setItemBrand(model.getItemBrand());
		soapModel.setCfpModelSid(model.getCfpModelSid());

		return soapModel;
	}

	public static ImtdIfpDetailsSoap[] toSoapModels(ImtdIfpDetails[] models) {
		ImtdIfpDetailsSoap[] soapModels = new ImtdIfpDetailsSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ImtdIfpDetailsSoap[][] toSoapModels(ImtdIfpDetails[][] models) {
		ImtdIfpDetailsSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ImtdIfpDetailsSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ImtdIfpDetailsSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ImtdIfpDetailsSoap[] toSoapModels(List<ImtdIfpDetails> models) {
		List<ImtdIfpDetailsSoap> soapModels = new ArrayList<ImtdIfpDetailsSoap>(models.size());

		for (ImtdIfpDetails model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ImtdIfpDetailsSoap[soapModels.size()]);
	}

	public ImtdIfpDetailsSoap() {
	}

	public int getPrimaryKey() {
		return _imtdIfpDetailsSid;
	}

	public void setPrimaryKey(int pk) {
		setImtdIfpDetailsSid(pk);
	}

	public int getItemStatus() {
		return _itemStatus;
	}

	public void setItemStatus(int itemStatus) {
		_itemStatus = itemStatus;
	}

	public Date getIfpDetailsEndDate() {
		return _ifpDetailsEndDate;
	}

	public void setIfpDetailsEndDate(Date ifpDetailsEndDate) {
		_ifpDetailsEndDate = ifpDetailsEndDate;
	}

	public int getItemMasterSid() {
		return _itemMasterSid;
	}

	public void setItemMasterSid(int itemMasterSid) {
		_itemMasterSid = itemMasterSid;
	}

	public Date getImtdCreateddate() {
		return _imtdCreateddate;
	}

	public void setImtdCreateddate(Date imtdCreateddate) {
		_imtdCreateddate = imtdCreateddate;
	}

	public String getItemPackageSize() {
		return _itemPackageSize;
	}

	public void setItemPackageSize(String itemPackageSize) {
		_itemPackageSize = itemPackageSize;
	}

	public Date getIfpDetailsCreatedDate() {
		return _ifpDetailsCreatedDate;
	}

	public void setIfpDetailsCreatedDate(Date ifpDetailsCreatedDate) {
		_ifpDetailsCreatedDate = ifpDetailsCreatedDate;
	}

	public String getTotalDollarCommitment() {
		return _totalDollarCommitment;
	}

	public void setTotalDollarCommitment(String totalDollarCommitment) {
		_totalDollarCommitment = totalDollarCommitment;
	}

	public String getIfpDetailsCreatedBy() {
		return _ifpDetailsCreatedBy;
	}

	public void setIfpDetailsCreatedBy(String ifpDetailsCreatedBy) {
		_ifpDetailsCreatedBy = ifpDetailsCreatedBy;
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

	public String getIfpDetailsModifiedBy() {
		return _ifpDetailsModifiedBy;
	}

	public void setIfpDetailsModifiedBy(String ifpDetailsModifiedBy) {
		_ifpDetailsModifiedBy = ifpDetailsModifiedBy;
	}

	public Date getIfpDetailsModifiedDate() {
		return _ifpDetailsModifiedDate;
	}

	public void setIfpDetailsModifiedDate(Date ifpDetailsModifiedDate) {
		_ifpDetailsModifiedDate = ifpDetailsModifiedDate;
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

	public int getUsersSid() {
		return _usersSid;
	}

	public void setUsersSid(int usersSid) {
		_usersSid = usersSid;
	}

	public String getItemDesc() {
		return _itemDesc;
	}

	public void setItemDesc(String itemDesc) {
		_itemDesc = itemDesc;
	}

	public Date getItemStartDate() {
		return _itemStartDate;
	}

	public void setItemStartDate(Date itemStartDate) {
		_itemStartDate = itemStartDate;
	}

	public String getItemStrength() {
		return _itemStrength;
	}

	public void setItemStrength(String itemStrength) {
		_itemStrength = itemStrength;
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

	public String getCommitmentPeriod() {
		return _commitmentPeriod;
	}

	public void setCommitmentPeriod(String commitmentPeriod) {
		_commitmentPeriod = commitmentPeriod;
	}

	public String getItemNo() {
		return _itemNo;
	}

	public void setItemNo(String itemNo) {
		_itemNo = itemNo;
	}

	public int getIfpDetailsSid() {
		return _ifpDetailsSid;
	}

	public void setIfpDetailsSid(int ifpDetailsSid) {
		_ifpDetailsSid = ifpDetailsSid;
	}

	public int getIfpModelSid() {
		return _ifpModelSid;
	}

	public void setIfpModelSid(int ifpModelSid) {
		_ifpModelSid = ifpModelSid;
	}

	public String getItemTherapeuticClass() {
		return _itemTherapeuticClass;
	}

	public void setItemTherapeuticClass(String itemTherapeuticClass) {
		_itemTherapeuticClass = itemTherapeuticClass;
	}

	public Date getIfpDetailsStartDate() {
		return _ifpDetailsStartDate;
	}

	public void setIfpDetailsStartDate(Date ifpDetailsStartDate) {
		_ifpDetailsStartDate = ifpDetailsStartDate;
	}

	public String getItemForm() {
		return _itemForm;
	}

	public void setItemForm(String itemForm) {
		_itemForm = itemForm;
	}

	public String getTotalVolumeCommitment() {
		return _totalVolumeCommitment;
	}

	public void setTotalVolumeCommitment(String totalVolumeCommitment) {
		_totalVolumeCommitment = totalVolumeCommitment;
	}

	public Date getItemEndDate() {
		return _itemEndDate;
	}

	public void setItemEndDate(Date itemEndDate) {
		_itemEndDate = itemEndDate;
	}

	public boolean getCheckBox() {
		return _checkBox;
	}

	public boolean isCheckBox() {
		return _checkBox;
	}

	public void setCheckBox(boolean checkBox) {
		_checkBox = checkBox;
	}

	public int getIfpDetailsAttachedStatus() {
		return _ifpDetailsAttachedStatus;
	}

	public void setIfpDetailsAttachedStatus(int ifpDetailsAttachedStatus) {
		_ifpDetailsAttachedStatus = ifpDetailsAttachedStatus;
	}

	public String getTotalMarketshareCommitment() {
		return _totalMarketshareCommitment;
	}

	public void setTotalMarketshareCommitment(String totalMarketshareCommitment) {
		_totalMarketshareCommitment = totalMarketshareCommitment;
	}

	public Date getIfpDetailsAttachedDate() {
		return _ifpDetailsAttachedDate;
	}

	public void setIfpDetailsAttachedDate(Date ifpDetailsAttachedDate) {
		_ifpDetailsAttachedDate = ifpDetailsAttachedDate;
	}

	public int getImtdIfpDetailsSid() {
		return _imtdIfpDetailsSid;
	}

	public void setImtdIfpDetailsSid(int imtdIfpDetailsSid) {
		_imtdIfpDetailsSid = imtdIfpDetailsSid;
	}

	public String getSessionId() {
		return _sessionId;
	}

	public void setSessionId(String sessionId) {
		_sessionId = sessionId;
	}

	public String getItemName() {
		return _itemName;
	}

	public void setItemName(String itemName) {
		_itemName = itemName;
	}

	public String getItemPrimaryUom() {
		return _itemPrimaryUom;
	}

	public void setItemPrimaryUom(String itemPrimaryUom) {
		_itemPrimaryUom = itemPrimaryUom;
	}

	public String getOperation() {
		return _operation;
	}

	public void setOperation(String operation) {
		_operation = operation;
	}

	public String getItemBrand() {
		return _itemBrand;
	}

	public void setItemBrand(String itemBrand) {
		_itemBrand = itemBrand;
	}

	public int getCfpModelSid() {
		return _cfpModelSid;
	}

	public void setCfpModelSid(int cfpModelSid) {
		_cfpModelSid = cfpModelSid;
	}

	private int _itemStatus;
	private Date _ifpDetailsEndDate;
	private int _itemMasterSid;
	private Date _imtdCreateddate;
	private String _itemPackageSize;
	private Date _ifpDetailsCreatedDate;
	private String _totalDollarCommitment;
	private String _ifpDetailsCreatedBy;
	private String _itemId;
	private Date _modifiedDate;
	private String _ifpDetailsModifiedBy;
	private Date _ifpDetailsModifiedDate;
	private Date _createdDate;
	private int _createdBy;
	private int _usersSid;
	private String _itemDesc;
	private Date _itemStartDate;
	private String _itemStrength;
	private int _contractMasterSid;
	private int _modifiedBy;
	private String _commitmentPeriod;
	private String _itemNo;
	private int _ifpDetailsSid;
	private int _ifpModelSid;
	private String _itemTherapeuticClass;
	private Date _ifpDetailsStartDate;
	private String _itemForm;
	private String _totalVolumeCommitment;
	private Date _itemEndDate;
	private boolean _checkBox;
	private int _ifpDetailsAttachedStatus;
	private String _totalMarketshareCommitment;
	private Date _ifpDetailsAttachedDate;
	private int _imtdIfpDetailsSid;
	private String _sessionId;
	private String _itemName;
	private String _itemPrimaryUom;
	private String _operation;
	private String _itemBrand;
	private int _cfpModelSid;
}