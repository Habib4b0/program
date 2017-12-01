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

import com.stpl.app.model.IfpContractDetails;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing IfpContractDetails in entity cache.
 *
 * @author
 * @see IfpContractDetails
 * @generated
 */
@ProviderType
public class IfpContractDetailsCacheModel implements CacheModel<IfpContractDetails>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof IfpContractDetailsCacheModel)) {
			return false;
		}

		IfpContractDetailsCacheModel ifpContractDetailsCacheModel = (IfpContractDetailsCacheModel)obj;

		if (ifpContractDetailsSid == ifpContractDetailsCacheModel.ifpContractDetailsSid) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, ifpContractDetailsSid);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(41);

		sb.append("{itemStatus=");
		sb.append(itemStatus);
		sb.append(", itemMasterSid=");
		sb.append(itemMasterSid);
		sb.append(", ifpContractAttachedDate=");
		sb.append(ifpContractAttachedDate);
		sb.append(", itemEndDate=");
		sb.append(itemEndDate);
		sb.append(", totalVolumeCommitment=");
		sb.append(totalVolumeCommitment);
		sb.append(", totalDollarCommitment=");
		sb.append(totalDollarCommitment);
		sb.append(", ifpContractAttachedStatus=");
		sb.append(ifpContractAttachedStatus);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", totalMarketshareCommitment=");
		sb.append(totalMarketshareCommitment);
		sb.append(", recordLockStatus=");
		sb.append(recordLockStatus);
		sb.append(", createdDate=");
		sb.append(createdDate);
		sb.append(", createdBy=");
		sb.append(createdBy);
		sb.append(", source=");
		sb.append(source);
		sb.append(", itemStartDate=");
		sb.append(itemStartDate);
		sb.append(", batchId=");
		sb.append(batchId);
		sb.append(", ifpContractDetailsSid=");
		sb.append(ifpContractDetailsSid);
		sb.append(", modifiedBy=");
		sb.append(modifiedBy);
		sb.append(", inboundStatus=");
		sb.append(inboundStatus);
		sb.append(", ifpContractSid=");
		sb.append(ifpContractSid);
		sb.append(", commitmentPeriod=");
		sb.append(commitmentPeriod);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public IfpContractDetails toEntityModel() {
		IfpContractDetailsImpl ifpContractDetailsImpl = new IfpContractDetailsImpl();

		ifpContractDetailsImpl.setItemStatus(itemStatus);
		ifpContractDetailsImpl.setItemMasterSid(itemMasterSid);

		if (ifpContractAttachedDate == Long.MIN_VALUE) {
			ifpContractDetailsImpl.setIfpContractAttachedDate(null);
		}
		else {
			ifpContractDetailsImpl.setIfpContractAttachedDate(new Date(
					ifpContractAttachedDate));
		}

		if (itemEndDate == Long.MIN_VALUE) {
			ifpContractDetailsImpl.setItemEndDate(null);
		}
		else {
			ifpContractDetailsImpl.setItemEndDate(new Date(itemEndDate));
		}

		if (totalVolumeCommitment == null) {
			ifpContractDetailsImpl.setTotalVolumeCommitment(StringPool.BLANK);
		}
		else {
			ifpContractDetailsImpl.setTotalVolumeCommitment(totalVolumeCommitment);
		}

		if (totalDollarCommitment == null) {
			ifpContractDetailsImpl.setTotalDollarCommitment(StringPool.BLANK);
		}
		else {
			ifpContractDetailsImpl.setTotalDollarCommitment(totalDollarCommitment);
		}

		ifpContractDetailsImpl.setIfpContractAttachedStatus(ifpContractAttachedStatus);

		if (modifiedDate == Long.MIN_VALUE) {
			ifpContractDetailsImpl.setModifiedDate(null);
		}
		else {
			ifpContractDetailsImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (totalMarketshareCommitment == null) {
			ifpContractDetailsImpl.setTotalMarketshareCommitment(StringPool.BLANK);
		}
		else {
			ifpContractDetailsImpl.setTotalMarketshareCommitment(totalMarketshareCommitment);
		}

		ifpContractDetailsImpl.setRecordLockStatus(recordLockStatus);

		if (createdDate == Long.MIN_VALUE) {
			ifpContractDetailsImpl.setCreatedDate(null);
		}
		else {
			ifpContractDetailsImpl.setCreatedDate(new Date(createdDate));
		}

		ifpContractDetailsImpl.setCreatedBy(createdBy);

		if (source == null) {
			ifpContractDetailsImpl.setSource(StringPool.BLANK);
		}
		else {
			ifpContractDetailsImpl.setSource(source);
		}

		if (itemStartDate == Long.MIN_VALUE) {
			ifpContractDetailsImpl.setItemStartDate(null);
		}
		else {
			ifpContractDetailsImpl.setItemStartDate(new Date(itemStartDate));
		}

		if (batchId == null) {
			ifpContractDetailsImpl.setBatchId(StringPool.BLANK);
		}
		else {
			ifpContractDetailsImpl.setBatchId(batchId);
		}

		ifpContractDetailsImpl.setIfpContractDetailsSid(ifpContractDetailsSid);
		ifpContractDetailsImpl.setModifiedBy(modifiedBy);

		if (inboundStatus == null) {
			ifpContractDetailsImpl.setInboundStatus(StringPool.BLANK);
		}
		else {
			ifpContractDetailsImpl.setInboundStatus(inboundStatus);
		}

		ifpContractDetailsImpl.setIfpContractSid(ifpContractSid);

		if (commitmentPeriod == null) {
			ifpContractDetailsImpl.setCommitmentPeriod(StringPool.BLANK);
		}
		else {
			ifpContractDetailsImpl.setCommitmentPeriod(commitmentPeriod);
		}

		ifpContractDetailsImpl.resetOriginalValues();

		return ifpContractDetailsImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		itemStatus = objectInput.readInt();

		itemMasterSid = objectInput.readInt();
		ifpContractAttachedDate = objectInput.readLong();
		itemEndDate = objectInput.readLong();
		totalVolumeCommitment = objectInput.readUTF();
		totalDollarCommitment = objectInput.readUTF();

		ifpContractAttachedStatus = objectInput.readInt();
		modifiedDate = objectInput.readLong();
		totalMarketshareCommitment = objectInput.readUTF();

		recordLockStatus = objectInput.readBoolean();
		createdDate = objectInput.readLong();

		createdBy = objectInput.readInt();
		source = objectInput.readUTF();
		itemStartDate = objectInput.readLong();
		batchId = objectInput.readUTF();

		ifpContractDetailsSid = objectInput.readInt();

		modifiedBy = objectInput.readInt();
		inboundStatus = objectInput.readUTF();

		ifpContractSid = objectInput.readInt();
		commitmentPeriod = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeInt(itemStatus);

		objectOutput.writeInt(itemMasterSid);
		objectOutput.writeLong(ifpContractAttachedDate);
		objectOutput.writeLong(itemEndDate);

		if (totalVolumeCommitment == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(totalVolumeCommitment);
		}

		if (totalDollarCommitment == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(totalDollarCommitment);
		}

		objectOutput.writeInt(ifpContractAttachedStatus);
		objectOutput.writeLong(modifiedDate);

		if (totalMarketshareCommitment == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(totalMarketshareCommitment);
		}

		objectOutput.writeBoolean(recordLockStatus);
		objectOutput.writeLong(createdDate);

		objectOutput.writeInt(createdBy);

		if (source == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(source);
		}

		objectOutput.writeLong(itemStartDate);

		if (batchId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(batchId);
		}

		objectOutput.writeInt(ifpContractDetailsSid);

		objectOutput.writeInt(modifiedBy);

		if (inboundStatus == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(inboundStatus);
		}

		objectOutput.writeInt(ifpContractSid);

		if (commitmentPeriod == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(commitmentPeriod);
		}
	}

	public int itemStatus;
	public int itemMasterSid;
	public long ifpContractAttachedDate;
	public long itemEndDate;
	public String totalVolumeCommitment;
	public String totalDollarCommitment;
	public int ifpContractAttachedStatus;
	public long modifiedDate;
	public String totalMarketshareCommitment;
	public boolean recordLockStatus;
	public long createdDate;
	public int createdBy;
	public String source;
	public long itemStartDate;
	public String batchId;
	public int ifpContractDetailsSid;
	public int modifiedBy;
	public String inboundStatus;
	public int ifpContractSid;
	public String commitmentPeriod;
}