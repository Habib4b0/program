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
 * This class is a wrapper for {@link DeductionDetails}.
 * </p>
 *
 * @author
 * @see DeductionDetails
 * @generated
 */
@ProviderType
public class DeductionDetailsWrapper implements DeductionDetails,
	ModelWrapper<DeductionDetails> {
	public DeductionDetailsWrapper(DeductionDetails deductionDetails) {
		_deductionDetails = deductionDetails;
	}

	@Override
	public Class<?> getModelClass() {
		return DeductionDetails.class;
	}

	@Override
	public String getModelClassName() {
		return DeductionDetails.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("createdBy", getCreatedBy());
		attributes.put("netSalesFormulaMasterSid", getNetSalesFormulaMasterSid());
		attributes.put("modifiedBy", getModifiedBy());
		attributes.put("rsContractSid", getRsContractSid());
		attributes.put("createdDate", getCreatedDate());
		attributes.put("contractMasterSid", getContractMasterSid());
		attributes.put("deductionDetailsSid", getDeductionDetailsSid());
		attributes.put("indicator", getIndicator());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("recordLockStatus", getRecordLockStatus());
		attributes.put("source", getSource());
		attributes.put("cdrModelSid", getCdrModelSid());
		attributes.put("inboundStatus", getInboundStatus());
		attributes.put("deductionSubType", getDeductionSubType());
		attributes.put("deductionType", getDeductionType());
		attributes.put("deductionCategory", getDeductionCategory());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Integer createdBy = (Integer)attributes.get("createdBy");

		if (createdBy != null) {
			setCreatedBy(createdBy);
		}

		Integer netSalesFormulaMasterSid = (Integer)attributes.get(
				"netSalesFormulaMasterSid");

		if (netSalesFormulaMasterSid != null) {
			setNetSalesFormulaMasterSid(netSalesFormulaMasterSid);
		}

		Integer modifiedBy = (Integer)attributes.get("modifiedBy");

		if (modifiedBy != null) {
			setModifiedBy(modifiedBy);
		}

		Integer rsContractSid = (Integer)attributes.get("rsContractSid");

		if (rsContractSid != null) {
			setRsContractSid(rsContractSid);
		}

		Date createdDate = (Date)attributes.get("createdDate");

		if (createdDate != null) {
			setCreatedDate(createdDate);
		}

		Integer contractMasterSid = (Integer)attributes.get("contractMasterSid");

		if (contractMasterSid != null) {
			setContractMasterSid(contractMasterSid);
		}

		Integer deductionDetailsSid = (Integer)attributes.get(
				"deductionDetailsSid");

		if (deductionDetailsSid != null) {
			setDeductionDetailsSid(deductionDetailsSid);
		}

		String indicator = (String)attributes.get("indicator");

		if (indicator != null) {
			setIndicator(indicator);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Boolean recordLockStatus = (Boolean)attributes.get("recordLockStatus");

		if (recordLockStatus != null) {
			setRecordLockStatus(recordLockStatus);
		}

		String source = (String)attributes.get("source");

		if (source != null) {
			setSource(source);
		}

		Integer cdrModelSid = (Integer)attributes.get("cdrModelSid");

		if (cdrModelSid != null) {
			setCdrModelSid(cdrModelSid);
		}

		String inboundStatus = (String)attributes.get("inboundStatus");

		if (inboundStatus != null) {
			setInboundStatus(inboundStatus);
		}

		String deductionSubType = (String)attributes.get("deductionSubType");

		if (deductionSubType != null) {
			setDeductionSubType(deductionSubType);
		}

		String deductionType = (String)attributes.get("deductionType");

		if (deductionType != null) {
			setDeductionType(deductionType);
		}

		String deductionCategory = (String)attributes.get("deductionCategory");

		if (deductionCategory != null) {
			setDeductionCategory(deductionCategory);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new DeductionDetailsWrapper((DeductionDetails)_deductionDetails.clone());
	}

	@Override
	public int compareTo(DeductionDetails deductionDetails) {
		return _deductionDetails.compareTo(deductionDetails);
	}

	/**
	* Returns the cdr model sid of this deduction details.
	*
	* @return the cdr model sid of this deduction details
	*/
	@Override
	public int getCdrModelSid() {
		return _deductionDetails.getCdrModelSid();
	}

	/**
	* Returns the contract master sid of this deduction details.
	*
	* @return the contract master sid of this deduction details
	*/
	@Override
	public int getContractMasterSid() {
		return _deductionDetails.getContractMasterSid();
	}

	/**
	* Returns the created by of this deduction details.
	*
	* @return the created by of this deduction details
	*/
	@Override
	public int getCreatedBy() {
		return _deductionDetails.getCreatedBy();
	}

	/**
	* Returns the created date of this deduction details.
	*
	* @return the created date of this deduction details
	*/
	@Override
	public Date getCreatedDate() {
		return _deductionDetails.getCreatedDate();
	}

	/**
	* Returns the deduction category of this deduction details.
	*
	* @return the deduction category of this deduction details
	*/
	@Override
	public java.lang.String getDeductionCategory() {
		return _deductionDetails.getDeductionCategory();
	}

	/**
	* Returns the deduction details sid of this deduction details.
	*
	* @return the deduction details sid of this deduction details
	*/
	@Override
	public int getDeductionDetailsSid() {
		return _deductionDetails.getDeductionDetailsSid();
	}

	/**
	* Returns the deduction sub type of this deduction details.
	*
	* @return the deduction sub type of this deduction details
	*/
	@Override
	public java.lang.String getDeductionSubType() {
		return _deductionDetails.getDeductionSubType();
	}

	/**
	* Returns the deduction type of this deduction details.
	*
	* @return the deduction type of this deduction details
	*/
	@Override
	public java.lang.String getDeductionType() {
		return _deductionDetails.getDeductionType();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _deductionDetails.getExpandoBridge();
	}

	/**
	* Returns the inbound status of this deduction details.
	*
	* @return the inbound status of this deduction details
	*/
	@Override
	public java.lang.String getInboundStatus() {
		return _deductionDetails.getInboundStatus();
	}

	/**
	* Returns the indicator of this deduction details.
	*
	* @return the indicator of this deduction details
	*/
	@Override
	public java.lang.String getIndicator() {
		return _deductionDetails.getIndicator();
	}

	/**
	* Returns the modified by of this deduction details.
	*
	* @return the modified by of this deduction details
	*/
	@Override
	public int getModifiedBy() {
		return _deductionDetails.getModifiedBy();
	}

	/**
	* Returns the modified date of this deduction details.
	*
	* @return the modified date of this deduction details
	*/
	@Override
	public Date getModifiedDate() {
		return _deductionDetails.getModifiedDate();
	}

	/**
	* Returns the net sales formula master sid of this deduction details.
	*
	* @return the net sales formula master sid of this deduction details
	*/
	@Override
	public int getNetSalesFormulaMasterSid() {
		return _deductionDetails.getNetSalesFormulaMasterSid();
	}

	/**
	* Returns the primary key of this deduction details.
	*
	* @return the primary key of this deduction details
	*/
	@Override
	public int getPrimaryKey() {
		return _deductionDetails.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _deductionDetails.getPrimaryKeyObj();
	}

	/**
	* Returns the record lock status of this deduction details.
	*
	* @return the record lock status of this deduction details
	*/
	@Override
	public boolean getRecordLockStatus() {
		return _deductionDetails.getRecordLockStatus();
	}

	/**
	* Returns the rs contract sid of this deduction details.
	*
	* @return the rs contract sid of this deduction details
	*/
	@Override
	public int getRsContractSid() {
		return _deductionDetails.getRsContractSid();
	}

	/**
	* Returns the source of this deduction details.
	*
	* @return the source of this deduction details
	*/
	@Override
	public java.lang.String getSource() {
		return _deductionDetails.getSource();
	}

	@Override
	public int hashCode() {
		return _deductionDetails.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _deductionDetails.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _deductionDetails.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _deductionDetails.isNew();
	}

	/**
	* Returns <code>true</code> if this deduction details is record lock status.
	*
	* @return <code>true</code> if this deduction details is record lock status; <code>false</code> otherwise
	*/
	@Override
	public boolean isRecordLockStatus() {
		return _deductionDetails.isRecordLockStatus();
	}

	@Override
	public void persist() {
		_deductionDetails.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_deductionDetails.setCachedModel(cachedModel);
	}

	/**
	* Sets the cdr model sid of this deduction details.
	*
	* @param cdrModelSid the cdr model sid of this deduction details
	*/
	@Override
	public void setCdrModelSid(int cdrModelSid) {
		_deductionDetails.setCdrModelSid(cdrModelSid);
	}

	/**
	* Sets the contract master sid of this deduction details.
	*
	* @param contractMasterSid the contract master sid of this deduction details
	*/
	@Override
	public void setContractMasterSid(int contractMasterSid) {
		_deductionDetails.setContractMasterSid(contractMasterSid);
	}

	/**
	* Sets the created by of this deduction details.
	*
	* @param createdBy the created by of this deduction details
	*/
	@Override
	public void setCreatedBy(int createdBy) {
		_deductionDetails.setCreatedBy(createdBy);
	}

	/**
	* Sets the created date of this deduction details.
	*
	* @param createdDate the created date of this deduction details
	*/
	@Override
	public void setCreatedDate(Date createdDate) {
		_deductionDetails.setCreatedDate(createdDate);
	}

	/**
	* Sets the deduction category of this deduction details.
	*
	* @param deductionCategory the deduction category of this deduction details
	*/
	@Override
	public void setDeductionCategory(java.lang.String deductionCategory) {
		_deductionDetails.setDeductionCategory(deductionCategory);
	}

	/**
	* Sets the deduction details sid of this deduction details.
	*
	* @param deductionDetailsSid the deduction details sid of this deduction details
	*/
	@Override
	public void setDeductionDetailsSid(int deductionDetailsSid) {
		_deductionDetails.setDeductionDetailsSid(deductionDetailsSid);
	}

	/**
	* Sets the deduction sub type of this deduction details.
	*
	* @param deductionSubType the deduction sub type of this deduction details
	*/
	@Override
	public void setDeductionSubType(java.lang.String deductionSubType) {
		_deductionDetails.setDeductionSubType(deductionSubType);
	}

	/**
	* Sets the deduction type of this deduction details.
	*
	* @param deductionType the deduction type of this deduction details
	*/
	@Override
	public void setDeductionType(java.lang.String deductionType) {
		_deductionDetails.setDeductionType(deductionType);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_deductionDetails.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_deductionDetails.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_deductionDetails.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the inbound status of this deduction details.
	*
	* @param inboundStatus the inbound status of this deduction details
	*/
	@Override
	public void setInboundStatus(java.lang.String inboundStatus) {
		_deductionDetails.setInboundStatus(inboundStatus);
	}

	/**
	* Sets the indicator of this deduction details.
	*
	* @param indicator the indicator of this deduction details
	*/
	@Override
	public void setIndicator(java.lang.String indicator) {
		_deductionDetails.setIndicator(indicator);
	}

	/**
	* Sets the modified by of this deduction details.
	*
	* @param modifiedBy the modified by of this deduction details
	*/
	@Override
	public void setModifiedBy(int modifiedBy) {
		_deductionDetails.setModifiedBy(modifiedBy);
	}

	/**
	* Sets the modified date of this deduction details.
	*
	* @param modifiedDate the modified date of this deduction details
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_deductionDetails.setModifiedDate(modifiedDate);
	}

	/**
	* Sets the net sales formula master sid of this deduction details.
	*
	* @param netSalesFormulaMasterSid the net sales formula master sid of this deduction details
	*/
	@Override
	public void setNetSalesFormulaMasterSid(int netSalesFormulaMasterSid) {
		_deductionDetails.setNetSalesFormulaMasterSid(netSalesFormulaMasterSid);
	}

	@Override
	public void setNew(boolean n) {
		_deductionDetails.setNew(n);
	}

	/**
	* Sets the primary key of this deduction details.
	*
	* @param primaryKey the primary key of this deduction details
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_deductionDetails.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_deductionDetails.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets whether this deduction details is record lock status.
	*
	* @param recordLockStatus the record lock status of this deduction details
	*/
	@Override
	public void setRecordLockStatus(boolean recordLockStatus) {
		_deductionDetails.setRecordLockStatus(recordLockStatus);
	}

	/**
	* Sets the rs contract sid of this deduction details.
	*
	* @param rsContractSid the rs contract sid of this deduction details
	*/
	@Override
	public void setRsContractSid(int rsContractSid) {
		_deductionDetails.setRsContractSid(rsContractSid);
	}

	/**
	* Sets the source of this deduction details.
	*
	* @param source the source of this deduction details
	*/
	@Override
	public void setSource(java.lang.String source) {
		_deductionDetails.setSource(source);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<DeductionDetails> toCacheModel() {
		return _deductionDetails.toCacheModel();
	}

	@Override
	public DeductionDetails toEscapedModel() {
		return new DeductionDetailsWrapper(_deductionDetails.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _deductionDetails.toString();
	}

	@Override
	public DeductionDetails toUnescapedModel() {
		return new DeductionDetailsWrapper(_deductionDetails.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _deductionDetails.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof DeductionDetailsWrapper)) {
			return false;
		}

		DeductionDetailsWrapper deductionDetailsWrapper = (DeductionDetailsWrapper)obj;

		if (Objects.equals(_deductionDetails,
					deductionDetailsWrapper._deductionDetails)) {
			return true;
		}

		return false;
	}

	@Override
	public DeductionDetails getWrappedModel() {
		return _deductionDetails;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _deductionDetails.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _deductionDetails.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_deductionDetails.resetOriginalValues();
	}

	private final DeductionDetails _deductionDetails;
}