package com.stpl.app.model;

import com.stpl.app.service.persistence.StChSalesProjectionMasterPK;

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
public class StChSalesProjectionMasterSoap implements Serializable {
    private Date _lastModifiedDate;
    private boolean _checkRecord;
    private String _calculationPeriods;
    private int _projectionDetailsSid;
    private int _userId;
    private int _sessionId;
    private String _methodology;

    public StChSalesProjectionMasterSoap() {
    }

    public static StChSalesProjectionMasterSoap toSoapModel(
        StChSalesProjectionMaster model) {
        StChSalesProjectionMasterSoap soapModel = new StChSalesProjectionMasterSoap();

        soapModel.setLastModifiedDate(model.getLastModifiedDate());
        soapModel.setCheckRecord(model.getCheckRecord());
        soapModel.setCalculationPeriods(model.getCalculationPeriods());
        soapModel.setProjectionDetailsSid(model.getProjectionDetailsSid());
        soapModel.setUserId(model.getUserId());
        soapModel.setSessionId(model.getSessionId());
        soapModel.setMethodology(model.getMethodology());

        return soapModel;
    }

    public static StChSalesProjectionMasterSoap[] toSoapModels(
        StChSalesProjectionMaster[] models) {
        StChSalesProjectionMasterSoap[] soapModels = new StChSalesProjectionMasterSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static StChSalesProjectionMasterSoap[][] toSoapModels(
        StChSalesProjectionMaster[][] models) {
        StChSalesProjectionMasterSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new StChSalesProjectionMasterSoap[models.length][models[0].length];
        } else {
            soapModels = new StChSalesProjectionMasterSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static StChSalesProjectionMasterSoap[] toSoapModels(
        List<StChSalesProjectionMaster> models) {
        List<StChSalesProjectionMasterSoap> soapModels = new ArrayList<StChSalesProjectionMasterSoap>(models.size());

        for (StChSalesProjectionMaster model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new StChSalesProjectionMasterSoap[soapModels.size()]);
    }

    public StChSalesProjectionMasterPK getPrimaryKey() {
        return new StChSalesProjectionMasterPK(_projectionDetailsSid, _userId,
            _sessionId);
    }

    public void setPrimaryKey(StChSalesProjectionMasterPK pk) {
        setProjectionDetailsSid(pk.projectionDetailsSid);
        setUserId(pk.userId);
        setSessionId(pk.sessionId);
    }

    public Date getLastModifiedDate() {
        return _lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        _lastModifiedDate = lastModifiedDate;
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

    public int getUserId() {
        return _userId;
    }

    public void setUserId(int userId) {
        _userId = userId;
    }

    public int getSessionId() {
        return _sessionId;
    }

    public void setSessionId(int sessionId) {
        _sessionId = sessionId;
    }

    public String getMethodology() {
        return _methodology;
    }

    public void setMethodology(String methodology) {
        _methodology = methodology;
    }
}
