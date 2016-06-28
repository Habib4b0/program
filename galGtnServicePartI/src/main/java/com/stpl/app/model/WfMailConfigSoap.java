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
public class WfMailConfigSoap implements Serializable {
    private String _smtpFlag;
    private int _createdBy;
    private String _emailAddress;
    private String _password;
    private int _modifiedBy;
    private int _wfMailConfigSid;
    private String _hostName;
    private Date _createdDate;
    private String _portNumber;
    private Date _modifiedDate;
    private String _inboundStatus;

    public WfMailConfigSoap() {
    }

    public static WfMailConfigSoap toSoapModel(WfMailConfig model) {
        WfMailConfigSoap soapModel = new WfMailConfigSoap();

        soapModel.setSmtpFlag(model.getSmtpFlag());
        soapModel.setCreatedBy(model.getCreatedBy());
        soapModel.setEmailAddress(model.getEmailAddress());
        soapModel.setPassword(model.getPassword());
        soapModel.setModifiedBy(model.getModifiedBy());
        soapModel.setWfMailConfigSid(model.getWfMailConfigSid());
        soapModel.setHostName(model.getHostName());
        soapModel.setCreatedDate(model.getCreatedDate());
        soapModel.setPortNumber(model.getPortNumber());
        soapModel.setModifiedDate(model.getModifiedDate());
        soapModel.setInboundStatus(model.getInboundStatus());

        return soapModel;
    }

    public static WfMailConfigSoap[] toSoapModels(WfMailConfig[] models) {
        WfMailConfigSoap[] soapModels = new WfMailConfigSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static WfMailConfigSoap[][] toSoapModels(WfMailConfig[][] models) {
        WfMailConfigSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new WfMailConfigSoap[models.length][models[0].length];
        } else {
            soapModels = new WfMailConfigSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static WfMailConfigSoap[] toSoapModels(List<WfMailConfig> models) {
        List<WfMailConfigSoap> soapModels = new ArrayList<WfMailConfigSoap>(models.size());

        for (WfMailConfig model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new WfMailConfigSoap[soapModels.size()]);
    }

    public int getPrimaryKey() {
        return _wfMailConfigSid;
    }

    public void setPrimaryKey(int pk) {
        setWfMailConfigSid(pk);
    }

    public String getSmtpFlag() {
        return _smtpFlag;
    }

    public void setSmtpFlag(String smtpFlag) {
        _smtpFlag = smtpFlag;
    }

    public int getCreatedBy() {
        return _createdBy;
    }

    public void setCreatedBy(int createdBy) {
        _createdBy = createdBy;
    }

    public String getEmailAddress() {
        return _emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        _emailAddress = emailAddress;
    }

    public String getPassword() {
        return _password;
    }

    public void setPassword(String password) {
        _password = password;
    }

    public int getModifiedBy() {
        return _modifiedBy;
    }

    public void setModifiedBy(int modifiedBy) {
        _modifiedBy = modifiedBy;
    }

    public int getWfMailConfigSid() {
        return _wfMailConfigSid;
    }

    public void setWfMailConfigSid(int wfMailConfigSid) {
        _wfMailConfigSid = wfMailConfigSid;
    }

    public String getHostName() {
        return _hostName;
    }

    public void setHostName(String hostName) {
        _hostName = hostName;
    }

    public Date getCreatedDate() {
        return _createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        _createdDate = createdDate;
    }

    public String getPortNumber() {
        return _portNumber;
    }

    public void setPortNumber(String portNumber) {
        _portNumber = portNumber;
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
