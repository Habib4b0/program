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
public class CustomerGtsActualSoap implements Serializable {
    private String _parentAccountId;
    private String _contractId;
    private String _accountId;
    private int _customerGtsActualSid;
    private Date _orderReceivedDate;
    private String _itemId;
    private Date _modifiedDate;
    private double _amount;
    private String _orderNumber;
    private String _organizationKey;
    private Date _invoiceDate;
    private int _customerGtsActualIntfId;
    private Date _createdDate;
    private String _createdBy;
    private String _source;
    private String _batchId;
    private String _salesId;
    private String _itemUom;
    private String _inboundStatus;
    private String _modifiedBy;
    private String _invoiceNumber;
    private String _lotNo;
    private String _invoiceLineNumber;
    private double _quantity;

    public CustomerGtsActualSoap() {
    }

    public static CustomerGtsActualSoap toSoapModel(CustomerGtsActual model) {
        CustomerGtsActualSoap soapModel = new CustomerGtsActualSoap();

        soapModel.setParentAccountId(model.getParentAccountId());
        soapModel.setContractId(model.getContractId());
        soapModel.setAccountId(model.getAccountId());
        soapModel.setCustomerGtsActualSid(model.getCustomerGtsActualSid());
        soapModel.setOrderReceivedDate(model.getOrderReceivedDate());
        soapModel.setItemId(model.getItemId());
        soapModel.setModifiedDate(model.getModifiedDate());
        soapModel.setAmount(model.getAmount());
        soapModel.setOrderNumber(model.getOrderNumber());
        soapModel.setOrganizationKey(model.getOrganizationKey());
        soapModel.setInvoiceDate(model.getInvoiceDate());
        soapModel.setCustomerGtsActualIntfId(model.getCustomerGtsActualIntfId());
        soapModel.setCreatedDate(model.getCreatedDate());
        soapModel.setCreatedBy(model.getCreatedBy());
        soapModel.setSource(model.getSource());
        soapModel.setBatchId(model.getBatchId());
        soapModel.setSalesId(model.getSalesId());
        soapModel.setItemUom(model.getItemUom());
        soapModel.setInboundStatus(model.getInboundStatus());
        soapModel.setModifiedBy(model.getModifiedBy());
        soapModel.setInvoiceNumber(model.getInvoiceNumber());
        soapModel.setLotNo(model.getLotNo());
        soapModel.setInvoiceLineNumber(model.getInvoiceLineNumber());
        soapModel.setQuantity(model.getQuantity());

        return soapModel;
    }

    public static CustomerGtsActualSoap[] toSoapModels(
        CustomerGtsActual[] models) {
        CustomerGtsActualSoap[] soapModels = new CustomerGtsActualSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static CustomerGtsActualSoap[][] toSoapModels(
        CustomerGtsActual[][] models) {
        CustomerGtsActualSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new CustomerGtsActualSoap[models.length][models[0].length];
        } else {
            soapModels = new CustomerGtsActualSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static CustomerGtsActualSoap[] toSoapModels(
        List<CustomerGtsActual> models) {
        List<CustomerGtsActualSoap> soapModels = new ArrayList<CustomerGtsActualSoap>(models.size());

        for (CustomerGtsActual model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new CustomerGtsActualSoap[soapModels.size()]);
    }

    public int getPrimaryKey() {
        return _customerGtsActualSid;
    }

    public void setPrimaryKey(int pk) {
        setCustomerGtsActualSid(pk);
    }

    public String getParentAccountId() {
        return _parentAccountId;
    }

    public void setParentAccountId(String parentAccountId) {
        _parentAccountId = parentAccountId;
    }

    public String getContractId() {
        return _contractId;
    }

    public void setContractId(String contractId) {
        _contractId = contractId;
    }

    public String getAccountId() {
        return _accountId;
    }

    public void setAccountId(String accountId) {
        _accountId = accountId;
    }

    public int getCustomerGtsActualSid() {
        return _customerGtsActualSid;
    }

    public void setCustomerGtsActualSid(int customerGtsActualSid) {
        _customerGtsActualSid = customerGtsActualSid;
    }

    public Date getOrderReceivedDate() {
        return _orderReceivedDate;
    }

    public void setOrderReceivedDate(Date orderReceivedDate) {
        _orderReceivedDate = orderReceivedDate;
    }

    public String getItemId() {
        return _itemId;
    }

    public void setItemId(String itemId) {
        _itemId = itemId;
    }

    public Date getModifiedDate() {
        return _modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        _modifiedDate = modifiedDate;
    }

    public double getAmount() {
        return _amount;
    }

    public void setAmount(double amount) {
        _amount = amount;
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

    public Date getInvoiceDate() {
        return _invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        _invoiceDate = invoiceDate;
    }

    public int getCustomerGtsActualIntfId() {
        return _customerGtsActualIntfId;
    }

    public void setCustomerGtsActualIntfId(int customerGtsActualIntfId) {
        _customerGtsActualIntfId = customerGtsActualIntfId;
    }

    public Date getCreatedDate() {
        return _createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        _createdDate = createdDate;
    }

    public String getCreatedBy() {
        return _createdBy;
    }

    public void setCreatedBy(String createdBy) {
        _createdBy = createdBy;
    }

    public String getSource() {
        return _source;
    }

    public void setSource(String source) {
        _source = source;
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

    public String getItemUom() {
        return _itemUom;
    }

    public void setItemUom(String itemUom) {
        _itemUom = itemUom;
    }

    public String getInboundStatus() {
        return _inboundStatus;
    }

    public void setInboundStatus(String inboundStatus) {
        _inboundStatus = inboundStatus;
    }

    public String getModifiedBy() {
        return _modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        _modifiedBy = modifiedBy;
    }

    public String getInvoiceNumber() {
        return _invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        _invoiceNumber = invoiceNumber;
    }

    public String getLotNo() {
        return _lotNo;
    }

    public void setLotNo(String lotNo) {
        _lotNo = lotNo;
    }

    public String getInvoiceLineNumber() {
        return _invoiceLineNumber;
    }

    public void setInvoiceLineNumber(String invoiceLineNumber) {
        _invoiceLineNumber = invoiceLineNumber;
    }

    public double getQuantity() {
        return _quantity;
    }

    public void setQuantity(double quantity) {
        _quantity = quantity;
    }
}
