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
 * This class is a wrapper for {@link ProjectionNameConfig}.
 * </p>
 *
 * @author
 * @see ProjectionNameConfig
 * @generated
 */
@ProviderType
public class ProjectionNameConfigWrapper implements ProjectionNameConfig,
	ModelWrapper<ProjectionNameConfig> {
	public ProjectionNameConfigWrapper(
		ProjectionNameConfig projectionNameConfig) {
		_projectionNameConfig = projectionNameConfig;
	}

	@Override
	public Class<?> getModelClass() {
		return ProjectionNameConfig.class;
	}

	@Override
	public String getModelClassName() {
		return ProjectionNameConfig.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("createdDate", getCreatedDate());
		attributes.put("createdBy", getCreatedBy());
		attributes.put("businessProcessType", getBusinessProcessType());
		attributes.put("versionNo", getVersionNo());
		attributes.put("modifiedBy", getModifiedBy());
		attributes.put("projectionNameConfigSid", getProjectionNameConfigSid());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("selectedAttributes", getSelectedAttributes());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Date createdDate = (Date)attributes.get("createdDate");

		if (createdDate != null) {
			setCreatedDate(createdDate);
		}

		Integer createdBy = (Integer)attributes.get("createdBy");

		if (createdBy != null) {
			setCreatedBy(createdBy);
		}

		String businessProcessType = (String)attributes.get(
				"businessProcessType");

		if (businessProcessType != null) {
			setBusinessProcessType(businessProcessType);
		}

		Integer versionNo = (Integer)attributes.get("versionNo");

		if (versionNo != null) {
			setVersionNo(versionNo);
		}

		Integer modifiedBy = (Integer)attributes.get("modifiedBy");

		if (modifiedBy != null) {
			setModifiedBy(modifiedBy);
		}

		Integer projectionNameConfigSid = (Integer)attributes.get(
				"projectionNameConfigSid");

		if (projectionNameConfigSid != null) {
			setProjectionNameConfigSid(projectionNameConfigSid);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String selectedAttributes = (String)attributes.get("selectedAttributes");

		if (selectedAttributes != null) {
			setSelectedAttributes(selectedAttributes);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new ProjectionNameConfigWrapper((ProjectionNameConfig)_projectionNameConfig.clone());
	}

	@Override
	public int compareTo(ProjectionNameConfig projectionNameConfig) {
		return _projectionNameConfig.compareTo(projectionNameConfig);
	}

	/**
	* Returns the business process type of this projection name config.
	*
	* @return the business process type of this projection name config
	*/
	@Override
	public java.lang.String getBusinessProcessType() {
		return _projectionNameConfig.getBusinessProcessType();
	}

	/**
	* Returns the created by of this projection name config.
	*
	* @return the created by of this projection name config
	*/
	@Override
	public int getCreatedBy() {
		return _projectionNameConfig.getCreatedBy();
	}

	/**
	* Returns the created date of this projection name config.
	*
	* @return the created date of this projection name config
	*/
	@Override
	public Date getCreatedDate() {
		return _projectionNameConfig.getCreatedDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _projectionNameConfig.getExpandoBridge();
	}

	/**
	* Returns the modified by of this projection name config.
	*
	* @return the modified by of this projection name config
	*/
	@Override
	public int getModifiedBy() {
		return _projectionNameConfig.getModifiedBy();
	}

	/**
	* Returns the modified date of this projection name config.
	*
	* @return the modified date of this projection name config
	*/
	@Override
	public Date getModifiedDate() {
		return _projectionNameConfig.getModifiedDate();
	}

	/**
	* Returns the primary key of this projection name config.
	*
	* @return the primary key of this projection name config
	*/
	@Override
	public int getPrimaryKey() {
		return _projectionNameConfig.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _projectionNameConfig.getPrimaryKeyObj();
	}

	/**
	* Returns the projection name config sid of this projection name config.
	*
	* @return the projection name config sid of this projection name config
	*/
	@Override
	public int getProjectionNameConfigSid() {
		return _projectionNameConfig.getProjectionNameConfigSid();
	}

	/**
	* Returns the selected attributes of this projection name config.
	*
	* @return the selected attributes of this projection name config
	*/
	@Override
	public java.lang.String getSelectedAttributes() {
		return _projectionNameConfig.getSelectedAttributes();
	}

	/**
	* Returns the version no of this projection name config.
	*
	* @return the version no of this projection name config
	*/
	@Override
	public int getVersionNo() {
		return _projectionNameConfig.getVersionNo();
	}

	@Override
	public int hashCode() {
		return _projectionNameConfig.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _projectionNameConfig.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _projectionNameConfig.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _projectionNameConfig.isNew();
	}

	@Override
	public void persist() {
		_projectionNameConfig.persist();
	}

	/**
	* Sets the business process type of this projection name config.
	*
	* @param businessProcessType the business process type of this projection name config
	*/
	@Override
	public void setBusinessProcessType(java.lang.String businessProcessType) {
		_projectionNameConfig.setBusinessProcessType(businessProcessType);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_projectionNameConfig.setCachedModel(cachedModel);
	}

	/**
	* Sets the created by of this projection name config.
	*
	* @param createdBy the created by of this projection name config
	*/
	@Override
	public void setCreatedBy(int createdBy) {
		_projectionNameConfig.setCreatedBy(createdBy);
	}

	/**
	* Sets the created date of this projection name config.
	*
	* @param createdDate the created date of this projection name config
	*/
	@Override
	public void setCreatedDate(Date createdDate) {
		_projectionNameConfig.setCreatedDate(createdDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_projectionNameConfig.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_projectionNameConfig.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_projectionNameConfig.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the modified by of this projection name config.
	*
	* @param modifiedBy the modified by of this projection name config
	*/
	@Override
	public void setModifiedBy(int modifiedBy) {
		_projectionNameConfig.setModifiedBy(modifiedBy);
	}

	/**
	* Sets the modified date of this projection name config.
	*
	* @param modifiedDate the modified date of this projection name config
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_projectionNameConfig.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_projectionNameConfig.setNew(n);
	}

	/**
	* Sets the primary key of this projection name config.
	*
	* @param primaryKey the primary key of this projection name config
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_projectionNameConfig.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_projectionNameConfig.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the projection name config sid of this projection name config.
	*
	* @param projectionNameConfigSid the projection name config sid of this projection name config
	*/
	@Override
	public void setProjectionNameConfigSid(int projectionNameConfigSid) {
		_projectionNameConfig.setProjectionNameConfigSid(projectionNameConfigSid);
	}

	/**
	* Sets the selected attributes of this projection name config.
	*
	* @param selectedAttributes the selected attributes of this projection name config
	*/
	@Override
	public void setSelectedAttributes(java.lang.String selectedAttributes) {
		_projectionNameConfig.setSelectedAttributes(selectedAttributes);
	}

	/**
	* Sets the version no of this projection name config.
	*
	* @param versionNo the version no of this projection name config
	*/
	@Override
	public void setVersionNo(int versionNo) {
		_projectionNameConfig.setVersionNo(versionNo);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<ProjectionNameConfig> toCacheModel() {
		return _projectionNameConfig.toCacheModel();
	}

	@Override
	public ProjectionNameConfig toEscapedModel() {
		return new ProjectionNameConfigWrapper(_projectionNameConfig.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _projectionNameConfig.toString();
	}

	@Override
	public ProjectionNameConfig toUnescapedModel() {
		return new ProjectionNameConfigWrapper(_projectionNameConfig.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _projectionNameConfig.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ProjectionNameConfigWrapper)) {
			return false;
		}

		ProjectionNameConfigWrapper projectionNameConfigWrapper = (ProjectionNameConfigWrapper)obj;

		if (Objects.equals(_projectionNameConfig,
					projectionNameConfigWrapper._projectionNameConfig)) {
			return true;
		}

		return false;
	}

	@Override
	public ProjectionNameConfig getWrappedModel() {
		return _projectionNameConfig;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _projectionNameConfig.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _projectionNameConfig.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_projectionNameConfig.resetOriginalValues();
	}

	private final ProjectionNameConfig _projectionNameConfig;
}