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
public class CfpDetailsSoap implements Serializable {
    private int _createdBy;
    private int _companyCfpAttachedStatus;
    private int _tradeClass;
    private Date _tradeClassEndDate;
    private int _modifiedBy;
    private Date _companyStartDate;
    private Date _tradeClassStartDate;
    private Date _createdDate;
    private Date _companyEndDate;
    private int _companyMasterSid;
    private Date _companyCfpAttachedDate;
    private int _cfpModelSid;
    private String _batchId;
    private int _cfpDetailsSid;
    private Date _modifiedDate;
    private boolean _recordLockStatus;
    private String _source;
    private String _inboundStatus;

    public CfpDetailsSoap() {
    }

    public static CfpDetailsSoap toSoapModel(CfpDetails model) {
        CfpDetailsSoap soapModel = new CfpDetailsSoap();

        soapModel.setCreatedBy(model.getCreatedBy());
        soapModel.setCompanyCfpAttachedStatus(model.getCompanyCfpAttachedStatus());
        soapModel.setTradeClass(model.getTradeClass());
        soapModel.setTradeClassEndDate(model.getTradeClassEndDate());
        soapModel.setModifiedBy(model.getModifiedBy());
        soapModel.setCompanyStartDate(model.getCompanyStartDate());
        soapModel.setTradeClassStartDate(model.getTradeClassStartDate());
        soapModel.setCreatedDate(model.getCreatedDate());
        soapModel.setCompanyEndDate(model.getCompanyEndDate());
        soapModel.setCompanyMasterSid(model.getCompanyMasterSid());
        soapModel.setCompanyCfpAttachedDate(model.getCompanyCfpAttachedDate());
        soapModel.setCfpModelSid(model.getCfpModelSid());
        soapModel.setBatchId(model.getBatchId());
        soapModel.setCfpDetailsSid(model.getCfpDetailsSid());
        soapModel.setModifiedDate(model.getModifiedDate());
        soapModel.setRecordLockStatus(model.getRecordLockStatus());
        soapModel.setSource(model.getSource());
        soapModel.setInboundStatus(model.getInboundStatus());

        return soapModel;
    }

    public static CfpDetailsSoap[] toSoapModels(CfpDetails[] models) {
        CfpDetailsSoap[] soapModels = new CfpDetailsSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static CfpDetailsSoap[][] toSoapModels(CfpDetails[][] models) {
        CfpDetailsSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new CfpDetailsSoap[models.length][models[0].length];
        } else {
            soapModels = new CfpDetailsSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static CfpDetailsSoap[] toSoapModels(List<CfpDetails> models) {
        List<CfpDetailsSoap> soapModels = new ArrayList<CfpDetailsSoap>(models.size());

        for (CfpDetails model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new CfpDetailsSoap[soapModels.size()]);
    }

    public int getPrimaryKey() {
        return _cfpDetailsSid;
    }

    public void setPrimaryKey(int pk) {
        setCfpDetailsSid(pk);
    }

    public int getCreatedBy() {
        return _createdBy;
    }

    public void setCreatedBy(int createdBy) {
        _createdBy = createdBy;
    }

    public int getCompanyCfpAttachedStatus() {
        return _companyCfpAttachedStatus;
    }

    public void setCompanyCfpAttachedStatus(int companyCfpAttachedStatus) {
        _companyCfpAttachedStatus = companyCfpAttachedStatus;
    }

    public int getTradeClass() {
        return _tradeClass;
    }

    public void setTradeClass(int tradeClass) {
        _tradeClass = tradeClass;
    }

    public Date getTradeClassEndDate() {
        return _tradeClassEndDate;
    }

    public void setTradeClassEndDate(Date tradeClassEndDate) {
        _tradeClassEndDate = tradeClassEndDate;
    }

    public int getModifiedBy() {
        return _modifiedBy;
    }

    public void setModifiedBy(int modifiedBy) {
        _modifiedBy = modifiedBy;
    }

    public Date getCompanyStartDate() {
        return _companyStartDate;
    }

    public void setCompanyStartDate(Date companyStartDate) {
        _companyStartDate = companyStartDate;
    }

    public Date getTradeClassStartDate() {
        return _tradeClassStartDate;
    }

    public void setTradeClassStartDate(Date tradeClassStartDate) {
        _tradeClassStartDate = tradeClassStartDate;
    }

    public Date getCreatedDate() {
        return _createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        _createdDate = createdDate;
    }

    public Date getCompanyEndDate() {
        return _companyEndDate;
    }

    public void setCompanyEndDate(Date companyEndDate) {
        _companyEndDate = companyEndDate;
    }

    public int getCompanyMasterSid() {
        return _companyMasterSid;
    }

    public void setCompanyMasterSid(int companyMasterSid) {
        _companyMasterSid = companyMasterSid;
    }

    public Date getCompanyCfpAttachedDate() {
        return _companyCfpAttachedDate;
    }

    public void setCompanyCfpAttachedDate(Date companyCfpAttachedDate) {
        _companyCfpAttachedDate = companyCfpAttachedDate;
    }

    public int getCfpModelSid() {
        return _cfpModelSid;
    }

    public void setCfpModelSid(int cfpModelSid) {
        _cfpModelSid = cfpModelSid;
    }

    public String getBatchId() {
        return _batchId;
    }

    public void setBatchId(String batchId) {
        _batchId = batchId;
    }

    public int getCfpDetailsSid() {
        return _cfpDetailsSid;
    }

    public void setCfpDetailsSid(int cfpDetailsSid) {
        _cfpDetailsSid = cfpDetailsSid;
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

    public String getInboundStatus() {
        return _inboundStatus;
    }

    public void setInboundStatus(String inboundStatus) {
        _inboundStatus = inboundStatus;
    }
}
