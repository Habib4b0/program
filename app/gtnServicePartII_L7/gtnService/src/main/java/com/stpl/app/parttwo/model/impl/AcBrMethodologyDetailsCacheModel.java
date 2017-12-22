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

import com.stpl.app.parttwo.model.AcBrMethodologyDetails;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing AcBrMethodologyDetails in entity cache.
 *
 * @author
 * @see AcBrMethodologyDetails
 * @generated
 */
@ProviderType
public class AcBrMethodologyDetailsCacheModel implements CacheModel<AcBrMethodologyDetails>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AcBrMethodologyDetailsCacheModel)) {
			return false;
		}

		AcBrMethodologyDetailsCacheModel acBrMethodologyDetailsCacheModel = (AcBrMethodologyDetailsCacheModel)obj;

		if (acBrMethodologyDetailsSid == acBrMethodologyDetailsCacheModel.acBrMethodologyDetailsSid) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, acBrMethodologyDetailsSid);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(25);

		sb.append("{salesGrowthRate=");
		sb.append(salesGrowthRate);
		sb.append(", methodologyStartDate=");
		sb.append(methodologyStartDate);
		sb.append(", frequency=");
		sb.append(frequency);
		sb.append(", calculationFlag=");
		sb.append(calculationFlag);
		sb.append(", provisionGrowthRate=");
		sb.append(provisionGrowthRate);
		sb.append(", salesBasis=");
		sb.append(salesBasis);
		sb.append(", acBrMethodologyDetailsSid=");
		sb.append(acBrMethodologyDetailsSid);
		sb.append(", accClosureMasterSid=");
		sb.append(accClosureMasterSid);
		sb.append(", methodologyEndDate=");
		sb.append(methodologyEndDate);
		sb.append(", methodologyValue=");
		sb.append(methodologyValue);
		sb.append(", dampingFactor=");
		sb.append(dampingFactor);
		sb.append(", methodologyName=");
		sb.append(methodologyName);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public AcBrMethodologyDetails toEntityModel() {
		AcBrMethodologyDetailsImpl acBrMethodologyDetailsImpl = new AcBrMethodologyDetailsImpl();

		acBrMethodologyDetailsImpl.setSalesGrowthRate(salesGrowthRate);

		if (methodologyStartDate == Long.MIN_VALUE) {
			acBrMethodologyDetailsImpl.setMethodologyStartDate(null);
		}
		else {
			acBrMethodologyDetailsImpl.setMethodologyStartDate(new Date(
					methodologyStartDate));
		}

		if (frequency == null) {
			acBrMethodologyDetailsImpl.setFrequency(StringPool.BLANK);
		}
		else {
			acBrMethodologyDetailsImpl.setFrequency(frequency);
		}

		acBrMethodologyDetailsImpl.setCalculationFlag(calculationFlag);
		acBrMethodologyDetailsImpl.setProvisionGrowthRate(provisionGrowthRate);
		acBrMethodologyDetailsImpl.setSalesBasis(salesBasis);
		acBrMethodologyDetailsImpl.setAcBrMethodologyDetailsSid(acBrMethodologyDetailsSid);
		acBrMethodologyDetailsImpl.setAccClosureMasterSid(accClosureMasterSid);

		if (methodologyEndDate == Long.MIN_VALUE) {
			acBrMethodologyDetailsImpl.setMethodologyEndDate(null);
		}
		else {
			acBrMethodologyDetailsImpl.setMethodologyEndDate(new Date(
					methodologyEndDate));
		}

		acBrMethodologyDetailsImpl.setMethodologyValue(methodologyValue);
		acBrMethodologyDetailsImpl.setDampingFactor(dampingFactor);

		if (methodologyName == null) {
			acBrMethodologyDetailsImpl.setMethodologyName(StringPool.BLANK);
		}
		else {
			acBrMethodologyDetailsImpl.setMethodologyName(methodologyName);
		}

		acBrMethodologyDetailsImpl.resetOriginalValues();

		return acBrMethodologyDetailsImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		salesGrowthRate = objectInput.readDouble();
		methodologyStartDate = objectInput.readLong();
		frequency = objectInput.readUTF();

		calculationFlag = objectInput.readBoolean();

		provisionGrowthRate = objectInput.readDouble();

		salesBasis = objectInput.readInt();

		acBrMethodologyDetailsSid = objectInput.readInt();

		accClosureMasterSid = objectInput.readInt();
		methodologyEndDate = objectInput.readLong();

		methodologyValue = objectInput.readDouble();

		dampingFactor = objectInput.readDouble();
		methodologyName = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeDouble(salesGrowthRate);
		objectOutput.writeLong(methodologyStartDate);

		if (frequency == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(frequency);
		}

		objectOutput.writeBoolean(calculationFlag);

		objectOutput.writeDouble(provisionGrowthRate);

		objectOutput.writeInt(salesBasis);

		objectOutput.writeInt(acBrMethodologyDetailsSid);

		objectOutput.writeInt(accClosureMasterSid);
		objectOutput.writeLong(methodologyEndDate);

		objectOutput.writeDouble(methodologyValue);

		objectOutput.writeDouble(dampingFactor);

		if (methodologyName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(methodologyName);
		}
	}

	public double salesGrowthRate;
	public long methodologyStartDate;
	public String frequency;
	public boolean calculationFlag;
	public double provisionGrowthRate;
	public int salesBasis;
	public int acBrMethodologyDetailsSid;
	public int accClosureMasterSid;
	public long methodologyEndDate;
	public double methodologyValue;
	public double dampingFactor;
	public String methodologyName;
}