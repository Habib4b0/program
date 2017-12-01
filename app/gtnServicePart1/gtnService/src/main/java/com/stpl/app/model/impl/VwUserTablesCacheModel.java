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

import com.stpl.app.model.VwUserTables;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing VwUserTables in entity cache.
 *
 * @author
 * @see VwUserTables
 * @generated
 */
@ProviderType
public class VwUserTablesCacheModel implements CacheModel<VwUserTables>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof VwUserTablesCacheModel)) {
			return false;
		}

		VwUserTablesCacheModel vwUserTablesCacheModel = (VwUserTablesCacheModel)obj;

		if (uniqueId == vwUserTablesCacheModel.uniqueId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, uniqueId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(7);

		sb.append("{uniqueId=");
		sb.append(uniqueId);
		sb.append(", tableName=");
		sb.append(tableName);
		sb.append(", columnName=");
		sb.append(columnName);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public VwUserTables toEntityModel() {
		VwUserTablesImpl vwUserTablesImpl = new VwUserTablesImpl();

		vwUserTablesImpl.setUniqueId(uniqueId);

		if (tableName == null) {
			vwUserTablesImpl.setTableName(StringPool.BLANK);
		}
		else {
			vwUserTablesImpl.setTableName(tableName);
		}

		if (columnName == null) {
			vwUserTablesImpl.setColumnName(StringPool.BLANK);
		}
		else {
			vwUserTablesImpl.setColumnName(columnName);
		}

		vwUserTablesImpl.resetOriginalValues();

		return vwUserTablesImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uniqueId = objectInput.readInt();
		tableName = objectInput.readUTF();
		columnName = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeInt(uniqueId);

		if (tableName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(tableName);
		}

		if (columnName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(columnName);
		}
	}

	public int uniqueId;
	public String tableName;
	public String columnName;
}