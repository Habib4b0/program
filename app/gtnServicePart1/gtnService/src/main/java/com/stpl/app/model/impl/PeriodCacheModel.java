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

import com.stpl.app.model.Period;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Period in entity cache.
 *
 * @author
 * @see Period
 * @generated
 */
@ProviderType
public class PeriodCacheModel implements CacheModel<Period>, Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof PeriodCacheModel)) {
			return false;
		}

		PeriodCacheModel periodCacheModel = (PeriodCacheModel)obj;

		if (periodSid == periodCacheModel.periodSid) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, periodSid);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(13);

		sb.append("{periodSid=");
		sb.append(periodSid);
		sb.append(", periodDate=");
		sb.append(periodDate);
		sb.append(", quarter=");
		sb.append(quarter);
		sb.append(", year=");
		sb.append(year);
		sb.append(", semiAnnual=");
		sb.append(semiAnnual);
		sb.append(", month=");
		sb.append(month);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Period toEntityModel() {
		PeriodImpl periodImpl = new PeriodImpl();

		periodImpl.setPeriodSid(periodSid);

		if (periodDate == Long.MIN_VALUE) {
			periodImpl.setPeriodDate(null);
		}
		else {
			periodImpl.setPeriodDate(new Date(periodDate));
		}

		periodImpl.setQuarter(quarter);
		periodImpl.setYear(year);
		periodImpl.setSemiAnnual(semiAnnual);
		periodImpl.setMonth(month);

		periodImpl.resetOriginalValues();

		return periodImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		periodSid = objectInput.readInt();
		periodDate = objectInput.readLong();

		quarter = objectInput.readInt();

		year = objectInput.readInt();

		semiAnnual = objectInput.readInt();

		month = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeInt(periodSid);
		objectOutput.writeLong(periodDate);

		objectOutput.writeInt(quarter);

		objectOutput.writeInt(year);

		objectOutput.writeInt(semiAnnual);

		objectOutput.writeInt(month);
	}

	public int periodSid;
	public long periodDate;
	public int quarter;
	public int year;
	public int semiAnnual;
	public int month;
}