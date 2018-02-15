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
 * This class is a wrapper for {@link AccrualDetails}.
 * </p>
 *
 * @author
 * @see AccrualDetails
 * @generated
 */
@ProviderType
public class AccrualDetailsWrapper implements AccrualDetails,
	ModelWrapper<AccrualDetails> {
	public AccrualDetailsWrapper(AccrualDetails accrualDetails) {
		_accrualDetails = accrualDetails;
	}

	@Override
	public Class<?> getModelClass() {
		return AccrualDetails.class;
	}

	@Override
	public String getModelClassName() {
		return AccrualDetails.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("accountId", getAccountId());
		attributes.put("recordCreatedDate", getRecordCreatedDate());
		attributes.put("postingIndicator", getPostingIndicator());
		attributes.put("brandName", getBrandName());
		attributes.put("accrualPeriodEndDate", getAccrualPeriodEndDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("salesMasterId", getSalesMasterId());
		attributes.put("source", getSource());
		attributes.put("contractMasterSid", getContractMasterSid());
		attributes.put("accrualDetailsSid", getAccrualDetailsSid());
		attributes.put("documentType", getDocumentType());
		attributes.put("inboundStatus", getInboundStatus());
		attributes.put("modifiedBy", getModifiedBy());
		attributes.put("deductionType", getDeductionType());
		attributes.put("companyMasterSid", getCompanyMasterSid());
		attributes.put("contractName", getContractName());
		attributes.put("accountNo", getAccountNo());
		attributes.put("accountName", getAccountName());
		attributes.put("versionNo", getVersionNo());
		attributes.put("provisionId", getProvisionId());
		attributes.put("companyStringIdentifierCodeQualifier",
			getCompanyStringIdentifierCodeQualifier());
		attributes.put("amount", getAmount());
		attributes.put("subLedger", getSubLedger());
		attributes.put("recordLockStatus", getRecordLockStatus());
		attributes.put("itemIdentifierCodeQualifier",
			getItemIdentifierCodeQualifier());
		attributes.put("companyNo", getCompanyNo());
		attributes.put("postingStatus", getPostingStatus());
		attributes.put("itemName", getItemName());
		attributes.put("rsModelSid", getRsModelSid());
		attributes.put("itemMasterSid", getItemMasterSid());
		attributes.put("itemId", getItemId());
		attributes.put("brandMasterSid", getBrandMasterSid());
		attributes.put("glCompanyMasterSid", getGlCompanyMasterSid());
		attributes.put("createdBy", getCreatedBy());
		attributes.put("createdDate", getCreatedDate());
		attributes.put("accrualPeriodStartDate", getAccrualPeriodStartDate());
		attributes.put("subLedgerType", getSubLedgerType());
		attributes.put("programNo", getProgramNo());
		attributes.put("categoryId", getCategoryId());
		attributes.put("itemNo", getItemNo());
		attributes.put("deductionSubType", getDeductionSubType());
		attributes.put("acctIdentifierCodeQualifier",
			getAcctIdentifierCodeQualifier());
		attributes.put("contractId", getContractId());
		attributes.put("accrualId", getAccrualId());
		attributes.put("companyStringId", getCompanyStringId());
		attributes.put("accrualType", getAccrualType());
		attributes.put("brandId", getBrandId());
		attributes.put("postingDate", getPostingDate());
		attributes.put("glDate", getGlDate());
		attributes.put("companyCostCenter", getCompanyCostCenter());
		attributes.put("glAccount", getGlAccount());
		attributes.put("batchId", getBatchId());
		attributes.put("programType", getProgramType());
		attributes.put("contractNo", getContractNo());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String accountId = (String)attributes.get("accountId");

		if (accountId != null) {
			setAccountId(accountId);
		}

		Date recordCreatedDate = (Date)attributes.get("recordCreatedDate");

		if (recordCreatedDate != null) {
			setRecordCreatedDate(recordCreatedDate);
		}

		String postingIndicator = (String)attributes.get("postingIndicator");

		if (postingIndicator != null) {
			setPostingIndicator(postingIndicator);
		}

		String brandName = (String)attributes.get("brandName");

		if (brandName != null) {
			setBrandName(brandName);
		}

		Date accrualPeriodEndDate = (Date)attributes.get("accrualPeriodEndDate");

		if (accrualPeriodEndDate != null) {
			setAccrualPeriodEndDate(accrualPeriodEndDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String salesMasterId = (String)attributes.get("salesMasterId");

		if (salesMasterId != null) {
			setSalesMasterId(salesMasterId);
		}

		String source = (String)attributes.get("source");

		if (source != null) {
			setSource(source);
		}

		Integer contractMasterSid = (Integer)attributes.get("contractMasterSid");

		if (contractMasterSid != null) {
			setContractMasterSid(contractMasterSid);
		}

		Integer accrualDetailsSid = (Integer)attributes.get("accrualDetailsSid");

		if (accrualDetailsSid != null) {
			setAccrualDetailsSid(accrualDetailsSid);
		}

		String documentType = (String)attributes.get("documentType");

		if (documentType != null) {
			setDocumentType(documentType);
		}

		String inboundStatus = (String)attributes.get("inboundStatus");

		if (inboundStatus != null) {
			setInboundStatus(inboundStatus);
		}

		Integer modifiedBy = (Integer)attributes.get("modifiedBy");

		if (modifiedBy != null) {
			setModifiedBy(modifiedBy);
		}

		String deductionType = (String)attributes.get("deductionType");

		if (deductionType != null) {
			setDeductionType(deductionType);
		}

		Integer companyMasterSid = (Integer)attributes.get("companyMasterSid");

		if (companyMasterSid != null) {
			setCompanyMasterSid(companyMasterSid);
		}

		String contractName = (String)attributes.get("contractName");

		if (contractName != null) {
			setContractName(contractName);
		}

		String accountNo = (String)attributes.get("accountNo");

		if (accountNo != null) {
			setAccountNo(accountNo);
		}

		String accountName = (String)attributes.get("accountName");

		if (accountName != null) {
			setAccountName(accountName);
		}

		Integer versionNo = (Integer)attributes.get("versionNo");

		if (versionNo != null) {
			setVersionNo(versionNo);
		}

		String provisionId = (String)attributes.get("provisionId");

		if (provisionId != null) {
			setProvisionId(provisionId);
		}

		String companyStringIdentifierCodeQualifier = (String)attributes.get(
				"companyStringIdentifierCodeQualifier");

		if (companyStringIdentifierCodeQualifier != null) {
			setCompanyStringIdentifierCodeQualifier(companyStringIdentifierCodeQualifier);
		}

		Double amount = (Double)attributes.get("amount");

		if (amount != null) {
			setAmount(amount);
		}

		String subLedger = (String)attributes.get("subLedger");

		if (subLedger != null) {
			setSubLedger(subLedger);
		}

		Boolean recordLockStatus = (Boolean)attributes.get("recordLockStatus");

		if (recordLockStatus != null) {
			setRecordLockStatus(recordLockStatus);
		}

		String itemIdentifierCodeQualifier = (String)attributes.get(
				"itemIdentifierCodeQualifier");

		if (itemIdentifierCodeQualifier != null) {
			setItemIdentifierCodeQualifier(itemIdentifierCodeQualifier);
		}

		String companyNo = (String)attributes.get("companyNo");

		if (companyNo != null) {
			setCompanyNo(companyNo);
		}

		String postingStatus = (String)attributes.get("postingStatus");

		if (postingStatus != null) {
			setPostingStatus(postingStatus);
		}

		String itemName = (String)attributes.get("itemName");

		if (itemName != null) {
			setItemName(itemName);
		}

		Integer rsModelSid = (Integer)attributes.get("rsModelSid");

		if (rsModelSid != null) {
			setRsModelSid(rsModelSid);
		}

		Integer itemMasterSid = (Integer)attributes.get("itemMasterSid");

		if (itemMasterSid != null) {
			setItemMasterSid(itemMasterSid);
		}

		String itemId = (String)attributes.get("itemId");

		if (itemId != null) {
			setItemId(itemId);
		}

		Integer brandMasterSid = (Integer)attributes.get("brandMasterSid");

		if (brandMasterSid != null) {
			setBrandMasterSid(brandMasterSid);
		}

		String glCompanyMasterSid = (String)attributes.get("glCompanyMasterSid");

		if (glCompanyMasterSid != null) {
			setGlCompanyMasterSid(glCompanyMasterSid);
		}

		Integer createdBy = (Integer)attributes.get("createdBy");

		if (createdBy != null) {
			setCreatedBy(createdBy);
		}

		Date createdDate = (Date)attributes.get("createdDate");

		if (createdDate != null) {
			setCreatedDate(createdDate);
		}

		Date accrualPeriodStartDate = (Date)attributes.get(
				"accrualPeriodStartDate");

		if (accrualPeriodStartDate != null) {
			setAccrualPeriodStartDate(accrualPeriodStartDate);
		}

		String subLedgerType = (String)attributes.get("subLedgerType");

		if (subLedgerType != null) {
			setSubLedgerType(subLedgerType);
		}

		String programNo = (String)attributes.get("programNo");

		if (programNo != null) {
			setProgramNo(programNo);
		}

		String categoryId = (String)attributes.get("categoryId");

		if (categoryId != null) {
			setCategoryId(categoryId);
		}

		String itemNo = (String)attributes.get("itemNo");

		if (itemNo != null) {
			setItemNo(itemNo);
		}

		String deductionSubType = (String)attributes.get("deductionSubType");

		if (deductionSubType != null) {
			setDeductionSubType(deductionSubType);
		}

		String acctIdentifierCodeQualifier = (String)attributes.get(
				"acctIdentifierCodeQualifier");

		if (acctIdentifierCodeQualifier != null) {
			setAcctIdentifierCodeQualifier(acctIdentifierCodeQualifier);
		}

		String contractId = (String)attributes.get("contractId");

		if (contractId != null) {
			setContractId(contractId);
		}

		String accrualId = (String)attributes.get("accrualId");

		if (accrualId != null) {
			setAccrualId(accrualId);
		}

		String companyStringId = (String)attributes.get("companyStringId");

		if (companyStringId != null) {
			setCompanyStringId(companyStringId);
		}

		String accrualType = (String)attributes.get("accrualType");

		if (accrualType != null) {
			setAccrualType(accrualType);
		}

		String brandId = (String)attributes.get("brandId");

		if (brandId != null) {
			setBrandId(brandId);
		}

		Date postingDate = (Date)attributes.get("postingDate");

		if (postingDate != null) {
			setPostingDate(postingDate);
		}

		Date glDate = (Date)attributes.get("glDate");

		if (glDate != null) {
			setGlDate(glDate);
		}

		String companyCostCenter = (String)attributes.get("companyCostCenter");

		if (companyCostCenter != null) {
			setCompanyCostCenter(companyCostCenter);
		}

		String glAccount = (String)attributes.get("glAccount");

		if (glAccount != null) {
			setGlAccount(glAccount);
		}

		String batchId = (String)attributes.get("batchId");

		if (batchId != null) {
			setBatchId(batchId);
		}

		Integer programType = (Integer)attributes.get("programType");

		if (programType != null) {
			setProgramType(programType);
		}

		String contractNo = (String)attributes.get("contractNo");

		if (contractNo != null) {
			setContractNo(contractNo);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new AccrualDetailsWrapper((AccrualDetails)_accrualDetails.clone());
	}

	@Override
	public int compareTo(AccrualDetails accrualDetails) {
		return _accrualDetails.compareTo(accrualDetails);
	}

	/**
	* Returns the account ID of this accrual details.
	*
	* @return the account ID of this accrual details
	*/
	@Override
	public java.lang.String getAccountId() {
		return _accrualDetails.getAccountId();
	}

	/**
	* Returns the account name of this accrual details.
	*
	* @return the account name of this accrual details
	*/
	@Override
	public java.lang.String getAccountName() {
		return _accrualDetails.getAccountName();
	}

	/**
	* Returns the account no of this accrual details.
	*
	* @return the account no of this accrual details
	*/
	@Override
	public java.lang.String getAccountNo() {
		return _accrualDetails.getAccountNo();
	}

	/**
	* Returns the accrual details sid of this accrual details.
	*
	* @return the accrual details sid of this accrual details
	*/
	@Override
	public int getAccrualDetailsSid() {
		return _accrualDetails.getAccrualDetailsSid();
	}

	/**
	* Returns the accrual ID of this accrual details.
	*
	* @return the accrual ID of this accrual details
	*/
	@Override
	public java.lang.String getAccrualId() {
		return _accrualDetails.getAccrualId();
	}

	/**
	* Returns the accrual period end date of this accrual details.
	*
	* @return the accrual period end date of this accrual details
	*/
	@Override
	public Date getAccrualPeriodEndDate() {
		return _accrualDetails.getAccrualPeriodEndDate();
	}

	/**
	* Returns the accrual period start date of this accrual details.
	*
	* @return the accrual period start date of this accrual details
	*/
	@Override
	public Date getAccrualPeriodStartDate() {
		return _accrualDetails.getAccrualPeriodStartDate();
	}

	/**
	* Returns the accrual type of this accrual details.
	*
	* @return the accrual type of this accrual details
	*/
	@Override
	public java.lang.String getAccrualType() {
		return _accrualDetails.getAccrualType();
	}

	/**
	* Returns the acct identifier code qualifier of this accrual details.
	*
	* @return the acct identifier code qualifier of this accrual details
	*/
	@Override
	public java.lang.String getAcctIdentifierCodeQualifier() {
		return _accrualDetails.getAcctIdentifierCodeQualifier();
	}

	/**
	* Returns the amount of this accrual details.
	*
	* @return the amount of this accrual details
	*/
	@Override
	public double getAmount() {
		return _accrualDetails.getAmount();
	}

	/**
	* Returns the batch ID of this accrual details.
	*
	* @return the batch ID of this accrual details
	*/
	@Override
	public java.lang.String getBatchId() {
		return _accrualDetails.getBatchId();
	}

	/**
	* Returns the brand ID of this accrual details.
	*
	* @return the brand ID of this accrual details
	*/
	@Override
	public java.lang.String getBrandId() {
		return _accrualDetails.getBrandId();
	}

	/**
	* Returns the brand master sid of this accrual details.
	*
	* @return the brand master sid of this accrual details
	*/
	@Override
	public int getBrandMasterSid() {
		return _accrualDetails.getBrandMasterSid();
	}

	/**
	* Returns the brand name of this accrual details.
	*
	* @return the brand name of this accrual details
	*/
	@Override
	public java.lang.String getBrandName() {
		return _accrualDetails.getBrandName();
	}

	/**
	* Returns the category ID of this accrual details.
	*
	* @return the category ID of this accrual details
	*/
	@Override
	public java.lang.String getCategoryId() {
		return _accrualDetails.getCategoryId();
	}

	/**
	* Returns the company cost center of this accrual details.
	*
	* @return the company cost center of this accrual details
	*/
	@Override
	public java.lang.String getCompanyCostCenter() {
		return _accrualDetails.getCompanyCostCenter();
	}

	/**
	* Returns the company master sid of this accrual details.
	*
	* @return the company master sid of this accrual details
	*/
	@Override
	public int getCompanyMasterSid() {
		return _accrualDetails.getCompanyMasterSid();
	}

	/**
	* Returns the company no of this accrual details.
	*
	* @return the company no of this accrual details
	*/
	@Override
	public java.lang.String getCompanyNo() {
		return _accrualDetails.getCompanyNo();
	}

	/**
	* Returns the company string ID of this accrual details.
	*
	* @return the company string ID of this accrual details
	*/
	@Override
	public java.lang.String getCompanyStringId() {
		return _accrualDetails.getCompanyStringId();
	}

	/**
	* Returns the company string identifier code qualifier of this accrual details.
	*
	* @return the company string identifier code qualifier of this accrual details
	*/
	@Override
	public java.lang.String getCompanyStringIdentifierCodeQualifier() {
		return _accrualDetails.getCompanyStringIdentifierCodeQualifier();
	}

	/**
	* Returns the contract ID of this accrual details.
	*
	* @return the contract ID of this accrual details
	*/
	@Override
	public java.lang.String getContractId() {
		return _accrualDetails.getContractId();
	}

	/**
	* Returns the contract master sid of this accrual details.
	*
	* @return the contract master sid of this accrual details
	*/
	@Override
	public int getContractMasterSid() {
		return _accrualDetails.getContractMasterSid();
	}

	/**
	* Returns the contract name of this accrual details.
	*
	* @return the contract name of this accrual details
	*/
	@Override
	public java.lang.String getContractName() {
		return _accrualDetails.getContractName();
	}

	/**
	* Returns the contract no of this accrual details.
	*
	* @return the contract no of this accrual details
	*/
	@Override
	public java.lang.String getContractNo() {
		return _accrualDetails.getContractNo();
	}

	/**
	* Returns the created by of this accrual details.
	*
	* @return the created by of this accrual details
	*/
	@Override
	public int getCreatedBy() {
		return _accrualDetails.getCreatedBy();
	}

	/**
	* Returns the created date of this accrual details.
	*
	* @return the created date of this accrual details
	*/
	@Override
	public Date getCreatedDate() {
		return _accrualDetails.getCreatedDate();
	}

	/**
	* Returns the deduction sub type of this accrual details.
	*
	* @return the deduction sub type of this accrual details
	*/
	@Override
	public java.lang.String getDeductionSubType() {
		return _accrualDetails.getDeductionSubType();
	}

	/**
	* Returns the deduction type of this accrual details.
	*
	* @return the deduction type of this accrual details
	*/
	@Override
	public java.lang.String getDeductionType() {
		return _accrualDetails.getDeductionType();
	}

	/**
	* Returns the document type of this accrual details.
	*
	* @return the document type of this accrual details
	*/
	@Override
	public java.lang.String getDocumentType() {
		return _accrualDetails.getDocumentType();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _accrualDetails.getExpandoBridge();
	}

	/**
	* Returns the gl account of this accrual details.
	*
	* @return the gl account of this accrual details
	*/
	@Override
	public java.lang.String getGlAccount() {
		return _accrualDetails.getGlAccount();
	}

	/**
	* Returns the gl company master sid of this accrual details.
	*
	* @return the gl company master sid of this accrual details
	*/
	@Override
	public java.lang.String getGlCompanyMasterSid() {
		return _accrualDetails.getGlCompanyMasterSid();
	}

	/**
	* Returns the gl date of this accrual details.
	*
	* @return the gl date of this accrual details
	*/
	@Override
	public Date getGlDate() {
		return _accrualDetails.getGlDate();
	}

	/**
	* Returns the inbound status of this accrual details.
	*
	* @return the inbound status of this accrual details
	*/
	@Override
	public java.lang.String getInboundStatus() {
		return _accrualDetails.getInboundStatus();
	}

	/**
	* Returns the item ID of this accrual details.
	*
	* @return the item ID of this accrual details
	*/
	@Override
	public java.lang.String getItemId() {
		return _accrualDetails.getItemId();
	}

	/**
	* Returns the item identifier code qualifier of this accrual details.
	*
	* @return the item identifier code qualifier of this accrual details
	*/
	@Override
	public java.lang.String getItemIdentifierCodeQualifier() {
		return _accrualDetails.getItemIdentifierCodeQualifier();
	}

	/**
	* Returns the item master sid of this accrual details.
	*
	* @return the item master sid of this accrual details
	*/
	@Override
	public int getItemMasterSid() {
		return _accrualDetails.getItemMasterSid();
	}

	/**
	* Returns the item name of this accrual details.
	*
	* @return the item name of this accrual details
	*/
	@Override
	public java.lang.String getItemName() {
		return _accrualDetails.getItemName();
	}

	/**
	* Returns the item no of this accrual details.
	*
	* @return the item no of this accrual details
	*/
	@Override
	public java.lang.String getItemNo() {
		return _accrualDetails.getItemNo();
	}

	/**
	* Returns the modified by of this accrual details.
	*
	* @return the modified by of this accrual details
	*/
	@Override
	public int getModifiedBy() {
		return _accrualDetails.getModifiedBy();
	}

	/**
	* Returns the modified date of this accrual details.
	*
	* @return the modified date of this accrual details
	*/
	@Override
	public Date getModifiedDate() {
		return _accrualDetails.getModifiedDate();
	}

	/**
	* Returns the posting date of this accrual details.
	*
	* @return the posting date of this accrual details
	*/
	@Override
	public Date getPostingDate() {
		return _accrualDetails.getPostingDate();
	}

	/**
	* Returns the posting indicator of this accrual details.
	*
	* @return the posting indicator of this accrual details
	*/
	@Override
	public java.lang.String getPostingIndicator() {
		return _accrualDetails.getPostingIndicator();
	}

	/**
	* Returns the posting status of this accrual details.
	*
	* @return the posting status of this accrual details
	*/
	@Override
	public java.lang.String getPostingStatus() {
		return _accrualDetails.getPostingStatus();
	}

	/**
	* Returns the primary key of this accrual details.
	*
	* @return the primary key of this accrual details
	*/
	@Override
	public int getPrimaryKey() {
		return _accrualDetails.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _accrualDetails.getPrimaryKeyObj();
	}

	/**
	* Returns the program no of this accrual details.
	*
	* @return the program no of this accrual details
	*/
	@Override
	public java.lang.String getProgramNo() {
		return _accrualDetails.getProgramNo();
	}

	/**
	* Returns the program type of this accrual details.
	*
	* @return the program type of this accrual details
	*/
	@Override
	public int getProgramType() {
		return _accrualDetails.getProgramType();
	}

	/**
	* Returns the provision ID of this accrual details.
	*
	* @return the provision ID of this accrual details
	*/
	@Override
	public java.lang.String getProvisionId() {
		return _accrualDetails.getProvisionId();
	}

	/**
	* Returns the record created date of this accrual details.
	*
	* @return the record created date of this accrual details
	*/
	@Override
	public Date getRecordCreatedDate() {
		return _accrualDetails.getRecordCreatedDate();
	}

	/**
	* Returns the record lock status of this accrual details.
	*
	* @return the record lock status of this accrual details
	*/
	@Override
	public boolean getRecordLockStatus() {
		return _accrualDetails.getRecordLockStatus();
	}

	/**
	* Returns the rs model sid of this accrual details.
	*
	* @return the rs model sid of this accrual details
	*/
	@Override
	public int getRsModelSid() {
		return _accrualDetails.getRsModelSid();
	}

	/**
	* Returns the sales master ID of this accrual details.
	*
	* @return the sales master ID of this accrual details
	*/
	@Override
	public java.lang.String getSalesMasterId() {
		return _accrualDetails.getSalesMasterId();
	}

	/**
	* Returns the source of this accrual details.
	*
	* @return the source of this accrual details
	*/
	@Override
	public java.lang.String getSource() {
		return _accrualDetails.getSource();
	}

	/**
	* Returns the sub ledger of this accrual details.
	*
	* @return the sub ledger of this accrual details
	*/
	@Override
	public java.lang.String getSubLedger() {
		return _accrualDetails.getSubLedger();
	}

	/**
	* Returns the sub ledger type of this accrual details.
	*
	* @return the sub ledger type of this accrual details
	*/
	@Override
	public java.lang.String getSubLedgerType() {
		return _accrualDetails.getSubLedgerType();
	}

	/**
	* Returns the version no of this accrual details.
	*
	* @return the version no of this accrual details
	*/
	@Override
	public int getVersionNo() {
		return _accrualDetails.getVersionNo();
	}

	@Override
	public int hashCode() {
		return _accrualDetails.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _accrualDetails.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _accrualDetails.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _accrualDetails.isNew();
	}

	/**
	* Returns <code>true</code> if this accrual details is record lock status.
	*
	* @return <code>true</code> if this accrual details is record lock status; <code>false</code> otherwise
	*/
	@Override
	public boolean isRecordLockStatus() {
		return _accrualDetails.isRecordLockStatus();
	}

	@Override
	public void persist() {
		_accrualDetails.persist();
	}

	/**
	* Sets the account ID of this accrual details.
	*
	* @param accountId the account ID of this accrual details
	*/
	@Override
	public void setAccountId(java.lang.String accountId) {
		_accrualDetails.setAccountId(accountId);
	}

	/**
	* Sets the account name of this accrual details.
	*
	* @param accountName the account name of this accrual details
	*/
	@Override
	public void setAccountName(java.lang.String accountName) {
		_accrualDetails.setAccountName(accountName);
	}

	/**
	* Sets the account no of this accrual details.
	*
	* @param accountNo the account no of this accrual details
	*/
	@Override
	public void setAccountNo(java.lang.String accountNo) {
		_accrualDetails.setAccountNo(accountNo);
	}

	/**
	* Sets the accrual details sid of this accrual details.
	*
	* @param accrualDetailsSid the accrual details sid of this accrual details
	*/
	@Override
	public void setAccrualDetailsSid(int accrualDetailsSid) {
		_accrualDetails.setAccrualDetailsSid(accrualDetailsSid);
	}

	/**
	* Sets the accrual ID of this accrual details.
	*
	* @param accrualId the accrual ID of this accrual details
	*/
	@Override
	public void setAccrualId(java.lang.String accrualId) {
		_accrualDetails.setAccrualId(accrualId);
	}

	/**
	* Sets the accrual period end date of this accrual details.
	*
	* @param accrualPeriodEndDate the accrual period end date of this accrual details
	*/
	@Override
	public void setAccrualPeriodEndDate(Date accrualPeriodEndDate) {
		_accrualDetails.setAccrualPeriodEndDate(accrualPeriodEndDate);
	}

	/**
	* Sets the accrual period start date of this accrual details.
	*
	* @param accrualPeriodStartDate the accrual period start date of this accrual details
	*/
	@Override
	public void setAccrualPeriodStartDate(Date accrualPeriodStartDate) {
		_accrualDetails.setAccrualPeriodStartDate(accrualPeriodStartDate);
	}

	/**
	* Sets the accrual type of this accrual details.
	*
	* @param accrualType the accrual type of this accrual details
	*/
	@Override
	public void setAccrualType(java.lang.String accrualType) {
		_accrualDetails.setAccrualType(accrualType);
	}

	/**
	* Sets the acct identifier code qualifier of this accrual details.
	*
	* @param acctIdentifierCodeQualifier the acct identifier code qualifier of this accrual details
	*/
	@Override
	public void setAcctIdentifierCodeQualifier(
		java.lang.String acctIdentifierCodeQualifier) {
		_accrualDetails.setAcctIdentifierCodeQualifier(acctIdentifierCodeQualifier);
	}

	/**
	* Sets the amount of this accrual details.
	*
	* @param amount the amount of this accrual details
	*/
	@Override
	public void setAmount(double amount) {
		_accrualDetails.setAmount(amount);
	}

	/**
	* Sets the batch ID of this accrual details.
	*
	* @param batchId the batch ID of this accrual details
	*/
	@Override
	public void setBatchId(java.lang.String batchId) {
		_accrualDetails.setBatchId(batchId);
	}

	/**
	* Sets the brand ID of this accrual details.
	*
	* @param brandId the brand ID of this accrual details
	*/
	@Override
	public void setBrandId(java.lang.String brandId) {
		_accrualDetails.setBrandId(brandId);
	}

	/**
	* Sets the brand master sid of this accrual details.
	*
	* @param brandMasterSid the brand master sid of this accrual details
	*/
	@Override
	public void setBrandMasterSid(int brandMasterSid) {
		_accrualDetails.setBrandMasterSid(brandMasterSid);
	}

	/**
	* Sets the brand name of this accrual details.
	*
	* @param brandName the brand name of this accrual details
	*/
	@Override
	public void setBrandName(java.lang.String brandName) {
		_accrualDetails.setBrandName(brandName);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_accrualDetails.setCachedModel(cachedModel);
	}

	/**
	* Sets the category ID of this accrual details.
	*
	* @param categoryId the category ID of this accrual details
	*/
	@Override
	public void setCategoryId(java.lang.String categoryId) {
		_accrualDetails.setCategoryId(categoryId);
	}

	/**
	* Sets the company cost center of this accrual details.
	*
	* @param companyCostCenter the company cost center of this accrual details
	*/
	@Override
	public void setCompanyCostCenter(java.lang.String companyCostCenter) {
		_accrualDetails.setCompanyCostCenter(companyCostCenter);
	}

	/**
	* Sets the company master sid of this accrual details.
	*
	* @param companyMasterSid the company master sid of this accrual details
	*/
	@Override
	public void setCompanyMasterSid(int companyMasterSid) {
		_accrualDetails.setCompanyMasterSid(companyMasterSid);
	}

	/**
	* Sets the company no of this accrual details.
	*
	* @param companyNo the company no of this accrual details
	*/
	@Override
	public void setCompanyNo(java.lang.String companyNo) {
		_accrualDetails.setCompanyNo(companyNo);
	}

	/**
	* Sets the company string ID of this accrual details.
	*
	* @param companyStringId the company string ID of this accrual details
	*/
	@Override
	public void setCompanyStringId(java.lang.String companyStringId) {
		_accrualDetails.setCompanyStringId(companyStringId);
	}

	/**
	* Sets the company string identifier code qualifier of this accrual details.
	*
	* @param companyStringIdentifierCodeQualifier the company string identifier code qualifier of this accrual details
	*/
	@Override
	public void setCompanyStringIdentifierCodeQualifier(
		java.lang.String companyStringIdentifierCodeQualifier) {
		_accrualDetails.setCompanyStringIdentifierCodeQualifier(companyStringIdentifierCodeQualifier);
	}

	/**
	* Sets the contract ID of this accrual details.
	*
	* @param contractId the contract ID of this accrual details
	*/
	@Override
	public void setContractId(java.lang.String contractId) {
		_accrualDetails.setContractId(contractId);
	}

	/**
	* Sets the contract master sid of this accrual details.
	*
	* @param contractMasterSid the contract master sid of this accrual details
	*/
	@Override
	public void setContractMasterSid(int contractMasterSid) {
		_accrualDetails.setContractMasterSid(contractMasterSid);
	}

	/**
	* Sets the contract name of this accrual details.
	*
	* @param contractName the contract name of this accrual details
	*/
	@Override
	public void setContractName(java.lang.String contractName) {
		_accrualDetails.setContractName(contractName);
	}

	/**
	* Sets the contract no of this accrual details.
	*
	* @param contractNo the contract no of this accrual details
	*/
	@Override
	public void setContractNo(java.lang.String contractNo) {
		_accrualDetails.setContractNo(contractNo);
	}

	/**
	* Sets the created by of this accrual details.
	*
	* @param createdBy the created by of this accrual details
	*/
	@Override
	public void setCreatedBy(int createdBy) {
		_accrualDetails.setCreatedBy(createdBy);
	}

	/**
	* Sets the created date of this accrual details.
	*
	* @param createdDate the created date of this accrual details
	*/
	@Override
	public void setCreatedDate(Date createdDate) {
		_accrualDetails.setCreatedDate(createdDate);
	}

	/**
	* Sets the deduction sub type of this accrual details.
	*
	* @param deductionSubType the deduction sub type of this accrual details
	*/
	@Override
	public void setDeductionSubType(java.lang.String deductionSubType) {
		_accrualDetails.setDeductionSubType(deductionSubType);
	}

	/**
	* Sets the deduction type of this accrual details.
	*
	* @param deductionType the deduction type of this accrual details
	*/
	@Override
	public void setDeductionType(java.lang.String deductionType) {
		_accrualDetails.setDeductionType(deductionType);
	}

	/**
	* Sets the document type of this accrual details.
	*
	* @param documentType the document type of this accrual details
	*/
	@Override
	public void setDocumentType(java.lang.String documentType) {
		_accrualDetails.setDocumentType(documentType);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_accrualDetails.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_accrualDetails.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_accrualDetails.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the gl account of this accrual details.
	*
	* @param glAccount the gl account of this accrual details
	*/
	@Override
	public void setGlAccount(java.lang.String glAccount) {
		_accrualDetails.setGlAccount(glAccount);
	}

	/**
	* Sets the gl company master sid of this accrual details.
	*
	* @param glCompanyMasterSid the gl company master sid of this accrual details
	*/
	@Override
	public void setGlCompanyMasterSid(java.lang.String glCompanyMasterSid) {
		_accrualDetails.setGlCompanyMasterSid(glCompanyMasterSid);
	}

	/**
	* Sets the gl date of this accrual details.
	*
	* @param glDate the gl date of this accrual details
	*/
	@Override
	public void setGlDate(Date glDate) {
		_accrualDetails.setGlDate(glDate);
	}

	/**
	* Sets the inbound status of this accrual details.
	*
	* @param inboundStatus the inbound status of this accrual details
	*/
	@Override
	public void setInboundStatus(java.lang.String inboundStatus) {
		_accrualDetails.setInboundStatus(inboundStatus);
	}

	/**
	* Sets the item ID of this accrual details.
	*
	* @param itemId the item ID of this accrual details
	*/
	@Override
	public void setItemId(java.lang.String itemId) {
		_accrualDetails.setItemId(itemId);
	}

	/**
	* Sets the item identifier code qualifier of this accrual details.
	*
	* @param itemIdentifierCodeQualifier the item identifier code qualifier of this accrual details
	*/
	@Override
	public void setItemIdentifierCodeQualifier(
		java.lang.String itemIdentifierCodeQualifier) {
		_accrualDetails.setItemIdentifierCodeQualifier(itemIdentifierCodeQualifier);
	}

	/**
	* Sets the item master sid of this accrual details.
	*
	* @param itemMasterSid the item master sid of this accrual details
	*/
	@Override
	public void setItemMasterSid(int itemMasterSid) {
		_accrualDetails.setItemMasterSid(itemMasterSid);
	}

	/**
	* Sets the item name of this accrual details.
	*
	* @param itemName the item name of this accrual details
	*/
	@Override
	public void setItemName(java.lang.String itemName) {
		_accrualDetails.setItemName(itemName);
	}

	/**
	* Sets the item no of this accrual details.
	*
	* @param itemNo the item no of this accrual details
	*/
	@Override
	public void setItemNo(java.lang.String itemNo) {
		_accrualDetails.setItemNo(itemNo);
	}

	/**
	* Sets the modified by of this accrual details.
	*
	* @param modifiedBy the modified by of this accrual details
	*/
	@Override
	public void setModifiedBy(int modifiedBy) {
		_accrualDetails.setModifiedBy(modifiedBy);
	}

	/**
	* Sets the modified date of this accrual details.
	*
	* @param modifiedDate the modified date of this accrual details
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_accrualDetails.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_accrualDetails.setNew(n);
	}

	/**
	* Sets the posting date of this accrual details.
	*
	* @param postingDate the posting date of this accrual details
	*/
	@Override
	public void setPostingDate(Date postingDate) {
		_accrualDetails.setPostingDate(postingDate);
	}

	/**
	* Sets the posting indicator of this accrual details.
	*
	* @param postingIndicator the posting indicator of this accrual details
	*/
	@Override
	public void setPostingIndicator(java.lang.String postingIndicator) {
		_accrualDetails.setPostingIndicator(postingIndicator);
	}

	/**
	* Sets the posting status of this accrual details.
	*
	* @param postingStatus the posting status of this accrual details
	*/
	@Override
	public void setPostingStatus(java.lang.String postingStatus) {
		_accrualDetails.setPostingStatus(postingStatus);
	}

	/**
	* Sets the primary key of this accrual details.
	*
	* @param primaryKey the primary key of this accrual details
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_accrualDetails.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_accrualDetails.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the program no of this accrual details.
	*
	* @param programNo the program no of this accrual details
	*/
	@Override
	public void setProgramNo(java.lang.String programNo) {
		_accrualDetails.setProgramNo(programNo);
	}

	/**
	* Sets the program type of this accrual details.
	*
	* @param programType the program type of this accrual details
	*/
	@Override
	public void setProgramType(int programType) {
		_accrualDetails.setProgramType(programType);
	}

	/**
	* Sets the provision ID of this accrual details.
	*
	* @param provisionId the provision ID of this accrual details
	*/
	@Override
	public void setProvisionId(java.lang.String provisionId) {
		_accrualDetails.setProvisionId(provisionId);
	}

	/**
	* Sets the record created date of this accrual details.
	*
	* @param recordCreatedDate the record created date of this accrual details
	*/
	@Override
	public void setRecordCreatedDate(Date recordCreatedDate) {
		_accrualDetails.setRecordCreatedDate(recordCreatedDate);
	}

	/**
	* Sets whether this accrual details is record lock status.
	*
	* @param recordLockStatus the record lock status of this accrual details
	*/
	@Override
	public void setRecordLockStatus(boolean recordLockStatus) {
		_accrualDetails.setRecordLockStatus(recordLockStatus);
	}

	/**
	* Sets the rs model sid of this accrual details.
	*
	* @param rsModelSid the rs model sid of this accrual details
	*/
	@Override
	public void setRsModelSid(int rsModelSid) {
		_accrualDetails.setRsModelSid(rsModelSid);
	}

	/**
	* Sets the sales master ID of this accrual details.
	*
	* @param salesMasterId the sales master ID of this accrual details
	*/
	@Override
	public void setSalesMasterId(java.lang.String salesMasterId) {
		_accrualDetails.setSalesMasterId(salesMasterId);
	}

	/**
	* Sets the source of this accrual details.
	*
	* @param source the source of this accrual details
	*/
	@Override
	public void setSource(java.lang.String source) {
		_accrualDetails.setSource(source);
	}

	/**
	* Sets the sub ledger of this accrual details.
	*
	* @param subLedger the sub ledger of this accrual details
	*/
	@Override
	public void setSubLedger(java.lang.String subLedger) {
		_accrualDetails.setSubLedger(subLedger);
	}

	/**
	* Sets the sub ledger type of this accrual details.
	*
	* @param subLedgerType the sub ledger type of this accrual details
	*/
	@Override
	public void setSubLedgerType(java.lang.String subLedgerType) {
		_accrualDetails.setSubLedgerType(subLedgerType);
	}

	/**
	* Sets the version no of this accrual details.
	*
	* @param versionNo the version no of this accrual details
	*/
	@Override
	public void setVersionNo(int versionNo) {
		_accrualDetails.setVersionNo(versionNo);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<AccrualDetails> toCacheModel() {
		return _accrualDetails.toCacheModel();
	}

	@Override
	public AccrualDetails toEscapedModel() {
		return new AccrualDetailsWrapper(_accrualDetails.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _accrualDetails.toString();
	}

	@Override
	public AccrualDetails toUnescapedModel() {
		return new AccrualDetailsWrapper(_accrualDetails.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _accrualDetails.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AccrualDetailsWrapper)) {
			return false;
		}

		AccrualDetailsWrapper accrualDetailsWrapper = (AccrualDetailsWrapper)obj;

		if (Objects.equals(_accrualDetails,
					accrualDetailsWrapper._accrualDetails)) {
			return true;
		}

		return false;
	}

	@Override
	public AccrualDetails getWrappedModel() {
		return _accrualDetails;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _accrualDetails.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _accrualDetails.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_accrualDetails.resetOriginalValues();
	}

	private final AccrualDetails _accrualDetails;
}