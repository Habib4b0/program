package com.stpl.app.model.impl;

import com.stpl.app.model.StNmDiscountProjMaster;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing StNmDiscountProjMaster in entity cache.
 *
 * @author
 * @see StNmDiscountProjMaster
 * @generated
 */
public class StNmDiscountProjMasterCacheModel implements CacheModel<StNmDiscountProjMaster>,
    Externalizable {
    public String selectedPeriods;
    public String methodology;
    public String netFlag;
    public String priceGroupType;
    public String userGroup;
    public int userId;
    public long lastModifiedDate;
    public int projectionDetailsSid;
    public int rsModelSid;
    public int sessionId;
    public boolean checkRecord;
    public String baselinePeriods;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(25);

        sb.append("{selectedPeriods=");
        sb.append(selectedPeriods);
        sb.append(", methodology=");
        sb.append(methodology);
        sb.append(", netFlag=");
        sb.append(netFlag);
        sb.append(", priceGroupType=");
        sb.append(priceGroupType);
        sb.append(", userGroup=");
        sb.append(userGroup);
        sb.append(", userId=");
        sb.append(userId);
        sb.append(", lastModifiedDate=");
        sb.append(lastModifiedDate);
        sb.append(", projectionDetailsSid=");
        sb.append(projectionDetailsSid);
        sb.append(", rsModelSid=");
        sb.append(rsModelSid);
        sb.append(", sessionId=");
        sb.append(sessionId);
        sb.append(", checkRecord=");
        sb.append(checkRecord);
        sb.append(", baselinePeriods=");
        sb.append(baselinePeriods);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public StNmDiscountProjMaster toEntityModel() {
        StNmDiscountProjMasterImpl stNmDiscountProjMasterImpl = new StNmDiscountProjMasterImpl();

        if (selectedPeriods == null) {
            stNmDiscountProjMasterImpl.setSelectedPeriods(StringPool.BLANK);
        } else {
            stNmDiscountProjMasterImpl.setSelectedPeriods(selectedPeriods);
        }

        if (methodology == null) {
            stNmDiscountProjMasterImpl.setMethodology(StringPool.BLANK);
        } else {
            stNmDiscountProjMasterImpl.setMethodology(methodology);
        }

        if (netFlag == null) {
            stNmDiscountProjMasterImpl.setNetFlag(StringPool.BLANK);
        } else {
            stNmDiscountProjMasterImpl.setNetFlag(netFlag);
        }

        if (priceGroupType == null) {
            stNmDiscountProjMasterImpl.setPriceGroupType(StringPool.BLANK);
        } else {
            stNmDiscountProjMasterImpl.setPriceGroupType(priceGroupType);
        }

        if (userGroup == null) {
            stNmDiscountProjMasterImpl.setUserGroup(StringPool.BLANK);
        } else {
            stNmDiscountProjMasterImpl.setUserGroup(userGroup);
        }

        stNmDiscountProjMasterImpl.setUserId(userId);

        if (lastModifiedDate == Long.MIN_VALUE) {
            stNmDiscountProjMasterImpl.setLastModifiedDate(null);
        } else {
            stNmDiscountProjMasterImpl.setLastModifiedDate(new Date(
                    lastModifiedDate));
        }

        stNmDiscountProjMasterImpl.setProjectionDetailsSid(projectionDetailsSid);
        stNmDiscountProjMasterImpl.setRsModelSid(rsModelSid);
        stNmDiscountProjMasterImpl.setSessionId(sessionId);
        stNmDiscountProjMasterImpl.setCheckRecord(checkRecord);

        if (baselinePeriods == null) {
            stNmDiscountProjMasterImpl.setBaselinePeriods(StringPool.BLANK);
        } else {
            stNmDiscountProjMasterImpl.setBaselinePeriods(baselinePeriods);
        }

        stNmDiscountProjMasterImpl.resetOriginalValues();

        return stNmDiscountProjMasterImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        selectedPeriods = objectInput.readUTF();
        methodology = objectInput.readUTF();
        netFlag = objectInput.readUTF();
        priceGroupType = objectInput.readUTF();
        userGroup = objectInput.readUTF();
        userId = objectInput.readInt();
        lastModifiedDate = objectInput.readLong();
        projectionDetailsSid = objectInput.readInt();
        rsModelSid = objectInput.readInt();
        sessionId = objectInput.readInt();
        checkRecord = objectInput.readBoolean();
        baselinePeriods = objectInput.readUTF();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        if (selectedPeriods == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(selectedPeriods);
        }

        if (methodology == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(methodology);
        }

        if (netFlag == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(netFlag);
        }

        if (priceGroupType == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(priceGroupType);
        }

        if (userGroup == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(userGroup);
        }

        objectOutput.writeInt(userId);
        objectOutput.writeLong(lastModifiedDate);
        objectOutput.writeInt(projectionDetailsSid);
        objectOutput.writeInt(rsModelSid);
        objectOutput.writeInt(sessionId);
        objectOutput.writeBoolean(checkRecord);

        if (baselinePeriods == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(baselinePeriods);
        }
    }
}
