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
public class BusinessroleMasterSoap implements Serializable {
    private int _businessroleMasterSid;
    private Date _createdDate;
    private int _createdBy;
    private int _usersSid;
    private int _hierarchyLevel;
    private String _processed;
    private String _businessroleName;
    private int _versionNo;
    private int _modifiedBy;
    private Date _modifiedDate;
    private String _businessroleDesc;
    private String _isActive;

    public BusinessroleMasterSoap() {
    }

    public static BusinessroleMasterSoap toSoapModel(BusinessroleMaster model) {
        BusinessroleMasterSoap soapModel = new BusinessroleMasterSoap();

        soapModel.setBusinessroleMasterSid(model.getBusinessroleMasterSid());
        soapModel.setCreatedDate(model.getCreatedDate());
        soapModel.setCreatedBy(model.getCreatedBy());
        soapModel.setUsersSid(model.getUsersSid());
        soapModel.setHierarchyLevel(model.getHierarchyLevel());
        soapModel.setProcessed(model.getProcessed());
        soapModel.setBusinessroleName(model.getBusinessroleName());
        soapModel.setVersionNo(model.getVersionNo());
        soapModel.setModifiedBy(model.getModifiedBy());
        soapModel.setModifiedDate(model.getModifiedDate());
        soapModel.setBusinessroleDesc(model.getBusinessroleDesc());
        soapModel.setIsActive(model.getIsActive());

        return soapModel;
    }

    public static BusinessroleMasterSoap[] toSoapModels(
        BusinessroleMaster[] models) {
        BusinessroleMasterSoap[] soapModels = new BusinessroleMasterSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static BusinessroleMasterSoap[][] toSoapModels(
        BusinessroleMaster[][] models) {
        BusinessroleMasterSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new BusinessroleMasterSoap[models.length][models[0].length];
        } else {
            soapModels = new BusinessroleMasterSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static BusinessroleMasterSoap[] toSoapModels(
        List<BusinessroleMaster> models) {
        List<BusinessroleMasterSoap> soapModels = new ArrayList<BusinessroleMasterSoap>(models.size());

        for (BusinessroleMaster model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new BusinessroleMasterSoap[soapModels.size()]);
    }

    public int getPrimaryKey() {
        return _businessroleMasterSid;
    }

    public void setPrimaryKey(int pk) {
        setBusinessroleMasterSid(pk);
    }

    public int getBusinessroleMasterSid() {
        return _businessroleMasterSid;
    }

    public void setBusinessroleMasterSid(int businessroleMasterSid) {
        _businessroleMasterSid = businessroleMasterSid;
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

    public int getUsersSid() {
        return _usersSid;
    }

    public void setUsersSid(int usersSid) {
        _usersSid = usersSid;
    }

    public int getHierarchyLevel() {
        return _hierarchyLevel;
    }

    public void setHierarchyLevel(int hierarchyLevel) {
        _hierarchyLevel = hierarchyLevel;
    }

    public String getProcessed() {
        return _processed;
    }

    public void setProcessed(String processed) {
        _processed = processed;
    }

    public String getBusinessroleName() {
        return _businessroleName;
    }

    public void setBusinessroleName(String businessroleName) {
        _businessroleName = businessroleName;
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

    public String getBusinessroleDesc() {
        return _businessroleDesc;
    }

    public void setBusinessroleDesc(String businessroleDesc) {
        _businessroleDesc = businessroleDesc;
    }

    public String getIsActive() {
        return _isActive;
    }

    public void setIsActive(String isActive) {
        _isActive = isActive;
    }
}
