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

import com.stpl.app.model.ImtdRsContractDetailsFr;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing ImtdRsContractDetailsFr in entity cache.
 *
 * @author
 * @see ImtdRsContractDetailsFr
 * @generated
 */
@ProviderType
public class ImtdRsContractDetailsFrCacheModel implements CacheModel<ImtdRsContractDetailsFr>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ImtdRsContractDetailsFrCacheModel)) {
			return false;
		}

		ImtdRsContractDetailsFrCacheModel imtdRsContractDetailsFrCacheModel = (ImtdRsContractDetailsFrCacheModel)obj;

		if (imtdRsContractDetailsFrSid == imtdRsContractDetailsFrCacheModel.imtdRsContractDetailsFrSid) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, imtdRsContractDetailsFrSid);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(39);

		sb.append("{imtdRsContractDetailsFrSid=");
		sb.append(imtdRsContractDetailsFrSid);
		sb.append(", formulaMethodId=");
		sb.append(formulaMethodId);
		sb.append(", itemMasterSid=");
		sb.append(itemMasterSid);
		sb.append(", rsContractDetailsFrSid=");
		sb.append(rsContractDetailsFrSid);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", rsContractDetailsSid=");
		sb.append(rsContractDetailsSid);
		sb.append(", imtdItemPriceRebateDetailsSid=");
		sb.append(imtdItemPriceRebateDetailsSid);
		sb.append(", recordLockStatus=");
		sb.append(recordLockStatus);
		sb.append(", createdDate=");
		sb.append(createdDate);
		sb.append(", createdBy=");
		sb.append(createdBy);
		sb.append(", source=");
		sb.append(source);
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
		sb.append(", formulaId=");
		sb.append(formulaId);
		sb.append(", inboundStatus=");
		sb.append(inboundStatus);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ImtdRsContractDetailsFr toEntityModel() {
		ImtdRsContractDetailsFrImpl imtdRsContractDetailsFrImpl = new ImtdRsContractDetailsFrImpl();

		imtdRsContractDetailsFrImpl.setImtdRsContractDetailsFrSid(imtdRsContractDetailsFrSid);

		if (formulaMethodId == null) {
			imtdRsContractDetailsFrImpl.setFormulaMethodId(StringPool.BLANK);
		}
		else {
			imtdRsContractDetailsFrImpl.setFormulaMethodId(formulaMethodId);
		}

		imtdRsContractDetailsFrImpl.setItemMasterSid(itemMasterSid);
		imtdRsContractDetailsFrImpl.setRsContractDetailsFrSid(rsContractDetailsFrSid);

		if (modifiedDate == Long.MIN_VALUE) {
			imtdRsContractDetailsFrImpl.setModifiedDate(null);
		}
		else {
			imtdRsContractDetailsFrImpl.setModifiedDate(new Date(modifiedDate));
		}

		imtdRsContractDetailsFrImpl.setRsContractDetailsSid(rsContractDetailsSid);
		imtdRsContractDetailsFrImpl.setImtdItemPriceRebateDetailsSid(imtdItemPriceRebateDetailsSid);
		imtdRsContractDetailsFrImpl.setRecordLockStatus(recordLockStatus);

		if (createdDate == Long.MIN_VALUE) {
			imtdRsContractDetailsFrImpl.setCreatedDate(null);
		}
		else {
			imtdRsContractDetailsFrImpl.setCreatedDate(new Date(createdDate));
		}

		imtdRsContractDetailsFrImpl.setCreatedBy(createdBy);

		if (source == null) {
			imtdRsContractDetailsFrImpl.setSource(StringPool.BLANK);
		}
		else {
			imtdRsContractDetailsFrImpl.setSource(source);
		}

		if (batchId == null) {
			imtdRsContractDetailsFrImpl.setBatchId(StringPool.BLANK);
		}
		else {
			imtdRsContractDetailsFrImpl.setBatchId(batchId);
		}

		if (imtdCreatedDate == Long.MIN_VALUE) {
			imtdRsContractDetailsFrImpl.setImtdCreatedDate(null);
		}
		else {
			imtdRsContractDetailsFrImpl.setImtdCreatedDate(new Date(
					imtdCreatedDate));
		}

		if (sessionId == null) {
			imtdRsContractDetailsFrImpl.setSessionId(StringPool.BLANK);
		}
		else {
			imtdRsContractDetailsFrImpl.setSessionId(sessionId);
		}

		if (usersId == null) {
			imtdRsContractDetailsFrImpl.setUsersId(StringPool.BLANK);
		}
		else {
			imtdRsContractDetailsFrImpl.setUsersId(usersId);
		}

		if (operation == null) {
			imtdRsContractDetailsFrImpl.setOperation(StringPool.BLANK);
		}
		else {
			imtdRsContractDetailsFrImpl.setOperation(operation);
		}

		imtdRsContractDetailsFrImpl.setModifiedBy(modifiedBy);
		imtdRsContractDetailsFrImpl.setFormulaId(formulaId);

		if (inboundStatus == null) {
			imtdRsContractDetailsFrImpl.setInboundStatus(StringPool.BLANK);
		}
		else {
			imtdRsContractDetailsFrImpl.setInboundStatus(inboundStatus);
		}

		imtdRsContractDetailsFrImpl.resetOriginalValues();

		return imtdRsContractDetailsFrImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		imtdRsContractDetailsFrSid = objectInput.readInt();
		formulaMethodId = objectInput.readUTF();

		itemMasterSid = objectInput.readInt();

		rsContractDetailsFrSid = objectInput.readInt();
		modifiedDate = objectInput.readLong();

		rsContractDetailsSid = objectInput.readInt();

		imtdItemPriceRebateDetailsSid = objectInput.readInt();

		recordLockStatus = objectInput.readBoolean();
		createdDate = objectInput.readLong();

		createdBy = objectInput.readInt();
		source = objectInput.readUTF();
		batchId = objectInput.readUTF();
		imtdCreatedDate = objectInput.readLong();
		sessionId = objectInput.readUTF();
		usersId = objectInput.readUTF();
		operation = objectInput.readUTF();

		modifiedBy = objectInput.readInt();

		formulaId = objectInput.readInt();
		inboundStatus = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeInt(imtdRsContractDetailsFrSid);

		if (formulaMethodId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(formulaMethodId);
		}

		objectOutput.writeInt(itemMasterSid);

		objectOutput.writeInt(rsContractDetailsFrSid);
		objectOutput.writeLong(modifiedDate);

		objectOutput.writeInt(rsContractDetailsSid);

		objectOutput.writeInt(imtdItemPriceRebateDetailsSid);

		objectOutput.writeBoolean(recordLockStatus);
		objectOutput.writeLong(createdDate);

		objectOutput.writeInt(createdBy);

		if (source == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(source);
		}

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

		objectOutput.writeInt(formulaId);

		if (inboundStatus == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(inboundStatus);
		}
	}

	public int imtdRsContractDetailsFrSid;
	public String formulaMethodId;
	public int itemMasterSid;
	public int rsContractDetailsFrSid;
	public long modifiedDate;
	public int rsContractDetailsSid;
	public int imtdItemPriceRebateDetailsSid;
	public boolean recordLockStatus;
	public long createdDate;
	public int createdBy;
	public String source;
	public String batchId;
	public long imtdCreatedDate;
	public String sessionId;
	public String usersId;
	public String operation;
	public int modifiedBy;
	public int formulaId;
	public String inboundStatus;
}