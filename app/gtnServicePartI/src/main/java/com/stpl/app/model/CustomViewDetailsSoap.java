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
public class CustomViewDetailsSoap implements Serializable {
    private int _hierarchyId;
    private String _hierarchyIndicator;
    private int _customViewMasterSid;
    private int _customViewDetailsSid;
    private int _levelNo;

    public CustomViewDetailsSoap() {
    }

    public static CustomViewDetailsSoap toSoapModel(CustomViewDetails model) {
        CustomViewDetailsSoap soapModel = new CustomViewDetailsSoap();

        soapModel.setHierarchyId(model.getHierarchyId());
        soapModel.setHierarchyIndicator(model.getHierarchyIndicator());
        soapModel.setCustomViewMasterSid(model.getCustomViewMasterSid());
        soapModel.setCustomViewDetailsSid(model.getCustomViewDetailsSid());
        soapModel.setLevelNo(model.getLevelNo());

        return soapModel;
    }

    public static CustomViewDetailsSoap[] toSoapModels(
        CustomViewDetails[] models) {
        CustomViewDetailsSoap[] soapModels = new CustomViewDetailsSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static CustomViewDetailsSoap[][] toSoapModels(
        CustomViewDetails[][] models) {
        CustomViewDetailsSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new CustomViewDetailsSoap[models.length][models[0].length];
        } else {
            soapModels = new CustomViewDetailsSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static CustomViewDetailsSoap[] toSoapModels(
        List<CustomViewDetails> models) {
        List<CustomViewDetailsSoap> soapModels = new ArrayList<CustomViewDetailsSoap>(models.size());

        for (CustomViewDetails model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new CustomViewDetailsSoap[soapModels.size()]);
    }

    public int getPrimaryKey() {
        return _customViewDetailsSid;
    }

    public void setPrimaryKey(int pk) {
        setCustomViewDetailsSid(pk);
    }

    public int getHierarchyId() {
        return _hierarchyId;
    }

    public void setHierarchyId(int hierarchyId) {
        _hierarchyId = hierarchyId;
    }

    public String getHierarchyIndicator() {
        return _hierarchyIndicator;
    }

    public void setHierarchyIndicator(String hierarchyIndicator) {
        _hierarchyIndicator = hierarchyIndicator;
    }

    public int getCustomViewMasterSid() {
        return _customViewMasterSid;
    }

    public void setCustomViewMasterSid(int customViewMasterSid) {
        _customViewMasterSid = customViewMasterSid;
    }

    public int getCustomViewDetailsSid() {
        return _customViewDetailsSid;
    }

    public void setCustomViewDetailsSid(int customViewDetailsSid) {
        _customViewDetailsSid = customViewDetailsSid;
    }

    public int getLevelNo() {
        return _levelNo;
    }

    public void setLevelNo(int levelNo) {
        _levelNo = levelNo;
    }
}
