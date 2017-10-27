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
    public int projectionDetailsSid;
    public double actualSales;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(9);

        sb.append("{actualRate=");
        sb.append(actualRate);
        sb.append(", periodSid=");
        sb.append(periodSid);
        sb.append(", projectionDetailsSid=");
        sb.append(projectionDetailsSid);
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
        nmActualDiscountImpl.setProjectionDetailsSid(projectionDetailsSid);
        nmActualDiscountImpl.setActualSales(actualSales);

        nmActualDiscountImpl.resetOriginalValues();

        return nmActualDiscountImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        actualRate = objectInput.readDouble();
        periodSid = objectInput.readInt();
        projectionDetailsSid = objectInput.readInt();
        actualSales = objectInput.readDouble();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeDouble(actualRate);
        objectOutput.writeInt(periodSid);
        objectOutput.writeInt(projectionDetailsSid);
        objectOutput.writeDouble(actualSales);
    }
}
