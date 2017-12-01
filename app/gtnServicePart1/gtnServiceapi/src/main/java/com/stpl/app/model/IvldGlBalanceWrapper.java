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
 * This class is a wrapper for {@link IvldGlBalance}.
 * </p>
 *
 * @author
 * @see IvldGlBalance
 * @generated
 */
@ProviderType
public class IvldGlBalanceWrapper implements IvldGlBalance,
	ModelWrapper<IvldGlBalance> {
	public IvldGlBalanceWrapper(IvldGlBalance ivldGlBalance) {
		_ivldGlBalance = ivldGlBalance;
	}

	@Override
	public Class<?> getModelClass() {
		return IvldGlBalance.class;
	}

	@Override
	public String getModelClassName() {
		return IvldGlBalance.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("balance", getBalance());
		attributes.put("accountNo", getAccountNo());
		attributes.put("reasonForFailure", getReasonForFailure());
		attributes.put("accountId", getAccountId());
		attributes.put("year", getYear());
		attributes.put("period", getPeriod());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("isActive", getIsActive());
		attributes.put("source", getSource());
		attributes.put("uploadDate", getUploadDate());
		attributes.put("createdBy", getCreatedBy());
		attributes.put("createdDate", getCreatedDate());
		attributes.put("addChgDelIndicator", getAddChgDelIndicator());
		attributes.put("batchId", getBatchId());
		attributes.put("closeIndicator", getCloseIndicator());
		attributes.put("insertedDate", getInsertedDate());
		attributes.put("errorField", getErrorField());
		attributes.put("ivldGlBalanceSid", getIvldGlBalanceSid());
		attributes.put("errorCode", getErrorCode());
		attributes.put("glBalanceIntfid", getGlBalanceIntfid());
		attributes.put("intfInsertedDate", getIntfInsertedDate());
		attributes.put("modifiedBy", getModifiedBy());
		attributes.put("reprocessedFlag", getReprocessedFlag());
		attributes.put("checkRecord", getCheckRecord());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String balance = (String)attributes.get("balance");

		if (balance != null) {
			setBalance(balance);
		}

		String accountNo = (String)attributes.get("accountNo");

		if (accountNo != null) {
			setAccountNo(accountNo);
		}

		String reasonForFailure = (String)attributes.get("reasonForFailure");

		if (reasonForFailure != null) {
			setReasonForFailure(reasonForFailure);
		}

		String accountId = (String)attributes.get("accountId");

		if (accountId != null) {
			setAccountId(accountId);
		}

		String year = (String)attributes.get("year");

		if (year != null) {
			setYear(year);
		}

		String period = (String)attributes.get("period");

		if (period != null) {
			setPeriod(period);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String isActive = (String)attributes.get("isActive");

		if (isActive != null) {
			setIsActive(isActive);
		}

		String source = (String)attributes.get("source");

		if (source != null) {
			setSource(source);
		}

		String uploadDate = (String)attributes.get("uploadDate");

		if (uploadDate != null) {
			setUploadDate(uploadDate);
		}

		String createdBy = (String)attributes.get("createdBy");

		if (createdBy != null) {
			setCreatedBy(createdBy);
		}

		Date createdDate = (Date)attributes.get("createdDate");

		if (createdDate != null) {
			setCreatedDate(createdDate);
		}

		String addChgDelIndicator = (String)attributes.get("addChgDelIndicator");

		if (addChgDelIndicator != null) {
			setAddChgDelIndicator(addChgDelIndicator);
		}

		String batchId = (String)attributes.get("batchId");

		if (batchId != null) {
			setBatchId(batchId);
		}

		String closeIndicator = (String)attributes.get("closeIndicator");

		if (closeIndicator != null) {
			setCloseIndicator(closeIndicator);
		}

		String insertedDate = (String)attributes.get("insertedDate");

		if (insertedDate != null) {
			setInsertedDate(insertedDate);
		}

		String errorField = (String)attributes.get("errorField");

		if (errorField != null) {
			setErrorField(errorField);
		}

		Integer ivldGlBalanceSid = (Integer)attributes.get("ivldGlBalanceSid");

		if (ivldGlBalanceSid != null) {
			setIvldGlBalanceSid(ivldGlBalanceSid);
		}

		String errorCode = (String)attributes.get("errorCode");

		if (errorCode != null) {
			setErrorCode(errorCode);
		}

		String glBalanceIntfid = (String)attributes.get("glBalanceIntfid");

		if (glBalanceIntfid != null) {
			setGlBalanceIntfid(glBalanceIntfid);
		}

		Date intfInsertedDate = (Date)attributes.get("intfInsertedDate");

		if (intfInsertedDate != null) {
			setIntfInsertedDate(intfInsertedDate);
		}

		String modifiedBy = (String)attributes.get("modifiedBy");

		if (modifiedBy != null) {
			setModifiedBy(modifiedBy);
		}

		String reprocessedFlag = (String)attributes.get("reprocessedFlag");

		if (reprocessedFlag != null) {
			setReprocessedFlag(reprocessedFlag);
		}

		Boolean checkRecord = (Boolean)attributes.get("checkRecord");

		if (checkRecord != null) {
			setCheckRecord(checkRecord);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new IvldGlBalanceWrapper((IvldGlBalance)_ivldGlBalance.clone());
	}

	@Override
	public int compareTo(IvldGlBalance ivldGlBalance) {
		return _ivldGlBalance.compareTo(ivldGlBalance);
	}

	/**
	* Returns the account ID of this ivld gl balance.
	*
	* @return the account ID of this ivld gl balance
	*/
	@Override
	public java.lang.String getAccountId() {
		return _ivldGlBalance.getAccountId();
	}

	/**
	* Returns the account no of this ivld gl balance.
	*
	* @return the account no of this ivld gl balance
	*/
	@Override
	public java.lang.String getAccountNo() {
		return _ivldGlBalance.getAccountNo();
	}

	/**
	* Returns the add chg del indicator of this ivld gl balance.
	*
	* @return the add chg del indicator of this ivld gl balance
	*/
	@Override
	public java.lang.String getAddChgDelIndicator() {
		return _ivldGlBalance.getAddChgDelIndicator();
	}

	/**
	* Returns the balance of this ivld gl balance.
	*
	* @return the balance of this ivld gl balance
	*/
	@Override
	public java.lang.String getBalance() {
		return _ivldGlBalance.getBalance();
	}

	/**
	* Returns the batch ID of this ivld gl balance.
	*
	* @return the batch ID of this ivld gl balance
	*/
	@Override
	public java.lang.String getBatchId() {
		return _ivldGlBalance.getBatchId();
	}

	/**
	* Returns the check record of this ivld gl balance.
	*
	* @return the check record of this ivld gl balance
	*/
	@Override
	public boolean getCheckRecord() {
		return _ivldGlBalance.getCheckRecord();
	}

	/**
	* Returns the close indicator of this ivld gl balance.
	*
	* @return the close indicator of this ivld gl balance
	*/
	@Override
	public java.lang.String getCloseIndicator() {
		return _ivldGlBalance.getCloseIndicator();
	}

	/**
	* Returns the created by of this ivld gl balance.
	*
	* @return the created by of this ivld gl balance
	*/
	@Override
	public java.lang.String getCreatedBy() {
		return _ivldGlBalance.getCreatedBy();
	}

	/**
	* Returns the created date of this ivld gl balance.
	*
	* @return the created date of this ivld gl balance
	*/
	@Override
	public Date getCreatedDate() {
		return _ivldGlBalance.getCreatedDate();
	}

	/**
	* Returns the error code of this ivld gl balance.
	*
	* @return the error code of this ivld gl balance
	*/
	@Override
	public java.lang.String getErrorCode() {
		return _ivldGlBalance.getErrorCode();
	}

	/**
	* Returns the error field of this ivld gl balance.
	*
	* @return the error field of this ivld gl balance
	*/
	@Override
	public java.lang.String getErrorField() {
		return _ivldGlBalance.getErrorField();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _ivldGlBalance.getExpandoBridge();
	}

	/**
	* Returns the gl balance intfid of this ivld gl balance.
	*
	* @return the gl balance intfid of this ivld gl balance
	*/
	@Override
	public java.lang.String getGlBalanceIntfid() {
		return _ivldGlBalance.getGlBalanceIntfid();
	}

	/**
	* Returns the inserted date of this ivld gl balance.
	*
	* @return the inserted date of this ivld gl balance
	*/
	@Override
	public java.lang.String getInsertedDate() {
		return _ivldGlBalance.getInsertedDate();
	}

	/**
	* Returns the intf inserted date of this ivld gl balance.
	*
	* @return the intf inserted date of this ivld gl balance
	*/
	@Override
	public Date getIntfInsertedDate() {
		return _ivldGlBalance.getIntfInsertedDate();
	}

	/**
	* Returns the is active of this ivld gl balance.
	*
	* @return the is active of this ivld gl balance
	*/
	@Override
	public java.lang.String getIsActive() {
		return _ivldGlBalance.getIsActive();
	}

	/**
	* Returns the ivld gl balance sid of this ivld gl balance.
	*
	* @return the ivld gl balance sid of this ivld gl balance
	*/
	@Override
	public int getIvldGlBalanceSid() {
		return _ivldGlBalance.getIvldGlBalanceSid();
	}

	/**
	* Returns the modified by of this ivld gl balance.
	*
	* @return the modified by of this ivld gl balance
	*/
	@Override
	public java.lang.String getModifiedBy() {
		return _ivldGlBalance.getModifiedBy();
	}

	/**
	* Returns the modified date of this ivld gl balance.
	*
	* @return the modified date of this ivld gl balance
	*/
	@Override
	public Date getModifiedDate() {
		return _ivldGlBalance.getModifiedDate();
	}

	/**
	* Returns the period of this ivld gl balance.
	*
	* @return the period of this ivld gl balance
	*/
	@Override
	public java.lang.String getPeriod() {
		return _ivldGlBalance.getPeriod();
	}

	/**
	* Returns the primary key of this ivld gl balance.
	*
	* @return the primary key of this ivld gl balance
	*/
	@Override
	public int getPrimaryKey() {
		return _ivldGlBalance.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _ivldGlBalance.getPrimaryKeyObj();
	}

	/**
	* Returns the reason for failure of this ivld gl balance.
	*
	* @return the reason for failure of this ivld gl balance
	*/
	@Override
	public java.lang.String getReasonForFailure() {
		return _ivldGlBalance.getReasonForFailure();
	}

	/**
	* Returns the reprocessed flag of this ivld gl balance.
	*
	* @return the reprocessed flag of this ivld gl balance
	*/
	@Override
	public java.lang.String getReprocessedFlag() {
		return _ivldGlBalance.getReprocessedFlag();
	}

	/**
	* Returns the source of this ivld gl balance.
	*
	* @return the source of this ivld gl balance
	*/
	@Override
	public java.lang.String getSource() {
		return _ivldGlBalance.getSource();
	}

	/**
	* Returns the upload date of this ivld gl balance.
	*
	* @return the upload date of this ivld gl balance
	*/
	@Override
	public java.lang.String getUploadDate() {
		return _ivldGlBalance.getUploadDate();
	}

	/**
	* Returns the year of this ivld gl balance.
	*
	* @return the year of this ivld gl balance
	*/
	@Override
	public java.lang.String getYear() {
		return _ivldGlBalance.getYear();
	}

	@Override
	public int hashCode() {
		return _ivldGlBalance.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _ivldGlBalance.isCachedModel();
	}

	/**
	* Returns <code>true</code> if this ivld gl balance is check record.
	*
	* @return <code>true</code> if this ivld gl balance is check record; <code>false</code> otherwise
	*/
	@Override
	public boolean isCheckRecord() {
		return _ivldGlBalance.isCheckRecord();
	}

	@Override
	public boolean isEscapedModel() {
		return _ivldGlBalance.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _ivldGlBalance.isNew();
	}

	@Override
	public void persist() {
		_ivldGlBalance.persist();
	}

	/**
	* Sets the account ID of this ivld gl balance.
	*
	* @param accountId the account ID of this ivld gl balance
	*/
	@Override
	public void setAccountId(java.lang.String accountId) {
		_ivldGlBalance.setAccountId(accountId);
	}

	/**
	* Sets the account no of this ivld gl balance.
	*
	* @param accountNo the account no of this ivld gl balance
	*/
	@Override
	public void setAccountNo(java.lang.String accountNo) {
		_ivldGlBalance.setAccountNo(accountNo);
	}

	/**
	* Sets the add chg del indicator of this ivld gl balance.
	*
	* @param addChgDelIndicator the add chg del indicator of this ivld gl balance
	*/
	@Override
	public void setAddChgDelIndicator(java.lang.String addChgDelIndicator) {
		_ivldGlBalance.setAddChgDelIndicator(addChgDelIndicator);
	}

	/**
	* Sets the balance of this ivld gl balance.
	*
	* @param balance the balance of this ivld gl balance
	*/
	@Override
	public void setBalance(java.lang.String balance) {
		_ivldGlBalance.setBalance(balance);
	}

	/**
	* Sets the batch ID of this ivld gl balance.
	*
	* @param batchId the batch ID of this ivld gl balance
	*/
	@Override
	public void setBatchId(java.lang.String batchId) {
		_ivldGlBalance.setBatchId(batchId);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_ivldGlBalance.setCachedModel(cachedModel);
	}

	/**
	* Sets whether this ivld gl balance is check record.
	*
	* @param checkRecord the check record of this ivld gl balance
	*/
	@Override
	public void setCheckRecord(boolean checkRecord) {
		_ivldGlBalance.setCheckRecord(checkRecord);
	}

	/**
	* Sets the close indicator of this ivld gl balance.
	*
	* @param closeIndicator the close indicator of this ivld gl balance
	*/
	@Override
	public void setCloseIndicator(java.lang.String closeIndicator) {
		_ivldGlBalance.setCloseIndicator(closeIndicator);
	}

	/**
	* Sets the created by of this ivld gl balance.
	*
	* @param createdBy the created by of this ivld gl balance
	*/
	@Override
	public void setCreatedBy(java.lang.String createdBy) {
		_ivldGlBalance.setCreatedBy(createdBy);
	}

	/**
	* Sets the created date of this ivld gl balance.
	*
	* @param createdDate the created date of this ivld gl balance
	*/
	@Override
	public void setCreatedDate(Date createdDate) {
		_ivldGlBalance.setCreatedDate(createdDate);
	}

	/**
	* Sets the error code of this ivld gl balance.
	*
	* @param errorCode the error code of this ivld gl balance
	*/
	@Override
	public void setErrorCode(java.lang.String errorCode) {
		_ivldGlBalance.setErrorCode(errorCode);
	}

	/**
	* Sets the error field of this ivld gl balance.
	*
	* @param errorField the error field of this ivld gl balance
	*/
	@Override
	public void setErrorField(java.lang.String errorField) {
		_ivldGlBalance.setErrorField(errorField);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_ivldGlBalance.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_ivldGlBalance.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_ivldGlBalance.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the gl balance intfid of this ivld gl balance.
	*
	* @param glBalanceIntfid the gl balance intfid of this ivld gl balance
	*/
	@Override
	public void setGlBalanceIntfid(java.lang.String glBalanceIntfid) {
		_ivldGlBalance.setGlBalanceIntfid(glBalanceIntfid);
	}

	/**
	* Sets the inserted date of this ivld gl balance.
	*
	* @param insertedDate the inserted date of this ivld gl balance
	*/
	@Override
	public void setInsertedDate(java.lang.String insertedDate) {
		_ivldGlBalance.setInsertedDate(insertedDate);
	}

	/**
	* Sets the intf inserted date of this ivld gl balance.
	*
	* @param intfInsertedDate the intf inserted date of this ivld gl balance
	*/
	@Override
	public void setIntfInsertedDate(Date intfInsertedDate) {
		_ivldGlBalance.setIntfInsertedDate(intfInsertedDate);
	}

	/**
	* Sets the is active of this ivld gl balance.
	*
	* @param isActive the is active of this ivld gl balance
	*/
	@Override
	public void setIsActive(java.lang.String isActive) {
		_ivldGlBalance.setIsActive(isActive);
	}

	/**
	* Sets the ivld gl balance sid of this ivld gl balance.
	*
	* @param ivldGlBalanceSid the ivld gl balance sid of this ivld gl balance
	*/
	@Override
	public void setIvldGlBalanceSid(int ivldGlBalanceSid) {
		_ivldGlBalance.setIvldGlBalanceSid(ivldGlBalanceSid);
	}

	/**
	* Sets the modified by of this ivld gl balance.
	*
	* @param modifiedBy the modified by of this ivld gl balance
	*/
	@Override
	public void setModifiedBy(java.lang.String modifiedBy) {
		_ivldGlBalance.setModifiedBy(modifiedBy);
	}

	/**
	* Sets the modified date of this ivld gl balance.
	*
	* @param modifiedDate the modified date of this ivld gl balance
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_ivldGlBalance.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_ivldGlBalance.setNew(n);
	}

	/**
	* Sets the period of this ivld gl balance.
	*
	* @param period the period of this ivld gl balance
	*/
	@Override
	public void setPeriod(java.lang.String period) {
		_ivldGlBalance.setPeriod(period);
	}

	/**
	* Sets the primary key of this ivld gl balance.
	*
	* @param primaryKey the primary key of this ivld gl balance
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_ivldGlBalance.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_ivldGlBalance.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the reason for failure of this ivld gl balance.
	*
	* @param reasonForFailure the reason for failure of this ivld gl balance
	*/
	@Override
	public void setReasonForFailure(java.lang.String reasonForFailure) {
		_ivldGlBalance.setReasonForFailure(reasonForFailure);
	}

	/**
	* Sets the reprocessed flag of this ivld gl balance.
	*
	* @param reprocessedFlag the reprocessed flag of this ivld gl balance
	*/
	@Override
	public void setReprocessedFlag(java.lang.String reprocessedFlag) {
		_ivldGlBalance.setReprocessedFlag(reprocessedFlag);
	}

	/**
	* Sets the source of this ivld gl balance.
	*
	* @param source the source of this ivld gl balance
	*/
	@Override
	public void setSource(java.lang.String source) {
		_ivldGlBalance.setSource(source);
	}

	/**
	* Sets the upload date of this ivld gl balance.
	*
	* @param uploadDate the upload date of this ivld gl balance
	*/
	@Override
	public void setUploadDate(java.lang.String uploadDate) {
		_ivldGlBalance.setUploadDate(uploadDate);
	}

	/**
	* Sets the year of this ivld gl balance.
	*
	* @param year the year of this ivld gl balance
	*/
	@Override
	public void setYear(java.lang.String year) {
		_ivldGlBalance.setYear(year);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<IvldGlBalance> toCacheModel() {
		return _ivldGlBalance.toCacheModel();
	}

	@Override
	public IvldGlBalance toEscapedModel() {
		return new IvldGlBalanceWrapper(_ivldGlBalance.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _ivldGlBalance.toString();
	}

	@Override
	public IvldGlBalance toUnescapedModel() {
		return new IvldGlBalanceWrapper(_ivldGlBalance.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _ivldGlBalance.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof IvldGlBalanceWrapper)) {
			return false;
		}

		IvldGlBalanceWrapper ivldGlBalanceWrapper = (IvldGlBalanceWrapper)obj;

		if (Objects.equals(_ivldGlBalance, ivldGlBalanceWrapper._ivldGlBalance)) {
			return true;
		}

		return false;
	}

	@Override
	public IvldGlBalance getWrappedModel() {
		return _ivldGlBalance;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _ivldGlBalance.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _ivldGlBalance.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_ivldGlBalance.resetOriginalValues();
	}

	private final IvldGlBalance _ivldGlBalance;
}