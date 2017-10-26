package com.stpl.app.model;

import com.stpl.app.service.persistence.ChDiscountProjMasterPK;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author
 * @generated
 */
public class ChDiscountProjMasterSoap implements Serializable {
    private String _selectedPeriods;
    private boolean _checkRecord;
    private String _priceGroupType;
    private int _projectionDetailsSid;
    private String _baselinePeriods;
    private String _netFlag;
    private String _methodology;
    private int _rsModelSid;
    private String _discountType;
    private String _projectedType;

    public ChDiscountProjMasterSoap() {
    }

    public static ChDiscountProjMasterSoap toSoapModel(
        ChDiscountProjMaster model) {
        ChDiscountProjMasterSoap soapModel = new ChDiscountProjMasterSoap();

        soapModel.setSelectedPeriods(model.getSelectedPeriods());
        soapModel.setCheckRecord(model.getCheckRecord());
        soapModel.setPriceGroupType(model.getPriceGroupType());
        soapModel.setProjectionDetailsSid(model.getProjectionDetailsSid());
        soapModel.setBaselinePeriods(model.getBaselinePeriods());
        soapModel.setNetFlag(model.getNetFlag());
        soapModel.setMethodology(model.getMethodology());
        soapModel.setRsModelSid(model.getRsModelSid());
        soapModel.setDiscountType(model.getDiscountType());
        soapModel.setProjectedType(model.getProjectedType());

        return soapModel;
    }

    public static ChDiscountProjMasterSoap[] toSoapModels(
        ChDiscountProjMaster[] models) {
        ChDiscountProjMasterSoap[] soapModels = new ChDiscountProjMasterSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static ChDiscountProjMasterSoap[][] toSoapModels(
        ChDiscountProjMaster[][] models) {
        ChDiscountProjMasterSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new ChDiscountProjMasterSoap[models.length][models[0].length];
        } else {
            soapModels = new ChDiscountProjMasterSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static ChDiscountProjMasterSoap[] toSoapModels(
        List<ChDiscountProjMaster> models) {
        List<ChDiscountProjMasterSoap> soapModels = new ArrayList<ChDiscountProjMasterSoap>(models.size());

        for (ChDiscountProjMaster model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new ChDiscountProjMasterSoap[soapModels.size()]);
    }

    public ChDiscountProjMasterPK getPrimaryKey() {
        return new ChDiscountProjMasterPK(_projectionDetailsSid, _rsModelSid);
    }

    public void setPrimaryKey(ChDiscountProjMasterPK pk) {
        setProjectionDetailsSid(pk.projectionDetailsSid);
        setRsModelSid(pk.rsModelSid);
    }

    public String getSelectedPeriods() {
        return _selectedPeriods;
    }

    public void setSelectedPeriods(String selectedPeriods) {
        _selectedPeriods = selectedPeriods;
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

    public String getBaselinePeriods() {
        return _baselinePeriods;
    }

    public void setBaselinePeriods(String baselinePeriods) {
        _baselinePeriods = baselinePeriods;
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

    public int getRsModelSid() {
        return _rsModelSid;
    }

    public void setRsModelSid(int rsModelSid) {
        _rsModelSid = rsModelSid;
    }

    public String getDiscountType() {
        return _discountType;
    }

    public void setDiscountType(String discountType) {
        _discountType = discountType;
    }

    public String getProjectedType() {
        return _projectedType;
    }

    public void setProjectedType(String projectedType) {
        _projectedType = projectedType;
    }
}
