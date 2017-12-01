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
 * This class is a wrapper for {@link ContractAliasMaster}.
 * </p>
 *
 * @author
 * @see ContractAliasMaster
 * @generated
 */
@ProviderType
public class ContractAliasMasterWrapper implements ContractAliasMaster,
	ModelWrapper<ContractAliasMaster> {
	public ContractAliasMasterWrapper(ContractAliasMaster contractAliasMaster) {
		_contractAliasMaster = contractAliasMaster;
	}

	@Override
	public Class<?> getModelClass() {
		return ContractAliasMaster.class;
	}

	@Override
	public String getModelClassName() {
		return ContractAliasMaster.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("contractAliasType", getContractAliasType());
		attributes.put("tpCompanyMasterSid", getTpCompanyMasterSid());
		attributes.put("endDate", getEndDate());
		attributes.put("contractAliasMasterSid", getContractAliasMasterSid());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("contractAliasNo", getContractAliasNo());
		attributes.put("recordLockStatus", getRecordLockStatus());
		attributes.put("startDate", getStartDate());
		attributes.put("createdDate", getCreatedDate());
		attributes.put("source", getSource());
		attributes.put("createdBy", getCreatedBy());
		attributes.put("contractMasterSid", getContractMasterSid());
		attributes.put("batchId", getBatchId());
		attributes.put("contractAliasName", getContractAliasName());
		attributes.put("modifiedBy", getModifiedBy());
		attributes.put("inboundStatus", getInboundStatus());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Integer contractAliasType = (Integer)attributes.get("contractAliasType");

		if (contractAliasType != null) {
			setContractAliasType(contractAliasType);
		}

		Integer tpCompanyMasterSid = (Integer)attributes.get(
				"tpCompanyMasterSid");

		if (tpCompanyMasterSid != null) {
			setTpCompanyMasterSid(tpCompanyMasterSid);
		}

		Date endDate = (Date)attributes.get("endDate");

		if (endDate != null) {
			setEndDate(endDate);
		}

		Integer contractAliasMasterSid = (Integer)attributes.get(
				"contractAliasMasterSid");

		if (contractAliasMasterSid != null) {
			setContractAliasMasterSid(contractAliasMasterSid);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String contractAliasNo = (String)attributes.get("contractAliasNo");

		if (contractAliasNo != null) {
			setContractAliasNo(contractAliasNo);
		}

		Boolean recordLockStatus = (Boolean)attributes.get("recordLockStatus");

		if (recordLockStatus != null) {
			setRecordLockStatus(recordLockStatus);
		}

		Date startDate = (Date)attributes.get("startDate");

		if (startDate != null) {
			setStartDate(startDate);
		}

		Date createdDate = (Date)attributes.get("createdDate");

		if (createdDate != null) {
			setCreatedDate(createdDate);
		}

		String source = (String)attributes.get("source");

		if (source != null) {
			setSource(source);
		}

		Integer createdBy = (Integer)attributes.get("createdBy");

		if (createdBy != null) {
			setCreatedBy(createdBy);
		}

		Integer contractMasterSid = (Integer)attributes.get("contractMasterSid");

		if (contractMasterSid != null) {
			setContractMasterSid(contractMasterSid);
		}

		String batchId = (String)attributes.get("batchId");

		if (batchId != null) {
			setBatchId(batchId);
		}

		String contractAliasName = (String)attributes.get("contractAliasName");

		if (contractAliasName != null) {
			setContractAliasName(contractAliasName);
		}

		Integer modifiedBy = (Integer)attributes.get("modifiedBy");

		if (modifiedBy != null) {
			setModifiedBy(modifiedBy);
		}

		String inboundStatus = (String)attributes.get("inboundStatus");

		if (inboundStatus != null) {
			setInboundStatus(inboundStatus);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new ContractAliasMasterWrapper((ContractAliasMaster)_contractAliasMaster.clone());
	}

	@Override
	public int compareTo(ContractAliasMaster contractAliasMaster) {
		return _contractAliasMaster.compareTo(contractAliasMaster);
	}

	/**
	* Returns the batch ID of this contract alias master.
	*
	* @return the batch ID of this contract alias master
	*/
	@Override
	public java.lang.String getBatchId() {
		return _contractAliasMaster.getBatchId();
	}

	/**
	* Returns the contract alias master sid of this contract alias master.
	*
	* @return the contract alias master sid of this contract alias master
	*/
	@Override
	public int getContractAliasMasterSid() {
		return _contractAliasMaster.getContractAliasMasterSid();
	}

	/**
	* Returns the contract alias name of this contract alias master.
	*
	* @return the contract alias name of this contract alias master
	*/
	@Override
	public java.lang.String getContractAliasName() {
		return _contractAliasMaster.getContractAliasName();
	}

	/**
	* Returns the contract alias no of this contract alias master.
	*
	* @return the contract alias no of this contract alias master
	*/
	@Override
	public java.lang.String getContractAliasNo() {
		return _contractAliasMaster.getContractAliasNo();
	}

	/**
	* Returns the contract alias type of this contract alias master.
	*
	* @return the contract alias type of this contract alias master
	*/
	@Override
	public int getContractAliasType() {
		return _contractAliasMaster.getContractAliasType();
	}

	/**
	* Returns the contract master sid of this contract alias master.
	*
	* @return the contract master sid of this contract alias master
	*/
	@Override
	public int getContractMasterSid() {
		return _contractAliasMaster.getContractMasterSid();
	}

	/**
	* Returns the created by of this contract alias master.
	*
	* @return the created by of this contract alias master
	*/
	@Override
	public int getCreatedBy() {
		return _contractAliasMaster.getCreatedBy();
	}

	/**
	* Returns the created date of this contract alias master.
	*
	* @return the created date of this contract alias master
	*/
	@Override
	public Date getCreatedDate() {
		return _contractAliasMaster.getCreatedDate();
	}

	/**
	* Returns the end date of this contract alias master.
	*
	* @return the end date of this contract alias master
	*/
	@Override
	public Date getEndDate() {
		return _contractAliasMaster.getEndDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _contractAliasMaster.getExpandoBridge();
	}

	/**
	* Returns the inbound status of this contract alias master.
	*
	* @return the inbound status of this contract alias master
	*/
	@Override
	public java.lang.String getInboundStatus() {
		return _contractAliasMaster.getInboundStatus();
	}

	/**
	* Returns the modified by of this contract alias master.
	*
	* @return the modified by of this contract alias master
	*/
	@Override
	public int getModifiedBy() {
		return _contractAliasMaster.getModifiedBy();
	}

	/**
	* Returns the modified date of this contract alias master.
	*
	* @return the modified date of this contract alias master
	*/
	@Override
	public Date getModifiedDate() {
		return _contractAliasMaster.getModifiedDate();
	}

	/**
	* Returns the primary key of this contract alias master.
	*
	* @return the primary key of this contract alias master
	*/
	@Override
	public int getPrimaryKey() {
		return _contractAliasMaster.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _contractAliasMaster.getPrimaryKeyObj();
	}

	/**
	* Returns the record lock status of this contract alias master.
	*
	* @return the record lock status of this contract alias master
	*/
	@Override
	public boolean getRecordLockStatus() {
		return _contractAliasMaster.getRecordLockStatus();
	}

	/**
	* Returns the source of this contract alias master.
	*
	* @return the source of this contract alias master
	*/
	@Override
	public java.lang.String getSource() {
		return _contractAliasMaster.getSource();
	}

	/**
	* Returns the start date of this contract alias master.
	*
	* @return the start date of this contract alias master
	*/
	@Override
	public Date getStartDate() {
		return _contractAliasMaster.getStartDate();
	}

	/**
	* Returns the tp company master sid of this contract alias master.
	*
	* @return the tp company master sid of this contract alias master
	*/
	@Override
	public int getTpCompanyMasterSid() {
		return _contractAliasMaster.getTpCompanyMasterSid();
	}

	@Override
	public int hashCode() {
		return _contractAliasMaster.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _contractAliasMaster.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _contractAliasMaster.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _contractAliasMaster.isNew();
	}

	/**
	* Returns <code>true</code> if this contract alias master is record lock status.
	*
	* @return <code>true</code> if this contract alias master is record lock status; <code>false</code> otherwise
	*/
	@Override
	public boolean isRecordLockStatus() {
		return _contractAliasMaster.isRecordLockStatus();
	}

	@Override
	public void persist() {
		_contractAliasMaster.persist();
	}

	/**
	* Sets the batch ID of this contract alias master.
	*
	* @param batchId the batch ID of this contract alias master
	*/
	@Override
	public void setBatchId(java.lang.String batchId) {
		_contractAliasMaster.setBatchId(batchId);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_contractAliasMaster.setCachedModel(cachedModel);
	}

	/**
	* Sets the contract alias master sid of this contract alias master.
	*
	* @param contractAliasMasterSid the contract alias master sid of this contract alias master
	*/
	@Override
	public void setContractAliasMasterSid(int contractAliasMasterSid) {
		_contractAliasMaster.setContractAliasMasterSid(contractAliasMasterSid);
	}

	/**
	* Sets the contract alias name of this contract alias master.
	*
	* @param contractAliasName the contract alias name of this contract alias master
	*/
	@Override
	public void setContractAliasName(java.lang.String contractAliasName) {
		_contractAliasMaster.setContractAliasName(contractAliasName);
	}

	/**
	* Sets the contract alias no of this contract alias master.
	*
	* @param contractAliasNo the contract alias no of this contract alias master
	*/
	@Override
	public void setContractAliasNo(java.lang.String contractAliasNo) {
		_contractAliasMaster.setContractAliasNo(contractAliasNo);
	}

	/**
	* Sets the contract alias type of this contract alias master.
	*
	* @param contractAliasType the contract alias type of this contract alias master
	*/
	@Override
	public void setContractAliasType(int contractAliasType) {
		_contractAliasMaster.setContractAliasType(contractAliasType);
	}

	/**
	* Sets the contract master sid of this contract alias master.
	*
	* @param contractMasterSid the contract master sid of this contract alias master
	*/
	@Override
	public void setContractMasterSid(int contractMasterSid) {
		_contractAliasMaster.setContractMasterSid(contractMasterSid);
	}

	/**
	* Sets the created by of this contract alias master.
	*
	* @param createdBy the created by of this contract alias master
	*/
	@Override
	public void setCreatedBy(int createdBy) {
		_contractAliasMaster.setCreatedBy(createdBy);
	}

	/**
	* Sets the created date of this contract alias master.
	*
	* @param createdDate the created date of this contract alias master
	*/
	@Override
	public void setCreatedDate(Date createdDate) {
		_contractAliasMaster.setCreatedDate(createdDate);
	}

	/**
	* Sets the end date of this contract alias master.
	*
	* @param endDate the end date of this contract alias master
	*/
	@Override
	public void setEndDate(Date endDate) {
		_contractAliasMaster.setEndDate(endDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_contractAliasMaster.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_contractAliasMaster.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_contractAliasMaster.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the inbound status of this contract alias master.
	*
	* @param inboundStatus the inbound status of this contract alias master
	*/
	@Override
	public void setInboundStatus(java.lang.String inboundStatus) {
		_contractAliasMaster.setInboundStatus(inboundStatus);
	}

	/**
	* Sets the modified by of this contract alias master.
	*
	* @param modifiedBy the modified by of this contract alias master
	*/
	@Override
	public void setModifiedBy(int modifiedBy) {
		_contractAliasMaster.setModifiedBy(modifiedBy);
	}

	/**
	* Sets the modified date of this contract alias master.
	*
	* @param modifiedDate the modified date of this contract alias master
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_contractAliasMaster.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_contractAliasMaster.setNew(n);
	}

	/**
	* Sets the primary key of this contract alias master.
	*
	* @param primaryKey the primary key of this contract alias master
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_contractAliasMaster.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_contractAliasMaster.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets whether this contract alias master is record lock status.
	*
	* @param recordLockStatus the record lock status of this contract alias master
	*/
	@Override
	public void setRecordLockStatus(boolean recordLockStatus) {
		_contractAliasMaster.setRecordLockStatus(recordLockStatus);
	}

	/**
	* Sets the source of this contract alias master.
	*
	* @param source the source of this contract alias master
	*/
	@Override
	public void setSource(java.lang.String source) {
		_contractAliasMaster.setSource(source);
	}

	/**
	* Sets the start date of this contract alias master.
	*
	* @param startDate the start date of this contract alias master
	*/
	@Override
	public void setStartDate(Date startDate) {
		_contractAliasMaster.setStartDate(startDate);
	}

	/**
	* Sets the tp company master sid of this contract alias master.
	*
	* @param tpCompanyMasterSid the tp company master sid of this contract alias master
	*/
	@Override
	public void setTpCompanyMasterSid(int tpCompanyMasterSid) {
		_contractAliasMaster.setTpCompanyMasterSid(tpCompanyMasterSid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<ContractAliasMaster> toCacheModel() {
		return _contractAliasMaster.toCacheModel();
	}

	@Override
	public ContractAliasMaster toEscapedModel() {
		return new ContractAliasMasterWrapper(_contractAliasMaster.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _contractAliasMaster.toString();
	}

	@Override
	public ContractAliasMaster toUnescapedModel() {
		return new ContractAliasMasterWrapper(_contractAliasMaster.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _contractAliasMaster.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ContractAliasMasterWrapper)) {
			return false;
		}

		ContractAliasMasterWrapper contractAliasMasterWrapper = (ContractAliasMasterWrapper)obj;

		if (Objects.equals(_contractAliasMaster,
					contractAliasMasterWrapper._contractAliasMaster)) {
			return true;
		}

		return false;
	}

	@Override
	public ContractAliasMaster getWrappedModel() {
		return _contractAliasMaster;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _contractAliasMaster.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _contractAliasMaster.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_contractAliasMaster.resetOriginalValues();
	}

	private final ContractAliasMaster _contractAliasMaster;
}