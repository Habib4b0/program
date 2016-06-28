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
public class IvldFormulaDetailsSoap implements Serializable {
    private String _endDate;
    private String _rebatePercent1;
    private String _itemId;
    private String _rebatePercent2;
    private String _formulaDesc;
    private Date _modifiedDate;
    private String _rebatePercent3;
    private String _createdBy;
    private Date _createdDate;
    private String _source;
    private String _addChgDelIndicator;
    private String _errorCode;
    private String _formulaId;
    private String _modifiedBy;
    private Date _intfInsertedDate;
    private String _reprocessedFlag;
    private String _formulaDetailsIntfid;
    private String _reasonForFailure;
    private String _contractPrice1;
    private String _companyId;
    private String _contractPrice2;
    private String _formulaNo;
    private String _startDate;
    private String _batchId;
    private String _errorField;
    private String _contractPrice3;
    private int _ivldFormulaDetailsSid;

    public IvldFormulaDetailsSoap() {
    }

    public static IvldFormulaDetailsSoap toSoapModel(IvldFormulaDetails model) {
        IvldFormulaDetailsSoap soapModel = new IvldFormulaDetailsSoap();

        soapModel.setEndDate(model.getEndDate());
        soapModel.setRebatePercent1(model.getRebatePercent1());
        soapModel.setItemId(model.getItemId());
        soapModel.setRebatePercent2(model.getRebatePercent2());
        soapModel.setFormulaDesc(model.getFormulaDesc());
        soapModel.setModifiedDate(model.getModifiedDate());
        soapModel.setRebatePercent3(model.getRebatePercent3());
        soapModel.setCreatedBy(model.getCreatedBy());
        soapModel.setCreatedDate(model.getCreatedDate());
        soapModel.setSource(model.getSource());
        soapModel.setAddChgDelIndicator(model.getAddChgDelIndicator());
        soapModel.setErrorCode(model.getErrorCode());
        soapModel.setFormulaId(model.getFormulaId());
        soapModel.setModifiedBy(model.getModifiedBy());
        soapModel.setIntfInsertedDate(model.getIntfInsertedDate());
        soapModel.setReprocessedFlag(model.getReprocessedFlag());
        soapModel.setFormulaDetailsIntfid(model.getFormulaDetailsIntfid());
        soapModel.setReasonForFailure(model.getReasonForFailure());
        soapModel.setContractPrice1(model.getContractPrice1());
        soapModel.setCompanyId(model.getCompanyId());
        soapModel.setContractPrice2(model.getContractPrice2());
        soapModel.setFormulaNo(model.getFormulaNo());
        soapModel.setStartDate(model.getStartDate());
        soapModel.setBatchId(model.getBatchId());
        soapModel.setErrorField(model.getErrorField());
        soapModel.setContractPrice3(model.getContractPrice3());
        soapModel.setIvldFormulaDetailsSid(model.getIvldFormulaDetailsSid());

        return soapModel;
    }

    public static IvldFormulaDetailsSoap[] toSoapModels(
        IvldFormulaDetails[] models) {
        IvldFormulaDetailsSoap[] soapModels = new IvldFormulaDetailsSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static IvldFormulaDetailsSoap[][] toSoapModels(
        IvldFormulaDetails[][] models) {
        IvldFormulaDetailsSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new IvldFormulaDetailsSoap[models.length][models[0].length];
        } else {
            soapModels = new IvldFormulaDetailsSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static IvldFormulaDetailsSoap[] toSoapModels(
        List<IvldFormulaDetails> models) {
        List<IvldFormulaDetailsSoap> soapModels = new ArrayList<IvldFormulaDetailsSoap>(models.size());

        for (IvldFormulaDetails model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new IvldFormulaDetailsSoap[soapModels.size()]);
    }

    public int getPrimaryKey() {
        return _ivldFormulaDetailsSid;
    }

    public void setPrimaryKey(int pk) {
        setIvldFormulaDetailsSid(pk);
    }

    public String getEndDate() {
        return _endDate;
    }

    public void setEndDate(String endDate) {
        _endDate = endDate;
    }

    public String getRebatePercent1() {
        return _rebatePercent1;
    }

    public void setRebatePercent1(String rebatePercent1) {
        _rebatePercent1 = rebatePercent1;
    }

    public String getItemId() {
        return _itemId;
    }

    public void setItemId(String itemId) {
        _itemId = itemId;
    }

    public String getRebatePercent2() {
        return _rebatePercent2;
    }

    public void setRebatePercent2(String rebatePercent2) {
        _rebatePercent2 = rebatePercent2;
    }

    public String getFormulaDesc() {
        return _formulaDesc;
    }

    public void setFormulaDesc(String formulaDesc) {
        _formulaDesc = formulaDesc;
    }

    public Date getModifiedDate() {
        return _modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        _modifiedDate = modifiedDate;
    }

    public String getRebatePercent3() {
        return _rebatePercent3;
    }

    public void setRebatePercent3(String rebatePercent3) {
        _rebatePercent3 = rebatePercent3;
    }

    public String getCreatedBy() {
        return _createdBy;
    }

    public void setCreatedBy(String createdBy) {
        _createdBy = createdBy;
    }

    public Date getCreatedDate() {
        return _createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        _createdDate = createdDate;
    }

    public String getSource() {
        return _source;
    }

    public void setSource(String source) {
        _source = source;
    }

    public String getAddChgDelIndicator() {
        return _addChgDelIndicator;
    }

    public void setAddChgDelIndicator(String addChgDelIndicator) {
        _addChgDelIndicator = addChgDelIndicator;
    }

    public String getErrorCode() {
        return _errorCode;
    }

    public void setErrorCode(String errorCode) {
        _errorCode = errorCode;
    }

    public String getFormulaId() {
        return _formulaId;
    }

    public void setFormulaId(String formulaId) {
        _formulaId = formulaId;
    }

    public String getModifiedBy() {
        return _modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        _modifiedBy = modifiedBy;
    }

    public Date getIntfInsertedDate() {
        return _intfInsertedDate;
    }

    public void setIntfInsertedDate(Date intfInsertedDate) {
        _intfInsertedDate = intfInsertedDate;
    }

    public String getReprocessedFlag() {
        return _reprocessedFlag;
    }

    public void setReprocessedFlag(String reprocessedFlag) {
        _reprocessedFlag = reprocessedFlag;
    }

    public String getFormulaDetailsIntfid() {
        return _formulaDetailsIntfid;
    }

    public void setFormulaDetailsIntfid(String formulaDetailsIntfid) {
        _formulaDetailsIntfid = formulaDetailsIntfid;
    }

    public String getReasonForFailure() {
        return _reasonForFailure;
    }

    public void setReasonForFailure(String reasonForFailure) {
        _reasonForFailure = reasonForFailure;
    }

    public String getContractPrice1() {
        return _contractPrice1;
    }

    public void setContractPrice1(String contractPrice1) {
        _contractPrice1 = contractPrice1;
    }

    public String getCompanyId() {
        return _companyId;
    }

    public void setCompanyId(String companyId) {
        _companyId = companyId;
    }

    public String getContractPrice2() {
        return _contractPrice2;
    }

    public void setContractPrice2(String contractPrice2) {
        _contractPrice2 = contractPrice2;
    }

    public String getFormulaNo() {
        return _formulaNo;
    }

    public void setFormulaNo(String formulaNo) {
        _formulaNo = formulaNo;
    }

    public String getStartDate() {
        return _startDate;
    }

    public void setStartDate(String startDate) {
        _startDate = startDate;
    }

    public String getBatchId() {
        return _batchId;
    }

    public void setBatchId(String batchId) {
        _batchId = batchId;
    }

    public String getErrorField() {
        return _errorField;
    }

    public void setErrorField(String errorField) {
        _errorField = errorField;
    }

    public String getContractPrice3() {
        return _contractPrice3;
    }

    public void setContractPrice3(String contractPrice3) {
        _contractPrice3 = contractPrice3;
    }

    public int getIvldFormulaDetailsSid() {
        return _ivldFormulaDetailsSid;
    }

    public void setIvldFormulaDetailsSid(int ivldFormulaDetailsSid) {
        _ivldFormulaDetailsSid = ivldFormulaDetailsSid;
    }
}
