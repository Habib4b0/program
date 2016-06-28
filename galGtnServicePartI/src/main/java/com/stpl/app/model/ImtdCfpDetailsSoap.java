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
public class ImtdCfpDetailsSoap implements Serializable {
    private String _companyNo;
    private int _imtdCfpDetailsSid;
    private Date _cfpDetailsStartDate;
    private String _companyType;
    private Date _cfpDetailsTcStartDate;
    private int _modifiedBy;
    private Date _createdDate;
    private Date _cfpDetailsTcEndDate;
    private Date _cfpDetailsCreatedDate;
    private Date _imtdCreatedDate;
    private Date _cfpDetailsModifiedDate;
    private int _cfpDetailsAttachedStatus;
    private boolean _checkRecord;
    private Date _cfpDetailsAttachedDate;
    private Date _cfpDetailsEndDate;
    private String _companyId;
    private String _cfpDetailsTradeClass;
    private String _tradingPartnerContractNo;
    private int _createdBy;
    private String _usersSid;
    private Date _companyStartDate;
    private String _cfpDetailsModifiedBy;
    private Date _companyEndDate;
    private int _companyMasterSid;
    private int _cfpModelSid;
    private int _cfpDetailsSid;
    private int _companyStatus;
    private Date _modifiedDate;
    private String _companyName;
    private String _operation;
    private String _cfpDetailsCreatedBy;
    private String _sessionId;

    public ImtdCfpDetailsSoap() {
    }

    public static ImtdCfpDetailsSoap toSoapModel(ImtdCfpDetails model) {
        ImtdCfpDetailsSoap soapModel = new ImtdCfpDetailsSoap();

        soapModel.setCompanyNo(model.getCompanyNo());
        soapModel.setImtdCfpDetailsSid(model.getImtdCfpDetailsSid());
        soapModel.setCfpDetailsStartDate(model.getCfpDetailsStartDate());
        soapModel.setCompanyType(model.getCompanyType());
        soapModel.setCfpDetailsTcStartDate(model.getCfpDetailsTcStartDate());
        soapModel.setModifiedBy(model.getModifiedBy());
        soapModel.setCreatedDate(model.getCreatedDate());
        soapModel.setCfpDetailsTcEndDate(model.getCfpDetailsTcEndDate());
        soapModel.setCfpDetailsCreatedDate(model.getCfpDetailsCreatedDate());
        soapModel.setImtdCreatedDate(model.getImtdCreatedDate());
        soapModel.setCfpDetailsModifiedDate(model.getCfpDetailsModifiedDate());
        soapModel.setCfpDetailsAttachedStatus(model.getCfpDetailsAttachedStatus());
        soapModel.setCheckRecord(model.getCheckRecord());
        soapModel.setCfpDetailsAttachedDate(model.getCfpDetailsAttachedDate());
        soapModel.setCfpDetailsEndDate(model.getCfpDetailsEndDate());
        soapModel.setCompanyId(model.getCompanyId());
        soapModel.setCfpDetailsTradeClass(model.getCfpDetailsTradeClass());
        soapModel.setTradingPartnerContractNo(model.getTradingPartnerContractNo());
        soapModel.setCreatedBy(model.getCreatedBy());
        soapModel.setUsersSid(model.getUsersSid());
        soapModel.setCompanyStartDate(model.getCompanyStartDate());
        soapModel.setCfpDetailsModifiedBy(model.getCfpDetailsModifiedBy());
        soapModel.setCompanyEndDate(model.getCompanyEndDate());
        soapModel.setCompanyMasterSid(model.getCompanyMasterSid());
        soapModel.setCfpModelSid(model.getCfpModelSid());
        soapModel.setCfpDetailsSid(model.getCfpDetailsSid());
        soapModel.setCompanyStatus(model.getCompanyStatus());
        soapModel.setModifiedDate(model.getModifiedDate());
        soapModel.setCompanyName(model.getCompanyName());
        soapModel.setOperation(model.getOperation());
        soapModel.setCfpDetailsCreatedBy(model.getCfpDetailsCreatedBy());
        soapModel.setSessionId(model.getSessionId());

        return soapModel;
    }

    public static ImtdCfpDetailsSoap[] toSoapModels(ImtdCfpDetails[] models) {
        ImtdCfpDetailsSoap[] soapModels = new ImtdCfpDetailsSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static ImtdCfpDetailsSoap[][] toSoapModels(ImtdCfpDetails[][] models) {
        ImtdCfpDetailsSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new ImtdCfpDetailsSoap[models.length][models[0].length];
        } else {
            soapModels = new ImtdCfpDetailsSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static ImtdCfpDetailsSoap[] toSoapModels(List<ImtdCfpDetails> models) {
        List<ImtdCfpDetailsSoap> soapModels = new ArrayList<ImtdCfpDetailsSoap>(models.size());

        for (ImtdCfpDetails model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new ImtdCfpDetailsSoap[soapModels.size()]);
    }

    public int getPrimaryKey() {
        return _imtdCfpDetailsSid;
    }

    public void setPrimaryKey(int pk) {
        setImtdCfpDetailsSid(pk);
    }

    public String getCompanyNo() {
        return _companyNo;
    }

    public void setCompanyNo(String companyNo) {
        _companyNo = companyNo;
    }

    public int getImtdCfpDetailsSid() {
        return _imtdCfpDetailsSid;
    }

    public void setImtdCfpDetailsSid(int imtdCfpDetailsSid) {
        _imtdCfpDetailsSid = imtdCfpDetailsSid;
    }

    public Date getCfpDetailsStartDate() {
        return _cfpDetailsStartDate;
    }

    public void setCfpDetailsStartDate(Date cfpDetailsStartDate) {
        _cfpDetailsStartDate = cfpDetailsStartDate;
    }

    public String getCompanyType() {
        return _companyType;
    }

    public void setCompanyType(String companyType) {
        _companyType = companyType;
    }

    public Date getCfpDetailsTcStartDate() {
        return _cfpDetailsTcStartDate;
    }

    public void setCfpDetailsTcStartDate(Date cfpDetailsTcStartDate) {
        _cfpDetailsTcStartDate = cfpDetailsTcStartDate;
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

    public Date getCfpDetailsTcEndDate() {
        return _cfpDetailsTcEndDate;
    }

    public void setCfpDetailsTcEndDate(Date cfpDetailsTcEndDate) {
        _cfpDetailsTcEndDate = cfpDetailsTcEndDate;
    }

    public Date getCfpDetailsCreatedDate() {
        return _cfpDetailsCreatedDate;
    }

    public void setCfpDetailsCreatedDate(Date cfpDetailsCreatedDate) {
        _cfpDetailsCreatedDate = cfpDetailsCreatedDate;
    }

    public Date getImtdCreatedDate() {
        return _imtdCreatedDate;
    }

    public void setImtdCreatedDate(Date imtdCreatedDate) {
        _imtdCreatedDate = imtdCreatedDate;
    }

    public Date getCfpDetailsModifiedDate() {
        return _cfpDetailsModifiedDate;
    }

    public void setCfpDetailsModifiedDate(Date cfpDetailsModifiedDate) {
        _cfpDetailsModifiedDate = cfpDetailsModifiedDate;
    }

    public int getCfpDetailsAttachedStatus() {
        return _cfpDetailsAttachedStatus;
    }

    public void setCfpDetailsAttachedStatus(int cfpDetailsAttachedStatus) {
        _cfpDetailsAttachedStatus = cfpDetailsAttachedStatus;
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

    public Date getCfpDetailsAttachedDate() {
        return _cfpDetailsAttachedDate;
    }

    public void setCfpDetailsAttachedDate(Date cfpDetailsAttachedDate) {
        _cfpDetailsAttachedDate = cfpDetailsAttachedDate;
    }

    public Date getCfpDetailsEndDate() {
        return _cfpDetailsEndDate;
    }

    public void setCfpDetailsEndDate(Date cfpDetailsEndDate) {
        _cfpDetailsEndDate = cfpDetailsEndDate;
    }

    public String getCompanyId() {
        return _companyId;
    }

    public void setCompanyId(String companyId) {
        _companyId = companyId;
    }

    public String getCfpDetailsTradeClass() {
        return _cfpDetailsTradeClass;
    }

    public void setCfpDetailsTradeClass(String cfpDetailsTradeClass) {
        _cfpDetailsTradeClass = cfpDetailsTradeClass;
    }

    public String getTradingPartnerContractNo() {
        return _tradingPartnerContractNo;
    }

    public void setTradingPartnerContractNo(String tradingPartnerContractNo) {
        _tradingPartnerContractNo = tradingPartnerContractNo;
    }

    public int getCreatedBy() {
        return _createdBy;
    }

    public void setCreatedBy(int createdBy) {
        _createdBy = createdBy;
    }

    public String getUsersSid() {
        return _usersSid;
    }

    public void setUsersSid(String usersSid) {
        _usersSid = usersSid;
    }

    public Date getCompanyStartDate() {
        return _companyStartDate;
    }

    public void setCompanyStartDate(Date companyStartDate) {
        _companyStartDate = companyStartDate;
    }

    public String getCfpDetailsModifiedBy() {
        return _cfpDetailsModifiedBy;
    }

    public void setCfpDetailsModifiedBy(String cfpDetailsModifiedBy) {
        _cfpDetailsModifiedBy = cfpDetailsModifiedBy;
    }

    public Date getCompanyEndDate() {
        return _companyEndDate;
    }

    public void setCompanyEndDate(Date companyEndDate) {
        _companyEndDate = companyEndDate;
    }

    public int getCompanyMasterSid() {
        return _companyMasterSid;
    }

    public void setCompanyMasterSid(int companyMasterSid) {
        _companyMasterSid = companyMasterSid;
    }

    public int getCfpModelSid() {
        return _cfpModelSid;
    }

    public void setCfpModelSid(int cfpModelSid) {
        _cfpModelSid = cfpModelSid;
    }

    public int getCfpDetailsSid() {
        return _cfpDetailsSid;
    }

    public void setCfpDetailsSid(int cfpDetailsSid) {
        _cfpDetailsSid = cfpDetailsSid;
    }

    public int getCompanyStatus() {
        return _companyStatus;
    }

    public void setCompanyStatus(int companyStatus) {
        _companyStatus = companyStatus;
    }

    public Date getModifiedDate() {
        return _modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        _modifiedDate = modifiedDate;
    }

    public String getCompanyName() {
        return _companyName;
    }

    public void setCompanyName(String companyName) {
        _companyName = companyName;
    }

    public String getOperation() {
        return _operation;
    }

    public void setOperation(String operation) {
        _operation = operation;
    }

    public String getCfpDetailsCreatedBy() {
        return _cfpDetailsCreatedBy;
    }

    public void setCfpDetailsCreatedBy(String cfpDetailsCreatedBy) {
        _cfpDetailsCreatedBy = cfpDetailsCreatedBy;
    }

    public String getSessionId() {
        return _sessionId;
    }

    public void setSessionId(String sessionId) {
        _sessionId = sessionId;
    }
}
