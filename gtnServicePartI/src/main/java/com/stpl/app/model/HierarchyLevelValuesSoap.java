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
public class HierarchyLevelValuesSoap implements Serializable {
    private String _levelValues;
    private int _hierarchyLevelValuesSid;
    private Date _createdDate;
    private int _createdBy;
    private int _hierarchyLevelDefinitionSid;
    private int _versionNo;
    private int _modifiedBy;
    private Date _modifiedDate;

    public HierarchyLevelValuesSoap() {
    }

    public static HierarchyLevelValuesSoap toSoapModel(
        HierarchyLevelValues model) {
        HierarchyLevelValuesSoap soapModel = new HierarchyLevelValuesSoap();

        soapModel.setLevelValues(model.getLevelValues());
        soapModel.setHierarchyLevelValuesSid(model.getHierarchyLevelValuesSid());
        soapModel.setCreatedDate(model.getCreatedDate());
        soapModel.setCreatedBy(model.getCreatedBy());
        soapModel.setHierarchyLevelDefinitionSid(model.getHierarchyLevelDefinitionSid());
        soapModel.setVersionNo(model.getVersionNo());
        soapModel.setModifiedBy(model.getModifiedBy());
        soapModel.setModifiedDate(model.getModifiedDate());

        return soapModel;
    }

    public static HierarchyLevelValuesSoap[] toSoapModels(
        HierarchyLevelValues[] models) {
        HierarchyLevelValuesSoap[] soapModels = new HierarchyLevelValuesSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static HierarchyLevelValuesSoap[][] toSoapModels(
        HierarchyLevelValues[][] models) {
        HierarchyLevelValuesSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new HierarchyLevelValuesSoap[models.length][models[0].length];
        } else {
            soapModels = new HierarchyLevelValuesSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static HierarchyLevelValuesSoap[] toSoapModels(
        List<HierarchyLevelValues> models) {
        List<HierarchyLevelValuesSoap> soapModels = new ArrayList<HierarchyLevelValuesSoap>(models.size());

        for (HierarchyLevelValues model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new HierarchyLevelValuesSoap[soapModels.size()]);
    }

    public int getPrimaryKey() {
        return _hierarchyLevelValuesSid;
    }

    public void setPrimaryKey(int pk) {
        setHierarchyLevelValuesSid(pk);
    }

    public String getLevelValues() {
        return _levelValues;
    }

    public void setLevelValues(String levelValues) {
        _levelValues = levelValues;
    }

    public int getHierarchyLevelValuesSid() {
        return _hierarchyLevelValuesSid;
    }

    public void setHierarchyLevelValuesSid(int hierarchyLevelValuesSid) {
        _hierarchyLevelValuesSid = hierarchyLevelValuesSid;
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

    public int getHierarchyLevelDefinitionSid() {
        return _hierarchyLevelDefinitionSid;
    }

    public void setHierarchyLevelDefinitionSid(int hierarchyLevelDefinitionSid) {
        _hierarchyLevelDefinitionSid = hierarchyLevelDefinitionSid;
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
