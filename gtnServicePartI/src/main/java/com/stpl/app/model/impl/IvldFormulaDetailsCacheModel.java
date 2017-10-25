package com.stpl.app.model.impl;

import com.stpl.app.model.IvldFormulaDetails;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing IvldFormulaDetails in entity cache.
 *
 * @author
 * @see IvldFormulaDetails
 * @generated
 */
public class IvldFormulaDetailsCacheModel implements CacheModel<IvldFormulaDetails>,
    Externalizable {
    public String endDate;
    public String rebatePercent1;
    public String itemId;
    public String rebatePercent2;
    public String formulaDesc;
    public long modifiedDate;
    public String rebatePercent3;
    public String createdBy;
    public long createdDate;
    public String source;
    public String addChgDelIndicator;
    public String errorCode;
    public String formulaId;
    public String modifiedBy;
    public long intfInsertedDate;
    public String reprocessedFlag;
    public String formulaDetailsIntfid;
    public String reasonForFailure;
    public String contractPrice1;
    public String companyId;
    public String contractPrice2;
    public String formulaNo;
    public String startDate;
    public String batchId;
    public String errorField;
    public String contractPrice3;
    public int ivldFormulaDetailsSid;
    public boolean checkRecord;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(57);

        sb.append("{endDate=");
        sb.append(endDate);
        sb.append(", rebatePercent1=");
        sb.append(rebatePercent1);
        sb.append(", itemId=");
        sb.append(itemId);
        sb.append(", rebatePercent2=");
        sb.append(rebatePercent2);
        sb.append(", formulaDesc=");
        sb.append(formulaDesc);
        sb.append(", modifiedDate=");
        sb.append(modifiedDate);
        sb.append(", rebatePercent3=");
        sb.append(rebatePercent3);
        sb.append(", createdBy=");
        sb.append(createdBy);
        sb.append(", createdDate=");
        sb.append(createdDate);
        sb.append(", source=");
        sb.append(source);
        sb.append(", addChgDelIndicator=");
        sb.append(addChgDelIndicator);
        sb.append(", errorCode=");
        sb.append(errorCode);
        sb.append(", formulaId=");
        sb.append(formulaId);
        sb.append(", modifiedBy=");
        sb.append(modifiedBy);
        sb.append(", intfInsertedDate=");
        sb.append(intfInsertedDate);
        sb.append(", reprocessedFlag=");
        sb.append(reprocessedFlag);
        sb.append(", formulaDetailsIntfid=");
        sb.append(formulaDetailsIntfid);
        sb.append(", reasonForFailure=");
        sb.append(reasonForFailure);
        sb.append(", contractPrice1=");
        sb.append(contractPrice1);
        sb.append(", companyId=");
        sb.append(companyId);
        sb.append(", contractPrice2=");
        sb.append(contractPrice2);
        sb.append(", formulaNo=");
        sb.append(formulaNo);
        sb.append(", startDate=");
        sb.append(startDate);
        sb.append(", batchId=");
        sb.append(batchId);
        sb.append(", errorField=");
        sb.append(errorField);
        sb.append(", contractPrice3=");
        sb.append(contractPrice3);
        sb.append(", ivldFormulaDetailsSid=");
        sb.append(ivldFormulaDetailsSid);
        sb.append(", checkRecord=");
        sb.append(checkRecord);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public IvldFormulaDetails toEntityModel() {
        IvldFormulaDetailsImpl ivldFormulaDetailsImpl = new IvldFormulaDetailsImpl();

        if (endDate == null) {
            ivldFormulaDetailsImpl.setEndDate(StringPool.BLANK);
        } else {
            ivldFormulaDetailsImpl.setEndDate(endDate);
        }

        if (rebatePercent1 == null) {
            ivldFormulaDetailsImpl.setRebatePercent1(StringPool.BLANK);
        } else {
            ivldFormulaDetailsImpl.setRebatePercent1(rebatePercent1);
        }

        if (itemId == null) {
            ivldFormulaDetailsImpl.setItemId(StringPool.BLANK);
        } else {
            ivldFormulaDetailsImpl.setItemId(itemId);
        }

        if (rebatePercent2 == null) {
            ivldFormulaDetailsImpl.setRebatePercent2(StringPool.BLANK);
        } else {
            ivldFormulaDetailsImpl.setRebatePercent2(rebatePercent2);
        }

        if (formulaDesc == null) {
            ivldFormulaDetailsImpl.setFormulaDesc(StringPool.BLANK);
        } else {
            ivldFormulaDetailsImpl.setFormulaDesc(formulaDesc);
        }

        if (modifiedDate == Long.MIN_VALUE) {
            ivldFormulaDetailsImpl.setModifiedDate(null);
        } else {
            ivldFormulaDetailsImpl.setModifiedDate(new Date(modifiedDate));
        }

        if (rebatePercent3 == null) {
            ivldFormulaDetailsImpl.setRebatePercent3(StringPool.BLANK);
        } else {
            ivldFormulaDetailsImpl.setRebatePercent3(rebatePercent3);
        }

        if (createdBy == null) {
            ivldFormulaDetailsImpl.setCreatedBy(StringPool.BLANK);
        } else {
            ivldFormulaDetailsImpl.setCreatedBy(createdBy);
        }

        if (createdDate == Long.MIN_VALUE) {
            ivldFormulaDetailsImpl.setCreatedDate(null);
        } else {
            ivldFormulaDetailsImpl.setCreatedDate(new Date(createdDate));
        }

        if (source == null) {
            ivldFormulaDetailsImpl.setSource(StringPool.BLANK);
        } else {
            ivldFormulaDetailsImpl.setSource(source);
        }

        if (addChgDelIndicator == null) {
            ivldFormulaDetailsImpl.setAddChgDelIndicator(StringPool.BLANK);
        } else {
            ivldFormulaDetailsImpl.setAddChgDelIndicator(addChgDelIndicator);
        }

        if (errorCode == null) {
            ivldFormulaDetailsImpl.setErrorCode(StringPool.BLANK);
        } else {
            ivldFormulaDetailsImpl.setErrorCode(errorCode);
        }

        if (formulaId == null) {
            ivldFormulaDetailsImpl.setFormulaId(StringPool.BLANK);
        } else {
            ivldFormulaDetailsImpl.setFormulaId(formulaId);
        }

        if (modifiedBy == null) {
            ivldFormulaDetailsImpl.setModifiedBy(StringPool.BLANK);
        } else {
            ivldFormulaDetailsImpl.setModifiedBy(modifiedBy);
        }

        if (intfInsertedDate == Long.MIN_VALUE) {
            ivldFormulaDetailsImpl.setIntfInsertedDate(null);
        } else {
            ivldFormulaDetailsImpl.setIntfInsertedDate(new Date(
                    intfInsertedDate));
        }

        if (reprocessedFlag == null) {
            ivldFormulaDetailsImpl.setReprocessedFlag(StringPool.BLANK);
        } else {
            ivldFormulaDetailsImpl.setReprocessedFlag(reprocessedFlag);
        }

        if (formulaDetailsIntfid == null) {
            ivldFormulaDetailsImpl.setFormulaDetailsIntfid(StringPool.BLANK);
        } else {
            ivldFormulaDetailsImpl.setFormulaDetailsIntfid(formulaDetailsIntfid);
        }

        if (reasonForFailure == null) {
            ivldFormulaDetailsImpl.setReasonForFailure(StringPool.BLANK);
        } else {
            ivldFormulaDetailsImpl.setReasonForFailure(reasonForFailure);
        }

        if (contractPrice1 == null) {
            ivldFormulaDetailsImpl.setContractPrice1(StringPool.BLANK);
        } else {
            ivldFormulaDetailsImpl.setContractPrice1(contractPrice1);
        }

        if (companyId == null) {
            ivldFormulaDetailsImpl.setCompanyId(StringPool.BLANK);
        } else {
            ivldFormulaDetailsImpl.setCompanyId(companyId);
        }

        if (contractPrice2 == null) {
            ivldFormulaDetailsImpl.setContractPrice2(StringPool.BLANK);
        } else {
            ivldFormulaDetailsImpl.setContractPrice2(contractPrice2);
        }

        if (formulaNo == null) {
            ivldFormulaDetailsImpl.setFormulaNo(StringPool.BLANK);
        } else {
            ivldFormulaDetailsImpl.setFormulaNo(formulaNo);
        }

        if (startDate == null) {
            ivldFormulaDetailsImpl.setStartDate(StringPool.BLANK);
        } else {
            ivldFormulaDetailsImpl.setStartDate(startDate);
        }

        if (batchId == null) {
            ivldFormulaDetailsImpl.setBatchId(StringPool.BLANK);
        } else {
            ivldFormulaDetailsImpl.setBatchId(batchId);
        }

        if (errorField == null) {
            ivldFormulaDetailsImpl.setErrorField(StringPool.BLANK);
        } else {
            ivldFormulaDetailsImpl.setErrorField(errorField);
        }

        if (contractPrice3 == null) {
            ivldFormulaDetailsImpl.setContractPrice3(StringPool.BLANK);
        } else {
            ivldFormulaDetailsImpl.setContractPrice3(contractPrice3);
        }

        ivldFormulaDetailsImpl.setIvldFormulaDetailsSid(ivldFormulaDetailsSid);
        ivldFormulaDetailsImpl.setCheckRecord(checkRecord);

        ivldFormulaDetailsImpl.resetOriginalValues();

        return ivldFormulaDetailsImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        endDate = objectInput.readUTF();
        rebatePercent1 = objectInput.readUTF();
        itemId = objectInput.readUTF();
        rebatePercent2 = objectInput.readUTF();
        formulaDesc = objectInput.readUTF();
        modifiedDate = objectInput.readLong();
        rebatePercent3 = objectInput.readUTF();
        createdBy = objectInput.readUTF();
        createdDate = objectInput.readLong();
        source = objectInput.readUTF();
        addChgDelIndicator = objectInput.readUTF();
        errorCode = objectInput.readUTF();
        formulaId = objectInput.readUTF();
        modifiedBy = objectInput.readUTF();
        intfInsertedDate = objectInput.readLong();
        reprocessedFlag = objectInput.readUTF();
        formulaDetailsIntfid = objectInput.readUTF();
        reasonForFailure = objectInput.readUTF();
        contractPrice1 = objectInput.readUTF();
        companyId = objectInput.readUTF();
        contractPrice2 = objectInput.readUTF();
        formulaNo = objectInput.readUTF();
        startDate = objectInput.readUTF();
        batchId = objectInput.readUTF();
        errorField = objectInput.readUTF();
        contractPrice3 = objectInput.readUTF();
        ivldFormulaDetailsSid = objectInput.readInt();
        checkRecord = objectInput.readBoolean();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        if (endDate == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(endDate);
        }

        if (rebatePercent1 == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(rebatePercent1);
        }

        if (itemId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(itemId);
        }

        if (rebatePercent2 == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(rebatePercent2);
        }

        if (formulaDesc == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(formulaDesc);
        }

        objectOutput.writeLong(modifiedDate);

        if (rebatePercent3 == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(rebatePercent3);
        }

        if (createdBy == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(createdBy);
        }

        objectOutput.writeLong(createdDate);

        if (source == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(source);
        }

        if (addChgDelIndicator == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(addChgDelIndicator);
        }

        if (errorCode == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(errorCode);
        }

        if (formulaId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(formulaId);
        }

        if (modifiedBy == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(modifiedBy);
        }

        objectOutput.writeLong(intfInsertedDate);

        if (reprocessedFlag == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(reprocessedFlag);
        }

        if (formulaDetailsIntfid == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(formulaDetailsIntfid);
        }

        if (reasonForFailure == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(reasonForFailure);
        }

        if (contractPrice1 == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(contractPrice1);
        }

        if (companyId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(companyId);
        }

        if (contractPrice2 == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(contractPrice2);
        }

        if (formulaNo == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(formulaNo);
        }

        if (startDate == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(startDate);
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

        if (contractPrice3 == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(contractPrice3);
        }

        objectOutput.writeInt(ivldFormulaDetailsSid);
        objectOutput.writeBoolean(checkRecord);
    }
}
