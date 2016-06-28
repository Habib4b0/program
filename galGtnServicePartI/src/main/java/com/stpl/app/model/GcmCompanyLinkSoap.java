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
public class GcmCompanyLinkSoap implements Serializable {
    private boolean _checkRecord;
    private int _userId;
    private String _companyNo;
    private String _companyId;
    private int _gcmCompanyLinkSid;
    private String _sessionId;
    private String _companyName;
    private String _linkId;
    private int _companyMasterSid;

    public GcmCompanyLinkSoap() {
    }

    public static GcmCompanyLinkSoap toSoapModel(GcmCompanyLink model) {
        GcmCompanyLinkSoap soapModel = new GcmCompanyLinkSoap();

        soapModel.setCheckRecord(model.getCheckRecord());
        soapModel.setUserId(model.getUserId());
        soapModel.setCompanyNo(model.getCompanyNo());
        soapModel.setCompanyId(model.getCompanyId());
        soapModel.setGcmCompanyLinkSid(model.getGcmCompanyLinkSid());
        soapModel.setSessionId(model.getSessionId());
        soapModel.setCompanyName(model.getCompanyName());
        soapModel.setLinkId(model.getLinkId());
        soapModel.setCompanyMasterSid(model.getCompanyMasterSid());

        return soapModel;
    }

    public static GcmCompanyLinkSoap[] toSoapModels(GcmCompanyLink[] models) {
        GcmCompanyLinkSoap[] soapModels = new GcmCompanyLinkSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static GcmCompanyLinkSoap[][] toSoapModels(GcmCompanyLink[][] models) {
        GcmCompanyLinkSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new GcmCompanyLinkSoap[models.length][models[0].length];
        } else {
            soapModels = new GcmCompanyLinkSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static GcmCompanyLinkSoap[] toSoapModels(List<GcmCompanyLink> models) {
        List<GcmCompanyLinkSoap> soapModels = new ArrayList<GcmCompanyLinkSoap>(models.size());

        for (GcmCompanyLink model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new GcmCompanyLinkSoap[soapModels.size()]);
    }

    public int getPrimaryKey() {
        return _gcmCompanyLinkSid;
    }

    public void setPrimaryKey(int pk) {
        setGcmCompanyLinkSid(pk);
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

    public int getUserId() {
        return _userId;
    }

    public void setUserId(int userId) {
        _userId = userId;
    }

    public String getCompanyNo() {
        return _companyNo;
    }

    public void setCompanyNo(String companyNo) {
        _companyNo = companyNo;
    }

    public String getCompanyId() {
        return _companyId;
    }

    public void setCompanyId(String companyId) {
        _companyId = companyId;
    }

    public int getGcmCompanyLinkSid() {
        return _gcmCompanyLinkSid;
    }

    public void setGcmCompanyLinkSid(int gcmCompanyLinkSid) {
        _gcmCompanyLinkSid = gcmCompanyLinkSid;
    }

    public String getSessionId() {
        return _sessionId;
    }

    public void setSessionId(String sessionId) {
        _sessionId = sessionId;
    }

    public String getCompanyName() {
        return _companyName;
    }

    public void setCompanyName(String companyName) {
        _companyName = companyName;
    }

    public String getLinkId() {
        return _linkId;
    }

    public void setLinkId(String linkId) {
        _linkId = linkId;
    }

    public int getCompanyMasterSid() {
        return _companyMasterSid;
    }

    public void setCompanyMasterSid(int companyMasterSid) {
        _companyMasterSid = companyMasterSid;
    }
}
