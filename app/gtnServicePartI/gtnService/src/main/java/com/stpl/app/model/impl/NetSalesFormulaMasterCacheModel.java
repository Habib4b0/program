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

import com.stpl.app.model.NetSalesFormulaMaster;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing NetSalesFormulaMaster in entity cache.
 *
 * @author
 * @see NetSalesFormulaMaster
 * @generated
 */
@ProviderType
public class NetSalesFormulaMasterCacheModel implements CacheModel<NetSalesFormulaMaster>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof NetSalesFormulaMasterCacheModel)) {
			return false;
		}

		NetSalesFormulaMasterCacheModel netSalesFormulaMasterCacheModel = (NetSalesFormulaMasterCacheModel)obj;

		if (netSalesFormulaMasterSid == netSalesFormulaMasterCacheModel.netSalesFormulaMasterSid) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, netSalesFormulaMasterSid);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(31);

		sb.append("{createdBy=");
		sb.append(createdBy);
		sb.append(", netSalesFormulaMasterSid=");
		sb.append(netSalesFormulaMasterSid);
		sb.append(", modifiedBy=");
		sb.append(modifiedBy);
		sb.append(", createdDate=");
		sb.append(createdDate);
		sb.append(", netSalesFormulaName=");
		sb.append(netSalesFormulaName);
		sb.append(", netSalesFormulaType=");
		sb.append(netSalesFormulaType);
		sb.append(", netSalesFormulaCategory=");
		sb.append(netSalesFormulaCategory);
		sb.append(", contractSelection=");
		sb.append(contractSelection);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", recordLockStatus=");
		sb.append(recordLockStatus);
		sb.append(", source=");
		sb.append(source);
		sb.append(", netSalesFormulaId=");
		sb.append(netSalesFormulaId);
		sb.append(", netSalesFormulaNo=");
		sb.append(netSalesFormulaNo);
		sb.append(", inboundStatus=");
		sb.append(inboundStatus);
		sb.append(", cdrModelSid=");
		sb.append(cdrModelSid);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public NetSalesFormulaMaster toEntityModel() {
		NetSalesFormulaMasterImpl netSalesFormulaMasterImpl = new NetSalesFormulaMasterImpl();

		netSalesFormulaMasterImpl.setCreatedBy(createdBy);
		netSalesFormulaMasterImpl.setNetSalesFormulaMasterSid(netSalesFormulaMasterSid);
		netSalesFormulaMasterImpl.setModifiedBy(modifiedBy);

		if (createdDate == Long.MIN_VALUE) {
			netSalesFormulaMasterImpl.setCreatedDate(null);
		}
		else {
			netSalesFormulaMasterImpl.setCreatedDate(new Date(createdDate));
		}

		if (netSalesFormulaName == null) {
			netSalesFormulaMasterImpl.setNetSalesFormulaName(StringPool.BLANK);
		}
		else {
			netSalesFormulaMasterImpl.setNetSalesFormulaName(netSalesFormulaName);
		}

		netSalesFormulaMasterImpl.setNetSalesFormulaType(netSalesFormulaType);
		netSalesFormulaMasterImpl.setNetSalesFormulaCategory(netSalesFormulaCategory);

		if (contractSelection == null) {
			netSalesFormulaMasterImpl.setContractSelection(StringPool.BLANK);
		}
		else {
			netSalesFormulaMasterImpl.setContractSelection(contractSelection);
		}

		if (modifiedDate == Long.MIN_VALUE) {
			netSalesFormulaMasterImpl.setModifiedDate(null);
		}
		else {
			netSalesFormulaMasterImpl.setModifiedDate(new Date(modifiedDate));
		}

		netSalesFormulaMasterImpl.setRecordLockStatus(recordLockStatus);

		if (source == null) {
			netSalesFormulaMasterImpl.setSource(StringPool.BLANK);
		}
		else {
			netSalesFormulaMasterImpl.setSource(source);
		}

		if (netSalesFormulaId == null) {
			netSalesFormulaMasterImpl.setNetSalesFormulaId(StringPool.BLANK);
		}
		else {
			netSalesFormulaMasterImpl.setNetSalesFormulaId(netSalesFormulaId);
		}

		if (netSalesFormulaNo == null) {
			netSalesFormulaMasterImpl.setNetSalesFormulaNo(StringPool.BLANK);
		}
		else {
			netSalesFormulaMasterImpl.setNetSalesFormulaNo(netSalesFormulaNo);
		}

		if (inboundStatus == null) {
			netSalesFormulaMasterImpl.setInboundStatus(StringPool.BLANK);
		}
		else {
			netSalesFormulaMasterImpl.setInboundStatus(inboundStatus);
		}

		if (cdrModelSid == null) {
			netSalesFormulaMasterImpl.setCdrModelSid(StringPool.BLANK);
		}
		else {
			netSalesFormulaMasterImpl.setCdrModelSid(cdrModelSid);
		}

		netSalesFormulaMasterImpl.resetOriginalValues();

		return netSalesFormulaMasterImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		createdBy = objectInput.readInt();

		netSalesFormulaMasterSid = objectInput.readInt();

		modifiedBy = objectInput.readInt();
		createdDate = objectInput.readLong();
		netSalesFormulaName = objectInput.readUTF();

		netSalesFormulaType = objectInput.readInt();

		netSalesFormulaCategory = objectInput.readInt();
		contractSelection = objectInput.readUTF();
		modifiedDate = objectInput.readLong();

		recordLockStatus = objectInput.readBoolean();
		source = objectInput.readUTF();
		netSalesFormulaId = objectInput.readUTF();
		netSalesFormulaNo = objectInput.readUTF();
		inboundStatus = objectInput.readUTF();
		cdrModelSid = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeInt(createdBy);

		objectOutput.writeInt(netSalesFormulaMasterSid);

		objectOutput.writeInt(modifiedBy);
		objectOutput.writeLong(createdDate);

		if (netSalesFormulaName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(netSalesFormulaName);
		}

		objectOutput.writeInt(netSalesFormulaType);

		objectOutput.writeInt(netSalesFormulaCategory);

		if (contractSelection == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(contractSelection);
		}

		objectOutput.writeLong(modifiedDate);

		objectOutput.writeBoolean(recordLockStatus);

		if (source == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(source);
		}

		if (netSalesFormulaId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(netSalesFormulaId);
		}

		if (netSalesFormulaNo == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(netSalesFormulaNo);
		}

		if (inboundStatus == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(inboundStatus);
		}

		if (cdrModelSid == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(cdrModelSid);
		}
	}

	public int createdBy;
	public int netSalesFormulaMasterSid;
	public int modifiedBy;
	public long createdDate;
	public String netSalesFormulaName;
	public int netSalesFormulaType;
	public int netSalesFormulaCategory;
	public String contractSelection;
	public long modifiedDate;
	public boolean recordLockStatus;
	public String source;
	public String netSalesFormulaId;
	public String netSalesFormulaNo;
	public String inboundStatus;
	public String cdrModelSid;
}