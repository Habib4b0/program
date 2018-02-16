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
 * This class is a wrapper for {@link ContractMaster}.
 * </p>
 *
 * @author
 * @see ContractMaster
 * @generated
 */
@ProviderType
public class ContractMasterWrapper implements ContractMaster,
	ModelWrapper<ContractMaster> {
	public ContractMasterWrapper(ContractMaster contractMaster) {
		_contractMaster = contractMaster;
	}

	@Override
	public Class<?> getModelClass() {
		return ContractMaster.class;
	}

	@Override
	public String getModelClassName() {
		return ContractMaster.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("proposalEndDate", getProposalEndDate());
		attributes.put("createdDate", getCreatedDate());
		attributes.put("renegotiationEndDate", getRenegotiationEndDate());
		attributes.put("outsideAdditionalName", getOutsideAdditionalName());
		attributes.put("endDate", getEndDate());
		attributes.put("manfCompanyMasterSid", getManfCompanyMasterSid());
		attributes.put("renegotiationStartDate", getRenegotiationStartDate());
		attributes.put("insideAuthor", getInsideAuthor());
		attributes.put("advanceNoticeDays", getAdvanceNoticeDays());
		attributes.put("outsideOwner", getOutsideOwner());
		attributes.put("mostFavoredNation", getMostFavoredNation());
		attributes.put("insideAdditionalPhone", getInsideAdditionalPhone());
		attributes.put("originalStartDate", getOriginalStartDate());
		attributes.put("createdBy", getCreatedBy());
		attributes.put("proposalStartDate", getProposalStartDate());
		attributes.put("contractTradeClass", getContractTradeClass());
		attributes.put("outsideAdditional", getOutsideAdditional());
		attributes.put("processStatus", getProcessStatus());
		attributes.put("insideAdditionalName", getInsideAdditionalName());
		attributes.put("contractMasterSid", getContractMasterSid());
		attributes.put("contractStatus", getContractStatus());
		attributes.put("contractId", getContractId());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("contractType", getContractType());
		attributes.put("awardStatus", getAwardStatus());
		attributes.put("insideOwner", getInsideOwner());
		attributes.put("source", getSource());
		attributes.put("shippingTerms", getShippingTerms());
		attributes.put("priceEscalationClause", getPriceEscalationClause());
		attributes.put("modifiedBy", getModifiedBy());
		attributes.put("outsideAdditionalPhone", getOutsideAdditionalPhone());
		attributes.put("term", getTerm());
		attributes.put("contractNo", getContractNo());
		attributes.put("batchId", getBatchId());
		attributes.put("documentClass", getDocumentClass());
		attributes.put("originalEndDate", getOriginalEndDate());
		attributes.put("paymentTerms", getPaymentTerms());
		attributes.put("insideAdditional", getInsideAdditional());
		attributes.put("affiliatedContractInfo", getAffiliatedContractInfo());
		attributes.put("category", getCategory());
		attributes.put("outsidePhone", getOutsidePhone());
		attributes.put("priceprotectionStartDate", getPriceprotectionStartDate());
		attributes.put("priceprotectionEndDate", getPriceprotectionEndDate());
		attributes.put("documentType", getDocumentType());
		attributes.put("exemptFromLowPrice", getExemptFromLowPrice());
		attributes.put("organizationKey", getOrganizationKey());
		attributes.put("currency", getCurrency());
		attributes.put("insidePhone", getInsidePhone());
		attributes.put("bunitCompanyMasterSid", getBunitCompanyMasterSid());
		attributes.put("outsideAuthor", getOutsideAuthor());
		attributes.put("contHoldCompanyMasterSid", getContHoldCompanyMasterSid());
		attributes.put("startDate", getStartDate());
		attributes.put("contractName", getContractName());
		attributes.put("lastUpdatedDate", getLastUpdatedDate());
		attributes.put("recordLockStatus", getRecordLockStatus());
		attributes.put("priceResetIndicator", getPriceResetIndicator());
		attributes.put("minimumOrder", getMinimumOrder());
		attributes.put("cancellationClause", getCancellationClause());
		attributes.put("inboundStatus", getInboundStatus());
		attributes.put("internalNotes", getInternalNotes());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Date proposalEndDate = (Date)attributes.get("proposalEndDate");

		if (proposalEndDate != null) {
			setProposalEndDate(proposalEndDate);
		}

		Date createdDate = (Date)attributes.get("createdDate");

		if (createdDate != null) {
			setCreatedDate(createdDate);
		}

		Date renegotiationEndDate = (Date)attributes.get("renegotiationEndDate");

		if (renegotiationEndDate != null) {
			setRenegotiationEndDate(renegotiationEndDate);
		}

		String outsideAdditionalName = (String)attributes.get(
				"outsideAdditionalName");

		if (outsideAdditionalName != null) {
			setOutsideAdditionalName(outsideAdditionalName);
		}

		Date endDate = (Date)attributes.get("endDate");

		if (endDate != null) {
			setEndDate(endDate);
		}

		String manfCompanyMasterSid = (String)attributes.get(
				"manfCompanyMasterSid");

		if (manfCompanyMasterSid != null) {
			setManfCompanyMasterSid(manfCompanyMasterSid);
		}

		Date renegotiationStartDate = (Date)attributes.get(
				"renegotiationStartDate");

		if (renegotiationStartDate != null) {
			setRenegotiationStartDate(renegotiationStartDate);
		}

		String insideAuthor = (String)attributes.get("insideAuthor");

		if (insideAuthor != null) {
			setInsideAuthor(insideAuthor);
		}

		Double advanceNoticeDays = (Double)attributes.get("advanceNoticeDays");

		if (advanceNoticeDays != null) {
			setAdvanceNoticeDays(advanceNoticeDays);
		}

		String outsideOwner = (String)attributes.get("outsideOwner");

		if (outsideOwner != null) {
			setOutsideOwner(outsideOwner);
		}

		String mostFavoredNation = (String)attributes.get("mostFavoredNation");

		if (mostFavoredNation != null) {
			setMostFavoredNation(mostFavoredNation);
		}

		String insideAdditionalPhone = (String)attributes.get(
				"insideAdditionalPhone");

		if (insideAdditionalPhone != null) {
			setInsideAdditionalPhone(insideAdditionalPhone);
		}

		Date originalStartDate = (Date)attributes.get("originalStartDate");

		if (originalStartDate != null) {
			setOriginalStartDate(originalStartDate);
		}

		Integer createdBy = (Integer)attributes.get("createdBy");

		if (createdBy != null) {
			setCreatedBy(createdBy);
		}

		Date proposalStartDate = (Date)attributes.get("proposalStartDate");

		if (proposalStartDate != null) {
			setProposalStartDate(proposalStartDate);
		}

		Integer contractTradeClass = (Integer)attributes.get(
				"contractTradeClass");

		if (contractTradeClass != null) {
			setContractTradeClass(contractTradeClass);
		}

		String outsideAdditional = (String)attributes.get("outsideAdditional");

		if (outsideAdditional != null) {
			setOutsideAdditional(outsideAdditional);
		}

		Boolean processStatus = (Boolean)attributes.get("processStatus");

		if (processStatus != null) {
			setProcessStatus(processStatus);
		}

		String insideAdditionalName = (String)attributes.get(
				"insideAdditionalName");

		if (insideAdditionalName != null) {
			setInsideAdditionalName(insideAdditionalName);
		}

		Integer contractMasterSid = (Integer)attributes.get("contractMasterSid");

		if (contractMasterSid != null) {
			setContractMasterSid(contractMasterSid);
		}

		Integer contractStatus = (Integer)attributes.get("contractStatus");

		if (contractStatus != null) {
			setContractStatus(contractStatus);
		}

		String contractId = (String)attributes.get("contractId");

		if (contractId != null) {
			setContractId(contractId);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Integer contractType = (Integer)attributes.get("contractType");

		if (contractType != null) {
			setContractType(contractType);
		}

		Integer awardStatus = (Integer)attributes.get("awardStatus");

		if (awardStatus != null) {
			setAwardStatus(awardStatus);
		}

		String insideOwner = (String)attributes.get("insideOwner");

		if (insideOwner != null) {
			setInsideOwner(insideOwner);
		}

		String source = (String)attributes.get("source");

		if (source != null) {
			setSource(source);
		}

		String shippingTerms = (String)attributes.get("shippingTerms");

		if (shippingTerms != null) {
			setShippingTerms(shippingTerms);
		}

		String priceEscalationClause = (String)attributes.get(
				"priceEscalationClause");

		if (priceEscalationClause != null) {
			setPriceEscalationClause(priceEscalationClause);
		}

		Integer modifiedBy = (Integer)attributes.get("modifiedBy");

		if (modifiedBy != null) {
			setModifiedBy(modifiedBy);
		}

		String outsideAdditionalPhone = (String)attributes.get(
				"outsideAdditionalPhone");

		if (outsideAdditionalPhone != null) {
			setOutsideAdditionalPhone(outsideAdditionalPhone);
		}

		Integer term = (Integer)attributes.get("term");

		if (term != null) {
			setTerm(term);
		}

		String contractNo = (String)attributes.get("contractNo");

		if (contractNo != null) {
			setContractNo(contractNo);
		}

		String batchId = (String)attributes.get("batchId");

		if (batchId != null) {
			setBatchId(batchId);
		}

		Integer documentClass = (Integer)attributes.get("documentClass");

		if (documentClass != null) {
			setDocumentClass(documentClass);
		}

		Date originalEndDate = (Date)attributes.get("originalEndDate");

		if (originalEndDate != null) {
			setOriginalEndDate(originalEndDate);
		}

		Integer paymentTerms = (Integer)attributes.get("paymentTerms");

		if (paymentTerms != null) {
			setPaymentTerms(paymentTerms);
		}

		String insideAdditional = (String)attributes.get("insideAdditional");

		if (insideAdditional != null) {
			setInsideAdditional(insideAdditional);
		}

		String affiliatedContractInfo = (String)attributes.get(
				"affiliatedContractInfo");

		if (affiliatedContractInfo != null) {
			setAffiliatedContractInfo(affiliatedContractInfo);
		}

		String category = (String)attributes.get("category");

		if (category != null) {
			setCategory(category);
		}

		String outsidePhone = (String)attributes.get("outsidePhone");

		if (outsidePhone != null) {
			setOutsidePhone(outsidePhone);
		}

		Date priceprotectionStartDate = (Date)attributes.get(
				"priceprotectionStartDate");

		if (priceprotectionStartDate != null) {
			setPriceprotectionStartDate(priceprotectionStartDate);
		}

		Date priceprotectionEndDate = (Date)attributes.get(
				"priceprotectionEndDate");

		if (priceprotectionEndDate != null) {
			setPriceprotectionEndDate(priceprotectionEndDate);
		}

		Integer documentType = (Integer)attributes.get("documentType");

		if (documentType != null) {
			setDocumentType(documentType);
		}

		String exemptFromLowPrice = (String)attributes.get("exemptFromLowPrice");

		if (exemptFromLowPrice != null) {
			setExemptFromLowPrice(exemptFromLowPrice);
		}

		String organizationKey = (String)attributes.get("organizationKey");

		if (organizationKey != null) {
			setOrganizationKey(organizationKey);
		}

		String currency = (String)attributes.get("currency");

		if (currency != null) {
			setCurrency(currency);
		}

		String insidePhone = (String)attributes.get("insidePhone");

		if (insidePhone != null) {
			setInsidePhone(insidePhone);
		}

		String bunitCompanyMasterSid = (String)attributes.get(
				"bunitCompanyMasterSid");

		if (bunitCompanyMasterSid != null) {
			setBunitCompanyMasterSid(bunitCompanyMasterSid);
		}

		String outsideAuthor = (String)attributes.get("outsideAuthor");

		if (outsideAuthor != null) {
			setOutsideAuthor(outsideAuthor);
		}

		String contHoldCompanyMasterSid = (String)attributes.get(
				"contHoldCompanyMasterSid");

		if (contHoldCompanyMasterSid != null) {
			setContHoldCompanyMasterSid(contHoldCompanyMasterSid);
		}

		Date startDate = (Date)attributes.get("startDate");

		if (startDate != null) {
			setStartDate(startDate);
		}

		String contractName = (String)attributes.get("contractName");

		if (contractName != null) {
			setContractName(contractName);
		}

		Date lastUpdatedDate = (Date)attributes.get("lastUpdatedDate");

		if (lastUpdatedDate != null) {
			setLastUpdatedDate(lastUpdatedDate);
		}

		Boolean recordLockStatus = (Boolean)attributes.get("recordLockStatus");

		if (recordLockStatus != null) {
			setRecordLockStatus(recordLockStatus);
		}

		String priceResetIndicator = (String)attributes.get(
				"priceResetIndicator");

		if (priceResetIndicator != null) {
			setPriceResetIndicator(priceResetIndicator);
		}

		String minimumOrder = (String)attributes.get("minimumOrder");

		if (minimumOrder != null) {
			setMinimumOrder(minimumOrder);
		}

		String cancellationClause = (String)attributes.get("cancellationClause");

		if (cancellationClause != null) {
			setCancellationClause(cancellationClause);
		}

		String inboundStatus = (String)attributes.get("inboundStatus");

		if (inboundStatus != null) {
			setInboundStatus(inboundStatus);
		}

		String internalNotes = (String)attributes.get("internalNotes");

		if (internalNotes != null) {
			setInternalNotes(internalNotes);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new ContractMasterWrapper((ContractMaster)_contractMaster.clone());
	}

	@Override
	public int compareTo(ContractMaster contractMaster) {
		return _contractMaster.compareTo(contractMaster);
	}

	/**
	* Returns the advance notice days of this contract master.
	*
	* @return the advance notice days of this contract master
	*/
	@Override
	public double getAdvanceNoticeDays() {
		return _contractMaster.getAdvanceNoticeDays();
	}

	/**
	* Returns the affiliated contract info of this contract master.
	*
	* @return the affiliated contract info of this contract master
	*/
	@Override
	public java.lang.String getAffiliatedContractInfo() {
		return _contractMaster.getAffiliatedContractInfo();
	}

	/**
	* Returns the award status of this contract master.
	*
	* @return the award status of this contract master
	*/
	@Override
	public int getAwardStatus() {
		return _contractMaster.getAwardStatus();
	}

	/**
	* Returns the batch ID of this contract master.
	*
	* @return the batch ID of this contract master
	*/
	@Override
	public java.lang.String getBatchId() {
		return _contractMaster.getBatchId();
	}

	/**
	* Returns the bunit company master sid of this contract master.
	*
	* @return the bunit company master sid of this contract master
	*/
	@Override
	public java.lang.String getBunitCompanyMasterSid() {
		return _contractMaster.getBunitCompanyMasterSid();
	}

	/**
	* Returns the cancellation clause of this contract master.
	*
	* @return the cancellation clause of this contract master
	*/
	@Override
	public java.lang.String getCancellationClause() {
		return _contractMaster.getCancellationClause();
	}

	/**
	* Returns the category of this contract master.
	*
	* @return the category of this contract master
	*/
	@Override
	public java.lang.String getCategory() {
		return _contractMaster.getCategory();
	}

	/**
	* Returns the cont hold company master sid of this contract master.
	*
	* @return the cont hold company master sid of this contract master
	*/
	@Override
	public java.lang.String getContHoldCompanyMasterSid() {
		return _contractMaster.getContHoldCompanyMasterSid();
	}

	/**
	* Returns the contract ID of this contract master.
	*
	* @return the contract ID of this contract master
	*/
	@Override
	public java.lang.String getContractId() {
		return _contractMaster.getContractId();
	}

	/**
	* Returns the contract master sid of this contract master.
	*
	* @return the contract master sid of this contract master
	*/
	@Override
	public int getContractMasterSid() {
		return _contractMaster.getContractMasterSid();
	}

	/**
	* Returns the contract name of this contract master.
	*
	* @return the contract name of this contract master
	*/
	@Override
	public java.lang.String getContractName() {
		return _contractMaster.getContractName();
	}

	/**
	* Returns the contract no of this contract master.
	*
	* @return the contract no of this contract master
	*/
	@Override
	public java.lang.String getContractNo() {
		return _contractMaster.getContractNo();
	}

	/**
	* Returns the contract status of this contract master.
	*
	* @return the contract status of this contract master
	*/
	@Override
	public int getContractStatus() {
		return _contractMaster.getContractStatus();
	}

	/**
	* Returns the contract trade class of this contract master.
	*
	* @return the contract trade class of this contract master
	*/
	@Override
	public int getContractTradeClass() {
		return _contractMaster.getContractTradeClass();
	}

	/**
	* Returns the contract type of this contract master.
	*
	* @return the contract type of this contract master
	*/
	@Override
	public int getContractType() {
		return _contractMaster.getContractType();
	}

	/**
	* Returns the created by of this contract master.
	*
	* @return the created by of this contract master
	*/
	@Override
	public int getCreatedBy() {
		return _contractMaster.getCreatedBy();
	}

	/**
	* Returns the created date of this contract master.
	*
	* @return the created date of this contract master
	*/
	@Override
	public Date getCreatedDate() {
		return _contractMaster.getCreatedDate();
	}

	/**
	* Returns the currency of this contract master.
	*
	* @return the currency of this contract master
	*/
	@Override
	public java.lang.String getCurrency() {
		return _contractMaster.getCurrency();
	}

	/**
	* Returns the document class of this contract master.
	*
	* @return the document class of this contract master
	*/
	@Override
	public int getDocumentClass() {
		return _contractMaster.getDocumentClass();
	}

	/**
	* Returns the document type of this contract master.
	*
	* @return the document type of this contract master
	*/
	@Override
	public int getDocumentType() {
		return _contractMaster.getDocumentType();
	}

	/**
	* Returns the end date of this contract master.
	*
	* @return the end date of this contract master
	*/
	@Override
	public Date getEndDate() {
		return _contractMaster.getEndDate();
	}

	/**
	* Returns the exempt from low price of this contract master.
	*
	* @return the exempt from low price of this contract master
	*/
	@Override
	public java.lang.String getExemptFromLowPrice() {
		return _contractMaster.getExemptFromLowPrice();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _contractMaster.getExpandoBridge();
	}

	/**
	* Returns the inbound status of this contract master.
	*
	* @return the inbound status of this contract master
	*/
	@Override
	public java.lang.String getInboundStatus() {
		return _contractMaster.getInboundStatus();
	}

	/**
	* Returns the inside additional of this contract master.
	*
	* @return the inside additional of this contract master
	*/
	@Override
	public java.lang.String getInsideAdditional() {
		return _contractMaster.getInsideAdditional();
	}

	/**
	* Returns the inside additional name of this contract master.
	*
	* @return the inside additional name of this contract master
	*/
	@Override
	public java.lang.String getInsideAdditionalName() {
		return _contractMaster.getInsideAdditionalName();
	}

	/**
	* Returns the inside additional phone of this contract master.
	*
	* @return the inside additional phone of this contract master
	*/
	@Override
	public java.lang.String getInsideAdditionalPhone() {
		return _contractMaster.getInsideAdditionalPhone();
	}

	/**
	* Returns the inside author of this contract master.
	*
	* @return the inside author of this contract master
	*/
	@Override
	public java.lang.String getInsideAuthor() {
		return _contractMaster.getInsideAuthor();
	}

	/**
	* Returns the inside owner of this contract master.
	*
	* @return the inside owner of this contract master
	*/
	@Override
	public java.lang.String getInsideOwner() {
		return _contractMaster.getInsideOwner();
	}

	/**
	* Returns the inside phone of this contract master.
	*
	* @return the inside phone of this contract master
	*/
	@Override
	public java.lang.String getInsidePhone() {
		return _contractMaster.getInsidePhone();
	}

	/**
	* Returns the internal notes of this contract master.
	*
	* @return the internal notes of this contract master
	*/
	@Override
	public java.lang.String getInternalNotes() {
		return _contractMaster.getInternalNotes();
	}

	/**
	* Returns the last updated date of this contract master.
	*
	* @return the last updated date of this contract master
	*/
	@Override
	public Date getLastUpdatedDate() {
		return _contractMaster.getLastUpdatedDate();
	}

	/**
	* Returns the manf company master sid of this contract master.
	*
	* @return the manf company master sid of this contract master
	*/
	@Override
	public java.lang.String getManfCompanyMasterSid() {
		return _contractMaster.getManfCompanyMasterSid();
	}

	/**
	* Returns the minimum order of this contract master.
	*
	* @return the minimum order of this contract master
	*/
	@Override
	public java.lang.String getMinimumOrder() {
		return _contractMaster.getMinimumOrder();
	}

	/**
	* Returns the modified by of this contract master.
	*
	* @return the modified by of this contract master
	*/
	@Override
	public int getModifiedBy() {
		return _contractMaster.getModifiedBy();
	}

	/**
	* Returns the modified date of this contract master.
	*
	* @return the modified date of this contract master
	*/
	@Override
	public Date getModifiedDate() {
		return _contractMaster.getModifiedDate();
	}

	/**
	* Returns the most favored nation of this contract master.
	*
	* @return the most favored nation of this contract master
	*/
	@Override
	public java.lang.String getMostFavoredNation() {
		return _contractMaster.getMostFavoredNation();
	}

	/**
	* Returns the organization key of this contract master.
	*
	* @return the organization key of this contract master
	*/
	@Override
	public java.lang.String getOrganizationKey() {
		return _contractMaster.getOrganizationKey();
	}

	/**
	* Returns the original end date of this contract master.
	*
	* @return the original end date of this contract master
	*/
	@Override
	public Date getOriginalEndDate() {
		return _contractMaster.getOriginalEndDate();
	}

	/**
	* Returns the original start date of this contract master.
	*
	* @return the original start date of this contract master
	*/
	@Override
	public Date getOriginalStartDate() {
		return _contractMaster.getOriginalStartDate();
	}

	/**
	* Returns the outside additional of this contract master.
	*
	* @return the outside additional of this contract master
	*/
	@Override
	public java.lang.String getOutsideAdditional() {
		return _contractMaster.getOutsideAdditional();
	}

	/**
	* Returns the outside additional name of this contract master.
	*
	* @return the outside additional name of this contract master
	*/
	@Override
	public java.lang.String getOutsideAdditionalName() {
		return _contractMaster.getOutsideAdditionalName();
	}

	/**
	* Returns the outside additional phone of this contract master.
	*
	* @return the outside additional phone of this contract master
	*/
	@Override
	public java.lang.String getOutsideAdditionalPhone() {
		return _contractMaster.getOutsideAdditionalPhone();
	}

	/**
	* Returns the outside author of this contract master.
	*
	* @return the outside author of this contract master
	*/
	@Override
	public java.lang.String getOutsideAuthor() {
		return _contractMaster.getOutsideAuthor();
	}

	/**
	* Returns the outside owner of this contract master.
	*
	* @return the outside owner of this contract master
	*/
	@Override
	public java.lang.String getOutsideOwner() {
		return _contractMaster.getOutsideOwner();
	}

	/**
	* Returns the outside phone of this contract master.
	*
	* @return the outside phone of this contract master
	*/
	@Override
	public java.lang.String getOutsidePhone() {
		return _contractMaster.getOutsidePhone();
	}

	/**
	* Returns the payment terms of this contract master.
	*
	* @return the payment terms of this contract master
	*/
	@Override
	public int getPaymentTerms() {
		return _contractMaster.getPaymentTerms();
	}

	/**
	* Returns the price escalation clause of this contract master.
	*
	* @return the price escalation clause of this contract master
	*/
	@Override
	public java.lang.String getPriceEscalationClause() {
		return _contractMaster.getPriceEscalationClause();
	}

	/**
	* Returns the priceprotection end date of this contract master.
	*
	* @return the priceprotection end date of this contract master
	*/
	@Override
	public Date getPriceprotectionEndDate() {
		return _contractMaster.getPriceprotectionEndDate();
	}

	/**
	* Returns the priceprotection start date of this contract master.
	*
	* @return the priceprotection start date of this contract master
	*/
	@Override
	public Date getPriceprotectionStartDate() {
		return _contractMaster.getPriceprotectionStartDate();
	}

	/**
	* Returns the price reset indicator of this contract master.
	*
	* @return the price reset indicator of this contract master
	*/
	@Override
	public java.lang.String getPriceResetIndicator() {
		return _contractMaster.getPriceResetIndicator();
	}

	/**
	* Returns the primary key of this contract master.
	*
	* @return the primary key of this contract master
	*/
	@Override
	public int getPrimaryKey() {
		return _contractMaster.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _contractMaster.getPrimaryKeyObj();
	}

	/**
	* Returns the process status of this contract master.
	*
	* @return the process status of this contract master
	*/
	@Override
	public boolean getProcessStatus() {
		return _contractMaster.getProcessStatus();
	}

	/**
	* Returns the proposal end date of this contract master.
	*
	* @return the proposal end date of this contract master
	*/
	@Override
	public Date getProposalEndDate() {
		return _contractMaster.getProposalEndDate();
	}

	/**
	* Returns the proposal start date of this contract master.
	*
	* @return the proposal start date of this contract master
	*/
	@Override
	public Date getProposalStartDate() {
		return _contractMaster.getProposalStartDate();
	}

	/**
	* Returns the record lock status of this contract master.
	*
	* @return the record lock status of this contract master
	*/
	@Override
	public boolean getRecordLockStatus() {
		return _contractMaster.getRecordLockStatus();
	}

	/**
	* Returns the renegotiation end date of this contract master.
	*
	* @return the renegotiation end date of this contract master
	*/
	@Override
	public Date getRenegotiationEndDate() {
		return _contractMaster.getRenegotiationEndDate();
	}

	/**
	* Returns the renegotiation start date of this contract master.
	*
	* @return the renegotiation start date of this contract master
	*/
	@Override
	public Date getRenegotiationStartDate() {
		return _contractMaster.getRenegotiationStartDate();
	}

	/**
	* Returns the shipping terms of this contract master.
	*
	* @return the shipping terms of this contract master
	*/
	@Override
	public java.lang.String getShippingTerms() {
		return _contractMaster.getShippingTerms();
	}

	/**
	* Returns the source of this contract master.
	*
	* @return the source of this contract master
	*/
	@Override
	public java.lang.String getSource() {
		return _contractMaster.getSource();
	}

	/**
	* Returns the start date of this contract master.
	*
	* @return the start date of this contract master
	*/
	@Override
	public Date getStartDate() {
		return _contractMaster.getStartDate();
	}

	/**
	* Returns the term of this contract master.
	*
	* @return the term of this contract master
	*/
	@Override
	public int getTerm() {
		return _contractMaster.getTerm();
	}

	@Override
	public int hashCode() {
		return _contractMaster.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _contractMaster.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _contractMaster.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _contractMaster.isNew();
	}

	/**
	* Returns <code>true</code> if this contract master is process status.
	*
	* @return <code>true</code> if this contract master is process status; <code>false</code> otherwise
	*/
	@Override
	public boolean isProcessStatus() {
		return _contractMaster.isProcessStatus();
	}

	/**
	* Returns <code>true</code> if this contract master is record lock status.
	*
	* @return <code>true</code> if this contract master is record lock status; <code>false</code> otherwise
	*/
	@Override
	public boolean isRecordLockStatus() {
		return _contractMaster.isRecordLockStatus();
	}

	@Override
	public void persist() {
		_contractMaster.persist();
	}

	/**
	* Sets the advance notice days of this contract master.
	*
	* @param advanceNoticeDays the advance notice days of this contract master
	*/
	@Override
	public void setAdvanceNoticeDays(double advanceNoticeDays) {
		_contractMaster.setAdvanceNoticeDays(advanceNoticeDays);
	}

	/**
	* Sets the affiliated contract info of this contract master.
	*
	* @param affiliatedContractInfo the affiliated contract info of this contract master
	*/
	@Override
	public void setAffiliatedContractInfo(
		java.lang.String affiliatedContractInfo) {
		_contractMaster.setAffiliatedContractInfo(affiliatedContractInfo);
	}

	/**
	* Sets the award status of this contract master.
	*
	* @param awardStatus the award status of this contract master
	*/
	@Override
	public void setAwardStatus(int awardStatus) {
		_contractMaster.setAwardStatus(awardStatus);
	}

	/**
	* Sets the batch ID of this contract master.
	*
	* @param batchId the batch ID of this contract master
	*/
	@Override
	public void setBatchId(java.lang.String batchId) {
		_contractMaster.setBatchId(batchId);
	}

	/**
	* Sets the bunit company master sid of this contract master.
	*
	* @param bunitCompanyMasterSid the bunit company master sid of this contract master
	*/
	@Override
	public void setBunitCompanyMasterSid(java.lang.String bunitCompanyMasterSid) {
		_contractMaster.setBunitCompanyMasterSid(bunitCompanyMasterSid);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_contractMaster.setCachedModel(cachedModel);
	}

	/**
	* Sets the cancellation clause of this contract master.
	*
	* @param cancellationClause the cancellation clause of this contract master
	*/
	@Override
	public void setCancellationClause(java.lang.String cancellationClause) {
		_contractMaster.setCancellationClause(cancellationClause);
	}

	/**
	* Sets the category of this contract master.
	*
	* @param category the category of this contract master
	*/
	@Override
	public void setCategory(java.lang.String category) {
		_contractMaster.setCategory(category);
	}

	/**
	* Sets the cont hold company master sid of this contract master.
	*
	* @param contHoldCompanyMasterSid the cont hold company master sid of this contract master
	*/
	@Override
	public void setContHoldCompanyMasterSid(
		java.lang.String contHoldCompanyMasterSid) {
		_contractMaster.setContHoldCompanyMasterSid(contHoldCompanyMasterSid);
	}

	/**
	* Sets the contract ID of this contract master.
	*
	* @param contractId the contract ID of this contract master
	*/
	@Override
	public void setContractId(java.lang.String contractId) {
		_contractMaster.setContractId(contractId);
	}

	/**
	* Sets the contract master sid of this contract master.
	*
	* @param contractMasterSid the contract master sid of this contract master
	*/
	@Override
	public void setContractMasterSid(int contractMasterSid) {
		_contractMaster.setContractMasterSid(contractMasterSid);
	}

	/**
	* Sets the contract name of this contract master.
	*
	* @param contractName the contract name of this contract master
	*/
	@Override
	public void setContractName(java.lang.String contractName) {
		_contractMaster.setContractName(contractName);
	}

	/**
	* Sets the contract no of this contract master.
	*
	* @param contractNo the contract no of this contract master
	*/
	@Override
	public void setContractNo(java.lang.String contractNo) {
		_contractMaster.setContractNo(contractNo);
	}

	/**
	* Sets the contract status of this contract master.
	*
	* @param contractStatus the contract status of this contract master
	*/
	@Override
	public void setContractStatus(int contractStatus) {
		_contractMaster.setContractStatus(contractStatus);
	}

	/**
	* Sets the contract trade class of this contract master.
	*
	* @param contractTradeClass the contract trade class of this contract master
	*/
	@Override
	public void setContractTradeClass(int contractTradeClass) {
		_contractMaster.setContractTradeClass(contractTradeClass);
	}

	/**
	* Sets the contract type of this contract master.
	*
	* @param contractType the contract type of this contract master
	*/
	@Override
	public void setContractType(int contractType) {
		_contractMaster.setContractType(contractType);
	}

	/**
	* Sets the created by of this contract master.
	*
	* @param createdBy the created by of this contract master
	*/
	@Override
	public void setCreatedBy(int createdBy) {
		_contractMaster.setCreatedBy(createdBy);
	}

	/**
	* Sets the created date of this contract master.
	*
	* @param createdDate the created date of this contract master
	*/
	@Override
	public void setCreatedDate(Date createdDate) {
		_contractMaster.setCreatedDate(createdDate);
	}

	/**
	* Sets the currency of this contract master.
	*
	* @param currency the currency of this contract master
	*/
	@Override
	public void setCurrency(java.lang.String currency) {
		_contractMaster.setCurrency(currency);
	}

	/**
	* Sets the document class of this contract master.
	*
	* @param documentClass the document class of this contract master
	*/
	@Override
	public void setDocumentClass(int documentClass) {
		_contractMaster.setDocumentClass(documentClass);
	}

	/**
	* Sets the document type of this contract master.
	*
	* @param documentType the document type of this contract master
	*/
	@Override
	public void setDocumentType(int documentType) {
		_contractMaster.setDocumentType(documentType);
	}

	/**
	* Sets the end date of this contract master.
	*
	* @param endDate the end date of this contract master
	*/
	@Override
	public void setEndDate(Date endDate) {
		_contractMaster.setEndDate(endDate);
	}

	/**
	* Sets the exempt from low price of this contract master.
	*
	* @param exemptFromLowPrice the exempt from low price of this contract master
	*/
	@Override
	public void setExemptFromLowPrice(java.lang.String exemptFromLowPrice) {
		_contractMaster.setExemptFromLowPrice(exemptFromLowPrice);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_contractMaster.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_contractMaster.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_contractMaster.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the inbound status of this contract master.
	*
	* @param inboundStatus the inbound status of this contract master
	*/
	@Override
	public void setInboundStatus(java.lang.String inboundStatus) {
		_contractMaster.setInboundStatus(inboundStatus);
	}

	/**
	* Sets the inside additional of this contract master.
	*
	* @param insideAdditional the inside additional of this contract master
	*/
	@Override
	public void setInsideAdditional(java.lang.String insideAdditional) {
		_contractMaster.setInsideAdditional(insideAdditional);
	}

	/**
	* Sets the inside additional name of this contract master.
	*
	* @param insideAdditionalName the inside additional name of this contract master
	*/
	@Override
	public void setInsideAdditionalName(java.lang.String insideAdditionalName) {
		_contractMaster.setInsideAdditionalName(insideAdditionalName);
	}

	/**
	* Sets the inside additional phone of this contract master.
	*
	* @param insideAdditionalPhone the inside additional phone of this contract master
	*/
	@Override
	public void setInsideAdditionalPhone(java.lang.String insideAdditionalPhone) {
		_contractMaster.setInsideAdditionalPhone(insideAdditionalPhone);
	}

	/**
	* Sets the inside author of this contract master.
	*
	* @param insideAuthor the inside author of this contract master
	*/
	@Override
	public void setInsideAuthor(java.lang.String insideAuthor) {
		_contractMaster.setInsideAuthor(insideAuthor);
	}

	/**
	* Sets the inside owner of this contract master.
	*
	* @param insideOwner the inside owner of this contract master
	*/
	@Override
	public void setInsideOwner(java.lang.String insideOwner) {
		_contractMaster.setInsideOwner(insideOwner);
	}

	/**
	* Sets the inside phone of this contract master.
	*
	* @param insidePhone the inside phone of this contract master
	*/
	@Override
	public void setInsidePhone(java.lang.String insidePhone) {
		_contractMaster.setInsidePhone(insidePhone);
	}

	/**
	* Sets the internal notes of this contract master.
	*
	* @param internalNotes the internal notes of this contract master
	*/
	@Override
	public void setInternalNotes(java.lang.String internalNotes) {
		_contractMaster.setInternalNotes(internalNotes);
	}

	/**
	* Sets the last updated date of this contract master.
	*
	* @param lastUpdatedDate the last updated date of this contract master
	*/
	@Override
	public void setLastUpdatedDate(Date lastUpdatedDate) {
		_contractMaster.setLastUpdatedDate(lastUpdatedDate);
	}

	/**
	* Sets the manf company master sid of this contract master.
	*
	* @param manfCompanyMasterSid the manf company master sid of this contract master
	*/
	@Override
	public void setManfCompanyMasterSid(java.lang.String manfCompanyMasterSid) {
		_contractMaster.setManfCompanyMasterSid(manfCompanyMasterSid);
	}

	/**
	* Sets the minimum order of this contract master.
	*
	* @param minimumOrder the minimum order of this contract master
	*/
	@Override
	public void setMinimumOrder(java.lang.String minimumOrder) {
		_contractMaster.setMinimumOrder(minimumOrder);
	}

	/**
	* Sets the modified by of this contract master.
	*
	* @param modifiedBy the modified by of this contract master
	*/
	@Override
	public void setModifiedBy(int modifiedBy) {
		_contractMaster.setModifiedBy(modifiedBy);
	}

	/**
	* Sets the modified date of this contract master.
	*
	* @param modifiedDate the modified date of this contract master
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_contractMaster.setModifiedDate(modifiedDate);
	}

	/**
	* Sets the most favored nation of this contract master.
	*
	* @param mostFavoredNation the most favored nation of this contract master
	*/
	@Override
	public void setMostFavoredNation(java.lang.String mostFavoredNation) {
		_contractMaster.setMostFavoredNation(mostFavoredNation);
	}

	@Override
	public void setNew(boolean n) {
		_contractMaster.setNew(n);
	}

	/**
	* Sets the organization key of this contract master.
	*
	* @param organizationKey the organization key of this contract master
	*/
	@Override
	public void setOrganizationKey(java.lang.String organizationKey) {
		_contractMaster.setOrganizationKey(organizationKey);
	}

	/**
	* Sets the original end date of this contract master.
	*
	* @param originalEndDate the original end date of this contract master
	*/
	@Override
	public void setOriginalEndDate(Date originalEndDate) {
		_contractMaster.setOriginalEndDate(originalEndDate);
	}

	/**
	* Sets the original start date of this contract master.
	*
	* @param originalStartDate the original start date of this contract master
	*/
	@Override
	public void setOriginalStartDate(Date originalStartDate) {
		_contractMaster.setOriginalStartDate(originalStartDate);
	}

	/**
	* Sets the outside additional of this contract master.
	*
	* @param outsideAdditional the outside additional of this contract master
	*/
	@Override
	public void setOutsideAdditional(java.lang.String outsideAdditional) {
		_contractMaster.setOutsideAdditional(outsideAdditional);
	}

	/**
	* Sets the outside additional name of this contract master.
	*
	* @param outsideAdditionalName the outside additional name of this contract master
	*/
	@Override
	public void setOutsideAdditionalName(java.lang.String outsideAdditionalName) {
		_contractMaster.setOutsideAdditionalName(outsideAdditionalName);
	}

	/**
	* Sets the outside additional phone of this contract master.
	*
	* @param outsideAdditionalPhone the outside additional phone of this contract master
	*/
	@Override
	public void setOutsideAdditionalPhone(
		java.lang.String outsideAdditionalPhone) {
		_contractMaster.setOutsideAdditionalPhone(outsideAdditionalPhone);
	}

	/**
	* Sets the outside author of this contract master.
	*
	* @param outsideAuthor the outside author of this contract master
	*/
	@Override
	public void setOutsideAuthor(java.lang.String outsideAuthor) {
		_contractMaster.setOutsideAuthor(outsideAuthor);
	}

	/**
	* Sets the outside owner of this contract master.
	*
	* @param outsideOwner the outside owner of this contract master
	*/
	@Override
	public void setOutsideOwner(java.lang.String outsideOwner) {
		_contractMaster.setOutsideOwner(outsideOwner);
	}

	/**
	* Sets the outside phone of this contract master.
	*
	* @param outsidePhone the outside phone of this contract master
	*/
	@Override
	public void setOutsidePhone(java.lang.String outsidePhone) {
		_contractMaster.setOutsidePhone(outsidePhone);
	}

	/**
	* Sets the payment terms of this contract master.
	*
	* @param paymentTerms the payment terms of this contract master
	*/
	@Override
	public void setPaymentTerms(int paymentTerms) {
		_contractMaster.setPaymentTerms(paymentTerms);
	}

	/**
	* Sets the price escalation clause of this contract master.
	*
	* @param priceEscalationClause the price escalation clause of this contract master
	*/
	@Override
	public void setPriceEscalationClause(java.lang.String priceEscalationClause) {
		_contractMaster.setPriceEscalationClause(priceEscalationClause);
	}

	/**
	* Sets the priceprotection end date of this contract master.
	*
	* @param priceprotectionEndDate the priceprotection end date of this contract master
	*/
	@Override
	public void setPriceprotectionEndDate(Date priceprotectionEndDate) {
		_contractMaster.setPriceprotectionEndDate(priceprotectionEndDate);
	}

	/**
	* Sets the priceprotection start date of this contract master.
	*
	* @param priceprotectionStartDate the priceprotection start date of this contract master
	*/
	@Override
	public void setPriceprotectionStartDate(Date priceprotectionStartDate) {
		_contractMaster.setPriceprotectionStartDate(priceprotectionStartDate);
	}

	/**
	* Sets the price reset indicator of this contract master.
	*
	* @param priceResetIndicator the price reset indicator of this contract master
	*/
	@Override
	public void setPriceResetIndicator(java.lang.String priceResetIndicator) {
		_contractMaster.setPriceResetIndicator(priceResetIndicator);
	}

	/**
	* Sets the primary key of this contract master.
	*
	* @param primaryKey the primary key of this contract master
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_contractMaster.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_contractMaster.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets whether this contract master is process status.
	*
	* @param processStatus the process status of this contract master
	*/
	@Override
	public void setProcessStatus(boolean processStatus) {
		_contractMaster.setProcessStatus(processStatus);
	}

	/**
	* Sets the proposal end date of this contract master.
	*
	* @param proposalEndDate the proposal end date of this contract master
	*/
	@Override
	public void setProposalEndDate(Date proposalEndDate) {
		_contractMaster.setProposalEndDate(proposalEndDate);
	}

	/**
	* Sets the proposal start date of this contract master.
	*
	* @param proposalStartDate the proposal start date of this contract master
	*/
	@Override
	public void setProposalStartDate(Date proposalStartDate) {
		_contractMaster.setProposalStartDate(proposalStartDate);
	}

	/**
	* Sets whether this contract master is record lock status.
	*
	* @param recordLockStatus the record lock status of this contract master
	*/
	@Override
	public void setRecordLockStatus(boolean recordLockStatus) {
		_contractMaster.setRecordLockStatus(recordLockStatus);
	}

	/**
	* Sets the renegotiation end date of this contract master.
	*
	* @param renegotiationEndDate the renegotiation end date of this contract master
	*/
	@Override
	public void setRenegotiationEndDate(Date renegotiationEndDate) {
		_contractMaster.setRenegotiationEndDate(renegotiationEndDate);
	}

	/**
	* Sets the renegotiation start date of this contract master.
	*
	* @param renegotiationStartDate the renegotiation start date of this contract master
	*/
	@Override
	public void setRenegotiationStartDate(Date renegotiationStartDate) {
		_contractMaster.setRenegotiationStartDate(renegotiationStartDate);
	}

	/**
	* Sets the shipping terms of this contract master.
	*
	* @param shippingTerms the shipping terms of this contract master
	*/
	@Override
	public void setShippingTerms(java.lang.String shippingTerms) {
		_contractMaster.setShippingTerms(shippingTerms);
	}

	/**
	* Sets the source of this contract master.
	*
	* @param source the source of this contract master
	*/
	@Override
	public void setSource(java.lang.String source) {
		_contractMaster.setSource(source);
	}

	/**
	* Sets the start date of this contract master.
	*
	* @param startDate the start date of this contract master
	*/
	@Override
	public void setStartDate(Date startDate) {
		_contractMaster.setStartDate(startDate);
	}

	/**
	* Sets the term of this contract master.
	*
	* @param term the term of this contract master
	*/
	@Override
	public void setTerm(int term) {
		_contractMaster.setTerm(term);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<ContractMaster> toCacheModel() {
		return _contractMaster.toCacheModel();
	}

	@Override
	public ContractMaster toEscapedModel() {
		return new ContractMasterWrapper(_contractMaster.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _contractMaster.toString();
	}

	@Override
	public ContractMaster toUnescapedModel() {
		return new ContractMasterWrapper(_contractMaster.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _contractMaster.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ContractMasterWrapper)) {
			return false;
		}

		ContractMasterWrapper contractMasterWrapper = (ContractMasterWrapper)obj;

		if (Objects.equals(_contractMaster,
					contractMasterWrapper._contractMaster)) {
			return true;
		}

		return false;
	}

	@Override
	public ContractMaster getWrappedModel() {
		return _contractMaster;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _contractMaster.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _contractMaster.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_contractMaster.resetOriginalValues();
	}

	private final ContractMaster _contractMaster;
}