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
public class AhTempDetailsSoap implements Serializable {
	public static AhTempDetailsSoap toSoapModel(AhTempDetails model) {
		AhTempDetailsSoap soapModel = new AhTempDetailsSoap();

		soapModel.setCheckRecord(model.getCheckRecord());
		soapModel.setContractHolder(model.getContractHolder());
		soapModel.setUserId(model.getUserId());
		soapModel.setItemMasterSid(model.getItemMasterSid());
		soapModel.setBusinessUnitNo(model.getBusinessUnitNo());
		soapModel.setCompanyName(model.getCompanyName());
		soapModel.setItemId(model.getItemId());
		soapModel.setBrandName(model.getBrandName());
		soapModel.setComponentName(model.getComponentName());
		soapModel.setCreatedDate(model.getCreatedDate());
		soapModel.setCreatedBy(model.getCreatedBy());
		soapModel.setScreenName(model.getScreenName());
		soapModel.setBusinessUnitName(model.getBusinessUnitName());
		soapModel.setCompanyNo(model.getCompanyNo());
		soapModel.setItemIdentifierType(model.getItemIdentifierType());
		soapModel.setComponentNo(model.getComponentNo());
		soapModel.setSessionId(model.getSessionId());
		soapModel.setItemName(model.getItemName());
		soapModel.setItemIdentifier(model.getItemIdentifier());
		soapModel.setCompanySid(model.getCompanySid());
		soapModel.setItemNo(model.getItemNo());
		soapModel.setComponentType(model.getComponentType());
		soapModel.setTheraputicClass(model.getTheraputicClass());
		soapModel.setComponentMasterSid(model.getComponentMasterSid());

		return soapModel;
	}

	public static AhTempDetailsSoap[] toSoapModels(AhTempDetails[] models) {
		AhTempDetailsSoap[] soapModels = new AhTempDetailsSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static AhTempDetailsSoap[][] toSoapModels(AhTempDetails[][] models) {
		AhTempDetailsSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new AhTempDetailsSoap[models.length][models[0].length];
		}
		else {
			soapModels = new AhTempDetailsSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static AhTempDetailsSoap[] toSoapModels(List<AhTempDetails> models) {
		List<AhTempDetailsSoap> soapModels = new ArrayList<AhTempDetailsSoap>(models.size());

		for (AhTempDetails model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new AhTempDetailsSoap[soapModels.size()]);
	}

	public AhTempDetailsSoap() {
	}

	public int getPrimaryKey() {
		return _componentMasterSid;
	}

	public void setPrimaryKey(int pk) {
		setComponentMasterSid(pk);
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

	public String getContractHolder() {
		return _contractHolder;
	}

	public void setContractHolder(String contractHolder) {
		_contractHolder = contractHolder;
	}

	public String getUserId() {
		return _userId;
	}

	public void setUserId(String userId) {
		_userId = userId;
	}

	public int getItemMasterSid() {
		return _itemMasterSid;
	}

	public void setItemMasterSid(int itemMasterSid) {
		_itemMasterSid = itemMasterSid;
	}

	public String getBusinessUnitNo() {
		return _businessUnitNo;
	}

	public void setBusinessUnitNo(String businessUnitNo) {
		_businessUnitNo = businessUnitNo;
	}

	public String getCompanyName() {
		return _companyName;
	}

	public void setCompanyName(String companyName) {
		_companyName = companyName;
	}

	public String getItemId() {
		return _itemId;
	}

	public void setItemId(String itemId) {
		_itemId = itemId;
	}

	public String getBrandName() {
		return _brandName;
	}

	public void setBrandName(String brandName) {
		_brandName = brandName;
	}

	public String getComponentName() {
		return _componentName;
	}

	public void setComponentName(String componentName) {
		_componentName = componentName;
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

	public String getScreenName() {
		return _screenName;
	}

	public void setScreenName(String screenName) {
		_screenName = screenName;
	}

	public String getBusinessUnitName() {
		return _businessUnitName;
	}

	public void setBusinessUnitName(String businessUnitName) {
		_businessUnitName = businessUnitName;
	}

	public String getCompanyNo() {
		return _companyNo;
	}

	public void setCompanyNo(String companyNo) {
		_companyNo = companyNo;
	}

	public String getItemIdentifierType() {
		return _itemIdentifierType;
	}

	public void setItemIdentifierType(String itemIdentifierType) {
		_itemIdentifierType = itemIdentifierType;
	}

	public String getComponentNo() {
		return _componentNo;
	}

	public void setComponentNo(String componentNo) {
		_componentNo = componentNo;
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

	public String getItemIdentifier() {
		return _itemIdentifier;
	}

	public void setItemIdentifier(String itemIdentifier) {
		_itemIdentifier = itemIdentifier;
	}

	public int getCompanySid() {
		return _companySid;
	}

	public void setCompanySid(int companySid) {
		_companySid = companySid;
	}

	public String getItemNo() {
		return _itemNo;
	}

	public void setItemNo(String itemNo) {
		_itemNo = itemNo;
	}

	public String getComponentType() {
		return _componentType;
	}

	public void setComponentType(String componentType) {
		_componentType = componentType;
	}

	public String getTheraputicClass() {
		return _theraputicClass;
	}

	public void setTheraputicClass(String theraputicClass) {
		_theraputicClass = theraputicClass;
	}

	public int getComponentMasterSid() {
		return _componentMasterSid;
	}

	public void setComponentMasterSid(int componentMasterSid) {
		_componentMasterSid = componentMasterSid;
	}

	private boolean _checkRecord;
	private String _contractHolder;
	private String _userId;
	private int _itemMasterSid;
	private String _businessUnitNo;
	private String _companyName;
	private String _itemId;
	private String _brandName;
	private String _componentName;
	private Date _createdDate;
	private String _createdBy;
	private String _screenName;
	private String _businessUnitName;
	private String _companyNo;
	private String _itemIdentifierType;
	private String _componentNo;
	private String _sessionId;
	private String _itemName;
	private String _itemIdentifier;
	private int _companySid;
	private String _itemNo;
	private String _componentType;
	private String _theraputicClass;
	private int _componentMasterSid;
}