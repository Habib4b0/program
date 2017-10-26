package com.stpl.app.model;

import com.stpl.app.service.persistence.StChDiscountProjMasterPK;

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
public class StChDiscountProjMasterSoap implements Serializable {
    private boolean _checkRecord;
    private String _selectedPeriods;
    private Date _lastModifiedDate;
    private int _projectionDetailsSid;
    private String _priceGroupType;
    private int _userId;
    private String _netFlag;
    private String _projectedType;
    private String _baselinePeriods;
    private int _sessionId;
    private String _methodology;
    private int _rsModelSid;
    private String _discountType;

    public StChDiscountProjMasterSoap() {
    }

    public static StChDiscountProjMasterSoap toSoapModel(
        StChDiscountProjMaster model) {
        StChDiscountProjMasterSoap soapModel = new StChDiscountProjMasterSoap();

        soapModel.setCheckRecord(model.getCheckRecord());
        soapModel.setSelectedPeriods(model.getSelectedPeriods());
        soapModel.setLastModifiedDate(model.getLastModifiedDate());
        soapModel.setProjectionDetailsSid(model.getProjectionDetailsSid());
        soapModel.setPriceGroupType(model.getPriceGroupType());
        soapModel.setUserId(model.getUserId());
        soapModel.setNetFlag(model.getNetFlag());
        soapModel.setProjectedType(model.getProjectedType());
        soapModel.setBaselinePeriods(model.getBaselinePeriods());
        soapModel.setSessionId(model.getSessionId());
        soapModel.setMethodology(model.getMethodology());
        soapModel.setRsModelSid(model.getRsModelSid());
        soapModel.setDiscountType(model.getDiscountType());

        return soapModel;
    }

    public static StChDiscountProjMasterSoap[] toSoapModels(
        StChDiscountProjMaster[] models) {
        StChDiscountProjMasterSoap[] soapModels = new StChDiscountProjMasterSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static StChDiscountProjMasterSoap[][] toSoapModels(
        StChDiscountProjMaster[][] models) {
        StChDiscountProjMasterSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new StChDiscountProjMasterSoap[models.length][models[0].length];
        } else {
            soapModels = new StChDiscountProjMasterSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static StChDiscountProjMasterSoap[] toSoapModels(
        List<StChDiscountProjMaster> models) {
        List<StChDiscountProjMasterSoap> soapModels = new ArrayList<StChDiscountProjMasterSoap>(models.size());

        for (StChDiscountProjMaster model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new StChDiscountProjMasterSoap[soapModels.size()]);
    }

    public StChDiscountProjMasterPK getPrimaryKey() {
        return new StChDiscountProjMasterPK(_projectionDetailsSid, _userId,
            _sessionId, _rsModelSid);
    }

    public void setPrimaryKey(StChDiscountProjMasterPK pk) {
        setProjectionDetailsSid(pk.projectionDetailsSid);
        setUserId(pk.userId);
        setSessionId(pk.sessionId);
        setRsModelSid(pk.rsModelSid);
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

    public String getSelectedPeriods() {
        return _selectedPeriods;
    }

    public void setSelectedPeriods(String selectedPeriods) {
        _selectedPeriods = selectedPeriods;
    }

    public Date getLastModifiedDate() {
        return _lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        _lastModifiedDate = lastModifiedDate;
    }

    public int getProjectionDetailsSid() {
        return _projectionDetailsSid;
    }

    public void setProjectionDetailsSid(int projectionDetailsSid) {
        _projectionDetailsSid = projectionDetailsSid;
    }

    public String getPriceGroupType() {
        return _priceGroupType;
    }

    public void setPriceGroupType(String priceGroupType) {
        _priceGroupType = priceGroupType;
    }

    public int getUserId() {
        return _userId;
    }

    public void setUserId(int userId) {
        _userId = userId;
    }

    public String getNetFlag() {
        return _netFlag;
    }

    public void setNetFlag(String netFlag) {
        _netFlag = netFlag;
    }

    public String getProjectedType() {
        return _projectedType;
    }

    public void setProjectedType(String projectedType) {
        _projectedType = projectedType;
    }

    public String getBaselinePeriods() {
        return _baselinePeriods;
    }

    public void setBaselinePeriods(String baselinePeriods) {
        _baselinePeriods = baselinePeriods;
    }

    public int getSessionId() {
        return _sessionId;
    }

    public void setSessionId(int sessionId) {
        _sessionId = sessionId;
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
}
