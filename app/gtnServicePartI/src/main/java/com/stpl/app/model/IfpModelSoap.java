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
public class IfpModelSoap implements Serializable {
    private int _modifiedBy;
    private String _totalDollarCommitment;
    private Date _createdDate;
    private int _ifpStatus;
    private String _totalVolumeCommitment;
    private String _batchId;
    private String _internalNotes;
    private String _ifpId;
    private String _totalMarketshareCommitment;
    private int _ifpCategory;
    private String _parentIfpName;
    private Date _ifpEndDate;
    private String _ifpDesignation;
    private int _createdBy;
    private Date _ifpStartDate;
    private String _parentIfpId;
    private String _commitmentPeriod;
    private int _ifpType;
    private Date _modifiedDate;
    private int _ifpModelSid;
    private boolean _recordLockStatus;
    private String _source;
    private String _ifpName;
    private String _ifpNo;
    private String _inboundStatus;

    public IfpModelSoap() {
    }

    public static IfpModelSoap toSoapModel(IfpModel model) {
        IfpModelSoap soapModel = new IfpModelSoap();

        soapModel.setModifiedBy(model.getModifiedBy());
        soapModel.setTotalDollarCommitment(model.getTotalDollarCommitment());
        soapModel.setCreatedDate(model.getCreatedDate());
        soapModel.setIfpStatus(model.getIfpStatus());
        soapModel.setTotalVolumeCommitment(model.getTotalVolumeCommitment());
        soapModel.setBatchId(model.getBatchId());
        soapModel.setInternalNotes(model.getInternalNotes());
        soapModel.setIfpId(model.getIfpId());
        soapModel.setTotalMarketshareCommitment(model.getTotalMarketshareCommitment());
        soapModel.setIfpCategory(model.getIfpCategory());
        soapModel.setParentIfpName(model.getParentIfpName());
        soapModel.setIfpEndDate(model.getIfpEndDate());
        soapModel.setIfpDesignation(model.getIfpDesignation());
        soapModel.setCreatedBy(model.getCreatedBy());
        soapModel.setIfpStartDate(model.getIfpStartDate());
        soapModel.setParentIfpId(model.getParentIfpId());
        soapModel.setCommitmentPeriod(model.getCommitmentPeriod());
        soapModel.setIfpType(model.getIfpType());
        soapModel.setModifiedDate(model.getModifiedDate());
        soapModel.setIfpModelSid(model.getIfpModelSid());
        soapModel.setRecordLockStatus(model.getRecordLockStatus());
        soapModel.setSource(model.getSource());
        soapModel.setIfpName(model.getIfpName());
        soapModel.setIfpNo(model.getIfpNo());
        soapModel.setInboundStatus(model.getInboundStatus());

        return soapModel;
    }

    public static IfpModelSoap[] toSoapModels(IfpModel[] models) {
        IfpModelSoap[] soapModels = new IfpModelSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static IfpModelSoap[][] toSoapModels(IfpModel[][] models) {
        IfpModelSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new IfpModelSoap[models.length][models[0].length];
        } else {
            soapModels = new IfpModelSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static IfpModelSoap[] toSoapModels(List<IfpModel> models) {
        List<IfpModelSoap> soapModels = new ArrayList<IfpModelSoap>(models.size());

        for (IfpModel model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new IfpModelSoap[soapModels.size()]);
    }

    public int getPrimaryKey() {
        return _ifpModelSid;
    }

    public void setPrimaryKey(int pk) {
        setIfpModelSid(pk);
    }

    public int getModifiedBy() {
        return _modifiedBy;
    }

    public void setModifiedBy(int modifiedBy) {
        _modifiedBy = modifiedBy;
    }

    public String getTotalDollarCommitment() {
        return _totalDollarCommitment;
    }

    public void setTotalDollarCommitment(String totalDollarCommitment) {
        _totalDollarCommitment = totalDollarCommitment;
    }

    public Date getCreatedDate() {
        return _createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        _createdDate = createdDate;
    }

    public int getIfpStatus() {
        return _ifpStatus;
    }

    public void setIfpStatus(int ifpStatus) {
        _ifpStatus = ifpStatus;
    }

    public String getTotalVolumeCommitment() {
        return _totalVolumeCommitment;
    }

    public void setTotalVolumeCommitment(String totalVolumeCommitment) {
        _totalVolumeCommitment = totalVolumeCommitment;
    }

    public String getBatchId() {
        return _batchId;
    }

    public void setBatchId(String batchId) {
        _batchId = batchId;
    }

    public String getInternalNotes() {
        return _internalNotes;
    }

    public void setInternalNotes(String internalNotes) {
        _internalNotes = internalNotes;
    }

    public String getIfpId() {
        return _ifpId;
    }

    public void setIfpId(String ifpId) {
        _ifpId = ifpId;
    }

    public String getTotalMarketshareCommitment() {
        return _totalMarketshareCommitment;
    }

    public void setTotalMarketshareCommitment(String totalMarketshareCommitment) {
        _totalMarketshareCommitment = totalMarketshareCommitment;
    }

    public int getIfpCategory() {
        return _ifpCategory;
    }

    public void setIfpCategory(int ifpCategory) {
        _ifpCategory = ifpCategory;
    }

    public String getParentIfpName() {
        return _parentIfpName;
    }

    public void setParentIfpName(String parentIfpName) {
        _parentIfpName = parentIfpName;
    }

    public Date getIfpEndDate() {
        return _ifpEndDate;
    }

    public void setIfpEndDate(Date ifpEndDate) {
        _ifpEndDate = ifpEndDate;
    }

    public String getIfpDesignation() {
        return _ifpDesignation;
    }

    public void setIfpDesignation(String ifpDesignation) {
        _ifpDesignation = ifpDesignation;
    }

    public int getCreatedBy() {
        return _createdBy;
    }

    public void setCreatedBy(int createdBy) {
        _createdBy = createdBy;
    }

    public Date getIfpStartDate() {
        return _ifpStartDate;
    }

    public void setIfpStartDate(Date ifpStartDate) {
        _ifpStartDate = ifpStartDate;
    }

    public String getParentIfpId() {
        return _parentIfpId;
    }

    public void setParentIfpId(String parentIfpId) {
        _parentIfpId = parentIfpId;
    }

    public String getCommitmentPeriod() {
        return _commitmentPeriod;
    }

    public void setCommitmentPeriod(String commitmentPeriod) {
        _commitmentPeriod = commitmentPeriod;
    }

    public int getIfpType() {
        return _ifpType;
    }

    public void setIfpType(int ifpType) {
        _ifpType = ifpType;
    }

    public Date getModifiedDate() {
        return _modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        _modifiedDate = modifiedDate;
    }

    public int getIfpModelSid() {
        return _ifpModelSid;
    }

    public void setIfpModelSid(int ifpModelSid) {
        _ifpModelSid = ifpModelSid;
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

    public String getIfpName() {
        return _ifpName;
    }

    public void setIfpName(String ifpName) {
        _ifpName = ifpName;
    }

    public String getIfpNo() {
        return _ifpNo;
    }

    public void setIfpNo(String ifpNo) {
        _ifpNo = ifpNo;
    }

    public String getInboundStatus() {
        return _inboundStatus;
    }

    public void setInboundStatus(String inboundStatus) {
        _inboundStatus = inboundStatus;
    }
}
