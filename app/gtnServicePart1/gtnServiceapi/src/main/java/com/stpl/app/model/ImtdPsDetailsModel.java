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

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;

/**
 * The base model interface for the ImtdPsDetails service. Represents a row in the &quot;IMTD_PS_DETAILS&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.stpl.app.model.impl.ImtdPsDetailsModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.stpl.app.model.impl.ImtdPsDetailsImpl}.
 * </p>
 *
 * @author
 * @see ImtdPsDetails
 * @see com.stpl.app.model.impl.ImtdPsDetailsImpl
 * @see com.stpl.app.model.impl.ImtdPsDetailsModelImpl
 * @generated
 */
@ProviderType
public interface ImtdPsDetailsModel extends BaseModel<ImtdPsDetails> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a imtd ps details model instance should use the {@link ImtdPsDetails} interface instead.
	 */

	/**
	 * Returns the primary key of this imtd ps details.
	 *
	 * @return the primary key of this imtd ps details
	 */
	public int getPrimaryKey();

	/**
	 * Sets the primary key of this imtd ps details.
	 *
	 * @param primaryKey the primary key of this imtd ps details
	 */
	public void setPrimaryKey(int primaryKey);

	/**
	 * Returns the ps details modified date of this imtd ps details.
	 *
	 * @return the ps details modified date of this imtd ps details
	 */
	public Date getPsDetailsModifiedDate();

	/**
	 * Sets the ps details modified date of this imtd ps details.
	 *
	 * @param psDetailsModifiedDate the ps details modified date of this imtd ps details
	 */
	public void setPsDetailsModifiedDate(Date psDetailsModifiedDate);

	/**
	 * Returns the ps details suggested price of this imtd ps details.
	 *
	 * @return the ps details suggested price of this imtd ps details
	 */
	public double getPsDetailsSuggestedPrice();

	/**
	 * Sets the ps details suggested price of this imtd ps details.
	 *
	 * @param psDetailsSuggestedPrice the ps details suggested price of this imtd ps details
	 */
	public void setPsDetailsSuggestedPrice(double psDetailsSuggestedPrice);

	/**
	 * Returns the ps details contract price of this imtd ps details.
	 *
	 * @return the ps details contract price of this imtd ps details
	 */
	public double getPsDetailsContractPrice();

	/**
	 * Sets the ps details contract price of this imtd ps details.
	 *
	 * @param psDetailsContractPrice the ps details contract price of this imtd ps details
	 */
	public void setPsDetailsContractPrice(double psDetailsContractPrice);

	/**
	 * Returns the reset date of this imtd ps details.
	 *
	 * @return the reset date of this imtd ps details
	 */
	public Date getResetDate();

	/**
	 * Sets the reset date of this imtd ps details.
	 *
	 * @param resetDate the reset date of this imtd ps details
	 */
	public void setResetDate(Date resetDate);

	/**
	 * Returns the ps details attached status of this imtd ps details.
	 *
	 * @return the ps details attached status of this imtd ps details
	 */
	public int getPsDetailsAttachedStatus();

	/**
	 * Sets the ps details attached status of this imtd ps details.
	 *
	 * @param psDetailsAttachedStatus the ps details attached status of this imtd ps details
	 */
	public void setPsDetailsAttachedStatus(int psDetailsAttachedStatus);

	/**
	 * Returns the imtd ps details sid of this imtd ps details.
	 *
	 * @return the imtd ps details sid of this imtd ps details
	 */
	public int getImtdPsDetailsSid();

	/**
	 * Sets the imtd ps details sid of this imtd ps details.
	 *
	 * @param imtdPsDetailsSid the imtd ps details sid of this imtd ps details
	 */
	public void setImtdPsDetailsSid(int imtdPsDetailsSid);

	/**
	 * Returns the modified date of this imtd ps details.
	 *
	 * @return the modified date of this imtd ps details
	 */
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this imtd ps details.
	 *
	 * @param modifiedDate the modified date of this imtd ps details
	 */
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the ps details created by of this imtd ps details.
	 *
	 * @return the ps details created by of this imtd ps details
	 */
	public int getPsDetailsCreatedBy();

	/**
	 * Sets the ps details created by of this imtd ps details.
	 *
	 * @param psDetailsCreatedBy the ps details created by of this imtd ps details
	 */
	public void setPsDetailsCreatedBy(int psDetailsCreatedBy);

	/**
	 * Returns the contract master sid of this imtd ps details.
	 *
	 * @return the contract master sid of this imtd ps details
	 */
	public int getContractMasterSid();

	/**
	 * Sets the contract master sid of this imtd ps details.
	 *
	 * @param contractMasterSid the contract master sid of this imtd ps details
	 */
	public void setContractMasterSid(int contractMasterSid);

	/**
	 * Returns the ps dtls cont price enddate of this imtd ps details.
	 *
	 * @return the ps dtls cont price enddate of this imtd ps details
	 */
	public Date getPsDtlsContPriceEnddate();

	/**
	 * Sets the ps dtls cont price enddate of this imtd ps details.
	 *
	 * @param psDtlsContPriceEnddate the ps dtls cont price enddate of this imtd ps details
	 */
	public void setPsDtlsContPriceEnddate(Date psDtlsContPriceEnddate);

	/**
	 * Returns the ps details pric prtcn stdate of this imtd ps details.
	 *
	 * @return the ps details pric prtcn stdate of this imtd ps details
	 */
	public Date getPsDetailsPricPrtcnStdate();

	/**
	 * Sets the ps details pric prtcn stdate of this imtd ps details.
	 *
	 * @param psDetailsPricPrtcnStdate the ps details pric prtcn stdate of this imtd ps details
	 */
	public void setPsDetailsPricPrtcnStdate(Date psDetailsPricPrtcnStdate);

	/**
	 * Returns the imtd created date of this imtd ps details.
	 *
	 * @return the imtd created date of this imtd ps details
	 */
	public Date getImtdCreatedDate();

	/**
	 * Sets the imtd created date of this imtd ps details.
	 *
	 * @param imtdCreatedDate the imtd created date of this imtd ps details
	 */
	public void setImtdCreatedDate(Date imtdCreatedDate);

	/**
	 * Returns the net price type formula of this imtd ps details.
	 *
	 * @return the net price type formula of this imtd ps details
	 */
	@AutoEscape
	public String getNetPriceTypeFormula();

	/**
	 * Sets the net price type formula of this imtd ps details.
	 *
	 * @param netPriceTypeFormula the net price type formula of this imtd ps details
	 */
	public void setNetPriceTypeFormula(String netPriceTypeFormula);

	/**
	 * Returns the modified by of this imtd ps details.
	 *
	 * @return the modified by of this imtd ps details
	 */
	public int getModifiedBy();

	/**
	 * Sets the modified by of this imtd ps details.
	 *
	 * @param modifiedBy the modified by of this imtd ps details
	 */
	public void setModifiedBy(int modifiedBy);

	/**
	 * Returns the max incremental change of this imtd ps details.
	 *
	 * @return the max incremental change of this imtd ps details
	 */
	public double getMaxIncrementalChange();

	/**
	 * Sets the max incremental change of this imtd ps details.
	 *
	 * @param maxIncrementalChange the max incremental change of this imtd ps details
	 */
	public void setMaxIncrementalChange(double maxIncrementalChange);

	/**
	 * Returns the ps details price plan ID of this imtd ps details.
	 *
	 * @return the ps details price plan ID of this imtd ps details
	 */
	@AutoEscape
	public String getPsDetailsPricePlanId();

	/**
	 * Sets the ps details price plan ID of this imtd ps details.
	 *
	 * @param psDetailsPricePlanId the ps details price plan ID of this imtd ps details
	 */
	public void setPsDetailsPricePlanId(String psDetailsPricePlanId);

	/**
	 * Returns the check record of this imtd ps details.
	 *
	 * @return the check record of this imtd ps details
	 */
	public boolean getCheckRecord();

	/**
	 * Returns <code>true</code> if this imtd ps details is check record.
	 *
	 * @return <code>true</code> if this imtd ps details is check record; <code>false</code> otherwise
	 */
	public boolean isCheckRecord();

	/**
	 * Sets whether this imtd ps details is check record.
	 *
	 * @param checkRecord the check record of this imtd ps details
	 */
	public void setCheckRecord(boolean checkRecord);

	/**
	 * Returns the ps dtls price tolerance freq of this imtd ps details.
	 *
	 * @return the ps dtls price tolerance freq of this imtd ps details
	 */
	public int getPsDtlsPriceToleranceFreq();

	/**
	 * Sets the ps dtls price tolerance freq of this imtd ps details.
	 *
	 * @param psDtlsPriceToleranceFreq the ps dtls price tolerance freq of this imtd ps details
	 */
	public void setPsDtlsPriceToleranceFreq(int psDtlsPriceToleranceFreq);

	/**
	 * Returns the item name of this imtd ps details.
	 *
	 * @return the item name of this imtd ps details
	 */
	@AutoEscape
	public String getItemName();

	/**
	 * Sets the item name of this imtd ps details.
	 *
	 * @param itemName the item name of this imtd ps details
	 */
	public void setItemName(String itemName);

	/**
	 * Returns the session ID of this imtd ps details.
	 *
	 * @return the session ID of this imtd ps details
	 */
	@AutoEscape
	public String getSessionId();

	/**
	 * Sets the session ID of this imtd ps details.
	 *
	 * @param sessionId the session ID of this imtd ps details
	 */
	public void setSessionId(String sessionId);

	/**
	 * Returns the reset frequency of this imtd ps details.
	 *
	 * @return the reset frequency of this imtd ps details
	 */
	public int getResetFrequency();

	/**
	 * Sets the reset frequency of this imtd ps details.
	 *
	 * @param resetFrequency the reset frequency of this imtd ps details
	 */
	public void setResetFrequency(int resetFrequency);

	/**
	 * Returns the ps dtls price tolerance type of this imtd ps details.
	 *
	 * @return the ps dtls price tolerance type of this imtd ps details
	 */
	public int getPsDtlsPriceToleranceType();

	/**
	 * Sets the ps dtls price tolerance type of this imtd ps details.
	 *
	 * @param psDtlsPriceToleranceType the ps dtls price tolerance type of this imtd ps details
	 */
	public void setPsDtlsPriceToleranceType(int psDtlsPriceToleranceType);

	/**
	 * Returns the ps details pricetype of this imtd ps details.
	 *
	 * @return the ps details pricetype of this imtd ps details
	 */
	public int getPsDetailsPricetype();

	/**
	 * Sets the ps details pricetype of this imtd ps details.
	 *
	 * @param psDetailsPricetype the ps details pricetype of this imtd ps details
	 */
	public void setPsDetailsPricetype(int psDetailsPricetype);

	/**
	 * Returns the ps details price revision of this imtd ps details.
	 *
	 * @return the ps details price revision of this imtd ps details
	 */
	public double getPsDetailsPriceRevision();

	/**
	 * Sets the ps details price revision of this imtd ps details.
	 *
	 * @param psDetailsPriceRevision the ps details price revision of this imtd ps details
	 */
	public void setPsDetailsPriceRevision(double psDetailsPriceRevision);

	/**
	 * Returns the reset interval of this imtd ps details.
	 *
	 * @return the reset interval of this imtd ps details
	 */
	public int getResetInterval();

	/**
	 * Sets the reset interval of this imtd ps details.
	 *
	 * @param resetInterval the reset interval of this imtd ps details
	 */
	public void setResetInterval(int resetInterval);

	/**
	 * Returns the ifp no of this imtd ps details.
	 *
	 * @return the ifp no of this imtd ps details
	 */
	@AutoEscape
	public String getIfpNo();

	/**
	 * Sets the ifp no of this imtd ps details.
	 *
	 * @param ifpNo the ifp no of this imtd ps details
	 */
	public void setIfpNo(String ifpNo);

	/**
	 * Returns the ps details attached date of this imtd ps details.
	 *
	 * @return the ps details attached date of this imtd ps details
	 */
	public Date getPsDetailsAttachedDate();

	/**
	 * Sets the ps details attached date of this imtd ps details.
	 *
	 * @param psDetailsAttachedDate the ps details attached date of this imtd ps details
	 */
	public void setPsDetailsAttachedDate(Date psDetailsAttachedDate);

	/**
	 * Returns the nep formula of this imtd ps details.
	 *
	 * @return the nep formula of this imtd ps details
	 */
	public int getNepFormula();

	/**
	 * Sets the nep formula of this imtd ps details.
	 *
	 * @param nepFormula the nep formula of this imtd ps details
	 */
	public void setNepFormula(int nepFormula);

	/**
	 * Returns the ps details modified by of this imtd ps details.
	 *
	 * @return the ps details modified by of this imtd ps details
	 */
	public int getPsDetailsModifiedBy();

	/**
	 * Sets the ps details modified by of this imtd ps details.
	 *
	 * @param psDetailsModifiedBy the ps details modified by of this imtd ps details
	 */
	public void setPsDetailsModifiedBy(int psDetailsModifiedBy);

	/**
	 * Returns the ps dtls price tolerance intrvl of this imtd ps details.
	 *
	 * @return the ps dtls price tolerance intrvl of this imtd ps details
	 */
	public int getPsDtlsPriceToleranceIntrvl();

	/**
	 * Sets the ps dtls price tolerance intrvl of this imtd ps details.
	 *
	 * @param psDtlsPriceToleranceIntrvl the ps dtls price tolerance intrvl of this imtd ps details
	 */
	public void setPsDtlsPriceToleranceIntrvl(int psDtlsPriceToleranceIntrvl);

	/**
	 * Returns the item master sid of this imtd ps details.
	 *
	 * @return the item master sid of this imtd ps details
	 */
	public int getItemMasterSid();

	/**
	 * Sets the item master sid of this imtd ps details.
	 *
	 * @param itemMasterSid the item master sid of this imtd ps details
	 */
	public void setItemMasterSid(int itemMasterSid);

	/**
	 * Returns the reset type of this imtd ps details.
	 *
	 * @return the reset type of this imtd ps details
	 */
	public int getResetType();

	/**
	 * Sets the reset type of this imtd ps details.
	 *
	 * @param resetType the reset type of this imtd ps details
	 */
	public void setResetType(int resetType);

	/**
	 * Returns the item ID of this imtd ps details.
	 *
	 * @return the item ID of this imtd ps details
	 */
	@AutoEscape
	public String getItemId();

	/**
	 * Sets the item ID of this imtd ps details.
	 *
	 * @param itemId the item ID of this imtd ps details
	 */
	public void setItemId(String itemId);

	/**
	 * Returns the status of this imtd ps details.
	 *
	 * @return the status of this imtd ps details
	 */
	public int getStatus();

	/**
	 * Sets the status of this imtd ps details.
	 *
	 * @param status the status of this imtd ps details
	 */
	public void setStatus(int status);

	/**
	 * Returns the brand master sid of this imtd ps details.
	 *
	 * @return the brand master sid of this imtd ps details
	 */
	public int getBrandMasterSid();

	/**
	 * Sets the brand master sid of this imtd ps details.
	 *
	 * @param brandMasterSid the brand master sid of this imtd ps details
	 */
	public void setBrandMasterSid(int brandMasterSid);

	/**
	 * Returns the ps details price of this imtd ps details.
	 *
	 * @return the ps details price of this imtd ps details
	 */
	public double getPsDetailsPrice();

	/**
	 * Sets the ps details price of this imtd ps details.
	 *
	 * @param psDetailsPrice the ps details price of this imtd ps details
	 */
	public void setPsDetailsPrice(double psDetailsPrice);

	/**
	 * Returns the ps details created date of this imtd ps details.
	 *
	 * @return the ps details created date of this imtd ps details
	 */
	public Date getPsDetailsCreatedDate();

	/**
	 * Sets the ps details created date of this imtd ps details.
	 *
	 * @param psDetailsCreatedDate the ps details created date of this imtd ps details
	 */
	public void setPsDetailsCreatedDate(Date psDetailsCreatedDate);

	/**
	 * Returns the users sid of this imtd ps details.
	 *
	 * @return the users sid of this imtd ps details
	 */
	public int getUsersSid();

	/**
	 * Sets the users sid of this imtd ps details.
	 *
	 * @param usersSid the users sid of this imtd ps details
	 */
	public void setUsersSid(int usersSid);

	/**
	 * Returns the created by of this imtd ps details.
	 *
	 * @return the created by of this imtd ps details
	 */
	public int getCreatedBy();

	/**
	 * Sets the created by of this imtd ps details.
	 *
	 * @param createdBy the created by of this imtd ps details
	 */
	public void setCreatedBy(int createdBy);

	/**
	 * Returns the created date of this imtd ps details.
	 *
	 * @return the created date of this imtd ps details
	 */
	public Date getCreatedDate();

	/**
	 * Sets the created date of this imtd ps details.
	 *
	 * @param createdDate the created date of this imtd ps details
	 */
	public void setCreatedDate(Date createdDate);

	/**
	 * Returns the ps details sid of this imtd ps details.
	 *
	 * @return the ps details sid of this imtd ps details
	 */
	public int getPsDetailsSid();

	/**
	 * Sets the ps details sid of this imtd ps details.
	 *
	 * @param psDetailsSid the ps details sid of this imtd ps details
	 */
	public void setPsDetailsSid(int psDetailsSid);

	/**
	 * Returns the ps model sid of this imtd ps details.
	 *
	 * @return the ps model sid of this imtd ps details
	 */
	public int getPsModelSid();

	/**
	 * Sets the ps model sid of this imtd ps details.
	 *
	 * @param psModelSid the ps model sid of this imtd ps details
	 */
	public void setPsModelSid(int psModelSid);

	/**
	 * Returns the price protection price type of this imtd ps details.
	 *
	 * @return the price protection price type of this imtd ps details
	 */
	public int getPriceProtectionPriceType();

	/**
	 * Sets the price protection price type of this imtd ps details.
	 *
	 * @param priceProtectionPriceType the price protection price type of this imtd ps details
	 */
	public void setPriceProtectionPriceType(int priceProtectionPriceType);

	/**
	 * Returns the ps details base price of this imtd ps details.
	 *
	 * @return the ps details base price of this imtd ps details
	 */
	public double getPsDetailsBasePrice();

	/**
	 * Sets the ps details base price of this imtd ps details.
	 *
	 * @param psDetailsBasePrice the ps details base price of this imtd ps details
	 */
	public void setPsDetailsBasePrice(double psDetailsBasePrice);

	/**
	 * Returns the item no of this imtd ps details.
	 *
	 * @return the item no of this imtd ps details
	 */
	@AutoEscape
	public String getItemNo();

	/**
	 * Sets the item no of this imtd ps details.
	 *
	 * @param itemNo the item no of this imtd ps details
	 */
	public void setItemNo(String itemNo);

	/**
	 * Returns the ifp model sid of this imtd ps details.
	 *
	 * @return the ifp model sid of this imtd ps details
	 */
	public int getIfpModelSid();

	/**
	 * Sets the ifp model sid of this imtd ps details.
	 *
	 * @param ifpModelSid the ifp model sid of this imtd ps details
	 */
	public void setIfpModelSid(int ifpModelSid);

	/**
	 * Returns the ps details revision date of this imtd ps details.
	 *
	 * @return the ps details revision date of this imtd ps details
	 */
	public Date getPsDetailsRevisionDate();

	/**
	 * Sets the ps details revision date of this imtd ps details.
	 *
	 * @param psDetailsRevisionDate the ps details revision date of this imtd ps details
	 */
	public void setPsDetailsRevisionDate(Date psDetailsRevisionDate);

	/**
	 * Returns the nep of this imtd ps details.
	 *
	 * @return the nep of this imtd ps details
	 */
	public double getNep();

	/**
	 * Sets the nep of this imtd ps details.
	 *
	 * @param nep the nep of this imtd ps details
	 */
	public void setNep(double nep);

	/**
	 * Returns the ps details price tolerance of this imtd ps details.
	 *
	 * @return the ps details price tolerance of this imtd ps details
	 */
	public double getPsDetailsPriceTolerance();

	/**
	 * Sets the ps details price tolerance of this imtd ps details.
	 *
	 * @param psDetailsPriceTolerance the ps details price tolerance of this imtd ps details
	 */
	public void setPsDetailsPriceTolerance(double psDetailsPriceTolerance);

	/**
	 * Returns the price protection status of this imtd ps details.
	 *
	 * @return the price protection status of this imtd ps details
	 */
	public int getPriceProtectionStatus();

	/**
	 * Sets the price protection status of this imtd ps details.
	 *
	 * @param priceProtectionStatus the price protection status of this imtd ps details
	 */
	public void setPriceProtectionStatus(int priceProtectionStatus);

	/**
	 * Returns the ps dtls cont price startdate of this imtd ps details.
	 *
	 * @return the ps dtls cont price startdate of this imtd ps details
	 */
	public Date getPsDtlsContPriceStartdate();

	/**
	 * Sets the ps dtls cont price startdate of this imtd ps details.
	 *
	 * @param psDtlsContPriceStartdate the ps dtls cont price startdate of this imtd ps details
	 */
	public void setPsDtlsContPriceStartdate(Date psDtlsContPriceStartdate);

	/**
	 * Returns the reset eligible of this imtd ps details.
	 *
	 * @return the reset eligible of this imtd ps details
	 */
	public int getResetEligible();

	/**
	 * Sets the reset eligible of this imtd ps details.
	 *
	 * @param resetEligible the reset eligible of this imtd ps details
	 */
	public void setResetEligible(int resetEligible);

	/**
	 * Returns the net price type of this imtd ps details.
	 *
	 * @return the net price type of this imtd ps details
	 */
	public int getNetPriceType();

	/**
	 * Sets the net price type of this imtd ps details.
	 *
	 * @param netPriceType the net price type of this imtd ps details
	 */
	public void setNetPriceType(int netPriceType);

	/**
	 * Returns the operation of this imtd ps details.
	 *
	 * @return the operation of this imtd ps details
	 */
	@AutoEscape
	public String getOperation();

	/**
	 * Sets the operation of this imtd ps details.
	 *
	 * @param operation the operation of this imtd ps details
	 */
	public void setOperation(String operation);

	/**
	 * Returns the cfp model sid of this imtd ps details.
	 *
	 * @return the cfp model sid of this imtd ps details
	 */
	public int getCfpModelSid();

	/**
	 * Sets the cfp model sid of this imtd ps details.
	 *
	 * @param cfpModelSid the cfp model sid of this imtd ps details
	 */
	public void setCfpModelSid(int cfpModelSid);

	/**
	 * Returns the ps details pric prtcn eddate of this imtd ps details.
	 *
	 * @return the ps details pric prtcn eddate of this imtd ps details
	 */
	public Date getPsDetailsPricPrtcnEddate();

	/**
	 * Sets the ps details pric prtcn eddate of this imtd ps details.
	 *
	 * @param psDetailsPricPrtcnEddate the ps details pric prtcn eddate of this imtd ps details
	 */
	public void setPsDetailsPricPrtcnEddate(Date psDetailsPricPrtcnEddate);

	/**
	 * Returns the base price type of this imtd ps details.
	 *
	 * @return the base price type of this imtd ps details
	 */
	public int getBasePriceType();

	/**
	 * Sets the base price type of this imtd ps details.
	 *
	 * @param basePriceType the base price type of this imtd ps details
	 */
	public void setBasePriceType(int basePriceType);

	/**
	 * Returns the base price entry of this imtd ps details.
	 *
	 * @return the base price entry of this imtd ps details
	 */
	public double getBasePriceEntry();

	/**
	 * Sets the base price entry of this imtd ps details.
	 *
	 * @param basePriceEntry the base price entry of this imtd ps details
	 */
	public void setBasePriceEntry(double basePriceEntry);

	/**
	 * Returns the base price date of this imtd ps details.
	 *
	 * @return the base price date of this imtd ps details
	 */
	public Date getBasePriceDate();

	/**
	 * Sets the base price date of this imtd ps details.
	 *
	 * @param basePriceDate the base price date of this imtd ps details
	 */
	public void setBasePriceDate(Date basePriceDate);

	/**
	 * Returns the base price ddlb of this imtd ps details.
	 *
	 * @return the base price ddlb of this imtd ps details
	 */
	public int getBasePriceDdlb();

	/**
	 * Sets the base price ddlb of this imtd ps details.
	 *
	 * @param basePriceDdlb the base price ddlb of this imtd ps details
	 */
	public void setBasePriceDdlb(int basePriceDdlb);

	/**
	 * Returns the net base price of this imtd ps details.
	 *
	 * @return the net base price of this imtd ps details
	 */
	public int getNetBasePrice();

	/**
	 * Sets the net base price of this imtd ps details.
	 *
	 * @param netBasePrice the net base price of this imtd ps details
	 */
	public void setNetBasePrice(int netBasePrice);

	/**
	 * Returns the net base price formula ID of this imtd ps details.
	 *
	 * @return the net base price formula ID of this imtd ps details
	 */
	public int getNetBasePriceFormulaId();

	/**
	 * Sets the net base price formula ID of this imtd ps details.
	 *
	 * @param netBasePriceFormulaId the net base price formula ID of this imtd ps details
	 */
	public void setNetBasePriceFormulaId(int netBasePriceFormulaId);

	/**
	 * Returns the net base price formula no of this imtd ps details.
	 *
	 * @return the net base price formula no of this imtd ps details
	 */
	@AutoEscape
	public String getNetBasePriceFormulaNo();

	/**
	 * Sets the net base price formula no of this imtd ps details.
	 *
	 * @param netBasePriceFormulaNo the net base price formula no of this imtd ps details
	 */
	public void setNetBasePriceFormulaNo(String netBasePriceFormulaNo);

	/**
	 * Returns the net base price formula name of this imtd ps details.
	 *
	 * @return the net base price formula name of this imtd ps details
	 */
	@AutoEscape
	public String getNetBasePriceFormulaName();

	/**
	 * Sets the net base price formula name of this imtd ps details.
	 *
	 * @param netBasePriceFormulaName the net base price formula name of this imtd ps details
	 */
	public void setNetBasePriceFormulaName(String netBasePriceFormulaName);

	/**
	 * Returns the subsequent period price type of this imtd ps details.
	 *
	 * @return the subsequent period price type of this imtd ps details
	 */
	public int getSubsequentPeriodPriceType();

	/**
	 * Sets the subsequent period price type of this imtd ps details.
	 *
	 * @param subsequentPeriodPriceType the subsequent period price type of this imtd ps details
	 */
	public void setSubsequentPeriodPriceType(int subsequentPeriodPriceType);

	/**
	 * Returns the net subsequent period price of this imtd ps details.
	 *
	 * @return the net subsequent period price of this imtd ps details
	 */
	public int getNetSubsequentPeriodPrice();

	/**
	 * Sets the net subsequent period price of this imtd ps details.
	 *
	 * @param netSubsequentPeriodPrice the net subsequent period price of this imtd ps details
	 */
	public void setNetSubsequentPeriodPrice(int netSubsequentPeriodPrice);

	/**
	 * Returns the net subsequent price formula ID of this imtd ps details.
	 *
	 * @return the net subsequent price formula ID of this imtd ps details
	 */
	public int getNetSubsequentPriceFormulaId();

	/**
	 * Sets the net subsequent price formula ID of this imtd ps details.
	 *
	 * @param netSubsequentPriceFormulaId the net subsequent price formula ID of this imtd ps details
	 */
	public void setNetSubsequentPriceFormulaId(int netSubsequentPriceFormulaId);

	/**
	 * Returns the net subsequent price formula no of this imtd ps details.
	 *
	 * @return the net subsequent price formula no of this imtd ps details
	 */
	@AutoEscape
	public String getNetSubsequentPriceFormulaNo();

	/**
	 * Sets the net subsequent price formula no of this imtd ps details.
	 *
	 * @param netSubsequentPriceFormulaNo the net subsequent price formula no of this imtd ps details
	 */
	public void setNetSubsequentPriceFormulaNo(
		String netSubsequentPriceFormulaNo);

	/**
	 * Returns the net subsequent price formula name of this imtd ps details.
	 *
	 * @return the net subsequent price formula name of this imtd ps details
	 */
	@AutoEscape
	public String getNetSubsequentPriceFormulaName();

	/**
	 * Sets the net subsequent price formula name of this imtd ps details.
	 *
	 * @param netSubsequentPriceFormulaName the net subsequent price formula name of this imtd ps details
	 */
	public void setNetSubsequentPriceFormulaName(
		String netSubsequentPriceFormulaName);

	/**
	 * Returns the reset price type of this imtd ps details.
	 *
	 * @return the reset price type of this imtd ps details
	 */
	public int getResetPriceType();

	/**
	 * Sets the reset price type of this imtd ps details.
	 *
	 * @param resetPriceType the reset price type of this imtd ps details
	 */
	public void setResetPriceType(int resetPriceType);

	/**
	 * Returns the net reset price type of this imtd ps details.
	 *
	 * @return the net reset price type of this imtd ps details
	 */
	public int getNetResetPriceType();

	/**
	 * Sets the net reset price type of this imtd ps details.
	 *
	 * @param netResetPriceType the net reset price type of this imtd ps details
	 */
	public void setNetResetPriceType(int netResetPriceType);

	/**
	 * Returns the net reset price formula ID of this imtd ps details.
	 *
	 * @return the net reset price formula ID of this imtd ps details
	 */
	public int getNetResetPriceFormulaId();

	/**
	 * Sets the net reset price formula ID of this imtd ps details.
	 *
	 * @param netResetPriceFormulaId the net reset price formula ID of this imtd ps details
	 */
	public void setNetResetPriceFormulaId(int netResetPriceFormulaId);

	/**
	 * Returns the net reset price formula no of this imtd ps details.
	 *
	 * @return the net reset price formula no of this imtd ps details
	 */
	@AutoEscape
	public String getNetResetPriceFormulaNo();

	/**
	 * Sets the net reset price formula no of this imtd ps details.
	 *
	 * @param netResetPriceFormulaNo the net reset price formula no of this imtd ps details
	 */
	public void setNetResetPriceFormulaNo(String netResetPriceFormulaNo);

	/**
	 * Returns the net reset price formula name of this imtd ps details.
	 *
	 * @return the net reset price formula name of this imtd ps details
	 */
	@AutoEscape
	public String getNetResetPriceFormulaName();

	/**
	 * Sets the net reset price formula name of this imtd ps details.
	 *
	 * @param netResetPriceFormulaName the net reset price formula name of this imtd ps details
	 */
	public void setNetResetPriceFormulaName(String netResetPriceFormulaName);

	@Override
	public boolean isNew();

	@Override
	public void setNew(boolean n);

	@Override
	public boolean isCachedModel();

	@Override
	public void setCachedModel(boolean cachedModel);

	@Override
	public boolean isEscapedModel();

	@Override
	public Serializable getPrimaryKeyObj();

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj);

	@Override
	public ExpandoBridge getExpandoBridge();

	@Override
	public void setExpandoBridgeAttributes(BaseModel<?> baseModel);

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge);

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext);

	@Override
	public Object clone();

	@Override
	public int compareTo(ImtdPsDetails imtdPsDetails);

	@Override
	public int hashCode();

	@Override
	public CacheModel<ImtdPsDetails> toCacheModel();

	@Override
	public ImtdPsDetails toEscapedModel();

	@Override
	public ImtdPsDetails toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}