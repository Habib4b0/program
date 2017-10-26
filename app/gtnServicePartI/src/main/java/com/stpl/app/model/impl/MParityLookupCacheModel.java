package com.stpl.app.model.impl;

import com.stpl.app.model.MParityLookup;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing MParityLookup in entity cache.
 *
 * @author
 * @see MParityLookup
 * @generated
 */
public class MParityLookupCacheModel implements CacheModel<MParityLookup>,
    Externalizable {
    public int contractMasterSid;
    public String marketType;
    public int itemMasterSid;
    public int mParityLookupSid;
    public int projectionDetailsSid;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(11);

        sb.append("{contractMasterSid=");
        sb.append(contractMasterSid);
        sb.append(", marketType=");
        sb.append(marketType);
        sb.append(", itemMasterSid=");
        sb.append(itemMasterSid);
        sb.append(", mParityLookupSid=");
        sb.append(mParityLookupSid);
        sb.append(", projectionDetailsSid=");
        sb.append(projectionDetailsSid);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public MParityLookup toEntityModel() {
        MParityLookupImpl mParityLookupImpl = new MParityLookupImpl();

        mParityLookupImpl.setContractMasterSid(contractMasterSid);

        if (marketType == null) {
            mParityLookupImpl.setMarketType(StringPool.BLANK);
        } else {
            mParityLookupImpl.setMarketType(marketType);
        }

        mParityLookupImpl.setItemMasterSid(itemMasterSid);
        mParityLookupImpl.setMParityLookupSid(mParityLookupSid);
        mParityLookupImpl.setProjectionDetailsSid(projectionDetailsSid);

        mParityLookupImpl.resetOriginalValues();

        return mParityLookupImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        contractMasterSid = objectInput.readInt();
        marketType = objectInput.readUTF();
        itemMasterSid = objectInput.readInt();
        mParityLookupSid = objectInput.readInt();
        projectionDetailsSid = objectInput.readInt();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeInt(contractMasterSid);

        if (marketType == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(marketType);
        }

        objectOutput.writeInt(itemMasterSid);
        objectOutput.writeInt(mParityLookupSid);
        objectOutput.writeInt(projectionDetailsSid);
    }
}
