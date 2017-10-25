package com.stpl.app.model;

import com.stpl.app.service.persistence.MSupplementalDiscActualsPK;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author
 * @generated
 */
public class MSupplementalDiscActualsSoap implements Serializable {
    private double _actualSales;
    private int _periodSid;
    private double _actualRate;
    private double _actualProjectionSales;
    private double _actualProjectionRate;
    private int _projectionDetailsSid;

    public MSupplementalDiscActualsSoap() {
    }

    public static MSupplementalDiscActualsSoap toSoapModel(
        MSupplementalDiscActuals model) {
        MSupplementalDiscActualsSoap soapModel = new MSupplementalDiscActualsSoap();

        soapModel.setActualSales(model.getActualSales());
        soapModel.setPeriodSid(model.getPeriodSid());
        soapModel.setActualRate(model.getActualRate());
        soapModel.setActualProjectionSales(model.getActualProjectionSales());
        soapModel.setActualProjectionRate(model.getActualProjectionRate());
        soapModel.setProjectionDetailsSid(model.getProjectionDetailsSid());

        return soapModel;
    }

    public static MSupplementalDiscActualsSoap[] toSoapModels(
        MSupplementalDiscActuals[] models) {
        MSupplementalDiscActualsSoap[] soapModels = new MSupplementalDiscActualsSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static MSupplementalDiscActualsSoap[][] toSoapModels(
        MSupplementalDiscActuals[][] models) {
        MSupplementalDiscActualsSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new MSupplementalDiscActualsSoap[models.length][models[0].length];
        } else {
            soapModels = new MSupplementalDiscActualsSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static MSupplementalDiscActualsSoap[] toSoapModels(
        List<MSupplementalDiscActuals> models) {
        List<MSupplementalDiscActualsSoap> soapModels = new ArrayList<MSupplementalDiscActualsSoap>(models.size());

        for (MSupplementalDiscActuals model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new MSupplementalDiscActualsSoap[soapModels.size()]);
    }

    public MSupplementalDiscActualsPK getPrimaryKey() {
        return new MSupplementalDiscActualsPK(_periodSid, _projectionDetailsSid);
    }

    public void setPrimaryKey(MSupplementalDiscActualsPK pk) {
        setPeriodSid(pk.periodSid);
        setProjectionDetailsSid(pk.projectionDetailsSid);
    }

    public double getActualSales() {
        return _actualSales;
    }

    public void setActualSales(double actualSales) {
        _actualSales = actualSales;
    }

    public int getPeriodSid() {
        return _periodSid;
    }

    public void setPeriodSid(int periodSid) {
        _periodSid = periodSid;
    }

    public double getActualRate() {
        return _actualRate;
    }

    public void setActualRate(double actualRate) {
        _actualRate = actualRate;
    }

    public double getActualProjectionSales() {
        return _actualProjectionSales;
    }

    public void setActualProjectionSales(double actualProjectionSales) {
        _actualProjectionSales = actualProjectionSales;
    }

    public double getActualProjectionRate() {
        return _actualProjectionRate;
    }

    public void setActualProjectionRate(double actualProjectionRate) {
        _actualProjectionRate = actualProjectionRate;
    }

    public int getProjectionDetailsSid() {
        return _projectionDetailsSid;
    }

    public void setProjectionDetailsSid(int projectionDetailsSid) {
        _projectionDetailsSid = projectionDetailsSid;
    }
}
