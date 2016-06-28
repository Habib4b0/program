package com.stpl.app.model.impl;

import com.stpl.app.model.NmActualDiscount;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing NmActualDiscount in entity cache.
 *
 * @author
 * @see NmActualDiscount
 * @generated
 */
public class NmActualDiscountCacheModel implements CacheModel<NmActualDiscount>,
    Externalizable {
    public double actualRate;
    public int periodSid;
    public double actualProjectionSales;
    public int projectionDetailsSid;
    public double actualProjectionRate;
    public double actualSales;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(13);

        sb.append("{actualRate=");
        sb.append(actualRate);
        sb.append(", periodSid=");
        sb.append(periodSid);
        sb.append(", actualProjectionSales=");
        sb.append(actualProjectionSales);
        sb.append(", projectionDetailsSid=");
        sb.append(projectionDetailsSid);
        sb.append(", actualProjectionRate=");
        sb.append(actualProjectionRate);
        sb.append(", actualSales=");
        sb.append(actualSales);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public NmActualDiscount toEntityModel() {
        NmActualDiscountImpl nmActualDiscountImpl = new NmActualDiscountImpl();

        nmActualDiscountImpl.setActualRate(actualRate);
        nmActualDiscountImpl.setPeriodSid(periodSid);
        nmActualDiscountImpl.setActualProjectionSales(actualProjectionSales);
        nmActualDiscountImpl.setProjectionDetailsSid(projectionDetailsSid);
        nmActualDiscountImpl.setActualProjectionRate(actualProjectionRate);
        nmActualDiscountImpl.setActualSales(actualSales);

        nmActualDiscountImpl.resetOriginalValues();

        return nmActualDiscountImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        actualRate = objectInput.readDouble();
        periodSid = objectInput.readInt();
        actualProjectionSales = objectInput.readDouble();
        projectionDetailsSid = objectInput.readInt();
        actualProjectionRate = objectInput.readDouble();
        actualSales = objectInput.readDouble();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeDouble(actualRate);
        objectOutput.writeInt(periodSid);
        objectOutput.writeDouble(actualProjectionSales);
        objectOutput.writeInt(projectionDetailsSid);
        objectOutput.writeDouble(actualProjectionRate);
        objectOutput.writeDouble(actualSales);
    }
}
