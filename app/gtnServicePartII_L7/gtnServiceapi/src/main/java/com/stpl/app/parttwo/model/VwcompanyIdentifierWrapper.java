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
 * This class is a wrapper for {@link VwcompanyIdentifier}.
 * </p>
 *
 * @author
 * @see VwcompanyIdentifier
 * @generated
 */
@ProviderType
public class VwcompanyIdentifierWrapper implements VwcompanyIdentifier,
	ModelWrapper<VwcompanyIdentifier> {
	public VwcompanyIdentifierWrapper(VwcompanyIdentifier vwcompanyIdentifier) {
		_vwcompanyIdentifier = vwcompanyIdentifier;
	}

	@Override
	public Class<?> getModelClass() {
		return VwcompanyIdentifier.class;
	}

	@Override
	public String getModelClassName() {
		return VwcompanyIdentifier.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("companyId", getCompanyId());
		attributes.put("companyName", getCompanyName());
		attributes.put("endDate", getEndDate());
		attributes.put("companyIdentifierSid", getCompanyIdentifierSid());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("identifierStatus", getIdentifierStatus());
		attributes.put("companyIdentifier", getCompanyIdentifier());
		attributes.put("entityCode", getEntityCode());
		attributes.put("startDate", getStartDate());
		attributes.put("createdDate", getCreatedDate());
		attributes.put("createdBy", getCreatedBy());
		attributes.put("source", getSource());
		attributes.put("companyNo", getCompanyNo());
		attributes.put("batchId", getBatchId());
		attributes.put("addChgDelIndicator", getAddChgDelIndicator());
		attributes.put("identifierCodeQualifierName",
			getIdentifierCodeQualifierName());
		attributes.put("modifiedBy", getModifiedBy());
		attributes.put("identifierCodeQualifier", getIdentifierCodeQualifier());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String companyId = (String)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		String companyName = (String)attributes.get("companyName");

		if (companyName != null) {
			setCompanyName(companyName);
		}

		Date endDate = (Date)attributes.get("endDate");

		if (endDate != null) {
			setEndDate(endDate);
		}

		Integer companyIdentifierSid = (Integer)attributes.get(
				"companyIdentifierSid");

		if (companyIdentifierSid != null) {
			setCompanyIdentifierSid(companyIdentifierSid);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String identifierStatus = (String)attributes.get("identifierStatus");

		if (identifierStatus != null) {
			setIdentifierStatus(identifierStatus);
		}

		String companyIdentifier = (String)attributes.get("companyIdentifier");

		if (companyIdentifier != null) {
			setCompanyIdentifier(companyIdentifier);
		}

		String entityCode = (String)attributes.get("entityCode");

		if (entityCode != null) {
			setEntityCode(entityCode);
		}

		Date startDate = (Date)attributes.get("startDate");

		if (startDate != null) {
			setStartDate(startDate);
		}

		Date createdDate = (Date)attributes.get("createdDate");

		if (createdDate != null) {
			setCreatedDate(createdDate);
		}

		String createdBy = (String)attributes.get("createdBy");

		if (createdBy != null) {
			setCreatedBy(createdBy);
		}

		String source = (String)attributes.get("source");

		if (source != null) {
			setSource(source);
		}

		String companyNo = (String)attributes.get("companyNo");

		if (companyNo != null) {
			setCompanyNo(companyNo);
		}

		String batchId = (String)attributes.get("batchId");

		if (batchId != null) {
			setBatchId(batchId);
		}

		String addChgDelIndicator = (String)attributes.get("addChgDelIndicator");

		if (addChgDelIndicator != null) {
			setAddChgDelIndicator(addChgDelIndicator);
		}

		String identifierCodeQualifierName = (String)attributes.get(
				"identifierCodeQualifierName");

		if (identifierCodeQualifierName != null) {
			setIdentifierCodeQualifierName(identifierCodeQualifierName);
		}

		String modifiedBy = (String)attributes.get("modifiedBy");

		if (modifiedBy != null) {
			setModifiedBy(modifiedBy);
		}

		String identifierCodeQualifier = (String)attributes.get(
				"identifierCodeQualifier");

		if (identifierCodeQualifier != null) {
			setIdentifierCodeQualifier(identifierCodeQualifier);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new VwcompanyIdentifierWrapper((VwcompanyIdentifier)_vwcompanyIdentifier.clone());
	}

	@Override
	public int compareTo(VwcompanyIdentifier vwcompanyIdentifier) {
		return _vwcompanyIdentifier.compareTo(vwcompanyIdentifier);
	}

	/**
	* Returns the add chg del indicator of this vwcompany identifier.
	*
	* @return the add chg del indicator of this vwcompany identifier
	*/
	@Override
	public java.lang.String getAddChgDelIndicator() {
		return _vwcompanyIdentifier.getAddChgDelIndicator();
	}

	/**
	* Returns the batch ID of this vwcompany identifier.
	*
	* @return the batch ID of this vwcompany identifier
	*/
	@Override
	public java.lang.String getBatchId() {
		return _vwcompanyIdentifier.getBatchId();
	}

	/**
	* Returns the company ID of this vwcompany identifier.
	*
	* @return the company ID of this vwcompany identifier
	*/
	@Override
	public java.lang.String getCompanyId() {
		return _vwcompanyIdentifier.getCompanyId();
	}

	/**
	* Returns the company identifier of this vwcompany identifier.
	*
	* @return the company identifier of this vwcompany identifier
	*/
	@Override
	public java.lang.String getCompanyIdentifier() {
		return _vwcompanyIdentifier.getCompanyIdentifier();
	}

	/**
	* Returns the company identifier sid of this vwcompany identifier.
	*
	* @return the company identifier sid of this vwcompany identifier
	*/
	@Override
	public int getCompanyIdentifierSid() {
		return _vwcompanyIdentifier.getCompanyIdentifierSid();
	}

	/**
	* Returns the company name of this vwcompany identifier.
	*
	* @return the company name of this vwcompany identifier
	*/
	@Override
	public java.lang.String getCompanyName() {
		return _vwcompanyIdentifier.getCompanyName();
	}

	/**
	* Returns the company no of this vwcompany identifier.
	*
	* @return the company no of this vwcompany identifier
	*/
	@Override
	public java.lang.String getCompanyNo() {
		return _vwcompanyIdentifier.getCompanyNo();
	}

	/**
	* Returns the created by of this vwcompany identifier.
	*
	* @return the created by of this vwcompany identifier
	*/
	@Override
	public java.lang.String getCreatedBy() {
		return _vwcompanyIdentifier.getCreatedBy();
	}

	/**
	* Returns the created date of this vwcompany identifier.
	*
	* @return the created date of this vwcompany identifier
	*/
	@Override
	public Date getCreatedDate() {
		return _vwcompanyIdentifier.getCreatedDate();
	}

	/**
	* Returns the end date of this vwcompany identifier.
	*
	* @return the end date of this vwcompany identifier
	*/
	@Override
	public Date getEndDate() {
		return _vwcompanyIdentifier.getEndDate();
	}

	/**
	* Returns the entity code of this vwcompany identifier.
	*
	* @return the entity code of this vwcompany identifier
	*/
	@Override
	public java.lang.String getEntityCode() {
		return _vwcompanyIdentifier.getEntityCode();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _vwcompanyIdentifier.getExpandoBridge();
	}

	/**
	* Returns the identifier code qualifier of this vwcompany identifier.
	*
	* @return the identifier code qualifier of this vwcompany identifier
	*/
	@Override
	public java.lang.String getIdentifierCodeQualifier() {
		return _vwcompanyIdentifier.getIdentifierCodeQualifier();
	}

	/**
	* Returns the identifier code qualifier name of this vwcompany identifier.
	*
	* @return the identifier code qualifier name of this vwcompany identifier
	*/
	@Override
	public java.lang.String getIdentifierCodeQualifierName() {
		return _vwcompanyIdentifier.getIdentifierCodeQualifierName();
	}

	/**
	* Returns the identifier status of this vwcompany identifier.
	*
	* @return the identifier status of this vwcompany identifier
	*/
	@Override
	public java.lang.String getIdentifierStatus() {
		return _vwcompanyIdentifier.getIdentifierStatus();
	}

	/**
	* Returns the modified by of this vwcompany identifier.
	*
	* @return the modified by of this vwcompany identifier
	*/
	@Override
	public java.lang.String getModifiedBy() {
		return _vwcompanyIdentifier.getModifiedBy();
	}

	/**
	* Returns the modified date of this vwcompany identifier.
	*
	* @return the modified date of this vwcompany identifier
	*/
	@Override
	public Date getModifiedDate() {
		return _vwcompanyIdentifier.getModifiedDate();
	}

	/**
	* Returns the primary key of this vwcompany identifier.
	*
	* @return the primary key of this vwcompany identifier
	*/
	@Override
	public int getPrimaryKey() {
		return _vwcompanyIdentifier.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _vwcompanyIdentifier.getPrimaryKeyObj();
	}

	/**
	* Returns the source of this vwcompany identifier.
	*
	* @return the source of this vwcompany identifier
	*/
	@Override
	public java.lang.String getSource() {
		return _vwcompanyIdentifier.getSource();
	}

	/**
	* Returns the start date of this vwcompany identifier.
	*
	* @return the start date of this vwcompany identifier
	*/
	@Override
	public Date getStartDate() {
		return _vwcompanyIdentifier.getStartDate();
	}

	@Override
	public int hashCode() {
		return _vwcompanyIdentifier.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _vwcompanyIdentifier.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _vwcompanyIdentifier.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _vwcompanyIdentifier.isNew();
	}

	@Override
	public void persist() {
		_vwcompanyIdentifier.persist();
	}

	/**
	* Sets the add chg del indicator of this vwcompany identifier.
	*
	* @param addChgDelIndicator the add chg del indicator of this vwcompany identifier
	*/
	@Override
	public void setAddChgDelIndicator(java.lang.String addChgDelIndicator) {
		_vwcompanyIdentifier.setAddChgDelIndicator(addChgDelIndicator);
	}

	/**
	* Sets the batch ID of this vwcompany identifier.
	*
	* @param batchId the batch ID of this vwcompany identifier
	*/
	@Override
	public void setBatchId(java.lang.String batchId) {
		_vwcompanyIdentifier.setBatchId(batchId);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_vwcompanyIdentifier.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this vwcompany identifier.
	*
	* @param companyId the company ID of this vwcompany identifier
	*/
	@Override
	public void setCompanyId(java.lang.String companyId) {
		_vwcompanyIdentifier.setCompanyId(companyId);
	}

	/**
	* Sets the company identifier of this vwcompany identifier.
	*
	* @param companyIdentifier the company identifier of this vwcompany identifier
	*/
	@Override
	public void setCompanyIdentifier(java.lang.String companyIdentifier) {
		_vwcompanyIdentifier.setCompanyIdentifier(companyIdentifier);
	}

	/**
	* Sets the company identifier sid of this vwcompany identifier.
	*
	* @param companyIdentifierSid the company identifier sid of this vwcompany identifier
	*/
	@Override
	public void setCompanyIdentifierSid(int companyIdentifierSid) {
		_vwcompanyIdentifier.setCompanyIdentifierSid(companyIdentifierSid);
	}

	/**
	* Sets the company name of this vwcompany identifier.
	*
	* @param companyName the company name of this vwcompany identifier
	*/
	@Override
	public void setCompanyName(java.lang.String companyName) {
		_vwcompanyIdentifier.setCompanyName(companyName);
	}

	/**
	* Sets the company no of this vwcompany identifier.
	*
	* @param companyNo the company no of this vwcompany identifier
	*/
	@Override
	public void setCompanyNo(java.lang.String companyNo) {
		_vwcompanyIdentifier.setCompanyNo(companyNo);
	}

	/**
	* Sets the created by of this vwcompany identifier.
	*
	* @param createdBy the created by of this vwcompany identifier
	*/
	@Override
	public void setCreatedBy(java.lang.String createdBy) {
		_vwcompanyIdentifier.setCreatedBy(createdBy);
	}

	/**
	* Sets the created date of this vwcompany identifier.
	*
	* @param createdDate the created date of this vwcompany identifier
	*/
	@Override
	public void setCreatedDate(Date createdDate) {
		_vwcompanyIdentifier.setCreatedDate(createdDate);
	}

	/**
	* Sets the end date of this vwcompany identifier.
	*
	* @param endDate the end date of this vwcompany identifier
	*/
	@Override
	public void setEndDate(Date endDate) {
		_vwcompanyIdentifier.setEndDate(endDate);
	}

	/**
	* Sets the entity code of this vwcompany identifier.
	*
	* @param entityCode the entity code of this vwcompany identifier
	*/
	@Override
	public void setEntityCode(java.lang.String entityCode) {
		_vwcompanyIdentifier.setEntityCode(entityCode);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_vwcompanyIdentifier.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_vwcompanyIdentifier.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_vwcompanyIdentifier.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the identifier code qualifier of this vwcompany identifier.
	*
	* @param identifierCodeQualifier the identifier code qualifier of this vwcompany identifier
	*/
	@Override
	public void setIdentifierCodeQualifier(
		java.lang.String identifierCodeQualifier) {
		_vwcompanyIdentifier.setIdentifierCodeQualifier(identifierCodeQualifier);
	}

	/**
	* Sets the identifier code qualifier name of this vwcompany identifier.
	*
	* @param identifierCodeQualifierName the identifier code qualifier name of this vwcompany identifier
	*/
	@Override
	public void setIdentifierCodeQualifierName(
		java.lang.String identifierCodeQualifierName) {
		_vwcompanyIdentifier.setIdentifierCodeQualifierName(identifierCodeQualifierName);
	}

	/**
	* Sets the identifier status of this vwcompany identifier.
	*
	* @param identifierStatus the identifier status of this vwcompany identifier
	*/
	@Override
	public void setIdentifierStatus(java.lang.String identifierStatus) {
		_vwcompanyIdentifier.setIdentifierStatus(identifierStatus);
	}

	/**
	* Sets the modified by of this vwcompany identifier.
	*
	* @param modifiedBy the modified by of this vwcompany identifier
	*/
	@Override
	public void setModifiedBy(java.lang.String modifiedBy) {
		_vwcompanyIdentifier.setModifiedBy(modifiedBy);
	}

	/**
	* Sets the modified date of this vwcompany identifier.
	*
	* @param modifiedDate the modified date of this vwcompany identifier
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_vwcompanyIdentifier.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_vwcompanyIdentifier.setNew(n);
	}

	/**
	* Sets the primary key of this vwcompany identifier.
	*
	* @param primaryKey the primary key of this vwcompany identifier
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_vwcompanyIdentifier.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_vwcompanyIdentifier.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the source of this vwcompany identifier.
	*
	* @param source the source of this vwcompany identifier
	*/
	@Override
	public void setSource(java.lang.String source) {
		_vwcompanyIdentifier.setSource(source);
	}

	/**
	* Sets the start date of this vwcompany identifier.
	*
	* @param startDate the start date of this vwcompany identifier
	*/
	@Override
	public void setStartDate(Date startDate) {
		_vwcompanyIdentifier.setStartDate(startDate);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<VwcompanyIdentifier> toCacheModel() {
		return _vwcompanyIdentifier.toCacheModel();
	}

	@Override
	public VwcompanyIdentifier toEscapedModel() {
		return new VwcompanyIdentifierWrapper(_vwcompanyIdentifier.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _vwcompanyIdentifier.toString();
	}

	@Override
	public VwcompanyIdentifier toUnescapedModel() {
		return new VwcompanyIdentifierWrapper(_vwcompanyIdentifier.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _vwcompanyIdentifier.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof VwcompanyIdentifierWrapper)) {
			return false;
		}

		VwcompanyIdentifierWrapper vwcompanyIdentifierWrapper = (VwcompanyIdentifierWrapper)obj;

		if (Objects.equals(_vwcompanyIdentifier,
					vwcompanyIdentifierWrapper._vwcompanyIdentifier)) {
			return true;
		}

		return false;
	}

	@Override
	public VwcompanyIdentifier getWrappedModel() {
		return _vwcompanyIdentifier;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _vwcompanyIdentifier.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _vwcompanyIdentifier.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_vwcompanyIdentifier.resetOriginalValues();
	}

	private final VwcompanyIdentifier _vwcompanyIdentifier;
}