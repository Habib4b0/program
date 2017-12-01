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
 * This class is a wrapper for {@link SalesMaster}.
 * </p>
 *
 * @author
 * @see SalesMaster
 * @generated
 */
@ProviderType
public class SalesMasterWrapper implements SalesMaster,
	ModelWrapper<SalesMaster> {
	public SalesMasterWrapper(SalesMaster salesMaster) {
		_salesMaster = salesMaster;
	}

	@Override
	public Class<?> getModelClass() {
		return SalesMaster.class;
	}

	@Override
	public String getModelClassName() {
		return SalesMaster.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("itemNo", getItemNo());
		attributes.put("recordSequence", getRecordSequence());
		attributes.put("quantity", getQuantity());
		attributes.put("accountId", getAccountId());
		attributes.put("createdDate", getCreatedDate());
		attributes.put("identifierCodeQualifier", getIdentifierCodeQualifier());
		attributes.put("isActive", getIsActive());
		attributes.put("marketId", getMarketId());
		attributes.put("invoiceDate", getInvoiceDate());
		attributes.put("accountName", getAccountName());
		attributes.put("docType", getDocType());
		attributes.put("orderReceivedDate", getOrderReceivedDate());
		attributes.put("amount", getAmount());
		attributes.put("salesMasterSid", getSalesMasterSid());
		attributes.put("orderSubtype", getOrderSubtype());
		attributes.put("createdBy", getCreatedBy());
		attributes.put("price", getPrice());
		attributes.put("uploadDate", getUploadDate());
		attributes.put("itemId", getItemId());
		attributes.put("priceAdjustmentName", getPriceAdjustmentName());
		attributes.put("itemCodeQualifier", getItemCodeQualifier());
		attributes.put("contractId", getContractId());
		attributes.put("itemUom", getItemUom());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("customerSubtype", getCustomerSubtype());
		attributes.put("provisionId", getProvisionId());
		attributes.put("wholesaleOwnerId", getWholesaleOwnerId());
		attributes.put("source", getSource());
		attributes.put("accountNo", getAccountNo());
		attributes.put("lotNo", getLotNo());
		attributes.put("parentItemId", getParentItemId());
		attributes.put("customerCompanyCode", getCustomerCompanyCode());
		attributes.put("analysisCode", getAnalysisCode());
		attributes.put("accountType", getAccountType());
		attributes.put("modifiedBy", getModifiedBy());
		attributes.put("contractNo", getContractNo());
		attributes.put("batchId", getBatchId());
		attributes.put("brandId", getBrandId());
		attributes.put("salesId", getSalesId());
		attributes.put("companyStringId", getCompanyStringId());
		attributes.put("organizationKey", getOrganizationKey());
		attributes.put("itemParentNo", getItemParentNo());
		attributes.put("invoiceNumber", getInvoiceNumber());
		attributes.put("orderType", getOrderType());
		attributes.put("recordLockStatus", getRecordLockStatus());
		attributes.put("divisionId", getDivisionId());
		attributes.put("invoiceLineNumber", getInvoiceLineNumber());
		attributes.put("orderNumber", getOrderNumber());
		attributes.put("inboundStatus", getInboundStatus());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String itemNo = (String)attributes.get("itemNo");

		if (itemNo != null) {
			setItemNo(itemNo);
		}

		Integer recordSequence = (Integer)attributes.get("recordSequence");

		if (recordSequence != null) {
			setRecordSequence(recordSequence);
		}

		Double quantity = (Double)attributes.get("quantity");

		if (quantity != null) {
			setQuantity(quantity);
		}

		String accountId = (String)attributes.get("accountId");

		if (accountId != null) {
			setAccountId(accountId);
		}

		Date createdDate = (Date)attributes.get("createdDate");

		if (createdDate != null) {
			setCreatedDate(createdDate);
		}

		String identifierCodeQualifier = (String)attributes.get(
				"identifierCodeQualifier");

		if (identifierCodeQualifier != null) {
			setIdentifierCodeQualifier(identifierCodeQualifier);
		}

		String isActive = (String)attributes.get("isActive");

		if (isActive != null) {
			setIsActive(isActive);
		}

		String marketId = (String)attributes.get("marketId");

		if (marketId != null) {
			setMarketId(marketId);
		}

		Date invoiceDate = (Date)attributes.get("invoiceDate");

		if (invoiceDate != null) {
			setInvoiceDate(invoiceDate);
		}

		String accountName = (String)attributes.get("accountName");

		if (accountName != null) {
			setAccountName(accountName);
		}

		String docType = (String)attributes.get("docType");

		if (docType != null) {
			setDocType(docType);
		}

		Date orderReceivedDate = (Date)attributes.get("orderReceivedDate");

		if (orderReceivedDate != null) {
			setOrderReceivedDate(orderReceivedDate);
		}

		Double amount = (Double)attributes.get("amount");

		if (amount != null) {
			setAmount(amount);
		}

		Integer salesMasterSid = (Integer)attributes.get("salesMasterSid");

		if (salesMasterSid != null) {
			setSalesMasterSid(salesMasterSid);
		}

		String orderSubtype = (String)attributes.get("orderSubtype");

		if (orderSubtype != null) {
			setOrderSubtype(orderSubtype);
		}

		Integer createdBy = (Integer)attributes.get("createdBy");

		if (createdBy != null) {
			setCreatedBy(createdBy);
		}

		Double price = (Double)attributes.get("price");

		if (price != null) {
			setPrice(price);
		}

		Date uploadDate = (Date)attributes.get("uploadDate");

		if (uploadDate != null) {
			setUploadDate(uploadDate);
		}

		String itemId = (String)attributes.get("itemId");

		if (itemId != null) {
			setItemId(itemId);
		}

		String priceAdjustmentName = (String)attributes.get(
				"priceAdjustmentName");

		if (priceAdjustmentName != null) {
			setPriceAdjustmentName(priceAdjustmentName);
		}

		String itemCodeQualifier = (String)attributes.get("itemCodeQualifier");

		if (itemCodeQualifier != null) {
			setItemCodeQualifier(itemCodeQualifier);
		}

		String contractId = (String)attributes.get("contractId");

		if (contractId != null) {
			setContractId(contractId);
		}

		String itemUom = (String)attributes.get("itemUom");

		if (itemUom != null) {
			setItemUom(itemUom);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String customerSubtype = (String)attributes.get("customerSubtype");

		if (customerSubtype != null) {
			setCustomerSubtype(customerSubtype);
		}

		Integer provisionId = (Integer)attributes.get("provisionId");

		if (provisionId != null) {
			setProvisionId(provisionId);
		}

		String wholesaleOwnerId = (String)attributes.get("wholesaleOwnerId");

		if (wholesaleOwnerId != null) {
			setWholesaleOwnerId(wholesaleOwnerId);
		}

		String source = (String)attributes.get("source");

		if (source != null) {
			setSource(source);
		}

		String accountNo = (String)attributes.get("accountNo");

		if (accountNo != null) {
			setAccountNo(accountNo);
		}

		String lotNo = (String)attributes.get("lotNo");

		if (lotNo != null) {
			setLotNo(lotNo);
		}

		String parentItemId = (String)attributes.get("parentItemId");

		if (parentItemId != null) {
			setParentItemId(parentItemId);
		}

		String customerCompanyCode = (String)attributes.get(
				"customerCompanyCode");

		if (customerCompanyCode != null) {
			setCustomerCompanyCode(customerCompanyCode);
		}

		String analysisCode = (String)attributes.get("analysisCode");

		if (analysisCode != null) {
			setAnalysisCode(analysisCode);
		}

		String accountType = (String)attributes.get("accountType");

		if (accountType != null) {
			setAccountType(accountType);
		}

		Integer modifiedBy = (Integer)attributes.get("modifiedBy");

		if (modifiedBy != null) {
			setModifiedBy(modifiedBy);
		}

		String contractNo = (String)attributes.get("contractNo");

		if (contractNo != null) {
			setContractNo(contractNo);
		}

		String batchId = (String)attributes.get("batchId");

		if (batchId != null) {
			setBatchId(batchId);
		}

		String brandId = (String)attributes.get("brandId");

		if (brandId != null) {
			setBrandId(brandId);
		}

		String salesId = (String)attributes.get("salesId");

		if (salesId != null) {
			setSalesId(salesId);
		}

		String companyStringId = (String)attributes.get("companyStringId");

		if (companyStringId != null) {
			setCompanyStringId(companyStringId);
		}

		String organizationKey = (String)attributes.get("organizationKey");

		if (organizationKey != null) {
			setOrganizationKey(organizationKey);
		}

		String itemParentNo = (String)attributes.get("itemParentNo");

		if (itemParentNo != null) {
			setItemParentNo(itemParentNo);
		}

		String invoiceNumber = (String)attributes.get("invoiceNumber");

		if (invoiceNumber != null) {
			setInvoiceNumber(invoiceNumber);
		}

		String orderType = (String)attributes.get("orderType");

		if (orderType != null) {
			setOrderType(orderType);
		}

		Boolean recordLockStatus = (Boolean)attributes.get("recordLockStatus");

		if (recordLockStatus != null) {
			setRecordLockStatus(recordLockStatus);
		}

		String divisionId = (String)attributes.get("divisionId");

		if (divisionId != null) {
			setDivisionId(divisionId);
		}

		String invoiceLineNumber = (String)attributes.get("invoiceLineNumber");

		if (invoiceLineNumber != null) {
			setInvoiceLineNumber(invoiceLineNumber);
		}

		String orderNumber = (String)attributes.get("orderNumber");

		if (orderNumber != null) {
			setOrderNumber(orderNumber);
		}

		String inboundStatus = (String)attributes.get("inboundStatus");

		if (inboundStatus != null) {
			setInboundStatus(inboundStatus);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new SalesMasterWrapper((SalesMaster)_salesMaster.clone());
	}

	@Override
	public int compareTo(SalesMaster salesMaster) {
		return _salesMaster.compareTo(salesMaster);
	}

	/**
	* Returns the account ID of this sales master.
	*
	* @return the account ID of this sales master
	*/
	@Override
	public java.lang.String getAccountId() {
		return _salesMaster.getAccountId();
	}

	/**
	* Returns the account name of this sales master.
	*
	* @return the account name of this sales master
	*/
	@Override
	public java.lang.String getAccountName() {
		return _salesMaster.getAccountName();
	}

	/**
	* Returns the account no of this sales master.
	*
	* @return the account no of this sales master
	*/
	@Override
	public java.lang.String getAccountNo() {
		return _salesMaster.getAccountNo();
	}

	/**
	* Returns the account type of this sales master.
	*
	* @return the account type of this sales master
	*/
	@Override
	public java.lang.String getAccountType() {
		return _salesMaster.getAccountType();
	}

	/**
	* Returns the amount of this sales master.
	*
	* @return the amount of this sales master
	*/
	@Override
	public double getAmount() {
		return _salesMaster.getAmount();
	}

	/**
	* Returns the analysis code of this sales master.
	*
	* @return the analysis code of this sales master
	*/
	@Override
	public java.lang.String getAnalysisCode() {
		return _salesMaster.getAnalysisCode();
	}

	/**
	* Returns the batch ID of this sales master.
	*
	* @return the batch ID of this sales master
	*/
	@Override
	public java.lang.String getBatchId() {
		return _salesMaster.getBatchId();
	}

	/**
	* Returns the brand ID of this sales master.
	*
	* @return the brand ID of this sales master
	*/
	@Override
	public java.lang.String getBrandId() {
		return _salesMaster.getBrandId();
	}

	/**
	* Returns the company string ID of this sales master.
	*
	* @return the company string ID of this sales master
	*/
	@Override
	public java.lang.String getCompanyStringId() {
		return _salesMaster.getCompanyStringId();
	}

	/**
	* Returns the contract ID of this sales master.
	*
	* @return the contract ID of this sales master
	*/
	@Override
	public java.lang.String getContractId() {
		return _salesMaster.getContractId();
	}

	/**
	* Returns the contract no of this sales master.
	*
	* @return the contract no of this sales master
	*/
	@Override
	public java.lang.String getContractNo() {
		return _salesMaster.getContractNo();
	}

	/**
	* Returns the created by of this sales master.
	*
	* @return the created by of this sales master
	*/
	@Override
	public int getCreatedBy() {
		return _salesMaster.getCreatedBy();
	}

	/**
	* Returns the created date of this sales master.
	*
	* @return the created date of this sales master
	*/
	@Override
	public Date getCreatedDate() {
		return _salesMaster.getCreatedDate();
	}

	/**
	* Returns the customer company code of this sales master.
	*
	* @return the customer company code of this sales master
	*/
	@Override
	public java.lang.String getCustomerCompanyCode() {
		return _salesMaster.getCustomerCompanyCode();
	}

	/**
	* Returns the customer subtype of this sales master.
	*
	* @return the customer subtype of this sales master
	*/
	@Override
	public java.lang.String getCustomerSubtype() {
		return _salesMaster.getCustomerSubtype();
	}

	/**
	* Returns the division ID of this sales master.
	*
	* @return the division ID of this sales master
	*/
	@Override
	public java.lang.String getDivisionId() {
		return _salesMaster.getDivisionId();
	}

	/**
	* Returns the doc type of this sales master.
	*
	* @return the doc type of this sales master
	*/
	@Override
	public java.lang.String getDocType() {
		return _salesMaster.getDocType();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _salesMaster.getExpandoBridge();
	}

	/**
	* Returns the identifier code qualifier of this sales master.
	*
	* @return the identifier code qualifier of this sales master
	*/
	@Override
	public java.lang.String getIdentifierCodeQualifier() {
		return _salesMaster.getIdentifierCodeQualifier();
	}

	/**
	* Returns the inbound status of this sales master.
	*
	* @return the inbound status of this sales master
	*/
	@Override
	public java.lang.String getInboundStatus() {
		return _salesMaster.getInboundStatus();
	}

	/**
	* Returns the invoice date of this sales master.
	*
	* @return the invoice date of this sales master
	*/
	@Override
	public Date getInvoiceDate() {
		return _salesMaster.getInvoiceDate();
	}

	/**
	* Returns the invoice line number of this sales master.
	*
	* @return the invoice line number of this sales master
	*/
	@Override
	public java.lang.String getInvoiceLineNumber() {
		return _salesMaster.getInvoiceLineNumber();
	}

	/**
	* Returns the invoice number of this sales master.
	*
	* @return the invoice number of this sales master
	*/
	@Override
	public java.lang.String getInvoiceNumber() {
		return _salesMaster.getInvoiceNumber();
	}

	/**
	* Returns the is active of this sales master.
	*
	* @return the is active of this sales master
	*/
	@Override
	public java.lang.String getIsActive() {
		return _salesMaster.getIsActive();
	}

	/**
	* Returns the item code qualifier of this sales master.
	*
	* @return the item code qualifier of this sales master
	*/
	@Override
	public java.lang.String getItemCodeQualifier() {
		return _salesMaster.getItemCodeQualifier();
	}

	/**
	* Returns the item ID of this sales master.
	*
	* @return the item ID of this sales master
	*/
	@Override
	public java.lang.String getItemId() {
		return _salesMaster.getItemId();
	}

	/**
	* Returns the item no of this sales master.
	*
	* @return the item no of this sales master
	*/
	@Override
	public java.lang.String getItemNo() {
		return _salesMaster.getItemNo();
	}

	/**
	* Returns the item parent no of this sales master.
	*
	* @return the item parent no of this sales master
	*/
	@Override
	public java.lang.String getItemParentNo() {
		return _salesMaster.getItemParentNo();
	}

	/**
	* Returns the item uom of this sales master.
	*
	* @return the item uom of this sales master
	*/
	@Override
	public java.lang.String getItemUom() {
		return _salesMaster.getItemUom();
	}

	/**
	* Returns the lot no of this sales master.
	*
	* @return the lot no of this sales master
	*/
	@Override
	public java.lang.String getLotNo() {
		return _salesMaster.getLotNo();
	}

	/**
	* Returns the market ID of this sales master.
	*
	* @return the market ID of this sales master
	*/
	@Override
	public java.lang.String getMarketId() {
		return _salesMaster.getMarketId();
	}

	/**
	* Returns the modified by of this sales master.
	*
	* @return the modified by of this sales master
	*/
	@Override
	public int getModifiedBy() {
		return _salesMaster.getModifiedBy();
	}

	/**
	* Returns the modified date of this sales master.
	*
	* @return the modified date of this sales master
	*/
	@Override
	public Date getModifiedDate() {
		return _salesMaster.getModifiedDate();
	}

	/**
	* Returns the order number of this sales master.
	*
	* @return the order number of this sales master
	*/
	@Override
	public java.lang.String getOrderNumber() {
		return _salesMaster.getOrderNumber();
	}

	/**
	* Returns the order received date of this sales master.
	*
	* @return the order received date of this sales master
	*/
	@Override
	public Date getOrderReceivedDate() {
		return _salesMaster.getOrderReceivedDate();
	}

	/**
	* Returns the order subtype of this sales master.
	*
	* @return the order subtype of this sales master
	*/
	@Override
	public java.lang.String getOrderSubtype() {
		return _salesMaster.getOrderSubtype();
	}

	/**
	* Returns the order type of this sales master.
	*
	* @return the order type of this sales master
	*/
	@Override
	public java.lang.String getOrderType() {
		return _salesMaster.getOrderType();
	}

	/**
	* Returns the organization key of this sales master.
	*
	* @return the organization key of this sales master
	*/
	@Override
	public java.lang.String getOrganizationKey() {
		return _salesMaster.getOrganizationKey();
	}

	/**
	* Returns the parent item ID of this sales master.
	*
	* @return the parent item ID of this sales master
	*/
	@Override
	public java.lang.String getParentItemId() {
		return _salesMaster.getParentItemId();
	}

	/**
	* Returns the price of this sales master.
	*
	* @return the price of this sales master
	*/
	@Override
	public double getPrice() {
		return _salesMaster.getPrice();
	}

	/**
	* Returns the price adjustment name of this sales master.
	*
	* @return the price adjustment name of this sales master
	*/
	@Override
	public java.lang.String getPriceAdjustmentName() {
		return _salesMaster.getPriceAdjustmentName();
	}

	/**
	* Returns the primary key of this sales master.
	*
	* @return the primary key of this sales master
	*/
	@Override
	public int getPrimaryKey() {
		return _salesMaster.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _salesMaster.getPrimaryKeyObj();
	}

	/**
	* Returns the provision ID of this sales master.
	*
	* @return the provision ID of this sales master
	*/
	@Override
	public int getProvisionId() {
		return _salesMaster.getProvisionId();
	}

	/**
	* Returns the quantity of this sales master.
	*
	* @return the quantity of this sales master
	*/
	@Override
	public double getQuantity() {
		return _salesMaster.getQuantity();
	}

	/**
	* Returns the record lock status of this sales master.
	*
	* @return the record lock status of this sales master
	*/
	@Override
	public boolean getRecordLockStatus() {
		return _salesMaster.getRecordLockStatus();
	}

	/**
	* Returns the record sequence of this sales master.
	*
	* @return the record sequence of this sales master
	*/
	@Override
	public int getRecordSequence() {
		return _salesMaster.getRecordSequence();
	}

	/**
	* Returns the sales ID of this sales master.
	*
	* @return the sales ID of this sales master
	*/
	@Override
	public java.lang.String getSalesId() {
		return _salesMaster.getSalesId();
	}

	/**
	* Returns the sales master sid of this sales master.
	*
	* @return the sales master sid of this sales master
	*/
	@Override
	public int getSalesMasterSid() {
		return _salesMaster.getSalesMasterSid();
	}

	/**
	* Returns the source of this sales master.
	*
	* @return the source of this sales master
	*/
	@Override
	public java.lang.String getSource() {
		return _salesMaster.getSource();
	}

	/**
	* Returns the upload date of this sales master.
	*
	* @return the upload date of this sales master
	*/
	@Override
	public Date getUploadDate() {
		return _salesMaster.getUploadDate();
	}

	/**
	* Returns the wholesale owner ID of this sales master.
	*
	* @return the wholesale owner ID of this sales master
	*/
	@Override
	public java.lang.String getWholesaleOwnerId() {
		return _salesMaster.getWholesaleOwnerId();
	}

	@Override
	public int hashCode() {
		return _salesMaster.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _salesMaster.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _salesMaster.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _salesMaster.isNew();
	}

	/**
	* Returns <code>true</code> if this sales master is record lock status.
	*
	* @return <code>true</code> if this sales master is record lock status; <code>false</code> otherwise
	*/
	@Override
	public boolean isRecordLockStatus() {
		return _salesMaster.isRecordLockStatus();
	}

	@Override
	public void persist() {
		_salesMaster.persist();
	}

	/**
	* Sets the account ID of this sales master.
	*
	* @param accountId the account ID of this sales master
	*/
	@Override
	public void setAccountId(java.lang.String accountId) {
		_salesMaster.setAccountId(accountId);
	}

	/**
	* Sets the account name of this sales master.
	*
	* @param accountName the account name of this sales master
	*/
	@Override
	public void setAccountName(java.lang.String accountName) {
		_salesMaster.setAccountName(accountName);
	}

	/**
	* Sets the account no of this sales master.
	*
	* @param accountNo the account no of this sales master
	*/
	@Override
	public void setAccountNo(java.lang.String accountNo) {
		_salesMaster.setAccountNo(accountNo);
	}

	/**
	* Sets the account type of this sales master.
	*
	* @param accountType the account type of this sales master
	*/
	@Override
	public void setAccountType(java.lang.String accountType) {
		_salesMaster.setAccountType(accountType);
	}

	/**
	* Sets the amount of this sales master.
	*
	* @param amount the amount of this sales master
	*/
	@Override
	public void setAmount(double amount) {
		_salesMaster.setAmount(amount);
	}

	/**
	* Sets the analysis code of this sales master.
	*
	* @param analysisCode the analysis code of this sales master
	*/
	@Override
	public void setAnalysisCode(java.lang.String analysisCode) {
		_salesMaster.setAnalysisCode(analysisCode);
	}

	/**
	* Sets the batch ID of this sales master.
	*
	* @param batchId the batch ID of this sales master
	*/
	@Override
	public void setBatchId(java.lang.String batchId) {
		_salesMaster.setBatchId(batchId);
	}

	/**
	* Sets the brand ID of this sales master.
	*
	* @param brandId the brand ID of this sales master
	*/
	@Override
	public void setBrandId(java.lang.String brandId) {
		_salesMaster.setBrandId(brandId);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_salesMaster.setCachedModel(cachedModel);
	}

	/**
	* Sets the company string ID of this sales master.
	*
	* @param companyStringId the company string ID of this sales master
	*/
	@Override
	public void setCompanyStringId(java.lang.String companyStringId) {
		_salesMaster.setCompanyStringId(companyStringId);
	}

	/**
	* Sets the contract ID of this sales master.
	*
	* @param contractId the contract ID of this sales master
	*/
	@Override
	public void setContractId(java.lang.String contractId) {
		_salesMaster.setContractId(contractId);
	}

	/**
	* Sets the contract no of this sales master.
	*
	* @param contractNo the contract no of this sales master
	*/
	@Override
	public void setContractNo(java.lang.String contractNo) {
		_salesMaster.setContractNo(contractNo);
	}

	/**
	* Sets the created by of this sales master.
	*
	* @param createdBy the created by of this sales master
	*/
	@Override
	public void setCreatedBy(int createdBy) {
		_salesMaster.setCreatedBy(createdBy);
	}

	/**
	* Sets the created date of this sales master.
	*
	* @param createdDate the created date of this sales master
	*/
	@Override
	public void setCreatedDate(Date createdDate) {
		_salesMaster.setCreatedDate(createdDate);
	}

	/**
	* Sets the customer company code of this sales master.
	*
	* @param customerCompanyCode the customer company code of this sales master
	*/
	@Override
	public void setCustomerCompanyCode(java.lang.String customerCompanyCode) {
		_salesMaster.setCustomerCompanyCode(customerCompanyCode);
	}

	/**
	* Sets the customer subtype of this sales master.
	*
	* @param customerSubtype the customer subtype of this sales master
	*/
	@Override
	public void setCustomerSubtype(java.lang.String customerSubtype) {
		_salesMaster.setCustomerSubtype(customerSubtype);
	}

	/**
	* Sets the division ID of this sales master.
	*
	* @param divisionId the division ID of this sales master
	*/
	@Override
	public void setDivisionId(java.lang.String divisionId) {
		_salesMaster.setDivisionId(divisionId);
	}

	/**
	* Sets the doc type of this sales master.
	*
	* @param docType the doc type of this sales master
	*/
	@Override
	public void setDocType(java.lang.String docType) {
		_salesMaster.setDocType(docType);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_salesMaster.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_salesMaster.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_salesMaster.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the identifier code qualifier of this sales master.
	*
	* @param identifierCodeQualifier the identifier code qualifier of this sales master
	*/
	@Override
	public void setIdentifierCodeQualifier(
		java.lang.String identifierCodeQualifier) {
		_salesMaster.setIdentifierCodeQualifier(identifierCodeQualifier);
	}

	/**
	* Sets the inbound status of this sales master.
	*
	* @param inboundStatus the inbound status of this sales master
	*/
	@Override
	public void setInboundStatus(java.lang.String inboundStatus) {
		_salesMaster.setInboundStatus(inboundStatus);
	}

	/**
	* Sets the invoice date of this sales master.
	*
	* @param invoiceDate the invoice date of this sales master
	*/
	@Override
	public void setInvoiceDate(Date invoiceDate) {
		_salesMaster.setInvoiceDate(invoiceDate);
	}

	/**
	* Sets the invoice line number of this sales master.
	*
	* @param invoiceLineNumber the invoice line number of this sales master
	*/
	@Override
	public void setInvoiceLineNumber(java.lang.String invoiceLineNumber) {
		_salesMaster.setInvoiceLineNumber(invoiceLineNumber);
	}

	/**
	* Sets the invoice number of this sales master.
	*
	* @param invoiceNumber the invoice number of this sales master
	*/
	@Override
	public void setInvoiceNumber(java.lang.String invoiceNumber) {
		_salesMaster.setInvoiceNumber(invoiceNumber);
	}

	/**
	* Sets the is active of this sales master.
	*
	* @param isActive the is active of this sales master
	*/
	@Override
	public void setIsActive(java.lang.String isActive) {
		_salesMaster.setIsActive(isActive);
	}

	/**
	* Sets the item code qualifier of this sales master.
	*
	* @param itemCodeQualifier the item code qualifier of this sales master
	*/
	@Override
	public void setItemCodeQualifier(java.lang.String itemCodeQualifier) {
		_salesMaster.setItemCodeQualifier(itemCodeQualifier);
	}

	/**
	* Sets the item ID of this sales master.
	*
	* @param itemId the item ID of this sales master
	*/
	@Override
	public void setItemId(java.lang.String itemId) {
		_salesMaster.setItemId(itemId);
	}

	/**
	* Sets the item no of this sales master.
	*
	* @param itemNo the item no of this sales master
	*/
	@Override
	public void setItemNo(java.lang.String itemNo) {
		_salesMaster.setItemNo(itemNo);
	}

	/**
	* Sets the item parent no of this sales master.
	*
	* @param itemParentNo the item parent no of this sales master
	*/
	@Override
	public void setItemParentNo(java.lang.String itemParentNo) {
		_salesMaster.setItemParentNo(itemParentNo);
	}

	/**
	* Sets the item uom of this sales master.
	*
	* @param itemUom the item uom of this sales master
	*/
	@Override
	public void setItemUom(java.lang.String itemUom) {
		_salesMaster.setItemUom(itemUom);
	}

	/**
	* Sets the lot no of this sales master.
	*
	* @param lotNo the lot no of this sales master
	*/
	@Override
	public void setLotNo(java.lang.String lotNo) {
		_salesMaster.setLotNo(lotNo);
	}

	/**
	* Sets the market ID of this sales master.
	*
	* @param marketId the market ID of this sales master
	*/
	@Override
	public void setMarketId(java.lang.String marketId) {
		_salesMaster.setMarketId(marketId);
	}

	/**
	* Sets the modified by of this sales master.
	*
	* @param modifiedBy the modified by of this sales master
	*/
	@Override
	public void setModifiedBy(int modifiedBy) {
		_salesMaster.setModifiedBy(modifiedBy);
	}

	/**
	* Sets the modified date of this sales master.
	*
	* @param modifiedDate the modified date of this sales master
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_salesMaster.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_salesMaster.setNew(n);
	}

	/**
	* Sets the order number of this sales master.
	*
	* @param orderNumber the order number of this sales master
	*/
	@Override
	public void setOrderNumber(java.lang.String orderNumber) {
		_salesMaster.setOrderNumber(orderNumber);
	}

	/**
	* Sets the order received date of this sales master.
	*
	* @param orderReceivedDate the order received date of this sales master
	*/
	@Override
	public void setOrderReceivedDate(Date orderReceivedDate) {
		_salesMaster.setOrderReceivedDate(orderReceivedDate);
	}

	/**
	* Sets the order subtype of this sales master.
	*
	* @param orderSubtype the order subtype of this sales master
	*/
	@Override
	public void setOrderSubtype(java.lang.String orderSubtype) {
		_salesMaster.setOrderSubtype(orderSubtype);
	}

	/**
	* Sets the order type of this sales master.
	*
	* @param orderType the order type of this sales master
	*/
	@Override
	public void setOrderType(java.lang.String orderType) {
		_salesMaster.setOrderType(orderType);
	}

	/**
	* Sets the organization key of this sales master.
	*
	* @param organizationKey the organization key of this sales master
	*/
	@Override
	public void setOrganizationKey(java.lang.String organizationKey) {
		_salesMaster.setOrganizationKey(organizationKey);
	}

	/**
	* Sets the parent item ID of this sales master.
	*
	* @param parentItemId the parent item ID of this sales master
	*/
	@Override
	public void setParentItemId(java.lang.String parentItemId) {
		_salesMaster.setParentItemId(parentItemId);
	}

	/**
	* Sets the price of this sales master.
	*
	* @param price the price of this sales master
	*/
	@Override
	public void setPrice(double price) {
		_salesMaster.setPrice(price);
	}

	/**
	* Sets the price adjustment name of this sales master.
	*
	* @param priceAdjustmentName the price adjustment name of this sales master
	*/
	@Override
	public void setPriceAdjustmentName(java.lang.String priceAdjustmentName) {
		_salesMaster.setPriceAdjustmentName(priceAdjustmentName);
	}

	/**
	* Sets the primary key of this sales master.
	*
	* @param primaryKey the primary key of this sales master
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_salesMaster.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_salesMaster.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the provision ID of this sales master.
	*
	* @param provisionId the provision ID of this sales master
	*/
	@Override
	public void setProvisionId(int provisionId) {
		_salesMaster.setProvisionId(provisionId);
	}

	/**
	* Sets the quantity of this sales master.
	*
	* @param quantity the quantity of this sales master
	*/
	@Override
	public void setQuantity(double quantity) {
		_salesMaster.setQuantity(quantity);
	}

	/**
	* Sets whether this sales master is record lock status.
	*
	* @param recordLockStatus the record lock status of this sales master
	*/
	@Override
	public void setRecordLockStatus(boolean recordLockStatus) {
		_salesMaster.setRecordLockStatus(recordLockStatus);
	}

	/**
	* Sets the record sequence of this sales master.
	*
	* @param recordSequence the record sequence of this sales master
	*/
	@Override
	public void setRecordSequence(int recordSequence) {
		_salesMaster.setRecordSequence(recordSequence);
	}

	/**
	* Sets the sales ID of this sales master.
	*
	* @param salesId the sales ID of this sales master
	*/
	@Override
	public void setSalesId(java.lang.String salesId) {
		_salesMaster.setSalesId(salesId);
	}

	/**
	* Sets the sales master sid of this sales master.
	*
	* @param salesMasterSid the sales master sid of this sales master
	*/
	@Override
	public void setSalesMasterSid(int salesMasterSid) {
		_salesMaster.setSalesMasterSid(salesMasterSid);
	}

	/**
	* Sets the source of this sales master.
	*
	* @param source the source of this sales master
	*/
	@Override
	public void setSource(java.lang.String source) {
		_salesMaster.setSource(source);
	}

	/**
	* Sets the upload date of this sales master.
	*
	* @param uploadDate the upload date of this sales master
	*/
	@Override
	public void setUploadDate(Date uploadDate) {
		_salesMaster.setUploadDate(uploadDate);
	}

	/**
	* Sets the wholesale owner ID of this sales master.
	*
	* @param wholesaleOwnerId the wholesale owner ID of this sales master
	*/
	@Override
	public void setWholesaleOwnerId(java.lang.String wholesaleOwnerId) {
		_salesMaster.setWholesaleOwnerId(wholesaleOwnerId);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<SalesMaster> toCacheModel() {
		return _salesMaster.toCacheModel();
	}

	@Override
	public SalesMaster toEscapedModel() {
		return new SalesMasterWrapper(_salesMaster.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _salesMaster.toString();
	}

	@Override
	public SalesMaster toUnescapedModel() {
		return new SalesMasterWrapper(_salesMaster.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _salesMaster.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof SalesMasterWrapper)) {
			return false;
		}

		SalesMasterWrapper salesMasterWrapper = (SalesMasterWrapper)obj;

		if (Objects.equals(_salesMaster, salesMasterWrapper._salesMaster)) {
			return true;
		}

		return false;
	}

	@Override
	public SalesMaster getWrappedModel() {
		return _salesMaster;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _salesMaster.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _salesMaster.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_salesMaster.resetOriginalValues();
	}

	private final SalesMaster _salesMaster;
}