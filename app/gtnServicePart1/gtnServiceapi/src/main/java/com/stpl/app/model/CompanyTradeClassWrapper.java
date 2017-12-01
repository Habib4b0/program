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
 * This class is a wrapper for {@link CompanyTradeClass}.
 * </p>
 *
 * @author
 * @see CompanyTradeClass
 * @generated
 */
@ProviderType
public class CompanyTradeClassWrapper implements CompanyTradeClass,
	ModelWrapper<CompanyTradeClass> {
	public CompanyTradeClassWrapper(CompanyTradeClass companyTradeClass) {
		_companyTradeClass = companyTradeClass;
	}

	@Override
	public Class<?> getModelClass() {
		return CompanyTradeClass.class;
	}

	@Override
	public String getModelClassName() {
		return CompanyTradeClass.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("priorTradeClass", getPriorTradeClass());
		attributes.put("companyTradeClassSid", getCompanyTradeClassSid());
		attributes.put("lastUpdatedDate", getLastUpdatedDate());
		attributes.put("priorTradeClassStartDate", getPriorTradeClassStartDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("tradeClassEndDate", getTradeClassEndDate());
		attributes.put("tradeClassStartDate", getTradeClassStartDate());
		attributes.put("recordLockStatus", getRecordLockStatus());
		attributes.put("createdDate", getCreatedDate());
		attributes.put("source", getSource());
		attributes.put("createdBy", getCreatedBy());
		attributes.put("batchId", getBatchId());
		attributes.put("companyTradeClass", getCompanyTradeClass());
		attributes.put("modifiedBy", getModifiedBy());
		attributes.put("inboundStatus", getInboundStatus());
		attributes.put("companyMasterSid", getCompanyMasterSid());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Integer priorTradeClass = (Integer)attributes.get("priorTradeClass");

		if (priorTradeClass != null) {
			setPriorTradeClass(priorTradeClass);
		}

		Integer companyTradeClassSid = (Integer)attributes.get(
				"companyTradeClassSid");

		if (companyTradeClassSid != null) {
			setCompanyTradeClassSid(companyTradeClassSid);
		}

		Date lastUpdatedDate = (Date)attributes.get("lastUpdatedDate");

		if (lastUpdatedDate != null) {
			setLastUpdatedDate(lastUpdatedDate);
		}

		Date priorTradeClassStartDate = (Date)attributes.get(
				"priorTradeClassStartDate");

		if (priorTradeClassStartDate != null) {
			setPriorTradeClassStartDate(priorTradeClassStartDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Date tradeClassEndDate = (Date)attributes.get("tradeClassEndDate");

		if (tradeClassEndDate != null) {
			setTradeClassEndDate(tradeClassEndDate);
		}

		Date tradeClassStartDate = (Date)attributes.get("tradeClassStartDate");

		if (tradeClassStartDate != null) {
			setTradeClassStartDate(tradeClassStartDate);
		}

		Boolean recordLockStatus = (Boolean)attributes.get("recordLockStatus");

		if (recordLockStatus != null) {
			setRecordLockStatus(recordLockStatus);
		}

		Date createdDate = (Date)attributes.get("createdDate");

		if (createdDate != null) {
			setCreatedDate(createdDate);
		}

		String source = (String)attributes.get("source");

		if (source != null) {
			setSource(source);
		}

		Integer createdBy = (Integer)attributes.get("createdBy");

		if (createdBy != null) {
			setCreatedBy(createdBy);
		}

		String batchId = (String)attributes.get("batchId");

		if (batchId != null) {
			setBatchId(batchId);
		}

		Integer companyTradeClass = (Integer)attributes.get("companyTradeClass");

		if (companyTradeClass != null) {
			setCompanyTradeClass(companyTradeClass);
		}

		Integer modifiedBy = (Integer)attributes.get("modifiedBy");

		if (modifiedBy != null) {
			setModifiedBy(modifiedBy);
		}

		String inboundStatus = (String)attributes.get("inboundStatus");

		if (inboundStatus != null) {
			setInboundStatus(inboundStatus);
		}

		Integer companyMasterSid = (Integer)attributes.get("companyMasterSid");

		if (companyMasterSid != null) {
			setCompanyMasterSid(companyMasterSid);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new CompanyTradeClassWrapper((CompanyTradeClass)_companyTradeClass.clone());
	}

	@Override
	public int compareTo(CompanyTradeClass companyTradeClass) {
		return _companyTradeClass.compareTo(companyTradeClass);
	}

	/**
	* Returns the batch ID of this company trade class.
	*
	* @return the batch ID of this company trade class
	*/
	@Override
	public java.lang.String getBatchId() {
		return _companyTradeClass.getBatchId();
	}

	/**
	* Returns the company master sid of this company trade class.
	*
	* @return the company master sid of this company trade class
	*/
	@Override
	public int getCompanyMasterSid() {
		return _companyTradeClass.getCompanyMasterSid();
	}

	/**
	* Returns the company trade class of this company trade class.
	*
	* @return the company trade class of this company trade class
	*/
	@Override
	public int getCompanyTradeClass() {
		return _companyTradeClass.getCompanyTradeClass();
	}

	/**
	* Returns the company trade class sid of this company trade class.
	*
	* @return the company trade class sid of this company trade class
	*/
	@Override
	public int getCompanyTradeClassSid() {
		return _companyTradeClass.getCompanyTradeClassSid();
	}

	/**
	* Returns the created by of this company trade class.
	*
	* @return the created by of this company trade class
	*/
	@Override
	public int getCreatedBy() {
		return _companyTradeClass.getCreatedBy();
	}

	/**
	* Returns the created date of this company trade class.
	*
	* @return the created date of this company trade class
	*/
	@Override
	public Date getCreatedDate() {
		return _companyTradeClass.getCreatedDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _companyTradeClass.getExpandoBridge();
	}

	/**
	* Returns the inbound status of this company trade class.
	*
	* @return the inbound status of this company trade class
	*/
	@Override
	public java.lang.String getInboundStatus() {
		return _companyTradeClass.getInboundStatus();
	}

	/**
	* Returns the last updated date of this company trade class.
	*
	* @return the last updated date of this company trade class
	*/
	@Override
	public Date getLastUpdatedDate() {
		return _companyTradeClass.getLastUpdatedDate();
	}

	/**
	* Returns the modified by of this company trade class.
	*
	* @return the modified by of this company trade class
	*/
	@Override
	public int getModifiedBy() {
		return _companyTradeClass.getModifiedBy();
	}

	/**
	* Returns the modified date of this company trade class.
	*
	* @return the modified date of this company trade class
	*/
	@Override
	public Date getModifiedDate() {
		return _companyTradeClass.getModifiedDate();
	}

	/**
	* Returns the primary key of this company trade class.
	*
	* @return the primary key of this company trade class
	*/
	@Override
	public int getPrimaryKey() {
		return _companyTradeClass.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _companyTradeClass.getPrimaryKeyObj();
	}

	/**
	* Returns the prior trade class of this company trade class.
	*
	* @return the prior trade class of this company trade class
	*/
	@Override
	public int getPriorTradeClass() {
		return _companyTradeClass.getPriorTradeClass();
	}

	/**
	* Returns the prior trade class start date of this company trade class.
	*
	* @return the prior trade class start date of this company trade class
	*/
	@Override
	public Date getPriorTradeClassStartDate() {
		return _companyTradeClass.getPriorTradeClassStartDate();
	}

	/**
	* Returns the record lock status of this company trade class.
	*
	* @return the record lock status of this company trade class
	*/
	@Override
	public boolean getRecordLockStatus() {
		return _companyTradeClass.getRecordLockStatus();
	}

	/**
	* Returns the source of this company trade class.
	*
	* @return the source of this company trade class
	*/
	@Override
	public java.lang.String getSource() {
		return _companyTradeClass.getSource();
	}

	/**
	* Returns the trade class end date of this company trade class.
	*
	* @return the trade class end date of this company trade class
	*/
	@Override
	public Date getTradeClassEndDate() {
		return _companyTradeClass.getTradeClassEndDate();
	}

	/**
	* Returns the trade class start date of this company trade class.
	*
	* @return the trade class start date of this company trade class
	*/
	@Override
	public Date getTradeClassStartDate() {
		return _companyTradeClass.getTradeClassStartDate();
	}

	@Override
	public int hashCode() {
		return _companyTradeClass.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _companyTradeClass.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _companyTradeClass.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _companyTradeClass.isNew();
	}

	/**
	* Returns <code>true</code> if this company trade class is record lock status.
	*
	* @return <code>true</code> if this company trade class is record lock status; <code>false</code> otherwise
	*/
	@Override
	public boolean isRecordLockStatus() {
		return _companyTradeClass.isRecordLockStatus();
	}

	@Override
	public void persist() {
		_companyTradeClass.persist();
	}

	/**
	* Sets the batch ID of this company trade class.
	*
	* @param batchId the batch ID of this company trade class
	*/
	@Override
	public void setBatchId(java.lang.String batchId) {
		_companyTradeClass.setBatchId(batchId);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_companyTradeClass.setCachedModel(cachedModel);
	}

	/**
	* Sets the company master sid of this company trade class.
	*
	* @param companyMasterSid the company master sid of this company trade class
	*/
	@Override
	public void setCompanyMasterSid(int companyMasterSid) {
		_companyTradeClass.setCompanyMasterSid(companyMasterSid);
	}

	/**
	* Sets the company trade class of this company trade class.
	*
	* @param companyTradeClass the company trade class of this company trade class
	*/
	@Override
	public void setCompanyTradeClass(int companyTradeClass) {
		_companyTradeClass.setCompanyTradeClass(companyTradeClass);
	}

	/**
	* Sets the company trade class sid of this company trade class.
	*
	* @param companyTradeClassSid the company trade class sid of this company trade class
	*/
	@Override
	public void setCompanyTradeClassSid(int companyTradeClassSid) {
		_companyTradeClass.setCompanyTradeClassSid(companyTradeClassSid);
	}

	/**
	* Sets the created by of this company trade class.
	*
	* @param createdBy the created by of this company trade class
	*/
	@Override
	public void setCreatedBy(int createdBy) {
		_companyTradeClass.setCreatedBy(createdBy);
	}

	/**
	* Sets the created date of this company trade class.
	*
	* @param createdDate the created date of this company trade class
	*/
	@Override
	public void setCreatedDate(Date createdDate) {
		_companyTradeClass.setCreatedDate(createdDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_companyTradeClass.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_companyTradeClass.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_companyTradeClass.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the inbound status of this company trade class.
	*
	* @param inboundStatus the inbound status of this company trade class
	*/
	@Override
	public void setInboundStatus(java.lang.String inboundStatus) {
		_companyTradeClass.setInboundStatus(inboundStatus);
	}

	/**
	* Sets the last updated date of this company trade class.
	*
	* @param lastUpdatedDate the last updated date of this company trade class
	*/
	@Override
	public void setLastUpdatedDate(Date lastUpdatedDate) {
		_companyTradeClass.setLastUpdatedDate(lastUpdatedDate);
	}

	/**
	* Sets the modified by of this company trade class.
	*
	* @param modifiedBy the modified by of this company trade class
	*/
	@Override
	public void setModifiedBy(int modifiedBy) {
		_companyTradeClass.setModifiedBy(modifiedBy);
	}

	/**
	* Sets the modified date of this company trade class.
	*
	* @param modifiedDate the modified date of this company trade class
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_companyTradeClass.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_companyTradeClass.setNew(n);
	}

	/**
	* Sets the primary key of this company trade class.
	*
	* @param primaryKey the primary key of this company trade class
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_companyTradeClass.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_companyTradeClass.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the prior trade class of this company trade class.
	*
	* @param priorTradeClass the prior trade class of this company trade class
	*/
	@Override
	public void setPriorTradeClass(int priorTradeClass) {
		_companyTradeClass.setPriorTradeClass(priorTradeClass);
	}

	/**
	* Sets the prior trade class start date of this company trade class.
	*
	* @param priorTradeClassStartDate the prior trade class start date of this company trade class
	*/
	@Override
	public void setPriorTradeClassStartDate(Date priorTradeClassStartDate) {
		_companyTradeClass.setPriorTradeClassStartDate(priorTradeClassStartDate);
	}

	/**
	* Sets whether this company trade class is record lock status.
	*
	* @param recordLockStatus the record lock status of this company trade class
	*/
	@Override
	public void setRecordLockStatus(boolean recordLockStatus) {
		_companyTradeClass.setRecordLockStatus(recordLockStatus);
	}

	/**
	* Sets the source of this company trade class.
	*
	* @param source the source of this company trade class
	*/
	@Override
	public void setSource(java.lang.String source) {
		_companyTradeClass.setSource(source);
	}

	/**
	* Sets the trade class end date of this company trade class.
	*
	* @param tradeClassEndDate the trade class end date of this company trade class
	*/
	@Override
	public void setTradeClassEndDate(Date tradeClassEndDate) {
		_companyTradeClass.setTradeClassEndDate(tradeClassEndDate);
	}

	/**
	* Sets the trade class start date of this company trade class.
	*
	* @param tradeClassStartDate the trade class start date of this company trade class
	*/
	@Override
	public void setTradeClassStartDate(Date tradeClassStartDate) {
		_companyTradeClass.setTradeClassStartDate(tradeClassStartDate);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<CompanyTradeClass> toCacheModel() {
		return _companyTradeClass.toCacheModel();
	}

	@Override
	public CompanyTradeClass toEscapedModel() {
		return new CompanyTradeClassWrapper(_companyTradeClass.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _companyTradeClass.toString();
	}

	@Override
	public CompanyTradeClass toUnescapedModel() {
		return new CompanyTradeClassWrapper(_companyTradeClass.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _companyTradeClass.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CompanyTradeClassWrapper)) {
			return false;
		}

		CompanyTradeClassWrapper companyTradeClassWrapper = (CompanyTradeClassWrapper)obj;

		if (Objects.equals(_companyTradeClass,
					companyTradeClassWrapper._companyTradeClass)) {
			return true;
		}

		return false;
	}

	@Override
	public CompanyTradeClass getWrappedModel() {
		return _companyTradeClass;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _companyTradeClass.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _companyTradeClass.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_companyTradeClass.resetOriginalValues();
	}

	private final CompanyTradeClass _companyTradeClass;
}