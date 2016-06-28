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
public class NmSalesProjectionMasterSoap implements Serializable {
    private boolean _checkRecord;
    private String _calculationPeriods;
    private String _userGroup;
    private int _projectionDetailsSid;
    private String _methodology;
    private String _calculationBased;

    public NmSalesProjectionMasterSoap() {
    }

    public static NmSalesProjectionMasterSoap toSoapModel(
        NmSalesProjectionMaster model) {
        NmSalesProjectionMasterSoap soapModel = new NmSalesProjectionMasterSoap();

        soapModel.setCheckRecord(model.getCheckRecord());
        soapModel.setCalculationPeriods(model.getCalculationPeriods());
        soapModel.setUserGroup(model.getUserGroup());
        soapModel.setProjectionDetailsSid(model.getProjectionDetailsSid());
        soapModel.setMethodology(model.getMethodology());
        soapModel.setCalculationBased(model.getCalculationBased());

        return soapModel;
    }

    public static NmSalesProjectionMasterSoap[] toSoapModels(
        NmSalesProjectionMaster[] models) {
        NmSalesProjectionMasterSoap[] soapModels = new NmSalesProjectionMasterSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static NmSalesProjectionMasterSoap[][] toSoapModels(
        NmSalesProjectionMaster[][] models) {
        NmSalesProjectionMasterSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new NmSalesProjectionMasterSoap[models.length][models[0].length];
        } else {
            soapModels = new NmSalesProjectionMasterSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static NmSalesProjectionMasterSoap[] toSoapModels(
        List<NmSalesProjectionMaster> models) {
        List<NmSalesProjectionMasterSoap> soapModels = new ArrayList<NmSalesProjectionMasterSoap>(models.size());

        for (NmSalesProjectionMaster model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new NmSalesProjectionMasterSoap[soapModels.size()]);
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

    public String getUserGroup() {
        return _userGroup;
    }

    public void setUserGroup(String userGroup) {
        _userGroup = userGroup;
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

    public String getCalculationBased() {
        return _calculationBased;
    }

    public void setCalculationBased(String calculationBased) {
        _calculationBased = calculationBased;
    }
}
