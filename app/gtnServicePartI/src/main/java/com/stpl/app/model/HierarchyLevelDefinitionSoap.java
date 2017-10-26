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
public class HierarchyLevelDefinitionSoap implements Serializable {
    private String _tableName;
    private Date _createdDate;
    private int _createdBy;
    private String _levelValueReference;
    private String _fieldName;
    private int _levelNo;
    private int _hierarchyLevelDefinitionSid;
    private int _hierarchyDefinitionSid;
    private int _versionNo;
    private int _modifiedBy;
    private Date _modifiedDate;
    private String _levelName;

    public HierarchyLevelDefinitionSoap() {
    }

    public static HierarchyLevelDefinitionSoap toSoapModel(
        HierarchyLevelDefinition model) {
        HierarchyLevelDefinitionSoap soapModel = new HierarchyLevelDefinitionSoap();

        soapModel.setTableName(model.getTableName());
        soapModel.setCreatedDate(model.getCreatedDate());
        soapModel.setCreatedBy(model.getCreatedBy());
        soapModel.setLevelValueReference(model.getLevelValueReference());
        soapModel.setFieldName(model.getFieldName());
        soapModel.setLevelNo(model.getLevelNo());
        soapModel.setHierarchyLevelDefinitionSid(model.getHierarchyLevelDefinitionSid());
        soapModel.setHierarchyDefinitionSid(model.getHierarchyDefinitionSid());
        soapModel.setVersionNo(model.getVersionNo());
        soapModel.setModifiedBy(model.getModifiedBy());
        soapModel.setModifiedDate(model.getModifiedDate());
        soapModel.setLevelName(model.getLevelName());

        return soapModel;
    }

    public static HierarchyLevelDefinitionSoap[] toSoapModels(
        HierarchyLevelDefinition[] models) {
        HierarchyLevelDefinitionSoap[] soapModels = new HierarchyLevelDefinitionSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static HierarchyLevelDefinitionSoap[][] toSoapModels(
        HierarchyLevelDefinition[][] models) {
        HierarchyLevelDefinitionSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new HierarchyLevelDefinitionSoap[models.length][models[0].length];
        } else {
            soapModels = new HierarchyLevelDefinitionSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static HierarchyLevelDefinitionSoap[] toSoapModels(
        List<HierarchyLevelDefinition> models) {
        List<HierarchyLevelDefinitionSoap> soapModels = new ArrayList<HierarchyLevelDefinitionSoap>(models.size());

        for (HierarchyLevelDefinition model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new HierarchyLevelDefinitionSoap[soapModels.size()]);
    }

    public int getPrimaryKey() {
        return _hierarchyLevelDefinitionSid;
    }

    public void setPrimaryKey(int pk) {
        setHierarchyLevelDefinitionSid(pk);
    }

    public String getTableName() {
        return _tableName;
    }

    public void setTableName(String tableName) {
        _tableName = tableName;
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

    public String getLevelValueReference() {
        return _levelValueReference;
    }

    public void setLevelValueReference(String levelValueReference) {
        _levelValueReference = levelValueReference;
    }

    public String getFieldName() {
        return _fieldName;
    }

    public void setFieldName(String fieldName) {
        _fieldName = fieldName;
    }

    public int getLevelNo() {
        return _levelNo;
    }

    public void setLevelNo(int levelNo) {
        _levelNo = levelNo;
    }

    public int getHierarchyLevelDefinitionSid() {
        return _hierarchyLevelDefinitionSid;
    }

    public void setHierarchyLevelDefinitionSid(int hierarchyLevelDefinitionSid) {
        _hierarchyLevelDefinitionSid = hierarchyLevelDefinitionSid;
    }

    public int getHierarchyDefinitionSid() {
        return _hierarchyDefinitionSid;
    }

    public void setHierarchyDefinitionSid(int hierarchyDefinitionSid) {
        _hierarchyDefinitionSid = hierarchyDefinitionSid;
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
