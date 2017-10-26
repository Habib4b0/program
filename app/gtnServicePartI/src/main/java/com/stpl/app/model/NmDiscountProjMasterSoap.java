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
public class NmDiscountProjMasterSoap implements Serializable {
    private boolean _checkRecord;
    private String _discountId;
    private String _userGroup;
    private String _priceGroupType;
    private int _projectionDetailsSid;
    private String _netFlag;
    private String _methodology;
    private String _discountName;

    public NmDiscountProjMasterSoap() {
    }

    public static NmDiscountProjMasterSoap toSoapModel(
        NmDiscountProjMaster model) {
        NmDiscountProjMasterSoap soapModel = new NmDiscountProjMasterSoap();

        soapModel.setCheckRecord(model.getCheckRecord());
        soapModel.setDiscountId(model.getDiscountId());
        soapModel.setUserGroup(model.getUserGroup());
        soapModel.setPriceGroupType(model.getPriceGroupType());
        soapModel.setProjectionDetailsSid(model.getProjectionDetailsSid());
        soapModel.setNetFlag(model.getNetFlag());
        soapModel.setMethodology(model.getMethodology());
        soapModel.setDiscountName(model.getDiscountName());

        return soapModel;
    }

    public static NmDiscountProjMasterSoap[] toSoapModels(
        NmDiscountProjMaster[] models) {
        NmDiscountProjMasterSoap[] soapModels = new NmDiscountProjMasterSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static NmDiscountProjMasterSoap[][] toSoapModels(
        NmDiscountProjMaster[][] models) {
        NmDiscountProjMasterSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new NmDiscountProjMasterSoap[models.length][models[0].length];
        } else {
            soapModels = new NmDiscountProjMasterSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static NmDiscountProjMasterSoap[] toSoapModels(
        List<NmDiscountProjMaster> models) {
        List<NmDiscountProjMasterSoap> soapModels = new ArrayList<NmDiscountProjMasterSoap>(models.size());

        for (NmDiscountProjMaster model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new NmDiscountProjMasterSoap[soapModels.size()]);
    }

    public int getPrimaryKey() {
        return _projectionDetailsSid;
    }

    public void setPrimaryKey(int pk) {
        setProjectionDetailsSid(pk);
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

    public String getDiscountId() {
        return _discountId;
    }

    public void setDiscountId(String discountId) {
        _discountId = discountId;
    }

    public String getUserGroup() {
        return _userGroup;
    }

    public void setUserGroup(String userGroup) {
        _userGroup = userGroup;
    }

    public String getPriceGroupType() {
        return _priceGroupType;
    }

    public void setPriceGroupType(String priceGroupType) {
        _priceGroupType = priceGroupType;
    }

    public int getProjectionDetailsSid() {
        return _projectionDetailsSid;
    }

    public void setProjectionDetailsSid(int projectionDetailsSid) {
        _projectionDetailsSid = projectionDetailsSid;
    }

    public String getNetFlag() {
        return _netFlag;
    }

    public void setNetFlag(String netFlag) {
        _netFlag = netFlag;
    }

    public String getMethodology() {
        return _methodology;
    }

    public void setMethodology(String methodology) {
        _methodology = methodology;
    }

    public String getDiscountName() {
        return _discountName;
    }

    public void setDiscountName(String discountName) {
        _discountName = discountName;
    }
}
