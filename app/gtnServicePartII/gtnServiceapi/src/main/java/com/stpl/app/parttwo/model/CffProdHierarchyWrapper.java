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
 * This class is a wrapper for {@link CffProdHierarchy}.
 * </p>
 *
 * @author
 * @see CffProdHierarchy
 * @generated
 */
@ProviderType
public class CffProdHierarchyWrapper implements CffProdHierarchy,
	ModelWrapper<CffProdHierarchy> {
	public CffProdHierarchyWrapper(CffProdHierarchy cffProdHierarchy) {
		_cffProdHierarchy = cffProdHierarchy;
	}

	@Override
	public Class<?> getModelClass() {
		return CffProdHierarchy.class;
	}

	@Override
	public String getModelClassName() {
		return CffProdHierarchy.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("cffMasterSid", getCffMasterSid());
		attributes.put("relationshipLevelSid", getRelationshipLevelSid());
		attributes.put("cffProdHierarchySid", getCffProdHierarchySid());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Integer cffMasterSid = (Integer)attributes.get("cffMasterSid");

		if (cffMasterSid != null) {
			setCffMasterSid(cffMasterSid);
		}

		Integer relationshipLevelSid = (Integer)attributes.get(
				"relationshipLevelSid");

		if (relationshipLevelSid != null) {
			setRelationshipLevelSid(relationshipLevelSid);
		}

		Integer cffProdHierarchySid = (Integer)attributes.get(
				"cffProdHierarchySid");

		if (cffProdHierarchySid != null) {
			setCffProdHierarchySid(cffProdHierarchySid);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new CffProdHierarchyWrapper((CffProdHierarchy)_cffProdHierarchy.clone());
	}

	@Override
	public int compareTo(CffProdHierarchy cffProdHierarchy) {
		return _cffProdHierarchy.compareTo(cffProdHierarchy);
	}

	/**
	* Returns the cff master sid of this cff prod hierarchy.
	*
	* @return the cff master sid of this cff prod hierarchy
	*/
	@Override
	public int getCffMasterSid() {
		return _cffProdHierarchy.getCffMasterSid();
	}

	/**
	* Returns the cff prod hierarchy sid of this cff prod hierarchy.
	*
	* @return the cff prod hierarchy sid of this cff prod hierarchy
	*/
	@Override
	public int getCffProdHierarchySid() {
		return _cffProdHierarchy.getCffProdHierarchySid();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _cffProdHierarchy.getExpandoBridge();
	}

	/**
	* Returns the primary key of this cff prod hierarchy.
	*
	* @return the primary key of this cff prod hierarchy
	*/
	@Override
	public int getPrimaryKey() {
		return _cffProdHierarchy.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _cffProdHierarchy.getPrimaryKeyObj();
	}

	/**
	* Returns the relationship level sid of this cff prod hierarchy.
	*
	* @return the relationship level sid of this cff prod hierarchy
	*/
	@Override
	public int getRelationshipLevelSid() {
		return _cffProdHierarchy.getRelationshipLevelSid();
	}

	@Override
	public int hashCode() {
		return _cffProdHierarchy.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _cffProdHierarchy.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _cffProdHierarchy.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _cffProdHierarchy.isNew();
	}

	@Override
	public void persist() {
		_cffProdHierarchy.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_cffProdHierarchy.setCachedModel(cachedModel);
	}

	/**
	* Sets the cff master sid of this cff prod hierarchy.
	*
	* @param cffMasterSid the cff master sid of this cff prod hierarchy
	*/
	@Override
	public void setCffMasterSid(int cffMasterSid) {
		_cffProdHierarchy.setCffMasterSid(cffMasterSid);
	}

	/**
	* Sets the cff prod hierarchy sid of this cff prod hierarchy.
	*
	* @param cffProdHierarchySid the cff prod hierarchy sid of this cff prod hierarchy
	*/
	@Override
	public void setCffProdHierarchySid(int cffProdHierarchySid) {
		_cffProdHierarchy.setCffProdHierarchySid(cffProdHierarchySid);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_cffProdHierarchy.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_cffProdHierarchy.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_cffProdHierarchy.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public void setNew(boolean n) {
		_cffProdHierarchy.setNew(n);
	}

	/**
	* Sets the primary key of this cff prod hierarchy.
	*
	* @param primaryKey the primary key of this cff prod hierarchy
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_cffProdHierarchy.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_cffProdHierarchy.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the relationship level sid of this cff prod hierarchy.
	*
	* @param relationshipLevelSid the relationship level sid of this cff prod hierarchy
	*/
	@Override
	public void setRelationshipLevelSid(int relationshipLevelSid) {
		_cffProdHierarchy.setRelationshipLevelSid(relationshipLevelSid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<CffProdHierarchy> toCacheModel() {
		return _cffProdHierarchy.toCacheModel();
	}

	@Override
	public CffProdHierarchy toEscapedModel() {
		return new CffProdHierarchyWrapper(_cffProdHierarchy.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _cffProdHierarchy.toString();
	}

	@Override
	public CffProdHierarchy toUnescapedModel() {
		return new CffProdHierarchyWrapper(_cffProdHierarchy.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _cffProdHierarchy.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CffProdHierarchyWrapper)) {
			return false;
		}

		CffProdHierarchyWrapper cffProdHierarchyWrapper = (CffProdHierarchyWrapper)obj;

		if (Objects.equals(_cffProdHierarchy,
					cffProdHierarchyWrapper._cffProdHierarchy)) {
			return true;
		}

		return false;
	}

	@Override
	public CffProdHierarchy getWrappedModel() {
		return _cffProdHierarchy;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _cffProdHierarchy.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _cffProdHierarchy.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_cffProdHierarchy.resetOriginalValues();
	}

	private final CffProdHierarchy _cffProdHierarchy;
}