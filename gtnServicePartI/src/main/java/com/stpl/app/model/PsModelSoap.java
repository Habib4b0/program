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
public class PsModelSoap implements Serializable {
    private String _psId;
    private String _psName;
    private int _psType;
    private Date _modifiedDate;
    private int _psCategory;
    private boolean _recordLockStatus;
    private int _psStatus;
    private Date _createdDate;
    private int _createdBy;
    private String _source;
    private String _psNo;
    private String _psDesignation;
    private String _parentPsId;
    private String _batchId;
    private int _psModelSid;
    private Date _psEndDate;
    private int _psTradeClass;
    private int _modifiedBy;
    private String _inboundStatus;
    private Date _psStartDate;
    private String _parentPsName;
    private String _internalNotes;

    public PsModelSoap() {
    }

    public static PsModelSoap toSoapModel(PsModel model) {
        PsModelSoap soapModel = new PsModelSoap();

        soapModel.setPsId(model.getPsId());
        soapModel.setPsName(model.getPsName());
        soapModel.setPsType(model.getPsType());
        soapModel.setModifiedDate(model.getModifiedDate());
        soapModel.setPsCategory(model.getPsCategory());
        soapModel.setRecordLockStatus(model.getRecordLockStatus());
        soapModel.setPsStatus(model.getPsStatus());
        soapModel.setCreatedDate(model.getCreatedDate());
        soapModel.setCreatedBy(model.getCreatedBy());
        soapModel.setSource(model.getSource());
        soapModel.setPsNo(model.getPsNo());
        soapModel.setPsDesignation(model.getPsDesignation());
        soapModel.setParentPsId(model.getParentPsId());
        soapModel.setBatchId(model.getBatchId());
        soapModel.setPsModelSid(model.getPsModelSid());
        soapModel.setPsEndDate(model.getPsEndDate());
        soapModel.setPsTradeClass(model.getPsTradeClass());
        soapModel.setModifiedBy(model.getModifiedBy());
        soapModel.setInboundStatus(model.getInboundStatus());
        soapModel.setPsStartDate(model.getPsStartDate());
        soapModel.setParentPsName(model.getParentPsName());
        soapModel.setInternalNotes(model.getInternalNotes());

        return soapModel;
    }

    public static PsModelSoap[] toSoapModels(PsModel[] models) {
        PsModelSoap[] soapModels = new PsModelSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static PsModelSoap[][] toSoapModels(PsModel[][] models) {
        PsModelSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new PsModelSoap[models.length][models[0].length];
        } else {
            soapModels = new PsModelSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static PsModelSoap[] toSoapModels(List<PsModel> models) {
        List<PsModelSoap> soapModels = new ArrayList<PsModelSoap>(models.size());

        for (PsModel model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new PsModelSoap[soapModels.size()]);
    }

    public int getPrimaryKey() {
        return _psModelSid;
    }

    public void setPrimaryKey(int pk) {
        setPsModelSid(pk);
    }

    public String getPsId() {
        return _psId;
    }

    public void setPsId(String psId) {
        _psId = psId;
    }

    public String getPsName() {
        return _psName;
    }

    public void setPsName(String psName) {
        _psName = psName;
    }

    public int getPsType() {
        return _psType;
    }

    public void setPsType(int psType) {
        _psType = psType;
    }

    public Date getModifiedDate() {
        return _modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        _modifiedDate = modifiedDate;
    }

    public int getPsCategory() {
        return _psCategory;
    }

    public void setPsCategory(int psCategory) {
        _psCategory = psCategory;
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

    public int getPsStatus() {
        return _psStatus;
    }

    public void setPsStatus(int psStatus) {
        _psStatus = psStatus;
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

    public String getPsNo() {
        return _psNo;
    }

    public void setPsNo(String psNo) {
        _psNo = psNo;
    }

    public String getPsDesignation() {
        return _psDesignation;
    }

    public void setPsDesignation(String psDesignation) {
        _psDesignation = psDesignation;
    }

    public String getParentPsId() {
        return _parentPsId;
    }

    public void setParentPsId(String parentPsId) {
        _parentPsId = parentPsId;
    }

    public String getBatchId() {
        return _batchId;
    }

    public void setBatchId(String batchId) {
        _batchId = batchId;
    }

    public int getPsModelSid() {
        return _psModelSid;
    }

    public void setPsModelSid(int psModelSid) {
        _psModelSid = psModelSid;
    }

    public Date getPsEndDate() {
        return _psEndDate;
    }

    public void setPsEndDate(Date psEndDate) {
        _psEndDate = psEndDate;
    }

    public int getPsTradeClass() {
        return _psTradeClass;
    }

    public void setPsTradeClass(int psTradeClass) {
        _psTradeClass = psTradeClass;
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

    public Date getPsStartDate() {
        return _psStartDate;
    }

    public void setPsStartDate(Date psStartDate) {
        _psStartDate = psStartDate;
    }

    public String getParentPsName() {
        return _parentPsName;
    }

    public void setParentPsName(String parentPsName) {
        _parentPsName = parentPsName;
    }

    public String getInternalNotes() {
        return _internalNotes;
    }

    public void setInternalNotes(String internalNotes) {
        _internalNotes = internalNotes;
    }
}
