package com.stpl.app.model;

import com.stpl.app.service.persistence.NmActualDiscountPK;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author
 * @generated
 */
public class NmActualDiscountSoap implements Serializable {
    private double _actualRate;
    private int _periodSid;
    private int _projectionDetailsSid;
    private double _actualSales;

    public NmActualDiscountSoap() {
    }

    public static NmActualDiscountSoap toSoapModel(NmActualDiscount model) {
        NmActualDiscountSoap soapModel = new NmActualDiscountSoap();

        soapModel.setActualRate(model.getActualRate());
        soapModel.setPeriodSid(model.getPeriodSid());
        soapModel.setProjectionDetailsSid(model.getProjectionDetailsSid());
        soapModel.setActualSales(model.getActualSales());

        return soapModel;
    }

    public static NmActualDiscountSoap[] toSoapModels(NmActualDiscount[] models) {
        NmActualDiscountSoap[] soapModels = new NmActualDiscountSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static NmActualDiscountSoap[][] toSoapModels(
        NmActualDiscount[][] models) {
        NmActualDiscountSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new NmActualDiscountSoap[models.length][models[0].length];
        } else {
            soapModels = new NmActualDiscountSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static NmActualDiscountSoap[] toSoapModels(
        List<NmActualDiscount> models) {
        List<NmActualDiscountSoap> soapModels = new ArrayList<NmActualDiscountSoap>(models.size());

        for (NmActualDiscount model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new NmActualDiscountSoap[soapModels.size()]);
    }

    public NmActualDiscountPK getPrimaryKey() {
        return new NmActualDiscountPK(_periodSid, _projectionDetailsSid);
    }

    public void setPrimaryKey(NmActualDiscountPK pk) {
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

    public int getProjectionDetailsSid() {
        return _projectionDetailsSid;
    }

    public void setProjectionDetailsSid(int projectionDetailsSid) {
        _projectionDetailsSid = projectionDetailsSid;
    }

    public double getActualSales() {
        return _actualSales;
    }

    public void setActualSales(double actualSales) {
        _actualSales = actualSales;
    }
}
