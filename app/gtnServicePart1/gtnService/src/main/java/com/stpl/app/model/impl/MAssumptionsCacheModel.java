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

import com.stpl.app.model.MAssumptions;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing MAssumptions in entity cache.
 *
 * @author
 * @see MAssumptions
 * @generated
 */
@ProviderType
public class MAssumptionsCacheModel implements CacheModel<MAssumptions>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof MAssumptionsCacheModel)) {
			return false;
		}

		MAssumptionsCacheModel mAssumptionsCacheModel = (MAssumptionsCacheModel)obj;

		if (mAssumptionsSid == mAssumptionsCacheModel.mAssumptionsSid) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, mAssumptionsSid);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(35);

		sb.append("{grossSalesPercentChange=");
		sb.append(grossSalesPercentChange);
		sb.append(", grossSalesPrior=");
		sb.append(grossSalesPrior);
		sb.append(", projYear=");
		sb.append(projYear);
		sb.append(", totalDiscountPercentProjected=");
		sb.append(totalDiscountPercentProjected);
		sb.append(", camId=");
		sb.append(camId);
		sb.append(", commentary=");
		sb.append(commentary);
		sb.append(", grossSalesProjected=");
		sb.append(grossSalesProjected);
		sb.append(", totalDiscountPercentChange=");
		sb.append(totalDiscountPercentChange);
		sb.append(", totalDiscountPercentPrior=");
		sb.append(totalDiscountPercentPrior);
		sb.append(", netSalesPercentChange=");
		sb.append(netSalesPercentChange);
		sb.append(", parent=");
		sb.append(parent);
		sb.append(", projectionPeriod=");
		sb.append(projectionPeriod);
		sb.append(", projectionDetailsSid=");
		sb.append(projectionDetailsSid);
		sb.append(", netSalesPrior=");
		sb.append(netSalesPrior);
		sb.append(", netSalesProjected=");
		sb.append(netSalesProjected);
		sb.append(", reasonCodes=");
		sb.append(reasonCodes);
		sb.append(", mAssumptionsSid=");
		sb.append(mAssumptionsSid);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public MAssumptions toEntityModel() {
		MAssumptionsImpl mAssumptionsImpl = new MAssumptionsImpl();

		mAssumptionsImpl.setGrossSalesPercentChange(grossSalesPercentChange);
		mAssumptionsImpl.setGrossSalesPrior(grossSalesPrior);
		mAssumptionsImpl.setProjYear(projYear);
		mAssumptionsImpl.setTotalDiscountPercentProjected(totalDiscountPercentProjected);
		mAssumptionsImpl.setCamId(camId);

		if (commentary == null) {
			mAssumptionsImpl.setCommentary(StringPool.BLANK);
		}
		else {
			mAssumptionsImpl.setCommentary(commentary);
		}

		mAssumptionsImpl.setGrossSalesProjected(grossSalesProjected);
		mAssumptionsImpl.setTotalDiscountPercentChange(totalDiscountPercentChange);
		mAssumptionsImpl.setTotalDiscountPercentPrior(totalDiscountPercentPrior);
		mAssumptionsImpl.setNetSalesPercentChange(netSalesPercentChange);
		mAssumptionsImpl.setParent(parent);
		mAssumptionsImpl.setProjectionPeriod(projectionPeriod);
		mAssumptionsImpl.setProjectionDetailsSid(projectionDetailsSid);
		mAssumptionsImpl.setNetSalesPrior(netSalesPrior);
		mAssumptionsImpl.setNetSalesProjected(netSalesProjected);

		if (reasonCodes == null) {
			mAssumptionsImpl.setReasonCodes(StringPool.BLANK);
		}
		else {
			mAssumptionsImpl.setReasonCodes(reasonCodes);
		}

		mAssumptionsImpl.setMAssumptionsSid(mAssumptionsSid);

		mAssumptionsImpl.resetOriginalValues();

		return mAssumptionsImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		grossSalesPercentChange = objectInput.readDouble();

		grossSalesPrior = objectInput.readDouble();

		projYear = objectInput.readInt();

		totalDiscountPercentProjected = objectInput.readDouble();

		camId = objectInput.readInt();
		commentary = objectInput.readUTF();

		grossSalesProjected = objectInput.readDouble();

		totalDiscountPercentChange = objectInput.readDouble();

		totalDiscountPercentPrior = objectInput.readDouble();

		netSalesPercentChange = objectInput.readDouble();

		parent = objectInput.readBoolean();

		projectionPeriod = objectInput.readInt();

		projectionDetailsSid = objectInput.readInt();

		netSalesPrior = objectInput.readDouble();

		netSalesProjected = objectInput.readDouble();
		reasonCodes = objectInput.readUTF();

		mAssumptionsSid = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeDouble(grossSalesPercentChange);

		objectOutput.writeDouble(grossSalesPrior);

		objectOutput.writeInt(projYear);

		objectOutput.writeDouble(totalDiscountPercentProjected);

		objectOutput.writeInt(camId);

		if (commentary == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(commentary);
		}

		objectOutput.writeDouble(grossSalesProjected);

		objectOutput.writeDouble(totalDiscountPercentChange);

		objectOutput.writeDouble(totalDiscountPercentPrior);

		objectOutput.writeDouble(netSalesPercentChange);

		objectOutput.writeBoolean(parent);

		objectOutput.writeInt(projectionPeriod);

		objectOutput.writeInt(projectionDetailsSid);

		objectOutput.writeDouble(netSalesPrior);

		objectOutput.writeDouble(netSalesProjected);

		if (reasonCodes == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(reasonCodes);
		}

		objectOutput.writeInt(mAssumptionsSid);
	}

	public double grossSalesPercentChange;
	public double grossSalesPrior;
	public int projYear;
	public double totalDiscountPercentProjected;
	public int camId;
	public String commentary;
	public double grossSalesProjected;
	public double totalDiscountPercentChange;
	public double totalDiscountPercentPrior;
	public double netSalesPercentChange;
	public boolean parent;
	public int projectionPeriod;
	public int projectionDetailsSid;
	public double netSalesPrior;
	public double netSalesProjected;
	public String reasonCodes;
	public int mAssumptionsSid;
}