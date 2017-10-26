package com.stpl.app.parttwo.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author
 * @generated
 */
public class CffDetailsSoap implements Serializable {
    private int _ccpDetailsSid;
    private int _projectionMasterSid;
    private int _cffMasterSid;
    private String _inboundStatus;
    private int _cffDetailsSid;

    public CffDetailsSoap() {
    }

    public static CffDetailsSoap toSoapModel(CffDetails model) {
        CffDetailsSoap soapModel = new CffDetailsSoap();

        soapModel.setCcpDetailsSid(model.getCcpDetailsSid());
        soapModel.setProjectionMasterSid(model.getProjectionMasterSid());
        soapModel.setCffMasterSid(model.getCffMasterSid());
        soapModel.setInboundStatus(model.getInboundStatus());
        soapModel.setCffDetailsSid(model.getCffDetailsSid());

        return soapModel;
    }

    public static CffDetailsSoap[] toSoapModels(CffDetails[] models) {
        CffDetailsSoap[] soapModels = new CffDetailsSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static CffDetailsSoap[][] toSoapModels(CffDetails[][] models) {
        CffDetailsSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new CffDetailsSoap[models.length][models[0].length];
        } else {
            soapModels = new CffDetailsSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static CffDetailsSoap[] toSoapModels(List<CffDetails> models) {
        List<CffDetailsSoap> soapModels = new ArrayList<CffDetailsSoap>(models.size());

        for (CffDetails model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new CffDetailsSoap[soapModels.size()]);
    }

    public int getPrimaryKey() {
        return _cffDetailsSid;
    }

    public void setPrimaryKey(int pk) {
        setCffDetailsSid(pk);
    }

    public int getCcpDetailsSid() {
        return _ccpDetailsSid;
    }

    public void setCcpDetailsSid(int ccpDetailsSid) {
        _ccpDetailsSid = ccpDetailsSid;
    }

    public int getProjectionMasterSid() {
        return _projectionMasterSid;
    }

    public void setProjectionMasterSid(int projectionMasterSid) {
        _projectionMasterSid = projectionMasterSid;
    }

    public int getCffMasterSid() {
        return _cffMasterSid;
    }

    public void setCffMasterSid(int cffMasterSid) {
        _cffMasterSid = cffMasterSid;
    }

    public String getInboundStatus() {
        return _inboundStatus;
    }

    public void setInboundStatus(String inboundStatus) {
        _inboundStatus = inboundStatus;
    }

    public int getCffDetailsSid() {
        return _cffDetailsSid;
    }

    public void setCffDetailsSid(int cffDetailsSid) {
        _cffDetailsSid = cffDetailsSid;
    }
}
