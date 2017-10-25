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
public class SalesMasterSoap implements Serializable {
    private String _itemNo;
    private int _recordSequence;
    private double _quantity;
    private String _accountId;
    private Date _createdDate;
    private String _identifierCodeQualifier;
    private String _isActive;
    private String _marketId;
    private Date _invoiceDate;
    private String _accountName;
    private String _docType;
    private Date _orderReceivedDate;
    private double _amount;
    private int _salesMasterSid;
    private String _orderSubtype;
    private int _createdBy;
    private double _price;
    private Date _uploadDate;
    private String _itemId;
    private String _priceAdjustmentName;
    private String _itemCodeQualifier;
    private String _contractId;
    private String _itemUom;
    private Date _modifiedDate;
    private String _customerSubtype;
    private int _provisionId;
    private String _wholesaleOwnerId;
    private String _source;
    private String _accountNo;
    private String _lotNo;
    private String _parentItemId;
    private String _customerCompanyCode;
    private String _analysisCode;
    private String _accountType;
    private int _modifiedBy;
    private String _contractNo;
    private String _batchId;
    private String _brandId;
    private String _salesId;
    private String _companyId;
    private String _organizationKey;
    private String _itemParentNo;
    private String _invoiceNumber;
    private String _orderType;
    private boolean _recordLockStatus;
    private String _divisionId;
    private String _invoiceLineNumber;
    private String _orderNumber;
    private String _inboundStatus;

    public SalesMasterSoap() {
    }

    public static SalesMasterSoap toSoapModel(SalesMaster model) {
        SalesMasterSoap soapModel = new SalesMasterSoap();

        soapModel.setItemNo(model.getItemNo());
        soapModel.setRecordSequence(model.getRecordSequence());
        soapModel.setQuantity(model.getQuantity());
        soapModel.setAccountId(model.getAccountId());
        soapModel.setCreatedDate(model.getCreatedDate());
        soapModel.setIdentifierCodeQualifier(model.getIdentifierCodeQualifier());
        soapModel.setIsActive(model.getIsActive());
        soapModel.setMarketId(model.getMarketId());
        soapModel.setInvoiceDate(model.getInvoiceDate());
        soapModel.setAccountName(model.getAccountName());
        soapModel.setDocType(model.getDocType());
        soapModel.setOrderReceivedDate(model.getOrderReceivedDate());
        soapModel.setAmount(model.getAmount());
        soapModel.setSalesMasterSid(model.getSalesMasterSid());
        soapModel.setOrderSubtype(model.getOrderSubtype());
        soapModel.setCreatedBy(model.getCreatedBy());
        soapModel.setPrice(model.getPrice());
        soapModel.setUploadDate(model.getUploadDate());
        soapModel.setItemId(model.getItemId());
        soapModel.setPriceAdjustmentName(model.getPriceAdjustmentName());
        soapModel.setItemCodeQualifier(model.getItemCodeQualifier());
        soapModel.setContractId(model.getContractId());
        soapModel.setItemUom(model.getItemUom());
        soapModel.setModifiedDate(model.getModifiedDate());
        soapModel.setCustomerSubtype(model.getCustomerSubtype());
        soapModel.setProvisionId(model.getProvisionId());
        soapModel.setWholesaleOwnerId(model.getWholesaleOwnerId());
        soapModel.setSource(model.getSource());
        soapModel.setAccountNo(model.getAccountNo());
        soapModel.setLotNo(model.getLotNo());
        soapModel.setParentItemId(model.getParentItemId());
        soapModel.setCustomerCompanyCode(model.getCustomerCompanyCode());
        soapModel.setAnalysisCode(model.getAnalysisCode());
        soapModel.setAccountType(model.getAccountType());
        soapModel.setModifiedBy(model.getModifiedBy());
        soapModel.setContractNo(model.getContractNo());
        soapModel.setBatchId(model.getBatchId());
        soapModel.setBrandId(model.getBrandId());
        soapModel.setSalesId(model.getSalesId());
        soapModel.setCompanyId(model.getCompanyId());
        soapModel.setOrganizationKey(model.getOrganizationKey());
        soapModel.setItemParentNo(model.getItemParentNo());
        soapModel.setInvoiceNumber(model.getInvoiceNumber());
        soapModel.setOrderType(model.getOrderType());
        soapModel.setRecordLockStatus(model.getRecordLockStatus());
        soapModel.setDivisionId(model.getDivisionId());
        soapModel.setInvoiceLineNumber(model.getInvoiceLineNumber());
        soapModel.setOrderNumber(model.getOrderNumber());
        soapModel.setInboundStatus(model.getInboundStatus());

        return soapModel;
    }

    public static SalesMasterSoap[] toSoapModels(SalesMaster[] models) {
        SalesMasterSoap[] soapModels = new SalesMasterSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static SalesMasterSoap[][] toSoapModels(SalesMaster[][] models) {
        SalesMasterSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new SalesMasterSoap[models.length][models[0].length];
        } else {
            soapModels = new SalesMasterSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static SalesMasterSoap[] toSoapModels(List<SalesMaster> models) {
        List<SalesMasterSoap> soapModels = new ArrayList<SalesMasterSoap>(models.size());

        for (SalesMaster model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new SalesMasterSoap[soapModels.size()]);
    }

    public int getPrimaryKey() {
        return _salesMasterSid;
    }

    public void setPrimaryKey(int pk) {
        setSalesMasterSid(pk);
    }

    public String getItemNo() {
        return _itemNo;
    }

    public void setItemNo(String itemNo) {
        _itemNo = itemNo;
    }

    public int getRecordSequence() {
        return _recordSequence;
    }

    public void setRecordSequence(int recordSequence) {
        _recordSequence = recordSequence;
    }

    public double getQuantity() {
        return _quantity;
    }

    public void setQuantity(double quantity) {
        _quantity = quantity;
    }

    public String getAccountId() {
        return _accountId;
    }

    public void setAccountId(String accountId) {
        _accountId = accountId;
    }

    public Date getCreatedDate() {
        return _createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        _createdDate = createdDate;
    }

    public String getIdentifierCodeQualifier() {
        return _identifierCodeQualifier;
    }

    public void setIdentifierCodeQualifier(String identifierCodeQualifier) {
        _identifierCodeQualifier = identifierCodeQualifier;
    }

    public String getIsActive() {
        return _isActive;
    }

    public void setIsActive(String isActive) {
        _isActive = isActive;
    }

    public String getMarketId() {
        return _marketId;
    }

    public void setMarketId(String marketId) {
        _marketId = marketId;
    }

    public Date getInvoiceDate() {
        return _invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        _invoiceDate = invoiceDate;
    }

    public String getAccountName() {
        return _accountName;
    }

    public void setAccountName(String accountName) {
        _accountName = accountName;
    }

    public String getDocType() {
        return _docType;
    }

    public void setDocType(String docType) {
        _docType = docType;
    }

    public Date getOrderReceivedDate() {
        return _orderReceivedDate;
    }

    public void setOrderReceivedDate(Date orderReceivedDate) {
        _orderReceivedDate = orderReceivedDate;
    }

    public double getAmount() {
        return _amount;
    }

    public void setAmount(double amount) {
        _amount = amount;
    }

    public int getSalesMasterSid() {
        return _salesMasterSid;
    }

    public void setSalesMasterSid(int salesMasterSid) {
        _salesMasterSid = salesMasterSid;
    }

    public String getOrderSubtype() {
        return _orderSubtype;
    }

    public void setOrderSubtype(String orderSubtype) {
        _orderSubtype = orderSubtype;
    }

    public int getCreatedBy() {
        return _createdBy;
    }

    public void setCreatedBy(int createdBy) {
        _createdBy = createdBy;
    }

    public double getPrice() {
        return _price;
    }

    public void setPrice(double price) {
        _price = price;
    }

    public Date getUploadDate() {
        return _uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        _uploadDate = uploadDate;
    }

    public String getItemId() {
        return _itemId;
    }

    public void setItemId(String itemId) {
        _itemId = itemId;
    }

    public String getPriceAdjustmentName() {
        return _priceAdjustmentName;
    }

    public void setPriceAdjustmentName(String priceAdjustmentName) {
        _priceAdjustmentName = priceAdjustmentName;
    }

    public String getItemCodeQualifier() {
        return _itemCodeQualifier;
    }

    public void setItemCodeQualifier(String itemCodeQualifier) {
        _itemCodeQualifier = itemCodeQualifier;
    }

    public String getContractId() {
        return _contractId;
    }

    public void setContractId(String contractId) {
        _contractId = contractId;
    }

    public String getItemUom() {
        return _itemUom;
    }

    public void setItemUom(String itemUom) {
        _itemUom = itemUom;
    }

    public Date getModifiedDate() {
        return _modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        _modifiedDate = modifiedDate;
    }

    public String getCustomerSubtype() {
        return _customerSubtype;
    }

    public void setCustomerSubtype(String customerSubtype) {
        _customerSubtype = customerSubtype;
    }

    public int getProvisionId() {
        return _provisionId;
    }

    public void setProvisionId(int provisionId) {
        _provisionId = provisionId;
    }

    public String getWholesaleOwnerId() {
        return _wholesaleOwnerId;
    }

    public void setWholesaleOwnerId(String wholesaleOwnerId) {
        _wholesaleOwnerId = wholesaleOwnerId;
    }

    public String getSource() {
        return _source;
    }

    public void setSource(String source) {
        _source = source;
    }

    public String getAccountNo() {
        return _accountNo;
    }

    public void setAccountNo(String accountNo) {
        _accountNo = accountNo;
    }

    public String getLotNo() {
        return _lotNo;
    }

    public void setLotNo(String lotNo) {
        _lotNo = lotNo;
    }

    public String getParentItemId() {
        return _parentItemId;
    }

    public void setParentItemId(String parentItemId) {
        _parentItemId = parentItemId;
    }

    public String getCustomerCompanyCode() {
        return _customerCompanyCode;
    }

    public void setCustomerCompanyCode(String customerCompanyCode) {
        _customerCompanyCode = customerCompanyCode;
    }

    public String getAnalysisCode() {
        return _analysisCode;
    }

    public void setAnalysisCode(String analysisCode) {
        _analysisCode = analysisCode;
    }

    public String getAccountType() {
        return _accountType;
    }

    public void setAccountType(String accountType) {
        _accountType = accountType;
    }

    public int getModifiedBy() {
        return _modifiedBy;
    }

    public void setModifiedBy(int modifiedBy) {
        _modifiedBy = modifiedBy;
    }

    public String getContractNo() {
        return _contractNo;
    }

    public void setContractNo(String contractNo) {
        _contractNo = contractNo;
    }

    public String getBatchId() {
        return _batchId;
    }

    public void setBatchId(String batchId) {
        _batchId = batchId;
    }

    public String getBrandId() {
        return _brandId;
    }

    public void setBrandId(String brandId) {
        _brandId = brandId;
    }

    public String getSalesId() {
        return _salesId;
    }

    public void setSalesId(String salesId) {
        _salesId = salesId;
    }

    public String getCompanyId() {
        return _companyId;
    }

    public void setCompanyId(String companyId) {
        _companyId = companyId;
    }

    public String getOrganizationKey() {
        return _organizationKey;
    }

    public void setOrganizationKey(String organizationKey) {
        _organizationKey = organizationKey;
    }

    public String getItemParentNo() {
        return _itemParentNo;
    }

    public void setItemParentNo(String itemParentNo) {
        _itemParentNo = itemParentNo;
    }

    public String getInvoiceNumber() {
        return _invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        _invoiceNumber = invoiceNumber;
    }

    public String getOrderType() {
        return _orderType;
    }

    public void setOrderType(String orderType) {
        _orderType = orderType;
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

    public String getDivisionId() {
        return _divisionId;
    }

    public void setDivisionId(String divisionId) {
        _divisionId = divisionId;
    }

    public String getInvoiceLineNumber() {
        return _invoiceLineNumber;
    }

    public void setInvoiceLineNumber(String invoiceLineNumber) {
        _invoiceLineNumber = invoiceLineNumber;
    }

    public String getOrderNumber() {
        return _orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        _orderNumber = orderNumber;
    }

    public String getInboundStatus() {
        return _inboundStatus;
    }

    public void setInboundStatus(String inboundStatus) {
        _inboundStatus = inboundStatus;
    }
}
