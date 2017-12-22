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

import com.stpl.app.parttwo.model.SlaCalendarMaster;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing SlaCalendarMaster in entity cache.
 *
 * @author
 * @see SlaCalendarMaster
 * @generated
 */
@ProviderType
public class SlaCalendarMasterCacheModel implements CacheModel<SlaCalendarMaster>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof SlaCalendarMasterCacheModel)) {
			return false;
		}

		SlaCalendarMasterCacheModel slaCalendarMasterCacheModel = (SlaCalendarMasterCacheModel)obj;

		if (slaCalendarMasterSid == slaCalendarMasterCacheModel.slaCalendarMasterSid) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, slaCalendarMasterSid);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(17);

		sb.append("{createdBy=");
		sb.append(createdBy);
		sb.append(", modifiedBy=");
		sb.append(modifiedBy);
		sb.append(", slaCalendarMasterSid=");
		sb.append(slaCalendarMasterSid);
		sb.append(", createdDate=");
		sb.append(createdDate);
		sb.append(", defaultHolidays=");
		sb.append(defaultHolidays);
		sb.append(", calendarName=");
		sb.append(calendarName);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", inboundStatus=");
		sb.append(inboundStatus);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public SlaCalendarMaster toEntityModel() {
		SlaCalendarMasterImpl slaCalendarMasterImpl = new SlaCalendarMasterImpl();

		slaCalendarMasterImpl.setCreatedBy(createdBy);
		slaCalendarMasterImpl.setModifiedBy(modifiedBy);
		slaCalendarMasterImpl.setSlaCalendarMasterSid(slaCalendarMasterSid);

		if (createdDate == Long.MIN_VALUE) {
			slaCalendarMasterImpl.setCreatedDate(null);
		}
		else {
			slaCalendarMasterImpl.setCreatedDate(new Date(createdDate));
		}

		slaCalendarMasterImpl.setDefaultHolidays(defaultHolidays);

		if (calendarName == null) {
			slaCalendarMasterImpl.setCalendarName(StringPool.BLANK);
		}
		else {
			slaCalendarMasterImpl.setCalendarName(calendarName);
		}

		if (modifiedDate == Long.MIN_VALUE) {
			slaCalendarMasterImpl.setModifiedDate(null);
		}
		else {
			slaCalendarMasterImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (inboundStatus == null) {
			slaCalendarMasterImpl.setInboundStatus(StringPool.BLANK);
		}
		else {
			slaCalendarMasterImpl.setInboundStatus(inboundStatus);
		}

		slaCalendarMasterImpl.resetOriginalValues();

		return slaCalendarMasterImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		createdBy = objectInput.readInt();

		modifiedBy = objectInput.readInt();

		slaCalendarMasterSid = objectInput.readInt();
		createdDate = objectInput.readLong();

		defaultHolidays = objectInput.readBoolean();
		calendarName = objectInput.readUTF();
		modifiedDate = objectInput.readLong();
		inboundStatus = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeInt(createdBy);

		objectOutput.writeInt(modifiedBy);

		objectOutput.writeInt(slaCalendarMasterSid);
		objectOutput.writeLong(createdDate);

		objectOutput.writeBoolean(defaultHolidays);

		if (calendarName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(calendarName);
		}

		objectOutput.writeLong(modifiedDate);

		if (inboundStatus == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(inboundStatus);
		}
	}

	public int createdBy;
	public int modifiedBy;
	public int slaCalendarMasterSid;
	public long createdDate;
	public boolean defaultHolidays;
	public String calendarName;
	public long modifiedDate;
	public String inboundStatus;
}