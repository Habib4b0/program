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

import com.stpl.app.model.ProjectionCustDetails;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing ProjectionCustDetails in entity cache.
 *
 * @author
 * @see ProjectionCustDetails
 * @generated
 */
@ProviderType
public class ProjectionCustDetailsCacheModel implements CacheModel<ProjectionCustDetails>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ProjectionCustDetailsCacheModel)) {
			return false;
		}

		ProjectionCustDetailsCacheModel projectionCustDetailsCacheModel = (ProjectionCustDetailsCacheModel)obj;

		if (customerDetailsId == projectionCustDetailsCacheModel.customerDetailsId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, customerDetailsId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(19);

		sb.append("{contractName=");
		sb.append(contractName);
		sb.append(", customerName=");
		sb.append(customerName);
		sb.append(", customerDetailsId=");
		sb.append(customerDetailsId);
		sb.append(", costCenter=");
		sb.append(costCenter);
		sb.append(", customerAlias=");
		sb.append(customerAlias);
		sb.append(", subLedgerCode=");
		sb.append(subLedgerCode);
		sb.append(", projectionId=");
		sb.append(projectionId);
		sb.append(", marketType=");
		sb.append(marketType);
		sb.append(", contractNo=");
		sb.append(contractNo);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ProjectionCustDetails toEntityModel() {
		ProjectionCustDetailsImpl projectionCustDetailsImpl = new ProjectionCustDetailsImpl();

		if (contractName == null) {
			projectionCustDetailsImpl.setContractName(StringPool.BLANK);
		}
		else {
			projectionCustDetailsImpl.setContractName(contractName);
		}

		if (customerName == null) {
			projectionCustDetailsImpl.setCustomerName(StringPool.BLANK);
		}
		else {
			projectionCustDetailsImpl.setCustomerName(customerName);
		}

		projectionCustDetailsImpl.setCustomerDetailsId(customerDetailsId);

		if (costCenter == null) {
			projectionCustDetailsImpl.setCostCenter(StringPool.BLANK);
		}
		else {
			projectionCustDetailsImpl.setCostCenter(costCenter);
		}

		if (customerAlias == null) {
			projectionCustDetailsImpl.setCustomerAlias(StringPool.BLANK);
		}
		else {
			projectionCustDetailsImpl.setCustomerAlias(customerAlias);
		}

		if (subLedgerCode == null) {
			projectionCustDetailsImpl.setSubLedgerCode(StringPool.BLANK);
		}
		else {
			projectionCustDetailsImpl.setSubLedgerCode(subLedgerCode);
		}

		projectionCustDetailsImpl.setProjectionId(projectionId);

		if (marketType == null) {
			projectionCustDetailsImpl.setMarketType(StringPool.BLANK);
		}
		else {
			projectionCustDetailsImpl.setMarketType(marketType);
		}

		if (contractNo == null) {
			projectionCustDetailsImpl.setContractNo(StringPool.BLANK);
		}
		else {
			projectionCustDetailsImpl.setContractNo(contractNo);
		}

		projectionCustDetailsImpl.resetOriginalValues();

		return projectionCustDetailsImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		contractName = objectInput.readUTF();
		customerName = objectInput.readUTF();

		customerDetailsId = objectInput.readInt();
		costCenter = objectInput.readUTF();
		customerAlias = objectInput.readUTF();
		subLedgerCode = objectInput.readUTF();

		projectionId = objectInput.readInt();
		marketType = objectInput.readUTF();
		contractNo = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		if (contractName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(contractName);
		}

		if (customerName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(customerName);
		}

		objectOutput.writeInt(customerDetailsId);

		if (costCenter == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(costCenter);
		}

		if (customerAlias == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(customerAlias);
		}

		if (subLedgerCode == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(subLedgerCode);
		}

		objectOutput.writeInt(projectionId);

		if (marketType == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(marketType);
		}

		if (contractNo == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(contractNo);
		}
	}

	public String contractName;
	public String customerName;
	public int customerDetailsId;
	public String costCenter;
	public String customerAlias;
	public String subLedgerCode;
	public int projectionId;
	public String marketType;
	public String contractNo;
}