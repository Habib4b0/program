package com.stpl.app.model.impl;

import com.stpl.app.model.Period;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Period in entity cache.
 *
 * @author
 * @see Period
 * @generated
 */
public class PeriodCacheModel implements CacheModel<Period>, Externalizable {
    public int periodSid;
    public long periodDate;
    public int quarter;
    public int year;
    public int semiAnnual;
    public int month;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(13);

        sb.append("{periodSid=");
        sb.append(periodSid);
        sb.append(", periodDate=");
        sb.append(periodDate);
        sb.append(", quarter=");
        sb.append(quarter);
        sb.append(", year=");
        sb.append(year);
        sb.append(", semiAnnual=");
        sb.append(semiAnnual);
        sb.append(", month=");
        sb.append(month);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public Period toEntityModel() {
        PeriodImpl periodImpl = new PeriodImpl();

        periodImpl.setPeriodSid(periodSid);

        if (periodDate == Long.MIN_VALUE) {
            periodImpl.setPeriodDate(null);
        } else {
            periodImpl.setPeriodDate(new Date(periodDate));
        }

        periodImpl.setQuarter(quarter);
        periodImpl.setYear(year);
        periodImpl.setSemiAnnual(semiAnnual);
        periodImpl.setMonth(month);

        periodImpl.resetOriginalValues();

        return periodImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        periodSid = objectInput.readInt();
        periodDate = objectInput.readLong();
        quarter = objectInput.readInt();
        year = objectInput.readInt();
        semiAnnual = objectInput.readInt();
        month = objectInput.readInt();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeInt(periodSid);
        objectOutput.writeLong(periodDate);
        objectOutput.writeInt(quarter);
        objectOutput.writeInt(year);
        objectOutput.writeInt(semiAnnual);
        objectOutput.writeInt(month);
    }
}
