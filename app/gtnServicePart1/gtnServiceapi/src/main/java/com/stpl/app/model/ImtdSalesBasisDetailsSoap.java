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
public class ImtdSalesBasisDetailsSoap implements Serializable {
	public static ImtdSalesBasisDetailsSoap toSoapModel(
		ImtdSalesBasisDetails model) {
		ImtdSalesBasisDetailsSoap soapModel = new ImtdSalesBasisDetailsSoap();

		soapModel.setCreatedBy(model.getCreatedBy());
		soapModel.setNetSalesFormulaMasterSid(model.getNetSalesFormulaMasterSid());
		soapModel.setUsersSid(model.getUsersSid());
		soapModel.setModifiedBy(model.getModifiedBy());
		soapModel.setCreatedDate(model.getCreatedDate());
		soapModel.setContractMasterSid(model.getContractMasterSid());
		soapModel.setCfpNo(model.getCfpNo());
		soapModel.setImtdCreatedDate(model.getImtdCreatedDate());
		soapModel.setContractNo(model.getContractNo());
		soapModel.setContractName(model.getContractName());
		soapModel.setSalesBasisDetailsSid(model.getSalesBasisDetailsSid());
		soapModel.setCheckRecord(model.getCheckRecord());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setCustomerName(model.getCustomerName());
		soapModel.setOperation(model.getOperation());
		soapModel.setCustomerNo(model.getCustomerNo());
		soapModel.setImtdSalesBasisDetailsSid(model.getImtdSalesBasisDetailsSid());
		soapModel.setCfpName(model.getCfpName());
		soapModel.setCdrModelSid(model.getCdrModelSid());
		soapModel.setSessionId(model.getSessionId());
		soapModel.setCfpContractDetailsSid(model.getCfpContractDetailsSid());
		soapModel.setInboundStatus(model.getInboundStatus());

		return soapModel;
	}

	public static ImtdSalesBasisDetailsSoap[] toSoapModels(
		ImtdSalesBasisDetails[] models) {
		ImtdSalesBasisDetailsSoap[] soapModels = new ImtdSalesBasisDetailsSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ImtdSalesBasisDetailsSoap[][] toSoapModels(
		ImtdSalesBasisDetails[][] models) {
		ImtdSalesBasisDetailsSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ImtdSalesBasisDetailsSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ImtdSalesBasisDetailsSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ImtdSalesBasisDetailsSoap[] toSoapModels(
		List<ImtdSalesBasisDetails> models) {
		List<ImtdSalesBasisDetailsSoap> soapModels = new ArrayList<ImtdSalesBasisDetailsSoap>(models.size());

		for (ImtdSalesBasisDetails model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ImtdSalesBasisDetailsSoap[soapModels.size()]);
	}

	public ImtdSalesBasisDetailsSoap() {
	}

	public int getPrimaryKey() {
		return _imtdSalesBasisDetailsSid;
	}

	public void setPrimaryKey(int pk) {
		setImtdSalesBasisDetailsSid(pk);
	}

	public int getCreatedBy() {
		return _createdBy;
	}

	public void setCreatedBy(int createdBy) {
		_createdBy = createdBy;
	}

	public int getNetSalesFormulaMasterSid() {
		return _netSalesFormulaMasterSid;
	}

	public void setNetSalesFormulaMasterSid(int netSalesFormulaMasterSid) {
		_netSalesFormulaMasterSid = netSalesFormulaMasterSid;
	}

	public int getUsersSid() {
		return _usersSid;
	}

	public void setUsersSid(int usersSid) {
		_usersSid = usersSid;
	}

	public int getModifiedBy() {
		return _modifiedBy;
	}

	public void setModifiedBy(int modifiedBy) {
		_modifiedBy = modifiedBy;
	}

	public Date getCreatedDate() {
		return _createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		_createdDate = createdDate;
	}

	public int getContractMasterSid() {
		return _contractMasterSid;
	}

	public void setContractMasterSid(int contractMasterSid) {
		_contractMasterSid = contractMasterSid;
	}

	public String getCfpNo() {
		return _cfpNo;
	}

	public void setCfpNo(String cfpNo) {
		_cfpNo = cfpNo;
	}

	public String getImtdCreatedDate() {
		return _imtdCreatedDate;
	}

	public void setImtdCreatedDate(String imtdCreatedDate) {
		_imtdCreatedDate = imtdCreatedDate;
	}

	public String getContractNo() {
		return _contractNo;
	}

	public void setContractNo(String contractNo) {
		_contractNo = contractNo;
	}

	public String getContractName() {
		return _contractName;
	}

	public void setContractName(String contractName) {
		_contractName = contractName;
	}

	public int getSalesBasisDetailsSid() {
		return _salesBasisDetailsSid;
	}

	public void setSalesBasisDetailsSid(int salesBasisDetailsSid) {
		_salesBasisDetailsSid = salesBasisDetailsSid;
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

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public String getCustomerName() {
		return _customerName;
	}

	public void setCustomerName(String customerName) {
		_customerName = customerName;
	}

	public String getOperation() {
		return _operation;
	}

	public void setOperation(String operation) {
		_operation = operation;
	}

	public String getCustomerNo() {
		return _customerNo;
	}

	public void setCustomerNo(String customerNo) {
		_customerNo = customerNo;
	}

	public int getImtdSalesBasisDetailsSid() {
		return _imtdSalesBasisDetailsSid;
	}

	public void setImtdSalesBasisDetailsSid(int imtdSalesBasisDetailsSid) {
		_imtdSalesBasisDetailsSid = imtdSalesBasisDetailsSid;
	}

	public String getCfpName() {
		return _cfpName;
	}

	public void setCfpName(String cfpName) {
		_cfpName = cfpName;
	}

	public int getCdrModelSid() {
		return _cdrModelSid;
	}

	public void setCdrModelSid(int cdrModelSid) {
		_cdrModelSid = cdrModelSid;
	}

	public String getSessionId() {
		return _sessionId;
	}

	public void setSessionId(String sessionId) {
		_sessionId = sessionId;
	}

	public int getCfpContractDetailsSid() {
		return _cfpContractDetailsSid;
	}

	public void setCfpContractDetailsSid(int cfpContractDetailsSid) {
		_cfpContractDetailsSid = cfpContractDetailsSid;
	}

	public String getInboundStatus() {
		return _inboundStatus;
	}

	public void setInboundStatus(String inboundStatus) {
		_inboundStatus = inboundStatus;
	}

	private int _createdBy;
	private int _netSalesFormulaMasterSid;
	private int _usersSid;
	private int _modifiedBy;
	private Date _createdDate;
	private int _contractMasterSid;
	private String _cfpNo;
	private String _imtdCreatedDate;
	private String _contractNo;
	private String _contractName;
	private int _salesBasisDetailsSid;
	private boolean _checkRecord;
	private Date _modifiedDate;
	private String _customerName;
	private String _operation;
	private String _customerNo;
	private int _imtdSalesBasisDetailsSid;
	private String _cfpName;
	private int _cdrModelSid;
	private String _sessionId;
	private int _cfpContractDetailsSid;
	private String _inboundStatus;
}