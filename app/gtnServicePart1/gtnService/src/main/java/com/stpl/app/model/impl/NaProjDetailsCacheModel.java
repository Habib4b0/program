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

import com.stpl.app.model.NaProjDetails;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing NaProjDetails in entity cache.
 *
 * @author
 * @see NaProjDetails
 * @generated
 */
@ProviderType
public class NaProjDetailsCacheModel implements CacheModel<NaProjDetails>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof NaProjDetailsCacheModel)) {
			return false;
		}

		NaProjDetailsCacheModel naProjDetailsCacheModel = (NaProjDetailsCacheModel)obj;

		if (naProjDetailsSid == naProjDetailsCacheModel.naProjDetailsSid) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, naProjDetailsSid);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(7);

		sb.append("{itemMasterSid=");
		sb.append(itemMasterSid);
		sb.append(", naProjMasterSid=");
		sb.append(naProjMasterSid);
		sb.append(", naProjDetailsSid=");
		sb.append(naProjDetailsSid);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public NaProjDetails toEntityModel() {
		NaProjDetailsImpl naProjDetailsImpl = new NaProjDetailsImpl();

		naProjDetailsImpl.setItemMasterSid(itemMasterSid);
		naProjDetailsImpl.setNaProjMasterSid(naProjMasterSid);
		naProjDetailsImpl.setNaProjDetailsSid(naProjDetailsSid);

		naProjDetailsImpl.resetOriginalValues();

		return naProjDetailsImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		itemMasterSid = objectInput.readInt();

		naProjMasterSid = objectInput.readInt();

		naProjDetailsSid = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeInt(itemMasterSid);

		objectOutput.writeInt(naProjMasterSid);

		objectOutput.writeInt(naProjDetailsSid);
	}

	public int itemMasterSid;
	public int naProjMasterSid;
	public int naProjDetailsSid;
}