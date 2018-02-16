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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author
 * @generated
 */
@ProviderType
public class MailNotificationMasterSoap implements Serializable {
	public static MailNotificationMasterSoap toSoapModel(
		MailNotificationMaster model) {
		MailNotificationMasterSoap soapModel = new MailNotificationMasterSoap();

		soapModel.setSubject(model.getSubject());
		soapModel.setCreatedDate(model.getCreatedDate());
		soapModel.setCreatedBy(model.getCreatedBy());
		soapModel.setToMailIds(model.getToMailIds());
		soapModel.setNotificationCategoryId(model.getNotificationCategoryId());
		soapModel.setNotificationModule(model.getNotificationModule());
		soapModel.setBody(model.getBody());
		soapModel.setFromMailId(model.getFromMailId());
		soapModel.setCcMailIds(model.getCcMailIds());
		soapModel.setVersionNo(model.getVersionNo());
		soapModel.setModifiedBy(model.getModifiedBy());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setMailNotificationSid(model.getMailNotificationSid());

		return soapModel;
	}

	public static MailNotificationMasterSoap[] toSoapModels(
		MailNotificationMaster[] models) {
		MailNotificationMasterSoap[] soapModels = new MailNotificationMasterSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static MailNotificationMasterSoap[][] toSoapModels(
		MailNotificationMaster[][] models) {
		MailNotificationMasterSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new MailNotificationMasterSoap[models.length][models[0].length];
		}
		else {
			soapModels = new MailNotificationMasterSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static MailNotificationMasterSoap[] toSoapModels(
		List<MailNotificationMaster> models) {
		List<MailNotificationMasterSoap> soapModels = new ArrayList<MailNotificationMasterSoap>(models.size());

		for (MailNotificationMaster model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new MailNotificationMasterSoap[soapModels.size()]);
	}

	public MailNotificationMasterSoap() {
	}

	public int getPrimaryKey() {
		return _mailNotificationSid;
	}

	public void setPrimaryKey(int pk) {
		setMailNotificationSid(pk);
	}

	public String getSubject() {
		return _subject;
	}

	public void setSubject(String subject) {
		_subject = subject;
	}

	public Date getCreatedDate() {
		return _createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		_createdDate = createdDate;
	}

	public int getCreatedBy() {
		return _createdBy;
	}

	public void setCreatedBy(int createdBy) {
		_createdBy = createdBy;
	}

	public String getToMailIds() {
		return _toMailIds;
	}

	public void setToMailIds(String toMailIds) {
		_toMailIds = toMailIds;
	}

	public int getNotificationCategoryId() {
		return _notificationCategoryId;
	}

	public void setNotificationCategoryId(int notificationCategoryId) {
		_notificationCategoryId = notificationCategoryId;
	}

	public String getNotificationModule() {
		return _notificationModule;
	}

	public void setNotificationModule(String notificationModule) {
		_notificationModule = notificationModule;
	}

	public String getBody() {
		return _body;
	}

	public void setBody(String body) {
		_body = body;
	}

	public String getFromMailId() {
		return _fromMailId;
	}

	public void setFromMailId(String fromMailId) {
		_fromMailId = fromMailId;
	}

	public String getCcMailIds() {
		return _ccMailIds;
	}

	public void setCcMailIds(String ccMailIds) {
		_ccMailIds = ccMailIds;
	}

	public int getVersionNo() {
		return _versionNo;
	}

	public void setVersionNo(int versionNo) {
		_versionNo = versionNo;
	}

	public int getModifiedBy() {
		return _modifiedBy;
	}

	public void setModifiedBy(int modifiedBy) {
		_modifiedBy = modifiedBy;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public int getMailNotificationSid() {
		return _mailNotificationSid;
	}

	public void setMailNotificationSid(int mailNotificationSid) {
		_mailNotificationSid = mailNotificationSid;
	}

	private String _subject;
	private Date _createdDate;
	private int _createdBy;
	private String _toMailIds;
	private int _notificationCategoryId;
	private String _notificationModule;
	private String _body;
	private String _fromMailId;
	private String _ccMailIds;
	private int _versionNo;
	private int _modifiedBy;
	private Date _modifiedDate;
	private int _mailNotificationSid;
}