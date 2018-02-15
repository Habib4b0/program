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

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link StAdjustmentReserveDetail}.
 * </p>
 *
 * @author
 * @see StAdjustmentReserveDetail
 * @generated
 */
@ProviderType
public class StAdjustmentReserveDetailWrapper
	implements StAdjustmentReserveDetail,
		ModelWrapper<StAdjustmentReserveDetail> {
	public StAdjustmentReserveDetailWrapper(
		StAdjustmentReserveDetail stAdjustmentReserveDetail) {
		_stAdjustmentReserveDetail = stAdjustmentReserveDetail;
	}

	@Override
	public Class<?> getModelClass() {
		return StAdjustmentReserveDetail.class;
	}

	@Override
	public String getModelClassName() {
		return StAdjustmentReserveDetail.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("adjustmentCreatedDate", getAdjustmentCreatedDate());
		attributes.put("etlCheckRecord", getEtlCheckRecord());
		attributes.put("postingIndicator", getPostingIndicator());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("account", getAccount());
		attributes.put("credit", getCredit());
		attributes.put("workflowApprovedDate", getWorkflowApprovedDate());
		attributes.put("source", getSource());
		attributes.put("lineDescription", getLineDescription());
		attributes.put("ledger", getLedger());
		attributes.put("udc6", getUdc6());
		attributes.put("udc5", getUdc5());
		attributes.put("udc4", getUdc4());
		attributes.put("workflowCreatedDate", getWorkflowCreatedDate());
		attributes.put("udc3", getUdc3());
		attributes.put("udc2", getUdc2());
		attributes.put("udc1", getUdc1());
		attributes.put("adjustmentType", getAdjustmentType());
		attributes.put("modifiedBy", getModifiedBy());
		attributes.put("checkRecord", getCheckRecord());
		attributes.put("glCompanyName", getGlCompanyName());
		attributes.put("division", getDivision());
		attributes.put("balanceType", getBalanceType());
		attributes.put("sessionId", getSessionId());
		attributes.put("journalName", getJournalName());
		attributes.put("project", getProject());
		attributes.put("debit", getDebit());
		attributes.put("accountType", getAccountType());
		attributes.put("journalDescription", getJournalDescription());
		attributes.put("category", getCategory());
		attributes.put("createdBy", getCreatedBy());
		attributes.put("createdDate", getCreatedDate());
		attributes.put("businessUnitId", getBusinessUnitId());
		attributes.put("reversalPeriodDate", getReversalPeriodDate());
		attributes.put("workflowId", getWorkflowId());
		attributes.put("chartOfAccounts", getChartOfAccounts());
		attributes.put("userId", getUserId());
		attributes.put("batchName", getBatchName());
		attributes.put("database", getDatabase());
		attributes.put("costCenter", getCostCenter());
		attributes.put("outboundStatus", getOutboundStatus());
		attributes.put("dataAccessSet", getDataAccessSet());
		attributes.put("future1", getFuture1());
		attributes.put("future2", getFuture2());
		attributes.put("workflowName", getWorkflowName());
		attributes.put("workflowCreatedBy", getWorkflowCreatedBy());
		attributes.put("currency", getCurrency());
		attributes.put("batchId", getBatchId());
		attributes.put("accountCategory", getAccountCategory());
		attributes.put("reverseJournal", getReverseJournal());
		attributes.put("workflowApprovedBy", getWorkflowApprovedBy());
		attributes.put("brand", getBrand());
		attributes.put("accountingDate", getAccountingDate());
		attributes.put("redemptionPeriod", getRedemptionPeriod());
		attributes.put("originalBatchId", getOriginalBatchId());
		attributes.put("adjustmentLevel", getAdjustmentLevel());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Date adjustmentCreatedDate = (Date)attributes.get(
				"adjustmentCreatedDate");

		if (adjustmentCreatedDate != null) {
			setAdjustmentCreatedDate(adjustmentCreatedDate);
		}

		Boolean etlCheckRecord = (Boolean)attributes.get("etlCheckRecord");

		if (etlCheckRecord != null) {
			setEtlCheckRecord(etlCheckRecord);
		}

		String postingIndicator = (String)attributes.get("postingIndicator");

		if (postingIndicator != null) {
			setPostingIndicator(postingIndicator);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String account = (String)attributes.get("account");

		if (account != null) {
			setAccount(account);
		}

		String credit = (String)attributes.get("credit");

		if (credit != null) {
			setCredit(credit);
		}

		Date workflowApprovedDate = (Date)attributes.get("workflowApprovedDate");

		if (workflowApprovedDate != null) {
			setWorkflowApprovedDate(workflowApprovedDate);
		}

		String source = (String)attributes.get("source");

		if (source != null) {
			setSource(source);
		}

		String lineDescription = (String)attributes.get("lineDescription");

		if (lineDescription != null) {
			setLineDescription(lineDescription);
		}

		String ledger = (String)attributes.get("ledger");

		if (ledger != null) {
			setLedger(ledger);
		}

		String udc6 = (String)attributes.get("udc6");

		if (udc6 != null) {
			setUdc6(udc6);
		}

		String udc5 = (String)attributes.get("udc5");

		if (udc5 != null) {
			setUdc5(udc5);
		}

		String udc4 = (String)attributes.get("udc4");

		if (udc4 != null) {
			setUdc4(udc4);
		}

		Date workflowCreatedDate = (Date)attributes.get("workflowCreatedDate");

		if (workflowCreatedDate != null) {
			setWorkflowCreatedDate(workflowCreatedDate);
		}

		String udc3 = (String)attributes.get("udc3");

		if (udc3 != null) {
			setUdc3(udc3);
		}

		String udc2 = (String)attributes.get("udc2");

		if (udc2 != null) {
			setUdc2(udc2);
		}

		String udc1 = (String)attributes.get("udc1");

		if (udc1 != null) {
			setUdc1(udc1);
		}

		String adjustmentType = (String)attributes.get("adjustmentType");

		if (adjustmentType != null) {
			setAdjustmentType(adjustmentType);
		}

		String modifiedBy = (String)attributes.get("modifiedBy");

		if (modifiedBy != null) {
			setModifiedBy(modifiedBy);
		}

		Boolean checkRecord = (Boolean)attributes.get("checkRecord");

		if (checkRecord != null) {
			setCheckRecord(checkRecord);
		}

		String glCompanyName = (String)attributes.get("glCompanyName");

		if (glCompanyName != null) {
			setGlCompanyName(glCompanyName);
		}

		String division = (String)attributes.get("division");

		if (division != null) {
			setDivision(division);
		}

		String balanceType = (String)attributes.get("balanceType");

		if (balanceType != null) {
			setBalanceType(balanceType);
		}

		String sessionId = (String)attributes.get("sessionId");

		if (sessionId != null) {
			setSessionId(sessionId);
		}

		String journalName = (String)attributes.get("journalName");

		if (journalName != null) {
			setJournalName(journalName);
		}

		String project = (String)attributes.get("project");

		if (project != null) {
			setProject(project);
		}

		String debit = (String)attributes.get("debit");

		if (debit != null) {
			setDebit(debit);
		}

		String accountType = (String)attributes.get("accountType");

		if (accountType != null) {
			setAccountType(accountType);
		}

		String journalDescription = (String)attributes.get("journalDescription");

		if (journalDescription != null) {
			setJournalDescription(journalDescription);
		}

		String category = (String)attributes.get("category");

		if (category != null) {
			setCategory(category);
		}

		String createdBy = (String)attributes.get("createdBy");

		if (createdBy != null) {
			setCreatedBy(createdBy);
		}

		Date createdDate = (Date)attributes.get("createdDate");

		if (createdDate != null) {
			setCreatedDate(createdDate);
		}

		String businessUnitId = (String)attributes.get("businessUnitId");

		if (businessUnitId != null) {
			setBusinessUnitId(businessUnitId);
		}

		Date reversalPeriodDate = (Date)attributes.get("reversalPeriodDate");

		if (reversalPeriodDate != null) {
			setReversalPeriodDate(reversalPeriodDate);
		}

		String workflowId = (String)attributes.get("workflowId");

		if (workflowId != null) {
			setWorkflowId(workflowId);
		}

		String chartOfAccounts = (String)attributes.get("chartOfAccounts");

		if (chartOfAccounts != null) {
			setChartOfAccounts(chartOfAccounts);
		}

		String userId = (String)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String batchName = (String)attributes.get("batchName");

		if (batchName != null) {
			setBatchName(batchName);
		}

		String database = (String)attributes.get("database");

		if (database != null) {
			setDatabase(database);
		}

		String costCenter = (String)attributes.get("costCenter");

		if (costCenter != null) {
			setCostCenter(costCenter);
		}

		String outboundStatus = (String)attributes.get("outboundStatus");

		if (outboundStatus != null) {
			setOutboundStatus(outboundStatus);
		}

		String dataAccessSet = (String)attributes.get("dataAccessSet");

		if (dataAccessSet != null) {
			setDataAccessSet(dataAccessSet);
		}

		String future1 = (String)attributes.get("future1");

		if (future1 != null) {
			setFuture1(future1);
		}

		String future2 = (String)attributes.get("future2");

		if (future2 != null) {
			setFuture2(future2);
		}

		String workflowName = (String)attributes.get("workflowName");

		if (workflowName != null) {
			setWorkflowName(workflowName);
		}

		String workflowCreatedBy = (String)attributes.get("workflowCreatedBy");

		if (workflowCreatedBy != null) {
			setWorkflowCreatedBy(workflowCreatedBy);
		}

		String currency = (String)attributes.get("currency");

		if (currency != null) {
			setCurrency(currency);
		}

		String batchId = (String)attributes.get("batchId");

		if (batchId != null) {
			setBatchId(batchId);
		}

		String accountCategory = (String)attributes.get("accountCategory");

		if (accountCategory != null) {
			setAccountCategory(accountCategory);
		}

		String reverseJournal = (String)attributes.get("reverseJournal");

		if (reverseJournal != null) {
			setReverseJournal(reverseJournal);
		}

		String workflowApprovedBy = (String)attributes.get("workflowApprovedBy");

		if (workflowApprovedBy != null) {
			setWorkflowApprovedBy(workflowApprovedBy);
		}

		String brand = (String)attributes.get("brand");

		if (brand != null) {
			setBrand(brand);
		}

		Date accountingDate = (Date)attributes.get("accountingDate");

		if (accountingDate != null) {
			setAccountingDate(accountingDate);
		}

		Date redemptionPeriod = (Date)attributes.get("redemptionPeriod");

		if (redemptionPeriod != null) {
			setRedemptionPeriod(redemptionPeriod);
		}

		String originalBatchId = (String)attributes.get("originalBatchId");

		if (originalBatchId != null) {
			setOriginalBatchId(originalBatchId);
		}

		String adjustmentLevel = (String)attributes.get("adjustmentLevel");

		if (adjustmentLevel != null) {
			setAdjustmentLevel(adjustmentLevel);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new StAdjustmentReserveDetailWrapper((StAdjustmentReserveDetail)_stAdjustmentReserveDetail.clone());
	}

	@Override
	public int compareTo(StAdjustmentReserveDetail stAdjustmentReserveDetail) {
		return _stAdjustmentReserveDetail.compareTo(stAdjustmentReserveDetail);
	}

	/**
	* Returns the account of this st adjustment reserve detail.
	*
	* @return the account of this st adjustment reserve detail
	*/
	@Override
	public java.lang.String getAccount() {
		return _stAdjustmentReserveDetail.getAccount();
	}

	/**
	* Returns the account category of this st adjustment reserve detail.
	*
	* @return the account category of this st adjustment reserve detail
	*/
	@Override
	public java.lang.String getAccountCategory() {
		return _stAdjustmentReserveDetail.getAccountCategory();
	}

	/**
	* Returns the accounting date of this st adjustment reserve detail.
	*
	* @return the accounting date of this st adjustment reserve detail
	*/
	@Override
	public Date getAccountingDate() {
		return _stAdjustmentReserveDetail.getAccountingDate();
	}

	/**
	* Returns the account type of this st adjustment reserve detail.
	*
	* @return the account type of this st adjustment reserve detail
	*/
	@Override
	public java.lang.String getAccountType() {
		return _stAdjustmentReserveDetail.getAccountType();
	}

	/**
	* Returns the adjustment created date of this st adjustment reserve detail.
	*
	* @return the adjustment created date of this st adjustment reserve detail
	*/
	@Override
	public Date getAdjustmentCreatedDate() {
		return _stAdjustmentReserveDetail.getAdjustmentCreatedDate();
	}

	/**
	* Returns the adjustment level of this st adjustment reserve detail.
	*
	* @return the adjustment level of this st adjustment reserve detail
	*/
	@Override
	public java.lang.String getAdjustmentLevel() {
		return _stAdjustmentReserveDetail.getAdjustmentLevel();
	}

	/**
	* Returns the adjustment type of this st adjustment reserve detail.
	*
	* @return the adjustment type of this st adjustment reserve detail
	*/
	@Override
	public java.lang.String getAdjustmentType() {
		return _stAdjustmentReserveDetail.getAdjustmentType();
	}

	/**
	* Returns the balance type of this st adjustment reserve detail.
	*
	* @return the balance type of this st adjustment reserve detail
	*/
	@Override
	public java.lang.String getBalanceType() {
		return _stAdjustmentReserveDetail.getBalanceType();
	}

	/**
	* Returns the batch ID of this st adjustment reserve detail.
	*
	* @return the batch ID of this st adjustment reserve detail
	*/
	@Override
	public java.lang.String getBatchId() {
		return _stAdjustmentReserveDetail.getBatchId();
	}

	/**
	* Returns the batch name of this st adjustment reserve detail.
	*
	* @return the batch name of this st adjustment reserve detail
	*/
	@Override
	public java.lang.String getBatchName() {
		return _stAdjustmentReserveDetail.getBatchName();
	}

	/**
	* Returns the brand of this st adjustment reserve detail.
	*
	* @return the brand of this st adjustment reserve detail
	*/
	@Override
	public java.lang.String getBrand() {
		return _stAdjustmentReserveDetail.getBrand();
	}

	/**
	* Returns the business unit ID of this st adjustment reserve detail.
	*
	* @return the business unit ID of this st adjustment reserve detail
	*/
	@Override
	public java.lang.String getBusinessUnitId() {
		return _stAdjustmentReserveDetail.getBusinessUnitId();
	}

	/**
	* Returns the category of this st adjustment reserve detail.
	*
	* @return the category of this st adjustment reserve detail
	*/
	@Override
	public java.lang.String getCategory() {
		return _stAdjustmentReserveDetail.getCategory();
	}

	/**
	* Returns the chart of accounts of this st adjustment reserve detail.
	*
	* @return the chart of accounts of this st adjustment reserve detail
	*/
	@Override
	public java.lang.String getChartOfAccounts() {
		return _stAdjustmentReserveDetail.getChartOfAccounts();
	}

	/**
	* Returns the check record of this st adjustment reserve detail.
	*
	* @return the check record of this st adjustment reserve detail
	*/
	@Override
	public boolean getCheckRecord() {
		return _stAdjustmentReserveDetail.getCheckRecord();
	}

	/**
	* Returns the cost center of this st adjustment reserve detail.
	*
	* @return the cost center of this st adjustment reserve detail
	*/
	@Override
	public java.lang.String getCostCenter() {
		return _stAdjustmentReserveDetail.getCostCenter();
	}

	/**
	* Returns the created by of this st adjustment reserve detail.
	*
	* @return the created by of this st adjustment reserve detail
	*/
	@Override
	public java.lang.String getCreatedBy() {
		return _stAdjustmentReserveDetail.getCreatedBy();
	}

	/**
	* Returns the created date of this st adjustment reserve detail.
	*
	* @return the created date of this st adjustment reserve detail
	*/
	@Override
	public Date getCreatedDate() {
		return _stAdjustmentReserveDetail.getCreatedDate();
	}

	/**
	* Returns the credit of this st adjustment reserve detail.
	*
	* @return the credit of this st adjustment reserve detail
	*/
	@Override
	public java.lang.String getCredit() {
		return _stAdjustmentReserveDetail.getCredit();
	}

	/**
	* Returns the currency of this st adjustment reserve detail.
	*
	* @return the currency of this st adjustment reserve detail
	*/
	@Override
	public java.lang.String getCurrency() {
		return _stAdjustmentReserveDetail.getCurrency();
	}

	/**
	* Returns the data access set of this st adjustment reserve detail.
	*
	* @return the data access set of this st adjustment reserve detail
	*/
	@Override
	public java.lang.String getDataAccessSet() {
		return _stAdjustmentReserveDetail.getDataAccessSet();
	}

	/**
	* Returns the database of this st adjustment reserve detail.
	*
	* @return the database of this st adjustment reserve detail
	*/
	@Override
	public java.lang.String getDatabase() {
		return _stAdjustmentReserveDetail.getDatabase();
	}

	/**
	* Returns the debit of this st adjustment reserve detail.
	*
	* @return the debit of this st adjustment reserve detail
	*/
	@Override
	public java.lang.String getDebit() {
		return _stAdjustmentReserveDetail.getDebit();
	}

	/**
	* Returns the division of this st adjustment reserve detail.
	*
	* @return the division of this st adjustment reserve detail
	*/
	@Override
	public java.lang.String getDivision() {
		return _stAdjustmentReserveDetail.getDivision();
	}

	/**
	* Returns the etl check record of this st adjustment reserve detail.
	*
	* @return the etl check record of this st adjustment reserve detail
	*/
	@Override
	public boolean getEtlCheckRecord() {
		return _stAdjustmentReserveDetail.getEtlCheckRecord();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _stAdjustmentReserveDetail.getExpandoBridge();
	}

	/**
	* Returns the future1 of this st adjustment reserve detail.
	*
	* @return the future1 of this st adjustment reserve detail
	*/
	@Override
	public java.lang.String getFuture1() {
		return _stAdjustmentReserveDetail.getFuture1();
	}

	/**
	* Returns the future2 of this st adjustment reserve detail.
	*
	* @return the future2 of this st adjustment reserve detail
	*/
	@Override
	public java.lang.String getFuture2() {
		return _stAdjustmentReserveDetail.getFuture2();
	}

	/**
	* Returns the gl company name of this st adjustment reserve detail.
	*
	* @return the gl company name of this st adjustment reserve detail
	*/
	@Override
	public java.lang.String getGlCompanyName() {
		return _stAdjustmentReserveDetail.getGlCompanyName();
	}

	/**
	* Returns the journal description of this st adjustment reserve detail.
	*
	* @return the journal description of this st adjustment reserve detail
	*/
	@Override
	public java.lang.String getJournalDescription() {
		return _stAdjustmentReserveDetail.getJournalDescription();
	}

	/**
	* Returns the journal name of this st adjustment reserve detail.
	*
	* @return the journal name of this st adjustment reserve detail
	*/
	@Override
	public java.lang.String getJournalName() {
		return _stAdjustmentReserveDetail.getJournalName();
	}

	/**
	* Returns the ledger of this st adjustment reserve detail.
	*
	* @return the ledger of this st adjustment reserve detail
	*/
	@Override
	public java.lang.String getLedger() {
		return _stAdjustmentReserveDetail.getLedger();
	}

	/**
	* Returns the line description of this st adjustment reserve detail.
	*
	* @return the line description of this st adjustment reserve detail
	*/
	@Override
	public java.lang.String getLineDescription() {
		return _stAdjustmentReserveDetail.getLineDescription();
	}

	/**
	* Returns the modified by of this st adjustment reserve detail.
	*
	* @return the modified by of this st adjustment reserve detail
	*/
	@Override
	public java.lang.String getModifiedBy() {
		return _stAdjustmentReserveDetail.getModifiedBy();
	}

	/**
	* Returns the modified date of this st adjustment reserve detail.
	*
	* @return the modified date of this st adjustment reserve detail
	*/
	@Override
	public Date getModifiedDate() {
		return _stAdjustmentReserveDetail.getModifiedDate();
	}

	/**
	* Returns the original batch ID of this st adjustment reserve detail.
	*
	* @return the original batch ID of this st adjustment reserve detail
	*/
	@Override
	public java.lang.String getOriginalBatchId() {
		return _stAdjustmentReserveDetail.getOriginalBatchId();
	}

	/**
	* Returns the outbound status of this st adjustment reserve detail.
	*
	* @return the outbound status of this st adjustment reserve detail
	*/
	@Override
	public java.lang.String getOutboundStatus() {
		return _stAdjustmentReserveDetail.getOutboundStatus();
	}

	/**
	* Returns the posting indicator of this st adjustment reserve detail.
	*
	* @return the posting indicator of this st adjustment reserve detail
	*/
	@Override
	public java.lang.String getPostingIndicator() {
		return _stAdjustmentReserveDetail.getPostingIndicator();
	}

	/**
	* Returns the primary key of this st adjustment reserve detail.
	*
	* @return the primary key of this st adjustment reserve detail
	*/
	@Override
	public java.lang.String getPrimaryKey() {
		return _stAdjustmentReserveDetail.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _stAdjustmentReserveDetail.getPrimaryKeyObj();
	}

	/**
	* Returns the project of this st adjustment reserve detail.
	*
	* @return the project of this st adjustment reserve detail
	*/
	@Override
	public java.lang.String getProject() {
		return _stAdjustmentReserveDetail.getProject();
	}

	/**
	* Returns the redemption period of this st adjustment reserve detail.
	*
	* @return the redemption period of this st adjustment reserve detail
	*/
	@Override
	public Date getRedemptionPeriod() {
		return _stAdjustmentReserveDetail.getRedemptionPeriod();
	}

	/**
	* Returns the reversal period date of this st adjustment reserve detail.
	*
	* @return the reversal period date of this st adjustment reserve detail
	*/
	@Override
	public Date getReversalPeriodDate() {
		return _stAdjustmentReserveDetail.getReversalPeriodDate();
	}

	/**
	* Returns the reverse journal of this st adjustment reserve detail.
	*
	* @return the reverse journal of this st adjustment reserve detail
	*/
	@Override
	public java.lang.String getReverseJournal() {
		return _stAdjustmentReserveDetail.getReverseJournal();
	}

	/**
	* Returns the session ID of this st adjustment reserve detail.
	*
	* @return the session ID of this st adjustment reserve detail
	*/
	@Override
	public java.lang.String getSessionId() {
		return _stAdjustmentReserveDetail.getSessionId();
	}

	/**
	* Returns the source of this st adjustment reserve detail.
	*
	* @return the source of this st adjustment reserve detail
	*/
	@Override
	public java.lang.String getSource() {
		return _stAdjustmentReserveDetail.getSource();
	}

	/**
	* Returns the udc1 of this st adjustment reserve detail.
	*
	* @return the udc1 of this st adjustment reserve detail
	*/
	@Override
	public java.lang.String getUdc1() {
		return _stAdjustmentReserveDetail.getUdc1();
	}

	/**
	* Returns the udc2 of this st adjustment reserve detail.
	*
	* @return the udc2 of this st adjustment reserve detail
	*/
	@Override
	public java.lang.String getUdc2() {
		return _stAdjustmentReserveDetail.getUdc2();
	}

	/**
	* Returns the udc3 of this st adjustment reserve detail.
	*
	* @return the udc3 of this st adjustment reserve detail
	*/
	@Override
	public java.lang.String getUdc3() {
		return _stAdjustmentReserveDetail.getUdc3();
	}

	/**
	* Returns the udc4 of this st adjustment reserve detail.
	*
	* @return the udc4 of this st adjustment reserve detail
	*/
	@Override
	public java.lang.String getUdc4() {
		return _stAdjustmentReserveDetail.getUdc4();
	}

	/**
	* Returns the udc5 of this st adjustment reserve detail.
	*
	* @return the udc5 of this st adjustment reserve detail
	*/
	@Override
	public java.lang.String getUdc5() {
		return _stAdjustmentReserveDetail.getUdc5();
	}

	/**
	* Returns the udc6 of this st adjustment reserve detail.
	*
	* @return the udc6 of this st adjustment reserve detail
	*/
	@Override
	public java.lang.String getUdc6() {
		return _stAdjustmentReserveDetail.getUdc6();
	}

	/**
	* Returns the user ID of this st adjustment reserve detail.
	*
	* @return the user ID of this st adjustment reserve detail
	*/
	@Override
	public java.lang.String getUserId() {
		return _stAdjustmentReserveDetail.getUserId();
	}

	/**
	* Returns the workflow approved by of this st adjustment reserve detail.
	*
	* @return the workflow approved by of this st adjustment reserve detail
	*/
	@Override
	public java.lang.String getWorkflowApprovedBy() {
		return _stAdjustmentReserveDetail.getWorkflowApprovedBy();
	}

	/**
	* Returns the workflow approved date of this st adjustment reserve detail.
	*
	* @return the workflow approved date of this st adjustment reserve detail
	*/
	@Override
	public Date getWorkflowApprovedDate() {
		return _stAdjustmentReserveDetail.getWorkflowApprovedDate();
	}

	/**
	* Returns the workflow created by of this st adjustment reserve detail.
	*
	* @return the workflow created by of this st adjustment reserve detail
	*/
	@Override
	public java.lang.String getWorkflowCreatedBy() {
		return _stAdjustmentReserveDetail.getWorkflowCreatedBy();
	}

	/**
	* Returns the workflow created date of this st adjustment reserve detail.
	*
	* @return the workflow created date of this st adjustment reserve detail
	*/
	@Override
	public Date getWorkflowCreatedDate() {
		return _stAdjustmentReserveDetail.getWorkflowCreatedDate();
	}

	/**
	* Returns the workflow ID of this st adjustment reserve detail.
	*
	* @return the workflow ID of this st adjustment reserve detail
	*/
	@Override
	public java.lang.String getWorkflowId() {
		return _stAdjustmentReserveDetail.getWorkflowId();
	}

	/**
	* Returns the workflow name of this st adjustment reserve detail.
	*
	* @return the workflow name of this st adjustment reserve detail
	*/
	@Override
	public java.lang.String getWorkflowName() {
		return _stAdjustmentReserveDetail.getWorkflowName();
	}

	@Override
	public int hashCode() {
		return _stAdjustmentReserveDetail.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _stAdjustmentReserveDetail.isCachedModel();
	}

	/**
	* Returns <code>true</code> if this st adjustment reserve detail is check record.
	*
	* @return <code>true</code> if this st adjustment reserve detail is check record; <code>false</code> otherwise
	*/
	@Override
	public boolean isCheckRecord() {
		return _stAdjustmentReserveDetail.isCheckRecord();
	}

	@Override
	public boolean isEscapedModel() {
		return _stAdjustmentReserveDetail.isEscapedModel();
	}

	/**
	* Returns <code>true</code> if this st adjustment reserve detail is etl check record.
	*
	* @return <code>true</code> if this st adjustment reserve detail is etl check record; <code>false</code> otherwise
	*/
	@Override
	public boolean isEtlCheckRecord() {
		return _stAdjustmentReserveDetail.isEtlCheckRecord();
	}

	@Override
	public boolean isNew() {
		return _stAdjustmentReserveDetail.isNew();
	}

	@Override
	public void persist() {
		_stAdjustmentReserveDetail.persist();
	}

	/**
	* Sets the account of this st adjustment reserve detail.
	*
	* @param account the account of this st adjustment reserve detail
	*/
	@Override
	public void setAccount(java.lang.String account) {
		_stAdjustmentReserveDetail.setAccount(account);
	}

	/**
	* Sets the account category of this st adjustment reserve detail.
	*
	* @param accountCategory the account category of this st adjustment reserve detail
	*/
	@Override
	public void setAccountCategory(java.lang.String accountCategory) {
		_stAdjustmentReserveDetail.setAccountCategory(accountCategory);
	}

	/**
	* Sets the accounting date of this st adjustment reserve detail.
	*
	* @param accountingDate the accounting date of this st adjustment reserve detail
	*/
	@Override
	public void setAccountingDate(Date accountingDate) {
		_stAdjustmentReserveDetail.setAccountingDate(accountingDate);
	}

	/**
	* Sets the account type of this st adjustment reserve detail.
	*
	* @param accountType the account type of this st adjustment reserve detail
	*/
	@Override
	public void setAccountType(java.lang.String accountType) {
		_stAdjustmentReserveDetail.setAccountType(accountType);
	}

	/**
	* Sets the adjustment created date of this st adjustment reserve detail.
	*
	* @param adjustmentCreatedDate the adjustment created date of this st adjustment reserve detail
	*/
	@Override
	public void setAdjustmentCreatedDate(Date adjustmentCreatedDate) {
		_stAdjustmentReserveDetail.setAdjustmentCreatedDate(adjustmentCreatedDate);
	}

	/**
	* Sets the adjustment level of this st adjustment reserve detail.
	*
	* @param adjustmentLevel the adjustment level of this st adjustment reserve detail
	*/
	@Override
	public void setAdjustmentLevel(java.lang.String adjustmentLevel) {
		_stAdjustmentReserveDetail.setAdjustmentLevel(adjustmentLevel);
	}

	/**
	* Sets the adjustment type of this st adjustment reserve detail.
	*
	* @param adjustmentType the adjustment type of this st adjustment reserve detail
	*/
	@Override
	public void setAdjustmentType(java.lang.String adjustmentType) {
		_stAdjustmentReserveDetail.setAdjustmentType(adjustmentType);
	}

	/**
	* Sets the balance type of this st adjustment reserve detail.
	*
	* @param balanceType the balance type of this st adjustment reserve detail
	*/
	@Override
	public void setBalanceType(java.lang.String balanceType) {
		_stAdjustmentReserveDetail.setBalanceType(balanceType);
	}

	/**
	* Sets the batch ID of this st adjustment reserve detail.
	*
	* @param batchId the batch ID of this st adjustment reserve detail
	*/
	@Override
	public void setBatchId(java.lang.String batchId) {
		_stAdjustmentReserveDetail.setBatchId(batchId);
	}

	/**
	* Sets the batch name of this st adjustment reserve detail.
	*
	* @param batchName the batch name of this st adjustment reserve detail
	*/
	@Override
	public void setBatchName(java.lang.String batchName) {
		_stAdjustmentReserveDetail.setBatchName(batchName);
	}

	/**
	* Sets the brand of this st adjustment reserve detail.
	*
	* @param brand the brand of this st adjustment reserve detail
	*/
	@Override
	public void setBrand(java.lang.String brand) {
		_stAdjustmentReserveDetail.setBrand(brand);
	}

	/**
	* Sets the business unit ID of this st adjustment reserve detail.
	*
	* @param businessUnitId the business unit ID of this st adjustment reserve detail
	*/
	@Override
	public void setBusinessUnitId(java.lang.String businessUnitId) {
		_stAdjustmentReserveDetail.setBusinessUnitId(businessUnitId);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_stAdjustmentReserveDetail.setCachedModel(cachedModel);
	}

	/**
	* Sets the category of this st adjustment reserve detail.
	*
	* @param category the category of this st adjustment reserve detail
	*/
	@Override
	public void setCategory(java.lang.String category) {
		_stAdjustmentReserveDetail.setCategory(category);
	}

	/**
	* Sets the chart of accounts of this st adjustment reserve detail.
	*
	* @param chartOfAccounts the chart of accounts of this st adjustment reserve detail
	*/
	@Override
	public void setChartOfAccounts(java.lang.String chartOfAccounts) {
		_stAdjustmentReserveDetail.setChartOfAccounts(chartOfAccounts);
	}

	/**
	* Sets whether this st adjustment reserve detail is check record.
	*
	* @param checkRecord the check record of this st adjustment reserve detail
	*/
	@Override
	public void setCheckRecord(boolean checkRecord) {
		_stAdjustmentReserveDetail.setCheckRecord(checkRecord);
	}

	/**
	* Sets the cost center of this st adjustment reserve detail.
	*
	* @param costCenter the cost center of this st adjustment reserve detail
	*/
	@Override
	public void setCostCenter(java.lang.String costCenter) {
		_stAdjustmentReserveDetail.setCostCenter(costCenter);
	}

	/**
	* Sets the created by of this st adjustment reserve detail.
	*
	* @param createdBy the created by of this st adjustment reserve detail
	*/
	@Override
	public void setCreatedBy(java.lang.String createdBy) {
		_stAdjustmentReserveDetail.setCreatedBy(createdBy);
	}

	/**
	* Sets the created date of this st adjustment reserve detail.
	*
	* @param createdDate the created date of this st adjustment reserve detail
	*/
	@Override
	public void setCreatedDate(Date createdDate) {
		_stAdjustmentReserveDetail.setCreatedDate(createdDate);
	}

	/**
	* Sets the credit of this st adjustment reserve detail.
	*
	* @param credit the credit of this st adjustment reserve detail
	*/
	@Override
	public void setCredit(java.lang.String credit) {
		_stAdjustmentReserveDetail.setCredit(credit);
	}

	/**
	* Sets the currency of this st adjustment reserve detail.
	*
	* @param currency the currency of this st adjustment reserve detail
	*/
	@Override
	public void setCurrency(java.lang.String currency) {
		_stAdjustmentReserveDetail.setCurrency(currency);
	}

	/**
	* Sets the data access set of this st adjustment reserve detail.
	*
	* @param dataAccessSet the data access set of this st adjustment reserve detail
	*/
	@Override
	public void setDataAccessSet(java.lang.String dataAccessSet) {
		_stAdjustmentReserveDetail.setDataAccessSet(dataAccessSet);
	}

	/**
	* Sets the database of this st adjustment reserve detail.
	*
	* @param database the database of this st adjustment reserve detail
	*/
	@Override
	public void setDatabase(java.lang.String database) {
		_stAdjustmentReserveDetail.setDatabase(database);
	}

	/**
	* Sets the debit of this st adjustment reserve detail.
	*
	* @param debit the debit of this st adjustment reserve detail
	*/
	@Override
	public void setDebit(java.lang.String debit) {
		_stAdjustmentReserveDetail.setDebit(debit);
	}

	/**
	* Sets the division of this st adjustment reserve detail.
	*
	* @param division the division of this st adjustment reserve detail
	*/
	@Override
	public void setDivision(java.lang.String division) {
		_stAdjustmentReserveDetail.setDivision(division);
	}

	/**
	* Sets whether this st adjustment reserve detail is etl check record.
	*
	* @param etlCheckRecord the etl check record of this st adjustment reserve detail
	*/
	@Override
	public void setEtlCheckRecord(boolean etlCheckRecord) {
		_stAdjustmentReserveDetail.setEtlCheckRecord(etlCheckRecord);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_stAdjustmentReserveDetail.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_stAdjustmentReserveDetail.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_stAdjustmentReserveDetail.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the future1 of this st adjustment reserve detail.
	*
	* @param future1 the future1 of this st adjustment reserve detail
	*/
	@Override
	public void setFuture1(java.lang.String future1) {
		_stAdjustmentReserveDetail.setFuture1(future1);
	}

	/**
	* Sets the future2 of this st adjustment reserve detail.
	*
	* @param future2 the future2 of this st adjustment reserve detail
	*/
	@Override
	public void setFuture2(java.lang.String future2) {
		_stAdjustmentReserveDetail.setFuture2(future2);
	}

	/**
	* Sets the gl company name of this st adjustment reserve detail.
	*
	* @param glCompanyName the gl company name of this st adjustment reserve detail
	*/
	@Override
	public void setGlCompanyName(java.lang.String glCompanyName) {
		_stAdjustmentReserveDetail.setGlCompanyName(glCompanyName);
	}

	/**
	* Sets the journal description of this st adjustment reserve detail.
	*
	* @param journalDescription the journal description of this st adjustment reserve detail
	*/
	@Override
	public void setJournalDescription(java.lang.String journalDescription) {
		_stAdjustmentReserveDetail.setJournalDescription(journalDescription);
	}

	/**
	* Sets the journal name of this st adjustment reserve detail.
	*
	* @param journalName the journal name of this st adjustment reserve detail
	*/
	@Override
	public void setJournalName(java.lang.String journalName) {
		_stAdjustmentReserveDetail.setJournalName(journalName);
	}

	/**
	* Sets the ledger of this st adjustment reserve detail.
	*
	* @param ledger the ledger of this st adjustment reserve detail
	*/
	@Override
	public void setLedger(java.lang.String ledger) {
		_stAdjustmentReserveDetail.setLedger(ledger);
	}

	/**
	* Sets the line description of this st adjustment reserve detail.
	*
	* @param lineDescription the line description of this st adjustment reserve detail
	*/
	@Override
	public void setLineDescription(java.lang.String lineDescription) {
		_stAdjustmentReserveDetail.setLineDescription(lineDescription);
	}

	/**
	* Sets the modified by of this st adjustment reserve detail.
	*
	* @param modifiedBy the modified by of this st adjustment reserve detail
	*/
	@Override
	public void setModifiedBy(java.lang.String modifiedBy) {
		_stAdjustmentReserveDetail.setModifiedBy(modifiedBy);
	}

	/**
	* Sets the modified date of this st adjustment reserve detail.
	*
	* @param modifiedDate the modified date of this st adjustment reserve detail
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_stAdjustmentReserveDetail.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_stAdjustmentReserveDetail.setNew(n);
	}

	/**
	* Sets the original batch ID of this st adjustment reserve detail.
	*
	* @param originalBatchId the original batch ID of this st adjustment reserve detail
	*/
	@Override
	public void setOriginalBatchId(java.lang.String originalBatchId) {
		_stAdjustmentReserveDetail.setOriginalBatchId(originalBatchId);
	}

	/**
	* Sets the outbound status of this st adjustment reserve detail.
	*
	* @param outboundStatus the outbound status of this st adjustment reserve detail
	*/
	@Override
	public void setOutboundStatus(java.lang.String outboundStatus) {
		_stAdjustmentReserveDetail.setOutboundStatus(outboundStatus);
	}

	/**
	* Sets the posting indicator of this st adjustment reserve detail.
	*
	* @param postingIndicator the posting indicator of this st adjustment reserve detail
	*/
	@Override
	public void setPostingIndicator(java.lang.String postingIndicator) {
		_stAdjustmentReserveDetail.setPostingIndicator(postingIndicator);
	}

	/**
	* Sets the primary key of this st adjustment reserve detail.
	*
	* @param primaryKey the primary key of this st adjustment reserve detail
	*/
	@Override
	public void setPrimaryKey(java.lang.String primaryKey) {
		_stAdjustmentReserveDetail.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_stAdjustmentReserveDetail.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the project of this st adjustment reserve detail.
	*
	* @param project the project of this st adjustment reserve detail
	*/
	@Override
	public void setProject(java.lang.String project) {
		_stAdjustmentReserveDetail.setProject(project);
	}

	/**
	* Sets the redemption period of this st adjustment reserve detail.
	*
	* @param redemptionPeriod the redemption period of this st adjustment reserve detail
	*/
	@Override
	public void setRedemptionPeriod(Date redemptionPeriod) {
		_stAdjustmentReserveDetail.setRedemptionPeriod(redemptionPeriod);
	}

	/**
	* Sets the reversal period date of this st adjustment reserve detail.
	*
	* @param reversalPeriodDate the reversal period date of this st adjustment reserve detail
	*/
	@Override
	public void setReversalPeriodDate(Date reversalPeriodDate) {
		_stAdjustmentReserveDetail.setReversalPeriodDate(reversalPeriodDate);
	}

	/**
	* Sets the reverse journal of this st adjustment reserve detail.
	*
	* @param reverseJournal the reverse journal of this st adjustment reserve detail
	*/
	@Override
	public void setReverseJournal(java.lang.String reverseJournal) {
		_stAdjustmentReserveDetail.setReverseJournal(reverseJournal);
	}

	/**
	* Sets the session ID of this st adjustment reserve detail.
	*
	* @param sessionId the session ID of this st adjustment reserve detail
	*/
	@Override
	public void setSessionId(java.lang.String sessionId) {
		_stAdjustmentReserveDetail.setSessionId(sessionId);
	}

	/**
	* Sets the source of this st adjustment reserve detail.
	*
	* @param source the source of this st adjustment reserve detail
	*/
	@Override
	public void setSource(java.lang.String source) {
		_stAdjustmentReserveDetail.setSource(source);
	}

	/**
	* Sets the udc1 of this st adjustment reserve detail.
	*
	* @param udc1 the udc1 of this st adjustment reserve detail
	*/
	@Override
	public void setUdc1(java.lang.String udc1) {
		_stAdjustmentReserveDetail.setUdc1(udc1);
	}

	/**
	* Sets the udc2 of this st adjustment reserve detail.
	*
	* @param udc2 the udc2 of this st adjustment reserve detail
	*/
	@Override
	public void setUdc2(java.lang.String udc2) {
		_stAdjustmentReserveDetail.setUdc2(udc2);
	}

	/**
	* Sets the udc3 of this st adjustment reserve detail.
	*
	* @param udc3 the udc3 of this st adjustment reserve detail
	*/
	@Override
	public void setUdc3(java.lang.String udc3) {
		_stAdjustmentReserveDetail.setUdc3(udc3);
	}

	/**
	* Sets the udc4 of this st adjustment reserve detail.
	*
	* @param udc4 the udc4 of this st adjustment reserve detail
	*/
	@Override
	public void setUdc4(java.lang.String udc4) {
		_stAdjustmentReserveDetail.setUdc4(udc4);
	}

	/**
	* Sets the udc5 of this st adjustment reserve detail.
	*
	* @param udc5 the udc5 of this st adjustment reserve detail
	*/
	@Override
	public void setUdc5(java.lang.String udc5) {
		_stAdjustmentReserveDetail.setUdc5(udc5);
	}

	/**
	* Sets the udc6 of this st adjustment reserve detail.
	*
	* @param udc6 the udc6 of this st adjustment reserve detail
	*/
	@Override
	public void setUdc6(java.lang.String udc6) {
		_stAdjustmentReserveDetail.setUdc6(udc6);
	}

	/**
	* Sets the user ID of this st adjustment reserve detail.
	*
	* @param userId the user ID of this st adjustment reserve detail
	*/
	@Override
	public void setUserId(java.lang.String userId) {
		_stAdjustmentReserveDetail.setUserId(userId);
	}

	/**
	* Sets the workflow approved by of this st adjustment reserve detail.
	*
	* @param workflowApprovedBy the workflow approved by of this st adjustment reserve detail
	*/
	@Override
	public void setWorkflowApprovedBy(java.lang.String workflowApprovedBy) {
		_stAdjustmentReserveDetail.setWorkflowApprovedBy(workflowApprovedBy);
	}

	/**
	* Sets the workflow approved date of this st adjustment reserve detail.
	*
	* @param workflowApprovedDate the workflow approved date of this st adjustment reserve detail
	*/
	@Override
	public void setWorkflowApprovedDate(Date workflowApprovedDate) {
		_stAdjustmentReserveDetail.setWorkflowApprovedDate(workflowApprovedDate);
	}

	/**
	* Sets the workflow created by of this st adjustment reserve detail.
	*
	* @param workflowCreatedBy the workflow created by of this st adjustment reserve detail
	*/
	@Override
	public void setWorkflowCreatedBy(java.lang.String workflowCreatedBy) {
		_stAdjustmentReserveDetail.setWorkflowCreatedBy(workflowCreatedBy);
	}

	/**
	* Sets the workflow created date of this st adjustment reserve detail.
	*
	* @param workflowCreatedDate the workflow created date of this st adjustment reserve detail
	*/
	@Override
	public void setWorkflowCreatedDate(Date workflowCreatedDate) {
		_stAdjustmentReserveDetail.setWorkflowCreatedDate(workflowCreatedDate);
	}

	/**
	* Sets the workflow ID of this st adjustment reserve detail.
	*
	* @param workflowId the workflow ID of this st adjustment reserve detail
	*/
	@Override
	public void setWorkflowId(java.lang.String workflowId) {
		_stAdjustmentReserveDetail.setWorkflowId(workflowId);
	}

	/**
	* Sets the workflow name of this st adjustment reserve detail.
	*
	* @param workflowName the workflow name of this st adjustment reserve detail
	*/
	@Override
	public void setWorkflowName(java.lang.String workflowName) {
		_stAdjustmentReserveDetail.setWorkflowName(workflowName);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<StAdjustmentReserveDetail> toCacheModel() {
		return _stAdjustmentReserveDetail.toCacheModel();
	}

	@Override
	public StAdjustmentReserveDetail toEscapedModel() {
		return new StAdjustmentReserveDetailWrapper(_stAdjustmentReserveDetail.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _stAdjustmentReserveDetail.toString();
	}

	@Override
	public StAdjustmentReserveDetail toUnescapedModel() {
		return new StAdjustmentReserveDetailWrapper(_stAdjustmentReserveDetail.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _stAdjustmentReserveDetail.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof StAdjustmentReserveDetailWrapper)) {
			return false;
		}

		StAdjustmentReserveDetailWrapper stAdjustmentReserveDetailWrapper = (StAdjustmentReserveDetailWrapper)obj;

		if (Objects.equals(_stAdjustmentReserveDetail,
					stAdjustmentReserveDetailWrapper._stAdjustmentReserveDetail)) {
			return true;
		}

		return false;
	}

	@Override
	public StAdjustmentReserveDetail getWrappedModel() {
		return _stAdjustmentReserveDetail;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _stAdjustmentReserveDetail.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _stAdjustmentReserveDetail.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_stAdjustmentReserveDetail.resetOriginalValues();
	}

	private final StAdjustmentReserveDetail _stAdjustmentReserveDetail;
}