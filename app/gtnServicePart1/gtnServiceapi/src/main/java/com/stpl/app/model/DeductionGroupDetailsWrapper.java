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
 * This class is a wrapper for {@link DeductionGroupDetails}.
 * </p>
 *
 * @author
 * @see DeductionGroupDetails
 * @generated
 */
@ProviderType
public class DeductionGroupDetailsWrapper implements DeductionGroupDetails,
	ModelWrapper<DeductionGroupDetails> {
	public DeductionGroupDetailsWrapper(
		DeductionGroupDetails deductionGroupDetails) {
		_deductionGroupDetails = deductionGroupDetails;
	}

	@Override
	public Class<?> getModelClass() {
		return DeductionGroupDetails.class;
	}

	@Override
	public String getModelClassName() {
		return DeductionGroupDetails.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("deductionGroupDetailsSid", getDeductionGroupDetailsSid());
		attributes.put("createdDate", getCreatedDate());
		attributes.put("createdBy", getCreatedBy());
		attributes.put("deductionGroupSid", getDeductionGroupSid());
		attributes.put("versionNo", getVersionNo());
		attributes.put("modifiedBy", getModifiedBy());
		attributes.put("rsModelSid", getRsModelSid());
		attributes.put("modifiedDate", getModifiedDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Integer deductionGroupDetailsSid = (Integer)attributes.get(
				"deductionGroupDetailsSid");

		if (deductionGroupDetailsSid != null) {
			setDeductionGroupDetailsSid(deductionGroupDetailsSid);
		}

		Date createdDate = (Date)attributes.get("createdDate");

		if (createdDate != null) {
			setCreatedDate(createdDate);
		}

		Integer createdBy = (Integer)attributes.get("createdBy");

		if (createdBy != null) {
			setCreatedBy(createdBy);
		}

		Integer deductionGroupSid = (Integer)attributes.get("deductionGroupSid");

		if (deductionGroupSid != null) {
			setDeductionGroupSid(deductionGroupSid);
		}

		Integer versionNo = (Integer)attributes.get("versionNo");

		if (versionNo != null) {
			setVersionNo(versionNo);
		}

		Integer modifiedBy = (Integer)attributes.get("modifiedBy");

		if (modifiedBy != null) {
			setModifiedBy(modifiedBy);
		}

		Integer rsModelSid = (Integer)attributes.get("rsModelSid");

		if (rsModelSid != null) {
			setRsModelSid(rsModelSid);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new DeductionGroupDetailsWrapper((DeductionGroupDetails)_deductionGroupDetails.clone());
	}

	@Override
	public int compareTo(DeductionGroupDetails deductionGroupDetails) {
		return _deductionGroupDetails.compareTo(deductionGroupDetails);
	}

	/**
	* Returns the created by of this deduction group details.
	*
	* @return the created by of this deduction group details
	*/
	@Override
	public int getCreatedBy() {
		return _deductionGroupDetails.getCreatedBy();
	}

	/**
	* Returns the created date of this deduction group details.
	*
	* @return the created date of this deduction group details
	*/
	@Override
	public Date getCreatedDate() {
		return _deductionGroupDetails.getCreatedDate();
	}

	/**
	* Returns the deduction group details sid of this deduction group details.
	*
	* @return the deduction group details sid of this deduction group details
	*/
	@Override
	public int getDeductionGroupDetailsSid() {
		return _deductionGroupDetails.getDeductionGroupDetailsSid();
	}

	/**
	* Returns the deduction group sid of this deduction group details.
	*
	* @return the deduction group sid of this deduction group details
	*/
	@Override
	public int getDeductionGroupSid() {
		return _deductionGroupDetails.getDeductionGroupSid();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _deductionGroupDetails.getExpandoBridge();
	}

	/**
	* Returns the modified by of this deduction group details.
	*
	* @return the modified by of this deduction group details
	*/
	@Override
	public int getModifiedBy() {
		return _deductionGroupDetails.getModifiedBy();
	}

	/**
	* Returns the modified date of this deduction group details.
	*
	* @return the modified date of this deduction group details
	*/
	@Override
	public Date getModifiedDate() {
		return _deductionGroupDetails.getModifiedDate();
	}

	/**
	* Returns the primary key of this deduction group details.
	*
	* @return the primary key of this deduction group details
	*/
	@Override
	public int getPrimaryKey() {
		return _deductionGroupDetails.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _deductionGroupDetails.getPrimaryKeyObj();
	}

	/**
	* Returns the rs model sid of this deduction group details.
	*
	* @return the rs model sid of this deduction group details
	*/
	@Override
	public int getRsModelSid() {
		return _deductionGroupDetails.getRsModelSid();
	}

	/**
	* Returns the version no of this deduction group details.
	*
	* @return the version no of this deduction group details
	*/
	@Override
	public int getVersionNo() {
		return _deductionGroupDetails.getVersionNo();
	}

	@Override
	public int hashCode() {
		return _deductionGroupDetails.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _deductionGroupDetails.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _deductionGroupDetails.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _deductionGroupDetails.isNew();
	}

	@Override
	public void persist() {
		_deductionGroupDetails.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_deductionGroupDetails.setCachedModel(cachedModel);
	}

	/**
	* Sets the created by of this deduction group details.
	*
	* @param createdBy the created by of this deduction group details
	*/
	@Override
	public void setCreatedBy(int createdBy) {
		_deductionGroupDetails.setCreatedBy(createdBy);
	}

	/**
	* Sets the created date of this deduction group details.
	*
	* @param createdDate the created date of this deduction group details
	*/
	@Override
	public void setCreatedDate(Date createdDate) {
		_deductionGroupDetails.setCreatedDate(createdDate);
	}

	/**
	* Sets the deduction group details sid of this deduction group details.
	*
	* @param deductionGroupDetailsSid the deduction group details sid of this deduction group details
	*/
	@Override
	public void setDeductionGroupDetailsSid(int deductionGroupDetailsSid) {
		_deductionGroupDetails.setDeductionGroupDetailsSid(deductionGroupDetailsSid);
	}

	/**
	* Sets the deduction group sid of this deduction group details.
	*
	* @param deductionGroupSid the deduction group sid of this deduction group details
	*/
	@Override
	public void setDeductionGroupSid(int deductionGroupSid) {
		_deductionGroupDetails.setDeductionGroupSid(deductionGroupSid);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_deductionGroupDetails.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_deductionGroupDetails.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_deductionGroupDetails.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the modified by of this deduction group details.
	*
	* @param modifiedBy the modified by of this deduction group details
	*/
	@Override
	public void setModifiedBy(int modifiedBy) {
		_deductionGroupDetails.setModifiedBy(modifiedBy);
	}

	/**
	* Sets the modified date of this deduction group details.
	*
	* @param modifiedDate the modified date of this deduction group details
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_deductionGroupDetails.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_deductionGroupDetails.setNew(n);
	}

	/**
	* Sets the primary key of this deduction group details.
	*
	* @param primaryKey the primary key of this deduction group details
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_deductionGroupDetails.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_deductionGroupDetails.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the rs model sid of this deduction group details.
	*
	* @param rsModelSid the rs model sid of this deduction group details
	*/
	@Override
	public void setRsModelSid(int rsModelSid) {
		_deductionGroupDetails.setRsModelSid(rsModelSid);
	}

	/**
	* Sets the version no of this deduction group details.
	*
	* @param versionNo the version no of this deduction group details
	*/
	@Override
	public void setVersionNo(int versionNo) {
		_deductionGroupDetails.setVersionNo(versionNo);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<DeductionGroupDetails> toCacheModel() {
		return _deductionGroupDetails.toCacheModel();
	}

	@Override
	public DeductionGroupDetails toEscapedModel() {
		return new DeductionGroupDetailsWrapper(_deductionGroupDetails.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _deductionGroupDetails.toString();
	}

	@Override
	public DeductionGroupDetails toUnescapedModel() {
		return new DeductionGroupDetailsWrapper(_deductionGroupDetails.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _deductionGroupDetails.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof DeductionGroupDetailsWrapper)) {
			return false;
		}

		DeductionGroupDetailsWrapper deductionGroupDetailsWrapper = (DeductionGroupDetailsWrapper)obj;

		if (Objects.equals(_deductionGroupDetails,
					deductionGroupDetailsWrapper._deductionGroupDetails)) {
			return true;
		}

		return false;
	}

	@Override
	public DeductionGroupDetails getWrappedModel() {
		return _deductionGroupDetails;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _deductionGroupDetails.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _deductionGroupDetails.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_deductionGroupDetails.resetOriginalValues();
	}

	private final DeductionGroupDetails _deductionGroupDetails;
}