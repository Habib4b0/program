package com.stpl.app.model.impl;

import com.stpl.app.model.NmDiscountProjection;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
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
    public String adjustmentMethodology;
    public String adjustmentBasis;
    public int periodSid;
    public double projectionRate;
    public int projectionDetailsSid;
    public boolean adjustmentVariable;
    public double adjustmentValue;
    public String adjustmentType;
    public double projectionSales;
    public double discountRate;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(21);

        sb.append("{adjustmentMethodology=");
        sb.append(adjustmentMethodology);
        sb.append(", adjustmentBasis=");
        sb.append(adjustmentBasis);
        sb.append(", periodSid=");
        sb.append(periodSid);
        sb.append(", projectionRate=");
        sb.append(projectionRate);
        sb.append(", projectionDetailsSid=");
        sb.append(projectionDetailsSid);
        sb.append(", adjustmentVariable=");
        sb.append(adjustmentVariable);
        sb.append(", adjustmentValue=");
        sb.append(adjustmentValue);
        sb.append(", adjustmentType=");
        sb.append(adjustmentType);
        sb.append(", projectionSales=");
        sb.append(projectionSales);
        sb.append(", discountRate=");
        sb.append(discountRate);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public NmDiscountProjection toEntityModel() {
        NmDiscountProjectionImpl nmDiscountProjectionImpl = new NmDiscountProjectionImpl();

        if (adjustmentMethodology == null) {
            nmDiscountProjectionImpl.setAdjustmentMethodology(StringPool.BLANK);
        } else {
            nmDiscountProjectionImpl.setAdjustmentMethodology(adjustmentMethodology);
        }

        if (adjustmentBasis == null) {
            nmDiscountProjectionImpl.setAdjustmentBasis(StringPool.BLANK);
        } else {
            nmDiscountProjectionImpl.setAdjustmentBasis(adjustmentBasis);
        }

        nmDiscountProjectionImpl.setPeriodSid(periodSid);
        nmDiscountProjectionImpl.setProjectionRate(projectionRate);
        nmDiscountProjectionImpl.setProjectionDetailsSid(projectionDetailsSid);
        nmDiscountProjectionImpl.setAdjustmentVariable(adjustmentVariable);
        nmDiscountProjectionImpl.setAdjustmentValue(adjustmentValue);

        if (adjustmentType == null) {
            nmDiscountProjectionImpl.setAdjustmentType(StringPool.BLANK);
        } else {
            nmDiscountProjectionImpl.setAdjustmentType(adjustmentType);
        }

        nmDiscountProjectionImpl.setProjectionSales(projectionSales);
        nmDiscountProjectionImpl.setDiscountRate(discountRate);

        nmDiscountProjectionImpl.resetOriginalValues();

        return nmDiscountProjectionImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        adjustmentMethodology = objectInput.readUTF();
        adjustmentBasis = objectInput.readUTF();
        periodSid = objectInput.readInt();
        projectionRate = objectInput.readDouble();
        projectionDetailsSid = objectInput.readInt();
        adjustmentVariable = objectInput.readBoolean();
        adjustmentValue = objectInput.readDouble();
        adjustmentType = objectInput.readUTF();
        projectionSales = objectInput.readDouble();
        discountRate = objectInput.readDouble();
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
        objectOutput.writeDouble(projectionRate);
        objectOutput.writeInt(projectionDetailsSid);
        objectOutput.writeBoolean(adjustmentVariable);
        objectOutput.writeDouble(adjustmentValue);

        if (adjustmentType == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(adjustmentType);
        }

        objectOutput.writeDouble(projectionSales);
        objectOutput.writeDouble(discountRate);
    }
}
