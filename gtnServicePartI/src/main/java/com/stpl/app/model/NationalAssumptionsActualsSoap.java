package com.stpl.app.model;

import com.stpl.app.service.persistence.NationalAssumptionsActualsPK;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author
 * @generated
 */
public class NationalAssumptionsActualsSoap implements Serializable {
    private int _periodSid;
    private int _itemMasterSid;
    private String _priceType;
    private double _actualPrice;

    public NationalAssumptionsActualsSoap() {
    }

    public static NationalAssumptionsActualsSoap toSoapModel(
        NationalAssumptionsActuals model) {
        NationalAssumptionsActualsSoap soapModel = new NationalAssumptionsActualsSoap();

        soapModel.setPeriodSid(model.getPeriodSid());
        soapModel.setItemMasterSid(model.getItemMasterSid());
        soapModel.setPriceType(model.getPriceType());
        soapModel.setActualPrice(model.getActualPrice());

        return soapModel;
    }

    public static NationalAssumptionsActualsSoap[] toSoapModels(
        NationalAssumptionsActuals[] models) {
        NationalAssumptionsActualsSoap[] soapModels = new NationalAssumptionsActualsSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static NationalAssumptionsActualsSoap[][] toSoapModels(
        NationalAssumptionsActuals[][] models) {
        NationalAssumptionsActualsSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new NationalAssumptionsActualsSoap[models.length][models[0].length];
        } else {
            soapModels = new NationalAssumptionsActualsSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static NationalAssumptionsActualsSoap[] toSoapModels(
        List<NationalAssumptionsActuals> models) {
        List<NationalAssumptionsActualsSoap> soapModels = new ArrayList<NationalAssumptionsActualsSoap>(models.size());

        for (NationalAssumptionsActuals model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new NationalAssumptionsActualsSoap[soapModels.size()]);
    }

    public NationalAssumptionsActualsPK getPrimaryKey() {
        return new NationalAssumptionsActualsPK(_periodSid, _itemMasterSid,
            _priceType);
    }

    public void setPrimaryKey(NationalAssumptionsActualsPK pk) {
        setPeriodSid(pk.periodSid);
        setItemMasterSid(pk.itemMasterSid);
        setPriceType(pk.priceType);
    }

    public int getPeriodSid() {
        return _periodSid;
    }

    public void setPeriodSid(int periodSid) {
        _periodSid = periodSid;
    }

    public int getItemMasterSid() {
        return _itemMasterSid;
    }

    public void setItemMasterSid(int itemMasterSid) {
        _itemMasterSid = itemMasterSid;
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
}
