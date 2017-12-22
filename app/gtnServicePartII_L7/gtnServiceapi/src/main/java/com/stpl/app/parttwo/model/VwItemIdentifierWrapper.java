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
 * This class is a wrapper for {@link VwItemIdentifier}.
 * </p>
 *
 * @author
 * @see VwItemIdentifier
 * @generated
 */
@ProviderType
public class VwItemIdentifierWrapper implements VwItemIdentifier,
	ModelWrapper<VwItemIdentifier> {
	public VwItemIdentifierWrapper(VwItemIdentifier vwItemIdentifier) {
		_vwItemIdentifier = vwItemIdentifier;
	}

	@Override
	public Class<?> getModelClass() {
		return VwItemIdentifier.class;
	}

	@Override
	public String getModelClassName() {
		return VwItemIdentifier.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("itemStatus", getItemStatus());
		attributes.put("itemIdentifierSid", getItemIdentifierSid());
		attributes.put("endDate", getEndDate());
		attributes.put("itemId", getItemId());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("entityCode", getEntityCode());
		attributes.put("startDate", getStartDate());
		attributes.put("createdDate", getCreatedDate());
		attributes.put("createdBy", getCreatedBy());
		attributes.put("source", getSource());
		attributes.put("batchId", getBatchId());
		attributes.put("addChgDelIndicator", getAddChgDelIndicator());
		attributes.put("itemName", getItemName());
		attributes.put("itemIdentifier", getItemIdentifier());
		attributes.put("identifierCodeQualifierName",
			getIdentifierCodeQualifierName());
		attributes.put("modifiedBy", getModifiedBy());
		attributes.put("itemNo", getItemNo());
		attributes.put("identifierCodeQualifier", getIdentifierCodeQualifier());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String itemStatus = (String)attributes.get("itemStatus");

		if (itemStatus != null) {
			setItemStatus(itemStatus);
		}

		Integer itemIdentifierSid = (Integer)attributes.get("itemIdentifierSid");

		if (itemIdentifierSid != null) {
			setItemIdentifierSid(itemIdentifierSid);
		}

		Date endDate = (Date)attributes.get("endDate");

		if (endDate != null) {
			setEndDate(endDate);
		}

		String itemId = (String)attributes.get("itemId");

		if (itemId != null) {
			setItemId(itemId);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String entityCode = (String)attributes.get("entityCode");

		if (entityCode != null) {
			setEntityCode(entityCode);
		}

		Date startDate = (Date)attributes.get("startDate");

		if (startDate != null) {
			setStartDate(startDate);
		}

		Date createdDate = (Date)attributes.get("createdDate");

		if (createdDate != null) {
			setCreatedDate(createdDate);
		}

		String createdBy = (String)attributes.get("createdBy");

		if (createdBy != null) {
			setCreatedBy(createdBy);
		}

		String source = (String)attributes.get("source");

		if (source != null) {
			setSource(source);
		}

		String batchId = (String)attributes.get("batchId");

		if (batchId != null) {
			setBatchId(batchId);
		}

		String addChgDelIndicator = (String)attributes.get("addChgDelIndicator");

		if (addChgDelIndicator != null) {
			setAddChgDelIndicator(addChgDelIndicator);
		}

		String itemName = (String)attributes.get("itemName");

		if (itemName != null) {
			setItemName(itemName);
		}

		String itemIdentifier = (String)attributes.get("itemIdentifier");

		if (itemIdentifier != null) {
			setItemIdentifier(itemIdentifier);
		}

		String identifierCodeQualifierName = (String)attributes.get(
				"identifierCodeQualifierName");

		if (identifierCodeQualifierName != null) {
			setIdentifierCodeQualifierName(identifierCodeQualifierName);
		}

		String modifiedBy = (String)attributes.get("modifiedBy");

		if (modifiedBy != null) {
			setModifiedBy(modifiedBy);
		}

		String itemNo = (String)attributes.get("itemNo");

		if (itemNo != null) {
			setItemNo(itemNo);
		}

		String identifierCodeQualifier = (String)attributes.get(
				"identifierCodeQualifier");

		if (identifierCodeQualifier != null) {
			setIdentifierCodeQualifier(identifierCodeQualifier);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new VwItemIdentifierWrapper((VwItemIdentifier)_vwItemIdentifier.clone());
	}

	@Override
	public int compareTo(VwItemIdentifier vwItemIdentifier) {
		return _vwItemIdentifier.compareTo(vwItemIdentifier);
	}

	/**
	* Returns the add chg del indicator of this vw item identifier.
	*
	* @return the add chg del indicator of this vw item identifier
	*/
	@Override
	public java.lang.String getAddChgDelIndicator() {
		return _vwItemIdentifier.getAddChgDelIndicator();
	}

	/**
	* Returns the batch ID of this vw item identifier.
	*
	* @return the batch ID of this vw item identifier
	*/
	@Override
	public java.lang.String getBatchId() {
		return _vwItemIdentifier.getBatchId();
	}

	/**
	* Returns the created by of this vw item identifier.
	*
	* @return the created by of this vw item identifier
	*/
	@Override
	public java.lang.String getCreatedBy() {
		return _vwItemIdentifier.getCreatedBy();
	}

	/**
	* Returns the created date of this vw item identifier.
	*
	* @return the created date of this vw item identifier
	*/
	@Override
	public Date getCreatedDate() {
		return _vwItemIdentifier.getCreatedDate();
	}

	/**
	* Returns the end date of this vw item identifier.
	*
	* @return the end date of this vw item identifier
	*/
	@Override
	public Date getEndDate() {
		return _vwItemIdentifier.getEndDate();
	}

	/**
	* Returns the entity code of this vw item identifier.
	*
	* @return the entity code of this vw item identifier
	*/
	@Override
	public java.lang.String getEntityCode() {
		return _vwItemIdentifier.getEntityCode();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _vwItemIdentifier.getExpandoBridge();
	}

	/**
	* Returns the identifier code qualifier of this vw item identifier.
	*
	* @return the identifier code qualifier of this vw item identifier
	*/
	@Override
	public java.lang.String getIdentifierCodeQualifier() {
		return _vwItemIdentifier.getIdentifierCodeQualifier();
	}

	/**
	* Returns the identifier code qualifier name of this vw item identifier.
	*
	* @return the identifier code qualifier name of this vw item identifier
	*/
	@Override
	public java.lang.String getIdentifierCodeQualifierName() {
		return _vwItemIdentifier.getIdentifierCodeQualifierName();
	}

	/**
	* Returns the item ID of this vw item identifier.
	*
	* @return the item ID of this vw item identifier
	*/
	@Override
	public java.lang.String getItemId() {
		return _vwItemIdentifier.getItemId();
	}

	/**
	* Returns the item identifier of this vw item identifier.
	*
	* @return the item identifier of this vw item identifier
	*/
	@Override
	public java.lang.String getItemIdentifier() {
		return _vwItemIdentifier.getItemIdentifier();
	}

	/**
	* Returns the item identifier sid of this vw item identifier.
	*
	* @return the item identifier sid of this vw item identifier
	*/
	@Override
	public int getItemIdentifierSid() {
		return _vwItemIdentifier.getItemIdentifierSid();
	}

	/**
	* Returns the item name of this vw item identifier.
	*
	* @return the item name of this vw item identifier
	*/
	@Override
	public java.lang.String getItemName() {
		return _vwItemIdentifier.getItemName();
	}

	/**
	* Returns the item no of this vw item identifier.
	*
	* @return the item no of this vw item identifier
	*/
	@Override
	public java.lang.String getItemNo() {
		return _vwItemIdentifier.getItemNo();
	}

	/**
	* Returns the item status of this vw item identifier.
	*
	* @return the item status of this vw item identifier
	*/
	@Override
	public java.lang.String getItemStatus() {
		return _vwItemIdentifier.getItemStatus();
	}

	/**
	* Returns the modified by of this vw item identifier.
	*
	* @return the modified by of this vw item identifier
	*/
	@Override
	public java.lang.String getModifiedBy() {
		return _vwItemIdentifier.getModifiedBy();
	}

	/**
	* Returns the modified date of this vw item identifier.
	*
	* @return the modified date of this vw item identifier
	*/
	@Override
	public Date getModifiedDate() {
		return _vwItemIdentifier.getModifiedDate();
	}

	/**
	* Returns the primary key of this vw item identifier.
	*
	* @return the primary key of this vw item identifier
	*/
	@Override
	public int getPrimaryKey() {
		return _vwItemIdentifier.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _vwItemIdentifier.getPrimaryKeyObj();
	}

	/**
	* Returns the source of this vw item identifier.
	*
	* @return the source of this vw item identifier
	*/
	@Override
	public java.lang.String getSource() {
		return _vwItemIdentifier.getSource();
	}

	/**
	* Returns the start date of this vw item identifier.
	*
	* @return the start date of this vw item identifier
	*/
	@Override
	public Date getStartDate() {
		return _vwItemIdentifier.getStartDate();
	}

	@Override
	public int hashCode() {
		return _vwItemIdentifier.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _vwItemIdentifier.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _vwItemIdentifier.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _vwItemIdentifier.isNew();
	}

	@Override
	public void persist() {
		_vwItemIdentifier.persist();
	}

	/**
	* Sets the add chg del indicator of this vw item identifier.
	*
	* @param addChgDelIndicator the add chg del indicator of this vw item identifier
	*/
	@Override
	public void setAddChgDelIndicator(java.lang.String addChgDelIndicator) {
		_vwItemIdentifier.setAddChgDelIndicator(addChgDelIndicator);
	}

	/**
	* Sets the batch ID of this vw item identifier.
	*
	* @param batchId the batch ID of this vw item identifier
	*/
	@Override
	public void setBatchId(java.lang.String batchId) {
		_vwItemIdentifier.setBatchId(batchId);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_vwItemIdentifier.setCachedModel(cachedModel);
	}

	/**
	* Sets the created by of this vw item identifier.
	*
	* @param createdBy the created by of this vw item identifier
	*/
	@Override
	public void setCreatedBy(java.lang.String createdBy) {
		_vwItemIdentifier.setCreatedBy(createdBy);
	}

	/**
	* Sets the created date of this vw item identifier.
	*
	* @param createdDate the created date of this vw item identifier
	*/
	@Override
	public void setCreatedDate(Date createdDate) {
		_vwItemIdentifier.setCreatedDate(createdDate);
	}

	/**
	* Sets the end date of this vw item identifier.
	*
	* @param endDate the end date of this vw item identifier
	*/
	@Override
	public void setEndDate(Date endDate) {
		_vwItemIdentifier.setEndDate(endDate);
	}

	/**
	* Sets the entity code of this vw item identifier.
	*
	* @param entityCode the entity code of this vw item identifier
	*/
	@Override
	public void setEntityCode(java.lang.String entityCode) {
		_vwItemIdentifier.setEntityCode(entityCode);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_vwItemIdentifier.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_vwItemIdentifier.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_vwItemIdentifier.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the identifier code qualifier of this vw item identifier.
	*
	* @param identifierCodeQualifier the identifier code qualifier of this vw item identifier
	*/
	@Override
	public void setIdentifierCodeQualifier(
		java.lang.String identifierCodeQualifier) {
		_vwItemIdentifier.setIdentifierCodeQualifier(identifierCodeQualifier);
	}

	/**
	* Sets the identifier code qualifier name of this vw item identifier.
	*
	* @param identifierCodeQualifierName the identifier code qualifier name of this vw item identifier
	*/
	@Override
	public void setIdentifierCodeQualifierName(
		java.lang.String identifierCodeQualifierName) {
		_vwItemIdentifier.setIdentifierCodeQualifierName(identifierCodeQualifierName);
	}

	/**
	* Sets the item ID of this vw item identifier.
	*
	* @param itemId the item ID of this vw item identifier
	*/
	@Override
	public void setItemId(java.lang.String itemId) {
		_vwItemIdentifier.setItemId(itemId);
	}

	/**
	* Sets the item identifier of this vw item identifier.
	*
	* @param itemIdentifier the item identifier of this vw item identifier
	*/
	@Override
	public void setItemIdentifier(java.lang.String itemIdentifier) {
		_vwItemIdentifier.setItemIdentifier(itemIdentifier);
	}

	/**
	* Sets the item identifier sid of this vw item identifier.
	*
	* @param itemIdentifierSid the item identifier sid of this vw item identifier
	*/
	@Override
	public void setItemIdentifierSid(int itemIdentifierSid) {
		_vwItemIdentifier.setItemIdentifierSid(itemIdentifierSid);
	}

	/**
	* Sets the item name of this vw item identifier.
	*
	* @param itemName the item name of this vw item identifier
	*/
	@Override
	public void setItemName(java.lang.String itemName) {
		_vwItemIdentifier.setItemName(itemName);
	}

	/**
	* Sets the item no of this vw item identifier.
	*
	* @param itemNo the item no of this vw item identifier
	*/
	@Override
	public void setItemNo(java.lang.String itemNo) {
		_vwItemIdentifier.setItemNo(itemNo);
	}

	/**
	* Sets the item status of this vw item identifier.
	*
	* @param itemStatus the item status of this vw item identifier
	*/
	@Override
	public void setItemStatus(java.lang.String itemStatus) {
		_vwItemIdentifier.setItemStatus(itemStatus);
	}

	/**
	* Sets the modified by of this vw item identifier.
	*
	* @param modifiedBy the modified by of this vw item identifier
	*/
	@Override
	public void setModifiedBy(java.lang.String modifiedBy) {
		_vwItemIdentifier.setModifiedBy(modifiedBy);
	}

	/**
	* Sets the modified date of this vw item identifier.
	*
	* @param modifiedDate the modified date of this vw item identifier
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_vwItemIdentifier.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_vwItemIdentifier.setNew(n);
	}

	/**
	* Sets the primary key of this vw item identifier.
	*
	* @param primaryKey the primary key of this vw item identifier
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_vwItemIdentifier.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_vwItemIdentifier.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the source of this vw item identifier.
	*
	* @param source the source of this vw item identifier
	*/
	@Override
	public void setSource(java.lang.String source) {
		_vwItemIdentifier.setSource(source);
	}

	/**
	* Sets the start date of this vw item identifier.
	*
	* @param startDate the start date of this vw item identifier
	*/
	@Override
	public void setStartDate(Date startDate) {
		_vwItemIdentifier.setStartDate(startDate);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<VwItemIdentifier> toCacheModel() {
		return _vwItemIdentifier.toCacheModel();
	}

	@Override
	public VwItemIdentifier toEscapedModel() {
		return new VwItemIdentifierWrapper(_vwItemIdentifier.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _vwItemIdentifier.toString();
	}

	@Override
	public VwItemIdentifier toUnescapedModel() {
		return new VwItemIdentifierWrapper(_vwItemIdentifier.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _vwItemIdentifier.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof VwItemIdentifierWrapper)) {
			return false;
		}

		VwItemIdentifierWrapper vwItemIdentifierWrapper = (VwItemIdentifierWrapper)obj;

		if (Objects.equals(_vwItemIdentifier,
					vwItemIdentifierWrapper._vwItemIdentifier)) {
			return true;
		}

		return false;
	}

	@Override
	public VwItemIdentifier getWrappedModel() {
		return _vwItemIdentifier;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _vwItemIdentifier.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _vwItemIdentifier.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_vwItemIdentifier.resetOriginalValues();
	}

	private final VwItemIdentifier _vwItemIdentifier;
}