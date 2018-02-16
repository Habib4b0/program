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
 * This class is a wrapper for {@link StArpOutbound}.
 * </p>
 *
 * @author
 * @see StArpOutbound
 * @generated
 */
@ProviderType
public class StArpOutboundWrapper implements StArpOutbound,
	ModelWrapper<StArpOutbound> {
	public StArpOutboundWrapper(StArpOutbound stArpOutbound) {
		_stArpOutbound = stArpOutbound;
	}

	@Override
	public Class<?> getModelClass() {
		return StArpOutbound.class;
	}

	@Override
	public String getModelClassName() {
		return StArpOutbound.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("salesUnitsRate", getSalesUnitsRate());
		attributes.put("accountType", getAccountType());
		attributes.put("originalBatchId", getOriginalBatchId());
		attributes.put("companyMasterSid", getCompanyMasterSid());
		attributes.put("brandMasterSid", getBrandMasterSid());
		attributes.put("arpApprovalDate", getArpApprovalDate());
		attributes.put("userId", getUserId());
		attributes.put("arpMasterSid", getArpMasterSid());
		attributes.put("arpCreationDate", getArpCreationDate());
		attributes.put("checkRecord", getCheckRecord());
		attributes.put("arpId", getArpId());
		attributes.put("account", getAccount());
		attributes.put("outboundStatus", getOutboundStatus());
		attributes.put("periodSid", getPeriodSid());
		attributes.put("itemMasterSid", getItemMasterSid());
		attributes.put("rsModelSid", getRsModelSid());
		attributes.put("sessionId", getSessionId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Double salesUnitsRate = (Double)attributes.get("salesUnitsRate");

		if (salesUnitsRate != null) {
			setSalesUnitsRate(salesUnitsRate);
		}

		String accountType = (String)attributes.get("accountType");

		if (accountType != null) {
			setAccountType(accountType);
		}

		String originalBatchId = (String)attributes.get("originalBatchId");

		if (originalBatchId != null) {
			setOriginalBatchId(originalBatchId);
		}

		Integer companyMasterSid = (Integer)attributes.get("companyMasterSid");

		if (companyMasterSid != null) {
			setCompanyMasterSid(companyMasterSid);
		}

		Integer brandMasterSid = (Integer)attributes.get("brandMasterSid");

		if (brandMasterSid != null) {
			setBrandMasterSid(brandMasterSid);
		}

		Date arpApprovalDate = (Date)attributes.get("arpApprovalDate");

		if (arpApprovalDate != null) {
			setArpApprovalDate(arpApprovalDate);
		}

		Integer userId = (Integer)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		Integer arpMasterSid = (Integer)attributes.get("arpMasterSid");

		if (arpMasterSid != null) {
			setArpMasterSid(arpMasterSid);
		}

		Date arpCreationDate = (Date)attributes.get("arpCreationDate");

		if (arpCreationDate != null) {
			setArpCreationDate(arpCreationDate);
		}

		Boolean checkRecord = (Boolean)attributes.get("checkRecord");

		if (checkRecord != null) {
			setCheckRecord(checkRecord);
		}

		Integer arpId = (Integer)attributes.get("arpId");

		if (arpId != null) {
			setArpId(arpId);
		}

		String account = (String)attributes.get("account");

		if (account != null) {
			setAccount(account);
		}

		Boolean outboundStatus = (Boolean)attributes.get("outboundStatus");

		if (outboundStatus != null) {
			setOutboundStatus(outboundStatus);
		}

		Integer periodSid = (Integer)attributes.get("periodSid");

		if (periodSid != null) {
			setPeriodSid(periodSid);
		}

		Integer itemMasterSid = (Integer)attributes.get("itemMasterSid");

		if (itemMasterSid != null) {
			setItemMasterSid(itemMasterSid);
		}

		Integer rsModelSid = (Integer)attributes.get("rsModelSid");

		if (rsModelSid != null) {
			setRsModelSid(rsModelSid);
		}

		String sessionId = (String)attributes.get("sessionId");

		if (sessionId != null) {
			setSessionId(sessionId);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new StArpOutboundWrapper((StArpOutbound)_stArpOutbound.clone());
	}

	@Override
	public int compareTo(StArpOutbound stArpOutbound) {
		return _stArpOutbound.compareTo(stArpOutbound);
	}

	/**
	* Returns the account of this st arp outbound.
	*
	* @return the account of this st arp outbound
	*/
	@Override
	public java.lang.String getAccount() {
		return _stArpOutbound.getAccount();
	}

	/**
	* Returns the account type of this st arp outbound.
	*
	* @return the account type of this st arp outbound
	*/
	@Override
	public java.lang.String getAccountType() {
		return _stArpOutbound.getAccountType();
	}

	/**
	* Returns the arp approval date of this st arp outbound.
	*
	* @return the arp approval date of this st arp outbound
	*/
	@Override
	public Date getArpApprovalDate() {
		return _stArpOutbound.getArpApprovalDate();
	}

	/**
	* Returns the arp creation date of this st arp outbound.
	*
	* @return the arp creation date of this st arp outbound
	*/
	@Override
	public Date getArpCreationDate() {
		return _stArpOutbound.getArpCreationDate();
	}

	/**
	* Returns the arp ID of this st arp outbound.
	*
	* @return the arp ID of this st arp outbound
	*/
	@Override
	public int getArpId() {
		return _stArpOutbound.getArpId();
	}

	/**
	* Returns the arp master sid of this st arp outbound.
	*
	* @return the arp master sid of this st arp outbound
	*/
	@Override
	public int getArpMasterSid() {
		return _stArpOutbound.getArpMasterSid();
	}

	/**
	* Returns the brand master sid of this st arp outbound.
	*
	* @return the brand master sid of this st arp outbound
	*/
	@Override
	public int getBrandMasterSid() {
		return _stArpOutbound.getBrandMasterSid();
	}

	/**
	* Returns the check record of this st arp outbound.
	*
	* @return the check record of this st arp outbound
	*/
	@Override
	public boolean getCheckRecord() {
		return _stArpOutbound.getCheckRecord();
	}

	/**
	* Returns the company master sid of this st arp outbound.
	*
	* @return the company master sid of this st arp outbound
	*/
	@Override
	public int getCompanyMasterSid() {
		return _stArpOutbound.getCompanyMasterSid();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _stArpOutbound.getExpandoBridge();
	}

	/**
	* Returns the item master sid of this st arp outbound.
	*
	* @return the item master sid of this st arp outbound
	*/
	@Override
	public int getItemMasterSid() {
		return _stArpOutbound.getItemMasterSid();
	}

	/**
	* Returns the original batch ID of this st arp outbound.
	*
	* @return the original batch ID of this st arp outbound
	*/
	@Override
	public java.lang.String getOriginalBatchId() {
		return _stArpOutbound.getOriginalBatchId();
	}

	/**
	* Returns the outbound status of this st arp outbound.
	*
	* @return the outbound status of this st arp outbound
	*/
	@Override
	public boolean getOutboundStatus() {
		return _stArpOutbound.getOutboundStatus();
	}

	/**
	* Returns the period sid of this st arp outbound.
	*
	* @return the period sid of this st arp outbound
	*/
	@Override
	public int getPeriodSid() {
		return _stArpOutbound.getPeriodSid();
	}

	/**
	* Returns the primary key of this st arp outbound.
	*
	* @return the primary key of this st arp outbound
	*/
	@Override
	public com.stpl.app.parttwo.service.persistence.StArpOutboundPK getPrimaryKey() {
		return _stArpOutbound.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _stArpOutbound.getPrimaryKeyObj();
	}

	/**
	* Returns the rs model sid of this st arp outbound.
	*
	* @return the rs model sid of this st arp outbound
	*/
	@Override
	public int getRsModelSid() {
		return _stArpOutbound.getRsModelSid();
	}

	/**
	* Returns the sales units rate of this st arp outbound.
	*
	* @return the sales units rate of this st arp outbound
	*/
	@Override
	public double getSalesUnitsRate() {
		return _stArpOutbound.getSalesUnitsRate();
	}

	/**
	* Returns the session ID of this st arp outbound.
	*
	* @return the session ID of this st arp outbound
	*/
	@Override
	public java.lang.String getSessionId() {
		return _stArpOutbound.getSessionId();
	}

	/**
	* Returns the user ID of this st arp outbound.
	*
	* @return the user ID of this st arp outbound
	*/
	@Override
	public int getUserId() {
		return _stArpOutbound.getUserId();
	}

	@Override
	public int hashCode() {
		return _stArpOutbound.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _stArpOutbound.isCachedModel();
	}

	/**
	* Returns <code>true</code> if this st arp outbound is check record.
	*
	* @return <code>true</code> if this st arp outbound is check record; <code>false</code> otherwise
	*/
	@Override
	public boolean isCheckRecord() {
		return _stArpOutbound.isCheckRecord();
	}

	@Override
	public boolean isEscapedModel() {
		return _stArpOutbound.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _stArpOutbound.isNew();
	}

	/**
	* Returns <code>true</code> if this st arp outbound is outbound status.
	*
	* @return <code>true</code> if this st arp outbound is outbound status; <code>false</code> otherwise
	*/
	@Override
	public boolean isOutboundStatus() {
		return _stArpOutbound.isOutboundStatus();
	}

	@Override
	public void persist() {
		_stArpOutbound.persist();
	}

	/**
	* Sets the account of this st arp outbound.
	*
	* @param account the account of this st arp outbound
	*/
	@Override
	public void setAccount(java.lang.String account) {
		_stArpOutbound.setAccount(account);
	}

	/**
	* Sets the account type of this st arp outbound.
	*
	* @param accountType the account type of this st arp outbound
	*/
	@Override
	public void setAccountType(java.lang.String accountType) {
		_stArpOutbound.setAccountType(accountType);
	}

	/**
	* Sets the arp approval date of this st arp outbound.
	*
	* @param arpApprovalDate the arp approval date of this st arp outbound
	*/
	@Override
	public void setArpApprovalDate(Date arpApprovalDate) {
		_stArpOutbound.setArpApprovalDate(arpApprovalDate);
	}

	/**
	* Sets the arp creation date of this st arp outbound.
	*
	* @param arpCreationDate the arp creation date of this st arp outbound
	*/
	@Override
	public void setArpCreationDate(Date arpCreationDate) {
		_stArpOutbound.setArpCreationDate(arpCreationDate);
	}

	/**
	* Sets the arp ID of this st arp outbound.
	*
	* @param arpId the arp ID of this st arp outbound
	*/
	@Override
	public void setArpId(int arpId) {
		_stArpOutbound.setArpId(arpId);
	}

	/**
	* Sets the arp master sid of this st arp outbound.
	*
	* @param arpMasterSid the arp master sid of this st arp outbound
	*/
	@Override
	public void setArpMasterSid(int arpMasterSid) {
		_stArpOutbound.setArpMasterSid(arpMasterSid);
	}

	/**
	* Sets the brand master sid of this st arp outbound.
	*
	* @param brandMasterSid the brand master sid of this st arp outbound
	*/
	@Override
	public void setBrandMasterSid(int brandMasterSid) {
		_stArpOutbound.setBrandMasterSid(brandMasterSid);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_stArpOutbound.setCachedModel(cachedModel);
	}

	/**
	* Sets whether this st arp outbound is check record.
	*
	* @param checkRecord the check record of this st arp outbound
	*/
	@Override
	public void setCheckRecord(boolean checkRecord) {
		_stArpOutbound.setCheckRecord(checkRecord);
	}

	/**
	* Sets the company master sid of this st arp outbound.
	*
	* @param companyMasterSid the company master sid of this st arp outbound
	*/
	@Override
	public void setCompanyMasterSid(int companyMasterSid) {
		_stArpOutbound.setCompanyMasterSid(companyMasterSid);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_stArpOutbound.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_stArpOutbound.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_stArpOutbound.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the item master sid of this st arp outbound.
	*
	* @param itemMasterSid the item master sid of this st arp outbound
	*/
	@Override
	public void setItemMasterSid(int itemMasterSid) {
		_stArpOutbound.setItemMasterSid(itemMasterSid);
	}

	@Override
	public void setNew(boolean n) {
		_stArpOutbound.setNew(n);
	}

	/**
	* Sets the original batch ID of this st arp outbound.
	*
	* @param originalBatchId the original batch ID of this st arp outbound
	*/
	@Override
	public void setOriginalBatchId(java.lang.String originalBatchId) {
		_stArpOutbound.setOriginalBatchId(originalBatchId);
	}

	/**
	* Sets whether this st arp outbound is outbound status.
	*
	* @param outboundStatus the outbound status of this st arp outbound
	*/
	@Override
	public void setOutboundStatus(boolean outboundStatus) {
		_stArpOutbound.setOutboundStatus(outboundStatus);
	}

	/**
	* Sets the period sid of this st arp outbound.
	*
	* @param periodSid the period sid of this st arp outbound
	*/
	@Override
	public void setPeriodSid(int periodSid) {
		_stArpOutbound.setPeriodSid(periodSid);
	}

	/**
	* Sets the primary key of this st arp outbound.
	*
	* @param primaryKey the primary key of this st arp outbound
	*/
	@Override
	public void setPrimaryKey(
		com.stpl.app.parttwo.service.persistence.StArpOutboundPK primaryKey) {
		_stArpOutbound.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_stArpOutbound.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the rs model sid of this st arp outbound.
	*
	* @param rsModelSid the rs model sid of this st arp outbound
	*/
	@Override
	public void setRsModelSid(int rsModelSid) {
		_stArpOutbound.setRsModelSid(rsModelSid);
	}

	/**
	* Sets the sales units rate of this st arp outbound.
	*
	* @param salesUnitsRate the sales units rate of this st arp outbound
	*/
	@Override
	public void setSalesUnitsRate(double salesUnitsRate) {
		_stArpOutbound.setSalesUnitsRate(salesUnitsRate);
	}

	/**
	* Sets the session ID of this st arp outbound.
	*
	* @param sessionId the session ID of this st arp outbound
	*/
	@Override
	public void setSessionId(java.lang.String sessionId) {
		_stArpOutbound.setSessionId(sessionId);
	}

	/**
	* Sets the user ID of this st arp outbound.
	*
	* @param userId the user ID of this st arp outbound
	*/
	@Override
	public void setUserId(int userId) {
		_stArpOutbound.setUserId(userId);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<StArpOutbound> toCacheModel() {
		return _stArpOutbound.toCacheModel();
	}

	@Override
	public StArpOutbound toEscapedModel() {
		return new StArpOutboundWrapper(_stArpOutbound.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _stArpOutbound.toString();
	}

	@Override
	public StArpOutbound toUnescapedModel() {
		return new StArpOutboundWrapper(_stArpOutbound.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _stArpOutbound.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof StArpOutboundWrapper)) {
			return false;
		}

		StArpOutboundWrapper stArpOutboundWrapper = (StArpOutboundWrapper)obj;

		if (Objects.equals(_stArpOutbound, stArpOutboundWrapper._stArpOutbound)) {
			return true;
		}

		return false;
	}

	@Override
	public StArpOutbound getWrappedModel() {
		return _stArpOutbound;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _stArpOutbound.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _stArpOutbound.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_stArpOutbound.resetOriginalValues();
	}

	private final StArpOutbound _stArpOutbound;
}