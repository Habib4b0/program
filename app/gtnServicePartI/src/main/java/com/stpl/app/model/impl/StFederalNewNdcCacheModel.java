package com.stpl.app.model.impl;

import com.stpl.app.model.StFederalNewNdc;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing StFederalNewNdc in entity cache.
 *
 * @author
 * @see StFederalNewNdc
 * @generated
 */
public class StFederalNewNdcCacheModel implements CacheModel<StFederalNewNdc>,
    Externalizable {
    public double fss;
    public int userId;
    public long lastModifiedDate;
    public int itemMasterSid;
    public double wacPrice;
    public int sessionId;
    public double nonFamp;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(15);

        sb.append("{fss=");
        sb.append(fss);
        sb.append(", userId=");
        sb.append(userId);
        sb.append(", lastModifiedDate=");
        sb.append(lastModifiedDate);
        sb.append(", itemMasterSid=");
        sb.append(itemMasterSid);
        sb.append(", wacPrice=");
        sb.append(wacPrice);
        sb.append(", sessionId=");
        sb.append(sessionId);
        sb.append(", nonFamp=");
        sb.append(nonFamp);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public StFederalNewNdc toEntityModel() {
        StFederalNewNdcImpl stFederalNewNdcImpl = new StFederalNewNdcImpl();

        stFederalNewNdcImpl.setFss(fss);
        stFederalNewNdcImpl.setUserId(userId);

        if (lastModifiedDate == Long.MIN_VALUE) {
            stFederalNewNdcImpl.setLastModifiedDate(null);
        } else {
            stFederalNewNdcImpl.setLastModifiedDate(new Date(lastModifiedDate));
        }

        stFederalNewNdcImpl.setItemMasterSid(itemMasterSid);
        stFederalNewNdcImpl.setWacPrice(wacPrice);
        stFederalNewNdcImpl.setSessionId(sessionId);
        stFederalNewNdcImpl.setNonFamp(nonFamp);

        stFederalNewNdcImpl.resetOriginalValues();

        return stFederalNewNdcImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        fss = objectInput.readDouble();
        userId = objectInput.readInt();
        lastModifiedDate = objectInput.readLong();
        itemMasterSid = objectInput.readInt();
        wacPrice = objectInput.readDouble();
        sessionId = objectInput.readInt();
        nonFamp = objectInput.readDouble();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeDouble(fss);
        objectOutput.writeInt(userId);
        objectOutput.writeLong(lastModifiedDate);
        objectOutput.writeInt(itemMasterSid);
        objectOutput.writeDouble(wacPrice);
        objectOutput.writeInt(sessionId);
        objectOutput.writeDouble(nonFamp);
    }
}
