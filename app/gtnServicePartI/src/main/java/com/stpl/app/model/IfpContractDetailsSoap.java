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
public class IfpContractDetailsSoap implements Serializable {
    private int _itemStatus;
    private int _itemMasterSid;
    private Date _ifpContractAttachedDate;
    private Date _itemEndDate;
    private String _totalVolumeCommitment;
    private String _totalDollarCommitment;
    private int _ifpContractAttachedStatus;
    private Date _modifiedDate;
    private String _totalMarketshareCommitment;
    private boolean _recordLockStatus;
    private Date _createdDate;
    private int _createdBy;
    private String _source;
    private Date _itemStartDate;
    private String _batchId;
    private int _ifpContractDetailsSid;
    private int _modifiedBy;
    private String _inboundStatus;
    private int _ifpContractSid;
    private String _commitmentPeriod;

    public IfpContractDetailsSoap() {
    }

    public static IfpContractDetailsSoap toSoapModel(IfpContractDetails model) {
        IfpContractDetailsSoap soapModel = new IfpContractDetailsSoap();

        soapModel.setItemStatus(model.getItemStatus());
        soapModel.setItemMasterSid(model.getItemMasterSid());
        soapModel.setIfpContractAttachedDate(model.getIfpContractAttachedDate());
        soapModel.setItemEndDate(model.getItemEndDate());
        soapModel.setTotalVolumeCommitment(model.getTotalVolumeCommitment());
        soapModel.setTotalDollarCommitment(model.getTotalDollarCommitment());
        soapModel.setIfpContractAttachedStatus(model.getIfpContractAttachedStatus());
        soapModel.setModifiedDate(model.getModifiedDate());
        soapModel.setTotalMarketshareCommitment(model.getTotalMarketshareCommitment());
        soapModel.setRecordLockStatus(model.getRecordLockStatus());
        soapModel.setCreatedDate(model.getCreatedDate());
        soapModel.setCreatedBy(model.getCreatedBy());
        soapModel.setSource(model.getSource());
        soapModel.setItemStartDate(model.getItemStartDate());
        soapModel.setBatchId(model.getBatchId());
        soapModel.setIfpContractDetailsSid(model.getIfpContractDetailsSid());
        soapModel.setModifiedBy(model.getModifiedBy());
        soapModel.setInboundStatus(model.getInboundStatus());
        soapModel.setIfpContractSid(model.getIfpContractSid());
        soapModel.setCommitmentPeriod(model.getCommitmentPeriod());

        return soapModel;
    }

    public static IfpContractDetailsSoap[] toSoapModels(
        IfpContractDetails[] models) {
        IfpContractDetailsSoap[] soapModels = new IfpContractDetailsSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static IfpContractDetailsSoap[][] toSoapModels(
        IfpContractDetails[][] models) {
        IfpContractDetailsSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new IfpContractDetailsSoap[models.length][models[0].length];
        } else {
            soapModels = new IfpContractDetailsSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static IfpContractDetailsSoap[] toSoapModels(
        List<IfpContractDetails> models) {
        List<IfpContractDetailsSoap> soapModels = new ArrayList<IfpContractDetailsSoap>(models.size());

        for (IfpContractDetails model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new IfpContractDetailsSoap[soapModels.size()]);
    }

    public int getPrimaryKey() {
        return _ifpContractDetailsSid;
    }

    public void setPrimaryKey(int pk) {
        setIfpContractDetailsSid(pk);
    }

    public int getItemStatus() {
        return _itemStatus;
    }

    public void setItemStatus(int itemStatus) {
        _itemStatus = itemStatus;
    }

    public int getItemMasterSid() {
        return _itemMasterSid;
    }

    public void setItemMasterSid(int itemMasterSid) {
        _itemMasterSid = itemMasterSid;
    }

    public Date getIfpContractAttachedDate() {
        return _ifpContractAttachedDate;
    }

    public void setIfpContractAttachedDate(Date ifpContractAttachedDate) {
        _ifpContractAttachedDate = ifpContractAttachedDate;
    }

    public Date getItemEndDate() {
        return _itemEndDate;
    }

    public void setItemEndDate(Date itemEndDate) {
        _itemEndDate = itemEndDate;
    }

    public String getTotalVolumeCommitment() {
        return _totalVolumeCommitment;
    }

    public void setTotalVolumeCommitment(String totalVolumeCommitment) {
        _totalVolumeCommitment = totalVolumeCommitment;
    }

    public String getTotalDollarCommitment() {
        return _totalDollarCommitment;
    }

    public void setTotalDollarCommitment(String totalDollarCommitment) {
        _totalDollarCommitment = totalDollarCommitment;
    }

    public int getIfpContractAttachedStatus() {
        return _ifpContractAttachedStatus;
    }

    public void setIfpContractAttachedStatus(int ifpContractAttachedStatus) {
        _ifpContractAttachedStatus = ifpContractAttachedStatus;
    }

    public Date getModifiedDate() {
        return _modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        _modifiedDate = modifiedDate;
    }

    public String getTotalMarketshareCommitment() {
        return _totalMarketshareCommitment;
    }

    public void setTotalMarketshareCommitment(String totalMarketshareCommitment) {
        _totalMarketshareCommitment = totalMarketshareCommitment;
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

    public Date getCreatedDate() {
        return _createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        _createdDate = createdDate;
    }

    public int getCreatedBy() {
        return _createdBy;
    }

    public void setCreatedBy(int createdBy) {
        _createdBy = createdBy;
    }

    public String getSource() {
        return _source;
    }

    public void setSource(String source) {
        _source = source;
    }

    public Date getItemStartDate() {
        return _itemStartDate;
    }

    public void setItemStartDate(Date itemStartDate) {
        _itemStartDate = itemStartDate;
    }

    public String getBatchId() {
        return _batchId;
    }

    public void setBatchId(String batchId) {
        _batchId = batchId;
    }

    public int getIfpContractDetailsSid() {
        return _ifpContractDetailsSid;
    }

    public void setIfpContractDetailsSid(int ifpContractDetailsSid) {
        _ifpContractDetailsSid = ifpContractDetailsSid;
    }

    public int getModifiedBy() {
        return _modifiedBy;
    }

    public void setModifiedBy(int modifiedBy) {
        _modifiedBy = modifiedBy;
    }

    public String getInboundStatus() {
        return _inboundStatus;
    }

    public void setInboundStatus(String inboundStatus) {
        _inboundStatus = inboundStatus;
    }

    public int getIfpContractSid() {
        return _ifpContractSid;
    }

    public void setIfpContractSid(int ifpContractSid) {
        _ifpContractSid = ifpContractSid;
    }

    public String getCommitmentPeriod() {
        return _commitmentPeriod;
    }

    public void setCommitmentPeriod(String commitmentPeriod) {
        _commitmentPeriod = commitmentPeriod;
    }
}
