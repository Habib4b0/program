package com.stpl.app.model;

import com.stpl.app.service.AccrualMasterLocalServiceUtil;
import com.stpl.app.service.ClpSerializer;

import com.stpl.portal.kernel.bean.AutoEscapeBeanHandler;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.ProxyUtil;
import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.model.BaseModel;
import com.stpl.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class AccrualMasterClp extends BaseModelImpl<AccrualMaster>
    implements AccrualMaster {
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
    private BaseModel<?> _accrualMasterRemoteModel;

    public AccrualMasterClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return AccrualMaster.class;
    }

    @Override
    public String getModelClassName() {
        return AccrualMaster.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _accrualMasterSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setAccrualMasterSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _accrualMasterSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("accountId", getAccountId());
        attributes.put("recordCreatedDate", getRecordCreatedDate());
        attributes.put("postingIndicator", getPostingIndicator());
        attributes.put("brandName", getBrandName());
        attributes.put("accrualPeriodEndDate", getAccrualPeriodEndDate());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("salesMasterId", getSalesMasterId());
        attributes.put("source", getSource());
        attributes.put("contractMasterSid", getContractMasterSid());
        attributes.put("documentType", getDocumentType());
        attributes.put("inboundStatus", getInboundStatus());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("acctIdentCodeQualifier", getAcctIdentCodeQualifier());
        attributes.put("compIdentCodeQualifier", getCompIdentCodeQualifier());
        attributes.put("companyMasterSid", getCompanyMasterSid());
        attributes.put("contractName", getContractName());
        attributes.put("accountNo", getAccountNo());
        attributes.put("accountName", getAccountName());
        attributes.put("provisionId", getProvisionId());
        attributes.put("amount", getAmount());
        attributes.put("subLedger", getSubLedger());
        attributes.put("recordLockStatus", getRecordLockStatus());
        attributes.put("companyNo", getCompanyNo());
        attributes.put("itemName", getItemName());
        attributes.put("rsModelSid", getRsModelSid());
        attributes.put("accrualMasterSid", getAccrualMasterSid());
        attributes.put("itemMasterSid", getItemMasterSid());
        attributes.put("itemId", getItemId());
        attributes.put("brandMasterSid", getBrandMasterSid());
        attributes.put("itemIdentCodeQualifier", getItemIdentCodeQualifier());
        attributes.put("glCompanyMasterSid", getGlCompanyMasterSid());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("accrualPeriodStartDate", getAccrualPeriodStartDate());
        attributes.put("subLedgerType", getSubLedgerType());
        attributes.put("programNo", getProgramNo());
        attributes.put("glCompanyName", getGlCompanyName());
        attributes.put("categoryId", getCategoryId());
        attributes.put("itemNo", getItemNo());
        attributes.put("contractId", getContractId());
        attributes.put("accrualId", getAccrualId());
        attributes.put("companyId", getCompanyId());
        attributes.put("accrualType", getAccrualType());
        attributes.put("brandId", getBrandId());
        attributes.put("postingDate", getPostingDate());
        attributes.put("glDate", getGlDate());
        attributes.put("glAmount", getGlAmount());
        attributes.put("companyCostCenter", getCompanyCostCenter());
        attributes.put("glAccount", getGlAccount());
        attributes.put("batchId", getBatchId());
        attributes.put("programType", getProgramType());
        attributes.put("contractNo", getContractNo());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String accountId = (String) attributes.get("accountId");

        if (accountId != null) {
            setAccountId(accountId);
        }

        Date recordCreatedDate = (Date) attributes.get("recordCreatedDate");

        if (recordCreatedDate != null) {
            setRecordCreatedDate(recordCreatedDate);
        }

        String postingIndicator = (String) attributes.get("postingIndicator");

        if (postingIndicator != null) {
            setPostingIndicator(postingIndicator);
        }

        String brandName = (String) attributes.get("brandName");

        if (brandName != null) {
            setBrandName(brandName);
        }

        Date accrualPeriodEndDate = (Date) attributes.get(
                "accrualPeriodEndDate");

        if (accrualPeriodEndDate != null) {
            setAccrualPeriodEndDate(accrualPeriodEndDate);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        String salesMasterId = (String) attributes.get("salesMasterId");

        if (salesMasterId != null) {
            setSalesMasterId(salesMasterId);
        }

        String source = (String) attributes.get("source");

        if (source != null) {
            setSource(source);
        }

        Integer contractMasterSid = (Integer) attributes.get(
                "contractMasterSid");

        if (contractMasterSid != null) {
            setContractMasterSid(contractMasterSid);
        }

        String documentType = (String) attributes.get("documentType");

        if (documentType != null) {
            setDocumentType(documentType);
        }

        String inboundStatus = (String) attributes.get("inboundStatus");

        if (inboundStatus != null) {
            setInboundStatus(inboundStatus);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        String acctIdentCodeQualifier = (String) attributes.get(
                "acctIdentCodeQualifier");

        if (acctIdentCodeQualifier != null) {
            setAcctIdentCodeQualifier(acctIdentCodeQualifier);
        }

        String compIdentCodeQualifier = (String) attributes.get(
                "compIdentCodeQualifier");

        if (compIdentCodeQualifier != null) {
            setCompIdentCodeQualifier(compIdentCodeQualifier);
        }

        Integer companyMasterSid = (Integer) attributes.get("companyMasterSid");

        if (companyMasterSid != null) {
            setCompanyMasterSid(companyMasterSid);
        }

        String contractName = (String) attributes.get("contractName");

        if (contractName != null) {
            setContractName(contractName);
        }

        String accountNo = (String) attributes.get("accountNo");

        if (accountNo != null) {
            setAccountNo(accountNo);
        }

        String accountName = (String) attributes.get("accountName");

        if (accountName != null) {
            setAccountName(accountName);
        }

        String provisionId = (String) attributes.get("provisionId");

        if (provisionId != null) {
            setProvisionId(provisionId);
        }

        Double amount = (Double) attributes.get("amount");

        if (amount != null) {
            setAmount(amount);
        }

        String subLedger = (String) attributes.get("subLedger");

        if (subLedger != null) {
            setSubLedger(subLedger);
        }

        Boolean recordLockStatus = (Boolean) attributes.get("recordLockStatus");

        if (recordLockStatus != null) {
            setRecordLockStatus(recordLockStatus);
        }

        String companyNo = (String) attributes.get("companyNo");

        if (companyNo != null) {
            setCompanyNo(companyNo);
        }

        String itemName = (String) attributes.get("itemName");

        if (itemName != null) {
            setItemName(itemName);
        }

        Integer rsModelSid = (Integer) attributes.get("rsModelSid");

        if (rsModelSid != null) {
            setRsModelSid(rsModelSid);
        }

        Integer accrualMasterSid = (Integer) attributes.get("accrualMasterSid");

        if (accrualMasterSid != null) {
            setAccrualMasterSid(accrualMasterSid);
        }

        Integer itemMasterSid = (Integer) attributes.get("itemMasterSid");

        if (itemMasterSid != null) {
            setItemMasterSid(itemMasterSid);
        }

        String itemId = (String) attributes.get("itemId");

        if (itemId != null) {
            setItemId(itemId);
        }

        Integer brandMasterSid = (Integer) attributes.get("brandMasterSid");

        if (brandMasterSid != null) {
            setBrandMasterSid(brandMasterSid);
        }

        String itemIdentCodeQualifier = (String) attributes.get(
                "itemIdentCodeQualifier");

        if (itemIdentCodeQualifier != null) {
            setItemIdentCodeQualifier(itemIdentCodeQualifier);
        }

        String glCompanyMasterSid = (String) attributes.get(
                "glCompanyMasterSid");

        if (glCompanyMasterSid != null) {
            setGlCompanyMasterSid(glCompanyMasterSid);
        }

        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        Date accrualPeriodStartDate = (Date) attributes.get(
                "accrualPeriodStartDate");

        if (accrualPeriodStartDate != null) {
            setAccrualPeriodStartDate(accrualPeriodStartDate);
        }

        String subLedgerType = (String) attributes.get("subLedgerType");

        if (subLedgerType != null) {
            setSubLedgerType(subLedgerType);
        }

        String programNo = (String) attributes.get("programNo");

        if (programNo != null) {
            setProgramNo(programNo);
        }

        String glCompanyName = (String) attributes.get("glCompanyName");

        if (glCompanyName != null) {
            setGlCompanyName(glCompanyName);
        }

        String categoryId = (String) attributes.get("categoryId");

        if (categoryId != null) {
            setCategoryId(categoryId);
        }

        String itemNo = (String) attributes.get("itemNo");

        if (itemNo != null) {
            setItemNo(itemNo);
        }

        String contractId = (String) attributes.get("contractId");

        if (contractId != null) {
            setContractId(contractId);
        }

        String accrualId = (String) attributes.get("accrualId");

        if (accrualId != null) {
            setAccrualId(accrualId);
        }

        String companyId = (String) attributes.get("companyId");

        if (companyId != null) {
            setCompanyId(companyId);
        }

        String accrualType = (String) attributes.get("accrualType");

        if (accrualType != null) {
            setAccrualType(accrualType);
        }

        String brandId = (String) attributes.get("brandId");

        if (brandId != null) {
            setBrandId(brandId);
        }

        Date postingDate = (Date) attributes.get("postingDate");

        if (postingDate != null) {
            setPostingDate(postingDate);
        }

        Date glDate = (Date) attributes.get("glDate");

        if (glDate != null) {
            setGlDate(glDate);
        }

        Double glAmount = (Double) attributes.get("glAmount");

        if (glAmount != null) {
            setGlAmount(glAmount);
        }

        String companyCostCenter = (String) attributes.get("companyCostCenter");

        if (companyCostCenter != null) {
            setCompanyCostCenter(companyCostCenter);
        }

        String glAccount = (String) attributes.get("glAccount");

        if (glAccount != null) {
            setGlAccount(glAccount);
        }

        String batchId = (String) attributes.get("batchId");

        if (batchId != null) {
            setBatchId(batchId);
        }

        String programType = (String) attributes.get("programType");

        if (programType != null) {
            setProgramType(programType);
        }

        String contractNo = (String) attributes.get("contractNo");

        if (contractNo != null) {
            setContractNo(contractNo);
        }
    }

    @Override
    public String getAccountId() {
        return _accountId;
    }

    @Override
    public void setAccountId(String accountId) {
        _accountId = accountId;

        if (_accrualMasterRemoteModel != null) {
            try {
                Class<?> clazz = _accrualMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setAccountId", String.class);

                method.invoke(_accrualMasterRemoteModel, accountId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getRecordCreatedDate() {
        return _recordCreatedDate;
    }

    @Override
    public void setRecordCreatedDate(Date recordCreatedDate) {
        _recordCreatedDate = recordCreatedDate;

        if (_accrualMasterRemoteModel != null) {
            try {
                Class<?> clazz = _accrualMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setRecordCreatedDate",
                        Date.class);

                method.invoke(_accrualMasterRemoteModel, recordCreatedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getPostingIndicator() {
        return _postingIndicator;
    }

    @Override
    public void setPostingIndicator(String postingIndicator) {
        _postingIndicator = postingIndicator;

        if (_accrualMasterRemoteModel != null) {
            try {
                Class<?> clazz = _accrualMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setPostingIndicator",
                        String.class);

                method.invoke(_accrualMasterRemoteModel, postingIndicator);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getBrandName() {
        return _brandName;
    }

    @Override
    public void setBrandName(String brandName) {
        _brandName = brandName;

        if (_accrualMasterRemoteModel != null) {
            try {
                Class<?> clazz = _accrualMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setBrandName", String.class);

                method.invoke(_accrualMasterRemoteModel, brandName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getAccrualPeriodEndDate() {
        return _accrualPeriodEndDate;
    }

    @Override
    public void setAccrualPeriodEndDate(Date accrualPeriodEndDate) {
        _accrualPeriodEndDate = accrualPeriodEndDate;

        if (_accrualMasterRemoteModel != null) {
            try {
                Class<?> clazz = _accrualMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setAccrualPeriodEndDate",
                        Date.class);

                method.invoke(_accrualMasterRemoteModel, accrualPeriodEndDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getModifiedDate() {
        return _modifiedDate;
    }

    @Override
    public void setModifiedDate(Date modifiedDate) {
        _modifiedDate = modifiedDate;

        if (_accrualMasterRemoteModel != null) {
            try {
                Class<?> clazz = _accrualMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_accrualMasterRemoteModel, modifiedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getSalesMasterId() {
        return _salesMasterId;
    }

    @Override
    public void setSalesMasterId(String salesMasterId) {
        _salesMasterId = salesMasterId;

        if (_accrualMasterRemoteModel != null) {
            try {
                Class<?> clazz = _accrualMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setSalesMasterId", String.class);

                method.invoke(_accrualMasterRemoteModel, salesMasterId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getSource() {
        return _source;
    }

    @Override
    public void setSource(String source) {
        _source = source;

        if (_accrualMasterRemoteModel != null) {
            try {
                Class<?> clazz = _accrualMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setSource", String.class);

                method.invoke(_accrualMasterRemoteModel, source);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getContractMasterSid() {
        return _contractMasterSid;
    }

    @Override
    public void setContractMasterSid(int contractMasterSid) {
        _contractMasterSid = contractMasterSid;

        if (_accrualMasterRemoteModel != null) {
            try {
                Class<?> clazz = _accrualMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setContractMasterSid",
                        int.class);

                method.invoke(_accrualMasterRemoteModel, contractMasterSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getDocumentType() {
        return _documentType;
    }

    @Override
    public void setDocumentType(String documentType) {
        _documentType = documentType;

        if (_accrualMasterRemoteModel != null) {
            try {
                Class<?> clazz = _accrualMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setDocumentType", String.class);

                method.invoke(_accrualMasterRemoteModel, documentType);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getInboundStatus() {
        return _inboundStatus;
    }

    @Override
    public void setInboundStatus(String inboundStatus) {
        _inboundStatus = inboundStatus;

        if (_accrualMasterRemoteModel != null) {
            try {
                Class<?> clazz = _accrualMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setInboundStatus", String.class);

                method.invoke(_accrualMasterRemoteModel, inboundStatus);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getModifiedBy() {
        return _modifiedBy;
    }

    @Override
    public void setModifiedBy(int modifiedBy) {
        _modifiedBy = modifiedBy;

        if (_accrualMasterRemoteModel != null) {
            try {
                Class<?> clazz = _accrualMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", int.class);

                method.invoke(_accrualMasterRemoteModel, modifiedBy);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getAcctIdentCodeQualifier() {
        return _acctIdentCodeQualifier;
    }

    @Override
    public void setAcctIdentCodeQualifier(String acctIdentCodeQualifier) {
        _acctIdentCodeQualifier = acctIdentCodeQualifier;

        if (_accrualMasterRemoteModel != null) {
            try {
                Class<?> clazz = _accrualMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setAcctIdentCodeQualifier",
                        String.class);

                method.invoke(_accrualMasterRemoteModel, acctIdentCodeQualifier);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getCompIdentCodeQualifier() {
        return _compIdentCodeQualifier;
    }

    @Override
    public void setCompIdentCodeQualifier(String compIdentCodeQualifier) {
        _compIdentCodeQualifier = compIdentCodeQualifier;

        if (_accrualMasterRemoteModel != null) {
            try {
                Class<?> clazz = _accrualMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCompIdentCodeQualifier",
                        String.class);

                method.invoke(_accrualMasterRemoteModel, compIdentCodeQualifier);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getCompanyMasterSid() {
        return _companyMasterSid;
    }

    @Override
    public void setCompanyMasterSid(int companyMasterSid) {
        _companyMasterSid = companyMasterSid;

        if (_accrualMasterRemoteModel != null) {
            try {
                Class<?> clazz = _accrualMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyMasterSid", int.class);

                method.invoke(_accrualMasterRemoteModel, companyMasterSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getContractName() {
        return _contractName;
    }

    @Override
    public void setContractName(String contractName) {
        _contractName = contractName;

        if (_accrualMasterRemoteModel != null) {
            try {
                Class<?> clazz = _accrualMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setContractName", String.class);

                method.invoke(_accrualMasterRemoteModel, contractName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getAccountNo() {
        return _accountNo;
    }

    @Override
    public void setAccountNo(String accountNo) {
        _accountNo = accountNo;

        if (_accrualMasterRemoteModel != null) {
            try {
                Class<?> clazz = _accrualMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setAccountNo", String.class);

                method.invoke(_accrualMasterRemoteModel, accountNo);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getAccountName() {
        return _accountName;
    }

    @Override
    public void setAccountName(String accountName) {
        _accountName = accountName;

        if (_accrualMasterRemoteModel != null) {
            try {
                Class<?> clazz = _accrualMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setAccountName", String.class);

                method.invoke(_accrualMasterRemoteModel, accountName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getProvisionId() {
        return _provisionId;
    }

    @Override
    public void setProvisionId(String provisionId) {
        _provisionId = provisionId;

        if (_accrualMasterRemoteModel != null) {
            try {
                Class<?> clazz = _accrualMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setProvisionId", String.class);

                method.invoke(_accrualMasterRemoteModel, provisionId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getAmount() {
        return _amount;
    }

    @Override
    public void setAmount(double amount) {
        _amount = amount;

        if (_accrualMasterRemoteModel != null) {
            try {
                Class<?> clazz = _accrualMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setAmount", double.class);

                method.invoke(_accrualMasterRemoteModel, amount);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getSubLedger() {
        return _subLedger;
    }

    @Override
    public void setSubLedger(String subLedger) {
        _subLedger = subLedger;

        if (_accrualMasterRemoteModel != null) {
            try {
                Class<?> clazz = _accrualMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setSubLedger", String.class);

                method.invoke(_accrualMasterRemoteModel, subLedger);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public boolean getRecordLockStatus() {
        return _recordLockStatus;
    }

    @Override
    public boolean isRecordLockStatus() {
        return _recordLockStatus;
    }

    @Override
    public void setRecordLockStatus(boolean recordLockStatus) {
        _recordLockStatus = recordLockStatus;

        if (_accrualMasterRemoteModel != null) {
            try {
                Class<?> clazz = _accrualMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setRecordLockStatus",
                        boolean.class);

                method.invoke(_accrualMasterRemoteModel, recordLockStatus);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getCompanyNo() {
        return _companyNo;
    }

    @Override
    public void setCompanyNo(String companyNo) {
        _companyNo = companyNo;

        if (_accrualMasterRemoteModel != null) {
            try {
                Class<?> clazz = _accrualMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyNo", String.class);

                method.invoke(_accrualMasterRemoteModel, companyNo);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getItemName() {
        return _itemName;
    }

    @Override
    public void setItemName(String itemName) {
        _itemName = itemName;

        if (_accrualMasterRemoteModel != null) {
            try {
                Class<?> clazz = _accrualMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setItemName", String.class);

                method.invoke(_accrualMasterRemoteModel, itemName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getRsModelSid() {
        return _rsModelSid;
    }

    @Override
    public void setRsModelSid(int rsModelSid) {
        _rsModelSid = rsModelSid;

        if (_accrualMasterRemoteModel != null) {
            try {
                Class<?> clazz = _accrualMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setRsModelSid", int.class);

                method.invoke(_accrualMasterRemoteModel, rsModelSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getAccrualMasterSid() {
        return _accrualMasterSid;
    }

    @Override
    public void setAccrualMasterSid(int accrualMasterSid) {
        _accrualMasterSid = accrualMasterSid;

        if (_accrualMasterRemoteModel != null) {
            try {
                Class<?> clazz = _accrualMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setAccrualMasterSid", int.class);

                method.invoke(_accrualMasterRemoteModel, accrualMasterSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getItemMasterSid() {
        return _itemMasterSid;
    }

    @Override
    public void setItemMasterSid(int itemMasterSid) {
        _itemMasterSid = itemMasterSid;

        if (_accrualMasterRemoteModel != null) {
            try {
                Class<?> clazz = _accrualMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setItemMasterSid", int.class);

                method.invoke(_accrualMasterRemoteModel, itemMasterSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getItemId() {
        return _itemId;
    }

    @Override
    public void setItemId(String itemId) {
        _itemId = itemId;

        if (_accrualMasterRemoteModel != null) {
            try {
                Class<?> clazz = _accrualMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setItemId", String.class);

                method.invoke(_accrualMasterRemoteModel, itemId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getBrandMasterSid() {
        return _brandMasterSid;
    }

    @Override
    public void setBrandMasterSid(int brandMasterSid) {
        _brandMasterSid = brandMasterSid;

        if (_accrualMasterRemoteModel != null) {
            try {
                Class<?> clazz = _accrualMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setBrandMasterSid", int.class);

                method.invoke(_accrualMasterRemoteModel, brandMasterSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getItemIdentCodeQualifier() {
        return _itemIdentCodeQualifier;
    }

    @Override
    public void setItemIdentCodeQualifier(String itemIdentCodeQualifier) {
        _itemIdentCodeQualifier = itemIdentCodeQualifier;

        if (_accrualMasterRemoteModel != null) {
            try {
                Class<?> clazz = _accrualMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setItemIdentCodeQualifier",
                        String.class);

                method.invoke(_accrualMasterRemoteModel, itemIdentCodeQualifier);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getGlCompanyMasterSid() {
        return _glCompanyMasterSid;
    }

    @Override
    public void setGlCompanyMasterSid(String glCompanyMasterSid) {
        _glCompanyMasterSid = glCompanyMasterSid;

        if (_accrualMasterRemoteModel != null) {
            try {
                Class<?> clazz = _accrualMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setGlCompanyMasterSid",
                        String.class);

                method.invoke(_accrualMasterRemoteModel, glCompanyMasterSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getCreatedBy() {
        return _createdBy;
    }

    @Override
    public void setCreatedBy(int createdBy) {
        _createdBy = createdBy;

        if (_accrualMasterRemoteModel != null) {
            try {
                Class<?> clazz = _accrualMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", int.class);

                method.invoke(_accrualMasterRemoteModel, createdBy);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getCreatedDate() {
        return _createdDate;
    }

    @Override
    public void setCreatedDate(Date createdDate) {
        _createdDate = createdDate;

        if (_accrualMasterRemoteModel != null) {
            try {
                Class<?> clazz = _accrualMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_accrualMasterRemoteModel, createdDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getAccrualPeriodStartDate() {
        return _accrualPeriodStartDate;
    }

    @Override
    public void setAccrualPeriodStartDate(Date accrualPeriodStartDate) {
        _accrualPeriodStartDate = accrualPeriodStartDate;

        if (_accrualMasterRemoteModel != null) {
            try {
                Class<?> clazz = _accrualMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setAccrualPeriodStartDate",
                        Date.class);

                method.invoke(_accrualMasterRemoteModel, accrualPeriodStartDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getSubLedgerType() {
        return _subLedgerType;
    }

    @Override
    public void setSubLedgerType(String subLedgerType) {
        _subLedgerType = subLedgerType;

        if (_accrualMasterRemoteModel != null) {
            try {
                Class<?> clazz = _accrualMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setSubLedgerType", String.class);

                method.invoke(_accrualMasterRemoteModel, subLedgerType);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getProgramNo() {
        return _programNo;
    }

    @Override
    public void setProgramNo(String programNo) {
        _programNo = programNo;

        if (_accrualMasterRemoteModel != null) {
            try {
                Class<?> clazz = _accrualMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setProgramNo", String.class);

                method.invoke(_accrualMasterRemoteModel, programNo);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getGlCompanyName() {
        return _glCompanyName;
    }

    @Override
    public void setGlCompanyName(String glCompanyName) {
        _glCompanyName = glCompanyName;

        if (_accrualMasterRemoteModel != null) {
            try {
                Class<?> clazz = _accrualMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setGlCompanyName", String.class);

                method.invoke(_accrualMasterRemoteModel, glCompanyName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getCategoryId() {
        return _categoryId;
    }

    @Override
    public void setCategoryId(String categoryId) {
        _categoryId = categoryId;

        if (_accrualMasterRemoteModel != null) {
            try {
                Class<?> clazz = _accrualMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCategoryId", String.class);

                method.invoke(_accrualMasterRemoteModel, categoryId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getItemNo() {
        return _itemNo;
    }

    @Override
    public void setItemNo(String itemNo) {
        _itemNo = itemNo;

        if (_accrualMasterRemoteModel != null) {
            try {
                Class<?> clazz = _accrualMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setItemNo", String.class);

                method.invoke(_accrualMasterRemoteModel, itemNo);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getContractId() {
        return _contractId;
    }

    @Override
    public void setContractId(String contractId) {
        _contractId = contractId;

        if (_accrualMasterRemoteModel != null) {
            try {
                Class<?> clazz = _accrualMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setContractId", String.class);

                method.invoke(_accrualMasterRemoteModel, contractId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getAccrualId() {
        return _accrualId;
    }

    @Override
    public void setAccrualId(String accrualId) {
        _accrualId = accrualId;

        if (_accrualMasterRemoteModel != null) {
            try {
                Class<?> clazz = _accrualMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setAccrualId", String.class);

                method.invoke(_accrualMasterRemoteModel, accrualId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getCompanyId() {
        return _companyId;
    }

    @Override
    public void setCompanyId(String companyId) {
        _companyId = companyId;

        if (_accrualMasterRemoteModel != null) {
            try {
                Class<?> clazz = _accrualMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyId", String.class);

                method.invoke(_accrualMasterRemoteModel, companyId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getAccrualType() {
        return _accrualType;
    }

    @Override
    public void setAccrualType(String accrualType) {
        _accrualType = accrualType;

        if (_accrualMasterRemoteModel != null) {
            try {
                Class<?> clazz = _accrualMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setAccrualType", String.class);

                method.invoke(_accrualMasterRemoteModel, accrualType);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getBrandId() {
        return _brandId;
    }

    @Override
    public void setBrandId(String brandId) {
        _brandId = brandId;

        if (_accrualMasterRemoteModel != null) {
            try {
                Class<?> clazz = _accrualMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setBrandId", String.class);

                method.invoke(_accrualMasterRemoteModel, brandId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getPostingDate() {
        return _postingDate;
    }

    @Override
    public void setPostingDate(Date postingDate) {
        _postingDate = postingDate;

        if (_accrualMasterRemoteModel != null) {
            try {
                Class<?> clazz = _accrualMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setPostingDate", Date.class);

                method.invoke(_accrualMasterRemoteModel, postingDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getGlDate() {
        return _glDate;
    }

    @Override
    public void setGlDate(Date glDate) {
        _glDate = glDate;

        if (_accrualMasterRemoteModel != null) {
            try {
                Class<?> clazz = _accrualMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setGlDate", Date.class);

                method.invoke(_accrualMasterRemoteModel, glDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getGlAmount() {
        return _glAmount;
    }

    @Override
    public void setGlAmount(double glAmount) {
        _glAmount = glAmount;

        if (_accrualMasterRemoteModel != null) {
            try {
                Class<?> clazz = _accrualMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setGlAmount", double.class);

                method.invoke(_accrualMasterRemoteModel, glAmount);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getCompanyCostCenter() {
        return _companyCostCenter;
    }

    @Override
    public void setCompanyCostCenter(String companyCostCenter) {
        _companyCostCenter = companyCostCenter;

        if (_accrualMasterRemoteModel != null) {
            try {
                Class<?> clazz = _accrualMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyCostCenter",
                        String.class);

                method.invoke(_accrualMasterRemoteModel, companyCostCenter);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getGlAccount() {
        return _glAccount;
    }

    @Override
    public void setGlAccount(String glAccount) {
        _glAccount = glAccount;

        if (_accrualMasterRemoteModel != null) {
            try {
                Class<?> clazz = _accrualMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setGlAccount", String.class);

                method.invoke(_accrualMasterRemoteModel, glAccount);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getBatchId() {
        return _batchId;
    }

    @Override
    public void setBatchId(String batchId) {
        _batchId = batchId;

        if (_accrualMasterRemoteModel != null) {
            try {
                Class<?> clazz = _accrualMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setBatchId", String.class);

                method.invoke(_accrualMasterRemoteModel, batchId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getProgramType() {
        return _programType;
    }

    @Override
    public void setProgramType(String programType) {
        _programType = programType;

        if (_accrualMasterRemoteModel != null) {
            try {
                Class<?> clazz = _accrualMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setProgramType", String.class);

                method.invoke(_accrualMasterRemoteModel, programType);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getContractNo() {
        return _contractNo;
    }

    @Override
    public void setContractNo(String contractNo) {
        _contractNo = contractNo;

        if (_accrualMasterRemoteModel != null) {
            try {
                Class<?> clazz = _accrualMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setContractNo", String.class);

                method.invoke(_accrualMasterRemoteModel, contractNo);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getAccrualMasterRemoteModel() {
        return _accrualMasterRemoteModel;
    }

    public void setAccrualMasterRemoteModel(
        BaseModel<?> accrualMasterRemoteModel) {
        _accrualMasterRemoteModel = accrualMasterRemoteModel;
    }

    public Object invokeOnRemoteModel(String methodName,
        Class<?>[] parameterTypes, Object[] parameterValues)
        throws Exception {
        Object[] remoteParameterValues = new Object[parameterValues.length];

        for (int i = 0; i < parameterValues.length; i++) {
            if (parameterValues[i] != null) {
                remoteParameterValues[i] = ClpSerializer.translateInput(parameterValues[i]);
            }
        }

        Class<?> remoteModelClass = _accrualMasterRemoteModel.getClass();

        ClassLoader remoteModelClassLoader = remoteModelClass.getClassLoader();

        Class<?>[] remoteParameterTypes = new Class[parameterTypes.length];

        for (int i = 0; i < parameterTypes.length; i++) {
            if (parameterTypes[i].isPrimitive()) {
                remoteParameterTypes[i] = parameterTypes[i];
            } else {
                String parameterTypeName = parameterTypes[i].getName();

                remoteParameterTypes[i] = remoteModelClassLoader.loadClass(parameterTypeName);
            }
        }

        Method method = remoteModelClass.getMethod(methodName,
                remoteParameterTypes);

        Object returnValue = method.invoke(_accrualMasterRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            AccrualMasterLocalServiceUtil.addAccrualMaster(this);
        } else {
            AccrualMasterLocalServiceUtil.updateAccrualMaster(this);
        }
    }

    @Override
    public AccrualMaster toEscapedModel() {
        return (AccrualMaster) ProxyUtil.newProxyInstance(AccrualMaster.class.getClassLoader(),
            new Class[] { AccrualMaster.class }, new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        AccrualMasterClp clone = new AccrualMasterClp();

        clone.setAccountId(getAccountId());
        clone.setRecordCreatedDate(getRecordCreatedDate());
        clone.setPostingIndicator(getPostingIndicator());
        clone.setBrandName(getBrandName());
        clone.setAccrualPeriodEndDate(getAccrualPeriodEndDate());
        clone.setModifiedDate(getModifiedDate());
        clone.setSalesMasterId(getSalesMasterId());
        clone.setSource(getSource());
        clone.setContractMasterSid(getContractMasterSid());
        clone.setDocumentType(getDocumentType());
        clone.setInboundStatus(getInboundStatus());
        clone.setModifiedBy(getModifiedBy());
        clone.setAcctIdentCodeQualifier(getAcctIdentCodeQualifier());
        clone.setCompIdentCodeQualifier(getCompIdentCodeQualifier());
        clone.setCompanyMasterSid(getCompanyMasterSid());
        clone.setContractName(getContractName());
        clone.setAccountNo(getAccountNo());
        clone.setAccountName(getAccountName());
        clone.setProvisionId(getProvisionId());
        clone.setAmount(getAmount());
        clone.setSubLedger(getSubLedger());
        clone.setRecordLockStatus(getRecordLockStatus());
        clone.setCompanyNo(getCompanyNo());
        clone.setItemName(getItemName());
        clone.setRsModelSid(getRsModelSid());
        clone.setAccrualMasterSid(getAccrualMasterSid());
        clone.setItemMasterSid(getItemMasterSid());
        clone.setItemId(getItemId());
        clone.setBrandMasterSid(getBrandMasterSid());
        clone.setItemIdentCodeQualifier(getItemIdentCodeQualifier());
        clone.setGlCompanyMasterSid(getGlCompanyMasterSid());
        clone.setCreatedBy(getCreatedBy());
        clone.setCreatedDate(getCreatedDate());
        clone.setAccrualPeriodStartDate(getAccrualPeriodStartDate());
        clone.setSubLedgerType(getSubLedgerType());
        clone.setProgramNo(getProgramNo());
        clone.setGlCompanyName(getGlCompanyName());
        clone.setCategoryId(getCategoryId());
        clone.setItemNo(getItemNo());
        clone.setContractId(getContractId());
        clone.setAccrualId(getAccrualId());
        clone.setCompanyId(getCompanyId());
        clone.setAccrualType(getAccrualType());
        clone.setBrandId(getBrandId());
        clone.setPostingDate(getPostingDate());
        clone.setGlDate(getGlDate());
        clone.setGlAmount(getGlAmount());
        clone.setCompanyCostCenter(getCompanyCostCenter());
        clone.setGlAccount(getGlAccount());
        clone.setBatchId(getBatchId());
        clone.setProgramType(getProgramType());
        clone.setContractNo(getContractNo());

        return clone;
    }

    @Override
    public int compareTo(AccrualMaster accrualMaster) {
        int primaryKey = accrualMaster.getPrimaryKey();

        if (getPrimaryKey() < primaryKey) {
            return -1;
        } else if (getPrimaryKey() > primaryKey) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof AccrualMasterClp)) {
            return false;
        }

        AccrualMasterClp accrualMaster = (AccrualMasterClp) obj;

        int primaryKey = accrualMaster.getPrimaryKey();

        if (getPrimaryKey() == primaryKey) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return getPrimaryKey();
    }

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(105);

        sb.append("{accountId=");
        sb.append(getAccountId());
        sb.append(", recordCreatedDate=");
        sb.append(getRecordCreatedDate());
        sb.append(", postingIndicator=");
        sb.append(getPostingIndicator());
        sb.append(", brandName=");
        sb.append(getBrandName());
        sb.append(", accrualPeriodEndDate=");
        sb.append(getAccrualPeriodEndDate());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", salesMasterId=");
        sb.append(getSalesMasterId());
        sb.append(", source=");
        sb.append(getSource());
        sb.append(", contractMasterSid=");
        sb.append(getContractMasterSid());
        sb.append(", documentType=");
        sb.append(getDocumentType());
        sb.append(", inboundStatus=");
        sb.append(getInboundStatus());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", acctIdentCodeQualifier=");
        sb.append(getAcctIdentCodeQualifier());
        sb.append(", compIdentCodeQualifier=");
        sb.append(getCompIdentCodeQualifier());
        sb.append(", companyMasterSid=");
        sb.append(getCompanyMasterSid());
        sb.append(", contractName=");
        sb.append(getContractName());
        sb.append(", accountNo=");
        sb.append(getAccountNo());
        sb.append(", accountName=");
        sb.append(getAccountName());
        sb.append(", provisionId=");
        sb.append(getProvisionId());
        sb.append(", amount=");
        sb.append(getAmount());
        sb.append(", subLedger=");
        sb.append(getSubLedger());
        sb.append(", recordLockStatus=");
        sb.append(getRecordLockStatus());
        sb.append(", companyNo=");
        sb.append(getCompanyNo());
        sb.append(", itemName=");
        sb.append(getItemName());
        sb.append(", rsModelSid=");
        sb.append(getRsModelSid());
        sb.append(", accrualMasterSid=");
        sb.append(getAccrualMasterSid());
        sb.append(", itemMasterSid=");
        sb.append(getItemMasterSid());
        sb.append(", itemId=");
        sb.append(getItemId());
        sb.append(", brandMasterSid=");
        sb.append(getBrandMasterSid());
        sb.append(", itemIdentCodeQualifier=");
        sb.append(getItemIdentCodeQualifier());
        sb.append(", glCompanyMasterSid=");
        sb.append(getGlCompanyMasterSid());
        sb.append(", createdBy=");
        sb.append(getCreatedBy());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", accrualPeriodStartDate=");
        sb.append(getAccrualPeriodStartDate());
        sb.append(", subLedgerType=");
        sb.append(getSubLedgerType());
        sb.append(", programNo=");
        sb.append(getProgramNo());
        sb.append(", glCompanyName=");
        sb.append(getGlCompanyName());
        sb.append(", categoryId=");
        sb.append(getCategoryId());
        sb.append(", itemNo=");
        sb.append(getItemNo());
        sb.append(", contractId=");
        sb.append(getContractId());
        sb.append(", accrualId=");
        sb.append(getAccrualId());
        sb.append(", companyId=");
        sb.append(getCompanyId());
        sb.append(", accrualType=");
        sb.append(getAccrualType());
        sb.append(", brandId=");
        sb.append(getBrandId());
        sb.append(", postingDate=");
        sb.append(getPostingDate());
        sb.append(", glDate=");
        sb.append(getGlDate());
        sb.append(", glAmount=");
        sb.append(getGlAmount());
        sb.append(", companyCostCenter=");
        sb.append(getCompanyCostCenter());
        sb.append(", glAccount=");
        sb.append(getGlAccount());
        sb.append(", batchId=");
        sb.append(getBatchId());
        sb.append(", programType=");
        sb.append(getProgramType());
        sb.append(", contractNo=");
        sb.append(getContractNo());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(160);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.AccrualMaster");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>accountId</column-name><column-value><![CDATA[");
        sb.append(getAccountId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>recordCreatedDate</column-name><column-value><![CDATA[");
        sb.append(getRecordCreatedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>postingIndicator</column-name><column-value><![CDATA[");
        sb.append(getPostingIndicator());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>brandName</column-name><column-value><![CDATA[");
        sb.append(getBrandName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>accrualPeriodEndDate</column-name><column-value><![CDATA[");
        sb.append(getAccrualPeriodEndDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
        sb.append(getModifiedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>salesMasterId</column-name><column-value><![CDATA[");
        sb.append(getSalesMasterId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>source</column-name><column-value><![CDATA[");
        sb.append(getSource());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>contractMasterSid</column-name><column-value><![CDATA[");
        sb.append(getContractMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>documentType</column-name><column-value><![CDATA[");
        sb.append(getDocumentType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>inboundStatus</column-name><column-value><![CDATA[");
        sb.append(getInboundStatus());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedBy</column-name><column-value><![CDATA[");
        sb.append(getModifiedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>acctIdentCodeQualifier</column-name><column-value><![CDATA[");
        sb.append(getAcctIdentCodeQualifier());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>compIdentCodeQualifier</column-name><column-value><![CDATA[");
        sb.append(getCompIdentCodeQualifier());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>companyMasterSid</column-name><column-value><![CDATA[");
        sb.append(getCompanyMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>contractName</column-name><column-value><![CDATA[");
        sb.append(getContractName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>accountNo</column-name><column-value><![CDATA[");
        sb.append(getAccountNo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>accountName</column-name><column-value><![CDATA[");
        sb.append(getAccountName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>provisionId</column-name><column-value><![CDATA[");
        sb.append(getProvisionId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>amount</column-name><column-value><![CDATA[");
        sb.append(getAmount());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>subLedger</column-name><column-value><![CDATA[");
        sb.append(getSubLedger());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>recordLockStatus</column-name><column-value><![CDATA[");
        sb.append(getRecordLockStatus());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>companyNo</column-name><column-value><![CDATA[");
        sb.append(getCompanyNo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemName</column-name><column-value><![CDATA[");
        sb.append(getItemName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>rsModelSid</column-name><column-value><![CDATA[");
        sb.append(getRsModelSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>accrualMasterSid</column-name><column-value><![CDATA[");
        sb.append(getAccrualMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemMasterSid</column-name><column-value><![CDATA[");
        sb.append(getItemMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemId</column-name><column-value><![CDATA[");
        sb.append(getItemId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>brandMasterSid</column-name><column-value><![CDATA[");
        sb.append(getBrandMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemIdentCodeQualifier</column-name><column-value><![CDATA[");
        sb.append(getItemIdentCodeQualifier());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>glCompanyMasterSid</column-name><column-value><![CDATA[");
        sb.append(getGlCompanyMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>createdBy</column-name><column-value><![CDATA[");
        sb.append(getCreatedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>createdDate</column-name><column-value><![CDATA[");
        sb.append(getCreatedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>accrualPeriodStartDate</column-name><column-value><![CDATA[");
        sb.append(getAccrualPeriodStartDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>subLedgerType</column-name><column-value><![CDATA[");
        sb.append(getSubLedgerType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>programNo</column-name><column-value><![CDATA[");
        sb.append(getProgramNo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>glCompanyName</column-name><column-value><![CDATA[");
        sb.append(getGlCompanyName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>categoryId</column-name><column-value><![CDATA[");
        sb.append(getCategoryId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemNo</column-name><column-value><![CDATA[");
        sb.append(getItemNo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>contractId</column-name><column-value><![CDATA[");
        sb.append(getContractId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>accrualId</column-name><column-value><![CDATA[");
        sb.append(getAccrualId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>companyId</column-name><column-value><![CDATA[");
        sb.append(getCompanyId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>accrualType</column-name><column-value><![CDATA[");
        sb.append(getAccrualType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>brandId</column-name><column-value><![CDATA[");
        sb.append(getBrandId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>postingDate</column-name><column-value><![CDATA[");
        sb.append(getPostingDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>glDate</column-name><column-value><![CDATA[");
        sb.append(getGlDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>glAmount</column-name><column-value><![CDATA[");
        sb.append(getGlAmount());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>companyCostCenter</column-name><column-value><![CDATA[");
        sb.append(getCompanyCostCenter());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>glAccount</column-name><column-value><![CDATA[");
        sb.append(getGlAccount());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>batchId</column-name><column-value><![CDATA[");
        sb.append(getBatchId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>programType</column-name><column-value><![CDATA[");
        sb.append(getProgramType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>contractNo</column-name><column-value><![CDATA[");
        sb.append(getContractNo());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
