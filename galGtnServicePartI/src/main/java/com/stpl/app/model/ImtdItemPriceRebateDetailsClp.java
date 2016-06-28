package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.ImtdItemPriceRebateDetailsLocalServiceUtil;

import com.stpl.portal.kernel.bean.AutoEscapeBeanHandler;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.ProxyUtil;
import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.model.BaseModel;
import com.stpl.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class ImtdItemPriceRebateDetailsClp extends BaseModelImpl<ImtdItemPriceRebateDetails>
    implements ImtdItemPriceRebateDetails {
    private String _formulaMethodId;
    private Date _endDate;
    private Date _priceProtectionStartDate;
    private Date _itemPriceRevisionDate;
    private Date _modifiedDate;
    private boolean _rsCheckRecord;
    private Date _rebateRevisionDate;
    private int _contractMasterSid;
    private Date _imtdCreatedDate;
    private int _modifiedBy;
    private int _udc6;
    private int _udc5;
    private int _udc4;
    private boolean _checkRecord;
    private int _udc1;
    private int _udc2;
    private int _udc3;
    private Date _contractPriceEndDate;
    private double _totalVolumeCommitment;
    private Date _priceProtectionEndDate;
    private String _recordLockStatus;
    private Date _startDate;
    private int _rebateProgramType;
    private String _sessionId;
    private String _itemName;
    private double _priceRevision;
    private int _rsModelSid;
    private double _price;
    private int _rsAttachedStatus;
    private int _itemMasterSid;
    private double _totalDollarCommitment;
    private int _itemType;
    private double _totalMarketShareCommitmnet;
    private String _itemId;
    private double _basePrice;
    private String _bundleNo;
    private String _formulaName;
    private int _psStatus;
    private double _priceTolerance;
    private Date _createdDate;
    private int _createdBy;
    private int _usersSid;
    private int _psDetailsSid;
    private double _suggestedPrice;
    private int _psModelSid;
    private String _formulaId;
    private String _commitmentPeriod;
    private String _itemNo;
    private double _contractPrice;
    private int _ifpDetailsSid;
    private int _ifpModelSid;
    private String _priceToleranceType;
    private double _rebateAmount;
    private Date _contractPriceStartDate;
    private String _rebateScheduleType;
    private String _priceToleranceFrequency;
    private int _imtdItemPriceRebateSid;
    private String _rebatePlanSystemId;
    private Date _attachedDate;
    private String _pricePlanId;
    private Date _itemRebateEndDate;
    private int _priceType;
    private int _priceToleranceInterval;
    private Date _rsAttachedDate;
    private Date _itemRebateStartDate;
    private String _operation;
    private int _cfpModelSid;
    private int _rsDetailsSid;
    private int _attachedStatus;
    private int _primaryUom;
    private String _packageSize;
    private String _deductionCalendarMasterSid;
    private String _rsContractDetailsDeductionCalendarNo;
    private String _rsContractDetailsDeductionCalendarName;
    private String _netSalesFormulaMasterSid;
    private String _rsContractDetailsNetSalesFormulaNo;
    private String _rsContractDetailsNetSalesFormulaName;
    private int _formulaType;
    private int _netSalesRule;
    private int _evaluationRule;
    private String _evaluationRuleBundle;
    private int _calculationRule;
    private String _calculationRuleBundle;
    private double _maxIncrementalChange;
    private int _resetEligible;
    private int _resetType;
    private Date _resetDate;
    private int _resetInterval;
    private int _resetFrequency;
    private int _netPriceType;
    private String _netPriceTypeFormula;
    private int _priceProtectionPriceType;
    private double _nep;
    private int _nepFormula;
    private String _brandMasterSid;
    private int _priceProtectionStatus;
    private int _basePriceType;
    private double _basePriceEntry;
    private Date _basePriceDate;
    private int _basePriceDdlb;
    private int _netBasePrice;
    private int _subsequentPeriodPriceType;
    private int _resetPriceType;
    private int _netResetPriceType;
    private int _netResetPriceFormulaId;
    private int _netBasePriceFormulaId;
    private int _netSubsequentPriceFormulaId;
    private int _netSubsequentPeriodPrice;
    private String _rsContractDetailsRebatePlanName;
    private String _rsContractDetailsFormulaNo;
    private String _source;
    private BaseModel<?> _imtdItemPriceRebateDetailsRemoteModel;

    public ImtdItemPriceRebateDetailsClp() {
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
    public int getPrimaryKey() {
        return _imtdItemPriceRebateSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setImtdItemPriceRebateSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _imtdItemPriceRebateSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
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

    @Override
    public String getFormulaMethodId() {
        return _formulaMethodId;
    }

    @Override
    public void setFormulaMethodId(String formulaMethodId) {
        _formulaMethodId = formulaMethodId;

        if (_imtdItemPriceRebateDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdItemPriceRebateDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setFormulaMethodId",
                        String.class);

                method.invoke(_imtdItemPriceRebateDetailsRemoteModel,
                    formulaMethodId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getEndDate() {
        return _endDate;
    }

    @Override
    public void setEndDate(Date endDate) {
        _endDate = endDate;

        if (_imtdItemPriceRebateDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdItemPriceRebateDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setEndDate", Date.class);

                method.invoke(_imtdItemPriceRebateDetailsRemoteModel, endDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getPriceProtectionStartDate() {
        return _priceProtectionStartDate;
    }

    @Override
    public void setPriceProtectionStartDate(Date priceProtectionStartDate) {
        _priceProtectionStartDate = priceProtectionStartDate;

        if (_imtdItemPriceRebateDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdItemPriceRebateDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setPriceProtectionStartDate",
                        Date.class);

                method.invoke(_imtdItemPriceRebateDetailsRemoteModel,
                    priceProtectionStartDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getItemPriceRevisionDate() {
        return _itemPriceRevisionDate;
    }

    @Override
    public void setItemPriceRevisionDate(Date itemPriceRevisionDate) {
        _itemPriceRevisionDate = itemPriceRevisionDate;

        if (_imtdItemPriceRebateDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdItemPriceRebateDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setItemPriceRevisionDate",
                        Date.class);

                method.invoke(_imtdItemPriceRebateDetailsRemoteModel,
                    itemPriceRevisionDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getModifiedDate() {
        return _modifiedDate;
    }

    @Override
    public void setModifiedDate(Date modifiedDate) {
        _modifiedDate = modifiedDate;

        if (_imtdItemPriceRebateDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdItemPriceRebateDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_imtdItemPriceRebateDetailsRemoteModel,
                    modifiedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public boolean getRsCheckRecord() {
        return _rsCheckRecord;
    }

    @Override
    public boolean isRsCheckRecord() {
        return _rsCheckRecord;
    }

    @Override
    public void setRsCheckRecord(boolean rsCheckRecord) {
        _rsCheckRecord = rsCheckRecord;

        if (_imtdItemPriceRebateDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdItemPriceRebateDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setRsCheckRecord",
                        boolean.class);

                method.invoke(_imtdItemPriceRebateDetailsRemoteModel,
                    rsCheckRecord);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getRebateRevisionDate() {
        return _rebateRevisionDate;
    }

    @Override
    public void setRebateRevisionDate(Date rebateRevisionDate) {
        _rebateRevisionDate = rebateRevisionDate;

        if (_imtdItemPriceRebateDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdItemPriceRebateDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setRebateRevisionDate",
                        Date.class);

                method.invoke(_imtdItemPriceRebateDetailsRemoteModel,
                    rebateRevisionDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getContractMasterSid() {
        return _contractMasterSid;
    }

    @Override
    public void setContractMasterSid(int contractMasterSid) {
        _contractMasterSid = contractMasterSid;

        if (_imtdItemPriceRebateDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdItemPriceRebateDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setContractMasterSid",
                        int.class);

                method.invoke(_imtdItemPriceRebateDetailsRemoteModel,
                    contractMasterSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getImtdCreatedDate() {
        return _imtdCreatedDate;
    }

    @Override
    public void setImtdCreatedDate(Date imtdCreatedDate) {
        _imtdCreatedDate = imtdCreatedDate;

        if (_imtdItemPriceRebateDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdItemPriceRebateDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setImtdCreatedDate", Date.class);

                method.invoke(_imtdItemPriceRebateDetailsRemoteModel,
                    imtdCreatedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getModifiedBy() {
        return _modifiedBy;
    }

    @Override
    public void setModifiedBy(int modifiedBy) {
        _modifiedBy = modifiedBy;

        if (_imtdItemPriceRebateDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdItemPriceRebateDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", int.class);

                method.invoke(_imtdItemPriceRebateDetailsRemoteModel, modifiedBy);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getUdc6() {
        return _udc6;
    }

    @Override
    public void setUdc6(int udc6) {
        _udc6 = udc6;

        if (_imtdItemPriceRebateDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdItemPriceRebateDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setUdc6", int.class);

                method.invoke(_imtdItemPriceRebateDetailsRemoteModel, udc6);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getUdc5() {
        return _udc5;
    }

    @Override
    public void setUdc5(int udc5) {
        _udc5 = udc5;

        if (_imtdItemPriceRebateDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdItemPriceRebateDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setUdc5", int.class);

                method.invoke(_imtdItemPriceRebateDetailsRemoteModel, udc5);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getUdc4() {
        return _udc4;
    }

    @Override
    public void setUdc4(int udc4) {
        _udc4 = udc4;

        if (_imtdItemPriceRebateDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdItemPriceRebateDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setUdc4", int.class);

                method.invoke(_imtdItemPriceRebateDetailsRemoteModel, udc4);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public boolean getCheckRecord() {
        return _checkRecord;
    }

    @Override
    public boolean isCheckRecord() {
        return _checkRecord;
    }

    @Override
    public void setCheckRecord(boolean checkRecord) {
        _checkRecord = checkRecord;

        if (_imtdItemPriceRebateDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdItemPriceRebateDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCheckRecord", boolean.class);

                method.invoke(_imtdItemPriceRebateDetailsRemoteModel,
                    checkRecord);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getUdc1() {
        return _udc1;
    }

    @Override
    public void setUdc1(int udc1) {
        _udc1 = udc1;

        if (_imtdItemPriceRebateDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdItemPriceRebateDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setUdc1", int.class);

                method.invoke(_imtdItemPriceRebateDetailsRemoteModel, udc1);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getUdc2() {
        return _udc2;
    }

    @Override
    public void setUdc2(int udc2) {
        _udc2 = udc2;

        if (_imtdItemPriceRebateDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdItemPriceRebateDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setUdc2", int.class);

                method.invoke(_imtdItemPriceRebateDetailsRemoteModel, udc2);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getUdc3() {
        return _udc3;
    }

    @Override
    public void setUdc3(int udc3) {
        _udc3 = udc3;

        if (_imtdItemPriceRebateDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdItemPriceRebateDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setUdc3", int.class);

                method.invoke(_imtdItemPriceRebateDetailsRemoteModel, udc3);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getContractPriceEndDate() {
        return _contractPriceEndDate;
    }

    @Override
    public void setContractPriceEndDate(Date contractPriceEndDate) {
        _contractPriceEndDate = contractPriceEndDate;

        if (_imtdItemPriceRebateDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdItemPriceRebateDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setContractPriceEndDate",
                        Date.class);

                method.invoke(_imtdItemPriceRebateDetailsRemoteModel,
                    contractPriceEndDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getTotalVolumeCommitment() {
        return _totalVolumeCommitment;
    }

    @Override
    public void setTotalVolumeCommitment(double totalVolumeCommitment) {
        _totalVolumeCommitment = totalVolumeCommitment;

        if (_imtdItemPriceRebateDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdItemPriceRebateDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setTotalVolumeCommitment",
                        double.class);

                method.invoke(_imtdItemPriceRebateDetailsRemoteModel,
                    totalVolumeCommitment);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getPriceProtectionEndDate() {
        return _priceProtectionEndDate;
    }

    @Override
    public void setPriceProtectionEndDate(Date priceProtectionEndDate) {
        _priceProtectionEndDate = priceProtectionEndDate;

        if (_imtdItemPriceRebateDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdItemPriceRebateDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setPriceProtectionEndDate",
                        Date.class);

                method.invoke(_imtdItemPriceRebateDetailsRemoteModel,
                    priceProtectionEndDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getRecordLockStatus() {
        return _recordLockStatus;
    }

    @Override
    public void setRecordLockStatus(String recordLockStatus) {
        _recordLockStatus = recordLockStatus;

        if (_imtdItemPriceRebateDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdItemPriceRebateDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setRecordLockStatus",
                        String.class);

                method.invoke(_imtdItemPriceRebateDetailsRemoteModel,
                    recordLockStatus);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getStartDate() {
        return _startDate;
    }

    @Override
    public void setStartDate(Date startDate) {
        _startDate = startDate;

        if (_imtdItemPriceRebateDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdItemPriceRebateDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setStartDate", Date.class);

                method.invoke(_imtdItemPriceRebateDetailsRemoteModel, startDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getRebateProgramType() {
        return _rebateProgramType;
    }

    @Override
    public void setRebateProgramType(int rebateProgramType) {
        _rebateProgramType = rebateProgramType;

        if (_imtdItemPriceRebateDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdItemPriceRebateDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setRebateProgramType",
                        int.class);

                method.invoke(_imtdItemPriceRebateDetailsRemoteModel,
                    rebateProgramType);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getSessionId() {
        return _sessionId;
    }

    @Override
    public void setSessionId(String sessionId) {
        _sessionId = sessionId;

        if (_imtdItemPriceRebateDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdItemPriceRebateDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setSessionId", String.class);

                method.invoke(_imtdItemPriceRebateDetailsRemoteModel, sessionId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getItemName() {
        return _itemName;
    }

    @Override
    public void setItemName(String itemName) {
        _itemName = itemName;

        if (_imtdItemPriceRebateDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdItemPriceRebateDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setItemName", String.class);

                method.invoke(_imtdItemPriceRebateDetailsRemoteModel, itemName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getPriceRevision() {
        return _priceRevision;
    }

    @Override
    public void setPriceRevision(double priceRevision) {
        _priceRevision = priceRevision;

        if (_imtdItemPriceRebateDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdItemPriceRebateDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setPriceRevision", double.class);

                method.invoke(_imtdItemPriceRebateDetailsRemoteModel,
                    priceRevision);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getRsModelSid() {
        return _rsModelSid;
    }

    @Override
    public void setRsModelSid(int rsModelSid) {
        _rsModelSid = rsModelSid;

        if (_imtdItemPriceRebateDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdItemPriceRebateDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setRsModelSid", int.class);

                method.invoke(_imtdItemPriceRebateDetailsRemoteModel, rsModelSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getPrice() {
        return _price;
    }

    @Override
    public void setPrice(double price) {
        _price = price;

        if (_imtdItemPriceRebateDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdItemPriceRebateDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setPrice", double.class);

                method.invoke(_imtdItemPriceRebateDetailsRemoteModel, price);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getRsAttachedStatus() {
        return _rsAttachedStatus;
    }

    @Override
    public void setRsAttachedStatus(int rsAttachedStatus) {
        _rsAttachedStatus = rsAttachedStatus;

        if (_imtdItemPriceRebateDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdItemPriceRebateDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setRsAttachedStatus", int.class);

                method.invoke(_imtdItemPriceRebateDetailsRemoteModel,
                    rsAttachedStatus);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getItemMasterSid() {
        return _itemMasterSid;
    }

    @Override
    public void setItemMasterSid(int itemMasterSid) {
        _itemMasterSid = itemMasterSid;

        if (_imtdItemPriceRebateDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdItemPriceRebateDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setItemMasterSid", int.class);

                method.invoke(_imtdItemPriceRebateDetailsRemoteModel,
                    itemMasterSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getTotalDollarCommitment() {
        return _totalDollarCommitment;
    }

    @Override
    public void setTotalDollarCommitment(double totalDollarCommitment) {
        _totalDollarCommitment = totalDollarCommitment;

        if (_imtdItemPriceRebateDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdItemPriceRebateDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setTotalDollarCommitment",
                        double.class);

                method.invoke(_imtdItemPriceRebateDetailsRemoteModel,
                    totalDollarCommitment);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getItemType() {
        return _itemType;
    }

    @Override
    public void setItemType(int itemType) {
        _itemType = itemType;

        if (_imtdItemPriceRebateDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdItemPriceRebateDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setItemType", int.class);

                method.invoke(_imtdItemPriceRebateDetailsRemoteModel, itemType);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getTotalMarketShareCommitmnet() {
        return _totalMarketShareCommitmnet;
    }

    @Override
    public void setTotalMarketShareCommitmnet(double totalMarketShareCommitmnet) {
        _totalMarketShareCommitmnet = totalMarketShareCommitmnet;

        if (_imtdItemPriceRebateDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdItemPriceRebateDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setTotalMarketShareCommitmnet",
                        double.class);

                method.invoke(_imtdItemPriceRebateDetailsRemoteModel,
                    totalMarketShareCommitmnet);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getItemId() {
        return _itemId;
    }

    @Override
    public void setItemId(String itemId) {
        _itemId = itemId;

        if (_imtdItemPriceRebateDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdItemPriceRebateDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setItemId", String.class);

                method.invoke(_imtdItemPriceRebateDetailsRemoteModel, itemId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getBasePrice() {
        return _basePrice;
    }

    @Override
    public void setBasePrice(double basePrice) {
        _basePrice = basePrice;

        if (_imtdItemPriceRebateDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdItemPriceRebateDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setBasePrice", double.class);

                method.invoke(_imtdItemPriceRebateDetailsRemoteModel, basePrice);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getBundleNo() {
        return _bundleNo;
    }

    @Override
    public void setBundleNo(String bundleNo) {
        _bundleNo = bundleNo;

        if (_imtdItemPriceRebateDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdItemPriceRebateDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setBundleNo", String.class);

                method.invoke(_imtdItemPriceRebateDetailsRemoteModel, bundleNo);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getFormulaName() {
        return _formulaName;
    }

    @Override
    public void setFormulaName(String formulaName) {
        _formulaName = formulaName;

        if (_imtdItemPriceRebateDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdItemPriceRebateDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setFormulaName", String.class);

                method.invoke(_imtdItemPriceRebateDetailsRemoteModel,
                    formulaName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getPsStatus() {
        return _psStatus;
    }

    @Override
    public void setPsStatus(int psStatus) {
        _psStatus = psStatus;

        if (_imtdItemPriceRebateDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdItemPriceRebateDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setPsStatus", int.class);

                method.invoke(_imtdItemPriceRebateDetailsRemoteModel, psStatus);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getPriceTolerance() {
        return _priceTolerance;
    }

    @Override
    public void setPriceTolerance(double priceTolerance) {
        _priceTolerance = priceTolerance;

        if (_imtdItemPriceRebateDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdItemPriceRebateDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setPriceTolerance",
                        double.class);

                method.invoke(_imtdItemPriceRebateDetailsRemoteModel,
                    priceTolerance);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getCreatedDate() {
        return _createdDate;
    }

    @Override
    public void setCreatedDate(Date createdDate) {
        _createdDate = createdDate;

        if (_imtdItemPriceRebateDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdItemPriceRebateDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_imtdItemPriceRebateDetailsRemoteModel,
                    createdDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getCreatedBy() {
        return _createdBy;
    }

    @Override
    public void setCreatedBy(int createdBy) {
        _createdBy = createdBy;

        if (_imtdItemPriceRebateDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdItemPriceRebateDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", int.class);

                method.invoke(_imtdItemPriceRebateDetailsRemoteModel, createdBy);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getUsersSid() {
        return _usersSid;
    }

    @Override
    public void setUsersSid(int usersSid) {
        _usersSid = usersSid;

        if (_imtdItemPriceRebateDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdItemPriceRebateDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setUsersSid", int.class);

                method.invoke(_imtdItemPriceRebateDetailsRemoteModel, usersSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getPsDetailsSid() {
        return _psDetailsSid;
    }

    @Override
    public void setPsDetailsSid(int psDetailsSid) {
        _psDetailsSid = psDetailsSid;

        if (_imtdItemPriceRebateDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdItemPriceRebateDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setPsDetailsSid", int.class);

                method.invoke(_imtdItemPriceRebateDetailsRemoteModel,
                    psDetailsSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getSuggestedPrice() {
        return _suggestedPrice;
    }

    @Override
    public void setSuggestedPrice(double suggestedPrice) {
        _suggestedPrice = suggestedPrice;

        if (_imtdItemPriceRebateDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdItemPriceRebateDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setSuggestedPrice",
                        double.class);

                method.invoke(_imtdItemPriceRebateDetailsRemoteModel,
                    suggestedPrice);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getPsModelSid() {
        return _psModelSid;
    }

    @Override
    public void setPsModelSid(int psModelSid) {
        _psModelSid = psModelSid;

        if (_imtdItemPriceRebateDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdItemPriceRebateDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setPsModelSid", int.class);

                method.invoke(_imtdItemPriceRebateDetailsRemoteModel, psModelSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getFormulaId() {
        return _formulaId;
    }

    @Override
    public void setFormulaId(String formulaId) {
        _formulaId = formulaId;

        if (_imtdItemPriceRebateDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdItemPriceRebateDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setFormulaId", String.class);

                method.invoke(_imtdItemPriceRebateDetailsRemoteModel, formulaId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getCommitmentPeriod() {
        return _commitmentPeriod;
    }

    @Override
    public void setCommitmentPeriod(String commitmentPeriod) {
        _commitmentPeriod = commitmentPeriod;

        if (_imtdItemPriceRebateDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdItemPriceRebateDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCommitmentPeriod",
                        String.class);

                method.invoke(_imtdItemPriceRebateDetailsRemoteModel,
                    commitmentPeriod);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getItemNo() {
        return _itemNo;
    }

    @Override
    public void setItemNo(String itemNo) {
        _itemNo = itemNo;

        if (_imtdItemPriceRebateDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdItemPriceRebateDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setItemNo", String.class);

                method.invoke(_imtdItemPriceRebateDetailsRemoteModel, itemNo);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getContractPrice() {
        return _contractPrice;
    }

    @Override
    public void setContractPrice(double contractPrice) {
        _contractPrice = contractPrice;

        if (_imtdItemPriceRebateDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdItemPriceRebateDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setContractPrice", double.class);

                method.invoke(_imtdItemPriceRebateDetailsRemoteModel,
                    contractPrice);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getIfpDetailsSid() {
        return _ifpDetailsSid;
    }

    @Override
    public void setIfpDetailsSid(int ifpDetailsSid) {
        _ifpDetailsSid = ifpDetailsSid;

        if (_imtdItemPriceRebateDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdItemPriceRebateDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setIfpDetailsSid", int.class);

                method.invoke(_imtdItemPriceRebateDetailsRemoteModel,
                    ifpDetailsSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getIfpModelSid() {
        return _ifpModelSid;
    }

    @Override
    public void setIfpModelSid(int ifpModelSid) {
        _ifpModelSid = ifpModelSid;

        if (_imtdItemPriceRebateDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdItemPriceRebateDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setIfpModelSid", int.class);

                method.invoke(_imtdItemPriceRebateDetailsRemoteModel,
                    ifpModelSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getPriceToleranceType() {
        return _priceToleranceType;
    }

    @Override
    public void setPriceToleranceType(String priceToleranceType) {
        _priceToleranceType = priceToleranceType;

        if (_imtdItemPriceRebateDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdItemPriceRebateDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setPriceToleranceType",
                        String.class);

                method.invoke(_imtdItemPriceRebateDetailsRemoteModel,
                    priceToleranceType);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getRebateAmount() {
        return _rebateAmount;
    }

    @Override
    public void setRebateAmount(double rebateAmount) {
        _rebateAmount = rebateAmount;

        if (_imtdItemPriceRebateDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdItemPriceRebateDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setRebateAmount", double.class);

                method.invoke(_imtdItemPriceRebateDetailsRemoteModel,
                    rebateAmount);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getContractPriceStartDate() {
        return _contractPriceStartDate;
    }

    @Override
    public void setContractPriceStartDate(Date contractPriceStartDate) {
        _contractPriceStartDate = contractPriceStartDate;

        if (_imtdItemPriceRebateDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdItemPriceRebateDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setContractPriceStartDate",
                        Date.class);

                method.invoke(_imtdItemPriceRebateDetailsRemoteModel,
                    contractPriceStartDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getRebateScheduleType() {
        return _rebateScheduleType;
    }

    @Override
    public void setRebateScheduleType(String rebateScheduleType) {
        _rebateScheduleType = rebateScheduleType;

        if (_imtdItemPriceRebateDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdItemPriceRebateDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setRebateScheduleType",
                        String.class);

                method.invoke(_imtdItemPriceRebateDetailsRemoteModel,
                    rebateScheduleType);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getPriceToleranceFrequency() {
        return _priceToleranceFrequency;
    }

    @Override
    public void setPriceToleranceFrequency(String priceToleranceFrequency) {
        _priceToleranceFrequency = priceToleranceFrequency;

        if (_imtdItemPriceRebateDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdItemPriceRebateDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setPriceToleranceFrequency",
                        String.class);

                method.invoke(_imtdItemPriceRebateDetailsRemoteModel,
                    priceToleranceFrequency);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getImtdItemPriceRebateSid() {
        return _imtdItemPriceRebateSid;
    }

    @Override
    public void setImtdItemPriceRebateSid(int imtdItemPriceRebateSid) {
        _imtdItemPriceRebateSid = imtdItemPriceRebateSid;

        if (_imtdItemPriceRebateDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdItemPriceRebateDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setImtdItemPriceRebateSid",
                        int.class);

                method.invoke(_imtdItemPriceRebateDetailsRemoteModel,
                    imtdItemPriceRebateSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getRebatePlanSystemId() {
        return _rebatePlanSystemId;
    }

    @Override
    public void setRebatePlanSystemId(String rebatePlanSystemId) {
        _rebatePlanSystemId = rebatePlanSystemId;

        if (_imtdItemPriceRebateDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdItemPriceRebateDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setRebatePlanSystemId",
                        String.class);

                method.invoke(_imtdItemPriceRebateDetailsRemoteModel,
                    rebatePlanSystemId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getAttachedDate() {
        return _attachedDate;
    }

    @Override
    public void setAttachedDate(Date attachedDate) {
        _attachedDate = attachedDate;

        if (_imtdItemPriceRebateDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdItemPriceRebateDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setAttachedDate", Date.class);

                method.invoke(_imtdItemPriceRebateDetailsRemoteModel,
                    attachedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getPricePlanId() {
        return _pricePlanId;
    }

    @Override
    public void setPricePlanId(String pricePlanId) {
        _pricePlanId = pricePlanId;

        if (_imtdItemPriceRebateDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdItemPriceRebateDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setPricePlanId", String.class);

                method.invoke(_imtdItemPriceRebateDetailsRemoteModel,
                    pricePlanId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getItemRebateEndDate() {
        return _itemRebateEndDate;
    }

    @Override
    public void setItemRebateEndDate(Date itemRebateEndDate) {
        _itemRebateEndDate = itemRebateEndDate;

        if (_imtdItemPriceRebateDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdItemPriceRebateDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setItemRebateEndDate",
                        Date.class);

                method.invoke(_imtdItemPriceRebateDetailsRemoteModel,
                    itemRebateEndDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getPriceType() {
        return _priceType;
    }

    @Override
    public void setPriceType(int priceType) {
        _priceType = priceType;

        if (_imtdItemPriceRebateDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdItemPriceRebateDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setPriceType", int.class);

                method.invoke(_imtdItemPriceRebateDetailsRemoteModel, priceType);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getPriceToleranceInterval() {
        return _priceToleranceInterval;
    }

    @Override
    public void setPriceToleranceInterval(int priceToleranceInterval) {
        _priceToleranceInterval = priceToleranceInterval;

        if (_imtdItemPriceRebateDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdItemPriceRebateDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setPriceToleranceInterval",
                        int.class);

                method.invoke(_imtdItemPriceRebateDetailsRemoteModel,
                    priceToleranceInterval);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getRsAttachedDate() {
        return _rsAttachedDate;
    }

    @Override
    public void setRsAttachedDate(Date rsAttachedDate) {
        _rsAttachedDate = rsAttachedDate;

        if (_imtdItemPriceRebateDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdItemPriceRebateDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setRsAttachedDate", Date.class);

                method.invoke(_imtdItemPriceRebateDetailsRemoteModel,
                    rsAttachedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getItemRebateStartDate() {
        return _itemRebateStartDate;
    }

    @Override
    public void setItemRebateStartDate(Date itemRebateStartDate) {
        _itemRebateStartDate = itemRebateStartDate;

        if (_imtdItemPriceRebateDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdItemPriceRebateDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setItemRebateStartDate",
                        Date.class);

                method.invoke(_imtdItemPriceRebateDetailsRemoteModel,
                    itemRebateStartDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getOperation() {
        return _operation;
    }

    @Override
    public void setOperation(String operation) {
        _operation = operation;

        if (_imtdItemPriceRebateDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdItemPriceRebateDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setOperation", String.class);

                method.invoke(_imtdItemPriceRebateDetailsRemoteModel, operation);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getCfpModelSid() {
        return _cfpModelSid;
    }

    @Override
    public void setCfpModelSid(int cfpModelSid) {
        _cfpModelSid = cfpModelSid;

        if (_imtdItemPriceRebateDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdItemPriceRebateDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCfpModelSid", int.class);

                method.invoke(_imtdItemPriceRebateDetailsRemoteModel,
                    cfpModelSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getRsDetailsSid() {
        return _rsDetailsSid;
    }

    @Override
    public void setRsDetailsSid(int rsDetailsSid) {
        _rsDetailsSid = rsDetailsSid;

        if (_imtdItemPriceRebateDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdItemPriceRebateDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setRsDetailsSid", int.class);

                method.invoke(_imtdItemPriceRebateDetailsRemoteModel,
                    rsDetailsSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getAttachedStatus() {
        return _attachedStatus;
    }

    @Override
    public void setAttachedStatus(int attachedStatus) {
        _attachedStatus = attachedStatus;

        if (_imtdItemPriceRebateDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdItemPriceRebateDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setAttachedStatus", int.class);

                method.invoke(_imtdItemPriceRebateDetailsRemoteModel,
                    attachedStatus);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getPrimaryUom() {
        return _primaryUom;
    }

    @Override
    public void setPrimaryUom(int primaryUom) {
        _primaryUom = primaryUom;

        if (_imtdItemPriceRebateDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdItemPriceRebateDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setPrimaryUom", int.class);

                method.invoke(_imtdItemPriceRebateDetailsRemoteModel, primaryUom);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getPackageSize() {
        return _packageSize;
    }

    @Override
    public void setPackageSize(String packageSize) {
        _packageSize = packageSize;

        if (_imtdItemPriceRebateDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdItemPriceRebateDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setPackageSize", String.class);

                method.invoke(_imtdItemPriceRebateDetailsRemoteModel,
                    packageSize);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getDeductionCalendarMasterSid() {
        return _deductionCalendarMasterSid;
    }

    @Override
    public void setDeductionCalendarMasterSid(String deductionCalendarMasterSid) {
        _deductionCalendarMasterSid = deductionCalendarMasterSid;

        if (_imtdItemPriceRebateDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdItemPriceRebateDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setDeductionCalendarMasterSid",
                        String.class);

                method.invoke(_imtdItemPriceRebateDetailsRemoteModel,
                    deductionCalendarMasterSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getRsContractDetailsDeductionCalendarNo() {
        return _rsContractDetailsDeductionCalendarNo;
    }

    @Override
    public void setRsContractDetailsDeductionCalendarNo(
        String rsContractDetailsDeductionCalendarNo) {
        _rsContractDetailsDeductionCalendarNo = rsContractDetailsDeductionCalendarNo;

        if (_imtdItemPriceRebateDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdItemPriceRebateDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setRsContractDetailsDeductionCalendarNo",
                        String.class);

                method.invoke(_imtdItemPriceRebateDetailsRemoteModel,
                    rsContractDetailsDeductionCalendarNo);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getRsContractDetailsDeductionCalendarName() {
        return _rsContractDetailsDeductionCalendarName;
    }

    @Override
    public void setRsContractDetailsDeductionCalendarName(
        String rsContractDetailsDeductionCalendarName) {
        _rsContractDetailsDeductionCalendarName = rsContractDetailsDeductionCalendarName;

        if (_imtdItemPriceRebateDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdItemPriceRebateDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setRsContractDetailsDeductionCalendarName",
                        String.class);

                method.invoke(_imtdItemPriceRebateDetailsRemoteModel,
                    rsContractDetailsDeductionCalendarName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getNetSalesFormulaMasterSid() {
        return _netSalesFormulaMasterSid;
    }

    @Override
    public void setNetSalesFormulaMasterSid(String netSalesFormulaMasterSid) {
        _netSalesFormulaMasterSid = netSalesFormulaMasterSid;

        if (_imtdItemPriceRebateDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdItemPriceRebateDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setNetSalesFormulaMasterSid",
                        String.class);

                method.invoke(_imtdItemPriceRebateDetailsRemoteModel,
                    netSalesFormulaMasterSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getRsContractDetailsNetSalesFormulaNo() {
        return _rsContractDetailsNetSalesFormulaNo;
    }

    @Override
    public void setRsContractDetailsNetSalesFormulaNo(
        String rsContractDetailsNetSalesFormulaNo) {
        _rsContractDetailsNetSalesFormulaNo = rsContractDetailsNetSalesFormulaNo;

        if (_imtdItemPriceRebateDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdItemPriceRebateDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setRsContractDetailsNetSalesFormulaNo",
                        String.class);

                method.invoke(_imtdItemPriceRebateDetailsRemoteModel,
                    rsContractDetailsNetSalesFormulaNo);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getRsContractDetailsNetSalesFormulaName() {
        return _rsContractDetailsNetSalesFormulaName;
    }

    @Override
    public void setRsContractDetailsNetSalesFormulaName(
        String rsContractDetailsNetSalesFormulaName) {
        _rsContractDetailsNetSalesFormulaName = rsContractDetailsNetSalesFormulaName;

        if (_imtdItemPriceRebateDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdItemPriceRebateDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setRsContractDetailsNetSalesFormulaName",
                        String.class);

                method.invoke(_imtdItemPriceRebateDetailsRemoteModel,
                    rsContractDetailsNetSalesFormulaName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getFormulaType() {
        return _formulaType;
    }

    @Override
    public void setFormulaType(int formulaType) {
        _formulaType = formulaType;

        if (_imtdItemPriceRebateDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdItemPriceRebateDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setFormulaType", int.class);

                method.invoke(_imtdItemPriceRebateDetailsRemoteModel,
                    formulaType);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getNetSalesRule() {
        return _netSalesRule;
    }

    @Override
    public void setNetSalesRule(int netSalesRule) {
        _netSalesRule = netSalesRule;

        if (_imtdItemPriceRebateDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdItemPriceRebateDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setNetSalesRule", int.class);

                method.invoke(_imtdItemPriceRebateDetailsRemoteModel,
                    netSalesRule);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getEvaluationRule() {
        return _evaluationRule;
    }

    @Override
    public void setEvaluationRule(int evaluationRule) {
        _evaluationRule = evaluationRule;

        if (_imtdItemPriceRebateDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdItemPriceRebateDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setEvaluationRule", int.class);

                method.invoke(_imtdItemPriceRebateDetailsRemoteModel,
                    evaluationRule);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getEvaluationRuleBundle() {
        return _evaluationRuleBundle;
    }

    @Override
    public void setEvaluationRuleBundle(String evaluationRuleBundle) {
        _evaluationRuleBundle = evaluationRuleBundle;

        if (_imtdItemPriceRebateDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdItemPriceRebateDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setEvaluationRuleBundle",
                        String.class);

                method.invoke(_imtdItemPriceRebateDetailsRemoteModel,
                    evaluationRuleBundle);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getCalculationRule() {
        return _calculationRule;
    }

    @Override
    public void setCalculationRule(int calculationRule) {
        _calculationRule = calculationRule;

        if (_imtdItemPriceRebateDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdItemPriceRebateDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCalculationRule", int.class);

                method.invoke(_imtdItemPriceRebateDetailsRemoteModel,
                    calculationRule);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getCalculationRuleBundle() {
        return _calculationRuleBundle;
    }

    @Override
    public void setCalculationRuleBundle(String calculationRuleBundle) {
        _calculationRuleBundle = calculationRuleBundle;

        if (_imtdItemPriceRebateDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdItemPriceRebateDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCalculationRuleBundle",
                        String.class);

                method.invoke(_imtdItemPriceRebateDetailsRemoteModel,
                    calculationRuleBundle);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getMaxIncrementalChange() {
        return _maxIncrementalChange;
    }

    @Override
    public void setMaxIncrementalChange(double maxIncrementalChange) {
        _maxIncrementalChange = maxIncrementalChange;

        if (_imtdItemPriceRebateDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdItemPriceRebateDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setMaxIncrementalChange",
                        double.class);

                method.invoke(_imtdItemPriceRebateDetailsRemoteModel,
                    maxIncrementalChange);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getResetEligible() {
        return _resetEligible;
    }

    @Override
    public void setResetEligible(int resetEligible) {
        _resetEligible = resetEligible;

        if (_imtdItemPriceRebateDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdItemPriceRebateDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setResetEligible", int.class);

                method.invoke(_imtdItemPriceRebateDetailsRemoteModel,
                    resetEligible);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getResetType() {
        return _resetType;
    }

    @Override
    public void setResetType(int resetType) {
        _resetType = resetType;

        if (_imtdItemPriceRebateDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdItemPriceRebateDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setResetType", int.class);

                method.invoke(_imtdItemPriceRebateDetailsRemoteModel, resetType);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getResetDate() {
        return _resetDate;
    }

    @Override
    public void setResetDate(Date resetDate) {
        _resetDate = resetDate;

        if (_imtdItemPriceRebateDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdItemPriceRebateDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setResetDate", Date.class);

                method.invoke(_imtdItemPriceRebateDetailsRemoteModel, resetDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getResetInterval() {
        return _resetInterval;
    }

    @Override
    public void setResetInterval(int resetInterval) {
        _resetInterval = resetInterval;

        if (_imtdItemPriceRebateDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdItemPriceRebateDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setResetInterval", int.class);

                method.invoke(_imtdItemPriceRebateDetailsRemoteModel,
                    resetInterval);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getResetFrequency() {
        return _resetFrequency;
    }

    @Override
    public void setResetFrequency(int resetFrequency) {
        _resetFrequency = resetFrequency;

        if (_imtdItemPriceRebateDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdItemPriceRebateDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setResetFrequency", int.class);

                method.invoke(_imtdItemPriceRebateDetailsRemoteModel,
                    resetFrequency);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getNetPriceType() {
        return _netPriceType;
    }

    @Override
    public void setNetPriceType(int netPriceType) {
        _netPriceType = netPriceType;

        if (_imtdItemPriceRebateDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdItemPriceRebateDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setNetPriceType", int.class);

                method.invoke(_imtdItemPriceRebateDetailsRemoteModel,
                    netPriceType);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getNetPriceTypeFormula() {
        return _netPriceTypeFormula;
    }

    @Override
    public void setNetPriceTypeFormula(String netPriceTypeFormula) {
        _netPriceTypeFormula = netPriceTypeFormula;

        if (_imtdItemPriceRebateDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdItemPriceRebateDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setNetPriceTypeFormula",
                        String.class);

                method.invoke(_imtdItemPriceRebateDetailsRemoteModel,
                    netPriceTypeFormula);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getPriceProtectionPriceType() {
        return _priceProtectionPriceType;
    }

    @Override
    public void setPriceProtectionPriceType(int priceProtectionPriceType) {
        _priceProtectionPriceType = priceProtectionPriceType;

        if (_imtdItemPriceRebateDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdItemPriceRebateDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setPriceProtectionPriceType",
                        int.class);

                method.invoke(_imtdItemPriceRebateDetailsRemoteModel,
                    priceProtectionPriceType);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getNep() {
        return _nep;
    }

    @Override
    public void setNep(double nep) {
        _nep = nep;

        if (_imtdItemPriceRebateDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdItemPriceRebateDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setNep", double.class);

                method.invoke(_imtdItemPriceRebateDetailsRemoteModel, nep);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getNepFormula() {
        return _nepFormula;
    }

    @Override
    public void setNepFormula(int nepFormula) {
        _nepFormula = nepFormula;

        if (_imtdItemPriceRebateDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdItemPriceRebateDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setNepFormula", int.class);

                method.invoke(_imtdItemPriceRebateDetailsRemoteModel, nepFormula);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getBrandMasterSid() {
        return _brandMasterSid;
    }

    @Override
    public void setBrandMasterSid(String brandMasterSid) {
        _brandMasterSid = brandMasterSid;

        if (_imtdItemPriceRebateDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdItemPriceRebateDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setBrandMasterSid",
                        String.class);

                method.invoke(_imtdItemPriceRebateDetailsRemoteModel,
                    brandMasterSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getPriceProtectionStatus() {
        return _priceProtectionStatus;
    }

    @Override
    public void setPriceProtectionStatus(int priceProtectionStatus) {
        _priceProtectionStatus = priceProtectionStatus;

        if (_imtdItemPriceRebateDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdItemPriceRebateDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setPriceProtectionStatus",
                        int.class);

                method.invoke(_imtdItemPriceRebateDetailsRemoteModel,
                    priceProtectionStatus);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getBasePriceType() {
        return _basePriceType;
    }

    @Override
    public void setBasePriceType(int basePriceType) {
        _basePriceType = basePriceType;

        if (_imtdItemPriceRebateDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdItemPriceRebateDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setBasePriceType", int.class);

                method.invoke(_imtdItemPriceRebateDetailsRemoteModel,
                    basePriceType);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getBasePriceEntry() {
        return _basePriceEntry;
    }

    @Override
    public void setBasePriceEntry(double basePriceEntry) {
        _basePriceEntry = basePriceEntry;

        if (_imtdItemPriceRebateDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdItemPriceRebateDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setBasePriceEntry",
                        double.class);

                method.invoke(_imtdItemPriceRebateDetailsRemoteModel,
                    basePriceEntry);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getBasePriceDate() {
        return _basePriceDate;
    }

    @Override
    public void setBasePriceDate(Date basePriceDate) {
        _basePriceDate = basePriceDate;

        if (_imtdItemPriceRebateDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdItemPriceRebateDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setBasePriceDate", Date.class);

                method.invoke(_imtdItemPriceRebateDetailsRemoteModel,
                    basePriceDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getBasePriceDdlb() {
        return _basePriceDdlb;
    }

    @Override
    public void setBasePriceDdlb(int basePriceDdlb) {
        _basePriceDdlb = basePriceDdlb;

        if (_imtdItemPriceRebateDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdItemPriceRebateDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setBasePriceDdlb", int.class);

                method.invoke(_imtdItemPriceRebateDetailsRemoteModel,
                    basePriceDdlb);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getNetBasePrice() {
        return _netBasePrice;
    }

    @Override
    public void setNetBasePrice(int netBasePrice) {
        _netBasePrice = netBasePrice;

        if (_imtdItemPriceRebateDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdItemPriceRebateDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setNetBasePrice", int.class);

                method.invoke(_imtdItemPriceRebateDetailsRemoteModel,
                    netBasePrice);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getSubsequentPeriodPriceType() {
        return _subsequentPeriodPriceType;
    }

    @Override
    public void setSubsequentPeriodPriceType(int subsequentPeriodPriceType) {
        _subsequentPeriodPriceType = subsequentPeriodPriceType;

        if (_imtdItemPriceRebateDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdItemPriceRebateDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setSubsequentPeriodPriceType",
                        int.class);

                method.invoke(_imtdItemPriceRebateDetailsRemoteModel,
                    subsequentPeriodPriceType);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getResetPriceType() {
        return _resetPriceType;
    }

    @Override
    public void setResetPriceType(int resetPriceType) {
        _resetPriceType = resetPriceType;

        if (_imtdItemPriceRebateDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdItemPriceRebateDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setResetPriceType", int.class);

                method.invoke(_imtdItemPriceRebateDetailsRemoteModel,
                    resetPriceType);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getNetResetPriceType() {
        return _netResetPriceType;
    }

    @Override
    public void setNetResetPriceType(int netResetPriceType) {
        _netResetPriceType = netResetPriceType;

        if (_imtdItemPriceRebateDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdItemPriceRebateDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setNetResetPriceType",
                        int.class);

                method.invoke(_imtdItemPriceRebateDetailsRemoteModel,
                    netResetPriceType);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getNetResetPriceFormulaId() {
        return _netResetPriceFormulaId;
    }

    @Override
    public void setNetResetPriceFormulaId(int netResetPriceFormulaId) {
        _netResetPriceFormulaId = netResetPriceFormulaId;

        if (_imtdItemPriceRebateDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdItemPriceRebateDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setNetResetPriceFormulaId",
                        int.class);

                method.invoke(_imtdItemPriceRebateDetailsRemoteModel,
                    netResetPriceFormulaId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getNetBasePriceFormulaId() {
        return _netBasePriceFormulaId;
    }

    @Override
    public void setNetBasePriceFormulaId(int netBasePriceFormulaId) {
        _netBasePriceFormulaId = netBasePriceFormulaId;

        if (_imtdItemPriceRebateDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdItemPriceRebateDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setNetBasePriceFormulaId",
                        int.class);

                method.invoke(_imtdItemPriceRebateDetailsRemoteModel,
                    netBasePriceFormulaId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getNetSubsequentPriceFormulaId() {
        return _netSubsequentPriceFormulaId;
    }

    @Override
    public void setNetSubsequentPriceFormulaId(int netSubsequentPriceFormulaId) {
        _netSubsequentPriceFormulaId = netSubsequentPriceFormulaId;

        if (_imtdItemPriceRebateDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdItemPriceRebateDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setNetSubsequentPriceFormulaId",
                        int.class);

                method.invoke(_imtdItemPriceRebateDetailsRemoteModel,
                    netSubsequentPriceFormulaId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getNetSubsequentPeriodPrice() {
        return _netSubsequentPeriodPrice;
    }

    @Override
    public void setNetSubsequentPeriodPrice(int netSubsequentPeriodPrice) {
        _netSubsequentPeriodPrice = netSubsequentPeriodPrice;

        if (_imtdItemPriceRebateDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdItemPriceRebateDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setNetSubsequentPeriodPrice",
                        int.class);

                method.invoke(_imtdItemPriceRebateDetailsRemoteModel,
                    netSubsequentPeriodPrice);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getRsContractDetailsRebatePlanName() {
        return _rsContractDetailsRebatePlanName;
    }

    @Override
    public void setRsContractDetailsRebatePlanName(
        String rsContractDetailsRebatePlanName) {
        _rsContractDetailsRebatePlanName = rsContractDetailsRebatePlanName;

        if (_imtdItemPriceRebateDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdItemPriceRebateDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setRsContractDetailsRebatePlanName",
                        String.class);

                method.invoke(_imtdItemPriceRebateDetailsRemoteModel,
                    rsContractDetailsRebatePlanName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getRsContractDetailsFormulaNo() {
        return _rsContractDetailsFormulaNo;
    }

    @Override
    public void setRsContractDetailsFormulaNo(String rsContractDetailsFormulaNo) {
        _rsContractDetailsFormulaNo = rsContractDetailsFormulaNo;

        if (_imtdItemPriceRebateDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdItemPriceRebateDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setRsContractDetailsFormulaNo",
                        String.class);

                method.invoke(_imtdItemPriceRebateDetailsRemoteModel,
                    rsContractDetailsFormulaNo);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getSource() {
        return _source;
    }

    @Override
    public void setSource(String source) {
        _source = source;

        if (_imtdItemPriceRebateDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdItemPriceRebateDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setSource", String.class);

                method.invoke(_imtdItemPriceRebateDetailsRemoteModel, source);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getImtdItemPriceRebateDetailsRemoteModel() {
        return _imtdItemPriceRebateDetailsRemoteModel;
    }

    public void setImtdItemPriceRebateDetailsRemoteModel(
        BaseModel<?> imtdItemPriceRebateDetailsRemoteModel) {
        _imtdItemPriceRebateDetailsRemoteModel = imtdItemPriceRebateDetailsRemoteModel;
    }

    public Object invokeOnRemoteModel(String methodName,
        Class<?>[] parameterTypes, Object[] parameterValues)
        throws Exception {
        Object[] remoteParameterValues = new Object[parameterValues.length];

        for (int i = 0; i < parameterValues.length; i++) {
            if (parameterValues[i] != null) {
                remoteParameterValues[i] = ClpSerializer.translateInput(parameterValues[i]);
            }
        }

        Class<?> remoteModelClass = _imtdItemPriceRebateDetailsRemoteModel.getClass();

        ClassLoader remoteModelClassLoader = remoteModelClass.getClassLoader();

        Class<?>[] remoteParameterTypes = new Class[parameterTypes.length];

        for (int i = 0; i < parameterTypes.length; i++) {
            if (parameterTypes[i].isPrimitive()) {
                remoteParameterTypes[i] = parameterTypes[i];
            } else {
                String parameterTypeName = parameterTypes[i].getName();

                remoteParameterTypes[i] = remoteModelClassLoader.loadClass(parameterTypeName);
            }
        }

        Method method = remoteModelClass.getMethod(methodName,
                remoteParameterTypes);

        Object returnValue = method.invoke(_imtdItemPriceRebateDetailsRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            ImtdItemPriceRebateDetailsLocalServiceUtil.addImtdItemPriceRebateDetails(this);
        } else {
            ImtdItemPriceRebateDetailsLocalServiceUtil.updateImtdItemPriceRebateDetails(this);
        }
    }

    @Override
    public ImtdItemPriceRebateDetails toEscapedModel() {
        return (ImtdItemPriceRebateDetails) ProxyUtil.newProxyInstance(ImtdItemPriceRebateDetails.class.getClassLoader(),
            new Class[] { ImtdItemPriceRebateDetails.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        ImtdItemPriceRebateDetailsClp clone = new ImtdItemPriceRebateDetailsClp();

        clone.setFormulaMethodId(getFormulaMethodId());
        clone.setEndDate(getEndDate());
        clone.setPriceProtectionStartDate(getPriceProtectionStartDate());
        clone.setItemPriceRevisionDate(getItemPriceRevisionDate());
        clone.setModifiedDate(getModifiedDate());
        clone.setRsCheckRecord(getRsCheckRecord());
        clone.setRebateRevisionDate(getRebateRevisionDate());
        clone.setContractMasterSid(getContractMasterSid());
        clone.setImtdCreatedDate(getImtdCreatedDate());
        clone.setModifiedBy(getModifiedBy());
        clone.setUdc6(getUdc6());
        clone.setUdc5(getUdc5());
        clone.setUdc4(getUdc4());
        clone.setCheckRecord(getCheckRecord());
        clone.setUdc1(getUdc1());
        clone.setUdc2(getUdc2());
        clone.setUdc3(getUdc3());
        clone.setContractPriceEndDate(getContractPriceEndDate());
        clone.setTotalVolumeCommitment(getTotalVolumeCommitment());
        clone.setPriceProtectionEndDate(getPriceProtectionEndDate());
        clone.setRecordLockStatus(getRecordLockStatus());
        clone.setStartDate(getStartDate());
        clone.setRebateProgramType(getRebateProgramType());
        clone.setSessionId(getSessionId());
        clone.setItemName(getItemName());
        clone.setPriceRevision(getPriceRevision());
        clone.setRsModelSid(getRsModelSid());
        clone.setPrice(getPrice());
        clone.setRsAttachedStatus(getRsAttachedStatus());
        clone.setItemMasterSid(getItemMasterSid());
        clone.setTotalDollarCommitment(getTotalDollarCommitment());
        clone.setItemType(getItemType());
        clone.setTotalMarketShareCommitmnet(getTotalMarketShareCommitmnet());
        clone.setItemId(getItemId());
        clone.setBasePrice(getBasePrice());
        clone.setBundleNo(getBundleNo());
        clone.setFormulaName(getFormulaName());
        clone.setPsStatus(getPsStatus());
        clone.setPriceTolerance(getPriceTolerance());
        clone.setCreatedDate(getCreatedDate());
        clone.setCreatedBy(getCreatedBy());
        clone.setUsersSid(getUsersSid());
        clone.setPsDetailsSid(getPsDetailsSid());
        clone.setSuggestedPrice(getSuggestedPrice());
        clone.setPsModelSid(getPsModelSid());
        clone.setFormulaId(getFormulaId());
        clone.setCommitmentPeriod(getCommitmentPeriod());
        clone.setItemNo(getItemNo());
        clone.setContractPrice(getContractPrice());
        clone.setIfpDetailsSid(getIfpDetailsSid());
        clone.setIfpModelSid(getIfpModelSid());
        clone.setPriceToleranceType(getPriceToleranceType());
        clone.setRebateAmount(getRebateAmount());
        clone.setContractPriceStartDate(getContractPriceStartDate());
        clone.setRebateScheduleType(getRebateScheduleType());
        clone.setPriceToleranceFrequency(getPriceToleranceFrequency());
        clone.setImtdItemPriceRebateSid(getImtdItemPriceRebateSid());
        clone.setRebatePlanSystemId(getRebatePlanSystemId());
        clone.setAttachedDate(getAttachedDate());
        clone.setPricePlanId(getPricePlanId());
        clone.setItemRebateEndDate(getItemRebateEndDate());
        clone.setPriceType(getPriceType());
        clone.setPriceToleranceInterval(getPriceToleranceInterval());
        clone.setRsAttachedDate(getRsAttachedDate());
        clone.setItemRebateStartDate(getItemRebateStartDate());
        clone.setOperation(getOperation());
        clone.setCfpModelSid(getCfpModelSid());
        clone.setRsDetailsSid(getRsDetailsSid());
        clone.setAttachedStatus(getAttachedStatus());
        clone.setPrimaryUom(getPrimaryUom());
        clone.setPackageSize(getPackageSize());
        clone.setDeductionCalendarMasterSid(getDeductionCalendarMasterSid());
        clone.setRsContractDetailsDeductionCalendarNo(getRsContractDetailsDeductionCalendarNo());
        clone.setRsContractDetailsDeductionCalendarName(getRsContractDetailsDeductionCalendarName());
        clone.setNetSalesFormulaMasterSid(getNetSalesFormulaMasterSid());
        clone.setRsContractDetailsNetSalesFormulaNo(getRsContractDetailsNetSalesFormulaNo());
        clone.setRsContractDetailsNetSalesFormulaName(getRsContractDetailsNetSalesFormulaName());
        clone.setFormulaType(getFormulaType());
        clone.setNetSalesRule(getNetSalesRule());
        clone.setEvaluationRule(getEvaluationRule());
        clone.setEvaluationRuleBundle(getEvaluationRuleBundle());
        clone.setCalculationRule(getCalculationRule());
        clone.setCalculationRuleBundle(getCalculationRuleBundle());
        clone.setMaxIncrementalChange(getMaxIncrementalChange());
        clone.setResetEligible(getResetEligible());
        clone.setResetType(getResetType());
        clone.setResetDate(getResetDate());
        clone.setResetInterval(getResetInterval());
        clone.setResetFrequency(getResetFrequency());
        clone.setNetPriceType(getNetPriceType());
        clone.setNetPriceTypeFormula(getNetPriceTypeFormula());
        clone.setPriceProtectionPriceType(getPriceProtectionPriceType());
        clone.setNep(getNep());
        clone.setNepFormula(getNepFormula());
        clone.setBrandMasterSid(getBrandMasterSid());
        clone.setPriceProtectionStatus(getPriceProtectionStatus());
        clone.setBasePriceType(getBasePriceType());
        clone.setBasePriceEntry(getBasePriceEntry());
        clone.setBasePriceDate(getBasePriceDate());
        clone.setBasePriceDdlb(getBasePriceDdlb());
        clone.setNetBasePrice(getNetBasePrice());
        clone.setSubsequentPeriodPriceType(getSubsequentPeriodPriceType());
        clone.setResetPriceType(getResetPriceType());
        clone.setNetResetPriceType(getNetResetPriceType());
        clone.setNetResetPriceFormulaId(getNetResetPriceFormulaId());
        clone.setNetBasePriceFormulaId(getNetBasePriceFormulaId());
        clone.setNetSubsequentPriceFormulaId(getNetSubsequentPriceFormulaId());
        clone.setNetSubsequentPeriodPrice(getNetSubsequentPeriodPrice());
        clone.setRsContractDetailsRebatePlanName(getRsContractDetailsRebatePlanName());
        clone.setRsContractDetailsFormulaNo(getRsContractDetailsFormulaNo());
        clone.setSource(getSource());

        return clone;
    }

    @Override
    public int compareTo(ImtdItemPriceRebateDetails imtdItemPriceRebateDetails) {
        int primaryKey = imtdItemPriceRebateDetails.getPrimaryKey();

        if (getPrimaryKey() < primaryKey) {
            return -1;
        } else if (getPrimaryKey() > primaryKey) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof ImtdItemPriceRebateDetailsClp)) {
            return false;
        }

        ImtdItemPriceRebateDetailsClp imtdItemPriceRebateDetails = (ImtdItemPriceRebateDetailsClp) obj;

        int primaryKey = imtdItemPriceRebateDetails.getPrimaryKey();

        if (getPrimaryKey() == primaryKey) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return getPrimaryKey();
    }

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(223);

        sb.append("{formulaMethodId=");
        sb.append(getFormulaMethodId());
        sb.append(", endDate=");
        sb.append(getEndDate());
        sb.append(", priceProtectionStartDate=");
        sb.append(getPriceProtectionStartDate());
        sb.append(", itemPriceRevisionDate=");
        sb.append(getItemPriceRevisionDate());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", rsCheckRecord=");
        sb.append(getRsCheckRecord());
        sb.append(", rebateRevisionDate=");
        sb.append(getRebateRevisionDate());
        sb.append(", contractMasterSid=");
        sb.append(getContractMasterSid());
        sb.append(", imtdCreatedDate=");
        sb.append(getImtdCreatedDate());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", udc6=");
        sb.append(getUdc6());
        sb.append(", udc5=");
        sb.append(getUdc5());
        sb.append(", udc4=");
        sb.append(getUdc4());
        sb.append(", checkRecord=");
        sb.append(getCheckRecord());
        sb.append(", udc1=");
        sb.append(getUdc1());
        sb.append(", udc2=");
        sb.append(getUdc2());
        sb.append(", udc3=");
        sb.append(getUdc3());
        sb.append(", contractPriceEndDate=");
        sb.append(getContractPriceEndDate());
        sb.append(", totalVolumeCommitment=");
        sb.append(getTotalVolumeCommitment());
        sb.append(", priceProtectionEndDate=");
        sb.append(getPriceProtectionEndDate());
        sb.append(", recordLockStatus=");
        sb.append(getRecordLockStatus());
        sb.append(", startDate=");
        sb.append(getStartDate());
        sb.append(", rebateProgramType=");
        sb.append(getRebateProgramType());
        sb.append(", sessionId=");
        sb.append(getSessionId());
        sb.append(", itemName=");
        sb.append(getItemName());
        sb.append(", priceRevision=");
        sb.append(getPriceRevision());
        sb.append(", rsModelSid=");
        sb.append(getRsModelSid());
        sb.append(", price=");
        sb.append(getPrice());
        sb.append(", rsAttachedStatus=");
        sb.append(getRsAttachedStatus());
        sb.append(", itemMasterSid=");
        sb.append(getItemMasterSid());
        sb.append(", totalDollarCommitment=");
        sb.append(getTotalDollarCommitment());
        sb.append(", itemType=");
        sb.append(getItemType());
        sb.append(", totalMarketShareCommitmnet=");
        sb.append(getTotalMarketShareCommitmnet());
        sb.append(", itemId=");
        sb.append(getItemId());
        sb.append(", basePrice=");
        sb.append(getBasePrice());
        sb.append(", bundleNo=");
        sb.append(getBundleNo());
        sb.append(", formulaName=");
        sb.append(getFormulaName());
        sb.append(", psStatus=");
        sb.append(getPsStatus());
        sb.append(", priceTolerance=");
        sb.append(getPriceTolerance());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", createdBy=");
        sb.append(getCreatedBy());
        sb.append(", usersSid=");
        sb.append(getUsersSid());
        sb.append(", psDetailsSid=");
        sb.append(getPsDetailsSid());
        sb.append(", suggestedPrice=");
        sb.append(getSuggestedPrice());
        sb.append(", psModelSid=");
        sb.append(getPsModelSid());
        sb.append(", formulaId=");
        sb.append(getFormulaId());
        sb.append(", commitmentPeriod=");
        sb.append(getCommitmentPeriod());
        sb.append(", itemNo=");
        sb.append(getItemNo());
        sb.append(", contractPrice=");
        sb.append(getContractPrice());
        sb.append(", ifpDetailsSid=");
        sb.append(getIfpDetailsSid());
        sb.append(", ifpModelSid=");
        sb.append(getIfpModelSid());
        sb.append(", priceToleranceType=");
        sb.append(getPriceToleranceType());
        sb.append(", rebateAmount=");
        sb.append(getRebateAmount());
        sb.append(", contractPriceStartDate=");
        sb.append(getContractPriceStartDate());
        sb.append(", rebateScheduleType=");
        sb.append(getRebateScheduleType());
        sb.append(", priceToleranceFrequency=");
        sb.append(getPriceToleranceFrequency());
        sb.append(", imtdItemPriceRebateSid=");
        sb.append(getImtdItemPriceRebateSid());
        sb.append(", rebatePlanSystemId=");
        sb.append(getRebatePlanSystemId());
        sb.append(", attachedDate=");
        sb.append(getAttachedDate());
        sb.append(", pricePlanId=");
        sb.append(getPricePlanId());
        sb.append(", itemRebateEndDate=");
        sb.append(getItemRebateEndDate());
        sb.append(", priceType=");
        sb.append(getPriceType());
        sb.append(", priceToleranceInterval=");
        sb.append(getPriceToleranceInterval());
        sb.append(", rsAttachedDate=");
        sb.append(getRsAttachedDate());
        sb.append(", itemRebateStartDate=");
        sb.append(getItemRebateStartDate());
        sb.append(", operation=");
        sb.append(getOperation());
        sb.append(", cfpModelSid=");
        sb.append(getCfpModelSid());
        sb.append(", rsDetailsSid=");
        sb.append(getRsDetailsSid());
        sb.append(", attachedStatus=");
        sb.append(getAttachedStatus());
        sb.append(", primaryUom=");
        sb.append(getPrimaryUom());
        sb.append(", packageSize=");
        sb.append(getPackageSize());
        sb.append(", deductionCalendarMasterSid=");
        sb.append(getDeductionCalendarMasterSid());
        sb.append(", rsContractDetailsDeductionCalendarNo=");
        sb.append(getRsContractDetailsDeductionCalendarNo());
        sb.append(", rsContractDetailsDeductionCalendarName=");
        sb.append(getRsContractDetailsDeductionCalendarName());
        sb.append(", netSalesFormulaMasterSid=");
        sb.append(getNetSalesFormulaMasterSid());
        sb.append(", rsContractDetailsNetSalesFormulaNo=");
        sb.append(getRsContractDetailsNetSalesFormulaNo());
        sb.append(", rsContractDetailsNetSalesFormulaName=");
        sb.append(getRsContractDetailsNetSalesFormulaName());
        sb.append(", formulaType=");
        sb.append(getFormulaType());
        sb.append(", netSalesRule=");
        sb.append(getNetSalesRule());
        sb.append(", evaluationRule=");
        sb.append(getEvaluationRule());
        sb.append(", evaluationRuleBundle=");
        sb.append(getEvaluationRuleBundle());
        sb.append(", calculationRule=");
        sb.append(getCalculationRule());
        sb.append(", calculationRuleBundle=");
        sb.append(getCalculationRuleBundle());
        sb.append(", maxIncrementalChange=");
        sb.append(getMaxIncrementalChange());
        sb.append(", resetEligible=");
        sb.append(getResetEligible());
        sb.append(", resetType=");
        sb.append(getResetType());
        sb.append(", resetDate=");
        sb.append(getResetDate());
        sb.append(", resetInterval=");
        sb.append(getResetInterval());
        sb.append(", resetFrequency=");
        sb.append(getResetFrequency());
        sb.append(", netPriceType=");
        sb.append(getNetPriceType());
        sb.append(", netPriceTypeFormula=");
        sb.append(getNetPriceTypeFormula());
        sb.append(", priceProtectionPriceType=");
        sb.append(getPriceProtectionPriceType());
        sb.append(", nep=");
        sb.append(getNep());
        sb.append(", nepFormula=");
        sb.append(getNepFormula());
        sb.append(", brandMasterSid=");
        sb.append(getBrandMasterSid());
        sb.append(", priceProtectionStatus=");
        sb.append(getPriceProtectionStatus());
        sb.append(", basePriceType=");
        sb.append(getBasePriceType());
        sb.append(", basePriceEntry=");
        sb.append(getBasePriceEntry());
        sb.append(", basePriceDate=");
        sb.append(getBasePriceDate());
        sb.append(", basePriceDdlb=");
        sb.append(getBasePriceDdlb());
        sb.append(", netBasePrice=");
        sb.append(getNetBasePrice());
        sb.append(", subsequentPeriodPriceType=");
        sb.append(getSubsequentPeriodPriceType());
        sb.append(", resetPriceType=");
        sb.append(getResetPriceType());
        sb.append(", netResetPriceType=");
        sb.append(getNetResetPriceType());
        sb.append(", netResetPriceFormulaId=");
        sb.append(getNetResetPriceFormulaId());
        sb.append(", netBasePriceFormulaId=");
        sb.append(getNetBasePriceFormulaId());
        sb.append(", netSubsequentPriceFormulaId=");
        sb.append(getNetSubsequentPriceFormulaId());
        sb.append(", netSubsequentPeriodPrice=");
        sb.append(getNetSubsequentPeriodPrice());
        sb.append(", rsContractDetailsRebatePlanName=");
        sb.append(getRsContractDetailsRebatePlanName());
        sb.append(", rsContractDetailsFormulaNo=");
        sb.append(getRsContractDetailsFormulaNo());
        sb.append(", source=");
        sb.append(getSource());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(337);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.ImtdItemPriceRebateDetails");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>formulaMethodId</column-name><column-value><![CDATA[");
        sb.append(getFormulaMethodId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>endDate</column-name><column-value><![CDATA[");
        sb.append(getEndDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>priceProtectionStartDate</column-name><column-value><![CDATA[");
        sb.append(getPriceProtectionStartDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemPriceRevisionDate</column-name><column-value><![CDATA[");
        sb.append(getItemPriceRevisionDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
        sb.append(getModifiedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>rsCheckRecord</column-name><column-value><![CDATA[");
        sb.append(getRsCheckRecord());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>rebateRevisionDate</column-name><column-value><![CDATA[");
        sb.append(getRebateRevisionDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>contractMasterSid</column-name><column-value><![CDATA[");
        sb.append(getContractMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>imtdCreatedDate</column-name><column-value><![CDATA[");
        sb.append(getImtdCreatedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedBy</column-name><column-value><![CDATA[");
        sb.append(getModifiedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>udc6</column-name><column-value><![CDATA[");
        sb.append(getUdc6());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>udc5</column-name><column-value><![CDATA[");
        sb.append(getUdc5());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>udc4</column-name><column-value><![CDATA[");
        sb.append(getUdc4());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>checkRecord</column-name><column-value><![CDATA[");
        sb.append(getCheckRecord());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>udc1</column-name><column-value><![CDATA[");
        sb.append(getUdc1());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>udc2</column-name><column-value><![CDATA[");
        sb.append(getUdc2());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>udc3</column-name><column-value><![CDATA[");
        sb.append(getUdc3());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>contractPriceEndDate</column-name><column-value><![CDATA[");
        sb.append(getContractPriceEndDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>totalVolumeCommitment</column-name><column-value><![CDATA[");
        sb.append(getTotalVolumeCommitment());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>priceProtectionEndDate</column-name><column-value><![CDATA[");
        sb.append(getPriceProtectionEndDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>recordLockStatus</column-name><column-value><![CDATA[");
        sb.append(getRecordLockStatus());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>startDate</column-name><column-value><![CDATA[");
        sb.append(getStartDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>rebateProgramType</column-name><column-value><![CDATA[");
        sb.append(getRebateProgramType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>sessionId</column-name><column-value><![CDATA[");
        sb.append(getSessionId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemName</column-name><column-value><![CDATA[");
        sb.append(getItemName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>priceRevision</column-name><column-value><![CDATA[");
        sb.append(getPriceRevision());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>rsModelSid</column-name><column-value><![CDATA[");
        sb.append(getRsModelSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>price</column-name><column-value><![CDATA[");
        sb.append(getPrice());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>rsAttachedStatus</column-name><column-value><![CDATA[");
        sb.append(getRsAttachedStatus());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemMasterSid</column-name><column-value><![CDATA[");
        sb.append(getItemMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>totalDollarCommitment</column-name><column-value><![CDATA[");
        sb.append(getTotalDollarCommitment());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemType</column-name><column-value><![CDATA[");
        sb.append(getItemType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>totalMarketShareCommitmnet</column-name><column-value><![CDATA[");
        sb.append(getTotalMarketShareCommitmnet());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemId</column-name><column-value><![CDATA[");
        sb.append(getItemId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>basePrice</column-name><column-value><![CDATA[");
        sb.append(getBasePrice());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>bundleNo</column-name><column-value><![CDATA[");
        sb.append(getBundleNo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>formulaName</column-name><column-value><![CDATA[");
        sb.append(getFormulaName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>psStatus</column-name><column-value><![CDATA[");
        sb.append(getPsStatus());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>priceTolerance</column-name><column-value><![CDATA[");
        sb.append(getPriceTolerance());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>createdDate</column-name><column-value><![CDATA[");
        sb.append(getCreatedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>createdBy</column-name><column-value><![CDATA[");
        sb.append(getCreatedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>usersSid</column-name><column-value><![CDATA[");
        sb.append(getUsersSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>psDetailsSid</column-name><column-value><![CDATA[");
        sb.append(getPsDetailsSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>suggestedPrice</column-name><column-value><![CDATA[");
        sb.append(getSuggestedPrice());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>psModelSid</column-name><column-value><![CDATA[");
        sb.append(getPsModelSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>formulaId</column-name><column-value><![CDATA[");
        sb.append(getFormulaId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>commitmentPeriod</column-name><column-value><![CDATA[");
        sb.append(getCommitmentPeriod());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemNo</column-name><column-value><![CDATA[");
        sb.append(getItemNo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>contractPrice</column-name><column-value><![CDATA[");
        sb.append(getContractPrice());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>ifpDetailsSid</column-name><column-value><![CDATA[");
        sb.append(getIfpDetailsSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>ifpModelSid</column-name><column-value><![CDATA[");
        sb.append(getIfpModelSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>priceToleranceType</column-name><column-value><![CDATA[");
        sb.append(getPriceToleranceType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>rebateAmount</column-name><column-value><![CDATA[");
        sb.append(getRebateAmount());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>contractPriceStartDate</column-name><column-value><![CDATA[");
        sb.append(getContractPriceStartDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>rebateScheduleType</column-name><column-value><![CDATA[");
        sb.append(getRebateScheduleType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>priceToleranceFrequency</column-name><column-value><![CDATA[");
        sb.append(getPriceToleranceFrequency());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>imtdItemPriceRebateSid</column-name><column-value><![CDATA[");
        sb.append(getImtdItemPriceRebateSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>rebatePlanSystemId</column-name><column-value><![CDATA[");
        sb.append(getRebatePlanSystemId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>attachedDate</column-name><column-value><![CDATA[");
        sb.append(getAttachedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>pricePlanId</column-name><column-value><![CDATA[");
        sb.append(getPricePlanId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemRebateEndDate</column-name><column-value><![CDATA[");
        sb.append(getItemRebateEndDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>priceType</column-name><column-value><![CDATA[");
        sb.append(getPriceType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>priceToleranceInterval</column-name><column-value><![CDATA[");
        sb.append(getPriceToleranceInterval());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>rsAttachedDate</column-name><column-value><![CDATA[");
        sb.append(getRsAttachedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemRebateStartDate</column-name><column-value><![CDATA[");
        sb.append(getItemRebateStartDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>operation</column-name><column-value><![CDATA[");
        sb.append(getOperation());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>cfpModelSid</column-name><column-value><![CDATA[");
        sb.append(getCfpModelSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>rsDetailsSid</column-name><column-value><![CDATA[");
        sb.append(getRsDetailsSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>attachedStatus</column-name><column-value><![CDATA[");
        sb.append(getAttachedStatus());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>primaryUom</column-name><column-value><![CDATA[");
        sb.append(getPrimaryUom());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>packageSize</column-name><column-value><![CDATA[");
        sb.append(getPackageSize());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>deductionCalendarMasterSid</column-name><column-value><![CDATA[");
        sb.append(getDeductionCalendarMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>rsContractDetailsDeductionCalendarNo</column-name><column-value><![CDATA[");
        sb.append(getRsContractDetailsDeductionCalendarNo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>rsContractDetailsDeductionCalendarName</column-name><column-value><![CDATA[");
        sb.append(getRsContractDetailsDeductionCalendarName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>netSalesFormulaMasterSid</column-name><column-value><![CDATA[");
        sb.append(getNetSalesFormulaMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>rsContractDetailsNetSalesFormulaNo</column-name><column-value><![CDATA[");
        sb.append(getRsContractDetailsNetSalesFormulaNo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>rsContractDetailsNetSalesFormulaName</column-name><column-value><![CDATA[");
        sb.append(getRsContractDetailsNetSalesFormulaName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>formulaType</column-name><column-value><![CDATA[");
        sb.append(getFormulaType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>netSalesRule</column-name><column-value><![CDATA[");
        sb.append(getNetSalesRule());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>evaluationRule</column-name><column-value><![CDATA[");
        sb.append(getEvaluationRule());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>evaluationRuleBundle</column-name><column-value><![CDATA[");
        sb.append(getEvaluationRuleBundle());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>calculationRule</column-name><column-value><![CDATA[");
        sb.append(getCalculationRule());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>calculationRuleBundle</column-name><column-value><![CDATA[");
        sb.append(getCalculationRuleBundle());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>maxIncrementalChange</column-name><column-value><![CDATA[");
        sb.append(getMaxIncrementalChange());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>resetEligible</column-name><column-value><![CDATA[");
        sb.append(getResetEligible());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>resetType</column-name><column-value><![CDATA[");
        sb.append(getResetType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>resetDate</column-name><column-value><![CDATA[");
        sb.append(getResetDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>resetInterval</column-name><column-value><![CDATA[");
        sb.append(getResetInterval());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>resetFrequency</column-name><column-value><![CDATA[");
        sb.append(getResetFrequency());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>netPriceType</column-name><column-value><![CDATA[");
        sb.append(getNetPriceType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>netPriceTypeFormula</column-name><column-value><![CDATA[");
        sb.append(getNetPriceTypeFormula());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>priceProtectionPriceType</column-name><column-value><![CDATA[");
        sb.append(getPriceProtectionPriceType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>nep</column-name><column-value><![CDATA[");
        sb.append(getNep());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>nepFormula</column-name><column-value><![CDATA[");
        sb.append(getNepFormula());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>brandMasterSid</column-name><column-value><![CDATA[");
        sb.append(getBrandMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>priceProtectionStatus</column-name><column-value><![CDATA[");
        sb.append(getPriceProtectionStatus());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>basePriceType</column-name><column-value><![CDATA[");
        sb.append(getBasePriceType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>basePriceEntry</column-name><column-value><![CDATA[");
        sb.append(getBasePriceEntry());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>basePriceDate</column-name><column-value><![CDATA[");
        sb.append(getBasePriceDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>basePriceDdlb</column-name><column-value><![CDATA[");
        sb.append(getBasePriceDdlb());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>netBasePrice</column-name><column-value><![CDATA[");
        sb.append(getNetBasePrice());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>subsequentPeriodPriceType</column-name><column-value><![CDATA[");
        sb.append(getSubsequentPeriodPriceType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>resetPriceType</column-name><column-value><![CDATA[");
        sb.append(getResetPriceType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>netResetPriceType</column-name><column-value><![CDATA[");
        sb.append(getNetResetPriceType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>netResetPriceFormulaId</column-name><column-value><![CDATA[");
        sb.append(getNetResetPriceFormulaId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>netBasePriceFormulaId</column-name><column-value><![CDATA[");
        sb.append(getNetBasePriceFormulaId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>netSubsequentPriceFormulaId</column-name><column-value><![CDATA[");
        sb.append(getNetSubsequentPriceFormulaId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>netSubsequentPeriodPrice</column-name><column-value><![CDATA[");
        sb.append(getNetSubsequentPeriodPrice());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>rsContractDetailsRebatePlanName</column-name><column-value><![CDATA[");
        sb.append(getRsContractDetailsRebatePlanName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>rsContractDetailsFormulaNo</column-name><column-value><![CDATA[");
        sb.append(getRsContractDetailsFormulaNo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>source</column-name><column-value><![CDATA[");
        sb.append(getSource());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
