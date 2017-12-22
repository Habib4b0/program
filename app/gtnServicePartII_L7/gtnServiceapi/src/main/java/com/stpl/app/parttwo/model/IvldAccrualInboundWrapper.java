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
 * This class is a wrapper for {@link IvldAccrualInbound}.
 * </p>
 *
 * @author
 * @see IvldAccrualInbound
 * @generated
 */
@ProviderType
public class IvldAccrualInboundWrapper implements IvldAccrualInbound,
	ModelWrapper<IvldAccrualInbound> {
	public IvldAccrualInboundWrapper(IvldAccrualInbound ivldAccrualInbound) {
		_ivldAccrualInbound = ivldAccrualInbound;
	}

	@Override
	public Class<?> getModelClass() {
		return IvldAccrualInbound.class;
	}

	@Override
	public String getModelClassName() {
		return IvldAccrualInbound.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("recordCreatedDate", getRecordCreatedDate());
		attributes.put("accountId", getAccountId());
		attributes.put("postingIndicator", getPostingIndicator());
		attributes.put("itemId", getItemId());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("accrualPeriodEndDate", getAccrualPeriodEndDate());
		attributes.put("itemIdentCodeQualifier", getItemIdentCodeQualifier());
		attributes.put("glCompanyMasterSid", getGlCompanyMasterSid());
		attributes.put("salesMasterId", getSalesMasterId());
		attributes.put("createdDate", getCreatedDate());
		attributes.put("createdBy", getCreatedBy());
		attributes.put("source", getSource());
		attributes.put("accrualPeriodStartDate", getAccrualPeriodStartDate());
		attributes.put("addChgDelIndicator", getAddChgDelIndicator());
		attributes.put("subLedgerType", getSubLedgerType());
		attributes.put("programNo", getProgramNo());
		attributes.put("documentType", getDocumentType());
		attributes.put("accrualIntfid", getAccrualIntfid());
		attributes.put("glCompanyName", getGlCompanyName());
		attributes.put("errorCode", getErrorCode());
		attributes.put("intfInsertedDate", getIntfInsertedDate());
		attributes.put("modifiedBy", getModifiedBy());
		attributes.put("categoryId", getCategoryId());
		attributes.put("itemNo", getItemNo());
		attributes.put("reprocessedFlag", getReprocessedFlag());
		attributes.put("compIdentCodeQualifier", getCompIdentCodeQualifier());
		attributes.put("acctIdentCodeQualifier", getAcctIdentCodeQualifier());
		attributes.put("contractId", getContractId());
		attributes.put("accountNo", getAccountNo());
		attributes.put("accrualId", getAccrualId());
		attributes.put("reasonForFailure", getReasonForFailure());
		attributes.put("companyIdString", getCompanyIdString());
		attributes.put("accountName", getAccountName());
		attributes.put("accrualType", getAccrualType());
		attributes.put("postingDate", getPostingDate());
		attributes.put("brandId", getBrandId());
		attributes.put("provisionId", getProvisionId());
		attributes.put("amount", getAmount());
		attributes.put("glDate", getGlDate());
		attributes.put("subLedger", getSubLedger());
		attributes.put("companyCostCenter", getCompanyCostCenter());
		attributes.put("glAccount", getGlAccount());
		attributes.put("companyNo", getCompanyNo());
		attributes.put("batchId", getBatchId());
		attributes.put("programType", getProgramType());
		attributes.put("itemName", getItemName());
		attributes.put("errorField", getErrorField());
		attributes.put("ivldAccrualInboundSid", getIvldAccrualInboundSid());
		attributes.put("contractNo", getContractNo());
		attributes.put("brandName", getBrandName());
		attributes.put("contractName", getContractName());
		attributes.put("checkRecord", getCheckRecord());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String recordCreatedDate = (String)attributes.get("recordCreatedDate");

		if (recordCreatedDate != null) {
			setRecordCreatedDate(recordCreatedDate);
		}

		String accountId = (String)attributes.get("accountId");

		if (accountId != null) {
			setAccountId(accountId);
		}

		String postingIndicator = (String)attributes.get("postingIndicator");

		if (postingIndicator != null) {
			setPostingIndicator(postingIndicator);
		}

		String itemId = (String)attributes.get("itemId");

		if (itemId != null) {
			setItemId(itemId);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String accrualPeriodEndDate = (String)attributes.get(
				"accrualPeriodEndDate");

		if (accrualPeriodEndDate != null) {
			setAccrualPeriodEndDate(accrualPeriodEndDate);
		}

		String itemIdentCodeQualifier = (String)attributes.get(
				"itemIdentCodeQualifier");

		if (itemIdentCodeQualifier != null) {
			setItemIdentCodeQualifier(itemIdentCodeQualifier);
		}

		String glCompanyMasterSid = (String)attributes.get("glCompanyMasterSid");

		if (glCompanyMasterSid != null) {
			setGlCompanyMasterSid(glCompanyMasterSid);
		}

		String salesMasterId = (String)attributes.get("salesMasterId");

		if (salesMasterId != null) {
			setSalesMasterId(salesMasterId);
		}

		Date createdDate = (Date)attributes.get("createdDate");

		if (createdDate != null) {
			setCreatedDate(createdDate);
		}

		String createdBy = (String)attributes.get("createdBy");

		if (createdBy != null) {
			setCreatedBy(createdBy);
		}

		String source = (String)attributes.get("source");

		if (source != null) {
			setSource(source);
		}

		String accrualPeriodStartDate = (String)attributes.get(
				"accrualPeriodStartDate");

		if (accrualPeriodStartDate != null) {
			setAccrualPeriodStartDate(accrualPeriodStartDate);
		}

		String addChgDelIndicator = (String)attributes.get("addChgDelIndicator");

		if (addChgDelIndicator != null) {
			setAddChgDelIndicator(addChgDelIndicator);
		}

		String subLedgerType = (String)attributes.get("subLedgerType");

		if (subLedgerType != null) {
			setSubLedgerType(subLedgerType);
		}

		String programNo = (String)attributes.get("programNo");

		if (programNo != null) {
			setProgramNo(programNo);
		}

		String documentType = (String)attributes.get("documentType");

		if (documentType != null) {
			setDocumentType(documentType);
		}

		String accrualIntfid = (String)attributes.get("accrualIntfid");

		if (accrualIntfid != null) {
			setAccrualIntfid(accrualIntfid);
		}

		String glCompanyName = (String)attributes.get("glCompanyName");

		if (glCompanyName != null) {
			setGlCompanyName(glCompanyName);
		}

		String errorCode = (String)attributes.get("errorCode");

		if (errorCode != null) {
			setErrorCode(errorCode);
		}

		Date intfInsertedDate = (Date)attributes.get("intfInsertedDate");

		if (intfInsertedDate != null) {
			setIntfInsertedDate(intfInsertedDate);
		}

		String modifiedBy = (String)attributes.get("modifiedBy");

		if (modifiedBy != null) {
			setModifiedBy(modifiedBy);
		}

		String categoryId = (String)attributes.get("categoryId");

		if (categoryId != null) {
			setCategoryId(categoryId);
		}

		String itemNo = (String)attributes.get("itemNo");

		if (itemNo != null) {
			setItemNo(itemNo);
		}

		String reprocessedFlag = (String)attributes.get("reprocessedFlag");

		if (reprocessedFlag != null) {
			setReprocessedFlag(reprocessedFlag);
		}

		String compIdentCodeQualifier = (String)attributes.get(
				"compIdentCodeQualifier");

		if (compIdentCodeQualifier != null) {
			setCompIdentCodeQualifier(compIdentCodeQualifier);
		}

		String acctIdentCodeQualifier = (String)attributes.get(
				"acctIdentCodeQualifier");

		if (acctIdentCodeQualifier != null) {
			setAcctIdentCodeQualifier(acctIdentCodeQualifier);
		}

		String contractId = (String)attributes.get("contractId");

		if (contractId != null) {
			setContractId(contractId);
		}

		String accountNo = (String)attributes.get("accountNo");

		if (accountNo != null) {
			setAccountNo(accountNo);
		}

		String accrualId = (String)attributes.get("accrualId");

		if (accrualId != null) {
			setAccrualId(accrualId);
		}

		String reasonForFailure = (String)attributes.get("reasonForFailure");

		if (reasonForFailure != null) {
			setReasonForFailure(reasonForFailure);
		}

		String companyIdString = (String)attributes.get("companyIdString");

		if (companyIdString != null) {
			setCompanyIdString(companyIdString);
		}

		String accountName = (String)attributes.get("accountName");

		if (accountName != null) {
			setAccountName(accountName);
		}

		String accrualType = (String)attributes.get("accrualType");

		if (accrualType != null) {
			setAccrualType(accrualType);
		}

		String postingDate = (String)attributes.get("postingDate");

		if (postingDate != null) {
			setPostingDate(postingDate);
		}

		String brandId = (String)attributes.get("brandId");

		if (brandId != null) {
			setBrandId(brandId);
		}

		String provisionId = (String)attributes.get("provisionId");

		if (provisionId != null) {
			setProvisionId(provisionId);
		}

		String amount = (String)attributes.get("amount");

		if (amount != null) {
			setAmount(amount);
		}

		String glDate = (String)attributes.get("glDate");

		if (glDate != null) {
			setGlDate(glDate);
		}

		String subLedger = (String)attributes.get("subLedger");

		if (subLedger != null) {
			setSubLedger(subLedger);
		}

		String companyCostCenter = (String)attributes.get("companyCostCenter");

		if (companyCostCenter != null) {
			setCompanyCostCenter(companyCostCenter);
		}

		String glAccount = (String)attributes.get("glAccount");

		if (glAccount != null) {
			setGlAccount(glAccount);
		}

		String companyNo = (String)attributes.get("companyNo");

		if (companyNo != null) {
			setCompanyNo(companyNo);
		}

		String batchId = (String)attributes.get("batchId");

		if (batchId != null) {
			setBatchId(batchId);
		}

		String programType = (String)attributes.get("programType");

		if (programType != null) {
			setProgramType(programType);
		}

		String itemName = (String)attributes.get("itemName");

		if (itemName != null) {
			setItemName(itemName);
		}

		String errorField = (String)attributes.get("errorField");

		if (errorField != null) {
			setErrorField(errorField);
		}

		Integer ivldAccrualInboundSid = (Integer)attributes.get(
				"ivldAccrualInboundSid");

		if (ivldAccrualInboundSid != null) {
			setIvldAccrualInboundSid(ivldAccrualInboundSid);
		}

		String contractNo = (String)attributes.get("contractNo");

		if (contractNo != null) {
			setContractNo(contractNo);
		}

		String brandName = (String)attributes.get("brandName");

		if (brandName != null) {
			setBrandName(brandName);
		}

		String contractName = (String)attributes.get("contractName");

		if (contractName != null) {
			setContractName(contractName);
		}

		Boolean checkRecord = (Boolean)attributes.get("checkRecord");

		if (checkRecord != null) {
			setCheckRecord(checkRecord);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new IvldAccrualInboundWrapper((IvldAccrualInbound)_ivldAccrualInbound.clone());
	}

	@Override
	public int compareTo(IvldAccrualInbound ivldAccrualInbound) {
		return _ivldAccrualInbound.compareTo(ivldAccrualInbound);
	}

	/**
	* Returns the account ID of this ivld accrual inbound.
	*
	* @return the account ID of this ivld accrual inbound
	*/
	@Override
	public java.lang.String getAccountId() {
		return _ivldAccrualInbound.getAccountId();
	}

	/**
	* Returns the account name of this ivld accrual inbound.
	*
	* @return the account name of this ivld accrual inbound
	*/
	@Override
	public java.lang.String getAccountName() {
		return _ivldAccrualInbound.getAccountName();
	}

	/**
	* Returns the account no of this ivld accrual inbound.
	*
	* @return the account no of this ivld accrual inbound
	*/
	@Override
	public java.lang.String getAccountNo() {
		return _ivldAccrualInbound.getAccountNo();
	}

	/**
	* Returns the accrual ID of this ivld accrual inbound.
	*
	* @return the accrual ID of this ivld accrual inbound
	*/
	@Override
	public java.lang.String getAccrualId() {
		return _ivldAccrualInbound.getAccrualId();
	}

	/**
	* Returns the accrual intfid of this ivld accrual inbound.
	*
	* @return the accrual intfid of this ivld accrual inbound
	*/
	@Override
	public java.lang.String getAccrualIntfid() {
		return _ivldAccrualInbound.getAccrualIntfid();
	}

	/**
	* Returns the accrual period end date of this ivld accrual inbound.
	*
	* @return the accrual period end date of this ivld accrual inbound
	*/
	@Override
	public java.lang.String getAccrualPeriodEndDate() {
		return _ivldAccrualInbound.getAccrualPeriodEndDate();
	}

	/**
	* Returns the accrual period start date of this ivld accrual inbound.
	*
	* @return the accrual period start date of this ivld accrual inbound
	*/
	@Override
	public java.lang.String getAccrualPeriodStartDate() {
		return _ivldAccrualInbound.getAccrualPeriodStartDate();
	}

	/**
	* Returns the accrual type of this ivld accrual inbound.
	*
	* @return the accrual type of this ivld accrual inbound
	*/
	@Override
	public java.lang.String getAccrualType() {
		return _ivldAccrualInbound.getAccrualType();
	}

	/**
	* Returns the acct ident code qualifier of this ivld accrual inbound.
	*
	* @return the acct ident code qualifier of this ivld accrual inbound
	*/
	@Override
	public java.lang.String getAcctIdentCodeQualifier() {
		return _ivldAccrualInbound.getAcctIdentCodeQualifier();
	}

	/**
	* Returns the add chg del indicator of this ivld accrual inbound.
	*
	* @return the add chg del indicator of this ivld accrual inbound
	*/
	@Override
	public java.lang.String getAddChgDelIndicator() {
		return _ivldAccrualInbound.getAddChgDelIndicator();
	}

	/**
	* Returns the amount of this ivld accrual inbound.
	*
	* @return the amount of this ivld accrual inbound
	*/
	@Override
	public java.lang.String getAmount() {
		return _ivldAccrualInbound.getAmount();
	}

	/**
	* Returns the batch ID of this ivld accrual inbound.
	*
	* @return the batch ID of this ivld accrual inbound
	*/
	@Override
	public java.lang.String getBatchId() {
		return _ivldAccrualInbound.getBatchId();
	}

	/**
	* Returns the brand ID of this ivld accrual inbound.
	*
	* @return the brand ID of this ivld accrual inbound
	*/
	@Override
	public java.lang.String getBrandId() {
		return _ivldAccrualInbound.getBrandId();
	}

	/**
	* Returns the brand name of this ivld accrual inbound.
	*
	* @return the brand name of this ivld accrual inbound
	*/
	@Override
	public java.lang.String getBrandName() {
		return _ivldAccrualInbound.getBrandName();
	}

	/**
	* Returns the category ID of this ivld accrual inbound.
	*
	* @return the category ID of this ivld accrual inbound
	*/
	@Override
	public java.lang.String getCategoryId() {
		return _ivldAccrualInbound.getCategoryId();
	}

	/**
	* Returns the check record of this ivld accrual inbound.
	*
	* @return the check record of this ivld accrual inbound
	*/
	@Override
	public boolean getCheckRecord() {
		return _ivldAccrualInbound.getCheckRecord();
	}

	/**
	* Returns the company cost center of this ivld accrual inbound.
	*
	* @return the company cost center of this ivld accrual inbound
	*/
	@Override
	public java.lang.String getCompanyCostCenter() {
		return _ivldAccrualInbound.getCompanyCostCenter();
	}

	/**
	* Returns the company ID string of this ivld accrual inbound.
	*
	* @return the company ID string of this ivld accrual inbound
	*/
	@Override
	public java.lang.String getCompanyIdString() {
		return _ivldAccrualInbound.getCompanyIdString();
	}

	/**
	* Returns the company no of this ivld accrual inbound.
	*
	* @return the company no of this ivld accrual inbound
	*/
	@Override
	public java.lang.String getCompanyNo() {
		return _ivldAccrualInbound.getCompanyNo();
	}

	/**
	* Returns the comp ident code qualifier of this ivld accrual inbound.
	*
	* @return the comp ident code qualifier of this ivld accrual inbound
	*/
	@Override
	public java.lang.String getCompIdentCodeQualifier() {
		return _ivldAccrualInbound.getCompIdentCodeQualifier();
	}

	/**
	* Returns the contract ID of this ivld accrual inbound.
	*
	* @return the contract ID of this ivld accrual inbound
	*/
	@Override
	public java.lang.String getContractId() {
		return _ivldAccrualInbound.getContractId();
	}

	/**
	* Returns the contract name of this ivld accrual inbound.
	*
	* @return the contract name of this ivld accrual inbound
	*/
	@Override
	public java.lang.String getContractName() {
		return _ivldAccrualInbound.getContractName();
	}

	/**
	* Returns the contract no of this ivld accrual inbound.
	*
	* @return the contract no of this ivld accrual inbound
	*/
	@Override
	public java.lang.String getContractNo() {
		return _ivldAccrualInbound.getContractNo();
	}

	/**
	* Returns the created by of this ivld accrual inbound.
	*
	* @return the created by of this ivld accrual inbound
	*/
	@Override
	public java.lang.String getCreatedBy() {
		return _ivldAccrualInbound.getCreatedBy();
	}

	/**
	* Returns the created date of this ivld accrual inbound.
	*
	* @return the created date of this ivld accrual inbound
	*/
	@Override
	public Date getCreatedDate() {
		return _ivldAccrualInbound.getCreatedDate();
	}

	/**
	* Returns the document type of this ivld accrual inbound.
	*
	* @return the document type of this ivld accrual inbound
	*/
	@Override
	public java.lang.String getDocumentType() {
		return _ivldAccrualInbound.getDocumentType();
	}

	/**
	* Returns the error code of this ivld accrual inbound.
	*
	* @return the error code of this ivld accrual inbound
	*/
	@Override
	public java.lang.String getErrorCode() {
		return _ivldAccrualInbound.getErrorCode();
	}

	/**
	* Returns the error field of this ivld accrual inbound.
	*
	* @return the error field of this ivld accrual inbound
	*/
	@Override
	public java.lang.String getErrorField() {
		return _ivldAccrualInbound.getErrorField();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _ivldAccrualInbound.getExpandoBridge();
	}

	/**
	* Returns the gl account of this ivld accrual inbound.
	*
	* @return the gl account of this ivld accrual inbound
	*/
	@Override
	public java.lang.String getGlAccount() {
		return _ivldAccrualInbound.getGlAccount();
	}

	/**
	* Returns the gl company master sid of this ivld accrual inbound.
	*
	* @return the gl company master sid of this ivld accrual inbound
	*/
	@Override
	public java.lang.String getGlCompanyMasterSid() {
		return _ivldAccrualInbound.getGlCompanyMasterSid();
	}

	/**
	* Returns the gl company name of this ivld accrual inbound.
	*
	* @return the gl company name of this ivld accrual inbound
	*/
	@Override
	public java.lang.String getGlCompanyName() {
		return _ivldAccrualInbound.getGlCompanyName();
	}

	/**
	* Returns the gl date of this ivld accrual inbound.
	*
	* @return the gl date of this ivld accrual inbound
	*/
	@Override
	public java.lang.String getGlDate() {
		return _ivldAccrualInbound.getGlDate();
	}

	/**
	* Returns the intf inserted date of this ivld accrual inbound.
	*
	* @return the intf inserted date of this ivld accrual inbound
	*/
	@Override
	public Date getIntfInsertedDate() {
		return _ivldAccrualInbound.getIntfInsertedDate();
	}

	/**
	* Returns the item ID of this ivld accrual inbound.
	*
	* @return the item ID of this ivld accrual inbound
	*/
	@Override
	public java.lang.String getItemId() {
		return _ivldAccrualInbound.getItemId();
	}

	/**
	* Returns the item ident code qualifier of this ivld accrual inbound.
	*
	* @return the item ident code qualifier of this ivld accrual inbound
	*/
	@Override
	public java.lang.String getItemIdentCodeQualifier() {
		return _ivldAccrualInbound.getItemIdentCodeQualifier();
	}

	/**
	* Returns the item name of this ivld accrual inbound.
	*
	* @return the item name of this ivld accrual inbound
	*/
	@Override
	public java.lang.String getItemName() {
		return _ivldAccrualInbound.getItemName();
	}

	/**
	* Returns the item no of this ivld accrual inbound.
	*
	* @return the item no of this ivld accrual inbound
	*/
	@Override
	public java.lang.String getItemNo() {
		return _ivldAccrualInbound.getItemNo();
	}

	/**
	* Returns the ivld accrual inbound sid of this ivld accrual inbound.
	*
	* @return the ivld accrual inbound sid of this ivld accrual inbound
	*/
	@Override
	public int getIvldAccrualInboundSid() {
		return _ivldAccrualInbound.getIvldAccrualInboundSid();
	}

	/**
	* Returns the modified by of this ivld accrual inbound.
	*
	* @return the modified by of this ivld accrual inbound
	*/
	@Override
	public java.lang.String getModifiedBy() {
		return _ivldAccrualInbound.getModifiedBy();
	}

	/**
	* Returns the modified date of this ivld accrual inbound.
	*
	* @return the modified date of this ivld accrual inbound
	*/
	@Override
	public Date getModifiedDate() {
		return _ivldAccrualInbound.getModifiedDate();
	}

	/**
	* Returns the posting date of this ivld accrual inbound.
	*
	* @return the posting date of this ivld accrual inbound
	*/
	@Override
	public java.lang.String getPostingDate() {
		return _ivldAccrualInbound.getPostingDate();
	}

	/**
	* Returns the posting indicator of this ivld accrual inbound.
	*
	* @return the posting indicator of this ivld accrual inbound
	*/
	@Override
	public java.lang.String getPostingIndicator() {
		return _ivldAccrualInbound.getPostingIndicator();
	}

	/**
	* Returns the primary key of this ivld accrual inbound.
	*
	* @return the primary key of this ivld accrual inbound
	*/
	@Override
	public int getPrimaryKey() {
		return _ivldAccrualInbound.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _ivldAccrualInbound.getPrimaryKeyObj();
	}

	/**
	* Returns the program no of this ivld accrual inbound.
	*
	* @return the program no of this ivld accrual inbound
	*/
	@Override
	public java.lang.String getProgramNo() {
		return _ivldAccrualInbound.getProgramNo();
	}

	/**
	* Returns the program type of this ivld accrual inbound.
	*
	* @return the program type of this ivld accrual inbound
	*/
	@Override
	public java.lang.String getProgramType() {
		return _ivldAccrualInbound.getProgramType();
	}

	/**
	* Returns the provision ID of this ivld accrual inbound.
	*
	* @return the provision ID of this ivld accrual inbound
	*/
	@Override
	public java.lang.String getProvisionId() {
		return _ivldAccrualInbound.getProvisionId();
	}

	/**
	* Returns the reason for failure of this ivld accrual inbound.
	*
	* @return the reason for failure of this ivld accrual inbound
	*/
	@Override
	public java.lang.String getReasonForFailure() {
		return _ivldAccrualInbound.getReasonForFailure();
	}

	/**
	* Returns the record created date of this ivld accrual inbound.
	*
	* @return the record created date of this ivld accrual inbound
	*/
	@Override
	public java.lang.String getRecordCreatedDate() {
		return _ivldAccrualInbound.getRecordCreatedDate();
	}

	/**
	* Returns the reprocessed flag of this ivld accrual inbound.
	*
	* @return the reprocessed flag of this ivld accrual inbound
	*/
	@Override
	public java.lang.String getReprocessedFlag() {
		return _ivldAccrualInbound.getReprocessedFlag();
	}

	/**
	* Returns the sales master ID of this ivld accrual inbound.
	*
	* @return the sales master ID of this ivld accrual inbound
	*/
	@Override
	public java.lang.String getSalesMasterId() {
		return _ivldAccrualInbound.getSalesMasterId();
	}

	/**
	* Returns the source of this ivld accrual inbound.
	*
	* @return the source of this ivld accrual inbound
	*/
	@Override
	public java.lang.String getSource() {
		return _ivldAccrualInbound.getSource();
	}

	/**
	* Returns the sub ledger of this ivld accrual inbound.
	*
	* @return the sub ledger of this ivld accrual inbound
	*/
	@Override
	public java.lang.String getSubLedger() {
		return _ivldAccrualInbound.getSubLedger();
	}

	/**
	* Returns the sub ledger type of this ivld accrual inbound.
	*
	* @return the sub ledger type of this ivld accrual inbound
	*/
	@Override
	public java.lang.String getSubLedgerType() {
		return _ivldAccrualInbound.getSubLedgerType();
	}

	@Override
	public int hashCode() {
		return _ivldAccrualInbound.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _ivldAccrualInbound.isCachedModel();
	}

	/**
	* Returns <code>true</code> if this ivld accrual inbound is check record.
	*
	* @return <code>true</code> if this ivld accrual inbound is check record; <code>false</code> otherwise
	*/
	@Override
	public boolean isCheckRecord() {
		return _ivldAccrualInbound.isCheckRecord();
	}

	@Override
	public boolean isEscapedModel() {
		return _ivldAccrualInbound.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _ivldAccrualInbound.isNew();
	}

	@Override
	public void persist() {
		_ivldAccrualInbound.persist();
	}

	/**
	* Sets the account ID of this ivld accrual inbound.
	*
	* @param accountId the account ID of this ivld accrual inbound
	*/
	@Override
	public void setAccountId(java.lang.String accountId) {
		_ivldAccrualInbound.setAccountId(accountId);
	}

	/**
	* Sets the account name of this ivld accrual inbound.
	*
	* @param accountName the account name of this ivld accrual inbound
	*/
	@Override
	public void setAccountName(java.lang.String accountName) {
		_ivldAccrualInbound.setAccountName(accountName);
	}

	/**
	* Sets the account no of this ivld accrual inbound.
	*
	* @param accountNo the account no of this ivld accrual inbound
	*/
	@Override
	public void setAccountNo(java.lang.String accountNo) {
		_ivldAccrualInbound.setAccountNo(accountNo);
	}

	/**
	* Sets the accrual ID of this ivld accrual inbound.
	*
	* @param accrualId the accrual ID of this ivld accrual inbound
	*/
	@Override
	public void setAccrualId(java.lang.String accrualId) {
		_ivldAccrualInbound.setAccrualId(accrualId);
	}

	/**
	* Sets the accrual intfid of this ivld accrual inbound.
	*
	* @param accrualIntfid the accrual intfid of this ivld accrual inbound
	*/
	@Override
	public void setAccrualIntfid(java.lang.String accrualIntfid) {
		_ivldAccrualInbound.setAccrualIntfid(accrualIntfid);
	}

	/**
	* Sets the accrual period end date of this ivld accrual inbound.
	*
	* @param accrualPeriodEndDate the accrual period end date of this ivld accrual inbound
	*/
	@Override
	public void setAccrualPeriodEndDate(java.lang.String accrualPeriodEndDate) {
		_ivldAccrualInbound.setAccrualPeriodEndDate(accrualPeriodEndDate);
	}

	/**
	* Sets the accrual period start date of this ivld accrual inbound.
	*
	* @param accrualPeriodStartDate the accrual period start date of this ivld accrual inbound
	*/
	@Override
	public void setAccrualPeriodStartDate(
		java.lang.String accrualPeriodStartDate) {
		_ivldAccrualInbound.setAccrualPeriodStartDate(accrualPeriodStartDate);
	}

	/**
	* Sets the accrual type of this ivld accrual inbound.
	*
	* @param accrualType the accrual type of this ivld accrual inbound
	*/
	@Override
	public void setAccrualType(java.lang.String accrualType) {
		_ivldAccrualInbound.setAccrualType(accrualType);
	}

	/**
	* Sets the acct ident code qualifier of this ivld accrual inbound.
	*
	* @param acctIdentCodeQualifier the acct ident code qualifier of this ivld accrual inbound
	*/
	@Override
	public void setAcctIdentCodeQualifier(
		java.lang.String acctIdentCodeQualifier) {
		_ivldAccrualInbound.setAcctIdentCodeQualifier(acctIdentCodeQualifier);
	}

	/**
	* Sets the add chg del indicator of this ivld accrual inbound.
	*
	* @param addChgDelIndicator the add chg del indicator of this ivld accrual inbound
	*/
	@Override
	public void setAddChgDelIndicator(java.lang.String addChgDelIndicator) {
		_ivldAccrualInbound.setAddChgDelIndicator(addChgDelIndicator);
	}

	/**
	* Sets the amount of this ivld accrual inbound.
	*
	* @param amount the amount of this ivld accrual inbound
	*/
	@Override
	public void setAmount(java.lang.String amount) {
		_ivldAccrualInbound.setAmount(amount);
	}

	/**
	* Sets the batch ID of this ivld accrual inbound.
	*
	* @param batchId the batch ID of this ivld accrual inbound
	*/
	@Override
	public void setBatchId(java.lang.String batchId) {
		_ivldAccrualInbound.setBatchId(batchId);
	}

	/**
	* Sets the brand ID of this ivld accrual inbound.
	*
	* @param brandId the brand ID of this ivld accrual inbound
	*/
	@Override
	public void setBrandId(java.lang.String brandId) {
		_ivldAccrualInbound.setBrandId(brandId);
	}

	/**
	* Sets the brand name of this ivld accrual inbound.
	*
	* @param brandName the brand name of this ivld accrual inbound
	*/
	@Override
	public void setBrandName(java.lang.String brandName) {
		_ivldAccrualInbound.setBrandName(brandName);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_ivldAccrualInbound.setCachedModel(cachedModel);
	}

	/**
	* Sets the category ID of this ivld accrual inbound.
	*
	* @param categoryId the category ID of this ivld accrual inbound
	*/
	@Override
	public void setCategoryId(java.lang.String categoryId) {
		_ivldAccrualInbound.setCategoryId(categoryId);
	}

	/**
	* Sets whether this ivld accrual inbound is check record.
	*
	* @param checkRecord the check record of this ivld accrual inbound
	*/
	@Override
	public void setCheckRecord(boolean checkRecord) {
		_ivldAccrualInbound.setCheckRecord(checkRecord);
	}

	/**
	* Sets the company cost center of this ivld accrual inbound.
	*
	* @param companyCostCenter the company cost center of this ivld accrual inbound
	*/
	@Override
	public void setCompanyCostCenter(java.lang.String companyCostCenter) {
		_ivldAccrualInbound.setCompanyCostCenter(companyCostCenter);
	}

	/**
	* Sets the company ID string of this ivld accrual inbound.
	*
	* @param companyIdString the company ID string of this ivld accrual inbound
	*/
	@Override
	public void setCompanyIdString(java.lang.String companyIdString) {
		_ivldAccrualInbound.setCompanyIdString(companyIdString);
	}

	/**
	* Sets the company no of this ivld accrual inbound.
	*
	* @param companyNo the company no of this ivld accrual inbound
	*/
	@Override
	public void setCompanyNo(java.lang.String companyNo) {
		_ivldAccrualInbound.setCompanyNo(companyNo);
	}

	/**
	* Sets the comp ident code qualifier of this ivld accrual inbound.
	*
	* @param compIdentCodeQualifier the comp ident code qualifier of this ivld accrual inbound
	*/
	@Override
	public void setCompIdentCodeQualifier(
		java.lang.String compIdentCodeQualifier) {
		_ivldAccrualInbound.setCompIdentCodeQualifier(compIdentCodeQualifier);
	}

	/**
	* Sets the contract ID of this ivld accrual inbound.
	*
	* @param contractId the contract ID of this ivld accrual inbound
	*/
	@Override
	public void setContractId(java.lang.String contractId) {
		_ivldAccrualInbound.setContractId(contractId);
	}

	/**
	* Sets the contract name of this ivld accrual inbound.
	*
	* @param contractName the contract name of this ivld accrual inbound
	*/
	@Override
	public void setContractName(java.lang.String contractName) {
		_ivldAccrualInbound.setContractName(contractName);
	}

	/**
	* Sets the contract no of this ivld accrual inbound.
	*
	* @param contractNo the contract no of this ivld accrual inbound
	*/
	@Override
	public void setContractNo(java.lang.String contractNo) {
		_ivldAccrualInbound.setContractNo(contractNo);
	}

	/**
	* Sets the created by of this ivld accrual inbound.
	*
	* @param createdBy the created by of this ivld accrual inbound
	*/
	@Override
	public void setCreatedBy(java.lang.String createdBy) {
		_ivldAccrualInbound.setCreatedBy(createdBy);
	}

	/**
	* Sets the created date of this ivld accrual inbound.
	*
	* @param createdDate the created date of this ivld accrual inbound
	*/
	@Override
	public void setCreatedDate(Date createdDate) {
		_ivldAccrualInbound.setCreatedDate(createdDate);
	}

	/**
	* Sets the document type of this ivld accrual inbound.
	*
	* @param documentType the document type of this ivld accrual inbound
	*/
	@Override
	public void setDocumentType(java.lang.String documentType) {
		_ivldAccrualInbound.setDocumentType(documentType);
	}

	/**
	* Sets the error code of this ivld accrual inbound.
	*
	* @param errorCode the error code of this ivld accrual inbound
	*/
	@Override
	public void setErrorCode(java.lang.String errorCode) {
		_ivldAccrualInbound.setErrorCode(errorCode);
	}

	/**
	* Sets the error field of this ivld accrual inbound.
	*
	* @param errorField the error field of this ivld accrual inbound
	*/
	@Override
	public void setErrorField(java.lang.String errorField) {
		_ivldAccrualInbound.setErrorField(errorField);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_ivldAccrualInbound.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_ivldAccrualInbound.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_ivldAccrualInbound.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the gl account of this ivld accrual inbound.
	*
	* @param glAccount the gl account of this ivld accrual inbound
	*/
	@Override
	public void setGlAccount(java.lang.String glAccount) {
		_ivldAccrualInbound.setGlAccount(glAccount);
	}

	/**
	* Sets the gl company master sid of this ivld accrual inbound.
	*
	* @param glCompanyMasterSid the gl company master sid of this ivld accrual inbound
	*/
	@Override
	public void setGlCompanyMasterSid(java.lang.String glCompanyMasterSid) {
		_ivldAccrualInbound.setGlCompanyMasterSid(glCompanyMasterSid);
	}

	/**
	* Sets the gl company name of this ivld accrual inbound.
	*
	* @param glCompanyName the gl company name of this ivld accrual inbound
	*/
	@Override
	public void setGlCompanyName(java.lang.String glCompanyName) {
		_ivldAccrualInbound.setGlCompanyName(glCompanyName);
	}

	/**
	* Sets the gl date of this ivld accrual inbound.
	*
	* @param glDate the gl date of this ivld accrual inbound
	*/
	@Override
	public void setGlDate(java.lang.String glDate) {
		_ivldAccrualInbound.setGlDate(glDate);
	}

	/**
	* Sets the intf inserted date of this ivld accrual inbound.
	*
	* @param intfInsertedDate the intf inserted date of this ivld accrual inbound
	*/
	@Override
	public void setIntfInsertedDate(Date intfInsertedDate) {
		_ivldAccrualInbound.setIntfInsertedDate(intfInsertedDate);
	}

	/**
	* Sets the item ID of this ivld accrual inbound.
	*
	* @param itemId the item ID of this ivld accrual inbound
	*/
	@Override
	public void setItemId(java.lang.String itemId) {
		_ivldAccrualInbound.setItemId(itemId);
	}

	/**
	* Sets the item ident code qualifier of this ivld accrual inbound.
	*
	* @param itemIdentCodeQualifier the item ident code qualifier of this ivld accrual inbound
	*/
	@Override
	public void setItemIdentCodeQualifier(
		java.lang.String itemIdentCodeQualifier) {
		_ivldAccrualInbound.setItemIdentCodeQualifier(itemIdentCodeQualifier);
	}

	/**
	* Sets the item name of this ivld accrual inbound.
	*
	* @param itemName the item name of this ivld accrual inbound
	*/
	@Override
	public void setItemName(java.lang.String itemName) {
		_ivldAccrualInbound.setItemName(itemName);
	}

	/**
	* Sets the item no of this ivld accrual inbound.
	*
	* @param itemNo the item no of this ivld accrual inbound
	*/
	@Override
	public void setItemNo(java.lang.String itemNo) {
		_ivldAccrualInbound.setItemNo(itemNo);
	}

	/**
	* Sets the ivld accrual inbound sid of this ivld accrual inbound.
	*
	* @param ivldAccrualInboundSid the ivld accrual inbound sid of this ivld accrual inbound
	*/
	@Override
	public void setIvldAccrualInboundSid(int ivldAccrualInboundSid) {
		_ivldAccrualInbound.setIvldAccrualInboundSid(ivldAccrualInboundSid);
	}

	/**
	* Sets the modified by of this ivld accrual inbound.
	*
	* @param modifiedBy the modified by of this ivld accrual inbound
	*/
	@Override
	public void setModifiedBy(java.lang.String modifiedBy) {
		_ivldAccrualInbound.setModifiedBy(modifiedBy);
	}

	/**
	* Sets the modified date of this ivld accrual inbound.
	*
	* @param modifiedDate the modified date of this ivld accrual inbound
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_ivldAccrualInbound.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_ivldAccrualInbound.setNew(n);
	}

	/**
	* Sets the posting date of this ivld accrual inbound.
	*
	* @param postingDate the posting date of this ivld accrual inbound
	*/
	@Override
	public void setPostingDate(java.lang.String postingDate) {
		_ivldAccrualInbound.setPostingDate(postingDate);
	}

	/**
	* Sets the posting indicator of this ivld accrual inbound.
	*
	* @param postingIndicator the posting indicator of this ivld accrual inbound
	*/
	@Override
	public void setPostingIndicator(java.lang.String postingIndicator) {
		_ivldAccrualInbound.setPostingIndicator(postingIndicator);
	}

	/**
	* Sets the primary key of this ivld accrual inbound.
	*
	* @param primaryKey the primary key of this ivld accrual inbound
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_ivldAccrualInbound.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_ivldAccrualInbound.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the program no of this ivld accrual inbound.
	*
	* @param programNo the program no of this ivld accrual inbound
	*/
	@Override
	public void setProgramNo(java.lang.String programNo) {
		_ivldAccrualInbound.setProgramNo(programNo);
	}

	/**
	* Sets the program type of this ivld accrual inbound.
	*
	* @param programType the program type of this ivld accrual inbound
	*/
	@Override
	public void setProgramType(java.lang.String programType) {
		_ivldAccrualInbound.setProgramType(programType);
	}

	/**
	* Sets the provision ID of this ivld accrual inbound.
	*
	* @param provisionId the provision ID of this ivld accrual inbound
	*/
	@Override
	public void setProvisionId(java.lang.String provisionId) {
		_ivldAccrualInbound.setProvisionId(provisionId);
	}

	/**
	* Sets the reason for failure of this ivld accrual inbound.
	*
	* @param reasonForFailure the reason for failure of this ivld accrual inbound
	*/
	@Override
	public void setReasonForFailure(java.lang.String reasonForFailure) {
		_ivldAccrualInbound.setReasonForFailure(reasonForFailure);
	}

	/**
	* Sets the record created date of this ivld accrual inbound.
	*
	* @param recordCreatedDate the record created date of this ivld accrual inbound
	*/
	@Override
	public void setRecordCreatedDate(java.lang.String recordCreatedDate) {
		_ivldAccrualInbound.setRecordCreatedDate(recordCreatedDate);
	}

	/**
	* Sets the reprocessed flag of this ivld accrual inbound.
	*
	* @param reprocessedFlag the reprocessed flag of this ivld accrual inbound
	*/
	@Override
	public void setReprocessedFlag(java.lang.String reprocessedFlag) {
		_ivldAccrualInbound.setReprocessedFlag(reprocessedFlag);
	}

	/**
	* Sets the sales master ID of this ivld accrual inbound.
	*
	* @param salesMasterId the sales master ID of this ivld accrual inbound
	*/
	@Override
	public void setSalesMasterId(java.lang.String salesMasterId) {
		_ivldAccrualInbound.setSalesMasterId(salesMasterId);
	}

	/**
	* Sets the source of this ivld accrual inbound.
	*
	* @param source the source of this ivld accrual inbound
	*/
	@Override
	public void setSource(java.lang.String source) {
		_ivldAccrualInbound.setSource(source);
	}

	/**
	* Sets the sub ledger of this ivld accrual inbound.
	*
	* @param subLedger the sub ledger of this ivld accrual inbound
	*/
	@Override
	public void setSubLedger(java.lang.String subLedger) {
		_ivldAccrualInbound.setSubLedger(subLedger);
	}

	/**
	* Sets the sub ledger type of this ivld accrual inbound.
	*
	* @param subLedgerType the sub ledger type of this ivld accrual inbound
	*/
	@Override
	public void setSubLedgerType(java.lang.String subLedgerType) {
		_ivldAccrualInbound.setSubLedgerType(subLedgerType);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<IvldAccrualInbound> toCacheModel() {
		return _ivldAccrualInbound.toCacheModel();
	}

	@Override
	public IvldAccrualInbound toEscapedModel() {
		return new IvldAccrualInboundWrapper(_ivldAccrualInbound.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _ivldAccrualInbound.toString();
	}

	@Override
	public IvldAccrualInbound toUnescapedModel() {
		return new IvldAccrualInboundWrapper(_ivldAccrualInbound.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _ivldAccrualInbound.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof IvldAccrualInboundWrapper)) {
			return false;
		}

		IvldAccrualInboundWrapper ivldAccrualInboundWrapper = (IvldAccrualInboundWrapper)obj;

		if (Objects.equals(_ivldAccrualInbound,
					ivldAccrualInboundWrapper._ivldAccrualInbound)) {
			return true;
		}

		return false;
	}

	@Override
	public IvldAccrualInbound getWrappedModel() {
		return _ivldAccrualInbound;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _ivldAccrualInbound.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _ivldAccrualInbound.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_ivldAccrualInbound.resetOriginalValues();
	}

	private final IvldAccrualInbound _ivldAccrualInbound;
}