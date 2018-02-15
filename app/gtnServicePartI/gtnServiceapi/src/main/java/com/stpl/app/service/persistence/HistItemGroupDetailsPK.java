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
public class HistItemGroupDetailsPK implements Comparable<HistItemGroupDetailsPK>,
	Serializable {
	public int itemGroupDetailsSid;
	public String actionFlag;
	public int versionNo;

	public HistItemGroupDetailsPK() {
	}

	public HistItemGroupDetailsPK(int itemGroupDetailsSid, String actionFlag,
		int versionNo) {
		this.itemGroupDetailsSid = itemGroupDetailsSid;
		this.actionFlag = actionFlag;
		this.versionNo = versionNo;
	}

	public int getItemGroupDetailsSid() {
		return itemGroupDetailsSid;
	}

	public void setItemGroupDetailsSid(int itemGroupDetailsSid) {
		this.itemGroupDetailsSid = itemGroupDetailsSid;
	}

	public String getActionFlag() {
		return actionFlag;
	}

	public void setActionFlag(String actionFlag) {
		this.actionFlag = actionFlag;
	}

	public int getVersionNo() {
		return versionNo;
	}

	public void setVersionNo(int versionNo) {
		this.versionNo = versionNo;
	}

	@Override
	public int compareTo(HistItemGroupDetailsPK pk) {
		if (pk == null) {
			return -1;
		}

		int value = 0;

		if (itemGroupDetailsSid < pk.itemGroupDetailsSid) {
			value = -1;
		}
		else if (itemGroupDetailsSid > pk.itemGroupDetailsSid) {
			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

		value = actionFlag.compareTo(pk.actionFlag);

		if (value != 0) {
			return value;
		}

		if (versionNo < pk.versionNo) {
			value = -1;
		}
		else if (versionNo > pk.versionNo) {
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

		if (!(obj instanceof HistItemGroupDetailsPK)) {
			return false;
		}

		HistItemGroupDetailsPK pk = (HistItemGroupDetailsPK)obj;

		if ((itemGroupDetailsSid == pk.itemGroupDetailsSid) &&
				(actionFlag.equals(pk.actionFlag)) &&
				(versionNo == pk.versionNo)) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		int hashCode = 0;

		hashCode = HashUtil.hash(hashCode, itemGroupDetailsSid);
		hashCode = HashUtil.hash(hashCode, actionFlag);
		hashCode = HashUtil.hash(hashCode, versionNo);

		return hashCode;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(15);

		sb.append(StringPool.OPEN_CURLY_BRACE);

		sb.append("itemGroupDetailsSid");
		sb.append(StringPool.EQUAL);
		sb.append(itemGroupDetailsSid);

		sb.append(StringPool.COMMA);
		sb.append(StringPool.SPACE);
		sb.append("actionFlag");
		sb.append(StringPool.EQUAL);
		sb.append(actionFlag);

		sb.append(StringPool.COMMA);
		sb.append(StringPool.SPACE);
		sb.append("versionNo");
		sb.append(StringPool.EQUAL);
		sb.append(versionNo);

		sb.append(StringPool.CLOSE_CURLY_BRACE);

		return sb.toString();
	}
}