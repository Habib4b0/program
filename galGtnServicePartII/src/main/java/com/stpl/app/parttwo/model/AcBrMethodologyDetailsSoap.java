package com.stpl.app.parttwo.model;

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
public class AcBrMethodologyDetailsSoap implements Serializable {
    private double _salesGrowthRate;
    private Date _methodologyStartDate;
    private String _frequency;
    private boolean _calculationFlag;
    private double _provisionGrowthRate;
    private int _salesBasis;
    private int _acBrMethodologyDetailsSid;
    private int _accClosureMasterSid;
    private Date _methodologyEndDate;
    private double _methodologyValue;
    private double _dampingFactor;
    private String _methodologyName;

    public AcBrMethodologyDetailsSoap() {
    }

    public static AcBrMethodologyDetailsSoap toSoapModel(
        AcBrMethodologyDetails model) {
        AcBrMethodologyDetailsSoap soapModel = new AcBrMethodologyDetailsSoap();

        soapModel.setSalesGrowthRate(model.getSalesGrowthRate());
        soapModel.setMethodologyStartDate(model.getMethodologyStartDate());
        soapModel.setFrequency(model.getFrequency());
        soapModel.setCalculationFlag(model.getCalculationFlag());
        soapModel.setProvisionGrowthRate(model.getProvisionGrowthRate());
        soapModel.setSalesBasis(model.getSalesBasis());
        soapModel.setAcBrMethodologyDetailsSid(model.getAcBrMethodologyDetailsSid());
        soapModel.setAccClosureMasterSid(model.getAccClosureMasterSid());
        soapModel.setMethodologyEndDate(model.getMethodologyEndDate());
        soapModel.setMethodologyValue(model.getMethodologyValue());
        soapModel.setDampingFactor(model.getDampingFactor());
        soapModel.setMethodologyName(model.getMethodologyName());

        return soapModel;
    }

    public static AcBrMethodologyDetailsSoap[] toSoapModels(
        AcBrMethodologyDetails[] models) {
        AcBrMethodologyDetailsSoap[] soapModels = new AcBrMethodologyDetailsSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static AcBrMethodologyDetailsSoap[][] toSoapModels(
        AcBrMethodologyDetails[][] models) {
        AcBrMethodologyDetailsSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new AcBrMethodologyDetailsSoap[models.length][models[0].length];
        } else {
            soapModels = new AcBrMethodologyDetailsSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static AcBrMethodologyDetailsSoap[] toSoapModels(
        List<AcBrMethodologyDetails> models) {
        List<AcBrMethodologyDetailsSoap> soapModels = new ArrayList<AcBrMethodologyDetailsSoap>(models.size());

        for (AcBrMethodologyDetails model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new AcBrMethodologyDetailsSoap[soapModels.size()]);
    }

    public int getPrimaryKey() {
        return _acBrMethodologyDetailsSid;
    }

    public void setPrimaryKey(int pk) {
        setAcBrMethodologyDetailsSid(pk);
    }

    public double getSalesGrowthRate() {
        return _salesGrowthRate;
    }

    public void setSalesGrowthRate(double salesGrowthRate) {
        _salesGrowthRate = salesGrowthRate;
    }

    public Date getMethodologyStartDate() {
        return _methodologyStartDate;
    }

    public void setMethodologyStartDate(Date methodologyStartDate) {
        _methodologyStartDate = methodologyStartDate;
    }

    public String getFrequency() {
        return _frequency;
    }

    public void setFrequency(String frequency) {
        _frequency = frequency;
    }

    public boolean getCalculationFlag() {
        return _calculationFlag;
    }

    public boolean isCalculationFlag() {
        return _calculationFlag;
    }

    public void setCalculationFlag(boolean calculationFlag) {
        _calculationFlag = calculationFlag;
    }

    public double getProvisionGrowthRate() {
        return _provisionGrowthRate;
    }

    public void setProvisionGrowthRate(double provisionGrowthRate) {
        _provisionGrowthRate = provisionGrowthRate;
    }

    public int getSalesBasis() {
        return _salesBasis;
    }

    public void setSalesBasis(int salesBasis) {
        _salesBasis = salesBasis;
    }

    public int getAcBrMethodologyDetailsSid() {
        return _acBrMethodologyDetailsSid;
    }

    public void setAcBrMethodologyDetailsSid(int acBrMethodologyDetailsSid) {
        _acBrMethodologyDetailsSid = acBrMethodologyDetailsSid;
    }

    public int getAccClosureMasterSid() {
        return _accClosureMasterSid;
    }

    public void setAccClosureMasterSid(int accClosureMasterSid) {
        _accClosureMasterSid = accClosureMasterSid;
    }

    public Date getMethodologyEndDate() {
        return _methodologyEndDate;
    }

    public void setMethodologyEndDate(Date methodologyEndDate) {
        _methodologyEndDate = methodologyEndDate;
    }

    public double getMethodologyValue() {
        return _methodologyValue;
    }

    public void setMethodologyValue(double methodologyValue) {
        _methodologyValue = methodologyValue;
    }

    public double getDampingFactor() {
        return _dampingFactor;
    }

    public void setDampingFactor(double dampingFactor) {
        _dampingFactor = dampingFactor;
    }

    public String getMethodologyName() {
        return _methodologyName;
    }

    public void setMethodologyName(String methodologyName) {
        _methodologyName = methodologyName;
    }
}
