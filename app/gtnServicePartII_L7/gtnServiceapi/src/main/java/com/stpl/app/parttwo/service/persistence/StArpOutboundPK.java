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

package com.stpl.app.parttwo.service.persistence;

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
public class StArpOutboundPK implements Comparable<StArpOutboundPK>,
	Serializable {
	public int companyMasterSid;
	public int userId;
	public int arpMasterSid;
	public int arpId;
	public int periodSid;
	public int itemMasterSid;
	public int rsModelSid;
	public String sessionId;

	public StArpOutboundPK() {
	}

	public StArpOutboundPK(int companyMasterSid, int userId, int arpMasterSid,
		int arpId, int periodSid, int itemMasterSid, int rsModelSid,
		String sessionId) {
		this.companyMasterSid = companyMasterSid;
		this.userId = userId;
		this.arpMasterSid = arpMasterSid;
		this.arpId = arpId;
		this.periodSid = periodSid;
		this.itemMasterSid = itemMasterSid;
		this.rsModelSid = rsModelSid;
		this.sessionId = sessionId;
	}

	public int getCompanyMasterSid() {
		return companyMasterSid;
	}

	public void setCompanyMasterSid(int companyMasterSid) {
		this.companyMasterSid = companyMasterSid;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getArpMasterSid() {
		return arpMasterSid;
	}

	public void setArpMasterSid(int arpMasterSid) {
		this.arpMasterSid = arpMasterSid;
	}

	public int getArpId() {
		return arpId;
	}

	public void setArpId(int arpId) {
		this.arpId = arpId;
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

	public int getRsModelSid() {
		return rsModelSid;
	}

	public void setRsModelSid(int rsModelSid) {
		this.rsModelSid = rsModelSid;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	@Override
	public int compareTo(StArpOutboundPK pk) {
		if (pk == null) {
			return -1;
		}

		int value = 0;

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

		if (arpMasterSid < pk.arpMasterSid) {
			value = -1;
		}
		else if (arpMasterSid > pk.arpMasterSid) {
			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

		if (arpId < pk.arpId) {
			value = -1;
		}
		else if (arpId > pk.arpId) {
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

		value = sessionId.compareTo(pk.sessionId);

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

		if (!(obj instanceof StArpOutboundPK)) {
			return false;
		}

		StArpOutboundPK pk = (StArpOutboundPK)obj;

		if ((companyMasterSid == pk.companyMasterSid) && (userId == pk.userId) &&
				(arpMasterSid == pk.arpMasterSid) && (arpId == pk.arpId) &&
				(periodSid == pk.periodSid) &&
				(itemMasterSid == pk.itemMasterSid) &&
				(rsModelSid == pk.rsModelSid) &&
				(sessionId.equals(pk.sessionId))) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		int hashCode = 0;

		hashCode = HashUtil.hash(hashCode, companyMasterSid);
		hashCode = HashUtil.hash(hashCode, userId);
		hashCode = HashUtil.hash(hashCode, arpMasterSid);
		hashCode = HashUtil.hash(hashCode, arpId);
		hashCode = HashUtil.hash(hashCode, periodSid);
		hashCode = HashUtil.hash(hashCode, itemMasterSid);
		hashCode = HashUtil.hash(hashCode, rsModelSid);
		hashCode = HashUtil.hash(hashCode, sessionId);

		return hashCode;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(40);

		sb.append(StringPool.OPEN_CURLY_BRACE);

		sb.append("companyMasterSid");
		sb.append(StringPool.EQUAL);
		sb.append(companyMasterSid);

		sb.append(StringPool.COMMA);
		sb.append(StringPool.SPACE);
		sb.append("userId");
		sb.append(StringPool.EQUAL);
		sb.append(userId);

		sb.append(StringPool.COMMA);
		sb.append(StringPool.SPACE);
		sb.append("arpMasterSid");
		sb.append(StringPool.EQUAL);
		sb.append(arpMasterSid);

		sb.append(StringPool.COMMA);
		sb.append(StringPool.SPACE);
		sb.append("arpId");
		sb.append(StringPool.EQUAL);
		sb.append(arpId);

		sb.append(StringPool.COMMA);
		sb.append(StringPool.SPACE);
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
		sb.append("rsModelSid");
		sb.append(StringPool.EQUAL);
		sb.append(rsModelSid);

		sb.append(StringPool.COMMA);
		sb.append(StringPool.SPACE);
		sb.append("sessionId");
		sb.append(StringPool.EQUAL);
		sb.append(sessionId);

		sb.append(StringPool.CLOSE_CURLY_BRACE);

		return sb.toString();
	}
}