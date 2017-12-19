package com.stpl.gtn.gtn2o.ws.entity.transaction;


import java.util.Date;

@SuppressWarnings("serial")
public class IvldRebateSchedule  implements java.io.Serializable {


     private int ivldRebateScheduleSid;
     private String rebateScheduleIntfid;
     private String rebateScheduleId;
     private String rebateScheduleNo;
     private String rebateScheduleName;
     private String rebateScheduleType;
     private String rebateProgramType;
     private String rebateScheduleCategory;
     private String tradeClass;
     private String rebateScheduleDesignation;
     private String parentRebateScheduleId;
     private String parentRebateScheduleName;
     private String rebateScheduleStatus;
     private String rebateScheduleTransRefId;
     private String rebateScheduleTransRefNo;
     private String rebateScheduleTransRefName;
     private String paymentMethod;
     private String paymentFrequency;
     private String paymentTerms;
     private String rebateFrequency;
     private String calendar;
     private String rebateProcessingType;
     private String validationProfile;
     private String rebateRuleType;
     private String rebateRuleAssociation;
     private String rebatePlanLevel;
     private String interestBearingIndicator;
     private String interestBearingBasis;
     private String paymentGracePeriod;
     private String makePayableTo;
     private String address1;
     private String address2;
     private String city;
     private String state;
     private String zipCode;
     private String itemId;
     private String identifierCodeQualifier;
     private String itemNo;
     private String itemName;
     private String attachedStatus;
     private String attachedDate;
     private String itemStartDate;
     private String itemEndDate;
     private String formulaType;
     private String formulaId;
     private String formulaNo;
     private String formulaName;
     private String rebatePlanId;
     private String rebateAmount;
     private String itemRebateStartDate;
     private String itemRebateEndDate;
     private String bundleNo;
     private String internalNotes;
     private String contractId;
     private String cfpId;
     private String mfpId;
     private String ifpId;
     private String priceScheduleId;
     private String udc1;
     private String udc2;
     private String udc3;
     private String udc4;
     private String udc5;
     private String udc6;
     private String formulaMethodId;
     private String createdBy;
     private Date createdDate;
     private String modifiedBy;
     private Date modifiedDate;
     private String batchId;
     private String source;
     private String addChgDelIndicator;
     private String reasonForFailure;
     private Date intfInsertedDate;
     private String errorCode;
     private String errorField;
     private boolean checkRecord;
     private String reprocessedFlag;
     
     private String deductionInclusion;
     private String rebateScheduleAliasId;
     private String paymentLevel;
     private String evaluationRuleLevel;
     private String evaluationRule;
     private String evaluationRuleBundle;
     private String calculationType;
     private String calculationRule;
     private String calculationRuleLevel;
     private String calculationRuleBundle;
     private String netSalesFormula;
     private String nestSalesRule;
     private String deductionCalendarNo;
     private String deductionCalendarName;
                                                             

    public IvldRebateSchedule() {
    }

	
    public IvldRebateSchedule(int ivldRebateScheduleSid, String rebateScheduleIntfid) {
        this.ivldRebateScheduleSid = ivldRebateScheduleSid;
        this.rebateScheduleIntfid = rebateScheduleIntfid;
    }
    public IvldRebateSchedule(int ivldRebateScheduleSid, String rebateScheduleIntfid, String rebateScheduleId, String rebateScheduleNo, String rebateScheduleName, String rebateScheduleType, String rebateProgramType, String rebateScheduleCategory, String tradeClass, String rebateScheduleDesignation, String parentRebateScheduleId, String parentRebateScheduleName, String rebateScheduleStatus, String rebateScheduleTransRefId, String rebateScheduleTransRefNo, String rebateScheduleTransRefName, String paymentMethod, String paymentFrequency, String paymentTerms, String rebateFrequency, String calendar, String rebateProcessingType, String validationProfile, String rebateRuleType, String rebateRuleAssociation, String rebatePlanLevel, String interestBearingIndicator, String interestBearingBasis, String paymentGracePeriod, String makePayableTo, String address1, String address2, String city, String state, String zipCode, String itemId, String identifierCodeQualifier, String itemNo, String itemName, String attachedStatus, String attachedDate, String itemStartDate, String itemEndDate, String formulaType, String formulaId, String formulaNo, String formulaName, String rebatePlanId, String rebateAmount, String itemRebateStartDate, String itemRebateEndDate, String bundleNo, String internalNotes, String contractId, String cfpId, String mfpId, String ifpId, String priceScheduleId, String udc1, String udc2, String udc3, String udc4, String udc5, String udc6, String formulaMethodId, String createdBy, Date createdDate, String modifiedBy, Date modifiedDate, String batchId, String source, String addChgDelIndicator, String reasonForFailure, Date intfInsertedDate, String errorCode, String errorField, boolean checkRecord, String reprocessedFlag) {
       this.ivldRebateScheduleSid = ivldRebateScheduleSid;
       this.rebateScheduleIntfid = rebateScheduleIntfid;
       this.rebateScheduleId = rebateScheduleId;
       this.rebateScheduleNo = rebateScheduleNo;
       this.rebateScheduleName = rebateScheduleName;
       this.rebateScheduleType = rebateScheduleType;
       this.rebateProgramType = rebateProgramType;
       this.rebateScheduleCategory = rebateScheduleCategory;
       this.tradeClass = tradeClass;
       this.rebateScheduleDesignation = rebateScheduleDesignation;
       this.parentRebateScheduleId = parentRebateScheduleId;
       this.parentRebateScheduleName = parentRebateScheduleName;
       this.rebateScheduleStatus = rebateScheduleStatus;
       this.rebateScheduleTransRefId = rebateScheduleTransRefId;
       this.rebateScheduleTransRefNo = rebateScheduleTransRefNo;
       this.rebateScheduleTransRefName = rebateScheduleTransRefName;
       this.paymentMethod = paymentMethod;
       this.paymentFrequency = paymentFrequency;
       this.paymentTerms = paymentTerms;
       this.rebateFrequency = rebateFrequency;
       this.calendar = calendar;
       this.rebateProcessingType = rebateProcessingType;
       this.validationProfile = validationProfile;
       this.rebateRuleType = rebateRuleType;
       this.rebateRuleAssociation = rebateRuleAssociation;
       this.rebatePlanLevel = rebatePlanLevel;
       this.interestBearingIndicator = interestBearingIndicator;
       this.interestBearingBasis = interestBearingBasis;
       this.paymentGracePeriod = paymentGracePeriod;
       this.makePayableTo = makePayableTo;
       this.address1 = address1;
       this.address2 = address2;
       this.city = city;
       this.state = state;
       this.zipCode = zipCode;
       this.itemId = itemId;
       this.identifierCodeQualifier = identifierCodeQualifier;
       this.itemNo = itemNo;
       this.itemName = itemName;
       this.attachedStatus = attachedStatus;
       this.attachedDate = attachedDate;
       this.itemStartDate = itemStartDate;
       this.itemEndDate = itemEndDate;
       this.formulaType = formulaType;
       this.formulaId = formulaId;
       this.formulaNo = formulaNo;
       this.formulaName = formulaName;
       this.rebatePlanId = rebatePlanId;
       this.rebateAmount = rebateAmount;
       this.itemRebateStartDate = itemRebateStartDate;
       this.itemRebateEndDate = itemRebateEndDate;
       this.bundleNo = bundleNo;
       this.internalNotes = internalNotes;
       this.contractId = contractId;
       this.cfpId = cfpId;
       this.mfpId = mfpId;
       this.ifpId = ifpId;
       this.priceScheduleId = priceScheduleId;
       this.udc1 = udc1;
       this.udc2 = udc2;
       this.udc3 = udc3;
       this.udc4 = udc4;
       this.udc5 = udc5;
       this.udc6 = udc6;
       this.formulaMethodId = formulaMethodId;
       this.createdBy = createdBy;
       this.createdDate = createdDate;
       this.modifiedBy = modifiedBy;
       this.modifiedDate = modifiedDate;
       this.batchId = batchId;
       this.source = source;
       this.addChgDelIndicator = addChgDelIndicator;
       this.reasonForFailure = reasonForFailure;
       this.intfInsertedDate = intfInsertedDate;
       this.errorCode = errorCode;
       this.errorField = errorField;
       this.checkRecord = checkRecord;
       this.reprocessedFlag = reprocessedFlag;
    }
   
    public int getIvldRebateScheduleSid() {
        return this.ivldRebateScheduleSid;
    }
    
    public void setIvldRebateScheduleSid(int ivldRebateScheduleSid) {
        this.ivldRebateScheduleSid = ivldRebateScheduleSid;
    }
    public String getRebateScheduleIntfid() {
        return this.rebateScheduleIntfid;
    }
    
    public void setRebateScheduleIntfid(String rebateScheduleIntfid) {
        this.rebateScheduleIntfid = rebateScheduleIntfid;
    }
    public String getRebateScheduleId() {
        return this.rebateScheduleId;
    }
    
    public void setRebateScheduleId(String rebateScheduleId) {
        this.rebateScheduleId = rebateScheduleId;
    }
    public String getRebateScheduleNo() {
        return this.rebateScheduleNo;
    }
    
    public void setRebateScheduleNo(String rebateScheduleNo) {
        this.rebateScheduleNo = rebateScheduleNo;
    }
    public String getRebateScheduleName() {
        return this.rebateScheduleName;
    }
    
    public void setRebateScheduleName(String rebateScheduleName) {
        this.rebateScheduleName = rebateScheduleName;
    }
    public String getRebateScheduleType() {
        return this.rebateScheduleType;
    }
    
    public void setRebateScheduleType(String rebateScheduleType) {
        this.rebateScheduleType = rebateScheduleType;
    }
    public String getRebateProgramType() {
        return this.rebateProgramType;
    }
    
    public void setRebateProgramType(String rebateProgramType) {
        this.rebateProgramType = rebateProgramType;
    }
    public String getRebateScheduleCategory() {
        return this.rebateScheduleCategory;
    }
    
    public void setRebateScheduleCategory(String rebateScheduleCategory) {
        this.rebateScheduleCategory = rebateScheduleCategory;
    }
    public String getTradeClass() {
        return this.tradeClass;
    }
    
    public void setTradeClass(String tradeClass) {
        this.tradeClass = tradeClass;
    }
    public String getRebateScheduleDesignation() {
        return this.rebateScheduleDesignation;
    }
    
    public void setRebateScheduleDesignation(String rebateScheduleDesignation) {
        this.rebateScheduleDesignation = rebateScheduleDesignation;
    }
    public String getParentRebateScheduleId() {
        return this.parentRebateScheduleId;
    }
    
    public void setParentRebateScheduleId(String parentRebateScheduleId) {
        this.parentRebateScheduleId = parentRebateScheduleId;
    }
    public String getParentRebateScheduleName() {
        return this.parentRebateScheduleName;
    }
    
    public void setParentRebateScheduleName(String parentRebateScheduleName) {
        this.parentRebateScheduleName = parentRebateScheduleName;
    }
    public String getRebateScheduleStatus() {
        return this.rebateScheduleStatus;
    }
    
    public void setRebateScheduleStatus(String rebateScheduleStatus) {
        this.rebateScheduleStatus = rebateScheduleStatus;
    }
    public String getRebateScheduleTransRefId() {
        return this.rebateScheduleTransRefId;
    }
    
    public void setRebateScheduleTransRefId(String rebateScheduleTransRefId) {
        this.rebateScheduleTransRefId = rebateScheduleTransRefId;
    }
    public String getRebateScheduleTransRefNo() {
        return this.rebateScheduleTransRefNo;
    }
    
    public void setRebateScheduleTransRefNo(String rebateScheduleTransRefNo) {
        this.rebateScheduleTransRefNo = rebateScheduleTransRefNo;
    }
    public String getRebateScheduleTransRefName() {
        return this.rebateScheduleTransRefName;
    }
    
    public void setRebateScheduleTransRefName(String rebateScheduleTransRefName) {
        this.rebateScheduleTransRefName = rebateScheduleTransRefName;
    }
    public String getPaymentMethod() {
        return this.paymentMethod;
    }
    
    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
    public String getPaymentFrequency() {
        return this.paymentFrequency;
    }
    
    public void setPaymentFrequency(String paymentFrequency) {
        this.paymentFrequency = paymentFrequency;
    }
    public String getPaymentTerms() {
        return this.paymentTerms;
    }
    
    public void setPaymentTerms(String paymentTerms) {
        this.paymentTerms = paymentTerms;
    }
    public String getRebateFrequency() {
        return this.rebateFrequency;
    }
    
    public void setRebateFrequency(String rebateFrequency) {
        this.rebateFrequency = rebateFrequency;
    }
    public String getCalendar() {
        return this.calendar;
    }
    
    public void setCalendar(String calendar) {
        this.calendar = calendar;
    }
    public String getRebateProcessingType() {
        return this.rebateProcessingType;
    }
    
    public void setRebateProcessingType(String rebateProcessingType) {
        this.rebateProcessingType = rebateProcessingType;
    }
    public String getValidationProfile() {
        return this.validationProfile;
    }
    
    public void setValidationProfile(String validationProfile) {
        this.validationProfile = validationProfile;
    }
    public String getRebateRuleType() {
        return this.rebateRuleType;
    }
    
    public void setRebateRuleType(String rebateRuleType) {
        this.rebateRuleType = rebateRuleType;
    }
    public String getRebateRuleAssociation() {
        return this.rebateRuleAssociation;
    }
    
    public void setRebateRuleAssociation(String rebateRuleAssociation) {
        this.rebateRuleAssociation = rebateRuleAssociation;
    }
    public String getRebatePlanLevel() {
        return this.rebatePlanLevel;
    }
    
    public void setRebatePlanLevel(String rebatePlanLevel) {
        this.rebatePlanLevel = rebatePlanLevel;
    }
    public String getInterestBearingIndicator() {
        return this.interestBearingIndicator;
    }
    
    public void setInterestBearingIndicator(String interestBearingIndicator) {
        this.interestBearingIndicator = interestBearingIndicator;
    }
    public String getInterestBearingBasis() {
        return this.interestBearingBasis;
    }
    
    public void setInterestBearingBasis(String interestBearingBasis) {
        this.interestBearingBasis = interestBearingBasis;
    }
    public String getPaymentGracePeriod() {
        return this.paymentGracePeriod;
    }
    
    public void setPaymentGracePeriod(String paymentGracePeriod) {
        this.paymentGracePeriod = paymentGracePeriod;
    }
    public String getMakePayableTo() {
        return this.makePayableTo;
    }
    
    public void setMakePayableTo(String makePayableTo) {
        this.makePayableTo = makePayableTo;
    }
    public String getAddress1() {
        return this.address1;
    }
    
    public void setAddress1(String address1) {
        this.address1 = address1;
    }
    public String getAddress2() {
        return this.address2;
    }
    
    public void setAddress2(String address2) {
        this.address2 = address2;
    }
    public String getCity() {
        return this.city;
    }
    
    public void setCity(String city) {
        this.city = city;
    }
    public String getState() {
        return this.state;
    }
    
    public void setState(String state) {
        this.state = state;
    }
    public String getZipCode() {
        return this.zipCode;
    }
    
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
    public String getItemId() {
        return this.itemId;
    }
    
    public void setItemId(String itemId) {
        this.itemId = itemId;
    }
    public String getIdentifierCodeQualifier() {
        return this.identifierCodeQualifier;
    }
    
    public void setIdentifierCodeQualifier(String identifierCodeQualifier) {
        this.identifierCodeQualifier = identifierCodeQualifier;
    }
    public String getItemNo() {
        return this.itemNo;
    }
    
    public void setItemNo(String itemNo) {
        this.itemNo = itemNo;
    }
    public String getItemName() {
        return this.itemName;
    }
    
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
    public String getAttachedStatus() {
        return this.attachedStatus;
    }
    
    public void setAttachedStatus(String attachedStatus) {
        this.attachedStatus = attachedStatus;
    }
    public String getAttachedDate() {
        return this.attachedDate;
    }
    
    public void setAttachedDate(String attachedDate) {
        this.attachedDate = attachedDate;
    }
    public String getItemStartDate() {
        return this.itemStartDate;
    }
    
    public void setItemStartDate(String itemStartDate) {
        this.itemStartDate = itemStartDate;
    }
    public String getItemEndDate() {
        return this.itemEndDate;
    }
    
    public void setItemEndDate(String itemEndDate) {
        this.itemEndDate = itemEndDate;
    }
    public String getFormulaType() {
        return this.formulaType;
    }
    
    public void setFormulaType(String formulaType) {
        this.formulaType = formulaType;
    }
    public String getFormulaId() {
        return this.formulaId;
    }
    
    public void setFormulaId(String formulaId) {
        this.formulaId = formulaId;
    }
    public String getFormulaNo() {
        return this.formulaNo;
    }
    
    public void setFormulaNo(String formulaNo) {
        this.formulaNo = formulaNo;
    }
    public String getFormulaName() {
        return this.formulaName;
    }
    
    public void setFormulaName(String formulaName) {
        this.formulaName = formulaName;
    }
    public String getRebatePlanId() {
        return this.rebatePlanId;
    }
    
    public void setRebatePlanId(String rebatePlanId) {
        this.rebatePlanId = rebatePlanId;
    }
    public String getRebateAmount() {
        return this.rebateAmount;
    }
    
    public void setRebateAmount(String rebateAmount) {
        this.rebateAmount = rebateAmount;
    }
    public String getItemRebateStartDate() {
        return this.itemRebateStartDate;
    }
    
    public void setItemRebateStartDate(String itemRebateStartDate) {
        this.itemRebateStartDate = itemRebateStartDate;
    }
    public String getItemRebateEndDate() {
        return this.itemRebateEndDate;
    }
    
    public void setItemRebateEndDate(String itemRebateEndDate) {
        this.itemRebateEndDate = itemRebateEndDate;
    }
    public String getBundleNo() {
        return this.bundleNo;
    }
    
    public void setBundleNo(String bundleNo) {
        this.bundleNo = bundleNo;
    }
    public String getInternalNotes() {
        return this.internalNotes;
    }
    
    public void setInternalNotes(String internalNotes) {
        this.internalNotes = internalNotes;
    }
    public String getContractId() {
        return this.contractId;
    }
    
    public void setContractId(String contractId) {
        this.contractId = contractId;
    }
    public String getCfpId() {
        return this.cfpId;
    }
    
    public void setCfpId(String cfpId) {
        this.cfpId = cfpId;
    }
    public String getMfpId() {
        return this.mfpId;
    }
    
    public void setMfpId(String mfpId) {
        this.mfpId = mfpId;
    }
    public String getIfpId() {
        return this.ifpId;
    }
    
    public void setIfpId(String ifpId) {
        this.ifpId = ifpId;
    }
    public String getPriceScheduleId() {
        return this.priceScheduleId;
    }
    
    public void setPriceScheduleId(String priceScheduleId) {
        this.priceScheduleId = priceScheduleId;
    }
    public String getUdc1() {
        return this.udc1;
    }
    
    public void setUdc1(String udc1) {
        this.udc1 = udc1;
    }
    public String getUdc2() {
        return this.udc2;
    }
    
    public void setUdc2(String udc2) {
        this.udc2 = udc2;
    }
    public String getUdc3() {
        return this.udc3;
    }
    
    public void setUdc3(String udc3) {
        this.udc3 = udc3;
    }
    public String getUdc4() {
        return this.udc4;
    }
    
    public void setUdc4(String udc4) {
        this.udc4 = udc4;
    }
    public String getUdc5() {
        return this.udc5;
    }
    
    public void setUdc5(String udc5) {
        this.udc5 = udc5;
    }
    public String getUdc6() {
        return this.udc6;
    }
    
    public void setUdc6(String udc6) {
        this.udc6 = udc6;
    }
    public String getFormulaMethodId() {
        return this.formulaMethodId;
    }
    
    public void setFormulaMethodId(String formulaMethodId) {
        this.formulaMethodId = formulaMethodId;
    }
    public String getCreatedBy() {
        return this.createdBy;
    }
    
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
    public Date getCreatedDate() {
        return this.createdDate;
    }
    
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
    public String getModifiedBy() {
        return this.modifiedBy;
    }
    
    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }
    public Date getModifiedDate() {
        return this.modifiedDate;
    }
    
    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
    public String getBatchId() {
        return this.batchId;
    }
    
    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }
    public String getSource() {
        return this.source;
    }
    
    public void setSource(String source) {
        this.source = source;
    }
    public String getAddChgDelIndicator() {
        return this.addChgDelIndicator;
    }
    
    public void setAddChgDelIndicator(String addChgDelIndicator) {
        this.addChgDelIndicator = addChgDelIndicator;
    }
    public String getReasonForFailure() {
        return this.reasonForFailure;
    }
    
    public void setReasonForFailure(String reasonForFailure) {
        this.reasonForFailure = reasonForFailure;
    }
    public Date getIntfInsertedDate() {
        return this.intfInsertedDate;
    }
    
    public void setIntfInsertedDate(Date intfInsertedDate) {
        this.intfInsertedDate = intfInsertedDate;
    }
    public String getErrorCode() {
        return this.errorCode;
    }
    
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
    public String getErrorField() {
        return this.errorField;
    }
    
    public void setErrorField(String errorField) {
        this.errorField = errorField;
    }
    public boolean getCheckRecord() {
        return this.checkRecord;
    }
    
    public void setCheckRecord(boolean checkRecord) {
        this.checkRecord = checkRecord;
    }
    public String getReprocessedFlag() {
        return this.reprocessedFlag;
    }
    
    public void setReprocessedFlag(String reprocessedFlag) {
        this.reprocessedFlag = reprocessedFlag;
    }

    public String getDeductionInclusion() {
        return deductionInclusion;
    }

    public void setDeductionInclusion(String deductionInclusion) {
        this.deductionInclusion = deductionInclusion;
    }

    public String getRebateScheduleAliasId() {
        return rebateScheduleAliasId;
    }

    public void setRebateScheduleAliasId(String rebateScheduleAliasId) {
        this.rebateScheduleAliasId = rebateScheduleAliasId;
    }

    public String getPaymentLevel() {
        return paymentLevel;
    }

    public void setPaymentLevel(String paymentLevel) {
        this.paymentLevel = paymentLevel;
    }

    public String getEvaluationRuleLevel() {
        return evaluationRuleLevel;
    }

    public void setEvaluationRuleLevel(String evaluationRuleLevel) {
        this.evaluationRuleLevel = evaluationRuleLevel;
    }

    public String getEvaluationRule() {
        return evaluationRule;
    }

    public void setEvaluationRule(String evaluationRule) {
        this.evaluationRule = evaluationRule;
    }

    public String getEvaluationRuleBundle() {
        return evaluationRuleBundle;
    }

    public void setEvaluationRuleBundle(String evaluationRuleBundle) {
        this.evaluationRuleBundle = evaluationRuleBundle;
    }

    public String getCalculationType() {
        return calculationType;
    }

    public void setCalculationType(String calculationType) {
        this.calculationType = calculationType;
    }

    public String getCalculationRule() {
        return calculationRule;
    }

    public void setCalculationRule(String calculationRule) {
        this.calculationRule = calculationRule;
    }

    public String getCalculationRuleLevel() {
        return calculationRuleLevel;
    }

    public void setCalculationRuleLevel(String calculationRuleLevel) {
        this.calculationRuleLevel = calculationRuleLevel;
    }

    public String getCalculationRuleBundle() {
        return calculationRuleBundle;
    }

    public void setCalculationRuleBundle(String calculationRuleBundle) {
        this.calculationRuleBundle = calculationRuleBundle;
    }

    public String getNetSalesFormula() {
        return netSalesFormula;
    }

    public void setNetSalesFormula(String netSalesFormula) {
        this.netSalesFormula = netSalesFormula;
    }

    public String getNestSalesRule() {
        return nestSalesRule;
    }

    public void setNestSalesRule(String nestSalesRule) {
        this.nestSalesRule = nestSalesRule;
    }

    public String getDeductionCalendarNo() {
        return deductionCalendarNo;
    }

    public void setDeductionCalendarNo(String deductionCalendarNo) {
        this.deductionCalendarNo = deductionCalendarNo;
    }

    public String getDeductionCalendarName() {
        return deductionCalendarName;
    }

    public void setDeductionCalendarName(String deductionCalendarName) {
        this.deductionCalendarName = deductionCalendarName;
    }
    
    



}


