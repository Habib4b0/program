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
public class AuditMasterInboundSoap implements Serializable {
    private String _receivedRecordAmountAttr;
    private int _modifiedBy;
    private Date _createdDate;
    private Date _interfaceRunEndDate;
    private String _applicationProcess;
    private double _discrepancyAmount;
    private String _batchId;
    private String _fileName;
    private String _sentRecordAmountAttribute;
    private String _status;
    private double _receivedRecordAmount;
    private double _validRecordAmount;
    private int _invalidRecordCount;
    private int _receivedRecordCount;
    private int _createdBy;
    private int _changeCount;
    private String _unprocessedRecords;
    private int _deleteCount;
    private Date _modifiedDate;
    private int _auditInboundSid;
    private double _sentRecordAmount;
    private int _sentRecordCount;
    private int _addCount;
    private String _source;
    private double _invalidRecordAmount;
    private Date _interfaceRunStartDate;

    public AuditMasterInboundSoap() {
    }

    public static AuditMasterInboundSoap toSoapModel(AuditMasterInbound model) {
        AuditMasterInboundSoap soapModel = new AuditMasterInboundSoap();

        soapModel.setReceivedRecordAmountAttr(model.getReceivedRecordAmountAttr());
        soapModel.setModifiedBy(model.getModifiedBy());
        soapModel.setCreatedDate(model.getCreatedDate());
        soapModel.setInterfaceRunEndDate(model.getInterfaceRunEndDate());
        soapModel.setApplicationProcess(model.getApplicationProcess());
        soapModel.setDiscrepancyAmount(model.getDiscrepancyAmount());
        soapModel.setBatchId(model.getBatchId());
        soapModel.setFileName(model.getFileName());
        soapModel.setSentRecordAmountAttribute(model.getSentRecordAmountAttribute());
        soapModel.setStatus(model.getStatus());
        soapModel.setReceivedRecordAmount(model.getReceivedRecordAmount());
        soapModel.setValidRecordAmount(model.getValidRecordAmount());
        soapModel.setInvalidRecordCount(model.getInvalidRecordCount());
        soapModel.setReceivedRecordCount(model.getReceivedRecordCount());
        soapModel.setCreatedBy(model.getCreatedBy());
        soapModel.setChangeCount(model.getChangeCount());
        soapModel.setUnprocessedRecords(model.getUnprocessedRecords());
        soapModel.setDeleteCount(model.getDeleteCount());
        soapModel.setModifiedDate(model.getModifiedDate());
        soapModel.setAuditInboundSid(model.getAuditInboundSid());
        soapModel.setSentRecordAmount(model.getSentRecordAmount());
        soapModel.setSentRecordCount(model.getSentRecordCount());
        soapModel.setAddCount(model.getAddCount());
        soapModel.setSource(model.getSource());
        soapModel.setInvalidRecordAmount(model.getInvalidRecordAmount());
        soapModel.setInterfaceRunStartDate(model.getInterfaceRunStartDate());

        return soapModel;
    }

    public static AuditMasterInboundSoap[] toSoapModels(
        AuditMasterInbound[] models) {
        AuditMasterInboundSoap[] soapModels = new AuditMasterInboundSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static AuditMasterInboundSoap[][] toSoapModels(
        AuditMasterInbound[][] models) {
        AuditMasterInboundSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new AuditMasterInboundSoap[models.length][models[0].length];
        } else {
            soapModels = new AuditMasterInboundSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static AuditMasterInboundSoap[] toSoapModels(
        List<AuditMasterInbound> models) {
        List<AuditMasterInboundSoap> soapModels = new ArrayList<AuditMasterInboundSoap>(models.size());

        for (AuditMasterInbound model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new AuditMasterInboundSoap[soapModels.size()]);
    }

    public int getPrimaryKey() {
        return _auditInboundSid;
    }

    public void setPrimaryKey(int pk) {
        setAuditInboundSid(pk);
    }

    public String getReceivedRecordAmountAttr() {
        return _receivedRecordAmountAttr;
    }

    public void setReceivedRecordAmountAttr(String receivedRecordAmountAttr) {
        _receivedRecordAmountAttr = receivedRecordAmountAttr;
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

    public Date getInterfaceRunEndDate() {
        return _interfaceRunEndDate;
    }

    public void setInterfaceRunEndDate(Date interfaceRunEndDate) {
        _interfaceRunEndDate = interfaceRunEndDate;
    }

    public String getApplicationProcess() {
        return _applicationProcess;
    }

    public void setApplicationProcess(String applicationProcess) {
        _applicationProcess = applicationProcess;
    }

    public double getDiscrepancyAmount() {
        return _discrepancyAmount;
    }

    public void setDiscrepancyAmount(double discrepancyAmount) {
        _discrepancyAmount = discrepancyAmount;
    }

    public String getBatchId() {
        return _batchId;
    }

    public void setBatchId(String batchId) {
        _batchId = batchId;
    }

    public String getFileName() {
        return _fileName;
    }

    public void setFileName(String fileName) {
        _fileName = fileName;
    }

    public String getSentRecordAmountAttribute() {
        return _sentRecordAmountAttribute;
    }

    public void setSentRecordAmountAttribute(String sentRecordAmountAttribute) {
        _sentRecordAmountAttribute = sentRecordAmountAttribute;
    }

    public String getStatus() {
        return _status;
    }

    public void setStatus(String status) {
        _status = status;
    }

    public double getReceivedRecordAmount() {
        return _receivedRecordAmount;
    }

    public void setReceivedRecordAmount(double receivedRecordAmount) {
        _receivedRecordAmount = receivedRecordAmount;
    }

    public double getValidRecordAmount() {
        return _validRecordAmount;
    }

    public void setValidRecordAmount(double validRecordAmount) {
        _validRecordAmount = validRecordAmount;
    }

    public int getInvalidRecordCount() {
        return _invalidRecordCount;
    }

    public void setInvalidRecordCount(int invalidRecordCount) {
        _invalidRecordCount = invalidRecordCount;
    }

    public int getReceivedRecordCount() {
        return _receivedRecordCount;
    }

    public void setReceivedRecordCount(int receivedRecordCount) {
        _receivedRecordCount = receivedRecordCount;
    }

    public int getCreatedBy() {
        return _createdBy;
    }

    public void setCreatedBy(int createdBy) {
        _createdBy = createdBy;
    }

    public int getChangeCount() {
        return _changeCount;
    }

    public void setChangeCount(int changeCount) {
        _changeCount = changeCount;
    }

    public String getUnprocessedRecords() {
        return _unprocessedRecords;
    }

    public void setUnprocessedRecords(String unprocessedRecords) {
        _unprocessedRecords = unprocessedRecords;
    }

    public int getDeleteCount() {
        return _deleteCount;
    }

    public void setDeleteCount(int deleteCount) {
        _deleteCount = deleteCount;
    }

    public Date getModifiedDate() {
        return _modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        _modifiedDate = modifiedDate;
    }

    public int getAuditInboundSid() {
        return _auditInboundSid;
    }

    public void setAuditInboundSid(int auditInboundSid) {
        _auditInboundSid = auditInboundSid;
    }

    public double getSentRecordAmount() {
        return _sentRecordAmount;
    }

    public void setSentRecordAmount(double sentRecordAmount) {
        _sentRecordAmount = sentRecordAmount;
    }

    public int getSentRecordCount() {
        return _sentRecordCount;
    }

    public void setSentRecordCount(int sentRecordCount) {
        _sentRecordCount = sentRecordCount;
    }

    public int getAddCount() {
        return _addCount;
    }

    public void setAddCount(int addCount) {
        _addCount = addCount;
    }

    public String getSource() {
        return _source;
    }

    public void setSource(String source) {
        _source = source;
    }

    public double getInvalidRecordAmount() {
        return _invalidRecordAmount;
    }

    public void setInvalidRecordAmount(double invalidRecordAmount) {
        _invalidRecordAmount = invalidRecordAmount;
    }

    public Date getInterfaceRunStartDate() {
        return _interfaceRunStartDate;
    }

    public void setInterfaceRunStartDate(Date interfaceRunStartDate) {
        _interfaceRunStartDate = interfaceRunStartDate;
    }
}
