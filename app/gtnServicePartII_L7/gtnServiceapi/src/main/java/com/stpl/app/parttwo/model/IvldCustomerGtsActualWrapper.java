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
 * This class is a wrapper for {@link IvldCustomerGtsActual}.
 * </p>
 *
 * @author
 * @see IvldCustomerGtsActual
 * @generated
 */
@ProviderType
public class IvldCustomerGtsActualWrapper implements IvldCustomerGtsActual,
	ModelWrapper<IvldCustomerGtsActual> {
	public IvldCustomerGtsActualWrapper(
		IvldCustomerGtsActual ivldCustomerGtsActual) {
		_ivldCustomerGtsActual = ivldCustomerGtsActual;
	}

	@Override
	public Class<?> getModelClass() {
		return IvldCustomerGtsActual.class;
	}

	@Override
	public String getModelClassName() {
		return IvldCustomerGtsActual.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("parentAccountId", getParentAccountId());
		attributes.put("ivldCustomerGtsActualSid", getIvldCustomerGtsActualSid());
		attributes.put("accountId", getAccountId());
		attributes.put("itemId", getItemId());
		attributes.put("orderReceivedDate", getOrderReceivedDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("orderNumber", getOrderNumber());
		attributes.put("organizationKey", getOrganizationKey());
		attributes.put("source", getSource());
		attributes.put("createdBy", getCreatedBy());
		attributes.put("createdDate", getCreatedDate());
		attributes.put("addChgDelIndicator", getAddChgDelIndicator());
		attributes.put("errorCode", getErrorCode());
		attributes.put("itemUom", getItemUom());
		attributes.put("invoiceNumber", getInvoiceNumber());
		attributes.put("modifiedBy", getModifiedBy());
		attributes.put("intfInsertedDate", getIntfInsertedDate());
		attributes.put("lotNo", getLotNo());
		attributes.put("reprocessedFlag", getReprocessedFlag());
		attributes.put("quantity", getQuantity());
		attributes.put("invoiceLineNumber", getInvoiceLineNumber());
		attributes.put("contractId", getContractId());
		attributes.put("reasonForFailure", getReasonForFailure());
		attributes.put("amount", getAmount());
		attributes.put("invoiceDate", getInvoiceDate());
		attributes.put("customerGtsActualIntfId", getCustomerGtsActualIntfId());
		attributes.put("batchId", getBatchId());
		attributes.put("salesId", getSalesId());
		attributes.put("errorField", getErrorField());
		attributes.put("checkRecord", getCheckRecord());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String parentAccountId = (String)attributes.get("parentAccountId");

		if (parentAccountId != null) {
			setParentAccountId(parentAccountId);
		}

		Integer ivldCustomerGtsActualSid = (Integer)attributes.get(
				"ivldCustomerGtsActualSid");

		if (ivldCustomerGtsActualSid != null) {
			setIvldCustomerGtsActualSid(ivldCustomerGtsActualSid);
		}

		String accountId = (String)attributes.get("accountId");

		if (accountId != null) {
			setAccountId(accountId);
		}

		String itemId = (String)attributes.get("itemId");

		if (itemId != null) {
			setItemId(itemId);
		}

		Date orderReceivedDate = (Date)attributes.get("orderReceivedDate");

		if (orderReceivedDate != null) {
			setOrderReceivedDate(orderReceivedDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String orderNumber = (String)attributes.get("orderNumber");

		if (orderNumber != null) {
			setOrderNumber(orderNumber);
		}

		String organizationKey = (String)attributes.get("organizationKey");

		if (organizationKey != null) {
			setOrganizationKey(organizationKey);
		}

		String source = (String)attributes.get("source");

		if (source != null) {
			setSource(source);
		}

		String createdBy = (String)attributes.get("createdBy");

		if (createdBy != null) {
			setCreatedBy(createdBy);
		}

		String createdDate = (String)attributes.get("createdDate");

		if (createdDate != null) {
			setCreatedDate(createdDate);
		}

		String addChgDelIndicator = (String)attributes.get("addChgDelIndicator");

		if (addChgDelIndicator != null) {
			setAddChgDelIndicator(addChgDelIndicator);
		}

		String errorCode = (String)attributes.get("errorCode");

		if (errorCode != null) {
			setErrorCode(errorCode);
		}

		String itemUom = (String)attributes.get("itemUom");

		if (itemUom != null) {
			setItemUom(itemUom);
		}

		String invoiceNumber = (String)attributes.get("invoiceNumber");

		if (invoiceNumber != null) {
			setInvoiceNumber(invoiceNumber);
		}

		String modifiedBy = (String)attributes.get("modifiedBy");

		if (modifiedBy != null) {
			setModifiedBy(modifiedBy);
		}

		Date intfInsertedDate = (Date)attributes.get("intfInsertedDate");

		if (intfInsertedDate != null) {
			setIntfInsertedDate(intfInsertedDate);
		}

		String lotNo = (String)attributes.get("lotNo");

		if (lotNo != null) {
			setLotNo(lotNo);
		}

		String reprocessedFlag = (String)attributes.get("reprocessedFlag");

		if (reprocessedFlag != null) {
			setReprocessedFlag(reprocessedFlag);
		}

		String quantity = (String)attributes.get("quantity");

		if (quantity != null) {
			setQuantity(quantity);
		}

		String invoiceLineNumber = (String)attributes.get("invoiceLineNumber");

		if (invoiceLineNumber != null) {
			setInvoiceLineNumber(invoiceLineNumber);
		}

		String contractId = (String)attributes.get("contractId");

		if (contractId != null) {
			setContractId(contractId);
		}

		String reasonForFailure = (String)attributes.get("reasonForFailure");

		if (reasonForFailure != null) {
			setReasonForFailure(reasonForFailure);
		}

		String amount = (String)attributes.get("amount");

		if (amount != null) {
			setAmount(amount);
		}

		Date invoiceDate = (Date)attributes.get("invoiceDate");

		if (invoiceDate != null) {
			setInvoiceDate(invoiceDate);
		}

		String customerGtsActualIntfId = (String)attributes.get(
				"customerGtsActualIntfId");

		if (customerGtsActualIntfId != null) {
			setCustomerGtsActualIntfId(customerGtsActualIntfId);
		}

		String batchId = (String)attributes.get("batchId");

		if (batchId != null) {
			setBatchId(batchId);
		}

		String salesId = (String)attributes.get("salesId");

		if (salesId != null) {
			setSalesId(salesId);
		}

		String errorField = (String)attributes.get("errorField");

		if (errorField != null) {
			setErrorField(errorField);
		}

		Boolean checkRecord = (Boolean)attributes.get("checkRecord");

		if (checkRecord != null) {
			setCheckRecord(checkRecord);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new IvldCustomerGtsActualWrapper((IvldCustomerGtsActual)_ivldCustomerGtsActual.clone());
	}

	@Override
	public int compareTo(IvldCustomerGtsActual ivldCustomerGtsActual) {
		return _ivldCustomerGtsActual.compareTo(ivldCustomerGtsActual);
	}

	/**
	* Returns the account ID of this ivld customer gts actual.
	*
	* @return the account ID of this ivld customer gts actual
	*/
	@Override
	public java.lang.String getAccountId() {
		return _ivldCustomerGtsActual.getAccountId();
	}

	/**
	* Returns the add chg del indicator of this ivld customer gts actual.
	*
	* @return the add chg del indicator of this ivld customer gts actual
	*/
	@Override
	public java.lang.String getAddChgDelIndicator() {
		return _ivldCustomerGtsActual.getAddChgDelIndicator();
	}

	/**
	* Returns the amount of this ivld customer gts actual.
	*
	* @return the amount of this ivld customer gts actual
	*/
	@Override
	public java.lang.String getAmount() {
		return _ivldCustomerGtsActual.getAmount();
	}

	/**
	* Returns the batch ID of this ivld customer gts actual.
	*
	* @return the batch ID of this ivld customer gts actual
	*/
	@Override
	public java.lang.String getBatchId() {
		return _ivldCustomerGtsActual.getBatchId();
	}

	/**
	* Returns the check record of this ivld customer gts actual.
	*
	* @return the check record of this ivld customer gts actual
	*/
	@Override
	public boolean getCheckRecord() {
		return _ivldCustomerGtsActual.getCheckRecord();
	}

	/**
	* Returns the contract ID of this ivld customer gts actual.
	*
	* @return the contract ID of this ivld customer gts actual
	*/
	@Override
	public java.lang.String getContractId() {
		return _ivldCustomerGtsActual.getContractId();
	}

	/**
	* Returns the created by of this ivld customer gts actual.
	*
	* @return the created by of this ivld customer gts actual
	*/
	@Override
	public java.lang.String getCreatedBy() {
		return _ivldCustomerGtsActual.getCreatedBy();
	}

	/**
	* Returns the created date of this ivld customer gts actual.
	*
	* @return the created date of this ivld customer gts actual
	*/
	@Override
	public java.lang.String getCreatedDate() {
		return _ivldCustomerGtsActual.getCreatedDate();
	}

	/**
	* Returns the customer gts actual intf ID of this ivld customer gts actual.
	*
	* @return the customer gts actual intf ID of this ivld customer gts actual
	*/
	@Override
	public java.lang.String getCustomerGtsActualIntfId() {
		return _ivldCustomerGtsActual.getCustomerGtsActualIntfId();
	}

	/**
	* Returns the error code of this ivld customer gts actual.
	*
	* @return the error code of this ivld customer gts actual
	*/
	@Override
	public java.lang.String getErrorCode() {
		return _ivldCustomerGtsActual.getErrorCode();
	}

	/**
	* Returns the error field of this ivld customer gts actual.
	*
	* @return the error field of this ivld customer gts actual
	*/
	@Override
	public java.lang.String getErrorField() {
		return _ivldCustomerGtsActual.getErrorField();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _ivldCustomerGtsActual.getExpandoBridge();
	}

	/**
	* Returns the intf inserted date of this ivld customer gts actual.
	*
	* @return the intf inserted date of this ivld customer gts actual
	*/
	@Override
	public Date getIntfInsertedDate() {
		return _ivldCustomerGtsActual.getIntfInsertedDate();
	}

	/**
	* Returns the invoice date of this ivld customer gts actual.
	*
	* @return the invoice date of this ivld customer gts actual
	*/
	@Override
	public Date getInvoiceDate() {
		return _ivldCustomerGtsActual.getInvoiceDate();
	}

	/**
	* Returns the invoice line number of this ivld customer gts actual.
	*
	* @return the invoice line number of this ivld customer gts actual
	*/
	@Override
	public java.lang.String getInvoiceLineNumber() {
		return _ivldCustomerGtsActual.getInvoiceLineNumber();
	}

	/**
	* Returns the invoice number of this ivld customer gts actual.
	*
	* @return the invoice number of this ivld customer gts actual
	*/
	@Override
	public java.lang.String getInvoiceNumber() {
		return _ivldCustomerGtsActual.getInvoiceNumber();
	}

	/**
	* Returns the item ID of this ivld customer gts actual.
	*
	* @return the item ID of this ivld customer gts actual
	*/
	@Override
	public java.lang.String getItemId() {
		return _ivldCustomerGtsActual.getItemId();
	}

	/**
	* Returns the item uom of this ivld customer gts actual.
	*
	* @return the item uom of this ivld customer gts actual
	*/
	@Override
	public java.lang.String getItemUom() {
		return _ivldCustomerGtsActual.getItemUom();
	}

	/**
	* Returns the ivld customer gts actual sid of this ivld customer gts actual.
	*
	* @return the ivld customer gts actual sid of this ivld customer gts actual
	*/
	@Override
	public int getIvldCustomerGtsActualSid() {
		return _ivldCustomerGtsActual.getIvldCustomerGtsActualSid();
	}

	/**
	* Returns the lot no of this ivld customer gts actual.
	*
	* @return the lot no of this ivld customer gts actual
	*/
	@Override
	public java.lang.String getLotNo() {
		return _ivldCustomerGtsActual.getLotNo();
	}

	/**
	* Returns the modified by of this ivld customer gts actual.
	*
	* @return the modified by of this ivld customer gts actual
	*/
	@Override
	public java.lang.String getModifiedBy() {
		return _ivldCustomerGtsActual.getModifiedBy();
	}

	/**
	* Returns the modified date of this ivld customer gts actual.
	*
	* @return the modified date of this ivld customer gts actual
	*/
	@Override
	public Date getModifiedDate() {
		return _ivldCustomerGtsActual.getModifiedDate();
	}

	/**
	* Returns the order number of this ivld customer gts actual.
	*
	* @return the order number of this ivld customer gts actual
	*/
	@Override
	public java.lang.String getOrderNumber() {
		return _ivldCustomerGtsActual.getOrderNumber();
	}

	/**
	* Returns the order received date of this ivld customer gts actual.
	*
	* @return the order received date of this ivld customer gts actual
	*/
	@Override
	public Date getOrderReceivedDate() {
		return _ivldCustomerGtsActual.getOrderReceivedDate();
	}

	/**
	* Returns the organization key of this ivld customer gts actual.
	*
	* @return the organization key of this ivld customer gts actual
	*/
	@Override
	public java.lang.String getOrganizationKey() {
		return _ivldCustomerGtsActual.getOrganizationKey();
	}

	/**
	* Returns the parent account ID of this ivld customer gts actual.
	*
	* @return the parent account ID of this ivld customer gts actual
	*/
	@Override
	public java.lang.String getParentAccountId() {
		return _ivldCustomerGtsActual.getParentAccountId();
	}

	/**
	* Returns the primary key of this ivld customer gts actual.
	*
	* @return the primary key of this ivld customer gts actual
	*/
	@Override
	public int getPrimaryKey() {
		return _ivldCustomerGtsActual.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _ivldCustomerGtsActual.getPrimaryKeyObj();
	}

	/**
	* Returns the quantity of this ivld customer gts actual.
	*
	* @return the quantity of this ivld customer gts actual
	*/
	@Override
	public java.lang.String getQuantity() {
		return _ivldCustomerGtsActual.getQuantity();
	}

	/**
	* Returns the reason for failure of this ivld customer gts actual.
	*
	* @return the reason for failure of this ivld customer gts actual
	*/
	@Override
	public java.lang.String getReasonForFailure() {
		return _ivldCustomerGtsActual.getReasonForFailure();
	}

	/**
	* Returns the reprocessed flag of this ivld customer gts actual.
	*
	* @return the reprocessed flag of this ivld customer gts actual
	*/
	@Override
	public java.lang.String getReprocessedFlag() {
		return _ivldCustomerGtsActual.getReprocessedFlag();
	}

	/**
	* Returns the sales ID of this ivld customer gts actual.
	*
	* @return the sales ID of this ivld customer gts actual
	*/
	@Override
	public java.lang.String getSalesId() {
		return _ivldCustomerGtsActual.getSalesId();
	}

	/**
	* Returns the source of this ivld customer gts actual.
	*
	* @return the source of this ivld customer gts actual
	*/
	@Override
	public java.lang.String getSource() {
		return _ivldCustomerGtsActual.getSource();
	}

	@Override
	public int hashCode() {
		return _ivldCustomerGtsActual.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _ivldCustomerGtsActual.isCachedModel();
	}

	/**
	* Returns <code>true</code> if this ivld customer gts actual is check record.
	*
	* @return <code>true</code> if this ivld customer gts actual is check record; <code>false</code> otherwise
	*/
	@Override
	public boolean isCheckRecord() {
		return _ivldCustomerGtsActual.isCheckRecord();
	}

	@Override
	public boolean isEscapedModel() {
		return _ivldCustomerGtsActual.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _ivldCustomerGtsActual.isNew();
	}

	@Override
	public void persist() {
		_ivldCustomerGtsActual.persist();
	}

	/**
	* Sets the account ID of this ivld customer gts actual.
	*
	* @param accountId the account ID of this ivld customer gts actual
	*/
	@Override
	public void setAccountId(java.lang.String accountId) {
		_ivldCustomerGtsActual.setAccountId(accountId);
	}

	/**
	* Sets the add chg del indicator of this ivld customer gts actual.
	*
	* @param addChgDelIndicator the add chg del indicator of this ivld customer gts actual
	*/
	@Override
	public void setAddChgDelIndicator(java.lang.String addChgDelIndicator) {
		_ivldCustomerGtsActual.setAddChgDelIndicator(addChgDelIndicator);
	}

	/**
	* Sets the amount of this ivld customer gts actual.
	*
	* @param amount the amount of this ivld customer gts actual
	*/
	@Override
	public void setAmount(java.lang.String amount) {
		_ivldCustomerGtsActual.setAmount(amount);
	}

	/**
	* Sets the batch ID of this ivld customer gts actual.
	*
	* @param batchId the batch ID of this ivld customer gts actual
	*/
	@Override
	public void setBatchId(java.lang.String batchId) {
		_ivldCustomerGtsActual.setBatchId(batchId);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_ivldCustomerGtsActual.setCachedModel(cachedModel);
	}

	/**
	* Sets whether this ivld customer gts actual is check record.
	*
	* @param checkRecord the check record of this ivld customer gts actual
	*/
	@Override
	public void setCheckRecord(boolean checkRecord) {
		_ivldCustomerGtsActual.setCheckRecord(checkRecord);
	}

	/**
	* Sets the contract ID of this ivld customer gts actual.
	*
	* @param contractId the contract ID of this ivld customer gts actual
	*/
	@Override
	public void setContractId(java.lang.String contractId) {
		_ivldCustomerGtsActual.setContractId(contractId);
	}

	/**
	* Sets the created by of this ivld customer gts actual.
	*
	* @param createdBy the created by of this ivld customer gts actual
	*/
	@Override
	public void setCreatedBy(java.lang.String createdBy) {
		_ivldCustomerGtsActual.setCreatedBy(createdBy);
	}

	/**
	* Sets the created date of this ivld customer gts actual.
	*
	* @param createdDate the created date of this ivld customer gts actual
	*/
	@Override
	public void setCreatedDate(java.lang.String createdDate) {
		_ivldCustomerGtsActual.setCreatedDate(createdDate);
	}

	/**
	* Sets the customer gts actual intf ID of this ivld customer gts actual.
	*
	* @param customerGtsActualIntfId the customer gts actual intf ID of this ivld customer gts actual
	*/
	@Override
	public void setCustomerGtsActualIntfId(
		java.lang.String customerGtsActualIntfId) {
		_ivldCustomerGtsActual.setCustomerGtsActualIntfId(customerGtsActualIntfId);
	}

	/**
	* Sets the error code of this ivld customer gts actual.
	*
	* @param errorCode the error code of this ivld customer gts actual
	*/
	@Override
	public void setErrorCode(java.lang.String errorCode) {
		_ivldCustomerGtsActual.setErrorCode(errorCode);
	}

	/**
	* Sets the error field of this ivld customer gts actual.
	*
	* @param errorField the error field of this ivld customer gts actual
	*/
	@Override
	public void setErrorField(java.lang.String errorField) {
		_ivldCustomerGtsActual.setErrorField(errorField);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_ivldCustomerGtsActual.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_ivldCustomerGtsActual.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_ivldCustomerGtsActual.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the intf inserted date of this ivld customer gts actual.
	*
	* @param intfInsertedDate the intf inserted date of this ivld customer gts actual
	*/
	@Override
	public void setIntfInsertedDate(Date intfInsertedDate) {
		_ivldCustomerGtsActual.setIntfInsertedDate(intfInsertedDate);
	}

	/**
	* Sets the invoice date of this ivld customer gts actual.
	*
	* @param invoiceDate the invoice date of this ivld customer gts actual
	*/
	@Override
	public void setInvoiceDate(Date invoiceDate) {
		_ivldCustomerGtsActual.setInvoiceDate(invoiceDate);
	}

	/**
	* Sets the invoice line number of this ivld customer gts actual.
	*
	* @param invoiceLineNumber the invoice line number of this ivld customer gts actual
	*/
	@Override
	public void setInvoiceLineNumber(java.lang.String invoiceLineNumber) {
		_ivldCustomerGtsActual.setInvoiceLineNumber(invoiceLineNumber);
	}

	/**
	* Sets the invoice number of this ivld customer gts actual.
	*
	* @param invoiceNumber the invoice number of this ivld customer gts actual
	*/
	@Override
	public void setInvoiceNumber(java.lang.String invoiceNumber) {
		_ivldCustomerGtsActual.setInvoiceNumber(invoiceNumber);
	}

	/**
	* Sets the item ID of this ivld customer gts actual.
	*
	* @param itemId the item ID of this ivld customer gts actual
	*/
	@Override
	public void setItemId(java.lang.String itemId) {
		_ivldCustomerGtsActual.setItemId(itemId);
	}

	/**
	* Sets the item uom of this ivld customer gts actual.
	*
	* @param itemUom the item uom of this ivld customer gts actual
	*/
	@Override
	public void setItemUom(java.lang.String itemUom) {
		_ivldCustomerGtsActual.setItemUom(itemUom);
	}

	/**
	* Sets the ivld customer gts actual sid of this ivld customer gts actual.
	*
	* @param ivldCustomerGtsActualSid the ivld customer gts actual sid of this ivld customer gts actual
	*/
	@Override
	public void setIvldCustomerGtsActualSid(int ivldCustomerGtsActualSid) {
		_ivldCustomerGtsActual.setIvldCustomerGtsActualSid(ivldCustomerGtsActualSid);
	}

	/**
	* Sets the lot no of this ivld customer gts actual.
	*
	* @param lotNo the lot no of this ivld customer gts actual
	*/
	@Override
	public void setLotNo(java.lang.String lotNo) {
		_ivldCustomerGtsActual.setLotNo(lotNo);
	}

	/**
	* Sets the modified by of this ivld customer gts actual.
	*
	* @param modifiedBy the modified by of this ivld customer gts actual
	*/
	@Override
	public void setModifiedBy(java.lang.String modifiedBy) {
		_ivldCustomerGtsActual.setModifiedBy(modifiedBy);
	}

	/**
	* Sets the modified date of this ivld customer gts actual.
	*
	* @param modifiedDate the modified date of this ivld customer gts actual
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_ivldCustomerGtsActual.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_ivldCustomerGtsActual.setNew(n);
	}

	/**
	* Sets the order number of this ivld customer gts actual.
	*
	* @param orderNumber the order number of this ivld customer gts actual
	*/
	@Override
	public void setOrderNumber(java.lang.String orderNumber) {
		_ivldCustomerGtsActual.setOrderNumber(orderNumber);
	}

	/**
	* Sets the order received date of this ivld customer gts actual.
	*
	* @param orderReceivedDate the order received date of this ivld customer gts actual
	*/
	@Override
	public void setOrderReceivedDate(Date orderReceivedDate) {
		_ivldCustomerGtsActual.setOrderReceivedDate(orderReceivedDate);
	}

	/**
	* Sets the organization key of this ivld customer gts actual.
	*
	* @param organizationKey the organization key of this ivld customer gts actual
	*/
	@Override
	public void setOrganizationKey(java.lang.String organizationKey) {
		_ivldCustomerGtsActual.setOrganizationKey(organizationKey);
	}

	/**
	* Sets the parent account ID of this ivld customer gts actual.
	*
	* @param parentAccountId the parent account ID of this ivld customer gts actual
	*/
	@Override
	public void setParentAccountId(java.lang.String parentAccountId) {
		_ivldCustomerGtsActual.setParentAccountId(parentAccountId);
	}

	/**
	* Sets the primary key of this ivld customer gts actual.
	*
	* @param primaryKey the primary key of this ivld customer gts actual
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_ivldCustomerGtsActual.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_ivldCustomerGtsActual.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the quantity of this ivld customer gts actual.
	*
	* @param quantity the quantity of this ivld customer gts actual
	*/
	@Override
	public void setQuantity(java.lang.String quantity) {
		_ivldCustomerGtsActual.setQuantity(quantity);
	}

	/**
	* Sets the reason for failure of this ivld customer gts actual.
	*
	* @param reasonForFailure the reason for failure of this ivld customer gts actual
	*/
	@Override
	public void setReasonForFailure(java.lang.String reasonForFailure) {
		_ivldCustomerGtsActual.setReasonForFailure(reasonForFailure);
	}

	/**
	* Sets the reprocessed flag of this ivld customer gts actual.
	*
	* @param reprocessedFlag the reprocessed flag of this ivld customer gts actual
	*/
	@Override
	public void setReprocessedFlag(java.lang.String reprocessedFlag) {
		_ivldCustomerGtsActual.setReprocessedFlag(reprocessedFlag);
	}

	/**
	* Sets the sales ID of this ivld customer gts actual.
	*
	* @param salesId the sales ID of this ivld customer gts actual
	*/
	@Override
	public void setSalesId(java.lang.String salesId) {
		_ivldCustomerGtsActual.setSalesId(salesId);
	}

	/**
	* Sets the source of this ivld customer gts actual.
	*
	* @param source the source of this ivld customer gts actual
	*/
	@Override
	public void setSource(java.lang.String source) {
		_ivldCustomerGtsActual.setSource(source);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<IvldCustomerGtsActual> toCacheModel() {
		return _ivldCustomerGtsActual.toCacheModel();
	}

	@Override
	public IvldCustomerGtsActual toEscapedModel() {
		return new IvldCustomerGtsActualWrapper(_ivldCustomerGtsActual.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _ivldCustomerGtsActual.toString();
	}

	@Override
	public IvldCustomerGtsActual toUnescapedModel() {
		return new IvldCustomerGtsActualWrapper(_ivldCustomerGtsActual.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _ivldCustomerGtsActual.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof IvldCustomerGtsActualWrapper)) {
			return false;
		}

		IvldCustomerGtsActualWrapper ivldCustomerGtsActualWrapper = (IvldCustomerGtsActualWrapper)obj;

		if (Objects.equals(_ivldCustomerGtsActual,
					ivldCustomerGtsActualWrapper._ivldCustomerGtsActual)) {
			return true;
		}

		return false;
	}

	@Override
	public IvldCustomerGtsActual getWrappedModel() {
		return _ivldCustomerGtsActual;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _ivldCustomerGtsActual.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _ivldCustomerGtsActual.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_ivldCustomerGtsActual.resetOriginalValues();
	}

	private final IvldCustomerGtsActual _ivldCustomerGtsActual;
}