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
 * This class is a wrapper for {@link HistCompanyGroupDetails}.
 * </p>
 *
 * @author
 * @see HistCompanyGroupDetails
 * @generated
 */
@ProviderType
public class HistCompanyGroupDetailsWrapper implements HistCompanyGroupDetails,
	ModelWrapper<HistCompanyGroupDetails> {
	public HistCompanyGroupDetailsWrapper(
		HistCompanyGroupDetails histCompanyGroupDetails) {
		_histCompanyGroupDetails = histCompanyGroupDetails;
	}

	@Override
	public Class<?> getModelClass() {
		return HistCompanyGroupDetails.class;
	}

	@Override
	public String getModelClassName() {
		return HistCompanyGroupDetails.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("createdDate", getCreatedDate());
		attributes.put("createdBy", getCreatedBy());
		attributes.put("actionDate", getActionDate());
		attributes.put("companyParentDetailsSid", getCompanyParentDetailsSid());
		attributes.put("companyTradeclassSid", getCompanyTradeclassSid());
		attributes.put("actionFlag", getActionFlag());
		attributes.put("companyGroupSid", getCompanyGroupSid());
		attributes.put("versionNo", getVersionNo());
		attributes.put("companyGroupDetailsSid", getCompanyGroupDetailsSid());
		attributes.put("modifiedBy", getModifiedBy());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("companyMasterSid", getCompanyMasterSid());

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

		Date actionDate = (Date)attributes.get("actionDate");

		if (actionDate != null) {
			setActionDate(actionDate);
		}

		String companyParentDetailsSid = (String)attributes.get(
				"companyParentDetailsSid");

		if (companyParentDetailsSid != null) {
			setCompanyParentDetailsSid(companyParentDetailsSid);
		}

		Integer companyTradeclassSid = (Integer)attributes.get(
				"companyTradeclassSid");

		if (companyTradeclassSid != null) {
			setCompanyTradeclassSid(companyTradeclassSid);
		}

		String actionFlag = (String)attributes.get("actionFlag");

		if (actionFlag != null) {
			setActionFlag(actionFlag);
		}

		Integer companyGroupSid = (Integer)attributes.get("companyGroupSid");

		if (companyGroupSid != null) {
			setCompanyGroupSid(companyGroupSid);
		}

		Integer versionNo = (Integer)attributes.get("versionNo");

		if (versionNo != null) {
			setVersionNo(versionNo);
		}

		Integer companyGroupDetailsSid = (Integer)attributes.get(
				"companyGroupDetailsSid");

		if (companyGroupDetailsSid != null) {
			setCompanyGroupDetailsSid(companyGroupDetailsSid);
		}

		Integer modifiedBy = (Integer)attributes.get("modifiedBy");

		if (modifiedBy != null) {
			setModifiedBy(modifiedBy);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Integer companyMasterSid = (Integer)attributes.get("companyMasterSid");

		if (companyMasterSid != null) {
			setCompanyMasterSid(companyMasterSid);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new HistCompanyGroupDetailsWrapper((HistCompanyGroupDetails)_histCompanyGroupDetails.clone());
	}

	@Override
	public int compareTo(HistCompanyGroupDetails histCompanyGroupDetails) {
		return _histCompanyGroupDetails.compareTo(histCompanyGroupDetails);
	}

	/**
	* Returns the action date of this hist company group details.
	*
	* @return the action date of this hist company group details
	*/
	@Override
	public Date getActionDate() {
		return _histCompanyGroupDetails.getActionDate();
	}

	/**
	* Returns the action flag of this hist company group details.
	*
	* @return the action flag of this hist company group details
	*/
	@Override
	public java.lang.String getActionFlag() {
		return _histCompanyGroupDetails.getActionFlag();
	}

	/**
	* Returns the company group details sid of this hist company group details.
	*
	* @return the company group details sid of this hist company group details
	*/
	@Override
	public int getCompanyGroupDetailsSid() {
		return _histCompanyGroupDetails.getCompanyGroupDetailsSid();
	}

	/**
	* Returns the company group sid of this hist company group details.
	*
	* @return the company group sid of this hist company group details
	*/
	@Override
	public int getCompanyGroupSid() {
		return _histCompanyGroupDetails.getCompanyGroupSid();
	}

	/**
	* Returns the company master sid of this hist company group details.
	*
	* @return the company master sid of this hist company group details
	*/
	@Override
	public int getCompanyMasterSid() {
		return _histCompanyGroupDetails.getCompanyMasterSid();
	}

	/**
	* Returns the company parent details sid of this hist company group details.
	*
	* @return the company parent details sid of this hist company group details
	*/
	@Override
	public java.lang.String getCompanyParentDetailsSid() {
		return _histCompanyGroupDetails.getCompanyParentDetailsSid();
	}

	/**
	* Returns the company tradeclass sid of this hist company group details.
	*
	* @return the company tradeclass sid of this hist company group details
	*/
	@Override
	public int getCompanyTradeclassSid() {
		return _histCompanyGroupDetails.getCompanyTradeclassSid();
	}

	/**
	* Returns the created by of this hist company group details.
	*
	* @return the created by of this hist company group details
	*/
	@Override
	public int getCreatedBy() {
		return _histCompanyGroupDetails.getCreatedBy();
	}

	/**
	* Returns the created date of this hist company group details.
	*
	* @return the created date of this hist company group details
	*/
	@Override
	public Date getCreatedDate() {
		return _histCompanyGroupDetails.getCreatedDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _histCompanyGroupDetails.getExpandoBridge();
	}

	/**
	* Returns the modified by of this hist company group details.
	*
	* @return the modified by of this hist company group details
	*/
	@Override
	public int getModifiedBy() {
		return _histCompanyGroupDetails.getModifiedBy();
	}

	/**
	* Returns the modified date of this hist company group details.
	*
	* @return the modified date of this hist company group details
	*/
	@Override
	public Date getModifiedDate() {
		return _histCompanyGroupDetails.getModifiedDate();
	}

	/**
	* Returns the primary key of this hist company group details.
	*
	* @return the primary key of this hist company group details
	*/
	@Override
	public com.stpl.app.service.persistence.HistCompanyGroupDetailsPK getPrimaryKey() {
		return _histCompanyGroupDetails.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _histCompanyGroupDetails.getPrimaryKeyObj();
	}

	/**
	* Returns the version no of this hist company group details.
	*
	* @return the version no of this hist company group details
	*/
	@Override
	public int getVersionNo() {
		return _histCompanyGroupDetails.getVersionNo();
	}

	@Override
	public int hashCode() {
		return _histCompanyGroupDetails.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _histCompanyGroupDetails.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _histCompanyGroupDetails.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _histCompanyGroupDetails.isNew();
	}

	@Override
	public void persist() {
		_histCompanyGroupDetails.persist();
	}

	/**
	* Sets the action date of this hist company group details.
	*
	* @param actionDate the action date of this hist company group details
	*/
	@Override
	public void setActionDate(Date actionDate) {
		_histCompanyGroupDetails.setActionDate(actionDate);
	}

	/**
	* Sets the action flag of this hist company group details.
	*
	* @param actionFlag the action flag of this hist company group details
	*/
	@Override
	public void setActionFlag(java.lang.String actionFlag) {
		_histCompanyGroupDetails.setActionFlag(actionFlag);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_histCompanyGroupDetails.setCachedModel(cachedModel);
	}

	/**
	* Sets the company group details sid of this hist company group details.
	*
	* @param companyGroupDetailsSid the company group details sid of this hist company group details
	*/
	@Override
	public void setCompanyGroupDetailsSid(int companyGroupDetailsSid) {
		_histCompanyGroupDetails.setCompanyGroupDetailsSid(companyGroupDetailsSid);
	}

	/**
	* Sets the company group sid of this hist company group details.
	*
	* @param companyGroupSid the company group sid of this hist company group details
	*/
	@Override
	public void setCompanyGroupSid(int companyGroupSid) {
		_histCompanyGroupDetails.setCompanyGroupSid(companyGroupSid);
	}

	/**
	* Sets the company master sid of this hist company group details.
	*
	* @param companyMasterSid the company master sid of this hist company group details
	*/
	@Override
	public void setCompanyMasterSid(int companyMasterSid) {
		_histCompanyGroupDetails.setCompanyMasterSid(companyMasterSid);
	}

	/**
	* Sets the company parent details sid of this hist company group details.
	*
	* @param companyParentDetailsSid the company parent details sid of this hist company group details
	*/
	@Override
	public void setCompanyParentDetailsSid(
		java.lang.String companyParentDetailsSid) {
		_histCompanyGroupDetails.setCompanyParentDetailsSid(companyParentDetailsSid);
	}

	/**
	* Sets the company tradeclass sid of this hist company group details.
	*
	* @param companyTradeclassSid the company tradeclass sid of this hist company group details
	*/
	@Override
	public void setCompanyTradeclassSid(int companyTradeclassSid) {
		_histCompanyGroupDetails.setCompanyTradeclassSid(companyTradeclassSid);
	}

	/**
	* Sets the created by of this hist company group details.
	*
	* @param createdBy the created by of this hist company group details
	*/
	@Override
	public void setCreatedBy(int createdBy) {
		_histCompanyGroupDetails.setCreatedBy(createdBy);
	}

	/**
	* Sets the created date of this hist company group details.
	*
	* @param createdDate the created date of this hist company group details
	*/
	@Override
	public void setCreatedDate(Date createdDate) {
		_histCompanyGroupDetails.setCreatedDate(createdDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_histCompanyGroupDetails.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_histCompanyGroupDetails.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_histCompanyGroupDetails.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the modified by of this hist company group details.
	*
	* @param modifiedBy the modified by of this hist company group details
	*/
	@Override
	public void setModifiedBy(int modifiedBy) {
		_histCompanyGroupDetails.setModifiedBy(modifiedBy);
	}

	/**
	* Sets the modified date of this hist company group details.
	*
	* @param modifiedDate the modified date of this hist company group details
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_histCompanyGroupDetails.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_histCompanyGroupDetails.setNew(n);
	}

	/**
	* Sets the primary key of this hist company group details.
	*
	* @param primaryKey the primary key of this hist company group details
	*/
	@Override
	public void setPrimaryKey(
		com.stpl.app.service.persistence.HistCompanyGroupDetailsPK primaryKey) {
		_histCompanyGroupDetails.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_histCompanyGroupDetails.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the version no of this hist company group details.
	*
	* @param versionNo the version no of this hist company group details
	*/
	@Override
	public void setVersionNo(int versionNo) {
		_histCompanyGroupDetails.setVersionNo(versionNo);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<HistCompanyGroupDetails> toCacheModel() {
		return _histCompanyGroupDetails.toCacheModel();
	}

	@Override
	public HistCompanyGroupDetails toEscapedModel() {
		return new HistCompanyGroupDetailsWrapper(_histCompanyGroupDetails.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _histCompanyGroupDetails.toString();
	}

	@Override
	public HistCompanyGroupDetails toUnescapedModel() {
		return new HistCompanyGroupDetailsWrapper(_histCompanyGroupDetails.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _histCompanyGroupDetails.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof HistCompanyGroupDetailsWrapper)) {
			return false;
		}

		HistCompanyGroupDetailsWrapper histCompanyGroupDetailsWrapper = (HistCompanyGroupDetailsWrapper)obj;

		if (Objects.equals(_histCompanyGroupDetails,
					histCompanyGroupDetailsWrapper._histCompanyGroupDetails)) {
			return true;
		}

		return false;
	}

	@Override
	public HistCompanyGroupDetails getWrappedModel() {
		return _histCompanyGroupDetails;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _histCompanyGroupDetails.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _histCompanyGroupDetails.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_histCompanyGroupDetails.resetOriginalValues();
	}

	private final HistCompanyGroupDetails _histCompanyGroupDetails;
}