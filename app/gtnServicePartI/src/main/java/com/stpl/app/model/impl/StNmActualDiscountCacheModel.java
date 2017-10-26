package com.stpl.app.model.impl;

import com.stpl.app.model.StNmActualDiscount;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing StNmActualDiscount in entity cache.
 *
 * @author
 * @see StNmActualDiscount
 * @generated
 */
public class StNmActualDiscountCacheModel implements CacheModel<StNmActualDiscount>,
    Externalizable {
    public double actualSales;
    public int periodSid;
    public double actualRate;
    public int userId;
    public long lastModifiedDate;
    public double actualProjectionSales;
    public double actualProjectionRate;
    public int projectionDetailsSid;
    public int rsModelSid;
    public int sessionId;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(21);

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
        sb.append(", rsModelSid=");
        sb.append(rsModelSid);
        sb.append(", sessionId=");
        sb.append(sessionId);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public StNmActualDiscount toEntityModel() {
        StNmActualDiscountImpl stNmActualDiscountImpl = new StNmActualDiscountImpl();

        stNmActualDiscountImpl.setActualSales(actualSales);
        stNmActualDiscountImpl.setPeriodSid(periodSid);
        stNmActualDiscountImpl.setActualRate(actualRate);
        stNmActualDiscountImpl.setUserId(userId);

        if (lastModifiedDate == Long.MIN_VALUE) {
            stNmActualDiscountImpl.setLastModifiedDate(null);
        } else {
            stNmActualDiscountImpl.setLastModifiedDate(new Date(
                    lastModifiedDate));
        }

        stNmActualDiscountImpl.setActualProjectionSales(actualProjectionSales);
        stNmActualDiscountImpl.setActualProjectionRate(actualProjectionRate);
        stNmActualDiscountImpl.setProjectionDetailsSid(projectionDetailsSid);
        stNmActualDiscountImpl.setRsModelSid(rsModelSid);
        stNmActualDiscountImpl.setSessionId(sessionId);

        stNmActualDiscountImpl.resetOriginalValues();

        return stNmActualDiscountImpl;
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
        rsModelSid = objectInput.readInt();
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
        objectOutput.writeInt(rsModelSid);
        objectOutput.writeInt(sessionId);
    }
}
