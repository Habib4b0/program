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
public class FileManagementSoap implements Serializable {
    private int _country;
    private Date _fromPeriod;
    private int _versionNo;
    private String _forecastSource;
    private Date _modifiedDate;
    private int _createdBy;
    private Date _createdDate;
    private String _version;
    private String _fileSource;
    private Date _toPeriod;
    private int _modifiedBy;
    private int _fileManagementSid;
    private String _forecastName;
    private int _fileType;
    private String _businessUnit;
    private int _company;

    public FileManagementSoap() {
    }

    public static FileManagementSoap toSoapModel(FileManagement model) {
        FileManagementSoap soapModel = new FileManagementSoap();

        soapModel.setCountry(model.getCountry());
        soapModel.setFromPeriod(model.getFromPeriod());
        soapModel.setVersionNo(model.getVersionNo());
        soapModel.setForecastSource(model.getForecastSource());
        soapModel.setModifiedDate(model.getModifiedDate());
        soapModel.setCreatedBy(model.getCreatedBy());
        soapModel.setCreatedDate(model.getCreatedDate());
        soapModel.setVersion(model.getVersion());
        soapModel.setFileSource(model.getFileSource());
        soapModel.setToPeriod(model.getToPeriod());
        soapModel.setModifiedBy(model.getModifiedBy());
        soapModel.setFileManagementSid(model.getFileManagementSid());
        soapModel.setForecastName(model.getForecastName());
        soapModel.setFileType(model.getFileType());
        soapModel.setBusinessUnit(model.getBusinessUnit());
        soapModel.setCompany(model.getCompany());

        return soapModel;
    }

    public static FileManagementSoap[] toSoapModels(FileManagement[] models) {
        FileManagementSoap[] soapModels = new FileManagementSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static FileManagementSoap[][] toSoapModels(FileManagement[][] models) {
        FileManagementSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new FileManagementSoap[models.length][models[0].length];
        } else {
            soapModels = new FileManagementSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static FileManagementSoap[] toSoapModels(List<FileManagement> models) {
        List<FileManagementSoap> soapModels = new ArrayList<FileManagementSoap>(models.size());

        for (FileManagement model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new FileManagementSoap[soapModels.size()]);
    }

    public int getPrimaryKey() {
        return _fileManagementSid;
    }

    public void setPrimaryKey(int pk) {
        setFileManagementSid(pk);
    }

    public int getCountry() {
        return _country;
    }

    public void setCountry(int country) {
        _country = country;
    }

    public Date getFromPeriod() {
        return _fromPeriod;
    }

    public void setFromPeriod(Date fromPeriod) {
        _fromPeriod = fromPeriod;
    }

    public int getVersionNo() {
        return _versionNo;
    }

    public void setVersionNo(int versionNo) {
        _versionNo = versionNo;
    }

    public String getForecastSource() {
        return _forecastSource;
    }

    public void setForecastSource(String forecastSource) {
        _forecastSource = forecastSource;
    }

    public Date getModifiedDate() {
        return _modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        _modifiedDate = modifiedDate;
    }

    public int getCreatedBy() {
        return _createdBy;
    }

    public void setCreatedBy(int createdBy) {
        _createdBy = createdBy;
    }

    public Date getCreatedDate() {
        return _createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        _createdDate = createdDate;
    }

    public String getVersion() {
        return _version;
    }

    public void setVersion(String version) {
        _version = version;
    }

    public String getFileSource() {
        return _fileSource;
    }

    public void setFileSource(String fileSource) {
        _fileSource = fileSource;
    }

    public Date getToPeriod() {
        return _toPeriod;
    }

    public void setToPeriod(Date toPeriod) {
        _toPeriod = toPeriod;
    }

    public int getModifiedBy() {
        return _modifiedBy;
    }

    public void setModifiedBy(int modifiedBy) {
        _modifiedBy = modifiedBy;
    }

    public int getFileManagementSid() {
        return _fileManagementSid;
    }

    public void setFileManagementSid(int fileManagementSid) {
        _fileManagementSid = fileManagementSid;
    }

    public String getForecastName() {
        return _forecastName;
    }

    public void setForecastName(String forecastName) {
        _forecastName = forecastName;
    }

    public int getFileType() {
        return _fileType;
    }

    public void setFileType(int fileType) {
        _fileType = fileType;
    }

    public String getBusinessUnit() {
        return _businessUnit;
    }

    public void setBusinessUnit(String businessUnit) {
        _businessUnit = businessUnit;
    }

    public int getCompany() {
        return _company;
    }

    public void setCompany(int company) {
        _company = company;
    }
}
