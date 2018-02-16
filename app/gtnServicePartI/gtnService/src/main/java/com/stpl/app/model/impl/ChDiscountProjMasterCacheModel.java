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

import com.stpl.app.model.ChDiscountProjMaster;
import com.stpl.app.service.persistence.ChDiscountProjMasterPK;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing ChDiscountProjMaster in entity cache.
 *
 * @author
 * @see ChDiscountProjMaster
 * @generated
 */
@ProviderType
public class ChDiscountProjMasterCacheModel implements CacheModel<ChDiscountProjMaster>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ChDiscountProjMasterCacheModel)) {
			return false;
		}

		ChDiscountProjMasterCacheModel chDiscountProjMasterCacheModel = (ChDiscountProjMasterCacheModel)obj;

		if (chDiscountProjMasterPK.equals(
					chDiscountProjMasterCacheModel.chDiscountProjMasterPK)) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, chDiscountProjMasterPK);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(21);

		sb.append("{selectedPeriods=");
		sb.append(selectedPeriods);
		sb.append(", checkRecord=");
		sb.append(checkRecord);
		sb.append(", priceGroupType=");
		sb.append(priceGroupType);
		sb.append(", projectionDetailsSid=");
		sb.append(projectionDetailsSid);
		sb.append(", baselinePeriods=");
		sb.append(baselinePeriods);
		sb.append(", netFlag=");
		sb.append(netFlag);
		sb.append(", methodology=");
		sb.append(methodology);
		sb.append(", rsModelSid=");
		sb.append(rsModelSid);
		sb.append(", discountType=");
		sb.append(discountType);
		sb.append(", projectedType=");
		sb.append(projectedType);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ChDiscountProjMaster toEntityModel() {
		ChDiscountProjMasterImpl chDiscountProjMasterImpl = new ChDiscountProjMasterImpl();

		if (selectedPeriods == null) {
			chDiscountProjMasterImpl.setSelectedPeriods(StringPool.BLANK);
		}
		else {
			chDiscountProjMasterImpl.setSelectedPeriods(selectedPeriods);
		}

		chDiscountProjMasterImpl.setCheckRecord(checkRecord);

		if (priceGroupType == null) {
			chDiscountProjMasterImpl.setPriceGroupType(StringPool.BLANK);
		}
		else {
			chDiscountProjMasterImpl.setPriceGroupType(priceGroupType);
		}

		chDiscountProjMasterImpl.setProjectionDetailsSid(projectionDetailsSid);

		if (baselinePeriods == null) {
			chDiscountProjMasterImpl.setBaselinePeriods(StringPool.BLANK);
		}
		else {
			chDiscountProjMasterImpl.setBaselinePeriods(baselinePeriods);
		}

		if (netFlag == null) {
			chDiscountProjMasterImpl.setNetFlag(StringPool.BLANK);
		}
		else {
			chDiscountProjMasterImpl.setNetFlag(netFlag);
		}

		if (methodology == null) {
			chDiscountProjMasterImpl.setMethodology(StringPool.BLANK);
		}
		else {
			chDiscountProjMasterImpl.setMethodology(methodology);
		}

		chDiscountProjMasterImpl.setRsModelSid(rsModelSid);

		if (discountType == null) {
			chDiscountProjMasterImpl.setDiscountType(StringPool.BLANK);
		}
		else {
			chDiscountProjMasterImpl.setDiscountType(discountType);
		}

		if (projectedType == null) {
			chDiscountProjMasterImpl.setProjectedType(StringPool.BLANK);
		}
		else {
			chDiscountProjMasterImpl.setProjectedType(projectedType);
		}

		chDiscountProjMasterImpl.resetOriginalValues();

		return chDiscountProjMasterImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		selectedPeriods = objectInput.readUTF();

		checkRecord = objectInput.readBoolean();
		priceGroupType = objectInput.readUTF();

		projectionDetailsSid = objectInput.readInt();
		baselinePeriods = objectInput.readUTF();
		netFlag = objectInput.readUTF();
		methodology = objectInput.readUTF();

		rsModelSid = objectInput.readInt();
		discountType = objectInput.readUTF();
		projectedType = objectInput.readUTF();

		chDiscountProjMasterPK = new ChDiscountProjMasterPK(projectionDetailsSid,
				rsModelSid);
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		if (selectedPeriods == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(selectedPeriods);
		}

		objectOutput.writeBoolean(checkRecord);

		if (priceGroupType == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(priceGroupType);
		}

		objectOutput.writeInt(projectionDetailsSid);

		if (baselinePeriods == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(baselinePeriods);
		}

		if (netFlag == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(netFlag);
		}

		if (methodology == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(methodology);
		}

		objectOutput.writeInt(rsModelSid);

		if (discountType == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(discountType);
		}

		if (projectedType == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(projectedType);
		}
	}

	public String selectedPeriods;
	public boolean checkRecord;
	public String priceGroupType;
	public int projectionDetailsSid;
	public String baselinePeriods;
	public String netFlag;
	public String methodology;
	public int rsModelSid;
	public String discountType;
	public String projectedType;
	public transient ChDiscountProjMasterPK chDiscountProjMasterPK;
}