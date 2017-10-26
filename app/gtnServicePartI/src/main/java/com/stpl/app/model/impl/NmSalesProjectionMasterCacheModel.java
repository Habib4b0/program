package com.stpl.app.model.impl;

import com.stpl.app.model.NmSalesProjectionMaster;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing NmSalesProjectionMaster in entity cache.
 *
 * @author
 * @see NmSalesProjectionMaster
 * @generated
 */
public class NmSalesProjectionMasterCacheModel implements CacheModel<NmSalesProjectionMaster>,
    Externalizable {
    public boolean checkRecord;
    public String calculationPeriods;
    public String userGroup;
    public int projectionDetailsSid;
    public String methodology;
    public String calculationBased;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(13);

        sb.append("{checkRecord=");
        sb.append(checkRecord);
        sb.append(", calculationPeriods=");
        sb.append(calculationPeriods);
        sb.append(", userGroup=");
        sb.append(userGroup);
        sb.append(", projectionDetailsSid=");
        sb.append(projectionDetailsSid);
        sb.append(", methodology=");
        sb.append(methodology);
        sb.append(", calculationBased=");
        sb.append(calculationBased);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public NmSalesProjectionMaster toEntityModel() {
        NmSalesProjectionMasterImpl nmSalesProjectionMasterImpl = new NmSalesProjectionMasterImpl();

        nmSalesProjectionMasterImpl.setCheckRecord(checkRecord);

        if (calculationPeriods == null) {
            nmSalesProjectionMasterImpl.setCalculationPeriods(StringPool.BLANK);
        } else {
            nmSalesProjectionMasterImpl.setCalculationPeriods(calculationPeriods);
        }

        if (userGroup == null) {
            nmSalesProjectionMasterImpl.setUserGroup(StringPool.BLANK);
        } else {
            nmSalesProjectionMasterImpl.setUserGroup(userGroup);
        }

        nmSalesProjectionMasterImpl.setProjectionDetailsSid(projectionDetailsSid);

        if (methodology == null) {
            nmSalesProjectionMasterImpl.setMethodology(StringPool.BLANK);
        } else {
            nmSalesProjectionMasterImpl.setMethodology(methodology);
        }

        if (calculationBased == null) {
            nmSalesProjectionMasterImpl.setCalculationBased(StringPool.BLANK);
        } else {
            nmSalesProjectionMasterImpl.setCalculationBased(calculationBased);
        }

        nmSalesProjectionMasterImpl.resetOriginalValues();

        return nmSalesProjectionMasterImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        checkRecord = objectInput.readBoolean();
        calculationPeriods = objectInput.readUTF();
        userGroup = objectInput.readUTF();
        projectionDetailsSid = objectInput.readInt();
        methodology = objectInput.readUTF();
        calculationBased = objectInput.readUTF();
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

        if (userGroup == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(userGroup);
        }

        objectOutput.writeInt(projectionDetailsSid);

        if (methodology == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(methodology);
        }

        if (calculationBased == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(calculationBased);
        }
    }
}
