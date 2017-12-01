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

import com.stpl.app.model.ForecastingViewMaster;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing ForecastingViewMaster in entity cache.
 *
 * @author
 * @see ForecastingViewMaster
 * @generated
 */
@ProviderType
public class ForecastingViewMasterCacheModel implements CacheModel<ForecastingViewMaster>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ForecastingViewMasterCacheModel)) {
			return false;
		}

		ForecastingViewMasterCacheModel forecastingViewMasterCacheModel = (ForecastingViewMasterCacheModel)obj;

		if (viewId == forecastingViewMasterCacheModel.viewId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, viewId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(17);

		sb.append("{createdDate=");
		sb.append(createdDate);
		sb.append(", createdBy=");
		sb.append(createdBy);
		sb.append(", viewType=");
		sb.append(viewType);
		sb.append(", viewId=");
		sb.append(viewId);
		sb.append(", projectionId=");
		sb.append(projectionId);
		sb.append(", modifiedBy=");
		sb.append(modifiedBy);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", viewName=");
		sb.append(viewName);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ForecastingViewMaster toEntityModel() {
		ForecastingViewMasterImpl forecastingViewMasterImpl = new ForecastingViewMasterImpl();

		if (createdDate == Long.MIN_VALUE) {
			forecastingViewMasterImpl.setCreatedDate(null);
		}
		else {
			forecastingViewMasterImpl.setCreatedDate(new Date(createdDate));
		}

		if (createdBy == null) {
			forecastingViewMasterImpl.setCreatedBy(StringPool.BLANK);
		}
		else {
			forecastingViewMasterImpl.setCreatedBy(createdBy);
		}

		if (viewType == null) {
			forecastingViewMasterImpl.setViewType(StringPool.BLANK);
		}
		else {
			forecastingViewMasterImpl.setViewType(viewType);
		}

		forecastingViewMasterImpl.setViewId(viewId);
		forecastingViewMasterImpl.setProjectionId(projectionId);

		if (modifiedBy == null) {
			forecastingViewMasterImpl.setModifiedBy(StringPool.BLANK);
		}
		else {
			forecastingViewMasterImpl.setModifiedBy(modifiedBy);
		}

		if (modifiedDate == Long.MIN_VALUE) {
			forecastingViewMasterImpl.setModifiedDate(null);
		}
		else {
			forecastingViewMasterImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (viewName == null) {
			forecastingViewMasterImpl.setViewName(StringPool.BLANK);
		}
		else {
			forecastingViewMasterImpl.setViewName(viewName);
		}

		forecastingViewMasterImpl.resetOriginalValues();

		return forecastingViewMasterImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		createdDate = objectInput.readLong();
		createdBy = objectInput.readUTF();
		viewType = objectInput.readUTF();

		viewId = objectInput.readInt();

		projectionId = objectInput.readInt();
		modifiedBy = objectInput.readUTF();
		modifiedDate = objectInput.readLong();
		viewName = objectInput.readUTF();
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

		if (viewType == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(viewType);
		}

		objectOutput.writeInt(viewId);

		objectOutput.writeInt(projectionId);

		if (modifiedBy == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(modifiedBy);
		}

		objectOutput.writeLong(modifiedDate);

		if (viewName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(viewName);
		}
	}

	public long createdDate;
	public String createdBy;
	public String viewType;
	public int viewId;
	public int projectionId;
	public String modifiedBy;
	public long modifiedDate;
	public String viewName;
}