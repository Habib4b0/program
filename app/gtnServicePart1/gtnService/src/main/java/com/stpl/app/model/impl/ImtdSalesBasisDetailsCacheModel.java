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

import com.stpl.app.model.ImtdSalesBasisDetails;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing ImtdSalesBasisDetails in entity cache.
 *
 * @author
 * @see ImtdSalesBasisDetails
 * @generated
 */
@ProviderType
public class ImtdSalesBasisDetailsCacheModel implements CacheModel<ImtdSalesBasisDetails>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ImtdSalesBasisDetailsCacheModel)) {
			return false;
		}

		ImtdSalesBasisDetailsCacheModel imtdSalesBasisDetailsCacheModel = (ImtdSalesBasisDetailsCacheModel)obj;

		if (imtdSalesBasisDetailsSid == imtdSalesBasisDetailsCacheModel.imtdSalesBasisDetailsSid) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, imtdSalesBasisDetailsSid);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(45);

		sb.append("{createdBy=");
		sb.append(createdBy);
		sb.append(", netSalesFormulaMasterSid=");
		sb.append(netSalesFormulaMasterSid);
		sb.append(", usersSid=");
		sb.append(usersSid);
		sb.append(", modifiedBy=");
		sb.append(modifiedBy);
		sb.append(", createdDate=");
		sb.append(createdDate);
		sb.append(", contractMasterSid=");
		sb.append(contractMasterSid);
		sb.append(", cfpNo=");
		sb.append(cfpNo);
		sb.append(", imtdCreatedDate=");
		sb.append(imtdCreatedDate);
		sb.append(", contractNo=");
		sb.append(contractNo);
		sb.append(", contractName=");
		sb.append(contractName);
		sb.append(", salesBasisDetailsSid=");
		sb.append(salesBasisDetailsSid);
		sb.append(", checkRecord=");
		sb.append(checkRecord);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", customerName=");
		sb.append(customerName);
		sb.append(", operation=");
		sb.append(operation);
		sb.append(", customerNo=");
		sb.append(customerNo);
		sb.append(", imtdSalesBasisDetailsSid=");
		sb.append(imtdSalesBasisDetailsSid);
		sb.append(", cfpName=");
		sb.append(cfpName);
		sb.append(", cdrModelSid=");
		sb.append(cdrModelSid);
		sb.append(", sessionId=");
		sb.append(sessionId);
		sb.append(", cfpContractDetailsSid=");
		sb.append(cfpContractDetailsSid);
		sb.append(", inboundStatus=");
		sb.append(inboundStatus);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ImtdSalesBasisDetails toEntityModel() {
		ImtdSalesBasisDetailsImpl imtdSalesBasisDetailsImpl = new ImtdSalesBasisDetailsImpl();

		imtdSalesBasisDetailsImpl.setCreatedBy(createdBy);
		imtdSalesBasisDetailsImpl.setNetSalesFormulaMasterSid(netSalesFormulaMasterSid);
		imtdSalesBasisDetailsImpl.setUsersSid(usersSid);
		imtdSalesBasisDetailsImpl.setModifiedBy(modifiedBy);

		if (createdDate == Long.MIN_VALUE) {
			imtdSalesBasisDetailsImpl.setCreatedDate(null);
		}
		else {
			imtdSalesBasisDetailsImpl.setCreatedDate(new Date(createdDate));
		}

		imtdSalesBasisDetailsImpl.setContractMasterSid(contractMasterSid);

		if (cfpNo == null) {
			imtdSalesBasisDetailsImpl.setCfpNo(StringPool.BLANK);
		}
		else {
			imtdSalesBasisDetailsImpl.setCfpNo(cfpNo);
		}

		if (imtdCreatedDate == null) {
			imtdSalesBasisDetailsImpl.setImtdCreatedDate(StringPool.BLANK);
		}
		else {
			imtdSalesBasisDetailsImpl.setImtdCreatedDate(imtdCreatedDate);
		}

		if (contractNo == null) {
			imtdSalesBasisDetailsImpl.setContractNo(StringPool.BLANK);
		}
		else {
			imtdSalesBasisDetailsImpl.setContractNo(contractNo);
		}

		if (contractName == null) {
			imtdSalesBasisDetailsImpl.setContractName(StringPool.BLANK);
		}
		else {
			imtdSalesBasisDetailsImpl.setContractName(contractName);
		}

		imtdSalesBasisDetailsImpl.setSalesBasisDetailsSid(salesBasisDetailsSid);
		imtdSalesBasisDetailsImpl.setCheckRecord(checkRecord);

		if (modifiedDate == Long.MIN_VALUE) {
			imtdSalesBasisDetailsImpl.setModifiedDate(null);
		}
		else {
			imtdSalesBasisDetailsImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (customerName == null) {
			imtdSalesBasisDetailsImpl.setCustomerName(StringPool.BLANK);
		}
		else {
			imtdSalesBasisDetailsImpl.setCustomerName(customerName);
		}

		if (operation == null) {
			imtdSalesBasisDetailsImpl.setOperation(StringPool.BLANK);
		}
		else {
			imtdSalesBasisDetailsImpl.setOperation(operation);
		}

		if (customerNo == null) {
			imtdSalesBasisDetailsImpl.setCustomerNo(StringPool.BLANK);
		}
		else {
			imtdSalesBasisDetailsImpl.setCustomerNo(customerNo);
		}

		imtdSalesBasisDetailsImpl.setImtdSalesBasisDetailsSid(imtdSalesBasisDetailsSid);

		if (cfpName == null) {
			imtdSalesBasisDetailsImpl.setCfpName(StringPool.BLANK);
		}
		else {
			imtdSalesBasisDetailsImpl.setCfpName(cfpName);
		}

		imtdSalesBasisDetailsImpl.setCdrModelSid(cdrModelSid);

		if (sessionId == null) {
			imtdSalesBasisDetailsImpl.setSessionId(StringPool.BLANK);
		}
		else {
			imtdSalesBasisDetailsImpl.setSessionId(sessionId);
		}

		imtdSalesBasisDetailsImpl.setCfpContractDetailsSid(cfpContractDetailsSid);

		if (inboundStatus == null) {
			imtdSalesBasisDetailsImpl.setInboundStatus(StringPool.BLANK);
		}
		else {
			imtdSalesBasisDetailsImpl.setInboundStatus(inboundStatus);
		}

		imtdSalesBasisDetailsImpl.resetOriginalValues();

		return imtdSalesBasisDetailsImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		createdBy = objectInput.readInt();

		netSalesFormulaMasterSid = objectInput.readInt();

		usersSid = objectInput.readInt();

		modifiedBy = objectInput.readInt();
		createdDate = objectInput.readLong();

		contractMasterSid = objectInput.readInt();
		cfpNo = objectInput.readUTF();
		imtdCreatedDate = objectInput.readUTF();
		contractNo = objectInput.readUTF();
		contractName = objectInput.readUTF();

		salesBasisDetailsSid = objectInput.readInt();

		checkRecord = objectInput.readBoolean();
		modifiedDate = objectInput.readLong();
		customerName = objectInput.readUTF();
		operation = objectInput.readUTF();
		customerNo = objectInput.readUTF();

		imtdSalesBasisDetailsSid = objectInput.readInt();
		cfpName = objectInput.readUTF();

		cdrModelSid = objectInput.readInt();
		sessionId = objectInput.readUTF();

		cfpContractDetailsSid = objectInput.readInt();
		inboundStatus = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeInt(createdBy);

		objectOutput.writeInt(netSalesFormulaMasterSid);

		objectOutput.writeInt(usersSid);

		objectOutput.writeInt(modifiedBy);
		objectOutput.writeLong(createdDate);

		objectOutput.writeInt(contractMasterSid);

		if (cfpNo == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(cfpNo);
		}

		if (imtdCreatedDate == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(imtdCreatedDate);
		}

		if (contractNo == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(contractNo);
		}

		if (contractName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(contractName);
		}

		objectOutput.writeInt(salesBasisDetailsSid);

		objectOutput.writeBoolean(checkRecord);
		objectOutput.writeLong(modifiedDate);

		if (customerName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(customerName);
		}

		if (operation == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(operation);
		}

		if (customerNo == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(customerNo);
		}

		objectOutput.writeInt(imtdSalesBasisDetailsSid);

		if (cfpName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(cfpName);
		}

		objectOutput.writeInt(cdrModelSid);

		if (sessionId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(sessionId);
		}

		objectOutput.writeInt(cfpContractDetailsSid);

		if (inboundStatus == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(inboundStatus);
		}
	}

	public int createdBy;
	public int netSalesFormulaMasterSid;
	public int usersSid;
	public int modifiedBy;
	public long createdDate;
	public int contractMasterSid;
	public String cfpNo;
	public String imtdCreatedDate;
	public String contractNo;
	public String contractName;
	public int salesBasisDetailsSid;
	public boolean checkRecord;
	public long modifiedDate;
	public String customerName;
	public String operation;
	public String customerNo;
	public int imtdSalesBasisDetailsSid;
	public String cfpName;
	public int cdrModelSid;
	public String sessionId;
	public int cfpContractDetailsSid;
	public String inboundStatus;
}