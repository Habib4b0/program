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
public class UsergroupBusinessroleSoap implements Serializable {
    private int _createdBy;
    private int _businessroleMasterSid;
    private int _usersSid;
    private int _modifiedBy;
    private Date _createdDate;
    private String _processed;
    private int _usergroupId;
    private int _versionNo;
    private String _isActive;
    private int _usergroupBusinessroleSid;
    private Date _modifiedDate;

    public UsergroupBusinessroleSoap() {
    }

    public static UsergroupBusinessroleSoap toSoapModel(
        UsergroupBusinessrole model) {
        UsergroupBusinessroleSoap soapModel = new UsergroupBusinessroleSoap();

        soapModel.setCreatedBy(model.getCreatedBy());
        soapModel.setBusinessroleMasterSid(model.getBusinessroleMasterSid());
        soapModel.setUsersSid(model.getUsersSid());
        soapModel.setModifiedBy(model.getModifiedBy());
        soapModel.setCreatedDate(model.getCreatedDate());
        soapModel.setProcessed(model.getProcessed());
        soapModel.setUsergroupId(model.getUsergroupId());
        soapModel.setVersionNo(model.getVersionNo());
        soapModel.setIsActive(model.getIsActive());
        soapModel.setUsergroupBusinessroleSid(model.getUsergroupBusinessroleSid());
        soapModel.setModifiedDate(model.getModifiedDate());

        return soapModel;
    }

    public static UsergroupBusinessroleSoap[] toSoapModels(
        UsergroupBusinessrole[] models) {
        UsergroupBusinessroleSoap[] soapModels = new UsergroupBusinessroleSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static UsergroupBusinessroleSoap[][] toSoapModels(
        UsergroupBusinessrole[][] models) {
        UsergroupBusinessroleSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new UsergroupBusinessroleSoap[models.length][models[0].length];
        } else {
            soapModels = new UsergroupBusinessroleSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static UsergroupBusinessroleSoap[] toSoapModels(
        List<UsergroupBusinessrole> models) {
        List<UsergroupBusinessroleSoap> soapModels = new ArrayList<UsergroupBusinessroleSoap>(models.size());

        for (UsergroupBusinessrole model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new UsergroupBusinessroleSoap[soapModels.size()]);
    }

    public int getPrimaryKey() {
        return _usergroupBusinessroleSid;
    }

    public void setPrimaryKey(int pk) {
        setUsergroupBusinessroleSid(pk);
    }

    public int getCreatedBy() {
        return _createdBy;
    }

    public void setCreatedBy(int createdBy) {
        _createdBy = createdBy;
    }

    public int getBusinessroleMasterSid() {
        return _businessroleMasterSid;
    }

    public void setBusinessroleMasterSid(int businessroleMasterSid) {
        _businessroleMasterSid = businessroleMasterSid;
    }

    public int getUsersSid() {
        return _usersSid;
    }

    public void setUsersSid(int usersSid) {
        _usersSid = usersSid;
    }

    public int getModifiedBy() {
        return _modifiedBy;
    }

    public void setModifiedBy(int modifiedBy) {
        _modifiedBy = modifiedBy;
    }

    public Date getCreatedDate() {
        return _createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        _createdDate = createdDate;
    }

    public String getProcessed() {
        return _processed;
    }

    public void setProcessed(String processed) {
        _processed = processed;
    }

    public int getUsergroupId() {
        return _usergroupId;
    }

    public void setUsergroupId(int usergroupId) {
        _usergroupId = usergroupId;
    }

    public int getVersionNo() {
        return _versionNo;
    }

    public void setVersionNo(int versionNo) {
        _versionNo = versionNo;
    }

    public String getIsActive() {
        return _isActive;
    }

    public void setIsActive(String isActive) {
        _isActive = isActive;
    }

    public int getUsergroupBusinessroleSid() {
        return _usergroupBusinessroleSid;
    }

    public void setUsergroupBusinessroleSid(int usergroupBusinessroleSid) {
        _usergroupBusinessroleSid = usergroupBusinessroleSid;
    }

    public Date getModifiedDate() {
        return _modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        _modifiedDate = modifiedDate;
    }
}
