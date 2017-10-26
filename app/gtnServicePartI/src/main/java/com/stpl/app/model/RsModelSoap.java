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
public class RsModelSoap implements Serializable {
    private String _formulaMethodId;
    private int _calculationType;
    private int _rsStatus;
    private Date _rsEndDate;
    private String _rsTransRefNo;
    private int _paymentFrequency;
    private Date _modifiedDate;
    private int _calculationLevel;
    private String _rsName;
    private String _source;
    private String _rebatePlanLevel;
    private int _rebateRuleType;
    private String _inboundStatus;
    private int _modifiedBy;
    private String _rsAlias;
    private String _rsId;
    private int _paymentMethod;
    private String _zipCode;
    private String _rebateRuleAssociation;
    private String _parentRsName;
    private String _internalNotes;
    private int _paymentLevel;
    private boolean _recordLockStatus;
    private int _rsCalendar;
    private int _rebateProgramType;
    private int _interestBearingBasis;
    private int _rsModelSid;
    private String _city;
    private int _rebateProcessingType;
    private String _rsNo;
    private int _state;
    private int _rebateFrequency;
    private String _parentRsId;
    private int _rsType;
    private Date _rsStartDate;
    private String _makePayableTo;
    private int _rsDesignation;
    private String _rsTransRefName;
    private int _createdBy;
    private Date _createdDate;
    private String _rsTransRefId;
    private int _rsCategory;
    private int _rsTradeClass;
    private int _interestBearingIndicator;
    private int _manfCompanyMasterSid;
    private int _paymentTerms;
    private String _address1;
    private String _address2;
    private int _rsValidationProfile;
    private String _paymentGracePeriod;
    private String _batchId;
    private String _evaluationRuleType;
    private String _evaluationRuleLevel;
    private String _evaluationRuleOrAssociation;
    private String _calculationRuleLevel;
    private String _calculationRule;
    private String _deductionInclusion;

    public RsModelSoap() {
    }

    public static RsModelSoap toSoapModel(RsModel model) {
        RsModelSoap soapModel = new RsModelSoap();

        soapModel.setFormulaMethodId(model.getFormulaMethodId());
        soapModel.setCalculationType(model.getCalculationType());
        soapModel.setRsStatus(model.getRsStatus());
        soapModel.setRsEndDate(model.getRsEndDate());
        soapModel.setRsTransRefNo(model.getRsTransRefNo());
        soapModel.setPaymentFrequency(model.getPaymentFrequency());
        soapModel.setModifiedDate(model.getModifiedDate());
        soapModel.setCalculationLevel(model.getCalculationLevel());
        soapModel.setRsName(model.getRsName());
        soapModel.setSource(model.getSource());
        soapModel.setRebatePlanLevel(model.getRebatePlanLevel());
        soapModel.setRebateRuleType(model.getRebateRuleType());
        soapModel.setInboundStatus(model.getInboundStatus());
        soapModel.setModifiedBy(model.getModifiedBy());
        soapModel.setRsAlias(model.getRsAlias());
        soapModel.setRsId(model.getRsId());
        soapModel.setPaymentMethod(model.getPaymentMethod());
        soapModel.setZipCode(model.getZipCode());
        soapModel.setRebateRuleAssociation(model.getRebateRuleAssociation());
        soapModel.setParentRsName(model.getParentRsName());
        soapModel.setInternalNotes(model.getInternalNotes());
        soapModel.setPaymentLevel(model.getPaymentLevel());
        soapModel.setRecordLockStatus(model.getRecordLockStatus());
        soapModel.setRsCalendar(model.getRsCalendar());
        soapModel.setRebateProgramType(model.getRebateProgramType());
        soapModel.setInterestBearingBasis(model.getInterestBearingBasis());
        soapModel.setRsModelSid(model.getRsModelSid());
        soapModel.setCity(model.getCity());
        soapModel.setRebateProcessingType(model.getRebateProcessingType());
        soapModel.setRsNo(model.getRsNo());
        soapModel.setState(model.getState());
        soapModel.setRebateFrequency(model.getRebateFrequency());
        soapModel.setParentRsId(model.getParentRsId());
        soapModel.setRsType(model.getRsType());
        soapModel.setRsStartDate(model.getRsStartDate());
        soapModel.setMakePayableTo(model.getMakePayableTo());
        soapModel.setRsDesignation(model.getRsDesignation());
        soapModel.setRsTransRefName(model.getRsTransRefName());
        soapModel.setCreatedBy(model.getCreatedBy());
        soapModel.setCreatedDate(model.getCreatedDate());
        soapModel.setRsTransRefId(model.getRsTransRefId());
        soapModel.setRsCategory(model.getRsCategory());
        soapModel.setRsTradeClass(model.getRsTradeClass());
        soapModel.setInterestBearingIndicator(model.getInterestBearingIndicator());
        soapModel.setManfCompanyMasterSid(model.getManfCompanyMasterSid());
        soapModel.setPaymentTerms(model.getPaymentTerms());
        soapModel.setAddress1(model.getAddress1());
        soapModel.setAddress2(model.getAddress2());
        soapModel.setRsValidationProfile(model.getRsValidationProfile());
        soapModel.setPaymentGracePeriod(model.getPaymentGracePeriod());
        soapModel.setBatchId(model.getBatchId());
        soapModel.setEvaluationRuleType(model.getEvaluationRuleType());
        soapModel.setEvaluationRuleLevel(model.getEvaluationRuleLevel());
        soapModel.setEvaluationRuleOrAssociation(model.getEvaluationRuleOrAssociation());
        soapModel.setCalculationRuleLevel(model.getCalculationRuleLevel());
        soapModel.setCalculationRule(model.getCalculationRule());
        soapModel.setDeductionInclusion(model.getDeductionInclusion());

        return soapModel;
    }

    public static RsModelSoap[] toSoapModels(RsModel[] models) {
        RsModelSoap[] soapModels = new RsModelSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static RsModelSoap[][] toSoapModels(RsModel[][] models) {
        RsModelSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new RsModelSoap[models.length][models[0].length];
        } else {
            soapModels = new RsModelSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static RsModelSoap[] toSoapModels(List<RsModel> models) {
        List<RsModelSoap> soapModels = new ArrayList<RsModelSoap>(models.size());

        for (RsModel model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new RsModelSoap[soapModels.size()]);
    }

    public int getPrimaryKey() {
        return _rsModelSid;
    }

    public void setPrimaryKey(int pk) {
        setRsModelSid(pk);
    }

    public String getFormulaMethodId() {
        return _formulaMethodId;
    }

    public void setFormulaMethodId(String formulaMethodId) {
        _formulaMethodId = formulaMethodId;
    }

    public int getCalculationType() {
        return _calculationType;
    }

    public void setCalculationType(int calculationType) {
        _calculationType = calculationType;
    }

    public int getRsStatus() {
        return _rsStatus;
    }

    public void setRsStatus(int rsStatus) {
        _rsStatus = rsStatus;
    }

    public Date getRsEndDate() {
        return _rsEndDate;
    }

    public void setRsEndDate(Date rsEndDate) {
        _rsEndDate = rsEndDate;
    }

    public String getRsTransRefNo() {
        return _rsTransRefNo;
    }

    public void setRsTransRefNo(String rsTransRefNo) {
        _rsTransRefNo = rsTransRefNo;
    }

    public int getPaymentFrequency() {
        return _paymentFrequency;
    }

    public void setPaymentFrequency(int paymentFrequency) {
        _paymentFrequency = paymentFrequency;
    }

    public Date getModifiedDate() {
        return _modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        _modifiedDate = modifiedDate;
    }

    public int getCalculationLevel() {
        return _calculationLevel;
    }

    public void setCalculationLevel(int calculationLevel) {
        _calculationLevel = calculationLevel;
    }

    public String getRsName() {
        return _rsName;
    }

    public void setRsName(String rsName) {
        _rsName = rsName;
    }

    public String getSource() {
        return _source;
    }

    public void setSource(String source) {
        _source = source;
    }

    public String getRebatePlanLevel() {
        return _rebatePlanLevel;
    }

    public void setRebatePlanLevel(String rebatePlanLevel) {
        _rebatePlanLevel = rebatePlanLevel;
    }

    public int getRebateRuleType() {
        return _rebateRuleType;
    }

    public void setRebateRuleType(int rebateRuleType) {
        _rebateRuleType = rebateRuleType;
    }

    public String getInboundStatus() {
        return _inboundStatus;
    }

    public void setInboundStatus(String inboundStatus) {
        _inboundStatus = inboundStatus;
    }

    public int getModifiedBy() {
        return _modifiedBy;
    }

    public void setModifiedBy(int modifiedBy) {
        _modifiedBy = modifiedBy;
    }

    public String getRsAlias() {
        return _rsAlias;
    }

    public void setRsAlias(String rsAlias) {
        _rsAlias = rsAlias;
    }

    public String getRsId() {
        return _rsId;
    }

    public void setRsId(String rsId) {
        _rsId = rsId;
    }

    public int getPaymentMethod() {
        return _paymentMethod;
    }

    public void setPaymentMethod(int paymentMethod) {
        _paymentMethod = paymentMethod;
    }

    public String getZipCode() {
        return _zipCode;
    }

    public void setZipCode(String zipCode) {
        _zipCode = zipCode;
    }

    public String getRebateRuleAssociation() {
        return _rebateRuleAssociation;
    }

    public void setRebateRuleAssociation(String rebateRuleAssociation) {
        _rebateRuleAssociation = rebateRuleAssociation;
    }

    public String getParentRsName() {
        return _parentRsName;
    }

    public void setParentRsName(String parentRsName) {
        _parentRsName = parentRsName;
    }

    public String getInternalNotes() {
        return _internalNotes;
    }

    public void setInternalNotes(String internalNotes) {
        _internalNotes = internalNotes;
    }

    public int getPaymentLevel() {
        return _paymentLevel;
    }

    public void setPaymentLevel(int paymentLevel) {
        _paymentLevel = paymentLevel;
    }

    public boolean getRecordLockStatus() {
        return _recordLockStatus;
    }

    public boolean isRecordLockStatus() {
        return _recordLockStatus;
    }

    public void setRecordLockStatus(boolean recordLockStatus) {
        _recordLockStatus = recordLockStatus;
    }

    public int getRsCalendar() {
        return _rsCalendar;
    }

    public void setRsCalendar(int rsCalendar) {
        _rsCalendar = rsCalendar;
    }

    public int getRebateProgramType() {
        return _rebateProgramType;
    }

    public void setRebateProgramType(int rebateProgramType) {
        _rebateProgramType = rebateProgramType;
    }

    public int getInterestBearingBasis() {
        return _interestBearingBasis;
    }

    public void setInterestBearingBasis(int interestBearingBasis) {
        _interestBearingBasis = interestBearingBasis;
    }

    public int getRsModelSid() {
        return _rsModelSid;
    }

    public void setRsModelSid(int rsModelSid) {
        _rsModelSid = rsModelSid;
    }

    public String getCity() {
        return _city;
    }

    public void setCity(String city) {
        _city = city;
    }

    public int getRebateProcessingType() {
        return _rebateProcessingType;
    }

    public void setRebateProcessingType(int rebateProcessingType) {
        _rebateProcessingType = rebateProcessingType;
    }

    public String getRsNo() {
        return _rsNo;
    }

    public void setRsNo(String rsNo) {
        _rsNo = rsNo;
    }

    public int getState() {
        return _state;
    }

    public void setState(int state) {
        _state = state;
    }

    public int getRebateFrequency() {
        return _rebateFrequency;
    }

    public void setRebateFrequency(int rebateFrequency) {
        _rebateFrequency = rebateFrequency;
    }

    public String getParentRsId() {
        return _parentRsId;
    }

    public void setParentRsId(String parentRsId) {
        _parentRsId = parentRsId;
    }

    public int getRsType() {
        return _rsType;
    }

    public void setRsType(int rsType) {
        _rsType = rsType;
    }

    public Date getRsStartDate() {
        return _rsStartDate;
    }

    public void setRsStartDate(Date rsStartDate) {
        _rsStartDate = rsStartDate;
    }

    public String getMakePayableTo() {
        return _makePayableTo;
    }

    public void setMakePayableTo(String makePayableTo) {
        _makePayableTo = makePayableTo;
    }

    public int getRsDesignation() {
        return _rsDesignation;
    }

    public void setRsDesignation(int rsDesignation) {
        _rsDesignation = rsDesignation;
    }

    public String getRsTransRefName() {
        return _rsTransRefName;
    }

    public void setRsTransRefName(String rsTransRefName) {
        _rsTransRefName = rsTransRefName;
    }

    public int getCreatedBy() {
        return _createdBy;
    }

    public void setCreatedBy(int createdBy) {
        _createdBy = createdBy;
    }

    public Date getCreatedDate() {
        return _createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        _createdDate = createdDate;
    }

    public String getRsTransRefId() {
        return _rsTransRefId;
    }

    public void setRsTransRefId(String rsTransRefId) {
        _rsTransRefId = rsTransRefId;
    }

    public int getRsCategory() {
        return _rsCategory;
    }

    public void setRsCategory(int rsCategory) {
        _rsCategory = rsCategory;
    }

    public int getRsTradeClass() {
        return _rsTradeClass;
    }

    public void setRsTradeClass(int rsTradeClass) {
        _rsTradeClass = rsTradeClass;
    }

    public int getInterestBearingIndicator() {
        return _interestBearingIndicator;
    }

    public void setInterestBearingIndicator(int interestBearingIndicator) {
        _interestBearingIndicator = interestBearingIndicator;
    }

    public int getManfCompanyMasterSid() {
        return _manfCompanyMasterSid;
    }

    public void setManfCompanyMasterSid(int manfCompanyMasterSid) {
        _manfCompanyMasterSid = manfCompanyMasterSid;
    }

    public int getPaymentTerms() {
        return _paymentTerms;
    }

    public void setPaymentTerms(int paymentTerms) {
        _paymentTerms = paymentTerms;
    }

    public String getAddress1() {
        return _address1;
    }

    public void setAddress1(String address1) {
        _address1 = address1;
    }

    public String getAddress2() {
        return _address2;
    }

    public void setAddress2(String address2) {
        _address2 = address2;
    }

    public int getRsValidationProfile() {
        return _rsValidationProfile;
    }

    public void setRsValidationProfile(int rsValidationProfile) {
        _rsValidationProfile = rsValidationProfile;
    }

    public String getPaymentGracePeriod() {
        return _paymentGracePeriod;
    }

    public void setPaymentGracePeriod(String paymentGracePeriod) {
        _paymentGracePeriod = paymentGracePeriod;
    }

    public String getBatchId() {
        return _batchId;
    }

    public void setBatchId(String batchId) {
        _batchId = batchId;
    }

    public String getEvaluationRuleType() {
        return _evaluationRuleType;
    }

    public void setEvaluationRuleType(String evaluationRuleType) {
        _evaluationRuleType = evaluationRuleType;
    }

    public String getEvaluationRuleLevel() {
        return _evaluationRuleLevel;
    }

    public void setEvaluationRuleLevel(String evaluationRuleLevel) {
        _evaluationRuleLevel = evaluationRuleLevel;
    }

    public String getEvaluationRuleOrAssociation() {
        return _evaluationRuleOrAssociation;
    }

    public void setEvaluationRuleOrAssociation(
        String evaluationRuleOrAssociation) {
        _evaluationRuleOrAssociation = evaluationRuleOrAssociation;
    }

    public String getCalculationRuleLevel() {
        return _calculationRuleLevel;
    }

    public void setCalculationRuleLevel(String calculationRuleLevel) {
        _calculationRuleLevel = calculationRuleLevel;
    }

    public String getCalculationRule() {
        return _calculationRule;
    }

    public void setCalculationRule(String calculationRule) {
        _calculationRule = calculationRule;
    }

    public String getDeductionInclusion() {
        return _deductionInclusion;
    }

    public void setDeductionInclusion(String deductionInclusion) {
        _deductionInclusion = deductionInclusion;
    }
}
