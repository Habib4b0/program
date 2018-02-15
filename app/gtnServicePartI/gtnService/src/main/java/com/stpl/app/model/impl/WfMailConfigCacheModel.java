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

import com.stpl.app.model.WfMailConfig;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing WfMailConfig in entity cache.
 *
 * @author
 * @see WfMailConfig
 * @generated
 */
@ProviderType
public class WfMailConfigCacheModel implements CacheModel<WfMailConfig>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof WfMailConfigCacheModel)) {
			return false;
		}

		WfMailConfigCacheModel wfMailConfigCacheModel = (WfMailConfigCacheModel)obj;

		if (wfMailConfigSid == wfMailConfigCacheModel.wfMailConfigSid) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, wfMailConfigSid);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(25);

		sb.append("{smtpFlag=");
		sb.append(smtpFlag);
		sb.append(", createdBy=");
		sb.append(createdBy);
		sb.append(", emailAddress=");
		sb.append(emailAddress);
		sb.append(", password=");
		sb.append(password);
		sb.append(", modifiedBy=");
		sb.append(modifiedBy);
		sb.append(", wfMailConfigSid=");
		sb.append(wfMailConfigSid);
		sb.append(", hostName=");
		sb.append(hostName);
		sb.append(", createdDate=");
		sb.append(createdDate);
		sb.append(", portNumber=");
		sb.append(portNumber);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", inboundStatus=");
		sb.append(inboundStatus);
		sb.append(", testMailAddress=");
		sb.append(testMailAddress);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public WfMailConfig toEntityModel() {
		WfMailConfigImpl wfMailConfigImpl = new WfMailConfigImpl();

		if (smtpFlag == null) {
			wfMailConfigImpl.setSmtpFlag(StringPool.BLANK);
		}
		else {
			wfMailConfigImpl.setSmtpFlag(smtpFlag);
		}

		wfMailConfigImpl.setCreatedBy(createdBy);

		if (emailAddress == null) {
			wfMailConfigImpl.setEmailAddress(StringPool.BLANK);
		}
		else {
			wfMailConfigImpl.setEmailAddress(emailAddress);
		}

		if (password == null) {
			wfMailConfigImpl.setPassword(StringPool.BLANK);
		}
		else {
			wfMailConfigImpl.setPassword(password);
		}

		wfMailConfigImpl.setModifiedBy(modifiedBy);
		wfMailConfigImpl.setWfMailConfigSid(wfMailConfigSid);

		if (hostName == null) {
			wfMailConfigImpl.setHostName(StringPool.BLANK);
		}
		else {
			wfMailConfigImpl.setHostName(hostName);
		}

		if (createdDate == Long.MIN_VALUE) {
			wfMailConfigImpl.setCreatedDate(null);
		}
		else {
			wfMailConfigImpl.setCreatedDate(new Date(createdDate));
		}

		if (portNumber == null) {
			wfMailConfigImpl.setPortNumber(StringPool.BLANK);
		}
		else {
			wfMailConfigImpl.setPortNumber(portNumber);
		}

		if (modifiedDate == Long.MIN_VALUE) {
			wfMailConfigImpl.setModifiedDate(null);
		}
		else {
			wfMailConfigImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (inboundStatus == null) {
			wfMailConfigImpl.setInboundStatus(StringPool.BLANK);
		}
		else {
			wfMailConfigImpl.setInboundStatus(inboundStatus);
		}

		if (testMailAddress == null) {
			wfMailConfigImpl.setTestMailAddress(StringPool.BLANK);
		}
		else {
			wfMailConfigImpl.setTestMailAddress(testMailAddress);
		}

		wfMailConfigImpl.resetOriginalValues();

		return wfMailConfigImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		smtpFlag = objectInput.readUTF();

		createdBy = objectInput.readInt();
		emailAddress = objectInput.readUTF();
		password = objectInput.readUTF();

		modifiedBy = objectInput.readInt();

		wfMailConfigSid = objectInput.readInt();
		hostName = objectInput.readUTF();
		createdDate = objectInput.readLong();
		portNumber = objectInput.readUTF();
		modifiedDate = objectInput.readLong();
		inboundStatus = objectInput.readUTF();
		testMailAddress = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		if (smtpFlag == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(smtpFlag);
		}

		objectOutput.writeInt(createdBy);

		if (emailAddress == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(emailAddress);
		}

		if (password == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(password);
		}

		objectOutput.writeInt(modifiedBy);

		objectOutput.writeInt(wfMailConfigSid);

		if (hostName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(hostName);
		}

		objectOutput.writeLong(createdDate);

		if (portNumber == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(portNumber);
		}

		objectOutput.writeLong(modifiedDate);

		if (inboundStatus == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(inboundStatus);
		}

		if (testMailAddress == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(testMailAddress);
		}
	}

	public String smtpFlag;
	public int createdBy;
	public String emailAddress;
	public String password;
	public int modifiedBy;
	public int wfMailConfigSid;
	public String hostName;
	public long createdDate;
	public String portNumber;
	public long modifiedDate;
	public String inboundStatus;
	public String testMailAddress;
}