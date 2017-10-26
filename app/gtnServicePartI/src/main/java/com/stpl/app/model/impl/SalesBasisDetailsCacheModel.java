package com.stpl.app.model.impl;

import com.stpl.app.model.SalesBasisDetails;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing SalesBasisDetails in entity cache.
 *
 * @author
 * @see SalesBasisDetails
 * @generated
 */
public class SalesBasisDetailsCacheModel implements CacheModel<SalesBasisDetails>,
    Externalizable {
    public int createdBy;
    public int netSalesFormulaMasterSid;
    public boolean recordLockStatus;
    public int modifiedBy;
    public long createdDate;
    public int contractMasterSid;
    public String source;
    public int cdrModelSid;
    public int salesBasisDetailsSid;
    public int cfpContractDetailsSid;
    public long modifiedDate;
    public String inboundStatus;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(25);

        sb.append("{createdBy=");
        sb.append(createdBy);
        sb.append(", netSalesFormulaMasterSid=");
        sb.append(netSalesFormulaMasterSid);
        sb.append(", recordLockStatus=");
        sb.append(recordLockStatus);
        sb.append(", modifiedBy=");
        sb.append(modifiedBy);
        sb.append(", createdDate=");
        sb.append(createdDate);
        sb.append(", contractMasterSid=");
        sb.append(contractMasterSid);
        sb.append(", source=");
        sb.append(source);
        sb.append(", cdrModelSid=");
        sb.append(cdrModelSid);
        sb.append(", salesBasisDetailsSid=");
        sb.append(salesBasisDetailsSid);
        sb.append(", cfpContractDetailsSid=");
        sb.append(cfpContractDetailsSid);
        sb.append(", modifiedDate=");
        sb.append(modifiedDate);
        sb.append(", inboundStatus=");
        sb.append(inboundStatus);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public SalesBasisDetails toEntityModel() {
        SalesBasisDetailsImpl salesBasisDetailsImpl = new SalesBasisDetailsImpl();

        salesBasisDetailsImpl.setCreatedBy(createdBy);
        salesBasisDetailsImpl.setNetSalesFormulaMasterSid(netSalesFormulaMasterSid);
        salesBasisDetailsImpl.setRecordLockStatus(recordLockStatus);
        salesBasisDetailsImpl.setModifiedBy(modifiedBy);

        if (createdDate == Long.MIN_VALUE) {
            salesBasisDetailsImpl.setCreatedDate(null);
        } else {
            salesBasisDetailsImpl.setCreatedDate(new Date(createdDate));
        }

        salesBasisDetailsImpl.setContractMasterSid(contractMasterSid);

        if (source == null) {
            salesBasisDetailsImpl.setSource(StringPool.BLANK);
        } else {
            salesBasisDetailsImpl.setSource(source);
        }

        salesBasisDetailsImpl.setCdrModelSid(cdrModelSid);
        salesBasisDetailsImpl.setSalesBasisDetailsSid(salesBasisDetailsSid);
        salesBasisDetailsImpl.setCfpContractDetailsSid(cfpContractDetailsSid);

        if (modifiedDate == Long.MIN_VALUE) {
            salesBasisDetailsImpl.setModifiedDate(null);
        } else {
            salesBasisDetailsImpl.setModifiedDate(new Date(modifiedDate));
        }

        if (inboundStatus == null) {
            salesBasisDetailsImpl.setInboundStatus(StringPool.BLANK);
        } else {
            salesBasisDetailsImpl.setInboundStatus(inboundStatus);
        }

        salesBasisDetailsImpl.resetOriginalValues();

        return salesBasisDetailsImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        createdBy = objectInput.readInt();
        netSalesFormulaMasterSid = objectInput.readInt();
        recordLockStatus = objectInput.readBoolean();
        modifiedBy = objectInput.readInt();
        createdDate = objectInput.readLong();
        contractMasterSid = objectInput.readInt();
        source = objectInput.readUTF();
        cdrModelSid = objectInput.readInt();
        salesBasisDetailsSid = objectInput.readInt();
        cfpContractDetailsSid = objectInput.readInt();
        modifiedDate = objectInput.readLong();
        inboundStatus = objectInput.readUTF();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeInt(createdBy);
        objectOutput.writeInt(netSalesFormulaMasterSid);
        objectOutput.writeBoolean(recordLockStatus);
        objectOutput.writeInt(modifiedBy);
        objectOutput.writeLong(createdDate);
        objectOutput.writeInt(contractMasterSid);

        if (source == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(source);
        }

        objectOutput.writeInt(cdrModelSid);
        objectOutput.writeInt(salesBasisDetailsSid);
        objectOutput.writeInt(cfpContractDetailsSid);
        objectOutput.writeLong(modifiedDate);

        if (inboundStatus == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(inboundStatus);
        }
    }
}
