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

import com.stpl.app.model.TransactionModuleMaster;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing TransactionModuleMaster in entity cache.
 *
 * @author
 * @see TransactionModuleMaster
 * @generated
 */
@ProviderType
public class TransactionModuleMasterCacheModel implements CacheModel<TransactionModuleMaster>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof TransactionModuleMasterCacheModel)) {
			return false;
		}

		TransactionModuleMasterCacheModel transactionModuleMasterCacheModel = (TransactionModuleMasterCacheModel)obj;

		if (transactionModuleMasterSid == transactionModuleMasterCacheModel.transactionModuleMasterSid) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, transactionModuleMasterSid);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(11);

		sb.append("{transactionModuleMasterSid=");
		sb.append(transactionModuleMasterSid);
		sb.append(", invalidTableName=");
		sb.append(invalidTableName);
		sb.append(", tableName=");
		sb.append(tableName);
		sb.append(", moduleName=");
		sb.append(moduleName);
		sb.append(", tabName=");
		sb.append(tabName);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public TransactionModuleMaster toEntityModel() {
		TransactionModuleMasterImpl transactionModuleMasterImpl = new TransactionModuleMasterImpl();

		transactionModuleMasterImpl.setTransactionModuleMasterSid(transactionModuleMasterSid);

		if (invalidTableName == null) {
			transactionModuleMasterImpl.setInvalidTableName(StringPool.BLANK);
		}
		else {
			transactionModuleMasterImpl.setInvalidTableName(invalidTableName);
		}

		if (tableName == null) {
			transactionModuleMasterImpl.setTableName(StringPool.BLANK);
		}
		else {
			transactionModuleMasterImpl.setTableName(tableName);
		}

		if (moduleName == null) {
			transactionModuleMasterImpl.setModuleName(StringPool.BLANK);
		}
		else {
			transactionModuleMasterImpl.setModuleName(moduleName);
		}

		if (tabName == null) {
			transactionModuleMasterImpl.setTabName(StringPool.BLANK);
		}
		else {
			transactionModuleMasterImpl.setTabName(tabName);
		}

		transactionModuleMasterImpl.resetOriginalValues();

		return transactionModuleMasterImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		transactionModuleMasterSid = objectInput.readInt();
		invalidTableName = objectInput.readUTF();
		tableName = objectInput.readUTF();
		moduleName = objectInput.readUTF();
		tabName = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeInt(transactionModuleMasterSid);

		if (invalidTableName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(invalidTableName);
		}

		if (tableName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(tableName);
		}

		if (moduleName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(moduleName);
		}

		if (tabName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(tabName);
		}
	}

	public int transactionModuleMasterSid;
	public String invalidTableName;
	public String tableName;
	public String moduleName;
	public String tabName;
}