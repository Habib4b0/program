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
public class DocDetailsSoap implements Serializable {
    private String _fileName;
    private String _fileType;
    private String _uploadedBy;
    private String _forecastType;
    private int _projectionId;
    private int _docDetailsId;
    private Date _uploadedDate;
    private String _fileSize;

    public DocDetailsSoap() {
    }

    public static DocDetailsSoap toSoapModel(DocDetails model) {
        DocDetailsSoap soapModel = new DocDetailsSoap();

        soapModel.setFileName(model.getFileName());
        soapModel.setFileType(model.getFileType());
        soapModel.setUploadedBy(model.getUploadedBy());
        soapModel.setForecastType(model.getForecastType());
        soapModel.setProjectionId(model.getProjectionId());
        soapModel.setDocDetailsId(model.getDocDetailsId());
        soapModel.setUploadedDate(model.getUploadedDate());
        soapModel.setFileSize(model.getFileSize());

        return soapModel;
    }

    public static DocDetailsSoap[] toSoapModels(DocDetails[] models) {
        DocDetailsSoap[] soapModels = new DocDetailsSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static DocDetailsSoap[][] toSoapModels(DocDetails[][] models) {
        DocDetailsSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new DocDetailsSoap[models.length][models[0].length];
        } else {
            soapModels = new DocDetailsSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static DocDetailsSoap[] toSoapModels(List<DocDetails> models) {
        List<DocDetailsSoap> soapModels = new ArrayList<DocDetailsSoap>(models.size());

        for (DocDetails model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new DocDetailsSoap[soapModels.size()]);
    }

    public int getPrimaryKey() {
        return _docDetailsId;
    }

    public void setPrimaryKey(int pk) {
        setDocDetailsId(pk);
    }

    public String getFileName() {
        return _fileName;
    }

    public void setFileName(String fileName) {
        _fileName = fileName;
    }

    public String getFileType() {
        return _fileType;
    }

    public void setFileType(String fileType) {
        _fileType = fileType;
    }

    public String getUploadedBy() {
        return _uploadedBy;
    }

    public void setUploadedBy(String uploadedBy) {
        _uploadedBy = uploadedBy;
    }

    public String getForecastType() {
        return _forecastType;
    }

    public void setForecastType(String forecastType) {
        _forecastType = forecastType;
    }

    public int getProjectionId() {
        return _projectionId;
    }

    public void setProjectionId(int projectionId) {
        _projectionId = projectionId;
    }

    public int getDocDetailsId() {
        return _docDetailsId;
    }

    public void setDocDetailsId(int docDetailsId) {
        _docDetailsId = docDetailsId;
    }

    public Date getUploadedDate() {
        return _uploadedDate;
    }

    public void setUploadedDate(Date uploadedDate) {
        _uploadedDate = uploadedDate;
    }

    public String getFileSize() {
        return _fileSize;
    }

    public void setFileSize(String fileSize) {
        _fileSize = fileSize;
    }
}
