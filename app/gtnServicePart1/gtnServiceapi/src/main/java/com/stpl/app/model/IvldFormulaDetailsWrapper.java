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
 * This class is a wrapper for {@link IvldFormulaDetails}.
 * </p>
 *
 * @author
 * @see IvldFormulaDetails
 * @generated
 */
@ProviderType
public class IvldFormulaDetailsWrapper implements IvldFormulaDetails,
	ModelWrapper<IvldFormulaDetails> {
	public IvldFormulaDetailsWrapper(IvldFormulaDetails ivldFormulaDetails) {
		_ivldFormulaDetails = ivldFormulaDetails;
	}

	@Override
	public Class<?> getModelClass() {
		return IvldFormulaDetails.class;
	}

	@Override
	public String getModelClassName() {
		return IvldFormulaDetails.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("endDate", getEndDate());
		attributes.put("rebatePercent1", getRebatePercent1());
		attributes.put("itemId", getItemId());
		attributes.put("rebatePercent2", getRebatePercent2());
		attributes.put("formulaDesc", getFormulaDesc());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("rebatePercent3", getRebatePercent3());
		attributes.put("createdBy", getCreatedBy());
		attributes.put("createdDate", getCreatedDate());
		attributes.put("source", getSource());
		attributes.put("addChgDelIndicator", getAddChgDelIndicator());
		attributes.put("errorCode", getErrorCode());
		attributes.put("formulaId", getFormulaId());
		attributes.put("modifiedBy", getModifiedBy());
		attributes.put("intfInsertedDate", getIntfInsertedDate());
		attributes.put("reprocessedFlag", getReprocessedFlag());
		attributes.put("formulaDetailsIntfid", getFormulaDetailsIntfid());
		attributes.put("reasonForFailure", getReasonForFailure());
		attributes.put("contractPrice1", getContractPrice1());
		attributes.put("companyStringId", getCompanyStringId());
		attributes.put("contractPrice2", getContractPrice2());
		attributes.put("formulaNo", getFormulaNo());
		attributes.put("startDate", getStartDate());
		attributes.put("batchId", getBatchId());
		attributes.put("errorField", getErrorField());
		attributes.put("contractPrice3", getContractPrice3());
		attributes.put("ivldFormulaDetailsSid", getIvldFormulaDetailsSid());
		attributes.put("checkRecord", getCheckRecord());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String endDate = (String)attributes.get("endDate");

		if (endDate != null) {
			setEndDate(endDate);
		}

		String rebatePercent1 = (String)attributes.get("rebatePercent1");

		if (rebatePercent1 != null) {
			setRebatePercent1(rebatePercent1);
		}

		String itemId = (String)attributes.get("itemId");

		if (itemId != null) {
			setItemId(itemId);
		}

		String rebatePercent2 = (String)attributes.get("rebatePercent2");

		if (rebatePercent2 != null) {
			setRebatePercent2(rebatePercent2);
		}

		String formulaDesc = (String)attributes.get("formulaDesc");

		if (formulaDesc != null) {
			setFormulaDesc(formulaDesc);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String rebatePercent3 = (String)attributes.get("rebatePercent3");

		if (rebatePercent3 != null) {
			setRebatePercent3(rebatePercent3);
		}

		String createdBy = (String)attributes.get("createdBy");

		if (createdBy != null) {
			setCreatedBy(createdBy);
		}

		Date createdDate = (Date)attributes.get("createdDate");

		if (createdDate != null) {
			setCreatedDate(createdDate);
		}

		String source = (String)attributes.get("source");

		if (source != null) {
			setSource(source);
		}

		String addChgDelIndicator = (String)attributes.get("addChgDelIndicator");

		if (addChgDelIndicator != null) {
			setAddChgDelIndicator(addChgDelIndicator);
		}

		String errorCode = (String)attributes.get("errorCode");

		if (errorCode != null) {
			setErrorCode(errorCode);
		}

		String formulaId = (String)attributes.get("formulaId");

		if (formulaId != null) {
			setFormulaId(formulaId);
		}

		String modifiedBy = (String)attributes.get("modifiedBy");

		if (modifiedBy != null) {
			setModifiedBy(modifiedBy);
		}

		Date intfInsertedDate = (Date)attributes.get("intfInsertedDate");

		if (intfInsertedDate != null) {
			setIntfInsertedDate(intfInsertedDate);
		}

		String reprocessedFlag = (String)attributes.get("reprocessedFlag");

		if (reprocessedFlag != null) {
			setReprocessedFlag(reprocessedFlag);
		}

		String formulaDetailsIntfid = (String)attributes.get(
				"formulaDetailsIntfid");

		if (formulaDetailsIntfid != null) {
			setFormulaDetailsIntfid(formulaDetailsIntfid);
		}

		String reasonForFailure = (String)attributes.get("reasonForFailure");

		if (reasonForFailure != null) {
			setReasonForFailure(reasonForFailure);
		}

		String contractPrice1 = (String)attributes.get("contractPrice1");

		if (contractPrice1 != null) {
			setContractPrice1(contractPrice1);
		}

		String companyStringId = (String)attributes.get("companyStringId");

		if (companyStringId != null) {
			setCompanyStringId(companyStringId);
		}

		String contractPrice2 = (String)attributes.get("contractPrice2");

		if (contractPrice2 != null) {
			setContractPrice2(contractPrice2);
		}

		String formulaNo = (String)attributes.get("formulaNo");

		if (formulaNo != null) {
			setFormulaNo(formulaNo);
		}

		String startDate = (String)attributes.get("startDate");

		if (startDate != null) {
			setStartDate(startDate);
		}

		String batchId = (String)attributes.get("batchId");

		if (batchId != null) {
			setBatchId(batchId);
		}

		String errorField = (String)attributes.get("errorField");

		if (errorField != null) {
			setErrorField(errorField);
		}

		String contractPrice3 = (String)attributes.get("contractPrice3");

		if (contractPrice3 != null) {
			setContractPrice3(contractPrice3);
		}

		Integer ivldFormulaDetailsSid = (Integer)attributes.get(
				"ivldFormulaDetailsSid");

		if (ivldFormulaDetailsSid != null) {
			setIvldFormulaDetailsSid(ivldFormulaDetailsSid);
		}

		Boolean checkRecord = (Boolean)attributes.get("checkRecord");

		if (checkRecord != null) {
			setCheckRecord(checkRecord);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new IvldFormulaDetailsWrapper((IvldFormulaDetails)_ivldFormulaDetails.clone());
	}

	@Override
	public int compareTo(IvldFormulaDetails ivldFormulaDetails) {
		return _ivldFormulaDetails.compareTo(ivldFormulaDetails);
	}

	/**
	* Returns the add chg del indicator of this ivld formula details.
	*
	* @return the add chg del indicator of this ivld formula details
	*/
	@Override
	public java.lang.String getAddChgDelIndicator() {
		return _ivldFormulaDetails.getAddChgDelIndicator();
	}

	/**
	* Returns the batch ID of this ivld formula details.
	*
	* @return the batch ID of this ivld formula details
	*/
	@Override
	public java.lang.String getBatchId() {
		return _ivldFormulaDetails.getBatchId();
	}

	/**
	* Returns the check record of this ivld formula details.
	*
	* @return the check record of this ivld formula details
	*/
	@Override
	public boolean getCheckRecord() {
		return _ivldFormulaDetails.getCheckRecord();
	}

	/**
	* Returns the company string ID of this ivld formula details.
	*
	* @return the company string ID of this ivld formula details
	*/
	@Override
	public java.lang.String getCompanyStringId() {
		return _ivldFormulaDetails.getCompanyStringId();
	}

	/**
	* Returns the contract price1 of this ivld formula details.
	*
	* @return the contract price1 of this ivld formula details
	*/
	@Override
	public java.lang.String getContractPrice1() {
		return _ivldFormulaDetails.getContractPrice1();
	}

	/**
	* Returns the contract price2 of this ivld formula details.
	*
	* @return the contract price2 of this ivld formula details
	*/
	@Override
	public java.lang.String getContractPrice2() {
		return _ivldFormulaDetails.getContractPrice2();
	}

	/**
	* Returns the contract price3 of this ivld formula details.
	*
	* @return the contract price3 of this ivld formula details
	*/
	@Override
	public java.lang.String getContractPrice3() {
		return _ivldFormulaDetails.getContractPrice3();
	}

	/**
	* Returns the created by of this ivld formula details.
	*
	* @return the created by of this ivld formula details
	*/
	@Override
	public java.lang.String getCreatedBy() {
		return _ivldFormulaDetails.getCreatedBy();
	}

	/**
	* Returns the created date of this ivld formula details.
	*
	* @return the created date of this ivld formula details
	*/
	@Override
	public Date getCreatedDate() {
		return _ivldFormulaDetails.getCreatedDate();
	}

	/**
	* Returns the end date of this ivld formula details.
	*
	* @return the end date of this ivld formula details
	*/
	@Override
	public java.lang.String getEndDate() {
		return _ivldFormulaDetails.getEndDate();
	}

	/**
	* Returns the error code of this ivld formula details.
	*
	* @return the error code of this ivld formula details
	*/
	@Override
	public java.lang.String getErrorCode() {
		return _ivldFormulaDetails.getErrorCode();
	}

	/**
	* Returns the error field of this ivld formula details.
	*
	* @return the error field of this ivld formula details
	*/
	@Override
	public java.lang.String getErrorField() {
		return _ivldFormulaDetails.getErrorField();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _ivldFormulaDetails.getExpandoBridge();
	}

	/**
	* Returns the formula desc of this ivld formula details.
	*
	* @return the formula desc of this ivld formula details
	*/
	@Override
	public java.lang.String getFormulaDesc() {
		return _ivldFormulaDetails.getFormulaDesc();
	}

	/**
	* Returns the formula details intfid of this ivld formula details.
	*
	* @return the formula details intfid of this ivld formula details
	*/
	@Override
	public java.lang.String getFormulaDetailsIntfid() {
		return _ivldFormulaDetails.getFormulaDetailsIntfid();
	}

	/**
	* Returns the formula ID of this ivld formula details.
	*
	* @return the formula ID of this ivld formula details
	*/
	@Override
	public java.lang.String getFormulaId() {
		return _ivldFormulaDetails.getFormulaId();
	}

	/**
	* Returns the formula no of this ivld formula details.
	*
	* @return the formula no of this ivld formula details
	*/
	@Override
	public java.lang.String getFormulaNo() {
		return _ivldFormulaDetails.getFormulaNo();
	}

	/**
	* Returns the intf inserted date of this ivld formula details.
	*
	* @return the intf inserted date of this ivld formula details
	*/
	@Override
	public Date getIntfInsertedDate() {
		return _ivldFormulaDetails.getIntfInsertedDate();
	}

	/**
	* Returns the item ID of this ivld formula details.
	*
	* @return the item ID of this ivld formula details
	*/
	@Override
	public java.lang.String getItemId() {
		return _ivldFormulaDetails.getItemId();
	}

	/**
	* Returns the ivld formula details sid of this ivld formula details.
	*
	* @return the ivld formula details sid of this ivld formula details
	*/
	@Override
	public int getIvldFormulaDetailsSid() {
		return _ivldFormulaDetails.getIvldFormulaDetailsSid();
	}

	/**
	* Returns the modified by of this ivld formula details.
	*
	* @return the modified by of this ivld formula details
	*/
	@Override
	public java.lang.String getModifiedBy() {
		return _ivldFormulaDetails.getModifiedBy();
	}

	/**
	* Returns the modified date of this ivld formula details.
	*
	* @return the modified date of this ivld formula details
	*/
	@Override
	public Date getModifiedDate() {
		return _ivldFormulaDetails.getModifiedDate();
	}

	/**
	* Returns the primary key of this ivld formula details.
	*
	* @return the primary key of this ivld formula details
	*/
	@Override
	public int getPrimaryKey() {
		return _ivldFormulaDetails.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _ivldFormulaDetails.getPrimaryKeyObj();
	}

	/**
	* Returns the reason for failure of this ivld formula details.
	*
	* @return the reason for failure of this ivld formula details
	*/
	@Override
	public java.lang.String getReasonForFailure() {
		return _ivldFormulaDetails.getReasonForFailure();
	}

	/**
	* Returns the rebate percent1 of this ivld formula details.
	*
	* @return the rebate percent1 of this ivld formula details
	*/
	@Override
	public java.lang.String getRebatePercent1() {
		return _ivldFormulaDetails.getRebatePercent1();
	}

	/**
	* Returns the rebate percent2 of this ivld formula details.
	*
	* @return the rebate percent2 of this ivld formula details
	*/
	@Override
	public java.lang.String getRebatePercent2() {
		return _ivldFormulaDetails.getRebatePercent2();
	}

	/**
	* Returns the rebate percent3 of this ivld formula details.
	*
	* @return the rebate percent3 of this ivld formula details
	*/
	@Override
	public java.lang.String getRebatePercent3() {
		return _ivldFormulaDetails.getRebatePercent3();
	}

	/**
	* Returns the reprocessed flag of this ivld formula details.
	*
	* @return the reprocessed flag of this ivld formula details
	*/
	@Override
	public java.lang.String getReprocessedFlag() {
		return _ivldFormulaDetails.getReprocessedFlag();
	}

	/**
	* Returns the source of this ivld formula details.
	*
	* @return the source of this ivld formula details
	*/
	@Override
	public java.lang.String getSource() {
		return _ivldFormulaDetails.getSource();
	}

	/**
	* Returns the start date of this ivld formula details.
	*
	* @return the start date of this ivld formula details
	*/
	@Override
	public java.lang.String getStartDate() {
		return _ivldFormulaDetails.getStartDate();
	}

	@Override
	public int hashCode() {
		return _ivldFormulaDetails.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _ivldFormulaDetails.isCachedModel();
	}

	/**
	* Returns <code>true</code> if this ivld formula details is check record.
	*
	* @return <code>true</code> if this ivld formula details is check record; <code>false</code> otherwise
	*/
	@Override
	public boolean isCheckRecord() {
		return _ivldFormulaDetails.isCheckRecord();
	}

	@Override
	public boolean isEscapedModel() {
		return _ivldFormulaDetails.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _ivldFormulaDetails.isNew();
	}

	@Override
	public void persist() {
		_ivldFormulaDetails.persist();
	}

	/**
	* Sets the add chg del indicator of this ivld formula details.
	*
	* @param addChgDelIndicator the add chg del indicator of this ivld formula details
	*/
	@Override
	public void setAddChgDelIndicator(java.lang.String addChgDelIndicator) {
		_ivldFormulaDetails.setAddChgDelIndicator(addChgDelIndicator);
	}

	/**
	* Sets the batch ID of this ivld formula details.
	*
	* @param batchId the batch ID of this ivld formula details
	*/
	@Override
	public void setBatchId(java.lang.String batchId) {
		_ivldFormulaDetails.setBatchId(batchId);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_ivldFormulaDetails.setCachedModel(cachedModel);
	}

	/**
	* Sets whether this ivld formula details is check record.
	*
	* @param checkRecord the check record of this ivld formula details
	*/
	@Override
	public void setCheckRecord(boolean checkRecord) {
		_ivldFormulaDetails.setCheckRecord(checkRecord);
	}

	/**
	* Sets the company string ID of this ivld formula details.
	*
	* @param companyStringId the company string ID of this ivld formula details
	*/
	@Override
	public void setCompanyStringId(java.lang.String companyStringId) {
		_ivldFormulaDetails.setCompanyStringId(companyStringId);
	}

	/**
	* Sets the contract price1 of this ivld formula details.
	*
	* @param contractPrice1 the contract price1 of this ivld formula details
	*/
	@Override
	public void setContractPrice1(java.lang.String contractPrice1) {
		_ivldFormulaDetails.setContractPrice1(contractPrice1);
	}

	/**
	* Sets the contract price2 of this ivld formula details.
	*
	* @param contractPrice2 the contract price2 of this ivld formula details
	*/
	@Override
	public void setContractPrice2(java.lang.String contractPrice2) {
		_ivldFormulaDetails.setContractPrice2(contractPrice2);
	}

	/**
	* Sets the contract price3 of this ivld formula details.
	*
	* @param contractPrice3 the contract price3 of this ivld formula details
	*/
	@Override
	public void setContractPrice3(java.lang.String contractPrice3) {
		_ivldFormulaDetails.setContractPrice3(contractPrice3);
	}

	/**
	* Sets the created by of this ivld formula details.
	*
	* @param createdBy the created by of this ivld formula details
	*/
	@Override
	public void setCreatedBy(java.lang.String createdBy) {
		_ivldFormulaDetails.setCreatedBy(createdBy);
	}

	/**
	* Sets the created date of this ivld formula details.
	*
	* @param createdDate the created date of this ivld formula details
	*/
	@Override
	public void setCreatedDate(Date createdDate) {
		_ivldFormulaDetails.setCreatedDate(createdDate);
	}

	/**
	* Sets the end date of this ivld formula details.
	*
	* @param endDate the end date of this ivld formula details
	*/
	@Override
	public void setEndDate(java.lang.String endDate) {
		_ivldFormulaDetails.setEndDate(endDate);
	}

	/**
	* Sets the error code of this ivld formula details.
	*
	* @param errorCode the error code of this ivld formula details
	*/
	@Override
	public void setErrorCode(java.lang.String errorCode) {
		_ivldFormulaDetails.setErrorCode(errorCode);
	}

	/**
	* Sets the error field of this ivld formula details.
	*
	* @param errorField the error field of this ivld formula details
	*/
	@Override
	public void setErrorField(java.lang.String errorField) {
		_ivldFormulaDetails.setErrorField(errorField);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_ivldFormulaDetails.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_ivldFormulaDetails.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_ivldFormulaDetails.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the formula desc of this ivld formula details.
	*
	* @param formulaDesc the formula desc of this ivld formula details
	*/
	@Override
	public void setFormulaDesc(java.lang.String formulaDesc) {
		_ivldFormulaDetails.setFormulaDesc(formulaDesc);
	}

	/**
	* Sets the formula details intfid of this ivld formula details.
	*
	* @param formulaDetailsIntfid the formula details intfid of this ivld formula details
	*/
	@Override
	public void setFormulaDetailsIntfid(java.lang.String formulaDetailsIntfid) {
		_ivldFormulaDetails.setFormulaDetailsIntfid(formulaDetailsIntfid);
	}

	/**
	* Sets the formula ID of this ivld formula details.
	*
	* @param formulaId the formula ID of this ivld formula details
	*/
	@Override
	public void setFormulaId(java.lang.String formulaId) {
		_ivldFormulaDetails.setFormulaId(formulaId);
	}

	/**
	* Sets the formula no of this ivld formula details.
	*
	* @param formulaNo the formula no of this ivld formula details
	*/
	@Override
	public void setFormulaNo(java.lang.String formulaNo) {
		_ivldFormulaDetails.setFormulaNo(formulaNo);
	}

	/**
	* Sets the intf inserted date of this ivld formula details.
	*
	* @param intfInsertedDate the intf inserted date of this ivld formula details
	*/
	@Override
	public void setIntfInsertedDate(Date intfInsertedDate) {
		_ivldFormulaDetails.setIntfInsertedDate(intfInsertedDate);
	}

	/**
	* Sets the item ID of this ivld formula details.
	*
	* @param itemId the item ID of this ivld formula details
	*/
	@Override
	public void setItemId(java.lang.String itemId) {
		_ivldFormulaDetails.setItemId(itemId);
	}

	/**
	* Sets the ivld formula details sid of this ivld formula details.
	*
	* @param ivldFormulaDetailsSid the ivld formula details sid of this ivld formula details
	*/
	@Override
	public void setIvldFormulaDetailsSid(int ivldFormulaDetailsSid) {
		_ivldFormulaDetails.setIvldFormulaDetailsSid(ivldFormulaDetailsSid);
	}

	/**
	* Sets the modified by of this ivld formula details.
	*
	* @param modifiedBy the modified by of this ivld formula details
	*/
	@Override
	public void setModifiedBy(java.lang.String modifiedBy) {
		_ivldFormulaDetails.setModifiedBy(modifiedBy);
	}

	/**
	* Sets the modified date of this ivld formula details.
	*
	* @param modifiedDate the modified date of this ivld formula details
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_ivldFormulaDetails.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_ivldFormulaDetails.setNew(n);
	}

	/**
	* Sets the primary key of this ivld formula details.
	*
	* @param primaryKey the primary key of this ivld formula details
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_ivldFormulaDetails.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_ivldFormulaDetails.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the reason for failure of this ivld formula details.
	*
	* @param reasonForFailure the reason for failure of this ivld formula details
	*/
	@Override
	public void setReasonForFailure(java.lang.String reasonForFailure) {
		_ivldFormulaDetails.setReasonForFailure(reasonForFailure);
	}

	/**
	* Sets the rebate percent1 of this ivld formula details.
	*
	* @param rebatePercent1 the rebate percent1 of this ivld formula details
	*/
	@Override
	public void setRebatePercent1(java.lang.String rebatePercent1) {
		_ivldFormulaDetails.setRebatePercent1(rebatePercent1);
	}

	/**
	* Sets the rebate percent2 of this ivld formula details.
	*
	* @param rebatePercent2 the rebate percent2 of this ivld formula details
	*/
	@Override
	public void setRebatePercent2(java.lang.String rebatePercent2) {
		_ivldFormulaDetails.setRebatePercent2(rebatePercent2);
	}

	/**
	* Sets the rebate percent3 of this ivld formula details.
	*
	* @param rebatePercent3 the rebate percent3 of this ivld formula details
	*/
	@Override
	public void setRebatePercent3(java.lang.String rebatePercent3) {
		_ivldFormulaDetails.setRebatePercent3(rebatePercent3);
	}

	/**
	* Sets the reprocessed flag of this ivld formula details.
	*
	* @param reprocessedFlag the reprocessed flag of this ivld formula details
	*/
	@Override
	public void setReprocessedFlag(java.lang.String reprocessedFlag) {
		_ivldFormulaDetails.setReprocessedFlag(reprocessedFlag);
	}

	/**
	* Sets the source of this ivld formula details.
	*
	* @param source the source of this ivld formula details
	*/
	@Override
	public void setSource(java.lang.String source) {
		_ivldFormulaDetails.setSource(source);
	}

	/**
	* Sets the start date of this ivld formula details.
	*
	* @param startDate the start date of this ivld formula details
	*/
	@Override
	public void setStartDate(java.lang.String startDate) {
		_ivldFormulaDetails.setStartDate(startDate);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<IvldFormulaDetails> toCacheModel() {
		return _ivldFormulaDetails.toCacheModel();
	}

	@Override
	public IvldFormulaDetails toEscapedModel() {
		return new IvldFormulaDetailsWrapper(_ivldFormulaDetails.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _ivldFormulaDetails.toString();
	}

	@Override
	public IvldFormulaDetails toUnescapedModel() {
		return new IvldFormulaDetailsWrapper(_ivldFormulaDetails.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _ivldFormulaDetails.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof IvldFormulaDetailsWrapper)) {
			return false;
		}

		IvldFormulaDetailsWrapper ivldFormulaDetailsWrapper = (IvldFormulaDetailsWrapper)obj;

		if (Objects.equals(_ivldFormulaDetails,
					ivldFormulaDetailsWrapper._ivldFormulaDetails)) {
			return true;
		}

		return false;
	}

	@Override
	public IvldFormulaDetails getWrappedModel() {
		return _ivldFormulaDetails;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _ivldFormulaDetails.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _ivldFormulaDetails.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_ivldFormulaDetails.resetOriginalValues();
	}

	private final IvldFormulaDetails _ivldFormulaDetails;
}