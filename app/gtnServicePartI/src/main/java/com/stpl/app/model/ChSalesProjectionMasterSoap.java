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
public class ChSalesProjectionMasterSoap implements Serializable {
    private boolean _checkRecord;
    private String _calculationPeriods;
    private int _projectionDetailsSid;
    private String _methodology;

    public ChSalesProjectionMasterSoap() {
    }

    public static ChSalesProjectionMasterSoap toSoapModel(
        ChSalesProjectionMaster model) {
        ChSalesProjectionMasterSoap soapModel = new ChSalesProjectionMasterSoap();

        soapModel.setCheckRecord(model.getCheckRecord());
        soapModel.setCalculationPeriods(model.getCalculationPeriods());
        soapModel.setProjectionDetailsSid(model.getProjectionDetailsSid());
        soapModel.setMethodology(model.getMethodology());

        return soapModel;
    }

    public static ChSalesProjectionMasterSoap[] toSoapModels(
        ChSalesProjectionMaster[] models) {
        ChSalesProjectionMasterSoap[] soapModels = new ChSalesProjectionMasterSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static ChSalesProjectionMasterSoap[][] toSoapModels(
        ChSalesProjectionMaster[][] models) {
        ChSalesProjectionMasterSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new ChSalesProjectionMasterSoap[models.length][models[0].length];
        } else {
            soapModels = new ChSalesProjectionMasterSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static ChSalesProjectionMasterSoap[] toSoapModels(
        List<ChSalesProjectionMaster> models) {
        List<ChSalesProjectionMasterSoap> soapModels = new ArrayList<ChSalesProjectionMasterSoap>(models.size());

        for (ChSalesProjectionMaster model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new ChSalesProjectionMasterSoap[soapModels.size()]);
    }

    public int getPrimaryKey() {
        return _projectionDetailsSid;
    }

    public void setPrimaryKey(int pk) {
        setProjectionDetailsSid(pk);
    }

    public boolean getCheckRecord() {
        return _checkRecord;
    }

    public boolean isCheckRecord() {
        return _checkRecord;
    }

    public void setCheckRecord(boolean checkRecord) {
        _checkRecord = checkRecord;
    }

    public String getCalculationPeriods() {
        return _calculationPeriods;
    }

    public void setCalculationPeriods(String calculationPeriods) {
        _calculationPeriods = calculationPeriods;
    }

    public int getProjectionDetailsSid() {
        return _projectionDetailsSid;
    }

    public void setProjectionDetailsSid(int projectionDetailsSid) {
        _projectionDetailsSid = projectionDetailsSid;
    }

    public String getMethodology() {
        return _methodology;
    }

    public void setMethodology(String methodology) {
        _methodology = methodology;
    }
}
