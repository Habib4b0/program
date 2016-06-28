package com.stpl.app.parttwo.model;

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
public class CffAdditionalInfoSoap implements Serializable {
    private Date _createdDate;
    private int _createdBy;
    private int _cffMasterSid;
    private int _cffAdditionalInfoSid;
    private String _notes;

    public CffAdditionalInfoSoap() {
    }

    public static CffAdditionalInfoSoap toSoapModel(CffAdditionalInfo model) {
        CffAdditionalInfoSoap soapModel = new CffAdditionalInfoSoap();

        soapModel.setCreatedDate(model.getCreatedDate());
        soapModel.setCreatedBy(model.getCreatedBy());
        soapModel.setCffMasterSid(model.getCffMasterSid());
        soapModel.setCffAdditionalInfoSid(model.getCffAdditionalInfoSid());
        soapModel.setNotes(model.getNotes());

        return soapModel;
    }

    public static CffAdditionalInfoSoap[] toSoapModels(
        CffAdditionalInfo[] models) {
        CffAdditionalInfoSoap[] soapModels = new CffAdditionalInfoSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static CffAdditionalInfoSoap[][] toSoapModels(
        CffAdditionalInfo[][] models) {
        CffAdditionalInfoSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new CffAdditionalInfoSoap[models.length][models[0].length];
        } else {
            soapModels = new CffAdditionalInfoSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static CffAdditionalInfoSoap[] toSoapModels(
        List<CffAdditionalInfo> models) {
        List<CffAdditionalInfoSoap> soapModels = new ArrayList<CffAdditionalInfoSoap>(models.size());

        for (CffAdditionalInfo model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new CffAdditionalInfoSoap[soapModels.size()]);
    }

    public int getPrimaryKey() {
        return _cffAdditionalInfoSid;
    }

    public void setPrimaryKey(int pk) {
        setCffAdditionalInfoSid(pk);
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

    public int getCffMasterSid() {
        return _cffMasterSid;
    }

    public void setCffMasterSid(int cffMasterSid) {
        _cffMasterSid = cffMasterSid;
    }

    public int getCffAdditionalInfoSid() {
        return _cffAdditionalInfoSid;
    }

    public void setCffAdditionalInfoSid(int cffAdditionalInfoSid) {
        _cffAdditionalInfoSid = cffAdditionalInfoSid;
    }

    public String getNotes() {
        return _notes;
    }

    public void setNotes(String notes) {
        _notes = notes;
    }
}
