package com.stpl.app.model;

import com.stpl.app.service.persistence.FcpActualsPK;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author
 * @generated
 */
public class FcpActualsSoap implements Serializable {
    private int _periodSid;
    private String _priceType;
    private double _actualPrice;
    private String _notes;
    private int _naProjDetailsSid;

    public FcpActualsSoap() {
    }

    public static FcpActualsSoap toSoapModel(FcpActuals model) {
        FcpActualsSoap soapModel = new FcpActualsSoap();

        soapModel.setPeriodSid(model.getPeriodSid());
        soapModel.setPriceType(model.getPriceType());
        soapModel.setActualPrice(model.getActualPrice());
        soapModel.setNotes(model.getNotes());
        soapModel.setNaProjDetailsSid(model.getNaProjDetailsSid());

        return soapModel;
    }

    public static FcpActualsSoap[] toSoapModels(FcpActuals[] models) {
        FcpActualsSoap[] soapModels = new FcpActualsSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static FcpActualsSoap[][] toSoapModels(FcpActuals[][] models) {
        FcpActualsSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new FcpActualsSoap[models.length][models[0].length];
        } else {
            soapModels = new FcpActualsSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static FcpActualsSoap[] toSoapModels(List<FcpActuals> models) {
        List<FcpActualsSoap> soapModels = new ArrayList<FcpActualsSoap>(models.size());

        for (FcpActuals model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new FcpActualsSoap[soapModels.size()]);
    }

    public FcpActualsPK getPrimaryKey() {
        return new FcpActualsPK(_periodSid, _priceType, _naProjDetailsSid);
    }

    public void setPrimaryKey(FcpActualsPK pk) {
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
