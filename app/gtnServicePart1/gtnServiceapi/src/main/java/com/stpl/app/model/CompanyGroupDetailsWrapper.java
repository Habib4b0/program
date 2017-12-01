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
 * This class is a wrapper for {@link CompanyGroupDetails}.
 * </p>
 *
 * @author
 * @see CompanyGroupDetails
 * @generated
 */
@ProviderType
public class CompanyGroupDetailsWrapper implements CompanyGroupDetails,
	ModelWrapper<CompanyGroupDetails> {
	public CompanyGroupDetailsWrapper(CompanyGroupDetails companyGroupDetails) {
		_companyGroupDetails = companyGroupDetails;
	}

	@Override
	public Class<?> getModelClass() {
		return CompanyGroupDetails.class;
	}

	@Override
	public String getModelClassName() {
		return CompanyGroupDetails.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("createdDate", getCreatedDate());
		attributes.put("createdBy", getCreatedBy());
		attributes.put("companyParentDetailsSid", getCompanyParentDetailsSid());
		attributes.put("companyTradeclassSid", getCompanyTradeclassSid());
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
		return new CompanyGroupDetailsWrapper((CompanyGroupDetails)_companyGroupDetails.clone());
	}

	@Override
	public int compareTo(CompanyGroupDetails companyGroupDetails) {
		return _companyGroupDetails.compareTo(companyGroupDetails);
	}

	/**
	* Returns the company group details sid of this company group details.
	*
	* @return the company group details sid of this company group details
	*/
	@Override
	public int getCompanyGroupDetailsSid() {
		return _companyGroupDetails.getCompanyGroupDetailsSid();
	}

	/**
	* Returns the company group sid of this company group details.
	*
	* @return the company group sid of this company group details
	*/
	@Override
	public int getCompanyGroupSid() {
		return _companyGroupDetails.getCompanyGroupSid();
	}

	/**
	* Returns the company master sid of this company group details.
	*
	* @return the company master sid of this company group details
	*/
	@Override
	public int getCompanyMasterSid() {
		return _companyGroupDetails.getCompanyMasterSid();
	}

	/**
	* Returns the company parent details sid of this company group details.
	*
	* @return the company parent details sid of this company group details
	*/
	@Override
	public java.lang.String getCompanyParentDetailsSid() {
		return _companyGroupDetails.getCompanyParentDetailsSid();
	}

	/**
	* Returns the company tradeclass sid of this company group details.
	*
	* @return the company tradeclass sid of this company group details
	*/
	@Override
	public int getCompanyTradeclassSid() {
		return _companyGroupDetails.getCompanyTradeclassSid();
	}

	/**
	* Returns the created by of this company group details.
	*
	* @return the created by of this company group details
	*/
	@Override
	public int getCreatedBy() {
		return _companyGroupDetails.getCreatedBy();
	}

	/**
	* Returns the created date of this company group details.
	*
	* @return the created date of this company group details
	*/
	@Override
	public Date getCreatedDate() {
		return _companyGroupDetails.getCreatedDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _companyGroupDetails.getExpandoBridge();
	}

	/**
	* Returns the modified by of this company group details.
	*
	* @return the modified by of this company group details
	*/
	@Override
	public int getModifiedBy() {
		return _companyGroupDetails.getModifiedBy();
	}

	/**
	* Returns the modified date of this company group details.
	*
	* @return the modified date of this company group details
	*/
	@Override
	public Date getModifiedDate() {
		return _companyGroupDetails.getModifiedDate();
	}

	/**
	* Returns the primary key of this company group details.
	*
	* @return the primary key of this company group details
	*/
	@Override
	public int getPrimaryKey() {
		return _companyGroupDetails.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _companyGroupDetails.getPrimaryKeyObj();
	}

	/**
	* Returns the version no of this company group details.
	*
	* @return the version no of this company group details
	*/
	@Override
	public int getVersionNo() {
		return _companyGroupDetails.getVersionNo();
	}

	@Override
	public int hashCode() {
		return _companyGroupDetails.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _companyGroupDetails.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _companyGroupDetails.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _companyGroupDetails.isNew();
	}

	@Override
	public void persist() {
		_companyGroupDetails.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_companyGroupDetails.setCachedModel(cachedModel);
	}

	/**
	* Sets the company group details sid of this company group details.
	*
	* @param companyGroupDetailsSid the company group details sid of this company group details
	*/
	@Override
	public void setCompanyGroupDetailsSid(int companyGroupDetailsSid) {
		_companyGroupDetails.setCompanyGroupDetailsSid(companyGroupDetailsSid);
	}

	/**
	* Sets the company group sid of this company group details.
	*
	* @param companyGroupSid the company group sid of this company group details
	*/
	@Override
	public void setCompanyGroupSid(int companyGroupSid) {
		_companyGroupDetails.setCompanyGroupSid(companyGroupSid);
	}

	/**
	* Sets the company master sid of this company group details.
	*
	* @param companyMasterSid the company master sid of this company group details
	*/
	@Override
	public void setCompanyMasterSid(int companyMasterSid) {
		_companyGroupDetails.setCompanyMasterSid(companyMasterSid);
	}

	/**
	* Sets the company parent details sid of this company group details.
	*
	* @param companyParentDetailsSid the company parent details sid of this company group details
	*/
	@Override
	public void setCompanyParentDetailsSid(
		java.lang.String companyParentDetailsSid) {
		_companyGroupDetails.setCompanyParentDetailsSid(companyParentDetailsSid);
	}

	/**
	* Sets the company tradeclass sid of this company group details.
	*
	* @param companyTradeclassSid the company tradeclass sid of this company group details
	*/
	@Override
	public void setCompanyTradeclassSid(int companyTradeclassSid) {
		_companyGroupDetails.setCompanyTradeclassSid(companyTradeclassSid);
	}

	/**
	* Sets the created by of this company group details.
	*
	* @param createdBy the created by of this company group details
	*/
	@Override
	public void setCreatedBy(int createdBy) {
		_companyGroupDetails.setCreatedBy(createdBy);
	}

	/**
	* Sets the created date of this company group details.
	*
	* @param createdDate the created date of this company group details
	*/
	@Override
	public void setCreatedDate(Date createdDate) {
		_companyGroupDetails.setCreatedDate(createdDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_companyGroupDetails.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_companyGroupDetails.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_companyGroupDetails.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the modified by of this company group details.
	*
	* @param modifiedBy the modified by of this company group details
	*/
	@Override
	public void setModifiedBy(int modifiedBy) {
		_companyGroupDetails.setModifiedBy(modifiedBy);
	}

	/**
	* Sets the modified date of this company group details.
	*
	* @param modifiedDate the modified date of this company group details
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_companyGroupDetails.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_companyGroupDetails.setNew(n);
	}

	/**
	* Sets the primary key of this company group details.
	*
	* @param primaryKey the primary key of this company group details
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_companyGroupDetails.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_companyGroupDetails.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the version no of this company group details.
	*
	* @param versionNo the version no of this company group details
	*/
	@Override
	public void setVersionNo(int versionNo) {
		_companyGroupDetails.setVersionNo(versionNo);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<CompanyGroupDetails> toCacheModel() {
		return _companyGroupDetails.toCacheModel();
	}

	@Override
	public CompanyGroupDetails toEscapedModel() {
		return new CompanyGroupDetailsWrapper(_companyGroupDetails.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _companyGroupDetails.toString();
	}

	@Override
	public CompanyGroupDetails toUnescapedModel() {
		return new CompanyGroupDetailsWrapper(_companyGroupDetails.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _companyGroupDetails.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CompanyGroupDetailsWrapper)) {
			return false;
		}

		CompanyGroupDetailsWrapper companyGroupDetailsWrapper = (CompanyGroupDetailsWrapper)obj;

		if (Objects.equals(_companyGroupDetails,
					companyGroupDetailsWrapper._companyGroupDetails)) {
			return true;
		}

		return false;
	}

	@Override
	public CompanyGroupDetails getWrappedModel() {
		return _companyGroupDetails;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _companyGroupDetails.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _companyGroupDetails.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_companyGroupDetails.resetOriginalValues();
	}

	private final CompanyGroupDetails _companyGroupDetails;
}