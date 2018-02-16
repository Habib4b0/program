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

package com.stpl.app.parttwo.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import com.stpl.app.parttwo.model.VwCompanyTradeClass;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing VwCompanyTradeClass in entity cache.
 *
 * @author
 * @see VwCompanyTradeClass
 * @generated
 */
@ProviderType
public class VwCompanyTradeClassCacheModel implements CacheModel<VwCompanyTradeClass>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof VwCompanyTradeClassCacheModel)) {
			return false;
		}

		VwCompanyTradeClassCacheModel vwCompanyTradeClassCacheModel = (VwCompanyTradeClassCacheModel)obj;

		if (companyTradeClassSid == vwCompanyTradeClassCacheModel.companyTradeClassSid) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, companyTradeClassSid);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(31);

		sb.append("{priorTradeClass=");
		sb.append(priorTradeClass);
		sb.append(", companyTradeClassSid=");
		sb.append(companyTradeClassSid);
		sb.append(", companyIdString=");
		sb.append(companyIdString);
		sb.append(", lastUpdatedDate=");
		sb.append(lastUpdatedDate);
		sb.append(", priorTradeClassStartDate=");
		sb.append(priorTradeClassStartDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", tradeClassEndDate=");
		sb.append(tradeClassEndDate);
		sb.append(", tradeClassStartDate=");
		sb.append(tradeClassStartDate);
		sb.append(", source=");
		sb.append(source);
		sb.append(", createdBy=");
		sb.append(createdBy);
		sb.append(", createdDate=");
		sb.append(createdDate);
		sb.append(", companyTradeClass=");
		sb.append(companyTradeClass);
		sb.append(", batchId=");
		sb.append(batchId);
		sb.append(", addChgDelIndicator=");
		sb.append(addChgDelIndicator);
		sb.append(", modifiedBy=");
		sb.append(modifiedBy);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public VwCompanyTradeClass toEntityModel() {
		VwCompanyTradeClassImpl vwCompanyTradeClassImpl = new VwCompanyTradeClassImpl();

		if (priorTradeClass == null) {
			vwCompanyTradeClassImpl.setPriorTradeClass(StringPool.BLANK);
		}
		else {
			vwCompanyTradeClassImpl.setPriorTradeClass(priorTradeClass);
		}

		vwCompanyTradeClassImpl.setCompanyTradeClassSid(companyTradeClassSid);

		if (companyIdString == null) {
			vwCompanyTradeClassImpl.setCompanyIdString(StringPool.BLANK);
		}
		else {
			vwCompanyTradeClassImpl.setCompanyIdString(companyIdString);
		}

		if (lastUpdatedDate == Long.MIN_VALUE) {
			vwCompanyTradeClassImpl.setLastUpdatedDate(null);
		}
		else {
			vwCompanyTradeClassImpl.setLastUpdatedDate(new Date(lastUpdatedDate));
		}

		if (priorTradeClassStartDate == Long.MIN_VALUE) {
			vwCompanyTradeClassImpl.setPriorTradeClassStartDate(null);
		}
		else {
			vwCompanyTradeClassImpl.setPriorTradeClassStartDate(new Date(
					priorTradeClassStartDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			vwCompanyTradeClassImpl.setModifiedDate(null);
		}
		else {
			vwCompanyTradeClassImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (tradeClassEndDate == Long.MIN_VALUE) {
			vwCompanyTradeClassImpl.setTradeClassEndDate(null);
		}
		else {
			vwCompanyTradeClassImpl.setTradeClassEndDate(new Date(
					tradeClassEndDate));
		}

		if (tradeClassStartDate == Long.MIN_VALUE) {
			vwCompanyTradeClassImpl.setTradeClassStartDate(null);
		}
		else {
			vwCompanyTradeClassImpl.setTradeClassStartDate(new Date(
					tradeClassStartDate));
		}

		if (source == null) {
			vwCompanyTradeClassImpl.setSource(StringPool.BLANK);
		}
		else {
			vwCompanyTradeClassImpl.setSource(source);
		}

		if (createdBy == null) {
			vwCompanyTradeClassImpl.setCreatedBy(StringPool.BLANK);
		}
		else {
			vwCompanyTradeClassImpl.setCreatedBy(createdBy);
		}

		if (createdDate == Long.MIN_VALUE) {
			vwCompanyTradeClassImpl.setCreatedDate(null);
		}
		else {
			vwCompanyTradeClassImpl.setCreatedDate(new Date(createdDate));
		}

		if (companyTradeClass == null) {
			vwCompanyTradeClassImpl.setCompanyTradeClass(StringPool.BLANK);
		}
		else {
			vwCompanyTradeClassImpl.setCompanyTradeClass(companyTradeClass);
		}

		if (batchId == null) {
			vwCompanyTradeClassImpl.setBatchId(StringPool.BLANK);
		}
		else {
			vwCompanyTradeClassImpl.setBatchId(batchId);
		}

		if (addChgDelIndicator == null) {
			vwCompanyTradeClassImpl.setAddChgDelIndicator(StringPool.BLANK);
		}
		else {
			vwCompanyTradeClassImpl.setAddChgDelIndicator(addChgDelIndicator);
		}

		if (modifiedBy == null) {
			vwCompanyTradeClassImpl.setModifiedBy(StringPool.BLANK);
		}
		else {
			vwCompanyTradeClassImpl.setModifiedBy(modifiedBy);
		}

		vwCompanyTradeClassImpl.resetOriginalValues();

		return vwCompanyTradeClassImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		priorTradeClass = objectInput.readUTF();

		companyTradeClassSid = objectInput.readInt();
		companyIdString = objectInput.readUTF();
		lastUpdatedDate = objectInput.readLong();
		priorTradeClassStartDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		tradeClassEndDate = objectInput.readLong();
		tradeClassStartDate = objectInput.readLong();
		source = objectInput.readUTF();
		createdBy = objectInput.readUTF();
		createdDate = objectInput.readLong();
		companyTradeClass = objectInput.readUTF();
		batchId = objectInput.readUTF();
		addChgDelIndicator = objectInput.readUTF();
		modifiedBy = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		if (priorTradeClass == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(priorTradeClass);
		}

		objectOutput.writeInt(companyTradeClassSid);

		if (companyIdString == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(companyIdString);
		}

		objectOutput.writeLong(lastUpdatedDate);
		objectOutput.writeLong(priorTradeClassStartDate);
		objectOutput.writeLong(modifiedDate);
		objectOutput.writeLong(tradeClassEndDate);
		objectOutput.writeLong(tradeClassStartDate);

		if (source == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(source);
		}

		if (createdBy == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(createdBy);
		}

		objectOutput.writeLong(createdDate);

		if (companyTradeClass == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(companyTradeClass);
		}

		if (batchId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(batchId);
		}

		if (addChgDelIndicator == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(addChgDelIndicator);
		}

		if (modifiedBy == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(modifiedBy);
		}
	}

	public String priorTradeClass;
	public int companyTradeClassSid;
	public String companyIdString;
	public long lastUpdatedDate;
	public long priorTradeClassStartDate;
	public long modifiedDate;
	public long tradeClassEndDate;
	public long tradeClassStartDate;
	public String source;
	public String createdBy;
	public long createdDate;
	public String companyTradeClass;
	public String batchId;
	public String addChgDelIndicator;
	public String modifiedBy;
}