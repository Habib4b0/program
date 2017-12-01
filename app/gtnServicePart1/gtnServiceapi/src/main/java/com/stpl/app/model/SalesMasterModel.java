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

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;

/**
 * The base model interface for the SalesMaster service. Represents a row in the &quot;SALES_MASTER&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.stpl.app.model.impl.SalesMasterModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.stpl.app.model.impl.SalesMasterImpl}.
 * </p>
 *
 * @author
 * @see SalesMaster
 * @see com.stpl.app.model.impl.SalesMasterImpl
 * @see com.stpl.app.model.impl.SalesMasterModelImpl
 * @generated
 */
@ProviderType
public interface SalesMasterModel extends BaseModel<SalesMaster> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a sales master model instance should use the {@link SalesMaster} interface instead.
	 */

	/**
	 * Returns the primary key of this sales master.
	 *
	 * @return the primary key of this sales master
	 */
	public int getPrimaryKey();

	/**
	 * Sets the primary key of this sales master.
	 *
	 * @param primaryKey the primary key of this sales master
	 */
	public void setPrimaryKey(int primaryKey);

	/**
	 * Returns the item no of this sales master.
	 *
	 * @return the item no of this sales master
	 */
	@AutoEscape
	public String getItemNo();

	/**
	 * Sets the item no of this sales master.
	 *
	 * @param itemNo the item no of this sales master
	 */
	public void setItemNo(String itemNo);

	/**
	 * Returns the record sequence of this sales master.
	 *
	 * @return the record sequence of this sales master
	 */
	public int getRecordSequence();

	/**
	 * Sets the record sequence of this sales master.
	 *
	 * @param recordSequence the record sequence of this sales master
	 */
	public void setRecordSequence(int recordSequence);

	/**
	 * Returns the quantity of this sales master.
	 *
	 * @return the quantity of this sales master
	 */
	public double getQuantity();

	/**
	 * Sets the quantity of this sales master.
	 *
	 * @param quantity the quantity of this sales master
	 */
	public void setQuantity(double quantity);

	/**
	 * Returns the account ID of this sales master.
	 *
	 * @return the account ID of this sales master
	 */
	@AutoEscape
	public String getAccountId();

	/**
	 * Sets the account ID of this sales master.
	 *
	 * @param accountId the account ID of this sales master
	 */
	public void setAccountId(String accountId);

	/**
	 * Returns the created date of this sales master.
	 *
	 * @return the created date of this sales master
	 */
	public Date getCreatedDate();

	/**
	 * Sets the created date of this sales master.
	 *
	 * @param createdDate the created date of this sales master
	 */
	public void setCreatedDate(Date createdDate);

	/**
	 * Returns the identifier code qualifier of this sales master.
	 *
	 * @return the identifier code qualifier of this sales master
	 */
	@AutoEscape
	public String getIdentifierCodeQualifier();

	/**
	 * Sets the identifier code qualifier of this sales master.
	 *
	 * @param identifierCodeQualifier the identifier code qualifier of this sales master
	 */
	public void setIdentifierCodeQualifier(String identifierCodeQualifier);

	/**
	 * Returns the is active of this sales master.
	 *
	 * @return the is active of this sales master
	 */
	@AutoEscape
	public String getIsActive();

	/**
	 * Sets the is active of this sales master.
	 *
	 * @param isActive the is active of this sales master
	 */
	public void setIsActive(String isActive);

	/**
	 * Returns the market ID of this sales master.
	 *
	 * @return the market ID of this sales master
	 */
	@AutoEscape
	public String getMarketId();

	/**
	 * Sets the market ID of this sales master.
	 *
	 * @param marketId the market ID of this sales master
	 */
	public void setMarketId(String marketId);

	/**
	 * Returns the invoice date of this sales master.
	 *
	 * @return the invoice date of this sales master
	 */
	public Date getInvoiceDate();

	/**
	 * Sets the invoice date of this sales master.
	 *
	 * @param invoiceDate the invoice date of this sales master
	 */
	public void setInvoiceDate(Date invoiceDate);

	/**
	 * Returns the account name of this sales master.
	 *
	 * @return the account name of this sales master
	 */
	@AutoEscape
	public String getAccountName();

	/**
	 * Sets the account name of this sales master.
	 *
	 * @param accountName the account name of this sales master
	 */
	public void setAccountName(String accountName);

	/**
	 * Returns the doc type of this sales master.
	 *
	 * @return the doc type of this sales master
	 */
	@AutoEscape
	public String getDocType();

	/**
	 * Sets the doc type of this sales master.
	 *
	 * @param docType the doc type of this sales master
	 */
	public void setDocType(String docType);

	/**
	 * Returns the order received date of this sales master.
	 *
	 * @return the order received date of this sales master
	 */
	public Date getOrderReceivedDate();

	/**
	 * Sets the order received date of this sales master.
	 *
	 * @param orderReceivedDate the order received date of this sales master
	 */
	public void setOrderReceivedDate(Date orderReceivedDate);

	/**
	 * Returns the amount of this sales master.
	 *
	 * @return the amount of this sales master
	 */
	public double getAmount();

	/**
	 * Sets the amount of this sales master.
	 *
	 * @param amount the amount of this sales master
	 */
	public void setAmount(double amount);

	/**
	 * Returns the sales master sid of this sales master.
	 *
	 * @return the sales master sid of this sales master
	 */
	public int getSalesMasterSid();

	/**
	 * Sets the sales master sid of this sales master.
	 *
	 * @param salesMasterSid the sales master sid of this sales master
	 */
	public void setSalesMasterSid(int salesMasterSid);

	/**
	 * Returns the order subtype of this sales master.
	 *
	 * @return the order subtype of this sales master
	 */
	@AutoEscape
	public String getOrderSubtype();

	/**
	 * Sets the order subtype of this sales master.
	 *
	 * @param orderSubtype the order subtype of this sales master
	 */
	public void setOrderSubtype(String orderSubtype);

	/**
	 * Returns the created by of this sales master.
	 *
	 * @return the created by of this sales master
	 */
	public int getCreatedBy();

	/**
	 * Sets the created by of this sales master.
	 *
	 * @param createdBy the created by of this sales master
	 */
	public void setCreatedBy(int createdBy);

	/**
	 * Returns the price of this sales master.
	 *
	 * @return the price of this sales master
	 */
	public double getPrice();

	/**
	 * Sets the price of this sales master.
	 *
	 * @param price the price of this sales master
	 */
	public void setPrice(double price);

	/**
	 * Returns the upload date of this sales master.
	 *
	 * @return the upload date of this sales master
	 */
	public Date getUploadDate();

	/**
	 * Sets the upload date of this sales master.
	 *
	 * @param uploadDate the upload date of this sales master
	 */
	public void setUploadDate(Date uploadDate);

	/**
	 * Returns the item ID of this sales master.
	 *
	 * @return the item ID of this sales master
	 */
	@AutoEscape
	public String getItemId();

	/**
	 * Sets the item ID of this sales master.
	 *
	 * @param itemId the item ID of this sales master
	 */
	public void setItemId(String itemId);

	/**
	 * Returns the price adjustment name of this sales master.
	 *
	 * @return the price adjustment name of this sales master
	 */
	@AutoEscape
	public String getPriceAdjustmentName();

	/**
	 * Sets the price adjustment name of this sales master.
	 *
	 * @param priceAdjustmentName the price adjustment name of this sales master
	 */
	public void setPriceAdjustmentName(String priceAdjustmentName);

	/**
	 * Returns the item code qualifier of this sales master.
	 *
	 * @return the item code qualifier of this sales master
	 */
	@AutoEscape
	public String getItemCodeQualifier();

	/**
	 * Sets the item code qualifier of this sales master.
	 *
	 * @param itemCodeQualifier the item code qualifier of this sales master
	 */
	public void setItemCodeQualifier(String itemCodeQualifier);

	/**
	 * Returns the contract ID of this sales master.
	 *
	 * @return the contract ID of this sales master
	 */
	@AutoEscape
	public String getContractId();

	/**
	 * Sets the contract ID of this sales master.
	 *
	 * @param contractId the contract ID of this sales master
	 */
	public void setContractId(String contractId);

	/**
	 * Returns the item uom of this sales master.
	 *
	 * @return the item uom of this sales master
	 */
	@AutoEscape
	public String getItemUom();

	/**
	 * Sets the item uom of this sales master.
	 *
	 * @param itemUom the item uom of this sales master
	 */
	public void setItemUom(String itemUom);

	/**
	 * Returns the modified date of this sales master.
	 *
	 * @return the modified date of this sales master
	 */
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this sales master.
	 *
	 * @param modifiedDate the modified date of this sales master
	 */
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the customer subtype of this sales master.
	 *
	 * @return the customer subtype of this sales master
	 */
	@AutoEscape
	public String getCustomerSubtype();

	/**
	 * Sets the customer subtype of this sales master.
	 *
	 * @param customerSubtype the customer subtype of this sales master
	 */
	public void setCustomerSubtype(String customerSubtype);

	/**
	 * Returns the provision ID of this sales master.
	 *
	 * @return the provision ID of this sales master
	 */
	public int getProvisionId();

	/**
	 * Sets the provision ID of this sales master.
	 *
	 * @param provisionId the provision ID of this sales master
	 */
	public void setProvisionId(int provisionId);

	/**
	 * Returns the wholesale owner ID of this sales master.
	 *
	 * @return the wholesale owner ID of this sales master
	 */
	@AutoEscape
	public String getWholesaleOwnerId();

	/**
	 * Sets the wholesale owner ID of this sales master.
	 *
	 * @param wholesaleOwnerId the wholesale owner ID of this sales master
	 */
	public void setWholesaleOwnerId(String wholesaleOwnerId);

	/**
	 * Returns the source of this sales master.
	 *
	 * @return the source of this sales master
	 */
	@AutoEscape
	public String getSource();

	/**
	 * Sets the source of this sales master.
	 *
	 * @param source the source of this sales master
	 */
	public void setSource(String source);

	/**
	 * Returns the account no of this sales master.
	 *
	 * @return the account no of this sales master
	 */
	@AutoEscape
	public String getAccountNo();

	/**
	 * Sets the account no of this sales master.
	 *
	 * @param accountNo the account no of this sales master
	 */
	public void setAccountNo(String accountNo);

	/**
	 * Returns the lot no of this sales master.
	 *
	 * @return the lot no of this sales master
	 */
	@AutoEscape
	public String getLotNo();

	/**
	 * Sets the lot no of this sales master.
	 *
	 * @param lotNo the lot no of this sales master
	 */
	public void setLotNo(String lotNo);

	/**
	 * Returns the parent item ID of this sales master.
	 *
	 * @return the parent item ID of this sales master
	 */
	@AutoEscape
	public String getParentItemId();

	/**
	 * Sets the parent item ID of this sales master.
	 *
	 * @param parentItemId the parent item ID of this sales master
	 */
	public void setParentItemId(String parentItemId);

	/**
	 * Returns the customer company code of this sales master.
	 *
	 * @return the customer company code of this sales master
	 */
	@AutoEscape
	public String getCustomerCompanyCode();

	/**
	 * Sets the customer company code of this sales master.
	 *
	 * @param customerCompanyCode the customer company code of this sales master
	 */
	public void setCustomerCompanyCode(String customerCompanyCode);

	/**
	 * Returns the analysis code of this sales master.
	 *
	 * @return the analysis code of this sales master
	 */
	@AutoEscape
	public String getAnalysisCode();

	/**
	 * Sets the analysis code of this sales master.
	 *
	 * @param analysisCode the analysis code of this sales master
	 */
	public void setAnalysisCode(String analysisCode);

	/**
	 * Returns the account type of this sales master.
	 *
	 * @return the account type of this sales master
	 */
	@AutoEscape
	public String getAccountType();

	/**
	 * Sets the account type of this sales master.
	 *
	 * @param accountType the account type of this sales master
	 */
	public void setAccountType(String accountType);

	/**
	 * Returns the modified by of this sales master.
	 *
	 * @return the modified by of this sales master
	 */
	public int getModifiedBy();

	/**
	 * Sets the modified by of this sales master.
	 *
	 * @param modifiedBy the modified by of this sales master
	 */
	public void setModifiedBy(int modifiedBy);

	/**
	 * Returns the contract no of this sales master.
	 *
	 * @return the contract no of this sales master
	 */
	@AutoEscape
	public String getContractNo();

	/**
	 * Sets the contract no of this sales master.
	 *
	 * @param contractNo the contract no of this sales master
	 */
	public void setContractNo(String contractNo);

	/**
	 * Returns the batch ID of this sales master.
	 *
	 * @return the batch ID of this sales master
	 */
	@AutoEscape
	public String getBatchId();

	/**
	 * Sets the batch ID of this sales master.
	 *
	 * @param batchId the batch ID of this sales master
	 */
	public void setBatchId(String batchId);

	/**
	 * Returns the brand ID of this sales master.
	 *
	 * @return the brand ID of this sales master
	 */
	@AutoEscape
	public String getBrandId();

	/**
	 * Sets the brand ID of this sales master.
	 *
	 * @param brandId the brand ID of this sales master
	 */
	public void setBrandId(String brandId);

	/**
	 * Returns the sales ID of this sales master.
	 *
	 * @return the sales ID of this sales master
	 */
	@AutoEscape
	public String getSalesId();

	/**
	 * Sets the sales ID of this sales master.
	 *
	 * @param salesId the sales ID of this sales master
	 */
	public void setSalesId(String salesId);

	/**
	 * Returns the company string ID of this sales master.
	 *
	 * @return the company string ID of this sales master
	 */
	@AutoEscape
	public String getCompanyStringId();

	/**
	 * Sets the company string ID of this sales master.
	 *
	 * @param companyStringId the company string ID of this sales master
	 */
	public void setCompanyStringId(String companyStringId);

	/**
	 * Returns the organization key of this sales master.
	 *
	 * @return the organization key of this sales master
	 */
	@AutoEscape
	public String getOrganizationKey();

	/**
	 * Sets the organization key of this sales master.
	 *
	 * @param organizationKey the organization key of this sales master
	 */
	public void setOrganizationKey(String organizationKey);

	/**
	 * Returns the item parent no of this sales master.
	 *
	 * @return the item parent no of this sales master
	 */
	@AutoEscape
	public String getItemParentNo();

	/**
	 * Sets the item parent no of this sales master.
	 *
	 * @param itemParentNo the item parent no of this sales master
	 */
	public void setItemParentNo(String itemParentNo);

	/**
	 * Returns the invoice number of this sales master.
	 *
	 * @return the invoice number of this sales master
	 */
	@AutoEscape
	public String getInvoiceNumber();

	/**
	 * Sets the invoice number of this sales master.
	 *
	 * @param invoiceNumber the invoice number of this sales master
	 */
	public void setInvoiceNumber(String invoiceNumber);

	/**
	 * Returns the order type of this sales master.
	 *
	 * @return the order type of this sales master
	 */
	@AutoEscape
	public String getOrderType();

	/**
	 * Sets the order type of this sales master.
	 *
	 * @param orderType the order type of this sales master
	 */
	public void setOrderType(String orderType);

	/**
	 * Returns the record lock status of this sales master.
	 *
	 * @return the record lock status of this sales master
	 */
	public boolean getRecordLockStatus();

	/**
	 * Returns <code>true</code> if this sales master is record lock status.
	 *
	 * @return <code>true</code> if this sales master is record lock status; <code>false</code> otherwise
	 */
	public boolean isRecordLockStatus();

	/**
	 * Sets whether this sales master is record lock status.
	 *
	 * @param recordLockStatus the record lock status of this sales master
	 */
	public void setRecordLockStatus(boolean recordLockStatus);

	/**
	 * Returns the division ID of this sales master.
	 *
	 * @return the division ID of this sales master
	 */
	@AutoEscape
	public String getDivisionId();

	/**
	 * Sets the division ID of this sales master.
	 *
	 * @param divisionId the division ID of this sales master
	 */
	public void setDivisionId(String divisionId);

	/**
	 * Returns the invoice line number of this sales master.
	 *
	 * @return the invoice line number of this sales master
	 */
	@AutoEscape
	public String getInvoiceLineNumber();

	/**
	 * Sets the invoice line number of this sales master.
	 *
	 * @param invoiceLineNumber the invoice line number of this sales master
	 */
	public void setInvoiceLineNumber(String invoiceLineNumber);

	/**
	 * Returns the order number of this sales master.
	 *
	 * @return the order number of this sales master
	 */
	@AutoEscape
	public String getOrderNumber();

	/**
	 * Sets the order number of this sales master.
	 *
	 * @param orderNumber the order number of this sales master
	 */
	public void setOrderNumber(String orderNumber);

	/**
	 * Returns the inbound status of this sales master.
	 *
	 * @return the inbound status of this sales master
	 */
	@AutoEscape
	public String getInboundStatus();

	/**
	 * Sets the inbound status of this sales master.
	 *
	 * @param inboundStatus the inbound status of this sales master
	 */
	public void setInboundStatus(String inboundStatus);

	@Override
	public boolean isNew();

	@Override
	public void setNew(boolean n);

	@Override
	public boolean isCachedModel();

	@Override
	public void setCachedModel(boolean cachedModel);

	@Override
	public boolean isEscapedModel();

	@Override
	public Serializable getPrimaryKeyObj();

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj);

	@Override
	public ExpandoBridge getExpandoBridge();

	@Override
	public void setExpandoBridgeAttributes(BaseModel<?> baseModel);

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge);

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext);

	@Override
	public Object clone();

	@Override
	public int compareTo(SalesMaster salesMaster);

	@Override
	public int hashCode();

	@Override
	public CacheModel<SalesMaster> toCacheModel();

	@Override
	public SalesMaster toEscapedModel();

	@Override
	public SalesMaster toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}