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

import com.stpl.app.model.StFederalNewNdc;
import com.stpl.app.service.persistence.StFederalNewNdcPK;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing StFederalNewNdc in entity cache.
 *
 * @author
 * @see StFederalNewNdc
 * @generated
 */
@ProviderType
public class StFederalNewNdcCacheModel implements CacheModel<StFederalNewNdc>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof StFederalNewNdcCacheModel)) {
			return false;
		}

		StFederalNewNdcCacheModel stFederalNewNdcCacheModel = (StFederalNewNdcCacheModel)obj;

		if (stFederalNewNdcPK.equals(
					stFederalNewNdcCacheModel.stFederalNewNdcPK)) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, stFederalNewNdcPK);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(15);

		sb.append("{fss=");
		sb.append(fss);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", lastModifiedDate=");
		sb.append(lastModifiedDate);
		sb.append(", itemMasterSid=");
		sb.append(itemMasterSid);
		sb.append(", wacPrice=");
		sb.append(wacPrice);
		sb.append(", sessionId=");
		sb.append(sessionId);
		sb.append(", nonFamp=");
		sb.append(nonFamp);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public StFederalNewNdc toEntityModel() {
		StFederalNewNdcImpl stFederalNewNdcImpl = new StFederalNewNdcImpl();

		stFederalNewNdcImpl.setFss(fss);
		stFederalNewNdcImpl.setUserId(userId);

		if (lastModifiedDate == Long.MIN_VALUE) {
			stFederalNewNdcImpl.setLastModifiedDate(null);
		}
		else {
			stFederalNewNdcImpl.setLastModifiedDate(new Date(lastModifiedDate));
		}

		stFederalNewNdcImpl.setItemMasterSid(itemMasterSid);
		stFederalNewNdcImpl.setWacPrice(wacPrice);
		stFederalNewNdcImpl.setSessionId(sessionId);
		stFederalNewNdcImpl.setNonFamp(nonFamp);

		stFederalNewNdcImpl.resetOriginalValues();

		return stFederalNewNdcImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		fss = objectInput.readDouble();

		userId = objectInput.readInt();
		lastModifiedDate = objectInput.readLong();

		itemMasterSid = objectInput.readInt();

		wacPrice = objectInput.readDouble();

		sessionId = objectInput.readInt();

		nonFamp = objectInput.readDouble();

		stFederalNewNdcPK = new StFederalNewNdcPK(userId, itemMasterSid,
				sessionId);
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeDouble(fss);

		objectOutput.writeInt(userId);
		objectOutput.writeLong(lastModifiedDate);

		objectOutput.writeInt(itemMasterSid);

		objectOutput.writeDouble(wacPrice);

		objectOutput.writeInt(sessionId);

		objectOutput.writeDouble(nonFamp);
	}

	public double fss;
	public int userId;
	public long lastModifiedDate;
	public int itemMasterSid;
	public double wacPrice;
	public int sessionId;
	public double nonFamp;
	public transient StFederalNewNdcPK stFederalNewNdcPK;
}