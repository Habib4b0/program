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
 * This class is a wrapper for {@link IvldItemIdentifier}.
 * </p>
 *
 * @author
 * @see IvldItemIdentifier
 * @generated
 */
@ProviderType
public class IvldItemIdentifierWrapper implements IvldItemIdentifier,
	ModelWrapper<IvldItemIdentifier> {
	public IvldItemIdentifierWrapper(IvldItemIdentifier ivldItemIdentifier) {
		_ivldItemIdentifier = ivldItemIdentifier;
	}

	@Override
	public Class<?> getModelClass() {
		return IvldItemIdentifier.class;
	}

	@Override
	public String getModelClassName() {
		return IvldItemIdentifier.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("createdBy", getCreatedBy());
		attributes.put("identifierCodeQualifierName",
			getIdentifierCodeQualifierName());
		attributes.put("ivldItemIdentifierSid", getIvldItemIdentifierSid());
		attributes.put("itemNo", getItemNo());
		attributes.put("modifiedBy", getModifiedBy());
		attributes.put("createdDate", getCreatedDate());
		attributes.put("identifierCodeQualifier", getIdentifierCodeQualifier());
		attributes.put("itemId", getItemId());
		attributes.put("endDate", getEndDate());
		attributes.put("errorField", getErrorField());
		attributes.put("startDate", getStartDate());
		attributes.put("batchId", getBatchId());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("itemName", getItemName());
		attributes.put("errorCode", getErrorCode());
		attributes.put("reprocessedFlag", getReprocessedFlag());
		attributes.put("itemIdentifier", getItemIdentifier());
		attributes.put("itemStatus", getItemStatus());
		attributes.put("reasonForFailure", getReasonForFailure());
		attributes.put("source", getSource());
		attributes.put("addChgDelIndicator", getAddChgDelIndicator());
		attributes.put("entityCode", getEntityCode());
		attributes.put("itemIdentifierIntfid", getItemIdentifierIntfid());
		attributes.put("intfInsertedDate", getIntfInsertedDate());
		attributes.put("checkRecord", getCheckRecord());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String createdBy = (String)attributes.get("createdBy");

		if (createdBy != null) {
			setCreatedBy(createdBy);
		}

		String identifierCodeQualifierName = (String)attributes.get(
				"identifierCodeQualifierName");

		if (identifierCodeQualifierName != null) {
			setIdentifierCodeQualifierName(identifierCodeQualifierName);
		}

		Integer ivldItemIdentifierSid = (Integer)attributes.get(
				"ivldItemIdentifierSid");

		if (ivldItemIdentifierSid != null) {
			setIvldItemIdentifierSid(ivldItemIdentifierSid);
		}

		String itemNo = (String)attributes.get("itemNo");

		if (itemNo != null) {
			setItemNo(itemNo);
		}

		String modifiedBy = (String)attributes.get("modifiedBy");

		if (modifiedBy != null) {
			setModifiedBy(modifiedBy);
		}

		Date createdDate = (Date)attributes.get("createdDate");

		if (createdDate != null) {
			setCreatedDate(createdDate);
		}

		String identifierCodeQualifier = (String)attributes.get(
				"identifierCodeQualifier");

		if (identifierCodeQualifier != null) {
			setIdentifierCodeQualifier(identifierCodeQualifier);
		}

		String itemId = (String)attributes.get("itemId");

		if (itemId != null) {
			setItemId(itemId);
		}

		String endDate = (String)attributes.get("endDate");

		if (endDate != null) {
			setEndDate(endDate);
		}

		String errorField = (String)attributes.get("errorField");

		if (errorField != null) {
			setErrorField(errorField);
		}

		String startDate = (String)attributes.get("startDate");

		if (startDate != null) {
			setStartDate(startDate);
		}

		String batchId = (String)attributes.get("batchId");

		if (batchId != null) {
			setBatchId(batchId);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String itemName = (String)attributes.get("itemName");

		if (itemName != null) {
			setItemName(itemName);
		}

		String errorCode = (String)attributes.get("errorCode");

		if (errorCode != null) {
			setErrorCode(errorCode);
		}

		String reprocessedFlag = (String)attributes.get("reprocessedFlag");

		if (reprocessedFlag != null) {
			setReprocessedFlag(reprocessedFlag);
		}

		String itemIdentifier = (String)attributes.get("itemIdentifier");

		if (itemIdentifier != null) {
			setItemIdentifier(itemIdentifier);
		}

		String itemStatus = (String)attributes.get("itemStatus");

		if (itemStatus != null) {
			setItemStatus(itemStatus);
		}

		String reasonForFailure = (String)attributes.get("reasonForFailure");

		if (reasonForFailure != null) {
			setReasonForFailure(reasonForFailure);
		}

		String source = (String)attributes.get("source");

		if (source != null) {
			setSource(source);
		}

		String addChgDelIndicator = (String)attributes.get("addChgDelIndicator");

		if (addChgDelIndicator != null) {
			setAddChgDelIndicator(addChgDelIndicator);
		}

		String entityCode = (String)attributes.get("entityCode");

		if (entityCode != null) {
			setEntityCode(entityCode);
		}

		String itemIdentifierIntfid = (String)attributes.get(
				"itemIdentifierIntfid");

		if (itemIdentifierIntfid != null) {
			setItemIdentifierIntfid(itemIdentifierIntfid);
		}

		Date intfInsertedDate = (Date)attributes.get("intfInsertedDate");

		if (intfInsertedDate != null) {
			setIntfInsertedDate(intfInsertedDate);
		}

		Boolean checkRecord = (Boolean)attributes.get("checkRecord");

		if (checkRecord != null) {
			setCheckRecord(checkRecord);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new IvldItemIdentifierWrapper((IvldItemIdentifier)_ivldItemIdentifier.clone());
	}

	@Override
	public int compareTo(IvldItemIdentifier ivldItemIdentifier) {
		return _ivldItemIdentifier.compareTo(ivldItemIdentifier);
	}

	/**
	* Returns the add chg del indicator of this ivld item identifier.
	*
	* @return the add chg del indicator of this ivld item identifier
	*/
	@Override
	public java.lang.String getAddChgDelIndicator() {
		return _ivldItemIdentifier.getAddChgDelIndicator();
	}

	/**
	* Returns the batch ID of this ivld item identifier.
	*
	* @return the batch ID of this ivld item identifier
	*/
	@Override
	public java.lang.String getBatchId() {
		return _ivldItemIdentifier.getBatchId();
	}

	/**
	* Returns the check record of this ivld item identifier.
	*
	* @return the check record of this ivld item identifier
	*/
	@Override
	public boolean getCheckRecord() {
		return _ivldItemIdentifier.getCheckRecord();
	}

	/**
	* Returns the created by of this ivld item identifier.
	*
	* @return the created by of this ivld item identifier
	*/
	@Override
	public java.lang.String getCreatedBy() {
		return _ivldItemIdentifier.getCreatedBy();
	}

	/**
	* Returns the created date of this ivld item identifier.
	*
	* @return the created date of this ivld item identifier
	*/
	@Override
	public Date getCreatedDate() {
		return _ivldItemIdentifier.getCreatedDate();
	}

	/**
	* Returns the end date of this ivld item identifier.
	*
	* @return the end date of this ivld item identifier
	*/
	@Override
	public java.lang.String getEndDate() {
		return _ivldItemIdentifier.getEndDate();
	}

	/**
	* Returns the entity code of this ivld item identifier.
	*
	* @return the entity code of this ivld item identifier
	*/
	@Override
	public java.lang.String getEntityCode() {
		return _ivldItemIdentifier.getEntityCode();
	}

	/**
	* Returns the error code of this ivld item identifier.
	*
	* @return the error code of this ivld item identifier
	*/
	@Override
	public java.lang.String getErrorCode() {
		return _ivldItemIdentifier.getErrorCode();
	}

	/**
	* Returns the error field of this ivld item identifier.
	*
	* @return the error field of this ivld item identifier
	*/
	@Override
	public java.lang.String getErrorField() {
		return _ivldItemIdentifier.getErrorField();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _ivldItemIdentifier.getExpandoBridge();
	}

	/**
	* Returns the identifier code qualifier of this ivld item identifier.
	*
	* @return the identifier code qualifier of this ivld item identifier
	*/
	@Override
	public java.lang.String getIdentifierCodeQualifier() {
		return _ivldItemIdentifier.getIdentifierCodeQualifier();
	}

	/**
	* Returns the identifier code qualifier name of this ivld item identifier.
	*
	* @return the identifier code qualifier name of this ivld item identifier
	*/
	@Override
	public java.lang.String getIdentifierCodeQualifierName() {
		return _ivldItemIdentifier.getIdentifierCodeQualifierName();
	}

	/**
	* Returns the intf inserted date of this ivld item identifier.
	*
	* @return the intf inserted date of this ivld item identifier
	*/
	@Override
	public Date getIntfInsertedDate() {
		return _ivldItemIdentifier.getIntfInsertedDate();
	}

	/**
	* Returns the item ID of this ivld item identifier.
	*
	* @return the item ID of this ivld item identifier
	*/
	@Override
	public java.lang.String getItemId() {
		return _ivldItemIdentifier.getItemId();
	}

	/**
	* Returns the item identifier of this ivld item identifier.
	*
	* @return the item identifier of this ivld item identifier
	*/
	@Override
	public java.lang.String getItemIdentifier() {
		return _ivldItemIdentifier.getItemIdentifier();
	}

	/**
	* Returns the item identifier intfid of this ivld item identifier.
	*
	* @return the item identifier intfid of this ivld item identifier
	*/
	@Override
	public java.lang.String getItemIdentifierIntfid() {
		return _ivldItemIdentifier.getItemIdentifierIntfid();
	}

	/**
	* Returns the item name of this ivld item identifier.
	*
	* @return the item name of this ivld item identifier
	*/
	@Override
	public java.lang.String getItemName() {
		return _ivldItemIdentifier.getItemName();
	}

	/**
	* Returns the item no of this ivld item identifier.
	*
	* @return the item no of this ivld item identifier
	*/
	@Override
	public java.lang.String getItemNo() {
		return _ivldItemIdentifier.getItemNo();
	}

	/**
	* Returns the item status of this ivld item identifier.
	*
	* @return the item status of this ivld item identifier
	*/
	@Override
	public java.lang.String getItemStatus() {
		return _ivldItemIdentifier.getItemStatus();
	}

	/**
	* Returns the ivld item identifier sid of this ivld item identifier.
	*
	* @return the ivld item identifier sid of this ivld item identifier
	*/
	@Override
	public int getIvldItemIdentifierSid() {
		return _ivldItemIdentifier.getIvldItemIdentifierSid();
	}

	/**
	* Returns the modified by of this ivld item identifier.
	*
	* @return the modified by of this ivld item identifier
	*/
	@Override
	public java.lang.String getModifiedBy() {
		return _ivldItemIdentifier.getModifiedBy();
	}

	/**
	* Returns the modified date of this ivld item identifier.
	*
	* @return the modified date of this ivld item identifier
	*/
	@Override
	public Date getModifiedDate() {
		return _ivldItemIdentifier.getModifiedDate();
	}

	/**
	* Returns the primary key of this ivld item identifier.
	*
	* @return the primary key of this ivld item identifier
	*/
	@Override
	public int getPrimaryKey() {
		return _ivldItemIdentifier.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _ivldItemIdentifier.getPrimaryKeyObj();
	}

	/**
	* Returns the reason for failure of this ivld item identifier.
	*
	* @return the reason for failure of this ivld item identifier
	*/
	@Override
	public java.lang.String getReasonForFailure() {
		return _ivldItemIdentifier.getReasonForFailure();
	}

	/**
	* Returns the reprocessed flag of this ivld item identifier.
	*
	* @return the reprocessed flag of this ivld item identifier
	*/
	@Override
	public java.lang.String getReprocessedFlag() {
		return _ivldItemIdentifier.getReprocessedFlag();
	}

	/**
	* Returns the source of this ivld item identifier.
	*
	* @return the source of this ivld item identifier
	*/
	@Override
	public java.lang.String getSource() {
		return _ivldItemIdentifier.getSource();
	}

	/**
	* Returns the start date of this ivld item identifier.
	*
	* @return the start date of this ivld item identifier
	*/
	@Override
	public java.lang.String getStartDate() {
		return _ivldItemIdentifier.getStartDate();
	}

	@Override
	public int hashCode() {
		return _ivldItemIdentifier.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _ivldItemIdentifier.isCachedModel();
	}

	/**
	* Returns <code>true</code> if this ivld item identifier is check record.
	*
	* @return <code>true</code> if this ivld item identifier is check record; <code>false</code> otherwise
	*/
	@Override
	public boolean isCheckRecord() {
		return _ivldItemIdentifier.isCheckRecord();
	}

	@Override
	public boolean isEscapedModel() {
		return _ivldItemIdentifier.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _ivldItemIdentifier.isNew();
	}

	@Override
	public void persist() {
		_ivldItemIdentifier.persist();
	}

	/**
	* Sets the add chg del indicator of this ivld item identifier.
	*
	* @param addChgDelIndicator the add chg del indicator of this ivld item identifier
	*/
	@Override
	public void setAddChgDelIndicator(java.lang.String addChgDelIndicator) {
		_ivldItemIdentifier.setAddChgDelIndicator(addChgDelIndicator);
	}

	/**
	* Sets the batch ID of this ivld item identifier.
	*
	* @param batchId the batch ID of this ivld item identifier
	*/
	@Override
	public void setBatchId(java.lang.String batchId) {
		_ivldItemIdentifier.setBatchId(batchId);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_ivldItemIdentifier.setCachedModel(cachedModel);
	}

	/**
	* Sets whether this ivld item identifier is check record.
	*
	* @param checkRecord the check record of this ivld item identifier
	*/
	@Override
	public void setCheckRecord(boolean checkRecord) {
		_ivldItemIdentifier.setCheckRecord(checkRecord);
	}

	/**
	* Sets the created by of this ivld item identifier.
	*
	* @param createdBy the created by of this ivld item identifier
	*/
	@Override
	public void setCreatedBy(java.lang.String createdBy) {
		_ivldItemIdentifier.setCreatedBy(createdBy);
	}

	/**
	* Sets the created date of this ivld item identifier.
	*
	* @param createdDate the created date of this ivld item identifier
	*/
	@Override
	public void setCreatedDate(Date createdDate) {
		_ivldItemIdentifier.setCreatedDate(createdDate);
	}

	/**
	* Sets the end date of this ivld item identifier.
	*
	* @param endDate the end date of this ivld item identifier
	*/
	@Override
	public void setEndDate(java.lang.String endDate) {
		_ivldItemIdentifier.setEndDate(endDate);
	}

	/**
	* Sets the entity code of this ivld item identifier.
	*
	* @param entityCode the entity code of this ivld item identifier
	*/
	@Override
	public void setEntityCode(java.lang.String entityCode) {
		_ivldItemIdentifier.setEntityCode(entityCode);
	}

	/**
	* Sets the error code of this ivld item identifier.
	*
	* @param errorCode the error code of this ivld item identifier
	*/
	@Override
	public void setErrorCode(java.lang.String errorCode) {
		_ivldItemIdentifier.setErrorCode(errorCode);
	}

	/**
	* Sets the error field of this ivld item identifier.
	*
	* @param errorField the error field of this ivld item identifier
	*/
	@Override
	public void setErrorField(java.lang.String errorField) {
		_ivldItemIdentifier.setErrorField(errorField);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_ivldItemIdentifier.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_ivldItemIdentifier.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_ivldItemIdentifier.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the identifier code qualifier of this ivld item identifier.
	*
	* @param identifierCodeQualifier the identifier code qualifier of this ivld item identifier
	*/
	@Override
	public void setIdentifierCodeQualifier(
		java.lang.String identifierCodeQualifier) {
		_ivldItemIdentifier.setIdentifierCodeQualifier(identifierCodeQualifier);
	}

	/**
	* Sets the identifier code qualifier name of this ivld item identifier.
	*
	* @param identifierCodeQualifierName the identifier code qualifier name of this ivld item identifier
	*/
	@Override
	public void setIdentifierCodeQualifierName(
		java.lang.String identifierCodeQualifierName) {
		_ivldItemIdentifier.setIdentifierCodeQualifierName(identifierCodeQualifierName);
	}

	/**
	* Sets the intf inserted date of this ivld item identifier.
	*
	* @param intfInsertedDate the intf inserted date of this ivld item identifier
	*/
	@Override
	public void setIntfInsertedDate(Date intfInsertedDate) {
		_ivldItemIdentifier.setIntfInsertedDate(intfInsertedDate);
	}

	/**
	* Sets the item ID of this ivld item identifier.
	*
	* @param itemId the item ID of this ivld item identifier
	*/
	@Override
	public void setItemId(java.lang.String itemId) {
		_ivldItemIdentifier.setItemId(itemId);
	}

	/**
	* Sets the item identifier of this ivld item identifier.
	*
	* @param itemIdentifier the item identifier of this ivld item identifier
	*/
	@Override
	public void setItemIdentifier(java.lang.String itemIdentifier) {
		_ivldItemIdentifier.setItemIdentifier(itemIdentifier);
	}

	/**
	* Sets the item identifier intfid of this ivld item identifier.
	*
	* @param itemIdentifierIntfid the item identifier intfid of this ivld item identifier
	*/
	@Override
	public void setItemIdentifierIntfid(java.lang.String itemIdentifierIntfid) {
		_ivldItemIdentifier.setItemIdentifierIntfid(itemIdentifierIntfid);
	}

	/**
	* Sets the item name of this ivld item identifier.
	*
	* @param itemName the item name of this ivld item identifier
	*/
	@Override
	public void setItemName(java.lang.String itemName) {
		_ivldItemIdentifier.setItemName(itemName);
	}

	/**
	* Sets the item no of this ivld item identifier.
	*
	* @param itemNo the item no of this ivld item identifier
	*/
	@Override
	public void setItemNo(java.lang.String itemNo) {
		_ivldItemIdentifier.setItemNo(itemNo);
	}

	/**
	* Sets the item status of this ivld item identifier.
	*
	* @param itemStatus the item status of this ivld item identifier
	*/
	@Override
	public void setItemStatus(java.lang.String itemStatus) {
		_ivldItemIdentifier.setItemStatus(itemStatus);
	}

	/**
	* Sets the ivld item identifier sid of this ivld item identifier.
	*
	* @param ivldItemIdentifierSid the ivld item identifier sid of this ivld item identifier
	*/
	@Override
	public void setIvldItemIdentifierSid(int ivldItemIdentifierSid) {
		_ivldItemIdentifier.setIvldItemIdentifierSid(ivldItemIdentifierSid);
	}

	/**
	* Sets the modified by of this ivld item identifier.
	*
	* @param modifiedBy the modified by of this ivld item identifier
	*/
	@Override
	public void setModifiedBy(java.lang.String modifiedBy) {
		_ivldItemIdentifier.setModifiedBy(modifiedBy);
	}

	/**
	* Sets the modified date of this ivld item identifier.
	*
	* @param modifiedDate the modified date of this ivld item identifier
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_ivldItemIdentifier.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_ivldItemIdentifier.setNew(n);
	}

	/**
	* Sets the primary key of this ivld item identifier.
	*
	* @param primaryKey the primary key of this ivld item identifier
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_ivldItemIdentifier.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_ivldItemIdentifier.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the reason for failure of this ivld item identifier.
	*
	* @param reasonForFailure the reason for failure of this ivld item identifier
	*/
	@Override
	public void setReasonForFailure(java.lang.String reasonForFailure) {
		_ivldItemIdentifier.setReasonForFailure(reasonForFailure);
	}

	/**
	* Sets the reprocessed flag of this ivld item identifier.
	*
	* @param reprocessedFlag the reprocessed flag of this ivld item identifier
	*/
	@Override
	public void setReprocessedFlag(java.lang.String reprocessedFlag) {
		_ivldItemIdentifier.setReprocessedFlag(reprocessedFlag);
	}

	/**
	* Sets the source of this ivld item identifier.
	*
	* @param source the source of this ivld item identifier
	*/
	@Override
	public void setSource(java.lang.String source) {
		_ivldItemIdentifier.setSource(source);
	}

	/**
	* Sets the start date of this ivld item identifier.
	*
	* @param startDate the start date of this ivld item identifier
	*/
	@Override
	public void setStartDate(java.lang.String startDate) {
		_ivldItemIdentifier.setStartDate(startDate);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<IvldItemIdentifier> toCacheModel() {
		return _ivldItemIdentifier.toCacheModel();
	}

	@Override
	public IvldItemIdentifier toEscapedModel() {
		return new IvldItemIdentifierWrapper(_ivldItemIdentifier.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _ivldItemIdentifier.toString();
	}

	@Override
	public IvldItemIdentifier toUnescapedModel() {
		return new IvldItemIdentifierWrapper(_ivldItemIdentifier.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _ivldItemIdentifier.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof IvldItemIdentifierWrapper)) {
			return false;
		}

		IvldItemIdentifierWrapper ivldItemIdentifierWrapper = (IvldItemIdentifierWrapper)obj;

		if (Objects.equals(_ivldItemIdentifier,
					ivldItemIdentifierWrapper._ivldItemIdentifier)) {
			return true;
		}

		return false;
	}

	@Override
	public IvldItemIdentifier getWrappedModel() {
		return _ivldItemIdentifier;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _ivldItemIdentifier.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _ivldItemIdentifier.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_ivldItemIdentifier.resetOriginalValues();
	}

	private final IvldItemIdentifier _ivldItemIdentifier;
}