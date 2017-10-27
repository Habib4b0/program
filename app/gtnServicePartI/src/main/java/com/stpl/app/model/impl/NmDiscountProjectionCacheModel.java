package com.stpl.app.model.impl;

import com.stpl.app.model.NmDiscountProjection;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing NmDiscountProjection in entity cache.
 *
 * @author
 * @see NmDiscountProjection
 * @generated
 */
public class NmDiscountProjectionCacheModel implements CacheModel<NmDiscountProjection>,
    Externalizable {
    public int periodSid;
    public double projectionRate;
    public int projectionDetailsSid;
    public double projectionSales;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(9);

        sb.append("{periodSid=");
        sb.append(periodSid);
        sb.append(", projectionRate=");
        sb.append(projectionRate);
        sb.append(", projectionDetailsSid=");
        sb.append(projectionDetailsSid);
        sb.append(", projectionSales=");
        sb.append(projectionSales);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public NmDiscountProjection toEntityModel() {
        NmDiscountProjectionImpl nmDiscountProjectionImpl = new NmDiscountProjectionImpl();

        nmDiscountProjectionImpl.setPeriodSid(periodSid);
        nmDiscountProjectionImpl.setProjectionRate(projectionRate);
        nmDiscountProjectionImpl.setProjectionDetailsSid(projectionDetailsSid);
        nmDiscountProjectionImpl.setProjectionSales(projectionSales);

        nmDiscountProjectionImpl.resetOriginalValues();

        return nmDiscountProjectionImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        periodSid = objectInput.readInt();
        projectionRate = objectInput.readDouble();
        projectionDetailsSid = objectInput.readInt();
        projectionSales = objectInput.readDouble();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeInt(periodSid);
        objectOutput.writeDouble(projectionRate);
        objectOutput.writeInt(projectionDetailsSid);
        objectOutput.writeDouble(projectionSales);
    }
}
