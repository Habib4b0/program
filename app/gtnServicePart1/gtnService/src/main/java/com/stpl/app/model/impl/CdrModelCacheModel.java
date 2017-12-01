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

import com.stpl.app.model.CdrModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing CdrModel in entity cache.
 *
 * @author
 * @see CdrModel
 * @generated
 */
@ProviderType
public class CdrModelCacheModel implements CacheModel<CdrModel>, Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CdrModelCacheModel)) {
			return false;
		}

		CdrModelCacheModel cdrModelCacheModel = (CdrModelCacheModel)obj;

		if (cdrModelSid == cdrModelCacheModel.cdrModelSid) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, cdrModelSid);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(21);

		sb.append("{createdBy=");
		sb.append(createdBy);
		sb.append(", ruleCategory=");
		sb.append(ruleCategory);
		sb.append(", ruleType=");
		sb.append(ruleType);
		sb.append(", modifiedBy=");
		sb.append(modifiedBy);
		sb.append(", internalNotes=");
		sb.append(internalNotes);
		sb.append(", createdDate=");
		sb.append(createdDate);
		sb.append(", ruleName=");
		sb.append(ruleName);
		sb.append(", cdrModelSid=");
		sb.append(cdrModelSid);
		sb.append(", ruleNo=");
		sb.append(ruleNo);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public CdrModel toEntityModel() {
		CdrModelImpl cdrModelImpl = new CdrModelImpl();

		cdrModelImpl.setCreatedBy(createdBy);
		cdrModelImpl.setRuleCategory(ruleCategory);
		cdrModelImpl.setRuleType(ruleType);
		cdrModelImpl.setModifiedBy(modifiedBy);

		if (internalNotes == null) {
			cdrModelImpl.setInternalNotes(StringPool.BLANK);
		}
		else {
			cdrModelImpl.setInternalNotes(internalNotes);
		}

		if (createdDate == Long.MIN_VALUE) {
			cdrModelImpl.setCreatedDate(null);
		}
		else {
			cdrModelImpl.setCreatedDate(new Date(createdDate));
		}

		if (ruleName == null) {
			cdrModelImpl.setRuleName(StringPool.BLANK);
		}
		else {
			cdrModelImpl.setRuleName(ruleName);
		}

		cdrModelImpl.setCdrModelSid(cdrModelSid);

		if (ruleNo == null) {
			cdrModelImpl.setRuleNo(StringPool.BLANK);
		}
		else {
			cdrModelImpl.setRuleNo(ruleNo);
		}

		if (modifiedDate == Long.MIN_VALUE) {
			cdrModelImpl.setModifiedDate(null);
		}
		else {
			cdrModelImpl.setModifiedDate(new Date(modifiedDate));
		}

		cdrModelImpl.resetOriginalValues();

		return cdrModelImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		createdBy = objectInput.readInt();

		ruleCategory = objectInput.readInt();

		ruleType = objectInput.readInt();

		modifiedBy = objectInput.readInt();
		internalNotes = objectInput.readUTF();
		createdDate = objectInput.readLong();
		ruleName = objectInput.readUTF();

		cdrModelSid = objectInput.readInt();
		ruleNo = objectInput.readUTF();
		modifiedDate = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeInt(createdBy);

		objectOutput.writeInt(ruleCategory);

		objectOutput.writeInt(ruleType);

		objectOutput.writeInt(modifiedBy);

		if (internalNotes == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(internalNotes);
		}

		objectOutput.writeLong(createdDate);

		if (ruleName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(ruleName);
		}

		objectOutput.writeInt(cdrModelSid);

		if (ruleNo == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(ruleNo);
		}

		objectOutput.writeLong(modifiedDate);
	}

	public int createdBy;
	public int ruleCategory;
	public int ruleType;
	public int modifiedBy;
	public String internalNotes;
	public long createdDate;
	public String ruleName;
	public int cdrModelSid;
	public String ruleNo;
	public long modifiedDate;
}