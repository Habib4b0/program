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
public class ChProjectionSelectionSoap implements Serializable {
    private String _screenName;
    private String _fieldName;
    private int _chProjectionSelectionSid;
    private String _fieldValues;
    private int _projectionMasterSid;

    public ChProjectionSelectionSoap() {
    }

    public static ChProjectionSelectionSoap toSoapModel(
        ChProjectionSelection model) {
        ChProjectionSelectionSoap soapModel = new ChProjectionSelectionSoap();

        soapModel.setScreenName(model.getScreenName());
        soapModel.setFieldName(model.getFieldName());
        soapModel.setChProjectionSelectionSid(model.getChProjectionSelectionSid());
        soapModel.setFieldValues(model.getFieldValues());
        soapModel.setProjectionMasterSid(model.getProjectionMasterSid());

        return soapModel;
    }

    public static ChProjectionSelectionSoap[] toSoapModels(
        ChProjectionSelection[] models) {
        ChProjectionSelectionSoap[] soapModels = new ChProjectionSelectionSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static ChProjectionSelectionSoap[][] toSoapModels(
        ChProjectionSelection[][] models) {
        ChProjectionSelectionSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new ChProjectionSelectionSoap[models.length][models[0].length];
        } else {
            soapModels = new ChProjectionSelectionSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static ChProjectionSelectionSoap[] toSoapModels(
        List<ChProjectionSelection> models) {
        List<ChProjectionSelectionSoap> soapModels = new ArrayList<ChProjectionSelectionSoap>(models.size());

        for (ChProjectionSelection model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new ChProjectionSelectionSoap[soapModels.size()]);
    }

    public int getPrimaryKey() {
        return _chProjectionSelectionSid;
    }

    public void setPrimaryKey(int pk) {
        setChProjectionSelectionSid(pk);
    }

    public String getScreenName() {
        return _screenName;
    }

    public void setScreenName(String screenName) {
        _screenName = screenName;
    }

    public String getFieldName() {
        return _fieldName;
    }

    public void setFieldName(String fieldName) {
        _fieldName = fieldName;
    }

    public int getChProjectionSelectionSid() {
        return _chProjectionSelectionSid;
    }

    public void setChProjectionSelectionSid(int chProjectionSelectionSid) {
        _chProjectionSelectionSid = chProjectionSelectionSid;
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
