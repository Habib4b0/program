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

package com.stpl.app.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import com.stpl.app.model.StSelectionTable;
import com.stpl.app.service.persistence.StSelectionTablePK;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing StSelectionTable in entity cache.
 *
 * @author
 * @see StSelectionTable
 * @generated
 */
@ProviderType
public class StSelectionTableCacheModel implements CacheModel<StSelectionTable>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof StSelectionTableCacheModel)) {
			return false;
		}

		StSelectionTableCacheModel stSelectionTableCacheModel = (StSelectionTableCacheModel)obj;

		if (stSelectionTablePK.equals(
					stSelectionTableCacheModel.stSelectionTablePK)) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, stSelectionTablePK);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(11);

		sb.append("{selectionType=");
		sb.append(selectionType);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", sessionId=");
		sb.append(sessionId);
		sb.append(", companyItemSid=");
		sb.append(companyItemSid);
		sb.append(", checkRecord=");
		sb.append(checkRecord);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public StSelectionTable toEntityModel() {
		StSelectionTableImpl stSelectionTableImpl = new StSelectionTableImpl();

		if (selectionType == null) {
			stSelectionTableImpl.setSelectionType(StringPool.BLANK);
		}
		else {
			stSelectionTableImpl.setSelectionType(selectionType);
		}

		stSelectionTableImpl.setUserId(userId);

		if (sessionId == null) {
			stSelectionTableImpl.setSessionId(StringPool.BLANK);
		}
		else {
			stSelectionTableImpl.setSessionId(sessionId);
		}

		stSelectionTableImpl.setCompanyItemSid(companyItemSid);
		stSelectionTableImpl.setCheckRecord(checkRecord);

		stSelectionTableImpl.resetOriginalValues();

		return stSelectionTableImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		selectionType = objectInput.readUTF();

		userId = objectInput.readInt();
		sessionId = objectInput.readUTF();

		companyItemSid = objectInput.readInt();

		checkRecord = objectInput.readBoolean();

		stSelectionTablePK = new StSelectionTablePK(selectionType, userId,
				sessionId, companyItemSid);
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		if (selectionType == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(selectionType);
		}

		objectOutput.writeInt(userId);

		if (sessionId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(sessionId);
		}

		objectOutput.writeInt(companyItemSid);

		objectOutput.writeBoolean(checkRecord);
	}

	public String selectionType;
	public int userId;
	public String sessionId;
	public int companyItemSid;
	public boolean checkRecord;
	public transient StSelectionTablePK stSelectionTablePK;
}