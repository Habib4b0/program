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

import com.stpl.app.model.CompanyParentDetails;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing CompanyParentDetails in entity cache.
 *
 * @author
 * @see CompanyParentDetails
 * @generated
 */
@ProviderType
public class CompanyParentDetailsCacheModel implements CacheModel<CompanyParentDetails>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CompanyParentDetailsCacheModel)) {
			return false;
		}

		CompanyParentDetailsCacheModel companyParentDetailsCacheModel = (CompanyParentDetailsCacheModel)obj;

		if (companyParentDetailsSid == companyParentDetailsCacheModel.companyParentDetailsSid) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, companyParentDetailsSid);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(33);

		sb.append("{lastUpdatedDate=");
		sb.append(lastUpdatedDate);
		sb.append(", parentEndDate=");
		sb.append(parentEndDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", parentCompanyMasterSid=");
		sb.append(parentCompanyMasterSid);
		sb.append(", recordLockStatus=");
		sb.append(recordLockStatus);
		sb.append(", priorParentStartDate=");
		sb.append(priorParentStartDate);
		sb.append(", createdDate=");
		sb.append(createdDate);
		sb.append(", source=");
		sb.append(source);
		sb.append(", createdBy=");
		sb.append(createdBy);
		sb.append(", companyParentDetailsSid=");
		sb.append(companyParentDetailsSid);
		sb.append(", priorParentCmpyMasterSid=");
		sb.append(priorParentCmpyMasterSid);
		sb.append(", batchId=");
		sb.append(batchId);
		sb.append(", modifiedBy=");
		sb.append(modifiedBy);
		sb.append(", inboundStatus=");
		sb.append(inboundStatus);
		sb.append(", companyMasterSid=");
		sb.append(companyMasterSid);
		sb.append(", parentStartDate=");
		sb.append(parentStartDate);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public CompanyParentDetails toEntityModel() {
		CompanyParentDetailsImpl companyParentDetailsImpl = new CompanyParentDetailsImpl();

		if (lastUpdatedDate == Long.MIN_VALUE) {
			companyParentDetailsImpl.setLastUpdatedDate(null);
		}
		else {
			companyParentDetailsImpl.setLastUpdatedDate(new Date(
					lastUpdatedDate));
		}

		if (parentEndDate == Long.MIN_VALUE) {
			companyParentDetailsImpl.setParentEndDate(null);
		}
		else {
			companyParentDetailsImpl.setParentEndDate(new Date(parentEndDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			companyParentDetailsImpl.setModifiedDate(null);
		}
		else {
			companyParentDetailsImpl.setModifiedDate(new Date(modifiedDate));
		}

		companyParentDetailsImpl.setParentCompanyMasterSid(parentCompanyMasterSid);
		companyParentDetailsImpl.setRecordLockStatus(recordLockStatus);

		if (priorParentStartDate == Long.MIN_VALUE) {
			companyParentDetailsImpl.setPriorParentStartDate(null);
		}
		else {
			companyParentDetailsImpl.setPriorParentStartDate(new Date(
					priorParentStartDate));
		}

		if (createdDate == Long.MIN_VALUE) {
			companyParentDetailsImpl.setCreatedDate(null);
		}
		else {
			companyParentDetailsImpl.setCreatedDate(new Date(createdDate));
		}

		if (source == null) {
			companyParentDetailsImpl.setSource(StringPool.BLANK);
		}
		else {
			companyParentDetailsImpl.setSource(source);
		}

		companyParentDetailsImpl.setCreatedBy(createdBy);
		companyParentDetailsImpl.setCompanyParentDetailsSid(companyParentDetailsSid);

		if (priorParentCmpyMasterSid == null) {
			companyParentDetailsImpl.setPriorParentCmpyMasterSid(StringPool.BLANK);
		}
		else {
			companyParentDetailsImpl.setPriorParentCmpyMasterSid(priorParentCmpyMasterSid);
		}

		if (batchId == null) {
			companyParentDetailsImpl.setBatchId(StringPool.BLANK);
		}
		else {
			companyParentDetailsImpl.setBatchId(batchId);
		}

		companyParentDetailsImpl.setModifiedBy(modifiedBy);

		if (inboundStatus == null) {
			companyParentDetailsImpl.setInboundStatus(StringPool.BLANK);
		}
		else {
			companyParentDetailsImpl.setInboundStatus(inboundStatus);
		}

		companyParentDetailsImpl.setCompanyMasterSid(companyMasterSid);

		if (parentStartDate == Long.MIN_VALUE) {
			companyParentDetailsImpl.setParentStartDate(null);
		}
		else {
			companyParentDetailsImpl.setParentStartDate(new Date(
					parentStartDate));
		}

		companyParentDetailsImpl.resetOriginalValues();

		return companyParentDetailsImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		lastUpdatedDate = objectInput.readLong();
		parentEndDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		parentCompanyMasterSid = objectInput.readInt();

		recordLockStatus = objectInput.readBoolean();
		priorParentStartDate = objectInput.readLong();
		createdDate = objectInput.readLong();
		source = objectInput.readUTF();

		createdBy = objectInput.readInt();

		companyParentDetailsSid = objectInput.readInt();
		priorParentCmpyMasterSid = objectInput.readUTF();
		batchId = objectInput.readUTF();

		modifiedBy = objectInput.readInt();
		inboundStatus = objectInput.readUTF();

		companyMasterSid = objectInput.readInt();
		parentStartDate = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(lastUpdatedDate);
		objectOutput.writeLong(parentEndDate);
		objectOutput.writeLong(modifiedDate);

		objectOutput.writeInt(parentCompanyMasterSid);

		objectOutput.writeBoolean(recordLockStatus);
		objectOutput.writeLong(priorParentStartDate);
		objectOutput.writeLong(createdDate);

		if (source == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(source);
		}

		objectOutput.writeInt(createdBy);

		objectOutput.writeInt(companyParentDetailsSid);

		if (priorParentCmpyMasterSid == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(priorParentCmpyMasterSid);
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

		objectOutput.writeInt(companyMasterSid);
		objectOutput.writeLong(parentStartDate);
	}

	public long lastUpdatedDate;
	public long parentEndDate;
	public long modifiedDate;
	public int parentCompanyMasterSid;
	public boolean recordLockStatus;
	public long priorParentStartDate;
	public long createdDate;
	public String source;
	public int createdBy;
	public int companyParentDetailsSid;
	public String priorParentCmpyMasterSid;
	public String batchId;
	public int modifiedBy;
	public String inboundStatus;
	public int companyMasterSid;
	public long parentStartDate;
}