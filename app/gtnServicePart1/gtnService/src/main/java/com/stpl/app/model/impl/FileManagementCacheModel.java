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

import com.stpl.app.model.FileManagement;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing FileManagement in entity cache.
 *
 * @author
 * @see FileManagement
 * @generated
 */
@ProviderType
public class FileManagementCacheModel implements CacheModel<FileManagement>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof FileManagementCacheModel)) {
			return false;
		}

		FileManagementCacheModel fileManagementCacheModel = (FileManagementCacheModel)obj;

		if (fileManagementSid == fileManagementCacheModel.fileManagementSid) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, fileManagementSid);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(33);

		sb.append("{country=");
		sb.append(country);
		sb.append(", fromPeriod=");
		sb.append(fromPeriod);
		sb.append(", versionNo=");
		sb.append(versionNo);
		sb.append(", forecastSource=");
		sb.append(forecastSource);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", createdBy=");
		sb.append(createdBy);
		sb.append(", createdDate=");
		sb.append(createdDate);
		sb.append(", version=");
		sb.append(version);
		sb.append(", fileSource=");
		sb.append(fileSource);
		sb.append(", toPeriod=");
		sb.append(toPeriod);
		sb.append(", modifiedBy=");
		sb.append(modifiedBy);
		sb.append(", fileManagementSid=");
		sb.append(fileManagementSid);
		sb.append(", forecastName=");
		sb.append(forecastName);
		sb.append(", fileType=");
		sb.append(fileType);
		sb.append(", businessUnit=");
		sb.append(businessUnit);
		sb.append(", company=");
		sb.append(company);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public FileManagement toEntityModel() {
		FileManagementImpl fileManagementImpl = new FileManagementImpl();

		fileManagementImpl.setCountry(country);

		if (fromPeriod == Long.MIN_VALUE) {
			fileManagementImpl.setFromPeriod(null);
		}
		else {
			fileManagementImpl.setFromPeriod(new Date(fromPeriod));
		}

		fileManagementImpl.setVersionNo(versionNo);

		if (forecastSource == null) {
			fileManagementImpl.setForecastSource(StringPool.BLANK);
		}
		else {
			fileManagementImpl.setForecastSource(forecastSource);
		}

		if (modifiedDate == Long.MIN_VALUE) {
			fileManagementImpl.setModifiedDate(null);
		}
		else {
			fileManagementImpl.setModifiedDate(new Date(modifiedDate));
		}

		fileManagementImpl.setCreatedBy(createdBy);

		if (createdDate == Long.MIN_VALUE) {
			fileManagementImpl.setCreatedDate(null);
		}
		else {
			fileManagementImpl.setCreatedDate(new Date(createdDate));
		}

		if (version == null) {
			fileManagementImpl.setVersion(StringPool.BLANK);
		}
		else {
			fileManagementImpl.setVersion(version);
		}

		if (fileSource == null) {
			fileManagementImpl.setFileSource(StringPool.BLANK);
		}
		else {
			fileManagementImpl.setFileSource(fileSource);
		}

		if (toPeriod == Long.MIN_VALUE) {
			fileManagementImpl.setToPeriod(null);
		}
		else {
			fileManagementImpl.setToPeriod(new Date(toPeriod));
		}

		fileManagementImpl.setModifiedBy(modifiedBy);
		fileManagementImpl.setFileManagementSid(fileManagementSid);

		if (forecastName == null) {
			fileManagementImpl.setForecastName(StringPool.BLANK);
		}
		else {
			fileManagementImpl.setForecastName(forecastName);
		}

		fileManagementImpl.setFileType(fileType);

		if (businessUnit == null) {
			fileManagementImpl.setBusinessUnit(StringPool.BLANK);
		}
		else {
			fileManagementImpl.setBusinessUnit(businessUnit);
		}

		fileManagementImpl.setCompany(company);

		fileManagementImpl.resetOriginalValues();

		return fileManagementImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		country = objectInput.readInt();
		fromPeriod = objectInput.readLong();

		versionNo = objectInput.readInt();
		forecastSource = objectInput.readUTF();
		modifiedDate = objectInput.readLong();

		createdBy = objectInput.readInt();
		createdDate = objectInput.readLong();
		version = objectInput.readUTF();
		fileSource = objectInput.readUTF();
		toPeriod = objectInput.readLong();

		modifiedBy = objectInput.readInt();

		fileManagementSid = objectInput.readInt();
		forecastName = objectInput.readUTF();

		fileType = objectInput.readInt();
		businessUnit = objectInput.readUTF();

		company = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeInt(country);
		objectOutput.writeLong(fromPeriod);

		objectOutput.writeInt(versionNo);

		if (forecastSource == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(forecastSource);
		}

		objectOutput.writeLong(modifiedDate);

		objectOutput.writeInt(createdBy);
		objectOutput.writeLong(createdDate);

		if (version == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(version);
		}

		if (fileSource == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(fileSource);
		}

		objectOutput.writeLong(toPeriod);

		objectOutput.writeInt(modifiedBy);

		objectOutput.writeInt(fileManagementSid);

		if (forecastName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(forecastName);
		}

		objectOutput.writeInt(fileType);

		if (businessUnit == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(businessUnit);
		}

		objectOutput.writeInt(company);
	}

	public int country;
	public long fromPeriod;
	public int versionNo;
	public String forecastSource;
	public long modifiedDate;
	public int createdBy;
	public long createdDate;
	public String version;
	public String fileSource;
	public long toPeriod;
	public int modifiedBy;
	public int fileManagementSid;
	public String forecastName;
	public int fileType;
	public String businessUnit;
	public int company;
}