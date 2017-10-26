package com.stpl.app.model;

import com.stpl.app.service.persistence.StSelectionTablePK;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author
 * @generated
 */
public class StSelectionTableSoap implements Serializable {
    private String _selectionType;
    private int _userId;
    private String _sessionId;
    private int _companyItemSid;
    private boolean _checkRecord;

    public StSelectionTableSoap() {
    }

    public static StSelectionTableSoap toSoapModel(StSelectionTable model) {
        StSelectionTableSoap soapModel = new StSelectionTableSoap();

        soapModel.setSelectionType(model.getSelectionType());
        soapModel.setUserId(model.getUserId());
        soapModel.setSessionId(model.getSessionId());
        soapModel.setCompanyItemSid(model.getCompanyItemSid());
        soapModel.setCheckRecord(model.getCheckRecord());

        return soapModel;
    }

    public static StSelectionTableSoap[] toSoapModels(StSelectionTable[] models) {
        StSelectionTableSoap[] soapModels = new StSelectionTableSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static StSelectionTableSoap[][] toSoapModels(
        StSelectionTable[][] models) {
        StSelectionTableSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new StSelectionTableSoap[models.length][models[0].length];
        } else {
            soapModels = new StSelectionTableSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static StSelectionTableSoap[] toSoapModels(
        List<StSelectionTable> models) {
        List<StSelectionTableSoap> soapModels = new ArrayList<StSelectionTableSoap>(models.size());

        for (StSelectionTable model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new StSelectionTableSoap[soapModels.size()]);
    }

    public StSelectionTablePK getPrimaryKey() {
        return new StSelectionTablePK(_selectionType, _userId, _sessionId,
            _companyItemSid);
    }

    public void setPrimaryKey(StSelectionTablePK pk) {
        setSelectionType(pk.selectionType);
        setUserId(pk.userId);
        setSessionId(pk.sessionId);
        setCompanyItemSid(pk.companyItemSid);
    }

    public String getSelectionType() {
        return _selectionType;
    }

    public void setSelectionType(String selectionType) {
        _selectionType = selectionType;
    }

    public int getUserId() {
        return _userId;
    }

    public void setUserId(int userId) {
        _userId = userId;
    }

    public String getSessionId() {
        return _sessionId;
    }

    public void setSessionId(String sessionId) {
        _sessionId = sessionId;
    }

    public int getCompanyItemSid() {
        return _companyItemSid;
    }

    public void setCompanyItemSid(int companyItemSid) {
        _companyItemSid = companyItemSid;
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
