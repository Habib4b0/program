package com.stpl.app.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author
 * @generated
 */
public class ImtdItemPriceRebateDetailsSoap implements Serializable {
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

    public ImtdItemPriceRebateDetailsSoap() {
    }

    public static ImtdItemPriceRebateDetailsSoap toSoapModel(
        ImtdItemPriceRebateDetails model) {
        ImtdItemPriceRebateDetailsSoap soapModel = new ImtdItemPriceRebateDetailsSoap();

        soapModel.setFormulaMethodId(model.getFormulaMethodId());
        soapModel.setEndDate(model.getEndDate());
        soapModel.setPriceProtectionStartDate(model.getPriceProtectionStartDate());
        soapModel.setItemPriceRevisionDate(model.getItemPriceRevisionDate());
        soapModel.setModifiedDate(model.getModifiedDate());
        soapModel.setRsCheckRecord(model.getRsCheckRecord());
        soapModel.setRebateRevisionDate(model.getRebateRevisionDate());
        soapModel.setContractMasterSid(model.getContractMasterSid());
        soapModel.setImtdCreatedDate(model.getImtdCreatedDate());
        soapModel.setModifiedBy(model.getModifiedBy());
        soapModel.setUdc6(model.getUdc6());
        soapModel.setUdc5(model.getUdc5());
        soapModel.setUdc4(model.getUdc4());
        soapModel.setCheckRecord(model.getCheckRecord());
        soapModel.setUdc1(model.getUdc1());
        soapModel.setUdc2(model.getUdc2());
        soapModel.setUdc3(model.getUdc3());
        soapModel.setContractPriceEndDate(model.getContractPriceEndDate());
        soapModel.setTotalVolumeCommitment(model.getTotalVolumeCommitment());
        soapModel.setPriceProtectionEndDate(model.getPriceProtectionEndDate());
        soapModel.setRecordLockStatus(model.getRecordLockStatus());
        soapModel.setStartDate(model.getStartDate());
        soapModel.setRebateProgramType(model.getRebateProgramType());
        soapModel.setSessionId(model.getSessionId());
        soapModel.setItemName(model.getItemName());
        soapModel.setPriceRevision(model.getPriceRevision());
        soapModel.setRsModelSid(model.getRsModelSid());
        soapModel.setPrice(model.getPrice());
        soapModel.setRsAttachedStatus(model.getRsAttachedStatus());
        soapModel.setItemMasterSid(model.getItemMasterSid());
        soapModel.setTotalDollarCommitment(model.getTotalDollarCommitment());
        soapModel.setItemType(model.getItemType());
        soapModel.setTotalMarketShareCommitmnet(model.getTotalMarketShareCommitmnet());
        soapModel.setItemId(model.getItemId());
        soapModel.setBasePrice(model.getBasePrice());
        soapModel.setBundleNo(model.getBundleNo());
        soapModel.setFormulaName(model.getFormulaName());
        soapModel.setPsStatus(model.getPsStatus());
        soapModel.setPriceTolerance(model.getPriceTolerance());
        soapModel.setCreatedDate(model.getCreatedDate());
        soapModel.setCreatedBy(model.getCreatedBy());
        soapModel.setUsersSid(model.getUsersSid());
        soapModel.setPsDetailsSid(model.getPsDetailsSid());
        soapModel.setSuggestedPrice(model.getSuggestedPrice());
        soapModel.setPsModelSid(model.getPsModelSid());
        soapModel.setFormulaId(model.getFormulaId());
        soapModel.setCommitmentPeriod(model.getCommitmentPeriod());
        soapModel.setItemNo(model.getItemNo());
        soapModel.setContractPrice(model.getContractPrice());
        soapModel.setIfpDetailsSid(model.getIfpDetailsSid());
        soapModel.setIfpModelSid(model.getIfpModelSid());
        soapModel.setPriceToleranceType(model.getPriceToleranceType());
        soapModel.setRebateAmount(model.getRebateAmount());
        soapModel.setContractPriceStartDate(model.getContractPriceStartDate());
        soapModel.setRebateScheduleType(model.getRebateScheduleType());
        soapModel.setPriceToleranceFrequency(model.getPriceToleranceFrequency());
        soapModel.setImtdItemPriceRebateSid(model.getImtdItemPriceRebateSid());
        soapModel.setRebatePlanSystemId(model.getRebatePlanSystemId());
        soapModel.setAttachedDate(model.getAttachedDate());
        soapModel.setPricePlanId(model.getPricePlanId());
        soapModel.setItemRebateEndDate(model.getItemRebateEndDate());
        soapModel.setPriceType(model.getPriceType());
        soapModel.setPriceToleranceInterval(model.getPriceToleranceInterval());
        soapModel.setRsAttachedDate(model.getRsAttachedDate());
        soapModel.setItemRebateStartDate(model.getItemRebateStartDate());
        soapModel.setOperation(model.getOperation());
        soapModel.setCfpModelSid(model.getCfpModelSid());
        soapModel.setRsDetailsSid(model.getRsDetailsSid());
        soapModel.setAttachedStatus(model.getAttachedStatus());
        soapModel.setPrimaryUom(model.getPrimaryUom());
        soapModel.setPackageSize(model.getPackageSize());
        soapModel.setDeductionCalendarMasterSid(model.getDeductionCalendarMasterSid());
        soapModel.setRsContractDetailsDeductionCalendarNo(model.getRsContractDetailsDeductionCalendarNo());
        soapModel.setRsContractDetailsDeductionCalendarName(model.getRsContractDetailsDeductionCalendarName());
        soapModel.setNetSalesFormulaMasterSid(model.getNetSalesFormulaMasterSid());
        soapModel.setRsContractDetailsNetSalesFormulaNo(model.getRsContractDetailsNetSalesFormulaNo());
        soapModel.setRsContractDetailsNetSalesFormulaName(model.getRsContractDetailsNetSalesFormulaName());
        soapModel.setFormulaType(model.getFormulaType());
        soapModel.setNetSalesRule(model.getNetSalesRule());
        soapModel.setEvaluationRule(model.getEvaluationRule());
        soapModel.setEvaluationRuleBundle(model.getEvaluationRuleBundle());
        soapModel.setCalculationRule(model.getCalculationRule());
        soapModel.setCalculationRuleBundle(model.getCalculationRuleBundle());
        soapModel.setMaxIncrementalChange(model.getMaxIncrementalChange());
        soapModel.setResetEligible(model.getResetEligible());
        soapModel.setResetType(model.getResetType());
        soapModel.setResetDate(model.getResetDate());
        soapModel.setResetInterval(model.getResetInterval());
        soapModel.setResetFrequency(model.getResetFrequency());
        soapModel.setNetPriceType(model.getNetPriceType());
        soapModel.setNetPriceTypeFormula(model.getNetPriceTypeFormula());
        soapModel.setPriceProtectionPriceType(model.getPriceProtectionPriceType());
        soapModel.setNep(model.getNep());
        soapModel.setNepFormula(model.getNepFormula());
        soapModel.setBrandMasterSid(model.getBrandMasterSid());
        soapModel.setPriceProtectionStatus(model.getPriceProtectionStatus());
        soapModel.setBasePriceType(model.getBasePriceType());
        soapModel.setBasePriceEntry(model.getBasePriceEntry());
        soapModel.setBasePriceDate(model.getBasePriceDate());
        soapModel.setBasePriceDdlb(model.getBasePriceDdlb());
        soapModel.setNetBasePrice(model.getNetBasePrice());
        soapModel.setSubsequentPeriodPriceType(model.getSubsequentPeriodPriceType());
        soapModel.setResetPriceType(model.getResetPriceType());
        soapModel.setNetResetPriceType(model.getNetResetPriceType());
        soapModel.setNetResetPriceFormulaId(model.getNetResetPriceFormulaId());
        soapModel.setNetBasePriceFormulaId(model.getNetBasePriceFormulaId());
        soapModel.setNetSubsequentPriceFormulaId(model.getNetSubsequentPriceFormulaId());
        soapModel.setNetSubsequentPeriodPrice(model.getNetSubsequentPeriodPrice());
        soapModel.setRsContractDetailsRebatePlanName(model.getRsContractDetailsRebatePlanName());
        soapModel.setRsContractDetailsFormulaNo(model.getRsContractDetailsFormulaNo());
        soapModel.setSource(model.getSource());

        return soapModel;
    }

    public static ImtdItemPriceRebateDetailsSoap[] toSoapModels(
        ImtdItemPriceRebateDetails[] models) {
        ImtdItemPriceRebateDetailsSoap[] soapModels = new ImtdItemPriceRebateDetailsSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static ImtdItemPriceRebateDetailsSoap[][] toSoapModels(
        ImtdItemPriceRebateDetails[][] models) {
        ImtdItemPriceRebateDetailsSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new ImtdItemPriceRebateDetailsSoap[models.length][models[0].length];
        } else {
            soapModels = new ImtdItemPriceRebateDetailsSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static ImtdItemPriceRebateDetailsSoap[] toSoapModels(
        List<ImtdItemPriceRebateDetails> models) {
        List<ImtdItemPriceRebateDetailsSoap> soapModels = new ArrayList<ImtdItemPriceRebateDetailsSoap>(models.size());

        for (ImtdItemPriceRebateDetails model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new ImtdItemPriceRebateDetailsSoap[soapModels.size()]);
    }

    public int getPrimaryKey() {
        return _imtdItemPriceRebateSid;
    }

    public void setPrimaryKey(int pk) {
        setImtdItemPriceRebateSid(pk);
    }

    public String getFormulaMethodId() {
        return _formulaMethodId;
    }

    public void setFormulaMethodId(String formulaMethodId) {
        _formulaMethodId = formulaMethodId;
    }

    public Date getEndDate() {
        return _endDate;
    }

    public void setEndDate(Date endDate) {
        _endDate = endDate;
    }

    public Date getPriceProtectionStartDate() {
        return _priceProtectionStartDate;
    }

    public void setPriceProtectionStartDate(Date priceProtectionStartDate) {
        _priceProtectionStartDate = priceProtectionStartDate;
    }

    public Date getItemPriceRevisionDate() {
        return _itemPriceRevisionDate;
    }

    public void setItemPriceRevisionDate(Date itemPriceRevisionDate) {
        _itemPriceRevisionDate = itemPriceRevisionDate;
    }

    public Date getModifiedDate() {
        return _modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        _modifiedDate = modifiedDate;
    }

    public boolean getRsCheckRecord() {
        return _rsCheckRecord;
    }

    public boolean isRsCheckRecord() {
        return _rsCheckRecord;
    }

    public void setRsCheckRecord(boolean rsCheckRecord) {
        _rsCheckRecord = rsCheckRecord;
    }

    public Date getRebateRevisionDate() {
        return _rebateRevisionDate;
    }

    public void setRebateRevisionDate(Date rebateRevisionDate) {
        _rebateRevisionDate = rebateRevisionDate;
    }

    public int getContractMasterSid() {
        return _contractMasterSid;
    }

    public void setContractMasterSid(int contractMasterSid) {
        _contractMasterSid = contractMasterSid;
    }

    public Date getImtdCreatedDate() {
        return _imtdCreatedDate;
    }

    public void setImtdCreatedDate(Date imtdCreatedDate) {
        _imtdCreatedDate = imtdCreatedDate;
    }

    public int getModifiedBy() {
        return _modifiedBy;
    }

    public void setModifiedBy(int modifiedBy) {
        _modifiedBy = modifiedBy;
    }

    public int getUdc6() {
        return _udc6;
    }

    public void setUdc6(int udc6) {
        _udc6 = udc6;
    }

    public int getUdc5() {
        return _udc5;
    }

    public void setUdc5(int udc5) {
        _udc5 = udc5;
    }

    public int getUdc4() {
        return _udc4;
    }

    public void setUdc4(int udc4) {
        _udc4 = udc4;
    }

    public boolean getCheckRecord() {
        return _checkRecord;
    }

    public boolean isCheckRecord() {
        return _checkRecord;
    }

    public void setCheckRecord(boolean checkRecord) {
        _checkRecord = checkRecord;
    }

    public int getUdc1() {
        return _udc1;
    }

    public void setUdc1(int udc1) {
        _udc1 = udc1;
    }

    public int getUdc2() {
        return _udc2;
    }

    public void setUdc2(int udc2) {
        _udc2 = udc2;
    }

    public int getUdc3() {
        return _udc3;
    }

    public void setUdc3(int udc3) {
        _udc3 = udc3;
    }

    public Date getContractPriceEndDate() {
        return _contractPriceEndDate;
    }

    public void setContractPriceEndDate(Date contractPriceEndDate) {
        _contractPriceEndDate = contractPriceEndDate;
    }

    public double getTotalVolumeCommitment() {
        return _totalVolumeCommitment;
    }

    public void setTotalVolumeCommitment(double totalVolumeCommitment) {
        _totalVolumeCommitment = totalVolumeCommitment;
    }

    public Date getPriceProtectionEndDate() {
        return _priceProtectionEndDate;
    }

    public void setPriceProtectionEndDate(Date priceProtectionEndDate) {
        _priceProtectionEndDate = priceProtectionEndDate;
    }

    public String getRecordLockStatus() {
        return _recordLockStatus;
    }

    public void setRecordLockStatus(String recordLockStatus) {
        _recordLockStatus = recordLockStatus;
    }

    public Date getStartDate() {
        return _startDate;
    }

    public void setStartDate(Date startDate) {
        _startDate = startDate;
    }

    public int getRebateProgramType() {
        return _rebateProgramType;
    }

    public void setRebateProgramType(int rebateProgramType) {
        _rebateProgramType = rebateProgramType;
    }

    public String getSessionId() {
        return _sessionId;
    }

    public void setSessionId(String sessionId) {
        _sessionId = sessionId;
    }

    public String getItemName() {
        return _itemName;
    }

    public void setItemName(String itemName) {
        _itemName = itemName;
    }

    public double getPriceRevision() {
        return _priceRevision;
    }

    public void setPriceRevision(double priceRevision) {
        _priceRevision = priceRevision;
    }

    public int getRsModelSid() {
        return _rsModelSid;
    }

    public void setRsModelSid(int rsModelSid) {
        _rsModelSid = rsModelSid;
    }

    public double getPrice() {
        return _price;
    }

    public void setPrice(double price) {
        _price = price;
    }

    public int getRsAttachedStatus() {
        return _rsAttachedStatus;
    }

    public void setRsAttachedStatus(int rsAttachedStatus) {
        _rsAttachedStatus = rsAttachedStatus;
    }

    public int getItemMasterSid() {
        return _itemMasterSid;
    }

    public void setItemMasterSid(int itemMasterSid) {
        _itemMasterSid = itemMasterSid;
    }

    public double getTotalDollarCommitment() {
        return _totalDollarCommitment;
    }

    public void setTotalDollarCommitment(double totalDollarCommitment) {
        _totalDollarCommitment = totalDollarCommitment;
    }

    public int getItemType() {
        return _itemType;
    }

    public void setItemType(int itemType) {
        _itemType = itemType;
    }

    public double getTotalMarketShareCommitmnet() {
        return _totalMarketShareCommitmnet;
    }

    public void setTotalMarketShareCommitmnet(double totalMarketShareCommitmnet) {
        _totalMarketShareCommitmnet = totalMarketShareCommitmnet;
    }

    public String getItemId() {
        return _itemId;
    }

    public void setItemId(String itemId) {
        _itemId = itemId;
    }

    public double getBasePrice() {
        return _basePrice;
    }

    public void setBasePrice(double basePrice) {
        _basePrice = basePrice;
    }

    public String getBundleNo() {
        return _bundleNo;
    }

    public void setBundleNo(String bundleNo) {
        _bundleNo = bundleNo;
    }

    public String getFormulaName() {
        return _formulaName;
    }

    public void setFormulaName(String formulaName) {
        _formulaName = formulaName;
    }

    public int getPsStatus() {
        return _psStatus;
    }

    public void setPsStatus(int psStatus) {
        _psStatus = psStatus;
    }

    public double getPriceTolerance() {
        return _priceTolerance;
    }

    public void setPriceTolerance(double priceTolerance) {
        _priceTolerance = priceTolerance;
    }

    public Date getCreatedDate() {
        return _createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        _createdDate = createdDate;
    }

    public int getCreatedBy() {
        return _createdBy;
    }

    public void setCreatedBy(int createdBy) {
        _createdBy = createdBy;
    }

    public int getUsersSid() {
        return _usersSid;
    }

    public void setUsersSid(int usersSid) {
        _usersSid = usersSid;
    }

    public int getPsDetailsSid() {
        return _psDetailsSid;
    }

    public void setPsDetailsSid(int psDetailsSid) {
        _psDetailsSid = psDetailsSid;
    }

    public double getSuggestedPrice() {
        return _suggestedPrice;
    }

    public void setSuggestedPrice(double suggestedPrice) {
        _suggestedPrice = suggestedPrice;
    }

    public int getPsModelSid() {
        return _psModelSid;
    }

    public void setPsModelSid(int psModelSid) {
        _psModelSid = psModelSid;
    }

    public String getFormulaId() {
        return _formulaId;
    }

    public void setFormulaId(String formulaId) {
        _formulaId = formulaId;
    }

    public String getCommitmentPeriod() {
        return _commitmentPeriod;
    }

    public void setCommitmentPeriod(String commitmentPeriod) {
        _commitmentPeriod = commitmentPeriod;
    }

    public String getItemNo() {
        return _itemNo;
    }

    public void setItemNo(String itemNo) {
        _itemNo = itemNo;
    }

    public double getContractPrice() {
        return _contractPrice;
    }

    public void setContractPrice(double contractPrice) {
        _contractPrice = contractPrice;
    }

    public int getIfpDetailsSid() {
        return _ifpDetailsSid;
    }

    public void setIfpDetailsSid(int ifpDetailsSid) {
        _ifpDetailsSid = ifpDetailsSid;
    }

    public int getIfpModelSid() {
        return _ifpModelSid;
    }

    public void setIfpModelSid(int ifpModelSid) {
        _ifpModelSid = ifpModelSid;
    }

    public String getPriceToleranceType() {
        return _priceToleranceType;
    }

    public void setPriceToleranceType(String priceToleranceType) {
        _priceToleranceType = priceToleranceType;
    }

    public double getRebateAmount() {
        return _rebateAmount;
    }

    public void setRebateAmount(double rebateAmount) {
        _rebateAmount = rebateAmount;
    }

    public Date getContractPriceStartDate() {
        return _contractPriceStartDate;
    }

    public void setContractPriceStartDate(Date contractPriceStartDate) {
        _contractPriceStartDate = contractPriceStartDate;
    }

    public String getRebateScheduleType() {
        return _rebateScheduleType;
    }

    public void setRebateScheduleType(String rebateScheduleType) {
        _rebateScheduleType = rebateScheduleType;
    }

    public String getPriceToleranceFrequency() {
        return _priceToleranceFrequency;
    }

    public void setPriceToleranceFrequency(String priceToleranceFrequency) {
        _priceToleranceFrequency = priceToleranceFrequency;
    }

    public int getImtdItemPriceRebateSid() {
        return _imtdItemPriceRebateSid;
    }

    public void setImtdItemPriceRebateSid(int imtdItemPriceRebateSid) {
        _imtdItemPriceRebateSid = imtdItemPriceRebateSid;
    }

    public String getRebatePlanSystemId() {
        return _rebatePlanSystemId;
    }

    public void setRebatePlanSystemId(String rebatePlanSystemId) {
        _rebatePlanSystemId = rebatePlanSystemId;
    }

    public Date getAttachedDate() {
        return _attachedDate;
    }

    public void setAttachedDate(Date attachedDate) {
        _attachedDate = attachedDate;
    }

    public String getPricePlanId() {
        return _pricePlanId;
    }

    public void setPricePlanId(String pricePlanId) {
        _pricePlanId = pricePlanId;
    }

    public Date getItemRebateEndDate() {
        return _itemRebateEndDate;
    }

    public void setItemRebateEndDate(Date itemRebateEndDate) {
        _itemRebateEndDate = itemRebateEndDate;
    }

    public int getPriceType() {
        return _priceType;
    }

    public void setPriceType(int priceType) {
        _priceType = priceType;
    }

    public int getPriceToleranceInterval() {
        return _priceToleranceInterval;
    }

    public void setPriceToleranceInterval(int priceToleranceInterval) {
        _priceToleranceInterval = priceToleranceInterval;
    }

    public Date getRsAttachedDate() {
        return _rsAttachedDate;
    }

    public void setRsAttachedDate(Date rsAttachedDate) {
        _rsAttachedDate = rsAttachedDate;
    }

    public Date getItemRebateStartDate() {
        return _itemRebateStartDate;
    }

    public void setItemRebateStartDate(Date itemRebateStartDate) {
        _itemRebateStartDate = itemRebateStartDate;
    }

    public String getOperation() {
        return _operation;
    }

    public void setOperation(String operation) {
        _operation = operation;
    }

    public int getCfpModelSid() {
        return _cfpModelSid;
    }

    public void setCfpModelSid(int cfpModelSid) {
        _cfpModelSid = cfpModelSid;
    }

    public int getRsDetailsSid() {
        return _rsDetailsSid;
    }

    public void setRsDetailsSid(int rsDetailsSid) {
        _rsDetailsSid = rsDetailsSid;
    }

    public int getAttachedStatus() {
        return _attachedStatus;
    }

    public void setAttachedStatus(int attachedStatus) {
        _attachedStatus = attachedStatus;
    }

    public int getPrimaryUom() {
        return _primaryUom;
    }

    public void setPrimaryUom(int primaryUom) {
        _primaryUom = primaryUom;
    }

    public String getPackageSize() {
        return _packageSize;
    }

    public void setPackageSize(String packageSize) {
        _packageSize = packageSize;
    }

    public String getDeductionCalendarMasterSid() {
        return _deductionCalendarMasterSid;
    }

    public void setDeductionCalendarMasterSid(String deductionCalendarMasterSid) {
        _deductionCalendarMasterSid = deductionCalendarMasterSid;
    }

    public String getRsContractDetailsDeductionCalendarNo() {
        return _rsContractDetailsDeductionCalendarNo;
    }

    public void setRsContractDetailsDeductionCalendarNo(
        String rsContractDetailsDeductionCalendarNo) {
        _rsContractDetailsDeductionCalendarNo = rsContractDetailsDeductionCalendarNo;
    }

    public String getRsContractDetailsDeductionCalendarName() {
        return _rsContractDetailsDeductionCalendarName;
    }

    public void setRsContractDetailsDeductionCalendarName(
        String rsContractDetailsDeductionCalendarName) {
        _rsContractDetailsDeductionCalendarName = rsContractDetailsDeductionCalendarName;
    }

    public String getNetSalesFormulaMasterSid() {
        return _netSalesFormulaMasterSid;
    }

    public void setNetSalesFormulaMasterSid(String netSalesFormulaMasterSid) {
        _netSalesFormulaMasterSid = netSalesFormulaMasterSid;
    }

    public String getRsContractDetailsNetSalesFormulaNo() {
        return _rsContractDetailsNetSalesFormulaNo;
    }

    public void setRsContractDetailsNetSalesFormulaNo(
        String rsContractDetailsNetSalesFormulaNo) {
        _rsContractDetailsNetSalesFormulaNo = rsContractDetailsNetSalesFormulaNo;
    }

    public String getRsContractDetailsNetSalesFormulaName() {
        return _rsContractDetailsNetSalesFormulaName;
    }

    public void setRsContractDetailsNetSalesFormulaName(
        String rsContractDetailsNetSalesFormulaName) {
        _rsContractDetailsNetSalesFormulaName = rsContractDetailsNetSalesFormulaName;
    }

    public int getFormulaType() {
        return _formulaType;
    }

    public void setFormulaType(int formulaType) {
        _formulaType = formulaType;
    }

    public int getNetSalesRule() {
        return _netSalesRule;
    }

    public void setNetSalesRule(int netSalesRule) {
        _netSalesRule = netSalesRule;
    }

    public int getEvaluationRule() {
        return _evaluationRule;
    }

    public void setEvaluationRule(int evaluationRule) {
        _evaluationRule = evaluationRule;
    }

    public String getEvaluationRuleBundle() {
        return _evaluationRuleBundle;
    }

    public void setEvaluationRuleBundle(String evaluationRuleBundle) {
        _evaluationRuleBundle = evaluationRuleBundle;
    }

    public int getCalculationRule() {
        return _calculationRule;
    }

    public void setCalculationRule(int calculationRule) {
        _calculationRule = calculationRule;
    }

    public String getCalculationRuleBundle() {
        return _calculationRuleBundle;
    }

    public void setCalculationRuleBundle(String calculationRuleBundle) {
        _calculationRuleBundle = calculationRuleBundle;
    }

    public double getMaxIncrementalChange() {
        return _maxIncrementalChange;
    }

    public void setMaxIncrementalChange(double maxIncrementalChange) {
        _maxIncrementalChange = maxIncrementalChange;
    }

    public int getResetEligible() {
        return _resetEligible;
    }

    public void setResetEligible(int resetEligible) {
        _resetEligible = resetEligible;
    }

    public int getResetType() {
        return _resetType;
    }

    public void setResetType(int resetType) {
        _resetType = resetType;
    }

    public Date getResetDate() {
        return _resetDate;
    }

    public void setResetDate(Date resetDate) {
        _resetDate = resetDate;
    }

    public int getResetInterval() {
        return _resetInterval;
    }

    public void setResetInterval(int resetInterval) {
        _resetInterval = resetInterval;
    }

    public int getResetFrequency() {
        return _resetFrequency;
    }

    public void setResetFrequency(int resetFrequency) {
        _resetFrequency = resetFrequency;
    }

    public int getNetPriceType() {
        return _netPriceType;
    }

    public void setNetPriceType(int netPriceType) {
        _netPriceType = netPriceType;
    }

    public String getNetPriceTypeFormula() {
        return _netPriceTypeFormula;
    }

    public void setNetPriceTypeFormula(String netPriceTypeFormula) {
        _netPriceTypeFormula = netPriceTypeFormula;
    }

    public int getPriceProtectionPriceType() {
        return _priceProtectionPriceType;
    }

    public void setPriceProtectionPriceType(int priceProtectionPriceType) {
        _priceProtectionPriceType = priceProtectionPriceType;
    }

    public double getNep() {
        return _nep;
    }

    public void setNep(double nep) {
        _nep = nep;
    }

    public int getNepFormula() {
        return _nepFormula;
    }

    public void setNepFormula(int nepFormula) {
        _nepFormula = nepFormula;
    }

    public String getBrandMasterSid() {
        return _brandMasterSid;
    }

    public void setBrandMasterSid(String brandMasterSid) {
        _brandMasterSid = brandMasterSid;
    }

    public int getPriceProtectionStatus() {
        return _priceProtectionStatus;
    }

    public void setPriceProtectionStatus(int priceProtectionStatus) {
        _priceProtectionStatus = priceProtectionStatus;
    }

    public int getBasePriceType() {
        return _basePriceType;
    }

    public void setBasePriceType(int basePriceType) {
        _basePriceType = basePriceType;
    }

    public double getBasePriceEntry() {
        return _basePriceEntry;
    }

    public void setBasePriceEntry(double basePriceEntry) {
        _basePriceEntry = basePriceEntry;
    }

    public Date getBasePriceDate() {
        return _basePriceDate;
    }

    public void setBasePriceDate(Date basePriceDate) {
        _basePriceDate = basePriceDate;
    }

    public int getBasePriceDdlb() {
        return _basePriceDdlb;
    }

    public void setBasePriceDdlb(int basePriceDdlb) {
        _basePriceDdlb = basePriceDdlb;
    }

    public int getNetBasePrice() {
        return _netBasePrice;
    }

    public void setNetBasePrice(int netBasePrice) {
        _netBasePrice = netBasePrice;
    }

    public int getSubsequentPeriodPriceType() {
        return _subsequentPeriodPriceType;
    }

    public void setSubsequentPeriodPriceType(int subsequentPeriodPriceType) {
        _subsequentPeriodPriceType = subsequentPeriodPriceType;
    }

    public int getResetPriceType() {
        return _resetPriceType;
    }

    public void setResetPriceType(int resetPriceType) {
        _resetPriceType = resetPriceType;
    }

    public int getNetResetPriceType() {
        return _netResetPriceType;
    }

    public void setNetResetPriceType(int netResetPriceType) {
        _netResetPriceType = netResetPriceType;
    }

    public int getNetResetPriceFormulaId() {
        return _netResetPriceFormulaId;
    }

    public void setNetResetPriceFormulaId(int netResetPriceFormulaId) {
        _netResetPriceFormulaId = netResetPriceFormulaId;
    }

    public int getNetBasePriceFormulaId() {
        return _netBasePriceFormulaId;
    }

    public void setNetBasePriceFormulaId(int netBasePriceFormulaId) {
        _netBasePriceFormulaId = netBasePriceFormulaId;
    }

    public int getNetSubsequentPriceFormulaId() {
        return _netSubsequentPriceFormulaId;
    }

    public void setNetSubsequentPriceFormulaId(int netSubsequentPriceFormulaId) {
        _netSubsequentPriceFormulaId = netSubsequentPriceFormulaId;
    }

    public int getNetSubsequentPeriodPrice() {
        return _netSubsequentPeriodPrice;
    }

    public void setNetSubsequentPeriodPrice(int netSubsequentPeriodPrice) {
        _netSubsequentPeriodPrice = netSubsequentPeriodPrice;
    }

    public String getRsContractDetailsRebatePlanName() {
        return _rsContractDetailsRebatePlanName;
    }

    public void setRsContractDetailsRebatePlanName(
        String rsContractDetailsRebatePlanName) {
        _rsContractDetailsRebatePlanName = rsContractDetailsRebatePlanName;
    }

    public String getRsContractDetailsFormulaNo() {
        return _rsContractDetailsFormulaNo;
    }

    public void setRsContractDetailsFormulaNo(String rsContractDetailsFormulaNo) {
        _rsContractDetailsFormulaNo = rsContractDetailsFormulaNo;
    }

    public String getSource() {
        return _source;
    }

    public void setSource(String source) {
        _source = source;
    }
}
