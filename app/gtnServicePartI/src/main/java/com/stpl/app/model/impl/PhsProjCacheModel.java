package com.stpl.app.model.impl;

import com.stpl.app.model.PhsProj;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing PhsProj in entity cache.
 *
 * @author
 * @see PhsProj
 * @generated
 */
public class PhsProjCacheModel implements CacheModel<PhsProj>, Externalizable {
    public double adjustment;
    public int periodSid;
    public String priceType;
    public double projectionPrice;
    public String notes;
    public int naProjDetailsSid;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(13);

        sb.append("{adjustment=");
        sb.append(adjustment);
        sb.append(", periodSid=");
        sb.append(periodSid);
        sb.append(", priceType=");
        sb.append(priceType);
        sb.append(", projectionPrice=");
        sb.append(projectionPrice);
        sb.append(", notes=");
        sb.append(notes);
        sb.append(", naProjDetailsSid=");
        sb.append(naProjDetailsSid);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public PhsProj toEntityModel() {
        PhsProjImpl phsProjImpl = new PhsProjImpl();

        phsProjImpl.setAdjustment(adjustment);
        phsProjImpl.setPeriodSid(periodSid);

        if (priceType == null) {
            phsProjImpl.setPriceType(StringPool.BLANK);
        } else {
            phsProjImpl.setPriceType(priceType);
        }

        phsProjImpl.setProjectionPrice(projectionPrice);

        if (notes == null) {
            phsProjImpl.setNotes(StringPool.BLANK);
        } else {
            phsProjImpl.setNotes(notes);
        }

        phsProjImpl.setNaProjDetailsSid(naProjDetailsSid);

        phsProjImpl.resetOriginalValues();

        return phsProjImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        adjustment = objectInput.readDouble();
        periodSid = objectInput.readInt();
        priceType = objectInput.readUTF();
        projectionPrice = objectInput.readDouble();
        notes = objectInput.readUTF();
        naProjDetailsSid = objectInput.readInt();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeDouble(adjustment);
        objectOutput.writeInt(periodSid);

        if (priceType == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(priceType);
        }

        objectOutput.writeDouble(projectionPrice);

        if (notes == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(notes);
        }

        objectOutput.writeInt(naProjDetailsSid);
    }
}
