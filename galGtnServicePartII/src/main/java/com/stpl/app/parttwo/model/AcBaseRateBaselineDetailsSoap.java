package com.stpl.app.parttwo.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author
 * @generated
 */
public class AcBaseRateBaselineDetailsSoap implements Serializable {
    private double _periodValue;
    private int _periodSid;
    private boolean _paymentsPeriod;
    private int _acBrMethodologyDetailsSid;
    private boolean _salesPeriod;

    public AcBaseRateBaselineDetailsSoap() {
    }

    public static AcBaseRateBaselineDetailsSoap toSoapModel(
        AcBaseRateBaselineDetails model) {
        AcBaseRateBaselineDetailsSoap soapModel = new AcBaseRateBaselineDetailsSoap();

        soapModel.setPeriodValue(model.getPeriodValue());
        soapModel.setPeriodSid(model.getPeriodSid());
        soapModel.setPaymentsPeriod(model.getPaymentsPeriod());
        soapModel.setAcBrMethodologyDetailsSid(model.getAcBrMethodologyDetailsSid());
        soapModel.setSalesPeriod(model.getSalesPeriod());

        return soapModel;
    }

    public static AcBaseRateBaselineDetailsSoap[] toSoapModels(
        AcBaseRateBaselineDetails[] models) {
        AcBaseRateBaselineDetailsSoap[] soapModels = new AcBaseRateBaselineDetailsSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static AcBaseRateBaselineDetailsSoap[][] toSoapModels(
        AcBaseRateBaselineDetails[][] models) {
        AcBaseRateBaselineDetailsSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new AcBaseRateBaselineDetailsSoap[models.length][models[0].length];
        } else {
            soapModels = new AcBaseRateBaselineDetailsSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static AcBaseRateBaselineDetailsSoap[] toSoapModels(
        List<AcBaseRateBaselineDetails> models) {
        List<AcBaseRateBaselineDetailsSoap> soapModels = new ArrayList<AcBaseRateBaselineDetailsSoap>(models.size());

        for (AcBaseRateBaselineDetails model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new AcBaseRateBaselineDetailsSoap[soapModels.size()]);
    }

    public int getPrimaryKey() {
        return _acBrMethodologyDetailsSid;
    }

    public void setPrimaryKey(int pk) {
        setAcBrMethodologyDetailsSid(pk);
    }

    public double getPeriodValue() {
        return _periodValue;
    }

    public void setPeriodValue(double periodValue) {
        _periodValue = periodValue;
    }

    public int getPeriodSid() {
        return _periodSid;
    }

    public void setPeriodSid(int periodSid) {
        _periodSid = periodSid;
    }

    public boolean getPaymentsPeriod() {
        return _paymentsPeriod;
    }

    public boolean isPaymentsPeriod() {
        return _paymentsPeriod;
    }

    public void setPaymentsPeriod(boolean paymentsPeriod) {
        _paymentsPeriod = paymentsPeriod;
    }

    public int getAcBrMethodologyDetailsSid() {
        return _acBrMethodologyDetailsSid;
    }

    public void setAcBrMethodologyDetailsSid(int acBrMethodologyDetailsSid) {
        _acBrMethodologyDetailsSid = acBrMethodologyDetailsSid;
    }

    public boolean getSalesPeriod() {
        return _salesPeriod;
    }

    public boolean isSalesPeriod() {
        return _salesPeriod;
    }

    public void setSalesPeriod(boolean salesPeriod) {
        _salesPeriod = salesPeriod;
    }
}
