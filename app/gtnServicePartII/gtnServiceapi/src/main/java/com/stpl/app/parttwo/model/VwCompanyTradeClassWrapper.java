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
 * This class is a wrapper for {@link VwCompanyTradeClass}.
 * </p>
 *
 * @author
 * @see VwCompanyTradeClass
 * @generated
 */
@ProviderType
public class VwCompanyTradeClassWrapper implements VwCompanyTradeClass,
	ModelWrapper<VwCompanyTradeClass> {
	public VwCompanyTradeClassWrapper(VwCompanyTradeClass vwCompanyTradeClass) {
		_vwCompanyTradeClass = vwCompanyTradeClass;
	}

	@Override
	public Class<?> getModelClass() {
		return VwCompanyTradeClass.class;
	}

	@Override
	public String getModelClassName() {
		return VwCompanyTradeClass.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("priorTradeClass", getPriorTradeClass());
		attributes.put("companyTradeClassSid", getCompanyTradeClassSid());
		attributes.put("companyIdString", getCompanyIdString());
		attributes.put("lastUpdatedDate", getLastUpdatedDate());
		attributes.put("priorTradeClassStartDate", getPriorTradeClassStartDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("tradeClassEndDate", getTradeClassEndDate());
		attributes.put("tradeClassStartDate", getTradeClassStartDate());
		attributes.put("source", getSource());
		attributes.put("createdBy", getCreatedBy());
		attributes.put("createdDate", getCreatedDate());
		attributes.put("companyTradeClass", getCompanyTradeClass());
		attributes.put("batchId", getBatchId());
		attributes.put("addChgDelIndicator", getAddChgDelIndicator());
		attributes.put("modifiedBy", getModifiedBy());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String priorTradeClass = (String)attributes.get("priorTradeClass");

		if (priorTradeClass != null) {
			setPriorTradeClass(priorTradeClass);
		}

		Integer companyTradeClassSid = (Integer)attributes.get(
				"companyTradeClassSid");

		if (companyTradeClassSid != null) {
			setCompanyTradeClassSid(companyTradeClassSid);
		}

		String companyIdString = (String)attributes.get("companyIdString");

		if (companyIdString != null) {
			setCompanyIdString(companyIdString);
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

		String companyTradeClass = (String)attributes.get("companyTradeClass");

		if (companyTradeClass != null) {
			setCompanyTradeClass(companyTradeClass);
		}

		String batchId = (String)attributes.get("batchId");

		if (batchId != null) {
			setBatchId(batchId);
		}

		String addChgDelIndicator = (String)attributes.get("addChgDelIndicator");

		if (addChgDelIndicator != null) {
			setAddChgDelIndicator(addChgDelIndicator);
		}

		String modifiedBy = (String)attributes.get("modifiedBy");

		if (modifiedBy != null) {
			setModifiedBy(modifiedBy);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new VwCompanyTradeClassWrapper((VwCompanyTradeClass)_vwCompanyTradeClass.clone());
	}

	@Override
	public int compareTo(VwCompanyTradeClass vwCompanyTradeClass) {
		return _vwCompanyTradeClass.compareTo(vwCompanyTradeClass);
	}

	/**
	* Returns the add chg del indicator of this vw company trade class.
	*
	* @return the add chg del indicator of this vw company trade class
	*/
	@Override
	public java.lang.String getAddChgDelIndicator() {
		return _vwCompanyTradeClass.getAddChgDelIndicator();
	}

	/**
	* Returns the batch ID of this vw company trade class.
	*
	* @return the batch ID of this vw company trade class
	*/
	@Override
	public java.lang.String getBatchId() {
		return _vwCompanyTradeClass.getBatchId();
	}

	/**
	* Returns the company ID string of this vw company trade class.
	*
	* @return the company ID string of this vw company trade class
	*/
	@Override
	public java.lang.String getCompanyIdString() {
		return _vwCompanyTradeClass.getCompanyIdString();
	}

	/**
	* Returns the company trade class of this vw company trade class.
	*
	* @return the company trade class of this vw company trade class
	*/
	@Override
	public java.lang.String getCompanyTradeClass() {
		return _vwCompanyTradeClass.getCompanyTradeClass();
	}

	/**
	* Returns the company trade class sid of this vw company trade class.
	*
	* @return the company trade class sid of this vw company trade class
	*/
	@Override
	public int getCompanyTradeClassSid() {
		return _vwCompanyTradeClass.getCompanyTradeClassSid();
	}

	/**
	* Returns the created by of this vw company trade class.
	*
	* @return the created by of this vw company trade class
	*/
	@Override
	public java.lang.String getCreatedBy() {
		return _vwCompanyTradeClass.getCreatedBy();
	}

	/**
	* Returns the created date of this vw company trade class.
	*
	* @return the created date of this vw company trade class
	*/
	@Override
	public Date getCreatedDate() {
		return _vwCompanyTradeClass.getCreatedDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _vwCompanyTradeClass.getExpandoBridge();
	}

	/**
	* Returns the last updated date of this vw company trade class.
	*
	* @return the last updated date of this vw company trade class
	*/
	@Override
	public Date getLastUpdatedDate() {
		return _vwCompanyTradeClass.getLastUpdatedDate();
	}

	/**
	* Returns the modified by of this vw company trade class.
	*
	* @return the modified by of this vw company trade class
	*/
	@Override
	public java.lang.String getModifiedBy() {
		return _vwCompanyTradeClass.getModifiedBy();
	}

	/**
	* Returns the modified date of this vw company trade class.
	*
	* @return the modified date of this vw company trade class
	*/
	@Override
	public Date getModifiedDate() {
		return _vwCompanyTradeClass.getModifiedDate();
	}

	/**
	* Returns the primary key of this vw company trade class.
	*
	* @return the primary key of this vw company trade class
	*/
	@Override
	public int getPrimaryKey() {
		return _vwCompanyTradeClass.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _vwCompanyTradeClass.getPrimaryKeyObj();
	}

	/**
	* Returns the prior trade class of this vw company trade class.
	*
	* @return the prior trade class of this vw company trade class
	*/
	@Override
	public java.lang.String getPriorTradeClass() {
		return _vwCompanyTradeClass.getPriorTradeClass();
	}

	/**
	* Returns the prior trade class start date of this vw company trade class.
	*
	* @return the prior trade class start date of this vw company trade class
	*/
	@Override
	public Date getPriorTradeClassStartDate() {
		return _vwCompanyTradeClass.getPriorTradeClassStartDate();
	}

	/**
	* Returns the source of this vw company trade class.
	*
	* @return the source of this vw company trade class
	*/
	@Override
	public java.lang.String getSource() {
		return _vwCompanyTradeClass.getSource();
	}

	/**
	* Returns the trade class end date of this vw company trade class.
	*
	* @return the trade class end date of this vw company trade class
	*/
	@Override
	public Date getTradeClassEndDate() {
		return _vwCompanyTradeClass.getTradeClassEndDate();
	}

	/**
	* Returns the trade class start date of this vw company trade class.
	*
	* @return the trade class start date of this vw company trade class
	*/
	@Override
	public Date getTradeClassStartDate() {
		return _vwCompanyTradeClass.getTradeClassStartDate();
	}

	@Override
	public int hashCode() {
		return _vwCompanyTradeClass.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _vwCompanyTradeClass.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _vwCompanyTradeClass.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _vwCompanyTradeClass.isNew();
	}

	@Override
	public void persist() {
		_vwCompanyTradeClass.persist();
	}

	/**
	* Sets the add chg del indicator of this vw company trade class.
	*
	* @param addChgDelIndicator the add chg del indicator of this vw company trade class
	*/
	@Override
	public void setAddChgDelIndicator(java.lang.String addChgDelIndicator) {
		_vwCompanyTradeClass.setAddChgDelIndicator(addChgDelIndicator);
	}

	/**
	* Sets the batch ID of this vw company trade class.
	*
	* @param batchId the batch ID of this vw company trade class
	*/
	@Override
	public void setBatchId(java.lang.String batchId) {
		_vwCompanyTradeClass.setBatchId(batchId);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_vwCompanyTradeClass.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID string of this vw company trade class.
	*
	* @param companyIdString the company ID string of this vw company trade class
	*/
	@Override
	public void setCompanyIdString(java.lang.String companyIdString) {
		_vwCompanyTradeClass.setCompanyIdString(companyIdString);
	}

	/**
	* Sets the company trade class of this vw company trade class.
	*
	* @param companyTradeClass the company trade class of this vw company trade class
	*/
	@Override
	public void setCompanyTradeClass(java.lang.String companyTradeClass) {
		_vwCompanyTradeClass.setCompanyTradeClass(companyTradeClass);
	}

	/**
	* Sets the company trade class sid of this vw company trade class.
	*
	* @param companyTradeClassSid the company trade class sid of this vw company trade class
	*/
	@Override
	public void setCompanyTradeClassSid(int companyTradeClassSid) {
		_vwCompanyTradeClass.setCompanyTradeClassSid(companyTradeClassSid);
	}

	/**
	* Sets the created by of this vw company trade class.
	*
	* @param createdBy the created by of this vw company trade class
	*/
	@Override
	public void setCreatedBy(java.lang.String createdBy) {
		_vwCompanyTradeClass.setCreatedBy(createdBy);
	}

	/**
	* Sets the created date of this vw company trade class.
	*
	* @param createdDate the created date of this vw company trade class
	*/
	@Override
	public void setCreatedDate(Date createdDate) {
		_vwCompanyTradeClass.setCreatedDate(createdDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_vwCompanyTradeClass.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_vwCompanyTradeClass.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_vwCompanyTradeClass.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the last updated date of this vw company trade class.
	*
	* @param lastUpdatedDate the last updated date of this vw company trade class
	*/
	@Override
	public void setLastUpdatedDate(Date lastUpdatedDate) {
		_vwCompanyTradeClass.setLastUpdatedDate(lastUpdatedDate);
	}

	/**
	* Sets the modified by of this vw company trade class.
	*
	* @param modifiedBy the modified by of this vw company trade class
	*/
	@Override
	public void setModifiedBy(java.lang.String modifiedBy) {
		_vwCompanyTradeClass.setModifiedBy(modifiedBy);
	}

	/**
	* Sets the modified date of this vw company trade class.
	*
	* @param modifiedDate the modified date of this vw company trade class
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_vwCompanyTradeClass.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_vwCompanyTradeClass.setNew(n);
	}

	/**
	* Sets the primary key of this vw company trade class.
	*
	* @param primaryKey the primary key of this vw company trade class
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_vwCompanyTradeClass.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_vwCompanyTradeClass.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the prior trade class of this vw company trade class.
	*
	* @param priorTradeClass the prior trade class of this vw company trade class
	*/
	@Override
	public void setPriorTradeClass(java.lang.String priorTradeClass) {
		_vwCompanyTradeClass.setPriorTradeClass(priorTradeClass);
	}

	/**
	* Sets the prior trade class start date of this vw company trade class.
	*
	* @param priorTradeClassStartDate the prior trade class start date of this vw company trade class
	*/
	@Override
	public void setPriorTradeClassStartDate(Date priorTradeClassStartDate) {
		_vwCompanyTradeClass.setPriorTradeClassStartDate(priorTradeClassStartDate);
	}

	/**
	* Sets the source of this vw company trade class.
	*
	* @param source the source of this vw company trade class
	*/
	@Override
	public void setSource(java.lang.String source) {
		_vwCompanyTradeClass.setSource(source);
	}

	/**
	* Sets the trade class end date of this vw company trade class.
	*
	* @param tradeClassEndDate the trade class end date of this vw company trade class
	*/
	@Override
	public void setTradeClassEndDate(Date tradeClassEndDate) {
		_vwCompanyTradeClass.setTradeClassEndDate(tradeClassEndDate);
	}

	/**
	* Sets the trade class start date of this vw company trade class.
	*
	* @param tradeClassStartDate the trade class start date of this vw company trade class
	*/
	@Override
	public void setTradeClassStartDate(Date tradeClassStartDate) {
		_vwCompanyTradeClass.setTradeClassStartDate(tradeClassStartDate);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<VwCompanyTradeClass> toCacheModel() {
		return _vwCompanyTradeClass.toCacheModel();
	}

	@Override
	public VwCompanyTradeClass toEscapedModel() {
		return new VwCompanyTradeClassWrapper(_vwCompanyTradeClass.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _vwCompanyTradeClass.toString();
	}

	@Override
	public VwCompanyTradeClass toUnescapedModel() {
		return new VwCompanyTradeClassWrapper(_vwCompanyTradeClass.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _vwCompanyTradeClass.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof VwCompanyTradeClassWrapper)) {
			return false;
		}

		VwCompanyTradeClassWrapper vwCompanyTradeClassWrapper = (VwCompanyTradeClassWrapper)obj;

		if (Objects.equals(_vwCompanyTradeClass,
					vwCompanyTradeClassWrapper._vwCompanyTradeClass)) {
			return true;
		}

		return false;
	}

	@Override
	public VwCompanyTradeClass getWrappedModel() {
		return _vwCompanyTradeClass;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _vwCompanyTradeClass.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _vwCompanyTradeClass.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_vwCompanyTradeClass.resetOriginalValues();
	}

	private final VwCompanyTradeClass _vwCompanyTradeClass;
}