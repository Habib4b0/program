package com.stpl.app.model.impl;

import com.stpl.app.model.NmActualPpa;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing NmActualPpa in entity cache.
 *
 * @author
 * @see NmActualPpa
 * @generated
 */
public class NmActualPpaCacheModel implements CacheModel<NmActualPpa>,
    Externalizable {
    public double actualRate;
    public int periodSid;
    public int projectionDetailsSid;
    public double actualDiscountDollar;
    public double actualDiscountUnits;
    public double actualSales;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(13);

        sb.append("{actualRate=");
        sb.append(actualRate);
        sb.append(", periodSid=");
        sb.append(periodSid);
        sb.append(", projectionDetailsSid=");
        sb.append(projectionDetailsSid);
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
    public NmActualPpa toEntityModel() {
        NmActualPpaImpl nmActualPpaImpl = new NmActualPpaImpl();

        nmActualPpaImpl.setActualRate(actualRate);
        nmActualPpaImpl.setPeriodSid(periodSid);
        nmActualPpaImpl.setProjectionDetailsSid(projectionDetailsSid);
        nmActualPpaImpl.setActualDiscountDollar(actualDiscountDollar);
        nmActualPpaImpl.setActualDiscountUnits(actualDiscountUnits);
        nmActualPpaImpl.setActualSales(actualSales);

        nmActualPpaImpl.resetOriginalValues();

        return nmActualPpaImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        actualRate = objectInput.readDouble();
        periodSid = objectInput.readInt();
        projectionDetailsSid = objectInput.readInt();
        actualDiscountDollar = objectInput.readDouble();
        actualDiscountUnits = objectInput.readDouble();
        actualSales = objectInput.readDouble();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeDouble(actualRate);
        objectOutput.writeInt(periodSid);
        objectOutput.writeInt(projectionDetailsSid);
        objectOutput.writeDouble(actualDiscountDollar);
        objectOutput.writeDouble(actualDiscountUnits);
        objectOutput.writeDouble(actualSales);
    }
}
