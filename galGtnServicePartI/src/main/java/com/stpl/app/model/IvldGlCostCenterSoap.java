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
public class IvldGlCostCenterSoap implements Serializable {
    private String _reasonForFailure;
    private String _glCostCenterIntfid;
    private Date _modifiedDate;
    private String _companyCostCenter;
    private String _uploadDate;
    private String _createdBy;
    private Date _createdDate;
    private String _source;
    private String _batchId;
    private String _addChgDelIndicator;
    private int _ivldGlCostCenterSid;
    private String _errorField;
    private String _errorCode;
    private Date _intfInsertedDate;
    private String _companyCode;
    private String _modifiedBy;
    private String _reprocessedFlag;
    private String _ndc8;

    public IvldGlCostCenterSoap() {
    }

    public static IvldGlCostCenterSoap toSoapModel(IvldGlCostCenter model) {
        IvldGlCostCenterSoap soapModel = new IvldGlCostCenterSoap();

        soapModel.setReasonForFailure(model.getReasonForFailure());
        soapModel.setGlCostCenterIntfid(model.getGlCostCenterIntfid());
        soapModel.setModifiedDate(model.getModifiedDate());
        soapModel.setCompanyCostCenter(model.getCompanyCostCenter());
        soapModel.setUploadDate(model.getUploadDate());
        soapModel.setCreatedBy(model.getCreatedBy());
        soapModel.setCreatedDate(model.getCreatedDate());
        soapModel.setSource(model.getSource());
        soapModel.setBatchId(model.getBatchId());
        soapModel.setAddChgDelIndicator(model.getAddChgDelIndicator());
        soapModel.setIvldGlCostCenterSid(model.getIvldGlCostCenterSid());
        soapModel.setErrorField(model.getErrorField());
        soapModel.setErrorCode(model.getErrorCode());
        soapModel.setIntfInsertedDate(model.getIntfInsertedDate());
        soapModel.setCompanyCode(model.getCompanyCode());
        soapModel.setModifiedBy(model.getModifiedBy());
        soapModel.setReprocessedFlag(model.getReprocessedFlag());
        soapModel.setNdc8(model.getNdc8());

        return soapModel;
    }

    public static IvldGlCostCenterSoap[] toSoapModels(IvldGlCostCenter[] models) {
        IvldGlCostCenterSoap[] soapModels = new IvldGlCostCenterSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static IvldGlCostCenterSoap[][] toSoapModels(
        IvldGlCostCenter[][] models) {
        IvldGlCostCenterSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new IvldGlCostCenterSoap[models.length][models[0].length];
        } else {
            soapModels = new IvldGlCostCenterSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static IvldGlCostCenterSoap[] toSoapModels(
        List<IvldGlCostCenter> models) {
        List<IvldGlCostCenterSoap> soapModels = new ArrayList<IvldGlCostCenterSoap>(models.size());

        for (IvldGlCostCenter model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new IvldGlCostCenterSoap[soapModels.size()]);
    }

    public int getPrimaryKey() {
        return _ivldGlCostCenterSid;
    }

    public void setPrimaryKey(int pk) {
        setIvldGlCostCenterSid(pk);
    }

    public String getReasonForFailure() {
        return _reasonForFailure;
    }

    public void setReasonForFailure(String reasonForFailure) {
        _reasonForFailure = reasonForFailure;
    }

    public String getGlCostCenterIntfid() {
        return _glCostCenterIntfid;
    }

    public void setGlCostCenterIntfid(String glCostCenterIntfid) {
        _glCostCenterIntfid = glCostCenterIntfid;
    }

    public Date getModifiedDate() {
        return _modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        _modifiedDate = modifiedDate;
    }

    public String getCompanyCostCenter() {
        return _companyCostCenter;
    }

    public void setCompanyCostCenter(String companyCostCenter) {
        _companyCostCenter = companyCostCenter;
    }

    public String getUploadDate() {
        return _uploadDate;
    }

    public void setUploadDate(String uploadDate) {
        _uploadDate = uploadDate;
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

    public String getBatchId() {
        return _batchId;
    }

    public void setBatchId(String batchId) {
        _batchId = batchId;
    }

    public String getAddChgDelIndicator() {
        return _addChgDelIndicator;
    }

    public void setAddChgDelIndicator(String addChgDelIndicator) {
        _addChgDelIndicator = addChgDelIndicator;
    }

    public int getIvldGlCostCenterSid() {
        return _ivldGlCostCenterSid;
    }

    public void setIvldGlCostCenterSid(int ivldGlCostCenterSid) {
        _ivldGlCostCenterSid = ivldGlCostCenterSid;
    }

    public String getErrorField() {
        return _errorField;
    }

    public void setErrorField(String errorField) {
        _errorField = errorField;
    }

    public String getErrorCode() {
        return _errorCode;
    }

    public void setErrorCode(String errorCode) {
        _errorCode = errorCode;
    }

    public Date getIntfInsertedDate() {
        return _intfInsertedDate;
    }

    public void setIntfInsertedDate(Date intfInsertedDate) {
        _intfInsertedDate = intfInsertedDate;
    }

    public String getCompanyCode() {
        return _companyCode;
    }

    public void setCompanyCode(String companyCode) {
        _companyCode = companyCode;
    }

    public String getModifiedBy() {
        return _modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        _modifiedBy = modifiedBy;
    }

    public String getReprocessedFlag() {
        return _reprocessedFlag;
    }

    public void setReprocessedFlag(String reprocessedFlag) {
        _reprocessedFlag = reprocessedFlag;
    }

    public String getNdc8() {
        return _ndc8;
    }

    public void setNdc8(String ndc8) {
        _ndc8 = ndc8;
    }
}
