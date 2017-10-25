package com.stpl.app.model.impl;

import com.stpl.app.model.StMedicaidNewNdc;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing StMedicaidNewNdc in entity cache.
 *
 * @author
 * @see StMedicaidNewNdc
 * @generated
 */
public class StMedicaidNewNdcCacheModel implements CacheModel<StMedicaidNewNdc>,
    Externalizable {
    public double forecastAmp;
    public double forecastBestprice;
    public double baseYearCpi;
    public String ndc9;
    public int userId;
    public long lastModifiedDate;
    public double wacPrice;
    public double baseYearAmp;
    public int sessionId;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(19);

        sb.append("{forecastAmp=");
        sb.append(forecastAmp);
        sb.append(", forecastBestprice=");
        sb.append(forecastBestprice);
        sb.append(", baseYearCpi=");
        sb.append(baseYearCpi);
        sb.append(", ndc9=");
        sb.append(ndc9);
        sb.append(", userId=");
        sb.append(userId);
        sb.append(", lastModifiedDate=");
        sb.append(lastModifiedDate);
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
    public StMedicaidNewNdc toEntityModel() {
        StMedicaidNewNdcImpl stMedicaidNewNdcImpl = new StMedicaidNewNdcImpl();

        stMedicaidNewNdcImpl.setForecastAmp(forecastAmp);
        stMedicaidNewNdcImpl.setForecastBestprice(forecastBestprice);
        stMedicaidNewNdcImpl.setBaseYearCpi(baseYearCpi);

        if (ndc9 == null) {
            stMedicaidNewNdcImpl.setNdc9(StringPool.BLANK);
        } else {
            stMedicaidNewNdcImpl.setNdc9(ndc9);
        }

        stMedicaidNewNdcImpl.setUserId(userId);

        if (lastModifiedDate == Long.MIN_VALUE) {
            stMedicaidNewNdcImpl.setLastModifiedDate(null);
        } else {
            stMedicaidNewNdcImpl.setLastModifiedDate(new Date(lastModifiedDate));
        }

        stMedicaidNewNdcImpl.setWacPrice(wacPrice);
        stMedicaidNewNdcImpl.setBaseYearAmp(baseYearAmp);
        stMedicaidNewNdcImpl.setSessionId(sessionId);

        stMedicaidNewNdcImpl.resetOriginalValues();

        return stMedicaidNewNdcImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        forecastAmp = objectInput.readDouble();
        forecastBestprice = objectInput.readDouble();
        baseYearCpi = objectInput.readDouble();
        ndc9 = objectInput.readUTF();
        userId = objectInput.readInt();
        lastModifiedDate = objectInput.readLong();
        wacPrice = objectInput.readDouble();
        baseYearAmp = objectInput.readDouble();
        sessionId = objectInput.readInt();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeDouble(forecastAmp);
        objectOutput.writeDouble(forecastBestprice);
        objectOutput.writeDouble(baseYearCpi);

        if (ndc9 == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(ndc9);
        }

        objectOutput.writeInt(userId);
        objectOutput.writeLong(lastModifiedDate);
        objectOutput.writeDouble(wacPrice);
        objectOutput.writeDouble(baseYearAmp);
        objectOutput.writeInt(sessionId);
    }
}
