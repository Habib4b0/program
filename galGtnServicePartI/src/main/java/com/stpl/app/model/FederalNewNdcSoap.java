package com.stpl.app.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author
 * @generated
 */
public class FederalNewNdcSoap implements Serializable {
    private double _fss;
    private int _itemMasterSid;
    private double _wacPrice;
    private double _nonFamp;

    public FederalNewNdcSoap() {
    }

    public static FederalNewNdcSoap toSoapModel(FederalNewNdc model) {
        FederalNewNdcSoap soapModel = new FederalNewNdcSoap();

        soapModel.setFss(model.getFss());
        soapModel.setItemMasterSid(model.getItemMasterSid());
        soapModel.setWacPrice(model.getWacPrice());
        soapModel.setNonFamp(model.getNonFamp());

        return soapModel;
    }

    public static FederalNewNdcSoap[] toSoapModels(FederalNewNdc[] models) {
        FederalNewNdcSoap[] soapModels = new FederalNewNdcSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static FederalNewNdcSoap[][] toSoapModels(FederalNewNdc[][] models) {
        FederalNewNdcSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new FederalNewNdcSoap[models.length][models[0].length];
        } else {
            soapModels = new FederalNewNdcSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static FederalNewNdcSoap[] toSoapModels(List<FederalNewNdc> models) {
        List<FederalNewNdcSoap> soapModels = new ArrayList<FederalNewNdcSoap>(models.size());

        for (FederalNewNdc model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new FederalNewNdcSoap[soapModels.size()]);
    }

    public int getPrimaryKey() {
        return _itemMasterSid;
    }

    public void setPrimaryKey(int pk) {
        setItemMasterSid(pk);
    }

    public double getFss() {
        return _fss;
    }

    public void setFss(double fss) {
        _fss = fss;
    }

    public int getItemMasterSid() {
        return _itemMasterSid;
    }

    public void setItemMasterSid(int itemMasterSid) {
        _itemMasterSid = itemMasterSid;
    }

    public double getWacPrice() {
        return _wacPrice;
    }

    public void setWacPrice(double wacPrice) {
        _wacPrice = wacPrice;
    }

    public double getNonFamp() {
        return _nonFamp;
    }

    public void setNonFamp(double nonFamp) {
        _nonFamp = nonFamp;
    }
}
