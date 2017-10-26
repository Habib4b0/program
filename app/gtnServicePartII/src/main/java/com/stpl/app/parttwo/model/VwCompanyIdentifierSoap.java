package com.stpl.app.parttwo.model;

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
public class VwCompanyIdentifierSoap implements Serializable {
    private String _companyId;
    private String _companyName;
    private Date _endDate;
    private int _companyIdentifierSid;
    private Date _modifiedDate;
    private String _identifierStatus;
    private String _companyIdentifier;
    private String _entityCode;
    private Date _startDate;
    private Date _createdDate;
    private String _createdBy;
    private String _source;
    private String _companyNo;
    private String _batchId;
    private String _addChgDelIndicator;
    private String _identifierCodeQualifierName;
    private String _modifiedBy;
    private String _identifierCodeQualifier;

    public VwCompanyIdentifierSoap() {
    }

    public static VwCompanyIdentifierSoap toSoapModel(VwCompanyIdentifier model) {
        VwCompanyIdentifierSoap soapModel = new VwCompanyIdentifierSoap();

        soapModel.setCompanyId(model.getCompanyId());
        soapModel.setCompanyName(model.getCompanyName());
        soapModel.setEndDate(model.getEndDate());
        soapModel.setCompanyIdentifierSid(model.getCompanyIdentifierSid());
        soapModel.setModifiedDate(model.getModifiedDate());
        soapModel.setIdentifierStatus(model.getIdentifierStatus());
        soapModel.setCompanyIdentifier(model.getCompanyIdentifier());
        soapModel.setEntityCode(model.getEntityCode());
        soapModel.setStartDate(model.getStartDate());
        soapModel.setCreatedDate(model.getCreatedDate());
        soapModel.setCreatedBy(model.getCreatedBy());
        soapModel.setSource(model.getSource());
        soapModel.setCompanyNo(model.getCompanyNo());
        soapModel.setBatchId(model.getBatchId());
        soapModel.setAddChgDelIndicator(model.getAddChgDelIndicator());
        soapModel.setIdentifierCodeQualifierName(model.getIdentifierCodeQualifierName());
        soapModel.setModifiedBy(model.getModifiedBy());
        soapModel.setIdentifierCodeQualifier(model.getIdentifierCodeQualifier());

        return soapModel;
    }

    public static VwCompanyIdentifierSoap[] toSoapModels(
        VwCompanyIdentifier[] models) {
        VwCompanyIdentifierSoap[] soapModels = new VwCompanyIdentifierSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static VwCompanyIdentifierSoap[][] toSoapModels(
        VwCompanyIdentifier[][] models) {
        VwCompanyIdentifierSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new VwCompanyIdentifierSoap[models.length][models[0].length];
        } else {
            soapModels = new VwCompanyIdentifierSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static VwCompanyIdentifierSoap[] toSoapModels(
        List<VwCompanyIdentifier> models) {
        List<VwCompanyIdentifierSoap> soapModels = new ArrayList<VwCompanyIdentifierSoap>(models.size());

        for (VwCompanyIdentifier model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new VwCompanyIdentifierSoap[soapModels.size()]);
    }

    public int getPrimaryKey() {
        return _companyIdentifierSid;
    }

    public void setPrimaryKey(int pk) {
        setCompanyIdentifierSid(pk);
    }

    public String getCompanyId() {
        return _companyId;
    }

    public void setCompanyId(String companyId) {
        _companyId = companyId;
    }

    public String getCompanyName() {
        return _companyName;
    }

    public void setCompanyName(String companyName) {
        _companyName = companyName;
    }

    public Date getEndDate() {
        return _endDate;
    }

    public void setEndDate(Date endDate) {
        _endDate = endDate;
    }

    public int getCompanyIdentifierSid() {
        return _companyIdentifierSid;
    }

    public void setCompanyIdentifierSid(int companyIdentifierSid) {
        _companyIdentifierSid = companyIdentifierSid;
    }

    public Date getModifiedDate() {
        return _modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        _modifiedDate = modifiedDate;
    }

    public String getIdentifierStatus() {
        return _identifierStatus;
    }

    public void setIdentifierStatus(String identifierStatus) {
        _identifierStatus = identifierStatus;
    }

    public String getCompanyIdentifier() {
        return _companyIdentifier;
    }

    public void setCompanyIdentifier(String companyIdentifier) {
        _companyIdentifier = companyIdentifier;
    }

    public String getEntityCode() {
        return _entityCode;
    }

    public void setEntityCode(String entityCode) {
        _entityCode = entityCode;
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

    public String getCreatedBy() {
        return _createdBy;
    }

    public void setCreatedBy(String createdBy) {
        _createdBy = createdBy;
    }

    public String getSource() {
        return _source;
    }

    public void setSource(String source) {
        _source = source;
    }

    public String getCompanyNo() {
        return _companyNo;
    }

    public void setCompanyNo(String companyNo) {
        _companyNo = companyNo;
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

    public String getIdentifierCodeQualifierName() {
        return _identifierCodeQualifierName;
    }

    public void setIdentifierCodeQualifierName(
        String identifierCodeQualifierName) {
        _identifierCodeQualifierName = identifierCodeQualifierName;
    }

    public String getModifiedBy() {
        return _modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        _modifiedBy = modifiedBy;
    }

    public String getIdentifierCodeQualifier() {
        return _identifierCodeQualifier;
    }

    public void setIdentifierCodeQualifier(String identifierCodeQualifier) {
        _identifierCodeQualifier = identifierCodeQualifier;
    }
}
