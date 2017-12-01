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

import com.stpl.app.model.TransactionModuleDetails;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing TransactionModuleDetails in entity cache.
 *
 * @author
 * @see TransactionModuleDetails
 * @generated
 */
@ProviderType
public class TransactionModuleDetailsCacheModel implements CacheModel<TransactionModuleDetails>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof TransactionModuleDetailsCacheModel)) {
			return false;
		}

		TransactionModuleDetailsCacheModel transactionModuleDetailsCacheModel = (TransactionModuleDetailsCacheModel)obj;

		if (transactionModuleDetailsSid == transactionModuleDetailsCacheModel.transactionModuleDetailsSid) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, transactionModuleDetailsSid);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(17);

		sb.append("{propertyIndex=");
		sb.append(propertyIndex);
		sb.append(", displayName=");
		sb.append(displayName);
		sb.append(", transactionModuleMasterSid=");
		sb.append(transactionModuleMasterSid);
		sb.append(", categoryName=");
		sb.append(categoryName);
		sb.append(", validation=");
		sb.append(validation);
		sb.append(", propertyName=");
		sb.append(propertyName);
		sb.append(", flag=");
		sb.append(flag);
		sb.append(", transactionModuleDetailsSid=");
		sb.append(transactionModuleDetailsSid);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public TransactionModuleDetails toEntityModel() {
		TransactionModuleDetailsImpl transactionModuleDetailsImpl = new TransactionModuleDetailsImpl();

		transactionModuleDetailsImpl.setPropertyIndex(propertyIndex);

		if (displayName == null) {
			transactionModuleDetailsImpl.setDisplayName(StringPool.BLANK);
		}
		else {
			transactionModuleDetailsImpl.setDisplayName(displayName);
		}

		transactionModuleDetailsImpl.setTransactionModuleMasterSid(transactionModuleMasterSid);

		if (categoryName == null) {
			transactionModuleDetailsImpl.setCategoryName(StringPool.BLANK);
		}
		else {
			transactionModuleDetailsImpl.setCategoryName(categoryName);
		}

		if (validation == null) {
			transactionModuleDetailsImpl.setValidation(StringPool.BLANK);
		}
		else {
			transactionModuleDetailsImpl.setValidation(validation);
		}

		if (propertyName == null) {
			transactionModuleDetailsImpl.setPropertyName(StringPool.BLANK);
		}
		else {
			transactionModuleDetailsImpl.setPropertyName(propertyName);
		}

		if (flag == null) {
			transactionModuleDetailsImpl.setFlag(StringPool.BLANK);
		}
		else {
			transactionModuleDetailsImpl.setFlag(flag);
		}

		transactionModuleDetailsImpl.setTransactionModuleDetailsSid(transactionModuleDetailsSid);

		transactionModuleDetailsImpl.resetOriginalValues();

		return transactionModuleDetailsImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		propertyIndex = objectInput.readDouble();
		displayName = objectInput.readUTF();

		transactionModuleMasterSid = objectInput.readInt();
		categoryName = objectInput.readUTF();
		validation = objectInput.readUTF();
		propertyName = objectInput.readUTF();
		flag = objectInput.readUTF();

		transactionModuleDetailsSid = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeDouble(propertyIndex);

		if (displayName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(displayName);
		}

		objectOutput.writeInt(transactionModuleMasterSid);

		if (categoryName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(categoryName);
		}

		if (validation == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(validation);
		}

		if (propertyName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(propertyName);
		}

		if (flag == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(flag);
		}

		objectOutput.writeInt(transactionModuleDetailsSid);
	}

	public double propertyIndex;
	public String displayName;
	public int transactionModuleMasterSid;
	public String categoryName;
	public String validation;
	public String propertyName;
	public String flag;
	public int transactionModuleDetailsSid;
}