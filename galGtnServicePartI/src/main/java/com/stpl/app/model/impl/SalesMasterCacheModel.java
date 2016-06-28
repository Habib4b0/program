package com.stpl.app.model.impl;

import com.stpl.app.model.SalesMaster;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing SalesMaster in entity cache.
 *
 * @author
 * @see SalesMaster
 * @generated
 */
public class SalesMasterCacheModel implements CacheModel<SalesMaster>,
    Externalizable {
    public String itemNo;
    public int recordSequence;
    public double quantity;
    public String accountId;
    public long createdDate;
    public String identifierCodeQualifier;
    public String isActive;
    public String marketId;
    public long invoiceDate;
    public String accountName;
    public String docType;
    public long orderReceivedDate;
    public double amount;
    public int salesMasterSid;
    public String orderSubtype;
    public int createdBy;
    public double price;
    public long uploadDate;
    public String itemId;
    public String priceAdjustmentName;
    public String itemCodeQualifier;
    public String contractId;
    public String itemUom;
    public long modifiedDate;
    public String customerSubtype;
    public int provisionId;
    public String wholesaleOwnerId;
    public String source;
    public String accountNo;
    public String lotNo;
    public String parentItemId;
    public String customerCompanyCode;
    public String analysisCode;
    public String accountType;
    public int modifiedBy;
    public String contractNo;
    public String batchId;
    public String brandId;
    public String salesId;
    public String companyId;
    public String organizationKey;
    public String itemParentNo;
    public String invoiceNumber;
    public String orderType;
    public boolean recordLockStatus;
    public String divisionId;
    public String invoiceLineNumber;
    public String orderNumber;
    public String inboundStatus;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(99);

        sb.append("{itemNo=");
        sb.append(itemNo);
        sb.append(", recordSequence=");
        sb.append(recordSequence);
        sb.append(", quantity=");
        sb.append(quantity);
        sb.append(", accountId=");
        sb.append(accountId);
        sb.append(", createdDate=");
        sb.append(createdDate);
        sb.append(", identifierCodeQualifier=");
        sb.append(identifierCodeQualifier);
        sb.append(", isActive=");
        sb.append(isActive);
        sb.append(", marketId=");
        sb.append(marketId);
        sb.append(", invoiceDate=");
        sb.append(invoiceDate);
        sb.append(", accountName=");
        sb.append(accountName);
        sb.append(", docType=");
        sb.append(docType);
        sb.append(", orderReceivedDate=");
        sb.append(orderReceivedDate);
        sb.append(", amount=");
        sb.append(amount);
        sb.append(", salesMasterSid=");
        sb.append(salesMasterSid);
        sb.append(", orderSubtype=");
        sb.append(orderSubtype);
        sb.append(", createdBy=");
        sb.append(createdBy);
        sb.append(", price=");
        sb.append(price);
        sb.append(", uploadDate=");
        sb.append(uploadDate);
        sb.append(", itemId=");
        sb.append(itemId);
        sb.append(", priceAdjustmentName=");
        sb.append(priceAdjustmentName);
        sb.append(", itemCodeQualifier=");
        sb.append(itemCodeQualifier);
        sb.append(", contractId=");
        sb.append(contractId);
        sb.append(", itemUom=");
        sb.append(itemUom);
        sb.append(", modifiedDate=");
        sb.append(modifiedDate);
        sb.append(", customerSubtype=");
        sb.append(customerSubtype);
        sb.append(", provisionId=");
        sb.append(provisionId);
        sb.append(", wholesaleOwnerId=");
        sb.append(wholesaleOwnerId);
        sb.append(", source=");
        sb.append(source);
        sb.append(", accountNo=");
        sb.append(accountNo);
        sb.append(", lotNo=");
        sb.append(lotNo);
        sb.append(", parentItemId=");
        sb.append(parentItemId);
        sb.append(", customerCompanyCode=");
        sb.append(customerCompanyCode);
        sb.append(", analysisCode=");
        sb.append(analysisCode);
        sb.append(", accountType=");
        sb.append(accountType);
        sb.append(", modifiedBy=");
        sb.append(modifiedBy);
        sb.append(", contractNo=");
        sb.append(contractNo);
        sb.append(", batchId=");
        sb.append(batchId);
        sb.append(", brandId=");
        sb.append(brandId);
        sb.append(", salesId=");
        sb.append(salesId);
        sb.append(", companyId=");
        sb.append(companyId);
        sb.append(", organizationKey=");
        sb.append(organizationKey);
        sb.append(", itemParentNo=");
        sb.append(itemParentNo);
        sb.append(", invoiceNumber=");
        sb.append(invoiceNumber);
        sb.append(", orderType=");
        sb.append(orderType);
        sb.append(", recordLockStatus=");
        sb.append(recordLockStatus);
        sb.append(", divisionId=");
        sb.append(divisionId);
        sb.append(", invoiceLineNumber=");
        sb.append(invoiceLineNumber);
        sb.append(", orderNumber=");
        sb.append(orderNumber);
        sb.append(", inboundStatus=");
        sb.append(inboundStatus);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public SalesMaster toEntityModel() {
        SalesMasterImpl salesMasterImpl = new SalesMasterImpl();

        if (itemNo == null) {
            salesMasterImpl.setItemNo(StringPool.BLANK);
        } else {
            salesMasterImpl.setItemNo(itemNo);
        }

        salesMasterImpl.setRecordSequence(recordSequence);
        salesMasterImpl.setQuantity(quantity);

        if (accountId == null) {
            salesMasterImpl.setAccountId(StringPool.BLANK);
        } else {
            salesMasterImpl.setAccountId(accountId);
        }

        if (createdDate == Long.MIN_VALUE) {
            salesMasterImpl.setCreatedDate(null);
        } else {
            salesMasterImpl.setCreatedDate(new Date(createdDate));
        }

        if (identifierCodeQualifier == null) {
            salesMasterImpl.setIdentifierCodeQualifier(StringPool.BLANK);
        } else {
            salesMasterImpl.setIdentifierCodeQualifier(identifierCodeQualifier);
        }

        if (isActive == null) {
            salesMasterImpl.setIsActive(StringPool.BLANK);
        } else {
            salesMasterImpl.setIsActive(isActive);
        }

        if (marketId == null) {
            salesMasterImpl.setMarketId(StringPool.BLANK);
        } else {
            salesMasterImpl.setMarketId(marketId);
        }

        if (invoiceDate == Long.MIN_VALUE) {
            salesMasterImpl.setInvoiceDate(null);
        } else {
            salesMasterImpl.setInvoiceDate(new Date(invoiceDate));
        }

        if (accountName == null) {
            salesMasterImpl.setAccountName(StringPool.BLANK);
        } else {
            salesMasterImpl.setAccountName(accountName);
        }

        if (docType == null) {
            salesMasterImpl.setDocType(StringPool.BLANK);
        } else {
            salesMasterImpl.setDocType(docType);
        }

        if (orderReceivedDate == Long.MIN_VALUE) {
            salesMasterImpl.setOrderReceivedDate(null);
        } else {
            salesMasterImpl.setOrderReceivedDate(new Date(orderReceivedDate));
        }

        salesMasterImpl.setAmount(amount);
        salesMasterImpl.setSalesMasterSid(salesMasterSid);

        if (orderSubtype == null) {
            salesMasterImpl.setOrderSubtype(StringPool.BLANK);
        } else {
            salesMasterImpl.setOrderSubtype(orderSubtype);
        }

        salesMasterImpl.setCreatedBy(createdBy);
        salesMasterImpl.setPrice(price);

        if (uploadDate == Long.MIN_VALUE) {
            salesMasterImpl.setUploadDate(null);
        } else {
            salesMasterImpl.setUploadDate(new Date(uploadDate));
        }

        if (itemId == null) {
            salesMasterImpl.setItemId(StringPool.BLANK);
        } else {
            salesMasterImpl.setItemId(itemId);
        }

        if (priceAdjustmentName == null) {
            salesMasterImpl.setPriceAdjustmentName(StringPool.BLANK);
        } else {
            salesMasterImpl.setPriceAdjustmentName(priceAdjustmentName);
        }

        if (itemCodeQualifier == null) {
            salesMasterImpl.setItemCodeQualifier(StringPool.BLANK);
        } else {
            salesMasterImpl.setItemCodeQualifier(itemCodeQualifier);
        }

        if (contractId == null) {
            salesMasterImpl.setContractId(StringPool.BLANK);
        } else {
            salesMasterImpl.setContractId(contractId);
        }

        if (itemUom == null) {
            salesMasterImpl.setItemUom(StringPool.BLANK);
        } else {
            salesMasterImpl.setItemUom(itemUom);
        }

        if (modifiedDate == Long.MIN_VALUE) {
            salesMasterImpl.setModifiedDate(null);
        } else {
            salesMasterImpl.setModifiedDate(new Date(modifiedDate));
        }

        if (customerSubtype == null) {
            salesMasterImpl.setCustomerSubtype(StringPool.BLANK);
        } else {
            salesMasterImpl.setCustomerSubtype(customerSubtype);
        }

        salesMasterImpl.setProvisionId(provisionId);

        if (wholesaleOwnerId == null) {
            salesMasterImpl.setWholesaleOwnerId(StringPool.BLANK);
        } else {
            salesMasterImpl.setWholesaleOwnerId(wholesaleOwnerId);
        }

        if (source == null) {
            salesMasterImpl.setSource(StringPool.BLANK);
        } else {
            salesMasterImpl.setSource(source);
        }

        if (accountNo == null) {
            salesMasterImpl.setAccountNo(StringPool.BLANK);
        } else {
            salesMasterImpl.setAccountNo(accountNo);
        }

        if (lotNo == null) {
            salesMasterImpl.setLotNo(StringPool.BLANK);
        } else {
            salesMasterImpl.setLotNo(lotNo);
        }

        if (parentItemId == null) {
            salesMasterImpl.setParentItemId(StringPool.BLANK);
        } else {
            salesMasterImpl.setParentItemId(parentItemId);
        }

        if (customerCompanyCode == null) {
            salesMasterImpl.setCustomerCompanyCode(StringPool.BLANK);
        } else {
            salesMasterImpl.setCustomerCompanyCode(customerCompanyCode);
        }

        if (analysisCode == null) {
            salesMasterImpl.setAnalysisCode(StringPool.BLANK);
        } else {
            salesMasterImpl.setAnalysisCode(analysisCode);
        }

        if (accountType == null) {
            salesMasterImpl.setAccountType(StringPool.BLANK);
        } else {
            salesMasterImpl.setAccountType(accountType);
        }

        salesMasterImpl.setModifiedBy(modifiedBy);

        if (contractNo == null) {
            salesMasterImpl.setContractNo(StringPool.BLANK);
        } else {
            salesMasterImpl.setContractNo(contractNo);
        }

        if (batchId == null) {
            salesMasterImpl.setBatchId(StringPool.BLANK);
        } else {
            salesMasterImpl.setBatchId(batchId);
        }

        if (brandId == null) {
            salesMasterImpl.setBrandId(StringPool.BLANK);
        } else {
            salesMasterImpl.setBrandId(brandId);
        }

        if (salesId == null) {
            salesMasterImpl.setSalesId(StringPool.BLANK);
        } else {
            salesMasterImpl.setSalesId(salesId);
        }

        if (companyId == null) {
            salesMasterImpl.setCompanyId(StringPool.BLANK);
        } else {
            salesMasterImpl.setCompanyId(companyId);
        }

        if (organizationKey == null) {
            salesMasterImpl.setOrganizationKey(StringPool.BLANK);
        } else {
            salesMasterImpl.setOrganizationKey(organizationKey);
        }

        if (itemParentNo == null) {
            salesMasterImpl.setItemParentNo(StringPool.BLANK);
        } else {
            salesMasterImpl.setItemParentNo(itemParentNo);
        }

        if (invoiceNumber == null) {
            salesMasterImpl.setInvoiceNumber(StringPool.BLANK);
        } else {
            salesMasterImpl.setInvoiceNumber(invoiceNumber);
        }

        if (orderType == null) {
            salesMasterImpl.setOrderType(StringPool.BLANK);
        } else {
            salesMasterImpl.setOrderType(orderType);
        }

        salesMasterImpl.setRecordLockStatus(recordLockStatus);

        if (divisionId == null) {
            salesMasterImpl.setDivisionId(StringPool.BLANK);
        } else {
            salesMasterImpl.setDivisionId(divisionId);
        }

        if (invoiceLineNumber == null) {
            salesMasterImpl.setInvoiceLineNumber(StringPool.BLANK);
        } else {
            salesMasterImpl.setInvoiceLineNumber(invoiceLineNumber);
        }

        if (orderNumber == null) {
            salesMasterImpl.setOrderNumber(StringPool.BLANK);
        } else {
            salesMasterImpl.setOrderNumber(orderNumber);
        }

        if (inboundStatus == null) {
            salesMasterImpl.setInboundStatus(StringPool.BLANK);
        } else {
            salesMasterImpl.setInboundStatus(inboundStatus);
        }

        salesMasterImpl.resetOriginalValues();

        return salesMasterImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        itemNo = objectInput.readUTF();
        recordSequence = objectInput.readInt();
        quantity = objectInput.readDouble();
        accountId = objectInput.readUTF();
        createdDate = objectInput.readLong();
        identifierCodeQualifier = objectInput.readUTF();
        isActive = objectInput.readUTF();
        marketId = objectInput.readUTF();
        invoiceDate = objectInput.readLong();
        accountName = objectInput.readUTF();
        docType = objectInput.readUTF();
        orderReceivedDate = objectInput.readLong();
        amount = objectInput.readDouble();
        salesMasterSid = objectInput.readInt();
        orderSubtype = objectInput.readUTF();
        createdBy = objectInput.readInt();
        price = objectInput.readDouble();
        uploadDate = objectInput.readLong();
        itemId = objectInput.readUTF();
        priceAdjustmentName = objectInput.readUTF();
        itemCodeQualifier = objectInput.readUTF();
        contractId = objectInput.readUTF();
        itemUom = objectInput.readUTF();
        modifiedDate = objectInput.readLong();
        customerSubtype = objectInput.readUTF();
        provisionId = objectInput.readInt();
        wholesaleOwnerId = objectInput.readUTF();
        source = objectInput.readUTF();
        accountNo = objectInput.readUTF();
        lotNo = objectInput.readUTF();
        parentItemId = objectInput.readUTF();
        customerCompanyCode = objectInput.readUTF();
        analysisCode = objectInput.readUTF();
        accountType = objectInput.readUTF();
        modifiedBy = objectInput.readInt();
        contractNo = objectInput.readUTF();
        batchId = objectInput.readUTF();
        brandId = objectInput.readUTF();
        salesId = objectInput.readUTF();
        companyId = objectInput.readUTF();
        organizationKey = objectInput.readUTF();
        itemParentNo = objectInput.readUTF();
        invoiceNumber = objectInput.readUTF();
        orderType = objectInput.readUTF();
        recordLockStatus = objectInput.readBoolean();
        divisionId = objectInput.readUTF();
        invoiceLineNumber = objectInput.readUTF();
        orderNumber = objectInput.readUTF();
        inboundStatus = objectInput.readUTF();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        if (itemNo == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(itemNo);
        }

        objectOutput.writeInt(recordSequence);
        objectOutput.writeDouble(quantity);

        if (accountId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(accountId);
        }

        objectOutput.writeLong(createdDate);

        if (identifierCodeQualifier == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(identifierCodeQualifier);
        }

        if (isActive == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(isActive);
        }

        if (marketId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(marketId);
        }

        objectOutput.writeLong(invoiceDate);

        if (accountName == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(accountName);
        }

        if (docType == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(docType);
        }

        objectOutput.writeLong(orderReceivedDate);
        objectOutput.writeDouble(amount);
        objectOutput.writeInt(salesMasterSid);

        if (orderSubtype == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(orderSubtype);
        }

        objectOutput.writeInt(createdBy);
        objectOutput.writeDouble(price);
        objectOutput.writeLong(uploadDate);

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

        if (itemCodeQualifier == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(itemCodeQualifier);
        }

        if (contractId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(contractId);
        }

        if (itemUom == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(itemUom);
        }

        objectOutput.writeLong(modifiedDate);

        if (customerSubtype == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(customerSubtype);
        }

        objectOutput.writeInt(provisionId);

        if (wholesaleOwnerId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(wholesaleOwnerId);
        }

        if (source == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(source);
        }

        if (accountNo == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(accountNo);
        }

        if (lotNo == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(lotNo);
        }

        if (parentItemId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(parentItemId);
        }

        if (customerCompanyCode == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(customerCompanyCode);
        }

        if (analysisCode == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(analysisCode);
        }

        if (accountType == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(accountType);
        }

        objectOutput.writeInt(modifiedBy);

        if (contractNo == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(contractNo);
        }

        if (batchId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(batchId);
        }

        if (brandId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(brandId);
        }

        if (salesId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(salesId);
        }

        if (companyId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(companyId);
        }

        if (organizationKey == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(organizationKey);
        }

        if (itemParentNo == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(itemParentNo);
        }

        if (invoiceNumber == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(invoiceNumber);
        }

        if (orderType == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(orderType);
        }

        objectOutput.writeBoolean(recordLockStatus);

        if (divisionId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(divisionId);
        }

        if (invoiceLineNumber == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(invoiceLineNumber);
        }

        if (orderNumber == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(orderNumber);
        }

        if (inboundStatus == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(inboundStatus);
        }
    }
}
