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
 * This class is a wrapper for {@link IfpContractDetails}.
 * </p>
 *
 * @author
 * @see IfpContractDetails
 * @generated
 */
@ProviderType
public class IfpContractDetailsWrapper implements IfpContractDetails,
	ModelWrapper<IfpContractDetails> {
	public IfpContractDetailsWrapper(IfpContractDetails ifpContractDetails) {
		_ifpContractDetails = ifpContractDetails;
	}

	@Override
	public Class<?> getModelClass() {
		return IfpContractDetails.class;
	}

	@Override
	public String getModelClassName() {
		return IfpContractDetails.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("itemStatus", getItemStatus());
		attributes.put("itemMasterSid", getItemMasterSid());
		attributes.put("ifpContractAttachedDate", getIfpContractAttachedDate());
		attributes.put("itemEndDate", getItemEndDate());
		attributes.put("totalVolumeCommitment", getTotalVolumeCommitment());
		attributes.put("totalDollarCommitment", getTotalDollarCommitment());
		attributes.put("ifpContractAttachedStatus",
			getIfpContractAttachedStatus());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("totalMarketshareCommitment",
			getTotalMarketshareCommitment());
		attributes.put("recordLockStatus", getRecordLockStatus());
		attributes.put("createdDate", getCreatedDate());
		attributes.put("createdBy", getCreatedBy());
		attributes.put("source", getSource());
		attributes.put("itemStartDate", getItemStartDate());
		attributes.put("batchId", getBatchId());
		attributes.put("ifpContractDetailsSid", getIfpContractDetailsSid());
		attributes.put("modifiedBy", getModifiedBy());
		attributes.put("inboundStatus", getInboundStatus());
		attributes.put("ifpContractSid", getIfpContractSid());
		attributes.put("commitmentPeriod", getCommitmentPeriod());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Integer itemStatus = (Integer)attributes.get("itemStatus");

		if (itemStatus != null) {
			setItemStatus(itemStatus);
		}

		Integer itemMasterSid = (Integer)attributes.get("itemMasterSid");

		if (itemMasterSid != null) {
			setItemMasterSid(itemMasterSid);
		}

		Date ifpContractAttachedDate = (Date)attributes.get(
				"ifpContractAttachedDate");

		if (ifpContractAttachedDate != null) {
			setIfpContractAttachedDate(ifpContractAttachedDate);
		}

		Date itemEndDate = (Date)attributes.get("itemEndDate");

		if (itemEndDate != null) {
			setItemEndDate(itemEndDate);
		}

		String totalVolumeCommitment = (String)attributes.get(
				"totalVolumeCommitment");

		if (totalVolumeCommitment != null) {
			setTotalVolumeCommitment(totalVolumeCommitment);
		}

		String totalDollarCommitment = (String)attributes.get(
				"totalDollarCommitment");

		if (totalDollarCommitment != null) {
			setTotalDollarCommitment(totalDollarCommitment);
		}

		Integer ifpContractAttachedStatus = (Integer)attributes.get(
				"ifpContractAttachedStatus");

		if (ifpContractAttachedStatus != null) {
			setIfpContractAttachedStatus(ifpContractAttachedStatus);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String totalMarketshareCommitment = (String)attributes.get(
				"totalMarketshareCommitment");

		if (totalMarketshareCommitment != null) {
			setTotalMarketshareCommitment(totalMarketshareCommitment);
		}

		Boolean recordLockStatus = (Boolean)attributes.get("recordLockStatus");

		if (recordLockStatus != null) {
			setRecordLockStatus(recordLockStatus);
		}

		Date createdDate = (Date)attributes.get("createdDate");

		if (createdDate != null) {
			setCreatedDate(createdDate);
		}

		Integer createdBy = (Integer)attributes.get("createdBy");

		if (createdBy != null) {
			setCreatedBy(createdBy);
		}

		String source = (String)attributes.get("source");

		if (source != null) {
			setSource(source);
		}

		Date itemStartDate = (Date)attributes.get("itemStartDate");

		if (itemStartDate != null) {
			setItemStartDate(itemStartDate);
		}

		String batchId = (String)attributes.get("batchId");

		if (batchId != null) {
			setBatchId(batchId);
		}

		Integer ifpContractDetailsSid = (Integer)attributes.get(
				"ifpContractDetailsSid");

		if (ifpContractDetailsSid != null) {
			setIfpContractDetailsSid(ifpContractDetailsSid);
		}

		Integer modifiedBy = (Integer)attributes.get("modifiedBy");

		if (modifiedBy != null) {
			setModifiedBy(modifiedBy);
		}

		String inboundStatus = (String)attributes.get("inboundStatus");

		if (inboundStatus != null) {
			setInboundStatus(inboundStatus);
		}

		Integer ifpContractSid = (Integer)attributes.get("ifpContractSid");

		if (ifpContractSid != null) {
			setIfpContractSid(ifpContractSid);
		}

		String commitmentPeriod = (String)attributes.get("commitmentPeriod");

		if (commitmentPeriod != null) {
			setCommitmentPeriod(commitmentPeriod);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new IfpContractDetailsWrapper((IfpContractDetails)_ifpContractDetails.clone());
	}

	@Override
	public int compareTo(IfpContractDetails ifpContractDetails) {
		return _ifpContractDetails.compareTo(ifpContractDetails);
	}

	/**
	* Returns the batch ID of this ifp contract details.
	*
	* @return the batch ID of this ifp contract details
	*/
	@Override
	public java.lang.String getBatchId() {
		return _ifpContractDetails.getBatchId();
	}

	/**
	* Returns the commitment period of this ifp contract details.
	*
	* @return the commitment period of this ifp contract details
	*/
	@Override
	public java.lang.String getCommitmentPeriod() {
		return _ifpContractDetails.getCommitmentPeriod();
	}

	/**
	* Returns the created by of this ifp contract details.
	*
	* @return the created by of this ifp contract details
	*/
	@Override
	public int getCreatedBy() {
		return _ifpContractDetails.getCreatedBy();
	}

	/**
	* Returns the created date of this ifp contract details.
	*
	* @return the created date of this ifp contract details
	*/
	@Override
	public Date getCreatedDate() {
		return _ifpContractDetails.getCreatedDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _ifpContractDetails.getExpandoBridge();
	}

	/**
	* Returns the ifp contract attached date of this ifp contract details.
	*
	* @return the ifp contract attached date of this ifp contract details
	*/
	@Override
	public Date getIfpContractAttachedDate() {
		return _ifpContractDetails.getIfpContractAttachedDate();
	}

	/**
	* Returns the ifp contract attached status of this ifp contract details.
	*
	* @return the ifp contract attached status of this ifp contract details
	*/
	@Override
	public int getIfpContractAttachedStatus() {
		return _ifpContractDetails.getIfpContractAttachedStatus();
	}

	/**
	* Returns the ifp contract details sid of this ifp contract details.
	*
	* @return the ifp contract details sid of this ifp contract details
	*/
	@Override
	public int getIfpContractDetailsSid() {
		return _ifpContractDetails.getIfpContractDetailsSid();
	}

	/**
	* Returns the ifp contract sid of this ifp contract details.
	*
	* @return the ifp contract sid of this ifp contract details
	*/
	@Override
	public int getIfpContractSid() {
		return _ifpContractDetails.getIfpContractSid();
	}

	/**
	* Returns the inbound status of this ifp contract details.
	*
	* @return the inbound status of this ifp contract details
	*/
	@Override
	public java.lang.String getInboundStatus() {
		return _ifpContractDetails.getInboundStatus();
	}

	/**
	* Returns the item end date of this ifp contract details.
	*
	* @return the item end date of this ifp contract details
	*/
	@Override
	public Date getItemEndDate() {
		return _ifpContractDetails.getItemEndDate();
	}

	/**
	* Returns the item master sid of this ifp contract details.
	*
	* @return the item master sid of this ifp contract details
	*/
	@Override
	public int getItemMasterSid() {
		return _ifpContractDetails.getItemMasterSid();
	}

	/**
	* Returns the item start date of this ifp contract details.
	*
	* @return the item start date of this ifp contract details
	*/
	@Override
	public Date getItemStartDate() {
		return _ifpContractDetails.getItemStartDate();
	}

	/**
	* Returns the item status of this ifp contract details.
	*
	* @return the item status of this ifp contract details
	*/
	@Override
	public int getItemStatus() {
		return _ifpContractDetails.getItemStatus();
	}

	/**
	* Returns the modified by of this ifp contract details.
	*
	* @return the modified by of this ifp contract details
	*/
	@Override
	public int getModifiedBy() {
		return _ifpContractDetails.getModifiedBy();
	}

	/**
	* Returns the modified date of this ifp contract details.
	*
	* @return the modified date of this ifp contract details
	*/
	@Override
	public Date getModifiedDate() {
		return _ifpContractDetails.getModifiedDate();
	}

	/**
	* Returns the primary key of this ifp contract details.
	*
	* @return the primary key of this ifp contract details
	*/
	@Override
	public int getPrimaryKey() {
		return _ifpContractDetails.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _ifpContractDetails.getPrimaryKeyObj();
	}

	/**
	* Returns the record lock status of this ifp contract details.
	*
	* @return the record lock status of this ifp contract details
	*/
	@Override
	public boolean getRecordLockStatus() {
		return _ifpContractDetails.getRecordLockStatus();
	}

	/**
	* Returns the source of this ifp contract details.
	*
	* @return the source of this ifp contract details
	*/
	@Override
	public java.lang.String getSource() {
		return _ifpContractDetails.getSource();
	}

	/**
	* Returns the total dollar commitment of this ifp contract details.
	*
	* @return the total dollar commitment of this ifp contract details
	*/
	@Override
	public java.lang.String getTotalDollarCommitment() {
		return _ifpContractDetails.getTotalDollarCommitment();
	}

	/**
	* Returns the total marketshare commitment of this ifp contract details.
	*
	* @return the total marketshare commitment of this ifp contract details
	*/
	@Override
	public java.lang.String getTotalMarketshareCommitment() {
		return _ifpContractDetails.getTotalMarketshareCommitment();
	}

	/**
	* Returns the total volume commitment of this ifp contract details.
	*
	* @return the total volume commitment of this ifp contract details
	*/
	@Override
	public java.lang.String getTotalVolumeCommitment() {
		return _ifpContractDetails.getTotalVolumeCommitment();
	}

	@Override
	public int hashCode() {
		return _ifpContractDetails.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _ifpContractDetails.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _ifpContractDetails.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _ifpContractDetails.isNew();
	}

	/**
	* Returns <code>true</code> if this ifp contract details is record lock status.
	*
	* @return <code>true</code> if this ifp contract details is record lock status; <code>false</code> otherwise
	*/
	@Override
	public boolean isRecordLockStatus() {
		return _ifpContractDetails.isRecordLockStatus();
	}

	@Override
	public void persist() {
		_ifpContractDetails.persist();
	}

	/**
	* Sets the batch ID of this ifp contract details.
	*
	* @param batchId the batch ID of this ifp contract details
	*/
	@Override
	public void setBatchId(java.lang.String batchId) {
		_ifpContractDetails.setBatchId(batchId);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_ifpContractDetails.setCachedModel(cachedModel);
	}

	/**
	* Sets the commitment period of this ifp contract details.
	*
	* @param commitmentPeriod the commitment period of this ifp contract details
	*/
	@Override
	public void setCommitmentPeriod(java.lang.String commitmentPeriod) {
		_ifpContractDetails.setCommitmentPeriod(commitmentPeriod);
	}

	/**
	* Sets the created by of this ifp contract details.
	*
	* @param createdBy the created by of this ifp contract details
	*/
	@Override
	public void setCreatedBy(int createdBy) {
		_ifpContractDetails.setCreatedBy(createdBy);
	}

	/**
	* Sets the created date of this ifp contract details.
	*
	* @param createdDate the created date of this ifp contract details
	*/
	@Override
	public void setCreatedDate(Date createdDate) {
		_ifpContractDetails.setCreatedDate(createdDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_ifpContractDetails.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_ifpContractDetails.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_ifpContractDetails.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the ifp contract attached date of this ifp contract details.
	*
	* @param ifpContractAttachedDate the ifp contract attached date of this ifp contract details
	*/
	@Override
	public void setIfpContractAttachedDate(Date ifpContractAttachedDate) {
		_ifpContractDetails.setIfpContractAttachedDate(ifpContractAttachedDate);
	}

	/**
	* Sets the ifp contract attached status of this ifp contract details.
	*
	* @param ifpContractAttachedStatus the ifp contract attached status of this ifp contract details
	*/
	@Override
	public void setIfpContractAttachedStatus(int ifpContractAttachedStatus) {
		_ifpContractDetails.setIfpContractAttachedStatus(ifpContractAttachedStatus);
	}

	/**
	* Sets the ifp contract details sid of this ifp contract details.
	*
	* @param ifpContractDetailsSid the ifp contract details sid of this ifp contract details
	*/
	@Override
	public void setIfpContractDetailsSid(int ifpContractDetailsSid) {
		_ifpContractDetails.setIfpContractDetailsSid(ifpContractDetailsSid);
	}

	/**
	* Sets the ifp contract sid of this ifp contract details.
	*
	* @param ifpContractSid the ifp contract sid of this ifp contract details
	*/
	@Override
	public void setIfpContractSid(int ifpContractSid) {
		_ifpContractDetails.setIfpContractSid(ifpContractSid);
	}

	/**
	* Sets the inbound status of this ifp contract details.
	*
	* @param inboundStatus the inbound status of this ifp contract details
	*/
	@Override
	public void setInboundStatus(java.lang.String inboundStatus) {
		_ifpContractDetails.setInboundStatus(inboundStatus);
	}

	/**
	* Sets the item end date of this ifp contract details.
	*
	* @param itemEndDate the item end date of this ifp contract details
	*/
	@Override
	public void setItemEndDate(Date itemEndDate) {
		_ifpContractDetails.setItemEndDate(itemEndDate);
	}

	/**
	* Sets the item master sid of this ifp contract details.
	*
	* @param itemMasterSid the item master sid of this ifp contract details
	*/
	@Override
	public void setItemMasterSid(int itemMasterSid) {
		_ifpContractDetails.setItemMasterSid(itemMasterSid);
	}

	/**
	* Sets the item start date of this ifp contract details.
	*
	* @param itemStartDate the item start date of this ifp contract details
	*/
	@Override
	public void setItemStartDate(Date itemStartDate) {
		_ifpContractDetails.setItemStartDate(itemStartDate);
	}

	/**
	* Sets the item status of this ifp contract details.
	*
	* @param itemStatus the item status of this ifp contract details
	*/
	@Override
	public void setItemStatus(int itemStatus) {
		_ifpContractDetails.setItemStatus(itemStatus);
	}

	/**
	* Sets the modified by of this ifp contract details.
	*
	* @param modifiedBy the modified by of this ifp contract details
	*/
	@Override
	public void setModifiedBy(int modifiedBy) {
		_ifpContractDetails.setModifiedBy(modifiedBy);
	}

	/**
	* Sets the modified date of this ifp contract details.
	*
	* @param modifiedDate the modified date of this ifp contract details
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_ifpContractDetails.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_ifpContractDetails.setNew(n);
	}

	/**
	* Sets the primary key of this ifp contract details.
	*
	* @param primaryKey the primary key of this ifp contract details
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_ifpContractDetails.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_ifpContractDetails.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets whether this ifp contract details is record lock status.
	*
	* @param recordLockStatus the record lock status of this ifp contract details
	*/
	@Override
	public void setRecordLockStatus(boolean recordLockStatus) {
		_ifpContractDetails.setRecordLockStatus(recordLockStatus);
	}

	/**
	* Sets the source of this ifp contract details.
	*
	* @param source the source of this ifp contract details
	*/
	@Override
	public void setSource(java.lang.String source) {
		_ifpContractDetails.setSource(source);
	}

	/**
	* Sets the total dollar commitment of this ifp contract details.
	*
	* @param totalDollarCommitment the total dollar commitment of this ifp contract details
	*/
	@Override
	public void setTotalDollarCommitment(java.lang.String totalDollarCommitment) {
		_ifpContractDetails.setTotalDollarCommitment(totalDollarCommitment);
	}

	/**
	* Sets the total marketshare commitment of this ifp contract details.
	*
	* @param totalMarketshareCommitment the total marketshare commitment of this ifp contract details
	*/
	@Override
	public void setTotalMarketshareCommitment(
		java.lang.String totalMarketshareCommitment) {
		_ifpContractDetails.setTotalMarketshareCommitment(totalMarketshareCommitment);
	}

	/**
	* Sets the total volume commitment of this ifp contract details.
	*
	* @param totalVolumeCommitment the total volume commitment of this ifp contract details
	*/
	@Override
	public void setTotalVolumeCommitment(java.lang.String totalVolumeCommitment) {
		_ifpContractDetails.setTotalVolumeCommitment(totalVolumeCommitment);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<IfpContractDetails> toCacheModel() {
		return _ifpContractDetails.toCacheModel();
	}

	@Override
	public IfpContractDetails toEscapedModel() {
		return new IfpContractDetailsWrapper(_ifpContractDetails.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _ifpContractDetails.toString();
	}

	@Override
	public IfpContractDetails toUnescapedModel() {
		return new IfpContractDetailsWrapper(_ifpContractDetails.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _ifpContractDetails.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof IfpContractDetailsWrapper)) {
			return false;
		}

		IfpContractDetailsWrapper ifpContractDetailsWrapper = (IfpContractDetailsWrapper)obj;

		if (Objects.equals(_ifpContractDetails,
					ifpContractDetailsWrapper._ifpContractDetails)) {
			return true;
		}

		return false;
	}

	@Override
	public IfpContractDetails getWrappedModel() {
		return _ifpContractDetails;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _ifpContractDetails.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _ifpContractDetails.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_ifpContractDetails.resetOriginalValues();
	}

	private final IfpContractDetails _ifpContractDetails;
}