package com.stpl.app.model;

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
public class PeriodSoap implements Serializable {
    private int _periodSid;
    private Date _periodDate;
    private int _quarter;
    private int _year;
    private int _semiAnnual;
    private int _month;

    public PeriodSoap() {
    }

    public static PeriodSoap toSoapModel(Period model) {
        PeriodSoap soapModel = new PeriodSoap();

        soapModel.setPeriodSid(model.getPeriodSid());
        soapModel.setPeriodDate(model.getPeriodDate());
        soapModel.setQuarter(model.getQuarter());
        soapModel.setYear(model.getYear());
        soapModel.setSemiAnnual(model.getSemiAnnual());
        soapModel.setMonth(model.getMonth());

        return soapModel;
    }

    public static PeriodSoap[] toSoapModels(Period[] models) {
        PeriodSoap[] soapModels = new PeriodSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static PeriodSoap[][] toSoapModels(Period[][] models) {
        PeriodSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new PeriodSoap[models.length][models[0].length];
        } else {
            soapModels = new PeriodSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static PeriodSoap[] toSoapModels(List<Period> models) {
        List<PeriodSoap> soapModels = new ArrayList<PeriodSoap>(models.size());

        for (Period model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new PeriodSoap[soapModels.size()]);
    }

    public int getPrimaryKey() {
        return _periodSid;
    }

    public void setPrimaryKey(int pk) {
        setPeriodSid(pk);
    }

    public int getPeriodSid() {
        return _periodSid;
    }

    public void setPeriodSid(int periodSid) {
        _periodSid = periodSid;
    }

    public Date getPeriodDate() {
        return _periodDate;
    }

    public void setPeriodDate(Date periodDate) {
        _periodDate = periodDate;
    }

    public int getQuarter() {
        return _quarter;
    }

    public void setQuarter(int quarter) {
        _quarter = quarter;
    }

    public int getYear() {
        return _year;
    }

    public void setYear(int year) {
        _year = year;
    }

    public int getSemiAnnual() {
        return _semiAnnual;
    }

    public void setSemiAnnual(int semiAnnual) {
        _semiAnnual = semiAnnual;
    }

    public int getMonth() {
        return _month;
    }

    public void setMonth(int month) {
        _month = month;
    }
}
