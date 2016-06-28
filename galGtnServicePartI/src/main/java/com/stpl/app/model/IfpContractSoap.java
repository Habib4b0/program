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
public class IfpContractSoap implements Serializable {
    private String _cfpContractSid;
    private String _parentIfpName;
    private Date _ifpContractAttachedDate;
    private int _ifpStatus;
    private Date _ifpStartDate;
    private int _ifpContractAttachedStatus;
    private Date _modifiedDate;
    private int _ifpCategory;
    private boolean _recordLockStatus;
    private Date _ifpEndDate;
    private Date _createdDate;
    private int _createdBy;
    private String _source;
    private String _ifpDesignation;
    private String _parentIfpId;
    private String _batchId;
    private int _contractMasterSid;
    private int _ifpType;
    private String _ifpName;
    private int _modifiedBy;
    private String _inboundStatus;
    private int _ifpContractSid;
    private int _ifpModelSid;

    public IfpContractSoap() {
    }

    public static IfpContractSoap toSoapModel(IfpContract model) {
        IfpContractSoap soapModel = new IfpContractSoap();

        soapModel.setCfpContractSid(model.getCfpContractSid());
        soapModel.setParentIfpName(model.getParentIfpName());
        soapModel.setIfpContractAttachedDate(model.getIfpContractAttachedDate());
        soapModel.setIfpStatus(model.getIfpStatus());
        soapModel.setIfpStartDate(model.getIfpStartDate());
        soapModel.setIfpContractAttachedStatus(model.getIfpContractAttachedStatus());
        soapModel.setModifiedDate(model.getModifiedDate());
        soapModel.setIfpCategory(model.getIfpCategory());
        soapModel.setRecordLockStatus(model.getRecordLockStatus());
        soapModel.setIfpEndDate(model.getIfpEndDate());
        soapModel.setCreatedDate(model.getCreatedDate());
        soapModel.setCreatedBy(model.getCreatedBy());
        soapModel.setSource(model.getSource());
        soapModel.setIfpDesignation(model.getIfpDesignation());
        soapModel.setParentIfpId(model.getParentIfpId());
        soapModel.setBatchId(model.getBatchId());
        soapModel.setContractMasterSid(model.getContractMasterSid());
        soapModel.setIfpType(model.getIfpType());
        soapModel.setIfpName(model.getIfpName());
        soapModel.setModifiedBy(model.getModifiedBy());
        soapModel.setInboundStatus(model.getInboundStatus());
        soapModel.setIfpContractSid(model.getIfpContractSid());
        soapModel.setIfpModelSid(model.getIfpModelSid());

        return soapModel;
    }

    public static IfpContractSoap[] toSoapModels(IfpContract[] models) {
        IfpContractSoap[] soapModels = new IfpContractSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static IfpContractSoap[][] toSoapModels(IfpContract[][] models) {
        IfpContractSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new IfpContractSoap[models.length][models[0].length];
        } else {
            soapModels = new IfpContractSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static IfpContractSoap[] toSoapModels(List<IfpContract> models) {
        List<IfpContractSoap> soapModels = new ArrayList<IfpContractSoap>(models.size());

        for (IfpContract model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new IfpContractSoap[soapModels.size()]);
    }

    public int getPrimaryKey() {
        return _ifpContractSid;
    }

    public void setPrimaryKey(int pk) {
        setIfpContractSid(pk);
    }

    public String getCfpContractSid() {
        return _cfpContractSid;
    }

    public void setCfpContractSid(String cfpContractSid) {
        _cfpContractSid = cfpContractSid;
    }

    public String getParentIfpName() {
        return _parentIfpName;
    }

    public void setParentIfpName(String parentIfpName) {
        _parentIfpName = parentIfpName;
    }

    public Date getIfpContractAttachedDate() {
        return _ifpContractAttachedDate;
    }

    public void setIfpContractAttachedDate(Date ifpContractAttachedDate) {
        _ifpContractAttachedDate = ifpContractAttachedDate;
    }

    public int getIfpStatus() {
        return _ifpStatus;
    }

    public void setIfpStatus(int ifpStatus) {
        _ifpStatus = ifpStatus;
    }

    public Date getIfpStartDate() {
        return _ifpStartDate;
    }

    public void setIfpStartDate(Date ifpStartDate) {
        _ifpStartDate = ifpStartDate;
    }

    public int getIfpContractAttachedStatus() {
        return _ifpContractAttachedStatus;
    }

    public void setIfpContractAttachedStatus(int ifpContractAttachedStatus) {
        _ifpContractAttachedStatus = ifpContractAttachedStatus;
    }

    public Date getModifiedDate() {
        return _modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        _modifiedDate = modifiedDate;
    }

    public int getIfpCategory() {
        return _ifpCategory;
    }

    public void setIfpCategory(int ifpCategory) {
        _ifpCategory = ifpCategory;
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

    public Date getIfpEndDate() {
        return _ifpEndDate;
    }

    public void setIfpEndDate(Date ifpEndDate) {
        _ifpEndDate = ifpEndDate;
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

    public String getIfpDesignation() {
        return _ifpDesignation;
    }

    public void setIfpDesignation(String ifpDesignation) {
        _ifpDesignation = ifpDesignation;
    }

    public String getParentIfpId() {
        return _parentIfpId;
    }

    public void setParentIfpId(String parentIfpId) {
        _parentIfpId = parentIfpId;
    }

    public String getBatchId() {
        return _batchId;
    }

    public void setBatchId(String batchId) {
        _batchId = batchId;
    }

    public int getContractMasterSid() {
        return _contractMasterSid;
    }

    public void setContractMasterSid(int contractMasterSid) {
        _contractMasterSid = contractMasterSid;
    }

    public int getIfpType() {
        return _ifpType;
    }

    public void setIfpType(int ifpType) {
        _ifpType = ifpType;
    }

    public String getIfpName() {
        return _ifpName;
    }

    public void setIfpName(String ifpName) {
        _ifpName = ifpName;
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

    public int getIfpContractSid() {
        return _ifpContractSid;
    }

    public void setIfpContractSid(int ifpContractSid) {
        _ifpContractSid = ifpContractSid;
    }

    public int getIfpModelSid() {
        return _ifpModelSid;
    }

    public void setIfpModelSid(int ifpModelSid) {
        _ifpModelSid = ifpModelSid;
    }
}
