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
 * This class is a wrapper for {@link BestPriceMaster}.
 * </p>
 *
 * @author
 * @see BestPriceMaster
 * @generated
 */
@ProviderType
public class BestPriceMasterWrapper implements BestPriceMaster,
	ModelWrapper<BestPriceMaster> {
	public BestPriceMasterWrapper(BestPriceMaster bestPriceMaster) {
		_bestPriceMaster = bestPriceMaster;
	}

	@Override
	public Class<?> getModelClass() {
		return BestPriceMaster.class;
	}

	@Override
	public String getModelClassName() {
		return BestPriceMaster.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("initialBestPrice", getInitialBestPrice());
		attributes.put("createdBy", getCreatedBy());
		attributes.put("itemNo", getItemNo());
		attributes.put("modifiedBy", getModifiedBy());
		attributes.put("accountId", getAccountId());
		attributes.put("createdDate", getCreatedDate());
		attributes.put("itemId", getItemId());
		attributes.put("itemDesc", getItemDesc());
		attributes.put("sellingPrice", getSellingPrice());
		attributes.put("contractId", getContractId());
		attributes.put("contractNo", getContractNo());
		attributes.put("batchId", getBatchId());
		attributes.put("bestPriceMasterSid", getBestPriceMasterSid());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("recordLockStatus", getRecordLockStatus());
		attributes.put("beginningWacPackage", getBeginningWacPackage());
		attributes.put("initialDiscount", getInitialDiscount());
		attributes.put("period", getPeriod());
		attributes.put("source", getSource());
		attributes.put("contractStartDate", getContractStartDate());
		attributes.put("contractEndDate", getContractEndDate());
		attributes.put("inboundStatus", getInboundStatus());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Double initialBestPrice = (Double)attributes.get("initialBestPrice");

		if (initialBestPrice != null) {
			setInitialBestPrice(initialBestPrice);
		}

		Integer createdBy = (Integer)attributes.get("createdBy");

		if (createdBy != null) {
			setCreatedBy(createdBy);
		}

		String itemNo = (String)attributes.get("itemNo");

		if (itemNo != null) {
			setItemNo(itemNo);
		}

		Integer modifiedBy = (Integer)attributes.get("modifiedBy");

		if (modifiedBy != null) {
			setModifiedBy(modifiedBy);
		}

		String accountId = (String)attributes.get("accountId");

		if (accountId != null) {
			setAccountId(accountId);
		}

		Date createdDate = (Date)attributes.get("createdDate");

		if (createdDate != null) {
			setCreatedDate(createdDate);
		}

		String itemId = (String)attributes.get("itemId");

		if (itemId != null) {
			setItemId(itemId);
		}

		String itemDesc = (String)attributes.get("itemDesc");

		if (itemDesc != null) {
			setItemDesc(itemDesc);
		}

		Double sellingPrice = (Double)attributes.get("sellingPrice");

		if (sellingPrice != null) {
			setSellingPrice(sellingPrice);
		}

		String contractId = (String)attributes.get("contractId");

		if (contractId != null) {
			setContractId(contractId);
		}

		String contractNo = (String)attributes.get("contractNo");

		if (contractNo != null) {
			setContractNo(contractNo);
		}

		String batchId = (String)attributes.get("batchId");

		if (batchId != null) {
			setBatchId(batchId);
		}

		Integer bestPriceMasterSid = (Integer)attributes.get(
				"bestPriceMasterSid");

		if (bestPriceMasterSid != null) {
			setBestPriceMasterSid(bestPriceMasterSid);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Boolean recordLockStatus = (Boolean)attributes.get("recordLockStatus");

		if (recordLockStatus != null) {
			setRecordLockStatus(recordLockStatus);
		}

		Double beginningWacPackage = (Double)attributes.get(
				"beginningWacPackage");

		if (beginningWacPackage != null) {
			setBeginningWacPackage(beginningWacPackage);
		}

		Double initialDiscount = (Double)attributes.get("initialDiscount");

		if (initialDiscount != null) {
			setInitialDiscount(initialDiscount);
		}

		String period = (String)attributes.get("period");

		if (period != null) {
			setPeriod(period);
		}

		String source = (String)attributes.get("source");

		if (source != null) {
			setSource(source);
		}

		Date contractStartDate = (Date)attributes.get("contractStartDate");

		if (contractStartDate != null) {
			setContractStartDate(contractStartDate);
		}

		Date contractEndDate = (Date)attributes.get("contractEndDate");

		if (contractEndDate != null) {
			setContractEndDate(contractEndDate);
		}

		String inboundStatus = (String)attributes.get("inboundStatus");

		if (inboundStatus != null) {
			setInboundStatus(inboundStatus);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new BestPriceMasterWrapper((BestPriceMaster)_bestPriceMaster.clone());
	}

	@Override
	public int compareTo(BestPriceMaster bestPriceMaster) {
		return _bestPriceMaster.compareTo(bestPriceMaster);
	}

	/**
	* Returns the account ID of this best price master.
	*
	* @return the account ID of this best price master
	*/
	@Override
	public java.lang.String getAccountId() {
		return _bestPriceMaster.getAccountId();
	}

	/**
	* Returns the batch ID of this best price master.
	*
	* @return the batch ID of this best price master
	*/
	@Override
	public java.lang.String getBatchId() {
		return _bestPriceMaster.getBatchId();
	}

	/**
	* Returns the beginning wac package of this best price master.
	*
	* @return the beginning wac package of this best price master
	*/
	@Override
	public double getBeginningWacPackage() {
		return _bestPriceMaster.getBeginningWacPackage();
	}

	/**
	* Returns the best price master sid of this best price master.
	*
	* @return the best price master sid of this best price master
	*/
	@Override
	public int getBestPriceMasterSid() {
		return _bestPriceMaster.getBestPriceMasterSid();
	}

	/**
	* Returns the contract end date of this best price master.
	*
	* @return the contract end date of this best price master
	*/
	@Override
	public Date getContractEndDate() {
		return _bestPriceMaster.getContractEndDate();
	}

	/**
	* Returns the contract ID of this best price master.
	*
	* @return the contract ID of this best price master
	*/
	@Override
	public java.lang.String getContractId() {
		return _bestPriceMaster.getContractId();
	}

	/**
	* Returns the contract no of this best price master.
	*
	* @return the contract no of this best price master
	*/
	@Override
	public java.lang.String getContractNo() {
		return _bestPriceMaster.getContractNo();
	}

	/**
	* Returns the contract start date of this best price master.
	*
	* @return the contract start date of this best price master
	*/
	@Override
	public Date getContractStartDate() {
		return _bestPriceMaster.getContractStartDate();
	}

	/**
	* Returns the created by of this best price master.
	*
	* @return the created by of this best price master
	*/
	@Override
	public int getCreatedBy() {
		return _bestPriceMaster.getCreatedBy();
	}

	/**
	* Returns the created date of this best price master.
	*
	* @return the created date of this best price master
	*/
	@Override
	public Date getCreatedDate() {
		return _bestPriceMaster.getCreatedDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _bestPriceMaster.getExpandoBridge();
	}

	/**
	* Returns the inbound status of this best price master.
	*
	* @return the inbound status of this best price master
	*/
	@Override
	public java.lang.String getInboundStatus() {
		return _bestPriceMaster.getInboundStatus();
	}

	/**
	* Returns the initial best price of this best price master.
	*
	* @return the initial best price of this best price master
	*/
	@Override
	public double getInitialBestPrice() {
		return _bestPriceMaster.getInitialBestPrice();
	}

	/**
	* Returns the initial discount of this best price master.
	*
	* @return the initial discount of this best price master
	*/
	@Override
	public double getInitialDiscount() {
		return _bestPriceMaster.getInitialDiscount();
	}

	/**
	* Returns the item desc of this best price master.
	*
	* @return the item desc of this best price master
	*/
	@Override
	public java.lang.String getItemDesc() {
		return _bestPriceMaster.getItemDesc();
	}

	/**
	* Returns the item ID of this best price master.
	*
	* @return the item ID of this best price master
	*/
	@Override
	public java.lang.String getItemId() {
		return _bestPriceMaster.getItemId();
	}

	/**
	* Returns the item no of this best price master.
	*
	* @return the item no of this best price master
	*/
	@Override
	public java.lang.String getItemNo() {
		return _bestPriceMaster.getItemNo();
	}

	/**
	* Returns the modified by of this best price master.
	*
	* @return the modified by of this best price master
	*/
	@Override
	public int getModifiedBy() {
		return _bestPriceMaster.getModifiedBy();
	}

	/**
	* Returns the modified date of this best price master.
	*
	* @return the modified date of this best price master
	*/
	@Override
	public Date getModifiedDate() {
		return _bestPriceMaster.getModifiedDate();
	}

	/**
	* Returns the period of this best price master.
	*
	* @return the period of this best price master
	*/
	@Override
	public java.lang.String getPeriod() {
		return _bestPriceMaster.getPeriod();
	}

	/**
	* Returns the primary key of this best price master.
	*
	* @return the primary key of this best price master
	*/
	@Override
	public int getPrimaryKey() {
		return _bestPriceMaster.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _bestPriceMaster.getPrimaryKeyObj();
	}

	/**
	* Returns the record lock status of this best price master.
	*
	* @return the record lock status of this best price master
	*/
	@Override
	public boolean getRecordLockStatus() {
		return _bestPriceMaster.getRecordLockStatus();
	}

	/**
	* Returns the selling price of this best price master.
	*
	* @return the selling price of this best price master
	*/
	@Override
	public double getSellingPrice() {
		return _bestPriceMaster.getSellingPrice();
	}

	/**
	* Returns the source of this best price master.
	*
	* @return the source of this best price master
	*/
	@Override
	public java.lang.String getSource() {
		return _bestPriceMaster.getSource();
	}

	@Override
	public int hashCode() {
		return _bestPriceMaster.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _bestPriceMaster.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _bestPriceMaster.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _bestPriceMaster.isNew();
	}

	/**
	* Returns <code>true</code> if this best price master is record lock status.
	*
	* @return <code>true</code> if this best price master is record lock status; <code>false</code> otherwise
	*/
	@Override
	public boolean isRecordLockStatus() {
		return _bestPriceMaster.isRecordLockStatus();
	}

	@Override
	public void persist() {
		_bestPriceMaster.persist();
	}

	/**
	* Sets the account ID of this best price master.
	*
	* @param accountId the account ID of this best price master
	*/
	@Override
	public void setAccountId(java.lang.String accountId) {
		_bestPriceMaster.setAccountId(accountId);
	}

	/**
	* Sets the batch ID of this best price master.
	*
	* @param batchId the batch ID of this best price master
	*/
	@Override
	public void setBatchId(java.lang.String batchId) {
		_bestPriceMaster.setBatchId(batchId);
	}

	/**
	* Sets the beginning wac package of this best price master.
	*
	* @param beginningWacPackage the beginning wac package of this best price master
	*/
	@Override
	public void setBeginningWacPackage(double beginningWacPackage) {
		_bestPriceMaster.setBeginningWacPackage(beginningWacPackage);
	}

	/**
	* Sets the best price master sid of this best price master.
	*
	* @param bestPriceMasterSid the best price master sid of this best price master
	*/
	@Override
	public void setBestPriceMasterSid(int bestPriceMasterSid) {
		_bestPriceMaster.setBestPriceMasterSid(bestPriceMasterSid);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_bestPriceMaster.setCachedModel(cachedModel);
	}

	/**
	* Sets the contract end date of this best price master.
	*
	* @param contractEndDate the contract end date of this best price master
	*/
	@Override
	public void setContractEndDate(Date contractEndDate) {
		_bestPriceMaster.setContractEndDate(contractEndDate);
	}

	/**
	* Sets the contract ID of this best price master.
	*
	* @param contractId the contract ID of this best price master
	*/
	@Override
	public void setContractId(java.lang.String contractId) {
		_bestPriceMaster.setContractId(contractId);
	}

	/**
	* Sets the contract no of this best price master.
	*
	* @param contractNo the contract no of this best price master
	*/
	@Override
	public void setContractNo(java.lang.String contractNo) {
		_bestPriceMaster.setContractNo(contractNo);
	}

	/**
	* Sets the contract start date of this best price master.
	*
	* @param contractStartDate the contract start date of this best price master
	*/
	@Override
	public void setContractStartDate(Date contractStartDate) {
		_bestPriceMaster.setContractStartDate(contractStartDate);
	}

	/**
	* Sets the created by of this best price master.
	*
	* @param createdBy the created by of this best price master
	*/
	@Override
	public void setCreatedBy(int createdBy) {
		_bestPriceMaster.setCreatedBy(createdBy);
	}

	/**
	* Sets the created date of this best price master.
	*
	* @param createdDate the created date of this best price master
	*/
	@Override
	public void setCreatedDate(Date createdDate) {
		_bestPriceMaster.setCreatedDate(createdDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_bestPriceMaster.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_bestPriceMaster.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_bestPriceMaster.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the inbound status of this best price master.
	*
	* @param inboundStatus the inbound status of this best price master
	*/
	@Override
	public void setInboundStatus(java.lang.String inboundStatus) {
		_bestPriceMaster.setInboundStatus(inboundStatus);
	}

	/**
	* Sets the initial best price of this best price master.
	*
	* @param initialBestPrice the initial best price of this best price master
	*/
	@Override
	public void setInitialBestPrice(double initialBestPrice) {
		_bestPriceMaster.setInitialBestPrice(initialBestPrice);
	}

	/**
	* Sets the initial discount of this best price master.
	*
	* @param initialDiscount the initial discount of this best price master
	*/
	@Override
	public void setInitialDiscount(double initialDiscount) {
		_bestPriceMaster.setInitialDiscount(initialDiscount);
	}

	/**
	* Sets the item desc of this best price master.
	*
	* @param itemDesc the item desc of this best price master
	*/
	@Override
	public void setItemDesc(java.lang.String itemDesc) {
		_bestPriceMaster.setItemDesc(itemDesc);
	}

	/**
	* Sets the item ID of this best price master.
	*
	* @param itemId the item ID of this best price master
	*/
	@Override
	public void setItemId(java.lang.String itemId) {
		_bestPriceMaster.setItemId(itemId);
	}

	/**
	* Sets the item no of this best price master.
	*
	* @param itemNo the item no of this best price master
	*/
	@Override
	public void setItemNo(java.lang.String itemNo) {
		_bestPriceMaster.setItemNo(itemNo);
	}

	/**
	* Sets the modified by of this best price master.
	*
	* @param modifiedBy the modified by of this best price master
	*/
	@Override
	public void setModifiedBy(int modifiedBy) {
		_bestPriceMaster.setModifiedBy(modifiedBy);
	}

	/**
	* Sets the modified date of this best price master.
	*
	* @param modifiedDate the modified date of this best price master
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_bestPriceMaster.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_bestPriceMaster.setNew(n);
	}

	/**
	* Sets the period of this best price master.
	*
	* @param period the period of this best price master
	*/
	@Override
	public void setPeriod(java.lang.String period) {
		_bestPriceMaster.setPeriod(period);
	}

	/**
	* Sets the primary key of this best price master.
	*
	* @param primaryKey the primary key of this best price master
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_bestPriceMaster.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_bestPriceMaster.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets whether this best price master is record lock status.
	*
	* @param recordLockStatus the record lock status of this best price master
	*/
	@Override
	public void setRecordLockStatus(boolean recordLockStatus) {
		_bestPriceMaster.setRecordLockStatus(recordLockStatus);
	}

	/**
	* Sets the selling price of this best price master.
	*
	* @param sellingPrice the selling price of this best price master
	*/
	@Override
	public void setSellingPrice(double sellingPrice) {
		_bestPriceMaster.setSellingPrice(sellingPrice);
	}

	/**
	* Sets the source of this best price master.
	*
	* @param source the source of this best price master
	*/
	@Override
	public void setSource(java.lang.String source) {
		_bestPriceMaster.setSource(source);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<BestPriceMaster> toCacheModel() {
		return _bestPriceMaster.toCacheModel();
	}

	@Override
	public BestPriceMaster toEscapedModel() {
		return new BestPriceMasterWrapper(_bestPriceMaster.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _bestPriceMaster.toString();
	}

	@Override
	public BestPriceMaster toUnescapedModel() {
		return new BestPriceMasterWrapper(_bestPriceMaster.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _bestPriceMaster.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof BestPriceMasterWrapper)) {
			return false;
		}

		BestPriceMasterWrapper bestPriceMasterWrapper = (BestPriceMasterWrapper)obj;

		if (Objects.equals(_bestPriceMaster,
					bestPriceMasterWrapper._bestPriceMaster)) {
			return true;
		}

		return false;
	}

	@Override
	public BestPriceMaster getWrappedModel() {
		return _bestPriceMaster;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _bestPriceMaster.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _bestPriceMaster.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_bestPriceMaster.resetOriginalValues();
	}

	private final BestPriceMaster _bestPriceMaster;
}