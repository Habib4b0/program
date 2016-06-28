package com.stpl.app.model.impl;

import com.stpl.app.model.StNmPpaProjectionMaster;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing StNmPpaProjectionMaster in entity cache.
 *
 * @author
 * @see StNmPpaProjectionMaster
 * @generated
 */
public class StNmPpaProjectionMasterCacheModel implements CacheModel<StNmPpaProjectionMaster>,
    Externalizable {
    public long lastModifiedDate;
    public boolean checkRecord;
    public String userGroup;
    public int projectionDetailsSid;
    public int userId;
    public int sessionId;
    public String priceBasis;
    public long priceProtectionEndDate;
    public long priceProtectionStartDate;
    public double actualPriceCap;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(21);

        sb.append("{lastModifiedDate=");
        sb.append(lastModifiedDate);
        sb.append(", checkRecord=");
        sb.append(checkRecord);
        sb.append(", userGroup=");
        sb.append(userGroup);
        sb.append(", projectionDetailsSid=");
        sb.append(projectionDetailsSid);
        sb.append(", userId=");
        sb.append(userId);
        sb.append(", sessionId=");
        sb.append(sessionId);
        sb.append(", priceBasis=");
        sb.append(priceBasis);
        sb.append(", priceProtectionEndDate=");
        sb.append(priceProtectionEndDate);
        sb.append(", priceProtectionStartDate=");
        sb.append(priceProtectionStartDate);
        sb.append(", actualPriceCap=");
        sb.append(actualPriceCap);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public StNmPpaProjectionMaster toEntityModel() {
        StNmPpaProjectionMasterImpl stNmPpaProjectionMasterImpl = new StNmPpaProjectionMasterImpl();

        if (lastModifiedDate == Long.MIN_VALUE) {
            stNmPpaProjectionMasterImpl.setLastModifiedDate(null);
        } else {
            stNmPpaProjectionMasterImpl.setLastModifiedDate(new Date(
                    lastModifiedDate));
        }

        stNmPpaProjectionMasterImpl.setCheckRecord(checkRecord);

        if (userGroup == null) {
            stNmPpaProjectionMasterImpl.setUserGroup(StringPool.BLANK);
        } else {
            stNmPpaProjectionMasterImpl.setUserGroup(userGroup);
        }

        stNmPpaProjectionMasterImpl.setProjectionDetailsSid(projectionDetailsSid);
        stNmPpaProjectionMasterImpl.setUserId(userId);
        stNmPpaProjectionMasterImpl.setSessionId(sessionId);

        if (priceBasis == null) {
            stNmPpaProjectionMasterImpl.setPriceBasis(StringPool.BLANK);
        } else {
            stNmPpaProjectionMasterImpl.setPriceBasis(priceBasis);
        }

        if (priceProtectionEndDate == Long.MIN_VALUE) {
            stNmPpaProjectionMasterImpl.setPriceProtectionEndDate(null);
        } else {
            stNmPpaProjectionMasterImpl.setPriceProtectionEndDate(new Date(
                    priceProtectionEndDate));
        }

        if (priceProtectionStartDate == Long.MIN_VALUE) {
            stNmPpaProjectionMasterImpl.setPriceProtectionStartDate(null);
        } else {
            stNmPpaProjectionMasterImpl.setPriceProtectionStartDate(new Date(
                    priceProtectionStartDate));
        }

        stNmPpaProjectionMasterImpl.setActualPriceCap(actualPriceCap);

        stNmPpaProjectionMasterImpl.resetOriginalValues();

        return stNmPpaProjectionMasterImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        lastModifiedDate = objectInput.readLong();
        checkRecord = objectInput.readBoolean();
        userGroup = objectInput.readUTF();
        projectionDetailsSid = objectInput.readInt();
        userId = objectInput.readInt();
        sessionId = objectInput.readInt();
        priceBasis = objectInput.readUTF();
        priceProtectionEndDate = objectInput.readLong();
        priceProtectionStartDate = objectInput.readLong();
        actualPriceCap = objectInput.readDouble();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeLong(lastModifiedDate);
        objectOutput.writeBoolean(checkRecord);

        if (userGroup == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(userGroup);
        }

        objectOutput.writeInt(projectionDetailsSid);
        objectOutput.writeInt(userId);
        objectOutput.writeInt(sessionId);

        if (priceBasis == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(priceBasis);
        }

        objectOutput.writeLong(priceProtectionEndDate);
        objectOutput.writeLong(priceProtectionStartDate);
        objectOutput.writeDouble(actualPriceCap);
    }
}
