package com.stpl.app.model.impl;

import com.stpl.app.model.NmSalesProjection;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing NmSalesProjection in entity cache.
 *
 * @author
 * @see NmSalesProjection
 * @generated
 */
public class NmSalesProjectionCacheModel implements CacheModel<NmSalesProjection>,
    Externalizable {
    public int periodSid;
    public double productGrowth;
    public int projectionDetailsSid;
    public double accountGrowth;
    public double projectionUnits;
    public double projectionSales;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(13);

        sb.append("{periodSid=");
        sb.append(periodSid);
        sb.append(", productGrowth=");
        sb.append(productGrowth);
        sb.append(", projectionDetailsSid=");
        sb.append(projectionDetailsSid);
        sb.append(", accountGrowth=");
        sb.append(accountGrowth);
        sb.append(", projectionUnits=");
        sb.append(projectionUnits);
        sb.append(", projectionSales=");
        sb.append(projectionSales);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public NmSalesProjection toEntityModel() {
        NmSalesProjectionImpl nmSalesProjectionImpl = new NmSalesProjectionImpl();

        nmSalesProjectionImpl.setPeriodSid(periodSid);
        nmSalesProjectionImpl.setProductGrowth(productGrowth);
        nmSalesProjectionImpl.setProjectionDetailsSid(projectionDetailsSid);
        nmSalesProjectionImpl.setAccountGrowth(accountGrowth);
        nmSalesProjectionImpl.setProjectionUnits(projectionUnits);
        nmSalesProjectionImpl.setProjectionSales(projectionSales);

        nmSalesProjectionImpl.resetOriginalValues();

        return nmSalesProjectionImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        periodSid = objectInput.readInt();
        productGrowth = objectInput.readDouble();
        projectionDetailsSid = objectInput.readInt();
        accountGrowth = objectInput.readDouble();
        projectionUnits = objectInput.readDouble();
        projectionSales = objectInput.readDouble();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeInt(periodSid);
        objectOutput.writeDouble(productGrowth);
        objectOutput.writeInt(projectionDetailsSid);
        objectOutput.writeDouble(accountGrowth);
        objectOutput.writeDouble(projectionUnits);
        objectOutput.writeDouble(projectionSales);
    }
}
