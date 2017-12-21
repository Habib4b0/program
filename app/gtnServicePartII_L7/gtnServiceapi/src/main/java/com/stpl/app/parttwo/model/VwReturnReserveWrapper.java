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

package com.stpl.app.parttwo.model;

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
 * This class is a wrapper for {@link VwReturnReserve}.
 * </p>
 *
 * @author
 * @see VwReturnReserve
 * @generated
 */
@ProviderType
public class VwReturnReserveWrapper implements VwReturnReserve,
	ModelWrapper<VwReturnReserve> {
	public VwReturnReserveWrapper(VwReturnReserve vwReturnReserve) {
		_vwReturnReserve = vwReturnReserve;
	}

	@Override
	public Class<?> getModelClass() {
		return VwReturnReserve.class;
	}

	@Override
	public String getModelClassName() {
		return VwReturnReserve.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("itemMasterSid", getItemMasterSid());
		attributes.put("companyName", getCompanyName());
		attributes.put("project", getProject());
		attributes.put("year", getYear());
		attributes.put("itemId", getItemId());
		attributes.put("brandName", getBrandName());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("brandMasterSid", getBrandMasterSid());
		attributes.put("account", getAccount());
		attributes.put("returnReserveIntfId", getReturnReserveIntfId());
		attributes.put("glCompanyMasterSid", getGlCompanyMasterSid());
		attributes.put("source", getSource());
		attributes.put("createdDate", getCreatedDate());
		attributes.put("createdBy", getCreatedBy());
		attributes.put("businessUnit", getBusinessUnit());
		attributes.put("businessUnitName", getBusinessUnitName());
		attributes.put("buCompanyMasterSid", getBuCompanyMasterSid());
		attributes.put("inboundStatus", getInboundStatus());
		attributes.put("modifiedBy", getModifiedBy());
		attributes.put("itemNo", getItemNo());
		attributes.put("month", getMonth());
		attributes.put("udc6", getUdc6());
		attributes.put("udc5", getUdc5());
		attributes.put("udc4", getUdc4());
		attributes.put("udc1", getUdc1());
		attributes.put("units", getUnits());
		attributes.put("udc2", getUdc2());
		attributes.put("udc3", getUdc3());
		attributes.put("country", getCountry());
		attributes.put("companyId", getCompanyId());
		attributes.put("costCenter", getCostCenter());
		attributes.put("glCompany", getGlCompany());
		attributes.put("brandId", getBrandId());
		attributes.put("future1", getFuture1());
		attributes.put("future2", getFuture2());
		attributes.put("amount", getAmount());
		attributes.put("recordLockStatus", getRecordLockStatus());
		attributes.put("division", getDivision());
		attributes.put("returnReserveSid", getReturnReserveSid());
		attributes.put("companyNo", getCompanyNo());
		attributes.put("batchId", getBatchId());
		attributes.put("itemName", getItemName());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Integer itemMasterSid = (Integer)attributes.get("itemMasterSid");

		if (itemMasterSid != null) {
			setItemMasterSid(itemMasterSid);
		}

		String companyName = (String)attributes.get("companyName");

		if (companyName != null) {
			setCompanyName(companyName);
		}

		String project = (String)attributes.get("project");

		if (project != null) {
			setProject(project);
		}

		String year = (String)attributes.get("year");

		if (year != null) {
			setYear(year);
		}

		String itemId = (String)attributes.get("itemId");

		if (itemId != null) {
			setItemId(itemId);
		}

		String brandName = (String)attributes.get("brandName");

		if (brandName != null) {
			setBrandName(brandName);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Integer brandMasterSid = (Integer)attributes.get("brandMasterSid");

		if (brandMasterSid != null) {
			setBrandMasterSid(brandMasterSid);
		}

		String account = (String)attributes.get("account");

		if (account != null) {
			setAccount(account);
		}

		Integer returnReserveIntfId = (Integer)attributes.get(
				"returnReserveIntfId");

		if (returnReserveIntfId != null) {
			setReturnReserveIntfId(returnReserveIntfId);
		}

		Integer glCompanyMasterSid = (Integer)attributes.get(
				"glCompanyMasterSid");

		if (glCompanyMasterSid != null) {
			setGlCompanyMasterSid(glCompanyMasterSid);
		}

		String source = (String)attributes.get("source");

		if (source != null) {
			setSource(source);
		}

		Date createdDate = (Date)attributes.get("createdDate");

		if (createdDate != null) {
			setCreatedDate(createdDate);
		}

		String createdBy = (String)attributes.get("createdBy");

		if (createdBy != null) {
			setCreatedBy(createdBy);
		}

		String businessUnit = (String)attributes.get("businessUnit");

		if (businessUnit != null) {
			setBusinessUnit(businessUnit);
		}

		String businessUnitName = (String)attributes.get("businessUnitName");

		if (businessUnitName != null) {
			setBusinessUnitName(businessUnitName);
		}

		Integer buCompanyMasterSid = (Integer)attributes.get(
				"buCompanyMasterSid");

		if (buCompanyMasterSid != null) {
			setBuCompanyMasterSid(buCompanyMasterSid);
		}

		String inboundStatus = (String)attributes.get("inboundStatus");

		if (inboundStatus != null) {
			setInboundStatus(inboundStatus);
		}

		String modifiedBy = (String)attributes.get("modifiedBy");

		if (modifiedBy != null) {
			setModifiedBy(modifiedBy);
		}

		String itemNo = (String)attributes.get("itemNo");

		if (itemNo != null) {
			setItemNo(itemNo);
		}

		String month = (String)attributes.get("month");

		if (month != null) {
			setMonth(month);
		}

		String udc6 = (String)attributes.get("udc6");

		if (udc6 != null) {
			setUdc6(udc6);
		}

		String udc5 = (String)attributes.get("udc5");

		if (udc5 != null) {
			setUdc5(udc5);
		}

		String udc4 = (String)attributes.get("udc4");

		if (udc4 != null) {
			setUdc4(udc4);
		}

		String udc1 = (String)attributes.get("udc1");

		if (udc1 != null) {
			setUdc1(udc1);
		}

		String units = (String)attributes.get("units");

		if (units != null) {
			setUnits(units);
		}

		String udc2 = (String)attributes.get("udc2");

		if (udc2 != null) {
			setUdc2(udc2);
		}

		String udc3 = (String)attributes.get("udc3");

		if (udc3 != null) {
			setUdc3(udc3);
		}

		String country = (String)attributes.get("country");

		if (country != null) {
			setCountry(country);
		}

		String companyId = (String)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		String costCenter = (String)attributes.get("costCenter");

		if (costCenter != null) {
			setCostCenter(costCenter);
		}

		String glCompany = (String)attributes.get("glCompany");

		if (glCompany != null) {
			setGlCompany(glCompany);
		}

		String brandId = (String)attributes.get("brandId");

		if (brandId != null) {
			setBrandId(brandId);
		}

		String future1 = (String)attributes.get("future1");

		if (future1 != null) {
			setFuture1(future1);
		}

		String future2 = (String)attributes.get("future2");

		if (future2 != null) {
			setFuture2(future2);
		}

		String amount = (String)attributes.get("amount");

		if (amount != null) {
			setAmount(amount);
		}

		Boolean recordLockStatus = (Boolean)attributes.get("recordLockStatus");

		if (recordLockStatus != null) {
			setRecordLockStatus(recordLockStatus);
		}

		String division = (String)attributes.get("division");

		if (division != null) {
			setDivision(division);
		}

		Integer returnReserveSid = (Integer)attributes.get("returnReserveSid");

		if (returnReserveSid != null) {
			setReturnReserveSid(returnReserveSid);
		}

		String companyNo = (String)attributes.get("companyNo");

		if (companyNo != null) {
			setCompanyNo(companyNo);
		}

		String batchId = (String)attributes.get("batchId");

		if (batchId != null) {
			setBatchId(batchId);
		}

		String itemName = (String)attributes.get("itemName");

		if (itemName != null) {
			setItemName(itemName);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new VwReturnReserveWrapper((VwReturnReserve)_vwReturnReserve.clone());
	}

	@Override
	public int compareTo(VwReturnReserve vwReturnReserve) {
		return _vwReturnReserve.compareTo(vwReturnReserve);
	}

	/**
	* Returns the account of this vw return reserve.
	*
	* @return the account of this vw return reserve
	*/
	@Override
	public java.lang.String getAccount() {
		return _vwReturnReserve.getAccount();
	}

	/**
	* Returns the amount of this vw return reserve.
	*
	* @return the amount of this vw return reserve
	*/
	@Override
	public java.lang.String getAmount() {
		return _vwReturnReserve.getAmount();
	}

	/**
	* Returns the batch ID of this vw return reserve.
	*
	* @return the batch ID of this vw return reserve
	*/
	@Override
	public java.lang.String getBatchId() {
		return _vwReturnReserve.getBatchId();
	}

	/**
	* Returns the brand ID of this vw return reserve.
	*
	* @return the brand ID of this vw return reserve
	*/
	@Override
	public java.lang.String getBrandId() {
		return _vwReturnReserve.getBrandId();
	}

	/**
	* Returns the brand master sid of this vw return reserve.
	*
	* @return the brand master sid of this vw return reserve
	*/
	@Override
	public int getBrandMasterSid() {
		return _vwReturnReserve.getBrandMasterSid();
	}

	/**
	* Returns the brand name of this vw return reserve.
	*
	* @return the brand name of this vw return reserve
	*/
	@Override
	public java.lang.String getBrandName() {
		return _vwReturnReserve.getBrandName();
	}

	/**
	* Returns the bu company master sid of this vw return reserve.
	*
	* @return the bu company master sid of this vw return reserve
	*/
	@Override
	public int getBuCompanyMasterSid() {
		return _vwReturnReserve.getBuCompanyMasterSid();
	}

	/**
	* Returns the business unit of this vw return reserve.
	*
	* @return the business unit of this vw return reserve
	*/
	@Override
	public java.lang.String getBusinessUnit() {
		return _vwReturnReserve.getBusinessUnit();
	}

	/**
	* Returns the business unit name of this vw return reserve.
	*
	* @return the business unit name of this vw return reserve
	*/
	@Override
	public java.lang.String getBusinessUnitName() {
		return _vwReturnReserve.getBusinessUnitName();
	}

	/**
	* Returns the company ID of this vw return reserve.
	*
	* @return the company ID of this vw return reserve
	*/
	@Override
	public java.lang.String getCompanyId() {
		return _vwReturnReserve.getCompanyId();
	}

	/**
	* Returns the company name of this vw return reserve.
	*
	* @return the company name of this vw return reserve
	*/
	@Override
	public java.lang.String getCompanyName() {
		return _vwReturnReserve.getCompanyName();
	}

	/**
	* Returns the company no of this vw return reserve.
	*
	* @return the company no of this vw return reserve
	*/
	@Override
	public java.lang.String getCompanyNo() {
		return _vwReturnReserve.getCompanyNo();
	}

	/**
	* Returns the cost center of this vw return reserve.
	*
	* @return the cost center of this vw return reserve
	*/
	@Override
	public java.lang.String getCostCenter() {
		return _vwReturnReserve.getCostCenter();
	}

	/**
	* Returns the country of this vw return reserve.
	*
	* @return the country of this vw return reserve
	*/
	@Override
	public java.lang.String getCountry() {
		return _vwReturnReserve.getCountry();
	}

	/**
	* Returns the created by of this vw return reserve.
	*
	* @return the created by of this vw return reserve
	*/
	@Override
	public java.lang.String getCreatedBy() {
		return _vwReturnReserve.getCreatedBy();
	}

	/**
	* Returns the created date of this vw return reserve.
	*
	* @return the created date of this vw return reserve
	*/
	@Override
	public Date getCreatedDate() {
		return _vwReturnReserve.getCreatedDate();
	}

	/**
	* Returns the division of this vw return reserve.
	*
	* @return the division of this vw return reserve
	*/
	@Override
	public java.lang.String getDivision() {
		return _vwReturnReserve.getDivision();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _vwReturnReserve.getExpandoBridge();
	}

	/**
	* Returns the future1 of this vw return reserve.
	*
	* @return the future1 of this vw return reserve
	*/
	@Override
	public java.lang.String getFuture1() {
		return _vwReturnReserve.getFuture1();
	}

	/**
	* Returns the future2 of this vw return reserve.
	*
	* @return the future2 of this vw return reserve
	*/
	@Override
	public java.lang.String getFuture2() {
		return _vwReturnReserve.getFuture2();
	}

	/**
	* Returns the gl company of this vw return reserve.
	*
	* @return the gl company of this vw return reserve
	*/
	@Override
	public java.lang.String getGlCompany() {
		return _vwReturnReserve.getGlCompany();
	}

	/**
	* Returns the gl company master sid of this vw return reserve.
	*
	* @return the gl company master sid of this vw return reserve
	*/
	@Override
	public int getGlCompanyMasterSid() {
		return _vwReturnReserve.getGlCompanyMasterSid();
	}

	/**
	* Returns the inbound status of this vw return reserve.
	*
	* @return the inbound status of this vw return reserve
	*/
	@Override
	public java.lang.String getInboundStatus() {
		return _vwReturnReserve.getInboundStatus();
	}

	/**
	* Returns the item ID of this vw return reserve.
	*
	* @return the item ID of this vw return reserve
	*/
	@Override
	public java.lang.String getItemId() {
		return _vwReturnReserve.getItemId();
	}

	/**
	* Returns the item master sid of this vw return reserve.
	*
	* @return the item master sid of this vw return reserve
	*/
	@Override
	public int getItemMasterSid() {
		return _vwReturnReserve.getItemMasterSid();
	}

	/**
	* Returns the item name of this vw return reserve.
	*
	* @return the item name of this vw return reserve
	*/
	@Override
	public java.lang.String getItemName() {
		return _vwReturnReserve.getItemName();
	}

	/**
	* Returns the item no of this vw return reserve.
	*
	* @return the item no of this vw return reserve
	*/
	@Override
	public java.lang.String getItemNo() {
		return _vwReturnReserve.getItemNo();
	}

	/**
	* Returns the modified by of this vw return reserve.
	*
	* @return the modified by of this vw return reserve
	*/
	@Override
	public java.lang.String getModifiedBy() {
		return _vwReturnReserve.getModifiedBy();
	}

	/**
	* Returns the modified date of this vw return reserve.
	*
	* @return the modified date of this vw return reserve
	*/
	@Override
	public Date getModifiedDate() {
		return _vwReturnReserve.getModifiedDate();
	}

	/**
	* Returns the month of this vw return reserve.
	*
	* @return the month of this vw return reserve
	*/
	@Override
	public java.lang.String getMonth() {
		return _vwReturnReserve.getMonth();
	}

	/**
	* Returns the primary key of this vw return reserve.
	*
	* @return the primary key of this vw return reserve
	*/
	@Override
	public int getPrimaryKey() {
		return _vwReturnReserve.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _vwReturnReserve.getPrimaryKeyObj();
	}

	/**
	* Returns the project of this vw return reserve.
	*
	* @return the project of this vw return reserve
	*/
	@Override
	public java.lang.String getProject() {
		return _vwReturnReserve.getProject();
	}

	/**
	* Returns the record lock status of this vw return reserve.
	*
	* @return the record lock status of this vw return reserve
	*/
	@Override
	public boolean getRecordLockStatus() {
		return _vwReturnReserve.getRecordLockStatus();
	}

	/**
	* Returns the return reserve intf ID of this vw return reserve.
	*
	* @return the return reserve intf ID of this vw return reserve
	*/
	@Override
	public int getReturnReserveIntfId() {
		return _vwReturnReserve.getReturnReserveIntfId();
	}

	/**
	* Returns the return reserve sid of this vw return reserve.
	*
	* @return the return reserve sid of this vw return reserve
	*/
	@Override
	public int getReturnReserveSid() {
		return _vwReturnReserve.getReturnReserveSid();
	}

	/**
	* Returns the source of this vw return reserve.
	*
	* @return the source of this vw return reserve
	*/
	@Override
	public java.lang.String getSource() {
		return _vwReturnReserve.getSource();
	}

	/**
	* Returns the udc1 of this vw return reserve.
	*
	* @return the udc1 of this vw return reserve
	*/
	@Override
	public java.lang.String getUdc1() {
		return _vwReturnReserve.getUdc1();
	}

	/**
	* Returns the udc2 of this vw return reserve.
	*
	* @return the udc2 of this vw return reserve
	*/
	@Override
	public java.lang.String getUdc2() {
		return _vwReturnReserve.getUdc2();
	}

	/**
	* Returns the udc3 of this vw return reserve.
	*
	* @return the udc3 of this vw return reserve
	*/
	@Override
	public java.lang.String getUdc3() {
		return _vwReturnReserve.getUdc3();
	}

	/**
	* Returns the udc4 of this vw return reserve.
	*
	* @return the udc4 of this vw return reserve
	*/
	@Override
	public java.lang.String getUdc4() {
		return _vwReturnReserve.getUdc4();
	}

	/**
	* Returns the udc5 of this vw return reserve.
	*
	* @return the udc5 of this vw return reserve
	*/
	@Override
	public java.lang.String getUdc5() {
		return _vwReturnReserve.getUdc5();
	}

	/**
	* Returns the udc6 of this vw return reserve.
	*
	* @return the udc6 of this vw return reserve
	*/
	@Override
	public java.lang.String getUdc6() {
		return _vwReturnReserve.getUdc6();
	}

	/**
	* Returns the units of this vw return reserve.
	*
	* @return the units of this vw return reserve
	*/
	@Override
	public java.lang.String getUnits() {
		return _vwReturnReserve.getUnits();
	}

	/**
	* Returns the year of this vw return reserve.
	*
	* @return the year of this vw return reserve
	*/
	@Override
	public java.lang.String getYear() {
		return _vwReturnReserve.getYear();
	}

	@Override
	public int hashCode() {
		return _vwReturnReserve.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _vwReturnReserve.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _vwReturnReserve.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _vwReturnReserve.isNew();
	}

	/**
	* Returns <code>true</code> if this vw return reserve is record lock status.
	*
	* @return <code>true</code> if this vw return reserve is record lock status; <code>false</code> otherwise
	*/
	@Override
	public boolean isRecordLockStatus() {
		return _vwReturnReserve.isRecordLockStatus();
	}

	@Override
	public void persist() {
		_vwReturnReserve.persist();
	}

	/**
	* Sets the account of this vw return reserve.
	*
	* @param account the account of this vw return reserve
	*/
	@Override
	public void setAccount(java.lang.String account) {
		_vwReturnReserve.setAccount(account);
	}

	/**
	* Sets the amount of this vw return reserve.
	*
	* @param amount the amount of this vw return reserve
	*/
	@Override
	public void setAmount(java.lang.String amount) {
		_vwReturnReserve.setAmount(amount);
	}

	/**
	* Sets the batch ID of this vw return reserve.
	*
	* @param batchId the batch ID of this vw return reserve
	*/
	@Override
	public void setBatchId(java.lang.String batchId) {
		_vwReturnReserve.setBatchId(batchId);
	}

	/**
	* Sets the brand ID of this vw return reserve.
	*
	* @param brandId the brand ID of this vw return reserve
	*/
	@Override
	public void setBrandId(java.lang.String brandId) {
		_vwReturnReserve.setBrandId(brandId);
	}

	/**
	* Sets the brand master sid of this vw return reserve.
	*
	* @param brandMasterSid the brand master sid of this vw return reserve
	*/
	@Override
	public void setBrandMasterSid(int brandMasterSid) {
		_vwReturnReserve.setBrandMasterSid(brandMasterSid);
	}

	/**
	* Sets the brand name of this vw return reserve.
	*
	* @param brandName the brand name of this vw return reserve
	*/
	@Override
	public void setBrandName(java.lang.String brandName) {
		_vwReturnReserve.setBrandName(brandName);
	}

	/**
	* Sets the bu company master sid of this vw return reserve.
	*
	* @param buCompanyMasterSid the bu company master sid of this vw return reserve
	*/
	@Override
	public void setBuCompanyMasterSid(int buCompanyMasterSid) {
		_vwReturnReserve.setBuCompanyMasterSid(buCompanyMasterSid);
	}

	/**
	* Sets the business unit of this vw return reserve.
	*
	* @param businessUnit the business unit of this vw return reserve
	*/
	@Override
	public void setBusinessUnit(java.lang.String businessUnit) {
		_vwReturnReserve.setBusinessUnit(businessUnit);
	}

	/**
	* Sets the business unit name of this vw return reserve.
	*
	* @param businessUnitName the business unit name of this vw return reserve
	*/
	@Override
	public void setBusinessUnitName(java.lang.String businessUnitName) {
		_vwReturnReserve.setBusinessUnitName(businessUnitName);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_vwReturnReserve.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this vw return reserve.
	*
	* @param companyId the company ID of this vw return reserve
	*/
	@Override
	public void setCompanyId(java.lang.String companyId) {
		_vwReturnReserve.setCompanyId(companyId);
	}

	/**
	* Sets the company name of this vw return reserve.
	*
	* @param companyName the company name of this vw return reserve
	*/
	@Override
	public void setCompanyName(java.lang.String companyName) {
		_vwReturnReserve.setCompanyName(companyName);
	}

	/**
	* Sets the company no of this vw return reserve.
	*
	* @param companyNo the company no of this vw return reserve
	*/
	@Override
	public void setCompanyNo(java.lang.String companyNo) {
		_vwReturnReserve.setCompanyNo(companyNo);
	}

	/**
	* Sets the cost center of this vw return reserve.
	*
	* @param costCenter the cost center of this vw return reserve
	*/
	@Override
	public void setCostCenter(java.lang.String costCenter) {
		_vwReturnReserve.setCostCenter(costCenter);
	}

	/**
	* Sets the country of this vw return reserve.
	*
	* @param country the country of this vw return reserve
	*/
	@Override
	public void setCountry(java.lang.String country) {
		_vwReturnReserve.setCountry(country);
	}

	/**
	* Sets the created by of this vw return reserve.
	*
	* @param createdBy the created by of this vw return reserve
	*/
	@Override
	public void setCreatedBy(java.lang.String createdBy) {
		_vwReturnReserve.setCreatedBy(createdBy);
	}

	/**
	* Sets the created date of this vw return reserve.
	*
	* @param createdDate the created date of this vw return reserve
	*/
	@Override
	public void setCreatedDate(Date createdDate) {
		_vwReturnReserve.setCreatedDate(createdDate);
	}

	/**
	* Sets the division of this vw return reserve.
	*
	* @param division the division of this vw return reserve
	*/
	@Override
	public void setDivision(java.lang.String division) {
		_vwReturnReserve.setDivision(division);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_vwReturnReserve.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_vwReturnReserve.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_vwReturnReserve.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the future1 of this vw return reserve.
	*
	* @param future1 the future1 of this vw return reserve
	*/
	@Override
	public void setFuture1(java.lang.String future1) {
		_vwReturnReserve.setFuture1(future1);
	}

	/**
	* Sets the future2 of this vw return reserve.
	*
	* @param future2 the future2 of this vw return reserve
	*/
	@Override
	public void setFuture2(java.lang.String future2) {
		_vwReturnReserve.setFuture2(future2);
	}

	/**
	* Sets the gl company of this vw return reserve.
	*
	* @param glCompany the gl company of this vw return reserve
	*/
	@Override
	public void setGlCompany(java.lang.String glCompany) {
		_vwReturnReserve.setGlCompany(glCompany);
	}

	/**
	* Sets the gl company master sid of this vw return reserve.
	*
	* @param glCompanyMasterSid the gl company master sid of this vw return reserve
	*/
	@Override
	public void setGlCompanyMasterSid(int glCompanyMasterSid) {
		_vwReturnReserve.setGlCompanyMasterSid(glCompanyMasterSid);
	}

	/**
	* Sets the inbound status of this vw return reserve.
	*
	* @param inboundStatus the inbound status of this vw return reserve
	*/
	@Override
	public void setInboundStatus(java.lang.String inboundStatus) {
		_vwReturnReserve.setInboundStatus(inboundStatus);
	}

	/**
	* Sets the item ID of this vw return reserve.
	*
	* @param itemId the item ID of this vw return reserve
	*/
	@Override
	public void setItemId(java.lang.String itemId) {
		_vwReturnReserve.setItemId(itemId);
	}

	/**
	* Sets the item master sid of this vw return reserve.
	*
	* @param itemMasterSid the item master sid of this vw return reserve
	*/
	@Override
	public void setItemMasterSid(int itemMasterSid) {
		_vwReturnReserve.setItemMasterSid(itemMasterSid);
	}

	/**
	* Sets the item name of this vw return reserve.
	*
	* @param itemName the item name of this vw return reserve
	*/
	@Override
	public void setItemName(java.lang.String itemName) {
		_vwReturnReserve.setItemName(itemName);
	}

	/**
	* Sets the item no of this vw return reserve.
	*
	* @param itemNo the item no of this vw return reserve
	*/
	@Override
	public void setItemNo(java.lang.String itemNo) {
		_vwReturnReserve.setItemNo(itemNo);
	}

	/**
	* Sets the modified by of this vw return reserve.
	*
	* @param modifiedBy the modified by of this vw return reserve
	*/
	@Override
	public void setModifiedBy(java.lang.String modifiedBy) {
		_vwReturnReserve.setModifiedBy(modifiedBy);
	}

	/**
	* Sets the modified date of this vw return reserve.
	*
	* @param modifiedDate the modified date of this vw return reserve
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_vwReturnReserve.setModifiedDate(modifiedDate);
	}

	/**
	* Sets the month of this vw return reserve.
	*
	* @param month the month of this vw return reserve
	*/
	@Override
	public void setMonth(java.lang.String month) {
		_vwReturnReserve.setMonth(month);
	}

	@Override
	public void setNew(boolean n) {
		_vwReturnReserve.setNew(n);
	}

	/**
	* Sets the primary key of this vw return reserve.
	*
	* @param primaryKey the primary key of this vw return reserve
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_vwReturnReserve.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_vwReturnReserve.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the project of this vw return reserve.
	*
	* @param project the project of this vw return reserve
	*/
	@Override
	public void setProject(java.lang.String project) {
		_vwReturnReserve.setProject(project);
	}

	/**
	* Sets whether this vw return reserve is record lock status.
	*
	* @param recordLockStatus the record lock status of this vw return reserve
	*/
	@Override
	public void setRecordLockStatus(boolean recordLockStatus) {
		_vwReturnReserve.setRecordLockStatus(recordLockStatus);
	}

	/**
	* Sets the return reserve intf ID of this vw return reserve.
	*
	* @param returnReserveIntfId the return reserve intf ID of this vw return reserve
	*/
	@Override
	public void setReturnReserveIntfId(int returnReserveIntfId) {
		_vwReturnReserve.setReturnReserveIntfId(returnReserveIntfId);
	}

	/**
	* Sets the return reserve sid of this vw return reserve.
	*
	* @param returnReserveSid the return reserve sid of this vw return reserve
	*/
	@Override
	public void setReturnReserveSid(int returnReserveSid) {
		_vwReturnReserve.setReturnReserveSid(returnReserveSid);
	}

	/**
	* Sets the source of this vw return reserve.
	*
	* @param source the source of this vw return reserve
	*/
	@Override
	public void setSource(java.lang.String source) {
		_vwReturnReserve.setSource(source);
	}

	/**
	* Sets the udc1 of this vw return reserve.
	*
	* @param udc1 the udc1 of this vw return reserve
	*/
	@Override
	public void setUdc1(java.lang.String udc1) {
		_vwReturnReserve.setUdc1(udc1);
	}

	/**
	* Sets the udc2 of this vw return reserve.
	*
	* @param udc2 the udc2 of this vw return reserve
	*/
	@Override
	public void setUdc2(java.lang.String udc2) {
		_vwReturnReserve.setUdc2(udc2);
	}

	/**
	* Sets the udc3 of this vw return reserve.
	*
	* @param udc3 the udc3 of this vw return reserve
	*/
	@Override
	public void setUdc3(java.lang.String udc3) {
		_vwReturnReserve.setUdc3(udc3);
	}

	/**
	* Sets the udc4 of this vw return reserve.
	*
	* @param udc4 the udc4 of this vw return reserve
	*/
	@Override
	public void setUdc4(java.lang.String udc4) {
		_vwReturnReserve.setUdc4(udc4);
	}

	/**
	* Sets the udc5 of this vw return reserve.
	*
	* @param udc5 the udc5 of this vw return reserve
	*/
	@Override
	public void setUdc5(java.lang.String udc5) {
		_vwReturnReserve.setUdc5(udc5);
	}

	/**
	* Sets the udc6 of this vw return reserve.
	*
	* @param udc6 the udc6 of this vw return reserve
	*/
	@Override
	public void setUdc6(java.lang.String udc6) {
		_vwReturnReserve.setUdc6(udc6);
	}

	/**
	* Sets the units of this vw return reserve.
	*
	* @param units the units of this vw return reserve
	*/
	@Override
	public void setUnits(java.lang.String units) {
		_vwReturnReserve.setUnits(units);
	}

	/**
	* Sets the year of this vw return reserve.
	*
	* @param year the year of this vw return reserve
	*/
	@Override
	public void setYear(java.lang.String year) {
		_vwReturnReserve.setYear(year);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<VwReturnReserve> toCacheModel() {
		return _vwReturnReserve.toCacheModel();
	}

	@Override
	public VwReturnReserve toEscapedModel() {
		return new VwReturnReserveWrapper(_vwReturnReserve.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _vwReturnReserve.toString();
	}

	@Override
	public VwReturnReserve toUnescapedModel() {
		return new VwReturnReserveWrapper(_vwReturnReserve.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _vwReturnReserve.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof VwReturnReserveWrapper)) {
			return false;
		}

		VwReturnReserveWrapper vwReturnReserveWrapper = (VwReturnReserveWrapper)obj;

		if (Objects.equals(_vwReturnReserve,
					vwReturnReserveWrapper._vwReturnReserve)) {
			return true;
		}

		return false;
	}

	@Override
	public VwReturnReserve getWrappedModel() {
		return _vwReturnReserve;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _vwReturnReserve.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _vwReturnReserve.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_vwReturnReserve.resetOriginalValues();
	}

	private final VwReturnReserve _vwReturnReserve;
}