package com.stpl.app.parttwo.model;

import com.stpl.app.parttwo.service.persistence.ArpOutboundPK;

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
public class ArpOutboundSoap implements Serializable {
    private double _salesUnitsRate;
    private String _accountType;
    private String _originalBatchId;
    private int _companyMasterSid;
    private int _brandMasterSid;
    private Date _arpApprovalDate;
    private int _arpMasterSid;
    private Date _arpCreationDate;
    private boolean _checkRecord;
    private int _arpId;
    private String _account;
    private boolean _outboundStatus;
    private int _periodSid;
    private int _itemMasterSid;
    private int _rsModelSid;

    public ArpOutboundSoap() {
    }

    public static ArpOutboundSoap toSoapModel(ArpOutbound model) {
        ArpOutboundSoap soapModel = new ArpOutboundSoap();

        soapModel.setSalesUnitsRate(model.getSalesUnitsRate());
        soapModel.setAccountType(model.getAccountType());
        soapModel.setOriginalBatchId(model.getOriginalBatchId());
        soapModel.setCompanyMasterSid(model.getCompanyMasterSid());
        soapModel.setBrandMasterSid(model.getBrandMasterSid());
        soapModel.setArpApprovalDate(model.getArpApprovalDate());
        soapModel.setArpMasterSid(model.getArpMasterSid());
        soapModel.setArpCreationDate(model.getArpCreationDate());
        soapModel.setCheckRecord(model.getCheckRecord());
        soapModel.setArpId(model.getArpId());
        soapModel.setAccount(model.getAccount());
        soapModel.setOutboundStatus(model.getOutboundStatus());
        soapModel.setPeriodSid(model.getPeriodSid());
        soapModel.setItemMasterSid(model.getItemMasterSid());
        soapModel.setRsModelSid(model.getRsModelSid());

        return soapModel;
    }

    public static ArpOutboundSoap[] toSoapModels(ArpOutbound[] models) {
        ArpOutboundSoap[] soapModels = new ArpOutboundSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static ArpOutboundSoap[][] toSoapModels(ArpOutbound[][] models) {
        ArpOutboundSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new ArpOutboundSoap[models.length][models[0].length];
        } else {
            soapModels = new ArpOutboundSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static ArpOutboundSoap[] toSoapModels(List<ArpOutbound> models) {
        List<ArpOutboundSoap> soapModels = new ArrayList<ArpOutboundSoap>(models.size());

        for (ArpOutbound model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new ArpOutboundSoap[soapModels.size()]);
    }

    public ArpOutboundPK getPrimaryKey() {
        return new ArpOutboundPK(_companyMasterSid, _arpMasterSid, _arpId,
            _periodSid, _itemMasterSid, _rsModelSid);
    }

    public void setPrimaryKey(ArpOutboundPK pk) {
        setCompanyMasterSid(pk.companyMasterSid);
        setArpMasterSid(pk.arpMasterSid);
        setArpId(pk.arpId);
        setPeriodSid(pk.periodSid);
        setItemMasterSid(pk.itemMasterSid);
        setRsModelSid(pk.rsModelSid);
    }

    public double getSalesUnitsRate() {
        return _salesUnitsRate;
    }

    public void setSalesUnitsRate(double salesUnitsRate) {
        _salesUnitsRate = salesUnitsRate;
    }

    public String getAccountType() {
        return _accountType;
    }

    public void setAccountType(String accountType) {
        _accountType = accountType;
    }

    public String getOriginalBatchId() {
        return _originalBatchId;
    }

    public void setOriginalBatchId(String originalBatchId) {
        _originalBatchId = originalBatchId;
    }

    public int getCompanyMasterSid() {
        return _companyMasterSid;
    }

    public void setCompanyMasterSid(int companyMasterSid) {
        _companyMasterSid = companyMasterSid;
    }

    public int getBrandMasterSid() {
        return _brandMasterSid;
    }

    public void setBrandMasterSid(int brandMasterSid) {
        _brandMasterSid = brandMasterSid;
    }

    public Date getArpApprovalDate() {
        return _arpApprovalDate;
    }

    public void setArpApprovalDate(Date arpApprovalDate) {
        _arpApprovalDate = arpApprovalDate;
    }

    public int getArpMasterSid() {
        return _arpMasterSid;
    }

    public void setArpMasterSid(int arpMasterSid) {
        _arpMasterSid = arpMasterSid;
    }

    public Date getArpCreationDate() {
        return _arpCreationDate;
    }

    public void setArpCreationDate(Date arpCreationDate) {
        _arpCreationDate = arpCreationDate;
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

    public int getArpId() {
        return _arpId;
    }

    public void setArpId(int arpId) {
        _arpId = arpId;
    }

    public String getAccount() {
        return _account;
    }

    public void setAccount(String account) {
        _account = account;
    }

    public boolean getOutboundStatus() {
        return _outboundStatus;
    }

    public boolean isOutboundStatus() {
        return _outboundStatus;
    }

    public void setOutboundStatus(boolean outboundStatus) {
        _outboundStatus = outboundStatus;
    }

    public int getPeriodSid() {
        return _periodSid;
    }

    public void setPeriodSid(int periodSid) {
        _periodSid = periodSid;
    }

    public int getItemMasterSid() {
        return _itemMasterSid;
    }

    public void setItemMasterSid(int itemMasterSid) {
        _itemMasterSid = itemMasterSid;
    }

    public int getRsModelSid() {
        return _rsModelSid;
    }

    public void setRsModelSid(int rsModelSid) {
        _rsModelSid = rsModelSid;
    }
}
