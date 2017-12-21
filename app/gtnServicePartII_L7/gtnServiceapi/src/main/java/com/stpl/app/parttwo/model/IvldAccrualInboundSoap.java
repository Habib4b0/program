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
public class IvldAccrualInboundSoap implements Serializable {
	public static IvldAccrualInboundSoap toSoapModel(IvldAccrualInbound model) {
		IvldAccrualInboundSoap soapModel = new IvldAccrualInboundSoap();

		soapModel.setRecordCreatedDate(model.getRecordCreatedDate());
		soapModel.setAccountId(model.getAccountId());
		soapModel.setPostingIndicator(model.getPostingIndicator());
		soapModel.setItemId(model.getItemId());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setAccrualPeriodEndDate(model.getAccrualPeriodEndDate());
		soapModel.setItemIdentCodeQualifier(model.getItemIdentCodeQualifier());
		soapModel.setGlCompanyMasterSid(model.getGlCompanyMasterSid());
		soapModel.setSalesMasterId(model.getSalesMasterId());
		soapModel.setCreatedDate(model.getCreatedDate());
		soapModel.setCreatedBy(model.getCreatedBy());
		soapModel.setSource(model.getSource());
		soapModel.setAccrualPeriodStartDate(model.getAccrualPeriodStartDate());
		soapModel.setAddChgDelIndicator(model.getAddChgDelIndicator());
		soapModel.setSubLedgerType(model.getSubLedgerType());
		soapModel.setProgramNo(model.getProgramNo());
		soapModel.setDocumentType(model.getDocumentType());
		soapModel.setAccrualIntfid(model.getAccrualIntfid());
		soapModel.setGlCompanyName(model.getGlCompanyName());
		soapModel.setErrorCode(model.getErrorCode());
		soapModel.setIntfInsertedDate(model.getIntfInsertedDate());
		soapModel.setModifiedBy(model.getModifiedBy());
		soapModel.setCategoryId(model.getCategoryId());
		soapModel.setItemNo(model.getItemNo());
		soapModel.setReprocessedFlag(model.getReprocessedFlag());
		soapModel.setCompIdentCodeQualifier(model.getCompIdentCodeQualifier());
		soapModel.setAcctIdentCodeQualifier(model.getAcctIdentCodeQualifier());
		soapModel.setContractId(model.getContractId());
		soapModel.setAccountNo(model.getAccountNo());
		soapModel.setAccrualId(model.getAccrualId());
		soapModel.setReasonForFailure(model.getReasonForFailure());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setAccountName(model.getAccountName());
		soapModel.setAccrualType(model.getAccrualType());
		soapModel.setPostingDate(model.getPostingDate());
		soapModel.setBrandId(model.getBrandId());
		soapModel.setProvisionId(model.getProvisionId());
		soapModel.setAmount(model.getAmount());
		soapModel.setGlDate(model.getGlDate());
		soapModel.setSubLedger(model.getSubLedger());
		soapModel.setCompanyCostCenter(model.getCompanyCostCenter());
		soapModel.setGlAccount(model.getGlAccount());
		soapModel.setCompanyNo(model.getCompanyNo());
		soapModel.setBatchId(model.getBatchId());
		soapModel.setProgramType(model.getProgramType());
		soapModel.setItemName(model.getItemName());
		soapModel.setErrorField(model.getErrorField());
		soapModel.setIvldAccrualInboundSid(model.getIvldAccrualInboundSid());
		soapModel.setContractNo(model.getContractNo());
		soapModel.setBrandName(model.getBrandName());
		soapModel.setContractName(model.getContractName());
		soapModel.setCheckRecord(model.getCheckRecord());

		return soapModel;
	}

	public static IvldAccrualInboundSoap[] toSoapModels(
		IvldAccrualInbound[] models) {
		IvldAccrualInboundSoap[] soapModels = new IvldAccrualInboundSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static IvldAccrualInboundSoap[][] toSoapModels(
		IvldAccrualInbound[][] models) {
		IvldAccrualInboundSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new IvldAccrualInboundSoap[models.length][models[0].length];
		}
		else {
			soapModels = new IvldAccrualInboundSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static IvldAccrualInboundSoap[] toSoapModels(
		List<IvldAccrualInbound> models) {
		List<IvldAccrualInboundSoap> soapModels = new ArrayList<IvldAccrualInboundSoap>(models.size());

		for (IvldAccrualInbound model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new IvldAccrualInboundSoap[soapModels.size()]);
	}

	public IvldAccrualInboundSoap() {
	}

	public int getPrimaryKey() {
		return _ivldAccrualInboundSid;
	}

	public void setPrimaryKey(int pk) {
		setIvldAccrualInboundSid(pk);
	}

	public String getRecordCreatedDate() {
		return _recordCreatedDate;
	}

	public void setRecordCreatedDate(String recordCreatedDate) {
		_recordCreatedDate = recordCreatedDate;
	}

	public String getAccountId() {
		return _accountId;
	}

	public void setAccountId(String accountId) {
		_accountId = accountId;
	}

	public String getPostingIndicator() {
		return _postingIndicator;
	}

	public void setPostingIndicator(String postingIndicator) {
		_postingIndicator = postingIndicator;
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

	public String getAccrualPeriodEndDate() {
		return _accrualPeriodEndDate;
	}

	public void setAccrualPeriodEndDate(String accrualPeriodEndDate) {
		_accrualPeriodEndDate = accrualPeriodEndDate;
	}

	public String getItemIdentCodeQualifier() {
		return _itemIdentCodeQualifier;
	}

	public void setItemIdentCodeQualifier(String itemIdentCodeQualifier) {
		_itemIdentCodeQualifier = itemIdentCodeQualifier;
	}

	public String getGlCompanyMasterSid() {
		return _glCompanyMasterSid;
	}

	public void setGlCompanyMasterSid(String glCompanyMasterSid) {
		_glCompanyMasterSid = glCompanyMasterSid;
	}

	public String getSalesMasterId() {
		return _salesMasterId;
	}

	public void setSalesMasterId(String salesMasterId) {
		_salesMasterId = salesMasterId;
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

	public String getAccrualPeriodStartDate() {
		return _accrualPeriodStartDate;
	}

	public void setAccrualPeriodStartDate(String accrualPeriodStartDate) {
		_accrualPeriodStartDate = accrualPeriodStartDate;
	}

	public String getAddChgDelIndicator() {
		return _addChgDelIndicator;
	}

	public void setAddChgDelIndicator(String addChgDelIndicator) {
		_addChgDelIndicator = addChgDelIndicator;
	}

	public String getSubLedgerType() {
		return _subLedgerType;
	}

	public void setSubLedgerType(String subLedgerType) {
		_subLedgerType = subLedgerType;
	}

	public String getProgramNo() {
		return _programNo;
	}

	public void setProgramNo(String programNo) {
		_programNo = programNo;
	}

	public String getDocumentType() {
		return _documentType;
	}

	public void setDocumentType(String documentType) {
		_documentType = documentType;
	}

	public String getAccrualIntfid() {
		return _accrualIntfid;
	}

	public void setAccrualIntfid(String accrualIntfid) {
		_accrualIntfid = accrualIntfid;
	}

	public String getGlCompanyName() {
		return _glCompanyName;
	}

	public void setGlCompanyName(String glCompanyName) {
		_glCompanyName = glCompanyName;
	}

	public String getErrorCode() {
		return _errorCode;
	}

	public void setErrorCode(String errorCode) {
		_errorCode = errorCode;
	}

	public Date getIntfInsertedDate() {
		return _intfInsertedDate;
	}

	public void setIntfInsertedDate(Date intfInsertedDate) {
		_intfInsertedDate = intfInsertedDate;
	}

	public String getModifiedBy() {
		return _modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		_modifiedBy = modifiedBy;
	}

	public String getCategoryId() {
		return _categoryId;
	}

	public void setCategoryId(String categoryId) {
		_categoryId = categoryId;
	}

	public String getItemNo() {
		return _itemNo;
	}

	public void setItemNo(String itemNo) {
		_itemNo = itemNo;
	}

	public String getReprocessedFlag() {
		return _reprocessedFlag;
	}

	public void setReprocessedFlag(String reprocessedFlag) {
		_reprocessedFlag = reprocessedFlag;
	}

	public String getCompIdentCodeQualifier() {
		return _compIdentCodeQualifier;
	}

	public void setCompIdentCodeQualifier(String compIdentCodeQualifier) {
		_compIdentCodeQualifier = compIdentCodeQualifier;
	}

	public String getAcctIdentCodeQualifier() {
		return _acctIdentCodeQualifier;
	}

	public void setAcctIdentCodeQualifier(String acctIdentCodeQualifier) {
		_acctIdentCodeQualifier = acctIdentCodeQualifier;
	}

	public String getContractId() {
		return _contractId;
	}

	public void setContractId(String contractId) {
		_contractId = contractId;
	}

	public String getAccountNo() {
		return _accountNo;
	}

	public void setAccountNo(String accountNo) {
		_accountNo = accountNo;
	}

	public String getAccrualId() {
		return _accrualId;
	}

	public void setAccrualId(String accrualId) {
		_accrualId = accrualId;
	}

	public String getReasonForFailure() {
		return _reasonForFailure;
	}

	public void setReasonForFailure(String reasonForFailure) {
		_reasonForFailure = reasonForFailure;
	}

	public String getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(String companyId) {
		_companyId = companyId;
	}

	public String getAccountName() {
		return _accountName;
	}

	public void setAccountName(String accountName) {
		_accountName = accountName;
	}

	public String getAccrualType() {
		return _accrualType;
	}

	public void setAccrualType(String accrualType) {
		_accrualType = accrualType;
	}

	public String getPostingDate() {
		return _postingDate;
	}

	public void setPostingDate(String postingDate) {
		_postingDate = postingDate;
	}

	public String getBrandId() {
		return _brandId;
	}

	public void setBrandId(String brandId) {
		_brandId = brandId;
	}

	public String getProvisionId() {
		return _provisionId;
	}

	public void setProvisionId(String provisionId) {
		_provisionId = provisionId;
	}

	public String getAmount() {
		return _amount;
	}

	public void setAmount(String amount) {
		_amount = amount;
	}

	public String getGlDate() {
		return _glDate;
	}

	public void setGlDate(String glDate) {
		_glDate = glDate;
	}

	public String getSubLedger() {
		return _subLedger;
	}

	public void setSubLedger(String subLedger) {
		_subLedger = subLedger;
	}

	public String getCompanyCostCenter() {
		return _companyCostCenter;
	}

	public void setCompanyCostCenter(String companyCostCenter) {
		_companyCostCenter = companyCostCenter;
	}

	public String getGlAccount() {
		return _glAccount;
	}

	public void setGlAccount(String glAccount) {
		_glAccount = glAccount;
	}

	public String getCompanyNo() {
		return _companyNo;
	}

	public void setCompanyNo(String companyNo) {
		_companyNo = companyNo;
	}

	public String getBatchId() {
		return _batchId;
	}

	public void setBatchId(String batchId) {
		_batchId = batchId;
	}

	public String getProgramType() {
		return _programType;
	}

	public void setProgramType(String programType) {
		_programType = programType;
	}

	public String getItemName() {
		return _itemName;
	}

	public void setItemName(String itemName) {
		_itemName = itemName;
	}

	public String getErrorField() {
		return _errorField;
	}

	public void setErrorField(String errorField) {
		_errorField = errorField;
	}

	public int getIvldAccrualInboundSid() {
		return _ivldAccrualInboundSid;
	}

	public void setIvldAccrualInboundSid(int ivldAccrualInboundSid) {
		_ivldAccrualInboundSid = ivldAccrualInboundSid;
	}

	public String getContractNo() {
		return _contractNo;
	}

	public void setContractNo(String contractNo) {
		_contractNo = contractNo;
	}

	public String getBrandName() {
		return _brandName;
	}

	public void setBrandName(String brandName) {
		_brandName = brandName;
	}

	public String getContractName() {
		return _contractName;
	}

	public void setContractName(String contractName) {
		_contractName = contractName;
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

	private String _recordCreatedDate;
	private String _accountId;
	private String _postingIndicator;
	private String _itemId;
	private Date _modifiedDate;
	private String _accrualPeriodEndDate;
	private String _itemIdentCodeQualifier;
	private String _glCompanyMasterSid;
	private String _salesMasterId;
	private Date _createdDate;
	private String _createdBy;
	private String _source;
	private String _accrualPeriodStartDate;
	private String _addChgDelIndicator;
	private String _subLedgerType;
	private String _programNo;
	private String _documentType;
	private String _accrualIntfid;
	private String _glCompanyName;
	private String _errorCode;
	private Date _intfInsertedDate;
	private String _modifiedBy;
	private String _categoryId;
	private String _itemNo;
	private String _reprocessedFlag;
	private String _compIdentCodeQualifier;
	private String _acctIdentCodeQualifier;
	private String _contractId;
	private String _accountNo;
	private String _accrualId;
	private String _reasonForFailure;
	private String _companyId;
	private String _accountName;
	private String _accrualType;
	private String _postingDate;
	private String _brandId;
	private String _provisionId;
	private String _amount;
	private String _glDate;
	private String _subLedger;
	private String _companyCostCenter;
	private String _glAccount;
	private String _companyNo;
	private String _batchId;
	private String _programType;
	private String _itemName;
	private String _errorField;
	private int _ivldAccrualInboundSid;
	private String _contractNo;
	private String _brandName;
	private String _contractName;
	private boolean _checkRecord;
}