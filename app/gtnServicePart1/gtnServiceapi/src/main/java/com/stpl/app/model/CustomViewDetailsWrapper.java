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
 * This class is a wrapper for {@link CustomViewDetails}.
 * </p>
 *
 * @author
 * @see CustomViewDetails
 * @generated
 */
@ProviderType
public class CustomViewDetailsWrapper implements CustomViewDetails,
	ModelWrapper<CustomViewDetails> {
	public CustomViewDetailsWrapper(CustomViewDetails customViewDetails) {
		_customViewDetails = customViewDetails;
	}

	@Override
	public Class<?> getModelClass() {
		return CustomViewDetails.class;
	}

	@Override
	public String getModelClassName() {
		return CustomViewDetails.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("hierarchyId", getHierarchyId());
		attributes.put("hierarchyIndicator", getHierarchyIndicator());
		attributes.put("customViewMasterSid", getCustomViewMasterSid());
		attributes.put("customViewDetailsSid", getCustomViewDetailsSid());
		attributes.put("levelNo", getLevelNo());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Integer hierarchyId = (Integer)attributes.get("hierarchyId");

		if (hierarchyId != null) {
			setHierarchyId(hierarchyId);
		}

		String hierarchyIndicator = (String)attributes.get("hierarchyIndicator");

		if (hierarchyIndicator != null) {
			setHierarchyIndicator(hierarchyIndicator);
		}

		Integer customViewMasterSid = (Integer)attributes.get(
				"customViewMasterSid");

		if (customViewMasterSid != null) {
			setCustomViewMasterSid(customViewMasterSid);
		}

		Integer customViewDetailsSid = (Integer)attributes.get(
				"customViewDetailsSid");

		if (customViewDetailsSid != null) {
			setCustomViewDetailsSid(customViewDetailsSid);
		}

		Integer levelNo = (Integer)attributes.get("levelNo");

		if (levelNo != null) {
			setLevelNo(levelNo);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new CustomViewDetailsWrapper((CustomViewDetails)_customViewDetails.clone());
	}

	@Override
	public int compareTo(CustomViewDetails customViewDetails) {
		return _customViewDetails.compareTo(customViewDetails);
	}

	/**
	* Returns the custom view details sid of this custom view details.
	*
	* @return the custom view details sid of this custom view details
	*/
	@Override
	public int getCustomViewDetailsSid() {
		return _customViewDetails.getCustomViewDetailsSid();
	}

	/**
	* Returns the custom view master sid of this custom view details.
	*
	* @return the custom view master sid of this custom view details
	*/
	@Override
	public int getCustomViewMasterSid() {
		return _customViewDetails.getCustomViewMasterSid();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _customViewDetails.getExpandoBridge();
	}

	/**
	* Returns the hierarchy ID of this custom view details.
	*
	* @return the hierarchy ID of this custom view details
	*/
	@Override
	public int getHierarchyId() {
		return _customViewDetails.getHierarchyId();
	}

	/**
	* Returns the hierarchy indicator of this custom view details.
	*
	* @return the hierarchy indicator of this custom view details
	*/
	@Override
	public java.lang.String getHierarchyIndicator() {
		return _customViewDetails.getHierarchyIndicator();
	}

	/**
	* Returns the level no of this custom view details.
	*
	* @return the level no of this custom view details
	*/
	@Override
	public int getLevelNo() {
		return _customViewDetails.getLevelNo();
	}

	/**
	* Returns the primary key of this custom view details.
	*
	* @return the primary key of this custom view details
	*/
	@Override
	public int getPrimaryKey() {
		return _customViewDetails.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _customViewDetails.getPrimaryKeyObj();
	}

	@Override
	public int hashCode() {
		return _customViewDetails.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _customViewDetails.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _customViewDetails.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _customViewDetails.isNew();
	}

	@Override
	public void persist() {
		_customViewDetails.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_customViewDetails.setCachedModel(cachedModel);
	}

	/**
	* Sets the custom view details sid of this custom view details.
	*
	* @param customViewDetailsSid the custom view details sid of this custom view details
	*/
	@Override
	public void setCustomViewDetailsSid(int customViewDetailsSid) {
		_customViewDetails.setCustomViewDetailsSid(customViewDetailsSid);
	}

	/**
	* Sets the custom view master sid of this custom view details.
	*
	* @param customViewMasterSid the custom view master sid of this custom view details
	*/
	@Override
	public void setCustomViewMasterSid(int customViewMasterSid) {
		_customViewDetails.setCustomViewMasterSid(customViewMasterSid);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_customViewDetails.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_customViewDetails.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_customViewDetails.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the hierarchy ID of this custom view details.
	*
	* @param hierarchyId the hierarchy ID of this custom view details
	*/
	@Override
	public void setHierarchyId(int hierarchyId) {
		_customViewDetails.setHierarchyId(hierarchyId);
	}

	/**
	* Sets the hierarchy indicator of this custom view details.
	*
	* @param hierarchyIndicator the hierarchy indicator of this custom view details
	*/
	@Override
	public void setHierarchyIndicator(java.lang.String hierarchyIndicator) {
		_customViewDetails.setHierarchyIndicator(hierarchyIndicator);
	}

	/**
	* Sets the level no of this custom view details.
	*
	* @param levelNo the level no of this custom view details
	*/
	@Override
	public void setLevelNo(int levelNo) {
		_customViewDetails.setLevelNo(levelNo);
	}

	@Override
	public void setNew(boolean n) {
		_customViewDetails.setNew(n);
	}

	/**
	* Sets the primary key of this custom view details.
	*
	* @param primaryKey the primary key of this custom view details
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_customViewDetails.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_customViewDetails.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<CustomViewDetails> toCacheModel() {
		return _customViewDetails.toCacheModel();
	}

	@Override
	public CustomViewDetails toEscapedModel() {
		return new CustomViewDetailsWrapper(_customViewDetails.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _customViewDetails.toString();
	}

	@Override
	public CustomViewDetails toUnescapedModel() {
		return new CustomViewDetailsWrapper(_customViewDetails.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _customViewDetails.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CustomViewDetailsWrapper)) {
			return false;
		}

		CustomViewDetailsWrapper customViewDetailsWrapper = (CustomViewDetailsWrapper)obj;

		if (Objects.equals(_customViewDetails,
					customViewDetailsWrapper._customViewDetails)) {
			return true;
		}

		return false;
	}

	@Override
	public CustomViewDetails getWrappedModel() {
		return _customViewDetails;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _customViewDetails.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _customViewDetails.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_customViewDetails.resetOriginalValues();
	}

	private final CustomViewDetails _customViewDetails;
}