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
 * This class is a wrapper for {@link FormulaDetailsMaster}.
 * </p>
 *
 * @author
 * @see FormulaDetailsMaster
 * @generated
 */
@ProviderType
public class FormulaDetailsMasterWrapper implements FormulaDetailsMaster,
	ModelWrapper<FormulaDetailsMaster> {
	public FormulaDetailsMasterWrapper(
		FormulaDetailsMaster formulaDetailsMaster) {
		_formulaDetailsMaster = formulaDetailsMaster;
	}

	@Override
	public Class<?> getModelClass() {
		return FormulaDetailsMaster.class;
	}

	@Override
	public String getModelClassName() {
		return FormulaDetailsMaster.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("companyStringId", getCompanyStringId());
		attributes.put("contractPrice1", getContractPrice1());
		attributes.put("contractPrice2", getContractPrice2());
		attributes.put("endDate", getEndDate());
		attributes.put("formulaNo", getFormulaNo());
		attributes.put("formulaDetailsMasterSid", getFormulaDetailsMasterSid());
		attributes.put("itemId", getItemId());
		attributes.put("rebatePercent1", getRebatePercent1());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("formulaDesc", getFormulaDesc());
		attributes.put("rebatePercent2", getRebatePercent2());
		attributes.put("rebatePercent3", getRebatePercent3());
		attributes.put("recordLockStatus", getRecordLockStatus());
		attributes.put("startDate", getStartDate());
		attributes.put("createdDate", getCreatedDate());
		attributes.put("createdBy", getCreatedBy());
		attributes.put("source", getSource());
		attributes.put("batchId", getBatchId());
		attributes.put("contractPrice3", getContractPrice3());
		attributes.put("modifiedBy", getModifiedBy());
		attributes.put("inboundStatus", getInboundStatus());
		attributes.put("formulaId", getFormulaId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String companyStringId = (String)attributes.get("companyStringId");

		if (companyStringId != null) {
			setCompanyStringId(companyStringId);
		}

		Double contractPrice1 = (Double)attributes.get("contractPrice1");

		if (contractPrice1 != null) {
			setContractPrice1(contractPrice1);
		}

		Double contractPrice2 = (Double)attributes.get("contractPrice2");

		if (contractPrice2 != null) {
			setContractPrice2(contractPrice2);
		}

		Date endDate = (Date)attributes.get("endDate");

		if (endDate != null) {
			setEndDate(endDate);
		}

		String formulaNo = (String)attributes.get("formulaNo");

		if (formulaNo != null) {
			setFormulaNo(formulaNo);
		}

		Integer formulaDetailsMasterSid = (Integer)attributes.get(
				"formulaDetailsMasterSid");

		if (formulaDetailsMasterSid != null) {
			setFormulaDetailsMasterSid(formulaDetailsMasterSid);
		}

		String itemId = (String)attributes.get("itemId");

		if (itemId != null) {
			setItemId(itemId);
		}

		Double rebatePercent1 = (Double)attributes.get("rebatePercent1");

		if (rebatePercent1 != null) {
			setRebatePercent1(rebatePercent1);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String formulaDesc = (String)attributes.get("formulaDesc");

		if (formulaDesc != null) {
			setFormulaDesc(formulaDesc);
		}

		Double rebatePercent2 = (Double)attributes.get("rebatePercent2");

		if (rebatePercent2 != null) {
			setRebatePercent2(rebatePercent2);
		}

		Double rebatePercent3 = (Double)attributes.get("rebatePercent3");

		if (rebatePercent3 != null) {
			setRebatePercent3(rebatePercent3);
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

		Integer createdBy = (Integer)attributes.get("createdBy");

		if (createdBy != null) {
			setCreatedBy(createdBy);
		}

		String source = (String)attributes.get("source");

		if (source != null) {
			setSource(source);
		}

		String batchId = (String)attributes.get("batchId");

		if (batchId != null) {
			setBatchId(batchId);
		}

		Double contractPrice3 = (Double)attributes.get("contractPrice3");

		if (contractPrice3 != null) {
			setContractPrice3(contractPrice3);
		}

		Integer modifiedBy = (Integer)attributes.get("modifiedBy");

		if (modifiedBy != null) {
			setModifiedBy(modifiedBy);
		}

		String inboundStatus = (String)attributes.get("inboundStatus");

		if (inboundStatus != null) {
			setInboundStatus(inboundStatus);
		}

		String formulaId = (String)attributes.get("formulaId");

		if (formulaId != null) {
			setFormulaId(formulaId);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new FormulaDetailsMasterWrapper((FormulaDetailsMaster)_formulaDetailsMaster.clone());
	}

	@Override
	public int compareTo(FormulaDetailsMaster formulaDetailsMaster) {
		return _formulaDetailsMaster.compareTo(formulaDetailsMaster);
	}

	/**
	* Returns the batch ID of this formula details master.
	*
	* @return the batch ID of this formula details master
	*/
	@Override
	public java.lang.String getBatchId() {
		return _formulaDetailsMaster.getBatchId();
	}

	/**
	* Returns the company string ID of this formula details master.
	*
	* @return the company string ID of this formula details master
	*/
	@Override
	public java.lang.String getCompanyStringId() {
		return _formulaDetailsMaster.getCompanyStringId();
	}

	/**
	* Returns the contract price1 of this formula details master.
	*
	* @return the contract price1 of this formula details master
	*/
	@Override
	public double getContractPrice1() {
		return _formulaDetailsMaster.getContractPrice1();
	}

	/**
	* Returns the contract price2 of this formula details master.
	*
	* @return the contract price2 of this formula details master
	*/
	@Override
	public double getContractPrice2() {
		return _formulaDetailsMaster.getContractPrice2();
	}

	/**
	* Returns the contract price3 of this formula details master.
	*
	* @return the contract price3 of this formula details master
	*/
	@Override
	public double getContractPrice3() {
		return _formulaDetailsMaster.getContractPrice3();
	}

	/**
	* Returns the created by of this formula details master.
	*
	* @return the created by of this formula details master
	*/
	@Override
	public int getCreatedBy() {
		return _formulaDetailsMaster.getCreatedBy();
	}

	/**
	* Returns the created date of this formula details master.
	*
	* @return the created date of this formula details master
	*/
	@Override
	public Date getCreatedDate() {
		return _formulaDetailsMaster.getCreatedDate();
	}

	/**
	* Returns the end date of this formula details master.
	*
	* @return the end date of this formula details master
	*/
	@Override
	public Date getEndDate() {
		return _formulaDetailsMaster.getEndDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _formulaDetailsMaster.getExpandoBridge();
	}

	/**
	* Returns the formula desc of this formula details master.
	*
	* @return the formula desc of this formula details master
	*/
	@Override
	public java.lang.String getFormulaDesc() {
		return _formulaDetailsMaster.getFormulaDesc();
	}

	/**
	* Returns the formula details master sid of this formula details master.
	*
	* @return the formula details master sid of this formula details master
	*/
	@Override
	public int getFormulaDetailsMasterSid() {
		return _formulaDetailsMaster.getFormulaDetailsMasterSid();
	}

	/**
	* Returns the formula ID of this formula details master.
	*
	* @return the formula ID of this formula details master
	*/
	@Override
	public java.lang.String getFormulaId() {
		return _formulaDetailsMaster.getFormulaId();
	}

	/**
	* Returns the formula no of this formula details master.
	*
	* @return the formula no of this formula details master
	*/
	@Override
	public java.lang.String getFormulaNo() {
		return _formulaDetailsMaster.getFormulaNo();
	}

	/**
	* Returns the inbound status of this formula details master.
	*
	* @return the inbound status of this formula details master
	*/
	@Override
	public java.lang.String getInboundStatus() {
		return _formulaDetailsMaster.getInboundStatus();
	}

	/**
	* Returns the item ID of this formula details master.
	*
	* @return the item ID of this formula details master
	*/
	@Override
	public java.lang.String getItemId() {
		return _formulaDetailsMaster.getItemId();
	}

	/**
	* Returns the modified by of this formula details master.
	*
	* @return the modified by of this formula details master
	*/
	@Override
	public int getModifiedBy() {
		return _formulaDetailsMaster.getModifiedBy();
	}

	/**
	* Returns the modified date of this formula details master.
	*
	* @return the modified date of this formula details master
	*/
	@Override
	public Date getModifiedDate() {
		return _formulaDetailsMaster.getModifiedDate();
	}

	/**
	* Returns the primary key of this formula details master.
	*
	* @return the primary key of this formula details master
	*/
	@Override
	public int getPrimaryKey() {
		return _formulaDetailsMaster.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _formulaDetailsMaster.getPrimaryKeyObj();
	}

	/**
	* Returns the rebate percent1 of this formula details master.
	*
	* @return the rebate percent1 of this formula details master
	*/
	@Override
	public double getRebatePercent1() {
		return _formulaDetailsMaster.getRebatePercent1();
	}

	/**
	* Returns the rebate percent2 of this formula details master.
	*
	* @return the rebate percent2 of this formula details master
	*/
	@Override
	public double getRebatePercent2() {
		return _formulaDetailsMaster.getRebatePercent2();
	}

	/**
	* Returns the rebate percent3 of this formula details master.
	*
	* @return the rebate percent3 of this formula details master
	*/
	@Override
	public double getRebatePercent3() {
		return _formulaDetailsMaster.getRebatePercent3();
	}

	/**
	* Returns the record lock status of this formula details master.
	*
	* @return the record lock status of this formula details master
	*/
	@Override
	public boolean getRecordLockStatus() {
		return _formulaDetailsMaster.getRecordLockStatus();
	}

	/**
	* Returns the source of this formula details master.
	*
	* @return the source of this formula details master
	*/
	@Override
	public java.lang.String getSource() {
		return _formulaDetailsMaster.getSource();
	}

	/**
	* Returns the start date of this formula details master.
	*
	* @return the start date of this formula details master
	*/
	@Override
	public Date getStartDate() {
		return _formulaDetailsMaster.getStartDate();
	}

	@Override
	public int hashCode() {
		return _formulaDetailsMaster.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _formulaDetailsMaster.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _formulaDetailsMaster.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _formulaDetailsMaster.isNew();
	}

	/**
	* Returns <code>true</code> if this formula details master is record lock status.
	*
	* @return <code>true</code> if this formula details master is record lock status; <code>false</code> otherwise
	*/
	@Override
	public boolean isRecordLockStatus() {
		return _formulaDetailsMaster.isRecordLockStatus();
	}

	@Override
	public void persist() {
		_formulaDetailsMaster.persist();
	}

	/**
	* Sets the batch ID of this formula details master.
	*
	* @param batchId the batch ID of this formula details master
	*/
	@Override
	public void setBatchId(java.lang.String batchId) {
		_formulaDetailsMaster.setBatchId(batchId);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_formulaDetailsMaster.setCachedModel(cachedModel);
	}

	/**
	* Sets the company string ID of this formula details master.
	*
	* @param companyStringId the company string ID of this formula details master
	*/
	@Override
	public void setCompanyStringId(java.lang.String companyStringId) {
		_formulaDetailsMaster.setCompanyStringId(companyStringId);
	}

	/**
	* Sets the contract price1 of this formula details master.
	*
	* @param contractPrice1 the contract price1 of this formula details master
	*/
	@Override
	public void setContractPrice1(double contractPrice1) {
		_formulaDetailsMaster.setContractPrice1(contractPrice1);
	}

	/**
	* Sets the contract price2 of this formula details master.
	*
	* @param contractPrice2 the contract price2 of this formula details master
	*/
	@Override
	public void setContractPrice2(double contractPrice2) {
		_formulaDetailsMaster.setContractPrice2(contractPrice2);
	}

	/**
	* Sets the contract price3 of this formula details master.
	*
	* @param contractPrice3 the contract price3 of this formula details master
	*/
	@Override
	public void setContractPrice3(double contractPrice3) {
		_formulaDetailsMaster.setContractPrice3(contractPrice3);
	}

	/**
	* Sets the created by of this formula details master.
	*
	* @param createdBy the created by of this formula details master
	*/
	@Override
	public void setCreatedBy(int createdBy) {
		_formulaDetailsMaster.setCreatedBy(createdBy);
	}

	/**
	* Sets the created date of this formula details master.
	*
	* @param createdDate the created date of this formula details master
	*/
	@Override
	public void setCreatedDate(Date createdDate) {
		_formulaDetailsMaster.setCreatedDate(createdDate);
	}

	/**
	* Sets the end date of this formula details master.
	*
	* @param endDate the end date of this formula details master
	*/
	@Override
	public void setEndDate(Date endDate) {
		_formulaDetailsMaster.setEndDate(endDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_formulaDetailsMaster.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_formulaDetailsMaster.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_formulaDetailsMaster.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the formula desc of this formula details master.
	*
	* @param formulaDesc the formula desc of this formula details master
	*/
	@Override
	public void setFormulaDesc(java.lang.String formulaDesc) {
		_formulaDetailsMaster.setFormulaDesc(formulaDesc);
	}

	/**
	* Sets the formula details master sid of this formula details master.
	*
	* @param formulaDetailsMasterSid the formula details master sid of this formula details master
	*/
	@Override
	public void setFormulaDetailsMasterSid(int formulaDetailsMasterSid) {
		_formulaDetailsMaster.setFormulaDetailsMasterSid(formulaDetailsMasterSid);
	}

	/**
	* Sets the formula ID of this formula details master.
	*
	* @param formulaId the formula ID of this formula details master
	*/
	@Override
	public void setFormulaId(java.lang.String formulaId) {
		_formulaDetailsMaster.setFormulaId(formulaId);
	}

	/**
	* Sets the formula no of this formula details master.
	*
	* @param formulaNo the formula no of this formula details master
	*/
	@Override
	public void setFormulaNo(java.lang.String formulaNo) {
		_formulaDetailsMaster.setFormulaNo(formulaNo);
	}

	/**
	* Sets the inbound status of this formula details master.
	*
	* @param inboundStatus the inbound status of this formula details master
	*/
	@Override
	public void setInboundStatus(java.lang.String inboundStatus) {
		_formulaDetailsMaster.setInboundStatus(inboundStatus);
	}

	/**
	* Sets the item ID of this formula details master.
	*
	* @param itemId the item ID of this formula details master
	*/
	@Override
	public void setItemId(java.lang.String itemId) {
		_formulaDetailsMaster.setItemId(itemId);
	}

	/**
	* Sets the modified by of this formula details master.
	*
	* @param modifiedBy the modified by of this formula details master
	*/
	@Override
	public void setModifiedBy(int modifiedBy) {
		_formulaDetailsMaster.setModifiedBy(modifiedBy);
	}

	/**
	* Sets the modified date of this formula details master.
	*
	* @param modifiedDate the modified date of this formula details master
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_formulaDetailsMaster.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_formulaDetailsMaster.setNew(n);
	}

	/**
	* Sets the primary key of this formula details master.
	*
	* @param primaryKey the primary key of this formula details master
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_formulaDetailsMaster.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_formulaDetailsMaster.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the rebate percent1 of this formula details master.
	*
	* @param rebatePercent1 the rebate percent1 of this formula details master
	*/
	@Override
	public void setRebatePercent1(double rebatePercent1) {
		_formulaDetailsMaster.setRebatePercent1(rebatePercent1);
	}

	/**
	* Sets the rebate percent2 of this formula details master.
	*
	* @param rebatePercent2 the rebate percent2 of this formula details master
	*/
	@Override
	public void setRebatePercent2(double rebatePercent2) {
		_formulaDetailsMaster.setRebatePercent2(rebatePercent2);
	}

	/**
	* Sets the rebate percent3 of this formula details master.
	*
	* @param rebatePercent3 the rebate percent3 of this formula details master
	*/
	@Override
	public void setRebatePercent3(double rebatePercent3) {
		_formulaDetailsMaster.setRebatePercent3(rebatePercent3);
	}

	/**
	* Sets whether this formula details master is record lock status.
	*
	* @param recordLockStatus the record lock status of this formula details master
	*/
	@Override
	public void setRecordLockStatus(boolean recordLockStatus) {
		_formulaDetailsMaster.setRecordLockStatus(recordLockStatus);
	}

	/**
	* Sets the source of this formula details master.
	*
	* @param source the source of this formula details master
	*/
	@Override
	public void setSource(java.lang.String source) {
		_formulaDetailsMaster.setSource(source);
	}

	/**
	* Sets the start date of this formula details master.
	*
	* @param startDate the start date of this formula details master
	*/
	@Override
	public void setStartDate(Date startDate) {
		_formulaDetailsMaster.setStartDate(startDate);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<FormulaDetailsMaster> toCacheModel() {
		return _formulaDetailsMaster.toCacheModel();
	}

	@Override
	public FormulaDetailsMaster toEscapedModel() {
		return new FormulaDetailsMasterWrapper(_formulaDetailsMaster.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _formulaDetailsMaster.toString();
	}

	@Override
	public FormulaDetailsMaster toUnescapedModel() {
		return new FormulaDetailsMasterWrapper(_formulaDetailsMaster.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _formulaDetailsMaster.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof FormulaDetailsMasterWrapper)) {
			return false;
		}

		FormulaDetailsMasterWrapper formulaDetailsMasterWrapper = (FormulaDetailsMasterWrapper)obj;

		if (Objects.equals(_formulaDetailsMaster,
					formulaDetailsMasterWrapper._formulaDetailsMaster)) {
			return true;
		}

		return false;
	}

	@Override
	public FormulaDetailsMaster getWrappedModel() {
		return _formulaDetailsMaster;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _formulaDetailsMaster.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _formulaDetailsMaster.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_formulaDetailsMaster.resetOriginalValues();
	}

	private final FormulaDetailsMaster _formulaDetailsMaster;
}