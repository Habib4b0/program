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
public class NetSalesFormulaMasterSoap implements Serializable {
    private int _createdBy;
    private int _netSalesFormulaMasterSid;
    private int _modifiedBy;
    private Date _createdDate;
    private String _netSalesFormulaName;
    private int _netSalesFormulaType;
    private int _netSalesFormulaCategory;
    private String _contractSelection;
    private Date _modifiedDate;
    private boolean _recordLockStatus;
    private String _source;
    private String _netSalesFormulaId;
    private String _netSalesFormulaNo;
    private String _inboundStatus;
    private String _cdrModelSid;

    public NetSalesFormulaMasterSoap() {
    }

    public static NetSalesFormulaMasterSoap toSoapModel(
        NetSalesFormulaMaster model) {
        NetSalesFormulaMasterSoap soapModel = new NetSalesFormulaMasterSoap();

        soapModel.setCreatedBy(model.getCreatedBy());
        soapModel.setNetSalesFormulaMasterSid(model.getNetSalesFormulaMasterSid());
        soapModel.setModifiedBy(model.getModifiedBy());
        soapModel.setCreatedDate(model.getCreatedDate());
        soapModel.setNetSalesFormulaName(model.getNetSalesFormulaName());
        soapModel.setNetSalesFormulaType(model.getNetSalesFormulaType());
        soapModel.setNetSalesFormulaCategory(model.getNetSalesFormulaCategory());
        soapModel.setContractSelection(model.getContractSelection());
        soapModel.setModifiedDate(model.getModifiedDate());
        soapModel.setRecordLockStatus(model.getRecordLockStatus());
        soapModel.setSource(model.getSource());
        soapModel.setNetSalesFormulaId(model.getNetSalesFormulaId());
        soapModel.setNetSalesFormulaNo(model.getNetSalesFormulaNo());
        soapModel.setInboundStatus(model.getInboundStatus());
        soapModel.setCdrModelSid(model.getCdrModelSid());

        return soapModel;
    }

    public static NetSalesFormulaMasterSoap[] toSoapModels(
        NetSalesFormulaMaster[] models) {
        NetSalesFormulaMasterSoap[] soapModels = new NetSalesFormulaMasterSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static NetSalesFormulaMasterSoap[][] toSoapModels(
        NetSalesFormulaMaster[][] models) {
        NetSalesFormulaMasterSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new NetSalesFormulaMasterSoap[models.length][models[0].length];
        } else {
            soapModels = new NetSalesFormulaMasterSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static NetSalesFormulaMasterSoap[] toSoapModels(
        List<NetSalesFormulaMaster> models) {
        List<NetSalesFormulaMasterSoap> soapModels = new ArrayList<NetSalesFormulaMasterSoap>(models.size());

        for (NetSalesFormulaMaster model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new NetSalesFormulaMasterSoap[soapModels.size()]);
    }

    public int getPrimaryKey() {
        return _netSalesFormulaMasterSid;
    }

    public void setPrimaryKey(int pk) {
        setNetSalesFormulaMasterSid(pk);
    }

    public int getCreatedBy() {
        return _createdBy;
    }

    public void setCreatedBy(int createdBy) {
        _createdBy = createdBy;
    }

    public int getNetSalesFormulaMasterSid() {
        return _netSalesFormulaMasterSid;
    }

    public void setNetSalesFormulaMasterSid(int netSalesFormulaMasterSid) {
        _netSalesFormulaMasterSid = netSalesFormulaMasterSid;
    }

    public int getModifiedBy() {
        return _modifiedBy;
    }

    public void setModifiedBy(int modifiedBy) {
        _modifiedBy = modifiedBy;
    }

    public Date getCreatedDate() {
        return _createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        _createdDate = createdDate;
    }

    public String getNetSalesFormulaName() {
        return _netSalesFormulaName;
    }

    public void setNetSalesFormulaName(String netSalesFormulaName) {
        _netSalesFormulaName = netSalesFormulaName;
    }

    public int getNetSalesFormulaType() {
        return _netSalesFormulaType;
    }

    public void setNetSalesFormulaType(int netSalesFormulaType) {
        _netSalesFormulaType = netSalesFormulaType;
    }

    public int getNetSalesFormulaCategory() {
        return _netSalesFormulaCategory;
    }

    public void setNetSalesFormulaCategory(int netSalesFormulaCategory) {
        _netSalesFormulaCategory = netSalesFormulaCategory;
    }

    public String getContractSelection() {
        return _contractSelection;
    }

    public void setContractSelection(String contractSelection) {
        _contractSelection = contractSelection;
    }

    public Date getModifiedDate() {
        return _modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        _modifiedDate = modifiedDate;
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

    public String getSource() {
        return _source;
    }

    public void setSource(String source) {
        _source = source;
    }

    public String getNetSalesFormulaId() {
        return _netSalesFormulaId;
    }

    public void setNetSalesFormulaId(String netSalesFormulaId) {
        _netSalesFormulaId = netSalesFormulaId;
    }

    public String getNetSalesFormulaNo() {
        return _netSalesFormulaNo;
    }

    public void setNetSalesFormulaNo(String netSalesFormulaNo) {
        _netSalesFormulaNo = netSalesFormulaNo;
    }

    public String getInboundStatus() {
        return _inboundStatus;
    }

    public void setInboundStatus(String inboundStatus) {
        _inboundStatus = inboundStatus;
    }

    public String getCdrModelSid() {
        return _cdrModelSid;
    }

    public void setCdrModelSid(String cdrModelSid) {
        _cdrModelSid = cdrModelSid;
    }
}
