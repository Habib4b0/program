package com.stpl.app.model;

import com.stpl.app.service.persistence.StChActualDiscountPK;

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
public class StChActualDiscountSoap implements Serializable {
    private Date _lastModifiedDate;
    private double _actualRate;
    private int _periodSid;
    private int _projectionDetailsSid;
    private int _userId;
    private int _sessionId;
    private int _rsModelSid;
    private double _actualSales;

    public StChActualDiscountSoap() {
    }

    public static StChActualDiscountSoap toSoapModel(StChActualDiscount model) {
        StChActualDiscountSoap soapModel = new StChActualDiscountSoap();

        soapModel.setLastModifiedDate(model.getLastModifiedDate());
        soapModel.setActualRate(model.getActualRate());
        soapModel.setPeriodSid(model.getPeriodSid());
        soapModel.setProjectionDetailsSid(model.getProjectionDetailsSid());
        soapModel.setUserId(model.getUserId());
        soapModel.setSessionId(model.getSessionId());
        soapModel.setRsModelSid(model.getRsModelSid());
        soapModel.setActualSales(model.getActualSales());

        return soapModel;
    }

    public static StChActualDiscountSoap[] toSoapModels(
        StChActualDiscount[] models) {
        StChActualDiscountSoap[] soapModels = new StChActualDiscountSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static StChActualDiscountSoap[][] toSoapModels(
        StChActualDiscount[][] models) {
        StChActualDiscountSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new StChActualDiscountSoap[models.length][models[0].length];
        } else {
            soapModels = new StChActualDiscountSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static StChActualDiscountSoap[] toSoapModels(
        List<StChActualDiscount> models) {
        List<StChActualDiscountSoap> soapModels = new ArrayList<StChActualDiscountSoap>(models.size());

        for (StChActualDiscount model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new StChActualDiscountSoap[soapModels.size()]);
    }

    public StChActualDiscountPK getPrimaryKey() {
        return new StChActualDiscountPK(_periodSid, _projectionDetailsSid,
            _userId, _sessionId, _rsModelSid);
    }

    public void setPrimaryKey(StChActualDiscountPK pk) {
        setPeriodSid(pk.periodSid);
        setProjectionDetailsSid(pk.projectionDetailsSid);
        setUserId(pk.userId);
        setSessionId(pk.sessionId);
        setRsModelSid(pk.rsModelSid);
    }

    public Date getLastModifiedDate() {
        return _lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        _lastModifiedDate = lastModifiedDate;
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

    public int getUserId() {
        return _userId;
    }

    public void setUserId(int userId) {
        _userId = userId;
    }

    public int getSessionId() {
        return _sessionId;
    }

    public void setSessionId(int sessionId) {
        _sessionId = sessionId;
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
