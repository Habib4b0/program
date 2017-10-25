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
public class IvldSalesMasterSoap implements Serializable {
    private String _accountId;
    private String _salesIntfid;
    private Date _modifiedDate;
    private String _organizationKey;
    private String _divisionId;
    private String _source;
    private String _addChgDelIndicator;
    private String _analysisCode;
    private int _ivldSalesMasterSid;
    private String _docType;
    private String _modifiedBy;
    private String _lotNo;
    private String _quantity;
    private String _invoiceLineNumber;
    private String _identifierCodeQualifier;
    private String _accountCodeQualifier;
    private String _parentItemId;
    private String _accountNo;
    private String _reasonForFailure;
    private String _accountName;
    private String _provisionId;
    private String _amount;
    private String _marketId;
    private String _isActive;
    private String _wholesaleOwnerId;
    private String _priceAdjustmentName;
    private String _salesId;
    private String _errorField;
    private String _recordSequence;
    private String _price;
    private String _customerSubtype;
    private String _parentItemNo;
    private String _itemId;
    private String _orderReceivedDate;
    private String _orderNumber;
    private String _accountType;
    private String _uploadDate;
    private String _createdBy;
    private Date _createdDate;
    private String _errorCode;
    private String _itemUom;
    private Date _intfInsertedDate;
    private String _invoiceNumber;
    private String _orderSubtype;
    private String _itemNo;
    private String _reprocessedFlag;
    private String _contractId;
    private String _customerCompanyCode;
    private String _orderType;
    private String _companyId;
    private String _brandId;
    private String _invoiceDate;
    private String _batchId;
    private String _contractNo;
    private boolean _checkRecord;

    public IvldSalesMasterSoap() {
    }

    public static IvldSalesMasterSoap toSoapModel(IvldSalesMaster model) {
        IvldSalesMasterSoap soapModel = new IvldSalesMasterSoap();

        soapModel.setAccountId(model.getAccountId());
        soapModel.setSalesIntfid(model.getSalesIntfid());
        soapModel.setModifiedDate(model.getModifiedDate());
        soapModel.setOrganizationKey(model.getOrganizationKey());
        soapModel.setDivisionId(model.getDivisionId());
        soapModel.setSource(model.getSource());
        soapModel.setAddChgDelIndicator(model.getAddChgDelIndicator());
        soapModel.setAnalysisCode(model.getAnalysisCode());
        soapModel.setIvldSalesMasterSid(model.getIvldSalesMasterSid());
        soapModel.setDocType(model.getDocType());
        soapModel.setModifiedBy(model.getModifiedBy());
        soapModel.setLotNo(model.getLotNo());
        soapModel.setQuantity(model.getQuantity());
        soapModel.setInvoiceLineNumber(model.getInvoiceLineNumber());
        soapModel.setIdentifierCodeQualifier(model.getIdentifierCodeQualifier());
        soapModel.setAccountCodeQualifier(model.getAccountCodeQualifier());
        soapModel.setParentItemId(model.getParentItemId());
        soapModel.setAccountNo(model.getAccountNo());
        soapModel.setReasonForFailure(model.getReasonForFailure());
        soapModel.setAccountName(model.getAccountName());
        soapModel.setProvisionId(model.getProvisionId());
        soapModel.setAmount(model.getAmount());
        soapModel.setMarketId(model.getMarketId());
        soapModel.setIsActive(model.getIsActive());
        soapModel.setWholesaleOwnerId(model.getWholesaleOwnerId());
        soapModel.setPriceAdjustmentName(model.getPriceAdjustmentName());
        soapModel.setSalesId(model.getSalesId());
        soapModel.setErrorField(model.getErrorField());
        soapModel.setRecordSequence(model.getRecordSequence());
        soapModel.setPrice(model.getPrice());
        soapModel.setCustomerSubtype(model.getCustomerSubtype());
        soapModel.setParentItemNo(model.getParentItemNo());
        soapModel.setItemId(model.getItemId());
        soapModel.setOrderReceivedDate(model.getOrderReceivedDate());
        soapModel.setOrderNumber(model.getOrderNumber());
        soapModel.setAccountType(model.getAccountType());
        soapModel.setUploadDate(model.getUploadDate());
        soapModel.setCreatedBy(model.getCreatedBy());
        soapModel.setCreatedDate(model.getCreatedDate());
        soapModel.setErrorCode(model.getErrorCode());
        soapModel.setItemUom(model.getItemUom());
        soapModel.setIntfInsertedDate(model.getIntfInsertedDate());
        soapModel.setInvoiceNumber(model.getInvoiceNumber());
        soapModel.setOrderSubtype(model.getOrderSubtype());
        soapModel.setItemNo(model.getItemNo());
        soapModel.setReprocessedFlag(model.getReprocessedFlag());
        soapModel.setContractId(model.getContractId());
        soapModel.setCustomerCompanyCode(model.getCustomerCompanyCode());
        soapModel.setOrderType(model.getOrderType());
        soapModel.setCompanyId(model.getCompanyId());
        soapModel.setBrandId(model.getBrandId());
        soapModel.setInvoiceDate(model.getInvoiceDate());
        soapModel.setBatchId(model.getBatchId());
        soapModel.setContractNo(model.getContractNo());
        soapModel.setCheckRecord(model.getCheckRecord());

        return soapModel;
    }

    public static IvldSalesMasterSoap[] toSoapModels(IvldSalesMaster[] models) {
        IvldSalesMasterSoap[] soapModels = new IvldSalesMasterSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static IvldSalesMasterSoap[][] toSoapModels(
        IvldSalesMaster[][] models) {
        IvldSalesMasterSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new IvldSalesMasterSoap[models.length][models[0].length];
        } else {
            soapModels = new IvldSalesMasterSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static IvldSalesMasterSoap[] toSoapModels(
        List<IvldSalesMaster> models) {
        List<IvldSalesMasterSoap> soapModels = new ArrayList<IvldSalesMasterSoap>(models.size());

        for (IvldSalesMaster model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new IvldSalesMasterSoap[soapModels.size()]);
    }

    public int getPrimaryKey() {
        return _ivldSalesMasterSid;
    }

    public void setPrimaryKey(int pk) {
        setIvldSalesMasterSid(pk);
    }

    public String getAccountId() {
        return _accountId;
    }

    public void setAccountId(String accountId) {
        _accountId = accountId;
    }

    public String getSalesIntfid() {
        return _salesIntfid;
    }

    public void setSalesIntfid(String salesIntfid) {
        _salesIntfid = salesIntfid;
    }

    public Date getModifiedDate() {
        return _modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        _modifiedDate = modifiedDate;
    }

    public String getOrganizationKey() {
        return _organizationKey;
    }

    public void setOrganizationKey(String organizationKey) {
        _organizationKey = organizationKey;
    }

    public String getDivisionId() {
        return _divisionId;
    }

    public void setDivisionId(String divisionId) {
        _divisionId = divisionId;
    }

    public String getSource() {
        return _source;
    }

    public void setSource(String source) {
        _source = source;
    }

    public String getAddChgDelIndicator() {
        return _addChgDelIndicator;
    }

    public void setAddChgDelIndicator(String addChgDelIndicator) {
        _addChgDelIndicator = addChgDelIndicator;
    }

    public String getAnalysisCode() {
        return _analysisCode;
    }

    public void setAnalysisCode(String analysisCode) {
        _analysisCode = analysisCode;
    }

    public int getIvldSalesMasterSid() {
        return _ivldSalesMasterSid;
    }

    public void setIvldSalesMasterSid(int ivldSalesMasterSid) {
        _ivldSalesMasterSid = ivldSalesMasterSid;
    }

    public String getDocType() {
        return _docType;
    }

    public void setDocType(String docType) {
        _docType = docType;
    }

    public String getModifiedBy() {
        return _modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        _modifiedBy = modifiedBy;
    }

    public String getLotNo() {
        return _lotNo;
    }

    public void setLotNo(String lotNo) {
        _lotNo = lotNo;
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

    public String getIdentifierCodeQualifier() {
        return _identifierCodeQualifier;
    }

    public void setIdentifierCodeQualifier(String identifierCodeQualifier) {
        _identifierCodeQualifier = identifierCodeQualifier;
    }

    public String getAccountCodeQualifier() {
        return _accountCodeQualifier;
    }

    public void setAccountCodeQualifier(String accountCodeQualifier) {
        _accountCodeQualifier = accountCodeQualifier;
    }

    public String getParentItemId() {
        return _parentItemId;
    }

    public void setParentItemId(String parentItemId) {
        _parentItemId = parentItemId;
    }

    public String getAccountNo() {
        return _accountNo;
    }

    public void setAccountNo(String accountNo) {
        _accountNo = accountNo;
    }

    public String getReasonForFailure() {
        return _reasonForFailure;
    }

    public void setReasonForFailure(String reasonForFailure) {
        _reasonForFailure = reasonForFailure;
    }

    public String getAccountName() {
        return _accountName;
    }

    public void setAccountName(String accountName) {
        _accountName = accountName;
    }

    public String getProvisionId() {
        return _provisionId;
    }

    public void setProvisionId(String provisionId) {
        _provisionId = provisionId;
    }

    public String getAmount() {
        return _amount;
    }

    public void setAmount(String amount) {
        _amount = amount;
    }

    public String getMarketId() {
        return _marketId;
    }

    public void setMarketId(String marketId) {
        _marketId = marketId;
    }

    public String getIsActive() {
        return _isActive;
    }

    public void setIsActive(String isActive) {
        _isActive = isActive;
    }

    public String getWholesaleOwnerId() {
        return _wholesaleOwnerId;
    }

    public void setWholesaleOwnerId(String wholesaleOwnerId) {
        _wholesaleOwnerId = wholesaleOwnerId;
    }

    public String getPriceAdjustmentName() {
        return _priceAdjustmentName;
    }

    public void setPriceAdjustmentName(String priceAdjustmentName) {
        _priceAdjustmentName = priceAdjustmentName;
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

    public String getRecordSequence() {
        return _recordSequence;
    }

    public void setRecordSequence(String recordSequence) {
        _recordSequence = recordSequence;
    }

    public String getPrice() {
        return _price;
    }

    public void setPrice(String price) {
        _price = price;
    }

    public String getCustomerSubtype() {
        return _customerSubtype;
    }

    public void setCustomerSubtype(String customerSubtype) {
        _customerSubtype = customerSubtype;
    }

    public String getParentItemNo() {
        return _parentItemNo;
    }

    public void setParentItemNo(String parentItemNo) {
        _parentItemNo = parentItemNo;
    }

    public String getItemId() {
        return _itemId;
    }

    public void setItemId(String itemId) {
        _itemId = itemId;
    }

    public String getOrderReceivedDate() {
        return _orderReceivedDate;
    }

    public void setOrderReceivedDate(String orderReceivedDate) {
        _orderReceivedDate = orderReceivedDate;
    }

    public String getOrderNumber() {
        return _orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        _orderNumber = orderNumber;
    }

    public String getAccountType() {
        return _accountType;
    }

    public void setAccountType(String accountType) {
        _accountType = accountType;
    }

    public String getUploadDate() {
        return _uploadDate;
    }

    public void setUploadDate(String uploadDate) {
        _uploadDate = uploadDate;
    }

    public String getCreatedBy() {
        return _createdBy;
    }

    public void setCreatedBy(String createdBy) {
        _createdBy = createdBy;
    }

    public Date getCreatedDate() {
        return _createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        _createdDate = createdDate;
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

    public Date getIntfInsertedDate() {
        return _intfInsertedDate;
    }

    public void setIntfInsertedDate(Date intfInsertedDate) {
        _intfInsertedDate = intfInsertedDate;
    }

    public String getInvoiceNumber() {
        return _invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        _invoiceNumber = invoiceNumber;
    }

    public String getOrderSubtype() {
        return _orderSubtype;
    }

    public void setOrderSubtype(String orderSubtype) {
        _orderSubtype = orderSubtype;
    }

    public String getItemNo() {
        return _itemNo;
    }

    public void setItemNo(String itemNo) {
        _itemNo = itemNo;
    }

    public String getReprocessedFlag() {
        return _reprocessedFlag;
    }

    public void setReprocessedFlag(String reprocessedFlag) {
        _reprocessedFlag = reprocessedFlag;
    }

    public String getContractId() {
        return _contractId;
    }

    public void setContractId(String contractId) {
        _contractId = contractId;
    }

    public String getCustomerCompanyCode() {
        return _customerCompanyCode;
    }

    public void setCustomerCompanyCode(String customerCompanyCode) {
        _customerCompanyCode = customerCompanyCode;
    }

    public String getOrderType() {
        return _orderType;
    }

    public void setOrderType(String orderType) {
        _orderType = orderType;
    }

    public String getCompanyId() {
        return _companyId;
    }

    public void setCompanyId(String companyId) {
        _companyId = companyId;
    }

    public String getBrandId() {
        return _brandId;
    }

    public void setBrandId(String brandId) {
        _brandId = brandId;
    }

    public String getInvoiceDate() {
        return _invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        _invoiceDate = invoiceDate;
    }

    public String getBatchId() {
        return _batchId;
    }

    public void setBatchId(String batchId) {
        _batchId = batchId;
    }

    public String getContractNo() {
        return _contractNo;
    }

    public void setContractNo(String contractNo) {
        _contractNo = contractNo;
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
