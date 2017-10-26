package com.stpl.app.model.impl;

import com.stpl.app.model.MedicaidUraActuals;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing MedicaidUraActuals in entity cache.
 *
 * @author
 * @see MedicaidUraActuals
 * @generated
 */
public class MedicaidUraActualsCacheModel implements CacheModel<MedicaidUraActuals>,
    Externalizable {
    public int periodSid;
    public String priceType;
    public double actualPrice;
    public String notes;
    public int naProjDetailsSid;
    public double baseYear;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(13);

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
        sb.append(", baseYear=");
        sb.append(baseYear);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public MedicaidUraActuals toEntityModel() {
        MedicaidUraActualsImpl medicaidUraActualsImpl = new MedicaidUraActualsImpl();

        medicaidUraActualsImpl.setPeriodSid(periodSid);

        if (priceType == null) {
            medicaidUraActualsImpl.setPriceType(StringPool.BLANK);
        } else {
            medicaidUraActualsImpl.setPriceType(priceType);
        }

        medicaidUraActualsImpl.setActualPrice(actualPrice);

        if (notes == null) {
            medicaidUraActualsImpl.setNotes(StringPool.BLANK);
        } else {
            medicaidUraActualsImpl.setNotes(notes);
        }

        medicaidUraActualsImpl.setNaProjDetailsSid(naProjDetailsSid);
        medicaidUraActualsImpl.setBaseYear(baseYear);

        medicaidUraActualsImpl.resetOriginalValues();

        return medicaidUraActualsImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        periodSid = objectInput.readInt();
        priceType = objectInput.readUTF();
        actualPrice = objectInput.readDouble();
        notes = objectInput.readUTF();
        naProjDetailsSid = objectInput.readInt();
        baseYear = objectInput.readDouble();
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
        objectOutput.writeDouble(baseYear);
    }
}
