package com.stpl.app.model.impl;

import com.stpl.app.model.IvldMasterDataAttribute;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing IvldMasterDataAttribute in entity cache.
 *
 * @author
 * @see IvldMasterDataAttribute
 * @generated
 */
public class IvldMasterDataAttributeCacheModel implements CacheModel<IvldMasterDataAttribute>,
    Externalizable {
    public String column19;
    public String column18;
    public String masterAttribute;
    public String masterType;
    public String dataAttributeIntfid;
    public long modifiedDate;
    public String source;
    public long createdDate;
    public String createdBy;
    public String addChgDelIndicator;
    public String masterId;
    public String column10;
    public String column11;
    public String errorCode;
    public String column12;
    public long intfInsertedDate;
    public String modifiedBy;
    public String column13;
    public String column14;
    public String reprocessedFlag;
    public String column15;
    public String column16;
    public String column17;
    public String column4;
    public String column3;
    public String column2;
    public String column1;
    public String column8;
    public String reasonForFailure;
    public String column7;
    public String column6;
    public String column5;
    public String column20;
    public String batchId;
    public String errorField;
    public String column9;
    public int ivldMasterDataAtbteSid;
    public boolean checkRecord;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(77);

        sb.append("{column19=");
        sb.append(column19);
        sb.append(", column18=");
        sb.append(column18);
        sb.append(", masterAttribute=");
        sb.append(masterAttribute);
        sb.append(", masterType=");
        sb.append(masterType);
        sb.append(", dataAttributeIntfid=");
        sb.append(dataAttributeIntfid);
        sb.append(", modifiedDate=");
        sb.append(modifiedDate);
        sb.append(", source=");
        sb.append(source);
        sb.append(", createdDate=");
        sb.append(createdDate);
        sb.append(", createdBy=");
        sb.append(createdBy);
        sb.append(", addChgDelIndicator=");
        sb.append(addChgDelIndicator);
        sb.append(", masterId=");
        sb.append(masterId);
        sb.append(", column10=");
        sb.append(column10);
        sb.append(", column11=");
        sb.append(column11);
        sb.append(", errorCode=");
        sb.append(errorCode);
        sb.append(", column12=");
        sb.append(column12);
        sb.append(", intfInsertedDate=");
        sb.append(intfInsertedDate);
        sb.append(", modifiedBy=");
        sb.append(modifiedBy);
        sb.append(", column13=");
        sb.append(column13);
        sb.append(", column14=");
        sb.append(column14);
        sb.append(", reprocessedFlag=");
        sb.append(reprocessedFlag);
        sb.append(", column15=");
        sb.append(column15);
        sb.append(", column16=");
        sb.append(column16);
        sb.append(", column17=");
        sb.append(column17);
        sb.append(", column4=");
        sb.append(column4);
        sb.append(", column3=");
        sb.append(column3);
        sb.append(", column2=");
        sb.append(column2);
        sb.append(", column1=");
        sb.append(column1);
        sb.append(", column8=");
        sb.append(column8);
        sb.append(", reasonForFailure=");
        sb.append(reasonForFailure);
        sb.append(", column7=");
        sb.append(column7);
        sb.append(", column6=");
        sb.append(column6);
        sb.append(", column5=");
        sb.append(column5);
        sb.append(", column20=");
        sb.append(column20);
        sb.append(", batchId=");
        sb.append(batchId);
        sb.append(", errorField=");
        sb.append(errorField);
        sb.append(", column9=");
        sb.append(column9);
        sb.append(", ivldMasterDataAtbteSid=");
        sb.append(ivldMasterDataAtbteSid);
        sb.append(", checkRecord=");
        sb.append(checkRecord);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public IvldMasterDataAttribute toEntityModel() {
        IvldMasterDataAttributeImpl ivldMasterDataAttributeImpl = new IvldMasterDataAttributeImpl();

        if (column19 == null) {
            ivldMasterDataAttributeImpl.setColumn19(StringPool.BLANK);
        } else {
            ivldMasterDataAttributeImpl.setColumn19(column19);
        }

        if (column18 == null) {
            ivldMasterDataAttributeImpl.setColumn18(StringPool.BLANK);
        } else {
            ivldMasterDataAttributeImpl.setColumn18(column18);
        }

        if (masterAttribute == null) {
            ivldMasterDataAttributeImpl.setMasterAttribute(StringPool.BLANK);
        } else {
            ivldMasterDataAttributeImpl.setMasterAttribute(masterAttribute);
        }

        if (masterType == null) {
            ivldMasterDataAttributeImpl.setMasterType(StringPool.BLANK);
        } else {
            ivldMasterDataAttributeImpl.setMasterType(masterType);
        }

        if (dataAttributeIntfid == null) {
            ivldMasterDataAttributeImpl.setDataAttributeIntfid(StringPool.BLANK);
        } else {
            ivldMasterDataAttributeImpl.setDataAttributeIntfid(dataAttributeIntfid);
        }

        if (modifiedDate == Long.MIN_VALUE) {
            ivldMasterDataAttributeImpl.setModifiedDate(null);
        } else {
            ivldMasterDataAttributeImpl.setModifiedDate(new Date(modifiedDate));
        }

        if (source == null) {
            ivldMasterDataAttributeImpl.setSource(StringPool.BLANK);
        } else {
            ivldMasterDataAttributeImpl.setSource(source);
        }

        if (createdDate == Long.MIN_VALUE) {
            ivldMasterDataAttributeImpl.setCreatedDate(null);
        } else {
            ivldMasterDataAttributeImpl.setCreatedDate(new Date(createdDate));
        }

        if (createdBy == null) {
            ivldMasterDataAttributeImpl.setCreatedBy(StringPool.BLANK);
        } else {
            ivldMasterDataAttributeImpl.setCreatedBy(createdBy);
        }

        if (addChgDelIndicator == null) {
            ivldMasterDataAttributeImpl.setAddChgDelIndicator(StringPool.BLANK);
        } else {
            ivldMasterDataAttributeImpl.setAddChgDelIndicator(addChgDelIndicator);
        }

        if (masterId == null) {
            ivldMasterDataAttributeImpl.setMasterId(StringPool.BLANK);
        } else {
            ivldMasterDataAttributeImpl.setMasterId(masterId);
        }

        if (column10 == null) {
            ivldMasterDataAttributeImpl.setColumn10(StringPool.BLANK);
        } else {
            ivldMasterDataAttributeImpl.setColumn10(column10);
        }

        if (column11 == null) {
            ivldMasterDataAttributeImpl.setColumn11(StringPool.BLANK);
        } else {
            ivldMasterDataAttributeImpl.setColumn11(column11);
        }

        if (errorCode == null) {
            ivldMasterDataAttributeImpl.setErrorCode(StringPool.BLANK);
        } else {
            ivldMasterDataAttributeImpl.setErrorCode(errorCode);
        }

        if (column12 == null) {
            ivldMasterDataAttributeImpl.setColumn12(StringPool.BLANK);
        } else {
            ivldMasterDataAttributeImpl.setColumn12(column12);
        }

        if (intfInsertedDate == Long.MIN_VALUE) {
            ivldMasterDataAttributeImpl.setIntfInsertedDate(null);
        } else {
            ivldMasterDataAttributeImpl.setIntfInsertedDate(new Date(
                    intfInsertedDate));
        }

        if (modifiedBy == null) {
            ivldMasterDataAttributeImpl.setModifiedBy(StringPool.BLANK);
        } else {
            ivldMasterDataAttributeImpl.setModifiedBy(modifiedBy);
        }

        if (column13 == null) {
            ivldMasterDataAttributeImpl.setColumn13(StringPool.BLANK);
        } else {
            ivldMasterDataAttributeImpl.setColumn13(column13);
        }

        if (column14 == null) {
            ivldMasterDataAttributeImpl.setColumn14(StringPool.BLANK);
        } else {
            ivldMasterDataAttributeImpl.setColumn14(column14);
        }

        if (reprocessedFlag == null) {
            ivldMasterDataAttributeImpl.setReprocessedFlag(StringPool.BLANK);
        } else {
            ivldMasterDataAttributeImpl.setReprocessedFlag(reprocessedFlag);
        }

        if (column15 == null) {
            ivldMasterDataAttributeImpl.setColumn15(StringPool.BLANK);
        } else {
            ivldMasterDataAttributeImpl.setColumn15(column15);
        }

        if (column16 == null) {
            ivldMasterDataAttributeImpl.setColumn16(StringPool.BLANK);
        } else {
            ivldMasterDataAttributeImpl.setColumn16(column16);
        }

        if (column17 == null) {
            ivldMasterDataAttributeImpl.setColumn17(StringPool.BLANK);
        } else {
            ivldMasterDataAttributeImpl.setColumn17(column17);
        }

        if (column4 == null) {
            ivldMasterDataAttributeImpl.setColumn4(StringPool.BLANK);
        } else {
            ivldMasterDataAttributeImpl.setColumn4(column4);
        }

        if (column3 == null) {
            ivldMasterDataAttributeImpl.setColumn3(StringPool.BLANK);
        } else {
            ivldMasterDataAttributeImpl.setColumn3(column3);
        }

        if (column2 == null) {
            ivldMasterDataAttributeImpl.setColumn2(StringPool.BLANK);
        } else {
            ivldMasterDataAttributeImpl.setColumn2(column2);
        }

        if (column1 == null) {
            ivldMasterDataAttributeImpl.setColumn1(StringPool.BLANK);
        } else {
            ivldMasterDataAttributeImpl.setColumn1(column1);
        }

        if (column8 == null) {
            ivldMasterDataAttributeImpl.setColumn8(StringPool.BLANK);
        } else {
            ivldMasterDataAttributeImpl.setColumn8(column8);
        }

        if (reasonForFailure == null) {
            ivldMasterDataAttributeImpl.setReasonForFailure(StringPool.BLANK);
        } else {
            ivldMasterDataAttributeImpl.setReasonForFailure(reasonForFailure);
        }

        if (column7 == null) {
            ivldMasterDataAttributeImpl.setColumn7(StringPool.BLANK);
        } else {
            ivldMasterDataAttributeImpl.setColumn7(column7);
        }

        if (column6 == null) {
            ivldMasterDataAttributeImpl.setColumn6(StringPool.BLANK);
        } else {
            ivldMasterDataAttributeImpl.setColumn6(column6);
        }

        if (column5 == null) {
            ivldMasterDataAttributeImpl.setColumn5(StringPool.BLANK);
        } else {
            ivldMasterDataAttributeImpl.setColumn5(column5);
        }

        if (column20 == null) {
            ivldMasterDataAttributeImpl.setColumn20(StringPool.BLANK);
        } else {
            ivldMasterDataAttributeImpl.setColumn20(column20);
        }

        if (batchId == null) {
            ivldMasterDataAttributeImpl.setBatchId(StringPool.BLANK);
        } else {
            ivldMasterDataAttributeImpl.setBatchId(batchId);
        }

        if (errorField == null) {
            ivldMasterDataAttributeImpl.setErrorField(StringPool.BLANK);
        } else {
            ivldMasterDataAttributeImpl.setErrorField(errorField);
        }

        if (column9 == null) {
            ivldMasterDataAttributeImpl.setColumn9(StringPool.BLANK);
        } else {
            ivldMasterDataAttributeImpl.setColumn9(column9);
        }

        ivldMasterDataAttributeImpl.setIvldMasterDataAtbteSid(ivldMasterDataAtbteSid);
        ivldMasterDataAttributeImpl.setCheckRecord(checkRecord);

        ivldMasterDataAttributeImpl.resetOriginalValues();

        return ivldMasterDataAttributeImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        column19 = objectInput.readUTF();
        column18 = objectInput.readUTF();
        masterAttribute = objectInput.readUTF();
        masterType = objectInput.readUTF();
        dataAttributeIntfid = objectInput.readUTF();
        modifiedDate = objectInput.readLong();
        source = objectInput.readUTF();
        createdDate = objectInput.readLong();
        createdBy = objectInput.readUTF();
        addChgDelIndicator = objectInput.readUTF();
        masterId = objectInput.readUTF();
        column10 = objectInput.readUTF();
        column11 = objectInput.readUTF();
        errorCode = objectInput.readUTF();
        column12 = objectInput.readUTF();
        intfInsertedDate = objectInput.readLong();
        modifiedBy = objectInput.readUTF();
        column13 = objectInput.readUTF();
        column14 = objectInput.readUTF();
        reprocessedFlag = objectInput.readUTF();
        column15 = objectInput.readUTF();
        column16 = objectInput.readUTF();
        column17 = objectInput.readUTF();
        column4 = objectInput.readUTF();
        column3 = objectInput.readUTF();
        column2 = objectInput.readUTF();
        column1 = objectInput.readUTF();
        column8 = objectInput.readUTF();
        reasonForFailure = objectInput.readUTF();
        column7 = objectInput.readUTF();
        column6 = objectInput.readUTF();
        column5 = objectInput.readUTF();
        column20 = objectInput.readUTF();
        batchId = objectInput.readUTF();
        errorField = objectInput.readUTF();
        column9 = objectInput.readUTF();
        ivldMasterDataAtbteSid = objectInput.readInt();
        checkRecord = objectInput.readBoolean();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
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

        if (masterType == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(masterType);
        }

        if (dataAttributeIntfid == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(dataAttributeIntfid);
        }

        objectOutput.writeLong(modifiedDate);

        if (source == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(source);
        }

        objectOutput.writeLong(createdDate);

        if (createdBy == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(createdBy);
        }

        if (addChgDelIndicator == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(addChgDelIndicator);
        }

        if (masterId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(masterId);
        }

        if (column10 == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(column10);
        }

        if (column11 == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(column11);
        }

        if (errorCode == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(errorCode);
        }

        if (column12 == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(column12);
        }

        objectOutput.writeLong(intfInsertedDate);

        if (modifiedBy == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(modifiedBy);
        }

        if (column13 == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(column13);
        }

        if (column14 == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(column14);
        }

        if (reprocessedFlag == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(reprocessedFlag);
        }

        if (column15 == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(column15);
        }

        if (column16 == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(column16);
        }

        if (column17 == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(column17);
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

        if (column8 == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(column8);
        }

        if (reasonForFailure == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(reasonForFailure);
        }

        if (column7 == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(column7);
        }

        if (column6 == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(column6);
        }

        if (column5 == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(column5);
        }

        if (column20 == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(column20);
        }

        if (batchId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(batchId);
        }

        if (errorField == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(errorField);
        }

        if (column9 == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(column9);
        }

        objectOutput.writeInt(ivldMasterDataAtbteSid);
        objectOutput.writeBoolean(checkRecord);
    }
}
