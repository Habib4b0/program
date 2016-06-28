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
public class AccrualMasterSoap implements Serializable {
    private String _accountId;
    private Date _recordCreatedDate;
    private String _postingIndicator;
    private String _brandName;
    private Date _accrualPeriodEndDate;
    private Date _modifiedDate;
    private String _salesMasterId;
    private String _source;
    private int _contractMasterSid;
    private String _documentType;
    private String _inboundStatus;
    private int _modifiedBy;
    private String _acctIdentCodeQualifier;
    private String _compIdentCodeQualifier;
    private int _companyMasterSid;
    private String _contractName;
    private String _accountNo;
    private String _accountName;
    private String _provisionId;
    private double _amount;
    private String _subLedger;
    private boolean _recordLockStatus;
    private String _companyNo;
    private String _itemName;
    private int _rsModelSid;
    private int _accrualMasterSid;
    private int _itemMasterSid;
    private String _itemId;
    private int _brandMasterSid;
    private String _itemIdentCodeQualifier;
    private String _glCompanyMasterSid;
    private int _createdBy;
    private Date _createdDate;
    private Date _accrualPeriodStartDate;
    private String _subLedgerType;
    private String _programNo;
    private String _glCompanyName;
    private String _categoryId;
    private String _itemNo;
    private String _contractId;
    private String _accrualId;
    private String _companyId;
    private String _accrualType;
    private String _brandId;
    private Date _postingDate;
    private Date _glDate;
    private double _glAmount;
    private String _companyCostCenter;
    private String _glAccount;
    private String _batchId;
    private String _programType;
    private String _contractNo;

    public AccrualMasterSoap() {
    }

    public static AccrualMasterSoap toSoapModel(AccrualMaster model) {
        AccrualMasterSoap soapModel = new AccrualMasterSoap();

        soapModel.setAccountId(model.getAccountId());
        soapModel.setRecordCreatedDate(model.getRecordCreatedDate());
        soapModel.setPostingIndicator(model.getPostingIndicator());
        soapModel.setBrandName(model.getBrandName());
        soapModel.setAccrualPeriodEndDate(model.getAccrualPeriodEndDate());
        soapModel.setModifiedDate(model.getModifiedDate());
        soapModel.setSalesMasterId(model.getSalesMasterId());
        soapModel.setSource(model.getSource());
        soapModel.setContractMasterSid(model.getContractMasterSid());
        soapModel.setDocumentType(model.getDocumentType());
        soapModel.setInboundStatus(model.getInboundStatus());
        soapModel.setModifiedBy(model.getModifiedBy());
        soapModel.setAcctIdentCodeQualifier(model.getAcctIdentCodeQualifier());
        soapModel.setCompIdentCodeQualifier(model.getCompIdentCodeQualifier());
        soapModel.setCompanyMasterSid(model.getCompanyMasterSid());
        soapModel.setContractName(model.getContractName());
        soapModel.setAccountNo(model.getAccountNo());
        soapModel.setAccountName(model.getAccountName());
        soapModel.setProvisionId(model.getProvisionId());
        soapModel.setAmount(model.getAmount());
        soapModel.setSubLedger(model.getSubLedger());
        soapModel.setRecordLockStatus(model.getRecordLockStatus());
        soapModel.setCompanyNo(model.getCompanyNo());
        soapModel.setItemName(model.getItemName());
        soapModel.setRsModelSid(model.getRsModelSid());
        soapModel.setAccrualMasterSid(model.getAccrualMasterSid());
        soapModel.setItemMasterSid(model.getItemMasterSid());
        soapModel.setItemId(model.getItemId());
        soapModel.setBrandMasterSid(model.getBrandMasterSid());
        soapModel.setItemIdentCodeQualifier(model.getItemIdentCodeQualifier());
        soapModel.setGlCompanyMasterSid(model.getGlCompanyMasterSid());
        soapModel.setCreatedBy(model.getCreatedBy());
        soapModel.setCreatedDate(model.getCreatedDate());
        soapModel.setAccrualPeriodStartDate(model.getAccrualPeriodStartDate());
        soapModel.setSubLedgerType(model.getSubLedgerType());
        soapModel.setProgramNo(model.getProgramNo());
        soapModel.setGlCompanyName(model.getGlCompanyName());
        soapModel.setCategoryId(model.getCategoryId());
        soapModel.setItemNo(model.getItemNo());
        soapModel.setContractId(model.getContractId());
        soapModel.setAccrualId(model.getAccrualId());
        soapModel.setCompanyId(model.getCompanyId());
        soapModel.setAccrualType(model.getAccrualType());
        soapModel.setBrandId(model.getBrandId());
        soapModel.setPostingDate(model.getPostingDate());
        soapModel.setGlDate(model.getGlDate());
        soapModel.setGlAmount(model.getGlAmount());
        soapModel.setCompanyCostCenter(model.getCompanyCostCenter());
        soapModel.setGlAccount(model.getGlAccount());
        soapModel.setBatchId(model.getBatchId());
        soapModel.setProgramType(model.getProgramType());
        soapModel.setContractNo(model.getContractNo());

        return soapModel;
    }

    public static AccrualMasterSoap[] toSoapModels(AccrualMaster[] models) {
        AccrualMasterSoap[] soapModels = new AccrualMasterSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static AccrualMasterSoap[][] toSoapModels(AccrualMaster[][] models) {
        AccrualMasterSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new AccrualMasterSoap[models.length][models[0].length];
        } else {
            soapModels = new AccrualMasterSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static AccrualMasterSoap[] toSoapModels(List<AccrualMaster> models) {
        List<AccrualMasterSoap> soapModels = new ArrayList<AccrualMasterSoap>(models.size());

        for (AccrualMaster model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new AccrualMasterSoap[soapModels.size()]);
    }

    public int getPrimaryKey() {
        return _accrualMasterSid;
    }

    public void setPrimaryKey(int pk) {
        setAccrualMasterSid(pk);
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

    public String getAcctIdentCodeQualifier() {
        return _acctIdentCodeQualifier;
    }

    public void setAcctIdentCodeQualifier(String acctIdentCodeQualifier) {
        _acctIdentCodeQualifier = acctIdentCodeQualifier;
    }

    public String getCompIdentCodeQualifier() {
        return _compIdentCodeQualifier;
    }

    public void setCompIdentCodeQualifier(String compIdentCodeQualifier) {
        _compIdentCodeQualifier = compIdentCodeQualifier;
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

    public String getProvisionId() {
        return _provisionId;
    }

    public void setProvisionId(String provisionId) {
        _provisionId = provisionId;
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

    public String getCompanyNo() {
        return _companyNo;
    }

    public void setCompanyNo(String companyNo) {
        _companyNo = companyNo;
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

    public int getAccrualMasterSid() {
        return _accrualMasterSid;
    }

    public void setAccrualMasterSid(int accrualMasterSid) {
        _accrualMasterSid = accrualMasterSid;
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

    public String getItemIdentCodeQualifier() {
        return _itemIdentCodeQualifier;
    }

    public void setItemIdentCodeQualifier(String itemIdentCodeQualifier) {
        _itemIdentCodeQualifier = itemIdentCodeQualifier;
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

    public String getGlCompanyName() {
        return _glCompanyName;
    }

    public void setGlCompanyName(String glCompanyName) {
        _glCompanyName = glCompanyName;
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

    public double getGlAmount() {
        return _glAmount;
    }

    public void setGlAmount(double glAmount) {
        _glAmount = glAmount;
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

    public String getProgramType() {
        return _programType;
    }

    public void setProgramType(String programType) {
        _programType = programType;
    }

    public String getContractNo() {
        return _contractNo;
    }

    public void setContractNo(String contractNo) {
        _contractNo = contractNo;
    }
}
