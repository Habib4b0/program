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

import com.stpl.app.model.SalesBasisDetails;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing SalesBasisDetails in entity cache.
 *
 * @author
 * @see SalesBasisDetails
 * @generated
 */
@ProviderType
public class SalesBasisDetailsCacheModel implements CacheModel<SalesBasisDetails>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof SalesBasisDetailsCacheModel)) {
			return false;
		}

		SalesBasisDetailsCacheModel salesBasisDetailsCacheModel = (SalesBasisDetailsCacheModel)obj;

		if (salesBasisDetailsSid == salesBasisDetailsCacheModel.salesBasisDetailsSid) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, salesBasisDetailsSid);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(25);

		sb.append("{createdBy=");
		sb.append(createdBy);
		sb.append(", netSalesFormulaMasterSid=");
		sb.append(netSalesFormulaMasterSid);
		sb.append(", recordLockStatus=");
		sb.append(recordLockStatus);
		sb.append(", modifiedBy=");
		sb.append(modifiedBy);
		sb.append(", createdDate=");
		sb.append(createdDate);
		sb.append(", contractMasterSid=");
		sb.append(contractMasterSid);
		sb.append(", source=");
		sb.append(source);
		sb.append(", cdrModelSid=");
		sb.append(cdrModelSid);
		sb.append(", salesBasisDetailsSid=");
		sb.append(salesBasisDetailsSid);
		sb.append(", cfpContractDetailsSid=");
		sb.append(cfpContractDetailsSid);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", inboundStatus=");
		sb.append(inboundStatus);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public SalesBasisDetails toEntityModel() {
		SalesBasisDetailsImpl salesBasisDetailsImpl = new SalesBasisDetailsImpl();

		salesBasisDetailsImpl.setCreatedBy(createdBy);
		salesBasisDetailsImpl.setNetSalesFormulaMasterSid(netSalesFormulaMasterSid);
		salesBasisDetailsImpl.setRecordLockStatus(recordLockStatus);
		salesBasisDetailsImpl.setModifiedBy(modifiedBy);

		if (createdDate == Long.MIN_VALUE) {
			salesBasisDetailsImpl.setCreatedDate(null);
		}
		else {
			salesBasisDetailsImpl.setCreatedDate(new Date(createdDate));
		}

		salesBasisDetailsImpl.setContractMasterSid(contractMasterSid);

		if (source == null) {
			salesBasisDetailsImpl.setSource(StringPool.BLANK);
		}
		else {
			salesBasisDetailsImpl.setSource(source);
		}

		salesBasisDetailsImpl.setCdrModelSid(cdrModelSid);
		salesBasisDetailsImpl.setSalesBasisDetailsSid(salesBasisDetailsSid);
		salesBasisDetailsImpl.setCfpContractDetailsSid(cfpContractDetailsSid);

		if (modifiedDate == Long.MIN_VALUE) {
			salesBasisDetailsImpl.setModifiedDate(null);
		}
		else {
			salesBasisDetailsImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (inboundStatus == null) {
			salesBasisDetailsImpl.setInboundStatus(StringPool.BLANK);
		}
		else {
			salesBasisDetailsImpl.setInboundStatus(inboundStatus);
		}

		salesBasisDetailsImpl.resetOriginalValues();

		return salesBasisDetailsImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		createdBy = objectInput.readInt();

		netSalesFormulaMasterSid = objectInput.readInt();

		recordLockStatus = objectInput.readBoolean();

		modifiedBy = objectInput.readInt();
		createdDate = objectInput.readLong();

		contractMasterSid = objectInput.readInt();
		source = objectInput.readUTF();

		cdrModelSid = objectInput.readInt();

		salesBasisDetailsSid = objectInput.readInt();

		cfpContractDetailsSid = objectInput.readInt();
		modifiedDate = objectInput.readLong();
		inboundStatus = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeInt(createdBy);

		objectOutput.writeInt(netSalesFormulaMasterSid);

		objectOutput.writeBoolean(recordLockStatus);

		objectOutput.writeInt(modifiedBy);
		objectOutput.writeLong(createdDate);

		objectOutput.writeInt(contractMasterSid);

		if (source == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(source);
		}

		objectOutput.writeInt(cdrModelSid);

		objectOutput.writeInt(salesBasisDetailsSid);

		objectOutput.writeInt(cfpContractDetailsSid);
		objectOutput.writeLong(modifiedDate);

		if (inboundStatus == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(inboundStatus);
		}
	}

	public int createdBy;
	public int netSalesFormulaMasterSid;
	public boolean recordLockStatus;
	public int modifiedBy;
	public long createdDate;
	public int contractMasterSid;
	public String source;
	public int cdrModelSid;
	public int salesBasisDetailsSid;
	public int cfpContractDetailsSid;
	public long modifiedDate;
	public String inboundStatus;
}