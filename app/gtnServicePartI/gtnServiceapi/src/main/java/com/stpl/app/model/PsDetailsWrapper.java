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
 * This class is a wrapper for {@link PsDetails}.
 * </p>
 *
 * @author
 * @see PsDetails
 * @generated
 */
@ProviderType
public class PsDetailsWrapper implements PsDetails, ModelWrapper<PsDetails> {
	public PsDetailsWrapper(PsDetails psDetails) {
		_psDetails = psDetails;
	}

	@Override
	public Class<?> getModelClass() {
		return PsDetails.class;
	}

	@Override
	public String getModelClassName() {
		return PsDetails.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("nepFormula", getNepFormula());
		attributes.put("price", getPrice());
		attributes.put("itemMasterSid", getItemMasterSid());
		attributes.put("resetType", getResetType());
		attributes.put("priceProtectionStartDate", getPriceProtectionStartDate());
		attributes.put("resetDate", getResetDate());
		attributes.put("basePrice", getBasePrice());
		attributes.put("itemPsAttachedDate", getItemPsAttachedDate());
		attributes.put("brandMasterSid", getBrandMasterSid());
		attributes.put("status", getStatus());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("itemPsAttachedStatus", getItemPsAttachedStatus());
		attributes.put("revisionDate", getRevisionDate());
		attributes.put("priceTolerance", getPriceTolerance());
		attributes.put("createdDate", getCreatedDate());
		attributes.put("createdBy", getCreatedBy());
		attributes.put("source", getSource());
		attributes.put("psDetailsSid", getPsDetailsSid());
		attributes.put("psModelSid", getPsModelSid());
		attributes.put("suggestedPrice", getSuggestedPrice());
		attributes.put("netPriceTypeFormula", getNetPriceTypeFormula());
		attributes.put("priceProtectionPriceType", getPriceProtectionPriceType());
		attributes.put("modifiedBy", getModifiedBy());
		attributes.put("inboundStatus", getInboundStatus());
		attributes.put("contractPrice", getContractPrice());
		attributes.put("ifpModelSid", getIfpModelSid());
		attributes.put("priceToleranceType", getPriceToleranceType());
		attributes.put("maxIncrementalChange", getMaxIncrementalChange());
		attributes.put("itemPricingQualifierSid", getItemPricingQualifierSid());
		attributes.put("contractPriceEndDate", getContractPriceEndDate());
		attributes.put("nep", getNep());
		attributes.put("contractPriceStartDate", getContractPriceStartDate());
		attributes.put("priceToleranceFrequency", getPriceToleranceFrequency());
		attributes.put("priceProtectionEndDate", getPriceProtectionEndDate());
		attributes.put("priceProtectionStatus", getPriceProtectionStatus());
		attributes.put("recordLockStatus", getRecordLockStatus());
		attributes.put("resetEligible", getResetEligible());
		attributes.put("batchId", getBatchId());
		attributes.put("priceToleranceInterval", getPriceToleranceInterval());
		attributes.put("netPriceType", getNetPriceType());
		attributes.put("priceRevision", getPriceRevision());
		attributes.put("resetFrequency", getResetFrequency());
		attributes.put("resetInterval", getResetInterval());
		attributes.put("basePriceType", getBasePriceType());
		attributes.put("basePriceEntry", getBasePriceEntry());
		attributes.put("basePriceDate", getBasePriceDate());
		attributes.put("netBasePrice", getNetBasePrice());
		attributes.put("basePriceDdlb", getBasePriceDdlb());
		attributes.put("subsequentPeriodPriceType",
			getSubsequentPeriodPriceType());
		attributes.put("netSubsequentPeriodPrice", getNetSubsequentPeriodPrice());
		attributes.put("netSubsequentPriceFormulaId",
			getNetSubsequentPriceFormulaId());
		attributes.put("resetPriceType", getResetPriceType());
		attributes.put("netResetPriceType", getNetResetPriceType());
		attributes.put("netResetPriceFormulaId", getNetResetPriceFormulaId());
		attributes.put("netBasePriceFormulaId", getNetBasePriceFormulaId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Integer nepFormula = (Integer)attributes.get("nepFormula");

		if (nepFormula != null) {
			setNepFormula(nepFormula);
		}

		Double price = (Double)attributes.get("price");

		if (price != null) {
			setPrice(price);
		}

		Integer itemMasterSid = (Integer)attributes.get("itemMasterSid");

		if (itemMasterSid != null) {
			setItemMasterSid(itemMasterSid);
		}

		Integer resetType = (Integer)attributes.get("resetType");

		if (resetType != null) {
			setResetType(resetType);
		}

		Date priceProtectionStartDate = (Date)attributes.get(
				"priceProtectionStartDate");

		if (priceProtectionStartDate != null) {
			setPriceProtectionStartDate(priceProtectionStartDate);
		}

		Date resetDate = (Date)attributes.get("resetDate");

		if (resetDate != null) {
			setResetDate(resetDate);
		}

		Double basePrice = (Double)attributes.get("basePrice");

		if (basePrice != null) {
			setBasePrice(basePrice);
		}

		Date itemPsAttachedDate = (Date)attributes.get("itemPsAttachedDate");

		if (itemPsAttachedDate != null) {
			setItemPsAttachedDate(itemPsAttachedDate);
		}

		String brandMasterSid = (String)attributes.get("brandMasterSid");

		if (brandMasterSid != null) {
			setBrandMasterSid(brandMasterSid);
		}

		Integer status = (Integer)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Integer itemPsAttachedStatus = (Integer)attributes.get(
				"itemPsAttachedStatus");

		if (itemPsAttachedStatus != null) {
			setItemPsAttachedStatus(itemPsAttachedStatus);
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

		Integer createdBy = (Integer)attributes.get("createdBy");

		if (createdBy != null) {
			setCreatedBy(createdBy);
		}

		String source = (String)attributes.get("source");

		if (source != null) {
			setSource(source);
		}

		Integer psDetailsSid = (Integer)attributes.get("psDetailsSid");

		if (psDetailsSid != null) {
			setPsDetailsSid(psDetailsSid);
		}

		Integer psModelSid = (Integer)attributes.get("psModelSid");

		if (psModelSid != null) {
			setPsModelSid(psModelSid);
		}

		Double suggestedPrice = (Double)attributes.get("suggestedPrice");

		if (suggestedPrice != null) {
			setSuggestedPrice(suggestedPrice);
		}

		String netPriceTypeFormula = (String)attributes.get(
				"netPriceTypeFormula");

		if (netPriceTypeFormula != null) {
			setNetPriceTypeFormula(netPriceTypeFormula);
		}

		Integer priceProtectionPriceType = (Integer)attributes.get(
				"priceProtectionPriceType");

		if (priceProtectionPriceType != null) {
			setPriceProtectionPriceType(priceProtectionPriceType);
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

		Integer ifpModelSid = (Integer)attributes.get("ifpModelSid");

		if (ifpModelSid != null) {
			setIfpModelSid(ifpModelSid);
		}

		Integer priceToleranceType = (Integer)attributes.get(
				"priceToleranceType");

		if (priceToleranceType != null) {
			setPriceToleranceType(priceToleranceType);
		}

		Double maxIncrementalChange = (Double)attributes.get(
				"maxIncrementalChange");

		if (maxIncrementalChange != null) {
			setMaxIncrementalChange(maxIncrementalChange);
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

		Double nep = (Double)attributes.get("nep");

		if (nep != null) {
			setNep(nep);
		}

		Date contractPriceStartDate = (Date)attributes.get(
				"contractPriceStartDate");

		if (contractPriceStartDate != null) {
			setContractPriceStartDate(contractPriceStartDate);
		}

		Integer priceToleranceFrequency = (Integer)attributes.get(
				"priceToleranceFrequency");

		if (priceToleranceFrequency != null) {
			setPriceToleranceFrequency(priceToleranceFrequency);
		}

		Date priceProtectionEndDate = (Date)attributes.get(
				"priceProtectionEndDate");

		if (priceProtectionEndDate != null) {
			setPriceProtectionEndDate(priceProtectionEndDate);
		}

		Integer priceProtectionStatus = (Integer)attributes.get(
				"priceProtectionStatus");

		if (priceProtectionStatus != null) {
			setPriceProtectionStatus(priceProtectionStatus);
		}

		Boolean recordLockStatus = (Boolean)attributes.get("recordLockStatus");

		if (recordLockStatus != null) {
			setRecordLockStatus(recordLockStatus);
		}

		Integer resetEligible = (Integer)attributes.get("resetEligible");

		if (resetEligible != null) {
			setResetEligible(resetEligible);
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

		Integer netPriceType = (Integer)attributes.get("netPriceType");

		if (netPriceType != null) {
			setNetPriceType(netPriceType);
		}

		Double priceRevision = (Double)attributes.get("priceRevision");

		if (priceRevision != null) {
			setPriceRevision(priceRevision);
		}

		Integer resetFrequency = (Integer)attributes.get("resetFrequency");

		if (resetFrequency != null) {
			setResetFrequency(resetFrequency);
		}

		Integer resetInterval = (Integer)attributes.get("resetInterval");

		if (resetInterval != null) {
			setResetInterval(resetInterval);
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

		Integer netBasePrice = (Integer)attributes.get("netBasePrice");

		if (netBasePrice != null) {
			setNetBasePrice(netBasePrice);
		}

		Integer basePriceDdlb = (Integer)attributes.get("basePriceDdlb");

		if (basePriceDdlb != null) {
			setBasePriceDdlb(basePriceDdlb);
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

		Integer netBasePriceFormulaId = (Integer)attributes.get(
				"netBasePriceFormulaId");

		if (netBasePriceFormulaId != null) {
			setNetBasePriceFormulaId(netBasePriceFormulaId);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new PsDetailsWrapper((PsDetails)_psDetails.clone());
	}

	@Override
	public int compareTo(PsDetails psDetails) {
		return _psDetails.compareTo(psDetails);
	}

	/**
	* Returns the base price of this ps details.
	*
	* @return the base price of this ps details
	*/
	@Override
	public double getBasePrice() {
		return _psDetails.getBasePrice();
	}

	/**
	* Returns the base price date of this ps details.
	*
	* @return the base price date of this ps details
	*/
	@Override
	public Date getBasePriceDate() {
		return _psDetails.getBasePriceDate();
	}

	/**
	* Returns the base price ddlb of this ps details.
	*
	* @return the base price ddlb of this ps details
	*/
	@Override
	public int getBasePriceDdlb() {
		return _psDetails.getBasePriceDdlb();
	}

	/**
	* Returns the base price entry of this ps details.
	*
	* @return the base price entry of this ps details
	*/
	@Override
	public double getBasePriceEntry() {
		return _psDetails.getBasePriceEntry();
	}

	/**
	* Returns the base price type of this ps details.
	*
	* @return the base price type of this ps details
	*/
	@Override
	public int getBasePriceType() {
		return _psDetails.getBasePriceType();
	}

	/**
	* Returns the batch ID of this ps details.
	*
	* @return the batch ID of this ps details
	*/
	@Override
	public java.lang.String getBatchId() {
		return _psDetails.getBatchId();
	}

	/**
	* Returns the brand master sid of this ps details.
	*
	* @return the brand master sid of this ps details
	*/
	@Override
	public java.lang.String getBrandMasterSid() {
		return _psDetails.getBrandMasterSid();
	}

	/**
	* Returns the contract price of this ps details.
	*
	* @return the contract price of this ps details
	*/
	@Override
	public double getContractPrice() {
		return _psDetails.getContractPrice();
	}

	/**
	* Returns the contract price end date of this ps details.
	*
	* @return the contract price end date of this ps details
	*/
	@Override
	public Date getContractPriceEndDate() {
		return _psDetails.getContractPriceEndDate();
	}

	/**
	* Returns the contract price start date of this ps details.
	*
	* @return the contract price start date of this ps details
	*/
	@Override
	public Date getContractPriceStartDate() {
		return _psDetails.getContractPriceStartDate();
	}

	/**
	* Returns the created by of this ps details.
	*
	* @return the created by of this ps details
	*/
	@Override
	public int getCreatedBy() {
		return _psDetails.getCreatedBy();
	}

	/**
	* Returns the created date of this ps details.
	*
	* @return the created date of this ps details
	*/
	@Override
	public Date getCreatedDate() {
		return _psDetails.getCreatedDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _psDetails.getExpandoBridge();
	}

	/**
	* Returns the ifp model sid of this ps details.
	*
	* @return the ifp model sid of this ps details
	*/
	@Override
	public int getIfpModelSid() {
		return _psDetails.getIfpModelSid();
	}

	/**
	* Returns the inbound status of this ps details.
	*
	* @return the inbound status of this ps details
	*/
	@Override
	public java.lang.String getInboundStatus() {
		return _psDetails.getInboundStatus();
	}

	/**
	* Returns the item master sid of this ps details.
	*
	* @return the item master sid of this ps details
	*/
	@Override
	public int getItemMasterSid() {
		return _psDetails.getItemMasterSid();
	}

	/**
	* Returns the item pricing qualifier sid of this ps details.
	*
	* @return the item pricing qualifier sid of this ps details
	*/
	@Override
	public int getItemPricingQualifierSid() {
		return _psDetails.getItemPricingQualifierSid();
	}

	/**
	* Returns the item ps attached date of this ps details.
	*
	* @return the item ps attached date of this ps details
	*/
	@Override
	public Date getItemPsAttachedDate() {
		return _psDetails.getItemPsAttachedDate();
	}

	/**
	* Returns the item ps attached status of this ps details.
	*
	* @return the item ps attached status of this ps details
	*/
	@Override
	public int getItemPsAttachedStatus() {
		return _psDetails.getItemPsAttachedStatus();
	}

	/**
	* Returns the max incremental change of this ps details.
	*
	* @return the max incremental change of this ps details
	*/
	@Override
	public double getMaxIncrementalChange() {
		return _psDetails.getMaxIncrementalChange();
	}

	/**
	* Returns the modified by of this ps details.
	*
	* @return the modified by of this ps details
	*/
	@Override
	public int getModifiedBy() {
		return _psDetails.getModifiedBy();
	}

	/**
	* Returns the modified date of this ps details.
	*
	* @return the modified date of this ps details
	*/
	@Override
	public Date getModifiedDate() {
		return _psDetails.getModifiedDate();
	}

	/**
	* Returns the nep of this ps details.
	*
	* @return the nep of this ps details
	*/
	@Override
	public double getNep() {
		return _psDetails.getNep();
	}

	/**
	* Returns the nep formula of this ps details.
	*
	* @return the nep formula of this ps details
	*/
	@Override
	public int getNepFormula() {
		return _psDetails.getNepFormula();
	}

	/**
	* Returns the net base price of this ps details.
	*
	* @return the net base price of this ps details
	*/
	@Override
	public int getNetBasePrice() {
		return _psDetails.getNetBasePrice();
	}

	/**
	* Returns the net base price formula ID of this ps details.
	*
	* @return the net base price formula ID of this ps details
	*/
	@Override
	public int getNetBasePriceFormulaId() {
		return _psDetails.getNetBasePriceFormulaId();
	}

	/**
	* Returns the net price type of this ps details.
	*
	* @return the net price type of this ps details
	*/
	@Override
	public int getNetPriceType() {
		return _psDetails.getNetPriceType();
	}

	/**
	* Returns the net price type formula of this ps details.
	*
	* @return the net price type formula of this ps details
	*/
	@Override
	public java.lang.String getNetPriceTypeFormula() {
		return _psDetails.getNetPriceTypeFormula();
	}

	/**
	* Returns the net reset price formula ID of this ps details.
	*
	* @return the net reset price formula ID of this ps details
	*/
	@Override
	public int getNetResetPriceFormulaId() {
		return _psDetails.getNetResetPriceFormulaId();
	}

	/**
	* Returns the net reset price type of this ps details.
	*
	* @return the net reset price type of this ps details
	*/
	@Override
	public int getNetResetPriceType() {
		return _psDetails.getNetResetPriceType();
	}

	/**
	* Returns the net subsequent period price of this ps details.
	*
	* @return the net subsequent period price of this ps details
	*/
	@Override
	public int getNetSubsequentPeriodPrice() {
		return _psDetails.getNetSubsequentPeriodPrice();
	}

	/**
	* Returns the net subsequent price formula ID of this ps details.
	*
	* @return the net subsequent price formula ID of this ps details
	*/
	@Override
	public int getNetSubsequentPriceFormulaId() {
		return _psDetails.getNetSubsequentPriceFormulaId();
	}

	/**
	* Returns the price of this ps details.
	*
	* @return the price of this ps details
	*/
	@Override
	public double getPrice() {
		return _psDetails.getPrice();
	}

	/**
	* Returns the price protection end date of this ps details.
	*
	* @return the price protection end date of this ps details
	*/
	@Override
	public Date getPriceProtectionEndDate() {
		return _psDetails.getPriceProtectionEndDate();
	}

	/**
	* Returns the price protection price type of this ps details.
	*
	* @return the price protection price type of this ps details
	*/
	@Override
	public int getPriceProtectionPriceType() {
		return _psDetails.getPriceProtectionPriceType();
	}

	/**
	* Returns the price protection start date of this ps details.
	*
	* @return the price protection start date of this ps details
	*/
	@Override
	public Date getPriceProtectionStartDate() {
		return _psDetails.getPriceProtectionStartDate();
	}

	/**
	* Returns the price protection status of this ps details.
	*
	* @return the price protection status of this ps details
	*/
	@Override
	public int getPriceProtectionStatus() {
		return _psDetails.getPriceProtectionStatus();
	}

	/**
	* Returns the price revision of this ps details.
	*
	* @return the price revision of this ps details
	*/
	@Override
	public double getPriceRevision() {
		return _psDetails.getPriceRevision();
	}

	/**
	* Returns the price tolerance of this ps details.
	*
	* @return the price tolerance of this ps details
	*/
	@Override
	public double getPriceTolerance() {
		return _psDetails.getPriceTolerance();
	}

	/**
	* Returns the price tolerance frequency of this ps details.
	*
	* @return the price tolerance frequency of this ps details
	*/
	@Override
	public int getPriceToleranceFrequency() {
		return _psDetails.getPriceToleranceFrequency();
	}

	/**
	* Returns the price tolerance interval of this ps details.
	*
	* @return the price tolerance interval of this ps details
	*/
	@Override
	public int getPriceToleranceInterval() {
		return _psDetails.getPriceToleranceInterval();
	}

	/**
	* Returns the price tolerance type of this ps details.
	*
	* @return the price tolerance type of this ps details
	*/
	@Override
	public int getPriceToleranceType() {
		return _psDetails.getPriceToleranceType();
	}

	/**
	* Returns the primary key of this ps details.
	*
	* @return the primary key of this ps details
	*/
	@Override
	public int getPrimaryKey() {
		return _psDetails.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _psDetails.getPrimaryKeyObj();
	}

	/**
	* Returns the ps details sid of this ps details.
	*
	* @return the ps details sid of this ps details
	*/
	@Override
	public int getPsDetailsSid() {
		return _psDetails.getPsDetailsSid();
	}

	/**
	* Returns the ps model sid of this ps details.
	*
	* @return the ps model sid of this ps details
	*/
	@Override
	public int getPsModelSid() {
		return _psDetails.getPsModelSid();
	}

	/**
	* Returns the record lock status of this ps details.
	*
	* @return the record lock status of this ps details
	*/
	@Override
	public boolean getRecordLockStatus() {
		return _psDetails.getRecordLockStatus();
	}

	/**
	* Returns the reset date of this ps details.
	*
	* @return the reset date of this ps details
	*/
	@Override
	public Date getResetDate() {
		return _psDetails.getResetDate();
	}

	/**
	* Returns the reset eligible of this ps details.
	*
	* @return the reset eligible of this ps details
	*/
	@Override
	public int getResetEligible() {
		return _psDetails.getResetEligible();
	}

	/**
	* Returns the reset frequency of this ps details.
	*
	* @return the reset frequency of this ps details
	*/
	@Override
	public int getResetFrequency() {
		return _psDetails.getResetFrequency();
	}

	/**
	* Returns the reset interval of this ps details.
	*
	* @return the reset interval of this ps details
	*/
	@Override
	public int getResetInterval() {
		return _psDetails.getResetInterval();
	}

	/**
	* Returns the reset price type of this ps details.
	*
	* @return the reset price type of this ps details
	*/
	@Override
	public int getResetPriceType() {
		return _psDetails.getResetPriceType();
	}

	/**
	* Returns the reset type of this ps details.
	*
	* @return the reset type of this ps details
	*/
	@Override
	public int getResetType() {
		return _psDetails.getResetType();
	}

	/**
	* Returns the revision date of this ps details.
	*
	* @return the revision date of this ps details
	*/
	@Override
	public Date getRevisionDate() {
		return _psDetails.getRevisionDate();
	}

	/**
	* Returns the source of this ps details.
	*
	* @return the source of this ps details
	*/
	@Override
	public java.lang.String getSource() {
		return _psDetails.getSource();
	}

	/**
	* Returns the status of this ps details.
	*
	* @return the status of this ps details
	*/
	@Override
	public int getStatus() {
		return _psDetails.getStatus();
	}

	/**
	* Returns the subsequent period price type of this ps details.
	*
	* @return the subsequent period price type of this ps details
	*/
	@Override
	public int getSubsequentPeriodPriceType() {
		return _psDetails.getSubsequentPeriodPriceType();
	}

	/**
	* Returns the suggested price of this ps details.
	*
	* @return the suggested price of this ps details
	*/
	@Override
	public double getSuggestedPrice() {
		return _psDetails.getSuggestedPrice();
	}

	@Override
	public int hashCode() {
		return _psDetails.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _psDetails.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _psDetails.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _psDetails.isNew();
	}

	/**
	* Returns <code>true</code> if this ps details is record lock status.
	*
	* @return <code>true</code> if this ps details is record lock status; <code>false</code> otherwise
	*/
	@Override
	public boolean isRecordLockStatus() {
		return _psDetails.isRecordLockStatus();
	}

	@Override
	public void persist() {
		_psDetails.persist();
	}

	/**
	* Sets the base price of this ps details.
	*
	* @param basePrice the base price of this ps details
	*/
	@Override
	public void setBasePrice(double basePrice) {
		_psDetails.setBasePrice(basePrice);
	}

	/**
	* Sets the base price date of this ps details.
	*
	* @param basePriceDate the base price date of this ps details
	*/
	@Override
	public void setBasePriceDate(Date basePriceDate) {
		_psDetails.setBasePriceDate(basePriceDate);
	}

	/**
	* Sets the base price ddlb of this ps details.
	*
	* @param basePriceDdlb the base price ddlb of this ps details
	*/
	@Override
	public void setBasePriceDdlb(int basePriceDdlb) {
		_psDetails.setBasePriceDdlb(basePriceDdlb);
	}

	/**
	* Sets the base price entry of this ps details.
	*
	* @param basePriceEntry the base price entry of this ps details
	*/
	@Override
	public void setBasePriceEntry(double basePriceEntry) {
		_psDetails.setBasePriceEntry(basePriceEntry);
	}

	/**
	* Sets the base price type of this ps details.
	*
	* @param basePriceType the base price type of this ps details
	*/
	@Override
	public void setBasePriceType(int basePriceType) {
		_psDetails.setBasePriceType(basePriceType);
	}

	/**
	* Sets the batch ID of this ps details.
	*
	* @param batchId the batch ID of this ps details
	*/
	@Override
	public void setBatchId(java.lang.String batchId) {
		_psDetails.setBatchId(batchId);
	}

	/**
	* Sets the brand master sid of this ps details.
	*
	* @param brandMasterSid the brand master sid of this ps details
	*/
	@Override
	public void setBrandMasterSid(java.lang.String brandMasterSid) {
		_psDetails.setBrandMasterSid(brandMasterSid);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_psDetails.setCachedModel(cachedModel);
	}

	/**
	* Sets the contract price of this ps details.
	*
	* @param contractPrice the contract price of this ps details
	*/
	@Override
	public void setContractPrice(double contractPrice) {
		_psDetails.setContractPrice(contractPrice);
	}

	/**
	* Sets the contract price end date of this ps details.
	*
	* @param contractPriceEndDate the contract price end date of this ps details
	*/
	@Override
	public void setContractPriceEndDate(Date contractPriceEndDate) {
		_psDetails.setContractPriceEndDate(contractPriceEndDate);
	}

	/**
	* Sets the contract price start date of this ps details.
	*
	* @param contractPriceStartDate the contract price start date of this ps details
	*/
	@Override
	public void setContractPriceStartDate(Date contractPriceStartDate) {
		_psDetails.setContractPriceStartDate(contractPriceStartDate);
	}

	/**
	* Sets the created by of this ps details.
	*
	* @param createdBy the created by of this ps details
	*/
	@Override
	public void setCreatedBy(int createdBy) {
		_psDetails.setCreatedBy(createdBy);
	}

	/**
	* Sets the created date of this ps details.
	*
	* @param createdDate the created date of this ps details
	*/
	@Override
	public void setCreatedDate(Date createdDate) {
		_psDetails.setCreatedDate(createdDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_psDetails.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_psDetails.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_psDetails.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the ifp model sid of this ps details.
	*
	* @param ifpModelSid the ifp model sid of this ps details
	*/
	@Override
	public void setIfpModelSid(int ifpModelSid) {
		_psDetails.setIfpModelSid(ifpModelSid);
	}

	/**
	* Sets the inbound status of this ps details.
	*
	* @param inboundStatus the inbound status of this ps details
	*/
	@Override
	public void setInboundStatus(java.lang.String inboundStatus) {
		_psDetails.setInboundStatus(inboundStatus);
	}

	/**
	* Sets the item master sid of this ps details.
	*
	* @param itemMasterSid the item master sid of this ps details
	*/
	@Override
	public void setItemMasterSid(int itemMasterSid) {
		_psDetails.setItemMasterSid(itemMasterSid);
	}

	/**
	* Sets the item pricing qualifier sid of this ps details.
	*
	* @param itemPricingQualifierSid the item pricing qualifier sid of this ps details
	*/
	@Override
	public void setItemPricingQualifierSid(int itemPricingQualifierSid) {
		_psDetails.setItemPricingQualifierSid(itemPricingQualifierSid);
	}

	/**
	* Sets the item ps attached date of this ps details.
	*
	* @param itemPsAttachedDate the item ps attached date of this ps details
	*/
	@Override
	public void setItemPsAttachedDate(Date itemPsAttachedDate) {
		_psDetails.setItemPsAttachedDate(itemPsAttachedDate);
	}

	/**
	* Sets the item ps attached status of this ps details.
	*
	* @param itemPsAttachedStatus the item ps attached status of this ps details
	*/
	@Override
	public void setItemPsAttachedStatus(int itemPsAttachedStatus) {
		_psDetails.setItemPsAttachedStatus(itemPsAttachedStatus);
	}

	/**
	* Sets the max incremental change of this ps details.
	*
	* @param maxIncrementalChange the max incremental change of this ps details
	*/
	@Override
	public void setMaxIncrementalChange(double maxIncrementalChange) {
		_psDetails.setMaxIncrementalChange(maxIncrementalChange);
	}

	/**
	* Sets the modified by of this ps details.
	*
	* @param modifiedBy the modified by of this ps details
	*/
	@Override
	public void setModifiedBy(int modifiedBy) {
		_psDetails.setModifiedBy(modifiedBy);
	}

	/**
	* Sets the modified date of this ps details.
	*
	* @param modifiedDate the modified date of this ps details
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_psDetails.setModifiedDate(modifiedDate);
	}

	/**
	* Sets the nep of this ps details.
	*
	* @param nep the nep of this ps details
	*/
	@Override
	public void setNep(double nep) {
		_psDetails.setNep(nep);
	}

	/**
	* Sets the nep formula of this ps details.
	*
	* @param nepFormula the nep formula of this ps details
	*/
	@Override
	public void setNepFormula(int nepFormula) {
		_psDetails.setNepFormula(nepFormula);
	}

	/**
	* Sets the net base price of this ps details.
	*
	* @param netBasePrice the net base price of this ps details
	*/
	@Override
	public void setNetBasePrice(int netBasePrice) {
		_psDetails.setNetBasePrice(netBasePrice);
	}

	/**
	* Sets the net base price formula ID of this ps details.
	*
	* @param netBasePriceFormulaId the net base price formula ID of this ps details
	*/
	@Override
	public void setNetBasePriceFormulaId(int netBasePriceFormulaId) {
		_psDetails.setNetBasePriceFormulaId(netBasePriceFormulaId);
	}

	/**
	* Sets the net price type of this ps details.
	*
	* @param netPriceType the net price type of this ps details
	*/
	@Override
	public void setNetPriceType(int netPriceType) {
		_psDetails.setNetPriceType(netPriceType);
	}

	/**
	* Sets the net price type formula of this ps details.
	*
	* @param netPriceTypeFormula the net price type formula of this ps details
	*/
	@Override
	public void setNetPriceTypeFormula(java.lang.String netPriceTypeFormula) {
		_psDetails.setNetPriceTypeFormula(netPriceTypeFormula);
	}

	/**
	* Sets the net reset price formula ID of this ps details.
	*
	* @param netResetPriceFormulaId the net reset price formula ID of this ps details
	*/
	@Override
	public void setNetResetPriceFormulaId(int netResetPriceFormulaId) {
		_psDetails.setNetResetPriceFormulaId(netResetPriceFormulaId);
	}

	/**
	* Sets the net reset price type of this ps details.
	*
	* @param netResetPriceType the net reset price type of this ps details
	*/
	@Override
	public void setNetResetPriceType(int netResetPriceType) {
		_psDetails.setNetResetPriceType(netResetPriceType);
	}

	/**
	* Sets the net subsequent period price of this ps details.
	*
	* @param netSubsequentPeriodPrice the net subsequent period price of this ps details
	*/
	@Override
	public void setNetSubsequentPeriodPrice(int netSubsequentPeriodPrice) {
		_psDetails.setNetSubsequentPeriodPrice(netSubsequentPeriodPrice);
	}

	/**
	* Sets the net subsequent price formula ID of this ps details.
	*
	* @param netSubsequentPriceFormulaId the net subsequent price formula ID of this ps details
	*/
	@Override
	public void setNetSubsequentPriceFormulaId(int netSubsequentPriceFormulaId) {
		_psDetails.setNetSubsequentPriceFormulaId(netSubsequentPriceFormulaId);
	}

	@Override
	public void setNew(boolean n) {
		_psDetails.setNew(n);
	}

	/**
	* Sets the price of this ps details.
	*
	* @param price the price of this ps details
	*/
	@Override
	public void setPrice(double price) {
		_psDetails.setPrice(price);
	}

	/**
	* Sets the price protection end date of this ps details.
	*
	* @param priceProtectionEndDate the price protection end date of this ps details
	*/
	@Override
	public void setPriceProtectionEndDate(Date priceProtectionEndDate) {
		_psDetails.setPriceProtectionEndDate(priceProtectionEndDate);
	}

	/**
	* Sets the price protection price type of this ps details.
	*
	* @param priceProtectionPriceType the price protection price type of this ps details
	*/
	@Override
	public void setPriceProtectionPriceType(int priceProtectionPriceType) {
		_psDetails.setPriceProtectionPriceType(priceProtectionPriceType);
	}

	/**
	* Sets the price protection start date of this ps details.
	*
	* @param priceProtectionStartDate the price protection start date of this ps details
	*/
	@Override
	public void setPriceProtectionStartDate(Date priceProtectionStartDate) {
		_psDetails.setPriceProtectionStartDate(priceProtectionStartDate);
	}

	/**
	* Sets the price protection status of this ps details.
	*
	* @param priceProtectionStatus the price protection status of this ps details
	*/
	@Override
	public void setPriceProtectionStatus(int priceProtectionStatus) {
		_psDetails.setPriceProtectionStatus(priceProtectionStatus);
	}

	/**
	* Sets the price revision of this ps details.
	*
	* @param priceRevision the price revision of this ps details
	*/
	@Override
	public void setPriceRevision(double priceRevision) {
		_psDetails.setPriceRevision(priceRevision);
	}

	/**
	* Sets the price tolerance of this ps details.
	*
	* @param priceTolerance the price tolerance of this ps details
	*/
	@Override
	public void setPriceTolerance(double priceTolerance) {
		_psDetails.setPriceTolerance(priceTolerance);
	}

	/**
	* Sets the price tolerance frequency of this ps details.
	*
	* @param priceToleranceFrequency the price tolerance frequency of this ps details
	*/
	@Override
	public void setPriceToleranceFrequency(int priceToleranceFrequency) {
		_psDetails.setPriceToleranceFrequency(priceToleranceFrequency);
	}

	/**
	* Sets the price tolerance interval of this ps details.
	*
	* @param priceToleranceInterval the price tolerance interval of this ps details
	*/
	@Override
	public void setPriceToleranceInterval(int priceToleranceInterval) {
		_psDetails.setPriceToleranceInterval(priceToleranceInterval);
	}

	/**
	* Sets the price tolerance type of this ps details.
	*
	* @param priceToleranceType the price tolerance type of this ps details
	*/
	@Override
	public void setPriceToleranceType(int priceToleranceType) {
		_psDetails.setPriceToleranceType(priceToleranceType);
	}

	/**
	* Sets the primary key of this ps details.
	*
	* @param primaryKey the primary key of this ps details
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_psDetails.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_psDetails.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the ps details sid of this ps details.
	*
	* @param psDetailsSid the ps details sid of this ps details
	*/
	@Override
	public void setPsDetailsSid(int psDetailsSid) {
		_psDetails.setPsDetailsSid(psDetailsSid);
	}

	/**
	* Sets the ps model sid of this ps details.
	*
	* @param psModelSid the ps model sid of this ps details
	*/
	@Override
	public void setPsModelSid(int psModelSid) {
		_psDetails.setPsModelSid(psModelSid);
	}

	/**
	* Sets whether this ps details is record lock status.
	*
	* @param recordLockStatus the record lock status of this ps details
	*/
	@Override
	public void setRecordLockStatus(boolean recordLockStatus) {
		_psDetails.setRecordLockStatus(recordLockStatus);
	}

	/**
	* Sets the reset date of this ps details.
	*
	* @param resetDate the reset date of this ps details
	*/
	@Override
	public void setResetDate(Date resetDate) {
		_psDetails.setResetDate(resetDate);
	}

	/**
	* Sets the reset eligible of this ps details.
	*
	* @param resetEligible the reset eligible of this ps details
	*/
	@Override
	public void setResetEligible(int resetEligible) {
		_psDetails.setResetEligible(resetEligible);
	}

	/**
	* Sets the reset frequency of this ps details.
	*
	* @param resetFrequency the reset frequency of this ps details
	*/
	@Override
	public void setResetFrequency(int resetFrequency) {
		_psDetails.setResetFrequency(resetFrequency);
	}

	/**
	* Sets the reset interval of this ps details.
	*
	* @param resetInterval the reset interval of this ps details
	*/
	@Override
	public void setResetInterval(int resetInterval) {
		_psDetails.setResetInterval(resetInterval);
	}

	/**
	* Sets the reset price type of this ps details.
	*
	* @param resetPriceType the reset price type of this ps details
	*/
	@Override
	public void setResetPriceType(int resetPriceType) {
		_psDetails.setResetPriceType(resetPriceType);
	}

	/**
	* Sets the reset type of this ps details.
	*
	* @param resetType the reset type of this ps details
	*/
	@Override
	public void setResetType(int resetType) {
		_psDetails.setResetType(resetType);
	}

	/**
	* Sets the revision date of this ps details.
	*
	* @param revisionDate the revision date of this ps details
	*/
	@Override
	public void setRevisionDate(Date revisionDate) {
		_psDetails.setRevisionDate(revisionDate);
	}

	/**
	* Sets the source of this ps details.
	*
	* @param source the source of this ps details
	*/
	@Override
	public void setSource(java.lang.String source) {
		_psDetails.setSource(source);
	}

	/**
	* Sets the status of this ps details.
	*
	* @param status the status of this ps details
	*/
	@Override
	public void setStatus(int status) {
		_psDetails.setStatus(status);
	}

	/**
	* Sets the subsequent period price type of this ps details.
	*
	* @param subsequentPeriodPriceType the subsequent period price type of this ps details
	*/
	@Override
	public void setSubsequentPeriodPriceType(int subsequentPeriodPriceType) {
		_psDetails.setSubsequentPeriodPriceType(subsequentPeriodPriceType);
	}

	/**
	* Sets the suggested price of this ps details.
	*
	* @param suggestedPrice the suggested price of this ps details
	*/
	@Override
	public void setSuggestedPrice(double suggestedPrice) {
		_psDetails.setSuggestedPrice(suggestedPrice);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<PsDetails> toCacheModel() {
		return _psDetails.toCacheModel();
	}

	@Override
	public PsDetails toEscapedModel() {
		return new PsDetailsWrapper(_psDetails.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _psDetails.toString();
	}

	@Override
	public PsDetails toUnescapedModel() {
		return new PsDetailsWrapper(_psDetails.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _psDetails.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof PsDetailsWrapper)) {
			return false;
		}

		PsDetailsWrapper psDetailsWrapper = (PsDetailsWrapper)obj;

		if (Objects.equals(_psDetails, psDetailsWrapper._psDetails)) {
			return true;
		}

		return false;
	}

	@Override
	public PsDetails getWrappedModel() {
		return _psDetails;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _psDetails.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _psDetails.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_psDetails.resetOriginalValues();
	}

	private final PsDetails _psDetails;
}