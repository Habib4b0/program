package com.stpl.app.model;

import com.stpl.app.service.persistence.StFederalNewNdcPK;

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
public class StFederalNewNdcSoap implements Serializable {
    private double _fss;
    private int _userId;
    private Date _lastModifiedDate;
    private int _itemMasterSid;
    private double _wacPrice;
    private int _sessionId;
    private double _nonFamp;

    public StFederalNewNdcSoap() {
    }

    public static StFederalNewNdcSoap toSoapModel(StFederalNewNdc model) {
        StFederalNewNdcSoap soapModel = new StFederalNewNdcSoap();

        soapModel.setFss(model.getFss());
        soapModel.setUserId(model.getUserId());
        soapModel.setLastModifiedDate(model.getLastModifiedDate());
        soapModel.setItemMasterSid(model.getItemMasterSid());
        soapModel.setWacPrice(model.getWacPrice());
        soapModel.setSessionId(model.getSessionId());
        soapModel.setNonFamp(model.getNonFamp());

        return soapModel;
    }

    public static StFederalNewNdcSoap[] toSoapModels(StFederalNewNdc[] models) {
        StFederalNewNdcSoap[] soapModels = new StFederalNewNdcSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static StFederalNewNdcSoap[][] toSoapModels(
        StFederalNewNdc[][] models) {
        StFederalNewNdcSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new StFederalNewNdcSoap[models.length][models[0].length];
        } else {
            soapModels = new StFederalNewNdcSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static StFederalNewNdcSoap[] toSoapModels(
        List<StFederalNewNdc> models) {
        List<StFederalNewNdcSoap> soapModels = new ArrayList<StFederalNewNdcSoap>(models.size());

        for (StFederalNewNdc model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new StFederalNewNdcSoap[soapModels.size()]);
    }

    public StFederalNewNdcPK getPrimaryKey() {
        return new StFederalNewNdcPK(_userId, _itemMasterSid, _sessionId);
    }

    public void setPrimaryKey(StFederalNewNdcPK pk) {
        setUserId(pk.userId);
        setItemMasterSid(pk.itemMasterSid);
        setSessionId(pk.sessionId);
    }

    public double getFss() {
        return _fss;
    }

    public void setFss(double fss) {
        _fss = fss;
    }

    public int getUserId() {
        return _userId;
    }

    public void setUserId(int userId) {
        _userId = userId;
    }

    public Date getLastModifiedDate() {
        return _lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        _lastModifiedDate = lastModifiedDate;
    }

    public int getItemMasterSid() {
        return _itemMasterSid;
    }

    public void setItemMasterSid(int itemMasterSid) {
        _itemMasterSid = itemMasterSid;
    }

    public double getWacPrice() {
        return _wacPrice;
    }

    public void setWacPrice(double wacPrice) {
        _wacPrice = wacPrice;
    }

    public int getSessionId() {
        return _sessionId;
    }

    public void setSessionId(int sessionId) {
        _sessionId = sessionId;
    }

    public double getNonFamp() {
        return _nonFamp;
    }

    public void setNonFamp(double nonFamp) {
        _nonFamp = nonFamp;
    }
}
