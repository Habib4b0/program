package com.stpl.app.model.impl;

import com.stpl.app.model.ChActualDiscount;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing ChActualDiscount in entity cache.
 *
 * @author
 * @see ChActualDiscount
 * @generated
 */
public class ChActualDiscountCacheModel implements CacheModel<ChActualDiscount>,
    Externalizable {
    public double actualRate;
    public int periodSid;
    public int projectionDetailsSid;
    public int rsModelSid;
    public double actualSales;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(11);

        sb.append("{actualRate=");
        sb.append(actualRate);
        sb.append(", periodSid=");
        sb.append(periodSid);
        sb.append(", projectionDetailsSid=");
        sb.append(projectionDetailsSid);
        sb.append(", rsModelSid=");
        sb.append(rsModelSid);
        sb.append(", actualSales=");
        sb.append(actualSales);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public ChActualDiscount toEntityModel() {
        ChActualDiscountImpl chActualDiscountImpl = new ChActualDiscountImpl();

        chActualDiscountImpl.setActualRate(actualRate);
        chActualDiscountImpl.setPeriodSid(periodSid);
        chActualDiscountImpl.setProjectionDetailsSid(projectionDetailsSid);
        chActualDiscountImpl.setRsModelSid(rsModelSid);
        chActualDiscountImpl.setActualSales(actualSales);

        chActualDiscountImpl.resetOriginalValues();

        return chActualDiscountImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        actualRate = objectInput.readDouble();
        periodSid = objectInput.readInt();
        projectionDetailsSid = objectInput.readInt();
        rsModelSid = objectInput.readInt();
        actualSales = objectInput.readDouble();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeDouble(actualRate);
        objectOutput.writeInt(periodSid);
        objectOutput.writeInt(projectionDetailsSid);
        objectOutput.writeInt(rsModelSid);
        objectOutput.writeDouble(actualSales);
    }
}
