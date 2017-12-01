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
 * The base model interface for the CfpContractDetails service. Represents a row in the &quot;CFP_CONTRACT_DETAILS&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.stpl.app.model.impl.CfpContractDetailsModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.stpl.app.model.impl.CfpContractDetailsImpl}.
 * </p>
 *
 * @author
 * @see CfpContractDetails
 * @see com.stpl.app.model.impl.CfpContractDetailsImpl
 * @see com.stpl.app.model.impl.CfpContractDetailsModelImpl
 * @generated
 */
@ProviderType
public interface CfpContractDetailsModel extends BaseModel<CfpContractDetails> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a cfp contract details model instance should use the {@link CfpContractDetails} interface instead.
	 */

	/**
	 * Returns the primary key of this cfp contract details.
	 *
	 * @return the primary key of this cfp contract details
	 */
	public int getPrimaryKey();

	/**
	 * Sets the primary key of this cfp contract details.
	 *
	 * @param primaryKey the primary key of this cfp contract details
	 */
	public void setPrimaryKey(int primaryKey);

	/**
	 * Returns the created by of this cfp contract details.
	 *
	 * @return the created by of this cfp contract details
	 */
	public int getCreatedBy();

	/**
	 * Sets the created by of this cfp contract details.
	 *
	 * @param createdBy the created by of this cfp contract details
	 */
	public void setCreatedBy(int createdBy);

	/**
	 * Returns the trade class of this cfp contract details.
	 *
	 * @return the trade class of this cfp contract details
	 */
	public int getTradeClass();

	/**
	 * Sets the trade class of this cfp contract details.
	 *
	 * @param tradeClass the trade class of this cfp contract details
	 */
	public void setTradeClass(int tradeClass);

	/**
	 * Returns the trade class end date of this cfp contract details.
	 *
	 * @return the trade class end date of this cfp contract details
	 */
	public Date getTradeClassEndDate();

	/**
	 * Sets the trade class end date of this cfp contract details.
	 *
	 * @param tradeClassEndDate the trade class end date of this cfp contract details
	 */
	public void setTradeClassEndDate(Date tradeClassEndDate);

	/**
	 * Returns the cfp contract sid of this cfp contract details.
	 *
	 * @return the cfp contract sid of this cfp contract details
	 */
	public int getCfpContractSid();

	/**
	 * Sets the cfp contract sid of this cfp contract details.
	 *
	 * @param cfpContractSid the cfp contract sid of this cfp contract details
	 */
	public void setCfpContractSid(int cfpContractSid);

	/**
	 * Returns the modified by of this cfp contract details.
	 *
	 * @return the modified by of this cfp contract details
	 */
	public int getModifiedBy();

	/**
	 * Sets the modified by of this cfp contract details.
	 *
	 * @param modifiedBy the modified by of this cfp contract details
	 */
	public void setModifiedBy(int modifiedBy);

	/**
	 * Returns the company start date of this cfp contract details.
	 *
	 * @return the company start date of this cfp contract details
	 */
	public Date getCompanyStartDate();

	/**
	 * Sets the company start date of this cfp contract details.
	 *
	 * @param companyStartDate the company start date of this cfp contract details
	 */
	public void setCompanyStartDate(Date companyStartDate);

	/**
	 * Returns the trade class start date of this cfp contract details.
	 *
	 * @return the trade class start date of this cfp contract details
	 */
	public Date getTradeClassStartDate();

	/**
	 * Sets the trade class start date of this cfp contract details.
	 *
	 * @param tradeClassStartDate the trade class start date of this cfp contract details
	 */
	public void setTradeClassStartDate(Date tradeClassStartDate);

	/**
	 * Returns the created date of this cfp contract details.
	 *
	 * @return the created date of this cfp contract details
	 */
	public Date getCreatedDate();

	/**
	 * Sets the created date of this cfp contract details.
	 *
	 * @param createdDate the created date of this cfp contract details
	 */
	public void setCreatedDate(Date createdDate);

	/**
	 * Returns the cfp contract attached date of this cfp contract details.
	 *
	 * @return the cfp contract attached date of this cfp contract details
	 */
	public Date getCfpContractAttachedDate();

	/**
	 * Sets the cfp contract attached date of this cfp contract details.
	 *
	 * @param cfpContractAttachedDate the cfp contract attached date of this cfp contract details
	 */
	public void setCfpContractAttachedDate(Date cfpContractAttachedDate);

	/**
	 * Returns the company end date of this cfp contract details.
	 *
	 * @return the company end date of this cfp contract details
	 */
	public Date getCompanyEndDate();

	/**
	 * Sets the company end date of this cfp contract details.
	 *
	 * @param companyEndDate the company end date of this cfp contract details
	 */
	public void setCompanyEndDate(Date companyEndDate);

	/**
	 * Returns the company master sid of this cfp contract details.
	 *
	 * @return the company master sid of this cfp contract details
	 */
	public int getCompanyMasterSid();

	/**
	 * Sets the company master sid of this cfp contract details.
	 *
	 * @param companyMasterSid the company master sid of this cfp contract details
	 */
	public void setCompanyMasterSid(int companyMasterSid);

	/**
	 * Returns the batch ID of this cfp contract details.
	 *
	 * @return the batch ID of this cfp contract details
	 */
	@AutoEscape
	public String getBatchId();

	/**
	 * Sets the batch ID of this cfp contract details.
	 *
	 * @param batchId the batch ID of this cfp contract details
	 */
	public void setBatchId(String batchId);

	/**
	 * Returns the modified date of this cfp contract details.
	 *
	 * @return the modified date of this cfp contract details
	 */
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this cfp contract details.
	 *
	 * @param modifiedDate the modified date of this cfp contract details
	 */
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the record lock status of this cfp contract details.
	 *
	 * @return the record lock status of this cfp contract details
	 */
	public boolean getRecordLockStatus();

	/**
	 * Returns <code>true</code> if this cfp contract details is record lock status.
	 *
	 * @return <code>true</code> if this cfp contract details is record lock status; <code>false</code> otherwise
	 */
	public boolean isRecordLockStatus();

	/**
	 * Sets whether this cfp contract details is record lock status.
	 *
	 * @param recordLockStatus the record lock status of this cfp contract details
	 */
	public void setRecordLockStatus(boolean recordLockStatus);

	/**
	 * Returns the source of this cfp contract details.
	 *
	 * @return the source of this cfp contract details
	 */
	@AutoEscape
	public String getSource();

	/**
	 * Sets the source of this cfp contract details.
	 *
	 * @param source the source of this cfp contract details
	 */
	public void setSource(String source);

	/**
	 * Returns the cfp contract details sid of this cfp contract details.
	 *
	 * @return the cfp contract details sid of this cfp contract details
	 */
	public int getCfpContractDetailsSid();

	/**
	 * Sets the cfp contract details sid of this cfp contract details.
	 *
	 * @param cfpContractDetailsSid the cfp contract details sid of this cfp contract details
	 */
	public void setCfpContractDetailsSid(int cfpContractDetailsSid);

	/**
	 * Returns the cfp contract attached status of this cfp contract details.
	 *
	 * @return the cfp contract attached status of this cfp contract details
	 */
	public int getCfpContractAttachedStatus();

	/**
	 * Sets the cfp contract attached status of this cfp contract details.
	 *
	 * @param cfpContractAttachedStatus the cfp contract attached status of this cfp contract details
	 */
	public void setCfpContractAttachedStatus(int cfpContractAttachedStatus);

	/**
	 * Returns the inbound status of this cfp contract details.
	 *
	 * @return the inbound status of this cfp contract details
	 */
	@AutoEscape
	public String getInboundStatus();

	/**
	 * Sets the inbound status of this cfp contract details.
	 *
	 * @param inboundStatus the inbound status of this cfp contract details
	 */
	public void setInboundStatus(String inboundStatus);

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
	public int compareTo(CfpContractDetails cfpContractDetails);

	@Override
	public int hashCode();

	@Override
	public CacheModel<CfpContractDetails> toCacheModel();

	@Override
	public CfpContractDetails toEscapedModel();

	@Override
	public CfpContractDetails toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}