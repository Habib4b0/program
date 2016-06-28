package com.stpl.app.model.impl;

import com.stpl.app.model.IvldSalesMaster;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing IvldSalesMaster in entity cache.
 *
 * @author
 * @see IvldSalesMaster
 * @generated
 */
public class IvldSalesMasterCacheModel implements CacheModel<IvldSalesMaster>,
    Externalizable {
    public String accountId;
    public String salesIntfid;
    public long modifiedDate;
    public String organizationKey;
    public String divisionId;
    public String source;
    public String addChgDelIndicator;
    public String analysisCode;
    public int ivldSalesMasterSid;
    public String docType;
    public String modifiedBy;
    public String lotNo;
    public String quantity;
    public String invoiceLineNumber;
    public String identifierCodeQualifier;
    public String accountCodeQualifier;
    public String parentItemId;
    public String accountNo;
    public String reasonForFailure;
    public String accountName;
    public String provisionId;
    public String amount;
    public String marketId;
    public String isActive;
    public String wholesaleOwnerId;
    public String priceAdjustmentName;
    public String salesId;
    public String errorField;
    public String recordSequence;
    public String price;
    public String customerSubtype;
    public String parentItemNo;
    public String itemId;
    public String orderReceivedDate;
    public String orderNumber;
    public String accountType;
    public String uploadDate;
    public String createdBy;
    public long createdDate;
    public String errorCode;
    public String itemUom;
    public long intfInsertedDate;
    public String invoiceNumber;
    public String orderSubtype;
    public String itemNo;
    public String reprocessedFlag;
    public String contractId;
    public String customerCompanyCode;
    public String orderType;
    public String companyId;
    public String brandId;
    public String invoiceDate;
    public String batchId;
    public String contractNo;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(109);

        sb.append("{accountId=");
        sb.append(accountId);
        sb.append(", salesIntfid=");
        sb.append(salesIntfid);
        sb.append(", modifiedDate=");
        sb.append(modifiedDate);
        sb.append(", organizationKey=");
        sb.append(organizationKey);
        sb.append(", divisionId=");
        sb.append(divisionId);
        sb.append(", source=");
        sb.append(source);
        sb.append(", addChgDelIndicator=");
        sb.append(addChgDelIndicator);
        sb.append(", analysisCode=");
        sb.append(analysisCode);
        sb.append(", ivldSalesMasterSid=");
        sb.append(ivldSalesMasterSid);
        sb.append(", docType=");
        sb.append(docType);
        sb.append(", modifiedBy=");
        sb.append(modifiedBy);
        sb.append(", lotNo=");
        sb.append(lotNo);
        sb.append(", quantity=");
        sb.append(quantity);
        sb.append(", invoiceLineNumber=");
        sb.append(invoiceLineNumber);
        sb.append(", identifierCodeQualifier=");
        sb.append(identifierCodeQualifier);
        sb.append(", accountCodeQualifier=");
        sb.append(accountCodeQualifier);
        sb.append(", parentItemId=");
        sb.append(parentItemId);
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
        sb.append(", wholesaleOwnerId=");
        sb.append(wholesaleOwnerId);
        sb.append(", priceAdjustmentName=");
        sb.append(priceAdjustmentName);
        sb.append(", salesId=");
        sb.append(salesId);
        sb.append(", errorField=");
        sb.append(errorField);
        sb.append(", recordSequence=");
        sb.append(recordSequence);
        sb.append(", price=");
        sb.append(price);
        sb.append(", customerSubtype=");
        sb.append(customerSubtype);
        sb.append(", parentItemNo=");
        sb.append(parentItemNo);
        sb.append(", itemId=");
        sb.append(itemId);
        sb.append(", orderReceivedDate=");
        sb.append(orderReceivedDate);
        sb.append(", orderNumber=");
        sb.append(orderNumber);
        sb.append(", accountType=");
        sb.append(accountType);
        sb.append(", uploadDate=");
        sb.append(uploadDate);
        sb.append(", createdBy=");
        sb.append(createdBy);
        sb.append(", createdDate=");
        sb.append(createdDate);
        sb.append(", errorCode=");
        sb.append(errorCode);
        sb.append(", itemUom=");
        sb.append(itemUom);
        sb.append(", intfInsertedDate=");
        sb.append(intfInsertedDate);
        sb.append(", invoiceNumber=");
        sb.append(invoiceNumber);
        sb.append(", orderSubtype=");
        sb.append(orderSubtype);
        sb.append(", itemNo=");
        sb.append(itemNo);
        sb.append(", reprocessedFlag=");
        sb.append(reprocessedFlag);
        sb.append(", contractId=");
        sb.append(contractId);
        sb.append(", customerCompanyCode=");
        sb.append(customerCompanyCode);
        sb.append(", orderType=");
        sb.append(orderType);
        sb.append(", companyId=");
        sb.append(companyId);
        sb.append(", brandId=");
        sb.append(brandId);
        sb.append(", invoiceDate=");
        sb.append(invoiceDate);
        sb.append(", batchId=");
        sb.append(batchId);
        sb.append(", contractNo=");
        sb.append(contractNo);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public IvldSalesMaster toEntityModel() {
        IvldSalesMasterImpl ivldSalesMasterImpl = new IvldSalesMasterImpl();

        if (accountId == null) {
            ivldSalesMasterImpl.setAccountId(StringPool.BLANK);
        } else {
            ivldSalesMasterImpl.setAccountId(accountId);
        }

        if (salesIntfid == null) {
            ivldSalesMasterImpl.setSalesIntfid(StringPool.BLANK);
        } else {
            ivldSalesMasterImpl.setSalesIntfid(salesIntfid);
        }

        if (modifiedDate == Long.MIN_VALUE) {
            ivldSalesMasterImpl.setModifiedDate(null);
        } else {
            ivldSalesMasterImpl.setModifiedDate(new Date(modifiedDate));
        }

        if (organizationKey == null) {
            ivldSalesMasterImpl.setOrganizationKey(StringPool.BLANK);
        } else {
            ivldSalesMasterImpl.setOrganizationKey(organizationKey);
        }

        if (divisionId == null) {
            ivldSalesMasterImpl.setDivisionId(StringPool.BLANK);
        } else {
            ivldSalesMasterImpl.setDivisionId(divisionId);
        }

        if (source == null) {
            ivldSalesMasterImpl.setSource(StringPool.BLANK);
        } else {
            ivldSalesMasterImpl.setSource(source);
        }

        if (addChgDelIndicator == null) {
            ivldSalesMasterImpl.setAddChgDelIndicator(StringPool.BLANK);
        } else {
            ivldSalesMasterImpl.setAddChgDelIndicator(addChgDelIndicator);
        }

        if (analysisCode == null) {
            ivldSalesMasterImpl.setAnalysisCode(StringPool.BLANK);
        } else {
            ivldSalesMasterImpl.setAnalysisCode(analysisCode);
        }

        ivldSalesMasterImpl.setIvldSalesMasterSid(ivldSalesMasterSid);

        if (docType == null) {
            ivldSalesMasterImpl.setDocType(StringPool.BLANK);
        } else {
            ivldSalesMasterImpl.setDocType(docType);
        }

        if (modifiedBy == null) {
            ivldSalesMasterImpl.setModifiedBy(StringPool.BLANK);
        } else {
            ivldSalesMasterImpl.setModifiedBy(modifiedBy);
        }

        if (lotNo == null) {
            ivldSalesMasterImpl.setLotNo(StringPool.BLANK);
        } else {
            ivldSalesMasterImpl.setLotNo(lotNo);
        }

        if (quantity == null) {
            ivldSalesMasterImpl.setQuantity(StringPool.BLANK);
        } else {
            ivldSalesMasterImpl.setQuantity(quantity);
        }

        if (invoiceLineNumber == null) {
            ivldSalesMasterImpl.setInvoiceLineNumber(StringPool.BLANK);
        } else {
            ivldSalesMasterImpl.setInvoiceLineNumber(invoiceLineNumber);
        }

        if (identifierCodeQualifier == null) {
            ivldSalesMasterImpl.setIdentifierCodeQualifier(StringPool.BLANK);
        } else {
            ivldSalesMasterImpl.setIdentifierCodeQualifier(identifierCodeQualifier);
        }

        if (accountCodeQualifier == null) {
            ivldSalesMasterImpl.setAccountCodeQualifier(StringPool.BLANK);
        } else {
            ivldSalesMasterImpl.setAccountCodeQualifier(accountCodeQualifier);
        }

        if (parentItemId == null) {
            ivldSalesMasterImpl.setParentItemId(StringPool.BLANK);
        } else {
            ivldSalesMasterImpl.setParentItemId(parentItemId);
        }

        if (accountNo == null) {
            ivldSalesMasterImpl.setAccountNo(StringPool.BLANK);
        } else {
            ivldSalesMasterImpl.setAccountNo(accountNo);
        }

        if (reasonForFailure == null) {
            ivldSalesMasterImpl.setReasonForFailure(StringPool.BLANK);
        } else {
            ivldSalesMasterImpl.setReasonForFailure(reasonForFailure);
        }

        if (accountName == null) {
            ivldSalesMasterImpl.setAccountName(StringPool.BLANK);
        } else {
            ivldSalesMasterImpl.setAccountName(accountName);
        }

        if (provisionId == null) {
            ivldSalesMasterImpl.setProvisionId(StringPool.BLANK);
        } else {
            ivldSalesMasterImpl.setProvisionId(provisionId);
        }

        if (amount == null) {
            ivldSalesMasterImpl.setAmount(StringPool.BLANK);
        } else {
            ivldSalesMasterImpl.setAmount(amount);
        }

        if (marketId == null) {
            ivldSalesMasterImpl.setMarketId(StringPool.BLANK);
        } else {
            ivldSalesMasterImpl.setMarketId(marketId);
        }

        if (isActive == null) {
            ivldSalesMasterImpl.setIsActive(StringPool.BLANK);
        } else {
            ivldSalesMasterImpl.setIsActive(isActive);
        }

        if (wholesaleOwnerId == null) {
            ivldSalesMasterImpl.setWholesaleOwnerId(StringPool.BLANK);
        } else {
            ivldSalesMasterImpl.setWholesaleOwnerId(wholesaleOwnerId);
        }

        if (priceAdjustmentName == null) {
            ivldSalesMasterImpl.setPriceAdjustmentName(StringPool.BLANK);
        } else {
            ivldSalesMasterImpl.setPriceAdjustmentName(priceAdjustmentName);
        }

        if (salesId == null) {
            ivldSalesMasterImpl.setSalesId(StringPool.BLANK);
        } else {
            ivldSalesMasterImpl.setSalesId(salesId);
        }

        if (errorField == null) {
            ivldSalesMasterImpl.setErrorField(StringPool.BLANK);
        } else {
            ivldSalesMasterImpl.setErrorField(errorField);
        }

        if (recordSequence == null) {
            ivldSalesMasterImpl.setRecordSequence(StringPool.BLANK);
        } else {
            ivldSalesMasterImpl.setRecordSequence(recordSequence);
        }

        if (price == null) {
            ivldSalesMasterImpl.setPrice(StringPool.BLANK);
        } else {
            ivldSalesMasterImpl.setPrice(price);
        }

        if (customerSubtype == null) {
            ivldSalesMasterImpl.setCustomerSubtype(StringPool.BLANK);
        } else {
            ivldSalesMasterImpl.setCustomerSubtype(customerSubtype);
        }

        if (parentItemNo == null) {
            ivldSalesMasterImpl.setParentItemNo(StringPool.BLANK);
        } else {
            ivldSalesMasterImpl.setParentItemNo(parentItemNo);
        }

        if (itemId == null) {
            ivldSalesMasterImpl.setItemId(StringPool.BLANK);
        } else {
            ivldSalesMasterImpl.setItemId(itemId);
        }

        if (orderReceivedDate == null) {
            ivldSalesMasterImpl.setOrderReceivedDate(StringPool.BLANK);
        } else {
            ivldSalesMasterImpl.setOrderReceivedDate(orderReceivedDate);
        }

        if (orderNumber == null) {
            ivldSalesMasterImpl.setOrderNumber(StringPool.BLANK);
        } else {
            ivldSalesMasterImpl.setOrderNumber(orderNumber);
        }

        if (accountType == null) {
            ivldSalesMasterImpl.setAccountType(StringPool.BLANK);
        } else {
            ivldSalesMasterImpl.setAccountType(accountType);
        }

        if (uploadDate == null) {
            ivldSalesMasterImpl.setUploadDate(StringPool.BLANK);
        } else {
            ivldSalesMasterImpl.setUploadDate(uploadDate);
        }

        if (createdBy == null) {
            ivldSalesMasterImpl.setCreatedBy(StringPool.BLANK);
        } else {
            ivldSalesMasterImpl.setCreatedBy(createdBy);
        }

        if (createdDate == Long.MIN_VALUE) {
            ivldSalesMasterImpl.setCreatedDate(null);
        } else {
            ivldSalesMasterImpl.setCreatedDate(new Date(createdDate));
        }

        if (errorCode == null) {
            ivldSalesMasterImpl.setErrorCode(StringPool.BLANK);
        } else {
            ivldSalesMasterImpl.setErrorCode(errorCode);
        }

        if (itemUom == null) {
            ivldSalesMasterImpl.setItemUom(StringPool.BLANK);
        } else {
            ivldSalesMasterImpl.setItemUom(itemUom);
        }

        if (intfInsertedDate == Long.MIN_VALUE) {
            ivldSalesMasterImpl.setIntfInsertedDate(null);
        } else {
            ivldSalesMasterImpl.setIntfInsertedDate(new Date(intfInsertedDate));
        }

        if (invoiceNumber == null) {
            ivldSalesMasterImpl.setInvoiceNumber(StringPool.BLANK);
        } else {
            ivldSalesMasterImpl.setInvoiceNumber(invoiceNumber);
        }

        if (orderSubtype == null) {
            ivldSalesMasterImpl.setOrderSubtype(StringPool.BLANK);
        } else {
            ivldSalesMasterImpl.setOrderSubtype(orderSubtype);
        }

        if (itemNo == null) {
            ivldSalesMasterImpl.setItemNo(StringPool.BLANK);
        } else {
            ivldSalesMasterImpl.setItemNo(itemNo);
        }

        if (reprocessedFlag == null) {
            ivldSalesMasterImpl.setReprocessedFlag(StringPool.BLANK);
        } else {
            ivldSalesMasterImpl.setReprocessedFlag(reprocessedFlag);
        }

        if (contractId == null) {
            ivldSalesMasterImpl.setContractId(StringPool.BLANK);
        } else {
            ivldSalesMasterImpl.setContractId(contractId);
        }

        if (customerCompanyCode == null) {
            ivldSalesMasterImpl.setCustomerCompanyCode(StringPool.BLANK);
        } else {
            ivldSalesMasterImpl.setCustomerCompanyCode(customerCompanyCode);
        }

        if (orderType == null) {
            ivldSalesMasterImpl.setOrderType(StringPool.BLANK);
        } else {
            ivldSalesMasterImpl.setOrderType(orderType);
        }

        if (companyId == null) {
            ivldSalesMasterImpl.setCompanyId(StringPool.BLANK);
        } else {
            ivldSalesMasterImpl.setCompanyId(companyId);
        }

        if (brandId == null) {
            ivldSalesMasterImpl.setBrandId(StringPool.BLANK);
        } else {
            ivldSalesMasterImpl.setBrandId(brandId);
        }

        if (invoiceDate == null) {
            ivldSalesMasterImpl.setInvoiceDate(StringPool.BLANK);
        } else {
            ivldSalesMasterImpl.setInvoiceDate(invoiceDate);
        }

        if (batchId == null) {
            ivldSalesMasterImpl.setBatchId(StringPool.BLANK);
        } else {
            ivldSalesMasterImpl.setBatchId(batchId);
        }

        if (contractNo == null) {
            ivldSalesMasterImpl.setContractNo(StringPool.BLANK);
        } else {
            ivldSalesMasterImpl.setContractNo(contractNo);
        }

        ivldSalesMasterImpl.resetOriginalValues();

        return ivldSalesMasterImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        accountId = objectInput.readUTF();
        salesIntfid = objectInput.readUTF();
        modifiedDate = objectInput.readLong();
        organizationKey = objectInput.readUTF();
        divisionId = objectInput.readUTF();
        source = objectInput.readUTF();
        addChgDelIndicator = objectInput.readUTF();
        analysisCode = objectInput.readUTF();
        ivldSalesMasterSid = objectInput.readInt();
        docType = objectInput.readUTF();
        modifiedBy = objectInput.readUTF();
        lotNo = objectInput.readUTF();
        quantity = objectInput.readUTF();
        invoiceLineNumber = objectInput.readUTF();
        identifierCodeQualifier = objectInput.readUTF();
        accountCodeQualifier = objectInput.readUTF();
        parentItemId = objectInput.readUTF();
        accountNo = objectInput.readUTF();
        reasonForFailure = objectInput.readUTF();
        accountName = objectInput.readUTF();
        provisionId = objectInput.readUTF();
        amount = objectInput.readUTF();
        marketId = objectInput.readUTF();
        isActive = objectInput.readUTF();
        wholesaleOwnerId = objectInput.readUTF();
        priceAdjustmentName = objectInput.readUTF();
        salesId = objectInput.readUTF();
        errorField = objectInput.readUTF();
        recordSequence = objectInput.readUTF();
        price = objectInput.readUTF();
        customerSubtype = objectInput.readUTF();
        parentItemNo = objectInput.readUTF();
        itemId = objectInput.readUTF();
        orderReceivedDate = objectInput.readUTF();
        orderNumber = objectInput.readUTF();
        accountType = objectInput.readUTF();
        uploadDate = objectInput.readUTF();
        createdBy = objectInput.readUTF();
        createdDate = objectInput.readLong();
        errorCode = objectInput.readUTF();
        itemUom = objectInput.readUTF();
        intfInsertedDate = objectInput.readLong();
        invoiceNumber = objectInput.readUTF();
        orderSubtype = objectInput.readUTF();
        itemNo = objectInput.readUTF();
        reprocessedFlag = objectInput.readUTF();
        contractId = objectInput.readUTF();
        customerCompanyCode = objectInput.readUTF();
        orderType = objectInput.readUTF();
        companyId = objectInput.readUTF();
        brandId = objectInput.readUTF();
        invoiceDate = objectInput.readUTF();
        batchId = objectInput.readUTF();
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

        if (salesIntfid == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(salesIntfid);
        }

        objectOutput.writeLong(modifiedDate);

        if (organizationKey == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(organizationKey);
        }

        if (divisionId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(divisionId);
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

        objectOutput.writeInt(ivldSalesMasterSid);

        if (docType == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(docType);
        }

        if (modifiedBy == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(modifiedBy);
        }

        if (lotNo == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(lotNo);
        }

        if (quantity == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(quantity);
        }

        if (invoiceLineNumber == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(invoiceLineNumber);
        }

        if (identifierCodeQualifier == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(identifierCodeQualifier);
        }

        if (accountCodeQualifier == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(accountCodeQualifier);
        }

        if (parentItemId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(parentItemId);
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

        if (wholesaleOwnerId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(wholesaleOwnerId);
        }

        if (priceAdjustmentName == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(priceAdjustmentName);
        }

        if (salesId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(salesId);
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

        if (price == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(price);
        }

        if (customerSubtype == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(customerSubtype);
        }

        if (parentItemNo == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(parentItemNo);
        }

        if (itemId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(itemId);
        }

        if (orderReceivedDate == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(orderReceivedDate);
        }

        if (orderNumber == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(orderNumber);
        }

        if (accountType == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(accountType);
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

        if (errorCode == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(errorCode);
        }

        if (itemUom == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(itemUom);
        }

        objectOutput.writeLong(intfInsertedDate);

        if (invoiceNumber == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(invoiceNumber);
        }

        if (orderSubtype == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(orderSubtype);
        }

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

        if (contractId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(contractId);
        }

        if (customerCompanyCode == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(customerCompanyCode);
        }

        if (orderType == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(orderType);
        }

        if (companyId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(companyId);
        }

        if (brandId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(brandId);
        }

        if (invoiceDate == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(invoiceDate);
        }

        if (batchId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(batchId);
        }

        if (contractNo == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(contractNo);
        }
    }
}
