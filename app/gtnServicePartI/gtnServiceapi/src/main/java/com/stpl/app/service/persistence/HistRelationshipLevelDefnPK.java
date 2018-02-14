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
public class HistRelationshipLevelDefnPK implements Comparable<HistRelationshipLevelDefnPK>,
	Serializable {
	public int versionNo;
	public String actionFlag;
	public int relationshipLevelSid;

	public HistRelationshipLevelDefnPK() {
	}

	public HistRelationshipLevelDefnPK(int versionNo, String actionFlag,
		int relationshipLevelSid) {
		this.versionNo = versionNo;
		this.actionFlag = actionFlag;
		this.relationshipLevelSid = relationshipLevelSid;
	}

	public int getVersionNo() {
		return versionNo;
	}

	public void setVersionNo(int versionNo) {
		this.versionNo = versionNo;
	}

	public String getActionFlag() {
		return actionFlag;
	}

	public void setActionFlag(String actionFlag) {
		this.actionFlag = actionFlag;
	}

	public int getRelationshipLevelSid() {
		return relationshipLevelSid;
	}

	public void setRelationshipLevelSid(int relationshipLevelSid) {
		this.relationshipLevelSid = relationshipLevelSid;
	}

	@Override
	public int compareTo(HistRelationshipLevelDefnPK pk) {
		if (pk == null) {
			return -1;
		}

		int value = 0;

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

		value = actionFlag.compareTo(pk.actionFlag);

		if (value != 0) {
			return value;
		}

		if (relationshipLevelSid < pk.relationshipLevelSid) {
			value = -1;
		}
		else if (relationshipLevelSid > pk.relationshipLevelSid) {
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

		if (!(obj instanceof HistRelationshipLevelDefnPK)) {
			return false;
		}

		HistRelationshipLevelDefnPK pk = (HistRelationshipLevelDefnPK)obj;

		if ((versionNo == pk.versionNo) && (actionFlag.equals(pk.actionFlag)) &&
				(relationshipLevelSid == pk.relationshipLevelSid)) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		int hashCode = 0;

		hashCode = HashUtil.hash(hashCode, versionNo);
		hashCode = HashUtil.hash(hashCode, actionFlag);
		hashCode = HashUtil.hash(hashCode, relationshipLevelSid);

		return hashCode;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(15);

		sb.append(StringPool.OPEN_CURLY_BRACE);

		sb.append("versionNo");
		sb.append(StringPool.EQUAL);
		sb.append(versionNo);

		sb.append(StringPool.COMMA);
		sb.append(StringPool.SPACE);
		sb.append("actionFlag");
		sb.append(StringPool.EQUAL);
		sb.append(actionFlag);

		sb.append(StringPool.COMMA);
		sb.append(StringPool.SPACE);
		sb.append("relationshipLevelSid");
		sb.append(StringPool.EQUAL);
		sb.append(relationshipLevelSid);

		sb.append(StringPool.CLOSE_CURLY_BRACE);

		return sb.toString();
	}
}