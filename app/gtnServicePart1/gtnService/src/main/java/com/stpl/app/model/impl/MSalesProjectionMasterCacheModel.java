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

import com.stpl.app.model.MSalesProjectionMaster;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing MSalesProjectionMaster in entity cache.
 *
 * @author
 * @see MSalesProjectionMaster
 * @generated
 */
@ProviderType
public class MSalesProjectionMasterCacheModel implements CacheModel<MSalesProjectionMaster>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof MSalesProjectionMasterCacheModel)) {
			return false;
		}

		MSalesProjectionMasterCacheModel mSalesProjectionMasterCacheModel = (MSalesProjectionMasterCacheModel)obj;

		if (projectionDetailsSid == mSalesProjectionMasterCacheModel.projectionDetailsSid) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, projectionDetailsSid);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(11);

		sb.append("{methodology=");
		sb.append(methodology);
		sb.append(", calculationPeriods=");
		sb.append(calculationPeriods);
		sb.append(", calculationBased=");
		sb.append(calculationBased);
		sb.append(", projectionDetailsSid=");
		sb.append(projectionDetailsSid);
		sb.append(", checkRecord=");
		sb.append(checkRecord);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public MSalesProjectionMaster toEntityModel() {
		MSalesProjectionMasterImpl mSalesProjectionMasterImpl = new MSalesProjectionMasterImpl();

		if (methodology == null) {
			mSalesProjectionMasterImpl.setMethodology(StringPool.BLANK);
		}
		else {
			mSalesProjectionMasterImpl.setMethodology(methodology);
		}

		if (calculationPeriods == null) {
			mSalesProjectionMasterImpl.setCalculationPeriods(StringPool.BLANK);
		}
		else {
			mSalesProjectionMasterImpl.setCalculationPeriods(calculationPeriods);
		}

		if (calculationBased == null) {
			mSalesProjectionMasterImpl.setCalculationBased(StringPool.BLANK);
		}
		else {
			mSalesProjectionMasterImpl.setCalculationBased(calculationBased);
		}

		mSalesProjectionMasterImpl.setProjectionDetailsSid(projectionDetailsSid);
		mSalesProjectionMasterImpl.setCheckRecord(checkRecord);

		mSalesProjectionMasterImpl.resetOriginalValues();

		return mSalesProjectionMasterImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		methodology = objectInput.readUTF();
		calculationPeriods = objectInput.readUTF();
		calculationBased = objectInput.readUTF();

		projectionDetailsSid = objectInput.readInt();

		checkRecord = objectInput.readBoolean();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		if (methodology == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(methodology);
		}

		if (calculationPeriods == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(calculationPeriods);
		}

		if (calculationBased == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(calculationBased);
		}

		objectOutput.writeInt(projectionDetailsSid);

		objectOutput.writeBoolean(checkRecord);
	}

	public String methodology;
	public String calculationPeriods;
	public String calculationBased;
	public int projectionDetailsSid;
	public boolean checkRecord;
}