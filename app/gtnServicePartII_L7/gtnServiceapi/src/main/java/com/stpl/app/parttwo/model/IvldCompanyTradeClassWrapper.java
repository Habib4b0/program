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
 * This class is a wrapper for {@link IvldCompanyTradeClass}.
 * </p>
 *
 * @author
 * @see IvldCompanyTradeClass
 * @generated
 */
@ProviderType
public class IvldCompanyTradeClassWrapper implements IvldCompanyTradeClass,
	ModelWrapper<IvldCompanyTradeClass> {
	public IvldCompanyTradeClassWrapper(
		IvldCompanyTradeClass ivldCompanyTradeClass) {
		_ivldCompanyTradeClass = ivldCompanyTradeClass;
	}

	@Override
	public Class<?> getModelClass() {
		return IvldCompanyTradeClass.class;
	}

	@Override
	public String getModelClassName() {
		return IvldCompanyTradeClass.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("ivldCompanyTradeClassSid", getIvldCompanyTradeClassSid());
		attributes.put("priorTradeClass", getPriorTradeClass());
		attributes.put("reasonForFailure", getReasonForFailure());
		attributes.put("companyId", getCompanyId());
		attributes.put("lastUpdatedDate", getLastUpdatedDate());
		attributes.put("priorTradeClassStartDate", getPriorTradeClassStartDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("tradeClassEndDate", getTradeClassEndDate());
		attributes.put("tradeClassIntfid", getTradeClassIntfid());
		attributes.put("tradeClassStartDate", getTradeClassStartDate());
		attributes.put("source", getSource());
		attributes.put("createdBy", getCreatedBy());
		attributes.put("createdDate", getCreatedDate());
		attributes.put("addChgDelIndicator", getAddChgDelIndicator());
		attributes.put("batchId", getBatchId());
		attributes.put("errorField", getErrorField());
		attributes.put("errorCode", getErrorCode());
		attributes.put("tradeClass", getTradeClass());
		attributes.put("intfInsertedDate", getIntfInsertedDate());
		attributes.put("modifiedBy", getModifiedBy());
		attributes.put("reprocessedFlag", getReprocessedFlag());
		attributes.put("checkRecord", getCheckRecord());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Integer ivldCompanyTradeClassSid = (Integer)attributes.get(
				"ivldCompanyTradeClassSid");

		if (ivldCompanyTradeClassSid != null) {
			setIvldCompanyTradeClassSid(ivldCompanyTradeClassSid);
		}

		String priorTradeClass = (String)attributes.get("priorTradeClass");

		if (priorTradeClass != null) {
			setPriorTradeClass(priorTradeClass);
		}

		String reasonForFailure = (String)attributes.get("reasonForFailure");

		if (reasonForFailure != null) {
			setReasonForFailure(reasonForFailure);
		}

		String companyId = (String)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		String lastUpdatedDate = (String)attributes.get("lastUpdatedDate");

		if (lastUpdatedDate != null) {
			setLastUpdatedDate(lastUpdatedDate);
		}

		String priorTradeClassStartDate = (String)attributes.get(
				"priorTradeClassStartDate");

		if (priorTradeClassStartDate != null) {
			setPriorTradeClassStartDate(priorTradeClassStartDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String tradeClassEndDate = (String)attributes.get("tradeClassEndDate");

		if (tradeClassEndDate != null) {
			setTradeClassEndDate(tradeClassEndDate);
		}

		String tradeClassIntfid = (String)attributes.get("tradeClassIntfid");

		if (tradeClassIntfid != null) {
			setTradeClassIntfid(tradeClassIntfid);
		}

		String tradeClassStartDate = (String)attributes.get(
				"tradeClassStartDate");

		if (tradeClassStartDate != null) {
			setTradeClassStartDate(tradeClassStartDate);
		}

		String source = (String)attributes.get("source");

		if (source != null) {
			setSource(source);
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

		String errorField = (String)attributes.get("errorField");

		if (errorField != null) {
			setErrorField(errorField);
		}

		String errorCode = (String)attributes.get("errorCode");

		if (errorCode != null) {
			setErrorCode(errorCode);
		}

		String tradeClass = (String)attributes.get("tradeClass");

		if (tradeClass != null) {
			setTradeClass(tradeClass);
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
		return new IvldCompanyTradeClassWrapper((IvldCompanyTradeClass)_ivldCompanyTradeClass.clone());
	}

	@Override
	public int compareTo(IvldCompanyTradeClass ivldCompanyTradeClass) {
		return _ivldCompanyTradeClass.compareTo(ivldCompanyTradeClass);
	}

	/**
	* Returns the add chg del indicator of this ivld company trade class.
	*
	* @return the add chg del indicator of this ivld company trade class
	*/
	@Override
	public java.lang.String getAddChgDelIndicator() {
		return _ivldCompanyTradeClass.getAddChgDelIndicator();
	}

	/**
	* Returns the batch ID of this ivld company trade class.
	*
	* @return the batch ID of this ivld company trade class
	*/
	@Override
	public java.lang.String getBatchId() {
		return _ivldCompanyTradeClass.getBatchId();
	}

	/**
	* Returns the check record of this ivld company trade class.
	*
	* @return the check record of this ivld company trade class
	*/
	@Override
	public boolean getCheckRecord() {
		return _ivldCompanyTradeClass.getCheckRecord();
	}

	/**
	* Returns the company ID of this ivld company trade class.
	*
	* @return the company ID of this ivld company trade class
	*/
	@Override
	public java.lang.String getCompanyId() {
		return _ivldCompanyTradeClass.getCompanyId();
	}

	/**
	* Returns the created by of this ivld company trade class.
	*
	* @return the created by of this ivld company trade class
	*/
	@Override
	public java.lang.String getCreatedBy() {
		return _ivldCompanyTradeClass.getCreatedBy();
	}

	/**
	* Returns the created date of this ivld company trade class.
	*
	* @return the created date of this ivld company trade class
	*/
	@Override
	public Date getCreatedDate() {
		return _ivldCompanyTradeClass.getCreatedDate();
	}

	/**
	* Returns the error code of this ivld company trade class.
	*
	* @return the error code of this ivld company trade class
	*/
	@Override
	public java.lang.String getErrorCode() {
		return _ivldCompanyTradeClass.getErrorCode();
	}

	/**
	* Returns the error field of this ivld company trade class.
	*
	* @return the error field of this ivld company trade class
	*/
	@Override
	public java.lang.String getErrorField() {
		return _ivldCompanyTradeClass.getErrorField();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _ivldCompanyTradeClass.getExpandoBridge();
	}

	/**
	* Returns the intf inserted date of this ivld company trade class.
	*
	* @return the intf inserted date of this ivld company trade class
	*/
	@Override
	public Date getIntfInsertedDate() {
		return _ivldCompanyTradeClass.getIntfInsertedDate();
	}

	/**
	* Returns the ivld company trade class sid of this ivld company trade class.
	*
	* @return the ivld company trade class sid of this ivld company trade class
	*/
	@Override
	public int getIvldCompanyTradeClassSid() {
		return _ivldCompanyTradeClass.getIvldCompanyTradeClassSid();
	}

	/**
	* Returns the last updated date of this ivld company trade class.
	*
	* @return the last updated date of this ivld company trade class
	*/
	@Override
	public java.lang.String getLastUpdatedDate() {
		return _ivldCompanyTradeClass.getLastUpdatedDate();
	}

	/**
	* Returns the modified by of this ivld company trade class.
	*
	* @return the modified by of this ivld company trade class
	*/
	@Override
	public java.lang.String getModifiedBy() {
		return _ivldCompanyTradeClass.getModifiedBy();
	}

	/**
	* Returns the modified date of this ivld company trade class.
	*
	* @return the modified date of this ivld company trade class
	*/
	@Override
	public Date getModifiedDate() {
		return _ivldCompanyTradeClass.getModifiedDate();
	}

	/**
	* Returns the primary key of this ivld company trade class.
	*
	* @return the primary key of this ivld company trade class
	*/
	@Override
	public int getPrimaryKey() {
		return _ivldCompanyTradeClass.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _ivldCompanyTradeClass.getPrimaryKeyObj();
	}

	/**
	* Returns the prior trade class of this ivld company trade class.
	*
	* @return the prior trade class of this ivld company trade class
	*/
	@Override
	public java.lang.String getPriorTradeClass() {
		return _ivldCompanyTradeClass.getPriorTradeClass();
	}

	/**
	* Returns the prior trade class start date of this ivld company trade class.
	*
	* @return the prior trade class start date of this ivld company trade class
	*/
	@Override
	public java.lang.String getPriorTradeClassStartDate() {
		return _ivldCompanyTradeClass.getPriorTradeClassStartDate();
	}

	/**
	* Returns the reason for failure of this ivld company trade class.
	*
	* @return the reason for failure of this ivld company trade class
	*/
	@Override
	public java.lang.String getReasonForFailure() {
		return _ivldCompanyTradeClass.getReasonForFailure();
	}

	/**
	* Returns the reprocessed flag of this ivld company trade class.
	*
	* @return the reprocessed flag of this ivld company trade class
	*/
	@Override
	public java.lang.String getReprocessedFlag() {
		return _ivldCompanyTradeClass.getReprocessedFlag();
	}

	/**
	* Returns the source of this ivld company trade class.
	*
	* @return the source of this ivld company trade class
	*/
	@Override
	public java.lang.String getSource() {
		return _ivldCompanyTradeClass.getSource();
	}

	/**
	* Returns the trade class of this ivld company trade class.
	*
	* @return the trade class of this ivld company trade class
	*/
	@Override
	public java.lang.String getTradeClass() {
		return _ivldCompanyTradeClass.getTradeClass();
	}

	/**
	* Returns the trade class end date of this ivld company trade class.
	*
	* @return the trade class end date of this ivld company trade class
	*/
	@Override
	public java.lang.String getTradeClassEndDate() {
		return _ivldCompanyTradeClass.getTradeClassEndDate();
	}

	/**
	* Returns the trade class intfid of this ivld company trade class.
	*
	* @return the trade class intfid of this ivld company trade class
	*/
	@Override
	public java.lang.String getTradeClassIntfid() {
		return _ivldCompanyTradeClass.getTradeClassIntfid();
	}

	/**
	* Returns the trade class start date of this ivld company trade class.
	*
	* @return the trade class start date of this ivld company trade class
	*/
	@Override
	public java.lang.String getTradeClassStartDate() {
		return _ivldCompanyTradeClass.getTradeClassStartDate();
	}

	@Override
	public int hashCode() {
		return _ivldCompanyTradeClass.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _ivldCompanyTradeClass.isCachedModel();
	}

	/**
	* Returns <code>true</code> if this ivld company trade class is check record.
	*
	* @return <code>true</code> if this ivld company trade class is check record; <code>false</code> otherwise
	*/
	@Override
	public boolean isCheckRecord() {
		return _ivldCompanyTradeClass.isCheckRecord();
	}

	@Override
	public boolean isEscapedModel() {
		return _ivldCompanyTradeClass.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _ivldCompanyTradeClass.isNew();
	}

	@Override
	public void persist() {
		_ivldCompanyTradeClass.persist();
	}

	/**
	* Sets the add chg del indicator of this ivld company trade class.
	*
	* @param addChgDelIndicator the add chg del indicator of this ivld company trade class
	*/
	@Override
	public void setAddChgDelIndicator(java.lang.String addChgDelIndicator) {
		_ivldCompanyTradeClass.setAddChgDelIndicator(addChgDelIndicator);
	}

	/**
	* Sets the batch ID of this ivld company trade class.
	*
	* @param batchId the batch ID of this ivld company trade class
	*/
	@Override
	public void setBatchId(java.lang.String batchId) {
		_ivldCompanyTradeClass.setBatchId(batchId);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_ivldCompanyTradeClass.setCachedModel(cachedModel);
	}

	/**
	* Sets whether this ivld company trade class is check record.
	*
	* @param checkRecord the check record of this ivld company trade class
	*/
	@Override
	public void setCheckRecord(boolean checkRecord) {
		_ivldCompanyTradeClass.setCheckRecord(checkRecord);
	}

	/**
	* Sets the company ID of this ivld company trade class.
	*
	* @param companyId the company ID of this ivld company trade class
	*/
	@Override
	public void setCompanyId(java.lang.String companyId) {
		_ivldCompanyTradeClass.setCompanyId(companyId);
	}

	/**
	* Sets the created by of this ivld company trade class.
	*
	* @param createdBy the created by of this ivld company trade class
	*/
	@Override
	public void setCreatedBy(java.lang.String createdBy) {
		_ivldCompanyTradeClass.setCreatedBy(createdBy);
	}

	/**
	* Sets the created date of this ivld company trade class.
	*
	* @param createdDate the created date of this ivld company trade class
	*/
	@Override
	public void setCreatedDate(Date createdDate) {
		_ivldCompanyTradeClass.setCreatedDate(createdDate);
	}

	/**
	* Sets the error code of this ivld company trade class.
	*
	* @param errorCode the error code of this ivld company trade class
	*/
	@Override
	public void setErrorCode(java.lang.String errorCode) {
		_ivldCompanyTradeClass.setErrorCode(errorCode);
	}

	/**
	* Sets the error field of this ivld company trade class.
	*
	* @param errorField the error field of this ivld company trade class
	*/
	@Override
	public void setErrorField(java.lang.String errorField) {
		_ivldCompanyTradeClass.setErrorField(errorField);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_ivldCompanyTradeClass.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_ivldCompanyTradeClass.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_ivldCompanyTradeClass.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the intf inserted date of this ivld company trade class.
	*
	* @param intfInsertedDate the intf inserted date of this ivld company trade class
	*/
	@Override
	public void setIntfInsertedDate(Date intfInsertedDate) {
		_ivldCompanyTradeClass.setIntfInsertedDate(intfInsertedDate);
	}

	/**
	* Sets the ivld company trade class sid of this ivld company trade class.
	*
	* @param ivldCompanyTradeClassSid the ivld company trade class sid of this ivld company trade class
	*/
	@Override
	public void setIvldCompanyTradeClassSid(int ivldCompanyTradeClassSid) {
		_ivldCompanyTradeClass.setIvldCompanyTradeClassSid(ivldCompanyTradeClassSid);
	}

	/**
	* Sets the last updated date of this ivld company trade class.
	*
	* @param lastUpdatedDate the last updated date of this ivld company trade class
	*/
	@Override
	public void setLastUpdatedDate(java.lang.String lastUpdatedDate) {
		_ivldCompanyTradeClass.setLastUpdatedDate(lastUpdatedDate);
	}

	/**
	* Sets the modified by of this ivld company trade class.
	*
	* @param modifiedBy the modified by of this ivld company trade class
	*/
	@Override
	public void setModifiedBy(java.lang.String modifiedBy) {
		_ivldCompanyTradeClass.setModifiedBy(modifiedBy);
	}

	/**
	* Sets the modified date of this ivld company trade class.
	*
	* @param modifiedDate the modified date of this ivld company trade class
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_ivldCompanyTradeClass.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_ivldCompanyTradeClass.setNew(n);
	}

	/**
	* Sets the primary key of this ivld company trade class.
	*
	* @param primaryKey the primary key of this ivld company trade class
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_ivldCompanyTradeClass.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_ivldCompanyTradeClass.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the prior trade class of this ivld company trade class.
	*
	* @param priorTradeClass the prior trade class of this ivld company trade class
	*/
	@Override
	public void setPriorTradeClass(java.lang.String priorTradeClass) {
		_ivldCompanyTradeClass.setPriorTradeClass(priorTradeClass);
	}

	/**
	* Sets the prior trade class start date of this ivld company trade class.
	*
	* @param priorTradeClassStartDate the prior trade class start date of this ivld company trade class
	*/
	@Override
	public void setPriorTradeClassStartDate(
		java.lang.String priorTradeClassStartDate) {
		_ivldCompanyTradeClass.setPriorTradeClassStartDate(priorTradeClassStartDate);
	}

	/**
	* Sets the reason for failure of this ivld company trade class.
	*
	* @param reasonForFailure the reason for failure of this ivld company trade class
	*/
	@Override
	public void setReasonForFailure(java.lang.String reasonForFailure) {
		_ivldCompanyTradeClass.setReasonForFailure(reasonForFailure);
	}

	/**
	* Sets the reprocessed flag of this ivld company trade class.
	*
	* @param reprocessedFlag the reprocessed flag of this ivld company trade class
	*/
	@Override
	public void setReprocessedFlag(java.lang.String reprocessedFlag) {
		_ivldCompanyTradeClass.setReprocessedFlag(reprocessedFlag);
	}

	/**
	* Sets the source of this ivld company trade class.
	*
	* @param source the source of this ivld company trade class
	*/
	@Override
	public void setSource(java.lang.String source) {
		_ivldCompanyTradeClass.setSource(source);
	}

	/**
	* Sets the trade class of this ivld company trade class.
	*
	* @param tradeClass the trade class of this ivld company trade class
	*/
	@Override
	public void setTradeClass(java.lang.String tradeClass) {
		_ivldCompanyTradeClass.setTradeClass(tradeClass);
	}

	/**
	* Sets the trade class end date of this ivld company trade class.
	*
	* @param tradeClassEndDate the trade class end date of this ivld company trade class
	*/
	@Override
	public void setTradeClassEndDate(java.lang.String tradeClassEndDate) {
		_ivldCompanyTradeClass.setTradeClassEndDate(tradeClassEndDate);
	}

	/**
	* Sets the trade class intfid of this ivld company trade class.
	*
	* @param tradeClassIntfid the trade class intfid of this ivld company trade class
	*/
	@Override
	public void setTradeClassIntfid(java.lang.String tradeClassIntfid) {
		_ivldCompanyTradeClass.setTradeClassIntfid(tradeClassIntfid);
	}

	/**
	* Sets the trade class start date of this ivld company trade class.
	*
	* @param tradeClassStartDate the trade class start date of this ivld company trade class
	*/
	@Override
	public void setTradeClassStartDate(java.lang.String tradeClassStartDate) {
		_ivldCompanyTradeClass.setTradeClassStartDate(tradeClassStartDate);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<IvldCompanyTradeClass> toCacheModel() {
		return _ivldCompanyTradeClass.toCacheModel();
	}

	@Override
	public IvldCompanyTradeClass toEscapedModel() {
		return new IvldCompanyTradeClassWrapper(_ivldCompanyTradeClass.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _ivldCompanyTradeClass.toString();
	}

	@Override
	public IvldCompanyTradeClass toUnescapedModel() {
		return new IvldCompanyTradeClassWrapper(_ivldCompanyTradeClass.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _ivldCompanyTradeClass.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof IvldCompanyTradeClassWrapper)) {
			return false;
		}

		IvldCompanyTradeClassWrapper ivldCompanyTradeClassWrapper = (IvldCompanyTradeClassWrapper)obj;

		if (Objects.equals(_ivldCompanyTradeClass,
					ivldCompanyTradeClassWrapper._ivldCompanyTradeClass)) {
			return true;
		}

		return false;
	}

	@Override
	public IvldCompanyTradeClass getWrappedModel() {
		return _ivldCompanyTradeClass;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _ivldCompanyTradeClass.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _ivldCompanyTradeClass.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_ivldCompanyTradeClass.resetOriginalValues();
	}

	private final IvldCompanyTradeClass _ivldCompanyTradeClass;
}