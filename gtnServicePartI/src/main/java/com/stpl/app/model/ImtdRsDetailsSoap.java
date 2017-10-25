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
public class ImtdRsDetailsSoap implements Serializable {
    private Date _rsDetailsModifiedDate;
    private String _rsDetailsBundleNo;
    private int _itemMasterSid;
    private int _imtdRsDetailsSid;
    private String _itemId;
    private String _rsDetailsFormulaMethodId;
    private Date _modifiedDate;
    private Date _createdDate;
    private int _createdBy;
    private String _usersSid;
    private int _contractMasterSid;
    private String _rsDetailsFormulaId;
    private Date _imtdCreatedDate;
    private int _psModelSid;
    private int _modifiedBy;
    private Date _rsDetailsCreatedDate;
    private String _itemNo;
    private String _rsDetailsFormulaName;
    private String _udc6;
    private String _rsDetailsCreatedBy;
    private String _udc5;
    private int _ifpModelSid;
    private String _udc4;
    private String _rsDetailsFormulaNo;
    private boolean _checkRecord;
    private String _rsId;
    private String _udc1;
    private double _rsDetailsRebateAmount;
    private String _udc2;
    private String _rsDetailsModifiedBy;
    private String _udc3;
    private String _rebatePlanMasterSid;
    private Date _rsDetailsAttachedDate;
    private Date _itemRebateEndDate;
    private String _rsDetailsRebatePlanName;
    private Date _itemRebateStartDate;
    private String _rsDetailsFormulaType;
    private String _sessionId;
    private String _itemName;
    private String _operation;
    private int _cfpModelSid;
    private int _rsModelSid;
    private int _rsDetailsSid;
    private int _rsDetailsAttachedStatus;
    private String _rsDetailsNetSalesFormulaNo;
    private String _rsDetailsNetSalesFormulaName;
    private String _rsDetailsDeductionCalendarNo;
    private String _rsDetailsDeductionCalendarName;
    private int _deductionCalendarMasterSid;
    private int _netSalesFormulaMasterSid;
    private String _evaluationRule;
    private String _netSalesRule;
    private String _formulaType;
    private String _calculationRule;
    private String _calculationRuleBundle;
    private String _evaluationRuleBundle;

    public ImtdRsDetailsSoap() {
    }

    public static ImtdRsDetailsSoap toSoapModel(ImtdRsDetails model) {
        ImtdRsDetailsSoap soapModel = new ImtdRsDetailsSoap();

        soapModel.setRsDetailsModifiedDate(model.getRsDetailsModifiedDate());
        soapModel.setRsDetailsBundleNo(model.getRsDetailsBundleNo());
        soapModel.setItemMasterSid(model.getItemMasterSid());
        soapModel.setImtdRsDetailsSid(model.getImtdRsDetailsSid());
        soapModel.setItemId(model.getItemId());
        soapModel.setRsDetailsFormulaMethodId(model.getRsDetailsFormulaMethodId());
        soapModel.setModifiedDate(model.getModifiedDate());
        soapModel.setCreatedDate(model.getCreatedDate());
        soapModel.setCreatedBy(model.getCreatedBy());
        soapModel.setUsersSid(model.getUsersSid());
        soapModel.setContractMasterSid(model.getContractMasterSid());
        soapModel.setRsDetailsFormulaId(model.getRsDetailsFormulaId());
        soapModel.setImtdCreatedDate(model.getImtdCreatedDate());
        soapModel.setPsModelSid(model.getPsModelSid());
        soapModel.setModifiedBy(model.getModifiedBy());
        soapModel.setRsDetailsCreatedDate(model.getRsDetailsCreatedDate());
        soapModel.setItemNo(model.getItemNo());
        soapModel.setRsDetailsFormulaName(model.getRsDetailsFormulaName());
        soapModel.setUdc6(model.getUdc6());
        soapModel.setRsDetailsCreatedBy(model.getRsDetailsCreatedBy());
        soapModel.setUdc5(model.getUdc5());
        soapModel.setIfpModelSid(model.getIfpModelSid());
        soapModel.setUdc4(model.getUdc4());
        soapModel.setRsDetailsFormulaNo(model.getRsDetailsFormulaNo());
        soapModel.setCheckRecord(model.getCheckRecord());
        soapModel.setRsId(model.getRsId());
        soapModel.setUdc1(model.getUdc1());
        soapModel.setRsDetailsRebateAmount(model.getRsDetailsRebateAmount());
        soapModel.setUdc2(model.getUdc2());
        soapModel.setRsDetailsModifiedBy(model.getRsDetailsModifiedBy());
        soapModel.setUdc3(model.getUdc3());
        soapModel.setRebatePlanMasterSid(model.getRebatePlanMasterSid());
        soapModel.setRsDetailsAttachedDate(model.getRsDetailsAttachedDate());
        soapModel.setItemRebateEndDate(model.getItemRebateEndDate());
        soapModel.setRsDetailsRebatePlanName(model.getRsDetailsRebatePlanName());
        soapModel.setItemRebateStartDate(model.getItemRebateStartDate());
        soapModel.setRsDetailsFormulaType(model.getRsDetailsFormulaType());
        soapModel.setSessionId(model.getSessionId());
        soapModel.setItemName(model.getItemName());
        soapModel.setOperation(model.getOperation());
        soapModel.setCfpModelSid(model.getCfpModelSid());
        soapModel.setRsModelSid(model.getRsModelSid());
        soapModel.setRsDetailsSid(model.getRsDetailsSid());
        soapModel.setRsDetailsAttachedStatus(model.getRsDetailsAttachedStatus());
        soapModel.setRsDetailsNetSalesFormulaNo(model.getRsDetailsNetSalesFormulaNo());
        soapModel.setRsDetailsNetSalesFormulaName(model.getRsDetailsNetSalesFormulaName());
        soapModel.setRsDetailsDeductionCalendarNo(model.getRsDetailsDeductionCalendarNo());
        soapModel.setRsDetailsDeductionCalendarName(model.getRsDetailsDeductionCalendarName());
        soapModel.setDeductionCalendarMasterSid(model.getDeductionCalendarMasterSid());
        soapModel.setNetSalesFormulaMasterSid(model.getNetSalesFormulaMasterSid());
        soapModel.setEvaluationRule(model.getEvaluationRule());
        soapModel.setNetSalesRule(model.getNetSalesRule());
        soapModel.setFormulaType(model.getFormulaType());
        soapModel.setCalculationRule(model.getCalculationRule());
        soapModel.setCalculationRuleBundle(model.getCalculationRuleBundle());
        soapModel.setEvaluationRuleBundle(model.getEvaluationRuleBundle());

        return soapModel;
    }

    public static ImtdRsDetailsSoap[] toSoapModels(ImtdRsDetails[] models) {
        ImtdRsDetailsSoap[] soapModels = new ImtdRsDetailsSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static ImtdRsDetailsSoap[][] toSoapModels(ImtdRsDetails[][] models) {
        ImtdRsDetailsSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new ImtdRsDetailsSoap[models.length][models[0].length];
        } else {
            soapModels = new ImtdRsDetailsSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static ImtdRsDetailsSoap[] toSoapModels(List<ImtdRsDetails> models) {
        List<ImtdRsDetailsSoap> soapModels = new ArrayList<ImtdRsDetailsSoap>(models.size());

        for (ImtdRsDetails model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new ImtdRsDetailsSoap[soapModels.size()]);
    }

    public int getPrimaryKey() {
        return _imtdRsDetailsSid;
    }

    public void setPrimaryKey(int pk) {
        setImtdRsDetailsSid(pk);
    }

    public Date getRsDetailsModifiedDate() {
        return _rsDetailsModifiedDate;
    }

    public void setRsDetailsModifiedDate(Date rsDetailsModifiedDate) {
        _rsDetailsModifiedDate = rsDetailsModifiedDate;
    }

    public String getRsDetailsBundleNo() {
        return _rsDetailsBundleNo;
    }

    public void setRsDetailsBundleNo(String rsDetailsBundleNo) {
        _rsDetailsBundleNo = rsDetailsBundleNo;
    }

    public int getItemMasterSid() {
        return _itemMasterSid;
    }

    public void setItemMasterSid(int itemMasterSid) {
        _itemMasterSid = itemMasterSid;
    }

    public int getImtdRsDetailsSid() {
        return _imtdRsDetailsSid;
    }

    public void setImtdRsDetailsSid(int imtdRsDetailsSid) {
        _imtdRsDetailsSid = imtdRsDetailsSid;
    }

    public String getItemId() {
        return _itemId;
    }

    public void setItemId(String itemId) {
        _itemId = itemId;
    }

    public String getRsDetailsFormulaMethodId() {
        return _rsDetailsFormulaMethodId;
    }

    public void setRsDetailsFormulaMethodId(String rsDetailsFormulaMethodId) {
        _rsDetailsFormulaMethodId = rsDetailsFormulaMethodId;
    }

    public Date getModifiedDate() {
        return _modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        _modifiedDate = modifiedDate;
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

    public String getUsersSid() {
        return _usersSid;
    }

    public void setUsersSid(String usersSid) {
        _usersSid = usersSid;
    }

    public int getContractMasterSid() {
        return _contractMasterSid;
    }

    public void setContractMasterSid(int contractMasterSid) {
        _contractMasterSid = contractMasterSid;
    }

    public String getRsDetailsFormulaId() {
        return _rsDetailsFormulaId;
    }

    public void setRsDetailsFormulaId(String rsDetailsFormulaId) {
        _rsDetailsFormulaId = rsDetailsFormulaId;
    }

    public Date getImtdCreatedDate() {
        return _imtdCreatedDate;
    }

    public void setImtdCreatedDate(Date imtdCreatedDate) {
        _imtdCreatedDate = imtdCreatedDate;
    }

    public int getPsModelSid() {
        return _psModelSid;
    }

    public void setPsModelSid(int psModelSid) {
        _psModelSid = psModelSid;
    }

    public int getModifiedBy() {
        return _modifiedBy;
    }

    public void setModifiedBy(int modifiedBy) {
        _modifiedBy = modifiedBy;
    }

    public Date getRsDetailsCreatedDate() {
        return _rsDetailsCreatedDate;
    }

    public void setRsDetailsCreatedDate(Date rsDetailsCreatedDate) {
        _rsDetailsCreatedDate = rsDetailsCreatedDate;
    }

    public String getItemNo() {
        return _itemNo;
    }

    public void setItemNo(String itemNo) {
        _itemNo = itemNo;
    }

    public String getRsDetailsFormulaName() {
        return _rsDetailsFormulaName;
    }

    public void setRsDetailsFormulaName(String rsDetailsFormulaName) {
        _rsDetailsFormulaName = rsDetailsFormulaName;
    }

    public String getUdc6() {
        return _udc6;
    }

    public void setUdc6(String udc6) {
        _udc6 = udc6;
    }

    public String getRsDetailsCreatedBy() {
        return _rsDetailsCreatedBy;
    }

    public void setRsDetailsCreatedBy(String rsDetailsCreatedBy) {
        _rsDetailsCreatedBy = rsDetailsCreatedBy;
    }

    public String getUdc5() {
        return _udc5;
    }

    public void setUdc5(String udc5) {
        _udc5 = udc5;
    }

    public int getIfpModelSid() {
        return _ifpModelSid;
    }

    public void setIfpModelSid(int ifpModelSid) {
        _ifpModelSid = ifpModelSid;
    }

    public String getUdc4() {
        return _udc4;
    }

    public void setUdc4(String udc4) {
        _udc4 = udc4;
    }

    public String getRsDetailsFormulaNo() {
        return _rsDetailsFormulaNo;
    }

    public void setRsDetailsFormulaNo(String rsDetailsFormulaNo) {
        _rsDetailsFormulaNo = rsDetailsFormulaNo;
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

    public String getRsId() {
        return _rsId;
    }

    public void setRsId(String rsId) {
        _rsId = rsId;
    }

    public String getUdc1() {
        return _udc1;
    }

    public void setUdc1(String udc1) {
        _udc1 = udc1;
    }

    public double getRsDetailsRebateAmount() {
        return _rsDetailsRebateAmount;
    }

    public void setRsDetailsRebateAmount(double rsDetailsRebateAmount) {
        _rsDetailsRebateAmount = rsDetailsRebateAmount;
    }

    public String getUdc2() {
        return _udc2;
    }

    public void setUdc2(String udc2) {
        _udc2 = udc2;
    }

    public String getRsDetailsModifiedBy() {
        return _rsDetailsModifiedBy;
    }

    public void setRsDetailsModifiedBy(String rsDetailsModifiedBy) {
        _rsDetailsModifiedBy = rsDetailsModifiedBy;
    }

    public String getUdc3() {
        return _udc3;
    }

    public void setUdc3(String udc3) {
        _udc3 = udc3;
    }

    public String getRebatePlanMasterSid() {
        return _rebatePlanMasterSid;
    }

    public void setRebatePlanMasterSid(String rebatePlanMasterSid) {
        _rebatePlanMasterSid = rebatePlanMasterSid;
    }

    public Date getRsDetailsAttachedDate() {
        return _rsDetailsAttachedDate;
    }

    public void setRsDetailsAttachedDate(Date rsDetailsAttachedDate) {
        _rsDetailsAttachedDate = rsDetailsAttachedDate;
    }

    public Date getItemRebateEndDate() {
        return _itemRebateEndDate;
    }

    public void setItemRebateEndDate(Date itemRebateEndDate) {
        _itemRebateEndDate = itemRebateEndDate;
    }

    public String getRsDetailsRebatePlanName() {
        return _rsDetailsRebatePlanName;
    }

    public void setRsDetailsRebatePlanName(String rsDetailsRebatePlanName) {
        _rsDetailsRebatePlanName = rsDetailsRebatePlanName;
    }

    public Date getItemRebateStartDate() {
        return _itemRebateStartDate;
    }

    public void setItemRebateStartDate(Date itemRebateStartDate) {
        _itemRebateStartDate = itemRebateStartDate;
    }

    public String getRsDetailsFormulaType() {
        return _rsDetailsFormulaType;
    }

    public void setRsDetailsFormulaType(String rsDetailsFormulaType) {
        _rsDetailsFormulaType = rsDetailsFormulaType;
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

    public int getRsModelSid() {
        return _rsModelSid;
    }

    public void setRsModelSid(int rsModelSid) {
        _rsModelSid = rsModelSid;
    }

    public int getRsDetailsSid() {
        return _rsDetailsSid;
    }

    public void setRsDetailsSid(int rsDetailsSid) {
        _rsDetailsSid = rsDetailsSid;
    }

    public int getRsDetailsAttachedStatus() {
        return _rsDetailsAttachedStatus;
    }

    public void setRsDetailsAttachedStatus(int rsDetailsAttachedStatus) {
        _rsDetailsAttachedStatus = rsDetailsAttachedStatus;
    }

    public String getRsDetailsNetSalesFormulaNo() {
        return _rsDetailsNetSalesFormulaNo;
    }

    public void setRsDetailsNetSalesFormulaNo(String rsDetailsNetSalesFormulaNo) {
        _rsDetailsNetSalesFormulaNo = rsDetailsNetSalesFormulaNo;
    }

    public String getRsDetailsNetSalesFormulaName() {
        return _rsDetailsNetSalesFormulaName;
    }

    public void setRsDetailsNetSalesFormulaName(
        String rsDetailsNetSalesFormulaName) {
        _rsDetailsNetSalesFormulaName = rsDetailsNetSalesFormulaName;
    }

    public String getRsDetailsDeductionCalendarNo() {
        return _rsDetailsDeductionCalendarNo;
    }

    public void setRsDetailsDeductionCalendarNo(
        String rsDetailsDeductionCalendarNo) {
        _rsDetailsDeductionCalendarNo = rsDetailsDeductionCalendarNo;
    }

    public String getRsDetailsDeductionCalendarName() {
        return _rsDetailsDeductionCalendarName;
    }

    public void setRsDetailsDeductionCalendarName(
        String rsDetailsDeductionCalendarName) {
        _rsDetailsDeductionCalendarName = rsDetailsDeductionCalendarName;
    }

    public int getDeductionCalendarMasterSid() {
        return _deductionCalendarMasterSid;
    }

    public void setDeductionCalendarMasterSid(int deductionCalendarMasterSid) {
        _deductionCalendarMasterSid = deductionCalendarMasterSid;
    }

    public int getNetSalesFormulaMasterSid() {
        return _netSalesFormulaMasterSid;
    }

    public void setNetSalesFormulaMasterSid(int netSalesFormulaMasterSid) {
        _netSalesFormulaMasterSid = netSalesFormulaMasterSid;
    }

    public String getEvaluationRule() {
        return _evaluationRule;
    }

    public void setEvaluationRule(String evaluationRule) {
        _evaluationRule = evaluationRule;
    }

    public String getNetSalesRule() {
        return _netSalesRule;
    }

    public void setNetSalesRule(String netSalesRule) {
        _netSalesRule = netSalesRule;
    }

    public String getFormulaType() {
        return _formulaType;
    }

    public void setFormulaType(String formulaType) {
        _formulaType = formulaType;
    }

    public String getCalculationRule() {
        return _calculationRule;
    }

    public void setCalculationRule(String calculationRule) {
        _calculationRule = calculationRule;
    }

    public String getCalculationRuleBundle() {
        return _calculationRuleBundle;
    }

    public void setCalculationRuleBundle(String calculationRuleBundle) {
        _calculationRuleBundle = calculationRuleBundle;
    }

    public String getEvaluationRuleBundle() {
        return _evaluationRuleBundle;
    }

    public void setEvaluationRuleBundle(String evaluationRuleBundle) {
        _evaluationRuleBundle = evaluationRuleBundle;
    }
}
