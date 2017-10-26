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
public class IvldActualMasterSoap implements Serializable {
    private String _actualIntfid;
    private String _accountId;
    private String _programStateCode;
    private String _settlementNo;
    private String _accrualActualEndDate;
    private String _actualId;
    private Date _modifiedDate;
    private String _divisionId;
    private String _organizationKey;
    private String _quantityInclusion;
    private String _cashPaidDate;
    private String _source;
    private String _addChgDelIndicator;
    private String _analysisCode;
    private String _accrualActualStartDate;
    private String _modifiedBy;
    private String _salesAmount;
    private String _quantity;
    private String _sentOut;
    private String _accountNo;
    private String _reasonForFailure;
    private String _accountName;
    private String _provisionId;
    private String _amount;
    private String _marketId;
    private String _isActive;
    private String _settlementMethodNo;
    private String _itemIdentifierCodeQualifier;
    private String _priceAdjustmentName;
    private String _errorField;
    private String _recordSequence;
    private String _mandatedDiscountAmount;
    private String _parentcomDivmktBrandProdkey;
    private String _price;
    private String _dispensedDate;
    private String _itemId;
    private String _accrualProcessed;
    private String _uploadDate;
    private String _createdBy;
    private Date _createdDate;
    private String _invoiceLineNo;
    private String _errorCode;
    private Date _intfInsertedDate;
    private String _itemNo;
    private String _reprocessedFlag;
    private String _acctIdentifierCodeQualifier;
    private String _contractId;
    private String _brandId;
    private String _comDivMktBrandProdKey;
    private String _claimIndicator;
    private int _ivldActualMasterSid;
    private String _batchId;
    private String _provisionClaimIndicator;
    private boolean _checkRecord;

    public IvldActualMasterSoap() {
    }

    public static IvldActualMasterSoap toSoapModel(IvldActualMaster model) {
        IvldActualMasterSoap soapModel = new IvldActualMasterSoap();

        soapModel.setActualIntfid(model.getActualIntfid());
        soapModel.setAccountId(model.getAccountId());
        soapModel.setProgramStateCode(model.getProgramStateCode());
        soapModel.setSettlementNo(model.getSettlementNo());
        soapModel.setAccrualActualEndDate(model.getAccrualActualEndDate());
        soapModel.setActualId(model.getActualId());
        soapModel.setModifiedDate(model.getModifiedDate());
        soapModel.setDivisionId(model.getDivisionId());
        soapModel.setOrganizationKey(model.getOrganizationKey());
        soapModel.setQuantityInclusion(model.getQuantityInclusion());
        soapModel.setCashPaidDate(model.getCashPaidDate());
        soapModel.setSource(model.getSource());
        soapModel.setAddChgDelIndicator(model.getAddChgDelIndicator());
        soapModel.setAnalysisCode(model.getAnalysisCode());
        soapModel.setAccrualActualStartDate(model.getAccrualActualStartDate());
        soapModel.setModifiedBy(model.getModifiedBy());
        soapModel.setSalesAmount(model.getSalesAmount());
        soapModel.setQuantity(model.getQuantity());
        soapModel.setSentOut(model.getSentOut());
        soapModel.setAccountNo(model.getAccountNo());
        soapModel.setReasonForFailure(model.getReasonForFailure());
        soapModel.setAccountName(model.getAccountName());
        soapModel.setProvisionId(model.getProvisionId());
        soapModel.setAmount(model.getAmount());
        soapModel.setMarketId(model.getMarketId());
        soapModel.setIsActive(model.getIsActive());
        soapModel.setSettlementMethodNo(model.getSettlementMethodNo());
        soapModel.setItemIdentifierCodeQualifier(model.getItemIdentifierCodeQualifier());
        soapModel.setPriceAdjustmentName(model.getPriceAdjustmentName());
        soapModel.setErrorField(model.getErrorField());
        soapModel.setRecordSequence(model.getRecordSequence());
        soapModel.setMandatedDiscountAmount(model.getMandatedDiscountAmount());
        soapModel.setParentcomDivmktBrandProdkey(model.getParentcomDivmktBrandProdkey());
        soapModel.setPrice(model.getPrice());
        soapModel.setDispensedDate(model.getDispensedDate());
        soapModel.setItemId(model.getItemId());
        soapModel.setAccrualProcessed(model.getAccrualProcessed());
        soapModel.setUploadDate(model.getUploadDate());
        soapModel.setCreatedBy(model.getCreatedBy());
        soapModel.setCreatedDate(model.getCreatedDate());
        soapModel.setInvoiceLineNo(model.getInvoiceLineNo());
        soapModel.setErrorCode(model.getErrorCode());
        soapModel.setIntfInsertedDate(model.getIntfInsertedDate());
        soapModel.setItemNo(model.getItemNo());
        soapModel.setReprocessedFlag(model.getReprocessedFlag());
        soapModel.setAcctIdentifierCodeQualifier(model.getAcctIdentifierCodeQualifier());
        soapModel.setContractId(model.getContractId());
        soapModel.setBrandId(model.getBrandId());
        soapModel.setComDivMktBrandProdKey(model.getComDivMktBrandProdKey());
        soapModel.setClaimIndicator(model.getClaimIndicator());
        soapModel.setIvldActualMasterSid(model.getIvldActualMasterSid());
        soapModel.setBatchId(model.getBatchId());
        soapModel.setProvisionClaimIndicator(model.getProvisionClaimIndicator());
        soapModel.setCheckRecord(model.getCheckRecord());

        return soapModel;
    }

    public static IvldActualMasterSoap[] toSoapModels(IvldActualMaster[] models) {
        IvldActualMasterSoap[] soapModels = new IvldActualMasterSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static IvldActualMasterSoap[][] toSoapModels(
        IvldActualMaster[][] models) {
        IvldActualMasterSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new IvldActualMasterSoap[models.length][models[0].length];
        } else {
            soapModels = new IvldActualMasterSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static IvldActualMasterSoap[] toSoapModels(
        List<IvldActualMaster> models) {
        List<IvldActualMasterSoap> soapModels = new ArrayList<IvldActualMasterSoap>(models.size());

        for (IvldActualMaster model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new IvldActualMasterSoap[soapModels.size()]);
    }

    public int getPrimaryKey() {
        return _ivldActualMasterSid;
    }

    public void setPrimaryKey(int pk) {
        setIvldActualMasterSid(pk);
    }

    public String getActualIntfid() {
        return _actualIntfid;
    }

    public void setActualIntfid(String actualIntfid) {
        _actualIntfid = actualIntfid;
    }

    public String getAccountId() {
        return _accountId;
    }

    public void setAccountId(String accountId) {
        _accountId = accountId;
    }

    public String getProgramStateCode() {
        return _programStateCode;
    }

    public void setProgramStateCode(String programStateCode) {
        _programStateCode = programStateCode;
    }

    public String getSettlementNo() {
        return _settlementNo;
    }

    public void setSettlementNo(String settlementNo) {
        _settlementNo = settlementNo;
    }

    public String getAccrualActualEndDate() {
        return _accrualActualEndDate;
    }

    public void setAccrualActualEndDate(String accrualActualEndDate) {
        _accrualActualEndDate = accrualActualEndDate;
    }

    public String getActualId() {
        return _actualId;
    }

    public void setActualId(String actualId) {
        _actualId = actualId;
    }

    public Date getModifiedDate() {
        return _modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        _modifiedDate = modifiedDate;
    }

    public String getDivisionId() {
        return _divisionId;
    }

    public void setDivisionId(String divisionId) {
        _divisionId = divisionId;
    }

    public String getOrganizationKey() {
        return _organizationKey;
    }

    public void setOrganizationKey(String organizationKey) {
        _organizationKey = organizationKey;
    }

    public String getQuantityInclusion() {
        return _quantityInclusion;
    }

    public void setQuantityInclusion(String quantityInclusion) {
        _quantityInclusion = quantityInclusion;
    }

    public String getCashPaidDate() {
        return _cashPaidDate;
    }

    public void setCashPaidDate(String cashPaidDate) {
        _cashPaidDate = cashPaidDate;
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

    public String getAccrualActualStartDate() {
        return _accrualActualStartDate;
    }

    public void setAccrualActualStartDate(String accrualActualStartDate) {
        _accrualActualStartDate = accrualActualStartDate;
    }

    public String getModifiedBy() {
        return _modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        _modifiedBy = modifiedBy;
    }

    public String getSalesAmount() {
        return _salesAmount;
    }

    public void setSalesAmount(String salesAmount) {
        _salesAmount = salesAmount;
    }

    public String getQuantity() {
        return _quantity;
    }

    public void setQuantity(String quantity) {
        _quantity = quantity;
    }

    public String getSentOut() {
        return _sentOut;
    }

    public void setSentOut(String sentOut) {
        _sentOut = sentOut;
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

    public String getSettlementMethodNo() {
        return _settlementMethodNo;
    }

    public void setSettlementMethodNo(String settlementMethodNo) {
        _settlementMethodNo = settlementMethodNo;
    }

    public String getItemIdentifierCodeQualifier() {
        return _itemIdentifierCodeQualifier;
    }

    public void setItemIdentifierCodeQualifier(
        String itemIdentifierCodeQualifier) {
        _itemIdentifierCodeQualifier = itemIdentifierCodeQualifier;
    }

    public String getPriceAdjustmentName() {
        return _priceAdjustmentName;
    }

    public void setPriceAdjustmentName(String priceAdjustmentName) {
        _priceAdjustmentName = priceAdjustmentName;
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

    public String getMandatedDiscountAmount() {
        return _mandatedDiscountAmount;
    }

    public void setMandatedDiscountAmount(String mandatedDiscountAmount) {
        _mandatedDiscountAmount = mandatedDiscountAmount;
    }

    public String getParentcomDivmktBrandProdkey() {
        return _parentcomDivmktBrandProdkey;
    }

    public void setParentcomDivmktBrandProdkey(
        String parentcomDivmktBrandProdkey) {
        _parentcomDivmktBrandProdkey = parentcomDivmktBrandProdkey;
    }

    public String getPrice() {
        return _price;
    }

    public void setPrice(String price) {
        _price = price;
    }

    public String getDispensedDate() {
        return _dispensedDate;
    }

    public void setDispensedDate(String dispensedDate) {
        _dispensedDate = dispensedDate;
    }

    public String getItemId() {
        return _itemId;
    }

    public void setItemId(String itemId) {
        _itemId = itemId;
    }

    public String getAccrualProcessed() {
        return _accrualProcessed;
    }

    public void setAccrualProcessed(String accrualProcessed) {
        _accrualProcessed = accrualProcessed;
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

    public String getInvoiceLineNo() {
        return _invoiceLineNo;
    }

    public void setInvoiceLineNo(String invoiceLineNo) {
        _invoiceLineNo = invoiceLineNo;
    }

    public String getErrorCode() {
        return _errorCode;
    }

    public void setErrorCode(String errorCode) {
        _errorCode = errorCode;
    }

    public Date getIntfInsertedDate() {
        return _intfInsertedDate;
    }

    public void setIntfInsertedDate(Date intfInsertedDate) {
        _intfInsertedDate = intfInsertedDate;
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

    public String getAcctIdentifierCodeQualifier() {
        return _acctIdentifierCodeQualifier;
    }

    public void setAcctIdentifierCodeQualifier(
        String acctIdentifierCodeQualifier) {
        _acctIdentifierCodeQualifier = acctIdentifierCodeQualifier;
    }

    public String getContractId() {
        return _contractId;
    }

    public void setContractId(String contractId) {
        _contractId = contractId;
    }

    public String getBrandId() {
        return _brandId;
    }

    public void setBrandId(String brandId) {
        _brandId = brandId;
    }

    public String getComDivMktBrandProdKey() {
        return _comDivMktBrandProdKey;
    }

    public void setComDivMktBrandProdKey(String comDivMktBrandProdKey) {
        _comDivMktBrandProdKey = comDivMktBrandProdKey;
    }

    public String getClaimIndicator() {
        return _claimIndicator;
    }

    public void setClaimIndicator(String claimIndicator) {
        _claimIndicator = claimIndicator;
    }

    public int getIvldActualMasterSid() {
        return _ivldActualMasterSid;
    }

    public void setIvldActualMasterSid(int ivldActualMasterSid) {
        _ivldActualMasterSid = ivldActualMasterSid;
    }

    public String getBatchId() {
        return _batchId;
    }

    public void setBatchId(String batchId) {
        _batchId = batchId;
    }

    public String getProvisionClaimIndicator() {
        return _provisionClaimIndicator;
    }

    public void setProvisionClaimIndicator(String provisionClaimIndicator) {
        _provisionClaimIndicator = provisionClaimIndicator;
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
