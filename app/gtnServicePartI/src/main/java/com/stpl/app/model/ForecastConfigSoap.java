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
public class ForecastConfigSoap implements Serializable {
    private boolean _processType;
    private Date _toDate;
    private int _versionNo;
    private int _forecastConfigSid;
    private Date _modifiedDate;
    private Date _fromDate;
    private int _projValue;
    private int _createdBy;
    private Date _createdDate;
    private int _projFreq;
    private int _histValue;
    private int _businessProcessType;
    private int _modifiedBy;
    private int _histFreq;
    private Date _activeStartDate;
    private Date _activeEndDate;
    private boolean _processMode;
    private Date _historicalDataIntervalFrom;
    private Date _historicalTimePeriodFrom;
    private int _projHistFreq;
    private Date _futureTimePeriodFrom;
    private Date _historicalDataIntervalTo;
    private int _projHistValue;

    public ForecastConfigSoap() {
    }

    public static ForecastConfigSoap toSoapModel(ForecastConfig model) {
        ForecastConfigSoap soapModel = new ForecastConfigSoap();

        soapModel.setProcessType(model.getProcessType());
        soapModel.setToDate(model.getToDate());
        soapModel.setVersionNo(model.getVersionNo());
        soapModel.setForecastConfigSid(model.getForecastConfigSid());
        soapModel.setModifiedDate(model.getModifiedDate());
        soapModel.setFromDate(model.getFromDate());
        soapModel.setProjValue(model.getProjValue());
        soapModel.setCreatedBy(model.getCreatedBy());
        soapModel.setCreatedDate(model.getCreatedDate());
        soapModel.setProjFreq(model.getProjFreq());
        soapModel.setHistValue(model.getHistValue());
        soapModel.setBusinessProcessType(model.getBusinessProcessType());
        soapModel.setModifiedBy(model.getModifiedBy());
        soapModel.setHistFreq(model.getHistFreq());
        soapModel.setActiveStartDate(model.getActiveStartDate());
        soapModel.setActiveEndDate(model.getActiveEndDate());
        soapModel.setProcessMode(model.getProcessMode());
        soapModel.setHistoricalDataIntervalFrom(model.getHistoricalDataIntervalFrom());
        soapModel.setHistoricalTimePeriodFrom(model.getHistoricalTimePeriodFrom());
        soapModel.setProjHistFreq(model.getProjHistFreq());
        soapModel.setFutureTimePeriodFrom(model.getFutureTimePeriodFrom());
        soapModel.setHistoricalDataIntervalTo(model.getHistoricalDataIntervalTo());
        soapModel.setProjHistValue(model.getProjHistValue());

        return soapModel;
    }

    public static ForecastConfigSoap[] toSoapModels(ForecastConfig[] models) {
        ForecastConfigSoap[] soapModels = new ForecastConfigSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static ForecastConfigSoap[][] toSoapModels(ForecastConfig[][] models) {
        ForecastConfigSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new ForecastConfigSoap[models.length][models[0].length];
        } else {
            soapModels = new ForecastConfigSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static ForecastConfigSoap[] toSoapModels(List<ForecastConfig> models) {
        List<ForecastConfigSoap> soapModels = new ArrayList<ForecastConfigSoap>(models.size());

        for (ForecastConfig model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new ForecastConfigSoap[soapModels.size()]);
    }

    public int getPrimaryKey() {
        return _forecastConfigSid;
    }

    public void setPrimaryKey(int pk) {
        setForecastConfigSid(pk);
    }

    public boolean getProcessType() {
        return _processType;
    }

    public boolean isProcessType() {
        return _processType;
    }

    public void setProcessType(boolean processType) {
        _processType = processType;
    }

    public Date getToDate() {
        return _toDate;
    }

    public void setToDate(Date toDate) {
        _toDate = toDate;
    }

    public int getVersionNo() {
        return _versionNo;
    }

    public void setVersionNo(int versionNo) {
        _versionNo = versionNo;
    }

    public int getForecastConfigSid() {
        return _forecastConfigSid;
    }

    public void setForecastConfigSid(int forecastConfigSid) {
        _forecastConfigSid = forecastConfigSid;
    }

    public Date getModifiedDate() {
        return _modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        _modifiedDate = modifiedDate;
    }

    public Date getFromDate() {
        return _fromDate;
    }

    public void setFromDate(Date fromDate) {
        _fromDate = fromDate;
    }

    public int getProjValue() {
        return _projValue;
    }

    public void setProjValue(int projValue) {
        _projValue = projValue;
    }

    public int getCreatedBy() {
        return _createdBy;
    }

    public void setCreatedBy(int createdBy) {
        _createdBy = createdBy;
    }

    public Date getCreatedDate() {
        return _createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        _createdDate = createdDate;
    }

    public int getProjFreq() {
        return _projFreq;
    }

    public void setProjFreq(int projFreq) {
        _projFreq = projFreq;
    }

    public int getHistValue() {
        return _histValue;
    }

    public void setHistValue(int histValue) {
        _histValue = histValue;
    }

    public int getBusinessProcessType() {
        return _businessProcessType;
    }

    public void setBusinessProcessType(int businessProcessType) {
        _businessProcessType = businessProcessType;
    }

    public int getModifiedBy() {
        return _modifiedBy;
    }

    public void setModifiedBy(int modifiedBy) {
        _modifiedBy = modifiedBy;
    }

    public int getHistFreq() {
        return _histFreq;
    }

    public void setHistFreq(int histFreq) {
        _histFreq = histFreq;
    }

    public Date getActiveStartDate() {
        return _activeStartDate;
    }

    public void setActiveStartDate(Date activeStartDate) {
        _activeStartDate = activeStartDate;
    }

    public Date getActiveEndDate() {
        return _activeEndDate;
    }

    public void setActiveEndDate(Date activeEndDate) {
        _activeEndDate = activeEndDate;
    }

    public boolean getProcessMode() {
        return _processMode;
    }

    public boolean isProcessMode() {
        return _processMode;
    }

    public void setProcessMode(boolean processMode) {
        _processMode = processMode;
    }

    public Date getHistoricalDataIntervalFrom() {
        return _historicalDataIntervalFrom;
    }

    public void setHistoricalDataIntervalFrom(Date historicalDataIntervalFrom) {
        _historicalDataIntervalFrom = historicalDataIntervalFrom;
    }

    public Date getHistoricalTimePeriodFrom() {
        return _historicalTimePeriodFrom;
    }

    public void setHistoricalTimePeriodFrom(Date historicalTimePeriodFrom) {
        _historicalTimePeriodFrom = historicalTimePeriodFrom;
    }

    public int getProjHistFreq() {
        return _projHistFreq;
    }

    public void setProjHistFreq(int projHistFreq) {
        _projHistFreq = projHistFreq;
    }

    public Date getFutureTimePeriodFrom() {
        return _futureTimePeriodFrom;
    }

    public void setFutureTimePeriodFrom(Date futureTimePeriodFrom) {
        _futureTimePeriodFrom = futureTimePeriodFrom;
    }

    public Date getHistoricalDataIntervalTo() {
        return _historicalDataIntervalTo;
    }

    public void setHistoricalDataIntervalTo(Date historicalDataIntervalTo) {
        _historicalDataIntervalTo = historicalDataIntervalTo;
    }

    public int getProjHistValue() {
        return _projHistValue;
    }

    public void setProjHistValue(int projHistValue) {
        _projHistValue = projHistValue;
    }
}
