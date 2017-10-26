package com.stpl.app.parttwo.model;

import com.stpl.app.parttwo.service.ClpSerializer;
import com.stpl.app.parttwo.service.IvldAccrualInboundLocalServiceUtil;

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


public class IvldAccrualInboundClp extends BaseModelImpl<IvldAccrualInbound>
    implements IvldAccrualInbound {
    private String _recordCreatedDate;
    private String _accountId;
    private String _postingIndicator;
    private String _itemId;
    private Date _modifiedDate;
    private String _accrualPeriodEndDate;
    private String _itemIdentCodeQualifier;
    private String _glCompanyMasterSid;
    private String _salesMasterId;
    private Date _createdDate;
    private String _createdBy;
    private String _source;
    private String _accrualPeriodStartDate;
    private String _addChgDelIndicator;
    private String _subLedgerType;
    private String _programNo;
    private String _documentType;
    private String _accrualIntfid;
    private String _glCompanyName;
    private String _errorCode;
    private Date _intfInsertedDate;
    private String _modifiedBy;
    private String _categoryId;
    private String _itemNo;
    private String _reprocessedFlag;
    private String _compIdentCodeQualifier;
    private String _acctIdentCodeQualifier;
    private String _contractId;
    private String _accountNo;
    private String _accrualId;
    private String _reasonForFailure;
    private String _companyId;
    private String _accountName;
    private String _accrualType;
    private String _postingDate;
    private String _brandId;
    private String _provisionId;
    private String _amount;
    private String _glDate;
    private String _subLedger;
    private String _companyCostCenter;
    private String _glAccount;
    private String _companyNo;
    private String _batchId;
    private String _programType;
    private String _itemName;
    private String _errorField;
    private int _ivldAccrualInboundSid;
    private String _contractNo;
    private String _brandName;
    private String _contractName;
    private boolean _checkRecord;
    private BaseModel<?> _ivldAccrualInboundRemoteModel;

    public IvldAccrualInboundClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return IvldAccrualInbound.class;
    }

    @Override
    public String getModelClassName() {
        return IvldAccrualInbound.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _ivldAccrualInboundSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setIvldAccrualInboundSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _ivldAccrualInboundSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("recordCreatedDate", getRecordCreatedDate());
        attributes.put("accountId", getAccountId());
        attributes.put("postingIndicator", getPostingIndicator());
        attributes.put("itemId", getItemId());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("accrualPeriodEndDate", getAccrualPeriodEndDate());
        attributes.put("itemIdentCodeQualifier", getItemIdentCodeQualifier());
        attributes.put("glCompanyMasterSid", getGlCompanyMasterSid());
        attributes.put("salesMasterId", getSalesMasterId());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("source", getSource());
        attributes.put("accrualPeriodStartDate", getAccrualPeriodStartDate());
        attributes.put("addChgDelIndicator", getAddChgDelIndicator());
        attributes.put("subLedgerType", getSubLedgerType());
        attributes.put("programNo", getProgramNo());
        attributes.put("documentType", getDocumentType());
        attributes.put("accrualIntfid", getAccrualIntfid());
        attributes.put("glCompanyName", getGlCompanyName());
        attributes.put("errorCode", getErrorCode());
        attributes.put("intfInsertedDate", getIntfInsertedDate());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("categoryId", getCategoryId());
        attributes.put("itemNo", getItemNo());
        attributes.put("reprocessedFlag", getReprocessedFlag());
        attributes.put("compIdentCodeQualifier", getCompIdentCodeQualifier());
        attributes.put("acctIdentCodeQualifier", getAcctIdentCodeQualifier());
        attributes.put("contractId", getContractId());
        attributes.put("accountNo", getAccountNo());
        attributes.put("accrualId", getAccrualId());
        attributes.put("reasonForFailure", getReasonForFailure());
        attributes.put("companyId", getCompanyId());
        attributes.put("accountName", getAccountName());
        attributes.put("accrualType", getAccrualType());
        attributes.put("postingDate", getPostingDate());
        attributes.put("brandId", getBrandId());
        attributes.put("provisionId", getProvisionId());
        attributes.put("amount", getAmount());
        attributes.put("glDate", getGlDate());
        attributes.put("subLedger", getSubLedger());
        attributes.put("companyCostCenter", getCompanyCostCenter());
        attributes.put("glAccount", getGlAccount());
        attributes.put("companyNo", getCompanyNo());
        attributes.put("batchId", getBatchId());
        attributes.put("programType", getProgramType());
        attributes.put("itemName", getItemName());
        attributes.put("errorField", getErrorField());
        attributes.put("ivldAccrualInboundSid", getIvldAccrualInboundSid());
        attributes.put("contractNo", getContractNo());
        attributes.put("brandName", getBrandName());
        attributes.put("contractName", getContractName());
        attributes.put("checkRecord", getCheckRecord());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String recordCreatedDate = (String) attributes.get("recordCreatedDate");

        if (recordCreatedDate != null) {
            setRecordCreatedDate(recordCreatedDate);
        }

        String accountId = (String) attributes.get("accountId");

        if (accountId != null) {
            setAccountId(accountId);
        }

        String postingIndicator = (String) attributes.get("postingIndicator");

        if (postingIndicator != null) {
            setPostingIndicator(postingIndicator);
        }

        String itemId = (String) attributes.get("itemId");

        if (itemId != null) {
            setItemId(itemId);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        String accrualPeriodEndDate = (String) attributes.get(
                "accrualPeriodEndDate");

        if (accrualPeriodEndDate != null) {
            setAccrualPeriodEndDate(accrualPeriodEndDate);
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

        String salesMasterId = (String) attributes.get("salesMasterId");

        if (salesMasterId != null) {
            setSalesMasterId(salesMasterId);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        String createdBy = (String) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        String source = (String) attributes.get("source");

        if (source != null) {
            setSource(source);
        }

        String accrualPeriodStartDate = (String) attributes.get(
                "accrualPeriodStartDate");

        if (accrualPeriodStartDate != null) {
            setAccrualPeriodStartDate(accrualPeriodStartDate);
        }

        String addChgDelIndicator = (String) attributes.get(
                "addChgDelIndicator");

        if (addChgDelIndicator != null) {
            setAddChgDelIndicator(addChgDelIndicator);
        }

        String subLedgerType = (String) attributes.get("subLedgerType");

        if (subLedgerType != null) {
            setSubLedgerType(subLedgerType);
        }

        String programNo = (String) attributes.get("programNo");

        if (programNo != null) {
            setProgramNo(programNo);
        }

        String documentType = (String) attributes.get("documentType");

        if (documentType != null) {
            setDocumentType(documentType);
        }

        String accrualIntfid = (String) attributes.get("accrualIntfid");

        if (accrualIntfid != null) {
            setAccrualIntfid(accrualIntfid);
        }

        String glCompanyName = (String) attributes.get("glCompanyName");

        if (glCompanyName != null) {
            setGlCompanyName(glCompanyName);
        }

        String errorCode = (String) attributes.get("errorCode");

        if (errorCode != null) {
            setErrorCode(errorCode);
        }

        Date intfInsertedDate = (Date) attributes.get("intfInsertedDate");

        if (intfInsertedDate != null) {
            setIntfInsertedDate(intfInsertedDate);
        }

        String modifiedBy = (String) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        String categoryId = (String) attributes.get("categoryId");

        if (categoryId != null) {
            setCategoryId(categoryId);
        }

        String itemNo = (String) attributes.get("itemNo");

        if (itemNo != null) {
            setItemNo(itemNo);
        }

        String reprocessedFlag = (String) attributes.get("reprocessedFlag");

        if (reprocessedFlag != null) {
            setReprocessedFlag(reprocessedFlag);
        }

        String compIdentCodeQualifier = (String) attributes.get(
                "compIdentCodeQualifier");

        if (compIdentCodeQualifier != null) {
            setCompIdentCodeQualifier(compIdentCodeQualifier);
        }

        String acctIdentCodeQualifier = (String) attributes.get(
                "acctIdentCodeQualifier");

        if (acctIdentCodeQualifier != null) {
            setAcctIdentCodeQualifier(acctIdentCodeQualifier);
        }

        String contractId = (String) attributes.get("contractId");

        if (contractId != null) {
            setContractId(contractId);
        }

        String accountNo = (String) attributes.get("accountNo");

        if (accountNo != null) {
            setAccountNo(accountNo);
        }

        String accrualId = (String) attributes.get("accrualId");

        if (accrualId != null) {
            setAccrualId(accrualId);
        }

        String reasonForFailure = (String) attributes.get("reasonForFailure");

        if (reasonForFailure != null) {
            setReasonForFailure(reasonForFailure);
        }

        String companyId = (String) attributes.get("companyId");

        if (companyId != null) {
            setCompanyId(companyId);
        }

        String accountName = (String) attributes.get("accountName");

        if (accountName != null) {
            setAccountName(accountName);
        }

        String accrualType = (String) attributes.get("accrualType");

        if (accrualType != null) {
            setAccrualType(accrualType);
        }

        String postingDate = (String) attributes.get("postingDate");

        if (postingDate != null) {
            setPostingDate(postingDate);
        }

        String brandId = (String) attributes.get("brandId");

        if (brandId != null) {
            setBrandId(brandId);
        }

        String provisionId = (String) attributes.get("provisionId");

        if (provisionId != null) {
            setProvisionId(provisionId);
        }

        String amount = (String) attributes.get("amount");

        if (amount != null) {
            setAmount(amount);
        }

        String glDate = (String) attributes.get("glDate");

        if (glDate != null) {
            setGlDate(glDate);
        }

        String subLedger = (String) attributes.get("subLedger");

        if (subLedger != null) {
            setSubLedger(subLedger);
        }

        String companyCostCenter = (String) attributes.get("companyCostCenter");

        if (companyCostCenter != null) {
            setCompanyCostCenter(companyCostCenter);
        }

        String glAccount = (String) attributes.get("glAccount");

        if (glAccount != null) {
            setGlAccount(glAccount);
        }

        String companyNo = (String) attributes.get("companyNo");

        if (companyNo != null) {
            setCompanyNo(companyNo);
        }

        String batchId = (String) attributes.get("batchId");

        if (batchId != null) {
            setBatchId(batchId);
        }

        String programType = (String) attributes.get("programType");

        if (programType != null) {
            setProgramType(programType);
        }

        String itemName = (String) attributes.get("itemName");

        if (itemName != null) {
            setItemName(itemName);
        }

        String errorField = (String) attributes.get("errorField");

        if (errorField != null) {
            setErrorField(errorField);
        }

        Integer ivldAccrualInboundSid = (Integer) attributes.get(
                "ivldAccrualInboundSid");

        if (ivldAccrualInboundSid != null) {
            setIvldAccrualInboundSid(ivldAccrualInboundSid);
        }

        String contractNo = (String) attributes.get("contractNo");

        if (contractNo != null) {
            setContractNo(contractNo);
        }

        String brandName = (String) attributes.get("brandName");

        if (brandName != null) {
            setBrandName(brandName);
        }

        String contractName = (String) attributes.get("contractName");

        if (contractName != null) {
            setContractName(contractName);
        }

        Boolean checkRecord = (Boolean) attributes.get("checkRecord");

        if (checkRecord != null) {
            setCheckRecord(checkRecord);
        }
    }

    @Override
    public String getRecordCreatedDate() {
        return _recordCreatedDate;
    }

    @Override
    public void setRecordCreatedDate(String recordCreatedDate) {
        _recordCreatedDate = recordCreatedDate;

        if (_ivldAccrualInboundRemoteModel != null) {
            try {
                Class<?> clazz = _ivldAccrualInboundRemoteModel.getClass();

                Method method = clazz.getMethod("setRecordCreatedDate",
                        String.class);

                method.invoke(_ivldAccrualInboundRemoteModel, recordCreatedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getAccountId() {
        return _accountId;
    }

    @Override
    public void setAccountId(String accountId) {
        _accountId = accountId;

        if (_ivldAccrualInboundRemoteModel != null) {
            try {
                Class<?> clazz = _ivldAccrualInboundRemoteModel.getClass();

                Method method = clazz.getMethod("setAccountId", String.class);

                method.invoke(_ivldAccrualInboundRemoteModel, accountId);
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

        if (_ivldAccrualInboundRemoteModel != null) {
            try {
                Class<?> clazz = _ivldAccrualInboundRemoteModel.getClass();

                Method method = clazz.getMethod("setPostingIndicator",
                        String.class);

                method.invoke(_ivldAccrualInboundRemoteModel, postingIndicator);
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

        if (_ivldAccrualInboundRemoteModel != null) {
            try {
                Class<?> clazz = _ivldAccrualInboundRemoteModel.getClass();

                Method method = clazz.getMethod("setItemId", String.class);

                method.invoke(_ivldAccrualInboundRemoteModel, itemId);
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

        if (_ivldAccrualInboundRemoteModel != null) {
            try {
                Class<?> clazz = _ivldAccrualInboundRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_ivldAccrualInboundRemoteModel, modifiedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getAccrualPeriodEndDate() {
        return _accrualPeriodEndDate;
    }

    @Override
    public void setAccrualPeriodEndDate(String accrualPeriodEndDate) {
        _accrualPeriodEndDate = accrualPeriodEndDate;

        if (_ivldAccrualInboundRemoteModel != null) {
            try {
                Class<?> clazz = _ivldAccrualInboundRemoteModel.getClass();

                Method method = clazz.getMethod("setAccrualPeriodEndDate",
                        String.class);

                method.invoke(_ivldAccrualInboundRemoteModel,
                    accrualPeriodEndDate);
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

        if (_ivldAccrualInboundRemoteModel != null) {
            try {
                Class<?> clazz = _ivldAccrualInboundRemoteModel.getClass();

                Method method = clazz.getMethod("setItemIdentCodeQualifier",
                        String.class);

                method.invoke(_ivldAccrualInboundRemoteModel,
                    itemIdentCodeQualifier);
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

        if (_ivldAccrualInboundRemoteModel != null) {
            try {
                Class<?> clazz = _ivldAccrualInboundRemoteModel.getClass();

                Method method = clazz.getMethod("setGlCompanyMasterSid",
                        String.class);

                method.invoke(_ivldAccrualInboundRemoteModel, glCompanyMasterSid);
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

        if (_ivldAccrualInboundRemoteModel != null) {
            try {
                Class<?> clazz = _ivldAccrualInboundRemoteModel.getClass();

                Method method = clazz.getMethod("setSalesMasterId", String.class);

                method.invoke(_ivldAccrualInboundRemoteModel, salesMasterId);
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

        if (_ivldAccrualInboundRemoteModel != null) {
            try {
                Class<?> clazz = _ivldAccrualInboundRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_ivldAccrualInboundRemoteModel, createdDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getCreatedBy() {
        return _createdBy;
    }

    @Override
    public void setCreatedBy(String createdBy) {
        _createdBy = createdBy;

        if (_ivldAccrualInboundRemoteModel != null) {
            try {
                Class<?> clazz = _ivldAccrualInboundRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", String.class);

                method.invoke(_ivldAccrualInboundRemoteModel, createdBy);
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

        if (_ivldAccrualInboundRemoteModel != null) {
            try {
                Class<?> clazz = _ivldAccrualInboundRemoteModel.getClass();

                Method method = clazz.getMethod("setSource", String.class);

                method.invoke(_ivldAccrualInboundRemoteModel, source);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getAccrualPeriodStartDate() {
        return _accrualPeriodStartDate;
    }

    @Override
    public void setAccrualPeriodStartDate(String accrualPeriodStartDate) {
        _accrualPeriodStartDate = accrualPeriodStartDate;

        if (_ivldAccrualInboundRemoteModel != null) {
            try {
                Class<?> clazz = _ivldAccrualInboundRemoteModel.getClass();

                Method method = clazz.getMethod("setAccrualPeriodStartDate",
                        String.class);

                method.invoke(_ivldAccrualInboundRemoteModel,
                    accrualPeriodStartDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getAddChgDelIndicator() {
        return _addChgDelIndicator;
    }

    @Override
    public void setAddChgDelIndicator(String addChgDelIndicator) {
        _addChgDelIndicator = addChgDelIndicator;

        if (_ivldAccrualInboundRemoteModel != null) {
            try {
                Class<?> clazz = _ivldAccrualInboundRemoteModel.getClass();

                Method method = clazz.getMethod("setAddChgDelIndicator",
                        String.class);

                method.invoke(_ivldAccrualInboundRemoteModel, addChgDelIndicator);
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

        if (_ivldAccrualInboundRemoteModel != null) {
            try {
                Class<?> clazz = _ivldAccrualInboundRemoteModel.getClass();

                Method method = clazz.getMethod("setSubLedgerType", String.class);

                method.invoke(_ivldAccrualInboundRemoteModel, subLedgerType);
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

        if (_ivldAccrualInboundRemoteModel != null) {
            try {
                Class<?> clazz = _ivldAccrualInboundRemoteModel.getClass();

                Method method = clazz.getMethod("setProgramNo", String.class);

                method.invoke(_ivldAccrualInboundRemoteModel, programNo);
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

        if (_ivldAccrualInboundRemoteModel != null) {
            try {
                Class<?> clazz = _ivldAccrualInboundRemoteModel.getClass();

                Method method = clazz.getMethod("setDocumentType", String.class);

                method.invoke(_ivldAccrualInboundRemoteModel, documentType);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getAccrualIntfid() {
        return _accrualIntfid;
    }

    @Override
    public void setAccrualIntfid(String accrualIntfid) {
        _accrualIntfid = accrualIntfid;

        if (_ivldAccrualInboundRemoteModel != null) {
            try {
                Class<?> clazz = _ivldAccrualInboundRemoteModel.getClass();

                Method method = clazz.getMethod("setAccrualIntfid", String.class);

                method.invoke(_ivldAccrualInboundRemoteModel, accrualIntfid);
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

        if (_ivldAccrualInboundRemoteModel != null) {
            try {
                Class<?> clazz = _ivldAccrualInboundRemoteModel.getClass();

                Method method = clazz.getMethod("setGlCompanyName", String.class);

                method.invoke(_ivldAccrualInboundRemoteModel, glCompanyName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getErrorCode() {
        return _errorCode;
    }

    @Override
    public void setErrorCode(String errorCode) {
        _errorCode = errorCode;

        if (_ivldAccrualInboundRemoteModel != null) {
            try {
                Class<?> clazz = _ivldAccrualInboundRemoteModel.getClass();

                Method method = clazz.getMethod("setErrorCode", String.class);

                method.invoke(_ivldAccrualInboundRemoteModel, errorCode);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getIntfInsertedDate() {
        return _intfInsertedDate;
    }

    @Override
    public void setIntfInsertedDate(Date intfInsertedDate) {
        _intfInsertedDate = intfInsertedDate;

        if (_ivldAccrualInboundRemoteModel != null) {
            try {
                Class<?> clazz = _ivldAccrualInboundRemoteModel.getClass();

                Method method = clazz.getMethod("setIntfInsertedDate",
                        Date.class);

                method.invoke(_ivldAccrualInboundRemoteModel, intfInsertedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getModifiedBy() {
        return _modifiedBy;
    }

    @Override
    public void setModifiedBy(String modifiedBy) {
        _modifiedBy = modifiedBy;

        if (_ivldAccrualInboundRemoteModel != null) {
            try {
                Class<?> clazz = _ivldAccrualInboundRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", String.class);

                method.invoke(_ivldAccrualInboundRemoteModel, modifiedBy);
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

        if (_ivldAccrualInboundRemoteModel != null) {
            try {
                Class<?> clazz = _ivldAccrualInboundRemoteModel.getClass();

                Method method = clazz.getMethod("setCategoryId", String.class);

                method.invoke(_ivldAccrualInboundRemoteModel, categoryId);
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

        if (_ivldAccrualInboundRemoteModel != null) {
            try {
                Class<?> clazz = _ivldAccrualInboundRemoteModel.getClass();

                Method method = clazz.getMethod("setItemNo", String.class);

                method.invoke(_ivldAccrualInboundRemoteModel, itemNo);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getReprocessedFlag() {
        return _reprocessedFlag;
    }

    @Override
    public void setReprocessedFlag(String reprocessedFlag) {
        _reprocessedFlag = reprocessedFlag;

        if (_ivldAccrualInboundRemoteModel != null) {
            try {
                Class<?> clazz = _ivldAccrualInboundRemoteModel.getClass();

                Method method = clazz.getMethod("setReprocessedFlag",
                        String.class);

                method.invoke(_ivldAccrualInboundRemoteModel, reprocessedFlag);
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

        if (_ivldAccrualInboundRemoteModel != null) {
            try {
                Class<?> clazz = _ivldAccrualInboundRemoteModel.getClass();

                Method method = clazz.getMethod("setCompIdentCodeQualifier",
                        String.class);

                method.invoke(_ivldAccrualInboundRemoteModel,
                    compIdentCodeQualifier);
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

        if (_ivldAccrualInboundRemoteModel != null) {
            try {
                Class<?> clazz = _ivldAccrualInboundRemoteModel.getClass();

                Method method = clazz.getMethod("setAcctIdentCodeQualifier",
                        String.class);

                method.invoke(_ivldAccrualInboundRemoteModel,
                    acctIdentCodeQualifier);
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

        if (_ivldAccrualInboundRemoteModel != null) {
            try {
                Class<?> clazz = _ivldAccrualInboundRemoteModel.getClass();

                Method method = clazz.getMethod("setContractId", String.class);

                method.invoke(_ivldAccrualInboundRemoteModel, contractId);
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

        if (_ivldAccrualInboundRemoteModel != null) {
            try {
                Class<?> clazz = _ivldAccrualInboundRemoteModel.getClass();

                Method method = clazz.getMethod("setAccountNo", String.class);

                method.invoke(_ivldAccrualInboundRemoteModel, accountNo);
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

        if (_ivldAccrualInboundRemoteModel != null) {
            try {
                Class<?> clazz = _ivldAccrualInboundRemoteModel.getClass();

                Method method = clazz.getMethod("setAccrualId", String.class);

                method.invoke(_ivldAccrualInboundRemoteModel, accrualId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getReasonForFailure() {
        return _reasonForFailure;
    }

    @Override
    public void setReasonForFailure(String reasonForFailure) {
        _reasonForFailure = reasonForFailure;

        if (_ivldAccrualInboundRemoteModel != null) {
            try {
                Class<?> clazz = _ivldAccrualInboundRemoteModel.getClass();

                Method method = clazz.getMethod("setReasonForFailure",
                        String.class);

                method.invoke(_ivldAccrualInboundRemoteModel, reasonForFailure);
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

        if (_ivldAccrualInboundRemoteModel != null) {
            try {
                Class<?> clazz = _ivldAccrualInboundRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyId", String.class);

                method.invoke(_ivldAccrualInboundRemoteModel, companyId);
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

        if (_ivldAccrualInboundRemoteModel != null) {
            try {
                Class<?> clazz = _ivldAccrualInboundRemoteModel.getClass();

                Method method = clazz.getMethod("setAccountName", String.class);

                method.invoke(_ivldAccrualInboundRemoteModel, accountName);
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

        if (_ivldAccrualInboundRemoteModel != null) {
            try {
                Class<?> clazz = _ivldAccrualInboundRemoteModel.getClass();

                Method method = clazz.getMethod("setAccrualType", String.class);

                method.invoke(_ivldAccrualInboundRemoteModel, accrualType);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getPostingDate() {
        return _postingDate;
    }

    @Override
    public void setPostingDate(String postingDate) {
        _postingDate = postingDate;

        if (_ivldAccrualInboundRemoteModel != null) {
            try {
                Class<?> clazz = _ivldAccrualInboundRemoteModel.getClass();

                Method method = clazz.getMethod("setPostingDate", String.class);

                method.invoke(_ivldAccrualInboundRemoteModel, postingDate);
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

        if (_ivldAccrualInboundRemoteModel != null) {
            try {
                Class<?> clazz = _ivldAccrualInboundRemoteModel.getClass();

                Method method = clazz.getMethod("setBrandId", String.class);

                method.invoke(_ivldAccrualInboundRemoteModel, brandId);
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

        if (_ivldAccrualInboundRemoteModel != null) {
            try {
                Class<?> clazz = _ivldAccrualInboundRemoteModel.getClass();

                Method method = clazz.getMethod("setProvisionId", String.class);

                method.invoke(_ivldAccrualInboundRemoteModel, provisionId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getAmount() {
        return _amount;
    }

    @Override
    public void setAmount(String amount) {
        _amount = amount;

        if (_ivldAccrualInboundRemoteModel != null) {
            try {
                Class<?> clazz = _ivldAccrualInboundRemoteModel.getClass();

                Method method = clazz.getMethod("setAmount", String.class);

                method.invoke(_ivldAccrualInboundRemoteModel, amount);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getGlDate() {
        return _glDate;
    }

    @Override
    public void setGlDate(String glDate) {
        _glDate = glDate;

        if (_ivldAccrualInboundRemoteModel != null) {
            try {
                Class<?> clazz = _ivldAccrualInboundRemoteModel.getClass();

                Method method = clazz.getMethod("setGlDate", String.class);

                method.invoke(_ivldAccrualInboundRemoteModel, glDate);
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

        if (_ivldAccrualInboundRemoteModel != null) {
            try {
                Class<?> clazz = _ivldAccrualInboundRemoteModel.getClass();

                Method method = clazz.getMethod("setSubLedger", String.class);

                method.invoke(_ivldAccrualInboundRemoteModel, subLedger);
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

        if (_ivldAccrualInboundRemoteModel != null) {
            try {
                Class<?> clazz = _ivldAccrualInboundRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyCostCenter",
                        String.class);

                method.invoke(_ivldAccrualInboundRemoteModel, companyCostCenter);
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

        if (_ivldAccrualInboundRemoteModel != null) {
            try {
                Class<?> clazz = _ivldAccrualInboundRemoteModel.getClass();

                Method method = clazz.getMethod("setGlAccount", String.class);

                method.invoke(_ivldAccrualInboundRemoteModel, glAccount);
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

        if (_ivldAccrualInboundRemoteModel != null) {
            try {
                Class<?> clazz = _ivldAccrualInboundRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyNo", String.class);

                method.invoke(_ivldAccrualInboundRemoteModel, companyNo);
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

        if (_ivldAccrualInboundRemoteModel != null) {
            try {
                Class<?> clazz = _ivldAccrualInboundRemoteModel.getClass();

                Method method = clazz.getMethod("setBatchId", String.class);

                method.invoke(_ivldAccrualInboundRemoteModel, batchId);
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

        if (_ivldAccrualInboundRemoteModel != null) {
            try {
                Class<?> clazz = _ivldAccrualInboundRemoteModel.getClass();

                Method method = clazz.getMethod("setProgramType", String.class);

                method.invoke(_ivldAccrualInboundRemoteModel, programType);
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

        if (_ivldAccrualInboundRemoteModel != null) {
            try {
                Class<?> clazz = _ivldAccrualInboundRemoteModel.getClass();

                Method method = clazz.getMethod("setItemName", String.class);

                method.invoke(_ivldAccrualInboundRemoteModel, itemName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getErrorField() {
        return _errorField;
    }

    @Override
    public void setErrorField(String errorField) {
        _errorField = errorField;

        if (_ivldAccrualInboundRemoteModel != null) {
            try {
                Class<?> clazz = _ivldAccrualInboundRemoteModel.getClass();

                Method method = clazz.getMethod("setErrorField", String.class);

                method.invoke(_ivldAccrualInboundRemoteModel, errorField);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getIvldAccrualInboundSid() {
        return _ivldAccrualInboundSid;
    }

    @Override
    public void setIvldAccrualInboundSid(int ivldAccrualInboundSid) {
        _ivldAccrualInboundSid = ivldAccrualInboundSid;

        if (_ivldAccrualInboundRemoteModel != null) {
            try {
                Class<?> clazz = _ivldAccrualInboundRemoteModel.getClass();

                Method method = clazz.getMethod("setIvldAccrualInboundSid",
                        int.class);

                method.invoke(_ivldAccrualInboundRemoteModel,
                    ivldAccrualInboundSid);
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

        if (_ivldAccrualInboundRemoteModel != null) {
            try {
                Class<?> clazz = _ivldAccrualInboundRemoteModel.getClass();

                Method method = clazz.getMethod("setContractNo", String.class);

                method.invoke(_ivldAccrualInboundRemoteModel, contractNo);
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

        if (_ivldAccrualInboundRemoteModel != null) {
            try {
                Class<?> clazz = _ivldAccrualInboundRemoteModel.getClass();

                Method method = clazz.getMethod("setBrandName", String.class);

                method.invoke(_ivldAccrualInboundRemoteModel, brandName);
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

        if (_ivldAccrualInboundRemoteModel != null) {
            try {
                Class<?> clazz = _ivldAccrualInboundRemoteModel.getClass();

                Method method = clazz.getMethod("setContractName", String.class);

                method.invoke(_ivldAccrualInboundRemoteModel, contractName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public boolean getCheckRecord() {
        return _checkRecord;
    }

    @Override
    public boolean isCheckRecord() {
        return _checkRecord;
    }

    @Override
    public void setCheckRecord(boolean checkRecord) {
        _checkRecord = checkRecord;

        if (_ivldAccrualInboundRemoteModel != null) {
            try {
                Class<?> clazz = _ivldAccrualInboundRemoteModel.getClass();

                Method method = clazz.getMethod("setCheckRecord", boolean.class);

                method.invoke(_ivldAccrualInboundRemoteModel, checkRecord);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getIvldAccrualInboundRemoteModel() {
        return _ivldAccrualInboundRemoteModel;
    }

    public void setIvldAccrualInboundRemoteModel(
        BaseModel<?> ivldAccrualInboundRemoteModel) {
        _ivldAccrualInboundRemoteModel = ivldAccrualInboundRemoteModel;
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

        Class<?> remoteModelClass = _ivldAccrualInboundRemoteModel.getClass();

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

        Object returnValue = method.invoke(_ivldAccrualInboundRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            IvldAccrualInboundLocalServiceUtil.addIvldAccrualInbound(this);
        } else {
            IvldAccrualInboundLocalServiceUtil.updateIvldAccrualInbound(this);
        }
    }

    @Override
    public IvldAccrualInbound toEscapedModel() {
        return (IvldAccrualInbound) ProxyUtil.newProxyInstance(IvldAccrualInbound.class.getClassLoader(),
            new Class[] { IvldAccrualInbound.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        IvldAccrualInboundClp clone = new IvldAccrualInboundClp();

        clone.setRecordCreatedDate(getRecordCreatedDate());
        clone.setAccountId(getAccountId());
        clone.setPostingIndicator(getPostingIndicator());
        clone.setItemId(getItemId());
        clone.setModifiedDate(getModifiedDate());
        clone.setAccrualPeriodEndDate(getAccrualPeriodEndDate());
        clone.setItemIdentCodeQualifier(getItemIdentCodeQualifier());
        clone.setGlCompanyMasterSid(getGlCompanyMasterSid());
        clone.setSalesMasterId(getSalesMasterId());
        clone.setCreatedDate(getCreatedDate());
        clone.setCreatedBy(getCreatedBy());
        clone.setSource(getSource());
        clone.setAccrualPeriodStartDate(getAccrualPeriodStartDate());
        clone.setAddChgDelIndicator(getAddChgDelIndicator());
        clone.setSubLedgerType(getSubLedgerType());
        clone.setProgramNo(getProgramNo());
        clone.setDocumentType(getDocumentType());
        clone.setAccrualIntfid(getAccrualIntfid());
        clone.setGlCompanyName(getGlCompanyName());
        clone.setErrorCode(getErrorCode());
        clone.setIntfInsertedDate(getIntfInsertedDate());
        clone.setModifiedBy(getModifiedBy());
        clone.setCategoryId(getCategoryId());
        clone.setItemNo(getItemNo());
        clone.setReprocessedFlag(getReprocessedFlag());
        clone.setCompIdentCodeQualifier(getCompIdentCodeQualifier());
        clone.setAcctIdentCodeQualifier(getAcctIdentCodeQualifier());
        clone.setContractId(getContractId());
        clone.setAccountNo(getAccountNo());
        clone.setAccrualId(getAccrualId());
        clone.setReasonForFailure(getReasonForFailure());
        clone.setCompanyId(getCompanyId());
        clone.setAccountName(getAccountName());
        clone.setAccrualType(getAccrualType());
        clone.setPostingDate(getPostingDate());
        clone.setBrandId(getBrandId());
        clone.setProvisionId(getProvisionId());
        clone.setAmount(getAmount());
        clone.setGlDate(getGlDate());
        clone.setSubLedger(getSubLedger());
        clone.setCompanyCostCenter(getCompanyCostCenter());
        clone.setGlAccount(getGlAccount());
        clone.setCompanyNo(getCompanyNo());
        clone.setBatchId(getBatchId());
        clone.setProgramType(getProgramType());
        clone.setItemName(getItemName());
        clone.setErrorField(getErrorField());
        clone.setIvldAccrualInboundSid(getIvldAccrualInboundSid());
        clone.setContractNo(getContractNo());
        clone.setBrandName(getBrandName());
        clone.setContractName(getContractName());
        clone.setCheckRecord(getCheckRecord());

        return clone;
    }

    @Override
    public int compareTo(IvldAccrualInbound ivldAccrualInbound) {
        int primaryKey = ivldAccrualInbound.getPrimaryKey();

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

        if (!(obj instanceof IvldAccrualInboundClp)) {
            return false;
        }

        IvldAccrualInboundClp ivldAccrualInbound = (IvldAccrualInboundClp) obj;

        int primaryKey = ivldAccrualInbound.getPrimaryKey();

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

        sb.append("{recordCreatedDate=");
        sb.append(getRecordCreatedDate());
        sb.append(", accountId=");
        sb.append(getAccountId());
        sb.append(", postingIndicator=");
        sb.append(getPostingIndicator());
        sb.append(", itemId=");
        sb.append(getItemId());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", accrualPeriodEndDate=");
        sb.append(getAccrualPeriodEndDate());
        sb.append(", itemIdentCodeQualifier=");
        sb.append(getItemIdentCodeQualifier());
        sb.append(", glCompanyMasterSid=");
        sb.append(getGlCompanyMasterSid());
        sb.append(", salesMasterId=");
        sb.append(getSalesMasterId());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", createdBy=");
        sb.append(getCreatedBy());
        sb.append(", source=");
        sb.append(getSource());
        sb.append(", accrualPeriodStartDate=");
        sb.append(getAccrualPeriodStartDate());
        sb.append(", addChgDelIndicator=");
        sb.append(getAddChgDelIndicator());
        sb.append(", subLedgerType=");
        sb.append(getSubLedgerType());
        sb.append(", programNo=");
        sb.append(getProgramNo());
        sb.append(", documentType=");
        sb.append(getDocumentType());
        sb.append(", accrualIntfid=");
        sb.append(getAccrualIntfid());
        sb.append(", glCompanyName=");
        sb.append(getGlCompanyName());
        sb.append(", errorCode=");
        sb.append(getErrorCode());
        sb.append(", intfInsertedDate=");
        sb.append(getIntfInsertedDate());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", categoryId=");
        sb.append(getCategoryId());
        sb.append(", itemNo=");
        sb.append(getItemNo());
        sb.append(", reprocessedFlag=");
        sb.append(getReprocessedFlag());
        sb.append(", compIdentCodeQualifier=");
        sb.append(getCompIdentCodeQualifier());
        sb.append(", acctIdentCodeQualifier=");
        sb.append(getAcctIdentCodeQualifier());
        sb.append(", contractId=");
        sb.append(getContractId());
        sb.append(", accountNo=");
        sb.append(getAccountNo());
        sb.append(", accrualId=");
        sb.append(getAccrualId());
        sb.append(", reasonForFailure=");
        sb.append(getReasonForFailure());
        sb.append(", companyId=");
        sb.append(getCompanyId());
        sb.append(", accountName=");
        sb.append(getAccountName());
        sb.append(", accrualType=");
        sb.append(getAccrualType());
        sb.append(", postingDate=");
        sb.append(getPostingDate());
        sb.append(", brandId=");
        sb.append(getBrandId());
        sb.append(", provisionId=");
        sb.append(getProvisionId());
        sb.append(", amount=");
        sb.append(getAmount());
        sb.append(", glDate=");
        sb.append(getGlDate());
        sb.append(", subLedger=");
        sb.append(getSubLedger());
        sb.append(", companyCostCenter=");
        sb.append(getCompanyCostCenter());
        sb.append(", glAccount=");
        sb.append(getGlAccount());
        sb.append(", companyNo=");
        sb.append(getCompanyNo());
        sb.append(", batchId=");
        sb.append(getBatchId());
        sb.append(", programType=");
        sb.append(getProgramType());
        sb.append(", itemName=");
        sb.append(getItemName());
        sb.append(", errorField=");
        sb.append(getErrorField());
        sb.append(", ivldAccrualInboundSid=");
        sb.append(getIvldAccrualInboundSid());
        sb.append(", contractNo=");
        sb.append(getContractNo());
        sb.append(", brandName=");
        sb.append(getBrandName());
        sb.append(", contractName=");
        sb.append(getContractName());
        sb.append(", checkRecord=");
        sb.append(getCheckRecord());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(160);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.parttwo.model.IvldAccrualInbound");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>recordCreatedDate</column-name><column-value><![CDATA[");
        sb.append(getRecordCreatedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>accountId</column-name><column-value><![CDATA[");
        sb.append(getAccountId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>postingIndicator</column-name><column-value><![CDATA[");
        sb.append(getPostingIndicator());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemId</column-name><column-value><![CDATA[");
        sb.append(getItemId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
        sb.append(getModifiedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>accrualPeriodEndDate</column-name><column-value><![CDATA[");
        sb.append(getAccrualPeriodEndDate());
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
            "<column><column-name>salesMasterId</column-name><column-value><![CDATA[");
        sb.append(getSalesMasterId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>createdDate</column-name><column-value><![CDATA[");
        sb.append(getCreatedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>createdBy</column-name><column-value><![CDATA[");
        sb.append(getCreatedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>source</column-name><column-value><![CDATA[");
        sb.append(getSource());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>accrualPeriodStartDate</column-name><column-value><![CDATA[");
        sb.append(getAccrualPeriodStartDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>addChgDelIndicator</column-name><column-value><![CDATA[");
        sb.append(getAddChgDelIndicator());
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
            "<column><column-name>documentType</column-name><column-value><![CDATA[");
        sb.append(getDocumentType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>accrualIntfid</column-name><column-value><![CDATA[");
        sb.append(getAccrualIntfid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>glCompanyName</column-name><column-value><![CDATA[");
        sb.append(getGlCompanyName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>errorCode</column-name><column-value><![CDATA[");
        sb.append(getErrorCode());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>intfInsertedDate</column-name><column-value><![CDATA[");
        sb.append(getIntfInsertedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedBy</column-name><column-value><![CDATA[");
        sb.append(getModifiedBy());
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
            "<column><column-name>reprocessedFlag</column-name><column-value><![CDATA[");
        sb.append(getReprocessedFlag());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>compIdentCodeQualifier</column-name><column-value><![CDATA[");
        sb.append(getCompIdentCodeQualifier());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>acctIdentCodeQualifier</column-name><column-value><![CDATA[");
        sb.append(getAcctIdentCodeQualifier());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>contractId</column-name><column-value><![CDATA[");
        sb.append(getContractId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>accountNo</column-name><column-value><![CDATA[");
        sb.append(getAccountNo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>accrualId</column-name><column-value><![CDATA[");
        sb.append(getAccrualId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>reasonForFailure</column-name><column-value><![CDATA[");
        sb.append(getReasonForFailure());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>companyId</column-name><column-value><![CDATA[");
        sb.append(getCompanyId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>accountName</column-name><column-value><![CDATA[");
        sb.append(getAccountName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>accrualType</column-name><column-value><![CDATA[");
        sb.append(getAccrualType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>postingDate</column-name><column-value><![CDATA[");
        sb.append(getPostingDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>brandId</column-name><column-value><![CDATA[");
        sb.append(getBrandId());
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
            "<column><column-name>glDate</column-name><column-value><![CDATA[");
        sb.append(getGlDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>subLedger</column-name><column-value><![CDATA[");
        sb.append(getSubLedger());
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
            "<column><column-name>companyNo</column-name><column-value><![CDATA[");
        sb.append(getCompanyNo());
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
            "<column><column-name>itemName</column-name><column-value><![CDATA[");
        sb.append(getItemName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>errorField</column-name><column-value><![CDATA[");
        sb.append(getErrorField());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>ivldAccrualInboundSid</column-name><column-value><![CDATA[");
        sb.append(getIvldAccrualInboundSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>contractNo</column-name><column-value><![CDATA[");
        sb.append(getContractNo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>brandName</column-name><column-value><![CDATA[");
        sb.append(getBrandName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>contractName</column-name><column-value><![CDATA[");
        sb.append(getContractName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>checkRecord</column-name><column-value><![CDATA[");
        sb.append(getCheckRecord());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
