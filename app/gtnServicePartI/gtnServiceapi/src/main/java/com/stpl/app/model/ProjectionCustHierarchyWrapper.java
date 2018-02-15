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

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link ProjectionCustHierarchy}.
 * </p>
 *
 * @author
 * @see ProjectionCustHierarchy
 * @generated
 */
@ProviderType
public class ProjectionCustHierarchyWrapper implements ProjectionCustHierarchy,
	ModelWrapper<ProjectionCustHierarchy> {
	public ProjectionCustHierarchyWrapper(
		ProjectionCustHierarchy projectionCustHierarchy) {
		_projectionCustHierarchy = projectionCustHierarchy;
	}

	@Override
	public Class<?> getModelClass() {
		return ProjectionCustHierarchy.class;
	}

	@Override
	public String getModelClassName() {
		return ProjectionCustHierarchy.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("projectionMasterSid", getProjectionMasterSid());
		attributes.put("projectionCustHierarchySid",
			getProjectionCustHierarchySid());
		attributes.put("relationshipLevelSid", getRelationshipLevelSid());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Integer projectionMasterSid = (Integer)attributes.get(
				"projectionMasterSid");

		if (projectionMasterSid != null) {
			setProjectionMasterSid(projectionMasterSid);
		}

		Integer projectionCustHierarchySid = (Integer)attributes.get(
				"projectionCustHierarchySid");

		if (projectionCustHierarchySid != null) {
			setProjectionCustHierarchySid(projectionCustHierarchySid);
		}

		Integer relationshipLevelSid = (Integer)attributes.get(
				"relationshipLevelSid");

		if (relationshipLevelSid != null) {
			setRelationshipLevelSid(relationshipLevelSid);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new ProjectionCustHierarchyWrapper((ProjectionCustHierarchy)_projectionCustHierarchy.clone());
	}

	@Override
	public int compareTo(ProjectionCustHierarchy projectionCustHierarchy) {
		return _projectionCustHierarchy.compareTo(projectionCustHierarchy);
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _projectionCustHierarchy.getExpandoBridge();
	}

	/**
	* Returns the primary key of this projection cust hierarchy.
	*
	* @return the primary key of this projection cust hierarchy
	*/
	@Override
	public int getPrimaryKey() {
		return _projectionCustHierarchy.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _projectionCustHierarchy.getPrimaryKeyObj();
	}

	/**
	* Returns the projection cust hierarchy sid of this projection cust hierarchy.
	*
	* @return the projection cust hierarchy sid of this projection cust hierarchy
	*/
	@Override
	public int getProjectionCustHierarchySid() {
		return _projectionCustHierarchy.getProjectionCustHierarchySid();
	}

	/**
	* Returns the projection master sid of this projection cust hierarchy.
	*
	* @return the projection master sid of this projection cust hierarchy
	*/
	@Override
	public int getProjectionMasterSid() {
		return _projectionCustHierarchy.getProjectionMasterSid();
	}

	/**
	* Returns the relationship level sid of this projection cust hierarchy.
	*
	* @return the relationship level sid of this projection cust hierarchy
	*/
	@Override
	public int getRelationshipLevelSid() {
		return _projectionCustHierarchy.getRelationshipLevelSid();
	}

	@Override
	public int hashCode() {
		return _projectionCustHierarchy.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _projectionCustHierarchy.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _projectionCustHierarchy.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _projectionCustHierarchy.isNew();
	}

	@Override
	public void persist() {
		_projectionCustHierarchy.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_projectionCustHierarchy.setCachedModel(cachedModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_projectionCustHierarchy.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_projectionCustHierarchy.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_projectionCustHierarchy.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public void setNew(boolean n) {
		_projectionCustHierarchy.setNew(n);
	}

	/**
	* Sets the primary key of this projection cust hierarchy.
	*
	* @param primaryKey the primary key of this projection cust hierarchy
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_projectionCustHierarchy.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_projectionCustHierarchy.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the projection cust hierarchy sid of this projection cust hierarchy.
	*
	* @param projectionCustHierarchySid the projection cust hierarchy sid of this projection cust hierarchy
	*/
	@Override
	public void setProjectionCustHierarchySid(int projectionCustHierarchySid) {
		_projectionCustHierarchy.setProjectionCustHierarchySid(projectionCustHierarchySid);
	}

	/**
	* Sets the projection master sid of this projection cust hierarchy.
	*
	* @param projectionMasterSid the projection master sid of this projection cust hierarchy
	*/
	@Override
	public void setProjectionMasterSid(int projectionMasterSid) {
		_projectionCustHierarchy.setProjectionMasterSid(projectionMasterSid);
	}

	/**
	* Sets the relationship level sid of this projection cust hierarchy.
	*
	* @param relationshipLevelSid the relationship level sid of this projection cust hierarchy
	*/
	@Override
	public void setRelationshipLevelSid(int relationshipLevelSid) {
		_projectionCustHierarchy.setRelationshipLevelSid(relationshipLevelSid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<ProjectionCustHierarchy> toCacheModel() {
		return _projectionCustHierarchy.toCacheModel();
	}

	@Override
	public ProjectionCustHierarchy toEscapedModel() {
		return new ProjectionCustHierarchyWrapper(_projectionCustHierarchy.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _projectionCustHierarchy.toString();
	}

	@Override
	public ProjectionCustHierarchy toUnescapedModel() {
		return new ProjectionCustHierarchyWrapper(_projectionCustHierarchy.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _projectionCustHierarchy.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ProjectionCustHierarchyWrapper)) {
			return false;
		}

		ProjectionCustHierarchyWrapper projectionCustHierarchyWrapper = (ProjectionCustHierarchyWrapper)obj;

		if (Objects.equals(_projectionCustHierarchy,
					projectionCustHierarchyWrapper._projectionCustHierarchy)) {
			return true;
		}

		return false;
	}

	@Override
	public ProjectionCustHierarchy getWrappedModel() {
		return _projectionCustHierarchy;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _projectionCustHierarchy.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _projectionCustHierarchy.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_projectionCustHierarchy.resetOriginalValues();
	}

	private final ProjectionCustHierarchy _projectionCustHierarchy;
}