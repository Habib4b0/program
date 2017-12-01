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
 * This class is a wrapper for {@link DeductionGroup}.
 * </p>
 *
 * @author
 * @see DeductionGroup
 * @generated
 */
@ProviderType
public class DeductionGroupWrapper implements DeductionGroup,
	ModelWrapper<DeductionGroup> {
	public DeductionGroupWrapper(DeductionGroup deductionGroup) {
		_deductionGroup = deductionGroup;
	}

	@Override
	public Class<?> getModelClass() {
		return DeductionGroup.class;
	}

	@Override
	public String getModelClassName() {
		return DeductionGroup.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("deductionFilter", getDeductionFilter());
		attributes.put("createdDate", getCreatedDate());
		attributes.put("createdBy", getCreatedBy());
		attributes.put("deductionGroupSid", getDeductionGroupSid());
		attributes.put("deductionGroupName", getDeductionGroupName());
		attributes.put("versionNo", getVersionNo());
		attributes.put("deductionGroupDescription",
			getDeductionGroupDescription());
		attributes.put("modifiedBy", getModifiedBy());
		attributes.put("deductionGroupNo", getDeductionGroupNo());
		attributes.put("modifiedDate", getModifiedDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String deductionFilter = (String)attributes.get("deductionFilter");

		if (deductionFilter != null) {
			setDeductionFilter(deductionFilter);
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

		String deductionGroupName = (String)attributes.get("deductionGroupName");

		if (deductionGroupName != null) {
			setDeductionGroupName(deductionGroupName);
		}

		Integer versionNo = (Integer)attributes.get("versionNo");

		if (versionNo != null) {
			setVersionNo(versionNo);
		}

		String deductionGroupDescription = (String)attributes.get(
				"deductionGroupDescription");

		if (deductionGroupDescription != null) {
			setDeductionGroupDescription(deductionGroupDescription);
		}

		Integer modifiedBy = (Integer)attributes.get("modifiedBy");

		if (modifiedBy != null) {
			setModifiedBy(modifiedBy);
		}

		String deductionGroupNo = (String)attributes.get("deductionGroupNo");

		if (deductionGroupNo != null) {
			setDeductionGroupNo(deductionGroupNo);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new DeductionGroupWrapper((DeductionGroup)_deductionGroup.clone());
	}

	@Override
	public int compareTo(DeductionGroup deductionGroup) {
		return _deductionGroup.compareTo(deductionGroup);
	}

	/**
	* Returns the created by of this deduction group.
	*
	* @return the created by of this deduction group
	*/
	@Override
	public int getCreatedBy() {
		return _deductionGroup.getCreatedBy();
	}

	/**
	* Returns the created date of this deduction group.
	*
	* @return the created date of this deduction group
	*/
	@Override
	public Date getCreatedDate() {
		return _deductionGroup.getCreatedDate();
	}

	/**
	* Returns the deduction filter of this deduction group.
	*
	* @return the deduction filter of this deduction group
	*/
	@Override
	public java.lang.String getDeductionFilter() {
		return _deductionGroup.getDeductionFilter();
	}

	/**
	* Returns the deduction group description of this deduction group.
	*
	* @return the deduction group description of this deduction group
	*/
	@Override
	public java.lang.String getDeductionGroupDescription() {
		return _deductionGroup.getDeductionGroupDescription();
	}

	/**
	* Returns the deduction group name of this deduction group.
	*
	* @return the deduction group name of this deduction group
	*/
	@Override
	public java.lang.String getDeductionGroupName() {
		return _deductionGroup.getDeductionGroupName();
	}

	/**
	* Returns the deduction group no of this deduction group.
	*
	* @return the deduction group no of this deduction group
	*/
	@Override
	public java.lang.String getDeductionGroupNo() {
		return _deductionGroup.getDeductionGroupNo();
	}

	/**
	* Returns the deduction group sid of this deduction group.
	*
	* @return the deduction group sid of this deduction group
	*/
	@Override
	public int getDeductionGroupSid() {
		return _deductionGroup.getDeductionGroupSid();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _deductionGroup.getExpandoBridge();
	}

	/**
	* Returns the modified by of this deduction group.
	*
	* @return the modified by of this deduction group
	*/
	@Override
	public int getModifiedBy() {
		return _deductionGroup.getModifiedBy();
	}

	/**
	* Returns the modified date of this deduction group.
	*
	* @return the modified date of this deduction group
	*/
	@Override
	public Date getModifiedDate() {
		return _deductionGroup.getModifiedDate();
	}

	/**
	* Returns the primary key of this deduction group.
	*
	* @return the primary key of this deduction group
	*/
	@Override
	public int getPrimaryKey() {
		return _deductionGroup.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _deductionGroup.getPrimaryKeyObj();
	}

	/**
	* Returns the version no of this deduction group.
	*
	* @return the version no of this deduction group
	*/
	@Override
	public int getVersionNo() {
		return _deductionGroup.getVersionNo();
	}

	@Override
	public int hashCode() {
		return _deductionGroup.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _deductionGroup.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _deductionGroup.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _deductionGroup.isNew();
	}

	@Override
	public void persist() {
		_deductionGroup.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_deductionGroup.setCachedModel(cachedModel);
	}

	/**
	* Sets the created by of this deduction group.
	*
	* @param createdBy the created by of this deduction group
	*/
	@Override
	public void setCreatedBy(int createdBy) {
		_deductionGroup.setCreatedBy(createdBy);
	}

	/**
	* Sets the created date of this deduction group.
	*
	* @param createdDate the created date of this deduction group
	*/
	@Override
	public void setCreatedDate(Date createdDate) {
		_deductionGroup.setCreatedDate(createdDate);
	}

	/**
	* Sets the deduction filter of this deduction group.
	*
	* @param deductionFilter the deduction filter of this deduction group
	*/
	@Override
	public void setDeductionFilter(java.lang.String deductionFilter) {
		_deductionGroup.setDeductionFilter(deductionFilter);
	}

	/**
	* Sets the deduction group description of this deduction group.
	*
	* @param deductionGroupDescription the deduction group description of this deduction group
	*/
	@Override
	public void setDeductionGroupDescription(
		java.lang.String deductionGroupDescription) {
		_deductionGroup.setDeductionGroupDescription(deductionGroupDescription);
	}

	/**
	* Sets the deduction group name of this deduction group.
	*
	* @param deductionGroupName the deduction group name of this deduction group
	*/
	@Override
	public void setDeductionGroupName(java.lang.String deductionGroupName) {
		_deductionGroup.setDeductionGroupName(deductionGroupName);
	}

	/**
	* Sets the deduction group no of this deduction group.
	*
	* @param deductionGroupNo the deduction group no of this deduction group
	*/
	@Override
	public void setDeductionGroupNo(java.lang.String deductionGroupNo) {
		_deductionGroup.setDeductionGroupNo(deductionGroupNo);
	}

	/**
	* Sets the deduction group sid of this deduction group.
	*
	* @param deductionGroupSid the deduction group sid of this deduction group
	*/
	@Override
	public void setDeductionGroupSid(int deductionGroupSid) {
		_deductionGroup.setDeductionGroupSid(deductionGroupSid);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_deductionGroup.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_deductionGroup.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_deductionGroup.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the modified by of this deduction group.
	*
	* @param modifiedBy the modified by of this deduction group
	*/
	@Override
	public void setModifiedBy(int modifiedBy) {
		_deductionGroup.setModifiedBy(modifiedBy);
	}

	/**
	* Sets the modified date of this deduction group.
	*
	* @param modifiedDate the modified date of this deduction group
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_deductionGroup.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_deductionGroup.setNew(n);
	}

	/**
	* Sets the primary key of this deduction group.
	*
	* @param primaryKey the primary key of this deduction group
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_deductionGroup.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_deductionGroup.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the version no of this deduction group.
	*
	* @param versionNo the version no of this deduction group
	*/
	@Override
	public void setVersionNo(int versionNo) {
		_deductionGroup.setVersionNo(versionNo);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<DeductionGroup> toCacheModel() {
		return _deductionGroup.toCacheModel();
	}

	@Override
	public DeductionGroup toEscapedModel() {
		return new DeductionGroupWrapper(_deductionGroup.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _deductionGroup.toString();
	}

	@Override
	public DeductionGroup toUnescapedModel() {
		return new DeductionGroupWrapper(_deductionGroup.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _deductionGroup.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof DeductionGroupWrapper)) {
			return false;
		}

		DeductionGroupWrapper deductionGroupWrapper = (DeductionGroupWrapper)obj;

		if (Objects.equals(_deductionGroup,
					deductionGroupWrapper._deductionGroup)) {
			return true;
		}

		return false;
	}

	@Override
	public DeductionGroup getWrappedModel() {
		return _deductionGroup;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _deductionGroup.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _deductionGroup.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_deductionGroup.resetOriginalValues();
	}

	private final DeductionGroup _deductionGroup;
}