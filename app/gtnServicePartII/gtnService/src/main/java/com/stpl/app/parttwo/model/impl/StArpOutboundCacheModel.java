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

import com.stpl.app.parttwo.model.StArpOutbound;
import com.stpl.app.parttwo.service.persistence.StArpOutboundPK;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing StArpOutbound in entity cache.
 *
 * @author
 * @see StArpOutbound
 * @generated
 */
@ProviderType
public class StArpOutboundCacheModel implements CacheModel<StArpOutbound>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof StArpOutboundCacheModel)) {
			return false;
		}

		StArpOutboundCacheModel stArpOutboundCacheModel = (StArpOutboundCacheModel)obj;

		if (stArpOutboundPK.equals(stArpOutboundCacheModel.stArpOutboundPK)) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, stArpOutboundPK);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(35);

		sb.append("{salesUnitsRate=");
		sb.append(salesUnitsRate);
		sb.append(", accountType=");
		sb.append(accountType);
		sb.append(", originalBatchId=");
		sb.append(originalBatchId);
		sb.append(", companyMasterSid=");
		sb.append(companyMasterSid);
		sb.append(", brandMasterSid=");
		sb.append(brandMasterSid);
		sb.append(", arpApprovalDate=");
		sb.append(arpApprovalDate);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", arpMasterSid=");
		sb.append(arpMasterSid);
		sb.append(", arpCreationDate=");
		sb.append(arpCreationDate);
		sb.append(", checkRecord=");
		sb.append(checkRecord);
		sb.append(", arpId=");
		sb.append(arpId);
		sb.append(", account=");
		sb.append(account);
		sb.append(", outboundStatus=");
		sb.append(outboundStatus);
		sb.append(", periodSid=");
		sb.append(periodSid);
		sb.append(", itemMasterSid=");
		sb.append(itemMasterSid);
		sb.append(", rsModelSid=");
		sb.append(rsModelSid);
		sb.append(", sessionId=");
		sb.append(sessionId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public StArpOutbound toEntityModel() {
		StArpOutboundImpl stArpOutboundImpl = new StArpOutboundImpl();

		stArpOutboundImpl.setSalesUnitsRate(salesUnitsRate);

		if (accountType == null) {
			stArpOutboundImpl.setAccountType(StringPool.BLANK);
		}
		else {
			stArpOutboundImpl.setAccountType(accountType);
		}

		if (originalBatchId == null) {
			stArpOutboundImpl.setOriginalBatchId(StringPool.BLANK);
		}
		else {
			stArpOutboundImpl.setOriginalBatchId(originalBatchId);
		}

		stArpOutboundImpl.setCompanyMasterSid(companyMasterSid);
		stArpOutboundImpl.setBrandMasterSid(brandMasterSid);

		if (arpApprovalDate == Long.MIN_VALUE) {
			stArpOutboundImpl.setArpApprovalDate(null);
		}
		else {
			stArpOutboundImpl.setArpApprovalDate(new Date(arpApprovalDate));
		}

		stArpOutboundImpl.setUserId(userId);
		stArpOutboundImpl.setArpMasterSid(arpMasterSid);

		if (arpCreationDate == Long.MIN_VALUE) {
			stArpOutboundImpl.setArpCreationDate(null);
		}
		else {
			stArpOutboundImpl.setArpCreationDate(new Date(arpCreationDate));
		}

		stArpOutboundImpl.setCheckRecord(checkRecord);
		stArpOutboundImpl.setArpId(arpId);

		if (account == null) {
			stArpOutboundImpl.setAccount(StringPool.BLANK);
		}
		else {
			stArpOutboundImpl.setAccount(account);
		}

		stArpOutboundImpl.setOutboundStatus(outboundStatus);
		stArpOutboundImpl.setPeriodSid(periodSid);
		stArpOutboundImpl.setItemMasterSid(itemMasterSid);
		stArpOutboundImpl.setRsModelSid(rsModelSid);

		if (sessionId == null) {
			stArpOutboundImpl.setSessionId(StringPool.BLANK);
		}
		else {
			stArpOutboundImpl.setSessionId(sessionId);
		}

		stArpOutboundImpl.resetOriginalValues();

		return stArpOutboundImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		salesUnitsRate = objectInput.readDouble();
		accountType = objectInput.readUTF();
		originalBatchId = objectInput.readUTF();

		companyMasterSid = objectInput.readInt();

		brandMasterSid = objectInput.readInt();
		arpApprovalDate = objectInput.readLong();

		userId = objectInput.readInt();

		arpMasterSid = objectInput.readInt();
		arpCreationDate = objectInput.readLong();

		checkRecord = objectInput.readBoolean();

		arpId = objectInput.readInt();
		account = objectInput.readUTF();

		outboundStatus = objectInput.readBoolean();

		periodSid = objectInput.readInt();

		itemMasterSid = objectInput.readInt();

		rsModelSid = objectInput.readInt();
		sessionId = objectInput.readUTF();

		stArpOutboundPK = new StArpOutboundPK(companyMasterSid, userId,
				arpMasterSid, arpId, periodSid, itemMasterSid, rsModelSid,
				sessionId);
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeDouble(salesUnitsRate);

		if (accountType == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(accountType);
		}

		if (originalBatchId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(originalBatchId);
		}

		objectOutput.writeInt(companyMasterSid);

		objectOutput.writeInt(brandMasterSid);
		objectOutput.writeLong(arpApprovalDate);

		objectOutput.writeInt(userId);

		objectOutput.writeInt(arpMasterSid);
		objectOutput.writeLong(arpCreationDate);

		objectOutput.writeBoolean(checkRecord);

		objectOutput.writeInt(arpId);

		if (account == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(account);
		}

		objectOutput.writeBoolean(outboundStatus);

		objectOutput.writeInt(periodSid);

		objectOutput.writeInt(itemMasterSid);

		objectOutput.writeInt(rsModelSid);

		if (sessionId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(sessionId);
		}
	}

	public double salesUnitsRate;
	public String accountType;
	public String originalBatchId;
	public int companyMasterSid;
	public int brandMasterSid;
	public long arpApprovalDate;
	public int userId;
	public int arpMasterSid;
	public long arpCreationDate;
	public boolean checkRecord;
	public int arpId;
	public String account;
	public boolean outboundStatus;
	public int periodSid;
	public int itemMasterSid;
	public int rsModelSid;
	public String sessionId;
	public transient StArpOutboundPK stArpOutboundPK;
}