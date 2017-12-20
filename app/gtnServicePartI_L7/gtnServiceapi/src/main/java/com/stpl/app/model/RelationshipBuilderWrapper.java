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
 * This class is a wrapper for {@link RelationshipBuilder}.
 * </p>
 *
 * @author
 * @see RelationshipBuilder
 * @generated
 */
@ProviderType
public class RelationshipBuilderWrapper implements RelationshipBuilder,
	ModelWrapper<RelationshipBuilder> {
	public RelationshipBuilderWrapper(RelationshipBuilder relationshipBuilder) {
		_relationshipBuilder = relationshipBuilder;
	}

	@Override
	public Class<?> getModelClass() {
		return RelationshipBuilder.class;
	}

	@Override
	public String getModelClassName() {
		return RelationshipBuilder.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("startDate", getStartDate());
		attributes.put("createdDate", getCreatedDate());
		attributes.put("createdBy", getCreatedBy());
		attributes.put("relationshipDescription", getRelationshipDescription());
		attributes.put("hierarchyDefinitionSid", getHierarchyDefinitionSid());
		attributes.put("versionNo", getVersionNo());
		attributes.put("relationshipName", getRelationshipName());
		attributes.put("relationshipBuilderSid", getRelationshipBuilderSid());
		attributes.put("hierarchyVersion", getHierarchyVersion());
		attributes.put("modifiedBy", getModifiedBy());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("deductionRelation", getDeductionRelation());
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

		Integer deductionRelation = (Integer)attributes.get("deductionRelation");

		if (deductionRelation != null) {
			setDeductionRelation(deductionRelation);
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
		return new RelationshipBuilderWrapper((RelationshipBuilder)_relationshipBuilder.clone());
	}

	@Override
	public int compareTo(RelationshipBuilder relationshipBuilder) {
		return _relationshipBuilder.compareTo(relationshipBuilder);
	}

	/**
	* Returns the build type of this relationship builder.
	*
	* @return the build type of this relationship builder
	*/
	@Override
	public java.lang.String getBuildType() {
		return _relationshipBuilder.getBuildType();
	}

	/**
	* Returns the created by of this relationship builder.
	*
	* @return the created by of this relationship builder
	*/
	@Override
	public int getCreatedBy() {
		return _relationshipBuilder.getCreatedBy();
	}

	/**
	* Returns the created date of this relationship builder.
	*
	* @return the created date of this relationship builder
	*/
	@Override
	public Date getCreatedDate() {
		return _relationshipBuilder.getCreatedDate();
	}

	/**
	* Returns the deduction relation of this relationship builder.
	*
	* @return the deduction relation of this relationship builder
	*/
	@Override
	public int getDeductionRelation() {
		return _relationshipBuilder.getDeductionRelation();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _relationshipBuilder.getExpandoBridge();
	}

	/**
	* Returns the hierarchy definition sid of this relationship builder.
	*
	* @return the hierarchy definition sid of this relationship builder
	*/
	@Override
	public int getHierarchyDefinitionSid() {
		return _relationshipBuilder.getHierarchyDefinitionSid();
	}

	/**
	* Returns the hierarchy version of this relationship builder.
	*
	* @return the hierarchy version of this relationship builder
	*/
	@Override
	public int getHierarchyVersion() {
		return _relationshipBuilder.getHierarchyVersion();
	}

	/**
	* Returns the modified by of this relationship builder.
	*
	* @return the modified by of this relationship builder
	*/
	@Override
	public int getModifiedBy() {
		return _relationshipBuilder.getModifiedBy();
	}

	/**
	* Returns the modified date of this relationship builder.
	*
	* @return the modified date of this relationship builder
	*/
	@Override
	public Date getModifiedDate() {
		return _relationshipBuilder.getModifiedDate();
	}

	/**
	* Returns the primary key of this relationship builder.
	*
	* @return the primary key of this relationship builder
	*/
	@Override
	public int getPrimaryKey() {
		return _relationshipBuilder.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _relationshipBuilder.getPrimaryKeyObj();
	}

	/**
	* Returns the relationship builder sid of this relationship builder.
	*
	* @return the relationship builder sid of this relationship builder
	*/
	@Override
	public int getRelationshipBuilderSid() {
		return _relationshipBuilder.getRelationshipBuilderSid();
	}

	/**
	* Returns the relationship description of this relationship builder.
	*
	* @return the relationship description of this relationship builder
	*/
	@Override
	public java.lang.String getRelationshipDescription() {
		return _relationshipBuilder.getRelationshipDescription();
	}

	/**
	* Returns the relationship name of this relationship builder.
	*
	* @return the relationship name of this relationship builder
	*/
	@Override
	public java.lang.String getRelationshipName() {
		return _relationshipBuilder.getRelationshipName();
	}

	/**
	* Returns the relationship type of this relationship builder.
	*
	* @return the relationship type of this relationship builder
	*/
	@Override
	public int getRelationshipType() {
		return _relationshipBuilder.getRelationshipType();
	}

	/**
	* Returns the start date of this relationship builder.
	*
	* @return the start date of this relationship builder
	*/
	@Override
	public Date getStartDate() {
		return _relationshipBuilder.getStartDate();
	}

	/**
	* Returns the version no of this relationship builder.
	*
	* @return the version no of this relationship builder
	*/
	@Override
	public int getVersionNo() {
		return _relationshipBuilder.getVersionNo();
	}

	@Override
	public int hashCode() {
		return _relationshipBuilder.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _relationshipBuilder.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _relationshipBuilder.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _relationshipBuilder.isNew();
	}

	@Override
	public void persist() {
		_relationshipBuilder.persist();
	}

	/**
	* Sets the build type of this relationship builder.
	*
	* @param buildType the build type of this relationship builder
	*/
	@Override
	public void setBuildType(java.lang.String buildType) {
		_relationshipBuilder.setBuildType(buildType);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_relationshipBuilder.setCachedModel(cachedModel);
	}

	/**
	* Sets the created by of this relationship builder.
	*
	* @param createdBy the created by of this relationship builder
	*/
	@Override
	public void setCreatedBy(int createdBy) {
		_relationshipBuilder.setCreatedBy(createdBy);
	}

	/**
	* Sets the created date of this relationship builder.
	*
	* @param createdDate the created date of this relationship builder
	*/
	@Override
	public void setCreatedDate(Date createdDate) {
		_relationshipBuilder.setCreatedDate(createdDate);
	}

	/**
	* Sets the deduction relation of this relationship builder.
	*
	* @param deductionRelation the deduction relation of this relationship builder
	*/
	@Override
	public void setDeductionRelation(int deductionRelation) {
		_relationshipBuilder.setDeductionRelation(deductionRelation);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_relationshipBuilder.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_relationshipBuilder.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_relationshipBuilder.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the hierarchy definition sid of this relationship builder.
	*
	* @param hierarchyDefinitionSid the hierarchy definition sid of this relationship builder
	*/
	@Override
	public void setHierarchyDefinitionSid(int hierarchyDefinitionSid) {
		_relationshipBuilder.setHierarchyDefinitionSid(hierarchyDefinitionSid);
	}

	/**
	* Sets the hierarchy version of this relationship builder.
	*
	* @param hierarchyVersion the hierarchy version of this relationship builder
	*/
	@Override
	public void setHierarchyVersion(int hierarchyVersion) {
		_relationshipBuilder.setHierarchyVersion(hierarchyVersion);
	}

	/**
	* Sets the modified by of this relationship builder.
	*
	* @param modifiedBy the modified by of this relationship builder
	*/
	@Override
	public void setModifiedBy(int modifiedBy) {
		_relationshipBuilder.setModifiedBy(modifiedBy);
	}

	/**
	* Sets the modified date of this relationship builder.
	*
	* @param modifiedDate the modified date of this relationship builder
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_relationshipBuilder.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_relationshipBuilder.setNew(n);
	}

	/**
	* Sets the primary key of this relationship builder.
	*
	* @param primaryKey the primary key of this relationship builder
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_relationshipBuilder.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_relationshipBuilder.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the relationship builder sid of this relationship builder.
	*
	* @param relationshipBuilderSid the relationship builder sid of this relationship builder
	*/
	@Override
	public void setRelationshipBuilderSid(int relationshipBuilderSid) {
		_relationshipBuilder.setRelationshipBuilderSid(relationshipBuilderSid);
	}

	/**
	* Sets the relationship description of this relationship builder.
	*
	* @param relationshipDescription the relationship description of this relationship builder
	*/
	@Override
	public void setRelationshipDescription(
		java.lang.String relationshipDescription) {
		_relationshipBuilder.setRelationshipDescription(relationshipDescription);
	}

	/**
	* Sets the relationship name of this relationship builder.
	*
	* @param relationshipName the relationship name of this relationship builder
	*/
	@Override
	public void setRelationshipName(java.lang.String relationshipName) {
		_relationshipBuilder.setRelationshipName(relationshipName);
	}

	/**
	* Sets the relationship type of this relationship builder.
	*
	* @param relationshipType the relationship type of this relationship builder
	*/
	@Override
	public void setRelationshipType(int relationshipType) {
		_relationshipBuilder.setRelationshipType(relationshipType);
	}

	/**
	* Sets the start date of this relationship builder.
	*
	* @param startDate the start date of this relationship builder
	*/
	@Override
	public void setStartDate(Date startDate) {
		_relationshipBuilder.setStartDate(startDate);
	}

	/**
	* Sets the version no of this relationship builder.
	*
	* @param versionNo the version no of this relationship builder
	*/
	@Override
	public void setVersionNo(int versionNo) {
		_relationshipBuilder.setVersionNo(versionNo);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<RelationshipBuilder> toCacheModel() {
		return _relationshipBuilder.toCacheModel();
	}

	@Override
	public RelationshipBuilder toEscapedModel() {
		return new RelationshipBuilderWrapper(_relationshipBuilder.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _relationshipBuilder.toString();
	}

	@Override
	public RelationshipBuilder toUnescapedModel() {
		return new RelationshipBuilderWrapper(_relationshipBuilder.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _relationshipBuilder.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof RelationshipBuilderWrapper)) {
			return false;
		}

		RelationshipBuilderWrapper relationshipBuilderWrapper = (RelationshipBuilderWrapper)obj;

		if (Objects.equals(_relationshipBuilder,
					relationshipBuilderWrapper._relationshipBuilder)) {
			return true;
		}

		return false;
	}

	@Override
	public RelationshipBuilder getWrappedModel() {
		return _relationshipBuilder;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _relationshipBuilder.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _relationshipBuilder.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_relationshipBuilder.resetOriginalValues();
	}

	private final RelationshipBuilder _relationshipBuilder;
}