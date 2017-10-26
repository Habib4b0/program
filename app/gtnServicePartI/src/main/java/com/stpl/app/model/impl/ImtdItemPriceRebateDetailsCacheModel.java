package com.stpl.app.model.impl;

import com.stpl.app.model.ImtdItemPriceRebateDetails;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing ImtdItemPriceRebateDetails in entity cache.
 *
 * @author
 * @see ImtdItemPriceRebateDetails
 * @generated
 */
public class ImtdItemPriceRebateDetailsCacheModel implements CacheModel<ImtdItemPriceRebateDetails>,
    Externalizable {
    public String formulaMethodId;
    public long endDate;
    public long priceProtectionStartDate;
    public long itemPriceRevisionDate;
    public long modifiedDate;
    public boolean rsCheckRecord;
    public long rebateRevisionDate;
    public int contractMasterSid;
    public long imtdCreatedDate;
    public int modifiedBy;
    public int udc6;
    public int udc5;
    public int udc4;
    public boolean checkRecord;
    public int udc1;
    public int udc2;
    public int udc3;
    public long contractPriceEndDate;
    public double totalVolumeCommitment;
    public long priceProtectionEndDate;
    public String recordLockStatus;
    public long startDate;
    public int rebateProgramType;
    public String sessionId;
    public String itemName;
    public double priceRevision;
    public int rsModelSid;
    public double price;
    public int rsAttachedStatus;
    public int itemMasterSid;
    public double totalDollarCommitment;
    public int itemType;
    public double totalMarketShareCommitmnet;
    public String itemId;
    public double basePrice;
    public String bundleNo;
    public String formulaName;
    public int psStatus;
    public double priceTolerance;
    public long createdDate;
    public int createdBy;
    public int usersSid;
    public int psDetailsSid;
    public double suggestedPrice;
    public int psModelSid;
    public String formulaId;
    public String commitmentPeriod;
    public String itemNo;
    public double contractPrice;
    public int ifpDetailsSid;
    public int ifpModelSid;
    public String priceToleranceType;
    public double rebateAmount;
    public long contractPriceStartDate;
    public String rebateScheduleType;
    public String priceToleranceFrequency;
    public int imtdItemPriceRebateSid;
    public String rebatePlanSystemId;
    public long attachedDate;
    public String pricePlanId;
    public long itemRebateEndDate;
    public int priceType;
    public int priceToleranceInterval;
    public long rsAttachedDate;
    public long itemRebateStartDate;
    public String operation;
    public int cfpModelSid;
    public int rsDetailsSid;
    public int attachedStatus;
    public int primaryUom;
    public String packageSize;
    public String deductionCalendarMasterSid;
    public String rsContractDetailsDeductionCalendarNo;
    public String rsContractDetailsDeductionCalendarName;
    public String netSalesFormulaMasterSid;
    public String rsContractDetailsNetSalesFormulaNo;
    public String rsContractDetailsNetSalesFormulaName;
    public int formulaType;
    public int netSalesRule;
    public int evaluationRule;
    public String evaluationRuleBundle;
    public int calculationRule;
    public String calculationRuleBundle;
    public double maxIncrementalChange;
    public int resetEligible;
    public int resetType;
    public long resetDate;
    public int resetInterval;
    public int resetFrequency;
    public int netPriceType;
    public String netPriceTypeFormula;
    public int priceProtectionPriceType;
    public double nep;
    public int nepFormula;
    public String brandMasterSid;
    public int priceProtectionStatus;
    public int basePriceType;
    public double basePriceEntry;
    public long basePriceDate;
    public int basePriceDdlb;
    public int netBasePrice;
    public int subsequentPeriodPriceType;
    public int resetPriceType;
    public int netResetPriceType;
    public int netResetPriceFormulaId;
    public int netBasePriceFormulaId;
    public int netSubsequentPriceFormulaId;
    public int netSubsequentPeriodPrice;
    public String rsContractDetailsRebatePlanName;
    public String rsContractDetailsFormulaNo;
    public String source;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(223);

        sb.append("{formulaMethodId=");
        sb.append(formulaMethodId);
        sb.append(", endDate=");
        sb.append(endDate);
        sb.append(", priceProtectionStartDate=");
        sb.append(priceProtectionStartDate);
        sb.append(", itemPriceRevisionDate=");
        sb.append(itemPriceRevisionDate);
        sb.append(", modifiedDate=");
        sb.append(modifiedDate);
        sb.append(", rsCheckRecord=");
        sb.append(rsCheckRecord);
        sb.append(", rebateRevisionDate=");
        sb.append(rebateRevisionDate);
        sb.append(", contractMasterSid=");
        sb.append(contractMasterSid);
        sb.append(", imtdCreatedDate=");
        sb.append(imtdCreatedDate);
        sb.append(", modifiedBy=");
        sb.append(modifiedBy);
        sb.append(", udc6=");
        sb.append(udc6);
        sb.append(", udc5=");
        sb.append(udc5);
        sb.append(", udc4=");
        sb.append(udc4);
        sb.append(", checkRecord=");
        sb.append(checkRecord);
        sb.append(", udc1=");
        sb.append(udc1);
        sb.append(", udc2=");
        sb.append(udc2);
        sb.append(", udc3=");
        sb.append(udc3);
        sb.append(", contractPriceEndDate=");
        sb.append(contractPriceEndDate);
        sb.append(", totalVolumeCommitment=");
        sb.append(totalVolumeCommitment);
        sb.append(", priceProtectionEndDate=");
        sb.append(priceProtectionEndDate);
        sb.append(", recordLockStatus=");
        sb.append(recordLockStatus);
        sb.append(", startDate=");
        sb.append(startDate);
        sb.append(", rebateProgramType=");
        sb.append(rebateProgramType);
        sb.append(", sessionId=");
        sb.append(sessionId);
        sb.append(", itemName=");
        sb.append(itemName);
        sb.append(", priceRevision=");
        sb.append(priceRevision);
        sb.append(", rsModelSid=");
        sb.append(rsModelSid);
        sb.append(", price=");
        sb.append(price);
        sb.append(", rsAttachedStatus=");
        sb.append(rsAttachedStatus);
        sb.append(", itemMasterSid=");
        sb.append(itemMasterSid);
        sb.append(", totalDollarCommitment=");
        sb.append(totalDollarCommitment);
        sb.append(", itemType=");
        sb.append(itemType);
        sb.append(", totalMarketShareCommitmnet=");
        sb.append(totalMarketShareCommitmnet);
        sb.append(", itemId=");
        sb.append(itemId);
        sb.append(", basePrice=");
        sb.append(basePrice);
        sb.append(", bundleNo=");
        sb.append(bundleNo);
        sb.append(", formulaName=");
        sb.append(formulaName);
        sb.append(", psStatus=");
        sb.append(psStatus);
        sb.append(", priceTolerance=");
        sb.append(priceTolerance);
        sb.append(", createdDate=");
        sb.append(createdDate);
        sb.append(", createdBy=");
        sb.append(createdBy);
        sb.append(", usersSid=");
        sb.append(usersSid);
        sb.append(", psDetailsSid=");
        sb.append(psDetailsSid);
        sb.append(", suggestedPrice=");
        sb.append(suggestedPrice);
        sb.append(", psModelSid=");
        sb.append(psModelSid);
        sb.append(", formulaId=");
        sb.append(formulaId);
        sb.append(", commitmentPeriod=");
        sb.append(commitmentPeriod);
        sb.append(", itemNo=");
        sb.append(itemNo);
        sb.append(", contractPrice=");
        sb.append(contractPrice);
        sb.append(", ifpDetailsSid=");
        sb.append(ifpDetailsSid);
        sb.append(", ifpModelSid=");
        sb.append(ifpModelSid);
        sb.append(", priceToleranceType=");
        sb.append(priceToleranceType);
        sb.append(", rebateAmount=");
        sb.append(rebateAmount);
        sb.append(", contractPriceStartDate=");
        sb.append(contractPriceStartDate);
        sb.append(", rebateScheduleType=");
        sb.append(rebateScheduleType);
        sb.append(", priceToleranceFrequency=");
        sb.append(priceToleranceFrequency);
        sb.append(", imtdItemPriceRebateSid=");
        sb.append(imtdItemPriceRebateSid);
        sb.append(", rebatePlanSystemId=");
        sb.append(rebatePlanSystemId);
        sb.append(", attachedDate=");
        sb.append(attachedDate);
        sb.append(", pricePlanId=");
        sb.append(pricePlanId);
        sb.append(", itemRebateEndDate=");
        sb.append(itemRebateEndDate);
        sb.append(", priceType=");
        sb.append(priceType);
        sb.append(", priceToleranceInterval=");
        sb.append(priceToleranceInterval);
        sb.append(", rsAttachedDate=");
        sb.append(rsAttachedDate);
        sb.append(", itemRebateStartDate=");
        sb.append(itemRebateStartDate);
        sb.append(", operation=");
        sb.append(operation);
        sb.append(", cfpModelSid=");
        sb.append(cfpModelSid);
        sb.append(", rsDetailsSid=");
        sb.append(rsDetailsSid);
        sb.append(", attachedStatus=");
        sb.append(attachedStatus);
        sb.append(", primaryUom=");
        sb.append(primaryUom);
        sb.append(", packageSize=");
        sb.append(packageSize);
        sb.append(", deductionCalendarMasterSid=");
        sb.append(deductionCalendarMasterSid);
        sb.append(", rsContractDetailsDeductionCalendarNo=");
        sb.append(rsContractDetailsDeductionCalendarNo);
        sb.append(", rsContractDetailsDeductionCalendarName=");
        sb.append(rsContractDetailsDeductionCalendarName);
        sb.append(", netSalesFormulaMasterSid=");
        sb.append(netSalesFormulaMasterSid);
        sb.append(", rsContractDetailsNetSalesFormulaNo=");
        sb.append(rsContractDetailsNetSalesFormulaNo);
        sb.append(", rsContractDetailsNetSalesFormulaName=");
        sb.append(rsContractDetailsNetSalesFormulaName);
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
        sb.append(", maxIncrementalChange=");
        sb.append(maxIncrementalChange);
        sb.append(", resetEligible=");
        sb.append(resetEligible);
        sb.append(", resetType=");
        sb.append(resetType);
        sb.append(", resetDate=");
        sb.append(resetDate);
        sb.append(", resetInterval=");
        sb.append(resetInterval);
        sb.append(", resetFrequency=");
        sb.append(resetFrequency);
        sb.append(", netPriceType=");
        sb.append(netPriceType);
        sb.append(", netPriceTypeFormula=");
        sb.append(netPriceTypeFormula);
        sb.append(", priceProtectionPriceType=");
        sb.append(priceProtectionPriceType);
        sb.append(", nep=");
        sb.append(nep);
        sb.append(", nepFormula=");
        sb.append(nepFormula);
        sb.append(", brandMasterSid=");
        sb.append(brandMasterSid);
        sb.append(", priceProtectionStatus=");
        sb.append(priceProtectionStatus);
        sb.append(", basePriceType=");
        sb.append(basePriceType);
        sb.append(", basePriceEntry=");
        sb.append(basePriceEntry);
        sb.append(", basePriceDate=");
        sb.append(basePriceDate);
        sb.append(", basePriceDdlb=");
        sb.append(basePriceDdlb);
        sb.append(", netBasePrice=");
        sb.append(netBasePrice);
        sb.append(", subsequentPeriodPriceType=");
        sb.append(subsequentPeriodPriceType);
        sb.append(", resetPriceType=");
        sb.append(resetPriceType);
        sb.append(", netResetPriceType=");
        sb.append(netResetPriceType);
        sb.append(", netResetPriceFormulaId=");
        sb.append(netResetPriceFormulaId);
        sb.append(", netBasePriceFormulaId=");
        sb.append(netBasePriceFormulaId);
        sb.append(", netSubsequentPriceFormulaId=");
        sb.append(netSubsequentPriceFormulaId);
        sb.append(", netSubsequentPeriodPrice=");
        sb.append(netSubsequentPeriodPrice);
        sb.append(", rsContractDetailsRebatePlanName=");
        sb.append(rsContractDetailsRebatePlanName);
        sb.append(", rsContractDetailsFormulaNo=");
        sb.append(rsContractDetailsFormulaNo);
        sb.append(", source=");
        sb.append(source);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public ImtdItemPriceRebateDetails toEntityModel() {
        ImtdItemPriceRebateDetailsImpl imtdItemPriceRebateDetailsImpl = new ImtdItemPriceRebateDetailsImpl();

        if (formulaMethodId == null) {
            imtdItemPriceRebateDetailsImpl.setFormulaMethodId(StringPool.BLANK);
        } else {
            imtdItemPriceRebateDetailsImpl.setFormulaMethodId(formulaMethodId);
        }

        if (endDate == Long.MIN_VALUE) {
            imtdItemPriceRebateDetailsImpl.setEndDate(null);
        } else {
            imtdItemPriceRebateDetailsImpl.setEndDate(new Date(endDate));
        }

        if (priceProtectionStartDate == Long.MIN_VALUE) {
            imtdItemPriceRebateDetailsImpl.setPriceProtectionStartDate(null);
        } else {
            imtdItemPriceRebateDetailsImpl.setPriceProtectionStartDate(new Date(
                    priceProtectionStartDate));
        }

        if (itemPriceRevisionDate == Long.MIN_VALUE) {
            imtdItemPriceRebateDetailsImpl.setItemPriceRevisionDate(null);
        } else {
            imtdItemPriceRebateDetailsImpl.setItemPriceRevisionDate(new Date(
                    itemPriceRevisionDate));
        }

        if (modifiedDate == Long.MIN_VALUE) {
            imtdItemPriceRebateDetailsImpl.setModifiedDate(null);
        } else {
            imtdItemPriceRebateDetailsImpl.setModifiedDate(new Date(
                    modifiedDate));
        }

        imtdItemPriceRebateDetailsImpl.setRsCheckRecord(rsCheckRecord);

        if (rebateRevisionDate == Long.MIN_VALUE) {
            imtdItemPriceRebateDetailsImpl.setRebateRevisionDate(null);
        } else {
            imtdItemPriceRebateDetailsImpl.setRebateRevisionDate(new Date(
                    rebateRevisionDate));
        }

        imtdItemPriceRebateDetailsImpl.setContractMasterSid(contractMasterSid);

        if (imtdCreatedDate == Long.MIN_VALUE) {
            imtdItemPriceRebateDetailsImpl.setImtdCreatedDate(null);
        } else {
            imtdItemPriceRebateDetailsImpl.setImtdCreatedDate(new Date(
                    imtdCreatedDate));
        }

        imtdItemPriceRebateDetailsImpl.setModifiedBy(modifiedBy);
        imtdItemPriceRebateDetailsImpl.setUdc6(udc6);
        imtdItemPriceRebateDetailsImpl.setUdc5(udc5);
        imtdItemPriceRebateDetailsImpl.setUdc4(udc4);
        imtdItemPriceRebateDetailsImpl.setCheckRecord(checkRecord);
        imtdItemPriceRebateDetailsImpl.setUdc1(udc1);
        imtdItemPriceRebateDetailsImpl.setUdc2(udc2);
        imtdItemPriceRebateDetailsImpl.setUdc3(udc3);

        if (contractPriceEndDate == Long.MIN_VALUE) {
            imtdItemPriceRebateDetailsImpl.setContractPriceEndDate(null);
        } else {
            imtdItemPriceRebateDetailsImpl.setContractPriceEndDate(new Date(
                    contractPriceEndDate));
        }

        imtdItemPriceRebateDetailsImpl.setTotalVolumeCommitment(totalVolumeCommitment);

        if (priceProtectionEndDate == Long.MIN_VALUE) {
            imtdItemPriceRebateDetailsImpl.setPriceProtectionEndDate(null);
        } else {
            imtdItemPriceRebateDetailsImpl.setPriceProtectionEndDate(new Date(
                    priceProtectionEndDate));
        }

        if (recordLockStatus == null) {
            imtdItemPriceRebateDetailsImpl.setRecordLockStatus(StringPool.BLANK);
        } else {
            imtdItemPriceRebateDetailsImpl.setRecordLockStatus(recordLockStatus);
        }

        if (startDate == Long.MIN_VALUE) {
            imtdItemPriceRebateDetailsImpl.setStartDate(null);
        } else {
            imtdItemPriceRebateDetailsImpl.setStartDate(new Date(startDate));
        }

        imtdItemPriceRebateDetailsImpl.setRebateProgramType(rebateProgramType);

        if (sessionId == null) {
            imtdItemPriceRebateDetailsImpl.setSessionId(StringPool.BLANK);
        } else {
            imtdItemPriceRebateDetailsImpl.setSessionId(sessionId);
        }

        if (itemName == null) {
            imtdItemPriceRebateDetailsImpl.setItemName(StringPool.BLANK);
        } else {
            imtdItemPriceRebateDetailsImpl.setItemName(itemName);
        }

        imtdItemPriceRebateDetailsImpl.setPriceRevision(priceRevision);
        imtdItemPriceRebateDetailsImpl.setRsModelSid(rsModelSid);
        imtdItemPriceRebateDetailsImpl.setPrice(price);
        imtdItemPriceRebateDetailsImpl.setRsAttachedStatus(rsAttachedStatus);
        imtdItemPriceRebateDetailsImpl.setItemMasterSid(itemMasterSid);
        imtdItemPriceRebateDetailsImpl.setTotalDollarCommitment(totalDollarCommitment);
        imtdItemPriceRebateDetailsImpl.setItemType(itemType);
        imtdItemPriceRebateDetailsImpl.setTotalMarketShareCommitmnet(totalMarketShareCommitmnet);

        if (itemId == null) {
            imtdItemPriceRebateDetailsImpl.setItemId(StringPool.BLANK);
        } else {
            imtdItemPriceRebateDetailsImpl.setItemId(itemId);
        }

        imtdItemPriceRebateDetailsImpl.setBasePrice(basePrice);

        if (bundleNo == null) {
            imtdItemPriceRebateDetailsImpl.setBundleNo(StringPool.BLANK);
        } else {
            imtdItemPriceRebateDetailsImpl.setBundleNo(bundleNo);
        }

        if (formulaName == null) {
            imtdItemPriceRebateDetailsImpl.setFormulaName(StringPool.BLANK);
        } else {
            imtdItemPriceRebateDetailsImpl.setFormulaName(formulaName);
        }

        imtdItemPriceRebateDetailsImpl.setPsStatus(psStatus);
        imtdItemPriceRebateDetailsImpl.setPriceTolerance(priceTolerance);

        if (createdDate == Long.MIN_VALUE) {
            imtdItemPriceRebateDetailsImpl.setCreatedDate(null);
        } else {
            imtdItemPriceRebateDetailsImpl.setCreatedDate(new Date(createdDate));
        }

        imtdItemPriceRebateDetailsImpl.setCreatedBy(createdBy);
        imtdItemPriceRebateDetailsImpl.setUsersSid(usersSid);
        imtdItemPriceRebateDetailsImpl.setPsDetailsSid(psDetailsSid);
        imtdItemPriceRebateDetailsImpl.setSuggestedPrice(suggestedPrice);
        imtdItemPriceRebateDetailsImpl.setPsModelSid(psModelSid);

        if (formulaId == null) {
            imtdItemPriceRebateDetailsImpl.setFormulaId(StringPool.BLANK);
        } else {
            imtdItemPriceRebateDetailsImpl.setFormulaId(formulaId);
        }

        if (commitmentPeriod == null) {
            imtdItemPriceRebateDetailsImpl.setCommitmentPeriod(StringPool.BLANK);
        } else {
            imtdItemPriceRebateDetailsImpl.setCommitmentPeriod(commitmentPeriod);
        }

        if (itemNo == null) {
            imtdItemPriceRebateDetailsImpl.setItemNo(StringPool.BLANK);
        } else {
            imtdItemPriceRebateDetailsImpl.setItemNo(itemNo);
        }

        imtdItemPriceRebateDetailsImpl.setContractPrice(contractPrice);
        imtdItemPriceRebateDetailsImpl.setIfpDetailsSid(ifpDetailsSid);
        imtdItemPriceRebateDetailsImpl.setIfpModelSid(ifpModelSid);

        if (priceToleranceType == null) {
            imtdItemPriceRebateDetailsImpl.setPriceToleranceType(StringPool.BLANK);
        } else {
            imtdItemPriceRebateDetailsImpl.setPriceToleranceType(priceToleranceType);
        }

        imtdItemPriceRebateDetailsImpl.setRebateAmount(rebateAmount);

        if (contractPriceStartDate == Long.MIN_VALUE) {
            imtdItemPriceRebateDetailsImpl.setContractPriceStartDate(null);
        } else {
            imtdItemPriceRebateDetailsImpl.setContractPriceStartDate(new Date(
                    contractPriceStartDate));
        }

        if (rebateScheduleType == null) {
            imtdItemPriceRebateDetailsImpl.setRebateScheduleType(StringPool.BLANK);
        } else {
            imtdItemPriceRebateDetailsImpl.setRebateScheduleType(rebateScheduleType);
        }

        if (priceToleranceFrequency == null) {
            imtdItemPriceRebateDetailsImpl.setPriceToleranceFrequency(StringPool.BLANK);
        } else {
            imtdItemPriceRebateDetailsImpl.setPriceToleranceFrequency(priceToleranceFrequency);
        }

        imtdItemPriceRebateDetailsImpl.setImtdItemPriceRebateSid(imtdItemPriceRebateSid);

        if (rebatePlanSystemId == null) {
            imtdItemPriceRebateDetailsImpl.setRebatePlanSystemId(StringPool.BLANK);
        } else {
            imtdItemPriceRebateDetailsImpl.setRebatePlanSystemId(rebatePlanSystemId);
        }

        if (attachedDate == Long.MIN_VALUE) {
            imtdItemPriceRebateDetailsImpl.setAttachedDate(null);
        } else {
            imtdItemPriceRebateDetailsImpl.setAttachedDate(new Date(
                    attachedDate));
        }

        if (pricePlanId == null) {
            imtdItemPriceRebateDetailsImpl.setPricePlanId(StringPool.BLANK);
        } else {
            imtdItemPriceRebateDetailsImpl.setPricePlanId(pricePlanId);
        }

        if (itemRebateEndDate == Long.MIN_VALUE) {
            imtdItemPriceRebateDetailsImpl.setItemRebateEndDate(null);
        } else {
            imtdItemPriceRebateDetailsImpl.setItemRebateEndDate(new Date(
                    itemRebateEndDate));
        }

        imtdItemPriceRebateDetailsImpl.setPriceType(priceType);
        imtdItemPriceRebateDetailsImpl.setPriceToleranceInterval(priceToleranceInterval);

        if (rsAttachedDate == Long.MIN_VALUE) {
            imtdItemPriceRebateDetailsImpl.setRsAttachedDate(null);
        } else {
            imtdItemPriceRebateDetailsImpl.setRsAttachedDate(new Date(
                    rsAttachedDate));
        }

        if (itemRebateStartDate == Long.MIN_VALUE) {
            imtdItemPriceRebateDetailsImpl.setItemRebateStartDate(null);
        } else {
            imtdItemPriceRebateDetailsImpl.setItemRebateStartDate(new Date(
                    itemRebateStartDate));
        }

        if (operation == null) {
            imtdItemPriceRebateDetailsImpl.setOperation(StringPool.BLANK);
        } else {
            imtdItemPriceRebateDetailsImpl.setOperation(operation);
        }

        imtdItemPriceRebateDetailsImpl.setCfpModelSid(cfpModelSid);
        imtdItemPriceRebateDetailsImpl.setRsDetailsSid(rsDetailsSid);
        imtdItemPriceRebateDetailsImpl.setAttachedStatus(attachedStatus);
        imtdItemPriceRebateDetailsImpl.setPrimaryUom(primaryUom);

        if (packageSize == null) {
            imtdItemPriceRebateDetailsImpl.setPackageSize(StringPool.BLANK);
        } else {
            imtdItemPriceRebateDetailsImpl.setPackageSize(packageSize);
        }

        if (deductionCalendarMasterSid == null) {
            imtdItemPriceRebateDetailsImpl.setDeductionCalendarMasterSid(StringPool.BLANK);
        } else {
            imtdItemPriceRebateDetailsImpl.setDeductionCalendarMasterSid(deductionCalendarMasterSid);
        }

        if (rsContractDetailsDeductionCalendarNo == null) {
            imtdItemPriceRebateDetailsImpl.setRsContractDetailsDeductionCalendarNo(StringPool.BLANK);
        } else {
            imtdItemPriceRebateDetailsImpl.setRsContractDetailsDeductionCalendarNo(rsContractDetailsDeductionCalendarNo);
        }

        if (rsContractDetailsDeductionCalendarName == null) {
            imtdItemPriceRebateDetailsImpl.setRsContractDetailsDeductionCalendarName(StringPool.BLANK);
        } else {
            imtdItemPriceRebateDetailsImpl.setRsContractDetailsDeductionCalendarName(rsContractDetailsDeductionCalendarName);
        }

        if (netSalesFormulaMasterSid == null) {
            imtdItemPriceRebateDetailsImpl.setNetSalesFormulaMasterSid(StringPool.BLANK);
        } else {
            imtdItemPriceRebateDetailsImpl.setNetSalesFormulaMasterSid(netSalesFormulaMasterSid);
        }

        if (rsContractDetailsNetSalesFormulaNo == null) {
            imtdItemPriceRebateDetailsImpl.setRsContractDetailsNetSalesFormulaNo(StringPool.BLANK);
        } else {
            imtdItemPriceRebateDetailsImpl.setRsContractDetailsNetSalesFormulaNo(rsContractDetailsNetSalesFormulaNo);
        }

        if (rsContractDetailsNetSalesFormulaName == null) {
            imtdItemPriceRebateDetailsImpl.setRsContractDetailsNetSalesFormulaName(StringPool.BLANK);
        } else {
            imtdItemPriceRebateDetailsImpl.setRsContractDetailsNetSalesFormulaName(rsContractDetailsNetSalesFormulaName);
        }

        imtdItemPriceRebateDetailsImpl.setFormulaType(formulaType);
        imtdItemPriceRebateDetailsImpl.setNetSalesRule(netSalesRule);
        imtdItemPriceRebateDetailsImpl.setEvaluationRule(evaluationRule);

        if (evaluationRuleBundle == null) {
            imtdItemPriceRebateDetailsImpl.setEvaluationRuleBundle(StringPool.BLANK);
        } else {
            imtdItemPriceRebateDetailsImpl.setEvaluationRuleBundle(evaluationRuleBundle);
        }

        imtdItemPriceRebateDetailsImpl.setCalculationRule(calculationRule);

        if (calculationRuleBundle == null) {
            imtdItemPriceRebateDetailsImpl.setCalculationRuleBundle(StringPool.BLANK);
        } else {
            imtdItemPriceRebateDetailsImpl.setCalculationRuleBundle(calculationRuleBundle);
        }

        imtdItemPriceRebateDetailsImpl.setMaxIncrementalChange(maxIncrementalChange);
        imtdItemPriceRebateDetailsImpl.setResetEligible(resetEligible);
        imtdItemPriceRebateDetailsImpl.setResetType(resetType);

        if (resetDate == Long.MIN_VALUE) {
            imtdItemPriceRebateDetailsImpl.setResetDate(null);
        } else {
            imtdItemPriceRebateDetailsImpl.setResetDate(new Date(resetDate));
        }

        imtdItemPriceRebateDetailsImpl.setResetInterval(resetInterval);
        imtdItemPriceRebateDetailsImpl.setResetFrequency(resetFrequency);
        imtdItemPriceRebateDetailsImpl.setNetPriceType(netPriceType);

        if (netPriceTypeFormula == null) {
            imtdItemPriceRebateDetailsImpl.setNetPriceTypeFormula(StringPool.BLANK);
        } else {
            imtdItemPriceRebateDetailsImpl.setNetPriceTypeFormula(netPriceTypeFormula);
        }

        imtdItemPriceRebateDetailsImpl.setPriceProtectionPriceType(priceProtectionPriceType);
        imtdItemPriceRebateDetailsImpl.setNep(nep);
        imtdItemPriceRebateDetailsImpl.setNepFormula(nepFormula);

        if (brandMasterSid == null) {
            imtdItemPriceRebateDetailsImpl.setBrandMasterSid(StringPool.BLANK);
        } else {
            imtdItemPriceRebateDetailsImpl.setBrandMasterSid(brandMasterSid);
        }

        imtdItemPriceRebateDetailsImpl.setPriceProtectionStatus(priceProtectionStatus);
        imtdItemPriceRebateDetailsImpl.setBasePriceType(basePriceType);
        imtdItemPriceRebateDetailsImpl.setBasePriceEntry(basePriceEntry);

        if (basePriceDate == Long.MIN_VALUE) {
            imtdItemPriceRebateDetailsImpl.setBasePriceDate(null);
        } else {
            imtdItemPriceRebateDetailsImpl.setBasePriceDate(new Date(
                    basePriceDate));
        }

        imtdItemPriceRebateDetailsImpl.setBasePriceDdlb(basePriceDdlb);
        imtdItemPriceRebateDetailsImpl.setNetBasePrice(netBasePrice);
        imtdItemPriceRebateDetailsImpl.setSubsequentPeriodPriceType(subsequentPeriodPriceType);
        imtdItemPriceRebateDetailsImpl.setResetPriceType(resetPriceType);
        imtdItemPriceRebateDetailsImpl.setNetResetPriceType(netResetPriceType);
        imtdItemPriceRebateDetailsImpl.setNetResetPriceFormulaId(netResetPriceFormulaId);
        imtdItemPriceRebateDetailsImpl.setNetBasePriceFormulaId(netBasePriceFormulaId);
        imtdItemPriceRebateDetailsImpl.setNetSubsequentPriceFormulaId(netSubsequentPriceFormulaId);
        imtdItemPriceRebateDetailsImpl.setNetSubsequentPeriodPrice(netSubsequentPeriodPrice);

        if (rsContractDetailsRebatePlanName == null) {
            imtdItemPriceRebateDetailsImpl.setRsContractDetailsRebatePlanName(StringPool.BLANK);
        } else {
            imtdItemPriceRebateDetailsImpl.setRsContractDetailsRebatePlanName(rsContractDetailsRebatePlanName);
        }

        if (rsContractDetailsFormulaNo == null) {
            imtdItemPriceRebateDetailsImpl.setRsContractDetailsFormulaNo(StringPool.BLANK);
        } else {
            imtdItemPriceRebateDetailsImpl.setRsContractDetailsFormulaNo(rsContractDetailsFormulaNo);
        }

        if (source == null) {
            imtdItemPriceRebateDetailsImpl.setSource(StringPool.BLANK);
        } else {
            imtdItemPriceRebateDetailsImpl.setSource(source);
        }

        imtdItemPriceRebateDetailsImpl.resetOriginalValues();

        return imtdItemPriceRebateDetailsImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        formulaMethodId = objectInput.readUTF();
        endDate = objectInput.readLong();
        priceProtectionStartDate = objectInput.readLong();
        itemPriceRevisionDate = objectInput.readLong();
        modifiedDate = objectInput.readLong();
        rsCheckRecord = objectInput.readBoolean();
        rebateRevisionDate = objectInput.readLong();
        contractMasterSid = objectInput.readInt();
        imtdCreatedDate = objectInput.readLong();
        modifiedBy = objectInput.readInt();
        udc6 = objectInput.readInt();
        udc5 = objectInput.readInt();
        udc4 = objectInput.readInt();
        checkRecord = objectInput.readBoolean();
        udc1 = objectInput.readInt();
        udc2 = objectInput.readInt();
        udc3 = objectInput.readInt();
        contractPriceEndDate = objectInput.readLong();
        totalVolumeCommitment = objectInput.readDouble();
        priceProtectionEndDate = objectInput.readLong();
        recordLockStatus = objectInput.readUTF();
        startDate = objectInput.readLong();
        rebateProgramType = objectInput.readInt();
        sessionId = objectInput.readUTF();
        itemName = objectInput.readUTF();
        priceRevision = objectInput.readDouble();
        rsModelSid = objectInput.readInt();
        price = objectInput.readDouble();
        rsAttachedStatus = objectInput.readInt();
        itemMasterSid = objectInput.readInt();
        totalDollarCommitment = objectInput.readDouble();
        itemType = objectInput.readInt();
        totalMarketShareCommitmnet = objectInput.readDouble();
        itemId = objectInput.readUTF();
        basePrice = objectInput.readDouble();
        bundleNo = objectInput.readUTF();
        formulaName = objectInput.readUTF();
        psStatus = objectInput.readInt();
        priceTolerance = objectInput.readDouble();
        createdDate = objectInput.readLong();
        createdBy = objectInput.readInt();
        usersSid = objectInput.readInt();
        psDetailsSid = objectInput.readInt();
        suggestedPrice = objectInput.readDouble();
        psModelSid = objectInput.readInt();
        formulaId = objectInput.readUTF();
        commitmentPeriod = objectInput.readUTF();
        itemNo = objectInput.readUTF();
        contractPrice = objectInput.readDouble();
        ifpDetailsSid = objectInput.readInt();
        ifpModelSid = objectInput.readInt();
        priceToleranceType = objectInput.readUTF();
        rebateAmount = objectInput.readDouble();
        contractPriceStartDate = objectInput.readLong();
        rebateScheduleType = objectInput.readUTF();
        priceToleranceFrequency = objectInput.readUTF();
        imtdItemPriceRebateSid = objectInput.readInt();
        rebatePlanSystemId = objectInput.readUTF();
        attachedDate = objectInput.readLong();
        pricePlanId = objectInput.readUTF();
        itemRebateEndDate = objectInput.readLong();
        priceType = objectInput.readInt();
        priceToleranceInterval = objectInput.readInt();
        rsAttachedDate = objectInput.readLong();
        itemRebateStartDate = objectInput.readLong();
        operation = objectInput.readUTF();
        cfpModelSid = objectInput.readInt();
        rsDetailsSid = objectInput.readInt();
        attachedStatus = objectInput.readInt();
        primaryUom = objectInput.readInt();
        packageSize = objectInput.readUTF();
        deductionCalendarMasterSid = objectInput.readUTF();
        rsContractDetailsDeductionCalendarNo = objectInput.readUTF();
        rsContractDetailsDeductionCalendarName = objectInput.readUTF();
        netSalesFormulaMasterSid = objectInput.readUTF();
        rsContractDetailsNetSalesFormulaNo = objectInput.readUTF();
        rsContractDetailsNetSalesFormulaName = objectInput.readUTF();
        formulaType = objectInput.readInt();
        netSalesRule = objectInput.readInt();
        evaluationRule = objectInput.readInt();
        evaluationRuleBundle = objectInput.readUTF();
        calculationRule = objectInput.readInt();
        calculationRuleBundle = objectInput.readUTF();
        maxIncrementalChange = objectInput.readDouble();
        resetEligible = objectInput.readInt();
        resetType = objectInput.readInt();
        resetDate = objectInput.readLong();
        resetInterval = objectInput.readInt();
        resetFrequency = objectInput.readInt();
        netPriceType = objectInput.readInt();
        netPriceTypeFormula = objectInput.readUTF();
        priceProtectionPriceType = objectInput.readInt();
        nep = objectInput.readDouble();
        nepFormula = objectInput.readInt();
        brandMasterSid = objectInput.readUTF();
        priceProtectionStatus = objectInput.readInt();
        basePriceType = objectInput.readInt();
        basePriceEntry = objectInput.readDouble();
        basePriceDate = objectInput.readLong();
        basePriceDdlb = objectInput.readInt();
        netBasePrice = objectInput.readInt();
        subsequentPeriodPriceType = objectInput.readInt();
        resetPriceType = objectInput.readInt();
        netResetPriceType = objectInput.readInt();
        netResetPriceFormulaId = objectInput.readInt();
        netBasePriceFormulaId = objectInput.readInt();
        netSubsequentPriceFormulaId = objectInput.readInt();
        netSubsequentPeriodPrice = objectInput.readInt();
        rsContractDetailsRebatePlanName = objectInput.readUTF();
        rsContractDetailsFormulaNo = objectInput.readUTF();
        source = objectInput.readUTF();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        if (formulaMethodId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(formulaMethodId);
        }

        objectOutput.writeLong(endDate);
        objectOutput.writeLong(priceProtectionStartDate);
        objectOutput.writeLong(itemPriceRevisionDate);
        objectOutput.writeLong(modifiedDate);
        objectOutput.writeBoolean(rsCheckRecord);
        objectOutput.writeLong(rebateRevisionDate);
        objectOutput.writeInt(contractMasterSid);
        objectOutput.writeLong(imtdCreatedDate);
        objectOutput.writeInt(modifiedBy);
        objectOutput.writeInt(udc6);
        objectOutput.writeInt(udc5);
        objectOutput.writeInt(udc4);
        objectOutput.writeBoolean(checkRecord);
        objectOutput.writeInt(udc1);
        objectOutput.writeInt(udc2);
        objectOutput.writeInt(udc3);
        objectOutput.writeLong(contractPriceEndDate);
        objectOutput.writeDouble(totalVolumeCommitment);
        objectOutput.writeLong(priceProtectionEndDate);

        if (recordLockStatus == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(recordLockStatus);
        }

        objectOutput.writeLong(startDate);
        objectOutput.writeInt(rebateProgramType);

        if (sessionId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(sessionId);
        }

        if (itemName == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(itemName);
        }

        objectOutput.writeDouble(priceRevision);
        objectOutput.writeInt(rsModelSid);
        objectOutput.writeDouble(price);
        objectOutput.writeInt(rsAttachedStatus);
        objectOutput.writeInt(itemMasterSid);
        objectOutput.writeDouble(totalDollarCommitment);
        objectOutput.writeInt(itemType);
        objectOutput.writeDouble(totalMarketShareCommitmnet);

        if (itemId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(itemId);
        }

        objectOutput.writeDouble(basePrice);

        if (bundleNo == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(bundleNo);
        }

        if (formulaName == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(formulaName);
        }

        objectOutput.writeInt(psStatus);
        objectOutput.writeDouble(priceTolerance);
        objectOutput.writeLong(createdDate);
        objectOutput.writeInt(createdBy);
        objectOutput.writeInt(usersSid);
        objectOutput.writeInt(psDetailsSid);
        objectOutput.writeDouble(suggestedPrice);
        objectOutput.writeInt(psModelSid);

        if (formulaId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(formulaId);
        }

        if (commitmentPeriod == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(commitmentPeriod);
        }

        if (itemNo == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(itemNo);
        }

        objectOutput.writeDouble(contractPrice);
        objectOutput.writeInt(ifpDetailsSid);
        objectOutput.writeInt(ifpModelSid);

        if (priceToleranceType == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(priceToleranceType);
        }

        objectOutput.writeDouble(rebateAmount);
        objectOutput.writeLong(contractPriceStartDate);

        if (rebateScheduleType == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(rebateScheduleType);
        }

        if (priceToleranceFrequency == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(priceToleranceFrequency);
        }

        objectOutput.writeInt(imtdItemPriceRebateSid);

        if (rebatePlanSystemId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(rebatePlanSystemId);
        }

        objectOutput.writeLong(attachedDate);

        if (pricePlanId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(pricePlanId);
        }

        objectOutput.writeLong(itemRebateEndDate);
        objectOutput.writeInt(priceType);
        objectOutput.writeInt(priceToleranceInterval);
        objectOutput.writeLong(rsAttachedDate);
        objectOutput.writeLong(itemRebateStartDate);

        if (operation == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(operation);
        }

        objectOutput.writeInt(cfpModelSid);
        objectOutput.writeInt(rsDetailsSid);
        objectOutput.writeInt(attachedStatus);
        objectOutput.writeInt(primaryUom);

        if (packageSize == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(packageSize);
        }

        if (deductionCalendarMasterSid == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(deductionCalendarMasterSid);
        }

        if (rsContractDetailsDeductionCalendarNo == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(rsContractDetailsDeductionCalendarNo);
        }

        if (rsContractDetailsDeductionCalendarName == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(rsContractDetailsDeductionCalendarName);
        }

        if (netSalesFormulaMasterSid == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(netSalesFormulaMasterSid);
        }

        if (rsContractDetailsNetSalesFormulaNo == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(rsContractDetailsNetSalesFormulaNo);
        }

        if (rsContractDetailsNetSalesFormulaName == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(rsContractDetailsNetSalesFormulaName);
        }

        objectOutput.writeInt(formulaType);
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

        objectOutput.writeDouble(maxIncrementalChange);
        objectOutput.writeInt(resetEligible);
        objectOutput.writeInt(resetType);
        objectOutput.writeLong(resetDate);
        objectOutput.writeInt(resetInterval);
        objectOutput.writeInt(resetFrequency);
        objectOutput.writeInt(netPriceType);

        if (netPriceTypeFormula == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(netPriceTypeFormula);
        }

        objectOutput.writeInt(priceProtectionPriceType);
        objectOutput.writeDouble(nep);
        objectOutput.writeInt(nepFormula);

        if (brandMasterSid == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(brandMasterSid);
        }

        objectOutput.writeInt(priceProtectionStatus);
        objectOutput.writeInt(basePriceType);
        objectOutput.writeDouble(basePriceEntry);
        objectOutput.writeLong(basePriceDate);
        objectOutput.writeInt(basePriceDdlb);
        objectOutput.writeInt(netBasePrice);
        objectOutput.writeInt(subsequentPeriodPriceType);
        objectOutput.writeInt(resetPriceType);
        objectOutput.writeInt(netResetPriceType);
        objectOutput.writeInt(netResetPriceFormulaId);
        objectOutput.writeInt(netBasePriceFormulaId);
        objectOutput.writeInt(netSubsequentPriceFormulaId);
        objectOutput.writeInt(netSubsequentPeriodPrice);

        if (rsContractDetailsRebatePlanName == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(rsContractDetailsRebatePlanName);
        }

        if (rsContractDetailsFormulaNo == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(rsContractDetailsFormulaNo);
        }

        if (source == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(source);
        }
    }
}
