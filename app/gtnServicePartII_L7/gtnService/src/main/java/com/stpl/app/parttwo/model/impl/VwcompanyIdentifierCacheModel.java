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

package com.stpl.app.parttwo.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import com.stpl.app.parttwo.model.VwcompanyIdentifier;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing VwcompanyIdentifier in entity cache.
 *
 * @author
 * @see VwcompanyIdentifier
 * @generated
 */
@ProviderType
public class VwcompanyIdentifierCacheModel implements CacheModel<VwcompanyIdentifier>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof VwcompanyIdentifierCacheModel)) {
			return false;
		}

		VwcompanyIdentifierCacheModel vwcompanyIdentifierCacheModel = (VwcompanyIdentifierCacheModel)obj;

		if (companyIdentifierSid == vwcompanyIdentifierCacheModel.companyIdentifierSid) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, companyIdentifierSid);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(37);

		sb.append("{companyId=");
		sb.append(companyId);
		sb.append(", companyName=");
		sb.append(companyName);
		sb.append(", endDate=");
		sb.append(endDate);
		sb.append(", companyIdentifierSid=");
		sb.append(companyIdentifierSid);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", identifierStatus=");
		sb.append(identifierStatus);
		sb.append(", companyIdentifier=");
		sb.append(companyIdentifier);
		sb.append(", entityCode=");
		sb.append(entityCode);
		sb.append(", startDate=");
		sb.append(startDate);
		sb.append(", createdDate=");
		sb.append(createdDate);
		sb.append(", createdBy=");
		sb.append(createdBy);
		sb.append(", source=");
		sb.append(source);
		sb.append(", companyNo=");
		sb.append(companyNo);
		sb.append(", batchId=");
		sb.append(batchId);
		sb.append(", addChgDelIndicator=");
		sb.append(addChgDelIndicator);
		sb.append(", identifierCodeQualifierName=");
		sb.append(identifierCodeQualifierName);
		sb.append(", modifiedBy=");
		sb.append(modifiedBy);
		sb.append(", identifierCodeQualifier=");
		sb.append(identifierCodeQualifier);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public VwcompanyIdentifier toEntityModel() {
		VwcompanyIdentifierImpl vwcompanyIdentifierImpl = new VwcompanyIdentifierImpl();

		if (companyId == null) {
			vwcompanyIdentifierImpl.setCompanyId(StringPool.BLANK);
		}
		else {
			vwcompanyIdentifierImpl.setCompanyId(companyId);
		}

		if (companyName == null) {
			vwcompanyIdentifierImpl.setCompanyName(StringPool.BLANK);
		}
		else {
			vwcompanyIdentifierImpl.setCompanyName(companyName);
		}

		if (endDate == Long.MIN_VALUE) {
			vwcompanyIdentifierImpl.setEndDate(null);
		}
		else {
			vwcompanyIdentifierImpl.setEndDate(new Date(endDate));
		}

		vwcompanyIdentifierImpl.setCompanyIdentifierSid(companyIdentifierSid);

		if (modifiedDate == Long.MIN_VALUE) {
			vwcompanyIdentifierImpl.setModifiedDate(null);
		}
		else {
			vwcompanyIdentifierImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (identifierStatus == null) {
			vwcompanyIdentifierImpl.setIdentifierStatus(StringPool.BLANK);
		}
		else {
			vwcompanyIdentifierImpl.setIdentifierStatus(identifierStatus);
		}

		if (companyIdentifier == null) {
			vwcompanyIdentifierImpl.setCompanyIdentifier(StringPool.BLANK);
		}
		else {
			vwcompanyIdentifierImpl.setCompanyIdentifier(companyIdentifier);
		}

		if (entityCode == null) {
			vwcompanyIdentifierImpl.setEntityCode(StringPool.BLANK);
		}
		else {
			vwcompanyIdentifierImpl.setEntityCode(entityCode);
		}

		if (startDate == Long.MIN_VALUE) {
			vwcompanyIdentifierImpl.setStartDate(null);
		}
		else {
			vwcompanyIdentifierImpl.setStartDate(new Date(startDate));
		}

		if (createdDate == Long.MIN_VALUE) {
			vwcompanyIdentifierImpl.setCreatedDate(null);
		}
		else {
			vwcompanyIdentifierImpl.setCreatedDate(new Date(createdDate));
		}

		if (createdBy == null) {
			vwcompanyIdentifierImpl.setCreatedBy(StringPool.BLANK);
		}
		else {
			vwcompanyIdentifierImpl.setCreatedBy(createdBy);
		}

		if (source == null) {
			vwcompanyIdentifierImpl.setSource(StringPool.BLANK);
		}
		else {
			vwcompanyIdentifierImpl.setSource(source);
		}

		if (companyNo == null) {
			vwcompanyIdentifierImpl.setCompanyNo(StringPool.BLANK);
		}
		else {
			vwcompanyIdentifierImpl.setCompanyNo(companyNo);
		}

		if (batchId == null) {
			vwcompanyIdentifierImpl.setBatchId(StringPool.BLANK);
		}
		else {
			vwcompanyIdentifierImpl.setBatchId(batchId);
		}

		if (addChgDelIndicator == null) {
			vwcompanyIdentifierImpl.setAddChgDelIndicator(StringPool.BLANK);
		}
		else {
			vwcompanyIdentifierImpl.setAddChgDelIndicator(addChgDelIndicator);
		}

		if (identifierCodeQualifierName == null) {
			vwcompanyIdentifierImpl.setIdentifierCodeQualifierName(StringPool.BLANK);
		}
		else {
			vwcompanyIdentifierImpl.setIdentifierCodeQualifierName(identifierCodeQualifierName);
		}

		if (modifiedBy == null) {
			vwcompanyIdentifierImpl.setModifiedBy(StringPool.BLANK);
		}
		else {
			vwcompanyIdentifierImpl.setModifiedBy(modifiedBy);
		}

		if (identifierCodeQualifier == null) {
			vwcompanyIdentifierImpl.setIdentifierCodeQualifier(StringPool.BLANK);
		}
		else {
			vwcompanyIdentifierImpl.setIdentifierCodeQualifier(identifierCodeQualifier);
		}

		vwcompanyIdentifierImpl.resetOriginalValues();

		return vwcompanyIdentifierImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		companyId = objectInput.readUTF();
		companyName = objectInput.readUTF();
		endDate = objectInput.readLong();

		companyIdentifierSid = objectInput.readInt();
		modifiedDate = objectInput.readLong();
		identifierStatus = objectInput.readUTF();
		companyIdentifier = objectInput.readUTF();
		entityCode = objectInput.readUTF();
		startDate = objectInput.readLong();
		createdDate = objectInput.readLong();
		createdBy = objectInput.readUTF();
		source = objectInput.readUTF();
		companyNo = objectInput.readUTF();
		batchId = objectInput.readUTF();
		addChgDelIndicator = objectInput.readUTF();
		identifierCodeQualifierName = objectInput.readUTF();
		modifiedBy = objectInput.readUTF();
		identifierCodeQualifier = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		if (companyId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(companyId);
		}

		if (companyName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(companyName);
		}

		objectOutput.writeLong(endDate);

		objectOutput.writeInt(companyIdentifierSid);
		objectOutput.writeLong(modifiedDate);

		if (identifierStatus == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(identifierStatus);
		}

		if (companyIdentifier == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(companyIdentifier);
		}

		if (entityCode == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(entityCode);
		}

		objectOutput.writeLong(startDate);
		objectOutput.writeLong(createdDate);

		if (createdBy == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(createdBy);
		}

		if (source == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(source);
		}

		if (companyNo == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(companyNo);
		}

		if (batchId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(batchId);
		}

		if (addChgDelIndicator == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(addChgDelIndicator);
		}

		if (identifierCodeQualifierName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(identifierCodeQualifierName);
		}

		if (modifiedBy == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(modifiedBy);
		}

		if (identifierCodeQualifier == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(identifierCodeQualifier);
		}
	}

	public String companyId;
	public String companyName;
	public long endDate;
	public int companyIdentifierSid;
	public long modifiedDate;
	public String identifierStatus;
	public String companyIdentifier;
	public String entityCode;
	public long startDate;
	public long createdDate;
	public String createdBy;
	public String source;
	public String companyNo;
	public String batchId;
	public String addChgDelIndicator;
	public String identifierCodeQualifierName;
	public String modifiedBy;
	public String identifierCodeQualifier;
}