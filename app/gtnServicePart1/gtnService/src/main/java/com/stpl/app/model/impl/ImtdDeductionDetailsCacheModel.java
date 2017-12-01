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

import com.stpl.app.model.ImtdDeductionDetails;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing ImtdDeductionDetails in entity cache.
 *
 * @author
 * @see ImtdDeductionDetails
 * @generated
 */
@ProviderType
public class ImtdDeductionDetailsCacheModel implements CacheModel<ImtdDeductionDetails>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ImtdDeductionDetailsCacheModel)) {
			return false;
		}

		ImtdDeductionDetailsCacheModel imtdDeductionDetailsCacheModel = (ImtdDeductionDetailsCacheModel)obj;

		if (imtdDeductionDetailsSid == imtdDeductionDetailsCacheModel.imtdDeductionDetailsSid) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, imtdDeductionDetailsSid);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(51);

		sb.append("{imtdDeductionDetailsSid=");
		sb.append(imtdDeductionDetailsSid);
		sb.append(", deductionName=");
		sb.append(deductionName);
		sb.append(", modifiedBy=");
		sb.append(modifiedBy);
		sb.append(", createdDate=");
		sb.append(createdDate);
		sb.append(", imtdCreatedDate=");
		sb.append(imtdCreatedDate);
		sb.append(", deductionDetailsSid=");
		sb.append(deductionDetailsSid);
		sb.append(", indicator=");
		sb.append(indicator);
		sb.append(", contractNo=");
		sb.append(contractNo);
		sb.append(", checkRecord=");
		sb.append(checkRecord);
		sb.append(", deductionSubType=");
		sb.append(deductionSubType);
		sb.append(", cdrModelSid=");
		sb.append(cdrModelSid);
		sb.append(", createdBy=");
		sb.append(createdBy);
		sb.append(", deductionNo=");
		sb.append(deductionNo);
		sb.append(", netSalesFormulaMasterSid=");
		sb.append(netSalesFormulaMasterSid);
		sb.append(", usersSid=");
		sb.append(usersSid);
		sb.append(", contractMasterSid=");
		sb.append(contractMasterSid);
		sb.append(", contractName=");
		sb.append(contractName);
		sb.append(", deductionCategory=");
		sb.append(deductionCategory);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", deductionType=");
		sb.append(deductionType);
		sb.append(", recordLockStatus=");
		sb.append(recordLockStatus);
		sb.append(", operation=");
		sb.append(operation);
		sb.append(", sessionId=");
		sb.append(sessionId);
		sb.append(", rsContractSid=");
		sb.append(rsContractSid);
		sb.append(", inboundStatus=");
		sb.append(inboundStatus);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ImtdDeductionDetails toEntityModel() {
		ImtdDeductionDetailsImpl imtdDeductionDetailsImpl = new ImtdDeductionDetailsImpl();

		imtdDeductionDetailsImpl.setImtdDeductionDetailsSid(imtdDeductionDetailsSid);

		if (deductionName == null) {
			imtdDeductionDetailsImpl.setDeductionName(StringPool.BLANK);
		}
		else {
			imtdDeductionDetailsImpl.setDeductionName(deductionName);
		}

		imtdDeductionDetailsImpl.setModifiedBy(modifiedBy);

		if (createdDate == Long.MIN_VALUE) {
			imtdDeductionDetailsImpl.setCreatedDate(null);
		}
		else {
			imtdDeductionDetailsImpl.setCreatedDate(new Date(createdDate));
		}

		if (imtdCreatedDate == null) {
			imtdDeductionDetailsImpl.setImtdCreatedDate(StringPool.BLANK);
		}
		else {
			imtdDeductionDetailsImpl.setImtdCreatedDate(imtdCreatedDate);
		}

		imtdDeductionDetailsImpl.setDeductionDetailsSid(deductionDetailsSid);

		if (indicator == null) {
			imtdDeductionDetailsImpl.setIndicator(StringPool.BLANK);
		}
		else {
			imtdDeductionDetailsImpl.setIndicator(indicator);
		}

		if (contractNo == null) {
			imtdDeductionDetailsImpl.setContractNo(StringPool.BLANK);
		}
		else {
			imtdDeductionDetailsImpl.setContractNo(contractNo);
		}

		imtdDeductionDetailsImpl.setCheckRecord(checkRecord);

		if (deductionSubType == null) {
			imtdDeductionDetailsImpl.setDeductionSubType(StringPool.BLANK);
		}
		else {
			imtdDeductionDetailsImpl.setDeductionSubType(deductionSubType);
		}

		imtdDeductionDetailsImpl.setCdrModelSid(cdrModelSid);
		imtdDeductionDetailsImpl.setCreatedBy(createdBy);

		if (deductionNo == null) {
			imtdDeductionDetailsImpl.setDeductionNo(StringPool.BLANK);
		}
		else {
			imtdDeductionDetailsImpl.setDeductionNo(deductionNo);
		}

		imtdDeductionDetailsImpl.setNetSalesFormulaMasterSid(netSalesFormulaMasterSid);
		imtdDeductionDetailsImpl.setUsersSid(usersSid);
		imtdDeductionDetailsImpl.setContractMasterSid(contractMasterSid);

		if (contractName == null) {
			imtdDeductionDetailsImpl.setContractName(StringPool.BLANK);
		}
		else {
			imtdDeductionDetailsImpl.setContractName(contractName);
		}

		if (deductionCategory == null) {
			imtdDeductionDetailsImpl.setDeductionCategory(StringPool.BLANK);
		}
		else {
			imtdDeductionDetailsImpl.setDeductionCategory(deductionCategory);
		}

		if (modifiedDate == Long.MIN_VALUE) {
			imtdDeductionDetailsImpl.setModifiedDate(null);
		}
		else {
			imtdDeductionDetailsImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (deductionType == null) {
			imtdDeductionDetailsImpl.setDeductionType(StringPool.BLANK);
		}
		else {
			imtdDeductionDetailsImpl.setDeductionType(deductionType);
		}

		imtdDeductionDetailsImpl.setRecordLockStatus(recordLockStatus);

		if (operation == null) {
			imtdDeductionDetailsImpl.setOperation(StringPool.BLANK);
		}
		else {
			imtdDeductionDetailsImpl.setOperation(operation);
		}

		if (sessionId == null) {
			imtdDeductionDetailsImpl.setSessionId(StringPool.BLANK);
		}
		else {
			imtdDeductionDetailsImpl.setSessionId(sessionId);
		}

		imtdDeductionDetailsImpl.setRsContractSid(rsContractSid);

		if (inboundStatus == null) {
			imtdDeductionDetailsImpl.setInboundStatus(StringPool.BLANK);
		}
		else {
			imtdDeductionDetailsImpl.setInboundStatus(inboundStatus);
		}

		imtdDeductionDetailsImpl.resetOriginalValues();

		return imtdDeductionDetailsImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		imtdDeductionDetailsSid = objectInput.readInt();
		deductionName = objectInput.readUTF();

		modifiedBy = objectInput.readInt();
		createdDate = objectInput.readLong();
		imtdCreatedDate = objectInput.readUTF();

		deductionDetailsSid = objectInput.readInt();
		indicator = objectInput.readUTF();
		contractNo = objectInput.readUTF();

		checkRecord = objectInput.readBoolean();
		deductionSubType = objectInput.readUTF();

		cdrModelSid = objectInput.readInt();

		createdBy = objectInput.readInt();
		deductionNo = objectInput.readUTF();

		netSalesFormulaMasterSid = objectInput.readInt();

		usersSid = objectInput.readInt();

		contractMasterSid = objectInput.readInt();
		contractName = objectInput.readUTF();
		deductionCategory = objectInput.readUTF();
		modifiedDate = objectInput.readLong();
		deductionType = objectInput.readUTF();

		recordLockStatus = objectInput.readBoolean();
		operation = objectInput.readUTF();
		sessionId = objectInput.readUTF();

		rsContractSid = objectInput.readInt();
		inboundStatus = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeInt(imtdDeductionDetailsSid);

		if (deductionName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(deductionName);
		}

		objectOutput.writeInt(modifiedBy);
		objectOutput.writeLong(createdDate);

		if (imtdCreatedDate == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(imtdCreatedDate);
		}

		objectOutput.writeInt(deductionDetailsSid);

		if (indicator == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(indicator);
		}

		if (contractNo == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(contractNo);
		}

		objectOutput.writeBoolean(checkRecord);

		if (deductionSubType == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(deductionSubType);
		}

		objectOutput.writeInt(cdrModelSid);

		objectOutput.writeInt(createdBy);

		if (deductionNo == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(deductionNo);
		}

		objectOutput.writeInt(netSalesFormulaMasterSid);

		objectOutput.writeInt(usersSid);

		objectOutput.writeInt(contractMasterSid);

		if (contractName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(contractName);
		}

		if (deductionCategory == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(deductionCategory);
		}

		objectOutput.writeLong(modifiedDate);

		if (deductionType == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(deductionType);
		}

		objectOutput.writeBoolean(recordLockStatus);

		if (operation == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(operation);
		}

		if (sessionId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(sessionId);
		}

		objectOutput.writeInt(rsContractSid);

		if (inboundStatus == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(inboundStatus);
		}
	}

	public int imtdDeductionDetailsSid;
	public String deductionName;
	public int modifiedBy;
	public long createdDate;
	public String imtdCreatedDate;
	public int deductionDetailsSid;
	public String indicator;
	public String contractNo;
	public boolean checkRecord;
	public String deductionSubType;
	public int cdrModelSid;
	public int createdBy;
	public String deductionNo;
	public int netSalesFormulaMasterSid;
	public int usersSid;
	public int contractMasterSid;
	public String contractName;
	public String deductionCategory;
	public long modifiedDate;
	public String deductionType;
	public boolean recordLockStatus;
	public String operation;
	public String sessionId;
	public int rsContractSid;
	public String inboundStatus;
}