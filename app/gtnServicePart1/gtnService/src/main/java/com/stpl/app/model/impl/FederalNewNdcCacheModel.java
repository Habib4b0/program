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

import com.stpl.app.model.FederalNewNdc;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing FederalNewNdc in entity cache.
 *
 * @author
 * @see FederalNewNdc
 * @generated
 */
@ProviderType
public class FederalNewNdcCacheModel implements CacheModel<FederalNewNdc>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof FederalNewNdcCacheModel)) {
			return false;
		}

		FederalNewNdcCacheModel federalNewNdcCacheModel = (FederalNewNdcCacheModel)obj;

		if (itemMasterSid == federalNewNdcCacheModel.itemMasterSid) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, itemMasterSid);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(9);

		sb.append("{fss=");
		sb.append(fss);
		sb.append(", itemMasterSid=");
		sb.append(itemMasterSid);
		sb.append(", wacPrice=");
		sb.append(wacPrice);
		sb.append(", nonFamp=");
		sb.append(nonFamp);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public FederalNewNdc toEntityModel() {
		FederalNewNdcImpl federalNewNdcImpl = new FederalNewNdcImpl();

		federalNewNdcImpl.setFss(fss);
		federalNewNdcImpl.setItemMasterSid(itemMasterSid);
		federalNewNdcImpl.setWacPrice(wacPrice);
		federalNewNdcImpl.setNonFamp(nonFamp);

		federalNewNdcImpl.resetOriginalValues();

		return federalNewNdcImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		fss = objectInput.readDouble();

		itemMasterSid = objectInput.readInt();

		wacPrice = objectInput.readDouble();

		nonFamp = objectInput.readDouble();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeDouble(fss);

		objectOutput.writeInt(itemMasterSid);

		objectOutput.writeDouble(wacPrice);

		objectOutput.writeDouble(nonFamp);
	}

	public double fss;
	public int itemMasterSid;
	public double wacPrice;
	public double nonFamp;
}