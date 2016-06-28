package com.stpl.app.model.impl;

import com.stpl.app.model.MasterDataAttribute;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing MasterDataAttribute in entity cache.
 *
 * @author
 * @see MasterDataAttribute
 * @generated
 */
public class MasterDataAttributeCacheModel implements CacheModel<MasterDataAttribute>,
    Externalizable {
    public String column15;
    public String column14;
    public String column17;
    public String column16;
    public String column11;
    public int modifiedBy;
    public String column10;
    public long createdDate;
    public String column13;
    public String column12;
    public String batchId;
    public String column19;
    public String column18;
    public String masterAttribute;
    public int createdBy;
    public String masterType;
    public String masterId;
    public String column20;
    public String column9;
    public long modifiedDate;
    public String column6;
    public int masterDataAttributeSid;
    public String column5;
    public String column8;
    public String column7;
    public boolean recordLockStatus;
    public String column2;
    public String column1;
    public String column4;
    public String column3;
    public String source;
    public String inboundStatus;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(65);

        sb.append("{column15=");
        sb.append(column15);
        sb.append(", column14=");
        sb.append(column14);
        sb.append(", column17=");
        sb.append(column17);
        sb.append(", column16=");
        sb.append(column16);
        sb.append(", column11=");
        sb.append(column11);
        sb.append(", modifiedBy=");
        sb.append(modifiedBy);
        sb.append(", column10=");
        sb.append(column10);
        sb.append(", createdDate=");
        sb.append(createdDate);
        sb.append(", column13=");
        sb.append(column13);
        sb.append(", column12=");
        sb.append(column12);
        sb.append(", batchId=");
        sb.append(batchId);
        sb.append(", column19=");
        sb.append(column19);
        sb.append(", column18=");
        sb.append(column18);
        sb.append(", masterAttribute=");
        sb.append(masterAttribute);
        sb.append(", createdBy=");
        sb.append(createdBy);
        sb.append(", masterType=");
        sb.append(masterType);
        sb.append(", masterId=");
        sb.append(masterId);
        sb.append(", column20=");
        sb.append(column20);
        sb.append(", column9=");
        sb.append(column9);
        sb.append(", modifiedDate=");
        sb.append(modifiedDate);
        sb.append(", column6=");
        sb.append(column6);
        sb.append(", masterDataAttributeSid=");
        sb.append(masterDataAttributeSid);
        sb.append(", column5=");
        sb.append(column5);
        sb.append(", column8=");
        sb.append(column8);
        sb.append(", column7=");
        sb.append(column7);
        sb.append(", recordLockStatus=");
        sb.append(recordLockStatus);
        sb.append(", column2=");
        sb.append(column2);
        sb.append(", column1=");
        sb.append(column1);
        sb.append(", column4=");
        sb.append(column4);
        sb.append(", column3=");
        sb.append(column3);
        sb.append(", source=");
        sb.append(source);
        sb.append(", inboundStatus=");
        sb.append(inboundStatus);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public MasterDataAttribute toEntityModel() {
        MasterDataAttributeImpl masterDataAttributeImpl = new MasterDataAttributeImpl();

        if (column15 == null) {
            masterDataAttributeImpl.setColumn15(StringPool.BLANK);
        } else {
            masterDataAttributeImpl.setColumn15(column15);
        }

        if (column14 == null) {
            masterDataAttributeImpl.setColumn14(StringPool.BLANK);
        } else {
            masterDataAttributeImpl.setColumn14(column14);
        }

        if (column17 == null) {
            masterDataAttributeImpl.setColumn17(StringPool.BLANK);
        } else {
            masterDataAttributeImpl.setColumn17(column17);
        }

        if (column16 == null) {
            masterDataAttributeImpl.setColumn16(StringPool.BLANK);
        } else {
            masterDataAttributeImpl.setColumn16(column16);
        }

        if (column11 == null) {
            masterDataAttributeImpl.setColumn11(StringPool.BLANK);
        } else {
            masterDataAttributeImpl.setColumn11(column11);
        }

        masterDataAttributeImpl.setModifiedBy(modifiedBy);

        if (column10 == null) {
            masterDataAttributeImpl.setColumn10(StringPool.BLANK);
        } else {
            masterDataAttributeImpl.setColumn10(column10);
        }

        if (createdDate == Long.MIN_VALUE) {
            masterDataAttributeImpl.setCreatedDate(null);
        } else {
            masterDataAttributeImpl.setCreatedDate(new Date(createdDate));
        }

        if (column13 == null) {
            masterDataAttributeImpl.setColumn13(StringPool.BLANK);
        } else {
            masterDataAttributeImpl.setColumn13(column13);
        }

        if (column12 == null) {
            masterDataAttributeImpl.setColumn12(StringPool.BLANK);
        } else {
            masterDataAttributeImpl.setColumn12(column12);
        }

        if (batchId == null) {
            masterDataAttributeImpl.setBatchId(StringPool.BLANK);
        } else {
            masterDataAttributeImpl.setBatchId(batchId);
        }

        if (column19 == null) {
            masterDataAttributeImpl.setColumn19(StringPool.BLANK);
        } else {
            masterDataAttributeImpl.setColumn19(column19);
        }

        if (column18 == null) {
            masterDataAttributeImpl.setColumn18(StringPool.BLANK);
        } else {
            masterDataAttributeImpl.setColumn18(column18);
        }

        if (masterAttribute == null) {
            masterDataAttributeImpl.setMasterAttribute(StringPool.BLANK);
        } else {
            masterDataAttributeImpl.setMasterAttribute(masterAttribute);
        }

        masterDataAttributeImpl.setCreatedBy(createdBy);

        if (masterType == null) {
            masterDataAttributeImpl.setMasterType(StringPool.BLANK);
        } else {
            masterDataAttributeImpl.setMasterType(masterType);
        }

        if (masterId == null) {
            masterDataAttributeImpl.setMasterId(StringPool.BLANK);
        } else {
            masterDataAttributeImpl.setMasterId(masterId);
        }

        if (column20 == null) {
            masterDataAttributeImpl.setColumn20(StringPool.BLANK);
        } else {
            masterDataAttributeImpl.setColumn20(column20);
        }

        if (column9 == null) {
            masterDataAttributeImpl.setColumn9(StringPool.BLANK);
        } else {
            masterDataAttributeImpl.setColumn9(column9);
        }

        if (modifiedDate == Long.MIN_VALUE) {
            masterDataAttributeImpl.setModifiedDate(null);
        } else {
            masterDataAttributeImpl.setModifiedDate(new Date(modifiedDate));
        }

        if (column6 == null) {
            masterDataAttributeImpl.setColumn6(StringPool.BLANK);
        } else {
            masterDataAttributeImpl.setColumn6(column6);
        }

        masterDataAttributeImpl.setMasterDataAttributeSid(masterDataAttributeSid);

        if (column5 == null) {
            masterDataAttributeImpl.setColumn5(StringPool.BLANK);
        } else {
            masterDataAttributeImpl.setColumn5(column5);
        }

        if (column8 == null) {
            masterDataAttributeImpl.setColumn8(StringPool.BLANK);
        } else {
            masterDataAttributeImpl.setColumn8(column8);
        }

        if (column7 == null) {
            masterDataAttributeImpl.setColumn7(StringPool.BLANK);
        } else {
            masterDataAttributeImpl.setColumn7(column7);
        }

        masterDataAttributeImpl.setRecordLockStatus(recordLockStatus);

        if (column2 == null) {
            masterDataAttributeImpl.setColumn2(StringPool.BLANK);
        } else {
            masterDataAttributeImpl.setColumn2(column2);
        }

        if (column1 == null) {
            masterDataAttributeImpl.setColumn1(StringPool.BLANK);
        } else {
            masterDataAttributeImpl.setColumn1(column1);
        }

        if (column4 == null) {
            masterDataAttributeImpl.setColumn4(StringPool.BLANK);
        } else {
            masterDataAttributeImpl.setColumn4(column4);
        }

        if (column3 == null) {
            masterDataAttributeImpl.setColumn3(StringPool.BLANK);
        } else {
            masterDataAttributeImpl.setColumn3(column3);
        }

        if (source == null) {
            masterDataAttributeImpl.setSource(StringPool.BLANK);
        } else {
            masterDataAttributeImpl.setSource(source);
        }

        if (inboundStatus == null) {
            masterDataAttributeImpl.setInboundStatus(StringPool.BLANK);
        } else {
            masterDataAttributeImpl.setInboundStatus(inboundStatus);
        }

        masterDataAttributeImpl.resetOriginalValues();

        return masterDataAttributeImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        column15 = objectInput.readUTF();
        column14 = objectInput.readUTF();
        column17 = objectInput.readUTF();
        column16 = objectInput.readUTF();
        column11 = objectInput.readUTF();
        modifiedBy = objectInput.readInt();
        column10 = objectInput.readUTF();
        createdDate = objectInput.readLong();
        column13 = objectInput.readUTF();
        column12 = objectInput.readUTF();
        batchId = objectInput.readUTF();
        column19 = objectInput.readUTF();
        column18 = objectInput.readUTF();
        masterAttribute = objectInput.readUTF();
        createdBy = objectInput.readInt();
        masterType = objectInput.readUTF();
        masterId = objectInput.readUTF();
        column20 = objectInput.readUTF();
        column9 = objectInput.readUTF();
        modifiedDate = objectInput.readLong();
        column6 = objectInput.readUTF();
        masterDataAttributeSid = objectInput.readInt();
        column5 = objectInput.readUTF();
        column8 = objectInput.readUTF();
        column7 = objectInput.readUTF();
        recordLockStatus = objectInput.readBoolean();
        column2 = objectInput.readUTF();
        column1 = objectInput.readUTF();
        column4 = objectInput.readUTF();
        column3 = objectInput.readUTF();
        source = objectInput.readUTF();
        inboundStatus = objectInput.readUTF();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        if (column15 == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(column15);
        }

        if (column14 == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(column14);
        }

        if (column17 == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(column17);
        }

        if (column16 == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(column16);
        }

        if (column11 == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(column11);
        }

        objectOutput.writeInt(modifiedBy);

        if (column10 == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(column10);
        }

        objectOutput.writeLong(createdDate);

        if (column13 == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(column13);
        }

        if (column12 == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(column12);
        }

        if (batchId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(batchId);
        }

        if (column19 == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(column19);
        }

        if (column18 == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(column18);
        }

        if (masterAttribute == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(masterAttribute);
        }

        objectOutput.writeInt(createdBy);

        if (masterType == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(masterType);
        }

        if (masterId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(masterId);
        }

        if (column20 == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(column20);
        }

        if (column9 == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(column9);
        }

        objectOutput.writeLong(modifiedDate);

        if (column6 == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(column6);
        }

        objectOutput.writeInt(masterDataAttributeSid);

        if (column5 == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(column5);
        }

        if (column8 == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(column8);
        }

        if (column7 == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(column7);
        }

        objectOutput.writeBoolean(recordLockStatus);

        if (column2 == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(column2);
        }

        if (column1 == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(column1);
        }

        if (column4 == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(column4);
        }

        if (column3 == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(column3);
        }

        if (source == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(source);
        }

        if (inboundStatus == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(inboundStatus);
        }
    }
}
