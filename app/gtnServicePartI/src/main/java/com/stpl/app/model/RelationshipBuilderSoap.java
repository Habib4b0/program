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
public class RelationshipBuilderSoap implements Serializable {
    private Date _startDate;
    private Date _createdDate;
    private int _createdBy;
    private String _relationshipDescription;
    private int _hierarchyDefinitionSid;
    private int _versionNo;
    private String _relationshipName;
    private int _relationshipBuilderSid;
    private int _hierarchyVersion;
    private int _modifiedBy;
    private Date _modifiedDate;
    private int _deductionRelation;
    private int _relationshipType;
    private String _buildType;

    public RelationshipBuilderSoap() {
    }

    public static RelationshipBuilderSoap toSoapModel(RelationshipBuilder model) {
        RelationshipBuilderSoap soapModel = new RelationshipBuilderSoap();

        soapModel.setStartDate(model.getStartDate());
        soapModel.setCreatedDate(model.getCreatedDate());
        soapModel.setCreatedBy(model.getCreatedBy());
        soapModel.setRelationshipDescription(model.getRelationshipDescription());
        soapModel.setHierarchyDefinitionSid(model.getHierarchyDefinitionSid());
        soapModel.setVersionNo(model.getVersionNo());
        soapModel.setRelationshipName(model.getRelationshipName());
        soapModel.setRelationshipBuilderSid(model.getRelationshipBuilderSid());
        soapModel.setHierarchyVersion(model.getHierarchyVersion());
        soapModel.setModifiedBy(model.getModifiedBy());
        soapModel.setModifiedDate(model.getModifiedDate());
        soapModel.setDeductionRelation(model.getDeductionRelation());
        soapModel.setRelationshipType(model.getRelationshipType());
        soapModel.setBuildType(model.getBuildType());

        return soapModel;
    }

    public static RelationshipBuilderSoap[] toSoapModels(
        RelationshipBuilder[] models) {
        RelationshipBuilderSoap[] soapModels = new RelationshipBuilderSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static RelationshipBuilderSoap[][] toSoapModels(
        RelationshipBuilder[][] models) {
        RelationshipBuilderSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new RelationshipBuilderSoap[models.length][models[0].length];
        } else {
            soapModels = new RelationshipBuilderSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static RelationshipBuilderSoap[] toSoapModels(
        List<RelationshipBuilder> models) {
        List<RelationshipBuilderSoap> soapModels = new ArrayList<RelationshipBuilderSoap>(models.size());

        for (RelationshipBuilder model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new RelationshipBuilderSoap[soapModels.size()]);
    }

    public int getPrimaryKey() {
        return _relationshipBuilderSid;
    }

    public void setPrimaryKey(int pk) {
        setRelationshipBuilderSid(pk);
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

    public int getDeductionRelation() {
        return _deductionRelation;
    }

    public void setDeductionRelation(int deductionRelation) {
        _deductionRelation = deductionRelation;
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
