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
public class CffCustHierarchySoap implements Serializable {
    private int _cffCustHierarchySid;
    private int _cffMasterSid;
    private int _relationshipLevelSid;

    public CffCustHierarchySoap() {
    }

    public static CffCustHierarchySoap toSoapModel(CffCustHierarchy model) {
        CffCustHierarchySoap soapModel = new CffCustHierarchySoap();

        soapModel.setCffCustHierarchySid(model.getCffCustHierarchySid());
        soapModel.setCffMasterSid(model.getCffMasterSid());
        soapModel.setRelationshipLevelSid(model.getRelationshipLevelSid());

        return soapModel;
    }

    public static CffCustHierarchySoap[] toSoapModels(CffCustHierarchy[] models) {
        CffCustHierarchySoap[] soapModels = new CffCustHierarchySoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static CffCustHierarchySoap[][] toSoapModels(
        CffCustHierarchy[][] models) {
        CffCustHierarchySoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new CffCustHierarchySoap[models.length][models[0].length];
        } else {
            soapModels = new CffCustHierarchySoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static CffCustHierarchySoap[] toSoapModels(
        List<CffCustHierarchy> models) {
        List<CffCustHierarchySoap> soapModels = new ArrayList<CffCustHierarchySoap>(models.size());

        for (CffCustHierarchy model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new CffCustHierarchySoap[soapModels.size()]);
    }

    public int getPrimaryKey() {
        return _cffCustHierarchySid;
    }

    public void setPrimaryKey(int pk) {
        setCffCustHierarchySid(pk);
    }

    public int getCffCustHierarchySid() {
        return _cffCustHierarchySid;
    }

    public void setCffCustHierarchySid(int cffCustHierarchySid) {
        _cffCustHierarchySid = cffCustHierarchySid;
    }

    public int getCffMasterSid() {
        return _cffMasterSid;
    }

    public void setCffMasterSid(int cffMasterSid) {
        _cffMasterSid = cffMasterSid;
    }

    public int getRelationshipLevelSid() {
        return _relationshipLevelSid;
    }

    public void setRelationshipLevelSid(int relationshipLevelSid) {
        _relationshipLevelSid = relationshipLevelSid;
    }
}
