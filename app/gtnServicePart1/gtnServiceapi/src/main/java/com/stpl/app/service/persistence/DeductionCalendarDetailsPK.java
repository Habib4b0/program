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

package com.stpl.app.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.io.Serializable;

/**
 * @author
 * @generated
 */
@ProviderType
public class DeductionCalendarDetailsPK implements Comparable<DeductionCalendarDetailsPK>,
	Serializable {
	public int deductionCalendarMasterSid;
	public int periodSid;
	public int companyMasterSid;
	public int itemMasterSid;

	public DeductionCalendarDetailsPK() {
	}

	public DeductionCalendarDetailsPK(int deductionCalendarMasterSid,
		int periodSid, int companyMasterSid, int itemMasterSid) {
		this.deductionCalendarMasterSid = deductionCalendarMasterSid;
		this.periodSid = periodSid;
		this.companyMasterSid = companyMasterSid;
		this.itemMasterSid = itemMasterSid;
	}

	public int getDeductionCalendarMasterSid() {
		return deductionCalendarMasterSid;
	}

	public void setDeductionCalendarMasterSid(int deductionCalendarMasterSid) {
		this.deductionCalendarMasterSid = deductionCalendarMasterSid;
	}

	public int getPeriodSid() {
		return periodSid;
	}

	public void setPeriodSid(int periodSid) {
		this.periodSid = periodSid;
	}

	public int getCompanyMasterSid() {
		return companyMasterSid;
	}

	public void setCompanyMasterSid(int companyMasterSid) {
		this.companyMasterSid = companyMasterSid;
	}

	public int getItemMasterSid() {
		return itemMasterSid;
	}

	public void setItemMasterSid(int itemMasterSid) {
		this.itemMasterSid = itemMasterSid;
	}

	@Override
	public int compareTo(DeductionCalendarDetailsPK pk) {
		if (pk == null) {
			return -1;
		}

		int value = 0;

		if (deductionCalendarMasterSid < pk.deductionCalendarMasterSid) {
			value = -1;
		}
		else if (deductionCalendarMasterSid > pk.deductionCalendarMasterSid) {
			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

		if (periodSid < pk.periodSid) {
			value = -1;
		}
		else if (periodSid > pk.periodSid) {
			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

		if (companyMasterSid < pk.companyMasterSid) {
			value = -1;
		}
		else if (companyMasterSid > pk.companyMasterSid) {
			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

		if (itemMasterSid < pk.itemMasterSid) {
			value = -1;
		}
		else if (itemMasterSid > pk.itemMasterSid) {
			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof DeductionCalendarDetailsPK)) {
			return false;
		}

		DeductionCalendarDetailsPK pk = (DeductionCalendarDetailsPK)obj;

		if ((deductionCalendarMasterSid == pk.deductionCalendarMasterSid) &&
				(periodSid == pk.periodSid) &&
				(companyMasterSid == pk.companyMasterSid) &&
				(itemMasterSid == pk.itemMasterSid)) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		int hashCode = 0;

		hashCode = HashUtil.hash(hashCode, deductionCalendarMasterSid);
		hashCode = HashUtil.hash(hashCode, periodSid);
		hashCode = HashUtil.hash(hashCode, companyMasterSid);
		hashCode = HashUtil.hash(hashCode, itemMasterSid);

		return hashCode;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(20);

		sb.append(StringPool.OPEN_CURLY_BRACE);

		sb.append("deductionCalendarMasterSid");
		sb.append(StringPool.EQUAL);
		sb.append(deductionCalendarMasterSid);

		sb.append(StringPool.COMMA);
		sb.append(StringPool.SPACE);
		sb.append("periodSid");
		sb.append(StringPool.EQUAL);
		sb.append(periodSid);

		sb.append(StringPool.COMMA);
		sb.append(StringPool.SPACE);
		sb.append("companyMasterSid");
		sb.append(StringPool.EQUAL);
		sb.append(companyMasterSid);

		sb.append(StringPool.COMMA);
		sb.append(StringPool.SPACE);
		sb.append("itemMasterSid");
		sb.append(StringPool.EQUAL);
		sb.append(itemMasterSid);

		sb.append(StringPool.CLOSE_CURLY_BRACE);

		return sb.toString();
	}
}