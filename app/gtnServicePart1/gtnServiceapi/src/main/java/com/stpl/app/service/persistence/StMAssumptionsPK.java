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
public class StMAssumptionsPK implements Comparable<StMAssumptionsPK>,
	Serializable {
	public int projYear;
	public int userId;
	public String stMAssumptionsSid;
	public int projectionPeriod;
	public int projectionDetailsSid;
	public int sessionId;
	public int mAssumptionsSid;

	public StMAssumptionsPK() {
	}

	public StMAssumptionsPK(int projYear, int userId, String stMAssumptionsSid,
		int projectionPeriod, int projectionDetailsSid, int sessionId,
		int mAssumptionsSid) {
		this.projYear = projYear;
		this.userId = userId;
		this.stMAssumptionsSid = stMAssumptionsSid;
		this.projectionPeriod = projectionPeriod;
		this.projectionDetailsSid = projectionDetailsSid;
		this.sessionId = sessionId;
		this.mAssumptionsSid = mAssumptionsSid;
	}

	public int getProjYear() {
		return projYear;
	}

	public void setProjYear(int projYear) {
		this.projYear = projYear;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getStMAssumptionsSid() {
		return stMAssumptionsSid;
	}

	public void setStMAssumptionsSid(String stMAssumptionsSid) {
		this.stMAssumptionsSid = stMAssumptionsSid;
	}

	public int getProjectionPeriod() {
		return projectionPeriod;
	}

	public void setProjectionPeriod(int projectionPeriod) {
		this.projectionPeriod = projectionPeriod;
	}

	public int getProjectionDetailsSid() {
		return projectionDetailsSid;
	}

	public void setProjectionDetailsSid(int projectionDetailsSid) {
		this.projectionDetailsSid = projectionDetailsSid;
	}

	public int getSessionId() {
		return sessionId;
	}

	public void setSessionId(int sessionId) {
		this.sessionId = sessionId;
	}

	public int getMAssumptionsSid() {
		return mAssumptionsSid;
	}

	public void setMAssumptionsSid(int mAssumptionsSid) {
		this.mAssumptionsSid = mAssumptionsSid;
	}

	@Override
	public int compareTo(StMAssumptionsPK pk) {
		if (pk == null) {
			return -1;
		}

		int value = 0;

		if (projYear < pk.projYear) {
			value = -1;
		}
		else if (projYear > pk.projYear) {
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

		value = stMAssumptionsSid.compareTo(pk.stMAssumptionsSid);

		if (value != 0) {
			return value;
		}

		if (projectionPeriod < pk.projectionPeriod) {
			value = -1;
		}
		else if (projectionPeriod > pk.projectionPeriod) {
			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

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

		if (mAssumptionsSid < pk.mAssumptionsSid) {
			value = -1;
		}
		else if (mAssumptionsSid > pk.mAssumptionsSid) {
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

		if (!(obj instanceof StMAssumptionsPK)) {
			return false;
		}

		StMAssumptionsPK pk = (StMAssumptionsPK)obj;

		if ((projYear == pk.projYear) && (userId == pk.userId) &&
				(stMAssumptionsSid.equals(pk.stMAssumptionsSid)) &&
				(projectionPeriod == pk.projectionPeriod) &&
				(projectionDetailsSid == pk.projectionDetailsSid) &&
				(sessionId == pk.sessionId) &&
				(mAssumptionsSid == pk.mAssumptionsSid)) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		int hashCode = 0;

		hashCode = HashUtil.hash(hashCode, projYear);
		hashCode = HashUtil.hash(hashCode, userId);
		hashCode = HashUtil.hash(hashCode, stMAssumptionsSid);
		hashCode = HashUtil.hash(hashCode, projectionPeriod);
		hashCode = HashUtil.hash(hashCode, projectionDetailsSid);
		hashCode = HashUtil.hash(hashCode, sessionId);
		hashCode = HashUtil.hash(hashCode, mAssumptionsSid);

		return hashCode;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(35);

		sb.append(StringPool.OPEN_CURLY_BRACE);

		sb.append("projYear");
		sb.append(StringPool.EQUAL);
		sb.append(projYear);

		sb.append(StringPool.COMMA);
		sb.append(StringPool.SPACE);
		sb.append("userId");
		sb.append(StringPool.EQUAL);
		sb.append(userId);

		sb.append(StringPool.COMMA);
		sb.append(StringPool.SPACE);
		sb.append("stMAssumptionsSid");
		sb.append(StringPool.EQUAL);
		sb.append(stMAssumptionsSid);

		sb.append(StringPool.COMMA);
		sb.append(StringPool.SPACE);
		sb.append("projectionPeriod");
		sb.append(StringPool.EQUAL);
		sb.append(projectionPeriod);

		sb.append(StringPool.COMMA);
		sb.append(StringPool.SPACE);
		sb.append("projectionDetailsSid");
		sb.append(StringPool.EQUAL);
		sb.append(projectionDetailsSid);

		sb.append(StringPool.COMMA);
		sb.append(StringPool.SPACE);
		sb.append("sessionId");
		sb.append(StringPool.EQUAL);
		sb.append(sessionId);

		sb.append(StringPool.COMMA);
		sb.append(StringPool.SPACE);
		sb.append("mAssumptionsSid");
		sb.append(StringPool.EQUAL);
		sb.append(mAssumptionsSid);

		sb.append(StringPool.CLOSE_CURLY_BRACE);

		return sb.toString();
	}
}