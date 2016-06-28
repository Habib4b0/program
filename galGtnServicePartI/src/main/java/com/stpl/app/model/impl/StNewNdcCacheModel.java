package com.stpl.app.model.impl;

import com.stpl.app.model.StNewNdc;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing StNewNdc in entity cache.
 *
 * @author
 * @see StNewNdc
 * @generated
 */
public class StNewNdcCacheModel implements CacheModel<StNewNdc>, Externalizable {
    public double forecastAmp;
    public double forecastBestprice;
    public int naProjDetailsSid;
    public double baseYearCpi;
    public int userId;
    public long lastModifiedDate;
    public int itemMasterSid;
    public double wacPrice;
    public double baseYearAmp;
    public int sessionId;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(21);

        sb.append("{forecastAmp=");
        sb.append(forecastAmp);
        sb.append(", forecastBestprice=");
        sb.append(forecastBestprice);
        sb.append(", naProjDetailsSid=");
        sb.append(naProjDetailsSid);
        sb.append(", baseYearCpi=");
        sb.append(baseYearCpi);
        sb.append(", userId=");
        sb.append(userId);
        sb.append(", lastModifiedDate=");
        sb.append(lastModifiedDate);
        sb.append(", itemMasterSid=");
        sb.append(itemMasterSid);
        sb.append(", wacPrice=");
        sb.append(wacPrice);
        sb.append(", baseYearAmp=");
        sb.append(baseYearAmp);
        sb.append(", sessionId=");
        sb.append(sessionId);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public StNewNdc toEntityModel() {
        StNewNdcImpl stNewNdcImpl = new StNewNdcImpl();

        stNewNdcImpl.setForecastAmp(forecastAmp);
        stNewNdcImpl.setForecastBestprice(forecastBestprice);
        stNewNdcImpl.setNaProjDetailsSid(naProjDetailsSid);
        stNewNdcImpl.setBaseYearCpi(baseYearCpi);
        stNewNdcImpl.setUserId(userId);

        if (lastModifiedDate == Long.MIN_VALUE) {
            stNewNdcImpl.setLastModifiedDate(null);
        } else {
            stNewNdcImpl.setLastModifiedDate(new Date(lastModifiedDate));
        }

        stNewNdcImpl.setItemMasterSid(itemMasterSid);
        stNewNdcImpl.setWacPrice(wacPrice);
        stNewNdcImpl.setBaseYearAmp(baseYearAmp);
        stNewNdcImpl.setSessionId(sessionId);

        stNewNdcImpl.resetOriginalValues();

        return stNewNdcImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        forecastAmp = objectInput.readDouble();
        forecastBestprice = objectInput.readDouble();
        naProjDetailsSid = objectInput.readInt();
        baseYearCpi = objectInput.readDouble();
        userId = objectInput.readInt();
        lastModifiedDate = objectInput.readLong();
        itemMasterSid = objectInput.readInt();
        wacPrice = objectInput.readDouble();
        baseYearAmp = objectInput.readDouble();
        sessionId = objectInput.readInt();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeDouble(forecastAmp);
        objectOutput.writeDouble(forecastBestprice);
        objectOutput.writeInt(naProjDetailsSid);
        objectOutput.writeDouble(baseYearCpi);
        objectOutput.writeInt(userId);
        objectOutput.writeLong(lastModifiedDate);
        objectOutput.writeInt(itemMasterSid);
        objectOutput.writeDouble(wacPrice);
        objectOutput.writeDouble(baseYearAmp);
        objectOutput.writeInt(sessionId);
    }
}
