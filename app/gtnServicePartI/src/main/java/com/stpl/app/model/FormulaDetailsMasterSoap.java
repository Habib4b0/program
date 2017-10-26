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
public class FormulaDetailsMasterSoap implements Serializable {
    private String _companyId;
    private double _contractPrice1;
    private double _contractPrice2;
    private Date _endDate;
    private String _formulaNo;
    private int _formulaDetailsMasterSid;
    private String _itemId;
    private double _rebatePercent1;
    private Date _modifiedDate;
    private String _formulaDesc;
    private double _rebatePercent2;
    private double _rebatePercent3;
    private boolean _recordLockStatus;
    private Date _startDate;
    private Date _createdDate;
    private int _createdBy;
    private String _source;
    private String _batchId;
    private double _contractPrice3;
    private int _modifiedBy;
    private String _inboundStatus;
    private String _formulaId;

    public FormulaDetailsMasterSoap() {
    }

    public static FormulaDetailsMasterSoap toSoapModel(
        FormulaDetailsMaster model) {
        FormulaDetailsMasterSoap soapModel = new FormulaDetailsMasterSoap();

        soapModel.setCompanyId(model.getCompanyId());
        soapModel.setContractPrice1(model.getContractPrice1());
        soapModel.setContractPrice2(model.getContractPrice2());
        soapModel.setEndDate(model.getEndDate());
        soapModel.setFormulaNo(model.getFormulaNo());
        soapModel.setFormulaDetailsMasterSid(model.getFormulaDetailsMasterSid());
        soapModel.setItemId(model.getItemId());
        soapModel.setRebatePercent1(model.getRebatePercent1());
        soapModel.setModifiedDate(model.getModifiedDate());
        soapModel.setFormulaDesc(model.getFormulaDesc());
        soapModel.setRebatePercent2(model.getRebatePercent2());
        soapModel.setRebatePercent3(model.getRebatePercent3());
        soapModel.setRecordLockStatus(model.getRecordLockStatus());
        soapModel.setStartDate(model.getStartDate());
        soapModel.setCreatedDate(model.getCreatedDate());
        soapModel.setCreatedBy(model.getCreatedBy());
        soapModel.setSource(model.getSource());
        soapModel.setBatchId(model.getBatchId());
        soapModel.setContractPrice3(model.getContractPrice3());
        soapModel.setModifiedBy(model.getModifiedBy());
        soapModel.setInboundStatus(model.getInboundStatus());
        soapModel.setFormulaId(model.getFormulaId());

        return soapModel;
    }

    public static FormulaDetailsMasterSoap[] toSoapModels(
        FormulaDetailsMaster[] models) {
        FormulaDetailsMasterSoap[] soapModels = new FormulaDetailsMasterSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static FormulaDetailsMasterSoap[][] toSoapModels(
        FormulaDetailsMaster[][] models) {
        FormulaDetailsMasterSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new FormulaDetailsMasterSoap[models.length][models[0].length];
        } else {
            soapModels = new FormulaDetailsMasterSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static FormulaDetailsMasterSoap[] toSoapModels(
        List<FormulaDetailsMaster> models) {
        List<FormulaDetailsMasterSoap> soapModels = new ArrayList<FormulaDetailsMasterSoap>(models.size());

        for (FormulaDetailsMaster model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new FormulaDetailsMasterSoap[soapModels.size()]);
    }

    public int getPrimaryKey() {
        return _formulaDetailsMasterSid;
    }

    public void setPrimaryKey(int pk) {
        setFormulaDetailsMasterSid(pk);
    }

    public String getCompanyId() {
        return _companyId;
    }

    public void setCompanyId(String companyId) {
        _companyId = companyId;
    }

    public double getContractPrice1() {
        return _contractPrice1;
    }

    public void setContractPrice1(double contractPrice1) {
        _contractPrice1 = contractPrice1;
    }

    public double getContractPrice2() {
        return _contractPrice2;
    }

    public void setContractPrice2(double contractPrice2) {
        _contractPrice2 = contractPrice2;
    }

    public Date getEndDate() {
        return _endDate;
    }

    public void setEndDate(Date endDate) {
        _endDate = endDate;
    }

    public String getFormulaNo() {
        return _formulaNo;
    }

    public void setFormulaNo(String formulaNo) {
        _formulaNo = formulaNo;
    }

    public int getFormulaDetailsMasterSid() {
        return _formulaDetailsMasterSid;
    }

    public void setFormulaDetailsMasterSid(int formulaDetailsMasterSid) {
        _formulaDetailsMasterSid = formulaDetailsMasterSid;
    }

    public String getItemId() {
        return _itemId;
    }

    public void setItemId(String itemId) {
        _itemId = itemId;
    }

    public double getRebatePercent1() {
        return _rebatePercent1;
    }

    public void setRebatePercent1(double rebatePercent1) {
        _rebatePercent1 = rebatePercent1;
    }

    public Date getModifiedDate() {
        return _modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        _modifiedDate = modifiedDate;
    }

    public String getFormulaDesc() {
        return _formulaDesc;
    }

    public void setFormulaDesc(String formulaDesc) {
        _formulaDesc = formulaDesc;
    }

    public double getRebatePercent2() {
        return _rebatePercent2;
    }

    public void setRebatePercent2(double rebatePercent2) {
        _rebatePercent2 = rebatePercent2;
    }

    public double getRebatePercent3() {
        return _rebatePercent3;
    }

    public void setRebatePercent3(double rebatePercent3) {
        _rebatePercent3 = rebatePercent3;
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

    public Date getStartDate() {
        return _startDate;
    }

    public void setStartDate(Date startDate) {
        _startDate = startDate;
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

    public String getSource() {
        return _source;
    }

    public void setSource(String source) {
        _source = source;
    }

    public String getBatchId() {
        return _batchId;
    }

    public void setBatchId(String batchId) {
        _batchId = batchId;
    }

    public double getContractPrice3() {
        return _contractPrice3;
    }

    public void setContractPrice3(double contractPrice3) {
        _contractPrice3 = contractPrice3;
    }

    public int getModifiedBy() {
        return _modifiedBy;
    }

    public void setModifiedBy(int modifiedBy) {
        _modifiedBy = modifiedBy;
    }

    public String getInboundStatus() {
        return _inboundStatus;
    }

    public void setInboundStatus(String inboundStatus) {
        _inboundStatus = inboundStatus;
    }

    public String getFormulaId() {
        return _formulaId;
    }

    public void setFormulaId(String formulaId) {
        _formulaId = formulaId;
    }
}
