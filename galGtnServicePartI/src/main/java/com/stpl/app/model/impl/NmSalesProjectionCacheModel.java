package com.stpl.app.model.impl;

import com.stpl.app.model.NmSalesProjection;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
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
    public String adjustmentMethodology;
    public String adjustmentBasis;
    public int periodSid;
    public double productGrowth;
    public int projectionDetailsSid;
    public double adjustmentValues;
    public boolean adjustmentVariable;
    public double accountGrowth;
    public double projectionUnits;
    public String adjustmentType;
    public double projectionSales;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(23);

        sb.append("{adjustmentMethodology=");
        sb.append(adjustmentMethodology);
        sb.append(", adjustmentBasis=");
        sb.append(adjustmentBasis);
        sb.append(", periodSid=");
        sb.append(periodSid);
        sb.append(", productGrowth=");
        sb.append(productGrowth);
        sb.append(", projectionDetailsSid=");
        sb.append(projectionDetailsSid);
        sb.append(", adjustmentValues=");
        sb.append(adjustmentValues);
        sb.append(", adjustmentVariable=");
        sb.append(adjustmentVariable);
        sb.append(", accountGrowth=");
        sb.append(accountGrowth);
        sb.append(", projectionUnits=");
        sb.append(projectionUnits);
        sb.append(", adjustmentType=");
        sb.append(adjustmentType);
        sb.append(", projectionSales=");
        sb.append(projectionSales);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public NmSalesProjection toEntityModel() {
        NmSalesProjectionImpl nmSalesProjectionImpl = new NmSalesProjectionImpl();

        if (adjustmentMethodology == null) {
            nmSalesProjectionImpl.setAdjustmentMethodology(StringPool.BLANK);
        } else {
            nmSalesProjectionImpl.setAdjustmentMethodology(adjustmentMethodology);
        }

        if (adjustmentBasis == null) {
            nmSalesProjectionImpl.setAdjustmentBasis(StringPool.BLANK);
        } else {
            nmSalesProjectionImpl.setAdjustmentBasis(adjustmentBasis);
        }

        nmSalesProjectionImpl.setPeriodSid(periodSid);
        nmSalesProjectionImpl.setProductGrowth(productGrowth);
        nmSalesProjectionImpl.setProjectionDetailsSid(projectionDetailsSid);
        nmSalesProjectionImpl.setAdjustmentValues(adjustmentValues);
        nmSalesProjectionImpl.setAdjustmentVariable(adjustmentVariable);
        nmSalesProjectionImpl.setAccountGrowth(accountGrowth);
        nmSalesProjectionImpl.setProjectionUnits(projectionUnits);

        if (adjustmentType == null) {
            nmSalesProjectionImpl.setAdjustmentType(StringPool.BLANK);
        } else {
            nmSalesProjectionImpl.setAdjustmentType(adjustmentType);
        }

        nmSalesProjectionImpl.setProjectionSales(projectionSales);

        nmSalesProjectionImpl.resetOriginalValues();

        return nmSalesProjectionImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        adjustmentMethodology = objectInput.readUTF();
        adjustmentBasis = objectInput.readUTF();
        periodSid = objectInput.readInt();
        productGrowth = objectInput.readDouble();
        projectionDetailsSid = objectInput.readInt();
        adjustmentValues = objectInput.readDouble();
        adjustmentVariable = objectInput.readBoolean();
        accountGrowth = objectInput.readDouble();
        projectionUnits = objectInput.readDouble();
        adjustmentType = objectInput.readUTF();
        projectionSales = objectInput.readDouble();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        if (adjustmentMethodology == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(adjustmentMethodology);
        }

        if (adjustmentBasis == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(adjustmentBasis);
        }

        objectOutput.writeInt(periodSid);
        objectOutput.writeDouble(productGrowth);
        objectOutput.writeInt(projectionDetailsSid);
        objectOutput.writeDouble(adjustmentValues);
        objectOutput.writeBoolean(adjustmentVariable);
        objectOutput.writeDouble(accountGrowth);
        objectOutput.writeDouble(projectionUnits);

        if (adjustmentType == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(adjustmentType);
        }

        objectOutput.writeDouble(projectionSales);
    }
}
