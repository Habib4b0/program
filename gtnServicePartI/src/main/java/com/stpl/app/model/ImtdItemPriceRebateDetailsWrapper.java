package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link ImtdItemPriceRebateDetails}.
 * </p>
 *
 * @author
 * @see ImtdItemPriceRebateDetails
 * @generated
 */
public class ImtdItemPriceRebateDetailsWrapper
    implements ImtdItemPriceRebateDetails,
        ModelWrapper<ImtdItemPriceRebateDetails> {
    private ImtdItemPriceRebateDetails _imtdItemPriceRebateDetails;

    public ImtdItemPriceRebateDetailsWrapper(
        ImtdItemPriceRebateDetails imtdItemPriceRebateDetails) {
        _imtdItemPriceRebateDetails = imtdItemPriceRebateDetails;
    }

    @Override
    public Class<?> getModelClass() {
        return ImtdItemPriceRebateDetails.class;
    }

    @Override
    public String getModelClassName() {
        return ImtdItemPriceRebateDetails.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("formulaMethodId", getFormulaMethodId());
        attributes.put("endDate", getEndDate());
        attributes.put("priceProtectionStartDate", getPriceProtectionStartDate());
        attributes.put("itemPriceRevisionDate", getItemPriceRevisionDate());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("rsCheckRecord", getRsCheckRecord());
        attributes.put("rebateRevisionDate", getRebateRevisionDate());
        attributes.put("contractMasterSid", getContractMasterSid());
        attributes.put("imtdCreatedDate", getImtdCreatedDate());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("udc6", getUdc6());
        attributes.put("udc5", getUdc5());
        attributes.put("udc4", getUdc4());
        attributes.put("checkRecord", getCheckRecord());
        attributes.put("udc1", getUdc1());
        attributes.put("udc2", getUdc2());
        attributes.put("udc3", getUdc3());
        attributes.put("contractPriceEndDate", getContractPriceEndDate());
        attributes.put("totalVolumeCommitment", getTotalVolumeCommitment());
        attributes.put("priceProtectionEndDate", getPriceProtectionEndDate());
        attributes.put("recordLockStatus", getRecordLockStatus());
        attributes.put("startDate", getStartDate());
        attributes.put("rebateProgramType", getRebateProgramType());
        attributes.put("sessionId", getSessionId());
        attributes.put("itemName", getItemName());
        attributes.put("priceRevision", getPriceRevision());
        attributes.put("rsModelSid", getRsModelSid());
        attributes.put("price", getPrice());
        attributes.put("rsAttachedStatus", getRsAttachedStatus());
        attributes.put("itemMasterSid", getItemMasterSid());
        attributes.put("totalDollarCommitment", getTotalDollarCommitment());
        attributes.put("itemType", getItemType());
        attributes.put("totalMarketShareCommitmnet",
            getTotalMarketShareCommitmnet());
        attributes.put("itemId", getItemId());
        attributes.put("basePrice", getBasePrice());
        attributes.put("bundleNo", getBundleNo());
        attributes.put("formulaName", getFormulaName());
        attributes.put("psStatus", getPsStatus());
        attributes.put("priceTolerance", getPriceTolerance());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("usersSid", getUsersSid());
        attributes.put("psDetailsSid", getPsDetailsSid());
        attributes.put("suggestedPrice", getSuggestedPrice());
        attributes.put("psModelSid", getPsModelSid());
        attributes.put("formulaId", getFormulaId());
        attributes.put("commitmentPeriod", getCommitmentPeriod());
        attributes.put("itemNo", getItemNo());
        attributes.put("contractPrice", getContractPrice());
        attributes.put("ifpDetailsSid", getIfpDetailsSid());
        attributes.put("ifpModelSid", getIfpModelSid());
        attributes.put("priceToleranceType", getPriceToleranceType());
        attributes.put("rebateAmount", getRebateAmount());
        attributes.put("contractPriceStartDate", getContractPriceStartDate());
        attributes.put("rebateScheduleType", getRebateScheduleType());
        attributes.put("priceToleranceFrequency", getPriceToleranceFrequency());
        attributes.put("imtdItemPriceRebateSid", getImtdItemPriceRebateSid());
        attributes.put("rebatePlanSystemId", getRebatePlanSystemId());
        attributes.put("attachedDate", getAttachedDate());
        attributes.put("pricePlanId", getPricePlanId());
        attributes.put("itemRebateEndDate", getItemRebateEndDate());
        attributes.put("priceType", getPriceType());
        attributes.put("priceToleranceInterval", getPriceToleranceInterval());
        attributes.put("rsAttachedDate", getRsAttachedDate());
        attributes.put("itemRebateStartDate", getItemRebateStartDate());
        attributes.put("operation", getOperation());
        attributes.put("cfpModelSid", getCfpModelSid());
        attributes.put("rsDetailsSid", getRsDetailsSid());
        attributes.put("attachedStatus", getAttachedStatus());
        attributes.put("primaryUom", getPrimaryUom());
        attributes.put("packageSize", getPackageSize());
        attributes.put("deductionCalendarMasterSid",
            getDeductionCalendarMasterSid());
        attributes.put("rsContractDetailsDeductionCalendarNo",
            getRsContractDetailsDeductionCalendarNo());
        attributes.put("rsContractDetailsDeductionCalendarName",
            getRsContractDetailsDeductionCalendarName());
        attributes.put("netSalesFormulaMasterSid", getNetSalesFormulaMasterSid());
        attributes.put("rsContractDetailsNetSalesFormulaNo",
            getRsContractDetailsNetSalesFormulaNo());
        attributes.put("rsContractDetailsNetSalesFormulaName",
            getRsContractDetailsNetSalesFormulaName());
        attributes.put("formulaType", getFormulaType());
        attributes.put("netSalesRule", getNetSalesRule());
        attributes.put("evaluationRule", getEvaluationRule());
        attributes.put("evaluationRuleBundle", getEvaluationRuleBundle());
        attributes.put("calculationRule", getCalculationRule());
        attributes.put("calculationRuleBundle", getCalculationRuleBundle());
        attributes.put("maxIncrementalChange", getMaxIncrementalChange());
        attributes.put("resetEligible", getResetEligible());
        attributes.put("resetType", getResetType());
        attributes.put("resetDate", getResetDate());
        attributes.put("resetInterval", getResetInterval());
        attributes.put("resetFrequency", getResetFrequency());
        attributes.put("netPriceType", getNetPriceType());
        attributes.put("netPriceTypeFormula", getNetPriceTypeFormula());
        attributes.put("priceProtectionPriceType", getPriceProtectionPriceType());
        attributes.put("nep", getNep());
        attributes.put("nepFormula", getNepFormula());
        attributes.put("brandMasterSid", getBrandMasterSid());
        attributes.put("priceProtectionStatus", getPriceProtectionStatus());
        attributes.put("basePriceType", getBasePriceType());
        attributes.put("basePriceEntry", getBasePriceEntry());
        attributes.put("basePriceDate", getBasePriceDate());
        attributes.put("basePriceDdlb", getBasePriceDdlb());
        attributes.put("netBasePrice", getNetBasePrice());
        attributes.put("subsequentPeriodPriceType",
            getSubsequentPeriodPriceType());
        attributes.put("resetPriceType", getResetPriceType());
        attributes.put("netResetPriceType", getNetResetPriceType());
        attributes.put("netResetPriceFormulaId", getNetResetPriceFormulaId());
        attributes.put("netBasePriceFormulaId", getNetBasePriceFormulaId());
        attributes.put("netSubsequentPriceFormulaId",
            getNetSubsequentPriceFormulaId());
        attributes.put("netSubsequentPeriodPrice", getNetSubsequentPeriodPrice());
        attributes.put("rsContractDetailsRebatePlanName",
            getRsContractDetailsRebatePlanName());
        attributes.put("rsContractDetailsFormulaNo",
            getRsContractDetailsFormulaNo());
        attributes.put("source", getSource());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String formulaMethodId = (String) attributes.get("formulaMethodId");

        if (formulaMethodId != null) {
            setFormulaMethodId(formulaMethodId);
        }

        Date endDate = (Date) attributes.get("endDate");

        if (endDate != null) {
            setEndDate(endDate);
        }

        Date priceProtectionStartDate = (Date) attributes.get(
                "priceProtectionStartDate");

        if (priceProtectionStartDate != null) {
            setPriceProtectionStartDate(priceProtectionStartDate);
        }

        Date itemPriceRevisionDate = (Date) attributes.get(
                "itemPriceRevisionDate");

        if (itemPriceRevisionDate != null) {
            setItemPriceRevisionDate(itemPriceRevisionDate);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        Boolean rsCheckRecord = (Boolean) attributes.get("rsCheckRecord");

        if (rsCheckRecord != null) {
            setRsCheckRecord(rsCheckRecord);
        }

        Date rebateRevisionDate = (Date) attributes.get("rebateRevisionDate");

        if (rebateRevisionDate != null) {
            setRebateRevisionDate(rebateRevisionDate);
        }

        Integer contractMasterSid = (Integer) attributes.get(
                "contractMasterSid");

        if (contractMasterSid != null) {
            setContractMasterSid(contractMasterSid);
        }

        Date imtdCreatedDate = (Date) attributes.get("imtdCreatedDate");

        if (imtdCreatedDate != null) {
            setImtdCreatedDate(imtdCreatedDate);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        Integer udc6 = (Integer) attributes.get("udc6");

        if (udc6 != null) {
            setUdc6(udc6);
        }

        Integer udc5 = (Integer) attributes.get("udc5");

        if (udc5 != null) {
            setUdc5(udc5);
        }

        Integer udc4 = (Integer) attributes.get("udc4");

        if (udc4 != null) {
            setUdc4(udc4);
        }

        Boolean checkRecord = (Boolean) attributes.get("checkRecord");

        if (checkRecord != null) {
            setCheckRecord(checkRecord);
        }

        Integer udc1 = (Integer) attributes.get("udc1");

        if (udc1 != null) {
            setUdc1(udc1);
        }

        Integer udc2 = (Integer) attributes.get("udc2");

        if (udc2 != null) {
            setUdc2(udc2);
        }

        Integer udc3 = (Integer) attributes.get("udc3");

        if (udc3 != null) {
            setUdc3(udc3);
        }

        Date contractPriceEndDate = (Date) attributes.get(
                "contractPriceEndDate");

        if (contractPriceEndDate != null) {
            setContractPriceEndDate(contractPriceEndDate);
        }

        Double totalVolumeCommitment = (Double) attributes.get(
                "totalVolumeCommitment");

        if (totalVolumeCommitment != null) {
            setTotalVolumeCommitment(totalVolumeCommitment);
        }

        Date priceProtectionEndDate = (Date) attributes.get(
                "priceProtectionEndDate");

        if (priceProtectionEndDate != null) {
            setPriceProtectionEndDate(priceProtectionEndDate);
        }

        String recordLockStatus = (String) attributes.get("recordLockStatus");

        if (recordLockStatus != null) {
            setRecordLockStatus(recordLockStatus);
        }

        Date startDate = (Date) attributes.get("startDate");

        if (startDate != null) {
            setStartDate(startDate);
        }

        Integer rebateProgramType = (Integer) attributes.get(
                "rebateProgramType");

        if (rebateProgramType != null) {
            setRebateProgramType(rebateProgramType);
        }

        String sessionId = (String) attributes.get("sessionId");

        if (sessionId != null) {
            setSessionId(sessionId);
        }

        String itemName = (String) attributes.get("itemName");

        if (itemName != null) {
            setItemName(itemName);
        }

        Double priceRevision = (Double) attributes.get("priceRevision");

        if (priceRevision != null) {
            setPriceRevision(priceRevision);
        }

        Integer rsModelSid = (Integer) attributes.get("rsModelSid");

        if (rsModelSid != null) {
            setRsModelSid(rsModelSid);
        }

        Double price = (Double) attributes.get("price");

        if (price != null) {
            setPrice(price);
        }

        Integer rsAttachedStatus = (Integer) attributes.get("rsAttachedStatus");

        if (rsAttachedStatus != null) {
            setRsAttachedStatus(rsAttachedStatus);
        }

        Integer itemMasterSid = (Integer) attributes.get("itemMasterSid");

        if (itemMasterSid != null) {
            setItemMasterSid(itemMasterSid);
        }

        Double totalDollarCommitment = (Double) attributes.get(
                "totalDollarCommitment");

        if (totalDollarCommitment != null) {
            setTotalDollarCommitment(totalDollarCommitment);
        }

        Integer itemType = (Integer) attributes.get("itemType");

        if (itemType != null) {
            setItemType(itemType);
        }

        Double totalMarketShareCommitmnet = (Double) attributes.get(
                "totalMarketShareCommitmnet");

        if (totalMarketShareCommitmnet != null) {
            setTotalMarketShareCommitmnet(totalMarketShareCommitmnet);
        }

        String itemId = (String) attributes.get("itemId");

        if (itemId != null) {
            setItemId(itemId);
        }

        Double basePrice = (Double) attributes.get("basePrice");

        if (basePrice != null) {
            setBasePrice(basePrice);
        }

        String bundleNo = (String) attributes.get("bundleNo");

        if (bundleNo != null) {
            setBundleNo(bundleNo);
        }

        String formulaName = (String) attributes.get("formulaName");

        if (formulaName != null) {
            setFormulaName(formulaName);
        }

        Integer psStatus = (Integer) attributes.get("psStatus");

        if (psStatus != null) {
            setPsStatus(psStatus);
        }

        Double priceTolerance = (Double) attributes.get("priceTolerance");

        if (priceTolerance != null) {
            setPriceTolerance(priceTolerance);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        Integer usersSid = (Integer) attributes.get("usersSid");

        if (usersSid != null) {
            setUsersSid(usersSid);
        }

        Integer psDetailsSid = (Integer) attributes.get("psDetailsSid");

        if (psDetailsSid != null) {
            setPsDetailsSid(psDetailsSid);
        }

        Double suggestedPrice = (Double) attributes.get("suggestedPrice");

        if (suggestedPrice != null) {
            setSuggestedPrice(suggestedPrice);
        }

        Integer psModelSid = (Integer) attributes.get("psModelSid");

        if (psModelSid != null) {
            setPsModelSid(psModelSid);
        }

        String formulaId = (String) attributes.get("formulaId");

        if (formulaId != null) {
            setFormulaId(formulaId);
        }

        String commitmentPeriod = (String) attributes.get("commitmentPeriod");

        if (commitmentPeriod != null) {
            setCommitmentPeriod(commitmentPeriod);
        }

        String itemNo = (String) attributes.get("itemNo");

        if (itemNo != null) {
            setItemNo(itemNo);
        }

        Double contractPrice = (Double) attributes.get("contractPrice");

        if (contractPrice != null) {
            setContractPrice(contractPrice);
        }

        Integer ifpDetailsSid = (Integer) attributes.get("ifpDetailsSid");

        if (ifpDetailsSid != null) {
            setIfpDetailsSid(ifpDetailsSid);
        }

        Integer ifpModelSid = (Integer) attributes.get("ifpModelSid");

        if (ifpModelSid != null) {
            setIfpModelSid(ifpModelSid);
        }

        String priceToleranceType = (String) attributes.get(
                "priceToleranceType");

        if (priceToleranceType != null) {
            setPriceToleranceType(priceToleranceType);
        }

        Double rebateAmount = (Double) attributes.get("rebateAmount");

        if (rebateAmount != null) {
            setRebateAmount(rebateAmount);
        }

        Date contractPriceStartDate = (Date) attributes.get(
                "contractPriceStartDate");

        if (contractPriceStartDate != null) {
            setContractPriceStartDate(contractPriceStartDate);
        }

        String rebateScheduleType = (String) attributes.get(
                "rebateScheduleType");

        if (rebateScheduleType != null) {
            setRebateScheduleType(rebateScheduleType);
        }

        String priceToleranceFrequency = (String) attributes.get(
                "priceToleranceFrequency");

        if (priceToleranceFrequency != null) {
            setPriceToleranceFrequency(priceToleranceFrequency);
        }

        Integer imtdItemPriceRebateSid = (Integer) attributes.get(
                "imtdItemPriceRebateSid");

        if (imtdItemPriceRebateSid != null) {
            setImtdItemPriceRebateSid(imtdItemPriceRebateSid);
        }

        String rebatePlanSystemId = (String) attributes.get(
                "rebatePlanSystemId");

        if (rebatePlanSystemId != null) {
            setRebatePlanSystemId(rebatePlanSystemId);
        }

        Date attachedDate = (Date) attributes.get("attachedDate");

        if (attachedDate != null) {
            setAttachedDate(attachedDate);
        }

        String pricePlanId = (String) attributes.get("pricePlanId");

        if (pricePlanId != null) {
            setPricePlanId(pricePlanId);
        }

        Date itemRebateEndDate = (Date) attributes.get("itemRebateEndDate");

        if (itemRebateEndDate != null) {
            setItemRebateEndDate(itemRebateEndDate);
        }

        Integer priceType = (Integer) attributes.get("priceType");

        if (priceType != null) {
            setPriceType(priceType);
        }

        Integer priceToleranceInterval = (Integer) attributes.get(
                "priceToleranceInterval");

        if (priceToleranceInterval != null) {
            setPriceToleranceInterval(priceToleranceInterval);
        }

        Date rsAttachedDate = (Date) attributes.get("rsAttachedDate");

        if (rsAttachedDate != null) {
            setRsAttachedDate(rsAttachedDate);
        }

        Date itemRebateStartDate = (Date) attributes.get("itemRebateStartDate");

        if (itemRebateStartDate != null) {
            setItemRebateStartDate(itemRebateStartDate);
        }

        String operation = (String) attributes.get("operation");

        if (operation != null) {
            setOperation(operation);
        }

        Integer cfpModelSid = (Integer) attributes.get("cfpModelSid");

        if (cfpModelSid != null) {
            setCfpModelSid(cfpModelSid);
        }

        Integer rsDetailsSid = (Integer) attributes.get("rsDetailsSid");

        if (rsDetailsSid != null) {
            setRsDetailsSid(rsDetailsSid);
        }

        Integer attachedStatus = (Integer) attributes.get("attachedStatus");

        if (attachedStatus != null) {
            setAttachedStatus(attachedStatus);
        }

        Integer primaryUom = (Integer) attributes.get("primaryUom");

        if (primaryUom != null) {
            setPrimaryUom(primaryUom);
        }

        String packageSize = (String) attributes.get("packageSize");

        if (packageSize != null) {
            setPackageSize(packageSize);
        }

        String deductionCalendarMasterSid = (String) attributes.get(
                "deductionCalendarMasterSid");

        if (deductionCalendarMasterSid != null) {
            setDeductionCalendarMasterSid(deductionCalendarMasterSid);
        }

        String rsContractDetailsDeductionCalendarNo = (String) attributes.get(
                "rsContractDetailsDeductionCalendarNo");

        if (rsContractDetailsDeductionCalendarNo != null) {
            setRsContractDetailsDeductionCalendarNo(rsContractDetailsDeductionCalendarNo);
        }

        String rsContractDetailsDeductionCalendarName = (String) attributes.get(
                "rsContractDetailsDeductionCalendarName");

        if (rsContractDetailsDeductionCalendarName != null) {
            setRsContractDetailsDeductionCalendarName(rsContractDetailsDeductionCalendarName);
        }

        String netSalesFormulaMasterSid = (String) attributes.get(
                "netSalesFormulaMasterSid");

        if (netSalesFormulaMasterSid != null) {
            setNetSalesFormulaMasterSid(netSalesFormulaMasterSid);
        }

        String rsContractDetailsNetSalesFormulaNo = (String) attributes.get(
                "rsContractDetailsNetSalesFormulaNo");

        if (rsContractDetailsNetSalesFormulaNo != null) {
            setRsContractDetailsNetSalesFormulaNo(rsContractDetailsNetSalesFormulaNo);
        }

        String rsContractDetailsNetSalesFormulaName = (String) attributes.get(
                "rsContractDetailsNetSalesFormulaName");

        if (rsContractDetailsNetSalesFormulaName != null) {
            setRsContractDetailsNetSalesFormulaName(rsContractDetailsNetSalesFormulaName);
        }

        Integer formulaType = (Integer) attributes.get("formulaType");

        if (formulaType != null) {
            setFormulaType(formulaType);
        }

        Integer netSalesRule = (Integer) attributes.get("netSalesRule");

        if (netSalesRule != null) {
            setNetSalesRule(netSalesRule);
        }

        Integer evaluationRule = (Integer) attributes.get("evaluationRule");

        if (evaluationRule != null) {
            setEvaluationRule(evaluationRule);
        }

        String evaluationRuleBundle = (String) attributes.get(
                "evaluationRuleBundle");

        if (evaluationRuleBundle != null) {
            setEvaluationRuleBundle(evaluationRuleBundle);
        }

        Integer calculationRule = (Integer) attributes.get("calculationRule");

        if (calculationRule != null) {
            setCalculationRule(calculationRule);
        }

        String calculationRuleBundle = (String) attributes.get(
                "calculationRuleBundle");

        if (calculationRuleBundle != null) {
            setCalculationRuleBundle(calculationRuleBundle);
        }

        Double maxIncrementalChange = (Double) attributes.get(
                "maxIncrementalChange");

        if (maxIncrementalChange != null) {
            setMaxIncrementalChange(maxIncrementalChange);
        }

        Integer resetEligible = (Integer) attributes.get("resetEligible");

        if (resetEligible != null) {
            setResetEligible(resetEligible);
        }

        Integer resetType = (Integer) attributes.get("resetType");

        if (resetType != null) {
            setResetType(resetType);
        }

        Date resetDate = (Date) attributes.get("resetDate");

        if (resetDate != null) {
            setResetDate(resetDate);
        }

        Integer resetInterval = (Integer) attributes.get("resetInterval");

        if (resetInterval != null) {
            setResetInterval(resetInterval);
        }

        Integer resetFrequency = (Integer) attributes.get("resetFrequency");

        if (resetFrequency != null) {
            setResetFrequency(resetFrequency);
        }

        Integer netPriceType = (Integer) attributes.get("netPriceType");

        if (netPriceType != null) {
            setNetPriceType(netPriceType);
        }

        String netPriceTypeFormula = (String) attributes.get(
                "netPriceTypeFormula");

        if (netPriceTypeFormula != null) {
            setNetPriceTypeFormula(netPriceTypeFormula);
        }

        Integer priceProtectionPriceType = (Integer) attributes.get(
                "priceProtectionPriceType");

        if (priceProtectionPriceType != null) {
            setPriceProtectionPriceType(priceProtectionPriceType);
        }

        Double nep = (Double) attributes.get("nep");

        if (nep != null) {
            setNep(nep);
        }

        Integer nepFormula = (Integer) attributes.get("nepFormula");

        if (nepFormula != null) {
            setNepFormula(nepFormula);
        }

        String brandMasterSid = (String) attributes.get("brandMasterSid");

        if (brandMasterSid != null) {
            setBrandMasterSid(brandMasterSid);
        }

        Integer priceProtectionStatus = (Integer) attributes.get(
                "priceProtectionStatus");

        if (priceProtectionStatus != null) {
            setPriceProtectionStatus(priceProtectionStatus);
        }

        Integer basePriceType = (Integer) attributes.get("basePriceType");

        if (basePriceType != null) {
            setBasePriceType(basePriceType);
        }

        Double basePriceEntry = (Double) attributes.get("basePriceEntry");

        if (basePriceEntry != null) {
            setBasePriceEntry(basePriceEntry);
        }

        Date basePriceDate = (Date) attributes.get("basePriceDate");

        if (basePriceDate != null) {
            setBasePriceDate(basePriceDate);
        }

        Integer basePriceDdlb = (Integer) attributes.get("basePriceDdlb");

        if (basePriceDdlb != null) {
            setBasePriceDdlb(basePriceDdlb);
        }

        Integer netBasePrice = (Integer) attributes.get("netBasePrice");

        if (netBasePrice != null) {
            setNetBasePrice(netBasePrice);
        }

        Integer subsequentPeriodPriceType = (Integer) attributes.get(
                "subsequentPeriodPriceType");

        if (subsequentPeriodPriceType != null) {
            setSubsequentPeriodPriceType(subsequentPeriodPriceType);
        }

        Integer resetPriceType = (Integer) attributes.get("resetPriceType");

        if (resetPriceType != null) {
            setResetPriceType(resetPriceType);
        }

        Integer netResetPriceType = (Integer) attributes.get(
                "netResetPriceType");

        if (netResetPriceType != null) {
            setNetResetPriceType(netResetPriceType);
        }

        Integer netResetPriceFormulaId = (Integer) attributes.get(
                "netResetPriceFormulaId");

        if (netResetPriceFormulaId != null) {
            setNetResetPriceFormulaId(netResetPriceFormulaId);
        }

        Integer netBasePriceFormulaId = (Integer) attributes.get(
                "netBasePriceFormulaId");

        if (netBasePriceFormulaId != null) {
            setNetBasePriceFormulaId(netBasePriceFormulaId);
        }

        Integer netSubsequentPriceFormulaId = (Integer) attributes.get(
                "netSubsequentPriceFormulaId");

        if (netSubsequentPriceFormulaId != null) {
            setNetSubsequentPriceFormulaId(netSubsequentPriceFormulaId);
        }

        Integer netSubsequentPeriodPrice = (Integer) attributes.get(
                "netSubsequentPeriodPrice");

        if (netSubsequentPeriodPrice != null) {
            setNetSubsequentPeriodPrice(netSubsequentPeriodPrice);
        }

        String rsContractDetailsRebatePlanName = (String) attributes.get(
                "rsContractDetailsRebatePlanName");

        if (rsContractDetailsRebatePlanName != null) {
            setRsContractDetailsRebatePlanName(rsContractDetailsRebatePlanName);
        }

        String rsContractDetailsFormulaNo = (String) attributes.get(
                "rsContractDetailsFormulaNo");

        if (rsContractDetailsFormulaNo != null) {
            setRsContractDetailsFormulaNo(rsContractDetailsFormulaNo);
        }

        String source = (String) attributes.get("source");

        if (source != null) {
            setSource(source);
        }
    }

    /**
    * Returns the primary key of this imtd item price rebate details.
    *
    * @return the primary key of this imtd item price rebate details
    */
    @Override
    public int getPrimaryKey() {
        return _imtdItemPriceRebateDetails.getPrimaryKey();
    }

    /**
    * Sets the primary key of this imtd item price rebate details.
    *
    * @param primaryKey the primary key of this imtd item price rebate details
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _imtdItemPriceRebateDetails.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the formula method ID of this imtd item price rebate details.
    *
    * @return the formula method ID of this imtd item price rebate details
    */
    @Override
    public java.lang.String getFormulaMethodId() {
        return _imtdItemPriceRebateDetails.getFormulaMethodId();
    }

    /**
    * Sets the formula method ID of this imtd item price rebate details.
    *
    * @param formulaMethodId the formula method ID of this imtd item price rebate details
    */
    @Override
    public void setFormulaMethodId(java.lang.String formulaMethodId) {
        _imtdItemPriceRebateDetails.setFormulaMethodId(formulaMethodId);
    }

    /**
    * Returns the end date of this imtd item price rebate details.
    *
    * @return the end date of this imtd item price rebate details
    */
    @Override
    public java.util.Date getEndDate() {
        return _imtdItemPriceRebateDetails.getEndDate();
    }

    /**
    * Sets the end date of this imtd item price rebate details.
    *
    * @param endDate the end date of this imtd item price rebate details
    */
    @Override
    public void setEndDate(java.util.Date endDate) {
        _imtdItemPriceRebateDetails.setEndDate(endDate);
    }

    /**
    * Returns the price protection start date of this imtd item price rebate details.
    *
    * @return the price protection start date of this imtd item price rebate details
    */
    @Override
    public java.util.Date getPriceProtectionStartDate() {
        return _imtdItemPriceRebateDetails.getPriceProtectionStartDate();
    }

    /**
    * Sets the price protection start date of this imtd item price rebate details.
    *
    * @param priceProtectionStartDate the price protection start date of this imtd item price rebate details
    */
    @Override
    public void setPriceProtectionStartDate(
        java.util.Date priceProtectionStartDate) {
        _imtdItemPriceRebateDetails.setPriceProtectionStartDate(priceProtectionStartDate);
    }

    /**
    * Returns the item price revision date of this imtd item price rebate details.
    *
    * @return the item price revision date of this imtd item price rebate details
    */
    @Override
    public java.util.Date getItemPriceRevisionDate() {
        return _imtdItemPriceRebateDetails.getItemPriceRevisionDate();
    }

    /**
    * Sets the item price revision date of this imtd item price rebate details.
    *
    * @param itemPriceRevisionDate the item price revision date of this imtd item price rebate details
    */
    @Override
    public void setItemPriceRevisionDate(java.util.Date itemPriceRevisionDate) {
        _imtdItemPriceRebateDetails.setItemPriceRevisionDate(itemPriceRevisionDate);
    }

    /**
    * Returns the modified date of this imtd item price rebate details.
    *
    * @return the modified date of this imtd item price rebate details
    */
    @Override
    public java.util.Date getModifiedDate() {
        return _imtdItemPriceRebateDetails.getModifiedDate();
    }

    /**
    * Sets the modified date of this imtd item price rebate details.
    *
    * @param modifiedDate the modified date of this imtd item price rebate details
    */
    @Override
    public void setModifiedDate(java.util.Date modifiedDate) {
        _imtdItemPriceRebateDetails.setModifiedDate(modifiedDate);
    }

    /**
    * Returns the rs check record of this imtd item price rebate details.
    *
    * @return the rs check record of this imtd item price rebate details
    */
    @Override
    public boolean getRsCheckRecord() {
        return _imtdItemPriceRebateDetails.getRsCheckRecord();
    }

    /**
    * Returns <code>true</code> if this imtd item price rebate details is rs check record.
    *
    * @return <code>true</code> if this imtd item price rebate details is rs check record; <code>false</code> otherwise
    */
    @Override
    public boolean isRsCheckRecord() {
        return _imtdItemPriceRebateDetails.isRsCheckRecord();
    }

    /**
    * Sets whether this imtd item price rebate details is rs check record.
    *
    * @param rsCheckRecord the rs check record of this imtd item price rebate details
    */
    @Override
    public void setRsCheckRecord(boolean rsCheckRecord) {
        _imtdItemPriceRebateDetails.setRsCheckRecord(rsCheckRecord);
    }

    /**
    * Returns the rebate revision date of this imtd item price rebate details.
    *
    * @return the rebate revision date of this imtd item price rebate details
    */
    @Override
    public java.util.Date getRebateRevisionDate() {
        return _imtdItemPriceRebateDetails.getRebateRevisionDate();
    }

    /**
    * Sets the rebate revision date of this imtd item price rebate details.
    *
    * @param rebateRevisionDate the rebate revision date of this imtd item price rebate details
    */
    @Override
    public void setRebateRevisionDate(java.util.Date rebateRevisionDate) {
        _imtdItemPriceRebateDetails.setRebateRevisionDate(rebateRevisionDate);
    }

    /**
    * Returns the contract master sid of this imtd item price rebate details.
    *
    * @return the contract master sid of this imtd item price rebate details
    */
    @Override
    public int getContractMasterSid() {
        return _imtdItemPriceRebateDetails.getContractMasterSid();
    }

    /**
    * Sets the contract master sid of this imtd item price rebate details.
    *
    * @param contractMasterSid the contract master sid of this imtd item price rebate details
    */
    @Override
    public void setContractMasterSid(int contractMasterSid) {
        _imtdItemPriceRebateDetails.setContractMasterSid(contractMasterSid);
    }

    /**
    * Returns the imtd created date of this imtd item price rebate details.
    *
    * @return the imtd created date of this imtd item price rebate details
    */
    @Override
    public java.util.Date getImtdCreatedDate() {
        return _imtdItemPriceRebateDetails.getImtdCreatedDate();
    }

    /**
    * Sets the imtd created date of this imtd item price rebate details.
    *
    * @param imtdCreatedDate the imtd created date of this imtd item price rebate details
    */
    @Override
    public void setImtdCreatedDate(java.util.Date imtdCreatedDate) {
        _imtdItemPriceRebateDetails.setImtdCreatedDate(imtdCreatedDate);
    }

    /**
    * Returns the modified by of this imtd item price rebate details.
    *
    * @return the modified by of this imtd item price rebate details
    */
    @Override
    public int getModifiedBy() {
        return _imtdItemPriceRebateDetails.getModifiedBy();
    }

    /**
    * Sets the modified by of this imtd item price rebate details.
    *
    * @param modifiedBy the modified by of this imtd item price rebate details
    */
    @Override
    public void setModifiedBy(int modifiedBy) {
        _imtdItemPriceRebateDetails.setModifiedBy(modifiedBy);
    }

    /**
    * Returns the udc6 of this imtd item price rebate details.
    *
    * @return the udc6 of this imtd item price rebate details
    */
    @Override
    public int getUdc6() {
        return _imtdItemPriceRebateDetails.getUdc6();
    }

    /**
    * Sets the udc6 of this imtd item price rebate details.
    *
    * @param udc6 the udc6 of this imtd item price rebate details
    */
    @Override
    public void setUdc6(int udc6) {
        _imtdItemPriceRebateDetails.setUdc6(udc6);
    }

    /**
    * Returns the udc5 of this imtd item price rebate details.
    *
    * @return the udc5 of this imtd item price rebate details
    */
    @Override
    public int getUdc5() {
        return _imtdItemPriceRebateDetails.getUdc5();
    }

    /**
    * Sets the udc5 of this imtd item price rebate details.
    *
    * @param udc5 the udc5 of this imtd item price rebate details
    */
    @Override
    public void setUdc5(int udc5) {
        _imtdItemPriceRebateDetails.setUdc5(udc5);
    }

    /**
    * Returns the udc4 of this imtd item price rebate details.
    *
    * @return the udc4 of this imtd item price rebate details
    */
    @Override
    public int getUdc4() {
        return _imtdItemPriceRebateDetails.getUdc4();
    }

    /**
    * Sets the udc4 of this imtd item price rebate details.
    *
    * @param udc4 the udc4 of this imtd item price rebate details
    */
    @Override
    public void setUdc4(int udc4) {
        _imtdItemPriceRebateDetails.setUdc4(udc4);
    }

    /**
    * Returns the check record of this imtd item price rebate details.
    *
    * @return the check record of this imtd item price rebate details
    */
    @Override
    public boolean getCheckRecord() {
        return _imtdItemPriceRebateDetails.getCheckRecord();
    }

    /**
    * Returns <code>true</code> if this imtd item price rebate details is check record.
    *
    * @return <code>true</code> if this imtd item price rebate details is check record; <code>false</code> otherwise
    */
    @Override
    public boolean isCheckRecord() {
        return _imtdItemPriceRebateDetails.isCheckRecord();
    }

    /**
    * Sets whether this imtd item price rebate details is check record.
    *
    * @param checkRecord the check record of this imtd item price rebate details
    */
    @Override
    public void setCheckRecord(boolean checkRecord) {
        _imtdItemPriceRebateDetails.setCheckRecord(checkRecord);
    }

    /**
    * Returns the udc1 of this imtd item price rebate details.
    *
    * @return the udc1 of this imtd item price rebate details
    */
    @Override
    public int getUdc1() {
        return _imtdItemPriceRebateDetails.getUdc1();
    }

    /**
    * Sets the udc1 of this imtd item price rebate details.
    *
    * @param udc1 the udc1 of this imtd item price rebate details
    */
    @Override
    public void setUdc1(int udc1) {
        _imtdItemPriceRebateDetails.setUdc1(udc1);
    }

    /**
    * Returns the udc2 of this imtd item price rebate details.
    *
    * @return the udc2 of this imtd item price rebate details
    */
    @Override
    public int getUdc2() {
        return _imtdItemPriceRebateDetails.getUdc2();
    }

    /**
    * Sets the udc2 of this imtd item price rebate details.
    *
    * @param udc2 the udc2 of this imtd item price rebate details
    */
    @Override
    public void setUdc2(int udc2) {
        _imtdItemPriceRebateDetails.setUdc2(udc2);
    }

    /**
    * Returns the udc3 of this imtd item price rebate details.
    *
    * @return the udc3 of this imtd item price rebate details
    */
    @Override
    public int getUdc3() {
        return _imtdItemPriceRebateDetails.getUdc3();
    }

    /**
    * Sets the udc3 of this imtd item price rebate details.
    *
    * @param udc3 the udc3 of this imtd item price rebate details
    */
    @Override
    public void setUdc3(int udc3) {
        _imtdItemPriceRebateDetails.setUdc3(udc3);
    }

    /**
    * Returns the contract price end date of this imtd item price rebate details.
    *
    * @return the contract price end date of this imtd item price rebate details
    */
    @Override
    public java.util.Date getContractPriceEndDate() {
        return _imtdItemPriceRebateDetails.getContractPriceEndDate();
    }

    /**
    * Sets the contract price end date of this imtd item price rebate details.
    *
    * @param contractPriceEndDate the contract price end date of this imtd item price rebate details
    */
    @Override
    public void setContractPriceEndDate(java.util.Date contractPriceEndDate) {
        _imtdItemPriceRebateDetails.setContractPriceEndDate(contractPriceEndDate);
    }

    /**
    * Returns the total volume commitment of this imtd item price rebate details.
    *
    * @return the total volume commitment of this imtd item price rebate details
    */
    @Override
    public double getTotalVolumeCommitment() {
        return _imtdItemPriceRebateDetails.getTotalVolumeCommitment();
    }

    /**
    * Sets the total volume commitment of this imtd item price rebate details.
    *
    * @param totalVolumeCommitment the total volume commitment of this imtd item price rebate details
    */
    @Override
    public void setTotalVolumeCommitment(double totalVolumeCommitment) {
        _imtdItemPriceRebateDetails.setTotalVolumeCommitment(totalVolumeCommitment);
    }

    /**
    * Returns the price protection end date of this imtd item price rebate details.
    *
    * @return the price protection end date of this imtd item price rebate details
    */
    @Override
    public java.util.Date getPriceProtectionEndDate() {
        return _imtdItemPriceRebateDetails.getPriceProtectionEndDate();
    }

    /**
    * Sets the price protection end date of this imtd item price rebate details.
    *
    * @param priceProtectionEndDate the price protection end date of this imtd item price rebate details
    */
    @Override
    public void setPriceProtectionEndDate(java.util.Date priceProtectionEndDate) {
        _imtdItemPriceRebateDetails.setPriceProtectionEndDate(priceProtectionEndDate);
    }

    /**
    * Returns the record lock status of this imtd item price rebate details.
    *
    * @return the record lock status of this imtd item price rebate details
    */
    @Override
    public java.lang.String getRecordLockStatus() {
        return _imtdItemPriceRebateDetails.getRecordLockStatus();
    }

    /**
    * Sets the record lock status of this imtd item price rebate details.
    *
    * @param recordLockStatus the record lock status of this imtd item price rebate details
    */
    @Override
    public void setRecordLockStatus(java.lang.String recordLockStatus) {
        _imtdItemPriceRebateDetails.setRecordLockStatus(recordLockStatus);
    }

    /**
    * Returns the start date of this imtd item price rebate details.
    *
    * @return the start date of this imtd item price rebate details
    */
    @Override
    public java.util.Date getStartDate() {
        return _imtdItemPriceRebateDetails.getStartDate();
    }

    /**
    * Sets the start date of this imtd item price rebate details.
    *
    * @param startDate the start date of this imtd item price rebate details
    */
    @Override
    public void setStartDate(java.util.Date startDate) {
        _imtdItemPriceRebateDetails.setStartDate(startDate);
    }

    /**
    * Returns the rebate program type of this imtd item price rebate details.
    *
    * @return the rebate program type of this imtd item price rebate details
    */
    @Override
    public int getRebateProgramType() {
        return _imtdItemPriceRebateDetails.getRebateProgramType();
    }

    /**
    * Sets the rebate program type of this imtd item price rebate details.
    *
    * @param rebateProgramType the rebate program type of this imtd item price rebate details
    */
    @Override
    public void setRebateProgramType(int rebateProgramType) {
        _imtdItemPriceRebateDetails.setRebateProgramType(rebateProgramType);
    }

    /**
    * Returns the session ID of this imtd item price rebate details.
    *
    * @return the session ID of this imtd item price rebate details
    */
    @Override
    public java.lang.String getSessionId() {
        return _imtdItemPriceRebateDetails.getSessionId();
    }

    /**
    * Sets the session ID of this imtd item price rebate details.
    *
    * @param sessionId the session ID of this imtd item price rebate details
    */
    @Override
    public void setSessionId(java.lang.String sessionId) {
        _imtdItemPriceRebateDetails.setSessionId(sessionId);
    }

    /**
    * Returns the item name of this imtd item price rebate details.
    *
    * @return the item name of this imtd item price rebate details
    */
    @Override
    public java.lang.String getItemName() {
        return _imtdItemPriceRebateDetails.getItemName();
    }

    /**
    * Sets the item name of this imtd item price rebate details.
    *
    * @param itemName the item name of this imtd item price rebate details
    */
    @Override
    public void setItemName(java.lang.String itemName) {
        _imtdItemPriceRebateDetails.setItemName(itemName);
    }

    /**
    * Returns the price revision of this imtd item price rebate details.
    *
    * @return the price revision of this imtd item price rebate details
    */
    @Override
    public double getPriceRevision() {
        return _imtdItemPriceRebateDetails.getPriceRevision();
    }

    /**
    * Sets the price revision of this imtd item price rebate details.
    *
    * @param priceRevision the price revision of this imtd item price rebate details
    */
    @Override
    public void setPriceRevision(double priceRevision) {
        _imtdItemPriceRebateDetails.setPriceRevision(priceRevision);
    }

    /**
    * Returns the rs model sid of this imtd item price rebate details.
    *
    * @return the rs model sid of this imtd item price rebate details
    */
    @Override
    public int getRsModelSid() {
        return _imtdItemPriceRebateDetails.getRsModelSid();
    }

    /**
    * Sets the rs model sid of this imtd item price rebate details.
    *
    * @param rsModelSid the rs model sid of this imtd item price rebate details
    */
    @Override
    public void setRsModelSid(int rsModelSid) {
        _imtdItemPriceRebateDetails.setRsModelSid(rsModelSid);
    }

    /**
    * Returns the price of this imtd item price rebate details.
    *
    * @return the price of this imtd item price rebate details
    */
    @Override
    public double getPrice() {
        return _imtdItemPriceRebateDetails.getPrice();
    }

    /**
    * Sets the price of this imtd item price rebate details.
    *
    * @param price the price of this imtd item price rebate details
    */
    @Override
    public void setPrice(double price) {
        _imtdItemPriceRebateDetails.setPrice(price);
    }

    /**
    * Returns the rs attached status of this imtd item price rebate details.
    *
    * @return the rs attached status of this imtd item price rebate details
    */
    @Override
    public int getRsAttachedStatus() {
        return _imtdItemPriceRebateDetails.getRsAttachedStatus();
    }

    /**
    * Sets the rs attached status of this imtd item price rebate details.
    *
    * @param rsAttachedStatus the rs attached status of this imtd item price rebate details
    */
    @Override
    public void setRsAttachedStatus(int rsAttachedStatus) {
        _imtdItemPriceRebateDetails.setRsAttachedStatus(rsAttachedStatus);
    }

    /**
    * Returns the item master sid of this imtd item price rebate details.
    *
    * @return the item master sid of this imtd item price rebate details
    */
    @Override
    public int getItemMasterSid() {
        return _imtdItemPriceRebateDetails.getItemMasterSid();
    }

    /**
    * Sets the item master sid of this imtd item price rebate details.
    *
    * @param itemMasterSid the item master sid of this imtd item price rebate details
    */
    @Override
    public void setItemMasterSid(int itemMasterSid) {
        _imtdItemPriceRebateDetails.setItemMasterSid(itemMasterSid);
    }

    /**
    * Returns the total dollar commitment of this imtd item price rebate details.
    *
    * @return the total dollar commitment of this imtd item price rebate details
    */
    @Override
    public double getTotalDollarCommitment() {
        return _imtdItemPriceRebateDetails.getTotalDollarCommitment();
    }

    /**
    * Sets the total dollar commitment of this imtd item price rebate details.
    *
    * @param totalDollarCommitment the total dollar commitment of this imtd item price rebate details
    */
    @Override
    public void setTotalDollarCommitment(double totalDollarCommitment) {
        _imtdItemPriceRebateDetails.setTotalDollarCommitment(totalDollarCommitment);
    }

    /**
    * Returns the item type of this imtd item price rebate details.
    *
    * @return the item type of this imtd item price rebate details
    */
    @Override
    public int getItemType() {
        return _imtdItemPriceRebateDetails.getItemType();
    }

    /**
    * Sets the item type of this imtd item price rebate details.
    *
    * @param itemType the item type of this imtd item price rebate details
    */
    @Override
    public void setItemType(int itemType) {
        _imtdItemPriceRebateDetails.setItemType(itemType);
    }

    /**
    * Returns the total market share commitmnet of this imtd item price rebate details.
    *
    * @return the total market share commitmnet of this imtd item price rebate details
    */
    @Override
    public double getTotalMarketShareCommitmnet() {
        return _imtdItemPriceRebateDetails.getTotalMarketShareCommitmnet();
    }

    /**
    * Sets the total market share commitmnet of this imtd item price rebate details.
    *
    * @param totalMarketShareCommitmnet the total market share commitmnet of this imtd item price rebate details
    */
    @Override
    public void setTotalMarketShareCommitmnet(double totalMarketShareCommitmnet) {
        _imtdItemPriceRebateDetails.setTotalMarketShareCommitmnet(totalMarketShareCommitmnet);
    }

    /**
    * Returns the item ID of this imtd item price rebate details.
    *
    * @return the item ID of this imtd item price rebate details
    */
    @Override
    public java.lang.String getItemId() {
        return _imtdItemPriceRebateDetails.getItemId();
    }

    /**
    * Sets the item ID of this imtd item price rebate details.
    *
    * @param itemId the item ID of this imtd item price rebate details
    */
    @Override
    public void setItemId(java.lang.String itemId) {
        _imtdItemPriceRebateDetails.setItemId(itemId);
    }

    /**
    * Returns the base price of this imtd item price rebate details.
    *
    * @return the base price of this imtd item price rebate details
    */
    @Override
    public double getBasePrice() {
        return _imtdItemPriceRebateDetails.getBasePrice();
    }

    /**
    * Sets the base price of this imtd item price rebate details.
    *
    * @param basePrice the base price of this imtd item price rebate details
    */
    @Override
    public void setBasePrice(double basePrice) {
        _imtdItemPriceRebateDetails.setBasePrice(basePrice);
    }

    /**
    * Returns the bundle no of this imtd item price rebate details.
    *
    * @return the bundle no of this imtd item price rebate details
    */
    @Override
    public java.lang.String getBundleNo() {
        return _imtdItemPriceRebateDetails.getBundleNo();
    }

    /**
    * Sets the bundle no of this imtd item price rebate details.
    *
    * @param bundleNo the bundle no of this imtd item price rebate details
    */
    @Override
    public void setBundleNo(java.lang.String bundleNo) {
        _imtdItemPriceRebateDetails.setBundleNo(bundleNo);
    }

    /**
    * Returns the formula name of this imtd item price rebate details.
    *
    * @return the formula name of this imtd item price rebate details
    */
    @Override
    public java.lang.String getFormulaName() {
        return _imtdItemPriceRebateDetails.getFormulaName();
    }

    /**
    * Sets the formula name of this imtd item price rebate details.
    *
    * @param formulaName the formula name of this imtd item price rebate details
    */
    @Override
    public void setFormulaName(java.lang.String formulaName) {
        _imtdItemPriceRebateDetails.setFormulaName(formulaName);
    }

    /**
    * Returns the ps status of this imtd item price rebate details.
    *
    * @return the ps status of this imtd item price rebate details
    */
    @Override
    public int getPsStatus() {
        return _imtdItemPriceRebateDetails.getPsStatus();
    }

    /**
    * Sets the ps status of this imtd item price rebate details.
    *
    * @param psStatus the ps status of this imtd item price rebate details
    */
    @Override
    public void setPsStatus(int psStatus) {
        _imtdItemPriceRebateDetails.setPsStatus(psStatus);
    }

    /**
    * Returns the price tolerance of this imtd item price rebate details.
    *
    * @return the price tolerance of this imtd item price rebate details
    */
    @Override
    public double getPriceTolerance() {
        return _imtdItemPriceRebateDetails.getPriceTolerance();
    }

    /**
    * Sets the price tolerance of this imtd item price rebate details.
    *
    * @param priceTolerance the price tolerance of this imtd item price rebate details
    */
    @Override
    public void setPriceTolerance(double priceTolerance) {
        _imtdItemPriceRebateDetails.setPriceTolerance(priceTolerance);
    }

    /**
    * Returns the created date of this imtd item price rebate details.
    *
    * @return the created date of this imtd item price rebate details
    */
    @Override
    public java.util.Date getCreatedDate() {
        return _imtdItemPriceRebateDetails.getCreatedDate();
    }

    /**
    * Sets the created date of this imtd item price rebate details.
    *
    * @param createdDate the created date of this imtd item price rebate details
    */
    @Override
    public void setCreatedDate(java.util.Date createdDate) {
        _imtdItemPriceRebateDetails.setCreatedDate(createdDate);
    }

    /**
    * Returns the created by of this imtd item price rebate details.
    *
    * @return the created by of this imtd item price rebate details
    */
    @Override
    public int getCreatedBy() {
        return _imtdItemPriceRebateDetails.getCreatedBy();
    }

    /**
    * Sets the created by of this imtd item price rebate details.
    *
    * @param createdBy the created by of this imtd item price rebate details
    */
    @Override
    public void setCreatedBy(int createdBy) {
        _imtdItemPriceRebateDetails.setCreatedBy(createdBy);
    }

    /**
    * Returns the users sid of this imtd item price rebate details.
    *
    * @return the users sid of this imtd item price rebate details
    */
    @Override
    public int getUsersSid() {
        return _imtdItemPriceRebateDetails.getUsersSid();
    }

    /**
    * Sets the users sid of this imtd item price rebate details.
    *
    * @param usersSid the users sid of this imtd item price rebate details
    */
    @Override
    public void setUsersSid(int usersSid) {
        _imtdItemPriceRebateDetails.setUsersSid(usersSid);
    }

    /**
    * Returns the ps details sid of this imtd item price rebate details.
    *
    * @return the ps details sid of this imtd item price rebate details
    */
    @Override
    public int getPsDetailsSid() {
        return _imtdItemPriceRebateDetails.getPsDetailsSid();
    }

    /**
    * Sets the ps details sid of this imtd item price rebate details.
    *
    * @param psDetailsSid the ps details sid of this imtd item price rebate details
    */
    @Override
    public void setPsDetailsSid(int psDetailsSid) {
        _imtdItemPriceRebateDetails.setPsDetailsSid(psDetailsSid);
    }

    /**
    * Returns the suggested price of this imtd item price rebate details.
    *
    * @return the suggested price of this imtd item price rebate details
    */
    @Override
    public double getSuggestedPrice() {
        return _imtdItemPriceRebateDetails.getSuggestedPrice();
    }

    /**
    * Sets the suggested price of this imtd item price rebate details.
    *
    * @param suggestedPrice the suggested price of this imtd item price rebate details
    */
    @Override
    public void setSuggestedPrice(double suggestedPrice) {
        _imtdItemPriceRebateDetails.setSuggestedPrice(suggestedPrice);
    }

    /**
    * Returns the ps model sid of this imtd item price rebate details.
    *
    * @return the ps model sid of this imtd item price rebate details
    */
    @Override
    public int getPsModelSid() {
        return _imtdItemPriceRebateDetails.getPsModelSid();
    }

    /**
    * Sets the ps model sid of this imtd item price rebate details.
    *
    * @param psModelSid the ps model sid of this imtd item price rebate details
    */
    @Override
    public void setPsModelSid(int psModelSid) {
        _imtdItemPriceRebateDetails.setPsModelSid(psModelSid);
    }

    /**
    * Returns the formula ID of this imtd item price rebate details.
    *
    * @return the formula ID of this imtd item price rebate details
    */
    @Override
    public java.lang.String getFormulaId() {
        return _imtdItemPriceRebateDetails.getFormulaId();
    }

    /**
    * Sets the formula ID of this imtd item price rebate details.
    *
    * @param formulaId the formula ID of this imtd item price rebate details
    */
    @Override
    public void setFormulaId(java.lang.String formulaId) {
        _imtdItemPriceRebateDetails.setFormulaId(formulaId);
    }

    /**
    * Returns the commitment period of this imtd item price rebate details.
    *
    * @return the commitment period of this imtd item price rebate details
    */
    @Override
    public java.lang.String getCommitmentPeriod() {
        return _imtdItemPriceRebateDetails.getCommitmentPeriod();
    }

    /**
    * Sets the commitment period of this imtd item price rebate details.
    *
    * @param commitmentPeriod the commitment period of this imtd item price rebate details
    */
    @Override
    public void setCommitmentPeriod(java.lang.String commitmentPeriod) {
        _imtdItemPriceRebateDetails.setCommitmentPeriod(commitmentPeriod);
    }

    /**
    * Returns the item no of this imtd item price rebate details.
    *
    * @return the item no of this imtd item price rebate details
    */
    @Override
    public java.lang.String getItemNo() {
        return _imtdItemPriceRebateDetails.getItemNo();
    }

    /**
    * Sets the item no of this imtd item price rebate details.
    *
    * @param itemNo the item no of this imtd item price rebate details
    */
    @Override
    public void setItemNo(java.lang.String itemNo) {
        _imtdItemPriceRebateDetails.setItemNo(itemNo);
    }

    /**
    * Returns the contract price of this imtd item price rebate details.
    *
    * @return the contract price of this imtd item price rebate details
    */
    @Override
    public double getContractPrice() {
        return _imtdItemPriceRebateDetails.getContractPrice();
    }

    /**
    * Sets the contract price of this imtd item price rebate details.
    *
    * @param contractPrice the contract price of this imtd item price rebate details
    */
    @Override
    public void setContractPrice(double contractPrice) {
        _imtdItemPriceRebateDetails.setContractPrice(contractPrice);
    }

    /**
    * Returns the ifp details sid of this imtd item price rebate details.
    *
    * @return the ifp details sid of this imtd item price rebate details
    */
    @Override
    public int getIfpDetailsSid() {
        return _imtdItemPriceRebateDetails.getIfpDetailsSid();
    }

    /**
    * Sets the ifp details sid of this imtd item price rebate details.
    *
    * @param ifpDetailsSid the ifp details sid of this imtd item price rebate details
    */
    @Override
    public void setIfpDetailsSid(int ifpDetailsSid) {
        _imtdItemPriceRebateDetails.setIfpDetailsSid(ifpDetailsSid);
    }

    /**
    * Returns the ifp model sid of this imtd item price rebate details.
    *
    * @return the ifp model sid of this imtd item price rebate details
    */
    @Override
    public int getIfpModelSid() {
        return _imtdItemPriceRebateDetails.getIfpModelSid();
    }

    /**
    * Sets the ifp model sid of this imtd item price rebate details.
    *
    * @param ifpModelSid the ifp model sid of this imtd item price rebate details
    */
    @Override
    public void setIfpModelSid(int ifpModelSid) {
        _imtdItemPriceRebateDetails.setIfpModelSid(ifpModelSid);
    }

    /**
    * Returns the price tolerance type of this imtd item price rebate details.
    *
    * @return the price tolerance type of this imtd item price rebate details
    */
    @Override
    public java.lang.String getPriceToleranceType() {
        return _imtdItemPriceRebateDetails.getPriceToleranceType();
    }

    /**
    * Sets the price tolerance type of this imtd item price rebate details.
    *
    * @param priceToleranceType the price tolerance type of this imtd item price rebate details
    */
    @Override
    public void setPriceToleranceType(java.lang.String priceToleranceType) {
        _imtdItemPriceRebateDetails.setPriceToleranceType(priceToleranceType);
    }

    /**
    * Returns the rebate amount of this imtd item price rebate details.
    *
    * @return the rebate amount of this imtd item price rebate details
    */
    @Override
    public double getRebateAmount() {
        return _imtdItemPriceRebateDetails.getRebateAmount();
    }

    /**
    * Sets the rebate amount of this imtd item price rebate details.
    *
    * @param rebateAmount the rebate amount of this imtd item price rebate details
    */
    @Override
    public void setRebateAmount(double rebateAmount) {
        _imtdItemPriceRebateDetails.setRebateAmount(rebateAmount);
    }

    /**
    * Returns the contract price start date of this imtd item price rebate details.
    *
    * @return the contract price start date of this imtd item price rebate details
    */
    @Override
    public java.util.Date getContractPriceStartDate() {
        return _imtdItemPriceRebateDetails.getContractPriceStartDate();
    }

    /**
    * Sets the contract price start date of this imtd item price rebate details.
    *
    * @param contractPriceStartDate the contract price start date of this imtd item price rebate details
    */
    @Override
    public void setContractPriceStartDate(java.util.Date contractPriceStartDate) {
        _imtdItemPriceRebateDetails.setContractPriceStartDate(contractPriceStartDate);
    }

    /**
    * Returns the rebate schedule type of this imtd item price rebate details.
    *
    * @return the rebate schedule type of this imtd item price rebate details
    */
    @Override
    public java.lang.String getRebateScheduleType() {
        return _imtdItemPriceRebateDetails.getRebateScheduleType();
    }

    /**
    * Sets the rebate schedule type of this imtd item price rebate details.
    *
    * @param rebateScheduleType the rebate schedule type of this imtd item price rebate details
    */
    @Override
    public void setRebateScheduleType(java.lang.String rebateScheduleType) {
        _imtdItemPriceRebateDetails.setRebateScheduleType(rebateScheduleType);
    }

    /**
    * Returns the price tolerance frequency of this imtd item price rebate details.
    *
    * @return the price tolerance frequency of this imtd item price rebate details
    */
    @Override
    public java.lang.String getPriceToleranceFrequency() {
        return _imtdItemPriceRebateDetails.getPriceToleranceFrequency();
    }

    /**
    * Sets the price tolerance frequency of this imtd item price rebate details.
    *
    * @param priceToleranceFrequency the price tolerance frequency of this imtd item price rebate details
    */
    @Override
    public void setPriceToleranceFrequency(
        java.lang.String priceToleranceFrequency) {
        _imtdItemPriceRebateDetails.setPriceToleranceFrequency(priceToleranceFrequency);
    }

    /**
    * Returns the imtd item price rebate sid of this imtd item price rebate details.
    *
    * @return the imtd item price rebate sid of this imtd item price rebate details
    */
    @Override
    public int getImtdItemPriceRebateSid() {
        return _imtdItemPriceRebateDetails.getImtdItemPriceRebateSid();
    }

    /**
    * Sets the imtd item price rebate sid of this imtd item price rebate details.
    *
    * @param imtdItemPriceRebateSid the imtd item price rebate sid of this imtd item price rebate details
    */
    @Override
    public void setImtdItemPriceRebateSid(int imtdItemPriceRebateSid) {
        _imtdItemPriceRebateDetails.setImtdItemPriceRebateSid(imtdItemPriceRebateSid);
    }

    /**
    * Returns the rebate plan system ID of this imtd item price rebate details.
    *
    * @return the rebate plan system ID of this imtd item price rebate details
    */
    @Override
    public java.lang.String getRebatePlanSystemId() {
        return _imtdItemPriceRebateDetails.getRebatePlanSystemId();
    }

    /**
    * Sets the rebate plan system ID of this imtd item price rebate details.
    *
    * @param rebatePlanSystemId the rebate plan system ID of this imtd item price rebate details
    */
    @Override
    public void setRebatePlanSystemId(java.lang.String rebatePlanSystemId) {
        _imtdItemPriceRebateDetails.setRebatePlanSystemId(rebatePlanSystemId);
    }

    /**
    * Returns the attached date of this imtd item price rebate details.
    *
    * @return the attached date of this imtd item price rebate details
    */
    @Override
    public java.util.Date getAttachedDate() {
        return _imtdItemPriceRebateDetails.getAttachedDate();
    }

    /**
    * Sets the attached date of this imtd item price rebate details.
    *
    * @param attachedDate the attached date of this imtd item price rebate details
    */
    @Override
    public void setAttachedDate(java.util.Date attachedDate) {
        _imtdItemPriceRebateDetails.setAttachedDate(attachedDate);
    }

    /**
    * Returns the price plan ID of this imtd item price rebate details.
    *
    * @return the price plan ID of this imtd item price rebate details
    */
    @Override
    public java.lang.String getPricePlanId() {
        return _imtdItemPriceRebateDetails.getPricePlanId();
    }

    /**
    * Sets the price plan ID of this imtd item price rebate details.
    *
    * @param pricePlanId the price plan ID of this imtd item price rebate details
    */
    @Override
    public void setPricePlanId(java.lang.String pricePlanId) {
        _imtdItemPriceRebateDetails.setPricePlanId(pricePlanId);
    }

    /**
    * Returns the item rebate end date of this imtd item price rebate details.
    *
    * @return the item rebate end date of this imtd item price rebate details
    */
    @Override
    public java.util.Date getItemRebateEndDate() {
        return _imtdItemPriceRebateDetails.getItemRebateEndDate();
    }

    /**
    * Sets the item rebate end date of this imtd item price rebate details.
    *
    * @param itemRebateEndDate the item rebate end date of this imtd item price rebate details
    */
    @Override
    public void setItemRebateEndDate(java.util.Date itemRebateEndDate) {
        _imtdItemPriceRebateDetails.setItemRebateEndDate(itemRebateEndDate);
    }

    /**
    * Returns the price type of this imtd item price rebate details.
    *
    * @return the price type of this imtd item price rebate details
    */
    @Override
    public int getPriceType() {
        return _imtdItemPriceRebateDetails.getPriceType();
    }

    /**
    * Sets the price type of this imtd item price rebate details.
    *
    * @param priceType the price type of this imtd item price rebate details
    */
    @Override
    public void setPriceType(int priceType) {
        _imtdItemPriceRebateDetails.setPriceType(priceType);
    }

    /**
    * Returns the price tolerance interval of this imtd item price rebate details.
    *
    * @return the price tolerance interval of this imtd item price rebate details
    */
    @Override
    public int getPriceToleranceInterval() {
        return _imtdItemPriceRebateDetails.getPriceToleranceInterval();
    }

    /**
    * Sets the price tolerance interval of this imtd item price rebate details.
    *
    * @param priceToleranceInterval the price tolerance interval of this imtd item price rebate details
    */
    @Override
    public void setPriceToleranceInterval(int priceToleranceInterval) {
        _imtdItemPriceRebateDetails.setPriceToleranceInterval(priceToleranceInterval);
    }

    /**
    * Returns the rs attached date of this imtd item price rebate details.
    *
    * @return the rs attached date of this imtd item price rebate details
    */
    @Override
    public java.util.Date getRsAttachedDate() {
        return _imtdItemPriceRebateDetails.getRsAttachedDate();
    }

    /**
    * Sets the rs attached date of this imtd item price rebate details.
    *
    * @param rsAttachedDate the rs attached date of this imtd item price rebate details
    */
    @Override
    public void setRsAttachedDate(java.util.Date rsAttachedDate) {
        _imtdItemPriceRebateDetails.setRsAttachedDate(rsAttachedDate);
    }

    /**
    * Returns the item rebate start date of this imtd item price rebate details.
    *
    * @return the item rebate start date of this imtd item price rebate details
    */
    @Override
    public java.util.Date getItemRebateStartDate() {
        return _imtdItemPriceRebateDetails.getItemRebateStartDate();
    }

    /**
    * Sets the item rebate start date of this imtd item price rebate details.
    *
    * @param itemRebateStartDate the item rebate start date of this imtd item price rebate details
    */
    @Override
    public void setItemRebateStartDate(java.util.Date itemRebateStartDate) {
        _imtdItemPriceRebateDetails.setItemRebateStartDate(itemRebateStartDate);
    }

    /**
    * Returns the operation of this imtd item price rebate details.
    *
    * @return the operation of this imtd item price rebate details
    */
    @Override
    public java.lang.String getOperation() {
        return _imtdItemPriceRebateDetails.getOperation();
    }

    /**
    * Sets the operation of this imtd item price rebate details.
    *
    * @param operation the operation of this imtd item price rebate details
    */
    @Override
    public void setOperation(java.lang.String operation) {
        _imtdItemPriceRebateDetails.setOperation(operation);
    }

    /**
    * Returns the cfp model sid of this imtd item price rebate details.
    *
    * @return the cfp model sid of this imtd item price rebate details
    */
    @Override
    public int getCfpModelSid() {
        return _imtdItemPriceRebateDetails.getCfpModelSid();
    }

    /**
    * Sets the cfp model sid of this imtd item price rebate details.
    *
    * @param cfpModelSid the cfp model sid of this imtd item price rebate details
    */
    @Override
    public void setCfpModelSid(int cfpModelSid) {
        _imtdItemPriceRebateDetails.setCfpModelSid(cfpModelSid);
    }

    /**
    * Returns the rs details sid of this imtd item price rebate details.
    *
    * @return the rs details sid of this imtd item price rebate details
    */
    @Override
    public int getRsDetailsSid() {
        return _imtdItemPriceRebateDetails.getRsDetailsSid();
    }

    /**
    * Sets the rs details sid of this imtd item price rebate details.
    *
    * @param rsDetailsSid the rs details sid of this imtd item price rebate details
    */
    @Override
    public void setRsDetailsSid(int rsDetailsSid) {
        _imtdItemPriceRebateDetails.setRsDetailsSid(rsDetailsSid);
    }

    /**
    * Returns the attached status of this imtd item price rebate details.
    *
    * @return the attached status of this imtd item price rebate details
    */
    @Override
    public int getAttachedStatus() {
        return _imtdItemPriceRebateDetails.getAttachedStatus();
    }

    /**
    * Sets the attached status of this imtd item price rebate details.
    *
    * @param attachedStatus the attached status of this imtd item price rebate details
    */
    @Override
    public void setAttachedStatus(int attachedStatus) {
        _imtdItemPriceRebateDetails.setAttachedStatus(attachedStatus);
    }

    /**
    * Returns the primary uom of this imtd item price rebate details.
    *
    * @return the primary uom of this imtd item price rebate details
    */
    @Override
    public int getPrimaryUom() {
        return _imtdItemPriceRebateDetails.getPrimaryUom();
    }

    /**
    * Sets the primary uom of this imtd item price rebate details.
    *
    * @param primaryUom the primary uom of this imtd item price rebate details
    */
    @Override
    public void setPrimaryUom(int primaryUom) {
        _imtdItemPriceRebateDetails.setPrimaryUom(primaryUom);
    }

    /**
    * Returns the package size of this imtd item price rebate details.
    *
    * @return the package size of this imtd item price rebate details
    */
    @Override
    public java.lang.String getPackageSize() {
        return _imtdItemPriceRebateDetails.getPackageSize();
    }

    /**
    * Sets the package size of this imtd item price rebate details.
    *
    * @param packageSize the package size of this imtd item price rebate details
    */
    @Override
    public void setPackageSize(java.lang.String packageSize) {
        _imtdItemPriceRebateDetails.setPackageSize(packageSize);
    }

    /**
    * Returns the deduction calendar master sid of this imtd item price rebate details.
    *
    * @return the deduction calendar master sid of this imtd item price rebate details
    */
    @Override
    public java.lang.String getDeductionCalendarMasterSid() {
        return _imtdItemPriceRebateDetails.getDeductionCalendarMasterSid();
    }

    /**
    * Sets the deduction calendar master sid of this imtd item price rebate details.
    *
    * @param deductionCalendarMasterSid the deduction calendar master sid of this imtd item price rebate details
    */
    @Override
    public void setDeductionCalendarMasterSid(
        java.lang.String deductionCalendarMasterSid) {
        _imtdItemPriceRebateDetails.setDeductionCalendarMasterSid(deductionCalendarMasterSid);
    }

    /**
    * Returns the rs contract details deduction calendar no of this imtd item price rebate details.
    *
    * @return the rs contract details deduction calendar no of this imtd item price rebate details
    */
    @Override
    public java.lang.String getRsContractDetailsDeductionCalendarNo() {
        return _imtdItemPriceRebateDetails.getRsContractDetailsDeductionCalendarNo();
    }

    /**
    * Sets the rs contract details deduction calendar no of this imtd item price rebate details.
    *
    * @param rsContractDetailsDeductionCalendarNo the rs contract details deduction calendar no of this imtd item price rebate details
    */
    @Override
    public void setRsContractDetailsDeductionCalendarNo(
        java.lang.String rsContractDetailsDeductionCalendarNo) {
        _imtdItemPriceRebateDetails.setRsContractDetailsDeductionCalendarNo(rsContractDetailsDeductionCalendarNo);
    }

    /**
    * Returns the rs contract details deduction calendar name of this imtd item price rebate details.
    *
    * @return the rs contract details deduction calendar name of this imtd item price rebate details
    */
    @Override
    public java.lang.String getRsContractDetailsDeductionCalendarName() {
        return _imtdItemPriceRebateDetails.getRsContractDetailsDeductionCalendarName();
    }

    /**
    * Sets the rs contract details deduction calendar name of this imtd item price rebate details.
    *
    * @param rsContractDetailsDeductionCalendarName the rs contract details deduction calendar name of this imtd item price rebate details
    */
    @Override
    public void setRsContractDetailsDeductionCalendarName(
        java.lang.String rsContractDetailsDeductionCalendarName) {
        _imtdItemPriceRebateDetails.setRsContractDetailsDeductionCalendarName(rsContractDetailsDeductionCalendarName);
    }

    /**
    * Returns the net sales formula master sid of this imtd item price rebate details.
    *
    * @return the net sales formula master sid of this imtd item price rebate details
    */
    @Override
    public java.lang.String getNetSalesFormulaMasterSid() {
        return _imtdItemPriceRebateDetails.getNetSalesFormulaMasterSid();
    }

    /**
    * Sets the net sales formula master sid of this imtd item price rebate details.
    *
    * @param netSalesFormulaMasterSid the net sales formula master sid of this imtd item price rebate details
    */
    @Override
    public void setNetSalesFormulaMasterSid(
        java.lang.String netSalesFormulaMasterSid) {
        _imtdItemPriceRebateDetails.setNetSalesFormulaMasterSid(netSalesFormulaMasterSid);
    }

    /**
    * Returns the rs contract details net sales formula no of this imtd item price rebate details.
    *
    * @return the rs contract details net sales formula no of this imtd item price rebate details
    */
    @Override
    public java.lang.String getRsContractDetailsNetSalesFormulaNo() {
        return _imtdItemPriceRebateDetails.getRsContractDetailsNetSalesFormulaNo();
    }

    /**
    * Sets the rs contract details net sales formula no of this imtd item price rebate details.
    *
    * @param rsContractDetailsNetSalesFormulaNo the rs contract details net sales formula no of this imtd item price rebate details
    */
    @Override
    public void setRsContractDetailsNetSalesFormulaNo(
        java.lang.String rsContractDetailsNetSalesFormulaNo) {
        _imtdItemPriceRebateDetails.setRsContractDetailsNetSalesFormulaNo(rsContractDetailsNetSalesFormulaNo);
    }

    /**
    * Returns the rs contract details net sales formula name of this imtd item price rebate details.
    *
    * @return the rs contract details net sales formula name of this imtd item price rebate details
    */
    @Override
    public java.lang.String getRsContractDetailsNetSalesFormulaName() {
        return _imtdItemPriceRebateDetails.getRsContractDetailsNetSalesFormulaName();
    }

    /**
    * Sets the rs contract details net sales formula name of this imtd item price rebate details.
    *
    * @param rsContractDetailsNetSalesFormulaName the rs contract details net sales formula name of this imtd item price rebate details
    */
    @Override
    public void setRsContractDetailsNetSalesFormulaName(
        java.lang.String rsContractDetailsNetSalesFormulaName) {
        _imtdItemPriceRebateDetails.setRsContractDetailsNetSalesFormulaName(rsContractDetailsNetSalesFormulaName);
    }

    /**
    * Returns the formula type of this imtd item price rebate details.
    *
    * @return the formula type of this imtd item price rebate details
    */
    @Override
    public int getFormulaType() {
        return _imtdItemPriceRebateDetails.getFormulaType();
    }

    /**
    * Sets the formula type of this imtd item price rebate details.
    *
    * @param formulaType the formula type of this imtd item price rebate details
    */
    @Override
    public void setFormulaType(int formulaType) {
        _imtdItemPriceRebateDetails.setFormulaType(formulaType);
    }

    /**
    * Returns the net sales rule of this imtd item price rebate details.
    *
    * @return the net sales rule of this imtd item price rebate details
    */
    @Override
    public int getNetSalesRule() {
        return _imtdItemPriceRebateDetails.getNetSalesRule();
    }

    /**
    * Sets the net sales rule of this imtd item price rebate details.
    *
    * @param netSalesRule the net sales rule of this imtd item price rebate details
    */
    @Override
    public void setNetSalesRule(int netSalesRule) {
        _imtdItemPriceRebateDetails.setNetSalesRule(netSalesRule);
    }

    /**
    * Returns the evaluation rule of this imtd item price rebate details.
    *
    * @return the evaluation rule of this imtd item price rebate details
    */
    @Override
    public int getEvaluationRule() {
        return _imtdItemPriceRebateDetails.getEvaluationRule();
    }

    /**
    * Sets the evaluation rule of this imtd item price rebate details.
    *
    * @param evaluationRule the evaluation rule of this imtd item price rebate details
    */
    @Override
    public void setEvaluationRule(int evaluationRule) {
        _imtdItemPriceRebateDetails.setEvaluationRule(evaluationRule);
    }

    /**
    * Returns the evaluation rule bundle of this imtd item price rebate details.
    *
    * @return the evaluation rule bundle of this imtd item price rebate details
    */
    @Override
    public java.lang.String getEvaluationRuleBundle() {
        return _imtdItemPriceRebateDetails.getEvaluationRuleBundle();
    }

    /**
    * Sets the evaluation rule bundle of this imtd item price rebate details.
    *
    * @param evaluationRuleBundle the evaluation rule bundle of this imtd item price rebate details
    */
    @Override
    public void setEvaluationRuleBundle(java.lang.String evaluationRuleBundle) {
        _imtdItemPriceRebateDetails.setEvaluationRuleBundle(evaluationRuleBundle);
    }

    /**
    * Returns the calculation rule of this imtd item price rebate details.
    *
    * @return the calculation rule of this imtd item price rebate details
    */
    @Override
    public int getCalculationRule() {
        return _imtdItemPriceRebateDetails.getCalculationRule();
    }

    /**
    * Sets the calculation rule of this imtd item price rebate details.
    *
    * @param calculationRule the calculation rule of this imtd item price rebate details
    */
    @Override
    public void setCalculationRule(int calculationRule) {
        _imtdItemPriceRebateDetails.setCalculationRule(calculationRule);
    }

    /**
    * Returns the calculation rule bundle of this imtd item price rebate details.
    *
    * @return the calculation rule bundle of this imtd item price rebate details
    */
    @Override
    public java.lang.String getCalculationRuleBundle() {
        return _imtdItemPriceRebateDetails.getCalculationRuleBundle();
    }

    /**
    * Sets the calculation rule bundle of this imtd item price rebate details.
    *
    * @param calculationRuleBundle the calculation rule bundle of this imtd item price rebate details
    */
    @Override
    public void setCalculationRuleBundle(java.lang.String calculationRuleBundle) {
        _imtdItemPriceRebateDetails.setCalculationRuleBundle(calculationRuleBundle);
    }

    /**
    * Returns the max incremental change of this imtd item price rebate details.
    *
    * @return the max incremental change of this imtd item price rebate details
    */
    @Override
    public double getMaxIncrementalChange() {
        return _imtdItemPriceRebateDetails.getMaxIncrementalChange();
    }

    /**
    * Sets the max incremental change of this imtd item price rebate details.
    *
    * @param maxIncrementalChange the max incremental change of this imtd item price rebate details
    */
    @Override
    public void setMaxIncrementalChange(double maxIncrementalChange) {
        _imtdItemPriceRebateDetails.setMaxIncrementalChange(maxIncrementalChange);
    }

    /**
    * Returns the reset eligible of this imtd item price rebate details.
    *
    * @return the reset eligible of this imtd item price rebate details
    */
    @Override
    public int getResetEligible() {
        return _imtdItemPriceRebateDetails.getResetEligible();
    }

    /**
    * Sets the reset eligible of this imtd item price rebate details.
    *
    * @param resetEligible the reset eligible of this imtd item price rebate details
    */
    @Override
    public void setResetEligible(int resetEligible) {
        _imtdItemPriceRebateDetails.setResetEligible(resetEligible);
    }

    /**
    * Returns the reset type of this imtd item price rebate details.
    *
    * @return the reset type of this imtd item price rebate details
    */
    @Override
    public int getResetType() {
        return _imtdItemPriceRebateDetails.getResetType();
    }

    /**
    * Sets the reset type of this imtd item price rebate details.
    *
    * @param resetType the reset type of this imtd item price rebate details
    */
    @Override
    public void setResetType(int resetType) {
        _imtdItemPriceRebateDetails.setResetType(resetType);
    }

    /**
    * Returns the reset date of this imtd item price rebate details.
    *
    * @return the reset date of this imtd item price rebate details
    */
    @Override
    public java.util.Date getResetDate() {
        return _imtdItemPriceRebateDetails.getResetDate();
    }

    /**
    * Sets the reset date of this imtd item price rebate details.
    *
    * @param resetDate the reset date of this imtd item price rebate details
    */
    @Override
    public void setResetDate(java.util.Date resetDate) {
        _imtdItemPriceRebateDetails.setResetDate(resetDate);
    }

    /**
    * Returns the reset interval of this imtd item price rebate details.
    *
    * @return the reset interval of this imtd item price rebate details
    */
    @Override
    public int getResetInterval() {
        return _imtdItemPriceRebateDetails.getResetInterval();
    }

    /**
    * Sets the reset interval of this imtd item price rebate details.
    *
    * @param resetInterval the reset interval of this imtd item price rebate details
    */
    @Override
    public void setResetInterval(int resetInterval) {
        _imtdItemPriceRebateDetails.setResetInterval(resetInterval);
    }

    /**
    * Returns the reset frequency of this imtd item price rebate details.
    *
    * @return the reset frequency of this imtd item price rebate details
    */
    @Override
    public int getResetFrequency() {
        return _imtdItemPriceRebateDetails.getResetFrequency();
    }

    /**
    * Sets the reset frequency of this imtd item price rebate details.
    *
    * @param resetFrequency the reset frequency of this imtd item price rebate details
    */
    @Override
    public void setResetFrequency(int resetFrequency) {
        _imtdItemPriceRebateDetails.setResetFrequency(resetFrequency);
    }

    /**
    * Returns the net price type of this imtd item price rebate details.
    *
    * @return the net price type of this imtd item price rebate details
    */
    @Override
    public int getNetPriceType() {
        return _imtdItemPriceRebateDetails.getNetPriceType();
    }

    /**
    * Sets the net price type of this imtd item price rebate details.
    *
    * @param netPriceType the net price type of this imtd item price rebate details
    */
    @Override
    public void setNetPriceType(int netPriceType) {
        _imtdItemPriceRebateDetails.setNetPriceType(netPriceType);
    }

    /**
    * Returns the net price type formula of this imtd item price rebate details.
    *
    * @return the net price type formula of this imtd item price rebate details
    */
    @Override
    public java.lang.String getNetPriceTypeFormula() {
        return _imtdItemPriceRebateDetails.getNetPriceTypeFormula();
    }

    /**
    * Sets the net price type formula of this imtd item price rebate details.
    *
    * @param netPriceTypeFormula the net price type formula of this imtd item price rebate details
    */
    @Override
    public void setNetPriceTypeFormula(java.lang.String netPriceTypeFormula) {
        _imtdItemPriceRebateDetails.setNetPriceTypeFormula(netPriceTypeFormula);
    }

    /**
    * Returns the price protection price type of this imtd item price rebate details.
    *
    * @return the price protection price type of this imtd item price rebate details
    */
    @Override
    public int getPriceProtectionPriceType() {
        return _imtdItemPriceRebateDetails.getPriceProtectionPriceType();
    }

    /**
    * Sets the price protection price type of this imtd item price rebate details.
    *
    * @param priceProtectionPriceType the price protection price type of this imtd item price rebate details
    */
    @Override
    public void setPriceProtectionPriceType(int priceProtectionPriceType) {
        _imtdItemPriceRebateDetails.setPriceProtectionPriceType(priceProtectionPriceType);
    }

    /**
    * Returns the nep of this imtd item price rebate details.
    *
    * @return the nep of this imtd item price rebate details
    */
    @Override
    public double getNep() {
        return _imtdItemPriceRebateDetails.getNep();
    }

    /**
    * Sets the nep of this imtd item price rebate details.
    *
    * @param nep the nep of this imtd item price rebate details
    */
    @Override
    public void setNep(double nep) {
        _imtdItemPriceRebateDetails.setNep(nep);
    }

    /**
    * Returns the nep formula of this imtd item price rebate details.
    *
    * @return the nep formula of this imtd item price rebate details
    */
    @Override
    public int getNepFormula() {
        return _imtdItemPriceRebateDetails.getNepFormula();
    }

    /**
    * Sets the nep formula of this imtd item price rebate details.
    *
    * @param nepFormula the nep formula of this imtd item price rebate details
    */
    @Override
    public void setNepFormula(int nepFormula) {
        _imtdItemPriceRebateDetails.setNepFormula(nepFormula);
    }

    /**
    * Returns the brand master sid of this imtd item price rebate details.
    *
    * @return the brand master sid of this imtd item price rebate details
    */
    @Override
    public java.lang.String getBrandMasterSid() {
        return _imtdItemPriceRebateDetails.getBrandMasterSid();
    }

    /**
    * Sets the brand master sid of this imtd item price rebate details.
    *
    * @param brandMasterSid the brand master sid of this imtd item price rebate details
    */
    @Override
    public void setBrandMasterSid(java.lang.String brandMasterSid) {
        _imtdItemPriceRebateDetails.setBrandMasterSid(brandMasterSid);
    }

    /**
    * Returns the price protection status of this imtd item price rebate details.
    *
    * @return the price protection status of this imtd item price rebate details
    */
    @Override
    public int getPriceProtectionStatus() {
        return _imtdItemPriceRebateDetails.getPriceProtectionStatus();
    }

    /**
    * Sets the price protection status of this imtd item price rebate details.
    *
    * @param priceProtectionStatus the price protection status of this imtd item price rebate details
    */
    @Override
    public void setPriceProtectionStatus(int priceProtectionStatus) {
        _imtdItemPriceRebateDetails.setPriceProtectionStatus(priceProtectionStatus);
    }

    /**
    * Returns the base price type of this imtd item price rebate details.
    *
    * @return the base price type of this imtd item price rebate details
    */
    @Override
    public int getBasePriceType() {
        return _imtdItemPriceRebateDetails.getBasePriceType();
    }

    /**
    * Sets the base price type of this imtd item price rebate details.
    *
    * @param basePriceType the base price type of this imtd item price rebate details
    */
    @Override
    public void setBasePriceType(int basePriceType) {
        _imtdItemPriceRebateDetails.setBasePriceType(basePriceType);
    }

    /**
    * Returns the base price entry of this imtd item price rebate details.
    *
    * @return the base price entry of this imtd item price rebate details
    */
    @Override
    public double getBasePriceEntry() {
        return _imtdItemPriceRebateDetails.getBasePriceEntry();
    }

    /**
    * Sets the base price entry of this imtd item price rebate details.
    *
    * @param basePriceEntry the base price entry of this imtd item price rebate details
    */
    @Override
    public void setBasePriceEntry(double basePriceEntry) {
        _imtdItemPriceRebateDetails.setBasePriceEntry(basePriceEntry);
    }

    /**
    * Returns the base price date of this imtd item price rebate details.
    *
    * @return the base price date of this imtd item price rebate details
    */
    @Override
    public java.util.Date getBasePriceDate() {
        return _imtdItemPriceRebateDetails.getBasePriceDate();
    }

    /**
    * Sets the base price date of this imtd item price rebate details.
    *
    * @param basePriceDate the base price date of this imtd item price rebate details
    */
    @Override
    public void setBasePriceDate(java.util.Date basePriceDate) {
        _imtdItemPriceRebateDetails.setBasePriceDate(basePriceDate);
    }

    /**
    * Returns the base price ddlb of this imtd item price rebate details.
    *
    * @return the base price ddlb of this imtd item price rebate details
    */
    @Override
    public int getBasePriceDdlb() {
        return _imtdItemPriceRebateDetails.getBasePriceDdlb();
    }

    /**
    * Sets the base price ddlb of this imtd item price rebate details.
    *
    * @param basePriceDdlb the base price ddlb of this imtd item price rebate details
    */
    @Override
    public void setBasePriceDdlb(int basePriceDdlb) {
        _imtdItemPriceRebateDetails.setBasePriceDdlb(basePriceDdlb);
    }

    /**
    * Returns the net base price of this imtd item price rebate details.
    *
    * @return the net base price of this imtd item price rebate details
    */
    @Override
    public int getNetBasePrice() {
        return _imtdItemPriceRebateDetails.getNetBasePrice();
    }

    /**
    * Sets the net base price of this imtd item price rebate details.
    *
    * @param netBasePrice the net base price of this imtd item price rebate details
    */
    @Override
    public void setNetBasePrice(int netBasePrice) {
        _imtdItemPriceRebateDetails.setNetBasePrice(netBasePrice);
    }

    /**
    * Returns the subsequent period price type of this imtd item price rebate details.
    *
    * @return the subsequent period price type of this imtd item price rebate details
    */
    @Override
    public int getSubsequentPeriodPriceType() {
        return _imtdItemPriceRebateDetails.getSubsequentPeriodPriceType();
    }

    /**
    * Sets the subsequent period price type of this imtd item price rebate details.
    *
    * @param subsequentPeriodPriceType the subsequent period price type of this imtd item price rebate details
    */
    @Override
    public void setSubsequentPeriodPriceType(int subsequentPeriodPriceType) {
        _imtdItemPriceRebateDetails.setSubsequentPeriodPriceType(subsequentPeriodPriceType);
    }

    /**
    * Returns the reset price type of this imtd item price rebate details.
    *
    * @return the reset price type of this imtd item price rebate details
    */
    @Override
    public int getResetPriceType() {
        return _imtdItemPriceRebateDetails.getResetPriceType();
    }

    /**
    * Sets the reset price type of this imtd item price rebate details.
    *
    * @param resetPriceType the reset price type of this imtd item price rebate details
    */
    @Override
    public void setResetPriceType(int resetPriceType) {
        _imtdItemPriceRebateDetails.setResetPriceType(resetPriceType);
    }

    /**
    * Returns the net reset price type of this imtd item price rebate details.
    *
    * @return the net reset price type of this imtd item price rebate details
    */
    @Override
    public int getNetResetPriceType() {
        return _imtdItemPriceRebateDetails.getNetResetPriceType();
    }

    /**
    * Sets the net reset price type of this imtd item price rebate details.
    *
    * @param netResetPriceType the net reset price type of this imtd item price rebate details
    */
    @Override
    public void setNetResetPriceType(int netResetPriceType) {
        _imtdItemPriceRebateDetails.setNetResetPriceType(netResetPriceType);
    }

    /**
    * Returns the net reset price formula ID of this imtd item price rebate details.
    *
    * @return the net reset price formula ID of this imtd item price rebate details
    */
    @Override
    public int getNetResetPriceFormulaId() {
        return _imtdItemPriceRebateDetails.getNetResetPriceFormulaId();
    }

    /**
    * Sets the net reset price formula ID of this imtd item price rebate details.
    *
    * @param netResetPriceFormulaId the net reset price formula ID of this imtd item price rebate details
    */
    @Override
    public void setNetResetPriceFormulaId(int netResetPriceFormulaId) {
        _imtdItemPriceRebateDetails.setNetResetPriceFormulaId(netResetPriceFormulaId);
    }

    /**
    * Returns the net base price formula ID of this imtd item price rebate details.
    *
    * @return the net base price formula ID of this imtd item price rebate details
    */
    @Override
    public int getNetBasePriceFormulaId() {
        return _imtdItemPriceRebateDetails.getNetBasePriceFormulaId();
    }

    /**
    * Sets the net base price formula ID of this imtd item price rebate details.
    *
    * @param netBasePriceFormulaId the net base price formula ID of this imtd item price rebate details
    */
    @Override
    public void setNetBasePriceFormulaId(int netBasePriceFormulaId) {
        _imtdItemPriceRebateDetails.setNetBasePriceFormulaId(netBasePriceFormulaId);
    }

    /**
    * Returns the net subsequent price formula ID of this imtd item price rebate details.
    *
    * @return the net subsequent price formula ID of this imtd item price rebate details
    */
    @Override
    public int getNetSubsequentPriceFormulaId() {
        return _imtdItemPriceRebateDetails.getNetSubsequentPriceFormulaId();
    }

    /**
    * Sets the net subsequent price formula ID of this imtd item price rebate details.
    *
    * @param netSubsequentPriceFormulaId the net subsequent price formula ID of this imtd item price rebate details
    */
    @Override
    public void setNetSubsequentPriceFormulaId(int netSubsequentPriceFormulaId) {
        _imtdItemPriceRebateDetails.setNetSubsequentPriceFormulaId(netSubsequentPriceFormulaId);
    }

    /**
    * Returns the net subsequent period price of this imtd item price rebate details.
    *
    * @return the net subsequent period price of this imtd item price rebate details
    */
    @Override
    public int getNetSubsequentPeriodPrice() {
        return _imtdItemPriceRebateDetails.getNetSubsequentPeriodPrice();
    }

    /**
    * Sets the net subsequent period price of this imtd item price rebate details.
    *
    * @param netSubsequentPeriodPrice the net subsequent period price of this imtd item price rebate details
    */
    @Override
    public void setNetSubsequentPeriodPrice(int netSubsequentPeriodPrice) {
        _imtdItemPriceRebateDetails.setNetSubsequentPeriodPrice(netSubsequentPeriodPrice);
    }

    /**
    * Returns the rs contract details rebate plan name of this imtd item price rebate details.
    *
    * @return the rs contract details rebate plan name of this imtd item price rebate details
    */
    @Override
    public java.lang.String getRsContractDetailsRebatePlanName() {
        return _imtdItemPriceRebateDetails.getRsContractDetailsRebatePlanName();
    }

    /**
    * Sets the rs contract details rebate plan name of this imtd item price rebate details.
    *
    * @param rsContractDetailsRebatePlanName the rs contract details rebate plan name of this imtd item price rebate details
    */
    @Override
    public void setRsContractDetailsRebatePlanName(
        java.lang.String rsContractDetailsRebatePlanName) {
        _imtdItemPriceRebateDetails.setRsContractDetailsRebatePlanName(rsContractDetailsRebatePlanName);
    }

    /**
    * Returns the rs contract details formula no of this imtd item price rebate details.
    *
    * @return the rs contract details formula no of this imtd item price rebate details
    */
    @Override
    public java.lang.String getRsContractDetailsFormulaNo() {
        return _imtdItemPriceRebateDetails.getRsContractDetailsFormulaNo();
    }

    /**
    * Sets the rs contract details formula no of this imtd item price rebate details.
    *
    * @param rsContractDetailsFormulaNo the rs contract details formula no of this imtd item price rebate details
    */
    @Override
    public void setRsContractDetailsFormulaNo(
        java.lang.String rsContractDetailsFormulaNo) {
        _imtdItemPriceRebateDetails.setRsContractDetailsFormulaNo(rsContractDetailsFormulaNo);
    }

    /**
    * Returns the source of this imtd item price rebate details.
    *
    * @return the source of this imtd item price rebate details
    */
    @Override
    public java.lang.String getSource() {
        return _imtdItemPriceRebateDetails.getSource();
    }

    /**
    * Sets the source of this imtd item price rebate details.
    *
    * @param source the source of this imtd item price rebate details
    */
    @Override
    public void setSource(java.lang.String source) {
        _imtdItemPriceRebateDetails.setSource(source);
    }

    @Override
    public boolean isNew() {
        return _imtdItemPriceRebateDetails.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _imtdItemPriceRebateDetails.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _imtdItemPriceRebateDetails.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _imtdItemPriceRebateDetails.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _imtdItemPriceRebateDetails.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _imtdItemPriceRebateDetails.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _imtdItemPriceRebateDetails.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _imtdItemPriceRebateDetails.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _imtdItemPriceRebateDetails.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _imtdItemPriceRebateDetails.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _imtdItemPriceRebateDetails.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new ImtdItemPriceRebateDetailsWrapper((ImtdItemPriceRebateDetails) _imtdItemPriceRebateDetails.clone());
    }

    @Override
    public int compareTo(ImtdItemPriceRebateDetails imtdItemPriceRebateDetails) {
        return _imtdItemPriceRebateDetails.compareTo(imtdItemPriceRebateDetails);
    }

    @Override
    public int hashCode() {
        return _imtdItemPriceRebateDetails.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<ImtdItemPriceRebateDetails> toCacheModel() {
        return _imtdItemPriceRebateDetails.toCacheModel();
    }

    @Override
    public ImtdItemPriceRebateDetails toEscapedModel() {
        return new ImtdItemPriceRebateDetailsWrapper(_imtdItemPriceRebateDetails.toEscapedModel());
    }

    @Override
    public ImtdItemPriceRebateDetails toUnescapedModel() {
        return new ImtdItemPriceRebateDetailsWrapper(_imtdItemPriceRebateDetails.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _imtdItemPriceRebateDetails.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _imtdItemPriceRebateDetails.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _imtdItemPriceRebateDetails.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof ImtdItemPriceRebateDetailsWrapper)) {
            return false;
        }

        ImtdItemPriceRebateDetailsWrapper imtdItemPriceRebateDetailsWrapper = (ImtdItemPriceRebateDetailsWrapper) obj;

        if (Validator.equals(_imtdItemPriceRebateDetails,
                    imtdItemPriceRebateDetailsWrapper._imtdItemPriceRebateDetails)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public ImtdItemPriceRebateDetails getWrappedImtdItemPriceRebateDetails() {
        return _imtdItemPriceRebateDetails;
    }

    @Override
    public ImtdItemPriceRebateDetails getWrappedModel() {
        return _imtdItemPriceRebateDetails;
    }

    @Override
    public void resetOriginalValues() {
        _imtdItemPriceRebateDetails.resetOriginalValues();
    }
}
