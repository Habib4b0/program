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
public class NmProjectionSelectionSoap implements Serializable {
    private String _screenName;
    private int _nmProjectionSelectionSid;
    private String _fieldName;
    private String _fieldValues;
    private int _projectionMasterSid;

    public NmProjectionSelectionSoap() {
    }

    public static NmProjectionSelectionSoap toSoapModel(
        NmProjectionSelection model) {
        NmProjectionSelectionSoap soapModel = new NmProjectionSelectionSoap();

        soapModel.setScreenName(model.getScreenName());
        soapModel.setNmProjectionSelectionSid(model.getNmProjectionSelectionSid());
        soapModel.setFieldName(model.getFieldName());
        soapModel.setFieldValues(model.getFieldValues());
        soapModel.setProjectionMasterSid(model.getProjectionMasterSid());

        return soapModel;
    }

    public static NmProjectionSelectionSoap[] toSoapModels(
        NmProjectionSelection[] models) {
        NmProjectionSelectionSoap[] soapModels = new NmProjectionSelectionSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static NmProjectionSelectionSoap[][] toSoapModels(
        NmProjectionSelection[][] models) {
        NmProjectionSelectionSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new NmProjectionSelectionSoap[models.length][models[0].length];
        } else {
            soapModels = new NmProjectionSelectionSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static NmProjectionSelectionSoap[] toSoapModels(
        List<NmProjectionSelection> models) {
        List<NmProjectionSelectionSoap> soapModels = new ArrayList<NmProjectionSelectionSoap>(models.size());

        for (NmProjectionSelection model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new NmProjectionSelectionSoap[soapModels.size()]);
    }

    public int getPrimaryKey() {
        return _nmProjectionSelectionSid;
    }

    public void setPrimaryKey(int pk) {
        setNmProjectionSelectionSid(pk);
    }

    public String getScreenName() {
        return _screenName;
    }

    public void setScreenName(String screenName) {
        _screenName = screenName;
    }

    public int getNmProjectionSelectionSid() {
        return _nmProjectionSelectionSid;
    }

    public void setNmProjectionSelectionSid(int nmProjectionSelectionSid) {
        _nmProjectionSelectionSid = nmProjectionSelectionSid;
    }

    public String getFieldName() {
        return _fieldName;
    }

    public void setFieldName(String fieldName) {
        _fieldName = fieldName;
    }

    public String getFieldValues() {
        return _fieldValues;
    }

    public void setFieldValues(String fieldValues) {
        _fieldValues = fieldValues;
    }

    public int getProjectionMasterSid() {
        return _projectionMasterSid;
    }

    public void setProjectionMasterSid(int projectionMasterSid) {
        _projectionMasterSid = projectionMasterSid;
    }
}
