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
public class MProjectionSelectionSoap implements Serializable {
    private int _mProjectionSelectionSid;
    private int _projectionMasterSid;
    private String _fieldValues;
    private String _fieldName;
    private String _screenName;

    public MProjectionSelectionSoap() {
    }

    public static MProjectionSelectionSoap toSoapModel(
        MProjectionSelection model) {
        MProjectionSelectionSoap soapModel = new MProjectionSelectionSoap();

        soapModel.setMProjectionSelectionSid(model.getMProjectionSelectionSid());
        soapModel.setProjectionMasterSid(model.getProjectionMasterSid());
        soapModel.setFieldValues(model.getFieldValues());
        soapModel.setFieldName(model.getFieldName());
        soapModel.setScreenName(model.getScreenName());

        return soapModel;
    }

    public static MProjectionSelectionSoap[] toSoapModels(
        MProjectionSelection[] models) {
        MProjectionSelectionSoap[] soapModels = new MProjectionSelectionSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static MProjectionSelectionSoap[][] toSoapModels(
        MProjectionSelection[][] models) {
        MProjectionSelectionSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new MProjectionSelectionSoap[models.length][models[0].length];
        } else {
            soapModels = new MProjectionSelectionSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static MProjectionSelectionSoap[] toSoapModels(
        List<MProjectionSelection> models) {
        List<MProjectionSelectionSoap> soapModels = new ArrayList<MProjectionSelectionSoap>(models.size());

        for (MProjectionSelection model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new MProjectionSelectionSoap[soapModels.size()]);
    }

    public int getPrimaryKey() {
        return _mProjectionSelectionSid;
    }

    public void setPrimaryKey(int pk) {
        setMProjectionSelectionSid(pk);
    }

    public int getMProjectionSelectionSid() {
        return _mProjectionSelectionSid;
    }

    public void setMProjectionSelectionSid(int mProjectionSelectionSid) {
        _mProjectionSelectionSid = mProjectionSelectionSid;
    }

    public int getProjectionMasterSid() {
        return _projectionMasterSid;
    }

    public void setProjectionMasterSid(int projectionMasterSid) {
        _projectionMasterSid = projectionMasterSid;
    }

    public String getFieldValues() {
        return _fieldValues;
    }

    public void setFieldValues(String fieldValues) {
        _fieldValues = fieldValues;
    }

    public String getFieldName() {
        return _fieldName;
    }

    public void setFieldName(String fieldName) {
        _fieldName = fieldName;
    }

    public String getScreenName() {
        return _screenName;
    }

    public void setScreenName(String screenName) {
        _screenName = screenName;
    }
}
