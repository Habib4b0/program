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
 * This class is a wrapper for {@link NetSalesFormulaMaster}.
 * </p>
 *
 * @author
 * @see NetSalesFormulaMaster
 * @generated
 */
@ProviderType
public class NetSalesFormulaMasterWrapper implements NetSalesFormulaMaster,
	ModelWrapper<NetSalesFormulaMaster> {
	public NetSalesFormulaMasterWrapper(
		NetSalesFormulaMaster netSalesFormulaMaster) {
		_netSalesFormulaMaster = netSalesFormulaMaster;
	}

	@Override
	public Class<?> getModelClass() {
		return NetSalesFormulaMaster.class;
	}

	@Override
	public String getModelClassName() {
		return NetSalesFormulaMaster.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("createdBy", getCreatedBy());
		attributes.put("netSalesFormulaMasterSid", getNetSalesFormulaMasterSid());
		attributes.put("modifiedBy", getModifiedBy());
		attributes.put("createdDate", getCreatedDate());
		attributes.put("netSalesFormulaName", getNetSalesFormulaName());
		attributes.put("netSalesFormulaType", getNetSalesFormulaType());
		attributes.put("netSalesFormulaCategory", getNetSalesFormulaCategory());
		attributes.put("contractSelection", getContractSelection());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("recordLockStatus", getRecordLockStatus());
		attributes.put("source", getSource());
		attributes.put("netSalesFormulaId", getNetSalesFormulaId());
		attributes.put("netSalesFormulaNo", getNetSalesFormulaNo());
		attributes.put("inboundStatus", getInboundStatus());
		attributes.put("cdrModelSid", getCdrModelSid());

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

		Integer modifiedBy = (Integer)attributes.get("modifiedBy");

		if (modifiedBy != null) {
			setModifiedBy(modifiedBy);
		}

		Date createdDate = (Date)attributes.get("createdDate");

		if (createdDate != null) {
			setCreatedDate(createdDate);
		}

		String netSalesFormulaName = (String)attributes.get(
				"netSalesFormulaName");

		if (netSalesFormulaName != null) {
			setNetSalesFormulaName(netSalesFormulaName);
		}

		Integer netSalesFormulaType = (Integer)attributes.get(
				"netSalesFormulaType");

		if (netSalesFormulaType != null) {
			setNetSalesFormulaType(netSalesFormulaType);
		}

		Integer netSalesFormulaCategory = (Integer)attributes.get(
				"netSalesFormulaCategory");

		if (netSalesFormulaCategory != null) {
			setNetSalesFormulaCategory(netSalesFormulaCategory);
		}

		String contractSelection = (String)attributes.get("contractSelection");

		if (contractSelection != null) {
			setContractSelection(contractSelection);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Boolean recordLockStatus = (Boolean)attributes.get("recordLockStatus");

		if (recordLockStatus != null) {
			setRecordLockStatus(recordLockStatus);
		}

		String source = (String)attributes.get("source");

		if (source != null) {
			setSource(source);
		}

		String netSalesFormulaId = (String)attributes.get("netSalesFormulaId");

		if (netSalesFormulaId != null) {
			setNetSalesFormulaId(netSalesFormulaId);
		}

		String netSalesFormulaNo = (String)attributes.get("netSalesFormulaNo");

		if (netSalesFormulaNo != null) {
			setNetSalesFormulaNo(netSalesFormulaNo);
		}

		String inboundStatus = (String)attributes.get("inboundStatus");

		if (inboundStatus != null) {
			setInboundStatus(inboundStatus);
		}

		String cdrModelSid = (String)attributes.get("cdrModelSid");

		if (cdrModelSid != null) {
			setCdrModelSid(cdrModelSid);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new NetSalesFormulaMasterWrapper((NetSalesFormulaMaster)_netSalesFormulaMaster.clone());
	}

	@Override
	public int compareTo(NetSalesFormulaMaster netSalesFormulaMaster) {
		return _netSalesFormulaMaster.compareTo(netSalesFormulaMaster);
	}

	/**
	* Returns the cdr model sid of this net sales formula master.
	*
	* @return the cdr model sid of this net sales formula master
	*/
	@Override
	public java.lang.String getCdrModelSid() {
		return _netSalesFormulaMaster.getCdrModelSid();
	}

	/**
	* Returns the contract selection of this net sales formula master.
	*
	* @return the contract selection of this net sales formula master
	*/
	@Override
	public java.lang.String getContractSelection() {
		return _netSalesFormulaMaster.getContractSelection();
	}

	/**
	* Returns the created by of this net sales formula master.
	*
	* @return the created by of this net sales formula master
	*/
	@Override
	public int getCreatedBy() {
		return _netSalesFormulaMaster.getCreatedBy();
	}

	/**
	* Returns the created date of this net sales formula master.
	*
	* @return the created date of this net sales formula master
	*/
	@Override
	public Date getCreatedDate() {
		return _netSalesFormulaMaster.getCreatedDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _netSalesFormulaMaster.getExpandoBridge();
	}

	/**
	* Returns the inbound status of this net sales formula master.
	*
	* @return the inbound status of this net sales formula master
	*/
	@Override
	public java.lang.String getInboundStatus() {
		return _netSalesFormulaMaster.getInboundStatus();
	}

	/**
	* Returns the modified by of this net sales formula master.
	*
	* @return the modified by of this net sales formula master
	*/
	@Override
	public int getModifiedBy() {
		return _netSalesFormulaMaster.getModifiedBy();
	}

	/**
	* Returns the modified date of this net sales formula master.
	*
	* @return the modified date of this net sales formula master
	*/
	@Override
	public Date getModifiedDate() {
		return _netSalesFormulaMaster.getModifiedDate();
	}

	/**
	* Returns the net sales formula category of this net sales formula master.
	*
	* @return the net sales formula category of this net sales formula master
	*/
	@Override
	public int getNetSalesFormulaCategory() {
		return _netSalesFormulaMaster.getNetSalesFormulaCategory();
	}

	/**
	* Returns the net sales formula ID of this net sales formula master.
	*
	* @return the net sales formula ID of this net sales formula master
	*/
	@Override
	public java.lang.String getNetSalesFormulaId() {
		return _netSalesFormulaMaster.getNetSalesFormulaId();
	}

	/**
	* Returns the net sales formula master sid of this net sales formula master.
	*
	* @return the net sales formula master sid of this net sales formula master
	*/
	@Override
	public int getNetSalesFormulaMasterSid() {
		return _netSalesFormulaMaster.getNetSalesFormulaMasterSid();
	}

	/**
	* Returns the net sales formula name of this net sales formula master.
	*
	* @return the net sales formula name of this net sales formula master
	*/
	@Override
	public java.lang.String getNetSalesFormulaName() {
		return _netSalesFormulaMaster.getNetSalesFormulaName();
	}

	/**
	* Returns the net sales formula no of this net sales formula master.
	*
	* @return the net sales formula no of this net sales formula master
	*/
	@Override
	public java.lang.String getNetSalesFormulaNo() {
		return _netSalesFormulaMaster.getNetSalesFormulaNo();
	}

	/**
	* Returns the net sales formula type of this net sales formula master.
	*
	* @return the net sales formula type of this net sales formula master
	*/
	@Override
	public int getNetSalesFormulaType() {
		return _netSalesFormulaMaster.getNetSalesFormulaType();
	}

	/**
	* Returns the primary key of this net sales formula master.
	*
	* @return the primary key of this net sales formula master
	*/
	@Override
	public int getPrimaryKey() {
		return _netSalesFormulaMaster.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _netSalesFormulaMaster.getPrimaryKeyObj();
	}

	/**
	* Returns the record lock status of this net sales formula master.
	*
	* @return the record lock status of this net sales formula master
	*/
	@Override
	public boolean getRecordLockStatus() {
		return _netSalesFormulaMaster.getRecordLockStatus();
	}

	/**
	* Returns the source of this net sales formula master.
	*
	* @return the source of this net sales formula master
	*/
	@Override
	public java.lang.String getSource() {
		return _netSalesFormulaMaster.getSource();
	}

	@Override
	public int hashCode() {
		return _netSalesFormulaMaster.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _netSalesFormulaMaster.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _netSalesFormulaMaster.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _netSalesFormulaMaster.isNew();
	}

	/**
	* Returns <code>true</code> if this net sales formula master is record lock status.
	*
	* @return <code>true</code> if this net sales formula master is record lock status; <code>false</code> otherwise
	*/
	@Override
	public boolean isRecordLockStatus() {
		return _netSalesFormulaMaster.isRecordLockStatus();
	}

	@Override
	public void persist() {
		_netSalesFormulaMaster.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_netSalesFormulaMaster.setCachedModel(cachedModel);
	}

	/**
	* Sets the cdr model sid of this net sales formula master.
	*
	* @param cdrModelSid the cdr model sid of this net sales formula master
	*/
	@Override
	public void setCdrModelSid(java.lang.String cdrModelSid) {
		_netSalesFormulaMaster.setCdrModelSid(cdrModelSid);
	}

	/**
	* Sets the contract selection of this net sales formula master.
	*
	* @param contractSelection the contract selection of this net sales formula master
	*/
	@Override
	public void setContractSelection(java.lang.String contractSelection) {
		_netSalesFormulaMaster.setContractSelection(contractSelection);
	}

	/**
	* Sets the created by of this net sales formula master.
	*
	* @param createdBy the created by of this net sales formula master
	*/
	@Override
	public void setCreatedBy(int createdBy) {
		_netSalesFormulaMaster.setCreatedBy(createdBy);
	}

	/**
	* Sets the created date of this net sales formula master.
	*
	* @param createdDate the created date of this net sales formula master
	*/
	@Override
	public void setCreatedDate(Date createdDate) {
		_netSalesFormulaMaster.setCreatedDate(createdDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_netSalesFormulaMaster.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_netSalesFormulaMaster.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_netSalesFormulaMaster.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the inbound status of this net sales formula master.
	*
	* @param inboundStatus the inbound status of this net sales formula master
	*/
	@Override
	public void setInboundStatus(java.lang.String inboundStatus) {
		_netSalesFormulaMaster.setInboundStatus(inboundStatus);
	}

	/**
	* Sets the modified by of this net sales formula master.
	*
	* @param modifiedBy the modified by of this net sales formula master
	*/
	@Override
	public void setModifiedBy(int modifiedBy) {
		_netSalesFormulaMaster.setModifiedBy(modifiedBy);
	}

	/**
	* Sets the modified date of this net sales formula master.
	*
	* @param modifiedDate the modified date of this net sales formula master
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_netSalesFormulaMaster.setModifiedDate(modifiedDate);
	}

	/**
	* Sets the net sales formula category of this net sales formula master.
	*
	* @param netSalesFormulaCategory the net sales formula category of this net sales formula master
	*/
	@Override
	public void setNetSalesFormulaCategory(int netSalesFormulaCategory) {
		_netSalesFormulaMaster.setNetSalesFormulaCategory(netSalesFormulaCategory);
	}

	/**
	* Sets the net sales formula ID of this net sales formula master.
	*
	* @param netSalesFormulaId the net sales formula ID of this net sales formula master
	*/
	@Override
	public void setNetSalesFormulaId(java.lang.String netSalesFormulaId) {
		_netSalesFormulaMaster.setNetSalesFormulaId(netSalesFormulaId);
	}

	/**
	* Sets the net sales formula master sid of this net sales formula master.
	*
	* @param netSalesFormulaMasterSid the net sales formula master sid of this net sales formula master
	*/
	@Override
	public void setNetSalesFormulaMasterSid(int netSalesFormulaMasterSid) {
		_netSalesFormulaMaster.setNetSalesFormulaMasterSid(netSalesFormulaMasterSid);
	}

	/**
	* Sets the net sales formula name of this net sales formula master.
	*
	* @param netSalesFormulaName the net sales formula name of this net sales formula master
	*/
	@Override
	public void setNetSalesFormulaName(java.lang.String netSalesFormulaName) {
		_netSalesFormulaMaster.setNetSalesFormulaName(netSalesFormulaName);
	}

	/**
	* Sets the net sales formula no of this net sales formula master.
	*
	* @param netSalesFormulaNo the net sales formula no of this net sales formula master
	*/
	@Override
	public void setNetSalesFormulaNo(java.lang.String netSalesFormulaNo) {
		_netSalesFormulaMaster.setNetSalesFormulaNo(netSalesFormulaNo);
	}

	/**
	* Sets the net sales formula type of this net sales formula master.
	*
	* @param netSalesFormulaType the net sales formula type of this net sales formula master
	*/
	@Override
	public void setNetSalesFormulaType(int netSalesFormulaType) {
		_netSalesFormulaMaster.setNetSalesFormulaType(netSalesFormulaType);
	}

	@Override
	public void setNew(boolean n) {
		_netSalesFormulaMaster.setNew(n);
	}

	/**
	* Sets the primary key of this net sales formula master.
	*
	* @param primaryKey the primary key of this net sales formula master
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_netSalesFormulaMaster.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_netSalesFormulaMaster.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets whether this net sales formula master is record lock status.
	*
	* @param recordLockStatus the record lock status of this net sales formula master
	*/
	@Override
	public void setRecordLockStatus(boolean recordLockStatus) {
		_netSalesFormulaMaster.setRecordLockStatus(recordLockStatus);
	}

	/**
	* Sets the source of this net sales formula master.
	*
	* @param source the source of this net sales formula master
	*/
	@Override
	public void setSource(java.lang.String source) {
		_netSalesFormulaMaster.setSource(source);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<NetSalesFormulaMaster> toCacheModel() {
		return _netSalesFormulaMaster.toCacheModel();
	}

	@Override
	public NetSalesFormulaMaster toEscapedModel() {
		return new NetSalesFormulaMasterWrapper(_netSalesFormulaMaster.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _netSalesFormulaMaster.toString();
	}

	@Override
	public NetSalesFormulaMaster toUnescapedModel() {
		return new NetSalesFormulaMasterWrapper(_netSalesFormulaMaster.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _netSalesFormulaMaster.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof NetSalesFormulaMasterWrapper)) {
			return false;
		}

		NetSalesFormulaMasterWrapper netSalesFormulaMasterWrapper = (NetSalesFormulaMasterWrapper)obj;

		if (Objects.equals(_netSalesFormulaMaster,
					netSalesFormulaMasterWrapper._netSalesFormulaMaster)) {
			return true;
		}

		return false;
	}

	@Override
	public NetSalesFormulaMaster getWrappedModel() {
		return _netSalesFormulaMaster;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _netSalesFormulaMaster.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _netSalesFormulaMaster.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_netSalesFormulaMaster.resetOriginalValues();
	}

	private final NetSalesFormulaMaster _netSalesFormulaMaster;
}