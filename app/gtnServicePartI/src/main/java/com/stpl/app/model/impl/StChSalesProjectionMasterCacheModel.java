package com.stpl.app.model.impl;

import com.stpl.app.model.StChSalesProjectionMaster;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing StChSalesProjectionMaster in entity cache.
 *
 * @author
 * @see StChSalesProjectionMaster
 * @generated
 */
public class StChSalesProjectionMasterCacheModel implements CacheModel<StChSalesProjectionMaster>,
    Externalizable {
    public long lastModifiedDate;
    public boolean checkRecord;
    public String calculationPeriods;
    public int projectionDetailsSid;
    public int userId;
    public int sessionId;
    public String methodology;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(15);

        sb.append("{lastModifiedDate=");
        sb.append(lastModifiedDate);
        sb.append(", checkRecord=");
        sb.append(checkRecord);
        sb.append(", calculationPeriods=");
        sb.append(calculationPeriods);
        sb.append(", projectionDetailsSid=");
        sb.append(projectionDetailsSid);
        sb.append(", userId=");
        sb.append(userId);
        sb.append(", sessionId=");
        sb.append(sessionId);
        sb.append(", methodology=");
        sb.append(methodology);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public StChSalesProjectionMaster toEntityModel() {
        StChSalesProjectionMasterImpl stChSalesProjectionMasterImpl = new StChSalesProjectionMasterImpl();

        if (lastModifiedDate == Long.MIN_VALUE) {
            stChSalesProjectionMasterImpl.setLastModifiedDate(null);
        } else {
            stChSalesProjectionMasterImpl.setLastModifiedDate(new Date(
                    lastModifiedDate));
        }

        stChSalesProjectionMasterImpl.setCheckRecord(checkRecord);

        if (calculationPeriods == null) {
            stChSalesProjectionMasterImpl.setCalculationPeriods(StringPool.BLANK);
        } else {
            stChSalesProjectionMasterImpl.setCalculationPeriods(calculationPeriods);
        }

        stChSalesProjectionMasterImpl.setProjectionDetailsSid(projectionDetailsSid);
        stChSalesProjectionMasterImpl.setUserId(userId);
        stChSalesProjectionMasterImpl.setSessionId(sessionId);

        if (methodology == null) {
            stChSalesProjectionMasterImpl.setMethodology(StringPool.BLANK);
        } else {
            stChSalesProjectionMasterImpl.setMethodology(methodology);
        }

        stChSalesProjectionMasterImpl.resetOriginalValues();

        return stChSalesProjectionMasterImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        lastModifiedDate = objectInput.readLong();
        checkRecord = objectInput.readBoolean();
        calculationPeriods = objectInput.readUTF();
        projectionDetailsSid = objectInput.readInt();
        userId = objectInput.readInt();
        sessionId = objectInput.readInt();
        methodology = objectInput.readUTF();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeLong(lastModifiedDate);
        objectOutput.writeBoolean(checkRecord);

        if (calculationPeriods == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(calculationPeriods);
        }

        objectOutput.writeInt(projectionDetailsSid);
        objectOutput.writeInt(userId);
        objectOutput.writeInt(sessionId);

        if (methodology == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(methodology);
        }
    }
}
