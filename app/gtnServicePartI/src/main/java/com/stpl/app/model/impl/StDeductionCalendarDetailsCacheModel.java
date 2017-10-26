package com.stpl.app.model.impl;

import com.stpl.app.model.StDeductionCalendarDetails;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing StDeductionCalendarDetails in entity cache.
 *
 * @author
 * @see StDeductionCalendarDetails
 * @generated
 */
public class StDeductionCalendarDetailsCacheModel implements CacheModel<StDeductionCalendarDetails>,
    Externalizable {
    public String adjustmentBasis;
    public int periodSid;
    public String adjustmentValue;
    public String adjustmentAllocationMethodology;
    public int companyMasterSid;
    public int discountAmount;
    public String adjustmentVariable;
    public int userId;
    public int itemMasterSid;
    public String adjustmentType;
    public String sessionId;
    public boolean checkRecord;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(25);

        sb.append("{adjustmentBasis=");
        sb.append(adjustmentBasis);
        sb.append(", periodSid=");
        sb.append(periodSid);
        sb.append(", adjustmentValue=");
        sb.append(adjustmentValue);
        sb.append(", adjustmentAllocationMethodology=");
        sb.append(adjustmentAllocationMethodology);
        sb.append(", companyMasterSid=");
        sb.append(companyMasterSid);
        sb.append(", discountAmount=");
        sb.append(discountAmount);
        sb.append(", adjustmentVariable=");
        sb.append(adjustmentVariable);
        sb.append(", userId=");
        sb.append(userId);
        sb.append(", itemMasterSid=");
        sb.append(itemMasterSid);
        sb.append(", adjustmentType=");
        sb.append(adjustmentType);
        sb.append(", sessionId=");
        sb.append(sessionId);
        sb.append(", checkRecord=");
        sb.append(checkRecord);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public StDeductionCalendarDetails toEntityModel() {
        StDeductionCalendarDetailsImpl stDeductionCalendarDetailsImpl = new StDeductionCalendarDetailsImpl();

        if (adjustmentBasis == null) {
            stDeductionCalendarDetailsImpl.setAdjustmentBasis(StringPool.BLANK);
        } else {
            stDeductionCalendarDetailsImpl.setAdjustmentBasis(adjustmentBasis);
        }

        stDeductionCalendarDetailsImpl.setPeriodSid(periodSid);

        if (adjustmentValue == null) {
            stDeductionCalendarDetailsImpl.setAdjustmentValue(StringPool.BLANK);
        } else {
            stDeductionCalendarDetailsImpl.setAdjustmentValue(adjustmentValue);
        }

        if (adjustmentAllocationMethodology == null) {
            stDeductionCalendarDetailsImpl.setAdjustmentAllocationMethodology(StringPool.BLANK);
        } else {
            stDeductionCalendarDetailsImpl.setAdjustmentAllocationMethodology(adjustmentAllocationMethodology);
        }

        stDeductionCalendarDetailsImpl.setCompanyMasterSid(companyMasterSid);
        stDeductionCalendarDetailsImpl.setDiscountAmount(discountAmount);

        if (adjustmentVariable == null) {
            stDeductionCalendarDetailsImpl.setAdjustmentVariable(StringPool.BLANK);
        } else {
            stDeductionCalendarDetailsImpl.setAdjustmentVariable(adjustmentVariable);
        }

        stDeductionCalendarDetailsImpl.setUserId(userId);
        stDeductionCalendarDetailsImpl.setItemMasterSid(itemMasterSid);

        if (adjustmentType == null) {
            stDeductionCalendarDetailsImpl.setAdjustmentType(StringPool.BLANK);
        } else {
            stDeductionCalendarDetailsImpl.setAdjustmentType(adjustmentType);
        }

        if (sessionId == null) {
            stDeductionCalendarDetailsImpl.setSessionId(StringPool.BLANK);
        } else {
            stDeductionCalendarDetailsImpl.setSessionId(sessionId);
        }

        stDeductionCalendarDetailsImpl.setCheckRecord(checkRecord);

        stDeductionCalendarDetailsImpl.resetOriginalValues();

        return stDeductionCalendarDetailsImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        adjustmentBasis = objectInput.readUTF();
        periodSid = objectInput.readInt();
        adjustmentValue = objectInput.readUTF();
        adjustmentAllocationMethodology = objectInput.readUTF();
        companyMasterSid = objectInput.readInt();
        discountAmount = objectInput.readInt();
        adjustmentVariable = objectInput.readUTF();
        userId = objectInput.readInt();
        itemMasterSid = objectInput.readInt();
        adjustmentType = objectInput.readUTF();
        sessionId = objectInput.readUTF();
        checkRecord = objectInput.readBoolean();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        if (adjustmentBasis == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(adjustmentBasis);
        }

        objectOutput.writeInt(periodSid);

        if (adjustmentValue == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(adjustmentValue);
        }

        if (adjustmentAllocationMethodology == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(adjustmentAllocationMethodology);
        }

        objectOutput.writeInt(companyMasterSid);
        objectOutput.writeInt(discountAmount);

        if (adjustmentVariable == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(adjustmentVariable);
        }

        objectOutput.writeInt(userId);
        objectOutput.writeInt(itemMasterSid);

        if (adjustmentType == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(adjustmentType);
        }

        if (sessionId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(sessionId);
        }

        objectOutput.writeBoolean(checkRecord);
    }
}
