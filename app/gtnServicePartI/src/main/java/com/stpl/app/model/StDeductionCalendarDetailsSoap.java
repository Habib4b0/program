package com.stpl.app.model;

import com.stpl.app.service.persistence.StDeductionCalendarDetailsPK;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author
 * @generated
 */
public class StDeductionCalendarDetailsSoap implements Serializable {
    private String _adjustmentBasis;
    private int _periodSid;
    private String _adjustmentValue;
    private String _adjustmentAllocationMethodology;
    private int _companyMasterSid;
    private int _discountAmount;
    private String _adjustmentVariable;
    private int _userId;
    private int _itemMasterSid;
    private String _adjustmentType;
    private String _sessionId;
    private boolean _checkRecord;

    public StDeductionCalendarDetailsSoap() {
    }

    public static StDeductionCalendarDetailsSoap toSoapModel(
        StDeductionCalendarDetails model) {
        StDeductionCalendarDetailsSoap soapModel = new StDeductionCalendarDetailsSoap();

        soapModel.setAdjustmentBasis(model.getAdjustmentBasis());
        soapModel.setPeriodSid(model.getPeriodSid());
        soapModel.setAdjustmentValue(model.getAdjustmentValue());
        soapModel.setAdjustmentAllocationMethodology(model.getAdjustmentAllocationMethodology());
        soapModel.setCompanyMasterSid(model.getCompanyMasterSid());
        soapModel.setDiscountAmount(model.getDiscountAmount());
        soapModel.setAdjustmentVariable(model.getAdjustmentVariable());
        soapModel.setUserId(model.getUserId());
        soapModel.setItemMasterSid(model.getItemMasterSid());
        soapModel.setAdjustmentType(model.getAdjustmentType());
        soapModel.setSessionId(model.getSessionId());
        soapModel.setCheckRecord(model.getCheckRecord());

        return soapModel;
    }

    public static StDeductionCalendarDetailsSoap[] toSoapModels(
        StDeductionCalendarDetails[] models) {
        StDeductionCalendarDetailsSoap[] soapModels = new StDeductionCalendarDetailsSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static StDeductionCalendarDetailsSoap[][] toSoapModels(
        StDeductionCalendarDetails[][] models) {
        StDeductionCalendarDetailsSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new StDeductionCalendarDetailsSoap[models.length][models[0].length];
        } else {
            soapModels = new StDeductionCalendarDetailsSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static StDeductionCalendarDetailsSoap[] toSoapModels(
        List<StDeductionCalendarDetails> models) {
        List<StDeductionCalendarDetailsSoap> soapModels = new ArrayList<StDeductionCalendarDetailsSoap>(models.size());

        for (StDeductionCalendarDetails model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new StDeductionCalendarDetailsSoap[soapModels.size()]);
    }

    public StDeductionCalendarDetailsPK getPrimaryKey() {
        return new StDeductionCalendarDetailsPK(_periodSid, _companyMasterSid,
            _userId, _itemMasterSid, _sessionId);
    }

    public void setPrimaryKey(StDeductionCalendarDetailsPK pk) {
        setPeriodSid(pk.periodSid);
        setCompanyMasterSid(pk.companyMasterSid);
        setUserId(pk.userId);
        setItemMasterSid(pk.itemMasterSid);
        setSessionId(pk.sessionId);
    }

    public String getAdjustmentBasis() {
        return _adjustmentBasis;
    }

    public void setAdjustmentBasis(String adjustmentBasis) {
        _adjustmentBasis = adjustmentBasis;
    }

    public int getPeriodSid() {
        return _periodSid;
    }

    public void setPeriodSid(int periodSid) {
        _periodSid = periodSid;
    }

    public String getAdjustmentValue() {
        return _adjustmentValue;
    }

    public void setAdjustmentValue(String adjustmentValue) {
        _adjustmentValue = adjustmentValue;
    }

    public String getAdjustmentAllocationMethodology() {
        return _adjustmentAllocationMethodology;
    }

    public void setAdjustmentAllocationMethodology(
        String adjustmentAllocationMethodology) {
        _adjustmentAllocationMethodology = adjustmentAllocationMethodology;
    }

    public int getCompanyMasterSid() {
        return _companyMasterSid;
    }

    public void setCompanyMasterSid(int companyMasterSid) {
        _companyMasterSid = companyMasterSid;
    }

    public int getDiscountAmount() {
        return _discountAmount;
    }

    public void setDiscountAmount(int discountAmount) {
        _discountAmount = discountAmount;
    }

    public String getAdjustmentVariable() {
        return _adjustmentVariable;
    }

    public void setAdjustmentVariable(String adjustmentVariable) {
        _adjustmentVariable = adjustmentVariable;
    }

    public int getUserId() {
        return _userId;
    }

    public void setUserId(int userId) {
        _userId = userId;
    }

    public int getItemMasterSid() {
        return _itemMasterSid;
    }

    public void setItemMasterSid(int itemMasterSid) {
        _itemMasterSid = itemMasterSid;
    }

    public String getAdjustmentType() {
        return _adjustmentType;
    }

    public void setAdjustmentType(String adjustmentType) {
        _adjustmentType = adjustmentType;
    }

    public String getSessionId() {
        return _sessionId;
    }

    public void setSessionId(String sessionId) {
        _sessionId = sessionId;
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
}
