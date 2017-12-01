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
 * This class is a wrapper for {@link SalesBasisDetails}.
 * </p>
 *
 * @author
 * @see SalesBasisDetails
 * @generated
 */
@ProviderType
public class SalesBasisDetailsWrapper implements SalesBasisDetails,
	ModelWrapper<SalesBasisDetails> {
	public SalesBasisDetailsWrapper(SalesBasisDetails salesBasisDetails) {
		_salesBasisDetails = salesBasisDetails;
	}

	@Override
	public Class<?> getModelClass() {
		return SalesBasisDetails.class;
	}

	@Override
	public String getModelClassName() {
		return SalesBasisDetails.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("createdBy", getCreatedBy());
		attributes.put("netSalesFormulaMasterSid", getNetSalesFormulaMasterSid());
		attributes.put("recordLockStatus", getRecordLockStatus());
		attributes.put("modifiedBy", getModifiedBy());
		attributes.put("createdDate", getCreatedDate());
		attributes.put("contractMasterSid", getContractMasterSid());
		attributes.put("source", getSource());
		attributes.put("cdrModelSid", getCdrModelSid());
		attributes.put("salesBasisDetailsSid", getSalesBasisDetailsSid());
		attributes.put("cfpContractDetailsSid", getCfpContractDetailsSid());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("inboundStatus", getInboundStatus());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Integer createdBy = (Integer)attributes.get("createdBy");

		if (createdBy != null) {
			setCreatedBy(createdBy);
		}

		Integer netSalesFormulaMasterSid = (Integer)attributes.get(
				"netSalesFormulaMasterSid");

		if (netSalesFormulaMasterSid != null) {
			setNetSalesFormulaMasterSid(netSalesFormulaMasterSid);
		}

		Boolean recordLockStatus = (Boolean)attributes.get("recordLockStatus");

		if (recordLockStatus != null) {
			setRecordLockStatus(recordLockStatus);
		}

		Integer modifiedBy = (Integer)attributes.get("modifiedBy");

		if (modifiedBy != null) {
			setModifiedBy(modifiedBy);
		}

		Date createdDate = (Date)attributes.get("createdDate");

		if (createdDate != null) {
			setCreatedDate(createdDate);
		}

		Integer contractMasterSid = (Integer)attributes.get("contractMasterSid");

		if (contractMasterSid != null) {
			setContractMasterSid(contractMasterSid);
		}

		String source = (String)attributes.get("source");

		if (source != null) {
			setSource(source);
		}

		Integer cdrModelSid = (Integer)attributes.get("cdrModelSid");

		if (cdrModelSid != null) {
			setCdrModelSid(cdrModelSid);
		}

		Integer salesBasisDetailsSid = (Integer)attributes.get(
				"salesBasisDetailsSid");

		if (salesBasisDetailsSid != null) {
			setSalesBasisDetailsSid(salesBasisDetailsSid);
		}

		Integer cfpContractDetailsSid = (Integer)attributes.get(
				"cfpContractDetailsSid");

		if (cfpContractDetailsSid != null) {
			setCfpContractDetailsSid(cfpContractDetailsSid);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String inboundStatus = (String)attributes.get("inboundStatus");

		if (inboundStatus != null) {
			setInboundStatus(inboundStatus);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new SalesBasisDetailsWrapper((SalesBasisDetails)_salesBasisDetails.clone());
	}

	@Override
	public int compareTo(SalesBasisDetails salesBasisDetails) {
		return _salesBasisDetails.compareTo(salesBasisDetails);
	}

	/**
	* Returns the cdr model sid of this sales basis details.
	*
	* @return the cdr model sid of this sales basis details
	*/
	@Override
	public int getCdrModelSid() {
		return _salesBasisDetails.getCdrModelSid();
	}

	/**
	* Returns the cfp contract details sid of this sales basis details.
	*
	* @return the cfp contract details sid of this sales basis details
	*/
	@Override
	public int getCfpContractDetailsSid() {
		return _salesBasisDetails.getCfpContractDetailsSid();
	}

	/**
	* Returns the contract master sid of this sales basis details.
	*
	* @return the contract master sid of this sales basis details
	*/
	@Override
	public int getContractMasterSid() {
		return _salesBasisDetails.getContractMasterSid();
	}

	/**
	* Returns the created by of this sales basis details.
	*
	* @return the created by of this sales basis details
	*/
	@Override
	public int getCreatedBy() {
		return _salesBasisDetails.getCreatedBy();
	}

	/**
	* Returns the created date of this sales basis details.
	*
	* @return the created date of this sales basis details
	*/
	@Override
	public Date getCreatedDate() {
		return _salesBasisDetails.getCreatedDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _salesBasisDetails.getExpandoBridge();
	}

	/**
	* Returns the inbound status of this sales basis details.
	*
	* @return the inbound status of this sales basis details
	*/
	@Override
	public java.lang.String getInboundStatus() {
		return _salesBasisDetails.getInboundStatus();
	}

	/**
	* Returns the modified by of this sales basis details.
	*
	* @return the modified by of this sales basis details
	*/
	@Override
	public int getModifiedBy() {
		return _salesBasisDetails.getModifiedBy();
	}

	/**
	* Returns the modified date of this sales basis details.
	*
	* @return the modified date of this sales basis details
	*/
	@Override
	public Date getModifiedDate() {
		return _salesBasisDetails.getModifiedDate();
	}

	/**
	* Returns the net sales formula master sid of this sales basis details.
	*
	* @return the net sales formula master sid of this sales basis details
	*/
	@Override
	public int getNetSalesFormulaMasterSid() {
		return _salesBasisDetails.getNetSalesFormulaMasterSid();
	}

	/**
	* Returns the primary key of this sales basis details.
	*
	* @return the primary key of this sales basis details
	*/
	@Override
	public int getPrimaryKey() {
		return _salesBasisDetails.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _salesBasisDetails.getPrimaryKeyObj();
	}

	/**
	* Returns the record lock status of this sales basis details.
	*
	* @return the record lock status of this sales basis details
	*/
	@Override
	public boolean getRecordLockStatus() {
		return _salesBasisDetails.getRecordLockStatus();
	}

	/**
	* Returns the sales basis details sid of this sales basis details.
	*
	* @return the sales basis details sid of this sales basis details
	*/
	@Override
	public int getSalesBasisDetailsSid() {
		return _salesBasisDetails.getSalesBasisDetailsSid();
	}

	/**
	* Returns the source of this sales basis details.
	*
	* @return the source of this sales basis details
	*/
	@Override
	public java.lang.String getSource() {
		return _salesBasisDetails.getSource();
	}

	@Override
	public int hashCode() {
		return _salesBasisDetails.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _salesBasisDetails.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _salesBasisDetails.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _salesBasisDetails.isNew();
	}

	/**
	* Returns <code>true</code> if this sales basis details is record lock status.
	*
	* @return <code>true</code> if this sales basis details is record lock status; <code>false</code> otherwise
	*/
	@Override
	public boolean isRecordLockStatus() {
		return _salesBasisDetails.isRecordLockStatus();
	}

	@Override
	public void persist() {
		_salesBasisDetails.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_salesBasisDetails.setCachedModel(cachedModel);
	}

	/**
	* Sets the cdr model sid of this sales basis details.
	*
	* @param cdrModelSid the cdr model sid of this sales basis details
	*/
	@Override
	public void setCdrModelSid(int cdrModelSid) {
		_salesBasisDetails.setCdrModelSid(cdrModelSid);
	}

	/**
	* Sets the cfp contract details sid of this sales basis details.
	*
	* @param cfpContractDetailsSid the cfp contract details sid of this sales basis details
	*/
	@Override
	public void setCfpContractDetailsSid(int cfpContractDetailsSid) {
		_salesBasisDetails.setCfpContractDetailsSid(cfpContractDetailsSid);
	}

	/**
	* Sets the contract master sid of this sales basis details.
	*
	* @param contractMasterSid the contract master sid of this sales basis details
	*/
	@Override
	public void setContractMasterSid(int contractMasterSid) {
		_salesBasisDetails.setContractMasterSid(contractMasterSid);
	}

	/**
	* Sets the created by of this sales basis details.
	*
	* @param createdBy the created by of this sales basis details
	*/
	@Override
	public void setCreatedBy(int createdBy) {
		_salesBasisDetails.setCreatedBy(createdBy);
	}

	/**
	* Sets the created date of this sales basis details.
	*
	* @param createdDate the created date of this sales basis details
	*/
	@Override
	public void setCreatedDate(Date createdDate) {
		_salesBasisDetails.setCreatedDate(createdDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_salesBasisDetails.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_salesBasisDetails.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_salesBasisDetails.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the inbound status of this sales basis details.
	*
	* @param inboundStatus the inbound status of this sales basis details
	*/
	@Override
	public void setInboundStatus(java.lang.String inboundStatus) {
		_salesBasisDetails.setInboundStatus(inboundStatus);
	}

	/**
	* Sets the modified by of this sales basis details.
	*
	* @param modifiedBy the modified by of this sales basis details
	*/
	@Override
	public void setModifiedBy(int modifiedBy) {
		_salesBasisDetails.setModifiedBy(modifiedBy);
	}

	/**
	* Sets the modified date of this sales basis details.
	*
	* @param modifiedDate the modified date of this sales basis details
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_salesBasisDetails.setModifiedDate(modifiedDate);
	}

	/**
	* Sets the net sales formula master sid of this sales basis details.
	*
	* @param netSalesFormulaMasterSid the net sales formula master sid of this sales basis details
	*/
	@Override
	public void setNetSalesFormulaMasterSid(int netSalesFormulaMasterSid) {
		_salesBasisDetails.setNetSalesFormulaMasterSid(netSalesFormulaMasterSid);
	}

	@Override
	public void setNew(boolean n) {
		_salesBasisDetails.setNew(n);
	}

	/**
	* Sets the primary key of this sales basis details.
	*
	* @param primaryKey the primary key of this sales basis details
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_salesBasisDetails.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_salesBasisDetails.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets whether this sales basis details is record lock status.
	*
	* @param recordLockStatus the record lock status of this sales basis details
	*/
	@Override
	public void setRecordLockStatus(boolean recordLockStatus) {
		_salesBasisDetails.setRecordLockStatus(recordLockStatus);
	}

	/**
	* Sets the sales basis details sid of this sales basis details.
	*
	* @param salesBasisDetailsSid the sales basis details sid of this sales basis details
	*/
	@Override
	public void setSalesBasisDetailsSid(int salesBasisDetailsSid) {
		_salesBasisDetails.setSalesBasisDetailsSid(salesBasisDetailsSid);
	}

	/**
	* Sets the source of this sales basis details.
	*
	* @param source the source of this sales basis details
	*/
	@Override
	public void setSource(java.lang.String source) {
		_salesBasisDetails.setSource(source);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<SalesBasisDetails> toCacheModel() {
		return _salesBasisDetails.toCacheModel();
	}

	@Override
	public SalesBasisDetails toEscapedModel() {
		return new SalesBasisDetailsWrapper(_salesBasisDetails.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _salesBasisDetails.toString();
	}

	@Override
	public SalesBasisDetails toUnescapedModel() {
		return new SalesBasisDetailsWrapper(_salesBasisDetails.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _salesBasisDetails.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof SalesBasisDetailsWrapper)) {
			return false;
		}

		SalesBasisDetailsWrapper salesBasisDetailsWrapper = (SalesBasisDetailsWrapper)obj;

		if (Objects.equals(_salesBasisDetails,
					salesBasisDetailsWrapper._salesBasisDetails)) {
			return true;
		}

		return false;
	}

	@Override
	public SalesBasisDetails getWrappedModel() {
		return _salesBasisDetails;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _salesBasisDetails.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _salesBasisDetails.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_salesBasisDetails.resetOriginalValues();
	}

	private final SalesBasisDetails _salesBasisDetails;
}