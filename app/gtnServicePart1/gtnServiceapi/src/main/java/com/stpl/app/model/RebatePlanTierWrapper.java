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
 * This class is a wrapper for {@link RebatePlanTier}.
 * </p>
 *
 * @author
 * @see RebatePlanTier
 * @generated
 */
@ProviderType
public class RebatePlanTierWrapper implements RebatePlanTier,
	ModelWrapper<RebatePlanTier> {
	public RebatePlanTierWrapper(RebatePlanTier rebatePlanTier) {
		_rebatePlanTier = rebatePlanTier;
	}

	@Override
	public Class<?> getModelClass() {
		return RebatePlanTier.class;
	}

	@Override
	public String getModelClassName() {
		return RebatePlanTier.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("tierValue", getTierValue());
		attributes.put("returnRateSid", getReturnRateSid());
		attributes.put("rebatePlanMasterSid", getRebatePlanMasterSid());
		attributes.put("rebatePlanTierSid", getRebatePlanTierSid());
		attributes.put("itemPricingQualifierSid", getItemPricingQualifierSid());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("tierTolerance", getTierTolerance());
		attributes.put("tierFrom", getTierFrom());
		attributes.put("tierOperator", getTierOperator());
		attributes.put("recordLockStatus", getRecordLockStatus());
		attributes.put("createdDate", getCreatedDate());
		attributes.put("createdBy", getCreatedBy());
		attributes.put("source", getSource());
		attributes.put("tierTo", getTierTo());
		attributes.put("batchId", getBatchId());
		attributes.put("rebatePlanTierId", getRebatePlanTierId());
		attributes.put("freeAmount", getFreeAmount());
		attributes.put("modifiedBy", getModifiedBy());
		attributes.put("inboundStatus", getInboundStatus());
		attributes.put("tierLevel", getTierLevel());
		attributes.put("formulaNo", getFormulaNo());
		attributes.put("formulaName", getFormulaName());
		attributes.put("secondaryRebatePlanNo", getSecondaryRebatePlanNo());
		attributes.put("secondaryRebatePlanName", getSecondaryRebatePlanName());
		attributes.put("secondaryRebatePlanSid", getSecondaryRebatePlanSid());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Double tierValue = (Double)attributes.get("tierValue");

		if (tierValue != null) {
			setTierValue(tierValue);
		}

		String returnRateSid = (String)attributes.get("returnRateSid");

		if (returnRateSid != null) {
			setReturnRateSid(returnRateSid);
		}

		Integer rebatePlanMasterSid = (Integer)attributes.get(
				"rebatePlanMasterSid");

		if (rebatePlanMasterSid != null) {
			setRebatePlanMasterSid(rebatePlanMasterSid);
		}

		Integer rebatePlanTierSid = (Integer)attributes.get("rebatePlanTierSid");

		if (rebatePlanTierSid != null) {
			setRebatePlanTierSid(rebatePlanTierSid);
		}

		String itemPricingQualifierSid = (String)attributes.get(
				"itemPricingQualifierSid");

		if (itemPricingQualifierSid != null) {
			setItemPricingQualifierSid(itemPricingQualifierSid);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Double tierTolerance = (Double)attributes.get("tierTolerance");

		if (tierTolerance != null) {
			setTierTolerance(tierTolerance);
		}

		String tierFrom = (String)attributes.get("tierFrom");

		if (tierFrom != null) {
			setTierFrom(tierFrom);
		}

		String tierOperator = (String)attributes.get("tierOperator");

		if (tierOperator != null) {
			setTierOperator(tierOperator);
		}

		Boolean recordLockStatus = (Boolean)attributes.get("recordLockStatus");

		if (recordLockStatus != null) {
			setRecordLockStatus(recordLockStatus);
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

		String tierTo = (String)attributes.get("tierTo");

		if (tierTo != null) {
			setTierTo(tierTo);
		}

		String batchId = (String)attributes.get("batchId");

		if (batchId != null) {
			setBatchId(batchId);
		}

		String rebatePlanTierId = (String)attributes.get("rebatePlanTierId");

		if (rebatePlanTierId != null) {
			setRebatePlanTierId(rebatePlanTierId);
		}

		Double freeAmount = (Double)attributes.get("freeAmount");

		if (freeAmount != null) {
			setFreeAmount(freeAmount);
		}

		Integer modifiedBy = (Integer)attributes.get("modifiedBy");

		if (modifiedBy != null) {
			setModifiedBy(modifiedBy);
		}

		String inboundStatus = (String)attributes.get("inboundStatus");

		if (inboundStatus != null) {
			setInboundStatus(inboundStatus);
		}

		String tierLevel = (String)attributes.get("tierLevel");

		if (tierLevel != null) {
			setTierLevel(tierLevel);
		}

		String formulaNo = (String)attributes.get("formulaNo");

		if (formulaNo != null) {
			setFormulaNo(formulaNo);
		}

		String formulaName = (String)attributes.get("formulaName");

		if (formulaName != null) {
			setFormulaName(formulaName);
		}

		String secondaryRebatePlanNo = (String)attributes.get(
				"secondaryRebatePlanNo");

		if (secondaryRebatePlanNo != null) {
			setSecondaryRebatePlanNo(secondaryRebatePlanNo);
		}

		String secondaryRebatePlanName = (String)attributes.get(
				"secondaryRebatePlanName");

		if (secondaryRebatePlanName != null) {
			setSecondaryRebatePlanName(secondaryRebatePlanName);
		}

		String secondaryRebatePlanSid = (String)attributes.get(
				"secondaryRebatePlanSid");

		if (secondaryRebatePlanSid != null) {
			setSecondaryRebatePlanSid(secondaryRebatePlanSid);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new RebatePlanTierWrapper((RebatePlanTier)_rebatePlanTier.clone());
	}

	@Override
	public int compareTo(RebatePlanTier rebatePlanTier) {
		return _rebatePlanTier.compareTo(rebatePlanTier);
	}

	/**
	* Returns the batch ID of this rebate plan tier.
	*
	* @return the batch ID of this rebate plan tier
	*/
	@Override
	public java.lang.String getBatchId() {
		return _rebatePlanTier.getBatchId();
	}

	/**
	* Returns the created by of this rebate plan tier.
	*
	* @return the created by of this rebate plan tier
	*/
	@Override
	public int getCreatedBy() {
		return _rebatePlanTier.getCreatedBy();
	}

	/**
	* Returns the created date of this rebate plan tier.
	*
	* @return the created date of this rebate plan tier
	*/
	@Override
	public Date getCreatedDate() {
		return _rebatePlanTier.getCreatedDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _rebatePlanTier.getExpandoBridge();
	}

	/**
	* Returns the formula name of this rebate plan tier.
	*
	* @return the formula name of this rebate plan tier
	*/
	@Override
	public java.lang.String getFormulaName() {
		return _rebatePlanTier.getFormulaName();
	}

	/**
	* Returns the formula no of this rebate plan tier.
	*
	* @return the formula no of this rebate plan tier
	*/
	@Override
	public java.lang.String getFormulaNo() {
		return _rebatePlanTier.getFormulaNo();
	}

	/**
	* Returns the free amount of this rebate plan tier.
	*
	* @return the free amount of this rebate plan tier
	*/
	@Override
	public double getFreeAmount() {
		return _rebatePlanTier.getFreeAmount();
	}

	/**
	* Returns the inbound status of this rebate plan tier.
	*
	* @return the inbound status of this rebate plan tier
	*/
	@Override
	public java.lang.String getInboundStatus() {
		return _rebatePlanTier.getInboundStatus();
	}

	/**
	* Returns the item pricing qualifier sid of this rebate plan tier.
	*
	* @return the item pricing qualifier sid of this rebate plan tier
	*/
	@Override
	public java.lang.String getItemPricingQualifierSid() {
		return _rebatePlanTier.getItemPricingQualifierSid();
	}

	/**
	* Returns the modified by of this rebate plan tier.
	*
	* @return the modified by of this rebate plan tier
	*/
	@Override
	public int getModifiedBy() {
		return _rebatePlanTier.getModifiedBy();
	}

	/**
	* Returns the modified date of this rebate plan tier.
	*
	* @return the modified date of this rebate plan tier
	*/
	@Override
	public Date getModifiedDate() {
		return _rebatePlanTier.getModifiedDate();
	}

	/**
	* Returns the primary key of this rebate plan tier.
	*
	* @return the primary key of this rebate plan tier
	*/
	@Override
	public int getPrimaryKey() {
		return _rebatePlanTier.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _rebatePlanTier.getPrimaryKeyObj();
	}

	/**
	* Returns the rebate plan master sid of this rebate plan tier.
	*
	* @return the rebate plan master sid of this rebate plan tier
	*/
	@Override
	public int getRebatePlanMasterSid() {
		return _rebatePlanTier.getRebatePlanMasterSid();
	}

	/**
	* Returns the rebate plan tier ID of this rebate plan tier.
	*
	* @return the rebate plan tier ID of this rebate plan tier
	*/
	@Override
	public java.lang.String getRebatePlanTierId() {
		return _rebatePlanTier.getRebatePlanTierId();
	}

	/**
	* Returns the rebate plan tier sid of this rebate plan tier.
	*
	* @return the rebate plan tier sid of this rebate plan tier
	*/
	@Override
	public int getRebatePlanTierSid() {
		return _rebatePlanTier.getRebatePlanTierSid();
	}

	/**
	* Returns the record lock status of this rebate plan tier.
	*
	* @return the record lock status of this rebate plan tier
	*/
	@Override
	public boolean getRecordLockStatus() {
		return _rebatePlanTier.getRecordLockStatus();
	}

	/**
	* Returns the return rate sid of this rebate plan tier.
	*
	* @return the return rate sid of this rebate plan tier
	*/
	@Override
	public java.lang.String getReturnRateSid() {
		return _rebatePlanTier.getReturnRateSid();
	}

	/**
	* Returns the secondary rebate plan name of this rebate plan tier.
	*
	* @return the secondary rebate plan name of this rebate plan tier
	*/
	@Override
	public java.lang.String getSecondaryRebatePlanName() {
		return _rebatePlanTier.getSecondaryRebatePlanName();
	}

	/**
	* Returns the secondary rebate plan no of this rebate plan tier.
	*
	* @return the secondary rebate plan no of this rebate plan tier
	*/
	@Override
	public java.lang.String getSecondaryRebatePlanNo() {
		return _rebatePlanTier.getSecondaryRebatePlanNo();
	}

	/**
	* Returns the secondary rebate plan sid of this rebate plan tier.
	*
	* @return the secondary rebate plan sid of this rebate plan tier
	*/
	@Override
	public java.lang.String getSecondaryRebatePlanSid() {
		return _rebatePlanTier.getSecondaryRebatePlanSid();
	}

	/**
	* Returns the source of this rebate plan tier.
	*
	* @return the source of this rebate plan tier
	*/
	@Override
	public java.lang.String getSource() {
		return _rebatePlanTier.getSource();
	}

	/**
	* Returns the tier from of this rebate plan tier.
	*
	* @return the tier from of this rebate plan tier
	*/
	@Override
	public java.lang.String getTierFrom() {
		return _rebatePlanTier.getTierFrom();
	}

	/**
	* Returns the tier level of this rebate plan tier.
	*
	* @return the tier level of this rebate plan tier
	*/
	@Override
	public java.lang.String getTierLevel() {
		return _rebatePlanTier.getTierLevel();
	}

	/**
	* Returns the tier operator of this rebate plan tier.
	*
	* @return the tier operator of this rebate plan tier
	*/
	@Override
	public java.lang.String getTierOperator() {
		return _rebatePlanTier.getTierOperator();
	}

	/**
	* Returns the tier to of this rebate plan tier.
	*
	* @return the tier to of this rebate plan tier
	*/
	@Override
	public java.lang.String getTierTo() {
		return _rebatePlanTier.getTierTo();
	}

	/**
	* Returns the tier tolerance of this rebate plan tier.
	*
	* @return the tier tolerance of this rebate plan tier
	*/
	@Override
	public double getTierTolerance() {
		return _rebatePlanTier.getTierTolerance();
	}

	/**
	* Returns the tier value of this rebate plan tier.
	*
	* @return the tier value of this rebate plan tier
	*/
	@Override
	public double getTierValue() {
		return _rebatePlanTier.getTierValue();
	}

	@Override
	public int hashCode() {
		return _rebatePlanTier.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _rebatePlanTier.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _rebatePlanTier.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _rebatePlanTier.isNew();
	}

	/**
	* Returns <code>true</code> if this rebate plan tier is record lock status.
	*
	* @return <code>true</code> if this rebate plan tier is record lock status; <code>false</code> otherwise
	*/
	@Override
	public boolean isRecordLockStatus() {
		return _rebatePlanTier.isRecordLockStatus();
	}

	@Override
	public void persist() {
		_rebatePlanTier.persist();
	}

	/**
	* Sets the batch ID of this rebate plan tier.
	*
	* @param batchId the batch ID of this rebate plan tier
	*/
	@Override
	public void setBatchId(java.lang.String batchId) {
		_rebatePlanTier.setBatchId(batchId);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_rebatePlanTier.setCachedModel(cachedModel);
	}

	/**
	* Sets the created by of this rebate plan tier.
	*
	* @param createdBy the created by of this rebate plan tier
	*/
	@Override
	public void setCreatedBy(int createdBy) {
		_rebatePlanTier.setCreatedBy(createdBy);
	}

	/**
	* Sets the created date of this rebate plan tier.
	*
	* @param createdDate the created date of this rebate plan tier
	*/
	@Override
	public void setCreatedDate(Date createdDate) {
		_rebatePlanTier.setCreatedDate(createdDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_rebatePlanTier.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_rebatePlanTier.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_rebatePlanTier.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the formula name of this rebate plan tier.
	*
	* @param formulaName the formula name of this rebate plan tier
	*/
	@Override
	public void setFormulaName(java.lang.String formulaName) {
		_rebatePlanTier.setFormulaName(formulaName);
	}

	/**
	* Sets the formula no of this rebate plan tier.
	*
	* @param formulaNo the formula no of this rebate plan tier
	*/
	@Override
	public void setFormulaNo(java.lang.String formulaNo) {
		_rebatePlanTier.setFormulaNo(formulaNo);
	}

	/**
	* Sets the free amount of this rebate plan tier.
	*
	* @param freeAmount the free amount of this rebate plan tier
	*/
	@Override
	public void setFreeAmount(double freeAmount) {
		_rebatePlanTier.setFreeAmount(freeAmount);
	}

	/**
	* Sets the inbound status of this rebate plan tier.
	*
	* @param inboundStatus the inbound status of this rebate plan tier
	*/
	@Override
	public void setInboundStatus(java.lang.String inboundStatus) {
		_rebatePlanTier.setInboundStatus(inboundStatus);
	}

	/**
	* Sets the item pricing qualifier sid of this rebate plan tier.
	*
	* @param itemPricingQualifierSid the item pricing qualifier sid of this rebate plan tier
	*/
	@Override
	public void setItemPricingQualifierSid(
		java.lang.String itemPricingQualifierSid) {
		_rebatePlanTier.setItemPricingQualifierSid(itemPricingQualifierSid);
	}

	/**
	* Sets the modified by of this rebate plan tier.
	*
	* @param modifiedBy the modified by of this rebate plan tier
	*/
	@Override
	public void setModifiedBy(int modifiedBy) {
		_rebatePlanTier.setModifiedBy(modifiedBy);
	}

	/**
	* Sets the modified date of this rebate plan tier.
	*
	* @param modifiedDate the modified date of this rebate plan tier
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_rebatePlanTier.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_rebatePlanTier.setNew(n);
	}

	/**
	* Sets the primary key of this rebate plan tier.
	*
	* @param primaryKey the primary key of this rebate plan tier
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_rebatePlanTier.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_rebatePlanTier.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the rebate plan master sid of this rebate plan tier.
	*
	* @param rebatePlanMasterSid the rebate plan master sid of this rebate plan tier
	*/
	@Override
	public void setRebatePlanMasterSid(int rebatePlanMasterSid) {
		_rebatePlanTier.setRebatePlanMasterSid(rebatePlanMasterSid);
	}

	/**
	* Sets the rebate plan tier ID of this rebate plan tier.
	*
	* @param rebatePlanTierId the rebate plan tier ID of this rebate plan tier
	*/
	@Override
	public void setRebatePlanTierId(java.lang.String rebatePlanTierId) {
		_rebatePlanTier.setRebatePlanTierId(rebatePlanTierId);
	}

	/**
	* Sets the rebate plan tier sid of this rebate plan tier.
	*
	* @param rebatePlanTierSid the rebate plan tier sid of this rebate plan tier
	*/
	@Override
	public void setRebatePlanTierSid(int rebatePlanTierSid) {
		_rebatePlanTier.setRebatePlanTierSid(rebatePlanTierSid);
	}

	/**
	* Sets whether this rebate plan tier is record lock status.
	*
	* @param recordLockStatus the record lock status of this rebate plan tier
	*/
	@Override
	public void setRecordLockStatus(boolean recordLockStatus) {
		_rebatePlanTier.setRecordLockStatus(recordLockStatus);
	}

	/**
	* Sets the return rate sid of this rebate plan tier.
	*
	* @param returnRateSid the return rate sid of this rebate plan tier
	*/
	@Override
	public void setReturnRateSid(java.lang.String returnRateSid) {
		_rebatePlanTier.setReturnRateSid(returnRateSid);
	}

	/**
	* Sets the secondary rebate plan name of this rebate plan tier.
	*
	* @param secondaryRebatePlanName the secondary rebate plan name of this rebate plan tier
	*/
	@Override
	public void setSecondaryRebatePlanName(
		java.lang.String secondaryRebatePlanName) {
		_rebatePlanTier.setSecondaryRebatePlanName(secondaryRebatePlanName);
	}

	/**
	* Sets the secondary rebate plan no of this rebate plan tier.
	*
	* @param secondaryRebatePlanNo the secondary rebate plan no of this rebate plan tier
	*/
	@Override
	public void setSecondaryRebatePlanNo(java.lang.String secondaryRebatePlanNo) {
		_rebatePlanTier.setSecondaryRebatePlanNo(secondaryRebatePlanNo);
	}

	/**
	* Sets the secondary rebate plan sid of this rebate plan tier.
	*
	* @param secondaryRebatePlanSid the secondary rebate plan sid of this rebate plan tier
	*/
	@Override
	public void setSecondaryRebatePlanSid(
		java.lang.String secondaryRebatePlanSid) {
		_rebatePlanTier.setSecondaryRebatePlanSid(secondaryRebatePlanSid);
	}

	/**
	* Sets the source of this rebate plan tier.
	*
	* @param source the source of this rebate plan tier
	*/
	@Override
	public void setSource(java.lang.String source) {
		_rebatePlanTier.setSource(source);
	}

	/**
	* Sets the tier from of this rebate plan tier.
	*
	* @param tierFrom the tier from of this rebate plan tier
	*/
	@Override
	public void setTierFrom(java.lang.String tierFrom) {
		_rebatePlanTier.setTierFrom(tierFrom);
	}

	/**
	* Sets the tier level of this rebate plan tier.
	*
	* @param tierLevel the tier level of this rebate plan tier
	*/
	@Override
	public void setTierLevel(java.lang.String tierLevel) {
		_rebatePlanTier.setTierLevel(tierLevel);
	}

	/**
	* Sets the tier operator of this rebate plan tier.
	*
	* @param tierOperator the tier operator of this rebate plan tier
	*/
	@Override
	public void setTierOperator(java.lang.String tierOperator) {
		_rebatePlanTier.setTierOperator(tierOperator);
	}

	/**
	* Sets the tier to of this rebate plan tier.
	*
	* @param tierTo the tier to of this rebate plan tier
	*/
	@Override
	public void setTierTo(java.lang.String tierTo) {
		_rebatePlanTier.setTierTo(tierTo);
	}

	/**
	* Sets the tier tolerance of this rebate plan tier.
	*
	* @param tierTolerance the tier tolerance of this rebate plan tier
	*/
	@Override
	public void setTierTolerance(double tierTolerance) {
		_rebatePlanTier.setTierTolerance(tierTolerance);
	}

	/**
	* Sets the tier value of this rebate plan tier.
	*
	* @param tierValue the tier value of this rebate plan tier
	*/
	@Override
	public void setTierValue(double tierValue) {
		_rebatePlanTier.setTierValue(tierValue);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<RebatePlanTier> toCacheModel() {
		return _rebatePlanTier.toCacheModel();
	}

	@Override
	public RebatePlanTier toEscapedModel() {
		return new RebatePlanTierWrapper(_rebatePlanTier.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _rebatePlanTier.toString();
	}

	@Override
	public RebatePlanTier toUnescapedModel() {
		return new RebatePlanTierWrapper(_rebatePlanTier.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _rebatePlanTier.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof RebatePlanTierWrapper)) {
			return false;
		}

		RebatePlanTierWrapper rebatePlanTierWrapper = (RebatePlanTierWrapper)obj;

		if (Objects.equals(_rebatePlanTier,
					rebatePlanTierWrapper._rebatePlanTier)) {
			return true;
		}

		return false;
	}

	@Override
	public RebatePlanTier getWrappedModel() {
		return _rebatePlanTier;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _rebatePlanTier.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _rebatePlanTier.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_rebatePlanTier.resetOriginalValues();
	}

	private final RebatePlanTier _rebatePlanTier;
}