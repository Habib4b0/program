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
 * This class is a wrapper for {@link ProjectionDetails}.
 * </p>
 *
 * @author
 * @see ProjectionDetails
 * @generated
 */
@ProviderType
public class ProjectionDetailsWrapper implements ProjectionDetails,
	ModelWrapper<ProjectionDetails> {
	public ProjectionDetailsWrapper(ProjectionDetails projectionDetails) {
		_projectionDetails = projectionDetails;
	}

	@Override
	public Class<?> getModelClass() {
		return ProjectionDetails.class;
	}

	@Override
	public String getModelClassName() {
		return ProjectionDetails.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("projectionDetailsSid", getProjectionDetailsSid());
		attributes.put("ccpDetailsSid", getCcpDetailsSid());
		attributes.put("projectionMasterSid", getProjectionMasterSid());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Integer projectionDetailsSid = (Integer)attributes.get(
				"projectionDetailsSid");

		if (projectionDetailsSid != null) {
			setProjectionDetailsSid(projectionDetailsSid);
		}

		Integer ccpDetailsSid = (Integer)attributes.get("ccpDetailsSid");

		if (ccpDetailsSid != null) {
			setCcpDetailsSid(ccpDetailsSid);
		}

		Integer projectionMasterSid = (Integer)attributes.get(
				"projectionMasterSid");

		if (projectionMasterSid != null) {
			setProjectionMasterSid(projectionMasterSid);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new ProjectionDetailsWrapper((ProjectionDetails)_projectionDetails.clone());
	}

	@Override
	public int compareTo(ProjectionDetails projectionDetails) {
		return _projectionDetails.compareTo(projectionDetails);
	}

	/**
	* Returns the ccp details sid of this projection details.
	*
	* @return the ccp details sid of this projection details
	*/
	@Override
	public int getCcpDetailsSid() {
		return _projectionDetails.getCcpDetailsSid();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _projectionDetails.getExpandoBridge();
	}

	/**
	* Returns the primary key of this projection details.
	*
	* @return the primary key of this projection details
	*/
	@Override
	public int getPrimaryKey() {
		return _projectionDetails.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _projectionDetails.getPrimaryKeyObj();
	}

	/**
	* Returns the projection details sid of this projection details.
	*
	* @return the projection details sid of this projection details
	*/
	@Override
	public int getProjectionDetailsSid() {
		return _projectionDetails.getProjectionDetailsSid();
	}

	/**
	* Returns the projection master sid of this projection details.
	*
	* @return the projection master sid of this projection details
	*/
	@Override
	public int getProjectionMasterSid() {
		return _projectionDetails.getProjectionMasterSid();
	}

	@Override
	public int hashCode() {
		return _projectionDetails.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _projectionDetails.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _projectionDetails.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _projectionDetails.isNew();
	}

	@Override
	public void persist() {
		_projectionDetails.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_projectionDetails.setCachedModel(cachedModel);
	}

	/**
	* Sets the ccp details sid of this projection details.
	*
	* @param ccpDetailsSid the ccp details sid of this projection details
	*/
	@Override
	public void setCcpDetailsSid(int ccpDetailsSid) {
		_projectionDetails.setCcpDetailsSid(ccpDetailsSid);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_projectionDetails.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_projectionDetails.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_projectionDetails.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public void setNew(boolean n) {
		_projectionDetails.setNew(n);
	}

	/**
	* Sets the primary key of this projection details.
	*
	* @param primaryKey the primary key of this projection details
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_projectionDetails.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_projectionDetails.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the projection details sid of this projection details.
	*
	* @param projectionDetailsSid the projection details sid of this projection details
	*/
	@Override
	public void setProjectionDetailsSid(int projectionDetailsSid) {
		_projectionDetails.setProjectionDetailsSid(projectionDetailsSid);
	}

	/**
	* Sets the projection master sid of this projection details.
	*
	* @param projectionMasterSid the projection master sid of this projection details
	*/
	@Override
	public void setProjectionMasterSid(int projectionMasterSid) {
		_projectionDetails.setProjectionMasterSid(projectionMasterSid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<ProjectionDetails> toCacheModel() {
		return _projectionDetails.toCacheModel();
	}

	@Override
	public ProjectionDetails toEscapedModel() {
		return new ProjectionDetailsWrapper(_projectionDetails.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _projectionDetails.toString();
	}

	@Override
	public ProjectionDetails toUnescapedModel() {
		return new ProjectionDetailsWrapper(_projectionDetails.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _projectionDetails.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ProjectionDetailsWrapper)) {
			return false;
		}

		ProjectionDetailsWrapper projectionDetailsWrapper = (ProjectionDetailsWrapper)obj;

		if (Objects.equals(_projectionDetails,
					projectionDetailsWrapper._projectionDetails)) {
			return true;
		}

		return false;
	}

	@Override
	public ProjectionDetails getWrappedModel() {
		return _projectionDetails;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _projectionDetails.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _projectionDetails.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_projectionDetails.resetOriginalValues();
	}

	private final ProjectionDetails _projectionDetails;
}