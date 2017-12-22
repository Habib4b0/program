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
 * This class is a wrapper for {@link IvldCompanyParent}.
 * </p>
 *
 * @author
 * @see IvldCompanyParent
 * @generated
 */
@ProviderType
public class IvldCompanyParentWrapper implements IvldCompanyParent,
	ModelWrapper<IvldCompanyParent> {
	public IvldCompanyParentWrapper(IvldCompanyParent ivldCompanyParent) {
		_ivldCompanyParent = ivldCompanyParent;
	}

	@Override
	public Class<?> getModelClass() {
		return IvldCompanyParent.class;
	}

	@Override
	public String getModelClassName() {
		return IvldCompanyParent.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("parentcompanyId", getParentcompanyId());
		attributes.put("priorParentcompanyId", getPriorParentcompanyId());
		attributes.put("reasonForFailure", getReasonForFailure());
		attributes.put("companyIdString", getCompanyIdString());
		attributes.put("lastUpdatedDate", getLastUpdatedDate());
		attributes.put("parentEndDate", getParentEndDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("parentDetailsIntfid", getParentDetailsIntfid());
		attributes.put("priorParentStartDate", getPriorParentStartDate());
		attributes.put("source", getSource());
		attributes.put("createdBy", getCreatedBy());
		attributes.put("createdDate", getCreatedDate());
		attributes.put("addChgDelIndicator", getAddChgDelIndicator());
		attributes.put("batchId", getBatchId());
		attributes.put("ivldCompanyParentSid", getIvldCompanyParentSid());
		attributes.put("errorField", getErrorField());
		attributes.put("errorCode", getErrorCode());
		attributes.put("intfInsertedDate", getIntfInsertedDate());
		attributes.put("modifiedBy", getModifiedBy());
		attributes.put("reprocessedFlag", getReprocessedFlag());
		attributes.put("parentStartDate", getParentStartDate());
		attributes.put("checkRecord", getCheckRecord());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String parentcompanyId = (String)attributes.get("parentcompanyId");

		if (parentcompanyId != null) {
			setParentcompanyId(parentcompanyId);
		}

		String priorParentcompanyId = (String)attributes.get(
				"priorParentcompanyId");

		if (priorParentcompanyId != null) {
			setPriorParentcompanyId(priorParentcompanyId);
		}

		String reasonForFailure = (String)attributes.get("reasonForFailure");

		if (reasonForFailure != null) {
			setReasonForFailure(reasonForFailure);
		}

		String companyIdString = (String)attributes.get("companyIdString");

		if (companyIdString != null) {
			setCompanyIdString(companyIdString);
		}

		String lastUpdatedDate = (String)attributes.get("lastUpdatedDate");

		if (lastUpdatedDate != null) {
			setLastUpdatedDate(lastUpdatedDate);
		}

		String parentEndDate = (String)attributes.get("parentEndDate");

		if (parentEndDate != null) {
			setParentEndDate(parentEndDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String parentDetailsIntfid = (String)attributes.get(
				"parentDetailsIntfid");

		if (parentDetailsIntfid != null) {
			setParentDetailsIntfid(parentDetailsIntfid);
		}

		String priorParentStartDate = (String)attributes.get(
				"priorParentStartDate");

		if (priorParentStartDate != null) {
			setPriorParentStartDate(priorParentStartDate);
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

		Integer ivldCompanyParentSid = (Integer)attributes.get(
				"ivldCompanyParentSid");

		if (ivldCompanyParentSid != null) {
			setIvldCompanyParentSid(ivldCompanyParentSid);
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

		String reprocessedFlag = (String)attributes.get("reprocessedFlag");

		if (reprocessedFlag != null) {
			setReprocessedFlag(reprocessedFlag);
		}

		String parentStartDate = (String)attributes.get("parentStartDate");

		if (parentStartDate != null) {
			setParentStartDate(parentStartDate);
		}

		Boolean checkRecord = (Boolean)attributes.get("checkRecord");

		if (checkRecord != null) {
			setCheckRecord(checkRecord);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new IvldCompanyParentWrapper((IvldCompanyParent)_ivldCompanyParent.clone());
	}

	@Override
	public int compareTo(IvldCompanyParent ivldCompanyParent) {
		return _ivldCompanyParent.compareTo(ivldCompanyParent);
	}

	/**
	* Returns the add chg del indicator of this ivld company parent.
	*
	* @return the add chg del indicator of this ivld company parent
	*/
	@Override
	public java.lang.String getAddChgDelIndicator() {
		return _ivldCompanyParent.getAddChgDelIndicator();
	}

	/**
	* Returns the batch ID of this ivld company parent.
	*
	* @return the batch ID of this ivld company parent
	*/
	@Override
	public java.lang.String getBatchId() {
		return _ivldCompanyParent.getBatchId();
	}

	/**
	* Returns the check record of this ivld company parent.
	*
	* @return the check record of this ivld company parent
	*/
	@Override
	public boolean getCheckRecord() {
		return _ivldCompanyParent.getCheckRecord();
	}

	/**
	* Returns the company ID string of this ivld company parent.
	*
	* @return the company ID string of this ivld company parent
	*/
	@Override
	public java.lang.String getCompanyIdString() {
		return _ivldCompanyParent.getCompanyIdString();
	}

	/**
	* Returns the created by of this ivld company parent.
	*
	* @return the created by of this ivld company parent
	*/
	@Override
	public java.lang.String getCreatedBy() {
		return _ivldCompanyParent.getCreatedBy();
	}

	/**
	* Returns the created date of this ivld company parent.
	*
	* @return the created date of this ivld company parent
	*/
	@Override
	public Date getCreatedDate() {
		return _ivldCompanyParent.getCreatedDate();
	}

	/**
	* Returns the error code of this ivld company parent.
	*
	* @return the error code of this ivld company parent
	*/
	@Override
	public java.lang.String getErrorCode() {
		return _ivldCompanyParent.getErrorCode();
	}

	/**
	* Returns the error field of this ivld company parent.
	*
	* @return the error field of this ivld company parent
	*/
	@Override
	public java.lang.String getErrorField() {
		return _ivldCompanyParent.getErrorField();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _ivldCompanyParent.getExpandoBridge();
	}

	/**
	* Returns the intf inserted date of this ivld company parent.
	*
	* @return the intf inserted date of this ivld company parent
	*/
	@Override
	public Date getIntfInsertedDate() {
		return _ivldCompanyParent.getIntfInsertedDate();
	}

	/**
	* Returns the ivld company parent sid of this ivld company parent.
	*
	* @return the ivld company parent sid of this ivld company parent
	*/
	@Override
	public int getIvldCompanyParentSid() {
		return _ivldCompanyParent.getIvldCompanyParentSid();
	}

	/**
	* Returns the last updated date of this ivld company parent.
	*
	* @return the last updated date of this ivld company parent
	*/
	@Override
	public java.lang.String getLastUpdatedDate() {
		return _ivldCompanyParent.getLastUpdatedDate();
	}

	/**
	* Returns the modified by of this ivld company parent.
	*
	* @return the modified by of this ivld company parent
	*/
	@Override
	public java.lang.String getModifiedBy() {
		return _ivldCompanyParent.getModifiedBy();
	}

	/**
	* Returns the modified date of this ivld company parent.
	*
	* @return the modified date of this ivld company parent
	*/
	@Override
	public Date getModifiedDate() {
		return _ivldCompanyParent.getModifiedDate();
	}

	/**
	* Returns the parentcompany ID of this ivld company parent.
	*
	* @return the parentcompany ID of this ivld company parent
	*/
	@Override
	public java.lang.String getParentcompanyId() {
		return _ivldCompanyParent.getParentcompanyId();
	}

	/**
	* Returns the parent details intfid of this ivld company parent.
	*
	* @return the parent details intfid of this ivld company parent
	*/
	@Override
	public java.lang.String getParentDetailsIntfid() {
		return _ivldCompanyParent.getParentDetailsIntfid();
	}

	/**
	* Returns the parent end date of this ivld company parent.
	*
	* @return the parent end date of this ivld company parent
	*/
	@Override
	public java.lang.String getParentEndDate() {
		return _ivldCompanyParent.getParentEndDate();
	}

	/**
	* Returns the parent start date of this ivld company parent.
	*
	* @return the parent start date of this ivld company parent
	*/
	@Override
	public java.lang.String getParentStartDate() {
		return _ivldCompanyParent.getParentStartDate();
	}

	/**
	* Returns the primary key of this ivld company parent.
	*
	* @return the primary key of this ivld company parent
	*/
	@Override
	public int getPrimaryKey() {
		return _ivldCompanyParent.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _ivldCompanyParent.getPrimaryKeyObj();
	}

	/**
	* Returns the prior parentcompany ID of this ivld company parent.
	*
	* @return the prior parentcompany ID of this ivld company parent
	*/
	@Override
	public java.lang.String getPriorParentcompanyId() {
		return _ivldCompanyParent.getPriorParentcompanyId();
	}

	/**
	* Returns the prior parent start date of this ivld company parent.
	*
	* @return the prior parent start date of this ivld company parent
	*/
	@Override
	public java.lang.String getPriorParentStartDate() {
		return _ivldCompanyParent.getPriorParentStartDate();
	}

	/**
	* Returns the reason for failure of this ivld company parent.
	*
	* @return the reason for failure of this ivld company parent
	*/
	@Override
	public java.lang.String getReasonForFailure() {
		return _ivldCompanyParent.getReasonForFailure();
	}

	/**
	* Returns the reprocessed flag of this ivld company parent.
	*
	* @return the reprocessed flag of this ivld company parent
	*/
	@Override
	public java.lang.String getReprocessedFlag() {
		return _ivldCompanyParent.getReprocessedFlag();
	}

	/**
	* Returns the source of this ivld company parent.
	*
	* @return the source of this ivld company parent
	*/
	@Override
	public java.lang.String getSource() {
		return _ivldCompanyParent.getSource();
	}

	@Override
	public int hashCode() {
		return _ivldCompanyParent.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _ivldCompanyParent.isCachedModel();
	}

	/**
	* Returns <code>true</code> if this ivld company parent is check record.
	*
	* @return <code>true</code> if this ivld company parent is check record; <code>false</code> otherwise
	*/
	@Override
	public boolean isCheckRecord() {
		return _ivldCompanyParent.isCheckRecord();
	}

	@Override
	public boolean isEscapedModel() {
		return _ivldCompanyParent.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _ivldCompanyParent.isNew();
	}

	@Override
	public void persist() {
		_ivldCompanyParent.persist();
	}

	/**
	* Sets the add chg del indicator of this ivld company parent.
	*
	* @param addChgDelIndicator the add chg del indicator of this ivld company parent
	*/
	@Override
	public void setAddChgDelIndicator(java.lang.String addChgDelIndicator) {
		_ivldCompanyParent.setAddChgDelIndicator(addChgDelIndicator);
	}

	/**
	* Sets the batch ID of this ivld company parent.
	*
	* @param batchId the batch ID of this ivld company parent
	*/
	@Override
	public void setBatchId(java.lang.String batchId) {
		_ivldCompanyParent.setBatchId(batchId);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_ivldCompanyParent.setCachedModel(cachedModel);
	}

	/**
	* Sets whether this ivld company parent is check record.
	*
	* @param checkRecord the check record of this ivld company parent
	*/
	@Override
	public void setCheckRecord(boolean checkRecord) {
		_ivldCompanyParent.setCheckRecord(checkRecord);
	}

	/**
	* Sets the company ID string of this ivld company parent.
	*
	* @param companyIdString the company ID string of this ivld company parent
	*/
	@Override
	public void setCompanyIdString(java.lang.String companyIdString) {
		_ivldCompanyParent.setCompanyIdString(companyIdString);
	}

	/**
	* Sets the created by of this ivld company parent.
	*
	* @param createdBy the created by of this ivld company parent
	*/
	@Override
	public void setCreatedBy(java.lang.String createdBy) {
		_ivldCompanyParent.setCreatedBy(createdBy);
	}

	/**
	* Sets the created date of this ivld company parent.
	*
	* @param createdDate the created date of this ivld company parent
	*/
	@Override
	public void setCreatedDate(Date createdDate) {
		_ivldCompanyParent.setCreatedDate(createdDate);
	}

	/**
	* Sets the error code of this ivld company parent.
	*
	* @param errorCode the error code of this ivld company parent
	*/
	@Override
	public void setErrorCode(java.lang.String errorCode) {
		_ivldCompanyParent.setErrorCode(errorCode);
	}

	/**
	* Sets the error field of this ivld company parent.
	*
	* @param errorField the error field of this ivld company parent
	*/
	@Override
	public void setErrorField(java.lang.String errorField) {
		_ivldCompanyParent.setErrorField(errorField);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_ivldCompanyParent.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_ivldCompanyParent.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_ivldCompanyParent.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the intf inserted date of this ivld company parent.
	*
	* @param intfInsertedDate the intf inserted date of this ivld company parent
	*/
	@Override
	public void setIntfInsertedDate(Date intfInsertedDate) {
		_ivldCompanyParent.setIntfInsertedDate(intfInsertedDate);
	}

	/**
	* Sets the ivld company parent sid of this ivld company parent.
	*
	* @param ivldCompanyParentSid the ivld company parent sid of this ivld company parent
	*/
	@Override
	public void setIvldCompanyParentSid(int ivldCompanyParentSid) {
		_ivldCompanyParent.setIvldCompanyParentSid(ivldCompanyParentSid);
	}

	/**
	* Sets the last updated date of this ivld company parent.
	*
	* @param lastUpdatedDate the last updated date of this ivld company parent
	*/
	@Override
	public void setLastUpdatedDate(java.lang.String lastUpdatedDate) {
		_ivldCompanyParent.setLastUpdatedDate(lastUpdatedDate);
	}

	/**
	* Sets the modified by of this ivld company parent.
	*
	* @param modifiedBy the modified by of this ivld company parent
	*/
	@Override
	public void setModifiedBy(java.lang.String modifiedBy) {
		_ivldCompanyParent.setModifiedBy(modifiedBy);
	}

	/**
	* Sets the modified date of this ivld company parent.
	*
	* @param modifiedDate the modified date of this ivld company parent
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_ivldCompanyParent.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_ivldCompanyParent.setNew(n);
	}

	/**
	* Sets the parentcompany ID of this ivld company parent.
	*
	* @param parentcompanyId the parentcompany ID of this ivld company parent
	*/
	@Override
	public void setParentcompanyId(java.lang.String parentcompanyId) {
		_ivldCompanyParent.setParentcompanyId(parentcompanyId);
	}

	/**
	* Sets the parent details intfid of this ivld company parent.
	*
	* @param parentDetailsIntfid the parent details intfid of this ivld company parent
	*/
	@Override
	public void setParentDetailsIntfid(java.lang.String parentDetailsIntfid) {
		_ivldCompanyParent.setParentDetailsIntfid(parentDetailsIntfid);
	}

	/**
	* Sets the parent end date of this ivld company parent.
	*
	* @param parentEndDate the parent end date of this ivld company parent
	*/
	@Override
	public void setParentEndDate(java.lang.String parentEndDate) {
		_ivldCompanyParent.setParentEndDate(parentEndDate);
	}

	/**
	* Sets the parent start date of this ivld company parent.
	*
	* @param parentStartDate the parent start date of this ivld company parent
	*/
	@Override
	public void setParentStartDate(java.lang.String parentStartDate) {
		_ivldCompanyParent.setParentStartDate(parentStartDate);
	}

	/**
	* Sets the primary key of this ivld company parent.
	*
	* @param primaryKey the primary key of this ivld company parent
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_ivldCompanyParent.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_ivldCompanyParent.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the prior parentcompany ID of this ivld company parent.
	*
	* @param priorParentcompanyId the prior parentcompany ID of this ivld company parent
	*/
	@Override
	public void setPriorParentcompanyId(java.lang.String priorParentcompanyId) {
		_ivldCompanyParent.setPriorParentcompanyId(priorParentcompanyId);
	}

	/**
	* Sets the prior parent start date of this ivld company parent.
	*
	* @param priorParentStartDate the prior parent start date of this ivld company parent
	*/
	@Override
	public void setPriorParentStartDate(java.lang.String priorParentStartDate) {
		_ivldCompanyParent.setPriorParentStartDate(priorParentStartDate);
	}

	/**
	* Sets the reason for failure of this ivld company parent.
	*
	* @param reasonForFailure the reason for failure of this ivld company parent
	*/
	@Override
	public void setReasonForFailure(java.lang.String reasonForFailure) {
		_ivldCompanyParent.setReasonForFailure(reasonForFailure);
	}

	/**
	* Sets the reprocessed flag of this ivld company parent.
	*
	* @param reprocessedFlag the reprocessed flag of this ivld company parent
	*/
	@Override
	public void setReprocessedFlag(java.lang.String reprocessedFlag) {
		_ivldCompanyParent.setReprocessedFlag(reprocessedFlag);
	}

	/**
	* Sets the source of this ivld company parent.
	*
	* @param source the source of this ivld company parent
	*/
	@Override
	public void setSource(java.lang.String source) {
		_ivldCompanyParent.setSource(source);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<IvldCompanyParent> toCacheModel() {
		return _ivldCompanyParent.toCacheModel();
	}

	@Override
	public IvldCompanyParent toEscapedModel() {
		return new IvldCompanyParentWrapper(_ivldCompanyParent.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _ivldCompanyParent.toString();
	}

	@Override
	public IvldCompanyParent toUnescapedModel() {
		return new IvldCompanyParentWrapper(_ivldCompanyParent.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _ivldCompanyParent.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof IvldCompanyParentWrapper)) {
			return false;
		}

		IvldCompanyParentWrapper ivldCompanyParentWrapper = (IvldCompanyParentWrapper)obj;

		if (Objects.equals(_ivldCompanyParent,
					ivldCompanyParentWrapper._ivldCompanyParent)) {
			return true;
		}

		return false;
	}

	@Override
	public IvldCompanyParent getWrappedModel() {
		return _ivldCompanyParent;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _ivldCompanyParent.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _ivldCompanyParent.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_ivldCompanyParent.resetOriginalValues();
	}

	private final IvldCompanyParent _ivldCompanyParent;
}