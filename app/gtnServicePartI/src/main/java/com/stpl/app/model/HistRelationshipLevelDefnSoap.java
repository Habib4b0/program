package com.stpl.app.model;

import com.stpl.app.service.persistence.HistRelationshipLevelDefnPK;

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
public class HistRelationshipLevelDefnSoap implements Serializable {
    private String _relationshipLevelValues;
    private Date _actionDate;
    private int _hierarchyLevelDefinitionSid;
    private String _parentNode;
    private int _versionNo;
    private int _relationshipBuilderSid;
    private Date _modifiedDate;
    private int _createdBy;
    private Date _createdDate;
    private String _levelNo;
    private String _actionFlag;
    private String _hierarchyNo;
    private int _modifiedBy;
    private int _relationshipLevelSid;
    private String _flag;
    private String _levelName;

    public HistRelationshipLevelDefnSoap() {
    }

    public static HistRelationshipLevelDefnSoap toSoapModel(
        HistRelationshipLevelDefn model) {
        HistRelationshipLevelDefnSoap soapModel = new HistRelationshipLevelDefnSoap();

        soapModel.setRelationshipLevelValues(model.getRelationshipLevelValues());
        soapModel.setActionDate(model.getActionDate());
        soapModel.setHierarchyLevelDefinitionSid(model.getHierarchyLevelDefinitionSid());
        soapModel.setParentNode(model.getParentNode());
        soapModel.setVersionNo(model.getVersionNo());
        soapModel.setRelationshipBuilderSid(model.getRelationshipBuilderSid());
        soapModel.setModifiedDate(model.getModifiedDate());
        soapModel.setCreatedBy(model.getCreatedBy());
        soapModel.setCreatedDate(model.getCreatedDate());
        soapModel.setLevelNo(model.getLevelNo());
        soapModel.setActionFlag(model.getActionFlag());
        soapModel.setHierarchyNo(model.getHierarchyNo());
        soapModel.setModifiedBy(model.getModifiedBy());
        soapModel.setRelationshipLevelSid(model.getRelationshipLevelSid());
        soapModel.setFlag(model.getFlag());
        soapModel.setLevelName(model.getLevelName());

        return soapModel;
    }

    public static HistRelationshipLevelDefnSoap[] toSoapModels(
        HistRelationshipLevelDefn[] models) {
        HistRelationshipLevelDefnSoap[] soapModels = new HistRelationshipLevelDefnSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static HistRelationshipLevelDefnSoap[][] toSoapModels(
        HistRelationshipLevelDefn[][] models) {
        HistRelationshipLevelDefnSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new HistRelationshipLevelDefnSoap[models.length][models[0].length];
        } else {
            soapModels = new HistRelationshipLevelDefnSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static HistRelationshipLevelDefnSoap[] toSoapModels(
        List<HistRelationshipLevelDefn> models) {
        List<HistRelationshipLevelDefnSoap> soapModels = new ArrayList<HistRelationshipLevelDefnSoap>(models.size());

        for (HistRelationshipLevelDefn model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new HistRelationshipLevelDefnSoap[soapModels.size()]);
    }

    public HistRelationshipLevelDefnPK getPrimaryKey() {
        return new HistRelationshipLevelDefnPK(_versionNo, _actionFlag,
            _relationshipLevelSid);
    }

    public void setPrimaryKey(HistRelationshipLevelDefnPK pk) {
        setVersionNo(pk.versionNo);
        setActionFlag(pk.actionFlag);
        setRelationshipLevelSid(pk.relationshipLevelSid);
    }

    public String getRelationshipLevelValues() {
        return _relationshipLevelValues;
    }

    public void setRelationshipLevelValues(String relationshipLevelValues) {
        _relationshipLevelValues = relationshipLevelValues;
    }

    public Date getActionDate() {
        return _actionDate;
    }

    public void setActionDate(Date actionDate) {
        _actionDate = actionDate;
    }

    public int getHierarchyLevelDefinitionSid() {
        return _hierarchyLevelDefinitionSid;
    }

    public void setHierarchyLevelDefinitionSid(int hierarchyLevelDefinitionSid) {
        _hierarchyLevelDefinitionSid = hierarchyLevelDefinitionSid;
    }

    public String getParentNode() {
        return _parentNode;
    }

    public void setParentNode(String parentNode) {
        _parentNode = parentNode;
    }

    public int getVersionNo() {
        return _versionNo;
    }

    public void setVersionNo(int versionNo) {
        _versionNo = versionNo;
    }

    public int getRelationshipBuilderSid() {
        return _relationshipBuilderSid;
    }

    public void setRelationshipBuilderSid(int relationshipBuilderSid) {
        _relationshipBuilderSid = relationshipBuilderSid;
    }

    public Date getModifiedDate() {
        return _modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        _modifiedDate = modifiedDate;
    }

    public int getCreatedBy() {
        return _createdBy;
    }

    public void setCreatedBy(int createdBy) {
        _createdBy = createdBy;
    }

    public Date getCreatedDate() {
        return _createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        _createdDate = createdDate;
    }

    public String getLevelNo() {
        return _levelNo;
    }

    public void setLevelNo(String levelNo) {
        _levelNo = levelNo;
    }

    public String getActionFlag() {
        return _actionFlag;
    }

    public void setActionFlag(String actionFlag) {
        _actionFlag = actionFlag;
    }

    public String getHierarchyNo() {
        return _hierarchyNo;
    }

    public void setHierarchyNo(String hierarchyNo) {
        _hierarchyNo = hierarchyNo;
    }

    public int getModifiedBy() {
        return _modifiedBy;
    }

    public void setModifiedBy(int modifiedBy) {
        _modifiedBy = modifiedBy;
    }

    public int getRelationshipLevelSid() {
        return _relationshipLevelSid;
    }

    public void setRelationshipLevelSid(int relationshipLevelSid) {
        _relationshipLevelSid = relationshipLevelSid;
    }

    public String getFlag() {
        return _flag;
    }

    public void setFlag(String flag) {
        _flag = flag;
    }

    public String getLevelName() {
        return _levelName;
    }

    public void setLevelName(String levelName) {
        _levelName = levelName;
    }
}
