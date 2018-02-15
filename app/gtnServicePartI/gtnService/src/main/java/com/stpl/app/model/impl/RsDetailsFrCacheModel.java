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

import com.stpl.app.model.RsDetailsFr;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing RsDetailsFr in entity cache.
 *
 * @author
 * @see RsDetailsFr
 * @generated
 */
@ProviderType
public class RsDetailsFrCacheModel implements CacheModel<RsDetailsFr>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof RsDetailsFrCacheModel)) {
			return false;
		}

		RsDetailsFrCacheModel rsDetailsFrCacheModel = (RsDetailsFrCacheModel)obj;

		if (rsDetailsFrSid == rsDetailsFrCacheModel.rsDetailsFrSid) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, rsDetailsFrSid);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(27);

		sb.append("{recordLockStatus=");
		sb.append(recordLockStatus);
		sb.append(", createdDate=");
		sb.append(createdDate);
		sb.append(", createdBy=");
		sb.append(createdBy);
		sb.append(", source=");
		sb.append(source);
		sb.append(", formulaMethodId=");
		sb.append(formulaMethodId);
		sb.append(", batchId=");
		sb.append(batchId);
		sb.append(", modifiedBy=");
		sb.append(modifiedBy);
		sb.append(", inboundStatus=");
		sb.append(inboundStatus);
		sb.append(", formulaId=");
		sb.append(formulaId);
		sb.append(", itemMasterSid=");
		sb.append(itemMasterSid);
		sb.append(", rsDetailsSid=");
		sb.append(rsDetailsSid);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", rsDetailsFrSid=");
		sb.append(rsDetailsFrSid);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public RsDetailsFr toEntityModel() {
		RsDetailsFrImpl rsDetailsFrImpl = new RsDetailsFrImpl();

		rsDetailsFrImpl.setRecordLockStatus(recordLockStatus);

		if (createdDate == Long.MIN_VALUE) {
			rsDetailsFrImpl.setCreatedDate(null);
		}
		else {
			rsDetailsFrImpl.setCreatedDate(new Date(createdDate));
		}

		rsDetailsFrImpl.setCreatedBy(createdBy);

		if (source == null) {
			rsDetailsFrImpl.setSource(StringPool.BLANK);
		}
		else {
			rsDetailsFrImpl.setSource(source);
		}

		if (formulaMethodId == null) {
			rsDetailsFrImpl.setFormulaMethodId(StringPool.BLANK);
		}
		else {
			rsDetailsFrImpl.setFormulaMethodId(formulaMethodId);
		}

		if (batchId == null) {
			rsDetailsFrImpl.setBatchId(StringPool.BLANK);
		}
		else {
			rsDetailsFrImpl.setBatchId(batchId);
		}

		rsDetailsFrImpl.setModifiedBy(modifiedBy);

		if (inboundStatus == null) {
			rsDetailsFrImpl.setInboundStatus(StringPool.BLANK);
		}
		else {
			rsDetailsFrImpl.setInboundStatus(inboundStatus);
		}

		rsDetailsFrImpl.setFormulaId(formulaId);
		rsDetailsFrImpl.setItemMasterSid(itemMasterSid);
		rsDetailsFrImpl.setRsDetailsSid(rsDetailsSid);

		if (modifiedDate == Long.MIN_VALUE) {
			rsDetailsFrImpl.setModifiedDate(null);
		}
		else {
			rsDetailsFrImpl.setModifiedDate(new Date(modifiedDate));
		}

		rsDetailsFrImpl.setRsDetailsFrSid(rsDetailsFrSid);

		rsDetailsFrImpl.resetOriginalValues();

		return rsDetailsFrImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		recordLockStatus = objectInput.readBoolean();
		createdDate = objectInput.readLong();

		createdBy = objectInput.readInt();
		source = objectInput.readUTF();
		formulaMethodId = objectInput.readUTF();
		batchId = objectInput.readUTF();

		modifiedBy = objectInput.readInt();
		inboundStatus = objectInput.readUTF();

		formulaId = objectInput.readInt();

		itemMasterSid = objectInput.readInt();

		rsDetailsSid = objectInput.readInt();
		modifiedDate = objectInput.readLong();

		rsDetailsFrSid = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeBoolean(recordLockStatus);
		objectOutput.writeLong(createdDate);

		objectOutput.writeInt(createdBy);

		if (source == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(source);
		}

		if (formulaMethodId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(formulaMethodId);
		}

		if (batchId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(batchId);
		}

		objectOutput.writeInt(modifiedBy);

		if (inboundStatus == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(inboundStatus);
		}

		objectOutput.writeInt(formulaId);

		objectOutput.writeInt(itemMasterSid);

		objectOutput.writeInt(rsDetailsSid);
		objectOutput.writeLong(modifiedDate);

		objectOutput.writeInt(rsDetailsFrSid);
	}

	public boolean recordLockStatus;
	public long createdDate;
	public int createdBy;
	public String source;
	public String formulaMethodId;
	public String batchId;
	public int modifiedBy;
	public String inboundStatus;
	public int formulaId;
	public int itemMasterSid;
	public int rsDetailsSid;
	public long modifiedDate;
	public int rsDetailsFrSid;
}