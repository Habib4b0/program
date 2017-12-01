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
 * This class is a wrapper for {@link ImtdPsDetails}.
 * </p>
 *
 * @author
 * @see ImtdPsDetails
 * @generated
 */
@ProviderType
public class ImtdPsDetailsWrapper implements ImtdPsDetails,
	ModelWrapper<ImtdPsDetails> {
	public ImtdPsDetailsWrapper(ImtdPsDetails imtdPsDetails) {
		_imtdPsDetails = imtdPsDetails;
	}

	@Override
	public Class<?> getModelClass() {
		return ImtdPsDetails.class;
	}

	@Override
	public String getModelClassName() {
		return ImtdPsDetails.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("psDetailsModifiedDate", getPsDetailsModifiedDate());
		attributes.put("psDetailsSuggestedPrice", getPsDetailsSuggestedPrice());
		attributes.put("psDetailsContractPrice", getPsDetailsContractPrice());
		attributes.put("resetDate", getResetDate());
		attributes.put("psDetailsAttachedStatus", getPsDetailsAttachedStatus());
		attributes.put("imtdPsDetailsSid", getImtdPsDetailsSid());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("psDetailsCreatedBy", getPsDetailsCreatedBy());
		attributes.put("contractMasterSid", getContractMasterSid());
		attributes.put("psDtlsContPriceEnddate", getPsDtlsContPriceEnddate());
		attributes.put("psDetailsPricPrtcnStdate", getPsDetailsPricPrtcnStdate());
		attributes.put("imtdCreatedDate", getImtdCreatedDate());
		attributes.put("netPriceTypeFormula", getNetPriceTypeFormula());
		attributes.put("modifiedBy", getModifiedBy());
		attributes.put("maxIncrementalChange", getMaxIncrementalChange());
		attributes.put("psDetailsPricePlanId", getPsDetailsPricePlanId());
		attributes.put("checkRecord", getCheckRecord());
		attributes.put("psDtlsPriceToleranceFreq", getPsDtlsPriceToleranceFreq());
		attributes.put("itemName", getItemName());
		attributes.put("sessionId", getSessionId());
		attributes.put("resetFrequency", getResetFrequency());
		attributes.put("psDtlsPriceToleranceType", getPsDtlsPriceToleranceType());
		attributes.put("psDetailsPricetype", getPsDetailsPricetype());
		attributes.put("psDetailsPriceRevision", getPsDetailsPriceRevision());
		attributes.put("resetInterval", getResetInterval());
		attributes.put("ifpNo", getIfpNo());
		attributes.put("psDetailsAttachedDate", getPsDetailsAttachedDate());
		attributes.put("nepFormula", getNepFormula());
		attributes.put("psDetailsModifiedBy", getPsDetailsModifiedBy());
		attributes.put("psDtlsPriceToleranceIntrvl",
			getPsDtlsPriceToleranceIntrvl());
		attributes.put("itemMasterSid", getItemMasterSid());
		attributes.put("resetType", getResetType());
		attributes.put("itemId", getItemId());
		attributes.put("status", getStatus());
		attributes.put("brandMasterSid", getBrandMasterSid());
		attributes.put("psDetailsPrice", getPsDetailsPrice());
		attributes.put("psDetailsCreatedDate", getPsDetailsCreatedDate());
		attributes.put("usersSid", getUsersSid());
		attributes.put("createdBy", getCreatedBy());
		attributes.put("createdDate", getCreatedDate());
		attributes.put("psDetailsSid", getPsDetailsSid());
		attributes.put("psModelSid", getPsModelSid());
		attributes.put("priceProtectionPriceType", getPriceProtectionPriceType());
		attributes.put("psDetailsBasePrice", getPsDetailsBasePrice());
		attributes.put("itemNo", getItemNo());
		attributes.put("ifpModelSid", getIfpModelSid());
		attributes.put("psDetailsRevisionDate", getPsDetailsRevisionDate());
		attributes.put("nep", getNep());
		attributes.put("psDetailsPriceTolerance", getPsDetailsPriceTolerance());
		attributes.put("priceProtectionStatus", getPriceProtectionStatus());
		attributes.put("psDtlsContPriceStartdate", getPsDtlsContPriceStartdate());
		attributes.put("resetEligible", getResetEligible());
		attributes.put("netPriceType", getNetPriceType());
		attributes.put("operation", getOperation());
		attributes.put("cfpModelSid", getCfpModelSid());
		attributes.put("psDetailsPricPrtcnEddate", getPsDetailsPricPrtcnEddate());
		attributes.put("basePriceType", getBasePriceType());
		attributes.put("basePriceEntry", getBasePriceEntry());
		attributes.put("basePriceDate", getBasePriceDate());
		attributes.put("basePriceDdlb", getBasePriceDdlb());
		attributes.put("netBasePrice", getNetBasePrice());
		attributes.put("netBasePriceFormulaId", getNetBasePriceFormulaId());
		attributes.put("netBasePriceFormulaNo", getNetBasePriceFormulaNo());
		attributes.put("netBasePriceFormulaName", getNetBasePriceFormulaName());
		attributes.put("subsequentPeriodPriceType",
			getSubsequentPeriodPriceType());
		attributes.put("netSubsequentPeriodPrice", getNetSubsequentPeriodPrice());
		attributes.put("netSubsequentPriceFormulaId",
			getNetSubsequentPriceFormulaId());
		attributes.put("netSubsequentPriceFormulaNo",
			getNetSubsequentPriceFormulaNo());
		attributes.put("netSubsequentPriceFormulaName",
			getNetSubsequentPriceFormulaName());
		attributes.put("resetPriceType", getResetPriceType());
		attributes.put("netResetPriceType", getNetResetPriceType());
		attributes.put("netResetPriceFormulaId", getNetResetPriceFormulaId());
		attributes.put("netResetPriceFormulaNo", getNetResetPriceFormulaNo());
		attributes.put("netResetPriceFormulaName", getNetResetPriceFormulaName());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Date psDetailsModifiedDate = (Date)attributes.get(
				"psDetailsModifiedDate");

		if (psDetailsModifiedDate != null) {
			setPsDetailsModifiedDate(psDetailsModifiedDate);
		}

		Double psDetailsSuggestedPrice = (Double)attributes.get(
				"psDetailsSuggestedPrice");

		if (psDetailsSuggestedPrice != null) {
			setPsDetailsSuggestedPrice(psDetailsSuggestedPrice);
		}

		Double psDetailsContractPrice = (Double)attributes.get(
				"psDetailsContractPrice");

		if (psDetailsContractPrice != null) {
			setPsDetailsContractPrice(psDetailsContractPrice);
		}

		Date resetDate = (Date)attributes.get("resetDate");

		if (resetDate != null) {
			setResetDate(resetDate);
		}

		Integer psDetailsAttachedStatus = (Integer)attributes.get(
				"psDetailsAttachedStatus");

		if (psDetailsAttachedStatus != null) {
			setPsDetailsAttachedStatus(psDetailsAttachedStatus);
		}

		Integer imtdPsDetailsSid = (Integer)attributes.get("imtdPsDetailsSid");

		if (imtdPsDetailsSid != null) {
			setImtdPsDetailsSid(imtdPsDetailsSid);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Integer psDetailsCreatedBy = (Integer)attributes.get(
				"psDetailsCreatedBy");

		if (psDetailsCreatedBy != null) {
			setPsDetailsCreatedBy(psDetailsCreatedBy);
		}

		Integer contractMasterSid = (Integer)attributes.get("contractMasterSid");

		if (contractMasterSid != null) {
			setContractMasterSid(contractMasterSid);
		}

		Date psDtlsContPriceEnddate = (Date)attributes.get(
				"psDtlsContPriceEnddate");

		if (psDtlsContPriceEnddate != null) {
			setPsDtlsContPriceEnddate(psDtlsContPriceEnddate);
		}

		Date psDetailsPricPrtcnStdate = (Date)attributes.get(
				"psDetailsPricPrtcnStdate");

		if (psDetailsPricPrtcnStdate != null) {
			setPsDetailsPricPrtcnStdate(psDetailsPricPrtcnStdate);
		}

		Date imtdCreatedDate = (Date)attributes.get("imtdCreatedDate");

		if (imtdCreatedDate != null) {
			setImtdCreatedDate(imtdCreatedDate);
		}

		String netPriceTypeFormula = (String)attributes.get(
				"netPriceTypeFormula");

		if (netPriceTypeFormula != null) {
			setNetPriceTypeFormula(netPriceTypeFormula);
		}

		Integer modifiedBy = (Integer)attributes.get("modifiedBy");

		if (modifiedBy != null) {
			setModifiedBy(modifiedBy);
		}

		Double maxIncrementalChange = (Double)attributes.get(
				"maxIncrementalChange");

		if (maxIncrementalChange != null) {
			setMaxIncrementalChange(maxIncrementalChange);
		}

		String psDetailsPricePlanId = (String)attributes.get(
				"psDetailsPricePlanId");

		if (psDetailsPricePlanId != null) {
			setPsDetailsPricePlanId(psDetailsPricePlanId);
		}

		Boolean checkRecord = (Boolean)attributes.get("checkRecord");

		if (checkRecord != null) {
			setCheckRecord(checkRecord);
		}

		Integer psDtlsPriceToleranceFreq = (Integer)attributes.get(
				"psDtlsPriceToleranceFreq");

		if (psDtlsPriceToleranceFreq != null) {
			setPsDtlsPriceToleranceFreq(psDtlsPriceToleranceFreq);
		}

		String itemName = (String)attributes.get("itemName");

		if (itemName != null) {
			setItemName(itemName);
		}

		String sessionId = (String)attributes.get("sessionId");

		if (sessionId != null) {
			setSessionId(sessionId);
		}

		Integer resetFrequency = (Integer)attributes.get("resetFrequency");

		if (resetFrequency != null) {
			setResetFrequency(resetFrequency);
		}

		Integer psDtlsPriceToleranceType = (Integer)attributes.get(
				"psDtlsPriceToleranceType");

		if (psDtlsPriceToleranceType != null) {
			setPsDtlsPriceToleranceType(psDtlsPriceToleranceType);
		}

		Integer psDetailsPricetype = (Integer)attributes.get(
				"psDetailsPricetype");

		if (psDetailsPricetype != null) {
			setPsDetailsPricetype(psDetailsPricetype);
		}

		Double psDetailsPriceRevision = (Double)attributes.get(
				"psDetailsPriceRevision");

		if (psDetailsPriceRevision != null) {
			setPsDetailsPriceRevision(psDetailsPriceRevision);
		}

		Integer resetInterval = (Integer)attributes.get("resetInterval");

		if (resetInterval != null) {
			setResetInterval(resetInterval);
		}

		String ifpNo = (String)attributes.get("ifpNo");

		if (ifpNo != null) {
			setIfpNo(ifpNo);
		}

		Date psDetailsAttachedDate = (Date)attributes.get(
				"psDetailsAttachedDate");

		if (psDetailsAttachedDate != null) {
			setPsDetailsAttachedDate(psDetailsAttachedDate);
		}

		Integer nepFormula = (Integer)attributes.get("nepFormula");

		if (nepFormula != null) {
			setNepFormula(nepFormula);
		}

		Integer psDetailsModifiedBy = (Integer)attributes.get(
				"psDetailsModifiedBy");

		if (psDetailsModifiedBy != null) {
			setPsDetailsModifiedBy(psDetailsModifiedBy);
		}

		Integer psDtlsPriceToleranceIntrvl = (Integer)attributes.get(
				"psDtlsPriceToleranceIntrvl");

		if (psDtlsPriceToleranceIntrvl != null) {
			setPsDtlsPriceToleranceIntrvl(psDtlsPriceToleranceIntrvl);
		}

		Integer itemMasterSid = (Integer)attributes.get("itemMasterSid");

		if (itemMasterSid != null) {
			setItemMasterSid(itemMasterSid);
		}

		Integer resetType = (Integer)attributes.get("resetType");

		if (resetType != null) {
			setResetType(resetType);
		}

		String itemId = (String)attributes.get("itemId");

		if (itemId != null) {
			setItemId(itemId);
		}

		Integer status = (Integer)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}

		Integer brandMasterSid = (Integer)attributes.get("brandMasterSid");

		if (brandMasterSid != null) {
			setBrandMasterSid(brandMasterSid);
		}

		Double psDetailsPrice = (Double)attributes.get("psDetailsPrice");

		if (psDetailsPrice != null) {
			setPsDetailsPrice(psDetailsPrice);
		}

		Date psDetailsCreatedDate = (Date)attributes.get("psDetailsCreatedDate");

		if (psDetailsCreatedDate != null) {
			setPsDetailsCreatedDate(psDetailsCreatedDate);
		}

		Integer usersSid = (Integer)attributes.get("usersSid");

		if (usersSid != null) {
			setUsersSid(usersSid);
		}

		Integer createdBy = (Integer)attributes.get("createdBy");

		if (createdBy != null) {
			setCreatedBy(createdBy);
		}

		Date createdDate = (Date)attributes.get("createdDate");

		if (createdDate != null) {
			setCreatedDate(createdDate);
		}

		Integer psDetailsSid = (Integer)attributes.get("psDetailsSid");

		if (psDetailsSid != null) {
			setPsDetailsSid(psDetailsSid);
		}

		Integer psModelSid = (Integer)attributes.get("psModelSid");

		if (psModelSid != null) {
			setPsModelSid(psModelSid);
		}

		Integer priceProtectionPriceType = (Integer)attributes.get(
				"priceProtectionPriceType");

		if (priceProtectionPriceType != null) {
			setPriceProtectionPriceType(priceProtectionPriceType);
		}

		Double psDetailsBasePrice = (Double)attributes.get("psDetailsBasePrice");

		if (psDetailsBasePrice != null) {
			setPsDetailsBasePrice(psDetailsBasePrice);
		}

		String itemNo = (String)attributes.get("itemNo");

		if (itemNo != null) {
			setItemNo(itemNo);
		}

		Integer ifpModelSid = (Integer)attributes.get("ifpModelSid");

		if (ifpModelSid != null) {
			setIfpModelSid(ifpModelSid);
		}

		Date psDetailsRevisionDate = (Date)attributes.get(
				"psDetailsRevisionDate");

		if (psDetailsRevisionDate != null) {
			setPsDetailsRevisionDate(psDetailsRevisionDate);
		}

		Double nep = (Double)attributes.get("nep");

		if (nep != null) {
			setNep(nep);
		}

		Double psDetailsPriceTolerance = (Double)attributes.get(
				"psDetailsPriceTolerance");

		if (psDetailsPriceTolerance != null) {
			setPsDetailsPriceTolerance(psDetailsPriceTolerance);
		}

		Integer priceProtectionStatus = (Integer)attributes.get(
				"priceProtectionStatus");

		if (priceProtectionStatus != null) {
			setPriceProtectionStatus(priceProtectionStatus);
		}

		Date psDtlsContPriceStartdate = (Date)attributes.get(
				"psDtlsContPriceStartdate");

		if (psDtlsContPriceStartdate != null) {
			setPsDtlsContPriceStartdate(psDtlsContPriceStartdate);
		}

		Integer resetEligible = (Integer)attributes.get("resetEligible");

		if (resetEligible != null) {
			setResetEligible(resetEligible);
		}

		Integer netPriceType = (Integer)attributes.get("netPriceType");

		if (netPriceType != null) {
			setNetPriceType(netPriceType);
		}

		String operation = (String)attributes.get("operation");

		if (operation != null) {
			setOperation(operation);
		}

		Integer cfpModelSid = (Integer)attributes.get("cfpModelSid");

		if (cfpModelSid != null) {
			setCfpModelSid(cfpModelSid);
		}

		Date psDetailsPricPrtcnEddate = (Date)attributes.get(
				"psDetailsPricPrtcnEddate");

		if (psDetailsPricPrtcnEddate != null) {
			setPsDetailsPricPrtcnEddate(psDetailsPricPrtcnEddate);
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

		String netBasePriceFormulaNo = (String)attributes.get(
				"netBasePriceFormulaNo");

		if (netBasePriceFormulaNo != null) {
			setNetBasePriceFormulaNo(netBasePriceFormulaNo);
		}

		String netBasePriceFormulaName = (String)attributes.get(
				"netBasePriceFormulaName");

		if (netBasePriceFormulaName != null) {
			setNetBasePriceFormulaName(netBasePriceFormulaName);
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

		String netSubsequentPriceFormulaNo = (String)attributes.get(
				"netSubsequentPriceFormulaNo");

		if (netSubsequentPriceFormulaNo != null) {
			setNetSubsequentPriceFormulaNo(netSubsequentPriceFormulaNo);
		}

		String netSubsequentPriceFormulaName = (String)attributes.get(
				"netSubsequentPriceFormulaName");

		if (netSubsequentPriceFormulaName != null) {
			setNetSubsequentPriceFormulaName(netSubsequentPriceFormulaName);
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

		String netResetPriceFormulaNo = (String)attributes.get(
				"netResetPriceFormulaNo");

		if (netResetPriceFormulaNo != null) {
			setNetResetPriceFormulaNo(netResetPriceFormulaNo);
		}

		String netResetPriceFormulaName = (String)attributes.get(
				"netResetPriceFormulaName");

		if (netResetPriceFormulaName != null) {
			setNetResetPriceFormulaName(netResetPriceFormulaName);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new ImtdPsDetailsWrapper((ImtdPsDetails)_imtdPsDetails.clone());
	}

	@Override
	public int compareTo(ImtdPsDetails imtdPsDetails) {
		return _imtdPsDetails.compareTo(imtdPsDetails);
	}

	/**
	* Returns the base price date of this imtd ps details.
	*
	* @return the base price date of this imtd ps details
	*/
	@Override
	public Date getBasePriceDate() {
		return _imtdPsDetails.getBasePriceDate();
	}

	/**
	* Returns the base price ddlb of this imtd ps details.
	*
	* @return the base price ddlb of this imtd ps details
	*/
	@Override
	public int getBasePriceDdlb() {
		return _imtdPsDetails.getBasePriceDdlb();
	}

	/**
	* Returns the base price entry of this imtd ps details.
	*
	* @return the base price entry of this imtd ps details
	*/
	@Override
	public double getBasePriceEntry() {
		return _imtdPsDetails.getBasePriceEntry();
	}

	/**
	* Returns the base price type of this imtd ps details.
	*
	* @return the base price type of this imtd ps details
	*/
	@Override
	public int getBasePriceType() {
		return _imtdPsDetails.getBasePriceType();
	}

	/**
	* Returns the brand master sid of this imtd ps details.
	*
	* @return the brand master sid of this imtd ps details
	*/
	@Override
	public int getBrandMasterSid() {
		return _imtdPsDetails.getBrandMasterSid();
	}

	/**
	* Returns the cfp model sid of this imtd ps details.
	*
	* @return the cfp model sid of this imtd ps details
	*/
	@Override
	public int getCfpModelSid() {
		return _imtdPsDetails.getCfpModelSid();
	}

	/**
	* Returns the check record of this imtd ps details.
	*
	* @return the check record of this imtd ps details
	*/
	@Override
	public boolean getCheckRecord() {
		return _imtdPsDetails.getCheckRecord();
	}

	/**
	* Returns the contract master sid of this imtd ps details.
	*
	* @return the contract master sid of this imtd ps details
	*/
	@Override
	public int getContractMasterSid() {
		return _imtdPsDetails.getContractMasterSid();
	}

	/**
	* Returns the created by of this imtd ps details.
	*
	* @return the created by of this imtd ps details
	*/
	@Override
	public int getCreatedBy() {
		return _imtdPsDetails.getCreatedBy();
	}

	/**
	* Returns the created date of this imtd ps details.
	*
	* @return the created date of this imtd ps details
	*/
	@Override
	public Date getCreatedDate() {
		return _imtdPsDetails.getCreatedDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _imtdPsDetails.getExpandoBridge();
	}

	/**
	* Returns the ifp model sid of this imtd ps details.
	*
	* @return the ifp model sid of this imtd ps details
	*/
	@Override
	public int getIfpModelSid() {
		return _imtdPsDetails.getIfpModelSid();
	}

	/**
	* Returns the ifp no of this imtd ps details.
	*
	* @return the ifp no of this imtd ps details
	*/
	@Override
	public java.lang.String getIfpNo() {
		return _imtdPsDetails.getIfpNo();
	}

	/**
	* Returns the imtd created date of this imtd ps details.
	*
	* @return the imtd created date of this imtd ps details
	*/
	@Override
	public Date getImtdCreatedDate() {
		return _imtdPsDetails.getImtdCreatedDate();
	}

	/**
	* Returns the imtd ps details sid of this imtd ps details.
	*
	* @return the imtd ps details sid of this imtd ps details
	*/
	@Override
	public int getImtdPsDetailsSid() {
		return _imtdPsDetails.getImtdPsDetailsSid();
	}

	/**
	* Returns the item ID of this imtd ps details.
	*
	* @return the item ID of this imtd ps details
	*/
	@Override
	public java.lang.String getItemId() {
		return _imtdPsDetails.getItemId();
	}

	/**
	* Returns the item master sid of this imtd ps details.
	*
	* @return the item master sid of this imtd ps details
	*/
	@Override
	public int getItemMasterSid() {
		return _imtdPsDetails.getItemMasterSid();
	}

	/**
	* Returns the item name of this imtd ps details.
	*
	* @return the item name of this imtd ps details
	*/
	@Override
	public java.lang.String getItemName() {
		return _imtdPsDetails.getItemName();
	}

	/**
	* Returns the item no of this imtd ps details.
	*
	* @return the item no of this imtd ps details
	*/
	@Override
	public java.lang.String getItemNo() {
		return _imtdPsDetails.getItemNo();
	}

	/**
	* Returns the max incremental change of this imtd ps details.
	*
	* @return the max incremental change of this imtd ps details
	*/
	@Override
	public double getMaxIncrementalChange() {
		return _imtdPsDetails.getMaxIncrementalChange();
	}

	/**
	* Returns the modified by of this imtd ps details.
	*
	* @return the modified by of this imtd ps details
	*/
	@Override
	public int getModifiedBy() {
		return _imtdPsDetails.getModifiedBy();
	}

	/**
	* Returns the modified date of this imtd ps details.
	*
	* @return the modified date of this imtd ps details
	*/
	@Override
	public Date getModifiedDate() {
		return _imtdPsDetails.getModifiedDate();
	}

	/**
	* Returns the nep of this imtd ps details.
	*
	* @return the nep of this imtd ps details
	*/
	@Override
	public double getNep() {
		return _imtdPsDetails.getNep();
	}

	/**
	* Returns the nep formula of this imtd ps details.
	*
	* @return the nep formula of this imtd ps details
	*/
	@Override
	public int getNepFormula() {
		return _imtdPsDetails.getNepFormula();
	}

	/**
	* Returns the net base price of this imtd ps details.
	*
	* @return the net base price of this imtd ps details
	*/
	@Override
	public int getNetBasePrice() {
		return _imtdPsDetails.getNetBasePrice();
	}

	/**
	* Returns the net base price formula ID of this imtd ps details.
	*
	* @return the net base price formula ID of this imtd ps details
	*/
	@Override
	public int getNetBasePriceFormulaId() {
		return _imtdPsDetails.getNetBasePriceFormulaId();
	}

	/**
	* Returns the net base price formula name of this imtd ps details.
	*
	* @return the net base price formula name of this imtd ps details
	*/
	@Override
	public java.lang.String getNetBasePriceFormulaName() {
		return _imtdPsDetails.getNetBasePriceFormulaName();
	}

	/**
	* Returns the net base price formula no of this imtd ps details.
	*
	* @return the net base price formula no of this imtd ps details
	*/
	@Override
	public java.lang.String getNetBasePriceFormulaNo() {
		return _imtdPsDetails.getNetBasePriceFormulaNo();
	}

	/**
	* Returns the net price type of this imtd ps details.
	*
	* @return the net price type of this imtd ps details
	*/
	@Override
	public int getNetPriceType() {
		return _imtdPsDetails.getNetPriceType();
	}

	/**
	* Returns the net price type formula of this imtd ps details.
	*
	* @return the net price type formula of this imtd ps details
	*/
	@Override
	public java.lang.String getNetPriceTypeFormula() {
		return _imtdPsDetails.getNetPriceTypeFormula();
	}

	/**
	* Returns the net reset price formula ID of this imtd ps details.
	*
	* @return the net reset price formula ID of this imtd ps details
	*/
	@Override
	public int getNetResetPriceFormulaId() {
		return _imtdPsDetails.getNetResetPriceFormulaId();
	}

	/**
	* Returns the net reset price formula name of this imtd ps details.
	*
	* @return the net reset price formula name of this imtd ps details
	*/
	@Override
	public java.lang.String getNetResetPriceFormulaName() {
		return _imtdPsDetails.getNetResetPriceFormulaName();
	}

	/**
	* Returns the net reset price formula no of this imtd ps details.
	*
	* @return the net reset price formula no of this imtd ps details
	*/
	@Override
	public java.lang.String getNetResetPriceFormulaNo() {
		return _imtdPsDetails.getNetResetPriceFormulaNo();
	}

	/**
	* Returns the net reset price type of this imtd ps details.
	*
	* @return the net reset price type of this imtd ps details
	*/
	@Override
	public int getNetResetPriceType() {
		return _imtdPsDetails.getNetResetPriceType();
	}

	/**
	* Returns the net subsequent period price of this imtd ps details.
	*
	* @return the net subsequent period price of this imtd ps details
	*/
	@Override
	public int getNetSubsequentPeriodPrice() {
		return _imtdPsDetails.getNetSubsequentPeriodPrice();
	}

	/**
	* Returns the net subsequent price formula ID of this imtd ps details.
	*
	* @return the net subsequent price formula ID of this imtd ps details
	*/
	@Override
	public int getNetSubsequentPriceFormulaId() {
		return _imtdPsDetails.getNetSubsequentPriceFormulaId();
	}

	/**
	* Returns the net subsequent price formula name of this imtd ps details.
	*
	* @return the net subsequent price formula name of this imtd ps details
	*/
	@Override
	public java.lang.String getNetSubsequentPriceFormulaName() {
		return _imtdPsDetails.getNetSubsequentPriceFormulaName();
	}

	/**
	* Returns the net subsequent price formula no of this imtd ps details.
	*
	* @return the net subsequent price formula no of this imtd ps details
	*/
	@Override
	public java.lang.String getNetSubsequentPriceFormulaNo() {
		return _imtdPsDetails.getNetSubsequentPriceFormulaNo();
	}

	/**
	* Returns the operation of this imtd ps details.
	*
	* @return the operation of this imtd ps details
	*/
	@Override
	public java.lang.String getOperation() {
		return _imtdPsDetails.getOperation();
	}

	/**
	* Returns the price protection price type of this imtd ps details.
	*
	* @return the price protection price type of this imtd ps details
	*/
	@Override
	public int getPriceProtectionPriceType() {
		return _imtdPsDetails.getPriceProtectionPriceType();
	}

	/**
	* Returns the price protection status of this imtd ps details.
	*
	* @return the price protection status of this imtd ps details
	*/
	@Override
	public int getPriceProtectionStatus() {
		return _imtdPsDetails.getPriceProtectionStatus();
	}

	/**
	* Returns the primary key of this imtd ps details.
	*
	* @return the primary key of this imtd ps details
	*/
	@Override
	public int getPrimaryKey() {
		return _imtdPsDetails.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _imtdPsDetails.getPrimaryKeyObj();
	}

	/**
	* Returns the ps details attached date of this imtd ps details.
	*
	* @return the ps details attached date of this imtd ps details
	*/
	@Override
	public Date getPsDetailsAttachedDate() {
		return _imtdPsDetails.getPsDetailsAttachedDate();
	}

	/**
	* Returns the ps details attached status of this imtd ps details.
	*
	* @return the ps details attached status of this imtd ps details
	*/
	@Override
	public int getPsDetailsAttachedStatus() {
		return _imtdPsDetails.getPsDetailsAttachedStatus();
	}

	/**
	* Returns the ps details base price of this imtd ps details.
	*
	* @return the ps details base price of this imtd ps details
	*/
	@Override
	public double getPsDetailsBasePrice() {
		return _imtdPsDetails.getPsDetailsBasePrice();
	}

	/**
	* Returns the ps details contract price of this imtd ps details.
	*
	* @return the ps details contract price of this imtd ps details
	*/
	@Override
	public double getPsDetailsContractPrice() {
		return _imtdPsDetails.getPsDetailsContractPrice();
	}

	/**
	* Returns the ps details created by of this imtd ps details.
	*
	* @return the ps details created by of this imtd ps details
	*/
	@Override
	public int getPsDetailsCreatedBy() {
		return _imtdPsDetails.getPsDetailsCreatedBy();
	}

	/**
	* Returns the ps details created date of this imtd ps details.
	*
	* @return the ps details created date of this imtd ps details
	*/
	@Override
	public Date getPsDetailsCreatedDate() {
		return _imtdPsDetails.getPsDetailsCreatedDate();
	}

	/**
	* Returns the ps details modified by of this imtd ps details.
	*
	* @return the ps details modified by of this imtd ps details
	*/
	@Override
	public int getPsDetailsModifiedBy() {
		return _imtdPsDetails.getPsDetailsModifiedBy();
	}

	/**
	* Returns the ps details modified date of this imtd ps details.
	*
	* @return the ps details modified date of this imtd ps details
	*/
	@Override
	public Date getPsDetailsModifiedDate() {
		return _imtdPsDetails.getPsDetailsModifiedDate();
	}

	/**
	* Returns the ps details price of this imtd ps details.
	*
	* @return the ps details price of this imtd ps details
	*/
	@Override
	public double getPsDetailsPrice() {
		return _imtdPsDetails.getPsDetailsPrice();
	}

	/**
	* Returns the ps details price plan ID of this imtd ps details.
	*
	* @return the ps details price plan ID of this imtd ps details
	*/
	@Override
	public java.lang.String getPsDetailsPricePlanId() {
		return _imtdPsDetails.getPsDetailsPricePlanId();
	}

	/**
	* Returns the ps details price revision of this imtd ps details.
	*
	* @return the ps details price revision of this imtd ps details
	*/
	@Override
	public double getPsDetailsPriceRevision() {
		return _imtdPsDetails.getPsDetailsPriceRevision();
	}

	/**
	* Returns the ps details price tolerance of this imtd ps details.
	*
	* @return the ps details price tolerance of this imtd ps details
	*/
	@Override
	public double getPsDetailsPriceTolerance() {
		return _imtdPsDetails.getPsDetailsPriceTolerance();
	}

	/**
	* Returns the ps details pricetype of this imtd ps details.
	*
	* @return the ps details pricetype of this imtd ps details
	*/
	@Override
	public int getPsDetailsPricetype() {
		return _imtdPsDetails.getPsDetailsPricetype();
	}

	/**
	* Returns the ps details pric prtcn eddate of this imtd ps details.
	*
	* @return the ps details pric prtcn eddate of this imtd ps details
	*/
	@Override
	public Date getPsDetailsPricPrtcnEddate() {
		return _imtdPsDetails.getPsDetailsPricPrtcnEddate();
	}

	/**
	* Returns the ps details pric prtcn stdate of this imtd ps details.
	*
	* @return the ps details pric prtcn stdate of this imtd ps details
	*/
	@Override
	public Date getPsDetailsPricPrtcnStdate() {
		return _imtdPsDetails.getPsDetailsPricPrtcnStdate();
	}

	/**
	* Returns the ps details revision date of this imtd ps details.
	*
	* @return the ps details revision date of this imtd ps details
	*/
	@Override
	public Date getPsDetailsRevisionDate() {
		return _imtdPsDetails.getPsDetailsRevisionDate();
	}

	/**
	* Returns the ps details sid of this imtd ps details.
	*
	* @return the ps details sid of this imtd ps details
	*/
	@Override
	public int getPsDetailsSid() {
		return _imtdPsDetails.getPsDetailsSid();
	}

	/**
	* Returns the ps details suggested price of this imtd ps details.
	*
	* @return the ps details suggested price of this imtd ps details
	*/
	@Override
	public double getPsDetailsSuggestedPrice() {
		return _imtdPsDetails.getPsDetailsSuggestedPrice();
	}

	/**
	* Returns the ps dtls cont price enddate of this imtd ps details.
	*
	* @return the ps dtls cont price enddate of this imtd ps details
	*/
	@Override
	public Date getPsDtlsContPriceEnddate() {
		return _imtdPsDetails.getPsDtlsContPriceEnddate();
	}

	/**
	* Returns the ps dtls cont price startdate of this imtd ps details.
	*
	* @return the ps dtls cont price startdate of this imtd ps details
	*/
	@Override
	public Date getPsDtlsContPriceStartdate() {
		return _imtdPsDetails.getPsDtlsContPriceStartdate();
	}

	/**
	* Returns the ps dtls price tolerance freq of this imtd ps details.
	*
	* @return the ps dtls price tolerance freq of this imtd ps details
	*/
	@Override
	public int getPsDtlsPriceToleranceFreq() {
		return _imtdPsDetails.getPsDtlsPriceToleranceFreq();
	}

	/**
	* Returns the ps dtls price tolerance intrvl of this imtd ps details.
	*
	* @return the ps dtls price tolerance intrvl of this imtd ps details
	*/
	@Override
	public int getPsDtlsPriceToleranceIntrvl() {
		return _imtdPsDetails.getPsDtlsPriceToleranceIntrvl();
	}

	/**
	* Returns the ps dtls price tolerance type of this imtd ps details.
	*
	* @return the ps dtls price tolerance type of this imtd ps details
	*/
	@Override
	public int getPsDtlsPriceToleranceType() {
		return _imtdPsDetails.getPsDtlsPriceToleranceType();
	}

	/**
	* Returns the ps model sid of this imtd ps details.
	*
	* @return the ps model sid of this imtd ps details
	*/
	@Override
	public int getPsModelSid() {
		return _imtdPsDetails.getPsModelSid();
	}

	/**
	* Returns the reset date of this imtd ps details.
	*
	* @return the reset date of this imtd ps details
	*/
	@Override
	public Date getResetDate() {
		return _imtdPsDetails.getResetDate();
	}

	/**
	* Returns the reset eligible of this imtd ps details.
	*
	* @return the reset eligible of this imtd ps details
	*/
	@Override
	public int getResetEligible() {
		return _imtdPsDetails.getResetEligible();
	}

	/**
	* Returns the reset frequency of this imtd ps details.
	*
	* @return the reset frequency of this imtd ps details
	*/
	@Override
	public int getResetFrequency() {
		return _imtdPsDetails.getResetFrequency();
	}

	/**
	* Returns the reset interval of this imtd ps details.
	*
	* @return the reset interval of this imtd ps details
	*/
	@Override
	public int getResetInterval() {
		return _imtdPsDetails.getResetInterval();
	}

	/**
	* Returns the reset price type of this imtd ps details.
	*
	* @return the reset price type of this imtd ps details
	*/
	@Override
	public int getResetPriceType() {
		return _imtdPsDetails.getResetPriceType();
	}

	/**
	* Returns the reset type of this imtd ps details.
	*
	* @return the reset type of this imtd ps details
	*/
	@Override
	public int getResetType() {
		return _imtdPsDetails.getResetType();
	}

	/**
	* Returns the session ID of this imtd ps details.
	*
	* @return the session ID of this imtd ps details
	*/
	@Override
	public java.lang.String getSessionId() {
		return _imtdPsDetails.getSessionId();
	}

	/**
	* Returns the status of this imtd ps details.
	*
	* @return the status of this imtd ps details
	*/
	@Override
	public int getStatus() {
		return _imtdPsDetails.getStatus();
	}

	/**
	* Returns the subsequent period price type of this imtd ps details.
	*
	* @return the subsequent period price type of this imtd ps details
	*/
	@Override
	public int getSubsequentPeriodPriceType() {
		return _imtdPsDetails.getSubsequentPeriodPriceType();
	}

	/**
	* Returns the users sid of this imtd ps details.
	*
	* @return the users sid of this imtd ps details
	*/
	@Override
	public int getUsersSid() {
		return _imtdPsDetails.getUsersSid();
	}

	@Override
	public int hashCode() {
		return _imtdPsDetails.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _imtdPsDetails.isCachedModel();
	}

	/**
	* Returns <code>true</code> if this imtd ps details is check record.
	*
	* @return <code>true</code> if this imtd ps details is check record; <code>false</code> otherwise
	*/
	@Override
	public boolean isCheckRecord() {
		return _imtdPsDetails.isCheckRecord();
	}

	@Override
	public boolean isEscapedModel() {
		return _imtdPsDetails.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _imtdPsDetails.isNew();
	}

	@Override
	public void persist() {
		_imtdPsDetails.persist();
	}

	/**
	* Sets the base price date of this imtd ps details.
	*
	* @param basePriceDate the base price date of this imtd ps details
	*/
	@Override
	public void setBasePriceDate(Date basePriceDate) {
		_imtdPsDetails.setBasePriceDate(basePriceDate);
	}

	/**
	* Sets the base price ddlb of this imtd ps details.
	*
	* @param basePriceDdlb the base price ddlb of this imtd ps details
	*/
	@Override
	public void setBasePriceDdlb(int basePriceDdlb) {
		_imtdPsDetails.setBasePriceDdlb(basePriceDdlb);
	}

	/**
	* Sets the base price entry of this imtd ps details.
	*
	* @param basePriceEntry the base price entry of this imtd ps details
	*/
	@Override
	public void setBasePriceEntry(double basePriceEntry) {
		_imtdPsDetails.setBasePriceEntry(basePriceEntry);
	}

	/**
	* Sets the base price type of this imtd ps details.
	*
	* @param basePriceType the base price type of this imtd ps details
	*/
	@Override
	public void setBasePriceType(int basePriceType) {
		_imtdPsDetails.setBasePriceType(basePriceType);
	}

	/**
	* Sets the brand master sid of this imtd ps details.
	*
	* @param brandMasterSid the brand master sid of this imtd ps details
	*/
	@Override
	public void setBrandMasterSid(int brandMasterSid) {
		_imtdPsDetails.setBrandMasterSid(brandMasterSid);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_imtdPsDetails.setCachedModel(cachedModel);
	}

	/**
	* Sets the cfp model sid of this imtd ps details.
	*
	* @param cfpModelSid the cfp model sid of this imtd ps details
	*/
	@Override
	public void setCfpModelSid(int cfpModelSid) {
		_imtdPsDetails.setCfpModelSid(cfpModelSid);
	}

	/**
	* Sets whether this imtd ps details is check record.
	*
	* @param checkRecord the check record of this imtd ps details
	*/
	@Override
	public void setCheckRecord(boolean checkRecord) {
		_imtdPsDetails.setCheckRecord(checkRecord);
	}

	/**
	* Sets the contract master sid of this imtd ps details.
	*
	* @param contractMasterSid the contract master sid of this imtd ps details
	*/
	@Override
	public void setContractMasterSid(int contractMasterSid) {
		_imtdPsDetails.setContractMasterSid(contractMasterSid);
	}

	/**
	* Sets the created by of this imtd ps details.
	*
	* @param createdBy the created by of this imtd ps details
	*/
	@Override
	public void setCreatedBy(int createdBy) {
		_imtdPsDetails.setCreatedBy(createdBy);
	}

	/**
	* Sets the created date of this imtd ps details.
	*
	* @param createdDate the created date of this imtd ps details
	*/
	@Override
	public void setCreatedDate(Date createdDate) {
		_imtdPsDetails.setCreatedDate(createdDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_imtdPsDetails.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_imtdPsDetails.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_imtdPsDetails.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the ifp model sid of this imtd ps details.
	*
	* @param ifpModelSid the ifp model sid of this imtd ps details
	*/
	@Override
	public void setIfpModelSid(int ifpModelSid) {
		_imtdPsDetails.setIfpModelSid(ifpModelSid);
	}

	/**
	* Sets the ifp no of this imtd ps details.
	*
	* @param ifpNo the ifp no of this imtd ps details
	*/
	@Override
	public void setIfpNo(java.lang.String ifpNo) {
		_imtdPsDetails.setIfpNo(ifpNo);
	}

	/**
	* Sets the imtd created date of this imtd ps details.
	*
	* @param imtdCreatedDate the imtd created date of this imtd ps details
	*/
	@Override
	public void setImtdCreatedDate(Date imtdCreatedDate) {
		_imtdPsDetails.setImtdCreatedDate(imtdCreatedDate);
	}

	/**
	* Sets the imtd ps details sid of this imtd ps details.
	*
	* @param imtdPsDetailsSid the imtd ps details sid of this imtd ps details
	*/
	@Override
	public void setImtdPsDetailsSid(int imtdPsDetailsSid) {
		_imtdPsDetails.setImtdPsDetailsSid(imtdPsDetailsSid);
	}

	/**
	* Sets the item ID of this imtd ps details.
	*
	* @param itemId the item ID of this imtd ps details
	*/
	@Override
	public void setItemId(java.lang.String itemId) {
		_imtdPsDetails.setItemId(itemId);
	}

	/**
	* Sets the item master sid of this imtd ps details.
	*
	* @param itemMasterSid the item master sid of this imtd ps details
	*/
	@Override
	public void setItemMasterSid(int itemMasterSid) {
		_imtdPsDetails.setItemMasterSid(itemMasterSid);
	}

	/**
	* Sets the item name of this imtd ps details.
	*
	* @param itemName the item name of this imtd ps details
	*/
	@Override
	public void setItemName(java.lang.String itemName) {
		_imtdPsDetails.setItemName(itemName);
	}

	/**
	* Sets the item no of this imtd ps details.
	*
	* @param itemNo the item no of this imtd ps details
	*/
	@Override
	public void setItemNo(java.lang.String itemNo) {
		_imtdPsDetails.setItemNo(itemNo);
	}

	/**
	* Sets the max incremental change of this imtd ps details.
	*
	* @param maxIncrementalChange the max incremental change of this imtd ps details
	*/
	@Override
	public void setMaxIncrementalChange(double maxIncrementalChange) {
		_imtdPsDetails.setMaxIncrementalChange(maxIncrementalChange);
	}

	/**
	* Sets the modified by of this imtd ps details.
	*
	* @param modifiedBy the modified by of this imtd ps details
	*/
	@Override
	public void setModifiedBy(int modifiedBy) {
		_imtdPsDetails.setModifiedBy(modifiedBy);
	}

	/**
	* Sets the modified date of this imtd ps details.
	*
	* @param modifiedDate the modified date of this imtd ps details
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_imtdPsDetails.setModifiedDate(modifiedDate);
	}

	/**
	* Sets the nep of this imtd ps details.
	*
	* @param nep the nep of this imtd ps details
	*/
	@Override
	public void setNep(double nep) {
		_imtdPsDetails.setNep(nep);
	}

	/**
	* Sets the nep formula of this imtd ps details.
	*
	* @param nepFormula the nep formula of this imtd ps details
	*/
	@Override
	public void setNepFormula(int nepFormula) {
		_imtdPsDetails.setNepFormula(nepFormula);
	}

	/**
	* Sets the net base price of this imtd ps details.
	*
	* @param netBasePrice the net base price of this imtd ps details
	*/
	@Override
	public void setNetBasePrice(int netBasePrice) {
		_imtdPsDetails.setNetBasePrice(netBasePrice);
	}

	/**
	* Sets the net base price formula ID of this imtd ps details.
	*
	* @param netBasePriceFormulaId the net base price formula ID of this imtd ps details
	*/
	@Override
	public void setNetBasePriceFormulaId(int netBasePriceFormulaId) {
		_imtdPsDetails.setNetBasePriceFormulaId(netBasePriceFormulaId);
	}

	/**
	* Sets the net base price formula name of this imtd ps details.
	*
	* @param netBasePriceFormulaName the net base price formula name of this imtd ps details
	*/
	@Override
	public void setNetBasePriceFormulaName(
		java.lang.String netBasePriceFormulaName) {
		_imtdPsDetails.setNetBasePriceFormulaName(netBasePriceFormulaName);
	}

	/**
	* Sets the net base price formula no of this imtd ps details.
	*
	* @param netBasePriceFormulaNo the net base price formula no of this imtd ps details
	*/
	@Override
	public void setNetBasePriceFormulaNo(java.lang.String netBasePriceFormulaNo) {
		_imtdPsDetails.setNetBasePriceFormulaNo(netBasePriceFormulaNo);
	}

	/**
	* Sets the net price type of this imtd ps details.
	*
	* @param netPriceType the net price type of this imtd ps details
	*/
	@Override
	public void setNetPriceType(int netPriceType) {
		_imtdPsDetails.setNetPriceType(netPriceType);
	}

	/**
	* Sets the net price type formula of this imtd ps details.
	*
	* @param netPriceTypeFormula the net price type formula of this imtd ps details
	*/
	@Override
	public void setNetPriceTypeFormula(java.lang.String netPriceTypeFormula) {
		_imtdPsDetails.setNetPriceTypeFormula(netPriceTypeFormula);
	}

	/**
	* Sets the net reset price formula ID of this imtd ps details.
	*
	* @param netResetPriceFormulaId the net reset price formula ID of this imtd ps details
	*/
	@Override
	public void setNetResetPriceFormulaId(int netResetPriceFormulaId) {
		_imtdPsDetails.setNetResetPriceFormulaId(netResetPriceFormulaId);
	}

	/**
	* Sets the net reset price formula name of this imtd ps details.
	*
	* @param netResetPriceFormulaName the net reset price formula name of this imtd ps details
	*/
	@Override
	public void setNetResetPriceFormulaName(
		java.lang.String netResetPriceFormulaName) {
		_imtdPsDetails.setNetResetPriceFormulaName(netResetPriceFormulaName);
	}

	/**
	* Sets the net reset price formula no of this imtd ps details.
	*
	* @param netResetPriceFormulaNo the net reset price formula no of this imtd ps details
	*/
	@Override
	public void setNetResetPriceFormulaNo(
		java.lang.String netResetPriceFormulaNo) {
		_imtdPsDetails.setNetResetPriceFormulaNo(netResetPriceFormulaNo);
	}

	/**
	* Sets the net reset price type of this imtd ps details.
	*
	* @param netResetPriceType the net reset price type of this imtd ps details
	*/
	@Override
	public void setNetResetPriceType(int netResetPriceType) {
		_imtdPsDetails.setNetResetPriceType(netResetPriceType);
	}

	/**
	* Sets the net subsequent period price of this imtd ps details.
	*
	* @param netSubsequentPeriodPrice the net subsequent period price of this imtd ps details
	*/
	@Override
	public void setNetSubsequentPeriodPrice(int netSubsequentPeriodPrice) {
		_imtdPsDetails.setNetSubsequentPeriodPrice(netSubsequentPeriodPrice);
	}

	/**
	* Sets the net subsequent price formula ID of this imtd ps details.
	*
	* @param netSubsequentPriceFormulaId the net subsequent price formula ID of this imtd ps details
	*/
	@Override
	public void setNetSubsequentPriceFormulaId(int netSubsequentPriceFormulaId) {
		_imtdPsDetails.setNetSubsequentPriceFormulaId(netSubsequentPriceFormulaId);
	}

	/**
	* Sets the net subsequent price formula name of this imtd ps details.
	*
	* @param netSubsequentPriceFormulaName the net subsequent price formula name of this imtd ps details
	*/
	@Override
	public void setNetSubsequentPriceFormulaName(
		java.lang.String netSubsequentPriceFormulaName) {
		_imtdPsDetails.setNetSubsequentPriceFormulaName(netSubsequentPriceFormulaName);
	}

	/**
	* Sets the net subsequent price formula no of this imtd ps details.
	*
	* @param netSubsequentPriceFormulaNo the net subsequent price formula no of this imtd ps details
	*/
	@Override
	public void setNetSubsequentPriceFormulaNo(
		java.lang.String netSubsequentPriceFormulaNo) {
		_imtdPsDetails.setNetSubsequentPriceFormulaNo(netSubsequentPriceFormulaNo);
	}

	@Override
	public void setNew(boolean n) {
		_imtdPsDetails.setNew(n);
	}

	/**
	* Sets the operation of this imtd ps details.
	*
	* @param operation the operation of this imtd ps details
	*/
	@Override
	public void setOperation(java.lang.String operation) {
		_imtdPsDetails.setOperation(operation);
	}

	/**
	* Sets the price protection price type of this imtd ps details.
	*
	* @param priceProtectionPriceType the price protection price type of this imtd ps details
	*/
	@Override
	public void setPriceProtectionPriceType(int priceProtectionPriceType) {
		_imtdPsDetails.setPriceProtectionPriceType(priceProtectionPriceType);
	}

	/**
	* Sets the price protection status of this imtd ps details.
	*
	* @param priceProtectionStatus the price protection status of this imtd ps details
	*/
	@Override
	public void setPriceProtectionStatus(int priceProtectionStatus) {
		_imtdPsDetails.setPriceProtectionStatus(priceProtectionStatus);
	}

	/**
	* Sets the primary key of this imtd ps details.
	*
	* @param primaryKey the primary key of this imtd ps details
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_imtdPsDetails.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_imtdPsDetails.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the ps details attached date of this imtd ps details.
	*
	* @param psDetailsAttachedDate the ps details attached date of this imtd ps details
	*/
	@Override
	public void setPsDetailsAttachedDate(Date psDetailsAttachedDate) {
		_imtdPsDetails.setPsDetailsAttachedDate(psDetailsAttachedDate);
	}

	/**
	* Sets the ps details attached status of this imtd ps details.
	*
	* @param psDetailsAttachedStatus the ps details attached status of this imtd ps details
	*/
	@Override
	public void setPsDetailsAttachedStatus(int psDetailsAttachedStatus) {
		_imtdPsDetails.setPsDetailsAttachedStatus(psDetailsAttachedStatus);
	}

	/**
	* Sets the ps details base price of this imtd ps details.
	*
	* @param psDetailsBasePrice the ps details base price of this imtd ps details
	*/
	@Override
	public void setPsDetailsBasePrice(double psDetailsBasePrice) {
		_imtdPsDetails.setPsDetailsBasePrice(psDetailsBasePrice);
	}

	/**
	* Sets the ps details contract price of this imtd ps details.
	*
	* @param psDetailsContractPrice the ps details contract price of this imtd ps details
	*/
	@Override
	public void setPsDetailsContractPrice(double psDetailsContractPrice) {
		_imtdPsDetails.setPsDetailsContractPrice(psDetailsContractPrice);
	}

	/**
	* Sets the ps details created by of this imtd ps details.
	*
	* @param psDetailsCreatedBy the ps details created by of this imtd ps details
	*/
	@Override
	public void setPsDetailsCreatedBy(int psDetailsCreatedBy) {
		_imtdPsDetails.setPsDetailsCreatedBy(psDetailsCreatedBy);
	}

	/**
	* Sets the ps details created date of this imtd ps details.
	*
	* @param psDetailsCreatedDate the ps details created date of this imtd ps details
	*/
	@Override
	public void setPsDetailsCreatedDate(Date psDetailsCreatedDate) {
		_imtdPsDetails.setPsDetailsCreatedDate(psDetailsCreatedDate);
	}

	/**
	* Sets the ps details modified by of this imtd ps details.
	*
	* @param psDetailsModifiedBy the ps details modified by of this imtd ps details
	*/
	@Override
	public void setPsDetailsModifiedBy(int psDetailsModifiedBy) {
		_imtdPsDetails.setPsDetailsModifiedBy(psDetailsModifiedBy);
	}

	/**
	* Sets the ps details modified date of this imtd ps details.
	*
	* @param psDetailsModifiedDate the ps details modified date of this imtd ps details
	*/
	@Override
	public void setPsDetailsModifiedDate(Date psDetailsModifiedDate) {
		_imtdPsDetails.setPsDetailsModifiedDate(psDetailsModifiedDate);
	}

	/**
	* Sets the ps details price of this imtd ps details.
	*
	* @param psDetailsPrice the ps details price of this imtd ps details
	*/
	@Override
	public void setPsDetailsPrice(double psDetailsPrice) {
		_imtdPsDetails.setPsDetailsPrice(psDetailsPrice);
	}

	/**
	* Sets the ps details price plan ID of this imtd ps details.
	*
	* @param psDetailsPricePlanId the ps details price plan ID of this imtd ps details
	*/
	@Override
	public void setPsDetailsPricePlanId(java.lang.String psDetailsPricePlanId) {
		_imtdPsDetails.setPsDetailsPricePlanId(psDetailsPricePlanId);
	}

	/**
	* Sets the ps details price revision of this imtd ps details.
	*
	* @param psDetailsPriceRevision the ps details price revision of this imtd ps details
	*/
	@Override
	public void setPsDetailsPriceRevision(double psDetailsPriceRevision) {
		_imtdPsDetails.setPsDetailsPriceRevision(psDetailsPriceRevision);
	}

	/**
	* Sets the ps details price tolerance of this imtd ps details.
	*
	* @param psDetailsPriceTolerance the ps details price tolerance of this imtd ps details
	*/
	@Override
	public void setPsDetailsPriceTolerance(double psDetailsPriceTolerance) {
		_imtdPsDetails.setPsDetailsPriceTolerance(psDetailsPriceTolerance);
	}

	/**
	* Sets the ps details pricetype of this imtd ps details.
	*
	* @param psDetailsPricetype the ps details pricetype of this imtd ps details
	*/
	@Override
	public void setPsDetailsPricetype(int psDetailsPricetype) {
		_imtdPsDetails.setPsDetailsPricetype(psDetailsPricetype);
	}

	/**
	* Sets the ps details pric prtcn eddate of this imtd ps details.
	*
	* @param psDetailsPricPrtcnEddate the ps details pric prtcn eddate of this imtd ps details
	*/
	@Override
	public void setPsDetailsPricPrtcnEddate(Date psDetailsPricPrtcnEddate) {
		_imtdPsDetails.setPsDetailsPricPrtcnEddate(psDetailsPricPrtcnEddate);
	}

	/**
	* Sets the ps details pric prtcn stdate of this imtd ps details.
	*
	* @param psDetailsPricPrtcnStdate the ps details pric prtcn stdate of this imtd ps details
	*/
	@Override
	public void setPsDetailsPricPrtcnStdate(Date psDetailsPricPrtcnStdate) {
		_imtdPsDetails.setPsDetailsPricPrtcnStdate(psDetailsPricPrtcnStdate);
	}

	/**
	* Sets the ps details revision date of this imtd ps details.
	*
	* @param psDetailsRevisionDate the ps details revision date of this imtd ps details
	*/
	@Override
	public void setPsDetailsRevisionDate(Date psDetailsRevisionDate) {
		_imtdPsDetails.setPsDetailsRevisionDate(psDetailsRevisionDate);
	}

	/**
	* Sets the ps details sid of this imtd ps details.
	*
	* @param psDetailsSid the ps details sid of this imtd ps details
	*/
	@Override
	public void setPsDetailsSid(int psDetailsSid) {
		_imtdPsDetails.setPsDetailsSid(psDetailsSid);
	}

	/**
	* Sets the ps details suggested price of this imtd ps details.
	*
	* @param psDetailsSuggestedPrice the ps details suggested price of this imtd ps details
	*/
	@Override
	public void setPsDetailsSuggestedPrice(double psDetailsSuggestedPrice) {
		_imtdPsDetails.setPsDetailsSuggestedPrice(psDetailsSuggestedPrice);
	}

	/**
	* Sets the ps dtls cont price enddate of this imtd ps details.
	*
	* @param psDtlsContPriceEnddate the ps dtls cont price enddate of this imtd ps details
	*/
	@Override
	public void setPsDtlsContPriceEnddate(Date psDtlsContPriceEnddate) {
		_imtdPsDetails.setPsDtlsContPriceEnddate(psDtlsContPriceEnddate);
	}

	/**
	* Sets the ps dtls cont price startdate of this imtd ps details.
	*
	* @param psDtlsContPriceStartdate the ps dtls cont price startdate of this imtd ps details
	*/
	@Override
	public void setPsDtlsContPriceStartdate(Date psDtlsContPriceStartdate) {
		_imtdPsDetails.setPsDtlsContPriceStartdate(psDtlsContPriceStartdate);
	}

	/**
	* Sets the ps dtls price tolerance freq of this imtd ps details.
	*
	* @param psDtlsPriceToleranceFreq the ps dtls price tolerance freq of this imtd ps details
	*/
	@Override
	public void setPsDtlsPriceToleranceFreq(int psDtlsPriceToleranceFreq) {
		_imtdPsDetails.setPsDtlsPriceToleranceFreq(psDtlsPriceToleranceFreq);
	}

	/**
	* Sets the ps dtls price tolerance intrvl of this imtd ps details.
	*
	* @param psDtlsPriceToleranceIntrvl the ps dtls price tolerance intrvl of this imtd ps details
	*/
	@Override
	public void setPsDtlsPriceToleranceIntrvl(int psDtlsPriceToleranceIntrvl) {
		_imtdPsDetails.setPsDtlsPriceToleranceIntrvl(psDtlsPriceToleranceIntrvl);
	}

	/**
	* Sets the ps dtls price tolerance type of this imtd ps details.
	*
	* @param psDtlsPriceToleranceType the ps dtls price tolerance type of this imtd ps details
	*/
	@Override
	public void setPsDtlsPriceToleranceType(int psDtlsPriceToleranceType) {
		_imtdPsDetails.setPsDtlsPriceToleranceType(psDtlsPriceToleranceType);
	}

	/**
	* Sets the ps model sid of this imtd ps details.
	*
	* @param psModelSid the ps model sid of this imtd ps details
	*/
	@Override
	public void setPsModelSid(int psModelSid) {
		_imtdPsDetails.setPsModelSid(psModelSid);
	}

	/**
	* Sets the reset date of this imtd ps details.
	*
	* @param resetDate the reset date of this imtd ps details
	*/
	@Override
	public void setResetDate(Date resetDate) {
		_imtdPsDetails.setResetDate(resetDate);
	}

	/**
	* Sets the reset eligible of this imtd ps details.
	*
	* @param resetEligible the reset eligible of this imtd ps details
	*/
	@Override
	public void setResetEligible(int resetEligible) {
		_imtdPsDetails.setResetEligible(resetEligible);
	}

	/**
	* Sets the reset frequency of this imtd ps details.
	*
	* @param resetFrequency the reset frequency of this imtd ps details
	*/
	@Override
	public void setResetFrequency(int resetFrequency) {
		_imtdPsDetails.setResetFrequency(resetFrequency);
	}

	/**
	* Sets the reset interval of this imtd ps details.
	*
	* @param resetInterval the reset interval of this imtd ps details
	*/
	@Override
	public void setResetInterval(int resetInterval) {
		_imtdPsDetails.setResetInterval(resetInterval);
	}

	/**
	* Sets the reset price type of this imtd ps details.
	*
	* @param resetPriceType the reset price type of this imtd ps details
	*/
	@Override
	public void setResetPriceType(int resetPriceType) {
		_imtdPsDetails.setResetPriceType(resetPriceType);
	}

	/**
	* Sets the reset type of this imtd ps details.
	*
	* @param resetType the reset type of this imtd ps details
	*/
	@Override
	public void setResetType(int resetType) {
		_imtdPsDetails.setResetType(resetType);
	}

	/**
	* Sets the session ID of this imtd ps details.
	*
	* @param sessionId the session ID of this imtd ps details
	*/
	@Override
	public void setSessionId(java.lang.String sessionId) {
		_imtdPsDetails.setSessionId(sessionId);
	}

	/**
	* Sets the status of this imtd ps details.
	*
	* @param status the status of this imtd ps details
	*/
	@Override
	public void setStatus(int status) {
		_imtdPsDetails.setStatus(status);
	}

	/**
	* Sets the subsequent period price type of this imtd ps details.
	*
	* @param subsequentPeriodPriceType the subsequent period price type of this imtd ps details
	*/
	@Override
	public void setSubsequentPeriodPriceType(int subsequentPeriodPriceType) {
		_imtdPsDetails.setSubsequentPeriodPriceType(subsequentPeriodPriceType);
	}

	/**
	* Sets the users sid of this imtd ps details.
	*
	* @param usersSid the users sid of this imtd ps details
	*/
	@Override
	public void setUsersSid(int usersSid) {
		_imtdPsDetails.setUsersSid(usersSid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<ImtdPsDetails> toCacheModel() {
		return _imtdPsDetails.toCacheModel();
	}

	@Override
	public ImtdPsDetails toEscapedModel() {
		return new ImtdPsDetailsWrapper(_imtdPsDetails.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _imtdPsDetails.toString();
	}

	@Override
	public ImtdPsDetails toUnescapedModel() {
		return new ImtdPsDetailsWrapper(_imtdPsDetails.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _imtdPsDetails.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ImtdPsDetailsWrapper)) {
			return false;
		}

		ImtdPsDetailsWrapper imtdPsDetailsWrapper = (ImtdPsDetailsWrapper)obj;

		if (Objects.equals(_imtdPsDetails, imtdPsDetailsWrapper._imtdPsDetails)) {
			return true;
		}

		return false;
	}

	@Override
	public ImtdPsDetails getWrappedModel() {
		return _imtdPsDetails;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _imtdPsDetails.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _imtdPsDetails.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_imtdPsDetails.resetOriginalValues();
	}

	private final ImtdPsDetails _imtdPsDetails;
}