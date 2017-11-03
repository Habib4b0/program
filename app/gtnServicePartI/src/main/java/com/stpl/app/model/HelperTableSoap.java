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
public class HelperTableSoap implements Serializable {
    private Date _createdDate;
    private int _createdBy;
    private String _description;
    private int _refCount;
    private String _listName;
    private int _helperTableSid;
    private int _modifiedBy;
    private Date _modifiedDate;
    private String _aliasName;

    public HelperTableSoap() {
    }

    public static HelperTableSoap toSoapModel(HelperTable model) {
        HelperTableSoap soapModel = new HelperTableSoap();

        soapModel.setCreatedDate(model.getCreatedDate());
        soapModel.setCreatedBy(model.getCreatedBy());
        soapModel.setDescription(model.getDescription());
        soapModel.setRefCount(model.getRefCount());
        soapModel.setListName(model.getListName());
        soapModel.setHelperTableSid(model.getHelperTableSid());
        soapModel.setModifiedBy(model.getModifiedBy());
        soapModel.setModifiedDate(model.getModifiedDate());
        soapModel.setAliasName(model.getAliasName());

        return soapModel;
    }

    public static HelperTableSoap[] toSoapModels(HelperTable[] models) {
        HelperTableSoap[] soapModels = new HelperTableSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static HelperTableSoap[][] toSoapModels(HelperTable[][] models) {
        HelperTableSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new HelperTableSoap[models.length][models[0].length];
        } else {
            soapModels = new HelperTableSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static HelperTableSoap[] toSoapModels(List<HelperTable> models) {
        List<HelperTableSoap> soapModels = new ArrayList<HelperTableSoap>(models.size());

        for (HelperTable model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new HelperTableSoap[soapModels.size()]);
    }

    public int getPrimaryKey() {
        return _helperTableSid;
    }

    public void setPrimaryKey(int pk) {
        setHelperTableSid(pk);
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

    public String getDescription() {
        return _description;
    }

    public void setDescription(String description) {
        _description = description;
    }

    public int getRefCount() {
        return _refCount;
    }

    public void setRefCount(int refCount) {
        _refCount = refCount;
    }

    public String getListName() {
        return _listName;
    }

    public void setListName(String listName) {
        _listName = listName;
    }

    public int getHelperTableSid() {
        return _helperTableSid;
    }

    public void setHelperTableSid(int helperTableSid) {
        _helperTableSid = helperTableSid;
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

    public String getAliasName() {
        return _aliasName;
    }

    public void setAliasName(String aliasName) {
        _aliasName = aliasName;
    }
}
