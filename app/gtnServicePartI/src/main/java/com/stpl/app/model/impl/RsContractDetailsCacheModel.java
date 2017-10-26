package com.stpl.app.model.impl;

import com.stpl.app.model.RsContractDetails;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing RsContractDetails in entity cache.
 *
 * @author
 * @see RsContractDetails
 * @generated
 */
public class RsContractDetailsCacheModel implements CacheModel<RsContractDetails>,
    Externalizable {
    public double rebateAmount;
    public String formulaMethodId;
    public int itemMasterSid;
    public String rebatePlanMasterSid;
    public long modifiedDate;
    public int rsContractDetailsSid;
    public String bundleNo;
    public boolean recordLockStatus;
    public long createdDate;
    public int createdBy;
    public String source;
    public int rsContractSid;
    public long itemRebateEndDate;
    public String batchId;
    public long itemRebateStartDate;
    public int modifiedBy;
    public int formulaId;
    public String inboundStatus;
    public String deductionCalendarMasterSid;
    public String netSalesFormulaMasterSid;
    public double formulaType;
    public int netSalesRule;
    public int evaluationRule;
    public String evaluationRuleBundle;
    public int calculationRule;
    public String calculationRuleBundle;
    public long rsContractAttachedDate;
    public int rsContractAttachedStatus;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(57);

        sb.append("{rebateAmount=");
        sb.append(rebateAmount);
        sb.append(", formulaMethodId=");
        sb.append(formulaMethodId);
        sb.append(", itemMasterSid=");
        sb.append(itemMasterSid);
        sb.append(", rebatePlanMasterSid=");
        sb.append(rebatePlanMasterSid);
        sb.append(", modifiedDate=");
        sb.append(modifiedDate);
        sb.append(", rsContractDetailsSid=");
        sb.append(rsContractDetailsSid);
        sb.append(", bundleNo=");
        sb.append(bundleNo);
        sb.append(", recordLockStatus=");
        sb.append(recordLockStatus);
        sb.append(", createdDate=");
        sb.append(createdDate);
        sb.append(", createdBy=");
        sb.append(createdBy);
        sb.append(", source=");
        sb.append(source);
        sb.append(", rsContractSid=");
        sb.append(rsContractSid);
        sb.append(", itemRebateEndDate=");
        sb.append(itemRebateEndDate);
        sb.append(", batchId=");
        sb.append(batchId);
        sb.append(", itemRebateStartDate=");
        sb.append(itemRebateStartDate);
        sb.append(", modifiedBy=");
        sb.append(modifiedBy);
        sb.append(", formulaId=");
        sb.append(formulaId);
        sb.append(", inboundStatus=");
        sb.append(inboundStatus);
        sb.append(", deductionCalendarMasterSid=");
        sb.append(deductionCalendarMasterSid);
        sb.append(", netSalesFormulaMasterSid=");
        sb.append(netSalesFormulaMasterSid);
        sb.append(", formulaType=");
        sb.append(formulaType);
        sb.append(", netSalesRule=");
        sb.append(netSalesRule);
        sb.append(", evaluationRule=");
        sb.append(evaluationRule);
        sb.append(", evaluationRuleBundle=");
        sb.append(evaluationRuleBundle);
        sb.append(", calculationRule=");
        sb.append(calculationRule);
        sb.append(", calculationRuleBundle=");
        sb.append(calculationRuleBundle);
        sb.append(", rsContractAttachedDate=");
        sb.append(rsContractAttachedDate);
        sb.append(", rsContractAttachedStatus=");
        sb.append(rsContractAttachedStatus);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public RsContractDetails toEntityModel() {
        RsContractDetailsImpl rsContractDetailsImpl = new RsContractDetailsImpl();

        rsContractDetailsImpl.setRebateAmount(rebateAmount);

        if (formulaMethodId == null) {
            rsContractDetailsImpl.setFormulaMethodId(StringPool.BLANK);
        } else {
            rsContractDetailsImpl.setFormulaMethodId(formulaMethodId);
        }

        rsContractDetailsImpl.setItemMasterSid(itemMasterSid);

        if (rebatePlanMasterSid == null) {
            rsContractDetailsImpl.setRebatePlanMasterSid(StringPool.BLANK);
        } else {
            rsContractDetailsImpl.setRebatePlanMasterSid(rebatePlanMasterSid);
        }

        if (modifiedDate == Long.MIN_VALUE) {
            rsContractDetailsImpl.setModifiedDate(null);
        } else {
            rsContractDetailsImpl.setModifiedDate(new Date(modifiedDate));
        }

        rsContractDetailsImpl.setRsContractDetailsSid(rsContractDetailsSid);

        if (bundleNo == null) {
            rsContractDetailsImpl.setBundleNo(StringPool.BLANK);
        } else {
            rsContractDetailsImpl.setBundleNo(bundleNo);
        }

        rsContractDetailsImpl.setRecordLockStatus(recordLockStatus);

        if (createdDate == Long.MIN_VALUE) {
            rsContractDetailsImpl.setCreatedDate(null);
        } else {
            rsContractDetailsImpl.setCreatedDate(new Date(createdDate));
        }

        rsContractDetailsImpl.setCreatedBy(createdBy);

        if (source == null) {
            rsContractDetailsImpl.setSource(StringPool.BLANK);
        } else {
            rsContractDetailsImpl.setSource(source);
        }

        rsContractDetailsImpl.setRsContractSid(rsContractSid);

        if (itemRebateEndDate == Long.MIN_VALUE) {
            rsContractDetailsImpl.setItemRebateEndDate(null);
        } else {
            rsContractDetailsImpl.setItemRebateEndDate(new Date(
                    itemRebateEndDate));
        }

        if (batchId == null) {
            rsContractDetailsImpl.setBatchId(StringPool.BLANK);
        } else {
            rsContractDetailsImpl.setBatchId(batchId);
        }

        if (itemRebateStartDate == Long.MIN_VALUE) {
            rsContractDetailsImpl.setItemRebateStartDate(null);
        } else {
            rsContractDetailsImpl.setItemRebateStartDate(new Date(
                    itemRebateStartDate));
        }

        rsContractDetailsImpl.setModifiedBy(modifiedBy);
        rsContractDetailsImpl.setFormulaId(formulaId);

        if (inboundStatus == null) {
            rsContractDetailsImpl.setInboundStatus(StringPool.BLANK);
        } else {
            rsContractDetailsImpl.setInboundStatus(inboundStatus);
        }

        if (deductionCalendarMasterSid == null) {
            rsContractDetailsImpl.setDeductionCalendarMasterSid(StringPool.BLANK);
        } else {
            rsContractDetailsImpl.setDeductionCalendarMasterSid(deductionCalendarMasterSid);
        }

        if (netSalesFormulaMasterSid == null) {
            rsContractDetailsImpl.setNetSalesFormulaMasterSid(StringPool.BLANK);
        } else {
            rsContractDetailsImpl.setNetSalesFormulaMasterSid(netSalesFormulaMasterSid);
        }

        rsContractDetailsImpl.setFormulaType(formulaType);
        rsContractDetailsImpl.setNetSalesRule(netSalesRule);
        rsContractDetailsImpl.setEvaluationRule(evaluationRule);

        if (evaluationRuleBundle == null) {
            rsContractDetailsImpl.setEvaluationRuleBundle(StringPool.BLANK);
        } else {
            rsContractDetailsImpl.setEvaluationRuleBundle(evaluationRuleBundle);
        }

        rsContractDetailsImpl.setCalculationRule(calculationRule);

        if (calculationRuleBundle == null) {
            rsContractDetailsImpl.setCalculationRuleBundle(StringPool.BLANK);
        } else {
            rsContractDetailsImpl.setCalculationRuleBundle(calculationRuleBundle);
        }

        if (rsContractAttachedDate == Long.MIN_VALUE) {
            rsContractDetailsImpl.setRsContractAttachedDate(null);
        } else {
            rsContractDetailsImpl.setRsContractAttachedDate(new Date(
                    rsContractAttachedDate));
        }

        rsContractDetailsImpl.setRsContractAttachedStatus(rsContractAttachedStatus);

        rsContractDetailsImpl.resetOriginalValues();

        return rsContractDetailsImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        rebateAmount = objectInput.readDouble();
        formulaMethodId = objectInput.readUTF();
        itemMasterSid = objectInput.readInt();
        rebatePlanMasterSid = objectInput.readUTF();
        modifiedDate = objectInput.readLong();
        rsContractDetailsSid = objectInput.readInt();
        bundleNo = objectInput.readUTF();
        recordLockStatus = objectInput.readBoolean();
        createdDate = objectInput.readLong();
        createdBy = objectInput.readInt();
        source = objectInput.readUTF();
        rsContractSid = objectInput.readInt();
        itemRebateEndDate = objectInput.readLong();
        batchId = objectInput.readUTF();
        itemRebateStartDate = objectInput.readLong();
        modifiedBy = objectInput.readInt();
        formulaId = objectInput.readInt();
        inboundStatus = objectInput.readUTF();
        deductionCalendarMasterSid = objectInput.readUTF();
        netSalesFormulaMasterSid = objectInput.readUTF();
        formulaType = objectInput.readDouble();
        netSalesRule = objectInput.readInt();
        evaluationRule = objectInput.readInt();
        evaluationRuleBundle = objectInput.readUTF();
        calculationRule = objectInput.readInt();
        calculationRuleBundle = objectInput.readUTF();
        rsContractAttachedDate = objectInput.readLong();
        rsContractAttachedStatus = objectInput.readInt();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeDouble(rebateAmount);

        if (formulaMethodId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(formulaMethodId);
        }

        objectOutput.writeInt(itemMasterSid);

        if (rebatePlanMasterSid == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(rebatePlanMasterSid);
        }

        objectOutput.writeLong(modifiedDate);
        objectOutput.writeInt(rsContractDetailsSid);

        if (bundleNo == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(bundleNo);
        }

        objectOutput.writeBoolean(recordLockStatus);
        objectOutput.writeLong(createdDate);
        objectOutput.writeInt(createdBy);

        if (source == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(source);
        }

        objectOutput.writeInt(rsContractSid);
        objectOutput.writeLong(itemRebateEndDate);

        if (batchId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(batchId);
        }

        objectOutput.writeLong(itemRebateStartDate);
        objectOutput.writeInt(modifiedBy);
        objectOutput.writeInt(formulaId);

        if (inboundStatus == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(inboundStatus);
        }

        if (deductionCalendarMasterSid == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(deductionCalendarMasterSid);
        }

        if (netSalesFormulaMasterSid == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(netSalesFormulaMasterSid);
        }

        objectOutput.writeDouble(formulaType);
        objectOutput.writeInt(netSalesRule);
        objectOutput.writeInt(evaluationRule);

        if (evaluationRuleBundle == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(evaluationRuleBundle);
        }

        objectOutput.writeInt(calculationRule);

        if (calculationRuleBundle == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(calculationRuleBundle);
        }

        objectOutput.writeLong(rsContractAttachedDate);
        objectOutput.writeInt(rsContractAttachedStatus);
    }
}
