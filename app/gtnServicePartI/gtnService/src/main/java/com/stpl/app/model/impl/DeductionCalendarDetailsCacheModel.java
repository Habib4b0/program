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

import com.stpl.app.model.DeductionCalendarDetails;
import com.stpl.app.service.persistence.DeductionCalendarDetailsPK;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing DeductionCalendarDetails in entity cache.
 *
 * @author
 * @see DeductionCalendarDetails
 * @generated
 */
@ProviderType
public class DeductionCalendarDetailsCacheModel implements CacheModel<DeductionCalendarDetails>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof DeductionCalendarDetailsCacheModel)) {
			return false;
		}

		DeductionCalendarDetailsCacheModel deductionCalendarDetailsCacheModel = (DeductionCalendarDetailsCacheModel)obj;

		if (deductionCalendarDetailsPK.equals(
					deductionCalendarDetailsCacheModel.deductionCalendarDetailsPK)) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, deductionCalendarDetailsPK);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(23);

		sb.append("{deductionCalendarMasterSid=");
		sb.append(deductionCalendarMasterSid);
		sb.append(", adjustmentBasis=");
		sb.append(adjustmentBasis);
		sb.append(", periodSid=");
		sb.append(periodSid);
		sb.append(", adjustmentValue=");
		sb.append(adjustmentValue);
		sb.append(", adjustmentAllocationMethodology=");
		sb.append(adjustmentAllocationMethodology);
		sb.append(", companyMasterSid=");
		sb.append(companyMasterSid);
		sb.append(", discountAmount=");
		sb.append(discountAmount);
		sb.append(", adjustmentVariable=");
		sb.append(adjustmentVariable);
		sb.append(", itemMasterSid=");
		sb.append(itemMasterSid);
		sb.append(", adjustmentType=");
		sb.append(adjustmentType);
		sb.append(", checkRecord=");
		sb.append(checkRecord);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public DeductionCalendarDetails toEntityModel() {
		DeductionCalendarDetailsImpl deductionCalendarDetailsImpl = new DeductionCalendarDetailsImpl();

		deductionCalendarDetailsImpl.setDeductionCalendarMasterSid(deductionCalendarMasterSid);

		if (adjustmentBasis == null) {
			deductionCalendarDetailsImpl.setAdjustmentBasis(StringPool.BLANK);
		}
		else {
			deductionCalendarDetailsImpl.setAdjustmentBasis(adjustmentBasis);
		}

		deductionCalendarDetailsImpl.setPeriodSid(periodSid);

		if (adjustmentValue == null) {
			deductionCalendarDetailsImpl.setAdjustmentValue(StringPool.BLANK);
		}
		else {
			deductionCalendarDetailsImpl.setAdjustmentValue(adjustmentValue);
		}

		if (adjustmentAllocationMethodology == null) {
			deductionCalendarDetailsImpl.setAdjustmentAllocationMethodology(StringPool.BLANK);
		}
		else {
			deductionCalendarDetailsImpl.setAdjustmentAllocationMethodology(adjustmentAllocationMethodology);
		}

		deductionCalendarDetailsImpl.setCompanyMasterSid(companyMasterSid);
		deductionCalendarDetailsImpl.setDiscountAmount(discountAmount);

		if (adjustmentVariable == null) {
			deductionCalendarDetailsImpl.setAdjustmentVariable(StringPool.BLANK);
		}
		else {
			deductionCalendarDetailsImpl.setAdjustmentVariable(adjustmentVariable);
		}

		deductionCalendarDetailsImpl.setItemMasterSid(itemMasterSid);

		if (adjustmentType == null) {
			deductionCalendarDetailsImpl.setAdjustmentType(StringPool.BLANK);
		}
		else {
			deductionCalendarDetailsImpl.setAdjustmentType(adjustmentType);
		}

		deductionCalendarDetailsImpl.setCheckRecord(checkRecord);

		deductionCalendarDetailsImpl.resetOriginalValues();

		return deductionCalendarDetailsImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		deductionCalendarMasterSid = objectInput.readInt();
		adjustmentBasis = objectInput.readUTF();

		periodSid = objectInput.readInt();
		adjustmentValue = objectInput.readUTF();
		adjustmentAllocationMethodology = objectInput.readUTF();

		companyMasterSid = objectInput.readInt();

		discountAmount = objectInput.readInt();
		adjustmentVariable = objectInput.readUTF();

		itemMasterSid = objectInput.readInt();
		adjustmentType = objectInput.readUTF();

		checkRecord = objectInput.readBoolean();

		deductionCalendarDetailsPK = new DeductionCalendarDetailsPK(deductionCalendarMasterSid,
				periodSid, companyMasterSid, itemMasterSid);
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeInt(deductionCalendarMasterSid);

		if (adjustmentBasis == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(adjustmentBasis);
		}

		objectOutput.writeInt(periodSid);

		if (adjustmentValue == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(adjustmentValue);
		}

		if (adjustmentAllocationMethodology == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(adjustmentAllocationMethodology);
		}

		objectOutput.writeInt(companyMasterSid);

		objectOutput.writeInt(discountAmount);

		if (adjustmentVariable == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(adjustmentVariable);
		}

		objectOutput.writeInt(itemMasterSid);

		if (adjustmentType == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(adjustmentType);
		}

		objectOutput.writeBoolean(checkRecord);
	}

	public int deductionCalendarMasterSid;
	public String adjustmentBasis;
	public int periodSid;
	public String adjustmentValue;
	public String adjustmentAllocationMethodology;
	public int companyMasterSid;
	public int discountAmount;
	public String adjustmentVariable;
	public int itemMasterSid;
	public String adjustmentType;
	public boolean checkRecord;
	public transient DeductionCalendarDetailsPK deductionCalendarDetailsPK;
}