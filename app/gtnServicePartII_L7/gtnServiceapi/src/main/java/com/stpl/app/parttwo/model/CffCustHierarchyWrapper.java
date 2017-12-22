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
 * This class is a wrapper for {@link CffCustHierarchy}.
 * </p>
 *
 * @author
 * @see CffCustHierarchy
 * @generated
 */
@ProviderType
public class CffCustHierarchyWrapper implements CffCustHierarchy,
	ModelWrapper<CffCustHierarchy> {
	public CffCustHierarchyWrapper(CffCustHierarchy cffCustHierarchy) {
		_cffCustHierarchy = cffCustHierarchy;
	}

	@Override
	public Class<?> getModelClass() {
		return CffCustHierarchy.class;
	}

	@Override
	public String getModelClassName() {
		return CffCustHierarchy.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("cffCustHierarchySid", getCffCustHierarchySid());
		attributes.put("cffMasterSid", getCffMasterSid());
		attributes.put("relationshipLevelSid", getRelationshipLevelSid());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Integer cffCustHierarchySid = (Integer)attributes.get(
				"cffCustHierarchySid");

		if (cffCustHierarchySid != null) {
			setCffCustHierarchySid(cffCustHierarchySid);
		}

		Integer cffMasterSid = (Integer)attributes.get("cffMasterSid");

		if (cffMasterSid != null) {
			setCffMasterSid(cffMasterSid);
		}

		Integer relationshipLevelSid = (Integer)attributes.get(
				"relationshipLevelSid");

		if (relationshipLevelSid != null) {
			setRelationshipLevelSid(relationshipLevelSid);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new CffCustHierarchyWrapper((CffCustHierarchy)_cffCustHierarchy.clone());
	}

	@Override
	public int compareTo(CffCustHierarchy cffCustHierarchy) {
		return _cffCustHierarchy.compareTo(cffCustHierarchy);
	}

	/**
	* Returns the cff cust hierarchy sid of this cff cust hierarchy.
	*
	* @return the cff cust hierarchy sid of this cff cust hierarchy
	*/
	@Override
	public int getCffCustHierarchySid() {
		return _cffCustHierarchy.getCffCustHierarchySid();
	}

	/**
	* Returns the cff master sid of this cff cust hierarchy.
	*
	* @return the cff master sid of this cff cust hierarchy
	*/
	@Override
	public int getCffMasterSid() {
		return _cffCustHierarchy.getCffMasterSid();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _cffCustHierarchy.getExpandoBridge();
	}

	/**
	* Returns the primary key of this cff cust hierarchy.
	*
	* @return the primary key of this cff cust hierarchy
	*/
	@Override
	public int getPrimaryKey() {
		return _cffCustHierarchy.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _cffCustHierarchy.getPrimaryKeyObj();
	}

	/**
	* Returns the relationship level sid of this cff cust hierarchy.
	*
	* @return the relationship level sid of this cff cust hierarchy
	*/
	@Override
	public int getRelationshipLevelSid() {
		return _cffCustHierarchy.getRelationshipLevelSid();
	}

	@Override
	public int hashCode() {
		return _cffCustHierarchy.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _cffCustHierarchy.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _cffCustHierarchy.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _cffCustHierarchy.isNew();
	}

	@Override
	public void persist() {
		_cffCustHierarchy.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_cffCustHierarchy.setCachedModel(cachedModel);
	}

	/**
	* Sets the cff cust hierarchy sid of this cff cust hierarchy.
	*
	* @param cffCustHierarchySid the cff cust hierarchy sid of this cff cust hierarchy
	*/
	@Override
	public void setCffCustHierarchySid(int cffCustHierarchySid) {
		_cffCustHierarchy.setCffCustHierarchySid(cffCustHierarchySid);
	}

	/**
	* Sets the cff master sid of this cff cust hierarchy.
	*
	* @param cffMasterSid the cff master sid of this cff cust hierarchy
	*/
	@Override
	public void setCffMasterSid(int cffMasterSid) {
		_cffCustHierarchy.setCffMasterSid(cffMasterSid);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_cffCustHierarchy.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_cffCustHierarchy.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_cffCustHierarchy.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public void setNew(boolean n) {
		_cffCustHierarchy.setNew(n);
	}

	/**
	* Sets the primary key of this cff cust hierarchy.
	*
	* @param primaryKey the primary key of this cff cust hierarchy
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_cffCustHierarchy.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_cffCustHierarchy.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the relationship level sid of this cff cust hierarchy.
	*
	* @param relationshipLevelSid the relationship level sid of this cff cust hierarchy
	*/
	@Override
	public void setRelationshipLevelSid(int relationshipLevelSid) {
		_cffCustHierarchy.setRelationshipLevelSid(relationshipLevelSid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<CffCustHierarchy> toCacheModel() {
		return _cffCustHierarchy.toCacheModel();
	}

	@Override
	public CffCustHierarchy toEscapedModel() {
		return new CffCustHierarchyWrapper(_cffCustHierarchy.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _cffCustHierarchy.toString();
	}

	@Override
	public CffCustHierarchy toUnescapedModel() {
		return new CffCustHierarchyWrapper(_cffCustHierarchy.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _cffCustHierarchy.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CffCustHierarchyWrapper)) {
			return false;
		}

		CffCustHierarchyWrapper cffCustHierarchyWrapper = (CffCustHierarchyWrapper)obj;

		if (Objects.equals(_cffCustHierarchy,
					cffCustHierarchyWrapper._cffCustHierarchy)) {
			return true;
		}

		return false;
	}

	@Override
	public CffCustHierarchy getWrappedModel() {
		return _cffCustHierarchy;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _cffCustHierarchy.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _cffCustHierarchy.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_cffCustHierarchy.resetOriginalValues();
	}

	private final CffCustHierarchy _cffCustHierarchy;
}