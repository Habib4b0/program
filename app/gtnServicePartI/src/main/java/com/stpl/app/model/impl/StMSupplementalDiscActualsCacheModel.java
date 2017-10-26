package com.stpl.app.model.impl;

import com.stpl.app.model.StMSupplementalDiscActuals;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing StMSupplementalDiscActuals in entity cache.
 *
 * @author
 * @see StMSupplementalDiscActuals
 * @generated
 */
public class StMSupplementalDiscActualsCacheModel implements CacheModel<StMSupplementalDiscActuals>,
    Externalizable {
    public double actualSales;
    public int periodSid;
    public double actualRate;
    public int userId;
    public long lastModifiedDate;
    public double actualProjectionSales;
    public double actualProjectionRate;
    public int projectionDetailsSid;
    public int sessionId;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(19);

        sb.append("{actualSales=");
        sb.append(actualSales);
        sb.append(", periodSid=");
        sb.append(periodSid);
        sb.append(", actualRate=");
        sb.append(actualRate);
        sb.append(", userId=");
        sb.append(userId);
        sb.append(", lastModifiedDate=");
        sb.append(lastModifiedDate);
        sb.append(", actualProjectionSales=");
        sb.append(actualProjectionSales);
        sb.append(", actualProjectionRate=");
        sb.append(actualProjectionRate);
        sb.append(", projectionDetailsSid=");
        sb.append(projectionDetailsSid);
        sb.append(", sessionId=");
        sb.append(sessionId);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public StMSupplementalDiscActuals toEntityModel() {
        StMSupplementalDiscActualsImpl stMSupplementalDiscActualsImpl = new StMSupplementalDiscActualsImpl();

        stMSupplementalDiscActualsImpl.setActualSales(actualSales);
        stMSupplementalDiscActualsImpl.setPeriodSid(periodSid);
        stMSupplementalDiscActualsImpl.setActualRate(actualRate);
        stMSupplementalDiscActualsImpl.setUserId(userId);

        if (lastModifiedDate == Long.MIN_VALUE) {
            stMSupplementalDiscActualsImpl.setLastModifiedDate(null);
        } else {
            stMSupplementalDiscActualsImpl.setLastModifiedDate(new Date(
                    lastModifiedDate));
        }

        stMSupplementalDiscActualsImpl.setActualProjectionSales(actualProjectionSales);
        stMSupplementalDiscActualsImpl.setActualProjectionRate(actualProjectionRate);
        stMSupplementalDiscActualsImpl.setProjectionDetailsSid(projectionDetailsSid);
        stMSupplementalDiscActualsImpl.setSessionId(sessionId);

        stMSupplementalDiscActualsImpl.resetOriginalValues();

        return stMSupplementalDiscActualsImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        actualSales = objectInput.readDouble();
        periodSid = objectInput.readInt();
        actualRate = objectInput.readDouble();
        userId = objectInput.readInt();
        lastModifiedDate = objectInput.readLong();
        actualProjectionSales = objectInput.readDouble();
        actualProjectionRate = objectInput.readDouble();
        projectionDetailsSid = objectInput.readInt();
        sessionId = objectInput.readInt();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeDouble(actualSales);
        objectOutput.writeInt(periodSid);
        objectOutput.writeDouble(actualRate);
        objectOutput.writeInt(userId);
        objectOutput.writeLong(lastModifiedDate);
        objectOutput.writeDouble(actualProjectionSales);
        objectOutput.writeDouble(actualProjectionRate);
        objectOutput.writeInt(projectionDetailsSid);
        objectOutput.writeInt(sessionId);
    }
}
