package com.stpl.app.model;

import com.stpl.app.service.persistence.ChActualDiscountPK;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author
 * @generated
 */
public class ChActualDiscountSoap implements Serializable {
    private double _actualRate;
    private int _periodSid;
    private int _projectionDetailsSid;
    private int _rsModelSid;
    private double _actualSales;

    public ChActualDiscountSoap() {
    }

    public static ChActualDiscountSoap toSoapModel(ChActualDiscount model) {
        ChActualDiscountSoap soapModel = new ChActualDiscountSoap();

        soapModel.setActualRate(model.getActualRate());
        soapModel.setPeriodSid(model.getPeriodSid());
        soapModel.setProjectionDetailsSid(model.getProjectionDetailsSid());
        soapModel.setRsModelSid(model.getRsModelSid());
        soapModel.setActualSales(model.getActualSales());

        return soapModel;
    }

    public static ChActualDiscountSoap[] toSoapModels(ChActualDiscount[] models) {
        ChActualDiscountSoap[] soapModels = new ChActualDiscountSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static ChActualDiscountSoap[][] toSoapModels(
        ChActualDiscount[][] models) {
        ChActualDiscountSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new ChActualDiscountSoap[models.length][models[0].length];
        } else {
            soapModels = new ChActualDiscountSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static ChActualDiscountSoap[] toSoapModels(
        List<ChActualDiscount> models) {
        List<ChActualDiscountSoap> soapModels = new ArrayList<ChActualDiscountSoap>(models.size());

        for (ChActualDiscount model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new ChActualDiscountSoap[soapModels.size()]);
    }

    public ChActualDiscountPK getPrimaryKey() {
        return new ChActualDiscountPK(_periodSid, _projectionDetailsSid,
            _rsModelSid);
    }

    public void setPrimaryKey(ChActualDiscountPK pk) {
        setPeriodSid(pk.periodSid);
        setProjectionDetailsSid(pk.projectionDetailsSid);
        setRsModelSid(pk.rsModelSid);
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

    public int getRsModelSid() {
        return _rsModelSid;
    }

    public void setRsModelSid(int rsModelSid) {
        _rsModelSid = rsModelSid;
    }

    public double getActualSales() {
        return _actualSales;
    }

    public void setActualSales(double actualSales) {
        _actualSales = actualSales;
    }
}
