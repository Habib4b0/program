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

import com.stpl.app.model.DocDetails;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing DocDetails in entity cache.
 *
 * @author
 * @see DocDetails
 * @generated
 */
@ProviderType
public class DocDetailsCacheModel implements CacheModel<DocDetails>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof DocDetailsCacheModel)) {
			return false;
		}

		DocDetailsCacheModel docDetailsCacheModel = (DocDetailsCacheModel)obj;

		if (docDetailsId == docDetailsCacheModel.docDetailsId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, docDetailsId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(17);

		sb.append("{fileName=");
		sb.append(fileName);
		sb.append(", fileType=");
		sb.append(fileType);
		sb.append(", uploadedBy=");
		sb.append(uploadedBy);
		sb.append(", forecastType=");
		sb.append(forecastType);
		sb.append(", projectionId=");
		sb.append(projectionId);
		sb.append(", docDetailsId=");
		sb.append(docDetailsId);
		sb.append(", uploadedDate=");
		sb.append(uploadedDate);
		sb.append(", fileSize=");
		sb.append(fileSize);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public DocDetails toEntityModel() {
		DocDetailsImpl docDetailsImpl = new DocDetailsImpl();

		if (fileName == null) {
			docDetailsImpl.setFileName(StringPool.BLANK);
		}
		else {
			docDetailsImpl.setFileName(fileName);
		}

		if (fileType == null) {
			docDetailsImpl.setFileType(StringPool.BLANK);
		}
		else {
			docDetailsImpl.setFileType(fileType);
		}

		if (uploadedBy == null) {
			docDetailsImpl.setUploadedBy(StringPool.BLANK);
		}
		else {
			docDetailsImpl.setUploadedBy(uploadedBy);
		}

		if (forecastType == null) {
			docDetailsImpl.setForecastType(StringPool.BLANK);
		}
		else {
			docDetailsImpl.setForecastType(forecastType);
		}

		docDetailsImpl.setProjectionId(projectionId);
		docDetailsImpl.setDocDetailsId(docDetailsId);

		if (uploadedDate == Long.MIN_VALUE) {
			docDetailsImpl.setUploadedDate(null);
		}
		else {
			docDetailsImpl.setUploadedDate(new Date(uploadedDate));
		}

		if (fileSize == null) {
			docDetailsImpl.setFileSize(StringPool.BLANK);
		}
		else {
			docDetailsImpl.setFileSize(fileSize);
		}

		docDetailsImpl.resetOriginalValues();

		return docDetailsImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		fileName = objectInput.readUTF();
		fileType = objectInput.readUTF();
		uploadedBy = objectInput.readUTF();
		forecastType = objectInput.readUTF();

		projectionId = objectInput.readInt();

		docDetailsId = objectInput.readInt();
		uploadedDate = objectInput.readLong();
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

		if (fileType == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(fileType);
		}

		if (uploadedBy == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(uploadedBy);
		}

		if (forecastType == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(forecastType);
		}

		objectOutput.writeInt(projectionId);

		objectOutput.writeInt(docDetailsId);
		objectOutput.writeLong(uploadedDate);

		if (fileSize == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(fileSize);
		}
	}

	public String fileName;
	public String fileType;
	public String uploadedBy;
	public String forecastType;
	public int projectionId;
	public int docDetailsId;
	public long uploadedDate;
	public String fileSize;
}