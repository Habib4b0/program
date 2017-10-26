package com.stpl.app.parttwo.model;

import com.stpl.app.parttwo.service.ClpSerializer;
import com.stpl.app.parttwo.service.StAdjustmentReserveDetailLocalServiceUtil;
import com.stpl.app.parttwo.service.persistence.StAdjustmentReserveDetailPK;

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


public class StAdjustmentReserveDetailClp extends BaseModelImpl<StAdjustmentReserveDetail>
    implements StAdjustmentReserveDetail {
    private Date _adjustmentCreatedDate;
    private boolean _etlCheckRecord;
    private String _postingIndicator;
    private Date _modifiedDate;
    private String _account;
    private String _credit;
    private Date _workflowApprovedDate;
    private String _source;
    private String _lineDescription;
    private String _ledger;
    private String _udc6;
    private String _udc5;
    private String _udc4;
    private Date _workflowCreatedDate;
    private String _udc3;
    private String _udc2;
    private String _udc1;
    private String _adjustmentType;
    private String _modifiedBy;
    private boolean _checkRecord;
    private String _glCompanyName;
    private String _division;
    private String _balanceType;
    private String _sessionId;
    private String _journalName;
    private String _project;
    private String _debit;
    private String _accountType;
    private String _journalDescription;
    private String _category;
    private String _createdBy;
    private Date _createdDate;
    private String _businessUnitId;
    private Date _reversalPeriodDate;
    private String _workflowId;
    private String _chartOfAccounts;
    private String _userId;
    private String _batchName;
    private String _database;
    private String _costCenter;
    private String _outboundStatus;
    private String _dataAccessSet;
    private String _future1;
    private String _future2;
    private String _workflowName;
    private String _workflowCreatedBy;
    private String _currency;
    private String _batchId;
    private String _accountCategory;
    private String _reverseJournal;
    private String _workflowApprovedBy;
    private String _brand;
    private Date _accountingDate;
    private Date _redemptionPeriod;
    private String _originalBatchId;
    private String _adjustmentLevel;
    private BaseModel<?> _stAdjustmentReserveDetailRemoteModel;

    public StAdjustmentReserveDetailClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return StAdjustmentReserveDetail.class;
    }

    @Override
    public String getModelClassName() {
        return StAdjustmentReserveDetail.class.getName();
    }

    @Override
    public StAdjustmentReserveDetailPK getPrimaryKey() {
        return new StAdjustmentReserveDetailPK(_account, _adjustmentType,
            _glCompanyName, _sessionId, _accountType, _businessUnitId,
            _workflowId, _userId, _accountCategory, _brand, _redemptionPeriod);
    }

    @Override
    public void setPrimaryKey(StAdjustmentReserveDetailPK primaryKey) {
        setAccount(primaryKey.account);
        setAdjustmentType(primaryKey.adjustmentType);
        setGlCompanyName(primaryKey.glCompanyName);
        setSessionId(primaryKey.sessionId);
        setAccountType(primaryKey.accountType);
        setBusinessUnitId(primaryKey.businessUnitId);
        setWorkflowId(primaryKey.workflowId);
        setUserId(primaryKey.userId);
        setAccountCategory(primaryKey.accountCategory);
        setBrand(primaryKey.brand);
        setRedemptionPeriod(primaryKey.redemptionPeriod);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return new StAdjustmentReserveDetailPK(_account, _adjustmentType,
            _glCompanyName, _sessionId, _accountType, _businessUnitId,
            _workflowId, _userId, _accountCategory, _brand, _redemptionPeriod);
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey((StAdjustmentReserveDetailPK) primaryKeyObj);
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("adjustmentCreatedDate", getAdjustmentCreatedDate());
        attributes.put("etlCheckRecord", getEtlCheckRecord());
        attributes.put("postingIndicator", getPostingIndicator());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("account", getAccount());
        attributes.put("credit", getCredit());
        attributes.put("workflowApprovedDate", getWorkflowApprovedDate());
        attributes.put("source", getSource());
        attributes.put("lineDescription", getLineDescription());
        attributes.put("ledger", getLedger());
        attributes.put("udc6", getUdc6());
        attributes.put("udc5", getUdc5());
        attributes.put("udc4", getUdc4());
        attributes.put("workflowCreatedDate", getWorkflowCreatedDate());
        attributes.put("udc3", getUdc3());
        attributes.put("udc2", getUdc2());
        attributes.put("udc1", getUdc1());
        attributes.put("adjustmentType", getAdjustmentType());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("checkRecord", getCheckRecord());
        attributes.put("glCompanyName", getGlCompanyName());
        attributes.put("division", getDivision());
        attributes.put("balanceType", getBalanceType());
        attributes.put("sessionId", getSessionId());
        attributes.put("journalName", getJournalName());
        attributes.put("project", getProject());
        attributes.put("debit", getDebit());
        attributes.put("accountType", getAccountType());
        attributes.put("journalDescription", getJournalDescription());
        attributes.put("category", getCategory());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("businessUnitId", getBusinessUnitId());
        attributes.put("reversalPeriodDate", getReversalPeriodDate());
        attributes.put("workflowId", getWorkflowId());
        attributes.put("chartOfAccounts", getChartOfAccounts());
        attributes.put("userId", getUserId());
        attributes.put("batchName", getBatchName());
        attributes.put("database", getDatabase());
        attributes.put("costCenter", getCostCenter());
        attributes.put("outboundStatus", getOutboundStatus());
        attributes.put("dataAccessSet", getDataAccessSet());
        attributes.put("future1", getFuture1());
        attributes.put("future2", getFuture2());
        attributes.put("workflowName", getWorkflowName());
        attributes.put("workflowCreatedBy", getWorkflowCreatedBy());
        attributes.put("currency", getCurrency());
        attributes.put("batchId", getBatchId());
        attributes.put("accountCategory", getAccountCategory());
        attributes.put("reverseJournal", getReverseJournal());
        attributes.put("workflowApprovedBy", getWorkflowApprovedBy());
        attributes.put("brand", getBrand());
        attributes.put("accountingDate", getAccountingDate());
        attributes.put("redemptionPeriod", getRedemptionPeriod());
        attributes.put("originalBatchId", getOriginalBatchId());
        attributes.put("adjustmentLevel", getAdjustmentLevel());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Date adjustmentCreatedDate = (Date) attributes.get(
                "adjustmentCreatedDate");

        if (adjustmentCreatedDate != null) {
            setAdjustmentCreatedDate(adjustmentCreatedDate);
        }

        Boolean etlCheckRecord = (Boolean) attributes.get("etlCheckRecord");

        if (etlCheckRecord != null) {
            setEtlCheckRecord(etlCheckRecord);
        }

        String postingIndicator = (String) attributes.get("postingIndicator");

        if (postingIndicator != null) {
            setPostingIndicator(postingIndicator);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        String account = (String) attributes.get("account");

        if (account != null) {
            setAccount(account);
        }

        String credit = (String) attributes.get("credit");

        if (credit != null) {
            setCredit(credit);
        }

        Date workflowApprovedDate = (Date) attributes.get(
                "workflowApprovedDate");

        if (workflowApprovedDate != null) {
            setWorkflowApprovedDate(workflowApprovedDate);
        }

        String source = (String) attributes.get("source");

        if (source != null) {
            setSource(source);
        }

        String lineDescription = (String) attributes.get("lineDescription");

        if (lineDescription != null) {
            setLineDescription(lineDescription);
        }

        String ledger = (String) attributes.get("ledger");

        if (ledger != null) {
            setLedger(ledger);
        }

        String udc6 = (String) attributes.get("udc6");

        if (udc6 != null) {
            setUdc6(udc6);
        }

        String udc5 = (String) attributes.get("udc5");

        if (udc5 != null) {
            setUdc5(udc5);
        }

        String udc4 = (String) attributes.get("udc4");

        if (udc4 != null) {
            setUdc4(udc4);
        }

        Date workflowCreatedDate = (Date) attributes.get("workflowCreatedDate");

        if (workflowCreatedDate != null) {
            setWorkflowCreatedDate(workflowCreatedDate);
        }

        String udc3 = (String) attributes.get("udc3");

        if (udc3 != null) {
            setUdc3(udc3);
        }

        String udc2 = (String) attributes.get("udc2");

        if (udc2 != null) {
            setUdc2(udc2);
        }

        String udc1 = (String) attributes.get("udc1");

        if (udc1 != null) {
            setUdc1(udc1);
        }

        String adjustmentType = (String) attributes.get("adjustmentType");

        if (adjustmentType != null) {
            setAdjustmentType(adjustmentType);
        }

        String modifiedBy = (String) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        Boolean checkRecord = (Boolean) attributes.get("checkRecord");

        if (checkRecord != null) {
            setCheckRecord(checkRecord);
        }

        String glCompanyName = (String) attributes.get("glCompanyName");

        if (glCompanyName != null) {
            setGlCompanyName(glCompanyName);
        }

        String division = (String) attributes.get("division");

        if (division != null) {
            setDivision(division);
        }

        String balanceType = (String) attributes.get("balanceType");

        if (balanceType != null) {
            setBalanceType(balanceType);
        }

        String sessionId = (String) attributes.get("sessionId");

        if (sessionId != null) {
            setSessionId(sessionId);
        }

        String journalName = (String) attributes.get("journalName");

        if (journalName != null) {
            setJournalName(journalName);
        }

        String project = (String) attributes.get("project");

        if (project != null) {
            setProject(project);
        }

        String debit = (String) attributes.get("debit");

        if (debit != null) {
            setDebit(debit);
        }

        String accountType = (String) attributes.get("accountType");

        if (accountType != null) {
            setAccountType(accountType);
        }

        String journalDescription = (String) attributes.get(
                "journalDescription");

        if (journalDescription != null) {
            setJournalDescription(journalDescription);
        }

        String category = (String) attributes.get("category");

        if (category != null) {
            setCategory(category);
        }

        String createdBy = (String) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        String businessUnitId = (String) attributes.get("businessUnitId");

        if (businessUnitId != null) {
            setBusinessUnitId(businessUnitId);
        }

        Date reversalPeriodDate = (Date) attributes.get("reversalPeriodDate");

        if (reversalPeriodDate != null) {
            setReversalPeriodDate(reversalPeriodDate);
        }

        String workflowId = (String) attributes.get("workflowId");

        if (workflowId != null) {
            setWorkflowId(workflowId);
        }

        String chartOfAccounts = (String) attributes.get("chartOfAccounts");

        if (chartOfAccounts != null) {
            setChartOfAccounts(chartOfAccounts);
        }

        String userId = (String) attributes.get("userId");

        if (userId != null) {
            setUserId(userId);
        }

        String batchName = (String) attributes.get("batchName");

        if (batchName != null) {
            setBatchName(batchName);
        }

        String database = (String) attributes.get("database");

        if (database != null) {
            setDatabase(database);
        }

        String costCenter = (String) attributes.get("costCenter");

        if (costCenter != null) {
            setCostCenter(costCenter);
        }

        String outboundStatus = (String) attributes.get("outboundStatus");

        if (outboundStatus != null) {
            setOutboundStatus(outboundStatus);
        }

        String dataAccessSet = (String) attributes.get("dataAccessSet");

        if (dataAccessSet != null) {
            setDataAccessSet(dataAccessSet);
        }

        String future1 = (String) attributes.get("future1");

        if (future1 != null) {
            setFuture1(future1);
        }

        String future2 = (String) attributes.get("future2");

        if (future2 != null) {
            setFuture2(future2);
        }

        String workflowName = (String) attributes.get("workflowName");

        if (workflowName != null) {
            setWorkflowName(workflowName);
        }

        String workflowCreatedBy = (String) attributes.get("workflowCreatedBy");

        if (workflowCreatedBy != null) {
            setWorkflowCreatedBy(workflowCreatedBy);
        }

        String currency = (String) attributes.get("currency");

        if (currency != null) {
            setCurrency(currency);
        }

        String batchId = (String) attributes.get("batchId");

        if (batchId != null) {
            setBatchId(batchId);
        }

        String accountCategory = (String) attributes.get("accountCategory");

        if (accountCategory != null) {
            setAccountCategory(accountCategory);
        }

        String reverseJournal = (String) attributes.get("reverseJournal");

        if (reverseJournal != null) {
            setReverseJournal(reverseJournal);
        }

        String workflowApprovedBy = (String) attributes.get(
                "workflowApprovedBy");

        if (workflowApprovedBy != null) {
            setWorkflowApprovedBy(workflowApprovedBy);
        }

        String brand = (String) attributes.get("brand");

        if (brand != null) {
            setBrand(brand);
        }

        Date accountingDate = (Date) attributes.get("accountingDate");

        if (accountingDate != null) {
            setAccountingDate(accountingDate);
        }

        Date redemptionPeriod = (Date) attributes.get("redemptionPeriod");

        if (redemptionPeriod != null) {
            setRedemptionPeriod(redemptionPeriod);
        }

        String originalBatchId = (String) attributes.get("originalBatchId");

        if (originalBatchId != null) {
            setOriginalBatchId(originalBatchId);
        }

        String adjustmentLevel = (String) attributes.get("adjustmentLevel");

        if (adjustmentLevel != null) {
            setAdjustmentLevel(adjustmentLevel);
        }
    }

    @Override
    public Date getAdjustmentCreatedDate() {
        return _adjustmentCreatedDate;
    }

    @Override
    public void setAdjustmentCreatedDate(Date adjustmentCreatedDate) {
        _adjustmentCreatedDate = adjustmentCreatedDate;

        if (_stAdjustmentReserveDetailRemoteModel != null) {
            try {
                Class<?> clazz = _stAdjustmentReserveDetailRemoteModel.getClass();

                Method method = clazz.getMethod("setAdjustmentCreatedDate",
                        Date.class);

                method.invoke(_stAdjustmentReserveDetailRemoteModel,
                    adjustmentCreatedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public boolean getEtlCheckRecord() {
        return _etlCheckRecord;
    }

    @Override
    public boolean isEtlCheckRecord() {
        return _etlCheckRecord;
    }

    @Override
    public void setEtlCheckRecord(boolean etlCheckRecord) {
        _etlCheckRecord = etlCheckRecord;

        if (_stAdjustmentReserveDetailRemoteModel != null) {
            try {
                Class<?> clazz = _stAdjustmentReserveDetailRemoteModel.getClass();

                Method method = clazz.getMethod("setEtlCheckRecord",
                        boolean.class);

                method.invoke(_stAdjustmentReserveDetailRemoteModel,
                    etlCheckRecord);
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

        if (_stAdjustmentReserveDetailRemoteModel != null) {
            try {
                Class<?> clazz = _stAdjustmentReserveDetailRemoteModel.getClass();

                Method method = clazz.getMethod("setPostingIndicator",
                        String.class);

                method.invoke(_stAdjustmentReserveDetailRemoteModel,
                    postingIndicator);
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

        if (_stAdjustmentReserveDetailRemoteModel != null) {
            try {
                Class<?> clazz = _stAdjustmentReserveDetailRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_stAdjustmentReserveDetailRemoteModel,
                    modifiedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getAccount() {
        return _account;
    }

    @Override
    public void setAccount(String account) {
        _account = account;

        if (_stAdjustmentReserveDetailRemoteModel != null) {
            try {
                Class<?> clazz = _stAdjustmentReserveDetailRemoteModel.getClass();

                Method method = clazz.getMethod("setAccount", String.class);

                method.invoke(_stAdjustmentReserveDetailRemoteModel, account);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getCredit() {
        return _credit;
    }

    @Override
    public void setCredit(String credit) {
        _credit = credit;

        if (_stAdjustmentReserveDetailRemoteModel != null) {
            try {
                Class<?> clazz = _stAdjustmentReserveDetailRemoteModel.getClass();

                Method method = clazz.getMethod("setCredit", String.class);

                method.invoke(_stAdjustmentReserveDetailRemoteModel, credit);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getWorkflowApprovedDate() {
        return _workflowApprovedDate;
    }

    @Override
    public void setWorkflowApprovedDate(Date workflowApprovedDate) {
        _workflowApprovedDate = workflowApprovedDate;

        if (_stAdjustmentReserveDetailRemoteModel != null) {
            try {
                Class<?> clazz = _stAdjustmentReserveDetailRemoteModel.getClass();

                Method method = clazz.getMethod("setWorkflowApprovedDate",
                        Date.class);

                method.invoke(_stAdjustmentReserveDetailRemoteModel,
                    workflowApprovedDate);
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

        if (_stAdjustmentReserveDetailRemoteModel != null) {
            try {
                Class<?> clazz = _stAdjustmentReserveDetailRemoteModel.getClass();

                Method method = clazz.getMethod("setSource", String.class);

                method.invoke(_stAdjustmentReserveDetailRemoteModel, source);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getLineDescription() {
        return _lineDescription;
    }

    @Override
    public void setLineDescription(String lineDescription) {
        _lineDescription = lineDescription;

        if (_stAdjustmentReserveDetailRemoteModel != null) {
            try {
                Class<?> clazz = _stAdjustmentReserveDetailRemoteModel.getClass();

                Method method = clazz.getMethod("setLineDescription",
                        String.class);

                method.invoke(_stAdjustmentReserveDetailRemoteModel,
                    lineDescription);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getLedger() {
        return _ledger;
    }

    @Override
    public void setLedger(String ledger) {
        _ledger = ledger;

        if (_stAdjustmentReserveDetailRemoteModel != null) {
            try {
                Class<?> clazz = _stAdjustmentReserveDetailRemoteModel.getClass();

                Method method = clazz.getMethod("setLedger", String.class);

                method.invoke(_stAdjustmentReserveDetailRemoteModel, ledger);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getUdc6() {
        return _udc6;
    }

    @Override
    public void setUdc6(String udc6) {
        _udc6 = udc6;

        if (_stAdjustmentReserveDetailRemoteModel != null) {
            try {
                Class<?> clazz = _stAdjustmentReserveDetailRemoteModel.getClass();

                Method method = clazz.getMethod("setUdc6", String.class);

                method.invoke(_stAdjustmentReserveDetailRemoteModel, udc6);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getUdc5() {
        return _udc5;
    }

    @Override
    public void setUdc5(String udc5) {
        _udc5 = udc5;

        if (_stAdjustmentReserveDetailRemoteModel != null) {
            try {
                Class<?> clazz = _stAdjustmentReserveDetailRemoteModel.getClass();

                Method method = clazz.getMethod("setUdc5", String.class);

                method.invoke(_stAdjustmentReserveDetailRemoteModel, udc5);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getUdc4() {
        return _udc4;
    }

    @Override
    public void setUdc4(String udc4) {
        _udc4 = udc4;

        if (_stAdjustmentReserveDetailRemoteModel != null) {
            try {
                Class<?> clazz = _stAdjustmentReserveDetailRemoteModel.getClass();

                Method method = clazz.getMethod("setUdc4", String.class);

                method.invoke(_stAdjustmentReserveDetailRemoteModel, udc4);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getWorkflowCreatedDate() {
        return _workflowCreatedDate;
    }

    @Override
    public void setWorkflowCreatedDate(Date workflowCreatedDate) {
        _workflowCreatedDate = workflowCreatedDate;

        if (_stAdjustmentReserveDetailRemoteModel != null) {
            try {
                Class<?> clazz = _stAdjustmentReserveDetailRemoteModel.getClass();

                Method method = clazz.getMethod("setWorkflowCreatedDate",
                        Date.class);

                method.invoke(_stAdjustmentReserveDetailRemoteModel,
                    workflowCreatedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getUdc3() {
        return _udc3;
    }

    @Override
    public void setUdc3(String udc3) {
        _udc3 = udc3;

        if (_stAdjustmentReserveDetailRemoteModel != null) {
            try {
                Class<?> clazz = _stAdjustmentReserveDetailRemoteModel.getClass();

                Method method = clazz.getMethod("setUdc3", String.class);

                method.invoke(_stAdjustmentReserveDetailRemoteModel, udc3);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getUdc2() {
        return _udc2;
    }

    @Override
    public void setUdc2(String udc2) {
        _udc2 = udc2;

        if (_stAdjustmentReserveDetailRemoteModel != null) {
            try {
                Class<?> clazz = _stAdjustmentReserveDetailRemoteModel.getClass();

                Method method = clazz.getMethod("setUdc2", String.class);

                method.invoke(_stAdjustmentReserveDetailRemoteModel, udc2);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getUdc1() {
        return _udc1;
    }

    @Override
    public void setUdc1(String udc1) {
        _udc1 = udc1;

        if (_stAdjustmentReserveDetailRemoteModel != null) {
            try {
                Class<?> clazz = _stAdjustmentReserveDetailRemoteModel.getClass();

                Method method = clazz.getMethod("setUdc1", String.class);

                method.invoke(_stAdjustmentReserveDetailRemoteModel, udc1);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getAdjustmentType() {
        return _adjustmentType;
    }

    @Override
    public void setAdjustmentType(String adjustmentType) {
        _adjustmentType = adjustmentType;

        if (_stAdjustmentReserveDetailRemoteModel != null) {
            try {
                Class<?> clazz = _stAdjustmentReserveDetailRemoteModel.getClass();

                Method method = clazz.getMethod("setAdjustmentType",
                        String.class);

                method.invoke(_stAdjustmentReserveDetailRemoteModel,
                    adjustmentType);
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

        if (_stAdjustmentReserveDetailRemoteModel != null) {
            try {
                Class<?> clazz = _stAdjustmentReserveDetailRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", String.class);

                method.invoke(_stAdjustmentReserveDetailRemoteModel, modifiedBy);
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

        if (_stAdjustmentReserveDetailRemoteModel != null) {
            try {
                Class<?> clazz = _stAdjustmentReserveDetailRemoteModel.getClass();

                Method method = clazz.getMethod("setCheckRecord", boolean.class);

                method.invoke(_stAdjustmentReserveDetailRemoteModel, checkRecord);
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

        if (_stAdjustmentReserveDetailRemoteModel != null) {
            try {
                Class<?> clazz = _stAdjustmentReserveDetailRemoteModel.getClass();

                Method method = clazz.getMethod("setGlCompanyName", String.class);

                method.invoke(_stAdjustmentReserveDetailRemoteModel,
                    glCompanyName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getDivision() {
        return _division;
    }

    @Override
    public void setDivision(String division) {
        _division = division;

        if (_stAdjustmentReserveDetailRemoteModel != null) {
            try {
                Class<?> clazz = _stAdjustmentReserveDetailRemoteModel.getClass();

                Method method = clazz.getMethod("setDivision", String.class);

                method.invoke(_stAdjustmentReserveDetailRemoteModel, division);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getBalanceType() {
        return _balanceType;
    }

    @Override
    public void setBalanceType(String balanceType) {
        _balanceType = balanceType;

        if (_stAdjustmentReserveDetailRemoteModel != null) {
            try {
                Class<?> clazz = _stAdjustmentReserveDetailRemoteModel.getClass();

                Method method = clazz.getMethod("setBalanceType", String.class);

                method.invoke(_stAdjustmentReserveDetailRemoteModel, balanceType);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getSessionId() {
        return _sessionId;
    }

    @Override
    public void setSessionId(String sessionId) {
        _sessionId = sessionId;

        if (_stAdjustmentReserveDetailRemoteModel != null) {
            try {
                Class<?> clazz = _stAdjustmentReserveDetailRemoteModel.getClass();

                Method method = clazz.getMethod("setSessionId", String.class);

                method.invoke(_stAdjustmentReserveDetailRemoteModel, sessionId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getJournalName() {
        return _journalName;
    }

    @Override
    public void setJournalName(String journalName) {
        _journalName = journalName;

        if (_stAdjustmentReserveDetailRemoteModel != null) {
            try {
                Class<?> clazz = _stAdjustmentReserveDetailRemoteModel.getClass();

                Method method = clazz.getMethod("setJournalName", String.class);

                method.invoke(_stAdjustmentReserveDetailRemoteModel, journalName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getProject() {
        return _project;
    }

    @Override
    public void setProject(String project) {
        _project = project;

        if (_stAdjustmentReserveDetailRemoteModel != null) {
            try {
                Class<?> clazz = _stAdjustmentReserveDetailRemoteModel.getClass();

                Method method = clazz.getMethod("setProject", String.class);

                method.invoke(_stAdjustmentReserveDetailRemoteModel, project);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getDebit() {
        return _debit;
    }

    @Override
    public void setDebit(String debit) {
        _debit = debit;

        if (_stAdjustmentReserveDetailRemoteModel != null) {
            try {
                Class<?> clazz = _stAdjustmentReserveDetailRemoteModel.getClass();

                Method method = clazz.getMethod("setDebit", String.class);

                method.invoke(_stAdjustmentReserveDetailRemoteModel, debit);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getAccountType() {
        return _accountType;
    }

    @Override
    public void setAccountType(String accountType) {
        _accountType = accountType;

        if (_stAdjustmentReserveDetailRemoteModel != null) {
            try {
                Class<?> clazz = _stAdjustmentReserveDetailRemoteModel.getClass();

                Method method = clazz.getMethod("setAccountType", String.class);

                method.invoke(_stAdjustmentReserveDetailRemoteModel, accountType);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getJournalDescription() {
        return _journalDescription;
    }

    @Override
    public void setJournalDescription(String journalDescription) {
        _journalDescription = journalDescription;

        if (_stAdjustmentReserveDetailRemoteModel != null) {
            try {
                Class<?> clazz = _stAdjustmentReserveDetailRemoteModel.getClass();

                Method method = clazz.getMethod("setJournalDescription",
                        String.class);

                method.invoke(_stAdjustmentReserveDetailRemoteModel,
                    journalDescription);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getCategory() {
        return _category;
    }

    @Override
    public void setCategory(String category) {
        _category = category;

        if (_stAdjustmentReserveDetailRemoteModel != null) {
            try {
                Class<?> clazz = _stAdjustmentReserveDetailRemoteModel.getClass();

                Method method = clazz.getMethod("setCategory", String.class);

                method.invoke(_stAdjustmentReserveDetailRemoteModel, category);
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

        if (_stAdjustmentReserveDetailRemoteModel != null) {
            try {
                Class<?> clazz = _stAdjustmentReserveDetailRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", String.class);

                method.invoke(_stAdjustmentReserveDetailRemoteModel, createdBy);
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

        if (_stAdjustmentReserveDetailRemoteModel != null) {
            try {
                Class<?> clazz = _stAdjustmentReserveDetailRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_stAdjustmentReserveDetailRemoteModel, createdDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getBusinessUnitId() {
        return _businessUnitId;
    }

    @Override
    public void setBusinessUnitId(String businessUnitId) {
        _businessUnitId = businessUnitId;

        if (_stAdjustmentReserveDetailRemoteModel != null) {
            try {
                Class<?> clazz = _stAdjustmentReserveDetailRemoteModel.getClass();

                Method method = clazz.getMethod("setBusinessUnitId",
                        String.class);

                method.invoke(_stAdjustmentReserveDetailRemoteModel,
                    businessUnitId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getReversalPeriodDate() {
        return _reversalPeriodDate;
    }

    @Override
    public void setReversalPeriodDate(Date reversalPeriodDate) {
        _reversalPeriodDate = reversalPeriodDate;

        if (_stAdjustmentReserveDetailRemoteModel != null) {
            try {
                Class<?> clazz = _stAdjustmentReserveDetailRemoteModel.getClass();

                Method method = clazz.getMethod("setReversalPeriodDate",
                        Date.class);

                method.invoke(_stAdjustmentReserveDetailRemoteModel,
                    reversalPeriodDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getWorkflowId() {
        return _workflowId;
    }

    @Override
    public void setWorkflowId(String workflowId) {
        _workflowId = workflowId;

        if (_stAdjustmentReserveDetailRemoteModel != null) {
            try {
                Class<?> clazz = _stAdjustmentReserveDetailRemoteModel.getClass();

                Method method = clazz.getMethod("setWorkflowId", String.class);

                method.invoke(_stAdjustmentReserveDetailRemoteModel, workflowId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getChartOfAccounts() {
        return _chartOfAccounts;
    }

    @Override
    public void setChartOfAccounts(String chartOfAccounts) {
        _chartOfAccounts = chartOfAccounts;

        if (_stAdjustmentReserveDetailRemoteModel != null) {
            try {
                Class<?> clazz = _stAdjustmentReserveDetailRemoteModel.getClass();

                Method method = clazz.getMethod("setChartOfAccounts",
                        String.class);

                method.invoke(_stAdjustmentReserveDetailRemoteModel,
                    chartOfAccounts);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getUserId() {
        return _userId;
    }

    @Override
    public void setUserId(String userId) {
        _userId = userId;

        if (_stAdjustmentReserveDetailRemoteModel != null) {
            try {
                Class<?> clazz = _stAdjustmentReserveDetailRemoteModel.getClass();

                Method method = clazz.getMethod("setUserId", String.class);

                method.invoke(_stAdjustmentReserveDetailRemoteModel, userId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getBatchName() {
        return _batchName;
    }

    @Override
    public void setBatchName(String batchName) {
        _batchName = batchName;

        if (_stAdjustmentReserveDetailRemoteModel != null) {
            try {
                Class<?> clazz = _stAdjustmentReserveDetailRemoteModel.getClass();

                Method method = clazz.getMethod("setBatchName", String.class);

                method.invoke(_stAdjustmentReserveDetailRemoteModel, batchName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getDatabase() {
        return _database;
    }

    @Override
    public void setDatabase(String database) {
        _database = database;

        if (_stAdjustmentReserveDetailRemoteModel != null) {
            try {
                Class<?> clazz = _stAdjustmentReserveDetailRemoteModel.getClass();

                Method method = clazz.getMethod("setDatabase", String.class);

                method.invoke(_stAdjustmentReserveDetailRemoteModel, database);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getCostCenter() {
        return _costCenter;
    }

    @Override
    public void setCostCenter(String costCenter) {
        _costCenter = costCenter;

        if (_stAdjustmentReserveDetailRemoteModel != null) {
            try {
                Class<?> clazz = _stAdjustmentReserveDetailRemoteModel.getClass();

                Method method = clazz.getMethod("setCostCenter", String.class);

                method.invoke(_stAdjustmentReserveDetailRemoteModel, costCenter);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getOutboundStatus() {
        return _outboundStatus;
    }

    @Override
    public void setOutboundStatus(String outboundStatus) {
        _outboundStatus = outboundStatus;

        if (_stAdjustmentReserveDetailRemoteModel != null) {
            try {
                Class<?> clazz = _stAdjustmentReserveDetailRemoteModel.getClass();

                Method method = clazz.getMethod("setOutboundStatus",
                        String.class);

                method.invoke(_stAdjustmentReserveDetailRemoteModel,
                    outboundStatus);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getDataAccessSet() {
        return _dataAccessSet;
    }

    @Override
    public void setDataAccessSet(String dataAccessSet) {
        _dataAccessSet = dataAccessSet;

        if (_stAdjustmentReserveDetailRemoteModel != null) {
            try {
                Class<?> clazz = _stAdjustmentReserveDetailRemoteModel.getClass();

                Method method = clazz.getMethod("setDataAccessSet", String.class);

                method.invoke(_stAdjustmentReserveDetailRemoteModel,
                    dataAccessSet);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getFuture1() {
        return _future1;
    }

    @Override
    public void setFuture1(String future1) {
        _future1 = future1;

        if (_stAdjustmentReserveDetailRemoteModel != null) {
            try {
                Class<?> clazz = _stAdjustmentReserveDetailRemoteModel.getClass();

                Method method = clazz.getMethod("setFuture1", String.class);

                method.invoke(_stAdjustmentReserveDetailRemoteModel, future1);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getFuture2() {
        return _future2;
    }

    @Override
    public void setFuture2(String future2) {
        _future2 = future2;

        if (_stAdjustmentReserveDetailRemoteModel != null) {
            try {
                Class<?> clazz = _stAdjustmentReserveDetailRemoteModel.getClass();

                Method method = clazz.getMethod("setFuture2", String.class);

                method.invoke(_stAdjustmentReserveDetailRemoteModel, future2);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getWorkflowName() {
        return _workflowName;
    }

    @Override
    public void setWorkflowName(String workflowName) {
        _workflowName = workflowName;

        if (_stAdjustmentReserveDetailRemoteModel != null) {
            try {
                Class<?> clazz = _stAdjustmentReserveDetailRemoteModel.getClass();

                Method method = clazz.getMethod("setWorkflowName", String.class);

                method.invoke(_stAdjustmentReserveDetailRemoteModel,
                    workflowName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getWorkflowCreatedBy() {
        return _workflowCreatedBy;
    }

    @Override
    public void setWorkflowCreatedBy(String workflowCreatedBy) {
        _workflowCreatedBy = workflowCreatedBy;

        if (_stAdjustmentReserveDetailRemoteModel != null) {
            try {
                Class<?> clazz = _stAdjustmentReserveDetailRemoteModel.getClass();

                Method method = clazz.getMethod("setWorkflowCreatedBy",
                        String.class);

                method.invoke(_stAdjustmentReserveDetailRemoteModel,
                    workflowCreatedBy);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getCurrency() {
        return _currency;
    }

    @Override
    public void setCurrency(String currency) {
        _currency = currency;

        if (_stAdjustmentReserveDetailRemoteModel != null) {
            try {
                Class<?> clazz = _stAdjustmentReserveDetailRemoteModel.getClass();

                Method method = clazz.getMethod("setCurrency", String.class);

                method.invoke(_stAdjustmentReserveDetailRemoteModel, currency);
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

        if (_stAdjustmentReserveDetailRemoteModel != null) {
            try {
                Class<?> clazz = _stAdjustmentReserveDetailRemoteModel.getClass();

                Method method = clazz.getMethod("setBatchId", String.class);

                method.invoke(_stAdjustmentReserveDetailRemoteModel, batchId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getAccountCategory() {
        return _accountCategory;
    }

    @Override
    public void setAccountCategory(String accountCategory) {
        _accountCategory = accountCategory;

        if (_stAdjustmentReserveDetailRemoteModel != null) {
            try {
                Class<?> clazz = _stAdjustmentReserveDetailRemoteModel.getClass();

                Method method = clazz.getMethod("setAccountCategory",
                        String.class);

                method.invoke(_stAdjustmentReserveDetailRemoteModel,
                    accountCategory);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getReverseJournal() {
        return _reverseJournal;
    }

    @Override
    public void setReverseJournal(String reverseJournal) {
        _reverseJournal = reverseJournal;

        if (_stAdjustmentReserveDetailRemoteModel != null) {
            try {
                Class<?> clazz = _stAdjustmentReserveDetailRemoteModel.getClass();

                Method method = clazz.getMethod("setReverseJournal",
                        String.class);

                method.invoke(_stAdjustmentReserveDetailRemoteModel,
                    reverseJournal);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getWorkflowApprovedBy() {
        return _workflowApprovedBy;
    }

    @Override
    public void setWorkflowApprovedBy(String workflowApprovedBy) {
        _workflowApprovedBy = workflowApprovedBy;

        if (_stAdjustmentReserveDetailRemoteModel != null) {
            try {
                Class<?> clazz = _stAdjustmentReserveDetailRemoteModel.getClass();

                Method method = clazz.getMethod("setWorkflowApprovedBy",
                        String.class);

                method.invoke(_stAdjustmentReserveDetailRemoteModel,
                    workflowApprovedBy);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getBrand() {
        return _brand;
    }

    @Override
    public void setBrand(String brand) {
        _brand = brand;

        if (_stAdjustmentReserveDetailRemoteModel != null) {
            try {
                Class<?> clazz = _stAdjustmentReserveDetailRemoteModel.getClass();

                Method method = clazz.getMethod("setBrand", String.class);

                method.invoke(_stAdjustmentReserveDetailRemoteModel, brand);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getAccountingDate() {
        return _accountingDate;
    }

    @Override
    public void setAccountingDate(Date accountingDate) {
        _accountingDate = accountingDate;

        if (_stAdjustmentReserveDetailRemoteModel != null) {
            try {
                Class<?> clazz = _stAdjustmentReserveDetailRemoteModel.getClass();

                Method method = clazz.getMethod("setAccountingDate", Date.class);

                method.invoke(_stAdjustmentReserveDetailRemoteModel,
                    accountingDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getRedemptionPeriod() {
        return _redemptionPeriod;
    }

    @Override
    public void setRedemptionPeriod(Date redemptionPeriod) {
        _redemptionPeriod = redemptionPeriod;

        if (_stAdjustmentReserveDetailRemoteModel != null) {
            try {
                Class<?> clazz = _stAdjustmentReserveDetailRemoteModel.getClass();

                Method method = clazz.getMethod("setRedemptionPeriod",
                        Date.class);

                method.invoke(_stAdjustmentReserveDetailRemoteModel,
                    redemptionPeriod);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getOriginalBatchId() {
        return _originalBatchId;
    }

    @Override
    public void setOriginalBatchId(String originalBatchId) {
        _originalBatchId = originalBatchId;

        if (_stAdjustmentReserveDetailRemoteModel != null) {
            try {
                Class<?> clazz = _stAdjustmentReserveDetailRemoteModel.getClass();

                Method method = clazz.getMethod("setOriginalBatchId",
                        String.class);

                method.invoke(_stAdjustmentReserveDetailRemoteModel,
                    originalBatchId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getAdjustmentLevel() {
        return _adjustmentLevel;
    }

    @Override
    public void setAdjustmentLevel(String adjustmentLevel) {
        _adjustmentLevel = adjustmentLevel;

        if (_stAdjustmentReserveDetailRemoteModel != null) {
            try {
                Class<?> clazz = _stAdjustmentReserveDetailRemoteModel.getClass();

                Method method = clazz.getMethod("setAdjustmentLevel",
                        String.class);

                method.invoke(_stAdjustmentReserveDetailRemoteModel,
                    adjustmentLevel);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getStAdjustmentReserveDetailRemoteModel() {
        return _stAdjustmentReserveDetailRemoteModel;
    }

    public void setStAdjustmentReserveDetailRemoteModel(
        BaseModel<?> stAdjustmentReserveDetailRemoteModel) {
        _stAdjustmentReserveDetailRemoteModel = stAdjustmentReserveDetailRemoteModel;
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

        Class<?> remoteModelClass = _stAdjustmentReserveDetailRemoteModel.getClass();

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

        Object returnValue = method.invoke(_stAdjustmentReserveDetailRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            StAdjustmentReserveDetailLocalServiceUtil.addStAdjustmentReserveDetail(this);
        } else {
            StAdjustmentReserveDetailLocalServiceUtil.updateStAdjustmentReserveDetail(this);
        }
    }

    @Override
    public StAdjustmentReserveDetail toEscapedModel() {
        return (StAdjustmentReserveDetail) ProxyUtil.newProxyInstance(StAdjustmentReserveDetail.class.getClassLoader(),
            new Class[] { StAdjustmentReserveDetail.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        StAdjustmentReserveDetailClp clone = new StAdjustmentReserveDetailClp();

        clone.setAdjustmentCreatedDate(getAdjustmentCreatedDate());
        clone.setEtlCheckRecord(getEtlCheckRecord());
        clone.setPostingIndicator(getPostingIndicator());
        clone.setModifiedDate(getModifiedDate());
        clone.setAccount(getAccount());
        clone.setCredit(getCredit());
        clone.setWorkflowApprovedDate(getWorkflowApprovedDate());
        clone.setSource(getSource());
        clone.setLineDescription(getLineDescription());
        clone.setLedger(getLedger());
        clone.setUdc6(getUdc6());
        clone.setUdc5(getUdc5());
        clone.setUdc4(getUdc4());
        clone.setWorkflowCreatedDate(getWorkflowCreatedDate());
        clone.setUdc3(getUdc3());
        clone.setUdc2(getUdc2());
        clone.setUdc1(getUdc1());
        clone.setAdjustmentType(getAdjustmentType());
        clone.setModifiedBy(getModifiedBy());
        clone.setCheckRecord(getCheckRecord());
        clone.setGlCompanyName(getGlCompanyName());
        clone.setDivision(getDivision());
        clone.setBalanceType(getBalanceType());
        clone.setSessionId(getSessionId());
        clone.setJournalName(getJournalName());
        clone.setProject(getProject());
        clone.setDebit(getDebit());
        clone.setAccountType(getAccountType());
        clone.setJournalDescription(getJournalDescription());
        clone.setCategory(getCategory());
        clone.setCreatedBy(getCreatedBy());
        clone.setCreatedDate(getCreatedDate());
        clone.setBusinessUnitId(getBusinessUnitId());
        clone.setReversalPeriodDate(getReversalPeriodDate());
        clone.setWorkflowId(getWorkflowId());
        clone.setChartOfAccounts(getChartOfAccounts());
        clone.setUserId(getUserId());
        clone.setBatchName(getBatchName());
        clone.setDatabase(getDatabase());
        clone.setCostCenter(getCostCenter());
        clone.setOutboundStatus(getOutboundStatus());
        clone.setDataAccessSet(getDataAccessSet());
        clone.setFuture1(getFuture1());
        clone.setFuture2(getFuture2());
        clone.setWorkflowName(getWorkflowName());
        clone.setWorkflowCreatedBy(getWorkflowCreatedBy());
        clone.setCurrency(getCurrency());
        clone.setBatchId(getBatchId());
        clone.setAccountCategory(getAccountCategory());
        clone.setReverseJournal(getReverseJournal());
        clone.setWorkflowApprovedBy(getWorkflowApprovedBy());
        clone.setBrand(getBrand());
        clone.setAccountingDate(getAccountingDate());
        clone.setRedemptionPeriod(getRedemptionPeriod());
        clone.setOriginalBatchId(getOriginalBatchId());
        clone.setAdjustmentLevel(getAdjustmentLevel());

        return clone;
    }

    @Override
    public int compareTo(StAdjustmentReserveDetail stAdjustmentReserveDetail) {
        StAdjustmentReserveDetailPK primaryKey = stAdjustmentReserveDetail.getPrimaryKey();

        return getPrimaryKey().compareTo(primaryKey);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof StAdjustmentReserveDetailClp)) {
            return false;
        }

        StAdjustmentReserveDetailClp stAdjustmentReserveDetail = (StAdjustmentReserveDetailClp) obj;

        StAdjustmentReserveDetailPK primaryKey = stAdjustmentReserveDetail.getPrimaryKey();

        if (getPrimaryKey().equals(primaryKey)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return getPrimaryKey().hashCode();
    }

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(113);

        sb.append("{adjustmentCreatedDate=");
        sb.append(getAdjustmentCreatedDate());
        sb.append(", etlCheckRecord=");
        sb.append(getEtlCheckRecord());
        sb.append(", postingIndicator=");
        sb.append(getPostingIndicator());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", account=");
        sb.append(getAccount());
        sb.append(", credit=");
        sb.append(getCredit());
        sb.append(", workflowApprovedDate=");
        sb.append(getWorkflowApprovedDate());
        sb.append(", source=");
        sb.append(getSource());
        sb.append(", lineDescription=");
        sb.append(getLineDescription());
        sb.append(", ledger=");
        sb.append(getLedger());
        sb.append(", udc6=");
        sb.append(getUdc6());
        sb.append(", udc5=");
        sb.append(getUdc5());
        sb.append(", udc4=");
        sb.append(getUdc4());
        sb.append(", workflowCreatedDate=");
        sb.append(getWorkflowCreatedDate());
        sb.append(", udc3=");
        sb.append(getUdc3());
        sb.append(", udc2=");
        sb.append(getUdc2());
        sb.append(", udc1=");
        sb.append(getUdc1());
        sb.append(", adjustmentType=");
        sb.append(getAdjustmentType());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", checkRecord=");
        sb.append(getCheckRecord());
        sb.append(", glCompanyName=");
        sb.append(getGlCompanyName());
        sb.append(", division=");
        sb.append(getDivision());
        sb.append(", balanceType=");
        sb.append(getBalanceType());
        sb.append(", sessionId=");
        sb.append(getSessionId());
        sb.append(", journalName=");
        sb.append(getJournalName());
        sb.append(", project=");
        sb.append(getProject());
        sb.append(", debit=");
        sb.append(getDebit());
        sb.append(", accountType=");
        sb.append(getAccountType());
        sb.append(", journalDescription=");
        sb.append(getJournalDescription());
        sb.append(", category=");
        sb.append(getCategory());
        sb.append(", createdBy=");
        sb.append(getCreatedBy());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", businessUnitId=");
        sb.append(getBusinessUnitId());
        sb.append(", reversalPeriodDate=");
        sb.append(getReversalPeriodDate());
        sb.append(", workflowId=");
        sb.append(getWorkflowId());
        sb.append(", chartOfAccounts=");
        sb.append(getChartOfAccounts());
        sb.append(", userId=");
        sb.append(getUserId());
        sb.append(", batchName=");
        sb.append(getBatchName());
        sb.append(", database=");
        sb.append(getDatabase());
        sb.append(", costCenter=");
        sb.append(getCostCenter());
        sb.append(", outboundStatus=");
        sb.append(getOutboundStatus());
        sb.append(", dataAccessSet=");
        sb.append(getDataAccessSet());
        sb.append(", future1=");
        sb.append(getFuture1());
        sb.append(", future2=");
        sb.append(getFuture2());
        sb.append(", workflowName=");
        sb.append(getWorkflowName());
        sb.append(", workflowCreatedBy=");
        sb.append(getWorkflowCreatedBy());
        sb.append(", currency=");
        sb.append(getCurrency());
        sb.append(", batchId=");
        sb.append(getBatchId());
        sb.append(", accountCategory=");
        sb.append(getAccountCategory());
        sb.append(", reverseJournal=");
        sb.append(getReverseJournal());
        sb.append(", workflowApprovedBy=");
        sb.append(getWorkflowApprovedBy());
        sb.append(", brand=");
        sb.append(getBrand());
        sb.append(", accountingDate=");
        sb.append(getAccountingDate());
        sb.append(", redemptionPeriod=");
        sb.append(getRedemptionPeriod());
        sb.append(", originalBatchId=");
        sb.append(getOriginalBatchId());
        sb.append(", adjustmentLevel=");
        sb.append(getAdjustmentLevel());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(172);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.parttwo.model.StAdjustmentReserveDetail");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>adjustmentCreatedDate</column-name><column-value><![CDATA[");
        sb.append(getAdjustmentCreatedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>etlCheckRecord</column-name><column-value><![CDATA[");
        sb.append(getEtlCheckRecord());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>postingIndicator</column-name><column-value><![CDATA[");
        sb.append(getPostingIndicator());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
        sb.append(getModifiedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>account</column-name><column-value><![CDATA[");
        sb.append(getAccount());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>credit</column-name><column-value><![CDATA[");
        sb.append(getCredit());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>workflowApprovedDate</column-name><column-value><![CDATA[");
        sb.append(getWorkflowApprovedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>source</column-name><column-value><![CDATA[");
        sb.append(getSource());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>lineDescription</column-name><column-value><![CDATA[");
        sb.append(getLineDescription());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>ledger</column-name><column-value><![CDATA[");
        sb.append(getLedger());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>udc6</column-name><column-value><![CDATA[");
        sb.append(getUdc6());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>udc5</column-name><column-value><![CDATA[");
        sb.append(getUdc5());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>udc4</column-name><column-value><![CDATA[");
        sb.append(getUdc4());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>workflowCreatedDate</column-name><column-value><![CDATA[");
        sb.append(getWorkflowCreatedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>udc3</column-name><column-value><![CDATA[");
        sb.append(getUdc3());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>udc2</column-name><column-value><![CDATA[");
        sb.append(getUdc2());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>udc1</column-name><column-value><![CDATA[");
        sb.append(getUdc1());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>adjustmentType</column-name><column-value><![CDATA[");
        sb.append(getAdjustmentType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedBy</column-name><column-value><![CDATA[");
        sb.append(getModifiedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>checkRecord</column-name><column-value><![CDATA[");
        sb.append(getCheckRecord());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>glCompanyName</column-name><column-value><![CDATA[");
        sb.append(getGlCompanyName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>division</column-name><column-value><![CDATA[");
        sb.append(getDivision());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>balanceType</column-name><column-value><![CDATA[");
        sb.append(getBalanceType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>sessionId</column-name><column-value><![CDATA[");
        sb.append(getSessionId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>journalName</column-name><column-value><![CDATA[");
        sb.append(getJournalName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>project</column-name><column-value><![CDATA[");
        sb.append(getProject());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>debit</column-name><column-value><![CDATA[");
        sb.append(getDebit());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>accountType</column-name><column-value><![CDATA[");
        sb.append(getAccountType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>journalDescription</column-name><column-value><![CDATA[");
        sb.append(getJournalDescription());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>category</column-name><column-value><![CDATA[");
        sb.append(getCategory());
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
            "<column><column-name>businessUnitId</column-name><column-value><![CDATA[");
        sb.append(getBusinessUnitId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>reversalPeriodDate</column-name><column-value><![CDATA[");
        sb.append(getReversalPeriodDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>workflowId</column-name><column-value><![CDATA[");
        sb.append(getWorkflowId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>chartOfAccounts</column-name><column-value><![CDATA[");
        sb.append(getChartOfAccounts());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>userId</column-name><column-value><![CDATA[");
        sb.append(getUserId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>batchName</column-name><column-value><![CDATA[");
        sb.append(getBatchName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>database</column-name><column-value><![CDATA[");
        sb.append(getDatabase());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>costCenter</column-name><column-value><![CDATA[");
        sb.append(getCostCenter());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>outboundStatus</column-name><column-value><![CDATA[");
        sb.append(getOutboundStatus());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>dataAccessSet</column-name><column-value><![CDATA[");
        sb.append(getDataAccessSet());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>future1</column-name><column-value><![CDATA[");
        sb.append(getFuture1());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>future2</column-name><column-value><![CDATA[");
        sb.append(getFuture2());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>workflowName</column-name><column-value><![CDATA[");
        sb.append(getWorkflowName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>workflowCreatedBy</column-name><column-value><![CDATA[");
        sb.append(getWorkflowCreatedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>currency</column-name><column-value><![CDATA[");
        sb.append(getCurrency());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>batchId</column-name><column-value><![CDATA[");
        sb.append(getBatchId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>accountCategory</column-name><column-value><![CDATA[");
        sb.append(getAccountCategory());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>reverseJournal</column-name><column-value><![CDATA[");
        sb.append(getReverseJournal());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>workflowApprovedBy</column-name><column-value><![CDATA[");
        sb.append(getWorkflowApprovedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>brand</column-name><column-value><![CDATA[");
        sb.append(getBrand());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>accountingDate</column-name><column-value><![CDATA[");
        sb.append(getAccountingDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>redemptionPeriod</column-name><column-value><![CDATA[");
        sb.append(getRedemptionPeriod());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>originalBatchId</column-name><column-value><![CDATA[");
        sb.append(getOriginalBatchId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>adjustmentLevel</column-name><column-value><![CDATA[");
        sb.append(getAdjustmentLevel());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
