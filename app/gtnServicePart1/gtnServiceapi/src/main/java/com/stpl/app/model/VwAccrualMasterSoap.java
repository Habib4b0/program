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
public class VwAccrualMasterSoap implements Serializable {
	public static VwAccrualMasterSoap toSoapModel(VwAccrualMaster model) {
		VwAccrualMasterSoap soapModel = new VwAccrualMasterSoap();

		soapModel.setItemQualifier(model.getItemQualifier());
		soapModel.setBusinessUnitQualifier(model.getBusinessUnitQualifier());
		soapModel.setItemNo(model.getItemNo());
		soapModel.setPostingIndicator(model.getPostingIndicator());
		soapModel.setCreatedDate(model.getCreatedDate());
		soapModel.setCostCenter(model.getCostCenter());
		soapModel.setSubLedger(model.getSubLedger());
		soapModel.setAccrualPeriodEd(model.getAccrualPeriodEd());
		soapModel.setAccrualId(model.getAccrualId());
		soapModel.setCompanyQualifier(model.getCompanyQualifier());
		soapModel.setContractNo(model.getContractNo());
		soapModel.setBatchId(model.getBatchId());
		soapModel.setItemName(model.getItemName());
		soapModel.setBrandId(model.getBrandId());
		soapModel.setPostingDate(model.getPostingDate());
		soapModel.setBusinessUnitName(model.getBusinessUnitName());
		soapModel.setSalesId(model.getSalesId());
		soapModel.setAmount(model.getAmount());
		soapModel.setBusinessUnitNo(model.getBusinessUnitNo());
		soapModel.setSubLedgerType(model.getSubLedgerType());
		soapModel.setDocumentType(model.getDocumentType());
		soapModel.setAccuralType(model.getAccuralType());
		soapModel.setCreatedBy(model.getCreatedBy());
		soapModel.setProgramNo(model.getProgramNo());
		soapModel.setCustomerId(model.getCustomerId());
		soapModel.setItemId(model.getItemId());
		soapModel.setAccrualMasterSid(model.getAccrualMasterSid());
		soapModel.setContractId(model.getContractId());
		soapModel.setContractName(model.getContractName());
		soapModel.setGlAccount(model.getGlAccount());
		soapModel.setGlDate(model.getGlDate());
		soapModel.setBusinessUnitId(model.getBusinessUnitId());
		soapModel.setProgramType(model.getProgramType());
		soapModel.setCustomerName(model.getCustomerName());
		soapModel.setCustomerNo(model.getCustomerNo());
		soapModel.setSource(model.getSource());
		soapModel.setAccrualPeriodSd(model.getAccrualPeriodSd());

		return soapModel;
	}

	public static VwAccrualMasterSoap[] toSoapModels(VwAccrualMaster[] models) {
		VwAccrualMasterSoap[] soapModels = new VwAccrualMasterSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static VwAccrualMasterSoap[][] toSoapModels(
		VwAccrualMaster[][] models) {
		VwAccrualMasterSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new VwAccrualMasterSoap[models.length][models[0].length];
		}
		else {
			soapModels = new VwAccrualMasterSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static VwAccrualMasterSoap[] toSoapModels(
		List<VwAccrualMaster> models) {
		List<VwAccrualMasterSoap> soapModels = new ArrayList<VwAccrualMasterSoap>(models.size());

		for (VwAccrualMaster model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new VwAccrualMasterSoap[soapModels.size()]);
	}

	public VwAccrualMasterSoap() {
	}

	public int getPrimaryKey() {
		return _accrualMasterSid;
	}

	public void setPrimaryKey(int pk) {
		setAccrualMasterSid(pk);
	}

	public String getItemQualifier() {
		return _itemQualifier;
	}

	public void setItemQualifier(String itemQualifier) {
		_itemQualifier = itemQualifier;
	}

	public String getBusinessUnitQualifier() {
		return _businessUnitQualifier;
	}

	public void setBusinessUnitQualifier(String businessUnitQualifier) {
		_businessUnitQualifier = businessUnitQualifier;
	}

	public String getItemNo() {
		return _itemNo;
	}

	public void setItemNo(String itemNo) {
		_itemNo = itemNo;
	}

	public String getPostingIndicator() {
		return _postingIndicator;
	}

	public void setPostingIndicator(String postingIndicator) {
		_postingIndicator = postingIndicator;
	}

	public Date getCreatedDate() {
		return _createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		_createdDate = createdDate;
	}

	public String getCostCenter() {
		return _costCenter;
	}

	public void setCostCenter(String costCenter) {
		_costCenter = costCenter;
	}

	public String getSubLedger() {
		return _subLedger;
	}

	public void setSubLedger(String subLedger) {
		_subLedger = subLedger;
	}

	public Date getAccrualPeriodEd() {
		return _accrualPeriodEd;
	}

	public void setAccrualPeriodEd(Date accrualPeriodEd) {
		_accrualPeriodEd = accrualPeriodEd;
	}

	public String getAccrualId() {
		return _accrualId;
	}

	public void setAccrualId(String accrualId) {
		_accrualId = accrualId;
	}

	public String getCompanyQualifier() {
		return _companyQualifier;
	}

	public void setCompanyQualifier(String companyQualifier) {
		_companyQualifier = companyQualifier;
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

	public String getItemName() {
		return _itemName;
	}

	public void setItemName(String itemName) {
		_itemName = itemName;
	}

	public String getBrandId() {
		return _brandId;
	}

	public void setBrandId(String brandId) {
		_brandId = brandId;
	}

	public Date getPostingDate() {
		return _postingDate;
	}

	public void setPostingDate(Date postingDate) {
		_postingDate = postingDate;
	}

	public String getBusinessUnitName() {
		return _businessUnitName;
	}

	public void setBusinessUnitName(String businessUnitName) {
		_businessUnitName = businessUnitName;
	}

	public String getSalesId() {
		return _salesId;
	}

	public void setSalesId(String salesId) {
		_salesId = salesId;
	}

	public double getAmount() {
		return _amount;
	}

	public void setAmount(double amount) {
		_amount = amount;
	}

	public String getBusinessUnitNo() {
		return _businessUnitNo;
	}

	public void setBusinessUnitNo(String businessUnitNo) {
		_businessUnitNo = businessUnitNo;
	}

	public String getSubLedgerType() {
		return _subLedgerType;
	}

	public void setSubLedgerType(String subLedgerType) {
		_subLedgerType = subLedgerType;
	}

	public String getDocumentType() {
		return _documentType;
	}

	public void setDocumentType(String documentType) {
		_documentType = documentType;
	}

	public String getAccuralType() {
		return _accuralType;
	}

	public void setAccuralType(String accuralType) {
		_accuralType = accuralType;
	}

	public int getCreatedBy() {
		return _createdBy;
	}

	public void setCreatedBy(int createdBy) {
		_createdBy = createdBy;
	}

	public String getProgramNo() {
		return _programNo;
	}

	public void setProgramNo(String programNo) {
		_programNo = programNo;
	}

	public String getCustomerId() {
		return _customerId;
	}

	public void setCustomerId(String customerId) {
		_customerId = customerId;
	}

	public String getItemId() {
		return _itemId;
	}

	public void setItemId(String itemId) {
		_itemId = itemId;
	}

	public int getAccrualMasterSid() {
		return _accrualMasterSid;
	}

	public void setAccrualMasterSid(int accrualMasterSid) {
		_accrualMasterSid = accrualMasterSid;
	}

	public String getContractId() {
		return _contractId;
	}

	public void setContractId(String contractId) {
		_contractId = contractId;
	}

	public String getContractName() {
		return _contractName;
	}

	public void setContractName(String contractName) {
		_contractName = contractName;
	}

	public String getGlAccount() {
		return _glAccount;
	}

	public void setGlAccount(String glAccount) {
		_glAccount = glAccount;
	}

	public Date getGlDate() {
		return _glDate;
	}

	public void setGlDate(Date glDate) {
		_glDate = glDate;
	}

	public String getBusinessUnitId() {
		return _businessUnitId;
	}

	public void setBusinessUnitId(String businessUnitId) {
		_businessUnitId = businessUnitId;
	}

	public String getProgramType() {
		return _programType;
	}

	public void setProgramType(String programType) {
		_programType = programType;
	}

	public String getCustomerName() {
		return _customerName;
	}

	public void setCustomerName(String customerName) {
		_customerName = customerName;
	}

	public String getCustomerNo() {
		return _customerNo;
	}

	public void setCustomerNo(String customerNo) {
		_customerNo = customerNo;
	}

	public String getSource() {
		return _source;
	}

	public void setSource(String source) {
		_source = source;
	}

	public Date getAccrualPeriodSd() {
		return _accrualPeriodSd;
	}

	public void setAccrualPeriodSd(Date accrualPeriodSd) {
		_accrualPeriodSd = accrualPeriodSd;
	}

	private String _itemQualifier;
	private String _businessUnitQualifier;
	private String _itemNo;
	private String _postingIndicator;
	private Date _createdDate;
	private String _costCenter;
	private String _subLedger;
	private Date _accrualPeriodEd;
	private String _accrualId;
	private String _companyQualifier;
	private String _contractNo;
	private String _batchId;
	private String _itemName;
	private String _brandId;
	private Date _postingDate;
	private String _businessUnitName;
	private String _salesId;
	private double _amount;
	private String _businessUnitNo;
	private String _subLedgerType;
	private String _documentType;
	private String _accuralType;
	private int _createdBy;
	private String _programNo;
	private String _customerId;
	private String _itemId;
	private int _accrualMasterSid;
	private String _contractId;
	private String _contractName;
	private String _glAccount;
	private Date _glDate;
	private String _businessUnitId;
	private String _programType;
	private String _customerName;
	private String _customerNo;
	private String _source;
	private Date _accrualPeriodSd;
}