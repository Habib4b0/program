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
public class AccrualDetailsSoap implements Serializable {
    private String _accountId;
    private Date _recordCreatedDate;
    private String _postingIndicator;
    private String _brandName;
    private Date _accrualPeriodEndDate;
    private Date _modifiedDate;
    private String _salesMasterId;
    private String _source;
    private int _contractMasterSid;
    private int _accrualDetailsSid;
    private String _documentType;
    private String _inboundStatus;
    private int _modifiedBy;
    private String _deductionType;
    private int _companyMasterSid;
    private String _contractName;
    private String _accountNo;
    private String _accountName;
    private int _versionNo;
    private String _provisionId;
    private String _companyIdentifierCodeQualifier;
    private double _amount;
    private String _subLedger;
    private boolean _recordLockStatus;
    private String _itemIdentifierCodeQualifier;
    private String _companyNo;
    private String _postingStatus;
    private String _itemName;
    private int _rsModelSid;
    private int _itemMasterSid;
    private String _itemId;
    private int _brandMasterSid;
    private String _glCompanyMasterSid;
    private int _createdBy;
    private Date _createdDate;
    private Date _accrualPeriodStartDate;
    private String _subLedgerType;
    private String _programNo;
    private String _categoryId;
    private String _itemNo;
    private String _deductionSubType;
    private String _acctIdentifierCodeQualifier;
    private String _contractId;
    private String _accrualId;
    private String _companyId;
    private String _accrualType;
    private String _brandId;
    private Date _postingDate;
    private Date _glDate;
    private String _companyCostCenter;
    private String _glAccount;
    private String _batchId;
    private int _programType;
    private String _contractNo;

    public AccrualDetailsSoap() {
    }

    public static AccrualDetailsSoap toSoapModel(AccrualDetails model) {
        AccrualDetailsSoap soapModel = new AccrualDetailsSoap();

        soapModel.setAccountId(model.getAccountId());
        soapModel.setRecordCreatedDate(model.getRecordCreatedDate());
        soapModel.setPostingIndicator(model.getPostingIndicator());
        soapModel.setBrandName(model.getBrandName());
        soapModel.setAccrualPeriodEndDate(model.getAccrualPeriodEndDate());
        soapModel.setModifiedDate(model.getModifiedDate());
        soapModel.setSalesMasterId(model.getSalesMasterId());
        soapModel.setSource(model.getSource());
        soapModel.setContractMasterSid(model.getContractMasterSid());
        soapModel.setAccrualDetailsSid(model.getAccrualDetailsSid());
        soapModel.setDocumentType(model.getDocumentType());
        soapModel.setInboundStatus(model.getInboundStatus());
        soapModel.setModifiedBy(model.getModifiedBy());
        soapModel.setDeductionType(model.getDeductionType());
        soapModel.setCompanyMasterSid(model.getCompanyMasterSid());
        soapModel.setContractName(model.getContractName());
        soapModel.setAccountNo(model.getAccountNo());
        soapModel.setAccountName(model.getAccountName());
        soapModel.setVersionNo(model.getVersionNo());
        soapModel.setProvisionId(model.getProvisionId());
        soapModel.setCompanyIdentifierCodeQualifier(model.getCompanyIdentifierCodeQualifier());
        soapModel.setAmount(model.getAmount());
        soapModel.setSubLedger(model.getSubLedger());
        soapModel.setRecordLockStatus(model.getRecordLockStatus());
        soapModel.setItemIdentifierCodeQualifier(model.getItemIdentifierCodeQualifier());
        soapModel.setCompanyNo(model.getCompanyNo());
        soapModel.setPostingStatus(model.getPostingStatus());
        soapModel.setItemName(model.getItemName());
        soapModel.setRsModelSid(model.getRsModelSid());
        soapModel.setItemMasterSid(model.getItemMasterSid());
        soapModel.setItemId(model.getItemId());
        soapModel.setBrandMasterSid(model.getBrandMasterSid());
        soapModel.setGlCompanyMasterSid(model.getGlCompanyMasterSid());
        soapModel.setCreatedBy(model.getCreatedBy());
        soapModel.setCreatedDate(model.getCreatedDate());
        soapModel.setAccrualPeriodStartDate(model.getAccrualPeriodStartDate());
        soapModel.setSubLedgerType(model.getSubLedgerType());
        soapModel.setProgramNo(model.getProgramNo());
        soapModel.setCategoryId(model.getCategoryId());
        soapModel.setItemNo(model.getItemNo());
        soapModel.setDeductionSubType(model.getDeductionSubType());
        soapModel.setAcctIdentifierCodeQualifier(model.getAcctIdentifierCodeQualifier());
        soapModel.setContractId(model.getContractId());
        soapModel.setAccrualId(model.getAccrualId());
        soapModel.setCompanyId(model.getCompanyId());
        soapModel.setAccrualType(model.getAccrualType());
        soapModel.setBrandId(model.getBrandId());
        soapModel.setPostingDate(model.getPostingDate());
        soapModel.setGlDate(model.getGlDate());
        soapModel.setCompanyCostCenter(model.getCompanyCostCenter());
        soapModel.setGlAccount(model.getGlAccount());
        soapModel.setBatchId(model.getBatchId());
        soapModel.setProgramType(model.getProgramType());
        soapModel.setContractNo(model.getContractNo());

        return soapModel;
    }

    public static AccrualDetailsSoap[] toSoapModels(AccrualDetails[] models) {
        AccrualDetailsSoap[] soapModels = new AccrualDetailsSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static AccrualDetailsSoap[][] toSoapModels(AccrualDetails[][] models) {
        AccrualDetailsSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new AccrualDetailsSoap[models.length][models[0].length];
        } else {
            soapModels = new AccrualDetailsSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static AccrualDetailsSoap[] toSoapModels(List<AccrualDetails> models) {
        List<AccrualDetailsSoap> soapModels = new ArrayList<AccrualDetailsSoap>(models.size());

        for (AccrualDetails model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new AccrualDetailsSoap[soapModels.size()]);
    }

    public int getPrimaryKey() {
        return _accrualDetailsSid;
    }

    public void setPrimaryKey(int pk) {
        setAccrualDetailsSid(pk);
    }

    public String getAccountId() {
        return _accountId;
    }

    public void setAccountId(String accountId) {
        _accountId = accountId;
    }

    public Date getRecordCreatedDate() {
        return _recordCreatedDate;
    }

    public void setRecordCreatedDate(Date recordCreatedDate) {
        _recordCreatedDate = recordCreatedDate;
    }

    public String getPostingIndicator() {
        return _postingIndicator;
    }

    public void setPostingIndicator(String postingIndicator) {
        _postingIndicator = postingIndicator;
    }

    public String getBrandName() {
        return _brandName;
    }

    public void setBrandName(String brandName) {
        _brandName = brandName;
    }

    public Date getAccrualPeriodEndDate() {
        return _accrualPeriodEndDate;
    }

    public void setAccrualPeriodEndDate(Date accrualPeriodEndDate) {
        _accrualPeriodEndDate = accrualPeriodEndDate;
    }

    public Date getModifiedDate() {
        return _modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        _modifiedDate = modifiedDate;
    }

    public String getSalesMasterId() {
        return _salesMasterId;
    }

    public void setSalesMasterId(String salesMasterId) {
        _salesMasterId = salesMasterId;
    }

    public String getSource() {
        return _source;
    }

    public void setSource(String source) {
        _source = source;
    }

    public int getContractMasterSid() {
        return _contractMasterSid;
    }

    public void setContractMasterSid(int contractMasterSid) {
        _contractMasterSid = contractMasterSid;
    }

    public int getAccrualDetailsSid() {
        return _accrualDetailsSid;
    }

    public void setAccrualDetailsSid(int accrualDetailsSid) {
        _accrualDetailsSid = accrualDetailsSid;
    }

    public String getDocumentType() {
        return _documentType;
    }

    public void setDocumentType(String documentType) {
        _documentType = documentType;
    }

    public String getInboundStatus() {
        return _inboundStatus;
    }

    public void setInboundStatus(String inboundStatus) {
        _inboundStatus = inboundStatus;
    }

    public int getModifiedBy() {
        return _modifiedBy;
    }

    public void setModifiedBy(int modifiedBy) {
        _modifiedBy = modifiedBy;
    }

    public String getDeductionType() {
        return _deductionType;
    }

    public void setDeductionType(String deductionType) {
        _deductionType = deductionType;
    }

    public int getCompanyMasterSid() {
        return _companyMasterSid;
    }

    public void setCompanyMasterSid(int companyMasterSid) {
        _companyMasterSid = companyMasterSid;
    }

    public String getContractName() {
        return _contractName;
    }

    public void setContractName(String contractName) {
        _contractName = contractName;
    }

    public String getAccountNo() {
        return _accountNo;
    }

    public void setAccountNo(String accountNo) {
        _accountNo = accountNo;
    }

    public String getAccountName() {
        return _accountName;
    }

    public void setAccountName(String accountName) {
        _accountName = accountName;
    }

    public int getVersionNo() {
        return _versionNo;
    }

    public void setVersionNo(int versionNo) {
        _versionNo = versionNo;
    }

    public String getProvisionId() {
        return _provisionId;
    }

    public void setProvisionId(String provisionId) {
        _provisionId = provisionId;
    }

    public String getCompanyIdentifierCodeQualifier() {
        return _companyIdentifierCodeQualifier;
    }

    public void setCompanyIdentifierCodeQualifier(
        String companyIdentifierCodeQualifier) {
        _companyIdentifierCodeQualifier = companyIdentifierCodeQualifier;
    }

    public double getAmount() {
        return _amount;
    }

    public void setAmount(double amount) {
        _amount = amount;
    }

    public String getSubLedger() {
        return _subLedger;
    }

    public void setSubLedger(String subLedger) {
        _subLedger = subLedger;
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

    public String getItemIdentifierCodeQualifier() {
        return _itemIdentifierCodeQualifier;
    }

    public void setItemIdentifierCodeQualifier(
        String itemIdentifierCodeQualifier) {
        _itemIdentifierCodeQualifier = itemIdentifierCodeQualifier;
    }

    public String getCompanyNo() {
        return _companyNo;
    }

    public void setCompanyNo(String companyNo) {
        _companyNo = companyNo;
    }

    public String getPostingStatus() {
        return _postingStatus;
    }

    public void setPostingStatus(String postingStatus) {
        _postingStatus = postingStatus;
    }

    public String getItemName() {
        return _itemName;
    }

    public void setItemName(String itemName) {
        _itemName = itemName;
    }

    public int getRsModelSid() {
        return _rsModelSid;
    }

    public void setRsModelSid(int rsModelSid) {
        _rsModelSid = rsModelSid;
    }

    public int getItemMasterSid() {
        return _itemMasterSid;
    }

    public void setItemMasterSid(int itemMasterSid) {
        _itemMasterSid = itemMasterSid;
    }

    public String getItemId() {
        return _itemId;
    }

    public void setItemId(String itemId) {
        _itemId = itemId;
    }

    public int getBrandMasterSid() {
        return _brandMasterSid;
    }

    public void setBrandMasterSid(int brandMasterSid) {
        _brandMasterSid = brandMasterSid;
    }

    public String getGlCompanyMasterSid() {
        return _glCompanyMasterSid;
    }

    public void setGlCompanyMasterSid(String glCompanyMasterSid) {
        _glCompanyMasterSid = glCompanyMasterSid;
    }

    public int getCreatedBy() {
        return _createdBy;
    }

    public void setCreatedBy(int createdBy) {
        _createdBy = createdBy;
    }

    public Date getCreatedDate() {
        return _createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        _createdDate = createdDate;
    }

    public Date getAccrualPeriodStartDate() {
        return _accrualPeriodStartDate;
    }

    public void setAccrualPeriodStartDate(Date accrualPeriodStartDate) {
        _accrualPeriodStartDate = accrualPeriodStartDate;
    }

    public String getSubLedgerType() {
        return _subLedgerType;
    }

    public void setSubLedgerType(String subLedgerType) {
        _subLedgerType = subLedgerType;
    }

    public String getProgramNo() {
        return _programNo;
    }

    public void setProgramNo(String programNo) {
        _programNo = programNo;
    }

    public String getCategoryId() {
        return _categoryId;
    }

    public void setCategoryId(String categoryId) {
        _categoryId = categoryId;
    }

    public String getItemNo() {
        return _itemNo;
    }

    public void setItemNo(String itemNo) {
        _itemNo = itemNo;
    }

    public String getDeductionSubType() {
        return _deductionSubType;
    }

    public void setDeductionSubType(String deductionSubType) {
        _deductionSubType = deductionSubType;
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

    public String getAccrualId() {
        return _accrualId;
    }

    public void setAccrualId(String accrualId) {
        _accrualId = accrualId;
    }

    public String getCompanyId() {
        return _companyId;
    }

    public void setCompanyId(String companyId) {
        _companyId = companyId;
    }

    public String getAccrualType() {
        return _accrualType;
    }

    public void setAccrualType(String accrualType) {
        _accrualType = accrualType;
    }

    public String getBrandId() {
        return _brandId;
    }

    public void setBrandId(String brandId) {
        _brandId = brandId;
    }

    public Date getPostingDate() {
        return _postingDate;
    }

    public void setPostingDate(Date postingDate) {
        _postingDate = postingDate;
    }

    public Date getGlDate() {
        return _glDate;
    }

    public void setGlDate(Date glDate) {
        _glDate = glDate;
    }

    public String getCompanyCostCenter() {
        return _companyCostCenter;
    }

    public void setCompanyCostCenter(String companyCostCenter) {
        _companyCostCenter = companyCostCenter;
    }

    public String getGlAccount() {
        return _glAccount;
    }

    public void setGlAccount(String glAccount) {
        _glAccount = glAccount;
    }

    public String getBatchId() {
        return _batchId;
    }

    public void setBatchId(String batchId) {
        _batchId = batchId;
    }

    public int getProgramType() {
        return _programType;
    }

    public void setProgramType(int programType) {
        _programType = programType;
    }

    public String getContractNo() {
        return _contractNo;
    }

    public void setContractNo(String contractNo) {
        _contractNo = contractNo;
    }
}
