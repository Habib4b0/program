package com.stpl.app.parttwo.model.impl;

import com.stpl.app.parttwo.model.CffApprovalDetails;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing CffApprovalDetails in entity cache.
 *
 * @author
 * @see CffApprovalDetails
 * @generated
 */
public class CffApprovalDetailsCacheModel implements CacheModel<CffApprovalDetails>,
    Externalizable {
    public int approvalSequence;
    public long approvedDate;
    public int approvedBy;
    public int approvalStatus;
    public int cffMasterSid;
    public String inboundStatus;
    public int cffApprovalDetailsSid;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(15);

        sb.append("{approvalSequence=");
        sb.append(approvalSequence);
        sb.append(", approvedDate=");
        sb.append(approvedDate);
        sb.append(", approvedBy=");
        sb.append(approvedBy);
        sb.append(", approvalStatus=");
        sb.append(approvalStatus);
        sb.append(", cffMasterSid=");
        sb.append(cffMasterSid);
        sb.append(", inboundStatus=");
        sb.append(inboundStatus);
        sb.append(", cffApprovalDetailsSid=");
        sb.append(cffApprovalDetailsSid);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public CffApprovalDetails toEntityModel() {
        CffApprovalDetailsImpl cffApprovalDetailsImpl = new CffApprovalDetailsImpl();

        cffApprovalDetailsImpl.setApprovalSequence(approvalSequence);

        if (approvedDate == Long.MIN_VALUE) {
            cffApprovalDetailsImpl.setApprovedDate(null);
        } else {
            cffApprovalDetailsImpl.setApprovedDate(new Date(approvedDate));
        }

        cffApprovalDetailsImpl.setApprovedBy(approvedBy);
        cffApprovalDetailsImpl.setApprovalStatus(approvalStatus);
        cffApprovalDetailsImpl.setCffMasterSid(cffMasterSid);

        if (inboundStatus == null) {
            cffApprovalDetailsImpl.setInboundStatus(StringPool.BLANK);
        } else {
            cffApprovalDetailsImpl.setInboundStatus(inboundStatus);
        }

        cffApprovalDetailsImpl.setCffApprovalDetailsSid(cffApprovalDetailsSid);

        cffApprovalDetailsImpl.resetOriginalValues();

        return cffApprovalDetailsImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        approvalSequence = objectInput.readInt();
        approvedDate = objectInput.readLong();
        approvedBy = objectInput.readInt();
        approvalStatus = objectInput.readInt();
        cffMasterSid = objectInput.readInt();
        inboundStatus = objectInput.readUTF();
        cffApprovalDetailsSid = objectInput.readInt();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeInt(approvalSequence);
        objectOutput.writeLong(approvedDate);
        objectOutput.writeInt(approvedBy);
        objectOutput.writeInt(approvalStatus);
        objectOutput.writeInt(cffMasterSid);

        if (inboundStatus == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(inboundStatus);
        }

        objectOutput.writeInt(cffApprovalDetailsSid);
    }
}
