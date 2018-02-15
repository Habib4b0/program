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

import com.stpl.app.model.StChSalesProjectionMaster;
import com.stpl.app.service.persistence.StChSalesProjectionMasterPK;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing StChSalesProjectionMaster in entity cache.
 *
 * @author
 * @see StChSalesProjectionMaster
 * @generated
 */
@ProviderType
public class StChSalesProjectionMasterCacheModel implements CacheModel<StChSalesProjectionMaster>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof StChSalesProjectionMasterCacheModel)) {
			return false;
		}

		StChSalesProjectionMasterCacheModel stChSalesProjectionMasterCacheModel = (StChSalesProjectionMasterCacheModel)obj;

		if (stChSalesProjectionMasterPK.equals(
					stChSalesProjectionMasterCacheModel.stChSalesProjectionMasterPK)) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, stChSalesProjectionMasterPK);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(15);

		sb.append("{lastModifiedDate=");
		sb.append(lastModifiedDate);
		sb.append(", checkRecord=");
		sb.append(checkRecord);
		sb.append(", calculationPeriods=");
		sb.append(calculationPeriods);
		sb.append(", projectionDetailsSid=");
		sb.append(projectionDetailsSid);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", sessionId=");
		sb.append(sessionId);
		sb.append(", methodology=");
		sb.append(methodology);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public StChSalesProjectionMaster toEntityModel() {
		StChSalesProjectionMasterImpl stChSalesProjectionMasterImpl = new StChSalesProjectionMasterImpl();

		if (lastModifiedDate == Long.MIN_VALUE) {
			stChSalesProjectionMasterImpl.setLastModifiedDate(null);
		}
		else {
			stChSalesProjectionMasterImpl.setLastModifiedDate(new Date(
					lastModifiedDate));
		}

		stChSalesProjectionMasterImpl.setCheckRecord(checkRecord);

		if (calculationPeriods == null) {
			stChSalesProjectionMasterImpl.setCalculationPeriods(StringPool.BLANK);
		}
		else {
			stChSalesProjectionMasterImpl.setCalculationPeriods(calculationPeriods);
		}

		stChSalesProjectionMasterImpl.setProjectionDetailsSid(projectionDetailsSid);
		stChSalesProjectionMasterImpl.setUserId(userId);
		stChSalesProjectionMasterImpl.setSessionId(sessionId);

		if (methodology == null) {
			stChSalesProjectionMasterImpl.setMethodology(StringPool.BLANK);
		}
		else {
			stChSalesProjectionMasterImpl.setMethodology(methodology);
		}

		stChSalesProjectionMasterImpl.resetOriginalValues();

		return stChSalesProjectionMasterImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		lastModifiedDate = objectInput.readLong();

		checkRecord = objectInput.readBoolean();
		calculationPeriods = objectInput.readUTF();

		projectionDetailsSid = objectInput.readInt();

		userId = objectInput.readInt();

		sessionId = objectInput.readInt();
		methodology = objectInput.readUTF();

		stChSalesProjectionMasterPK = new StChSalesProjectionMasterPK(projectionDetailsSid,
				userId, sessionId);
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(lastModifiedDate);

		objectOutput.writeBoolean(checkRecord);

		if (calculationPeriods == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(calculationPeriods);
		}

		objectOutput.writeInt(projectionDetailsSid);

		objectOutput.writeInt(userId);

		objectOutput.writeInt(sessionId);

		if (methodology == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(methodology);
		}
	}

	public long lastModifiedDate;
	public boolean checkRecord;
	public String calculationPeriods;
	public int projectionDetailsSid;
	public int userId;
	public int sessionId;
	public String methodology;
	public transient StChSalesProjectionMasterPK stChSalesProjectionMasterPK;
}