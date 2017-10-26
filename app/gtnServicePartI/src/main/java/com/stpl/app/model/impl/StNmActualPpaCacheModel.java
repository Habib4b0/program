package com.stpl.app.model.impl;

import com.stpl.app.model.StNmActualPpa;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing StNmActualPpa in entity cache.
 *
 * @author
 * @see StNmActualPpa
 * @generated
 */
public class StNmActualPpaCacheModel implements CacheModel<StNmActualPpa>,
    Externalizable {
    public long lastModifiedDate;
    public double actualRate;
    public int periodSid;
    public double actualProjDiscountDollar;
    public double actualProjectionSales;
    public int projectionDetailsSid;
    public int userId;
    public double actualProjectionRate;
    public int sessionId;
    public double actualProjDiscountUnits;
    public double actualDiscountDollar;
    public double actualDiscountUnits;
    public double actualSales;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(27);

        sb.append("{lastModifiedDate=");
        sb.append(lastModifiedDate);
        sb.append(", actualRate=");
        sb.append(actualRate);
        sb.append(", periodSid=");
        sb.append(periodSid);
        sb.append(", actualProjDiscountDollar=");
        sb.append(actualProjDiscountDollar);
        sb.append(", actualProjectionSales=");
        sb.append(actualProjectionSales);
        sb.append(", projectionDetailsSid=");
        sb.append(projectionDetailsSid);
        sb.append(", userId=");
        sb.append(userId);
        sb.append(", actualProjectionRate=");
        sb.append(actualProjectionRate);
        sb.append(", sessionId=");
        sb.append(sessionId);
        sb.append(", actualProjDiscountUnits=");
        sb.append(actualProjDiscountUnits);
        sb.append(", actualDiscountDollar=");
        sb.append(actualDiscountDollar);
        sb.append(", actualDiscountUnits=");
        sb.append(actualDiscountUnits);
        sb.append(", actualSales=");
        sb.append(actualSales);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public StNmActualPpa toEntityModel() {
        StNmActualPpaImpl stNmActualPpaImpl = new StNmActualPpaImpl();

        if (lastModifiedDate == Long.MIN_VALUE) {
            stNmActualPpaImpl.setLastModifiedDate(null);
        } else {
            stNmActualPpaImpl.setLastModifiedDate(new Date(lastModifiedDate));
        }

        stNmActualPpaImpl.setActualRate(actualRate);
        stNmActualPpaImpl.setPeriodSid(periodSid);
        stNmActualPpaImpl.setActualProjDiscountDollar(actualProjDiscountDollar);
        stNmActualPpaImpl.setActualProjectionSales(actualProjectionSales);
        stNmActualPpaImpl.setProjectionDetailsSid(projectionDetailsSid);
        stNmActualPpaImpl.setUserId(userId);
        stNmActualPpaImpl.setActualProjectionRate(actualProjectionRate);
        stNmActualPpaImpl.setSessionId(sessionId);
        stNmActualPpaImpl.setActualProjDiscountUnits(actualProjDiscountUnits);
        stNmActualPpaImpl.setActualDiscountDollar(actualDiscountDollar);
        stNmActualPpaImpl.setActualDiscountUnits(actualDiscountUnits);
        stNmActualPpaImpl.setActualSales(actualSales);

        stNmActualPpaImpl.resetOriginalValues();

        return stNmActualPpaImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        lastModifiedDate = objectInput.readLong();
        actualRate = objectInput.readDouble();
        periodSid = objectInput.readInt();
        actualProjDiscountDollar = objectInput.readDouble();
        actualProjectionSales = objectInput.readDouble();
        projectionDetailsSid = objectInput.readInt();
        userId = objectInput.readInt();
        actualProjectionRate = objectInput.readDouble();
        sessionId = objectInput.readInt();
        actualProjDiscountUnits = objectInput.readDouble();
        actualDiscountDollar = objectInput.readDouble();
        actualDiscountUnits = objectInput.readDouble();
        actualSales = objectInput.readDouble();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeLong(lastModifiedDate);
        objectOutput.writeDouble(actualRate);
        objectOutput.writeInt(periodSid);
        objectOutput.writeDouble(actualProjDiscountDollar);
        objectOutput.writeDouble(actualProjectionSales);
        objectOutput.writeInt(projectionDetailsSid);
        objectOutput.writeInt(userId);
        objectOutput.writeDouble(actualProjectionRate);
        objectOutput.writeInt(sessionId);
        objectOutput.writeDouble(actualProjDiscountUnits);
        objectOutput.writeDouble(actualDiscountDollar);
        objectOutput.writeDouble(actualDiscountUnits);
        objectOutput.writeDouble(actualSales);
    }
}
