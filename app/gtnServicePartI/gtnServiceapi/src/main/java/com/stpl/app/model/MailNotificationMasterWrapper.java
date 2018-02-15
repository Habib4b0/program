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
 * This class is a wrapper for {@link MailNotificationMaster}.
 * </p>
 *
 * @author
 * @see MailNotificationMaster
 * @generated
 */
@ProviderType
public class MailNotificationMasterWrapper implements MailNotificationMaster,
	ModelWrapper<MailNotificationMaster> {
	public MailNotificationMasterWrapper(
		MailNotificationMaster mailNotificationMaster) {
		_mailNotificationMaster = mailNotificationMaster;
	}

	@Override
	public Class<?> getModelClass() {
		return MailNotificationMaster.class;
	}

	@Override
	public String getModelClassName() {
		return MailNotificationMaster.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("subject", getSubject());
		attributes.put("createdDate", getCreatedDate());
		attributes.put("createdBy", getCreatedBy());
		attributes.put("toMailIds", getToMailIds());
		attributes.put("notificationCategoryId", getNotificationCategoryId());
		attributes.put("notificationModule", getNotificationModule());
		attributes.put("body", getBody());
		attributes.put("fromMailId", getFromMailId());
		attributes.put("ccMailIds", getCcMailIds());
		attributes.put("versionNo", getVersionNo());
		attributes.put("modifiedBy", getModifiedBy());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("mailNotificationSid", getMailNotificationSid());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String subject = (String)attributes.get("subject");

		if (subject != null) {
			setSubject(subject);
		}

		Date createdDate = (Date)attributes.get("createdDate");

		if (createdDate != null) {
			setCreatedDate(createdDate);
		}

		Integer createdBy = (Integer)attributes.get("createdBy");

		if (createdBy != null) {
			setCreatedBy(createdBy);
		}

		String toMailIds = (String)attributes.get("toMailIds");

		if (toMailIds != null) {
			setToMailIds(toMailIds);
		}

		Integer notificationCategoryId = (Integer)attributes.get(
				"notificationCategoryId");

		if (notificationCategoryId != null) {
			setNotificationCategoryId(notificationCategoryId);
		}

		String notificationModule = (String)attributes.get("notificationModule");

		if (notificationModule != null) {
			setNotificationModule(notificationModule);
		}

		String body = (String)attributes.get("body");

		if (body != null) {
			setBody(body);
		}

		String fromMailId = (String)attributes.get("fromMailId");

		if (fromMailId != null) {
			setFromMailId(fromMailId);
		}

		String ccMailIds = (String)attributes.get("ccMailIds");

		if (ccMailIds != null) {
			setCcMailIds(ccMailIds);
		}

		Integer versionNo = (Integer)attributes.get("versionNo");

		if (versionNo != null) {
			setVersionNo(versionNo);
		}

		Integer modifiedBy = (Integer)attributes.get("modifiedBy");

		if (modifiedBy != null) {
			setModifiedBy(modifiedBy);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Integer mailNotificationSid = (Integer)attributes.get(
				"mailNotificationSid");

		if (mailNotificationSid != null) {
			setMailNotificationSid(mailNotificationSid);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new MailNotificationMasterWrapper((MailNotificationMaster)_mailNotificationMaster.clone());
	}

	@Override
	public int compareTo(MailNotificationMaster mailNotificationMaster) {
		return _mailNotificationMaster.compareTo(mailNotificationMaster);
	}

	/**
	* Returns the body of this mail notification master.
	*
	* @return the body of this mail notification master
	*/
	@Override
	public java.lang.String getBody() {
		return _mailNotificationMaster.getBody();
	}

	/**
	* Returns the cc mail IDs of this mail notification master.
	*
	* @return the cc mail IDs of this mail notification master
	*/
	@Override
	public java.lang.String getCcMailIds() {
		return _mailNotificationMaster.getCcMailIds();
	}

	/**
	* Returns the created by of this mail notification master.
	*
	* @return the created by of this mail notification master
	*/
	@Override
	public int getCreatedBy() {
		return _mailNotificationMaster.getCreatedBy();
	}

	/**
	* Returns the created date of this mail notification master.
	*
	* @return the created date of this mail notification master
	*/
	@Override
	public Date getCreatedDate() {
		return _mailNotificationMaster.getCreatedDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _mailNotificationMaster.getExpandoBridge();
	}

	/**
	* Returns the from mail ID of this mail notification master.
	*
	* @return the from mail ID of this mail notification master
	*/
	@Override
	public java.lang.String getFromMailId() {
		return _mailNotificationMaster.getFromMailId();
	}

	/**
	* Returns the mail notification sid of this mail notification master.
	*
	* @return the mail notification sid of this mail notification master
	*/
	@Override
	public int getMailNotificationSid() {
		return _mailNotificationMaster.getMailNotificationSid();
	}

	/**
	* Returns the modified by of this mail notification master.
	*
	* @return the modified by of this mail notification master
	*/
	@Override
	public int getModifiedBy() {
		return _mailNotificationMaster.getModifiedBy();
	}

	/**
	* Returns the modified date of this mail notification master.
	*
	* @return the modified date of this mail notification master
	*/
	@Override
	public Date getModifiedDate() {
		return _mailNotificationMaster.getModifiedDate();
	}

	/**
	* Returns the notification category ID of this mail notification master.
	*
	* @return the notification category ID of this mail notification master
	*/
	@Override
	public int getNotificationCategoryId() {
		return _mailNotificationMaster.getNotificationCategoryId();
	}

	/**
	* Returns the notification module of this mail notification master.
	*
	* @return the notification module of this mail notification master
	*/
	@Override
	public java.lang.String getNotificationModule() {
		return _mailNotificationMaster.getNotificationModule();
	}

	/**
	* Returns the primary key of this mail notification master.
	*
	* @return the primary key of this mail notification master
	*/
	@Override
	public int getPrimaryKey() {
		return _mailNotificationMaster.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _mailNotificationMaster.getPrimaryKeyObj();
	}

	/**
	* Returns the subject of this mail notification master.
	*
	* @return the subject of this mail notification master
	*/
	@Override
	public java.lang.String getSubject() {
		return _mailNotificationMaster.getSubject();
	}

	/**
	* Returns the to mail IDs of this mail notification master.
	*
	* @return the to mail IDs of this mail notification master
	*/
	@Override
	public java.lang.String getToMailIds() {
		return _mailNotificationMaster.getToMailIds();
	}

	/**
	* Returns the version no of this mail notification master.
	*
	* @return the version no of this mail notification master
	*/
	@Override
	public int getVersionNo() {
		return _mailNotificationMaster.getVersionNo();
	}

	@Override
	public int hashCode() {
		return _mailNotificationMaster.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _mailNotificationMaster.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _mailNotificationMaster.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _mailNotificationMaster.isNew();
	}

	@Override
	public void persist() {
		_mailNotificationMaster.persist();
	}

	/**
	* Sets the body of this mail notification master.
	*
	* @param body the body of this mail notification master
	*/
	@Override
	public void setBody(java.lang.String body) {
		_mailNotificationMaster.setBody(body);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_mailNotificationMaster.setCachedModel(cachedModel);
	}

	/**
	* Sets the cc mail IDs of this mail notification master.
	*
	* @param ccMailIds the cc mail IDs of this mail notification master
	*/
	@Override
	public void setCcMailIds(java.lang.String ccMailIds) {
		_mailNotificationMaster.setCcMailIds(ccMailIds);
	}

	/**
	* Sets the created by of this mail notification master.
	*
	* @param createdBy the created by of this mail notification master
	*/
	@Override
	public void setCreatedBy(int createdBy) {
		_mailNotificationMaster.setCreatedBy(createdBy);
	}

	/**
	* Sets the created date of this mail notification master.
	*
	* @param createdDate the created date of this mail notification master
	*/
	@Override
	public void setCreatedDate(Date createdDate) {
		_mailNotificationMaster.setCreatedDate(createdDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_mailNotificationMaster.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_mailNotificationMaster.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_mailNotificationMaster.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the from mail ID of this mail notification master.
	*
	* @param fromMailId the from mail ID of this mail notification master
	*/
	@Override
	public void setFromMailId(java.lang.String fromMailId) {
		_mailNotificationMaster.setFromMailId(fromMailId);
	}

	/**
	* Sets the mail notification sid of this mail notification master.
	*
	* @param mailNotificationSid the mail notification sid of this mail notification master
	*/
	@Override
	public void setMailNotificationSid(int mailNotificationSid) {
		_mailNotificationMaster.setMailNotificationSid(mailNotificationSid);
	}

	/**
	* Sets the modified by of this mail notification master.
	*
	* @param modifiedBy the modified by of this mail notification master
	*/
	@Override
	public void setModifiedBy(int modifiedBy) {
		_mailNotificationMaster.setModifiedBy(modifiedBy);
	}

	/**
	* Sets the modified date of this mail notification master.
	*
	* @param modifiedDate the modified date of this mail notification master
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_mailNotificationMaster.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_mailNotificationMaster.setNew(n);
	}

	/**
	* Sets the notification category ID of this mail notification master.
	*
	* @param notificationCategoryId the notification category ID of this mail notification master
	*/
	@Override
	public void setNotificationCategoryId(int notificationCategoryId) {
		_mailNotificationMaster.setNotificationCategoryId(notificationCategoryId);
	}

	/**
	* Sets the notification module of this mail notification master.
	*
	* @param notificationModule the notification module of this mail notification master
	*/
	@Override
	public void setNotificationModule(java.lang.String notificationModule) {
		_mailNotificationMaster.setNotificationModule(notificationModule);
	}

	/**
	* Sets the primary key of this mail notification master.
	*
	* @param primaryKey the primary key of this mail notification master
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_mailNotificationMaster.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_mailNotificationMaster.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the subject of this mail notification master.
	*
	* @param subject the subject of this mail notification master
	*/
	@Override
	public void setSubject(java.lang.String subject) {
		_mailNotificationMaster.setSubject(subject);
	}

	/**
	* Sets the to mail IDs of this mail notification master.
	*
	* @param toMailIds the to mail IDs of this mail notification master
	*/
	@Override
	public void setToMailIds(java.lang.String toMailIds) {
		_mailNotificationMaster.setToMailIds(toMailIds);
	}

	/**
	* Sets the version no of this mail notification master.
	*
	* @param versionNo the version no of this mail notification master
	*/
	@Override
	public void setVersionNo(int versionNo) {
		_mailNotificationMaster.setVersionNo(versionNo);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<MailNotificationMaster> toCacheModel() {
		return _mailNotificationMaster.toCacheModel();
	}

	@Override
	public MailNotificationMaster toEscapedModel() {
		return new MailNotificationMasterWrapper(_mailNotificationMaster.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _mailNotificationMaster.toString();
	}

	@Override
	public MailNotificationMaster toUnescapedModel() {
		return new MailNotificationMasterWrapper(_mailNotificationMaster.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _mailNotificationMaster.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof MailNotificationMasterWrapper)) {
			return false;
		}

		MailNotificationMasterWrapper mailNotificationMasterWrapper = (MailNotificationMasterWrapper)obj;

		if (Objects.equals(_mailNotificationMaster,
					mailNotificationMasterWrapper._mailNotificationMaster)) {
			return true;
		}

		return false;
	}

	@Override
	public MailNotificationMaster getWrappedModel() {
		return _mailNotificationMaster;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _mailNotificationMaster.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _mailNotificationMaster.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_mailNotificationMaster.resetOriginalValues();
	}

	private final MailNotificationMaster _mailNotificationMaster;
}