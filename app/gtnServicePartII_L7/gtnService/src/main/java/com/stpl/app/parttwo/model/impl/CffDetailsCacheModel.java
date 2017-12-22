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

import com.stpl.app.parttwo.model.CffDetails;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing CffDetails in entity cache.
 *
 * @author
 * @see CffDetails
 * @generated
 */
@ProviderType
public class CffDetailsCacheModel implements CacheModel<CffDetails>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CffDetailsCacheModel)) {
			return false;
		}

		CffDetailsCacheModel cffDetailsCacheModel = (CffDetailsCacheModel)obj;

		if (cffDetailsSid == cffDetailsCacheModel.cffDetailsSid) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, cffDetailsSid);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(11);

		sb.append("{ccpDetailsSid=");
		sb.append(ccpDetailsSid);
		sb.append(", projectionMasterSid=");
		sb.append(projectionMasterSid);
		sb.append(", cffMasterSid=");
		sb.append(cffMasterSid);
		sb.append(", inboundStatus=");
		sb.append(inboundStatus);
		sb.append(", cffDetailsSid=");
		sb.append(cffDetailsSid);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public CffDetails toEntityModel() {
		CffDetailsImpl cffDetailsImpl = new CffDetailsImpl();

		cffDetailsImpl.setCcpDetailsSid(ccpDetailsSid);
		cffDetailsImpl.setProjectionMasterSid(projectionMasterSid);
		cffDetailsImpl.setCffMasterSid(cffMasterSid);

		if (inboundStatus == null) {
			cffDetailsImpl.setInboundStatus(StringPool.BLANK);
		}
		else {
			cffDetailsImpl.setInboundStatus(inboundStatus);
		}

		cffDetailsImpl.setCffDetailsSid(cffDetailsSid);

		cffDetailsImpl.resetOriginalValues();

		return cffDetailsImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		ccpDetailsSid = objectInput.readInt();

		projectionMasterSid = objectInput.readInt();

		cffMasterSid = objectInput.readInt();
		inboundStatus = objectInput.readUTF();

		cffDetailsSid = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeInt(ccpDetailsSid);

		objectOutput.writeInt(projectionMasterSid);

		objectOutput.writeInt(cffMasterSid);

		if (inboundStatus == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(inboundStatus);
		}

		objectOutput.writeInt(cffDetailsSid);
	}

	public int ccpDetailsSid;
	public int projectionMasterSid;
	public int cffMasterSid;
	public String inboundStatus;
	public int cffDetailsSid;
}