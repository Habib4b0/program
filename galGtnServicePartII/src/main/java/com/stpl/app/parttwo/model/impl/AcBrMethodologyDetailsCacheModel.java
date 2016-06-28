package com.stpl.app.parttwo.model.impl;

import com.stpl.app.parttwo.model.AcBrMethodologyDetails;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing AcBrMethodologyDetails in entity cache.
 *
 * @author
 * @see AcBrMethodologyDetails
 * @generated
 */
public class AcBrMethodologyDetailsCacheModel implements CacheModel<AcBrMethodologyDetails>,
    Externalizable {
    public double salesGrowthRate;
    public long methodologyStartDate;
    public String frequency;
    public boolean calculationFlag;
    public double provisionGrowthRate;
    public int salesBasis;
    public int acBrMethodologyDetailsSid;
    public int accClosureMasterSid;
    public long methodologyEndDate;
    public double methodologyValue;
    public double dampingFactor;
    public String methodologyName;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(25);

        sb.append("{salesGrowthRate=");
        sb.append(salesGrowthRate);
        sb.append(", methodologyStartDate=");
        sb.append(methodologyStartDate);
        sb.append(", frequency=");
        sb.append(frequency);
        sb.append(", calculationFlag=");
        sb.append(calculationFlag);
        sb.append(", provisionGrowthRate=");
        sb.append(provisionGrowthRate);
        sb.append(", salesBasis=");
        sb.append(salesBasis);
        sb.append(", acBrMethodologyDetailsSid=");
        sb.append(acBrMethodologyDetailsSid);
        sb.append(", accClosureMasterSid=");
        sb.append(accClosureMasterSid);
        sb.append(", methodologyEndDate=");
        sb.append(methodologyEndDate);
        sb.append(", methodologyValue=");
        sb.append(methodologyValue);
        sb.append(", dampingFactor=");
        sb.append(dampingFactor);
        sb.append(", methodologyName=");
        sb.append(methodologyName);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public AcBrMethodologyDetails toEntityModel() {
        AcBrMethodologyDetailsImpl acBrMethodologyDetailsImpl = new AcBrMethodologyDetailsImpl();

        acBrMethodologyDetailsImpl.setSalesGrowthRate(salesGrowthRate);

        if (methodologyStartDate == Long.MIN_VALUE) {
            acBrMethodologyDetailsImpl.setMethodologyStartDate(null);
        } else {
            acBrMethodologyDetailsImpl.setMethodologyStartDate(new Date(
                    methodologyStartDate));
        }

        if (frequency == null) {
            acBrMethodologyDetailsImpl.setFrequency(StringPool.BLANK);
        } else {
            acBrMethodologyDetailsImpl.setFrequency(frequency);
        }

        acBrMethodologyDetailsImpl.setCalculationFlag(calculationFlag);
        acBrMethodologyDetailsImpl.setProvisionGrowthRate(provisionGrowthRate);
        acBrMethodologyDetailsImpl.setSalesBasis(salesBasis);
        acBrMethodologyDetailsImpl.setAcBrMethodologyDetailsSid(acBrMethodologyDetailsSid);
        acBrMethodologyDetailsImpl.setAccClosureMasterSid(accClosureMasterSid);

        if (methodologyEndDate == Long.MIN_VALUE) {
            acBrMethodologyDetailsImpl.setMethodologyEndDate(null);
        } else {
            acBrMethodologyDetailsImpl.setMethodologyEndDate(new Date(
                    methodologyEndDate));
        }

        acBrMethodologyDetailsImpl.setMethodologyValue(methodologyValue);
        acBrMethodologyDetailsImpl.setDampingFactor(dampingFactor);

        if (methodologyName == null) {
            acBrMethodologyDetailsImpl.setMethodologyName(StringPool.BLANK);
        } else {
            acBrMethodologyDetailsImpl.setMethodologyName(methodologyName);
        }

        acBrMethodologyDetailsImpl.resetOriginalValues();

        return acBrMethodologyDetailsImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        salesGrowthRate = objectInput.readDouble();
        methodologyStartDate = objectInput.readLong();
        frequency = objectInput.readUTF();
        calculationFlag = objectInput.readBoolean();
        provisionGrowthRate = objectInput.readDouble();
        salesBasis = objectInput.readInt();
        acBrMethodologyDetailsSid = objectInput.readInt();
        accClosureMasterSid = objectInput.readInt();
        methodologyEndDate = objectInput.readLong();
        methodologyValue = objectInput.readDouble();
        dampingFactor = objectInput.readDouble();
        methodologyName = objectInput.readUTF();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeDouble(salesGrowthRate);
        objectOutput.writeLong(methodologyStartDate);

        if (frequency == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(frequency);
        }

        objectOutput.writeBoolean(calculationFlag);
        objectOutput.writeDouble(provisionGrowthRate);
        objectOutput.writeInt(salesBasis);
        objectOutput.writeInt(acBrMethodologyDetailsSid);
        objectOutput.writeInt(accClosureMasterSid);
        objectOutput.writeLong(methodologyEndDate);
        objectOutput.writeDouble(methodologyValue);
        objectOutput.writeDouble(dampingFactor);

        if (methodologyName == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(methodologyName);
        }
    }
}
