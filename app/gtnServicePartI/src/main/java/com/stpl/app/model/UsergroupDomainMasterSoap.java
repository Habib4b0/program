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
public class UsergroupDomainMasterSoap implements Serializable {
    private int _createdBy;
    private int _usergroupDomainSid;
    private int _usersSid;
    private int _modifiedBy;
    private Date _createdDate;
    private int _domainId;
    private String _processed;
    private int _usergroupId;
    private int _versionNo;
    private String _isActive;
    private Date _modifiedDate;

    public UsergroupDomainMasterSoap() {
    }

    public static UsergroupDomainMasterSoap toSoapModel(
        UsergroupDomainMaster model) {
        UsergroupDomainMasterSoap soapModel = new UsergroupDomainMasterSoap();

        soapModel.setCreatedBy(model.getCreatedBy());
        soapModel.setUsergroupDomainSid(model.getUsergroupDomainSid());
        soapModel.setUsersSid(model.getUsersSid());
        soapModel.setModifiedBy(model.getModifiedBy());
        soapModel.setCreatedDate(model.getCreatedDate());
        soapModel.setDomainId(model.getDomainId());
        soapModel.setProcessed(model.getProcessed());
        soapModel.setUsergroupId(model.getUsergroupId());
        soapModel.setVersionNo(model.getVersionNo());
        soapModel.setIsActive(model.getIsActive());
        soapModel.setModifiedDate(model.getModifiedDate());

        return soapModel;
    }

    public static UsergroupDomainMasterSoap[] toSoapModels(
        UsergroupDomainMaster[] models) {
        UsergroupDomainMasterSoap[] soapModels = new UsergroupDomainMasterSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static UsergroupDomainMasterSoap[][] toSoapModels(
        UsergroupDomainMaster[][] models) {
        UsergroupDomainMasterSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new UsergroupDomainMasterSoap[models.length][models[0].length];
        } else {
            soapModels = new UsergroupDomainMasterSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static UsergroupDomainMasterSoap[] toSoapModels(
        List<UsergroupDomainMaster> models) {
        List<UsergroupDomainMasterSoap> soapModels = new ArrayList<UsergroupDomainMasterSoap>(models.size());

        for (UsergroupDomainMaster model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new UsergroupDomainMasterSoap[soapModels.size()]);
    }

    public int getPrimaryKey() {
        return _usergroupDomainSid;
    }

    public void setPrimaryKey(int pk) {
        setUsergroupDomainSid(pk);
    }

    public int getCreatedBy() {
        return _createdBy;
    }

    public void setCreatedBy(int createdBy) {
        _createdBy = createdBy;
    }

    public int getUsergroupDomainSid() {
        return _usergroupDomainSid;
    }

    public void setUsergroupDomainSid(int usergroupDomainSid) {
        _usergroupDomainSid = usergroupDomainSid;
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

    public int getDomainId() {
        return _domainId;
    }

    public void setDomainId(int domainId) {
        _domainId = domainId;
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

    public Date getModifiedDate() {
        return _modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        _modifiedDate = modifiedDate;
    }
}
