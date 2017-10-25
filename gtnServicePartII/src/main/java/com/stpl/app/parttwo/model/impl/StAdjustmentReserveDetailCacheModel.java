package com.stpl.app.parttwo.model.impl;

import com.stpl.app.parttwo.model.StAdjustmentReserveDetail;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing StAdjustmentReserveDetail in entity cache.
 *
 * @author
 * @see StAdjustmentReserveDetail
 * @generated
 */
public class StAdjustmentReserveDetailCacheModel implements CacheModel<StAdjustmentReserveDetail>,
    Externalizable {
    public long adjustmentCreatedDate;
    public boolean etlCheckRecord;
    public String postingIndicator;
    public long modifiedDate;
    public String account;
    public String credit;
    public long workflowApprovedDate;
    public String source;
    public String lineDescription;
    public String ledger;
    public String udc6;
    public String udc5;
    public String udc4;
    public long workflowCreatedDate;
    public String udc3;
    public String udc2;
    public String udc1;
    public String adjustmentType;
    public String modifiedBy;
    public boolean checkRecord;
    public String glCompanyName;
    public String division;
    public String balanceType;
    public String sessionId;
    public String journalName;
    public String project;
    public String debit;
    public String accountType;
    public String journalDescription;
    public String category;
    public String createdBy;
    public long createdDate;
    public String businessUnitId;
    public long reversalPeriodDate;
    public String workflowId;
    public String chartOfAccounts;
    public String userId;
    public String batchName;
    public String database;
    public String costCenter;
    public String outboundStatus;
    public String dataAccessSet;
    public String future1;
    public String future2;
    public String workflowName;
    public String workflowCreatedBy;
    public String currency;
    public String batchId;
    public String accountCategory;
    public String reverseJournal;
    public String workflowApprovedBy;
    public String brand;
    public long accountingDate;
    public long redemptionPeriod;
    public String originalBatchId;
    public String adjustmentLevel;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(113);

        sb.append("{adjustmentCreatedDate=");
        sb.append(adjustmentCreatedDate);
        sb.append(", etlCheckRecord=");
        sb.append(etlCheckRecord);
        sb.append(", postingIndicator=");
        sb.append(postingIndicator);
        sb.append(", modifiedDate=");
        sb.append(modifiedDate);
        sb.append(", account=");
        sb.append(account);
        sb.append(", credit=");
        sb.append(credit);
        sb.append(", workflowApprovedDate=");
        sb.append(workflowApprovedDate);
        sb.append(", source=");
        sb.append(source);
        sb.append(", lineDescription=");
        sb.append(lineDescription);
        sb.append(", ledger=");
        sb.append(ledger);
        sb.append(", udc6=");
        sb.append(udc6);
        sb.append(", udc5=");
        sb.append(udc5);
        sb.append(", udc4=");
        sb.append(udc4);
        sb.append(", workflowCreatedDate=");
        sb.append(workflowCreatedDate);
        sb.append(", udc3=");
        sb.append(udc3);
        sb.append(", udc2=");
        sb.append(udc2);
        sb.append(", udc1=");
        sb.append(udc1);
        sb.append(", adjustmentType=");
        sb.append(adjustmentType);
        sb.append(", modifiedBy=");
        sb.append(modifiedBy);
        sb.append(", checkRecord=");
        sb.append(checkRecord);
        sb.append(", glCompanyName=");
        sb.append(glCompanyName);
        sb.append(", division=");
        sb.append(division);
        sb.append(", balanceType=");
        sb.append(balanceType);
        sb.append(", sessionId=");
        sb.append(sessionId);
        sb.append(", journalName=");
        sb.append(journalName);
        sb.append(", project=");
        sb.append(project);
        sb.append(", debit=");
        sb.append(debit);
        sb.append(", accountType=");
        sb.append(accountType);
        sb.append(", journalDescription=");
        sb.append(journalDescription);
        sb.append(", category=");
        sb.append(category);
        sb.append(", createdBy=");
        sb.append(createdBy);
        sb.append(", createdDate=");
        sb.append(createdDate);
        sb.append(", businessUnitId=");
        sb.append(businessUnitId);
        sb.append(", reversalPeriodDate=");
        sb.append(reversalPeriodDate);
        sb.append(", workflowId=");
        sb.append(workflowId);
        sb.append(", chartOfAccounts=");
        sb.append(chartOfAccounts);
        sb.append(", userId=");
        sb.append(userId);
        sb.append(", batchName=");
        sb.append(batchName);
        sb.append(", database=");
        sb.append(database);
        sb.append(", costCenter=");
        sb.append(costCenter);
        sb.append(", outboundStatus=");
        sb.append(outboundStatus);
        sb.append(", dataAccessSet=");
        sb.append(dataAccessSet);
        sb.append(", future1=");
        sb.append(future1);
        sb.append(", future2=");
        sb.append(future2);
        sb.append(", workflowName=");
        sb.append(workflowName);
        sb.append(", workflowCreatedBy=");
        sb.append(workflowCreatedBy);
        sb.append(", currency=");
        sb.append(currency);
        sb.append(", batchId=");
        sb.append(batchId);
        sb.append(", accountCategory=");
        sb.append(accountCategory);
        sb.append(", reverseJournal=");
        sb.append(reverseJournal);
        sb.append(", workflowApprovedBy=");
        sb.append(workflowApprovedBy);
        sb.append(", brand=");
        sb.append(brand);
        sb.append(", accountingDate=");
        sb.append(accountingDate);
        sb.append(", redemptionPeriod=");
        sb.append(redemptionPeriod);
        sb.append(", originalBatchId=");
        sb.append(originalBatchId);
        sb.append(", adjustmentLevel=");
        sb.append(adjustmentLevel);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public StAdjustmentReserveDetail toEntityModel() {
        StAdjustmentReserveDetailImpl stAdjustmentReserveDetailImpl = new StAdjustmentReserveDetailImpl();

        if (adjustmentCreatedDate == Long.MIN_VALUE) {
            stAdjustmentReserveDetailImpl.setAdjustmentCreatedDate(null);
        } else {
            stAdjustmentReserveDetailImpl.setAdjustmentCreatedDate(new Date(
                    adjustmentCreatedDate));
        }

        stAdjustmentReserveDetailImpl.setEtlCheckRecord(etlCheckRecord);

        if (postingIndicator == null) {
            stAdjustmentReserveDetailImpl.setPostingIndicator(StringPool.BLANK);
        } else {
            stAdjustmentReserveDetailImpl.setPostingIndicator(postingIndicator);
        }

        if (modifiedDate == Long.MIN_VALUE) {
            stAdjustmentReserveDetailImpl.setModifiedDate(null);
        } else {
            stAdjustmentReserveDetailImpl.setModifiedDate(new Date(modifiedDate));
        }

        if (account == null) {
            stAdjustmentReserveDetailImpl.setAccount(StringPool.BLANK);
        } else {
            stAdjustmentReserveDetailImpl.setAccount(account);
        }

        if (credit == null) {
            stAdjustmentReserveDetailImpl.setCredit(StringPool.BLANK);
        } else {
            stAdjustmentReserveDetailImpl.setCredit(credit);
        }

        if (workflowApprovedDate == Long.MIN_VALUE) {
            stAdjustmentReserveDetailImpl.setWorkflowApprovedDate(null);
        } else {
            stAdjustmentReserveDetailImpl.setWorkflowApprovedDate(new Date(
                    workflowApprovedDate));
        }

        if (source == null) {
            stAdjustmentReserveDetailImpl.setSource(StringPool.BLANK);
        } else {
            stAdjustmentReserveDetailImpl.setSource(source);
        }

        if (lineDescription == null) {
            stAdjustmentReserveDetailImpl.setLineDescription(StringPool.BLANK);
        } else {
            stAdjustmentReserveDetailImpl.setLineDescription(lineDescription);
        }

        if (ledger == null) {
            stAdjustmentReserveDetailImpl.setLedger(StringPool.BLANK);
        } else {
            stAdjustmentReserveDetailImpl.setLedger(ledger);
        }

        if (udc6 == null) {
            stAdjustmentReserveDetailImpl.setUdc6(StringPool.BLANK);
        } else {
            stAdjustmentReserveDetailImpl.setUdc6(udc6);
        }

        if (udc5 == null) {
            stAdjustmentReserveDetailImpl.setUdc5(StringPool.BLANK);
        } else {
            stAdjustmentReserveDetailImpl.setUdc5(udc5);
        }

        if (udc4 == null) {
            stAdjustmentReserveDetailImpl.setUdc4(StringPool.BLANK);
        } else {
            stAdjustmentReserveDetailImpl.setUdc4(udc4);
        }

        if (workflowCreatedDate == Long.MIN_VALUE) {
            stAdjustmentReserveDetailImpl.setWorkflowCreatedDate(null);
        } else {
            stAdjustmentReserveDetailImpl.setWorkflowCreatedDate(new Date(
                    workflowCreatedDate));
        }

        if (udc3 == null) {
            stAdjustmentReserveDetailImpl.setUdc3(StringPool.BLANK);
        } else {
            stAdjustmentReserveDetailImpl.setUdc3(udc3);
        }

        if (udc2 == null) {
            stAdjustmentReserveDetailImpl.setUdc2(StringPool.BLANK);
        } else {
            stAdjustmentReserveDetailImpl.setUdc2(udc2);
        }

        if (udc1 == null) {
            stAdjustmentReserveDetailImpl.setUdc1(StringPool.BLANK);
        } else {
            stAdjustmentReserveDetailImpl.setUdc1(udc1);
        }

        if (adjustmentType == null) {
            stAdjustmentReserveDetailImpl.setAdjustmentType(StringPool.BLANK);
        } else {
            stAdjustmentReserveDetailImpl.setAdjustmentType(adjustmentType);
        }

        if (modifiedBy == null) {
            stAdjustmentReserveDetailImpl.setModifiedBy(StringPool.BLANK);
        } else {
            stAdjustmentReserveDetailImpl.setModifiedBy(modifiedBy);
        }

        stAdjustmentReserveDetailImpl.setCheckRecord(checkRecord);

        if (glCompanyName == null) {
            stAdjustmentReserveDetailImpl.setGlCompanyName(StringPool.BLANK);
        } else {
            stAdjustmentReserveDetailImpl.setGlCompanyName(glCompanyName);
        }

        if (division == null) {
            stAdjustmentReserveDetailImpl.setDivision(StringPool.BLANK);
        } else {
            stAdjustmentReserveDetailImpl.setDivision(division);
        }

        if (balanceType == null) {
            stAdjustmentReserveDetailImpl.setBalanceType(StringPool.BLANK);
        } else {
            stAdjustmentReserveDetailImpl.setBalanceType(balanceType);
        }

        if (sessionId == null) {
            stAdjustmentReserveDetailImpl.setSessionId(StringPool.BLANK);
        } else {
            stAdjustmentReserveDetailImpl.setSessionId(sessionId);
        }

        if (journalName == null) {
            stAdjustmentReserveDetailImpl.setJournalName(StringPool.BLANK);
        } else {
            stAdjustmentReserveDetailImpl.setJournalName(journalName);
        }

        if (project == null) {
            stAdjustmentReserveDetailImpl.setProject(StringPool.BLANK);
        } else {
            stAdjustmentReserveDetailImpl.setProject(project);
        }

        if (debit == null) {
            stAdjustmentReserveDetailImpl.setDebit(StringPool.BLANK);
        } else {
            stAdjustmentReserveDetailImpl.setDebit(debit);
        }

        if (accountType == null) {
            stAdjustmentReserveDetailImpl.setAccountType(StringPool.BLANK);
        } else {
            stAdjustmentReserveDetailImpl.setAccountType(accountType);
        }

        if (journalDescription == null) {
            stAdjustmentReserveDetailImpl.setJournalDescription(StringPool.BLANK);
        } else {
            stAdjustmentReserveDetailImpl.setJournalDescription(journalDescription);
        }

        if (category == null) {
            stAdjustmentReserveDetailImpl.setCategory(StringPool.BLANK);
        } else {
            stAdjustmentReserveDetailImpl.setCategory(category);
        }

        if (createdBy == null) {
            stAdjustmentReserveDetailImpl.setCreatedBy(StringPool.BLANK);
        } else {
            stAdjustmentReserveDetailImpl.setCreatedBy(createdBy);
        }

        if (createdDate == Long.MIN_VALUE) {
            stAdjustmentReserveDetailImpl.setCreatedDate(null);
        } else {
            stAdjustmentReserveDetailImpl.setCreatedDate(new Date(createdDate));
        }

        if (businessUnitId == null) {
            stAdjustmentReserveDetailImpl.setBusinessUnitId(StringPool.BLANK);
        } else {
            stAdjustmentReserveDetailImpl.setBusinessUnitId(businessUnitId);
        }

        if (reversalPeriodDate == Long.MIN_VALUE) {
            stAdjustmentReserveDetailImpl.setReversalPeriodDate(null);
        } else {
            stAdjustmentReserveDetailImpl.setReversalPeriodDate(new Date(
                    reversalPeriodDate));
        }

        if (workflowId == null) {
            stAdjustmentReserveDetailImpl.setWorkflowId(StringPool.BLANK);
        } else {
            stAdjustmentReserveDetailImpl.setWorkflowId(workflowId);
        }

        if (chartOfAccounts == null) {
            stAdjustmentReserveDetailImpl.setChartOfAccounts(StringPool.BLANK);
        } else {
            stAdjustmentReserveDetailImpl.setChartOfAccounts(chartOfAccounts);
        }

        if (userId == null) {
            stAdjustmentReserveDetailImpl.setUserId(StringPool.BLANK);
        } else {
            stAdjustmentReserveDetailImpl.setUserId(userId);
        }

        if (batchName == null) {
            stAdjustmentReserveDetailImpl.setBatchName(StringPool.BLANK);
        } else {
            stAdjustmentReserveDetailImpl.setBatchName(batchName);
        }

        if (database == null) {
            stAdjustmentReserveDetailImpl.setDatabase(StringPool.BLANK);
        } else {
            stAdjustmentReserveDetailImpl.setDatabase(database);
        }

        if (costCenter == null) {
            stAdjustmentReserveDetailImpl.setCostCenter(StringPool.BLANK);
        } else {
            stAdjustmentReserveDetailImpl.setCostCenter(costCenter);
        }

        if (outboundStatus == null) {
            stAdjustmentReserveDetailImpl.setOutboundStatus(StringPool.BLANK);
        } else {
            stAdjustmentReserveDetailImpl.setOutboundStatus(outboundStatus);
        }

        if (dataAccessSet == null) {
            stAdjustmentReserveDetailImpl.setDataAccessSet(StringPool.BLANK);
        } else {
            stAdjustmentReserveDetailImpl.setDataAccessSet(dataAccessSet);
        }

        if (future1 == null) {
            stAdjustmentReserveDetailImpl.setFuture1(StringPool.BLANK);
        } else {
            stAdjustmentReserveDetailImpl.setFuture1(future1);
        }

        if (future2 == null) {
            stAdjustmentReserveDetailImpl.setFuture2(StringPool.BLANK);
        } else {
            stAdjustmentReserveDetailImpl.setFuture2(future2);
        }

        if (workflowName == null) {
            stAdjustmentReserveDetailImpl.setWorkflowName(StringPool.BLANK);
        } else {
            stAdjustmentReserveDetailImpl.setWorkflowName(workflowName);
        }

        if (workflowCreatedBy == null) {
            stAdjustmentReserveDetailImpl.setWorkflowCreatedBy(StringPool.BLANK);
        } else {
            stAdjustmentReserveDetailImpl.setWorkflowCreatedBy(workflowCreatedBy);
        }

        if (currency == null) {
            stAdjustmentReserveDetailImpl.setCurrency(StringPool.BLANK);
        } else {
            stAdjustmentReserveDetailImpl.setCurrency(currency);
        }

        if (batchId == null) {
            stAdjustmentReserveDetailImpl.setBatchId(StringPool.BLANK);
        } else {
            stAdjustmentReserveDetailImpl.setBatchId(batchId);
        }

        if (accountCategory == null) {
            stAdjustmentReserveDetailImpl.setAccountCategory(StringPool.BLANK);
        } else {
            stAdjustmentReserveDetailImpl.setAccountCategory(accountCategory);
        }

        if (reverseJournal == null) {
            stAdjustmentReserveDetailImpl.setReverseJournal(StringPool.BLANK);
        } else {
            stAdjustmentReserveDetailImpl.setReverseJournal(reverseJournal);
        }

        if (workflowApprovedBy == null) {
            stAdjustmentReserveDetailImpl.setWorkflowApprovedBy(StringPool.BLANK);
        } else {
            stAdjustmentReserveDetailImpl.setWorkflowApprovedBy(workflowApprovedBy);
        }

        if (brand == null) {
            stAdjustmentReserveDetailImpl.setBrand(StringPool.BLANK);
        } else {
            stAdjustmentReserveDetailImpl.setBrand(brand);
        }

        if (accountingDate == Long.MIN_VALUE) {
            stAdjustmentReserveDetailImpl.setAccountingDate(null);
        } else {
            stAdjustmentReserveDetailImpl.setAccountingDate(new Date(
                    accountingDate));
        }

        if (redemptionPeriod == Long.MIN_VALUE) {
            stAdjustmentReserveDetailImpl.setRedemptionPeriod(null);
        } else {
            stAdjustmentReserveDetailImpl.setRedemptionPeriod(new Date(
                    redemptionPeriod));
        }

        if (originalBatchId == null) {
            stAdjustmentReserveDetailImpl.setOriginalBatchId(StringPool.BLANK);
        } else {
            stAdjustmentReserveDetailImpl.setOriginalBatchId(originalBatchId);
        }

        if (adjustmentLevel == null) {
            stAdjustmentReserveDetailImpl.setAdjustmentLevel(StringPool.BLANK);
        } else {
            stAdjustmentReserveDetailImpl.setAdjustmentLevel(adjustmentLevel);
        }

        stAdjustmentReserveDetailImpl.resetOriginalValues();

        return stAdjustmentReserveDetailImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        adjustmentCreatedDate = objectInput.readLong();
        etlCheckRecord = objectInput.readBoolean();
        postingIndicator = objectInput.readUTF();
        modifiedDate = objectInput.readLong();
        account = objectInput.readUTF();
        credit = objectInput.readUTF();
        workflowApprovedDate = objectInput.readLong();
        source = objectInput.readUTF();
        lineDescription = objectInput.readUTF();
        ledger = objectInput.readUTF();
        udc6 = objectInput.readUTF();
        udc5 = objectInput.readUTF();
        udc4 = objectInput.readUTF();
        workflowCreatedDate = objectInput.readLong();
        udc3 = objectInput.readUTF();
        udc2 = objectInput.readUTF();
        udc1 = objectInput.readUTF();
        adjustmentType = objectInput.readUTF();
        modifiedBy = objectInput.readUTF();
        checkRecord = objectInput.readBoolean();
        glCompanyName = objectInput.readUTF();
        division = objectInput.readUTF();
        balanceType = objectInput.readUTF();
        sessionId = objectInput.readUTF();
        journalName = objectInput.readUTF();
        project = objectInput.readUTF();
        debit = objectInput.readUTF();
        accountType = objectInput.readUTF();
        journalDescription = objectInput.readUTF();
        category = objectInput.readUTF();
        createdBy = objectInput.readUTF();
        createdDate = objectInput.readLong();
        businessUnitId = objectInput.readUTF();
        reversalPeriodDate = objectInput.readLong();
        workflowId = objectInput.readUTF();
        chartOfAccounts = objectInput.readUTF();
        userId = objectInput.readUTF();
        batchName = objectInput.readUTF();
        database = objectInput.readUTF();
        costCenter = objectInput.readUTF();
        outboundStatus = objectInput.readUTF();
        dataAccessSet = objectInput.readUTF();
        future1 = objectInput.readUTF();
        future2 = objectInput.readUTF();
        workflowName = objectInput.readUTF();
        workflowCreatedBy = objectInput.readUTF();
        currency = objectInput.readUTF();
        batchId = objectInput.readUTF();
        accountCategory = objectInput.readUTF();
        reverseJournal = objectInput.readUTF();
        workflowApprovedBy = objectInput.readUTF();
        brand = objectInput.readUTF();
        accountingDate = objectInput.readLong();
        redemptionPeriod = objectInput.readLong();
        originalBatchId = objectInput.readUTF();
        adjustmentLevel = objectInput.readUTF();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeLong(adjustmentCreatedDate);
        objectOutput.writeBoolean(etlCheckRecord);

        if (postingIndicator == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(postingIndicator);
        }

        objectOutput.writeLong(modifiedDate);

        if (account == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(account);
        }

        if (credit == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(credit);
        }

        objectOutput.writeLong(workflowApprovedDate);

        if (source == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(source);
        }

        if (lineDescription == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(lineDescription);
        }

        if (ledger == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(ledger);
        }

        if (udc6 == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(udc6);
        }

        if (udc5 == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(udc5);
        }

        if (udc4 == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(udc4);
        }

        objectOutput.writeLong(workflowCreatedDate);

        if (udc3 == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(udc3);
        }

        if (udc2 == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(udc2);
        }

        if (udc1 == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(udc1);
        }

        if (adjustmentType == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(adjustmentType);
        }

        if (modifiedBy == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(modifiedBy);
        }

        objectOutput.writeBoolean(checkRecord);

        if (glCompanyName == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(glCompanyName);
        }

        if (division == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(division);
        }

        if (balanceType == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(balanceType);
        }

        if (sessionId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(sessionId);
        }

        if (journalName == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(journalName);
        }

        if (project == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(project);
        }

        if (debit == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(debit);
        }

        if (accountType == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(accountType);
        }

        if (journalDescription == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(journalDescription);
        }

        if (category == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(category);
        }

        if (createdBy == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(createdBy);
        }

        objectOutput.writeLong(createdDate);

        if (businessUnitId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(businessUnitId);
        }

        objectOutput.writeLong(reversalPeriodDate);

        if (workflowId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(workflowId);
        }

        if (chartOfAccounts == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(chartOfAccounts);
        }

        if (userId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(userId);
        }

        if (batchName == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(batchName);
        }

        if (database == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(database);
        }

        if (costCenter == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(costCenter);
        }

        if (outboundStatus == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(outboundStatus);
        }

        if (dataAccessSet == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(dataAccessSet);
        }

        if (future1 == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(future1);
        }

        if (future2 == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(future2);
        }

        if (workflowName == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(workflowName);
        }

        if (workflowCreatedBy == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(workflowCreatedBy);
        }

        if (currency == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(currency);
        }

        if (batchId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(batchId);
        }

        if (accountCategory == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(accountCategory);
        }

        if (reverseJournal == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(reverseJournal);
        }

        if (workflowApprovedBy == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(workflowApprovedBy);
        }

        if (brand == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(brand);
        }

        objectOutput.writeLong(accountingDate);
        objectOutput.writeLong(redemptionPeriod);

        if (originalBatchId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(originalBatchId);
        }

        if (adjustmentLevel == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(adjustmentLevel);
        }
    }
}
