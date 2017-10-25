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
public class ProjectionDetailsSoap implements Serializable {
    private int _projectionDetailsSid;
    private int _ccpDetailsSid;
    private int _projectionMasterSid;

    public ProjectionDetailsSoap() {
    }

    public static ProjectionDetailsSoap toSoapModel(ProjectionDetails model) {
        ProjectionDetailsSoap soapModel = new ProjectionDetailsSoap();

        soapModel.setProjectionDetailsSid(model.getProjectionDetailsSid());
        soapModel.setCcpDetailsSid(model.getCcpDetailsSid());
        soapModel.setProjectionMasterSid(model.getProjectionMasterSid());

        return soapModel;
    }

    public static ProjectionDetailsSoap[] toSoapModels(
        ProjectionDetails[] models) {
        ProjectionDetailsSoap[] soapModels = new ProjectionDetailsSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static ProjectionDetailsSoap[][] toSoapModels(
        ProjectionDetails[][] models) {
        ProjectionDetailsSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new ProjectionDetailsSoap[models.length][models[0].length];
        } else {
            soapModels = new ProjectionDetailsSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static ProjectionDetailsSoap[] toSoapModels(
        List<ProjectionDetails> models) {
        List<ProjectionDetailsSoap> soapModels = new ArrayList<ProjectionDetailsSoap>(models.size());

        for (ProjectionDetails model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new ProjectionDetailsSoap[soapModels.size()]);
    }

    public int getPrimaryKey() {
        return _projectionDetailsSid;
    }

    public void setPrimaryKey(int pk) {
        setProjectionDetailsSid(pk);
    }

    public int getProjectionDetailsSid() {
        return _projectionDetailsSid;
    }

    public void setProjectionDetailsSid(int projectionDetailsSid) {
        _projectionDetailsSid = projectionDetailsSid;
    }

    public int getCcpDetailsSid() {
        return _ccpDetailsSid;
    }

    public void setCcpDetailsSid(int ccpDetailsSid) {
        _ccpDetailsSid = ccpDetailsSid;
    }

    public int getProjectionMasterSid() {
        return _projectionMasterSid;
    }

    public void setProjectionMasterSid(int projectionMasterSid) {
        _projectionMasterSid = projectionMasterSid;
    }
}
