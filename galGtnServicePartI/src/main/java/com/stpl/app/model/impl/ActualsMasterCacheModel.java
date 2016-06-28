package com.stpl.app.model.impl;

import com.stpl.app.model.ActualsMaster;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing ActualsMaster in entity cache.
 *
 * @author
 * @see ActualsMaster
 * @generated
 */
public class ActualsMasterCacheModel implements CacheModel<ActualsMaster>,
    Externalizable {
    public String quantityInclusion;
    public double mandatedDiscountAmount;
    public String itemNo;
    public String analysisCode;
    public String recordSequence;
    public int modifiedBy;
    public String settlementMethodNo;
    public double quantity;
    public String accountId;
    public long createdDate;
    public String provisionClaimIndicator;
    public long dispensedDate;
    public String isActive;
    public String batchId;
    public long accrualActualEndDate;
    public String marketId;
    public String brandId;
    public String accountName;
    public double amount;
    public int actualsMasterSid;
    public String acctIdentifierCodeQualifier;
    public String organizationKey;
    public int createdBy;
    public String accrualProcessed;
    public String parentcomDivmktBrandProdkey;
    public long cashPaidDate;
    public double salesAmount;
    public long accrualActualStartDate;
    public String settlementNo;
    public double price;
    public long uploadDate;
    public String claimIndicator;
    public String itemId;
    public String priceAdjustmentName;
    public String contractId;
    public long modifiedDate;
    public String actualId;
    public String provisionId;
    public String sentOut;
    public boolean recordLockStatus;
    public String divisionId;
    public String itemIdentifierCodeQualifier;
    public String programStateCode;
    public String source;
    public String invoiceLineNo;
    public String accountNo;
    public String comDivMktBrandProdKey;
    public String inboundStatus;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(97);

        sb.append("{quantityInclusion=");
        sb.append(quantityInclusion);
        sb.append(", mandatedDiscountAmount=");
        sb.append(mandatedDiscountAmount);
        sb.append(", itemNo=");
        sb.append(itemNo);
        sb.append(", analysisCode=");
        sb.append(analysisCode);
        sb.append(", recordSequence=");
        sb.append(recordSequence);
        sb.append(", modifiedBy=");
        sb.append(modifiedBy);
        sb.append(", settlementMethodNo=");
        sb.append(settlementMethodNo);
        sb.append(", quantity=");
        sb.append(quantity);
        sb.append(", accountId=");
        sb.append(accountId);
        sb.append(", createdDate=");
        sb.append(createdDate);
        sb.append(", provisionClaimIndicator=");
        sb.append(provisionClaimIndicator);
        sb.append(", dispensedDate=");
        sb.append(dispensedDate);
        sb.append(", isActive=");
        sb.append(isActive);
        sb.append(", batchId=");
        sb.append(batchId);
        sb.append(", accrualActualEndDate=");
        sb.append(accrualActualEndDate);
        sb.append(", marketId=");
        sb.append(marketId);
        sb.append(", brandId=");
        sb.append(brandId);
        sb.append(", accountName=");
        sb.append(accountName);
        sb.append(", amount=");
        sb.append(amount);
        sb.append(", actualsMasterSid=");
        sb.append(actualsMasterSid);
        sb.append(", acctIdentifierCodeQualifier=");
        sb.append(acctIdentifierCodeQualifier);
        sb.append(", organizationKey=");
        sb.append(organizationKey);
        sb.append(", createdBy=");
        sb.append(createdBy);
        sb.append(", accrualProcessed=");
        sb.append(accrualProcessed);
        sb.append(", parentcomDivmktBrandProdkey=");
        sb.append(parentcomDivmktBrandProdkey);
        sb.append(", cashPaidDate=");
        sb.append(cashPaidDate);
        sb.append(", salesAmount=");
        sb.append(salesAmount);
        sb.append(", accrualActualStartDate=");
        sb.append(accrualActualStartDate);
        sb.append(", settlementNo=");
        sb.append(settlementNo);
        sb.append(", price=");
        sb.append(price);
        sb.append(", uploadDate=");
        sb.append(uploadDate);
        sb.append(", claimIndicator=");
        sb.append(claimIndicator);
        sb.append(", itemId=");
        sb.append(itemId);
        sb.append(", priceAdjustmentName=");
        sb.append(priceAdjustmentName);
        sb.append(", contractId=");
        sb.append(contractId);
        sb.append(", modifiedDate=");
        sb.append(modifiedDate);
        sb.append(", actualId=");
        sb.append(actualId);
        sb.append(", provisionId=");
        sb.append(provisionId);
        sb.append(", sentOut=");
        sb.append(sentOut);
        sb.append(", recordLockStatus=");
        sb.append(recordLockStatus);
        sb.append(", divisionId=");
        sb.append(divisionId);
        sb.append(", itemIdentifierCodeQualifier=");
        sb.append(itemIdentifierCodeQualifier);
        sb.append(", programStateCode=");
        sb.append(programStateCode);
        sb.append(", source=");
        sb.append(source);
        sb.append(", invoiceLineNo=");
        sb.append(invoiceLineNo);
        sb.append(", accountNo=");
        sb.append(accountNo);
        sb.append(", comDivMktBrandProdKey=");
        sb.append(comDivMktBrandProdKey);
        sb.append(", inboundStatus=");
        sb.append(inboundStatus);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public ActualsMaster toEntityModel() {
        ActualsMasterImpl actualsMasterImpl = new ActualsMasterImpl();

        if (quantityInclusion == null) {
            actualsMasterImpl.setQuantityInclusion(StringPool.BLANK);
        } else {
            actualsMasterImpl.setQuantityInclusion(quantityInclusion);
        }

        actualsMasterImpl.setMandatedDiscountAmount(mandatedDiscountAmount);

        if (itemNo == null) {
            actualsMasterImpl.setItemNo(StringPool.BLANK);
        } else {
            actualsMasterImpl.setItemNo(itemNo);
        }

        if (analysisCode == null) {
            actualsMasterImpl.setAnalysisCode(StringPool.BLANK);
        } else {
            actualsMasterImpl.setAnalysisCode(analysisCode);
        }

        if (recordSequence == null) {
            actualsMasterImpl.setRecordSequence(StringPool.BLANK);
        } else {
            actualsMasterImpl.setRecordSequence(recordSequence);
        }

        actualsMasterImpl.setModifiedBy(modifiedBy);

        if (settlementMethodNo == null) {
            actualsMasterImpl.setSettlementMethodNo(StringPool.BLANK);
        } else {
            actualsMasterImpl.setSettlementMethodNo(settlementMethodNo);
        }

        actualsMasterImpl.setQuantity(quantity);

        if (accountId == null) {
            actualsMasterImpl.setAccountId(StringPool.BLANK);
        } else {
            actualsMasterImpl.setAccountId(accountId);
        }

        if (createdDate == Long.MIN_VALUE) {
            actualsMasterImpl.setCreatedDate(null);
        } else {
            actualsMasterImpl.setCreatedDate(new Date(createdDate));
        }

        if (provisionClaimIndicator == null) {
            actualsMasterImpl.setProvisionClaimIndicator(StringPool.BLANK);
        } else {
            actualsMasterImpl.setProvisionClaimIndicator(provisionClaimIndicator);
        }

        if (dispensedDate == Long.MIN_VALUE) {
            actualsMasterImpl.setDispensedDate(null);
        } else {
            actualsMasterImpl.setDispensedDate(new Date(dispensedDate));
        }

        if (isActive == null) {
            actualsMasterImpl.setIsActive(StringPool.BLANK);
        } else {
            actualsMasterImpl.setIsActive(isActive);
        }

        if (batchId == null) {
            actualsMasterImpl.setBatchId(StringPool.BLANK);
        } else {
            actualsMasterImpl.setBatchId(batchId);
        }

        if (accrualActualEndDate == Long.MIN_VALUE) {
            actualsMasterImpl.setAccrualActualEndDate(null);
        } else {
            actualsMasterImpl.setAccrualActualEndDate(new Date(
                    accrualActualEndDate));
        }

        if (marketId == null) {
            actualsMasterImpl.setMarketId(StringPool.BLANK);
        } else {
            actualsMasterImpl.setMarketId(marketId);
        }

        if (brandId == null) {
            actualsMasterImpl.setBrandId(StringPool.BLANK);
        } else {
            actualsMasterImpl.setBrandId(brandId);
        }

        if (accountName == null) {
            actualsMasterImpl.setAccountName(StringPool.BLANK);
        } else {
            actualsMasterImpl.setAccountName(accountName);
        }

        actualsMasterImpl.setAmount(amount);
        actualsMasterImpl.setActualsMasterSid(actualsMasterSid);

        if (acctIdentifierCodeQualifier == null) {
            actualsMasterImpl.setAcctIdentifierCodeQualifier(StringPool.BLANK);
        } else {
            actualsMasterImpl.setAcctIdentifierCodeQualifier(acctIdentifierCodeQualifier);
        }

        if (organizationKey == null) {
            actualsMasterImpl.setOrganizationKey(StringPool.BLANK);
        } else {
            actualsMasterImpl.setOrganizationKey(organizationKey);
        }

        actualsMasterImpl.setCreatedBy(createdBy);

        if (accrualProcessed == null) {
            actualsMasterImpl.setAccrualProcessed(StringPool.BLANK);
        } else {
            actualsMasterImpl.setAccrualProcessed(accrualProcessed);
        }

        if (parentcomDivmktBrandProdkey == null) {
            actualsMasterImpl.setParentcomDivmktBrandProdkey(StringPool.BLANK);
        } else {
            actualsMasterImpl.setParentcomDivmktBrandProdkey(parentcomDivmktBrandProdkey);
        }

        if (cashPaidDate == Long.MIN_VALUE) {
            actualsMasterImpl.setCashPaidDate(null);
        } else {
            actualsMasterImpl.setCashPaidDate(new Date(cashPaidDate));
        }

        actualsMasterImpl.setSalesAmount(salesAmount);

        if (accrualActualStartDate == Long.MIN_VALUE) {
            actualsMasterImpl.setAccrualActualStartDate(null);
        } else {
            actualsMasterImpl.setAccrualActualStartDate(new Date(
                    accrualActualStartDate));
        }

        if (settlementNo == null) {
            actualsMasterImpl.setSettlementNo(StringPool.BLANK);
        } else {
            actualsMasterImpl.setSettlementNo(settlementNo);
        }

        actualsMasterImpl.setPrice(price);

        if (uploadDate == Long.MIN_VALUE) {
            actualsMasterImpl.setUploadDate(null);
        } else {
            actualsMasterImpl.setUploadDate(new Date(uploadDate));
        }

        if (claimIndicator == null) {
            actualsMasterImpl.setClaimIndicator(StringPool.BLANK);
        } else {
            actualsMasterImpl.setClaimIndicator(claimIndicator);
        }

        if (itemId == null) {
            actualsMasterImpl.setItemId(StringPool.BLANK);
        } else {
            actualsMasterImpl.setItemId(itemId);
        }

        if (priceAdjustmentName == null) {
            actualsMasterImpl.setPriceAdjustmentName(StringPool.BLANK);
        } else {
            actualsMasterImpl.setPriceAdjustmentName(priceAdjustmentName);
        }

        if (contractId == null) {
            actualsMasterImpl.setContractId(StringPool.BLANK);
        } else {
            actualsMasterImpl.setContractId(contractId);
        }

        if (modifiedDate == Long.MIN_VALUE) {
            actualsMasterImpl.setModifiedDate(null);
        } else {
            actualsMasterImpl.setModifiedDate(new Date(modifiedDate));
        }

        if (actualId == null) {
            actualsMasterImpl.setActualId(StringPool.BLANK);
        } else {
            actualsMasterImpl.setActualId(actualId);
        }

        if (provisionId == null) {
            actualsMasterImpl.setProvisionId(StringPool.BLANK);
        } else {
            actualsMasterImpl.setProvisionId(provisionId);
        }

        if (sentOut == null) {
            actualsMasterImpl.setSentOut(StringPool.BLANK);
        } else {
            actualsMasterImpl.setSentOut(sentOut);
        }

        actualsMasterImpl.setRecordLockStatus(recordLockStatus);

        if (divisionId == null) {
            actualsMasterImpl.setDivisionId(StringPool.BLANK);
        } else {
            actualsMasterImpl.setDivisionId(divisionId);
        }

        if (itemIdentifierCodeQualifier == null) {
            actualsMasterImpl.setItemIdentifierCodeQualifier(StringPool.BLANK);
        } else {
            actualsMasterImpl.setItemIdentifierCodeQualifier(itemIdentifierCodeQualifier);
        }

        if (programStateCode == null) {
            actualsMasterImpl.setProgramStateCode(StringPool.BLANK);
        } else {
            actualsMasterImpl.setProgramStateCode(programStateCode);
        }

        if (source == null) {
            actualsMasterImpl.setSource(StringPool.BLANK);
        } else {
            actualsMasterImpl.setSource(source);
        }

        if (invoiceLineNo == null) {
            actualsMasterImpl.setInvoiceLineNo(StringPool.BLANK);
        } else {
            actualsMasterImpl.setInvoiceLineNo(invoiceLineNo);
        }

        if (accountNo == null) {
            actualsMasterImpl.setAccountNo(StringPool.BLANK);
        } else {
            actualsMasterImpl.setAccountNo(accountNo);
        }

        if (comDivMktBrandProdKey == null) {
            actualsMasterImpl.setComDivMktBrandProdKey(StringPool.BLANK);
        } else {
            actualsMasterImpl.setComDivMktBrandProdKey(comDivMktBrandProdKey);
        }

        if (inboundStatus == null) {
            actualsMasterImpl.setInboundStatus(StringPool.BLANK);
        } else {
            actualsMasterImpl.setInboundStatus(inboundStatus);
        }

        actualsMasterImpl.resetOriginalValues();

        return actualsMasterImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        quantityInclusion = objectInput.readUTF();
        mandatedDiscountAmount = objectInput.readDouble();
        itemNo = objectInput.readUTF();
        analysisCode = objectInput.readUTF();
        recordSequence = objectInput.readUTF();
        modifiedBy = objectInput.readInt();
        settlementMethodNo = objectInput.readUTF();
        quantity = objectInput.readDouble();
        accountId = objectInput.readUTF();
        createdDate = objectInput.readLong();
        provisionClaimIndicator = objectInput.readUTF();
        dispensedDate = objectInput.readLong();
        isActive = objectInput.readUTF();
        batchId = objectInput.readUTF();
        accrualActualEndDate = objectInput.readLong();
        marketId = objectInput.readUTF();
        brandId = objectInput.readUTF();
        accountName = objectInput.readUTF();
        amount = objectInput.readDouble();
        actualsMasterSid = objectInput.readInt();
        acctIdentifierCodeQualifier = objectInput.readUTF();
        organizationKey = objectInput.readUTF();
        createdBy = objectInput.readInt();
        accrualProcessed = objectInput.readUTF();
        parentcomDivmktBrandProdkey = objectInput.readUTF();
        cashPaidDate = objectInput.readLong();
        salesAmount = objectInput.readDouble();
        accrualActualStartDate = objectInput.readLong();
        settlementNo = objectInput.readUTF();
        price = objectInput.readDouble();
        uploadDate = objectInput.readLong();
        claimIndicator = objectInput.readUTF();
        itemId = objectInput.readUTF();
        priceAdjustmentName = objectInput.readUTF();
        contractId = objectInput.readUTF();
        modifiedDate = objectInput.readLong();
        actualId = objectInput.readUTF();
        provisionId = objectInput.readUTF();
        sentOut = objectInput.readUTF();
        recordLockStatus = objectInput.readBoolean();
        divisionId = objectInput.readUTF();
        itemIdentifierCodeQualifier = objectInput.readUTF();
        programStateCode = objectInput.readUTF();
        source = objectInput.readUTF();
        invoiceLineNo = objectInput.readUTF();
        accountNo = objectInput.readUTF();
        comDivMktBrandProdKey = objectInput.readUTF();
        inboundStatus = objectInput.readUTF();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        if (quantityInclusion == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(quantityInclusion);
        }

        objectOutput.writeDouble(mandatedDiscountAmount);

        if (itemNo == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(itemNo);
        }

        if (analysisCode == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(analysisCode);
        }

        if (recordSequence == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(recordSequence);
        }

        objectOutput.writeInt(modifiedBy);

        if (settlementMethodNo == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(settlementMethodNo);
        }

        objectOutput.writeDouble(quantity);

        if (accountId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(accountId);
        }

        objectOutput.writeLong(createdDate);

        if (provisionClaimIndicator == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(provisionClaimIndicator);
        }

        objectOutput.writeLong(dispensedDate);

        if (isActive == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(isActive);
        }

        if (batchId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(batchId);
        }

        objectOutput.writeLong(accrualActualEndDate);

        if (marketId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(marketId);
        }

        if (brandId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(brandId);
        }

        if (accountName == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(accountName);
        }

        objectOutput.writeDouble(amount);
        objectOutput.writeInt(actualsMasterSid);

        if (acctIdentifierCodeQualifier == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(acctIdentifierCodeQualifier);
        }

        if (organizationKey == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(organizationKey);
        }

        objectOutput.writeInt(createdBy);

        if (accrualProcessed == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(accrualProcessed);
        }

        if (parentcomDivmktBrandProdkey == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(parentcomDivmktBrandProdkey);
        }

        objectOutput.writeLong(cashPaidDate);
        objectOutput.writeDouble(salesAmount);
        objectOutput.writeLong(accrualActualStartDate);

        if (settlementNo == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(settlementNo);
        }

        objectOutput.writeDouble(price);
        objectOutput.writeLong(uploadDate);

        if (claimIndicator == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(claimIndicator);
        }

        if (itemId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(itemId);
        }

        if (priceAdjustmentName == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(priceAdjustmentName);
        }

        if (contractId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(contractId);
        }

        objectOutput.writeLong(modifiedDate);

        if (actualId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(actualId);
        }

        if (provisionId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(provisionId);
        }

        if (sentOut == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(sentOut);
        }

        objectOutput.writeBoolean(recordLockStatus);

        if (divisionId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(divisionId);
        }

        if (itemIdentifierCodeQualifier == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(itemIdentifierCodeQualifier);
        }

        if (programStateCode == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(programStateCode);
        }

        if (source == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(source);
        }

        if (invoiceLineNo == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(invoiceLineNo);
        }

        if (accountNo == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(accountNo);
        }

        if (comDivMktBrandProdKey == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(comDivMktBrandProdKey);
        }

        if (inboundStatus == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(inboundStatus);
        }
    }
}
