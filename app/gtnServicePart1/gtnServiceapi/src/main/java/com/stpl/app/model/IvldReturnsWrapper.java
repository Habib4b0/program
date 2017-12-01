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
 * This class is a wrapper for {@link IvldReturns}.
 * </p>
 *
 * @author
 * @see IvldReturns
 * @generated
 */
@ProviderType
public class IvldReturnsWrapper implements IvldReturns,
	ModelWrapper<IvldReturns> {
	public IvldReturnsWrapper(IvldReturns ivldReturns) {
		_ivldReturns = ivldReturns;
	}

	@Override
	public Class<?> getModelClass() {
		return IvldReturns.class;
	}

	@Override
	public String getModelClassName() {
		return IvldReturns.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("adjValueAtOrigAsp", getAdjValueAtOrigAsp());
		attributes.put("firstReturn", getFirstReturn());
		attributes.put("asp", getAsp());
		attributes.put("maxExpiredMonthPlusCutoff",
			getMaxExpiredMonthPlusCutoff());
		attributes.put("posEstimatedReturnUnits", getPosEstimatedReturnUnits());
		attributes.put("origSaleMonthCutOff", getOrigSaleMonthCutOff());
		attributes.put("calcUsed", getCalcUsed());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("lastReturn", getLastReturn());
		attributes.put("expectedReturnUnits", getExpectedReturnUnits());
		attributes.put("createdDate", getCreatedDate());
		attributes.put("createdBy", getCreatedBy());
		attributes.put("source", getSource());
		attributes.put("version", getVersion());
		attributes.put("addChgDelIndicator", getAddChgDelIndicator());
		attributes.put("weightedAvgMonths", getWeightedAvgMonths());
		attributes.put("errorCode", getErrorCode());
		attributes.put("modifiedBy", getModifiedBy());
		attributes.put("ivldReturnsSid", getIvldReturnsSid());
		attributes.put("intfInsertedDate", getIntfInsertedDate());
		attributes.put("pct25th", getPct25th());
		attributes.put("loadDate", getLoadDate());
		attributes.put("maxExpiredMonth", getMaxExpiredMonth());
		attributes.put("reprocessedFlag", getReprocessedFlag());
		attributes.put("actualReturnRate", getActualReturnRate());
		attributes.put("rreserveId", getRreserveId());
		attributes.put("returnComplete", getReturnComplete());
		attributes.put("expectedReturnRate", getExpectedReturnRate());
		attributes.put("pct50th", getPct50th());
		attributes.put("within50qrtile", getWithin50qrtile());
		attributes.put("rreserveInterfaceId", getRreserveInterfaceId());
		attributes.put("cumReturnUnits", getCumReturnUnits());
		attributes.put("reasonForFailure", getReasonForFailure());
		attributes.put("origSaleMonth", getOrigSaleMonth());
		attributes.put("description", getDescription());
		attributes.put("sku", getSku());
		attributes.put("upperLimit", getUpperLimit());
		attributes.put("lowerLimit", getLowerLimit());
		attributes.put("valueAtOrigAsp", getValueAtOrigAsp());
		attributes.put("adjEstimatedReturnUnits", getAdjEstimatedReturnUnits());
		attributes.put("pct75th", getPct75th());
		attributes.put("pastExpiration", getPastExpiration());
		attributes.put("batchId", getBatchId());
		attributes.put("maximum", getMaximum());
		attributes.put("origSaleUnits", getOrigSaleUnits());
		attributes.put("errorField", getErrorField());
		attributes.put("brand", getBrand());
		attributes.put("origSaleDollars", getOrigSaleDollars());
		attributes.put("checkRecord", getCheckRecord());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String adjValueAtOrigAsp = (String)attributes.get("adjValueAtOrigAsp");

		if (adjValueAtOrigAsp != null) {
			setAdjValueAtOrigAsp(adjValueAtOrigAsp);
		}

		String firstReturn = (String)attributes.get("firstReturn");

		if (firstReturn != null) {
			setFirstReturn(firstReturn);
		}

		String asp = (String)attributes.get("asp");

		if (asp != null) {
			setAsp(asp);
		}

		String maxExpiredMonthPlusCutoff = (String)attributes.get(
				"maxExpiredMonthPlusCutoff");

		if (maxExpiredMonthPlusCutoff != null) {
			setMaxExpiredMonthPlusCutoff(maxExpiredMonthPlusCutoff);
		}

		String posEstimatedReturnUnits = (String)attributes.get(
				"posEstimatedReturnUnits");

		if (posEstimatedReturnUnits != null) {
			setPosEstimatedReturnUnits(posEstimatedReturnUnits);
		}

		String origSaleMonthCutOff = (String)attributes.get(
				"origSaleMonthCutOff");

		if (origSaleMonthCutOff != null) {
			setOrigSaleMonthCutOff(origSaleMonthCutOff);
		}

		String calcUsed = (String)attributes.get("calcUsed");

		if (calcUsed != null) {
			setCalcUsed(calcUsed);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String lastReturn = (String)attributes.get("lastReturn");

		if (lastReturn != null) {
			setLastReturn(lastReturn);
		}

		String expectedReturnUnits = (String)attributes.get(
				"expectedReturnUnits");

		if (expectedReturnUnits != null) {
			setExpectedReturnUnits(expectedReturnUnits);
		}

		Date createdDate = (Date)attributes.get("createdDate");

		if (createdDate != null) {
			setCreatedDate(createdDate);
		}

		String createdBy = (String)attributes.get("createdBy");

		if (createdBy != null) {
			setCreatedBy(createdBy);
		}

		String source = (String)attributes.get("source");

		if (source != null) {
			setSource(source);
		}

		String version = (String)attributes.get("version");

		if (version != null) {
			setVersion(version);
		}

		String addChgDelIndicator = (String)attributes.get("addChgDelIndicator");

		if (addChgDelIndicator != null) {
			setAddChgDelIndicator(addChgDelIndicator);
		}

		String weightedAvgMonths = (String)attributes.get("weightedAvgMonths");

		if (weightedAvgMonths != null) {
			setWeightedAvgMonths(weightedAvgMonths);
		}

		String errorCode = (String)attributes.get("errorCode");

		if (errorCode != null) {
			setErrorCode(errorCode);
		}

		String modifiedBy = (String)attributes.get("modifiedBy");

		if (modifiedBy != null) {
			setModifiedBy(modifiedBy);
		}

		Integer ivldReturnsSid = (Integer)attributes.get("ivldReturnsSid");

		if (ivldReturnsSid != null) {
			setIvldReturnsSid(ivldReturnsSid);
		}

		Date intfInsertedDate = (Date)attributes.get("intfInsertedDate");

		if (intfInsertedDate != null) {
			setIntfInsertedDate(intfInsertedDate);
		}

		String pct25th = (String)attributes.get("pct25th");

		if (pct25th != null) {
			setPct25th(pct25th);
		}

		String loadDate = (String)attributes.get("loadDate");

		if (loadDate != null) {
			setLoadDate(loadDate);
		}

		String maxExpiredMonth = (String)attributes.get("maxExpiredMonth");

		if (maxExpiredMonth != null) {
			setMaxExpiredMonth(maxExpiredMonth);
		}

		String reprocessedFlag = (String)attributes.get("reprocessedFlag");

		if (reprocessedFlag != null) {
			setReprocessedFlag(reprocessedFlag);
		}

		String actualReturnRate = (String)attributes.get("actualReturnRate");

		if (actualReturnRate != null) {
			setActualReturnRate(actualReturnRate);
		}

		String rreserveId = (String)attributes.get("rreserveId");

		if (rreserveId != null) {
			setRreserveId(rreserveId);
		}

		String returnComplete = (String)attributes.get("returnComplete");

		if (returnComplete != null) {
			setReturnComplete(returnComplete);
		}

		String expectedReturnRate = (String)attributes.get("expectedReturnRate");

		if (expectedReturnRate != null) {
			setExpectedReturnRate(expectedReturnRate);
		}

		String pct50th = (String)attributes.get("pct50th");

		if (pct50th != null) {
			setPct50th(pct50th);
		}

		String within50qrtile = (String)attributes.get("within50qrtile");

		if (within50qrtile != null) {
			setWithin50qrtile(within50qrtile);
		}

		Integer rreserveInterfaceId = (Integer)attributes.get(
				"rreserveInterfaceId");

		if (rreserveInterfaceId != null) {
			setRreserveInterfaceId(rreserveInterfaceId);
		}

		String cumReturnUnits = (String)attributes.get("cumReturnUnits");

		if (cumReturnUnits != null) {
			setCumReturnUnits(cumReturnUnits);
		}

		String reasonForFailure = (String)attributes.get("reasonForFailure");

		if (reasonForFailure != null) {
			setReasonForFailure(reasonForFailure);
		}

		String origSaleMonth = (String)attributes.get("origSaleMonth");

		if (origSaleMonth != null) {
			setOrigSaleMonth(origSaleMonth);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		String sku = (String)attributes.get("sku");

		if (sku != null) {
			setSku(sku);
		}

		String upperLimit = (String)attributes.get("upperLimit");

		if (upperLimit != null) {
			setUpperLimit(upperLimit);
		}

		String lowerLimit = (String)attributes.get("lowerLimit");

		if (lowerLimit != null) {
			setLowerLimit(lowerLimit);
		}

		String valueAtOrigAsp = (String)attributes.get("valueAtOrigAsp");

		if (valueAtOrigAsp != null) {
			setValueAtOrigAsp(valueAtOrigAsp);
		}

		String adjEstimatedReturnUnits = (String)attributes.get(
				"adjEstimatedReturnUnits");

		if (adjEstimatedReturnUnits != null) {
			setAdjEstimatedReturnUnits(adjEstimatedReturnUnits);
		}

		String pct75th = (String)attributes.get("pct75th");

		if (pct75th != null) {
			setPct75th(pct75th);
		}

		String pastExpiration = (String)attributes.get("pastExpiration");

		if (pastExpiration != null) {
			setPastExpiration(pastExpiration);
		}

		String batchId = (String)attributes.get("batchId");

		if (batchId != null) {
			setBatchId(batchId);
		}

		String maximum = (String)attributes.get("maximum");

		if (maximum != null) {
			setMaximum(maximum);
		}

		String origSaleUnits = (String)attributes.get("origSaleUnits");

		if (origSaleUnits != null) {
			setOrigSaleUnits(origSaleUnits);
		}

		String errorField = (String)attributes.get("errorField");

		if (errorField != null) {
			setErrorField(errorField);
		}

		String brand = (String)attributes.get("brand");

		if (brand != null) {
			setBrand(brand);
		}

		String origSaleDollars = (String)attributes.get("origSaleDollars");

		if (origSaleDollars != null) {
			setOrigSaleDollars(origSaleDollars);
		}

		Boolean checkRecord = (Boolean)attributes.get("checkRecord");

		if (checkRecord != null) {
			setCheckRecord(checkRecord);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new IvldReturnsWrapper((IvldReturns)_ivldReturns.clone());
	}

	@Override
	public int compareTo(IvldReturns ivldReturns) {
		return _ivldReturns.compareTo(ivldReturns);
	}

	/**
	* Returns the actual return rate of this ivld returns.
	*
	* @return the actual return rate of this ivld returns
	*/
	@Override
	public java.lang.String getActualReturnRate() {
		return _ivldReturns.getActualReturnRate();
	}

	/**
	* Returns the add chg del indicator of this ivld returns.
	*
	* @return the add chg del indicator of this ivld returns
	*/
	@Override
	public java.lang.String getAddChgDelIndicator() {
		return _ivldReturns.getAddChgDelIndicator();
	}

	/**
	* Returns the adj estimated return units of this ivld returns.
	*
	* @return the adj estimated return units of this ivld returns
	*/
	@Override
	public java.lang.String getAdjEstimatedReturnUnits() {
		return _ivldReturns.getAdjEstimatedReturnUnits();
	}

	/**
	* Returns the adj value at orig asp of this ivld returns.
	*
	* @return the adj value at orig asp of this ivld returns
	*/
	@Override
	public java.lang.String getAdjValueAtOrigAsp() {
		return _ivldReturns.getAdjValueAtOrigAsp();
	}

	/**
	* Returns the asp of this ivld returns.
	*
	* @return the asp of this ivld returns
	*/
	@Override
	public java.lang.String getAsp() {
		return _ivldReturns.getAsp();
	}

	/**
	* Returns the batch ID of this ivld returns.
	*
	* @return the batch ID of this ivld returns
	*/
	@Override
	public java.lang.String getBatchId() {
		return _ivldReturns.getBatchId();
	}

	/**
	* Returns the brand of this ivld returns.
	*
	* @return the brand of this ivld returns
	*/
	@Override
	public java.lang.String getBrand() {
		return _ivldReturns.getBrand();
	}

	/**
	* Returns the calc used of this ivld returns.
	*
	* @return the calc used of this ivld returns
	*/
	@Override
	public java.lang.String getCalcUsed() {
		return _ivldReturns.getCalcUsed();
	}

	/**
	* Returns the check record of this ivld returns.
	*
	* @return the check record of this ivld returns
	*/
	@Override
	public boolean getCheckRecord() {
		return _ivldReturns.getCheckRecord();
	}

	/**
	* Returns the created by of this ivld returns.
	*
	* @return the created by of this ivld returns
	*/
	@Override
	public java.lang.String getCreatedBy() {
		return _ivldReturns.getCreatedBy();
	}

	/**
	* Returns the created date of this ivld returns.
	*
	* @return the created date of this ivld returns
	*/
	@Override
	public Date getCreatedDate() {
		return _ivldReturns.getCreatedDate();
	}

	/**
	* Returns the cum return units of this ivld returns.
	*
	* @return the cum return units of this ivld returns
	*/
	@Override
	public java.lang.String getCumReturnUnits() {
		return _ivldReturns.getCumReturnUnits();
	}

	/**
	* Returns the description of this ivld returns.
	*
	* @return the description of this ivld returns
	*/
	@Override
	public java.lang.String getDescription() {
		return _ivldReturns.getDescription();
	}

	/**
	* Returns the error code of this ivld returns.
	*
	* @return the error code of this ivld returns
	*/
	@Override
	public java.lang.String getErrorCode() {
		return _ivldReturns.getErrorCode();
	}

	/**
	* Returns the error field of this ivld returns.
	*
	* @return the error field of this ivld returns
	*/
	@Override
	public java.lang.String getErrorField() {
		return _ivldReturns.getErrorField();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _ivldReturns.getExpandoBridge();
	}

	/**
	* Returns the expected return rate of this ivld returns.
	*
	* @return the expected return rate of this ivld returns
	*/
	@Override
	public java.lang.String getExpectedReturnRate() {
		return _ivldReturns.getExpectedReturnRate();
	}

	/**
	* Returns the expected return units of this ivld returns.
	*
	* @return the expected return units of this ivld returns
	*/
	@Override
	public java.lang.String getExpectedReturnUnits() {
		return _ivldReturns.getExpectedReturnUnits();
	}

	/**
	* Returns the first return of this ivld returns.
	*
	* @return the first return of this ivld returns
	*/
	@Override
	public java.lang.String getFirstReturn() {
		return _ivldReturns.getFirstReturn();
	}

	/**
	* Returns the intf inserted date of this ivld returns.
	*
	* @return the intf inserted date of this ivld returns
	*/
	@Override
	public Date getIntfInsertedDate() {
		return _ivldReturns.getIntfInsertedDate();
	}

	/**
	* Returns the ivld returns sid of this ivld returns.
	*
	* @return the ivld returns sid of this ivld returns
	*/
	@Override
	public int getIvldReturnsSid() {
		return _ivldReturns.getIvldReturnsSid();
	}

	/**
	* Returns the last return of this ivld returns.
	*
	* @return the last return of this ivld returns
	*/
	@Override
	public java.lang.String getLastReturn() {
		return _ivldReturns.getLastReturn();
	}

	/**
	* Returns the load date of this ivld returns.
	*
	* @return the load date of this ivld returns
	*/
	@Override
	public java.lang.String getLoadDate() {
		return _ivldReturns.getLoadDate();
	}

	/**
	* Returns the lower limit of this ivld returns.
	*
	* @return the lower limit of this ivld returns
	*/
	@Override
	public java.lang.String getLowerLimit() {
		return _ivldReturns.getLowerLimit();
	}

	/**
	* Returns the max expired month of this ivld returns.
	*
	* @return the max expired month of this ivld returns
	*/
	@Override
	public java.lang.String getMaxExpiredMonth() {
		return _ivldReturns.getMaxExpiredMonth();
	}

	/**
	* Returns the max expired month plus cutoff of this ivld returns.
	*
	* @return the max expired month plus cutoff of this ivld returns
	*/
	@Override
	public java.lang.String getMaxExpiredMonthPlusCutoff() {
		return _ivldReturns.getMaxExpiredMonthPlusCutoff();
	}

	/**
	* Returns the maximum of this ivld returns.
	*
	* @return the maximum of this ivld returns
	*/
	@Override
	public java.lang.String getMaximum() {
		return _ivldReturns.getMaximum();
	}

	/**
	* Returns the modified by of this ivld returns.
	*
	* @return the modified by of this ivld returns
	*/
	@Override
	public java.lang.String getModifiedBy() {
		return _ivldReturns.getModifiedBy();
	}

	/**
	* Returns the modified date of this ivld returns.
	*
	* @return the modified date of this ivld returns
	*/
	@Override
	public Date getModifiedDate() {
		return _ivldReturns.getModifiedDate();
	}

	/**
	* Returns the orig sale dollars of this ivld returns.
	*
	* @return the orig sale dollars of this ivld returns
	*/
	@Override
	public java.lang.String getOrigSaleDollars() {
		return _ivldReturns.getOrigSaleDollars();
	}

	/**
	* Returns the orig sale month of this ivld returns.
	*
	* @return the orig sale month of this ivld returns
	*/
	@Override
	public java.lang.String getOrigSaleMonth() {
		return _ivldReturns.getOrigSaleMonth();
	}

	/**
	* Returns the orig sale month cut off of this ivld returns.
	*
	* @return the orig sale month cut off of this ivld returns
	*/
	@Override
	public java.lang.String getOrigSaleMonthCutOff() {
		return _ivldReturns.getOrigSaleMonthCutOff();
	}

	/**
	* Returns the orig sale units of this ivld returns.
	*
	* @return the orig sale units of this ivld returns
	*/
	@Override
	public java.lang.String getOrigSaleUnits() {
		return _ivldReturns.getOrigSaleUnits();
	}

	/**
	* Returns the past expiration of this ivld returns.
	*
	* @return the past expiration of this ivld returns
	*/
	@Override
	public java.lang.String getPastExpiration() {
		return _ivldReturns.getPastExpiration();
	}

	/**
	* Returns the pct25th of this ivld returns.
	*
	* @return the pct25th of this ivld returns
	*/
	@Override
	public java.lang.String getPct25th() {
		return _ivldReturns.getPct25th();
	}

	/**
	* Returns the pct50th of this ivld returns.
	*
	* @return the pct50th of this ivld returns
	*/
	@Override
	public java.lang.String getPct50th() {
		return _ivldReturns.getPct50th();
	}

	/**
	* Returns the pct75th of this ivld returns.
	*
	* @return the pct75th of this ivld returns
	*/
	@Override
	public java.lang.String getPct75th() {
		return _ivldReturns.getPct75th();
	}

	/**
	* Returns the pos estimated return units of this ivld returns.
	*
	* @return the pos estimated return units of this ivld returns
	*/
	@Override
	public java.lang.String getPosEstimatedReturnUnits() {
		return _ivldReturns.getPosEstimatedReturnUnits();
	}

	/**
	* Returns the primary key of this ivld returns.
	*
	* @return the primary key of this ivld returns
	*/
	@Override
	public int getPrimaryKey() {
		return _ivldReturns.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _ivldReturns.getPrimaryKeyObj();
	}

	/**
	* Returns the reason for failure of this ivld returns.
	*
	* @return the reason for failure of this ivld returns
	*/
	@Override
	public java.lang.String getReasonForFailure() {
		return _ivldReturns.getReasonForFailure();
	}

	/**
	* Returns the reprocessed flag of this ivld returns.
	*
	* @return the reprocessed flag of this ivld returns
	*/
	@Override
	public java.lang.String getReprocessedFlag() {
		return _ivldReturns.getReprocessedFlag();
	}

	/**
	* Returns the return complete of this ivld returns.
	*
	* @return the return complete of this ivld returns
	*/
	@Override
	public java.lang.String getReturnComplete() {
		return _ivldReturns.getReturnComplete();
	}

	/**
	* Returns the rreserve ID of this ivld returns.
	*
	* @return the rreserve ID of this ivld returns
	*/
	@Override
	public java.lang.String getRreserveId() {
		return _ivldReturns.getRreserveId();
	}

	/**
	* Returns the rreserve interface ID of this ivld returns.
	*
	* @return the rreserve interface ID of this ivld returns
	*/
	@Override
	public int getRreserveInterfaceId() {
		return _ivldReturns.getRreserveInterfaceId();
	}

	/**
	* Returns the sku of this ivld returns.
	*
	* @return the sku of this ivld returns
	*/
	@Override
	public java.lang.String getSku() {
		return _ivldReturns.getSku();
	}

	/**
	* Returns the source of this ivld returns.
	*
	* @return the source of this ivld returns
	*/
	@Override
	public java.lang.String getSource() {
		return _ivldReturns.getSource();
	}

	/**
	* Returns the upper limit of this ivld returns.
	*
	* @return the upper limit of this ivld returns
	*/
	@Override
	public java.lang.String getUpperLimit() {
		return _ivldReturns.getUpperLimit();
	}

	/**
	* Returns the value at orig asp of this ivld returns.
	*
	* @return the value at orig asp of this ivld returns
	*/
	@Override
	public java.lang.String getValueAtOrigAsp() {
		return _ivldReturns.getValueAtOrigAsp();
	}

	/**
	* Returns the version of this ivld returns.
	*
	* @return the version of this ivld returns
	*/
	@Override
	public java.lang.String getVersion() {
		return _ivldReturns.getVersion();
	}

	/**
	* Returns the weighted avg months of this ivld returns.
	*
	* @return the weighted avg months of this ivld returns
	*/
	@Override
	public java.lang.String getWeightedAvgMonths() {
		return _ivldReturns.getWeightedAvgMonths();
	}

	/**
	* Returns the within50qrtile of this ivld returns.
	*
	* @return the within50qrtile of this ivld returns
	*/
	@Override
	public java.lang.String getWithin50qrtile() {
		return _ivldReturns.getWithin50qrtile();
	}

	@Override
	public int hashCode() {
		return _ivldReturns.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _ivldReturns.isCachedModel();
	}

	/**
	* Returns <code>true</code> if this ivld returns is check record.
	*
	* @return <code>true</code> if this ivld returns is check record; <code>false</code> otherwise
	*/
	@Override
	public boolean isCheckRecord() {
		return _ivldReturns.isCheckRecord();
	}

	@Override
	public boolean isEscapedModel() {
		return _ivldReturns.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _ivldReturns.isNew();
	}

	@Override
	public void persist() {
		_ivldReturns.persist();
	}

	/**
	* Sets the actual return rate of this ivld returns.
	*
	* @param actualReturnRate the actual return rate of this ivld returns
	*/
	@Override
	public void setActualReturnRate(java.lang.String actualReturnRate) {
		_ivldReturns.setActualReturnRate(actualReturnRate);
	}

	/**
	* Sets the add chg del indicator of this ivld returns.
	*
	* @param addChgDelIndicator the add chg del indicator of this ivld returns
	*/
	@Override
	public void setAddChgDelIndicator(java.lang.String addChgDelIndicator) {
		_ivldReturns.setAddChgDelIndicator(addChgDelIndicator);
	}

	/**
	* Sets the adj estimated return units of this ivld returns.
	*
	* @param adjEstimatedReturnUnits the adj estimated return units of this ivld returns
	*/
	@Override
	public void setAdjEstimatedReturnUnits(
		java.lang.String adjEstimatedReturnUnits) {
		_ivldReturns.setAdjEstimatedReturnUnits(adjEstimatedReturnUnits);
	}

	/**
	* Sets the adj value at orig asp of this ivld returns.
	*
	* @param adjValueAtOrigAsp the adj value at orig asp of this ivld returns
	*/
	@Override
	public void setAdjValueAtOrigAsp(java.lang.String adjValueAtOrigAsp) {
		_ivldReturns.setAdjValueAtOrigAsp(adjValueAtOrigAsp);
	}

	/**
	* Sets the asp of this ivld returns.
	*
	* @param asp the asp of this ivld returns
	*/
	@Override
	public void setAsp(java.lang.String asp) {
		_ivldReturns.setAsp(asp);
	}

	/**
	* Sets the batch ID of this ivld returns.
	*
	* @param batchId the batch ID of this ivld returns
	*/
	@Override
	public void setBatchId(java.lang.String batchId) {
		_ivldReturns.setBatchId(batchId);
	}

	/**
	* Sets the brand of this ivld returns.
	*
	* @param brand the brand of this ivld returns
	*/
	@Override
	public void setBrand(java.lang.String brand) {
		_ivldReturns.setBrand(brand);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_ivldReturns.setCachedModel(cachedModel);
	}

	/**
	* Sets the calc used of this ivld returns.
	*
	* @param calcUsed the calc used of this ivld returns
	*/
	@Override
	public void setCalcUsed(java.lang.String calcUsed) {
		_ivldReturns.setCalcUsed(calcUsed);
	}

	/**
	* Sets whether this ivld returns is check record.
	*
	* @param checkRecord the check record of this ivld returns
	*/
	@Override
	public void setCheckRecord(boolean checkRecord) {
		_ivldReturns.setCheckRecord(checkRecord);
	}

	/**
	* Sets the created by of this ivld returns.
	*
	* @param createdBy the created by of this ivld returns
	*/
	@Override
	public void setCreatedBy(java.lang.String createdBy) {
		_ivldReturns.setCreatedBy(createdBy);
	}

	/**
	* Sets the created date of this ivld returns.
	*
	* @param createdDate the created date of this ivld returns
	*/
	@Override
	public void setCreatedDate(Date createdDate) {
		_ivldReturns.setCreatedDate(createdDate);
	}

	/**
	* Sets the cum return units of this ivld returns.
	*
	* @param cumReturnUnits the cum return units of this ivld returns
	*/
	@Override
	public void setCumReturnUnits(java.lang.String cumReturnUnits) {
		_ivldReturns.setCumReturnUnits(cumReturnUnits);
	}

	/**
	* Sets the description of this ivld returns.
	*
	* @param description the description of this ivld returns
	*/
	@Override
	public void setDescription(java.lang.String description) {
		_ivldReturns.setDescription(description);
	}

	/**
	* Sets the error code of this ivld returns.
	*
	* @param errorCode the error code of this ivld returns
	*/
	@Override
	public void setErrorCode(java.lang.String errorCode) {
		_ivldReturns.setErrorCode(errorCode);
	}

	/**
	* Sets the error field of this ivld returns.
	*
	* @param errorField the error field of this ivld returns
	*/
	@Override
	public void setErrorField(java.lang.String errorField) {
		_ivldReturns.setErrorField(errorField);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_ivldReturns.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_ivldReturns.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_ivldReturns.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the expected return rate of this ivld returns.
	*
	* @param expectedReturnRate the expected return rate of this ivld returns
	*/
	@Override
	public void setExpectedReturnRate(java.lang.String expectedReturnRate) {
		_ivldReturns.setExpectedReturnRate(expectedReturnRate);
	}

	/**
	* Sets the expected return units of this ivld returns.
	*
	* @param expectedReturnUnits the expected return units of this ivld returns
	*/
	@Override
	public void setExpectedReturnUnits(java.lang.String expectedReturnUnits) {
		_ivldReturns.setExpectedReturnUnits(expectedReturnUnits);
	}

	/**
	* Sets the first return of this ivld returns.
	*
	* @param firstReturn the first return of this ivld returns
	*/
	@Override
	public void setFirstReturn(java.lang.String firstReturn) {
		_ivldReturns.setFirstReturn(firstReturn);
	}

	/**
	* Sets the intf inserted date of this ivld returns.
	*
	* @param intfInsertedDate the intf inserted date of this ivld returns
	*/
	@Override
	public void setIntfInsertedDate(Date intfInsertedDate) {
		_ivldReturns.setIntfInsertedDate(intfInsertedDate);
	}

	/**
	* Sets the ivld returns sid of this ivld returns.
	*
	* @param ivldReturnsSid the ivld returns sid of this ivld returns
	*/
	@Override
	public void setIvldReturnsSid(int ivldReturnsSid) {
		_ivldReturns.setIvldReturnsSid(ivldReturnsSid);
	}

	/**
	* Sets the last return of this ivld returns.
	*
	* @param lastReturn the last return of this ivld returns
	*/
	@Override
	public void setLastReturn(java.lang.String lastReturn) {
		_ivldReturns.setLastReturn(lastReturn);
	}

	/**
	* Sets the load date of this ivld returns.
	*
	* @param loadDate the load date of this ivld returns
	*/
	@Override
	public void setLoadDate(java.lang.String loadDate) {
		_ivldReturns.setLoadDate(loadDate);
	}

	/**
	* Sets the lower limit of this ivld returns.
	*
	* @param lowerLimit the lower limit of this ivld returns
	*/
	@Override
	public void setLowerLimit(java.lang.String lowerLimit) {
		_ivldReturns.setLowerLimit(lowerLimit);
	}

	/**
	* Sets the max expired month of this ivld returns.
	*
	* @param maxExpiredMonth the max expired month of this ivld returns
	*/
	@Override
	public void setMaxExpiredMonth(java.lang.String maxExpiredMonth) {
		_ivldReturns.setMaxExpiredMonth(maxExpiredMonth);
	}

	/**
	* Sets the max expired month plus cutoff of this ivld returns.
	*
	* @param maxExpiredMonthPlusCutoff the max expired month plus cutoff of this ivld returns
	*/
	@Override
	public void setMaxExpiredMonthPlusCutoff(
		java.lang.String maxExpiredMonthPlusCutoff) {
		_ivldReturns.setMaxExpiredMonthPlusCutoff(maxExpiredMonthPlusCutoff);
	}

	/**
	* Sets the maximum of this ivld returns.
	*
	* @param maximum the maximum of this ivld returns
	*/
	@Override
	public void setMaximum(java.lang.String maximum) {
		_ivldReturns.setMaximum(maximum);
	}

	/**
	* Sets the modified by of this ivld returns.
	*
	* @param modifiedBy the modified by of this ivld returns
	*/
	@Override
	public void setModifiedBy(java.lang.String modifiedBy) {
		_ivldReturns.setModifiedBy(modifiedBy);
	}

	/**
	* Sets the modified date of this ivld returns.
	*
	* @param modifiedDate the modified date of this ivld returns
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_ivldReturns.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_ivldReturns.setNew(n);
	}

	/**
	* Sets the orig sale dollars of this ivld returns.
	*
	* @param origSaleDollars the orig sale dollars of this ivld returns
	*/
	@Override
	public void setOrigSaleDollars(java.lang.String origSaleDollars) {
		_ivldReturns.setOrigSaleDollars(origSaleDollars);
	}

	/**
	* Sets the orig sale month of this ivld returns.
	*
	* @param origSaleMonth the orig sale month of this ivld returns
	*/
	@Override
	public void setOrigSaleMonth(java.lang.String origSaleMonth) {
		_ivldReturns.setOrigSaleMonth(origSaleMonth);
	}

	/**
	* Sets the orig sale month cut off of this ivld returns.
	*
	* @param origSaleMonthCutOff the orig sale month cut off of this ivld returns
	*/
	@Override
	public void setOrigSaleMonthCutOff(java.lang.String origSaleMonthCutOff) {
		_ivldReturns.setOrigSaleMonthCutOff(origSaleMonthCutOff);
	}

	/**
	* Sets the orig sale units of this ivld returns.
	*
	* @param origSaleUnits the orig sale units of this ivld returns
	*/
	@Override
	public void setOrigSaleUnits(java.lang.String origSaleUnits) {
		_ivldReturns.setOrigSaleUnits(origSaleUnits);
	}

	/**
	* Sets the past expiration of this ivld returns.
	*
	* @param pastExpiration the past expiration of this ivld returns
	*/
	@Override
	public void setPastExpiration(java.lang.String pastExpiration) {
		_ivldReturns.setPastExpiration(pastExpiration);
	}

	/**
	* Sets the pct25th of this ivld returns.
	*
	* @param pct25th the pct25th of this ivld returns
	*/
	@Override
	public void setPct25th(java.lang.String pct25th) {
		_ivldReturns.setPct25th(pct25th);
	}

	/**
	* Sets the pct50th of this ivld returns.
	*
	* @param pct50th the pct50th of this ivld returns
	*/
	@Override
	public void setPct50th(java.lang.String pct50th) {
		_ivldReturns.setPct50th(pct50th);
	}

	/**
	* Sets the pct75th of this ivld returns.
	*
	* @param pct75th the pct75th of this ivld returns
	*/
	@Override
	public void setPct75th(java.lang.String pct75th) {
		_ivldReturns.setPct75th(pct75th);
	}

	/**
	* Sets the pos estimated return units of this ivld returns.
	*
	* @param posEstimatedReturnUnits the pos estimated return units of this ivld returns
	*/
	@Override
	public void setPosEstimatedReturnUnits(
		java.lang.String posEstimatedReturnUnits) {
		_ivldReturns.setPosEstimatedReturnUnits(posEstimatedReturnUnits);
	}

	/**
	* Sets the primary key of this ivld returns.
	*
	* @param primaryKey the primary key of this ivld returns
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_ivldReturns.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_ivldReturns.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the reason for failure of this ivld returns.
	*
	* @param reasonForFailure the reason for failure of this ivld returns
	*/
	@Override
	public void setReasonForFailure(java.lang.String reasonForFailure) {
		_ivldReturns.setReasonForFailure(reasonForFailure);
	}

	/**
	* Sets the reprocessed flag of this ivld returns.
	*
	* @param reprocessedFlag the reprocessed flag of this ivld returns
	*/
	@Override
	public void setReprocessedFlag(java.lang.String reprocessedFlag) {
		_ivldReturns.setReprocessedFlag(reprocessedFlag);
	}

	/**
	* Sets the return complete of this ivld returns.
	*
	* @param returnComplete the return complete of this ivld returns
	*/
	@Override
	public void setReturnComplete(java.lang.String returnComplete) {
		_ivldReturns.setReturnComplete(returnComplete);
	}

	/**
	* Sets the rreserve ID of this ivld returns.
	*
	* @param rreserveId the rreserve ID of this ivld returns
	*/
	@Override
	public void setRreserveId(java.lang.String rreserveId) {
		_ivldReturns.setRreserveId(rreserveId);
	}

	/**
	* Sets the rreserve interface ID of this ivld returns.
	*
	* @param rreserveInterfaceId the rreserve interface ID of this ivld returns
	*/
	@Override
	public void setRreserveInterfaceId(int rreserveInterfaceId) {
		_ivldReturns.setRreserveInterfaceId(rreserveInterfaceId);
	}

	/**
	* Sets the sku of this ivld returns.
	*
	* @param sku the sku of this ivld returns
	*/
	@Override
	public void setSku(java.lang.String sku) {
		_ivldReturns.setSku(sku);
	}

	/**
	* Sets the source of this ivld returns.
	*
	* @param source the source of this ivld returns
	*/
	@Override
	public void setSource(java.lang.String source) {
		_ivldReturns.setSource(source);
	}

	/**
	* Sets the upper limit of this ivld returns.
	*
	* @param upperLimit the upper limit of this ivld returns
	*/
	@Override
	public void setUpperLimit(java.lang.String upperLimit) {
		_ivldReturns.setUpperLimit(upperLimit);
	}

	/**
	* Sets the value at orig asp of this ivld returns.
	*
	* @param valueAtOrigAsp the value at orig asp of this ivld returns
	*/
	@Override
	public void setValueAtOrigAsp(java.lang.String valueAtOrigAsp) {
		_ivldReturns.setValueAtOrigAsp(valueAtOrigAsp);
	}

	/**
	* Sets the version of this ivld returns.
	*
	* @param version the version of this ivld returns
	*/
	@Override
	public void setVersion(java.lang.String version) {
		_ivldReturns.setVersion(version);
	}

	/**
	* Sets the weighted avg months of this ivld returns.
	*
	* @param weightedAvgMonths the weighted avg months of this ivld returns
	*/
	@Override
	public void setWeightedAvgMonths(java.lang.String weightedAvgMonths) {
		_ivldReturns.setWeightedAvgMonths(weightedAvgMonths);
	}

	/**
	* Sets the within50qrtile of this ivld returns.
	*
	* @param within50qrtile the within50qrtile of this ivld returns
	*/
	@Override
	public void setWithin50qrtile(java.lang.String within50qrtile) {
		_ivldReturns.setWithin50qrtile(within50qrtile);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<IvldReturns> toCacheModel() {
		return _ivldReturns.toCacheModel();
	}

	@Override
	public IvldReturns toEscapedModel() {
		return new IvldReturnsWrapper(_ivldReturns.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _ivldReturns.toString();
	}

	@Override
	public IvldReturns toUnescapedModel() {
		return new IvldReturnsWrapper(_ivldReturns.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _ivldReturns.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof IvldReturnsWrapper)) {
			return false;
		}

		IvldReturnsWrapper ivldReturnsWrapper = (IvldReturnsWrapper)obj;

		if (Objects.equals(_ivldReturns, ivldReturnsWrapper._ivldReturns)) {
			return true;
		}

		return false;
	}

	@Override
	public IvldReturns getWrappedModel() {
		return _ivldReturns;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _ivldReturns.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _ivldReturns.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_ivldReturns.resetOriginalValues();
	}

	private final IvldReturns _ivldReturns;
}