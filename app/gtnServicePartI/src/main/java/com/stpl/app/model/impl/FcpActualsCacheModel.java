package com.stpl.app.model.impl;

import com.stpl.app.model.FcpActuals;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing FcpActuals in entity cache.
 *
 * @author
 * @see FcpActuals
 * @generated
 */
public class FcpActualsCacheModel implements CacheModel<FcpActuals>,
    Externalizable {
    public int periodSid;
    public String priceType;
    public double actualPrice;
    public String notes;
    public int naProjDetailsSid;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(11);

        sb.append("{periodSid=");
        sb.append(periodSid);
        sb.append(", priceType=");
        sb.append(priceType);
        sb.append(", actualPrice=");
        sb.append(actualPrice);
        sb.append(", notes=");
        sb.append(notes);
        sb.append(", naProjDetailsSid=");
        sb.append(naProjDetailsSid);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public FcpActuals toEntityModel() {
        FcpActualsImpl fcpActualsImpl = new FcpActualsImpl();

        fcpActualsImpl.setPeriodSid(periodSid);

        if (priceType == null) {
            fcpActualsImpl.setPriceType(StringPool.BLANK);
        } else {
            fcpActualsImpl.setPriceType(priceType);
        }

        fcpActualsImpl.setActualPrice(actualPrice);

        if (notes == null) {
            fcpActualsImpl.setNotes(StringPool.BLANK);
        } else {
            fcpActualsImpl.setNotes(notes);
        }

        fcpActualsImpl.setNaProjDetailsSid(naProjDetailsSid);

        fcpActualsImpl.resetOriginalValues();

        return fcpActualsImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        periodSid = objectInput.readInt();
        priceType = objectInput.readUTF();
        actualPrice = objectInput.readDouble();
        notes = objectInput.readUTF();
        naProjDetailsSid = objectInput.readInt();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeInt(periodSid);

        if (priceType == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(priceType);
        }

        objectOutput.writeDouble(actualPrice);

        if (notes == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(notes);
        }

        objectOutput.writeInt(naProjDetailsSid);
    }
}
