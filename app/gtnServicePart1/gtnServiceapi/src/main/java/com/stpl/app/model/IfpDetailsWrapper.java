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
 * This class is a wrapper for {@link IfpDetails}.
 * </p>
 *
 * @author
 * @see IfpDetails
 * @generated
 */
@ProviderType
public class IfpDetailsWrapper implements IfpDetails, ModelWrapper<IfpDetails> {
	public IfpDetailsWrapper(IfpDetails ifpDetails) {
		_ifpDetails = ifpDetails;
	}

	@Override
	public Class<?> getModelClass() {
		return IfpDetails.class;
	}

	@Override
	public String getModelClassName() {
		return IfpDetails.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("itemMasterSid", getItemMasterSid());
		attributes.put("endDate", getEndDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("recordLockStatus", getRecordLockStatus());
		attributes.put("startDate", getStartDate());
		attributes.put("createdDate", getCreatedDate());
		attributes.put("source", getSource());
		attributes.put("createdBy", getCreatedBy());
		attributes.put("itemIfpAttachedDate", getItemIfpAttachedDate());
		attributes.put("batchId", getBatchId());
		attributes.put("modifiedBy", getModifiedBy());
		attributes.put("inboundStatus", getInboundStatus());
		attributes.put("ifpDetailsSid", getIfpDetailsSid());
		attributes.put("ifpModelSid", getIfpModelSid());
		attributes.put("itemIfpAttachedStatus", getItemIfpAttachedStatus());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Integer itemMasterSid = (Integer)attributes.get("itemMasterSid");

		if (itemMasterSid != null) {
			setItemMasterSid(itemMasterSid);
		}

		Date endDate = (Date)attributes.get("endDate");

		if (endDate != null) {
			setEndDate(endDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Boolean recordLockStatus = (Boolean)attributes.get("recordLockStatus");

		if (recordLockStatus != null) {
			setRecordLockStatus(recordLockStatus);
		}

		Date startDate = (Date)attributes.get("startDate");

		if (startDate != null) {
			setStartDate(startDate);
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

		Date itemIfpAttachedDate = (Date)attributes.get("itemIfpAttachedDate");

		if (itemIfpAttachedDate != null) {
			setItemIfpAttachedDate(itemIfpAttachedDate);
		}

		String batchId = (String)attributes.get("batchId");

		if (batchId != null) {
			setBatchId(batchId);
		}

		Integer modifiedBy = (Integer)attributes.get("modifiedBy");

		if (modifiedBy != null) {
			setModifiedBy(modifiedBy);
		}

		String inboundStatus = (String)attributes.get("inboundStatus");

		if (inboundStatus != null) {
			setInboundStatus(inboundStatus);
		}

		Integer ifpDetailsSid = (Integer)attributes.get("ifpDetailsSid");

		if (ifpDetailsSid != null) {
			setIfpDetailsSid(ifpDetailsSid);
		}

		Integer ifpModelSid = (Integer)attributes.get("ifpModelSid");

		if (ifpModelSid != null) {
			setIfpModelSid(ifpModelSid);
		}

		Integer itemIfpAttachedStatus = (Integer)attributes.get(
				"itemIfpAttachedStatus");

		if (itemIfpAttachedStatus != null) {
			setItemIfpAttachedStatus(itemIfpAttachedStatus);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new IfpDetailsWrapper((IfpDetails)_ifpDetails.clone());
	}

	@Override
	public int compareTo(IfpDetails ifpDetails) {
		return _ifpDetails.compareTo(ifpDetails);
	}

	/**
	* Returns the batch ID of this ifp details.
	*
	* @return the batch ID of this ifp details
	*/
	@Override
	public java.lang.String getBatchId() {
		return _ifpDetails.getBatchId();
	}

	/**
	* Returns the created by of this ifp details.
	*
	* @return the created by of this ifp details
	*/
	@Override
	public int getCreatedBy() {
		return _ifpDetails.getCreatedBy();
	}

	/**
	* Returns the created date of this ifp details.
	*
	* @return the created date of this ifp details
	*/
	@Override
	public Date getCreatedDate() {
		return _ifpDetails.getCreatedDate();
	}

	/**
	* Returns the end date of this ifp details.
	*
	* @return the end date of this ifp details
	*/
	@Override
	public Date getEndDate() {
		return _ifpDetails.getEndDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _ifpDetails.getExpandoBridge();
	}

	/**
	* Returns the ifp details sid of this ifp details.
	*
	* @return the ifp details sid of this ifp details
	*/
	@Override
	public int getIfpDetailsSid() {
		return _ifpDetails.getIfpDetailsSid();
	}

	/**
	* Returns the ifp model sid of this ifp details.
	*
	* @return the ifp model sid of this ifp details
	*/
	@Override
	public int getIfpModelSid() {
		return _ifpDetails.getIfpModelSid();
	}

	/**
	* Returns the inbound status of this ifp details.
	*
	* @return the inbound status of this ifp details
	*/
	@Override
	public java.lang.String getInboundStatus() {
		return _ifpDetails.getInboundStatus();
	}

	/**
	* Returns the item ifp attached date of this ifp details.
	*
	* @return the item ifp attached date of this ifp details
	*/
	@Override
	public Date getItemIfpAttachedDate() {
		return _ifpDetails.getItemIfpAttachedDate();
	}

	/**
	* Returns the item ifp attached status of this ifp details.
	*
	* @return the item ifp attached status of this ifp details
	*/
	@Override
	public int getItemIfpAttachedStatus() {
		return _ifpDetails.getItemIfpAttachedStatus();
	}

	/**
	* Returns the item master sid of this ifp details.
	*
	* @return the item master sid of this ifp details
	*/
	@Override
	public int getItemMasterSid() {
		return _ifpDetails.getItemMasterSid();
	}

	/**
	* Returns the modified by of this ifp details.
	*
	* @return the modified by of this ifp details
	*/
	@Override
	public int getModifiedBy() {
		return _ifpDetails.getModifiedBy();
	}

	/**
	* Returns the modified date of this ifp details.
	*
	* @return the modified date of this ifp details
	*/
	@Override
	public Date getModifiedDate() {
		return _ifpDetails.getModifiedDate();
	}

	/**
	* Returns the primary key of this ifp details.
	*
	* @return the primary key of this ifp details
	*/
	@Override
	public int getPrimaryKey() {
		return _ifpDetails.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _ifpDetails.getPrimaryKeyObj();
	}

	/**
	* Returns the record lock status of this ifp details.
	*
	* @return the record lock status of this ifp details
	*/
	@Override
	public boolean getRecordLockStatus() {
		return _ifpDetails.getRecordLockStatus();
	}

	/**
	* Returns the source of this ifp details.
	*
	* @return the source of this ifp details
	*/
	@Override
	public java.lang.String getSource() {
		return _ifpDetails.getSource();
	}

	/**
	* Returns the start date of this ifp details.
	*
	* @return the start date of this ifp details
	*/
	@Override
	public Date getStartDate() {
		return _ifpDetails.getStartDate();
	}

	@Override
	public int hashCode() {
		return _ifpDetails.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _ifpDetails.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _ifpDetails.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _ifpDetails.isNew();
	}

	/**
	* Returns <code>true</code> if this ifp details is record lock status.
	*
	* @return <code>true</code> if this ifp details is record lock status; <code>false</code> otherwise
	*/
	@Override
	public boolean isRecordLockStatus() {
		return _ifpDetails.isRecordLockStatus();
	}

	@Override
	public void persist() {
		_ifpDetails.persist();
	}

	/**
	* Sets the batch ID of this ifp details.
	*
	* @param batchId the batch ID of this ifp details
	*/
	@Override
	public void setBatchId(java.lang.String batchId) {
		_ifpDetails.setBatchId(batchId);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_ifpDetails.setCachedModel(cachedModel);
	}

	/**
	* Sets the created by of this ifp details.
	*
	* @param createdBy the created by of this ifp details
	*/
	@Override
	public void setCreatedBy(int createdBy) {
		_ifpDetails.setCreatedBy(createdBy);
	}

	/**
	* Sets the created date of this ifp details.
	*
	* @param createdDate the created date of this ifp details
	*/
	@Override
	public void setCreatedDate(Date createdDate) {
		_ifpDetails.setCreatedDate(createdDate);
	}

	/**
	* Sets the end date of this ifp details.
	*
	* @param endDate the end date of this ifp details
	*/
	@Override
	public void setEndDate(Date endDate) {
		_ifpDetails.setEndDate(endDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_ifpDetails.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_ifpDetails.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_ifpDetails.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the ifp details sid of this ifp details.
	*
	* @param ifpDetailsSid the ifp details sid of this ifp details
	*/
	@Override
	public void setIfpDetailsSid(int ifpDetailsSid) {
		_ifpDetails.setIfpDetailsSid(ifpDetailsSid);
	}

	/**
	* Sets the ifp model sid of this ifp details.
	*
	* @param ifpModelSid the ifp model sid of this ifp details
	*/
	@Override
	public void setIfpModelSid(int ifpModelSid) {
		_ifpDetails.setIfpModelSid(ifpModelSid);
	}

	/**
	* Sets the inbound status of this ifp details.
	*
	* @param inboundStatus the inbound status of this ifp details
	*/
	@Override
	public void setInboundStatus(java.lang.String inboundStatus) {
		_ifpDetails.setInboundStatus(inboundStatus);
	}

	/**
	* Sets the item ifp attached date of this ifp details.
	*
	* @param itemIfpAttachedDate the item ifp attached date of this ifp details
	*/
	@Override
	public void setItemIfpAttachedDate(Date itemIfpAttachedDate) {
		_ifpDetails.setItemIfpAttachedDate(itemIfpAttachedDate);
	}

	/**
	* Sets the item ifp attached status of this ifp details.
	*
	* @param itemIfpAttachedStatus the item ifp attached status of this ifp details
	*/
	@Override
	public void setItemIfpAttachedStatus(int itemIfpAttachedStatus) {
		_ifpDetails.setItemIfpAttachedStatus(itemIfpAttachedStatus);
	}

	/**
	* Sets the item master sid of this ifp details.
	*
	* @param itemMasterSid the item master sid of this ifp details
	*/
	@Override
	public void setItemMasterSid(int itemMasterSid) {
		_ifpDetails.setItemMasterSid(itemMasterSid);
	}

	/**
	* Sets the modified by of this ifp details.
	*
	* @param modifiedBy the modified by of this ifp details
	*/
	@Override
	public void setModifiedBy(int modifiedBy) {
		_ifpDetails.setModifiedBy(modifiedBy);
	}

	/**
	* Sets the modified date of this ifp details.
	*
	* @param modifiedDate the modified date of this ifp details
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_ifpDetails.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_ifpDetails.setNew(n);
	}

	/**
	* Sets the primary key of this ifp details.
	*
	* @param primaryKey the primary key of this ifp details
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_ifpDetails.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_ifpDetails.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets whether this ifp details is record lock status.
	*
	* @param recordLockStatus the record lock status of this ifp details
	*/
	@Override
	public void setRecordLockStatus(boolean recordLockStatus) {
		_ifpDetails.setRecordLockStatus(recordLockStatus);
	}

	/**
	* Sets the source of this ifp details.
	*
	* @param source the source of this ifp details
	*/
	@Override
	public void setSource(java.lang.String source) {
		_ifpDetails.setSource(source);
	}

	/**
	* Sets the start date of this ifp details.
	*
	* @param startDate the start date of this ifp details
	*/
	@Override
	public void setStartDate(Date startDate) {
		_ifpDetails.setStartDate(startDate);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<IfpDetails> toCacheModel() {
		return _ifpDetails.toCacheModel();
	}

	@Override
	public IfpDetails toEscapedModel() {
		return new IfpDetailsWrapper(_ifpDetails.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _ifpDetails.toString();
	}

	@Override
	public IfpDetails toUnescapedModel() {
		return new IfpDetailsWrapper(_ifpDetails.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _ifpDetails.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof IfpDetailsWrapper)) {
			return false;
		}

		IfpDetailsWrapper ifpDetailsWrapper = (IfpDetailsWrapper)obj;

		if (Objects.equals(_ifpDetails, ifpDetailsWrapper._ifpDetails)) {
			return true;
		}

		return false;
	}

	@Override
	public IfpDetails getWrappedModel() {
		return _ifpDetails;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _ifpDetails.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _ifpDetails.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_ifpDetails.resetOriginalValues();
	}

	private final IfpDetails _ifpDetails;
}