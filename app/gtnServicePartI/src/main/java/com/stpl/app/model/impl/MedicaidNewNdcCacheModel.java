package com.stpl.app.model.impl;

import com.stpl.app.model.MedicaidNewNdc;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing MedicaidNewNdc in entity cache.
 *
 * @author
 * @see MedicaidNewNdc
 * @generated
 */
public class MedicaidNewNdcCacheModel implements CacheModel<MedicaidNewNdc>,
    Externalizable {
    public double forecastAmp;
    public double forecastBestprice;
    public double baseYearCpi;
    public String ndc9;
    public double wacPrice;
    public double baseYearAmp;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(13);

        sb.append("{forecastAmp=");
        sb.append(forecastAmp);
        sb.append(", forecastBestprice=");
        sb.append(forecastBestprice);
        sb.append(", baseYearCpi=");
        sb.append(baseYearCpi);
        sb.append(", ndc9=");
        sb.append(ndc9);
        sb.append(", wacPrice=");
        sb.append(wacPrice);
        sb.append(", baseYearAmp=");
        sb.append(baseYearAmp);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public MedicaidNewNdc toEntityModel() {
        MedicaidNewNdcImpl medicaidNewNdcImpl = new MedicaidNewNdcImpl();

        medicaidNewNdcImpl.setForecastAmp(forecastAmp);
        medicaidNewNdcImpl.setForecastBestprice(forecastBestprice);
        medicaidNewNdcImpl.setBaseYearCpi(baseYearCpi);

        if (ndc9 == null) {
            medicaidNewNdcImpl.setNdc9(StringPool.BLANK);
        } else {
            medicaidNewNdcImpl.setNdc9(ndc9);
        }

        medicaidNewNdcImpl.setWacPrice(wacPrice);
        medicaidNewNdcImpl.setBaseYearAmp(baseYearAmp);

        medicaidNewNdcImpl.resetOriginalValues();

        return medicaidNewNdcImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        forecastAmp = objectInput.readDouble();
        forecastBestprice = objectInput.readDouble();
        baseYearCpi = objectInput.readDouble();
        ndc9 = objectInput.readUTF();
        wacPrice = objectInput.readDouble();
        baseYearAmp = objectInput.readDouble();
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

        objectOutput.writeDouble(wacPrice);
        objectOutput.writeDouble(baseYearAmp);
    }
}
