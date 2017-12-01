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

import com.stpl.app.model.CompanyIdentifier;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing CompanyIdentifier in entity cache.
 *
 * @author
 * @see CompanyIdentifier
 * @generated
 */
@ProviderType
public class CompanyIdentifierCacheModel implements CacheModel<CompanyIdentifier>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CompanyIdentifierCacheModel)) {
			return false;
		}

		CompanyIdentifierCacheModel companyIdentifierCacheModel = (CompanyIdentifierCacheModel)obj;

		if (companyStringIdentifierSid == companyIdentifierCacheModel.companyStringIdentifierSid) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, companyStringIdentifierSid);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(33);

		sb.append("{endDate=");
		sb.append(endDate);
		sb.append(", companyStringIdentifierSid=");
		sb.append(companyStringIdentifierSid);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", identifierStatus=");
		sb.append(identifierStatus);
		sb.append(", entityCode=");
		sb.append(entityCode);
		sb.append(", recordLockStatus=");
		sb.append(recordLockStatus);
		sb.append(", startDate=");
		sb.append(startDate);
		sb.append(", createdDate=");
		sb.append(createdDate);
		sb.append(", source=");
		sb.append(source);
		sb.append(", createdBy=");
		sb.append(createdBy);
		sb.append(", companyStringIdentifierValue=");
		sb.append(companyStringIdentifierValue);
		sb.append(", batchId=");
		sb.append(batchId);
		sb.append(", companyQualifierSid=");
		sb.append(companyQualifierSid);
		sb.append(", modifiedBy=");
		sb.append(modifiedBy);
		sb.append(", inboundStatus=");
		sb.append(inboundStatus);
		sb.append(", companyMasterSid=");
		sb.append(companyMasterSid);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public CompanyIdentifier toEntityModel() {
		CompanyIdentifierImpl companyIdentifierImpl = new CompanyIdentifierImpl();

		if (endDate == Long.MIN_VALUE) {
			companyIdentifierImpl.setEndDate(null);
		}
		else {
			companyIdentifierImpl.setEndDate(new Date(endDate));
		}

		companyIdentifierImpl.setCompanyStringIdentifierSid(companyStringIdentifierSid);

		if (modifiedDate == Long.MIN_VALUE) {
			companyIdentifierImpl.setModifiedDate(null);
		}
		else {
			companyIdentifierImpl.setModifiedDate(new Date(modifiedDate));
		}

		companyIdentifierImpl.setIdentifierStatus(identifierStatus);

		if (entityCode == null) {
			companyIdentifierImpl.setEntityCode(StringPool.BLANK);
		}
		else {
			companyIdentifierImpl.setEntityCode(entityCode);
		}

		companyIdentifierImpl.setRecordLockStatus(recordLockStatus);

		if (startDate == Long.MIN_VALUE) {
			companyIdentifierImpl.setStartDate(null);
		}
		else {
			companyIdentifierImpl.setStartDate(new Date(startDate));
		}

		if (createdDate == Long.MIN_VALUE) {
			companyIdentifierImpl.setCreatedDate(null);
		}
		else {
			companyIdentifierImpl.setCreatedDate(new Date(createdDate));
		}

		if (source == null) {
			companyIdentifierImpl.setSource(StringPool.BLANK);
		}
		else {
			companyIdentifierImpl.setSource(source);
		}

		companyIdentifierImpl.setCreatedBy(createdBy);

		if (companyStringIdentifierValue == null) {
			companyIdentifierImpl.setCompanyStringIdentifierValue(StringPool.BLANK);
		}
		else {
			companyIdentifierImpl.setCompanyStringIdentifierValue(companyStringIdentifierValue);
		}

		if (batchId == null) {
			companyIdentifierImpl.setBatchId(StringPool.BLANK);
		}
		else {
			companyIdentifierImpl.setBatchId(batchId);
		}

		companyIdentifierImpl.setCompanyQualifierSid(companyQualifierSid);
		companyIdentifierImpl.setModifiedBy(modifiedBy);

		if (inboundStatus == null) {
			companyIdentifierImpl.setInboundStatus(StringPool.BLANK);
		}
		else {
			companyIdentifierImpl.setInboundStatus(inboundStatus);
		}

		companyIdentifierImpl.setCompanyMasterSid(companyMasterSid);

		companyIdentifierImpl.resetOriginalValues();

		return companyIdentifierImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		endDate = objectInput.readLong();

		companyStringIdentifierSid = objectInput.readInt();
		modifiedDate = objectInput.readLong();

		identifierStatus = objectInput.readInt();
		entityCode = objectInput.readUTF();

		recordLockStatus = objectInput.readBoolean();
		startDate = objectInput.readLong();
		createdDate = objectInput.readLong();
		source = objectInput.readUTF();

		createdBy = objectInput.readInt();
		companyStringIdentifierValue = objectInput.readUTF();
		batchId = objectInput.readUTF();

		companyQualifierSid = objectInput.readInt();

		modifiedBy = objectInput.readInt();
		inboundStatus = objectInput.readUTF();

		companyMasterSid = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(endDate);

		objectOutput.writeInt(companyStringIdentifierSid);
		objectOutput.writeLong(modifiedDate);

		objectOutput.writeInt(identifierStatus);

		if (entityCode == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(entityCode);
		}

		objectOutput.writeBoolean(recordLockStatus);
		objectOutput.writeLong(startDate);
		objectOutput.writeLong(createdDate);

		if (source == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(source);
		}

		objectOutput.writeInt(createdBy);

		if (companyStringIdentifierValue == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(companyStringIdentifierValue);
		}

		if (batchId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(batchId);
		}

		objectOutput.writeInt(companyQualifierSid);

		objectOutput.writeInt(modifiedBy);

		if (inboundStatus == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(inboundStatus);
		}

		objectOutput.writeInt(companyMasterSid);
	}

	public long endDate;
	public int companyStringIdentifierSid;
	public long modifiedDate;
	public int identifierStatus;
	public String entityCode;
	public boolean recordLockStatus;
	public long startDate;
	public long createdDate;
	public String source;
	public int createdBy;
	public String companyStringIdentifierValue;
	public String batchId;
	public int companyQualifierSid;
	public int modifiedBy;
	public String inboundStatus;
	public int companyMasterSid;
}