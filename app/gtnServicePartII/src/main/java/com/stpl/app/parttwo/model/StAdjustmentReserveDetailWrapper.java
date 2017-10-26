package com.stpl.app.parttwo.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link StAdjustmentReserveDetail}.
 * </p>
 *
 * @author
 * @see StAdjustmentReserveDetail
 * @generated
 */
public class StAdjustmentReserveDetailWrapper
    implements StAdjustmentReserveDetail,
        ModelWrapper<StAdjustmentReserveDetail> {
    private StAdjustmentReserveDetail _stAdjustmentReserveDetail;

    public StAdjustmentReserveDetailWrapper(
        StAdjustmentReserveDetail stAdjustmentReserveDetail) {
        _stAdjustmentReserveDetail = stAdjustmentReserveDetail;
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

    /**
    * Returns the primary key of this st adjustment reserve detail.
    *
    * @return the primary key of this st adjustment reserve detail
    */
    @Override
    public com.stpl.app.parttwo.service.persistence.StAdjustmentReserveDetailPK getPrimaryKey() {
        return _stAdjustmentReserveDetail.getPrimaryKey();
    }

    /**
    * Sets the primary key of this st adjustment reserve detail.
    *
    * @param primaryKey the primary key of this st adjustment reserve detail
    */
    @Override
    public void setPrimaryKey(
        com.stpl.app.parttwo.service.persistence.StAdjustmentReserveDetailPK primaryKey) {
        _stAdjustmentReserveDetail.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the adjustment created date of this st adjustment reserve detail.
    *
    * @return the adjustment created date of this st adjustment reserve detail
    */
    @Override
    public java.util.Date getAdjustmentCreatedDate() {
        return _stAdjustmentReserveDetail.getAdjustmentCreatedDate();
    }

    /**
    * Sets the adjustment created date of this st adjustment reserve detail.
    *
    * @param adjustmentCreatedDate the adjustment created date of this st adjustment reserve detail
    */
    @Override
    public void setAdjustmentCreatedDate(java.util.Date adjustmentCreatedDate) {
        _stAdjustmentReserveDetail.setAdjustmentCreatedDate(adjustmentCreatedDate);
    }

    /**
    * Returns the etl check record of this st adjustment reserve detail.
    *
    * @return the etl check record of this st adjustment reserve detail
    */
    @Override
    public boolean getEtlCheckRecord() {
        return _stAdjustmentReserveDetail.getEtlCheckRecord();
    }

    /**
    * Returns <code>true</code> if this st adjustment reserve detail is etl check record.
    *
    * @return <code>true</code> if this st adjustment reserve detail is etl check record; <code>false</code> otherwise
    */
    @Override
    public boolean isEtlCheckRecord() {
        return _stAdjustmentReserveDetail.isEtlCheckRecord();
    }

    /**
    * Sets whether this st adjustment reserve detail is etl check record.
    *
    * @param etlCheckRecord the etl check record of this st adjustment reserve detail
    */
    @Override
    public void setEtlCheckRecord(boolean etlCheckRecord) {
        _stAdjustmentReserveDetail.setEtlCheckRecord(etlCheckRecord);
    }

    /**
    * Returns the posting indicator of this st adjustment reserve detail.
    *
    * @return the posting indicator of this st adjustment reserve detail
    */
    @Override
    public java.lang.String getPostingIndicator() {
        return _stAdjustmentReserveDetail.getPostingIndicator();
    }

    /**
    * Sets the posting indicator of this st adjustment reserve detail.
    *
    * @param postingIndicator the posting indicator of this st adjustment reserve detail
    */
    @Override
    public void setPostingIndicator(java.lang.String postingIndicator) {
        _stAdjustmentReserveDetail.setPostingIndicator(postingIndicator);
    }

    /**
    * Returns the modified date of this st adjustment reserve detail.
    *
    * @return the modified date of this st adjustment reserve detail
    */
    @Override
    public java.util.Date getModifiedDate() {
        return _stAdjustmentReserveDetail.getModifiedDate();
    }

    /**
    * Sets the modified date of this st adjustment reserve detail.
    *
    * @param modifiedDate the modified date of this st adjustment reserve detail
    */
    @Override
    public void setModifiedDate(java.util.Date modifiedDate) {
        _stAdjustmentReserveDetail.setModifiedDate(modifiedDate);
    }

    /**
    * Returns the account of this st adjustment reserve detail.
    *
    * @return the account of this st adjustment reserve detail
    */
    @Override
    public java.lang.String getAccount() {
        return _stAdjustmentReserveDetail.getAccount();
    }

    /**
    * Sets the account of this st adjustment reserve detail.
    *
    * @param account the account of this st adjustment reserve detail
    */
    @Override
    public void setAccount(java.lang.String account) {
        _stAdjustmentReserveDetail.setAccount(account);
    }

    /**
    * Returns the credit of this st adjustment reserve detail.
    *
    * @return the credit of this st adjustment reserve detail
    */
    @Override
    public java.lang.String getCredit() {
        return _stAdjustmentReserveDetail.getCredit();
    }

    /**
    * Sets the credit of this st adjustment reserve detail.
    *
    * @param credit the credit of this st adjustment reserve detail
    */
    @Override
    public void setCredit(java.lang.String credit) {
        _stAdjustmentReserveDetail.setCredit(credit);
    }

    /**
    * Returns the workflow approved date of this st adjustment reserve detail.
    *
    * @return the workflow approved date of this st adjustment reserve detail
    */
    @Override
    public java.util.Date getWorkflowApprovedDate() {
        return _stAdjustmentReserveDetail.getWorkflowApprovedDate();
    }

    /**
    * Sets the workflow approved date of this st adjustment reserve detail.
    *
    * @param workflowApprovedDate the workflow approved date of this st adjustment reserve detail
    */
    @Override
    public void setWorkflowApprovedDate(java.util.Date workflowApprovedDate) {
        _stAdjustmentReserveDetail.setWorkflowApprovedDate(workflowApprovedDate);
    }

    /**
    * Returns the source of this st adjustment reserve detail.
    *
    * @return the source of this st adjustment reserve detail
    */
    @Override
    public java.lang.String getSource() {
        return _stAdjustmentReserveDetail.getSource();
    }

    /**
    * Sets the source of this st adjustment reserve detail.
    *
    * @param source the source of this st adjustment reserve detail
    */
    @Override
    public void setSource(java.lang.String source) {
        _stAdjustmentReserveDetail.setSource(source);
    }

    /**
    * Returns the line description of this st adjustment reserve detail.
    *
    * @return the line description of this st adjustment reserve detail
    */
    @Override
    public java.lang.String getLineDescription() {
        return _stAdjustmentReserveDetail.getLineDescription();
    }

    /**
    * Sets the line description of this st adjustment reserve detail.
    *
    * @param lineDescription the line description of this st adjustment reserve detail
    */
    @Override
    public void setLineDescription(java.lang.String lineDescription) {
        _stAdjustmentReserveDetail.setLineDescription(lineDescription);
    }

    /**
    * Returns the ledger of this st adjustment reserve detail.
    *
    * @return the ledger of this st adjustment reserve detail
    */
    @Override
    public java.lang.String getLedger() {
        return _stAdjustmentReserveDetail.getLedger();
    }

    /**
    * Sets the ledger of this st adjustment reserve detail.
    *
    * @param ledger the ledger of this st adjustment reserve detail
    */
    @Override
    public void setLedger(java.lang.String ledger) {
        _stAdjustmentReserveDetail.setLedger(ledger);
    }

    /**
    * Returns the udc6 of this st adjustment reserve detail.
    *
    * @return the udc6 of this st adjustment reserve detail
    */
    @Override
    public java.lang.String getUdc6() {
        return _stAdjustmentReserveDetail.getUdc6();
    }

    /**
    * Sets the udc6 of this st adjustment reserve detail.
    *
    * @param udc6 the udc6 of this st adjustment reserve detail
    */
    @Override
    public void setUdc6(java.lang.String udc6) {
        _stAdjustmentReserveDetail.setUdc6(udc6);
    }

    /**
    * Returns the udc5 of this st adjustment reserve detail.
    *
    * @return the udc5 of this st adjustment reserve detail
    */
    @Override
    public java.lang.String getUdc5() {
        return _stAdjustmentReserveDetail.getUdc5();
    }

    /**
    * Sets the udc5 of this st adjustment reserve detail.
    *
    * @param udc5 the udc5 of this st adjustment reserve detail
    */
    @Override
    public void setUdc5(java.lang.String udc5) {
        _stAdjustmentReserveDetail.setUdc5(udc5);
    }

    /**
    * Returns the udc4 of this st adjustment reserve detail.
    *
    * @return the udc4 of this st adjustment reserve detail
    */
    @Override
    public java.lang.String getUdc4() {
        return _stAdjustmentReserveDetail.getUdc4();
    }

    /**
    * Sets the udc4 of this st adjustment reserve detail.
    *
    * @param udc4 the udc4 of this st adjustment reserve detail
    */
    @Override
    public void setUdc4(java.lang.String udc4) {
        _stAdjustmentReserveDetail.setUdc4(udc4);
    }

    /**
    * Returns the workflow created date of this st adjustment reserve detail.
    *
    * @return the workflow created date of this st adjustment reserve detail
    */
    @Override
    public java.util.Date getWorkflowCreatedDate() {
        return _stAdjustmentReserveDetail.getWorkflowCreatedDate();
    }

    /**
    * Sets the workflow created date of this st adjustment reserve detail.
    *
    * @param workflowCreatedDate the workflow created date of this st adjustment reserve detail
    */
    @Override
    public void setWorkflowCreatedDate(java.util.Date workflowCreatedDate) {
        _stAdjustmentReserveDetail.setWorkflowCreatedDate(workflowCreatedDate);
    }

    /**
    * Returns the udc3 of this st adjustment reserve detail.
    *
    * @return the udc3 of this st adjustment reserve detail
    */
    @Override
    public java.lang.String getUdc3() {
        return _stAdjustmentReserveDetail.getUdc3();
    }

    /**
    * Sets the udc3 of this st adjustment reserve detail.
    *
    * @param udc3 the udc3 of this st adjustment reserve detail
    */
    @Override
    public void setUdc3(java.lang.String udc3) {
        _stAdjustmentReserveDetail.setUdc3(udc3);
    }

    /**
    * Returns the udc2 of this st adjustment reserve detail.
    *
    * @return the udc2 of this st adjustment reserve detail
    */
    @Override
    public java.lang.String getUdc2() {
        return _stAdjustmentReserveDetail.getUdc2();
    }

    /**
    * Sets the udc2 of this st adjustment reserve detail.
    *
    * @param udc2 the udc2 of this st adjustment reserve detail
    */
    @Override
    public void setUdc2(java.lang.String udc2) {
        _stAdjustmentReserveDetail.setUdc2(udc2);
    }

    /**
    * Returns the udc1 of this st adjustment reserve detail.
    *
    * @return the udc1 of this st adjustment reserve detail
    */
    @Override
    public java.lang.String getUdc1() {
        return _stAdjustmentReserveDetail.getUdc1();
    }

    /**
    * Sets the udc1 of this st adjustment reserve detail.
    *
    * @param udc1 the udc1 of this st adjustment reserve detail
    */
    @Override
    public void setUdc1(java.lang.String udc1) {
        _stAdjustmentReserveDetail.setUdc1(udc1);
    }

    /**
    * Returns the adjustment type of this st adjustment reserve detail.
    *
    * @return the adjustment type of this st adjustment reserve detail
    */
    @Override
    public java.lang.String getAdjustmentType() {
        return _stAdjustmentReserveDetail.getAdjustmentType();
    }

    /**
    * Sets the adjustment type of this st adjustment reserve detail.
    *
    * @param adjustmentType the adjustment type of this st adjustment reserve detail
    */
    @Override
    public void setAdjustmentType(java.lang.String adjustmentType) {
        _stAdjustmentReserveDetail.setAdjustmentType(adjustmentType);
    }

    /**
    * Returns the modified by of this st adjustment reserve detail.
    *
    * @return the modified by of this st adjustment reserve detail
    */
    @Override
    public java.lang.String getModifiedBy() {
        return _stAdjustmentReserveDetail.getModifiedBy();
    }

    /**
    * Sets the modified by of this st adjustment reserve detail.
    *
    * @param modifiedBy the modified by of this st adjustment reserve detail
    */
    @Override
    public void setModifiedBy(java.lang.String modifiedBy) {
        _stAdjustmentReserveDetail.setModifiedBy(modifiedBy);
    }

    /**
    * Returns the check record of this st adjustment reserve detail.
    *
    * @return the check record of this st adjustment reserve detail
    */
    @Override
    public boolean getCheckRecord() {
        return _stAdjustmentReserveDetail.getCheckRecord();
    }

    /**
    * Returns <code>true</code> if this st adjustment reserve detail is check record.
    *
    * @return <code>true</code> if this st adjustment reserve detail is check record; <code>false</code> otherwise
    */
    @Override
    public boolean isCheckRecord() {
        return _stAdjustmentReserveDetail.isCheckRecord();
    }

    /**
    * Sets whether this st adjustment reserve detail is check record.
    *
    * @param checkRecord the check record of this st adjustment reserve detail
    */
    @Override
    public void setCheckRecord(boolean checkRecord) {
        _stAdjustmentReserveDetail.setCheckRecord(checkRecord);
    }

    /**
    * Returns the gl company name of this st adjustment reserve detail.
    *
    * @return the gl company name of this st adjustment reserve detail
    */
    @Override
    public java.lang.String getGlCompanyName() {
        return _stAdjustmentReserveDetail.getGlCompanyName();
    }

    /**
    * Sets the gl company name of this st adjustment reserve detail.
    *
    * @param glCompanyName the gl company name of this st adjustment reserve detail
    */
    @Override
    public void setGlCompanyName(java.lang.String glCompanyName) {
        _stAdjustmentReserveDetail.setGlCompanyName(glCompanyName);
    }

    /**
    * Returns the division of this st adjustment reserve detail.
    *
    * @return the division of this st adjustment reserve detail
    */
    @Override
    public java.lang.String getDivision() {
        return _stAdjustmentReserveDetail.getDivision();
    }

    /**
    * Sets the division of this st adjustment reserve detail.
    *
    * @param division the division of this st adjustment reserve detail
    */
    @Override
    public void setDivision(java.lang.String division) {
        _stAdjustmentReserveDetail.setDivision(division);
    }

    /**
    * Returns the balance type of this st adjustment reserve detail.
    *
    * @return the balance type of this st adjustment reserve detail
    */
    @Override
    public java.lang.String getBalanceType() {
        return _stAdjustmentReserveDetail.getBalanceType();
    }

    /**
    * Sets the balance type of this st adjustment reserve detail.
    *
    * @param balanceType the balance type of this st adjustment reserve detail
    */
    @Override
    public void setBalanceType(java.lang.String balanceType) {
        _stAdjustmentReserveDetail.setBalanceType(balanceType);
    }

    /**
    * Returns the session ID of this st adjustment reserve detail.
    *
    * @return the session ID of this st adjustment reserve detail
    */
    @Override
    public java.lang.String getSessionId() {
        return _stAdjustmentReserveDetail.getSessionId();
    }

    /**
    * Sets the session ID of this st adjustment reserve detail.
    *
    * @param sessionId the session ID of this st adjustment reserve detail
    */
    @Override
    public void setSessionId(java.lang.String sessionId) {
        _stAdjustmentReserveDetail.setSessionId(sessionId);
    }

    /**
    * Returns the journal name of this st adjustment reserve detail.
    *
    * @return the journal name of this st adjustment reserve detail
    */
    @Override
    public java.lang.String getJournalName() {
        return _stAdjustmentReserveDetail.getJournalName();
    }

    /**
    * Sets the journal name of this st adjustment reserve detail.
    *
    * @param journalName the journal name of this st adjustment reserve detail
    */
    @Override
    public void setJournalName(java.lang.String journalName) {
        _stAdjustmentReserveDetail.setJournalName(journalName);
    }

    /**
    * Returns the project of this st adjustment reserve detail.
    *
    * @return the project of this st adjustment reserve detail
    */
    @Override
    public java.lang.String getProject() {
        return _stAdjustmentReserveDetail.getProject();
    }

    /**
    * Sets the project of this st adjustment reserve detail.
    *
    * @param project the project of this st adjustment reserve detail
    */
    @Override
    public void setProject(java.lang.String project) {
        _stAdjustmentReserveDetail.setProject(project);
    }

    /**
    * Returns the debit of this st adjustment reserve detail.
    *
    * @return the debit of this st adjustment reserve detail
    */
    @Override
    public java.lang.String getDebit() {
        return _stAdjustmentReserveDetail.getDebit();
    }

    /**
    * Sets the debit of this st adjustment reserve detail.
    *
    * @param debit the debit of this st adjustment reserve detail
    */
    @Override
    public void setDebit(java.lang.String debit) {
        _stAdjustmentReserveDetail.setDebit(debit);
    }

    /**
    * Returns the account type of this st adjustment reserve detail.
    *
    * @return the account type of this st adjustment reserve detail
    */
    @Override
    public java.lang.String getAccountType() {
        return _stAdjustmentReserveDetail.getAccountType();
    }

    /**
    * Sets the account type of this st adjustment reserve detail.
    *
    * @param accountType the account type of this st adjustment reserve detail
    */
    @Override
    public void setAccountType(java.lang.String accountType) {
        _stAdjustmentReserveDetail.setAccountType(accountType);
    }

    /**
    * Returns the journal description of this st adjustment reserve detail.
    *
    * @return the journal description of this st adjustment reserve detail
    */
    @Override
    public java.lang.String getJournalDescription() {
        return _stAdjustmentReserveDetail.getJournalDescription();
    }

    /**
    * Sets the journal description of this st adjustment reserve detail.
    *
    * @param journalDescription the journal description of this st adjustment reserve detail
    */
    @Override
    public void setJournalDescription(java.lang.String journalDescription) {
        _stAdjustmentReserveDetail.setJournalDescription(journalDescription);
    }

    /**
    * Returns the category of this st adjustment reserve detail.
    *
    * @return the category of this st adjustment reserve detail
    */
    @Override
    public java.lang.String getCategory() {
        return _stAdjustmentReserveDetail.getCategory();
    }

    /**
    * Sets the category of this st adjustment reserve detail.
    *
    * @param category the category of this st adjustment reserve detail
    */
    @Override
    public void setCategory(java.lang.String category) {
        _stAdjustmentReserveDetail.setCategory(category);
    }

    /**
    * Returns the created by of this st adjustment reserve detail.
    *
    * @return the created by of this st adjustment reserve detail
    */
    @Override
    public java.lang.String getCreatedBy() {
        return _stAdjustmentReserveDetail.getCreatedBy();
    }

    /**
    * Sets the created by of this st adjustment reserve detail.
    *
    * @param createdBy the created by of this st adjustment reserve detail
    */
    @Override
    public void setCreatedBy(java.lang.String createdBy) {
        _stAdjustmentReserveDetail.setCreatedBy(createdBy);
    }

    /**
    * Returns the created date of this st adjustment reserve detail.
    *
    * @return the created date of this st adjustment reserve detail
    */
    @Override
    public java.util.Date getCreatedDate() {
        return _stAdjustmentReserveDetail.getCreatedDate();
    }

    /**
    * Sets the created date of this st adjustment reserve detail.
    *
    * @param createdDate the created date of this st adjustment reserve detail
    */
    @Override
    public void setCreatedDate(java.util.Date createdDate) {
        _stAdjustmentReserveDetail.setCreatedDate(createdDate);
    }

    /**
    * Returns the business unit ID of this st adjustment reserve detail.
    *
    * @return the business unit ID of this st adjustment reserve detail
    */
    @Override
    public java.lang.String getBusinessUnitId() {
        return _stAdjustmentReserveDetail.getBusinessUnitId();
    }

    /**
    * Sets the business unit ID of this st adjustment reserve detail.
    *
    * @param businessUnitId the business unit ID of this st adjustment reserve detail
    */
    @Override
    public void setBusinessUnitId(java.lang.String businessUnitId) {
        _stAdjustmentReserveDetail.setBusinessUnitId(businessUnitId);
    }

    /**
    * Returns the reversal period date of this st adjustment reserve detail.
    *
    * @return the reversal period date of this st adjustment reserve detail
    */
    @Override
    public java.util.Date getReversalPeriodDate() {
        return _stAdjustmentReserveDetail.getReversalPeriodDate();
    }

    /**
    * Sets the reversal period date of this st adjustment reserve detail.
    *
    * @param reversalPeriodDate the reversal period date of this st adjustment reserve detail
    */
    @Override
    public void setReversalPeriodDate(java.util.Date reversalPeriodDate) {
        _stAdjustmentReserveDetail.setReversalPeriodDate(reversalPeriodDate);
    }

    /**
    * Returns the workflow ID of this st adjustment reserve detail.
    *
    * @return the workflow ID of this st adjustment reserve detail
    */
    @Override
    public java.lang.String getWorkflowId() {
        return _stAdjustmentReserveDetail.getWorkflowId();
    }

    /**
    * Sets the workflow ID of this st adjustment reserve detail.
    *
    * @param workflowId the workflow ID of this st adjustment reserve detail
    */
    @Override
    public void setWorkflowId(java.lang.String workflowId) {
        _stAdjustmentReserveDetail.setWorkflowId(workflowId);
    }

    /**
    * Returns the chart of accounts of this st adjustment reserve detail.
    *
    * @return the chart of accounts of this st adjustment reserve detail
    */
    @Override
    public java.lang.String getChartOfAccounts() {
        return _stAdjustmentReserveDetail.getChartOfAccounts();
    }

    /**
    * Sets the chart of accounts of this st adjustment reserve detail.
    *
    * @param chartOfAccounts the chart of accounts of this st adjustment reserve detail
    */
    @Override
    public void setChartOfAccounts(java.lang.String chartOfAccounts) {
        _stAdjustmentReserveDetail.setChartOfAccounts(chartOfAccounts);
    }

    /**
    * Returns the user ID of this st adjustment reserve detail.
    *
    * @return the user ID of this st adjustment reserve detail
    */
    @Override
    public java.lang.String getUserId() {
        return _stAdjustmentReserveDetail.getUserId();
    }

    /**
    * Sets the user ID of this st adjustment reserve detail.
    *
    * @param userId the user ID of this st adjustment reserve detail
    */
    @Override
    public void setUserId(java.lang.String userId) {
        _stAdjustmentReserveDetail.setUserId(userId);
    }

    /**
    * Returns the batch name of this st adjustment reserve detail.
    *
    * @return the batch name of this st adjustment reserve detail
    */
    @Override
    public java.lang.String getBatchName() {
        return _stAdjustmentReserveDetail.getBatchName();
    }

    /**
    * Sets the batch name of this st adjustment reserve detail.
    *
    * @param batchName the batch name of this st adjustment reserve detail
    */
    @Override
    public void setBatchName(java.lang.String batchName) {
        _stAdjustmentReserveDetail.setBatchName(batchName);
    }

    /**
    * Returns the database of this st adjustment reserve detail.
    *
    * @return the database of this st adjustment reserve detail
    */
    @Override
    public java.lang.String getDatabase() {
        return _stAdjustmentReserveDetail.getDatabase();
    }

    /**
    * Sets the database of this st adjustment reserve detail.
    *
    * @param database the database of this st adjustment reserve detail
    */
    @Override
    public void setDatabase(java.lang.String database) {
        _stAdjustmentReserveDetail.setDatabase(database);
    }

    /**
    * Returns the cost center of this st adjustment reserve detail.
    *
    * @return the cost center of this st adjustment reserve detail
    */
    @Override
    public java.lang.String getCostCenter() {
        return _stAdjustmentReserveDetail.getCostCenter();
    }

    /**
    * Sets the cost center of this st adjustment reserve detail.
    *
    * @param costCenter the cost center of this st adjustment reserve detail
    */
    @Override
    public void setCostCenter(java.lang.String costCenter) {
        _stAdjustmentReserveDetail.setCostCenter(costCenter);
    }

    /**
    * Returns the outbound status of this st adjustment reserve detail.
    *
    * @return the outbound status of this st adjustment reserve detail
    */
    @Override
    public java.lang.String getOutboundStatus() {
        return _stAdjustmentReserveDetail.getOutboundStatus();
    }

    /**
    * Sets the outbound status of this st adjustment reserve detail.
    *
    * @param outboundStatus the outbound status of this st adjustment reserve detail
    */
    @Override
    public void setOutboundStatus(java.lang.String outboundStatus) {
        _stAdjustmentReserveDetail.setOutboundStatus(outboundStatus);
    }

    /**
    * Returns the data access set of this st adjustment reserve detail.
    *
    * @return the data access set of this st adjustment reserve detail
    */
    @Override
    public java.lang.String getDataAccessSet() {
        return _stAdjustmentReserveDetail.getDataAccessSet();
    }

    /**
    * Sets the data access set of this st adjustment reserve detail.
    *
    * @param dataAccessSet the data access set of this st adjustment reserve detail
    */
    @Override
    public void setDataAccessSet(java.lang.String dataAccessSet) {
        _stAdjustmentReserveDetail.setDataAccessSet(dataAccessSet);
    }

    /**
    * Returns the future1 of this st adjustment reserve detail.
    *
    * @return the future1 of this st adjustment reserve detail
    */
    @Override
    public java.lang.String getFuture1() {
        return _stAdjustmentReserveDetail.getFuture1();
    }

    /**
    * Sets the future1 of this st adjustment reserve detail.
    *
    * @param future1 the future1 of this st adjustment reserve detail
    */
    @Override
    public void setFuture1(java.lang.String future1) {
        _stAdjustmentReserveDetail.setFuture1(future1);
    }

    /**
    * Returns the future2 of this st adjustment reserve detail.
    *
    * @return the future2 of this st adjustment reserve detail
    */
    @Override
    public java.lang.String getFuture2() {
        return _stAdjustmentReserveDetail.getFuture2();
    }

    /**
    * Sets the future2 of this st adjustment reserve detail.
    *
    * @param future2 the future2 of this st adjustment reserve detail
    */
    @Override
    public void setFuture2(java.lang.String future2) {
        _stAdjustmentReserveDetail.setFuture2(future2);
    }

    /**
    * Returns the workflow name of this st adjustment reserve detail.
    *
    * @return the workflow name of this st adjustment reserve detail
    */
    @Override
    public java.lang.String getWorkflowName() {
        return _stAdjustmentReserveDetail.getWorkflowName();
    }

    /**
    * Sets the workflow name of this st adjustment reserve detail.
    *
    * @param workflowName the workflow name of this st adjustment reserve detail
    */
    @Override
    public void setWorkflowName(java.lang.String workflowName) {
        _stAdjustmentReserveDetail.setWorkflowName(workflowName);
    }

    /**
    * Returns the workflow created by of this st adjustment reserve detail.
    *
    * @return the workflow created by of this st adjustment reserve detail
    */
    @Override
    public java.lang.String getWorkflowCreatedBy() {
        return _stAdjustmentReserveDetail.getWorkflowCreatedBy();
    }

    /**
    * Sets the workflow created by of this st adjustment reserve detail.
    *
    * @param workflowCreatedBy the workflow created by of this st adjustment reserve detail
    */
    @Override
    public void setWorkflowCreatedBy(java.lang.String workflowCreatedBy) {
        _stAdjustmentReserveDetail.setWorkflowCreatedBy(workflowCreatedBy);
    }

    /**
    * Returns the currency of this st adjustment reserve detail.
    *
    * @return the currency of this st adjustment reserve detail
    */
    @Override
    public java.lang.String getCurrency() {
        return _stAdjustmentReserveDetail.getCurrency();
    }

    /**
    * Sets the currency of this st adjustment reserve detail.
    *
    * @param currency the currency of this st adjustment reserve detail
    */
    @Override
    public void setCurrency(java.lang.String currency) {
        _stAdjustmentReserveDetail.setCurrency(currency);
    }

    /**
    * Returns the batch ID of this st adjustment reserve detail.
    *
    * @return the batch ID of this st adjustment reserve detail
    */
    @Override
    public java.lang.String getBatchId() {
        return _stAdjustmentReserveDetail.getBatchId();
    }

    /**
    * Sets the batch ID of this st adjustment reserve detail.
    *
    * @param batchId the batch ID of this st adjustment reserve detail
    */
    @Override
    public void setBatchId(java.lang.String batchId) {
        _stAdjustmentReserveDetail.setBatchId(batchId);
    }

    /**
    * Returns the account category of this st adjustment reserve detail.
    *
    * @return the account category of this st adjustment reserve detail
    */
    @Override
    public java.lang.String getAccountCategory() {
        return _stAdjustmentReserveDetail.getAccountCategory();
    }

    /**
    * Sets the account category of this st adjustment reserve detail.
    *
    * @param accountCategory the account category of this st adjustment reserve detail
    */
    @Override
    public void setAccountCategory(java.lang.String accountCategory) {
        _stAdjustmentReserveDetail.setAccountCategory(accountCategory);
    }

    /**
    * Returns the reverse journal of this st adjustment reserve detail.
    *
    * @return the reverse journal of this st adjustment reserve detail
    */
    @Override
    public java.lang.String getReverseJournal() {
        return _stAdjustmentReserveDetail.getReverseJournal();
    }

    /**
    * Sets the reverse journal of this st adjustment reserve detail.
    *
    * @param reverseJournal the reverse journal of this st adjustment reserve detail
    */
    @Override
    public void setReverseJournal(java.lang.String reverseJournal) {
        _stAdjustmentReserveDetail.setReverseJournal(reverseJournal);
    }

    /**
    * Returns the workflow approved by of this st adjustment reserve detail.
    *
    * @return the workflow approved by of this st adjustment reserve detail
    */
    @Override
    public java.lang.String getWorkflowApprovedBy() {
        return _stAdjustmentReserveDetail.getWorkflowApprovedBy();
    }

    /**
    * Sets the workflow approved by of this st adjustment reserve detail.
    *
    * @param workflowApprovedBy the workflow approved by of this st adjustment reserve detail
    */
    @Override
    public void setWorkflowApprovedBy(java.lang.String workflowApprovedBy) {
        _stAdjustmentReserveDetail.setWorkflowApprovedBy(workflowApprovedBy);
    }

    /**
    * Returns the brand of this st adjustment reserve detail.
    *
    * @return the brand of this st adjustment reserve detail
    */
    @Override
    public java.lang.String getBrand() {
        return _stAdjustmentReserveDetail.getBrand();
    }

    /**
    * Sets the brand of this st adjustment reserve detail.
    *
    * @param brand the brand of this st adjustment reserve detail
    */
    @Override
    public void setBrand(java.lang.String brand) {
        _stAdjustmentReserveDetail.setBrand(brand);
    }

    /**
    * Returns the accounting date of this st adjustment reserve detail.
    *
    * @return the accounting date of this st adjustment reserve detail
    */
    @Override
    public java.util.Date getAccountingDate() {
        return _stAdjustmentReserveDetail.getAccountingDate();
    }

    /**
    * Sets the accounting date of this st adjustment reserve detail.
    *
    * @param accountingDate the accounting date of this st adjustment reserve detail
    */
    @Override
    public void setAccountingDate(java.util.Date accountingDate) {
        _stAdjustmentReserveDetail.setAccountingDate(accountingDate);
    }

    /**
    * Returns the redemption period of this st adjustment reserve detail.
    *
    * @return the redemption period of this st adjustment reserve detail
    */
    @Override
    public java.util.Date getRedemptionPeriod() {
        return _stAdjustmentReserveDetail.getRedemptionPeriod();
    }

    /**
    * Sets the redemption period of this st adjustment reserve detail.
    *
    * @param redemptionPeriod the redemption period of this st adjustment reserve detail
    */
    @Override
    public void setRedemptionPeriod(java.util.Date redemptionPeriod) {
        _stAdjustmentReserveDetail.setRedemptionPeriod(redemptionPeriod);
    }

    /**
    * Returns the original batch ID of this st adjustment reserve detail.
    *
    * @return the original batch ID of this st adjustment reserve detail
    */
    @Override
    public java.lang.String getOriginalBatchId() {
        return _stAdjustmentReserveDetail.getOriginalBatchId();
    }

    /**
    * Sets the original batch ID of this st adjustment reserve detail.
    *
    * @param originalBatchId the original batch ID of this st adjustment reserve detail
    */
    @Override
    public void setOriginalBatchId(java.lang.String originalBatchId) {
        _stAdjustmentReserveDetail.setOriginalBatchId(originalBatchId);
    }

    /**
    * Returns the adjustment level of this st adjustment reserve detail.
    *
    * @return the adjustment level of this st adjustment reserve detail
    */
    @Override
    public java.lang.String getAdjustmentLevel() {
        return _stAdjustmentReserveDetail.getAdjustmentLevel();
    }

    /**
    * Sets the adjustment level of this st adjustment reserve detail.
    *
    * @param adjustmentLevel the adjustment level of this st adjustment reserve detail
    */
    @Override
    public void setAdjustmentLevel(java.lang.String adjustmentLevel) {
        _stAdjustmentReserveDetail.setAdjustmentLevel(adjustmentLevel);
    }

    @Override
    public boolean isNew() {
        return _stAdjustmentReserveDetail.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _stAdjustmentReserveDetail.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _stAdjustmentReserveDetail.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _stAdjustmentReserveDetail.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _stAdjustmentReserveDetail.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _stAdjustmentReserveDetail.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _stAdjustmentReserveDetail.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _stAdjustmentReserveDetail.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _stAdjustmentReserveDetail.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _stAdjustmentReserveDetail.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _stAdjustmentReserveDetail.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new StAdjustmentReserveDetailWrapper((StAdjustmentReserveDetail) _stAdjustmentReserveDetail.clone());
    }

    @Override
    public int compareTo(StAdjustmentReserveDetail stAdjustmentReserveDetail) {
        return _stAdjustmentReserveDetail.compareTo(stAdjustmentReserveDetail);
    }

    @Override
    public int hashCode() {
        return _stAdjustmentReserveDetail.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<StAdjustmentReserveDetail> toCacheModel() {
        return _stAdjustmentReserveDetail.toCacheModel();
    }

    @Override
    public StAdjustmentReserveDetail toEscapedModel() {
        return new StAdjustmentReserveDetailWrapper(_stAdjustmentReserveDetail.toEscapedModel());
    }

    @Override
    public StAdjustmentReserveDetail toUnescapedModel() {
        return new StAdjustmentReserveDetailWrapper(_stAdjustmentReserveDetail.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _stAdjustmentReserveDetail.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _stAdjustmentReserveDetail.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _stAdjustmentReserveDetail.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof StAdjustmentReserveDetailWrapper)) {
            return false;
        }

        StAdjustmentReserveDetailWrapper stAdjustmentReserveDetailWrapper = (StAdjustmentReserveDetailWrapper) obj;

        if (Validator.equals(_stAdjustmentReserveDetail,
                    stAdjustmentReserveDetailWrapper._stAdjustmentReserveDetail)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public StAdjustmentReserveDetail getWrappedStAdjustmentReserveDetail() {
        return _stAdjustmentReserveDetail;
    }

    @Override
    public StAdjustmentReserveDetail getWrappedModel() {
        return _stAdjustmentReserveDetail;
    }

    @Override
    public void resetOriginalValues() {
        _stAdjustmentReserveDetail.resetOriginalValues();
    }
}
