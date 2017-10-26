package com.stpl.app.model;

import com.stpl.app.service.persistence.HistHierarchyLevelValuesPK;

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
public class HistHierarchyLevelValuesSoap implements Serializable {
    private String _levelValues;
    private int _hierarchyLevelValuesSid;
    private Date _createdDate;
    private int _createdBy;
    private Date _actionDate;
    private String _actionFlag;
    private int _hierarchyLevelDefinitionSid;
    private int _versionNo;
    private int _modifiedBy;
    private Date _modifiedDate;

    public HistHierarchyLevelValuesSoap() {
    }

    public static HistHierarchyLevelValuesSoap toSoapModel(
        HistHierarchyLevelValues model) {
        HistHierarchyLevelValuesSoap soapModel = new HistHierarchyLevelValuesSoap();

        soapModel.setLevelValues(model.getLevelValues());
        soapModel.setHierarchyLevelValuesSid(model.getHierarchyLevelValuesSid());
        soapModel.setCreatedDate(model.getCreatedDate());
        soapModel.setCreatedBy(model.getCreatedBy());
        soapModel.setActionDate(model.getActionDate());
        soapModel.setActionFlag(model.getActionFlag());
        soapModel.setHierarchyLevelDefinitionSid(model.getHierarchyLevelDefinitionSid());
        soapModel.setVersionNo(model.getVersionNo());
        soapModel.setModifiedBy(model.getModifiedBy());
        soapModel.setModifiedDate(model.getModifiedDate());

        return soapModel;
    }

    public static HistHierarchyLevelValuesSoap[] toSoapModels(
        HistHierarchyLevelValues[] models) {
        HistHierarchyLevelValuesSoap[] soapModels = new HistHierarchyLevelValuesSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static HistHierarchyLevelValuesSoap[][] toSoapModels(
        HistHierarchyLevelValues[][] models) {
        HistHierarchyLevelValuesSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new HistHierarchyLevelValuesSoap[models.length][models[0].length];
        } else {
            soapModels = new HistHierarchyLevelValuesSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static HistHierarchyLevelValuesSoap[] toSoapModels(
        List<HistHierarchyLevelValues> models) {
        List<HistHierarchyLevelValuesSoap> soapModels = new ArrayList<HistHierarchyLevelValuesSoap>(models.size());

        for (HistHierarchyLevelValues model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new HistHierarchyLevelValuesSoap[soapModels.size()]);
    }

    public HistHierarchyLevelValuesPK getPrimaryKey() {
        return new HistHierarchyLevelValuesPK(_hierarchyLevelValuesSid,
            _actionFlag, _versionNo);
    }

    public void setPrimaryKey(HistHierarchyLevelValuesPK pk) {
        setHierarchyLevelValuesSid(pk.hierarchyLevelValuesSid);
        setActionFlag(pk.actionFlag);
        setVersionNo(pk.versionNo);
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
