package com.stpl.app.model;

import com.stpl.app.service.persistence.ChActualSalesPK;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author
 * @generated
 */
public class ChActualSalesSoap implements Serializable {
    private double _contractUnits;
    private double _perOfBusiness;
    private int _periodSid;
    private double _contractSales;
    private int _projectionDetailsSid;
    private double _gtsSales;

    public ChActualSalesSoap() {
    }

    public static ChActualSalesSoap toSoapModel(ChActualSales model) {
        ChActualSalesSoap soapModel = new ChActualSalesSoap();

        soapModel.setContractUnits(model.getContractUnits());
        soapModel.setPerOfBusiness(model.getPerOfBusiness());
        soapModel.setPeriodSid(model.getPeriodSid());
        soapModel.setContractSales(model.getContractSales());
        soapModel.setProjectionDetailsSid(model.getProjectionDetailsSid());
        soapModel.setGtsSales(model.getGtsSales());

        return soapModel;
    }

    public static ChActualSalesSoap[] toSoapModels(ChActualSales[] models) {
        ChActualSalesSoap[] soapModels = new ChActualSalesSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static ChActualSalesSoap[][] toSoapModels(ChActualSales[][] models) {
        ChActualSalesSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new ChActualSalesSoap[models.length][models[0].length];
        } else {
            soapModels = new ChActualSalesSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static ChActualSalesSoap[] toSoapModels(List<ChActualSales> models) {
        List<ChActualSalesSoap> soapModels = new ArrayList<ChActualSalesSoap>(models.size());

        for (ChActualSales model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new ChActualSalesSoap[soapModels.size()]);
    }

    public ChActualSalesPK getPrimaryKey() {
        return new ChActualSalesPK(_periodSid, _projectionDetailsSid);
    }

    public void setPrimaryKey(ChActualSalesPK pk) {
        setPeriodSid(pk.periodSid);
        setProjectionDetailsSid(pk.projectionDetailsSid);
    }

    public double getContractUnits() {
        return _contractUnits;
    }

    public void setContractUnits(double contractUnits) {
        _contractUnits = contractUnits;
    }

    public double getPerOfBusiness() {
        return _perOfBusiness;
    }

    public void setPerOfBusiness(double perOfBusiness) {
        _perOfBusiness = perOfBusiness;
    }

    public int getPeriodSid() {
        return _periodSid;
    }

    public void setPeriodSid(int periodSid) {
        _periodSid = periodSid;
    }

    public double getContractSales() {
        return _contractSales;
    }

    public void setContractSales(double contractSales) {
        _contractSales = contractSales;
    }

    public int getProjectionDetailsSid() {
        return _projectionDetailsSid;
    }

    public void setProjectionDetailsSid(int projectionDetailsSid) {
        _projectionDetailsSid = projectionDetailsSid;
    }

    public double getGtsSales() {
        return _gtsSales;
    }

    public void setGtsSales(double gtsSales) {
        _gtsSales = gtsSales;
    }
}
