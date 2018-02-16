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
 * This class is a wrapper for {@link VwCompanyParentDetails}.
 * </p>
 *
 * @author
 * @see VwCompanyParentDetails
 * @generated
 */
@ProviderType
public class VwCompanyParentDetailsWrapper implements VwCompanyParentDetails,
	ModelWrapper<VwCompanyParentDetails> {
	public VwCompanyParentDetailsWrapper(
		VwCompanyParentDetails vwCompanyParentDetails) {
		_vwCompanyParentDetails = vwCompanyParentDetails;
	}

	@Override
	public Class<?> getModelClass() {
		return VwCompanyParentDetails.class;
	}

	@Override
	public String getModelClassName() {
		return VwCompanyParentDetails.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("parentcompanyId", getParentcompanyId());
		attributes.put("priorParentcompanyId", getPriorParentcompanyId());
		attributes.put("companyIdString", getCompanyIdString());
		attributes.put("lastUpdatedDate", getLastUpdatedDate());
		attributes.put("parentEndDate", getParentEndDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("priorParentStartDate", getPriorParentStartDate());
		attributes.put("source", getSource());
		attributes.put("createdBy", getCreatedBy());
		attributes.put("createdDate", getCreatedDate());
		attributes.put("companyParentDetailsSid", getCompanyParentDetailsSid());
		attributes.put("batchId", getBatchId());
		attributes.put("addChgDelIndicator", getAddChgDelIndicator());
		attributes.put("modifiedBy", getModifiedBy());
		attributes.put("parentStartDate", getParentStartDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String parentcompanyId = (String)attributes.get("parentcompanyId");

		if (parentcompanyId != null) {
			setParentcompanyId(parentcompanyId);
		}

		String priorParentcompanyId = (String)attributes.get(
				"priorParentcompanyId");

		if (priorParentcompanyId != null) {
			setPriorParentcompanyId(priorParentcompanyId);
		}

		String companyIdString = (String)attributes.get("companyIdString");

		if (companyIdString != null) {
			setCompanyIdString(companyIdString);
		}

		Date lastUpdatedDate = (Date)attributes.get("lastUpdatedDate");

		if (lastUpdatedDate != null) {
			setLastUpdatedDate(lastUpdatedDate);
		}

		Date parentEndDate = (Date)attributes.get("parentEndDate");

		if (parentEndDate != null) {
			setParentEndDate(parentEndDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Date priorParentStartDate = (Date)attributes.get("priorParentStartDate");

		if (priorParentStartDate != null) {
			setPriorParentStartDate(priorParentStartDate);
		}

		String source = (String)attributes.get("source");

		if (source != null) {
			setSource(source);
		}

		String createdBy = (String)attributes.get("createdBy");

		if (createdBy != null) {
			setCreatedBy(createdBy);
		}

		Date createdDate = (Date)attributes.get("createdDate");

		if (createdDate != null) {
			setCreatedDate(createdDate);
		}

		Integer companyParentDetailsSid = (Integer)attributes.get(
				"companyParentDetailsSid");

		if (companyParentDetailsSid != null) {
			setCompanyParentDetailsSid(companyParentDetailsSid);
		}

		String batchId = (String)attributes.get("batchId");

		if (batchId != null) {
			setBatchId(batchId);
		}

		String addChgDelIndicator = (String)attributes.get("addChgDelIndicator");

		if (addChgDelIndicator != null) {
			setAddChgDelIndicator(addChgDelIndicator);
		}

		String modifiedBy = (String)attributes.get("modifiedBy");

		if (modifiedBy != null) {
			setModifiedBy(modifiedBy);
		}

		Date parentStartDate = (Date)attributes.get("parentStartDate");

		if (parentStartDate != null) {
			setParentStartDate(parentStartDate);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new VwCompanyParentDetailsWrapper((VwCompanyParentDetails)_vwCompanyParentDetails.clone());
	}

	@Override
	public int compareTo(VwCompanyParentDetails vwCompanyParentDetails) {
		return _vwCompanyParentDetails.compareTo(vwCompanyParentDetails);
	}

	/**
	* Returns the add chg del indicator of this vw company parent details.
	*
	* @return the add chg del indicator of this vw company parent details
	*/
	@Override
	public java.lang.String getAddChgDelIndicator() {
		return _vwCompanyParentDetails.getAddChgDelIndicator();
	}

	/**
	* Returns the batch ID of this vw company parent details.
	*
	* @return the batch ID of this vw company parent details
	*/
	@Override
	public java.lang.String getBatchId() {
		return _vwCompanyParentDetails.getBatchId();
	}

	/**
	* Returns the company ID string of this vw company parent details.
	*
	* @return the company ID string of this vw company parent details
	*/
	@Override
	public java.lang.String getCompanyIdString() {
		return _vwCompanyParentDetails.getCompanyIdString();
	}

	/**
	* Returns the company parent details sid of this vw company parent details.
	*
	* @return the company parent details sid of this vw company parent details
	*/
	@Override
	public int getCompanyParentDetailsSid() {
		return _vwCompanyParentDetails.getCompanyParentDetailsSid();
	}

	/**
	* Returns the created by of this vw company parent details.
	*
	* @return the created by of this vw company parent details
	*/
	@Override
	public java.lang.String getCreatedBy() {
		return _vwCompanyParentDetails.getCreatedBy();
	}

	/**
	* Returns the created date of this vw company parent details.
	*
	* @return the created date of this vw company parent details
	*/
	@Override
	public Date getCreatedDate() {
		return _vwCompanyParentDetails.getCreatedDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _vwCompanyParentDetails.getExpandoBridge();
	}

	/**
	* Returns the last updated date of this vw company parent details.
	*
	* @return the last updated date of this vw company parent details
	*/
	@Override
	public Date getLastUpdatedDate() {
		return _vwCompanyParentDetails.getLastUpdatedDate();
	}

	/**
	* Returns the modified by of this vw company parent details.
	*
	* @return the modified by of this vw company parent details
	*/
	@Override
	public java.lang.String getModifiedBy() {
		return _vwCompanyParentDetails.getModifiedBy();
	}

	/**
	* Returns the modified date of this vw company parent details.
	*
	* @return the modified date of this vw company parent details
	*/
	@Override
	public Date getModifiedDate() {
		return _vwCompanyParentDetails.getModifiedDate();
	}

	/**
	* Returns the parentcompany ID of this vw company parent details.
	*
	* @return the parentcompany ID of this vw company parent details
	*/
	@Override
	public java.lang.String getParentcompanyId() {
		return _vwCompanyParentDetails.getParentcompanyId();
	}

	/**
	* Returns the parent end date of this vw company parent details.
	*
	* @return the parent end date of this vw company parent details
	*/
	@Override
	public Date getParentEndDate() {
		return _vwCompanyParentDetails.getParentEndDate();
	}

	/**
	* Returns the parent start date of this vw company parent details.
	*
	* @return the parent start date of this vw company parent details
	*/
	@Override
	public Date getParentStartDate() {
		return _vwCompanyParentDetails.getParentStartDate();
	}

	/**
	* Returns the primary key of this vw company parent details.
	*
	* @return the primary key of this vw company parent details
	*/
	@Override
	public int getPrimaryKey() {
		return _vwCompanyParentDetails.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _vwCompanyParentDetails.getPrimaryKeyObj();
	}

	/**
	* Returns the prior parentcompany ID of this vw company parent details.
	*
	* @return the prior parentcompany ID of this vw company parent details
	*/
	@Override
	public java.lang.String getPriorParentcompanyId() {
		return _vwCompanyParentDetails.getPriorParentcompanyId();
	}

	/**
	* Returns the prior parent start date of this vw company parent details.
	*
	* @return the prior parent start date of this vw company parent details
	*/
	@Override
	public Date getPriorParentStartDate() {
		return _vwCompanyParentDetails.getPriorParentStartDate();
	}

	/**
	* Returns the source of this vw company parent details.
	*
	* @return the source of this vw company parent details
	*/
	@Override
	public java.lang.String getSource() {
		return _vwCompanyParentDetails.getSource();
	}

	@Override
	public int hashCode() {
		return _vwCompanyParentDetails.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _vwCompanyParentDetails.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _vwCompanyParentDetails.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _vwCompanyParentDetails.isNew();
	}

	@Override
	public void persist() {
		_vwCompanyParentDetails.persist();
	}

	/**
	* Sets the add chg del indicator of this vw company parent details.
	*
	* @param addChgDelIndicator the add chg del indicator of this vw company parent details
	*/
	@Override
	public void setAddChgDelIndicator(java.lang.String addChgDelIndicator) {
		_vwCompanyParentDetails.setAddChgDelIndicator(addChgDelIndicator);
	}

	/**
	* Sets the batch ID of this vw company parent details.
	*
	* @param batchId the batch ID of this vw company parent details
	*/
	@Override
	public void setBatchId(java.lang.String batchId) {
		_vwCompanyParentDetails.setBatchId(batchId);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_vwCompanyParentDetails.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID string of this vw company parent details.
	*
	* @param companyIdString the company ID string of this vw company parent details
	*/
	@Override
	public void setCompanyIdString(java.lang.String companyIdString) {
		_vwCompanyParentDetails.setCompanyIdString(companyIdString);
	}

	/**
	* Sets the company parent details sid of this vw company parent details.
	*
	* @param companyParentDetailsSid the company parent details sid of this vw company parent details
	*/
	@Override
	public void setCompanyParentDetailsSid(int companyParentDetailsSid) {
		_vwCompanyParentDetails.setCompanyParentDetailsSid(companyParentDetailsSid);
	}

	/**
	* Sets the created by of this vw company parent details.
	*
	* @param createdBy the created by of this vw company parent details
	*/
	@Override
	public void setCreatedBy(java.lang.String createdBy) {
		_vwCompanyParentDetails.setCreatedBy(createdBy);
	}

	/**
	* Sets the created date of this vw company parent details.
	*
	* @param createdDate the created date of this vw company parent details
	*/
	@Override
	public void setCreatedDate(Date createdDate) {
		_vwCompanyParentDetails.setCreatedDate(createdDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_vwCompanyParentDetails.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_vwCompanyParentDetails.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_vwCompanyParentDetails.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the last updated date of this vw company parent details.
	*
	* @param lastUpdatedDate the last updated date of this vw company parent details
	*/
	@Override
	public void setLastUpdatedDate(Date lastUpdatedDate) {
		_vwCompanyParentDetails.setLastUpdatedDate(lastUpdatedDate);
	}

	/**
	* Sets the modified by of this vw company parent details.
	*
	* @param modifiedBy the modified by of this vw company parent details
	*/
	@Override
	public void setModifiedBy(java.lang.String modifiedBy) {
		_vwCompanyParentDetails.setModifiedBy(modifiedBy);
	}

	/**
	* Sets the modified date of this vw company parent details.
	*
	* @param modifiedDate the modified date of this vw company parent details
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_vwCompanyParentDetails.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_vwCompanyParentDetails.setNew(n);
	}

	/**
	* Sets the parentcompany ID of this vw company parent details.
	*
	* @param parentcompanyId the parentcompany ID of this vw company parent details
	*/
	@Override
	public void setParentcompanyId(java.lang.String parentcompanyId) {
		_vwCompanyParentDetails.setParentcompanyId(parentcompanyId);
	}

	/**
	* Sets the parent end date of this vw company parent details.
	*
	* @param parentEndDate the parent end date of this vw company parent details
	*/
	@Override
	public void setParentEndDate(Date parentEndDate) {
		_vwCompanyParentDetails.setParentEndDate(parentEndDate);
	}

	/**
	* Sets the parent start date of this vw company parent details.
	*
	* @param parentStartDate the parent start date of this vw company parent details
	*/
	@Override
	public void setParentStartDate(Date parentStartDate) {
		_vwCompanyParentDetails.setParentStartDate(parentStartDate);
	}

	/**
	* Sets the primary key of this vw company parent details.
	*
	* @param primaryKey the primary key of this vw company parent details
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_vwCompanyParentDetails.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_vwCompanyParentDetails.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the prior parentcompany ID of this vw company parent details.
	*
	* @param priorParentcompanyId the prior parentcompany ID of this vw company parent details
	*/
	@Override
	public void setPriorParentcompanyId(java.lang.String priorParentcompanyId) {
		_vwCompanyParentDetails.setPriorParentcompanyId(priorParentcompanyId);
	}

	/**
	* Sets the prior parent start date of this vw company parent details.
	*
	* @param priorParentStartDate the prior parent start date of this vw company parent details
	*/
	@Override
	public void setPriorParentStartDate(Date priorParentStartDate) {
		_vwCompanyParentDetails.setPriorParentStartDate(priorParentStartDate);
	}

	/**
	* Sets the source of this vw company parent details.
	*
	* @param source the source of this vw company parent details
	*/
	@Override
	public void setSource(java.lang.String source) {
		_vwCompanyParentDetails.setSource(source);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<VwCompanyParentDetails> toCacheModel() {
		return _vwCompanyParentDetails.toCacheModel();
	}

	@Override
	public VwCompanyParentDetails toEscapedModel() {
		return new VwCompanyParentDetailsWrapper(_vwCompanyParentDetails.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _vwCompanyParentDetails.toString();
	}

	@Override
	public VwCompanyParentDetails toUnescapedModel() {
		return new VwCompanyParentDetailsWrapper(_vwCompanyParentDetails.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _vwCompanyParentDetails.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof VwCompanyParentDetailsWrapper)) {
			return false;
		}

		VwCompanyParentDetailsWrapper vwCompanyParentDetailsWrapper = (VwCompanyParentDetailsWrapper)obj;

		if (Objects.equals(_vwCompanyParentDetails,
					vwCompanyParentDetailsWrapper._vwCompanyParentDetails)) {
			return true;
		}

		return false;
	}

	@Override
	public VwCompanyParentDetails getWrappedModel() {
		return _vwCompanyParentDetails;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _vwCompanyParentDetails.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _vwCompanyParentDetails.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_vwCompanyParentDetails.resetOriginalValues();
	}

	private final VwCompanyParentDetails _vwCompanyParentDetails;
}