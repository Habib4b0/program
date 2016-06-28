package com.stpl.app.model.impl;

import com.stpl.app.model.NationalAssumptionsProj;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing NationalAssumptionsProj in entity cache.
 *
 * @author
 * @see NationalAssumptionsProj
 * @generated
 */
public class NationalAssumptionsProjCacheModel implements CacheModel<NationalAssumptionsProj>,
    Externalizable {
    public int periodSid;
    public int itemMasterSid;
    public String priceType;
    public double projectionPrice;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(9);

        sb.append("{periodSid=");
        sb.append(periodSid);
        sb.append(", itemMasterSid=");
        sb.append(itemMasterSid);
        sb.append(", priceType=");
        sb.append(priceType);
        sb.append(", projectionPrice=");
        sb.append(projectionPrice);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public NationalAssumptionsProj toEntityModel() {
        NationalAssumptionsProjImpl nationalAssumptionsProjImpl = new NationalAssumptionsProjImpl();

        nationalAssumptionsProjImpl.setPeriodSid(periodSid);
        nationalAssumptionsProjImpl.setItemMasterSid(itemMasterSid);

        if (priceType == null) {
            nationalAssumptionsProjImpl.setPriceType(StringPool.BLANK);
        } else {
            nationalAssumptionsProjImpl.setPriceType(priceType);
        }

        nationalAssumptionsProjImpl.setProjectionPrice(projectionPrice);

        nationalAssumptionsProjImpl.resetOriginalValues();

        return nationalAssumptionsProjImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        periodSid = objectInput.readInt();
        itemMasterSid = objectInput.readInt();
        priceType = objectInput.readUTF();
        projectionPrice = objectInput.readDouble();
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

        objectOutput.writeDouble(projectionPrice);
    }
}
