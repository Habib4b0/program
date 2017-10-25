package com.stpl.app.model;

import com.stpl.app.service.persistence.MedicaidUraActualsPK;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author
 * @generated
 */
public class MedicaidUraActualsSoap implements Serializable {
    private int _periodSid;
    private String _priceType;
    private double _actualPrice;
    private String _notes;
    private int _naProjDetailsSid;
    private double _baseYear;

    public MedicaidUraActualsSoap() {
    }

    public static MedicaidUraActualsSoap toSoapModel(MedicaidUraActuals model) {
        MedicaidUraActualsSoap soapModel = new MedicaidUraActualsSoap();

        soapModel.setPeriodSid(model.getPeriodSid());
        soapModel.setPriceType(model.getPriceType());
        soapModel.setActualPrice(model.getActualPrice());
        soapModel.setNotes(model.getNotes());
        soapModel.setNaProjDetailsSid(model.getNaProjDetailsSid());
        soapModel.setBaseYear(model.getBaseYear());

        return soapModel;
    }

    public static MedicaidUraActualsSoap[] toSoapModels(
        MedicaidUraActuals[] models) {
        MedicaidUraActualsSoap[] soapModels = new MedicaidUraActualsSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static MedicaidUraActualsSoap[][] toSoapModels(
        MedicaidUraActuals[][] models) {
        MedicaidUraActualsSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new MedicaidUraActualsSoap[models.length][models[0].length];
        } else {
            soapModels = new MedicaidUraActualsSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static MedicaidUraActualsSoap[] toSoapModels(
        List<MedicaidUraActuals> models) {
        List<MedicaidUraActualsSoap> soapModels = new ArrayList<MedicaidUraActualsSoap>(models.size());

        for (MedicaidUraActuals model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new MedicaidUraActualsSoap[soapModels.size()]);
    }

    public MedicaidUraActualsPK getPrimaryKey() {
        return new MedicaidUraActualsPK(_periodSid, _priceType,
            _naProjDetailsSid);
    }

    public void setPrimaryKey(MedicaidUraActualsPK pk) {
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

    public double getBaseYear() {
        return _baseYear;
    }

    public void setBaseYear(double baseYear) {
        _baseYear = baseYear;
    }
}
