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
 * This class is a wrapper for {@link CffApprovalDetails}.
 * </p>
 *
 * @author
 * @see CffApprovalDetails
 * @generated
 */
@ProviderType
public class CffApprovalDetailsWrapper implements CffApprovalDetails,
	ModelWrapper<CffApprovalDetails> {
	public CffApprovalDetailsWrapper(CffApprovalDetails cffApprovalDetails) {
		_cffApprovalDetails = cffApprovalDetails;
	}

	@Override
	public Class<?> getModelClass() {
		return CffApprovalDetails.class;
	}

	@Override
	public String getModelClassName() {
		return CffApprovalDetails.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("approvalSequence", getApprovalSequence());
		attributes.put("approvedDate", getApprovedDate());
		attributes.put("approvedBy", getApprovedBy());
		attributes.put("approvalStatus", getApprovalStatus());
		attributes.put("cffMasterSid", getCffMasterSid());
		attributes.put("inboundStatus", getInboundStatus());
		attributes.put("cffApprovalDetailsSid", getCffApprovalDetailsSid());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Integer approvalSequence = (Integer)attributes.get("approvalSequence");

		if (approvalSequence != null) {
			setApprovalSequence(approvalSequence);
		}

		Date approvedDate = (Date)attributes.get("approvedDate");

		if (approvedDate != null) {
			setApprovedDate(approvedDate);
		}

		Integer approvedBy = (Integer)attributes.get("approvedBy");

		if (approvedBy != null) {
			setApprovedBy(approvedBy);
		}

		Integer approvalStatus = (Integer)attributes.get("approvalStatus");

		if (approvalStatus != null) {
			setApprovalStatus(approvalStatus);
		}

		Integer cffMasterSid = (Integer)attributes.get("cffMasterSid");

		if (cffMasterSid != null) {
			setCffMasterSid(cffMasterSid);
		}

		String inboundStatus = (String)attributes.get("inboundStatus");

		if (inboundStatus != null) {
			setInboundStatus(inboundStatus);
		}

		Integer cffApprovalDetailsSid = (Integer)attributes.get(
				"cffApprovalDetailsSid");

		if (cffApprovalDetailsSid != null) {
			setCffApprovalDetailsSid(cffApprovalDetailsSid);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new CffApprovalDetailsWrapper((CffApprovalDetails)_cffApprovalDetails.clone());
	}

	@Override
	public int compareTo(CffApprovalDetails cffApprovalDetails) {
		return _cffApprovalDetails.compareTo(cffApprovalDetails);
	}

	/**
	* Returns the approval sequence of this cff approval details.
	*
	* @return the approval sequence of this cff approval details
	*/
	@Override
	public int getApprovalSequence() {
		return _cffApprovalDetails.getApprovalSequence();
	}

	/**
	* Returns the approval status of this cff approval details.
	*
	* @return the approval status of this cff approval details
	*/
	@Override
	public int getApprovalStatus() {
		return _cffApprovalDetails.getApprovalStatus();
	}

	/**
	* Returns the approved by of this cff approval details.
	*
	* @return the approved by of this cff approval details
	*/
	@Override
	public int getApprovedBy() {
		return _cffApprovalDetails.getApprovedBy();
	}

	/**
	* Returns the approved date of this cff approval details.
	*
	* @return the approved date of this cff approval details
	*/
	@Override
	public Date getApprovedDate() {
		return _cffApprovalDetails.getApprovedDate();
	}

	/**
	* Returns the cff approval details sid of this cff approval details.
	*
	* @return the cff approval details sid of this cff approval details
	*/
	@Override
	public int getCffApprovalDetailsSid() {
		return _cffApprovalDetails.getCffApprovalDetailsSid();
	}

	/**
	* Returns the cff master sid of this cff approval details.
	*
	* @return the cff master sid of this cff approval details
	*/
	@Override
	public int getCffMasterSid() {
		return _cffApprovalDetails.getCffMasterSid();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _cffApprovalDetails.getExpandoBridge();
	}

	/**
	* Returns the inbound status of this cff approval details.
	*
	* @return the inbound status of this cff approval details
	*/
	@Override
	public java.lang.String getInboundStatus() {
		return _cffApprovalDetails.getInboundStatus();
	}

	/**
	* Returns the primary key of this cff approval details.
	*
	* @return the primary key of this cff approval details
	*/
	@Override
	public int getPrimaryKey() {
		return _cffApprovalDetails.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _cffApprovalDetails.getPrimaryKeyObj();
	}

	@Override
	public int hashCode() {
		return _cffApprovalDetails.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _cffApprovalDetails.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _cffApprovalDetails.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _cffApprovalDetails.isNew();
	}

	@Override
	public void persist() {
		_cffApprovalDetails.persist();
	}

	/**
	* Sets the approval sequence of this cff approval details.
	*
	* @param approvalSequence the approval sequence of this cff approval details
	*/
	@Override
	public void setApprovalSequence(int approvalSequence) {
		_cffApprovalDetails.setApprovalSequence(approvalSequence);
	}

	/**
	* Sets the approval status of this cff approval details.
	*
	* @param approvalStatus the approval status of this cff approval details
	*/
	@Override
	public void setApprovalStatus(int approvalStatus) {
		_cffApprovalDetails.setApprovalStatus(approvalStatus);
	}

	/**
	* Sets the approved by of this cff approval details.
	*
	* @param approvedBy the approved by of this cff approval details
	*/
	@Override
	public void setApprovedBy(int approvedBy) {
		_cffApprovalDetails.setApprovedBy(approvedBy);
	}

	/**
	* Sets the approved date of this cff approval details.
	*
	* @param approvedDate the approved date of this cff approval details
	*/
	@Override
	public void setApprovedDate(Date approvedDate) {
		_cffApprovalDetails.setApprovedDate(approvedDate);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_cffApprovalDetails.setCachedModel(cachedModel);
	}

	/**
	* Sets the cff approval details sid of this cff approval details.
	*
	* @param cffApprovalDetailsSid the cff approval details sid of this cff approval details
	*/
	@Override
	public void setCffApprovalDetailsSid(int cffApprovalDetailsSid) {
		_cffApprovalDetails.setCffApprovalDetailsSid(cffApprovalDetailsSid);
	}

	/**
	* Sets the cff master sid of this cff approval details.
	*
	* @param cffMasterSid the cff master sid of this cff approval details
	*/
	@Override
	public void setCffMasterSid(int cffMasterSid) {
		_cffApprovalDetails.setCffMasterSid(cffMasterSid);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_cffApprovalDetails.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_cffApprovalDetails.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_cffApprovalDetails.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the inbound status of this cff approval details.
	*
	* @param inboundStatus the inbound status of this cff approval details
	*/
	@Override
	public void setInboundStatus(java.lang.String inboundStatus) {
		_cffApprovalDetails.setInboundStatus(inboundStatus);
	}

	@Override
	public void setNew(boolean n) {
		_cffApprovalDetails.setNew(n);
	}

	/**
	* Sets the primary key of this cff approval details.
	*
	* @param primaryKey the primary key of this cff approval details
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_cffApprovalDetails.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_cffApprovalDetails.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<CffApprovalDetails> toCacheModel() {
		return _cffApprovalDetails.toCacheModel();
	}

	@Override
	public CffApprovalDetails toEscapedModel() {
		return new CffApprovalDetailsWrapper(_cffApprovalDetails.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _cffApprovalDetails.toString();
	}

	@Override
	public CffApprovalDetails toUnescapedModel() {
		return new CffApprovalDetailsWrapper(_cffApprovalDetails.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _cffApprovalDetails.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CffApprovalDetailsWrapper)) {
			return false;
		}

		CffApprovalDetailsWrapper cffApprovalDetailsWrapper = (CffApprovalDetailsWrapper)obj;

		if (Objects.equals(_cffApprovalDetails,
					cffApprovalDetailsWrapper._cffApprovalDetails)) {
			return true;
		}

		return false;
	}

	@Override
	public CffApprovalDetails getWrappedModel() {
		return _cffApprovalDetails;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _cffApprovalDetails.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _cffApprovalDetails.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_cffApprovalDetails.resetOriginalValues();
	}

	private final CffApprovalDetails _cffApprovalDetails;
}