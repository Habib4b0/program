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
public class NationalAssumptionsPK implements Comparable<NationalAssumptionsPK>,
	Serializable {
	public String startPeriod;
	public String priceType;
	public String endPeriod;
	public int naProjMasterSid;

	public NationalAssumptionsPK() {
	}

	public NationalAssumptionsPK(String startPeriod, String priceType,
		String endPeriod, int naProjMasterSid) {
		this.startPeriod = startPeriod;
		this.priceType = priceType;
		this.endPeriod = endPeriod;
		this.naProjMasterSid = naProjMasterSid;
	}

	public String getStartPeriod() {
		return startPeriod;
	}

	public void setStartPeriod(String startPeriod) {
		this.startPeriod = startPeriod;
	}

	public String getPriceType() {
		return priceType;
	}

	public void setPriceType(String priceType) {
		this.priceType = priceType;
	}

	public String getEndPeriod() {
		return endPeriod;
	}

	public void setEndPeriod(String endPeriod) {
		this.endPeriod = endPeriod;
	}

	public int getNaProjMasterSid() {
		return naProjMasterSid;
	}

	public void setNaProjMasterSid(int naProjMasterSid) {
		this.naProjMasterSid = naProjMasterSid;
	}

	@Override
	public int compareTo(NationalAssumptionsPK pk) {
		if (pk == null) {
			return -1;
		}

		int value = 0;

		value = startPeriod.compareTo(pk.startPeriod);

		if (value != 0) {
			return value;
		}

		value = priceType.compareTo(pk.priceType);

		if (value != 0) {
			return value;
		}

		value = endPeriod.compareTo(pk.endPeriod);

		if (value != 0) {
			return value;
		}

		if (naProjMasterSid < pk.naProjMasterSid) {
			value = -1;
		}
		else if (naProjMasterSid > pk.naProjMasterSid) {
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

		if (!(obj instanceof NationalAssumptionsPK)) {
			return false;
		}

		NationalAssumptionsPK pk = (NationalAssumptionsPK)obj;

		if ((startPeriod.equals(pk.startPeriod)) &&
				(priceType.equals(pk.priceType)) &&
				(endPeriod.equals(pk.endPeriod)) &&
				(naProjMasterSid == pk.naProjMasterSid)) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		int hashCode = 0;

		hashCode = HashUtil.hash(hashCode, startPeriod);
		hashCode = HashUtil.hash(hashCode, priceType);
		hashCode = HashUtil.hash(hashCode, endPeriod);
		hashCode = HashUtil.hash(hashCode, naProjMasterSid);

		return hashCode;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(20);

		sb.append(StringPool.OPEN_CURLY_BRACE);

		sb.append("startPeriod");
		sb.append(StringPool.EQUAL);
		sb.append(startPeriod);

		sb.append(StringPool.COMMA);
		sb.append(StringPool.SPACE);
		sb.append("priceType");
		sb.append(StringPool.EQUAL);
		sb.append(priceType);

		sb.append(StringPool.COMMA);
		sb.append(StringPool.SPACE);
		sb.append("endPeriod");
		sb.append(StringPool.EQUAL);
		sb.append(endPeriod);

		sb.append(StringPool.COMMA);
		sb.append(StringPool.SPACE);
		sb.append("naProjMasterSid");
		sb.append(StringPool.EQUAL);
		sb.append(naProjMasterSid);

		sb.append(StringPool.CLOSE_CURLY_BRACE);

		return sb.toString();
	}
}