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
 * This class is a wrapper for {@link CcpMap}.
 * </p>
 *
 * @author
 * @see CcpMap
 * @generated
 */
@ProviderType
public class CcpMapWrapper implements CcpMap, ModelWrapper<CcpMap> {
	public CcpMapWrapper(CcpMap ccpMap) {
		_ccpMap = ccpMap;
	}

	@Override
	public Class<?> getModelClass() {
		return CcpMap.class;
	}

	@Override
	public String getModelClassName() {
		return CcpMap.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("ccpDetailsSid", getCcpDetailsSid());
		attributes.put("relationshipLevelSid", getRelationshipLevelSid());
		attributes.put("ccpMapSid", getCcpMapSid());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Integer ccpDetailsSid = (Integer)attributes.get("ccpDetailsSid");

		if (ccpDetailsSid != null) {
			setCcpDetailsSid(ccpDetailsSid);
		}

		Integer relationshipLevelSid = (Integer)attributes.get(
				"relationshipLevelSid");

		if (relationshipLevelSid != null) {
			setRelationshipLevelSid(relationshipLevelSid);
		}

		Integer ccpMapSid = (Integer)attributes.get("ccpMapSid");

		if (ccpMapSid != null) {
			setCcpMapSid(ccpMapSid);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new CcpMapWrapper((CcpMap)_ccpMap.clone());
	}

	@Override
	public int compareTo(CcpMap ccpMap) {
		return _ccpMap.compareTo(ccpMap);
	}

	/**
	* Returns the ccp details sid of this ccp map.
	*
	* @return the ccp details sid of this ccp map
	*/
	@Override
	public int getCcpDetailsSid() {
		return _ccpMap.getCcpDetailsSid();
	}

	/**
	* Returns the ccp map sid of this ccp map.
	*
	* @return the ccp map sid of this ccp map
	*/
	@Override
	public int getCcpMapSid() {
		return _ccpMap.getCcpMapSid();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _ccpMap.getExpandoBridge();
	}

	/**
	* Returns the primary key of this ccp map.
	*
	* @return the primary key of this ccp map
	*/
	@Override
	public int getPrimaryKey() {
		return _ccpMap.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _ccpMap.getPrimaryKeyObj();
	}

	/**
	* Returns the relationship level sid of this ccp map.
	*
	* @return the relationship level sid of this ccp map
	*/
	@Override
	public int getRelationshipLevelSid() {
		return _ccpMap.getRelationshipLevelSid();
	}

	@Override
	public int hashCode() {
		return _ccpMap.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _ccpMap.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _ccpMap.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _ccpMap.isNew();
	}

	@Override
	public void persist() {
		_ccpMap.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_ccpMap.setCachedModel(cachedModel);
	}

	/**
	* Sets the ccp details sid of this ccp map.
	*
	* @param ccpDetailsSid the ccp details sid of this ccp map
	*/
	@Override
	public void setCcpDetailsSid(int ccpDetailsSid) {
		_ccpMap.setCcpDetailsSid(ccpDetailsSid);
	}

	/**
	* Sets the ccp map sid of this ccp map.
	*
	* @param ccpMapSid the ccp map sid of this ccp map
	*/
	@Override
	public void setCcpMapSid(int ccpMapSid) {
		_ccpMap.setCcpMapSid(ccpMapSid);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_ccpMap.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_ccpMap.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_ccpMap.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public void setNew(boolean n) {
		_ccpMap.setNew(n);
	}

	/**
	* Sets the primary key of this ccp map.
	*
	* @param primaryKey the primary key of this ccp map
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_ccpMap.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_ccpMap.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the relationship level sid of this ccp map.
	*
	* @param relationshipLevelSid the relationship level sid of this ccp map
	*/
	@Override
	public void setRelationshipLevelSid(int relationshipLevelSid) {
		_ccpMap.setRelationshipLevelSid(relationshipLevelSid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<CcpMap> toCacheModel() {
		return _ccpMap.toCacheModel();
	}

	@Override
	public CcpMap toEscapedModel() {
		return new CcpMapWrapper(_ccpMap.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _ccpMap.toString();
	}

	@Override
	public CcpMap toUnescapedModel() {
		return new CcpMapWrapper(_ccpMap.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _ccpMap.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CcpMapWrapper)) {
			return false;
		}

		CcpMapWrapper ccpMapWrapper = (CcpMapWrapper)obj;

		if (Objects.equals(_ccpMap, ccpMapWrapper._ccpMap)) {
			return true;
		}

		return false;
	}

	@Override
	public CcpMap getWrappedModel() {
		return _ccpMap;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _ccpMap.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _ccpMap.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_ccpMap.resetOriginalValues();
	}

	private final CcpMap _ccpMap;
}