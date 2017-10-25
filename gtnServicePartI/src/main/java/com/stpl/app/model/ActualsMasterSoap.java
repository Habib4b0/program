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
public class ActualsMasterSoap implements Serializable {
    private String _quantityInclusion;
    private double _mandatedDiscountAmount;
    private String _itemNo;
    private String _analysisCode;
    private String _recordSequence;
    private int _modifiedBy;
    private String _settlementMethodNo;
    private String _quantity;
    private String _accountId;
    private Date _createdDate;
    private String _provisionClaimIndicator;
    private Date _dispensedDate;
    private String _isActive;
    private String _batchId;
    private Date _accrualActualEndDate;
    private String _marketId;
    private String _brandId;
    private String _accountName;
    private String _amount;
    private int _actualsMasterSid;
    private String _acctIdentifierCodeQualifier;
    private String _organizationKey;
    private int _createdBy;
    private String _accrualProcessed;
    private String _parentcomDivmktBrandProdkey;
    private Date _cashPaidDate;
    private String _salesAmount;
    private Date _accrualActualStartDate;
    private String _settlementNo;
    private double _price;
    private Date _uploadDate;
    private String _claimIndicator;
    private String _itemId;
    private String _priceAdjustmentName;
    private String _contractId;
    private Date _modifiedDate;
    private String _actualId;
    private String _provisionId;
    private String _sentOut;
    private boolean _recordLockStatus;
    private String _divisionId;
    private String _itemIdentifierCodeQualifier;
    private String _programStateCode;
    private String _source;
    private String _invoiceLineNo;
    private String _accountNo;
    private String _comDivMktBrandProdKey;
    private String _inboundStatus;

    public ActualsMasterSoap() {
    }

    public static ActualsMasterSoap toSoapModel(ActualsMaster model) {
        ActualsMasterSoap soapModel = new ActualsMasterSoap();

        soapModel.setQuantityInclusion(model.getQuantityInclusion());
        soapModel.setMandatedDiscountAmount(model.getMandatedDiscountAmount());
        soapModel.setItemNo(model.getItemNo());
        soapModel.setAnalysisCode(model.getAnalysisCode());
        soapModel.setRecordSequence(model.getRecordSequence());
        soapModel.setModifiedBy(model.getModifiedBy());
        soapModel.setSettlementMethodNo(model.getSettlementMethodNo());
        soapModel.setQuantity(model.getQuantity());
        soapModel.setAccountId(model.getAccountId());
        soapModel.setCreatedDate(model.getCreatedDate());
        soapModel.setProvisionClaimIndicator(model.getProvisionClaimIndicator());
        soapModel.setDispensedDate(model.getDispensedDate());
        soapModel.setIsActive(model.getIsActive());
        soapModel.setBatchId(model.getBatchId());
        soapModel.setAccrualActualEndDate(model.getAccrualActualEndDate());
        soapModel.setMarketId(model.getMarketId());
        soapModel.setBrandId(model.getBrandId());
        soapModel.setAccountName(model.getAccountName());
        soapModel.setAmount(model.getAmount());
        soapModel.setActualsMasterSid(model.getActualsMasterSid());
        soapModel.setAcctIdentifierCodeQualifier(model.getAcctIdentifierCodeQualifier());
        soapModel.setOrganizationKey(model.getOrganizationKey());
        soapModel.setCreatedBy(model.getCreatedBy());
        soapModel.setAccrualProcessed(model.getAccrualProcessed());
        soapModel.setParentcomDivmktBrandProdkey(model.getParentcomDivmktBrandProdkey());
        soapModel.setCashPaidDate(model.getCashPaidDate());
        soapModel.setSalesAmount(model.getSalesAmount());
        soapModel.setAccrualActualStartDate(model.getAccrualActualStartDate());
        soapModel.setSettlementNo(model.getSettlementNo());
        soapModel.setPrice(model.getPrice());
        soapModel.setUploadDate(model.getUploadDate());
        soapModel.setClaimIndicator(model.getClaimIndicator());
        soapModel.setItemId(model.getItemId());
        soapModel.setPriceAdjustmentName(model.getPriceAdjustmentName());
        soapModel.setContractId(model.getContractId());
        soapModel.setModifiedDate(model.getModifiedDate());
        soapModel.setActualId(model.getActualId());
        soapModel.setProvisionId(model.getProvisionId());
        soapModel.setSentOut(model.getSentOut());
        soapModel.setRecordLockStatus(model.getRecordLockStatus());
        soapModel.setDivisionId(model.getDivisionId());
        soapModel.setItemIdentifierCodeQualifier(model.getItemIdentifierCodeQualifier());
        soapModel.setProgramStateCode(model.getProgramStateCode());
        soapModel.setSource(model.getSource());
        soapModel.setInvoiceLineNo(model.getInvoiceLineNo());
        soapModel.setAccountNo(model.getAccountNo());
        soapModel.setComDivMktBrandProdKey(model.getComDivMktBrandProdKey());
        soapModel.setInboundStatus(model.getInboundStatus());

        return soapModel;
    }

    public static ActualsMasterSoap[] toSoapModels(ActualsMaster[] models) {
        ActualsMasterSoap[] soapModels = new ActualsMasterSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static ActualsMasterSoap[][] toSoapModels(ActualsMaster[][] models) {
        ActualsMasterSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new ActualsMasterSoap[models.length][models[0].length];
        } else {
            soapModels = new ActualsMasterSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static ActualsMasterSoap[] toSoapModels(List<ActualsMaster> models) {
        List<ActualsMasterSoap> soapModels = new ArrayList<ActualsMasterSoap>(models.size());

        for (ActualsMaster model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new ActualsMasterSoap[soapModels.size()]);
    }

    public int getPrimaryKey() {
        return _actualsMasterSid;
    }

    public void setPrimaryKey(int pk) {
        setActualsMasterSid(pk);
    }

    public String getQuantityInclusion() {
        return _quantityInclusion;
    }

    public void setQuantityInclusion(String quantityInclusion) {
        _quantityInclusion = quantityInclusion;
    }

    public double getMandatedDiscountAmount() {
        return _mandatedDiscountAmount;
    }

    public void setMandatedDiscountAmount(double mandatedDiscountAmount) {
        _mandatedDiscountAmount = mandatedDiscountAmount;
    }

    public String getItemNo() {
        return _itemNo;
    }

    public void setItemNo(String itemNo) {
        _itemNo = itemNo;
    }

    public String getAnalysisCode() {
        return _analysisCode;
    }

    public void setAnalysisCode(String analysisCode) {
        _analysisCode = analysisCode;
    }

    public String getRecordSequence() {
        return _recordSequence;
    }

    public void setRecordSequence(String recordSequence) {
        _recordSequence = recordSequence;
    }

    public int getModifiedBy() {
        return _modifiedBy;
    }

    public void setModifiedBy(int modifiedBy) {
        _modifiedBy = modifiedBy;
    }

    public String getSettlementMethodNo() {
        return _settlementMethodNo;
    }

    public void setSettlementMethodNo(String settlementMethodNo) {
        _settlementMethodNo = settlementMethodNo;
    }

    public String getQuantity() {
        return _quantity;
    }

    public void setQuantity(String quantity) {
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

    public String getProvisionClaimIndicator() {
        return _provisionClaimIndicator;
    }

    public void setProvisionClaimIndicator(String provisionClaimIndicator) {
        _provisionClaimIndicator = provisionClaimIndicator;
    }

    public Date getDispensedDate() {
        return _dispensedDate;
    }

    public void setDispensedDate(Date dispensedDate) {
        _dispensedDate = dispensedDate;
    }

    public String getIsActive() {
        return _isActive;
    }

    public void setIsActive(String isActive) {
        _isActive = isActive;
    }

    public String getBatchId() {
        return _batchId;
    }

    public void setBatchId(String batchId) {
        _batchId = batchId;
    }

    public Date getAccrualActualEndDate() {
        return _accrualActualEndDate;
    }

    public void setAccrualActualEndDate(Date accrualActualEndDate) {
        _accrualActualEndDate = accrualActualEndDate;
    }

    public String getMarketId() {
        return _marketId;
    }

    public void setMarketId(String marketId) {
        _marketId = marketId;
    }

    public String getBrandId() {
        return _brandId;
    }

    public void setBrandId(String brandId) {
        _brandId = brandId;
    }

    public String getAccountName() {
        return _accountName;
    }

    public void setAccountName(String accountName) {
        _accountName = accountName;
    }

    public String getAmount() {
        return _amount;
    }

    public void setAmount(String amount) {
        _amount = amount;
    }

    public int getActualsMasterSid() {
        return _actualsMasterSid;
    }

    public void setActualsMasterSid(int actualsMasterSid) {
        _actualsMasterSid = actualsMasterSid;
    }

    public String getAcctIdentifierCodeQualifier() {
        return _acctIdentifierCodeQualifier;
    }

    public void setAcctIdentifierCodeQualifier(
        String acctIdentifierCodeQualifier) {
        _acctIdentifierCodeQualifier = acctIdentifierCodeQualifier;
    }

    public String getOrganizationKey() {
        return _organizationKey;
    }

    public void setOrganizationKey(String organizationKey) {
        _organizationKey = organizationKey;
    }

    public int getCreatedBy() {
        return _createdBy;
    }

    public void setCreatedBy(int createdBy) {
        _createdBy = createdBy;
    }

    public String getAccrualProcessed() {
        return _accrualProcessed;
    }

    public void setAccrualProcessed(String accrualProcessed) {
        _accrualProcessed = accrualProcessed;
    }

    public String getParentcomDivmktBrandProdkey() {
        return _parentcomDivmktBrandProdkey;
    }

    public void setParentcomDivmktBrandProdkey(
        String parentcomDivmktBrandProdkey) {
        _parentcomDivmktBrandProdkey = parentcomDivmktBrandProdkey;
    }

    public Date getCashPaidDate() {
        return _cashPaidDate;
    }

    public void setCashPaidDate(Date cashPaidDate) {
        _cashPaidDate = cashPaidDate;
    }

    public String getSalesAmount() {
        return _salesAmount;
    }

    public void setSalesAmount(String salesAmount) {
        _salesAmount = salesAmount;
    }

    public Date getAccrualActualStartDate() {
        return _accrualActualStartDate;
    }

    public void setAccrualActualStartDate(Date accrualActualStartDate) {
        _accrualActualStartDate = accrualActualStartDate;
    }

    public String getSettlementNo() {
        return _settlementNo;
    }

    public void setSettlementNo(String settlementNo) {
        _settlementNo = settlementNo;
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

    public String getClaimIndicator() {
        return _claimIndicator;
    }

    public void setClaimIndicator(String claimIndicator) {
        _claimIndicator = claimIndicator;
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

    public String getContractId() {
        return _contractId;
    }

    public void setContractId(String contractId) {
        _contractId = contractId;
    }

    public Date getModifiedDate() {
        return _modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        _modifiedDate = modifiedDate;
    }

    public String getActualId() {
        return _actualId;
    }

    public void setActualId(String actualId) {
        _actualId = actualId;
    }

    public String getProvisionId() {
        return _provisionId;
    }

    public void setProvisionId(String provisionId) {
        _provisionId = provisionId;
    }

    public String getSentOut() {
        return _sentOut;
    }

    public void setSentOut(String sentOut) {
        _sentOut = sentOut;
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

    public String getItemIdentifierCodeQualifier() {
        return _itemIdentifierCodeQualifier;
    }

    public void setItemIdentifierCodeQualifier(
        String itemIdentifierCodeQualifier) {
        _itemIdentifierCodeQualifier = itemIdentifierCodeQualifier;
    }

    public String getProgramStateCode() {
        return _programStateCode;
    }

    public void setProgramStateCode(String programStateCode) {
        _programStateCode = programStateCode;
    }

    public String getSource() {
        return _source;
    }

    public void setSource(String source) {
        _source = source;
    }

    public String getInvoiceLineNo() {
        return _invoiceLineNo;
    }

    public void setInvoiceLineNo(String invoiceLineNo) {
        _invoiceLineNo = invoiceLineNo;
    }

    public String getAccountNo() {
        return _accountNo;
    }

    public void setAccountNo(String accountNo) {
        _accountNo = accountNo;
    }

    public String getComDivMktBrandProdKey() {
        return _comDivMktBrandProdKey;
    }

    public void setComDivMktBrandProdKey(String comDivMktBrandProdKey) {
        _comDivMktBrandProdKey = comDivMktBrandProdKey;
    }

    public String getInboundStatus() {
        return _inboundStatus;
    }

    public void setInboundStatus(String inboundStatus) {
        _inboundStatus = inboundStatus;
    }
}
