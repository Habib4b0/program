package com.stpl.app.model;

import com.stpl.app.service.AccrualDetailsLocalServiceUtil;
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


public class AccrualDetailsClp extends BaseModelImpl<AccrualDetails>
    implements AccrualDetails {
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
    private BaseModel<?> _accrualDetailsRemoteModel;

    public AccrualDetailsClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return AccrualDetails.class;
    }

    @Override
    public String getModelClassName() {
        return AccrualDetails.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _accrualDetailsSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setAccrualDetailsSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _accrualDetailsSid;
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
        attributes.put("accrualDetailsSid", getAccrualDetailsSid());
        attributes.put("documentType", getDocumentType());
        attributes.put("inboundStatus", getInboundStatus());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("deductionType", getDeductionType());
        attributes.put("companyMasterSid", getCompanyMasterSid());
        attributes.put("contractName", getContractName());
        attributes.put("accountNo", getAccountNo());
        attributes.put("accountName", getAccountName());
        attributes.put("versionNo", getVersionNo());
        attributes.put("provisionId", getProvisionId());
        attributes.put("companyIdentifierCodeQualifier",
            getCompanyIdentifierCodeQualifier());
        attributes.put("amount", getAmount());
        attributes.put("subLedger", getSubLedger());
        attributes.put("recordLockStatus", getRecordLockStatus());
        attributes.put("itemIdentifierCodeQualifier",
            getItemIdentifierCodeQualifier());
        attributes.put("companyNo", getCompanyNo());
        attributes.put("postingStatus", getPostingStatus());
        attributes.put("itemName", getItemName());
        attributes.put("rsModelSid", getRsModelSid());
        attributes.put("itemMasterSid", getItemMasterSid());
        attributes.put("itemId", getItemId());
        attributes.put("brandMasterSid", getBrandMasterSid());
        attributes.put("glCompanyMasterSid", getGlCompanyMasterSid());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("accrualPeriodStartDate", getAccrualPeriodStartDate());
        attributes.put("subLedgerType", getSubLedgerType());
        attributes.put("programNo", getProgramNo());
        attributes.put("categoryId", getCategoryId());
        attributes.put("itemNo", getItemNo());
        attributes.put("deductionSubType", getDeductionSubType());
        attributes.put("acctIdentifierCodeQualifier",
            getAcctIdentifierCodeQualifier());
        attributes.put("contractId", getContractId());
        attributes.put("accrualId", getAccrualId());
        attributes.put("companyId", getCompanyId());
        attributes.put("accrualType", getAccrualType());
        attributes.put("brandId", getBrandId());
        attributes.put("postingDate", getPostingDate());
        attributes.put("glDate", getGlDate());
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

        Integer accrualDetailsSid = (Integer) attributes.get(
                "accrualDetailsSid");

        if (accrualDetailsSid != null) {
            setAccrualDetailsSid(accrualDetailsSid);
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

        String deductionType = (String) attributes.get("deductionType");

        if (deductionType != null) {
            setDeductionType(deductionType);
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

        Integer versionNo = (Integer) attributes.get("versionNo");

        if (versionNo != null) {
            setVersionNo(versionNo);
        }

        String provisionId = (String) attributes.get("provisionId");

        if (provisionId != null) {
            setProvisionId(provisionId);
        }

        String companyIdentifierCodeQualifier = (String) attributes.get(
                "companyIdentifierCodeQualifier");

        if (companyIdentifierCodeQualifier != null) {
            setCompanyIdentifierCodeQualifier(companyIdentifierCodeQualifier);
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

        String itemIdentifierCodeQualifier = (String) attributes.get(
                "itemIdentifierCodeQualifier");

        if (itemIdentifierCodeQualifier != null) {
            setItemIdentifierCodeQualifier(itemIdentifierCodeQualifier);
        }

        String companyNo = (String) attributes.get("companyNo");

        if (companyNo != null) {
            setCompanyNo(companyNo);
        }

        String postingStatus = (String) attributes.get("postingStatus");

        if (postingStatus != null) {
            setPostingStatus(postingStatus);
        }

        String itemName = (String) attributes.get("itemName");

        if (itemName != null) {
            setItemName(itemName);
        }

        Integer rsModelSid = (Integer) attributes.get("rsModelSid");

        if (rsModelSid != null) {
            setRsModelSid(rsModelSid);
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

        String categoryId = (String) attributes.get("categoryId");

        if (categoryId != null) {
            setCategoryId(categoryId);
        }

        String itemNo = (String) attributes.get("itemNo");

        if (itemNo != null) {
            setItemNo(itemNo);
        }

        String deductionSubType = (String) attributes.get("deductionSubType");

        if (deductionSubType != null) {
            setDeductionSubType(deductionSubType);
        }

        String acctIdentifierCodeQualifier = (String) attributes.get(
                "acctIdentifierCodeQualifier");

        if (acctIdentifierCodeQualifier != null) {
            setAcctIdentifierCodeQualifier(acctIdentifierCodeQualifier);
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

        Integer programType = (Integer) attributes.get("programType");

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

        if (_accrualDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _accrualDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setAccountId", String.class);

                method.invoke(_accrualDetailsRemoteModel, accountId);
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

        if (_accrualDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _accrualDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setRecordCreatedDate",
                        Date.class);

                method.invoke(_accrualDetailsRemoteModel, recordCreatedDate);
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

        if (_accrualDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _accrualDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setPostingIndicator",
                        String.class);

                method.invoke(_accrualDetailsRemoteModel, postingIndicator);
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

        if (_accrualDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _accrualDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setBrandName", String.class);

                method.invoke(_accrualDetailsRemoteModel, brandName);
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

        if (_accrualDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _accrualDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setAccrualPeriodEndDate",
                        Date.class);

                method.invoke(_accrualDetailsRemoteModel, accrualPeriodEndDate);
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

        if (_accrualDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _accrualDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_accrualDetailsRemoteModel, modifiedDate);
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

        if (_accrualDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _accrualDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setSalesMasterId", String.class);

                method.invoke(_accrualDetailsRemoteModel, salesMasterId);
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

        if (_accrualDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _accrualDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setSource", String.class);

                method.invoke(_accrualDetailsRemoteModel, source);
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

        if (_accrualDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _accrualDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setContractMasterSid",
                        int.class);

                method.invoke(_accrualDetailsRemoteModel, contractMasterSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getAccrualDetailsSid() {
        return _accrualDetailsSid;
    }

    @Override
    public void setAccrualDetailsSid(int accrualDetailsSid) {
        _accrualDetailsSid = accrualDetailsSid;

        if (_accrualDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _accrualDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setAccrualDetailsSid",
                        int.class);

                method.invoke(_accrualDetailsRemoteModel, accrualDetailsSid);
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

        if (_accrualDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _accrualDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setDocumentType", String.class);

                method.invoke(_accrualDetailsRemoteModel, documentType);
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

        if (_accrualDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _accrualDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setInboundStatus", String.class);

                method.invoke(_accrualDetailsRemoteModel, inboundStatus);
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

        if (_accrualDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _accrualDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", int.class);

                method.invoke(_accrualDetailsRemoteModel, modifiedBy);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getDeductionType() {
        return _deductionType;
    }

    @Override
    public void setDeductionType(String deductionType) {
        _deductionType = deductionType;

        if (_accrualDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _accrualDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setDeductionType", String.class);

                method.invoke(_accrualDetailsRemoteModel, deductionType);
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

        if (_accrualDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _accrualDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyMasterSid", int.class);

                method.invoke(_accrualDetailsRemoteModel, companyMasterSid);
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

        if (_accrualDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _accrualDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setContractName", String.class);

                method.invoke(_accrualDetailsRemoteModel, contractName);
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

        if (_accrualDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _accrualDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setAccountNo", String.class);

                method.invoke(_accrualDetailsRemoteModel, accountNo);
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

        if (_accrualDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _accrualDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setAccountName", String.class);

                method.invoke(_accrualDetailsRemoteModel, accountName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getVersionNo() {
        return _versionNo;
    }

    @Override
    public void setVersionNo(int versionNo) {
        _versionNo = versionNo;

        if (_accrualDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _accrualDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setVersionNo", int.class);

                method.invoke(_accrualDetailsRemoteModel, versionNo);
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

        if (_accrualDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _accrualDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setProvisionId", String.class);

                method.invoke(_accrualDetailsRemoteModel, provisionId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getCompanyIdentifierCodeQualifier() {
        return _companyIdentifierCodeQualifier;
    }

    @Override
    public void setCompanyIdentifierCodeQualifier(
        String companyIdentifierCodeQualifier) {
        _companyIdentifierCodeQualifier = companyIdentifierCodeQualifier;

        if (_accrualDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _accrualDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyIdentifierCodeQualifier",
                        String.class);

                method.invoke(_accrualDetailsRemoteModel,
                    companyIdentifierCodeQualifier);
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

        if (_accrualDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _accrualDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setAmount", double.class);

                method.invoke(_accrualDetailsRemoteModel, amount);
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

        if (_accrualDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _accrualDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setSubLedger", String.class);

                method.invoke(_accrualDetailsRemoteModel, subLedger);
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

        if (_accrualDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _accrualDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setRecordLockStatus",
                        boolean.class);

                method.invoke(_accrualDetailsRemoteModel, recordLockStatus);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getItemIdentifierCodeQualifier() {
        return _itemIdentifierCodeQualifier;
    }

    @Override
    public void setItemIdentifierCodeQualifier(
        String itemIdentifierCodeQualifier) {
        _itemIdentifierCodeQualifier = itemIdentifierCodeQualifier;

        if (_accrualDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _accrualDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setItemIdentifierCodeQualifier",
                        String.class);

                method.invoke(_accrualDetailsRemoteModel,
                    itemIdentifierCodeQualifier);
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

        if (_accrualDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _accrualDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyNo", String.class);

                method.invoke(_accrualDetailsRemoteModel, companyNo);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getPostingStatus() {
        return _postingStatus;
    }

    @Override
    public void setPostingStatus(String postingStatus) {
        _postingStatus = postingStatus;

        if (_accrualDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _accrualDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setPostingStatus", String.class);

                method.invoke(_accrualDetailsRemoteModel, postingStatus);
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

        if (_accrualDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _accrualDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setItemName", String.class);

                method.invoke(_accrualDetailsRemoteModel, itemName);
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

        if (_accrualDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _accrualDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setRsModelSid", int.class);

                method.invoke(_accrualDetailsRemoteModel, rsModelSid);
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

        if (_accrualDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _accrualDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setItemMasterSid", int.class);

                method.invoke(_accrualDetailsRemoteModel, itemMasterSid);
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

        if (_accrualDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _accrualDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setItemId", String.class);

                method.invoke(_accrualDetailsRemoteModel, itemId);
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

        if (_accrualDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _accrualDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setBrandMasterSid", int.class);

                method.invoke(_accrualDetailsRemoteModel, brandMasterSid);
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

        if (_accrualDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _accrualDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setGlCompanyMasterSid",
                        String.class);

                method.invoke(_accrualDetailsRemoteModel, glCompanyMasterSid);
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

        if (_accrualDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _accrualDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", int.class);

                method.invoke(_accrualDetailsRemoteModel, createdBy);
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

        if (_accrualDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _accrualDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_accrualDetailsRemoteModel, createdDate);
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

        if (_accrualDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _accrualDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setAccrualPeriodStartDate",
                        Date.class);

                method.invoke(_accrualDetailsRemoteModel, accrualPeriodStartDate);
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

        if (_accrualDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _accrualDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setSubLedgerType", String.class);

                method.invoke(_accrualDetailsRemoteModel, subLedgerType);
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

        if (_accrualDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _accrualDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setProgramNo", String.class);

                method.invoke(_accrualDetailsRemoteModel, programNo);
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

        if (_accrualDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _accrualDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCategoryId", String.class);

                method.invoke(_accrualDetailsRemoteModel, categoryId);
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

        if (_accrualDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _accrualDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setItemNo", String.class);

                method.invoke(_accrualDetailsRemoteModel, itemNo);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getDeductionSubType() {
        return _deductionSubType;
    }

    @Override
    public void setDeductionSubType(String deductionSubType) {
        _deductionSubType = deductionSubType;

        if (_accrualDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _accrualDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setDeductionSubType",
                        String.class);

                method.invoke(_accrualDetailsRemoteModel, deductionSubType);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getAcctIdentifierCodeQualifier() {
        return _acctIdentifierCodeQualifier;
    }

    @Override
    public void setAcctIdentifierCodeQualifier(
        String acctIdentifierCodeQualifier) {
        _acctIdentifierCodeQualifier = acctIdentifierCodeQualifier;

        if (_accrualDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _accrualDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setAcctIdentifierCodeQualifier",
                        String.class);

                method.invoke(_accrualDetailsRemoteModel,
                    acctIdentifierCodeQualifier);
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

        if (_accrualDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _accrualDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setContractId", String.class);

                method.invoke(_accrualDetailsRemoteModel, contractId);
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

        if (_accrualDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _accrualDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setAccrualId", String.class);

                method.invoke(_accrualDetailsRemoteModel, accrualId);
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

        if (_accrualDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _accrualDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyId", String.class);

                method.invoke(_accrualDetailsRemoteModel, companyId);
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

        if (_accrualDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _accrualDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setAccrualType", String.class);

                method.invoke(_accrualDetailsRemoteModel, accrualType);
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

        if (_accrualDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _accrualDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setBrandId", String.class);

                method.invoke(_accrualDetailsRemoteModel, brandId);
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

        if (_accrualDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _accrualDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setPostingDate", Date.class);

                method.invoke(_accrualDetailsRemoteModel, postingDate);
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

        if (_accrualDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _accrualDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setGlDate", Date.class);

                method.invoke(_accrualDetailsRemoteModel, glDate);
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

        if (_accrualDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _accrualDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyCostCenter",
                        String.class);

                method.invoke(_accrualDetailsRemoteModel, companyCostCenter);
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

        if (_accrualDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _accrualDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setGlAccount", String.class);

                method.invoke(_accrualDetailsRemoteModel, glAccount);
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

        if (_accrualDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _accrualDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setBatchId", String.class);

                method.invoke(_accrualDetailsRemoteModel, batchId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getProgramType() {
        return _programType;
    }

    @Override
    public void setProgramType(int programType) {
        _programType = programType;

        if (_accrualDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _accrualDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setProgramType", int.class);

                method.invoke(_accrualDetailsRemoteModel, programType);
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

        if (_accrualDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _accrualDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setContractNo", String.class);

                method.invoke(_accrualDetailsRemoteModel, contractNo);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getAccrualDetailsRemoteModel() {
        return _accrualDetailsRemoteModel;
    }

    public void setAccrualDetailsRemoteModel(
        BaseModel<?> accrualDetailsRemoteModel) {
        _accrualDetailsRemoteModel = accrualDetailsRemoteModel;
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

        Class<?> remoteModelClass = _accrualDetailsRemoteModel.getClass();

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

        Object returnValue = method.invoke(_accrualDetailsRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            AccrualDetailsLocalServiceUtil.addAccrualDetails(this);
        } else {
            AccrualDetailsLocalServiceUtil.updateAccrualDetails(this);
        }
    }

    @Override
    public AccrualDetails toEscapedModel() {
        return (AccrualDetails) ProxyUtil.newProxyInstance(AccrualDetails.class.getClassLoader(),
            new Class[] { AccrualDetails.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        AccrualDetailsClp clone = new AccrualDetailsClp();

        clone.setAccountId(getAccountId());
        clone.setRecordCreatedDate(getRecordCreatedDate());
        clone.setPostingIndicator(getPostingIndicator());
        clone.setBrandName(getBrandName());
        clone.setAccrualPeriodEndDate(getAccrualPeriodEndDate());
        clone.setModifiedDate(getModifiedDate());
        clone.setSalesMasterId(getSalesMasterId());
        clone.setSource(getSource());
        clone.setContractMasterSid(getContractMasterSid());
        clone.setAccrualDetailsSid(getAccrualDetailsSid());
        clone.setDocumentType(getDocumentType());
        clone.setInboundStatus(getInboundStatus());
        clone.setModifiedBy(getModifiedBy());
        clone.setDeductionType(getDeductionType());
        clone.setCompanyMasterSid(getCompanyMasterSid());
        clone.setContractName(getContractName());
        clone.setAccountNo(getAccountNo());
        clone.setAccountName(getAccountName());
        clone.setVersionNo(getVersionNo());
        clone.setProvisionId(getProvisionId());
        clone.setCompanyIdentifierCodeQualifier(getCompanyIdentifierCodeQualifier());
        clone.setAmount(getAmount());
        clone.setSubLedger(getSubLedger());
        clone.setRecordLockStatus(getRecordLockStatus());
        clone.setItemIdentifierCodeQualifier(getItemIdentifierCodeQualifier());
        clone.setCompanyNo(getCompanyNo());
        clone.setPostingStatus(getPostingStatus());
        clone.setItemName(getItemName());
        clone.setRsModelSid(getRsModelSid());
        clone.setItemMasterSid(getItemMasterSid());
        clone.setItemId(getItemId());
        clone.setBrandMasterSid(getBrandMasterSid());
        clone.setGlCompanyMasterSid(getGlCompanyMasterSid());
        clone.setCreatedBy(getCreatedBy());
        clone.setCreatedDate(getCreatedDate());
        clone.setAccrualPeriodStartDate(getAccrualPeriodStartDate());
        clone.setSubLedgerType(getSubLedgerType());
        clone.setProgramNo(getProgramNo());
        clone.setCategoryId(getCategoryId());
        clone.setItemNo(getItemNo());
        clone.setDeductionSubType(getDeductionSubType());
        clone.setAcctIdentifierCodeQualifier(getAcctIdentifierCodeQualifier());
        clone.setContractId(getContractId());
        clone.setAccrualId(getAccrualId());
        clone.setCompanyId(getCompanyId());
        clone.setAccrualType(getAccrualType());
        clone.setBrandId(getBrandId());
        clone.setPostingDate(getPostingDate());
        clone.setGlDate(getGlDate());
        clone.setCompanyCostCenter(getCompanyCostCenter());
        clone.setGlAccount(getGlAccount());
        clone.setBatchId(getBatchId());
        clone.setProgramType(getProgramType());
        clone.setContractNo(getContractNo());

        return clone;
    }

    @Override
    public int compareTo(AccrualDetails accrualDetails) {
        int primaryKey = accrualDetails.getPrimaryKey();

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

        if (!(obj instanceof AccrualDetailsClp)) {
            return false;
        }

        AccrualDetailsClp accrualDetails = (AccrualDetailsClp) obj;

        int primaryKey = accrualDetails.getPrimaryKey();

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
        StringBundler sb = new StringBundler(109);

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
        sb.append(", accrualDetailsSid=");
        sb.append(getAccrualDetailsSid());
        sb.append(", documentType=");
        sb.append(getDocumentType());
        sb.append(", inboundStatus=");
        sb.append(getInboundStatus());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", deductionType=");
        sb.append(getDeductionType());
        sb.append(", companyMasterSid=");
        sb.append(getCompanyMasterSid());
        sb.append(", contractName=");
        sb.append(getContractName());
        sb.append(", accountNo=");
        sb.append(getAccountNo());
        sb.append(", accountName=");
        sb.append(getAccountName());
        sb.append(", versionNo=");
        sb.append(getVersionNo());
        sb.append(", provisionId=");
        sb.append(getProvisionId());
        sb.append(", companyIdentifierCodeQualifier=");
        sb.append(getCompanyIdentifierCodeQualifier());
        sb.append(", amount=");
        sb.append(getAmount());
        sb.append(", subLedger=");
        sb.append(getSubLedger());
        sb.append(", recordLockStatus=");
        sb.append(getRecordLockStatus());
        sb.append(", itemIdentifierCodeQualifier=");
        sb.append(getItemIdentifierCodeQualifier());
        sb.append(", companyNo=");
        sb.append(getCompanyNo());
        sb.append(", postingStatus=");
        sb.append(getPostingStatus());
        sb.append(", itemName=");
        sb.append(getItemName());
        sb.append(", rsModelSid=");
        sb.append(getRsModelSid());
        sb.append(", itemMasterSid=");
        sb.append(getItemMasterSid());
        sb.append(", itemId=");
        sb.append(getItemId());
        sb.append(", brandMasterSid=");
        sb.append(getBrandMasterSid());
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
        sb.append(", categoryId=");
        sb.append(getCategoryId());
        sb.append(", itemNo=");
        sb.append(getItemNo());
        sb.append(", deductionSubType=");
        sb.append(getDeductionSubType());
        sb.append(", acctIdentifierCodeQualifier=");
        sb.append(getAcctIdentifierCodeQualifier());
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
        StringBundler sb = new StringBundler(166);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.AccrualDetails");
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
            "<column><column-name>accrualDetailsSid</column-name><column-value><![CDATA[");
        sb.append(getAccrualDetailsSid());
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
            "<column><column-name>deductionType</column-name><column-value><![CDATA[");
        sb.append(getDeductionType());
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
            "<column><column-name>versionNo</column-name><column-value><![CDATA[");
        sb.append(getVersionNo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>provisionId</column-name><column-value><![CDATA[");
        sb.append(getProvisionId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>companyIdentifierCodeQualifier</column-name><column-value><![CDATA[");
        sb.append(getCompanyIdentifierCodeQualifier());
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
            "<column><column-name>itemIdentifierCodeQualifier</column-name><column-value><![CDATA[");
        sb.append(getItemIdentifierCodeQualifier());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>companyNo</column-name><column-value><![CDATA[");
        sb.append(getCompanyNo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>postingStatus</column-name><column-value><![CDATA[");
        sb.append(getPostingStatus());
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
            "<column><column-name>categoryId</column-name><column-value><![CDATA[");
        sb.append(getCategoryId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemNo</column-name><column-value><![CDATA[");
        sb.append(getItemNo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>deductionSubType</column-name><column-value><![CDATA[");
        sb.append(getDeductionSubType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>acctIdentifierCodeQualifier</column-name><column-value><![CDATA[");
        sb.append(getAcctIdentifierCodeQualifier());
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
