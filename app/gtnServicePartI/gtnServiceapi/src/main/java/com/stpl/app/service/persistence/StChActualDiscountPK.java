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
public class StChActualDiscountPK implements Comparable<StChActualDiscountPK>,
	Serializable {
	public int periodSid;
	public int projectionDetailsSid;
	public int userId;
	public int sessionId;
	public int rsModelSid;

	public StChActualDiscountPK() {
	}

	public StChActualDiscountPK(int periodSid, int projectionDetailsSid,
		int userId, int sessionId, int rsModelSid) {
		this.periodSid = periodSid;
		this.projectionDetailsSid = projectionDetailsSid;
		this.userId = userId;
		this.sessionId = sessionId;
		this.rsModelSid = rsModelSid;
	}

	public int getPeriodSid() {
		return periodSid;
	}

	public void setPeriodSid(int periodSid) {
		this.periodSid = periodSid;
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

	public int getSessionId() {
		return sessionId;
	}

	public void setSessionId(int sessionId) {
		this.sessionId = sessionId;
	}

	public int getRsModelSid() {
		return rsModelSid;
	}

	public void setRsModelSid(int rsModelSid) {
		this.rsModelSid = rsModelSid;
	}

	@Override
	public int compareTo(StChActualDiscountPK pk) {
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

		if (rsModelSid < pk.rsModelSid) {
			value = -1;
		}
		else if (rsModelSid > pk.rsModelSid) {
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

		if (!(obj instanceof StChActualDiscountPK)) {
			return false;
		}

		StChActualDiscountPK pk = (StChActualDiscountPK)obj;

		if ((periodSid == pk.periodSid) &&
				(projectionDetailsSid == pk.projectionDetailsSid) &&
				(userId == pk.userId) && (sessionId == pk.sessionId) &&
				(rsModelSid == pk.rsModelSid)) {
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
		hashCode = HashUtil.hash(hashCode, projectionDetailsSid);
		hashCode = HashUtil.hash(hashCode, userId);
		hashCode = HashUtil.hash(hashCode, sessionId);
		hashCode = HashUtil.hash(hashCode, rsModelSid);

		return hashCode;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(25);

		sb.append(StringPool.OPEN_CURLY_BRACE);

		sb.append("periodSid");
		sb.append(StringPool.EQUAL);
		sb.append(periodSid);

		sb.append(StringPool.COMMA);
		sb.append(StringPool.SPACE);
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
		sb.append("sessionId");
		sb.append(StringPool.EQUAL);
		sb.append(sessionId);

		sb.append(StringPool.COMMA);
		sb.append(StringPool.SPACE);
		sb.append("rsModelSid");
		sb.append(StringPool.EQUAL);
		sb.append(rsModelSid);

		sb.append(StringPool.CLOSE_CURLY_BRACE);

		return sb.toString();
	}
}