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
public class StSelectionTablePK implements Comparable<StSelectionTablePK>,
	Serializable {
	public String selectionType;
	public int userId;
	public String sessionId;
	public int companyItemSid;

	public StSelectionTablePK() {
	}

	public StSelectionTablePK(String selectionType, int userId,
		String sessionId, int companyItemSid) {
		this.selectionType = selectionType;
		this.userId = userId;
		this.sessionId = sessionId;
		this.companyItemSid = companyItemSid;
	}

	public String getSelectionType() {
		return selectionType;
	}

	public void setSelectionType(String selectionType) {
		this.selectionType = selectionType;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public int getCompanyItemSid() {
		return companyItemSid;
	}

	public void setCompanyItemSid(int companyItemSid) {
		this.companyItemSid = companyItemSid;
	}

	@Override
	public int compareTo(StSelectionTablePK pk) {
		if (pk == null) {
			return -1;
		}

		int value = 0;

		value = selectionType.compareTo(pk.selectionType);

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

		value = sessionId.compareTo(pk.sessionId);

		if (value != 0) {
			return value;
		}

		if (companyItemSid < pk.companyItemSid) {
			value = -1;
		}
		else if (companyItemSid > pk.companyItemSid) {
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

		if (!(obj instanceof StSelectionTablePK)) {
			return false;
		}

		StSelectionTablePK pk = (StSelectionTablePK)obj;

		if ((selectionType.equals(pk.selectionType)) && (userId == pk.userId) &&
				(sessionId.equals(pk.sessionId)) &&
				(companyItemSid == pk.companyItemSid)) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		int hashCode = 0;

		hashCode = HashUtil.hash(hashCode, selectionType);
		hashCode = HashUtil.hash(hashCode, userId);
		hashCode = HashUtil.hash(hashCode, sessionId);
		hashCode = HashUtil.hash(hashCode, companyItemSid);

		return hashCode;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(20);

		sb.append(StringPool.OPEN_CURLY_BRACE);

		sb.append("selectionType");
		sb.append(StringPool.EQUAL);
		sb.append(selectionType);

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
		sb.append("companyItemSid");
		sb.append(StringPool.EQUAL);
		sb.append(companyItemSid);

		sb.append(StringPool.CLOSE_CURLY_BRACE);

		return sb.toString();
	}
}