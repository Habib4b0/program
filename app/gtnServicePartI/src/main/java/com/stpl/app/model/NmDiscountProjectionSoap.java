package com.stpl.app.model;

import com.stpl.app.service.persistence.NmDiscountProjectionPK;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author
 * @generated
 */
public class NmDiscountProjectionSoap implements Serializable {
    private int _periodSid;
    private double _projectionRate;
    private int _projectionDetailsSid;
    private double _projectionSales;

    public NmDiscountProjectionSoap() {
    }

    public static NmDiscountProjectionSoap toSoapModel(
        NmDiscountProjection model) {
        NmDiscountProjectionSoap soapModel = new NmDiscountProjectionSoap();

        soapModel.setPeriodSid(model.getPeriodSid());
        soapModel.setProjectionRate(model.getProjectionRate());
        soapModel.setProjectionDetailsSid(model.getProjectionDetailsSid());
        soapModel.setProjectionSales(model.getProjectionSales());

        return soapModel;
    }

    public static NmDiscountProjectionSoap[] toSoapModels(
        NmDiscountProjection[] models) {
        NmDiscountProjectionSoap[] soapModels = new NmDiscountProjectionSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static NmDiscountProjectionSoap[][] toSoapModels(
        NmDiscountProjection[][] models) {
        NmDiscountProjectionSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new NmDiscountProjectionSoap[models.length][models[0].length];
        } else {
            soapModels = new NmDiscountProjectionSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static NmDiscountProjectionSoap[] toSoapModels(
        List<NmDiscountProjection> models) {
        List<NmDiscountProjectionSoap> soapModels = new ArrayList<NmDiscountProjectionSoap>(models.size());

        for (NmDiscountProjection model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new NmDiscountProjectionSoap[soapModels.size()]);
    }

    public NmDiscountProjectionPK getPrimaryKey() {
        return new NmDiscountProjectionPK(_periodSid, _projectionDetailsSid);
    }

    public void setPrimaryKey(NmDiscountProjectionPK pk) {
        setPeriodSid(pk.periodSid);
        setProjectionDetailsSid(pk.projectionDetailsSid);
    }

    public int getPeriodSid() {
        return _periodSid;
    }

    public void setPeriodSid(int periodSid) {
        _periodSid = periodSid;
    }

    public double getProjectionRate() {
        return _projectionRate;
    }

    public void setProjectionRate(double projectionRate) {
        _projectionRate = projectionRate;
    }

    public int getProjectionDetailsSid() {
        return _projectionDetailsSid;
    }

    public void setProjectionDetailsSid(int projectionDetailsSid) {
        _projectionDetailsSid = projectionDetailsSid;
    }

    public double getProjectionSales() {
        return _projectionSales;
    }

    public void setProjectionSales(double projectionSales) {
        _projectionSales = projectionSales;
    }
}
