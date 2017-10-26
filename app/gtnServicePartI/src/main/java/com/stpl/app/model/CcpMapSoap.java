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
public class CcpMapSoap implements Serializable {
    private int _ccpDetailsSid;
    private int _relationshipLevelSid;
    private int _ccpMapSid;

    public CcpMapSoap() {
    }

    public static CcpMapSoap toSoapModel(CcpMap model) {
        CcpMapSoap soapModel = new CcpMapSoap();

        soapModel.setCcpDetailsSid(model.getCcpDetailsSid());
        soapModel.setRelationshipLevelSid(model.getRelationshipLevelSid());
        soapModel.setCcpMapSid(model.getCcpMapSid());

        return soapModel;
    }

    public static CcpMapSoap[] toSoapModels(CcpMap[] models) {
        CcpMapSoap[] soapModels = new CcpMapSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static CcpMapSoap[][] toSoapModels(CcpMap[][] models) {
        CcpMapSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new CcpMapSoap[models.length][models[0].length];
        } else {
            soapModels = new CcpMapSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static CcpMapSoap[] toSoapModels(List<CcpMap> models) {
        List<CcpMapSoap> soapModels = new ArrayList<CcpMapSoap>(models.size());

        for (CcpMap model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new CcpMapSoap[soapModels.size()]);
    }

    public int getPrimaryKey() {
        return _ccpMapSid;
    }

    public void setPrimaryKey(int pk) {
        setCcpMapSid(pk);
    }

    public int getCcpDetailsSid() {
        return _ccpDetailsSid;
    }

    public void setCcpDetailsSid(int ccpDetailsSid) {
        _ccpDetailsSid = ccpDetailsSid;
    }

    public int getRelationshipLevelSid() {
        return _relationshipLevelSid;
    }

    public void setRelationshipLevelSid(int relationshipLevelSid) {
        _relationshipLevelSid = relationshipLevelSid;
    }

    public int getCcpMapSid() {
        return _ccpMapSid;
    }

    public void setCcpMapSid(int ccpMapSid) {
        _ccpMapSid = ccpMapSid;
    }
}
