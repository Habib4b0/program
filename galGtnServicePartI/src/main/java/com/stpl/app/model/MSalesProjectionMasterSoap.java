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
public class MSalesProjectionMasterSoap implements Serializable {
    private String _methodology;
    private String _calculationPeriods;
    private String _calculationBased;
    private int _projectionDetailsSid;
    private boolean _checkRecord;

    public MSalesProjectionMasterSoap() {
    }

    public static MSalesProjectionMasterSoap toSoapModel(
        MSalesProjectionMaster model) {
        MSalesProjectionMasterSoap soapModel = new MSalesProjectionMasterSoap();

        soapModel.setMethodology(model.getMethodology());
        soapModel.setCalculationPeriods(model.getCalculationPeriods());
        soapModel.setCalculationBased(model.getCalculationBased());
        soapModel.setProjectionDetailsSid(model.getProjectionDetailsSid());
        soapModel.setCheckRecord(model.getCheckRecord());

        return soapModel;
    }

    public static MSalesProjectionMasterSoap[] toSoapModels(
        MSalesProjectionMaster[] models) {
        MSalesProjectionMasterSoap[] soapModels = new MSalesProjectionMasterSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static MSalesProjectionMasterSoap[][] toSoapModels(
        MSalesProjectionMaster[][] models) {
        MSalesProjectionMasterSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new MSalesProjectionMasterSoap[models.length][models[0].length];
        } else {
            soapModels = new MSalesProjectionMasterSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static MSalesProjectionMasterSoap[] toSoapModels(
        List<MSalesProjectionMaster> models) {
        List<MSalesProjectionMasterSoap> soapModels = new ArrayList<MSalesProjectionMasterSoap>(models.size());

        for (MSalesProjectionMaster model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new MSalesProjectionMasterSoap[soapModels.size()]);
    }

    public int getPrimaryKey() {
        return _projectionDetailsSid;
    }

    public void setPrimaryKey(int pk) {
        setProjectionDetailsSid(pk);
    }

    public String getMethodology() {
        return _methodology;
    }

    public void setMethodology(String methodology) {
        _methodology = methodology;
    }

    public String getCalculationPeriods() {
        return _calculationPeriods;
    }

    public void setCalculationPeriods(String calculationPeriods) {
        _calculationPeriods = calculationPeriods;
    }

    public String getCalculationBased() {
        return _calculationBased;
    }

    public void setCalculationBased(String calculationBased) {
        _calculationBased = calculationBased;
    }

    public int getProjectionDetailsSid() {
        return _projectionDetailsSid;
    }

    public void setProjectionDetailsSid(int projectionDetailsSid) {
        _projectionDetailsSid = projectionDetailsSid;
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
}
