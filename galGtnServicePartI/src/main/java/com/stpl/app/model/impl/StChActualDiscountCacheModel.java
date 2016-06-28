package com.stpl.app.model.impl;

import com.stpl.app.model.StChActualDiscount;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing StChActualDiscount in entity cache.
 *
 * @author
 * @see StChActualDiscount
 * @generated
 */
public class StChActualDiscountCacheModel implements CacheModel<StChActualDiscount>,
    Externalizable {
    public long lastModifiedDate;
    public double actualRate;
    public int periodSid;
    public int projectionDetailsSid;
    public int userId;
    public int sessionId;
    public int rsModelSid;
    public double actualSales;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(17);

        sb.append("{lastModifiedDate=");
        sb.append(lastModifiedDate);
        sb.append(", actualRate=");
        sb.append(actualRate);
        sb.append(", periodSid=");
        sb.append(periodSid);
        sb.append(", projectionDetailsSid=");
        sb.append(projectionDetailsSid);
        sb.append(", userId=");
        sb.append(userId);
        sb.append(", sessionId=");
        sb.append(sessionId);
        sb.append(", rsModelSid=");
        sb.append(rsModelSid);
        sb.append(", actualSales=");
        sb.append(actualSales);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public StChActualDiscount toEntityModel() {
        StChActualDiscountImpl stChActualDiscountImpl = new StChActualDiscountImpl();

        if (lastModifiedDate == Long.MIN_VALUE) {
            stChActualDiscountImpl.setLastModifiedDate(null);
        } else {
            stChActualDiscountImpl.setLastModifiedDate(new Date(
                    lastModifiedDate));
        }

        stChActualDiscountImpl.setActualRate(actualRate);
        stChActualDiscountImpl.setPeriodSid(periodSid);
        stChActualDiscountImpl.setProjectionDetailsSid(projectionDetailsSid);
        stChActualDiscountImpl.setUserId(userId);
        stChActualDiscountImpl.setSessionId(sessionId);
        stChActualDiscountImpl.setRsModelSid(rsModelSid);
        stChActualDiscountImpl.setActualSales(actualSales);

        stChActualDiscountImpl.resetOriginalValues();

        return stChActualDiscountImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        lastModifiedDate = objectInput.readLong();
        actualRate = objectInput.readDouble();
        periodSid = objectInput.readInt();
        projectionDetailsSid = objectInput.readInt();
        userId = objectInput.readInt();
        sessionId = objectInput.readInt();
        rsModelSid = objectInput.readInt();
        actualSales = objectInput.readDouble();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeLong(lastModifiedDate);
        objectOutput.writeDouble(actualRate);
        objectOutput.writeInt(periodSid);
        objectOutput.writeInt(projectionDetailsSid);
        objectOutput.writeInt(userId);
        objectOutput.writeInt(sessionId);
        objectOutput.writeInt(rsModelSid);
        objectOutput.writeDouble(actualSales);
    }
}
