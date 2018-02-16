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
 * This class is a wrapper for {@link ReturnsMaster}.
 * </p>
 *
 * @author
 * @see ReturnsMaster
 * @generated
 */
@ProviderType
public class ReturnsMasterWrapper implements ReturnsMaster,
	ModelWrapper<ReturnsMaster> {
	public ReturnsMasterWrapper(ReturnsMaster returnsMaster) {
		_returnsMaster = returnsMaster;
	}

	@Override
	public Class<?> getModelClass() {
		return ReturnsMaster.class;
	}

	@Override
	public String getModelClassName() {
		return ReturnsMaster.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("adjValueAtOrigAsp", getAdjValueAtOrigAsp());
		attributes.put("itemMasterSid", getItemMasterSid());
		attributes.put("firstReturn", getFirstReturn());
		attributes.put("asp", getAsp());
		attributes.put("maxExpiredMonthPlusCutoff",
			getMaxExpiredMonthPlusCutoff());
		attributes.put("posEstimatedReturnUnits", getPosEstimatedReturnUnits());
		attributes.put("origSaleMonthCutOff", getOrigSaleMonthCutOff());
		attributes.put("calcUsed", getCalcUsed());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("brandMasterSid", getBrandMasterSid());
		attributes.put("lastReturn", getLastReturn());
		attributes.put("expectedReturnUnits", getExpectedReturnUnits());
		attributes.put("createdDate", getCreatedDate());
		attributes.put("createdBy", getCreatedBy());
		attributes.put("source", getSource());
		attributes.put("version", getVersion());
		attributes.put("addChgDelIndicator", getAddChgDelIndicator());
		attributes.put("returnsMasterSid", getReturnsMasterSid());
		attributes.put("weightedAvgMonths", getWeightedAvgMonths());
		attributes.put("modifiedBy", getModifiedBy());
		attributes.put("inboundStatus", getInboundStatus());
		attributes.put("pct25th", getPct25th());
		attributes.put("loadDate", getLoadDate());
		attributes.put("maxExpiredMonth", getMaxExpiredMonth());
		attributes.put("actualReturnRate", getActualReturnRate());
		attributes.put("rreserveId", getRreserveId());
		attributes.put("returnComplete", getReturnComplete());
		attributes.put("expectedReturnRate", getExpectedReturnRate());
		attributes.put("pct50th", getPct50th());
		attributes.put("within50qrtile", getWithin50qrtile());
		attributes.put("cumReturnUnits", getCumReturnUnits());
		attributes.put("origSaleMonth", getOrigSaleMonth());
		attributes.put("description", getDescription());
		attributes.put("sku", getSku());
		attributes.put("upperLimit", getUpperLimit());
		attributes.put("lowerLimit", getLowerLimit());
		attributes.put("valueAtOrigAsp", getValueAtOrigAsp());
		attributes.put("adjEstimatedReturnUnits", getAdjEstimatedReturnUnits());
		attributes.put("pct75th", getPct75th());
		attributes.put("recordLockStatus", getRecordLockStatus());
		attributes.put("pastExpiration", getPastExpiration());
		attributes.put("batchId", getBatchId());
		attributes.put("maximum", getMaximum());
		attributes.put("origSaleUnits", getOrigSaleUnits());
		attributes.put("brand", getBrand());
		attributes.put("origSaleDollars", getOrigSaleDollars());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String adjValueAtOrigAsp = (String)attributes.get("adjValueAtOrigAsp");

		if (adjValueAtOrigAsp != null) {
			setAdjValueAtOrigAsp(adjValueAtOrigAsp);
		}

		Integer itemMasterSid = (Integer)attributes.get("itemMasterSid");

		if (itemMasterSid != null) {
			setItemMasterSid(itemMasterSid);
		}

		Date firstReturn = (Date)attributes.get("firstReturn");

		if (firstReturn != null) {
			setFirstReturn(firstReturn);
		}

		String asp = (String)attributes.get("asp");

		if (asp != null) {
			setAsp(asp);
		}

		Date maxExpiredMonthPlusCutoff = (Date)attributes.get(
				"maxExpiredMonthPlusCutoff");

		if (maxExpiredMonthPlusCutoff != null) {
			setMaxExpiredMonthPlusCutoff(maxExpiredMonthPlusCutoff);
		}

		String posEstimatedReturnUnits = (String)attributes.get(
				"posEstimatedReturnUnits");

		if (posEstimatedReturnUnits != null) {
			setPosEstimatedReturnUnits(posEstimatedReturnUnits);
		}

		Date origSaleMonthCutOff = (Date)attributes.get("origSaleMonthCutOff");

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

		Integer brandMasterSid = (Integer)attributes.get("brandMasterSid");

		if (brandMasterSid != null) {
			setBrandMasterSid(brandMasterSid);
		}

		Date lastReturn = (Date)attributes.get("lastReturn");

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

		Integer returnsMasterSid = (Integer)attributes.get("returnsMasterSid");

		if (returnsMasterSid != null) {
			setReturnsMasterSid(returnsMasterSid);
		}

		String weightedAvgMonths = (String)attributes.get("weightedAvgMonths");

		if (weightedAvgMonths != null) {
			setWeightedAvgMonths(weightedAvgMonths);
		}

		String modifiedBy = (String)attributes.get("modifiedBy");

		if (modifiedBy != null) {
			setModifiedBy(modifiedBy);
		}

		String inboundStatus = (String)attributes.get("inboundStatus");

		if (inboundStatus != null) {
			setInboundStatus(inboundStatus);
		}

		String pct25th = (String)attributes.get("pct25th");

		if (pct25th != null) {
			setPct25th(pct25th);
		}

		Date loadDate = (Date)attributes.get("loadDate");

		if (loadDate != null) {
			setLoadDate(loadDate);
		}

		Date maxExpiredMonth = (Date)attributes.get("maxExpiredMonth");

		if (maxExpiredMonth != null) {
			setMaxExpiredMonth(maxExpiredMonth);
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

		String cumReturnUnits = (String)attributes.get("cumReturnUnits");

		if (cumReturnUnits != null) {
			setCumReturnUnits(cumReturnUnits);
		}

		Date origSaleMonth = (Date)attributes.get("origSaleMonth");

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

		Boolean recordLockStatus = (Boolean)attributes.get("recordLockStatus");

		if (recordLockStatus != null) {
			setRecordLockStatus(recordLockStatus);
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

		String brand = (String)attributes.get("brand");

		if (brand != null) {
			setBrand(brand);
		}

		String origSaleDollars = (String)attributes.get("origSaleDollars");

		if (origSaleDollars != null) {
			setOrigSaleDollars(origSaleDollars);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new ReturnsMasterWrapper((ReturnsMaster)_returnsMaster.clone());
	}

	@Override
	public int compareTo(ReturnsMaster returnsMaster) {
		return _returnsMaster.compareTo(returnsMaster);
	}

	/**
	* Returns the actual return rate of this returns master.
	*
	* @return the actual return rate of this returns master
	*/
	@Override
	public java.lang.String getActualReturnRate() {
		return _returnsMaster.getActualReturnRate();
	}

	/**
	* Returns the add chg del indicator of this returns master.
	*
	* @return the add chg del indicator of this returns master
	*/
	@Override
	public java.lang.String getAddChgDelIndicator() {
		return _returnsMaster.getAddChgDelIndicator();
	}

	/**
	* Returns the adj estimated return units of this returns master.
	*
	* @return the adj estimated return units of this returns master
	*/
	@Override
	public java.lang.String getAdjEstimatedReturnUnits() {
		return _returnsMaster.getAdjEstimatedReturnUnits();
	}

	/**
	* Returns the adj value at orig asp of this returns master.
	*
	* @return the adj value at orig asp of this returns master
	*/
	@Override
	public java.lang.String getAdjValueAtOrigAsp() {
		return _returnsMaster.getAdjValueAtOrigAsp();
	}

	/**
	* Returns the asp of this returns master.
	*
	* @return the asp of this returns master
	*/
	@Override
	public java.lang.String getAsp() {
		return _returnsMaster.getAsp();
	}

	/**
	* Returns the batch ID of this returns master.
	*
	* @return the batch ID of this returns master
	*/
	@Override
	public java.lang.String getBatchId() {
		return _returnsMaster.getBatchId();
	}

	/**
	* Returns the brand of this returns master.
	*
	* @return the brand of this returns master
	*/
	@Override
	public java.lang.String getBrand() {
		return _returnsMaster.getBrand();
	}

	/**
	* Returns the brand master sid of this returns master.
	*
	* @return the brand master sid of this returns master
	*/
	@Override
	public int getBrandMasterSid() {
		return _returnsMaster.getBrandMasterSid();
	}

	/**
	* Returns the calc used of this returns master.
	*
	* @return the calc used of this returns master
	*/
	@Override
	public java.lang.String getCalcUsed() {
		return _returnsMaster.getCalcUsed();
	}

	/**
	* Returns the created by of this returns master.
	*
	* @return the created by of this returns master
	*/
	@Override
	public java.lang.String getCreatedBy() {
		return _returnsMaster.getCreatedBy();
	}

	/**
	* Returns the created date of this returns master.
	*
	* @return the created date of this returns master
	*/
	@Override
	public Date getCreatedDate() {
		return _returnsMaster.getCreatedDate();
	}

	/**
	* Returns the cum return units of this returns master.
	*
	* @return the cum return units of this returns master
	*/
	@Override
	public java.lang.String getCumReturnUnits() {
		return _returnsMaster.getCumReturnUnits();
	}

	/**
	* Returns the description of this returns master.
	*
	* @return the description of this returns master
	*/
	@Override
	public java.lang.String getDescription() {
		return _returnsMaster.getDescription();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _returnsMaster.getExpandoBridge();
	}

	/**
	* Returns the expected return rate of this returns master.
	*
	* @return the expected return rate of this returns master
	*/
	@Override
	public java.lang.String getExpectedReturnRate() {
		return _returnsMaster.getExpectedReturnRate();
	}

	/**
	* Returns the expected return units of this returns master.
	*
	* @return the expected return units of this returns master
	*/
	@Override
	public java.lang.String getExpectedReturnUnits() {
		return _returnsMaster.getExpectedReturnUnits();
	}

	/**
	* Returns the first return of this returns master.
	*
	* @return the first return of this returns master
	*/
	@Override
	public Date getFirstReturn() {
		return _returnsMaster.getFirstReturn();
	}

	/**
	* Returns the inbound status of this returns master.
	*
	* @return the inbound status of this returns master
	*/
	@Override
	public java.lang.String getInboundStatus() {
		return _returnsMaster.getInboundStatus();
	}

	/**
	* Returns the item master sid of this returns master.
	*
	* @return the item master sid of this returns master
	*/
	@Override
	public int getItemMasterSid() {
		return _returnsMaster.getItemMasterSid();
	}

	/**
	* Returns the last return of this returns master.
	*
	* @return the last return of this returns master
	*/
	@Override
	public Date getLastReturn() {
		return _returnsMaster.getLastReturn();
	}

	/**
	* Returns the load date of this returns master.
	*
	* @return the load date of this returns master
	*/
	@Override
	public Date getLoadDate() {
		return _returnsMaster.getLoadDate();
	}

	/**
	* Returns the lower limit of this returns master.
	*
	* @return the lower limit of this returns master
	*/
	@Override
	public java.lang.String getLowerLimit() {
		return _returnsMaster.getLowerLimit();
	}

	/**
	* Returns the max expired month of this returns master.
	*
	* @return the max expired month of this returns master
	*/
	@Override
	public Date getMaxExpiredMonth() {
		return _returnsMaster.getMaxExpiredMonth();
	}

	/**
	* Returns the max expired month plus cutoff of this returns master.
	*
	* @return the max expired month plus cutoff of this returns master
	*/
	@Override
	public Date getMaxExpiredMonthPlusCutoff() {
		return _returnsMaster.getMaxExpiredMonthPlusCutoff();
	}

	/**
	* Returns the maximum of this returns master.
	*
	* @return the maximum of this returns master
	*/
	@Override
	public java.lang.String getMaximum() {
		return _returnsMaster.getMaximum();
	}

	/**
	* Returns the modified by of this returns master.
	*
	* @return the modified by of this returns master
	*/
	@Override
	public java.lang.String getModifiedBy() {
		return _returnsMaster.getModifiedBy();
	}

	/**
	* Returns the modified date of this returns master.
	*
	* @return the modified date of this returns master
	*/
	@Override
	public Date getModifiedDate() {
		return _returnsMaster.getModifiedDate();
	}

	/**
	* Returns the orig sale dollars of this returns master.
	*
	* @return the orig sale dollars of this returns master
	*/
	@Override
	public java.lang.String getOrigSaleDollars() {
		return _returnsMaster.getOrigSaleDollars();
	}

	/**
	* Returns the orig sale month of this returns master.
	*
	* @return the orig sale month of this returns master
	*/
	@Override
	public Date getOrigSaleMonth() {
		return _returnsMaster.getOrigSaleMonth();
	}

	/**
	* Returns the orig sale month cut off of this returns master.
	*
	* @return the orig sale month cut off of this returns master
	*/
	@Override
	public Date getOrigSaleMonthCutOff() {
		return _returnsMaster.getOrigSaleMonthCutOff();
	}

	/**
	* Returns the orig sale units of this returns master.
	*
	* @return the orig sale units of this returns master
	*/
	@Override
	public java.lang.String getOrigSaleUnits() {
		return _returnsMaster.getOrigSaleUnits();
	}

	/**
	* Returns the past expiration of this returns master.
	*
	* @return the past expiration of this returns master
	*/
	@Override
	public java.lang.String getPastExpiration() {
		return _returnsMaster.getPastExpiration();
	}

	/**
	* Returns the pct25th of this returns master.
	*
	* @return the pct25th of this returns master
	*/
	@Override
	public java.lang.String getPct25th() {
		return _returnsMaster.getPct25th();
	}

	/**
	* Returns the pct50th of this returns master.
	*
	* @return the pct50th of this returns master
	*/
	@Override
	public java.lang.String getPct50th() {
		return _returnsMaster.getPct50th();
	}

	/**
	* Returns the pct75th of this returns master.
	*
	* @return the pct75th of this returns master
	*/
	@Override
	public java.lang.String getPct75th() {
		return _returnsMaster.getPct75th();
	}

	/**
	* Returns the pos estimated return units of this returns master.
	*
	* @return the pos estimated return units of this returns master
	*/
	@Override
	public java.lang.String getPosEstimatedReturnUnits() {
		return _returnsMaster.getPosEstimatedReturnUnits();
	}

	/**
	* Returns the primary key of this returns master.
	*
	* @return the primary key of this returns master
	*/
	@Override
	public int getPrimaryKey() {
		return _returnsMaster.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _returnsMaster.getPrimaryKeyObj();
	}

	/**
	* Returns the record lock status of this returns master.
	*
	* @return the record lock status of this returns master
	*/
	@Override
	public boolean getRecordLockStatus() {
		return _returnsMaster.getRecordLockStatus();
	}

	/**
	* Returns the return complete of this returns master.
	*
	* @return the return complete of this returns master
	*/
	@Override
	public java.lang.String getReturnComplete() {
		return _returnsMaster.getReturnComplete();
	}

	/**
	* Returns the returns master sid of this returns master.
	*
	* @return the returns master sid of this returns master
	*/
	@Override
	public int getReturnsMasterSid() {
		return _returnsMaster.getReturnsMasterSid();
	}

	/**
	* Returns the rreserve ID of this returns master.
	*
	* @return the rreserve ID of this returns master
	*/
	@Override
	public java.lang.String getRreserveId() {
		return _returnsMaster.getRreserveId();
	}

	/**
	* Returns the sku of this returns master.
	*
	* @return the sku of this returns master
	*/
	@Override
	public java.lang.String getSku() {
		return _returnsMaster.getSku();
	}

	/**
	* Returns the source of this returns master.
	*
	* @return the source of this returns master
	*/
	@Override
	public java.lang.String getSource() {
		return _returnsMaster.getSource();
	}

	/**
	* Returns the upper limit of this returns master.
	*
	* @return the upper limit of this returns master
	*/
	@Override
	public java.lang.String getUpperLimit() {
		return _returnsMaster.getUpperLimit();
	}

	/**
	* Returns the value at orig asp of this returns master.
	*
	* @return the value at orig asp of this returns master
	*/
	@Override
	public java.lang.String getValueAtOrigAsp() {
		return _returnsMaster.getValueAtOrigAsp();
	}

	/**
	* Returns the version of this returns master.
	*
	* @return the version of this returns master
	*/
	@Override
	public java.lang.String getVersion() {
		return _returnsMaster.getVersion();
	}

	/**
	* Returns the weighted avg months of this returns master.
	*
	* @return the weighted avg months of this returns master
	*/
	@Override
	public java.lang.String getWeightedAvgMonths() {
		return _returnsMaster.getWeightedAvgMonths();
	}

	/**
	* Returns the within50qrtile of this returns master.
	*
	* @return the within50qrtile of this returns master
	*/
	@Override
	public java.lang.String getWithin50qrtile() {
		return _returnsMaster.getWithin50qrtile();
	}

	@Override
	public int hashCode() {
		return _returnsMaster.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _returnsMaster.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _returnsMaster.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _returnsMaster.isNew();
	}

	/**
	* Returns <code>true</code> if this returns master is record lock status.
	*
	* @return <code>true</code> if this returns master is record lock status; <code>false</code> otherwise
	*/
	@Override
	public boolean isRecordLockStatus() {
		return _returnsMaster.isRecordLockStatus();
	}

	@Override
	public void persist() {
		_returnsMaster.persist();
	}

	/**
	* Sets the actual return rate of this returns master.
	*
	* @param actualReturnRate the actual return rate of this returns master
	*/
	@Override
	public void setActualReturnRate(java.lang.String actualReturnRate) {
		_returnsMaster.setActualReturnRate(actualReturnRate);
	}

	/**
	* Sets the add chg del indicator of this returns master.
	*
	* @param addChgDelIndicator the add chg del indicator of this returns master
	*/
	@Override
	public void setAddChgDelIndicator(java.lang.String addChgDelIndicator) {
		_returnsMaster.setAddChgDelIndicator(addChgDelIndicator);
	}

	/**
	* Sets the adj estimated return units of this returns master.
	*
	* @param adjEstimatedReturnUnits the adj estimated return units of this returns master
	*/
	@Override
	public void setAdjEstimatedReturnUnits(
		java.lang.String adjEstimatedReturnUnits) {
		_returnsMaster.setAdjEstimatedReturnUnits(adjEstimatedReturnUnits);
	}

	/**
	* Sets the adj value at orig asp of this returns master.
	*
	* @param adjValueAtOrigAsp the adj value at orig asp of this returns master
	*/
	@Override
	public void setAdjValueAtOrigAsp(java.lang.String adjValueAtOrigAsp) {
		_returnsMaster.setAdjValueAtOrigAsp(adjValueAtOrigAsp);
	}

	/**
	* Sets the asp of this returns master.
	*
	* @param asp the asp of this returns master
	*/
	@Override
	public void setAsp(java.lang.String asp) {
		_returnsMaster.setAsp(asp);
	}

	/**
	* Sets the batch ID of this returns master.
	*
	* @param batchId the batch ID of this returns master
	*/
	@Override
	public void setBatchId(java.lang.String batchId) {
		_returnsMaster.setBatchId(batchId);
	}

	/**
	* Sets the brand of this returns master.
	*
	* @param brand the brand of this returns master
	*/
	@Override
	public void setBrand(java.lang.String brand) {
		_returnsMaster.setBrand(brand);
	}

	/**
	* Sets the brand master sid of this returns master.
	*
	* @param brandMasterSid the brand master sid of this returns master
	*/
	@Override
	public void setBrandMasterSid(int brandMasterSid) {
		_returnsMaster.setBrandMasterSid(brandMasterSid);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_returnsMaster.setCachedModel(cachedModel);
	}

	/**
	* Sets the calc used of this returns master.
	*
	* @param calcUsed the calc used of this returns master
	*/
	@Override
	public void setCalcUsed(java.lang.String calcUsed) {
		_returnsMaster.setCalcUsed(calcUsed);
	}

	/**
	* Sets the created by of this returns master.
	*
	* @param createdBy the created by of this returns master
	*/
	@Override
	public void setCreatedBy(java.lang.String createdBy) {
		_returnsMaster.setCreatedBy(createdBy);
	}

	/**
	* Sets the created date of this returns master.
	*
	* @param createdDate the created date of this returns master
	*/
	@Override
	public void setCreatedDate(Date createdDate) {
		_returnsMaster.setCreatedDate(createdDate);
	}

	/**
	* Sets the cum return units of this returns master.
	*
	* @param cumReturnUnits the cum return units of this returns master
	*/
	@Override
	public void setCumReturnUnits(java.lang.String cumReturnUnits) {
		_returnsMaster.setCumReturnUnits(cumReturnUnits);
	}

	/**
	* Sets the description of this returns master.
	*
	* @param description the description of this returns master
	*/
	@Override
	public void setDescription(java.lang.String description) {
		_returnsMaster.setDescription(description);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_returnsMaster.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_returnsMaster.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_returnsMaster.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the expected return rate of this returns master.
	*
	* @param expectedReturnRate the expected return rate of this returns master
	*/
	@Override
	public void setExpectedReturnRate(java.lang.String expectedReturnRate) {
		_returnsMaster.setExpectedReturnRate(expectedReturnRate);
	}

	/**
	* Sets the expected return units of this returns master.
	*
	* @param expectedReturnUnits the expected return units of this returns master
	*/
	@Override
	public void setExpectedReturnUnits(java.lang.String expectedReturnUnits) {
		_returnsMaster.setExpectedReturnUnits(expectedReturnUnits);
	}

	/**
	* Sets the first return of this returns master.
	*
	* @param firstReturn the first return of this returns master
	*/
	@Override
	public void setFirstReturn(Date firstReturn) {
		_returnsMaster.setFirstReturn(firstReturn);
	}

	/**
	* Sets the inbound status of this returns master.
	*
	* @param inboundStatus the inbound status of this returns master
	*/
	@Override
	public void setInboundStatus(java.lang.String inboundStatus) {
		_returnsMaster.setInboundStatus(inboundStatus);
	}

	/**
	* Sets the item master sid of this returns master.
	*
	* @param itemMasterSid the item master sid of this returns master
	*/
	@Override
	public void setItemMasterSid(int itemMasterSid) {
		_returnsMaster.setItemMasterSid(itemMasterSid);
	}

	/**
	* Sets the last return of this returns master.
	*
	* @param lastReturn the last return of this returns master
	*/
	@Override
	public void setLastReturn(Date lastReturn) {
		_returnsMaster.setLastReturn(lastReturn);
	}

	/**
	* Sets the load date of this returns master.
	*
	* @param loadDate the load date of this returns master
	*/
	@Override
	public void setLoadDate(Date loadDate) {
		_returnsMaster.setLoadDate(loadDate);
	}

	/**
	* Sets the lower limit of this returns master.
	*
	* @param lowerLimit the lower limit of this returns master
	*/
	@Override
	public void setLowerLimit(java.lang.String lowerLimit) {
		_returnsMaster.setLowerLimit(lowerLimit);
	}

	/**
	* Sets the max expired month of this returns master.
	*
	* @param maxExpiredMonth the max expired month of this returns master
	*/
	@Override
	public void setMaxExpiredMonth(Date maxExpiredMonth) {
		_returnsMaster.setMaxExpiredMonth(maxExpiredMonth);
	}

	/**
	* Sets the max expired month plus cutoff of this returns master.
	*
	* @param maxExpiredMonthPlusCutoff the max expired month plus cutoff of this returns master
	*/
	@Override
	public void setMaxExpiredMonthPlusCutoff(Date maxExpiredMonthPlusCutoff) {
		_returnsMaster.setMaxExpiredMonthPlusCutoff(maxExpiredMonthPlusCutoff);
	}

	/**
	* Sets the maximum of this returns master.
	*
	* @param maximum the maximum of this returns master
	*/
	@Override
	public void setMaximum(java.lang.String maximum) {
		_returnsMaster.setMaximum(maximum);
	}

	/**
	* Sets the modified by of this returns master.
	*
	* @param modifiedBy the modified by of this returns master
	*/
	@Override
	public void setModifiedBy(java.lang.String modifiedBy) {
		_returnsMaster.setModifiedBy(modifiedBy);
	}

	/**
	* Sets the modified date of this returns master.
	*
	* @param modifiedDate the modified date of this returns master
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_returnsMaster.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_returnsMaster.setNew(n);
	}

	/**
	* Sets the orig sale dollars of this returns master.
	*
	* @param origSaleDollars the orig sale dollars of this returns master
	*/
	@Override
	public void setOrigSaleDollars(java.lang.String origSaleDollars) {
		_returnsMaster.setOrigSaleDollars(origSaleDollars);
	}

	/**
	* Sets the orig sale month of this returns master.
	*
	* @param origSaleMonth the orig sale month of this returns master
	*/
	@Override
	public void setOrigSaleMonth(Date origSaleMonth) {
		_returnsMaster.setOrigSaleMonth(origSaleMonth);
	}

	/**
	* Sets the orig sale month cut off of this returns master.
	*
	* @param origSaleMonthCutOff the orig sale month cut off of this returns master
	*/
	@Override
	public void setOrigSaleMonthCutOff(Date origSaleMonthCutOff) {
		_returnsMaster.setOrigSaleMonthCutOff(origSaleMonthCutOff);
	}

	/**
	* Sets the orig sale units of this returns master.
	*
	* @param origSaleUnits the orig sale units of this returns master
	*/
	@Override
	public void setOrigSaleUnits(java.lang.String origSaleUnits) {
		_returnsMaster.setOrigSaleUnits(origSaleUnits);
	}

	/**
	* Sets the past expiration of this returns master.
	*
	* @param pastExpiration the past expiration of this returns master
	*/
	@Override
	public void setPastExpiration(java.lang.String pastExpiration) {
		_returnsMaster.setPastExpiration(pastExpiration);
	}

	/**
	* Sets the pct25th of this returns master.
	*
	* @param pct25th the pct25th of this returns master
	*/
	@Override
	public void setPct25th(java.lang.String pct25th) {
		_returnsMaster.setPct25th(pct25th);
	}

	/**
	* Sets the pct50th of this returns master.
	*
	* @param pct50th the pct50th of this returns master
	*/
	@Override
	public void setPct50th(java.lang.String pct50th) {
		_returnsMaster.setPct50th(pct50th);
	}

	/**
	* Sets the pct75th of this returns master.
	*
	* @param pct75th the pct75th of this returns master
	*/
	@Override
	public void setPct75th(java.lang.String pct75th) {
		_returnsMaster.setPct75th(pct75th);
	}

	/**
	* Sets the pos estimated return units of this returns master.
	*
	* @param posEstimatedReturnUnits the pos estimated return units of this returns master
	*/
	@Override
	public void setPosEstimatedReturnUnits(
		java.lang.String posEstimatedReturnUnits) {
		_returnsMaster.setPosEstimatedReturnUnits(posEstimatedReturnUnits);
	}

	/**
	* Sets the primary key of this returns master.
	*
	* @param primaryKey the primary key of this returns master
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_returnsMaster.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_returnsMaster.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets whether this returns master is record lock status.
	*
	* @param recordLockStatus the record lock status of this returns master
	*/
	@Override
	public void setRecordLockStatus(boolean recordLockStatus) {
		_returnsMaster.setRecordLockStatus(recordLockStatus);
	}

	/**
	* Sets the return complete of this returns master.
	*
	* @param returnComplete the return complete of this returns master
	*/
	@Override
	public void setReturnComplete(java.lang.String returnComplete) {
		_returnsMaster.setReturnComplete(returnComplete);
	}

	/**
	* Sets the returns master sid of this returns master.
	*
	* @param returnsMasterSid the returns master sid of this returns master
	*/
	@Override
	public void setReturnsMasterSid(int returnsMasterSid) {
		_returnsMaster.setReturnsMasterSid(returnsMasterSid);
	}

	/**
	* Sets the rreserve ID of this returns master.
	*
	* @param rreserveId the rreserve ID of this returns master
	*/
	@Override
	public void setRreserveId(java.lang.String rreserveId) {
		_returnsMaster.setRreserveId(rreserveId);
	}

	/**
	* Sets the sku of this returns master.
	*
	* @param sku the sku of this returns master
	*/
	@Override
	public void setSku(java.lang.String sku) {
		_returnsMaster.setSku(sku);
	}

	/**
	* Sets the source of this returns master.
	*
	* @param source the source of this returns master
	*/
	@Override
	public void setSource(java.lang.String source) {
		_returnsMaster.setSource(source);
	}

	/**
	* Sets the upper limit of this returns master.
	*
	* @param upperLimit the upper limit of this returns master
	*/
	@Override
	public void setUpperLimit(java.lang.String upperLimit) {
		_returnsMaster.setUpperLimit(upperLimit);
	}

	/**
	* Sets the value at orig asp of this returns master.
	*
	* @param valueAtOrigAsp the value at orig asp of this returns master
	*/
	@Override
	public void setValueAtOrigAsp(java.lang.String valueAtOrigAsp) {
		_returnsMaster.setValueAtOrigAsp(valueAtOrigAsp);
	}

	/**
	* Sets the version of this returns master.
	*
	* @param version the version of this returns master
	*/
	@Override
	public void setVersion(java.lang.String version) {
		_returnsMaster.setVersion(version);
	}

	/**
	* Sets the weighted avg months of this returns master.
	*
	* @param weightedAvgMonths the weighted avg months of this returns master
	*/
	@Override
	public void setWeightedAvgMonths(java.lang.String weightedAvgMonths) {
		_returnsMaster.setWeightedAvgMonths(weightedAvgMonths);
	}

	/**
	* Sets the within50qrtile of this returns master.
	*
	* @param within50qrtile the within50qrtile of this returns master
	*/
	@Override
	public void setWithin50qrtile(java.lang.String within50qrtile) {
		_returnsMaster.setWithin50qrtile(within50qrtile);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<ReturnsMaster> toCacheModel() {
		return _returnsMaster.toCacheModel();
	}

	@Override
	public ReturnsMaster toEscapedModel() {
		return new ReturnsMasterWrapper(_returnsMaster.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _returnsMaster.toString();
	}

	@Override
	public ReturnsMaster toUnescapedModel() {
		return new ReturnsMasterWrapper(_returnsMaster.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _returnsMaster.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ReturnsMasterWrapper)) {
			return false;
		}

		ReturnsMasterWrapper returnsMasterWrapper = (ReturnsMasterWrapper)obj;

		if (Objects.equals(_returnsMaster, returnsMasterWrapper._returnsMaster)) {
			return true;
		}

		return false;
	}

	@Override
	public ReturnsMaster getWrappedModel() {
		return _returnsMaster;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _returnsMaster.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _returnsMaster.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_returnsMaster.resetOriginalValues();
	}

	private final ReturnsMaster _returnsMaster;
}