package com.stpl.app.model.impl;

import com.stpl.app.model.NationalAssumptionsActuals;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing NationalAssumptionsActuals in entity cache.
 *
 * @author
 * @see NationalAssumptionsActuals
 * @generated
 */
public class NationalAssumptionsActualsCacheModel implements CacheModel<NationalAssumptionsActuals>,
    Externalizable {
    public int periodSid;
    public int itemMasterSid;
    public String priceType;
    public double actualPrice;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(9);

        sb.append("{periodSid=");
        sb.append(periodSid);
        sb.append(", itemMasterSid=");
        sb.append(itemMasterSid);
        sb.append(", priceType=");
        sb.append(priceType);
        sb.append(", actualPrice=");
        sb.append(actualPrice);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public NationalAssumptionsActuals toEntityModel() {
        NationalAssumptionsActualsImpl nationalAssumptionsActualsImpl = new NationalAssumptionsActualsImpl();

        nationalAssumptionsActualsImpl.setPeriodSid(periodSid);
        nationalAssumptionsActualsImpl.setItemMasterSid(itemMasterSid);

        if (priceType == null) {
            nationalAssumptionsActualsImpl.setPriceType(StringPool.BLANK);
        } else {
            nationalAssumptionsActualsImpl.setPriceType(priceType);
        }

        nationalAssumptionsActualsImpl.setActualPrice(actualPrice);

        nationalAssumptionsActualsImpl.resetOriginalValues();

        return nationalAssumptionsActualsImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        periodSid = objectInput.readInt();
        itemMasterSid = objectInput.readInt();
        priceType = objectInput.readUTF();
        actualPrice = objectInput.readDouble();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeInt(periodSid);
        objectOutput.writeInt(itemMasterSid);

        if (priceType == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(priceType);
        }

        objectOutput.writeDouble(actualPrice);
    }
}
