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

import com.stpl.app.model.AdditionalNotes;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing AdditionalNotes in entity cache.
 *
 * @author
 * @see AdditionalNotes
 * @generated
 */
@ProviderType
public class AdditionalNotesCacheModel implements CacheModel<AdditionalNotes>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AdditionalNotesCacheModel)) {
			return false;
		}

		AdditionalNotesCacheModel additionalNotesCacheModel = (AdditionalNotesCacheModel)obj;

		if (additionalNotesId == additionalNotesCacheModel.additionalNotesId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, additionalNotesId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(13);

		sb.append("{createdDate=");
		sb.append(createdDate);
		sb.append(", createdBy=");
		sb.append(createdBy);
		sb.append(", forecastType=");
		sb.append(forecastType);
		sb.append(", additionalNotesId=");
		sb.append(additionalNotesId);
		sb.append(", projectionId=");
		sb.append(projectionId);
		sb.append(", notes=");
		sb.append(notes);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public AdditionalNotes toEntityModel() {
		AdditionalNotesImpl additionalNotesImpl = new AdditionalNotesImpl();

		if (createdDate == Long.MIN_VALUE) {
			additionalNotesImpl.setCreatedDate(null);
		}
		else {
			additionalNotesImpl.setCreatedDate(new Date(createdDate));
		}

		if (createdBy == null) {
			additionalNotesImpl.setCreatedBy(StringPool.BLANK);
		}
		else {
			additionalNotesImpl.setCreatedBy(createdBy);
		}

		if (forecastType == null) {
			additionalNotesImpl.setForecastType(StringPool.BLANK);
		}
		else {
			additionalNotesImpl.setForecastType(forecastType);
		}

		additionalNotesImpl.setAdditionalNotesId(additionalNotesId);
		additionalNotesImpl.setProjectionId(projectionId);

		if (notes == null) {
			additionalNotesImpl.setNotes(StringPool.BLANK);
		}
		else {
			additionalNotesImpl.setNotes(notes);
		}

		additionalNotesImpl.resetOriginalValues();

		return additionalNotesImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		createdDate = objectInput.readLong();
		createdBy = objectInput.readUTF();
		forecastType = objectInput.readUTF();

		additionalNotesId = objectInput.readInt();

		projectionId = objectInput.readInt();
		notes = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(createdDate);

		if (createdBy == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(createdBy);
		}

		if (forecastType == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(forecastType);
		}

		objectOutput.writeInt(additionalNotesId);

		objectOutput.writeInt(projectionId);

		if (notes == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(notes);
		}
	}

	public long createdDate;
	public String createdBy;
	public String forecastType;
	public int additionalNotesId;
	public int projectionId;
	public String notes;
}