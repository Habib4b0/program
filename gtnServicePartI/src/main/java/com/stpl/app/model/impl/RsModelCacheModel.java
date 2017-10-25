package com.stpl.app.model.impl;

import com.stpl.app.model.RsModel;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing RsModel in entity cache.
 *
 * @author
 * @see RsModel
 * @generated
 */
public class RsModelCacheModel implements CacheModel<RsModel>, Externalizable {
    public String formulaMethodId;
    public int calculationType;
    public int rsStatus;
    public long rsEndDate;
    public String rsTransRefNo;
    public int paymentFrequency;
    public long modifiedDate;
    public int calculationLevel;
    public String rsName;
    public String source;
    public String rebatePlanLevel;
    public int rebateRuleType;
    public String inboundStatus;
    public int modifiedBy;
    public String rsAlias;
    public String rsId;
    public int paymentMethod;
    public String zipCode;
    public String rebateRuleAssociation;
    public String parentRsName;
    public String internalNotes;
    public int paymentLevel;
    public boolean recordLockStatus;
    public int rsCalendar;
    public int rebateProgramType;
    public int interestBearingBasis;
    public int rsModelSid;
    public String city;
    public int rebateProcessingType;
    public String rsNo;
    public int state;
    public int rebateFrequency;
    public String parentRsId;
    public int rsType;
    public long rsStartDate;
    public String makePayableTo;
    public int rsDesignation;
    public String rsTransRefName;
    public int createdBy;
    public long createdDate;
    public String rsTransRefId;
    public int rsCategory;
    public int rsTradeClass;
    public int interestBearingIndicator;
    public int manfCompanyMasterSid;
    public int paymentTerms;
    public String address1;
    public String address2;
    public int rsValidationProfile;
    public String paymentGracePeriod;
    public String batchId;
    public String evaluationRuleType;
    public String evaluationRuleLevel;
    public String evaluationRuleOrAssociation;
    public String calculationRuleLevel;
    public String calculationRule;
    public String deductionInclusion;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(115);

        sb.append("{formulaMethodId=");
        sb.append(formulaMethodId);
        sb.append(", calculationType=");
        sb.append(calculationType);
        sb.append(", rsStatus=");
        sb.append(rsStatus);
        sb.append(", rsEndDate=");
        sb.append(rsEndDate);
        sb.append(", rsTransRefNo=");
        sb.append(rsTransRefNo);
        sb.append(", paymentFrequency=");
        sb.append(paymentFrequency);
        sb.append(", modifiedDate=");
        sb.append(modifiedDate);
        sb.append(", calculationLevel=");
        sb.append(calculationLevel);
        sb.append(", rsName=");
        sb.append(rsName);
        sb.append(", source=");
        sb.append(source);
        sb.append(", rebatePlanLevel=");
        sb.append(rebatePlanLevel);
        sb.append(", rebateRuleType=");
        sb.append(rebateRuleType);
        sb.append(", inboundStatus=");
        sb.append(inboundStatus);
        sb.append(", modifiedBy=");
        sb.append(modifiedBy);
        sb.append(", rsAlias=");
        sb.append(rsAlias);
        sb.append(", rsId=");
        sb.append(rsId);
        sb.append(", paymentMethod=");
        sb.append(paymentMethod);
        sb.append(", zipCode=");
        sb.append(zipCode);
        sb.append(", rebateRuleAssociation=");
        sb.append(rebateRuleAssociation);
        sb.append(", parentRsName=");
        sb.append(parentRsName);
        sb.append(", internalNotes=");
        sb.append(internalNotes);
        sb.append(", paymentLevel=");
        sb.append(paymentLevel);
        sb.append(", recordLockStatus=");
        sb.append(recordLockStatus);
        sb.append(", rsCalendar=");
        sb.append(rsCalendar);
        sb.append(", rebateProgramType=");
        sb.append(rebateProgramType);
        sb.append(", interestBearingBasis=");
        sb.append(interestBearingBasis);
        sb.append(", rsModelSid=");
        sb.append(rsModelSid);
        sb.append(", city=");
        sb.append(city);
        sb.append(", rebateProcessingType=");
        sb.append(rebateProcessingType);
        sb.append(", rsNo=");
        sb.append(rsNo);
        sb.append(", state=");
        sb.append(state);
        sb.append(", rebateFrequency=");
        sb.append(rebateFrequency);
        sb.append(", parentRsId=");
        sb.append(parentRsId);
        sb.append(", rsType=");
        sb.append(rsType);
        sb.append(", rsStartDate=");
        sb.append(rsStartDate);
        sb.append(", makePayableTo=");
        sb.append(makePayableTo);
        sb.append(", rsDesignation=");
        sb.append(rsDesignation);
        sb.append(", rsTransRefName=");
        sb.append(rsTransRefName);
        sb.append(", createdBy=");
        sb.append(createdBy);
        sb.append(", createdDate=");
        sb.append(createdDate);
        sb.append(", rsTransRefId=");
        sb.append(rsTransRefId);
        sb.append(", rsCategory=");
        sb.append(rsCategory);
        sb.append(", rsTradeClass=");
        sb.append(rsTradeClass);
        sb.append(", interestBearingIndicator=");
        sb.append(interestBearingIndicator);
        sb.append(", manfCompanyMasterSid=");
        sb.append(manfCompanyMasterSid);
        sb.append(", paymentTerms=");
        sb.append(paymentTerms);
        sb.append(", address1=");
        sb.append(address1);
        sb.append(", address2=");
        sb.append(address2);
        sb.append(", rsValidationProfile=");
        sb.append(rsValidationProfile);
        sb.append(", paymentGracePeriod=");
        sb.append(paymentGracePeriod);
        sb.append(", batchId=");
        sb.append(batchId);
        sb.append(", evaluationRuleType=");
        sb.append(evaluationRuleType);
        sb.append(", evaluationRuleLevel=");
        sb.append(evaluationRuleLevel);
        sb.append(", evaluationRuleOrAssociation=");
        sb.append(evaluationRuleOrAssociation);
        sb.append(", calculationRuleLevel=");
        sb.append(calculationRuleLevel);
        sb.append(", calculationRule=");
        sb.append(calculationRule);
        sb.append(", deductionInclusion=");
        sb.append(deductionInclusion);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public RsModel toEntityModel() {
        RsModelImpl rsModelImpl = new RsModelImpl();

        if (formulaMethodId == null) {
            rsModelImpl.setFormulaMethodId(StringPool.BLANK);
        } else {
            rsModelImpl.setFormulaMethodId(formulaMethodId);
        }

        rsModelImpl.setCalculationType(calculationType);
        rsModelImpl.setRsStatus(rsStatus);

        if (rsEndDate == Long.MIN_VALUE) {
            rsModelImpl.setRsEndDate(null);
        } else {
            rsModelImpl.setRsEndDate(new Date(rsEndDate));
        }

        if (rsTransRefNo == null) {
            rsModelImpl.setRsTransRefNo(StringPool.BLANK);
        } else {
            rsModelImpl.setRsTransRefNo(rsTransRefNo);
        }

        rsModelImpl.setPaymentFrequency(paymentFrequency);

        if (modifiedDate == Long.MIN_VALUE) {
            rsModelImpl.setModifiedDate(null);
        } else {
            rsModelImpl.setModifiedDate(new Date(modifiedDate));
        }

        rsModelImpl.setCalculationLevel(calculationLevel);

        if (rsName == null) {
            rsModelImpl.setRsName(StringPool.BLANK);
        } else {
            rsModelImpl.setRsName(rsName);
        }

        if (source == null) {
            rsModelImpl.setSource(StringPool.BLANK);
        } else {
            rsModelImpl.setSource(source);
        }

        if (rebatePlanLevel == null) {
            rsModelImpl.setRebatePlanLevel(StringPool.BLANK);
        } else {
            rsModelImpl.setRebatePlanLevel(rebatePlanLevel);
        }

        rsModelImpl.setRebateRuleType(rebateRuleType);

        if (inboundStatus == null) {
            rsModelImpl.setInboundStatus(StringPool.BLANK);
        } else {
            rsModelImpl.setInboundStatus(inboundStatus);
        }

        rsModelImpl.setModifiedBy(modifiedBy);

        if (rsAlias == null) {
            rsModelImpl.setRsAlias(StringPool.BLANK);
        } else {
            rsModelImpl.setRsAlias(rsAlias);
        }

        if (rsId == null) {
            rsModelImpl.setRsId(StringPool.BLANK);
        } else {
            rsModelImpl.setRsId(rsId);
        }

        rsModelImpl.setPaymentMethod(paymentMethod);

        if (zipCode == null) {
            rsModelImpl.setZipCode(StringPool.BLANK);
        } else {
            rsModelImpl.setZipCode(zipCode);
        }

        if (rebateRuleAssociation == null) {
            rsModelImpl.setRebateRuleAssociation(StringPool.BLANK);
        } else {
            rsModelImpl.setRebateRuleAssociation(rebateRuleAssociation);
        }

        if (parentRsName == null) {
            rsModelImpl.setParentRsName(StringPool.BLANK);
        } else {
            rsModelImpl.setParentRsName(parentRsName);
        }

        if (internalNotes == null) {
            rsModelImpl.setInternalNotes(StringPool.BLANK);
        } else {
            rsModelImpl.setInternalNotes(internalNotes);
        }

        rsModelImpl.setPaymentLevel(paymentLevel);
        rsModelImpl.setRecordLockStatus(recordLockStatus);
        rsModelImpl.setRsCalendar(rsCalendar);
        rsModelImpl.setRebateProgramType(rebateProgramType);
        rsModelImpl.setInterestBearingBasis(interestBearingBasis);
        rsModelImpl.setRsModelSid(rsModelSid);

        if (city == null) {
            rsModelImpl.setCity(StringPool.BLANK);
        } else {
            rsModelImpl.setCity(city);
        }

        rsModelImpl.setRebateProcessingType(rebateProcessingType);

        if (rsNo == null) {
            rsModelImpl.setRsNo(StringPool.BLANK);
        } else {
            rsModelImpl.setRsNo(rsNo);
        }

        rsModelImpl.setState(state);
        rsModelImpl.setRebateFrequency(rebateFrequency);

        if (parentRsId == null) {
            rsModelImpl.setParentRsId(StringPool.BLANK);
        } else {
            rsModelImpl.setParentRsId(parentRsId);
        }

        rsModelImpl.setRsType(rsType);

        if (rsStartDate == Long.MIN_VALUE) {
            rsModelImpl.setRsStartDate(null);
        } else {
            rsModelImpl.setRsStartDate(new Date(rsStartDate));
        }

        if (makePayableTo == null) {
            rsModelImpl.setMakePayableTo(StringPool.BLANK);
        } else {
            rsModelImpl.setMakePayableTo(makePayableTo);
        }

        rsModelImpl.setRsDesignation(rsDesignation);

        if (rsTransRefName == null) {
            rsModelImpl.setRsTransRefName(StringPool.BLANK);
        } else {
            rsModelImpl.setRsTransRefName(rsTransRefName);
        }

        rsModelImpl.setCreatedBy(createdBy);

        if (createdDate == Long.MIN_VALUE) {
            rsModelImpl.setCreatedDate(null);
        } else {
            rsModelImpl.setCreatedDate(new Date(createdDate));
        }

        if (rsTransRefId == null) {
            rsModelImpl.setRsTransRefId(StringPool.BLANK);
        } else {
            rsModelImpl.setRsTransRefId(rsTransRefId);
        }

        rsModelImpl.setRsCategory(rsCategory);
        rsModelImpl.setRsTradeClass(rsTradeClass);
        rsModelImpl.setInterestBearingIndicator(interestBearingIndicator);
        rsModelImpl.setManfCompanyMasterSid(manfCompanyMasterSid);
        rsModelImpl.setPaymentTerms(paymentTerms);

        if (address1 == null) {
            rsModelImpl.setAddress1(StringPool.BLANK);
        } else {
            rsModelImpl.setAddress1(address1);
        }

        if (address2 == null) {
            rsModelImpl.setAddress2(StringPool.BLANK);
        } else {
            rsModelImpl.setAddress2(address2);
        }

        rsModelImpl.setRsValidationProfile(rsValidationProfile);

        if (paymentGracePeriod == null) {
            rsModelImpl.setPaymentGracePeriod(StringPool.BLANK);
        } else {
            rsModelImpl.setPaymentGracePeriod(paymentGracePeriod);
        }

        if (batchId == null) {
            rsModelImpl.setBatchId(StringPool.BLANK);
        } else {
            rsModelImpl.setBatchId(batchId);
        }

        if (evaluationRuleType == null) {
            rsModelImpl.setEvaluationRuleType(StringPool.BLANK);
        } else {
            rsModelImpl.setEvaluationRuleType(evaluationRuleType);
        }

        if (evaluationRuleLevel == null) {
            rsModelImpl.setEvaluationRuleLevel(StringPool.BLANK);
        } else {
            rsModelImpl.setEvaluationRuleLevel(evaluationRuleLevel);
        }

        if (evaluationRuleOrAssociation == null) {
            rsModelImpl.setEvaluationRuleOrAssociation(StringPool.BLANK);
        } else {
            rsModelImpl.setEvaluationRuleOrAssociation(evaluationRuleOrAssociation);
        }

        if (calculationRuleLevel == null) {
            rsModelImpl.setCalculationRuleLevel(StringPool.BLANK);
        } else {
            rsModelImpl.setCalculationRuleLevel(calculationRuleLevel);
        }

        if (calculationRule == null) {
            rsModelImpl.setCalculationRule(StringPool.BLANK);
        } else {
            rsModelImpl.setCalculationRule(calculationRule);
        }

        if (deductionInclusion == null) {
            rsModelImpl.setDeductionInclusion(StringPool.BLANK);
        } else {
            rsModelImpl.setDeductionInclusion(deductionInclusion);
        }

        rsModelImpl.resetOriginalValues();

        return rsModelImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        formulaMethodId = objectInput.readUTF();
        calculationType = objectInput.readInt();
        rsStatus = objectInput.readInt();
        rsEndDate = objectInput.readLong();
        rsTransRefNo = objectInput.readUTF();
        paymentFrequency = objectInput.readInt();
        modifiedDate = objectInput.readLong();
        calculationLevel = objectInput.readInt();
        rsName = objectInput.readUTF();
        source = objectInput.readUTF();
        rebatePlanLevel = objectInput.readUTF();
        rebateRuleType = objectInput.readInt();
        inboundStatus = objectInput.readUTF();
        modifiedBy = objectInput.readInt();
        rsAlias = objectInput.readUTF();
        rsId = objectInput.readUTF();
        paymentMethod = objectInput.readInt();
        zipCode = objectInput.readUTF();
        rebateRuleAssociation = objectInput.readUTF();
        parentRsName = objectInput.readUTF();
        internalNotes = objectInput.readUTF();
        paymentLevel = objectInput.readInt();
        recordLockStatus = objectInput.readBoolean();
        rsCalendar = objectInput.readInt();
        rebateProgramType = objectInput.readInt();
        interestBearingBasis = objectInput.readInt();
        rsModelSid = objectInput.readInt();
        city = objectInput.readUTF();
        rebateProcessingType = objectInput.readInt();
        rsNo = objectInput.readUTF();
        state = objectInput.readInt();
        rebateFrequency = objectInput.readInt();
        parentRsId = objectInput.readUTF();
        rsType = objectInput.readInt();
        rsStartDate = objectInput.readLong();
        makePayableTo = objectInput.readUTF();
        rsDesignation = objectInput.readInt();
        rsTransRefName = objectInput.readUTF();
        createdBy = objectInput.readInt();
        createdDate = objectInput.readLong();
        rsTransRefId = objectInput.readUTF();
        rsCategory = objectInput.readInt();
        rsTradeClass = objectInput.readInt();
        interestBearingIndicator = objectInput.readInt();
        manfCompanyMasterSid = objectInput.readInt();
        paymentTerms = objectInput.readInt();
        address1 = objectInput.readUTF();
        address2 = objectInput.readUTF();
        rsValidationProfile = objectInput.readInt();
        paymentGracePeriod = objectInput.readUTF();
        batchId = objectInput.readUTF();
        evaluationRuleType = objectInput.readUTF();
        evaluationRuleLevel = objectInput.readUTF();
        evaluationRuleOrAssociation = objectInput.readUTF();
        calculationRuleLevel = objectInput.readUTF();
        calculationRule = objectInput.readUTF();
        deductionInclusion = objectInput.readUTF();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        if (formulaMethodId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(formulaMethodId);
        }

        objectOutput.writeInt(calculationType);
        objectOutput.writeInt(rsStatus);
        objectOutput.writeLong(rsEndDate);

        if (rsTransRefNo == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(rsTransRefNo);
        }

        objectOutput.writeInt(paymentFrequency);
        objectOutput.writeLong(modifiedDate);
        objectOutput.writeInt(calculationLevel);

        if (rsName == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(rsName);
        }

        if (source == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(source);
        }

        if (rebatePlanLevel == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(rebatePlanLevel);
        }

        objectOutput.writeInt(rebateRuleType);

        if (inboundStatus == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(inboundStatus);
        }

        objectOutput.writeInt(modifiedBy);

        if (rsAlias == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(rsAlias);
        }

        if (rsId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(rsId);
        }

        objectOutput.writeInt(paymentMethod);

        if (zipCode == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(zipCode);
        }

        if (rebateRuleAssociation == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(rebateRuleAssociation);
        }

        if (parentRsName == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(parentRsName);
        }

        if (internalNotes == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(internalNotes);
        }

        objectOutput.writeInt(paymentLevel);
        objectOutput.writeBoolean(recordLockStatus);
        objectOutput.writeInt(rsCalendar);
        objectOutput.writeInt(rebateProgramType);
        objectOutput.writeInt(interestBearingBasis);
        objectOutput.writeInt(rsModelSid);

        if (city == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(city);
        }

        objectOutput.writeInt(rebateProcessingType);

        if (rsNo == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(rsNo);
        }

        objectOutput.writeInt(state);
        objectOutput.writeInt(rebateFrequency);

        if (parentRsId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(parentRsId);
        }

        objectOutput.writeInt(rsType);
        objectOutput.writeLong(rsStartDate);

        if (makePayableTo == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(makePayableTo);
        }

        objectOutput.writeInt(rsDesignation);

        if (rsTransRefName == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(rsTransRefName);
        }

        objectOutput.writeInt(createdBy);
        objectOutput.writeLong(createdDate);

        if (rsTransRefId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(rsTransRefId);
        }

        objectOutput.writeInt(rsCategory);
        objectOutput.writeInt(rsTradeClass);
        objectOutput.writeInt(interestBearingIndicator);
        objectOutput.writeInt(manfCompanyMasterSid);
        objectOutput.writeInt(paymentTerms);

        if (address1 == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(address1);
        }

        if (address2 == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(address2);
        }

        objectOutput.writeInt(rsValidationProfile);

        if (paymentGracePeriod == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(paymentGracePeriod);
        }

        if (batchId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(batchId);
        }

        if (evaluationRuleType == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(evaluationRuleType);
        }

        if (evaluationRuleLevel == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(evaluationRuleLevel);
        }

        if (evaluationRuleOrAssociation == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(evaluationRuleOrAssociation);
        }

        if (calculationRuleLevel == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(calculationRuleLevel);
        }

        if (calculationRule == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(calculationRule);
        }

        if (deductionInclusion == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(deductionInclusion);
        }
    }
}
