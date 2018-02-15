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
 * This class is a wrapper for {@link PsContractDetails}.
 * </p>
 *
 * @author
 * @see PsContractDetails
 * @generated
 */
@ProviderType
public class PsContractDetailsWrapper implements PsContractDetails,
	ModelWrapper<PsContractDetails> {
	public PsContractDetailsWrapper(PsContractDetails psContractDetails) {
		_psContractDetails = psContractDetails;
	}

	@Override
	public Class<?> getModelClass() {
		return PsContractDetails.class;
	}

	@Override
	public String getModelClassName() {
		return PsContractDetails.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("price", getPrice());
		attributes.put("itemMasterSid", getItemMasterSid());
		attributes.put("priceProtectionStartDate", getPriceProtectionStartDate());
		attributes.put("basePrice", getBasePrice());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("revisionDate", getRevisionDate());
		attributes.put("priceTolerance", getPriceTolerance());
		attributes.put("createdDate", getCreatedDate());
		attributes.put("source", getSource());
		attributes.put("createdBy", getCreatedBy());
		attributes.put("suggestedPrice", getSuggestedPrice());
		attributes.put("psContractAttachedDate", getPsContractAttachedDate());
		attributes.put("psContractDetailsSid", getPsContractDetailsSid());
		attributes.put("modifiedBy", getModifiedBy());
		attributes.put("inboundStatus", getInboundStatus());
		attributes.put("contractPrice", getContractPrice());
		attributes.put("priceToleranceType", getPriceToleranceType());
		attributes.put("itemPricingQualifierSid", getItemPricingQualifierSid());
		attributes.put("contractPriceEndDate", getContractPriceEndDate());
		attributes.put("priceToleranceFrequency", getPriceToleranceFrequency());
		attributes.put("contractPriceStartDate", getContractPriceStartDate());
		attributes.put("psContractSid", getPsContractSid());
		attributes.put("priceProtectionEndDate", getPriceProtectionEndDate());
		attributes.put("psContractAttachedStatus", getPsContractAttachedStatus());
		attributes.put("recordLockStatus", getRecordLockStatus());
		attributes.put("batchId", getBatchId());
		attributes.put("priceToleranceInterval", getPriceToleranceInterval());
		attributes.put("priceRevision", getPriceRevision());
		attributes.put("brandMasterSid", getBrandMasterSid());
		attributes.put("nep", getNep());
		attributes.put("priceProtectionStatus", getPriceProtectionStatus());
		attributes.put("priceProtectionPriceType", getPriceProtectionPriceType());
		attributes.put("nepFormula", getNepFormula());
		attributes.put("maxIncrementalChange", getMaxIncrementalChange());
		attributes.put("resetEligible", getResetEligible());
		attributes.put("resetType", getResetType());
		attributes.put("resetDate", getResetDate());
		attributes.put("resetInterval", getResetInterval());
		attributes.put("resetFrequency", getResetFrequency());
		attributes.put("netPriceType", getNetPriceType());
		attributes.put("netPriceTypeFormula", getNetPriceTypeFormula());
		attributes.put("basePriceType", getBasePriceType());
		attributes.put("basePriceEntry", getBasePriceEntry());
		attributes.put("basePriceDate", getBasePriceDate());
		attributes.put("basePriceDdlb", getBasePriceDdlb());
		attributes.put("netBasePrice", getNetBasePrice());
		attributes.put("netBasePriceFormulaId", getNetBasePriceFormulaId());
		attributes.put("subsequentPeriodPriceType",
			getSubsequentPeriodPriceType());
		attributes.put("netSubsequentPeriodPrice", getNetSubsequentPeriodPrice());
		attributes.put("netSubsequentPriceFormulaId",
			getNetSubsequentPriceFormulaId());
		attributes.put("resetPriceType", getResetPriceType());
		attributes.put("netResetPriceType", getNetResetPriceType());
		attributes.put("netResetPriceFormulaId", getNetResetPriceFormulaId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Double price = (Double)attributes.get("price");

		if (price != null) {
			setPrice(price);
		}

		Integer itemMasterSid = (Integer)attributes.get("itemMasterSid");

		if (itemMasterSid != null) {
			setItemMasterSid(itemMasterSid);
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

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Date revisionDate = (Date)attributes.get("revisionDate");

		if (revisionDate != null) {
			setRevisionDate(revisionDate);
		}

		Double priceTolerance = (Double)attributes.get("priceTolerance");

		if (priceTolerance != null) {
			setPriceTolerance(priceTolerance);
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

		Double suggestedPrice = (Double)attributes.get("suggestedPrice");

		if (suggestedPrice != null) {
			setSuggestedPrice(suggestedPrice);
		}

		Date psContractAttachedDate = (Date)attributes.get(
				"psContractAttachedDate");

		if (psContractAttachedDate != null) {
			setPsContractAttachedDate(psContractAttachedDate);
		}

		Integer psContractDetailsSid = (Integer)attributes.get(
				"psContractDetailsSid");

		if (psContractDetailsSid != null) {
			setPsContractDetailsSid(psContractDetailsSid);
		}

		Integer modifiedBy = (Integer)attributes.get("modifiedBy");

		if (modifiedBy != null) {
			setModifiedBy(modifiedBy);
		}

		String inboundStatus = (String)attributes.get("inboundStatus");

		if (inboundStatus != null) {
			setInboundStatus(inboundStatus);
		}

		Double contractPrice = (Double)attributes.get("contractPrice");

		if (contractPrice != null) {
			setContractPrice(contractPrice);
		}

		Integer priceToleranceType = (Integer)attributes.get(
				"priceToleranceType");

		if (priceToleranceType != null) {
			setPriceToleranceType(priceToleranceType);
		}

		Integer itemPricingQualifierSid = (Integer)attributes.get(
				"itemPricingQualifierSid");

		if (itemPricingQualifierSid != null) {
			setItemPricingQualifierSid(itemPricingQualifierSid);
		}

		Date contractPriceEndDate = (Date)attributes.get("contractPriceEndDate");

		if (contractPriceEndDate != null) {
			setContractPriceEndDate(contractPriceEndDate);
		}

		Integer priceToleranceFrequency = (Integer)attributes.get(
				"priceToleranceFrequency");

		if (priceToleranceFrequency != null) {
			setPriceToleranceFrequency(priceToleranceFrequency);
		}

		Date contractPriceStartDate = (Date)attributes.get(
				"contractPriceStartDate");

		if (contractPriceStartDate != null) {
			setContractPriceStartDate(contractPriceStartDate);
		}

		Integer psContractSid = (Integer)attributes.get("psContractSid");

		if (psContractSid != null) {
			setPsContractSid(psContractSid);
		}

		Date priceProtectionEndDate = (Date)attributes.get(
				"priceProtectionEndDate");

		if (priceProtectionEndDate != null) {
			setPriceProtectionEndDate(priceProtectionEndDate);
		}

		Integer psContractAttachedStatus = (Integer)attributes.get(
				"psContractAttachedStatus");

		if (psContractAttachedStatus != null) {
			setPsContractAttachedStatus(psContractAttachedStatus);
		}

		Boolean recordLockStatus = (Boolean)attributes.get("recordLockStatus");

		if (recordLockStatus != null) {
			setRecordLockStatus(recordLockStatus);
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

		String brandMasterSid = (String)attributes.get("brandMasterSid");

		if (brandMasterSid != null) {
			setBrandMasterSid(brandMasterSid);
		}

		Double nep = (Double)attributes.get("nep");

		if (nep != null) {
			setNep(nep);
		}

		Integer priceProtectionStatus = (Integer)attributes.get(
				"priceProtectionStatus");

		if (priceProtectionStatus != null) {
			setPriceProtectionStatus(priceProtectionStatus);
		}

		Integer priceProtectionPriceType = (Integer)attributes.get(
				"priceProtectionPriceType");

		if (priceProtectionPriceType != null) {
			setPriceProtectionPriceType(priceProtectionPriceType);
		}

		Integer nepFormula = (Integer)attributes.get("nepFormula");

		if (nepFormula != null) {
			setNepFormula(nepFormula);
		}

		Double maxIncrementalChange = (Double)attributes.get(
				"maxIncrementalChange");

		if (maxIncrementalChange != null) {
			setMaxIncrementalChange(maxIncrementalChange);
		}

		Integer resetEligible = (Integer)attributes.get("resetEligible");

		if (resetEligible != null) {
			setResetEligible(resetEligible);
		}

		Integer resetType = (Integer)attributes.get("resetType");

		if (resetType != null) {
			setResetType(resetType);
		}

		Date resetDate = (Date)attributes.get("resetDate");

		if (resetDate != null) {
			setResetDate(resetDate);
		}

		Integer resetInterval = (Integer)attributes.get("resetInterval");

		if (resetInterval != null) {
			setResetInterval(resetInterval);
		}

		Integer resetFrequency = (Integer)attributes.get("resetFrequency");

		if (resetFrequency != null) {
			setResetFrequency(resetFrequency);
		}

		Integer netPriceType = (Integer)attributes.get("netPriceType");

		if (netPriceType != null) {
			setNetPriceType(netPriceType);
		}

		String netPriceTypeFormula = (String)attributes.get(
				"netPriceTypeFormula");

		if (netPriceTypeFormula != null) {
			setNetPriceTypeFormula(netPriceTypeFormula);
		}

		Integer basePriceType = (Integer)attributes.get("basePriceType");

		if (basePriceType != null) {
			setBasePriceType(basePriceType);
		}

		Double basePriceEntry = (Double)attributes.get("basePriceEntry");

		if (basePriceEntry != null) {
			setBasePriceEntry(basePriceEntry);
		}

		Date basePriceDate = (Date)attributes.get("basePriceDate");

		if (basePriceDate != null) {
			setBasePriceDate(basePriceDate);
		}

		Integer basePriceDdlb = (Integer)attributes.get("basePriceDdlb");

		if (basePriceDdlb != null) {
			setBasePriceDdlb(basePriceDdlb);
		}

		Integer netBasePrice = (Integer)attributes.get("netBasePrice");

		if (netBasePrice != null) {
			setNetBasePrice(netBasePrice);
		}

		Integer netBasePriceFormulaId = (Integer)attributes.get(
				"netBasePriceFormulaId");

		if (netBasePriceFormulaId != null) {
			setNetBasePriceFormulaId(netBasePriceFormulaId);
		}

		Integer subsequentPeriodPriceType = (Integer)attributes.get(
				"subsequentPeriodPriceType");

		if (subsequentPeriodPriceType != null) {
			setSubsequentPeriodPriceType(subsequentPeriodPriceType);
		}

		Integer netSubsequentPeriodPrice = (Integer)attributes.get(
				"netSubsequentPeriodPrice");

		if (netSubsequentPeriodPrice != null) {
			setNetSubsequentPeriodPrice(netSubsequentPeriodPrice);
		}

		Integer netSubsequentPriceFormulaId = (Integer)attributes.get(
				"netSubsequentPriceFormulaId");

		if (netSubsequentPriceFormulaId != null) {
			setNetSubsequentPriceFormulaId(netSubsequentPriceFormulaId);
		}

		Integer resetPriceType = (Integer)attributes.get("resetPriceType");

		if (resetPriceType != null) {
			setResetPriceType(resetPriceType);
		}

		Integer netResetPriceType = (Integer)attributes.get("netResetPriceType");

		if (netResetPriceType != null) {
			setNetResetPriceType(netResetPriceType);
		}

		Integer netResetPriceFormulaId = (Integer)attributes.get(
				"netResetPriceFormulaId");

		if (netResetPriceFormulaId != null) {
			setNetResetPriceFormulaId(netResetPriceFormulaId);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new PsContractDetailsWrapper((PsContractDetails)_psContractDetails.clone());
	}

	@Override
	public int compareTo(PsContractDetails psContractDetails) {
		return _psContractDetails.compareTo(psContractDetails);
	}

	/**
	* Returns the base price of this ps contract details.
	*
	* @return the base price of this ps contract details
	*/
	@Override
	public double getBasePrice() {
		return _psContractDetails.getBasePrice();
	}

	/**
	* Returns the base price date of this ps contract details.
	*
	* @return the base price date of this ps contract details
	*/
	@Override
	public Date getBasePriceDate() {
		return _psContractDetails.getBasePriceDate();
	}

	/**
	* Returns the base price ddlb of this ps contract details.
	*
	* @return the base price ddlb of this ps contract details
	*/
	@Override
	public int getBasePriceDdlb() {
		return _psContractDetails.getBasePriceDdlb();
	}

	/**
	* Returns the base price entry of this ps contract details.
	*
	* @return the base price entry of this ps contract details
	*/
	@Override
	public double getBasePriceEntry() {
		return _psContractDetails.getBasePriceEntry();
	}

	/**
	* Returns the base price type of this ps contract details.
	*
	* @return the base price type of this ps contract details
	*/
	@Override
	public int getBasePriceType() {
		return _psContractDetails.getBasePriceType();
	}

	/**
	* Returns the batch ID of this ps contract details.
	*
	* @return the batch ID of this ps contract details
	*/
	@Override
	public java.lang.String getBatchId() {
		return _psContractDetails.getBatchId();
	}

	/**
	* Returns the brand master sid of this ps contract details.
	*
	* @return the brand master sid of this ps contract details
	*/
	@Override
	public java.lang.String getBrandMasterSid() {
		return _psContractDetails.getBrandMasterSid();
	}

	/**
	* Returns the contract price of this ps contract details.
	*
	* @return the contract price of this ps contract details
	*/
	@Override
	public double getContractPrice() {
		return _psContractDetails.getContractPrice();
	}

	/**
	* Returns the contract price end date of this ps contract details.
	*
	* @return the contract price end date of this ps contract details
	*/
	@Override
	public Date getContractPriceEndDate() {
		return _psContractDetails.getContractPriceEndDate();
	}

	/**
	* Returns the contract price start date of this ps contract details.
	*
	* @return the contract price start date of this ps contract details
	*/
	@Override
	public Date getContractPriceStartDate() {
		return _psContractDetails.getContractPriceStartDate();
	}

	/**
	* Returns the created by of this ps contract details.
	*
	* @return the created by of this ps contract details
	*/
	@Override
	public int getCreatedBy() {
		return _psContractDetails.getCreatedBy();
	}

	/**
	* Returns the created date of this ps contract details.
	*
	* @return the created date of this ps contract details
	*/
	@Override
	public Date getCreatedDate() {
		return _psContractDetails.getCreatedDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _psContractDetails.getExpandoBridge();
	}

	/**
	* Returns the inbound status of this ps contract details.
	*
	* @return the inbound status of this ps contract details
	*/
	@Override
	public java.lang.String getInboundStatus() {
		return _psContractDetails.getInboundStatus();
	}

	/**
	* Returns the item master sid of this ps contract details.
	*
	* @return the item master sid of this ps contract details
	*/
	@Override
	public int getItemMasterSid() {
		return _psContractDetails.getItemMasterSid();
	}

	/**
	* Returns the item pricing qualifier sid of this ps contract details.
	*
	* @return the item pricing qualifier sid of this ps contract details
	*/
	@Override
	public int getItemPricingQualifierSid() {
		return _psContractDetails.getItemPricingQualifierSid();
	}

	/**
	* Returns the max incremental change of this ps contract details.
	*
	* @return the max incremental change of this ps contract details
	*/
	@Override
	public double getMaxIncrementalChange() {
		return _psContractDetails.getMaxIncrementalChange();
	}

	/**
	* Returns the modified by of this ps contract details.
	*
	* @return the modified by of this ps contract details
	*/
	@Override
	public int getModifiedBy() {
		return _psContractDetails.getModifiedBy();
	}

	/**
	* Returns the modified date of this ps contract details.
	*
	* @return the modified date of this ps contract details
	*/
	@Override
	public Date getModifiedDate() {
		return _psContractDetails.getModifiedDate();
	}

	/**
	* Returns the nep of this ps contract details.
	*
	* @return the nep of this ps contract details
	*/
	@Override
	public double getNep() {
		return _psContractDetails.getNep();
	}

	/**
	* Returns the nep formula of this ps contract details.
	*
	* @return the nep formula of this ps contract details
	*/
	@Override
	public int getNepFormula() {
		return _psContractDetails.getNepFormula();
	}

	/**
	* Returns the net base price of this ps contract details.
	*
	* @return the net base price of this ps contract details
	*/
	@Override
	public int getNetBasePrice() {
		return _psContractDetails.getNetBasePrice();
	}

	/**
	* Returns the net base price formula ID of this ps contract details.
	*
	* @return the net base price formula ID of this ps contract details
	*/
	@Override
	public int getNetBasePriceFormulaId() {
		return _psContractDetails.getNetBasePriceFormulaId();
	}

	/**
	* Returns the net price type of this ps contract details.
	*
	* @return the net price type of this ps contract details
	*/
	@Override
	public int getNetPriceType() {
		return _psContractDetails.getNetPriceType();
	}

	/**
	* Returns the net price type formula of this ps contract details.
	*
	* @return the net price type formula of this ps contract details
	*/
	@Override
	public java.lang.String getNetPriceTypeFormula() {
		return _psContractDetails.getNetPriceTypeFormula();
	}

	/**
	* Returns the net reset price formula ID of this ps contract details.
	*
	* @return the net reset price formula ID of this ps contract details
	*/
	@Override
	public int getNetResetPriceFormulaId() {
		return _psContractDetails.getNetResetPriceFormulaId();
	}

	/**
	* Returns the net reset price type of this ps contract details.
	*
	* @return the net reset price type of this ps contract details
	*/
	@Override
	public int getNetResetPriceType() {
		return _psContractDetails.getNetResetPriceType();
	}

	/**
	* Returns the net subsequent period price of this ps contract details.
	*
	* @return the net subsequent period price of this ps contract details
	*/
	@Override
	public int getNetSubsequentPeriodPrice() {
		return _psContractDetails.getNetSubsequentPeriodPrice();
	}

	/**
	* Returns the net subsequent price formula ID of this ps contract details.
	*
	* @return the net subsequent price formula ID of this ps contract details
	*/
	@Override
	public int getNetSubsequentPriceFormulaId() {
		return _psContractDetails.getNetSubsequentPriceFormulaId();
	}

	/**
	* Returns the price of this ps contract details.
	*
	* @return the price of this ps contract details
	*/
	@Override
	public double getPrice() {
		return _psContractDetails.getPrice();
	}

	/**
	* Returns the price protection end date of this ps contract details.
	*
	* @return the price protection end date of this ps contract details
	*/
	@Override
	public Date getPriceProtectionEndDate() {
		return _psContractDetails.getPriceProtectionEndDate();
	}

	/**
	* Returns the price protection price type of this ps contract details.
	*
	* @return the price protection price type of this ps contract details
	*/
	@Override
	public int getPriceProtectionPriceType() {
		return _psContractDetails.getPriceProtectionPriceType();
	}

	/**
	* Returns the price protection start date of this ps contract details.
	*
	* @return the price protection start date of this ps contract details
	*/
	@Override
	public Date getPriceProtectionStartDate() {
		return _psContractDetails.getPriceProtectionStartDate();
	}

	/**
	* Returns the price protection status of this ps contract details.
	*
	* @return the price protection status of this ps contract details
	*/
	@Override
	public int getPriceProtectionStatus() {
		return _psContractDetails.getPriceProtectionStatus();
	}

	/**
	* Returns the price revision of this ps contract details.
	*
	* @return the price revision of this ps contract details
	*/
	@Override
	public double getPriceRevision() {
		return _psContractDetails.getPriceRevision();
	}

	/**
	* Returns the price tolerance of this ps contract details.
	*
	* @return the price tolerance of this ps contract details
	*/
	@Override
	public double getPriceTolerance() {
		return _psContractDetails.getPriceTolerance();
	}

	/**
	* Returns the price tolerance frequency of this ps contract details.
	*
	* @return the price tolerance frequency of this ps contract details
	*/
	@Override
	public int getPriceToleranceFrequency() {
		return _psContractDetails.getPriceToleranceFrequency();
	}

	/**
	* Returns the price tolerance interval of this ps contract details.
	*
	* @return the price tolerance interval of this ps contract details
	*/
	@Override
	public int getPriceToleranceInterval() {
		return _psContractDetails.getPriceToleranceInterval();
	}

	/**
	* Returns the price tolerance type of this ps contract details.
	*
	* @return the price tolerance type of this ps contract details
	*/
	@Override
	public int getPriceToleranceType() {
		return _psContractDetails.getPriceToleranceType();
	}

	/**
	* Returns the primary key of this ps contract details.
	*
	* @return the primary key of this ps contract details
	*/
	@Override
	public int getPrimaryKey() {
		return _psContractDetails.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _psContractDetails.getPrimaryKeyObj();
	}

	/**
	* Returns the ps contract attached date of this ps contract details.
	*
	* @return the ps contract attached date of this ps contract details
	*/
	@Override
	public Date getPsContractAttachedDate() {
		return _psContractDetails.getPsContractAttachedDate();
	}

	/**
	* Returns the ps contract attached status of this ps contract details.
	*
	* @return the ps contract attached status of this ps contract details
	*/
	@Override
	public int getPsContractAttachedStatus() {
		return _psContractDetails.getPsContractAttachedStatus();
	}

	/**
	* Returns the ps contract details sid of this ps contract details.
	*
	* @return the ps contract details sid of this ps contract details
	*/
	@Override
	public int getPsContractDetailsSid() {
		return _psContractDetails.getPsContractDetailsSid();
	}

	/**
	* Returns the ps contract sid of this ps contract details.
	*
	* @return the ps contract sid of this ps contract details
	*/
	@Override
	public int getPsContractSid() {
		return _psContractDetails.getPsContractSid();
	}

	/**
	* Returns the record lock status of this ps contract details.
	*
	* @return the record lock status of this ps contract details
	*/
	@Override
	public boolean getRecordLockStatus() {
		return _psContractDetails.getRecordLockStatus();
	}

	/**
	* Returns the reset date of this ps contract details.
	*
	* @return the reset date of this ps contract details
	*/
	@Override
	public Date getResetDate() {
		return _psContractDetails.getResetDate();
	}

	/**
	* Returns the reset eligible of this ps contract details.
	*
	* @return the reset eligible of this ps contract details
	*/
	@Override
	public int getResetEligible() {
		return _psContractDetails.getResetEligible();
	}

	/**
	* Returns the reset frequency of this ps contract details.
	*
	* @return the reset frequency of this ps contract details
	*/
	@Override
	public int getResetFrequency() {
		return _psContractDetails.getResetFrequency();
	}

	/**
	* Returns the reset interval of this ps contract details.
	*
	* @return the reset interval of this ps contract details
	*/
	@Override
	public int getResetInterval() {
		return _psContractDetails.getResetInterval();
	}

	/**
	* Returns the reset price type of this ps contract details.
	*
	* @return the reset price type of this ps contract details
	*/
	@Override
	public int getResetPriceType() {
		return _psContractDetails.getResetPriceType();
	}

	/**
	* Returns the reset type of this ps contract details.
	*
	* @return the reset type of this ps contract details
	*/
	@Override
	public int getResetType() {
		return _psContractDetails.getResetType();
	}

	/**
	* Returns the revision date of this ps contract details.
	*
	* @return the revision date of this ps contract details
	*/
	@Override
	public Date getRevisionDate() {
		return _psContractDetails.getRevisionDate();
	}

	/**
	* Returns the source of this ps contract details.
	*
	* @return the source of this ps contract details
	*/
	@Override
	public java.lang.String getSource() {
		return _psContractDetails.getSource();
	}

	/**
	* Returns the subsequent period price type of this ps contract details.
	*
	* @return the subsequent period price type of this ps contract details
	*/
	@Override
	public int getSubsequentPeriodPriceType() {
		return _psContractDetails.getSubsequentPeriodPriceType();
	}

	/**
	* Returns the suggested price of this ps contract details.
	*
	* @return the suggested price of this ps contract details
	*/
	@Override
	public double getSuggestedPrice() {
		return _psContractDetails.getSuggestedPrice();
	}

	@Override
	public int hashCode() {
		return _psContractDetails.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _psContractDetails.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _psContractDetails.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _psContractDetails.isNew();
	}

	/**
	* Returns <code>true</code> if this ps contract details is record lock status.
	*
	* @return <code>true</code> if this ps contract details is record lock status; <code>false</code> otherwise
	*/
	@Override
	public boolean isRecordLockStatus() {
		return _psContractDetails.isRecordLockStatus();
	}

	@Override
	public void persist() {
		_psContractDetails.persist();
	}

	/**
	* Sets the base price of this ps contract details.
	*
	* @param basePrice the base price of this ps contract details
	*/
	@Override
	public void setBasePrice(double basePrice) {
		_psContractDetails.setBasePrice(basePrice);
	}

	/**
	* Sets the base price date of this ps contract details.
	*
	* @param basePriceDate the base price date of this ps contract details
	*/
	@Override
	public void setBasePriceDate(Date basePriceDate) {
		_psContractDetails.setBasePriceDate(basePriceDate);
	}

	/**
	* Sets the base price ddlb of this ps contract details.
	*
	* @param basePriceDdlb the base price ddlb of this ps contract details
	*/
	@Override
	public void setBasePriceDdlb(int basePriceDdlb) {
		_psContractDetails.setBasePriceDdlb(basePriceDdlb);
	}

	/**
	* Sets the base price entry of this ps contract details.
	*
	* @param basePriceEntry the base price entry of this ps contract details
	*/
	@Override
	public void setBasePriceEntry(double basePriceEntry) {
		_psContractDetails.setBasePriceEntry(basePriceEntry);
	}

	/**
	* Sets the base price type of this ps contract details.
	*
	* @param basePriceType the base price type of this ps contract details
	*/
	@Override
	public void setBasePriceType(int basePriceType) {
		_psContractDetails.setBasePriceType(basePriceType);
	}

	/**
	* Sets the batch ID of this ps contract details.
	*
	* @param batchId the batch ID of this ps contract details
	*/
	@Override
	public void setBatchId(java.lang.String batchId) {
		_psContractDetails.setBatchId(batchId);
	}

	/**
	* Sets the brand master sid of this ps contract details.
	*
	* @param brandMasterSid the brand master sid of this ps contract details
	*/
	@Override
	public void setBrandMasterSid(java.lang.String brandMasterSid) {
		_psContractDetails.setBrandMasterSid(brandMasterSid);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_psContractDetails.setCachedModel(cachedModel);
	}

	/**
	* Sets the contract price of this ps contract details.
	*
	* @param contractPrice the contract price of this ps contract details
	*/
	@Override
	public void setContractPrice(double contractPrice) {
		_psContractDetails.setContractPrice(contractPrice);
	}

	/**
	* Sets the contract price end date of this ps contract details.
	*
	* @param contractPriceEndDate the contract price end date of this ps contract details
	*/
	@Override
	public void setContractPriceEndDate(Date contractPriceEndDate) {
		_psContractDetails.setContractPriceEndDate(contractPriceEndDate);
	}

	/**
	* Sets the contract price start date of this ps contract details.
	*
	* @param contractPriceStartDate the contract price start date of this ps contract details
	*/
	@Override
	public void setContractPriceStartDate(Date contractPriceStartDate) {
		_psContractDetails.setContractPriceStartDate(contractPriceStartDate);
	}

	/**
	* Sets the created by of this ps contract details.
	*
	* @param createdBy the created by of this ps contract details
	*/
	@Override
	public void setCreatedBy(int createdBy) {
		_psContractDetails.setCreatedBy(createdBy);
	}

	/**
	* Sets the created date of this ps contract details.
	*
	* @param createdDate the created date of this ps contract details
	*/
	@Override
	public void setCreatedDate(Date createdDate) {
		_psContractDetails.setCreatedDate(createdDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_psContractDetails.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_psContractDetails.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_psContractDetails.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the inbound status of this ps contract details.
	*
	* @param inboundStatus the inbound status of this ps contract details
	*/
	@Override
	public void setInboundStatus(java.lang.String inboundStatus) {
		_psContractDetails.setInboundStatus(inboundStatus);
	}

	/**
	* Sets the item master sid of this ps contract details.
	*
	* @param itemMasterSid the item master sid of this ps contract details
	*/
	@Override
	public void setItemMasterSid(int itemMasterSid) {
		_psContractDetails.setItemMasterSid(itemMasterSid);
	}

	/**
	* Sets the item pricing qualifier sid of this ps contract details.
	*
	* @param itemPricingQualifierSid the item pricing qualifier sid of this ps contract details
	*/
	@Override
	public void setItemPricingQualifierSid(int itemPricingQualifierSid) {
		_psContractDetails.setItemPricingQualifierSid(itemPricingQualifierSid);
	}

	/**
	* Sets the max incremental change of this ps contract details.
	*
	* @param maxIncrementalChange the max incremental change of this ps contract details
	*/
	@Override
	public void setMaxIncrementalChange(double maxIncrementalChange) {
		_psContractDetails.setMaxIncrementalChange(maxIncrementalChange);
	}

	/**
	* Sets the modified by of this ps contract details.
	*
	* @param modifiedBy the modified by of this ps contract details
	*/
	@Override
	public void setModifiedBy(int modifiedBy) {
		_psContractDetails.setModifiedBy(modifiedBy);
	}

	/**
	* Sets the modified date of this ps contract details.
	*
	* @param modifiedDate the modified date of this ps contract details
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_psContractDetails.setModifiedDate(modifiedDate);
	}

	/**
	* Sets the nep of this ps contract details.
	*
	* @param nep the nep of this ps contract details
	*/
	@Override
	public void setNep(double nep) {
		_psContractDetails.setNep(nep);
	}

	/**
	* Sets the nep formula of this ps contract details.
	*
	* @param nepFormula the nep formula of this ps contract details
	*/
	@Override
	public void setNepFormula(int nepFormula) {
		_psContractDetails.setNepFormula(nepFormula);
	}

	/**
	* Sets the net base price of this ps contract details.
	*
	* @param netBasePrice the net base price of this ps contract details
	*/
	@Override
	public void setNetBasePrice(int netBasePrice) {
		_psContractDetails.setNetBasePrice(netBasePrice);
	}

	/**
	* Sets the net base price formula ID of this ps contract details.
	*
	* @param netBasePriceFormulaId the net base price formula ID of this ps contract details
	*/
	@Override
	public void setNetBasePriceFormulaId(int netBasePriceFormulaId) {
		_psContractDetails.setNetBasePriceFormulaId(netBasePriceFormulaId);
	}

	/**
	* Sets the net price type of this ps contract details.
	*
	* @param netPriceType the net price type of this ps contract details
	*/
	@Override
	public void setNetPriceType(int netPriceType) {
		_psContractDetails.setNetPriceType(netPriceType);
	}

	/**
	* Sets the net price type formula of this ps contract details.
	*
	* @param netPriceTypeFormula the net price type formula of this ps contract details
	*/
	@Override
	public void setNetPriceTypeFormula(java.lang.String netPriceTypeFormula) {
		_psContractDetails.setNetPriceTypeFormula(netPriceTypeFormula);
	}

	/**
	* Sets the net reset price formula ID of this ps contract details.
	*
	* @param netResetPriceFormulaId the net reset price formula ID of this ps contract details
	*/
	@Override
	public void setNetResetPriceFormulaId(int netResetPriceFormulaId) {
		_psContractDetails.setNetResetPriceFormulaId(netResetPriceFormulaId);
	}

	/**
	* Sets the net reset price type of this ps contract details.
	*
	* @param netResetPriceType the net reset price type of this ps contract details
	*/
	@Override
	public void setNetResetPriceType(int netResetPriceType) {
		_psContractDetails.setNetResetPriceType(netResetPriceType);
	}

	/**
	* Sets the net subsequent period price of this ps contract details.
	*
	* @param netSubsequentPeriodPrice the net subsequent period price of this ps contract details
	*/
	@Override
	public void setNetSubsequentPeriodPrice(int netSubsequentPeriodPrice) {
		_psContractDetails.setNetSubsequentPeriodPrice(netSubsequentPeriodPrice);
	}

	/**
	* Sets the net subsequent price formula ID of this ps contract details.
	*
	* @param netSubsequentPriceFormulaId the net subsequent price formula ID of this ps contract details
	*/
	@Override
	public void setNetSubsequentPriceFormulaId(int netSubsequentPriceFormulaId) {
		_psContractDetails.setNetSubsequentPriceFormulaId(netSubsequentPriceFormulaId);
	}

	@Override
	public void setNew(boolean n) {
		_psContractDetails.setNew(n);
	}

	/**
	* Sets the price of this ps contract details.
	*
	* @param price the price of this ps contract details
	*/
	@Override
	public void setPrice(double price) {
		_psContractDetails.setPrice(price);
	}

	/**
	* Sets the price protection end date of this ps contract details.
	*
	* @param priceProtectionEndDate the price protection end date of this ps contract details
	*/
	@Override
	public void setPriceProtectionEndDate(Date priceProtectionEndDate) {
		_psContractDetails.setPriceProtectionEndDate(priceProtectionEndDate);
	}

	/**
	* Sets the price protection price type of this ps contract details.
	*
	* @param priceProtectionPriceType the price protection price type of this ps contract details
	*/
	@Override
	public void setPriceProtectionPriceType(int priceProtectionPriceType) {
		_psContractDetails.setPriceProtectionPriceType(priceProtectionPriceType);
	}

	/**
	* Sets the price protection start date of this ps contract details.
	*
	* @param priceProtectionStartDate the price protection start date of this ps contract details
	*/
	@Override
	public void setPriceProtectionStartDate(Date priceProtectionStartDate) {
		_psContractDetails.setPriceProtectionStartDate(priceProtectionStartDate);
	}

	/**
	* Sets the price protection status of this ps contract details.
	*
	* @param priceProtectionStatus the price protection status of this ps contract details
	*/
	@Override
	public void setPriceProtectionStatus(int priceProtectionStatus) {
		_psContractDetails.setPriceProtectionStatus(priceProtectionStatus);
	}

	/**
	* Sets the price revision of this ps contract details.
	*
	* @param priceRevision the price revision of this ps contract details
	*/
	@Override
	public void setPriceRevision(double priceRevision) {
		_psContractDetails.setPriceRevision(priceRevision);
	}

	/**
	* Sets the price tolerance of this ps contract details.
	*
	* @param priceTolerance the price tolerance of this ps contract details
	*/
	@Override
	public void setPriceTolerance(double priceTolerance) {
		_psContractDetails.setPriceTolerance(priceTolerance);
	}

	/**
	* Sets the price tolerance frequency of this ps contract details.
	*
	* @param priceToleranceFrequency the price tolerance frequency of this ps contract details
	*/
	@Override
	public void setPriceToleranceFrequency(int priceToleranceFrequency) {
		_psContractDetails.setPriceToleranceFrequency(priceToleranceFrequency);
	}

	/**
	* Sets the price tolerance interval of this ps contract details.
	*
	* @param priceToleranceInterval the price tolerance interval of this ps contract details
	*/
	@Override
	public void setPriceToleranceInterval(int priceToleranceInterval) {
		_psContractDetails.setPriceToleranceInterval(priceToleranceInterval);
	}

	/**
	* Sets the price tolerance type of this ps contract details.
	*
	* @param priceToleranceType the price tolerance type of this ps contract details
	*/
	@Override
	public void setPriceToleranceType(int priceToleranceType) {
		_psContractDetails.setPriceToleranceType(priceToleranceType);
	}

	/**
	* Sets the primary key of this ps contract details.
	*
	* @param primaryKey the primary key of this ps contract details
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_psContractDetails.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_psContractDetails.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the ps contract attached date of this ps contract details.
	*
	* @param psContractAttachedDate the ps contract attached date of this ps contract details
	*/
	@Override
	public void setPsContractAttachedDate(Date psContractAttachedDate) {
		_psContractDetails.setPsContractAttachedDate(psContractAttachedDate);
	}

	/**
	* Sets the ps contract attached status of this ps contract details.
	*
	* @param psContractAttachedStatus the ps contract attached status of this ps contract details
	*/
	@Override
	public void setPsContractAttachedStatus(int psContractAttachedStatus) {
		_psContractDetails.setPsContractAttachedStatus(psContractAttachedStatus);
	}

	/**
	* Sets the ps contract details sid of this ps contract details.
	*
	* @param psContractDetailsSid the ps contract details sid of this ps contract details
	*/
	@Override
	public void setPsContractDetailsSid(int psContractDetailsSid) {
		_psContractDetails.setPsContractDetailsSid(psContractDetailsSid);
	}

	/**
	* Sets the ps contract sid of this ps contract details.
	*
	* @param psContractSid the ps contract sid of this ps contract details
	*/
	@Override
	public void setPsContractSid(int psContractSid) {
		_psContractDetails.setPsContractSid(psContractSid);
	}

	/**
	* Sets whether this ps contract details is record lock status.
	*
	* @param recordLockStatus the record lock status of this ps contract details
	*/
	@Override
	public void setRecordLockStatus(boolean recordLockStatus) {
		_psContractDetails.setRecordLockStatus(recordLockStatus);
	}

	/**
	* Sets the reset date of this ps contract details.
	*
	* @param resetDate the reset date of this ps contract details
	*/
	@Override
	public void setResetDate(Date resetDate) {
		_psContractDetails.setResetDate(resetDate);
	}

	/**
	* Sets the reset eligible of this ps contract details.
	*
	* @param resetEligible the reset eligible of this ps contract details
	*/
	@Override
	public void setResetEligible(int resetEligible) {
		_psContractDetails.setResetEligible(resetEligible);
	}

	/**
	* Sets the reset frequency of this ps contract details.
	*
	* @param resetFrequency the reset frequency of this ps contract details
	*/
	@Override
	public void setResetFrequency(int resetFrequency) {
		_psContractDetails.setResetFrequency(resetFrequency);
	}

	/**
	* Sets the reset interval of this ps contract details.
	*
	* @param resetInterval the reset interval of this ps contract details
	*/
	@Override
	public void setResetInterval(int resetInterval) {
		_psContractDetails.setResetInterval(resetInterval);
	}

	/**
	* Sets the reset price type of this ps contract details.
	*
	* @param resetPriceType the reset price type of this ps contract details
	*/
	@Override
	public void setResetPriceType(int resetPriceType) {
		_psContractDetails.setResetPriceType(resetPriceType);
	}

	/**
	* Sets the reset type of this ps contract details.
	*
	* @param resetType the reset type of this ps contract details
	*/
	@Override
	public void setResetType(int resetType) {
		_psContractDetails.setResetType(resetType);
	}

	/**
	* Sets the revision date of this ps contract details.
	*
	* @param revisionDate the revision date of this ps contract details
	*/
	@Override
	public void setRevisionDate(Date revisionDate) {
		_psContractDetails.setRevisionDate(revisionDate);
	}

	/**
	* Sets the source of this ps contract details.
	*
	* @param source the source of this ps contract details
	*/
	@Override
	public void setSource(java.lang.String source) {
		_psContractDetails.setSource(source);
	}

	/**
	* Sets the subsequent period price type of this ps contract details.
	*
	* @param subsequentPeriodPriceType the subsequent period price type of this ps contract details
	*/
	@Override
	public void setSubsequentPeriodPriceType(int subsequentPeriodPriceType) {
		_psContractDetails.setSubsequentPeriodPriceType(subsequentPeriodPriceType);
	}

	/**
	* Sets the suggested price of this ps contract details.
	*
	* @param suggestedPrice the suggested price of this ps contract details
	*/
	@Override
	public void setSuggestedPrice(double suggestedPrice) {
		_psContractDetails.setSuggestedPrice(suggestedPrice);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<PsContractDetails> toCacheModel() {
		return _psContractDetails.toCacheModel();
	}

	@Override
	public PsContractDetails toEscapedModel() {
		return new PsContractDetailsWrapper(_psContractDetails.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _psContractDetails.toString();
	}

	@Override
	public PsContractDetails toUnescapedModel() {
		return new PsContractDetailsWrapper(_psContractDetails.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _psContractDetails.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof PsContractDetailsWrapper)) {
			return false;
		}

		PsContractDetailsWrapper psContractDetailsWrapper = (PsContractDetailsWrapper)obj;

		if (Objects.equals(_psContractDetails,
					psContractDetailsWrapper._psContractDetails)) {
			return true;
		}

		return false;
	}

	@Override
	public PsContractDetails getWrappedModel() {
		return _psContractDetails;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _psContractDetails.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _psContractDetails.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_psContractDetails.resetOriginalValues();
	}

	private final PsContractDetails _psContractDetails;
}