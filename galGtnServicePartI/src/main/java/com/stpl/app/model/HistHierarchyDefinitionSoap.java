package com.stpl.app.model;

import com.stpl.app.service.persistence.HistHierarchyDefinitionPK;

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
public class HistHierarchyDefinitionSoap implements Serializable {
    private int _noOfLevels;
    private int _createdBy;
    private Date _actionDate;
    private String _actionFlag;
    private int _modifiedBy;
    private int _hierarchyDefinitionSid;
    private Date _createdDate;
    private int _hierarchyType;
    private int _hierarchyCategory;
    private String _hierarchyName;
    private int _versionNo;
    private Date _modifiedDate;

    public HistHierarchyDefinitionSoap() {
    }

    public static HistHierarchyDefinitionSoap toSoapModel(
        HistHierarchyDefinition model) {
        HistHierarchyDefinitionSoap soapModel = new HistHierarchyDefinitionSoap();

        soapModel.setNoOfLevels(model.getNoOfLevels());
        soapModel.setCreatedBy(model.getCreatedBy());
        soapModel.setActionDate(model.getActionDate());
        soapModel.setActionFlag(model.getActionFlag());
        soapModel.setModifiedBy(model.getModifiedBy());
        soapModel.setHierarchyDefinitionSid(model.getHierarchyDefinitionSid());
        soapModel.setCreatedDate(model.getCreatedDate());
        soapModel.setHierarchyType(model.getHierarchyType());
        soapModel.setHierarchyCategory(model.getHierarchyCategory());
        soapModel.setHierarchyName(model.getHierarchyName());
        soapModel.setVersionNo(model.getVersionNo());
        soapModel.setModifiedDate(model.getModifiedDate());

        return soapModel;
    }

    public static HistHierarchyDefinitionSoap[] toSoapModels(
        HistHierarchyDefinition[] models) {
        HistHierarchyDefinitionSoap[] soapModels = new HistHierarchyDefinitionSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static HistHierarchyDefinitionSoap[][] toSoapModels(
        HistHierarchyDefinition[][] models) {
        HistHierarchyDefinitionSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new HistHierarchyDefinitionSoap[models.length][models[0].length];
        } else {
            soapModels = new HistHierarchyDefinitionSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static HistHierarchyDefinitionSoap[] toSoapModels(
        List<HistHierarchyDefinition> models) {
        List<HistHierarchyDefinitionSoap> soapModels = new ArrayList<HistHierarchyDefinitionSoap>(models.size());

        for (HistHierarchyDefinition model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new HistHierarchyDefinitionSoap[soapModels.size()]);
    }

    public HistHierarchyDefinitionPK getPrimaryKey() {
        return new HistHierarchyDefinitionPK(_actionFlag,
            _hierarchyDefinitionSid, _versionNo);
    }

    public void setPrimaryKey(HistHierarchyDefinitionPK pk) {
        setActionFlag(pk.actionFlag);
        setHierarchyDefinitionSid(pk.hierarchyDefinitionSid);
        setVersionNo(pk.versionNo);
    }

    public int getNoOfLevels() {
        return _noOfLevels;
    }

    public void setNoOfLevels(int noOfLevels) {
        _noOfLevels = noOfLevels;
    }

    public int getCreatedBy() {
        return _createdBy;
    }

    public void setCreatedBy(int createdBy) {
        _createdBy = createdBy;
    }

    public Date getActionDate() {
        return _actionDate;
    }

    public void setActionDate(Date actionDate) {
        _actionDate = actionDate;
    }

    public String getActionFlag() {
        return _actionFlag;
    }

    public void setActionFlag(String actionFlag) {
        _actionFlag = actionFlag;
    }

    public int getModifiedBy() {
        return _modifiedBy;
    }

    public void setModifiedBy(int modifiedBy) {
        _modifiedBy = modifiedBy;
    }

    public int getHierarchyDefinitionSid() {
        return _hierarchyDefinitionSid;
    }

    public void setHierarchyDefinitionSid(int hierarchyDefinitionSid) {
        _hierarchyDefinitionSid = hierarchyDefinitionSid;
    }

    public Date getCreatedDate() {
        return _createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        _createdDate = createdDate;
    }

    public int getHierarchyType() {
        return _hierarchyType;
    }

    public void setHierarchyType(int hierarchyType) {
        _hierarchyType = hierarchyType;
    }

    public int getHierarchyCategory() {
        return _hierarchyCategory;
    }

    public void setHierarchyCategory(int hierarchyCategory) {
        _hierarchyCategory = hierarchyCategory;
    }

    public String getHierarchyName() {
        return _hierarchyName;
    }

    public void setHierarchyName(String hierarchyName) {
        _hierarchyName = hierarchyName;
    }

    public int getVersionNo() {
        return _versionNo;
    }

    public void setVersionNo(int versionNo) {
        _versionNo = versionNo;
    }

    public Date getModifiedDate() {
        return _modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        _modifiedDate = modifiedDate;
    }
}
