package com.stpl.app.model;

import com.stpl.app.service.persistence.StNmActualDiscountPK;

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
public class StNmActualDiscountSoap implements Serializable {
    private double _actualSales;
    private int _periodSid;
    private double _actualRate;
    private int _userId;
    private Date _lastModifiedDate;
    private double _actualProjectionSales;
    private double _actualProjectionRate;
    private int _projectionDetailsSid;
    private int _rsModelSid;
    private int _sessionId;

    public StNmActualDiscountSoap() {
    }

    public static StNmActualDiscountSoap toSoapModel(StNmActualDiscount model) {
        StNmActualDiscountSoap soapModel = new StNmActualDiscountSoap();

        soapModel.setActualSales(model.getActualSales());
        soapModel.setPeriodSid(model.getPeriodSid());
        soapModel.setActualRate(model.getActualRate());
        soapModel.setUserId(model.getUserId());
        soapModel.setLastModifiedDate(model.getLastModifiedDate());
        soapModel.setActualProjectionSales(model.getActualProjectionSales());
        soapModel.setActualProjectionRate(model.getActualProjectionRate());
        soapModel.setProjectionDetailsSid(model.getProjectionDetailsSid());
        soapModel.setRsModelSid(model.getRsModelSid());
        soapModel.setSessionId(model.getSessionId());

        return soapModel;
    }

    public static StNmActualDiscountSoap[] toSoapModels(
        StNmActualDiscount[] models) {
        StNmActualDiscountSoap[] soapModels = new StNmActualDiscountSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static StNmActualDiscountSoap[][] toSoapModels(
        StNmActualDiscount[][] models) {
        StNmActualDiscountSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new StNmActualDiscountSoap[models.length][models[0].length];
        } else {
            soapModels = new StNmActualDiscountSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static StNmActualDiscountSoap[] toSoapModels(
        List<StNmActualDiscount> models) {
        List<StNmActualDiscountSoap> soapModels = new ArrayList<StNmActualDiscountSoap>(models.size());

        for (StNmActualDiscount model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new StNmActualDiscountSoap[soapModels.size()]);
    }

    public StNmActualDiscountPK getPrimaryKey() {
        return new StNmActualDiscountPK(_periodSid, _userId,
            _projectionDetailsSid, _rsModelSid, _sessionId);
    }

    public void setPrimaryKey(StNmActualDiscountPK pk) {
        setPeriodSid(pk.periodSid);
        setUserId(pk.userId);
        setProjectionDetailsSid(pk.projectionDetailsSid);
        setRsModelSid(pk.rsModelSid);
        setSessionId(pk.sessionId);
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

    public int getUserId() {
        return _userId;
    }

    public void setUserId(int userId) {
        _userId = userId;
    }

    public Date getLastModifiedDate() {
        return _lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        _lastModifiedDate = lastModifiedDate;
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

    public int getRsModelSid() {
        return _rsModelSid;
    }

    public void setRsModelSid(int rsModelSid) {
        _rsModelSid = rsModelSid;
    }

    public int getSessionId() {
        return _sessionId;
    }

    public void setSessionId(int sessionId) {
        _sessionId = sessionId;
    }
}
