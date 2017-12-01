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

import com.stpl.app.model.ImtdCfpDetails;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing ImtdCfpDetails in entity cache.
 *
 * @author
 * @see ImtdCfpDetails
 * @generated
 */
@ProviderType
public class ImtdCfpDetailsCacheModel implements CacheModel<ImtdCfpDetails>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ImtdCfpDetailsCacheModel)) {
			return false;
		}

		ImtdCfpDetailsCacheModel imtdCfpDetailsCacheModel = (ImtdCfpDetailsCacheModel)obj;

		if (imtdCfpDetailsSid == imtdCfpDetailsCacheModel.imtdCfpDetailsSid) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, imtdCfpDetailsSid);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(65);

		sb.append("{companyNo=");
		sb.append(companyNo);
		sb.append(", imtdCfpDetailsSid=");
		sb.append(imtdCfpDetailsSid);
		sb.append(", cfpDetailsStartDate=");
		sb.append(cfpDetailsStartDate);
		sb.append(", companyType=");
		sb.append(companyType);
		sb.append(", cfpDetailsTcStartDate=");
		sb.append(cfpDetailsTcStartDate);
		sb.append(", modifiedBy=");
		sb.append(modifiedBy);
		sb.append(", createdDate=");
		sb.append(createdDate);
		sb.append(", cfpDetailsTcEndDate=");
		sb.append(cfpDetailsTcEndDate);
		sb.append(", cfpDetailsCreatedDate=");
		sb.append(cfpDetailsCreatedDate);
		sb.append(", imtdCreatedDate=");
		sb.append(imtdCreatedDate);
		sb.append(", cfpDetailsModifiedDate=");
		sb.append(cfpDetailsModifiedDate);
		sb.append(", cfpDetailsAttachedStatus=");
		sb.append(cfpDetailsAttachedStatus);
		sb.append(", checkRecord=");
		sb.append(checkRecord);
		sb.append(", cfpDetailsAttachedDate=");
		sb.append(cfpDetailsAttachedDate);
		sb.append(", cfpDetailsEndDate=");
		sb.append(cfpDetailsEndDate);
		sb.append(", companyStringId=");
		sb.append(companyStringId);
		sb.append(", cfpDetailsTradeClass=");
		sb.append(cfpDetailsTradeClass);
		sb.append(", tradingPartnerContractNo=");
		sb.append(tradingPartnerContractNo);
		sb.append(", createdBy=");
		sb.append(createdBy);
		sb.append(", usersSid=");
		sb.append(usersSid);
		sb.append(", companyStartDate=");
		sb.append(companyStartDate);
		sb.append(", cfpDetailsModifiedBy=");
		sb.append(cfpDetailsModifiedBy);
		sb.append(", companyEndDate=");
		sb.append(companyEndDate);
		sb.append(", companyMasterSid=");
		sb.append(companyMasterSid);
		sb.append(", cfpModelSid=");
		sb.append(cfpModelSid);
		sb.append(", cfpDetailsSid=");
		sb.append(cfpDetailsSid);
		sb.append(", companyStatus=");
		sb.append(companyStatus);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", companyName=");
		sb.append(companyName);
		sb.append(", operation=");
		sb.append(operation);
		sb.append(", cfpDetailsCreatedBy=");
		sb.append(cfpDetailsCreatedBy);
		sb.append(", sessionId=");
		sb.append(sessionId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ImtdCfpDetails toEntityModel() {
		ImtdCfpDetailsImpl imtdCfpDetailsImpl = new ImtdCfpDetailsImpl();

		if (companyNo == null) {
			imtdCfpDetailsImpl.setCompanyNo(StringPool.BLANK);
		}
		else {
			imtdCfpDetailsImpl.setCompanyNo(companyNo);
		}

		imtdCfpDetailsImpl.setImtdCfpDetailsSid(imtdCfpDetailsSid);

		if (cfpDetailsStartDate == Long.MIN_VALUE) {
			imtdCfpDetailsImpl.setCfpDetailsStartDate(null);
		}
		else {
			imtdCfpDetailsImpl.setCfpDetailsStartDate(new Date(
					cfpDetailsStartDate));
		}

		if (companyType == null) {
			imtdCfpDetailsImpl.setCompanyType(StringPool.BLANK);
		}
		else {
			imtdCfpDetailsImpl.setCompanyType(companyType);
		}

		if (cfpDetailsTcStartDate == Long.MIN_VALUE) {
			imtdCfpDetailsImpl.setCfpDetailsTcStartDate(null);
		}
		else {
			imtdCfpDetailsImpl.setCfpDetailsTcStartDate(new Date(
					cfpDetailsTcStartDate));
		}

		imtdCfpDetailsImpl.setModifiedBy(modifiedBy);

		if (createdDate == Long.MIN_VALUE) {
			imtdCfpDetailsImpl.setCreatedDate(null);
		}
		else {
			imtdCfpDetailsImpl.setCreatedDate(new Date(createdDate));
		}

		if (cfpDetailsTcEndDate == Long.MIN_VALUE) {
			imtdCfpDetailsImpl.setCfpDetailsTcEndDate(null);
		}
		else {
			imtdCfpDetailsImpl.setCfpDetailsTcEndDate(new Date(
					cfpDetailsTcEndDate));
		}

		if (cfpDetailsCreatedDate == Long.MIN_VALUE) {
			imtdCfpDetailsImpl.setCfpDetailsCreatedDate(null);
		}
		else {
			imtdCfpDetailsImpl.setCfpDetailsCreatedDate(new Date(
					cfpDetailsCreatedDate));
		}

		if (imtdCreatedDate == Long.MIN_VALUE) {
			imtdCfpDetailsImpl.setImtdCreatedDate(null);
		}
		else {
			imtdCfpDetailsImpl.setImtdCreatedDate(new Date(imtdCreatedDate));
		}

		if (cfpDetailsModifiedDate == Long.MIN_VALUE) {
			imtdCfpDetailsImpl.setCfpDetailsModifiedDate(null);
		}
		else {
			imtdCfpDetailsImpl.setCfpDetailsModifiedDate(new Date(
					cfpDetailsModifiedDate));
		}

		imtdCfpDetailsImpl.setCfpDetailsAttachedStatus(cfpDetailsAttachedStatus);
		imtdCfpDetailsImpl.setCheckRecord(checkRecord);

		if (cfpDetailsAttachedDate == Long.MIN_VALUE) {
			imtdCfpDetailsImpl.setCfpDetailsAttachedDate(null);
		}
		else {
			imtdCfpDetailsImpl.setCfpDetailsAttachedDate(new Date(
					cfpDetailsAttachedDate));
		}

		if (cfpDetailsEndDate == Long.MIN_VALUE) {
			imtdCfpDetailsImpl.setCfpDetailsEndDate(null);
		}
		else {
			imtdCfpDetailsImpl.setCfpDetailsEndDate(new Date(cfpDetailsEndDate));
		}

		if (companyStringId == null) {
			imtdCfpDetailsImpl.setCompanyStringId(StringPool.BLANK);
		}
		else {
			imtdCfpDetailsImpl.setCompanyStringId(companyStringId);
		}

		if (cfpDetailsTradeClass == null) {
			imtdCfpDetailsImpl.setCfpDetailsTradeClass(StringPool.BLANK);
		}
		else {
			imtdCfpDetailsImpl.setCfpDetailsTradeClass(cfpDetailsTradeClass);
		}

		if (tradingPartnerContractNo == null) {
			imtdCfpDetailsImpl.setTradingPartnerContractNo(StringPool.BLANK);
		}
		else {
			imtdCfpDetailsImpl.setTradingPartnerContractNo(tradingPartnerContractNo);
		}

		imtdCfpDetailsImpl.setCreatedBy(createdBy);

		if (usersSid == null) {
			imtdCfpDetailsImpl.setUsersSid(StringPool.BLANK);
		}
		else {
			imtdCfpDetailsImpl.setUsersSid(usersSid);
		}

		if (companyStartDate == Long.MIN_VALUE) {
			imtdCfpDetailsImpl.setCompanyStartDate(null);
		}
		else {
			imtdCfpDetailsImpl.setCompanyStartDate(new Date(companyStartDate));
		}

		if (cfpDetailsModifiedBy == null) {
			imtdCfpDetailsImpl.setCfpDetailsModifiedBy(StringPool.BLANK);
		}
		else {
			imtdCfpDetailsImpl.setCfpDetailsModifiedBy(cfpDetailsModifiedBy);
		}

		if (companyEndDate == Long.MIN_VALUE) {
			imtdCfpDetailsImpl.setCompanyEndDate(null);
		}
		else {
			imtdCfpDetailsImpl.setCompanyEndDate(new Date(companyEndDate));
		}

		imtdCfpDetailsImpl.setCompanyMasterSid(companyMasterSid);
		imtdCfpDetailsImpl.setCfpModelSid(cfpModelSid);
		imtdCfpDetailsImpl.setCfpDetailsSid(cfpDetailsSid);
		imtdCfpDetailsImpl.setCompanyStatus(companyStatus);

		if (modifiedDate == Long.MIN_VALUE) {
			imtdCfpDetailsImpl.setModifiedDate(null);
		}
		else {
			imtdCfpDetailsImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (companyName == null) {
			imtdCfpDetailsImpl.setCompanyName(StringPool.BLANK);
		}
		else {
			imtdCfpDetailsImpl.setCompanyName(companyName);
		}

		if (operation == null) {
			imtdCfpDetailsImpl.setOperation(StringPool.BLANK);
		}
		else {
			imtdCfpDetailsImpl.setOperation(operation);
		}

		if (cfpDetailsCreatedBy == null) {
			imtdCfpDetailsImpl.setCfpDetailsCreatedBy(StringPool.BLANK);
		}
		else {
			imtdCfpDetailsImpl.setCfpDetailsCreatedBy(cfpDetailsCreatedBy);
		}

		if (sessionId == null) {
			imtdCfpDetailsImpl.setSessionId(StringPool.BLANK);
		}
		else {
			imtdCfpDetailsImpl.setSessionId(sessionId);
		}

		imtdCfpDetailsImpl.resetOriginalValues();

		return imtdCfpDetailsImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		companyNo = objectInput.readUTF();

		imtdCfpDetailsSid = objectInput.readInt();
		cfpDetailsStartDate = objectInput.readLong();
		companyType = objectInput.readUTF();
		cfpDetailsTcStartDate = objectInput.readLong();

		modifiedBy = objectInput.readInt();
		createdDate = objectInput.readLong();
		cfpDetailsTcEndDate = objectInput.readLong();
		cfpDetailsCreatedDate = objectInput.readLong();
		imtdCreatedDate = objectInput.readLong();
		cfpDetailsModifiedDate = objectInput.readLong();

		cfpDetailsAttachedStatus = objectInput.readInt();

		checkRecord = objectInput.readBoolean();
		cfpDetailsAttachedDate = objectInput.readLong();
		cfpDetailsEndDate = objectInput.readLong();
		companyStringId = objectInput.readUTF();
		cfpDetailsTradeClass = objectInput.readUTF();
		tradingPartnerContractNo = objectInput.readUTF();

		createdBy = objectInput.readInt();
		usersSid = objectInput.readUTF();
		companyStartDate = objectInput.readLong();
		cfpDetailsModifiedBy = objectInput.readUTF();
		companyEndDate = objectInput.readLong();

		companyMasterSid = objectInput.readInt();

		cfpModelSid = objectInput.readInt();

		cfpDetailsSid = objectInput.readInt();

		companyStatus = objectInput.readInt();
		modifiedDate = objectInput.readLong();
		companyName = objectInput.readUTF();
		operation = objectInput.readUTF();
		cfpDetailsCreatedBy = objectInput.readUTF();
		sessionId = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		if (companyNo == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(companyNo);
		}

		objectOutput.writeInt(imtdCfpDetailsSid);
		objectOutput.writeLong(cfpDetailsStartDate);

		if (companyType == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(companyType);
		}

		objectOutput.writeLong(cfpDetailsTcStartDate);

		objectOutput.writeInt(modifiedBy);
		objectOutput.writeLong(createdDate);
		objectOutput.writeLong(cfpDetailsTcEndDate);
		objectOutput.writeLong(cfpDetailsCreatedDate);
		objectOutput.writeLong(imtdCreatedDate);
		objectOutput.writeLong(cfpDetailsModifiedDate);

		objectOutput.writeInt(cfpDetailsAttachedStatus);

		objectOutput.writeBoolean(checkRecord);
		objectOutput.writeLong(cfpDetailsAttachedDate);
		objectOutput.writeLong(cfpDetailsEndDate);

		if (companyStringId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(companyStringId);
		}

		if (cfpDetailsTradeClass == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(cfpDetailsTradeClass);
		}

		if (tradingPartnerContractNo == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(tradingPartnerContractNo);
		}

		objectOutput.writeInt(createdBy);

		if (usersSid == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(usersSid);
		}

		objectOutput.writeLong(companyStartDate);

		if (cfpDetailsModifiedBy == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(cfpDetailsModifiedBy);
		}

		objectOutput.writeLong(companyEndDate);

		objectOutput.writeInt(companyMasterSid);

		objectOutput.writeInt(cfpModelSid);

		objectOutput.writeInt(cfpDetailsSid);

		objectOutput.writeInt(companyStatus);
		objectOutput.writeLong(modifiedDate);

		if (companyName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(companyName);
		}

		if (operation == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(operation);
		}

		if (cfpDetailsCreatedBy == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(cfpDetailsCreatedBy);
		}

		if (sessionId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(sessionId);
		}
	}

	public String companyNo;
	public int imtdCfpDetailsSid;
	public long cfpDetailsStartDate;
	public String companyType;
	public long cfpDetailsTcStartDate;
	public int modifiedBy;
	public long createdDate;
	public long cfpDetailsTcEndDate;
	public long cfpDetailsCreatedDate;
	public long imtdCreatedDate;
	public long cfpDetailsModifiedDate;
	public int cfpDetailsAttachedStatus;
	public boolean checkRecord;
	public long cfpDetailsAttachedDate;
	public long cfpDetailsEndDate;
	public String companyStringId;
	public String cfpDetailsTradeClass;
	public String tradingPartnerContractNo;
	public int createdBy;
	public String usersSid;
	public long companyStartDate;
	public String cfpDetailsModifiedBy;
	public long companyEndDate;
	public int companyMasterSid;
	public int cfpModelSid;
	public int cfpDetailsSid;
	public int companyStatus;
	public long modifiedDate;
	public String companyName;
	public String operation;
	public String cfpDetailsCreatedBy;
	public String sessionId;
}