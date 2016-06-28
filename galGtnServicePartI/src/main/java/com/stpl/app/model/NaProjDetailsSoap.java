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
public class NaProjDetailsSoap implements Serializable {
    private int _itemMasterSid;
    private int _naProjMasterSid;
    private int _naProjDetailsSid;

    public NaProjDetailsSoap() {
    }

    public static NaProjDetailsSoap toSoapModel(NaProjDetails model) {
        NaProjDetailsSoap soapModel = new NaProjDetailsSoap();

        soapModel.setItemMasterSid(model.getItemMasterSid());
        soapModel.setNaProjMasterSid(model.getNaProjMasterSid());
        soapModel.setNaProjDetailsSid(model.getNaProjDetailsSid());

        return soapModel;
    }

    public static NaProjDetailsSoap[] toSoapModels(NaProjDetails[] models) {
        NaProjDetailsSoap[] soapModels = new NaProjDetailsSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static NaProjDetailsSoap[][] toSoapModels(NaProjDetails[][] models) {
        NaProjDetailsSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new NaProjDetailsSoap[models.length][models[0].length];
        } else {
            soapModels = new NaProjDetailsSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static NaProjDetailsSoap[] toSoapModels(List<NaProjDetails> models) {
        List<NaProjDetailsSoap> soapModels = new ArrayList<NaProjDetailsSoap>(models.size());

        for (NaProjDetails model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new NaProjDetailsSoap[soapModels.size()]);
    }

    public int getPrimaryKey() {
        return _naProjDetailsSid;
    }

    public void setPrimaryKey(int pk) {
        setNaProjDetailsSid(pk);
    }

    public int getItemMasterSid() {
        return _itemMasterSid;
    }

    public void setItemMasterSid(int itemMasterSid) {
        _itemMasterSid = itemMasterSid;
    }

    public int getNaProjMasterSid() {
        return _naProjMasterSid;
    }

    public void setNaProjMasterSid(int naProjMasterSid) {
        _naProjMasterSid = naProjMasterSid;
    }

    public int getNaProjDetailsSid() {
        return _naProjDetailsSid;
    }

    public void setNaProjDetailsSid(int naProjDetailsSid) {
        _naProjDetailsSid = naProjDetailsSid;
    }
}
