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
public class StAdjustmentReserveDetailSoap implements Serializable {
	public static StAdjustmentReserveDetailSoap toSoapModel(
		StAdjustmentReserveDetail model) {
		StAdjustmentReserveDetailSoap soapModel = new StAdjustmentReserveDetailSoap();

		soapModel.setAdjustmentCreatedDate(model.getAdjustmentCreatedDate());
		soapModel.setEtlCheckRecord(model.getEtlCheckRecord());
		soapModel.setPostingIndicator(model.getPostingIndicator());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setAccount(model.getAccount());
		soapModel.setCredit(model.getCredit());
		soapModel.setWorkflowApprovedDate(model.getWorkflowApprovedDate());
		soapModel.setSource(model.getSource());
		soapModel.setLineDescription(model.getLineDescription());
		soapModel.setLedger(model.getLedger());
		soapModel.setUdc6(model.getUdc6());
		soapModel.setUdc5(model.getUdc5());
		soapModel.setUdc4(model.getUdc4());
		soapModel.setWorkflowCreatedDate(model.getWorkflowCreatedDate());
		soapModel.setUdc3(model.getUdc3());
		soapModel.setUdc2(model.getUdc2());
		soapModel.setUdc1(model.getUdc1());
		soapModel.setAdjustmentType(model.getAdjustmentType());
		soapModel.setModifiedBy(model.getModifiedBy());
		soapModel.setCheckRecord(model.getCheckRecord());
		soapModel.setGlCompanyName(model.getGlCompanyName());
		soapModel.setDivision(model.getDivision());
		soapModel.setBalanceType(model.getBalanceType());
		soapModel.setSessionId(model.getSessionId());
		soapModel.setJournalName(model.getJournalName());
		soapModel.setProject(model.getProject());
		soapModel.setDebit(model.getDebit());
		soapModel.setAccountType(model.getAccountType());
		soapModel.setJournalDescription(model.getJournalDescription());
		soapModel.setCategory(model.getCategory());
		soapModel.setCreatedBy(model.getCreatedBy());
		soapModel.setCreatedDate(model.getCreatedDate());
		soapModel.setBusinessUnitId(model.getBusinessUnitId());
		soapModel.setReversalPeriodDate(model.getReversalPeriodDate());
		soapModel.setWorkflowId(model.getWorkflowId());
		soapModel.setChartOfAccounts(model.getChartOfAccounts());
		soapModel.setUserId(model.getUserId());
		soapModel.setBatchName(model.getBatchName());
		soapModel.setDatabase(model.getDatabase());
		soapModel.setCostCenter(model.getCostCenter());
		soapModel.setOutboundStatus(model.getOutboundStatus());
		soapModel.setDataAccessSet(model.getDataAccessSet());
		soapModel.setFuture1(model.getFuture1());
		soapModel.setFuture2(model.getFuture2());
		soapModel.setWorkflowName(model.getWorkflowName());
		soapModel.setWorkflowCreatedBy(model.getWorkflowCreatedBy());
		soapModel.setCurrency(model.getCurrency());
		soapModel.setBatchId(model.getBatchId());
		soapModel.setAccountCategory(model.getAccountCategory());
		soapModel.setReverseJournal(model.getReverseJournal());
		soapModel.setWorkflowApprovedBy(model.getWorkflowApprovedBy());
		soapModel.setBrand(model.getBrand());
		soapModel.setAccountingDate(model.getAccountingDate());
		soapModel.setRedemptionPeriod(model.getRedemptionPeriod());
		soapModel.setOriginalBatchId(model.getOriginalBatchId());
		soapModel.setAdjustmentLevel(model.getAdjustmentLevel());

		return soapModel;
	}

	public static StAdjustmentReserveDetailSoap[] toSoapModels(
		StAdjustmentReserveDetail[] models) {
		StAdjustmentReserveDetailSoap[] soapModels = new StAdjustmentReserveDetailSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static StAdjustmentReserveDetailSoap[][] toSoapModels(
		StAdjustmentReserveDetail[][] models) {
		StAdjustmentReserveDetailSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new StAdjustmentReserveDetailSoap[models.length][models[0].length];
		}
		else {
			soapModels = new StAdjustmentReserveDetailSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static StAdjustmentReserveDetailSoap[] toSoapModels(
		List<StAdjustmentReserveDetail> models) {
		List<StAdjustmentReserveDetailSoap> soapModels = new ArrayList<StAdjustmentReserveDetailSoap>(models.size());

		for (StAdjustmentReserveDetail model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new StAdjustmentReserveDetailSoap[soapModels.size()]);
	}

	public StAdjustmentReserveDetailSoap() {
	}

	public String getPrimaryKey() {
		return _workflowId;
	}

	public void setPrimaryKey(String pk) {
		setWorkflowId(pk);
	}

	public Date getAdjustmentCreatedDate() {
		return _adjustmentCreatedDate;
	}

	public void setAdjustmentCreatedDate(Date adjustmentCreatedDate) {
		_adjustmentCreatedDate = adjustmentCreatedDate;
	}

	public boolean getEtlCheckRecord() {
		return _etlCheckRecord;
	}

	public boolean isEtlCheckRecord() {
		return _etlCheckRecord;
	}

	public void setEtlCheckRecord(boolean etlCheckRecord) {
		_etlCheckRecord = etlCheckRecord;
	}

	public String getPostingIndicator() {
		return _postingIndicator;
	}

	public void setPostingIndicator(String postingIndicator) {
		_postingIndicator = postingIndicator;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public String getAccount() {
		return _account;
	}

	public void setAccount(String account) {
		_account = account;
	}

	public String getCredit() {
		return _credit;
	}

	public void setCredit(String credit) {
		_credit = credit;
	}

	public Date getWorkflowApprovedDate() {
		return _workflowApprovedDate;
	}

	public void setWorkflowApprovedDate(Date workflowApprovedDate) {
		_workflowApprovedDate = workflowApprovedDate;
	}

	public String getSource() {
		return _source;
	}

	public void setSource(String source) {
		_source = source;
	}

	public String getLineDescription() {
		return _lineDescription;
	}

	public void setLineDescription(String lineDescription) {
		_lineDescription = lineDescription;
	}

	public String getLedger() {
		return _ledger;
	}

	public void setLedger(String ledger) {
		_ledger = ledger;
	}

	public String getUdc6() {
		return _udc6;
	}

	public void setUdc6(String udc6) {
		_udc6 = udc6;
	}

	public String getUdc5() {
		return _udc5;
	}

	public void setUdc5(String udc5) {
		_udc5 = udc5;
	}

	public String getUdc4() {
		return _udc4;
	}

	public void setUdc4(String udc4) {
		_udc4 = udc4;
	}

	public Date getWorkflowCreatedDate() {
		return _workflowCreatedDate;
	}

	public void setWorkflowCreatedDate(Date workflowCreatedDate) {
		_workflowCreatedDate = workflowCreatedDate;
	}

	public String getUdc3() {
		return _udc3;
	}

	public void setUdc3(String udc3) {
		_udc3 = udc3;
	}

	public String getUdc2() {
		return _udc2;
	}

	public void setUdc2(String udc2) {
		_udc2 = udc2;
	}

	public String getUdc1() {
		return _udc1;
	}

	public void setUdc1(String udc1) {
		_udc1 = udc1;
	}

	public String getAdjustmentType() {
		return _adjustmentType;
	}

	public void setAdjustmentType(String adjustmentType) {
		_adjustmentType = adjustmentType;
	}

	public String getModifiedBy() {
		return _modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		_modifiedBy = modifiedBy;
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

	public String getGlCompanyName() {
		return _glCompanyName;
	}

	public void setGlCompanyName(String glCompanyName) {
		_glCompanyName = glCompanyName;
	}

	public String getDivision() {
		return _division;
	}

	public void setDivision(String division) {
		_division = division;
	}

	public String getBalanceType() {
		return _balanceType;
	}

	public void setBalanceType(String balanceType) {
		_balanceType = balanceType;
	}

	public String getSessionId() {
		return _sessionId;
	}

	public void setSessionId(String sessionId) {
		_sessionId = sessionId;
	}

	public String getJournalName() {
		return _journalName;
	}

	public void setJournalName(String journalName) {
		_journalName = journalName;
	}

	public String getProject() {
		return _project;
	}

	public void setProject(String project) {
		_project = project;
	}

	public String getDebit() {
		return _debit;
	}

	public void setDebit(String debit) {
		_debit = debit;
	}

	public String getAccountType() {
		return _accountType;
	}

	public void setAccountType(String accountType) {
		_accountType = accountType;
	}

	public String getJournalDescription() {
		return _journalDescription;
	}

	public void setJournalDescription(String journalDescription) {
		_journalDescription = journalDescription;
	}

	public String getCategory() {
		return _category;
	}

	public void setCategory(String category) {
		_category = category;
	}

	public String getCreatedBy() {
		return _createdBy;
	}

	public void setCreatedBy(String createdBy) {
		_createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return _createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		_createdDate = createdDate;
	}

	public String getBusinessUnitId() {
		return _businessUnitId;
	}

	public void setBusinessUnitId(String businessUnitId) {
		_businessUnitId = businessUnitId;
	}

	public Date getReversalPeriodDate() {
		return _reversalPeriodDate;
	}

	public void setReversalPeriodDate(Date reversalPeriodDate) {
		_reversalPeriodDate = reversalPeriodDate;
	}

	public String getWorkflowId() {
		return _workflowId;
	}

	public void setWorkflowId(String workflowId) {
		_workflowId = workflowId;
	}

	public String getChartOfAccounts() {
		return _chartOfAccounts;
	}

	public void setChartOfAccounts(String chartOfAccounts) {
		_chartOfAccounts = chartOfAccounts;
	}

	public String getUserId() {
		return _userId;
	}

	public void setUserId(String userId) {
		_userId = userId;
	}

	public String getBatchName() {
		return _batchName;
	}

	public void setBatchName(String batchName) {
		_batchName = batchName;
	}

	public String getDatabase() {
		return _database;
	}

	public void setDatabase(String database) {
		_database = database;
	}

	public String getCostCenter() {
		return _costCenter;
	}

	public void setCostCenter(String costCenter) {
		_costCenter = costCenter;
	}

	public String getOutboundStatus() {
		return _outboundStatus;
	}

	public void setOutboundStatus(String outboundStatus) {
		_outboundStatus = outboundStatus;
	}

	public String getDataAccessSet() {
		return _dataAccessSet;
	}

	public void setDataAccessSet(String dataAccessSet) {
		_dataAccessSet = dataAccessSet;
	}

	public String getFuture1() {
		return _future1;
	}

	public void setFuture1(String future1) {
		_future1 = future1;
	}

	public String getFuture2() {
		return _future2;
	}

	public void setFuture2(String future2) {
		_future2 = future2;
	}

	public String getWorkflowName() {
		return _workflowName;
	}

	public void setWorkflowName(String workflowName) {
		_workflowName = workflowName;
	}

	public String getWorkflowCreatedBy() {
		return _workflowCreatedBy;
	}

	public void setWorkflowCreatedBy(String workflowCreatedBy) {
		_workflowCreatedBy = workflowCreatedBy;
	}

	public String getCurrency() {
		return _currency;
	}

	public void setCurrency(String currency) {
		_currency = currency;
	}

	public String getBatchId() {
		return _batchId;
	}

	public void setBatchId(String batchId) {
		_batchId = batchId;
	}

	public String getAccountCategory() {
		return _accountCategory;
	}

	public void setAccountCategory(String accountCategory) {
		_accountCategory = accountCategory;
	}

	public String getReverseJournal() {
		return _reverseJournal;
	}

	public void setReverseJournal(String reverseJournal) {
		_reverseJournal = reverseJournal;
	}

	public String getWorkflowApprovedBy() {
		return _workflowApprovedBy;
	}

	public void setWorkflowApprovedBy(String workflowApprovedBy) {
		_workflowApprovedBy = workflowApprovedBy;
	}

	public String getBrand() {
		return _brand;
	}

	public void setBrand(String brand) {
		_brand = brand;
	}

	public Date getAccountingDate() {
		return _accountingDate;
	}

	public void setAccountingDate(Date accountingDate) {
		_accountingDate = accountingDate;
	}

	public Date getRedemptionPeriod() {
		return _redemptionPeriod;
	}

	public void setRedemptionPeriod(Date redemptionPeriod) {
		_redemptionPeriod = redemptionPeriod;
	}

	public String getOriginalBatchId() {
		return _originalBatchId;
	}

	public void setOriginalBatchId(String originalBatchId) {
		_originalBatchId = originalBatchId;
	}

	public String getAdjustmentLevel() {
		return _adjustmentLevel;
	}

	public void setAdjustmentLevel(String adjustmentLevel) {
		_adjustmentLevel = adjustmentLevel;
	}

	private Date _adjustmentCreatedDate;
	private boolean _etlCheckRecord;
	private String _postingIndicator;
	private Date _modifiedDate;
	private String _account;
	private String _credit;
	private Date _workflowApprovedDate;
	private String _source;
	private String _lineDescription;
	private String _ledger;
	private String _udc6;
	private String _udc5;
	private String _udc4;
	private Date _workflowCreatedDate;
	private String _udc3;
	private String _udc2;
	private String _udc1;
	private String _adjustmentType;
	private String _modifiedBy;
	private boolean _checkRecord;
	private String _glCompanyName;
	private String _division;
	private String _balanceType;
	private String _sessionId;
	private String _journalName;
	private String _project;
	private String _debit;
	private String _accountType;
	private String _journalDescription;
	private String _category;
	private String _createdBy;
	private Date _createdDate;
	private String _businessUnitId;
	private Date _reversalPeriodDate;
	private String _workflowId;
	private String _chartOfAccounts;
	private String _userId;
	private String _batchName;
	private String _database;
	private String _costCenter;
	private String _outboundStatus;
	private String _dataAccessSet;
	private String _future1;
	private String _future2;
	private String _workflowName;
	private String _workflowCreatedBy;
	private String _currency;
	private String _batchId;
	private String _accountCategory;
	private String _reverseJournal;
	private String _workflowApprovedBy;
	private String _brand;
	private Date _accountingDate;
	private Date _redemptionPeriod;
	private String _originalBatchId;
	private String _adjustmentLevel;
}