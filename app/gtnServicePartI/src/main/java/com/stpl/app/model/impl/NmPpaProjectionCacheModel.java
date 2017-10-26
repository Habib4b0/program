package com.stpl.app.model.impl;

import com.stpl.app.model.NmPpaProjection;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing NmPpaProjection in entity cache.
 *
 * @author
 * @see NmPpaProjection
 * @generated
 */
public class NmPpaProjectionCacheModel implements CacheModel<NmPpaProjection>,
    Externalizable {
    public int periodSid;
    public double projectionRate;
    public int projectionDetailsSid;
    public double priceCap;
    public double projectionDiscountUnits;
    public double projectionDiscountDollar;
    public boolean reset;
    public double projectionSales;
    public double projectionMap;
    public boolean resetPriceCap;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(21);

        sb.append("{periodSid=");
        sb.append(periodSid);
        sb.append(", projectionRate=");
        sb.append(projectionRate);
        sb.append(", projectionDetailsSid=");
        sb.append(projectionDetailsSid);
        sb.append(", priceCap=");
        sb.append(priceCap);
        sb.append(", projectionDiscountUnits=");
        sb.append(projectionDiscountUnits);
        sb.append(", projectionDiscountDollar=");
        sb.append(projectionDiscountDollar);
        sb.append(", reset=");
        sb.append(reset);
        sb.append(", projectionSales=");
        sb.append(projectionSales);
        sb.append(", projectionMap=");
        sb.append(projectionMap);
        sb.append(", resetPriceCap=");
        sb.append(resetPriceCap);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public NmPpaProjection toEntityModel() {
        NmPpaProjectionImpl nmPpaProjectionImpl = new NmPpaProjectionImpl();

        nmPpaProjectionImpl.setPeriodSid(periodSid);
        nmPpaProjectionImpl.setProjectionRate(projectionRate);
        nmPpaProjectionImpl.setProjectionDetailsSid(projectionDetailsSid);
        nmPpaProjectionImpl.setPriceCap(priceCap);
        nmPpaProjectionImpl.setProjectionDiscountUnits(projectionDiscountUnits);
        nmPpaProjectionImpl.setProjectionDiscountDollar(projectionDiscountDollar);
        nmPpaProjectionImpl.setReset(reset);
        nmPpaProjectionImpl.setProjectionSales(projectionSales);
        nmPpaProjectionImpl.setProjectionMap(projectionMap);
        nmPpaProjectionImpl.setResetPriceCap(resetPriceCap);

        nmPpaProjectionImpl.resetOriginalValues();

        return nmPpaProjectionImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        periodSid = objectInput.readInt();
        projectionRate = objectInput.readDouble();
        projectionDetailsSid = objectInput.readInt();
        priceCap = objectInput.readDouble();
        projectionDiscountUnits = objectInput.readDouble();
        projectionDiscountDollar = objectInput.readDouble();
        reset = objectInput.readBoolean();
        projectionSales = objectInput.readDouble();
        projectionMap = objectInput.readDouble();
        resetPriceCap = objectInput.readBoolean();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeInt(periodSid);
        objectOutput.writeDouble(projectionRate);
        objectOutput.writeInt(projectionDetailsSid);
        objectOutput.writeDouble(priceCap);
        objectOutput.writeDouble(projectionDiscountUnits);
        objectOutput.writeDouble(projectionDiscountDollar);
        objectOutput.writeBoolean(reset);
        objectOutput.writeDouble(projectionSales);
        objectOutput.writeDouble(projectionMap);
        objectOutput.writeBoolean(resetPriceCap);
    }
}
