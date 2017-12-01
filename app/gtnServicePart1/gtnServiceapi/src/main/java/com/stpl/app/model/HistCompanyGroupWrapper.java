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
 * This class is a wrapper for {@link HistCompanyGroup}.
 * </p>
 *
 * @author
 * @see HistCompanyGroup
 * @generated
 */
@ProviderType
public class HistCompanyGroupWrapper implements HistCompanyGroup,
	ModelWrapper<HistCompanyGroup> {
	public HistCompanyGroupWrapper(HistCompanyGroup histCompanyGroup) {
		_histCompanyGroup = histCompanyGroup;
	}

	@Override
	public Class<?> getModelClass() {
		return HistCompanyGroup.class;
	}

	@Override
	public String getModelClassName() {
		return HistCompanyGroup.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("companyGroupNo", getCompanyGroupNo());
		attributes.put("createdDate", getCreatedDate());
		attributes.put("createdBy", getCreatedBy());
		attributes.put("actionDate", getActionDate());
		attributes.put("actionFlag", getActionFlag());
		attributes.put("companyGroupSid", getCompanyGroupSid());
		attributes.put("companyGroupDescription", getCompanyGroupDescription());
		attributes.put("versionNo", getVersionNo());
		attributes.put("modifiedBy", getModifiedBy());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("companyGroupName", getCompanyGroupName());
		attributes.put("companyFilter", getCompanyFilter());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String companyGroupNo = (String)attributes.get("companyGroupNo");

		if (companyGroupNo != null) {
			setCompanyGroupNo(companyGroupNo);
		}

		Date createdDate = (Date)attributes.get("createdDate");

		if (createdDate != null) {
			setCreatedDate(createdDate);
		}

		Integer createdBy = (Integer)attributes.get("createdBy");

		if (createdBy != null) {
			setCreatedBy(createdBy);
		}

		Date actionDate = (Date)attributes.get("actionDate");

		if (actionDate != null) {
			setActionDate(actionDate);
		}

		String actionFlag = (String)attributes.get("actionFlag");

		if (actionFlag != null) {
			setActionFlag(actionFlag);
		}

		Integer companyGroupSid = (Integer)attributes.get("companyGroupSid");

		if (companyGroupSid != null) {
			setCompanyGroupSid(companyGroupSid);
		}

		String companyGroupDescription = (String)attributes.get(
				"companyGroupDescription");

		if (companyGroupDescription != null) {
			setCompanyGroupDescription(companyGroupDescription);
		}

		Integer versionNo = (Integer)attributes.get("versionNo");

		if (versionNo != null) {
			setVersionNo(versionNo);
		}

		Integer modifiedBy = (Integer)attributes.get("modifiedBy");

		if (modifiedBy != null) {
			setModifiedBy(modifiedBy);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String companyGroupName = (String)attributes.get("companyGroupName");

		if (companyGroupName != null) {
			setCompanyGroupName(companyGroupName);
		}

		String companyFilter = (String)attributes.get("companyFilter");

		if (companyFilter != null) {
			setCompanyFilter(companyFilter);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new HistCompanyGroupWrapper((HistCompanyGroup)_histCompanyGroup.clone());
	}

	@Override
	public int compareTo(HistCompanyGroup histCompanyGroup) {
		return _histCompanyGroup.compareTo(histCompanyGroup);
	}

	/**
	* Returns the action date of this hist company group.
	*
	* @return the action date of this hist company group
	*/
	@Override
	public Date getActionDate() {
		return _histCompanyGroup.getActionDate();
	}

	/**
	* Returns the action flag of this hist company group.
	*
	* @return the action flag of this hist company group
	*/
	@Override
	public java.lang.String getActionFlag() {
		return _histCompanyGroup.getActionFlag();
	}

	/**
	* Returns the company filter of this hist company group.
	*
	* @return the company filter of this hist company group
	*/
	@Override
	public java.lang.String getCompanyFilter() {
		return _histCompanyGroup.getCompanyFilter();
	}

	/**
	* Returns the company group description of this hist company group.
	*
	* @return the company group description of this hist company group
	*/
	@Override
	public java.lang.String getCompanyGroupDescription() {
		return _histCompanyGroup.getCompanyGroupDescription();
	}

	/**
	* Returns the company group name of this hist company group.
	*
	* @return the company group name of this hist company group
	*/
	@Override
	public java.lang.String getCompanyGroupName() {
		return _histCompanyGroup.getCompanyGroupName();
	}

	/**
	* Returns the company group no of this hist company group.
	*
	* @return the company group no of this hist company group
	*/
	@Override
	public java.lang.String getCompanyGroupNo() {
		return _histCompanyGroup.getCompanyGroupNo();
	}

	/**
	* Returns the company group sid of this hist company group.
	*
	* @return the company group sid of this hist company group
	*/
	@Override
	public int getCompanyGroupSid() {
		return _histCompanyGroup.getCompanyGroupSid();
	}

	/**
	* Returns the created by of this hist company group.
	*
	* @return the created by of this hist company group
	*/
	@Override
	public int getCreatedBy() {
		return _histCompanyGroup.getCreatedBy();
	}

	/**
	* Returns the created date of this hist company group.
	*
	* @return the created date of this hist company group
	*/
	@Override
	public Date getCreatedDate() {
		return _histCompanyGroup.getCreatedDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _histCompanyGroup.getExpandoBridge();
	}

	/**
	* Returns the modified by of this hist company group.
	*
	* @return the modified by of this hist company group
	*/
	@Override
	public int getModifiedBy() {
		return _histCompanyGroup.getModifiedBy();
	}

	/**
	* Returns the modified date of this hist company group.
	*
	* @return the modified date of this hist company group
	*/
	@Override
	public Date getModifiedDate() {
		return _histCompanyGroup.getModifiedDate();
	}

	/**
	* Returns the primary key of this hist company group.
	*
	* @return the primary key of this hist company group
	*/
	@Override
	public com.stpl.app.service.persistence.HistCompanyGroupPK getPrimaryKey() {
		return _histCompanyGroup.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _histCompanyGroup.getPrimaryKeyObj();
	}

	/**
	* Returns the version no of this hist company group.
	*
	* @return the version no of this hist company group
	*/
	@Override
	public int getVersionNo() {
		return _histCompanyGroup.getVersionNo();
	}

	@Override
	public int hashCode() {
		return _histCompanyGroup.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _histCompanyGroup.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _histCompanyGroup.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _histCompanyGroup.isNew();
	}

	@Override
	public void persist() {
		_histCompanyGroup.persist();
	}

	/**
	* Sets the action date of this hist company group.
	*
	* @param actionDate the action date of this hist company group
	*/
	@Override
	public void setActionDate(Date actionDate) {
		_histCompanyGroup.setActionDate(actionDate);
	}

	/**
	* Sets the action flag of this hist company group.
	*
	* @param actionFlag the action flag of this hist company group
	*/
	@Override
	public void setActionFlag(java.lang.String actionFlag) {
		_histCompanyGroup.setActionFlag(actionFlag);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_histCompanyGroup.setCachedModel(cachedModel);
	}

	/**
	* Sets the company filter of this hist company group.
	*
	* @param companyFilter the company filter of this hist company group
	*/
	@Override
	public void setCompanyFilter(java.lang.String companyFilter) {
		_histCompanyGroup.setCompanyFilter(companyFilter);
	}

	/**
	* Sets the company group description of this hist company group.
	*
	* @param companyGroupDescription the company group description of this hist company group
	*/
	@Override
	public void setCompanyGroupDescription(
		java.lang.String companyGroupDescription) {
		_histCompanyGroup.setCompanyGroupDescription(companyGroupDescription);
	}

	/**
	* Sets the company group name of this hist company group.
	*
	* @param companyGroupName the company group name of this hist company group
	*/
	@Override
	public void setCompanyGroupName(java.lang.String companyGroupName) {
		_histCompanyGroup.setCompanyGroupName(companyGroupName);
	}

	/**
	* Sets the company group no of this hist company group.
	*
	* @param companyGroupNo the company group no of this hist company group
	*/
	@Override
	public void setCompanyGroupNo(java.lang.String companyGroupNo) {
		_histCompanyGroup.setCompanyGroupNo(companyGroupNo);
	}

	/**
	* Sets the company group sid of this hist company group.
	*
	* @param companyGroupSid the company group sid of this hist company group
	*/
	@Override
	public void setCompanyGroupSid(int companyGroupSid) {
		_histCompanyGroup.setCompanyGroupSid(companyGroupSid);
	}

	/**
	* Sets the created by of this hist company group.
	*
	* @param createdBy the created by of this hist company group
	*/
	@Override
	public void setCreatedBy(int createdBy) {
		_histCompanyGroup.setCreatedBy(createdBy);
	}

	/**
	* Sets the created date of this hist company group.
	*
	* @param createdDate the created date of this hist company group
	*/
	@Override
	public void setCreatedDate(Date createdDate) {
		_histCompanyGroup.setCreatedDate(createdDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_histCompanyGroup.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_histCompanyGroup.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_histCompanyGroup.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the modified by of this hist company group.
	*
	* @param modifiedBy the modified by of this hist company group
	*/
	@Override
	public void setModifiedBy(int modifiedBy) {
		_histCompanyGroup.setModifiedBy(modifiedBy);
	}

	/**
	* Sets the modified date of this hist company group.
	*
	* @param modifiedDate the modified date of this hist company group
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_histCompanyGroup.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_histCompanyGroup.setNew(n);
	}

	/**
	* Sets the primary key of this hist company group.
	*
	* @param primaryKey the primary key of this hist company group
	*/
	@Override
	public void setPrimaryKey(
		com.stpl.app.service.persistence.HistCompanyGroupPK primaryKey) {
		_histCompanyGroup.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_histCompanyGroup.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the version no of this hist company group.
	*
	* @param versionNo the version no of this hist company group
	*/
	@Override
	public void setVersionNo(int versionNo) {
		_histCompanyGroup.setVersionNo(versionNo);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<HistCompanyGroup> toCacheModel() {
		return _histCompanyGroup.toCacheModel();
	}

	@Override
	public HistCompanyGroup toEscapedModel() {
		return new HistCompanyGroupWrapper(_histCompanyGroup.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _histCompanyGroup.toString();
	}

	@Override
	public HistCompanyGroup toUnescapedModel() {
		return new HistCompanyGroupWrapper(_histCompanyGroup.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _histCompanyGroup.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof HistCompanyGroupWrapper)) {
			return false;
		}

		HistCompanyGroupWrapper histCompanyGroupWrapper = (HistCompanyGroupWrapper)obj;

		if (Objects.equals(_histCompanyGroup,
					histCompanyGroupWrapper._histCompanyGroup)) {
			return true;
		}

		return false;
	}

	@Override
	public HistCompanyGroup getWrappedModel() {
		return _histCompanyGroup;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _histCompanyGroup.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _histCompanyGroup.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_histCompanyGroup.resetOriginalValues();
	}

	private final HistCompanyGroup _histCompanyGroup;
}