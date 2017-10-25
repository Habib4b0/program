package com.stpl.app.model.impl;

import com.stpl.app.model.AccrualMaster;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing AccrualMaster in entity cache.
 *
 * @author
 * @see AccrualMaster
 * @generated
 */
public class AccrualMasterCacheModel implements CacheModel<AccrualMaster>,
    Externalizable {
    public String accountId;
    public long recordCreatedDate;
    public String postingIndicator;
    public String brandName;
    public long accrualPeriodEndDate;
    public long modifiedDate;
    public String salesMasterId;
    public String source;
    public int contractMasterSid;
    public String documentType;
    public String inboundStatus;
    public int modifiedBy;
    public String acctIdentCodeQualifier;
    public String compIdentCodeQualifier;
    public int companyMasterSid;
    public String contractName;
    public String accountNo;
    public String accountName;
    public String provisionId;
    public double amount;
    public String subLedger;
    public boolean recordLockStatus;
    public String companyNo;
    public String itemName;
    public int rsModelSid;
    public int accrualMasterSid;
    public int itemMasterSid;
    public String itemId;
    public int brandMasterSid;
    public String itemIdentCodeQualifier;
    public String glCompanyMasterSid;
    public int createdBy;
    public long createdDate;
    public long accrualPeriodStartDate;
    public String subLedgerType;
    public String programNo;
    public String glCompanyName;
    public String categoryId;
    public String itemNo;
    public String contractId;
    public String accrualId;
    public String companyId;
    public String accrualType;
    public String brandId;
    public long postingDate;
    public long glDate;
    public double glAmount;
    public String companyCostCenter;
    public String glAccount;
    public String batchId;
    public String programType;
    public String contractNo;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(105);

        sb.append("{accountId=");
        sb.append(accountId);
        sb.append(", recordCreatedDate=");
        sb.append(recordCreatedDate);
        sb.append(", postingIndicator=");
        sb.append(postingIndicator);
        sb.append(", brandName=");
        sb.append(brandName);
        sb.append(", accrualPeriodEndDate=");
        sb.append(accrualPeriodEndDate);
        sb.append(", modifiedDate=");
        sb.append(modifiedDate);
        sb.append(", salesMasterId=");
        sb.append(salesMasterId);
        sb.append(", source=");
        sb.append(source);
        sb.append(", contractMasterSid=");
        sb.append(contractMasterSid);
        sb.append(", documentType=");
        sb.append(documentType);
        sb.append(", inboundStatus=");
        sb.append(inboundStatus);
        sb.append(", modifiedBy=");
        sb.append(modifiedBy);
        sb.append(", acctIdentCodeQualifier=");
        sb.append(acctIdentCodeQualifier);
        sb.append(", compIdentCodeQualifier=");
        sb.append(compIdentCodeQualifier);
        sb.append(", companyMasterSid=");
        sb.append(companyMasterSid);
        sb.append(", contractName=");
        sb.append(contractName);
        sb.append(", accountNo=");
        sb.append(accountNo);
        sb.append(", accountName=");
        sb.append(accountName);
        sb.append(", provisionId=");
        sb.append(provisionId);
        sb.append(", amount=");
        sb.append(amount);
        sb.append(", subLedger=");
        sb.append(subLedger);
        sb.append(", recordLockStatus=");
        sb.append(recordLockStatus);
        sb.append(", companyNo=");
        sb.append(companyNo);
        sb.append(", itemName=");
        sb.append(itemName);
        sb.append(", rsModelSid=");
        sb.append(rsModelSid);
        sb.append(", accrualMasterSid=");
        sb.append(accrualMasterSid);
        sb.append(", itemMasterSid=");
        sb.append(itemMasterSid);
        sb.append(", itemId=");
        sb.append(itemId);
        sb.append(", brandMasterSid=");
        sb.append(brandMasterSid);
        sb.append(", itemIdentCodeQualifier=");
        sb.append(itemIdentCodeQualifier);
        sb.append(", glCompanyMasterSid=");
        sb.append(glCompanyMasterSid);
        sb.append(", createdBy=");
        sb.append(createdBy);
        sb.append(", createdDate=");
        sb.append(createdDate);
        sb.append(", accrualPeriodStartDate=");
        sb.append(accrualPeriodStartDate);
        sb.append(", subLedgerType=");
        sb.append(subLedgerType);
        sb.append(", programNo=");
        sb.append(programNo);
        sb.append(", glCompanyName=");
        sb.append(glCompanyName);
        sb.append(", categoryId=");
        sb.append(categoryId);
        sb.append(", itemNo=");
        sb.append(itemNo);
        sb.append(", contractId=");
        sb.append(contractId);
        sb.append(", accrualId=");
        sb.append(accrualId);
        sb.append(", companyId=");
        sb.append(companyId);
        sb.append(", accrualType=");
        sb.append(accrualType);
        sb.append(", brandId=");
        sb.append(brandId);
        sb.append(", postingDate=");
        sb.append(postingDate);
        sb.append(", glDate=");
        sb.append(glDate);
        sb.append(", glAmount=");
        sb.append(glAmount);
        sb.append(", companyCostCenter=");
        sb.append(companyCostCenter);
        sb.append(", glAccount=");
        sb.append(glAccount);
        sb.append(", batchId=");
        sb.append(batchId);
        sb.append(", programType=");
        sb.append(programType);
        sb.append(", contractNo=");
        sb.append(contractNo);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public AccrualMaster toEntityModel() {
        AccrualMasterImpl accrualMasterImpl = new AccrualMasterImpl();

        if (accountId == null) {
            accrualMasterImpl.setAccountId(StringPool.BLANK);
        } else {
            accrualMasterImpl.setAccountId(accountId);
        }

        if (recordCreatedDate == Long.MIN_VALUE) {
            accrualMasterImpl.setRecordCreatedDate(null);
        } else {
            accrualMasterImpl.setRecordCreatedDate(new Date(recordCreatedDate));
        }

        if (postingIndicator == null) {
            accrualMasterImpl.setPostingIndicator(StringPool.BLANK);
        } else {
            accrualMasterImpl.setPostingIndicator(postingIndicator);
        }

        if (brandName == null) {
            accrualMasterImpl.setBrandName(StringPool.BLANK);
        } else {
            accrualMasterImpl.setBrandName(brandName);
        }

        if (accrualPeriodEndDate == Long.MIN_VALUE) {
            accrualMasterImpl.setAccrualPeriodEndDate(null);
        } else {
            accrualMasterImpl.setAccrualPeriodEndDate(new Date(
                    accrualPeriodEndDate));
        }

        if (modifiedDate == Long.MIN_VALUE) {
            accrualMasterImpl.setModifiedDate(null);
        } else {
            accrualMasterImpl.setModifiedDate(new Date(modifiedDate));
        }

        if (salesMasterId == null) {
            accrualMasterImpl.setSalesMasterId(StringPool.BLANK);
        } else {
            accrualMasterImpl.setSalesMasterId(salesMasterId);
        }

        if (source == null) {
            accrualMasterImpl.setSource(StringPool.BLANK);
        } else {
            accrualMasterImpl.setSource(source);
        }

        accrualMasterImpl.setContractMasterSid(contractMasterSid);

        if (documentType == null) {
            accrualMasterImpl.setDocumentType(StringPool.BLANK);
        } else {
            accrualMasterImpl.setDocumentType(documentType);
        }

        if (inboundStatus == null) {
            accrualMasterImpl.setInboundStatus(StringPool.BLANK);
        } else {
            accrualMasterImpl.setInboundStatus(inboundStatus);
        }

        accrualMasterImpl.setModifiedBy(modifiedBy);

        if (acctIdentCodeQualifier == null) {
            accrualMasterImpl.setAcctIdentCodeQualifier(StringPool.BLANK);
        } else {
            accrualMasterImpl.setAcctIdentCodeQualifier(acctIdentCodeQualifier);
        }

        if (compIdentCodeQualifier == null) {
            accrualMasterImpl.setCompIdentCodeQualifier(StringPool.BLANK);
        } else {
            accrualMasterImpl.setCompIdentCodeQualifier(compIdentCodeQualifier);
        }

        accrualMasterImpl.setCompanyMasterSid(companyMasterSid);

        if (contractName == null) {
            accrualMasterImpl.setContractName(StringPool.BLANK);
        } else {
            accrualMasterImpl.setContractName(contractName);
        }

        if (accountNo == null) {
            accrualMasterImpl.setAccountNo(StringPool.BLANK);
        } else {
            accrualMasterImpl.setAccountNo(accountNo);
        }

        if (accountName == null) {
            accrualMasterImpl.setAccountName(StringPool.BLANK);
        } else {
            accrualMasterImpl.setAccountName(accountName);
        }

        if (provisionId == null) {
            accrualMasterImpl.setProvisionId(StringPool.BLANK);
        } else {
            accrualMasterImpl.setProvisionId(provisionId);
        }

        accrualMasterImpl.setAmount(amount);

        if (subLedger == null) {
            accrualMasterImpl.setSubLedger(StringPool.BLANK);
        } else {
            accrualMasterImpl.setSubLedger(subLedger);
        }

        accrualMasterImpl.setRecordLockStatus(recordLockStatus);

        if (companyNo == null) {
            accrualMasterImpl.setCompanyNo(StringPool.BLANK);
        } else {
            accrualMasterImpl.setCompanyNo(companyNo);
        }

        if (itemName == null) {
            accrualMasterImpl.setItemName(StringPool.BLANK);
        } else {
            accrualMasterImpl.setItemName(itemName);
        }

        accrualMasterImpl.setRsModelSid(rsModelSid);
        accrualMasterImpl.setAccrualMasterSid(accrualMasterSid);
        accrualMasterImpl.setItemMasterSid(itemMasterSid);

        if (itemId == null) {
            accrualMasterImpl.setItemId(StringPool.BLANK);
        } else {
            accrualMasterImpl.setItemId(itemId);
        }

        accrualMasterImpl.setBrandMasterSid(brandMasterSid);

        if (itemIdentCodeQualifier == null) {
            accrualMasterImpl.setItemIdentCodeQualifier(StringPool.BLANK);
        } else {
            accrualMasterImpl.setItemIdentCodeQualifier(itemIdentCodeQualifier);
        }

        if (glCompanyMasterSid == null) {
            accrualMasterImpl.setGlCompanyMasterSid(StringPool.BLANK);
        } else {
            accrualMasterImpl.setGlCompanyMasterSid(glCompanyMasterSid);
        }

        accrualMasterImpl.setCreatedBy(createdBy);

        if (createdDate == Long.MIN_VALUE) {
            accrualMasterImpl.setCreatedDate(null);
        } else {
            accrualMasterImpl.setCreatedDate(new Date(createdDate));
        }

        if (accrualPeriodStartDate == Long.MIN_VALUE) {
            accrualMasterImpl.setAccrualPeriodStartDate(null);
        } else {
            accrualMasterImpl.setAccrualPeriodStartDate(new Date(
                    accrualPeriodStartDate));
        }

        if (subLedgerType == null) {
            accrualMasterImpl.setSubLedgerType(StringPool.BLANK);
        } else {
            accrualMasterImpl.setSubLedgerType(subLedgerType);
        }

        if (programNo == null) {
            accrualMasterImpl.setProgramNo(StringPool.BLANK);
        } else {
            accrualMasterImpl.setProgramNo(programNo);
        }

        if (glCompanyName == null) {
            accrualMasterImpl.setGlCompanyName(StringPool.BLANK);
        } else {
            accrualMasterImpl.setGlCompanyName(glCompanyName);
        }

        if (categoryId == null) {
            accrualMasterImpl.setCategoryId(StringPool.BLANK);
        } else {
            accrualMasterImpl.setCategoryId(categoryId);
        }

        if (itemNo == null) {
            accrualMasterImpl.setItemNo(StringPool.BLANK);
        } else {
            accrualMasterImpl.setItemNo(itemNo);
        }

        if (contractId == null) {
            accrualMasterImpl.setContractId(StringPool.BLANK);
        } else {
            accrualMasterImpl.setContractId(contractId);
        }

        if (accrualId == null) {
            accrualMasterImpl.setAccrualId(StringPool.BLANK);
        } else {
            accrualMasterImpl.setAccrualId(accrualId);
        }

        if (companyId == null) {
            accrualMasterImpl.setCompanyId(StringPool.BLANK);
        } else {
            accrualMasterImpl.setCompanyId(companyId);
        }

        if (accrualType == null) {
            accrualMasterImpl.setAccrualType(StringPool.BLANK);
        } else {
            accrualMasterImpl.setAccrualType(accrualType);
        }

        if (brandId == null) {
            accrualMasterImpl.setBrandId(StringPool.BLANK);
        } else {
            accrualMasterImpl.setBrandId(brandId);
        }

        if (postingDate == Long.MIN_VALUE) {
            accrualMasterImpl.setPostingDate(null);
        } else {
            accrualMasterImpl.setPostingDate(new Date(postingDate));
        }

        if (glDate == Long.MIN_VALUE) {
            accrualMasterImpl.setGlDate(null);
        } else {
            accrualMasterImpl.setGlDate(new Date(glDate));
        }

        accrualMasterImpl.setGlAmount(glAmount);

        if (companyCostCenter == null) {
            accrualMasterImpl.setCompanyCostCenter(StringPool.BLANK);
        } else {
            accrualMasterImpl.setCompanyCostCenter(companyCostCenter);
        }

        if (glAccount == null) {
            accrualMasterImpl.setGlAccount(StringPool.BLANK);
        } else {
            accrualMasterImpl.setGlAccount(glAccount);
        }

        if (batchId == null) {
            accrualMasterImpl.setBatchId(StringPool.BLANK);
        } else {
            accrualMasterImpl.setBatchId(batchId);
        }

        if (programType == null) {
            accrualMasterImpl.setProgramType(StringPool.BLANK);
        } else {
            accrualMasterImpl.setProgramType(programType);
        }

        if (contractNo == null) {
            accrualMasterImpl.setContractNo(StringPool.BLANK);
        } else {
            accrualMasterImpl.setContractNo(contractNo);
        }

        accrualMasterImpl.resetOriginalValues();

        return accrualMasterImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        accountId = objectInput.readUTF();
        recordCreatedDate = objectInput.readLong();
        postingIndicator = objectInput.readUTF();
        brandName = objectInput.readUTF();
        accrualPeriodEndDate = objectInput.readLong();
        modifiedDate = objectInput.readLong();
        salesMasterId = objectInput.readUTF();
        source = objectInput.readUTF();
        contractMasterSid = objectInput.readInt();
        documentType = objectInput.readUTF();
        inboundStatus = objectInput.readUTF();
        modifiedBy = objectInput.readInt();
        acctIdentCodeQualifier = objectInput.readUTF();
        compIdentCodeQualifier = objectInput.readUTF();
        companyMasterSid = objectInput.readInt();
        contractName = objectInput.readUTF();
        accountNo = objectInput.readUTF();
        accountName = objectInput.readUTF();
        provisionId = objectInput.readUTF();
        amount = objectInput.readDouble();
        subLedger = objectInput.readUTF();
        recordLockStatus = objectInput.readBoolean();
        companyNo = objectInput.readUTF();
        itemName = objectInput.readUTF();
        rsModelSid = objectInput.readInt();
        accrualMasterSid = objectInput.readInt();
        itemMasterSid = objectInput.readInt();
        itemId = objectInput.readUTF();
        brandMasterSid = objectInput.readInt();
        itemIdentCodeQualifier = objectInput.readUTF();
        glCompanyMasterSid = objectInput.readUTF();
        createdBy = objectInput.readInt();
        createdDate = objectInput.readLong();
        accrualPeriodStartDate = objectInput.readLong();
        subLedgerType = objectInput.readUTF();
        programNo = objectInput.readUTF();
        glCompanyName = objectInput.readUTF();
        categoryId = objectInput.readUTF();
        itemNo = objectInput.readUTF();
        contractId = objectInput.readUTF();
        accrualId = objectInput.readUTF();
        companyId = objectInput.readUTF();
        accrualType = objectInput.readUTF();
        brandId = objectInput.readUTF();
        postingDate = objectInput.readLong();
        glDate = objectInput.readLong();
        glAmount = objectInput.readDouble();
        companyCostCenter = objectInput.readUTF();
        glAccount = objectInput.readUTF();
        batchId = objectInput.readUTF();
        programType = objectInput.readUTF();
        contractNo = objectInput.readUTF();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        if (accountId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(accountId);
        }

        objectOutput.writeLong(recordCreatedDate);

        if (postingIndicator == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(postingIndicator);
        }

        if (brandName == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(brandName);
        }

        objectOutput.writeLong(accrualPeriodEndDate);
        objectOutput.writeLong(modifiedDate);

        if (salesMasterId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(salesMasterId);
        }

        if (source == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(source);
        }

        objectOutput.writeInt(contractMasterSid);

        if (documentType == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(documentType);
        }

        if (inboundStatus == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(inboundStatus);
        }

        objectOutput.writeInt(modifiedBy);

        if (acctIdentCodeQualifier == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(acctIdentCodeQualifier);
        }

        if (compIdentCodeQualifier == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(compIdentCodeQualifier);
        }

        objectOutput.writeInt(companyMasterSid);

        if (contractName == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(contractName);
        }

        if (accountNo == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(accountNo);
        }

        if (accountName == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(accountName);
        }

        if (provisionId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(provisionId);
        }

        objectOutput.writeDouble(amount);

        if (subLedger == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(subLedger);
        }

        objectOutput.writeBoolean(recordLockStatus);

        if (companyNo == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(companyNo);
        }

        if (itemName == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(itemName);
        }

        objectOutput.writeInt(rsModelSid);
        objectOutput.writeInt(accrualMasterSid);
        objectOutput.writeInt(itemMasterSid);

        if (itemId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(itemId);
        }

        objectOutput.writeInt(brandMasterSid);

        if (itemIdentCodeQualifier == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(itemIdentCodeQualifier);
        }

        if (glCompanyMasterSid == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(glCompanyMasterSid);
        }

        objectOutput.writeInt(createdBy);
        objectOutput.writeLong(createdDate);
        objectOutput.writeLong(accrualPeriodStartDate);

        if (subLedgerType == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(subLedgerType);
        }

        if (programNo == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(programNo);
        }

        if (glCompanyName == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(glCompanyName);
        }

        if (categoryId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(categoryId);
        }

        if (itemNo == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(itemNo);
        }

        if (contractId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(contractId);
        }

        if (accrualId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(accrualId);
        }

        if (companyId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(companyId);
        }

        if (accrualType == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(accrualType);
        }

        if (brandId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(brandId);
        }

        objectOutput.writeLong(postingDate);
        objectOutput.writeLong(glDate);
        objectOutput.writeDouble(glAmount);

        if (companyCostCenter == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(companyCostCenter);
        }

        if (glAccount == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(glAccount);
        }

        if (batchId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(batchId);
        }

        if (programType == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(programType);
        }

        if (contractNo == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(contractNo);
        }
    }
}
