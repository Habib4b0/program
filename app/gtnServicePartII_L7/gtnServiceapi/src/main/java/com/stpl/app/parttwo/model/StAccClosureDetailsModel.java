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
 * The base model interface for the StAccClosureDetails service. Represents a row in the &quot;ST_ACC_CLOSURE_DETAILS&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.stpl.app.parttwo.model.impl.StAccClosureDetailsModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.stpl.app.parttwo.model.impl.StAccClosureDetailsImpl}.
 * </p>
 *
 * @author
 * @see StAccClosureDetails
 * @see com.stpl.app.parttwo.model.impl.StAccClosureDetailsImpl
 * @see com.stpl.app.parttwo.model.impl.StAccClosureDetailsModelImpl
 * @generated
 */
@ProviderType
public interface StAccClosureDetailsModel extends BaseModel<StAccClosureDetails> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a st acc closure details model instance should use the {@link StAccClosureDetails} interface instead.
	 */

	/**
	 * Returns the primary key of this st acc closure details.
	 *
	 * @return the primary key of this st acc closure details
	 */
	public int getPrimaryKey();

	/**
	 * Sets the primary key of this st acc closure details.
	 *
	 * @param primaryKey the primary key of this st acc closure details
	 */
	public void setPrimaryKey(int primaryKey);

	/**
	 * Returns the last modified date of this st acc closure details.
	 *
	 * @return the last modified date of this st acc closure details
	 */
	public Date getLastModifiedDate();

	/**
	 * Sets the last modified date of this st acc closure details.
	 *
	 * @param lastModifiedDate the last modified date of this st acc closure details
	 */
	public void setLastModifiedDate(Date lastModifiedDate);

	/**
	 * Returns the check record of this st acc closure details.
	 *
	 * @return the check record of this st acc closure details
	 */
	public boolean getCheckRecord();

	/**
	 * Returns <code>true</code> if this st acc closure details is check record.
	 *
	 * @return <code>true</code> if this st acc closure details is check record; <code>false</code> otherwise
	 */
	public boolean isCheckRecord();

	/**
	 * Sets whether this st acc closure details is check record.
	 *
	 * @param checkRecord the check record of this st acc closure details
	 */
	public void setCheckRecord(boolean checkRecord);

	/**
	 * Returns the contract name of this st acc closure details.
	 *
	 * @return the contract name of this st acc closure details
	 */
	@AutoEscape
	public String getContractName();

	/**
	 * Sets the contract name of this st acc closure details.
	 *
	 * @param contractName the contract name of this st acc closure details
	 */
	public void setContractName(String contractName);

	/**
	 * Returns the user ID of this st acc closure details.
	 *
	 * @return the user ID of this st acc closure details
	 */
	public int getUserId();

	/**
	 * Sets the user ID of this st acc closure details.
	 *
	 * @param userId the user ID of this st acc closure details
	 */
	public void setUserId(int userId);

	/**
	 * Returns the item master sid of this st acc closure details.
	 *
	 * @return the item master sid of this st acc closure details
	 */
	public int getItemMasterSid();

	/**
	 * Sets the item master sid of this st acc closure details.
	 *
	 * @param itemMasterSid the item master sid of this st acc closure details
	 */
	public void setItemMasterSid(int itemMasterSid);

	/**
	 * Returns the module name of this st acc closure details.
	 *
	 * @return the module name of this st acc closure details
	 */
	@AutoEscape
	public String getModuleName();

	/**
	 * Sets the module name of this st acc closure details.
	 *
	 * @param moduleName the module name of this st acc closure details
	 */
	public void setModuleName(String moduleName);

	/**
	 * Returns the company name of this st acc closure details.
	 *
	 * @return the company name of this st acc closure details
	 */
	@AutoEscape
	public String getCompanyName();

	/**
	 * Sets the company name of this st acc closure details.
	 *
	 * @param companyName the company name of this st acc closure details
	 */
	public void setCompanyName(String companyName);

	/**
	 * Returns the brand name of this st acc closure details.
	 *
	 * @return the brand name of this st acc closure details
	 */
	@AutoEscape
	public String getBrandName();

	/**
	 * Sets the brand name of this st acc closure details.
	 *
	 * @param brandName the brand name of this st acc closure details
	 */
	public void setBrandName(String brandName);

	/**
	 * Returns the company cost center of this st acc closure details.
	 *
	 * @return the company cost center of this st acc closure details
	 */
	@AutoEscape
	public String getCompanyCostCenter();

	/**
	 * Sets the company cost center of this st acc closure details.
	 *
	 * @param companyCostCenter the company cost center of this st acc closure details
	 */
	public void setCompanyCostCenter(String companyCostCenter);

	/**
	 * Returns the company no of this st acc closure details.
	 *
	 * @return the company no of this st acc closure details
	 */
	@AutoEscape
	public String getCompanyNo();

	/**
	 * Sets the company no of this st acc closure details.
	 *
	 * @param companyNo the company no of this st acc closure details
	 */
	public void setCompanyNo(String companyNo);

	/**
	 * Returns the contract master sid of this st acc closure details.
	 *
	 * @return the contract master sid of this st acc closure details
	 */
	public int getContractMasterSid();

	/**
	 * Sets the contract master sid of this st acc closure details.
	 *
	 * @param contractMasterSid the contract master sid of this st acc closure details
	 */
	public void setContractMasterSid(int contractMasterSid);

	/**
	 * Returns the session ID of this st acc closure details.
	 *
	 * @return the session ID of this st acc closure details
	 */
	public int getSessionId();

	/**
	 * Sets the session ID of this st acc closure details.
	 *
	 * @param sessionId the session ID of this st acc closure details
	 */
	public void setSessionId(int sessionId);

	/**
	 * Returns the ccp details sid of this st acc closure details.
	 *
	 * @return the ccp details sid of this st acc closure details
	 */
	public int getCcpDetailsSid();

	/**
	 * Sets the ccp details sid of this st acc closure details.
	 *
	 * @param ccpDetailsSid the ccp details sid of this st acc closure details
	 */
	public void setCcpDetailsSid(int ccpDetailsSid);

	/**
	 * Returns the item name of this st acc closure details.
	 *
	 * @return the item name of this st acc closure details
	 */
	@AutoEscape
	public String getItemName();

	/**
	 * Sets the item name of this st acc closure details.
	 *
	 * @param itemName the item name of this st acc closure details
	 */
	public void setItemName(String itemName);

	/**
	 * Returns the acc closure master sid of this st acc closure details.
	 *
	 * @return the acc closure master sid of this st acc closure details
	 */
	public int getAccClosureMasterSid();

	/**
	 * Sets the acc closure master sid of this st acc closure details.
	 *
	 * @param accClosureMasterSid the acc closure master sid of this st acc closure details
	 */
	public void setAccClosureMasterSid(int accClosureMasterSid);

	/**
	 * Returns the rs model sid of this st acc closure details.
	 *
	 * @return the rs model sid of this st acc closure details
	 */
	public int getRsModelSid();

	/**
	 * Sets the rs model sid of this st acc closure details.
	 *
	 * @param rsModelSid the rs model sid of this st acc closure details
	 */
	public void setRsModelSid(int rsModelSid);

	/**
	 * Returns the contract no of this st acc closure details.
	 *
	 * @return the contract no of this st acc closure details
	 */
	@AutoEscape
	public String getContractNo();

	/**
	 * Sets the contract no of this st acc closure details.
	 *
	 * @param contractNo the contract no of this st acc closure details
	 */
	public void setContractNo(String contractNo);

	/**
	 * Returns the company master sid of this st acc closure details.
	 *
	 * @return the company master sid of this st acc closure details
	 */
	public int getCompanyMasterSid();

	/**
	 * Sets the company master sid of this st acc closure details.
	 *
	 * @param companyMasterSid the company master sid of this st acc closure details
	 */
	public void setCompanyMasterSid(int companyMasterSid);

	/**
	 * Returns the ndc8 of this st acc closure details.
	 *
	 * @return the ndc8 of this st acc closure details
	 */
	@AutoEscape
	public String getNdc8();

	/**
	 * Sets the ndc8 of this st acc closure details.
	 *
	 * @param ndc8 the ndc8 of this st acc closure details
	 */
	public void setNdc8(String ndc8);

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
	public int compareTo(StAccClosureDetails stAccClosureDetails);

	@Override
	public int hashCode();

	@Override
	public CacheModel<StAccClosureDetails> toCacheModel();

	@Override
	public StAccClosureDetails toEscapedModel();

	@Override
	public StAccClosureDetails toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}