package com.stpl.app.model.impl;

import com.stpl.app.model.ChSalesProjectionMaster;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing ChSalesProjectionMaster in entity cache.
 *
 * @author
 * @see ChSalesProjectionMaster
 * @generated
 */
public class ChSalesProjectionMasterCacheModel implements CacheModel<ChSalesProjectionMaster>,
    Externalizable {
    public boolean checkRecord;
    public String calculationPeriods;
    public int projectionDetailsSid;
    public String methodology;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(9);

        sb.append("{checkRecord=");
        sb.append(checkRecord);
        sb.append(", calculationPeriods=");
        sb.append(calculationPeriods);
        sb.append(", projectionDetailsSid=");
        sb.append(projectionDetailsSid);
        sb.append(", methodology=");
        sb.append(methodology);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public ChSalesProjectionMaster toEntityModel() {
        ChSalesProjectionMasterImpl chSalesProjectionMasterImpl = new ChSalesProjectionMasterImpl();

        chSalesProjectionMasterImpl.setCheckRecord(checkRecord);

        if (calculationPeriods == null) {
            chSalesProjectionMasterImpl.setCalculationPeriods(StringPool.BLANK);
        } else {
            chSalesProjectionMasterImpl.setCalculationPeriods(calculationPeriods);
        }

        chSalesProjectionMasterImpl.setProjectionDetailsSid(projectionDetailsSid);

        if (methodology == null) {
            chSalesProjectionMasterImpl.setMethodology(StringPool.BLANK);
        } else {
            chSalesProjectionMasterImpl.setMethodology(methodology);
        }

        chSalesProjectionMasterImpl.resetOriginalValues();

        return chSalesProjectionMasterImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        checkRecord = objectInput.readBoolean();
        calculationPeriods = objectInput.readUTF();
        projectionDetailsSid = objectInput.readInt();
        methodology = objectInput.readUTF();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeBoolean(checkRecord);

        if (calculationPeriods == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(calculationPeriods);
        }

        objectOutput.writeInt(projectionDetailsSid);

        if (methodology == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(methodology);
        }
    }
}
