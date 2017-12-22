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

import com.stpl.app.parttwo.model.CffDocDetails;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing CffDocDetails in entity cache.
 *
 * @author
 * @see CffDocDetails
 * @generated
 */
@ProviderType
public class CffDocDetailsCacheModel implements CacheModel<CffDocDetails>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CffDocDetailsCacheModel)) {
			return false;
		}

		CffDocDetailsCacheModel cffDocDetailsCacheModel = (CffDocDetailsCacheModel)obj;

		if (cffDocDetailsSid == cffDocDetailsCacheModel.cffDocDetailsSid) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, cffDocDetailsSid);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(15);

		sb.append("{fileName=");
		sb.append(fileName);
		sb.append(", uploadDate=");
		sb.append(uploadDate);
		sb.append(", fileType=");
		sb.append(fileType);
		sb.append(", uploadBy=");
		sb.append(uploadBy);
		sb.append(", cffMasterSid=");
		sb.append(cffMasterSid);
		sb.append(", cffDocDetailsSid=");
		sb.append(cffDocDetailsSid);
		sb.append(", fileSize=");
		sb.append(fileSize);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public CffDocDetails toEntityModel() {
		CffDocDetailsImpl cffDocDetailsImpl = new CffDocDetailsImpl();

		if (fileName == null) {
			cffDocDetailsImpl.setFileName(StringPool.BLANK);
		}
		else {
			cffDocDetailsImpl.setFileName(fileName);
		}

		if (uploadDate == Long.MIN_VALUE) {
			cffDocDetailsImpl.setUploadDate(null);
		}
		else {
			cffDocDetailsImpl.setUploadDate(new Date(uploadDate));
		}

		if (fileType == null) {
			cffDocDetailsImpl.setFileType(StringPool.BLANK);
		}
		else {
			cffDocDetailsImpl.setFileType(fileType);
		}

		cffDocDetailsImpl.setUploadBy(uploadBy);
		cffDocDetailsImpl.setCffMasterSid(cffMasterSid);
		cffDocDetailsImpl.setCffDocDetailsSid(cffDocDetailsSid);

		if (fileSize == null) {
			cffDocDetailsImpl.setFileSize(StringPool.BLANK);
		}
		else {
			cffDocDetailsImpl.setFileSize(fileSize);
		}

		cffDocDetailsImpl.resetOriginalValues();

		return cffDocDetailsImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		fileName = objectInput.readUTF();
		uploadDate = objectInput.readLong();
		fileType = objectInput.readUTF();

		uploadBy = objectInput.readInt();

		cffMasterSid = objectInput.readInt();

		cffDocDetailsSid = objectInput.readInt();
		fileSize = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		if (fileName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(fileName);
		}

		objectOutput.writeLong(uploadDate);

		if (fileType == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(fileType);
		}

		objectOutput.writeInt(uploadBy);

		objectOutput.writeInt(cffMasterSid);

		objectOutput.writeInt(cffDocDetailsSid);

		if (fileSize == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(fileSize);
		}
	}

	public String fileName;
	public long uploadDate;
	public String fileType;
	public int uploadBy;
	public int cffMasterSid;
	public int cffDocDetailsSid;
	public String fileSize;
}