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

import com.stpl.app.model.GcmCompanyLink;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing GcmCompanyLink in entity cache.
 *
 * @author
 * @see GcmCompanyLink
 * @generated
 */
@ProviderType
public class GcmCompanyLinkCacheModel implements CacheModel<GcmCompanyLink>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof GcmCompanyLinkCacheModel)) {
			return false;
		}

		GcmCompanyLinkCacheModel gcmCompanyLinkCacheModel = (GcmCompanyLinkCacheModel)obj;

		if (gcmCompanyLinkSid == gcmCompanyLinkCacheModel.gcmCompanyLinkSid) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, gcmCompanyLinkSid);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(19);

		sb.append("{checkRecord=");
		sb.append(checkRecord);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", companyNo=");
		sb.append(companyNo);
		sb.append(", companyStringId=");
		sb.append(companyStringId);
		sb.append(", gcmCompanyLinkSid=");
		sb.append(gcmCompanyLinkSid);
		sb.append(", sessionId=");
		sb.append(sessionId);
		sb.append(", companyName=");
		sb.append(companyName);
		sb.append(", linkId=");
		sb.append(linkId);
		sb.append(", companyMasterSid=");
		sb.append(companyMasterSid);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public GcmCompanyLink toEntityModel() {
		GcmCompanyLinkImpl gcmCompanyLinkImpl = new GcmCompanyLinkImpl();

		gcmCompanyLinkImpl.setCheckRecord(checkRecord);
		gcmCompanyLinkImpl.setUserId(userId);

		if (companyNo == null) {
			gcmCompanyLinkImpl.setCompanyNo(StringPool.BLANK);
		}
		else {
			gcmCompanyLinkImpl.setCompanyNo(companyNo);
		}

		if (companyStringId == null) {
			gcmCompanyLinkImpl.setCompanyStringId(StringPool.BLANK);
		}
		else {
			gcmCompanyLinkImpl.setCompanyStringId(companyStringId);
		}

		gcmCompanyLinkImpl.setGcmCompanyLinkSid(gcmCompanyLinkSid);

		if (sessionId == null) {
			gcmCompanyLinkImpl.setSessionId(StringPool.BLANK);
		}
		else {
			gcmCompanyLinkImpl.setSessionId(sessionId);
		}

		if (companyName == null) {
			gcmCompanyLinkImpl.setCompanyName(StringPool.BLANK);
		}
		else {
			gcmCompanyLinkImpl.setCompanyName(companyName);
		}

		if (linkId == null) {
			gcmCompanyLinkImpl.setLinkId(StringPool.BLANK);
		}
		else {
			gcmCompanyLinkImpl.setLinkId(linkId);
		}

		gcmCompanyLinkImpl.setCompanyMasterSid(companyMasterSid);

		gcmCompanyLinkImpl.resetOriginalValues();

		return gcmCompanyLinkImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		checkRecord = objectInput.readBoolean();

		userId = objectInput.readInt();
		companyNo = objectInput.readUTF();
		companyStringId = objectInput.readUTF();

		gcmCompanyLinkSid = objectInput.readInt();
		sessionId = objectInput.readUTF();
		companyName = objectInput.readUTF();
		linkId = objectInput.readUTF();

		companyMasterSid = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeBoolean(checkRecord);

		objectOutput.writeInt(userId);

		if (companyNo == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(companyNo);
		}

		if (companyStringId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(companyStringId);
		}

		objectOutput.writeInt(gcmCompanyLinkSid);

		if (sessionId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(sessionId);
		}

		if (companyName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(companyName);
		}

		if (linkId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(linkId);
		}

		objectOutput.writeInt(companyMasterSid);
	}

	public boolean checkRecord;
	public int userId;
	public String companyNo;
	public String companyStringId;
	public int gcmCompanyLinkSid;
	public String sessionId;
	public String companyName;
	public String linkId;
	public int companyMasterSid;
}