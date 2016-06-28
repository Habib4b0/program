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
public class AdditionalNotesSoap implements Serializable {
    private Date _createdDate;
    private String _createdBy;
    private String _forecastType;
    private int _additionalNotesId;
    private int _projectionId;
    private String _notes;

    public AdditionalNotesSoap() {
    }

    public static AdditionalNotesSoap toSoapModel(AdditionalNotes model) {
        AdditionalNotesSoap soapModel = new AdditionalNotesSoap();

        soapModel.setCreatedDate(model.getCreatedDate());
        soapModel.setCreatedBy(model.getCreatedBy());
        soapModel.setForecastType(model.getForecastType());
        soapModel.setAdditionalNotesId(model.getAdditionalNotesId());
        soapModel.setProjectionId(model.getProjectionId());
        soapModel.setNotes(model.getNotes());

        return soapModel;
    }

    public static AdditionalNotesSoap[] toSoapModels(AdditionalNotes[] models) {
        AdditionalNotesSoap[] soapModels = new AdditionalNotesSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static AdditionalNotesSoap[][] toSoapModels(
        AdditionalNotes[][] models) {
        AdditionalNotesSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new AdditionalNotesSoap[models.length][models[0].length];
        } else {
            soapModels = new AdditionalNotesSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static AdditionalNotesSoap[] toSoapModels(
        List<AdditionalNotes> models) {
        List<AdditionalNotesSoap> soapModels = new ArrayList<AdditionalNotesSoap>(models.size());

        for (AdditionalNotes model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new AdditionalNotesSoap[soapModels.size()]);
    }

    public int getPrimaryKey() {
        return _additionalNotesId;
    }

    public void setPrimaryKey(int pk) {
        setAdditionalNotesId(pk);
    }

    public Date getCreatedDate() {
        return _createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        _createdDate = createdDate;
    }

    public String getCreatedBy() {
        return _createdBy;
    }

    public void setCreatedBy(String createdBy) {
        _createdBy = createdBy;
    }

    public String getForecastType() {
        return _forecastType;
    }

    public void setForecastType(String forecastType) {
        _forecastType = forecastType;
    }

    public int getAdditionalNotesId() {
        return _additionalNotesId;
    }

    public void setAdditionalNotesId(int additionalNotesId) {
        _additionalNotesId = additionalNotesId;
    }

    public int getProjectionId() {
        return _projectionId;
    }

    public void setProjectionId(int projectionId) {
        _projectionId = projectionId;
    }

    public String getNotes() {
        return _notes;
    }

    public void setNotes(String notes) {
        _notes = notes;
    }
}
