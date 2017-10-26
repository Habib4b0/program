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
public class SalesBasisDetailsSoap implements Serializable {
    private int _createdBy;
    private int _netSalesFormulaMasterSid;
    private boolean _recordLockStatus;
    private int _modifiedBy;
    private Date _createdDate;
    private int _contractMasterSid;
    private String _source;
    private int _cdrModelSid;
    private int _salesBasisDetailsSid;
    private int _cfpContractDetailsSid;
    private Date _modifiedDate;
    private String _inboundStatus;

    public SalesBasisDetailsSoap() {
    }

    public static SalesBasisDetailsSoap toSoapModel(SalesBasisDetails model) {
        SalesBasisDetailsSoap soapModel = new SalesBasisDetailsSoap();

        soapModel.setCreatedBy(model.getCreatedBy());
        soapModel.setNetSalesFormulaMasterSid(model.getNetSalesFormulaMasterSid());
        soapModel.setRecordLockStatus(model.getRecordLockStatus());
        soapModel.setModifiedBy(model.getModifiedBy());
        soapModel.setCreatedDate(model.getCreatedDate());
        soapModel.setContractMasterSid(model.getContractMasterSid());
        soapModel.setSource(model.getSource());
        soapModel.setCdrModelSid(model.getCdrModelSid());
        soapModel.setSalesBasisDetailsSid(model.getSalesBasisDetailsSid());
        soapModel.setCfpContractDetailsSid(model.getCfpContractDetailsSid());
        soapModel.setModifiedDate(model.getModifiedDate());
        soapModel.setInboundStatus(model.getInboundStatus());

        return soapModel;
    }

    public static SalesBasisDetailsSoap[] toSoapModels(
        SalesBasisDetails[] models) {
        SalesBasisDetailsSoap[] soapModels = new SalesBasisDetailsSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static SalesBasisDetailsSoap[][] toSoapModels(
        SalesBasisDetails[][] models) {
        SalesBasisDetailsSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new SalesBasisDetailsSoap[models.length][models[0].length];
        } else {
            soapModels = new SalesBasisDetailsSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static SalesBasisDetailsSoap[] toSoapModels(
        List<SalesBasisDetails> models) {
        List<SalesBasisDetailsSoap> soapModels = new ArrayList<SalesBasisDetailsSoap>(models.size());

        for (SalesBasisDetails model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new SalesBasisDetailsSoap[soapModels.size()]);
    }

    public int getPrimaryKey() {
        return _salesBasisDetailsSid;
    }

    public void setPrimaryKey(int pk) {
        setSalesBasisDetailsSid(pk);
    }

    public int getCreatedBy() {
        return _createdBy;
    }

    public void setCreatedBy(int createdBy) {
        _createdBy = createdBy;
    }

    public int getNetSalesFormulaMasterSid() {
        return _netSalesFormulaMasterSid;
    }

    public void setNetSalesFormulaMasterSid(int netSalesFormulaMasterSid) {
        _netSalesFormulaMasterSid = netSalesFormulaMasterSid;
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

    public int getContractMasterSid() {
        return _contractMasterSid;
    }

    public void setContractMasterSid(int contractMasterSid) {
        _contractMasterSid = contractMasterSid;
    }

    public String getSource() {
        return _source;
    }

    public void setSource(String source) {
        _source = source;
    }

    public int getCdrModelSid() {
        return _cdrModelSid;
    }

    public void setCdrModelSid(int cdrModelSid) {
        _cdrModelSid = cdrModelSid;
    }

    public int getSalesBasisDetailsSid() {
        return _salesBasisDetailsSid;
    }

    public void setSalesBasisDetailsSid(int salesBasisDetailsSid) {
        _salesBasisDetailsSid = salesBasisDetailsSid;
    }

    public int getCfpContractDetailsSid() {
        return _cfpContractDetailsSid;
    }

    public void setCfpContractDetailsSid(int cfpContractDetailsSid) {
        _cfpContractDetailsSid = cfpContractDetailsSid;
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
