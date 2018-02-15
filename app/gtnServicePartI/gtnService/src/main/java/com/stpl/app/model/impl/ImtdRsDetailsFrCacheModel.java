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

import com.stpl.app.model.ImtdRsDetailsFr;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing ImtdRsDetailsFr in entity cache.
 *
 * @author
 * @see ImtdRsDetailsFr
 * @generated
 */
@ProviderType
public class ImtdRsDetailsFrCacheModel implements CacheModel<ImtdRsDetailsFr>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ImtdRsDetailsFrCacheModel)) {
			return false;
		}

		ImtdRsDetailsFrCacheModel imtdRsDetailsFrCacheModel = (ImtdRsDetailsFrCacheModel)obj;

		if (imtdRsDetailsFrSid == imtdRsDetailsFrCacheModel.imtdRsDetailsFrSid) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, imtdRsDetailsFrSid);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(39);

		sb.append("{formulaMethodId=");
		sb.append(formulaMethodId);
		sb.append(", itemMasterSid=");
		sb.append(itemMasterSid);
		sb.append(", imtdRsDetailsSid=");
		sb.append(imtdRsDetailsSid);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", rsDetailsFrSid=");
		sb.append(rsDetailsFrSid);
		sb.append(", recordLockStatus=");
		sb.append(recordLockStatus);
		sb.append(", createdDate=");
		sb.append(createdDate);
		sb.append(", createdBy=");
		sb.append(createdBy);
		sb.append(", source=");
		sb.append(source);
		sb.append(", imtdRsDetailsFrSid=");
		sb.append(imtdRsDetailsFrSid);
		sb.append(", batchId=");
		sb.append(batchId);
		sb.append(", imtdCreatedDate=");
		sb.append(imtdCreatedDate);
		sb.append(", sessionId=");
		sb.append(sessionId);
		sb.append(", usersId=");
		sb.append(usersId);
		sb.append(", operation=");
		sb.append(operation);
		sb.append(", modifiedBy=");
		sb.append(modifiedBy);
		sb.append(", rsDetailsSid=");
		sb.append(rsDetailsSid);
		sb.append(", formulaId=");
		sb.append(formulaId);
		sb.append(", inboundStatus=");
		sb.append(inboundStatus);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ImtdRsDetailsFr toEntityModel() {
		ImtdRsDetailsFrImpl imtdRsDetailsFrImpl = new ImtdRsDetailsFrImpl();

		if (formulaMethodId == null) {
			imtdRsDetailsFrImpl.setFormulaMethodId(StringPool.BLANK);
		}
		else {
			imtdRsDetailsFrImpl.setFormulaMethodId(formulaMethodId);
		}

		imtdRsDetailsFrImpl.setItemMasterSid(itemMasterSid);
		imtdRsDetailsFrImpl.setImtdRsDetailsSid(imtdRsDetailsSid);

		if (modifiedDate == Long.MIN_VALUE) {
			imtdRsDetailsFrImpl.setModifiedDate(null);
		}
		else {
			imtdRsDetailsFrImpl.setModifiedDate(new Date(modifiedDate));
		}

		imtdRsDetailsFrImpl.setRsDetailsFrSid(rsDetailsFrSid);
		imtdRsDetailsFrImpl.setRecordLockStatus(recordLockStatus);

		if (createdDate == Long.MIN_VALUE) {
			imtdRsDetailsFrImpl.setCreatedDate(null);
		}
		else {
			imtdRsDetailsFrImpl.setCreatedDate(new Date(createdDate));
		}

		imtdRsDetailsFrImpl.setCreatedBy(createdBy);

		if (source == null) {
			imtdRsDetailsFrImpl.setSource(StringPool.BLANK);
		}
		else {
			imtdRsDetailsFrImpl.setSource(source);
		}

		imtdRsDetailsFrImpl.setImtdRsDetailsFrSid(imtdRsDetailsFrSid);

		if (batchId == null) {
			imtdRsDetailsFrImpl.setBatchId(StringPool.BLANK);
		}
		else {
			imtdRsDetailsFrImpl.setBatchId(batchId);
		}

		if (imtdCreatedDate == Long.MIN_VALUE) {
			imtdRsDetailsFrImpl.setImtdCreatedDate(null);
		}
		else {
			imtdRsDetailsFrImpl.setImtdCreatedDate(new Date(imtdCreatedDate));
		}

		if (sessionId == null) {
			imtdRsDetailsFrImpl.setSessionId(StringPool.BLANK);
		}
		else {
			imtdRsDetailsFrImpl.setSessionId(sessionId);
		}

		if (usersId == null) {
			imtdRsDetailsFrImpl.setUsersId(StringPool.BLANK);
		}
		else {
			imtdRsDetailsFrImpl.setUsersId(usersId);
		}

		if (operation == null) {
			imtdRsDetailsFrImpl.setOperation(StringPool.BLANK);
		}
		else {
			imtdRsDetailsFrImpl.setOperation(operation);
		}

		imtdRsDetailsFrImpl.setModifiedBy(modifiedBy);
		imtdRsDetailsFrImpl.setRsDetailsSid(rsDetailsSid);
		imtdRsDetailsFrImpl.setFormulaId(formulaId);

		if (inboundStatus == null) {
			imtdRsDetailsFrImpl.setInboundStatus(StringPool.BLANK);
		}
		else {
			imtdRsDetailsFrImpl.setInboundStatus(inboundStatus);
		}

		imtdRsDetailsFrImpl.resetOriginalValues();

		return imtdRsDetailsFrImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		formulaMethodId = objectInput.readUTF();

		itemMasterSid = objectInput.readInt();

		imtdRsDetailsSid = objectInput.readInt();
		modifiedDate = objectInput.readLong();

		rsDetailsFrSid = objectInput.readInt();

		recordLockStatus = objectInput.readBoolean();
		createdDate = objectInput.readLong();

		createdBy = objectInput.readInt();
		source = objectInput.readUTF();

		imtdRsDetailsFrSid = objectInput.readInt();
		batchId = objectInput.readUTF();
		imtdCreatedDate = objectInput.readLong();
		sessionId = objectInput.readUTF();
		usersId = objectInput.readUTF();
		operation = objectInput.readUTF();

		modifiedBy = objectInput.readInt();

		rsDetailsSid = objectInput.readInt();

		formulaId = objectInput.readInt();
		inboundStatus = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		if (formulaMethodId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(formulaMethodId);
		}

		objectOutput.writeInt(itemMasterSid);

		objectOutput.writeInt(imtdRsDetailsSid);
		objectOutput.writeLong(modifiedDate);

		objectOutput.writeInt(rsDetailsFrSid);

		objectOutput.writeBoolean(recordLockStatus);
		objectOutput.writeLong(createdDate);

		objectOutput.writeInt(createdBy);

		if (source == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(source);
		}

		objectOutput.writeInt(imtdRsDetailsFrSid);

		if (batchId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(batchId);
		}

		objectOutput.writeLong(imtdCreatedDate);

		if (sessionId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(sessionId);
		}

		if (usersId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(usersId);
		}

		if (operation == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(operation);
		}

		objectOutput.writeInt(modifiedBy);

		objectOutput.writeInt(rsDetailsSid);

		objectOutput.writeInt(formulaId);

		if (inboundStatus == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(inboundStatus);
		}
	}

	public String formulaMethodId;
	public int itemMasterSid;
	public int imtdRsDetailsSid;
	public long modifiedDate;
	public int rsDetailsFrSid;
	public boolean recordLockStatus;
	public long createdDate;
	public int createdBy;
	public String source;
	public int imtdRsDetailsFrSid;
	public String batchId;
	public long imtdCreatedDate;
	public String sessionId;
	public String usersId;
	public String operation;
	public int modifiedBy;
	public int rsDetailsSid;
	public int formulaId;
	public String inboundStatus;
}