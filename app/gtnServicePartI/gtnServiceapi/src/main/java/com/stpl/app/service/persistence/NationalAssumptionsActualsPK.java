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
public class NationalAssumptionsActualsPK implements Comparable<NationalAssumptionsActualsPK>,
	Serializable {
	public int periodSid;
	public int itemMasterSid;
	public String priceType;

	public NationalAssumptionsActualsPK() {
	}

	public NationalAssumptionsActualsPK(int periodSid, int itemMasterSid,
		String priceType) {
		this.periodSid = periodSid;
		this.itemMasterSid = itemMasterSid;
		this.priceType = priceType;
	}

	public int getPeriodSid() {
		return periodSid;
	}

	public void setPeriodSid(int periodSid) {
		this.periodSid = periodSid;
	}

	public int getItemMasterSid() {
		return itemMasterSid;
	}

	public void setItemMasterSid(int itemMasterSid) {
		this.itemMasterSid = itemMasterSid;
	}

	public String getPriceType() {
		return priceType;
	}

	public void setPriceType(String priceType) {
		this.priceType = priceType;
	}

	@Override
	public int compareTo(NationalAssumptionsActualsPK pk) {
		if (pk == null) {
			return -1;
		}

		int value = 0;

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

		value = priceType.compareTo(pk.priceType);

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

		if (!(obj instanceof NationalAssumptionsActualsPK)) {
			return false;
		}

		NationalAssumptionsActualsPK pk = (NationalAssumptionsActualsPK)obj;

		if ((periodSid == pk.periodSid) && (itemMasterSid == pk.itemMasterSid) &&
				(priceType.equals(pk.priceType))) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		int hashCode = 0;

		hashCode = HashUtil.hash(hashCode, periodSid);
		hashCode = HashUtil.hash(hashCode, itemMasterSid);
		hashCode = HashUtil.hash(hashCode, priceType);

		return hashCode;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(15);

		sb.append(StringPool.OPEN_CURLY_BRACE);

		sb.append("periodSid");
		sb.append(StringPool.EQUAL);
		sb.append(periodSid);

		sb.append(StringPool.COMMA);
		sb.append(StringPool.SPACE);
		sb.append("itemMasterSid");
		sb.append(StringPool.EQUAL);
		sb.append(itemMasterSid);

		sb.append(StringPool.COMMA);
		sb.append(StringPool.SPACE);
		sb.append("priceType");
		sb.append(StringPool.EQUAL);
		sb.append(priceType);

		sb.append(StringPool.CLOSE_CURLY_BRACE);

		return sb.toString();
	}
}