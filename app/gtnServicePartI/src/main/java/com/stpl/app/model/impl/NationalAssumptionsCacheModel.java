package com.stpl.app.model.impl;

import com.stpl.app.model.NationalAssumptions;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing NationalAssumptions in entity cache.
 *
 * @author
 * @see NationalAssumptions
 * @generated
 */
public class NationalAssumptionsCacheModel implements CacheModel<NationalAssumptions>,
    Externalizable {
    public String baselinePeriod;
    public String frequency;
    public String startPeriod;
    public String forecastMethodology;
    public String priceType;
    public String endPeriod;
    public String priceBasis;
    public int naProjMasterSid;
    public String rollingPeriod;
    public String baselineMethodology;
    public double growthRate;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(23);

        sb.append("{baselinePeriod=");
        sb.append(baselinePeriod);
        sb.append(", frequency=");
        sb.append(frequency);
        sb.append(", startPeriod=");
        sb.append(startPeriod);
        sb.append(", forecastMethodology=");
        sb.append(forecastMethodology);
        sb.append(", priceType=");
        sb.append(priceType);
        sb.append(", endPeriod=");
        sb.append(endPeriod);
        sb.append(", priceBasis=");
        sb.append(priceBasis);
        sb.append(", naProjMasterSid=");
        sb.append(naProjMasterSid);
        sb.append(", rollingPeriod=");
        sb.append(rollingPeriod);
        sb.append(", baselineMethodology=");
        sb.append(baselineMethodology);
        sb.append(", growthRate=");
        sb.append(growthRate);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public NationalAssumptions toEntityModel() {
        NationalAssumptionsImpl nationalAssumptionsImpl = new NationalAssumptionsImpl();

        if (baselinePeriod == null) {
            nationalAssumptionsImpl.setBaselinePeriod(StringPool.BLANK);
        } else {
            nationalAssumptionsImpl.setBaselinePeriod(baselinePeriod);
        }

        if (frequency == null) {
            nationalAssumptionsImpl.setFrequency(StringPool.BLANK);
        } else {
            nationalAssumptionsImpl.setFrequency(frequency);
        }

        if (startPeriod == null) {
            nationalAssumptionsImpl.setStartPeriod(StringPool.BLANK);
        } else {
            nationalAssumptionsImpl.setStartPeriod(startPeriod);
        }

        if (forecastMethodology == null) {
            nationalAssumptionsImpl.setForecastMethodology(StringPool.BLANK);
        } else {
            nationalAssumptionsImpl.setForecastMethodology(forecastMethodology);
        }

        if (priceType == null) {
            nationalAssumptionsImpl.setPriceType(StringPool.BLANK);
        } else {
            nationalAssumptionsImpl.setPriceType(priceType);
        }

        if (endPeriod == null) {
            nationalAssumptionsImpl.setEndPeriod(StringPool.BLANK);
        } else {
            nationalAssumptionsImpl.setEndPeriod(endPeriod);
        }

        if (priceBasis == null) {
            nationalAssumptionsImpl.setPriceBasis(StringPool.BLANK);
        } else {
            nationalAssumptionsImpl.setPriceBasis(priceBasis);
        }

        nationalAssumptionsImpl.setNaProjMasterSid(naProjMasterSid);

        if (rollingPeriod == null) {
            nationalAssumptionsImpl.setRollingPeriod(StringPool.BLANK);
        } else {
            nationalAssumptionsImpl.setRollingPeriod(rollingPeriod);
        }

        if (baselineMethodology == null) {
            nationalAssumptionsImpl.setBaselineMethodology(StringPool.BLANK);
        } else {
            nationalAssumptionsImpl.setBaselineMethodology(baselineMethodology);
        }

        nationalAssumptionsImpl.setGrowthRate(growthRate);

        nationalAssumptionsImpl.resetOriginalValues();

        return nationalAssumptionsImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        baselinePeriod = objectInput.readUTF();
        frequency = objectInput.readUTF();
        startPeriod = objectInput.readUTF();
        forecastMethodology = objectInput.readUTF();
        priceType = objectInput.readUTF();
        endPeriod = objectInput.readUTF();
        priceBasis = objectInput.readUTF();
        naProjMasterSid = objectInput.readInt();
        rollingPeriod = objectInput.readUTF();
        baselineMethodology = objectInput.readUTF();
        growthRate = objectInput.readDouble();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        if (baselinePeriod == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(baselinePeriod);
        }

        if (frequency == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(frequency);
        }

        if (startPeriod == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(startPeriod);
        }

        if (forecastMethodology == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(forecastMethodology);
        }

        if (priceType == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(priceType);
        }

        if (endPeriod == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(endPeriod);
        }

        if (priceBasis == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(priceBasis);
        }

        objectOutput.writeInt(naProjMasterSid);

        if (rollingPeriod == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(rollingPeriod);
        }

        if (baselineMethodology == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(baselineMethodology);
        }

        objectOutput.writeDouble(growthRate);
    }
}
