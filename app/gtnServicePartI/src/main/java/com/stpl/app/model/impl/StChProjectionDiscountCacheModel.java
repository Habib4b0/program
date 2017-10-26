package com.stpl.app.model.impl;

import com.stpl.app.model.StChProjectionDiscount;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing StChProjectionDiscount in entity cache.
 *
 * @author
 * @see StChProjectionDiscount
 * @generated
 */
public class StChProjectionDiscountCacheModel implements CacheModel<StChProjectionDiscount>,
    Externalizable {
    public long lastModifiedDate;
    public String adjustmentMethodology;
    public double productGrowth;
    public double projectionRate;
    public int projectionDetailsSid;
    public int userId;
    public double accountGrowth;
    public double discountAmount;
    public double discountRate;
    public int periodSid;
    public String adjustmentBasis;
    public int sessionId;
    public double adjustmentValue;
    public String adjustmentType;
    public int rsModelSid;
    public double projectionSales;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(33);

        sb.append("{lastModifiedDate=");
        sb.append(lastModifiedDate);
        sb.append(", adjustmentMethodology=");
        sb.append(adjustmentMethodology);
        sb.append(", productGrowth=");
        sb.append(productGrowth);
        sb.append(", projectionRate=");
        sb.append(projectionRate);
        sb.append(", projectionDetailsSid=");
        sb.append(projectionDetailsSid);
        sb.append(", userId=");
        sb.append(userId);
        sb.append(", accountGrowth=");
        sb.append(accountGrowth);
        sb.append(", discountAmount=");
        sb.append(discountAmount);
        sb.append(", discountRate=");
        sb.append(discountRate);
        sb.append(", periodSid=");
        sb.append(periodSid);
        sb.append(", adjustmentBasis=");
        sb.append(adjustmentBasis);
        sb.append(", sessionId=");
        sb.append(sessionId);
        sb.append(", adjustmentValue=");
        sb.append(adjustmentValue);
        sb.append(", adjustmentType=");
        sb.append(adjustmentType);
        sb.append(", rsModelSid=");
        sb.append(rsModelSid);
        sb.append(", projectionSales=");
        sb.append(projectionSales);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public StChProjectionDiscount toEntityModel() {
        StChProjectionDiscountImpl stChProjectionDiscountImpl = new StChProjectionDiscountImpl();

        if (lastModifiedDate == Long.MIN_VALUE) {
            stChProjectionDiscountImpl.setLastModifiedDate(null);
        } else {
            stChProjectionDiscountImpl.setLastModifiedDate(new Date(
                    lastModifiedDate));
        }

        if (adjustmentMethodology == null) {
            stChProjectionDiscountImpl.setAdjustmentMethodology(StringPool.BLANK);
        } else {
            stChProjectionDiscountImpl.setAdjustmentMethodology(adjustmentMethodology);
        }

        stChProjectionDiscountImpl.setProductGrowth(productGrowth);
        stChProjectionDiscountImpl.setProjectionRate(projectionRate);
        stChProjectionDiscountImpl.setProjectionDetailsSid(projectionDetailsSid);
        stChProjectionDiscountImpl.setUserId(userId);
        stChProjectionDiscountImpl.setAccountGrowth(accountGrowth);
        stChProjectionDiscountImpl.setDiscountAmount(discountAmount);
        stChProjectionDiscountImpl.setDiscountRate(discountRate);
        stChProjectionDiscountImpl.setPeriodSid(periodSid);

        if (adjustmentBasis == null) {
            stChProjectionDiscountImpl.setAdjustmentBasis(StringPool.BLANK);
        } else {
            stChProjectionDiscountImpl.setAdjustmentBasis(adjustmentBasis);
        }

        stChProjectionDiscountImpl.setSessionId(sessionId);
        stChProjectionDiscountImpl.setAdjustmentValue(adjustmentValue);

        if (adjustmentType == null) {
            stChProjectionDiscountImpl.setAdjustmentType(StringPool.BLANK);
        } else {
            stChProjectionDiscountImpl.setAdjustmentType(adjustmentType);
        }

        stChProjectionDiscountImpl.setRsModelSid(rsModelSid);
        stChProjectionDiscountImpl.setProjectionSales(projectionSales);

        stChProjectionDiscountImpl.resetOriginalValues();

        return stChProjectionDiscountImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        lastModifiedDate = objectInput.readLong();
        adjustmentMethodology = objectInput.readUTF();
        productGrowth = objectInput.readDouble();
        projectionRate = objectInput.readDouble();
        projectionDetailsSid = objectInput.readInt();
        userId = objectInput.readInt();
        accountGrowth = objectInput.readDouble();
        discountAmount = objectInput.readDouble();
        discountRate = objectInput.readDouble();
        periodSid = objectInput.readInt();
        adjustmentBasis = objectInput.readUTF();
        sessionId = objectInput.readInt();
        adjustmentValue = objectInput.readDouble();
        adjustmentType = objectInput.readUTF();
        rsModelSid = objectInput.readInt();
        projectionSales = objectInput.readDouble();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeLong(lastModifiedDate);

        if (adjustmentMethodology == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(adjustmentMethodology);
        }

        objectOutput.writeDouble(productGrowth);
        objectOutput.writeDouble(projectionRate);
        objectOutput.writeInt(projectionDetailsSid);
        objectOutput.writeInt(userId);
        objectOutput.writeDouble(accountGrowth);
        objectOutput.writeDouble(discountAmount);
        objectOutput.writeDouble(discountRate);
        objectOutput.writeInt(periodSid);

        if (adjustmentBasis == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(adjustmentBasis);
        }

        objectOutput.writeInt(sessionId);
        objectOutput.writeDouble(adjustmentValue);

        if (adjustmentType == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(adjustmentType);
        }

        objectOutput.writeInt(rsModelSid);
        objectOutput.writeDouble(projectionSales);
    }
}
