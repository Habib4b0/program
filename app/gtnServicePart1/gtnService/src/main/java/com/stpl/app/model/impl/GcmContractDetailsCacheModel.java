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

import com.stpl.app.model.GcmContractDetails;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing GcmContractDetails in entity cache.
 *
 * @author
 * @see GcmContractDetails
 * @generated
 */
@ProviderType
public class GcmContractDetailsCacheModel implements CacheModel<GcmContractDetails>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof GcmContractDetailsCacheModel)) {
			return false;
		}

		GcmContractDetailsCacheModel gcmContractDetailsCacheModel = (GcmContractDetailsCacheModel)obj;

		if (gcmContractDetailsSid == gcmContractDetailsCacheModel.gcmContractDetailsSid) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, gcmContractDetailsSid);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(41);

		sb.append("{paymentMethod=");
		sb.append(paymentMethod);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", endDate=");
		sb.append(endDate);
		sb.append(", paymentFrequency=");
		sb.append(paymentFrequency);
		sb.append(", gcmContractDetailsSid=");
		sb.append(gcmContractDetailsSid);
		sb.append(", componentId=");
		sb.append(componentId);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", componentName=");
		sb.append(componentName);
		sb.append(", rsCalendar=");
		sb.append(rsCalendar);
		sb.append(", fileName=");
		sb.append(fileName);
		sb.append(", startDate=");
		sb.append(startDate);
		sb.append(", planLevel=");
		sb.append(planLevel);
		sb.append(", createdDate=");
		sb.append(createdDate);
		sb.append(", createdBy=");
		sb.append(createdBy);
		sb.append(", componentNo=");
		sb.append(componentNo);
		sb.append(", programType=");
		sb.append(programType);
		sb.append(", sessionId=");
		sb.append(sessionId);
		sb.append(", modifiedBy=");
		sb.append(modifiedBy);
		sb.append(", componentStatus=");
		sb.append(componentStatus);
		sb.append(", componentType=");
		sb.append(componentType);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public GcmContractDetails toEntityModel() {
		GcmContractDetailsImpl gcmContractDetailsImpl = new GcmContractDetailsImpl();

		if (paymentMethod == null) {
			gcmContractDetailsImpl.setPaymentMethod(StringPool.BLANK);
		}
		else {
			gcmContractDetailsImpl.setPaymentMethod(paymentMethod);
		}

		gcmContractDetailsImpl.setUserId(userId);

		if (endDate == Long.MIN_VALUE) {
			gcmContractDetailsImpl.setEndDate(null);
		}
		else {
			gcmContractDetailsImpl.setEndDate(new Date(endDate));
		}

		if (paymentFrequency == null) {
			gcmContractDetailsImpl.setPaymentFrequency(StringPool.BLANK);
		}
		else {
			gcmContractDetailsImpl.setPaymentFrequency(paymentFrequency);
		}

		gcmContractDetailsImpl.setGcmContractDetailsSid(gcmContractDetailsSid);

		if (componentId == null) {
			gcmContractDetailsImpl.setComponentId(StringPool.BLANK);
		}
		else {
			gcmContractDetailsImpl.setComponentId(componentId);
		}

		if (modifiedDate == Long.MIN_VALUE) {
			gcmContractDetailsImpl.setModifiedDate(null);
		}
		else {
			gcmContractDetailsImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (componentName == null) {
			gcmContractDetailsImpl.setComponentName(StringPool.BLANK);
		}
		else {
			gcmContractDetailsImpl.setComponentName(componentName);
		}

		if (rsCalendar == null) {
			gcmContractDetailsImpl.setRsCalendar(StringPool.BLANK);
		}
		else {
			gcmContractDetailsImpl.setRsCalendar(rsCalendar);
		}

		if (fileName == null) {
			gcmContractDetailsImpl.setFileName(StringPool.BLANK);
		}
		else {
			gcmContractDetailsImpl.setFileName(fileName);
		}

		if (startDate == Long.MIN_VALUE) {
			gcmContractDetailsImpl.setStartDate(null);
		}
		else {
			gcmContractDetailsImpl.setStartDate(new Date(startDate));
		}

		if (planLevel == null) {
			gcmContractDetailsImpl.setPlanLevel(StringPool.BLANK);
		}
		else {
			gcmContractDetailsImpl.setPlanLevel(planLevel);
		}

		if (createdDate == Long.MIN_VALUE) {
			gcmContractDetailsImpl.setCreatedDate(null);
		}
		else {
			gcmContractDetailsImpl.setCreatedDate(new Date(createdDate));
		}

		gcmContractDetailsImpl.setCreatedBy(createdBy);

		if (componentNo == null) {
			gcmContractDetailsImpl.setComponentNo(StringPool.BLANK);
		}
		else {
			gcmContractDetailsImpl.setComponentNo(componentNo);
		}

		if (programType == null) {
			gcmContractDetailsImpl.setProgramType(StringPool.BLANK);
		}
		else {
			gcmContractDetailsImpl.setProgramType(programType);
		}

		if (sessionId == null) {
			gcmContractDetailsImpl.setSessionId(StringPool.BLANK);
		}
		else {
			gcmContractDetailsImpl.setSessionId(sessionId);
		}

		gcmContractDetailsImpl.setModifiedBy(modifiedBy);

		if (componentStatus == null) {
			gcmContractDetailsImpl.setComponentStatus(StringPool.BLANK);
		}
		else {
			gcmContractDetailsImpl.setComponentStatus(componentStatus);
		}

		if (componentType == null) {
			gcmContractDetailsImpl.setComponentType(StringPool.BLANK);
		}
		else {
			gcmContractDetailsImpl.setComponentType(componentType);
		}

		gcmContractDetailsImpl.resetOriginalValues();

		return gcmContractDetailsImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		paymentMethod = objectInput.readUTF();

		userId = objectInput.readInt();
		endDate = objectInput.readLong();
		paymentFrequency = objectInput.readUTF();

		gcmContractDetailsSid = objectInput.readInt();
		componentId = objectInput.readUTF();
		modifiedDate = objectInput.readLong();
		componentName = objectInput.readUTF();
		rsCalendar = objectInput.readUTF();
		fileName = objectInput.readUTF();
		startDate = objectInput.readLong();
		planLevel = objectInput.readUTF();
		createdDate = objectInput.readLong();

		createdBy = objectInput.readInt();
		componentNo = objectInput.readUTF();
		programType = objectInput.readUTF();
		sessionId = objectInput.readUTF();

		modifiedBy = objectInput.readInt();
		componentStatus = objectInput.readUTF();
		componentType = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		if (paymentMethod == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(paymentMethod);
		}

		objectOutput.writeInt(userId);
		objectOutput.writeLong(endDate);

		if (paymentFrequency == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(paymentFrequency);
		}

		objectOutput.writeInt(gcmContractDetailsSid);

		if (componentId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(componentId);
		}

		objectOutput.writeLong(modifiedDate);

		if (componentName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(componentName);
		}

		if (rsCalendar == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(rsCalendar);
		}

		if (fileName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(fileName);
		}

		objectOutput.writeLong(startDate);

		if (planLevel == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(planLevel);
		}

		objectOutput.writeLong(createdDate);

		objectOutput.writeInt(createdBy);

		if (componentNo == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(componentNo);
		}

		if (programType == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(programType);
		}

		if (sessionId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(sessionId);
		}

		objectOutput.writeInt(modifiedBy);

		if (componentStatus == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(componentStatus);
		}

		if (componentType == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(componentType);
		}
	}

	public String paymentMethod;
	public int userId;
	public long endDate;
	public String paymentFrequency;
	public int gcmContractDetailsSid;
	public String componentId;
	public long modifiedDate;
	public String componentName;
	public String rsCalendar;
	public String fileName;
	public long startDate;
	public String planLevel;
	public long createdDate;
	public int createdBy;
	public String componentNo;
	public String programType;
	public String sessionId;
	public int modifiedBy;
	public String componentStatus;
	public String componentType;
}