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

import com.stpl.app.model.RebatePlanMaster;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing RebatePlanMaster in entity cache.
 *
 * @author
 * @see RebatePlanMaster
 * @generated
 */
@ProviderType
public class RebatePlanMasterCacheModel implements CacheModel<RebatePlanMaster>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof RebatePlanMasterCacheModel)) {
			return false;
		}

		RebatePlanMasterCacheModel rebatePlanMasterCacheModel = (RebatePlanMasterCacheModel)obj;

		if (rebatePlanMasterSid == rebatePlanMasterCacheModel.rebatePlanMasterSid) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, rebatePlanMasterSid);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(71);

		sb.append("{selfGrowthIndicator=");
		sb.append(selfGrowthIndicator);
		sb.append(", rebateStructure=");
		sb.append(rebateStructure);
		sb.append(", marketShareFrom=");
		sb.append(marketShareFrom);
		sb.append(", secondaryRebatePlanNo=");
		sb.append(secondaryRebatePlanNo);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", rebateRangeBasedOn=");
		sb.append(rebateRangeBasedOn);
		sb.append(", cdrModelSid=");
		sb.append(cdrModelSid);
		sb.append(", rebateRule=");
		sb.append(rebateRule);
		sb.append(", createdDate=");
		sb.append(createdDate);
		sb.append(", createdBy=");
		sb.append(createdBy);
		sb.append(", source=");
		sb.append(source);
		sb.append(", rebateBasedOn=");
		sb.append(rebateBasedOn);
		sb.append(", rebatePlanType=");
		sb.append(rebatePlanType);
		sb.append(", rebatePlanId=");
		sb.append(rebatePlanId);
		sb.append(", manfCompanyMasterSid=");
		sb.append(manfCompanyMasterSid);
		sb.append(", modifiedBy=");
		sb.append(modifiedBy);
		sb.append(", inboundStatus=");
		sb.append(inboundStatus);
		sb.append(", secondaryRebatePlanId=");
		sb.append(secondaryRebatePlanId);
		sb.append(", marketShareIndicator=");
		sb.append(marketShareIndicator);
		sb.append(", bogoEligible=");
		sb.append(bogoEligible);
		sb.append(", marketShareTo=");
		sb.append(marketShareTo);
		sb.append(", rebatePlanStatus=");
		sb.append(rebatePlanStatus);
		sb.append(", rebatePlanMasterSid=");
		sb.append(rebatePlanMasterSid);
		sb.append(", marketShareReference=");
		sb.append(marketShareReference);
		sb.append(", netSalesFormulaMasterSid=");
		sb.append(netSalesFormulaMasterSid);
		sb.append(", selfGrowthFrom=");
		sb.append(selfGrowthFrom);
		sb.append(", internalNotes=");
		sb.append(internalNotes);
		sb.append(", secondaryRebatePlanName=");
		sb.append(secondaryRebatePlanName);
		sb.append(", recordLockStatus=");
		sb.append(recordLockStatus);
		sb.append(", rebatePlanName=");
		sb.append(rebatePlanName);
		sb.append(", selfGrowthReference=");
		sb.append(selfGrowthReference);
		sb.append(", batchId=");
		sb.append(batchId);
		sb.append(", formulaType=");
		sb.append(formulaType);
		sb.append(", selfGrowthTo=");
		sb.append(selfGrowthTo);
		sb.append(", rebatePlanNo=");
		sb.append(rebatePlanNo);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public RebatePlanMaster toEntityModel() {
		RebatePlanMasterImpl rebatePlanMasterImpl = new RebatePlanMasterImpl();

		if (selfGrowthIndicator == null) {
			rebatePlanMasterImpl.setSelfGrowthIndicator(StringPool.BLANK);
		}
		else {
			rebatePlanMasterImpl.setSelfGrowthIndicator(selfGrowthIndicator);
		}

		if (rebateStructure == null) {
			rebatePlanMasterImpl.setRebateStructure(StringPool.BLANK);
		}
		else {
			rebatePlanMasterImpl.setRebateStructure(rebateStructure);
		}

		if (marketShareFrom == Long.MIN_VALUE) {
			rebatePlanMasterImpl.setMarketShareFrom(null);
		}
		else {
			rebatePlanMasterImpl.setMarketShareFrom(new Date(marketShareFrom));
		}

		if (secondaryRebatePlanNo == null) {
			rebatePlanMasterImpl.setSecondaryRebatePlanNo(StringPool.BLANK);
		}
		else {
			rebatePlanMasterImpl.setSecondaryRebatePlanNo(secondaryRebatePlanNo);
		}

		if (modifiedDate == Long.MIN_VALUE) {
			rebatePlanMasterImpl.setModifiedDate(null);
		}
		else {
			rebatePlanMasterImpl.setModifiedDate(new Date(modifiedDate));
		}

		rebatePlanMasterImpl.setRebateRangeBasedOn(rebateRangeBasedOn);

		if (cdrModelSid == null) {
			rebatePlanMasterImpl.setCdrModelSid(StringPool.BLANK);
		}
		else {
			rebatePlanMasterImpl.setCdrModelSid(cdrModelSid);
		}

		if (rebateRule == null) {
			rebatePlanMasterImpl.setRebateRule(StringPool.BLANK);
		}
		else {
			rebatePlanMasterImpl.setRebateRule(rebateRule);
		}

		if (createdDate == Long.MIN_VALUE) {
			rebatePlanMasterImpl.setCreatedDate(null);
		}
		else {
			rebatePlanMasterImpl.setCreatedDate(new Date(createdDate));
		}

		rebatePlanMasterImpl.setCreatedBy(createdBy);

		if (source == null) {
			rebatePlanMasterImpl.setSource(StringPool.BLANK);
		}
		else {
			rebatePlanMasterImpl.setSource(source);
		}

		rebatePlanMasterImpl.setRebateBasedOn(rebateBasedOn);
		rebatePlanMasterImpl.setRebatePlanType(rebatePlanType);

		if (rebatePlanId == null) {
			rebatePlanMasterImpl.setRebatePlanId(StringPool.BLANK);
		}
		else {
			rebatePlanMasterImpl.setRebatePlanId(rebatePlanId);
		}

		rebatePlanMasterImpl.setManfCompanyMasterSid(manfCompanyMasterSid);
		rebatePlanMasterImpl.setModifiedBy(modifiedBy);

		if (inboundStatus == null) {
			rebatePlanMasterImpl.setInboundStatus(StringPool.BLANK);
		}
		else {
			rebatePlanMasterImpl.setInboundStatus(inboundStatus);
		}

		if (secondaryRebatePlanId == null) {
			rebatePlanMasterImpl.setSecondaryRebatePlanId(StringPool.BLANK);
		}
		else {
			rebatePlanMasterImpl.setSecondaryRebatePlanId(secondaryRebatePlanId);
		}

		if (marketShareIndicator == null) {
			rebatePlanMasterImpl.setMarketShareIndicator(StringPool.BLANK);
		}
		else {
			rebatePlanMasterImpl.setMarketShareIndicator(marketShareIndicator);
		}

		if (bogoEligible == null) {
			rebatePlanMasterImpl.setBogoEligible(StringPool.BLANK);
		}
		else {
			rebatePlanMasterImpl.setBogoEligible(bogoEligible);
		}

		if (marketShareTo == Long.MIN_VALUE) {
			rebatePlanMasterImpl.setMarketShareTo(null);
		}
		else {
			rebatePlanMasterImpl.setMarketShareTo(new Date(marketShareTo));
		}

		rebatePlanMasterImpl.setRebatePlanStatus(rebatePlanStatus);
		rebatePlanMasterImpl.setRebatePlanMasterSid(rebatePlanMasterSid);

		if (marketShareReference == null) {
			rebatePlanMasterImpl.setMarketShareReference(StringPool.BLANK);
		}
		else {
			rebatePlanMasterImpl.setMarketShareReference(marketShareReference);
		}

		if (netSalesFormulaMasterSid == null) {
			rebatePlanMasterImpl.setNetSalesFormulaMasterSid(StringPool.BLANK);
		}
		else {
			rebatePlanMasterImpl.setNetSalesFormulaMasterSid(netSalesFormulaMasterSid);
		}

		if (selfGrowthFrom == Long.MIN_VALUE) {
			rebatePlanMasterImpl.setSelfGrowthFrom(null);
		}
		else {
			rebatePlanMasterImpl.setSelfGrowthFrom(new Date(selfGrowthFrom));
		}

		if (internalNotes == null) {
			rebatePlanMasterImpl.setInternalNotes(StringPool.BLANK);
		}
		else {
			rebatePlanMasterImpl.setInternalNotes(internalNotes);
		}

		if (secondaryRebatePlanName == null) {
			rebatePlanMasterImpl.setSecondaryRebatePlanName(StringPool.BLANK);
		}
		else {
			rebatePlanMasterImpl.setSecondaryRebatePlanName(secondaryRebatePlanName);
		}

		rebatePlanMasterImpl.setRecordLockStatus(recordLockStatus);

		if (rebatePlanName == null) {
			rebatePlanMasterImpl.setRebatePlanName(StringPool.BLANK);
		}
		else {
			rebatePlanMasterImpl.setRebatePlanName(rebatePlanName);
		}

		if (selfGrowthReference == null) {
			rebatePlanMasterImpl.setSelfGrowthReference(StringPool.BLANK);
		}
		else {
			rebatePlanMasterImpl.setSelfGrowthReference(selfGrowthReference);
		}

		if (batchId == null) {
			rebatePlanMasterImpl.setBatchId(StringPool.BLANK);
		}
		else {
			rebatePlanMasterImpl.setBatchId(batchId);
		}

		rebatePlanMasterImpl.setFormulaType(formulaType);

		if (selfGrowthTo == Long.MIN_VALUE) {
			rebatePlanMasterImpl.setSelfGrowthTo(null);
		}
		else {
			rebatePlanMasterImpl.setSelfGrowthTo(new Date(selfGrowthTo));
		}

		if (rebatePlanNo == null) {
			rebatePlanMasterImpl.setRebatePlanNo(StringPool.BLANK);
		}
		else {
			rebatePlanMasterImpl.setRebatePlanNo(rebatePlanNo);
		}

		rebatePlanMasterImpl.resetOriginalValues();

		return rebatePlanMasterImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		selfGrowthIndicator = objectInput.readUTF();
		rebateStructure = objectInput.readUTF();
		marketShareFrom = objectInput.readLong();
		secondaryRebatePlanNo = objectInput.readUTF();
		modifiedDate = objectInput.readLong();

		rebateRangeBasedOn = objectInput.readInt();
		cdrModelSid = objectInput.readUTF();
		rebateRule = objectInput.readUTF();
		createdDate = objectInput.readLong();

		createdBy = objectInput.readInt();
		source = objectInput.readUTF();

		rebateBasedOn = objectInput.readInt();

		rebatePlanType = objectInput.readInt();
		rebatePlanId = objectInput.readUTF();

		manfCompanyMasterSid = objectInput.readInt();

		modifiedBy = objectInput.readInt();
		inboundStatus = objectInput.readUTF();
		secondaryRebatePlanId = objectInput.readUTF();
		marketShareIndicator = objectInput.readUTF();
		bogoEligible = objectInput.readUTF();
		marketShareTo = objectInput.readLong();

		rebatePlanStatus = objectInput.readInt();

		rebatePlanMasterSid = objectInput.readInt();
		marketShareReference = objectInput.readUTF();
		netSalesFormulaMasterSid = objectInput.readUTF();
		selfGrowthFrom = objectInput.readLong();
		internalNotes = objectInput.readUTF();
		secondaryRebatePlanName = objectInput.readUTF();

		recordLockStatus = objectInput.readBoolean();
		rebatePlanName = objectInput.readUTF();
		selfGrowthReference = objectInput.readUTF();
		batchId = objectInput.readUTF();

		formulaType = objectInput.readInt();
		selfGrowthTo = objectInput.readLong();
		rebatePlanNo = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		if (selfGrowthIndicator == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(selfGrowthIndicator);
		}

		if (rebateStructure == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(rebateStructure);
		}

		objectOutput.writeLong(marketShareFrom);

		if (secondaryRebatePlanNo == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(secondaryRebatePlanNo);
		}

		objectOutput.writeLong(modifiedDate);

		objectOutput.writeInt(rebateRangeBasedOn);

		if (cdrModelSid == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(cdrModelSid);
		}

		if (rebateRule == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(rebateRule);
		}

		objectOutput.writeLong(createdDate);

		objectOutput.writeInt(createdBy);

		if (source == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(source);
		}

		objectOutput.writeInt(rebateBasedOn);

		objectOutput.writeInt(rebatePlanType);

		if (rebatePlanId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(rebatePlanId);
		}

		objectOutput.writeInt(manfCompanyMasterSid);

		objectOutput.writeInt(modifiedBy);

		if (inboundStatus == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(inboundStatus);
		}

		if (secondaryRebatePlanId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(secondaryRebatePlanId);
		}

		if (marketShareIndicator == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(marketShareIndicator);
		}

		if (bogoEligible == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(bogoEligible);
		}

		objectOutput.writeLong(marketShareTo);

		objectOutput.writeInt(rebatePlanStatus);

		objectOutput.writeInt(rebatePlanMasterSid);

		if (marketShareReference == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(marketShareReference);
		}

		if (netSalesFormulaMasterSid == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(netSalesFormulaMasterSid);
		}

		objectOutput.writeLong(selfGrowthFrom);

		if (internalNotes == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(internalNotes);
		}

		if (secondaryRebatePlanName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(secondaryRebatePlanName);
		}

		objectOutput.writeBoolean(recordLockStatus);

		if (rebatePlanName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(rebatePlanName);
		}

		if (selfGrowthReference == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(selfGrowthReference);
		}

		if (batchId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(batchId);
		}

		objectOutput.writeInt(formulaType);
		objectOutput.writeLong(selfGrowthTo);

		if (rebatePlanNo == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(rebatePlanNo);
		}
	}

	public String selfGrowthIndicator;
	public String rebateStructure;
	public long marketShareFrom;
	public String secondaryRebatePlanNo;
	public long modifiedDate;
	public int rebateRangeBasedOn;
	public String cdrModelSid;
	public String rebateRule;
	public long createdDate;
	public int createdBy;
	public String source;
	public int rebateBasedOn;
	public int rebatePlanType;
	public String rebatePlanId;
	public int manfCompanyMasterSid;
	public int modifiedBy;
	public String inboundStatus;
	public String secondaryRebatePlanId;
	public String marketShareIndicator;
	public String bogoEligible;
	public long marketShareTo;
	public int rebatePlanStatus;
	public int rebatePlanMasterSid;
	public String marketShareReference;
	public String netSalesFormulaMasterSid;
	public long selfGrowthFrom;
	public String internalNotes;
	public String secondaryRebatePlanName;
	public boolean recordLockStatus;
	public String rebatePlanName;
	public String selfGrowthReference;
	public String batchId;
	public int formulaType;
	public long selfGrowthTo;
	public String rebatePlanNo;
}