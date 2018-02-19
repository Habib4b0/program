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

import com.stpl.app.parttwo.model.AcFdAdjustmentSelection;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing AcFdAdjustmentSelection in entity cache.
 *
 * @author
 * @see AcFdAdjustmentSelection
 * @generated
 */
@ProviderType
public class AcFdAdjustmentSelectionCacheModel implements CacheModel<AcFdAdjustmentSelection>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AcFdAdjustmentSelectionCacheModel)) {
			return false;
		}

		AcFdAdjustmentSelectionCacheModel acFdAdjustmentSelectionCacheModel = (AcFdAdjustmentSelectionCacheModel)obj;

		if (accClosureMasterSid == acFdAdjustmentSelectionCacheModel.accClosureMasterSid) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, accClosureMasterSid);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(29);

		sb.append("{methodologyStartDate=");
		sb.append(methodologyStartDate);
		sb.append(", allocationMethod=");
		sb.append(allocationMethod);
		sb.append(", startDate=");
		sb.append(startDate);
		sb.append(", totalFixedDollarAdj=");
		sb.append(totalFixedDollarAdj);
		sb.append(", calculationFlag=");
		sb.append(calculationFlag);
		sb.append(", rateCorrection=");
		sb.append(rateCorrection);
		sb.append(", businessDays=");
		sb.append(businessDays);
		sb.append(", glImpactDate=");
		sb.append(glImpactDate);
		sb.append(", salesBasis=");
		sb.append(salesBasis);
		sb.append(", releaseType=");
		sb.append(releaseType);
		sb.append(", accClosureMasterSid=");
		sb.append(accClosureMasterSid);
		sb.append(", releaseAmount=");
		sb.append(releaseAmount);
		sb.append(", suggestedAdj=");
		sb.append(suggestedAdj);
		sb.append(", methodologyEndDate=");
		sb.append(methodologyEndDate);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public AcFdAdjustmentSelection toEntityModel() {
		AcFdAdjustmentSelectionImpl acFdAdjustmentSelectionImpl = new AcFdAdjustmentSelectionImpl();

		if (methodologyStartDate == null) {
			acFdAdjustmentSelectionImpl.setMethodologyStartDate(StringPool.BLANK);
		}
		else {
			acFdAdjustmentSelectionImpl.setMethodologyStartDate(methodologyStartDate);
		}

		acFdAdjustmentSelectionImpl.setAllocationMethod(allocationMethod);

		if (startDate == Long.MIN_VALUE) {
			acFdAdjustmentSelectionImpl.setStartDate(null);
		}
		else {
			acFdAdjustmentSelectionImpl.setStartDate(new Date(startDate));
		}

		acFdAdjustmentSelectionImpl.setTotalFixedDollarAdj(totalFixedDollarAdj);
		acFdAdjustmentSelectionImpl.setCalculationFlag(calculationFlag);
		acFdAdjustmentSelectionImpl.setRateCorrection(rateCorrection);
		acFdAdjustmentSelectionImpl.setBusinessDays(businessDays);

		if (glImpactDate == Long.MIN_VALUE) {
			acFdAdjustmentSelectionImpl.setGlImpactDate(null);
		}
		else {
			acFdAdjustmentSelectionImpl.setGlImpactDate(new Date(glImpactDate));
		}

		acFdAdjustmentSelectionImpl.setSalesBasis(salesBasis);
		acFdAdjustmentSelectionImpl.setReleaseType(releaseType);
		acFdAdjustmentSelectionImpl.setAccClosureMasterSid(accClosureMasterSid);
		acFdAdjustmentSelectionImpl.setReleaseAmount(releaseAmount);
		acFdAdjustmentSelectionImpl.setSuggestedAdj(suggestedAdj);

		if (methodologyEndDate == null) {
			acFdAdjustmentSelectionImpl.setMethodologyEndDate(StringPool.BLANK);
		}
		else {
			acFdAdjustmentSelectionImpl.setMethodologyEndDate(methodologyEndDate);
		}

		acFdAdjustmentSelectionImpl.resetOriginalValues();

		return acFdAdjustmentSelectionImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		methodologyStartDate = objectInput.readUTF();

		allocationMethod = objectInput.readInt();
		startDate = objectInput.readLong();

		totalFixedDollarAdj = objectInput.readDouble();

		calculationFlag = objectInput.readBoolean();

		rateCorrection = objectInput.readDouble();

		businessDays = objectInput.readInt();
		glImpactDate = objectInput.readLong();

		salesBasis = objectInput.readInt();

		releaseType = objectInput.readBoolean();

		accClosureMasterSid = objectInput.readInt();

		releaseAmount = objectInput.readDouble();

		suggestedAdj = objectInput.readDouble();
		methodologyEndDate = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		if (methodologyStartDate == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(methodologyStartDate);
		}

		objectOutput.writeInt(allocationMethod);
		objectOutput.writeLong(startDate);

		objectOutput.writeDouble(totalFixedDollarAdj);

		objectOutput.writeBoolean(calculationFlag);

		objectOutput.writeDouble(rateCorrection);

		objectOutput.writeInt(businessDays);
		objectOutput.writeLong(glImpactDate);

		objectOutput.writeInt(salesBasis);

		objectOutput.writeBoolean(releaseType);

		objectOutput.writeInt(accClosureMasterSid);

		objectOutput.writeDouble(releaseAmount);

		objectOutput.writeDouble(suggestedAdj);

		if (methodologyEndDate == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(methodologyEndDate);
		}
	}

	public String methodologyStartDate;
	public int allocationMethod;
	public long startDate;
	public double totalFixedDollarAdj;
	public boolean calculationFlag;
	public double rateCorrection;
	public int businessDays;
	public long glImpactDate;
	public int salesBasis;
	public boolean releaseType;
	public int accClosureMasterSid;
	public double releaseAmount;
	public double suggestedAdj;
	public String methodologyEndDate;
}