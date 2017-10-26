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
public class CompanyIdentifierSoap implements Serializable {
    private Date _endDate;
    private int _companyIdentifierSid;
    private Date _modifiedDate;
    private int _identifierStatus;
    private String _entityCode;
    private boolean _recordLockStatus;
    private Date _startDate;
    private Date _createdDate;
    private String _source;
    private int _createdBy;
    private String _companyIdentifierValue;
    private String _batchId;
    private int _companyQualifierSid;
    private int _modifiedBy;
    private String _inboundStatus;
    private int _companyMasterSid;

    public CompanyIdentifierSoap() {
    }

    public static CompanyIdentifierSoap toSoapModel(CompanyIdentifier model) {
        CompanyIdentifierSoap soapModel = new CompanyIdentifierSoap();

        soapModel.setEndDate(model.getEndDate());
        soapModel.setCompanyIdentifierSid(model.getCompanyIdentifierSid());
        soapModel.setModifiedDate(model.getModifiedDate());
        soapModel.setIdentifierStatus(model.getIdentifierStatus());
        soapModel.setEntityCode(model.getEntityCode());
        soapModel.setRecordLockStatus(model.getRecordLockStatus());
        soapModel.setStartDate(model.getStartDate());
        soapModel.setCreatedDate(model.getCreatedDate());
        soapModel.setSource(model.getSource());
        soapModel.setCreatedBy(model.getCreatedBy());
        soapModel.setCompanyIdentifierValue(model.getCompanyIdentifierValue());
        soapModel.setBatchId(model.getBatchId());
        soapModel.setCompanyQualifierSid(model.getCompanyQualifierSid());
        soapModel.setModifiedBy(model.getModifiedBy());
        soapModel.setInboundStatus(model.getInboundStatus());
        soapModel.setCompanyMasterSid(model.getCompanyMasterSid());

        return soapModel;
    }

    public static CompanyIdentifierSoap[] toSoapModels(
        CompanyIdentifier[] models) {
        CompanyIdentifierSoap[] soapModels = new CompanyIdentifierSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static CompanyIdentifierSoap[][] toSoapModels(
        CompanyIdentifier[][] models) {
        CompanyIdentifierSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new CompanyIdentifierSoap[models.length][models[0].length];
        } else {
            soapModels = new CompanyIdentifierSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static CompanyIdentifierSoap[] toSoapModels(
        List<CompanyIdentifier> models) {
        List<CompanyIdentifierSoap> soapModels = new ArrayList<CompanyIdentifierSoap>(models.size());

        for (CompanyIdentifier model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new CompanyIdentifierSoap[soapModels.size()]);
    }

    public int getPrimaryKey() {
        return _companyIdentifierSid;
    }

    public void setPrimaryKey(int pk) {
        setCompanyIdentifierSid(pk);
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

    public int getIdentifierStatus() {
        return _identifierStatus;
    }

    public void setIdentifierStatus(int identifierStatus) {
        _identifierStatus = identifierStatus;
    }

    public String getEntityCode() {
        return _entityCode;
    }

    public void setEntityCode(String entityCode) {
        _entityCode = entityCode;
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

    public String getSource() {
        return _source;
    }

    public void setSource(String source) {
        _source = source;
    }

    public int getCreatedBy() {
        return _createdBy;
    }

    public void setCreatedBy(int createdBy) {
        _createdBy = createdBy;
    }

    public String getCompanyIdentifierValue() {
        return _companyIdentifierValue;
    }

    public void setCompanyIdentifierValue(String companyIdentifierValue) {
        _companyIdentifierValue = companyIdentifierValue;
    }

    public String getBatchId() {
        return _batchId;
    }

    public void setBatchId(String batchId) {
        _batchId = batchId;
    }

    public int getCompanyQualifierSid() {
        return _companyQualifierSid;
    }

    public void setCompanyQualifierSid(int companyQualifierSid) {
        _companyQualifierSid = companyQualifierSid;
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

    public int getCompanyMasterSid() {
        return _companyMasterSid;
    }

    public void setCompanyMasterSid(int companyMasterSid) {
        _companyMasterSid = companyMasterSid;
    }
}
