package com.stpl.app.model.impl;

import com.stpl.app.model.ChSalesProjection;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing ChSalesProjection in entity cache.
 *
 * @author
 * @see ChSalesProjection
 * @generated
 */
public class ChSalesProjectionCacheModel implements CacheModel<ChSalesProjection>,
    Externalizable {
    public double contractUnits;
    public double perOfBusiness;
    public int periodSid;
    public double contractSales;
    public int projectionDetailsSid;
    public double gtsSales;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(13);

        sb.append("{contractUnits=");
        sb.append(contractUnits);
        sb.append(", perOfBusiness=");
        sb.append(perOfBusiness);
        sb.append(", periodSid=");
        sb.append(periodSid);
        sb.append(", contractSales=");
        sb.append(contractSales);
        sb.append(", projectionDetailsSid=");
        sb.append(projectionDetailsSid);
        sb.append(", gtsSales=");
        sb.append(gtsSales);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public ChSalesProjection toEntityModel() {
        ChSalesProjectionImpl chSalesProjectionImpl = new ChSalesProjectionImpl();

        chSalesProjectionImpl.setContractUnits(contractUnits);
        chSalesProjectionImpl.setPerOfBusiness(perOfBusiness);
        chSalesProjectionImpl.setPeriodSid(periodSid);
        chSalesProjectionImpl.setContractSales(contractSales);
        chSalesProjectionImpl.setProjectionDetailsSid(projectionDetailsSid);
        chSalesProjectionImpl.setGtsSales(gtsSales);

        chSalesProjectionImpl.resetOriginalValues();

        return chSalesProjectionImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        contractUnits = objectInput.readDouble();
        perOfBusiness = objectInput.readDouble();
        periodSid = objectInput.readInt();
        contractSales = objectInput.readDouble();
        projectionDetailsSid = objectInput.readInt();
        gtsSales = objectInput.readDouble();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeDouble(contractUnits);
        objectOutput.writeDouble(perOfBusiness);
        objectOutput.writeInt(periodSid);
        objectOutput.writeDouble(contractSales);
        objectOutput.writeInt(projectionDetailsSid);
        objectOutput.writeDouble(gtsSales);
    }
}
