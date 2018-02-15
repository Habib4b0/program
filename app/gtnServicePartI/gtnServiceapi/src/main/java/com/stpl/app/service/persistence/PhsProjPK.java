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
public class PhsProjPK implements Comparable<PhsProjPK>, Serializable {
	public int periodSid;
	public String priceType;
	public int naProjDetailsSid;

	public PhsProjPK() {
	}

	public PhsProjPK(int periodSid, String priceType, int naProjDetailsSid) {
		this.periodSid = periodSid;
		this.priceType = priceType;
		this.naProjDetailsSid = naProjDetailsSid;
	}

	public int getPeriodSid() {
		return periodSid;
	}

	public void setPeriodSid(int periodSid) {
		this.periodSid = periodSid;
	}

	public String getPriceType() {
		return priceType;
	}

	public void setPriceType(String priceType) {
		this.priceType = priceType;
	}

	public int getNaProjDetailsSid() {
		return naProjDetailsSid;
	}

	public void setNaProjDetailsSid(int naProjDetailsSid) {
		this.naProjDetailsSid = naProjDetailsSid;
	}

	@Override
	public int compareTo(PhsProjPK pk) {
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

		value = priceType.compareTo(pk.priceType);

		if (value != 0) {
			return value;
		}

		if (naProjDetailsSid < pk.naProjDetailsSid) {
			value = -1;
		}
		else if (naProjDetailsSid > pk.naProjDetailsSid) {
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

		if (!(obj instanceof PhsProjPK)) {
			return false;
		}

		PhsProjPK pk = (PhsProjPK)obj;

		if ((periodSid == pk.periodSid) && (priceType.equals(pk.priceType)) &&
				(naProjDetailsSid == pk.naProjDetailsSid)) {
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
		hashCode = HashUtil.hash(hashCode, priceType);
		hashCode = HashUtil.hash(hashCode, naProjDetailsSid);

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
		sb.append("priceType");
		sb.append(StringPool.EQUAL);
		sb.append(priceType);

		sb.append(StringPool.COMMA);
		sb.append(StringPool.SPACE);
		sb.append("naProjDetailsSid");
		sb.append(StringPool.EQUAL);
		sb.append(naProjDetailsSid);

		sb.append(StringPool.CLOSE_CURLY_BRACE);

		return sb.toString();
	}
}