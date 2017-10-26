package com.stpl.app.model;

import com.stpl.app.service.persistence.HistItemGroupPK;

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
public class HistItemGroupSoap implements Serializable {
    private Date _createdDate;
    private int _createdBy;
    private String _actionFlag;
    private String _itemGroupNo;
    private int _versionNo;
    private int _modifiedBy;
    private String _itemGroupDescription;
    private Date _modifiedDate;
    private String _itemGroupName;
    private int _itemGroupSid;
    private int _companyMasterSid;

    public HistItemGroupSoap() {
    }

    public static HistItemGroupSoap toSoapModel(HistItemGroup model) {
        HistItemGroupSoap soapModel = new HistItemGroupSoap();

        soapModel.setCreatedDate(model.getCreatedDate());
        soapModel.setCreatedBy(model.getCreatedBy());
        soapModel.setActionFlag(model.getActionFlag());
        soapModel.setItemGroupNo(model.getItemGroupNo());
        soapModel.setVersionNo(model.getVersionNo());
        soapModel.setModifiedBy(model.getModifiedBy());
        soapModel.setItemGroupDescription(model.getItemGroupDescription());
        soapModel.setModifiedDate(model.getModifiedDate());
        soapModel.setItemGroupName(model.getItemGroupName());
        soapModel.setItemGroupSid(model.getItemGroupSid());
        soapModel.setCompanyMasterSid(model.getCompanyMasterSid());

        return soapModel;
    }

    public static HistItemGroupSoap[] toSoapModels(HistItemGroup[] models) {
        HistItemGroupSoap[] soapModels = new HistItemGroupSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static HistItemGroupSoap[][] toSoapModels(HistItemGroup[][] models) {
        HistItemGroupSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new HistItemGroupSoap[models.length][models[0].length];
        } else {
            soapModels = new HistItemGroupSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static HistItemGroupSoap[] toSoapModels(List<HistItemGroup> models) {
        List<HistItemGroupSoap> soapModels = new ArrayList<HistItemGroupSoap>(models.size());

        for (HistItemGroup model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new HistItemGroupSoap[soapModels.size()]);
    }

    public HistItemGroupPK getPrimaryKey() {
        return new HistItemGroupPK(_actionFlag, _versionNo, _itemGroupSid);
    }

    public void setPrimaryKey(HistItemGroupPK pk) {
        setActionFlag(pk.actionFlag);
        setVersionNo(pk.versionNo);
        setItemGroupSid(pk.itemGroupSid);
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

    public String getActionFlag() {
        return _actionFlag;
    }

    public void setActionFlag(String actionFlag) {
        _actionFlag = actionFlag;
    }

    public String getItemGroupNo() {
        return _itemGroupNo;
    }

    public void setItemGroupNo(String itemGroupNo) {
        _itemGroupNo = itemGroupNo;
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

    public String getItemGroupDescription() {
        return _itemGroupDescription;
    }

    public void setItemGroupDescription(String itemGroupDescription) {
        _itemGroupDescription = itemGroupDescription;
    }

    public Date getModifiedDate() {
        return _modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        _modifiedDate = modifiedDate;
    }

    public String getItemGroupName() {
        return _itemGroupName;
    }

    public void setItemGroupName(String itemGroupName) {
        _itemGroupName = itemGroupName;
    }

    public int getItemGroupSid() {
        return _itemGroupSid;
    }

    public void setItemGroupSid(int itemGroupSid) {
        _itemGroupSid = itemGroupSid;
    }

    public int getCompanyMasterSid() {
        return _companyMasterSid;
    }

    public void setCompanyMasterSid(int companyMasterSid) {
        _companyMasterSid = companyMasterSid;
    }
}
