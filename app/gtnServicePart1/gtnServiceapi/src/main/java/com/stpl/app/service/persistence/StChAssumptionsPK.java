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
public class StChAssumptionsPK implements Comparable<StChAssumptionsPK>,
	Serializable {
	public int projectionDetailsSid;
	public int userId;
	public int quarter;
	public int year;
	public String stChAssumptionsSid;
	public int chAssumptionsSid;
	public int sessionId;

	public StChAssumptionsPK() {
	}

	public StChAssumptionsPK(int projectionDetailsSid, int userId, int quarter,
		int year, String stChAssumptionsSid, int chAssumptionsSid, int sessionId) {
		this.projectionDetailsSid = projectionDetailsSid;
		this.userId = userId;
		this.quarter = quarter;
		this.year = year;
		this.stChAssumptionsSid = stChAssumptionsSid;
		this.chAssumptionsSid = chAssumptionsSid;
		this.sessionId = sessionId;
	}

	public int getProjectionDetailsSid() {
		return projectionDetailsSid;
	}

	public void setProjectionDetailsSid(int projectionDetailsSid) {
		this.projectionDetailsSid = projectionDetailsSid;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getQuarter() {
		return quarter;
	}

	public void setQuarter(int quarter) {
		this.quarter = quarter;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getStChAssumptionsSid() {
		return stChAssumptionsSid;
	}

	public void setStChAssumptionsSid(String stChAssumptionsSid) {
		this.stChAssumptionsSid = stChAssumptionsSid;
	}

	public int getChAssumptionsSid() {
		return chAssumptionsSid;
	}

	public void setChAssumptionsSid(int chAssumptionsSid) {
		this.chAssumptionsSid = chAssumptionsSid;
	}

	public int getSessionId() {
		return sessionId;
	}

	public void setSessionId(int sessionId) {
		this.sessionId = sessionId;
	}

	@Override
	public int compareTo(StChAssumptionsPK pk) {
		if (pk == null) {
			return -1;
		}

		int value = 0;

		if (projectionDetailsSid < pk.projectionDetailsSid) {
			value = -1;
		}
		else if (projectionDetailsSid > pk.projectionDetailsSid) {
			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

		if (userId < pk.userId) {
			value = -1;
		}
		else if (userId > pk.userId) {
			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

		if (quarter < pk.quarter) {
			value = -1;
		}
		else if (quarter > pk.quarter) {
			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

		if (year < pk.year) {
			value = -1;
		}
		else if (year > pk.year) {
			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

		value = stChAssumptionsSid.compareTo(pk.stChAssumptionsSid);

		if (value != 0) {
			return value;
		}

		if (chAssumptionsSid < pk.chAssumptionsSid) {
			value = -1;
		}
		else if (chAssumptionsSid > pk.chAssumptionsSid) {
			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

		if (sessionId < pk.sessionId) {
			value = -1;
		}
		else if (sessionId > pk.sessionId) {
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

		if (!(obj instanceof StChAssumptionsPK)) {
			return false;
		}

		StChAssumptionsPK pk = (StChAssumptionsPK)obj;

		if ((projectionDetailsSid == pk.projectionDetailsSid) &&
				(userId == pk.userId) && (quarter == pk.quarter) &&
				(year == pk.year) &&
				(stChAssumptionsSid.equals(pk.stChAssumptionsSid)) &&
				(chAssumptionsSid == pk.chAssumptionsSid) &&
				(sessionId == pk.sessionId)) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		int hashCode = 0;

		hashCode = HashUtil.hash(hashCode, projectionDetailsSid);
		hashCode = HashUtil.hash(hashCode, userId);
		hashCode = HashUtil.hash(hashCode, quarter);
		hashCode = HashUtil.hash(hashCode, year);
		hashCode = HashUtil.hash(hashCode, stChAssumptionsSid);
		hashCode = HashUtil.hash(hashCode, chAssumptionsSid);
		hashCode = HashUtil.hash(hashCode, sessionId);

		return hashCode;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(35);

		sb.append(StringPool.OPEN_CURLY_BRACE);

		sb.append("projectionDetailsSid");
		sb.append(StringPool.EQUAL);
		sb.append(projectionDetailsSid);

		sb.append(StringPool.COMMA);
		sb.append(StringPool.SPACE);
		sb.append("userId");
		sb.append(StringPool.EQUAL);
		sb.append(userId);

		sb.append(StringPool.COMMA);
		sb.append(StringPool.SPACE);
		sb.append("quarter");
		sb.append(StringPool.EQUAL);
		sb.append(quarter);

		sb.append(StringPool.COMMA);
		sb.append(StringPool.SPACE);
		sb.append("year");
		sb.append(StringPool.EQUAL);
		sb.append(year);

		sb.append(StringPool.COMMA);
		sb.append(StringPool.SPACE);
		sb.append("stChAssumptionsSid");
		sb.append(StringPool.EQUAL);
		sb.append(stChAssumptionsSid);

		sb.append(StringPool.COMMA);
		sb.append(StringPool.SPACE);
		sb.append("chAssumptionsSid");
		sb.append(StringPool.EQUAL);
		sb.append(chAssumptionsSid);

		sb.append(StringPool.COMMA);
		sb.append(StringPool.SPACE);
		sb.append("sessionId");
		sb.append(StringPool.EQUAL);
		sb.append(sessionId);

		sb.append(StringPool.CLOSE_CURLY_BRACE);

		return sb.toString();
	}
}