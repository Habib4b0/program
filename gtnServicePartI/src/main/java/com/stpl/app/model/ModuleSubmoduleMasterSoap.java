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
public class ModuleSubmoduleMasterSoap implements Serializable {
    private Date _createdDate;
    private int _createdBy;
    private String _category;
    private int _moduleSubmoduleSid;
    private String _submoduleName;
    private String _moduleName;
    private int _versionNo;
    private int _modifiedBy;
    private Date _modifiedDate;

    public ModuleSubmoduleMasterSoap() {
    }

    public static ModuleSubmoduleMasterSoap toSoapModel(
        ModuleSubmoduleMaster model) {
        ModuleSubmoduleMasterSoap soapModel = new ModuleSubmoduleMasterSoap();

        soapModel.setCreatedDate(model.getCreatedDate());
        soapModel.setCreatedBy(model.getCreatedBy());
        soapModel.setCategory(model.getCategory());
        soapModel.setModuleSubmoduleSid(model.getModuleSubmoduleSid());
        soapModel.setSubmoduleName(model.getSubmoduleName());
        soapModel.setModuleName(model.getModuleName());
        soapModel.setVersionNo(model.getVersionNo());
        soapModel.setModifiedBy(model.getModifiedBy());
        soapModel.setModifiedDate(model.getModifiedDate());

        return soapModel;
    }

    public static ModuleSubmoduleMasterSoap[] toSoapModels(
        ModuleSubmoduleMaster[] models) {
        ModuleSubmoduleMasterSoap[] soapModels = new ModuleSubmoduleMasterSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static ModuleSubmoduleMasterSoap[][] toSoapModels(
        ModuleSubmoduleMaster[][] models) {
        ModuleSubmoduleMasterSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new ModuleSubmoduleMasterSoap[models.length][models[0].length];
        } else {
            soapModels = new ModuleSubmoduleMasterSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static ModuleSubmoduleMasterSoap[] toSoapModels(
        List<ModuleSubmoduleMaster> models) {
        List<ModuleSubmoduleMasterSoap> soapModels = new ArrayList<ModuleSubmoduleMasterSoap>(models.size());

        for (ModuleSubmoduleMaster model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new ModuleSubmoduleMasterSoap[soapModels.size()]);
    }

    public int getPrimaryKey() {
        return _moduleSubmoduleSid;
    }

    public void setPrimaryKey(int pk) {
        setModuleSubmoduleSid(pk);
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

    public String getCategory() {
        return _category;
    }

    public void setCategory(String category) {
        _category = category;
    }

    public int getModuleSubmoduleSid() {
        return _moduleSubmoduleSid;
    }

    public void setModuleSubmoduleSid(int moduleSubmoduleSid) {
        _moduleSubmoduleSid = moduleSubmoduleSid;
    }

    public String getSubmoduleName() {
        return _submoduleName;
    }

    public void setSubmoduleName(String submoduleName) {
        _submoduleName = submoduleName;
    }

    public String getModuleName() {
        return _moduleName;
    }

    public void setModuleName(String moduleName) {
        _moduleName = moduleName;
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
}
