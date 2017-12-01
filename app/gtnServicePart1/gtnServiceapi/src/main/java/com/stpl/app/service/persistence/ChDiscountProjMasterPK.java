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
public class ChDiscountProjMasterPK implements Comparable<ChDiscountProjMasterPK>,
	Serializable {
	public int projectionDetailsSid;
	public int rsModelSid;

	public ChDiscountProjMasterPK() {
	}

	public ChDiscountProjMasterPK(int projectionDetailsSid, int rsModelSid) {
		this.projectionDetailsSid = projectionDetailsSid;
		this.rsModelSid = rsModelSid;
	}

	public int getProjectionDetailsSid() {
		return projectionDetailsSid;
	}

	public void setProjectionDetailsSid(int projectionDetailsSid) {
		this.projectionDetailsSid = projectionDetailsSid;
	}

	public int getRsModelSid() {
		return rsModelSid;
	}

	public void setRsModelSid(int rsModelSid) {
		this.rsModelSid = rsModelSid;
	}

	@Override
	public int compareTo(ChDiscountProjMasterPK pk) {
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

		if (!(obj instanceof ChDiscountProjMasterPK)) {
			return false;
		}

		ChDiscountProjMasterPK pk = (ChDiscountProjMasterPK)obj;

		if ((projectionDetailsSid == pk.projectionDetailsSid) &&
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

		hashCode = HashUtil.hash(hashCode, projectionDetailsSid);
		hashCode = HashUtil.hash(hashCode, rsModelSid);

		return hashCode;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(10);

		sb.append(StringPool.OPEN_CURLY_BRACE);

		sb.append("projectionDetailsSid");
		sb.append(StringPool.EQUAL);
		sb.append(projectionDetailsSid);

		sb.append(StringPool.COMMA);
		sb.append(StringPool.SPACE);
		sb.append("rsModelSid");
		sb.append(StringPool.EQUAL);
		sb.append(rsModelSid);

		sb.append(StringPool.CLOSE_CURLY_BRACE);

		return sb.toString();
	}
}