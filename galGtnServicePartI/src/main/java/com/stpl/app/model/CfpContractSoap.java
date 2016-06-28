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
public class CfpContractSoap implements Serializable {
    private int _createdBy;
    private int _cfpContractSid;
    private int _cfpType;
    private int _cfpTradeClass;
    private int _modifiedBy;
    private Date _createdDate;
    private int _contractMasterSid;
    private Date _cfpContractAttachedDate;
    private int _cfpModelSid;
    private String _batchId;
    private Date _modifiedDate;
    private boolean _recordLockStatus;
    private String _cfpDesignation;
    private String _cfpName;
    private int _cfpCategory;
    private String _source;
    private int _cfpStatus;
    private int _parentCfpId;
    private int _cfpContractAttachedStatus;
    private Date _cfpStartDate;
    private Date _cfpEndDate;
    private String _parentCfpName;
    private String _inboundStatus;
    private int _salesInclusion;

    public CfpContractSoap() {
    }

    public static CfpContractSoap toSoapModel(CfpContract model) {
        CfpContractSoap soapModel = new CfpContractSoap();

        soapModel.setCreatedBy(model.getCreatedBy());
        soapModel.setCfpContractSid(model.getCfpContractSid());
        soapModel.setCfpType(model.getCfpType());
        soapModel.setCfpTradeClass(model.getCfpTradeClass());
        soapModel.setModifiedBy(model.getModifiedBy());
        soapModel.setCreatedDate(model.getCreatedDate());
        soapModel.setContractMasterSid(model.getContractMasterSid());
        soapModel.setCfpContractAttachedDate(model.getCfpContractAttachedDate());
        soapModel.setCfpModelSid(model.getCfpModelSid());
        soapModel.setBatchId(model.getBatchId());
        soapModel.setModifiedDate(model.getModifiedDate());
        soapModel.setRecordLockStatus(model.getRecordLockStatus());
        soapModel.setCfpDesignation(model.getCfpDesignation());
        soapModel.setCfpName(model.getCfpName());
        soapModel.setCfpCategory(model.getCfpCategory());
        soapModel.setSource(model.getSource());
        soapModel.setCfpStatus(model.getCfpStatus());
        soapModel.setParentCfpId(model.getParentCfpId());
        soapModel.setCfpContractAttachedStatus(model.getCfpContractAttachedStatus());
        soapModel.setCfpStartDate(model.getCfpStartDate());
        soapModel.setCfpEndDate(model.getCfpEndDate());
        soapModel.setParentCfpName(model.getParentCfpName());
        soapModel.setInboundStatus(model.getInboundStatus());
        soapModel.setSalesInclusion(model.getSalesInclusion());

        return soapModel;
    }

    public static CfpContractSoap[] toSoapModels(CfpContract[] models) {
        CfpContractSoap[] soapModels = new CfpContractSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static CfpContractSoap[][] toSoapModels(CfpContract[][] models) {
        CfpContractSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new CfpContractSoap[models.length][models[0].length];
        } else {
            soapModels = new CfpContractSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static CfpContractSoap[] toSoapModels(List<CfpContract> models) {
        List<CfpContractSoap> soapModels = new ArrayList<CfpContractSoap>(models.size());

        for (CfpContract model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new CfpContractSoap[soapModels.size()]);
    }

    public int getPrimaryKey() {
        return _cfpContractSid;
    }

    public void setPrimaryKey(int pk) {
        setCfpContractSid(pk);
    }

    public int getCreatedBy() {
        return _createdBy;
    }

    public void setCreatedBy(int createdBy) {
        _createdBy = createdBy;
    }

    public int getCfpContractSid() {
        return _cfpContractSid;
    }

    public void setCfpContractSid(int cfpContractSid) {
        _cfpContractSid = cfpContractSid;
    }

    public int getCfpType() {
        return _cfpType;
    }

    public void setCfpType(int cfpType) {
        _cfpType = cfpType;
    }

    public int getCfpTradeClass() {
        return _cfpTradeClass;
    }

    public void setCfpTradeClass(int cfpTradeClass) {
        _cfpTradeClass = cfpTradeClass;
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

    public int getContractMasterSid() {
        return _contractMasterSid;
    }

    public void setContractMasterSid(int contractMasterSid) {
        _contractMasterSid = contractMasterSid;
    }

    public Date getCfpContractAttachedDate() {
        return _cfpContractAttachedDate;
    }

    public void setCfpContractAttachedDate(Date cfpContractAttachedDate) {
        _cfpContractAttachedDate = cfpContractAttachedDate;
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

    public String getCfpDesignation() {
        return _cfpDesignation;
    }

    public void setCfpDesignation(String cfpDesignation) {
        _cfpDesignation = cfpDesignation;
    }

    public String getCfpName() {
        return _cfpName;
    }

    public void setCfpName(String cfpName) {
        _cfpName = cfpName;
    }

    public int getCfpCategory() {
        return _cfpCategory;
    }

    public void setCfpCategory(int cfpCategory) {
        _cfpCategory = cfpCategory;
    }

    public String getSource() {
        return _source;
    }

    public void setSource(String source) {
        _source = source;
    }

    public int getCfpStatus() {
        return _cfpStatus;
    }

    public void setCfpStatus(int cfpStatus) {
        _cfpStatus = cfpStatus;
    }

    public int getParentCfpId() {
        return _parentCfpId;
    }

    public void setParentCfpId(int parentCfpId) {
        _parentCfpId = parentCfpId;
    }

    public int getCfpContractAttachedStatus() {
        return _cfpContractAttachedStatus;
    }

    public void setCfpContractAttachedStatus(int cfpContractAttachedStatus) {
        _cfpContractAttachedStatus = cfpContractAttachedStatus;
    }

    public Date getCfpStartDate() {
        return _cfpStartDate;
    }

    public void setCfpStartDate(Date cfpStartDate) {
        _cfpStartDate = cfpStartDate;
    }

    public Date getCfpEndDate() {
        return _cfpEndDate;
    }

    public void setCfpEndDate(Date cfpEndDate) {
        _cfpEndDate = cfpEndDate;
    }

    public String getParentCfpName() {
        return _parentCfpName;
    }

    public void setParentCfpName(String parentCfpName) {
        _parentCfpName = parentCfpName;
    }

    public String getInboundStatus() {
        return _inboundStatus;
    }

    public void setInboundStatus(String inboundStatus) {
        _inboundStatus = inboundStatus;
    }

    public int getSalesInclusion() {
        return _salesInclusion;
    }

    public void setSalesInclusion(int salesInclusion) {
        _salesInclusion = salesInclusion;
    }
}
