package com.stpl.app.parttwo.model;

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
public class IvldCustomerGtsActualSoap implements Serializable {
    private String _parentAccountId;
    private int _ivldCustomerGtsActualSid;
    private String _accountId;
    private String _itemId;
    private Date _orderReceivedDate;
    private Date _modifiedDate;
    private String _orderNumber;
    private String _organizationKey;
    private String _source;
    private String _createdBy;
    private String _createdDate;
    private String _addChgDelIndicator;
    private String _errorCode;
    private String _itemUom;
    private String _invoiceNumber;
    private String _modifiedBy;
    private Date _intfInsertedDate;
    private String _lotNo;
    private String _reprocessedFlag;
    private String _quantity;
    private String _invoiceLineNumber;
    private String _contractId;
    private String _reasonForFailure;
    private String _amount;
    private Date _invoiceDate;
    private String _customerGtsActualIntfId;
    private String _batchId;
    private String _salesId;
    private String _errorField;
    private boolean _checkRecord;

    public IvldCustomerGtsActualSoap() {
    }

    public static IvldCustomerGtsActualSoap toSoapModel(
        IvldCustomerGtsActual model) {
        IvldCustomerGtsActualSoap soapModel = new IvldCustomerGtsActualSoap();

        soapModel.setParentAccountId(model.getParentAccountId());
        soapModel.setIvldCustomerGtsActualSid(model.getIvldCustomerGtsActualSid());
        soapModel.setAccountId(model.getAccountId());
        soapModel.setItemId(model.getItemId());
        soapModel.setOrderReceivedDate(model.getOrderReceivedDate());
        soapModel.setModifiedDate(model.getModifiedDate());
        soapModel.setOrderNumber(model.getOrderNumber());
        soapModel.setOrganizationKey(model.getOrganizationKey());
        soapModel.setSource(model.getSource());
        soapModel.setCreatedBy(model.getCreatedBy());
        soapModel.setCreatedDate(model.getCreatedDate());
        soapModel.setAddChgDelIndicator(model.getAddChgDelIndicator());
        soapModel.setErrorCode(model.getErrorCode());
        soapModel.setItemUom(model.getItemUom());
        soapModel.setInvoiceNumber(model.getInvoiceNumber());
        soapModel.setModifiedBy(model.getModifiedBy());
        soapModel.setIntfInsertedDate(model.getIntfInsertedDate());
        soapModel.setLotNo(model.getLotNo());
        soapModel.setReprocessedFlag(model.getReprocessedFlag());
        soapModel.setQuantity(model.getQuantity());
        soapModel.setInvoiceLineNumber(model.getInvoiceLineNumber());
        soapModel.setContractId(model.getContractId());
        soapModel.setReasonForFailure(model.getReasonForFailure());
        soapModel.setAmount(model.getAmount());
        soapModel.setInvoiceDate(model.getInvoiceDate());
        soapModel.setCustomerGtsActualIntfId(model.getCustomerGtsActualIntfId());
        soapModel.setBatchId(model.getBatchId());
        soapModel.setSalesId(model.getSalesId());
        soapModel.setErrorField(model.getErrorField());
        soapModel.setCheckRecord(model.getCheckRecord());

        return soapModel;
    }

    public static IvldCustomerGtsActualSoap[] toSoapModels(
        IvldCustomerGtsActual[] models) {
        IvldCustomerGtsActualSoap[] soapModels = new IvldCustomerGtsActualSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static IvldCustomerGtsActualSoap[][] toSoapModels(
        IvldCustomerGtsActual[][] models) {
        IvldCustomerGtsActualSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new IvldCustomerGtsActualSoap[models.length][models[0].length];
        } else {
            soapModels = new IvldCustomerGtsActualSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static IvldCustomerGtsActualSoap[] toSoapModels(
        List<IvldCustomerGtsActual> models) {
        List<IvldCustomerGtsActualSoap> soapModels = new ArrayList<IvldCustomerGtsActualSoap>(models.size());

        for (IvldCustomerGtsActual model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new IvldCustomerGtsActualSoap[soapModels.size()]);
    }

    public int getPrimaryKey() {
        return _ivldCustomerGtsActualSid;
    }

    public void setPrimaryKey(int pk) {
        setIvldCustomerGtsActualSid(pk);
    }

    public String getParentAccountId() {
        return _parentAccountId;
    }

    public void setParentAccountId(String parentAccountId) {
        _parentAccountId = parentAccountId;
    }

    public int getIvldCustomerGtsActualSid() {
        return _ivldCustomerGtsActualSid;
    }

    public void setIvldCustomerGtsActualSid(int ivldCustomerGtsActualSid) {
        _ivldCustomerGtsActualSid = ivldCustomerGtsActualSid;
    }

    public String getAccountId() {
        return _accountId;
    }

    public void setAccountId(String accountId) {
        _accountId = accountId;
    }

    public String getItemId() {
        return _itemId;
    }

    public void setItemId(String itemId) {
        _itemId = itemId;
    }

    public Date getOrderReceivedDate() {
        return _orderReceivedDate;
    }

    public void setOrderReceivedDate(Date orderReceivedDate) {
        _orderReceivedDate = orderReceivedDate;
    }

    public Date getModifiedDate() {
        return _modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        _modifiedDate = modifiedDate;
    }

    public String getOrderNumber() {
        return _orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        _orderNumber = orderNumber;
    }

    public String getOrganizationKey() {
        return _organizationKey;
    }

    public void setOrganizationKey(String organizationKey) {
        _organizationKey = organizationKey;
    }

    public String getSource() {
        return _source;
    }

    public void setSource(String source) {
        _source = source;
    }

    public String getCreatedBy() {
        return _createdBy;
    }

    public void setCreatedBy(String createdBy) {
        _createdBy = createdBy;
    }

    public String getCreatedDate() {
        return _createdDate;
    }

    public void setCreatedDate(String createdDate) {
        _createdDate = createdDate;
    }

    public String getAddChgDelIndicator() {
        return _addChgDelIndicator;
    }

    public void setAddChgDelIndicator(String addChgDelIndicator) {
        _addChgDelIndicator = addChgDelIndicator;
    }

    public String getErrorCode() {
        return _errorCode;
    }

    public void setErrorCode(String errorCode) {
        _errorCode = errorCode;
    }

    public String getItemUom() {
        return _itemUom;
    }

    public void setItemUom(String itemUom) {
        _itemUom = itemUom;
    }

    public String getInvoiceNumber() {
        return _invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        _invoiceNumber = invoiceNumber;
    }

    public String getModifiedBy() {
        return _modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        _modifiedBy = modifiedBy;
    }

    public Date getIntfInsertedDate() {
        return _intfInsertedDate;
    }

    public void setIntfInsertedDate(Date intfInsertedDate) {
        _intfInsertedDate = intfInsertedDate;
    }

    public String getLotNo() {
        return _lotNo;
    }

    public void setLotNo(String lotNo) {
        _lotNo = lotNo;
    }

    public String getReprocessedFlag() {
        return _reprocessedFlag;
    }

    public void setReprocessedFlag(String reprocessedFlag) {
        _reprocessedFlag = reprocessedFlag;
    }

    public String getQuantity() {
        return _quantity;
    }

    public void setQuantity(String quantity) {
        _quantity = quantity;
    }

    public String getInvoiceLineNumber() {
        return _invoiceLineNumber;
    }

    public void setInvoiceLineNumber(String invoiceLineNumber) {
        _invoiceLineNumber = invoiceLineNumber;
    }

    public String getContractId() {
        return _contractId;
    }

    public void setContractId(String contractId) {
        _contractId = contractId;
    }

    public String getReasonForFailure() {
        return _reasonForFailure;
    }

    public void setReasonForFailure(String reasonForFailure) {
        _reasonForFailure = reasonForFailure;
    }

    public String getAmount() {
        return _amount;
    }

    public void setAmount(String amount) {
        _amount = amount;
    }

    public Date getInvoiceDate() {
        return _invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        _invoiceDate = invoiceDate;
    }

    public String getCustomerGtsActualIntfId() {
        return _customerGtsActualIntfId;
    }

    public void setCustomerGtsActualIntfId(String customerGtsActualIntfId) {
        _customerGtsActualIntfId = customerGtsActualIntfId;
    }

    public String getBatchId() {
        return _batchId;
    }

    public void setBatchId(String batchId) {
        _batchId = batchId;
    }

    public String getSalesId() {
        return _salesId;
    }

    public void setSalesId(String salesId) {
        _salesId = salesId;
    }

    public String getErrorField() {
        return _errorField;
    }

    public void setErrorField(String errorField) {
        _errorField = errorField;
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
