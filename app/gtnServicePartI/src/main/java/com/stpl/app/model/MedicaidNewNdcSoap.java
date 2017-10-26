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
public class MedicaidNewNdcSoap implements Serializable {
    private double _forecastAmp;
    private double _forecastBestprice;
    private double _baseYearCpi;
    private String _ndc9;
    private double _wacPrice;
    private double _baseYearAmp;

    public MedicaidNewNdcSoap() {
    }

    public static MedicaidNewNdcSoap toSoapModel(MedicaidNewNdc model) {
        MedicaidNewNdcSoap soapModel = new MedicaidNewNdcSoap();

        soapModel.setForecastAmp(model.getForecastAmp());
        soapModel.setForecastBestprice(model.getForecastBestprice());
        soapModel.setBaseYearCpi(model.getBaseYearCpi());
        soapModel.setNdc9(model.getNdc9());
        soapModel.setWacPrice(model.getWacPrice());
        soapModel.setBaseYearAmp(model.getBaseYearAmp());

        return soapModel;
    }

    public static MedicaidNewNdcSoap[] toSoapModels(MedicaidNewNdc[] models) {
        MedicaidNewNdcSoap[] soapModels = new MedicaidNewNdcSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static MedicaidNewNdcSoap[][] toSoapModels(MedicaidNewNdc[][] models) {
        MedicaidNewNdcSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new MedicaidNewNdcSoap[models.length][models[0].length];
        } else {
            soapModels = new MedicaidNewNdcSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static MedicaidNewNdcSoap[] toSoapModels(List<MedicaidNewNdc> models) {
        List<MedicaidNewNdcSoap> soapModels = new ArrayList<MedicaidNewNdcSoap>(models.size());

        for (MedicaidNewNdc model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new MedicaidNewNdcSoap[soapModels.size()]);
    }

    public String getPrimaryKey() {
        return _ndc9;
    }

    public void setPrimaryKey(String pk) {
        setNdc9(pk);
    }

    public double getForecastAmp() {
        return _forecastAmp;
    }

    public void setForecastAmp(double forecastAmp) {
        _forecastAmp = forecastAmp;
    }

    public double getForecastBestprice() {
        return _forecastBestprice;
    }

    public void setForecastBestprice(double forecastBestprice) {
        _forecastBestprice = forecastBestprice;
    }

    public double getBaseYearCpi() {
        return _baseYearCpi;
    }

    public void setBaseYearCpi(double baseYearCpi) {
        _baseYearCpi = baseYearCpi;
    }

    public String getNdc9() {
        return _ndc9;
    }

    public void setNdc9(String ndc9) {
        _ndc9 = ndc9;
    }

    public double getWacPrice() {
        return _wacPrice;
    }

    public void setWacPrice(double wacPrice) {
        _wacPrice = wacPrice;
    }

    public double getBaseYearAmp() {
        return _baseYearAmp;
    }

    public void setBaseYearAmp(double baseYearAmp) {
        _baseYearAmp = baseYearAmp;
    }
}
