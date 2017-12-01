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

import com.stpl.app.model.ChSalesProjectionMaster;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing ChSalesProjectionMaster in entity cache.
 *
 * @author
 * @see ChSalesProjectionMaster
 * @generated
 */
@ProviderType
public class ChSalesProjectionMasterCacheModel implements CacheModel<ChSalesProjectionMaster>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ChSalesProjectionMasterCacheModel)) {
			return false;
		}

		ChSalesProjectionMasterCacheModel chSalesProjectionMasterCacheModel = (ChSalesProjectionMasterCacheModel)obj;

		if (projectionDetailsSid == chSalesProjectionMasterCacheModel.projectionDetailsSid) {
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
		StringBundler sb = new StringBundler(9);

		sb.append("{checkRecord=");
		sb.append(checkRecord);
		sb.append(", calculationPeriods=");
		sb.append(calculationPeriods);
		sb.append(", projectionDetailsSid=");
		sb.append(projectionDetailsSid);
		sb.append(", methodology=");
		sb.append(methodology);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ChSalesProjectionMaster toEntityModel() {
		ChSalesProjectionMasterImpl chSalesProjectionMasterImpl = new ChSalesProjectionMasterImpl();

		chSalesProjectionMasterImpl.setCheckRecord(checkRecord);

		if (calculationPeriods == null) {
			chSalesProjectionMasterImpl.setCalculationPeriods(StringPool.BLANK);
		}
		else {
			chSalesProjectionMasterImpl.setCalculationPeriods(calculationPeriods);
		}

		chSalesProjectionMasterImpl.setProjectionDetailsSid(projectionDetailsSid);

		if (methodology == null) {
			chSalesProjectionMasterImpl.setMethodology(StringPool.BLANK);
		}
		else {
			chSalesProjectionMasterImpl.setMethodology(methodology);
		}

		chSalesProjectionMasterImpl.resetOriginalValues();

		return chSalesProjectionMasterImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		checkRecord = objectInput.readBoolean();
		calculationPeriods = objectInput.readUTF();

		projectionDetailsSid = objectInput.readInt();
		methodology = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeBoolean(checkRecord);

		if (calculationPeriods == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(calculationPeriods);
		}

		objectOutput.writeInt(projectionDetailsSid);

		if (methodology == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(methodology);
		}
	}

	public boolean checkRecord;
	public String calculationPeriods;
	public int projectionDetailsSid;
	public String methodology;
}