package com.stpl.app.parttwo.model.impl;

import com.stpl.app.parttwo.model.SlaCalendarDetails;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing SlaCalendarDetails in entity cache.
 *
 * @author
 * @see SlaCalendarDetails
 * @generated
 */
public class SlaCalendarDetailsCacheModel implements CacheModel<SlaCalendarDetails>,
    Externalizable {
    public long createdDate;
    public int createdBy;
    public int slaCalendarMasterSid;
    public String holidayYear;
    public int slaCalendarDetailsSid;
    public int modifiedBy;
    public String inboundStatus;
    public String holidayDay;
    public long modifiedDate;
    public long holidayCombined;
    public String holidayMonth;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(23);

        sb.append("{createdDate=");
        sb.append(createdDate);
        sb.append(", createdBy=");
        sb.append(createdBy);
        sb.append(", slaCalendarMasterSid=");
        sb.append(slaCalendarMasterSid);
        sb.append(", holidayYear=");
        sb.append(holidayYear);
        sb.append(", slaCalendarDetailsSid=");
        sb.append(slaCalendarDetailsSid);
        sb.append(", modifiedBy=");
        sb.append(modifiedBy);
        sb.append(", inboundStatus=");
        sb.append(inboundStatus);
        sb.append(", holidayDay=");
        sb.append(holidayDay);
        sb.append(", modifiedDate=");
        sb.append(modifiedDate);
        sb.append(", holidayCombined=");
        sb.append(holidayCombined);
        sb.append(", holidayMonth=");
        sb.append(holidayMonth);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public SlaCalendarDetails toEntityModel() {
        SlaCalendarDetailsImpl slaCalendarDetailsImpl = new SlaCalendarDetailsImpl();

        if (createdDate == Long.MIN_VALUE) {
            slaCalendarDetailsImpl.setCreatedDate(null);
        } else {
            slaCalendarDetailsImpl.setCreatedDate(new Date(createdDate));
        }

        slaCalendarDetailsImpl.setCreatedBy(createdBy);
        slaCalendarDetailsImpl.setSlaCalendarMasterSid(slaCalendarMasterSid);

        if (holidayYear == null) {
            slaCalendarDetailsImpl.setHolidayYear(StringPool.BLANK);
        } else {
            slaCalendarDetailsImpl.setHolidayYear(holidayYear);
        }

        slaCalendarDetailsImpl.setSlaCalendarDetailsSid(slaCalendarDetailsSid);
        slaCalendarDetailsImpl.setModifiedBy(modifiedBy);

        if (inboundStatus == null) {
            slaCalendarDetailsImpl.setInboundStatus(StringPool.BLANK);
        } else {
            slaCalendarDetailsImpl.setInboundStatus(inboundStatus);
        }

        if (holidayDay == null) {
            slaCalendarDetailsImpl.setHolidayDay(StringPool.BLANK);
        } else {
            slaCalendarDetailsImpl.setHolidayDay(holidayDay);
        }

        if (modifiedDate == Long.MIN_VALUE) {
            slaCalendarDetailsImpl.setModifiedDate(null);
        } else {
            slaCalendarDetailsImpl.setModifiedDate(new Date(modifiedDate));
        }

        if (holidayCombined == Long.MIN_VALUE) {
            slaCalendarDetailsImpl.setHolidayCombined(null);
        } else {
            slaCalendarDetailsImpl.setHolidayCombined(new Date(holidayCombined));
        }

        if (holidayMonth == null) {
            slaCalendarDetailsImpl.setHolidayMonth(StringPool.BLANK);
        } else {
            slaCalendarDetailsImpl.setHolidayMonth(holidayMonth);
        }

        slaCalendarDetailsImpl.resetOriginalValues();

        return slaCalendarDetailsImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        createdDate = objectInput.readLong();
        createdBy = objectInput.readInt();
        slaCalendarMasterSid = objectInput.readInt();
        holidayYear = objectInput.readUTF();
        slaCalendarDetailsSid = objectInput.readInt();
        modifiedBy = objectInput.readInt();
        inboundStatus = objectInput.readUTF();
        holidayDay = objectInput.readUTF();
        modifiedDate = objectInput.readLong();
        holidayCombined = objectInput.readLong();
        holidayMonth = objectInput.readUTF();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeLong(createdDate);
        objectOutput.writeInt(createdBy);
        objectOutput.writeInt(slaCalendarMasterSid);

        if (holidayYear == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(holidayYear);
        }

        objectOutput.writeInt(slaCalendarDetailsSid);
        objectOutput.writeInt(modifiedBy);

        if (inboundStatus == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(inboundStatus);
        }

        if (holidayDay == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(holidayDay);
        }

        objectOutput.writeLong(modifiedDate);
        objectOutput.writeLong(holidayCombined);

        if (holidayMonth == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(holidayMonth);
        }
    }
}
