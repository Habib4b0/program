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

import com.stpl.app.model.CcpDetails;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing CcpDetails in entity cache.
 *
 * @author
 * @see CcpDetails
 * @generated
 */
@ProviderType
public class CcpDetailsCacheModel implements CacheModel<CcpDetails>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CcpDetailsCacheModel)) {
			return false;
		}

		CcpDetailsCacheModel ccpDetailsCacheModel = (CcpDetailsCacheModel)obj;

		if (ccpDetailsSid == ccpDetailsCacheModel.ccpDetailsSid) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, ccpDetailsSid);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(9);

		sb.append("{itemMasterSid=");
		sb.append(itemMasterSid);
		sb.append(", contractMasterSid=");
		sb.append(contractMasterSid);
		sb.append(", ccpDetailsSid=");
		sb.append(ccpDetailsSid);
		sb.append(", companyMasterSid=");
		sb.append(companyMasterSid);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public CcpDetails toEntityModel() {
		CcpDetailsImpl ccpDetailsImpl = new CcpDetailsImpl();

		ccpDetailsImpl.setItemMasterSid(itemMasterSid);
		ccpDetailsImpl.setContractMasterSid(contractMasterSid);
		ccpDetailsImpl.setCcpDetailsSid(ccpDetailsSid);
		ccpDetailsImpl.setCompanyMasterSid(companyMasterSid);

		ccpDetailsImpl.resetOriginalValues();

		return ccpDetailsImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		itemMasterSid = objectInput.readInt();

		contractMasterSid = objectInput.readInt();

		ccpDetailsSid = objectInput.readInt();

		companyMasterSid = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeInt(itemMasterSid);

		objectOutput.writeInt(contractMasterSid);

		objectOutput.writeInt(ccpDetailsSid);

		objectOutput.writeInt(companyMasterSid);
	}

	public int itemMasterSid;
	public int contractMasterSid;
	public int ccpDetailsSid;
	public int companyMasterSid;
}