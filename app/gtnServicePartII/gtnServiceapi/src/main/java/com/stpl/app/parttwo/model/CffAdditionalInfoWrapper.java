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

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link CffAdditionalInfo}.
 * </p>
 *
 * @author
 * @see CffAdditionalInfo
 * @generated
 */
@ProviderType
public class CffAdditionalInfoWrapper implements CffAdditionalInfo,
	ModelWrapper<CffAdditionalInfo> {
	public CffAdditionalInfoWrapper(CffAdditionalInfo cffAdditionalInfo) {
		_cffAdditionalInfo = cffAdditionalInfo;
	}

	@Override
	public Class<?> getModelClass() {
		return CffAdditionalInfo.class;
	}

	@Override
	public String getModelClassName() {
		return CffAdditionalInfo.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("createdDate", getCreatedDate());
		attributes.put("createdBy", getCreatedBy());
		attributes.put("cffMasterSid", getCffMasterSid());
		attributes.put("cffAdditionalInfoSid", getCffAdditionalInfoSid());
		attributes.put("notes", getNotes());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Date createdDate = (Date)attributes.get("createdDate");

		if (createdDate != null) {
			setCreatedDate(createdDate);
		}

		Integer createdBy = (Integer)attributes.get("createdBy");

		if (createdBy != null) {
			setCreatedBy(createdBy);
		}

		Integer cffMasterSid = (Integer)attributes.get("cffMasterSid");

		if (cffMasterSid != null) {
			setCffMasterSid(cffMasterSid);
		}

		Integer cffAdditionalInfoSid = (Integer)attributes.get(
				"cffAdditionalInfoSid");

		if (cffAdditionalInfoSid != null) {
			setCffAdditionalInfoSid(cffAdditionalInfoSid);
		}

		String notes = (String)attributes.get("notes");

		if (notes != null) {
			setNotes(notes);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new CffAdditionalInfoWrapper((CffAdditionalInfo)_cffAdditionalInfo.clone());
	}

	@Override
	public int compareTo(CffAdditionalInfo cffAdditionalInfo) {
		return _cffAdditionalInfo.compareTo(cffAdditionalInfo);
	}

	/**
	* Returns the cff additional info sid of this cff additional info.
	*
	* @return the cff additional info sid of this cff additional info
	*/
	@Override
	public int getCffAdditionalInfoSid() {
		return _cffAdditionalInfo.getCffAdditionalInfoSid();
	}

	/**
	* Returns the cff master sid of this cff additional info.
	*
	* @return the cff master sid of this cff additional info
	*/
	@Override
	public int getCffMasterSid() {
		return _cffAdditionalInfo.getCffMasterSid();
	}

	/**
	* Returns the created by of this cff additional info.
	*
	* @return the created by of this cff additional info
	*/
	@Override
	public int getCreatedBy() {
		return _cffAdditionalInfo.getCreatedBy();
	}

	/**
	* Returns the created date of this cff additional info.
	*
	* @return the created date of this cff additional info
	*/
	@Override
	public Date getCreatedDate() {
		return _cffAdditionalInfo.getCreatedDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _cffAdditionalInfo.getExpandoBridge();
	}

	/**
	* Returns the notes of this cff additional info.
	*
	* @return the notes of this cff additional info
	*/
	@Override
	public java.lang.String getNotes() {
		return _cffAdditionalInfo.getNotes();
	}

	/**
	* Returns the primary key of this cff additional info.
	*
	* @return the primary key of this cff additional info
	*/
	@Override
	public int getPrimaryKey() {
		return _cffAdditionalInfo.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _cffAdditionalInfo.getPrimaryKeyObj();
	}

	@Override
	public int hashCode() {
		return _cffAdditionalInfo.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _cffAdditionalInfo.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _cffAdditionalInfo.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _cffAdditionalInfo.isNew();
	}

	@Override
	public void persist() {
		_cffAdditionalInfo.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_cffAdditionalInfo.setCachedModel(cachedModel);
	}

	/**
	* Sets the cff additional info sid of this cff additional info.
	*
	* @param cffAdditionalInfoSid the cff additional info sid of this cff additional info
	*/
	@Override
	public void setCffAdditionalInfoSid(int cffAdditionalInfoSid) {
		_cffAdditionalInfo.setCffAdditionalInfoSid(cffAdditionalInfoSid);
	}

	/**
	* Sets the cff master sid of this cff additional info.
	*
	* @param cffMasterSid the cff master sid of this cff additional info
	*/
	@Override
	public void setCffMasterSid(int cffMasterSid) {
		_cffAdditionalInfo.setCffMasterSid(cffMasterSid);
	}

	/**
	* Sets the created by of this cff additional info.
	*
	* @param createdBy the created by of this cff additional info
	*/
	@Override
	public void setCreatedBy(int createdBy) {
		_cffAdditionalInfo.setCreatedBy(createdBy);
	}

	/**
	* Sets the created date of this cff additional info.
	*
	* @param createdDate the created date of this cff additional info
	*/
	@Override
	public void setCreatedDate(Date createdDate) {
		_cffAdditionalInfo.setCreatedDate(createdDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_cffAdditionalInfo.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_cffAdditionalInfo.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_cffAdditionalInfo.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public void setNew(boolean n) {
		_cffAdditionalInfo.setNew(n);
	}

	/**
	* Sets the notes of this cff additional info.
	*
	* @param notes the notes of this cff additional info
	*/
	@Override
	public void setNotes(java.lang.String notes) {
		_cffAdditionalInfo.setNotes(notes);
	}

	/**
	* Sets the primary key of this cff additional info.
	*
	* @param primaryKey the primary key of this cff additional info
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_cffAdditionalInfo.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_cffAdditionalInfo.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<CffAdditionalInfo> toCacheModel() {
		return _cffAdditionalInfo.toCacheModel();
	}

	@Override
	public CffAdditionalInfo toEscapedModel() {
		return new CffAdditionalInfoWrapper(_cffAdditionalInfo.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _cffAdditionalInfo.toString();
	}

	@Override
	public CffAdditionalInfo toUnescapedModel() {
		return new CffAdditionalInfoWrapper(_cffAdditionalInfo.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _cffAdditionalInfo.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CffAdditionalInfoWrapper)) {
			return false;
		}

		CffAdditionalInfoWrapper cffAdditionalInfoWrapper = (CffAdditionalInfoWrapper)obj;

		if (Objects.equals(_cffAdditionalInfo,
					cffAdditionalInfoWrapper._cffAdditionalInfo)) {
			return true;
		}

		return false;
	}

	@Override
	public CffAdditionalInfo getWrappedModel() {
		return _cffAdditionalInfo;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _cffAdditionalInfo.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _cffAdditionalInfo.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_cffAdditionalInfo.resetOriginalValues();
	}

	private final CffAdditionalInfo _cffAdditionalInfo;
}