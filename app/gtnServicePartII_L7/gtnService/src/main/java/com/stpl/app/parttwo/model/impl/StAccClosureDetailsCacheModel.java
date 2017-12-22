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

import com.stpl.app.parttwo.model.StAccClosureDetails;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing StAccClosureDetails in entity cache.
 *
 * @author
 * @see StAccClosureDetails
 * @generated
 */
@ProviderType
public class StAccClosureDetailsCacheModel implements CacheModel<StAccClosureDetails>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof StAccClosureDetailsCacheModel)) {
			return false;
		}

		StAccClosureDetailsCacheModel stAccClosureDetailsCacheModel = (StAccClosureDetailsCacheModel)obj;

		if (accClosureMasterSid == stAccClosureDetailsCacheModel.accClosureMasterSid) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, accClosureMasterSid);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(39);

		sb.append("{lastModifiedDate=");
		sb.append(lastModifiedDate);
		sb.append(", checkRecord=");
		sb.append(checkRecord);
		sb.append(", contractName=");
		sb.append(contractName);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", itemMasterSid=");
		sb.append(itemMasterSid);
		sb.append(", moduleName=");
		sb.append(moduleName);
		sb.append(", companyName=");
		sb.append(companyName);
		sb.append(", brandName=");
		sb.append(brandName);
		sb.append(", companyCostCenter=");
		sb.append(companyCostCenter);
		sb.append(", companyNo=");
		sb.append(companyNo);
		sb.append(", contractMasterSid=");
		sb.append(contractMasterSid);
		sb.append(", sessionId=");
		sb.append(sessionId);
		sb.append(", ccpDetailsSid=");
		sb.append(ccpDetailsSid);
		sb.append(", itemName=");
		sb.append(itemName);
		sb.append(", accClosureMasterSid=");
		sb.append(accClosureMasterSid);
		sb.append(", rsModelSid=");
		sb.append(rsModelSid);
		sb.append(", contractNo=");
		sb.append(contractNo);
		sb.append(", companyMasterSid=");
		sb.append(companyMasterSid);
		sb.append(", ndc8=");
		sb.append(ndc8);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public StAccClosureDetails toEntityModel() {
		StAccClosureDetailsImpl stAccClosureDetailsImpl = new StAccClosureDetailsImpl();

		if (lastModifiedDate == Long.MIN_VALUE) {
			stAccClosureDetailsImpl.setLastModifiedDate(null);
		}
		else {
			stAccClosureDetailsImpl.setLastModifiedDate(new Date(
					lastModifiedDate));
		}

		stAccClosureDetailsImpl.setCheckRecord(checkRecord);

		if (contractName == null) {
			stAccClosureDetailsImpl.setContractName(StringPool.BLANK);
		}
		else {
			stAccClosureDetailsImpl.setContractName(contractName);
		}

		stAccClosureDetailsImpl.setUserId(userId);
		stAccClosureDetailsImpl.setItemMasterSid(itemMasterSid);

		if (moduleName == null) {
			stAccClosureDetailsImpl.setModuleName(StringPool.BLANK);
		}
		else {
			stAccClosureDetailsImpl.setModuleName(moduleName);
		}

		if (companyName == null) {
			stAccClosureDetailsImpl.setCompanyName(StringPool.BLANK);
		}
		else {
			stAccClosureDetailsImpl.setCompanyName(companyName);
		}

		if (brandName == null) {
			stAccClosureDetailsImpl.setBrandName(StringPool.BLANK);
		}
		else {
			stAccClosureDetailsImpl.setBrandName(brandName);
		}

		if (companyCostCenter == null) {
			stAccClosureDetailsImpl.setCompanyCostCenter(StringPool.BLANK);
		}
		else {
			stAccClosureDetailsImpl.setCompanyCostCenter(companyCostCenter);
		}

		if (companyNo == null) {
			stAccClosureDetailsImpl.setCompanyNo(StringPool.BLANK);
		}
		else {
			stAccClosureDetailsImpl.setCompanyNo(companyNo);
		}

		stAccClosureDetailsImpl.setContractMasterSid(contractMasterSid);
		stAccClosureDetailsImpl.setSessionId(sessionId);
		stAccClosureDetailsImpl.setCcpDetailsSid(ccpDetailsSid);

		if (itemName == null) {
			stAccClosureDetailsImpl.setItemName(StringPool.BLANK);
		}
		else {
			stAccClosureDetailsImpl.setItemName(itemName);
		}

		stAccClosureDetailsImpl.setAccClosureMasterSid(accClosureMasterSid);
		stAccClosureDetailsImpl.setRsModelSid(rsModelSid);

		if (contractNo == null) {
			stAccClosureDetailsImpl.setContractNo(StringPool.BLANK);
		}
		else {
			stAccClosureDetailsImpl.setContractNo(contractNo);
		}

		stAccClosureDetailsImpl.setCompanyMasterSid(companyMasterSid);

		if (ndc8 == null) {
			stAccClosureDetailsImpl.setNdc8(StringPool.BLANK);
		}
		else {
			stAccClosureDetailsImpl.setNdc8(ndc8);
		}

		stAccClosureDetailsImpl.resetOriginalValues();

		return stAccClosureDetailsImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		lastModifiedDate = objectInput.readLong();

		checkRecord = objectInput.readBoolean();
		contractName = objectInput.readUTF();

		userId = objectInput.readInt();

		itemMasterSid = objectInput.readInt();
		moduleName = objectInput.readUTF();
		companyName = objectInput.readUTF();
		brandName = objectInput.readUTF();
		companyCostCenter = objectInput.readUTF();
		companyNo = objectInput.readUTF();

		contractMasterSid = objectInput.readInt();

		sessionId = objectInput.readInt();

		ccpDetailsSid = objectInput.readInt();
		itemName = objectInput.readUTF();

		accClosureMasterSid = objectInput.readInt();

		rsModelSid = objectInput.readInt();
		contractNo = objectInput.readUTF();

		companyMasterSid = objectInput.readInt();
		ndc8 = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(lastModifiedDate);

		objectOutput.writeBoolean(checkRecord);

		if (contractName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(contractName);
		}

		objectOutput.writeInt(userId);

		objectOutput.writeInt(itemMasterSid);

		if (moduleName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(moduleName);
		}

		if (companyName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(companyName);
		}

		if (brandName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(brandName);
		}

		if (companyCostCenter == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(companyCostCenter);
		}

		if (companyNo == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(companyNo);
		}

		objectOutput.writeInt(contractMasterSid);

		objectOutput.writeInt(sessionId);

		objectOutput.writeInt(ccpDetailsSid);

		if (itemName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(itemName);
		}

		objectOutput.writeInt(accClosureMasterSid);

		objectOutput.writeInt(rsModelSid);

		if (contractNo == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(contractNo);
		}

		objectOutput.writeInt(companyMasterSid);

		if (ndc8 == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(ndc8);
		}
	}

	public long lastModifiedDate;
	public boolean checkRecord;
	public String contractName;
	public int userId;
	public int itemMasterSid;
	public String moduleName;
	public String companyName;
	public String brandName;
	public String companyCostCenter;
	public String companyNo;
	public int contractMasterSid;
	public int sessionId;
	public int ccpDetailsSid;
	public String itemName;
	public int accClosureMasterSid;
	public int rsModelSid;
	public String contractNo;
	public int companyMasterSid;
	public String ndc8;
}