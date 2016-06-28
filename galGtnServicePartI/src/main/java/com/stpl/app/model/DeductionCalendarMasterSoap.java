package com.stpl.app.model;

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
public class DeductionCalendarMasterSoap implements Serializable {
    private int _createdBy;
    private int _deductionCalendarMasterSid;
    private String _deductionCalendarNo;
    private int _modifiedBy;
    private Date _createdDate;
    private int _category;
    private String _additionalNotes;
    private int _userId;
    private String _deductionCalendarName;
    private String _deductionCalendarDesc;
    private Date _modifiedDate;
    private String _inboundStatus;

    public DeductionCalendarMasterSoap() {
    }

    public static DeductionCalendarMasterSoap toSoapModel(
        DeductionCalendarMaster model) {
        DeductionCalendarMasterSoap soapModel = new DeductionCalendarMasterSoap();

        soapModel.setCreatedBy(model.getCreatedBy());
        soapModel.setDeductionCalendarMasterSid(model.getDeductionCalendarMasterSid());
        soapModel.setDeductionCalendarNo(model.getDeductionCalendarNo());
        soapModel.setModifiedBy(model.getModifiedBy());
        soapModel.setCreatedDate(model.getCreatedDate());
        soapModel.setCategory(model.getCategory());
        soapModel.setAdditionalNotes(model.getAdditionalNotes());
        soapModel.setUserId(model.getUserId());
        soapModel.setDeductionCalendarName(model.getDeductionCalendarName());
        soapModel.setDeductionCalendarDesc(model.getDeductionCalendarDesc());
        soapModel.setModifiedDate(model.getModifiedDate());
        soapModel.setInboundStatus(model.getInboundStatus());

        return soapModel;
    }

    public static DeductionCalendarMasterSoap[] toSoapModels(
        DeductionCalendarMaster[] models) {
        DeductionCalendarMasterSoap[] soapModels = new DeductionCalendarMasterSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static DeductionCalendarMasterSoap[][] toSoapModels(
        DeductionCalendarMaster[][] models) {
        DeductionCalendarMasterSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new DeductionCalendarMasterSoap[models.length][models[0].length];
        } else {
            soapModels = new DeductionCalendarMasterSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static DeductionCalendarMasterSoap[] toSoapModels(
        List<DeductionCalendarMaster> models) {
        List<DeductionCalendarMasterSoap> soapModels = new ArrayList<DeductionCalendarMasterSoap>(models.size());

        for (DeductionCalendarMaster model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new DeductionCalendarMasterSoap[soapModels.size()]);
    }

    public int getPrimaryKey() {
        return _deductionCalendarMasterSid;
    }

    public void setPrimaryKey(int pk) {
        setDeductionCalendarMasterSid(pk);
    }

    public int getCreatedBy() {
        return _createdBy;
    }

    public void setCreatedBy(int createdBy) {
        _createdBy = createdBy;
    }

    public int getDeductionCalendarMasterSid() {
        return _deductionCalendarMasterSid;
    }

    public void setDeductionCalendarMasterSid(int deductionCalendarMasterSid) {
        _deductionCalendarMasterSid = deductionCalendarMasterSid;
    }

    public String getDeductionCalendarNo() {
        return _deductionCalendarNo;
    }

    public void setDeductionCalendarNo(String deductionCalendarNo) {
        _deductionCalendarNo = deductionCalendarNo;
    }

    public int getModifiedBy() {
        return _modifiedBy;
    }

    public void setModifiedBy(int modifiedBy) {
        _modifiedBy = modifiedBy;
    }

    public Date getCreatedDate() {
        return _createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        _createdDate = createdDate;
    }

    public int getCategory() {
        return _category;
    }

    public void setCategory(int category) {
        _category = category;
    }

    public String getAdditionalNotes() {
        return _additionalNotes;
    }

    public void setAdditionalNotes(String additionalNotes) {
        _additionalNotes = additionalNotes;
    }

    public int getUserId() {
        return _userId;
    }

    public void setUserId(int userId) {
        _userId = userId;
    }

    public String getDeductionCalendarName() {
        return _deductionCalendarName;
    }

    public void setDeductionCalendarName(String deductionCalendarName) {
        _deductionCalendarName = deductionCalendarName;
    }

    public String getDeductionCalendarDesc() {
        return _deductionCalendarDesc;
    }

    public void setDeductionCalendarDesc(String deductionCalendarDesc) {
        _deductionCalendarDesc = deductionCalendarDesc;
    }

    public Date getModifiedDate() {
        return _modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        _modifiedDate = modifiedDate;
    }

    public String getInboundStatus() {
        return _inboundStatus;
    }

    public void setInboundStatus(String inboundStatus) {
        _inboundStatus = inboundStatus;
    }
}
