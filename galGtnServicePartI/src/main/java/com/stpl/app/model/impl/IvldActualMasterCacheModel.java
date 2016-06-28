package com.stpl.app.model.impl;

import com.stpl.app.model.IvldActualMaster;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing IvldActualMaster in entity cache.
 *
 * @author
 * @see IvldActualMaster
 * @generated
 */
public class IvldActualMasterCacheModel implements CacheModel<IvldActualMaster>,
    Externalizable {
    public String actualIntfid;
    public String accountId;
    public String programStateCode;
    public String settlementNo;
    public String accrualActualEndDate;
    public String actualId;
    public long modifiedDate;
    public String divisionId;
    public String organizationKey;
    public String quantityInclusion;
    public String cashPaidDate;
    public String source;
    public String addChgDelIndicator;
    public String analysisCode;
    public String accrualActualStartDate;
    public String modifiedBy;
    public String salesAmount;
    public String quantity;
    public String sentOut;
    public String accountNo;
    public String reasonForFailure;
    public String accountName;
    public String provisionId;
    public String amount;
    public String marketId;
    public String isActive;
    public String settlementMethodNo;
    public String itemIdentifierCodeQualifier;
    public String priceAdjustmentName;
    public String errorField;
    public String recordSequence;
    public String mandatedDiscountAmount;
    public String parentcomDivmktBrandProdkey;
    public String price;
    public String dispensedDate;
    public String itemId;
    public String accrualProcessed;
    public String uploadDate;
    public String createdBy;
    public long createdDate;
    public String invoiceLineNo;
    public String errorCode;
    public long intfInsertedDate;
    public String itemNo;
    public String reprocessedFlag;
    public String acctIdentifierCodeQualifier;
    public String contractId;
    public String brandId;
    public String comDivMktBrandProdKey;
    public String claimIndicator;
    public int ivldActualMasterSid;
    public String batchId;
    public String provisionClaimIndicator;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(107);

        sb.append("{actualIntfid=");
        sb.append(actualIntfid);
        sb.append(", accountId=");
        sb.append(accountId);
        sb.append(", programStateCode=");
        sb.append(programStateCode);
        sb.append(", settlementNo=");
        sb.append(settlementNo);
        sb.append(", accrualActualEndDate=");
        sb.append(accrualActualEndDate);
        sb.append(", actualId=");
        sb.append(actualId);
        sb.append(", modifiedDate=");
        sb.append(modifiedDate);
        sb.append(", divisionId=");
        sb.append(divisionId);
        sb.append(", organizationKey=");
        sb.append(organizationKey);
        sb.append(", quantityInclusion=");
        sb.append(quantityInclusion);
        sb.append(", cashPaidDate=");
        sb.append(cashPaidDate);
        sb.append(", source=");
        sb.append(source);
        sb.append(", addChgDelIndicator=");
        sb.append(addChgDelIndicator);
        sb.append(", analysisCode=");
        sb.append(analysisCode);
        sb.append(", accrualActualStartDate=");
        sb.append(accrualActualStartDate);
        sb.append(", modifiedBy=");
        sb.append(modifiedBy);
        sb.append(", salesAmount=");
        sb.append(salesAmount);
        sb.append(", quantity=");
        sb.append(quantity);
        sb.append(", sentOut=");
        sb.append(sentOut);
        sb.append(", accountNo=");
        sb.append(accountNo);
        sb.append(", reasonForFailure=");
        sb.append(reasonForFailure);
        sb.append(", accountName=");
        sb.append(accountName);
        sb.append(", provisionId=");
        sb.append(provisionId);
        sb.append(", amount=");
        sb.append(amount);
        sb.append(", marketId=");
        sb.append(marketId);
        sb.append(", isActive=");
        sb.append(isActive);
        sb.append(", settlementMethodNo=");
        sb.append(settlementMethodNo);
        sb.append(", itemIdentifierCodeQualifier=");
        sb.append(itemIdentifierCodeQualifier);
        sb.append(", priceAdjustmentName=");
        sb.append(priceAdjustmentName);
        sb.append(", errorField=");
        sb.append(errorField);
        sb.append(", recordSequence=");
        sb.append(recordSequence);
        sb.append(", mandatedDiscountAmount=");
        sb.append(mandatedDiscountAmount);
        sb.append(", parentcomDivmktBrandProdkey=");
        sb.append(parentcomDivmktBrandProdkey);
        sb.append(", price=");
        sb.append(price);
        sb.append(", dispensedDate=");
        sb.append(dispensedDate);
        sb.append(", itemId=");
        sb.append(itemId);
        sb.append(", accrualProcessed=");
        sb.append(accrualProcessed);
        sb.append(", uploadDate=");
        sb.append(uploadDate);
        sb.append(", createdBy=");
        sb.append(createdBy);
        sb.append(", createdDate=");
        sb.append(createdDate);
        sb.append(", invoiceLineNo=");
        sb.append(invoiceLineNo);
        sb.append(", errorCode=");
        sb.append(errorCode);
        sb.append(", intfInsertedDate=");
        sb.append(intfInsertedDate);
        sb.append(", itemNo=");
        sb.append(itemNo);
        sb.append(", reprocessedFlag=");
        sb.append(reprocessedFlag);
        sb.append(", acctIdentifierCodeQualifier=");
        sb.append(acctIdentifierCodeQualifier);
        sb.append(", contractId=");
        sb.append(contractId);
        sb.append(", brandId=");
        sb.append(brandId);
        sb.append(", comDivMktBrandProdKey=");
        sb.append(comDivMktBrandProdKey);
        sb.append(", claimIndicator=");
        sb.append(claimIndicator);
        sb.append(", ivldActualMasterSid=");
        sb.append(ivldActualMasterSid);
        sb.append(", batchId=");
        sb.append(batchId);
        sb.append(", provisionClaimIndicator=");
        sb.append(provisionClaimIndicator);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public IvldActualMaster toEntityModel() {
        IvldActualMasterImpl ivldActualMasterImpl = new IvldActualMasterImpl();

        if (actualIntfid == null) {
            ivldActualMasterImpl.setActualIntfid(StringPool.BLANK);
        } else {
            ivldActualMasterImpl.setActualIntfid(actualIntfid);
        }

        if (accountId == null) {
            ivldActualMasterImpl.setAccountId(StringPool.BLANK);
        } else {
            ivldActualMasterImpl.setAccountId(accountId);
        }

        if (programStateCode == null) {
            ivldActualMasterImpl.setProgramStateCode(StringPool.BLANK);
        } else {
            ivldActualMasterImpl.setProgramStateCode(programStateCode);
        }

        if (settlementNo == null) {
            ivldActualMasterImpl.setSettlementNo(StringPool.BLANK);
        } else {
            ivldActualMasterImpl.setSettlementNo(settlementNo);
        }

        if (accrualActualEndDate == null) {
            ivldActualMasterImpl.setAccrualActualEndDate(StringPool.BLANK);
        } else {
            ivldActualMasterImpl.setAccrualActualEndDate(accrualActualEndDate);
        }

        if (actualId == null) {
            ivldActualMasterImpl.setActualId(StringPool.BLANK);
        } else {
            ivldActualMasterImpl.setActualId(actualId);
        }

        if (modifiedDate == Long.MIN_VALUE) {
            ivldActualMasterImpl.setModifiedDate(null);
        } else {
            ivldActualMasterImpl.setModifiedDate(new Date(modifiedDate));
        }

        if (divisionId == null) {
            ivldActualMasterImpl.setDivisionId(StringPool.BLANK);
        } else {
            ivldActualMasterImpl.setDivisionId(divisionId);
        }

        if (organizationKey == null) {
            ivldActualMasterImpl.setOrganizationKey(StringPool.BLANK);
        } else {
            ivldActualMasterImpl.setOrganizationKey(organizationKey);
        }

        if (quantityInclusion == null) {
            ivldActualMasterImpl.setQuantityInclusion(StringPool.BLANK);
        } else {
            ivldActualMasterImpl.setQuantityInclusion(quantityInclusion);
        }

        if (cashPaidDate == null) {
            ivldActualMasterImpl.setCashPaidDate(StringPool.BLANK);
        } else {
            ivldActualMasterImpl.setCashPaidDate(cashPaidDate);
        }

        if (source == null) {
            ivldActualMasterImpl.setSource(StringPool.BLANK);
        } else {
            ivldActualMasterImpl.setSource(source);
        }

        if (addChgDelIndicator == null) {
            ivldActualMasterImpl.setAddChgDelIndicator(StringPool.BLANK);
        } else {
            ivldActualMasterImpl.setAddChgDelIndicator(addChgDelIndicator);
        }

        if (analysisCode == null) {
            ivldActualMasterImpl.setAnalysisCode(StringPool.BLANK);
        } else {
            ivldActualMasterImpl.setAnalysisCode(analysisCode);
        }

        if (accrualActualStartDate == null) {
            ivldActualMasterImpl.setAccrualActualStartDate(StringPool.BLANK);
        } else {
            ivldActualMasterImpl.setAccrualActualStartDate(accrualActualStartDate);
        }

        if (modifiedBy == null) {
            ivldActualMasterImpl.setModifiedBy(StringPool.BLANK);
        } else {
            ivldActualMasterImpl.setModifiedBy(modifiedBy);
        }

        if (salesAmount == null) {
            ivldActualMasterImpl.setSalesAmount(StringPool.BLANK);
        } else {
            ivldActualMasterImpl.setSalesAmount(salesAmount);
        }

        if (quantity == null) {
            ivldActualMasterImpl.setQuantity(StringPool.BLANK);
        } else {
            ivldActualMasterImpl.setQuantity(quantity);
        }

        if (sentOut == null) {
            ivldActualMasterImpl.setSentOut(StringPool.BLANK);
        } else {
            ivldActualMasterImpl.setSentOut(sentOut);
        }

        if (accountNo == null) {
            ivldActualMasterImpl.setAccountNo(StringPool.BLANK);
        } else {
            ivldActualMasterImpl.setAccountNo(accountNo);
        }

        if (reasonForFailure == null) {
            ivldActualMasterImpl.setReasonForFailure(StringPool.BLANK);
        } else {
            ivldActualMasterImpl.setReasonForFailure(reasonForFailure);
        }

        if (accountName == null) {
            ivldActualMasterImpl.setAccountName(StringPool.BLANK);
        } else {
            ivldActualMasterImpl.setAccountName(accountName);
        }

        if (provisionId == null) {
            ivldActualMasterImpl.setProvisionId(StringPool.BLANK);
        } else {
            ivldActualMasterImpl.setProvisionId(provisionId);
        }

        if (amount == null) {
            ivldActualMasterImpl.setAmount(StringPool.BLANK);
        } else {
            ivldActualMasterImpl.setAmount(amount);
        }

        if (marketId == null) {
            ivldActualMasterImpl.setMarketId(StringPool.BLANK);
        } else {
            ivldActualMasterImpl.setMarketId(marketId);
        }

        if (isActive == null) {
            ivldActualMasterImpl.setIsActive(StringPool.BLANK);
        } else {
            ivldActualMasterImpl.setIsActive(isActive);
        }

        if (settlementMethodNo == null) {
            ivldActualMasterImpl.setSettlementMethodNo(StringPool.BLANK);
        } else {
            ivldActualMasterImpl.setSettlementMethodNo(settlementMethodNo);
        }

        if (itemIdentifierCodeQualifier == null) {
            ivldActualMasterImpl.setItemIdentifierCodeQualifier(StringPool.BLANK);
        } else {
            ivldActualMasterImpl.setItemIdentifierCodeQualifier(itemIdentifierCodeQualifier);
        }

        if (priceAdjustmentName == null) {
            ivldActualMasterImpl.setPriceAdjustmentName(StringPool.BLANK);
        } else {
            ivldActualMasterImpl.setPriceAdjustmentName(priceAdjustmentName);
        }

        if (errorField == null) {
            ivldActualMasterImpl.setErrorField(StringPool.BLANK);
        } else {
            ivldActualMasterImpl.setErrorField(errorField);
        }

        if (recordSequence == null) {
            ivldActualMasterImpl.setRecordSequence(StringPool.BLANK);
        } else {
            ivldActualMasterImpl.setRecordSequence(recordSequence);
        }

        if (mandatedDiscountAmount == null) {
            ivldActualMasterImpl.setMandatedDiscountAmount(StringPool.BLANK);
        } else {
            ivldActualMasterImpl.setMandatedDiscountAmount(mandatedDiscountAmount);
        }

        if (parentcomDivmktBrandProdkey == null) {
            ivldActualMasterImpl.setParentcomDivmktBrandProdkey(StringPool.BLANK);
        } else {
            ivldActualMasterImpl.setParentcomDivmktBrandProdkey(parentcomDivmktBrandProdkey);
        }

        if (price == null) {
            ivldActualMasterImpl.setPrice(StringPool.BLANK);
        } else {
            ivldActualMasterImpl.setPrice(price);
        }

        if (dispensedDate == null) {
            ivldActualMasterImpl.setDispensedDate(StringPool.BLANK);
        } else {
            ivldActualMasterImpl.setDispensedDate(dispensedDate);
        }

        if (itemId == null) {
            ivldActualMasterImpl.setItemId(StringPool.BLANK);
        } else {
            ivldActualMasterImpl.setItemId(itemId);
        }

        if (accrualProcessed == null) {
            ivldActualMasterImpl.setAccrualProcessed(StringPool.BLANK);
        } else {
            ivldActualMasterImpl.setAccrualProcessed(accrualProcessed);
        }

        if (uploadDate == null) {
            ivldActualMasterImpl.setUploadDate(StringPool.BLANK);
        } else {
            ivldActualMasterImpl.setUploadDate(uploadDate);
        }

        if (createdBy == null) {
            ivldActualMasterImpl.setCreatedBy(StringPool.BLANK);
        } else {
            ivldActualMasterImpl.setCreatedBy(createdBy);
        }

        if (createdDate == Long.MIN_VALUE) {
            ivldActualMasterImpl.setCreatedDate(null);
        } else {
            ivldActualMasterImpl.setCreatedDate(new Date(createdDate));
        }

        if (invoiceLineNo == null) {
            ivldActualMasterImpl.setInvoiceLineNo(StringPool.BLANK);
        } else {
            ivldActualMasterImpl.setInvoiceLineNo(invoiceLineNo);
        }

        if (errorCode == null) {
            ivldActualMasterImpl.setErrorCode(StringPool.BLANK);
        } else {
            ivldActualMasterImpl.setErrorCode(errorCode);
        }

        if (intfInsertedDate == Long.MIN_VALUE) {
            ivldActualMasterImpl.setIntfInsertedDate(null);
        } else {
            ivldActualMasterImpl.setIntfInsertedDate(new Date(intfInsertedDate));
        }

        if (itemNo == null) {
            ivldActualMasterImpl.setItemNo(StringPool.BLANK);
        } else {
            ivldActualMasterImpl.setItemNo(itemNo);
        }

        if (reprocessedFlag == null) {
            ivldActualMasterImpl.setReprocessedFlag(StringPool.BLANK);
        } else {
            ivldActualMasterImpl.setReprocessedFlag(reprocessedFlag);
        }

        if (acctIdentifierCodeQualifier == null) {
            ivldActualMasterImpl.setAcctIdentifierCodeQualifier(StringPool.BLANK);
        } else {
            ivldActualMasterImpl.setAcctIdentifierCodeQualifier(acctIdentifierCodeQualifier);
        }

        if (contractId == null) {
            ivldActualMasterImpl.setContractId(StringPool.BLANK);
        } else {
            ivldActualMasterImpl.setContractId(contractId);
        }

        if (brandId == null) {
            ivldActualMasterImpl.setBrandId(StringPool.BLANK);
        } else {
            ivldActualMasterImpl.setBrandId(brandId);
        }

        if (comDivMktBrandProdKey == null) {
            ivldActualMasterImpl.setComDivMktBrandProdKey(StringPool.BLANK);
        } else {
            ivldActualMasterImpl.setComDivMktBrandProdKey(comDivMktBrandProdKey);
        }

        if (claimIndicator == null) {
            ivldActualMasterImpl.setClaimIndicator(StringPool.BLANK);
        } else {
            ivldActualMasterImpl.setClaimIndicator(claimIndicator);
        }

        ivldActualMasterImpl.setIvldActualMasterSid(ivldActualMasterSid);

        if (batchId == null) {
            ivldActualMasterImpl.setBatchId(StringPool.BLANK);
        } else {
            ivldActualMasterImpl.setBatchId(batchId);
        }

        if (provisionClaimIndicator == null) {
            ivldActualMasterImpl.setProvisionClaimIndicator(StringPool.BLANK);
        } else {
            ivldActualMasterImpl.setProvisionClaimIndicator(provisionClaimIndicator);
        }

        ivldActualMasterImpl.resetOriginalValues();

        return ivldActualMasterImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        actualIntfid = objectInput.readUTF();
        accountId = objectInput.readUTF();
        programStateCode = objectInput.readUTF();
        settlementNo = objectInput.readUTF();
        accrualActualEndDate = objectInput.readUTF();
        actualId = objectInput.readUTF();
        modifiedDate = objectInput.readLong();
        divisionId = objectInput.readUTF();
        organizationKey = objectInput.readUTF();
        quantityInclusion = objectInput.readUTF();
        cashPaidDate = objectInput.readUTF();
        source = objectInput.readUTF();
        addChgDelIndicator = objectInput.readUTF();
        analysisCode = objectInput.readUTF();
        accrualActualStartDate = objectInput.readUTF();
        modifiedBy = objectInput.readUTF();
        salesAmount = objectInput.readUTF();
        quantity = objectInput.readUTF();
        sentOut = objectInput.readUTF();
        accountNo = objectInput.readUTF();
        reasonForFailure = objectInput.readUTF();
        accountName = objectInput.readUTF();
        provisionId = objectInput.readUTF();
        amount = objectInput.readUTF();
        marketId = objectInput.readUTF();
        isActive = objectInput.readUTF();
        settlementMethodNo = objectInput.readUTF();
        itemIdentifierCodeQualifier = objectInput.readUTF();
        priceAdjustmentName = objectInput.readUTF();
        errorField = objectInput.readUTF();
        recordSequence = objectInput.readUTF();
        mandatedDiscountAmount = objectInput.readUTF();
        parentcomDivmktBrandProdkey = objectInput.readUTF();
        price = objectInput.readUTF();
        dispensedDate = objectInput.readUTF();
        itemId = objectInput.readUTF();
        accrualProcessed = objectInput.readUTF();
        uploadDate = objectInput.readUTF();
        createdBy = objectInput.readUTF();
        createdDate = objectInput.readLong();
        invoiceLineNo = objectInput.readUTF();
        errorCode = objectInput.readUTF();
        intfInsertedDate = objectInput.readLong();
        itemNo = objectInput.readUTF();
        reprocessedFlag = objectInput.readUTF();
        acctIdentifierCodeQualifier = objectInput.readUTF();
        contractId = objectInput.readUTF();
        brandId = objectInput.readUTF();
        comDivMktBrandProdKey = objectInput.readUTF();
        claimIndicator = objectInput.readUTF();
        ivldActualMasterSid = objectInput.readInt();
        batchId = objectInput.readUTF();
        provisionClaimIndicator = objectInput.readUTF();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        if (actualIntfid == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(actualIntfid);
        }

        if (accountId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(accountId);
        }

        if (programStateCode == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(programStateCode);
        }

        if (settlementNo == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(settlementNo);
        }

        if (accrualActualEndDate == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(accrualActualEndDate);
        }

        if (actualId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(actualId);
        }

        objectOutput.writeLong(modifiedDate);

        if (divisionId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(divisionId);
        }

        if (organizationKey == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(organizationKey);
        }

        if (quantityInclusion == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(quantityInclusion);
        }

        if (cashPaidDate == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(cashPaidDate);
        }

        if (source == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(source);
        }

        if (addChgDelIndicator == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(addChgDelIndicator);
        }

        if (analysisCode == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(analysisCode);
        }

        if (accrualActualStartDate == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(accrualActualStartDate);
        }

        if (modifiedBy == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(modifiedBy);
        }

        if (salesAmount == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(salesAmount);
        }

        if (quantity == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(quantity);
        }

        if (sentOut == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(sentOut);
        }

        if (accountNo == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(accountNo);
        }

        if (reasonForFailure == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(reasonForFailure);
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

        if (amount == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(amount);
        }

        if (marketId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(marketId);
        }

        if (isActive == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(isActive);
        }

        if (settlementMethodNo == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(settlementMethodNo);
        }

        if (itemIdentifierCodeQualifier == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(itemIdentifierCodeQualifier);
        }

        if (priceAdjustmentName == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(priceAdjustmentName);
        }

        if (errorField == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(errorField);
        }

        if (recordSequence == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(recordSequence);
        }

        if (mandatedDiscountAmount == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(mandatedDiscountAmount);
        }

        if (parentcomDivmktBrandProdkey == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(parentcomDivmktBrandProdkey);
        }

        if (price == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(price);
        }

        if (dispensedDate == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(dispensedDate);
        }

        if (itemId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(itemId);
        }

        if (accrualProcessed == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(accrualProcessed);
        }

        if (uploadDate == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(uploadDate);
        }

        if (createdBy == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(createdBy);
        }

        objectOutput.writeLong(createdDate);

        if (invoiceLineNo == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(invoiceLineNo);
        }

        if (errorCode == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(errorCode);
        }

        objectOutput.writeLong(intfInsertedDate);

        if (itemNo == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(itemNo);
        }

        if (reprocessedFlag == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(reprocessedFlag);
        }

        if (acctIdentifierCodeQualifier == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(acctIdentifierCodeQualifier);
        }

        if (contractId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(contractId);
        }

        if (brandId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(brandId);
        }

        if (comDivMktBrandProdKey == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(comDivMktBrandProdKey);
        }

        if (claimIndicator == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(claimIndicator);
        }

        objectOutput.writeInt(ivldActualMasterSid);

        if (batchId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(batchId);
        }

        if (provisionClaimIndicator == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(provisionClaimIndicator);
        }
    }
}
