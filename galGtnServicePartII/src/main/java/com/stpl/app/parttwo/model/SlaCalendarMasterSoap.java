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
public class SlaCalendarMasterSoap implements Serializable {
    private int _createdBy;
    private int _modifiedBy;
    private int _slaCalendarMasterSid;
    private Date _createdDate;
    private boolean _defaultHolidays;
    private String _calendarName;
    private Date _modifiedDate;
    private String _inboundStatus;

    public SlaCalendarMasterSoap() {
    }

    public static SlaCalendarMasterSoap toSoapModel(SlaCalendarMaster model) {
        SlaCalendarMasterSoap soapModel = new SlaCalendarMasterSoap();

        soapModel.setCreatedBy(model.getCreatedBy());
        soapModel.setModifiedBy(model.getModifiedBy());
        soapModel.setSlaCalendarMasterSid(model.getSlaCalendarMasterSid());
        soapModel.setCreatedDate(model.getCreatedDate());
        soapModel.setDefaultHolidays(model.getDefaultHolidays());
        soapModel.setCalendarName(model.getCalendarName());
        soapModel.setModifiedDate(model.getModifiedDate());
        soapModel.setInboundStatus(model.getInboundStatus());

        return soapModel;
    }

    public static SlaCalendarMasterSoap[] toSoapModels(
        SlaCalendarMaster[] models) {
        SlaCalendarMasterSoap[] soapModels = new SlaCalendarMasterSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static SlaCalendarMasterSoap[][] toSoapModels(
        SlaCalendarMaster[][] models) {
        SlaCalendarMasterSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new SlaCalendarMasterSoap[models.length][models[0].length];
        } else {
            soapModels = new SlaCalendarMasterSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static SlaCalendarMasterSoap[] toSoapModels(
        List<SlaCalendarMaster> models) {
        List<SlaCalendarMasterSoap> soapModels = new ArrayList<SlaCalendarMasterSoap>(models.size());

        for (SlaCalendarMaster model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new SlaCalendarMasterSoap[soapModels.size()]);
    }

    public int getPrimaryKey() {
        return _slaCalendarMasterSid;
    }

    public void setPrimaryKey(int pk) {
        setSlaCalendarMasterSid(pk);
    }

    public int getCreatedBy() {
        return _createdBy;
    }

    public void setCreatedBy(int createdBy) {
        _createdBy = createdBy;
    }

    public int getModifiedBy() {
        return _modifiedBy;
    }

    public void setModifiedBy(int modifiedBy) {
        _modifiedBy = modifiedBy;
    }

    public int getSlaCalendarMasterSid() {
        return _slaCalendarMasterSid;
    }

    public void setSlaCalendarMasterSid(int slaCalendarMasterSid) {
        _slaCalendarMasterSid = slaCalendarMasterSid;
    }

    public Date getCreatedDate() {
        return _createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        _createdDate = createdDate;
    }

    public boolean getDefaultHolidays() {
        return _defaultHolidays;
    }

    public boolean isDefaultHolidays() {
        return _defaultHolidays;
    }

    public void setDefaultHolidays(boolean defaultHolidays) {
        _defaultHolidays = defaultHolidays;
    }

    public String getCalendarName() {
        return _calendarName;
    }

    public void setCalendarName(String calendarName) {
        _calendarName = calendarName;
    }

    public Date getModifiedDate() {
        return _modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        _modifiedDate = modifiedDate;
    }

    public String getInboundStatus() {
        return _inboundStatus;
    }

    public void setInboundStatus(String inboundStatus) {
        _inboundStatus = inboundStatus;
    }
}
