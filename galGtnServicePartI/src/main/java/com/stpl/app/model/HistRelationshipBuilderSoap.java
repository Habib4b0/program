package com.stpl.app.model;

import com.stpl.app.service.persistence.HistRelationshipBuilderPK;

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
public class HistRelationshipBuilderSoap implements Serializable {
    private Date _startDate;
    private Date _createdDate;
    private int _createdBy;
    private String _relationshipDescription;
    private Date _actionDate;
    private String _actionFlag;
    private int _hierarchyDefinitionSid;
    private int _versionNo;
    private String _relationshipName;
    private int _relationshipBuilderSid;
    private int _hierarchyVersion;
    private int _modifiedBy;
    private Date _modifiedDate;
    private int _relationshipType;
    private String _buildType;

    public HistRelationshipBuilderSoap() {
    }

    public static HistRelationshipBuilderSoap toSoapModel(
        HistRelationshipBuilder model) {
        HistRelationshipBuilderSoap soapModel = new HistRelationshipBuilderSoap();

        soapModel.setStartDate(model.getStartDate());
        soapModel.setCreatedDate(model.getCreatedDate());
        soapModel.setCreatedBy(model.getCreatedBy());
        soapModel.setRelationshipDescription(model.getRelationshipDescription());
        soapModel.setActionDate(model.getActionDate());
        soapModel.setActionFlag(model.getActionFlag());
        soapModel.setHierarchyDefinitionSid(model.getHierarchyDefinitionSid());
        soapModel.setVersionNo(model.getVersionNo());
        soapModel.setRelationshipName(model.getRelationshipName());
        soapModel.setRelationshipBuilderSid(model.getRelationshipBuilderSid());
        soapModel.setHierarchyVersion(model.getHierarchyVersion());
        soapModel.setModifiedBy(model.getModifiedBy());
        soapModel.setModifiedDate(model.getModifiedDate());
        soapModel.setRelationshipType(model.getRelationshipType());
        soapModel.setBuildType(model.getBuildType());

        return soapModel;
    }

    public static HistRelationshipBuilderSoap[] toSoapModels(
        HistRelationshipBuilder[] models) {
        HistRelationshipBuilderSoap[] soapModels = new HistRelationshipBuilderSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static HistRelationshipBuilderSoap[][] toSoapModels(
        HistRelationshipBuilder[][] models) {
        HistRelationshipBuilderSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new HistRelationshipBuilderSoap[models.length][models[0].length];
        } else {
            soapModels = new HistRelationshipBuilderSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static HistRelationshipBuilderSoap[] toSoapModels(
        List<HistRelationshipBuilder> models) {
        List<HistRelationshipBuilderSoap> soapModels = new ArrayList<HistRelationshipBuilderSoap>(models.size());

        for (HistRelationshipBuilder model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new HistRelationshipBuilderSoap[soapModels.size()]);
    }

    public HistRelationshipBuilderPK getPrimaryKey() {
        return new HistRelationshipBuilderPK(_actionFlag, _versionNo,
            _relationshipBuilderSid);
    }

    public void setPrimaryKey(HistRelationshipBuilderPK pk) {
        setActionFlag(pk.actionFlag);
        setVersionNo(pk.versionNo);
        setRelationshipBuilderSid(pk.relationshipBuilderSid);
    }

    public Date getStartDate() {
        return _startDate;
    }

    public void setStartDate(Date startDate) {
        _startDate = startDate;
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

    public String getRelationshipDescription() {
        return _relationshipDescription;
    }

    public void setRelationshipDescription(String relationshipDescription) {
        _relationshipDescription = relationshipDescription;
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

    public String getRelationshipName() {
        return _relationshipName;
    }

    public void setRelationshipName(String relationshipName) {
        _relationshipName = relationshipName;
    }

    public int getRelationshipBuilderSid() {
        return _relationshipBuilderSid;
    }

    public void setRelationshipBuilderSid(int relationshipBuilderSid) {
        _relationshipBuilderSid = relationshipBuilderSid;
    }

    public int getHierarchyVersion() {
        return _hierarchyVersion;
    }

    public void setHierarchyVersion(int hierarchyVersion) {
        _hierarchyVersion = hierarchyVersion;
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

    public int getRelationshipType() {
        return _relationshipType;
    }

    public void setRelationshipType(int relationshipType) {
        _relationshipType = relationshipType;
    }

    public String getBuildType() {
        return _buildType;
    }

    public void setBuildType(String buildType) {
        _buildType = buildType;
    }
}
