package com.stpl.app.model.impl;

import com.stpl.app.model.StChDiscountProjMaster;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing StChDiscountProjMaster in entity cache.
 *
 * @author
 * @see StChDiscountProjMaster
 * @generated
 */
public class StChDiscountProjMasterCacheModel implements CacheModel<StChDiscountProjMaster>,
    Externalizable {
    public boolean checkRecord;
    public String selectedPeriods;
    public long lastModifiedDate;
    public int projectionDetailsSid;
    public String priceGroupType;
    public int userId;
    public String netFlag;
    public String projectedType;
    public String baselinePeriods;
    public int sessionId;
    public String methodology;
    public int rsModelSid;
    public String discountType;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(27);

        sb.append("{checkRecord=");
        sb.append(checkRecord);
        sb.append(", selectedPeriods=");
        sb.append(selectedPeriods);
        sb.append(", lastModifiedDate=");
        sb.append(lastModifiedDate);
        sb.append(", projectionDetailsSid=");
        sb.append(projectionDetailsSid);
        sb.append(", priceGroupType=");
        sb.append(priceGroupType);
        sb.append(", userId=");
        sb.append(userId);
        sb.append(", netFlag=");
        sb.append(netFlag);
        sb.append(", projectedType=");
        sb.append(projectedType);
        sb.append(", baselinePeriods=");
        sb.append(baselinePeriods);
        sb.append(", sessionId=");
        sb.append(sessionId);
        sb.append(", methodology=");
        sb.append(methodology);
        sb.append(", rsModelSid=");
        sb.append(rsModelSid);
        sb.append(", discountType=");
        sb.append(discountType);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public StChDiscountProjMaster toEntityModel() {
        StChDiscountProjMasterImpl stChDiscountProjMasterImpl = new StChDiscountProjMasterImpl();

        stChDiscountProjMasterImpl.setCheckRecord(checkRecord);

        if (selectedPeriods == null) {
            stChDiscountProjMasterImpl.setSelectedPeriods(StringPool.BLANK);
        } else {
            stChDiscountProjMasterImpl.setSelectedPeriods(selectedPeriods);
        }

        if (lastModifiedDate == Long.MIN_VALUE) {
            stChDiscountProjMasterImpl.setLastModifiedDate(null);
        } else {
            stChDiscountProjMasterImpl.setLastModifiedDate(new Date(
                    lastModifiedDate));
        }

        stChDiscountProjMasterImpl.setProjectionDetailsSid(projectionDetailsSid);

        if (priceGroupType == null) {
            stChDiscountProjMasterImpl.setPriceGroupType(StringPool.BLANK);
        } else {
            stChDiscountProjMasterImpl.setPriceGroupType(priceGroupType);
        }

        stChDiscountProjMasterImpl.setUserId(userId);

        if (netFlag == null) {
            stChDiscountProjMasterImpl.setNetFlag(StringPool.BLANK);
        } else {
            stChDiscountProjMasterImpl.setNetFlag(netFlag);
        }

        if (projectedType == null) {
            stChDiscountProjMasterImpl.setProjectedType(StringPool.BLANK);
        } else {
            stChDiscountProjMasterImpl.setProjectedType(projectedType);
        }

        if (baselinePeriods == null) {
            stChDiscountProjMasterImpl.setBaselinePeriods(StringPool.BLANK);
        } else {
            stChDiscountProjMasterImpl.setBaselinePeriods(baselinePeriods);
        }

        stChDiscountProjMasterImpl.setSessionId(sessionId);

        if (methodology == null) {
            stChDiscountProjMasterImpl.setMethodology(StringPool.BLANK);
        } else {
            stChDiscountProjMasterImpl.setMethodology(methodology);
        }

        stChDiscountProjMasterImpl.setRsModelSid(rsModelSid);

        if (discountType == null) {
            stChDiscountProjMasterImpl.setDiscountType(StringPool.BLANK);
        } else {
            stChDiscountProjMasterImpl.setDiscountType(discountType);
        }

        stChDiscountProjMasterImpl.resetOriginalValues();

        return stChDiscountProjMasterImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        checkRecord = objectInput.readBoolean();
        selectedPeriods = objectInput.readUTF();
        lastModifiedDate = objectInput.readLong();
        projectionDetailsSid = objectInput.readInt();
        priceGroupType = objectInput.readUTF();
        userId = objectInput.readInt();
        netFlag = objectInput.readUTF();
        projectedType = objectInput.readUTF();
        baselinePeriods = objectInput.readUTF();
        sessionId = objectInput.readInt();
        methodology = objectInput.readUTF();
        rsModelSid = objectInput.readInt();
        discountType = objectInput.readUTF();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeBoolean(checkRecord);

        if (selectedPeriods == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(selectedPeriods);
        }

        objectOutput.writeLong(lastModifiedDate);
        objectOutput.writeInt(projectionDetailsSid);

        if (priceGroupType == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(priceGroupType);
        }

        objectOutput.writeInt(userId);

        if (netFlag == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(netFlag);
        }

        if (projectedType == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(projectedType);
        }

        if (baselinePeriods == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(baselinePeriods);
        }

        objectOutput.writeInt(sessionId);

        if (methodology == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(methodology);
        }

        objectOutput.writeInt(rsModelSid);

        if (discountType == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(discountType);
        }
    }
}
