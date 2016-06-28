package com.stpl.app.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author
 * @generated
 */
public class ProjectionCustHierarchySoap implements Serializable {
    private int _projectionMasterSid;
    private int _projectionCustHierarchySid;
    private int _relationshipLevelSid;

    public ProjectionCustHierarchySoap() {
    }

    public static ProjectionCustHierarchySoap toSoapModel(
        ProjectionCustHierarchy model) {
        ProjectionCustHierarchySoap soapModel = new ProjectionCustHierarchySoap();

        soapModel.setProjectionMasterSid(model.getProjectionMasterSid());
        soapModel.setProjectionCustHierarchySid(model.getProjectionCustHierarchySid());
        soapModel.setRelationshipLevelSid(model.getRelationshipLevelSid());

        return soapModel;
    }

    public static ProjectionCustHierarchySoap[] toSoapModels(
        ProjectionCustHierarchy[] models) {
        ProjectionCustHierarchySoap[] soapModels = new ProjectionCustHierarchySoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static ProjectionCustHierarchySoap[][] toSoapModels(
        ProjectionCustHierarchy[][] models) {
        ProjectionCustHierarchySoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new ProjectionCustHierarchySoap[models.length][models[0].length];
        } else {
            soapModels = new ProjectionCustHierarchySoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static ProjectionCustHierarchySoap[] toSoapModels(
        List<ProjectionCustHierarchy> models) {
        List<ProjectionCustHierarchySoap> soapModels = new ArrayList<ProjectionCustHierarchySoap>(models.size());

        for (ProjectionCustHierarchy model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new ProjectionCustHierarchySoap[soapModels.size()]);
    }

    public int getPrimaryKey() {
        return _projectionCustHierarchySid;
    }

    public void setPrimaryKey(int pk) {
        setProjectionCustHierarchySid(pk);
    }

    public int getProjectionMasterSid() {
        return _projectionMasterSid;
    }

    public void setProjectionMasterSid(int projectionMasterSid) {
        _projectionMasterSid = projectionMasterSid;
    }

    public int getProjectionCustHierarchySid() {
        return _projectionCustHierarchySid;
    }

    public void setProjectionCustHierarchySid(int projectionCustHierarchySid) {
        _projectionCustHierarchySid = projectionCustHierarchySid;
    }

    public int getRelationshipLevelSid() {
        return _relationshipLevelSid;
    }

    public void setRelationshipLevelSid(int relationshipLevelSid) {
        _relationshipLevelSid = relationshipLevelSid;
    }
}
