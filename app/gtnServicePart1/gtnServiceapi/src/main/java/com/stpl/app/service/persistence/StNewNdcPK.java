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
public class StNewNdcPK implements Comparable<StNewNdcPK>, Serializable {
	public int naProjDetailsSid;
	public int userId;
	public int itemMasterSid;
	public int sessionId;

	public StNewNdcPK() {
	}

	public StNewNdcPK(int naProjDetailsSid, int userId, int itemMasterSid,
		int sessionId) {
		this.naProjDetailsSid = naProjDetailsSid;
		this.userId = userId;
		this.itemMasterSid = itemMasterSid;
		this.sessionId = sessionId;
	}

	public int getNaProjDetailsSid() {
		return naProjDetailsSid;
	}

	public void setNaProjDetailsSid(int naProjDetailsSid) {
		this.naProjDetailsSid = naProjDetailsSid;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getItemMasterSid() {
		return itemMasterSid;
	}

	public void setItemMasterSid(int itemMasterSid) {
		this.itemMasterSid = itemMasterSid;
	}

	public int getSessionId() {
		return sessionId;
	}

	public void setSessionId(int sessionId) {
		this.sessionId = sessionId;
	}

	@Override
	public int compareTo(StNewNdcPK pk) {
		if (pk == null) {
			return -1;
		}

		int value = 0;

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

		if (!(obj instanceof StNewNdcPK)) {
			return false;
		}

		StNewNdcPK pk = (StNewNdcPK)obj;

		if ((naProjDetailsSid == pk.naProjDetailsSid) && (userId == pk.userId) &&
				(itemMasterSid == pk.itemMasterSid) &&
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

		hashCode = HashUtil.hash(hashCode, naProjDetailsSid);
		hashCode = HashUtil.hash(hashCode, userId);
		hashCode = HashUtil.hash(hashCode, itemMasterSid);
		hashCode = HashUtil.hash(hashCode, sessionId);

		return hashCode;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(20);

		sb.append(StringPool.OPEN_CURLY_BRACE);

		sb.append("naProjDetailsSid");
		sb.append(StringPool.EQUAL);
		sb.append(naProjDetailsSid);

		sb.append(StringPool.COMMA);
		sb.append(StringPool.SPACE);
		sb.append("userId");
		sb.append(StringPool.EQUAL);
		sb.append(userId);

		sb.append(StringPool.COMMA);
		sb.append(StringPool.SPACE);
		sb.append("itemMasterSid");
		sb.append(StringPool.EQUAL);
		sb.append(itemMasterSid);

		sb.append(StringPool.COMMA);
		sb.append(StringPool.SPACE);
		sb.append("sessionId");
		sb.append(StringPool.EQUAL);
		sb.append(sessionId);

		sb.append(StringPool.CLOSE_CURLY_BRACE);

		return sb.toString();
	}
}