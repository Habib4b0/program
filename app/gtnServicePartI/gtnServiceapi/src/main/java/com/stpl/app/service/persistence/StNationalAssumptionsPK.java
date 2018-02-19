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
public class StNationalAssumptionsPK implements Comparable<StNationalAssumptionsPK>,
	Serializable {
	public String startPeriod;
	public int userId;
	public String endPeriod;
	public int naProjMasterSid;
	public String priceType;
	public int sessionId;

	public StNationalAssumptionsPK() {
	}

	public StNationalAssumptionsPK(String startPeriod, int userId,
		String endPeriod, int naProjMasterSid, String priceType, int sessionId) {
		this.startPeriod = startPeriod;
		this.userId = userId;
		this.endPeriod = endPeriod;
		this.naProjMasterSid = naProjMasterSid;
		this.priceType = priceType;
		this.sessionId = sessionId;
	}

	public String getStartPeriod() {
		return startPeriod;
	}

	public void setStartPeriod(String startPeriod) {
		this.startPeriod = startPeriod;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
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

	public String getPriceType() {
		return priceType;
	}

	public void setPriceType(String priceType) {
		this.priceType = priceType;
	}

	public int getSessionId() {
		return sessionId;
	}

	public void setSessionId(int sessionId) {
		this.sessionId = sessionId;
	}

	@Override
	public int compareTo(StNationalAssumptionsPK pk) {
		if (pk == null) {
			return -1;
		}

		int value = 0;

		value = startPeriod.compareTo(pk.startPeriod);

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

		value = priceType.compareTo(pk.priceType);

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

		if (!(obj instanceof StNationalAssumptionsPK)) {
			return false;
		}

		StNationalAssumptionsPK pk = (StNationalAssumptionsPK)obj;

		if ((startPeriod.equals(pk.startPeriod)) && (userId == pk.userId) &&
				(endPeriod.equals(pk.endPeriod)) &&
				(naProjMasterSid == pk.naProjMasterSid) &&
				(priceType.equals(pk.priceType)) &&
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

		hashCode = HashUtil.hash(hashCode, startPeriod);
		hashCode = HashUtil.hash(hashCode, userId);
		hashCode = HashUtil.hash(hashCode, endPeriod);
		hashCode = HashUtil.hash(hashCode, naProjMasterSid);
		hashCode = HashUtil.hash(hashCode, priceType);
		hashCode = HashUtil.hash(hashCode, sessionId);

		return hashCode;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(30);

		sb.append(StringPool.OPEN_CURLY_BRACE);

		sb.append("startPeriod");
		sb.append(StringPool.EQUAL);
		sb.append(startPeriod);

		sb.append(StringPool.COMMA);
		sb.append(StringPool.SPACE);
		sb.append("userId");
		sb.append(StringPool.EQUAL);
		sb.append(userId);

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

		sb.append(StringPool.COMMA);
		sb.append(StringPool.SPACE);
		sb.append("priceType");
		sb.append(StringPool.EQUAL);
		sb.append(priceType);

		sb.append(StringPool.COMMA);
		sb.append(StringPool.SPACE);
		sb.append("sessionId");
		sb.append(StringPool.EQUAL);
		sb.append(sessionId);

		sb.append(StringPool.CLOSE_CURLY_BRACE);

		return sb.toString();
	}
}