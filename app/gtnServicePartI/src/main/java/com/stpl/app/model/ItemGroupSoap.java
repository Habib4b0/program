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
public class ItemGroupSoap implements Serializable {
    private Date _createdDate;
    private int _createdBy;
    private String _itemGroupNo;
    private int _versionNo;
    private String _itemFilter;
    private int _modifiedBy;
    private String _itemGroupDescription;
    private Date _modifiedDate;
    private String _itemGroupName;
    private int _itemGroupSid;
    private int _companyMasterSid;

    public ItemGroupSoap() {
    }

    public static ItemGroupSoap toSoapModel(ItemGroup model) {
        ItemGroupSoap soapModel = new ItemGroupSoap();

        soapModel.setCreatedDate(model.getCreatedDate());
        soapModel.setCreatedBy(model.getCreatedBy());
        soapModel.setItemGroupNo(model.getItemGroupNo());
        soapModel.setVersionNo(model.getVersionNo());
        soapModel.setItemFilter(model.getItemFilter());
        soapModel.setModifiedBy(model.getModifiedBy());
        soapModel.setItemGroupDescription(model.getItemGroupDescription());
        soapModel.setModifiedDate(model.getModifiedDate());
        soapModel.setItemGroupName(model.getItemGroupName());
        soapModel.setItemGroupSid(model.getItemGroupSid());
        soapModel.setCompanyMasterSid(model.getCompanyMasterSid());

        return soapModel;
    }

    public static ItemGroupSoap[] toSoapModels(ItemGroup[] models) {
        ItemGroupSoap[] soapModels = new ItemGroupSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static ItemGroupSoap[][] toSoapModels(ItemGroup[][] models) {
        ItemGroupSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new ItemGroupSoap[models.length][models[0].length];
        } else {
            soapModels = new ItemGroupSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static ItemGroupSoap[] toSoapModels(List<ItemGroup> models) {
        List<ItemGroupSoap> soapModels = new ArrayList<ItemGroupSoap>(models.size());

        for (ItemGroup model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new ItemGroupSoap[soapModels.size()]);
    }

    public int getPrimaryKey() {
        return _itemGroupSid;
    }

    public void setPrimaryKey(int pk) {
        setItemGroupSid(pk);
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

    public String getItemFilter() {
        return _itemFilter;
    }

    public void setItemFilter(String itemFilter) {
        _itemFilter = itemFilter;
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
