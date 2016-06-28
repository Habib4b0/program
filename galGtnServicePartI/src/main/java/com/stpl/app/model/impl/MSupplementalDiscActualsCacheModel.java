package com.stpl.app.model.impl;

import com.stpl.app.model.MSupplementalDiscActuals;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing MSupplementalDiscActuals in entity cache.
 *
 * @author
 * @see MSupplementalDiscActuals
 * @generated
 */
public class MSupplementalDiscActualsCacheModel implements CacheModel<MSupplementalDiscActuals>,
    Externalizable {
    public double actualSales;
    public int periodSid;
    public double actualRate;
    public double actualProjectionSales;
    public double actualProjectionRate;
    public int projectionDetailsSid;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(13);

        sb.append("{actualSales=");
        sb.append(actualSales);
        sb.append(", periodSid=");
        sb.append(periodSid);
        sb.append(", actualRate=");
        sb.append(actualRate);
        sb.append(", actualProjectionSales=");
        sb.append(actualProjectionSales);
        sb.append(", actualProjectionRate=");
        sb.append(actualProjectionRate);
        sb.append(", projectionDetailsSid=");
        sb.append(projectionDetailsSid);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public MSupplementalDiscActuals toEntityModel() {
        MSupplementalDiscActualsImpl mSupplementalDiscActualsImpl = new MSupplementalDiscActualsImpl();

        mSupplementalDiscActualsImpl.setActualSales(actualSales);
        mSupplementalDiscActualsImpl.setPeriodSid(periodSid);
        mSupplementalDiscActualsImpl.setActualRate(actualRate);
        mSupplementalDiscActualsImpl.setActualProjectionSales(actualProjectionSales);
        mSupplementalDiscActualsImpl.setActualProjectionRate(actualProjectionRate);
        mSupplementalDiscActualsImpl.setProjectionDetailsSid(projectionDetailsSid);

        mSupplementalDiscActualsImpl.resetOriginalValues();

        return mSupplementalDiscActualsImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        actualSales = objectInput.readDouble();
        periodSid = objectInput.readInt();
        actualRate = objectInput.readDouble();
        actualProjectionSales = objectInput.readDouble();
        actualProjectionRate = objectInput.readDouble();
        projectionDetailsSid = objectInput.readInt();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeDouble(actualSales);
        objectOutput.writeInt(periodSid);
        objectOutput.writeDouble(actualRate);
        objectOutput.writeDouble(actualProjectionSales);
        objectOutput.writeDouble(actualProjectionRate);
        objectOutput.writeInt(projectionDetailsSid);
    }
}
