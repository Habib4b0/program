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
 * This class is a wrapper for {@link IvldLotMaster}.
 * </p>
 *
 * @author
 * @see IvldLotMaster
 * @generated
 */
@ProviderType
public class IvldLotMasterWrapper implements IvldLotMaster,
	ModelWrapper<IvldLotMaster> {
	public IvldLotMasterWrapper(IvldLotMaster ivldLotMaster) {
		_ivldLotMaster = ivldLotMaster;
	}

	@Override
	public Class<?> getModelClass() {
		return IvldLotMaster.class;
	}

	@Override
	public String getModelClassName() {
		return IvldLotMaster.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("reasonForFailure", getReasonForFailure());
		attributes.put("itemId", getItemId());
		attributes.put("ivldLotMasterSid", getIvldLotMasterSid());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("createdBy", getCreatedBy());
		attributes.put("createdDate", getCreatedDate());
		attributes.put("source", getSource());
		attributes.put("lastLotSoldDate", getLastLotSoldDate());
		attributes.put("lotExpirationDate", getLotExpirationDate());
		attributes.put("batchId", getBatchId());
		attributes.put("addChgDelIndicator", getAddChgDelIndicator());
		attributes.put("errorField", getErrorField());
		attributes.put("errorCode", getErrorCode());
		attributes.put("intfInsertedDate", getIntfInsertedDate());
		attributes.put("modifiedBy", getModifiedBy());
		attributes.put("lotNo", getLotNo());
		attributes.put("reprocessedFlag", getReprocessedFlag());
		attributes.put("lotMasterIntfid", getLotMasterIntfid());
		attributes.put("checkRecord", getCheckRecord());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String reasonForFailure = (String)attributes.get("reasonForFailure");

		if (reasonForFailure != null) {
			setReasonForFailure(reasonForFailure);
		}

		String itemId = (String)attributes.get("itemId");

		if (itemId != null) {
			setItemId(itemId);
		}

		Integer ivldLotMasterSid = (Integer)attributes.get("ivldLotMasterSid");

		if (ivldLotMasterSid != null) {
			setIvldLotMasterSid(ivldLotMasterSid);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String createdBy = (String)attributes.get("createdBy");

		if (createdBy != null) {
			setCreatedBy(createdBy);
		}

		Date createdDate = (Date)attributes.get("createdDate");

		if (createdDate != null) {
			setCreatedDate(createdDate);
		}

		String source = (String)attributes.get("source");

		if (source != null) {
			setSource(source);
		}

		String lastLotSoldDate = (String)attributes.get("lastLotSoldDate");

		if (lastLotSoldDate != null) {
			setLastLotSoldDate(lastLotSoldDate);
		}

		String lotExpirationDate = (String)attributes.get("lotExpirationDate");

		if (lotExpirationDate != null) {
			setLotExpirationDate(lotExpirationDate);
		}

		String batchId = (String)attributes.get("batchId");

		if (batchId != null) {
			setBatchId(batchId);
		}

		String addChgDelIndicator = (String)attributes.get("addChgDelIndicator");

		if (addChgDelIndicator != null) {
			setAddChgDelIndicator(addChgDelIndicator);
		}

		String errorField = (String)attributes.get("errorField");

		if (errorField != null) {
			setErrorField(errorField);
		}

		String errorCode = (String)attributes.get("errorCode");

		if (errorCode != null) {
			setErrorCode(errorCode);
		}

		Date intfInsertedDate = (Date)attributes.get("intfInsertedDate");

		if (intfInsertedDate != null) {
			setIntfInsertedDate(intfInsertedDate);
		}

		String modifiedBy = (String)attributes.get("modifiedBy");

		if (modifiedBy != null) {
			setModifiedBy(modifiedBy);
		}

		String lotNo = (String)attributes.get("lotNo");

		if (lotNo != null) {
			setLotNo(lotNo);
		}

		String reprocessedFlag = (String)attributes.get("reprocessedFlag");

		if (reprocessedFlag != null) {
			setReprocessedFlag(reprocessedFlag);
		}

		String lotMasterIntfid = (String)attributes.get("lotMasterIntfid");

		if (lotMasterIntfid != null) {
			setLotMasterIntfid(lotMasterIntfid);
		}

		Boolean checkRecord = (Boolean)attributes.get("checkRecord");

		if (checkRecord != null) {
			setCheckRecord(checkRecord);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new IvldLotMasterWrapper((IvldLotMaster)_ivldLotMaster.clone());
	}

	@Override
	public int compareTo(IvldLotMaster ivldLotMaster) {
		return _ivldLotMaster.compareTo(ivldLotMaster);
	}

	/**
	* Returns the add chg del indicator of this ivld lot master.
	*
	* @return the add chg del indicator of this ivld lot master
	*/
	@Override
	public java.lang.String getAddChgDelIndicator() {
		return _ivldLotMaster.getAddChgDelIndicator();
	}

	/**
	* Returns the batch ID of this ivld lot master.
	*
	* @return the batch ID of this ivld lot master
	*/
	@Override
	public java.lang.String getBatchId() {
		return _ivldLotMaster.getBatchId();
	}

	/**
	* Returns the check record of this ivld lot master.
	*
	* @return the check record of this ivld lot master
	*/
	@Override
	public boolean getCheckRecord() {
		return _ivldLotMaster.getCheckRecord();
	}

	/**
	* Returns the created by of this ivld lot master.
	*
	* @return the created by of this ivld lot master
	*/
	@Override
	public java.lang.String getCreatedBy() {
		return _ivldLotMaster.getCreatedBy();
	}

	/**
	* Returns the created date of this ivld lot master.
	*
	* @return the created date of this ivld lot master
	*/
	@Override
	public Date getCreatedDate() {
		return _ivldLotMaster.getCreatedDate();
	}

	/**
	* Returns the error code of this ivld lot master.
	*
	* @return the error code of this ivld lot master
	*/
	@Override
	public java.lang.String getErrorCode() {
		return _ivldLotMaster.getErrorCode();
	}

	/**
	* Returns the error field of this ivld lot master.
	*
	* @return the error field of this ivld lot master
	*/
	@Override
	public java.lang.String getErrorField() {
		return _ivldLotMaster.getErrorField();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _ivldLotMaster.getExpandoBridge();
	}

	/**
	* Returns the intf inserted date of this ivld lot master.
	*
	* @return the intf inserted date of this ivld lot master
	*/
	@Override
	public Date getIntfInsertedDate() {
		return _ivldLotMaster.getIntfInsertedDate();
	}

	/**
	* Returns the item ID of this ivld lot master.
	*
	* @return the item ID of this ivld lot master
	*/
	@Override
	public java.lang.String getItemId() {
		return _ivldLotMaster.getItemId();
	}

	/**
	* Returns the ivld lot master sid of this ivld lot master.
	*
	* @return the ivld lot master sid of this ivld lot master
	*/
	@Override
	public int getIvldLotMasterSid() {
		return _ivldLotMaster.getIvldLotMasterSid();
	}

	/**
	* Returns the last lot sold date of this ivld lot master.
	*
	* @return the last lot sold date of this ivld lot master
	*/
	@Override
	public java.lang.String getLastLotSoldDate() {
		return _ivldLotMaster.getLastLotSoldDate();
	}

	/**
	* Returns the lot expiration date of this ivld lot master.
	*
	* @return the lot expiration date of this ivld lot master
	*/
	@Override
	public java.lang.String getLotExpirationDate() {
		return _ivldLotMaster.getLotExpirationDate();
	}

	/**
	* Returns the lot master intfid of this ivld lot master.
	*
	* @return the lot master intfid of this ivld lot master
	*/
	@Override
	public java.lang.String getLotMasterIntfid() {
		return _ivldLotMaster.getLotMasterIntfid();
	}

	/**
	* Returns the lot no of this ivld lot master.
	*
	* @return the lot no of this ivld lot master
	*/
	@Override
	public java.lang.String getLotNo() {
		return _ivldLotMaster.getLotNo();
	}

	/**
	* Returns the modified by of this ivld lot master.
	*
	* @return the modified by of this ivld lot master
	*/
	@Override
	public java.lang.String getModifiedBy() {
		return _ivldLotMaster.getModifiedBy();
	}

	/**
	* Returns the modified date of this ivld lot master.
	*
	* @return the modified date of this ivld lot master
	*/
	@Override
	public Date getModifiedDate() {
		return _ivldLotMaster.getModifiedDate();
	}

	/**
	* Returns the primary key of this ivld lot master.
	*
	* @return the primary key of this ivld lot master
	*/
	@Override
	public int getPrimaryKey() {
		return _ivldLotMaster.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _ivldLotMaster.getPrimaryKeyObj();
	}

	/**
	* Returns the reason for failure of this ivld lot master.
	*
	* @return the reason for failure of this ivld lot master
	*/
	@Override
	public java.lang.String getReasonForFailure() {
		return _ivldLotMaster.getReasonForFailure();
	}

	/**
	* Returns the reprocessed flag of this ivld lot master.
	*
	* @return the reprocessed flag of this ivld lot master
	*/
	@Override
	public java.lang.String getReprocessedFlag() {
		return _ivldLotMaster.getReprocessedFlag();
	}

	/**
	* Returns the source of this ivld lot master.
	*
	* @return the source of this ivld lot master
	*/
	@Override
	public java.lang.String getSource() {
		return _ivldLotMaster.getSource();
	}

	@Override
	public int hashCode() {
		return _ivldLotMaster.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _ivldLotMaster.isCachedModel();
	}

	/**
	* Returns <code>true</code> if this ivld lot master is check record.
	*
	* @return <code>true</code> if this ivld lot master is check record; <code>false</code> otherwise
	*/
	@Override
	public boolean isCheckRecord() {
		return _ivldLotMaster.isCheckRecord();
	}

	@Override
	public boolean isEscapedModel() {
		return _ivldLotMaster.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _ivldLotMaster.isNew();
	}

	@Override
	public void persist() {
		_ivldLotMaster.persist();
	}

	/**
	* Sets the add chg del indicator of this ivld lot master.
	*
	* @param addChgDelIndicator the add chg del indicator of this ivld lot master
	*/
	@Override
	public void setAddChgDelIndicator(java.lang.String addChgDelIndicator) {
		_ivldLotMaster.setAddChgDelIndicator(addChgDelIndicator);
	}

	/**
	* Sets the batch ID of this ivld lot master.
	*
	* @param batchId the batch ID of this ivld lot master
	*/
	@Override
	public void setBatchId(java.lang.String batchId) {
		_ivldLotMaster.setBatchId(batchId);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_ivldLotMaster.setCachedModel(cachedModel);
	}

	/**
	* Sets whether this ivld lot master is check record.
	*
	* @param checkRecord the check record of this ivld lot master
	*/
	@Override
	public void setCheckRecord(boolean checkRecord) {
		_ivldLotMaster.setCheckRecord(checkRecord);
	}

	/**
	* Sets the created by of this ivld lot master.
	*
	* @param createdBy the created by of this ivld lot master
	*/
	@Override
	public void setCreatedBy(java.lang.String createdBy) {
		_ivldLotMaster.setCreatedBy(createdBy);
	}

	/**
	* Sets the created date of this ivld lot master.
	*
	* @param createdDate the created date of this ivld lot master
	*/
	@Override
	public void setCreatedDate(Date createdDate) {
		_ivldLotMaster.setCreatedDate(createdDate);
	}

	/**
	* Sets the error code of this ivld lot master.
	*
	* @param errorCode the error code of this ivld lot master
	*/
	@Override
	public void setErrorCode(java.lang.String errorCode) {
		_ivldLotMaster.setErrorCode(errorCode);
	}

	/**
	* Sets the error field of this ivld lot master.
	*
	* @param errorField the error field of this ivld lot master
	*/
	@Override
	public void setErrorField(java.lang.String errorField) {
		_ivldLotMaster.setErrorField(errorField);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_ivldLotMaster.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_ivldLotMaster.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_ivldLotMaster.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the intf inserted date of this ivld lot master.
	*
	* @param intfInsertedDate the intf inserted date of this ivld lot master
	*/
	@Override
	public void setIntfInsertedDate(Date intfInsertedDate) {
		_ivldLotMaster.setIntfInsertedDate(intfInsertedDate);
	}

	/**
	* Sets the item ID of this ivld lot master.
	*
	* @param itemId the item ID of this ivld lot master
	*/
	@Override
	public void setItemId(java.lang.String itemId) {
		_ivldLotMaster.setItemId(itemId);
	}

	/**
	* Sets the ivld lot master sid of this ivld lot master.
	*
	* @param ivldLotMasterSid the ivld lot master sid of this ivld lot master
	*/
	@Override
	public void setIvldLotMasterSid(int ivldLotMasterSid) {
		_ivldLotMaster.setIvldLotMasterSid(ivldLotMasterSid);
	}

	/**
	* Sets the last lot sold date of this ivld lot master.
	*
	* @param lastLotSoldDate the last lot sold date of this ivld lot master
	*/
	@Override
	public void setLastLotSoldDate(java.lang.String lastLotSoldDate) {
		_ivldLotMaster.setLastLotSoldDate(lastLotSoldDate);
	}

	/**
	* Sets the lot expiration date of this ivld lot master.
	*
	* @param lotExpirationDate the lot expiration date of this ivld lot master
	*/
	@Override
	public void setLotExpirationDate(java.lang.String lotExpirationDate) {
		_ivldLotMaster.setLotExpirationDate(lotExpirationDate);
	}

	/**
	* Sets the lot master intfid of this ivld lot master.
	*
	* @param lotMasterIntfid the lot master intfid of this ivld lot master
	*/
	@Override
	public void setLotMasterIntfid(java.lang.String lotMasterIntfid) {
		_ivldLotMaster.setLotMasterIntfid(lotMasterIntfid);
	}

	/**
	* Sets the lot no of this ivld lot master.
	*
	* @param lotNo the lot no of this ivld lot master
	*/
	@Override
	public void setLotNo(java.lang.String lotNo) {
		_ivldLotMaster.setLotNo(lotNo);
	}

	/**
	* Sets the modified by of this ivld lot master.
	*
	* @param modifiedBy the modified by of this ivld lot master
	*/
	@Override
	public void setModifiedBy(java.lang.String modifiedBy) {
		_ivldLotMaster.setModifiedBy(modifiedBy);
	}

	/**
	* Sets the modified date of this ivld lot master.
	*
	* @param modifiedDate the modified date of this ivld lot master
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_ivldLotMaster.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_ivldLotMaster.setNew(n);
	}

	/**
	* Sets the primary key of this ivld lot master.
	*
	* @param primaryKey the primary key of this ivld lot master
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_ivldLotMaster.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_ivldLotMaster.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the reason for failure of this ivld lot master.
	*
	* @param reasonForFailure the reason for failure of this ivld lot master
	*/
	@Override
	public void setReasonForFailure(java.lang.String reasonForFailure) {
		_ivldLotMaster.setReasonForFailure(reasonForFailure);
	}

	/**
	* Sets the reprocessed flag of this ivld lot master.
	*
	* @param reprocessedFlag the reprocessed flag of this ivld lot master
	*/
	@Override
	public void setReprocessedFlag(java.lang.String reprocessedFlag) {
		_ivldLotMaster.setReprocessedFlag(reprocessedFlag);
	}

	/**
	* Sets the source of this ivld lot master.
	*
	* @param source the source of this ivld lot master
	*/
	@Override
	public void setSource(java.lang.String source) {
		_ivldLotMaster.setSource(source);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<IvldLotMaster> toCacheModel() {
		return _ivldLotMaster.toCacheModel();
	}

	@Override
	public IvldLotMaster toEscapedModel() {
		return new IvldLotMasterWrapper(_ivldLotMaster.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _ivldLotMaster.toString();
	}

	@Override
	public IvldLotMaster toUnescapedModel() {
		return new IvldLotMasterWrapper(_ivldLotMaster.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _ivldLotMaster.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof IvldLotMasterWrapper)) {
			return false;
		}

		IvldLotMasterWrapper ivldLotMasterWrapper = (IvldLotMasterWrapper)obj;

		if (Objects.equals(_ivldLotMaster, ivldLotMasterWrapper._ivldLotMaster)) {
			return true;
		}

		return false;
	}

	@Override
	public IvldLotMaster getWrappedModel() {
		return _ivldLotMaster;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _ivldLotMaster.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _ivldLotMaster.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_ivldLotMaster.resetOriginalValues();
	}

	private final IvldLotMaster _ivldLotMaster;
}