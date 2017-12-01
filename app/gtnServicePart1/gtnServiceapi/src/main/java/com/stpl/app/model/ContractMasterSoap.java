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
public class ContractMasterSoap implements Serializable {
	public static ContractMasterSoap toSoapModel(ContractMaster model) {
		ContractMasterSoap soapModel = new ContractMasterSoap();

		soapModel.setProposalEndDate(model.getProposalEndDate());
		soapModel.setCreatedDate(model.getCreatedDate());
		soapModel.setRenegotiationEndDate(model.getRenegotiationEndDate());
		soapModel.setOutsideAdditionalName(model.getOutsideAdditionalName());
		soapModel.setEndDate(model.getEndDate());
		soapModel.setManfCompanyMasterSid(model.getManfCompanyMasterSid());
		soapModel.setRenegotiationStartDate(model.getRenegotiationStartDate());
		soapModel.setInsideAuthor(model.getInsideAuthor());
		soapModel.setAdvanceNoticeDays(model.getAdvanceNoticeDays());
		soapModel.setOutsideOwner(model.getOutsideOwner());
		soapModel.setMostFavoredNation(model.getMostFavoredNation());
		soapModel.setInsideAdditionalPhone(model.getInsideAdditionalPhone());
		soapModel.setOriginalStartDate(model.getOriginalStartDate());
		soapModel.setCreatedBy(model.getCreatedBy());
		soapModel.setProposalStartDate(model.getProposalStartDate());
		soapModel.setContractTradeClass(model.getContractTradeClass());
		soapModel.setOutsideAdditional(model.getOutsideAdditional());
		soapModel.setProcessStatus(model.getProcessStatus());
		soapModel.setInsideAdditionalName(model.getInsideAdditionalName());
		soapModel.setContractMasterSid(model.getContractMasterSid());
		soapModel.setContractStatus(model.getContractStatus());
		soapModel.setContractId(model.getContractId());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setContractType(model.getContractType());
		soapModel.setAwardStatus(model.getAwardStatus());
		soapModel.setInsideOwner(model.getInsideOwner());
		soapModel.setSource(model.getSource());
		soapModel.setShippingTerms(model.getShippingTerms());
		soapModel.setPriceEscalationClause(model.getPriceEscalationClause());
		soapModel.setModifiedBy(model.getModifiedBy());
		soapModel.setOutsideAdditionalPhone(model.getOutsideAdditionalPhone());
		soapModel.setTerm(model.getTerm());
		soapModel.setContractNo(model.getContractNo());
		soapModel.setBatchId(model.getBatchId());
		soapModel.setDocumentClass(model.getDocumentClass());
		soapModel.setOriginalEndDate(model.getOriginalEndDate());
		soapModel.setPaymentTerms(model.getPaymentTerms());
		soapModel.setInsideAdditional(model.getInsideAdditional());
		soapModel.setAffiliatedContractInfo(model.getAffiliatedContractInfo());
		soapModel.setCategory(model.getCategory());
		soapModel.setOutsidePhone(model.getOutsidePhone());
		soapModel.setPriceprotectionStartDate(model.getPriceprotectionStartDate());
		soapModel.setPriceprotectionEndDate(model.getPriceprotectionEndDate());
		soapModel.setDocumentType(model.getDocumentType());
		soapModel.setExemptFromLowPrice(model.getExemptFromLowPrice());
		soapModel.setOrganizationKey(model.getOrganizationKey());
		soapModel.setCurrency(model.getCurrency());
		soapModel.setInsidePhone(model.getInsidePhone());
		soapModel.setBunitCompanyMasterSid(model.getBunitCompanyMasterSid());
		soapModel.setOutsideAuthor(model.getOutsideAuthor());
		soapModel.setContHoldCompanyMasterSid(model.getContHoldCompanyMasterSid());
		soapModel.setStartDate(model.getStartDate());
		soapModel.setContractName(model.getContractName());
		soapModel.setLastUpdatedDate(model.getLastUpdatedDate());
		soapModel.setRecordLockStatus(model.getRecordLockStatus());
		soapModel.setPriceResetIndicator(model.getPriceResetIndicator());
		soapModel.setMinimumOrder(model.getMinimumOrder());
		soapModel.setCancellationClause(model.getCancellationClause());
		soapModel.setInboundStatus(model.getInboundStatus());
		soapModel.setInternalNotes(model.getInternalNotes());

		return soapModel;
	}

	public static ContractMasterSoap[] toSoapModels(ContractMaster[] models) {
		ContractMasterSoap[] soapModels = new ContractMasterSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ContractMasterSoap[][] toSoapModels(ContractMaster[][] models) {
		ContractMasterSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ContractMasterSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ContractMasterSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ContractMasterSoap[] toSoapModels(List<ContractMaster> models) {
		List<ContractMasterSoap> soapModels = new ArrayList<ContractMasterSoap>(models.size());

		for (ContractMaster model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ContractMasterSoap[soapModels.size()]);
	}

	public ContractMasterSoap() {
	}

	public int getPrimaryKey() {
		return _contractMasterSid;
	}

	public void setPrimaryKey(int pk) {
		setContractMasterSid(pk);
	}

	public Date getProposalEndDate() {
		return _proposalEndDate;
	}

	public void setProposalEndDate(Date proposalEndDate) {
		_proposalEndDate = proposalEndDate;
	}

	public Date getCreatedDate() {
		return _createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		_createdDate = createdDate;
	}

	public Date getRenegotiationEndDate() {
		return _renegotiationEndDate;
	}

	public void setRenegotiationEndDate(Date renegotiationEndDate) {
		_renegotiationEndDate = renegotiationEndDate;
	}

	public String getOutsideAdditionalName() {
		return _outsideAdditionalName;
	}

	public void setOutsideAdditionalName(String outsideAdditionalName) {
		_outsideAdditionalName = outsideAdditionalName;
	}

	public Date getEndDate() {
		return _endDate;
	}

	public void setEndDate(Date endDate) {
		_endDate = endDate;
	}

	public String getManfCompanyMasterSid() {
		return _manfCompanyMasterSid;
	}

	public void setManfCompanyMasterSid(String manfCompanyMasterSid) {
		_manfCompanyMasterSid = manfCompanyMasterSid;
	}

	public Date getRenegotiationStartDate() {
		return _renegotiationStartDate;
	}

	public void setRenegotiationStartDate(Date renegotiationStartDate) {
		_renegotiationStartDate = renegotiationStartDate;
	}

	public String getInsideAuthor() {
		return _insideAuthor;
	}

	public void setInsideAuthor(String insideAuthor) {
		_insideAuthor = insideAuthor;
	}

	public double getAdvanceNoticeDays() {
		return _advanceNoticeDays;
	}

	public void setAdvanceNoticeDays(double advanceNoticeDays) {
		_advanceNoticeDays = advanceNoticeDays;
	}

	public String getOutsideOwner() {
		return _outsideOwner;
	}

	public void setOutsideOwner(String outsideOwner) {
		_outsideOwner = outsideOwner;
	}

	public String getMostFavoredNation() {
		return _mostFavoredNation;
	}

	public void setMostFavoredNation(String mostFavoredNation) {
		_mostFavoredNation = mostFavoredNation;
	}

	public String getInsideAdditionalPhone() {
		return _insideAdditionalPhone;
	}

	public void setInsideAdditionalPhone(String insideAdditionalPhone) {
		_insideAdditionalPhone = insideAdditionalPhone;
	}

	public Date getOriginalStartDate() {
		return _originalStartDate;
	}

	public void setOriginalStartDate(Date originalStartDate) {
		_originalStartDate = originalStartDate;
	}

	public int getCreatedBy() {
		return _createdBy;
	}

	public void setCreatedBy(int createdBy) {
		_createdBy = createdBy;
	}

	public Date getProposalStartDate() {
		return _proposalStartDate;
	}

	public void setProposalStartDate(Date proposalStartDate) {
		_proposalStartDate = proposalStartDate;
	}

	public int getContractTradeClass() {
		return _contractTradeClass;
	}

	public void setContractTradeClass(int contractTradeClass) {
		_contractTradeClass = contractTradeClass;
	}

	public String getOutsideAdditional() {
		return _outsideAdditional;
	}

	public void setOutsideAdditional(String outsideAdditional) {
		_outsideAdditional = outsideAdditional;
	}

	public boolean getProcessStatus() {
		return _processStatus;
	}

	public boolean isProcessStatus() {
		return _processStatus;
	}

	public void setProcessStatus(boolean processStatus) {
		_processStatus = processStatus;
	}

	public String getInsideAdditionalName() {
		return _insideAdditionalName;
	}

	public void setInsideAdditionalName(String insideAdditionalName) {
		_insideAdditionalName = insideAdditionalName;
	}

	public int getContractMasterSid() {
		return _contractMasterSid;
	}

	public void setContractMasterSid(int contractMasterSid) {
		_contractMasterSid = contractMasterSid;
	}

	public int getContractStatus() {
		return _contractStatus;
	}

	public void setContractStatus(int contractStatus) {
		_contractStatus = contractStatus;
	}

	public String getContractId() {
		return _contractId;
	}

	public void setContractId(String contractId) {
		_contractId = contractId;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public int getContractType() {
		return _contractType;
	}

	public void setContractType(int contractType) {
		_contractType = contractType;
	}

	public int getAwardStatus() {
		return _awardStatus;
	}

	public void setAwardStatus(int awardStatus) {
		_awardStatus = awardStatus;
	}

	public String getInsideOwner() {
		return _insideOwner;
	}

	public void setInsideOwner(String insideOwner) {
		_insideOwner = insideOwner;
	}

	public String getSource() {
		return _source;
	}

	public void setSource(String source) {
		_source = source;
	}

	public String getShippingTerms() {
		return _shippingTerms;
	}

	public void setShippingTerms(String shippingTerms) {
		_shippingTerms = shippingTerms;
	}

	public String getPriceEscalationClause() {
		return _priceEscalationClause;
	}

	public void setPriceEscalationClause(String priceEscalationClause) {
		_priceEscalationClause = priceEscalationClause;
	}

	public int getModifiedBy() {
		return _modifiedBy;
	}

	public void setModifiedBy(int modifiedBy) {
		_modifiedBy = modifiedBy;
	}

	public String getOutsideAdditionalPhone() {
		return _outsideAdditionalPhone;
	}

	public void setOutsideAdditionalPhone(String outsideAdditionalPhone) {
		_outsideAdditionalPhone = outsideAdditionalPhone;
	}

	public int getTerm() {
		return _term;
	}

	public void setTerm(int term) {
		_term = term;
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

	public int getDocumentClass() {
		return _documentClass;
	}

	public void setDocumentClass(int documentClass) {
		_documentClass = documentClass;
	}

	public Date getOriginalEndDate() {
		return _originalEndDate;
	}

	public void setOriginalEndDate(Date originalEndDate) {
		_originalEndDate = originalEndDate;
	}

	public int getPaymentTerms() {
		return _paymentTerms;
	}

	public void setPaymentTerms(int paymentTerms) {
		_paymentTerms = paymentTerms;
	}

	public String getInsideAdditional() {
		return _insideAdditional;
	}

	public void setInsideAdditional(String insideAdditional) {
		_insideAdditional = insideAdditional;
	}

	public String getAffiliatedContractInfo() {
		return _affiliatedContractInfo;
	}

	public void setAffiliatedContractInfo(String affiliatedContractInfo) {
		_affiliatedContractInfo = affiliatedContractInfo;
	}

	public String getCategory() {
		return _category;
	}

	public void setCategory(String category) {
		_category = category;
	}

	public String getOutsidePhone() {
		return _outsidePhone;
	}

	public void setOutsidePhone(String outsidePhone) {
		_outsidePhone = outsidePhone;
	}

	public Date getPriceprotectionStartDate() {
		return _priceprotectionStartDate;
	}

	public void setPriceprotectionStartDate(Date priceprotectionStartDate) {
		_priceprotectionStartDate = priceprotectionStartDate;
	}

	public Date getPriceprotectionEndDate() {
		return _priceprotectionEndDate;
	}

	public void setPriceprotectionEndDate(Date priceprotectionEndDate) {
		_priceprotectionEndDate = priceprotectionEndDate;
	}

	public int getDocumentType() {
		return _documentType;
	}

	public void setDocumentType(int documentType) {
		_documentType = documentType;
	}

	public String getExemptFromLowPrice() {
		return _exemptFromLowPrice;
	}

	public void setExemptFromLowPrice(String exemptFromLowPrice) {
		_exemptFromLowPrice = exemptFromLowPrice;
	}

	public String getOrganizationKey() {
		return _organizationKey;
	}

	public void setOrganizationKey(String organizationKey) {
		_organizationKey = organizationKey;
	}

	public String getCurrency() {
		return _currency;
	}

	public void setCurrency(String currency) {
		_currency = currency;
	}

	public String getInsidePhone() {
		return _insidePhone;
	}

	public void setInsidePhone(String insidePhone) {
		_insidePhone = insidePhone;
	}

	public String getBunitCompanyMasterSid() {
		return _bunitCompanyMasterSid;
	}

	public void setBunitCompanyMasterSid(String bunitCompanyMasterSid) {
		_bunitCompanyMasterSid = bunitCompanyMasterSid;
	}

	public String getOutsideAuthor() {
		return _outsideAuthor;
	}

	public void setOutsideAuthor(String outsideAuthor) {
		_outsideAuthor = outsideAuthor;
	}

	public String getContHoldCompanyMasterSid() {
		return _contHoldCompanyMasterSid;
	}

	public void setContHoldCompanyMasterSid(String contHoldCompanyMasterSid) {
		_contHoldCompanyMasterSid = contHoldCompanyMasterSid;
	}

	public Date getStartDate() {
		return _startDate;
	}

	public void setStartDate(Date startDate) {
		_startDate = startDate;
	}

	public String getContractName() {
		return _contractName;
	}

	public void setContractName(String contractName) {
		_contractName = contractName;
	}

	public Date getLastUpdatedDate() {
		return _lastUpdatedDate;
	}

	public void setLastUpdatedDate(Date lastUpdatedDate) {
		_lastUpdatedDate = lastUpdatedDate;
	}

	public boolean getRecordLockStatus() {
		return _recordLockStatus;
	}

	public boolean isRecordLockStatus() {
		return _recordLockStatus;
	}

	public void setRecordLockStatus(boolean recordLockStatus) {
		_recordLockStatus = recordLockStatus;
	}

	public String getPriceResetIndicator() {
		return _priceResetIndicator;
	}

	public void setPriceResetIndicator(String priceResetIndicator) {
		_priceResetIndicator = priceResetIndicator;
	}

	public String getMinimumOrder() {
		return _minimumOrder;
	}

	public void setMinimumOrder(String minimumOrder) {
		_minimumOrder = minimumOrder;
	}

	public String getCancellationClause() {
		return _cancellationClause;
	}

	public void setCancellationClause(String cancellationClause) {
		_cancellationClause = cancellationClause;
	}

	public String getInboundStatus() {
		return _inboundStatus;
	}

	public void setInboundStatus(String inboundStatus) {
		_inboundStatus = inboundStatus;
	}

	public String getInternalNotes() {
		return _internalNotes;
	}

	public void setInternalNotes(String internalNotes) {
		_internalNotes = internalNotes;
	}

	private Date _proposalEndDate;
	private Date _createdDate;
	private Date _renegotiationEndDate;
	private String _outsideAdditionalName;
	private Date _endDate;
	private String _manfCompanyMasterSid;
	private Date _renegotiationStartDate;
	private String _insideAuthor;
	private double _advanceNoticeDays;
	private String _outsideOwner;
	private String _mostFavoredNation;
	private String _insideAdditionalPhone;
	private Date _originalStartDate;
	private int _createdBy;
	private Date _proposalStartDate;
	private int _contractTradeClass;
	private String _outsideAdditional;
	private boolean _processStatus;
	private String _insideAdditionalName;
	private int _contractMasterSid;
	private int _contractStatus;
	private String _contractId;
	private Date _modifiedDate;
	private int _contractType;
	private int _awardStatus;
	private String _insideOwner;
	private String _source;
	private String _shippingTerms;
	private String _priceEscalationClause;
	private int _modifiedBy;
	private String _outsideAdditionalPhone;
	private int _term;
	private String _contractNo;
	private String _batchId;
	private int _documentClass;
	private Date _originalEndDate;
	private int _paymentTerms;
	private String _insideAdditional;
	private String _affiliatedContractInfo;
	private String _category;
	private String _outsidePhone;
	private Date _priceprotectionStartDate;
	private Date _priceprotectionEndDate;
	private int _documentType;
	private String _exemptFromLowPrice;
	private String _organizationKey;
	private String _currency;
	private String _insidePhone;
	private String _bunitCompanyMasterSid;
	private String _outsideAuthor;
	private String _contHoldCompanyMasterSid;
	private Date _startDate;
	private String _contractName;
	private Date _lastUpdatedDate;
	private boolean _recordLockStatus;
	private String _priceResetIndicator;
	private String _minimumOrder;
	private String _cancellationClause;
	private String _inboundStatus;
	private String _internalNotes;
}