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
 * This class is a wrapper for {@link PriceScheduleDetails}.
 * </p>
 *
 * @author
 * @see PriceScheduleDetails
 * @generated
 */
@ProviderType
public class PriceScheduleDetailsWrapper implements PriceScheduleDetails,
	ModelWrapper<PriceScheduleDetails> {
	public PriceScheduleDetailsWrapper(
		PriceScheduleDetails priceScheduleDetails) {
		_priceScheduleDetails = priceScheduleDetails;
	}

	@Override
	public Class<?> getModelClass() {
		return PriceScheduleDetails.class;
	}

	@Override
	public String getModelClassName() {
		return PriceScheduleDetails.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("price", getPrice());
		attributes.put("psDetailsSystemId", getPsDetailsSystemId());
		attributes.put("companyFamilyplanSystemId",
			getCompanyFamilyplanSystemId());
		attributes.put("itemSystemId", getItemSystemId());
		attributes.put("priceProtectionStartDate", getPriceProtectionStartDate());
		attributes.put("basePrice", getBasePrice());
		attributes.put("revisionDate", getRevisionDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("priceScheduleSystemId", getPriceScheduleSystemId());
		attributes.put("itemFamilyplanSystemId", getItemFamilyplanSystemId());
		attributes.put("priceTolerance", getPriceTolerance());
		attributes.put("createdBy", getCreatedBy());
		attributes.put("createdDate", getCreatedDate());
		attributes.put("suggestedPrice", getSuggestedPrice());
		attributes.put("inboundStatus", getInboundStatus());
		attributes.put("modifiedBy", getModifiedBy());
		attributes.put("contractPrice", getContractPrice());
		attributes.put("priceToleranceType", getPriceToleranceType());
		attributes.put("memberFamilyplanSystemId", getMemberFamilyplanSystemId());
		attributes.put("contractPriceEndDate", getContractPriceEndDate());
		attributes.put("contractPriceStartDate", getContractPriceStartDate());
		attributes.put("priceToleranceFrequency", getPriceToleranceFrequency());
		attributes.put("attachedDate", getAttachedDate());
		attributes.put("priceProtectionEndDate", getPriceProtectionEndDate());
		attributes.put("recordLockStatus", getRecordLockStatus());
		attributes.put("pricePlanId", getPricePlanId());
		attributes.put("priceType", getPriceType());
		attributes.put("batchId", getBatchId());
		attributes.put("priceToleranceInterval", getPriceToleranceInterval());
		attributes.put("priceRevision", getPriceRevision());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Double price = (Double)attributes.get("price");

		if (price != null) {
			setPrice(price);
		}

		Integer psDetailsSystemId = (Integer)attributes.get("psDetailsSystemId");

		if (psDetailsSystemId != null) {
			setPsDetailsSystemId(psDetailsSystemId);
		}

		Integer companyFamilyplanSystemId = (Integer)attributes.get(
				"companyFamilyplanSystemId");

		if (companyFamilyplanSystemId != null) {
			setCompanyFamilyplanSystemId(companyFamilyplanSystemId);
		}

		Integer itemSystemId = (Integer)attributes.get("itemSystemId");

		if (itemSystemId != null) {
			setItemSystemId(itemSystemId);
		}

		Date priceProtectionStartDate = (Date)attributes.get(
				"priceProtectionStartDate");

		if (priceProtectionStartDate != null) {
			setPriceProtectionStartDate(priceProtectionStartDate);
		}

		Double basePrice = (Double)attributes.get("basePrice");

		if (basePrice != null) {
			setBasePrice(basePrice);
		}

		Date revisionDate = (Date)attributes.get("revisionDate");

		if (revisionDate != null) {
			setRevisionDate(revisionDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Integer priceScheduleSystemId = (Integer)attributes.get(
				"priceScheduleSystemId");

		if (priceScheduleSystemId != null) {
			setPriceScheduleSystemId(priceScheduleSystemId);
		}

		Integer itemFamilyplanSystemId = (Integer)attributes.get(
				"itemFamilyplanSystemId");

		if (itemFamilyplanSystemId != null) {
			setItemFamilyplanSystemId(itemFamilyplanSystemId);
		}

		Double priceTolerance = (Double)attributes.get("priceTolerance");

		if (priceTolerance != null) {
			setPriceTolerance(priceTolerance);
		}

		String createdBy = (String)attributes.get("createdBy");

		if (createdBy != null) {
			setCreatedBy(createdBy);
		}

		Date createdDate = (Date)attributes.get("createdDate");

		if (createdDate != null) {
			setCreatedDate(createdDate);
		}

		Double suggestedPrice = (Double)attributes.get("suggestedPrice");

		if (suggestedPrice != null) {
			setSuggestedPrice(suggestedPrice);
		}

		String inboundStatus = (String)attributes.get("inboundStatus");

		if (inboundStatus != null) {
			setInboundStatus(inboundStatus);
		}

		String modifiedBy = (String)attributes.get("modifiedBy");

		if (modifiedBy != null) {
			setModifiedBy(modifiedBy);
		}

		Double contractPrice = (Double)attributes.get("contractPrice");

		if (contractPrice != null) {
			setContractPrice(contractPrice);
		}

		String priceToleranceType = (String)attributes.get("priceToleranceType");

		if (priceToleranceType != null) {
			setPriceToleranceType(priceToleranceType);
		}

		Integer memberFamilyplanSystemId = (Integer)attributes.get(
				"memberFamilyplanSystemId");

		if (memberFamilyplanSystemId != null) {
			setMemberFamilyplanSystemId(memberFamilyplanSystemId);
		}

		Date contractPriceEndDate = (Date)attributes.get("contractPriceEndDate");

		if (contractPriceEndDate != null) {
			setContractPriceEndDate(contractPriceEndDate);
		}

		Date contractPriceStartDate = (Date)attributes.get(
				"contractPriceStartDate");

		if (contractPriceStartDate != null) {
			setContractPriceStartDate(contractPriceStartDate);
		}

		String priceToleranceFrequency = (String)attributes.get(
				"priceToleranceFrequency");

		if (priceToleranceFrequency != null) {
			setPriceToleranceFrequency(priceToleranceFrequency);
		}

		Date attachedDate = (Date)attributes.get("attachedDate");

		if (attachedDate != null) {
			setAttachedDate(attachedDate);
		}

		Date priceProtectionEndDate = (Date)attributes.get(
				"priceProtectionEndDate");

		if (priceProtectionEndDate != null) {
			setPriceProtectionEndDate(priceProtectionEndDate);
		}

		String recordLockStatus = (String)attributes.get("recordLockStatus");

		if (recordLockStatus != null) {
			setRecordLockStatus(recordLockStatus);
		}

		String pricePlanId = (String)attributes.get("pricePlanId");

		if (pricePlanId != null) {
			setPricePlanId(pricePlanId);
		}

		String priceType = (String)attributes.get("priceType");

		if (priceType != null) {
			setPriceType(priceType);
		}

		String batchId = (String)attributes.get("batchId");

		if (batchId != null) {
			setBatchId(batchId);
		}

		Integer priceToleranceInterval = (Integer)attributes.get(
				"priceToleranceInterval");

		if (priceToleranceInterval != null) {
			setPriceToleranceInterval(priceToleranceInterval);
		}

		Double priceRevision = (Double)attributes.get("priceRevision");

		if (priceRevision != null) {
			setPriceRevision(priceRevision);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new PriceScheduleDetailsWrapper((PriceScheduleDetails)_priceScheduleDetails.clone());
	}

	@Override
	public int compareTo(PriceScheduleDetails priceScheduleDetails) {
		return _priceScheduleDetails.compareTo(priceScheduleDetails);
	}

	/**
	* Returns the attached date of this price schedule details.
	*
	* @return the attached date of this price schedule details
	*/
	@Override
	public Date getAttachedDate() {
		return _priceScheduleDetails.getAttachedDate();
	}

	/**
	* Returns the base price of this price schedule details.
	*
	* @return the base price of this price schedule details
	*/
	@Override
	public double getBasePrice() {
		return _priceScheduleDetails.getBasePrice();
	}

	/**
	* Returns the batch ID of this price schedule details.
	*
	* @return the batch ID of this price schedule details
	*/
	@Override
	public java.lang.String getBatchId() {
		return _priceScheduleDetails.getBatchId();
	}

	/**
	* Returns the company familyplan system ID of this price schedule details.
	*
	* @return the company familyplan system ID of this price schedule details
	*/
	@Override
	public int getCompanyFamilyplanSystemId() {
		return _priceScheduleDetails.getCompanyFamilyplanSystemId();
	}

	/**
	* Returns the contract price of this price schedule details.
	*
	* @return the contract price of this price schedule details
	*/
	@Override
	public double getContractPrice() {
		return _priceScheduleDetails.getContractPrice();
	}

	/**
	* Returns the contract price end date of this price schedule details.
	*
	* @return the contract price end date of this price schedule details
	*/
	@Override
	public Date getContractPriceEndDate() {
		return _priceScheduleDetails.getContractPriceEndDate();
	}

	/**
	* Returns the contract price start date of this price schedule details.
	*
	* @return the contract price start date of this price schedule details
	*/
	@Override
	public Date getContractPriceStartDate() {
		return _priceScheduleDetails.getContractPriceStartDate();
	}

	/**
	* Returns the created by of this price schedule details.
	*
	* @return the created by of this price schedule details
	*/
	@Override
	public java.lang.String getCreatedBy() {
		return _priceScheduleDetails.getCreatedBy();
	}

	/**
	* Returns the created date of this price schedule details.
	*
	* @return the created date of this price schedule details
	*/
	@Override
	public Date getCreatedDate() {
		return _priceScheduleDetails.getCreatedDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _priceScheduleDetails.getExpandoBridge();
	}

	/**
	* Returns the inbound status of this price schedule details.
	*
	* @return the inbound status of this price schedule details
	*/
	@Override
	public java.lang.String getInboundStatus() {
		return _priceScheduleDetails.getInboundStatus();
	}

	/**
	* Returns the item familyplan system ID of this price schedule details.
	*
	* @return the item familyplan system ID of this price schedule details
	*/
	@Override
	public int getItemFamilyplanSystemId() {
		return _priceScheduleDetails.getItemFamilyplanSystemId();
	}

	/**
	* Returns the item system ID of this price schedule details.
	*
	* @return the item system ID of this price schedule details
	*/
	@Override
	public int getItemSystemId() {
		return _priceScheduleDetails.getItemSystemId();
	}

	/**
	* Returns the member familyplan system ID of this price schedule details.
	*
	* @return the member familyplan system ID of this price schedule details
	*/
	@Override
	public int getMemberFamilyplanSystemId() {
		return _priceScheduleDetails.getMemberFamilyplanSystemId();
	}

	/**
	* Returns the modified by of this price schedule details.
	*
	* @return the modified by of this price schedule details
	*/
	@Override
	public java.lang.String getModifiedBy() {
		return _priceScheduleDetails.getModifiedBy();
	}

	/**
	* Returns the modified date of this price schedule details.
	*
	* @return the modified date of this price schedule details
	*/
	@Override
	public Date getModifiedDate() {
		return _priceScheduleDetails.getModifiedDate();
	}

	/**
	* Returns the price of this price schedule details.
	*
	* @return the price of this price schedule details
	*/
	@Override
	public double getPrice() {
		return _priceScheduleDetails.getPrice();
	}

	/**
	* Returns the price plan ID of this price schedule details.
	*
	* @return the price plan ID of this price schedule details
	*/
	@Override
	public java.lang.String getPricePlanId() {
		return _priceScheduleDetails.getPricePlanId();
	}

	/**
	* Returns the price protection end date of this price schedule details.
	*
	* @return the price protection end date of this price schedule details
	*/
	@Override
	public Date getPriceProtectionEndDate() {
		return _priceScheduleDetails.getPriceProtectionEndDate();
	}

	/**
	* Returns the price protection start date of this price schedule details.
	*
	* @return the price protection start date of this price schedule details
	*/
	@Override
	public Date getPriceProtectionStartDate() {
		return _priceScheduleDetails.getPriceProtectionStartDate();
	}

	/**
	* Returns the price revision of this price schedule details.
	*
	* @return the price revision of this price schedule details
	*/
	@Override
	public double getPriceRevision() {
		return _priceScheduleDetails.getPriceRevision();
	}

	/**
	* Returns the price schedule system ID of this price schedule details.
	*
	* @return the price schedule system ID of this price schedule details
	*/
	@Override
	public int getPriceScheduleSystemId() {
		return _priceScheduleDetails.getPriceScheduleSystemId();
	}

	/**
	* Returns the price tolerance of this price schedule details.
	*
	* @return the price tolerance of this price schedule details
	*/
	@Override
	public double getPriceTolerance() {
		return _priceScheduleDetails.getPriceTolerance();
	}

	/**
	* Returns the price tolerance frequency of this price schedule details.
	*
	* @return the price tolerance frequency of this price schedule details
	*/
	@Override
	public java.lang.String getPriceToleranceFrequency() {
		return _priceScheduleDetails.getPriceToleranceFrequency();
	}

	/**
	* Returns the price tolerance interval of this price schedule details.
	*
	* @return the price tolerance interval of this price schedule details
	*/
	@Override
	public int getPriceToleranceInterval() {
		return _priceScheduleDetails.getPriceToleranceInterval();
	}

	/**
	* Returns the price tolerance type of this price schedule details.
	*
	* @return the price tolerance type of this price schedule details
	*/
	@Override
	public java.lang.String getPriceToleranceType() {
		return _priceScheduleDetails.getPriceToleranceType();
	}

	/**
	* Returns the price type of this price schedule details.
	*
	* @return the price type of this price schedule details
	*/
	@Override
	public java.lang.String getPriceType() {
		return _priceScheduleDetails.getPriceType();
	}

	/**
	* Returns the primary key of this price schedule details.
	*
	* @return the primary key of this price schedule details
	*/
	@Override
	public int getPrimaryKey() {
		return _priceScheduleDetails.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _priceScheduleDetails.getPrimaryKeyObj();
	}

	/**
	* Returns the ps details system ID of this price schedule details.
	*
	* @return the ps details system ID of this price schedule details
	*/
	@Override
	public int getPsDetailsSystemId() {
		return _priceScheduleDetails.getPsDetailsSystemId();
	}

	/**
	* Returns the record lock status of this price schedule details.
	*
	* @return the record lock status of this price schedule details
	*/
	@Override
	public java.lang.String getRecordLockStatus() {
		return _priceScheduleDetails.getRecordLockStatus();
	}

	/**
	* Returns the revision date of this price schedule details.
	*
	* @return the revision date of this price schedule details
	*/
	@Override
	public Date getRevisionDate() {
		return _priceScheduleDetails.getRevisionDate();
	}

	/**
	* Returns the suggested price of this price schedule details.
	*
	* @return the suggested price of this price schedule details
	*/
	@Override
	public double getSuggestedPrice() {
		return _priceScheduleDetails.getSuggestedPrice();
	}

	@Override
	public int hashCode() {
		return _priceScheduleDetails.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _priceScheduleDetails.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _priceScheduleDetails.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _priceScheduleDetails.isNew();
	}

	@Override
	public void persist() {
		_priceScheduleDetails.persist();
	}

	/**
	* Sets the attached date of this price schedule details.
	*
	* @param attachedDate the attached date of this price schedule details
	*/
	@Override
	public void setAttachedDate(Date attachedDate) {
		_priceScheduleDetails.setAttachedDate(attachedDate);
	}

	/**
	* Sets the base price of this price schedule details.
	*
	* @param basePrice the base price of this price schedule details
	*/
	@Override
	public void setBasePrice(double basePrice) {
		_priceScheduleDetails.setBasePrice(basePrice);
	}

	/**
	* Sets the batch ID of this price schedule details.
	*
	* @param batchId the batch ID of this price schedule details
	*/
	@Override
	public void setBatchId(java.lang.String batchId) {
		_priceScheduleDetails.setBatchId(batchId);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_priceScheduleDetails.setCachedModel(cachedModel);
	}

	/**
	* Sets the company familyplan system ID of this price schedule details.
	*
	* @param companyFamilyplanSystemId the company familyplan system ID of this price schedule details
	*/
	@Override
	public void setCompanyFamilyplanSystemId(int companyFamilyplanSystemId) {
		_priceScheduleDetails.setCompanyFamilyplanSystemId(companyFamilyplanSystemId);
	}

	/**
	* Sets the contract price of this price schedule details.
	*
	* @param contractPrice the contract price of this price schedule details
	*/
	@Override
	public void setContractPrice(double contractPrice) {
		_priceScheduleDetails.setContractPrice(contractPrice);
	}

	/**
	* Sets the contract price end date of this price schedule details.
	*
	* @param contractPriceEndDate the contract price end date of this price schedule details
	*/
	@Override
	public void setContractPriceEndDate(Date contractPriceEndDate) {
		_priceScheduleDetails.setContractPriceEndDate(contractPriceEndDate);
	}

	/**
	* Sets the contract price start date of this price schedule details.
	*
	* @param contractPriceStartDate the contract price start date of this price schedule details
	*/
	@Override
	public void setContractPriceStartDate(Date contractPriceStartDate) {
		_priceScheduleDetails.setContractPriceStartDate(contractPriceStartDate);
	}

	/**
	* Sets the created by of this price schedule details.
	*
	* @param createdBy the created by of this price schedule details
	*/
	@Override
	public void setCreatedBy(java.lang.String createdBy) {
		_priceScheduleDetails.setCreatedBy(createdBy);
	}

	/**
	* Sets the created date of this price schedule details.
	*
	* @param createdDate the created date of this price schedule details
	*/
	@Override
	public void setCreatedDate(Date createdDate) {
		_priceScheduleDetails.setCreatedDate(createdDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_priceScheduleDetails.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_priceScheduleDetails.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_priceScheduleDetails.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the inbound status of this price schedule details.
	*
	* @param inboundStatus the inbound status of this price schedule details
	*/
	@Override
	public void setInboundStatus(java.lang.String inboundStatus) {
		_priceScheduleDetails.setInboundStatus(inboundStatus);
	}

	/**
	* Sets the item familyplan system ID of this price schedule details.
	*
	* @param itemFamilyplanSystemId the item familyplan system ID of this price schedule details
	*/
	@Override
	public void setItemFamilyplanSystemId(int itemFamilyplanSystemId) {
		_priceScheduleDetails.setItemFamilyplanSystemId(itemFamilyplanSystemId);
	}

	/**
	* Sets the item system ID of this price schedule details.
	*
	* @param itemSystemId the item system ID of this price schedule details
	*/
	@Override
	public void setItemSystemId(int itemSystemId) {
		_priceScheduleDetails.setItemSystemId(itemSystemId);
	}

	/**
	* Sets the member familyplan system ID of this price schedule details.
	*
	* @param memberFamilyplanSystemId the member familyplan system ID of this price schedule details
	*/
	@Override
	public void setMemberFamilyplanSystemId(int memberFamilyplanSystemId) {
		_priceScheduleDetails.setMemberFamilyplanSystemId(memberFamilyplanSystemId);
	}

	/**
	* Sets the modified by of this price schedule details.
	*
	* @param modifiedBy the modified by of this price schedule details
	*/
	@Override
	public void setModifiedBy(java.lang.String modifiedBy) {
		_priceScheduleDetails.setModifiedBy(modifiedBy);
	}

	/**
	* Sets the modified date of this price schedule details.
	*
	* @param modifiedDate the modified date of this price schedule details
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_priceScheduleDetails.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_priceScheduleDetails.setNew(n);
	}

	/**
	* Sets the price of this price schedule details.
	*
	* @param price the price of this price schedule details
	*/
	@Override
	public void setPrice(double price) {
		_priceScheduleDetails.setPrice(price);
	}

	/**
	* Sets the price plan ID of this price schedule details.
	*
	* @param pricePlanId the price plan ID of this price schedule details
	*/
	@Override
	public void setPricePlanId(java.lang.String pricePlanId) {
		_priceScheduleDetails.setPricePlanId(pricePlanId);
	}

	/**
	* Sets the price protection end date of this price schedule details.
	*
	* @param priceProtectionEndDate the price protection end date of this price schedule details
	*/
	@Override
	public void setPriceProtectionEndDate(Date priceProtectionEndDate) {
		_priceScheduleDetails.setPriceProtectionEndDate(priceProtectionEndDate);
	}

	/**
	* Sets the price protection start date of this price schedule details.
	*
	* @param priceProtectionStartDate the price protection start date of this price schedule details
	*/
	@Override
	public void setPriceProtectionStartDate(Date priceProtectionStartDate) {
		_priceScheduleDetails.setPriceProtectionStartDate(priceProtectionStartDate);
	}

	/**
	* Sets the price revision of this price schedule details.
	*
	* @param priceRevision the price revision of this price schedule details
	*/
	@Override
	public void setPriceRevision(double priceRevision) {
		_priceScheduleDetails.setPriceRevision(priceRevision);
	}

	/**
	* Sets the price schedule system ID of this price schedule details.
	*
	* @param priceScheduleSystemId the price schedule system ID of this price schedule details
	*/
	@Override
	public void setPriceScheduleSystemId(int priceScheduleSystemId) {
		_priceScheduleDetails.setPriceScheduleSystemId(priceScheduleSystemId);
	}

	/**
	* Sets the price tolerance of this price schedule details.
	*
	* @param priceTolerance the price tolerance of this price schedule details
	*/
	@Override
	public void setPriceTolerance(double priceTolerance) {
		_priceScheduleDetails.setPriceTolerance(priceTolerance);
	}

	/**
	* Sets the price tolerance frequency of this price schedule details.
	*
	* @param priceToleranceFrequency the price tolerance frequency of this price schedule details
	*/
	@Override
	public void setPriceToleranceFrequency(
		java.lang.String priceToleranceFrequency) {
		_priceScheduleDetails.setPriceToleranceFrequency(priceToleranceFrequency);
	}

	/**
	* Sets the price tolerance interval of this price schedule details.
	*
	* @param priceToleranceInterval the price tolerance interval of this price schedule details
	*/
	@Override
	public void setPriceToleranceInterval(int priceToleranceInterval) {
		_priceScheduleDetails.setPriceToleranceInterval(priceToleranceInterval);
	}

	/**
	* Sets the price tolerance type of this price schedule details.
	*
	* @param priceToleranceType the price tolerance type of this price schedule details
	*/
	@Override
	public void setPriceToleranceType(java.lang.String priceToleranceType) {
		_priceScheduleDetails.setPriceToleranceType(priceToleranceType);
	}

	/**
	* Sets the price type of this price schedule details.
	*
	* @param priceType the price type of this price schedule details
	*/
	@Override
	public void setPriceType(java.lang.String priceType) {
		_priceScheduleDetails.setPriceType(priceType);
	}

	/**
	* Sets the primary key of this price schedule details.
	*
	* @param primaryKey the primary key of this price schedule details
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_priceScheduleDetails.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_priceScheduleDetails.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the ps details system ID of this price schedule details.
	*
	* @param psDetailsSystemId the ps details system ID of this price schedule details
	*/
	@Override
	public void setPsDetailsSystemId(int psDetailsSystemId) {
		_priceScheduleDetails.setPsDetailsSystemId(psDetailsSystemId);
	}

	/**
	* Sets the record lock status of this price schedule details.
	*
	* @param recordLockStatus the record lock status of this price schedule details
	*/
	@Override
	public void setRecordLockStatus(java.lang.String recordLockStatus) {
		_priceScheduleDetails.setRecordLockStatus(recordLockStatus);
	}

	/**
	* Sets the revision date of this price schedule details.
	*
	* @param revisionDate the revision date of this price schedule details
	*/
	@Override
	public void setRevisionDate(Date revisionDate) {
		_priceScheduleDetails.setRevisionDate(revisionDate);
	}

	/**
	* Sets the suggested price of this price schedule details.
	*
	* @param suggestedPrice the suggested price of this price schedule details
	*/
	@Override
	public void setSuggestedPrice(double suggestedPrice) {
		_priceScheduleDetails.setSuggestedPrice(suggestedPrice);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<PriceScheduleDetails> toCacheModel() {
		return _priceScheduleDetails.toCacheModel();
	}

	@Override
	public PriceScheduleDetails toEscapedModel() {
		return new PriceScheduleDetailsWrapper(_priceScheduleDetails.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _priceScheduleDetails.toString();
	}

	@Override
	public PriceScheduleDetails toUnescapedModel() {
		return new PriceScheduleDetailsWrapper(_priceScheduleDetails.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _priceScheduleDetails.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof PriceScheduleDetailsWrapper)) {
			return false;
		}

		PriceScheduleDetailsWrapper priceScheduleDetailsWrapper = (PriceScheduleDetailsWrapper)obj;

		if (Objects.equals(_priceScheduleDetails,
					priceScheduleDetailsWrapper._priceScheduleDetails)) {
			return true;
		}

		return false;
	}

	@Override
	public PriceScheduleDetails getWrappedModel() {
		return _priceScheduleDetails;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _priceScheduleDetails.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _priceScheduleDetails.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_priceScheduleDetails.resetOriginalValues();
	}

	private final PriceScheduleDetails _priceScheduleDetails;
}