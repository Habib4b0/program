package com.stpl.app.parttwo.model.impl;

import com.stpl.app.parttwo.model.IvldCustomerGtsActual;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing IvldCustomerGtsActual in entity cache.
 *
 * @author
 * @see IvldCustomerGtsActual
 * @generated
 */
public class IvldCustomerGtsActualCacheModel implements CacheModel<IvldCustomerGtsActual>,
    Externalizable {
    public String parentAccountId;
    public int ivldCustomerGtsActualSid;
    public String accountId;
    public String itemId;
    public long orderReceivedDate;
    public long modifiedDate;
    public String orderNumber;
    public String organizationKey;
    public String source;
    public String createdBy;
    public String createdDate;
    public String addChgDelIndicator;
    public String errorCode;
    public String itemUom;
    public String invoiceNumber;
    public String modifiedBy;
    public long intfInsertedDate;
    public String lotNo;
    public String reprocessedFlag;
    public String quantity;
    public String invoiceLineNumber;
    public String contractId;
    public String reasonForFailure;
    public String amount;
    public long invoiceDate;
    public String customerGtsActualIntfId;
    public String batchId;
    public String salesId;
    public String errorField;
    public boolean checkRecord;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(61);

        sb.append("{parentAccountId=");
        sb.append(parentAccountId);
        sb.append(", ivldCustomerGtsActualSid=");
        sb.append(ivldCustomerGtsActualSid);
        sb.append(", accountId=");
        sb.append(accountId);
        sb.append(", itemId=");
        sb.append(itemId);
        sb.append(", orderReceivedDate=");
        sb.append(orderReceivedDate);
        sb.append(", modifiedDate=");
        sb.append(modifiedDate);
        sb.append(", orderNumber=");
        sb.append(orderNumber);
        sb.append(", organizationKey=");
        sb.append(organizationKey);
        sb.append(", source=");
        sb.append(source);
        sb.append(", createdBy=");
        sb.append(createdBy);
        sb.append(", createdDate=");
        sb.append(createdDate);
        sb.append(", addChgDelIndicator=");
        sb.append(addChgDelIndicator);
        sb.append(", errorCode=");
        sb.append(errorCode);
        sb.append(", itemUom=");
        sb.append(itemUom);
        sb.append(", invoiceNumber=");
        sb.append(invoiceNumber);
        sb.append(", modifiedBy=");
        sb.append(modifiedBy);
        sb.append(", intfInsertedDate=");
        sb.append(intfInsertedDate);
        sb.append(", lotNo=");
        sb.append(lotNo);
        sb.append(", reprocessedFlag=");
        sb.append(reprocessedFlag);
        sb.append(", quantity=");
        sb.append(quantity);
        sb.append(", invoiceLineNumber=");
        sb.append(invoiceLineNumber);
        sb.append(", contractId=");
        sb.append(contractId);
        sb.append(", reasonForFailure=");
        sb.append(reasonForFailure);
        sb.append(", amount=");
        sb.append(amount);
        sb.append(", invoiceDate=");
        sb.append(invoiceDate);
        sb.append(", customerGtsActualIntfId=");
        sb.append(customerGtsActualIntfId);
        sb.append(", batchId=");
        sb.append(batchId);
        sb.append(", salesId=");
        sb.append(salesId);
        sb.append(", errorField=");
        sb.append(errorField);
        sb.append(", checkRecord=");
        sb.append(checkRecord);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public IvldCustomerGtsActual toEntityModel() {
        IvldCustomerGtsActualImpl ivldCustomerGtsActualImpl = new IvldCustomerGtsActualImpl();

        if (parentAccountId == null) {
            ivldCustomerGtsActualImpl.setParentAccountId(StringPool.BLANK);
        } else {
            ivldCustomerGtsActualImpl.setParentAccountId(parentAccountId);
        }

        ivldCustomerGtsActualImpl.setIvldCustomerGtsActualSid(ivldCustomerGtsActualSid);

        if (accountId == null) {
            ivldCustomerGtsActualImpl.setAccountId(StringPool.BLANK);
        } else {
            ivldCustomerGtsActualImpl.setAccountId(accountId);
        }

        if (itemId == null) {
            ivldCustomerGtsActualImpl.setItemId(StringPool.BLANK);
        } else {
            ivldCustomerGtsActualImpl.setItemId(itemId);
        }

        if (orderReceivedDate == Long.MIN_VALUE) {
            ivldCustomerGtsActualImpl.setOrderReceivedDate(null);
        } else {
            ivldCustomerGtsActualImpl.setOrderReceivedDate(new Date(
                    orderReceivedDate));
        }

        if (modifiedDate == Long.MIN_VALUE) {
            ivldCustomerGtsActualImpl.setModifiedDate(null);
        } else {
            ivldCustomerGtsActualImpl.setModifiedDate(new Date(modifiedDate));
        }

        if (orderNumber == null) {
            ivldCustomerGtsActualImpl.setOrderNumber(StringPool.BLANK);
        } else {
            ivldCustomerGtsActualImpl.setOrderNumber(orderNumber);
        }

        if (organizationKey == null) {
            ivldCustomerGtsActualImpl.setOrganizationKey(StringPool.BLANK);
        } else {
            ivldCustomerGtsActualImpl.setOrganizationKey(organizationKey);
        }

        if (source == null) {
            ivldCustomerGtsActualImpl.setSource(StringPool.BLANK);
        } else {
            ivldCustomerGtsActualImpl.setSource(source);
        }

        if (createdBy == null) {
            ivldCustomerGtsActualImpl.setCreatedBy(StringPool.BLANK);
        } else {
            ivldCustomerGtsActualImpl.setCreatedBy(createdBy);
        }

        if (createdDate == null) {
            ivldCustomerGtsActualImpl.setCreatedDate(StringPool.BLANK);
        } else {
            ivldCustomerGtsActualImpl.setCreatedDate(createdDate);
        }

        if (addChgDelIndicator == null) {
            ivldCustomerGtsActualImpl.setAddChgDelIndicator(StringPool.BLANK);
        } else {
            ivldCustomerGtsActualImpl.setAddChgDelIndicator(addChgDelIndicator);
        }

        if (errorCode == null) {
            ivldCustomerGtsActualImpl.setErrorCode(StringPool.BLANK);
        } else {
            ivldCustomerGtsActualImpl.setErrorCode(errorCode);
        }

        if (itemUom == null) {
            ivldCustomerGtsActualImpl.setItemUom(StringPool.BLANK);
        } else {
            ivldCustomerGtsActualImpl.setItemUom(itemUom);
        }

        if (invoiceNumber == null) {
            ivldCustomerGtsActualImpl.setInvoiceNumber(StringPool.BLANK);
        } else {
            ivldCustomerGtsActualImpl.setInvoiceNumber(invoiceNumber);
        }

        if (modifiedBy == null) {
            ivldCustomerGtsActualImpl.setModifiedBy(StringPool.BLANK);
        } else {
            ivldCustomerGtsActualImpl.setModifiedBy(modifiedBy);
        }

        if (intfInsertedDate == Long.MIN_VALUE) {
            ivldCustomerGtsActualImpl.setIntfInsertedDate(null);
        } else {
            ivldCustomerGtsActualImpl.setIntfInsertedDate(new Date(
                    intfInsertedDate));
        }

        if (lotNo == null) {
            ivldCustomerGtsActualImpl.setLotNo(StringPool.BLANK);
        } else {
            ivldCustomerGtsActualImpl.setLotNo(lotNo);
        }

        if (reprocessedFlag == null) {
            ivldCustomerGtsActualImpl.setReprocessedFlag(StringPool.BLANK);
        } else {
            ivldCustomerGtsActualImpl.setReprocessedFlag(reprocessedFlag);
        }

        if (quantity == null) {
            ivldCustomerGtsActualImpl.setQuantity(StringPool.BLANK);
        } else {
            ivldCustomerGtsActualImpl.setQuantity(quantity);
        }

        if (invoiceLineNumber == null) {
            ivldCustomerGtsActualImpl.setInvoiceLineNumber(StringPool.BLANK);
        } else {
            ivldCustomerGtsActualImpl.setInvoiceLineNumber(invoiceLineNumber);
        }

        if (contractId == null) {
            ivldCustomerGtsActualImpl.setContractId(StringPool.BLANK);
        } else {
            ivldCustomerGtsActualImpl.setContractId(contractId);
        }

        if (reasonForFailure == null) {
            ivldCustomerGtsActualImpl.setReasonForFailure(StringPool.BLANK);
        } else {
            ivldCustomerGtsActualImpl.setReasonForFailure(reasonForFailure);
        }

        if (amount == null) {
            ivldCustomerGtsActualImpl.setAmount(StringPool.BLANK);
        } else {
            ivldCustomerGtsActualImpl.setAmount(amount);
        }

        if (invoiceDate == Long.MIN_VALUE) {
            ivldCustomerGtsActualImpl.setInvoiceDate(null);
        } else {
            ivldCustomerGtsActualImpl.setInvoiceDate(new Date(invoiceDate));
        }

        if (customerGtsActualIntfId == null) {
            ivldCustomerGtsActualImpl.setCustomerGtsActualIntfId(StringPool.BLANK);
        } else {
            ivldCustomerGtsActualImpl.setCustomerGtsActualIntfId(customerGtsActualIntfId);
        }

        if (batchId == null) {
            ivldCustomerGtsActualImpl.setBatchId(StringPool.BLANK);
        } else {
            ivldCustomerGtsActualImpl.setBatchId(batchId);
        }

        if (salesId == null) {
            ivldCustomerGtsActualImpl.setSalesId(StringPool.BLANK);
        } else {
            ivldCustomerGtsActualImpl.setSalesId(salesId);
        }

        if (errorField == null) {
            ivldCustomerGtsActualImpl.setErrorField(StringPool.BLANK);
        } else {
            ivldCustomerGtsActualImpl.setErrorField(errorField);
        }

        ivldCustomerGtsActualImpl.setCheckRecord(checkRecord);

        ivldCustomerGtsActualImpl.resetOriginalValues();

        return ivldCustomerGtsActualImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        parentAccountId = objectInput.readUTF();
        ivldCustomerGtsActualSid = objectInput.readInt();
        accountId = objectInput.readUTF();
        itemId = objectInput.readUTF();
        orderReceivedDate = objectInput.readLong();
        modifiedDate = objectInput.readLong();
        orderNumber = objectInput.readUTF();
        organizationKey = objectInput.readUTF();
        source = objectInput.readUTF();
        createdBy = objectInput.readUTF();
        createdDate = objectInput.readUTF();
        addChgDelIndicator = objectInput.readUTF();
        errorCode = objectInput.readUTF();
        itemUom = objectInput.readUTF();
        invoiceNumber = objectInput.readUTF();
        modifiedBy = objectInput.readUTF();
        intfInsertedDate = objectInput.readLong();
        lotNo = objectInput.readUTF();
        reprocessedFlag = objectInput.readUTF();
        quantity = objectInput.readUTF();
        invoiceLineNumber = objectInput.readUTF();
        contractId = objectInput.readUTF();
        reasonForFailure = objectInput.readUTF();
        amount = objectInput.readUTF();
        invoiceDate = objectInput.readLong();
        customerGtsActualIntfId = objectInput.readUTF();
        batchId = objectInput.readUTF();
        salesId = objectInput.readUTF();
        errorField = objectInput.readUTF();
        checkRecord = objectInput.readBoolean();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        if (parentAccountId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(parentAccountId);
        }

        objectOutput.writeInt(ivldCustomerGtsActualSid);

        if (accountId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(accountId);
        }

        if (itemId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(itemId);
        }

        objectOutput.writeLong(orderReceivedDate);
        objectOutput.writeLong(modifiedDate);

        if (orderNumber == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(orderNumber);
        }

        if (organizationKey == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(organizationKey);
        }

        if (source == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(source);
        }

        if (createdBy == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(createdBy);
        }

        if (createdDate == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(createdDate);
        }

        if (addChgDelIndicator == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(addChgDelIndicator);
        }

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

        if (invoiceNumber == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(invoiceNumber);
        }

        if (modifiedBy == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(modifiedBy);
        }

        objectOutput.writeLong(intfInsertedDate);

        if (lotNo == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(lotNo);
        }

        if (reprocessedFlag == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(reprocessedFlag);
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

        if (contractId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(contractId);
        }

        if (reasonForFailure == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(reasonForFailure);
        }

        if (amount == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(amount);
        }

        objectOutput.writeLong(invoiceDate);

        if (customerGtsActualIntfId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(customerGtsActualIntfId);
        }

        if (batchId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(batchId);
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

        objectOutput.writeBoolean(checkRecord);
    }
}
