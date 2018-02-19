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

package com.stpl.app.parttwo.model;

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
 * This class is a wrapper for {@link CffCustomViewDetails}.
 * </p>
 *
 * @author
 * @see CffCustomViewDetails
 * @generated
 */
@ProviderType
public class CffCustomViewDetailsWrapper implements CffCustomViewDetails,
	ModelWrapper<CffCustomViewDetails> {
	public CffCustomViewDetailsWrapper(
		CffCustomViewDetails cffCustomViewDetails) {
		_cffCustomViewDetails = cffCustomViewDetails;
	}

	@Override
	public Class<?> getModelClass() {
		return CffCustomViewDetails.class;
	}

	@Override
	public String getModelClassName() {
		return CffCustomViewDetails.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("hierarchyId", getHierarchyId());
		attributes.put("hierarchyIndicator", getHierarchyIndicator());
		attributes.put("cffCustomViewDetailsSid", getCffCustomViewDetailsSid());
		attributes.put("levelNo", getLevelNo());
		attributes.put("cffCustomViewMasterSid", getCffCustomViewMasterSid());

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

		Integer cffCustomViewDetailsSid = (Integer)attributes.get(
				"cffCustomViewDetailsSid");

		if (cffCustomViewDetailsSid != null) {
			setCffCustomViewDetailsSid(cffCustomViewDetailsSid);
		}

		Integer levelNo = (Integer)attributes.get("levelNo");

		if (levelNo != null) {
			setLevelNo(levelNo);
		}

		Integer cffCustomViewMasterSid = (Integer)attributes.get(
				"cffCustomViewMasterSid");

		if (cffCustomViewMasterSid != null) {
			setCffCustomViewMasterSid(cffCustomViewMasterSid);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new CffCustomViewDetailsWrapper((CffCustomViewDetails)_cffCustomViewDetails.clone());
	}

	@Override
	public int compareTo(CffCustomViewDetails cffCustomViewDetails) {
		return _cffCustomViewDetails.compareTo(cffCustomViewDetails);
	}

	/**
	* Returns the cff custom view details sid of this cff custom view details.
	*
	* @return the cff custom view details sid of this cff custom view details
	*/
	@Override
	public int getCffCustomViewDetailsSid() {
		return _cffCustomViewDetails.getCffCustomViewDetailsSid();
	}

	/**
	* Returns the cff custom view master sid of this cff custom view details.
	*
	* @return the cff custom view master sid of this cff custom view details
	*/
	@Override
	public int getCffCustomViewMasterSid() {
		return _cffCustomViewDetails.getCffCustomViewMasterSid();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _cffCustomViewDetails.getExpandoBridge();
	}

	/**
	* Returns the hierarchy ID of this cff custom view details.
	*
	* @return the hierarchy ID of this cff custom view details
	*/
	@Override
	public int getHierarchyId() {
		return _cffCustomViewDetails.getHierarchyId();
	}

	/**
	* Returns the hierarchy indicator of this cff custom view details.
	*
	* @return the hierarchy indicator of this cff custom view details
	*/
	@Override
	public java.lang.String getHierarchyIndicator() {
		return _cffCustomViewDetails.getHierarchyIndicator();
	}

	/**
	* Returns the level no of this cff custom view details.
	*
	* @return the level no of this cff custom view details
	*/
	@Override
	public int getLevelNo() {
		return _cffCustomViewDetails.getLevelNo();
	}

	/**
	* Returns the primary key of this cff custom view details.
	*
	* @return the primary key of this cff custom view details
	*/
	@Override
	public int getPrimaryKey() {
		return _cffCustomViewDetails.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _cffCustomViewDetails.getPrimaryKeyObj();
	}

	@Override
	public int hashCode() {
		return _cffCustomViewDetails.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _cffCustomViewDetails.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _cffCustomViewDetails.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _cffCustomViewDetails.isNew();
	}

	@Override
	public void persist() {
		_cffCustomViewDetails.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_cffCustomViewDetails.setCachedModel(cachedModel);
	}

	/**
	* Sets the cff custom view details sid of this cff custom view details.
	*
	* @param cffCustomViewDetailsSid the cff custom view details sid of this cff custom view details
	*/
	@Override
	public void setCffCustomViewDetailsSid(int cffCustomViewDetailsSid) {
		_cffCustomViewDetails.setCffCustomViewDetailsSid(cffCustomViewDetailsSid);
	}

	/**
	* Sets the cff custom view master sid of this cff custom view details.
	*
	* @param cffCustomViewMasterSid the cff custom view master sid of this cff custom view details
	*/
	@Override
	public void setCffCustomViewMasterSid(int cffCustomViewMasterSid) {
		_cffCustomViewDetails.setCffCustomViewMasterSid(cffCustomViewMasterSid);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_cffCustomViewDetails.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_cffCustomViewDetails.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_cffCustomViewDetails.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the hierarchy ID of this cff custom view details.
	*
	* @param hierarchyId the hierarchy ID of this cff custom view details
	*/
	@Override
	public void setHierarchyId(int hierarchyId) {
		_cffCustomViewDetails.setHierarchyId(hierarchyId);
	}

	/**
	* Sets the hierarchy indicator of this cff custom view details.
	*
	* @param hierarchyIndicator the hierarchy indicator of this cff custom view details
	*/
	@Override
	public void setHierarchyIndicator(java.lang.String hierarchyIndicator) {
		_cffCustomViewDetails.setHierarchyIndicator(hierarchyIndicator);
	}

	/**
	* Sets the level no of this cff custom view details.
	*
	* @param levelNo the level no of this cff custom view details
	*/
	@Override
	public void setLevelNo(int levelNo) {
		_cffCustomViewDetails.setLevelNo(levelNo);
	}

	@Override
	public void setNew(boolean n) {
		_cffCustomViewDetails.setNew(n);
	}

	/**
	* Sets the primary key of this cff custom view details.
	*
	* @param primaryKey the primary key of this cff custom view details
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_cffCustomViewDetails.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_cffCustomViewDetails.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<CffCustomViewDetails> toCacheModel() {
		return _cffCustomViewDetails.toCacheModel();
	}

	@Override
	public CffCustomViewDetails toEscapedModel() {
		return new CffCustomViewDetailsWrapper(_cffCustomViewDetails.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _cffCustomViewDetails.toString();
	}

	@Override
	public CffCustomViewDetails toUnescapedModel() {
		return new CffCustomViewDetailsWrapper(_cffCustomViewDetails.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _cffCustomViewDetails.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CffCustomViewDetailsWrapper)) {
			return false;
		}

		CffCustomViewDetailsWrapper cffCustomViewDetailsWrapper = (CffCustomViewDetailsWrapper)obj;

		if (Objects.equals(_cffCustomViewDetails,
					cffCustomViewDetailsWrapper._cffCustomViewDetails)) {
			return true;
		}

		return false;
	}

	@Override
	public CffCustomViewDetails getWrappedModel() {
		return _cffCustomViewDetails;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _cffCustomViewDetails.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _cffCustomViewDetails.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_cffCustomViewDetails.resetOriginalValues();
	}

	private final CffCustomViewDetails _cffCustomViewDetails;
}