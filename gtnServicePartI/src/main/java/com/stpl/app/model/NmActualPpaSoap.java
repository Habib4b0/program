package com.stpl.app.model;

import com.stpl.app.service.persistence.NmActualPpaPK;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author
 * @generated
 */
public class NmActualPpaSoap implements Serializable {
    private double _actualRate;
    private int _periodSid;
    private double _actualProjDiscountDollar;
    private double _actualProjectionSales;
    private int _projectionDetailsSid;
    private double _actualProjectionRate;
    private double _actualProjDiscountUnits;
    private double _actualDiscountDollar;
    private double _actualDiscountUnits;
    private double _actualSales;

    public NmActualPpaSoap() {
    }

    public static NmActualPpaSoap toSoapModel(NmActualPpa model) {
        NmActualPpaSoap soapModel = new NmActualPpaSoap();

        soapModel.setActualRate(model.getActualRate());
        soapModel.setPeriodSid(model.getPeriodSid());
        soapModel.setActualProjDiscountDollar(model.getActualProjDiscountDollar());
        soapModel.setActualProjectionSales(model.getActualProjectionSales());
        soapModel.setProjectionDetailsSid(model.getProjectionDetailsSid());
        soapModel.setActualProjectionRate(model.getActualProjectionRate());
        soapModel.setActualProjDiscountUnits(model.getActualProjDiscountUnits());
        soapModel.setActualDiscountDollar(model.getActualDiscountDollar());
        soapModel.setActualDiscountUnits(model.getActualDiscountUnits());
        soapModel.setActualSales(model.getActualSales());

        return soapModel;
    }

    public static NmActualPpaSoap[] toSoapModels(NmActualPpa[] models) {
        NmActualPpaSoap[] soapModels = new NmActualPpaSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static NmActualPpaSoap[][] toSoapModels(NmActualPpa[][] models) {
        NmActualPpaSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new NmActualPpaSoap[models.length][models[0].length];
        } else {
            soapModels = new NmActualPpaSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static NmActualPpaSoap[] toSoapModels(List<NmActualPpa> models) {
        List<NmActualPpaSoap> soapModels = new ArrayList<NmActualPpaSoap>(models.size());

        for (NmActualPpa model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new NmActualPpaSoap[soapModels.size()]);
    }

    public NmActualPpaPK getPrimaryKey() {
        return new NmActualPpaPK(_periodSid, _projectionDetailsSid);
    }

    public void setPrimaryKey(NmActualPpaPK pk) {
        setPeriodSid(pk.periodSid);
        setProjectionDetailsSid(pk.projectionDetailsSid);
    }

    public double getActualRate() {
        return _actualRate;
    }

    public void setActualRate(double actualRate) {
        _actualRate = actualRate;
    }

    public int getPeriodSid() {
        return _periodSid;
    }

    public void setPeriodSid(int periodSid) {
        _periodSid = periodSid;
    }

    public double getActualProjDiscountDollar() {
        return _actualProjDiscountDollar;
    }

    public void setActualProjDiscountDollar(double actualProjDiscountDollar) {
        _actualProjDiscountDollar = actualProjDiscountDollar;
    }

    public double getActualProjectionSales() {
        return _actualProjectionSales;
    }

    public void setActualProjectionSales(double actualProjectionSales) {
        _actualProjectionSales = actualProjectionSales;
    }

    public int getProjectionDetailsSid() {
        return _projectionDetailsSid;
    }

    public void setProjectionDetailsSid(int projectionDetailsSid) {
        _projectionDetailsSid = projectionDetailsSid;
    }

    public double getActualProjectionRate() {
        return _actualProjectionRate;
    }

    public void setActualProjectionRate(double actualProjectionRate) {
        _actualProjectionRate = actualProjectionRate;
    }

    public double getActualProjDiscountUnits() {
        return _actualProjDiscountUnits;
    }

    public void setActualProjDiscountUnits(double actualProjDiscountUnits) {
        _actualProjDiscountUnits = actualProjDiscountUnits;
    }

    public double getActualDiscountDollar() {
        return _actualDiscountDollar;
    }

    public void setActualDiscountDollar(double actualDiscountDollar) {
        _actualDiscountDollar = actualDiscountDollar;
    }

    public double getActualDiscountUnits() {
        return _actualDiscountUnits;
    }

    public void setActualDiscountUnits(double actualDiscountUnits) {
        _actualDiscountUnits = actualDiscountUnits;
    }

    public double getActualSales() {
        return _actualSales;
    }

    public void setActualSales(double actualSales) {
        _actualSales = actualSales;
    }
}
