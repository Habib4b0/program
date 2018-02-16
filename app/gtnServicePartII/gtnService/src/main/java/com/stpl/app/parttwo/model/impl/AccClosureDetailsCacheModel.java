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

import com.stpl.app.parttwo.model.AccClosureDetails;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing AccClosureDetails in entity cache.
 *
 * @author
 * @see AccClosureDetails
 * @generated
 */
@ProviderType
public class AccClosureDetailsCacheModel implements CacheModel<AccClosureDetails>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AccClosureDetailsCacheModel)) {
			return false;
		}

		AccClosureDetailsCacheModel accClosureDetailsCacheModel = (AccClosureDetailsCacheModel)obj;

		if (accClosureDetailsSid == accClosureDetailsCacheModel.accClosureDetailsSid) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, accClosureDetailsSid);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(9);

		sb.append("{accClosureDetailsSid=");
		sb.append(accClosureDetailsSid);
		sb.append(", ccpDetailsSid=");
		sb.append(ccpDetailsSid);
		sb.append(", accClosureMasterSid=");
		sb.append(accClosureMasterSid);
		sb.append(", rsModelSid=");
		sb.append(rsModelSid);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public AccClosureDetails toEntityModel() {
		AccClosureDetailsImpl accClosureDetailsImpl = new AccClosureDetailsImpl();

		accClosureDetailsImpl.setAccClosureDetailsSid(accClosureDetailsSid);
		accClosureDetailsImpl.setCcpDetailsSid(ccpDetailsSid);
		accClosureDetailsImpl.setAccClosureMasterSid(accClosureMasterSid);
		accClosureDetailsImpl.setRsModelSid(rsModelSid);

		accClosureDetailsImpl.resetOriginalValues();

		return accClosureDetailsImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		accClosureDetailsSid = objectInput.readInt();

		ccpDetailsSid = objectInput.readInt();

		accClosureMasterSid = objectInput.readInt();

		rsModelSid = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeInt(accClosureDetailsSid);

		objectOutput.writeInt(ccpDetailsSid);

		objectOutput.writeInt(accClosureMasterSid);

		objectOutput.writeInt(rsModelSid);
	}

	public int accClosureDetailsSid;
	public int ccpDetailsSid;
	public int accClosureMasterSid;
	public int rsModelSid;
}