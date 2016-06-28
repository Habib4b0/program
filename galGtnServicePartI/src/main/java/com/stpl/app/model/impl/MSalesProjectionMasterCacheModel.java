package com.stpl.app.model.impl;

import com.stpl.app.model.MSalesProjectionMaster;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing MSalesProjectionMaster in entity cache.
 *
 * @author
 * @see MSalesProjectionMaster
 * @generated
 */
public class MSalesProjectionMasterCacheModel implements CacheModel<MSalesProjectionMaster>,
    Externalizable {
    public String methodology;
    public String calculationPeriods;
    public String calculationBased;
    public int projectionDetailsSid;
    public boolean checkRecord;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(11);

        sb.append("{methodology=");
        sb.append(methodology);
        sb.append(", calculationPeriods=");
        sb.append(calculationPeriods);
        sb.append(", calculationBased=");
        sb.append(calculationBased);
        sb.append(", projectionDetailsSid=");
        sb.append(projectionDetailsSid);
        sb.append(", checkRecord=");
        sb.append(checkRecord);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public MSalesProjectionMaster toEntityModel() {
        MSalesProjectionMasterImpl mSalesProjectionMasterImpl = new MSalesProjectionMasterImpl();

        if (methodology == null) {
            mSalesProjectionMasterImpl.setMethodology(StringPool.BLANK);
        } else {
            mSalesProjectionMasterImpl.setMethodology(methodology);
        }

        if (calculationPeriods == null) {
            mSalesProjectionMasterImpl.setCalculationPeriods(StringPool.BLANK);
        } else {
            mSalesProjectionMasterImpl.setCalculationPeriods(calculationPeriods);
        }

        if (calculationBased == null) {
            mSalesProjectionMasterImpl.setCalculationBased(StringPool.BLANK);
        } else {
            mSalesProjectionMasterImpl.setCalculationBased(calculationBased);
        }

        mSalesProjectionMasterImpl.setProjectionDetailsSid(projectionDetailsSid);
        mSalesProjectionMasterImpl.setCheckRecord(checkRecord);

        mSalesProjectionMasterImpl.resetOriginalValues();

        return mSalesProjectionMasterImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        methodology = objectInput.readUTF();
        calculationPeriods = objectInput.readUTF();
        calculationBased = objectInput.readUTF();
        projectionDetailsSid = objectInput.readInt();
        checkRecord = objectInput.readBoolean();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        if (methodology == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(methodology);
        }

        if (calculationPeriods == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(calculationPeriods);
        }

        if (calculationBased == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(calculationBased);
        }

        objectOutput.writeInt(projectionDetailsSid);
        objectOutput.writeBoolean(checkRecord);
    }
}
