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
public class ImtdLevelValuesSoap implements Serializable {
    private String _levelValues;
    private Date _createdDate;
    private int _createdBy;
    private int _imtdLevelValuesSid;
    private int _levelNo;
    private int _versionNo;
    private int _modifiedBy;
    private Date _modifiedDate;
    private String _levelName;

    public ImtdLevelValuesSoap() {
    }

    public static ImtdLevelValuesSoap toSoapModel(ImtdLevelValues model) {
        ImtdLevelValuesSoap soapModel = new ImtdLevelValuesSoap();

        soapModel.setLevelValues(model.getLevelValues());
        soapModel.setCreatedDate(model.getCreatedDate());
        soapModel.setCreatedBy(model.getCreatedBy());
        soapModel.setImtdLevelValuesSid(model.getImtdLevelValuesSid());
        soapModel.setLevelNo(model.getLevelNo());
        soapModel.setVersionNo(model.getVersionNo());
        soapModel.setModifiedBy(model.getModifiedBy());
        soapModel.setModifiedDate(model.getModifiedDate());
        soapModel.setLevelName(model.getLevelName());

        return soapModel;
    }

    public static ImtdLevelValuesSoap[] toSoapModels(ImtdLevelValues[] models) {
        ImtdLevelValuesSoap[] soapModels = new ImtdLevelValuesSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static ImtdLevelValuesSoap[][] toSoapModels(
        ImtdLevelValues[][] models) {
        ImtdLevelValuesSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new ImtdLevelValuesSoap[models.length][models[0].length];
        } else {
            soapModels = new ImtdLevelValuesSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static ImtdLevelValuesSoap[] toSoapModels(
        List<ImtdLevelValues> models) {
        List<ImtdLevelValuesSoap> soapModels = new ArrayList<ImtdLevelValuesSoap>(models.size());

        for (ImtdLevelValues model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new ImtdLevelValuesSoap[soapModels.size()]);
    }

    public int getPrimaryKey() {
        return _imtdLevelValuesSid;
    }

    public void setPrimaryKey(int pk) {
        setImtdLevelValuesSid(pk);
    }

    public String getLevelValues() {
        return _levelValues;
    }

    public void setLevelValues(String levelValues) {
        _levelValues = levelValues;
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

    public int getImtdLevelValuesSid() {
        return _imtdLevelValuesSid;
    }

    public void setImtdLevelValuesSid(int imtdLevelValuesSid) {
        _imtdLevelValuesSid = imtdLevelValuesSid;
    }

    public int getLevelNo() {
        return _levelNo;
    }

    public void setLevelNo(int levelNo) {
        _levelNo = levelNo;
    }

    public int getVersionNo() {
        return _versionNo;
    }

    public void setVersionNo(int versionNo) {
        _versionNo = versionNo;
    }

    public int getModifiedBy() {
        return _modifiedBy;
    }

    public void setModifiedBy(int modifiedBy) {
        _modifiedBy = modifiedBy;
    }

    public Date getModifiedDate() {
        return _modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        _modifiedDate = modifiedDate;
    }

    public String getLevelName() {
        return _levelName;
    }

    public void setLevelName(String levelName) {
        _levelName = levelName;
    }
}
