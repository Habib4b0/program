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
public class RebateTierFormulaSoap implements Serializable {
    private String _rebateTierFormulaNo;
    private String _rebateTierFormulaName;
    private int _rebatePlanMasterSid;
    private Date _modifiedDate;
    private boolean _recordLockStatus;
    private String _source;
    private int _createdBy;
    private Date _createdDate;
    private String _batchId;
    private String _rebateTierFormulaId;
    private String _inboundStatus;
    private int _modifiedBy;
    private int _rebateTierFormulaSid;

    public RebateTierFormulaSoap() {
    }

    public static RebateTierFormulaSoap toSoapModel(RebateTierFormula model) {
        RebateTierFormulaSoap soapModel = new RebateTierFormulaSoap();

        soapModel.setRebateTierFormulaNo(model.getRebateTierFormulaNo());
        soapModel.setRebateTierFormulaName(model.getRebateTierFormulaName());
        soapModel.setRebatePlanMasterSid(model.getRebatePlanMasterSid());
        soapModel.setModifiedDate(model.getModifiedDate());
        soapModel.setRecordLockStatus(model.getRecordLockStatus());
        soapModel.setSource(model.getSource());
        soapModel.setCreatedBy(model.getCreatedBy());
        soapModel.setCreatedDate(model.getCreatedDate());
        soapModel.setBatchId(model.getBatchId());
        soapModel.setRebateTierFormulaId(model.getRebateTierFormulaId());
        soapModel.setInboundStatus(model.getInboundStatus());
        soapModel.setModifiedBy(model.getModifiedBy());
        soapModel.setRebateTierFormulaSid(model.getRebateTierFormulaSid());

        return soapModel;
    }

    public static RebateTierFormulaSoap[] toSoapModels(
        RebateTierFormula[] models) {
        RebateTierFormulaSoap[] soapModels = new RebateTierFormulaSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static RebateTierFormulaSoap[][] toSoapModels(
        RebateTierFormula[][] models) {
        RebateTierFormulaSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new RebateTierFormulaSoap[models.length][models[0].length];
        } else {
            soapModels = new RebateTierFormulaSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static RebateTierFormulaSoap[] toSoapModels(
        List<RebateTierFormula> models) {
        List<RebateTierFormulaSoap> soapModels = new ArrayList<RebateTierFormulaSoap>(models.size());

        for (RebateTierFormula model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new RebateTierFormulaSoap[soapModels.size()]);
    }

    public int getPrimaryKey() {
        return _rebateTierFormulaSid;
    }

    public void setPrimaryKey(int pk) {
        setRebateTierFormulaSid(pk);
    }

    public String getRebateTierFormulaNo() {
        return _rebateTierFormulaNo;
    }

    public void setRebateTierFormulaNo(String rebateTierFormulaNo) {
        _rebateTierFormulaNo = rebateTierFormulaNo;
    }

    public String getRebateTierFormulaName() {
        return _rebateTierFormulaName;
    }

    public void setRebateTierFormulaName(String rebateTierFormulaName) {
        _rebateTierFormulaName = rebateTierFormulaName;
    }

    public int getRebatePlanMasterSid() {
        return _rebatePlanMasterSid;
    }

    public void setRebatePlanMasterSid(int rebatePlanMasterSid) {
        _rebatePlanMasterSid = rebatePlanMasterSid;
    }

    public Date getModifiedDate() {
        return _modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        _modifiedDate = modifiedDate;
    }

    public boolean getRecordLockStatus() {
        return _recordLockStatus;
    }

    public boolean isRecordLockStatus() {
        return _recordLockStatus;
    }

    public void setRecordLockStatus(boolean recordLockStatus) {
        _recordLockStatus = recordLockStatus;
    }

    public String getSource() {
        return _source;
    }

    public void setSource(String source) {
        _source = source;
    }

    public int getCreatedBy() {
        return _createdBy;
    }

    public void setCreatedBy(int createdBy) {
        _createdBy = createdBy;
    }

    public Date getCreatedDate() {
        return _createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        _createdDate = createdDate;
    }

    public String getBatchId() {
        return _batchId;
    }

    public void setBatchId(String batchId) {
        _batchId = batchId;
    }

    public String getRebateTierFormulaId() {
        return _rebateTierFormulaId;
    }

    public void setRebateTierFormulaId(String rebateTierFormulaId) {
        _rebateTierFormulaId = rebateTierFormulaId;
    }

    public String getInboundStatus() {
        return _inboundStatus;
    }

    public void setInboundStatus(String inboundStatus) {
        _inboundStatus = inboundStatus;
    }

    public int getModifiedBy() {
        return _modifiedBy;
    }

    public void setModifiedBy(int modifiedBy) {
        _modifiedBy = modifiedBy;
    }

    public int getRebateTierFormulaSid() {
        return _rebateTierFormulaSid;
    }

    public void setRebateTierFormulaSid(int rebateTierFormulaSid) {
        _rebateTierFormulaSid = rebateTierFormulaSid;
    }
}
