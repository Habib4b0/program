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
 * This class is a wrapper for {@link WfMailConfig}.
 * </p>
 *
 * @author
 * @see WfMailConfig
 * @generated
 */
@ProviderType
public class WfMailConfigWrapper implements WfMailConfig,
	ModelWrapper<WfMailConfig> {
	public WfMailConfigWrapper(WfMailConfig wfMailConfig) {
		_wfMailConfig = wfMailConfig;
	}

	@Override
	public Class<?> getModelClass() {
		return WfMailConfig.class;
	}

	@Override
	public String getModelClassName() {
		return WfMailConfig.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("smtpFlag", getSmtpFlag());
		attributes.put("createdBy", getCreatedBy());
		attributes.put("emailAddress", getEmailAddress());
		attributes.put("password", getPassword());
		attributes.put("modifiedBy", getModifiedBy());
		attributes.put("wfMailConfigSid", getWfMailConfigSid());
		attributes.put("hostName", getHostName());
		attributes.put("createdDate", getCreatedDate());
		attributes.put("portNumber", getPortNumber());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("inboundStatus", getInboundStatus());
		attributes.put("testMailAddress", getTestMailAddress());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String smtpFlag = (String)attributes.get("smtpFlag");

		if (smtpFlag != null) {
			setSmtpFlag(smtpFlag);
		}

		Integer createdBy = (Integer)attributes.get("createdBy");

		if (createdBy != null) {
			setCreatedBy(createdBy);
		}

		String emailAddress = (String)attributes.get("emailAddress");

		if (emailAddress != null) {
			setEmailAddress(emailAddress);
		}

		String password = (String)attributes.get("password");

		if (password != null) {
			setPassword(password);
		}

		Integer modifiedBy = (Integer)attributes.get("modifiedBy");

		if (modifiedBy != null) {
			setModifiedBy(modifiedBy);
		}

		Integer wfMailConfigSid = (Integer)attributes.get("wfMailConfigSid");

		if (wfMailConfigSid != null) {
			setWfMailConfigSid(wfMailConfigSid);
		}

		String hostName = (String)attributes.get("hostName");

		if (hostName != null) {
			setHostName(hostName);
		}

		Date createdDate = (Date)attributes.get("createdDate");

		if (createdDate != null) {
			setCreatedDate(createdDate);
		}

		String portNumber = (String)attributes.get("portNumber");

		if (portNumber != null) {
			setPortNumber(portNumber);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String inboundStatus = (String)attributes.get("inboundStatus");

		if (inboundStatus != null) {
			setInboundStatus(inboundStatus);
		}

		String testMailAddress = (String)attributes.get("testMailAddress");

		if (testMailAddress != null) {
			setTestMailAddress(testMailAddress);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new WfMailConfigWrapper((WfMailConfig)_wfMailConfig.clone());
	}

	@Override
	public int compareTo(WfMailConfig wfMailConfig) {
		return _wfMailConfig.compareTo(wfMailConfig);
	}

	/**
	* Returns the created by of this wf mail config.
	*
	* @return the created by of this wf mail config
	*/
	@Override
	public int getCreatedBy() {
		return _wfMailConfig.getCreatedBy();
	}

	/**
	* Returns the created date of this wf mail config.
	*
	* @return the created date of this wf mail config
	*/
	@Override
	public Date getCreatedDate() {
		return _wfMailConfig.getCreatedDate();
	}

	/**
	* Returns the email address of this wf mail config.
	*
	* @return the email address of this wf mail config
	*/
	@Override
	public java.lang.String getEmailAddress() {
		return _wfMailConfig.getEmailAddress();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _wfMailConfig.getExpandoBridge();
	}

	/**
	* Returns the host name of this wf mail config.
	*
	* @return the host name of this wf mail config
	*/
	@Override
	public java.lang.String getHostName() {
		return _wfMailConfig.getHostName();
	}

	/**
	* Returns the inbound status of this wf mail config.
	*
	* @return the inbound status of this wf mail config
	*/
	@Override
	public java.lang.String getInboundStatus() {
		return _wfMailConfig.getInboundStatus();
	}

	/**
	* Returns the modified by of this wf mail config.
	*
	* @return the modified by of this wf mail config
	*/
	@Override
	public int getModifiedBy() {
		return _wfMailConfig.getModifiedBy();
	}

	/**
	* Returns the modified date of this wf mail config.
	*
	* @return the modified date of this wf mail config
	*/
	@Override
	public Date getModifiedDate() {
		return _wfMailConfig.getModifiedDate();
	}

	/**
	* Returns the password of this wf mail config.
	*
	* @return the password of this wf mail config
	*/
	@Override
	public java.lang.String getPassword() {
		return _wfMailConfig.getPassword();
	}

	/**
	* Returns the port number of this wf mail config.
	*
	* @return the port number of this wf mail config
	*/
	@Override
	public java.lang.String getPortNumber() {
		return _wfMailConfig.getPortNumber();
	}

	/**
	* Returns the primary key of this wf mail config.
	*
	* @return the primary key of this wf mail config
	*/
	@Override
	public int getPrimaryKey() {
		return _wfMailConfig.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _wfMailConfig.getPrimaryKeyObj();
	}

	/**
	* Returns the smtp flag of this wf mail config.
	*
	* @return the smtp flag of this wf mail config
	*/
	@Override
	public java.lang.String getSmtpFlag() {
		return _wfMailConfig.getSmtpFlag();
	}

	/**
	* Returns the test mail address of this wf mail config.
	*
	* @return the test mail address of this wf mail config
	*/
	@Override
	public java.lang.String getTestMailAddress() {
		return _wfMailConfig.getTestMailAddress();
	}

	/**
	* Returns the wf mail config sid of this wf mail config.
	*
	* @return the wf mail config sid of this wf mail config
	*/
	@Override
	public int getWfMailConfigSid() {
		return _wfMailConfig.getWfMailConfigSid();
	}

	@Override
	public int hashCode() {
		return _wfMailConfig.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _wfMailConfig.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _wfMailConfig.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _wfMailConfig.isNew();
	}

	@Override
	public void persist() {
		_wfMailConfig.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_wfMailConfig.setCachedModel(cachedModel);
	}

	/**
	* Sets the created by of this wf mail config.
	*
	* @param createdBy the created by of this wf mail config
	*/
	@Override
	public void setCreatedBy(int createdBy) {
		_wfMailConfig.setCreatedBy(createdBy);
	}

	/**
	* Sets the created date of this wf mail config.
	*
	* @param createdDate the created date of this wf mail config
	*/
	@Override
	public void setCreatedDate(Date createdDate) {
		_wfMailConfig.setCreatedDate(createdDate);
	}

	/**
	* Sets the email address of this wf mail config.
	*
	* @param emailAddress the email address of this wf mail config
	*/
	@Override
	public void setEmailAddress(java.lang.String emailAddress) {
		_wfMailConfig.setEmailAddress(emailAddress);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_wfMailConfig.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_wfMailConfig.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_wfMailConfig.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the host name of this wf mail config.
	*
	* @param hostName the host name of this wf mail config
	*/
	@Override
	public void setHostName(java.lang.String hostName) {
		_wfMailConfig.setHostName(hostName);
	}

	/**
	* Sets the inbound status of this wf mail config.
	*
	* @param inboundStatus the inbound status of this wf mail config
	*/
	@Override
	public void setInboundStatus(java.lang.String inboundStatus) {
		_wfMailConfig.setInboundStatus(inboundStatus);
	}

	/**
	* Sets the modified by of this wf mail config.
	*
	* @param modifiedBy the modified by of this wf mail config
	*/
	@Override
	public void setModifiedBy(int modifiedBy) {
		_wfMailConfig.setModifiedBy(modifiedBy);
	}

	/**
	* Sets the modified date of this wf mail config.
	*
	* @param modifiedDate the modified date of this wf mail config
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_wfMailConfig.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_wfMailConfig.setNew(n);
	}

	/**
	* Sets the password of this wf mail config.
	*
	* @param password the password of this wf mail config
	*/
	@Override
	public void setPassword(java.lang.String password) {
		_wfMailConfig.setPassword(password);
	}

	/**
	* Sets the port number of this wf mail config.
	*
	* @param portNumber the port number of this wf mail config
	*/
	@Override
	public void setPortNumber(java.lang.String portNumber) {
		_wfMailConfig.setPortNumber(portNumber);
	}

	/**
	* Sets the primary key of this wf mail config.
	*
	* @param primaryKey the primary key of this wf mail config
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_wfMailConfig.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_wfMailConfig.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the smtp flag of this wf mail config.
	*
	* @param smtpFlag the smtp flag of this wf mail config
	*/
	@Override
	public void setSmtpFlag(java.lang.String smtpFlag) {
		_wfMailConfig.setSmtpFlag(smtpFlag);
	}

	/**
	* Sets the test mail address of this wf mail config.
	*
	* @param testMailAddress the test mail address of this wf mail config
	*/
	@Override
	public void setTestMailAddress(java.lang.String testMailAddress) {
		_wfMailConfig.setTestMailAddress(testMailAddress);
	}

	/**
	* Sets the wf mail config sid of this wf mail config.
	*
	* @param wfMailConfigSid the wf mail config sid of this wf mail config
	*/
	@Override
	public void setWfMailConfigSid(int wfMailConfigSid) {
		_wfMailConfig.setWfMailConfigSid(wfMailConfigSid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<WfMailConfig> toCacheModel() {
		return _wfMailConfig.toCacheModel();
	}

	@Override
	public WfMailConfig toEscapedModel() {
		return new WfMailConfigWrapper(_wfMailConfig.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _wfMailConfig.toString();
	}

	@Override
	public WfMailConfig toUnescapedModel() {
		return new WfMailConfigWrapper(_wfMailConfig.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _wfMailConfig.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof WfMailConfigWrapper)) {
			return false;
		}

		WfMailConfigWrapper wfMailConfigWrapper = (WfMailConfigWrapper)obj;

		if (Objects.equals(_wfMailConfig, wfMailConfigWrapper._wfMailConfig)) {
			return true;
		}

		return false;
	}

	@Override
	public WfMailConfig getWrappedModel() {
		return _wfMailConfig;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _wfMailConfig.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _wfMailConfig.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_wfMailConfig.resetOriginalValues();
	}

	private final WfMailConfig _wfMailConfig;
}