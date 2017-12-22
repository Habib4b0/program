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

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;

/**
 * The base model interface for the VwCompanyMaster service. Represents a row in the &quot;VW_COMPANY_MASTER&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.stpl.app.parttwo.model.impl.VwCompanyMasterModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.stpl.app.parttwo.model.impl.VwCompanyMasterImpl}.
 * </p>
 *
 * @author
 * @see VwCompanyMaster
 * @see com.stpl.app.parttwo.model.impl.VwCompanyMasterImpl
 * @see com.stpl.app.parttwo.model.impl.VwCompanyMasterModelImpl
 * @generated
 */
@ProviderType
public interface VwCompanyMasterModel extends BaseModel<VwCompanyMaster> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a vw company master model instance should use the {@link VwCompanyMaster} interface instead.
	 */

	/**
	 * Returns the primary key of this vw company master.
	 *
	 * @return the primary key of this vw company master
	 */
	public int getPrimaryKey();

	/**
	 * Sets the primary key of this vw company master.
	 *
	 * @param primaryKey the primary key of this vw company master
	 */
	public void setPrimaryKey(int primaryKey);

	/**
	 * Returns the state of this vw company master.
	 *
	 * @return the state of this vw company master
	 */
	@AutoEscape
	public String getState();

	/**
	 * Sets the state of this vw company master.
	 *
	 * @param state the state of this vw company master
	 */
	public void setState(String state);

	/**
	 * Returns the financial system of this vw company master.
	 *
	 * @return the financial system of this vw company master
	 */
	@AutoEscape
	public String getFinancialSystem();

	/**
	 * Sets the financial system of this vw company master.
	 *
	 * @param financialSystem the financial system of this vw company master
	 */
	public void setFinancialSystem(String financialSystem);

	/**
	 * Returns the company group of this vw company master.
	 *
	 * @return the company group of this vw company master
	 */
	@AutoEscape
	public String getCompanyGroup();

	/**
	 * Sets the company group of this vw company master.
	 *
	 * @param companyGroup the company group of this vw company master
	 */
	public void setCompanyGroup(String companyGroup);

	/**
	 * Returns the company name of this vw company master.
	 *
	 * @return the company name of this vw company master
	 */
	@AutoEscape
	public String getCompanyName();

	/**
	 * Sets the company name of this vw company master.
	 *
	 * @param companyName the company name of this vw company master
	 */
	public void setCompanyName(String companyName);

	/**
	 * Returns the last updated date of this vw company master.
	 *
	 * @return the last updated date of this vw company master
	 */
	public Date getLastUpdatedDate();

	/**
	 * Sets the last updated date of this vw company master.
	 *
	 * @param lastUpdatedDate the last updated date of this vw company master
	 */
	public void setLastUpdatedDate(Date lastUpdatedDate);

	/**
	 * Returns the company category of this vw company master.
	 *
	 * @return the company category of this vw company master
	 */
	@AutoEscape
	public String getCompanyCategory();

	/**
	 * Sets the company category of this vw company master.
	 *
	 * @param companyCategory the company category of this vw company master
	 */
	public void setCompanyCategory(String companyCategory);

	/**
	 * Returns the modified date of this vw company master.
	 *
	 * @return the modified date of this vw company master
	 */
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this vw company master.
	 *
	 * @param modifiedDate the modified date of this vw company master
	 */
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the lives of this vw company master.
	 *
	 * @return the lives of this vw company master
	 */
	public int getLives();

	/**
	 * Sets the lives of this vw company master.
	 *
	 * @param lives the lives of this vw company master
	 */
	public void setLives(int lives);

	/**
	 * Returns the organization key of this vw company master.
	 *
	 * @return the organization key of this vw company master
	 */
	@AutoEscape
	public String getOrganizationKey();

	/**
	 * Sets the organization key of this vw company master.
	 *
	 * @param organizationKey the organization key of this vw company master
	 */
	public void setOrganizationKey(String organizationKey);

	/**
	 * Returns the address2 of this vw company master.
	 *
	 * @return the address2 of this vw company master
	 */
	@AutoEscape
	public String getAddress2();

	/**
	 * Sets the address2 of this vw company master.
	 *
	 * @param address2 the address2 of this vw company master
	 */
	public void setAddress2(String address2);

	/**
	 * Returns the created date of this vw company master.
	 *
	 * @return the created date of this vw company master
	 */
	public Date getCreatedDate();

	/**
	 * Sets the created date of this vw company master.
	 *
	 * @param createdDate the created date of this vw company master
	 */
	public void setCreatedDate(Date createdDate);

	/**
	 * Returns the created by of this vw company master.
	 *
	 * @return the created by of this vw company master
	 */
	@AutoEscape
	public String getCreatedBy();

	/**
	 * Sets the created by of this vw company master.
	 *
	 * @param createdBy the created by of this vw company master
	 */
	public void setCreatedBy(String createdBy);

	/**
	 * Returns the source of this vw company master.
	 *
	 * @return the source of this vw company master
	 */
	@AutoEscape
	public String getSource();

	/**
	 * Sets the source of this vw company master.
	 *
	 * @param source the source of this vw company master
	 */
	public void setSource(String source);

	/**
	 * Returns the address1 of this vw company master.
	 *
	 * @return the address1 of this vw company master
	 */
	@AutoEscape
	public String getAddress1();

	/**
	 * Sets the address1 of this vw company master.
	 *
	 * @param address1 the address1 of this vw company master
	 */
	public void setAddress1(String address1);

	/**
	 * Returns the add chg del indicator of this vw company master.
	 *
	 * @return the add chg del indicator of this vw company master
	 */
	@AutoEscape
	public String getAddChgDelIndicator();

	/**
	 * Sets the add chg del indicator of this vw company master.
	 *
	 * @param addChgDelIndicator the add chg del indicator of this vw company master
	 */
	public void setAddChgDelIndicator(String addChgDelIndicator);

	/**
	 * Returns the modified by of this vw company master.
	 *
	 * @return the modified by of this vw company master
	 */
	@AutoEscape
	public String getModifiedBy();

	/**
	 * Sets the modified by of this vw company master.
	 *
	 * @param modifiedBy the modified by of this vw company master
	 */
	public void setModifiedBy(String modifiedBy);

	/**
	 * Returns the udc6 of this vw company master.
	 *
	 * @return the udc6 of this vw company master
	 */
	@AutoEscape
	public String getUdc6();

	/**
	 * Sets the udc6 of this vw company master.
	 *
	 * @param udc6 the udc6 of this vw company master
	 */
	public void setUdc6(String udc6);

	/**
	 * Returns the udc5 of this vw company master.
	 *
	 * @return the udc5 of this vw company master
	 */
	@AutoEscape
	public String getUdc5();

	/**
	 * Sets the udc5 of this vw company master.
	 *
	 * @param udc5 the udc5 of this vw company master
	 */
	public void setUdc5(String udc5);

	/**
	 * Returns the company master sid of this vw company master.
	 *
	 * @return the company master sid of this vw company master
	 */
	public int getCompanyMasterSid();

	/**
	 * Sets the company master sid of this vw company master.
	 *
	 * @param companyMasterSid the company master sid of this vw company master
	 */
	public void setCompanyMasterSid(int companyMasterSid);

	/**
	 * Returns the udc4 of this vw company master.
	 *
	 * @return the udc4 of this vw company master
	 */
	@AutoEscape
	public String getUdc4();

	/**
	 * Sets the udc4 of this vw company master.
	 *
	 * @param udc4 the udc4 of this vw company master
	 */
	public void setUdc4(String udc4);

	/**
	 * Returns the udc1 of this vw company master.
	 *
	 * @return the udc1 of this vw company master
	 */
	@AutoEscape
	public String getUdc1();

	/**
	 * Sets the udc1 of this vw company master.
	 *
	 * @param udc1 the udc1 of this vw company master
	 */
	public void setUdc1(String udc1);

	/**
	 * Returns the zip code of this vw company master.
	 *
	 * @return the zip code of this vw company master
	 */
	@AutoEscape
	public String getZipCode();

	/**
	 * Sets the zip code of this vw company master.
	 *
	 * @param zipCode the zip code of this vw company master
	 */
	public void setZipCode(String zipCode);

	/**
	 * Returns the udc2 of this vw company master.
	 *
	 * @return the udc2 of this vw company master
	 */
	@AutoEscape
	public String getUdc2();

	/**
	 * Sets the udc2 of this vw company master.
	 *
	 * @param udc2 the udc2 of this vw company master
	 */
	public void setUdc2(String udc2);

	/**
	 * Returns the udc3 of this vw company master.
	 *
	 * @return the udc3 of this vw company master
	 */
	@AutoEscape
	public String getUdc3();

	/**
	 * Sets the udc3 of this vw company master.
	 *
	 * @param udc3 the udc3 of this vw company master
	 */
	public void setUdc3(String udc3);

	/**
	 * Returns the company ID string of this vw company master.
	 *
	 * @return the company ID string of this vw company master
	 */
	@AutoEscape
	public String getCompanyIdString();

	/**
	 * Sets the company ID string of this vw company master.
	 *
	 * @param companyIdString the company ID string of this vw company master
	 */
	public void setCompanyIdString(String companyIdString);

	/**
	 * Returns the country of this vw company master.
	 *
	 * @return the country of this vw company master
	 */
	@AutoEscape
	public String getCountry();

	/**
	 * Sets the country of this vw company master.
	 *
	 * @param country the country of this vw company master
	 */
	public void setCountry(String country);

	/**
	 * Returns the company type of this vw company master.
	 *
	 * @return the company type of this vw company master
	 */
	@AutoEscape
	public String getCompanyType();

	/**
	 * Sets the company type of this vw company master.
	 *
	 * @param companyType the company type of this vw company master
	 */
	public void setCompanyType(String companyType);

	/**
	 * Returns the company start date of this vw company master.
	 *
	 * @return the company start date of this vw company master
	 */
	public Date getCompanyStartDate();

	/**
	 * Sets the company start date of this vw company master.
	 *
	 * @param companyStartDate the company start date of this vw company master
	 */
	public void setCompanyStartDate(Date companyStartDate);

	/**
	 * Returns the company no of this vw company master.
	 *
	 * @return the company no of this vw company master
	 */
	@AutoEscape
	public String getCompanyNo();

	/**
	 * Sets the company no of this vw company master.
	 *
	 * @param companyNo the company no of this vw company master
	 */
	public void setCompanyNo(String companyNo);

	/**
	 * Returns the batch ID of this vw company master.
	 *
	 * @return the batch ID of this vw company master
	 */
	@AutoEscape
	public String getBatchId();

	/**
	 * Sets the batch ID of this vw company master.
	 *
	 * @param batchId the batch ID of this vw company master
	 */
	public void setBatchId(String batchId);

	/**
	 * Returns the company status of this vw company master.
	 *
	 * @return the company status of this vw company master
	 */
	@AutoEscape
	public String getCompanyStatus();

	/**
	 * Sets the company status of this vw company master.
	 *
	 * @param companyStatus the company status of this vw company master
	 */
	public void setCompanyStatus(String companyStatus);

	/**
	 * Returns the company end date of this vw company master.
	 *
	 * @return the company end date of this vw company master
	 */
	public Date getCompanyEndDate();

	/**
	 * Sets the company end date of this vw company master.
	 *
	 * @param companyEndDate the company end date of this vw company master
	 */
	public void setCompanyEndDate(Date companyEndDate);

	/**
	 * Returns the city of this vw company master.
	 *
	 * @return the city of this vw company master
	 */
	@AutoEscape
	public String getCity();

	/**
	 * Sets the city of this vw company master.
	 *
	 * @param city the city of this vw company master
	 */
	public void setCity(String city);

	/**
	 * Returns the region code of this vw company master.
	 *
	 * @return the region code of this vw company master
	 */
	@AutoEscape
	public String getRegionCode();

	/**
	 * Sets the region code of this vw company master.
	 *
	 * @param regionCode the region code of this vw company master
	 */
	public void setRegionCode(String regionCode);

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
	public int compareTo(VwCompanyMaster vwCompanyMaster);

	@Override
	public int hashCode();

	@Override
	public CacheModel<VwCompanyMaster> toCacheModel();

	@Override
	public VwCompanyMaster toEscapedModel();

	@Override
	public VwCompanyMaster toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}