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
public class MasterDataFilesSoap implements Serializable {
    private int _masterTableSid;
    private int _masterDataFilesSid;
    private String _masterTableName;
    private String _filePath;
    private Date _createdDate;
    private int _createdBy;

    public MasterDataFilesSoap() {
    }

    public static MasterDataFilesSoap toSoapModel(MasterDataFiles model) {
        MasterDataFilesSoap soapModel = new MasterDataFilesSoap();

        soapModel.setMasterTableSid(model.getMasterTableSid());
        soapModel.setMasterDataFilesSid(model.getMasterDataFilesSid());
        soapModel.setMasterTableName(model.getMasterTableName());
        soapModel.setFilePath(model.getFilePath());
        soapModel.setCreatedDate(model.getCreatedDate());
        soapModel.setCreatedBy(model.getCreatedBy());

        return soapModel;
    }

    public static MasterDataFilesSoap[] toSoapModels(MasterDataFiles[] models) {
        MasterDataFilesSoap[] soapModels = new MasterDataFilesSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static MasterDataFilesSoap[][] toSoapModels(
        MasterDataFiles[][] models) {
        MasterDataFilesSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new MasterDataFilesSoap[models.length][models[0].length];
        } else {
            soapModels = new MasterDataFilesSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static MasterDataFilesSoap[] toSoapModels(
        List<MasterDataFiles> models) {
        List<MasterDataFilesSoap> soapModels = new ArrayList<MasterDataFilesSoap>(models.size());

        for (MasterDataFiles model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new MasterDataFilesSoap[soapModels.size()]);
    }

    public int getPrimaryKey() {
        return _masterDataFilesSid;
    }

    public void setPrimaryKey(int pk) {
        setMasterDataFilesSid(pk);
    }

    public int getMasterTableSid() {
        return _masterTableSid;
    }

    public void setMasterTableSid(int masterTableSid) {
        _masterTableSid = masterTableSid;
    }

    public int getMasterDataFilesSid() {
        return _masterDataFilesSid;
    }

    public void setMasterDataFilesSid(int masterDataFilesSid) {
        _masterDataFilesSid = masterDataFilesSid;
    }

    public String getMasterTableName() {
        return _masterTableName;
    }

    public void setMasterTableName(String masterTableName) {
        _masterTableName = masterTableName;
    }

    public String getFilePath() {
        return _filePath;
    }

    public void setFilePath(String filePath) {
        _filePath = filePath;
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
}
