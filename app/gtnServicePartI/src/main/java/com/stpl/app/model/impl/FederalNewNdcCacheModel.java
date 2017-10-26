package com.stpl.app.model.impl;

import com.stpl.app.model.FederalNewNdc;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing FederalNewNdc in entity cache.
 *
 * @author
 * @see FederalNewNdc
 * @generated
 */
public class FederalNewNdcCacheModel implements CacheModel<FederalNewNdc>,
    Externalizable {
    public double fss;
    public int itemMasterSid;
    public double wacPrice;
    public double nonFamp;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(9);

        sb.append("{fss=");
        sb.append(fss);
        sb.append(", itemMasterSid=");
        sb.append(itemMasterSid);
        sb.append(", wacPrice=");
        sb.append(wacPrice);
        sb.append(", nonFamp=");
        sb.append(nonFamp);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public FederalNewNdc toEntityModel() {
        FederalNewNdcImpl federalNewNdcImpl = new FederalNewNdcImpl();

        federalNewNdcImpl.setFss(fss);
        federalNewNdcImpl.setItemMasterSid(itemMasterSid);
        federalNewNdcImpl.setWacPrice(wacPrice);
        federalNewNdcImpl.setNonFamp(nonFamp);

        federalNewNdcImpl.resetOriginalValues();

        return federalNewNdcImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        fss = objectInput.readDouble();
        itemMasterSid = objectInput.readInt();
        wacPrice = objectInput.readDouble();
        nonFamp = objectInput.readDouble();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeDouble(fss);
        objectOutput.writeInt(itemMasterSid);
        objectOutput.writeDouble(wacPrice);
        objectOutput.writeDouble(nonFamp);
    }
}
