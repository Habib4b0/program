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
public class NaProjectionSelectionSoap implements Serializable {
    private String _screenName;
    private String _fieldName;
    private String _fieldValues;
    private int _naProjectionSelectionSid;
    private int _naProjMasterSid;

    public NaProjectionSelectionSoap() {
    }

    public static NaProjectionSelectionSoap toSoapModel(
        NaProjectionSelection model) {
        NaProjectionSelectionSoap soapModel = new NaProjectionSelectionSoap();

        soapModel.setScreenName(model.getScreenName());
        soapModel.setFieldName(model.getFieldName());
        soapModel.setFieldValues(model.getFieldValues());
        soapModel.setNaProjectionSelectionSid(model.getNaProjectionSelectionSid());
        soapModel.setNaProjMasterSid(model.getNaProjMasterSid());

        return soapModel;
    }

    public static NaProjectionSelectionSoap[] toSoapModels(
        NaProjectionSelection[] models) {
        NaProjectionSelectionSoap[] soapModels = new NaProjectionSelectionSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static NaProjectionSelectionSoap[][] toSoapModels(
        NaProjectionSelection[][] models) {
        NaProjectionSelectionSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new NaProjectionSelectionSoap[models.length][models[0].length];
        } else {
            soapModels = new NaProjectionSelectionSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static NaProjectionSelectionSoap[] toSoapModels(
        List<NaProjectionSelection> models) {
        List<NaProjectionSelectionSoap> soapModels = new ArrayList<NaProjectionSelectionSoap>(models.size());

        for (NaProjectionSelection model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new NaProjectionSelectionSoap[soapModels.size()]);
    }

    public int getPrimaryKey() {
        return _naProjectionSelectionSid;
    }

    public void setPrimaryKey(int pk) {
        setNaProjectionSelectionSid(pk);
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

    public String getFieldValues() {
        return _fieldValues;
    }

    public void setFieldValues(String fieldValues) {
        _fieldValues = fieldValues;
    }

    public int getNaProjectionSelectionSid() {
        return _naProjectionSelectionSid;
    }

    public void setNaProjectionSelectionSid(int naProjectionSelectionSid) {
        _naProjectionSelectionSid = naProjectionSelectionSid;
    }

    public int getNaProjMasterSid() {
        return _naProjMasterSid;
    }

    public void setNaProjMasterSid(int naProjMasterSid) {
        _naProjMasterSid = naProjMasterSid;
    }
}
