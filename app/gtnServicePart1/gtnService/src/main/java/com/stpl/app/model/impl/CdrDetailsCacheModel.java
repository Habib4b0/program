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

import com.stpl.app.model.CdrDetails;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing CdrDetails in entity cache.
 *
 * @author
 * @see CdrDetails
 * @generated
 */
@ProviderType
public class CdrDetailsCacheModel implements CacheModel<CdrDetails>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CdrDetailsCacheModel)) {
			return false;
		}

		CdrDetailsCacheModel cdrDetailsCacheModel = (CdrDetailsCacheModel)obj;

		if (cdrDetailsSid == cdrDetailsCacheModel.cdrDetailsSid) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, cdrDetailsSid);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(27);

		sb.append("{createdBy=");
		sb.append(createdBy);
		sb.append(", modifiedBy=");
		sb.append(modifiedBy);
		sb.append(", createdDate=");
		sb.append(createdDate);
		sb.append(", lineType=");
		sb.append(lineType);
		sb.append(", keyword=");
		sb.append(keyword);
		sb.append(", itemGroupMsAssociation=");
		sb.append(itemGroupMsAssociation);
		sb.append(", value=");
		sb.append(value);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", logicalOperator=");
		sb.append(logicalOperator);
		sb.append(", operator=");
		sb.append(operator);
		sb.append(", cdrDetailsSid=");
		sb.append(cdrDetailsSid);
		sb.append(", cdrModelSid=");
		sb.append(cdrModelSid);
		sb.append(", comparison=");
		sb.append(comparison);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public CdrDetails toEntityModel() {
		CdrDetailsImpl cdrDetailsImpl = new CdrDetailsImpl();

		cdrDetailsImpl.setCreatedBy(createdBy);
		cdrDetailsImpl.setModifiedBy(modifiedBy);

		if (createdDate == Long.MIN_VALUE) {
			cdrDetailsImpl.setCreatedDate(null);
		}
		else {
			cdrDetailsImpl.setCreatedDate(new Date(createdDate));
		}

		if (lineType == null) {
			cdrDetailsImpl.setLineType(StringPool.BLANK);
		}
		else {
			cdrDetailsImpl.setLineType(lineType);
		}

		if (keyword == null) {
			cdrDetailsImpl.setKeyword(StringPool.BLANK);
		}
		else {
			cdrDetailsImpl.setKeyword(keyword);
		}

		if (itemGroupMsAssociation == null) {
			cdrDetailsImpl.setItemGroupMsAssociation(StringPool.BLANK);
		}
		else {
			cdrDetailsImpl.setItemGroupMsAssociation(itemGroupMsAssociation);
		}

		cdrDetailsImpl.setValue(value);

		if (modifiedDate == Long.MIN_VALUE) {
			cdrDetailsImpl.setModifiedDate(null);
		}
		else {
			cdrDetailsImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (logicalOperator == null) {
			cdrDetailsImpl.setLogicalOperator(StringPool.BLANK);
		}
		else {
			cdrDetailsImpl.setLogicalOperator(logicalOperator);
		}

		if (operator == null) {
			cdrDetailsImpl.setOperator(StringPool.BLANK);
		}
		else {
			cdrDetailsImpl.setOperator(operator);
		}

		cdrDetailsImpl.setCdrDetailsSid(cdrDetailsSid);
		cdrDetailsImpl.setCdrModelSid(cdrModelSid);

		if (comparison == null) {
			cdrDetailsImpl.setComparison(StringPool.BLANK);
		}
		else {
			cdrDetailsImpl.setComparison(comparison);
		}

		cdrDetailsImpl.resetOriginalValues();

		return cdrDetailsImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		createdBy = objectInput.readInt();

		modifiedBy = objectInput.readInt();
		createdDate = objectInput.readLong();
		lineType = objectInput.readUTF();
		keyword = objectInput.readUTF();
		itemGroupMsAssociation = objectInput.readUTF();

		value = objectInput.readDouble();
		modifiedDate = objectInput.readLong();
		logicalOperator = objectInput.readUTF();
		operator = objectInput.readUTF();

		cdrDetailsSid = objectInput.readInt();

		cdrModelSid = objectInput.readInt();
		comparison = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeInt(createdBy);

		objectOutput.writeInt(modifiedBy);
		objectOutput.writeLong(createdDate);

		if (lineType == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(lineType);
		}

		if (keyword == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(keyword);
		}

		if (itemGroupMsAssociation == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(itemGroupMsAssociation);
		}

		objectOutput.writeDouble(value);
		objectOutput.writeLong(modifiedDate);

		if (logicalOperator == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(logicalOperator);
		}

		if (operator == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(operator);
		}

		objectOutput.writeInt(cdrDetailsSid);

		objectOutput.writeInt(cdrModelSid);

		if (comparison == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(comparison);
		}
	}

	public int createdBy;
	public int modifiedBy;
	public long createdDate;
	public String lineType;
	public String keyword;
	public String itemGroupMsAssociation;
	public double value;
	public long modifiedDate;
	public String logicalOperator;
	public String operator;
	public int cdrDetailsSid;
	public int cdrModelSid;
	public String comparison;
}