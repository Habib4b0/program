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

package com.stpl.app.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import com.stpl.app.model.MailNotificationMaster;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing MailNotificationMaster in entity cache.
 *
 * @author
 * @see MailNotificationMaster
 * @generated
 */
@ProviderType
public class MailNotificationMasterCacheModel implements CacheModel<MailNotificationMaster>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof MailNotificationMasterCacheModel)) {
			return false;
		}

		MailNotificationMasterCacheModel mailNotificationMasterCacheModel = (MailNotificationMasterCacheModel)obj;

		if (mailNotificationSid == mailNotificationMasterCacheModel.mailNotificationSid) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, mailNotificationSid);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(27);

		sb.append("{subject=");
		sb.append(subject);
		sb.append(", createdDate=");
		sb.append(createdDate);
		sb.append(", createdBy=");
		sb.append(createdBy);
		sb.append(", toMailIds=");
		sb.append(toMailIds);
		sb.append(", notificationCategoryId=");
		sb.append(notificationCategoryId);
		sb.append(", notificationModule=");
		sb.append(notificationModule);
		sb.append(", body=");
		sb.append(body);
		sb.append(", fromMailId=");
		sb.append(fromMailId);
		sb.append(", ccMailIds=");
		sb.append(ccMailIds);
		sb.append(", versionNo=");
		sb.append(versionNo);
		sb.append(", modifiedBy=");
		sb.append(modifiedBy);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", mailNotificationSid=");
		sb.append(mailNotificationSid);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public MailNotificationMaster toEntityModel() {
		MailNotificationMasterImpl mailNotificationMasterImpl = new MailNotificationMasterImpl();

		if (subject == null) {
			mailNotificationMasterImpl.setSubject(StringPool.BLANK);
		}
		else {
			mailNotificationMasterImpl.setSubject(subject);
		}

		if (createdDate == Long.MIN_VALUE) {
			mailNotificationMasterImpl.setCreatedDate(null);
		}
		else {
			mailNotificationMasterImpl.setCreatedDate(new Date(createdDate));
		}

		mailNotificationMasterImpl.setCreatedBy(createdBy);

		if (toMailIds == null) {
			mailNotificationMasterImpl.setToMailIds(StringPool.BLANK);
		}
		else {
			mailNotificationMasterImpl.setToMailIds(toMailIds);
		}

		mailNotificationMasterImpl.setNotificationCategoryId(notificationCategoryId);

		if (notificationModule == null) {
			mailNotificationMasterImpl.setNotificationModule(StringPool.BLANK);
		}
		else {
			mailNotificationMasterImpl.setNotificationModule(notificationModule);
		}

		if (body == null) {
			mailNotificationMasterImpl.setBody(StringPool.BLANK);
		}
		else {
			mailNotificationMasterImpl.setBody(body);
		}

		if (fromMailId == null) {
			mailNotificationMasterImpl.setFromMailId(StringPool.BLANK);
		}
		else {
			mailNotificationMasterImpl.setFromMailId(fromMailId);
		}

		if (ccMailIds == null) {
			mailNotificationMasterImpl.setCcMailIds(StringPool.BLANK);
		}
		else {
			mailNotificationMasterImpl.setCcMailIds(ccMailIds);
		}

		mailNotificationMasterImpl.setVersionNo(versionNo);
		mailNotificationMasterImpl.setModifiedBy(modifiedBy);

		if (modifiedDate == Long.MIN_VALUE) {
			mailNotificationMasterImpl.setModifiedDate(null);
		}
		else {
			mailNotificationMasterImpl.setModifiedDate(new Date(modifiedDate));
		}

		mailNotificationMasterImpl.setMailNotificationSid(mailNotificationSid);

		mailNotificationMasterImpl.resetOriginalValues();

		return mailNotificationMasterImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		subject = objectInput.readUTF();
		createdDate = objectInput.readLong();

		createdBy = objectInput.readInt();
		toMailIds = objectInput.readUTF();

		notificationCategoryId = objectInput.readInt();
		notificationModule = objectInput.readUTF();
		body = objectInput.readUTF();
		fromMailId = objectInput.readUTF();
		ccMailIds = objectInput.readUTF();

		versionNo = objectInput.readInt();

		modifiedBy = objectInput.readInt();
		modifiedDate = objectInput.readLong();

		mailNotificationSid = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		if (subject == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(subject);
		}

		objectOutput.writeLong(createdDate);

		objectOutput.writeInt(createdBy);

		if (toMailIds == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(toMailIds);
		}

		objectOutput.writeInt(notificationCategoryId);

		if (notificationModule == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(notificationModule);
		}

		if (body == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(body);
		}

		if (fromMailId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(fromMailId);
		}

		if (ccMailIds == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(ccMailIds);
		}

		objectOutput.writeInt(versionNo);

		objectOutput.writeInt(modifiedBy);
		objectOutput.writeLong(modifiedDate);

		objectOutput.writeInt(mailNotificationSid);
	}

	public String subject;
	public long createdDate;
	public int createdBy;
	public String toMailIds;
	public int notificationCategoryId;
	public String notificationModule;
	public String body;
	public String fromMailId;
	public String ccMailIds;
	public int versionNo;
	public int modifiedBy;
	public long modifiedDate;
	public int mailNotificationSid;
}