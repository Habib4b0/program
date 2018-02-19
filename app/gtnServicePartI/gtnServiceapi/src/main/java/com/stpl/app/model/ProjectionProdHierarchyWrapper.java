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
 * This class is a wrapper for {@link ProjectionProdHierarchy}.
 * </p>
 *
 * @author
 * @see ProjectionProdHierarchy
 * @generated
 */
@ProviderType
public class ProjectionProdHierarchyWrapper implements ProjectionProdHierarchy,
	ModelWrapper<ProjectionProdHierarchy> {
	public ProjectionProdHierarchyWrapper(
		ProjectionProdHierarchy projectionProdHierarchy) {
		_projectionProdHierarchy = projectionProdHierarchy;
	}

	@Override
	public Class<?> getModelClass() {
		return ProjectionProdHierarchy.class;
	}

	@Override
	public String getModelClassName() {
		return ProjectionProdHierarchy.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("projectionMasterSid", getProjectionMasterSid());
		attributes.put("projectionProdHierarchySid",
			getProjectionProdHierarchySid());
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

		Integer projectionProdHierarchySid = (Integer)attributes.get(
				"projectionProdHierarchySid");

		if (projectionProdHierarchySid != null) {
			setProjectionProdHierarchySid(projectionProdHierarchySid);
		}

		Integer relationshipLevelSid = (Integer)attributes.get(
				"relationshipLevelSid");

		if (relationshipLevelSid != null) {
			setRelationshipLevelSid(relationshipLevelSid);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new ProjectionProdHierarchyWrapper((ProjectionProdHierarchy)_projectionProdHierarchy.clone());
	}

	@Override
	public int compareTo(ProjectionProdHierarchy projectionProdHierarchy) {
		return _projectionProdHierarchy.compareTo(projectionProdHierarchy);
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _projectionProdHierarchy.getExpandoBridge();
	}

	/**
	* Returns the primary key of this projection prod hierarchy.
	*
	* @return the primary key of this projection prod hierarchy
	*/
	@Override
	public int getPrimaryKey() {
		return _projectionProdHierarchy.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _projectionProdHierarchy.getPrimaryKeyObj();
	}

	/**
	* Returns the projection master sid of this projection prod hierarchy.
	*
	* @return the projection master sid of this projection prod hierarchy
	*/
	@Override
	public int getProjectionMasterSid() {
		return _projectionProdHierarchy.getProjectionMasterSid();
	}

	/**
	* Returns the projection prod hierarchy sid of this projection prod hierarchy.
	*
	* @return the projection prod hierarchy sid of this projection prod hierarchy
	*/
	@Override
	public int getProjectionProdHierarchySid() {
		return _projectionProdHierarchy.getProjectionProdHierarchySid();
	}

	/**
	* Returns the relationship level sid of this projection prod hierarchy.
	*
	* @return the relationship level sid of this projection prod hierarchy
	*/
	@Override
	public int getRelationshipLevelSid() {
		return _projectionProdHierarchy.getRelationshipLevelSid();
	}

	@Override
	public int hashCode() {
		return _projectionProdHierarchy.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _projectionProdHierarchy.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _projectionProdHierarchy.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _projectionProdHierarchy.isNew();
	}

	@Override
	public void persist() {
		_projectionProdHierarchy.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_projectionProdHierarchy.setCachedModel(cachedModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_projectionProdHierarchy.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_projectionProdHierarchy.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_projectionProdHierarchy.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public void setNew(boolean n) {
		_projectionProdHierarchy.setNew(n);
	}

	/**
	* Sets the primary key of this projection prod hierarchy.
	*
	* @param primaryKey the primary key of this projection prod hierarchy
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_projectionProdHierarchy.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_projectionProdHierarchy.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the projection master sid of this projection prod hierarchy.
	*
	* @param projectionMasterSid the projection master sid of this projection prod hierarchy
	*/
	@Override
	public void setProjectionMasterSid(int projectionMasterSid) {
		_projectionProdHierarchy.setProjectionMasterSid(projectionMasterSid);
	}

	/**
	* Sets the projection prod hierarchy sid of this projection prod hierarchy.
	*
	* @param projectionProdHierarchySid the projection prod hierarchy sid of this projection prod hierarchy
	*/
	@Override
	public void setProjectionProdHierarchySid(int projectionProdHierarchySid) {
		_projectionProdHierarchy.setProjectionProdHierarchySid(projectionProdHierarchySid);
	}

	/**
	* Sets the relationship level sid of this projection prod hierarchy.
	*
	* @param relationshipLevelSid the relationship level sid of this projection prod hierarchy
	*/
	@Override
	public void setRelationshipLevelSid(int relationshipLevelSid) {
		_projectionProdHierarchy.setRelationshipLevelSid(relationshipLevelSid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<ProjectionProdHierarchy> toCacheModel() {
		return _projectionProdHierarchy.toCacheModel();
	}

	@Override
	public ProjectionProdHierarchy toEscapedModel() {
		return new ProjectionProdHierarchyWrapper(_projectionProdHierarchy.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _projectionProdHierarchy.toString();
	}

	@Override
	public ProjectionProdHierarchy toUnescapedModel() {
		return new ProjectionProdHierarchyWrapper(_projectionProdHierarchy.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _projectionProdHierarchy.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ProjectionProdHierarchyWrapper)) {
			return false;
		}

		ProjectionProdHierarchyWrapper projectionProdHierarchyWrapper = (ProjectionProdHierarchyWrapper)obj;

		if (Objects.equals(_projectionProdHierarchy,
					projectionProdHierarchyWrapper._projectionProdHierarchy)) {
			return true;
		}

		return false;
	}

	@Override
	public ProjectionProdHierarchy getWrappedModel() {
		return _projectionProdHierarchy;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _projectionProdHierarchy.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _projectionProdHierarchy.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_projectionProdHierarchy.resetOriginalValues();
	}

	private final ProjectionProdHierarchy _projectionProdHierarchy;
}