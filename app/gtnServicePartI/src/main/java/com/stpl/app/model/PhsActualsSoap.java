package com.stpl.app.model;

import com.stpl.app.service.persistence.PhsActualsPK;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author
 * @generated
 */
public class PhsActualsSoap implements Serializable {
    private int _periodSid;
    private String _priceType;
    private double _actualPrice;
    private String _notes;
    private int _naProjDetailsSid;

    public PhsActualsSoap() {
    }

    public static PhsActualsSoap toSoapModel(PhsActuals model) {
        PhsActualsSoap soapModel = new PhsActualsSoap();

        soapModel.setPeriodSid(model.getPeriodSid());
        soapModel.setPriceType(model.getPriceType());
        soapModel.setActualPrice(model.getActualPrice());
        soapModel.setNotes(model.getNotes());
        soapModel.setNaProjDetailsSid(model.getNaProjDetailsSid());

        return soapModel;
    }

    public static PhsActualsSoap[] toSoapModels(PhsActuals[] models) {
        PhsActualsSoap[] soapModels = new PhsActualsSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static PhsActualsSoap[][] toSoapModels(PhsActuals[][] models) {
        PhsActualsSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new PhsActualsSoap[models.length][models[0].length];
        } else {
            soapModels = new PhsActualsSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static PhsActualsSoap[] toSoapModels(List<PhsActuals> models) {
        List<PhsActualsSoap> soapModels = new ArrayList<PhsActualsSoap>(models.size());

        for (PhsActuals model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new PhsActualsSoap[soapModels.size()]);
    }

    public PhsActualsPK getPrimaryKey() {
        return new PhsActualsPK(_periodSid, _priceType, _naProjDetailsSid);
    }

    public void setPrimaryKey(PhsActualsPK pk) {
        setPeriodSid(pk.periodSid);
        setPriceType(pk.priceType);
        setNaProjDetailsSid(pk.naProjDetailsSid);
    }

    public int getPeriodSid() {
        return _periodSid;
    }

    public void setPeriodSid(int periodSid) {
        _periodSid = periodSid;
    }

    public String getPriceType() {
        return _priceType;
    }

    public void setPriceType(String priceType) {
        _priceType = priceType;
    }

    public double getActualPrice() {
        return _actualPrice;
    }

    public void setActualPrice(double actualPrice) {
        _actualPrice = actualPrice;
    }

    public String getNotes() {
        return _notes;
    }

    public void setNotes(String notes) {
        _notes = notes;
    }

    public int getNaProjDetailsSid() {
        return _naProjDetailsSid;
    }

    public void setNaProjDetailsSid(int naProjDetailsSid) {
        _naProjDetailsSid = naProjDetailsSid;
    }
}
