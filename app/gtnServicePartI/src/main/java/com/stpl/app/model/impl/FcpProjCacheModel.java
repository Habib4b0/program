package com.stpl.app.model.impl;

import com.stpl.app.model.FcpProj;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing FcpProj in entity cache.
 *
 * @author
 * @see FcpProj
 * @generated
 */
public class FcpProjCacheModel implements CacheModel<FcpProj>, Externalizable {
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
    public FcpProj toEntityModel() {
        FcpProjImpl fcpProjImpl = new FcpProjImpl();

        fcpProjImpl.setAdjustment(adjustment);
        fcpProjImpl.setPeriodSid(periodSid);

        if (priceType == null) {
            fcpProjImpl.setPriceType(StringPool.BLANK);
        } else {
            fcpProjImpl.setPriceType(priceType);
        }

        fcpProjImpl.setProjectionPrice(projectionPrice);

        if (notes == null) {
            fcpProjImpl.setNotes(StringPool.BLANK);
        } else {
            fcpProjImpl.setNotes(notes);
        }

        fcpProjImpl.setNaProjDetailsSid(naProjDetailsSid);

        fcpProjImpl.resetOriginalValues();

        return fcpProjImpl;
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
