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
 * This class is a wrapper for {@link HistRelationshipBuilder}.
 * </p>
 *
 * @author
 * @see HistRelationshipBuilder
 * @generated
 */
@ProviderType
public class HistRelationshipBuilderWrapper implements HistRelationshipBuilder,
	ModelWrapper<HistRelationshipBuilder> {
	public HistRelationshipBuilderWrapper(
		HistRelationshipBuilder histRelationshipBuilder) {
		_histRelationshipBuilder = histRelationshipBuilder;
	}

	@Override
	public Class<?> getModelClass() {
		return HistRelationshipBuilder.class;
	}

	@Override
	public String getModelClassName() {
		return HistRelationshipBuilder.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("startDate", getStartDate());
		attributes.put("createdDate", getCreatedDate());
		attributes.put("createdBy", getCreatedBy());
		attributes.put("relationshipDescription", getRelationshipDescription());
		attributes.put("actionDate", getActionDate());
		attributes.put("actionFlag", getActionFlag());
		attributes.put("hierarchyDefinitionSid", getHierarchyDefinitionSid());
		attributes.put("versionNo", getVersionNo());
		attributes.put("relationshipName", getRelationshipName());
		attributes.put("relationshipBuilderSid", getRelationshipBuilderSid());
		attributes.put("hierarchyVersion", getHierarchyVersion());
		attributes.put("modifiedBy", getModifiedBy());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("relationshipType", getRelationshipType());
		attributes.put("buildType", getBuildType());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Date startDate = (Date)attributes.get("startDate");

		if (startDate != null) {
			setStartDate(startDate);
		}

		Date createdDate = (Date)attributes.get("createdDate");

		if (createdDate != null) {
			setCreatedDate(createdDate);
		}

		Integer createdBy = (Integer)attributes.get("createdBy");

		if (createdBy != null) {
			setCreatedBy(createdBy);
		}

		String relationshipDescription = (String)attributes.get(
				"relationshipDescription");

		if (relationshipDescription != null) {
			setRelationshipDescription(relationshipDescription);
		}

		Date actionDate = (Date)attributes.get("actionDate");

		if (actionDate != null) {
			setActionDate(actionDate);
		}

		String actionFlag = (String)attributes.get("actionFlag");

		if (actionFlag != null) {
			setActionFlag(actionFlag);
		}

		Integer hierarchyDefinitionSid = (Integer)attributes.get(
				"hierarchyDefinitionSid");

		if (hierarchyDefinitionSid != null) {
			setHierarchyDefinitionSid(hierarchyDefinitionSid);
		}

		Integer versionNo = (Integer)attributes.get("versionNo");

		if (versionNo != null) {
			setVersionNo(versionNo);
		}

		String relationshipName = (String)attributes.get("relationshipName");

		if (relationshipName != null) {
			setRelationshipName(relationshipName);
		}

		Integer relationshipBuilderSid = (Integer)attributes.get(
				"relationshipBuilderSid");

		if (relationshipBuilderSid != null) {
			setRelationshipBuilderSid(relationshipBuilderSid);
		}

		Integer hierarchyVersion = (Integer)attributes.get("hierarchyVersion");

		if (hierarchyVersion != null) {
			setHierarchyVersion(hierarchyVersion);
		}

		Integer modifiedBy = (Integer)attributes.get("modifiedBy");

		if (modifiedBy != null) {
			setModifiedBy(modifiedBy);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Integer relationshipType = (Integer)attributes.get("relationshipType");

		if (relationshipType != null) {
			setRelationshipType(relationshipType);
		}

		String buildType = (String)attributes.get("buildType");

		if (buildType != null) {
			setBuildType(buildType);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new HistRelationshipBuilderWrapper((HistRelationshipBuilder)_histRelationshipBuilder.clone());
	}

	@Override
	public int compareTo(HistRelationshipBuilder histRelationshipBuilder) {
		return _histRelationshipBuilder.compareTo(histRelationshipBuilder);
	}

	/**
	* Returns the action date of this hist relationship builder.
	*
	* @return the action date of this hist relationship builder
	*/
	@Override
	public Date getActionDate() {
		return _histRelationshipBuilder.getActionDate();
	}

	/**
	* Returns the action flag of this hist relationship builder.
	*
	* @return the action flag of this hist relationship builder
	*/
	@Override
	public java.lang.String getActionFlag() {
		return _histRelationshipBuilder.getActionFlag();
	}

	/**
	* Returns the build type of this hist relationship builder.
	*
	* @return the build type of this hist relationship builder
	*/
	@Override
	public java.lang.String getBuildType() {
		return _histRelationshipBuilder.getBuildType();
	}

	/**
	* Returns the created by of this hist relationship builder.
	*
	* @return the created by of this hist relationship builder
	*/
	@Override
	public int getCreatedBy() {
		return _histRelationshipBuilder.getCreatedBy();
	}

	/**
	* Returns the created date of this hist relationship builder.
	*
	* @return the created date of this hist relationship builder
	*/
	@Override
	public Date getCreatedDate() {
		return _histRelationshipBuilder.getCreatedDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _histRelationshipBuilder.getExpandoBridge();
	}

	/**
	* Returns the hierarchy definition sid of this hist relationship builder.
	*
	* @return the hierarchy definition sid of this hist relationship builder
	*/
	@Override
	public int getHierarchyDefinitionSid() {
		return _histRelationshipBuilder.getHierarchyDefinitionSid();
	}

	/**
	* Returns the hierarchy version of this hist relationship builder.
	*
	* @return the hierarchy version of this hist relationship builder
	*/
	@Override
	public int getHierarchyVersion() {
		return _histRelationshipBuilder.getHierarchyVersion();
	}

	/**
	* Returns the modified by of this hist relationship builder.
	*
	* @return the modified by of this hist relationship builder
	*/
	@Override
	public int getModifiedBy() {
		return _histRelationshipBuilder.getModifiedBy();
	}

	/**
	* Returns the modified date of this hist relationship builder.
	*
	* @return the modified date of this hist relationship builder
	*/
	@Override
	public Date getModifiedDate() {
		return _histRelationshipBuilder.getModifiedDate();
	}

	/**
	* Returns the primary key of this hist relationship builder.
	*
	* @return the primary key of this hist relationship builder
	*/
	@Override
	public com.stpl.app.service.persistence.HistRelationshipBuilderPK getPrimaryKey() {
		return _histRelationshipBuilder.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _histRelationshipBuilder.getPrimaryKeyObj();
	}

	/**
	* Returns the relationship builder sid of this hist relationship builder.
	*
	* @return the relationship builder sid of this hist relationship builder
	*/
	@Override
	public int getRelationshipBuilderSid() {
		return _histRelationshipBuilder.getRelationshipBuilderSid();
	}

	/**
	* Returns the relationship description of this hist relationship builder.
	*
	* @return the relationship description of this hist relationship builder
	*/
	@Override
	public java.lang.String getRelationshipDescription() {
		return _histRelationshipBuilder.getRelationshipDescription();
	}

	/**
	* Returns the relationship name of this hist relationship builder.
	*
	* @return the relationship name of this hist relationship builder
	*/
	@Override
	public java.lang.String getRelationshipName() {
		return _histRelationshipBuilder.getRelationshipName();
	}

	/**
	* Returns the relationship type of this hist relationship builder.
	*
	* @return the relationship type of this hist relationship builder
	*/
	@Override
	public int getRelationshipType() {
		return _histRelationshipBuilder.getRelationshipType();
	}

	/**
	* Returns the start date of this hist relationship builder.
	*
	* @return the start date of this hist relationship builder
	*/
	@Override
	public Date getStartDate() {
		return _histRelationshipBuilder.getStartDate();
	}

	/**
	* Returns the version no of this hist relationship builder.
	*
	* @return the version no of this hist relationship builder
	*/
	@Override
	public int getVersionNo() {
		return _histRelationshipBuilder.getVersionNo();
	}

	@Override
	public int hashCode() {
		return _histRelationshipBuilder.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _histRelationshipBuilder.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _histRelationshipBuilder.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _histRelationshipBuilder.isNew();
	}

	@Override
	public void persist() {
		_histRelationshipBuilder.persist();
	}

	/**
	* Sets the action date of this hist relationship builder.
	*
	* @param actionDate the action date of this hist relationship builder
	*/
	@Override
	public void setActionDate(Date actionDate) {
		_histRelationshipBuilder.setActionDate(actionDate);
	}

	/**
	* Sets the action flag of this hist relationship builder.
	*
	* @param actionFlag the action flag of this hist relationship builder
	*/
	@Override
	public void setActionFlag(java.lang.String actionFlag) {
		_histRelationshipBuilder.setActionFlag(actionFlag);
	}

	/**
	* Sets the build type of this hist relationship builder.
	*
	* @param buildType the build type of this hist relationship builder
	*/
	@Override
	public void setBuildType(java.lang.String buildType) {
		_histRelationshipBuilder.setBuildType(buildType);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_histRelationshipBuilder.setCachedModel(cachedModel);
	}

	/**
	* Sets the created by of this hist relationship builder.
	*
	* @param createdBy the created by of this hist relationship builder
	*/
	@Override
	public void setCreatedBy(int createdBy) {
		_histRelationshipBuilder.setCreatedBy(createdBy);
	}

	/**
	* Sets the created date of this hist relationship builder.
	*
	* @param createdDate the created date of this hist relationship builder
	*/
	@Override
	public void setCreatedDate(Date createdDate) {
		_histRelationshipBuilder.setCreatedDate(createdDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_histRelationshipBuilder.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_histRelationshipBuilder.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_histRelationshipBuilder.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the hierarchy definition sid of this hist relationship builder.
	*
	* @param hierarchyDefinitionSid the hierarchy definition sid of this hist relationship builder
	*/
	@Override
	public void setHierarchyDefinitionSid(int hierarchyDefinitionSid) {
		_histRelationshipBuilder.setHierarchyDefinitionSid(hierarchyDefinitionSid);
	}

	/**
	* Sets the hierarchy version of this hist relationship builder.
	*
	* @param hierarchyVersion the hierarchy version of this hist relationship builder
	*/
	@Override
	public void setHierarchyVersion(int hierarchyVersion) {
		_histRelationshipBuilder.setHierarchyVersion(hierarchyVersion);
	}

	/**
	* Sets the modified by of this hist relationship builder.
	*
	* @param modifiedBy the modified by of this hist relationship builder
	*/
	@Override
	public void setModifiedBy(int modifiedBy) {
		_histRelationshipBuilder.setModifiedBy(modifiedBy);
	}

	/**
	* Sets the modified date of this hist relationship builder.
	*
	* @param modifiedDate the modified date of this hist relationship builder
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_histRelationshipBuilder.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_histRelationshipBuilder.setNew(n);
	}

	/**
	* Sets the primary key of this hist relationship builder.
	*
	* @param primaryKey the primary key of this hist relationship builder
	*/
	@Override
	public void setPrimaryKey(
		com.stpl.app.service.persistence.HistRelationshipBuilderPK primaryKey) {
		_histRelationshipBuilder.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_histRelationshipBuilder.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the relationship builder sid of this hist relationship builder.
	*
	* @param relationshipBuilderSid the relationship builder sid of this hist relationship builder
	*/
	@Override
	public void setRelationshipBuilderSid(int relationshipBuilderSid) {
		_histRelationshipBuilder.setRelationshipBuilderSid(relationshipBuilderSid);
	}

	/**
	* Sets the relationship description of this hist relationship builder.
	*
	* @param relationshipDescription the relationship description of this hist relationship builder
	*/
	@Override
	public void setRelationshipDescription(
		java.lang.String relationshipDescription) {
		_histRelationshipBuilder.setRelationshipDescription(relationshipDescription);
	}

	/**
	* Sets the relationship name of this hist relationship builder.
	*
	* @param relationshipName the relationship name of this hist relationship builder
	*/
	@Override
	public void setRelationshipName(java.lang.String relationshipName) {
		_histRelationshipBuilder.setRelationshipName(relationshipName);
	}

	/**
	* Sets the relationship type of this hist relationship builder.
	*
	* @param relationshipType the relationship type of this hist relationship builder
	*/
	@Override
	public void setRelationshipType(int relationshipType) {
		_histRelationshipBuilder.setRelationshipType(relationshipType);
	}

	/**
	* Sets the start date of this hist relationship builder.
	*
	* @param startDate the start date of this hist relationship builder
	*/
	@Override
	public void setStartDate(Date startDate) {
		_histRelationshipBuilder.setStartDate(startDate);
	}

	/**
	* Sets the version no of this hist relationship builder.
	*
	* @param versionNo the version no of this hist relationship builder
	*/
	@Override
	public void setVersionNo(int versionNo) {
		_histRelationshipBuilder.setVersionNo(versionNo);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<HistRelationshipBuilder> toCacheModel() {
		return _histRelationshipBuilder.toCacheModel();
	}

	@Override
	public HistRelationshipBuilder toEscapedModel() {
		return new HistRelationshipBuilderWrapper(_histRelationshipBuilder.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _histRelationshipBuilder.toString();
	}

	@Override
	public HistRelationshipBuilder toUnescapedModel() {
		return new HistRelationshipBuilderWrapper(_histRelationshipBuilder.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _histRelationshipBuilder.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof HistRelationshipBuilderWrapper)) {
			return false;
		}

		HistRelationshipBuilderWrapper histRelationshipBuilderWrapper = (HistRelationshipBuilderWrapper)obj;

		if (Objects.equals(_histRelationshipBuilder,
					histRelationshipBuilderWrapper._histRelationshipBuilder)) {
			return true;
		}

		return false;
	}

	@Override
	public HistRelationshipBuilder getWrappedModel() {
		return _histRelationshipBuilder;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _histRelationshipBuilder.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _histRelationshipBuilder.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_histRelationshipBuilder.resetOriginalValues();
	}

	private final HistRelationshipBuilder _histRelationshipBuilder;
}